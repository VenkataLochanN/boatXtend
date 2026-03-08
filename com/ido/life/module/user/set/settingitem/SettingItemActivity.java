package com.ido.life.module.user.set.settingitem;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.NormalToast;
import com.ido.life.database.model.TempUnitSetting;
import com.ido.life.database.model.TimeSet;
import com.ido.life.database.model.UnitSetting;
import com.ido.life.database.model.WeekStartSetting;
import com.ido.life.module.sport.setting.SportSettingPreference;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingItemActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u0007H\u0014J\b\u0010\u000f\u001a\u00020\rH\u0014J\b\u0010\u0010\u001a\u00020\rH\u0016J\b\u0010\u0011\u001a\u00020\rH\u0016J\u0012\u0010\u0012\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\rH\u0016J\b\u0010\u0016\u001a\u00020\rH\u0016J\b\u0010\u0017\u001a\u00020\rH\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/ido/life/module/user/set/settingitem/SettingItemActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/user/set/settingitem/SettingItemPresenter;", "Lcom/ido/life/module/user/set/settingitem/ISettingItemView;", "Landroid/view/View$OnClickListener;", "()V", "mType", "", "getMType", "()I", "setMType", "(I)V", "dismissLoading", "", "getLayoutResId", "initLabelLanguage", "initViews", "onBackPressed", "onClick", "v", "Landroid/view/View;", "onConfigFailed", "onConfigSuccess", "showLoading", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SettingItemActivity extends BaseActivity<SettingItemPresenter> implements ISettingItemView, View.OnClickListener {
    private HashMap _$_findViewCache;
    private int mType = -1;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String SETTING_TYPE = "setting_type";
    private static final int TYPE_SETTING_UNIT = 1;
    private static final int TYPE_SETTING_TIME = 2;
    private static final int TYPE_SETTING_WEEK_START = 3;
    private static final int TYPE_SETTING_MAP_ENGINE = 4;

    public static final String getSETTING_TYPE() {
        Companion companion = INSTANCE;
        return SETTING_TYPE;
    }

    public static final int getTYPE_SETTING_MAP_ENGINE() {
        Companion companion = INSTANCE;
        return TYPE_SETTING_MAP_ENGINE;
    }

    public static final int getTYPE_SETTING_TIME() {
        Companion companion = INSTANCE;
        return TYPE_SETTING_TIME;
    }

    public static final int getTYPE_SETTING_UNIT() {
        Companion companion = INSTANCE;
        return TYPE_SETTING_UNIT;
    }

    public static final int getTYPE_SETTING_WEEK_START() {
        Companion companion = INSTANCE;
        return TYPE_SETTING_WEEK_START;
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
        return R.layout.activity_setting_item_layout;
    }

    /* JADX INFO: compiled from: SettingItemActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u00020\t8\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u0002\u001a\u0004\b\u000f\u0010\fR\u001c\u0010\u0010\u001a\u00020\t8\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0002\u001a\u0004\b\u0012\u0010\fR\u001c\u0010\u0013\u001a\u00020\t8\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0002\u001a\u0004\b\u0015\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/ido/life/module/user/set/settingitem/SettingItemActivity$Companion;", "", "()V", "SETTING_TYPE", "", "SETTING_TYPE$annotations", "getSETTING_TYPE", "()Ljava/lang/String;", "TYPE_SETTING_MAP_ENGINE", "", "TYPE_SETTING_MAP_ENGINE$annotations", "getTYPE_SETTING_MAP_ENGINE", "()I", "TYPE_SETTING_TIME", "TYPE_SETTING_TIME$annotations", "getTYPE_SETTING_TIME", "TYPE_SETTING_UNIT", "TYPE_SETTING_UNIT$annotations", "getTYPE_SETTING_UNIT", "TYPE_SETTING_WEEK_START", "TYPE_SETTING_WEEK_START$annotations", "getTYPE_SETTING_WEEK_START", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void SETTING_TYPE$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_SETTING_MAP_ENGINE$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_SETTING_TIME$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_SETTING_UNIT$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_SETTING_WEEK_START$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getSETTING_TYPE() {
            return SettingItemActivity.SETTING_TYPE;
        }

        public final int getTYPE_SETTING_UNIT() {
            return SettingItemActivity.TYPE_SETTING_UNIT;
        }

        public final int getTYPE_SETTING_TIME() {
            return SettingItemActivity.TYPE_SETTING_TIME;
        }

        public final int getTYPE_SETTING_WEEK_START() {
            return SettingItemActivity.TYPE_SETTING_WEEK_START;
        }

        public final int getTYPE_SETTING_MAP_ENGINE() {
            return SettingItemActivity.TYPE_SETTING_MAP_ENGINE;
        }
    }

    public static final /* synthetic */ SettingItemPresenter access$getMPresenter$p(SettingItemActivity settingItemActivity) {
        return (SettingItemPresenter) settingItemActivity.mPresenter;
    }

    public final int getMType() {
        return this.mType;
    }

    public final void setMType(int i) {
        this.mType = i;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mType = getIntent().getIntExtra(SETTING_TYPE, this.mType);
        if (this.mType == -1) {
            finish();
            return;
        }
        ((SettingItemPresenter) this.mPresenter).setMSetType(this.mType);
        ((SettingItemPresenter) this.mPresenter).initConfig();
        int i = this.mType;
        if (i == TYPE_SETTING_UNIT) {
            this.mTitleBar.setTitle(getLanguageText(R.string.mine_unit_set));
            LinearLayout lay_setting_three = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_setting_three);
            Intrinsics.checkExpressionValueIsNotNull(lay_setting_three, "lay_setting_three");
            lay_setting_three.setVisibility(8);
            View space_three = _$_findCachedViewById(com.ido.life.R.id.space_three);
            Intrinsics.checkExpressionValueIsNotNull(space_three, "space_three");
            space_three.setVisibility(8);
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            UnitSetting unitSettingQueryUnitSetting = GreenDaoUtil.queryUnitSetting(runTimeUtil.getUserId());
            if (unitSettingQueryUnitSetting != null) {
                ((SettingItemPresenter) this.mPresenter).setMSettingValueBefore(unitSettingQueryUnitSetting.getUnit());
                ImageView img_setting_one = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_one);
                Intrinsics.checkExpressionValueIsNotNull(img_setting_one, "img_setting_one");
                img_setting_one.setSelected(unitSettingQueryUnitSetting.getUnit() == 1);
                ImageView img_setting_two = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_two);
                Intrinsics.checkExpressionValueIsNotNull(img_setting_two, "img_setting_two");
                img_setting_two.setSelected(unitSettingQueryUnitSetting.getUnit() == 2);
                SportSettingPreference.clear();
            } else {
                ((SettingItemPresenter) this.mPresenter).setMSettingNewValue(1);
                ImageView img_setting_one2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_one);
                Intrinsics.checkExpressionValueIsNotNull(img_setting_one2, "img_setting_one");
                img_setting_one2.setSelected(true);
                ImageView img_setting_two2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_two);
                Intrinsics.checkExpressionValueIsNotNull(img_setting_two2, "img_setting_two");
                img_setting_two2.setSelected(false);
            }
            if (((SettingItemPresenter) this.mPresenter).getMSupportTempUnitSetting()) {
                TextView tv_group_title_one = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_group_title_one);
                Intrinsics.checkExpressionValueIsNotNull(tv_group_title_one, "tv_group_title_one");
                tv_group_title_one.setVisibility(0);
                View divider_group_two = _$_findCachedViewById(com.ido.life.R.id.divider_group_two);
                Intrinsics.checkExpressionValueIsNotNull(divider_group_two, "divider_group_two");
                divider_group_two.setVisibility(0);
                TextView tv_group_title_two = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_group_title_two);
                Intrinsics.checkExpressionValueIsNotNull(tv_group_title_two, "tv_group_title_two");
                tv_group_title_two.setVisibility(0);
                LinearLayout lay_temp_setting = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_temp_setting);
                Intrinsics.checkExpressionValueIsNotNull(lay_temp_setting, "lay_temp_setting");
                lay_temp_setting.setVisibility(0);
                TextView tv_group_title_two2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_group_title_two);
                Intrinsics.checkExpressionValueIsNotNull(tv_group_title_two2, "tv_group_title_two");
                tv_group_title_two2.setText(getLanguageText(R.string.temp_setting_title));
                TextView tv_group_title_one2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_group_title_one);
                Intrinsics.checkExpressionValueIsNotNull(tv_group_title_one2, "tv_group_title_one");
                tv_group_title_one2.setText(getLanguageText(R.string.unit_setting_title));
                RegularTextView tv_temp_setting_c = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_temp_setting_c);
                Intrinsics.checkExpressionValueIsNotNull(tv_temp_setting_c, "tv_temp_setting_c");
                tv_temp_setting_c.setText(getLanguageText(R.string.temp_unit_c));
                RegularTextView tv_temp_setting_f = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_temp_setting_f);
                Intrinsics.checkExpressionValueIsNotNull(tv_temp_setting_f, "tv_temp_setting_f");
                tv_temp_setting_f.setText(getLanguageText(R.string.temp_unit_f));
                View space_two = _$_findCachedViewById(com.ido.life.R.id.space_two);
                Intrinsics.checkExpressionValueIsNotNull(space_two, "space_two");
                space_two.setVisibility(8);
                RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
                TempUnitSetting tempUnitSettingQueryTempUnitSetting = GreenDaoUtil.queryTempUnitSetting(runTimeUtil2.getUserId());
                if (tempUnitSettingQueryTempUnitSetting != null) {
                    ((SettingItemPresenter) this.mPresenter).setMTempSettingValueBefore(tempUnitSettingQueryTempUnitSetting.getTemp());
                    if (tempUnitSettingQueryTempUnitSetting.getTemp() == 1) {
                        ImageView img_temp_setting_c = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_temp_setting_c);
                        Intrinsics.checkExpressionValueIsNotNull(img_temp_setting_c, "img_temp_setting_c");
                        img_temp_setting_c.setSelected(true);
                        ImageView img_temp_setting_f = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_temp_setting_f);
                        Intrinsics.checkExpressionValueIsNotNull(img_temp_setting_f, "img_temp_setting_f");
                        img_temp_setting_f.setSelected(false);
                    } else {
                        ImageView img_temp_setting_c2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_temp_setting_c);
                        Intrinsics.checkExpressionValueIsNotNull(img_temp_setting_c2, "img_temp_setting_c");
                        img_temp_setting_c2.setSelected(false);
                        ImageView img_temp_setting_f2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_temp_setting_f);
                        Intrinsics.checkExpressionValueIsNotNull(img_temp_setting_f2, "img_temp_setting_f");
                        img_temp_setting_f2.setSelected(true);
                    }
                } else {
                    ((SettingItemPresenter) this.mPresenter).setMTempSettingNewValue(1);
                    ImageView img_temp_setting_c3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_temp_setting_c);
                    Intrinsics.checkExpressionValueIsNotNull(img_temp_setting_c3, "img_temp_setting_c");
                    img_temp_setting_c3.setSelected(true);
                    ImageView img_temp_setting_f3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_temp_setting_f);
                    Intrinsics.checkExpressionValueIsNotNull(img_temp_setting_f3, "img_temp_setting_f");
                    img_temp_setting_f3.setSelected(false);
                }
            } else {
                TextView tv_group_title_one3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_group_title_one);
                Intrinsics.checkExpressionValueIsNotNull(tv_group_title_one3, "tv_group_title_one");
                tv_group_title_one3.setVisibility(8);
                View divider_group_two2 = _$_findCachedViewById(com.ido.life.R.id.divider_group_two);
                Intrinsics.checkExpressionValueIsNotNull(divider_group_two2, "divider_group_two");
                divider_group_two2.setVisibility(8);
                TextView tv_group_title_two3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_group_title_two);
                Intrinsics.checkExpressionValueIsNotNull(tv_group_title_two3, "tv_group_title_two");
                tv_group_title_two3.setVisibility(8);
                LinearLayout lay_temp_setting2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_temp_setting);
                Intrinsics.checkExpressionValueIsNotNull(lay_temp_setting2, "lay_temp_setting");
                lay_temp_setting2.setVisibility(8);
                View space_two2 = _$_findCachedViewById(com.ido.life.R.id.space_two);
                Intrinsics.checkExpressionValueIsNotNull(space_two2, "space_two");
                space_two2.setVisibility(0);
            }
        } else if (i == TYPE_SETTING_TIME) {
            View divider_group_one = _$_findCachedViewById(com.ido.life.R.id.divider_group_one);
            Intrinsics.checkExpressionValueIsNotNull(divider_group_one, "divider_group_one");
            divider_group_one.setVisibility(0);
            View space_one = _$_findCachedViewById(com.ido.life.R.id.space_one);
            Intrinsics.checkExpressionValueIsNotNull(space_one, "space_one");
            space_one.setVisibility(8);
            this.mTitleBar.setTitle(getLanguageText(R.string.sport_time));
            RunTimeUtil runTimeUtil3 = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil3, "RunTimeUtil.getInstance()");
            TimeSet timeSetQueryTimeSet = GreenDaoUtil.queryTimeSet(runTimeUtil3.getUserId());
            if (timeSetQueryTimeSet != null) {
                ((SettingItemPresenter) this.mPresenter).setMSettingValueBefore(timeSetQueryTimeSet.getTimeFormat());
                ImageView img_setting_one3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_one);
                Intrinsics.checkExpressionValueIsNotNull(img_setting_one3, "img_setting_one");
                img_setting_one3.setSelected(timeSetQueryTimeSet.getTimeFormat() == 0);
                ImageView img_setting_two3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_two);
                Intrinsics.checkExpressionValueIsNotNull(img_setting_two3, "img_setting_two");
                img_setting_two3.setSelected(timeSetQueryTimeSet.getTimeFormat() == 2);
                ImageView img_setting_three = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_three);
                Intrinsics.checkExpressionValueIsNotNull(img_setting_three, "img_setting_three");
                img_setting_three.setSelected(timeSetQueryTimeSet.getTimeFormat() == 1);
            } else {
                ((SettingItemPresenter) this.mPresenter).setMSettingNewValue(0);
                ImageView img_setting_one4 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_one);
                Intrinsics.checkExpressionValueIsNotNull(img_setting_one4, "img_setting_one");
                img_setting_one4.setSelected(true);
                ImageView img_setting_two4 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_two);
                Intrinsics.checkExpressionValueIsNotNull(img_setting_two4, "img_setting_two");
                img_setting_two4.setSelected(false);
                ImageView img_setting_three2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_three);
                Intrinsics.checkExpressionValueIsNotNull(img_setting_three2, "img_setting_three");
                img_setting_three2.setSelected(false);
            }
        } else if (i == TYPE_SETTING_WEEK_START) {
            this.mTitleBar.setTitle(getLanguageText(R.string.title_week_start));
            RunTimeUtil runTimeUtil4 = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil4, "RunTimeUtil.getInstance()");
            WeekStartSetting weekStartSettingQueryWeekStart = GreenDaoUtil.queryWeekStart(runTimeUtil4.getUserId());
            if (weekStartSettingQueryWeekStart != null) {
                ((SettingItemPresenter) this.mPresenter).setMSettingValueBefore(weekStartSettingQueryWeekStart.getWeekStart());
                ImageView img_setting_one5 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_one);
                Intrinsics.checkExpressionValueIsNotNull(img_setting_one5, "img_setting_one");
                img_setting_one5.setSelected(weekStartSettingQueryWeekStart.getWeekStart() == 1);
                ImageView img_setting_two5 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_two);
                Intrinsics.checkExpressionValueIsNotNull(img_setting_two5, "img_setting_two");
                img_setting_two5.setSelected(weekStartSettingQueryWeekStart.getWeekStart() == 7);
                ImageView img_setting_three3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_three);
                Intrinsics.checkExpressionValueIsNotNull(img_setting_three3, "img_setting_three");
                img_setting_three3.setSelected(weekStartSettingQueryWeekStart.getWeekStart() == 2);
            } else {
                ((SettingItemPresenter) this.mPresenter).setMSettingNewValue(1);
                ImageView img_setting_one6 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_one);
                Intrinsics.checkExpressionValueIsNotNull(img_setting_one6, "img_setting_one");
                img_setting_one6.setSelected(true);
                ImageView img_setting_two6 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_two);
                Intrinsics.checkExpressionValueIsNotNull(img_setting_two6, "img_setting_two");
                img_setting_two6.setSelected(false);
                ImageView img_setting_three4 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_three);
                Intrinsics.checkExpressionValueIsNotNull(img_setting_three4, "img_setting_three");
                img_setting_three4.setSelected(false);
            }
        } else if (i == TYPE_SETTING_MAP_ENGINE) {
            this.mTitleBar.setTitle(getLanguageText(R.string.my_map_engine));
            LinearLayout lay_setting_three2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_setting_three);
            Intrinsics.checkExpressionValueIsNotNull(lay_setting_three2, "lay_setting_three");
            lay_setting_three2.setVisibility(8);
            int mapEngine = RunTimeUtil.getInstance().getMapEngine(this);
            ImageView img_setting_one7 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_one);
            Intrinsics.checkExpressionValueIsNotNull(img_setting_one7, "img_setting_one");
            img_setting_one7.setSelected(mapEngine == 1);
            ImageView img_setting_two7 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_two);
            Intrinsics.checkExpressionValueIsNotNull(img_setting_two7, "img_setting_two");
            img_setting_two7.setSelected(mapEngine == 2);
            ((SettingItemPresenter) this.mPresenter).setMSettingValueBefore(mapEngine);
        }
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.user.set.settingitem.SettingItemActivity.initViews.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingItemActivity.access$getMPresenter$p(SettingItemActivity.this).syncConfig();
            }
        });
    }

    @Override // com.ido.life.module.user.set.settingitem.ISettingItemView
    public void onConfigSuccess() {
        NormalToast.showToast(LanguageUtil.getLanguageText(R.string.setting_success), 2000);
        supportFinishAfterTransition();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        ((SettingItemPresenter) this.mPresenter).syncConfig();
    }

    @Override // com.ido.life.module.user.set.settingitem.ISettingItemView
    public void onConfigFailed() {
        supportFinishAfterTransition();
    }

    @Override // com.ido.life.base.IBaseLoadingView
    public void showLoading() {
        showLoadingDialog();
    }

    @Override // com.ido.life.base.IBaseLoadingView
    public void dismissLoading() {
        dismissLoadingDialog();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        int i = this.mType;
        if (i == TYPE_SETTING_UNIT) {
            this.mTitleBar.setTitle(getLanguageText(R.string.mine_unit_set));
            RegularTextView tv_setting_one = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_setting_one);
            Intrinsics.checkExpressionValueIsNotNull(tv_setting_one, "tv_setting_one");
            tv_setting_one.setText(getLanguageText(R.string.mine_unit_metric_system));
            RegularTextView tv_setting_two = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_setting_two);
            Intrinsics.checkExpressionValueIsNotNull(tv_setting_two, "tv_setting_two");
            tv_setting_two.setText(getLanguageText(R.string.mine_unit_british_system));
            TextView tv_group_title_one = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_group_title_one);
            Intrinsics.checkExpressionValueIsNotNull(tv_group_title_one, "tv_group_title_one");
            tv_group_title_one.setText(getLanguageText(R.string.unit_setting_title));
            TextView tv_group_title_two = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_group_title_two);
            Intrinsics.checkExpressionValueIsNotNull(tv_group_title_two, "tv_group_title_two");
            tv_group_title_two.setText(getLanguageText(R.string.temp_setting_title));
            RegularTextView tv_temp_setting_c = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_temp_setting_c);
            Intrinsics.checkExpressionValueIsNotNull(tv_temp_setting_c, "tv_temp_setting_c");
            tv_temp_setting_c.setText(getLanguageText(R.string.temp_unit_c));
            RegularTextView tv_temp_setting_f = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_temp_setting_f);
            Intrinsics.checkExpressionValueIsNotNull(tv_temp_setting_f, "tv_temp_setting_f");
            tv_temp_setting_f.setText(getLanguageText(R.string.temp_unit_f));
            return;
        }
        if (i == TYPE_SETTING_TIME) {
            this.mTitleBar.setTitle(getLanguageText(R.string.sport_time));
            RegularTextView tv_setting_one2 = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_setting_one);
            Intrinsics.checkExpressionValueIsNotNull(tv_setting_one2, "tv_setting_one");
            tv_setting_one2.setText(getLanguageText(R.string.device_follow_system_time));
            RegularTextView tv_setting_two2 = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_setting_two);
            Intrinsics.checkExpressionValueIsNotNull(tv_setting_two2, "tv_setting_two");
            tv_setting_two2.setText(getLanguageText(R.string.hours_12));
            RegularTextView tv_setting_three = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_setting_three);
            Intrinsics.checkExpressionValueIsNotNull(tv_setting_three, "tv_setting_three");
            tv_setting_three.setText(getLanguageText(R.string.hours_24));
            return;
        }
        if (i == TYPE_SETTING_WEEK_START) {
            this.mTitleBar.setTitle(getLanguageText(R.string.title_week_start));
            RegularTextView tv_setting_one3 = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_setting_one);
            Intrinsics.checkExpressionValueIsNotNull(tv_setting_one3, "tv_setting_one");
            tv_setting_one3.setText(String.valueOf(getLanguageText(R.string.public_time_sunday)));
            RegularTextView tv_setting_two3 = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_setting_two);
            Intrinsics.checkExpressionValueIsNotNull(tv_setting_two3, "tv_setting_two");
            tv_setting_two3.setText(String.valueOf(getLanguageText(R.string.public_time_saturday)));
            RegularTextView tv_setting_three2 = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_setting_three);
            Intrinsics.checkExpressionValueIsNotNull(tv_setting_three2, "tv_setting_three");
            tv_setting_three2.setText(String.valueOf(getLanguageText(R.string.public_time_monday)));
            return;
        }
        if (i == TYPE_SETTING_MAP_ENGINE) {
            this.mTitleBar.setTitle(getLanguageText(R.string.my_map_engine));
            RegularTextView tv_setting_one4 = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_setting_one);
            Intrinsics.checkExpressionValueIsNotNull(tv_setting_one4, "tv_setting_one");
            tv_setting_one4.setText(getLanguageText(R.string.device_google));
            RegularTextView tv_setting_two4 = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_setting_two);
            Intrinsics.checkExpressionValueIsNotNull(tv_setting_two4, "tv_setting_two");
            tv_setting_two4.setText(getLanguageText(R.string.sport_map_gaode));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_setting_one) {
            ImageView img_setting_one = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_one);
            Intrinsics.checkExpressionValueIsNotNull(img_setting_one, "img_setting_one");
            img_setting_one.setSelected(true);
            ImageView img_setting_two = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_two);
            Intrinsics.checkExpressionValueIsNotNull(img_setting_two, "img_setting_two");
            img_setting_two.setSelected(false);
            ImageView img_setting_three = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_three);
            Intrinsics.checkExpressionValueIsNotNull(img_setting_three, "img_setting_three");
            img_setting_three.setSelected(false);
            ((SettingItemPresenter) this.mPresenter).updateConfig(0);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_setting_two) {
            ImageView img_setting_two2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_two);
            Intrinsics.checkExpressionValueIsNotNull(img_setting_two2, "img_setting_two");
            img_setting_two2.setSelected(true);
            ImageView img_setting_one2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_one);
            Intrinsics.checkExpressionValueIsNotNull(img_setting_one2, "img_setting_one");
            img_setting_one2.setSelected(false);
            ImageView img_setting_three2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_three);
            Intrinsics.checkExpressionValueIsNotNull(img_setting_three2, "img_setting_three");
            img_setting_three2.setSelected(false);
            ((SettingItemPresenter) this.mPresenter).updateConfig(1);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_setting_three) {
            ImageView img_setting_three3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_three);
            Intrinsics.checkExpressionValueIsNotNull(img_setting_three3, "img_setting_three");
            img_setting_three3.setSelected(true);
            ImageView img_setting_one3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_one);
            Intrinsics.checkExpressionValueIsNotNull(img_setting_one3, "img_setting_one");
            img_setting_one3.setSelected(false);
            ImageView img_setting_two3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_setting_two);
            Intrinsics.checkExpressionValueIsNotNull(img_setting_two3, "img_setting_two");
            img_setting_two3.setSelected(false);
            ((SettingItemPresenter) this.mPresenter).updateConfig(2);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_temp_setting_c) {
            ImageView img_temp_setting_c = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_temp_setting_c);
            Intrinsics.checkExpressionValueIsNotNull(img_temp_setting_c, "img_temp_setting_c");
            img_temp_setting_c.setSelected(true);
            ImageView img_temp_setting_f = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_temp_setting_f);
            Intrinsics.checkExpressionValueIsNotNull(img_temp_setting_f, "img_temp_setting_f");
            img_temp_setting_f.setSelected(false);
            ((SettingItemPresenter) this.mPresenter).updateConfig(3);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_temp_setting_f) {
            ImageView img_temp_setting_c2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_temp_setting_c);
            Intrinsics.checkExpressionValueIsNotNull(img_temp_setting_c2, "img_temp_setting_c");
            img_temp_setting_c2.setSelected(false);
            ImageView img_temp_setting_f2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_temp_setting_f);
            Intrinsics.checkExpressionValueIsNotNull(img_temp_setting_f2, "img_temp_setting_f");
            img_temp_setting_f2.setSelected(true);
            ((SettingItemPresenter) this.mPresenter).updateConfig(4);
        }
    }
}