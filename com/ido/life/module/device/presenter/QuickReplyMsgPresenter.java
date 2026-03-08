package com.ido.life.module.device.presenter;

import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.data.Func;
import com.ido.life.data.listener.Callback;
import com.ido.life.module.device.view.IQuickReplyMsgView;
import com.ido.life.util.SPHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QuickReplyMsgPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/ido/life/module/device/presenter/QuickReplyMsgPresenter;", "Lcom/ido/life/base/BaseCmdPresenter;", "Lcom/ido/life/module/device/view/IQuickReplyMsgView;", "()V", "getQuickReplyStatus", "", "setQuickReplyStatus", "openQuickReply", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final class QuickReplyMsgPresenter extends BaseCmdPresenter<IQuickReplyMsgView> {
    public static final /* synthetic */ IQuickReplyMsgView access$getView(QuickReplyMsgPresenter quickReplyMsgPresenter) {
        return (IQuickReplyMsgView) quickReplyMsgPresenter.getView();
    }

    public final void setQuickReplyStatus(final boolean openQuickReply) {
        addAutoCancelSubscriber(new Func<Boolean>() { // from class: com.ido.life.module.device.presenter.QuickReplyMsgPresenter.setQuickReplyStatus.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ido.life.data.Func
            public Boolean call() {
                SPHelper.setQuickMsgReplySwitchStatus(openQuickReply);
                return true;
            }
        }, new Callback<Boolean>() { // from class: com.ido.life.module.device.presenter.QuickReplyMsgPresenter.setQuickReplyStatus.2
            @Override // com.ido.life.data.listener.Callback
            public /* bridge */ /* synthetic */ void onSuccess(Boolean bool) {
                onSuccess(bool.booleanValue());
            }

            public void onSuccess(boolean data) {
                if (data) {
                    IQuickReplyMsgView iQuickReplyMsgViewAccess$getView = QuickReplyMsgPresenter.access$getView(QuickReplyMsgPresenter.this);
                    if (iQuickReplyMsgViewAccess$getView != null) {
                        iQuickReplyMsgViewAccess$getView.onSetQuickReplySuccess();
                        return;
                    }
                    return;
                }
                IQuickReplyMsgView iQuickReplyMsgViewAccess$getView2 = QuickReplyMsgPresenter.access$getView(QuickReplyMsgPresenter.this);
                if (iQuickReplyMsgViewAccess$getView2 != null) {
                    iQuickReplyMsgViewAccess$getView2.onSetQuickReplyFailed();
                }
            }

            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String errMsg) {
                Intrinsics.checkParameterIsNotNull(errMsg, "errMsg");
                IQuickReplyMsgView iQuickReplyMsgViewAccess$getView = QuickReplyMsgPresenter.access$getView(QuickReplyMsgPresenter.this);
                if (iQuickReplyMsgViewAccess$getView != null) {
                    iQuickReplyMsgViewAccess$getView.onSetQuickReplyFailed();
                }
            }
        });
    }

    public final void getQuickReplyStatus() {
        addAutoCancelSubscriber(new Func<Boolean>() { // from class: com.ido.life.module.device.presenter.QuickReplyMsgPresenter.getQuickReplyStatus.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ido.life.data.Func
            public Boolean call() {
                return Boolean.valueOf(SPHelper.isQuickMsgReplySwitchOpened());
            }
        }, new Callback<Boolean>() { // from class: com.ido.life.module.device.presenter.QuickReplyMsgPresenter.getQuickReplyStatus.2
            @Override // com.ido.life.data.listener.Callback
            public /* bridge */ /* synthetic */ void onSuccess(Boolean bool) {
                onSuccess(bool.booleanValue());
            }

            public void onSuccess(boolean data) {
                IQuickReplyMsgView iQuickReplyMsgViewAccess$getView = QuickReplyMsgPresenter.access$getView(QuickReplyMsgPresenter.this);
                if (iQuickReplyMsgViewAccess$getView != null) {
                    iQuickReplyMsgViewAccess$getView.onGetQuickReplySuccess(data);
                }
            }

            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String errMsg) {
                Intrinsics.checkParameterIsNotNull(errMsg, "errMsg");
                IQuickReplyMsgView iQuickReplyMsgViewAccess$getView = QuickReplyMsgPresenter.access$getView(QuickReplyMsgPresenter.this);
                if (iQuickReplyMsgViewAccess$getView != null) {
                    iQuickReplyMsgViewAccess$getView.onSetQuickReplyFailed();
                }
            }
        });
    }
}