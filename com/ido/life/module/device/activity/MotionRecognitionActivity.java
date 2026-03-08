package com.ido.life.module.device.activity;

import android.view.View;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.ActivitySwitch;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.MotionRecognitionPresenter;
import com.ido.life.module.device.view.IMotionRecognitionView;
import com.ido.life.module.home.HomeFragmentPresenter;

/* JADX INFO: loaded from: classes2.dex */
public class MotionRecognitionActivity extends BaseActivity<MotionRecognitionPresenter> implements IMotionRecognitionView {
    private ActivitySwitch defaultState;

    @BindView(R.id.item_motion_intelligent_recognition)
    CustomItemLabelView mItemMotionIntelligentRecognition;

    @BindView(R.id.item_run)
    CustomItemLabelView mItemRun;

    @BindView(R.id.item_walk)
    CustomItemLabelView mItemWalk;
    private ActivitySwitch mMotionRecognitionState;

    @BindView(R.id.rtv_motion_intelligent_recognition_tip)
    RegularTextView mRtvMotionIntelligentRecognitionTip;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_motion_recognition;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mMotionRecognitionState = ((MotionRecognitionPresenter) this.mPresenter).getMotionRecognitionState();
        this.defaultState = ((MotionRecognitionPresenter) this.mPresenter).getMotionRecognitionState();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$MotionRecognitionActivity$fzUiGaphRjE2HmiuoGPjFUtu1nE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$MotionRecognitionActivity(view);
            }
        });
        initDataState();
        initSwitchEvent();
    }

    public /* synthetic */ void lambda$initEvent$0$MotionRecognitionActivity(View view) {
        onBackPressed();
    }

    private void initSwitchEvent() {
        this.mItemRun.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$MotionRecognitionActivity$yQ_KIfUGn1tX9dIfvKS7TCB8Dis
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$1$MotionRecognitionActivity(view, z);
            }
        });
        this.mItemWalk.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$MotionRecognitionActivity$MOG1mAV96xo6FaaXdRqVQZnKyas
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$2$MotionRecognitionActivity(view, z);
            }
        });
    }

    public /* synthetic */ void lambda$initSwitchEvent$1$MotionRecognitionActivity(View view, boolean z) {
        this.mMotionRecognitionState.autoIdentifySportRun = z ? 1 : 0;
    }

    public /* synthetic */ void lambda$initSwitchEvent$2$MotionRecognitionActivity(View view, boolean z) {
        this.mMotionRecognitionState.autoIdentifySportWalk = z ? 1 : 0;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_intelligent_motion));
        this.mItemMotionIntelligentRecognition.setTitle(getLanguageText(R.string.device_motion_intelligent_recognition));
        this.mRtvMotionIntelligentRecognitionTip.setText(getLanguageText(R.string.device_motion_intelligent_recognition_tip_android));
        this.mItemRun.setTitle(getLanguageText(R.string.sport_tab_run));
        this.mItemWalk.setTitle(getLanguageText(R.string.sport_tab_walk));
    }

    private void initDataState() {
        this.mItemRun.setSwitchStatus(this.mMotionRecognitionState.autoIdentifySportRun == 1);
        this.mItemWalk.setSwitchStatus(this.mMotionRecognitionState.autoIdentifySportWalk == 1);
    }

    private boolean isDataChanged() {
        return !this.mMotionRecognitionState.toString().equals(this.defaultState.toString());
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        if (isDataChanged()) {
            if (HomeFragmentPresenter.mIsSyncing) {
                showToast(getLanguageText(R.string.syncing_pls_try_again_later));
            } else {
                ((MotionRecognitionPresenter) this.mPresenter).sendMotionRecognition2Device(this.mMotionRecognitionState);
            }
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        saveData();
    }

    @Override // com.ido.life.module.device.view.IMotionRecognitionView
    public void onSetMotionRecognitionSuccess() {
        CommonLogUtil.d("onSetMotionRecognitionSuccess");
    }

    @Override // com.ido.life.module.device.view.IMotionRecognitionView
    public void onSetMotionRecognitionFailed() {
        showCmdResultToast(false);
    }
}