package com.ido.life.module.sport.history.swim;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.database.model.SportHealth;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportSwimContract {

    interface Presenter extends BasePresenter {
        void deleteRecord(String str, String str2);

        void getSwimNameByType(int i);

        void getSwimSportData(String str);
    }

    interface View extends BaseView<Presenter> {
        void hideLoading();

        void hideOpenWaterSwimView();

        void hidePoolSwimView();

        void setFrequencyChartView(List<String> list, List<String> list2, List<BaseCharBean> list3, int i, int i2);

        void setFrequencyChartVisible(boolean z);

        void setIndoorSwimNormalData(SportHealth sportHealth);

        void setLoadLoadTitleShow(boolean z);

        void setLoadTitleText(String str);

        void setOpenWaterData(String str, int i, int i2);

        void setPaceChartView(List<String> list, List<String> list2, List<BaseCharBean> list3, int i, int i2);

        void setPeaceVisible(boolean z);

        void setRightButtonView(boolean z);

        void setShareView(boolean z);

        void setSwimName(String str);

        void setSwolfChartView(List<String> list, List<String> list2, List<BaseCharBean> list3, int i, int i2);

        void setSwolfChartVisible(boolean z);

        void showLoading();

        void showRightBtn(boolean z);

        void showSportDataView(boolean z);

        void showSportNoNet(boolean z);

        void showSportRetryView(boolean z);

        void toBack();
    }
}