package com.ido.life.module.device.fragment;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.ImageLoaderUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseFragment;
import com.ido.life.base.BaseMessage;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.bean.AppBLEDevice;
import com.ido.life.ble.BleHelper;
import com.ido.life.customview.recyclerview.BaseLinearLayoutManager;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.HeaderAndFooterWrapper;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.module.alexa.AlexaLoginActivity;
import com.ido.life.module.bind.ChoiceBlueTypeActivity;
import com.ido.life.module.bind.scan.ScanCodeActivity;
import com.ido.life.module.device.activity.DeviceInfoActivity;
import com.ido.life.module.device.activity.DeviceUpgradeNewActivity;
import com.ido.life.module.device.activity.DialMarketActivity;
import com.ido.life.module.device.presenter.DeviceFragmentPresenter;
import com.ido.life.module.device.view.IDeviceView;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.util.ConnectLogHelper;
import com.ido.smartrefresh.layout.SmartRefreshLayout;
import com.ido.smartrefresh.layout.api.RefreshLayout;
import com.ido.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceFragment extends BaseFragment<DeviceFragmentPresenter> implements IDeviceView, OnRefreshListener, View.OnClickListener {
    private CommonRecyclerViewAdapter<DeviceListEntity.DeviceInfo> mAdapter;
    private DeviceListEntity.DeviceInfo mConnectingDevice;
    private List<DeviceListEntity.DeviceInfo> mDeviceList;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private View mItemFootAlexa;
    private View mItemFootRecommend;

    @BindView(R.id.iv_scan)
    ImageView mIvScan;

    @BindView(R.id.layout_device_list)
    LinearLayout mLayoutDeviceList;

    @BindView(R.id.layout_header_add_device)
    RelativeLayout mLayoutHeaderAddDevice;
    private CommBottomConfirmDialog mReconnectDialog;

    @BindView(R.id.recycler_view_device)
    RecyclerView mRecyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.rtv_device_title)
    TextView mRtvTitle;

    @BindView(R.id.rl_fg_device)
    RelativeLayout rlFgDevice;

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_device;
    }

    @Override // com.ido.life.base.BaseFragment
    protected boolean needEventBus() {
        return true;
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void initView() {
        super.initView();
        this.mDeviceList = new ArrayList();
        this.mRecyclerView.setLayoutManager(new BaseLinearLayoutManager(this.mActivity));
        this.mAdapter = new CommonRecyclerViewAdapter<DeviceListEntity.DeviceInfo>(this.mActivity, R.layout.item_device_record, this.mDeviceList) { // from class: com.ido.life.module.device.fragment.DeviceFragment.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, DeviceListEntity.DeviceInfo deviceInfo, int i) {
                DeviceFragment.this.bindData2View(commonRecyclerViewHolder, deviceInfo, i);
            }
        };
        this.mItemFootRecommend = LayoutInflater.from(this.mActivity).inflate(R.layout.item_foot_recommend, (ViewGroup) this.mRecyclerView, false);
        this.mItemFootAlexa = LayoutInflater.from(this.mActivity).inflate(R.layout.item_foot_alexa, (ViewGroup) this.mRecyclerView, false);
        this.mItemFootRecommend.setAlpha(((DeviceFragmentPresenter) this.mPresenter).isConnected() ? 1.0f : 0.3f);
        this.mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(this.mAdapter);
        this.mHeaderAndFooterWrapper.addFootView(this.mItemFootRecommend);
        this.mHeaderAndFooterWrapper.addFootView(this.mItemFootAlexa);
        this.mRecyclerView.setAdapter(this.mHeaderAndFooterWrapper);
        this.mRefreshLayout.setOnRefreshListener(this);
        this.mItemFootAlexa.setId(R.id.item_foot_alexa);
        this.mItemFootRecommend.setOnClickListener(this);
        this.mItemFootAlexa.setOnClickListener(this);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDeviceFragmentLogPath(), "Alexa显示状态：initView()");
        this.mItemFootAlexa.setVisibility(((DeviceFragmentPresenter) this.mPresenter).isSupportAlex() ? 0 : 8);
        this.mItemFootAlexa.setAlpha(((DeviceFragmentPresenter) this.mPresenter).isSupportAlexConnected() ? 1.0f : 0.3f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindData2View(CommonRecyclerViewHolder commonRecyclerViewHolder, final DeviceListEntity.DeviceInfo deviceInfo, int i) {
        if (deviceInfo == null) {
            return;
        }
        ((MediumTextView) commonRecyclerViewHolder.getView(R.id.mtv_name)).setText(deviceInfo.getCustomName());
        MediumTextView mediumTextView = (MediumTextView) commonRecyclerViewHolder.getView(R.id.mtv_status);
        RegularTextView regularTextView = (RegularTextView) commonRecyclerViewHolder.getView(R.id.rtv_battery);
        RegularTextView regularTextView2 = (RegularTextView) commonRecyclerViewHolder.getView(R.id.rtv_relink);
        MediumTextView mediumTextView2 = (MediumTextView) commonRecyclerViewHolder.getView(R.id.mtv_version);
        LinearLayout linearLayout = (LinearLayout) commonRecyclerViewHolder.getView(R.id.layout_connect);
        AppCompatImageView appCompatImageView = (AppCompatImageView) commonRecyclerViewHolder.getView(R.id.iv_connecting);
        RelativeLayout relativeLayout = (RelativeLayout) commonRecyclerViewHolder.getView(R.id.layout_version);
        AppCompatImageView appCompatImageView2 = (AppCompatImageView) commonRecyclerViewHolder.getView(R.id.iv_dot);
        String deviceImageUrl = ((DeviceFragmentPresenter) this.mPresenter).getDeviceImageUrl(deviceInfo.getDeviceId());
        deviceInfo.setImageUrl(deviceImageUrl);
        ImageLoaderUtil.loadImage((ImageView) commonRecyclerViewHolder.getView(R.id.iv_device), deviceImageUrl, deviceInfo.type == 3 ? R.mipmap.icon_type_bracelet : R.mipmap.icon_type_watch);
        AppBLEDevice appBleDevice = ((DeviceFragmentPresenter) this.mPresenter).getAppBleDevice();
        if (((DeviceFragmentPresenter) this.mPresenter).isConnected() && appBleDevice != null && appBleDevice.mDeviceAddress.equals(deviceInfo.getMac())) {
            mediumTextView.setText(getLanguageText(R.string.device_connected));
            regularTextView.setVisibility(0);
            regularTextView.setText(String.format(getString(R.string.battery_percent), Integer.valueOf(((DeviceFragmentPresenter) this.mPresenter).getDeviceBattery())));
            regularTextView.setCompoundDrawablesWithIntrinsicBounds(this.mActivity.getDrawable(((DeviceFragmentPresenter) this.mPresenter).getBatteryIconResByPower(((DeviceFragmentPresenter) this.mPresenter).getDeviceBattery())), (Drawable) null, (Drawable) null, (Drawable) null);
            regularTextView2.setVisibility(8);
            linearLayout.setVisibility(8);
            relativeLayout.setVisibility(0);
            appCompatImageView2.setVisibility(HomeFragmentPresenter.hasNewDeviceVersion ? 0 : 8);
            mediumTextView2.setText(String.format(getString(R.string.device_version_code), appBleDevice.getDeviceThirdVersion()));
        } else {
            DeviceListEntity.DeviceInfo deviceInfo2 = this.mConnectingDevice;
            if (deviceInfo2 != null && deviceInfo2.getMac().equals(deviceInfo.getMac())) {
                startAnimation(appCompatImageView);
                regularTextView2.setText(getLanguageText(R.string.device_in_connecting));
            } else {
                stopAnimation(appCompatImageView);
                regularTextView2.setText(getLanguageText(R.string.device_relink));
            }
            linearLayout.setVisibility(0);
            regularTextView2.setVisibility(0);
            relativeLayout.setVisibility(8);
            regularTextView.setVisibility(8);
            mediumTextView.setText(getLanguageText(R.string.device_disconnected));
        }
        View view = commonRecyclerViewHolder.getView(R.id.rtv_add_device);
        if (i == this.mAdapter.getItemCount() - 1) {
            view.setVisibility(0);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.fragment.-$$Lambda$DeviceFragment$nEv0K7h31Cf4kncfZkW2ZQWXjpw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$bindData2View$0$DeviceFragment(view2);
                }
            });
        } else {
            view.setVisibility(8);
        }
        regularTextView2.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.fragment.-$$Lambda$DeviceFragment$sLxBso6FXj-vGnFmM52mJYGQh1o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$bindData2View$1$DeviceFragment(deviceInfo, view2);
            }
        });
        commonRecyclerViewHolder.getView(R.id.layout_device_info).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.fragment.-$$Lambda$DeviceFragment$e8-nwZPYp-RF1aBrMBuNEpl58iE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$bindData2View$2$DeviceFragment(deviceInfo, view2);
            }
        });
        commonRecyclerViewHolder.getView(R.id.layout_device_info).setOnLongClickListener(new View.OnLongClickListener() { // from class: com.ido.life.module.device.fragment.-$$Lambda$DeviceFragment$uJlTaxj9Eh3jJhy_vib8MT1quUc
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view2) {
                return this.f$0.lambda$bindData2View$3$DeviceFragment(deviceInfo, view2);
            }
        });
    }

    public /* synthetic */ void lambda$bindData2View$0$DeviceFragment(View view) {
        jump2SearchActivity();
    }

    public /* synthetic */ void lambda$bindData2View$1$DeviceFragment(DeviceListEntity.DeviceInfo deviceInfo, View view) {
        if (this.mConnectingDevice != null) {
            return;
        }
        ((DeviceFragmentPresenter) this.mPresenter).connectDevice(deviceInfo);
    }

    public /* synthetic */ void lambda$bindData2View$2$DeviceFragment(DeviceListEntity.DeviceInfo deviceInfo, View view) {
        onItemClick(deviceInfo);
    }

    public /* synthetic */ boolean lambda$bindData2View$3$DeviceFragment(DeviceListEntity.DeviceInfo deviceInfo, View view) {
        onItemLongClick(deviceInfo);
        return true;
    }

    private void stopAnimation(View view) {
        view.clearAnimation();
        view.setVisibility(8);
    }

    private void startAnimation(View view) {
        view.setVisibility(0);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 1800.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(4000L);
        rotateAnimation.setRepeatCount(-1);
        view.startAnimation(rotateAnimation);
    }

    private void jump2SearchActivity() {
        if (this.mConnectingDevice != null) {
            showToast(getLanguageText(R.string.device_in_connecting));
        } else {
            startActivity(new SingleTopIntent(this.mActivity, (Class<?>) ChoiceBlueTypeActivity.class));
        }
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void onVisible() {
        super.onVisible();
        ConnectLogHelper.saveLog("DeviceFragment", "DeviceFragment onVisible");
        initLabelLanguage();
        ((DeviceFragmentPresenter) this.mPresenter).getBasicInfo();
        if (this.mConnectingDevice == null) {
            ((DeviceFragmentPresenter) this.mPresenter).getDeviceList();
        }
    }

    @Override // com.ido.common.base.BaseCoreFragment
    public void onInVisible() {
        super.onInVisible();
        ConnectLogHelper.saveLog("DeviceFragment", "DeviceFragment onInVisible");
    }

    private void initLabelLanguage() {
        this.mRtvTitle.setText(getLanguageText(R.string.nav_device_title));
    }

    @OnClick({R.id.iv_scan, R.id.layout_header_add_device})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_scan) {
            startActivity(new SingleTopIntent(this.mActivity, (Class<?>) ScanCodeActivity.class));
        } else {
            if (id != R.id.layout_header_add_device) {
                return;
            }
            jump2SearchActivity();
        }
    }

    @Override // com.ido.life.module.device.view.IDeviceView
    public void onGetDeviceList(List<DeviceListEntity.DeviceInfo> list, boolean z) {
        if (z) {
            dismissLoadingDialog();
        }
        this.mRefreshLayout.finishRefresh(500);
        this.mLayoutHeaderAddDevice.setVisibility(list.isEmpty() ? 0 : 8);
        this.mDeviceList.clear();
        this.mDeviceList.addAll(list);
        this.mAdapter.addAll(list);
        this.mHeaderAndFooterWrapper.notifyDataSetChanged();
    }

    @Override // com.ido.life.module.device.view.IDeviceView
    public void onDeleteDeviceSuccess(String str) {
        dismissLoadingDialog();
        showToast(getLanguageText(R.string.device_delete_success));
    }

    @Override // com.ido.life.module.device.view.IDeviceView
    public void onDeleteDeviceFailed() {
        dismissLoadingDialog();
        showToast(getLanguageText(R.string.device_delete_failed));
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onNeedOpenBle() {
        this.mConnectingDevice = null;
        BleHelper.openBLE(this.mActivity);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onConnectStart(BLEDevice bLEDevice) {
        CommonLogUtil.d("BindActivity------onConnectStart");
        this.mConnectingDevice = new DeviceListEntity.DeviceInfo(bLEDevice);
        ((DeviceFragmentPresenter) this.mPresenter).refreshDeviceList();
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onConnectSuccess(boolean z) {
        CommonLogUtil.d("BindActivity------onConnectSuccess");
        this.mConnectingDevice = null;
        if (z) {
            ((DeviceFragmentPresenter) this.mPresenter).getBasicInfoFromDevice();
        } else {
            ((DeviceFragmentPresenter) this.mPresenter).refreshDeviceList();
        }
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onConnectFailed(int i) {
        CommonLogUtil.d("BindActivity------onConnectFailed");
        refreshDeviceList();
        showToast(getLanguageText(R.string.home_user_connectfailed));
    }

    private void refreshDeviceList() {
        this.mConnectingDevice = null;
        ((DeviceFragmentPresenter) this.mPresenter).refreshDeviceList();
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onInDfuMode(final BLEDevice bLEDevice) {
        dismissLoadingDialog();
        this.mConnectingDevice = null;
        ((DeviceFragmentPresenter) this.mPresenter).disconnect();
        CommBottomConfirmDialog.newInstance(getString(R.string.dfu_mode_tip), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.fragment.-$$Lambda$DeviceFragment$6lnedI7hyYTQsxOoPVmFOgK59sU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onInDfuMode$4$DeviceFragment(bLEDevice, view);
            }
        }).show(this.mActivity.getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$onInDfuMode$4$DeviceFragment(BLEDevice bLEDevice, View view) {
        SingleTopIntent singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) DeviceUpgradeNewActivity.class);
        singleTopIntent.putExtra("device_info", bLEDevice);
        startActivity(singleTopIntent);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onBindWrongDevice(BLEDevice bLEDevice) {
        CommonLogUtil.d("onBindWrongDevice " + bLEDevice);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onGetDeviceInfoSuccess() {
        CommonLogUtil.d("BindActivity------onGetDeviceInfoSuccess");
        ((DeviceFragmentPresenter) this.mPresenter).bindDevice();
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onNeedAuthCode(int i) {
        CommonLogUtil.d("onNeedAuthCode len = " + i);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onNeedConfirm(int i, String str) {
        CommonLogUtil.d("onNeedConfirm mac = " + str + ";    shape = " + i);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onBindSuccess() {
        CommonLogUtil.d("BindActivity------onBindSuccess");
        refreshDeviceList();
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onBindTimeOut(int i) {
        CommonLogUtil.d("BindActivity------onBindTimeOut");
        refreshDeviceList();
        showToast(getLanguageText(R.string.home_user_connectfailed));
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onBindFailed(int i, boolean z) {
        CommonLogUtil.d("BindActivity------onBindFailed");
        refreshDeviceList();
        dismissLoadingDialog();
        if (z) {
            showToast(getLanguageText(R.string.device_bind_failed_rejected));
        } else {
            showToast(getLanguageText(R.string.home_user_connectfailed));
        }
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        super.handleMessage(baseMessage);
        int type = baseMessage.getType();
        if (type != 101 && type != 102) {
            if (type == 829) {
                ((DeviceFragmentPresenter) this.mPresenter).getDeviceList();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDeviceFragmentLogPath(), "Alexa显示状态：设备列表改变");
                this.mItemFootAlexa.setVisibility(((DeviceFragmentPresenter) this.mPresenter).isSupportAlex() ? 0 : 8);
                return;
            } else {
                if (type == 846 && ((Boolean) baseMessage.getData()).booleanValue()) {
                    ((DeviceFragmentPresenter) this.mPresenter).refreshDeviceList();
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDeviceFragmentLogPath(), "Alexa显示状态：EVENT_REQUEST_DEVICE_INFO_COMPLETE");
                    this.mItemFootAlexa.setVisibility(((DeviceFragmentPresenter) this.mPresenter).isSupportAlex() ? 0 : 8);
                    return;
                }
                return;
            }
        }
        this.mItemFootRecommend.setAlpha(((DeviceFragmentPresenter) this.mPresenter).isConnected() ? 1.0f : 0.2f);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDeviceFragmentLogPath(), "Alexa显示状态：连接成功, isSupportAlexa=" + ((DeviceFragmentPresenter) this.mPresenter).isSupportAlexConnected() + " ," + baseMessage.getType());
        this.mItemFootAlexa.setVisibility(((DeviceFragmentPresenter) this.mPresenter).isSupportAlex() ? 0 : 8);
        this.mItemFootAlexa.setAlpha(((DeviceFragmentPresenter) this.mPresenter).isSupportAlexConnected() ? 1.0f : 0.3f);
        if (((DeviceFragmentPresenter) this.mPresenter).isConnected() && ((DeviceFragmentPresenter) this.mPresenter).isSupportDeviceThirdVersion()) {
            ((DeviceFragmentPresenter) this.mPresenter).getDeviceThirdVersion();
        } else {
            ((DeviceFragmentPresenter) this.mPresenter).refreshDeviceList();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.rlFgDevice.setBackgroundColor(getResources().getColor(R.color.black));
    }

    @Override // com.ido.smartrefresh.layout.listener.OnRefreshListener
    public void onRefresh(RefreshLayout refreshLayout) {
        ((DeviceFragmentPresenter) this.mPresenter).getDeviceList();
    }

    private void onItemClick(DeviceListEntity.DeviceInfo deviceInfo) {
        if (this.mConnectingDevice != null) {
            showToast(getLanguageText(R.string.device_in_connecting));
            return;
        }
        SingleTopIntent singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) DeviceInfoActivity.class);
        singleTopIntent.putExtra("device", deviceInfo);
        startActivity(singleTopIntent);
    }

    private void onItemLongClick(DeviceListEntity.DeviceInfo deviceInfo) {
        if (this.mConnectingDevice != null) {
            showToast(getLanguageText(R.string.device_in_connecting));
        } else {
            if (((DeviceFragmentPresenter) this.mPresenter).isCurrentConnectedDevice(deviceInfo.getMac())) {
                return;
            }
            showReConnectDialog(deviceInfo);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (HomeFragmentPresenter.mIsSyncing) {
            showToast(getLanguageText(R.string.syncing_pls_try_again_later));
            return;
        }
        if (!((DeviceFragmentPresenter) this.mPresenter).isConnected()) {
            showToast(getLanguageText(R.string.device_pls_connect_device_to_use));
            return;
        }
        if (view.getId() == R.id.item_foot_alexa) {
            if (((DeviceFragmentPresenter) this.mPresenter).isCurrentDeviceSupportAlex()) {
                startActivity(new SingleTopIntent(this.mActivity, (Class<?>) AlexaLoginActivity.class));
            }
        } else if (((DeviceFragmentPresenter) this.mPresenter).getSupportFunctionInfo().multiDial) {
            startActivity(new SingleTopIntent(this.mActivity, (Class<?>) DialMarketActivity.class));
        }
    }

    private void showReConnectDialog(final DeviceListEntity.DeviceInfo deviceInfo) {
        if (this.mReconnectDialog == null) {
            this.mReconnectDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.delete_device), getLanguageText(R.string.device_device_off_message_ios), getLanguageText(R.string.device_relink), getLanguageText(R.string.delete_device), true);
        }
        this.mReconnectDialog.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.device.fragment.-$$Lambda$DeviceFragment$X1M2W9TuJzePS-kvDfd6HC6ifzM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showReConnectDialog$5$DeviceFragment(deviceInfo, view);
            }
        });
        this.mReconnectDialog.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.fragment.-$$Lambda$DeviceFragment$y8z3xxnT51sqAjk4xxnMvT6PUbM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showReConnectDialog$6$DeviceFragment(deviceInfo, view);
            }
        });
        this.mReconnectDialog.show(this.mActivity.getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showReConnectDialog$5$DeviceFragment(DeviceListEntity.DeviceInfo deviceInfo, View view) {
        ((DeviceFragmentPresenter) this.mPresenter).removeDevice(deviceInfo);
    }

    public /* synthetic */ void lambda$showReConnectDialog$6$DeviceFragment(DeviceListEntity.DeviceInfo deviceInfo, View view) {
        ((DeviceFragmentPresenter) this.mPresenter).connectDevice(deviceInfo);
    }
}