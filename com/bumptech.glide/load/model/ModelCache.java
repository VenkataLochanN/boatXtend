package com.bumptech.glide.load.model;

import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
public class ModelCache<A, B> {
    private static final int DEFAULT_SIZE = 250;
    private final LruCache<ModelKey<A>, B> cache;

    public ModelCache() {
        this(250);
    }

    public ModelCache(int i) {
        this.cache = new LruCache<ModelKey<A>, B>(i) { // from class: com.bumptech.glide.load.model.ModelCache.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.bumptech.glide.util.LruCache
            public void onItemEvicted(ModelKey<A> modelKey, B b2) {
                modelKey.release();
            }
        };
    }

    public B get(A a2, int i, int i2) {
        ModelKey<A> modelKey = ModelKey.get(a2, i, i2);
        B b2 = this.cache.get(modelKey);
        modelKey.release();
        return b2;
    }

    public void put(A a2, int i, int i2, B b2) {
        this.cache.put(ModelKey.get(a2, i, i2), b2);
    }

    static final class ModelKey<A> {
        private static final Queue<ModelKey<?>> KEY_QUEUE = Util.createQueue(0);
        private int height;
        private A model;
        private int width;

        static <A> ModelKey<A> get(A a2, int i, int i2) {
            ModelKey<A> modelKey = (ModelKey) KEY_QUEUE.poll();
            if (modelKey == null) {
                modelKey = new ModelKey<>();
            }
            modelKey.init(a2, i, i2);
            return modelKey;
        }

        private ModelKey() {
        }

        private void init(A a2, int i, int i2) {
            this.model = a2;
            this.width = i;
            this.height = i2;
        }

        public void release() {
            KEY_QUEUE.offer(this);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ModelKey)) {
                return false;
            }
            ModelKey modelKey = (ModelKey) obj;
            return this.width == modelKey.width && this.height == modelKey.height && this.model.equals(modelKey.model);
        }

        public int hashCode() {
            return (((this.height * 31) + this.width) * 31) + this.model.hashCode();
        }
    }
}