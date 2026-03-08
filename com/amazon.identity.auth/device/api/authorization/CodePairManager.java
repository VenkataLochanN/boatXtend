package com.amazon.identity.auth.device.api.authorization;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.CodePairError;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.dataobject.CodePair;
import com.amazon.identity.auth.device.datastore.CodePairDataSource;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.amazon.identity.auth.device.endpoint.ServerCommunication;
import com.amazon.identity.auth.device.endpoint.TokenVendor;
import com.amazon.identity.auth.device.thread.ThreadUtils;
import com.amazon.identity.auth.device.utils.ScopeUtils;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CodePairManager {
    private static final String LOG_TAG = CodePairManager.class.getName();
    private static ServerCommunication mServerCommunication = new ServerCommunication();
    private static Comparator<Scope> ScopeNameComparator = new Comparator<Scope>() { // from class: com.amazon.identity.auth.device.api.authorization.CodePairManager.3
        @Override // java.util.Comparator
        public int compare(Scope scope, Scope scope2) {
            return scope.getName().compareTo(scope2.getName());
        }
    };

    public static void setServerCommunication(ServerCommunication serverCommunication) {
        mServerCommunication = serverCommunication;
    }

    public static void createCodePair(CreateCodePairRequest createCodePairRequest) {
        final Context context = createCodePairRequest.getContext();
        final List<Scope> scopes = createCodePairRequest.getScopes();
        final CodePairListener listener = createCodePairRequest.getListener();
        final AppInfo appInfo = createCodePairRequest.getAppInfo();
        if (scopes == null || scopes.isEmpty()) {
            MAPLog.e(LOG_TAG, "Vend code pair - No scopes passed in");
            listener.onError(new CodePairError("No scopes provided in parameters", CodePairError.ERROR_TYPE.ERROR_BAD_API_PARAM));
        } else {
            ThreadUtils.THREAD_POOL.execute(new Runnable() { // from class: com.amazon.identity.auth.device.api.authorization.CodePairManager.1
                @Override // java.lang.Runnable
                public void run() {
                    if (CodePairManager.deleteExistingCodePairSuccessful(listener, scopes, context)) {
                        try {
                            CodePair newCodePairFromPandaService = CodePairManager.getNewCodePairFromPandaService(scopes, context, appInfo, listener);
                            if (CodePairManager.insertCodePairIntoDatabaseSuccessful(listener, newCodePairFromPandaService, context)) {
                                MAPLog.i(CodePairManager.LOG_TAG, "New Code Pair has been inserted into the database");
                                listener.onSuccess(CodePairManager.getCodePairResult(newCodePairFromPandaService));
                            }
                        } catch (Exception e2) {
                            MAPLog.e(CodePairManager.LOG_TAG, "Failed to get the code pair from Panda Service", e2);
                            listener.onError(new CodePairError("Failed to get the code pair from Panda Service", CodePairError.ERROR_TYPE.ERROR_INVALID_REQUEST));
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean deleteExistingCodePairSuccessful(CodePairListener codePairListener, List<Scope> list, Context context) {
        CodePair existingCodePair = getExistingCodePair(convertScopeListToStringArray(list), context);
        if (existingCodePair == null) {
            return true;
        }
        MAPLog.i(LOG_TAG, "The existing code pair found! Delete it before getting a new one");
        if (existingCodePair.delete(context)) {
            MAPLog.i(LOG_TAG, "Succesfully deleted the old code pair and will create a new one!");
            return true;
        }
        MAPLog.e(LOG_TAG, "Unable to delete code pair in db");
        codePairListener.onError(new CodePairError("Unable to delete code pair in db", CodePairError.ERROR_TYPE.ERROR_DATA_STORAGE));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean insertCodePairIntoDatabaseSuccessful(CodePairListener codePairListener, CodePair codePair, Context context) {
        if (codePair.insert(context) != -1) {
            return true;
        }
        codePairListener.onError(new CodePairError("Unable to insert code pair into db", CodePairError.ERROR_TYPE.ERROR_DATA_STORAGE));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CodePairResult getCodePairResult(CodePair codePair) {
        CodePairResult codePairResult = new CodePairResult(codePair.getUserCode(), codePair.getVerificationUri().toString());
        MAPLog.i(LOG_TAG, "user code is: " + codePair.getUserCode());
        return codePairResult;
    }

    public static void getToken(GetTokenRequest getTokenRequest) {
        final Context context = getTokenRequest.getContext();
        final List<Scope> scopes = getTokenRequest.getScopes();
        final AppInfo appInfo = getTokenRequest.getAppInfo();
        final GetTokenListener listener = getTokenRequest.getListener();
        if (scopes == null || scopes.isEmpty()) {
            MAPLog.e(LOG_TAG, "Get Authorization tokens - No scopes passed in");
            listener.onError(new AuthError("No scopes provided in parameters", AuthError.ERROR_TYPE.ERROR_BAD_API_PARAM));
        } else {
            ThreadUtils.THREAD_POOL.execute(new Runnable() { // from class: com.amazon.identity.auth.device.api.authorization.CodePairManager.2
                @Override // java.lang.Runnable
                public void run() {
                    String[] strArrConvertScopeListToStringArray = CodePairManager.convertScopeListToStringArray(scopes);
                    String strConvertScopeArrayToString = ScopeUtils.convertScopeArrayToString(strArrConvertScopeListToStringArray);
                    try {
                        String strVendAccessToken = CodePairManager.vendAccessToken(strConvertScopeArrayToString, context, appInfo);
                        if (strVendAccessToken != null) {
                            MAPLog.i(CodePairManager.LOG_TAG, "Vend Access Token for the given scope successfully, simply return it");
                            listener.onSuccess(new GetTokenResult(strVendAccessToken));
                            return;
                        }
                        CodePair existingCodePair = CodePairManager.getExistingCodePair(strArrConvertScopeListToStringArray, context);
                        if (CodePairManager.isCodePairValid(listener, existingCodePair)) {
                            ScheduledExecutorService scheduledExecutorServiceNewSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
                            scheduledExecutorServiceNewSingleThreadScheduledExecutor.scheduleWithFixedDelay(new PollingTask(CodePairManager.mServerCommunication, listener, existingCodePair, scheduledExecutorServiceNewSingleThreadScheduledExecutor, context, appInfo, strConvertScopeArrayToString), 0L, existingCodePair.getInterval() * 1000, TimeUnit.MILLISECONDS);
                        }
                    } catch (AuthError e2) {
                        listener.onError(e2);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isCodePairValid(GetTokenListener getTokenListener, CodePair codePair) {
        if (codePair == null) {
            MAPLog.e(LOG_TAG, "No existing code pair found for getting token");
            getTokenListener.onError(new AuthError("No existing code pair found for getting token", AuthError.ERROR_TYPE.ERROR_BAD_API_PARAM));
            return false;
        }
        if (!hasCodePairExpired(codePair.getExpirationTime())) {
            return true;
        }
        MAPLog.e(LOG_TAG, "Code Pair has already expired");
        getTokenListener.onError(new AuthError("Code Pair has already expired", AuthError.ERROR_TYPE.ERROR_BAD_API_PARAM));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String vendAccessToken(String str, Context context, AppInfo appInfo) throws AuthError {
        try {
            return new TokenVendor().vendToken(null, new String[]{str}, context, new Bundle(), appInfo);
        } catch (IOException e2) {
            MAPLog.e(LOG_TAG, e2.getMessage(), e2);
            throw new AuthError("Error communicating with server", e2, AuthError.ERROR_TYPE.ERROR_IO);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CodePair getExistingCodePair(String[] strArr, Context context) {
        MAPLog.i(LOG_TAG, "Try finding an exisiting code pair for requested scopes");
        CodePair codePairFindOneRow = CodePairDataSource.getInstance(context).findOneRow(new String[]{DatabaseHelper.codePair_Scopes}, new String[]{ScopeUtils.convertScopeArrayToString(strArr)});
        if (codePairFindOneRow == null) {
            MAPLog.i(LOG_TAG, "Existing code pair not found!");
            return null;
        }
        MAPLog.i(LOG_TAG, "Existing code pair found for given scope");
        return codePairFindOneRow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CodePair getNewCodePairFromPandaService(List<Scope> list, Context context, AppInfo appInfo, CodePairListener codePairListener) throws AuthError, IOException, CodePairError {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return mServerCommunication.getCodePair(convertToInternalScope(list), context, appInfo);
    }

    private static com.amazon.identity.auth.device.dataobject.Scope[] convertToInternalScope(List<Scope> list) {
        com.amazon.identity.auth.device.dataobject.Scope[] scopeArr = new com.amazon.identity.auth.device.dataobject.Scope[list.size()];
        int i = 0;
        for (Scope scope : list) {
            String name = scope.getName();
            JSONObject scopeData = scope.getScopeData();
            if (scopeData != null) {
                scopeArr[i] = new com.amazon.identity.auth.device.dataobject.Scope(name, scopeData.toString());
                i++;
            } else {
                scopeArr[i] = new com.amazon.identity.auth.device.dataobject.Scope(name);
                i++;
            }
        }
        return scopeArr;
    }

    public static boolean hasCodePairExpired(Date date) {
        return date.before(new Date());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String[] convertScopeListToStringArray(List<Scope> list) {
        Collections.sort(list, ScopeNameComparator);
        String[] strArr = new String[list.size()];
        Iterator<Scope> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            strArr[i] = it.next().getName();
            i++;
        }
        return strArr;
    }
}