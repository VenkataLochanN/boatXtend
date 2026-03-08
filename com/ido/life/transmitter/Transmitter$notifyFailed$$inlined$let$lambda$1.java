package com.ido.life.transmitter;

import com.ido.life.transmitter.task.TransferTask;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Transmitter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/ido/life/transmitter/Transmitter$notifyFailed$1$1"}, k = 3, mv = {1, 1, 16})
final class Transmitter$notifyFailed$$inlined$let$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.IntRef $dataType;
    final /* synthetic */ String $error$inlined;
    final /* synthetic */ TransferTask $task$inlined;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ Transmitter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Transmitter$notifyFailed$$inlined$let$lambda$1(Ref.IntRef intRef, Continuation continuation, Transmitter transmitter, TransferTask transferTask, String str) {
        super(2, continuation);
        this.$dataType = intRef;
        this.this$0 = transmitter;
        this.$task$inlined = transferTask;
        this.$error$inlined = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        Transmitter$notifyFailed$$inlined$let$lambda$1 transmitter$notifyFailed$$inlined$let$lambda$1 = new Transmitter$notifyFailed$$inlined$let$lambda$1(this.$dataType, completion, this.this$0, this.$task$inlined, this.$error$inlined);
        transmitter$notifyFailed$$inlined$let$lambda$1.p$ = (CoroutineScope) obj;
        return transmitter$notifyFailed$$inlined$let$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((Transmitter$notifyFailed$$inlined$let$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.p$;
        List list = this.this$0.mIconTransmitterListeners;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((IconTransmitterListener) it.next()).onTransferFailed(this.$task$inlined.getModuleType(), this.$dataType.element);
            arrayList.add(Unit.INSTANCE);
        }
        return Unit.INSTANCE;
    }
}