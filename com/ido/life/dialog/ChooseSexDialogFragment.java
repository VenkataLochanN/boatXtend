package com.ido.life.dialog;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.LanguageUtil;
import com.watch.life.wheelview.adapter.ArrayWheelAdapter;
import com.watch.life.wheelview.listener.OnItemSelectedListener;
import com.watch.life.wheelview.view.WheelView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class ChooseSexDialogFragment extends BaseDialogFragment implements View.OnClickListener {
    private static final String CHOOSE_TYPE = "select_type";
    public static final int FEMALE = 2;
    public static final int MALE = 1;
    private OnChooseListener mOnChooseListener;
    private int mType = 1;

    @BindView(R.id.wheel_sex)
    WheelView mWheelViewSex;

    public interface OnChooseListener {
        void choose(int i);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_choose_sex;
    }

    public static ChooseSexDialogFragment newInstance(int i) {
        ChooseSexDialogFragment chooseSexDialogFragment = new ChooseSexDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CHOOSE_TYPE, i);
        chooseSexDialogFragment.setArguments(bundle);
        chooseSexDialogFragment.setStyle(1, 2131886083);
        return chooseSexDialogFragment;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        this.mType = getArguments().getInt(CHOOSE_TYPE, this.mType);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        if (getDialog() != null && getDialog().getWindow() != null) {
            Window window = getDialog().getWindow();
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 80;
            attributes.width = -1;
            window.setAttributes(attributes);
        }
        this.mWheelViewSex.setCyclic(false);
        this.mWheelViewSex.setItemsVisibleCount(3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(LanguageUtil.getLanguageText(R.string.me_all_male_ios));
        arrayList.add(LanguageUtil.getLanguageText(R.string.me_all_female_ios));
        this.mWheelViewSex.setAdapter(new ArrayWheelAdapter(arrayList));
        updateView();
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initListener(View view) {
        super.initListener(view);
        this.mWheelViewSex.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.life.dialog.-$$Lambda$ChooseSexDialogFragment$0rx3Y2_fYV8ikHDGGryrEANQFOc
            @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
            public final void onItemSelected(int i) {
                this.f$0.lambda$initListener$0$ChooseSexDialogFragment(i);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$ChooseSexDialogFragment(int i) {
        if (i == 0) {
            this.mType = 1;
        } else {
            this.mType = 2;
        }
        this.mOnChooseListener.choose(this.mType);
    }

    private void updateView() {
        if (this.mType == 1) {
            this.mWheelViewSex.setCurrentItem(0);
        } else {
            this.mWheelViewSex.setCurrentItem(1);
        }
    }

    public void setOnChooseListener(OnChooseListener onChooseListener) {
        this.mOnChooseListener = onChooseListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        view.getId();
    }

    public void setChooseIndex(int i) {
        this.mType = i;
        updateView();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            dismissAllowingStateLoss();
        }
    }
}