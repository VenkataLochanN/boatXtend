package com.ido.life.module.nodatapage.bind;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.PressureParam;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BasePresenter;
import com.ido.life.base.IBaseView;
import com.ido.life.module.device.activity.MenstrualCycleSettingActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HasBindNoDataActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\f\r\u000eB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0005H\u0014J\u0006\u0010\u0007\u001a\u00020\bJ\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\nH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/ido/life/module/nodatapage/bind/HasBindNoDataActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/nodatapage/bind/HasBindNoDataActivity$HasBindNoDataPresenter;", "()V", "mType", "", "getLayoutResId", "getPressureSwitch", "", "initData", "", "initLabelLanguage", "Companion", "HasBindNoDataPresenter", "HasBindNoDataView", "app_release"}, k = 1, mv = {1, 1, 16})
public final class HasBindNoDataActivity extends BaseActivity<HasBindNoDataPresenter> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_TYPE = "type";
    private HashMap _$_findViewCache;
    private int mType = -1;

    /* JADX INFO: compiled from: HasBindNoDataActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/ido/life/module/nodatapage/bind/HasBindNoDataActivity$HasBindNoDataPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/nodatapage/bind/HasBindNoDataActivity$HasBindNoDataView;", "()V", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class HasBindNoDataPresenter extends BasePresenter<HasBindNoDataView> {
    }

    /* JADX INFO: compiled from: HasBindNoDataActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/ido/life/module/nodatapage/bind/HasBindNoDataActivity$HasBindNoDataView;", "Lcom/ido/life/base/IBaseView;", "()V", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class HasBindNoDataView implements IBaseView {
    }

    public static final String getKEY_TYPE() {
        Companion companion = INSTANCE;
        return KEY_TYPE;
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
        return R.layout.activity_hasbind_nodata_layout;
    }

    /* JADX INFO: compiled from: HasBindNoDataActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/ido/life/module/nodatapage/bind/HasBindNoDataActivity$Companion;", "", "()V", "KEY_TYPE", "", "KEY_TYPE$annotations", "getKEY_TYPE", "()Ljava/lang/String;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void KEY_TYPE$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getKEY_TYPE() {
            return HasBindNoDataActivity.KEY_TYPE;
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mType = getIntent().getIntExtra(KEY_TYPE, this.mType);
        if (this.mType == -1) {
            finish();
        }
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        TextView tv_title = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title);
        Intrinsics.checkExpressionValueIsNotNull(tv_title, "tv_title");
        tv_title.setText(getLanguageText(R.string.has_no_data));
        int i = this.mType;
        if (i == 0) {
            setTitle(getLanguageText(R.string.home_steps_tittle));
            TextView tv_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
            Intrinsics.checkExpressionValueIsNotNull(tv_desc, "tv_desc");
            tv_desc.setText(getLanguageText(R.string.step_hasbind_nodata));
            return;
        }
        if (i == 1) {
            setTitle(getLanguageText(R.string.sport_distance));
            TextView tv_desc2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
            Intrinsics.checkExpressionValueIsNotNull(tv_desc2, "tv_desc");
            tv_desc2.setText(getLanguageText(R.string.distance_hasbind_nodata));
            return;
        }
        if (i == 2) {
            setTitle(getLanguageText(R.string.home_card_activity_calorie_title));
            TextView tv_desc3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
            Intrinsics.checkExpressionValueIsNotNull(tv_desc3, "tv_desc");
            tv_desc3.setText(getLanguageText(R.string.calorie_hasbind_nodata));
            return;
        }
        if (i == 3) {
            setTitle(getLanguageText(R.string.home_card_activity_stronger_walk));
            TextView tv_desc4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
            Intrinsics.checkExpressionValueIsNotNull(tv_desc4, "tv_desc");
            tv_desc4.setText(getLanguageText(R.string.activetime_hasbind_nodata));
            return;
        }
        if (i == 4) {
            setTitle(getLanguageText(R.string.detail_walk_hour));
            TextView tv_desc5 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
            Intrinsics.checkExpressionValueIsNotNull(tv_desc5, "tv_desc");
            tv_desc5.setText(getLanguageText(R.string.walkhour_hasbind_nodata));
            return;
        }
        if (i == 12) {
            setTitle(getLanguageText(R.string.home_card_physiological_cycle));
            this.mTitleBar.initLayout(1);
            this.mTitleBar.setRightImg(R.mipmap.icon_setting);
            this.mTitleBar.setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.nodatapage.bind.HasBindNoDataActivity.initLabelLanguage.2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HasBindNoDataActivity.this.startActivity(new Intent(HasBindNoDataActivity.this, (Class<?>) MenstrualCycleSettingActivity.class));
                }
            });
            return;
        }
        if (i == 19) {
            setTitle(getLanguageText(R.string.help_max_spo2_title));
            TextView tv_desc6 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
            Intrinsics.checkExpressionValueIsNotNull(tv_desc6, "tv_desc");
            tv_desc6.setText(getLanguageText(R.string.oxygen_uptake_hasbind_nodata));
            return;
        }
        if (i == 16) {
            setTitle(getLanguageText(R.string.sport_record_fitness));
            TextView tv_desc7 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
            Intrinsics.checkExpressionValueIsNotNull(tv_desc7, "tv_desc");
            tv_desc7.setText(getLanguageText(R.string.fitness_no_data));
            return;
        }
        if (i != 17) {
            switch (i) {
                case 7:
                    setTitle(getLanguageText(R.string.home_card_sleep));
                    TextView tv_desc8 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                    Intrinsics.checkExpressionValueIsNotNull(tv_desc8, "tv_desc");
                    tv_desc8.setText(getLanguageText(R.string.sleep_hasbind_nodata));
                    break;
                case 8:
                    setTitle(getLanguageText(R.string.home_card_heart_rate));
                    TextView tv_desc9 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                    Intrinsics.checkExpressionValueIsNotNull(tv_desc9, "tv_desc");
                    tv_desc9.setText(getLanguageText(R.string.heart_rate_hasbind_nodata));
                    break;
                case 9:
                    setTitle(getLanguageText(R.string.home_card_pressure));
                    TextView tv_desc10 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                    Intrinsics.checkExpressionValueIsNotNull(tv_desc10, "tv_desc");
                    tv_desc10.setText(getLanguageText(R.string.pressure_hasbind_nodata));
                    break;
                case 10:
                    setTitle(getLanguageText(R.string.home_card_blood_oxygen));
                    TextView tv_desc11 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                    Intrinsics.checkExpressionValueIsNotNull(tv_desc11, "tv_desc");
                    tv_desc11.setText(getLanguageText(R.string.oxy_hasbind_nodata));
                    TextView tv_action = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_action);
                    Intrinsics.checkExpressionValueIsNotNull(tv_action, "tv_action");
                    tv_action.setText(getLanguageText(R.string.start_testing));
                    ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_action)).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.nodatapage.bind.HasBindNoDataActivity.initLabelLanguage.1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                        }
                    });
                    break;
                default:
                    finish();
                    break;
            }
            return;
        }
        setTitle(getLanguageText(R.string.ambient_volume));
        TextView tv_desc12 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_desc12, "tv_desc");
        tv_desc12.setText(getLanguageText(R.string.ambient_volume_hasbind_nodata));
    }

    public final boolean getPressureSwitch() {
        PressureParam pressureParam = LocalDataManager.getPressureParam();
        if (pressureParam == null) {
            pressureParam = new PressureParam();
            pressureParam.onOff = 85;
        }
        return pressureParam.onOff == 170;
    }
}