package com.ido.life.module.user.lang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.StatusBarUtil;
import com.ido.life.bean.Language;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.module.user.lang.LanguageContract;
import com.ido.life.util.LanguageManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class LanguageActivity extends BaseCoreActivity implements LanguageContract.View, View.OnClickListener {
    private static final String TAG = "LanguageActivity";
    private CommonRecyclerViewAdapter mAdapter;
    private CommBottomConfirmDialog mConfirmDialog;
    private List<Language> mList;
    private LanguageContract.Presenter mPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private int mSelectedPosition;

    @BindView(R.id.title_text)
    TextView mTitleText;

    public interface FinishCallBack {
        void onFailed();

        void onSuccess();
    }

    @Override // com.ido.life.module.user.lang.LanguageContract.View
    public String getLanguage() {
        return null;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_language;
    }

    @Override // com.ido.life.module.user.lang.LanguageContract.View
    public void selected(int i) {
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) LanguageActivity.class));
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initView();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mList = new ArrayList();
        String[] stringArray = getResources().getStringArray(R.array.language_array);
        String language = LanguageManager.getLanguage(this);
        for (String str : stringArray) {
            String[] strArrSplit = str.split("_");
            String str2 = strArrSplit[1];
            this.mList.add(new Language(strArrSplit[0], str2, TextUtils.equals(str2, language)));
        }
        if (TextUtils.isEmpty(language)) {
            this.mList.get(0).setChecked(true);
        }
        this.mPresenter = new LanguagePresenter(this);
        this.mPresenter.start();
    }

    private void initView() {
        this.mTitleText.setText(LanguageUtil.getLanguageText(R.string.mine_language_set));
        RecyclerView recyclerView = this.mRecyclerView;
        CommonRecyclerViewAdapter<Language> commonRecyclerViewAdapter = new CommonRecyclerViewAdapter<Language>(this, R.layout.item_language_layout, this.mList) { // from class: com.ido.life.module.user.lang.LanguageActivity.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, Language language, int i) {
                ((TextView) commonRecyclerViewHolder.getView(R.id.tv_name)).setText(language.getName());
                if (language.isChecked()) {
                    commonRecyclerViewHolder.getView(R.id.img_state).setVisibility(0);
                } else {
                    commonRecyclerViewHolder.getView(R.id.img_state).setVisibility(4);
                }
                commonRecyclerViewHolder.itemView.setOnClickListener(LanguageActivity.this);
                commonRecyclerViewHolder.itemView.setTag(Integer.valueOf(i));
            }
        };
        this.mAdapter = commonRecyclerViewAdapter;
        recyclerView.setAdapter(commonRecyclerViewAdapter);
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(LanguageContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @OnClick({R.id.title_leftBtn})
    public void toBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.lay_item && (view.getTag() instanceof Integer)) {
            int i = this.mSelectedPosition;
            this.mSelectedPosition = ((Integer) view.getTag()).intValue();
            this.mAdapter.notifyItemChanged(i);
            this.mAdapter.notifyItemChanged(this.mSelectedPosition);
            showLanguageConfirmDialog();
        }
    }

    private void showLanguageConfirmDialog() {
        if (this.mConfirmDialog == null) {
            this.mConfirmDialog = getConfirmDialog();
        }
        try {
            this.mConfirmDialog.show(getSupportFragmentManager());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private CommBottomConfirmDialog getConfirmDialog() {
        CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.mine_confirm_change_language), LanguageUtil.getLanguageText(R.string.public_tip_confirm), LanguageUtil.getLanguageText(R.string.public_tip_cancel), true);
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.lang.-$$Lambda$LanguageActivity$SChsAnEMq3CLLgPf_DY9ShvD9dg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$getConfirmDialog$0$LanguageActivity(view);
            }
        });
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.user.lang.-$$Lambda$LanguageActivity$zXkZ7F58007r3xleOnGBZuNlxlo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$getConfirmDialog$1$LanguageActivity(view);
            }
        });
        return commBottomConfirmDialogNewInstance;
    }

    public /* synthetic */ void lambda$getConfirmDialog$0$LanguageActivity(View view) {
        final String region = this.mList.get(this.mSelectedPosition).getRegion();
        if (TextUtils.isEmpty(region)) {
            return;
        }
        this.mPresenter.getMultilLanguage(region, new FinishCallBack() { // from class: com.ido.life.module.user.lang.LanguageActivity.2
            @Override // com.ido.life.module.user.lang.LanguageActivity.FinishCallBack
            public void onSuccess() {
                CommonLogUtil.d(LanguageActivity.TAG, "onSuccess: " + region + "语言修改成功");
                LanguageActivity.this.mPresenter.doChangeLanguage(region);
                LanguageActivity.this.finish();
            }

            @Override // com.ido.life.module.user.lang.LanguageActivity.FinishCallBack
            public void onFailed() {
                CommonLogUtil.d(LanguageActivity.TAG, "onSuccess: " + region + "语言修改失败");
            }
        });
    }

    public /* synthetic */ void lambda$getConfirmDialog$1$LanguageActivity(View view) {
        Toast.makeText(getApplicationContext(), "取消", 0).show();
    }
}