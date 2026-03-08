package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.OtherProtocolCallBack;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.util.HealthCareUtil;
import com.ido.life.util.SPHelper;

/* JADX INFO: loaded from: classes2.dex */
public class HealthCareActivity extends BaseActivity<BaseCmdPresenter> implements CustomToggleButton.OnSwitchListener {
    public static final int REQUEST_CODE_MENSTRUAL_SETTING = 1;
    private OtherProtocolCallBack.ICallBack mCallback = new OtherProtocolCallBack.ICallBack() { // from class: com.ido.life.module.device.activity.HealthCareActivity.1
        @Override // com.ido.ble.callback.OtherProtocolCallBack.ICallBack
        public void onSuccess(OtherProtocolCallBack.SettingType settingType) {
            int i = AnonymousClass2.$SwitchMap$com$ido$ble$callback$OtherProtocolCallBack$SettingType[settingType.ordinal()];
            if (i == 1) {
                BLEManager.setMenstrualRemind(HealthCareActivity.this.mTempMenstrualRemind);
                return;
            }
            if (i != 2) {
                return;
            }
            HealthCareActivity.this.dismissLoadingDialog();
            HealthCareActivity.this.showCmdResultToast(true);
            SPHelper.saveMenstrualData(LocalDataManager.getMenstrual());
            HealthCareActivity.this.setResult(1);
            HealthCareActivity.this.finish();
        }

        @Override // com.ido.ble.callback.OtherProtocolCallBack.ICallBack
        public void onFailed(OtherProtocolCallBack.SettingType settingType) {
            HealthCareActivity.this.dismissLoadingDialog();
            HealthCareActivity.this.showCmdResultToast(false);
        }
    };

    @BindView(R.id.item_health_care_switch)
    CustomItemLabelView mItemHealthCareSwitch;

    @BindView(R.id.item_Menstrual_set)
    CustomItemLabelView mItemMenstrualSet;

    @BindView(R.id.item_remind_set)
    CustomItemLabelView mItemRemindSet;

    @BindView(R.id.layout_content)
    LinearLayout mLayoutContent;
    private Menstrual mMenstrual;
    private MenstrualRemind mMenstrualRemind;
    private Menstrual mNewMenstrual;
    private MenstrualRemind mNewMenstrualRemind;

