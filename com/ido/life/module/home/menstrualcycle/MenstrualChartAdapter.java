package com.ido.life.module.home.menstrualcycle;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.life.customview.MenstruationCalendar;
import com.ido.life.module.home.menstrualcycle.dialog.MenstrulationSettingPresenter;
import com.ido.life.util.RunTimeUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MenstrualChartAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001#B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001aH\u0016J\u0018\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001aH\u0016R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/MenstrualChartAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/module/home/menstrualcycle/MenstrualChartAdapter$MenstrualChartViewHolder;", "context", "Landroid/content/Context;", "dataList", "", "Lcom/ido/life/module/home/menstrualcycle/dialog/MenstrulationSettingPresenter$SettingBean;", "callBack", "Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailCallback;", "(Landroid/content/Context;Ljava/util/List;Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailCallback;)V", "getCallBack", "()Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailCallback;", "setCallBack", "(Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailCallback;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getDataList", "()Ljava/util/List;", "setDataList", "(Ljava/util/List;)V", "mLayoutInflater", "Landroid/view/LayoutInflater;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "MenstrualChartViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MenstrualChartAdapter extends RecyclerView.Adapter<MenstrualChartViewHolder> {
    private MenstrualCycleDetailCallback callBack;
    private Context context;
    private List<MenstrulationSettingPresenter.SettingBean> dataList;
    private LayoutInflater mLayoutInflater;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    public final List<MenstrulationSettingPresenter.SettingBean> getDataList() {
        return this.dataList;
    }

    public final void setDataList(List<MenstrulationSettingPresenter.SettingBean> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.dataList = list;
    }

    public final MenstrualCycleDetailCallback getCallBack() {
        return this.callBack;
    }

    public final void setCallBack(MenstrualCycleDetailCallback menstrualCycleDetailCallback) {
        Intrinsics.checkParameterIsNotNull(menstrualCycleDetailCallback, "<set-?>");
        this.callBack = menstrualCycleDetailCallback;
    }

    public MenstrualChartAdapter(Context context, List<MenstrulationSettingPresenter.SettingBean> dataList, MenstrualCycleDetailCallback callBack) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(dataList, "dataList");
        Intrinsics.checkParameterIsNotNull(callBack, "callBack");
        this.context = context;
        this.dataList = dataList;
        this.callBack = callBack;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.context);
        Intrinsics.checkExpressionValueIsNotNull(layoutInflaterFrom, "LayoutInflater.from(context)");
        this.mLayoutInflater = layoutInflaterFrom;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MenstrualChartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View viewInflate = this.mLayoutInflater.inflate(R.layout.item_menstrulation_chart_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "mLayoutInflater.inflate(…           null\n        )");
        return new MenstrualChartViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MenstrualChartViewHolder holder, int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        MenstrulationSettingPresenter.SettingBean settingBean = this.dataList.get(position);
        MenstruationCalendar mChartView = holder.getMChartView();
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        mChartView.setWeekStart(runTimeUtil.getWeekStart());
        holder.getMChartView().setYear(settingBean.getYear());
        holder.getMChartView().setMonth(settingBean.getMonth());
        holder.getMChartView().setDateList(this.callBack.getMenstruationDateList(settingBean.getYear(), settingBean.getMonth()));
        holder.getMChartView().refreshCalendar(true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.dataList.size();
    }

    /* JADX INFO: compiled from: MenstrualChartAdapter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/MenstrualChartAdapter$MenstrualChartViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mChartView", "Lcom/ido/life/customview/MenstruationCalendar;", "getMChartView", "()Lcom/ido/life/customview/MenstruationCalendar;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class MenstrualChartViewHolder extends RecyclerView.ViewHolder {
        private final MenstruationCalendar mChartView;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MenstrualChartViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            View viewFindViewById = itemView.findViewById(R.id.menstrulation_chart);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "itemView.findViewById(R.id.menstrulation_chart)");
            this.mChartView = (MenstruationCalendar) viewFindViewById;
            Resources resources = itemView.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "itemView.resources");
            itemView.setMinimumWidth(resources.getDisplayMetrics().widthPixels - (itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_16) * 2));
            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            if (layoutParams != null) {
                itemView.setLayoutParams(layoutParams);
                layoutParams.width = itemView.getMinimumWidth();
            }
        }

        public final MenstruationCalendar getMChartView() {
            return this.mChartView;
        }
    }
}