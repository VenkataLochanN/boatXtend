package com.amazon.identity.auth.device.authorization;

import android.os.Bundle;
import android.util.Base64;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
class CodeChallengeWorkflow {
    private static final String ALORITHM_SHA_256 = "SHA-256";
    public static final String CODE_CHALLENGE_KEY = "code_challenge";
    public static final String CODE_CHALLENGE_METHOD_KEY = "code_challenge_method";
    private static final String LOG_TAG = CodeChallengeWorkflow.class.getName();
    private static final String PLAIN = "plain";
    private static final String SHA_256 = "S256";
    private static CodeChallengeWorkflow instance;
    private String codeChallenge;
    private String codeChallengeMethod;
    private String codeVerifier;

    private CodeChallengeWorkflow() {
    }

    public static CodeChallengeWorkflow getInstance() {
        if (instance == null) {
            instance = new CodeChallengeWorkflow();
        }
        return instance;
    }

    public Bundle getProofKeyParameters() {
        this.codeVerifier = generateCodeVerifier();
        try {
            this.codeChallengeMethod = SHA_256;
            this.codeChallenge = generateCodeChallenge(this.codeVerifier, this.codeChallengeMethod);
        } catch (NoSuchAlgorithmException e2) {
            MAPLog.e(LOG_TAG, "Error generating Proof Key parmeter", e2);
            this.codeChallengeMethod = PLAIN;
            this.codeChallenge = this.codeVerifier;
        }
        Bundle bundle = new Bundle();
        bundle.putString(CODE_CHALLENGE_METHOD_KEY, this.codeChallengeMethod);
        bundle.putString(CODE_CHALLENGE_KEY, this.codeChallenge);
        return bundle;
    }

    public String getCodeVerifier() {
        return this.codeVerifier;
    }

    private String generateCodeChallenge(String str, String str2) throws NoSuchAlgorithmException {
        return SHA_256.equalsIgnoreCase(str2) ? base64UrlEncode(MessageDigest.getInstance(ALORITHM_SHA_256).digest(str.getBytes())) : str;
    }

    private String generateCodeVerifier() {
        return base64UrlEncode(generateRandomOctetSequence());
    }

    private byte[] generateRandomOctetSequence() {
        byte[] bArr = new byte[32];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    private String base64UrlEncode(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }
}