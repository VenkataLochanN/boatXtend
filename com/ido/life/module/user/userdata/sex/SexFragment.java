package com.ido.life.module.user.userdata.sex;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.base.BaseFragment;
import com.ido.life.customview.NormalToast;
import com.ido.life.module.user.userdata.sex.SexContract;

/* JADX INFO: loaded from: classes3.dex */
public class SexFragment extends BaseFragment implements SexContract.View {

    @BindView(R.id.cv_man)
    CardView mCvMan;

    @BindView(R.id.cv_woman)
    CardView mCvWoman;

    @BindView(R.id.iv_forward)
    ImageButton mIvForward;
    private OnChangeListener mOnChangeListener;
    private SexContract.Presenter mPresenter;

    @BindView(R.id.tv_man)
    TextView mTvMan;

    @BindView(R.id.tv_title_gender)
    TextView mTvTitleGender;

    @BindView(R.id.tv_woman)
    TextView mTvWoman;
    int sexValue = 1;

    public interface OnChangeListener {
        void onForward();

        void onPageBack();
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_sex;
    }

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.mOnChangeListener = onChangeListener;
    }

    public static SexFragment newInstance() {
        Bundle bundle = new Bundle();
        SexFragment sexFragment = new SexFragment();
        sexFragment.setArguments(bundle);
        return sexFragment;
    }

    @OnClick({R.id.iv_forward})
    public void toUpdateSex(View view) {
        this.mPresenter.saveSex(this.sexValue);
    }

    @OnClick({R.id.iv_back_forward})
    public void backForward(View view) {
        OnChangeListener onChangeListener = this.mOnChangeListener;
        if (onChangeListener != null) {
            onChangeListener.onPageBack();
        }
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void initView() {
        super.initView();
        changeForwardEnable(false);
    }

    @Override // com.ido.life.base.BaseFragment
    protected void refreshLanguage() {
        super.refreshLanguage();
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void initData() {
        this.mPresenter = new SexPresenter(this);
        this.mPresenter.initSex();
        initListener();
    }

    private void initListener() {
        this.mCvMan.setOnTouchListener(new View.OnTouchListener() { // from class: com.ido.life.module.user.userdata.sex.SexFragment.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    SexFragment.this.mCvMan.animate().scaleX(0.8f).scaleY(0.8f).setDuration(500L).start();
                } else if (action == 1) {
                    SexFragment.this.mCvMan.animate().scaleX(1.0f).scaleY(1.0f).setDuration(500L).start();
                    SexFragment sexFragment = SexFragment.this;
                    sexFragment.sexValue = 1;
                    sexFragment.setManSelect();
                    if (!SexFragment.this.mIvForward.isEnabled()) {
                        SexFragment.this.changeForwardEnable(true);
                    }
                }
                return true;
            }
        });
        this.mCvWoman.setOnTouchListener(new View.OnTouchListener() { // from class: com.ido.life.module.user.userdata.sex.SexFragment.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    SexFragment.this.mCvWoman.animate().scaleX(0.8f).scaleY(0.8f).setDuration(500L).start();
                } else if (action == 1) {
                    SexFragment.this.mCvWoman.animate().scaleX(1.0f).scaleY(1.0f).setDuration(500L).start();
                    SexFragment sexFragment = SexFragment.this;
                    sexFragment.sexValue = 2;
                    sexFragment.setWomanSelect();
                    if (!SexFragment.this.mIvForward.isEnabled()) {
                        SexFragment.this.changeForwardEnable(true);
                    }
                }
                return true;
            }
        });
    }

    @Override // com.ido.life.module.user.userdata.sex.SexContract.View
    public void setManSelect() {
        TextView textView;
        if (this.mTvMan == null || (textView = this.mTvWoman) == null) {
            return;
        }
        textView.setBackground(getResources().getDrawable(R.color.color_text_user_sex, null));
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_woman, null);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.mTvWoman.setCompoundDrawables(null, drawable, null, null);
        this.mTvWoman.setTextColor(ResourceUtil.getColor(R.color.color_B3_FFFFFF));
        this.mTvMan.setBackground(ResourceUtil.getDrawable(R.drawable.shape_rectangle_line_green_bg_white_5_corner));
        Drawable drawable2 = getResources().getDrawable(R.mipmap.ic_men_select);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        this.mTvMan.setCompoundDrawables(null, drawable2, null, null);
        this.mTvMan.setTextColor(ResourceUtil.getColor(R.color.color_E51C23));
    }

    @Override // com.ido.life.module.user.userdata.sex.SexContract.View
    public void setWomanSelect() {
        TextView textView = this.mTvMan;
        if (textView == null || this.mTvWoman == null) {
            return;
        }
        textView.setBackground(getResources().getDrawable(R.color.color_text_user_sex, null));
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_men);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.mTvMan.setCompoundDrawables(null, drawable, null, null);
        this.mTvMan.setTextColor(ResourceUtil.getColor(R.color.color_B3_FFFFFF));
        this.mTvWoman.setBackground(ResourceUtil.getDrawable(R.drawable.shape_rectangle_line_green_bg_white_5_corner));
        Drawable drawable2 = getResources().getDrawable(R.mipmap.ic_woman_select);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        this.mTvWoman.setCompoundDrawables(null, drawable2, null, null);
        this.mTvWoman.setTextColor(ResourceUtil.getColor(R.color.color_E51C23));
    }

    @Override // com.ido.life.module.user.userdata.sex.SexContract.View
    public void toForward() {
        OnChangeListener onChangeListener = this.mOnChangeListener;
        if (onChangeListener != null) {
            onChangeListener.onForward();
        }
    }

    @Override // com.ido.life.module.user.userdata.sex.SexContract.View
    public void showLoading() {
        WaitingDialog.showDialog(getActivity());
    }

    @Override // com.ido.life.module.user.userdata.sex.SexContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.userdata.sex.SexContract.View
    public void showMessage(String str) {
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.userdata.sex.SexContract.View
    public void changeForwardEnable(boolean z) {
        this.mIvForward.setEnabled(z);
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(SexContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}