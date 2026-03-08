package com.ido.life.module.user.about;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.utils.AppInfoUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.StatusBarUtil;
import com.ido.common.utils.TimeUtil;
import com.ido.life.constants.Constants;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.data.AuthorizationPreference;
import com.ido.life.module.device.activity.NativeWebViewActivity;
import com.ido.life.module.user.UserFragment;
import com.ido.life.module.user.about.AboutUsContract;
import com.ido.life.util.SPUtils;
import com.ido.life.util.eventbus.EventBusHelper;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes.dex */
public class AboutUsActivity extends BaseCoreActivity implements AboutUsContract.View {
    private static final int MAX_CLICK_COUNT = 5;
    private static final int MAX_CLICK_DURATION = 5000;
    private CommBottomConfirmDialog mDialog;

    @BindView(R.id.iv_logo_about)
    ImageView mIvLogoAbout;

    @BindView(R.id.opt_disagree_policy)
    CustomItemLabelView mOptDisagreePolicy;

    @BindView(R.id.opt_privacy_policy)
    CustomItemLabelView mOptPrivacyPolicy;

    @BindView(R.id.opt_user_agreement)
    CustomItemLabelView mOptUserAgreement;
    private AboutUsPresenter mPresenter;

    @BindView(R.id.title_leftBtn)
    ImageButton mTitleLeftBtn;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.tv_app_name)
    TextView mTvAppName;

    @BindView(R.id.tv_version)
    TextView mTvVersion;
    private int mClickCount = 0;
    private Runnable mShowShareRunable = new Runnable() { // from class: com.ido.life.module.user.about.-$$Lambda$AboutUsActivity$JczsW6mGXozqKAMA-fGS8TV05p8
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$AboutUsActivity();
        }
    };

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_about_us;
    }

    @Override // com.ido.life.module.user.about.AboutUsContract.View
    public void onGetNewVersion(String str) {
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(AboutUsContract.Presenter presenter) {
    }

    public /* synthetic */ void lambda$new$0$AboutUsActivity() {
        this.mClickCount = 0;
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) AboutUsActivity.class));
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initView();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter = new AboutUsPresenter(this);
        this.mPresenter.requestVersionInfo();
    }

    private void initView() {
        this.mTvAppName.setText(LanguageUtil.getLanguageText(R.string.me_about_app_name));
        this.mTitleText.setText(LanguageUtil.getLanguageText(R.string.mine_about));
        this.mTvVersion.setText("V " + AppInfoUtil.getVersionName(this));
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mTitleText.setText(LanguageUtil.getLanguageText(R.string.mine_about));
        this.mTitleText.setTypeface(Typeface.defaultFromStyle(1));
        this.mTvVersion.setText("V " + AppInfoUtil.getVersionName(this));
        this.mOptUserAgreement.setTitle(LanguageUtil.getLanguageText(R.string.mine_user_agreement));
        this.mOptPrivacyPolicy.setTitle(LanguageUtil.getLanguageText(R.string.logn_detail_policy_ios));
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
    }

    @OnClick({R.id.title_leftBtn})
    public void toBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @OnClick({R.id.opt_user_agreement})
    public void toUserAgreement(View view) {
        Intent intent = new Intent(this, (Class<?>) NativeWebViewActivity.class);
        intent.putExtra("type", 2);
        startActivity(intent);
    }

    @OnClick({R.id.opt_privacy_policy})
    public void toPrivacyPolicy(View view) {
        Intent intent = new Intent(this, (Class<?>) NativeWebViewActivity.class);
        intent.putExtra("type", 3);
        startActivity(intent);
    }

    @OnClick({R.id.opt_disagree_policy})
    public void disagreePolicy(View view) {
        if (this.mDialog == null) {
            this.mDialog = CommBottomConfirmDialog.newInstance(String.format(getString(R.string.disagree_policy_message), TimeUtil.convTime(((Long) SPUtils.get(Constants.AGREE_POLICY_TIME, 0L)).longValue())), getString(R.string.disagree_policy), getString(R.string.public_tip_cancel), true);
        }
        this.mDialog.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.about.-$$Lambda$AboutUsActivity$I2xbwjA8x_3-cnznmfFN5jzI6l4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$disagreePolicy$1$AboutUsActivity(view2);
            }
        });
        this.mDialog.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$disagreePolicy$1$AboutUsActivity(View view) {
        SPUtils.put(Constants.AGREE_POLICY_TIME, 0L);
        AuthorizationPreference.setToken(this, "");
        this.mTvAppName.postDelayed(new Runnable() { // from class: com.ido.life.module.user.about.AboutUsActivity.1
            @Override // java.lang.Runnable
            public void run() {
                EventBusHelper.post(306);
            }
        }, 500L);
    }

    @OnClick({R.id.iv_logo_about})
    public void logoClick(View view) {
        if (((Boolean) SPUtils.get(UserFragment.SHOW_SHARE, false)).booleanValue()) {
            return;
        }
        if (this.mClickCount == 0) {
            view.postDelayed(this.mShowShareRunable, BootloaderScanner.TIMEOUT);
        }
        this.mClickCount++;
        if (this.mClickCount > 5) {
            SPUtils.put(UserFragment.SHOW_SHARE, true);
            view.removeCallbacks(this.mShowShareRunable);
            this.mClickCount = 0;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        AboutUsPresenter aboutUsPresenter = this.mPresenter;
        if (aboutUsPresenter != null) {
            aboutUsPresenter.detachView();
        }
    }
}