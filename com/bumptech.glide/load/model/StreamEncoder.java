package com.bumptech.glide.load.model;

import android.util.Log;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.util.ByteArrayPool;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class StreamEncoder implements Encoder<InputStream> {
    private static final String TAG = "StreamEncoder";

    @Override // com.bumptech.glide.load.Encoder
    public String getId() {
        return "";
    }

    @Override // com.bumptech.glide.load.Encoder
    public boolean encode(InputStream inputStream, OutputStream outputStream) {
        byte[] bytes = ByteArrayPool.get().getBytes();
        while (true) {
            try {
                int i = inputStream.read(bytes);
                if (i != -1) {
                    outputStream.write(bytes, 0, i);
                } else {
                    return true;
                }
            } catch (IOException e2) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Failed to encode data onto the OutputStream", e2);
                }
                return false;
            } finally {
                ByteArrayPool.get().releaseBytes(bytes);
            }
        }
    }
}