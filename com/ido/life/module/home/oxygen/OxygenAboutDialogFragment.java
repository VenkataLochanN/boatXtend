package com.ido.life.module.home.oxygen;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.module.sport.bean.PracticeEffectBean;
import com.ido.smartrefresh.layoutkernel.util.SmartUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class OxygenAboutDialogFragment extends BaseDialogFragment {
    private static final String AGE = "age";
    private static final String OXYGENDATA = "oxygendata";
    private static final String POSITION = "position";
    String ageInt;

    @BindView(R.id.ll_Oxygen_progress_about)
    LinearLayout llOxygenAbout;
    private View.OnClickListener mCancelListener;

    @BindView(R.id.ib_oxygen_close)
    ImageButton mIbClose;

    @BindView(R.id.rtv_oxygen_age)
    TextView mTvage;
    ArrayList<Integer> oxygenList;
    private int position;

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_oxygen_about;
    }

    public static OxygenAboutDialogFragment newInstance(int i, String str, ArrayList<Integer> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, i);
        bundle.putString(AGE, str);
        bundle.putIntegerArrayList(OXYGENDATA, arrayList);
        OxygenAboutDialogFragment oxygenAboutDialogFragment = new OxygenAboutDialogFragment();
        oxygenAboutDialogFragment.setStyle(1, 2131886083);
        oxygenAboutDialogFragment.setArguments(bundle);
        return oxygenAboutDialogFragment;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        super.initUI(view);
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        window.getDecorView().setPadding(DipPixelUtil.dip2px(10.0f), 0, DipPixelUtil.dip2px(10.0f), DipPixelUtil.dip2px(11.0f));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = -1;
        window.setAttributes(attributes);
    }

    @Override // com.ido.common.base.BaseDialogFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(-1, -2);
        }
        this.position = getArguments().getInt(POSITION);
        this.ageInt = getArguments().getString(AGE, "20 - 29");
        this.oxygenList = getArguments().getIntegerArrayList(OXYGENDATA);
        if (TextUtils.isEmpty(this.ageInt)) {
            this.mTvage.setVisibility(8);
        } else {
            this.mTvage.setVisibility(0);
        }
        this.mTvage.setText(String.format(getString(R.string.sport_detail_train_oxygen_be_in), this.ageInt));
        initProgressView(this.position);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            dismissAllowingStateLoss();
        }
    }

    public OxygenAboutDialogFragment setCancelListener(View.OnClickListener onClickListener) {
        this.mCancelListener = onClickListener;
        return this;
    }

    @OnClick({R.id.ib_oxygen_close})
    public void doClickCancel(View view) {
        dismissAllowingStateLoss();
        View.OnClickListener onClickListener = this.mCancelListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    private List<PracticeEffectBean> getOxygenProgessPropertys() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PracticeEffectBean(LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_one), "#E64C8B", "26 - 37"));
        arrayList.add(new PracticeEffectBean(LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_two), "#9067F2", "38 - 41"));
        arrayList.add(new PracticeEffectBean(LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_three), "#598EFF", "42 - 45"));
        arrayList.add(new PracticeEffectBean(LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_four), "#01B3FE", "46 - 49"));
        arrayList.add(new PracticeEffectBean(LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_five), "#01CEFE", "50 - 54"));
        arrayList.add(new PracticeEffectBean(LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_six), "#00F2FF", "55 - 60"));
        return arrayList;
    }

    private void initProgressView(int i) {
        this.llOxygenAbout.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
        List<PracticeEffectBean> oxygenProgessPropertys = getOxygenProgessPropertys();
        int i2 = 0;
        while (i2 < oxygenProgessPropertys.size()) {
            PracticeEffectBean practiceEffectBean = getOxygenProgessPropertys().get(i2);
            View viewInflate = getLayoutInflater().inflate(R.layout.item_practice_dialog, (ViewGroup) null);
            viewInflate.setLayoutParams(layoutParams);
            TextView textView = (TextView) viewInflate.findViewById(R.id.rtv_practice_title);
            textView.setTextColor(Color.parseColor(practiceEffectBean.getColor()));
            ImageView imageView = (ImageView) viewInflate.findViewById(R.id.iv_practice_bg);
            RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.practice_top_ll);
            TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_pactice_progress);
            StringBuilder sb = new StringBuilder();
            sb.append(this.oxygenList.get(i2));
            sb.append(" - ");
            int i3 = i2 + 1;
            sb.append(this.oxygenList.get(i3));
            textView2.setText(sb.toString());
            textView.setText(practiceEffectBean.getName());
            relativeLayout.setVisibility(0);
            setProgressColor(oxygenProgessPropertys, i2, textView2, practiceEffectBean.getColor(), i);
            if (i == i2) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) textView.getLayoutParams();
                layoutParams2.addRule(12, -1);
                textView.setLayoutParams(layoutParams2);
            }
            this.llOxygenAbout.addView(viewInflate);
            i2 = i3;
        }
    }

    private void setProgressColor(List<PracticeEffectBean> list, int i, TextView textView, String str, int i2) {
        if (i == 0) {
            setDrableCircle(textView, true, str);
        } else if (i == list.size() - 1) {
            setDrableCircle(textView, false, str);
        } else {
            textView.setBackgroundColor(Color.parseColor(str));
        }
    }

    private void setDrableCircle(TextView textView, boolean z, String str) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(Color.parseColor(str));
        gradientDrawable.setCornerRadii(z ? new float[]{SmartUtil.dp2px(13.0f), SmartUtil.dp2px(13.0f), 0.0f, 0.0f, 0.0f, 0.0f, SmartUtil.dp2px(13.0f), SmartUtil.dp2px(13.0f)} : new float[]{0.0f, 0.0f, SmartUtil.dp2px(13.0f), SmartUtil.dp2px(13.0f), SmartUtil.dp2px(13.0f), SmartUtil.dp2px(13.0f), 0.0f, 0.0f});
        textView.setBackground(gradientDrawable);
    }
}