package com.ido.life.module.bind;

import android.content.Intent;
import android.view.View;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.life.base.BaseActivity;
import com.ido.life.module.main.MainActivity;

/* JADX INFO: loaded from: classes2.dex */
public class PreSearchActivity extends BaseActivity {
    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_pre_search;
    }

    @OnClick({R.id.tv_not_bind, R.id.rtv_btn_bottom})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id != R.id.rtv_btn_bottom) {
            if (id != R.id.tv_not_bind) {
                return;
            }
            startActivity(new Intent(this, (Class<?>) MainActivity.class));
            supportFinishAfterTransition();
            return;
        }
        Intent intent = new Intent(this, (Class<?>) ChoiceBlueTypeActivity.class);
        intent.putExtra(ChoiceBlueTypeActivity.FROM_WHERE, "register");
        startActivity(intent);
        supportFinishAfterTransition();
    }
}