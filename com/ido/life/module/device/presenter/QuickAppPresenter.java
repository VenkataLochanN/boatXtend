package com.ido.life.module.device.presenter;

import android.os.Handler;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.MenuList;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.BaseDeviceParaCallBack;
import com.ido.life.bean.SortBean;
import com.ido.life.module.device.view.IQuickAppView;
import com.ido.life.util.FunctionHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class QuickAppPresenter extends BaseCmdPresenter<IQuickAppView> {
    private static final int TYPE_COUNT_MAX = Integer.MAX_VALUE;
    private static final int TYPE_COUNT_MIN = 1;
    private final Handler mHandler = new Handler();
    private final List<Integer> mDefaultItemList = new ArrayList();
    private final BaseDeviceParaCallBack mDeviceParaCallBack = new BaseDeviceParaCallBack() { // from class: com.ido.life.module.device.presenter.QuickAppPresenter.1
        @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
        public void onGetMenuList(MenuList.DeviceReturnInfo deviceReturnInfo) {
            super.onGetMenuList(deviceReturnInfo);
            QuickAppPresenter.this.mHandler.removeCallbacks(QuickAppPresenter.this.mQuickAppTimeoutRunnable);
            BLEManager.unregisterGetDeviceParaCallBack(QuickAppPresenter.this.mDeviceParaCallBack);
            QuickAppPresenter.this.formatV3MenuList(deviceReturnInfo);
        }
    };
    private final Runnable mQuickAppTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$QuickAppPresenter$IPYmjDbYxKhayXi22We7XB2BFLY
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$QuickAppPresenter();
        }
    };

    private int formatType2ResId(int i) {
        switch (i) {
            case 1:
            case 19:
            default:
                return R.string.home_steps_tittle;
            case 2:
                return R.string.home_card_heartbeat_title;
            case 3:
                return R.string.home_card_sleep_title;
            case 4:
                return R.string.device_user_take_photo;
            case 5:
                return R.string.device_alarm_clock;
            case 6:
                return R.string.device_music_control;
            case 7:
                return R.string.device_menu_second_chronograph_android;
            case 8:
                return R.string.device_menu_timer_android;
            case 9:
                return R.string.device_menu_sport_mode_android;
            case 10:
                return R.string.device_menu_weather_android;
            case 11:
                return R.string.home_breath_title;
            case 12:
                return R.string.device_find_phone;
            case 13:
                return R.string.home_pressure_title;
            case 14:
            case 17:
                return R.string.device_menu_health_data_android;
            case 15:
                return R.string.device_menu_time_interface_android;
            case 16:
                return R.string.device_last_movement;
            case 18:
                return R.string.home_card_blood_oxygen;
            case 20:
                return R.string.alexa_quick_name;
        }
    }

    public /* synthetic */ void lambda$new$0$QuickAppPresenter() {
        if (isAttachView()) {
            ((IQuickAppView) getView()).onGetQuickAppFailed();
        }
    }

    public void getQuickAppItemList() {
        if (isAttachView()) {
            ((IQuickAppView) getView()).onGetQuickAppStart();
            if (getSupportFunctionInfo().V3_get_menu_list) {
                this.mHandler.removeCallbacks(this.mQuickAppTimeoutRunnable);
                this.mHandler.postDelayed(this.mQuickAppTimeoutRunnable, 20000L);
                BLEManager.registerGetDeviceParaCallBack(this.mDeviceParaCallBack);
                BLEManager.getMenuList();
                return;
            }
            formatMenuList();
        }
    }

    private void formatMenuList() {
        MenuList menuList = LocalDataManager.getMenuList();
        List<Integer> allQuickAppList = FunctionHelper.getAllQuickAppList();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (menuList != null && menuList.items != null) {
            for (Integer num : menuList.items) {
                linkedHashMap.put(num, new SortBean(num.intValue(), formatType2ResId(num.intValue()), true));
            }
        }
        for (Integer num2 : allQuickAppList) {
            if (!linkedHashMap.containsKey(num2)) {
                linkedHashMap.put(num2, new SortBean(num2.intValue(), formatType2ResId(num2.intValue()), false));
            }
        }
        Collection collectionValues = linkedHashMap.values();
        if (isAttachView()) {
            ((IQuickAppView) getView()).onGetQuickAppSuccess(new ArrayList(collectionValues), Integer.MAX_VALUE, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void formatV3MenuList(MenuList.DeviceReturnInfo deviceReturnInfo) {
        this.mDefaultItemList.clear();
        if (isAttachView()) {
            if (deviceReturnInfo == null || deviceReturnInfo.items == null) {
                ((IQuickAppView) getView()).onGetQuickAppFailed();
                return;
            }
            List<MenuList.DeviceReturnInfo.Item> list = deviceReturnInfo.items;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (int i = 0; i < list.size(); i++) {
                MenuList.DeviceReturnInfo.Item item = list.get(i);
                if (item != null) {
                    int i2 = item.value;
                    if (i < deviceReturnInfo.currentShowNum) {
                        this.mDefaultItemList.add(Integer.valueOf(i2));
                        linkedHashMap.put(Integer.valueOf(i2), new SortBean(i2, formatType2ResId(i2), true));
                    } else {
                        linkedHashMap.put(Integer.valueOf(i2), new SortBean(i2, formatType2ResId(i2), false));
                    }
                }
            }
            Collection collectionValues = linkedHashMap.values();
            if (isAttachView()) {
                ((IQuickAppView) getView()).onGetQuickAppSuccess(new ArrayList(collectionValues), deviceReturnInfo.maxShowNum, deviceReturnInfo.minShowNum);
            }
        }
    }

    public boolean isDataChanged(ArrayList<SortBean> arrayList) {
        List<Integer> list;
        if (getSupportFunctionInfo().V3_get_menu_list) {
            list = this.mDefaultItemList;
        } else {
            MenuList menuList = LocalDataManager.getMenuList();
            if (menuList == null || menuList.items == null) {
                return !arrayList.isEmpty();
            }
            list = menuList.items;
        }
        if (list.size() != arrayList.size()) {
            return true;
        }
        for (int i = 0; i < list.size(); i++) {
            SortBean sortBean = arrayList.get(i);
            Integer num = list.get(i);
            if (sortBean == null || sortBean.getType() != num.intValue()) {
                return true;
            }
        }
        return false;
    }

    public void sendQuickApp2Device(List<SortBean> list) {
        if (list == null || list.isEmpty() || !isConnected()) {
            return;
        }
        MenuList menuList = new MenuList();
        menuList.items = new ArrayList();
        for (SortBean sortBean : list) {
            if (sortBean != null) {
                menuList.items.add(Integer.valueOf(sortBean.getType()));
            }
        }
        BLEManager.setMenuList(menuList);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (SettingCallBack.SettingType.MENU_LIST_SET == settingType) {
            CommonLogUtil.d("onSetCmdFailed " + SettingCallBack.SettingType.MENU_LIST_SET);
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        if (SettingCallBack.SettingType.MENU_LIST_SET == settingType) {
            CommonLogUtil.d("onSetCmdSuccess " + SettingCallBack.SettingType.MENU_LIST_SET);
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        this.mHandler.removeCallbacks(this.mQuickAppTimeoutRunnable);
        BLEManager.unregisterGetDeviceParaCallBack(this.mDeviceParaCallBack);
    }
}