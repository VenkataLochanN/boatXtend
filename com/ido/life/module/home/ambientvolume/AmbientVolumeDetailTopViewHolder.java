package com.ido.life.module.home.ambientvolume;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AmbientVolumeDetailTopViewHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,J\b\u0010.\u001a\u00020/H\u0016J\b\u00100\u001a\u00020/H\u0016J\b\u00101\u001a\u00020/H\u0016J\u0012\u00102\u001a\u00020/2\b\u00103\u001a\u0004\u0018\u000104H\u0016J\b\u00105\u001a\u00020/H\u0016J\u0006\u00106\u001a\u00020/J\u0006\u00107\u001a\u00020/J\b\u00108\u001a\u00020/H\u0016J\u0010\u00109\u001a\u00020/2\u0006\u0010:\u001a\u00020;H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\u001a\u0010\u001d\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\u001a\u0010 \u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0017\"\u0004\b\"\u0010\u0019R\u001a\u0010#\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0017\"\u0004\b%\u0010\u0019R\u001a\u0010&\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0017\"\u0004\b(\u0010\u0019¨\u0006<"}, d2 = {"Lcom/ido/life/module/home/ambientvolume/AmbientVolumeDetailTopViewHolder;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailTopViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mImgVolumeHelp", "Landroid/widget/ImageView;", "getMImgVolumeHelp", "()Landroid/widget/ImageView;", "setMImgVolumeHelp", "(Landroid/widget/ImageView;)V", "mImgVolumeState", "getMImgVolumeState", "setMImgVolumeState", "mLayContent", "Landroid/widget/LinearLayout;", "getMLayContent", "()Landroid/widget/LinearLayout;", "setMLayContent", "(Landroid/widget/LinearLayout;)V", "mTvAvgTitle", "Landroid/widget/TextView;", "getMTvAvgTitle", "()Landroid/widget/TextView;", "setMTvAvgTitle", "(Landroid/widget/TextView;)V", "mTvDate", "getMTvDate", "setMTvDate", "mTvVolumeState", "getMTvVolumeState", "setMTvVolumeState", "mTvVolumeTitle", "getMTvVolumeTitle", "setMTvVolumeTitle", "mTvVolumeUnit", "getMTvVolumeUnit", "setMTvVolumeUnit", "mTvVolumeValue", "getMTvVolumeValue", "setMTvVolumeValue", "getVolumeUnitSpannable", "Landroid/text/Spannable;", "hour", "", "min", "hideRightLayout", "", "refreshLanguage", "setDefaultValue", "showLoadFailedView", "clickListener", "Landroid/view/View$OnClickListener;", "showLoadingView", "showNoDataUI", "showNormalUI", "showRightLayout", "showSuccessView", "showRight", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final class AmbientVolumeDetailTopViewHolder extends BaseDetailTopViewHolder {
    private ImageView mImgVolumeHelp;
    private ImageView mImgVolumeState;
    private LinearLayout mLayContent;
    private TextView mTvAvgTitle;
    private TextView mTvDate;
    private TextView mTvVolumeState;
    private TextView mTvVolumeTitle;
    private TextView mTvVolumeUnit;
    private TextView mTvVolumeValue;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AmbientVolumeDetailTopViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        View viewFindViewById = itemView.findViewById(R.id.lay_content);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "itemView.findViewById(R.id.lay_content)");
        this.mLayContent = (LinearLayout) viewFindViewById;
        View viewFindViewById2 = itemView.findViewById(R.id.tv_volume_title);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "itemView.findViewById(R.id.tv_volume_title)");
        this.mTvVolumeTitle = (TextView) viewFindViewById2;
        View viewFindViewById3 = itemView.findViewById(R.id.img_volume_state);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "itemView.findViewById(R.id.img_volume_state)");
        this.mImgVolumeState = (ImageView) viewFindViewById3;
        View viewFindViewById4 = itemView.findViewById(R.id.tv_volume_state);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "itemView.findViewById(R.id.tv_volume_state)");
        this.mTvVolumeState = (TextView) viewFindViewById4;
        View viewFindViewById5 = itemView.findViewById(R.id.tv_date);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "itemView.findViewById(R.id.tv_date)");
        this.mTvDate = (TextView) viewFindViewById5;
        View viewFindViewById6 = itemView.findViewById(R.id.tv_avg_title);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById6, "itemView.findViewById(R.id.tv_avg_title)");
        this.mTvAvgTitle = (TextView) viewFindViewById6;
        View viewFindViewById7 = itemView.findViewById(R.id.tv_volume_value);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById7, "itemView.findViewById(R.id.tv_volume_value)");
        this.mTvVolumeValue = (TextView) viewFindViewById7;
        View viewFindViewById8 = itemView.findViewById(R.id.tv_volume_unit);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById8, "itemView.findViewById(R.id.tv_volume_unit)");
        this.mTvVolumeUnit = (TextView) viewFindViewById8;
        View viewFindViewById9 = itemView.findViewById(R.id.img_volume_help);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById9, "itemView.findViewById(R.id.img_volume_help)");
        this.mImgVolumeHelp = (ImageView) viewFindViewById9;
        setDefaultValue();
        refreshLanguage();
        refreshLanguage();
        setDefaultValue();
    }

    public final LinearLayout getMLayContent() {
        return this.mLayContent;
    }

    public final void setMLayContent(LinearLayout linearLayout) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "<set-?>");
        this.mLayContent = linearLayout;
    }

    public final TextView getMTvVolumeTitle() {
        return this.mTvVolumeTitle;
    }

    public final void setMTvVolumeTitle(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvVolumeTitle = textView;
    }

    public final ImageView getMImgVolumeState() {
        return this.mImgVolumeState;
    }

    public final void setMImgVolumeState(ImageView imageView) {
        Intrinsics.checkParameterIsNotNull(imageView, "<set-?>");
        this.mImgVolumeState = imageView;
    }

    public final TextView getMTvVolumeState() {
        return this.mTvVolumeState;
    }

    public final void setMTvVolumeState(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvVolumeState = textView;
    }

    public final TextView getMTvDate() {
        return this.mTvDate;
    }

    public final void setMTvDate(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvDate = textView;
    }

    public final TextView getMTvAvgTitle() {
        return this.mTvAvgTitle;
    }

    public final void setMTvAvgTitle(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvAvgTitle = textView;
    }

    public final TextView getMTvVolumeValue() {
        return this.mTvVolumeValue;
    }

    public final void setMTvVolumeValue(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvVolumeValue = textView;
    }

    public final TextView getMTvVolumeUnit() {
        return this.mTvVolumeUnit;
    }

    public final void setMTvVolumeUnit(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvVolumeUnit = textView;
    }

    public final ImageView getMImgVolumeHelp() {
        return this.mImgVolumeHelp;
    }

    public final void setMImgVolumeHelp(ImageView imageView) {
        Intrinsics.checkParameterIsNotNull(imageView, "<set-?>");
        this.mImgVolumeHelp = imageView;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        this.mTvVolumeTitle.setText(LanguageUtil.getLanguageText(R.string.exposure));
        this.mTvAvgTitle.setText(LanguageUtil.getLanguageText(R.string.home_detail_ave_ios));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
        this.mImgVolumeHelp.setImageResource(R.mipmap.volume_icon_info);
        this.mTvDate.setText("--");
        this.mTvVolumeValue.setText("--");
        this.mTvVolumeUnit.setText(getVolumeUnitSpannable(0, 0));
        this.mImgVolumeState.setImageResource(R.mipmap.volume_state_normal);
        this.mTvVolumeState.setText(LanguageUtil.getLanguageText(R.string.home_pressure_normal));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showSuccessView(boolean showRight) {
        stopAnimator();
        this.mLayContent.setVisibility(0);
        LinearLayout mLayLoading = this.mLayLoading;
        Intrinsics.checkExpressionValueIsNotNull(mLayLoading, "mLayLoading");
        mLayLoading.setVisibility(8);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showLoadFailedView(View.OnClickListener clickListener) {
        stopAnimator();
        this.mLayContent.setVisibility(8);
        LinearLayout mLayLoading = this.mLayLoading;
        Intrinsics.checkExpressionValueIsNotNull(mLayLoading, "mLayLoading");
        mLayLoading.setVisibility(0);
        ImageView mImgLoading = this.mImgLoading;
        Intrinsics.checkExpressionValueIsNotNull(mImgLoading, "mImgLoading");
        mImgLoading.setVisibility(8);
        ImageView mImgLoadFailed = this.mImgLoadFailed;
        Intrinsics.checkExpressionValueIsNotNull(mImgLoadFailed, "mImgLoadFailed");
        mImgLoadFailed.setVisibility(0);
        TextView mTvLoadingState = this.mTvLoadingState;
        Intrinsics.checkExpressionValueIsNotNull(mTvLoadingState, "mTvLoadingState");
        mTvLoadingState.setText(LanguageUtil.getLanguageText(R.string.chart_detail_data_load_failed));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showLoadingView() {
        stopAnimator();
        this.mLayContent.setVisibility(8);
        LinearLayout mLayLoading = this.mLayLoading;
        Intrinsics.checkExpressionValueIsNotNull(mLayLoading, "mLayLoading");
        mLayLoading.setVisibility(0);
        ImageView mImgLoading = this.mImgLoading;
        Intrinsics.checkExpressionValueIsNotNull(mImgLoading, "mImgLoading");
        mImgLoading.setVisibility(0);
        ImageView mImgLoadFailed = this.mImgLoadFailed;
        Intrinsics.checkExpressionValueIsNotNull(mImgLoadFailed, "mImgLoadFailed");
        mImgLoadFailed.setVisibility(8);
        TextView mTvLoadingState = this.mTvLoadingState;
        Intrinsics.checkExpressionValueIsNotNull(mTvLoadingState, "mTvLoadingState");
        mTvLoadingState.setText(LanguageUtil.getLanguageText(R.string.chart_detail_data_loading));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showRightLayout() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void hideRightLayout() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    public final void showNormalUI() {
        this.mImgVolumeState.setVisibility(0);
        this.mTvAvgTitle.setVisibility(0);
        this.mTvVolumeValue.setVisibility(0);
        this.mTvVolumeUnit.setVisibility(0);
    }

    public final void showNoDataUI() {
        this.mImgVolumeState.setVisibility(8);
        this.mTvAvgTitle.setVisibility(8);
        this.mTvVolumeValue.setVisibility(8);
        this.mTvVolumeUnit.setVisibility(8);
        this.mTvVolumeState.setText(LanguageUtil.getLanguageText(R.string.no_data));
    }

    public final Spannable getVolumeUnitSpannable(int hour, int min) {
        String str = LanguageUtil.getLanguageText(R.string.public_volume_unit) + "(";
        String str2 = String.valueOf(hour) + LanguageUtil.getLanguageText(R.string.public_time_hour) + min + LanguageUtil.getLanguageText(R.string.public_time_minute);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str + str2 + ")");
        View itemView = getItemView();
        Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
        spannableStringBuilder.setSpan(new TextAppearanceSpan(itemView.getContext(), R.style.style_volume_unit_value), str.length(), str.length() + str2.length(), 17);
        return spannableStringBuilder;
    }
}