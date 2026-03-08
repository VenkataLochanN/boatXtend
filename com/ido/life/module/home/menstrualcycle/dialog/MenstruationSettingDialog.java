package com.ido.life.module.home.menstrualcycle.dialog;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.utils.FileSizeUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.constants.Constants;
import com.ido.life.customview.MenstruationCalendar;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.module.home.menstrualcycle.MenstrualUtilKt;
import com.ido.life.module.home.menstrualcycle.dialog.MenstrulationSettingPresenter;
import com.ido.life.util.DateUtil;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: MenstruationSettingDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 ,2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002,-B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\u001a\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0017\u001a\u00020\u0013H\u0002J\u0018\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0013H\u0002J\u0012\u0010\u001c\u001a\u00020\u00112\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0012\u0010\u001f\u001a\u00020\u00112\b\u0010 \u001a\u0004\u0018\u00010\u001eH\u0016J\u0012\u0010!\u001a\u00020\u00112\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0018\u0010$\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0013H\u0016J\b\u0010%\u001a\u00020\u0011H\u0016J\u0010\u0010&\u001a\u00020\u00112\u0006\u0010'\u001a\u00020\u0007H\u0016J\b\u0010(\u001a\u00020\u0011H\u0016J\u000e\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\rJ\b\u0010+\u001a\u00020\u0011H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0018\u00010\tR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/dialog/MenstruationSettingDialog;", "Lcom/ido/common/base/BaseDialogFragment;", "Landroid/view/View$OnClickListener;", "Lcom/ido/life/customview/MenstruationCalendar$CalendarClickListener;", "Lcom/ido/life/module/home/menstrualcycle/dialog/IMenstrulationSettingView;", "()V", "TAG", "", "mAdapter", "Lcom/ido/life/module/home/menstrualcycle/dialog/MenstruationSettingDialog$SettingAdapter;", "mPresenter", "Lcom/ido/life/module/home/menstrualcycle/dialog/MenstrulationSettingPresenter;", "mSaveDataSuccessCallback", "Lcom/ido/life/module/home/menstrualcycle/dialog/MenstrualCycleSaveDataCallback;", "mWaitingDialog", "Lcom/ido/common/dialog/WaitingDialog;", "dismissLoadingDialog", "", "getLayoutResId", "", "getWeekTextView", "Landroid/widget/TextView;", "label", "size", "handleClickDate", Constants.AppPackage.CALENDAR, "Lcom/ido/life/customview/MenstruationCalendar;", "index", "initUI", "view", "Landroid/view/View;", "onClick", "v", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDateClickListenter", "refreshData", "saveFailed", "message", "saveSuccess", "setOnDataSuccessCallback", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "showLoadingDialog", "Companion", "SettingAdapter", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MenstruationSettingDialog extends BaseDialogFragment implements View.OnClickListener, MenstruationCalendar.CalendarClickListener, IMenstrulationSettingView {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private HashMap _$_findViewCache;
    private SettingAdapter mAdapter;
    private MenstrualCycleSaveDataCallback mSaveDataSuccessCallback;
    private WaitingDialog mWaitingDialog;
    private final String TAG = "MenstrulationSettingDialog";
    private MenstrulationSettingPresenter mPresenter = new MenstrulationSettingPresenter();

    @JvmStatic
    public static final MenstruationSettingDialog getInstance() {
        return INSTANCE.getInstance();
    }

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View viewFindViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_menstrulation_setting_layout;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, R.style.dialog_translate);
    }

    public final MenstruationSettingDialog setOnDataSuccessCallback(MenstrualCycleSaveDataCallback listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.mSaveDataSuccessCallback = listener;
        return this;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        if (getDialog() != null) {
            Dialog dialog = getDialog();
            if (dialog == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog!!");
            if (dialog.getWindow() != null) {
                Dialog dialog2 = getDialog();
                if (dialog2 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(dialog2, "dialog!!");
                Window window = dialog2.getWindow();
                if (window == null) {
                    Intrinsics.throwNpe();
                }
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.gravity = 80;
                attributes.width = -1;
                window.setAttributes(attributes);
                int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sw_dp_8);
                window.getDecorView().setPadding(dimensionPixelSize, 0, dimensionPixelSize, getResources().getDimensionPixelSize(R.dimen.sw_dp_4));
                ViewGroup contentView = (ViewGroup) window.getDecorView().findViewById(android.R.id.content);
                Intrinsics.checkExpressionValueIsNotNull(contentView, "contentView");
                ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
                Resources resources = getResources();
                Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
                layoutParams.height = (resources.getDisplayMetrics().heightPixels * 7) / 8;
                contentView.setLayoutParams(layoutParams);
            }
        }
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_cancel);
        if (textView != null) {
            textView.setText(LanguageUtil.getLanguageText(R.string.public_tip_cancel));
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title);
        if (textView2 != null) {
            textView2.setText(LanguageUtil.getLanguageText(R.string.select_menstrulation_date));
        }
        TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete);
        if (textView3 != null) {
            textView3.setText(LanguageUtil.getLanguageText(R.string.mine_complete));
        }
        TextView textView4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_desc);
        if (textView4 != null) {
            textView4.setText(LanguageUtil.getLanguageText(R.string.mens_length_max_desc));
        }
        TextView textView5 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_cancel);
        if (textView5 != null) {
            textView5.setOnClickListener(this);
        }
        this.mPresenter.attachView(this);
        List<String> weekList = this.mPresenter.getWeekList();
        int size = weekList.size();
        Resources resources2 = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources2, "resources");
        int dimensionPixelSize2 = (resources2.getDisplayMetrics().widthPixels - (getResources().getDimensionPixelSize(R.dimen.sw_dp_8) * 2)) / 7;
        for (int i = 0; i < size; i++) {
            ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_week)).addView(getWeekTextView(weekList.get(i), dimensionPixelSize2));
        }
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        this.mAdapter = new SettingAdapter(this, context, R.layout.item_dialog_menstrulation_layout, this.mPresenter.getSettingList());
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_calendar);
        if (recyclerView != null) {
            recyclerView.setAdapter(this.mAdapter);
        }
        int size2 = this.mPresenter.getSettingList().size();
        int mCurrentCalendarPosition = this.mPresenter.getMCurrentCalendarPosition();
        if (mCurrentCalendarPosition >= 0 && size2 > mCurrentCalendarPosition) {
            RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_calendar);
            if (recyclerView2 != null) {
                recyclerView2.scrollToPosition(this.mPresenter.getMCurrentCalendarPosition());
                return;
            }
            return;
        }
        RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_calendar);
        if (recyclerView3 != null) {
            recyclerView3.scrollToPosition(this.mPresenter.getSettingList().size() - 1);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_cancel) {
            dismissAllowingStateLoss();
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_complete) {
            this.mPresenter.saveMenstruation();
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_retry) {
            if (NetworkUtil.isConnected(getContext())) {
                this.mPresenter.retryLoadData();
            } else {
                NormalToast.showToast(LanguageUtil.getLanguageText(R.string.public_net_unuse));
            }
        }
    }

    private final TextView getWeekTextView(String label, int size) {
        TextView textView = new TextView(getContext());
        textView.setText(label);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setTextSize(0, getResources().getDimensionPixelSize(R.dimen.size12sp));
        textView.setLayoutParams(new LinearLayout.LayoutParams(size, size));
        textView.setGravity(17);
        return textView;
    }

    /* JADX INFO: compiled from: MenstruationSettingDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b¢\u0006\u0002\u0010\tJ \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0006H\u0014¨\u0006\u0010"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/dialog/MenstruationSettingDialog$SettingAdapter;", "Lcom/ido/life/customview/recyclerview/CommonRecyclerViewAdapter;", "Lcom/ido/life/module/home/menstrualcycle/dialog/MenstrulationSettingPresenter$SettingBean;", "context", "Landroid/content/Context;", "layoutResId", "", "dataList", "", "(Lcom/ido/life/module/home/menstrualcycle/dialog/MenstruationSettingDialog;Landroid/content/Context;ILjava/util/List;)V", "convert", "", "holder", "Lcom/ido/life/customview/recyclerview/CommonRecyclerViewHolder;", "t", "position", "app_release"}, k = 1, mv = {1, 1, 16})
    public final class SettingAdapter extends CommonRecyclerViewAdapter<MenstrulationSettingPresenter.SettingBean> {
        final /* synthetic */ MenstruationSettingDialog this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SettingAdapter(MenstruationSettingDialog menstruationSettingDialog, Context context, int i, List<MenstrulationSettingPresenter.SettingBean> dataList) {
            super(context, i, dataList);
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(dataList, "dataList");
            this.this$0 = menstruationSettingDialog;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
        public void convert(CommonRecyclerViewHolder holder, MenstrulationSettingPresenter.SettingBean t, int position) {
            Intrinsics.checkParameterIsNotNull(holder, "holder");
            Intrinsics.checkParameterIsNotNull(t, "t");
            MenstrulationSettingPresenter.SettingBean settingBean = getDatas().get(position);
            TextView tv_month = (TextView) holder.getView(R.id.tv_month);
            TextView tv_year = (TextView) holder.getView(R.id.tv_year);
            LinearLayout layLoading = (LinearLayout) holder.getView(R.id.lay_loading);
            LinearLayout layLoadFailed = (LinearLayout) holder.getView(R.id.load_failed);
            ImageView imageView = (ImageView) holder.getView(R.id.img_loading);
            int color = Color.parseColor("#FFF7F7");
            Intrinsics.checkExpressionValueIsNotNull(layLoading, "layLoading");
            layLoading.setBackgroundTintList(ColorStateList.valueOf(color));
            Intrinsics.checkExpressionValueIsNotNull(layLoadFailed, "layLoadFailed");
            layLoadFailed.setBackgroundTintList(ColorStateList.valueOf(color));
            layLoading.setAlpha(0.3f);
            layLoadFailed.setAlpha(0.3f);
            TextView textView = (TextView) holder.getView(R.id.tv_retry);
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            calendar.set(1, settingBean.getYear());
            calendar.set(2, settingBean.getMonth());
            Intrinsics.checkExpressionValueIsNotNull(tv_month, "tv_month");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = DateUtil.format(calendar, FileSizeUtil.UNIT_MB);
            Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(dateCalendar, \"M\")");
            Object[] objArr = {Integer.valueOf(settingBean.getYear()), Integer.valueOf(Integer.parseInt(str))};
            String str2 = String.format("%d-%02d", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
            tv_month.setText(str2);
            Intrinsics.checkExpressionValueIsNotNull(tv_year, "tv_year");
            tv_year.setVisibility(8);
            MenstruationCalendar calendar2 = (MenstruationCalendar) holder.getView(R.id.menstrulation_calendar);
            Intrinsics.checkExpressionValueIsNotNull(calendar2, "calendar");
            Resources resources = this.this$0.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
            calendar2.setMinimumWidth(resources.getDisplayMetrics().widthPixels - (this.this$0.getResources().getDimensionPixelSize(R.dimen.sw_dp_8) * 2));
            calendar2.setYear(settingBean.getYear());
            calendar2.setMonth(settingBean.getMonth());
            calendar2.setCalendarClickListener(this.this$0);
            calendar2.setDateList(this.this$0.mPresenter.getDateList(settingBean.getYear(), settingBean.getMonth()));
            ObjectAnimator valueAnimator = (ValueAnimator) null;
            Object tag = layLoading.getTag();
            if (tag instanceof ValueAnimator) {
                valueAnimator = (ValueAnimator) tag;
            }
            int dataDownloadState = this.this$0.mPresenter.getDataDownloadState(settingBean.getYear(), settingBean.getMonth());
            if (dataDownloadState == 2) {
                layLoading.setVisibility(0);
                layLoadFailed.setVisibility(8);
                if (valueAnimator == null) {
                    valueAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0.0f, 360.0f);
                    Intrinsics.checkExpressionValueIsNotNull(valueAnimator, "valueAnimator");
                    valueAnimator.setDuration(600L);
                } else {
                    valueAnimator.cancel();
                }
                valueAnimator.start();
                layLoading.setTag(valueAnimator);
            } else if (dataDownloadState == 4) {
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                }
                layLoadFailed.setVisibility(0);
                layLoading.setVisibility(8);
                textView.setOnClickListener(this.this$0);
            } else {
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                }
                layLoadFailed.setVisibility(8);
                layLoading.setVisibility(8);
            }
            calendar2.refreshCalendar(true);
        }
    }

    @Override // com.ido.life.customview.MenstruationCalendar.CalendarClickListener
    public void onDateClickListenter(MenstruationCalendar calendar, int index) {
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        handleClickDate(calendar, index);
    }

    private final void handleClickDate(MenstruationCalendar calendar, int index) {
        int iFindPositionInSettingList = this.mPresenter.findPositionInSettingList(calendar.getMYear(), calendar.getMMonth());
        if (iFindPositionInSettingList < 0 || iFindPositionInSettingList >= this.mPresenter.getSettingList().size()) {
            HomeHelperKt.printAndSave("数据错误,index=" + index + ",year=" + calendar.getMYear() + ",month=" + calendar.getMMonth(), this.TAG);
            return;
        }
        MenstruationCalendar.DateInfo dateInfo = calendar.getDateList().get(index);
        boolean z = true;
        if (dateInfo.getType() == MenstruationCalendar.Type.MENSTRUATION) {
            dateInfo.setType(MenstruationCalendar.Type.NORMAL);
            dateInfo.setAutoLink(false);
            calendar.refreshCalendar(false);
            if (this.mPresenter.dataHasChanged()) {
                TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete);
                if (textView != null) {
                    textView.setOnClickListener(this);
                }
                TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete);
                if (textView2 != null) {
                    textView2.setEnabled(true);
                    return;
                }
                return;
            }
            TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete);
            if (textView3 != null) {
                textView3.setOnClickListener(null);
            }
            TextView textView4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete);
            if (textView4 != null) {
                textView4.setEnabled(false);
                return;
            }
            return;
        }
        Calendar calendar2 = Calendar.getInstance(Locale.CHINA);
        calendar2.set(1, calendar.getMYear());
        calendar2.set(2, calendar.getMMonth());
        calendar2.set(5, dateInfo.getValue());
        Pair<String, String> pairFindPastMensFirstEndDate = this.mPresenter.findPastMensFirstEndDate(calendar.getMYear(), calendar.getMMonth(), index - 1);
        Pair<String, String> pairFindAfterMensFirstStartDate = this.mPresenter.findAfterMensFirstStartDate(calendar.getMYear(), calendar.getMMonth(), index + 1);
        if (pairFindPastMensFirstEndDate == null && pairFindAfterMensFirstStartDate == null) {
            dateInfo.setType(MenstruationCalendar.Type.MENSTRUATION);
        } else {
            if (pairFindPastMensFirstEndDate != null && pairFindAfterMensFirstStartDate != null) {
                String second = pairFindPastMensFirstEndDate.getSecond();
                String str = DateUtil.format(calendar2, DateUtil.DATE_FORMAT_YMD);
                Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(clickCal…DateUtil.DATE_FORMAT_YMD)");
                int days = MenstrualUtilKt.getDays(second, str);
                String str2 = DateUtil.format(calendar2, DateUtil.DATE_FORMAT_YMD);
                Intrinsics.checkExpressionValueIsNotNull(str2, "DateUtil.format(clickCal…DateUtil.DATE_FORMAT_YMD)");
                int days2 = MenstrualUtilKt.getDays(str2, pairFindAfterMensFirstStartDate.getFirst());
                HomeHelperKt.printAndSave("距离前面最近一段生理周期结束日期的天数是" + days + ",距离后面最近一段生理周期的天数是" + days2, this.TAG);
                if (days == 2 && days2 == 2) {
                    if (MenstrualUtilKt.getDays(pairFindPastMensFirstEndDate.getFirst(), pairFindAfterMensFirstStartDate.getSecond()) <= 14) {
                        dateInfo.setType(MenstruationCalendar.Type.MENSTRUATION);
                    } else {
                        NormalToast.showToast(LanguageUtil.getLanguageText(R.string.mens_length_max_desc));
                        return;
                    }
                } else if (days == 2 && days2 > 2) {
                    String first = pairFindPastMensFirstEndDate.getFirst();
                    String str3 = DateUtil.format(calendar2, DateUtil.DATE_FORMAT_YMD);
                    Intrinsics.checkExpressionValueIsNotNull(str3, "DateUtil.format(clickCal…DateUtil.DATE_FORMAT_YMD)");
                    if (MenstrualUtilKt.getDays(first, str3) <= 14) {
                        dateInfo.setType(MenstruationCalendar.Type.MENSTRUATION);
                    } else {
                        NormalToast.showToast(LanguageUtil.getLanguageText(R.string.mens_length_max_desc));
                        return;
                    }
                } else if (days > 2 && days2 == 2) {
                    String clickDate = DateUtil.format(calendar2, DateUtil.DATE_FORMAT_YMD);
                    Intrinsics.checkExpressionValueIsNotNull(clickDate, "clickDate");
                    if (MenstrualUtilKt.getDays(clickDate, pairFindAfterMensFirstStartDate.getSecond()) <= 14) {
                        dateInfo.setType(MenstruationCalendar.Type.MENSTRUATION);
                        if (MenstrualUtilKt.getDays(pairFindPastMensFirstEndDate.getSecond(), clickDate) - 2 <= 5 && MenstrualUtilKt.getDays(pairFindPastMensFirstEndDate.getFirst(), pairFindAfterMensFirstStartDate.getSecond()) <= 14) {
                            this.mPresenter.setMenstruationDate(pairFindPastMensFirstEndDate.getFirst(), pairFindAfterMensFirstStartDate.getSecond());
                        }
                    } else {
                        NormalToast.showToast(LanguageUtil.getLanguageText(R.string.mens_length_max_desc));
                        return;
                    }
                } else if (days <= 2 || days2 <= 2) {
                    z = false;
                } else {
                    String selectedDate = DateUtil.format(calendar2, DateUtil.DATE_FORMAT_YMD);
                    dateInfo.setType(MenstruationCalendar.Type.MENSTRUATION);
                    String second2 = pairFindPastMensFirstEndDate.getSecond();
                    Intrinsics.checkExpressionValueIsNotNull(selectedDate, "selectedDate");
                    if (MenstrualUtilKt.getDays(second2, selectedDate) - 2 <= 5 && MenstrualUtilKt.getDays(pairFindPastMensFirstEndDate.getFirst(), selectedDate) <= 14) {
                        this.mPresenter.setMenstruationDate(pairFindPastMensFirstEndDate.getSecond(), selectedDate);
                    }
                }
            } else if (pairFindPastMensFirstEndDate == null && pairFindAfterMensFirstStartDate != null) {
                String selectedDate2 = DateUtil.format(calendar2, DateUtil.DATE_FORMAT_YMD);
                Intrinsics.checkExpressionValueIsNotNull(selectedDate2, "selectedDate");
                if (MenstrualUtilKt.getDays(selectedDate2, pairFindAfterMensFirstStartDate.getFirst()) != 2 || MenstrualUtilKt.getDays(selectedDate2, pairFindAfterMensFirstStartDate.getSecond()) <= 14) {
                    dateInfo.setType(MenstruationCalendar.Type.MENSTRUATION);
                } else {
                    NormalToast.showToast(LanguageUtil.getLanguageText(R.string.mens_length_max_desc));
                    return;
                }
            } else {
                String selectedDate3 = DateUtil.format(calendar2, DateUtil.DATE_FORMAT_YMD);
                if (pairFindPastMensFirstEndDate == null) {
                    Intrinsics.throwNpe();
                }
                String second3 = pairFindPastMensFirstEndDate.getSecond();
                Intrinsics.checkExpressionValueIsNotNull(selectedDate3, "selectedDate");
                if (MenstrualUtilKt.getDays(second3, selectedDate3) == 2) {
                    if (MenstrualUtilKt.getDays(pairFindPastMensFirstEndDate.getFirst(), selectedDate3) <= 14) {
                        dateInfo.setType(MenstruationCalendar.Type.MENSTRUATION);
                    } else {
                        NormalToast.showToast(LanguageUtil.getLanguageText(R.string.mens_length_max_desc));
                        return;
                    }
                } else {
                    dateInfo.setType(MenstruationCalendar.Type.MENSTRUATION);
                    if (MenstrualUtilKt.getDays(pairFindPastMensFirstEndDate.getSecond(), selectedDate3) - 2 <= 5 && MenstrualUtilKt.getDays(pairFindPastMensFirstEndDate.getFirst(), selectedDate3) <= 14) {
                        this.mPresenter.setMenstruationDate(pairFindPastMensFirstEndDate.getFirst(), selectedDate3);
                    }
                }
            }
            z = true;
        }
        if (z) {
            calendar.refreshCalendar(false);
        }
        if (this.mPresenter.dataHasChanged()) {
            TextView textView5 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete);
            if (textView5 != null) {
                textView5.setOnClickListener(this);
            }
            TextView textView6 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete);
            if (textView6 != null) {
                textView6.setEnabled(true);
                return;
            }
            return;
        }
        TextView textView7 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete);
        if (textView7 != null) {
            textView7.setOnClickListener(null);
        }
        TextView textView8 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete);
        if (textView8 != null) {
            textView8.setEnabled(false);
        }
    }

    /* JADX INFO: compiled from: MenstruationSettingDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/dialog/MenstruationSettingDialog$Companion;", "", "()V", "getInstance", "Lcom/ido/life/module/home/menstrualcycle/dialog/MenstruationSettingDialog;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final MenstruationSettingDialog getInstance() {
            return new MenstruationSettingDialog();
        }
    }

    @Override // com.ido.life.module.home.menstrualcycle.dialog.IMenstrulationSettingView
    public void saveSuccess() {
        MenstrualCycleSaveDataCallback menstrualCycleSaveDataCallback = this.mSaveDataSuccessCallback;
        if (menstrualCycleSaveDataCallback != null) {
            menstrualCycleSaveDataCallback.saveDataSuccess();
        }
        dismissAllowingStateLoss();
    }

    @Override // com.ido.life.module.home.menstrualcycle.dialog.IMenstrulationSettingView
    public void saveFailed(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        MenstrualCycleSaveDataCallback menstrualCycleSaveDataCallback = this.mSaveDataSuccessCallback;
        if (menstrualCycleSaveDataCallback != null) {
            menstrualCycleSaveDataCallback.saveDataSuccess();
        }
        dismissAllowingStateLoss();
    }

    @Override // com.ido.life.module.home.menstrualcycle.dialog.IMenstrulationSettingView
    public void showLoadingDialog() {
        WaitingDialog waitingDialog = this.mWaitingDialog;
        if (waitingDialog != null) {
            if (waitingDialog == null) {
                Intrinsics.throwNpe();
            }
            if (waitingDialog.isDialogShowing()) {
                return;
            }
        }
        this.mWaitingDialog = WaitingDialog.newInstance(null);
        WaitingDialog waitingDialog2 = this.mWaitingDialog;
        if (waitingDialog2 != null) {
            waitingDialog2.show(getFragmentManager());
        }
    }

    @Override // com.ido.life.module.home.menstrualcycle.dialog.IMenstrulationSettingView
    public void dismissLoadingDialog() {
        WaitingDialog waitingDialog = this.mWaitingDialog;
        if (waitingDialog != null) {
            waitingDialog.dismissAllowingStateLoss();
        }
    }

    @Override // com.ido.life.module.home.menstrualcycle.dialog.IMenstrulationSettingView
    public void refreshData() {
        SettingAdapter settingAdapter = this.mAdapter;
        if (settingAdapter != null) {
            settingAdapter.notifyDataSetChanged();
        }
    }
}