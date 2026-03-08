package com.ido.life.customview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.fitness.FitnessActivities;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.R;
import com.ido.life.constants.Constants;
import com.ido.life.location.MapHelper;
import com.ido.life.module.home.menstrualcycle.CustomGridLayoutManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MenstruationCalendar.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\f\u0094\u0001\u0095\u0001\u0096\u0001\u0097\u0001\u0098\u0001\u0099\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010}\u001a\u00020\u00112\u0006\u0010~\u001a\u00020\u0018J\u000e\u0010\u007f\u001a\u00020\u00112\u0006\u0010~\u001a\u00020\u0018J\u0013\u0010\u0080\u0001\u001a\u00020\u00182\b\u0010\u0081\u0001\u001a\u00030\u0082\u0001H\u0002J\u0013\u0010\u0083\u0001\u001a\u00020\u00182\b\u0010\u0081\u0001\u001a\u00030\u0082\u0001H\u0002J\u0013\u0010\u0084\u0001\u001a\u00020\u00182\b\u0010\u0081\u0001\u001a\u00030\u0082\u0001H\u0002J\u000f\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020\"0!H\u0002J\n\u0010\u0086\u0001\u001a\u00030\u0087\u0001H\u0002J\n\u0010\u0088\u0001\u001a\u00030\u0087\u0001H\u0016J\u0014\u0010\u0089\u0001\u001a\u00030\u0087\u00012\b\u0010\u008a\u0001\u001a\u00030\u008b\u0001H\u0016J\u001c\u0010\u008c\u0001\u001a\u00030\u0087\u00012\u0007\u0010\u008d\u0001\u001a\u00020\u00182\u0007\u0010\u008e\u0001\u001a\u00020\u0018H\u0014J\u0011\u0010\u008f\u0001\u001a\u00030\u0087\u00012\u0007\u0010\u0090\u0001\u001a\u00020\u0011J\u0014\u0010\u0091\u0001\u001a\u00030\u0087\u00012\b\u0010\u0092\u0001\u001a\u00030\u0093\u0001H\u0002R\u0012\u0010\b\u001a\u00060\tR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R&\u0010\u0012\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u00118G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0019\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010)\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010*\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010+\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010,\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001b\"\u0004\b.\u0010\u001dR&\u0010/\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001b\"\u0004\b1\u0010\u001dR&\u00102\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u001b\"\u0004\b4\u0010\u001dR&\u00105\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u001b\"\u0004\b7\u0010\u001dR\u001a\u00108\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R&\u00109\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u001b\"\u0004\b;\u0010\u001dR\u001a\u0010<\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010=\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010>\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u00118C@CX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010?\u001a\u00060@R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010C\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u001b\"\u0004\bE\u0010\u001dR\u001a\u0010F\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010G\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010H\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u001b\"\u0004\bJ\u0010\u001dR\u001a\u0010K\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010L\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u001b\"\u0004\bN\u0010\u001dR&\u0010O\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010\u001b\"\u0004\bQ\u0010\u001dR&\u0010R\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\u001b\"\u0004\bT\u0010\u001dR&\u0010U\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010\u001b\"\u0004\bW\u0010\u001dR&\u0010X\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010\u001b\"\u0004\bZ\u0010\u001dR\u001a\u0010[\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\\\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010]\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010\u001b\"\u0004\b_\u0010\u001dR&\u0010`\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010\u001b\"\u0004\bb\u0010\u001dR\u001a\u0010c\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010d\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010e\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010f\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010g\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010h\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010i\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010j\u001a\u00020k¢\u0006\b\n\u0000\u001a\u0004\bl\u0010mR\u001a\u0010n\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u00118C@CX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010o\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u00118C@CX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010p\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u00118G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010\u0014\"\u0004\br\u0010\u0016R\u0014\u0010s\u001a\b\u0012\u0004\u0012\u00020\"0tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010u\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010v\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188C@CX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010w\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010\u001b\"\u0004\by\u0010\u001dR&\u0010z\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b{\u0010\u001b\"\u0004\b|\u0010\u001d¨\u0006\u009a\u0001"}, d2 = {"Lcom/ido/life/customview/MenstruationCalendar;", "Landroid/widget/LinearLayout;", "Landroid/view/View$OnClickListener;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mAdapter", "Lcom/ido/life/customview/MenstruationCalendar$CalendarAdapter;", "<set-?>", "Lcom/ido/life/customview/MenstruationCalendar$CalendarClickListener;", "mCalendarClickListener", "getCalendarCliekListener", "()Lcom/ido/life/customview/MenstruationCalendar$CalendarClickListener;", "setCalendarClickListener", "(Lcom/ido/life/customview/MenstruationCalendar$CalendarClickListener;)V", "", "mClickable", "isCanClick", "()Z", "setCanClick", "(Z)V", "mCurrentDay", "", "mCurrentMensOutColor", "getCurrentMensOutColor", "()I", "setCurrentMensOutColor", "(I)V", "mCurrentMonth", "mCurrentYear", "it", "", "Lcom/ido/life/customview/MenstruationCalendar$DateInfo;", "mDateList", "getDateList", "()Ljava/util/List;", "setDateList", "(Ljava/util/List;)V", "mDateTextColor", "mDateTextSize", "mDisableDateTextColor", "mDisableDateTextSize", "mFerTilePeriodBackgroundColor", "getFerTilePeriodBackgroundColor", "setFerTilePeriodBackgroundColor", "mFerTilePeriodBackgroundRadius", "getFerTilePeriodBackgroundRadius", "setFerTilePeriodBackgroundRadius", "mFerTilePeriodTextBackgroundPaddingBottom", "getFerTilePeriodTextBackgroundPaddingBottom", "setFerTilePeriodTextBackgroundPaddingBottom", "mFerTilePeriodTextBackgroundPaddingLeft", "getFerTilePeriodTextBackgroundPaddingLeft", "setFerTilePeriodTextBackgroundPaddingLeft", "mFerTilePeriodTextBackgroundPaddingRight", "mFerTilePeriodTextBackgroundPaddingTop", "getFerTilePeriodTextBackgroundPaddingTop", "setFerTilePeriodTextBackgroundPaddingTop", "mFerTilePeriodTextColor", "mFerTilePeriodTextSize", "mFillEmptyDate", "mItemDecoration", "Lcom/ido/life/customview/MenstruationCalendar$ItemDecoration;", "mItemHeight", "mItemWidth", "mMenstrulationBackgroundColor", "getMenstrulationBackgroundColor", "setMenstrulationBackgroundColor", "mMenstrulationEmptyDateTextColor", "mMenstrulationEmptyDateTextSize", "mMenstrulationFutureBgResId", "getMenstrulationFutureResId", "setMenstrulationFutureBgResId", "mMenstrulationFutureTextColor", "mMenstrulationLinkColor", "getMenstrulationLinkColor", "setMenstrulationLinkColor", "mMenstrulationLinkHeight", "getMenstrulationLinkHeight", "setMenstrulationLinkHeight", "mMenstrulationLinkPaddingLeft", "getMenstrulationLinkPaddingLeft", "setMenstrulationLinkPaddingLeft", "mMenstrulationLinkPaddingRight", "getMenstrulationLinkPaddingRight", "setMenstrulationLinkPaddingRight", "mMenstrulationLinkRadius", "getMenstrulationLinkRadius", "setMenstrulationLinkRadius", "mMenstrulationTextColor", "mMenstrulationTextSize", "mMonth", "getMonth", "setMonth", "mOvulationBackgroundColor", "getOvulationBackgroundColor", "setOvulationBackgroundColor", "mOvulationDayTextColor", "mOvulationDayTextSize", "mPredictionBackColor", "mPredictionBackRes", "mPredictionDateTextColor", "mPredictionDateTextPadding", "mPredictionDateTextSize", "mRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getMRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "mShowMenstrulationDateLink", "mShowTopWeek", "mTodayMensHighLight", "getTodayMensHighLight", "setTodayMensHighLight", "mTopLabelList", "Ljava/util/LinkedList;", "mTopWeekTextColor", "mTopWeekTextSize", "mWeekStart", "getWeekStart", "setWeekStart", "mYear", "getYear", "setYear", "endColumn", "dataIndex", "firstColumn", "getDateBackgroundColor", "type", "Lcom/ido/life/customview/MenstruationCalendar$Type;", "getDateTextColor", "getDateTextSize", "getTopWeekInfo", "initData", "", "invalidate", "onClick", "v", "Landroid/view/View;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "refreshCalendar", "animator", "resolveAttributes", "typeArray", "Landroid/content/res/TypedArray;", "CalendarAdapter", "CalendarClickListener", "CalendarViewHolder", "DateInfo", "ItemDecoration", "Type", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MenstruationCalendar extends LinearLayout implements View.OnClickListener {
    private HashMap _$_findViewCache;
    private CalendarAdapter mAdapter;
    private CalendarClickListener mCalendarClickListener;
    private boolean mClickable;
    private int mCurrentDay;
    private int mCurrentMensOutColor;
    private int mCurrentMonth;
    private int mCurrentYear;
    private List<DateInfo> mDateList;
    private int mDateTextColor;
    private int mDateTextSize;
    private int mDisableDateTextColor;
    private int mDisableDateTextSize;
    private int mFerTilePeriodBackgroundColor;
    private int mFerTilePeriodBackgroundRadius;
    private int mFerTilePeriodTextBackgroundPaddingBottom;
    private int mFerTilePeriodTextBackgroundPaddingLeft;
    private int mFerTilePeriodTextBackgroundPaddingRight;
    private int mFerTilePeriodTextBackgroundPaddingTop;
    private int mFerTilePeriodTextColor;
    private int mFerTilePeriodTextSize;
    private boolean mFillEmptyDate;
    private ItemDecoration mItemDecoration;
    private int mItemHeight;
    private int mItemWidth;
    private int mMenstrulationBackgroundColor;
    private int mMenstrulationEmptyDateTextColor;
    private int mMenstrulationEmptyDateTextSize;
    private int mMenstrulationFutureBgResId;
    private int mMenstrulationFutureTextColor;
    private int mMenstrulationLinkColor;
    private int mMenstrulationLinkHeight;
    private int mMenstrulationLinkPaddingLeft;
    private int mMenstrulationLinkPaddingRight;
    private int mMenstrulationLinkRadius;
    private int mMenstrulationTextColor;
    private int mMenstrulationTextSize;
    private int mMonth;
    private int mOvulationBackgroundColor;
    private int mOvulationDayTextColor;
    private int mOvulationDayTextSize;
    private int mPredictionBackColor;
    private int mPredictionBackRes;
    private int mPredictionDateTextColor;
    private int mPredictionDateTextPadding;
    private int mPredictionDateTextSize;
    private final RecyclerView mRecyclerView;
    private boolean mShowMenstrulationDateLink;
    private boolean mShowTopWeek;
    private boolean mTodayMensHighLight;
    private final LinkedList<DateInfo> mTopLabelList;
    private int mTopWeekTextColor;
    private int mTopWeekTextSize;
    private int mWeekStart;
    private int mYear;

    /* JADX INFO: compiled from: MenstruationCalendar.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/ido/life/customview/MenstruationCalendar$CalendarClickListener;", "", "onDateClickListenter", "", Constants.AppPackage.CALENDAR, "Lcom/ido/life/customview/MenstruationCalendar;", "index", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface CalendarClickListener {
        void onDateClickListenter(MenstruationCalendar calendar, int index);
    }

    /* JADX INFO: compiled from: MenstruationCalendar.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/ido/life/customview/MenstruationCalendar$Type;", "", "(Ljava/lang/String;I)V", "WEEK", "FILL", "NORMAL", "MENSTRUATION", "CONTRACTION_FUTURE", "PREDICTION", "OVULATION", "FERTILE_PERIOD", "DISABLE", "app_release"}, k = 1, mv = {1, 1, 16})
    public enum Type {
        WEEK,
        FILL,
        NORMAL,
        MENSTRUATION,
        CONTRACTION_FUTURE,
        PREDICTION,
        OVULATION,
        FERTILE_PERIOD,
        DISABLE
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            $EnumSwitchMapping$0[Type.WEEK.ordinal()] = 1;
            $EnumSwitchMapping$0[Type.FILL.ordinal()] = 2;
            $EnumSwitchMapping$0[Type.NORMAL.ordinal()] = 3;
            $EnumSwitchMapping$0[Type.MENSTRUATION.ordinal()] = 4;
            $EnumSwitchMapping$0[Type.PREDICTION.ordinal()] = 5;
            $EnumSwitchMapping$0[Type.OVULATION.ordinal()] = 6;
            $EnumSwitchMapping$0[Type.FERTILE_PERIOD.ordinal()] = 7;
            $EnumSwitchMapping$0[Type.DISABLE.ordinal()] = 8;
            $EnumSwitchMapping$0[Type.CONTRACTION_FUTURE.ordinal()] = 9;
            $EnumSwitchMapping$1 = new int[Type.values().length];
            $EnumSwitchMapping$1[Type.WEEK.ordinal()] = 1;
            $EnumSwitchMapping$1[Type.FILL.ordinal()] = 2;
            $EnumSwitchMapping$1[Type.NORMAL.ordinal()] = 3;
            $EnumSwitchMapping$1[Type.MENSTRUATION.ordinal()] = 4;
            $EnumSwitchMapping$1[Type.PREDICTION.ordinal()] = 5;
            $EnumSwitchMapping$1[Type.OVULATION.ordinal()] = 6;
            $EnumSwitchMapping$1[Type.FERTILE_PERIOD.ordinal()] = 7;
            $EnumSwitchMapping$1[Type.DISABLE.ordinal()] = 8;
            $EnumSwitchMapping$2 = new int[Type.values().length];
            $EnumSwitchMapping$2[Type.MENSTRUATION.ordinal()] = 1;
            $EnumSwitchMapping$2[Type.PREDICTION.ordinal()] = 2;
            $EnumSwitchMapping$2[Type.OVULATION.ordinal()] = 3;
        }
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
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MenstruationCalendar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mFillEmptyDate = true;
        this.mShowTopWeek = true;
        this.mTopWeekTextSize = 12;
        this.mTopWeekTextColor = -7829368;
        this.mDateTextSize = 14;
        this.mDateTextColor = ViewCompat.MEASURED_STATE_MASK;
        this.mMenstrulationTextColor = -1;
        this.mMenstrulationTextSize = 14;
        this.mMenstrulationBackgroundColor = -65536;
        this.mShowMenstrulationDateLink = true;
        this.mMenstrulationLinkColor = -65536;
        this.mMenstrulationLinkHeight = 4;
        this.mMenstrulationLinkPaddingLeft = 4;
        this.mMenstrulationLinkPaddingRight = 4;
        this.mMenstrulationLinkRadius = 4;
        this.mPredictionDateTextColor = -16776961;
        this.mPredictionDateTextSize = 14;
        this.mPredictionBackColor = -16776961;
        this.mPredictionBackRes = -1;
        this.mPredictionDateTextPadding = 4;
        this.mFerTilePeriodTextSize = 14;
        this.mFerTilePeriodTextColor = MapHelper.Standard_Color;
        this.mFerTilePeriodTextBackgroundPaddingTop = 4;
        this.mFerTilePeriodTextBackgroundPaddingBottom = 4;
        this.mFerTilePeriodTextBackgroundPaddingLeft = 4;
        this.mFerTilePeriodTextBackgroundPaddingRight = 4;
        this.mFerTilePeriodBackgroundColor = MapHelper.Standard_Color;
        this.mFerTilePeriodBackgroundRadius = 4;
        this.mMenstrulationEmptyDateTextColor = -7829368;
        this.mMenstrulationEmptyDateTextSize = 14;
        this.mDisableDateTextSize = 14;
        this.mDisableDateTextColor = -7829368;
        this.mOvulationDayTextColor = ViewCompat.MEASURED_STATE_MASK;
        this.mMenstrulationFutureTextColor = ViewCompat.MEASURED_STATE_MASK;
        this.mOvulationDayTextSize = 14;
        this.mCurrentMensOutColor = -7829368;
        this.mOvulationBackgroundColor = -7829368;
        this.mMenstrulationFutureBgResId = -1;
        this.mDateList = new ArrayList();
        this.mTopLabelList = new LinkedList<>();
        this.mAdapter = new CalendarAdapter();
        this.mItemDecoration = new ItemDecoration();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MenstrulationCalendar);
        Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…le.MenstrulationCalendar)");
        resolveAttributes(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        LayoutInflater.from(context).inflate(com.boat.Xtend.two.R.layout.menstrulation_calendar_layout, (ViewGroup) this, true);
        View viewFindViewById = findViewById(com.boat.Xtend.two.R.id.recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.recycler_view)");
        this.mRecyclerView = (RecyclerView) viewFindViewById;
        this.mRecyclerView.setLayoutManager(new CustomGridLayoutManager(context, 7, 1, false));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.addItemDecoration(this.mItemDecoration);
        setGravity(17);
        initData();
    }

    /* JADX INFO: renamed from: isCanClick, reason: from getter */
    public final boolean getMClickable() {
        return this.mClickable;
    }

    public final void setCanClick(boolean z) {
        this.mClickable = z;
    }

    /* JADX INFO: renamed from: getMenstrulationBackgroundColor, reason: from getter */
    public final int getMMenstrulationBackgroundColor() {
        return this.mMenstrulationBackgroundColor;
    }

    public final void setMenstrulationBackgroundColor(int i) {
        this.mMenstrulationBackgroundColor = i;
    }

    /* JADX INFO: renamed from: getMenstrulationLinkColor, reason: from getter */
    public final int getMMenstrulationLinkColor() {
        return this.mMenstrulationLinkColor;
    }

    public final void setMenstrulationLinkColor(int i) {
        this.mMenstrulationLinkColor = i;
    }

    /* JADX INFO: renamed from: getMenstrulationLinkHeight, reason: from getter */
    public final int getMMenstrulationLinkHeight() {
        return this.mMenstrulationLinkHeight;
    }

    public final void setMenstrulationLinkHeight(int i) {
        this.mMenstrulationLinkHeight = i;
    }

    /* JADX INFO: renamed from: getMenstrulationLinkPaddingLeft, reason: from getter */
    public final int getMMenstrulationLinkPaddingLeft() {
        return this.mMenstrulationLinkPaddingLeft;
    }

    public final void setMenstrulationLinkPaddingLeft(int i) {
        this.mMenstrulationLinkPaddingLeft = i;
    }

    /* JADX INFO: renamed from: getMenstrulationLinkPaddingRight, reason: from getter */
    public final int getMMenstrulationLinkPaddingRight() {
        return this.mMenstrulationLinkPaddingRight;
    }

    public final void setMenstrulationLinkPaddingRight(int i) {
        this.mMenstrulationLinkPaddingRight = i;
    }

    /* JADX INFO: renamed from: getMenstrulationLinkRadius, reason: from getter */
    public final int getMMenstrulationLinkRadius() {
        return this.mMenstrulationLinkRadius;
    }

    public final void setMenstrulationLinkRadius(int i) {
        this.mMenstrulationLinkRadius = i;
    }

    /* JADX INFO: renamed from: getFerTilePeriodTextBackgroundPaddingTop, reason: from getter */
    public final int getMFerTilePeriodTextBackgroundPaddingTop() {
        return this.mFerTilePeriodTextBackgroundPaddingTop;
    }

    public final void setFerTilePeriodTextBackgroundPaddingTop(int i) {
        this.mFerTilePeriodTextBackgroundPaddingTop = i;
    }

    /* JADX INFO: renamed from: getFerTilePeriodTextBackgroundPaddingBottom, reason: from getter */
    public final int getMFerTilePeriodTextBackgroundPaddingBottom() {
        return this.mFerTilePeriodTextBackgroundPaddingBottom;
    }

    public final void setFerTilePeriodTextBackgroundPaddingBottom(int i) {
        this.mFerTilePeriodTextBackgroundPaddingBottom = i;
    }

    /* JADX INFO: renamed from: getFerTilePeriodTextBackgroundPaddingLeft, reason: from getter */
    public final int getMFerTilePeriodTextBackgroundPaddingLeft() {
        return this.mFerTilePeriodTextBackgroundPaddingLeft;
    }

    public final void setFerTilePeriodTextBackgroundPaddingLeft(int i) {
        this.mFerTilePeriodTextBackgroundPaddingLeft = i;
    }

    /* JADX INFO: renamed from: getFerTilePeriodBackgroundColor, reason: from getter */
    public final int getMFerTilePeriodBackgroundColor() {
        return this.mFerTilePeriodBackgroundColor;
    }

    public final void setFerTilePeriodBackgroundColor(int i) {
        this.mFerTilePeriodBackgroundColor = i;
    }

    /* JADX INFO: renamed from: getFerTilePeriodBackgroundRadius, reason: from getter */
    public final int getMFerTilePeriodBackgroundRadius() {
        return this.mFerTilePeriodBackgroundRadius;
    }

    public final void setFerTilePeriodBackgroundRadius(int i) {
        this.mFerTilePeriodBackgroundRadius = i;
    }

    /* JADX INFO: renamed from: getCurrentMensOutColor, reason: from getter */
    public final int getMCurrentMensOutColor() {
        return this.mCurrentMensOutColor;
    }

    public final void setCurrentMensOutColor(int i) {
        this.mCurrentMensOutColor = i;
    }

    /* JADX INFO: renamed from: getOvulationBackgroundColor, reason: from getter */
    public final int getMOvulationBackgroundColor() {
        return this.mOvulationBackgroundColor;
    }

    public final void setOvulationBackgroundColor(int i) {
        this.mOvulationBackgroundColor = i;
    }

    /* JADX INFO: renamed from: getMenstrulationFutureResId, reason: from getter */
    public final int getMMenstrulationFutureBgResId() {
        return this.mMenstrulationFutureBgResId;
    }

    public final void setMenstrulationFutureBgResId(int i) {
        this.mMenstrulationFutureBgResId = i;
    }

    public final List<DateInfo> getDateList() {
        return this.mDateList;
    }

    public final void setDateList(List<DateInfo> it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        this.mDateList.clear();
        if (this.mShowTopWeek) {
            if (this.mTopLabelList.isEmpty()) {
                this.mTopLabelList.addAll(getTopWeekInfo());
            }
            this.mDateList.addAll(this.mTopLabelList);
        }
        List<DateInfo> list = it;
        if (!list.isEmpty()) {
            this.mDateList.addAll(list);
        }
    }

    public final RecyclerView getMRecyclerView() {
        return this.mRecyclerView;
    }

    /* JADX INFO: renamed from: getCalendarCliekListener, reason: from getter */
    public final CalendarClickListener getMCalendarClickListener() {
        return this.mCalendarClickListener;
    }

    public final void setCalendarClickListener(CalendarClickListener calendarClickListener) {
        this.mCalendarClickListener = calendarClickListener;
    }

    /* JADX INFO: renamed from: getWeekStart, reason: from getter */
    public final int getMWeekStart() {
        return this.mWeekStart;
    }

    public final void setWeekStart(int i) {
        this.mWeekStart = i;
    }

    /* JADX INFO: renamed from: getYear, reason: from getter */
    public final int getMYear() {
        return this.mYear;
    }

    public final void setYear(int i) {
        this.mYear = i;
    }

    /* JADX INFO: renamed from: getMonth, reason: from getter */
    public final int getMMonth() {
        return this.mMonth;
    }

    public final void setMonth(int i) {
        this.mMonth = i;
    }

    /* JADX INFO: renamed from: getTodayMensHighLight, reason: from getter */
    public final boolean getMTodayMensHighLight() {
        return this.mTodayMensHighLight;
    }

    public final void setTodayMensHighLight(boolean z) {
        this.mTodayMensHighLight = z;
    }

    private final void initData() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        this.mCurrentYear = calendar.get(1);
        this.mCurrentMonth = calendar.get(2);
        this.mCurrentDay = calendar.get(5);
    }

    private final void resolveAttributes(TypedArray typeArray) {
        this.mFillEmptyDate = typeArray.getBoolean(5, this.mFillEmptyDate);
        this.mMenstrulationEmptyDateTextColor = typeArray.getColor(3, this.mMenstrulationEmptyDateTextColor);
        this.mMenstrulationEmptyDateTextSize = typeArray.getDimensionPixelSize(4, this.mMenstrulationEmptyDateTextSize);
        this.mClickable = typeArray.getBoolean(0, this.mClickable);
        this.mShowTopWeek = typeArray.getBoolean(7, this.mShowTopWeek);
        this.mTopWeekTextSize = typeArray.getDimensionPixelSize(9, this.mTopWeekTextSize);
        this.mTopWeekTextColor = typeArray.getColor(8, this.mTopWeekTextColor);
        this.mDateTextSize = typeArray.getDimensionPixelSize(2, this.mDateTextSize);
        this.mDateTextColor = typeArray.getColor(1, this.mDateTextColor);
        this.mMenstrulationTextColor = typeArray.getColor(14, this.mMenstrulationTextColor);
        this.mMenstrulationTextSize = typeArray.getDimensionPixelSize(21, this.mMenstrulationTextSize);
        this.mMenstrulationBackgroundColor = typeArray.getColor(10, this.mMenstrulationBackgroundColor);
        this.mShowMenstrulationDateLink = typeArray.getBoolean(16, this.mShowMenstrulationDateLink);
        this.mMenstrulationLinkColor = typeArray.getColor(15, this.mMenstrulationLinkColor);
        this.mMenstrulationLinkHeight = typeArray.getDimensionPixelSize(17, this.mMenstrulationLinkHeight);
        this.mMenstrulationLinkPaddingLeft = typeArray.getDimensionPixelSize(18, this.mMenstrulationLinkPaddingLeft);
        this.mMenstrulationLinkPaddingRight = typeArray.getDimensionPixelSize(19, this.mMenstrulationLinkPaddingRight);
        this.mMenstrulationLinkRadius = typeArray.getDimensionPixelSize(20, this.mMenstrulationLinkRadius);
        this.mPredictionDateTextColor = typeArray.getColor(37, this.mPredictionDateTextColor);
        this.mPredictionDateTextSize = typeArray.getDimensionPixelSize(39, this.mPredictionDateTextSize);
        this.mPredictionBackColor = typeArray.getColor(36, this.mPredictionBackColor);
        this.mPredictionDateTextPadding = typeArray.getDimensionPixelSize(38, this.mPredictionDateTextPadding);
        this.mFerTilePeriodTextColor = typeArray.getColor(24, this.mFerTilePeriodTextColor);
        this.mFerTilePeriodTextSize = typeArray.getDimensionPixelSize(25, this.mFerTilePeriodTextSize);
        this.mFerTilePeriodBackgroundColor = typeArray.getColor(22, this.mFerTilePeriodBackgroundColor);
        this.mFerTilePeriodBackgroundRadius = typeArray.getDimensionPixelSize(23, this.mFerTilePeriodBackgroundRadius);
        this.mFerTilePeriodTextBackgroundPaddingLeft = typeArray.getDimensionPixelSize(27, this.mFerTilePeriodTextBackgroundPaddingLeft);
        this.mFerTilePeriodTextBackgroundPaddingRight = typeArray.getDimensionPixelSize(28, this.mFerTilePeriodTextBackgroundPaddingRight);
        this.mFerTilePeriodTextBackgroundPaddingTop = typeArray.getDimensionPixelSize(29, this.mFerTilePeriodTextBackgroundPaddingTop);
        this.mFerTilePeriodTextBackgroundPaddingBottom = typeArray.getDimensionPixelSize(26, this.mFerTilePeriodTextBackgroundPaddingBottom);
        this.mDisableDateTextColor = typeArray.getColor(11, this.mDisableDateTextColor);
        this.mDisableDateTextSize = typeArray.getDimensionPixelSize(12, this.mDisableDateTextSize);
        this.mPredictionBackRes = typeArray.getResourceId(35, this.mPredictionBackRes);
        this.mOvulationDayTextSize = typeArray.getDimensionPixelSize(34, this.mOvulationDayTextSize);
        this.mOvulationDayTextColor = typeArray.getColor(33, this.mOvulationDayTextColor);
        this.mCurrentMensOutColor = typeArray.getColor(13, this.mCurrentMensOutColor);
        this.mMenstrulationFutureTextColor = typeArray.getColor(31, this.mMenstrulationFutureTextColor);
        this.mMenstrulationFutureBgResId = typeArray.getResourceId(30, this.mMenstrulationFutureBgResId);
        this.mOvulationBackgroundColor = typeArray.getColor(32, this.mOvulationBackgroundColor);
        this.mTodayMensHighLight = typeArray.getBoolean(40, this.mTodayMensHighLight);
    }

    private final List<DateInfo> getTopWeekInfo() {
        ArrayList arrayList = new ArrayList();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setFirstDayOfWeek(this.mWeekStart);
        calendar.set(7, this.mWeekStart);
        for (int i = 1; i < 8; i++) {
            switch (calendar.get(7)) {
                case 2:
                    String languageText = LanguageUtil.getLanguageText(com.boat.Xtend.two.R.string.week_en_short_monday);
                    Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…ing.week_en_short_monday)");
                    arrayList.add(new DateInfo(2, languageText, Type.WEEK, false, Type.WEEK, 8, null));
                    break;
                case 3:
                    String languageText2 = LanguageUtil.getLanguageText(com.boat.Xtend.two.R.string.week_en_short_tuesday);
                    Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…ng.week_en_short_tuesday)");
                    arrayList.add(new DateInfo(3, languageText2, Type.WEEK, false, Type.WEEK, 8, null));
                    break;
                case 4:
                    String languageText3 = LanguageUtil.getLanguageText(com.boat.Xtend.two.R.string.week_en_short_wednesday);
                    Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage….week_en_short_wednesday)");
                    arrayList.add(new DateInfo(4, languageText3, Type.WEEK, false, Type.WEEK, 8, null));
                    break;
                case 5:
                    String languageText4 = LanguageUtil.getLanguageText(com.boat.Xtend.two.R.string.week_en_short_thursday);
                    Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…g.week_en_short_thursday)");
                    arrayList.add(new DateInfo(5, languageText4, Type.WEEK, false, Type.WEEK, 8, null));
                    break;
                case 6:
                    String languageText5 = LanguageUtil.getLanguageText(com.boat.Xtend.two.R.string.week_en_short_friday);
                    Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…ing.week_en_short_friday)");
                    arrayList.add(new DateInfo(6, languageText5, Type.WEEK, false, Type.WEEK, 8, null));
                    break;
                case 7:
                    String languageText6 = LanguageUtil.getLanguageText(com.boat.Xtend.two.R.string.week_en_short_saturday);
                    Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…g.week_en_short_saturday)");
                    arrayList.add(new DateInfo(7, languageText6, Type.WEEK, false, Type.WEEK, 8, null));
                    break;
                default:
                    String languageText7 = LanguageUtil.getLanguageText(com.boat.Xtend.two.R.string.week_en_short_sunday);
                    Intrinsics.checkExpressionValueIsNotNull(languageText7, "LanguageUtil.getLanguage…ing.week_en_short_sunday)");
                    arrayList.add(new DateInfo(1, languageText7, Type.WEEK, false, Type.WEEK, 8, null));
                    break;
            }
            calendar.add(7, 1);
        }
        return arrayList;
    }

    public final void refreshCalendar(boolean animator) {
        if (this.mRecyclerView.getItemDecorationCount() == 0) {
            this.mRecyclerView.addItemDecoration(this.mItemDecoration);
        }
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    @Override // android.view.View
    public void invalidate() {
        refreshCalendar(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        CalendarClickListener calendarClickListener;
        Intrinsics.checkParameterIsNotNull(v, "v");
        if (v.getId() == com.boat.Xtend.two.R.id.lay_out && (v.getTag() instanceof Integer)) {
            Object tag = v.getTag();
            if (tag == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
            int iIntValue = ((Integer) tag).intValue();
            if (iIntValue <= -1 || this.mDateList.size() <= iIntValue || (calendarClickListener = this.mCalendarClickListener) == null) {
                return;
            }
            calendarClickListener.onDateClickListenter(this, iIntValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getDateTextColor(Type type) {
        int i = this.mDateTextColor;
        switch (type) {
            case WEEK:
                return this.mTopWeekTextColor;
            case FILL:
                return this.mMenstrulationEmptyDateTextColor;
            case NORMAL:
                return this.mDateTextColor;
            case MENSTRUATION:
                return this.mMenstrulationTextColor;
            case PREDICTION:
                return this.mPredictionDateTextColor;
            case OVULATION:
                return this.mOvulationDayTextColor;
            case FERTILE_PERIOD:
                return this.mFerTilePeriodTextColor;
            case DISABLE:
                return this.mDisableDateTextColor;
            case CONTRACTION_FUTURE:
                int i2 = this.mMenstrulationFutureTextColor;
                return i;
            default:
                return i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getDateTextSize(Type type) {
        int i = this.mDateTextSize;
        switch (type) {
            case WEEK:
                return this.mTopWeekTextSize;
            case FILL:
                return this.mMenstrulationEmptyDateTextSize;
            case NORMAL:
                return this.mDateTextSize;
            case MENSTRUATION:
                return this.mMenstrulationTextSize;
            case PREDICTION:
                return this.mPredictionDateTextSize;
            case OVULATION:
                return this.mOvulationDayTextSize;
            case FERTILE_PERIOD:
                return this.mFerTilePeriodTextSize;
            case DISABLE:
                return this.mDisableDateTextSize;
            default:
                return i;
        }
    }

    private final int getDateBackgroundColor(Type type) {
        int i = WhenMappings.$EnumSwitchMapping$2[type.ordinal()];
        if (i == 1) {
            return this.mMenstrulationBackgroundColor;
        }
        if (i == 2) {
            return this.mPredictionBackColor;
        }
        if (i != 3) {
            return -1;
        }
        return this.mFerTilePeriodBackgroundColor;
    }

    public final boolean firstColumn(int dataIndex) {
        if (this.mDateList.isEmpty() || dataIndex < 0 || dataIndex >= this.mDateList.size()) {
            return false;
        }
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.recyclerview.widget.GridLayoutManager");
        }
        GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
        if (gridLayoutManager.getSpanCount() <= 0) {
            return false;
        }
        return dataIndex == 0 || dataIndex % gridLayoutManager.getSpanCount() == 0;
    }

    public final boolean endColumn(int dataIndex) {
        if (this.mDateList.isEmpty() || dataIndex < 0 || dataIndex >= this.mDateList.size()) {
            return false;
        }
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.recyclerview.widget.GridLayoutManager");
        }
        GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
        return gridLayoutManager.getSpanCount() > 0 && (dataIndex + 1) % gridLayoutManager.getSpanCount() == 0;
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int minimumWidth;
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        if (mode != 1073741824) {
            if (this.mRecyclerView.getMinimumWidth() > 0) {
                minimumWidth = this.mRecyclerView.getMinimumWidth();
            } else {
                Resources resources = getResources();
                Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
                minimumWidth = resources.getDisplayMetrics().widthPixels;
            }
            size = minimumWidth;
        }
        if (mode2 != 1073741824) {
            if (this.mItemHeight == 0) {
                View itemView = LayoutInflater.from(getContext()).inflate(com.boat.Xtend.two.R.layout.item_menstrulation_calendar_layout, (ViewGroup) null);
                itemView.measure(0, 0);
                Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
                this.mItemHeight = itemView.getMeasuredHeight();
                this.mItemWidth = itemView.getMeasuredWidth();
            }
            int i = this.mItemWidth * 7;
            ViewGroup.LayoutParams layoutParams = this.mRecyclerView.getLayoutParams();
            if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                layoutParams = null;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            int i2 = i + (marginLayoutParams != null ? marginLayoutParams.leftMargin : 0);
            ViewGroup.LayoutParams layoutParams2 = this.mRecyclerView.getLayoutParams();
            if (!(layoutParams2 instanceof ViewGroup.MarginLayoutParams)) {
                layoutParams2 = null;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams2;
            if (i2 + (marginLayoutParams2 != null ? marginLayoutParams2.rightMargin : 0) + getPaddingLeft() + getPaddingRight() > size) {
                ViewGroup.LayoutParams layoutParams3 = this.mRecyclerView.getLayoutParams();
                if (!(layoutParams3 instanceof ViewGroup.MarginLayoutParams)) {
                    layoutParams3 = null;
                }
                ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) layoutParams3;
                int i3 = size - (marginLayoutParams3 != null ? marginLayoutParams3.leftMargin : 0);
                ViewGroup.LayoutParams layoutParams4 = this.mRecyclerView.getLayoutParams();
                if (!(layoutParams4 instanceof ViewGroup.MarginLayoutParams)) {
                    layoutParams4 = null;
                }
                ViewGroup.MarginLayoutParams marginLayoutParams4 = (ViewGroup.MarginLayoutParams) layoutParams4;
                this.mItemWidth = (((i3 - (marginLayoutParams4 != null ? marginLayoutParams4.rightMargin : 0)) - getPaddingLeft()) - getPaddingRight()) / 7;
            }
            int size3 = this.mDateList.size();
            int i4 = this.mItemHeight;
            int i5 = (size3 / 7) * i4;
            if (size3 % 7 == 0) {
                i4 = 0;
            }
            size2 = i5 + i4;
        }
        int iMin = Math.min(size, Math.max(0, getMinimumWidth()));
        RecyclerView recyclerView = this.mRecyclerView;
        int paddingTop = (size2 - getPaddingTop()) - getPaddingBottom();
        ViewGroup.LayoutParams layoutParams5 = this.mRecyclerView.getLayoutParams();
        if (!(layoutParams5 instanceof ViewGroup.MarginLayoutParams)) {
            layoutParams5 = null;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams5 = (ViewGroup.MarginLayoutParams) layoutParams5;
        int i6 = paddingTop - (marginLayoutParams5 != null ? marginLayoutParams5.topMargin : 0);
        ViewGroup.LayoutParams layoutParams6 = this.mRecyclerView.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams6 = (ViewGroup.MarginLayoutParams) (layoutParams6 instanceof ViewGroup.MarginLayoutParams ? layoutParams6 : null);
        recyclerView.setMinimumHeight(i6 - (marginLayoutParams6 != null ? marginLayoutParams6.bottomMargin : 0));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(iMin, size2);
    }

    /* JADX INFO: compiled from: MenstruationCalendar.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\r\u001a\u00020\tH\u0016J\u001c\u0010\u000e\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\tH\u0016R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/ido/life/customview/MenstruationCalendar$CalendarAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/customview/MenstruationCalendar$CalendarViewHolder;", "Lcom/ido/life/customview/MenstruationCalendar;", "(Lcom/ido/life/customview/MenstruationCalendar;)V", "layoutInflater", "Landroid/view/LayoutInflater;", "kotlin.jvm.PlatformType", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "app_release"}, k = 1, mv = {1, 1, 16})
    public final class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
        private final LayoutInflater layoutInflater;

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                $EnumSwitchMapping$0[Type.NORMAL.ordinal()] = 1;
                $EnumSwitchMapping$0[Type.MENSTRUATION.ordinal()] = 2;
                $EnumSwitchMapping$0[Type.PREDICTION.ordinal()] = 3;
                $EnumSwitchMapping$0[Type.OVULATION.ordinal()] = 4;
                $EnumSwitchMapping$0[Type.FERTILE_PERIOD.ordinal()] = 5;
                $EnumSwitchMapping$0[Type.CONTRACTION_FUTURE.ordinal()] = 6;
                $EnumSwitchMapping$1 = new int[Type.values().length];
                $EnumSwitchMapping$1[Type.MENSTRUATION.ordinal()] = 1;
                $EnumSwitchMapping$1[Type.CONTRACTION_FUTURE.ordinal()] = 2;
                $EnumSwitchMapping$1[Type.PREDICTION.ordinal()] = 3;
                $EnumSwitchMapping$1[Type.OVULATION.ordinal()] = 4;
            }
        }

        public CalendarAdapter() {
            this.layoutInflater = LayoutInflater.from(MenstruationCalendar.this.getContext());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Intrinsics.checkParameterIsNotNull(parent, "parent");
            MenstruationCalendar menstruationCalendar = MenstruationCalendar.this;
            View viewInflate = this.layoutInflater.inflate(com.boat.Xtend.two.R.layout.item_menstrulation_calendar_layout, (ViewGroup) null);
            Intrinsics.checkExpressionValueIsNotNull(viewInflate, "layoutInflater.inflate(R…on_calendar_layout, null)");
            return new CalendarViewHolder(menstruationCalendar, viewInflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(CalendarViewHolder holder, int position) {
            Intrinsics.checkParameterIsNotNull(holder, "holder");
            DateInfo dateInfo = MenstruationCalendar.this.getDateList().get(position);
            holder.getMTextView().setText(dateInfo.getLabel());
            holder.getMLayContent().setBackground((Drawable) null);
            holder.getMLayContent().setBackgroundTintList((ColorStateList) null);
            holder.getMLayOutBg().setBackgroundTintList(ColorStateList.valueOf(0));
            holder.getMImgDot().setVisibility(4);
            holder.getMLayOut().setOnClickListener(null);
            if (MenstruationCalendar.this.getMClickable()) {
                switch (dateInfo.getType()) {
                    case NORMAL:
                    case MENSTRUATION:
                    case PREDICTION:
                    case OVULATION:
                    case FERTILE_PERIOD:
                    case CONTRACTION_FUTURE:
                        holder.getMLayOut().setOnClickListener(MenstruationCalendar.this);
                        holder.getMLayOut().setTag(Integer.valueOf(position));
                        break;
                }
            }
            holder.getMTextView().setTextColor(MenstruationCalendar.this.getDateTextColor(dateInfo.getType()));
            holder.getMTextView().setTextSize(0, MenstruationCalendar.this.getDateTextSize(dateInfo.getType()));
            int i = WhenMappings.$EnumSwitchMapping$1[dateInfo.getType().ordinal()];
            if (i != 1) {
                if (i == 2 || i == 3) {
                    holder.getMLayContent().setBackgroundResource(MenstruationCalendar.this.getMMenstrulationFutureBgResId());
                    return;
                } else {
                    if (i != 4) {
                        return;
                    }
                    holder.getMImgDot().setBackgroundTintList(ColorStateList.valueOf(MenstruationCalendar.this.getMOvulationBackgroundColor()));
                    holder.getMImgDot().setVisibility(0);
                    return;
                }
            }
            holder.getMLayContent().setBackgroundResource(com.boat.Xtend.two.R.drawable.circle_text_bg);
            holder.getMLayContent().setBackgroundTintList(ColorStateList.valueOf(MenstruationCalendar.this.getMMenstrulationBackgroundColor()));
            if (MenstruationCalendar.this.getMTodayMensHighLight() && MenstruationCalendar.this.mCurrentYear == MenstruationCalendar.this.getMYear() && MenstruationCalendar.this.mCurrentMonth == MenstruationCalendar.this.getMMonth() && MenstruationCalendar.this.mCurrentDay == dateInfo.getValue()) {
                holder.getMLayOutBg().setBackgroundTintList(ColorStateList.valueOf(MenstruationCalendar.this.getMCurrentMensOutColor()));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return MenstruationCalendar.this.getDateList().size();
        }
    }

    /* JADX INFO: compiled from: MenstruationCalendar.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/ido/life/customview/MenstruationCalendar$CalendarViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/ido/life/customview/MenstruationCalendar;Landroid/view/View;)V", "mImgDot", "Landroid/widget/ImageView;", "getMImgDot", "()Landroid/widget/ImageView;", "mLayContent", "Landroid/widget/LinearLayout;", "getMLayContent", "()Landroid/widget/LinearLayout;", "mLayOut", "getMLayOut", "mLayOutBg", "getMLayOutBg", "mTextView", "Landroid/widget/TextView;", "getMTextView", "()Landroid/widget/TextView;", "app_release"}, k = 1, mv = {1, 1, 16})
    public final class CalendarViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mImgDot;
        private final LinearLayout mLayContent;
        private final LinearLayout mLayOut;
        private final LinearLayout mLayOutBg;
        private final TextView mTextView;
        final /* synthetic */ MenstruationCalendar this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CalendarViewHolder(MenstruationCalendar menstruationCalendar, View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            this.this$0 = menstruationCalendar;
            View viewFindViewById = itemView.findViewById(com.boat.Xtend.two.R.id.tv_content);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "itemView.findViewById(R.id.tv_content)");
            this.mTextView = (TextView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(com.boat.Xtend.two.R.id.lay_content);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "itemView.findViewById(R.id.lay_content)");
            this.mLayContent = (LinearLayout) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(com.boat.Xtend.two.R.id.lay_out);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "itemView.findViewById(R.id.lay_out)");
            this.mLayOut = (LinearLayout) viewFindViewById3;
            View viewFindViewById4 = itemView.findViewById(com.boat.Xtend.two.R.id.lay_out_bg);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "itemView.findViewById(R.id.lay_out_bg)");
            this.mLayOutBg = (LinearLayout) viewFindViewById4;
            View viewFindViewById5 = itemView.findViewById(com.boat.Xtend.two.R.id.img_dot);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "itemView.findViewById(R.id.img_dot)");
            this.mImgDot = (ImageView) viewFindViewById5;
        }

        public final TextView getMTextView() {
            return this.mTextView;
        }

        public final LinearLayout getMLayContent() {
            return this.mLayContent;
        }

        public final LinearLayout getMLayOut() {
            return this.mLayOut;
        }

        public final LinearLayout getMLayOutBg() {
            return this.mLayOutBg;
        }

        public final ImageView getMImgDot() {
            return this.mImgDot;
        }
    }

    /* JADX INFO: compiled from: MenstruationCalendar.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/ido/life/customview/MenstruationCalendar$ItemDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "(Lcom/ido/life/customview/MenstruationCalendar;)V", "mPaint", "Landroid/graphics/Paint;", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "onDraw", "c", "Landroid/graphics/Canvas;", "app_release"}, k = 1, mv = {1, 1, 16})
    public final class ItemDecoration extends RecyclerView.ItemDecoration {
        private final Paint mPaint = new Paint();

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Type.values().length];

            static {
                $EnumSwitchMapping$0[Type.MENSTRUATION.ordinal()] = 1;
                $EnumSwitchMapping$0[Type.OVULATION.ordinal()] = 2;
                $EnumSwitchMapping$0[Type.FERTILE_PERIOD.ordinal()] = 3;
            }
        }

        public ItemDecoration() {
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setDither(true);
            this.mPaint.setAntiAlias(true);
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x00cb  */
        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onDraw(android.graphics.Canvas r21, androidx.recyclerview.widget.RecyclerView r22, androidx.recyclerview.widget.RecyclerView.State r23) {
            /*
                Method dump skipped, instruction units count: 2504
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.MenstruationCalendar.ItemDecoration.onDraw(android.graphics.Canvas, androidx.recyclerview.widget.RecyclerView, androidx.recyclerview.widget.RecyclerView$State):void");
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            Intrinsics.checkParameterIsNotNull(outRect, "outRect");
            Intrinsics.checkParameterIsNotNull(view, "view");
            Intrinsics.checkParameterIsNotNull(parent, "parent");
            Intrinsics.checkParameterIsNotNull(state, "state");
            outRect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    /* JADX INFO: compiled from: MenstruationCalendar.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001b\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J;\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0007HÆ\u0001J\u0013\u0010 \u001a\u00020\t2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\t\u0010#\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\n\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006$"}, d2 = {"Lcom/ido/life/customview/MenstruationCalendar$DateInfo;", "", "value", "", "label", "", "type", "Lcom/ido/life/customview/MenstruationCalendar$Type;", "autoLink", "", "originalType", "(ILjava/lang/String;Lcom/ido/life/customview/MenstruationCalendar$Type;ZLcom/ido/life/customview/MenstruationCalendar$Type;)V", "getAutoLink", "()Z", "setAutoLink", "(Z)V", "getLabel", "()Ljava/lang/String;", "getOriginalType", "()Lcom/ido/life/customview/MenstruationCalendar$Type;", "setOriginalType", "(Lcom/ido/life/customview/MenstruationCalendar$Type;)V", "getType", "setType", "getValue", "()I", "component1", "component2", "component3", "component4", "component5", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class DateInfo {
        private boolean autoLink;
        private final String label;
        private Type originalType;
        private Type type;
        private final int value;

        public static /* synthetic */ DateInfo copy$default(DateInfo dateInfo, int i, String str, Type type, boolean z, Type type2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = dateInfo.value;
            }
            if ((i2 & 2) != 0) {
                str = dateInfo.label;
            }
            String str2 = str;
            if ((i2 & 4) != 0) {
                type = dateInfo.type;
            }
            Type type3 = type;
            if ((i2 & 8) != 0) {
                z = dateInfo.autoLink;
            }
            boolean z2 = z;
            if ((i2 & 16) != 0) {
                type2 = dateInfo.originalType;
            }
            return dateInfo.copy(i, str2, type3, z2, type2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getValue() {
            return this.value;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Type getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getAutoLink() {
            return this.autoLink;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Type getOriginalType() {
            return this.originalType;
        }

        public final DateInfo copy(int value, String label, Type type, boolean autoLink, Type originalType) {
            Intrinsics.checkParameterIsNotNull(label, "label");
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(originalType, "originalType");
            return new DateInfo(value, label, type, autoLink, originalType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DateInfo)) {
                return false;
            }
            DateInfo dateInfo = (DateInfo) other;
            return this.value == dateInfo.value && Intrinsics.areEqual(this.label, dateInfo.label) && Intrinsics.areEqual(this.type, dateInfo.type) && this.autoLink == dateInfo.autoLink && Intrinsics.areEqual(this.originalType, dateInfo.originalType);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v12 */
        /* JADX WARN: Type inference failed for: r1v7, types: [int] */
        /* JADX WARN: Type inference failed for: r1v9 */
        public int hashCode() {
            int iHashCode = Integer.valueOf(this.value).hashCode() * 31;
            String str = this.label;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            Type type = this.type;
            int iHashCode3 = (iHashCode2 + (type != null ? type.hashCode() : 0)) * 31;
            boolean z = this.autoLink;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            int i = (iHashCode3 + r1) * 31;
            Type type2 = this.originalType;
            return i + (type2 != null ? type2.hashCode() : 0);
        }

        public String toString() {
            return "DateInfo(value=" + this.value + ", label=" + this.label + ", type=" + this.type + ", autoLink=" + this.autoLink + ", originalType=" + this.originalType + ")";
        }

        public DateInfo(int i, String label, Type type, boolean z, Type originalType) {
            Intrinsics.checkParameterIsNotNull(label, "label");
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(originalType, "originalType");
            this.value = i;
            this.label = label;
            this.type = type;
            this.autoLink = z;
            this.originalType = originalType;
        }

        public final int getValue() {
            return this.value;
        }

        public final String getLabel() {
            return this.label;
        }

        public final Type getType() {
            return this.type;
        }

        public final void setType(Type type) {
            Intrinsics.checkParameterIsNotNull(type, "<set-?>");
            this.type = type;
        }

        public /* synthetic */ DateInfo(int i, String str, Type type, boolean z, Type type2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, str, type, (i2 & 8) != 0 ? true : z, type2);
        }

        public final boolean getAutoLink() {
            return this.autoLink;
        }

        public final void setAutoLink(boolean z) {
            this.autoLink = z;
        }

        public final Type getOriginalType() {
            return this.originalType;
        }

        public final void setOriginalType(Type type) {
            Intrinsics.checkParameterIsNotNull(type, "<set-?>");
            this.originalType = type;
        }
    }
}