    @BindView(R.id.rtv_health_care_tip)
    RegularTextView mRtvHealthCareTip;
    private Menstrual mTempMenstrual;
    private MenstrualRemind mTempMenstrualRemind;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_health_care;
    }

    @Override // com.ido.life.base.BaseActivity
    protected boolean isFunctionActivity() {
        return true;
    }

    /* JADX INFO: renamed from: com.ido.life.module.device.activity.HealthCareActivity$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$ido$ble$callback$OtherProtocolCallBack$SettingType = new int[OtherProtocolCallBack.SettingType.values().length];

        static {
            try {
                $SwitchMap$com$ido$ble$callback$OtherProtocolCallBack$SettingType[OtherProtocolCallBack.SettingType.MENSTRUAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$OtherProtocolCallBack$SettingType[OtherProtocolCallBack.SettingType.MENSTRUAL_REMIND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mMenstrual = LocalDataManager.getMenstrual();
        this.mMenstrualRemind = LocalDataManager.getMenstrualRemind();
        this.mTempMenstrual = HealthCareUtil.initTempMenstrual(this.mMenstrual);
        this.mTempMenstrualRemind = HealthCareUtil.initTempMenstrualRemind(this.mMenstrualRemind);
        Menstrual menstrual = this.mMenstrual;
        if (menstrual != null && menstrual.on_off == 170) {
            this.mItemHealthCareSwitch.setSwitchStatus(true);
            this.mLayoutContent.setVisibility(0);
        } else {
            this.mItemHealthCareSwitch.setSwitchStatus(false);
            this.mLayoutContent.setVisibility(8);
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.initLayout(1).setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$HealthCareActivity$K1viyRNrTva_plpaunXhuFZHUl0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$HealthCareActivity(view);
            }
        }).setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$HealthCareActivity$PbdhpTrGViKW5IIZ7SaOUsGF0zc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$1$HealthCareActivity(view);
            }
        });
        this.mItemHealthCareSwitch.setOnSwitchListener(this);
        BLEManager.registerOtherProtocolCallBack(this.mCallback);
    }

    public /* synthetic */ void lambda$initEvent$0$HealthCareActivity(View view) {
        saveData();
    }

    public /* synthetic */ void lambda$initEvent$1$HealthCareActivity(View view) {
        onBackPressed();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_menstrual_cycle));
        this.mRtvHealthCareTip.setText(getLanguageText(R.string.device_tip_women_health));
        this.mItemHealthCareSwitch.setTitle(getLanguageText(R.string.device_menstrual_cycle));
        this.mItemMenstrualSet.setTitle(getLanguageText(R.string.device_menstrual_set));
        this.mItemRemindSet.setTitle(getLanguageText(R.string.device_remind_set));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 100) {
            this.mNewMenstrual = (Menstrual) intent.getSerializableExtra(HealthCareUtil.MENSTRUAL);
            this.mTempMenstrual = this.mNewMenstrual;
        } else {
            if (i2 != 200) {
                return;
            }
            this.mNewMenstrualRemind = (MenstrualRemind) intent.getSerializableExtra(HealthCareUtil.MENSTRUAL_REMIND);
            this.mTempMenstrualRemind = this.mNewMenstrualRemind;
        }
    }

    private boolean isDataChanged() {
        if (this.mNewMenstrual == null && this.mNewMenstrualRemind == null) {
            if (this.mMenstrual == null && this.mTempMenstrual.on_off == 85) {
                return false;
            }
            Menstrual menstrual = this.mMenstrual;
            return menstrual == null || menstrual.on_off != this.mTempMenstrual.on_off;
        }
        Menstrual menstrual2 = this.mMenstrual;
        if (menstrual2 == null || this.mMenstrualRemind == null) {
            return true;
        }
        return (HealthCareUtil.compareAttributes(menstrual2, this.mTempMenstrual) && HealthCareUtil.compareAttributes(this.mMenstrualRemind, this.mTempMenstrualRemind)) ? false : true;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        if (this.mNewMenstrual == null && this.mNewMenstrualRemind == null) {
            if ((this.mMenstrual == null && this.mTempMenstrual.on_off == 85) || this.mMenstrual.on_off == this.mTempMenstrual.on_off) {
                finish();
                return;
            } else {
                showLoadingDialog();
                BLEManager.setMenstrual(this.mTempMenstrual);
                return;
            }
        }
        showLoadingDialog();
        BLEManager.setMenstrual(this.mTempMenstrual);
    }

    @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
    public void onSwitched(View view, boolean z) {
        this.mLayoutContent.setVisibility(z ? 0 : 8);
        this.mTempMenstrual.on_off = z ? 170 : 85;
    }

    @OnClick({R.id.item_Menstrual_set, R.id.item_remind_set})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.item_Menstrual_set) {
            Intent intent = new Intent(this, (Class<?>) MenstrualSetActivity.class);
            intent.putExtra(HealthCareUtil.MENSTRUAL, this.mTempMenstrual);
            startActivityForResult(intent, 100);
        } else {
            if (id != R.id.item_remind_set) {
                return;
            }
            Intent intent2 = new Intent(this, (Class<?>) MenstrualRemindSetActivity.class);
            intent2.putExtra(HealthCareUtil.MENSTRUAL_REMIND, this.mTempMenstrualRemind);
            intent2.putExtra(HealthCareUtil.MENSTRUAL, this.mTempMenstrual);
            startActivityForResult(intent2, 200);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (isDataChanged()) {
            showDataChangedDialog();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        BLEManager.unregisterOtherProtocolCallBack(this.mCallback);
    }
}