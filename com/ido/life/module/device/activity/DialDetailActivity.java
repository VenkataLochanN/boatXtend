package com.ido.life.module.device.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.NestedScrollView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.common.IdoApp;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.ImageLoaderUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.ExpandLayout;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.LoadingTextView;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.module.device.fragment.MyDialFragment;
import com.ido.life.module.device.presenter.BaseDialPresenter;
import com.ido.life.module.device.presenter.DialDetailPresenter;
import com.ido.life.module.device.view.DialDetailView;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.util.DevicePicUtils;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialDetailActivity extends BaseActivity<DialDetailPresenter> implements DialDetailView {
    public static final String COME_FROM_DEVICE_INFO_ACTIVITY = "come_from_device_info_activity";
    public static final String COME_FROM_MY_DIAL = "come_from_my_dial";
    private static final long DELAY_MILLIS = 1500;
    public static final String DIAL = "dial";
    private static final int ITEM_ADD_AND_INSTALL = 1;
    private static final int ITEM_DELETE_DIAL = 5;
    private static final int ITEM_INSTALL_TO_DEVICE = 3;
    private static final int ITEM_REMOVE_FROM_DEVICE = 4;
    private static final int ITEM_SET_CURRENT_DIAL = 2;
    private int clickedItem;
    private String currentDialName;
    private boolean installed;
    private boolean isBuiltInDial;
    private long loadingStartTime;
    private boolean mComeFromDeviceInfo;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;
    private CommBottomConfirmDialog mDialDeleteDialog;
    private MyDialListEntity.DialInfo mDialInfo;
    private CommBottomConfirmDialog mDialMemoryFullDialog;

    @BindView(R.id.divider_bottom)
    View mDividerBottom;

    @BindView(R.id.divider_top)
    View mDividerTop;
    private boolean mIsBracelet;
    private boolean mIsCurrentDial;

    @BindView(R.id.iv_dial)
    ImageView mIvDial;

    @BindView(R.id.iv_loading)
    AppCompatImageView mIvLoading;

    @BindView(R.id.layout_bottom)
    LinearLayout mLayoutBottom;

    @BindView(R.id.layout_description)
    LinearLayout mLayoutDesc;

    @BindView(R.id.layout_dial_info)
    RelativeLayout mLayoutDialInfo;

    @BindView(R.id.layout_network_error)
    LinearLayout mLayoutNetworkError;

    @BindView(R.id.mtv_description)
    ExpandLayout mMtvDescription;

    @BindView(R.id.mtv_dial_name)
    TextView mMtvDialName;

    @BindView(R.id.mtv_dial_size)
    TextView mMtvDialSize;
    private boolean mOwned;
    private float mRatio;

    @BindView(R.id.rtv_add_and_install)
    RegularTextView mRtvAddAndInstall;

    @BindView(R.id.scroll_view)
    NestedScrollView mScrollView;

    @BindView(R.id.view_delete_dial)
    LoadingTextView mViewDeleteDial;

    @BindView(R.id.view_set_use)
    LoadingTextView mViewSetUse;

    @BindView(R.id.rtl_dial)
    RelativeLayout rtlDial;
    private int installedVersion = Integer.MAX_VALUE;
    private final Runnable mDelayRunnable = new Runnable() { // from class: com.ido.life.module.device.activity.-$$Lambda$DialDetailActivity$Owx7eJJ3fkP2nhfloOWEw3lm_Bc
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$DialDetailActivity();
        }
    };
    private boolean mComeFromMydial = false;
    private boolean mIsRemovedFromServer = false;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_dial_detail;
    }

    @Override // com.ido.life.module.device.view.DialDetailView
    public void onGetDialState(boolean z) {
    }

    public /* synthetic */ void lambda$new$0$DialDetailActivity() {
        if (isDestroyed()) {
            return;
        }
        refreshDialState();
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        int i;
        super.initViews();
        this.mIsBracelet = ((DialDetailPresenter) this.mPresenter).isBracelet();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.rtlDial.getLayoutParams();
        layoutParams.width = ScreenUtil.getScreenW() / (this.mIsBracelet ? 4 : 3);
        this.mRatio = ((DialDetailPresenter) this.mPresenter).getDialImageAspectRatio();
        layoutParams.height = (int) (layoutParams.width * this.mRatio);
        this.rtlDial.setLayoutParams(layoutParams);
        RelativeLayout relativeLayout = this.rtlDial;
        if (1 == ((DialDetailPresenter) this.mPresenter).getDeviceShape()) {
            i = R.drawable.dial_frame_circle_bg;
        } else {
            i = ((DialDetailPresenter) this.mPresenter).isBracelet() ? R.drawable.dial_frame_bracelet_bg : R.drawable.dial_frame_watch_bg;
        }
        relativeLayout.setBackgroundResource(i);
        if (1 == ((DialDetailPresenter) this.mPresenter).getDeviceShape()) {
            this.mIvDial.setImageResource(R.mipmap.ic_default_dial_oval);
        } else if (this.mIsBracelet) {
            this.mIvDial.setImageResource(R.mipmap.ic_default_dial_bracelet);
        } else {
            this.mIvDial.setImageResource(R.mipmap.ic_default_dial_rectangle);
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mDialInfo = (MyDialListEntity.DialInfo) getIntent().getSerializableExtra("dial");
        this.mComeFromMydial = getIntent().getBooleanExtra("come_from_my_dial", false);
        this.mComeFromDeviceInfo = getIntent().getBooleanExtra("come_from_device_info_activity", false);
        if (this.mDialInfo == null) {
            this.mDialInfo = new MyDialListEntity.DialInfo();
        }
        this.mScrollView.setVisibility(8);
        this.mLayoutBottom.setVisibility(8);
        this.mRtvAddAndInstall.setVisibility(0);
        this.mIsCurrentDial = this.mDialInfo.isCurrentDial;
        this.installed = this.mDialInfo.isInstalledDial;
        String otaFaceName = this.mDialInfo.getOtaFaceName();
        if ((!TextUtils.isEmpty(otaFaceName) && (otaFaceName.startsWith(BaseDialPresenter.BUILT_IN_DIAL_NAME_START) || otaFaceName.startsWith(BaseDialPresenter.BUILT_IN_DIAL_NAME_START_OLD))) || TextUtils.equals(this.mDialInfo.getFaceType(), BaseDialPresenter.DIAL_TYPE_BUILTIN)) {
            this.currentDialName = otaFaceName;
            this.isBuiltInDial = true;
            this.installed = true;
            this.mOwned = true;
            this.mDialInfo.setEnabled(true);
            ImageLoaderUtil.loadImgFillet(this.mIvDial, this.mDialInfo.getImageUrl(), (int) getResources().getDimension(this.mIsBracelet ? R.dimen.sw_dp_5 : R.dimen.sw_dp_9), 1 == ((DialDetailPresenter) this.mPresenter).getDeviceShape() ? R.mipmap.ic_default_dial_oval : this.mIsBracelet ? R.mipmap.ic_default_dial_bracelet : this.mRatio > 1.0f ? R.mipmap.ic_default_dial_rectangle : R.mipmap.ic_default_dial_square);
            this.mMtvDialName.setText(this.currentDialName);
            hideDeleteView();
            this.mLayoutDesc.setVisibility(8);
        } else {
            if (TextUtils.isEmpty(otaFaceName) || !otaFaceName.endsWith(".iwf")) {
                otaFaceName = otaFaceName + ".iwf";
            }
            this.currentDialName = otaFaceName;
            ((DialDetailPresenter) this.mPresenter).requestDialInfo(this.mDialInfo.getId());
            if (NetworkUtil.isConnected(this)) {
                this.mCommLoadingView.setVisibility(0);
            } else {
                this.mCommLoadingView.setVisibility(0);
            }
            initCollect();
        }
        if (getIntent().getBooleanExtra("come_from_my_dial", false)) {
            this.mOwned = true;
            this.installed = true;
            this.mRtvAddAndInstall.setEnabled(false);
            this.mRtvAddAndInstall.setText(getString(R.string.dial_aready_undercarriage));
            this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_diable_bg);
            if (!this.isBuiltInDial) {
                this.mTitleBar.setTitle(TextUtils.isEmpty(this.mDialInfo.getName()) ? LanguageUtil.getLanguageText(R.string.device_dial) : this.mDialInfo.getName());
                return;
            } else {
                this.mTitleBar.setTitle(LanguageUtil.getLanguageText(R.string.device_dial));
                return;
            }
        }
        this.mTitleBar.setTitle(LanguageUtil.getLanguageText(R.string.device_dial));
    }

    private void hideDeleteView() {
        this.mViewDeleteDial.setVisibility(8);
        this.mDividerBottom.setVisibility(8);
    }

    private void initCollect() {
        this.mTitleBar.initLayout(1);
        if (this.mDialInfo.isCollected()) {
            this.mTitleBar.setRightImg(R.mipmap.collect_select_icon);
        } else {
            this.mTitleBar.setRightImg(R.mipmap.collect_icon);
        }
        this.mTitleBar.setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.DialDetailActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DialDetailActivity.this.mDialInfo.isCollected()) {
                    ((DialDetailPresenter) DialDetailActivity.this.mPresenter).updateDialCollect(2);
                } else {
                    ((DialDetailPresenter) DialDetailActivity.this.mPresenter).updateDialCollect(1);
                }
            }
        });
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DialDetailActivity$gerh8kik2_qwWTjtxOHaoLdzCdg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$1$DialDetailActivity(view);
            }
        });
        ((DialDetailPresenter) this.mPresenter).getInstalledDialInfo();
        if (this.isBuiltInDial && !this.mComeFromDeviceInfo && this.mIsCurrentDial) {
            this.loadingStartTime = 0L;
        }
        this.mViewSetUse.setEnabled(!this.mIsCurrentDial);
        initState();
    }

    public /* synthetic */ void lambda$initEvent$1$DialDetailActivity(View view) {
        onBackPressed();
    }

    private void initState() {
        this.mViewSetUse.setText(getLanguageText(this.mIsCurrentDial ? R.string.device_using : R.string.device_set_current_dial));
        this.mViewDeleteDial.setText(getLanguageText(R.string.device_delete_dial));
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        updateDeleteViewState();
    }

    private void updateDeleteViewState() {
        if (this.isBuiltInDial || ((DialDetailPresenter) this.mPresenter).isOffline()) {
            return;
        }
        this.mViewDeleteDial.setVisibility(NetworkUtil.isConnected(this) ? 0 : 8);
        this.mDividerBottom.setVisibility(NetworkUtil.isConnected(this) ? 0 : 8);
    }

    private void showDeleteDialog() {
        if (!((DialDetailPresenter) this.mPresenter).isConnected()) {
            showToast(R.string.device_pls_connect_device);
            return;
        }
        if (this.mDialDeleteDialog == null) {
            this.mDialDeleteDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.device_delete_dial), getLanguageText(R.string.dial_delete_tip), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DialDetailActivity$PAdkEoxS65jbAXyqZaosZ-1N1bA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showDeleteDialog$2$DialDetailActivity(view);
                }
            });
        }
        this.mDialDeleteDialog.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showDeleteDialog$2$DialDetailActivity(View view) {
        deleteDial(false);
    }

    private void showDialMemoryFullDialog() {
        if (!((DialDetailPresenter) this.mPresenter).isConnected()) {
            showToast(R.string.device_pls_connect_device);
            return;
        }
        if (this.mDialMemoryFullDialog == null) {
            this.mDialMemoryFullDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.dial_memory_full), getLanguageText(R.string.dial_memory_full_tip), getLanguageText(R.string.go_settting), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DialDetailActivity$HmTTsv0vTYinI1Fj8FHF1QXevFo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showDialMemoryFullDialog$3$DialDetailActivity(view);
                }
            });
        }
        this.mDialMemoryFullDialog.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showDialMemoryFullDialog$3$DialDetailActivity(View view) {
        goDeleteDial();
    }

    private void deleteDial(boolean z) {
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            showToast(getLanguageText(R.string.logn_all_pleaseCheckNetWork_ios));
            return;
        }
        this.clickedItem = 5;
        this.mViewDeleteDial.startAnimation();
        this.mViewDeleteDial.setText(getLanguageText(R.string.device_deleting));
        ((DialDetailPresenter) this.mPresenter).removeDialFromDevice(this.currentDialName, z);
    }

    private void goDeleteDial() {
        startActivity(new Intent(this, (Class<?>) MyDialEditActivity.class));
    }

    @OnClick({R.id.rtv_add_and_install, R.id.view_set_use, R.id.view_delete_dial})
    public void onViewClicked(View view) {
        if (((DialDetailPresenter) this.mPresenter).isSetting) {
            return;
        }
        int id = view.getId();
        if (id == R.id.rtv_add_and_install) {
            this.clickedItem = 1;
            if (this.mDialInfo.hasNewVersion) {
                installDial2Device(this.mIsCurrentDial, true);
                return;
            } else {
                installDial2Device(true, false);
                return;
            }
        }
        if (id == R.id.view_delete_dial) {
            if (!this.mDialInfo.isEnabled() && !this.installed) {
                deleteDial(true);
                return;
            } else {
                showDeleteDialog();
                return;
            }
        }
        if (id != R.id.view_set_use) {
            return;
        }
        this.clickedItem = 2;
        if (this.mDialInfo.hasNewVersion) {
            installDial2Device(this.mIsCurrentDial, true);
        } else {
            installDial2Device(true, false);
        }
    }

    private void installDial2Device(boolean z, boolean z2) {
        if (this.installed && !z2) {
            ((DialDetailPresenter) this.mPresenter).switchBuiltInDial(this.mDialInfo);
            return;
        }
        if (this.mDialInfo.getOtaFaceVersion() == null) {
            ((DialDetailPresenter) this.mPresenter).saveDialLog("mDialInfo.getOtaFaceVersion()==null");
            showToast(getString(R.string.public_empty));
            return;
        }
        if (!((DialDetailPresenter) this.mPresenter).isConnected()) {
            showToast(R.string.device_pls_connect_device);
            return;
        }
        if (this.installed && !z2) {
            ((DialDetailPresenter) this.mPresenter).switchBuiltInDial(this.mDialInfo);
            return;
        }
        if (HomeFragmentPresenter.mIsSyncing) {
            showToast(getLanguageText(R.string.syncing_pls_try_again_later));
            return;
        }
        if (HomeFragmentPresenter.mIsTelephone) {
            showToast(getLanguageText(R.string.sport_start_incalling));
            return;
        }
        if (z2) {
            ((DialDetailPresenter) this.mPresenter).downloadDialFile(this.mDialInfo, z, z2);
        } else if (((DialDetailPresenter) this.mPresenter).hasMemoryInstall(this.mDialInfo.getOtaFaceVersion().getSize())) {
            ((DialDetailPresenter) this.mPresenter).downloadDialFile(this.mDialInfo, z, z2);
        } else {
            showDialMemoryFullDialog();
        }
    }

    private void bindDialInfo2View(MyDialListEntity.DialInfo dialInfo) {
        ImageLoaderUtil.loadImgFillet(this.mIvDial, dialInfo.getImageUrl(), (int) getResources().getDimension(this.mIsBracelet ? R.dimen.sw_dp_5 : R.dimen.sw_dp_9), 1 == ((DialDetailPresenter) this.mPresenter).getDeviceShape() ? R.mipmap.ic_default_dial_oval : this.mIsBracelet ? R.mipmap.ic_default_dial_bracelet : this.mRatio > 1.0f ? R.mipmap.ic_default_dial_rectangle : R.mipmap.ic_default_dial_square);
        this.mMtvDialName.setText(dialInfo.getName());
        String description = dialInfo.getDescription();
        this.mLayoutDesc.setVisibility(TextUtils.isEmpty(description) ? 8 : 0);
        this.mMtvDescription.setContent(description);
        this.mTitleBar.setTitle(TextUtils.isEmpty(dialInfo.getName()) ? getString(R.string.device_dial) : dialInfo.getName());
    }

    @Override // com.ido.life.module.device.view.DialDetailView
    public void onGetDialInfo(MyDialListEntity.DialInfo dialInfo) {
        dialInfo.hasNewVersion = this.mDialInfo.hasNewVersion;
        this.mDialInfo = dialInfo;
        bindDialInfo2View(dialInfo);
        if (this.mDialInfo.isCollected()) {
            this.mTitleBar.setRightImg(R.mipmap.collect_select_icon);
        } else {
            this.mTitleBar.setRightImg(R.mipmap.collect_icon);
        }
        if (this.mDialInfo.getOtaFaceVersion() != null) {
            this.mMtvDialSize.setText((this.mDialInfo.getOtaFaceVersion().getSize() / 1024) + "kb");
        }
        if (this.mDialInfo.isEnabled() || this.installed) {
            refreshDialState();
        }
        this.mIsRemovedFromServer = !this.mDialInfo.isEnabled();
        updateRemovedView();
        showDialUpdate();
    }

    private void refreshDialState() {
        this.mCommLoadingView.removeCallbacks(this.mDelayRunnable);
        this.mCommLoadingView.setVisibility(8);
        this.mScrollView.setVisibility(0);
        this.mViewSetUse.setVisibility(0);
        refreshViewState();
    }

    private void showDialUpdate() {
        try {
            MyDialListEntity.DialInfo.OtaFaceVersion otaFaceVersion = this.mDialInfo.getOtaFaceVersion();
            if (otaFaceVersion == null || Integer.parseInt(otaFaceVersion.getVersion()) <= this.installedVersion || TextUtils.isEmpty(otaFaceVersion.getLinkUrl())) {
                return;
            }
            this.mRtvAddAndInstall.setEnabled(true);
            this.mRtvAddAndInstall.setText(getLanguageText(R.string.device_update_dial));
            this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_bg);
            this.mRtvAddAndInstall.setTextColor(getColor(R.color.white));
        } catch (Exception e2) {
            ((DialDetailPresenter) this.mPresenter).saveDialLog("onGetDialInfo Exception：" + e2.toString());
        }
    }

    private void refreshViewState() {
        if (this.installed || this.mIsCurrentDial || this.mOwned) {
            this.mLayoutBottom.setVisibility(0);
            this.mLayoutDialInfo.setVisibility(0);
            this.mRtvAddAndInstall.setEnabled(false);
            this.mRtvAddAndInstall.setText(getString(R.string.device_installed));
            this.mRtvAddAndInstall.setVisibility(0);
            this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_diable_bg);
            if (!this.isBuiltInDial) {
                this.mTitleBar.setTitle(TextUtils.isEmpty(this.mDialInfo.getName()) ? LanguageUtil.getLanguageText(R.string.device_dial) : this.mDialInfo.getName());
            }
            if (((DialDetailPresenter) this.mPresenter).canDelete()) {
                updateDeleteViewState();
            } else {
                hideDeleteView();
            }
        } else {
            this.mLayoutBottom.setVisibility(8);
            if (!((DialDetailPresenter) this.mPresenter).isOffline()) {
                this.mRtvAddAndInstall.setVisibility(0);
                this.mRtvAddAndInstall.setEnabled(true);
            } else {
                this.mRtvAddAndInstall.setVisibility(8);
            }
            this.mLayoutDialInfo.setVisibility(0);
        }
        updateRemovedView();
        showDialUpdate();
    }

    private void updateRemovedView() {
        if (this.mIsRemovedFromServer) {
            this.mRtvAddAndInstall.setEnabled(false);
            this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_diable_bg);
            if (!this.installed && NetworkUtil.isConnected(this)) {
                ((DialDetailPresenter) this.mPresenter).saveDialLog("updateRemovedView 处理已下架，未安装情况");
                this.mRtvAddAndInstall.setText(getString(R.string.dial_aready_undercarriage));
                this.mViewSetUse.setVisibility(8);
                this.mDividerTop.setVisibility(8);
                this.mViewDeleteDial.setVisibility(0);
                this.mViewDeleteDial.setText(getString(R.string.dial_delete_from_record));
                this.mLayoutBottom.setVisibility(0);
                return;
            }
            ((DialDetailPresenter) this.mPresenter).saveDialLog("updateRemovedView 处理已下架，已安装情况");
            this.mRtvAddAndInstall.setText(getString(R.string.device_installed));
        }
    }

    @Override // com.ido.life.module.device.view.DialDetailView
    public void onSwitchDialStart() {
        int i = this.clickedItem;
        if (i == 1) {
            startTopAnimation();
            this.mRtvAddAndInstall.setText(getLanguageText(R.string.device_setting));
        } else {
            if (i != 2) {
                return;
            }
            this.mViewSetUse.startAnimation();
            this.mViewSetUse.setText(getLanguageText(R.string.device_setting));
        }
    }

    @Override // com.ido.life.module.device.view.DialDetailView
    public void onDialSwitched(boolean z) {
        CommonLogUtil.d("onDialSwitched : " + z);
        if (z) {
            updateSuccessView();
            this.mIsCurrentDial = true;
            this.installed = true;
            notifyDialChanged();
            onBackPressed();
            return;
        }
        showCmdResultToast(false);
        updateFailedView();
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetCurrentDial(String str) {
        if (((DialDetailPresenter) this.mPresenter).isSetting) {
            return;
        }
        if (this.loadingStartTime == 0 && !this.mLayoutNetworkError.isShown()) {
            refreshDialState();
        }
        this.loadingStartTime = 0L;
        if (((DialDetailPresenter) this.mPresenter).isSetting) {
            return;
        }
        if (!TextUtils.isEmpty(this.currentDialName)) {
            this.mIsCurrentDial = TextUtils.equals(str.split("\\.")[0], this.currentDialName.split("\\.")[0]);
        }
        if (this.mDialInfo.hasNewVersion) {
            return;
        }
        this.mViewSetUse.setEnabled(!this.mIsCurrentDial);
        this.mViewSetUse.setText(getLanguageText(this.mIsCurrentDial ? R.string.device_using : R.string.device_set_current_dial));
        refreshViewState();
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialList(List<String> list) {
        if (((DialDetailPresenter) this.mPresenter).isSetting) {
            return;
        }
        if (list.contains(this.currentDialName)) {
            this.installed = true;
        } else {
            this.installed = false;
        }
        refreshViewState();
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialListV3(List<DialPlateParam.PlateFileInfo> list) {
        if (((DialDetailPresenter) this.mPresenter).isSetting || list == null || list.isEmpty()) {
            return;
        }
        Iterator<DialPlateParam.PlateFileInfo> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DialPlateParam.PlateFileInfo next = it.next();
            if (next != null && TextUtils.equals(next.name, this.currentDialName)) {
                this.installed = true;
                this.installedVersion = next.watch_version;
                showDialUpdate();
                break;
            }
        }
        refreshViewState();
    }

    @Override // com.ido.life.module.device.view.DialDetailView
    public void onRemoveDial(boolean z) {
        if (z) {
            this.mIsCurrentDial = false;
            this.installed = false;
            updateSuccessView();
            finish();
            return;
        }
        showToast(getLanguageText(R.string.device_remove_failed));
        updateFailedView();
    }

    @Override // com.ido.life.module.device.view.DialDetailView
    public void onDeleteDial(boolean z) {
        if (z) {
            this.mIsCurrentDial = false;
            this.installed = false;
            updateSuccessView();
            finish();
            return;
        }
        showToast(getLanguageText(R.string.device_delete_failed));
        updateFailedView();
    }

    @Override // com.ido.life.module.device.view.DialDetailView
    public void onCollectDial(boolean z) {
        if (z) {
            if (this.mDialInfo.isCollected()) {
                this.mDialInfo.setCollected(false);
                this.mTitleBar.setRightImg(R.mipmap.collect_icon);
                showToast(getString(R.string.cancel_collect_success));
                return;
            } else {
                this.mDialInfo.setCollected(true);
                this.mTitleBar.setRightImg(R.mipmap.collect_select_icon);
                showToast(getString(R.string.collect_success));
                return;
            }
        }
        if (this.mDialInfo.isCollected()) {
            showToast(getString(R.string.cancel_collect_failed));
        } else {
            showToast(getString(R.string.collect_failed));
        }
    }

    @Override // com.ido.life.module.device.view.DialDetailView
    public void onInstallFailByNotMemory() {
        updateFailedView();
        showDialMemoryFullDialog();
    }

    @Override // com.ido.life.module.device.view.DialDetailView
    public void onProgress(int i, int i2) {
        int i3 = this.clickedItem;
        if (i3 == 1) {
            updateTopLabelProgress(i, i2);
        } else {
            if (i3 != 2) {
                return;
            }
            updateProgress(i, i2, this.mViewSetUse);
        }
    }

    private void updateTopLabelProgress(int i, int i2) {
        if (i2 < 100) {
            if (i2 == 0) {
                startTopAnimation();
            }
            this.mRtvAddAndInstall.setText(String.format(getLanguageText(i == 1 ? R.string.device_downloading_x : R.string.device_installing_x), Integer.valueOf(i2)));
        } else {
            this.mRtvAddAndInstall.setBackgroundResource(R.color.translate);
            if (i != 1) {
                stopTopAnimation();
                this.mRtvAddAndInstall.setText(getLanguageText(R.string.device_install_success));
            }
        }
    }

    private void stopTopAnimation() {
        this.mIvLoading.clearAnimation();
        this.mIvLoading.setImageResource(R.mipmap.icon_set_complete);
    }

    private void startTopAnimation() {
        this.mRtvAddAndInstall.setTextColor(getColor(R.color.color_545657));
        this.mRtvAddAndInstall.setBackgroundResource(R.color.translate);
        this.mIvLoading.setVisibility(0);
        this.mIvLoading.setImageResource(R.mipmap.icon_loading_dial);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 1800.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(4000L);
        rotateAnimation.setRepeatCount(-1);
        this.mIvLoading.startAnimation(rotateAnimation);
    }

    private void updateProgress(int i, int i2, LoadingTextView loadingTextView) {
        int i3 = R.string.device_downloading_x;
        if (i2 == 0) {
            loadingTextView.startAnimation();
            if (i != 1) {
                i3 = R.string.device_installing_x;
            }
            loadingTextView.setText(String.format(getLanguageText(i3), Integer.valueOf(i2)));
            return;
        }
        if (i2 != 100) {
            if (i != 1) {
                i3 = R.string.device_installing_x;
            }
            loadingTextView.setText(String.format(getLanguageText(i3), Integer.valueOf(i2)));
        } else if (i != 1) {
            loadingTextView.setImageResource(R.mipmap.icon_set_complete);
            loadingTextView.setText(getLanguageText(R.string.device_install_success));
        }
    }

    @Override // com.ido.life.module.device.view.DialDetailView
    public void onDownloadFailed() {
        ((DialDetailPresenter) this.mPresenter).saveDialLog("thread:" + Thread.currentThread());
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            showToast(getLanguageText(R.string.download_failed));
        } else {
            showToast(getLanguageText(R.string.logn_all_pleaseCheckNetWork_ios));
        }
        updateFailedView();
    }

    private void updateFailedView() {
        ((DialDetailPresenter) this.mPresenter).saveDialLog("thread:" + Thread.currentThread().getName());
        int i = this.clickedItem;
        if (i == 1) {
            this.mIvLoading.clearAnimation();
            this.mIvLoading.setVisibility(8);
            this.mRtvAddAndInstall.setText(getLanguageText(R.string.public_install));
            this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_bg);
            this.mRtvAddAndInstall.setTextColor(getColor(R.color.white));
            return;
        }
        if (i == 2) {
            this.mViewSetUse.stopAnimation();
            this.mViewSetUse.setText(getLanguageText(this.mDialInfo.hasNewVersion ? R.string.device_update_dial : R.string.device_set_current_dial));
        } else {
            if (i != 5) {
                return;
            }
            this.mViewDeleteDial.stopAnimation();
            this.mViewDeleteDial.setText(getLanguageText(R.string.device_delete_dial));
        }
    }

    private void updateSuccessView() {
        ((DialDetailPresenter) this.mPresenter).saveDialLog("thread:" + Thread.currentThread().getName());
        int i = this.clickedItem;
        if (i == 1) {
            stopTopAnimation();
            this.mRtvAddAndInstall.setText(getLanguageText(R.string.device_install_success));
        } else if (i == 2) {
            this.mViewSetUse.setImageResource(R.mipmap.icon_set_complete);
            this.mViewSetUse.setText(getLanguageText(R.string.device_set_success));
        } else {
            if (i != 5) {
                return;
            }
            this.mViewDeleteDial.setImageResource(R.mipmap.icon_set_complete);
            this.mViewDeleteDial.setText(getLanguageText(R.string.device_delete_success));
        }
    }

    @Override // com.ido.life.module.device.view.DialDetailView
    public void onDialInstallSuccess() {
        this.mIsCurrentDial = true;
        this.installed = true;
        this.mDialInfo.hasNewVersion = false;
        notifyDialChanged();
        updateSuccessView();
        onBackPressed();
    }

    private void notifyDialChanged() {
        DevicePicUtils.spSaveDial(((DialDetailPresenter) this.mPresenter).getDeviceInfo().mDeviceAddress + "", this.currentDialName, true);
    }

    @Override // com.ido.life.module.device.view.DialDetailView
    public void onDialInstallFailed() {
        showToast(getLanguageText(R.string.device_install_failed));
        updateFailedView();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (((DialDetailPresenter) this.mPresenter).isSetting) {
            showToast(getLanguageText(R.string.device_setting_not_exit));
            return;
        }
        CommonLogUtil.d("onDialSwitched : onBackPressed");
        if (this.mIsCurrentDial) {
            Intent intent = new Intent();
            if (this.isBuiltInDial) {
                intent.putExtra(MyDialFragment.DIAL_NAME, this.mDialInfo.getOtaFaceName());
            } else {
                intent.putExtra(MyDialFragment.DIAL_NAME, this.mDialInfo.getOtaFaceName() + ".iwf");
            }
            setResult(88, intent);
        }
        finish();
    }
}