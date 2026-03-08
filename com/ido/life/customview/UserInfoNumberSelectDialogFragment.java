package com.ido.life.customview;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.widget.view.UserInfoPickerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class UserInfoNumberSelectDialogFragment extends BaseDialogFragment {
    private static final String CHOOSE = "choose";
    private static final String TYPE = "type";
    public static final int TYPE_HEIGHT_EM = 2;
    public static final int TYPE_HEIGHT_KM = 1;
    public static final int TYPE_WEIGHT_EM = 4;
    public static final int TYPE_WEIGHT_KM = 3;

    @BindView(R.id.custom_picker)
    UserInfoPickerView mCustomPickerView;
    private List<String> mLeftList;
    private OnItemSelectedListener mOnItemSelectedListener;
    private List<String> mRightList;

    @BindView(R.id.tv_cancel)
    TextView mTvCancel;

    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;
    private int mType;

    public interface OnItemSelectedListener {
        void onItemSelected(int i, String str);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_pick2;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getWindowAnimations() {
        return R.style.DialogAnimSlideInBottom;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }

    public static UserInfoNumberSelectDialogFragment newInstance(int i, String str) {
        UserInfoNumberSelectDialogFragment userInfoNumberSelectDialogFragment = new UserInfoNumberSelectDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        bundle.putString(CHOOSE, str);
        userInfoNumberSelectDialogFragment.setArguments(bundle);
        userInfoNumberSelectDialogFragment.setStyle(1, 2131886083);
        return userInfoNumberSelectDialogFragment;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        super.initUI(view);
        if (getDialog() != null && getDialog().getWindow() != null) {
            Window window = getDialog().getWindow();
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 80;
            attributes.width = -1;
            window.setSoftInputMode(18);
            window.setAttributes(attributes);
        }
        int color = ResourceUtil.getColor(R.color.color_E51C23);
        int color2 = ResourceUtil.getColor(R.color.white);
        this.mTvCancel.setTextColor(color);
        this.mTvConfirm.setTextColor(color);
        this.mCustomPickerView.setCenterTextColor(color);
        this.mCustomPickerView.setUnitTextColor(color);
        this.mCustomPickerView.setLeftWeelCenterTextColor(color);
        this.mCustomPickerView.setRightWheelCenterTextColor(color);
        this.mCustomPickerView.setLeftWeelOutTextColor(color2);
        this.mCustomPickerView.setRightWheelOutTextColor(color2);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        this.mType = arguments.getInt("type");
        String string = arguments.getString(CHOOSE);
        this.mLeftList = new ArrayList();
        this.mRightList = new ArrayList();
        int i = this.mType;
        if (i == 1) {
            for (int i2 = 50; i2 <= 250; i2++) {
                this.mLeftList.add(String.valueOf(i2));
            }
            this.mCustomPickerView.setViewStyle(1);
            this.mCustomPickerView.setUnitText(LanguageUtil.getLanguageText(R.string.sport_detail_stride_avg_unit));
            this.mCustomPickerView.setLeftData(this.mLeftList);
            this.mCustomPickerView.setLeftSelect(string);
            return;
        }
        if (i == 2) {
            for (float f2 = 1.64f; f2 < 8.21f; f2 += 0.01f) {
                this.mLeftList.add(String.format("%.2f", Float.valueOf(f2)));
            }
            this.mCustomPickerView.setViewStyle(1);
            this.mCustomPickerView.setUnitText(LanguageUtil.getLanguageText(R.string.public_unit_feet));
            this.mCustomPickerView.setLeftData(this.mLeftList);
            this.mCustomPickerView.setLeftSelect(string);
            return;
        }
        if (i != 3) {
            if (i != 4) {
                return;
            }
            for (int i3 = 22; i3 <= 551; i3++) {
                this.mLeftList.add(String.valueOf(i3));
            }
            this.mCustomPickerView.setViewStyle(1);
            this.mCustomPickerView.setUnitText(LanguageUtil.getLanguageText(R.string.public_unit_pound));
            this.mCustomPickerView.setLeftData(this.mLeftList);
            this.mCustomPickerView.setLeftSelect(string);
            return;
        }
        for (int i4 = 10; i4 <= 250; i4++) {
            this.mLeftList.add(String.valueOf(i4));
        }
        for (int i5 = 0; i5 < 10; i5++) {
            this.mRightList.add(String.valueOf(i5));
        }
        this.mCustomPickerView.setViewStyle(2);
        this.mCustomPickerView.setUnitText(LanguageUtil.getLanguageText(R.string.public_unit_kg));
        this.mCustomPickerView.setLeftData(this.mLeftList);
        this.mCustomPickerView.setRightData(this.mRightList);
        try {
            float f3 = Float.parseFloat(string);
            this.mCustomPickerView.setLeftSelect(String.valueOf((int) f3));
            this.mCustomPickerView.setRightSelect(String.valueOf((int) ((f3 * 10.0f) - (r1 * 10))));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @OnClick({R.id.tv_cancel})
    public void toCancel(View view) {
        dismissAllowingStateLoss();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0041  */
    @butterknife.OnClick({com.boat.Xtend.two.R.id.tv_confirm})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void toConfirm(android.view.View r5) {
        /*
            r4 = this;
            com.ido.life.customview.UserInfoNumberSelectDialogFragment$OnItemSelectedListener r5 = r4.mOnItemSelectedListener
            if (r5 == 0) goto L4e
            int r5 = r4.mType
            r0 = 1
            if (r5 == r0) goto L41
            r0 = 2
            if (r5 == r0) goto L41
            r0 = 3
            if (r5 == r0) goto L13
            r0 = 4
            if (r5 == r0) goto L41
            goto L4e
        L13:
            com.ido.common.widget.view.UserInfoPickerView r5 = r4.mCustomPickerView
            java.lang.String r5 = r5.getLeftSelectData()
            com.ido.common.widget.view.UserInfoPickerView r0 = r4.mCustomPickerView
            java.lang.String r0 = r0.getRightSelectData()
            com.ido.life.customview.UserInfoNumberSelectDialogFragment$OnItemSelectedListener r1 = r4.mOnItemSelectedListener     // Catch: java.lang.Exception -> L3c
            int r2 = r4.mType     // Catch: java.lang.Exception -> L3c
            int r5 = java.lang.Integer.parseInt(r5)     // Catch: java.lang.Exception -> L3c
            float r5 = (float) r5     // Catch: java.lang.Exception -> L3c
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Exception -> L3c
            float r0 = (float) r0     // Catch: java.lang.Exception -> L3c
            r3 = 1065353216(0x3f800000, float:1.0)
            float r0 = r0 * r3
            r3 = 1092616192(0x41200000, float:10.0)
            float r0 = r0 / r3
            float r5 = r5 + r0
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch: java.lang.Exception -> L3c
            r1.onItemSelected(r2, r5)     // Catch: java.lang.Exception -> L3c
            goto L4e
        L3c:
            r5 = move-exception
            r5.printStackTrace()
            goto L4e
        L41:
            com.ido.life.customview.UserInfoNumberSelectDialogFragment$OnItemSelectedListener r5 = r4.mOnItemSelectedListener
            int r0 = r4.mType
            com.ido.common.widget.view.UserInfoPickerView r1 = r4.mCustomPickerView
            java.lang.String r1 = r1.getLeftSelectData()
            r5.onItemSelected(r0, r1)
        L4e:
            r4.dismissAllowingStateLoss()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.UserInfoNumberSelectDialogFragment.toConfirm(android.view.View):void");
    }
}