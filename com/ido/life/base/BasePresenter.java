package com.ido.life.base;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.base.IBaseView;
import com.ido.life.constants.Constants;
import com.ido.life.data.ExecutorDispatcher;
import com.ido.life.data.Func;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.data.listener.Callback;
import com.ido.life.util.SPHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BasePresenter<V extends IBaseView> {
    private ArrayList<Future> futures;
    private Handler mHandler = null;
    protected WeakReference<V> mWeak;

    public void attachView(V v) {
        this.mWeak = new WeakReference<>(v);
        init();
    }

    public void init() {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.futures = new ArrayList<>();
    }

    protected boolean isAttachView() {
        WeakReference<V> weakReference = this.mWeak;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public V getView() {
        WeakReference<V> weakReference = this.mWeak;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public void detachView() {
        WeakReference<V> weakReference = this.mWeak;
        if (weakReference != null) {
            weakReference.clear();
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
        ArrayList<Future> arrayList = this.futures;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        for (Future future : this.futures) {
            if (future.isCancelled()) {
                future.cancel(true);
            }
        }
    }

    public void deleteDevice() {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBindLogPath(), BasePresenter.class.getSimpleName(), "deleteDevice 强制解除绑定设备device=" + currentDeviceInfo.toString());
            BLEManager.forceUnbind(currentDeviceInfo.mDeviceAddress);
            SPHelper.removeDevice(new DeviceListEntity.DeviceInfo(currentDeviceInfo));
            BLEManager.removeBondStatusFromPhoneBluetoothPairedList(currentDeviceInfo.mDeviceAddress);
            EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_DEVICE_LIST_CHANGED, currentDeviceInfo.mDeviceAddress));
        }
    }

    protected <T> Future addSubscriber(final Func<T> func, final Callback<T> callback) {
        return ExecutorDispatcher.getInstance().dispatch(new Runnable() { // from class: com.ido.life.base.-$$Lambda$BasePresenter$59aTB_l2_tu-1fj5GFzxO2mETgY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$addSubscriber$2$BasePresenter(func, callback);
            }
        });
    }

    public /* synthetic */ void lambda$addSubscriber$2$BasePresenter(Func func, final Callback callback) {
        try {
            final Object objCall = func.call();
            if (this.mHandler != null) {
                this.mHandler.post(new Runnable() { // from class: com.ido.life.base.-$$Lambda$BasePresenter$eGOIklc9djp1_2ypYiC51wCFpZA
                    @Override // java.lang.Runnable
                    public final void run() {
                        callback.onSuccess(objCall);
                    }
                });
            }
        } catch (Exception unused) {
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.ido.life.base.-$$Lambda$BasePresenter$HSvIuJHj0pmbmjVTI5wqNNNS6bY
                    @Override // java.lang.Runnable
                    public final void run() {
                        callback.onFailed("");
                    }
                });
            }
        }
    }

    protected <T> void addAutoCancelSubscriber(final Func<T> func, final Callback<T> callback) {
        this.futures.add(ExecutorDispatcher.getInstance().dispatch(new Runnable() { // from class: com.ido.life.base.-$$Lambda$BasePresenter$Hemzr1lUiwaSzo08R4zbJc_H_r8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$addAutoCancelSubscriber$5$BasePresenter(func, callback);
            }
        }));
    }

    public /* synthetic */ void lambda$addAutoCancelSubscriber$5$BasePresenter(Func func, final Callback callback) {
        try {
            final Object objCall = func.call();
            if (this.mHandler != null) {
                this.mHandler.post(new Runnable() { // from class: com.ido.life.base.-$$Lambda$BasePresenter$ipH-iGIgmO98o1g8WqvVpv8CZRc
                    @Override // java.lang.Runnable
                    public final void run() {
                        callback.onSuccess(objCall);
                    }
                });
            }
        } catch (Exception unused) {
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.ido.life.base.-$$Lambda$BasePresenter$wu6O6cQ7AzshB6R6En-0iUZrUBY
                    @Override // java.lang.Runnable
                    public final void run() {
                        callback.onFailed("");
                    }
                });
            }
        }
    }
}