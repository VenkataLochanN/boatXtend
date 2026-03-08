package com.ido.life.module.home.menstrualcycle;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.OtherProtocolCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.base.BaseClickActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.customview.MenstruationCalendar;
import com.ido.life.module.device.activity.MenstrualCycleSettingActivity;
import com.ido.life.module.home.menstrualcycle.MenstrualCycleDetailPresenter;
import com.ido.life.module.home.menstrualcycle.dialog.MenstrualCycleSaveDataCallback;
import com.ido.life.module.home.menstrualcycle.dialog.MenstruationSettingDialog;
import com.ido.life.module.home.menstrualcycle.dialog.MenstrulationSettingPresenter;
import com.ido.life.util.DateUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MenstrualCycleDetailActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0001?B\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0016J\b\u0010\u0015\u001a\u00020\u0011H\u0016J\b\u0010\u0016\u001a\u00020\u0011H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0014J\u001e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u0018H\u0016J\b\u0010\u001e\u001a\u00020\u0018H\u0016J\u0016\u0010\u001f\u001a\u00020\u00112\f\u0010 \u001a\b\u0012\u0002\b\u0003\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\u0011H\u0014J\b\u0010#\u001a\u00020\u0011H\u0014J\b\u0010$\u001a\u00020\u0011H\u0016J\u0010\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u0018H\u0016J\u0010\u0010'\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u0018H\u0016J\b\u0010(\u001a\u00020\u0011H\u0014J\u0016\u0010)\u001a\u00020\u00112\f\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+H\u0016J\b\u0010-\u001a\u00020\u0011H\u0014J\b\u0010.\u001a\u00020\u0011H\u0016J\u0016\u0010/\u001a\u00020\u00112\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\u001aH\u0016J\b\u00102\u001a\u00020\u0011H\u0002J\b\u00103\u001a\u00020\u0011H\u0016J\b\u00104\u001a\u00020\u0011H\u0016J\b\u00105\u001a\u00020\u0011H\u0016J\b\u00106\u001a\u00020\u0011H\u0016J\u0010\u00107\u001a\u00020\u00112\u0006\u00108\u001a\u00020\rH\u0016J\u001a\u00109\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\u00182\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\u0010\u0010=\u001a\u00020\u00112\u0006\u0010>\u001a\u00020\u0018H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailActivity;", "Lcom/ido/life/base/BaseClickActivity;", "Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailPresenter;", "Lcom/ido/life/module/home/menstrualcycle/IMenstrualCycleDetailView;", "Landroid/view/View$OnClickListener;", "Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailCallback;", "Lcom/ido/life/module/home/menstrualcycle/dialog/MenstrualCycleSaveDataCallback;", "()V", "mChartAdapter", "Lcom/ido/life/module/home/menstrualcycle/MenstrualChartAdapter;", "mHistoryLoadingAnimator", "Landroid/animation/ValueAnimator;", "mNeedRefresh", "", "mRelateAnimator", "mScrolled", "clickAction", "", "view", "Landroid/view/View;", "dataLoadFailed", "dataLoadSuccess", "dataLoading", "getLayoutResId", "", "getMenstruationDateList", "", "Lcom/ido/life/customview/MenstruationCalendar$DateInfo;", "year", "month", "getWeekStart", "handleMessage", "message", "Lcom/ido/life/base/BaseMessage;", "initData", "initLabelLanguage", "initViews", "menstruationAvgLength", "length", "menstruationCycleAvg", "onDestroy", "onGetDataSuccess", "dataList", "", "Lcom/ido/life/module/home/menstrualcycle/dialog/MenstrulationSettingPresenter$SettingBean;", "onStart", "saveDataSuccess", "setHistoryMenstruation", "historyList", "Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailPresenter$HistoryMenstruationItemBean;", "showSyncDialog", "startSyncToDevice", "syncToDeviceDisconnect", "syncToDeviceFailed", "syncToDeviceSuccess", "updateDeviceConnectState", "connected", "updateMensDesc", "color", "desc", "Landroid/text/SpannableStringBuilder;", "updateMonthOffest", "offset", "MensRecyclerScrollListener", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MenstrualCycleDetailActivity extends BaseClickActivity<MenstrualCycleDetailPresenter> implements IMenstrualCycleDetailView, View.OnClickListener, MenstrualCycleDetailCallback, MenstrualCycleSaveDataCallback {
    private HashMap _$_findViewCache;
    private MenstrualChartAdapter mChartAdapter;
    private ValueAnimator mHistoryLoadingAnimator;
    private boolean mNeedRefresh;
    private ValueAnimator mRelateAnimator;
    private boolean mScrolled;

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
        return R.layout.activity_menstrual_cycle_detail_layout;
    }

    public static final /* synthetic */ MenstrualCycleDetailPresenter access$getMPresenter$p(MenstrualCycleDetailActivity menstrualCycleDetailActivity) {
        return (MenstrualCycleDetailPresenter) menstrualCycleDetailActivity.mPresenter;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        ((MenstrualCycleDetailPresenter) this.mPresenter).startLoadData();
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mTitleBar.initLayout(1);
        this.mTitleBar.setRightImg(R.mipmap.icon_setting);
        this.mTitleBar.setRightOnClick(this);
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_guide)).measure(0, 0);
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.img_state)).measure(0, 0);
        TextView tv_guide = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_guide);
        Intrinsics.checkExpressionValueIsNotNull(tv_guide, "tv_guide");
        int measuredHeight = tv_guide.getMeasuredHeight();
        ImageView img_state = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_state);
        Intrinsics.checkExpressionValueIsNotNull(img_state, "img_state");
        if (measuredHeight > img_state.getMeasuredHeight()) {
            TextView tv_guide2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_guide);
            Intrinsics.checkExpressionValueIsNotNull(tv_guide2, "tv_guide");
            int measuredHeight2 = tv_guide2.getMeasuredHeight();
            ImageView img_state2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_state);
            Intrinsics.checkExpressionValueIsNotNull(img_state2, "img_state");
            int measuredHeight3 = (measuredHeight2 - img_state2.getMeasuredHeight()) / 2;
            ImageView img_state3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_state);
            Intrinsics.checkExpressionValueIsNotNull(img_state3, "img_state");
            ViewGroup.LayoutParams layoutParams = img_state3.getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            }
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.bottomMargin = measuredHeight3;
            ImageView img_state4 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_state);
            Intrinsics.checkExpressionValueIsNotNull(img_state4, "img_state");
            img_state4.setLayoutParams(layoutParams2);
        }
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (this.mNeedRefresh) {
            this.mNeedRefresh = false;
            ((MenstrualCycleDetailPresenter) this.mPresenter).startLoadData();
        }
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        this.mTitleBar.setTitle(R.string.title_mens_track);
        TextView tv_title_chart_menstrulation_day = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_chart_menstrulation_day);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_chart_menstrulation_day, "tv_title_chart_menstrulation_day");
        tv_title_chart_menstrulation_day.setText(getLanguageText(R.string.title_menstrulation_day));
        TextView tv_title_chart_prediction_day = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_chart_prediction_day);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_chart_prediction_day, "tv_title_chart_prediction_day");
        tv_title_chart_prediction_day.setText(getLanguageText(R.string.title_prediction_menstrulation));
        TextView tv_title_chart_ovulation_day = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_chart_ovulation_day);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_chart_ovulation_day, "tv_title_chart_ovulation_day");
        tv_title_chart_ovulation_day.setText(getLanguageText(R.string.detail_ovulation_date));
        TextView tv_title_chart_fertile_period = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_chart_fertile_period);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_chart_fertile_period, "tv_title_chart_fertile_period");
        tv_title_chart_fertile_period.setText(getLanguageText(R.string.fertile_period));
        TextView tv_mens_record_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_mens_record_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_mens_record_desc, "tv_mens_record_desc");
        tv_mens_record_desc.setText(getLanguageText(R.string.mens_record_desc));
        TextView tv_record_menstrual_cycle = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_record_menstrual_cycle);
        Intrinsics.checkExpressionValueIsNotNull(tv_record_menstrual_cycle, "tv_record_menstrual_cycle");
        tv_record_menstrual_cycle.setText(getLanguageText(R.string.record_menstrulation_day));
        TextView tv_title_menstrulation_avg = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_menstrulation_avg);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_menstrulation_avg, "tv_title_menstrulation_avg");
        tv_title_menstrulation_avg.setText(getLanguageText(R.string.menstrulation_avg_length));
        TextView tv_menstrulation_avg_unit = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_menstrulation_avg_unit);
        Intrinsics.checkExpressionValueIsNotNull(tv_menstrulation_avg_unit, "tv_menstrulation_avg_unit");
        tv_menstrulation_avg_unit.setText(getLanguageText(R.string.public_unit_day));
        TextView tv_title_menstrulation_cycle_avg = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_menstrulation_cycle_avg);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_menstrulation_cycle_avg, "tv_title_menstrulation_cycle_avg");
        tv_title_menstrulation_cycle_avg.setText(getLanguageText(R.string.menstrulation_avg_cycle_length));
        TextView tv_menstrulation_cycle_avg_unit = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_menstrulation_cycle_avg_unit);
        Intrinsics.checkExpressionValueIsNotNull(tv_menstrulation_cycle_avg_unit, "tv_menstrulation_cycle_avg_unit");
        tv_menstrulation_cycle_avg_unit.setText(getLanguageText(R.string.public_unit_day));
        TextView tv_mensulation_history = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_mensulation_history);
        Intrinsics.checkExpressionValueIsNotNull(tv_mensulation_history, "tv_mensulation_history");
        tv_mensulation_history.setText(getLanguageText(R.string.menstrulation_history));
        TextView tv_title_abort_menstrulation_cycle = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_abort_menstrulation_cycle);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_abort_menstrulation_cycle, "tv_title_abort_menstrulation_cycle");
        tv_title_abort_menstrulation_cycle.setText(getLanguageText(R.string.title_abort_menstrulation));
        TextView tv_abort_menstrulation_cycle_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_abort_menstrulation_cycle_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_abort_menstrulation_cycle_desc, "tv_abort_menstrulation_cycle_desc");
        tv_abort_menstrulation_cycle_desc.setText(getLanguageText(R.string.title_abort_menstrulation_desc));
    }

    @Override // com.ido.life.module.home.menstrualcycle.IMenstrualCycleDetailView
    public void onGetDataSuccess(List<MenstrulationSettingPresenter.SettingBean> dataList) {
        Intrinsics.checkParameterIsNotNull(dataList, "dataList");
        if (this.mChartAdapter == null) {
            this.mChartAdapter = new MenstrualChartAdapter(this, dataList, this);
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view);
            if (recyclerView != null) {
                recyclerView.setAdapter(this.mChartAdapter);
            }
            new PagerSnapHelper().attachToRecyclerView((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view));
            RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view);
            if (recyclerView2 != null) {
                recyclerView2.addOnScrollListener(new MensRecyclerScrollListener());
            }
            RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view);
            if (recyclerView3 != null) {
                recyclerView3.scrollToPosition(1);
            }
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            MenstrulationSettingPresenter.SettingBean settingBean = dataList.get(1);
            calendar.set(1, settingBean.getYear());
            calendar.set(2, settingBean.getMonth());
            TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_chart_date);
            if (textView != null) {
                textView.setText(DateUtil.format(calendar, DateUtil.DATE_FORMAT_MY_1));
                return;
            }
            return;
        }
        ((MenstrualCycleDetailPresenter) this.mPresenter).updateMensInfo();
        MenstrualChartAdapter menstrualChartAdapter = this.mChartAdapter;
        if (menstrualChartAdapter != null) {
            menstrualChartAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.ido.life.module.home.menstrualcycle.IMenstrualCycleDetailView
    public void menstruationAvgLength(int length) {
        CommonLogUtil.printAndSave("menstruationAvgLength=" + length);
        if (length > 0) {
            MediumTextView mediumTextView = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_menstrulation_avg_value);
            if (mediumTextView != null) {
                mediumTextView.setText(String.valueOf(length));
                return;
            }
            return;
        }
        MediumTextView mediumTextView2 = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_menstrulation_avg_value);
        if (mediumTextView2 != null) {
            mediumTextView2.setText("-");
        }
    }

    @Override // com.ido.life.module.home.menstrualcycle.IMenstrualCycleDetailView
    public void menstruationCycleAvg(int length) {
        if (length > 0) {
            MediumTextView mediumTextView = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_menstrulation_cycle_avg_value);
            if (mediumTextView != null) {
                mediumTextView.setText(String.valueOf(length));
                return;
            }
            return;
        }
        MediumTextView mediumTextView2 = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_menstrulation_cycle_avg_value);
        if (mediumTextView2 != null) {
            mediumTextView2.setText("-");
        }
    }

    @Override // com.ido.life.module.home.menstrualcycle.IMenstrualCycleDetailView
    public void setHistoryMenstruation(List<MenstrualCycleDetailPresenter.HistoryMenstruationItemBean> historyList) {
        Intrinsics.checkParameterIsNotNull(historyList, "historyList");
        if (historyList.isEmpty()) {
            LinearLayout lay_mens_history = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_mens_history);
            Intrinsics.checkExpressionValueIsNotNull(lay_mens_history, "lay_mens_history");
            lay_mens_history.setVisibility(8);
        } else {
            LinearLayout lay_mens_history2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_mens_history);
            Intrinsics.checkExpressionValueIsNotNull(lay_mens_history2, "lay_mens_history");
            lay_mens_history2.setVisibility(0);
            RecyclerView recycler_history = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_history);
            Intrinsics.checkExpressionValueIsNotNull(recycler_history, "recycler_history");
            recycler_history.setAdapter(new HistoryMenstrulationAdapter(this, historyList));
        }
    }

    private final void showSyncDialog() {
        MenstrualCycleDetailActivity menstrualCycleDetailActivity = this;
        View viewInflate = LayoutInflater.from(menstrualCycleDetailActivity).inflate(R.layout.dialog_sync_cycle_layout, (ViewGroup) null);
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(menstrualCycleDetailActivity, R.style.dialog_translate).setView(viewInflate).create();
        Intrinsics.checkExpressionValueIsNotNull(alertDialogCreate, "AlertDialog.Builder(this…e).setView(view).create()");
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.setCancelable(false);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_ok);
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.home.menstrualcycle.MenstrualCycleDetailActivity.showSyncDialog.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    alertDialogCreate.dismiss();
                }
            });
        }
        alertDialogCreate.show();
    }

    @Override // com.ido.life.module.home.menstrualcycle.IMenstrualCycleDetailView
    public void updateMensDesc(int color, SpannableStringBuilder desc) {
        if (desc == null) {
            TextView tv_title_no_data = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_no_data);
            Intrinsics.checkExpressionValueIsNotNull(tv_title_no_data, "tv_title_no_data");
            tv_title_no_data.setVisibility(0);
            LinearLayout lay_lifecycle_status = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_lifecycle_status);
            Intrinsics.checkExpressionValueIsNotNull(lay_lifecycle_status, "lay_lifecycle_status");
            lay_lifecycle_status.setVisibility(8);
            return;
        }
        TextView tv_title_no_data2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_no_data);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_no_data2, "tv_title_no_data");
        tv_title_no_data2.setVisibility(8);
        LinearLayout lay_lifecycle_status2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_lifecycle_status);
        Intrinsics.checkExpressionValueIsNotNull(lay_lifecycle_status2, "lay_lifecycle_status");
        lay_lifecycle_status2.setVisibility(0);
        ImageView img_state = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_state);
        Intrinsics.checkExpressionValueIsNotNull(img_state, "img_state");
        img_state.setBackgroundTintList(ColorStateList.valueOf(color));
        TextView tv_guide = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_guide);
        Intrinsics.checkExpressionValueIsNotNull(tv_guide, "tv_guide");
        tv_guide.setVisibility(0);
        ImageView img_state2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_state);
        Intrinsics.checkExpressionValueIsNotNull(img_state2, "img_state");
        img_state2.setVisibility(0);
        TextView tv_state_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_state_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_state_desc, "tv_state_desc");
        tv_state_desc.setText(desc);
    }

    @Override // com.ido.life.module.home.menstrualcycle.IMenstrualCycleDetailView
    public void syncToDeviceDisconnect() {
        ValueAnimator valueAnimator = this.mRelateAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        showSyncDialog();
    }

    @Override // com.ido.life.module.home.menstrualcycle.IMenstrualCycleDetailView
    public void syncToDeviceSuccess() {
        ValueAnimator valueAnimator = this.mRelateAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    @Override // com.ido.life.module.home.menstrualcycle.IMenstrualCycleDetailView
    public void syncToDeviceFailed() {
        ValueAnimator valueAnimator = this.mRelateAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_sync_device)).setOnClickListener(this);
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.img_sync_device)).setBackgroundResource(R.mipmap.mens_sync_device);
        TextView tv_sync_device_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_sync_device_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_sync_device_desc, "tv_sync_device_desc");
        tv_sync_device_desc.setText(getLanguageText(R.string.sync_to_device));
    }

    @Override // com.ido.life.module.home.menstrualcycle.IMenstrualCycleDetailView
    public void startSyncToDevice() {
        ValueAnimator valueAnimator = this.mRelateAnimator;
        if (valueAnimator != null) {
            if (valueAnimator == null) {
                Intrinsics.throwNpe();
            }
            valueAnimator.cancel();
        } else {
            this.mRelateAnimator = ObjectAnimator.ofFloat((ImageView) _$_findCachedViewById(com.ido.life.R.id.img_sync_device), "rotation", 0.0f, 360.0f);
            ValueAnimator valueAnimator2 = this.mRelateAnimator;
            if (valueAnimator2 == null) {
                Intrinsics.throwNpe();
            }
            valueAnimator2.setDuration(600L);
        }
        ValueAnimator valueAnimator3 = this.mRelateAnimator;
        if (valueAnimator3 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator3.start();
    }

    @Override // com.ido.life.module.home.menstrualcycle.IMenstrualCycleDetailView
    public void dataLoadSuccess() {
        ValueAnimator valueAnimator = this.mHistoryLoadingAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        LinearLayout load_failed = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.load_failed);
        Intrinsics.checkExpressionValueIsNotNull(load_failed, "load_failed");
        load_failed.setVisibility(8);
        LinearLayout lay_loading = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_loading);
        Intrinsics.checkExpressionValueIsNotNull(lay_loading, "lay_loading");
        lay_loading.setVisibility(8);
        ImageView img_state = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_state);
        Intrinsics.checkExpressionValueIsNotNull(img_state, "img_state");
        img_state.setVisibility(0);
        TextView tv_guide = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_guide);
        Intrinsics.checkExpressionValueIsNotNull(tv_guide, "tv_guide");
        tv_guide.setVisibility(0);
    }

    @Override // com.ido.life.module.home.menstrualcycle.IMenstrualCycleDetailView
    public void dataLoadFailed() {
        ValueAnimator valueAnimator = this.mHistoryLoadingAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        LinearLayout load_failed = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.load_failed);
        Intrinsics.checkExpressionValueIsNotNull(load_failed, "load_failed");
        load_failed.setVisibility(0);
        LinearLayout lay_loading = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_loading);
        Intrinsics.checkExpressionValueIsNotNull(lay_loading, "lay_loading");
        lay_loading.setVisibility(8);
        LinearLayout lay_sync_device = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_sync_device);
        Intrinsics.checkExpressionValueIsNotNull(lay_sync_device, "lay_sync_device");
        lay_sync_device.setVisibility(8);
        ImageView img_state = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_state);
        Intrinsics.checkExpressionValueIsNotNull(img_state, "img_state");
        img_state.setVisibility(8);
        TextView tv_guide = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_guide);
        Intrinsics.checkExpressionValueIsNotNull(tv_guide, "tv_guide");
        tv_guide.setVisibility(8);
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_retry)).setOnClickListener(this);
        String str = getLanguageText(R.string.detail_menstrual) + " --" + getLanguageText(R.string.public_unit_day);
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, "--", 0, false, 6, (Object) null);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new TextAppearanceSpan(IdoApp.getAppContext(), R.style.home_life_cycle_info), iIndexOf$default, 2 + iIndexOf$default, 17);
        TextView tv_state_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_state_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_state_desc, "tv_state_desc");
        tv_state_desc.setText(spannableStringBuilder);
    }

    @Override // com.ido.life.module.home.menstrualcycle.IMenstrualCycleDetailView
    public void dataLoading() {
        LinearLayout load_failed = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.load_failed);
        Intrinsics.checkExpressionValueIsNotNull(load_failed, "load_failed");
        load_failed.setVisibility(8);
        LinearLayout lay_loading = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_loading);
        Intrinsics.checkExpressionValueIsNotNull(lay_loading, "lay_loading");
        lay_loading.setVisibility(0);
        LinearLayout lay_sync_device = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_sync_device);
        Intrinsics.checkExpressionValueIsNotNull(lay_sync_device, "lay_sync_device");
        lay_sync_device.setVisibility(8);
        ImageView img_state = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_state);
        Intrinsics.checkExpressionValueIsNotNull(img_state, "img_state");
        img_state.setVisibility(8);
        TextView tv_guide = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_guide);
        Intrinsics.checkExpressionValueIsNotNull(tv_guide, "tv_guide");
        tv_guide.setVisibility(8);
        String str = getLanguageText(R.string.detail_menstrual) + " --" + getLanguageText(R.string.public_unit_day);
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, "--", 0, false, 6, (Object) null);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new TextAppearanceSpan(IdoApp.getAppContext(), R.style.home_life_cycle_info), iIndexOf$default, 2 + iIndexOf$default, 17);
        TextView tv_state_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_state_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_state_desc, "tv_state_desc");
        tv_state_desc.setText(spannableStringBuilder);
        ValueAnimator valueAnimator = this.mHistoryLoadingAnimator;
        if (valueAnimator != null) {
            if (valueAnimator == null) {
                Intrinsics.throwNpe();
            }
            valueAnimator.cancel();
        } else {
            this.mHistoryLoadingAnimator = ObjectAnimator.ofFloat((ImageView) _$_findCachedViewById(com.ido.life.R.id.img_loading), "rotation", 0.0f, 360.0f);
            ValueAnimator valueAnimator2 = this.mHistoryLoadingAnimator;
            if (valueAnimator2 == null) {
                Intrinsics.throwNpe();
            }
            valueAnimator2.setDuration(600L);
        }
        ValueAnimator valueAnimator3 = this.mHistoryLoadingAnimator;
        if (valueAnimator3 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator3.start();
    }

    @Override // com.ido.life.module.home.menstrualcycle.IMenstrualCycleDetailView
    public void updateDeviceConnectState(boolean connected) {
        if (connected) {
            ((ImageView) _$_findCachedViewById(com.ido.life.R.id.img_sync_device)).setBackgroundResource(R.mipmap.mens_sync_device);
            TextView tv_sync_device_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_sync_device_desc);
            Intrinsics.checkExpressionValueIsNotNull(tv_sync_device_desc, "tv_sync_device_desc");
            tv_sync_device_desc.setText(getLanguageText(R.string.sync_to_device));
            LinearLayout lay_sync_device = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_sync_device);
            Intrinsics.checkExpressionValueIsNotNull(lay_sync_device, "lay_sync_device");
            lay_sync_device.setVisibility(0);
        } else {
            ((ImageView) _$_findCachedViewById(com.ido.life.R.id.img_sync_device)).setBackgroundResource(R.mipmap.mens_device_unconnect_tip);
            TextView tv_sync_device_desc2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_sync_device_desc);
            Intrinsics.checkExpressionValueIsNotNull(tv_sync_device_desc2, "tv_sync_device_desc");
            tv_sync_device_desc2.setText(getLanguageText(R.string.device_un_connect));
            LinearLayout lay_sync_device2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_sync_device);
            Intrinsics.checkExpressionValueIsNotNull(lay_sync_device2, "lay_sync_device");
            lay_sync_device2.setVisibility(0);
        }
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_sync_device)).setOnClickListener(this);
    }

    @Override // com.ido.life.base.BaseClickActivity
    public void clickAction(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        switch (view.getId()) {
            case R.id.lay_sync_device /* 2131362775 */:
                ((MenstrualCycleDetailPresenter) this.mPresenter).syncMensToDevice();
                break;
            case R.id.layout_right /* 2131362860 */:
                this.mNeedRefresh = true;
                startActivity(new SingleTopIntent(this, (Class<?>) MenstrualCycleSettingActivity.class));
                break;
            case R.id.tv_record_menstrual_cycle /* 2131364108 */:
                MenstruationSettingDialog companion = MenstruationSettingDialog.INSTANCE.getInstance();
                companion.show(getSupportFragmentManager());
                companion.setOnDataSuccessCallback(this);
                break;
            case R.id.tv_retry /* 2131364121 */:
                if (NetworkUtil.isConnected(this)) {
                    ((MenstrualCycleDetailPresenter) this.mPresenter).retryLoadData();
                } else {
                    showToast(R.string.public_net_unuse);
                }
                break;
        }
    }

    /* JADX INFO: compiled from: MenstrualCycleDetailActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J \u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0016¨\u0006\f"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailActivity$MensRecyclerScrollListener;", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "(Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailActivity;)V", "onScrollStateChanged", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "newState", "", "onScrolled", "dx", "dy", "app_release"}, k = 1, mv = {1, 1, 16})
    private final class MensRecyclerScrollListener extends RecyclerView.OnScrollListener {
        public MensRecyclerScrollListener() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
            if (newState == 0 && MenstrualCycleDetailActivity.this.mScrolled) {
                MenstrualCycleDetailActivity.this.mScrolled = false;
                if (MenstrualCycleDetailActivity.this.mChartAdapter != null) {
                    RecyclerView recyclerView2 = (RecyclerView) MenstrualCycleDetailActivity.this._$_findCachedViewById(com.ido.life.R.id.recycler_view);
                    if (recyclerView2 == null) {
                        Intrinsics.throwNpe();
                    }
                    RecyclerView.LayoutManager layoutManager = recyclerView2.getLayoutManager();
                    if (layoutManager != null) {
                        int iFindFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                        MenstrualChartAdapter menstrualChartAdapter = MenstrualCycleDetailActivity.this.mChartAdapter;
                        if (menstrualChartAdapter == null) {
                            Intrinsics.throwNpe();
                        }
                        MenstrulationSettingPresenter.SettingBean settingBean = menstrualChartAdapter.getDataList().get(iFindFirstVisibleItemPosition);
                        MenstrualCycleDetailActivity.this.printAndSaveLog("用户当前选择的页面信息：index=" + iFindFirstVisibleItemPosition + ",year=" + settingBean.getYear() + ",month=" + settingBean.getMonth());
                        Calendar calendar = Calendar.getInstance(Locale.CHINA);
                        calendar.set(1, settingBean.getYear());
                        calendar.set(2, settingBean.getMonth());
                        TextView textView = (TextView) MenstrualCycleDetailActivity.this._$_findCachedViewById(com.ido.life.R.id.tv_chart_date);
                        if (textView != null) {
                            textView.setText(DateUtil.format(calendar, DateUtil.DATE_FORMAT_MY_1));
                        }
                        if (iFindFirstVisibleItemPosition != 0) {
                            MenstrualChartAdapter menstrualChartAdapter2 = MenstrualCycleDetailActivity.this.mChartAdapter;
                            if (menstrualChartAdapter2 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (iFindFirstVisibleItemPosition == menstrualChartAdapter2.getDataList().size() - 1) {
                                MenstrualCycleDetailActivity.access$getMPresenter$p(MenstrualCycleDetailActivity.this).updateMonthOffset(1);
                            }
                        } else {
                            MenstrualCycleDetailActivity.access$getMPresenter$p(MenstrualCycleDetailActivity.this).updateMonthOffset(-1);
                        }
                        MenstrualChartAdapter menstrualChartAdapter3 = MenstrualCycleDetailActivity.this.mChartAdapter;
                        if (menstrualChartAdapter3 != null) {
                            menstrualChartAdapter3.notifyDataSetChanged();
                        }
                        RecyclerView recyclerView3 = (RecyclerView) MenstrualCycleDetailActivity.this._$_findCachedViewById(com.ido.life.R.id.recycler_view);
                        if (recyclerView3 != null) {
                            recyclerView3.scrollToPosition(1);
                            return;
                        }
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
            if (dx == 0 && dy == 0) {
                return;
            }
            MenstrualCycleDetailActivity.this.mScrolled = true;
        }
    }

    @Override // com.ido.life.module.home.menstrualcycle.MenstrualCycleDetailCallback
    public List<MenstruationCalendar.DateInfo> getMenstruationDateList(int year, int month) {
        return ((MenstrualCycleDetailPresenter) this.mPresenter).getMenstruationData(year, month);
    }

    @Override // com.ido.life.module.home.menstrualcycle.MenstrualCycleDetailCallback
    public int getWeekStart() {
        return ((MenstrualCycleDetailPresenter) this.mPresenter).getMWeekStart();
    }

    @Override // com.ido.life.module.home.menstrualcycle.MenstrualCycleDetailCallback
    public void updateMonthOffest(int offset) {
        ((MenstrualCycleDetailPresenter) this.mPresenter).updateMonthOffset(offset);
        ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view)).scrollToPosition(1);
    }

    @Override // com.ido.life.module.home.menstrualcycle.dialog.MenstrualCycleSaveDataCallback
    public void saveDataSuccess() {
        ((MenstrualCycleDetailPresenter) this.mPresenter).startLoadData();
        ((MenstrualCycleDetailPresenter) this.mPresenter).syncMensToDevice();
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        BLEManager.unregisterSettingCallBack((SettingCallBack.ICallBack) this.mPresenter);
        BLEManager.unregisterOtherProtocolCallBack((OtherProtocolCallBack.ICallBack) this.mPresenter);
        super.onDestroy();
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage<?> message) {
        super.handleMessage(message);
        MenstrualCycleDetailPresenter menstrualCycleDetailPresenter = (MenstrualCycleDetailPresenter) this.mPresenter;
        if (menstrualCycleDetailPresenter != null) {
            menstrualCycleDetailPresenter.processBusMessage(message);
        }
    }
}