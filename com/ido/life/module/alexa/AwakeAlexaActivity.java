package com.ido.life.module.alexa;

import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.life.base.BaseActivity;

/* JADX INFO: loaded from: classes2.dex */
public class AwakeAlexaActivity extends BaseActivity {
    private static final int GTBAND_ID = 354;

    @BindView(R.id.awakeImg)
    ImageView mAwakeImg;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_awake_alexa;
    }

    private BLEDevice getDeviceInfo() {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo == null) {
            currentDeviceInfo = new BLEDevice();
        }
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            currentDeviceInfo.mDeviceId = basicInfo.deivceId;
            currentDeviceInfo.version = basicInfo.firmwareVersion;
        }
        return currentDeviceInfo;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        if (getDeviceInfo().mDeviceId == GTBAND_ID) {
            this.mAwakeImg.setImageResource(R.mipmap.alexa_guide_gtband);
        } else {
            this.mAwakeImg.setImageResource(R.mipmap.alexa_guide_5);
        }
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.alexa.-$$Lambda$AwakeAlexaActivity$btivB_awLuCQadzXgU6NIGaU7sk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initViews$0$AwakeAlexaActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initViews$0$AwakeAlexaActivity(View view) {
        onBackPressed();
    }

    @OnClick({R.id.rtv_close})
    public void onCloseClick() {
        onBackPressed();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        setResult(-1);
        finish();
    }
}