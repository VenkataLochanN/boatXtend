package com.ido.life.module.home.chartdetail.vertical;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.ido.common.base.BaseCoreFragment;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter;
import com.ido.life.util.eventbus.EventBusWrapper;
import com.ido.life.util.eventbus.IHandlerEventBus;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseDetailFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0004\b\u0003\u0010\u0004*@\b\u0004\u0010\u0005*:\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0004\u0012\u001e\b\u0001\u0012\u001a\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u00070\u00062\u00020\b2\u00020\t2\u00020\nB\u0005¢\u0006\u0002\u0010\u000bJ\b\u0010-\u001a\u00020.H\u0014J\u0010\u0010/\u001a\u00020\r2\u0006\u00100\u001a\u000201H\u0004J\r\u00102\u001a\u00028\u0004H&¢\u0006\u0002\u0010)J\u0016\u00103\u001a\u00020.2\f\u00104\u001a\b\u0012\u0002\b\u0003\u0018\u000105H\u0016J\b\u00106\u001a\u00020.H\u0014J\b\u00107\u001a\u00020\"H\u0016J\u0010\u00108\u001a\u00020.2\u0006\u00109\u001a\u00020:H\u0016J\u0012\u0010;\u001a\u00020.2\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\b\u0010>\u001a\u00020.H\u0016J\b\u0010?\u001a\u00020.H\u0014J\b\u0010@\u001a\u00020.H\u0014J\b\u0010A\u001a\u00020.H\u0016J\b\u0010B\u001a\u00020.H\u0014J\b\u0010C\u001a\u00020.H\u0014J\u0012\u0010C\u001a\u00020.2\b\u0010D\u001a\u0004\u0018\u00010\rH\u0014J\u0010\u0010E\u001a\u00020.2\u0006\u0010D\u001a\u000201H\u0014J\u0012\u0010E\u001a\u00020.2\b\u0010D\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010F\u001a\u00020\"H$R\"\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0014X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\"X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010'\u001a\u00028\u0004X\u0084\u000e¢\u0006\u0010\n\u0002\u0010,\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+¨\u0006G"}, d2 = {"Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailFragment;", "Day", "Week", "Month", "Year", "T", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailPresenter;", "Lcom/ido/life/module/home/chartdetail/vertical/IBaseDetailView;", "Lcom/ido/common/base/BaseCoreFragment;", "Landroid/view/View$OnClickListener;", "Lcom/ido/life/util/eventbus/IHandlerEventBus;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "mActivity", "Lcom/ido/life/base/BaseActivity;", "getMActivity", "()Lcom/ido/life/base/BaseActivity;", "setMActivity", "(Lcom/ido/life/base/BaseActivity;)V", "mCallBack", "Lcom/ido/life/module/home/chartdetail/vertical/IChartDetailCallback;", "getMCallBack", "()Lcom/ido/life/module/home/chartdetail/vertical/IChartDetailCallback;", "setMCallBack", "(Lcom/ido/life/module/home/chartdetail/vertical/IChartDetailCallback;)V", "mEventBusWrapper", "Lcom/ido/life/util/eventbus/EventBusWrapper;", "mNeedRefreshPage", "", "getMNeedRefreshPage", "()Z", "setMNeedRefreshPage", "(Z)V", "mPresenter", "getMPresenter", "()Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailPresenter;", "setMPresenter", "(Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailPresenter;)V", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailPresenter;", "dismissLoadingDialog", "", "getLanguageText", "resId", "", "getPresenter", "handleMessage", "message", "Lcom/ido/life/base/BaseMessage;", "initView", "needEventBus", "onAttach", "context", "Landroid/content/Context;", "onClick", "v", "Landroid/view/View;", "onDestroy", "onVisible", "refreshChart", "refreshLanguage", "refreshUiByDateType", "showLoadingDialog", "msg", "showToast", "showTopRightLayout", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class BaseDetailFragment<Day, Week, Month, Year, T extends BaseDetailPresenter<Day, Week, Month, Year, ? extends IBaseDetailView<Day, Week, Month, Year>>> extends BaseCoreFragment implements View.OnClickListener, IHandlerEventBus {
    private HashMap _$_findViewCache;
    private BaseActivity<?> mActivity;
    private IChartDetailCallback mCallBack;
    private boolean mNeedRefreshPage;
    private String TAG = getClass().getSimpleName();
    private T mPresenter = (T) getPresenter();
    private EventBusWrapper mEventBusWrapper = new EventBusWrapper();

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

    public abstract T getPresenter();

    public boolean needEventBus() {
        return true;
    }

    public void onClick(View v) {
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    protected void refreshChart() {
    }

    public void refreshLanguage() {
    }

    protected abstract boolean showTopRightLayout();

    protected final String getTAG() {
        return this.TAG;
    }

    protected final void setTAG(String str) {
        this.TAG = str;
    }

    protected final T getMPresenter() {
        return this.mPresenter;
    }

    protected final void setMPresenter(T t) {
        Intrinsics.checkParameterIsNotNull(t, "<set-?>");
        this.mPresenter = t;
    }

    protected final BaseActivity<?> getMActivity() {
        return this.mActivity;
    }

    protected final void setMActivity(BaseActivity<?> baseActivity) {
        this.mActivity = baseActivity;
    }

    protected final boolean getMNeedRefreshPage() {
        return this.mNeedRefreshPage;
    }

    protected final void setMNeedRefreshPage(boolean z) {
        this.mNeedRefreshPage = z;
    }

    protected final IChartDetailCallback getMCallBack() {
        return this.mCallBack;
    }

    protected final void setMCallBack(IChartDetailCallback iChartDetailCallback) {
        this.mCallBack = iChartDetailCallback;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
        if (context instanceof IChartDetailCallback) {
            this.mCallBack = (IChartDetailCallback) context;
        }
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) context;
        }
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected void onVisible() {
        super.onVisible();
        if (this.mRootView != null) {
            refreshLanguage();
        }
    }

    protected void refreshUiByDateType() {
        this.mNeedRefreshPage = false;
    }

    protected final String getLanguageText(int resId) {
        String languageText = LanguageUtil.getLanguageText(resId);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguageText(resId)");
        return languageText;
    }

    @Override // com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage<?> message) {
        this.mPresenter.processEventBusMessage(message);
    }

    protected void showLoadingDialog(String msg) {
        BaseActivity<?> baseActivity = this.mActivity;
        if (baseActivity != null) {
            if (baseActivity == null) {
                Intrinsics.throwNpe();
            }
            baseActivity.showLoadingDialog(msg);
        }
    }

    protected void showLoadingDialog() {
        showLoadingDialog(null);
    }

    protected void dismissLoadingDialog() {
        BaseActivity<?> baseActivity = this.mActivity;
        if (baseActivity != null) {
            if (baseActivity == null) {
                Intrinsics.throwNpe();
            }
            baseActivity.dismissLoadingDialog();
        }
    }

    protected void showToast(String msg) {
        if (this.mActivity == null || TextUtils.isEmpty(msg)) {
            return;
        }
        BaseActivity<?> baseActivity = this.mActivity;
        if (baseActivity == null) {
            Intrinsics.throwNpe();
        }
        baseActivity.showToast(msg);
    }

    protected void showToast(int msg) {
        showToast(getString(msg));
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected void initView() {
        super.initView();
        this.mEventBusWrapper.register(this);
        refreshLanguage();
        IChartDetailCallback iChartDetailCallback = this.mCallBack;
        if (iChartDetailCallback != null) {
            T t = this.mPresenter;
            if (iChartDetailCallback == null) {
                Intrinsics.throwNpe();
            }
            BaseDetailFragment<Day, Week, Month, Year, T> baseDetailFragment = this;
            int dateType = iChartDetailCallback.getDateType(baseDetailFragment);
            IChartDetailCallback iChartDetailCallback2 = this.mCallBack;
            if (iChartDetailCallback2 == null) {
                Intrinsics.throwNpe();
            }
            t.initType(dateType, iChartDetailCallback2.getPageOffset(baseDetailFragment));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mEventBusWrapper.unregister();
    }
}