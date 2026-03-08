package com.ido.life.module.device.presenter;

import android.util.SparseIntArray;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.OperateCallBack;
import com.ido.ble.event.stat.one.d;
import com.ido.ble.protocol.model.Sport100TypeItem;
import com.ido.ble.protocol.model.Sport100TypeSort;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.IBaseView;
import com.ido.life.bean.IconTransBean;
import com.ido.life.bean.MotionTypeBean;
import com.ido.life.ble.BaseOperateCallback;
import com.ido.life.data.Func;
import com.ido.life.data.cache.MotionTypeManager;
import com.ido.life.data.listener.Callback;
import com.ido.life.data.listener.OnMotionTypeQueryCallback;
import com.ido.life.util.ListUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: BaseMotionTypesPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000*\u0001\u0014\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020 H\u0016J\b\u0010%\u001a\u00020 H\u0014J \u0010&\u001a\u00020 2\u0016\u0010'\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\nj\b\u0012\u0004\u0012\u00020\u000e`\fH\u0015J\u0010\u0010(\u001a\u00020 2\u0006\u0010)\u001a\u00020#H\u0014J\u0018\u0010*\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0014J\b\u0010+\u001a\u00020 H\u0014J\b\u0010,\u001a\u00020 H\u0014J\u0012\u0010-\u001a\u00020 2\b\u0010.\u001a\u0004\u0018\u00010/H\u0004R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0018\u00010\nj\n\u0012\u0004\u0012\u00020\u000e\u0018\u0001`\fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/ido/life/module/device/presenter/BaseMotionTypesPresenter;", "V", "Lcom/ido/life/base/IBaseView;", "Lcom/ido/life/base/BaseCmdPresenter;", "()V", "isTransIcon", "Ljava/util/concurrent/atomic/AtomicBoolean;", "mAnimationIconTypes", "", "mFailedTransMotionType", "Ljava/util/ArrayList;", "Lcom/ido/life/bean/IconTransBean;", "Lkotlin/collections/ArrayList;", "mMotionTypes", "Lcom/ido/life/bean/MotionTypeBean;", "getMMotionTypes", "()Ljava/util/ArrayList;", "setMMotionTypes", "(Ljava/util/ArrayList;)V", "mOperateCallback", "com/ido/life/module/device/presenter/BaseMotionTypesPresenter$mOperateCallback$1", "Lcom/ido/life/module/device/presenter/BaseMotionTypesPresenter$mOperateCallback$1;", "mTransStatus", "Landroid/util/SparseIntArray;", "getMTransStatus", "()Landroid/util/SparseIntArray;", "setMTransStatus", "(Landroid/util/SparseIntArray;)V", "transformIndex", "", "transformMaxCount", "detachView", "", "getDeviceMotionTypes", "forceUpdate", "", d.m, "onGetDeviceMotionTypesFailed", "onGetDeviceMotionTypesSuccess", "list", "onIconTransComplete", "isSuccess", "onIconTransProgress", "onOperateSetFailed", "onOperateSetSuccess", "transformData", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Lcom/ido/ble/protocol/model/Sport100TypeSort;", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class BaseMotionTypesPresenter<V extends IBaseView> extends BaseCmdPresenter<V> {
    private int[] mAnimationIconTypes;
    private ArrayList<MotionTypeBean> mMotionTypes;
    private volatile int transformIndex;
    private volatile int transformMaxCount;
    private volatile ArrayList<IconTransBean> mFailedTransMotionType = new ArrayList<>();
    private SparseIntArray mTransStatus = new SparseIntArray();
    private AtomicBoolean isTransIcon = new AtomicBoolean(false);
    private final BaseMotionTypesPresenter$mOperateCallback$1 mOperateCallback = new BaseOperateCallback() { // from class: com.ido.life.module.device.presenter.BaseMotionTypesPresenter$mOperateCallback$1
        @Override // com.ido.life.ble.BaseOperateCallback, com.ido.ble.callback.OperateCallBack.ICallBack
        public void onSetResult(OperateCallBack.OperateType operateType, boolean b2) {
            if (operateType == OperateCallBack.OperateType.SPORT_100_TYPE_SORT) {
                CommonLogUtil.printAndSave("onSetResult：" + b2);
                if (b2) {
                    this.this$0.onOperateSetSuccess();
                } else {
                    this.this$0.onOperateSetFailed();
                }
            }
        }

        @Override // com.ido.life.ble.BaseOperateCallback, com.ido.ble.callback.OperateCallBack.ICallBack
        public void onQueryResult(OperateCallBack.OperateType operateType, Object o) {
            if (operateType == OperateCallBack.OperateType.SPORT_100_TYPE_SORT) {
                CommonLogUtil.printAndSave("onQueryResult：" + o);
                if (o != null) {
                    boolean z = o instanceof Sport100TypeSort;
                }
            }
        }
    };

    protected void onGetDeviceMotionTypesFailed() {
    }

    protected void onGetDeviceMotionTypesSuccess(ArrayList<MotionTypeBean> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
    }

    protected void onIconTransComplete(boolean isSuccess) {
    }

    protected void onOperateSetFailed() {
    }

    protected void onOperateSetSuccess() {
    }

    protected final ArrayList<MotionTypeBean> getMMotionTypes() {
        return this.mMotionTypes;
    }

    protected final void setMMotionTypes(ArrayList<MotionTypeBean> arrayList) {
        this.mMotionTypes = arrayList;
    }

    protected final SparseIntArray getMTransStatus() {
        return this.mTransStatus;
    }

    protected final void setMTransStatus(SparseIntArray sparseIntArray) {
        Intrinsics.checkParameterIsNotNull(sparseIntArray, "<set-?>");
        this.mTransStatus = sparseIntArray;
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        BLEManager.registerOperateCallBack(this.mOperateCallback);
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BLEManager.unregisterOperateCallBack(this.mOperateCallback);
    }

    protected final void transformData(final Sport100TypeSort data) {
        addAutoCancelSubscriber((Func) new Func<Pair<? extends List<MotionTypeBean>, ? extends ArrayList<MotionTypeBean>>>() { // from class: com.ido.life.module.device.presenter.BaseMotionTypesPresenter.transformData.1
            @Override // com.ido.life.data.Func
            public Pair<? extends List<MotionTypeBean>, ? extends ArrayList<MotionTypeBean>> call() {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = (List) null;
                if (data != null) {
                    MotionTypeManager.INSTANCE.setMAX_MOTION_TYPE(data.max_show_num);
                    MotionTypeManager.INSTANCE.setMIN_MOTION_TYPE(data.min_show_num);
                    int i = data.now_user_location;
                    if (ListUtils.INSTANCE.isNotEmpty(data.items)) {
                        MotionTypeManager.Companion companion = MotionTypeManager.INSTANCE;
                        List<Sport100TypeItem> list = data.items;
                        Intrinsics.checkExpressionValueIsNotNull(list, "data.items");
                        arrayList2 = companion.convertToBean(list);
                        if (i >= 0) {
                            arrayList.addAll(arrayList2.subList(0, i));
                        }
                    }
                }
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                }
                return new Pair<>(arrayList2, arrayList);
            }
        }, (Callback) new Callback<Pair<? extends List<MotionTypeBean>, ? extends ArrayList<MotionTypeBean>>>() { // from class: com.ido.life.module.device.presenter.BaseMotionTypesPresenter.transformData.2
            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(Pair<? extends List<MotionTypeBean>, ? extends ArrayList<MotionTypeBean>> data2) {
                Intrinsics.checkParameterIsNotNull(data2, "data");
                try {
                    MotionTypeManager.INSTANCE.getInstance().setMAllMotionTypes(data2.getFirst());
                    MotionTypeManager.INSTANCE.getInstance().setMMotionTypesInDevice(data2.getSecond());
                } catch (Exception unused) {
                }
                BaseMotionTypesPresenter.this.onGetDeviceMotionTypesSuccess(data2.getSecond());
            }

            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String errMsg) {
                Intrinsics.checkParameterIsNotNull(errMsg, "errMsg");
                CommonLogUtil.printAndSave("transformData failed: " + errMsg);
                BaseMotionTypesPresenter.this.onGetDeviceMotionTypesFailed();
            }
        });
    }

    public void getDeviceMotionTypes(boolean forceUpdate) {
        MotionTypeManager.INSTANCE.getInstance().getMotionTypes(new OnMotionTypeQueryCallback() { // from class: com.ido.life.module.device.presenter.BaseMotionTypesPresenter.getDeviceMotionTypes.1
            @Override // com.ido.life.data.listener.OnMotionTypeQueryCallback
            public void onMotionTypeQuery(ArrayList<MotionTypeBean> types) {
                BaseMotionTypesPresenter baseMotionTypesPresenter = BaseMotionTypesPresenter.this;
                if (types == null) {
                    types = new ArrayList<>();
                }
                baseMotionTypesPresenter.onGetDeviceMotionTypesSuccess(types);
            }
        }, forceUpdate);
    }

    protected void onIconTransProgress(int transformIndex, int transformMaxCount) {
        CommonLogUtil.printAndSave("onIconTransProgress -> " + transformIndex + IOUtils.DIR_SEPARATOR_UNIX + transformMaxCount);
    }
}