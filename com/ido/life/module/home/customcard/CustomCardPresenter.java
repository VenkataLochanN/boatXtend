package com.ido.life.module.home.customcard;

import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.life.base.BasePresenter;
import com.ido.life.bean.SortBean;
import com.ido.life.database.model.HomeCard;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CustomCardPresenter extends BasePresenter {
    public List<SortBean> getItemList() {
        List<SortBean> cardTypeShowList = getCardTypeShowList();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (SortBean sortBean : cardTypeShowList) {
            sortBean.setNameId(HomeCard.getTypeName(sortBean.getType()));
            if (sortBean.isSelected()) {
                arrayList2.add(sortBean);
            } else {
                arrayList3.add(sortBean);
            }
        }
        arrayList.addAll(0, arrayList2);
        arrayList.addAll(arrayList3);
        return arrayList;
    }

    public List<SortBean> getCardTypeShowList() {
        List<Integer> hideValueList;
        List<Integer> arrayList;
        BLEDevice lastConnectedDeviceInfo;
        ArrayList arrayList2 = new ArrayList();
        HomeCard homeCardQueryHomeCardInfo = GreenDaoUtil.queryHomeCardInfo(RunTimeUtil.getInstance().getUserId());
        if (homeCardQueryHomeCardInfo == null) {
            arrayList = HomeCard.getDefaultHomeShowCardValueList();
            hideValueList = HomeCard.getDefaultHomeHideCardValueList();
        } else {
            List<Integer> valueList = homeCardQueryHomeCardInfo.getValueList();
            hideValueList = homeCardQueryHomeCardInfo.getHideValueList();
            arrayList = valueList;
        }
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        if (hideValueList == null) {
            hideValueList = new ArrayList<>();
        }
        boolean z = false;
        for (Integer num : arrayList) {
            if (num.intValue() == 9) {
                z = true;
            }
            arrayList2.add(new SortBean(num.intValue(), true));
        }
        for (Integer num2 : hideValueList) {
            if (num2.intValue() == 9) {
                z = true;
            }
            arrayList2.add(new SortBean(num2.intValue(), false));
        }
        if (!z && BLEManager.isBind() && (lastConnectedDeviceInfo = LocalDataManager.getLastConnectedDeviceInfo()) != null && HomeHelperKt.deviceSupportMenstrual(lastConnectedDeviceInfo.mDeviceAddress)) {
            arrayList2.add(new SortBean(9, false));
        }
        return arrayList2;
    }

    public void saveCardStatus(List<SortBean> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (list != null && list.size() > 0) {
            for (SortBean sortBean : list) {
                if (sortBean != null) {
                    if (sortBean.isSelected()) {
                        arrayList.add(Integer.valueOf(sortBean.getType()));
                    } else {
                        arrayList2.add(Integer.valueOf(sortBean.getType()));
                    }
                }
            }
        }
        HomeCard homeCardQueryHomeCardInfo = GreenDaoUtil.queryHomeCardInfo(RunTimeUtil.getInstance().getUserId());
        if (homeCardQueryHomeCardInfo != null) {
            homeCardQueryHomeCardInfo.setValueList(arrayList);
            homeCardQueryHomeCardInfo.setHideValueList(arrayList2);
            homeCardQueryHomeCardInfo.setUploadSuccess(false);
            homeCardQueryHomeCardInfo.update();
            return;
        }
        HomeCard homeCard = new HomeCard();
        homeCard.setValueList(arrayList);
        homeCard.setHideValueList(arrayList2);
        homeCard.setUploadSuccess(false);
        homeCard.setUserId(Long.valueOf(RunTimeUtil.getInstance().getUserId()));
        long jCurrentTimeMillis = System.currentTimeMillis();
        homeCard.setCreateTime(jCurrentTimeMillis);
        homeCard.setUpdateTime(jCurrentTimeMillis);
        GreenDaoUtil.addHomeCardInfo(homeCard);
    }
}