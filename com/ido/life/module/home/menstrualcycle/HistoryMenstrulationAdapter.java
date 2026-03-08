package com.ido.life.module.home.menstrualcycle;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.module.home.menstrualcycle.MenstrualCycleDetailPresenter;
import com.ido.life.util.DateUtil;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: HistoryMenstrulationAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0013H\u0016J\u0018\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0013H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/HistoryMenstrulationAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/module/home/menstrualcycle/HistoryMenstrulationViewHolder;", "context", "Landroid/content/Context;", "dataList", "", "Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailPresenter$HistoryMenstruationItemBean;", "(Landroid/content/Context;Ljava/util/List;)V", "getContext", "()Landroid/content/Context;", "getDataList", "()Ljava/util/List;", "mLayoutInflater", "Landroid/view/LayoutInflater;", "kotlin.jvm.PlatformType", "getMLayoutInflater", "()Landroid/view/LayoutInflater;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "app_release"}, k = 1, mv = {1, 1, 16})
public final class HistoryMenstrulationAdapter extends RecyclerView.Adapter<HistoryMenstrulationViewHolder> {
    private final Context context;
    private final List<MenstrualCycleDetailPresenter.HistoryMenstruationItemBean> dataList;
    private final LayoutInflater mLayoutInflater;

    public final Context getContext() {
        return this.context;
    }

    public final List<MenstrualCycleDetailPresenter.HistoryMenstruationItemBean> getDataList() {
        return this.dataList;
    }

    public HistoryMenstrulationAdapter(Context context, List<MenstrualCycleDetailPresenter.HistoryMenstruationItemBean> dataList) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(dataList, "dataList");
        this.context = context;
        this.dataList = dataList;
        this.mLayoutInflater = LayoutInflater.from(this.context);
    }

    public final LayoutInflater getMLayoutInflater() {
        return this.mLayoutInflater;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public HistoryMenstrulationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View viewInflate = this.mLayoutInflater.inflate(R.layout.item_menstrulation_history_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "mLayoutInflater.inflate(…           null\n        )");
        return new HistoryMenstrulationViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(HistoryMenstrulationViewHolder holder, int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        MenstrualCycleDetailPresenter.HistoryMenstruationItemBean historyMenstruationItemBean = this.dataList.get(position);
        List listSplit$default = StringsKt.split$default((CharSequence) historyMenstruationItemBean.getStartDate(), new String[]{"-"}, false, 0, 6, (Object) null);
        List listSplit$default2 = StringsKt.split$default((CharSequence) historyMenstruationItemBean.getEndDate(), new String[]{"-"}, false, 0, 6, (Object) null);
        int i = Integer.parseInt((String) listSplit$default.get(0));
        int i2 = Integer.parseInt((String) listSplit$default.get(1));
        int i3 = Integer.parseInt((String) listSplit$default.get(2));
        int i4 = Integer.parseInt((String) listSplit$default2.get(0));
        Integer.parseInt((String) listSplit$default2.get(1));
        Integer.parseInt((String) listSplit$default2.get(2));
        Calendar.getInstance();
        Calendar startCycleCalendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(startCycleCalendar, "startCycleCalendar");
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append('-');
        sb.append(i2);
        sb.append('-');
        sb.append(i3);
        startCycleCalendar.setTime(DateUtil.string2Date(sb.toString(), DateUtil.DATE_FORMAT_YMD));
        startCycleCalendar.add(5, historyMenstruationItemBean.getMenstruationCycle() - 1);
        int i5 = startCycleCalendar.get(2) + 1;
        int i6 = startCycleCalendar.get(5);
        int i7 = startCycleCalendar.get(1);
        String str = historyMenstruationItemBean.getMenstruationCycle() + LanguageUtil.getLanguageText(R.string.public_unit_day) + (char) 65306 + i2 + IOUtils.DIR_SEPARATOR_UNIX + i3 + '-' + i5 + IOUtils.DIR_SEPARATOR_UNIX + i6;
        if (i != i7 || i4 != i7) {
            TextView mTvYear = holder.getMTvYear();
            Intrinsics.checkExpressionValueIsNotNull(mTvYear, "holder.mTvYear");
            mTvYear.setVisibility(0);
            TextView mTvYear2 = holder.getMTvYear();
            Intrinsics.checkExpressionValueIsNotNull(mTvYear2, "holder.mTvYear");
            mTvYear2.setText(String.valueOf(i));
        } else {
            TextView mTvYear3 = holder.getMTvYear();
            Intrinsics.checkExpressionValueIsNotNull(mTvYear3, "holder.mTvYear");
            mTvYear3.setVisibility(8);
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new TextAppearanceSpan(this.context, R.style.style_mensulation_history_days_value), 0, str.length(), 17);
        TextView mTvDateAreaDesc = holder.getMTvDateAreaDesc();
        Intrinsics.checkExpressionValueIsNotNull(mTvDateAreaDesc, "holder.mTvDateAreaDesc");
        mTvDateAreaDesc.setText(spannableStringBuilder);
        TextView mTvDateDesc = holder.getMTvDateDesc();
        Intrinsics.checkExpressionValueIsNotNull(mTvDateDesc, "holder.mTvDateDesc");
        String languageText = LanguageUtil.getLanguageText(R.string.days_menstrulation);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…tring.days_menstrulation)");
        Object[] objArr = {Integer.valueOf(historyMenstruationItemBean.getMenstruationLength())};
        String str2 = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(this, *args)");
        mTvDateDesc.setText(str2);
        holder.getMProgres().setMaxProgress(historyMenstruationItemBean.getMenstruationCycle());
        holder.getMProgres().setProgress(historyMenstruationItemBean.getMenstruationLength());
        holder.getMProgres().refreshProgress(true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.dataList.size();
    }
}