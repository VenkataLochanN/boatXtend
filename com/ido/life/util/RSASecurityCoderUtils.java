package com.ido.life.util;

import com.ido.life.appachcodec.Base64;
import com.ido.life.net.DecryptionInterceptor;
import com.ido.life.net.EncryptionInterceptor;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* JADX INFO: loaded from: classes3.dex */
public class RSASecurityCoderUtils {
    private static final int MAX_DECRYPT_BLOCK = 128;
    private static final int MAX_ENCRYPT_BLOCK = 117;

    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        return keyPairGenerator.generateKeyPair();
    }

    public static PrivateKey getPrivateKey(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(str.getBytes())));
        } catch (Exception unused) {
            return null;
        }
    }

    public static PublicKey getPublicKey(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(str.getBytes())));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String str, PublicKey publicKey) {
        byte[] bArrDoFinal;
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, publicKey);
            int length = str.getBytes().length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = length - i;
                if (i3 > 0) {
                    if (i3 > 117) {
                        bArrDoFinal = cipher.doFinal(str.getBytes(), i, 117);
                    } else {
                        bArrDoFinal = cipher.doFinal(str.getBytes(), i, i3);
                    }
                    byteArrayOutputStream.write(bArrDoFinal, 0, bArrDoFinal.length);
                    i2++;
                    i = i2 * 117;
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return Base64.encodeBase64String(byteArray);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String str, PrivateKey privateKey) {
        byte[] bArrDoFinal;
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(2, privateKey);
            byte[] bArrDecodeBase64 = Base64.decodeBase64(str);
            int length = bArrDecodeBase64.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = length - i;
                if (i3 > 0) {
                    if (i3 > 128) {
                        bArrDoFinal = cipher.doFinal(bArrDecodeBase64, i, 128);
                    } else {
                        bArrDoFinal = cipher.doFinal(bArrDecodeBase64, i, i3);
                    }
                    byteArrayOutputStream.write(bArrDoFinal, 0, bArrDoFinal.length);
                    i2++;
                    i = i2 * 128;
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return new String(byteArray, StandardCharsets.UTF_8);
                }
            }
        } catch (Exception unused) {
            return null;
        }
    }

    public static String sign(String str, PrivateKey privateKey) throws Exception {
        PrivateKey privateKeyGeneratePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKey.getEncoded()));
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKeyGeneratePrivate);
        signature.update(str.getBytes());
        return new String(Base64.encodeBase64(signature.sign()));
    }

    public static boolean verify(String str, PublicKey publicKey, String str2) throws Exception {
        PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKey.getEncoded()));
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(publicKeyGeneratePublic);
        signature.update(str.getBytes());
        return signature.verify(Base64.decodeBase64(str2.getBytes()));
    }

    public static void main(String[] strArr) {
        try {
            getKeyPair();
            System.out.println("私钥:" + DecryptionInterceptor.RSA_KEY_PRIVATE);
            System.out.println("公钥:" + EncryptionInterceptor.RSA_KEY);
            String strEncrypt = encrypt("B273BD38E266E80B4B477A875060628A7EBD7CF16171186C676E7E6C1CC9D8AA", getPublicKey(EncryptionInterceptor.RSA_KEY));
            System.out.println("加密后内容:" + strEncrypt);
            String strDecrypt = decrypt(strEncrypt, getPrivateKey(DecryptionInterceptor.RSA_KEY_PRIVATE));
            System.out.println("解密后内容:" + strDecrypt);
            boolean zVerify = verify("B273BD38E266E80B4B477A875060628A7EBD7CF16171186C676E7E6C1CC9D8AA", getPublicKey(EncryptionInterceptor.RSA_KEY), sign("B273BD38E266E80B4B477A875060628A7EBD7CF16171186C676E7E6C1CC9D8AA", getPrivateKey(DecryptionInterceptor.RSA_KEY_PRIVATE)));
            System.out.print("验签结果:" + zVerify);
        } catch (Exception e2) {
            e2.printStackTrace();
            System.out.print("加解密异常");
        }
    }
}