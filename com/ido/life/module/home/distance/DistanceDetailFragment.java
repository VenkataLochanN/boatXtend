package com.ido.life.module.home.distance;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.bean.BarChartPoint;
import com.ido.life.customview.charter.BarChartBar;
import com.ido.life.customview.charter.CustomChatBar;
import com.ido.life.database.model.SportDistanceBean;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder;
import com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback;
import com.ido.life.util.DateUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.UnitUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: DistanceDetailFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u001e\u0018\u0000 <2>\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00012\u00020\u00062\u00020\u0004:\u0001<B\u0005¢\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000bH\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0016J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\rH\u0014J\b\u0010\u0018\u001a\u00020\rH\u0016J\b\u0010\u0019\u001a\u00020\u0005H\u0016J\n\u0010\u001a\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u000fH\u0014J\b\u0010!\u001a\u00020\u001fH\u0016J\b\u0010\"\u001a\u00020\u000fH\u0016J\b\u0010#\u001a\u00020\u000fH\u0016J\u001e\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u001f2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001e\u0010'\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u001f2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001e\u0010)\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u001f2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001e\u0010+\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u001f2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\b\u0010-\u001a\u00020\u000fH\u0016J\u0012\u0010.\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010/\u001a\u00020\u000fH\u0014J\u0010\u00100\u001a\u00020\u001f2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0012\u00101\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u00102\u001a\u00020\u000fH\u0014J\u0010\u00103\u001a\u00020\u000f2\u0006\u00104\u001a\u00020\rH\u0016J\u0010\u00105\u001a\u00020\u000f2\u0006\u00106\u001a\u00020\rH\u0016J\b\u00107\u001a\u00020\u000fH\u0016J\b\u00108\u001a\u00020\u000fH\u0016J\b\u00109\u001a\u00020\u000fH\u0016J\b\u0010:\u001a\u00020\u000fH\u0016J\b\u0010;\u001a\u00020\u001fH\u0014¨\u0006="}, d2 = {"Lcom/ido/life/module/home/distance/DistanceDetailFragment;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailCoreFragment;", "", "Lcom/ido/life/bean/BarChartPoint;", "Lcom/ido/life/module/home/distance/IDistanceDetailView;", "Lcom/ido/life/module/home/distance/DistanceDetailPresenter;", "Lcom/ido/life/customview/charter/CustomChatBar$CaluteXGridLineCallback;", "()V", "calculateXGridLineValue", "", "target", "Landroid/view/View;", "index", "", "clearCache", "", "clickAction", "view", "dismissLoading", "getBottomView", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "context", "Landroid/content/Context;", "getLayoutResId", "getPageType", "getPresenter", "getTipLayContent", "getTipViewHolder", "getTopView", "hideSelectedUi", "resetDate", "", "initView", "needEventBus", "onBottomViewRefresh", "onDetailLoadFailed", "onLoadSuccessByDay", "showChartAnimator", "dayChartList", "onLoadSuccessByMonth", "monthChartList", "onLoadSuccessByWeek", "weekChartList", "onLoadSuccessByYear", "yearChartList", "onTopViewRefresh", "refreshBottomView", "refreshChart", "refreshChartTipView", "refreshTopView", "refreshUiByDateType", "setXMaxValue", "maxValue", "setXMinValue", "minValue", "showLoadFailedView", "showLoadSuccessView", "showLoading", "showLoadingView", "showTopRightLayout", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class DistanceDetailFragment extends BaseDetailCoreFragment<List<? extends BarChartPoint>, List<? extends BarChartPoint>, List<? extends BarChartPoint>, List<? extends BarChartPoint>, IDistanceDetailView, DistanceDetailPresenter> implements CustomChatBar.CaluteXGridLineCallback, IDistanceDetailView {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = DistanceDetailFragment.class.getSimpleName();
    private HashMap _$_findViewCache;

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
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

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getBottomView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return null;
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_distance_detail_layout;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public int getPageType() {
        return -1;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public boolean needEventBus() {
        return true;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public DistanceDetailPresenter getPresenter() {
        return new DistanceDetailPresenter(this);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, com.ido.common.base.BaseCoreFragment
    protected void initView() {
        super.initView();
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mXGridLineCallback = this;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected void refreshChart() {
        BarChartBar barChartBar;
        super.refreshChart();
        if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
            BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (barChartBar2 == null) {
                Intrinsics.throwNpe();
            }
            if (barChartBar2.getVisibility() != 0 || (barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
                return;
            }
            barChartBar.refreshChart(true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    protected void refreshTopView(Context context) {
        super.refreshTopView(context);
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof DistanceDetailTopViewHolder) {
            topViewHolder.refreshLanguage();
            topViewHolder.setDefaultValue();
            DistanceDetailTopViewHolder distanceDetailTopViewHolder = (DistanceDetailTopViewHolder) topViewHolder;
            TextView textView = distanceDetailTopViewHolder.mTvDate;
            Intrinsics.checkExpressionValueIsNotNull(textView, "topViewHolder.mTvDate");
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            textView.setText(((DistanceDetailPresenter) mPresenter).getDateText());
            TextView textView2 = distanceDetailTopViewHolder.mTvTotalDistance;
            Intrinsics.checkExpressionValueIsNotNull(textView2, "topViewHolder.mTvTotalDistance");
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            textView2.setText(new BigDecimal(String.valueOf(((DistanceDetailPresenter) mPresenter2).getMSportTotalDistance())).setScale(2, RoundingMode.HALF_UP).toString());
            TextView textView3 = distanceDetailTopViewHolder.mTvAvgDistance;
            Intrinsics.checkExpressionValueIsNotNull(textView3, "topViewHolder.mTvAvgDistance");
            T mPresenter3 = getMPresenter();
            if (mPresenter3 == 0) {
                Intrinsics.throwNpe();
            }
            textView3.setText(new BigDecimal(String.valueOf(((DistanceDetailPresenter) mPresenter3).getMSportAvgDistance())).setScale(2, RoundingMode.HALF_UP).toString());
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    protected void refreshBottomView(Context context) {
        super.refreshBottomView(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public boolean refreshChartTipView(int index) {
        BarChartPoint barChartPoint;
        SportDistanceBean sportDistanceBean;
        SportDistanceBean sportDistanceBean2;
        super.refreshChartTipView(index);
        if (index < 0) {
            return false;
        }
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack != null ? mCallBack.getTipViewHolder(this) : null;
        if (!(tipViewHolder instanceof DistanceDetailTipViewHolder)) {
            return false;
        }
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        int unitSet = ((DistanceDetailPresenter) mPresenter).getUnitSet();
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        List list = barChartBar != null ? barChartBar.getList() : null;
        if (list == null || list.size() <= index || (barChartPoint = (BarChartPoint) list.get(index)) == null) {
            return false;
        }
        if (unitSet == 2) {
            TextView mTvTipState = ((DistanceDetailTipViewHolder) tipViewHolder).getMTvTipState();
            if (mTvTipState != null) {
                mTvTipState.setText(getLanguageText(R.string.mile_short));
            }
        } else {
            TextView mTvTipState2 = ((DistanceDetailTipViewHolder) tipViewHolder).getMTvTipState();
            if (mTvTipState2 != null) {
                mTvTipState2.setText(getLanguageText(R.string.km_short));
            }
        }
        int mDateType = ((DistanceDetailPresenter) getMPresenter()).getMDateType();
        if (mDateType == 1) {
            IChartDetailCallback mCallBack2 = getMCallBack();
            if (mCallBack2 != null) {
                DistanceDetailFragment distanceDetailFragment = this;
                T mPresenter2 = getMPresenter();
                if (mPresenter2 == 0) {
                    Intrinsics.throwNpe();
                }
                mCallBack2.updateSelectDate(distanceDetailFragment, ((DistanceDetailPresenter) mPresenter2).getMStartDate());
                Unit unit = Unit.INSTANCE;
            }
            DistanceDetailTipViewHolder distanceDetailTipViewHolder = (DistanceDetailTipViewHolder) tipViewHolder;
            TextView mTvTipTitleAvg = distanceDetailTipViewHolder.getMTvTipTitleAvg();
            if (mTvTipTitleAvg != null) {
                mTvTipTitleAvg.setVisibility(8);
            }
            int iRoundToInt = MathKt.roundToInt(barChartPoint.x) - 1;
            int i = iRoundToInt + 1;
            float actualValue = barChartPoint.getActualValue();
            TextView mTvTipAvg = distanceDetailTipViewHolder.getMTvTipAvg();
            if (mTvTipAvg != null) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = {Float.valueOf(actualValue)};
                String str = String.format("%.2f", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                mTvTipAvg.setText(str);
            }
            TextView mTvTipDate = distanceDetailTipViewHolder.getMTvTipDate();
            if (mTvTipDate != null) {
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                Object[] objArr2 = {Integer.valueOf(iRoundToInt), Integer.valueOf(i)};
                String str2 = String.format("%02d:00-%02d:00", Arrays.copyOf(objArr2, objArr2.length));
                Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
                mTvTipDate.setText(str2);
            }
        } else if (mDateType == 2) {
            DistanceDetailTipViewHolder distanceDetailTipViewHolder2 = (DistanceDetailTipViewHolder) tipViewHolder;
            TextView mTvTipTitleAvg2 = distanceDetailTipViewHolder2.getMTvTipTitleAvg();
            if (mTvTipTitleAvg2 != null) {
                mTvTipTitleAvg2.setVisibility(8);
            }
            TextView mTvTipTitleAvg3 = distanceDetailTipViewHolder2.getMTvTipTitleAvg();
            if (mTvTipTitleAvg3 != null) {
                mTvTipTitleAvg3.setText(getLanguageText(R.string.detail_average_daily));
            }
            if (barChartPoint.hasEffect()) {
                TextView mTvTipAvg2 = distanceDetailTipViewHolder2.getMTvTipAvg();
                if (mTvTipAvg2 != null) {
                    StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                    Object[] objArr3 = {Float.valueOf(barChartPoint.getActualValue())};
                    String str3 = String.format("%.2f", Arrays.copyOf(objArr3, objArr3.length));
                    Intrinsics.checkNotNullExpressionValue(str3, "java.lang.String.format(format, *args)");
                    mTvTipAvg2.setText(str3);
                }
                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                T mPresenter3 = getMPresenter();
                if (mPresenter3 == 0) {
                    Intrinsics.throwNpe();
                }
                calendar.setTime(DateUtil.string2Date(((DistanceDetailPresenter) mPresenter3).getMStartDate(), DateUtil.DATE_FORMAT_YMD));
                calendar.add(5, MathKt.roundToInt(barChartPoint.x) - 1);
                TextView mTvTipDate2 = distanceDetailTipViewHolder2.getMTvTipDate();
                if (mTvTipDate2 != null) {
                    mTvTipDate2.setText(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DM_1));
                }
                IChartDetailCallback mCallBack3 = getMCallBack();
                if (mCallBack3 != null) {
                    mCallBack3.updateSelectDate(this, DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD));
                    Unit unit2 = Unit.INSTANCE;
                }
            } else {
                List<SportDistanceBean> sportList = ((DistanceDetailPresenter) getMPresenter()).getSportList();
                if (sportList != null && sportList.size() > barChartPoint.getIndex() && (sportDistanceBean = sportList.get(barChartPoint.getIndex())) != null) {
                    if (unitSet == 2) {
                        TextView mTvTipAvg3 = distanceDetailTipViewHolder2.getMTvTipAvg();
                        if (mTvTipAvg3 != null) {
                            StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                            Object[] objArr4 = {Float.valueOf(UnitUtil.km2mile(sportDistanceBean.getTotalDistance() / 1000))};
                            String str4 = String.format("%.2f", Arrays.copyOf(objArr4, objArr4.length));
                            Intrinsics.checkNotNullExpressionValue(str4, "java.lang.String.format(format, *args)");
                            mTvTipAvg3.setText(str4);
                        }
                    } else {
                        TextView mTvTipAvg4 = distanceDetailTipViewHolder2.getMTvTipAvg();
                        if (mTvTipAvg4 != null) {
                            StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
                            Object[] objArr5 = {Float.valueOf(sportDistanceBean.getTotalDistance() / 1000)};
                            String str5 = String.format("%.2f", Arrays.copyOf(objArr5, objArr5.length));
                            Intrinsics.checkNotNullExpressionValue(str5, "java.lang.String.format(format, *args)");
                            mTvTipAvg4.setText(str5);
                        }
                    }
                    String date = sportDistanceBean.getDate();
                    TextView mTvTipDate3 = distanceDetailTipViewHolder2.getMTvTipDate();
                    if (mTvTipDate3 != null) {
                        mTvTipDate3.setText(DateUtil.format(DateUtil.string2Date(date, DateUtil.DATE_FORMAT_YMD), DateUtil.DATE_FORMAT_DM_1));
                    }
                    IChartDetailCallback mCallBack4 = getMCallBack();
                    if (mCallBack4 != null) {
                        mCallBack4.updateSelectDate(this, date);
                        Unit unit3 = Unit.INSTANCE;
                    }
                }
            }
        } else if (mDateType == 3) {
            DistanceDetailTipViewHolder distanceDetailTipViewHolder3 = (DistanceDetailTipViewHolder) tipViewHolder;
            TextView mTvTipTitleAvg4 = distanceDetailTipViewHolder3.getMTvTipTitleAvg();
            if (mTvTipTitleAvg4 != null) {
                mTvTipTitleAvg4.setVisibility(8);
            }
            TextView mTvTipTitleAvg5 = distanceDetailTipViewHolder3.getMTvTipTitleAvg();
            if (mTvTipTitleAvg5 != null) {
                mTvTipTitleAvg5.setText(getLanguageText(R.string.detail_average_daily));
            }
            if (barChartPoint.hasEffect()) {
                TextView mTvTipAvg5 = distanceDetailTipViewHolder3.getMTvTipAvg();
                if (mTvTipAvg5 != null) {
                    StringCompanionObject stringCompanionObject6 = StringCompanionObject.INSTANCE;
                    Object[] objArr6 = {Float.valueOf(barChartPoint.getActualValue())};
                    String str6 = String.format("%.2f", Arrays.copyOf(objArr6, objArr6.length));
                    Intrinsics.checkNotNullExpressionValue(str6, "java.lang.String.format(format, *args)");
                    mTvTipAvg5.setText(str6);
                }
                int iRoundToInt2 = MathKt.roundToInt(barChartPoint.x);
                Calendar calendar2 = Calendar.getInstance(Locale.getDefault());
                Intrinsics.checkExpressionValueIsNotNull(calendar2, "calendar");
                T mPresenter4 = getMPresenter();
                if (mPresenter4 == 0) {
                    Intrinsics.throwNpe();
                }
                calendar2.setTime(DateUtil.string2Date(((DistanceDetailPresenter) mPresenter4).getMStartDate(), DateUtil.DATE_FORMAT_YMD));
                RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
                calendar2.setFirstDayOfWeek(runTimeUtil.getWeekStart());
                calendar2.set(5, iRoundToInt2);
                TextView mTvTipDate4 = distanceDetailTipViewHolder3.getMTvTipDate();
                if (mTvTipDate4 != null) {
                    mTvTipDate4.setText(DateUtil.format(calendar2.getTime(), DateUtil.DATE_FORMAT_DM_1));
                }
                IChartDetailCallback mCallBack5 = getMCallBack();
                if (mCallBack5 != null) {
                    mCallBack5.updateSelectDate(this, DateUtil.format(calendar2.getTime(), DateUtil.DATE_FORMAT_YMD));
                    Unit unit4 = Unit.INSTANCE;
                }
            } else {
                List<SportDistanceBean> sportList2 = ((DistanceDetailPresenter) getMPresenter()).getSportList();
                if (sportList2 != null && sportList2.size() > barChartPoint.getIndex() && (sportDistanceBean2 = sportList2.get(barChartPoint.getIndex())) != null) {
                    if (unitSet == 2) {
                        TextView mTvTipAvg6 = distanceDetailTipViewHolder3.getMTvTipAvg();
                        if (mTvTipAvg6 != null) {
                            StringCompanionObject stringCompanionObject7 = StringCompanionObject.INSTANCE;
                            Object[] objArr7 = {Float.valueOf(UnitUtil.km2mile(sportDistanceBean2.getTotalDistance() / 1000))};
                            String str7 = String.format("%.2f", Arrays.copyOf(objArr7, objArr7.length));
                            Intrinsics.checkNotNullExpressionValue(str7, "java.lang.String.format(format, *args)");
                            mTvTipAvg6.setText(str7);
                        }
                    } else {
                        TextView mTvTipAvg7 = distanceDetailTipViewHolder3.getMTvTipAvg();
                        if (mTvTipAvg7 != null) {
                            StringCompanionObject stringCompanionObject8 = StringCompanionObject.INSTANCE;
                            Object[] objArr8 = {Float.valueOf(sportDistanceBean2.getTotalDistance() / 1000)};
                            String str8 = String.format("%.2f", Arrays.copyOf(objArr8, objArr8.length));
                            Intrinsics.checkNotNullExpressionValue(str8, "java.lang.String.format(format, *args)");
                            mTvTipAvg7.setText(str8);
                        }
                    }
                    String date2 = sportDistanceBean2.getDate();
                    TextView mTvTipDate5 = distanceDetailTipViewHolder3.getMTvTipDate();
                    if (mTvTipDate5 != null) {
                        mTvTipDate5.setText(DateUtil.format(DateUtil.string2Date(date2, DateUtil.DATE_FORMAT_YMD), DateUtil.DATE_FORMAT_DM_1));
                    }
                    IChartDetailCallback mCallBack6 = getMCallBack();
                    if (mCallBack6 != null) {
                        mCallBack6.updateSelectDate(this, date2);
                        Unit unit5 = Unit.INSTANCE;
                    }
                }
            }
        } else if (mDateType == 4) {
            DistanceDetailTipViewHolder distanceDetailTipViewHolder4 = (DistanceDetailTipViewHolder) tipViewHolder;
            TextView mTvTipTitleAvg6 = distanceDetailTipViewHolder4.getMTvTipTitleAvg();
            if (mTvTipTitleAvg6 != null) {
                mTvTipTitleAvg6.setText(getLanguageText(R.string.detail_average_daily));
            }
            if (barChartPoint.hasEffect()) {
                TextView mTvTipAvg8 = distanceDetailTipViewHolder4.getMTvTipAvg();
                if (mTvTipAvg8 != null) {
                    StringCompanionObject stringCompanionObject9 = StringCompanionObject.INSTANCE;
                    Object[] objArr9 = {Float.valueOf(barChartPoint.getActualValue())};
                    String str9 = String.format("%.2f", Arrays.copyOf(objArr9, objArr9.length));
                    Intrinsics.checkNotNullExpressionValue(str9, "java.lang.String.format(format, *args)");
                    mTvTipAvg8.setText(str9);
                }
                Calendar calendar3 = Calendar.getInstance(Locale.getDefault());
                Intrinsics.checkExpressionValueIsNotNull(calendar3, "calendar");
                T mPresenter5 = getMPresenter();
                if (mPresenter5 == 0) {
                    Intrinsics.throwNpe();
                }
                calendar3.setTime(DateUtil.string2Date(((DistanceDetailPresenter) mPresenter5).getMStartDate(), DateUtil.DATE_FORMAT_YMD));
                RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
                calendar3.setFirstDayOfWeek(runTimeUtil2.getWeekStart());
                int i2 = calendar3.get(1);
                int iRoundToInt3 = MathKt.roundToInt(barChartPoint.x);
                TextView mTvTipDate6 = distanceDetailTipViewHolder4.getMTvTipDate();
                if (mTvTipDate6 != null) {
                    StringCompanionObject stringCompanionObject10 = StringCompanionObject.INSTANCE;
                    Object[] objArr10 = {Integer.valueOf(iRoundToInt3), Integer.valueOf(i2)};
                    String str10 = String.format("%02d/%d", Arrays.copyOf(objArr10, objArr10.length));
                    Intrinsics.checkNotNullExpressionValue(str10, "java.lang.String.format(format, *args)");
                    mTvTipDate6.setText(str10);
                }
                IChartDetailCallback mCallBack7 = getMCallBack();
                if (mCallBack7 != null) {
                    StringCompanionObject stringCompanionObject11 = StringCompanionObject.INSTANCE;
                    Object[] objArr11 = {Integer.valueOf(i2), Integer.valueOf(iRoundToInt3)};
                    String str11 = String.format("%d-%02d-01", Arrays.copyOf(objArr11, objArr11.length));
                    Intrinsics.checkNotNullExpressionValue(str11, "java.lang.String.format(format, *args)");
                    mCallBack7.updateSelectDate(this, str11);
                    Unit unit6 = Unit.INSTANCE;
                }
            } else {
                List<SportDistanceBean> sportList3 = ((DistanceDetailPresenter) getMPresenter()).getSportList();
                if (sportList3 != null && sportList3.size() > barChartPoint.getIndex()) {
                    SportDistanceBean sportDistanceBean3 = sportList3.get(barChartPoint.getIndex());
                    if (unitSet == 2) {
                        TextView mTvTipAvg9 = distanceDetailTipViewHolder4.getMTvTipAvg();
                        if (mTvTipAvg9 != null) {
                            StringCompanionObject stringCompanionObject12 = StringCompanionObject.INSTANCE;
                            Object[] objArr12 = new Object[1];
                            if (sportDistanceBean3 == null) {
                                Intrinsics.throwNpe();
                            }
                            objArr12[0] = Float.valueOf(UnitUtil.km2mile(sportDistanceBean3.getDayAvgDistance() / 1000));
                            String str12 = String.format("%.2f", Arrays.copyOf(objArr12, objArr12.length));
                            Intrinsics.checkNotNullExpressionValue(str12, "java.lang.String.format(format, *args)");
                            mTvTipAvg9.setText(str12);
                        }
                    } else {
                        TextView mTvTipAvg10 = distanceDetailTipViewHolder4.getMTvTipAvg();
                        if (mTvTipAvg10 != null) {
                            StringCompanionObject stringCompanionObject13 = StringCompanionObject.INSTANCE;
                            Object[] objArr13 = new Object[1];
                            if (sportDistanceBean3 == null) {
                                Intrinsics.throwNpe();
                            }
                            objArr13[0] = Float.valueOf(sportDistanceBean3.getDayAvgDistance() / 1000);
                            String str13 = String.format("%.2f", Arrays.copyOf(objArr13, objArr13.length));
                            Intrinsics.checkNotNullExpressionValue(str13, "java.lang.String.format(format, *args)");
                            mTvTipAvg10.setText(str13);
                        }
                    }
                    if (sportDistanceBean3 == null) {
                        Intrinsics.throwNpe();
                    }
                    String date3 = sportDistanceBean3.getDate();
                    TextView mTvTipDate7 = distanceDetailTipViewHolder4.getMTvTipDate();
                    if (mTvTipDate7 != null) {
                        mTvTipDate7.setText(DateUtil.format(DateUtil.string2Date(date3, DateUtil.DATE_FORMAT_YMD), DateUtil.DATE_FORMAT_MY));
                    }
                    IChartDetailCallback mCallBack8 = getMCallBack();
                    if (mCallBack8 != null) {
                        mCallBack8.updateSelectDate(this, date3);
                        Unit unit7 = Unit.INSTANCE;
                    }
                }
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByDay(boolean showChartAnimator, List<? extends BarChartPoint> dayChartList) {
        Intrinsics.checkParameterIsNotNull(dayChartList, "dayChartList");
        DistanceDetailPresenter distanceDetailPresenter = (DistanceDetailPresenter) getMPresenter();
        if (distanceDetailPresenter == null || distanceDetailPresenter.getMDateType() != 1 || this.mRootView == null || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar != null) {
            barChartBar.setList(dayChartList);
        }
        BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar2 != null) {
            barChartBar2.mYMinValue = ((DistanceDetailPresenter) getMPresenter()).getDefaultYMinValue();
        }
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        float fCalculateYMaxDistance = ((DistanceDetailPresenter) mPresenter).calculateYMaxDistance();
        BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar3 != null) {
            barChartBar3.mYMaxValue = fCalculateYMaxDistance;
        }
        BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar4 != null) {
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar4.setLabelYLeftList(((DistanceDetailPresenter) mPresenter2).getDefaultYLabelList(fCalculateYMaxDistance));
        }
        BarChartBar barChartBar5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar5 != null) {
            T mPresenter3 = getMPresenter();
            if (mPresenter3 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar5.mXMinValue = ((DistanceDetailPresenter) mPresenter3).getMXMinValue();
        }
        BarChartBar barChartBar6 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar6 != null) {
            T mPresenter4 = getMPresenter();
            if (mPresenter4 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar6.mXMaxValue = ((DistanceDetailPresenter) mPresenter4).getMXMaxValue();
        }
        BarChartBar barChartBar7 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar7 != null) {
            T mPresenter5 = getMPresenter();
            if (mPresenter5 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar7.setDefaultHeight(((DistanceDetailPresenter) mPresenter5).getMDefaultHeight());
        }
        BarChartBar barChartBar8 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar8 != null) {
            barChartBar8.refreshChart(showChartAnimator);
        }
        refreshTopView(getMActivity());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByWeek(boolean showChartAnimator, List<? extends BarChartPoint> weekChartList) {
        DistanceDetailPresenter distanceDetailPresenter;
        Intrinsics.checkParameterIsNotNull(weekChartList, "weekChartList");
        if (this.mRootView == null || (distanceDetailPresenter = (DistanceDetailPresenter) getMPresenter()) == null || distanceDetailPresenter.getMDateType() != 2 || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar != null) {
            barChartBar.setList(weekChartList);
        }
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        float fCalculateYMaxDistance = ((DistanceDetailPresenter) mPresenter).calculateYMaxDistance();
        BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar2 != null) {
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar2.setLabelYLeftList(((DistanceDetailPresenter) mPresenter2).getDefaultYLabelList(fCalculateYMaxDistance));
        }
        BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar3 != null) {
            barChartBar3.mYMaxValue = fCalculateYMaxDistance;
        }
        BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar4 != null) {
            T mPresenter3 = getMPresenter();
            if (mPresenter3 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar4.mXMinValue = ((DistanceDetailPresenter) mPresenter3).getMXMinValue();
        }
        BarChartBar barChartBar5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar5 != null) {
            T mPresenter4 = getMPresenter();
            if (mPresenter4 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar5.mXMaxValue = ((DistanceDetailPresenter) mPresenter4).getMXMaxValue();
        }
        BarChartBar barChartBar6 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar6 != null) {
            T mPresenter5 = getMPresenter();
            if (mPresenter5 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar6.setDefaultHeight(((DistanceDetailPresenter) mPresenter5).getMDefaultHeight());
        }
        BarChartBar barChartBar7 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar7 != null) {
            barChartBar7.refreshChart(showChartAnimator);
        }
        refreshTopView(getMActivity());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByMonth(boolean showChartAnimator, List<? extends BarChartPoint> monthChartList) {
        Intrinsics.checkParameterIsNotNull(monthChartList, "monthChartList");
        DistanceDetailPresenter distanceDetailPresenter = (DistanceDetailPresenter) getMPresenter();
        if (distanceDetailPresenter == null || distanceDetailPresenter.getMDateType() != 3 || this.mRootView == null || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar != null) {
            barChartBar.setList(monthChartList);
        }
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        float fCalculateYMaxDistance = ((DistanceDetailPresenter) mPresenter).calculateYMaxDistance();
        BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar2 != null) {
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar2.setLabelYLeftList(((DistanceDetailPresenter) mPresenter2).getDefaultYLabelList(fCalculateYMaxDistance));
        }
        BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar3 != null) {
            barChartBar3.mYMaxValue = fCalculateYMaxDistance;
        }
        BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar4 != null) {
            T mPresenter3 = getMPresenter();
            if (mPresenter3 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar4.mXMinValue = ((DistanceDetailPresenter) mPresenter3).getMXMinValue();
        }
        BarChartBar barChartBar5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar5 != null) {
            T mPresenter4 = getMPresenter();
            if (mPresenter4 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar5.mXMaxValue = ((DistanceDetailPresenter) mPresenter4).getMXMaxValue();
        }
        BarChartBar barChartBar6 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar6 != null) {
            T mPresenter5 = getMPresenter();
            if (mPresenter5 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar6.setDefaultHeight(((DistanceDetailPresenter) mPresenter5).getMDefaultHeight());
        }
        BarChartBar barChartBar7 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar7 != null) {
            barChartBar7.refreshChart(showChartAnimator);
        }
        refreshTopView(getMActivity());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByYear(boolean showChartAnimator, List<? extends BarChartPoint> yearChartList) {
        BarChartBar barChartBar;
        Intrinsics.checkParameterIsNotNull(yearChartList, "yearChartList");
        DistanceDetailPresenter distanceDetailPresenter = (DistanceDetailPresenter) getMPresenter();
        if (distanceDetailPresenter == null || distanceDetailPresenter.getMDateType() != 4 || this.mRootView == null || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar2 != null) {
            barChartBar2.setList(yearChartList);
        }
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        float fCalculateYMaxDistance = ((DistanceDetailPresenter) mPresenter).calculateYMaxDistance();
        BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar3 != null) {
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar3.setLabelYLeftList(((DistanceDetailPresenter) mPresenter2).getDefaultYLabelList(fCalculateYMaxDistance));
        }
        BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar4 != null) {
            barChartBar4.mYMaxValue = fCalculateYMaxDistance;
        }
        BarChartBar barChartBar5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar5 != null) {
            barChartBar5.mBottomTitle = (String) null;
        }
        T mPresenter3 = getMPresenter();
        if (mPresenter3 == 0) {
            Intrinsics.throwNpe();
        }
        int iYear = ((DistanceDetailPresenter) mPresenter3).year();
        if (iYear > 0 && (barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
            barChartBar.mBottomTitle = String.valueOf(iYear);
        }
        BarChartBar barChartBar6 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar6 != null) {
            T mPresenter4 = getMPresenter();
            if (mPresenter4 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar6.mXMinValue = ((DistanceDetailPresenter) mPresenter4).getMXMinValue();
        }
        BarChartBar barChartBar7 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar7 != null) {
            T mPresenter5 = getMPresenter();
            if (mPresenter5 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar7.mXMaxValue = ((DistanceDetailPresenter) mPresenter5).getMXMaxValue();
        }
        BarChartBar barChartBar8 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar8 != null) {
            T mPresenter6 = getMPresenter();
            if (mPresenter6 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar8.setDefaultHeight(((DistanceDetailPresenter) mPresenter6).getMDefaultHeight());
        }
        BarChartBar barChartBar9 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar9 != null) {
            barChartBar9.refreshChart(showChartAnimator);
        }
        refreshTopView(getMActivity());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onDetailLoadFailed() {
        BarChartBar barChartBar;
        if (this.mRootView == null || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar2 != null) {
            barChartBar2.setList(null);
        }
        BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar3 != null) {
            barChartBar3.mBottomTitle = (String) null;
        }
        if (((DistanceDetailPresenter) getMPresenter()).getMDateType() == 4) {
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            int iYear = ((DistanceDetailPresenter) mPresenter).year();
            if (iYear > 0 && (barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
                barChartBar.mBottomTitle = String.valueOf(iYear);
            }
        }
        BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar4 != null) {
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar4.setDefaultHeight(((DistanceDetailPresenter) mPresenter2).getMDefaultHeight());
        }
        BarChartBar barChartBar5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar5 != null) {
            barChartBar5.refreshChart(false);
        }
        refreshTopView(getMActivity());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected boolean showTopRightLayout() {
        DistanceDetailPresenter distanceDetailPresenter = (DistanceDetailPresenter) getMPresenter();
        return distanceDetailPresenter == null || distanceDetailPresenter.getMDateType() != 1;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public View getTipLayContent() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack != null ? mCallBack.getTipViewHolder(this) : null;
        if (tipViewHolder instanceof DistanceDetailTipViewHolder) {
            return ((DistanceDetailTipViewHolder) tipViewHolder).getMLayTipContent();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:63:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0128  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void refreshUiByDateType() {
        /*
            Method dump skipped, instruction units count: 416
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.distance.DistanceDetailFragment.refreshUiByDateType():void");
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void setXMaxValue(int maxValue) {
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar != null) {
            barChartBar.mXMaxValue = maxValue;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void setXMinValue(int minValue) {
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar != null) {
            barChartBar.mXMinValue = minValue;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onTopViewRefresh() {
        refreshTopView(getMActivity());
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onBottomViewRefresh() {
        refreshBottomView(getMActivity());
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void clearCache() {
        IChartDetailCallback mCallBack;
        IChartDetailCallback mCallBack2 = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack2 != null ? mCallBack2.getTopViewHolder(this) : null;
        if (!(topViewHolder instanceof DistanceDetailTopViewHolder) && topViewHolder != null) {
            topViewHolder.setDefaultValue();
        }
        if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
            BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (barChartBar != null) {
                barChartBar.clearList();
            }
            BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (barChartBar2 != null) {
                barChartBar2.refreshChart(false);
            }
        }
        if (!isVisible() || (mCallBack = getMCallBack()) == null) {
            return;
        }
        mCallBack.updateSelectDate(this, null);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadingView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof DistanceDetailTopViewHolder) {
            ((DistanceDetailTopViewHolder) topViewHolder).showLoadingView();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadSuccessView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof DistanceDetailTopViewHolder) {
            ((DistanceDetailTopViewHolder) topViewHolder).showSuccessView(showTopRightLayout());
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadFailedView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof DistanceDetailTopViewHolder) {
            ((DistanceDetailTopViewHolder) topViewHolder).showLoadFailedView(this);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTopView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return new DistanceDetailTopViewHolder(LayoutInflater.from(context).inflate(R.layout.detail_top_distance_layout, (ViewGroup) null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public void hideSelectedUi(boolean resetDate) {
        BarChartBar barChartBar;
        IChartDetailCallback mCallBack;
        if (resetDate && (mCallBack = getMCallBack()) != null) {
            DistanceDetailFragment distanceDetailFragment = this;
            DistanceDetailPresenter distanceDetailPresenter = (DistanceDetailPresenter) getMPresenter();
            mCallBack.updateSelectDate(distanceDetailFragment, distanceDetailPresenter != null ? distanceDetailPresenter.getMStartDate() : null);
        }
        if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
            BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (barChartBar2 == null) {
                Intrinsics.throwNpe();
            }
            if (barChartBar2.getVisibility() != 0 || (barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
                return;
            }
            barChartBar.refreshChart(false);
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar.CaluteXGridLineCallback
    public float calculateXGridLineValue(View target, int index) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        List<String> labelYLeftList = barChartBar != null ? barChartBar.getLabelYLeftList() : null;
        if (labelYLeftList == null || labelYLeftList.size() <= index) {
            return 0.0f;
        }
        String str = labelYLeftList.get(index);
        Intrinsics.checkExpressionValueIsNotNull(str, "leftLabelList[index]");
        return Float.parseFloat(str);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTipViewHolder(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.detail_chart_tip_distance_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…ip_distance_layout, null)");
        return new DistanceDetailTipViewHolder(viewInflate);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoading() {
        showLoadingDialog();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void dismissLoading() {
        dismissLoadingDialog();
    }

    /* JADX INFO: compiled from: DistanceDetailFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ido/life/module/home/distance/DistanceDetailFragment$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getInstance", "Lcom/ido/life/module/home/distance/DistanceDetailFragment;", "bundle", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DistanceDetailFragment getInstance(Bundle bundle) {
            DistanceDetailFragment distanceDetailFragment = new DistanceDetailFragment();
            if (bundle != null) {
                distanceDetailFragment.setArguments(bundle);
            }
            return distanceDetailFragment;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    public void clickAction(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        if (view.getId() != R.id.lay_loading) {
            return;
        }
        if (NetworkUtil.isConnected(getMActivity())) {
            IChartDetailCallback mCallBack = getMCallBack();
            BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
            if (topViewHolder instanceof DistanceDetailTopViewHolder) {
                ((DistanceDetailTopViewHolder) topViewHolder).showLoadingView();
                ((DistanceDetailPresenter) getMPresenter()).getDetailData();
                return;
            }
            return;
        }
        showToast(R.string.public_net_unuse);
    }
}