package com.ido.life.autotest;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.common.base.BaseCoreActivity;

/* JADX INFO: loaded from: classes2.dex */
public class AutoTestActivity extends BaseCoreActivity {

    @BindView(R.id.et_auto_test)
    EditText inputCmdEt;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.btn_auto_test)
    Button sendButton;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_auto_test;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initViews() {
        super.initViews();
        this.mTitleText.setText("自动化测试");
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
    }

    @OnClick({R.id.btn_auto_test})
    void sendCmdToDevice() {
        if (!BLEManager.isConnected()) {
            Toast.makeText(this, "设备未连接！", 0).show();
            return;
        }
        String string = this.inputCmdEt.getText().toString();
        if (TextUtils.isEmpty(string)) {
            Toast.makeText(this, "输入的命令不能为空！", 0).show();
        } else {
            BLEManager.sendCustomBytesDataToDevice(string);
            Toast.makeText(this, "命令发送成功", 0).show();
        }
    }

    @OnClick({R.id.title_leftBtn})
    public void toBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }
}