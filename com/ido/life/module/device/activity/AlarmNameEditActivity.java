package com.ido.life.module.device.activity;

import android.content.Intent;
import android.text.Selection;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.TextLengthWatcher;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmNameEditActivity extends BaseActivity implements View.OnClickListener, TextLengthWatcher.OnTextChangedListener {
    public static final String ALARM_NAME = "alarm_name";
    public static final int CODE_ALARM_NAME = 22;

    @BindView(R.id.et_alarm_name)
    EditText mEtAlarmName;

    @BindView(R.id.rtv_alarm_tip)
    RegularTextView mRtvAlarmTip;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_alarm_name_edit;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mEtAlarmName.setText(getIntent().getStringExtra(ALARM_NAME));
        Selection.setSelection(this.mEtAlarmName.getEditableText(), this.mEtAlarmName.getText().length());
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.initLayout(1).setRightOnClick(this);
        this.mEtAlarmName.addTextChangedListener(new TextLengthWatcher(22, this));
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_alarm_name));
        this.mRtvAlarmTip.setText(getLanguageText(R.string.device_alarm_name_edit_tip));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra(ALARM_NAME, this.mEtAlarmName.getText().toString());
        setResult(22, intent);
        finish();
    }

    @Override // com.ido.life.base.TextLengthWatcher.OnTextChangedListener
    public void onAfterTextChanged(String str) {
        this.mEtAlarmName.setText(str);
        Selection.setSelection(this.mEtAlarmName.getEditableText(), str.length());
    }
}