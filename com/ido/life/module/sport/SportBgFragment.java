package com.ido.life.module.sport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.base.BaseCoreFragment;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.customview.NormalToast;
import com.ido.life.module.bind.ChoiceBlueTypeActivity;
import com.ido.life.module.sport.SportBgContract;
import com.ido.life.module.sport.explain.SportExplainActivity;
import com.ido.life.module.sport.ready.SportRunReadyActivity;
import com.ido.life.module.sport.setting.SportSettingActivity;
import com.ido.life.module.sport.view.CustomGPSView;

/* JADX INFO: loaded from: classes2.dex */
public class SportBgFragment extends BaseCoreFragment implements SportBgContract.View {
    private static final String EXTRA_TYPE = "type";
    private static final String TAG = "SportBgFragment";

    @BindView(R.id.gps_sport_view)
    CustomGPSView mCustomGPSView;

    @BindView(R.id.lay_sport_explain)
    LinearLayout mLaySportExplain;
    private SportBgContract.Presenter mPresenter;

    @BindView(R.id.re_sport_run)
    RelativeLayout mReSportRun;

    @BindView(R.id.tv_sport_total)
    TextView mTvSportTotal;
    private int mTypeId;

    @BindView(R.id.tv_start)
    View startView;

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_sport_bg;
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(SportBgContract.Presenter presenter) {
    }

