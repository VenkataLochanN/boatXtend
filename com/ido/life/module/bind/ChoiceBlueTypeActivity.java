package com.ido.life.module.bind;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.module.main.MainActivity;

/* JADX INFO: loaded from: classes2.dex */
public class ChoiceBlueTypeActivity extends BaseActivity {
    public static String FROM_WHERE = "from_where";
    private String mType;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_chooice_blue_type;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mType = getIntent().getStringExtra(FROM_WHERE);
        if (!TextUtils.isEmpty(this.mType) && "register".equals(this.mType)) {
            this.mTitleBar.initLayout(2).setRightText(R.string.login_skip).setRightTextColor(ResourceUtil.getColor(R.color.white)).setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.bind.-$$Lambda$ChoiceBlueTypeActivity$Cfcjw4Soj1trq5noRNj3nNBDefA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initViews$0$ChoiceBlueTypeActivity(view);
                }
            });
            this.mTitleBar.getTitleLayoutLeft(this).setVisibility(4);
        } else {
            this.mTitleBar.initLayout(0).setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.bind.-$$Lambda$ChoiceBlueTypeActivity$7PmvwA9MA3kYgG2dKhD0hGoZgu0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initViews$1$ChoiceBlueTypeActivity(view);
                }
            });
        }
        this.mTitleBar.setTitle(R.string.add_device);
    }

    public /* synthetic */ void lambda$initViews$0$ChoiceBlueTypeActivity(View view) {
        onBackPressed();
    }

    public /* synthetic */ void lambda$initViews$1$ChoiceBlueTypeActivity(View view) {
        if (!TextUtils.isEmpty(this.mType) && this.mType.equals("register")) {
            startActivity(new Intent(this, (Class<?>) MainActivity.class));
            supportFinishAfterTransition();
        } else {
            onBackPressed();
        }
    }

    @OnClick({R.id.item_watch, R.id.item_bracelet})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.item_bracelet) {
            SearchActivity.start(this, 2);
        } else {
            if (id != R.id.item_watch) {
                return;
            }
            SearchActivity.start(this, 1);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (!TextUtils.isEmpty(this.mType) && "register".equals(this.mType)) {
            MainActivity.startActivity(this, 301);
            supportFinishAfterTransition();
        } else {
            super.onBackPressed();
        }
    }
}