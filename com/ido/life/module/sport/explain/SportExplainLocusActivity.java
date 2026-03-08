package com.ido.life.module.sport.explain;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.base.BaseActivity;

/* JADX INFO: loaded from: classes2.dex */
public class SportExplainLocusActivity extends BaseActivity {

    @BindView(R.id.title_leftBtn)
    ImageButton mTitleLeft;

    @BindView(R.id.title_rightBtn)
    Button mTitleRight;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_sport_explain_locus;
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.com_color_white), true);
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, (Class<?>) SportExplainLocusActivity.class));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mTitleText.setText(LanguageUtil.getLanguageText(R.string.sport_locus_explain_title));
        this.mTitleRight.setVisibility(8);
    }

    @OnClick({R.id.title_leftBtn})
    public void toBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }
}