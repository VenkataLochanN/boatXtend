package com.ido.life.util;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: CoroutinesUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J{\u0010\u0003\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u00072'\u0010\b\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H\u00050\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\t2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00102\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e\u0018\u00010\t2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u000e0\tJ\u001c\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010¨\u0006\u0016"}, d2 = {"Lcom/ido/life/util/CoroutinesUtils;", "", "()V", "debounce", "Lkotlinx/coroutines/Job;", "R", "timeInMill", "", "onOffer", "Lkotlin/Function1;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "producerScope", "", "onClose", "Lkotlin/Function0;", "onCatch", "", "todo", "delay", "func", "app_release"}, k = 1, mv = {1, 1, 16})
public final class CoroutinesUtils {
    public static final CoroutinesUtils INSTANCE = new CoroutinesUtils();

    private CoroutinesUtils() {
    }

    /* JADX INFO: renamed from: com.ido.life.util.CoroutinesUtils$delay$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CoroutinesUtils.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.util.CoroutinesUtils$delay$1", f = "CoroutinesUtils.kt", i = {0}, l = {17}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C04591 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0 $func;
        final /* synthetic */ long $timeInMill;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04591(long j, Function0 function0, Continuation continuation) {
            super(2, continuation);
            this.$timeInMill = j;
            this.$func = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04591 c04591 = new C04591(this.$timeInMill, this.$func, completion);
            c04591.p$ = (CoroutineScope) obj;
            return c04591;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04591) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                long j = this.$timeInMill;
                this.L$0 = coroutineScope;
                this.label = 1;
                if (DelayKt.delay(j, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.$func.invoke();
            return Unit.INSTANCE;
        }
    }

    public final Job delay(long timeInMill, Function0<Unit> func) {
        Intrinsics.checkParameterIsNotNull(func, "func");
        return BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C04591(timeInMill, func, null), 2, null);
    }

    /* JADX INFO: renamed from: com.ido.life.util.CoroutinesUtils$debounce$1, reason: invalid class name */
    /* JADX INFO: compiled from: CoroutinesUtils.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.util.CoroutinesUtils$debounce$1", f = "CoroutinesUtils.kt", i = {0, 0}, l = {42}, m = "invokeSuspend", n = {"$this$launch", "$this$collect$iv"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1 $onCatch;
        final /* synthetic */ Function0 $onClose;
        final /* synthetic */ Function1 $onOffer;
        final /* synthetic */ long $timeInMill;
        final /* synthetic */ Function1 $todo;
        Object L$0;
        Object L$1;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Function1 function1, Function0 function0, long j, Function1 function12, Function1 function13, Continuation continuation) {
            super(2, continuation);
            this.$onOffer = function1;
            this.$onClose = function0;
            this.$timeInMill = j;
            this.$onCatch = function12;
            this.$todo = function13;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$onOffer, this.$onClose, this.$timeInMill, this.$onCatch, this.$todo, completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: Add missing generic type declarations: [R] */
        /* JADX INFO: renamed from: com.ido.life.util.CoroutinesUtils$debounce$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: CoroutinesUtils.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.util.CoroutinesUtils$debounce$1$1", f = "CoroutinesUtils.kt", i = {0}, l = {32}, m = "invokeSuspend", n = {"$this$callbackFlow"}, s = {"L$0"})
        static final class C01251<R> extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
            Object L$0;
            int label;
            private ProducerScope p$;

            C01251(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C01251 c01251 = AnonymousClass1.this.new C01251(completion);
                c01251.p$ = (ProducerScope) obj;
                return c01251;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
                return ((C01251) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    ProducerScope producerScope = this.p$;
                    AnonymousClass1.this.$onOffer.invoke(producerScope);
                    Function0<Unit> function0 = new Function0<Unit>() { // from class: com.ido.life.util.CoroutinesUtils.debounce.1.1.1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            Function0 function02 = AnonymousClass1.this.$onClose;
                            if (function02 != null) {
                                function02.invoke();
                            }
                        }
                    };
                    this.L$0 = producerScope;
                    this.label = 1;
                    if (ProduceKt.awaitClose(producerScope, function0, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                Flow flowFlowOn = FlowKt.flowOn(FlowKt.m1376catch(FlowKt.debounce(FlowKt.callbackFlow(new C01251(null)), this.$timeInMill), new AnonymousClass2(null)), Dispatchers.getIO());
                FlowCollector flowCollector = new FlowCollector<R>() { // from class: com.ido.life.util.CoroutinesUtils$debounce$1$invokeSuspend$$inlined$collect$1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object obj2, Continuation continuation) {
                        Object objInvoke = this.this$0.$todo.invoke(obj2);
                        return objInvoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInvoke : Unit.INSTANCE;
                    }
                };
                this.L$0 = coroutineScope;
                this.L$1 = flowFlowOn;
                this.label = 1;
                if (flowFlowOn.collect(flowCollector, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Add missing generic type declarations: [R] */
        /* JADX INFO: renamed from: com.ido.life.util.CoroutinesUtils$debounce$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: CoroutinesUtils.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "msg", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.util.CoroutinesUtils$debounce$1$2", f = "CoroutinesUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2<R> extends SuspendLambda implements Function3<FlowCollector<? super R>, Throwable, Continuation<? super Unit>, Object> {
            int label;
            private FlowCollector p$;
            private Throwable p$0;

            AnonymousClass2(Continuation continuation) {
                super(3, continuation);
            }

            public final Continuation<Unit> create(FlowCollector<? super R> create, Throwable msg, Continuation<? super Unit> continuation) {
                Intrinsics.checkParameterIsNotNull(create, "$this$create");
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                Intrinsics.checkParameterIsNotNull(continuation, "continuation");
                AnonymousClass2 anonymousClass2 = AnonymousClass1.this.new AnonymousClass2(continuation);
                anonymousClass2.p$ = create;
                anonymousClass2.p$0 = msg;
                return anonymousClass2;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Throwable th, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create((FlowCollector) obj, th, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = this.p$;
                Throwable th = this.p$0;
                Function1 function1 = AnonymousClass1.this.$onCatch;
                if (function1 != null) {
                    function1.invoke(th);
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final <R> Job debounce(long timeInMill, Function1<? super ProducerScope<? super R>, Unit> onOffer, Function0<Unit> onClose, Function1<? super Throwable, Unit> onCatch, Function1<? super R, Unit> todo) {
        Intrinsics.checkParameterIsNotNull(onOffer, "onOffer");
        Intrinsics.checkParameterIsNotNull(todo, "todo");
        return BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass1(onOffer, onClose, timeInMill, onCatch, todo, null), 2, null);
    }
}