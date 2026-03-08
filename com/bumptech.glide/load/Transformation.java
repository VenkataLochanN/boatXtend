package com.bumptech.glide.load;

import com.bumptech.glide.load.engine.Resource;

/* JADX INFO: loaded from: classes.dex */
public interface Transformation<T> {
    String getId();

    Resource<T> transform(Resource<T> resource, int i, int i2);
}