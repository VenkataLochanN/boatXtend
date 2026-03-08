package com.ido.life.module.device.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.common.dialog.CommSelectDialogFragment;
import com.ido.common.dialog.DateDialogFragment;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.util.DateUtil;
import com.ido.life.util.HealthCareUtil;

/* JADX INFO: loaded from: classes2.dex */
public class MenstrualSetActivity extends BaseActivity implements CommSelectDialogFragment.OnItemSelectedListener, DateDialogFragment.OnDateSelectedListener {
    private static final int ITEM_MENSTRUAL_LENGTH = 0;
    private static final int ITEM_PERIOD_LENGTH = 1;
    private String[] mCycleDataArray;

    @BindView(R.id.item_last_menstruation)
    CustomItemLabelView mItemLastMenstruation;

    @BindView(R.id.item_menstrual_length)
    CustomItemLabelView mItemMenstrualLength;

    @BindView(R.id.item_period_length)
    CustomItemLabelView mItemPeriodLength;
    private int[] mLastDate = new int[3];
    private Menstrual mMenstrual;
    private int mMenstrualCycleIndex;
    private int mMenstrualLengthIndex;
    private String[] mPeriodDataArray;

    @BindView(R.id.rtv_q_last_menstruation_date)
    RegularTextView mRtvQLastMenstruationDate;

    @BindView(R.id.rtv_q_menstrual_length)
    RegularTextView mRtvQMenstrualLength;

    @BindView(R.id.rtv_q_period_length)
    RegularTextView mRtvQPeriodLength;
    private int mSelected;
    private int[] mStartDate;

    @BindView(R.id.rtv_tip)
    RegularTextView mTvTips;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_menstrual_set;
    }

    @Override // com.ido.life.base.BaseActivity
    protected boolean isFunctionActivity() {
        return true;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    public void initData() {
        initTips();
        initDialogDataArray();
        initDefaultData();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$MenstrualSetActivity$GiRkIgcDO7-Xq_exMwsc8Z-eCLs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$MenstrualSetActivity(view);
            }
        });
        this.mItemMenstrualLength.setValue(this.mPeriodDataArray[this.mMenstrualLengthIndex]);
        this.mItemPeriodLength.setValue(this.mCycleDataArray[this.mMenstrualCycleIndex]);
        CustomItemLabelView customItemLabelView = this.mItemLastMenstruation;
        int[] iArr = this.mLastDate;
        customItemLabelView.setValue(DateUtil.dateFormat(iArr[0], iArr[1], iArr[2]));
    }

    public /* synthetic */ void lambda$initEvent$0$MenstrualSetActivity(View view) {
        back2PreviousPage();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_menstrual_set));
        this.mRtvQMenstrualLength.setText(getLanguageText(R.string.q_menstrual_length));
        this.mItemMenstrualLength.setTitle(getLanguageText(R.string.device_menstrual_length));
        this.mRtvQPeriodLength.setText(getLanguageText(R.string.q_period_length));
        this.mItemPeriodLength.setTitle(getLanguageText(R.string.device_period_length));
        this.mRtvQLastMenstruationDate.setText(getLanguageText(R.string.q_last_menstruation_date));
        this.mItemLastMenstruation.setTitle(getLanguageText(R.string.device_last_menstruation));
    }

    private void initDefaultData() {
        this.mMenstrual = (Menstrual) getIntent().getSerializableExtra(HealthCareUtil.MENSTRUAL);
        this.mMenstrualLengthIndex = this.mMenstrual.menstrual_length - 1;
        this.mMenstrualCycleIndex = this.mMenstrual.menstrual_cycle - 20;
        this.mLastDate[0] = this.mMenstrual.last_menstrual_year;
        this.mLastDate[1] = this.mMenstrual.last_menstrual_month;
        this.mLastDate[2] = this.mMenstrual.last_menstrual_day;
        this.mStartDate = new int[]{HealthCareUtil.DEFAULT_START_YEAR, 1, 1};
    }

    private void initDialogDataArray() {
        String languageText = getLanguageText(R.string.public_unit_day);
        this.mPeriodDataArray = HealthCareUtil.initArray(1, 14, languageText);
        this.mCycleDataArray = HealthCareUtil.initArray(20, 90, languageText);
    }

    private void initTips() {
        SpannableString spannableString = new SpannableString("[icon]" + (" " + getLanguageText(R.string.device_menstrual_set_tip)));
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_warning);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        spannableString.setSpan(new ImageSpan(drawable), 0, 6, 33);
        this.mTvTips.setText(spannableString);
    }

    private void back2PreviousPage() {
        Intent intent = new Intent();
        intent.putExtra(HealthCareUtil.MENSTRUAL, this.mMenstrual);
        setResult(100, intent);
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        back2PreviousPage();
    }

    @OnClick({R.id.item_menstrual_length, R.id.item_period_length, R.id.item_last_menstruation})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.item_last_menstruation) {
            showDatePickerDialog();
            return;
        }
        if (id == R.id.item_menstrual_length) {
            this.mSelected = 0;
            showPickerDialog(this.mPeriodDataArray, this.mMenstrualLengthIndex);
        } else {
            if (id != R.id.item_period_length) {
                return;
            }
            this.mSelected = 1;
            showPickerDialog(this.mCycleDataArray, this.mMenstrualCycleIndex);
        }
    }

    private void showDatePickerDialog() {
        this.mLastDate[0] = this.mMenstrual.last_menstrual_year;
        this.mLastDate[1] = this.mMenstrual.last_menstrual_month;
        this.mLastDate[2] = this.mMenstrual.last_menstrual_day;
        DateDialogFragment dateDialogFragmentNewInstance = DateDialogFragment.newInstance(this.mStartDate, this.mLastDate);
        dateDialogFragmentNewInstance.show(getSupportFragmentManager());
        dateDialogFragmentNewInstance.setOnDateSelectedListener(this);
    }

    private void showPickerDialog(String[] strArr, int i) {
        CommSelectDialogFragment commSelectDialogFragmentNewInstance = CommSelectDialogFragment.newInstance(strArr, i);
        commSelectDialogFragmentNewInstance.show(getSupportFragmentManager());
        commSelectDialogFragmentNewInstance.setOnItemSelectedListener(this);
    }

    @Override // com.ido.common.dialog.CommSelectDialogFragment.OnItemSelectedListener
    public void onItemSelected(int i) {
        int i2 = this.mSelected;
        if (i2 == 0) {
            this.mItemMenstrualLength.setValue(this.mPeriodDataArray[i]);
            this.mMenstrualLengthIndex = i;
            this.mMenstrual.menstrual_length = i + 1;
            return;
        }
        if (i2 != 1) {
            return;
        }
        this.mItemPeriodLength.setValue(this.mCycleDataArray[i]);
        this.mMenstrualCycleIndex = i;
        this.mMenstrual.menstrual_cycle = i + 20;
    }

    @Override // com.ido.common.dialog.DateDialogFragment.OnDateSelectedListener
    public void onDateSelected(int i, int i2, int i3) {
        Menstrual menstrual = this.mMenstrual;
        menstrual.last_menstrual_year = i;
        menstrual.last_menstrual_month = i2;
        menstrual.last_menstrual_day = i3;
        this.mItemLastMenstruation.setValue(DateUtil.dateFormat(i, i2, i3));
    }
}