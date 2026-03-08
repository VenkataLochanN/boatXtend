package com.ido.life.module.user.set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.dialog.ChooseDialogFragment;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.StatusBarUtil;
import com.ido.life.base.BaseMessage;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.viewgroup.OptionView;
import com.ido.life.module.guide.WelcomeGuideActivity;
import com.ido.life.module.user.lang.LanguageActivity;
import com.ido.life.module.user.set.SettingContract;
import com.ido.life.module.user.set.account.AccountActivity;
import com.ido.life.module.user.set.data.DataShareActivity;
import com.ido.life.util.eventbus.EventBusHelper;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes3.dex */
public class SettingActivity extends BaseCoreActivity implements SettingContract.View {
    private static final String TAG = "SettingActivity";
    private int mCount = 0;

    @BindView(R.id.title_bar)
    FrameLayout mFrameLayout;

    @BindView(R.id.opt_remove_cache)
    OptionView mOptRemoveCache;

    @BindView(R.id.opt_account_security)
    OptionView mOpt_account_security;

    @BindView(R.id.opt_data_sharing)
    OptionView mOpt_data_sharing;

    @BindView(R.id.opt_language)
    OptionView mOpt_language;

    @BindView(R.id.opt_unit)
    OptionView mOpt_unit;
    private SettingContract.Presenter mPresenter;
    private Handler mTestHandler;
    private TestRunnable mTestRunnable;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_setting;
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) SettingActivity.class));
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initView();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter = new SettingPresenter(this);
        this.mPresenter.getCacheSize(this);
    }

    private void initView() {
        this.mTitleText.setText(LanguageUtil.getLanguageText(R.string.mine_set));
        this.mOpt_account_security.setStartText(LanguageUtil.getLanguageText(R.string.mine_account_set));
        this.mOpt_language.setStartText(LanguageUtil.getLanguageText(R.string.mine_language_set));
        this.mOpt_unit.setStartText(LanguageUtil.getLanguageText(R.string.mine_unit_set));
        this.mOpt_data_sharing.setStartText(LanguageUtil.getLanguageText(R.string.mine_data_compare));
        this.mOptRemoveCache.setStartText(LanguageUtil.getLanguageText(R.string.me_clear_cache_ios));
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
    }

    @OnClick({R.id.title_leftBtn})
    public void back(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @OnClick({R.id.opt_account_security})
    public void toAccountSecurity(View view) {
        if (this.mPresenter.isUserLogin()) {
            AccountActivity.startActivityForResult(this);
        } else {
            showLoginDialog();
        }
    }

    @OnClick({R.id.title_bar})
    public void toUploadData(View view) {
        if (this.mCount == 0) {
            if (this.mTestHandler == null) {
                this.mTestHandler = new Handler();
            }
            if (this.mTestRunnable == null) {
                this.mTestRunnable = new TestRunnable();
            }
            this.mTestHandler.removeCallbacks(this.mTestRunnable);
            this.mTestHandler.postDelayed(this.mTestRunnable, BootloaderScanner.TIMEOUT);
        }
        this.mCount++;
    }

    class TestRunnable implements Runnable {
        TestRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SettingActivity.this.mCount = 0;
        }
    }

    public void showLoginDialog() {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(ResourceUtil.getString(R.string.mine_user_no_login), ResourceUtil.getString(R.string.me_main_login_register_ios), ResourceUtil.getString(R.string.public_tip_cancel), true);
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.SettingActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.SettingActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                SettingActivity.this.goGuide();
            }
        });
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
    }

    public void goGuide() {
        WelcomeGuideActivity.startActivity(this);
        ActivityCompat.finishAfterTransition(this);
    }

    @OnClick({R.id.opt_language})
    public void toLanguageSet(View view) {
        LanguageActivity.startActivity(this);
    }

    @OnClick({R.id.opt_unit})
    public void toUnitSet(View view) {
        ChooseDialogFragment chooseDialogFragmentNewInstance = ChooseDialogFragment.newInstance(new String[]{LanguageUtil.getLanguageText(R.string.mine_unit_metric_system), LanguageUtil.getLanguageText(R.string.mine_unit_british_system)}, false);
        chooseDialogFragmentNewInstance.show(getSupportFragmentManager());
        chooseDialogFragmentNewInstance.setOnChooseListener(new ChooseDialogFragment.OnChooseListener() { // from class: com.ido.life.module.user.set.SettingActivity.3
            @Override // com.ido.common.dialog.ChooseDialogFragment.OnChooseListener
            public void choose(int i) {
                SettingActivity.this.mPresenter.changeUnit(i);
            }
        });
    }

    @OnClick({R.id.opt_data_sharing})
    public void toDataShare(View view) {
        DataShareActivity.startActivity(this);
    }

    @OnClick({R.id.opt_remove_cache})
    public void toRemoveCache(View view) {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.mine_tip_clear_cache), LanguageUtil.getLanguageText(R.string.public_tip_confirm), LanguageUtil.getLanguageText(R.string.public_tip_cancel), true);
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.SettingActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SettingActivity.this.mPresenter.doClearCache(SettingActivity.this);
                NormalToast.showToast(LanguageUtil.getLanguageText(R.string.mine_clear_cache_done));
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.SettingActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mTitleText.setText(LanguageUtil.getLanguageText(R.string.mine_set));
        this.mOpt_account_security.setStartText(LanguageUtil.getLanguageText(R.string.mine_account_set));
        this.mOpt_language.setStartText(LanguageUtil.getLanguageText(R.string.mine_language_set));
        this.mOpt_unit.setStartText(LanguageUtil.getLanguageText(R.string.mine_unit_set));
        this.mOpt_data_sharing.setStartText(LanguageUtil.getLanguageText(R.string.mine_data_compare));
        this.mOptRemoveCache.setStartText(LanguageUtil.getLanguageText(R.string.me_clear_cache_ios));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001 && i2 == 1002) {
            Intent intent2 = new Intent(this, (Class<?>) WelcomeGuideActivity.class);
            intent2.addFlags(32768);
            startActivity(intent2);
            EventBusHelper.post(new BaseMessage(501));
            ActivityCompat.finishAfterTransition(this);
        }
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(SettingContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // com.ido.life.module.user.set.SettingContract.View
    public void showCacheSize(String str) {
        this.mOptRemoveCache.setEndText(str);
    }
}