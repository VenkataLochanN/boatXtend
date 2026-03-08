package com.ido.life.net;

import android.text.TextUtils;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.util.AESUtils;
import com.ido.life.util.RSASecurityCoderUtils;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes3.dex */
public class DecryptionInterceptor implements Interceptor {
    public static final String RSA_KEY_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAISTv6ClXKdVAsfwH4vst6XZkD6M+WCHI3C1ErgL946Prc5L9GRwIjgq+iydnJs1xe6CP/M0ACF5Vfsq3HYvI1HHPpuqbsBlLPw7B5BC1qegeDJYRyxG2KejnnhyeYyg8SqX6H3QqwVA4Y9saxAabT5cPpoBpfJ0Cs0P5d0JrZBrAgMBAAECgYAZl5fXP/SZzJxD0kOUtDZjSpNAr9/31T5vJFUfuBeqqmunthvQQ6EnbIxsjmRLxd6WgwgrP2+DBrQlTwnNup27hU8NVZPW1o8sRiLR537LhZV/hhTbON83ETw+g+G+42EkomhJ0baq3+zUra0EJ5UGOFL2IsvGvJq8g6lEHkK5OQJBAMSWI1jayAB3e/oog9/C2VtTMW1xAIHK2x8MKS49vVm1cSWl+xKlke006B+iGkWii0CmeXD/ijHGOU3Hwo+ANecCQQCspTgjlD7DvCRBnjurz15meNuH0PwKIRWpB5mW1HN8vEfVMWg2orWWF+4iZc862zdCV3ceDs/bcQMWFFBal7jdAkA/QxmI5I7sJ4dMD3GwtmGAFBuZ3n2NRxyQtzchXFjs34jT1sAfVgzJyvbQBzhxJEevvGLkTDfKoMGcGCayS04NAkEAkad2fHXmmgZR5FZV63axIuzV8Xi8GuOcvZVe2+RzZooGZHQhgu61GmMoEjcopXQq47qdEWvXq0BmJSuQO04DtQJANlZGi6BXymVbEYvQ42/lyNCtvF43Ob9KodsYaWZQjsHZq6JJGaCbllyu0zIl4mgiFmfY35puG4deKkRU1Wvfhg==";
    private static final String TAG = DecryptionInterceptor.class.getSimpleName();

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws Exception {
        String strDecrypt;
        Request request = chain.request();
        Response responseProceed = chain.proceed(request);
        if (responseProceed.body() != null) {
            MediaType mediaTypeContentType = responseProceed.body().contentType();
            String strString = responseProceed.body().string();
            if (!TextUtils.isEmpty(strString)) {
                String strHeader = request.header("SecureKey");
                if (!TextUtils.isEmpty(strHeader)) {
                    CommonLogUtil.d(TAG, strHeader);
                    try {
                        strDecrypt = AESUtils.decrypt(strString, RSASecurityCoderUtils.decrypt(strHeader, RSASecurityCoderUtils.getPrivateKey(RSA_KEY_PRIVATE)), null);
                    } catch (Exception e2) {
                        e = e2;
                        strDecrypt = strString;
                    }
                    try {
                        CommonLogUtil.d(TAG, "返回的解密后数据内容：" + strDecrypt);
                        return responseProceed.newBuilder().body(ResponseBody.create(mediaTypeContentType, strDecrypt)).build();
                    } catch (Exception e3) {
                        e = e3;
                        CommonLogUtil.d(TAG, "数据未加密：" + strDecrypt);
                        e.printStackTrace();
                        return responseProceed.newBuilder().body(ResponseBody.create(mediaTypeContentType, strString)).build();
                    }
                }
                CommonLogUtil.d(TAG, "数据未加密：" + strString);
                return responseProceed.newBuilder().body(ResponseBody.create(mediaTypeContentType, strString)).build();
            }
            return responseProceed.newBuilder().body(ResponseBody.create(mediaTypeContentType, "")).build();
        }
        return responseProceed.newBuilder().body(ResponseBody.create(MediaType.parse("application/json"), "")).build();
    }
}