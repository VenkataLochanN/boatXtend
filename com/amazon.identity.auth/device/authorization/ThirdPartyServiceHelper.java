package com.amazon.identity.auth.device.authorization;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.IInterface;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.thread.ThreadUtils;
import com.amazon.identity.auth.device.utils.HashAlgorithm;
import com.amazon.identity.auth.device.utils.MAPConstants;
import com.amazon.identity.auth.device.utils.MAPUtils;
import com.amazon.identity.auth.device.utils.PackageSignatureUtil;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.amazon.identity.auth.map.device.utils.MAPVersion;
import com.ido.life.util.DateUtil;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class ThirdPartyServiceHelper {
    private static final String AUTHORIZATION_SERVICE_INTENT_ACTION_NAME = "com.amazon.identity.auth.device.authorization.MapAuthorizationService";
    private static final String BINDING_ERROR_MESSAGE = "Binding to authorization service has timed out!";
    private static final long CONNECTION_TIMEOUT = 10;
    private static final String LOG_TAG = ThirdPartyServiceHelper.class.getName();
    static String DEVO_FINGERPRINT = "7cac391937981b6134bdce1fd9834c253181f5abf91ded6078210d0f91ace360";
    private static String PROD_FINGERPRINT = "2f19adeb284eb36f7f07786152b9a1d14b21653203ad0b04ebbf9c73ab6d7625";
    private static Object lock = new Object();

    public static final class TOKEN_KEYS {
        public static final String ACCESS_ATZ_EXPIRES_IN = "accessAtzToken.expiries_in";
        public static final String ACCESS_ATZ_TOKEN = "accessAtzToken";
        public static final String REFRESH_ATZ_TOKEN = "refreshAtzToken";
    }

    static class HighestVersionedService {
        static MAPServiceInfo mapServiceInfo;
        static long serviceDiscoveryTimestamp;

        HighestVersionedService() {
        }

        public static MAPServiceInfo getMAPServiceInfo() {
            return mapServiceInfo;
        }

        static void setMAPServiceInfo(MAPServiceInfo mAPServiceInfo) {
            mapServiceInfo = mAPServiceInfo;
            if (mAPServiceInfo == null) {
                serviceDiscoveryTimestamp = 0L;
            } else {
                serviceDiscoveryTimestamp = new Date().getTime();
            }
        }

        static boolean isServiceCacheStale() {
            return mapServiceInfo == null || new Date().getTime() > serviceDiscoveryTimestamp + DateUtil.DAY;
        }
    }

    protected IInterface getAuthorizationServiceInstance(Context context, boolean z) throws AuthError {
        MAPServiceInfo mAPServiceInfo;
        if (ThreadUtils.isRunningOnMainThread()) {
            MAPLog.e(LOG_TAG, "getAuthorizationServiceInstance started on main thread");
            throw new IllegalStateException("getAuthorizationServiceInstance started on main thread");
        }
        MAPLog.d(LOG_TAG, "getAuthorizationServiceInstance");
        synchronized (lock) {
            if (z) {
                mAPServiceInfo = HighestVersionedService.getMAPServiceInfo();
                if (mAPServiceInfo != null) {
                    safeUnbind(context, mAPServiceInfo.getConnection(), mAPServiceInfo.getServiceIntent());
                    HighestVersionedService.setMAPServiceInfo(null);
                }
            } else {
                MAPServiceInfo mAPServiceInfo2 = HighestVersionedService.getMAPServiceInfo();
                if (mAPServiceInfo2 != null) {
                    safeUnbind(context, mAPServiceInfo2.getConnection(), mAPServiceInfo2.getServiceIntent());
                    if (bindHighestVersionedService(context)) {
                        return mAPServiceInfo2.getService();
                    }
                    HighestVersionedService.setMAPServiceInfo(null);
                }
                mAPServiceInfo = null;
            }
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent();
            intent.setAction(AUTHORIZATION_SERVICE_INTENT_ACTION_NAME);
            List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
            MAPLog.i(LOG_TAG, "Number of services found : " + listQueryIntentServices.size());
            List<MAPServiceInfo> allAuthorizationServices = getAllAuthorizationServices(context, listQueryIntentServices, mAPServiceInfo);
            synchronized (lock) {
                HighestVersionedService.setMAPServiceInfo(findAuthorizationService(allAuthorizationServices));
                if (HighestVersionedService.getMAPServiceInfo() == null) {
                    MAPLog.i(LOG_TAG, "Returning no service to use");
                    return null;
                }
                bindHighestVersionedService(context);
                MAPLog.i(LOG_TAG, "Returning service to use");
                MAPServiceInfo mAPServiceInfo3 = HighestVersionedService.getMAPServiceInfo();
                return mAPServiceInfo3 != null ? mAPServiceInfo3.getService() : null;
            }
        }
    }

    List<MAPServiceInfo> getAllAuthorizationServices(Context context, List<ResolveInfo> list, MAPServiceInfo mAPServiceInfo) throws AuthError {
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : list) {
            if (mAPServiceInfo == null || !resolveInfo.serviceInfo.applicationInfo.packageName.equals(mAPServiceInfo.mResolveInfo.serviceInfo.applicationInfo.packageName)) {
                try {
                    MAPLog.i(LOG_TAG, "Verifying signature for pkg=" + resolveInfo.serviceInfo.applicationInfo.packageName);
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(resolveInfo.serviceInfo.applicationInfo.packageName, 64);
                    if (isPlatformFireOS(context) && isDelgationCallToCredentialManager(packageInfo)) {
                        MAPLog.pii(LOG_TAG, "Current platform=", "FireOS with CredentialManager");
                    } else {
                        Signature[] signatureArr = packageInfo.signatures;
                        if (signatureArr.length == 1) {
                            if (!isFingerprintValid(context, signatureArr)) {
                                MAPLog.pii(LOG_TAG, "Security check failure", "Signature is incorrect.");
                            }
                        } else {
                            MAPLog.pii(LOG_TAG, "Security count failure", "Signature count (" + signatureArr.length + ") is incorrect.");
                        }
                    }
                    ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.applicationInfo.packageName, resolveInfo.serviceInfo.name);
                    Bundle bundle = context.getPackageManager().getServiceInfo(componentName, 128).metaData;
                    if (bundle != null) {
                        boolean z = bundle.getBoolean(MAPConstants.MAP_IS_PRIMARY);
                        String string = bundle.getString(MAPConstants.MAP_VERSION_KEY);
                        if (!TextUtils.isEmpty(string) || z) {
                            arrayList.add(new MAPServiceInfo(z ? MAPVersion.VERSION_ZERO : new MAPVersion(string), null, new ThirdPartyAuthorizationServiceConnection(), z, resolveInfo, new Intent().setComponent(componentName)));
                        }
                    }
                } catch (PackageManager.NameNotFoundException e2) {
                    MAPLog.pii(LOG_TAG, "NameNotFoundException.", "msg=" + e2.getMessage());
                }
            } else {
                MAPLog.i(LOG_TAG, "Ignoring previous service =" + resolveInfo.serviceInfo.name);
            }
        }
        return arrayList;
    }

    private boolean isPlatformFireOS(Context context) {
        boolean zHasSystemFeature = context.getPackageManager().hasSystemFeature("com.amazon.software.fireos");
        MAPLog.pii(LOG_TAG, "Is current platform FireOS:", String.valueOf(zHasSystemFeature));
        return zHasSystemFeature;
    }

    private boolean isDelgationCallToCredentialManager(PackageInfo packageInfo) {
        boolean zEquals = TextUtils.equals(packageInfo.packageName, "com.amazon.identity.auth.device.authorization");
        MAPLog.pii(LOG_TAG, "Is current package CredentialManager:", String.valueOf(zEquals));
        return zEquals;
    }

    private static boolean isFingerprintValid(Context context, Signature[] signatureArr) {
        if (MAPUtils.isDevo(context)) {
            MAPLog.i(LOG_TAG, "Attempting to check fingerprint in development environment");
            return isSameSignatureFingerprint(DEVO_FINGERPRINT, signatureArr[0]);
        }
        MAPLog.i(LOG_TAG, "Attempting to check fingerprint in production environment");
        return isSameSignatureFingerprint(PROD_FINGERPRINT, signatureArr[0]);
    }

    static boolean isSameSignatureFingerprint(String str, Signature signature) {
        try {
            String signatureFingerprint = PackageSignatureUtil.getSignatureFingerprint(signature, HashAlgorithm.SHA_256);
            MAPLog.pii(LOG_TAG, "Expected fingerprint", "Fingerprint=" + str);
            MAPLog.pii(LOG_TAG, "Extracted fingerprint", "Fingerprint=" + signatureFingerprint);
            return str.equals(signatureFingerprint);
        } catch (IOException e2) {
            MAPLog.pii(LOG_TAG, "IOException getting Fingerprint. ", e2.getMessage());
            return false;
        } catch (NoSuchAlgorithmException e3) {
            MAPLog.pii(LOG_TAG, "NoSuchAlgorithmException getting Fingerprint. ", e3.getMessage());
            return false;
        } catch (CertificateException e4) {
            MAPLog.pii(LOG_TAG, "CertificateException getting Fingerprint. ", e4.getMessage());
            return false;
        }
    }

    boolean bindHighestVersionedService(Context context) throws AuthError {
        if (HighestVersionedService.isServiceCacheStale()) {
            return false;
        }
        final MAPServiceInfo mAPServiceInfo = HighestVersionedService.getMAPServiceInfo();
        ServiceInfo serviceInfo = mAPServiceInfo.getResolveInfo().serviceInfo;
        ComponentName componentName = new ComponentName(serviceInfo.applicationInfo.packageName, serviceInfo.name);
        final Intent intent = new Intent();
        intent.setComponent(componentName);
        final ThirdPartyAuthorizationServiceConnection thirdPartyAuthorizationServiceConnection = new ThirdPartyAuthorizationServiceConnection();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        thirdPartyAuthorizationServiceConnection.setServiceListener(new AmazonServiceListener() { // from class: com.amazon.identity.auth.device.authorization.ThirdPartyServiceHelper.1
            @Override // com.amazon.identity.auth.device.authorization.AmazonServiceListener
            public void onBindSuccess(IInterface iInterface) {
                mAPServiceInfo.setService(iInterface);
                mAPServiceInfo.setConnection(thirdPartyAuthorizationServiceConnection);
                mAPServiceInfo.setServiceIntent(intent);
                countDownLatch.countDown();
            }

            @Override // com.amazon.identity.auth.device.authorization.AmazonServiceListener
            public void onBindError(AuthError authError) {
                mAPServiceInfo.setService(null);
                mAPServiceInfo.setConnection(null);
                mAPServiceInfo.setServiceIntent(null);
                MAPLog.i(ThirdPartyServiceHelper.LOG_TAG, "Bind - error");
                countDownLatch.countDown();
            }
        });
        if (context.bindService(intent, thirdPartyAuthorizationServiceConnection, 5)) {
            try {
                MAPLog.i(LOG_TAG, "Awaiting latch");
                if (!countDownLatch.await(CONNECTION_TIMEOUT, TimeUnit.SECONDS)) {
                    MAPLog.w(LOG_TAG, "Unable to establish bind within timelimit = 10");
                    HighestVersionedService.setMAPServiceInfo(null);
                    throw new AuthError(BINDING_ERROR_MESSAGE, AuthError.ERROR_TYPE.ERROR_THREAD);
                }
            } catch (InterruptedException e2) {
                MAPLog.pii(LOG_TAG, "InterruptedException", "msg+=" + e2.getMessage());
                HighestVersionedService.setMAPServiceInfo(null);
                throw new AuthError(BINDING_ERROR_MESSAGE, e2, AuthError.ERROR_TYPE.ERROR_THREAD);
            }
        } else {
            HighestVersionedService.setMAPServiceInfo(null);
            MAPLog.w(LOG_TAG, "Bind Service " + intent.getComponent().flattenToString() + "unsuccessful");
        }
        return true;
    }

    MAPServiceInfo findAuthorizationService(List<MAPServiceInfo> list) {
        MAPLog.i(LOG_TAG, "Number of MAP services to compare = " + list.size());
        MAPServiceInfo mAPServiceInfo = null;
        for (MAPServiceInfo mAPServiceInfo2 : list) {
            if (mAPServiceInfo == null || mAPServiceInfo2.getMapVersion().compare(mAPServiceInfo.getMapVersion()) > 0) {
                mAPServiceInfo = mAPServiceInfo2;
            }
        }
        return mAPServiceInfo;
    }

    public static void unbind(Context context) {
        synchronized (lock) {
            MAPLog.i(LOG_TAG, "Unbinding Highest Versioned Service");
            MAPServiceInfo mAPServiceInfo = HighestVersionedService.getMAPServiceInfo();
            if (mAPServiceInfo != null && mAPServiceInfo.getConnection() != null) {
                safeUnbind(context, mAPServiceInfo.getConnection(), mAPServiceInfo.getServiceIntent());
                mAPServiceInfo.setService(null);
                mAPServiceInfo.setConnection(null);
                mAPServiceInfo.setServiceIntent(null);
            }
        }
    }

    public static void clearCachedService(Context context) {
        synchronized (lock) {
            MAPLog.i(LOG_TAG, "Clearing Highest Versioned Service");
            MAPServiceInfo mAPServiceInfo = HighestVersionedService.getMAPServiceInfo();
            if (mAPServiceInfo != null) {
                safeUnbind(context, mAPServiceInfo.getConnection(), mAPServiceInfo.getServiceIntent());
                HighestVersionedService.setMAPServiceInfo(null);
            }
        }
    }

    public static void safeUnbind(Context context, ServiceConnection serviceConnection, Intent intent) {
        String packageName = intent != null ? intent.getComponent().getPackageName() : null;
        MAPLog.d(LOG_TAG, "Unbinding pkg=" + packageName);
        if (serviceConnection != null) {
            try {
                context.unbindService(serviceConnection);
            } catch (IllegalArgumentException unused) {
                Log.w(LOG_TAG, String.format("IllegalArgumentException is received during unbinding from %s. Ignored.", packageName));
            }
        }
    }

    public AmazonAuthorizationServiceInterface getRemoteAndroidService(Context context, boolean z) throws AuthError {
        MAPLog.i(LOG_TAG, "Attempting to retrieve remote Android service. Ignore cached service=" + z);
        return (AmazonAuthorizationServiceInterface) getAuthorizationServiceInstance(context, z);
    }

    class MAPServiceInfo {
        private ThirdPartyAuthorizationServiceConnection mConnection;
        private final boolean mIsPrimary;
        private final MAPVersion mMapVersion;
        private final ResolveInfo mResolveInfo;
        private IInterface mService;
        private Intent mServiceIntent;

        public MAPServiceInfo(MAPVersion mAPVersion, IInterface iInterface, ThirdPartyAuthorizationServiceConnection thirdPartyAuthorizationServiceConnection, boolean z, ResolveInfo resolveInfo, Intent intent) {
            this.mMapVersion = mAPVersion;
            this.mService = iInterface;
            setConnection(thirdPartyAuthorizationServiceConnection);
            this.mIsPrimary = z;
            this.mResolveInfo = resolveInfo;
            this.mServiceIntent = intent;
        }

        public MAPVersion getMapVersion() {
            return this.mMapVersion;
        }

        public void setService(IInterface iInterface) {
            this.mService = iInterface;
        }

        public IInterface getService() {
            return this.mService;
        }

        public ThirdPartyAuthorizationServiceConnection getConnection() {
            return this.mConnection;
        }

        public boolean isPrimary() {
            return this.mIsPrimary;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ResolveInfo getResolveInfo() {
            return this.mResolveInfo;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConnection(ThirdPartyAuthorizationServiceConnection thirdPartyAuthorizationServiceConnection) {
            this.mConnection = thirdPartyAuthorizationServiceConnection;
        }

        public void setServiceIntent(Intent intent) {
            this.mServiceIntent = intent;
        }

        public Intent getServiceIntent() {
            return this.mServiceIntent;
        }
    }
}