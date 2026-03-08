package com.ido.life.module.device.activity;

import android.view.View;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.SleepMonitoringPara;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.ScientificSleepPresenter;
import com.ido.life.module.device.view.IScientificSleepView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScientificSleepMonitoringActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\nH\u0014J\b\u0010\f\u001a\u00020\nH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0011\u001a\u00020\nH\u0016J\b\u0010\u0012\u001a\u00020\nH\u0014R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/ido/life/module/device/activity/ScientificSleepMonitoringActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/presenter/ScientificSleepPresenter;", "Lcom/ido/life/module/device/view/IScientificSleepView;", "()V", "mSleepMonitoringPara", "Lcom/ido/ble/protocol/model/SleepMonitoringPara;", "getLayoutResId", "", "initData", "", "initEvent", "initLabelLanguage", "isDataChanged", "", "onBackPressed", "onSetScientificSleepFailed", "onSetScientificSleepSuccess", "sendCmd", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ScientificSleepMonitoringActivity extends BaseActivity<ScientificSleepPresenter> implements IScientificSleepView {
    private HashMap _$_findViewCache;
    private SleepMonitoringPara mSleepMonitoringPara;

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
        return R.layout.activity_scientific_sleep_monitoring;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_scientific_sleep));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.ScientificSleepMonitoringActivity.initEvent.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScientificSleepMonitoringActivity.this.onBackPressed();
            }
        });
        ((CustomItemLabelView) _$_findCachedViewById(com.ido.life.R.id.item_switch)).setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.ScientificSleepMonitoringActivity.initEvent.2
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                SleepMonitoringPara sleepMonitoringPara = ScientificSleepMonitoringActivity.this.mSleepMonitoringPara;
                if (sleepMonitoringPara != null) {
                    sleepMonitoringPara.mode = z ? 170 : 85;
                }
            }
        });
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        ScientificSleepPresenter scientificSleepPresenter = (ScientificSleepPresenter) this.mPresenter;
        this.mSleepMonitoringPara = scientificSleepPresenter != null ? scientificSleepPresenter.getSleepMonitoringPara() : null;
        CustomItemLabelView item_switch = (CustomItemLabelView) _$_findCachedViewById(com.ido.life.R.id.item_switch);
        Intrinsics.checkExpressionValueIsNotNull(item_switch, "item_switch");
        SleepMonitoringPara sleepMonitoringPara = this.mSleepMonitoringPara;
        item_switch.setSwitchStatus(sleepMonitoringPara != null && sleepMonitoringPara.mode == 170);
    }

    @Override // com.ido.life.module.device.view.IScientificSleepView
    public void onSetScientificSleepSuccess() {
        setResult(-1);
        finish();
    }

    @Override // com.ido.life.module.device.view.IScientificSleepView
    public void onSetScientificSleepFailed() {
        showToast(getLanguageText(R.string.public_set_failed));
        CustomItemLabelView item_switch = (CustomItemLabelView) _$_findCachedViewById(com.ido.life.R.id.item_switch);
        Intrinsics.checkExpressionValueIsNotNull(item_switch, "item_switch");
        CustomItemLabelView item_switch2 = (CustomItemLabelView) _$_findCachedViewById(com.ido.life.R.id.item_switch);
        Intrinsics.checkExpressionValueIsNotNull(item_switch2, "item_switch");
        item_switch.setSwitchStatus(!item_switch2.getSwitchStatus());
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        if (isDataChanged()) {
            showSettingLoading(false);
            ScientificSleepPresenter scientificSleepPresenter = (ScientificSleepPresenter) this.mPresenter;
            if (scientificSleepPresenter != null) {
                scientificSleepPresenter.sendMonitoring(this.mSleepMonitoringPara);
                return;
            }
            return;
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        saveData();
    }

    private final boolean isDataChanged() {
        return !Intrinsics.areEqual(String.valueOf(this.mSleepMonitoringPara), String.valueOf(((ScientificSleepPresenter) this.mPresenter).getSleepMonitoringPara()));
    }
}