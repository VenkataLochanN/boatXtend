package com.amazon.identity.auth.device.utils;

import android.util.Base64;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class JWTDecoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String CHAR_SET = "UTF-8";
    private static final String FAILED_TO_DECODE = "Failed to decode: ";
    private static final String JWT_SPLITTER = "[.]";
    private static final String LOG_TAG = JWTDecoder.class.getName();

    private enum JWT_SECTION {
        HEADER,
        PAYLOAD,
        SIGNATURE
    }

    public JSONObject decode(String str) {
        if (str == null) {
            return null;
        }
        try {
            String[] tokenParts = getTokenParts(str.trim());
            verifySignature(tokenParts);
            return new JSONObject(decodeBase64ToString(tokenParts[JWT_SECTION.PAYLOAD.ordinal()]));
        } catch (UnsupportedEncodingException e2) {
            MAPLog.w(LOG_TAG, FAILED_TO_DECODE + e2.getMessage());
            return null;
        } catch (IOException e3) {
            MAPLog.w(LOG_TAG, FAILED_TO_DECODE + e3.getMessage());
            return null;
        } catch (IllegalArgumentException e4) {
            MAPLog.w(LOG_TAG, FAILED_TO_DECODE + e4.getMessage());
            return null;
        } catch (SecurityException e5) {
            MAPLog.w(LOG_TAG, FAILED_TO_DECODE + e5.getMessage());
            return null;
        } catch (InvalidKeyException e6) {
            MAPLog.w(LOG_TAG, FAILED_TO_DECODE + e6.getMessage());
            return null;
        } catch (NoSuchAlgorithmException e7) {
            MAPLog.w(LOG_TAG, FAILED_TO_DECODE + e7.getMessage());
            return null;
        } catch (NoSuchProviderException e8) {
            MAPLog.w(LOG_TAG, FAILED_TO_DECODE + e8.getMessage());
            return null;
        } catch (SignatureException e9) {
            MAPLog.w(LOG_TAG, FAILED_TO_DECODE + e9.getMessage());
            return null;
        } catch (CertificateException e10) {
            MAPLog.w(LOG_TAG, FAILED_TO_DECODE + e10.getMessage());
            return null;
        } catch (JSONException e11) {
            MAPLog.w(LOG_TAG, FAILED_TO_DECODE + e11.getMessage());
            return null;
        }
    }

    private String[] getTokenParts(String str) {
        String[] strArrSplit = str.split(JWT_SPLITTER);
        if (strArrSplit.length == 3) {
            return strArrSplit;
        }
        throw new IllegalArgumentException("Invalid JWT format");
    }

    private String decodeBase64ToString(String str) throws UnsupportedEncodingException {
        return new String(decodeBase64ToBytes(str), "UTF-8");
    }

    private byte[] decodeBase64ToBytes(String str) throws UnsupportedEncodingException {
        return Base64.decode(str.trim().getBytes("UTF-8"), 0);
    }

    private void verifySignature(String[] strArr) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, IOException, CertificateException, NoSuchProviderException {
        if (!verifySignatureWithRsaSha256(decodeBase64ToBytes(strArr[JWT_SECTION.SIGNATURE.ordinal()]), (strArr[JWT_SECTION.HEADER.ordinal()].trim() + "." + strArr[JWT_SECTION.PAYLOAD.ordinal()].trim()).getBytes("UTF-8"), SignatureUtil.getAmazonPublicCertificate())) {
            throw new SecurityException("Decoding fails: signature mismatch!");
        }
        MAPLog.i(LOG_TAG, "Signature match!");
    }

    private boolean verifySignatureWithRsaSha256(byte[] bArr, byte[] bArr2, Certificate certificate) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, NoSuchProviderException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(certificate);
        signature.update(bArr2);
        return signature.verify(bArr);
    }
}