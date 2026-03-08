package com.ido.life.module.home.chartdetail.vertical;

import android.content.Context;
import android.view.View;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter;
import com.ido.life.module.home.chartdetail.vertical.IBaseDetailView;
import com.ido.life.util.ClickUtilKt;
import com.ido.life.util.RunTimeUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseDetailCoreFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\n\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0004\b\u0003\u0010\u0004* \b\u0004\u0010\u0005*\u001a\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u0006*&\b\u0005\u0010\u0007* \u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\b2 \u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00070\t2\u00020\n2\u00020\u000bB\u0005¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H&J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\n \u001a*\u0004\u0018\u00010\u00190\u0019H\u0014J\n\u0010\u001b\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010\u001c\u001a\u00020\u00112\b\u0010\u001d\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u001e\u001a\u00020\u0011H\u0014J\u0012\u0010\u001f\u001a\u00020\u00112\b\u0010 \u001a\u0004\u0018\u00010\u0019H\u0004J\u0012\u0010!\u001a\u00020\u00112\b\u0010 \u001a\u0004\u0018\u00010\u0019H\u0004J\u0012\u0010\"\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u0010\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020%H\u0016J\u0010\u0010(\u001a\u00020\u00112\u0006\u0010)\u001a\u00020%H\u0016J\u0012\u0010*\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u0010\u0010+\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u000eH\u0016J\b\u0010-\u001a\u00020\u000eH\u0016J\b\u0010.\u001a\u00020\u000eH\u0016¨\u0006/"}, d2 = {"Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailCoreFragment;", "Day", "Week", "Month", "Year", "V", "Lcom/ido/life/module/home/chartdetail/vertical/IBaseDetailView;", "T", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailPresenter;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailFragment;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailCallback;", "Landroid/view/View$OnClickListener;", "()V", "backgroundNeedUpdate", "", "bottomViewNeedUpdate", "clickAction", "", "view", "Landroid/view/View;", "getBottomView", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "context", "Landroid/content/Context;", "getLogPath", "", "kotlin.jvm.PlatformType", "getTipLayContent", "onClick", "v", "onVisible", "printAndSaveLog", "msg", "printLog", "refreshBottomView", "refreshCalorieType", "calorieType", "", "refreshChartTipView", "index", "refreshRateType", "rateType", "refreshTopView", "refreshTypeAndOffset", "refreshPage", "tipViewNeedUpdate", "topViewNeedUpdate", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class BaseDetailCoreFragment<Day, Week, Month, Year, V extends IBaseDetailView<Day, Week, Month, Year>, T extends BaseDetailPresenter<Day, Week, Month, Year, V>> extends BaseDetailFragment<Day, Week, Month, Year, T> implements BaseDetailCallback, View.OnClickListener {
    private HashMap _$_findViewCache;

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
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

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public boolean backgroundNeedUpdate() {
        return false;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public boolean bottomViewNeedUpdate() {
        return false;
    }

    public abstract void clickAction(View view);

    public BaseDetailViewHolder getBottomView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return null;
    }

    public View getTipLayContent() {
        return null;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    protected void refreshBottomView(Context context) {
    }

    public void refreshCalorieType(int calorieType) {
    }

    public boolean refreshChartTipView(int index) {
        return true;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public void refreshRateType(int rateType) {
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public boolean tipViewNeedUpdate() {
        return false;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public boolean topViewNeedUpdate() {
        return false;
    }

    public int getChartHeight(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return BaseDetailCallback.DefaultImpls.getChartHeight(this, context);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, com.ido.common.base.BaseCoreFragment
    protected void onVisible() {
        super.onVisible();
        if (this.mRootView == null) {
            return;
        }
        refreshLanguage();
        if (getMCallBack() != null) {
            IChartDetailCallback mCallBack = getMCallBack();
            if (mCallBack == null) {
                Intrinsics.throwNpe();
            }
            if (mCallBack.isShow(this)) {
                refreshUiByDateType();
            }
        }
        if (getMNeedRefreshPage()) {
            setMNeedRefreshPage(false);
        } else {
            refreshChart();
        }
    }

    protected void refreshTopView(Context context) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof BaseDetailTopViewHolder) {
            if (RunTimeUtil.getInstance().hasLogin()) {
                int dataDownloadState = getMPresenter().getDataDownloadState();
                if (dataDownloadState == 3) {
                    ((BaseDetailTopViewHolder) topViewHolder).showSuccessView(showTopRightLayout());
                    return;
                } else if (dataDownloadState == 4) {
                    ((BaseDetailTopViewHolder) topViewHolder).showLoadFailedView(this);
                    return;
                } else {
                    ((BaseDetailTopViewHolder) topViewHolder).showLoadingView();
                    return;
                }
            }
            ((BaseDetailTopViewHolder) topViewHolder).showSuccessView(showTopRightLayout());
        }
    }

    public void refreshTypeAndOffset(boolean refreshPage) {
        if (getMCallBack() == null) {
            return;
        }
        IChartDetailCallback mCallBack = getMCallBack();
        if (mCallBack == null) {
            Intrinsics.throwNpe();
        }
        BaseDetailCoreFragment<Day, Week, Month, Year, V, T> baseDetailCoreFragment = this;
        int dateType = mCallBack.getDateType(baseDetailCoreFragment);
        IChartDetailCallback mCallBack2 = getMCallBack();
        if (mCallBack2 == null) {
            Intrinsics.throwNpe();
        }
        int pageOffset = mCallBack2.getPageOffset(baseDetailCoreFragment);
        IChartDetailCallback mCallBack3 = getMCallBack();
        if (mCallBack3 == null) {
            Intrinsics.throwNpe();
        }
        long userId = mCallBack3.getUserId(baseDetailCoreFragment);
        CommonLogUtil.d(getTAG(), "dateType=" + dateType + ",pageOffset=" + pageOffset + ",refreshPage=" + refreshPage + ",userId=" + userId);
        getMPresenter().initType(dateType, pageOffset);
        if (isVisible() && refreshPage) {
            refreshUiByDateType();
        } else {
            setMNeedRefreshPage(true);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, android.view.View.OnClickListener
    public void onClick(View v) {
        if (v == null) {
            return;
        }
        if (!ClickUtilKt.canClick(v)) {
            printAndSaveLog("500毫秒之内只能触发一次点击。");
        } else {
            clickAction(v);
        }
    }

    protected final void printAndSaveLog(String msg) {
        String str = msg;
        if (str == null || str.length() == 0) {
            return;
        }
        CommonLogUtil.printAndSave(getLogPath(), getClass().getSimpleName(), msg);
    }

    protected final void printLog(String msg) {
        String str = msg;
        if (str == null || str.length() == 0) {
            return;
        }
        CommonLogUtil.d(getClass().getSimpleName(), msg);
    }

    protected String getLogPath() {
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        return logPathImpl.getLogPath();
    }
}