package com.ido.life.util;

import android.view.View;
import com.boat.Xtend.two.R;
import kotlin.Metadata;
import kotlin.TypeCastException;

/* JADX INFO: compiled from: ClickUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¨\u0006\u0004"}, d2 = {"canClick", "", "view", "Landroid/view/View;", "app_release"}, k = 2, mv = {1, 1, 16})
public final class ClickUtilKt {
    public static final boolean canClick(View view) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (view == null || !(view.getTag(R.id.tag_click) instanceof Long)) {
            if (view != null) {
                view.setTag(R.id.tag_click, Long.valueOf(jCurrentTimeMillis));
            }
            return true;
        }
        Object tag = view.getTag(R.id.tag_click);
        if (tag == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Long");
        }
        if (Math.abs(jCurrentTimeMillis - ((Long) tag).longValue()) <= 500) {
            return false;
        }
        view.setTag(R.id.tag_click, Long.valueOf(jCurrentTimeMillis));
        return true;
    }
}