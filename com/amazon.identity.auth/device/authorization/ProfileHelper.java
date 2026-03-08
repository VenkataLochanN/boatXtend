package com.amazon.identity.auth.device.authorization;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.InsufficientScopeAuthError;
import com.amazon.identity.auth.device.InvalidTokenAuthError;
import com.amazon.identity.auth.device.appid.ThirdPartyAppIdentifier;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.dataobject.Profile;
import com.amazon.identity.auth.device.dataobject.RequestedScope;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.amazon.identity.auth.device.datastore.ProfileDataSource;
import com.amazon.identity.auth.device.datastore.RequestedScopeDataSource;
import com.amazon.identity.auth.device.endpoint.ServerCommunication;
import com.amazon.identity.auth.device.shared.APIListener;
import com.amazon.identity.auth.device.utils.LWAConstants;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ProfileHelper {
    private static final String TAG = ProfileHelper.class.getName();
    private static ServerCommunication mServerCommunication = new ServerCommunication();

    public static void getProfile(final Context context, String str, final Bundle bundle, final APIListener aPIListener) {
        final AppInfo appInfo = new ThirdPartyAppIdentifier().getAppInfo(str, context);
        if (appInfo == null) {
            aPIListener.onError(new AuthError("App info is null", AuthError.ERROR_TYPE.ERROR_ACCESS_DENIED));
            return;
        }
        try {
            TokenHelper.getToken(context, str, appInfo.getClientId(), getAuthorizedScopes(context, appInfo), new APIListener() { // from class: com.amazon.identity.auth.device.authorization.ProfileHelper.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.amazon.identity.auth.device.api.Listener
                public void onSuccess(Bundle bundle2) {
                    String string = bundle2.getString(AuthzConstants.BUNDLE_KEY.TOKEN.val);
                    if (!TextUtils.isEmpty(string)) {
                        Bundle profileLocal = ProfileHelper.getProfileLocal(context, appInfo.getAppFamilyId());
                        if (profileLocal != null) {
                            MAPLog.pii(ProfileHelper.TAG, "Returning local profile information", profileLocal.toString());
                            aPIListener.onSuccess(ProfileHelper.createResponseBundle(profileLocal));
                            return;
                        }
                        try {
                            JSONObject profileRemote = ProfileHelper.getProfileRemote(context, string, bundle, appInfo);
                            MAPLog.d(ProfileHelper.TAG, "Returning remote profile information");
                            aPIListener.onSuccess(ProfileHelper.createResponseBundle(ProfileHelper.createProfileBundle(profileRemote)));
                            ProfileHelper.updateProfileLocal(context, appInfo.getAppFamilyId(), profileRemote);
                            return;
                        } catch (InsufficientScopeAuthError e2) {
                            MAPLog.e(ProfileHelper.TAG, e2.getMessage());
                            if (!ProfileHelper.shouldFailOnInsufficientScope(bundle)) {
                                aPIListener.onSuccess(ProfileHelper.createResponseBundle(null));
                                return;
                            } else {
                                aPIListener.onError((AuthError) e2);
                                return;
                            }
                        } catch (InvalidTokenAuthError e3) {
                            MAPLog.e(ProfileHelper.TAG, "Invalid token sent to the server. Cleaning up local state");
                            DatabaseHelper.clearAuthorizationState(context);
                            aPIListener.onError((AuthError) e3);
                            return;
                        } catch (AuthError e4) {
                            MAPLog.e(ProfileHelper.TAG, e4.getMessage());
                            aPIListener.onError(e4);
                            return;
                        } catch (IOException e5) {
                            MAPLog.e(ProfileHelper.TAG, e5.getMessage(), e5);
                            aPIListener.onError(new AuthError(e5.getMessage(), AuthError.ERROR_TYPE.ERROR_IO));
                            return;
                        } catch (JSONException e6) {
                            MAPLog.e(ProfileHelper.TAG, e6.getMessage(), e6);
                            aPIListener.onError(new AuthError(e6.getMessage(), AuthError.ERROR_TYPE.ERROR_JSON));
                            return;
                        }
                    }
                    ProfileDataSource.getInstance(context).deleteAllRows();
                    MAPLog.e(ProfileHelper.TAG, "Not authorized for getProfile");
                    if (!ProfileHelper.shouldFailOnInsufficientScope(bundle)) {
                        aPIListener.onSuccess(ProfileHelper.createResponseBundle(null));
                    } else {
                        aPIListener.onError((AuthError) new InsufficientScopeAuthError("Profile request not valid for authorized scopes"));
                    }
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.amazon.identity.auth.device.api.Listener
                public void onError(AuthError authError) {
                    aPIListener.onError(authError);
                }
            }, new ThirdPartyAppIdentifier(), bundle);
        } catch (AuthError e2) {
            aPIListener.onError(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle getProfileLocal(Context context, String str) {
        MAPLog.d(TAG, "Accessing local profile information");
        Profile profile = ProfileDataSource.getInstance(context).getProfile(str);
        if (profile == null || profile.hasExpired()) {
            MAPLog.d(TAG, "Local profile information does not exist, or has expired");
            return null;
        }
        try {
            return profile.getDataAsBundle();
        } catch (AuthError unused) {
            MAPLog.d(TAG, "Local profile information invalid");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject getProfileRemote(Context context, String str, Bundle bundle, AppInfo appInfo) throws AuthError, IOException {
        MAPLog.d(TAG, "Fetching remote profile information");
        return mServerCommunication.getProfile(context, str, bundle, appInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateProfileLocal(Context context, String str, JSONObject jSONObject) {
        MAPLog.d(TAG, "Updating local profile information");
        ProfileDataSource profileDataSource = ProfileDataSource.getInstance(context);
        profileDataSource.deleteAllRows();
        profileDataSource.insertRow(new Profile(str, jSONObject.toString()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle createProfileBundle(JSONObject jSONObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            bundle.putString(next, jSONObject.getString(next));
        }
        MAPLog.pii(TAG, "Profile Information", bundle.toString());
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle createResponseBundle(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putBundle(AuthzConstants.BUNDLE_KEY.PROFILE.val, bundle);
        return bundle2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean shouldFailOnInsufficientScope(Bundle bundle) {
        return bundle != null && bundle.containsKey(LWAConstants.PROFILE_BUNDLE_KEY.FAIL_ON_INSUFFICIENT_SCOPE.val);
    }

    private static String[] getAuthorizedScopes(Context context, AppInfo appInfo) {
        List<RequestedScope> listFindByAppFamilyId = RequestedScopeDataSource.getInstance(context).findByAppFamilyId(appInfo.getAppFamilyId());
        String[] strArr = new String[listFindByAppFamilyId.size()];
        Iterator<RequestedScope> it = listFindByAppFamilyId.iterator();
        int i = 0;
        while (it.hasNext()) {
            strArr[i] = it.next().getScopeValue();
            i++;
        }
        return strArr;
    }

    static void setServerCommunication(ServerCommunication serverCommunication) {
        mServerCommunication = serverCommunication;
    }
}