package com.ido.life.module.alexa;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.alexa.data.UserCodeResponse;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.module.device.activity.CommonWebViewActivity;

/* JADX INFO: loaded from: classes2.dex */
public class CBLLoginGuideActivity extends BaseActivity {
    public static final String KEY_BEAN = "key_UserCodeResponse";

    @BindView(R.id.rtv_code)
    TextView mTvCode;

    @BindView(R.id.rtv_go)
    TextView mTvGo;
    private UserCodeResponse mUserCodeResponse;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_c_b_l_login_guide;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        if (getIntent().getExtras() == null) {
            finish();
        }
        this.mUserCodeResponse = (UserCodeResponse) getIntent().getSerializableExtra(KEY_BEAN);
        this.mTvCode.setText(this.mUserCodeResponse.getUser_code());
        ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Label", this.mUserCodeResponse.getUser_code()));
        this.mTvGo.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.alexa.-$$Lambda$CBLLoginGuideActivity$7H1eQFc4u_tlmqbQSQKEeMYDyK8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initData$0$CBLLoginGuideActivity(view);
            }
        });
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.alexa.-$$Lambda$CBLLoginGuideActivity$3_5FduPjJqk3Y2q8q6l1OXAnF10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initData$1$CBLLoginGuideActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initData$0$CBLLoginGuideActivity(View view) {
        SingleTopIntent singleTopIntent = new SingleTopIntent(this, (Class<?>) CommonWebViewActivity.class);
        singleTopIntent.putExtra("type", 12);
        singleTopIntent.putExtra(CommonWebViewActivity.FORM_URL, this.mUserCodeResponse.getVerification_uri());
        startActivity(singleTopIntent);
        setResult(-1);
        finish();
    }

    public /* synthetic */ void lambda$initData$1$CBLLoginGuideActivity(View view) {
        setResult(0);
        finish();
    }
}