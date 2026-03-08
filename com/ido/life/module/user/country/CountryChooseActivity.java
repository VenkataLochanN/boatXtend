package com.ido.life.module.user.country;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.adapter.OnClickItemListener;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.InputMethodUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.common.utils.StatusBarUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.ILocationCallback;
import com.ido.life.ble.BleHelper;
import com.ido.life.boatservice.GDLocationManager;
import com.ido.life.constants.Constants;
import com.ido.life.customview.SideBar;
import com.ido.life.location.LocationMessage;
import com.ido.life.module.user.country.CountryContract;
import com.ido.life.util.SPUtils;
import com.realsil.sdk.dfu.model.DfuConfig;

/* JADX INFO: loaded from: classes3.dex */
public class CountryChooseActivity extends BaseActivity implements CountryContract.View, ILocationCallback {
    public static final String COUNTRY = "country";
    public static final String COUNTRY_CODE = "country_code";
    public static final String COUNTRY_NAME = "country_name";
    public static final int REQUEST_CODE = 100;
    public static final int RESULT_CODE = 200;
    private static final String TAG = CountryChooseActivity.class.getSimpleName();
    private String fromActivity;
    private CountryInfo mCountryInfo;

    @BindView(R.id.country_sidebar)
    SideBar mCountrySidebar;

    @BindView(R.id.et_search_key)
    EditText mEtSerchKey;
    private String mGDLocationCountry;
    private CommBottomConfirmDialog mGpsOpenDialog;

    @BindView(R.id.iv_country_tip)
    ImageView mIvCountryTip;
    protected CountryContract.Presenter mPresenter;

    @BindView(R.id.country_recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.tv_not_find_country)
    TextView mTvNoFindCountry;