    public static SportBgFragment newInstance(int i) {
        Bundle bundle = new Bundle();
        SportBgFragment sportBgFragment = new SportBgFragment();
        bundle.putInt("type", i);
        sportBgFragment.setArguments(bundle);
        return sportBgFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.ido.common.base.BaseCoreFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mTypeId = arguments.getInt("type");
        }
        this.mPresenter = new SportBgPresenter(this);
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected void onVisible() {
        super.onVisible();
        SportBgContract.Presenter presenter = this.mPresenter;
        if (presenter == null) {
            return;
        }
        presenter.getDeviceStatus();
        this.mPresenter.getTotalDistance(this.mTypeId);
        this.mPresenter.showBgByType(this.mTypeId);
        this.mLaySportExplain.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.-$$Lambda$SportBgFragment$8N-JyL7hvvp2XL-Ar_lXjJ9gQBE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onVisible$0$SportBgFragment(view);
            }
        });
    }

    public /* synthetic */ void lambda$onVisible$0$SportBgFragment(View view) {
        SportExplainActivity.start(getActivity(), LanguageUtil.getLanguageText(R.string.sport_explain), LanguageUtil.getLanguageText(R.string.sport_explain), LanguageUtil.getLanguageText(R.string.sport_explain_content));
    }

    public void setTypeId(int i) {
        this.mTypeId = i;
    }

    @OnClick({R.id.tv_start})
    public void toStart(View view) {
        this.mPresenter.startRun();
    }

    @OnClick({R.id.iv_setting})
    public void toSportSetting(View view) {
        SportSettingActivity.start(getActivity(), this.mTypeId);
    }

    @Override // com.ido.life.module.sport.SportBgContract.View
    public void showOutDoorBg() {
        this.mReSportRun.setBackgroundResource(R.mipmap.ic_sport_outdoor_bg);
    }

    @Override // com.ido.life.module.sport.SportBgContract.View
    public void showInDoorBg() {
        this.mReSportRun.setBackgroundResource(R.mipmap.ic_sport_outdoor_bg);
    }

    @Override // com.ido.life.module.sport.SportBgContract.View
    public void showMessage(String str) {
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.sport.SportBgContract.View
    public void showConnectDevice() {
        CommonLogUtil.d(TAG, "showConnectDevice: 连接状态");
    }

    @Override // com.ido.life.module.sport.SportBgContract.View
    public void showDisconnectDevice() {
        CommonLogUtil.d(TAG, "showDisconnectDevice: 未连接");
    }

    @Override // com.ido.life.module.sport.SportBgContract.View
    public void setTotalSportDistance(String str) {
        CommonLogUtil.d(TAG, "setTotalSportDistance: " + str);
        this.mTvSportTotal.setText(str);
    }

    @Override // com.ido.life.module.sport.SportBgContract.View
    public void setTotalSportDistanceVisible(boolean z) {
        this.mTvSportTotal.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.SportBgContract.View
    public void goSportReady(int i, boolean z) {
        SportRunReadyActivity.startActivity(getActivity(), i, z, this.startView, "startView");
    }

    @Override // com.ido.life.module.sport.SportBgContract.View
    public void goSportReadyPermission(int i, boolean z) {
        SportRunReadyActivity.startActivity(getActivity(), i, z);
    }

    @Override // com.ido.life.module.sport.SportBgContract.View
    public void showLocationServiceDialog() {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.sport_device_gps_tips_title), LanguageUtil.getLanguageText(R.string.sport_device_gps_tips), LanguageUtil.getLanguageText(R.string.sport_device_gps_tips_confirm), ResourceUtil.getString(R.string.public_tip_cancel), false);
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.SportBgFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.show(getActivity().getSupportFragmentManager());
    }

    @Override // com.ido.life.module.sport.SportBgContract.View
    public void showLocationPermissionDialogForAndroidQ() {
        if (!PermissionUtil.checkSelfPermission(IdoApp.getAppContext(), PermissionUtil.getLocationPermission())) {
            showSettingDialog();
            return;
        }
        if (isDeniedByNoAsk("android.permission.ACCESS_BACKGROUND_LOCATION") && SportFragment.mRefuse) {
            SportFragment.mRefuseIndex++;
            if (SportFragment.mRefuseIndex % 2 == 0) {
                showSettingDialog();
                return;
            } else {
                this.mPresenter.toStartOutSport();
                return;
            }
        }
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.sport_bg_permission_title), LanguageUtil.getLanguageText(R.string.sport_bg_permission_confirm), LanguageUtil.getLanguageText(R.string.sport_bg_permission_cancel), true);
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.SportBgFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SportBgFragment.this.toRequestPermissions();
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.SportBgFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                SportBgFragment.this.mPresenter.toStartOutSport();
            }
        });
        commBottomConfirmDialogNewInstance.setCancelable(false);
        commBottomConfirmDialogNewInstance.show(getActivity().getSupportFragmentManager());
    }

    private void showSettingDialog() {
        CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.sport_device_gps_permission_tips_title), LanguageUtil.getLanguageText(R.string.sport_device_background_location_gps_permission_tips), ResourceUtil.getString(R.string.public_tip_confirm), ResourceUtil.getString(R.string.public_tip_cancel), true);
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.-$$Lambda$SportBgFragment$UHvap1ZL2QnwRmlrACoYVlyTBzI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showSettingDialog$1$SportBgFragment(view);
            }
        });
        commBottomConfirmDialogNewInstance.show(getActivity().getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showSettingDialog$1$SportBgFragment(View view) {
        startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toRequestPermissions() {
        requestPermissions(101, new PermissionUtil.RequestResult() { // from class: com.ido.life.module.sport.SportBgFragment.4
            @Override // com.ido.common.utils.PermissionUtil.RequestResult
            public void requestPermissionsSuccess(int i) {
                CommonLogUtil.d(SportBgFragment.TAG, "requestPermissionsSuccess: ");
                SportBgFragment.this.mPresenter.toStartOutSport();
                SportFragment.mRefuse = false;
            }

            @Override // com.ido.common.utils.PermissionUtil.RequestResult
            public void requestPermissionsFail(int i) {
                if (SportBgFragment.this.isDeniedByNoAsk("android.permission.ACCESS_BACKGROUND_LOCATION")) {
                    SportFragment.mRefuse = true;
                    SportFragment.mRefuseIndex++;
                } else {
                    SportFragment.mRefuse = false;
                }
                SportBgFragment.this.mPresenter.toStartOutSport();
            }
        }, "android.permission.ACCESS_BACKGROUND_LOCATION");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDeniedByNoAsk(String... strArr) {
        for (String str : strArr) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), str)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.ido.life.module.sport.SportBgContract.View
    public void showLocationPermissionDialog() {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.sport_device_gps_permission_tips_title), LanguageUtil.getLanguageText(R.string.sport_device_gps_permission_tips), LanguageUtil.getLanguageText(R.string.sport_device_gps_permission_tips_confirm), ResourceUtil.getString(R.string.public_tip_cancel), false);
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.SportBgFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.show(getActivity().getSupportFragmentManager());
    }

    @Override // com.ido.life.module.sport.SportBgContract.View
    public void showDeviceAddDialog() {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.sport_device_add_title), LanguageUtil.getLanguageText(R.string.sport_device_add), LanguageUtil.getLanguageText(R.string.sport_device_add_yes), ResourceUtil.getString(R.string.sport_device_add_no), true);
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.SportBgFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.SportBgFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                    SportBgFragment.this.startActivity(new Intent(SportBgFragment.this.getActivity(), (Class<?>) ChoiceBlueTypeActivity.class));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        commBottomConfirmDialogNewInstance.show(getActivity().getSupportFragmentManager());
    }

    @Override // com.ido.life.module.sport.SportBgContract.View
    public void showDeviceConnectDialog() {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.sport_device_connect_title), LanguageUtil.getLanguageText(R.string.sport_device_connect), LanguageUtil.getLanguageText(R.string.sport_device_connect_yes), ResourceUtil.getString(R.string.sport_device_connect_no), true);
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.SportBgFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.SportBgFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                    SportBgFragment.this.startActivity(new Intent(SportBgFragment.this.getActivity(), (Class<?>) ChoiceBlueTypeActivity.class));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        commBottomConfirmDialogNewInstance.show(getActivity().getSupportFragmentManager());
    }

    @Override // com.ido.life.module.sport.SportBgContract.View
    public void setGpsStatus(int i) {
        this.mCustomGPSView.setGPSSignalStrength(i);
    }
}