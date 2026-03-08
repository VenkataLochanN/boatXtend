package com.ido.life.module.device.presenter;

import com.ido.common.log.CommonLogUtil;
import com.ido.life.bean.MotionTypeBean;
import com.ido.life.data.cache.MotionTypeManager;
import com.ido.life.data.listener.OnMotionIconTransListener;
import com.ido.life.module.device.view.IMotionTypesView;
import com.ido.life.util.CoroutinesUtils;
import com.ido.life.util.ListUtils;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: MotionTypesPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0006\u0010\n\u001a\u00020\tJ\b\u0010\u000b\u001a\u00020\u0007H\u0014J \u0010\f\u001a\u00020\u00072\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010H\u0014J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\tH\u0014J\u0018\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0014J\b\u0010\u0017\u001a\u00020\u0007H\u0014J\b\u0010\u0018\u001a\u00020\u0007H\u0014J\"\u0010\u0019\u001a\u00020\u00072\u001a\u0010\u001a\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\u000f\u0018\u0001`\u0010J\u0006\u0010\u001b\u001a\u00020\tR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/ido/life/module/device/presenter/MotionTypesPresenter;", "Lcom/ido/life/module/device/presenter/BaseMotionTypesPresenter;", "Lcom/ido/life/module/device/view/IMotionTypesView;", "()V", "mTimer", "Lkotlinx/coroutines/Job;", "getDeviceMotionTypes", "", "forceUpdate", "", "isSupportMiddleIcon", "onGetDeviceMotionTypesFailed", "onGetDeviceMotionTypesSuccess", "list", "Ljava/util/ArrayList;", "Lcom/ido/life/bean/MotionTypeBean;", "Lkotlin/collections/ArrayList;", "onIconTransComplete", "isSuccess", "onIconTransProgress", "transformIndex", "", "transformMaxCount", "onOperateSetFailed", "onOperateSetSuccess", "setDeviceMotionTypes", "types", "supportIconTrans", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MotionTypesPresenter extends BaseMotionTypesPresenter<IMotionTypesView> {
    private Job mTimer;

    public final boolean isSupportMiddleIcon() {
        return true;
    }

    public static final /* synthetic */ IMotionTypesView access$getView(MotionTypesPresenter motionTypesPresenter) {
        return (IMotionTypesView) motionTypesPresenter.getView();
    }

    @Override // com.ido.life.module.device.presenter.BaseMotionTypesPresenter
    protected void onGetDeviceMotionTypesSuccess(ArrayList<MotionTypeBean> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        super.onGetDeviceMotionTypesSuccess(list);
        IMotionTypesView iMotionTypesView = (IMotionTypesView) getView();
        if (iMotionTypesView != null) {
            iMotionTypesView.onGetDeviceMotionTypesSuccess(list);
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseMotionTypesPresenter
    protected void onGetDeviceMotionTypesFailed() {
        IMotionTypesView iMotionTypesView = (IMotionTypesView) getView();
        if (iMotionTypesView != null) {
            iMotionTypesView.onGetDeviceMotionTypesFailed();
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseMotionTypesPresenter
    protected void onOperateSetFailed() {
        Job job = this.mTimer;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        IMotionTypesView iMotionTypesView = (IMotionTypesView) getView();
        if (iMotionTypesView != null) {
            iMotionTypesView.onSetMotionTypesFailed();
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseMotionTypesPresenter
    protected void onOperateSetSuccess() {
        Job job = this.mTimer;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        IMotionTypesView iMotionTypesView = (IMotionTypesView) getView();
        if (iMotionTypesView != null) {
            iMotionTypesView.onSetMotionTypesSuccess();
        }
        if (supportIconTrans()) {
            CommonLogUtil.printAndSave("运动类型设置成功，准备传输图标！！！");
            if (ListUtils.INSTANCE.isNotEmpty(getMMotionTypes())) {
                MotionTypeManager companion = MotionTypeManager.INSTANCE.getInstance();
                ArrayList<MotionTypeBean> mMotionTypes = getMMotionTypes();
                if (mMotionTypes == null) {
                    Intrinsics.throwNpe();
                }
                companion.transformIcon2Device(mMotionTypes, new OnMotionIconTransListener() { // from class: com.ido.life.module.device.presenter.MotionTypesPresenter.onOperateSetSuccess.1
                    @Override // com.ido.life.data.listener.OnMotionIconTransListener
                    public void onIconTransStart() {
                        MotionTypeManager.INSTANCE.getInstance().startIconTransStatus();
                    }

                    @Override // com.ido.life.data.listener.OnMotionIconTransListener
                    public void onIconTransProgress(int progress, int maxCount) {
                        MotionTypesPresenter.this.onIconTransProgress(progress, maxCount);
                    }

                    @Override // com.ido.life.data.listener.OnMotionIconTransListener
                    public void onIconTransComplete(boolean isSuccess) {
                        if (isSuccess) {
                            MotionTypeManager.INSTANCE.getInstance().completeIconTransStatus();
                        }
                        MotionTypesPresenter.this.onIconTransComplete(isSuccess);
                    }
                });
                return;
            }
            return;
        }
        CommonLogUtil.printAndSave("不支持运动图片传输，直接通知完成！");
        onIconTransComplete(true);
    }

    public final boolean supportIconTrans() {
        return getSupportFunctionInfo().V3_support_v3_notify_icon_adaptive;
    }

    @Override // com.ido.life.module.device.presenter.BaseMotionTypesPresenter
    public void getDeviceMotionTypes(boolean forceUpdate) {
        super.getDeviceMotionTypes(forceUpdate);
    }

    @Override // com.ido.life.module.device.presenter.BaseMotionTypesPresenter
    protected void onIconTransProgress(int transformIndex, int transformMaxCount) {
        IMotionTypesView iMotionTypesView = (IMotionTypesView) getView();
        if (iMotionTypesView != null) {
            iMotionTypesView.onSync(transformIndex, transformMaxCount);
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseMotionTypesPresenter
    protected void onIconTransComplete(boolean isSuccess) {
        CommonLogUtil.d("onIconTransComplete，isSuccess = " + isSuccess);
        IMotionTypesView iMotionTypesView = (IMotionTypesView) getView();
        if (iMotionTypesView != null) {
            iMotionTypesView.onSyncComplete(isSuccess);
        }
    }

    public final void setDeviceMotionTypes(ArrayList<MotionTypeBean> types) {
        setMMotionTypes(types);
        CommonLogUtil.printAndSave("setDeviceMotionTypes types = " + types);
        if (ListUtils.INSTANCE.isNotEmpty(types)) {
            MotionTypeManager companion = MotionTypeManager.INSTANCE.getInstance();
            if (types == null) {
                Intrinsics.throwNpe();
            }
            companion.setMotionTypes2Device(types);
            this.mTimer = CoroutinesUtils.INSTANCE.delay(30000L, new Function0<Unit>() { // from class: com.ido.life.module.device.presenter.MotionTypesPresenter.setDeviceMotionTypes.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: com.ido.life.module.device.presenter.MotionTypesPresenter$setDeviceMotionTypes$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: MotionTypesPresenter.kt */
                @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
                @DebugMetadata(c = "com.ido.life.module.device.presenter.MotionTypesPresenter$setDeviceMotionTypes$1$1", f = "MotionTypesPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                static final class C01091 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;
                    private CoroutineScope p$;

                    C01091(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C01091 c01091 = C02601.this.new C01091(completion);
                        c01091.p$ = (CoroutineScope) obj;
                        return c01091;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C01091) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) throws Throwable {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.p$;
                        IMotionTypesView iMotionTypesViewAccess$getView = MotionTypesPresenter.access$getView(MotionTypesPresenter.this);
                        if (iMotionTypesViewAccess$getView != null) {
                            iMotionTypesViewAccess$getView.onSetMotionTypesFailed();
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C01091(null), 2, null);
                }
            });
            return;
        }
        IMotionTypesView iMotionTypesView = (IMotionTypesView) getView();
        if (iMotionTypesView != null) {
            iMotionTypesView.onSetMotionTypesFailed();
        }
    }
}