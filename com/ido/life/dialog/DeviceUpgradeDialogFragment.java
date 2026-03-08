package com.ido.life.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.DotView;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceUpgradeDialogFragment extends BaseDialogFragment {
    private static final String COUNT = "count";
    private static final String MODE = "MODE";
    private static final String TYPE = "type";
    private static ClickListener sListener;

    @BindView(R.id.dot_view_right)
    DotView mDotViewRight;

    @BindView(R.id.iv_fireware_upgrade_result)
    AppCompatImageView mIvFirewareUpgradeSuccessWaiting;

    @BindView(R.id.iv_status_right)
    AppCompatImageView mIvStatusRight;

    @BindView(R.id.layout_upgrade_progress)
    LinearLayout mLayoutUpgradeProgress;

    @BindView(R.id.layout_upgrade_result)
    LinearLayout mLayoutUpgradeResult;

    @BindView(R.id.layout_upgrade_status)
    LinearLayout mLayoutUpgradeStatus;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.tv_close)
    MediumTextView mTvClose;

    @BindView(R.id.tv_fireware_success_waiting_time_tip)
    MediumTextView mTvFirewareSuccessWaitingTimeTip;

    @BindView(R.id.tv_fireware_success_waiting_tip)
    MediumTextView mTvFirewareSuccessWaitingTip;

    @BindView(R.id.tv_progress_value)
    RegularTextView mTvProgressValue;

    @BindView(R.id.tv_retry)
    MediumTextView mTvRetry;

    @BindView(R.id.rtv_upgrade_ing)
    RegularTextView mTvUpgradeIng;

    @BindView(R.id.rtv_upgrade_type_name)
    RegularTextView mTvUpgradeName;
    int type_upgrade;
    int mode = 1;
    int count = 1;
    int currentIndex = 1;
    boolean isStartDotAnimal = false;
    boolean isWaiting = false;

    public interface ClickListener {
        void onCloseClick();

        void onRetryClick();
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_device_upgrade;
    }

    public static DeviceUpgradeDialogFragment getInstance(int i, int i2, int i3) {
        return getInstance(i, i2, i3, null);
    }

    public static DeviceUpgradeDialogFragment getInstance(int i, int i2, int i3, ClickListener clickListener) {
        sListener = clickListener;
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        bundle.putInt(MODE, i2);
        bundle.putInt(COUNT, i3);
        DeviceUpgradeDialogFragment deviceUpgradeDialogFragment = new DeviceUpgradeDialogFragment();
        deviceUpgradeDialogFragment.setArguments(bundle);
        deviceUpgradeDialogFragment.setStyle(1, 2131886083);
        deviceUpgradeDialogFragment.setCancelable(false);
        return deviceUpgradeDialogFragment;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.type_upgrade = arguments.getInt("type");
            this.mode = arguments.getInt(MODE);
            this.count = arguments.getInt(COUNT);
            int i = this.type_upgrade;
            if (i == 1) {
                if (this.mode == 2) {
                    this.mTvUpgradeIng.setVisibility(0);
                    this.mTvUpgradeName.setText(R.string.device_upgrade);
                    this.mTvUpgradeIng.setVisibility(0);
                    this.mTvUpgradeIng.setText(LanguageUtil.getLanguageText(R.string.upgrading_fireware) + " (" + this.currentIndex + "/" + this.count + ")");
                    return;
                }
                this.mTvUpgradeName.setText(R.string.firmware_upgrade);
                return;
            }
            if (i == 2) {
                if (this.mode == 2) {
                    this.mTvUpgradeName.setText(R.string.device_upgrade);
                    this.mTvUpgradeIng.setVisibility(0);
                    this.mTvUpgradeIng.setText(LanguageUtil.getLanguageText(R.string.upgrading_resource) + " (" + this.currentIndex + "/" + this.count + ")");
                    return;
                }
                this.mTvUpgradeName.setText(R.string.resource_upgrade);
                return;
            }
            if (i != 4) {
                return;
            }
            if (this.mode == 2) {
                this.mTvUpgradeName.setText(R.string.device_upgrade);
                this.mTvUpgradeIng.setVisibility(0);
                this.mTvUpgradeIng.setText(LanguageUtil.getLanguageText(R.string.upgrading_system_contitusent) + " (" + this.currentIndex + "/" + this.count + ")");
                return;
            }
            this.mTvUpgradeName.setText(R.string.system_component_upgrade);
        }
    }

    @OnClick({R.id.tv_retry, R.id.tv_close})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id != R.id.tv_close) {
            if (id == R.id.tv_retry && sListener != null) {
                if (this.mode == 2 && this.isWaiting) {
                    resetView();
                }
                sListener.onRetryClick();
                return;
            }
            return;
        }
        dismissAllowingStateLoss();
        ClickListener clickListener = sListener;
        if (clickListener != null) {
            clickListener.onCloseClick();
        }
    }

    public void setFontTimeOutView(int i) {
        this.type_upgrade = i;
        this.mTvUpgradeName.setText(R.string.device_upgrade);
        this.mTvUpgradeIng.setVisibility(0);
        if (i == 2) {
            this.mTvUpgradeIng.setText(LanguageUtil.getLanguageText(R.string.upgrading_resource) + " (" + this.currentIndex + "/" + this.count + ")");
        } else if (i == 4) {
            this.mTvUpgradeIng.setText(LanguageUtil.getLanguageText(R.string.upgrading_system_contitusent) + " (" + this.currentIndex + "/" + this.count + ")");
        }
        this.mIvFirewareUpgradeSuccessWaiting.setVisibility(0);
        this.mIvFirewareUpgradeSuccessWaiting.setImageResource(R.mipmap.icon_watch_fail);
        this.mTvFirewareSuccessWaitingTimeTip.setVisibility(8);
        this.mTvFirewareSuccessWaitingTip.setVisibility(8);
        this.mLayoutUpgradeResult.setVisibility(0);
        this.mTvRetry.setVisibility(0);
        this.mTvProgressValue.setText(R.string.upgrade_timeout_connect_fail);
    }

    public void startUpgradeFont(int i) {
        this.isWaiting = false;
        this.type_upgrade = i;
        this.mTvUpgradeName.setText(R.string.device_upgrade);
        this.mTvUpgradeIng.setVisibility(0);
        this.currentIndex++;
        if (i == 2) {
            this.mTvUpgradeIng.setText(LanguageUtil.getLanguageText(R.string.upgrading_resource) + " (" + this.currentIndex + "/" + this.count + ")");
        } else if (i == 4) {
            this.mTvUpgradeIng.setText(LanguageUtil.getLanguageText(R.string.upgrading_system_contitusent) + " (" + this.currentIndex + "/" + this.count + ")");
        }
        this.mLayoutUpgradeStatus.setVisibility(0);
        this.mIvFirewareUpgradeSuccessWaiting.setVisibility(8);
        this.mTvFirewareSuccessWaitingTimeTip.setVisibility(8);
        this.mTvFirewareSuccessWaitingTip.setVisibility(8);
        startUpgrade(100);
    }

    public void waitingView() {
        this.mLayoutUpgradeStatus.setVisibility(8);
        this.mIvFirewareUpgradeSuccessWaiting.setVisibility(0);
        this.mIvFirewareUpgradeSuccessWaiting.setImageResource(R.mipmap.icon_watch_success);
        this.mTvFirewareSuccessWaitingTimeTip.setVisibility(0);
        this.mTvFirewareSuccessWaitingTip.setVisibility(0);
        this.isWaiting = true;
    }

    private void resetView() {
        this.mLayoutUpgradeStatus.setVisibility(0);
        this.mIvFirewareUpgradeSuccessWaiting.setVisibility(8);
        this.mIvStatusRight.setVisibility(0);
        this.mIvStatusRight.setImageResource(R.mipmap.icon_upgrade_failed);
        this.mDotViewRight.setVisibility(8);
    }

    public void startUpgrade(int i) {
        try {
            this.isStartDotAnimal = false;
            this.mIvStatusRight.setVisibility(8);
            this.mDotViewRight.setVisibility(0);
            this.mDotViewRight.setCheckedColor(ResourceUtil.getColor(R.color.black));
            this.mDotViewRight.setDotDefaultColor(ResourceUtil.getColor(R.color.color_EEEEF0));
            this.mDotViewRight.setCheckedIndex(1, true);
            startAnimation();
            setProgress(i, 0);
            this.mLayoutUpgradeProgress.setVisibility(0);
            this.mLayoutUpgradeResult.setVisibility(8);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void startAnimation() {
        try {
            if (this.isStartDotAnimal) {
                return;
            }
            this.isStartDotAnimal = true;
            this.mIvStatusRight.setVisibility(8);
            this.mDotViewRight.setVisibility(0);
            this.mDotViewRight.startAnimation();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void stopAnimation() {
        this.isStartDotAnimal = false;
        DotView dotView = this.mDotViewRight;
        if (dotView != null) {
            dotView.stopAnimation();
        }
    }

    public void setProgress(int i, int i2) {
        int i3;
        LinearLayout linearLayout = this.mLayoutUpgradeProgress;
        if (linearLayout != null && !linearLayout.isShown()) {
            this.mLayoutUpgradeProgress.setVisibility(0);
        }
        LinearLayout linearLayout2 = this.mLayoutUpgradeStatus;
        if (linearLayout2 != null && !linearLayout2.isShown()) {
            this.mLayoutUpgradeStatus.setVisibility(0);
        }
        AppCompatImageView appCompatImageView = this.mIvFirewareUpgradeSuccessWaiting;
        if (appCompatImageView != null && appCompatImageView.getVisibility() == 0) {
            this.mIvFirewareUpgradeSuccessWaiting.setVisibility(8);
        }
        RegularTextView regularTextView = this.mTvProgressValue;
        if (regularTextView == null) {
            i3 = 0;
        } else if (i == 100) {
            i3 = i2 / 2;
            regularTextView.setText(String.format(LanguageUtil.getLanguageText(R.string.public_downloading), Integer.valueOf(i2)));
        } else {
            i3 = (i2 / 2) + 50;
            regularTextView.setText(String.format(LanguageUtil.getLanguageText(R.string.device_sending_x), Integer.valueOf(i2)));
        }
        ProgressBar progressBar = this.mProgressBar;
        if (progressBar != null) {
            progressBar.setProgress(i3);
        }
    }

    public void setUpgradeResult(int i, boolean z) {
        try {
            if (i != 100) {
                this.mDotViewRight.stopAnimation();
                this.mDotViewRight.setVisibility(8);
                this.mIvStatusRight.setVisibility(0);
                this.mTvProgressValue.setVisibility(0);
                this.mTvClose.setVisibility(0);
                if (z) {
                    if (this.mode == 2 && this.currentIndex < this.count) {
                        this.mTvClose.setVisibility(8);
                    }
                    this.mTvRetry.setVisibility(8);
                    this.mTvProgressValue.setText(R.string.send_success_firewrare_check);
                    this.mIvStatusRight.setImageResource(R.mipmap.icon_upgrade_success);
                } else {
                    this.mTvRetry.setVisibility(0);
                    this.mTvProgressValue.setText(R.string.send_failed);
                    this.mIvStatusRight.setImageResource(R.mipmap.icon_upgrade_failed);
                }
            } else if (z) {
                this.mTvRetry.setVisibility(8);
                this.mTvClose.setVisibility(8);
            } else {
                this.mTvClose.setVisibility(0);
                this.mTvRetry.setVisibility(0);
                this.mTvProgressValue.setText(R.string.download_failed);
                this.mIvStatusRight.setImageResource(R.mipmap.icon_upgrade_failed);
                this.mDotViewRight.setVisibility(8);
            }
            if (i == 100 && z) {
                return;
            }
            this.mLayoutUpgradeResult.setVisibility(0);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}