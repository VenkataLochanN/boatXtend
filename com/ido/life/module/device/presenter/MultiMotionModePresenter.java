package com.ido.life.module.device.presenter;

import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.OtherProtocolCallBack;
import com.ido.ble.protocol.model.SportModeSort;
import com.ido.ble.protocol.model.SportModeSortV3;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.bean.SortBean;
import com.ido.life.module.device.view.IMultiMotionModeView;
import com.ido.life.util.FunctionHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MultiMotionModePresenter extends BaseCmdPresenter<IMultiMotionModeView> {
    public int[] getShowCount() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo == null) {
            return new int[]{8, 4};
        }
        if (supportFunctionInfo.ex_table_main7_v3_sports_type) {
            return new int[]{Integer.MAX_VALUE, 4};
        }
        if (!supportFunctionInfo.sport_mode_sort) {
            return new int[]{8, 4};
        }
        int[] iArr = new int[2];
        iArr[0] = supportFunctionInfo.sport_show_num;
        iArr[1] = supportFunctionInfo.sport_show_num <= 4 ? 1 : 4;
        return iArr;
    }

    public List<SortBean> getMotionTypeStateList() {
        List<SortBean> supportMotionTypeList = FunctionHelper.getSupportMotionTypeList();
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo == null) {
            return supportMotionTypeList;
        }
        ArrayList arrayList = new ArrayList();
        if (supportFunctionInfo.ex_table_main7_v3_sports_type) {
            SportModeSortV3 sportModeSortV3 = LocalDataManager.getSportModeSortV3();
            if (sportModeSortV3 == null || sportModeSortV3.item == null || sportModeSortV3.item.size() == 0) {
                return supportMotionTypeList;
            }
            for (SportModeSortV3.SportModeSortItemV3 sportModeSortItemV3 : sportModeSortV3.item) {
                Iterator<SortBean> it = supportMotionTypeList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        SortBean next = it.next();
                        if (sportModeSortItemV3.type == next.getType()) {
                            supportMotionTypeList.remove(next);
                            next.setSelected(true);
                            arrayList.add(next);
                            break;
                        }
                    }
                }
            }
        } else if (supportFunctionInfo.sport_mode_sort) {
            SportModeSort sportModeSort = LocalDataManager.getSportModeSort();
            if (sportModeSort == null || sportModeSort.items == null || sportModeSort.items.length == 0) {
                return supportMotionTypeList;
            }
            SportModeSort.SportModeSortItem[] sportModeSortItemArr = sportModeSort.items;
            Arrays.sort(sportModeSortItemArr, new Comparator() { // from class: com.ido.life.module.device.presenter.-$$Lambda$MultiMotionModePresenter$lOaILpshQ0eUOdoUlavH2S9I3gA
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return MultiMotionModePresenter.lambda$getMotionTypeStateList$0((SportModeSort.SportModeSortItem) obj, (SportModeSort.SportModeSortItem) obj2);
                }
            });
            for (SportModeSort.SportModeSortItem sportModeSortItem : sportModeSortItemArr) {
                Iterator<SortBean> it2 = supportMotionTypeList.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        SortBean next2 = it2.next();
                        if (sportModeSortItem.type == next2.getType()) {
                            supportMotionTypeList.remove(next2);
                            next2.setSelected(true);
                            arrayList.add(next2);
                            break;
                        }
                    }
                }
            }
        }
        arrayList.addAll(supportMotionTypeList);
        return arrayList;
    }

    static /* synthetic */ int lambda$getMotionTypeStateList$0(SportModeSort.SportModeSortItem sportModeSortItem, SportModeSort.SportModeSortItem sportModeSortItem2) {
        if (sportModeSortItem == null || sportModeSortItem2 == null) {
            return 0;
        }
        return sportModeSortItem.index - sportModeSortItem2.index;
    }

    public void sendMotionType2Device(ArrayList<SortBean> arrayList) {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo == null) {
            return;
        }
        int i = 0;
        if (supportFunctionInfo.ex_table_main7_v3_sports_type) {
            SportModeSortV3 sportModeSortV3 = new SportModeSortV3();
            sportModeSortV3.num = arrayList.size();
            sportModeSortV3.item = new ArrayList();
            while (i < arrayList.size()) {
                SportModeSortV3.SportModeSortItemV3 sportModeSortItemV3 = new SportModeSortV3.SportModeSortItemV3();
                int i2 = i + 1;
                sportModeSortItemV3.index = i2;
                sportModeSortItemV3.type = arrayList.get(i).getType();
                sportModeSortV3.item.add(sportModeSortItemV3);
                i = i2;
            }
            BLEManager.setSportModeSortInfoV3(sportModeSortV3);
            return;
        }
        if (supportFunctionInfo.sport_mode_sort) {
            SportModeSort sportModeSort = new SportModeSort();
            SportModeSort.SportModeSortItem[] sportModeSortItemArr = new SportModeSort.SportModeSortItem[arrayList.size()];
            while (i < arrayList.size()) {
                sportModeSortItemArr[i] = new SportModeSort.SportModeSortItem();
                int i3 = i + 1;
                sportModeSortItemArr[i].index = i3;
                sportModeSortItemArr[i].type = arrayList.get(i).getType();
                i = i3;
            }
            sportModeSort.items = sportModeSortItemArr;
            BLEManager.setSportModeSortInfo(sportModeSort);
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetOtherCmdSuccess(OtherProtocolCallBack.SettingType settingType) {
        super.onSetOtherCmdSuccess(settingType);
        if (isAttachView() && settingType == OtherProtocolCallBack.SettingType.SPORT_MODE_SORT) {
            ((IMultiMotionModeView) getView()).onSetMotionTypeSuccess();
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetOtherCmdFailed(OtherProtocolCallBack.SettingType settingType) {
        super.onSetOtherCmdFailed(settingType);
        if (isAttachView() && settingType == OtherProtocolCallBack.SettingType.SPORT_MODE_SORT) {
            ((IMultiMotionModeView) getView()).onSetMotionTypeFailed();
        }
    }
}