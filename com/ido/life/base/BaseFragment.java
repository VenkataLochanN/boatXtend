package com.ido.life.base;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.common.base.BaseCoreFragment;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.util.ObjectUtil;
import com.ido.life.util.eventbus.EventBusWrapper;
import com.ido.life.util.eventbus.IHandlerEventBus;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseFragment<P extends BasePresenter> extends BaseCoreFragment implements IHandlerEventBus {
    protected BaseActivity mActivity;
    private Unbinder mBind;
    private EventBusWrapper mEventBusWrapper;
    private long mLastClickTime;
    protected P mPresenter;

    @Override // com.ido.common.base.BaseCoreFragment
    protected void initData() {
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected void initEvent() {
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected void initView() {
    }

    protected boolean needEventBus() {
        return false;
    }

    protected void refreshLanguage() {
    }

    @Override // com.ido.common.base.BaseCoreFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        CommonLogUtil.d("BaseDetailPresenter", "onViewCreated");
        this.mBind = ButterKnife.bind(this, this.mRootView);
        if (this.mPresenter == null) {
            initPresenter();
        }
        if (needEventBus() && this.mEventBusWrapper == null) {
            this.mEventBusWrapper = new EventBusWrapper();
            this.mEventBusWrapper.register(this);
        }
        super.onViewCreated(view, bundle);
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected void onVisible() {
        super.onVisible();
        if (this.mRootView != null) {
            refreshLanguage();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void initPresenter() {
        this.mPresenter = (P) autoCreatePresenter();
        try {
            if (this.mPresenter != null) {
                this.mPresenter.attachView((IBaseView) this);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public P autoCreatePresenter() {
        return (P) ObjectUtil.getParameterizedType(getClass());
    }

    @Override // com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        CommonLogUtil.d(baseMessage.toString() + AppInfo.DELIM + toString());
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) context;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getLanguageText(int i) {
        BaseActivity baseActivity = this.mActivity;
        return baseActivity != null ? baseActivity.getLanguageText(i) : "";
    }

    protected void showLoadingDialog(String str) {
        BaseActivity baseActivity = this.mActivity;
        if (baseActivity != null) {
            baseActivity.showLoadingDialog(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showLoadingDialog() {
        showLoadingDialog(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void dismissLoadingDialog() {
        BaseActivity baseActivity = this.mActivity;
        if (baseActivity != null) {
            baseActivity.dismissLoadingDialog();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showToast(String str) {
        if (this.mActivity == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.mActivity.showToast(str);
    }

    protected void showToast(int i) {
        showToast(getString(i));
    }

    protected boolean clickValid() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (Math.abs(this.mLastClickTime - jCurrentTimeMillis) < 500) {
            return false;
        }
        this.mLastClickTime = jCurrentTimeMillis;
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Unbinder unbinder = this.mBind;
        if (unbinder != null) {
            unbinder.unbind();
        }
        EventBusWrapper eventBusWrapper = this.mEventBusWrapper;
        if (eventBusWrapper != null) {
            eventBusWrapper.unregister();
            this.mEventBusWrapper = null;
        }
        P p = this.mPresenter;
        if (p != null) {
            p.detachView();
        }
    }
}