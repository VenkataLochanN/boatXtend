package com.ido.life.module.nodatapage.unbind;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BasePresenter;
import com.ido.life.base.IBaseView;
import com.ido.life.constants.Constants;
import com.ido.life.module.bind.ChoiceBlueTypeActivity;
import com.ido.life.module.nodatapage.bind.HasBindNoDataActivity;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UnBindNoDataActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0002\u000e\u000fB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0006H\u0014J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\tH\u0014J\u0012\u0010\u000b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/ido/life/module/nodatapage/unbind/UnBindNoDataActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/nodatapage/unbind/UnBindNoDataActivity$UnBindNoDataPagePresenter;", "Landroid/view/View$OnClickListener;", "()V", "mType", "", "getLayoutResId", "initData", "", "initLabelLanguage", "onClick", "v", "Landroid/view/View;", "UnBindNoDataPagePresenter", "UnBindNoDataPageView", "app_release"}, k = 1, mv = {1, 1, 16})
public final class UnBindNoDataActivity extends BaseActivity<UnBindNoDataPagePresenter> implements View.OnClickListener {
    private HashMap _$_findViewCache;
    private int mType = -1;

    /* JADX INFO: compiled from: UnBindNoDataActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/ido/life/module/nodatapage/unbind/UnBindNoDataActivity$UnBindNoDataPagePresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/nodatapage/unbind/UnBindNoDataActivity$UnBindNoDataPageView;", "()V", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class UnBindNoDataPagePresenter extends BasePresenter<UnBindNoDataPageView> {
    }

    /* JADX INFO: compiled from: UnBindNoDataActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/ido/life/module/nodatapage/unbind/UnBindNoDataActivity$UnBindNoDataPageView;", "Lcom/ido/life/base/IBaseView;", "()V", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class UnBindNoDataPageView implements IBaseView {
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
        return R.layout.activity_unbind_nodata_layout;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mType = getIntent().getIntExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), this.mType);
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
        switch (this.mType) {
            case 0:
                setTitle(getLanguageText(R.string.home_steps_tittle));
                TextView tv_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_desc, "tv_desc");
                tv_desc.setText(getLanguageText(R.string.step_hasbind_nodata));
                break;
            case 1:
                setTitle(getLanguageText(R.string.sport_distance));
                TextView tv_desc2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_desc2, "tv_desc");
                tv_desc2.setText(getLanguageText(R.string.distance_hasbind_nodata));
                break;
            case 2:
                setTitle(getLanguageText(R.string.home_card_activity_calorie_title));
                TextView tv_desc3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_desc3, "tv_desc");
                tv_desc3.setText(getLanguageText(R.string.calorie_hasbind_nodata));
                break;
            case 3:
                setTitle(getLanguageText(R.string.home_card_activity_stronger_walk));
                TextView tv_desc4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_desc4, "tv_desc");
                tv_desc4.setText(getLanguageText(R.string.activetime_hasbind_nodata));
                break;
            case 4:
                setTitle(getLanguageText(R.string.detail_walk_hour));
                TextView tv_desc5 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_desc5, "tv_desc");
                tv_desc5.setText(getLanguageText(R.string.walkhour_hasbind_nodata));
                break;
            case 5:
            case 11:
            case 13:
            case 14:
            case 15:
            case 18:
            default:
                finish();
                break;
            case 6:
                setTitle(getLanguageText(R.string.mine_sport_record));
                TextView tv_desc6 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_desc6, "tv_desc");
                tv_desc6.setText(getLanguageText(R.string.sport_hasbind_nodata));
                TextView tv_add_device = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_add_device);
                Intrinsics.checkExpressionValueIsNotNull(tv_add_device, "tv_add_device");
                tv_add_device.setText(getLanguageText(R.string.start_sport));
                ((ImageView) _$_findCachedViewById(com.ido.life.R.id.img)).setImageResource(R.mipmap.sport_nodata);
                break;
            case 7:
                setTitle(getLanguageText(R.string.home_card_sleep));
                TextView tv_desc7 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_desc7, "tv_desc");
                tv_desc7.setText(getLanguageText(R.string.sleep_hasbind_nodata));
                break;
            case 8:
                setTitle(getLanguageText(R.string.home_card_heart_rate));
                TextView tv_desc8 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_desc8, "tv_desc");
                tv_desc8.setText(getLanguageText(R.string.heart_rate_hasbind_nodata));
                break;
            case 9:
                setTitle(getLanguageText(R.string.home_card_pressure));
                TextView tv_desc9 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_desc9, "tv_desc");
                tv_desc9.setText(getLanguageText(R.string.pressure_hasbind_nodata));
                break;
            case 10:
                setTitle(getLanguageText(R.string.home_card_blood_oxygen));
                TextView tv_desc10 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_desc10, "tv_desc");
                tv_desc10.setText(getLanguageText(R.string.oxy_hasbind_nodata));
                break;
            case 12:
                setTitle(getLanguageText(R.string.home_card_physiological_cycle));
                TextView tv_desc11 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_desc11, "tv_desc");
                tv_desc11.setText(getLanguageText(R.string.menses_hasbind_nodata));
                break;
            case 16:
                setTitle(getLanguageText(R.string.sport_record_fitness));
                TextView tv_desc12 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_desc12, "tv_desc");
                tv_desc12.setText(getLanguageText(R.string.get_fitness_data_by_add_device));
                break;
            case 17:
                setTitle(getLanguageText(R.string.ambient_volume));
                TextView tv_desc13 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_desc13, "tv_desc");
                tv_desc13.setText(getLanguageText(R.string.ambient_volume_unbind_nodata));
                break;
            case 19:
                setTitle(getLanguageText(R.string.help_max_spo2_title));
                TextView tv_desc14 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_desc14, "tv_desc");
                tv_desc14.setText(getLanguageText(R.string.oxygen_uptake_unbind_nodata));
                break;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_add_device) {
            if (this.mType == 6) {
                EventBusHelper.post(Constants.EventConstants.EVENT_GO_TO_SPORT_PAGE);
                finish();
            } else {
                startActivity(new Intent(this, (Class<?>) ChoiceBlueTypeActivity.class));
            }
        }
    }
}