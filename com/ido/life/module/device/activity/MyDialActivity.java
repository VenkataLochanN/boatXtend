package com.ido.life.module.device.activity;

import android.view.View;
import com.boat.Xtend.two.R;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.module.device.fragment.MyDialFragment;
import com.ido.life.module.device.presenter.DialMarketActivityPresenter;
import com.ido.life.module.device.view.IDialMarketActivityView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MyDialActivity extends BaseActivity<DialMarketActivityPresenter> implements IDialMarketActivityView {
    MyDialFragment myDialFragment;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_my_dial;
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetCurrentDial(String str) {
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialList(List<String> list) {
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialListV3(List<DialPlateParam.PlateFileInfo> list) {
    }

    @Override // com.ido.life.module.device.view.IDialMarketActivityView
    public void onGetMyDialList(List<MyDialListEntity.DialInfo> list) {
    }

    @Override // com.ido.life.module.device.view.IDialMarketActivityView
    public void startLoading() {
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.myDialFragment = new MyDialFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.mainFragment, this.myDialFragment).commitAllowingStateLoss();
        this.mTitleBar.initLayout(2);
        if (isSupportDialSort()) {
            this.mTitleBar.setRightText(getString(R.string.edit)).setRightTextColor(ResourceUtil.getColor(R.color.color_4F6EB9));
            this.mTitleBar.setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.MyDialActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MyDialActivity.this.myDialFragment.onEditMyDial();
                }
            });
        }
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.my_dial));
    }

    private boolean isSupportDialMarket() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo != null && (!supportFunctionInfo.V3_Veryfit_not_support_photo_wallpaper || supportFunctionInfo.ex_table_main11_support_cloud_dial);
    }

    private boolean isSupportDialSort() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return (supportFunctionInfo == null || supportFunctionInfo.V3_set_watch_dial_sort_33_3E) ? false : true;
    }
}