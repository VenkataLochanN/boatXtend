package com.ido.life.module.device.presenter;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.WatchDialOrder;
import com.ido.ble.watch.custom.callback.PhotoWallpaperOperateCallBack;
import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
import com.ido.life.base.BaseMessage;
import com.ido.life.constants.Constants;
import com.ido.life.data.Func;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.data.listener.Callback;
import com.ido.life.module.device.view.IMyDialView;
import com.ido.life.module.main.MainPresenter;
import com.ido.life.util.eventbus.EventBusHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MydialEditPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006J\u0014\u0010\r\u001a\u00020\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u000eH\u0002J\b\u0010\u0017\u001a\u00020\u000eH\u0002J\b\u0010\u0018\u001a\u00020\u000eH\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0006\u0010\u001b\u001a\u00020\u000eJ\b\u0010\u001c\u001a\u00020\u0015H\u0016J\u0014\u0010\u001d\u001a\u00020\u000e2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/ido/life/module/device/presenter/MydialEditPresenter;", "Lcom/ido/life/module/device/presenter/MyDialPresenter;", "()V", "DIAL_TIMEOUT", "", "mDeleteDialName", "", "mDialRemoveRunnable", "Ljava/lang/Runnable;", "mHandler", "Landroid/os/Handler;", "mSettingCallback", "Lcom/ido/ble/callback/SettingCallBack$ICallBack;", "deleteDial", "", "dialName", "list", "", "Lcom/ido/life/data/api/entity/MyDialListEntity$DialInfo;", "deleteDialResult", "succeed", "", "deleteLocalWallpaper", "deleteWallpaperDial", "detachView", "getDialImageAspectRatio", "", "initSettingCallBack", "isBracelet", "setDialOrder", "mMyDialList", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MydialEditPresenter extends MyDialPresenter {
    private final Runnable mDialRemoveRunnable;
    private SettingCallBack.ICallBack mSettingCallback;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final long DIAL_TIMEOUT = 30000;
    private String mDeleteDialName = "";

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SettingCallBack.SettingType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[SettingCallBack.SettingType.WATCH_DIAL_ORDER.ordinal()] = 1;
            $EnumSwitchMapping$1 = new int[SettingCallBack.SettingType.values().length];
            $EnumSwitchMapping$1[SettingCallBack.SettingType.WATCH_DIAL_ORDER.ordinal()] = 1;
        }
    }

    public MydialEditPresenter() {
        initSettingCallBack();
        this.mDialRemoveRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.MydialEditPresenter$mDialRemoveRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                this.this$0.saveDialLog("删除表盘超时");
                if (this.this$0.isAttachView()) {
                    MydialEditPresenter.access$getView(this.this$0).onDeleteDial(false);
                }
            }
        };
    }

    public static final /* synthetic */ IMyDialView access$getView(MydialEditPresenter mydialEditPresenter) {
        return (IMyDialView) mydialEditPresenter.getView();
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    public float getDialImageAspectRatio() {
        Float f2;
        BLEDevice deviceInfo = getDeviceInfo();
        if (MainPresenter.screenRatioMap.containsKey(deviceInfo.mDeviceAddress) && (f2 = MainPresenter.screenRatioMap.get(deviceInfo.mDeviceAddress)) != null && (!Intrinsics.areEqual(f2, 0.0f))) {
            return f2.floatValue();
        }
        if (isBindAndConnected()) {
            BLEManager.getWatchPlateScreenInfo();
        }
        return isBracelet() ? 2.0f : 1.128f;
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    public boolean isBracelet() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        return basicInfo != null && basicInfo.dev_type == 1;
    }

    public final void deleteDial(List<MyDialListEntity.DialInfo> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        for (MyDialListEntity.DialInfo dialInfo : list) {
            this.mHandler.postDelayed(this.mDialRemoveRunnable, this.DIAL_TIMEOUT);
            BLEManager.deleteWatchPlate(dialInfo.getOtaFaceName());
        }
    }

    public final void deleteDial(String dialName) {
        Intrinsics.checkParameterIsNotNull(dialName, "dialName");
        this.mDeleteDialName = dialName;
        this.mHandler.postDelayed(this.mDialRemoveRunnable, this.DIAL_TIMEOUT);
        if ((!Intrinsics.areEqual(dialName, BaseDialPresenter.WALLPAPER_DIAL_NAME)) && !StringsKt.contains$default((CharSequence) dialName, (CharSequence) ".iwf", false, 2, (Object) null)) {
            BLEManager.deleteWatchPlate(dialName + ".iwf");
            return;
        }
        if (StringsKt.contains$default((CharSequence) dialName, (CharSequence) ".iwf", false, 2, (Object) null)) {
            BLEManager.deleteWatchPlate(dialName);
        } else if (Intrinsics.areEqual(dialName, BaseDialPresenter.WALLPAPER_DIAL_NAME)) {
            deleteWallpaperDial();
        }
    }

    private final void deleteWallpaperDial() {
        saveDialLog("deleteWallpaperDial start");
        if (isAttachView()) {
            this.isSetting = true;
            this.mHandler.removeCallbacks(this.mDialRemoveRunnable);
            this.mHandler.postDelayed(this.mDialRemoveRunnable, this.DIAL_TIMEOUT);
            BLEManager.registerPhotoWallpaperOperateCallBack(new PhotoWallpaperOperateCallBack.ICallBack() { // from class: com.ido.life.module.device.presenter.MydialEditPresenter.deleteWallpaperDial.1
                @Override // com.ido.ble.watch.custom.callback.PhotoWallpaperOperateCallBack.ICallBack
                public void onOperateResult(PhotoWallpaperOperation.ResponseInfo responseInfo) {
                    MydialEditPresenter.this.saveDialLog("getWallpaperDialInfo, onOperateResult : " + responseInfo);
                    if (responseInfo != null && responseInfo.operate == 2) {
                        BLEManager.unregisterPhotoWallpaperOperateCallBack(this);
                        MydialEditPresenter.this.mHandler.removeCallbacks(MydialEditPresenter.this.mDialRemoveRunnable);
                        MydialEditPresenter mydialEditPresenter = MydialEditPresenter.this;
                        mydialEditPresenter.isSetting = false;
                        mydialEditPresenter.deleteDialResult(responseInfo.err_code == 0);
                        if (responseInfo.err_code == 0) {
                            MydialEditPresenter.this.deleteLocalWallpaper();
                        }
                    }
                }
            });
            PhotoWallpaperOperation.SetParams setParams = new PhotoWallpaperOperation.SetParams();
            setParams.operate = 2;
            BLEManager.photoWallpaperOperate(setParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteLocalWallpaper() {
        addSubscriber(new Func<Boolean>() { // from class: com.ido.life.module.device.presenter.MydialEditPresenter.deleteLocalWallpaper.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ido.life.data.Func
            public Boolean call() {
                boolean z;
                try {
                    String wallPaperDialPath = MydialEditPresenter.this.getWallPaperDialPath();
                    if (!TextUtils.isEmpty(wallPaperDialPath)) {
                        File file = new File(wallPaperDialPath);
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                    z = true;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        }, new Callback<Boolean>() { // from class: com.ido.life.module.device.presenter.MydialEditPresenter.deleteLocalWallpaper.2
            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String errMsg) {
                Intrinsics.checkParameterIsNotNull(errMsg, "errMsg");
            }

            @Override // com.ido.life.data.listener.Callback
            public /* bridge */ /* synthetic */ void onSuccess(Boolean bool) {
                onSuccess(bool.booleanValue());
            }

            public void onSuccess(boolean data) {
                MydialEditPresenter.this.saveDialLog("deleteLocalWallpaper：" + data);
                EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_DIAL_DELETE, MydialEditPresenter.this.mDeleteDialName));
            }
        });
    }

    @Override // com.ido.life.module.device.presenter.BaseDialPresenter
    protected void deleteDialResult(boolean succeed) {
        super.deleteDialResult(succeed);
        saveDialLog("deleteDialResult ：" + succeed);
        this.mHandler.removeCallbacks(this.mDialRemoveRunnable);
        if (succeed && !TextUtils.isEmpty(this.mDeleteDialName)) {
            EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_DIAL_DELETE, this.mDeleteDialName));
        }
        ((IMyDialView) getView()).onDeleteDial(succeed);
    }

    public final void setDialOrder(List<MyDialListEntity.DialInfo> mMyDialList) {
        Intrinsics.checkParameterIsNotNull(mMyDialList, "mMyDialList");
        WatchDialOrder watchDialOrder = new WatchDialOrder();
        watchDialOrder.sort_item_numb = mMyDialList.size();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (MyDialListEntity.DialInfo dialInfo : mMyDialList) {
            WatchDialOrder.WatchDialInfo watchDialInfo = new WatchDialOrder.WatchDialInfo();
            watchDialInfo.name = dialInfo.getFaceTypeName();
            watchDialInfo.sort_number = i;
            arrayList.add(watchDialInfo);
            i++;
        }
        watchDialOrder.p_sort_item = arrayList;
        SettingCallBack.ICallBack iCallBack = this.mSettingCallback;
        if (iCallBack == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSettingCallback");
        }
        BLEManager.registerSettingCallBack(iCallBack);
        BLEManager.setWatchDialOrder(watchDialOrder);
    }

    public final void initSettingCallBack() {
        this.mSettingCallback = new SettingCallBack.ICallBack() { // from class: com.ido.life.module.device.presenter.MydialEditPresenter.initSettingCallBack.1
            @Override // com.ido.ble.callback.SettingCallBack.ICallBack
            public void onSuccess(SettingCallBack.SettingType type, Object p1) {
                IMyDialView iMyDialViewAccess$getView;
                if (type == null || WhenMappings.$EnumSwitchMapping$0[type.ordinal()] != 1 || (iMyDialViewAccess$getView = MydialEditPresenter.access$getView(MydialEditPresenter.this)) == null) {
                    return;
                }
                iMyDialViewAccess$getView.onDialOrder(true);
            }

            @Override // com.ido.ble.callback.SettingCallBack.ICallBack
            public void onFailed(SettingCallBack.SettingType type) {
                IMyDialView iMyDialViewAccess$getView;
                if (type == null || WhenMappings.$EnumSwitchMapping$1[type.ordinal()] != 1 || (iMyDialViewAccess$getView = MydialEditPresenter.access$getView(MydialEditPresenter.this)) == null) {
                    return;
                }
                iMyDialViewAccess$getView.onDialOrder(false);
            }
        };
    }

    @Override // com.ido.life.module.device.presenter.BaseDialPresenter, com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        SettingCallBack.ICallBack iCallBack = this.mSettingCallback;
        if (iCallBack == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSettingCallback");
        }
        BLEManager.unregisterSettingCallBack(iCallBack);
    }
}