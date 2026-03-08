package com.ido.life.net;

import android.text.TextUtils;
import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.util.AESUtils;
import com.ido.life.util.RSASecurityCoderUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/* JADX INFO: loaded from: classes3.dex */
public class EncryptionInterceptor implements Interceptor {
    public static final String RSA_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCEk7+gpVynVQLH8B+L7Lel2ZA+jPlghyNwtRK4C/eOj63OS/RkcCI4KvosnZybNcXugj/zNAAheVX7Ktx2LyNRxz6bqm7AZSz8OweQQtanoHgyWEcsRtino554cnmMoPEql+h90KsFQOGPbGsQGm0+XD6aAaXydArND+XdCa2QawIDAQAB";
    private static final String TAG = EncryptionInterceptor.class.getSimpleName();

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return addPlatform(chain, chain.request());
    }

    private Response addPlatform(Interceptor.Chain chain, Request request) throws IOException {
        Request requestBuild = null;
        if (request != null) {
            try {
                if (request.body() != null && request.body().contentType() != null && "json".equalsIgnoreCase(request.body().contentType().subtype()) && "POST".equalsIgnoreCase(request.method()) && request.body().contentLength() > 0) {
                    RequestBody requestBodyBody = request.body();
                    Buffer buffer = new Buffer();
                    requestBodyBody.writeTo(buffer);
                    String string = buffer.readString(StandardCharsets.UTF_8);
                    if (!TextUtils.isEmpty(string)) {
                        CommonLogUtil.d(TAG, string);
                        String secretKey = AESUtils.getSecretKey();
                        CommonLogUtil.d(TAG, secretKey);
                        String strEncrypt = AESUtils.encrypt(string, secretKey, null);
                        CommonLogUtil.d(TAG, "加密后数据长度 keyBody=" + strEncrypt.length());
                        String strEncrypt2 = RSASecurityCoderUtils.encrypt(secretKey, RSASecurityCoderUtils.getPublicKey(RSA_KEY));
                        requestBuild = request.newBuilder().url(request.url()).headers(request.headers()).addHeader("SecureKey", strEncrypt2).addHeader(MAPCookie.KEY_SECURE, "CODEC").method(request.method(), RequestBody.create(MediaType.parse("application/json"), strEncrypt)).tag(request.tag()).build();
                        CommonLogUtil.d(TAG, "加密后的数据:" + strEncrypt + " SecureKey=" + strEncrypt2);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (requestBuild != null) {
            request = requestBuild;
        }
        return chain.proceed(request);
    }
}