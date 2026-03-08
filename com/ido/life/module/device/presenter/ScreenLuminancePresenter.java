package com.ido.life.module.device.presenter;

import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.BaseDeviceParaCallBack;
import com.ido.life.bean.ScreenLuminance;
import com.ido.life.module.device.view.IScreenLuminanceView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ScreenLuminancePresenter extends BaseCmdPresenter<IScreenLuminanceView> {
    private int mDefaultValue;
    private final BaseDeviceParaCallBack mICallBack = new BaseDeviceParaCallBack() { // from class: com.ido.life.module.device.presenter.ScreenLuminancePresenter.1
        @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
        public void onGetScreenBrightness(ScreenBrightness screenBrightness) {
            super.onGetScreenBrightness(screenBrightness);
            BLEManager.unregisterGetDeviceParaCallBack(ScreenLuminancePresenter.this.mICallBack);
            if (ScreenLuminancePresenter.this.isAttachView()) {
                if (screenBrightness == null) {
                    screenBrightness = new ScreenBrightness();
                    screenBrightness.level = 100;
                }
                ScreenLuminancePresenter.this.mDefaultValue = screenBrightness.level;
                IScreenLuminanceView iScreenLuminanceView = (IScreenLuminanceView) ScreenLuminancePresenter.this.getView();
                ScreenLuminancePresenter screenLuminancePresenter = ScreenLuminancePresenter.this;
                iScreenLuminanceView.onGetScreenLuminanceLevel(screenLuminancePresenter.formatValue2Level(screenLuminancePresenter.mDefaultValue), ScreenLuminancePresenter.this.mDefaultValue);
            }
        }
    };
    private int mPerValue;

    public List<ScreenLuminance> getScreenLuminanceData() {
        ArrayList arrayList = new ArrayList();
        SupportFunctionInfo supportFunctionInfo = getSupportFunctionInfo();
        int i = supportFunctionInfo.ex_table_main8_screen_brightness_3_level ? 3 : 5;
        this.mPerValue = 100 / i;
        int i2 = 0;
        while (i2 < i) {
            i2++;
            arrayList.add(new ScreenLuminance(i2, i2 == i ? 100 : this.mPerValue * i2));
        }
        if (supportFunctionInfo.ex_table_main9_get_screen_brightness) {
            BLEManager.registerGetDeviceParaCallBack(this.mICallBack);
            BLEManager.getScreenBrightness();
        } else if (!isAttachView()) {
            this.mDefaultValue = getLocalScreenBrightness().level;
            ((IScreenLuminanceView) getView()).onGetScreenLuminanceLevel(formatValue2Level(this.mDefaultValue), this.mDefaultValue);
        }
        return arrayList;
    }

    private ScreenBrightness getLocalScreenBrightness() {
        ScreenBrightness screenBrigthnessConfig = LocalDataManager.getScreenBrigthnessConfig();
        if (screenBrigthnessConfig != null) {
            return screenBrigthnessConfig;
        }
        ScreenBrightness screenBrightness = new ScreenBrightness();
        screenBrightness.level = 100;
        return screenBrightness;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int formatValue2Level(int i) {
        if (i > 100) {
            i = 100;
        }
        int i2 = this.mPerValue;
        return Math.max((i / i2) + ((i % i2) * 2 >= i2 ? 1 : 0), 1);
    }

    public boolean isDataChanged(int i) {
        return i != this.mDefaultValue;
    }

    public void sendScreenLuminance2Device(int i) {
        ScreenBrightness localScreenBrightness = getLocalScreenBrightness();
        localScreenBrightness.level = i;
        BLEManager.setSCreenBrightnessConfig(localScreenBrightness);
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BLEManager.unregisterGetDeviceParaCallBack(this.mICallBack);
    }
}