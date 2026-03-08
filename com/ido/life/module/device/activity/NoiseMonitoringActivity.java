package com.ido.life.module.device.activity;

import android.view.View;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.NoisePara;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.bean.HealthMonitoringBean;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.NoiseMonitoringPresenter;
import com.ido.life.module.device.view.INoiseMonitoringView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NoiseMonitoringActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\fH\u0014J\b\u0010\u000e\u001a\u00020\fH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\fH\u0016J\b\u0010\u0012\u001a\u00020\fH\u0016J\b\u0010\u0013\u001a\u00020\fH\u0016J\b\u0010\u0014\u001a\u00020\fH\u0014R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/ido/life/module/device/activity/NoiseMonitoringActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/presenter/NoiseMonitoringPresenter;", "Lcom/ido/life/module/device/view/INoiseMonitoringView;", "()V", "mHealthMonitoringBean", "Lcom/ido/life/bean/HealthMonitoringBean;", "noisePara", "Lcom/ido/ble/protocol/model/NoisePara;", "getLayoutResId", "", "initData", "", "initEvent", "initLabelLanguage", "isDataChanged", "", "onBackPressed", "onSetNoiseFailed", "onSetNoiseSuccess", "sendCmd", "app_release"}, k = 1, mv = {1, 1, 16})
public final class NoiseMonitoringActivity extends BaseActivity<NoiseMonitoringPresenter> implements INoiseMonitoringView {
    private HashMap _$_findViewCache;
    private HealthMonitoringBean mHealthMonitoringBean;
    private NoisePara noisePara;

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
        return R.layout.activity_noise_monitoring;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.ambient_volume));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.NoiseMonitoringActivity.initEvent.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NoiseMonitoringActivity.this.onBackPressed();
            }
        });
        ((CustomItemLabelView) _$_findCachedViewById(com.ido.life.R.id.item_switch)).setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.NoiseMonitoringActivity.initEvent.2
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                NoisePara noisePara = NoiseMonitoringActivity.this.noisePara;
                if (noisePara != null) {
                    noisePara.mode = z ? 170 : 85;
                }
            }
        });
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        NoisePara noisePara;
        super.initData();
        this.mHealthMonitoringBean = (HealthMonitoringBean) getIntent().getSerializableExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME);
        NoiseMonitoringPresenter noiseMonitoringPresenter = (NoiseMonitoringPresenter) this.mPresenter;
        if (noiseMonitoringPresenter != null) {
            HealthMonitoringBean healthMonitoringBean = this.mHealthMonitoringBean;
            noisePara = noiseMonitoringPresenter.getNoisePara(healthMonitoringBean != null ? healthMonitoringBean.getStatus() : false);
        } else {
            noisePara = null;
        }
        this.noisePara = noisePara;
        NoisePara noisePara2 = this.noisePara;
        if (noisePara2 != null) {
            CustomItemLabelView item_switch = (CustomItemLabelView) _$_findCachedViewById(com.ido.life.R.id.item_switch);
            Intrinsics.checkExpressionValueIsNotNull(item_switch, "item_switch");
            item_switch.setSwitchStatus(noisePara2.mode == 170);
        }
    }

    @Override // com.ido.life.module.device.view.INoiseMonitoringView
    public void onSetNoiseSuccess() {
        CommonLogUtil.printAndSave("onSetNoiseSuccess");
        setResult(-1);
        supportFinishAfterTransition();
    }

    @Override // com.ido.life.module.device.view.INoiseMonitoringView
    public void onSetNoiseFailed() {
        CommonLogUtil.printAndSave("onSetNoiseFailed");
        showToast(getLanguageText(R.string.device_set_failed));
        CustomItemLabelView item_switch = (CustomItemLabelView) _$_findCachedViewById(com.ido.life.R.id.item_switch);
        Intrinsics.checkExpressionValueIsNotNull(item_switch, "item_switch");
        CustomItemLabelView item_switch2 = (CustomItemLabelView) _$_findCachedViewById(com.ido.life.R.id.item_switch);
        Intrinsics.checkExpressionValueIsNotNull(item_switch2, "item_switch");
        item_switch.setSwitchStatus(!item_switch2.getSwitchStatus());
        finish();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        if (this.noisePara != null && isDataChanged()) {
            showSettingLoading(false);
            NoiseMonitoringPresenter noiseMonitoringPresenter = (NoiseMonitoringPresenter) this.mPresenter;
            NoisePara noisePara = this.noisePara;
            if (noisePara == null) {
                Intrinsics.throwNpe();
            }
            noiseMonitoringPresenter.sendNoiseParam(noisePara);
            return;
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (isDataChanged()) {
            saveData();
        } else {
            super.onBackPressed();
        }
    }

    private final boolean isDataChanged() {
        String strValueOf = String.valueOf(this.noisePara);
        NoiseMonitoringPresenter noiseMonitoringPresenter = (NoiseMonitoringPresenter) this.mPresenter;
        return !Intrinsics.areEqual(strValueOf, noiseMonitoringPresenter.getNoisePara(this.mHealthMonitoringBean != null ? r2.getStatus() : false).toString());
    }
}