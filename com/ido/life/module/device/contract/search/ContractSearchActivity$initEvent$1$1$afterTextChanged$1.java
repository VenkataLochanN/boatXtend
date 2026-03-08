package com.ido.life.module.device.contract.search;

import android.text.Editable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.ido.life.R;
import com.ido.life.module.device.contract.search.ContractSearchActivity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ContractSearchActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.ido.life.module.device.contract.search.ContractSearchActivity$initEvent$1$1$afterTextChanged$1", f = "ContractSearchActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class ContractSearchActivity$initEvent$1$1$afterTextChanged$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Editable $s;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ ContractSearchActivity.AnonymousClass1.C01081 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ContractSearchActivity$initEvent$1$1$afterTextChanged$1(ContractSearchActivity.AnonymousClass1.C01081 c01081, Editable editable, Continuation continuation) {
        super(2, continuation);
        this.this$0 = c01081;
        this.$s = editable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ContractSearchActivity$initEvent$1$1$afterTextChanged$1 contractSearchActivity$initEvent$1$1$afterTextChanged$1 = new ContractSearchActivity$initEvent$1$1$afterTextChanged$1(this.this$0, this.$s, completion);
        contractSearchActivity$initEvent$1$1$afterTextChanged$1.p$ = (CoroutineScope) obj;
        return contractSearchActivity$initEvent$1$1$afterTextChanged$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ContractSearchActivity$initEvent$1$1$afterTextChanged$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.p$;
        ImageView search_iv_delete = (ImageView) ContractSearchActivity.this._$_findCachedViewById(R.id.search_iv_delete);
        Intrinsics.checkExpressionValueIsNotNull(search_iv_delete, "search_iv_delete");
        search_iv_delete.setVisibility(!TextUtils.isEmpty(this.$s) ? 0 : 8);
        return Unit.INSTANCE;
    }
}