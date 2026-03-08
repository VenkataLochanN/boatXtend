package com.ido.life.transmitter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.SparseIntArray;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.IconTransInformation;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.bean.MotionTypeBean;
import com.ido.life.data.ExecutorDispatcher;
import com.ido.life.data.cache.MotionTypeManager;
import com.ido.life.data.listener.OnMotionIconTransListener;
import com.ido.life.transmitter.IconTransmitterListener;
import com.ido.life.transmitter.task.IconTransferTask;
import com.ido.life.transmitter.task.TransferTask;
import com.ido.life.util.ListUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MotionIconTransMicroTask.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000m\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b *\u0003\u0010\u0017\u001a\u0018\u0000 J2\u00020\u0001:\u0002JKB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010+\u001a\u00020,H\u0002J\b\u0010-\u001a\u00020\u0004H\u0002J&\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u00020\b2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u00101\u001a\u00020\u0004H\u0002J(\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u00020\b2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\b\b\u0001\u00102\u001a\u00020\bH\u0002J&\u00103\u001a\u00020\b2\u0006\u0010/\u001a\u00020\b2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u00105\u001a\u00020\bH\u0002J\b\u00106\u001a\u00020\bH\u0002J\b\u00107\u001a\u00020\bH\u0002J\u0010\u00108\u001a\u00020\u00042\u0006\u00109\u001a\u00020\bH\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010:\u001a\u00020\u0004H\u0002J\b\u0010;\u001a\u00020,H\u0002J\b\u0010<\u001a\u00020,H\u0002J\b\u0010=\u001a\u00020,H\u0002J \u0010>\u001a\u00020,2\u0006\u00109\u001a\u00020\b2\u0006\u0010/\u001a\u00020\b2\u0006\u0010?\u001a\u00020\u0004H\u0002J.\u0010@\u001a\u00020,2\u001a\u0010A\u001a\u0016\u0012\u0004\u0012\u00020\r\u0018\u00010\fj\n\u0012\u0004\u0012\u00020\r\u0018\u0001`\u000e2\b\b\u0002\u0010B\u001a\u00020\u0004H\u0002J\b\u0010C\u001a\u00020,H\u0002J\b\u0010D\u001a\u00020,H\u0002J\u0010\u0010E\u001a\u00020\b2\u0006\u0010F\u001a\u00020\bH\u0002J\b\u0010G\u001a\u00020,H\u0002J\u0014\u0010H\u001a\u00020,2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\r0\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0016\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006L"}, d2 = {"Lcom/ido/life/transmitter/MotionIconTransMicroTask;", "", "()V", "isSupportMiddleIcon", "", "isTransIcon", "Ljava/util/concurrent/atomic/AtomicBoolean;", "mAlreadyTransferredCount", "", "mAnimationIconTypes", "", "mFailedTransMotionType", "Ljava/util/ArrayList;", "Lcom/ido/life/transmitter/task/TransferTask;", "Lkotlin/collections/ArrayList;", "mIconTransListener", "com/ido/life/transmitter/MotionIconTransMicroTask$mIconTransListener$1", "Lcom/ido/life/transmitter/MotionIconTransMicroTask$mIconTransListener$1;", "mMotionTypes", "", "Lcom/ido/life/bean/MotionTypeBean;", "mNotifyState", "mNotifyTimer", "com/ido/life/transmitter/MotionIconTransMicroTask$mNotifyTimer$1", "Lcom/ido/life/transmitter/MotionIconTransMicroTask$mNotifyTimer$1;", "mSettingCallback", "com/ido/life/transmitter/MotionIconTransMicroTask$mSettingCallback$1", "Lcom/ido/life/transmitter/MotionIconTransMicroTask$mSettingCallback$1;", "mTransList", "getMTransList", "()Ljava/util/List;", "mTransStatus", "Landroid/util/SparseIntArray;", "onMotionIconTransListener", "Lcom/ido/life/data/listener/OnMotionIconTransListener;", "getOnMotionIconTransListener", "()Lcom/ido/life/data/listener/OnMotionIconTransListener;", "setOnMotionIconTransListener", "(Lcom/ido/life/data/listener/OnMotionIconTransListener;)V", "task", "Ljava/util/concurrent/Future;", "transformIndex", "transformMaxCount", "checkIfComplete", "", "checkSportsIconExist", "createAndAdd", "type", "stateList", "isBig", "iconType", "createTaskAndAdd", "taskList", "iconFlag", "getAllPicDownloadSuccessStatus", "getMaxIconCount", "isMotionTypeIcon", "module", "isSupportSetTransferInfo", "notifyDeviceTransferStart", "notifyDeviceTransferTerminated", "notifyFailed", "onIconTrans", "isSuccess", "postComplete", "list", "forcePost", "postProgress", "postStart", "sizeType2Status", "sizeType", "startTransferTask", "transformIcon2Device", "types", "Companion", "SingleInstanceHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MotionIconTransMicroTask {
    public static final int MAX_ICON_COUNT_EACH_MOTION = 3;
    public static final int MAX_ICON_COUNT_EACH_MOTION_WITHOUT_MID = 2;
    public static final long NOTIFY_TIMEOUT = 30000;
    public static final int WHAT_NOTIFY = 1;
    private boolean isSupportMiddleIcon;
    private int mAlreadyTransferredCount;
    private final MotionIconTransMicroTask$mIconTransListener$1 mIconTransListener;
    private List<MotionTypeBean> mMotionTypes;
    private final MotionIconTransMicroTask$mNotifyTimer$1 mNotifyTimer;
    private final MotionIconTransMicroTask$mSettingCallback$1 mSettingCallback;
    private OnMotionIconTransListener onMotionIconTransListener;
    private Future<Object> task;
    private volatile int transformIndex;
    private volatile int transformMaxCount;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final MotionIconTransMicroTask instance = SingleInstanceHolder.INSTANCE.getINSTANCE();
    private volatile ArrayList<TransferTask> mFailedTransMotionType = new ArrayList<>();
    private int[] mAnimationIconTypes = ResourceUtil.getIntegerArray(R.array.motion_type_animations);
    private SparseIntArray mTransStatus = new SparseIntArray();
    private AtomicBoolean isTransIcon = new AtomicBoolean(false);
    private final List<TransferTask> mTransList = new ArrayList();
    private int mNotifyState = -1;

    private final int sizeType2Status(int sizeType) {
        if (sizeType == 2) {
            return 1;
        }
        if (sizeType == 3) {
            return 2;
        }
        if (sizeType != 4) {
            return sizeType != 5 ? 0 : 4;
        }
        return 1;
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [com.ido.life.transmitter.MotionIconTransMicroTask$mIconTransListener$1] */
    /* JADX WARN: Type inference failed for: r0v8, types: [com.ido.life.transmitter.MotionIconTransMicroTask$mNotifyTimer$1] */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.ido.life.transmitter.MotionIconTransMicroTask$mSettingCallback$1] */
    public MotionIconTransMicroTask() {
        final Looper mainLooper = Looper.getMainLooper();
        this.mNotifyTimer = new Handler(mainLooper) { // from class: com.ido.life.transmitter.MotionIconTransMicroTask$mNotifyTimer$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                super.handleMessage(msg);
                CommonLogUtil.printAndSave("notifyDeviceTransferStart time out");
                this.this$0.notifyFailed();
            }
        };
        this.mSettingCallback = new SettingCallBack.ICallBack() { // from class: com.ido.life.transmitter.MotionIconTransMicroTask$mSettingCallback$1
            @Override // com.ido.ble.callback.SettingCallBack.ICallBack
            public void onSuccess(SettingCallBack.SettingType p0, Object p1) {
                if (p0 == SettingCallBack.SettingType.ICON_INFORMATION_NOTICE) {
                    if (this.this$0.mNotifyState == 0) {
                        removeMessages(1);
                        CommonLogUtil.printAndSave("notifyDeviceTransferStart success");
                        this.this$0.startTransferTask();
                    } else if (this.this$0.mNotifyState == 16) {
                        CommonLogUtil.printAndSave("notifyDeviceTransferTerminated success");
                    }
                }
            }

            @Override // com.ido.ble.callback.SettingCallBack.ICallBack
            public void onFailed(SettingCallBack.SettingType p0) {
                if (p0 == SettingCallBack.SettingType.ICON_INFORMATION_NOTICE) {
                    if (this.this$0.mNotifyState != 0) {
                        if (this.this$0.mNotifyState == 16) {
                            CommonLogUtil.printAndSave("notifyDeviceTransferTerminated failed");
                        }
                    } else {
                        CommonLogUtil.printAndSave("notifyDeviceTransferStart failed");
                        removeMessages(1);
                        this.this$0.notifyFailed();
                    }
                }
            }
        };
        this.mIconTransListener = new IconTransmitterListener() { // from class: com.ido.life.transmitter.MotionIconTransMicroTask$mIconTransListener$1
            @Override // com.ido.life.transmitter.IconTransmitterListener
            public void onTransferProgress(@ModuleType int i, int i2, int i3) {
                IconTransmitterListener.DefaultImpls.onTransferProgress(this, i, i2, i3);
            }

            @Override // com.ido.life.transmitter.IconTransmitterListener
            public void onTransferStart(@ModuleType int i, int i2) {
                IconTransmitterListener.DefaultImpls.onTransferStart(this, i, i2);
            }

            @Override // com.ido.life.transmitter.IconTransmitterListener
            public void onTransferSuccess(int module, int type) {
                if (this.this$0.isMotionTypeIcon(module)) {
                    this.this$0.onIconTrans(module, type, true);
                }
            }

            @Override // com.ido.life.transmitter.IconTransmitterListener
            public void onTransferFailed(int module, int type) {
                if (this.this$0.isMotionTypeIcon(module)) {
                    this.this$0.onIconTrans(module, type, false);
                }
            }

            @Override // com.ido.life.transmitter.IconTransmitterListener
            public void onTransferShutdown(List<TransferTask> remainList) {
                Intrinsics.checkParameterIsNotNull(remainList, "remainList");
                CommonLogUtil.printAndSave("onTransmitterShutdown，transformIndex = " + this.this$0.transformIndex + ",    transformMaxCount = " + this.this$0.transformMaxCount + ",     failedSize = " + this.this$0.mFailedTransMotionType.size());
                StringBuilder sb = new StringBuilder();
                sb.append("onTransmitterShutdown，mFailedTransMotionType = ");
                sb.append(this.this$0.mFailedTransMotionType);
                sb.append(",    remainIcons = ");
                sb.append(remainList);
                CommonLogUtil.printAndSave(sb.toString());
                for (TransferTask transferTask : remainList) {
                    if (this.this$0.isMotionTypeIcon(transferTask.getModuleType()) && !this.this$0.mFailedTransMotionType.contains(transferTask)) {
                        this.this$0.mFailedTransMotionType.add(transferTask);
                    }
                }
                CommonLogUtil.printAndSave("onTransmitterShutdown mTransStatus = " + this.this$0.mTransStatus);
                MotionIconTransMicroTask motionIconTransMicroTask = this.this$0;
                MotionIconTransMicroTask.postComplete$default(motionIconTransMicroTask, motionIconTransMicroTask.mFailedTransMotionType, false, 2, null);
            }
        };
    }

    public final List<TransferTask> getMTransList() {
        return this.mTransList;
    }

    public final OnMotionIconTransListener getOnMotionIconTransListener() {
        return this.onMotionIconTransListener;
    }

    public final void setOnMotionIconTransListener(OnMotionIconTransListener onMotionIconTransListener) {
        this.onMotionIconTransListener = onMotionIconTransListener;
    }

    /* JADX INFO: compiled from: MotionIconTransMicroTask.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/ido/life/transmitter/MotionIconTransMicroTask$Companion;", "", "()V", "MAX_ICON_COUNT_EACH_MOTION", "", "MAX_ICON_COUNT_EACH_MOTION_WITHOUT_MID", "NOTIFY_TIMEOUT", "", "WHAT_NOTIFY", "instance", "Lcom/ido/life/transmitter/MotionIconTransMicroTask;", "getInstance", "()Lcom/ido/life/transmitter/MotionIconTransMicroTask;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final MotionIconTransMicroTask getInstance() {
            return MotionIconTransMicroTask.instance;
        }
    }

    /* JADX INFO: compiled from: MotionIconTransMicroTask.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/ido/life/transmitter/MotionIconTransMicroTask$SingleInstanceHolder;", "", "()V", "INSTANCE", "Lcom/ido/life/transmitter/MotionIconTransMicroTask;", "getINSTANCE", "()Lcom/ido/life/transmitter/MotionIconTransMicroTask;", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final class SingleInstanceHolder {
        public static final SingleInstanceHolder INSTANCE = new SingleInstanceHolder();
        private static final MotionIconTransMicroTask INSTANCE = new MotionIconTransMicroTask();

        private SingleInstanceHolder() {
        }

        public final MotionIconTransMicroTask getINSTANCE() {
            return INSTANCE;
        }
    }

    public final void transformIcon2Device(List<MotionTypeBean> types) {
        Future<Object> future;
        Intrinsics.checkParameterIsNotNull(types, "types");
        this.mMotionTypes = types;
        postStart();
        if (ListUtils.INSTANCE.isNullOrEmpty(this.mMotionTypes)) {
            notifyFailed();
            return;
        }
        if (!checkSportsIconExist()) {
            CommonLogUtil.printAndSave("运动图标不存在！！！");
            notifyFailed();
            return;
        }
        if (this.isTransIcon.get()) {
            CommonLogUtil.printAndSave("有运动图标在传输，移除传输任务");
            FileTransmitter.INSTANCE.getInstance().removeTask(this.mTransList);
            Future<Object> future2 = this.task;
            if (future2 == null || !future2.isCancelled() || (future = this.task) == null || !future.isDone()) {
                try {
                    Future<Object> future3 = this.task;
                    if (future3 != null) {
                        future3.cancel(true);
                    }
                } catch (Exception unused) {
                }
            }
        }
        this.isTransIcon.set(true);
        this.isSupportMiddleIcon = isSupportMiddleIcon();
        this.task = ExecutorDispatcher.getInstance().dispatch(new Runnable() { // from class: com.ido.life.transmitter.MotionIconTransMicroTask.transformIcon2Device.1
            @Override // java.lang.Runnable
            public final void run() {
                int maxIconCount = MotionIconTransMicroTask.this.getMaxIconCount();
                CommonLogUtil.printAndSave("开始传输运动图标，支持" + maxIconCount);
                CommonLogUtil.printAndSave("动图类型：" + Arrays.toString(MotionIconTransMicroTask.this.mAnimationIconTypes));
                CommonLogUtil.printAndSave("开始传输运动图标：" + MotionIconTransMicroTask.this.mMotionTypes);
                MotionIconTransMicroTask.this.transformIndex = 0;
                MotionIconTransMicroTask.this.mAlreadyTransferredCount = 0;
                MotionIconTransMicroTask.this.mTransStatus.clear();
                MotionIconTransMicroTask.this.getMTransList().clear();
                MotionIconTransMicroTask.this.mFailedTransMotionType.clear();
                List<MotionTypeBean> list = MotionIconTransMicroTask.this.mMotionTypes;
                if (list != null) {
                    for (MotionTypeBean motionTypeBean : list) {
                        int iconFlag = motionTypeBean.getIconFlag();
                        if (iconFlag != 0) {
                            switch (iconFlag) {
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                    break;
                                default:
                                    CommonLogUtil.printAndSave("运动：" + motionTypeBean.getType() + " 状态异常！！！");
                                    continue;
                            }
                        }
                        int iCreateTaskAndAdd = MotionIconTransMicroTask.this.createTaskAndAdd(motionTypeBean.getType(), MotionIconTransMicroTask.this.getMTransList(), motionTypeBean.getIconFlag());
                        if (iCreateTaskAndAdd < maxIconCount) {
                            MotionIconTransMicroTask.this.mTransStatus.put(motionTypeBean.getType(), motionTypeBean.getIconFlag());
                        }
                        MotionIconTransMicroTask.this.transformIndex += iCreateTaskAndAdd;
                    }
                }
                MotionIconTransMicroTask motionIconTransMicroTask = MotionIconTransMicroTask.this;
                List list2 = motionIconTransMicroTask.mMotionTypes;
                motionIconTransMicroTask.transformMaxCount = (list2 != null ? list2.size() : 0) * maxIconCount;
                MotionIconTransMicroTask motionIconTransMicroTask2 = MotionIconTransMicroTask.this;
                motionIconTransMicroTask2.mAlreadyTransferredCount = motionIconTransMicroTask2.transformIndex;
                CommonLogUtil.d("总运动图标数量：" + MotionIconTransMicroTask.this.transformMaxCount + "，有" + MotionIconTransMicroTask.this.transformIndex + "个运动图标已传！！！");
                if (ListUtils.INSTANCE.isNotEmpty(MotionIconTransMicroTask.this.getMTransList())) {
                    if (!MotionIconTransMicroTask.this.isSupportSetTransferInfo()) {
                        MotionIconTransMicroTask.this.startTransferTask();
                        return;
                    } else {
                        CommonLogUtil.printAndSave("设置任务开始状态和图标数量！");
                        MotionIconTransMicroTask.this.notifyDeviceTransferStart();
                        return;
                    }
                }
                CommonLogUtil.printAndSave("没有要上传的运动图标");
                MotionIconTransMicroTask.this.notifyFailed();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyDeviceTransferStart() {
        IconTransInformation iconTransInformation = new IconTransInformation();
        iconTransInformation.icon_num = this.mTransList.size();
        iconTransInformation.states = 0;
        BLEManager.unregisterSettingCallBack(this.mSettingCallback);
        BLEManager.registerSettingCallBack(this.mSettingCallback);
        CommonLogUtil.printAndSave("notifyDeviceTransferStart, info = " + iconTransInformation);
        this.mNotifyState = 0;
        BLEManager.setTranSportIconInformation(iconTransInformation);
        sendEmptyMessageDelayed(1, 30000L);
    }

    private final void notifyDeviceTransferTerminated() {
        IconTransInformation iconTransInformation = new IconTransInformation();
        iconTransInformation.states = 16;
        BLEManager.unregisterSettingCallBack(this.mSettingCallback);
        BLEManager.registerSettingCallBack(this.mSettingCallback);
        CommonLogUtil.printAndSave("notifyDeviceTransferTerminated, info = " + iconTransInformation);
        this.mNotifyState = 16;
        BLEManager.setTranSportIconInformation(iconTransInformation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startTransferTask() {
        CommonLogUtil.printAndSave("startTransferTask");
        FileTransmitter.INSTANCE.getInstance().removeListener(this.mIconTransListener);
        FileTransmitter.INSTANCE.getInstance().addListener(this.mIconTransListener);
        FileTransmitter.INSTANCE.getInstance().execute(this.mTransList);
        postProgress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyFailed() {
        postComplete(null, true);
    }

    private final void createAndAdd(int type, List<TransferTask> stateList, boolean isBig) {
        int i;
        if (isBig) {
            i = 3;
        } else {
            int[] iArr = this.mAnimationIconTypes;
            i = (iArr == null || !ArraysKt.contains(iArr, type)) ? 2 : 4;
        }
        stateList.add(new IconTransferTask(i, type, 2));
    }

    private final void createAndAdd(int type, List<TransferTask> stateList, @ModuleType int iconType) {
        int[] iArr;
        if (iconType == 2 && (iArr = this.mAnimationIconTypes) != null && ArraysKt.contains(iArr, type)) {
            iconType = 4;
            CommonLogUtil.printAndSave("运动：" + type + " 存在动图，传输动图！");
        }
        stateList.add(new IconTransferTask(iconType, type, 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int createTaskAndAdd(int type, List<TransferTask> taskList, int iconFlag) {
        int i;
        CommonLogUtil.printAndSave("运动：" + type + " 图标任务创建: ");
        if (IconTransStatus.INSTANCE.requireSmall(iconFlag)) {
            createAndAdd(type, taskList, 2);
            CommonLogUtil.printAndSave("运动：" + type + " 的小图标还没有传输过！！！");
            i = 0;
        } else {
            i = 1;
        }
        if (this.isSupportMiddleIcon) {
            if (IconTransStatus.INSTANCE.requireMiddle(iconFlag)) {
                createAndAdd(type, taskList, 5);
                CommonLogUtil.printAndSave("运动：" + type + " 的中图标还没有传输过！！！");
            } else {
                i++;
            }
        }
        if (IconTransStatus.INSTANCE.requireBig(iconFlag)) {
            createAndAdd(type, taskList, 3);
            CommonLogUtil.printAndSave("运动：" + type + " 的大图标还没有传输过！！！");
        } else {
            i++;
        }
        if (i == getMaxIconCount()) {
            CommonLogUtil.printAndSave("运动：" + type + " 的图标已经传输过！！！");
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onIconTrans(int module, int type, boolean isSuccess) {
        Object next;
        if (isSuccess) {
            int iSizeType2Status = this.mTransStatus.get(type) + sizeType2Status(module);
            this.mTransStatus.put(type, iSizeType2Status);
            List<MotionTypeBean> list = this.mMotionTypes;
            if (list != null) {
                Iterator<T> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    } else {
                        next = it.next();
                        if (((MotionTypeBean) next).getType() == type) {
                            break;
                        }
                    }
                }
                MotionTypeBean motionTypeBean = (MotionTypeBean) next;
                if (motionTypeBean != null) {
                    motionTypeBean.setIconFlag(iSizeType2Status);
                }
            }
            CommonLogUtil.printAndSave("运动：" + type + "--->" + module + "图标传输完成");
            if (iSizeType2Status >= getAllPicDownloadSuccessStatus()) {
                CommonLogUtil.printAndSave("运动" + type + "--->3种size的图标都传输完成");
                this.mTransStatus.delete(type);
            }
            this.transformIndex++;
            postProgress();
        } else {
            CommonLogUtil.printAndSave("运动：" + type + "--->" + module + "图标传输失败");
            IconTransferTask iconTransferTask = new IconTransferTask(module, type, 0, 4, null);
            if (!this.mFailedTransMotionType.contains(iconTransferTask)) {
                this.mFailedTransMotionType.add(iconTransferTask);
            }
        }
        checkIfComplete();
    }

    private final int getAllPicDownloadSuccessStatus() {
        return this.isSupportMiddleIcon ? 7 : 3;
    }

    private final void checkIfComplete() {
        int size = this.transformIndex + this.mFailedTransMotionType.size();
        CommonLogUtil.printAndSave("当前传输状态：" + this.mTransStatus);
        CommonLogUtil.printAndSave("checkIfComplete，totalTransCount = " + size + "，transformIndex = " + this.transformIndex + "，transformMaxCount = " + this.transformMaxCount);
        if (size >= this.transformMaxCount) {
            CommonLogUtil.printAndSave("All " + this.transformMaxCount + " motion types icon has trans complete!!!");
            postComplete$default(this, this.mFailedTransMotionType, false, 2, null);
        }
    }

    static /* synthetic */ void postComplete$default(MotionIconTransMicroTask motionIconTransMicroTask, ArrayList arrayList, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        motionIconTransMicroTask.postComplete(arrayList, z);
    }

    private final void postComplete(final ArrayList<TransferTask> list, boolean forcePost) {
        CommonLogUtil.printAndSave("postComplete，isTransIcon = " + this.isTransIcon.get() + "mTransStatus = " + this.mTransStatus);
        if (!forcePost && !this.isTransIcon.get()) {
            CommonLogUtil.printAndSave("postComplete，trans task is already completed!");
            return;
        }
        this.isTransIcon.set(false);
        if (isSupportSetTransferInfo()) {
            notifyDeviceTransferTerminated();
        }
        ExecutorDispatcher.getInstance().dispatchOnMainThread(new Runnable() { // from class: com.ido.life.transmitter.MotionIconTransMicroTask.postComplete.1
            @Override // java.lang.Runnable
            public final void run() {
                OnMotionIconTransListener onMotionIconTransListener = MotionIconTransMicroTask.this.getOnMotionIconTransListener();
                if (onMotionIconTransListener != null) {
                    onMotionIconTransListener.onIconTransComplete(ListUtils.INSTANCE.isNullOrEmpty(list));
                }
            }
        });
    }

    private final void postProgress() {
        ExecutorDispatcher.getInstance().dispatchOnMainThread(new Runnable() { // from class: com.ido.life.transmitter.MotionIconTransMicroTask.postProgress.1
            @Override // java.lang.Runnable
            public final void run() {
                OnMotionIconTransListener onMotionIconTransListener = MotionIconTransMicroTask.this.getOnMotionIconTransListener();
                if (onMotionIconTransListener != null) {
                    onMotionIconTransListener.onIconTransProgress(MotionIconTransMicroTask.this.transformIndex - MotionIconTransMicroTask.this.mAlreadyTransferredCount, MotionIconTransMicroTask.this.transformMaxCount - MotionIconTransMicroTask.this.mAlreadyTransferredCount);
                }
            }
        });
    }

    private final void postStart() {
        CommonLogUtil.printAndSave("postStart");
        ExecutorDispatcher.getInstance().dispatchOnMainThread(new Runnable() { // from class: com.ido.life.transmitter.MotionIconTransMicroTask.postStart.1
            @Override // java.lang.Runnable
            public final void run() {
                OnMotionIconTransListener onMotionIconTransListener = MotionIconTransMicroTask.this.getOnMotionIconTransListener();
                if (onMotionIconTransListener != null) {
                    onMotionIconTransListener.onIconTransStart();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isMotionTypeIcon(int module) {
        return ModuleType.INSTANCE.getGROUP_TYPE_MOTION().contains(Integer.valueOf(module));
    }

    private final boolean checkSportsIconExist() {
        return MotionTypeManager.INSTANCE.isMotionTypeIconsExist();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getMaxIconCount() {
        return this.isSupportMiddleIcon ? 3 : 2;
    }

    private final boolean isSupportMiddleIcon() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo != null) {
            return supportFunctionInfo.v3_surport_sport_medium_icon;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isSupportSetTransferInfo() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo != null) {
            return supportFunctionInfo.v2_support_notice_icon_information;
        }
        return false;
    }
}