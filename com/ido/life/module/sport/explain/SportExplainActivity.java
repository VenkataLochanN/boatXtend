package com.ido.life.module.sport.explain;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.module.device.activity.CommonWebViewActivity;

/* JADX INFO: loaded from: classes2.dex */
public class SportExplainActivity extends BaseActivity {
    static final String CONTENT_KEY = "content_key";
    static final String SPORT_TITLE_KEY = "sport_title_key";
    static final String TITLE_KEY = "title_key";

    @BindView(R.id.tv_explain_content)
    RegularTextView mTvExplainContent;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_sport_explain;
    }

    public static void start(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, (Class<?>) SportExplainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(TITLE_KEY, str);
        bundle.putString(SPORT_TITLE_KEY, str2);
        bundle.putString(CONTENT_KEY, str3);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();
        extras.getString(TITLE_KEY);
        extras.getString(SPORT_TITLE_KEY);
        this.mTvExplainContent.setText(extras.getString(CONTENT_KEY));
        this.mTitleBar.setBarBackground(0);
    }

    @OnClick({R.id.lay_sport_type_explain, R.id.lay_sport_name_explain, R.id.lay_sport_train_explain})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lay_sport_name_explain /* 2131362765 */:
                SingleTopIntent singleTopIntent = new SingleTopIntent(getApplication(), (Class<?>) CommonWebViewActivity.class);
                singleTopIntent.putExtra("type", 19);
                startActivity(singleTopIntent);
                break;
            case R.id.lay_sport_train_explain /* 2131362766 */:
                SingleTopIntent singleTopIntent2 = new SingleTopIntent(getApplication(), (Class<?>) CommonWebViewActivity.class);
                singleTopIntent2.putExtra("type", 18);
                startActivity(singleTopIntent2);
                break;
            case R.id.lay_sport_type_explain /* 2131362767 */:
                SingleTopIntent singleTopIntent3 = new SingleTopIntent(getApplication(), (Class<?>) CommonWebViewActivity.class);
                singleTopIntent3.putExtra("type", 20);
                startActivity(singleTopIntent3);
                break;
        }
    }
}