package com.ido.life.transmitter;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.life.bean.IconTransBean;
import com.ido.life.dialog.CommonDialog;
import com.ido.life.transmitter.callback.OnFileTransferCallback;
import com.ido.life.transmitter.task.DialTransferTask;
import com.ido.life.transmitter.task.IconTransferTask;
import com.ido.life.transmitter.task.TransferTask;
import com.ido.life.util.CoroutinesUtils;
import com.ido.life.util.ListUtils;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: compiled from: Transmitter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0019\b&\u0018\u0000 J2\u00020\u00012\u00020\u0002:\u0002JKB\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001bJ\u001a\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00120\u001a2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00120\u001aJ\u0010\u0010'\u001a\u00020#2\b\b\u0001\u0010(\u001a\u00020\u001dJ\u0010\u0010)\u001a\u00020\u00122\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020\u0012H\u0002J\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00120\u001a2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00120\u001aJ\b\u0010/\u001a\u00020\u001dH\u0002J\u0006\u0010\r\u001a\u000200J\u0006\u0010\u000e\u001a\u000200J\b\u00101\u001a\u00020#H\u0002J\u0010\u00101\u001a\u00020#2\u0006\u00102\u001a\u000203H\u0002J\u001e\u00104\u001a\u00020#2\b\u00105\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u0005H\u0002J\u0012\u00107\u001a\u00020#2\b\u00105\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u00108\u001a\u00020#2\b\u00105\u001a\u0004\u0018\u00010\u0012H\u0016J\u001c\u00109\u001a\u00020#2\b\u00105\u001a\u0004\u0018\u00010\u00122\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0016J\u001a\u0010;\u001a\u00020#2\b\u00105\u001a\u0004\u0018\u00010\u00122\u0006\u0010<\u001a\u00020\u001dH\u0016J\u0012\u0010=\u001a\u00020#2\b\u00105\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010>\u001a\u00020#2\b\u00105\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010?\u001a\u00020#H\u0016J\u0010\u0010@\u001a\u00020#2\u0006\u0010:\u001a\u00020\u0005H\u0016J\u0010\u0010A\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u001bJ\u0014\u0010B\u001a\u00020#2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00120\u001aJ\b\u0010C\u001a\u00020#H\u0016J\b\u0010D\u001a\u00020#H\u0016J\b\u0010E\u001a\u00020#H\u0002J\b\u0010F\u001a\u00020#H\u0004J\b\u0010G\u001a\u00020#H\u0016J\b\u0010H\u001a\u00020#H\u0002J\b\u0010I\u001a\u00020#H\u0002R\"\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00120\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006L"}, d2 = {"Lcom/ido/life/transmitter/Transmitter;", "Lcom/ido/life/transmitter/callback/OnFileTransferCallback;", "Lcom/ido/life/transmitter/ITransmitter;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "isPause", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isStarted", "isTransmitting", "mCallback", "Landroid/os/Handler$Callback;", "mCurrentTransferTask", "Lcom/ido/life/transmitter/task/TransferTask;", "mFailedQueue", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "mHandler", "Landroid/os/Handler;", "mHandlerThread", "Landroid/os/HandlerThread;", "mIconTransmitterListeners", "", "Lcom/ido/life/transmitter/IconTransmitterListener;", "mMaxPriority", "", "mQueue", "Ljava/util/concurrent/PriorityBlockingQueue;", "mTimeOutJob", "Lkotlinx/coroutines/Job;", "addListener", "", "mIconTransmitterListener", "addTask", "tasks", CommonDialog.EXTRA_CANCEL, "moduleType", "convertBean2Task", "iconTransBean", "Lcom/ido/life/bean/IconTransBean;", "convertTask2Bean", "transferTask", "execute", "getMaxPriority", "", "next", "delayInMill", "", "notifyFailed", "task", AuthorizationResponseParser.ERROR, "onBusy", "onCancel", "onFailed", "msg", "onProgress", "var2", "onStart", "onSuccess", "pause", "printAndSave", "removeListener", "removeTask", "resume", "shutdown", "shutdownInternal", "shutdownUnexpected", "start", "trigger", "tryTrigger", "Companion", "TransmitterType", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class Transmitter implements OnFileTransferCallback, ITransmitter {
    public static final int DEFAULT_INITIAL_CAPACITY = 11;
    public static final long DELAY_IN_MILL = 100;
    public static final long DELAY_IN_MILL_WHEN_BUSY = 3000;
    public static final int WHAT_TRANS = 1;
    private TransferTask mCurrentTransferTask;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private Job mTimeOutJob;
    private List<IconTransmitterListener> mIconTransmitterListeners = new ArrayList();
    private final PriorityBlockingQueue<TransferTask> mQueue = new PriorityBlockingQueue<>(11, new Comparator<E>() { // from class: com.ido.life.transmitter.Transmitter$mQueue$1
        @Override // java.util.Comparator
        public final int compare(TransferTask transferTask, TransferTask transferTask2) {
            return transferTask2.getPriority() - transferTask.getPriority();
        }
    });
    private final ConcurrentLinkedQueue<TransferTask> mFailedQueue = new ConcurrentLinkedQueue<>();
    private final AtomicBoolean isTransmitting = new AtomicBoolean(false);
    private final AtomicBoolean isStarted = new AtomicBoolean(false);
    private AtomicBoolean isPause = new AtomicBoolean(false);
    private String TAG = getClass().getSimpleName();
    private int mMaxPriority = 2000;
    private final Handler.Callback mCallback = new Handler.Callback() { // from class: com.ido.life.transmitter.Transmitter$mCallback$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message it) {
            Intrinsics.checkParameterIsNotNull(it, "it");
            this.this$0.printAndSave("收到传输事件，当前是否正在传输：" + this.this$0.isTransmitting.get() + ", 任务数：" + this.this$0.mQueue.size() + ", 是否暂停：" + this.this$0.isPause.get());
            if (it.what == 1 && !this.this$0.isTransmitting.get() && !this.this$0.isPause.get()) {
                Transmitter transmitter = this.this$0;
                transmitter.mCurrentTransferTask = (TransferTask) transmitter.mQueue.poll();
                this.this$0.printAndSave("开始传输：" + this.this$0.mCurrentTransferTask);
                if (this.this$0.mCurrentTransferTask != null) {
                    this.this$0.isTransmitting.set(true);
                    Job job = this.this$0.mTimeOutJob;
                    if (job != null) {
                        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                    }
                    TransferTask transferTask = this.this$0.mCurrentTransferTask;
                    if (transferTask == null) {
                        Intrinsics.throwNpe();
                    }
                    long timeout = transferTask.getTimeout();
                    if (timeout <= 0) {
                        timeout = BootloaderScanner.TIMEOUT;
                    }
                    this.this$0.printAndSave("开始任务，超时时长：" + timeout);
                    this.this$0.mTimeOutJob = CoroutinesUtils.INSTANCE.delay(timeout, new Function0<Unit>() { // from class: com.ido.life.transmitter.Transmitter$mCallback$1.1
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
                            Transmitter$mCallback$1.this.this$0.printAndSave("the task " + Transmitter$mCallback$1.this.this$0.mCurrentTransferTask + " time out, next!");
                            TransferTask transferTask2 = Transmitter$mCallback$1.this.this$0.mCurrentTransferTask;
                            if (transferTask2 != null) {
                                transferTask2.stopTransfer();
                            }
                        }
                    });
                    TransferTask transferTask2 = this.this$0.mCurrentTransferTask;
                    if (transferTask2 == null) {
                        Intrinsics.throwNpe();
                    }
                    transferTask2.startTransfer(this.this$0);
                } else {
                    this.this$0.isTransmitting.set(false);
                }
            }
            return false;
        }
    };

    /* JADX INFO: compiled from: Transmitter.kt */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0087\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lcom/ido/life/transmitter/Transmitter$TransmitterType;", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
    @kotlin.annotation.Target(allowedTargets = {AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION})
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    public @interface TransmitterType {
        public static final int BLE = 1;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;
        public static final int SPP = 2;

        /* JADX INFO: compiled from: Transmitter.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/ido/life/transmitter/Transmitter$TransmitterType$Companion;", "", "()V", "BLE", "", "SPP", "app_release"}, k = 1, mv = {1, 1, 16})
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            public static final int BLE = 1;
            public static final int SPP = 2;

            private Companion() {
            }
        }
    }

    protected final String getTAG() {
        return this.TAG;
    }

    protected final void setTAG(String str) {
        this.TAG = str;
    }

    public final boolean isStarted() {
        return this.isStarted.get();
    }

    public final boolean isTransmitting() {
        return this.isTransmitting.get();
    }

    protected final void shutdownUnexpected() {
        if (this.isStarted.get()) {
            if (this.isTransmitting.get()) {
                printAndSave("It's necessary to add the last data to the queue to ensure data consistency!!!");
                this.mQueue.add(this.mCurrentTransferTask);
                TransferTask transferTask = this.mCurrentTransferTask;
                if (transferTask != null) {
                    transferTask.stopTransferSilently();
                }
            }
            shutdown();
        }
    }

    private final int getMaxPriority() {
        TransferTask transferTaskPeek = this.mQueue.peek();
        if (transferTaskPeek != null) {
            return transferTaskPeek.getPriority();
        }
        return 0;
    }

    public final void addListener(IconTransmitterListener mIconTransmitterListener) {
        Intrinsics.checkParameterIsNotNull(mIconTransmitterListener, "mIconTransmitterListener");
        this.mIconTransmitterListeners.add(mIconTransmitterListener);
    }

    public final void removeListener(IconTransmitterListener mIconTransmitterListener) {
        if (mIconTransmitterListener != null) {
            this.mIconTransmitterListeners.remove(mIconTransmitterListener);
        }
    }

    public final List<TransferTask> execute(List<TransferTask> tasks) {
        TransferTask transferTask;
        TransferTask transferTask2;
        Intrinsics.checkParameterIsNotNull(tasks, "tasks");
        printAndSave("execute：" + tasks.size());
        ArrayList arrayList = new ArrayList();
        if (ListUtils.INSTANCE.isNullOrEmpty(tasks)) {
            return arrayList;
        }
        printAndSave("The current max priority = " + this.mMaxPriority);
        this.mMaxPriority = this.mMaxPriority + 1;
        for (TransferTask transferTask3 : tasks) {
            if (this.mQueue.contains(transferTask3)) {
                printAndSave("重复任务：" + transferTask3 + "，提高优先级至：" + this.mMaxPriority);
                transferTask3.setPriority(this.mMaxPriority);
                arrayList.add(transferTask3);
            } else if (this.isTransmitting.get() && (transferTask2 = this.mCurrentTransferTask) != null && Intrinsics.areEqual(transferTask2, transferTask3)) {
                arrayList.add(transferTask3);
                printAndSave("重复任务，且正在传输：" + transferTask3);
            } else {
                transferTask3.setPriority(this.mMaxPriority);
                printAndSave("task: " + transferTask3 + "，isAdd = " + this.mQueue.add(transferTask3));
            }
        }
        printAndSave("当前总任务数：" + this.mQueue.size() + ", check if pause：" + this.isPause.get());
        TransferTask transferTask4 = this.mCurrentTransferTask;
        if ((transferTask4 == null || transferTask4.getModuleType() != 0) && (transferTask = this.mCurrentTransferTask) != null && transferTask.getModuleType() == tasks.get(0).getModuleType()) {
            printAndSave("同类任务，不强制执行！");
            tryTrigger();
        } else {
            trigger();
        }
        return arrayList;
    }

    public final void cancel(@ModuleType int moduleType) {
        printAndSave("取消任务类型：" + moduleType);
        Iterator<TransferTask> it = this.mQueue.iterator();
        Intrinsics.checkExpressionValueIsNotNull(it, "mQueue.iterator()");
        while (it.hasNext()) {
            TransferTask next = it.next();
            if (next.getModuleType() == moduleType) {
                printAndSave("取消任务：" + next);
                next.stopTransfer();
                it.remove();
                notifyFailed$default(this, next, null, 2, null);
            }
        }
        TransferTask transferTask = this.mCurrentTransferTask;
        if (transferTask != null && transferTask.getModuleType() == moduleType) {
            printAndSave("取消正在执行的任务：" + this.mCurrentTransferTask);
            TransferTask transferTask2 = this.mCurrentTransferTask;
            if (transferTask2 != null) {
                transferTask2.stopTransfer();
            }
        }
        printAndSave("取消任务总任务数：" + this.mQueue.size());
    }

    public final List<TransferTask> addTask(List<TransferTask> tasks) {
        Intrinsics.checkParameterIsNotNull(tasks, "tasks");
        printAndSave("addTask：" + tasks.size());
        ArrayList arrayList = new ArrayList();
        if (ListUtils.INSTANCE.isNullOrEmpty(tasks)) {
            return arrayList;
        }
        for (TransferTask transferTask : tasks) {
            if (this.mQueue.contains(transferTask)) {
                printAndSave("重复：" + transferTask);
                arrayList.add(transferTask);
            } else {
                printAndSave("task: " + transferTask + "，isAdd = " + this.mQueue.add(transferTask));
            }
        }
        printAndSave("总任务数：" + this.mQueue.size() + ", check if pause：" + this.isPause.get());
        tryTrigger();
        return arrayList;
    }

    public final void removeTask(List<TransferTask> tasks) {
        Intrinsics.checkParameterIsNotNull(tasks, "tasks");
        printAndSave("removeTask：" + tasks);
        if (ListUtils.INSTANCE.isNullOrEmpty(tasks)) {
            return;
        }
        for (TransferTask transferTask : tasks) {
            if (Intrinsics.areEqual(this.mCurrentTransferTask, transferTask)) {
                printAndSave("移除任务，当前任务正在执行：" + transferTask);
                TransferTask transferTask2 = this.mCurrentTransferTask;
                if (transferTask2 != null) {
                    transferTask2.stopTransferSilently();
                }
                this.mCurrentTransferTask = (TransferTask) null;
            }
            if (this.mQueue.contains(transferTask)) {
                printAndSave("移除任务：" + transferTask);
                this.mQueue.remove(transferTask);
            } else {
                printAndSave("移除任务任务失败：" + transferTask + "不存在或已完成");
            }
        }
        printAndSave("移除后总任务数：" + this.mQueue.size());
        if (this.mQueue.size() == 0) {
            shutdownInternal();
        }
    }

    public void start() {
        HandlerThread handlerThread;
        if (this.isStarted.get()) {
            printAndSave("Mission has started!!!");
            return;
        }
        printAndSave("Icon transmission start，check pause status：" + this.isPause.get());
        this.isStarted.set(true);
        HandlerThread handlerThread2 = this.mHandlerThread;
        if (handlerThread2 == null || (!(handlerThread2 == null || handlerThread2.isAlive()) || ((handlerThread = this.mHandlerThread) != null && handlerThread.isInterrupted()))) {
            this.mHandlerThread = new HandlerThread(this.TAG);
            HandlerThread handlerThread3 = this.mHandlerThread;
            if (handlerThread3 == null) {
                Intrinsics.throwNpe();
            }
            handlerThread3.start();
            HandlerThread handlerThread4 = this.mHandlerThread;
            if (handlerThread4 == null) {
                Intrinsics.throwNpe();
            }
            this.mHandler = new Handler(handlerThread4.getLooper(), this.mCallback);
            this.isTransmitting.set(false);
            printAndSave("start transmitter thread!");
        }
    }

    public void shutdown() {
        printAndSave("shutdown");
        shutdownInternal();
        printAndSave("the transmitter is shutdown!!! there are " + this.mQueue.size() + " Icon left!!!");
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(CollectionsKt.toMutableList((Collection) this.mQueue));
        if (ListUtils.INSTANCE.isNotEmpty(this.mIconTransmitterListeners)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(arrayList, null), 2, null);
        }
        this.mQueue.clear();
    }

    /* JADX INFO: renamed from: com.ido.life.transmitter.Transmitter$shutdown$1, reason: invalid class name */
    /* JADX INFO: compiled from: Transmitter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.transmitter.Transmitter$shutdown$1", f = "Transmitter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List $remainList;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(List list, Continuation continuation) {
            super(2, continuation);
            this.$remainList = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = Transmitter.this.new AnonymousClass1(this.$remainList, completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            List list = Transmitter.this.mIconTransmitterListeners;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((IconTransmitterListener) it.next()).onTransferShutdown(this.$remainList);
                arrayList.add(Unit.INSTANCE);
            }
            return Unit.INSTANCE;
        }
    }

    private final void shutdownInternal() {
        this.isTransmitting.set(false);
        this.isStarted.set(false);
        this.isPause.set(false);
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.mHandler = (Handler) null;
        this.mHandlerThread = (HandlerThread) null;
        this.mCurrentTransferTask = (TransferTask) null;
    }

    private final void next() {
        next(100L);
    }

    private final void next(long delayInMill) {
        this.isTransmitting.set(false);
        this.mCurrentTransferTask = (TransferTask) null;
        if (this.isPause.get()) {
            printAndSave("任务暂停了...");
            return;
        }
        int size = this.mQueue.size();
        if (size != 0) {
            printAndSave("总任务数：" + size);
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.sendEmptyMessageDelayed(1, delayInMill);
                return;
            }
            return;
        }
        printAndSave("任务全部执行完毕");
        shutdown();
    }

    @Override // com.ido.life.transmitter.ITransmitter
    public void pause() {
        if (!this.isPause.get()) {
            this.isPause.set(true);
            if (this.isStarted.get()) {
                printAndSave("暂停任务...");
                if (!this.isTransmitting.get() || this.mCurrentTransferTask == null) {
                    return;
                }
                printAndSave("暂停任务，停止当传输任务：" + this.mCurrentTransferTask + "，等待任务恢复后执行。");
                this.mQueue.add(this.mCurrentTransferTask);
                TransferTask transferTask = this.mCurrentTransferTask;
                if (transferTask != null) {
                    transferTask.stopTransferSilently();
                    return;
                }
                return;
            }
            printAndSave("pause->任务未启动");
            return;
        }
        printAndSave("pause->状态错误，任务已经暂停");
    }

    @Override // com.ido.life.transmitter.ITransmitter
    public void resume() {
        if (this.isPause.get()) {
            this.isPause.set(false);
            if (this.isStarted.get()) {
                printAndSave("恢复暂停的任务...");
                next();
                return;
            } else {
                printAndSave("resume->任务未启动");
                return;
            }
        }
        printAndSave("resume->状态错误，非暂停状态");
    }

    private final void trigger() {
        TransferTask transferTask;
        if (!this.isStarted.get()) {
            start();
        }
        if (this.isTransmitting.get() && (transferTask = this.mCurrentTransferTask) != null) {
            if (transferTask != null) {
                transferTask.stopTransferSilently();
            }
            this.isTransmitting.set(false);
            printAndSave("停止当前任务：" + this.mCurrentTransferTask + "，并加入到队列稍后再执行");
            this.mQueue.add(this.mCurrentTransferTask);
        }
        printAndSave("开始强制执行，总任务数：" + this.mQueue.size() + ", check if pause：" + this.isPause.get());
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(1, 100L);
        }
    }

    private final void tryTrigger() {
        if (!this.isStarted.get()) {
            start();
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(1, 100L);
        }
    }

    private final TransferTask convertBean2Task(IconTransBean iconTransBean) {
        return new IconTransferTask(iconTransBean.getModuleType(), iconTransBean.getDataType(), iconTransBean.getPriority());
    }

    private final IconTransBean convertTask2Bean(TransferTask transferTask) {
        return new IconTransBean(transferTask.getModuleType(), transferTask.getChildType(), transferTask.getPriority());
    }

    @Override // com.ido.life.transmitter.callback.OnFileTransferCallback
    public void onStart(TransferTask task) {
        printAndSave("onStart -> " + task + ", cancel the timeout job!");
        Job job = this.mTimeOutJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (task == null || !ListUtils.INSTANCE.isNotEmpty(this.mIconTransmitterListeners)) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new Transmitter$onStart$$inlined$let$lambda$1(null, this, task), 2, null);
    }

    @Override // com.ido.life.transmitter.callback.OnFileTransferCallback
    public void onProgress(TransferTask task, int var2) {
        printAndSave("onProgress -> " + task + ", progress: " + var2);
        if (task == null || !ListUtils.INSTANCE.isNotEmpty(this.mIconTransmitterListeners)) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new Transmitter$onProgress$$inlined$let$lambda$1(null, this, task, var2), 2, null);
    }

    @Override // com.ido.life.transmitter.callback.OnFileTransferCallback
    public void onSuccess(TransferTask task) {
        printAndSave("onSuccess -> " + task);
        if (task != null && ListUtils.INSTANCE.isNotEmpty(this.mIconTransmitterListeners)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new Transmitter$onSuccess$$inlined$let$lambda$1(null, this, task), 2, null);
        }
        next();
    }

    @Override // com.ido.life.transmitter.callback.OnFileTransferCallback
    public void onFailed(TransferTask task, String msg) {
        printAndSave("onFailed -> " + task);
        notifyFailed(task, msg);
        next();
    }

    @Override // com.ido.life.transmitter.callback.OnFileTransferCallback
    public void onBusy(TransferTask task) {
        printAndSave("onBusy -> " + task);
        if (task != null) {
            if (!this.mQueue.contains(task)) {
                printAndSave("【onBusy】SDK层有传输任务正在进行，重新加入队列：" + task);
                this.mQueue.add(task);
            } else {
                printAndSave("【onBusy】任务已在队列，无需重新添加！");
            }
        }
        next(3000L);
    }

    @Override // com.ido.life.transmitter.callback.OnFileTransferCallback
    public void onCancel(TransferTask task) {
        printAndSave("onCancel -> " + task);
        notifyFailed$default(this, task, null, 2, null);
        next();
    }

    static /* synthetic */ void notifyFailed$default(Transmitter transmitter, TransferTask transferTask, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: notifyFailed");
        }
        if ((i & 2) != 0) {
            str = "";
        }
        transmitter.notifyFailed(transferTask, str);
    }

    private final void notifyFailed(TransferTask task, String error) {
        if (task != null) {
            Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = task.getChildType();
            try {
                if (Intrinsics.areEqual(error, DialTransferTask.DEVICE_SPACE_NOT_ENOUGH) && task.getModuleType() == 7) {
                    printAndSave("【notifyFailed】表盘传输失败，设备空间不足！");
                    intRef.element = Integer.parseInt(DialTransferTask.DEVICE_SPACE_NOT_ENOUGH);
                }
            } catch (Exception unused) {
                printAndSave("【notifyFailed】异常情况处理失败");
            }
            if (ListUtils.INSTANCE.isNotEmpty(this.mIconTransmitterListeners)) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new Transmitter$notifyFailed$$inlined$let$lambda$1(intRef, null, this, task, error), 2, null);
            }
        }
    }

    public void printAndSave(String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getLogPath(), this.TAG, msg);
    }
}