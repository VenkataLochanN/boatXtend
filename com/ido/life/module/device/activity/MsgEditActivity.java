package com.ido.life.module.device.activity;

import android.content.Intent;
import android.text.Selection;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.TextLengthWatcher;

/* JADX INFO: loaded from: classes2.dex */
public class MsgEditActivity extends BaseActivity implements View.OnClickListener, TextLengthWatcher.OnTextChangedListener {
    public static final int CODE_MSG_EDIT = 11;
    public static final String INDEX = "index";
    public static final String MSG = "msg";
    private String mDefaultMsg;

    @BindView(R.id.et_msg)
    EditText mEtMsg;
    private int mIndex;

    @BindView(R.id.rtv_msg_edit_tip)
    RegularTextView mRtvMsgEditTip;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_msg_edit;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        this.mDefaultMsg = intent.getStringExtra("msg");
        this.mIndex = intent.getIntExtra("index", -1);
        this.mEtMsg.setText(this.mDefaultMsg);
        Selection.setSelection(this.mEtMsg.getText(), this.mEtMsg.getText().toString().length());
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.initLayout(1).setRightOnClick(this);
        this.mEtMsg.addTextChangedListener(new TextLengthWatcher(24, this));
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_msg_content));
        this.mRtvMsgEditTip.setText(getLanguageText(R.string.device_msg_edit_tip));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (!BLEManager.isConnected()) {
            showToast(getLanguageText(R.string.device_pls_connect_device));
            return;
        }
        String string = this.mEtMsg.getText().toString();
        if (!string.equals(this.mDefaultMsg)) {
            Intent intent = new Intent();
            intent.putExtra("msg", string);
            intent.putExtra("index", this.mIndex);
            setResult(11, intent);
        }
        finish();
    }

    @Override // com.ido.life.base.TextLengthWatcher.OnTextChangedListener
    public void onAfterTextChanged(String str) {
        this.mEtMsg.setText(str);
        Selection.setSelection(this.mEtMsg.getEditableText(), str.length());
    }
}