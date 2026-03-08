package com.ido.life.module.user.set.target;

import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.app.NotificationCompat;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.boat.Xtend.two.R;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.NormalToast;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.UnitUtil;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: SettingTargetActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007H\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007H\u0002J\b\u0010\u001d\u001a\u00020\u0012H\u0016J\b\u0010\u001e\u001a\u00020\u0007H\u0014J\b\u0010\u001f\u001a\u00020\u0012H\u0016J\u0010\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u0012H\u0014J\b\u0010$\u001a\u00020\u0012H\u0016J\u0010\u0010%\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\"\u0010&\u001a\u00020\u00122\b\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020+H\u0016J\u0012\u0010,\u001a\u00020\u00122\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0012\u0010-\u001a\u00020\u00122\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0012\u0010.\u001a\u00020\u00122\b\u0010/\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u00100\u001a\u00020\u0012H\u0016J\b\u00101\u001a\u00020\u0012H\u0016R\u0014\u0010\u0006\u001a\u00020\u0007X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0007X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\t¨\u00062"}, d2 = {"Lcom/ido/life/module/user/set/target/SettingTargetActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/user/set/target/SettingTargetPresenter;", "Landroid/widget/SeekBar$OnSeekBarChangeListener;", "Lcom/ido/life/module/user/set/target/ISettingTargetView;", "()V", "STEP_PROGRESS_VALUE", "", "getSTEP_PROGRESS_VALUE", "()I", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "WEIGHT_PROGRESS_VALUE", "getWEIGHT_PROGRESS_VALUE", "addWeightGoal", "", "view", "Landroid/view/View;", "caluteRunMin", "step", "caluteStandartWeightMax", "", "height", "", "caluteStandartWeightMin", "caluteWalkMin", "dismissLoading", "getLayoutResId", "getTargetInfoFailed", "getTargetInfoSuccess", "target", "Lcom/ido/life/database/model/UserTargetNew;", "initLabelLanguage", "initViews", "minusWeightGoal", "onProgressChanged", "seekBar", "Landroid/widget/SeekBar;", NotificationCompat.CATEGORY_PROGRESS, "fromUser", "", "onStartTrackingTouch", "onStopTrackingTouch", "setTargetInfoFailed", "errMsg", "setTargetInfoSuccess", "showLoading", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SettingTargetActivity extends BaseActivity<SettingTargetPresenter> implements SeekBar.OnSeekBarChangeListener, ISettingTargetView {
    private HashMap _$_findViewCache;
    private final String TAG = SettingTargetActivity.class.getSimpleName();
    private final int STEP_PROGRESS_VALUE = 1000;
    private final int WEIGHT_PROGRESS_VALUE = 1;

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
        return R.layout.activity_setting_target_layout;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public static final /* synthetic */ SettingTargetPresenter access$getMPresenter$p(SettingTargetActivity settingTargetActivity) {
        return (SettingTargetPresenter) settingTargetActivity.mPresenter;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final int getSTEP_PROGRESS_VALUE() {
        return this.STEP_PROGRESS_VALUE;
    }

    public final int getWEIGHT_PROGRESS_VALUE() {
        return this.WEIGHT_PROGRESS_VALUE;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        int pound2Kg;
        int kg2Pound;
        super.initViews();
        this.mTitleBar.initLayout(1);
        this.mTitleBar.setRightImg(R.mipmap.save);
        SettingTargetActivity settingTargetActivity = this;
        ((AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_step)).setOnSeekBarChangeListener(settingTargetActivity);
        ((AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_weight)).setOnSeekBarChangeListener(settingTargetActivity);
        AppCompatSeekBar seek_step = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_step);
        Intrinsics.checkExpressionValueIsNotNull(seek_step, "seek_step");
        seek_step.setMax(20000 / this.STEP_PROGRESS_VALUE);
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        int unitSet = runTimeUtil.getUnitSet();
        RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
        UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(runTimeUtil2.getUserId());
        if (unitSet == 1) {
            AppCompatSeekBar seek_weight = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_weight);
            Intrinsics.checkExpressionValueIsNotNull(seek_weight, "seek_weight");
            seek_weight.setMax(GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN / this.WEIGHT_PROGRESS_VALUE);
            if (userTargetNewQueryUserLatestTarget == null) {
                pound2Kg = 10;
            } else if (userTargetNewQueryUserLatestTarget.getWeightUnit() == 1) {
                pound2Kg = MathKt.roundToInt(userTargetNewQueryUserLatestTarget.getWeight());
            } else {
                pound2Kg = (int) UnitUtil.getPound2Kg(userTargetNewQueryUserLatestTarget.getWeight());
            }
            AppCompatSeekBar seek_weight2 = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_weight);
            Intrinsics.checkExpressionValueIsNotNull(seek_weight2, "seek_weight");
            seek_weight2.setProgress((pound2Kg - 10) / this.WEIGHT_PROGRESS_VALUE);
        } else {
            AppCompatSeekBar seek_weight3 = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_weight);
            Intrinsics.checkExpressionValueIsNotNull(seek_weight3, "seek_weight");
            seek_weight3.setMax(529 / this.WEIGHT_PROGRESS_VALUE);
            if (userTargetNewQueryUserLatestTarget == null) {
                kg2Pound = 22;
            } else if (userTargetNewQueryUserLatestTarget.getWeightUnit() == 2) {
                kg2Pound = MathKt.roundToInt(userTargetNewQueryUserLatestTarget.getWeight());
            } else {
                kg2Pound = (int) UnitUtil.getKg2Pound(0);
            }
            AppCompatSeekBar seek_weight4 = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_weight);
            Intrinsics.checkExpressionValueIsNotNull(seek_weight4, "seek_weight");
            seek_weight4.setProgress((kg2Pound - 22) / this.WEIGHT_PROGRESS_VALUE);
        }
        this.mTitleBar.setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.user.set.target.SettingTargetActivity.initViews.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int progress;
                RunTimeUtil runTimeUtil3 = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil3, "RunTimeUtil.getInstance()");
                int unitSet2 = runTimeUtil3.getUnitSet();
                if (unitSet2 == 2) {
                    int weight_progress_value = SettingTargetActivity.this.getWEIGHT_PROGRESS_VALUE();
                    AppCompatSeekBar seek_weight5 = (AppCompatSeekBar) SettingTargetActivity.this._$_findCachedViewById(com.ido.life.R.id.seek_weight);
                    Intrinsics.checkExpressionValueIsNotNull(seek_weight5, "seek_weight");
                    progress = (weight_progress_value * seek_weight5.getProgress()) + 22;
                } else {
                    int weight_progress_value2 = SettingTargetActivity.this.getWEIGHT_PROGRESS_VALUE();
                    AppCompatSeekBar seek_weight6 = (AppCompatSeekBar) SettingTargetActivity.this._$_findCachedViewById(com.ido.life.R.id.seek_weight);
                    Intrinsics.checkExpressionValueIsNotNull(seek_weight6, "seek_weight");
                    progress = (weight_progress_value2 * seek_weight6.getProgress()) + 10;
                }
                SettingTargetPresenter settingTargetPresenterAccess$getMPresenter$p = SettingTargetActivity.access$getMPresenter$p(SettingTargetActivity.this);
                int step_progress_value = SettingTargetActivity.this.getSTEP_PROGRESS_VALUE();
                AppCompatSeekBar seek_step2 = (AppCompatSeekBar) SettingTargetActivity.this._$_findCachedViewById(com.ido.life.R.id.seek_step);
                Intrinsics.checkExpressionValueIsNotNull(seek_step2, "seek_step");
                settingTargetPresenterAccess$getMPresenter$p.saveTargetInfo((step_progress_value * seek_step2.getProgress()) + 5000, progress, unitSet2);
            }
        });
        ((SettingTargetPresenter) this.mPresenter).getTargetInfo();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        float fInch2Cm;
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.my_target));
        TextView tv_title_step = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_step);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_step, "tv_title_step");
        tv_title_step.setText(getLanguageText(R.string.mine_goal_steps));
        TextView tv_title_walk = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_walk);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_walk, "tv_title_walk");
        tv_title_walk.setText(getLanguageText(R.string.title_setting_target_step_min));
        TextView tv_title_run = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_run);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_run, "tv_title_run");
        tv_title_run.setText(getLanguageText(R.string.sport_tab_run));
        TextView tv_title_weight = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_weight);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_weight, "tv_title_weight");
        tv_title_weight.setText(getLanguageText(R.string.me_weight_goal));
        TextView tv_weight_info = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_weight_info);
        Intrinsics.checkExpressionValueIsNotNull(tv_weight_info, "tv_weight_info");
        tv_weight_info.setText(getLanguageText(R.string.setting_target_weight_info));
        TextView tv_step_unit = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_step_unit);
        Intrinsics.checkExpressionValueIsNotNull(tv_step_unit, "tv_step_unit");
        tv_step_unit.setText(getLanguageText(R.string.public_sport_step));
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        int unitSet = runTimeUtil.getUnitSet();
        if (unitSet == 1) {
            TextView tv_weight_unit = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_weight_unit);
            Intrinsics.checkExpressionValueIsNotNull(tv_weight_unit, "tv_weight_unit");
            tv_weight_unit.setText(getLanguageText(R.string.weight_kg_unit_short));
            MediumTextView tv_weight = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_weight);
            Intrinsics.checkExpressionValueIsNotNull(tv_weight, "tv_weight");
            int i = this.WEIGHT_PROGRESS_VALUE;
            AppCompatSeekBar seek_weight = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_weight);
            Intrinsics.checkExpressionValueIsNotNull(seek_weight, "seek_weight");
            tv_weight.setText(String.valueOf((i * seek_weight.getProgress()) + 10));
        } else {
            TextView tv_weight_unit2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_weight_unit);
            Intrinsics.checkExpressionValueIsNotNull(tv_weight_unit2, "tv_weight_unit");
            tv_weight_unit2.setText(getLanguageText(R.string.weight_pound_unit_short));
            MediumTextView tv_weight2 = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_weight);
            Intrinsics.checkExpressionValueIsNotNull(tv_weight2, "tv_weight");
            int i2 = this.WEIGHT_PROGRESS_VALUE;
            AppCompatSeekBar seek_weight2 = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_weight);
            Intrinsics.checkExpressionValueIsNotNull(seek_weight2, "seek_weight");
            tv_weight2.setText(String.valueOf((i2 * seek_weight2.getProgress()) + 22));
        }
        RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(runTimeUtil2.getUserId());
        if (userInfoQueryUserInfo != null) {
            if (userInfoQueryUserInfo.getHeightUnit() == 1) {
                fInch2Cm = userInfoQueryUserInfo.getHeight();
            } else {
                fInch2Cm = UnitUtil.inch2Cm(userInfoQueryUserInfo.getHeight());
            }
            float f2 = fInch2Cm / 100;
            if (unitSet == 1) {
                TextView tv_title_weight_area = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_weight_area);
                Intrinsics.checkExpressionValueIsNotNull(tv_title_weight_area, "tv_title_weight_area");
                String languageText = getLanguageText(R.string.setting_target_weight_area_km);
                Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string…ng_target_weight_area_km)");
                Object[] objArr = {Integer.valueOf(MathKt.roundToInt(caluteStandartWeightMin(f2))), Integer.valueOf(MathKt.roundToInt(caluteStandartWeightMax(f2)))};
                String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                tv_title_weight_area.setText(str);
            } else {
                TextView tv_title_weight_area2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_weight_area);
                Intrinsics.checkExpressionValueIsNotNull(tv_title_weight_area2, "tv_title_weight_area");
                String languageText2 = getLanguageText(R.string.setting_target_weight_area_em);
                Intrinsics.checkExpressionValueIsNotNull(languageText2, "getLanguageText(R.string…ng_target_weight_area_em)");
                Object[] objArr2 = {Integer.valueOf(MathKt.roundToInt(UnitUtil.getKg2Pound((float) caluteStandartWeightMin(f2)))), Integer.valueOf(MathKt.roundToInt(UnitUtil.getKg2Pound((float) caluteStandartWeightMax(f2))))};
                String str2 = String.format(languageText2, Arrays.copyOf(objArr2, objArr2.length));
                Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(this, *args)");
                tv_title_weight_area2.setText(str2);
            }
        }
        TextView tv_walk_min = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_walk_min);
        Intrinsics.checkExpressionValueIsNotNull(tv_walk_min, "tv_walk_min");
        StringBuilder sb = new StringBuilder();
        int i3 = this.STEP_PROGRESS_VALUE;
        AppCompatSeekBar seek_step = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_step);
        Intrinsics.checkExpressionValueIsNotNull(seek_step, "seek_step");
        sb.append(caluteWalkMin((i3 * seek_step.getProgress()) + 5000));
        sb.append(getLanguageText(R.string.min_unit_short));
        tv_walk_min.setText(sb.toString());
        TextView tv_run_min = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_run_min);
        Intrinsics.checkExpressionValueIsNotNull(tv_run_min, "tv_run_min");
        StringBuilder sb2 = new StringBuilder();
        int i4 = this.STEP_PROGRESS_VALUE;
        AppCompatSeekBar seek_step2 = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_step);
        Intrinsics.checkExpressionValueIsNotNull(seek_step2, "seek_step");
        sb2.append(caluteRunMin((i4 * seek_step2.getProgress()) + 5000));
        sb2.append(getLanguageText(R.string.min_unit_short));
        tv_run_min.setText(sb2.toString());
        MediumTextView tv_step = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_step);
        Intrinsics.checkExpressionValueIsNotNull(tv_step, "tv_step");
        int i5 = this.STEP_PROGRESS_VALUE;
        AppCompatSeekBar seek_step3 = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_step);
        Intrinsics.checkExpressionValueIsNotNull(seek_step3, "seek_step");
        tv_step.setText(String.valueOf((i5 * seek_step3.getProgress()) + 5000));
    }

    public final void minusWeightGoal(View view) {
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "minus 我的目标 体重的progress：" + ((AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_weight)).getProgress());
        AppCompatSeekBar seek_weight = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_weight);
        Intrinsics.checkExpressionValueIsNotNull(seek_weight, "seek_weight");
        int progress = seek_weight.getProgress() + (-1);
        MediumTextView tv_weight = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_weight);
        Intrinsics.checkExpressionValueIsNotNull(tv_weight, "tv_weight");
        CharSequence text = tv_weight.getText();
        if (text == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        }
        Integer numValueOf = Integer.valueOf((String) text);
        Intrinsics.checkExpressionValueIsNotNull(numValueOf, "Integer.valueOf((tv_weight.text as String))");
        int iIntValue = numValueOf.intValue() - 1;
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        int unitSet = runTimeUtil.getUnitSet();
        if (unitSet == 1 && iIntValue >= 10) {
            ((AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_weight)).setProgress(progress);
            ((MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_weight)).setText(String.valueOf(iIntValue));
        } else {
            if (unitSet != 2 || iIntValue < 22) {
                return;
            }
            ((AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_weight)).setProgress(progress);
            ((MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_weight)).setText(String.valueOf(iIntValue));
        }
    }

    public final void addWeightGoal(View view) {
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "add 我的目标 体重的progress：" + ((AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_weight)).getProgress());
        int progress = ((AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_weight)).getProgress() + 1;
        MediumTextView tv_weight = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_weight);
        Intrinsics.checkExpressionValueIsNotNull(tv_weight, "tv_weight");
        CharSequence text = tv_weight.getText();
        if (text == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        }
        Integer numValueOf = Integer.valueOf((String) text);
        Intrinsics.checkExpressionValueIsNotNull(numValueOf, "Integer.valueOf((tv_weight.text as String))");
        int iIntValue = numValueOf.intValue() + 1;
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        int unitSet = runTimeUtil.getUnitSet();
        if (unitSet == 1 && iIntValue <= 250) {
            AppCompatSeekBar seek_weight = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_weight);
            Intrinsics.checkExpressionValueIsNotNull(seek_weight, "seek_weight");
            seek_weight.setProgress(progress);
            MediumTextView tv_weight2 = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_weight);
            Intrinsics.checkExpressionValueIsNotNull(tv_weight2, "tv_weight");
            tv_weight2.setText(String.valueOf(iIntValue));
            return;
        }
        if (unitSet != 2 || iIntValue > 551) {
            return;
        }
        AppCompatSeekBar seek_weight2 = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_weight);
        Intrinsics.checkExpressionValueIsNotNull(seek_weight2, "seek_weight");
        seek_weight2.setProgress(progress);
        MediumTextView tv_weight3 = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_weight);
        Intrinsics.checkExpressionValueIsNotNull(tv_weight3, "tv_weight");
        tv_weight3.setText(String.valueOf(iIntValue));
    }

    private final double caluteStandartWeightMin(float height) {
        return ((double) 18.5f) * Math.pow(height, 2);
    }

    private final double caluteStandartWeightMax(float height) {
        return ((double) 23.9f) * Math.pow(height, 2);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            Integer numValueOf = seekBar != null ? Integer.valueOf(seekBar.getId()) : null;
            if (numValueOf != null && numValueOf.intValue() == R.id.seek_step) {
                MediumTextView tv_step = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_step);
                Intrinsics.checkExpressionValueIsNotNull(tv_step, "tv_step");
                tv_step.setText(String.valueOf((this.STEP_PROGRESS_VALUE * progress) + 5000));
                TextView tv_walk_min = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_walk_min);
                Intrinsics.checkExpressionValueIsNotNull(tv_walk_min, "tv_walk_min");
                tv_walk_min.setText(caluteWalkMin((this.STEP_PROGRESS_VALUE * progress) + 5000) + getLanguageText(R.string.min_unit_short));
                TextView tv_run_min = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_run_min);
                Intrinsics.checkExpressionValueIsNotNull(tv_run_min, "tv_run_min");
                tv_run_min.setText(caluteRunMin((this.STEP_PROGRESS_VALUE * progress) + 5000) + getLanguageText(R.string.min_unit_short));
                return;
            }
            if (numValueOf != null && numValueOf.intValue() == R.id.seek_weight) {
                RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
                if (runTimeUtil.getUnitSet() == 1) {
                    MediumTextView tv_weight = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_weight);
                    Intrinsics.checkExpressionValueIsNotNull(tv_weight, "tv_weight");
                    tv_weight.setText(String.valueOf((this.WEIGHT_PROGRESS_VALUE * progress) + 10));
                } else {
                    MediumTextView tv_weight2 = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_weight);
                    Intrinsics.checkExpressionValueIsNotNull(tv_weight2, "tv_weight");
                    tv_weight2.setText(String.valueOf((this.WEIGHT_PROGRESS_VALUE * progress) + 22));
                }
            }
        }
    }

    @Override // com.ido.life.module.user.set.target.ISettingTargetView
    public void setTargetInfoSuccess() {
        NormalToast.showToast(getLanguageText(R.string.setting_success), 2000);
        setResult(100);
        finish();
    }

    @Override // com.ido.life.module.user.set.target.ISettingTargetView
    public void setTargetInfoFailed(String errMsg) {
        String str = errMsg;
        if (str == null || str.length() == 0) {
            NormalToast.showToast(getLanguageText(R.string.setting_failed), 2000);
        } else {
            NormalToast.showToast(str, 2000);
        }
    }

    @Override // com.ido.life.module.user.set.target.ISettingTargetView
    public void getTargetInfoSuccess(UserTargetNew target) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        int step = target.getStep();
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        int unitSet = runTimeUtil.getUnitSet();
        if (target.getStep() < 5000 && target.getStep() > 25000) {
            step = 5000;
        }
        AppCompatSeekBar seek_step = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_step);
        Intrinsics.checkExpressionValueIsNotNull(seek_step, "seek_step");
        seek_step.setProgress((step + FitnessStatusCodes.SUCCESS_NO_DATA_SOURCES) / this.STEP_PROGRESS_VALUE);
        MediumTextView tv_step = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_step);
        Intrinsics.checkExpressionValueIsNotNull(tv_step, "tv_step");
        tv_step.setText(String.valueOf(step));
        TextView tv_walk_min = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_walk_min);
        Intrinsics.checkExpressionValueIsNotNull(tv_walk_min, "tv_walk_min");
        tv_walk_min.setText(caluteWalkMin(step) + getLanguageText(R.string.min_unit_short));
        TextView tv_run_min = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_run_min);
        Intrinsics.checkExpressionValueIsNotNull(tv_run_min, "tv_run_min");
        tv_run_min.setText(caluteRunMin(step) + getLanguageText(R.string.min_unit_short));
        int iRoundToInt = 22;
        int iRoundToInt2 = 10;
        if (unitSet == 1) {
            if (target.getWeightUnit() == unitSet) {
                if (target.getWeight() >= 10 || target.getWeight() <= 250) {
                    iRoundToInt2 = MathKt.roundToInt(target.getWeight());
                }
            } else if (target.getWeight() >= 22 || target.getWeight() <= 551) {
                iRoundToInt2 = MathKt.roundToInt(UnitUtil.getPound2Kg(target.getWeight()));
            }
            AppCompatSeekBar seek_weight = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_weight);
            Intrinsics.checkExpressionValueIsNotNull(seek_weight, "seek_weight");
            seek_weight.setProgress((iRoundToInt2 - 10) / this.WEIGHT_PROGRESS_VALUE);
        } else {
            if (target.getWeightUnit() == unitSet) {
                if (target.getWeight() >= 22 || target.getWeight() <= 551) {
                    iRoundToInt = MathKt.roundToInt(target.getWeight());
                }
            } else if (target.getWeight() >= 10 || target.getWeight() <= 250) {
                iRoundToInt = MathKt.roundToInt(UnitUtil.getKg2Pound(target.getWeight()));
            }
            iRoundToInt2 = iRoundToInt;
            AppCompatSeekBar seek_weight2 = (AppCompatSeekBar) _$_findCachedViewById(com.ido.life.R.id.seek_weight);
            Intrinsics.checkExpressionValueIsNotNull(seek_weight2, "seek_weight");
            seek_weight2.setProgress((iRoundToInt2 - 22) / this.WEIGHT_PROGRESS_VALUE);
        }
        MediumTextView tv_weight = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_weight);
        Intrinsics.checkExpressionValueIsNotNull(tv_weight, "tv_weight");
        tv_weight.setText(String.valueOf(iRoundToInt2));
    }

    private final int caluteWalkMin(int step) {
        return MathKt.roundToInt(((step * 1.0f) * 12) / 1400);
    }

    private final int caluteRunMin(int step) {
        return MathKt.roundToInt(((step * 1.0f) * 6) / 1400);
    }

    @Override // com.ido.life.module.user.set.target.ISettingTargetView
    public void getTargetInfoFailed() {
        NormalToast.showToast(getLanguageText(R.string.get_user_target_failed), 2000);
    }

    @Override // com.ido.life.base.IBaseLoadingView
    public void showLoading() {
        showLoadingDialog();
    }

    @Override // com.ido.life.base.IBaseLoadingView
    public void dismissLoading() {
        dismissLoadingDialog();
    }
}