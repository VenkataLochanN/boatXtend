package com.ido.life.module.device.view;

import com.ido.life.base.IBaseView;
import com.ido.life.bean.SortBean;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IQuickAppView extends IBaseView {
    void onGetQuickAppFailed();

    void onGetQuickAppStart();

    void onGetQuickAppSuccess(List<SortBean> list, int i, int i2);
}