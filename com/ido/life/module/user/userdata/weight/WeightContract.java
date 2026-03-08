package com.ido.life.module.user.userdata.weight;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes3.dex */
public interface WeightContract {

    public interface Presenter extends BasePresenter {
        void checkWeight(Context context, float f2);

        void getWeight(String str);

        int getWeightBan();

        float getWeightKg();

        void initWeight();

        void saveTempWeight(int i);

        void toCloudSyncDialog(FragmentManager fragmentManager);
    }

    public interface View extends BaseView<Presenter> {
        void changeForwardEnable(boolean z);

        void hideLoading();

        void setForward();

        void setRulerView(int i);

        void showLoading();

        void showMessage(String str);

        void toSetGoal();
    }
}