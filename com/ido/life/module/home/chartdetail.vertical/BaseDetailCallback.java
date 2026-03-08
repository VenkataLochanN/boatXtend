package com.ido.life.module.home.chartdetail.vertical;

import android.content.Context;
import android.view.View;
import com.boat.Xtend.two.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseDetailCallback.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\u0012\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0012\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0003H&J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\nH&J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\nH&J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\nH&J\u0010\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0003H&J\b\u0010\u001a\u001a\u00020\u0003H&J\b\u0010\u001b\u001a\u00020\u0003H&¨\u0006\u001c"}, d2 = {"Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailCallback;", "", "backgroundNeedUpdate", "", "bottomViewNeedUpdate", "getBottomView", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "context", "Landroid/content/Context;", "getChartHeight", "", "getTipLayContent", "Landroid/view/View;", "getTipViewHolder", "getTopView", "hideSelectedUi", "", "resetDate", "refreshCalorieType", "calorieType", "refreshChartTipView", "index", "refreshRateType", "rateType", "refreshTypeAndOffset", "refreshPage", "tipViewNeedUpdate", "topViewNeedUpdate", "app_release"}, k = 1, mv = {1, 1, 16})
public interface BaseDetailCallback {
    boolean backgroundNeedUpdate();

    boolean bottomViewNeedUpdate();

    BaseDetailViewHolder getBottomView(Context context);

    int getChartHeight(Context context);

    View getTipLayContent();

    BaseDetailViewHolder getTipViewHolder(Context context);

    BaseDetailViewHolder getTopView(Context context);

    void hideSelectedUi(boolean resetDate);

    void refreshCalorieType(int calorieType);

    boolean refreshChartTipView(int index);

    void refreshRateType(int rateType);

    void refreshTypeAndOffset(boolean refreshPage);

    boolean tipViewNeedUpdate();

    boolean topViewNeedUpdate();

    /* JADX INFO: compiled from: BaseDetailCallback.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static final class DefaultImpls {
        public static int getChartHeight(BaseDetailCallback baseDetailCallback, Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return context.getResources().getDimensionPixelSize(R.dimen.sw_dp_300);
        }

        public static /* synthetic */ void hideSelectedUi$default(BaseDetailCallback baseDetailCallback, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: hideSelectedUi");
            }
            if ((i & 1) != 0) {
                z = true;
            }
            baseDetailCallback.hideSelectedUi(z);
        }
    }
}