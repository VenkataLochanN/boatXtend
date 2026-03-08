package com.ido.life.module.home.recentsituation;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.os.BundleKt;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.StatusBarUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.base.BaseClickActivity;
import com.ido.life.bean.GoalLineInfo;
import com.ido.life.bean.GradientBarPoint;
import com.ido.life.constants.Constants;
import com.ido.life.customview.RecentSituationProgressView;
import com.ido.life.customview.charter.CustomChatBar;
import com.ido.life.customview.charter.GoalGradientBarChartBar;
import com.ido.life.customview.charter.RecentSituationWeekChart;
import com.ido.life.dialog.CommonDialog;
import com.ido.life.module.home.chartdetail.vertical.ChartDetailActivity;
import com.ido.life.module.home.recentsituation.targetset.UserSettingTargetFragment;
import com.ido.life.util.DialogHelper;
import com.ido.life.util.RunTimeUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: RecentSituationDetailActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 E2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001EB\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\tH\u0002J\b\u0010\r\u001a\u00020\tH\u0016J\b\u0010\u000e\u001a\u00020\tH\u0002J\b\u0010\u000f\u001a\u00020\u0007H\u0014J\b\u0010\u0010\u001a\u00020\tH\u0014J\b\u0010\u0011\u001a\u00020\tH\u0016J\b\u0010\u0012\u001a\u00020\tH\u0016J\b\u0010\u0013\u001a\u00020\tH\u0016J\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0019\u001a\u00020\tH\u0016J\b\u0010\u001a\u001a\u00020\tH\u0002J\u0018\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0016J\u0010\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\u0007H\u0016J\u0010\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020 H\u0016J\u0018\u0010%\u001a\u00020\t2\u000e\u0010&\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'H\u0016J\u0016\u0010)\u001a\u00020\t2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020 0'H\u0016J\u0018\u0010+\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0016J\u0016\u0010,\u001a\u00020\t2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020 0'H\u0016J\u0018\u0010-\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0016J\u0018\u0010.\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u0007H\u0016J\u0018\u00101\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0016J\u0010\u00102\u001a\u00020\t2\u0006\u00103\u001a\u000204H\u0016J\u0018\u00105\u001a\u00020\t2\u000e\u0010&\u001a\n\u0012\u0004\u0012\u000206\u0018\u00010'H\u0016J\u0012\u00107\u001a\u00020\t2\b\u00108\u001a\u0004\u0018\u00010 H\u0016J\u0010\u00109\u001a\u00020\t2\u0006\u0010:\u001a\u00020\u0007H\u0016J\u0018\u0010;\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0016J\u0010\u0010<\u001a\u00020\t2\u0006\u00103\u001a\u000204H\u0016J\u0018\u0010=\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0016J\u0010\u0010>\u001a\u00020\t2\u0006\u00103\u001a\u000204H\u0016J\u0018\u0010?\u001a\u00020\t2\u000e\u0010&\u001a\n\u0012\u0004\u0012\u000206\u0018\u00010'H\u0016J\u0012\u0010@\u001a\u00020\t2\b\u00108\u001a\u0004\u0018\u00010 H\u0016J\u0018\u0010A\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u0007H\u0016J\u0010\u0010B\u001a\u00020\t2\u0006\u0010C\u001a\u00020\u0007H\u0016J\b\u0010D\u001a\u00020\tH\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/ido/life/module/home/recentsituation/RecentSituationDetailActivity;", "Lcom/ido/life/base/BaseClickActivity;", "Lcom/ido/life/module/home/recentsituation/RecentSituationDetailPresenter;", "Lcom/ido/life/module/home/recentsituation/IRecentSituationDetailView;", "Landroid/view/View$OnClickListener;", "()V", "mPageType", "", "clickAction", "", "view", "Landroid/view/View;", "disableSetTarget", "dismissDialog", "enableSetTarget", "getLayoutResId", "initData", "initViews", "onTargetSettingFailed", "onTargetSettingSuccess", "refreshLineChart", "showAnimator", "", "refreshPastChart", "refreshRecentChart", "showDialog", "switchUIStyleByPageType", "updateBarYmaxmin", "max", "min", "updateCompareStateDesc", "compareDesc", "", "updateCompareStateIcon", "iconResid", "updateCompareStateTitle", CommonDialog.EXTRA_TITLE, "updateLineChartData", "dataList", "", "Lcom/ido/life/customview/charter/RecentSituationWeekChart$ChartBean;", "updateLineXLabel", "labelList", "updateLineXMaxmin", "updateLineYLabel", "updateLineYMaxmin", "updatePassDayCount", "totalCount", "validCount", "updatePastBarXMaxmin", "updatePastChartAvg", "avg", "", "updatePastChartData", "Lcom/ido/life/bean/GradientBarPoint;", "updatePastChartDesc", "desc", "updateProgress", "currProgress", "updateProgressMaxmin", "updateRecentAvg", "updateRecentBarXMaxmin", "updateRecentChartAvg", "updateRecentChartData", "updateRecentChartDesc", "updateRecentDayCount", "updateTarget", "target", "viewCreate", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class RecentSituationDetailActivity extends BaseClickActivity<RecentSituationDetailPresenter> implements IRecentSituationDetailView, View.OnClickListener {
    private HashMap _$_findViewCache;
    private int mPageType = PAGE_ACTIVITY_CALORIE;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int PAGE_ACTIVITY_CALORIE = 1;
    private static final int PAGE_ACTIVITY_TIME = 2;
    private static final int PAGE_WALKING = 3;
    private static final String PAGE_TYPE = ChartDetailActivity.PAGE_TYPE;

    public static final int getPAGE_ACTIVITY_CALORIE() {
        Companion companion = INSTANCE;
        return PAGE_ACTIVITY_CALORIE;
    }

    public static final int getPAGE_ACTIVITY_TIME() {
        Companion companion = INSTANCE;
        return PAGE_ACTIVITY_TIME;
    }

    public static final String getPAGE_TYPE() {
        Companion companion = INSTANCE;
        return PAGE_TYPE;
    }

    public static final int getPAGE_WALKING() {
        Companion companion = INSTANCE;
        return PAGE_WALKING;
    }

    @JvmStatic
    public static final void startActivity(Activity activity, int i, long j) {
        INSTANCE.startActivity(activity, i, j);
    }

    @Override // com.ido.life.base.BaseClickActivity
    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.ido.life.base.BaseClickActivity
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_recent_situation_detail_layout;
    }

    public static final /* synthetic */ RecentSituationDetailPresenter access$getMPresenter$p(RecentSituationDetailActivity recentSituationDetailActivity) {
        return (RecentSituationDetailPresenter) recentSituationDetailActivity.mPresenter;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void viewCreate() {
        super.viewCreate();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        if (intent.getExtras() != null) {
            Intent intent2 = getIntent();
            Intrinsics.checkExpressionValueIsNotNull(intent2, "intent");
            Bundle extras = intent2.getExtras();
            if (extras == null) {
                Intrinsics.throwNpe();
            }
            this.mPageType = extras.getInt(PAGE_TYPE, this.mPageType);
        }
        switchUIStyleByPageType();
        ((RecentSituationDetailPresenter) this.mPresenter).setMPageType(this.mPageType);
        ((RecentSituationDetailPresenter) this.mPresenter).getPageData();
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        GoalGradientBarChartBar goalGradientBarChartBar = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_right);
        if (goalGradientBarChartBar != null) {
            goalGradientBarChartBar.setMCallback(new GoalGradientBarChartBar.BarSizeCaluteCallback() { // from class: com.ido.life.module.home.recentsituation.RecentSituationDetailActivity.initViews.1
                @Override // com.ido.life.customview.charter.GoalGradientBarChartBar.BarSizeCaluteCallback
                public void onBarSizeCaluteComplete() {
                    GoalGradientBarChartBar goalGradientBarChartBar2 = (GoalGradientBarChartBar) RecentSituationDetailActivity.this._$_findCachedViewById(com.ido.life.R.id.chart_right);
                    List<GoalLineInfo> list = goalGradientBarChartBar2 != null ? goalGradientBarChartBar2.mGoalLineList : null;
                    if (list == null || list.isEmpty()) {
                        return;
                    }
                    Paint paint = new Paint();
                    TextView tv_recent_chart_avg = (TextView) RecentSituationDetailActivity.this._$_findCachedViewById(com.ido.life.R.id.tv_recent_chart_avg);
                    Intrinsics.checkExpressionValueIsNotNull(tv_recent_chart_avg, "tv_recent_chart_avg");
                    paint.setTextSize(tv_recent_chart_avg.getTextSize());
                    paint.setStyle(Paint.Style.FILL);
                    Rect rect = new Rect();
                    paint.getTextBounds(Constants.INDIA_CODE, 0, 2, rect);
                    TextView textView = (TextView) RecentSituationDetailActivity.this._$_findCachedViewById(com.ido.life.R.id.tv_recent_chart_avg);
                    if (textView != null) {
                        GoalLineInfo goalLineInfo = ((GoalGradientBarChartBar) RecentSituationDetailActivity.this._$_findCachedViewById(com.ido.life.R.id.chart_right)).mGoalLineList.get(0);
                        Intrinsics.checkExpressionValueIsNotNull(goalLineInfo, "chart_right.mGoalLineList[0]");
                        textView.setPadding(0, MathKt.roundToInt(goalLineInfo.getPosition()) - rect.height(), 0, 0);
                    }
                }
            });
        }
        GoalGradientBarChartBar goalGradientBarChartBar2 = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_left);
        if (goalGradientBarChartBar2 != null) {
            goalGradientBarChartBar2.setMCallback(new GoalGradientBarChartBar.BarSizeCaluteCallback() { // from class: com.ido.life.module.home.recentsituation.RecentSituationDetailActivity.initViews.2
                @Override // com.ido.life.customview.charter.GoalGradientBarChartBar.BarSizeCaluteCallback
                public void onBarSizeCaluteComplete() {
                    GoalGradientBarChartBar goalGradientBarChartBar3 = (GoalGradientBarChartBar) RecentSituationDetailActivity.this._$_findCachedViewById(com.ido.life.R.id.chart_left);
                    List<GoalLineInfo> list = goalGradientBarChartBar3 != null ? goalGradientBarChartBar3.mGoalLineList : null;
                    if (list == null || list.isEmpty()) {
                        return;
                    }
                    Paint paint = new Paint();
                    TextView tv_past_chart_avg = (TextView) RecentSituationDetailActivity.this._$_findCachedViewById(com.ido.life.R.id.tv_past_chart_avg);
                    Intrinsics.checkExpressionValueIsNotNull(tv_past_chart_avg, "tv_past_chart_avg");
                    paint.setTextSize(tv_past_chart_avg.getTextSize());
                    paint.setStyle(Paint.Style.FILL);
                    Rect rect = new Rect();
                    paint.getTextBounds(Constants.INDIA_CODE, 0, 2, rect);
                    TextView textView = (TextView) RecentSituationDetailActivity.this._$_findCachedViewById(com.ido.life.R.id.tv_past_chart_avg);
                    GoalLineInfo goalLineInfo = ((GoalGradientBarChartBar) RecentSituationDetailActivity.this._$_findCachedViewById(com.ido.life.R.id.chart_left)).mGoalLineList.get(0);
                    Intrinsics.checkExpressionValueIsNotNull(goalLineInfo, "chart_left.mGoalLineList[0]");
                    textView.setPadding(0, MathKt.roundToInt(goalLineInfo.getPosition()) - (rect.height() * 2), 0, 0);
                }
            });
        }
        ImageView img_header = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_header);
        Intrinsics.checkExpressionValueIsNotNull(img_header, "img_header");
        img_header.setMinimumHeight(StatusBarUtil.getStatusBarHeight(this) + getResources().getDimensionPixelSize(R.dimen.common_tittle_height));
    }

    private final void switchUIStyleByPageType() {
        RecentSituationWeekChart recentSituationWeekChart = (RecentSituationWeekChart) _$_findCachedViewById(com.ido.life.R.id.chart_trend);
        if (recentSituationWeekChart != null) {
            recentSituationWeekChart.mXGridLineCallback = new CustomChatBar.CaluteXGridLineCallback() { // from class: com.ido.life.module.home.recentsituation.RecentSituationDetailActivity.switchUIStyleByPageType.1
                @Override // com.ido.life.customview.charter.CustomChatBar.CaluteXGridLineCallback
                public final float calculateXGridLineValue(View view, int i) {
                    RecentSituationWeekChart recentSituationWeekChart2 = (RecentSituationWeekChart) RecentSituationDetailActivity.this._$_findCachedViewById(com.ido.life.R.id.chart_trend);
                    if (recentSituationWeekChart2 == null) {
                        Intrinsics.throwNpe();
                    }
                    List<String> list = recentSituationWeekChart2.mLabelYLeftList;
                    if (!(list == null || list.isEmpty())) {
                        try {
                            RecentSituationWeekChart recentSituationWeekChart3 = (RecentSituationWeekChart) RecentSituationDetailActivity.this._$_findCachedViewById(com.ido.life.R.id.chart_trend);
                            if (recentSituationWeekChart3 == null) {
                                Intrinsics.throwNpe();
                            }
                            Intrinsics.checkExpressionValueIsNotNull(recentSituationWeekChart3.mLabelYLeftList.get(i), "chart_trend!!.mLabelYLeftList[index]");
                            return Integer.parseInt(r2);
                        } catch (Exception unused) {
                        }
                    }
                    return 0.0f;
                }
            };
        }
        int i = this.mPageType;
        if (i == PAGE_ACTIVITY_CALORIE) {
            setTitle(R.string.activity);
            ImageView imageView = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_type);
            if (imageView != null) {
                imageView.setImageResource(R.mipmap.recent_situation_activity_calorie);
            }
            MediumTextView mediumTextView = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_value);
            if (mediumTextView != null) {
                mediumTextView.setTextColor(getResources().getColor(R.color.color_FF4826));
            }
            MediumTextView mediumTextView2 = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_value);
            if (mediumTextView2 != null) {
                mediumTextView2.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            }
            TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_unit);
            if (textView != null) {
                textView.setTextColor(getResources().getColor(R.color.color_FF4826));
            }
            String calorieUnit = RunTimeUtil.getCalorieUnit();
            TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_unit);
            if (textView2 != null) {
                textView2.setText(calorieUnit + IOUtils.DIR_SEPARATOR_UNIX + LanguageUtil.getLanguageText(R.string.fitness_day_unit));
            }
            TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_target_unit);
            if (textView3 != null) {
                textView3.setText(calorieUnit);
            }
            RecentSituationProgressView recentSituationProgressView = (RecentSituationProgressView) _$_findCachedViewById(com.ido.life.R.id.progress);
            if (recentSituationProgressView != null) {
                recentSituationProgressView.setMLeftLabel("0 " + calorieUnit);
            }
            RecentSituationProgressView recentSituationProgressView2 = (RecentSituationProgressView) _$_findCachedViewById(com.ido.life.R.id.progress);
            if (recentSituationProgressView2 != null) {
                recentSituationProgressView2.setMRightLabel("0 " + calorieUnit);
            }
            TextView textView4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_desc);
            if (textView4 != null) {
                textView4.setText(getLanguageText(R.string.recent_situation_activity_calorie_amount_desc));
            }
            TextView textView5 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_target_title);
            if (textView5 != null) {
                textView5.setText(getLanguageText(R.string.target) + (char) 65306);
            }
            TextView textView6 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_target_value);
            if (textView6 != null) {
                textView6.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            }
            if (((RecentSituationDetailPresenter) this.mPresenter).supportActiveCalorieTarget()) {
                enableSetTarget();
            } else {
                disableSetTarget();
            }
            RecentSituationProgressView recentSituationProgressView3 = (RecentSituationProgressView) _$_findCachedViewById(com.ido.life.R.id.progress);
            if (recentSituationProgressView3 != null) {
                recentSituationProgressView3.setMPropertyList(((RecentSituationDetailPresenter) this.mPresenter).getActivityCalorie());
            }
            ImageView imageView2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_compare_state);
            if (imageView2 != null) {
                imageView2.setImageResource(R.mipmap.fitness_activity_calorie_flat);
            }
            TextView textView7 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_compare_state_title);
            if (textView7 != null) {
                textView7.setTextColor(getColor(R.color.color_E83D1D));
            }
            TextView textView8 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_compare_state_title);
            if (textView8 != null) {
                textView8.setText("");
            }
            TextView textView9 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_compare_state_desc);
            if (textView9 != null) {
                textView9.setText("");
            }
            TextView textView10 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_past_desc);
            if (textView10 != null) {
                textView10.setText("");
            }
            TextView textView11 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_recent_desc);
            if (textView11 != null) {
                textView11.setText("");
            }
            TextView textView12 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_chart_trend_title);
            if (textView12 != null) {
                textView12.setText(getLanguageText(R.string.recent_situation_trend_chart_title));
            }
            TextView textView13 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_chart_desc);
            if (textView13 != null) {
                textView13.setText(getLanguageText(R.string.recent_situation_activity_calorie_desc));
                return;
            }
            return;
        }
        if (i == PAGE_ACTIVITY_TIME) {
            setTitle(R.string.exercise);
            ImageView imageView3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_type);
            if (imageView3 != null) {
                imageView3.setImageResource(R.mipmap.recent_situation_activity_time);
            }
            MediumTextView mediumTextView3 = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_value);
            if (mediumTextView3 != null) {
                mediumTextView3.setTextColor(getColor(R.color.color_1ACE5E));
            }
            MediumTextView mediumTextView4 = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_value);
            if (mediumTextView4 != null) {
                mediumTextView4.setText("");
            }
            TextView textView14 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_unit);
            if (textView14 != null) {
                textView14.setTextColor(getColor(R.color.color_1ACE5E));
            }
            TextView textView15 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_unit);
            if (textView15 != null) {
                textView15.setText(getLanguageText(R.string.min_unit_short) + IOUtils.DIR_SEPARATOR_UNIX + getLanguageText(R.string.fitness_day_unit));
            }
            TextView textView16 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_desc);
            if (textView16 != null) {
                textView16.setText(getLanguageText(R.string.recent_situation_activity_time_amount_desc));
            }
            TextView textView17 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_target_title);
            if (textView17 != null) {
                textView17.setText(getLanguageText(R.string.target) + (char) 65306);
            }
            TextView textView18 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_target_value);
            if (textView18 != null) {
                textView18.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            }
            TextView textView19 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_target_unit);
            if (textView19 != null) {
                textView19.setText(getLanguageText(R.string.min_unit_short));
            }
            if (((RecentSituationDetailPresenter) this.mPresenter).supportActiveTimeTarget()) {
                enableSetTarget();
            } else {
                disableSetTarget();
            }
            RecentSituationProgressView recentSituationProgressView4 = (RecentSituationProgressView) _$_findCachedViewById(com.ido.life.R.id.progress);
            if (recentSituationProgressView4 != null) {
                recentSituationProgressView4.setMLeftLabel("5 " + getLanguageText(R.string.min_unit_short));
            }
            RecentSituationProgressView recentSituationProgressView5 = (RecentSituationProgressView) _$_findCachedViewById(com.ido.life.R.id.progress);
            if (recentSituationProgressView5 != null) {
                recentSituationProgressView5.setMPropertyList(((RecentSituationDetailPresenter) this.mPresenter).getActivityTime());
            }
            RecentSituationProgressView recentSituationProgressView6 = (RecentSituationProgressView) _$_findCachedViewById(com.ido.life.R.id.progress);
            if (recentSituationProgressView6 != null) {
                recentSituationProgressView6.setMRightLabel("60 " + getLanguageText(R.string.min_unit_short));
            }
            ImageView imageView4 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_compare_state);
            if (imageView4 != null) {
                imageView4.setImageResource(R.mipmap.fitness_activity_time_flat);
            }
            TextView textView20 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_compare_state_title);
            if (textView20 != null) {
                textView20.setTextColor(getColor(R.color.color_00BE47));
            }
            TextView textView21 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_compare_state_title);
            if (textView21 != null) {
                textView21.setText("");
            }
            TextView textView22 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_compare_state_desc);
            if (textView22 != null) {
                textView22.setText("");
            }
            GoalGradientBarChartBar goalGradientBarChartBar = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_right);
            if (goalGradientBarChartBar != null) {
                goalGradientBarChartBar.setBarStartColor(getColor(R.color.color_00C84B));
            }
            TextView textView23 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_past_desc);
            if (textView23 != null) {
                textView23.setTextColor(Color.parseColor("#B6CBBE"));
            }
            TextView textView24 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_past_desc);
            if (textView24 != null) {
                textView24.setText("");
            }
            TextView textView25 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_recent_desc);
            if (textView25 != null) {
                textView25.setTextColor(getColor(R.color.color_00BE47));
            }
            TextView textView26 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_recent_desc);
            if (textView26 != null) {
                textView26.setText("");
            }
            TextView textView27 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_chart_trend_title);
            if (textView27 != null) {
                textView27.setText(getLanguageText(R.string.recent_situation_trend_chart_title));
            }
            TextView textView28 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_chart_desc);
            if (textView28 != null) {
                textView28.setText(getLanguageText(R.string.recent_situation_activity_time_desc));
                return;
            }
            return;
        }
        setTitle(R.string.walking);
        ImageView imageView5 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_type);
        if (imageView5 != null) {
            imageView5.setImageResource(R.mipmap.recent_situation_walking);
        }
        MediumTextView mediumTextView5 = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_value);
        if (mediumTextView5 != null) {
            mediumTextView5.setTextColor(getColor(R.color.color_1AA5F0));
        }
        MediumTextView mediumTextView6 = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_value);
        if (mediumTextView6 != null) {
            mediumTextView6.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        }
        TextView textView29 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_unit);
        if (textView29 != null) {
            textView29.setTextColor(getColor(R.color.color_1AA5F0));
        }
        TextView textView30 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_unit);
        if (textView30 != null) {
            textView30.setText(getLanguageText(R.string.public_unit_hr) + IOUtils.DIR_SEPARATOR_UNIX + getLanguageText(R.string.fitness_day_unit));
        }
        TextView textView31 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_desc);
        if (textView31 != null) {
            textView31.setText(getLanguageText(R.string.recent_situation_walking_amount_desc));
        }
        TextView textView32 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_target_title);
        if (textView32 != null) {
            textView32.setText(getLanguageText(R.string.target) + (char) 65306);
        }
        TextView textView33 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_target_value);
        if (textView33 != null) {
            textView33.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        }
        TextView textView34 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_target_unit);
        if (textView34 != null) {
            textView34.setText(getLanguageText(R.string.public_unit_hr));
        }
        if (((RecentSituationDetailPresenter) this.mPresenter).supportWalkTarget()) {
            enableSetTarget();
        } else {
            disableSetTarget();
        }
        RecentSituationProgressView recentSituationProgressView7 = (RecentSituationProgressView) _$_findCachedViewById(com.ido.life.R.id.progress);
        if (recentSituationProgressView7 != null) {
            recentSituationProgressView7.setMLeftLabel("6 " + getLanguageText(R.string.public_unit_hrs));
        }
        RecentSituationProgressView recentSituationProgressView8 = (RecentSituationProgressView) _$_findCachedViewById(com.ido.life.R.id.progress);
        if (recentSituationProgressView8 != null) {
            recentSituationProgressView8.setMPropertyList(((RecentSituationDetailPresenter) this.mPresenter).getWalking());
        }
        RecentSituationProgressView recentSituationProgressView9 = (RecentSituationProgressView) _$_findCachedViewById(com.ido.life.R.id.progress);
        if (recentSituationProgressView9 != null) {
            recentSituationProgressView9.setMRightLabel("14 " + getLanguageText(R.string.public_unit_hrs));
        }
        ImageView imageView6 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_compare_state);
        if (imageView6 != null) {
            imageView6.setImageResource(R.mipmap.fitness_walk_flat);
        }
        TextView textView35 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_compare_state_title);
        if (textView35 != null) {
            textView35.setTextColor(getColor(R.color.color_0BA9FF));
        }
        TextView textView36 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_compare_state_title);
        if (textView36 != null) {
            textView36.setText("");
        }
        TextView textView37 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_compare_state_desc);
        if (textView37 != null) {
            textView37.setText("");
        }
        GoalGradientBarChartBar goalGradientBarChartBar2 = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_right);
        if (goalGradientBarChartBar2 != null) {
            goalGradientBarChartBar2.setBarStartColor(getColor(R.color.color_0BA9FF));
        }
        GoalGradientBarChartBar goalGradientBarChartBar3 = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_right);
        if (goalGradientBarChartBar3 != null) {
            goalGradientBarChartBar3.setBarEndColor(getColor(R.color.color_3AE5FF));
        }
        TextView textView38 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_past_desc);
        if (textView38 != null) {
            textView38.setText("");
        }
        TextView textView39 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_recent_desc);
        if (textView39 != null) {
            textView39.setText("");
        }
        TextView textView40 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_chart_trend_title);
        if (textView40 != null) {
            textView40.setText(getLanguageText(R.string.recent_situation_trend_chart_title));
        }
        TextView textView41 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_chart_desc);
        if (textView41 != null) {
            textView41.setText(getLanguageText(R.string.recent_situation_walk_desc));
        }
    }

    private final void enableSetTarget() {
        ImageView imageView = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_target_more);
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_set_target);
        if (linearLayout != null) {
            linearLayout.setOnClickListener(this);
        }
    }

    private final void disableSetTarget() {
        ImageView imageView = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_target_more);
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_set_target);
        if (linearLayout != null) {
            linearLayout.setBackgroundTintList(ColorStateList.valueOf(0));
        }
        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_set_target);
        if (linearLayout2 != null) {
            linearLayout2.setOnClickListener(null);
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateRecentAvg(float avg) {
        int i = this.mPageType;
        if (i == PAGE_ACTIVITY_CALORIE) {
            MediumTextView mediumTextView = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_value);
            if (mediumTextView != null) {
                mediumTextView.setText(String.valueOf(MathKt.roundToInt(avg)));
                return;
            }
            return;
        }
        if (i == PAGE_ACTIVITY_TIME) {
            MediumTextView mediumTextView2 = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_value);
            if (mediumTextView2 != null) {
                mediumTextView2.setText(String.valueOf(MathKt.roundToInt(avg)));
                return;
            }
            return;
        }
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_unit);
        if (textView != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(getLanguageText(avg > ((float) 1) ? R.string.public_unit_hrs : R.string.public_unit_hr));
            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
            sb.append(getLanguageText(R.string.fitness_day_unit));
            textView.setText(sb.toString());
        }
        MediumTextView mediumTextView3 = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_value);
        if (mediumTextView3 != null) {
            mediumTextView3.setText(String.valueOf(MathKt.roundToInt(avg)));
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateTarget(int target) {
        if (target <= 0) {
            int i = this.mPageType;
            if (i == PAGE_ACTIVITY_TIME) {
                target = 30;
            } else if (i == PAGE_WALKING) {
                target = 12;
            }
        }
        int i2 = this.mPageType;
        if (i2 == PAGE_ACTIVITY_CALORIE) {
            TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_target_value);
            if (textView != null) {
                textView.setText(String.valueOf(target));
                return;
            }
            return;
        }
        if (i2 == PAGE_ACTIVITY_TIME) {
            TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_target_value);
            if (textView2 != null) {
                textView2.setText(String.valueOf(target));
                return;
            }
            return;
        }
        TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_target_unit);
        if (textView3 != null) {
            textView3.setText(getLanguageText(target > 1 ? R.string.public_unit_hrs : R.string.public_unit_hr));
        }
        TextView textView4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_target_value);
        if (textView4 != null) {
            textView4.setText(String.valueOf(target));
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateProgressMaxmin(int max, int min) {
        int i = this.mPageType;
        if (i == PAGE_ACTIVITY_CALORIE) {
            String languageText = LanguageUtil.getLanguageText(R.string.public_heat_calorie);
            RecentSituationProgressView recentSituationProgressView = (RecentSituationProgressView) _$_findCachedViewById(com.ido.life.R.id.progress);
            if (recentSituationProgressView != null) {
                recentSituationProgressView.setMLeftLabel(min + ' ' + languageText);
            }
            RecentSituationProgressView recentSituationProgressView2 = (RecentSituationProgressView) _$_findCachedViewById(com.ido.life.R.id.progress);
            if (recentSituationProgressView2 != null) {
                recentSituationProgressView2.setMRightLabel(max + ' ' + languageText);
                return;
            }
            return;
        }
        if (i == PAGE_ACTIVITY_TIME) {
            RecentSituationProgressView recentSituationProgressView3 = (RecentSituationProgressView) _$_findCachedViewById(com.ido.life.R.id.progress);
            if (recentSituationProgressView3 != null) {
                recentSituationProgressView3.setMLeftLabel(min + ' ' + getLanguageText(R.string.min_unit_short));
            }
            RecentSituationProgressView recentSituationProgressView4 = (RecentSituationProgressView) _$_findCachedViewById(com.ido.life.R.id.progress);
            if (recentSituationProgressView4 != null) {
                recentSituationProgressView4.setMRightLabel(max + ' ' + getLanguageText(R.string.min_unit_short));
                return;
            }
            return;
        }
        if (i == PAGE_WALKING) {
            RecentSituationProgressView recentSituationProgressView5 = (RecentSituationProgressView) _$_findCachedViewById(com.ido.life.R.id.progress);
            if (recentSituationProgressView5 != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(min);
                sb.append(' ');
                sb.append(min > 1 ? getLanguageText(R.string.public_unit_hrs) : getLanguageText(R.string.public_unit_hr));
                recentSituationProgressView5.setMLeftLabel(sb.toString());
            }
            RecentSituationProgressView recentSituationProgressView6 = (RecentSituationProgressView) _$_findCachedViewById(com.ido.life.R.id.progress);
            if (recentSituationProgressView6 != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(max);
                sb2.append(' ');
                sb2.append(max > 1 ? getLanguageText(R.string.public_unit_hrs) : getLanguageText(R.string.public_unit_hr));
                recentSituationProgressView6.setMRightLabel(sb2.toString());
            }
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateProgress(int currProgress) {
        RecentSituationProgressView recentSituationProgressView = (RecentSituationProgressView) _$_findCachedViewById(com.ido.life.R.id.progress);
        if (recentSituationProgressView != null) {
            recentSituationProgressView.setMCurrProgress(currProgress);
        }
        RecentSituationProgressView recentSituationProgressView2 = (RecentSituationProgressView) _$_findCachedViewById(com.ido.life.R.id.progress);
        if (recentSituationProgressView2 != null) {
            recentSituationProgressView2.invalidate();
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateCompareStateIcon(int iconResid) {
        ImageView imageView = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_compare_state);
        if (imageView != null) {
            imageView.setImageResource(iconResid);
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateCompareStateTitle(String title) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_compare_state_title);
        if (textView != null) {
            textView.setText(title);
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateCompareStateDesc(String compareDesc) {
        Intrinsics.checkParameterIsNotNull(compareDesc, "compareDesc");
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_compare_state_desc);
        if (textView != null) {
            textView.setText(compareDesc);
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateRecentChartAvg(float avg) {
        if (avg >= 0) {
            int i = this.mPageType;
            if (i == PAGE_ACTIVITY_TIME) {
                TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_recent_chart_avg);
                if (textView != null) {
                    textView.setText(String.valueOf(MathKt.roundToInt(avg)));
                }
            } else if (i == PAGE_ACTIVITY_CALORIE) {
                TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_recent_chart_avg);
                if (textView2 != null) {
                    textView2.setText(String.valueOf(new BigDecimal(String.valueOf(avg)).setScale(1, RoundingMode.HALF_UP).floatValue()));
                }
            } else if (MathKt.roundToInt(avg) == avg) {
                TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_recent_chart_avg);
                if (textView3 != null) {
                    textView3.setText(String.valueOf(MathKt.roundToInt(avg)));
                }
            } else {
                TextView textView4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_recent_chart_avg);
                if (textView4 != null) {
                    textView4.setText(String.valueOf(new BigDecimal(String.valueOf(avg)).setScale(1, RoundingMode.HALF_UP).floatValue()));
                }
            }
            GoalGradientBarChartBar goalGradientBarChartBar = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_right);
            if (goalGradientBarChartBar != null) {
                goalGradientBarChartBar.setGoalLineList(CollectionsKt.mutableListOf(new GoalLineInfo(0, MathKt.roundToInt(avg), avg, "")));
            }
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updatePastChartAvg(float avg) {
        if (avg >= 0) {
            int i = this.mPageType;
            if (i == PAGE_ACTIVITY_TIME) {
                TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_past_chart_avg);
                if (textView != null) {
                    textView.setText(String.valueOf(MathKt.roundToInt(avg)));
                }
            } else if (i == PAGE_ACTIVITY_CALORIE) {
                TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_past_chart_avg);
                if (textView2 != null) {
                    textView2.setText(String.valueOf(new BigDecimal(String.valueOf(avg)).setScale(1, RoundingMode.HALF_UP).floatValue()));
                }
            } else if (MathKt.roundToInt(avg) == avg) {
                TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_past_chart_avg);
                if (textView3 != null) {
                    textView3.setText(String.valueOf(MathKt.roundToInt(avg)));
                }
            } else {
                TextView textView4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_past_chart_avg);
                if (textView4 != null) {
                    textView4.setText(String.valueOf(new BigDecimal(String.valueOf(avg)).setScale(1, RoundingMode.HALF_UP).floatValue()));
                }
            }
            GoalGradientBarChartBar goalGradientBarChartBar = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_left);
            if (goalGradientBarChartBar != null) {
                goalGradientBarChartBar.setGoalLineList(CollectionsKt.mutableListOf(new GoalLineInfo(0, MathKt.roundToInt(avg), avg, "")));
            }
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updatePastChartDesc(String desc) {
        TextView textView;
        String str = desc;
        if ((str == null || str.length() == 0) || (textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_chart_left_bottom_label)) == null) {
            return;
        }
        textView.setText(str);
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateRecentChartDesc(String desc) {
        TextView textView;
        String str = desc;
        if ((str == null || str.length() == 0) || (textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_chart_right_bottom_label)) == null) {
            return;
        }
        textView.setText(str);
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updatePassDayCount(int totalCount, int validCount) {
        TextView textView;
        if (totalCount >= 1 && (textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_past_desc)) != null) {
            textView.setText(getLanguageText(R.string.past) + ' ' + validCount + IOUtils.DIR_SEPARATOR_UNIX + totalCount + " (" + ((validCount * 100) / totalCount) + "%)");
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateRecentDayCount(int totalCount, int validCount) {
        TextView textView;
        if (totalCount >= 1 && (textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_recent_desc)) != null) {
            textView.setText(getLanguageText(R.string.past) + ' ' + validCount + IOUtils.DIR_SEPARATOR_UNIX + totalCount + " (" + ((validCount * 100) / totalCount) + "%)");
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateRecentBarXMaxmin(int max, int min) {
        GoalGradientBarChartBar goalGradientBarChartBar = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_right);
        if (goalGradientBarChartBar != null) {
            goalGradientBarChartBar.mXMinValue = min;
        }
        GoalGradientBarChartBar goalGradientBarChartBar2 = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_right);
        if (goalGradientBarChartBar2 != null) {
            goalGradientBarChartBar2.mXMaxValue = max;
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updatePastBarXMaxmin(int max, int min) {
        GoalGradientBarChartBar goalGradientBarChartBar = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_left);
        if (goalGradientBarChartBar != null) {
            goalGradientBarChartBar.mXMaxValue = max;
        }
        GoalGradientBarChartBar goalGradientBarChartBar2 = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_left);
        if (goalGradientBarChartBar2 != null) {
            goalGradientBarChartBar2.mXMinValue = min;
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateBarYmaxmin(int max, int min) {
        GoalGradientBarChartBar goalGradientBarChartBar = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_left);
        if (goalGradientBarChartBar != null) {
            goalGradientBarChartBar.mYMinValue = min;
        }
        GoalGradientBarChartBar goalGradientBarChartBar2 = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_left);
        if (goalGradientBarChartBar2 != null) {
            goalGradientBarChartBar2.mYMaxValue = max;
        }
        GoalGradientBarChartBar goalGradientBarChartBar3 = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_right);
        if (goalGradientBarChartBar3 != null) {
            goalGradientBarChartBar3.mYMinValue = min;
        }
        GoalGradientBarChartBar goalGradientBarChartBar4 = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_right);
        if (goalGradientBarChartBar4 != null) {
            goalGradientBarChartBar4.mYMaxValue = max;
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateLineXMaxmin(int max, int min) {
        RecentSituationWeekChart recentSituationWeekChart = (RecentSituationWeekChart) _$_findCachedViewById(com.ido.life.R.id.chart_trend);
        if (recentSituationWeekChart != null) {
            recentSituationWeekChart.mXMinValue = min;
        }
        RecentSituationWeekChart recentSituationWeekChart2 = (RecentSituationWeekChart) _$_findCachedViewById(com.ido.life.R.id.chart_trend);
        if (recentSituationWeekChart2 != null) {
            recentSituationWeekChart2.mXMaxValue = max;
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateLineYMaxmin(int max, int min) {
        RecentSituationWeekChart recentSituationWeekChart = (RecentSituationWeekChart) _$_findCachedViewById(com.ido.life.R.id.chart_trend);
        if (recentSituationWeekChart != null) {
            recentSituationWeekChart.mYMinValue = min;
        }
        RecentSituationWeekChart recentSituationWeekChart2 = (RecentSituationWeekChart) _$_findCachedViewById(com.ido.life.R.id.chart_trend);
        if (recentSituationWeekChart2 != null) {
            recentSituationWeekChart2.mYMaxValue = max;
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateLineXLabel(List<String> labelList) {
        Intrinsics.checkParameterIsNotNull(labelList, "labelList");
        RecentSituationWeekChart recentSituationWeekChart = (RecentSituationWeekChart) _$_findCachedViewById(com.ido.life.R.id.chart_trend);
        if (recentSituationWeekChart != null) {
            recentSituationWeekChart.setLabelXList(labelList);
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateLineYLabel(List<String> labelList) {
        Intrinsics.checkParameterIsNotNull(labelList, "labelList");
        RecentSituationWeekChart recentSituationWeekChart = (RecentSituationWeekChart) _$_findCachedViewById(com.ido.life.R.id.chart_trend);
        if (recentSituationWeekChart != null) {
            recentSituationWeekChart.setLabelYLeftList(labelList);
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updatePastChartData(List<? extends GradientBarPoint> dataList) {
        GoalGradientBarChartBar goalGradientBarChartBar = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_left);
        if (goalGradientBarChartBar != null) {
            goalGradientBarChartBar.setList(dataList);
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateRecentChartData(List<? extends GradientBarPoint> dataList) {
        GoalGradientBarChartBar goalGradientBarChartBar = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_right);
        if (goalGradientBarChartBar != null) {
            goalGradientBarChartBar.setList(dataList);
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void updateLineChartData(List<RecentSituationWeekChart.ChartBean> dataList) {
        RecentSituationWeekChart recentSituationWeekChart = (RecentSituationWeekChart) _$_findCachedViewById(com.ido.life.R.id.chart_trend);
        if (recentSituationWeekChart != null) {
            recentSituationWeekChart.setList(dataList);
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void refreshRecentChart(boolean showAnimator) {
        GoalGradientBarChartBar goalGradientBarChartBar = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_right);
        if (goalGradientBarChartBar != null) {
            goalGradientBarChartBar.refreshChart(showAnimator);
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void refreshPastChart(boolean showAnimator) {
        GoalGradientBarChartBar goalGradientBarChartBar = (GoalGradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_left);
        if (goalGradientBarChartBar != null) {
            goalGradientBarChartBar.refreshChart(showAnimator);
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void refreshLineChart(boolean showAnimator) {
        RecentSituationWeekChart recentSituationWeekChart = (RecentSituationWeekChart) _$_findCachedViewById(com.ido.life.R.id.chart_trend);
        if (recentSituationWeekChart != null) {
            recentSituationWeekChart.refreshChart(showAnimator);
        }
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void showDialog() {
        showLoadingDialog();
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void dismissDialog() {
        dismissLoadingDialog();
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void onTargetSettingSuccess() {
        showToast(R.string.setting_success);
    }

    @Override // com.ido.life.module.home.recentsituation.IRecentSituationDetailView
    public void onTargetSettingFailed() {
        showToast(R.string.setting_failed);
    }

    /* JADX INFO: compiled from: RecentSituationDetailActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0019H\u0007R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u0007R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u0002\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0002\u001a\u0004\b\u0012\u0010\u0007¨\u0006\u001a"}, d2 = {"Lcom/ido/life/module/home/recentsituation/RecentSituationDetailActivity$Companion;", "", "()V", "PAGE_ACTIVITY_CALORIE", "", "PAGE_ACTIVITY_CALORIE$annotations", "getPAGE_ACTIVITY_CALORIE", "()I", "PAGE_ACTIVITY_TIME", "PAGE_ACTIVITY_TIME$annotations", "getPAGE_ACTIVITY_TIME", "PAGE_TYPE", "", "PAGE_TYPE$annotations", "getPAGE_TYPE", "()Ljava/lang/String;", "PAGE_WALKING", "PAGE_WALKING$annotations", "getPAGE_WALKING", "startActivity", "", "activity", "Landroid/app/Activity;", "pageType", "userId", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void PAGE_ACTIVITY_CALORIE$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void PAGE_ACTIVITY_TIME$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void PAGE_TYPE$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void PAGE_WALKING$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getPAGE_ACTIVITY_CALORIE() {
            return RecentSituationDetailActivity.PAGE_ACTIVITY_CALORIE;
        }

        public final int getPAGE_ACTIVITY_TIME() {
            return RecentSituationDetailActivity.PAGE_ACTIVITY_TIME;
        }

        public final int getPAGE_WALKING() {
            return RecentSituationDetailActivity.PAGE_WALKING;
        }

        public final String getPAGE_TYPE() {
            return RecentSituationDetailActivity.PAGE_TYPE;
        }

        @JvmStatic
        public final void startActivity(Activity activity, int pageType, long userId) {
            Intrinsics.checkParameterIsNotNull(activity, "activity");
            Intent intent = new Intent(activity, (Class<?>) RecentSituationDetailActivity.class);
            intent.putExtras(BundleKt.bundleOf(new Pair(getPAGE_TYPE(), Integer.valueOf(pageType)), new Pair(Constants.INTENT_USER_ID, Long.valueOf(userId))));
            intent.addFlags(67108864);
            activity.startActivity(intent);
        }
    }

    @Override // com.ido.life.base.BaseClickActivity
    public void clickAction(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == R.id.lay_set_target) {
            UserSettingTargetFragment userSettingTargetFragmentNewInstance = UserSettingTargetFragment.INSTANCE.newInstance(this.mPageType);
            userSettingTargetFragmentNewInstance.show(getSupportFragmentManager());
            userSettingTargetFragmentNewInstance.setOnTargetEnsureListener(new UserSettingTargetFragment.OnTargetEnsureListener() { // from class: com.ido.life.module.home.recentsituation.RecentSituationDetailActivity.clickAction.1
                @Override // com.ido.life.module.home.recentsituation.targetset.UserSettingTargetFragment.OnTargetEnsureListener
                public void confirmValue(int targetType, List<Integer> targetValue) {
                    RecentSituationDetailActivity recentSituationDetailActivity;
                    int i;
                    Intrinsics.checkParameterIsNotNull(targetValue, "targetValue");
                    if (targetValue.size() != 2) {
                        return;
                    }
                    if (targetType == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_CALORIE()) {
                        TextView tv_target_value = (TextView) RecentSituationDetailActivity.this._$_findCachedViewById(com.ido.life.R.id.tv_target_value);
                        Intrinsics.checkExpressionValueIsNotNull(tv_target_value, "tv_target_value");
                        tv_target_value.setText(String.valueOf(targetValue.get(0).intValue()));
                        RecentSituationDetailActivity.access$getMPresenter$p(RecentSituationDetailActivity.this).saveUserTarget(targetValue.get(0).intValue(), 0);
                        return;
                    }
                    if (targetType == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_TIME()) {
                        TextView tv_target_value2 = (TextView) RecentSituationDetailActivity.this._$_findCachedViewById(com.ido.life.R.id.tv_target_value);
                        Intrinsics.checkExpressionValueIsNotNull(tv_target_value2, "tv_target_value");
                        tv_target_value2.setText(String.valueOf(targetValue.get(0).intValue()));
                        RecentSituationDetailActivity.access$getMPresenter$p(RecentSituationDetailActivity.this).saveUserTarget(targetValue.get(0).intValue(), 0);
                        return;
                    }
                    int iIntValue = targetValue.get(1).intValue();
                    TextView tv_target_value3 = (TextView) RecentSituationDetailActivity.this._$_findCachedViewById(com.ido.life.R.id.tv_target_value);
                    Intrinsics.checkExpressionValueIsNotNull(tv_target_value3, "tv_target_value");
                    tv_target_value3.setText(String.valueOf(targetValue.get(0).intValue()));
                    TextView textView = (TextView) RecentSituationDetailActivity.this._$_findCachedViewById(com.ido.life.R.id.tv_target_unit);
                    if (textView != null) {
                        if (iIntValue > 1) {
                            recentSituationDetailActivity = RecentSituationDetailActivity.this;
                            i = R.string.public_unit_hrs;
                        } else {
                            recentSituationDetailActivity = RecentSituationDetailActivity.this;
                            i = R.string.public_unit_hr;
                        }
                        textView.setText(recentSituationDetailActivity.getLanguageText(i));
                    }
                    RecentSituationDetailActivity.access$getMPresenter$p(RecentSituationDetailActivity.this).saveUserTarget(targetValue.get(0).intValue(), iIntValue);
                }
            });
        } else {
            if (id != R.id.lay_stage_info) {
                return;
            }
            int i = this.mPageType;
            if (i == PAGE_ACTIVITY_CALORIE) {
                DialogHelper.INSTANCE.showFitnessCalorieSummaryDialog(this);
            } else if (i == PAGE_ACTIVITY_TIME) {
                DialogHelper.INSTANCE.showFitnessTimeSummaryDialog(this);
            } else {
                DialogHelper.INSTANCE.showFitnessWalkSummaryDialog(this);
            }
        }
    }
}