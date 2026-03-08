package com.ido.life.module.user.healthreport;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BasePresenter;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HealthReportActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\b\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"Lcom/ido/life/module/user/healthreport/HealthReportActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/base/BasePresenter;", "()V", "getLayoutResId", "", "initLabelLanguage", "", "initViews", "app_release"}, k = 1, mv = {1, 1, 16})
public final class HealthReportActivity extends BaseActivity<BasePresenter<?>> {
    private HashMap _$_findViewCache;

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
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_health_report_layout;
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [T, java.lang.String] */
    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        long userId = runTimeUtil.getUserId();
        RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
        if (GreenDaoUtil.queryWeekReportCount(userId, runTimeUtil2.getWeekStart()) > 0) {
            CardView lay_report_week = (CardView) _$_findCachedViewById(com.ido.life.R.id.lay_report_week);
            Intrinsics.checkExpressionValueIsNotNull(lay_report_week, "lay_report_week");
            lay_report_week.setVisibility(0);
            LinearLayout layout_parent = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_parent);
            Intrinsics.checkExpressionValueIsNotNull(layout_parent, "layout_parent");
            layout_parent.setVisibility(0);
            LinearLayout lay_empty = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_empty);
            Intrinsics.checkExpressionValueIsNotNull(lay_empty, "lay_empty");
            lay_empty.setVisibility(8);
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            RunTimeUtil runTimeUtil3 = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil3, "RunTimeUtil.getInstance()");
            calendar.setFirstDayOfWeek(runTimeUtil3.getWeekStart());
            calendar.set(7, calendar.getFirstDayOfWeek());
            calendar.add(5, -7);
            String str = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DMY_1);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD_2);
            calendar.add(5, 6);
            String str2 = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DMY_1);
            Intrinsics.checkExpressionValueIsNotNull(str2, "DateUtil.format(calendar…teUtil.DATE_FORMAT_DMY_1)");
            if (str2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = str2.substring(0, 5);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            TextView tv_date = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_date);
            Intrinsics.checkExpressionValueIsNotNull(tv_date, "tv_date");
            tv_date.setText(str + '-' + strSubstring);
            ((CardView) _$_findCachedViewById(com.ido.life.R.id.lay_report_week)).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.user.healthreport.HealthReportActivity.initViews.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Intent intent = new Intent(HealthReportActivity.this, (Class<?>) HealthReportNewActivity.class);
                    String date = HealthReportNewActivity.INSTANCE.getDATE();
                    String startDateJump = (String) objectRef.element;
                    Intrinsics.checkExpressionValueIsNotNull(startDateJump, "startDateJump");
                    intent.putExtra(date, StringsKt.replace$default(startDateJump, "/", "-", false, 4, (Object) null));
                    HealthReportActivity.this.startActivity(intent);
                }
            });
            return;
        }
        CardView lay_report_week2 = (CardView) _$_findCachedViewById(com.ido.life.R.id.lay_report_week);
        Intrinsics.checkExpressionValueIsNotNull(lay_report_week2, "lay_report_week");
        lay_report_week2.setVisibility(8);
        LinearLayout lay_empty2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_empty);
        Intrinsics.checkExpressionValueIsNotNull(lay_empty2, "lay_empty");
        lay_empty2.setVisibility(0);
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        LinearLayout lay_empty = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_empty);
        Intrinsics.checkExpressionValueIsNotNull(lay_empty, "lay_empty");
        if (lay_empty.getVisibility() == 0) {
            TextView tv_week_report_nodata = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_week_report_nodata);
            Intrinsics.checkExpressionValueIsNotNull(tv_week_report_nodata, "tv_week_report_nodata");
            tv_week_report_nodata.setText(getLanguageText(R.string.week_report_nodata));
        } else {
            RegularTextView tv_title = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_title);
            Intrinsics.checkExpressionValueIsNotNull(tv_title, "tv_title");
            tv_title.setText(LanguageUtil.getLanguageText(R.string.health_report));
        }
        this.mTitleBar.setTitle(LanguageUtil.getLanguageText(R.string.health_week_report));
    }
}