package com.ido.life.module.user.healthreport;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.bumptech.glide.Glide;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.ImageLoaderUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.BarChartPoint;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.bean.GoalLineInfo;
import com.ido.life.customview.charter.HealthReportChartBar;
import com.ido.life.customview.charter.HealthReportLineBar;
import com.ido.life.customview.charter.HealthSmallReportChartBar;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.dialog.HealthReportShareDialogFragment;
import com.ido.life.enums.UserModelEnum;
import com.ido.life.module.user.feedback.FeedbackActivity;
import com.ido.life.util.eventbus.IHandlerEventBus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: HealthReportNewActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b4\n\u0002\u0010\u000b\n\u0002\b\f\u0018\u0000 b2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u0005:\u0001bB\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0014J\u0016\u0010\u0013\u001a\u00020\u00112\f\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0011H\u0014J\b\u0010\u0017\u001a\u00020\u0011H\u0014J\b\u0010\u0018\u001a\u00020\u0011H\u0016J\u0012\u0010\u0019\u001a\u00020\u00112\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u00112\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\rH\u0016J\u0018\u0010\u001f\u001a\u00020\u00112\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010 H\u0016J\u0018\u0010!\u001a\u00020\u00112\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\rH\u0016J\u0018\u0010#\u001a\u00020\u00112\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\rH\u0016J\u0018\u0010$\u001a\u00020\u00112\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\rH\u0016J\u0018\u0010%\u001a\u00020\u00112\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\rH\u0016J\b\u0010&\u001a\u00020\u0011H\u0016J\b\u0010'\u001a\u00020\u0011H\u0016J\u0016\u0010(\u001a\u00020\u00112\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0 H\u0016J\u0016\u0010)\u001a\u00020\u00112\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0 H\u0016J\u0010\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u000fH\u0016J\u0010\u0010,\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u000fH\u0016J\u0010\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\u000fH\u0016J\u0010\u00100\u001a\u00020\u00112\u0006\u00101\u001a\u00020\u000fH\u0016J\u0016\u00102\u001a\u00020\u00112\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0 H\u0016J\u0016\u00103\u001a\u00020\u00112\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0 H\u0016J\u0010\u00104\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u000fH\u0016J\u0010\u00105\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u000fH\u0016J\u0010\u00106\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\u000fH\u0016J\u0010\u00107\u001a\u00020\u00112\u0006\u00101\u001a\u00020\u000fH\u0016J\u0016\u00108\u001a\u00020\u00112\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0 H\u0016J\u0016\u00109\u001a\u00020\u00112\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0 H\u0016J\u0010\u0010:\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u000fH\u0016J\u0010\u0010;\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u000fH\u0016J\u0010\u0010<\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\u000fH\u0016J\u0010\u0010=\u001a\u00020\u00112\u0006\u00101\u001a\u00020\u000fH\u0016J\b\u0010>\u001a\u00020\u0011H\u0002J\u0010\u0010?\u001a\u00020\u00112\u0006\u0010@\u001a\u00020\u000fH\u0016J\u0016\u0010A\u001a\u00020\u00112\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0 H\u0016J\u0016\u0010B\u001a\u00020\u00112\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0 H\u0016J\u0010\u0010C\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u000fH\u0016J\u0010\u0010D\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u000fH\u0016J\u0010\u0010E\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\u000fH\u0016J\u0010\u0010F\u001a\u00020\u00112\u0006\u00101\u001a\u00020\u000fH\u0016J\u0016\u0010G\u001a\u00020\u00112\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0 H\u0016J\u0016\u0010H\u001a\u00020\u00112\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0 H\u0016J\u0010\u0010I\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u000fH\u0016J\u0010\u0010J\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u000fH\u0016J\u0010\u0010K\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\u000fH\u0016J\u0010\u0010L\u001a\u00020\u00112\u0006\u00101\u001a\u00020\u000fH\u0016J\b\u0010M\u001a\u00020\u0011H\u0016J\b\u0010N\u001a\u00020\u0011H\u0002J\u0010\u0010O\u001a\u00020\u00112\u0006\u0010P\u001a\u00020\u000fH\u0016J\u0018\u0010Q\u001a\u00020\u00112\u0006\u0010R\u001a\u00020\u000f2\u0006\u0010S\u001a\u00020\u000fH\u0016J\u0018\u0010T\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\u000f2\u0006\u0010V\u001a\u00020WH\u0016J\u0010\u0010X\u001a\u00020\u00112\u0006\u0010Y\u001a\u00020\u000fH\u0016J\u0012\u0010Z\u001a\u00020\u00112\b\u0010[\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\\\u001a\u00020\u00112\b\u0010]\u001a\u0004\u0018\u00010\bH\u0016J\u0010\u0010^\u001a\u00020\u00112\u0006\u0010_\u001a\u00020\u000fH\u0016J\u0010\u0010`\u001a\u00020\u00112\u0006\u0010a\u001a\u00020\bH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006c"}, d2 = {"Lcom/ido/life/module/user/healthreport/HealthReportNewActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/user/healthreport/HealthReportNewPresenter;", "Landroid/view/View$OnClickListener;", "Lcom/ido/life/module/user/healthreport/HealthReportNewView;", "Lcom/ido/life/util/eventbus/IHandlerEventBus;", "()V", "mDisableColor", "", "mModelAdapter", "Lcom/ido/life/customview/recyclerview/CommonRecyclerViewAdapter;", "Lcom/ido/life/enums/UserModelEnum;", "mModelList", "", "mScreenWidth", "", "dismissLoading", "", "getLayoutResId", "handleMessage", "message", "Lcom/ido/life/base/BaseMessage;", "initData", "initLabelLanguage", "initViews", "onClick", "v", "Landroid/view/View;", "onGetFourWeekStepSuccess", "list", "Lcom/ido/life/bean/BaseCharBean;", "onGetUserMealSuccess", "", "onGetWeekAllCalorieSuccess", "Lcom/ido/life/bean/BarChartPoint;", "onGetWeekStepSuccess", "onGetWeekStrengthSuccess", "onGetWeekWalkHourSuccess", "screenShotFailed", "screenShotSuccess", "setAllDayCalorieBottomLabelList", "setAllDayCalorieRightLabelList", "setAllDayCalorieXMaxValue", "xMax", "setAllDayCalorieXMinValue", "xMin", "setAllDayCalorieYMaxValue", "yMax", "setAllDayCalorieYMinValue", "yMin", "setCurrentWeekStepBottomLabelList", "setCurrentWeekStepRightLabelList", "setCurrentWeekStepXMaxValue", "setCurrentWeekStepXMinValue", "setCurrentWeekStepYMaxValue", "setCurrentWeekStepYMinValue", "setFourWeekStepBottomLabelList", "setFourWeekStepRightLabelList", "setFourWeekStepXMaxValue", "setFourWeekStepXMinValue", "setFourWeekStepYMaxValue", "setFourWeekStepYMinValue", "setListener", "setStepTarget", "stepTarget", "setStrengthBottomLabelList", "setStrengthRightLabelList", "setStrengthXMaxValue", "setStrengthXMinValue", "setStrengthYMaxValue", "setStrengthYMinValue", "setWalkHourBottomLabelList", "setWalkHourRightLabelList", "setWalkHourXMaxValue", "setWalkHourXMinValue", "setWalkHourYMaxValue", "setWalkHourYMinValue", "showLoading", "showShareDialog", "updateCalorieByDay", "calorie", "updateCurrentWeekStepDesc", "maxStep", "dayStep", "updateFourWeekStepDesc", "percent", "lower", "", "updateStrengthByDay", "min", "updateUserAvatar", "avatar", "updateUserNickName", "nickName", "updateWalkHourByDay", "hour", "updateWeekDateArea", "dateArea", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class HealthReportNewActivity extends BaseActivity<HealthReportNewPresenter> implements View.OnClickListener, HealthReportNewView, IHandlerEventBus {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DATE = "date";
    private HashMap _$_findViewCache;
    private CommonRecyclerViewAdapter<UserModelEnum> mModelAdapter;
    private int mScreenWidth;
    private List<UserModelEnum> mModelList = new ArrayList();
    private String mDisableColor = "#55000000";

    public static final String getDATE() {
        Companion companion = INSTANCE;
        return DATE;
    }

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

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
        return R.layout.activity_health_report_new_layout;
    }

    /* JADX INFO: compiled from: HealthReportNewActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/ido/life/module/user/healthreport/HealthReportNewActivity$Companion;", "", "()V", "DATE", "", "DATE$annotations", "getDATE", "()Ljava/lang/String;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void DATE$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getDATE() {
            return HealthReportNewActivity.DATE;
        }
    }

    public static final /* synthetic */ HealthReportNewPresenter access$getMPresenter$p(HealthReportNewActivity healthReportNewActivity) {
        return (HealthReportNewPresenter) healthReportNewActivity.mPresenter;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        setStatusBarColor(getResources().getColor(R.color.color_2368DD));
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_parent)).setBackgroundColor(0);
        this.mTitleBar.initLayout(1);
        this.mTitleBar.setRightImg(R.mipmap.share_report);
        this.mTitleBar.setBarBackground(0);
        this.mTitleBar.setTitleColor(-1);
        this.mTitleBar.setLeftImgTint(ColorStateList.valueOf(-1));
        ImageView img_top_bg = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_top_bg);
        Intrinsics.checkExpressionValueIsNotNull(img_top_bg, "img_top_bg");
        ViewGroup.LayoutParams layoutParams = img_top_bg.getLayoutParams();
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        layoutParams.width = resources.getDisplayMetrics().widthPixels;
        layoutParams.height = MathKt.roundToInt(layoutParams.width * 1.44133f);
        ImageView img_top_bg2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_top_bg);
        Intrinsics.checkExpressionValueIsNotNull(img_top_bg2, "img_top_bg");
        img_top_bg2.setLayoutParams(layoutParams);
        Glide.with((FragmentActivity) this).load(Integer.valueOf(R.mipmap.health_report_new_bg)).asBitmap().into((ImageView) _$_findCachedViewById(com.ido.life.R.id.img_top_bg));
        LinearLayout lay_bottom_bg = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_bottom_bg);
        Intrinsics.checkExpressionValueIsNotNull(lay_bottom_bg, "lay_bottom_bg");
        ViewGroup.LayoutParams layoutParams2 = lay_bottom_bg.getLayoutParams();
        Resources resources2 = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources2, "resources");
        layoutParams2.width = resources2.getDisplayMetrics().widthPixels;
        layoutParams2.height = MathKt.roundToInt(layoutParams2.width * 0.48f);
        LinearLayout lay_bottom_bg2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_bottom_bg);
        Intrinsics.checkExpressionValueIsNotNull(lay_bottom_bg2, "lay_bottom_bg");
        lay_bottom_bg2.setLayoutParams(layoutParams2);
        setListener();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        this.mScreenWidth = resources.getDisplayMetrics().widthPixels - getResources().getDimensionPixelSize(R.dimen.sw_dp_48);
        String it = getIntent().getStringExtra(DATE);
        if (it != null) {
            HealthReportNewPresenter healthReportNewPresenter = (HealthReportNewPresenter) this.mPresenter;
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            healthReportNewPresenter.setWeekStartDate(it);
        }
        ((HealthReportNewPresenter) this.mPresenter).startLoadData();
        if (((HealthReportNewPresenter) this.mPresenter).hasNextWeek()) {
            return;
        }
        LinearLayout lay_date_right = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_date_right);
        Intrinsics.checkExpressionValueIsNotNull(lay_date_right, "lay_date_right");
        lay_date_right.setEnabled(false);
        ImageView img_date_right = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_date_right);
        Intrinsics.checkExpressionValueIsNotNull(img_date_right, "img_date_right");
        img_date_right.setImageTintList(ColorStateList.valueOf(Color.parseColor(this.mDisableColor)));
    }

    private final void setListener() {
        HealthReportNewActivity healthReportNewActivity = this;
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_date_left)).setOnClickListener(healthReportNewActivity);
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_date_right)).setOnClickListener(healthReportNewActivity);
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_feedback_action)).setOnClickListener(healthReportNewActivity);
        this.mTitleBar.setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.user.healthreport.HealthReportNewActivity.setListener.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthReportNewActivity.this.showShareDialog();
            }
        });
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(R.string.health_report);
        TextView tv_title_all_calorie = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_all_calorie);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_all_calorie, "tv_title_all_calorie");
        tv_title_all_calorie.setText(getLanguageText(R.string.home_calorie_all));
        TextView tv_title_strength = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_strength);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_strength, "tv_title_strength");
        tv_title_strength.setText(getLanguageText(R.string.home_card_activity_stronger_walk));
        TextView tv_title_walk = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_walk);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_walk, "tv_title_walk");
        tv_title_walk.setText(getLanguageText(R.string.detail_walk_hour));
        TextView tv_title_step = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_step);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_step, "tv_title_step");
        tv_title_step.setText(getLanguageText(R.string.home_steps_tittle));
        TextView tv_step_week_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_step_week_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_step_week_desc, "tv_step_week_desc");
        String languageText = getLanguageText(R.string.format_health_report_step_info);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string…_health_report_step_info)");
        Object[] objArr = {Integer.valueOf(((HealthReportNewPresenter) this.mPresenter).getMCurrentWeekMaxStep()), Integer.valueOf(((HealthReportNewPresenter) this.mPresenter).getMCurrentWeekPerStep())};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        tv_step_week_desc.setText(str);
        TextView tv_step_four_week_title = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_step_four_week_title);
        Intrinsics.checkExpressionValueIsNotNull(tv_step_four_week_title, "tv_step_four_week_title");
        tv_step_four_week_title.setText(getLanguageText(R.string.home_steps_tittle));
        TextView tv_has_problem = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_has_problem);
        Intrinsics.checkExpressionValueIsNotNull(tv_has_problem, "tv_has_problem");
        tv_has_problem.setText(getLanguageText(R.string.has_proplem_to_data));
        TextView tv_feedback_action = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_feedback_action);
        Intrinsics.checkExpressionValueIsNotNull(tv_feedback_action, "tv_feedback_action");
        tv_feedback_action.setText(getLanguageText(R.string.commit_feedback));
        TextView tv_qr_down = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_qr_down);
        Intrinsics.checkExpressionValueIsNotNull(tv_qr_down, "tv_qr_down");
        tv_qr_down.setText(getLanguageText(R.string.sport_share_qr_text));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_date_left) {
            ((HealthReportNewPresenter) this.mPresenter).switchToPreviourWeek();
            LinearLayout lay_date_right = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_date_right);
            Intrinsics.checkExpressionValueIsNotNull(lay_date_right, "lay_date_right");
            if (lay_date_right.isEnabled()) {
                return;
            }
            LinearLayout lay_date_right2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_date_right);
            Intrinsics.checkExpressionValueIsNotNull(lay_date_right2, "lay_date_right");
            lay_date_right2.setEnabled(true);
            ImageView img_date_right = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_date_right);
            Intrinsics.checkExpressionValueIsNotNull(img_date_right, "img_date_right");
            img_date_right.setImageTintList(ColorStateList.valueOf(-1));
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_date_right) {
            if (((HealthReportNewPresenter) this.mPresenter).hasNextWeek()) {
                ((HealthReportNewPresenter) this.mPresenter).switchToNextWeek();
                if (((HealthReportNewPresenter) this.mPresenter).hasNextWeek()) {
                    return;
                }
                LinearLayout lay_date_right3 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_date_right);
                Intrinsics.checkExpressionValueIsNotNull(lay_date_right3, "lay_date_right");
                lay_date_right3.setEnabled(false);
                ImageView img_date_right2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_date_right);
                Intrinsics.checkExpressionValueIsNotNull(img_date_right2, "img_date_right");
                img_date_right2.setImageTintList(ColorStateList.valueOf(Color.parseColor(this.mDisableColor)));
                return;
            }
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_feedback_action) {
            startActivity(new Intent(this, (Class<?>) FeedbackActivity.class));
        }
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void updateWeekDateArea(String dateArea) {
        Intrinsics.checkParameterIsNotNull(dateArea, "dateArea");
        TextView tv_date_area = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_date_area);
        Intrinsics.checkExpressionValueIsNotNull(tv_date_area, "tv_date_area");
        tv_date_area.setText(dateArea);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void updateUserAvatar(String avatar) {
        String str = avatar;
        if (str == null || str.length() == 0) {
            return;
        }
        ImageLoaderUtil.loadCircleImage((ImageView) _$_findCachedViewById(com.ido.life.R.id.img_user_avata), avatar, R.mipmap.ic_avatar_default);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void updateUserNickName(String nickName) {
        String str = nickName;
        if (str == null || str.length() == 0) {
            return;
        }
        TextView tv_nick_name = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_nick_name);
        Intrinsics.checkExpressionValueIsNotNull(tv_nick_name, "tv_nick_name");
        tv_nick_name.setText(str);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void onGetUserMealSuccess(List<? extends UserModelEnum> list) {
        this.mModelList.clear();
        List<? extends UserModelEnum> list2 = list;
        if (list2 == null || list2.isEmpty()) {
            CommonRecyclerViewAdapter<UserModelEnum> commonRecyclerViewAdapter = this.mModelAdapter;
            if (commonRecyclerViewAdapter == null || commonRecyclerViewAdapter == null) {
                return;
            }
            commonRecyclerViewAdapter.notifyDataSetChanged();
            return;
        }
        this.mModelList.addAll(list2);
        CommonRecyclerViewAdapter<UserModelEnum> commonRecyclerViewAdapter2 = this.mModelAdapter;
        if (commonRecyclerViewAdapter2 != null) {
            if (commonRecyclerViewAdapter2 != null) {
                commonRecyclerViewAdapter2.notifyDataSetChanged();
            }
        } else {
            this.mModelAdapter = new CommonRecyclerViewAdapter<UserModelEnum>(this, R.layout.item_health_report_medal_layout, this.mModelList) { // from class: com.ido.life.module.user.healthreport.HealthReportNewActivity.onGetUserMealSuccess.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
                public void convert(CommonRecyclerViewHolder holder, UserModelEnum modelEnum, int position) {
                    View view;
                    Intrinsics.checkParameterIsNotNull(modelEnum, "modelEnum");
                    if (holder != null && (view = holder.itemView) != null) {
                        view.setMinimumWidth((int) (HealthReportNewActivity.this.mScreenWidth / 5.5f));
                    }
                    ImageView imageView = holder != null ? (ImageView) holder.getView(R.id.img) : null;
                    if (imageView == null) {
                        Intrinsics.throwNpe();
                    }
                    imageView.setImageResource(modelEnum.getMedalImageId_KM());
                }
            };
            RecyclerView recycler_medal = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_medal);
            Intrinsics.checkExpressionValueIsNotNull(recycler_medal, "recycler_medal");
            recycler_medal.setAdapter(this.mModelAdapter);
        }
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setAllDayCalorieBottomLabelList(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_calorie)).setLabelXList(list);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setAllDayCalorieRightLabelList(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_calorie)).setLabelYRightList(list);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setAllDayCalorieXMaxValue(int xMax) {
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_calorie)).setXMaxValue(xMax);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setAllDayCalorieXMinValue(int xMin) {
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_calorie)).setXMinValue(xMin);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setAllDayCalorieYMaxValue(int yMax) {
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_calorie)).setYMaxValue(yMax);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setAllDayCalorieYMinValue(int yMin) {
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_calorie)).setYMinValue(yMin);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setStrengthBottomLabelList(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_strength)).setLabelXList(list);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setStrengthRightLabelList(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_strength)).setLabelYRightList(list);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setStrengthXMaxValue(int xMax) {
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_strength)).setXMaxValue(xMax);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setStrengthXMinValue(int xMin) {
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_strength)).setXMinValue(xMin);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setStrengthYMinValue(int yMin) {
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_strength)).setYMinValue(yMin);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setStrengthYMaxValue(int yMax) {
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_strength)).setYMaxValue(yMax);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setWalkHourBottomLabelList(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk)).setLabelXList(list);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setWalkHourRightLabelList(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk)).setLabelYRightList(list);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setWalkHourXMinValue(int xMin) {
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk)).setXMinValue(xMin);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setWalkHourXMaxValue(int xMax) {
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk)).setXMaxValue(xMax);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setWalkHourYMinValue(int yMin) {
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk)).setYMinValue(yMin);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setWalkHourYMaxValue(int yMax) {
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk)).setYMaxValue(yMax);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setCurrentWeekStepBottomLabelList(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        ((HealthReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_step_week)).setLabelXList(list);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setCurrentWeekStepRightLabelList(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        ((HealthReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_step_week)).setLabelYRightList(list);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setCurrentWeekStepXMinValue(int xMin) {
        ((HealthReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_step_week)).setXMinValue(xMin);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setCurrentWeekStepXMaxValue(int xMax) {
        ((HealthReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_step_week)).setXMaxValue(xMax);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setCurrentWeekStepYMinValue(int yMin) {
        ((HealthReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_step_week)).setYMinValue(yMin);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setCurrentWeekStepYMaxValue(int yMax) {
        ((HealthReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_step_week)).setYMaxValue(yMax);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setStepTarget(int stepTarget) {
        ((HealthReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_step_week)).setGoalLineList(CollectionsKt.mutableListOf(new GoalLineInfo(0, stepTarget, stepTarget, "")));
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setFourWeekStepBottomLabelList(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        ((HealthReportLineBar) _$_findCachedViewById(com.ido.life.R.id.chart_four_week)).setLabelXList(list);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setFourWeekStepRightLabelList(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        ((HealthReportLineBar) _$_findCachedViewById(com.ido.life.R.id.chart_four_week)).setLabelYRightList(list);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setFourWeekStepXMinValue(int xMin) {
        ((HealthReportLineBar) _$_findCachedViewById(com.ido.life.R.id.chart_four_week)).setXMinValue(xMin);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setFourWeekStepXMaxValue(int xMax) {
        ((HealthReportLineBar) _$_findCachedViewById(com.ido.life.R.id.chart_four_week)).setXMaxValue(xMax);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setFourWeekStepYMaxValue(int yMax) {
        ((HealthReportLineBar) _$_findCachedViewById(com.ido.life.R.id.chart_four_week)).setYMaxValue(yMax);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void setFourWeekStepYMinValue(int yMin) {
        ((HealthReportLineBar) _$_findCachedViewById(com.ido.life.R.id.chart_four_week)).setYMinValue(yMin);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void updateCalorieByDay(int calorie) {
        TextView tv_all_calorie_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_all_calorie_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_all_calorie_desc, "tv_all_calorie_desc");
        String languageText = getLanguageText(R.string.health_report_all_calorie_desc_format);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string…_all_calorie_desc_format)");
        Object[] objArr = {Integer.valueOf(calorie)};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        tv_all_calorie_desc.setText(str);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void updateStrengthByDay(int min) {
        TextView tv_strength_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_strength_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_strength_desc, "tv_strength_desc");
        String languageText = getLanguageText(R.string.health_report_strength_desc_format);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string…ort_strength_desc_format)");
        Object[] objArr = {Integer.valueOf(min)};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        tv_strength_desc.setText(str);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void updateWalkHourByDay(int hour) {
        TextView tv_walk_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_walk_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_walk_desc, "tv_walk_desc");
        String languageText = getLanguageText(R.string.health_report_walk_desc_format);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string…_report_walk_desc_format)");
        Object[] objArr = {Integer.valueOf(hour)};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        tv_walk_desc.setText(str);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void updateCurrentWeekStepDesc(int maxStep, int dayStep) {
        TextView tv_step_week_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_step_week_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_step_week_desc, "tv_step_week_desc");
        String languageText = getLanguageText(R.string.format_health_report_step_info);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string…_health_report_step_info)");
        Object[] objArr = {Integer.valueOf(maxStep), Integer.valueOf(dayStep)};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        tv_step_week_desc.setText(str);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void updateFourWeekStepDesc(int percent, boolean lower) {
        if (percent == Integer.MAX_VALUE) {
            TextView tv_step_compare_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_step_compare_desc);
            Intrinsics.checkExpressionValueIsNotNull(tv_step_compare_desc, "tv_step_compare_desc");
            tv_step_compare_desc.setText(getLanguageText(R.string.health_report_compare_has_no_data));
            return;
        }
        if (percent > 0) {
            if (percent > 100) {
                percent = 100;
            }
            TextView tv_step_compare_desc2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_step_compare_desc);
            Intrinsics.checkExpressionValueIsNotNull(tv_step_compare_desc2, "tv_step_compare_desc");
            String languageText = getLanguageText(R.string.health_report_compare_higher_format);
            Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string…rt_compare_higher_format)");
            StringBuilder sb = new StringBuilder();
            sb.append(percent);
            sb.append('%');
            Object[] objArr = {sb.toString()};
            String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
            tv_step_compare_desc2.setText(str);
            return;
        }
        if (lower) {
            if (percent > 100) {
                percent = 100;
            }
            TextView tv_step_compare_desc3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_step_compare_desc);
            Intrinsics.checkExpressionValueIsNotNull(tv_step_compare_desc3, "tv_step_compare_desc");
            String languageText2 = getLanguageText(R.string.health_report_compare_lower_format);
            Intrinsics.checkExpressionValueIsNotNull(languageText2, "getLanguageText(R.string…ort_compare_lower_format)");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(-percent);
            sb2.append('%');
            Object[] objArr2 = {sb2.toString()};
            String str2 = String.format(languageText2, Arrays.copyOf(objArr2, objArr2.length));
            Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(this, *args)");
            tv_step_compare_desc3.setText(str2);
            return;
        }
        TextView tv_step_compare_desc4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_step_compare_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_step_compare_desc4, "tv_step_compare_desc");
        String languageText3 = getLanguageText(R.string.health_report_compare_higher_format);
        Intrinsics.checkExpressionValueIsNotNull(languageText3, "getLanguageText(R.string…rt_compare_higher_format)");
        Object[] objArr3 = {"0%"};
        String str3 = String.format(languageText3, Arrays.copyOf(objArr3, objArr3.length));
        Intrinsics.checkNotNullExpressionValue(str3, "java.lang.String.format(this, *args)");
        tv_step_compare_desc4.setText(str3);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void onGetWeekAllCalorieSuccess(List<BarChartPoint> list) {
        HealthSmallReportChartBar chart_calorie = (HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_calorie);
        Intrinsics.checkExpressionValueIsNotNull(chart_calorie, "chart_calorie");
        chart_calorie.setList(list);
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_calorie)).refreshChart(true);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void onGetWeekStrengthSuccess(List<BarChartPoint> list) {
        HealthSmallReportChartBar chart_strength = (HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_strength);
        Intrinsics.checkExpressionValueIsNotNull(chart_strength, "chart_strength");
        chart_strength.setList(list);
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_strength)).refreshChart(true);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void onGetWeekWalkHourSuccess(List<BarChartPoint> list) {
        HealthSmallReportChartBar chart_walk = (HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
        Intrinsics.checkExpressionValueIsNotNull(chart_walk, "chart_walk");
        chart_walk.setList(list);
        ((HealthSmallReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk)).refreshChart(true);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void onGetWeekStepSuccess(List<BarChartPoint> list) {
        HealthReportChartBar chart_step_week = (HealthReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_step_week);
        Intrinsics.checkExpressionValueIsNotNull(chart_step_week, "chart_step_week");
        chart_step_week.setList(list);
        ((HealthReportChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_step_week)).refreshChart(true);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void onGetFourWeekStepSuccess(List<BaseCharBean> list) {
        HealthReportLineBar chart_four_week = (HealthReportLineBar) _$_findCachedViewById(com.ido.life.R.id.chart_four_week);
        Intrinsics.checkExpressionValueIsNotNull(chart_four_week, "chart_four_week");
        chart_four_week.setList(list);
        ((HealthReportLineBar) _$_findCachedViewById(com.ido.life.R.id.chart_four_week)).refreshChart(true);
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void showLoading() {
        showLoadingDialog();
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void dismissLoading() {
        dismissLoadingDialog();
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void screenShotSuccess() {
        dismissLoading();
        HealthReportShareDialogFragment healthReportShareDialogFragmentNewInstance = HealthReportShareDialogFragment.INSTANCE.newInstance();
        healthReportShareDialogFragmentNewInstance.setCancelable(true);
        healthReportShareDialogFragmentNewInstance.show(getSupportFragmentManager());
        healthReportShareDialogFragmentNewInstance.setOnDismissionListener(new BaseDialogFragment.OnDismissionListener() { // from class: com.ido.life.module.user.healthreport.HealthReportNewActivity.screenShotSuccess.1
            @Override // com.ido.common.base.BaseDialogFragment.OnDismissionListener
            public final void onDismission() {
                LinearLayout lay_date_left = (LinearLayout) HealthReportNewActivity.this._$_findCachedViewById(com.ido.life.R.id.lay_date_left);
                Intrinsics.checkExpressionValueIsNotNull(lay_date_left, "lay_date_left");
                lay_date_left.setVisibility(0);
                LinearLayout lay_date_right = (LinearLayout) HealthReportNewActivity.this._$_findCachedViewById(com.ido.life.R.id.lay_date_right);
                Intrinsics.checkExpressionValueIsNotNull(lay_date_right, "lay_date_right");
                lay_date_right.setVisibility(0);
                LinearLayout layout_left = (LinearLayout) HealthReportNewActivity.this._$_findCachedViewById(com.ido.life.R.id.layout_left);
                Intrinsics.checkExpressionValueIsNotNull(layout_left, "layout_left");
                layout_left.setVisibility(0);
                LinearLayout layout_right = (LinearLayout) HealthReportNewActivity.this._$_findCachedViewById(com.ido.life.R.id.layout_right);
                Intrinsics.checkExpressionValueIsNotNull(layout_right, "layout_right");
                layout_right.setVisibility(0);
            }
        });
    }

    @Override // com.ido.life.module.user.healthreport.HealthReportNewView
    public void screenShotFailed() {
        dismissLoading();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showShareDialog() {
        LinearLayout lay_date_left = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_date_left);
        Intrinsics.checkExpressionValueIsNotNull(lay_date_left, "lay_date_left");
        lay_date_left.setVisibility(8);
        LinearLayout lay_date_right = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_date_right);
        Intrinsics.checkExpressionValueIsNotNull(lay_date_right, "lay_date_right");
        lay_date_right.setVisibility(8);
        LinearLayout layout_left = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_left);
        Intrinsics.checkExpressionValueIsNotNull(layout_left, "layout_left");
        layout_left.setVisibility(8);
        LinearLayout layout_right = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_right);
        Intrinsics.checkExpressionValueIsNotNull(layout_right, "layout_right");
        layout_right.setVisibility(8);
        showLoading();
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_right)).postDelayed(new Runnable() { // from class: com.ido.life.module.user.healthreport.HealthReportNewActivity.showShareDialog.1
            @Override // java.lang.Runnable
            public final void run() {
                HealthReportNewPresenter healthReportNewPresenterAccess$getMPresenter$p = HealthReportNewActivity.access$getMPresenter$p(HealthReportNewActivity.this);
                ConstraintLayout lay_outer = (ConstraintLayout) HealthReportNewActivity.this._$_findCachedViewById(com.ido.life.R.id.lay_outer);
                Intrinsics.checkExpressionValueIsNotNull(lay_outer, "lay_outer");
                healthReportNewPresenterAccess$getMPresenter$p.shotLongScreen(lay_outer);
            }
        }, 2000L);
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage<?> message) {
        if (message == null) {
            return;
        }
        super.handleMessage(message);
        if (message.getType() != 850) {
            return;
        }
        HealthReportNewPresenter healthReportNewPresenter = (HealthReportNewPresenter) this.mPresenter;
        ConstraintLayout lay_outer = (ConstraintLayout) _$_findCachedViewById(com.ido.life.R.id.lay_outer);
        Intrinsics.checkExpressionValueIsNotNull(lay_outer, "lay_outer");
        healthReportNewPresenter.saveReportPhoto(lay_outer);
    }
}