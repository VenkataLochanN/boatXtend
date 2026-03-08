package com.ido.life.module.sport.editcard;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;
import com.ido.life.bean.SortBean;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class EditCardContract {

    interface Presenter extends BasePresenter {
        List<SortBean> getItemList();

        void saveCardStatus(List<SortBean> list);
    }

    interface View extends BaseView<Presenter> {
        void hideLoading();

        void showLoading();

        void showMessage(String str);
    }
}