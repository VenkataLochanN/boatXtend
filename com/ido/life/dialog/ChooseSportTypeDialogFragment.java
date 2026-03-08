package com.ido.life.dialog;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.module.user.sportrecord.model.SportScreenType;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ChooseSportTypeDialogFragment extends BaseDialogFragment {
    private static final String CHOOSE_CANCEL = "choosecancel";
    public static final int CHOOSE_CANCEL_NONE = -1;
    private static final String CHOOSE_INDEX = "choose_index";
    private static final String CHOOSE_LIST = "chooselist";
    RadioGroup mChooseContentFl;
    private OnChooseListener mOnChooseListener;

    public interface OnChooseListener {
        void choose(int i);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_choose_sport;
    }

    public static ChooseSportTypeDialogFragment newInstance(List<SportScreenType> list, int i) {
        ChooseSportTypeDialogFragment chooseSportTypeDialogFragment = new ChooseSportTypeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CHOOSE_LIST, (Serializable) list);
        bundle.putInt(CHOOSE_INDEX, i);
        chooseSportTypeDialogFragment.setArguments(bundle);
        chooseSportTypeDialogFragment.setStyle(1, 2131886083);
        return chooseSportTypeDialogFragment;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        Bundle arguments = getArguments();
        initView((List) arguments.getSerializable(CHOOSE_LIST), arguments.getInt(CHOOSE_INDEX));
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        super.initUI(view);
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = -1;
        window.setAttributes(attributes);
    }

    private void initView(List<SportScreenType> list, int i) {
        this.mChooseContentFl = (RadioGroup) getView().findViewById(R.id.choose_content_fl);
        createChooseView(list, i);
    }

    private void cancel() {
        OnChooseListener onChooseListener = this.mOnChooseListener;
        if (onChooseListener != null) {
            onChooseListener.choose(-1);
        }
        dismiss();
    }

    private void createChooseView(List<SportScreenType> list, int i) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (final int i2 = 0; i2 < list.size(); i2++) {
            RadioButton radioButton = new RadioButton(getActivity());
            radioButton.setLayoutParams(new FrameLayout.LayoutParams(-1, DipPixelUtil.dip2px(45.0f)));
            radioButton.setTextSize(2, 16.0f);
            radioButton.setTextColor(ResourceUtil.getColor(R.color.color_B3_FFFFFF));
            radioButton.setLayoutDirection(1);
            radioButton.setButtonDrawable(ResourceUtil.getDrawable(R.drawable.selector_radio_sport_voice));
            radioButton.setGravity(19);
            radioButton.setText(list.get(i2).getTypeName());
            if (i == i2) {
                radioButton.setChecked(true);
            }
            this.mChooseContentFl.addView(radioButton);
            radioButton.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.dialog.ChooseSportTypeDialogFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ChooseSportTypeDialogFragment.this.mOnChooseListener != null) {
                        ChooseSportTypeDialogFragment.this.mOnChooseListener.choose(i2);
                    }
                    ChooseSportTypeDialogFragment.this.dismiss();
                }
            });
        }
    }

    private Drawable getDrawableByIndex(int i) {
        Drawable drawable = ResourceUtil.getDrawable(R.mipmap.ic_sport_item_outdoor_run);
        switch (i) {
            case 1:
                drawable = ResourceUtil.getDrawable(R.mipmap.ic_sport_item_outdoor_run);
                break;
            case 2:
                drawable = ResourceUtil.getDrawable(R.mipmap.ic_sport_item_indoor_run);
                break;
            case 3:
                drawable = ResourceUtil.getDrawable(R.mipmap.ic_sport_item_outdoor_walk);
                break;
            case 4:
                drawable = ResourceUtil.getDrawable(R.mipmap.ic_sport_item_indoor_walk);
                break;
            case 5:
                drawable = ResourceUtil.getDrawable(R.mipmap.ic_sport_item_hiking);
                break;
            case 6:
                drawable = ResourceUtil.getDrawable(R.mipmap.ic_sport_item_outdoor_cycle);
                break;
            case 7:
                drawable = ResourceUtil.getDrawable(R.mipmap.ic_sport_item_indoor_swim);
                break;
            case 8:
                drawable = ResourceUtil.getDrawable(R.mipmap.ic_sport_item_outdoor_swim);
                break;
            case 9:
                drawable = ResourceUtil.getDrawable(R.mipmap.ic_sport_item_yogo);
                break;
            case 10:
                drawable = ResourceUtil.getDrawable(R.mipmap.ic_sport_item_other);
                break;
            case 11:
                drawable = ResourceUtil.getDrawable(R.mipmap.ic_sport_item_rower);
                break;
            case 12:
                drawable = ResourceUtil.getDrawable(R.mipmap.ic_sport_item_elliptical);
                break;
            case 13:
                drawable = ResourceUtil.getDrawable(R.mipmap.ic_sport_item_cricket);
                break;
            case 14:
                drawable = ResourceUtil.getDrawable(R.mipmap.ic_sport_item_indoor_cycle);
                break;
        }
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth() / 2, drawable.getIntrinsicHeight() / 2);
        return drawable;
    }

    public void setOnChooseListener(OnChooseListener onChooseListener) {
        this.mOnChooseListener = onChooseListener;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            dismissAllowingStateLoss();
        }
    }
}