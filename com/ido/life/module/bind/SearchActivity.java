package com.ido.life.module.bind;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.common.IdoApp;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.ble.BleHelper;
import com.ido.life.constants.Constants;
import com.ido.life.customview.recyclerview.BaseLinearLayoutManager;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.module.device.activity.DeviceUpgradeNewActivity;
import com.ido.life.module.main.MainActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SearchActivity extends BaseActivity<SearchPresenter> implements ISearchView, SwipeRefreshLayout.OnRefreshListener {
    public static final String DEVICE_TYPE = "device_type";
    int deviceType;
    private CommonRecyclerViewAdapter<BLEDevice> mAdapter;
    private CommBottomConfirmDialog mDFUDialog;
    private List<BLEDevice> mDeviceList = new ArrayList();
    private CommBottomConfirmDialog mGpsOpenDialog;

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;

    @BindView(R.id.iv_search)
    ImageView mIvSearch;

    @BindView(R.id.layout_refresh)
    SwipeRefreshLayout mLayoutRefresh;

    @BindView(R.id.layout_search)
    RelativeLayout mLayoutSearch;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.rtv_cancel)
    RegularTextView mRtvCancel;
    private LinearLayout mTitleLayoutRight;

    @BindView(R.id.tv_search_again)
    RegularTextView mTvSearchAgain;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override // com.ido.life.base.BaseActivity
    protected boolean needEventBus() {
        return false;
    }

    public static void start(Context context, int i) {
        SingleTopIntent singleTopIntent = new SingleTopIntent(context, (Class<?>) SearchActivity.class);
        singleTopIntent.putExtra(DEVICE_TYPE, i);
        context.startActivity(singleTopIntent);
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mTitleBar.initLayout(2).setRightTextColor(ResourceUtil.getColor(R.color.color_027AFF));
        this.mTitleBar.setRightText(LanguageUtil.getLanguageText(R.string.help));
        this.mTitleBar.setTitle(R.string.device_searching);
        this.deviceType = getIntent().getIntExtra(DEVICE_TYPE, 0);
        initRecyclerView();
        this.mTitleLayoutRight = this.mTitleBar.getTitleLayoutRight(this);
        this.mTitleLayoutRight.setVisibility(4);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.bind.-$$Lambda$SearchActivity$qnmT3fITTByG5I9MPHUf2kgJ0Cc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$SearchActivity(view);
            }
        });
        this.mTitleBar.setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.bind.-$$Lambda$SearchActivity$q03_RaAmUFMei1pKE_SE67A818s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$1$SearchActivity(view);
            }
        });
        this.mLayoutRefresh.setOnRefreshListener(this);
    }

    public /* synthetic */ void lambda$initEvent$0$SearchActivity(View view) {
        stopScan();
        finish();
    }

    public /* synthetic */ void lambda$initEvent$1$SearchActivity(View view) {
        stopScan();
        startActivity(new SingleTopIntent(this, (Class<?>) BindHelpActivity.class));
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        scanDevice();
    }

    private boolean checkGpsSwitch() {
        CommBottomConfirmDialog commBottomConfirmDialog = this.mGpsOpenDialog;
        if (commBottomConfirmDialog != null) {
            commBottomConfirmDialog.dismissAllowingStateLoss();
        }
        if (BleHelper.isOpenGPS(IdoApp.getAppContext())) {
            return true;
        }
        if (this.mGpsOpenDialog == null) {
            this.mGpsOpenDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.sport_device_gps_tips_title), getLanguageText(R.string.gps_open_tips), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.bind.-$$Lambda$SearchActivity$w9c8msP4Qchlbr-nlmZrhtsa-FE
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$checkGpsSwitch$2$SearchActivity(view);
                }
            }).setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.bind.-$$Lambda$SearchActivity$IdH7FpgqZBdKM-ecgzoVcwlb2v4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$checkGpsSwitch$3$SearchActivity(view);
                }
            });
        }
        this.mGpsOpenDialog.show(getSupportFragmentManager());
        return false;
    }

    public /* synthetic */ void lambda$checkGpsSwitch$2$SearchActivity(View view) {
        startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }

    public /* synthetic */ void lambda$checkGpsSwitch$3$SearchActivity(View view) {
        searchFailed();
    }

    private void initRecyclerView() {
        this.mRecyclerView.setLayoutManager(new BaseLinearLayoutManager(this));
        this.mAdapter = new CommonRecyclerViewAdapter<BLEDevice>(this, R.layout.item_device, this.mDeviceList) { // from class: com.ido.life.module.bind.SearchActivity.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, BLEDevice bLEDevice, int i) {
                ((TextView) commonRecyclerViewHolder.getView(R.id.tv_name)).setText(bLEDevice.mDeviceName);
                ((TextView) commonRecyclerViewHolder.getView(R.id.tv_mac)).setText(bLEDevice.mDeviceAddress);
                ((ImageView) commonRecyclerViewHolder.getView(R.id.iv_icon)).setImageResource(SearchActivity.this.deviceType == 1 ? R.mipmap.icon_type_watch : R.mipmap.icon_type_bracelet);
                int iAbs = Math.abs(bLEDevice.mRssi);
                if (iAbs <= 50) {
                    ((ImageView) commonRecyclerViewHolder.getView(R.id.iv_signal)).setImageResource(R.mipmap.device_signal4);
                    return;
                }
                if (iAbs <= 80) {
                    ((ImageView) commonRecyclerViewHolder.getView(R.id.iv_signal)).setImageResource(R.mipmap.device_signal3);
                } else if (iAbs <= 110) {
                    ((ImageView) commonRecyclerViewHolder.getView(R.id.iv_signal)).setImageResource(R.mipmap.device_signal2);
                } else {
                    ((ImageView) commonRecyclerViewHolder.getView(R.id.iv_signal)).setImageResource(R.mipmap.device_signal1);
                }
            }
        };
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(new MultiItemTypeAdapterForRV.OnItemClickListener() { // from class: com.ido.life.module.bind.SearchActivity.2
            @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }

            @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                SearchActivity.this.stopScan();
                if (i < 0 || i >= SearchActivity.this.mDeviceList.size()) {
                    return;
                }
                SearchActivity searchActivity = SearchActivity.this;
                searchActivity.startActivity(new SingleTopIntent(searchActivity, (Class<?>) BindActivity.class).putExtra(Constants.INTENT_DATA_KEY, (Serializable) SearchActivity.this.mDeviceList.get(i)));
            }
        });
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 300 && checkGpsSwitch()) {
            if (checkSelfPermission(PermissionUtil.getLocationPermission())) {
                startScan();
            } else {
                checkLocationPermission(strArr, iArr);
            }
        }
    }

    private void checkLocationPermission(String[] strArr, int[] iArr) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i < iArr.length) {
                if (iArr[i] == -1 && !ActivityCompat.shouldShowRequestPermissionRationale(this, strArr[i])) {
                    z = true;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        if (z) {
            CommBottomConfirmDialog.newInstance(getLanguageText(R.string.get_gps_permission_title), getLanguageText(R.string.get_gps_permission_content), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.bind.-$$Lambda$SearchActivity$XxfGDRMSj1roYy0_3WjmzZjPQks
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$checkLocationPermission$4$SearchActivity(view);
                }
            }).setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.bind.-$$Lambda$SearchActivity$T7AN-aQV9zdVp395L3tUz1fjpOU
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$checkLocationPermission$5$SearchActivity(view);
                }
            }).show(getSupportFragmentManager());
        } else {
            searchFailed();
        }
    }

    public /* synthetic */ void lambda$checkLocationPermission$4$SearchActivity(View view) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        startActivityForResult(intent, 300);
    }

    public /* synthetic */ void lambda$checkLocationPermission$5$SearchActivity(View view) {
        searchFailed();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101) {
            if (((SearchPresenter) this.mPresenter).isBleEnable()) {
                startScan();
                return;
            }
            this.mLayoutRefresh.setRefreshing(false);
            this.mLayoutRefresh.setEnabled(true);
            searchFailed();
            return;
        }
        if (i == 300) {
            CommonLogUtil.d("CODE_LOCATION_PERMISSION");
            if (checkSelfPermission(PermissionUtil.getLocationPermission())) {
                startScan();
            } else {
                searchFailed();
            }
        }
    }

    private void scanDevice() {
        if (checkGpsSwitch()) {
            if (!checkSelfPermission(PermissionUtil.getLocationPermission())) {
                requestPermissions(300, PermissionUtil.getLocationPermission());
            } else {
                startScan();
            }
        }
    }

    private void startScan() {
        this.mTitleLayoutRight.setVisibility(4);
        this.mLayoutSearch.setVisibility(0);
        this.mTitleBar.setTitle(R.string.device_searching);
        this.mIvIcon.setImageResource(R.mipmap.icon_search_device);
        Glide.with((FragmentActivity) this).load(Integer.valueOf(R.mipmap.anim_searching)).asGif().diskCacheStrategy(DiskCacheStrategy.NONE).into(this.mIvSearch);
        this.mRtvCancel.setVisibility(0);
        this.mTvSearchAgain.setVisibility(8);
        this.mLayoutRefresh.setVisibility(8);
        this.mLayoutRefresh.setRefreshing(false);
        this.mLayoutRefresh.setEnabled(false);
        ((SearchPresenter) this.mPresenter).scanDevice(this.deviceType);
    }

    @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
    public void onRefresh() {
        if (((SearchPresenter) this.mPresenter).isSearching()) {
            this.mLayoutRefresh.setRefreshing(false);
        } else {
            scanDevice();
        }
    }

    @Override // com.ido.life.module.bind.ISearchView
    public void onGetDeviceWhiteList() {
        if (checkSelfPermission(PermissionUtil.getLocationPermission()) && ((SearchPresenter) this.mPresenter).isBleEnable()) {
            startScan();
        }
    }

    @Override // com.ido.life.module.bind.ISearchView
    public void onSearchStart() {
        this.mDeviceList.clear();
        this.mAdapter.setData(this.mDeviceList);
    }

    @Override // com.ido.life.module.bind.ISearchView
    public void onSearchedDevice(BLEDevice bLEDevice, List<BLEDevice> list) {
        this.mTitleBar.setTitle(R.string.device_choice_device);
        if (this.mLayoutSearch.isShown()) {
            this.mLayoutSearch.setVisibility(8);
            this.mLayoutRefresh.setVisibility(0);
        }
        this.mDeviceList = list;
        this.mAdapter.setData(this.mDeviceList);
    }

    @Override // com.ido.life.module.bind.ISearchView
    public void onSearchedDFUDevice(final BLEDevice bLEDevice) {
        CommBottomConfirmDialog commBottomConfirmDialog = this.mDFUDialog;
        if (commBottomConfirmDialog == null || !commBottomConfirmDialog.isDialogShowing()) {
            this.mDFUDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.dfu_mode_tip), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.bind.-$$Lambda$SearchActivity$LcF4nIJUgVGKnXczpJom9T9lTjY
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onSearchedDFUDevice$6$SearchActivity(bLEDevice, view);
                }
            });
            this.mDFUDialog.show(getSupportFragmentManager());
        }
    }

    public /* synthetic */ void lambda$onSearchedDFUDevice$6$SearchActivity(BLEDevice bLEDevice, View view) {
        stopScan();
        SingleTopIntent singleTopIntent = new SingleTopIntent(this, (Class<?>) DeviceUpgradeNewActivity.class);
        singleTopIntent.putExtra("device_info", bLEDevice);
        startActivity(singleTopIntent);
    }

    @Override // com.ido.life.module.bind.ISearchView
    public void onSearchFailed() {
        searchFailed();
    }

    @Override // com.ido.life.module.bind.ISearchView
    public void onSearchFinished() {
        this.mLayoutRefresh.setEnabled(true);
    }

    @Override // com.ido.life.module.bind.ISearchView
    public void onNeedLocationPermission() {
        searchFailed();
        if (checkSelfPermission(PermissionUtil.getLocationPermission())) {
            return;
        }
        requestPermissions(300, PermissionUtil.getLocationPermission());
    }

    @Override // com.ido.life.module.bind.ISearchView
    public void onNeedOpenGps() {
        searchFailed();
        checkGpsSwitch();
    }

    @Override // com.ido.life.module.bind.ISearchView
    public void OnNeedOpenBle() {
        searchFailed();
        BleHelper.openBLE(this);
    }

    private void searchFailed() {
        this.mTitleLayoutRight.setVisibility(0);
        this.mTitleBar.setTitle(R.string.device_search_failed);
        this.mLayoutRefresh.setEnabled(true);
        this.mIvSearch.clearAnimation();
        this.mIvSearch.setImageResource(R.mipmap.bg_search_failed);
        this.mIvIcon.setImageResource(R.mipmap.icon_search_failed);
        this.mRtvCancel.setVisibility(4);
        this.mTvSearchAgain.setVisibility(0);
        this.mLayoutRefresh.setVisibility(8);
        this.mLayoutSearch.setVisibility(0);
        showToast(this.deviceType == 1 ? R.string.wristband_search_failed : R.string.watch_search_failed_tip);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopScan() {
        this.mLayoutRefresh.setEnabled(true);
        ((SearchPresenter) this.mPresenter).stopScan();
        this.mIvSearch.clearAnimation();
    }

    @OnClick({R.id.tv_search_again, R.id.rtv_cancel})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id != R.id.rtv_cancel) {
            if (id != R.id.tv_search_again) {
                return;
            }
            scanDevice();
        } else {
            stopScan();
            startActivity(new Intent(this, (Class<?>) MainActivity.class));
            supportFinishAfterTransition();
        }
    }
}