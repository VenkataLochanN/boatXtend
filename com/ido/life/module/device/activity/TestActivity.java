package com.ido.life.module.device.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.util.AgpsUpgradeHelper;

/* JADX INFO: loaded from: classes2.dex */
public class TestActivity extends BaseActivity<BaseCmdPresenter> implements AgpsUpgradeHelper.AgpsUpgradeListener {

    @BindView(R.id.et_cmd)
    EditText mEtCmd;

    @BindView(R.id.layout_agps_upgrade)
    LinearLayout mLayoutAgpsUpgrade;

    @BindView(R.id.tv_send)
    TextView mTvSend;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_test;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setTitle("Test");
        this.mLayoutAgpsUpgrade.setVisibility(AgpsUpgradeHelper.getInstance().isSupportAgpsUpgrade() ? 0 : 8);
    }

    @OnClick({R.id.item_agps_online, R.id.item_agps_offline, R.id.tv_send})
    public void onViewClicked(View view) {
        if (!((BaseCmdPresenter) this.mPresenter).isBindAndConnected()) {
            showToast(R.string.device_pls_connect_device);
        }
        switch (view.getId()) {
            case R.id.item_agps_offline /* 2131362376 */:
                AgpsUpgradeHelper.getInstance().startAgpsUpgrade(2, this);
                break;
            case R.id.item_agps_online /* 2131362377 */:
                AgpsUpgradeHelper.getInstance().startAgpsUpgrade(1, this);
                break;
            case R.id.tv_send /* 2131364138 */:
                String strTrim = this.mEtCmd.getText().toString().trim();
                if (!TextUtils.isEmpty(strTrim)) {
                    BLEManager.sendCustomBytesDataToDevice(strTrim);
                }
                break;
        }
    }

    @Override // com.ido.life.util.AgpsUpgradeHelper.AgpsUpgradeListener
    public void onAgpsUpgradeComplete(final int i) {
        this.mLayoutAgpsUpgrade.post(new Runnable() { // from class: com.ido.life.module.device.activity.-$$Lambda$TestActivity$hE2E0YAPGllzLQ9xo6tWE6s5cwA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onAgpsUpgradeComplete$0$TestActivity(i);
            }
        });
    }

    public /* synthetic */ void lambda$onAgpsUpgradeComplete$0$TestActivity(int i) {
        if (i == 0) {
            showToast("更新成功");
            return;
        }
        if (i == 1) {
            showToast("下载成功");
        } else if (i == 2) {
            showToast("下载失败");
        } else {
            if (i != 3) {
                return;
            }
            showToast("更新失败");
        }
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        AgpsUpgradeHelper.getInstance().removeAgpsUpgradeListener();
    }
}