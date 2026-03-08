package com.ido.life.module.home.ambientvolume;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailComoTipViewHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AmbientVolumeDetailTipViewHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010/\u001a\u00020\u0003H\u0016J\b\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u000201H\u0016J\u0006\u00103\u001a\u000201J\u0006\u00104\u001a\u000201R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001a\u0010\u001a\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\u001a\u0010\u001d\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0014\"\u0004\b\u001f\u0010\u0016R\u001a\u0010 \u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0014\"\u0004\b\"\u0010\u0016R\u001a\u0010#\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0014\"\u0004\b%\u0010\u0016R\u001a\u0010&\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0014\"\u0004\b(\u0010\u0016R\u001a\u0010)\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0014\"\u0004\b+\u0010\u0016R\u001a\u0010,\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0014\"\u0004\b.\u0010\u0016¨\u00065"}, d2 = {"Lcom/ido/life/module/home/ambientvolume/AmbientVolumeDetailTipViewHolder;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailComoTipViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mLayContent", "Landroid/widget/LinearLayout;", "getMLayContent", "()Landroid/widget/LinearLayout;", "setMLayContent", "(Landroid/widget/LinearLayout;)V", "mLayLeft", "getMLayLeft", "setMLayLeft", "mLayRight", "getMLayRight", "setMLayRight", "mTvDate", "Landroid/widget/TextView;", "getMTvDate", "()Landroid/widget/TextView;", "setMTvDate", "(Landroid/widget/TextView;)V", "mTvTitleVolumeArea", "getMTvTitleVolumeArea", "setMTvTitleVolumeArea", "mTvTitleVolumeDuration", "getMTvTitleVolumeDuration", "setMTvTitleVolumeDuration", "mTvVolumeAreaValue", "getMTvVolumeAreaValue", "setMTvVolumeAreaValue", "mTvVolumeDurationHour", "getMTvVolumeDurationHour", "setMTvVolumeDurationHour", "mTvVolumeDurationMin", "getMTvVolumeDurationMin", "setMTvVolumeDurationMin", "mTvVolumeDurationMinUnit", "getMTvVolumeDurationMinUnit", "setMTvVolumeDurationMinUnit", "mTvVolumeHourUnit", "getMTvVolumeHourUnit", "setMTvVolumeHourUnit", "mTvVolumeUnit", "getMTvVolumeUnit", "setMTvVolumeUnit", "getContentView", "refreshLanguage", "", "setDefaultValue", "showBarTipUI", "showLineTipUI", "app_release"}, k = 1, mv = {1, 1, 16})
public final class AmbientVolumeDetailTipViewHolder extends BaseDetailComoTipViewHolder {
    private LinearLayout mLayContent;
    private LinearLayout mLayLeft;
    private LinearLayout mLayRight;
    private TextView mTvDate;
    private TextView mTvTitleVolumeArea;
    private TextView mTvTitleVolumeDuration;
    private TextView mTvVolumeAreaValue;
    private TextView mTvVolumeDurationHour;
    private TextView mTvVolumeDurationMin;
    private TextView mTvVolumeDurationMinUnit;
    private TextView mTvVolumeHourUnit;
    private TextView mTvVolumeUnit;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AmbientVolumeDetailTipViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        View viewFindViewById = itemView.findViewById(R.id.lay_content);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "itemView.findViewById(R.id.lay_content)");
        this.mLayContent = (LinearLayout) viewFindViewById;
        View viewFindViewById2 = itemView.findViewById(R.id.lay_left);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "itemView.findViewById(R.id.lay_left)");
        this.mLayLeft = (LinearLayout) viewFindViewById2;
        View viewFindViewById3 = itemView.findViewById(R.id.tv_title_volume_area);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "itemView.findViewById(R.id.tv_title_volume_area)");
        this.mTvTitleVolumeArea = (TextView) viewFindViewById3;
        View viewFindViewById4 = itemView.findViewById(R.id.tv_volume_area_value);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "itemView.findViewById(R.id.tv_volume_area_value)");
        this.mTvVolumeAreaValue = (TextView) viewFindViewById4;
        View viewFindViewById5 = itemView.findViewById(R.id.tv_volume_unit);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "itemView.findViewById(R.id.tv_volume_unit)");
        this.mTvVolumeUnit = (TextView) viewFindViewById5;
        View viewFindViewById6 = itemView.findViewById(R.id.tv_date);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById6, "itemView.findViewById(R.id.tv_date)");
        this.mTvDate = (TextView) viewFindViewById6;
        View viewFindViewById7 = itemView.findViewById(R.id.lay_right);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById7, "itemView.findViewById(R.id.lay_right)");
        this.mLayRight = (LinearLayout) viewFindViewById7;
        View viewFindViewById8 = itemView.findViewById(R.id.tv_title_volume_duration);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById8, "itemView.findViewById(R.…tv_title_volume_duration)");
        this.mTvTitleVolumeDuration = (TextView) viewFindViewById8;
        View viewFindViewById9 = itemView.findViewById(R.id.tv_volume_duration_hour);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById9, "itemView.findViewById(R.….tv_volume_duration_hour)");
        this.mTvVolumeDurationHour = (TextView) viewFindViewById9;
        View viewFindViewById10 = itemView.findViewById(R.id.tv_volume_hour_unit);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById10, "itemView.findViewById(R.id.tv_volume_hour_unit)");
        this.mTvVolumeHourUnit = (TextView) viewFindViewById10;
        View viewFindViewById11 = itemView.findViewById(R.id.tv_volume_duration_min);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById11, "itemView.findViewById(R.id.tv_volume_duration_min)");
        this.mTvVolumeDurationMin = (TextView) viewFindViewById11;
        View viewFindViewById12 = itemView.findViewById(R.id.tv_volume_min_unit);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById12, "itemView.findViewById(R.id.tv_volume_min_unit)");
        this.mTvVolumeDurationMinUnit = (TextView) viewFindViewById12;
        setDefaultValue();
        refreshLanguage();
    }

    public final LinearLayout getMLayContent() {
        return this.mLayContent;
    }

    public final void setMLayContent(LinearLayout linearLayout) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "<set-?>");
        this.mLayContent = linearLayout;
    }

    public final LinearLayout getMLayLeft() {
        return this.mLayLeft;
    }

    public final void setMLayLeft(LinearLayout linearLayout) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "<set-?>");
        this.mLayLeft = linearLayout;
    }

    public final TextView getMTvTitleVolumeArea() {
        return this.mTvTitleVolumeArea;
    }

    public final void setMTvTitleVolumeArea(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvTitleVolumeArea = textView;
    }

    public final TextView getMTvVolumeAreaValue() {
        return this.mTvVolumeAreaValue;
    }

    public final void setMTvVolumeAreaValue(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvVolumeAreaValue = textView;
    }

    public final TextView getMTvVolumeUnit() {
        return this.mTvVolumeUnit;
    }

    public final void setMTvVolumeUnit(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvVolumeUnit = textView;
    }

    public final TextView getMTvDate() {
        return this.mTvDate;
    }

    public final void setMTvDate(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvDate = textView;
    }

    public final LinearLayout getMLayRight() {
        return this.mLayRight;
    }

    public final void setMLayRight(LinearLayout linearLayout) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "<set-?>");
        this.mLayRight = linearLayout;
    }

    public final TextView getMTvTitleVolumeDuration() {
        return this.mTvTitleVolumeDuration;
    }

    public final void setMTvTitleVolumeDuration(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvTitleVolumeDuration = textView;
    }

    public final TextView getMTvVolumeDurationHour() {
        return this.mTvVolumeDurationHour;
    }

    public final void setMTvVolumeDurationHour(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvVolumeDurationHour = textView;
    }

    public final TextView getMTvVolumeHourUnit() {
        return this.mTvVolumeHourUnit;
    }

    public final void setMTvVolumeHourUnit(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvVolumeHourUnit = textView;
    }

    public final TextView getMTvVolumeDurationMin() {
        return this.mTvVolumeDurationMin;
    }

    public final void setMTvVolumeDurationMin(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvVolumeDurationMin = textView;
    }

    public final TextView getMTvVolumeDurationMinUnit() {
        return this.mTvVolumeDurationMinUnit;
    }

    public final void setMTvVolumeDurationMinUnit(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvVolumeDurationMinUnit = textView;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTipViewHolder
    public View getContentView() {
        return this.mLayContent;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailComoTipViewHolder, com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        this.mTvTitleVolumeArea.setText(LanguageUtil.getLanguageText(R.string.title_area));
        this.mTvVolumeUnit.setText(LanguageUtil.getLanguageText(R.string.public_volume_unit));
        this.mTvTitleVolumeDuration.setText(LanguageUtil.getLanguageText(R.string.title_volume_duration));
        this.mTvVolumeHourUnit.setText(LanguageUtil.getLanguageText(R.string.public_time_hour));
        this.mTvVolumeDurationMinUnit.setText(LanguageUtil.getLanguageText(R.string.public_time_minute));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailComoTipViewHolder, com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
        this.mTvVolumeAreaValue.setText("--");
        this.mTvDate.setText("-");
        this.mTvVolumeDurationHour.setText("-");
        this.mTvVolumeDurationMin.setText("-");
    }

    public final void showBarTipUI() {
        this.mLayLeft.setVisibility(0);
        this.mLayRight.setVisibility(8);
    }

    public final void showLineTipUI() {
        this.mLayLeft.setVisibility(0);
        this.mLayRight.setVisibility(0);
    }
}