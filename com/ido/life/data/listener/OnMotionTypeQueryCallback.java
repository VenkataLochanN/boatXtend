package com.ido.life.data.listener;

import com.ido.life.bean.MotionTypeBean;
import java.util.ArrayList;
import kotlin.Metadata;

/* JADX INFO: compiled from: OnMotionTypeQueryCallback.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007H&¨\u0006\b"}, d2 = {"Lcom/ido/life/data/listener/OnMotionTypeQueryCallback;", "", "onMotionTypeQuery", "", "types", "Ljava/util/ArrayList;", "Lcom/ido/life/bean/MotionTypeBean;", "Lkotlin/collections/ArrayList;", "app_release"}, k = 1, mv = {1, 1, 16})
public interface OnMotionTypeQueryCallback {
    void onMotionTypeQuery(ArrayList<MotionTypeBean> types);
}