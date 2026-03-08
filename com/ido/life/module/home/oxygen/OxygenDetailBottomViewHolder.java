package com.ido.life.module.home.oxygen;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.boat.Xtend.two.R;
import com.ido.life.customview.AmbientVolumeProgressBar;
import com.ido.life.customview.TrainingEffectProgressView;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OxygenDetailBottomViewHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010D\u001a\u00020EH\u0016J\b\u0010F\u001a\u00020EH\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010#\"\u0004\b(\u0010%R\u001a\u0010)\u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010#\"\u0004\b+\u0010%R\u001a\u0010,\u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010#\"\u0004\b.\u0010%R\u001a\u0010/\u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010#\"\u0004\b1\u0010%R\u001a\u00102\u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010#\"\u0004\b4\u0010%R\u001a\u00105\u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010#\"\u0004\b7\u0010%R\u001a\u00108\u001a\u000209X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001a\u0010>\u001a\u000209X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010;\"\u0004\b@\u0010=R\u001a\u0010A\u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010#\"\u0004\bC\u0010%¨\u0006G"}, d2 = {"Lcom/ido/life/module/home/oxygen/OxygenDetailBottomViewHolder;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mClVo2Appraise", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getMClVo2Appraise", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "setMClVo2Appraise", "(Landroidx/constraintlayout/widget/ConstraintLayout;)V", "mClVo2Detail", "Landroid/widget/LinearLayout;", "getMClVo2Detail", "()Landroid/widget/LinearLayout;", "setMClVo2Detail", "(Landroid/widget/LinearLayout;)V", "mClVo2Tip", "getMClVo2Tip", "setMClVo2Tip", "mIvOxygenAbout", "Landroid/widget/ImageView;", "getMIvOxygenAbout", "()Landroid/widget/ImageView;", "setMIvOxygenAbout", "(Landroid/widget/ImageView;)V", "mLlOxygenProgress", "Lcom/ido/life/customview/TrainingEffectProgressView;", "getMLlOxygenProgress", "()Lcom/ido/life/customview/TrainingEffectProgressView;", "setMLlOxygenProgress", "(Lcom/ido/life/customview/TrainingEffectProgressView;)V", "mRtvAbout", "Landroid/widget/TextView;", "getMRtvAbout", "()Landroid/widget/TextView;", "setMRtvAbout", "(Landroid/widget/TextView;)V", "mRtvAppraise", "getMRtvAppraise", "setMRtvAppraise", "mRtvOxygenFine", "getMRtvOxygenFine", "setMRtvOxygenFine", "mRtvOxygenPoor", "getMRtvOxygenPoor", "setMRtvOxygenPoor", "mRtvOxygenScore", "getMRtvOxygenScore", "setMRtvOxygenScore", "mRtvOxygenTip", "getMRtvOxygenTip", "setMRtvOxygenTip", "mRtvUnitFine", "getMRtvUnitFine", "setMRtvUnitFine", "mRtvUnitFineDate", "Lcom/ido/life/customview/AmbientVolumeProgressBar;", "getMRtvUnitFineDate", "()Lcom/ido/life/customview/AmbientVolumeProgressBar;", "setMRtvUnitFineDate", "(Lcom/ido/life/customview/AmbientVolumeProgressBar;)V", "mRtvUnitPoorDate", "getMRtvUnitPoorDate", "setMRtvUnitPoorDate", "mRtvZj", "getMRtvZj", "setMRtvZj", "refreshLanguage", "", "setDefaultValue", "app_release"}, k = 1, mv = {1, 1, 16})
public final class OxygenDetailBottomViewHolder extends BaseDetailViewHolder {
    private ConstraintLayout mClVo2Appraise;
    private LinearLayout mClVo2Detail;
    private ConstraintLayout mClVo2Tip;
    private ImageView mIvOxygenAbout;
    private TrainingEffectProgressView mLlOxygenProgress;
    private TextView mRtvAbout;
    private TextView mRtvAppraise;
    private TextView mRtvOxygenFine;
    private TextView mRtvOxygenPoor;
    private TextView mRtvOxygenScore;
    private TextView mRtvOxygenTip;
    private TextView mRtvUnitFine;
    private AmbientVolumeProgressBar mRtvUnitFineDate;
    private AmbientVolumeProgressBar mRtvUnitPoorDate;
    private TextView mRtvZj;

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OxygenDetailBottomViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        View viewFindViewById = itemView.findViewById(R.id.ll_oxygen);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "itemView.findViewById(R.id.ll_oxygen)");
        this.mClVo2Detail = (LinearLayout) viewFindViewById;
        View viewFindViewById2 = itemView.findViewById(R.id.cl_vo2_appraise);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "itemView.findViewById(R.id.cl_vo2_appraise)");
        this.mClVo2Appraise = (ConstraintLayout) viewFindViewById2;
        View viewFindViewById3 = itemView.findViewById(R.id.cl_vo2_tip);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "itemView.findViewById(R.id.cl_vo2_tip)");
        this.mClVo2Tip = (ConstraintLayout) viewFindViewById3;
        View viewFindViewById4 = itemView.findViewById(R.id.rtv_zj);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "itemView.findViewById(R.id.rtv_zj)");
        this.mRtvZj = (TextView) viewFindViewById4;
        View viewFindViewById5 = itemView.findViewById(R.id.rtv_about);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "itemView.findViewById(R.id.rtv_about)");
        this.mRtvAbout = (TextView) viewFindViewById5;
        View viewFindViewById6 = itemView.findViewById(R.id.rtv_Oxygen_score);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById6, "itemView.findViewById(R.id.rtv_Oxygen_score)");
        this.mRtvOxygenScore = (TextView) viewFindViewById6;
        View viewFindViewById7 = itemView.findViewById(R.id.iv_oxygen_about);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById7, "itemView.findViewById(R.id.iv_oxygen_about)");
        this.mIvOxygenAbout = (ImageView) viewFindViewById7;
        View viewFindViewById8 = itemView.findViewById(R.id.rtv_Oxygen_tip);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById8, "itemView.findViewById(R.id.rtv_Oxygen_tip)");
        this.mRtvOxygenTip = (TextView) viewFindViewById8;
        View viewFindViewById9 = itemView.findViewById(R.id.rtv_appraise);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById9, "itemView.findViewById(R.id.rtv_appraise)");
        this.mRtvAppraise = (TextView) viewFindViewById9;
        View viewFindViewById10 = itemView.findViewById(R.id.rtv_Oxygen_fine);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById10, "itemView.findViewById(R.id.rtv_Oxygen_fine)");
        this.mRtvOxygenFine = (TextView) viewFindViewById10;
        View viewFindViewById11 = itemView.findViewById(R.id.rtv_unit_fine);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById11, "itemView.findViewById(R.id.rtv_unit_fine)");
        this.mRtvUnitFine = (TextView) viewFindViewById11;
        View viewFindViewById12 = itemView.findViewById(R.id.rtv_unit_fine_date);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById12, "itemView.findViewById(R.id.rtv_unit_fine_date)");
        this.mRtvUnitFineDate = (AmbientVolumeProgressBar) viewFindViewById12;
        View viewFindViewById13 = itemView.findViewById(R.id.ll_Oxygen_progress);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById13, "itemView.findViewById(R.id.ll_Oxygen_progress)");
        this.mLlOxygenProgress = (TrainingEffectProgressView) viewFindViewById13;
        View viewFindViewById14 = itemView.findViewById(R.id.rtv_Oxygen_poor);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById14, "itemView.findViewById(R.id.rtv_Oxygen_poor)");
        this.mRtvOxygenPoor = (TextView) viewFindViewById14;
        View viewFindViewById15 = itemView.findViewById(R.id.rtv_unit_poor_date);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById15, "itemView.findViewById(R.id.rtv_unit_poor_date)");
        this.mRtvUnitPoorDate = (AmbientVolumeProgressBar) viewFindViewById15;
        setDefaultValue();
        refreshLanguage();
    }

    public final LinearLayout getMClVo2Detail() {
        return this.mClVo2Detail;
    }

    public final void setMClVo2Detail(LinearLayout linearLayout) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "<set-?>");
        this.mClVo2Detail = linearLayout;
    }

    public final ConstraintLayout getMClVo2Appraise() {
        return this.mClVo2Appraise;
    }

    public final void setMClVo2Appraise(ConstraintLayout constraintLayout) {
        Intrinsics.checkParameterIsNotNull(constraintLayout, "<set-?>");
        this.mClVo2Appraise = constraintLayout;
    }

    public final ConstraintLayout getMClVo2Tip() {
        return this.mClVo2Tip;
    }

    public final void setMClVo2Tip(ConstraintLayout constraintLayout) {
        Intrinsics.checkParameterIsNotNull(constraintLayout, "<set-?>");
        this.mClVo2Tip = constraintLayout;
    }

    public final TextView getMRtvZj() {
        return this.mRtvZj;
    }

    public final void setMRtvZj(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mRtvZj = textView;
    }

    public final TextView getMRtvAbout() {
        return this.mRtvAbout;
    }

    public final void setMRtvAbout(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mRtvAbout = textView;
    }

    public final TextView getMRtvOxygenScore() {
        return this.mRtvOxygenScore;
    }

    public final void setMRtvOxygenScore(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mRtvOxygenScore = textView;
    }

    public final ImageView getMIvOxygenAbout() {
        return this.mIvOxygenAbout;
    }

    public final void setMIvOxygenAbout(ImageView imageView) {
        Intrinsics.checkParameterIsNotNull(imageView, "<set-?>");
        this.mIvOxygenAbout = imageView;
    }

    public final TextView getMRtvOxygenTip() {
        return this.mRtvOxygenTip;
    }

    public final void setMRtvOxygenTip(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mRtvOxygenTip = textView;
    }

    public final TextView getMRtvAppraise() {
        return this.mRtvAppraise;
    }

    public final void setMRtvAppraise(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mRtvAppraise = textView;
    }

    public final TextView getMRtvOxygenFine() {
        return this.mRtvOxygenFine;
    }

    public final void setMRtvOxygenFine(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mRtvOxygenFine = textView;
    }

    public final TextView getMRtvUnitFine() {
        return this.mRtvUnitFine;
    }

    public final void setMRtvUnitFine(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mRtvUnitFine = textView;
    }

    public final AmbientVolumeProgressBar getMRtvUnitFineDate() {
        return this.mRtvUnitFineDate;
    }

    public final void setMRtvUnitFineDate(AmbientVolumeProgressBar ambientVolumeProgressBar) {
        Intrinsics.checkParameterIsNotNull(ambientVolumeProgressBar, "<set-?>");
        this.mRtvUnitFineDate = ambientVolumeProgressBar;
    }

    public final TrainingEffectProgressView getMLlOxygenProgress() {
        return this.mLlOxygenProgress;
    }

    public final void setMLlOxygenProgress(TrainingEffectProgressView trainingEffectProgressView) {
        Intrinsics.checkParameterIsNotNull(trainingEffectProgressView, "<set-?>");
        this.mLlOxygenProgress = trainingEffectProgressView;
    }

    public final TextView getMRtvOxygenPoor() {
        return this.mRtvOxygenPoor;
    }

    public final void setMRtvOxygenPoor(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mRtvOxygenPoor = textView;
    }

    public final AmbientVolumeProgressBar getMRtvUnitPoorDate() {
        return this.mRtvUnitPoorDate;
    }

    public final void setMRtvUnitPoorDate(AmbientVolumeProgressBar ambientVolumeProgressBar) {
        Intrinsics.checkParameterIsNotNull(ambientVolumeProgressBar, "<set-?>");
        this.mRtvUnitPoorDate = ambientVolumeProgressBar;
    }
}