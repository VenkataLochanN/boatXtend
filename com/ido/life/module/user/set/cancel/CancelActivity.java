package com.ido.life.module.user.set.cancel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.StatusBarUtil;

/* JADX INFO: loaded from: classes3.dex */
public class CancelActivity extends BaseCoreActivity {
    public static final int REQUEST_CODE = 1001;
    public static final int RESULT_CODE = 1002;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_cancel;
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) CancelActivity.class));
    }

    public static void startActivityForResult(Activity activity) {
        activity.startActivityForResult(new Intent(activity, (Class<?>) CancelActivity.class), 1001);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initView();
    }

    private void initView() {
        this.mTitleText.setText(ResourceUtil.getString(R.string.login_cancel_account));
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
    }

    @OnClick({R.id.tv_cancel_apply})
    public void toApplyCancel(View view) {
        CancelConfirmActivity.startActivityForResult(this);
    }

    @OnClick({R.id.title_leftBtn})
    public void toBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 5002 && i2 == 5003) {
            setResult(1002, new Intent());
            ActivityCompat.finishAfterTransition(this);
        }
    }
}