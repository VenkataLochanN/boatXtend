package com.ido.life.module.user.set.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.PermissionUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.StatusBarUtil;
import com.ido.life.database.model.PrivateSafeSetting;
import com.ido.life.module.user.set.data.googlefit.GoogleFitPresenter;
import com.ido.life.module.user.set.data.strava.StravaAccreditActivity;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class DataShareActivity extends BaseCoreActivity implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "DataShareActivity";
    int STRAVA_ACCREDIT_CODE = 100;
    private GoogleFitPresenter mGoogleFitPresenter;

    @BindView(R.id.relative_google_fit)
    RelativeLayout mRelativeGoogleFit;

    @BindView(R.id.relative_strava)
    RelativeLayout mRelativeLayoutStava;

    @BindView(R.id.switch_google_fit)
    Switch mSwitchGoogleFit;

    @BindView(R.id.switch_stava)
    ImageView mSwitchStava;

    @BindView(R.id.title_leftBtn)
    ImageButton mTitleLeftBtn;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_data_share;
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) DataShareActivity.class));
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initView();
        initListener();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        boolean saveToGoogleFit;
        super.onResume();
        PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
        if (privateSafeSettingQueryPrivateSafeSetting != null) {
            saveToGoogleFit = privateSafeSettingQueryPrivateSafeSetting.getSaveToGoogleFit();
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onResume()  mSafeSetting = " + privateSafeSettingQueryPrivateSafeSetting.toString());
        } else {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onResume()  mSafeSetting 为空");
            saveToGoogleFit = false;
        }
        this.mSwitchGoogleFit.setOnCheckedChangeListener(null);
        if (saveToGoogleFit) {
            this.mSwitchGoogleFit.setChecked(true);
        } else {
            this.mSwitchGoogleFit.setChecked(false);
        }
        this.mSwitchGoogleFit.setOnCheckedChangeListener(this);
    }

    private void initView() {
        this.mTitleText.setText(ResourceUtil.getString(R.string.data_share_authorization));
    }

    private void initListener() {
        this.mRelativeLayoutStava.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.data.-$$Lambda$DataShareActivity$Hyka3TEBKbdFcQYmRAOrvZ_3moQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initListener$0$DataShareActivity(view);
            }
        });
        this.mRelativeGoogleFit.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.data.-$$Lambda$DataShareActivity$h9_og17sZNkKUVh8GLKE3_jlWQI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initListener$1$DataShareActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$DataShareActivity(View view) {
        startActivity(new Intent(this, (Class<?>) StravaAccreditActivity.class));
    }

    public /* synthetic */ void lambda$initListener$1$DataShareActivity(View view) {
        if (Build.VERSION.SDK_INT >= 29 && !PermissionUtil.checkSelfPermission(this, "android.permission.ACTIVITY_RECOGNITION")) {
            requestPermissions(102, "android.permission.ACTIVITY_RECOGNITION");
        } else {
            startActivity(new Intent(this, (Class<?>) GoogleFitAccreditActivity.class));
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mGoogleFitPresenter = GoogleFitPresenter.getInstance();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    public void requestPermissions(int i, String... strArr) {
        PermissionUtil.requestPermissions(this, i, strArr);
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        CommonLogUtil.d(TAG, "handleIntent: " + intent.getAction() + AppInfo.DELIM + intent.getData().toString());
    }

    @OnClick({R.id.title_leftBtn})
    public void toBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        CommonLogUtil.d(TAG, "onActivityResult:  " + i + AppInfo.DELIM + i2);
        if (i == 3) {
            this.mGoogleFitPresenter.handleSignInResult(i, intent, this);
            return;
        }
        if (this.STRAVA_ACCREDIT_CODE == i) {
            CommonLogUtil.d(TAG, "onActivityResult: 授权成功");
            return;
        }
        GoogleFitPresenter googleFitPresenter = this.mGoogleFitPresenter;
        if (i == 10 && -1 == i2) {
            GoogleFitPresenter.setSubScribed(true);
            this.mGoogleFitPresenter.subscriber(this);
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton.getId() != R.id.switch_google_fit) {
            return;
        }
        if (z) {
            CommBottomConfirmDialog.newInstance(getString(R.string.register_cloud_sync_title), getString(R.string.google_dialog_agree), getString(R.string.login_agree_continue), getString(R.string.sport_device_add_no), true, false).setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.data.-$$Lambda$DataShareActivity$XTxTfJqZugwH8ngfYKEkzkYKWB8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onCheckedChanged$2$DataShareActivity(view);
                }
            }).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.data.-$$Lambda$DataShareActivity$67mma4Mha89Ezpl5syVqCvwKBzk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onCheckedChanged$3$DataShareActivity(view);
                }
            }).show(getSupportFragmentManager());
        } else {
            CommBottomConfirmDialog.newInstance(getString(R.string.register_cloud_sync_title), getString(R.string.google_dialog_disagree), getString(R.string.me_disagree), getString(R.string.sport_device_add_no), true, false).setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.data.-$$Lambda$DataShareActivity$p3AW4CVODi_gLMhOXjmKOxzIs4I
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onCheckedChanged$4$DataShareActivity(view);
                }
            }).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.data.-$$Lambda$DataShareActivity$p-5seLlXaWV3nwJBKpGnACRGHBk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DataShareActivity.lambda$onCheckedChanged$5(view);
                }
            }).show(getSupportFragmentManager());
        }
    }

    public /* synthetic */ void lambda$onCheckedChanged$2$DataShareActivity(View view) {
        this.mSwitchGoogleFit.setOnCheckedChangeListener(null);
        this.mSwitchGoogleFit.setChecked(false);
        this.mSwitchGoogleFit.setOnCheckedChangeListener(this);
        PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
        if (privateSafeSettingQueryPrivateSafeSetting != null) {
            privateSafeSettingQueryPrivateSafeSetting.setSaveToGoogleFit(false);
            privateSafeSettingQueryPrivateSafeSetting.update();
        }
    }

    public /* synthetic */ void lambda$onCheckedChanged$3$DataShareActivity(View view) {
        this.mGoogleFitPresenter.connectGoogle(this);
    }

    public /* synthetic */ void lambda$onCheckedChanged$4$DataShareActivity(View view) {
        PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
        this.mSwitchGoogleFit.setOnCheckedChangeListener(null);
        this.mSwitchGoogleFit.setChecked(true);
        this.mSwitchGoogleFit.setOnCheckedChangeListener(this);
        if (privateSafeSettingQueryPrivateSafeSetting != null) {
            privateSafeSettingQueryPrivateSafeSetting.setSaveToGoogleFit(true);
            privateSafeSettingQueryPrivateSafeSetting.update();
        }
    }

    static /* synthetic */ void lambda$onCheckedChanged$5(View view) {
        PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
        if (privateSafeSettingQueryPrivateSafeSetting != null) {
            privateSafeSettingQueryPrivateSafeSetting.setSaveToGoogleFit(false);
            privateSafeSettingQueryPrivateSafeSetting.update();
        }
    }
}