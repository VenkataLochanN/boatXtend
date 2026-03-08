package com.ido.life.data.cache;

import android.content.Context;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.OperateCallBack;
import com.ido.ble.protocol.model.Sport100TypeItem;
import com.ido.ble.protocol.model.Sport100TypeSort;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.bean.MotionTypeBean;
import com.ido.life.ble.BaseOperateCallback;
import com.ido.life.constants.Constants;
import com.ido.life.data.Constant;
import com.ido.life.data.ExecutorDispatcher;
import com.ido.life.data.cache.MotionTypeManager;
import com.ido.life.data.listener.OnMotionIconTransListener;
import com.ido.life.data.listener.OnMotionTypeQueryCallback;
import com.ido.life.transmitter.MotionIconTransMicroTask;
import com.ido.life.util.CoroutinesUtils;
import com.ido.life.util.FileUtil;
import com.ido.life.util.ListUtils;
import com.ido.life.util.ResourceUtils;
import com.ido.life.util.SPHelper;
import com.ido.life.util.ZipUtils;
import com.ido.life.util.eventbus.EventBusHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: MotionTypeManager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000[\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u001c\u0018\u0000 =2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002=>B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010!\u001a\u00020\u0005H\u0002J\b\u0010\"\u001a\u00020\u0005H\u0002J\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0007J\u0006\u0010&\u001a\u00020'J\u0018\u0010(\u001a\u00020$2\b\u0010)\u001a\u0004\u0018\u00010\u00182\u0006\u0010*\u001a\u00020\u0005J\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007J\u0006\u0010,\u001a\u00020\u0005J\b\u0010-\u001a\u00020$H\u0002J\u0012\u0010.\u001a\u00020$2\b\u0010/\u001a\u0004\u0018\u00010'H\u0016J\b\u00100\u001a\u00020$H\u0016J\u0012\u00101\u001a\u00020$2\b\u0010/\u001a\u0004\u0018\u00010'H\u0016J\u0010\u00102\u001a\u00020$2\u0006\u0010/\u001a\u00020'H\u0016J\b\u00103\u001a\u00020$H\u0002J\b\u00104\u001a\u00020$H\u0002J\b\u00105\u001a\u00020$H\u0002J\u0006\u00106\u001a\u00020$J\u001e\u00107\u001a\u00020$2\u0016\u00108\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0011j\b\u0012\u0004\u0012\u00020\u0002`\u0012J\u0006\u00109\u001a\u00020$J(\u0010:\u001a\u00020$2\u0016\u00108\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0011j\b\u0012\u0004\u0012\u00020\u0002`\u00122\b\u0010;\u001a\u0004\u0018\u00010<R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0011j\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001`\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0011j\b\u0012\u0004\u0012\u00020\u0002`\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lcom/ido/life/data/cache/MotionTypeManager;", "Lcom/ido/life/data/cache/AbsDataCacheManager;", "Lcom/ido/life/bean/MotionTypeBean;", "()V", "initialed", "", "mAllMotionTypes", "", "getMAllMotionTypes", "()Ljava/util/List;", "setMAllMotionTypes", "(Ljava/util/List;)V", "mForceQueryFromDevice", "mHasReplenishChecked", "mJob", "Lkotlinx/coroutines/Job;", "mMotionTypesInDevice", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getMMotionTypesInDevice", "()Ljava/util/ArrayList;", "setMMotionTypesInDevice", "(Ljava/util/ArrayList;)V", "mOnMotionTypeQueryCallback", "Lcom/ido/life/data/listener/OnMotionTypeQueryCallback;", "mOperateQueryCallback", "Lcom/ido/ble/callback/OperateCallBack$ICallBack;", "mOperateSetCallback", "com/ido/life/data/cache/MotionTypeManager$mOperateSetCallback$1", "Lcom/ido/life/data/cache/MotionTypeManager$mOperateSetCallback$1;", "mResourceLoading", "mSetList", "mTimer", "checkIfReplenishIcons", "checkMotionResourceVersionChanged", "completeIconTransStatus", "", "getAllMotionTypes", "getMotionTypeProportion", "", "getMotionTypes", "onMotionTypeQueryCallback", "forceUpdate", "getMotionTypesHasIcon", "isIconTransComplete", "loadMotionTypesResource", "onBind", "macAddress", "onClear", "onSdkInitComplete", "onUnBind", "querySport100TypeSort", "querySport100TypeSortDelay", "removeMotionIconTransStatus", "replenishIcons", "setMotionTypes2Device", "types", "startIconTransStatus", "transformIcon2Device", "onMotionIconTransListener", "Lcom/ido/life/data/listener/OnMotionIconTransListener;", "Companion", "SingleInstanceHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MotionTypeManager extends AbsDataCacheManager<MotionTypeBean> {
    private static final int[] BALL_GROUP;
    private static final int[] BASIC_GROUP;
    private static final int[] CASUAL_GROUP;
    private static final int[] EXTREME_GROUP;
    private static final int[] FITNESS_GROUP;
    private static final int[] ICE_GROUP;
    private static int MAX_MOTION_TYPE = 0;
    private static int MIN_MOTION_TYPE = 0;
    private static final String RESOURCE_DIR = "motion_types";
    private static final String RESOURCE_NAME = "motion_types.zip";
    private static final String RESOURCE_NAME_NO_SUFFIX = "motion_types";
    private static final int RESOURCE_VERSION = 5;
    public static final String TAG = "MotionTypeManager";
    private static final int[] WATER_GROUP;
    private boolean initialed;
    private volatile List<MotionTypeBean> mAllMotionTypes;
    private boolean mForceQueryFromDevice;
    private boolean mHasReplenishChecked;
    private Job mJob;
    private volatile ArrayList<MotionTypeBean> mMotionTypesInDevice;
    private OnMotionTypeQueryCallback mOnMotionTypeQueryCallback;
    private volatile boolean mResourceLoading;
    private Job mTimer;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final MotionTypeManager instance = SingleInstanceHolder.INSTANCE.getINSTANCE();
    private ArrayList<MotionTypeBean> mSetList = new ArrayList<>();
    private final MotionTypeManager$mOperateSetCallback$1 mOperateSetCallback = new BaseOperateCallback() { // from class: com.ido.life.data.cache.MotionTypeManager$mOperateSetCallback$1
        @Override // com.ido.life.ble.BaseOperateCallback, com.ido.ble.callback.OperateCallBack.ICallBack
        public void onSetResult(OperateCallBack.OperateType operateType, boolean b2) {
            if (operateType == OperateCallBack.OperateType.SPORT_100_TYPE_SORT) {
                this.this$0.logP("onSetResult mSetList = " + this.this$0.mSetList + ",  result = " + b2);
                BLEManager.unregisterOperateCallBack(this);
                if (b2) {
                    MotionTypeManager motionTypeManager = this.this$0;
                    motionTypeManager.setMMotionTypesInDevice(motionTypeManager.mSetList);
                }
            }
        }
    };
    private final OperateCallBack.ICallBack mOperateQueryCallback = new BaseOperateCallback() { // from class: com.ido.life.data.cache.MotionTypeManager$mOperateQueryCallback$1
        @Override // com.ido.life.ble.BaseOperateCallback, com.ido.ble.callback.OperateCallBack.ICallBack
        public void onQueryResult(OperateCallBack.OperateType operateType, Object data) {
            if (operateType == OperateCallBack.OperateType.SPORT_100_TYPE_SORT) {
                BLEManager.unregisterOperateCallBack(this);
                this.this$0.logP("onQueryResult：" + data);
                if (data != null ? data instanceof Sport100TypeSort : true) {
                    ArrayList<MotionTypeBean> arrayList = new ArrayList<>();
                    ArrayList<MotionTypeBean> arrayListConvertToBean = (List) null;
                    if (data != null) {
                        Sport100TypeSort sport100TypeSort = (Sport100TypeSort) data;
                        MotionTypeManager.INSTANCE.setMAX_MOTION_TYPE(sport100TypeSort.max_show_num);
                        MotionTypeManager.INSTANCE.setMIN_MOTION_TYPE(sport100TypeSort.min_show_num);
                        int i = sport100TypeSort.now_user_location;
                        if (ListUtils.INSTANCE.isNotEmpty(sport100TypeSort.items)) {
                            MotionTypeManager.Companion companion = MotionTypeManager.INSTANCE;
                            List<Sport100TypeItem> list = sport100TypeSort.items;
                            Intrinsics.checkExpressionValueIsNotNull(list, "data.items");
                            arrayListConvertToBean = companion.convertToBean(list);
                            if (i >= 0) {
                                arrayList.addAll(arrayListConvertToBean.subList(0, i));
                            }
                        }
                    }
                    try {
                        this.this$0.setMAllMotionTypes(arrayListConvertToBean);
                        this.this$0.setMMotionTypesInDevice(arrayList);
                        MotionTypeManager motionTypeManager = this.this$0;
                        StringBuilder sb = new StringBuilder();
                        sb.append("querySport100TypeSort finished,   motionTypesInDevice: ");
                        sb.append(arrayList.size());
                        sb.append(",  totalMotionTypes: ");
                        sb.append(arrayListConvertToBean != null ? Integer.valueOf(arrayListConvertToBean.size()) : null);
                        motionTypeManager.logP(sb.toString());
                    } catch (Exception unused) {
                    }
                }
                OnMotionTypeQueryCallback onMotionTypeQueryCallback = this.this$0.mOnMotionTypeQueryCallback;
                if (onMotionTypeQueryCallback != null) {
                    onMotionTypeQueryCallback.onMotionTypeQuery(this.this$0.getMMotionTypesInDevice());
                }
                if (this.this$0.checkIfReplenishIcons()) {
                    this.this$0.logP("notify user to confirm replenish icons!");
                    EventBusHelper.postSticky(Constants.EventConstants.EVENT_MOTION_ICONS_REPLENISH);
                    this.this$0.completeIconTransStatus();
                }
            }
        }
    };

    /* JADX WARN: Type inference failed for: r0v1, types: [com.ido.life.data.cache.MotionTypeManager$mOperateSetCallback$1] */
    public MotionTypeManager() {
        loadMotionTypesResource();
    }

    public final ArrayList<MotionTypeBean> getMMotionTypesInDevice() {
        return this.mMotionTypesInDevice;
    }

    public final void setMMotionTypesInDevice(ArrayList<MotionTypeBean> arrayList) {
        this.mMotionTypesInDevice = arrayList;
    }

    public final List<MotionTypeBean> getMAllMotionTypes() {
        return this.mAllMotionTypes;
    }

    public final void setMAllMotionTypes(List<MotionTypeBean> list) {
        this.mAllMotionTypes = list;
    }

    /* JADX INFO: compiled from: MotionTypeManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/ido/life/data/cache/MotionTypeManager$SingleInstanceHolder;", "", "()V", "INSTANCE", "Lcom/ido/life/data/cache/MotionTypeManager;", "getINSTANCE", "()Lcom/ido/life/data/cache/MotionTypeManager;", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final class SingleInstanceHolder {
        public static final SingleInstanceHolder INSTANCE = new SingleInstanceHolder();
        private static final MotionTypeManager INSTANCE = new MotionTypeManager();

        private SingleInstanceHolder() {
        }

        public final MotionTypeManager getINSTANCE() {
            return INSTANCE;
        }
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onUnBind(String macAddress) {
        Intrinsics.checkParameterIsNotNull(macAddress, "macAddress");
        super.onUnBind(macAddress);
        logP("removeMotionIconTransStatus：" + macAddress);
        SPHelper.removeMotionIconTransStatus(macAddress);
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onBind(String macAddress) {
        super.onBind(macAddress);
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onSdkInitComplete(String macAddress) {
        super.onSdkInitComplete(macAddress);
        querySport100TypeSortDelay();
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onClear() {
        ArrayList<MotionTypeBean> arrayList;
        List<MotionTypeBean> list;
        ArrayList<MotionTypeBean> arrayList2;
        if (ListUtils.INSTANCE.isNotEmpty(this.mMotionTypesInDevice) && (arrayList2 = this.mMotionTypesInDevice) != null) {
            arrayList2.clear();
        }
        if (ListUtils.INSTANCE.isNotEmpty(this.mAllMotionTypes) && (list = this.mAllMotionTypes) != null) {
            list.clear();
        }
        if (!ListUtils.INSTANCE.isNotEmpty(this.mSetList) || (arrayList = this.mSetList) == null) {
            return;
        }
        arrayList.clear();
    }

    private final void removeMotionIconTransStatus() {
        String str;
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo == null || (str = currentDeviceInfo.mDeviceAddress) == null) {
            str = "";
        }
        SPHelper.removeMotionIconTransStatus(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkIfReplenishIcons() {
        logP("checkIfReplenishIcons,    mForceQueryFromDevice = " + this.mForceQueryFromDevice);
        if (this.mForceQueryFromDevice) {
            this.mForceQueryFromDevice = false;
            return false;
        }
        if (!this.initialed) {
            this.initialed = true;
            logP("first initial! It's necessary to replenish!");
            boolean zIsIconTransComplete = isIconTransComplete();
            logP("checkIfReplenishIcons,    isIconTransComplete = " + zIsIconTransComplete);
            if (ListUtils.INSTANCE.isNotEmpty(this.mMotionTypesInDevice)) {
                ArrayList<MotionTypeBean> arrayList = this.mMotionTypesInDevice;
                Object obj = null;
                if (arrayList != null) {
                    Iterator<T> it = arrayList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Object next = it.next();
                        if (((MotionTypeBean) next).getIconFlag() != 3) {
                            obj = next;
                            break;
                        }
                    }
                    obj = (MotionTypeBean) obj;
                }
                if (obj != null && !zIsIconTransComplete) {
                    return true;
                }
            }
            return false;
        }
        logP("has initialed! It's not necessary to replenish!");
        return false;
    }

    public final void replenishIcons() {
        logP("replenishIcons start!!!");
        if (ListUtils.INSTANCE.isNullOrEmpty(this.mMotionTypesInDevice)) {
            logP("replenishIcons mMotionTypesInDevice is null or empty !");
            return;
        }
        logP("replenishIcons,    start to transformIcon2Device");
        ArrayList<MotionTypeBean> arrayList = this.mMotionTypesInDevice;
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        transformIcon2Device(arrayList, new OnMotionIconTransListener() { // from class: com.ido.life.data.cache.MotionTypeManager.replenishIcons.1
            @Override // com.ido.life.data.listener.OnMotionIconTransListener
            public void onIconTransStart() {
                MotionTypeManager.this.logP("replenishIcons onIconTransStart");
            }

            @Override // com.ido.life.data.listener.OnMotionIconTransListener
            public void onIconTransProgress(int progress, int maxCount) {
                MotionTypeManager.this.logP("replenishIcons onIconTransProgress progress = " + progress + ", maxCount = " + maxCount);
            }

            @Override // com.ido.life.data.listener.OnMotionIconTransListener
            public void onIconTransComplete(boolean isSuccess) {
                MotionTypeManager.this.logP("replenishIcons onIconTransComplete isSuccess = " + isSuccess);
            }
        });
    }

    public final void startIconTransStatus() {
        logP("startIconTransStatus");
        SPHelper.saveMotionIconTransStatus(false);
    }

    public final void completeIconTransStatus() {
        logP("completeIconTransStatus");
        SPHelper.saveMotionIconTransStatus(true);
    }

    public final boolean isIconTransComplete() {
        return SPHelper.isMotionIconTransComplete();
    }

    public final String getMotionTypeProportion() {
        if (ListUtils.INSTANCE.isNullOrEmpty(this.mMotionTypesInDevice)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<MotionTypeBean> arrayList = this.mMotionTypesInDevice;
        sb.append(arrayList != null ? arrayList.size() : 0);
        sb.append(IOUtils.DIR_SEPARATOR_UNIX);
        sb.append(MAX_MOTION_TYPE);
        return sb.toString();
    }

    public final synchronized List<MotionTypeBean> getAllMotionTypes() {
        return this.mAllMotionTypes;
    }

    public final List<MotionTypeBean> getMotionTypesHasIcon() {
        List<MotionTypeBean> list = this.mAllMotionTypes;
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (((MotionTypeBean) obj).getIconFlag() != 0) {
                    arrayList.add(obj);
                }
            }
            List<MotionTypeBean> mutableList = CollectionsKt.toMutableList((Collection) arrayList);
            if (mutableList != null) {
                return mutableList;
            }
        }
        return new ArrayList();
    }

    public final void getMotionTypes(OnMotionTypeQueryCallback onMotionTypeQueryCallback, boolean forceUpdate) {
        this.mOnMotionTypeQueryCallback = onMotionTypeQueryCallback;
        this.mForceQueryFromDevice = forceUpdate;
        if (forceUpdate || ListUtils.INSTANCE.isNullOrEmpty(this.mMotionTypesInDevice)) {
            CommonLogUtil.printAndSave("querySport100TypeSort from device!!!");
            querySport100TypeSort();
            return;
        }
        CommonLogUtil.printAndSave("querySport100TypeSort from cache!!!");
        OnMotionTypeQueryCallback onMotionTypeQueryCallback2 = this.mOnMotionTypeQueryCallback;
        if (onMotionTypeQueryCallback2 != null) {
            onMotionTypeQueryCallback2.onMotionTypeQuery(this.mMotionTypesInDevice);
        }
    }

    public final void transformIcon2Device(ArrayList<MotionTypeBean> types, OnMotionIconTransListener onMotionIconTransListener) {
        Intrinsics.checkParameterIsNotNull(types, "types");
        logP("transformIcon2Device types = " + types);
        MotionIconTransMicroTask.INSTANCE.getInstance().setOnMotionIconTransListener(onMotionIconTransListener);
        MotionIconTransMicroTask.INSTANCE.getInstance().transformIcon2Device(types);
    }

    public final void setMotionTypes2Device(ArrayList<MotionTypeBean> types) {
        Intrinsics.checkParameterIsNotNull(types, "types");
        this.mSetList.clear();
        this.mSetList.addAll(types);
        ArrayList<MotionTypeBean> arrayList = types;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        for (MotionTypeBean motionTypeBean : arrayList) {
            Sport100TypeItem sport100TypeItem = new Sport100TypeItem();
            sport100TypeItem.type = motionTypeBean.getType();
            arrayList2.add(sport100TypeItem);
        }
        ArrayList arrayList3 = arrayList2;
        ArrayList arrayList4 = new ArrayList();
        arrayList4.addAll(arrayList3);
        List<MotionTypeBean> allMotionTypes = getAllMotionTypes();
        if (ListUtils.INSTANCE.isNotEmpty(allMotionTypes)) {
            if (allMotionTypes == null) {
                Intrinsics.throwNpe();
            }
            for (MotionTypeBean motionTypeBean2 : allMotionTypes) {
                Sport100TypeItem sport100TypeItem2 = new Sport100TypeItem();
                sport100TypeItem2.type = motionTypeBean2.getType();
                if (!arrayList3.contains(sport100TypeItem2)) {
                    arrayList4.add(sport100TypeItem2);
                }
            }
        }
        BLEManager.unregisterOperateCallBack(this.mOperateSetCallback);
        BLEManager.registerOperateCallBack(this.mOperateSetCallback);
        logP("setMotionTypes2Device types = " + types);
        logP("setMotionTypes2Device size = " + types.size());
        logP("setMotionTypes2Device result = " + arrayList4);
        logP("setMotionTypes2Device result.size = " + arrayList4.size());
        ArrayList arrayList5 = arrayList4;
        ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList5, 10));
        Iterator it = arrayList5.iterator();
        while (it.hasNext()) {
            arrayList6.add(Integer.valueOf(((Sport100TypeItem) it.next()).type));
        }
        BLEManager.setSport100TypeSort(arrayList6, arrayList3.size());
    }

    private final void querySport100TypeSortDelay() {
        if (BLEManager.isConnected() && BLEManager.isBind()) {
            StringBuilder sb = new StringBuilder();
            sb.append("delay 3000 to querySport100TypeSort mJob = ");
            Job job = this.mJob;
            sb.append(job != null ? Boolean.valueOf(job.isActive()) : null);
            logP(sb.toString());
            Job job2 = this.mJob;
            if (job2 != null) {
                Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
            }
            this.mJob = CoroutinesUtils.INSTANCE.delay(3000L, new Function0<Unit>() { // from class: com.ido.life.data.cache.MotionTypeManager.querySport100TypeSortDelay.1
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
                    MotionTypeManager.this.logP("It's time to querySport100TypeSort");
                    MotionTypeManager.this.querySport100TypeSort();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void querySport100TypeSort() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        StringBuilder sb = new StringBuilder();
        sb.append("querySport100TypeSort V3_set_100_sport_sort = ");
        sb.append(supportFunctionInfo != null ? Boolean.valueOf(supportFunctionInfo.V3_set_100_sport_sort) : null);
        logP(sb.toString());
        if (supportFunctionInfo == null || !supportFunctionInfo.V3_set_100_sport_sort) {
            return;
        }
        BLEManager.registerOperateCallBack(this.mOperateQueryCallback);
        BLEManager.querySport100TypeSort();
    }

    private final void loadMotionTypesResource() {
        if (INSTANCE.isMotionTypeIconsExist() && !checkMotionResourceVersionChanged()) {
            logP("motion resource exist and version has not changed, so it's necessary to unzip!!!");
        } else {
            if (this.mResourceLoading) {
                logP("motion resource is loading!!!");
                return;
            }
            logP("start to load motion resource!!!");
            this.mResourceLoading = true;
            ExecutorDispatcher.getInstance().dispatch(new Runnable() { // from class: com.ido.life.data.cache.MotionTypeManager.loadMotionTypesResource.1
                @Override // java.lang.Runnable
                public final void run() {
                    File resourceDir = MotionTypeManager.INSTANCE.getResourceDir();
                    try {
                        MotionTypeManager.this.logP("clean the old resource file " + resourceDir.getAbsolutePath());
                        FileUtil.cleanDirectory(resourceDir);
                    } catch (Exception unused) {
                    }
                    try {
                        try {
                            Context appContext = IdoApp.getAppContext();
                            Intrinsics.checkExpressionValueIsNotNull(appContext, "IdoApp.getAppContext()");
                            boolean zUnzip = ZipUtils.unzip(appContext.getAssets().open(MotionTypeManager.RESOURCE_NAME), resourceDir.getAbsolutePath());
                            MotionTypeManager.this.logP("loadMotionTypesResource：" + zUnzip);
                            if (zUnzip) {
                                SPHelper.setMotionResourceVersion(5);
                                MotionTypeManager.this.logP("save the motion resource version 5");
                            }
                        } catch (Exception e2) {
                            MotionTypeManager.this.logP("save the motion resource error: " + e2.getMessage());
                        }
                    } finally {
                        MotionTypeManager.this.mResourceLoading = false;
                    }
                }
            });
        }
    }

    private final boolean checkMotionResourceVersionChanged() {
        int motionResourceVersion = SPHelper.getMotionResourceVersion();
        logP("check the motion resource version, oldVersion = " + motionResourceVersion + ", newVersion = 5");
        return motionResourceVersion != 5;
    }

    /* JADX INFO: compiled from: MotionTypeManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020!0 j\b\u0012\u0004\u0012\u00020!`\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$J\u000e\u0010&\u001a\u00020\u00152\u0006\u0010'\u001a\u00020\fJ\u000e\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\fJ\u000e\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\fJ\u000e\u0010,\u001a\u00020\f2\u0006\u0010-\u001a\u00020\fJ\u000e\u0010.\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\fJ\u0006\u0010/\u001a\u000200J\u0006\u00101\u001a\u000200J\u000e\u00102\u001a\u00020\f2\u0006\u00103\u001a\u00020\fJ\u000e\u00104\u001a\u00020\f2\u0006\u00103\u001a\u00020\fJ\u000e\u00105\u001a\u00020\f2\u0006\u00103\u001a\u00020\fJ\u0006\u00106\u001a\u000207J\u0010\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u0015H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0015X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006;"}, d2 = {"Lcom/ido/life/data/cache/MotionTypeManager$Companion;", "", "()V", "BALL_GROUP", "", "kotlin.jvm.PlatformType", "BASIC_GROUP", "CASUAL_GROUP", "EXTREME_GROUP", "FITNESS_GROUP", "ICE_GROUP", "MAX_MOTION_TYPE", "", "getMAX_MOTION_TYPE", "()I", "setMAX_MOTION_TYPE", "(I)V", "MIN_MOTION_TYPE", "getMIN_MOTION_TYPE", "setMIN_MOTION_TYPE", "RESOURCE_DIR", "", "RESOURCE_NAME", "RESOURCE_NAME_NO_SUFFIX", "RESOURCE_VERSION", "TAG", "WATER_GROUP", "instance", "Lcom/ido/life/data/cache/MotionTypeManager;", "getInstance", "()Lcom/ido/life/data/cache/MotionTypeManager;", "convertToBean", "Ljava/util/ArrayList;", "Lcom/ido/life/bean/MotionTypeBean;", "Lkotlin/collections/ArrayList;", "types", "", "Lcom/ido/ble/protocol/model/Sport100TypeItem;", "getGroupName", "groupType", "getGroupType", "type", "getMotionTypeDetailName", "detailType", "getMotionTypeIcon", "motionType", "getMotionTypeName", "getResourceDir", "Ljava/io/File;", "getResourceUnZipDir", "getSportDetailTypeIcon", "sportId", "getSportHomeTypeIcon", "getSportListTypeIcon", "isMotionTypeIconsExist", "", "logD", "", "msg", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final MotionTypeManager getInstance() {
            return MotionTypeManager.instance;
        }

        public final int getMAX_MOTION_TYPE() {
            return MotionTypeManager.MAX_MOTION_TYPE;
        }

        public final void setMAX_MOTION_TYPE(int i) {
            MotionTypeManager.MAX_MOTION_TYPE = i;
        }

        public final int getMIN_MOTION_TYPE() {
            return MotionTypeManager.MIN_MOTION_TYPE;
        }

        public final void setMIN_MOTION_TYPE(int i) {
            MotionTypeManager.MIN_MOTION_TYPE = i;
        }

        private final void logD(String msg) {
            CommonLogUtil.printAndSave(msg);
        }

        public final boolean isMotionTypeIconsExist() {
            File[] fileArrListFiles;
            try {
                File resourceDir = getResourceDir();
                boolean zExists = resourceDir.exists();
                logD("isMotionTypeIconsExist,     dir = " + resourceDir.getAbsolutePath() + ",     exist = " + zExists);
                if (!zExists || (fileArrListFiles = resourceDir.listFiles()) == null) {
                    return false;
                }
                return (fileArrListFiles.length == 0) ^ true;
            } catch (Exception e2) {
                logD("isMotionTypeIconsExist,   error：" + e2);
                return false;
            }
        }

        public final File getResourceUnZipDir() {
            File dir = IdoApp.getAppContext().getDir("motion_types", 0);
            Intrinsics.checkExpressionValueIsNotNull(dir, "IdoApp.getAppContext().g…IR, Context.MODE_PRIVATE)");
            return dir;
        }

        public final File getResourceDir() {
            File dir = IdoApp.getAppContext().getDir("motion_types", 0);
            Intrinsics.checkExpressionValueIsNotNull(dir, "IdoApp.getAppContext().g…IR, Context.MODE_PRIVATE)");
            return dir;
        }

        public final ArrayList<MotionTypeBean> convertToBean(List<Sport100TypeItem> types) {
            Intrinsics.checkParameterIsNotNull(types, "types");
            List<Sport100TypeItem> list = types;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (Sport100TypeItem sport100TypeItem : list) {
                int i = sport100TypeItem.type;
                arrayList.add(new MotionTypeBean(i, MotionTypeManager.INSTANCE.getGroupType(i), MotionTypeManager.INSTANCE.getMotionTypeName(i), MotionTypeManager.INSTANCE.getMotionTypeIcon(i), sport100TypeItem.flag, false, 32, null));
            }
            ArrayList<MotionTypeBean> arrayList2 = new ArrayList<>();
            arrayList2.addAll(arrayList);
            return arrayList2;
        }

        public final int getMotionTypeIcon(int motionType) {
            return ResourceUtils.INSTANCE.getMipmapResId(Constant.PREFIX_MOTION_RESOURCE + motionType);
        }

        public final String getMotionTypeName(int motionType) {
            String languageText;
            int stringResId = ResourceUtil.getStringResId(Constant.PREFIX_MOTION_RESOURCE + motionType);
            if (stringResId > 0) {
                String languageText2 = LanguageUtil.getLanguageText(stringResId);
                Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguageText(resId)");
                return languageText2;
            }
            if (motionType == 1) {
                languageText = LanguageUtil.getLanguageText(R.string.sport_tab_walk);
            } else if (motionType == 2) {
                languageText = LanguageUtil.getLanguageText(R.string.sport_tab_run);
            } else if (motionType == 3) {
                languageText = LanguageUtil.getLanguageText(R.string.sport_tab_ride);
            } else if (motionType == 5) {
                languageText = LanguageUtil.getLanguageText(R.string.mine_sport_swim);
            } else if (motionType == 12) {
                languageText = LanguageUtil.getLanguageText(R.string.sport_treadmill_android);
            } else if (motionType == 16) {
                languageText = LanguageUtil.getLanguageText(R.string.sport_weightlifting);
            } else if (motionType == 23) {
                languageText = LanguageUtil.getLanguageText(R.string.sport_volleyball);
            } else if (motionType == 38) {
                languageText = LanguageUtil.getLanguageText(R.string.old_sport_gym);
            } else if (motionType == 9) {
                languageText = LanguageUtil.getLanguageText(R.string.sport_record_fitness);
            } else if (motionType == 10) {
                languageText = LanguageUtil.getLanguageText(R.string.sport_type_dynamic_bicycle);
            } else if (motionType == 28) {
                languageText = LanguageUtil.getLanguageText(R.string.sport_roller_skating);
            } else if (motionType == 29) {
                languageText = LanguageUtil.getLanguageText(R.string.sport_record_dancing);
            } else if (motionType == 193) {
                languageText = LanguageUtil.getLanguageText(R.string.sport_fun_outdoor);
            } else if (motionType != 194) {
                switch (motionType) {
                    case 33:
                        languageText = LanguageUtil.getLanguageText(R.string.old_sport_cross_train);
                        break;
                    case 34:
                        languageText = LanguageUtil.getLanguageText(R.string.old_sport_cardio);
                        break;
                    case 35:
                        languageText = LanguageUtil.getLanguageText(R.string.old_sport_cross_zumba);
                        break;
                    default:
                        languageText = "";
                        break;
                }
            } else {
                languageText = LanguageUtil.getLanguageText(R.string.me_other_activity);
            }
            Intrinsics.checkExpressionValueIsNotNull(languageText, "when (motionType) {\n    …      }\n                }");
            return languageText;
        }

        public final int getSportListTypeIcon(int sportId) {
            int mipmapResId = ResourceUtils.INSTANCE.getMipmapResId(Constant.PREFIX_SPORT_RECORD_LIST_RESOURCE + sportId);
            if (mipmapResId > 0) {
                return mipmapResId;
            }
            if (sportId == 1) {
                return R.mipmap.icon_sport_type_record_list_52;
            }
            if (sportId == 2) {
                return R.mipmap.icon_sport_type_record_list_48;
            }
            if (sportId == 3) {
                return R.mipmap.icon_sport_type_record_list_50;
            }
            if (sportId == 5) {
                return R.mipmap.icon_sport_type_record_list_55;
            }
            if (sportId == 12) {
                return R.mipmap.icon_sport_type_record_list_53;
            }
            if (sportId == 16) {
                return R.mipmap.icon_sport_type_record_list_101;
            }
            if (sportId == 23) {
                return R.mipmap.icon_sport_type_record_list_23;
            }
            if (sportId == 38) {
                return R.mipmap.icon_sport_type_record_list_gym;
            }
            if (sportId == 9) {
                return R.mipmap.icon_sport_type_record_list_9;
            }
            if (sportId == 10) {
                return R.mipmap.icon_sport_type_record_list_10;
            }
            if (sportId == 28) {
                return R.mipmap.icon_sport_type_record_list_28;
            }
            if (sportId == 29) {
                return R.mipmap.icon_sport_type_record_list_29;
            }
            if (sportId == 193) {
                return R.mipmap.icon_sport_type_record_list_fun_outdoor;
            }
            if (sportId == 194) {
                return R.mipmap.icon_sport_type_record_list_9;
            }
            switch (sportId) {
                case 33:
                    return R.mipmap.icon_sport_type_record_list_33;
                case 34:
                    return R.mipmap.icon_sport_type_record_list_cardio;
                case 35:
                    return R.mipmap.icon_sport_type_record_list_35;
                default:
                    return R.mipmap.icon_sport_type_record_list_9;
            }
        }

        public final int getSportHomeTypeIcon(int sportId) {
            int mipmapResId = ResourceUtils.INSTANCE.getMipmapResId(Constant.PREFIX_SPORT_HOME_RESOURCE + sportId);
            if (mipmapResId > 0) {
                return mipmapResId;
            }
            if (sportId == 1) {
                return R.mipmap.icon_sport_type_home_detail_52;
            }
            if (sportId == 2) {
                return R.mipmap.icon_sport_type_home_detail_48;
            }
            if (sportId == 3) {
                return R.mipmap.icon_sport_type_home_detail_50;
            }
            if (sportId == 5) {
                return R.mipmap.icon_sport_type_home_detail_55;
            }
            if (sportId == 12) {
                return R.mipmap.icon_sport_type_home_detail_53;
            }
            if (sportId == 16) {
                return R.mipmap.icon_sport_type_home_detail_101;
            }
            if (sportId == 23) {
                return R.mipmap.icon_sport_type_home_detail_23;
            }
            if (sportId == 38) {
                return R.mipmap.icon_sport_type_home_detail_gym;
            }
            if (sportId == 9) {
                return R.mipmap.icon_sport_type_home_detail_9;
            }
            if (sportId == 10) {
                return R.mipmap.icon_sport_type_home_detail_10;
            }
            if (sportId == 28) {
                return R.mipmap.icon_sport_type_home_detail_28;
            }
            if (sportId == 29) {
                return R.mipmap.icon_sport_type_home_detail_29;
            }
            if (sportId == 193) {
                return R.mipmap.icon_sport_type_home_detail_fun_outdoor;
            }
            if (sportId == 194) {
                return R.mipmap.icon_sport_type_home_detail_9;
            }
            switch (sportId) {
                case 33:
                    return R.mipmap.icon_sport_type_home_detail_33;
                case 34:
                    return R.mipmap.icon_sport_type_home_detail_cardio;
                case 35:
                    return R.mipmap.icon_sport_type_home_detail_35;
                default:
                    return R.mipmap.icon_sport_type_home_detail_9;
            }
        }

        public final int getSportDetailTypeIcon(int sportId) {
            int mipmapResId = ResourceUtils.INSTANCE.getMipmapResId(Constant.PREFIX_SPORT_DETAIL_RESOURCE + sportId);
            if (mipmapResId > 0) {
                return mipmapResId;
            }
            if (sportId == 1) {
                return R.mipmap.icon_sport_type_detail_52;
            }
            if (sportId == 2) {
                return R.mipmap.icon_sport_type_detail_48;
            }
            if (sportId == 3) {
                return R.mipmap.icon_sport_type_detail_50;
            }
            if (sportId == 5) {
                return R.mipmap.icon_sport_type_detail_55;
            }
            if (sportId == 12) {
                return R.mipmap.icon_sport_type_detail_53;
            }
            if (sportId == 16) {
                return R.mipmap.icon_sport_type_detail_101;
            }
            if (sportId == 23) {
                return R.mipmap.icon_sport_type_detail_23;
            }
            if (sportId == 38) {
                return R.mipmap.icon_sport_type_detail_gym;
            }
            if (sportId == 9) {
                return R.mipmap.icon_sport_type_detail_9;
            }
            if (sportId == 10) {
                return R.mipmap.icon_sport_type_detail_10;
            }
            if (sportId == 28) {
                return R.mipmap.icon_sport_type_detail_28;
            }
            if (sportId == 29) {
                return R.mipmap.icon_sport_type_detail_29;
            }
            if (sportId == 193) {
                return R.mipmap.icon_sport_type_detail_fun_outdoor;
            }
            if (sportId == 194) {
                return R.mipmap.icon_sport_type_detail_9;
            }
            switch (sportId) {
                case 33:
                    return R.mipmap.icon_sport_type_detail_33;
                case 34:
                    return R.mipmap.icon_sport_type_detail_cardio;
                case 35:
                    return R.mipmap.icon_sport_type_detail_35;
                default:
                    return R.mipmap.icon_sport_type_detail_9;
            }
        }

        public final int getGroupType(int type) {
            int[] FITNESS_GROUP = MotionTypeManager.FITNESS_GROUP;
            Intrinsics.checkExpressionValueIsNotNull(FITNESS_GROUP, "FITNESS_GROUP");
            if (ArraysKt.contains(FITNESS_GROUP, type)) {
                return 2;
            }
            int[] BALL_GROUP = MotionTypeManager.BALL_GROUP;
            Intrinsics.checkExpressionValueIsNotNull(BALL_GROUP, "BALL_GROUP");
            if (ArraysKt.contains(BALL_GROUP, type)) {
                return 3;
            }
            int[] CASUAL_GROUP = MotionTypeManager.CASUAL_GROUP;
            Intrinsics.checkExpressionValueIsNotNull(CASUAL_GROUP, "CASUAL_GROUP");
            if (ArraysKt.contains(CASUAL_GROUP, type)) {
                return 4;
            }
            int[] ICE_GROUP = MotionTypeManager.ICE_GROUP;
            Intrinsics.checkExpressionValueIsNotNull(ICE_GROUP, "ICE_GROUP");
            if (ArraysKt.contains(ICE_GROUP, type)) {
                return 5;
            }
            int[] WATER_GROUP = MotionTypeManager.WATER_GROUP;
            Intrinsics.checkExpressionValueIsNotNull(WATER_GROUP, "WATER_GROUP");
            if (ArraysKt.contains(WATER_GROUP, type)) {
                return 6;
            }
            int[] EXTREME_GROUP = MotionTypeManager.EXTREME_GROUP;
            Intrinsics.checkExpressionValueIsNotNull(EXTREME_GROUP, "EXTREME_GROUP");
            if (ArraysKt.contains(EXTREME_GROUP, type)) {
                return 7;
            }
            ArraysKt.contains(MotionTypeManager.BASIC_GROUP, type);
            return 1;
        }

        public final String getGroupName(int groupType) {
            int i;
            switch (groupType) {
                case 2:
                    i = R.string.motion_type_fitness_title;
                    break;
                case 3:
                    i = R.string.motion_type_ball_title;
                    break;
                case 4:
                    i = R.string.motion_type_casual_title;
                    break;
                case 5:
                    i = R.string.motion_type_ice_title;
                    break;
                case 6:
                    i = R.string.motion_type_water_title;
                    break;
                case 7:
                    i = R.string.motion_type_extreme_title;
                    break;
                default:
                    i = R.string.motion_type_basic_title;
                    break;
            }
            String string = ResourceUtil.getString(i);
            Intrinsics.checkExpressionValueIsNotNull(string, "ResourceUtil.getString(resId)");
            return string;
        }

        public final String getMotionTypeDetailName(int detailType) {
            int stringResId = ResourceUtil.getStringResId("motion_type_detail_" + detailType);
            if (stringResId <= 0) {
                return "";
            }
            String languageText = LanguageUtil.getLanguageText(stringResId);
            Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguageText(nameRes)");
            return languageText;
        }
    }

    static {
        int[] integerArray = ResourceUtil.getIntegerArray(R.array.motion_basic);
        Intrinsics.checkExpressionValueIsNotNull(integerArray, "ResourceUtil.getIntegerArray(R.array.motion_basic)");
        BASIC_GROUP = integerArray;
        FITNESS_GROUP = ResourceUtil.getIntegerArray(R.array.motion_fitness);
        BALL_GROUP = ResourceUtil.getIntegerArray(R.array.motion_ball);
        CASUAL_GROUP = ResourceUtil.getIntegerArray(R.array.motion_casual);
        ICE_GROUP = ResourceUtil.getIntegerArray(R.array.motion_ice);
        WATER_GROUP = ResourceUtil.getIntegerArray(R.array.motion_water);
        EXTREME_GROUP = ResourceUtil.getIntegerArray(R.array.motion_extreme);
        MAX_MOTION_TYPE = 20;
        MIN_MOTION_TYPE = 1;
    }
}