package com.ido.life.module.home.weight;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSeekBar;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.google.android.material.timepicker.TimeModel;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.bean.GoalLineInfo;
import com.ido.life.customview.charter.CustomChatBar;
import com.ido.life.customview.charter.WeightLineChartBar;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.enums.WeightBmiEnum;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder;
import com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback;
import com.ido.life.module.user.set.target.SettingTargetActivity;
import com.ido.life.module.user.userdata.weight.WeightSettingActivity;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.UnitUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: WeightDetailFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001c\u0018\u0000 @2:\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00012\u00020\u00072\u00020\u0005:\u0001@B\u0005¢\u0006\u0002\u0010\bJ\b\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\rH\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u0006H\u0016J\n\u0010\u001a\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u001fH\u0016J\"\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u00172\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020\rH\u0016J\b\u0010'\u001a\u00020\rH\u0016J\u001a\u0010(\u001a\u00020\r2\u0006\u0010)\u001a\u00020\u001f2\b\u0010*\u001a\u0004\u0018\u00010\u0002H\u0016J \u0010+\u001a\u00020\r2\u0006\u0010)\u001a\u00020\u001f2\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u0016J \u0010-\u001a\u00020\r2\u0006\u0010)\u001a\u00020\u001f2\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u0016J \u0010.\u001a\u00020\r2\u0006\u0010)\u001a\u00020\u001f2\u000e\u0010/\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u0016J\b\u00100\u001a\u00020\rH\u0016J\u0012\u00101\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u00102\u001a\u00020\rH\u0014J\u0010\u00103\u001a\u00020\u001f2\u0006\u00104\u001a\u00020\u0017H\u0016J\u0012\u00105\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u00106\u001a\u00020\rH\u0014J\u0010\u00107\u001a\u00020\r2\u0006\u00108\u001a\u00020\u0017H\u0016J\u0010\u00109\u001a\u00020\r2\u0006\u0010:\u001a\u00020\u0017H\u0016J\b\u0010;\u001a\u00020\rH\u0016J\b\u0010<\u001a\u00020\rH\u0016J\b\u0010=\u001a\u00020\rH\u0016J\b\u0010>\u001a\u00020\rH\u0016J\b\u0010?\u001a\u00020\u001fH\u0014R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lcom/ido/life/module/home/weight/WeightDetailFragment;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailCoreFragment;", "Lcom/ido/life/database/model/WeightItemBean;", "", "Lcom/ido/life/bean/BaseCharBean;", "Lcom/ido/life/module/home/weight/IWeightDetailView;", "Lcom/ido/life/module/home/weight/WeightDetailPresenter;", "Landroid/view/View$OnClickListener;", "()V", "mGoalLineList", "", "Lcom/ido/life/bean/GoalLineInfo;", "clearCache", "", "clickAction", "view", "Landroid/view/View;", "dismissLoading", "getBottomView", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "context", "Landroid/content/Context;", "getLayoutResId", "", "getPageType", "getPresenter", "getTipLayContent", "getTipViewHolder", "getTopView", "hideSelectedUi", "resetDate", "", "needEventBus", "onActivityResult", "requestCode", "resultCode", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Landroid/content/Intent;", "onBottomViewRefresh", "onDetailLoadFailed", "onLoadSuccessByDay", "showChartAnimator", "day", "onLoadSuccessByMonth", "month", "onLoadSuccessByWeek", "onLoadSuccessByYear", "year", "onTopViewRefresh", "refreshBottomView", "refreshChart", "refreshChartTipView", "index", "refreshTopView", "refreshUiByDateType", "setXMaxValue", "maxValue", "setXMinValue", "minValue", "showLoadFailedView", "showLoadSuccessView", "showLoading", "showLoadingView", "showTopRightLayout", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WeightDetailFragment extends BaseDetailCoreFragment<WeightItemBean, List<? extends BaseCharBean>, List<? extends BaseCharBean>, List<? extends BaseCharBean>, IWeightDetailView, WeightDetailPresenter> implements View.OnClickListener, IWeightDetailView {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = WeightDetailFragment.class.getSimpleName();
    private HashMap _$_findViewCache;
    private List<GoalLineInfo> mGoalLineList = new ArrayList();

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View viewFindViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void clearCache() {
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_weight_detail_layout;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public int getPageType() {
        return 0;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public boolean needEventBus() {
        return true;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected boolean showTopRightLayout() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ((WeightDetailPresenter) getMPresenter()).getDetailData();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTipViewHolder(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.detail_chart_tip_weight_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…_tip_weight_layout, null)");
        return new WeightDetailTipViewHolder(viewInflate);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTopView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return new WeightDetailTopViewHolder(LayoutInflater.from(context).inflate(R.layout.detail_top_weight_layout, (ViewGroup) null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getBottomView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if ((context instanceof IChartDetailCallback) && ((IChartDetailCallback) context).getDateType(this) == 1) {
            return new WeightDetailBottomViewHolder(LayoutInflater.from(context).inflate(R.layout.detail_bottom_weight_layout, (ViewGroup) null));
        }
        return null;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoading() {
        showLoadingDialog();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void dismissLoading() {
        dismissLoadingDialog();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onDetailLoadFailed() {
        if (((WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
            WeightLineChartBar weightLineChartBar = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar != null && weightLineChartBar.getVisibility() == 0) {
                WeightLineChartBar weightLineChartBar2 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                if (weightLineChartBar2 != null) {
                    weightLineChartBar2.setList((List) null);
                }
                WeightLineChartBar weightLineChartBar3 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                if (weightLineChartBar3 != null) {
                    weightLineChartBar3.setXMinValue(((WeightDetailPresenter) getMPresenter()).getMXMinValue());
                }
                WeightLineChartBar weightLineChartBar4 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                if (weightLineChartBar4 != null) {
                    weightLineChartBar4.setXMaxValue(((WeightDetailPresenter) getMPresenter()).getMXMaxValue());
                }
                WeightLineChartBar weightLineChartBar5 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                if (weightLineChartBar5 != null) {
                    weightLineChartBar5.refreshChart(false);
                }
            }
            refreshBottomView(getMActivity());
            refreshTopView(getMActivity());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByDay(boolean showChartAnimator, WeightItemBean day) {
        TextView textView;
        try {
            IChartDetailCallback mCallBack = getMCallBack();
            BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
            if (topViewHolder instanceof WeightDetailTopViewHolder) {
                refreshTopView(getMActivity());
                refreshBottomView(getMActivity());
                WeightItemBean mWeightDay = ((WeightDetailPresenter) getMPresenter()).getMWeightDay();
                if (mWeightDay != null) {
                    RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
                    UserInfo userInfo = GreenDaoUtil.queryUserInfo(runTimeUtil.getUserId());
                    float fFloatValue = (mWeightDay != null ? Float.valueOf(mWeightDay.getTotalWeight()) : null).floatValue();
                    Intrinsics.checkExpressionValueIsNotNull(userInfo, "userInfo");
                    float fCaluteBmi = WeightBmiEnum.caluteBmi(fFloatValue, userInfo.getHeightCm());
                    TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_bmi_value);
                    if (textView2 != null) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        Object[] objArr = {Float.valueOf(fCaluteBmi)};
                        String str = String.format("%.1f", Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                        textView2.setText(str);
                    }
                    WeightBmiEnum weightEnum = WeightBmiEnum.getWeightEnum(fCaluteBmi);
                    if (weightEnum != null && (textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_bmi_state)) != null) {
                        textView.setText(getLanguageText(weightEnum.getStateResId()));
                    }
                    RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
                    int unitSet = runTimeUtil2.getUnitSet();
                    TextView textView3 = ((WeightDetailTopViewHolder) topViewHolder).mTvDate;
                    Intrinsics.checkExpressionValueIsNotNull(textView3, "topViewHolder.mTvDate");
                    T mPresenter = getMPresenter();
                    if (mPresenter == 0) {
                        Intrinsics.throwNpe();
                    }
                    textView3.setText(((WeightDetailPresenter) mPresenter).getDateText());
                    AppCompatSeekBar appCompatSeekBar = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seekBar_step_number);
                    if (appCompatSeekBar != null) {
                        T mPresenter2 = getMPresenter();
                        if (mPresenter2 == 0) {
                            Intrinsics.throwNpe();
                        }
                        appCompatSeekBar.setMax(((WeightDetailPresenter) mPresenter2).getTargetWeight());
                    }
                    if (unitSet == 1) {
                        MediumTextView mediumTextView = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_weight);
                        if (mediumTextView != null) {
                            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                            Object[] objArr2 = {Float.valueOf(mWeightDay.getTotalWeight())};
                            String str2 = String.format("%.1f", Arrays.copyOf(objArr2, objArr2.length));
                            Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
                            mediumTextView.setText(str2);
                        }
                        AppCompatSeekBar appCompatSeekBar2 = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seekBar_step_number);
                        if (appCompatSeekBar2 != null) {
                            appCompatSeekBar2.setProgress(MathKt.roundToInt(mWeightDay.getTotalWeight()));
                        }
                        String languageText = getLanguageText(R.string.main_weight_unit);
                        RunTimeUtil runTimeUtil3 = RunTimeUtil.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil3, "RunTimeUtil.getInstance()");
                        UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(runTimeUtil3.getUserId());
                        if (userTargetNewQueryUserLatestTarget != null) {
                            int weightInt = userTargetNewQueryUserLatestTarget.getWeightInt();
                            if (userTargetNewQueryUserLatestTarget.getWeightUnit() == 2) {
                                weightInt = MathKt.roundToInt(UnitUtil.getPound2Kg(weightInt));
                            }
                            if (mWeightDay != null && Math.abs(weightInt - mWeightDay.getTotalWeight()) < 0.05f) {
                                TextView textView4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_tip);
                                if (textView4 != null) {
                                    textView4.setText(getLanguageText(R.string.weight_to_target));
                                    return;
                                }
                                return;
                            }
                            if (mWeightDay != null) {
                                float f2 = weightInt;
                                if (f2 < mWeightDay.getTotalWeight()) {
                                    TextView textView5 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_tip);
                                    if (textView5 != null) {
                                        StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                                        String languageText2 = getLanguageText(R.string.weight_greater_target_km);
                                        Object[] objArr3 = {Float.valueOf(mWeightDay.getTotalWeight() - f2), languageText};
                                        String str3 = String.format(languageText2, Arrays.copyOf(objArr3, objArr3.length));
                                        Intrinsics.checkNotNullExpressionValue(str3, "java.lang.String.format(format, *args)");
                                        textView5.setText(str3);
                                        return;
                                    }
                                    return;
                                }
                            }
                            TextView textView6 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_tip);
                            if (textView6 != null) {
                                StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                                String languageText3 = getLanguageText(R.string.weight_lower_target_km);
                                Object[] objArr4 = {Float.valueOf(weightInt - mWeightDay.getTotalWeight()), languageText};
                                String str4 = String.format(languageText3, Arrays.copyOf(objArr4, objArr4.length));
                                Intrinsics.checkNotNullExpressionValue(str4, "java.lang.String.format(format, *args)");
                                textView6.setText(str4);
                                return;
                            }
                            return;
                        }
                        TextView textView7 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_tip);
                        if (textView7 != null) {
                            textView7.setText(getLanguageText(R.string.weight_to_target));
                            return;
                        }
                        return;
                    }
                    int iRoundToInt = MathKt.roundToInt(UnitUtil.getKg2Pound(mWeightDay.getTotalWeight()));
                    MediumTextView mediumTextView2 = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_weight);
                    if (mediumTextView2 != null) {
                        StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
                        Object[] objArr5 = {Integer.valueOf(iRoundToInt)};
                        String str5 = String.format(TimeModel.NUMBER_FORMAT, Arrays.copyOf(objArr5, objArr5.length));
                        Intrinsics.checkNotNullExpressionValue(str5, "java.lang.String.format(format, *args)");
                        mediumTextView2.setText(str5);
                    }
                    AppCompatSeekBar appCompatSeekBar3 = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seekBar_step_number);
                    if (appCompatSeekBar3 != null) {
                        appCompatSeekBar3.setProgress(MathKt.roundToInt(iRoundToInt));
                    }
                    String languageText4 = getLanguageText(R.string.public_unit_pound);
                    RunTimeUtil runTimeUtil4 = RunTimeUtil.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(runTimeUtil4, "RunTimeUtil.getInstance()");
                    UserTargetNew userTargetNewQueryUserLatestTarget2 = GreenDaoUtil.queryUserLatestTarget(runTimeUtil4.getUserId());
                    if (userTargetNewQueryUserLatestTarget2 != null) {
                        int weightInt2 = userTargetNewQueryUserLatestTarget2.getWeightInt();
                        if (userTargetNewQueryUserLatestTarget2.getWeightUnit() == 1) {
                            weightInt2 = MathKt.roundToInt(UnitUtil.getKg2Pound(weightInt2));
                        }
                        int i = weightInt2 - iRoundToInt;
                        if (i == 0) {
                            TextView textView8 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_tip);
                            if (textView8 != null) {
                                textView8.setText(getLanguageText(R.string.weight_to_target));
                                return;
                            }
                            return;
                        }
                        if (weightInt2 < iRoundToInt) {
                            TextView textView9 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_tip);
                            if (textView9 != null) {
                                StringCompanionObject stringCompanionObject6 = StringCompanionObject.INSTANCE;
                                String languageText5 = getLanguageText(R.string.weight_greater_target_em);
                                Object[] objArr6 = {Integer.valueOf(iRoundToInt - weightInt2), languageText4};
                                String str6 = String.format(languageText5, Arrays.copyOf(objArr6, objArr6.length));
                                Intrinsics.checkNotNullExpressionValue(str6, "java.lang.String.format(format, *args)");
                                textView9.setText(str6);
                                return;
                            }
                            return;
                        }
                        TextView textView10 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_tip);
                        if (textView10 != null) {
                            StringCompanionObject stringCompanionObject7 = StringCompanionObject.INSTANCE;
                            String languageText6 = getLanguageText(R.string.weight_lower_target_em);
                            Object[] objArr7 = {Integer.valueOf(i), languageText4};
                            String str7 = String.format(languageText6, Arrays.copyOf(objArr7, objArr7.length));
                            Intrinsics.checkNotNullExpressionValue(str7, "java.lang.String.format(format, *args)");
                            textView10.setText(str7);
                            return;
                        }
                        return;
                    }
                    TextView textView11 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_tip);
                    if (textView11 != null) {
                        textView11.setText(getLanguageText(R.string.weight_to_target));
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByWeek(boolean showChartAnimator, List<? extends BaseCharBean> month) {
        if (((WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null || ((WeightDetailPresenter) getMPresenter()).getMDateType() != 2) {
            return;
        }
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof WeightDetailTopViewHolder) {
            refreshBottomView(getMActivity());
            WeightDetailPresenter weightDetailPresenter = (WeightDetailPresenter) getMPresenter();
            List<BaseCharBean> weekChartList = weightDetailPresenter != null ? weightDetailPresenter.getWeekChartList() : null;
            WeightLineChartBar weightLineChartBar = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar != null) {
                weightLineChartBar.setCircleRadius(getResources().getDimensionPixelSize(R.dimen.sw_dp_3));
            }
            WeightLineChartBar weightLineChartBar2 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar2 != null) {
                weightLineChartBar2.setList(weekChartList);
            }
            WeightLineChartBar weightLineChartBar3 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar3 != null) {
                weightLineChartBar3.setBottomLabelCenter(true);
            }
            WeightLineChartBar weightLineChartBar4 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar4 != null) {
                WeightDetailPresenter weightDetailPresenter2 = (WeightDetailPresenter) getMPresenter();
                weightLineChartBar4.setLabelYLeftList(weightDetailPresenter2 != null ? weightDetailPresenter2.getYLabelList() : null);
            }
            WeightLineChartBar weightLineChartBar5 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar5 != null) {
                if (getMPresenter() == 0) {
                    Intrinsics.throwNpe();
                }
                weightLineChartBar5.setYMaxValue(((WeightDetailPresenter) r0).getMYMax());
            }
            WeightLineChartBar weightLineChartBar6 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar6 != null) {
                if (getMPresenter() == 0) {
                    Intrinsics.throwNpe();
                }
                weightLineChartBar6.setYMinValue(((WeightDetailPresenter) r0).getMYMin());
            }
            WeightLineChartBar weightLineChartBar7 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar7 != null) {
                T mPresenter = getMPresenter();
                if (mPresenter == 0) {
                    Intrinsics.throwNpe();
                }
                weightLineChartBar7.setXMinValue(((WeightDetailPresenter) mPresenter).getMXMinValue());
            }
            WeightLineChartBar weightLineChartBar8 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar8 != null) {
                T mPresenter2 = getMPresenter();
                if (mPresenter2 == 0) {
                    Intrinsics.throwNpe();
                }
                weightLineChartBar8.setXMaxValue(((WeightDetailPresenter) mPresenter2).getMXMaxValue());
            }
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            if (runTimeUtil.getUnitSet() == 1) {
                TextView textView = ((WeightDetailTopViewHolder) topViewHolder).mTvAvg;
                Intrinsics.checkExpressionValueIsNotNull(textView, "topViewHolder.mTvAvg");
                textView.setText(String.format("%.1f", Float.valueOf(((WeightDetailPresenter) getMPresenter()).caluteAvgWeight())));
            } else {
                TextView textView2 = ((WeightDetailTopViewHolder) topViewHolder).mTvAvg;
                Intrinsics.checkExpressionValueIsNotNull(textView2, "topViewHolder.mTvAvg");
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = {Integer.valueOf(MathKt.roundToInt(UnitUtil.getKg2Pound(((WeightDetailPresenter) getMPresenter()).caluteAvgWeight())))};
                String str = String.format(TimeModel.NUMBER_FORMAT, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                textView2.setText(str);
            }
            int targetWeight = ((WeightDetailPresenter) getMPresenter()).getTargetWeight();
            this.mGoalLineList.clear();
            if (targetWeight > 0) {
                this.mGoalLineList.add(new GoalLineInfo(0, targetWeight, targetWeight, String.valueOf(targetWeight)));
            }
            WeightLineChartBar weightLineChartBar9 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar9 != null) {
                weightLineChartBar9.setGoalLineList(this.mGoalLineList);
            }
            WeightLineChartBar weightLineChartBar10 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar10 != null) {
                weightLineChartBar10.refreshChart(true);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByMonth(boolean showChartAnimator, List<? extends BaseCharBean> month) {
        if (((WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null || ((WeightDetailPresenter) getMPresenter()).getMDateType() != 3) {
            return;
        }
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof WeightDetailTopViewHolder) {
            refreshBottomView(getMActivity());
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            List<BaseCharBean> monthChartList = ((WeightDetailPresenter) mPresenter).getMonthChartList();
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            int unitSet = runTimeUtil.getUnitSet();
            WeightLineChartBar weightLineChartBar = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar != null) {
                weightLineChartBar.setCircleRadius(getResources().getDimensionPixelSize(R.dimen.sw_dp_2));
            }
            WeightLineChartBar weightLineChartBar2 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar2 != null) {
                weightLineChartBar2.setList(monthChartList);
            }
            WeightLineChartBar weightLineChartBar3 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar3 != null) {
                weightLineChartBar3.setBottomLabelCenter(false);
            }
            WeightLineChartBar weightLineChartBar4 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar4 != null) {
                T mPresenter2 = getMPresenter();
                if (mPresenter2 == 0) {
                    Intrinsics.throwNpe();
                }
                weightLineChartBar4.setLabelYLeftList(((WeightDetailPresenter) mPresenter2).getYLabelList());
            }
            WeightLineChartBar weightLineChartBar5 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar5 != null) {
                if (getMPresenter() == 0) {
                    Intrinsics.throwNpe();
                }
                weightLineChartBar5.setYMaxValue(((WeightDetailPresenter) r2).getMYMax());
            }
            WeightLineChartBar weightLineChartBar6 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar6 != null) {
                if (getMPresenter() == 0) {
                    Intrinsics.throwNpe();
                }
                weightLineChartBar6.setYMinValue(((WeightDetailPresenter) r2).getMYMin());
            }
            WeightLineChartBar weightLineChartBar7 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar7 != null) {
                T mPresenter3 = getMPresenter();
                if (mPresenter3 == 0) {
                    Intrinsics.throwNpe();
                }
                weightLineChartBar7.setXMinValue(((WeightDetailPresenter) mPresenter3).getMXMinValue());
            }
            WeightLineChartBar weightLineChartBar8 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar8 != null) {
                T mPresenter4 = getMPresenter();
                if (mPresenter4 == 0) {
                    Intrinsics.throwNpe();
                }
                weightLineChartBar8.setXMaxValue(((WeightDetailPresenter) mPresenter4).getMXMaxValue());
            }
            if (unitSet == 1) {
                TextView textView = ((WeightDetailTopViewHolder) topViewHolder).mTvAvg;
                Intrinsics.checkExpressionValueIsNotNull(textView, "topViewHolder.mTvAvg");
                textView.setText(String.format("%.1f", Float.valueOf(((WeightDetailPresenter) getMPresenter()).caluteAvgWeight())));
            } else {
                TextView textView2 = ((WeightDetailTopViewHolder) topViewHolder).mTvAvg;
                Intrinsics.checkExpressionValueIsNotNull(textView2, "topViewHolder.mTvAvg");
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = {Integer.valueOf(MathKt.roundToInt(UnitUtil.getKg2Pound(((WeightDetailPresenter) getMPresenter()).caluteAvgWeight())))};
                String str = String.format(TimeModel.NUMBER_FORMAT, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                textView2.setText(str);
            }
            T mPresenter5 = getMPresenter();
            if (mPresenter5 == 0) {
                Intrinsics.throwNpe();
            }
            int targetWeight = ((WeightDetailPresenter) mPresenter5).getTargetWeight();
            this.mGoalLineList.clear();
            if (targetWeight > 0) {
                this.mGoalLineList.add(new GoalLineInfo(0, targetWeight, targetWeight, String.valueOf(targetWeight)));
            }
            WeightLineChartBar weightLineChartBar9 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar9 != null) {
                weightLineChartBar9.setGoalLineList(this.mGoalLineList);
            }
            WeightLineChartBar weightLineChartBar10 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar10 != null) {
                weightLineChartBar10.refreshChart(true);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByYear(boolean showChartAnimator, List<? extends BaseCharBean> year) {
        if (((WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null || ((WeightDetailPresenter) getMPresenter()).getMDateType() != 4) {
            return;
        }
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof WeightDetailTopViewHolder) {
            refreshBottomView(getMActivity());
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            List<BaseCharBean> yearChartList = ((WeightDetailPresenter) mPresenter).getYearChartList();
            WeightLineChartBar weightLineChartBar = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar != null) {
                weightLineChartBar.setCircleRadius(getResources().getDimensionPixelSize(R.dimen.sw_dp_3));
            }
            WeightLineChartBar weightLineChartBar2 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar2 != null) {
                weightLineChartBar2.setList(yearChartList);
            }
            WeightLineChartBar weightLineChartBar3 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar3 != null) {
                weightLineChartBar3.setBottomLabelCenter(true);
            }
            WeightLineChartBar weightLineChartBar4 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar4 != null) {
                T mPresenter2 = getMPresenter();
                if (mPresenter2 == 0) {
                    Intrinsics.throwNpe();
                }
                weightLineChartBar4.setLabelYLeftList(((WeightDetailPresenter) mPresenter2).getYLabelList());
            }
            WeightLineChartBar weightLineChartBar5 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar5 != null) {
                if (getMPresenter() == 0) {
                    Intrinsics.throwNpe();
                }
                weightLineChartBar5.setYMaxValue(((WeightDetailPresenter) r1).getMYMax());
            }
            WeightLineChartBar weightLineChartBar6 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar6 != null) {
                if (getMPresenter() == 0) {
                    Intrinsics.throwNpe();
                }
                weightLineChartBar6.setYMinValue(((WeightDetailPresenter) r1).getMYMin());
            }
            WeightLineChartBar weightLineChartBar7 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar7 != null) {
                T mPresenter3 = getMPresenter();
                if (mPresenter3 == 0) {
                    Intrinsics.throwNpe();
                }
                weightLineChartBar7.setXMinValue(((WeightDetailPresenter) mPresenter3).getMXMinValue());
            }
            WeightLineChartBar weightLineChartBar8 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar8 != null) {
                T mPresenter4 = getMPresenter();
                if (mPresenter4 == 0) {
                    Intrinsics.throwNpe();
                }
                weightLineChartBar8.setXMaxValue(((WeightDetailPresenter) mPresenter4).getMXMaxValue());
            }
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            if (runTimeUtil.getUnitSet() == 1) {
                TextView textView = ((WeightDetailTopViewHolder) topViewHolder).mTvAvg;
                Intrinsics.checkExpressionValueIsNotNull(textView, "topViewHolder.mTvAvg");
                Object[] objArr = {Float.valueOf(((WeightDetailPresenter) getMPresenter()).caluteAvgWeight())};
                String str = String.format("%.1f", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                textView.setText(str);
            } else {
                TextView textView2 = ((WeightDetailTopViewHolder) topViewHolder).mTvAvg;
                Intrinsics.checkExpressionValueIsNotNull(textView2, "topViewHolder.mTvAvg");
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr2 = {Integer.valueOf(MathKt.roundToInt(UnitUtil.getKg2Pound(((WeightDetailPresenter) getMPresenter()).caluteAvgWeight())))};
                String str2 = String.format(TimeModel.NUMBER_FORMAT, Arrays.copyOf(objArr2, objArr2.length));
                Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
                textView2.setText(str2);
            }
            T mPresenter5 = getMPresenter();
            if (mPresenter5 == 0) {
                Intrinsics.throwNpe();
            }
            int targetWeight = ((WeightDetailPresenter) mPresenter5).getTargetWeight();
            this.mGoalLineList.clear();
            if (targetWeight > 0) {
                this.mGoalLineList.add(new GoalLineInfo(0, targetWeight, targetWeight, String.valueOf(targetWeight)));
            }
            WeightLineChartBar weightLineChartBar9 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar9 != null) {
                weightLineChartBar9.setGoalLineList(this.mGoalLineList);
            }
            WeightLineChartBar weightLineChartBar10 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar10 != null) {
                weightLineChartBar10.refreshChart(true);
            }
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void setXMaxValue(int maxValue) {
        CommonLogUtil.d(getTAG(), "setXMaxValue=" + maxValue);
        WeightLineChartBar weightLineChartBar = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (weightLineChartBar != null) {
            weightLineChartBar.setXMaxValue(maxValue);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void setXMinValue(int minValue) {
        CommonLogUtil.d(getTAG(), "setXMinValue=" + minValue);
        WeightLineChartBar weightLineChartBar = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (weightLineChartBar != null) {
            weightLineChartBar.setXMinValue(minValue);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onTopViewRefresh() {
        refreshTopView(getContext());
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onBottomViewRefresh() {
        refreshBottomView(getContext());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadingView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof WeightDetailTopViewHolder) {
            if (((WeightDetailPresenter) getMPresenter()).getMDateType() == 1) {
                ((WeightDetailTopViewHolder) topViewHolder).showSuccessView(showTopRightLayout());
            } else {
                ((WeightDetailTopViewHolder) topViewHolder).showLoadingView();
            }
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadSuccessView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof WeightDetailTopViewHolder) {
            ((WeightDetailTopViewHolder) topViewHolder).showSuccessView(showTopRightLayout());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadFailedView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof WeightDetailTopViewHolder) {
            if (((WeightDetailPresenter) getMPresenter()).getMDateType() == 1) {
                ((WeightDetailTopViewHolder) topViewHolder).showSuccessView(showTopRightLayout());
            } else {
                ((WeightDetailTopViewHolder) topViewHolder).showLoadFailedView(this);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public void hideSelectedUi(boolean resetDate) {
        WeightLineChartBar weightLineChartBar;
        IChartDetailCallback mCallBack;
        if (resetDate && (mCallBack = getMCallBack()) != null) {
            mCallBack.updateSelectDate(this, ((WeightDetailPresenter) getMPresenter()).getMStartDate());
        }
        if (((WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
            WeightLineChartBar weightLineChartBar2 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar2 == null) {
                Intrinsics.throwNpe();
            }
            if (weightLineChartBar2.getVisibility() != 0 || (weightLineChartBar = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
                return;
            }
            weightLineChartBar.refreshChart(false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected void refreshUiByDateType() {
        WeightLineChartBar weightLineChartBar;
        WeightLineChartBar weightLineChartBar2;
        WeightLineChartBar weightLineChartBar3;
        super.refreshUiByDateType();
        if (getMCallBack() != null) {
            IChartDetailCallback mCallBack = getMCallBack();
            if (mCallBack == null) {
                Intrinsics.throwNpe();
            }
            WeightDetailFragment weightDetailFragment = this;
            if (mCallBack.isShow(weightDetailFragment)) {
                int mDateType = ((WeightDetailPresenter) getMPresenter()).getMDateType();
                if (mDateType == 1) {
                    LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_day);
                    if (linearLayout != null) {
                        linearLayout.setVisibility(0);
                    }
                    WeightLineChartBar weightLineChartBar4 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar4 != null) {
                        weightLineChartBar4.setVisibility(8);
                    }
                    TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_edit_target);
                    if (textView != null) {
                        textView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.home.weight.WeightDetailFragment.refreshUiByDateType.1
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View v) {
                                Intrinsics.checkParameterIsNotNull(v, "v");
                                WeightDetailFragment.this.onClick(v);
                            }
                        });
                    }
                    MediumTextView mediumTextView = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_weight);
                    if (mediumTextView != null) {
                        mediumTextView.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                    }
                    TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_bmi_value);
                    if (textView2 != null) {
                        textView2.setText("");
                    }
                    AppCompatSeekBar appCompatSeekBar = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seekBar_step_number);
                    if (appCompatSeekBar != null) {
                        appCompatSeekBar.setEnabled(false);
                    }
                    TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_bmi_state);
                    if (textView3 != null) {
                        textView3.setText(getLanguageText(WeightBmiEnum.WEIGHT.getStateResId()));
                    }
                } else if (mDateType == 2 || mDateType == 3) {
                    LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_day);
                    if (linearLayout2 != null) {
                        linearLayout2.setVisibility(8);
                    }
                    WeightLineChartBar weightLineChartBar5 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar5 != null) {
                        weightLineChartBar5.setVisibility(0);
                    }
                    WeightLineChartBar weightLineChartBar6 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar6 != null) {
                        WeightDetailPresenter weightDetailPresenter = (WeightDetailPresenter) getMPresenter();
                        weightLineChartBar6.setLabelXList(weightDetailPresenter != null ? weightDetailPresenter.getBottomLabelList() : null);
                    }
                    WeightLineChartBar weightLineChartBar7 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar7 != null) {
                        weightLineChartBar7.clearList();
                    }
                    WeightLineChartBar weightLineChartBar8 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar8 != null) {
                        weightLineChartBar8.setGoalLineList(this.mGoalLineList);
                    }
                    WeightLineChartBar weightLineChartBar9 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar9 != null) {
                        if (getMPresenter() == 0) {
                            Intrinsics.throwNpe();
                        }
                        weightLineChartBar9.setYMinValue(((WeightDetailPresenter) r2).getMYMin());
                    }
                    WeightLineChartBar weightLineChartBar10 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar10 != null) {
                        if (getMPresenter() == 0) {
                            Intrinsics.throwNpe();
                        }
                        weightLineChartBar10.setYMaxValue(((WeightDetailPresenter) r2).getMYMax());
                    }
                    WeightLineChartBar weightLineChartBar11 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar11 != null) {
                        weightLineChartBar11.setLabelYLeftList(((WeightDetailPresenter) getMPresenter()).getYLabelList());
                    }
                    WeightLineChartBar weightLineChartBar12 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar12 != null) {
                        weightLineChartBar12.setBottomTitle(null);
                    }
                    WeightLineChartBar weightLineChartBar13 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar13 != null) {
                        weightLineChartBar13.refreshChart(false);
                    }
                    if ((getMActivity() instanceof CustomChatBar.ChartClickListener) && (weightLineChartBar = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
                        weightLineChartBar.setClickListener((CustomChatBar.ChartClickListener) getMActivity());
                    }
                } else if (mDateType == 4) {
                    LinearLayout linearLayout3 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_day);
                    if (linearLayout3 != null) {
                        linearLayout3.setVisibility(8);
                    }
                    WeightLineChartBar weightLineChartBar14 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar14 != null) {
                        weightLineChartBar14.setVisibility(0);
                    }
                    WeightLineChartBar weightLineChartBar15 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar15 != null) {
                        weightLineChartBar15.setLabelXList(((WeightDetailPresenter) getMPresenter()).getBottomLabelList());
                    }
                    WeightLineChartBar weightLineChartBar16 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar16 != null) {
                        weightLineChartBar16.clearList();
                    }
                    WeightLineChartBar weightLineChartBar17 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar17 != null) {
                        weightLineChartBar17.setGoalLineList(this.mGoalLineList);
                    }
                    WeightLineChartBar weightLineChartBar18 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar18 != null) {
                        weightLineChartBar18.setYMinValue(((WeightDetailPresenter) getMPresenter()).getMYMin());
                    }
                    WeightLineChartBar weightLineChartBar19 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar19 != null) {
                        weightLineChartBar19.setYMaxValue(((WeightDetailPresenter) getMPresenter()).getMYMax());
                    }
                    WeightLineChartBar weightLineChartBar20 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar20 != null) {
                        weightLineChartBar20.setLabelYLeftList(((WeightDetailPresenter) getMPresenter()).getYLabelList());
                    }
                    WeightLineChartBar weightLineChartBar21 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar21 != null) {
                        weightLineChartBar21.setBottomTitle(null);
                    }
                    int iYear = ((WeightDetailPresenter) getMPresenter()).year();
                    if (iYear > 0 && (weightLineChartBar3 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
                        weightLineChartBar3.setBottomTitle(String.valueOf(iYear));
                    }
                    WeightLineChartBar weightLineChartBar22 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (weightLineChartBar22 != null) {
                        weightLineChartBar22.refreshChart(false);
                    }
                    if ((getMActivity() instanceof CustomChatBar.ChartClickListener) && (weightLineChartBar2 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
                        weightLineChartBar2.setClickListener((CustomChatBar.ChartClickListener) getMActivity());
                    }
                }
                WeightDetailPresenter weightDetailPresenter2 = (WeightDetailPresenter) getMPresenter();
                if (weightDetailPresenter2 != null) {
                    weightDetailPresenter2.getDetailData();
                }
                IChartDetailCallback mCallBack2 = getMCallBack();
                if (mCallBack2 != null) {
                    WeightDetailPresenter weightDetailPresenter3 = (WeightDetailPresenter) getMPresenter();
                    mCallBack2.updateSelectDate(weightDetailFragment, weightDetailPresenter3 != null ? weightDetailPresenter3.getMStartDate() : null);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    protected void refreshTopView(Context context) {
        int dataDownloadState;
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof WeightDetailTopViewHolder) {
            if (((WeightDetailPresenter) getMPresenter()).getMDateType() == 1 || !RunTimeUtil.getInstance().hasLogin() || (dataDownloadState = ((WeightDetailPresenter) getMPresenter()).getDataDownloadState()) == 3) {
                ((WeightDetailTopViewHolder) topViewHolder).showSuccessView(showTopRightLayout());
            } else if (dataDownloadState == 4) {
                ((WeightDetailTopViewHolder) topViewHolder).showLoadFailedView(this);
            } else {
                ((WeightDetailTopViewHolder) topViewHolder).showLoadingView();
            }
            if (((WeightDetailPresenter) getMPresenter()).getMDateType() == 1) {
                WeightDetailTopViewHolder weightDetailTopViewHolder = (WeightDetailTopViewHolder) topViewHolder;
                TextView textView = weightDetailTopViewHolder.mTvAvg;
                Intrinsics.checkExpressionValueIsNotNull(textView, "topViewHolder.mTvAvg");
                textView.setVisibility(8);
                TextView textView2 = weightDetailTopViewHolder.mTvUnit;
                Intrinsics.checkExpressionValueIsNotNull(textView2, "topViewHolder.mTvUnit");
                textView2.setVisibility(8);
                TextView textView3 = weightDetailTopViewHolder.mTvTitleAvg;
                Intrinsics.checkExpressionValueIsNotNull(textView3, "topViewHolder.mTvTitleAvg");
                textView3.setVisibility(8);
                TextView textView4 = weightDetailTopViewHolder.mTvDate;
                Intrinsics.checkExpressionValueIsNotNull(textView4, "topViewHolder.mTvDate");
                textView4.setText("");
                return;
            }
            topViewHolder.refreshLanguage();
            WeightDetailTopViewHolder weightDetailTopViewHolder2 = (WeightDetailTopViewHolder) topViewHolder;
            TextView textView5 = weightDetailTopViewHolder2.mTvDate;
            if (textView5 != null) {
                WeightDetailPresenter weightDetailPresenter = (WeightDetailPresenter) getMPresenter();
                textView5.setText(weightDetailPresenter != null ? weightDetailPresenter.getDateText() : null);
            }
            float fCaluteAvgWeight = ((WeightDetailPresenter) getMPresenter()).caluteAvgWeight();
            if (((WeightDetailPresenter) getMPresenter()).getUnitSet() == 1) {
                TextView textView6 = weightDetailTopViewHolder2.mTvAvg;
                Intrinsics.checkExpressionValueIsNotNull(textView6, "topViewHolder.mTvAvg");
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = {Float.valueOf(fCaluteAvgWeight)};
                String str = String.format("%.1f", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                textView6.setText(str);
                return;
            }
            TextView textView7 = weightDetailTopViewHolder2.mTvAvg;
            Intrinsics.checkExpressionValueIsNotNull(textView7, "topViewHolder.mTvAvg");
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Object[] objArr2 = {Integer.valueOf(MathKt.roundToInt(UnitUtil.getKg2Pound(fCaluteAvgWeight)))};
            String str2 = String.format(TimeModel.NUMBER_FORMAT, Arrays.copyOf(objArr2, objArr2.length));
            Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
            textView7.setText(str2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    protected void refreshBottomView(Context context) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof WeightDetailBottomViewHolder) {
            if (((WeightDetailPresenter) getMPresenter()).getMDateType() == 1) {
                WeightDetailPresenter weightDetailPresenter = (WeightDetailPresenter) getMPresenter();
                WeightItemBean todayWeightRecord = weightDetailPresenter != null ? weightDetailPresenter.getTodayWeightRecord() : null;
                if (todayWeightRecord == null) {
                    TextView tvRecordWeightTip = ((WeightDetailBottomViewHolder) bottomViewHolder).getTvRecordWeightTip();
                    Intrinsics.checkExpressionValueIsNotNull(tvRecordWeightTip, "bottomViewHolder.tvRecordWeightTip");
                    tvRecordWeightTip.setText(LanguageUtil.getLanguageText(R.string.has_no_record_weight));
                } else {
                    Calendar calendar = Calendar.getInstance(Locale.CHINA);
                    Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                    calendar.setTimeInMillis(todayWeightRecord.getTimeStamp());
                    TextView tvRecordWeightTip2 = ((WeightDetailBottomViewHolder) bottomViewHolder).getTvRecordWeightTip();
                    Intrinsics.checkExpressionValueIsNotNull(tvRecordWeightTip2, "bottomViewHolder.tvRecordWeightTip");
                    tvRecordWeightTip2.setText(DateUtil.format(calendar.getTime(), "dd/MM/yyyy HH:mm:ss"));
                }
                WeightDetailBottomViewHolder weightDetailBottomViewHolder = (WeightDetailBottomViewHolder) bottomViewHolder;
                weightDetailBottomViewHolder.updateVisiblity(0);
                weightDetailBottomViewHolder.getTvRecordWeight().setOnClickListener(this);
                return;
            }
            ((WeightDetailBottomViewHolder) bottomViewHolder).updateVisiblity(8);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public View getTipLayContent() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack != null ? mCallBack.getTipViewHolder(this) : null;
        if (tipViewHolder instanceof WeightDetailTipViewHolder) {
            return ((WeightDetailTipViewHolder) tipViewHolder).getMLayTipContent();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public boolean refreshChartTipView(int index) {
        super.refreshChartTipView(index);
        if (index < 0 || ((WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return false;
        }
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack != null ? mCallBack.getTipViewHolder(this) : null;
        if (!(tipViewHolder instanceof WeightDetailTipViewHolder)) {
            return false;
        }
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        int unitSet = ((WeightDetailPresenter) mPresenter).getUnitSet();
        WeightLineChartBar weightLineChartBar = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (weightLineChartBar == null) {
            Intrinsics.throwNpe();
        }
        List<T> list = weightLineChartBar.getList();
        if (list == 0) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.ido.life.bean.BaseCharBean>");
        }
        if (list.size() > index) {
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            WeightItemBean weightItemByIndex = ((WeightDetailPresenter) mPresenter2).getWeightItemByIndex(((BaseCharBean) list.get(index)).getIndex());
            WeightDetailTipViewHolder weightDetailTipViewHolder = (WeightDetailTipViewHolder) tipViewHolder;
            TextView mTvTipTitleAvg = weightDetailTipViewHolder.getMTvTipTitleAvg();
            if (mTvTipTitleAvg != null) {
                mTvTipTitleAvg.setText(getLanguageText(R.string.detail_average_daily));
            }
            if (unitSet == 1) {
                TextView mTvTipState = weightDetailTipViewHolder.getMTvTipState();
                if (mTvTipState != null) {
                    mTvTipState.setText(getLanguageText(R.string.weight_kg_unit_short));
                }
            } else {
                TextView mTvTipState2 = weightDetailTipViewHolder.getMTvTipState();
                if (mTvTipState2 != null) {
                    mTvTipState2.setText(getLanguageText(R.string.weight_pound_unit_short));
                }
            }
            TextView mTvTipAvg = weightDetailTipViewHolder.getMTvTipAvg();
            if (mTvTipAvg != null) {
                mTvTipAvg.setText("--");
            }
            TextView mTvTipDate = weightDetailTipViewHolder.getMTvTipDate();
            if (mTvTipDate != null) {
                mTvTipDate.setText("--");
            }
            if (weightItemByIndex != null) {
                IChartDetailCallback mCallBack2 = getMCallBack();
                if (mCallBack2 != null) {
                    mCallBack2.updateSelectDate(this, weightItemByIndex.getDate() + "-01");
                }
                String date = weightItemByIndex.getDate();
                int mDateType = ((WeightDetailPresenter) getMPresenter()).getMDateType();
                if (mDateType == 2 || mDateType == 3) {
                    if (unitSet == 1) {
                        TextView mTvTipAvg2 = weightDetailTipViewHolder.getMTvTipAvg();
                        if (mTvTipAvg2 != null) {
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            Object[] objArr = {Float.valueOf(weightItemByIndex.getTotalWeight())};
                            String str = String.format("%.1f", Arrays.copyOf(objArr, objArr.length));
                            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                            mTvTipAvg2.setText(str);
                        }
                    } else {
                        TextView mTvTipAvg3 = weightDetailTipViewHolder.getMTvTipAvg();
                        if (mTvTipAvg3 != null) {
                            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                            Object[] objArr2 = {Integer.valueOf(MathKt.roundToInt(UnitUtil.getKg2Pound(weightItemByIndex.getTotalWeight())))};
                            String str2 = String.format(TimeModel.NUMBER_FORMAT, Arrays.copyOf(objArr2, objArr2.length));
                            Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
                            mTvTipAvg3.setText(str2);
                        }
                    }
                    TextView mTvTipTitleAvg2 = weightDetailTipViewHolder.getMTvTipTitleAvg();
                    if (mTvTipTitleAvg2 != null) {
                        mTvTipTitleAvg2.setVisibility(8);
                    }
                    Intrinsics.checkExpressionValueIsNotNull(date, "date");
                    String str3 = date;
                    List listSplit$default = StringsKt.split$default((CharSequence) str3, new String[]{"-"}, false, 0, 6, (Object) null);
                    TextView mTvTipDate2 = weightDetailTipViewHolder.getMTvTipDate();
                    if (mTvTipDate2 != null) {
                        if (listSplit$default.size() == 3) {
                            str3 = ((String) listSplit$default.get(2)) + "/" + ((String) listSplit$default.get(1));
                        }
                        mTvTipDate2.setText(str3);
                    }
                } else if (mDateType == 4) {
                    if (weightItemByIndex.getBmi() <= 0) {
                        TextView mTvTipAvg4 = weightDetailTipViewHolder.getMTvTipAvg();
                        if (mTvTipAvg4 != null) {
                            mTvTipAvg4.setText("0.0");
                        }
                    } else if (unitSet == 1) {
                        TextView mTvTipAvg5 = weightDetailTipViewHolder.getMTvTipAvg();
                        if (mTvTipAvg5 != null) {
                            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                            Object[] objArr3 = {Float.valueOf(weightItemByIndex.getTotalWeight() / weightItemByIndex.getBmi())};
                            String str4 = String.format("%.1f", Arrays.copyOf(objArr3, objArr3.length));
                            Intrinsics.checkNotNullExpressionValue(str4, "java.lang.String.format(format, *args)");
                            mTvTipAvg5.setText(str4);
                        }
                    } else {
                        TextView mTvTipAvg6 = weightDetailTipViewHolder.getMTvTipAvg();
                        if (mTvTipAvg6 != null) {
                            StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                            Object[] objArr4 = {Integer.valueOf(MathKt.roundToInt(UnitUtil.getKg2Pound(weightItemByIndex.getTotalWeight() / weightItemByIndex.getBmi())))};
                            String str5 = String.format(TimeModel.NUMBER_FORMAT, Arrays.copyOf(objArr4, objArr4.length));
                            Intrinsics.checkNotNullExpressionValue(str5, "java.lang.String.format(format, *args)");
                            mTvTipAvg6.setText(str5);
                        }
                    }
                    TextView mTvTipTitleAvg3 = weightDetailTipViewHolder.getMTvTipTitleAvg();
                    if (mTvTipTitleAvg3 != null) {
                        mTvTipTitleAvg3.setVisibility(0);
                    }
                    Intrinsics.checkExpressionValueIsNotNull(date, "date");
                    String str6 = date;
                    List listSplit$default2 = StringsKt.split$default((CharSequence) str6, new String[]{"-"}, false, 0, 6, (Object) null);
                    TextView mTvTipDate3 = weightDetailTipViewHolder.getMTvTipDate();
                    if (mTvTipDate3 != null) {
                        if (listSplit$default2.size() >= 2) {
                            str6 = ((String) listSplit$default2.get(1)) + "/" + ((String) listSplit$default2.get(0));
                        }
                        mTvTipDate3.setText(str6);
                    }
                }
            }
        }
        return true;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected void refreshChart() {
        WeightLineChartBar weightLineChartBar;
        super.refreshChart();
        if (((WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
            WeightLineChartBar weightLineChartBar2 = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (weightLineChartBar2 == null) {
                Intrinsics.throwNpe();
            }
            if (weightLineChartBar2.getVisibility() != 0 || (weightLineChartBar = (WeightLineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
                return;
            }
            weightLineChartBar.refreshChart(true);
        }
    }

    /* JADX INFO: compiled from: WeightDetailFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ido/life/module/home/weight/WeightDetailFragment$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getInstance", "Lcom/ido/life/module/home/weight/WeightDetailFragment;", "bundle", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final WeightDetailFragment getInstance(Bundle bundle) {
            WeightDetailFragment weightDetailFragment = new WeightDetailFragment();
            if (bundle != null) {
                weightDetailFragment.setArguments(bundle);
            }
            return weightDetailFragment;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    public void clickAction(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != R.id.lay_loading) {
            if (id == R.id.tv_edit_target) {
                startActivity(new Intent(getMActivity(), (Class<?>) SettingTargetActivity.class));
                return;
            } else {
                if (id != R.id.tv_record_weight) {
                    return;
                }
                startActivity(new Intent(getMActivity(), (Class<?>) WeightSettingActivity.class));
                return;
            }
        }
        if (NetworkUtil.isConnected(getMActivity())) {
            IChartDetailCallback mCallBack = getMCallBack();
            BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
            if (topViewHolder instanceof WeightDetailTopViewHolder) {
                ((WeightDetailTopViewHolder) topViewHolder).showLoadingView();
                ((WeightDetailPresenter) getMPresenter()).requestPullData();
                return;
            }
            return;
        }
        showToast(R.string.public_net_unuse);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public WeightDetailPresenter getPresenter() {
        return new WeightDetailPresenter(this);
    }
}