    @BindView(R.id.tv_remind)
    TextView mTvRemind;
    private boolean move;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mOpenGpsManual = true;
    private GDLocationManager.LocationStringListener mGDLisner = new AnonymousClass1();
    private Runnable locationFailRunable = new Runnable() { // from class: com.ido.life.module.user.country.CountryChooseActivity.2
        @Override // java.lang.Runnable
        public void run() {
            CountryChooseActivity.this.mPresenter.initDefaultCountry(CountryChooseActivity.this.mGDLocationCountry);
            int iInitCountryAdapter = CountryChooseActivity.this.mPresenter.initCountryAdapter();
            if (iInitCountryAdapter > 0) {
                CountryChooseActivity.this.mRecyclerView.scrollToPosition(iInitCountryAdapter);
            }
            GDLocationManager.getInstance(IdoApp.getAppContext()).stopLocation(CountryChooseActivity.this.mGDLisner);
            CountryChooseActivity.this.hideLoading();
        }
    };

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_country_choose;
    }

    @Override // com.ido.life.base.ILocationCallback
    public void onLocationUpdate(LocationMessage locationMessage) {
    }

    @Override // com.ido.life.module.user.country.CountryContract.View
    public void showSideBarContent(String[] strArr) {
    }

    /* JADX INFO: renamed from: com.ido.life.module.user.country.CountryChooseActivity$1, reason: invalid class name */
    class AnonymousClass1 implements GDLocationManager.LocationStringListener {
        AnonymousClass1() {
        }

        @Override // com.ido.life.boatservice.GDLocationManager.LocationStringListener
        public void onReceiveLocation(com.ido.life.module.sport.bean.LocationMessage locationMessage) {
            if (locationMessage != null) {
                if (locationMessage.latitude == 0.0d && locationMessage.longitude == 0.0d) {
                    return;
                }
                CountryChooseActivity.this.hideLoading();
                CountryChooseActivity.this.mHandler.removeCallbacks(CountryChooseActivity.this.locationFailRunable);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), CountryChooseActivity.TAG, "定位" + locationMessage.toString());
                CountryChooseActivity.this.mGDLocationCountry = locationMessage.country;
                CountryChooseActivity.this.mPresenter.initDefaultCountry(CountryChooseActivity.this.mGDLocationCountry);
                CountryChooseActivity.this.runOnUiThread(new Runnable() { // from class: com.ido.life.module.user.country.-$$Lambda$CountryChooseActivity$1$NXrII0l_oJQEulUkRub3OjEkYyY
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onReceiveLocation$0$CountryChooseActivity$1();
                    }
                });
                GDLocationManager.getInstance(IdoApp.getAppContext()).stopLocation(this);
            }
        }

        public /* synthetic */ void lambda$onReceiveLocation$0$CountryChooseActivity$1() {
            int iInitCountryAdapter = CountryChooseActivity.this.mPresenter.initCountryAdapter();
            if (iInitCountryAdapter > 0) {
                CountryChooseActivity.this.mRecyclerView.scrollToPosition(iInitCountryAdapter);
            }
        }
    }

    public static void startActivity(Activity activity, String str) {
        Intent intent = new Intent(activity, (Class<?>) CountryChooseActivity.class);
        intent.putExtra("country_code", str);
        activity.startActivityForResult(intent, 100);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initTitleBar();
        initListener();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initPresenter() {
        this.mPresenter = new CountryPresenter(this);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter.start();
        this.fromActivity = getIntent().getStringExtra(Constants.FROM_WHICH_OPEN_COUNTRY);
        if (TextUtils.isEmpty(this.fromActivity)) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "fromActivity 为null， getIntent().getStringExtra(FROM_WHICH_OPEN_COUNTRY)");
        } else {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "fromActivity 不为null： " + this.fromActivity);
        }
        CountryInfo countryInfo = this.mCountryInfo;
        if (countryInfo != null) {
            this.mGDLocationCountry = countryInfo.countryName;
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "fromActivity == mCountryInfo.type： " + this.mCountryInfo.type);
        }
        if (TextUtils.isEmpty(this.mGDLocationCountry)) {
            return;
        }
        this.mPresenter.initDefaultCountry(this.mGDLocationCountry);
        int iInitCountryAdapter = this.mPresenter.initCountryAdapter();
        if (iInitCountryAdapter > 0) {
            this.mRecyclerView.scrollToPosition(iInitCountryAdapter);
        }
    }

    private void startTask() {
        this.mHandler.postDelayed(this.locationFailRunable, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (TextUtils.isEmpty(this.mGDLocationCountry) && this.mOpenGpsManual) {
            if (!checkSelfPermission(PermissionUtil.getLocationPermission())) {
                requestPermissions(300, PermissionUtil.getLocationPermission());
            } else if (BleHelper.isOpenGPS(this)) {
                startLocationTask();
            } else {
                checkGpsSwitch();
            }
        }
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        this.mTitleBar.initLayout(1);
        this.mTitleBar.getTitleLayoutLeft(this).setVisibility(0);
        this.mTitleBar.setTitle(LanguageUtil.getLanguageText(R.string.register_area_code_choose));
        this.mTitleBar.setRightImg(R.mipmap.save);
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        this.mTvRemind.setText(LanguageUtil.getLanguageText(R.string.register_country_region_choose));
        this.mTvNoFindCountry.setText(LanguageUtil.getLanguageText(R.string.register_not_find_country));
        this.mEtSerchKey.addTextChangedListener(new TextWatcher() { // from class: com.ido.life.module.user.country.CountryChooseActivity.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                CountryChooseActivity.this.mPresenter.doSearch(editable.toString());
            }
        });
    }

    private void initListener() {
        this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.ido.life.module.user.country.CountryChooseActivity.4
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                int height = recyclerView.getHeight();
                if (CountryChooseActivity.this.move) {
                    CountryChooseActivity.this.move = false;
                    recyclerView.scrollBy(0, height - DipPixelUtil.dip2px(40.0f));
                }
            }
        });
        this.mTitleBar.setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.user.country.-$$Lambda$CountryChooseActivity$u7ZKYYUhP81OXtlglWNq471pkCw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initListener$0$CountryChooseActivity(view);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x012b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ void lambda$initListener$0$CountryChooseActivity(android.view.View r8) {
        /*
            Method dump skipped, instruction units count: 389
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.user.country.CountryChooseActivity.lambda$initListener$0$CountryChooseActivity(android.view.View):void");
    }

    @OnClick({R.id.iv_country_tip})
    public void toTipActivity(View view) {
        startActivity(new Intent(this, (Class<?>) CountryChooseTipActivity.class));
    }

    public /* synthetic */ void lambda$showCountryList$1$CountryChooseActivity(View view, CountryInfo countryInfo) {
        this.mPresenter.ChooseCountryInfo();
    }

    @Override // com.ido.life.module.user.country.CountryContract.View
    public void showCountryList(CountryAdapter countryAdapter) {
        countryAdapter.setOnClickItemListener(new OnClickItemListener() { // from class: com.ido.life.module.user.country.-$$Lambda$CountryChooseActivity$GjYoRlxvq_-wPFkRByk4Cqdb4ZI
            @Override // com.ido.common.adapter.OnClickItemListener
            public final void onClick(View view, Object obj) {
                this.f$0.lambda$showCountryList$1$CountryChooseActivity(view, (CountryInfo) obj);
            }
        });
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.setAdapter(countryAdapter);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "mRecyclerView != null 开始setAdapter");
        } else {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "mRecyclerView = null ");
        }
        hideLoading();
    }

    @Override // com.ido.life.module.user.country.CountryContract.View
    public void confirmChooseCountryInfo(CountryInfo countryInfo) {
        CommonLogUtil.d(countryInfo.toString());
        this.mCountryInfo = countryInfo;
        SPUtils.put(Constants.CHOOSE_COUNTRY_CODE, countryInfo.countryCode);
    }

    @Override // com.ido.life.module.user.country.CountryContract.View
    public void showSessionPositionTop(int i) {
        GridLayoutManager gridLayoutManager = (GridLayoutManager) this.mRecyclerView.getLayoutManager();
        int iFindFirstVisibleItemPosition = gridLayoutManager != null ? gridLayoutManager.findFirstVisibleItemPosition() : 0;
        int iFindLastVisibleItemPosition = gridLayoutManager != null ? gridLayoutManager.findLastVisibleItemPosition() : 0;
        if (i <= iFindFirstVisibleItemPosition) {
            this.mRecyclerView.scrollToPosition(i);
        } else if (i <= iFindLastVisibleItemPosition) {
            this.mRecyclerView.scrollBy(0, this.mRecyclerView.getChildAt(i - iFindFirstVisibleItemPosition).getTop());
        } else {
            this.mRecyclerView.scrollToPosition(i);
            this.move = true;
        }
    }

    @Override // com.ido.life.module.user.country.CountryContract.View
    public void showSideBar(boolean z) {
        this.mCountrySidebar.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.user.country.CountryContract.View
    public void hideInputEdit() {
        InputMethodUtil.hiddenInput(this, this.mEtSerchKey);
    }

    @Override // com.ido.life.module.user.country.CountryContract.View
    public void showLoading() {
        WaitingDialog.showDialogBack(this);
    }

    @Override // com.ido.life.module.user.country.CountryContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.country.CountryContract.View
    public void setShowTip(boolean z) {
        if (z) {
            this.mTvRemind.setVisibility(0);
            this.mIvCountryTip.setVisibility(0);
            this.mTvNoFindCountry.setVisibility(8);
        } else {
            this.mTvRemind.setVisibility(8);
            this.mIvCountryTip.setVisibility(8);
            this.mTvNoFindCountry.setVisibility(0);
        }
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(CountryContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // com.ido.life.base.ILocationCallback
    public void onLocationFailed(String str) {
        CommonLogUtil.d(TAG, str);
    }

    @Override // com.ido.common.base.BaseCoreActivity, com.ido.common.utils.PermissionUtil.RequestResult
    public void requestPermissionsSuccess(int i) {
        if (BleHelper.isOpenGPS(this)) {
            startLocationTask();
        } else {
            checkGpsSwitch();
        }
    }

    private void startLocationTask() {
        showLoading();
        GDLocationManager.getInstance(IdoApp.getAppContext()).startLocation(this.mGDLisner);
        startTask();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "请求定位权限成功!");
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
    }

    private void checkGpsSwitch() {
        CommBottomConfirmDialog commBottomConfirmDialog = this.mGpsOpenDialog;
        if (commBottomConfirmDialog != null) {
            commBottomConfirmDialog.dismissAllowingStateLoss();
        }
        if (this.mGpsOpenDialog == null) {
            this.mGpsOpenDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.gps_open_tips), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.country.-$$Lambda$CountryChooseActivity$kfjzORHtdjEkI0iXmrtMhLsxfHY
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$checkGpsSwitch$2$CountryChooseActivity(view);
                }
            }).setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.user.country.-$$Lambda$CountryChooseActivity$QhQD86JopVpiWXBHUDIO2GQUYSQ
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$checkGpsSwitch$3$CountryChooseActivity(view);
                }
            });
        }
        this.mGpsOpenDialog.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$checkGpsSwitch$2$CountryChooseActivity(View view) {
        Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        intent.addFlags(268435456);
        startActivity(intent);
    }

    public /* synthetic */ void lambda$checkGpsSwitch$3$CountryChooseActivity(View view) {
        this.mOpenGpsManual = false;
    }

    @Override // com.ido.common.base.BaseCoreActivity, com.ido.common.utils.PermissionUtil.RequestResult
    public void requestPermissionsFail(int i) {
        int iInitCountryAdapter = this.mPresenter.initCountryAdapter();
        if (iInitCountryAdapter > 0) {
            this.mRecyclerView.scrollToPosition(iInitCountryAdapter);
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "请求定位权限失败!");
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}