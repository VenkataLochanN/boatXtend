package com.ido.life.module.home.menstrualcycle.guide;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.base.BaseClickActivity;
import com.ido.life.constants.Constants;
import com.ido.life.customview.DateSelectWheelView;
import com.ido.life.module.home.menstrualcycle.MenstrualCycleDetailActivity;
import com.ido.life.util.DateUtil;
import com.ido.life.util.systembar.ImmersionBar;
import com.watch.life.wheelview.adapter.ArrayWheelAdapter;
import com.watch.life.wheelview.view.WheelView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MenstrualCycleGuideActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\tH\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0007H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0007H\u0002J\b\u0010\u0015\u001a\u00020\u0007H\u0002J\b\u0010\u0016\u001a\u00020\u000eH\u0002J\b\u0010\u0017\u001a\u00020\u000eH\u0016J\b\u0010\u0018\u001a\u00020\u000eH\u0002J\b\u0010\u0019\u001a\u00020\u000eH\u0002J\b\u0010\u001a\u001a\u00020\u000eH\u0014J\b\u0010\u001b\u001a\u00020\u000eH\u0014J\b\u0010\u001c\u001a\u00020\u000eH\u0016J\b\u0010\u001d\u001a\u00020\u000eH\u0016J\b\u0010\u001e\u001a\u00020\u000eH\u0016J\b\u0010\u001f\u001a\u00020\u000eH\u0002J\b\u0010 \u001a\u00020\u000eH\u0016J\b\u0010!\u001a\u00020\u000eH\u0002J\b\u0010\"\u001a\u00020\u000eH\u0002J\b\u0010#\u001a\u00020\u000eH\u0014R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/guide/MenstrualCycleGuideActivity;", "Lcom/ido/life/base/BaseClickActivity;", "Lcom/ido/life/module/home/menstrualcycle/guide/MenstrualCycleGuidePresenter;", "Landroid/view/View$OnClickListener;", "Lcom/ido/life/module/home/menstrualcycle/guide/IMenstrualCycleGuideView;", "()V", "mDateSelectorDialog", "Landroidx/appcompat/app/AlertDialog;", "mFromHome", "", "mMensCycleDialog", "mMensLengthDialog", "checkSetComplete", "clickAction", "", "view", "Landroid/view/View;", "getDateSelectorDialog", "getLayoutResId", "", "getMensCycleSettingDialog", "getMensLengthSettingDialog", "hideDateSelectorDialog", "hideLoading", "hideMensCycleDialog", "hideMensLengthDialog", "initData", "initLabelLanguage", "initViews", "saveFailed", "saveSuccess", "showDateSelectorDialog", "showLoading", "showMensCycleSettingDialog", "showMensLengthSettingDialog", "viewCreate", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MenstrualCycleGuideActivity extends BaseClickActivity<MenstrualCycleGuidePresenter> implements View.OnClickListener, IMenstrualCycleGuideView {
    private HashMap _$_findViewCache;
    private AlertDialog mDateSelectorDialog;
    private boolean mFromHome = true;
    private AlertDialog mMensCycleDialog;
    private AlertDialog mMensLengthDialog;

    @Override // com.ido.life.base.BaseClickActivity
    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.ido.life.base.BaseClickActivity
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_menstrulation_cycle_guide;
    }

    @Override // com.ido.life.module.home.menstrualcycle.guide.IMenstrualCycleGuideView
    public void saveFailed() {
    }

    public static final /* synthetic */ MenstrualCycleGuidePresenter access$getMPresenter$p(MenstrualCycleGuideActivity menstrualCycleGuideActivity) {
        return (MenstrualCycleGuidePresenter) menstrualCycleGuideActivity.mPresenter;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void viewCreate() {
        super.viewCreate();
        ImmersionBar.with(this).navigationBarEnable(false).titleBarMarginTop(R.id.layout_parent).statusBarDarkFont(true).init();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_mens_title);
        if (textView != null) {
            textView.setText(getLanguageText(R.string.title_mens_track));
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
        if (textView2 != null) {
            textView2.setText(getLanguageText(R.string.mens_guide_page_desc));
        }
        TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_mens_length);
        if (textView3 != null) {
            textView3.setText(getLanguageText(R.string.device_menstrual_length));
        }
        TextView textView4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_mens_length_setting);
        if (textView4 != null) {
            textView4.setText(getLanguageText(R.string.mine_set));
        }
        TextView textView5 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_mens_cycle);
        if (textView5 != null) {
            textView5.setText(getLanguageText(R.string.device_period_length));
        }
        TextView textView6 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_mens_cycle_setting);
        if (textView6 != null) {
            textView6.setText(getLanguageText(R.string.mine_set));
        }
        TextView textView7 = (TextView) _$_findCachedViewById(com.ido.life.R.id.btn_start_use);
        if (textView7 != null) {
            textView7.setText(getLanguageText(R.string.login_start_use));
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mFromHome = getIntent().getBooleanExtra(Constants.INTENT_DATA_KEY, this.mFromHome);
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_parent);
        if (linearLayout != null) {
            linearLayout.setBackground((Drawable) null);
        }
    }

    @Override // com.ido.life.base.BaseClickActivity
    public void clickAction(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        switch (view.getId()) {
            case R.id.btn_start_use /* 2131361931 */:
                ((MenstrualCycleGuidePresenter) this.mPresenter).saveSetting();
                break;
            case R.id.lay_mens_cycle_setting /* 2131362716 */:
                showMensCycleSettingDialog();
                break;
            case R.id.lay_mens_length_setting /* 2131362718 */:
                showMensLengthSettingDialog();
                break;
            case R.id.ll_last_time_setting /* 2131362949 */:
                showDateSelectorDialog();
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideMensLengthDialog() {
        AlertDialog alertDialog = this.mMensLengthDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideMensCycleDialog() {
        AlertDialog alertDialog = this.mMensCycleDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideDateSelectorDialog() {
        AlertDialog alertDialog = this.mDateSelectorDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    private final void showMensCycleSettingDialog() {
        AlertDialog alertDialog = this.mMensCycleDialog;
        if (alertDialog != null) {
            if (alertDialog == null) {
                Intrinsics.throwNpe();
            }
            if (alertDialog.isShowing()) {
                return;
            }
        }
        if (this.mMensCycleDialog == null) {
            this.mMensCycleDialog = getMensCycleSettingDialog();
        }
        AlertDialog alertDialog2 = this.mMensCycleDialog;
        if (alertDialog2 == null) {
            Intrinsics.throwNpe();
        }
        alertDialog2.show();
    }

    private final void showMensLengthSettingDialog() {
        AlertDialog alertDialog = this.mMensLengthDialog;
        if (alertDialog != null) {
            if (alertDialog == null) {
                Intrinsics.throwNpe();
            }
            if (alertDialog.isShowing()) {
                return;
            }
        }
        if (this.mMensLengthDialog == null) {
            this.mMensLengthDialog = getMensLengthSettingDialog();
        }
        AlertDialog alertDialog2 = this.mMensLengthDialog;
        if (alertDialog2 == null) {
            Intrinsics.throwNpe();
        }
        alertDialog2.show();
    }

    private final void showDateSelectorDialog() {
        AlertDialog alertDialog = this.mDateSelectorDialog;
        if (alertDialog != null) {
            if (alertDialog == null) {
                Intrinsics.throwNpe();
            }
            if (alertDialog.isShowing()) {
                return;
            }
        }
        if (this.mDateSelectorDialog == null) {
            this.mDateSelectorDialog = getDateSelectorDialog();
        }
        AlertDialog alertDialog2 = this.mDateSelectorDialog;
        if (alertDialog2 == null) {
            Intrinsics.throwNpe();
        }
        alertDialog2.show();
    }

    private final AlertDialog getMensLengthSettingDialog() {
        MenstrualCycleGuideActivity menstrualCycleGuideActivity = this;
        View viewInflate = LayoutInflater.from(menstrualCycleGuideActivity).inflate(R.layout.dialog_mens_setting_layout, (ViewGroup) null);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(menstrualCycleGuideActivity, R.style.dialog_translate).setView(viewInflate).create();
        Intrinsics.checkExpressionValueIsNotNull(alertDialogCreate, "AlertDialog.Builder(this…e).setView(view).create()");
        alertDialogCreate.setCanceledOnTouchOutside(true);
        alertDialogCreate.setCancelable(true);
        Window window = alertDialogCreate.getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        window.setGravity(80);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sw_dp_8);
        Window window2 = alertDialogCreate.getWindow();
        if (window2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(window2, "dialog.window!!");
        window2.getDecorView().setPadding(dimensionPixelSize, 0, dimensionPixelSize, dimensionPixelSize);
        Window window3 = alertDialogCreate.getWindow();
        if (window3 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(window3, "dialog.window!!");
        WindowManager.LayoutParams attributes = window3.getAttributes();
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        attributes.width = resources.getDisplayMetrics().widthPixels;
        TextView tv_title = (TextView) viewInflate.findViewById(R.id.tv_title);
        TextView tv_desc = (TextView) viewInflate.findViewById(R.id.tv_desc);
        TextView tv_confirm = (TextView) viewInflate.findViewById(R.id.tv_confirm);
        final WheelView wheel_view = (WheelView) viewInflate.findViewById(R.id.wheel_view);
        Intrinsics.checkExpressionValueIsNotNull(tv_title, "tv_title");
        tv_title.setText(LanguageUtil.getLanguageText(R.string.device_menstrual_length));
        Intrinsics.checkExpressionValueIsNotNull(tv_desc, "tv_desc");
        tv_desc.setText(LanguageUtil.getLanguageText(R.string.mens_length_desc));
        Intrinsics.checkExpressionValueIsNotNull(tv_confirm, "tv_confirm");
        tv_confirm.setText(LanguageUtil.getLanguageText(R.string.public_tip_confirm));
        wheel_view.setCyclic(false);
        wheel_view.setItemsVisibleCount(5);
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i <= 14; i++) {
            arrayList.add(String.valueOf(i));
        }
        Intrinsics.checkExpressionValueIsNotNull(wheel_view, "wheel_view");
        wheel_view.setAdapter(new ArrayWheelAdapter(arrayList));
        int mMensLength = ((MenstrualCycleGuidePresenter) this.mPresenter).getMMensLength();
        wheel_view.setCurrentItem((1 <= mMensLength && 14 >= mMensLength) ? ((MenstrualCycleGuidePresenter) this.mPresenter).getMMensLength() - 1 : 6);
        tv_confirm.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.home.menstrualcycle.guide.MenstrualCycleGuideActivity.getMensLengthSettingDialog.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MenstrualCycleGuidePresenter menstrualCycleGuidePresenterAccess$getMPresenter$p = MenstrualCycleGuideActivity.access$getMPresenter$p(MenstrualCycleGuideActivity.this);
                WheelView wheel_view2 = wheel_view;
                Intrinsics.checkExpressionValueIsNotNull(wheel_view2, "wheel_view");
                menstrualCycleGuidePresenterAccess$getMPresenter$p.setMMensLength(wheel_view2.getCurrentItem() + 1);
                MenstrualCycleGuideActivity.this.hideMensLengthDialog();
                TextView tv_mens_length_setting = (TextView) MenstrualCycleGuideActivity.this._$_findCachedViewById(com.ido.life.R.id.tv_mens_length_setting);
                Intrinsics.checkExpressionValueIsNotNull(tv_mens_length_setting, "tv_mens_length_setting");
                tv_mens_length_setting.setText(MenstrualCycleGuideActivity.access$getMPresenter$p(MenstrualCycleGuideActivity.this).getMMensLength() + MenstrualCycleGuideActivity.this.getLanguageText(R.string.public_unit_day));
                if (MenstrualCycleGuideActivity.this.checkSetComplete()) {
                    TextView btn_start_use = (TextView) MenstrualCycleGuideActivity.this._$_findCachedViewById(com.ido.life.R.id.btn_start_use);
                    Intrinsics.checkExpressionValueIsNotNull(btn_start_use, "btn_start_use");
                    btn_start_use.setEnabled(true);
                    ((TextView) MenstrualCycleGuideActivity.this._$_findCachedViewById(com.ido.life.R.id.btn_start_use)).setOnClickListener(MenstrualCycleGuideActivity.this);
                }
            }
        });
        return alertDialogCreate;
    }

    private final AlertDialog getMensCycleSettingDialog() {
        MenstrualCycleGuideActivity menstrualCycleGuideActivity = this;
        View viewInflate = LayoutInflater.from(menstrualCycleGuideActivity).inflate(R.layout.dialog_mens_setting_layout, (ViewGroup) null);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(menstrualCycleGuideActivity, R.style.dialog_translate).setView(viewInflate).create();
        Intrinsics.checkExpressionValueIsNotNull(alertDialogCreate, "AlertDialog.Builder(this…e).setView(view).create()");
        alertDialogCreate.setCanceledOnTouchOutside(true);
        alertDialogCreate.setCancelable(true);
        Window window = alertDialogCreate.getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        window.setGravity(80);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sw_dp_8);
        Window window2 = alertDialogCreate.getWindow();
        if (window2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(window2, "dialog.window!!");
        window2.getDecorView().setPadding(dimensionPixelSize, 0, dimensionPixelSize, dimensionPixelSize);
        Window window3 = alertDialogCreate.getWindow();
        if (window3 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(window3, "dialog.window!!");
        WindowManager.LayoutParams attributes = window3.getAttributes();
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        attributes.width = resources.getDisplayMetrics().widthPixels;
        TextView tv_title = (TextView) viewInflate.findViewById(R.id.tv_title);
        TextView tv_desc = (TextView) viewInflate.findViewById(R.id.tv_desc);
        TextView tv_confirm = (TextView) viewInflate.findViewById(R.id.tv_confirm);
        final WheelView wheel_view = (WheelView) viewInflate.findViewById(R.id.wheel_view);
        Intrinsics.checkExpressionValueIsNotNull(tv_title, "tv_title");
        tv_title.setText(LanguageUtil.getLanguageText(R.string.device_period_length));
        Intrinsics.checkExpressionValueIsNotNull(tv_desc, "tv_desc");
        tv_desc.setText(LanguageUtil.getLanguageText(R.string.mens_cycle_desc));
        Intrinsics.checkExpressionValueIsNotNull(tv_confirm, "tv_confirm");
        tv_confirm.setText(LanguageUtil.getLanguageText(R.string.public_tip_confirm));
        wheel_view.setCyclic(false);
        wheel_view.setItemsVisibleCount(5);
        ArrayList arrayList = new ArrayList();
        for (int i = 20; i <= 90; i++) {
            arrayList.add(String.valueOf(i));
        }
        Intrinsics.checkExpressionValueIsNotNull(wheel_view, "wheel_view");
        wheel_view.setAdapter(new ArrayWheelAdapter(arrayList));
        int mMensCycle = ((MenstrualCycleGuidePresenter) this.mPresenter).getMMensCycle();
        wheel_view.setCurrentItem((20 <= mMensCycle && 90 >= mMensCycle) ? ((MenstrualCycleGuidePresenter) this.mPresenter).getMMensCycle() - 20 : 8);
        tv_confirm.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.home.menstrualcycle.guide.MenstrualCycleGuideActivity.getMensCycleSettingDialog.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MenstrualCycleGuidePresenter menstrualCycleGuidePresenterAccess$getMPresenter$p = MenstrualCycleGuideActivity.access$getMPresenter$p(MenstrualCycleGuideActivity.this);
                WheelView wheel_view2 = wheel_view;
                Intrinsics.checkExpressionValueIsNotNull(wheel_view2, "wheel_view");
                menstrualCycleGuidePresenterAccess$getMPresenter$p.setMMensCycle(wheel_view2.getCurrentItem() + 20);
                MenstrualCycleGuideActivity.this.hideMensCycleDialog();
                TextView tv_mens_cycle_setting = (TextView) MenstrualCycleGuideActivity.this._$_findCachedViewById(com.ido.life.R.id.tv_mens_cycle_setting);
                Intrinsics.checkExpressionValueIsNotNull(tv_mens_cycle_setting, "tv_mens_cycle_setting");
                tv_mens_cycle_setting.setText(MenstrualCycleGuideActivity.access$getMPresenter$p(MenstrualCycleGuideActivity.this).getMMensCycle() + MenstrualCycleGuideActivity.this.getLanguageText(R.string.public_unit_day));
                if (MenstrualCycleGuideActivity.this.checkSetComplete()) {
                    TextView btn_start_use = (TextView) MenstrualCycleGuideActivity.this._$_findCachedViewById(com.ido.life.R.id.btn_start_use);
                    Intrinsics.checkExpressionValueIsNotNull(btn_start_use, "btn_start_use");
                    btn_start_use.setEnabled(true);
                    ((TextView) MenstrualCycleGuideActivity.this._$_findCachedViewById(com.ido.life.R.id.btn_start_use)).setOnClickListener(MenstrualCycleGuideActivity.this);
                }
            }
        });
        return alertDialogCreate;
    }

    private final AlertDialog getDateSelectorDialog() {
        MenstrualCycleGuideActivity menstrualCycleGuideActivity = this;
        View viewInflate = LayoutInflater.from(menstrualCycleGuideActivity).inflate(R.layout.dialog_mens_date_setting_layout, (ViewGroup) null);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(menstrualCycleGuideActivity, R.style.dialog_translate).setView(viewInflate).create();
        Intrinsics.checkExpressionValueIsNotNull(alertDialogCreate, "AlertDialog.Builder(this…e).setView(view).create()");
        alertDialogCreate.setCanceledOnTouchOutside(true);
        alertDialogCreate.setCancelable(true);
        Window window = alertDialogCreate.getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        window.setGravity(80);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sw_dp_8);
        Window window2 = alertDialogCreate.getWindow();
        if (window2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(window2, "dialog.window!!");
        window2.getDecorView().setPadding(dimensionPixelSize, 0, dimensionPixelSize, dimensionPixelSize);
        Window window3 = alertDialogCreate.getWindow();
        if (window3 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(window3, "dialog.window!!");
        WindowManager.LayoutParams attributes = window3.getAttributes();
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        attributes.width = resources.getDisplayMetrics().widthPixels;
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_confirm);
        final DateSelectWheelView dateSelectWheelView = (DateSelectWheelView) viewInflate.findViewById(R.id.date_selector);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.home.menstrualcycle.guide.MenstrualCycleGuideActivity.getDateSelectorDialog.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                Object[] objArr = {Integer.valueOf(dateSelectWheelView.getMSelectedDay()), Integer.valueOf(dateSelectWheelView.getMSelectedMonth()), Integer.valueOf(dateSelectWheelView.getMSelectedYear())};
                String str = String.format("%02d/%02d/%02d", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                calendar.setTime(DateUtil.string2Date(str, DateUtil.DATE_FORMAT_DMY_1));
                if (calendar.getTimeInMillis() > System.currentTimeMillis()) {
                    MenstrualCycleGuideActivity.this.showToast(R.string.login_target_set_wrong);
                    return;
                }
                MenstrualCycleGuideActivity.access$getMPresenter$p(MenstrualCycleGuideActivity.this).setMCycleTime(calendar.getTimeInMillis());
                TextView tv_last_time_setting = (TextView) MenstrualCycleGuideActivity.this._$_findCachedViewById(com.ido.life.R.id.tv_last_time_setting);
                Intrinsics.checkExpressionValueIsNotNull(tv_last_time_setting, "tv_last_time_setting");
                tv_last_time_setting.setText(str);
                if (MenstrualCycleGuideActivity.this.checkSetComplete()) {
                    TextView btn_start_use = (TextView) MenstrualCycleGuideActivity.this._$_findCachedViewById(com.ido.life.R.id.btn_start_use);
                    Intrinsics.checkExpressionValueIsNotNull(btn_start_use, "btn_start_use");
                    btn_start_use.setEnabled(true);
                    ((TextView) MenstrualCycleGuideActivity.this._$_findCachedViewById(com.ido.life.R.id.btn_start_use)).setOnClickListener(MenstrualCycleGuideActivity.this);
                }
                MenstrualCycleGuideActivity.this.hideDateSelectorDialog();
            }
        });
        return alertDialogCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkSetComplete() {
        int mMensCycle;
        int mMensLength = ((MenstrualCycleGuidePresenter) this.mPresenter).getMMensLength();
        return 1 <= mMensLength && 14 >= mMensLength && 20 <= (mMensCycle = ((MenstrualCycleGuidePresenter) this.mPresenter).getMMensCycle()) && 90 >= mMensCycle && ((MenstrualCycleGuidePresenter) this.mPresenter).getMCycleTime() != -1;
    }

    @Override // com.ido.life.module.home.menstrualcycle.guide.IMenstrualCycleGuideView
    public void saveSuccess() {
        if (this.mFromHome) {
            startActivity(new Intent(this, (Class<?>) MenstrualCycleDetailActivity.class));
        } else {
            setResult(-1);
        }
        supportFinishAfterTransition();
    }

    @Override // com.ido.life.module.home.menstrualcycle.guide.IMenstrualCycleGuideView
    public void showLoading() {
        showLoadingDialog();
    }

    @Override // com.ido.life.module.home.menstrualcycle.guide.IMenstrualCycleGuideView
    public void hideLoading() {
        dismissLoadingDialog();
    }
}