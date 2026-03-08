package com.ido.life.module.sport.setting.rate;

import com.ido.life.base.IBaseView;

/* JADX INFO: loaded from: classes2.dex */
public interface ISportRateView extends IBaseView {
    void setHeartLimit(int i);

    void setRateAerobicEndurance(String str);

    void setRateAnaerobicEndurance(String str);

    void setRateBurningGrease(String str);

    void setRateLayoutBg(boolean z);

    void setRateLimit(String str);

    void setRateLimitEnable(boolean z);

    void setRateMax(int i);

    void setRateMax(String str);

    void setRateUpperLimit(String str);

    void setRateWarmUp(String str);

    void setRateWarnIsOpen(boolean z);

    void showMessage(String str);

    void showOptSportHeartRate(boolean z);

    void showRateUpper(boolean z);
}