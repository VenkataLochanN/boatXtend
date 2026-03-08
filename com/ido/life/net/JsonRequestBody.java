package com.ido.life.net;

import com.bumptech.glide.load.Key;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/* JADX INFO: loaded from: classes3.dex */
public class JsonRequestBody extends RequestBody {
    private Map<String, String> mParamsMap;

    public JsonRequestBody() {
    }

    public JsonRequestBody(Map<String, String> map) {
        this.mParamsMap = map;
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return MediaType.parse("application/json");
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        Map<String, String> map = this.mParamsMap;
        if (map == null || map.size() <= 0) {
            return;
        }
        JsonObject jsonObject = new JsonObject();
        for (String str : this.mParamsMap.keySet()) {
            jsonObject.addProperty(str, this.mParamsMap.get(str));
        }
        byte[] bytes = jsonObject.toString().getBytes(getCharset());
        bufferedSink.write(bytes, 0, bytes.length);
    }

    private Charset getCharset() {
        Charset charset = contentType().charset();
        return charset == null ? Charset.forName(Key.STRING_CHARSET_NAME) : charset;
    }
}