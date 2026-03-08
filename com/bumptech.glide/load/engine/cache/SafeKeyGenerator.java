package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
class SafeKeyGenerator {
    private final LruCache<Key, String> loadIdToSafeHash = new LruCache<>(1000);

    SafeKeyGenerator() {
    }

    public String getSafeKey(Key key) {
        String strSha256BytesToHex;
        synchronized (this.loadIdToSafeHash) {
            strSha256BytesToHex = this.loadIdToSafeHash.get(key);
        }
        if (strSha256BytesToHex == null) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                key.updateDiskCacheKey(messageDigest);
                strSha256BytesToHex = Util.sha256BytesToHex(messageDigest.digest());
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            } catch (NoSuchAlgorithmException e3) {
                e3.printStackTrace();
            }
            synchronized (this.loadIdToSafeHash) {
                this.loadIdToSafeHash.put(key, strSha256BytesToHex);
            }
        }
        return strSha256BytesToHex;
    }
}