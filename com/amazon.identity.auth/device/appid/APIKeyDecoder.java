package com.amazon.identity.auth.device.appid;

import android.content.Context;
import android.content.pm.PackageManager;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.utils.HashAlgorithm;
import com.amazon.identity.auth.device.utils.JWTDecoder;
import com.amazon.identity.auth.device.utils.PackageSignatureUtil;
import com.amazon.identity.auth.device.utils.ThirdPartyResourceParser;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class APIKeyDecoder {
    private static final String EXPECTED_ISSUER = "Amazon";
    private static final String FAILED_TO_DECODE = "Failed to decode: ";
    private static final char HASH_SEPARATOR = ':';
    private static final String HTTPS = "https";
    private static final String KEY_API_KEY_VER = "ver";
    private static final String KEY_APP_FAMILY_ID = "appFamilyId";
    private static final String KEY_APP_ID = "appId";
    private static final String KEY_APP_VARIANT_ID = "appVariantId";
    public static final String KEY_AUTHORIZATION_HOST = "authz";
    private static final String KEY_CLIENT_ID = "clientId";
    public static final String KEY_ENDPOINTS = "endpoints";
    public static final String KEY_EXCHANGE_HOST = "tokenExchange";
    private static final String KEY_ISSUER = "iss";
    private static final String KEY_PACKAGE_NAME = "pkg";
    private static final String KEY_PERMISSIONS = "perm";
    private static final String KEY_SCOPES = "scopes";
    private static final String KEY_SIGNATURE_MD5 = "appsig";
    private static final String KEY_SIGNATURE_SHA256 = "appsigSha256";
    private static final String LOG_TAG = APIKeyDecoder.class.getName();
    private static final String VER_1 = "1";
    private static final String VER_3 = "3";

    private APIKeyDecoder() throws Exception {
        throw new Exception("This class is not instantiable!");
    }

    public static AppInfo decode(String str, String str2, Context context) {
        return doDecode(str, str2, true, context);
    }

    static AppInfo doDecode(String str, String str2, boolean z, Context context) {
        MAPLog.i(LOG_TAG, "Begin decoding API Key for packageName=" + str);
        JSONObject jSONObjectDecode = new JWTDecoder().decode(str2);
        MAPLog.pii(LOG_TAG, ThirdPartyResourceParser.KEY_API_KEY, "payload=" + jSONObjectDecode);
        if (jSONObjectDecode == null) {
            MAPLog.w(LOG_TAG, "Unable to decode APIKey for pkg=" + str);
            return null;
        }
        if (z) {
            try {
                verifyPayload(str, jSONObjectDecode, context);
            } catch (PackageManager.NameNotFoundException e2) {
                MAPLog.w(LOG_TAG, FAILED_TO_DECODE + e2.getMessage());
                MAPLog.w(LOG_TAG, "Unable to decode APIKey for pkg=" + str);
                return null;
            } catch (AuthError e3) {
                MAPLog.w(LOG_TAG, FAILED_TO_DECODE + e3.getMessage());
                MAPLog.w(LOG_TAG, "Unable to decode APIKey for pkg=" + str);
                return null;
            } catch (IOException e4) {
                MAPLog.w(LOG_TAG, FAILED_TO_DECODE + e4.getMessage());
                MAPLog.w(LOG_TAG, "Unable to decode APIKey for pkg=" + str);
                return null;
            } catch (SecurityException e5) {
                MAPLog.w(LOG_TAG, FAILED_TO_DECODE + e5.getMessage());
                MAPLog.w(LOG_TAG, "Unable to decode APIKey for pkg=" + str);
                return null;
            } catch (NoSuchAlgorithmException e6) {
                MAPLog.w(LOG_TAG, FAILED_TO_DECODE + e6.getMessage());
                MAPLog.w(LOG_TAG, "Unable to decode APIKey for pkg=" + str);
                return null;
            } catch (CertificateException e7) {
                MAPLog.w(LOG_TAG, FAILED_TO_DECODE + e7.getMessage());
                MAPLog.w(LOG_TAG, "Unable to decode APIKey for pkg=" + str);
                return null;
            } catch (JSONException e8) {
                MAPLog.w(LOG_TAG, FAILED_TO_DECODE + e8.getMessage());
                MAPLog.w(LOG_TAG, "Unable to decode APIKey for pkg=" + str);
                return null;
            }
        }
        return extractAppInfo(jSONObjectDecode);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.amazon.identity.auth.device.dataobject.AppInfo extractAppInfo(org.json.JSONObject r13) throws org.json.JSONException, com.amazon.identity.auth.device.AuthError {
        /*
            java.lang.String r0 = "ver"
            java.lang.String r0 = r13.getString(r0)
            java.lang.String r1 = "1"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L17
            java.lang.String r1 = "appId"
            java.lang.String r1 = r13.getString(r1)
            r4 = r1
            r5 = r4
            goto L25
        L17:
            java.lang.String r1 = "appFamilyId"
            java.lang.String r1 = r13.getString(r1)
            java.lang.String r2 = "appVariantId"
            java.lang.String r2 = r13.getString(r2)
            r4 = r1
            r5 = r2
        L25:
            java.lang.String r1 = "3"
            boolean r0 = r0.equals(r1)
            r1 = 0
            if (r0 == 0) goto L76
            java.lang.String r0 = "endpoints"
            org.json.JSONObject r0 = r13.getJSONObject(r0)     // Catch: org.json.JSONException -> L35
            goto L3d
        L35:
            java.lang.String r0 = com.amazon.identity.auth.device.appid.APIKeyDecoder.LOG_TAG
            java.lang.String r2 = "APIKey does not contain endpoints object"
            com.amazon.identity.auth.map.device.utils.MAPLog.w(r0, r2)
            r0 = r1
        L3d:
            if (r0 == 0) goto L76
            java.lang.String r2 = "authz"
            java.lang.String r2 = r0.getString(r2)
            java.lang.String r3 = "tokenExchange"
            java.lang.String r0 = r0.getString(r3)
            java.lang.String r3 = "https"
            if (r2 == 0) goto L60
            boolean r6 = r2.startsWith(r3)
            if (r6 == 0) goto L56
            goto L60
        L56:
            com.amazon.identity.auth.device.AuthError r13 = new com.amazon.identity.auth.device.AuthError
            com.amazon.identity.auth.device.AuthError$ERROR_TYPE r0 = com.amazon.identity.auth.device.AuthError.ERROR_TYPE.ERROR_BAD_PARAM
            java.lang.String r1 = "Authorization Host in APIKey is invalid"
            r13.<init>(r1, r0)
            throw r13
        L60:
            if (r0 == 0) goto L73
            boolean r3 = r0.startsWith(r3)
            if (r3 == 0) goto L69
            goto L73
        L69:
            com.amazon.identity.auth.device.AuthError r13 = new com.amazon.identity.auth.device.AuthError
            com.amazon.identity.auth.device.AuthError$ERROR_TYPE r0 = com.amazon.identity.auth.device.AuthError.ERROR_TYPE.ERROR_BAD_PARAM
            java.lang.String r1 = "Exchange Host in APIKey is invalid"
            r13.<init>(r1, r0)
            throw r13
        L73:
            r11 = r0
            r10 = r2
            goto L78
        L76:
            r10 = r1
            r11 = r10
        L78:
            java.lang.String r0 = "pkg"
            java.lang.String r6 = r13.getString(r0)
            java.lang.String r0 = "scopes"
            java.lang.String[] r7 = com.amazon.identity.auth.device.utils.JSONUtils.getStringArray(r13, r0)
            java.lang.String r0 = "clientId"
            java.lang.String r0 = r13.getString(r0)     // Catch: org.json.JSONException -> L8c
            r9 = r0
            goto L94
        L8c:
            java.lang.String r0 = com.amazon.identity.auth.device.appid.APIKeyDecoder.LOG_TAG
            java.lang.String r2 = "APIKey does not contain a client id"
            com.amazon.identity.auth.map.device.utils.MAPLog.w(r0, r2)
            r9 = r1
        L94:
            java.lang.String r0 = "perm"
            java.lang.String[] r8 = com.amazon.identity.auth.device.utils.JSONUtils.getStringArray(r13, r0)
            com.amazon.identity.auth.device.dataobject.AppInfo r0 = new com.amazon.identity.auth.device.dataobject.AppInfo
            r3 = r0
            r12 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.appid.APIKeyDecoder.extractAppInfo(org.json.JSONObject):com.amazon.identity.auth.device.dataobject.AppInfo");
    }

    private static void verifyPayload(String str, JSONObject jSONObject, Context context) throws JSONException, PackageManager.NameNotFoundException, NoSuchAlgorithmException, SecurityException, IOException, CertificateException {
        MAPLog.i(LOG_TAG, "verifyPayload for packageName=" + str);
        if (!jSONObject.getString(KEY_ISSUER).equals(EXPECTED_ISSUER)) {
            throw new SecurityException("Decoding fails: issuer (" + jSONObject.getString(KEY_ISSUER) + ") is not = " + EXPECTED_ISSUER + " pkg=" + str);
        }
        if (str != null && !str.equals(jSONObject.getString(KEY_PACKAGE_NAME))) {
            throw new SecurityException("Decoding fails: package names don't match! - " + str + " != " + jSONObject.getString(KEY_PACKAGE_NAME));
        }
        if (jSONObject.has(KEY_SIGNATURE_MD5)) {
            String string = jSONObject.getString(KEY_SIGNATURE_MD5);
            MAPLog.pii(LOG_TAG, "Validating MD5 signature in API key", String.format("pkg = %s and signature %s", str, string));
            verifySignature(string, str, HashAlgorithm.MD5, context);
        }
        if (jSONObject.has(KEY_SIGNATURE_SHA256)) {
            String string2 = jSONObject.getString(KEY_SIGNATURE_SHA256);
            MAPLog.pii(LOG_TAG, "Validating SHA256 signature in API key", String.format("pkg = %s and signature %s", str, string2));
            verifySignature(string2, str, HashAlgorithm.SHA_256, context);
        }
    }

    private static void verifySignature(String str, String str2, HashAlgorithm hashAlgorithm, Context context) {
        if (str == null) {
            MAPLog.d(LOG_TAG, "App Signature is null. pkg=" + str2);
            throw new SecurityException("Decoding failed: certificate fingerprint can't be verified! pkg=" + str2);
        }
        String strReplace = str.replace(":", "");
        List<String> allSignaturesFor = PackageSignatureUtil.getAllSignaturesFor(str2, hashAlgorithm, context);
        MAPLog.i(LOG_TAG, "Number of signatures = " + allSignaturesFor.size());
        MAPLog.pii(LOG_TAG, "Fingerprint checking", allSignaturesFor.toString());
        if (allSignaturesFor.contains(strReplace.toLowerCase(Locale.US))) {
            return;
        }
        throw new SecurityException("Decoding failed: certificate fingerprint can't be verified! pkg=" + str2);
    }
}