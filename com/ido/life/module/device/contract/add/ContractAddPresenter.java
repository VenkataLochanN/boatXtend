package com.ido.life.module.device.contract.add;

import android.util.Log;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.OperateCallBack;
import com.ido.ble.protocol.model.FrequentContactsV3;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.module.device.base.BaseOperatePresenter;
import com.ido.life.module.device.contract.PhoneDto;
import com.ido.life.util.ListUtils;
import com.ido.life.util.PhoneUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ContractAddPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nJ\u0016\u0010\u000b\u001a\u00020\b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0005J\u001a\u0010\u000e\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0014R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/ido/life/module/device/contract/add/ContractAddPresenter;", "Lcom/ido/life/module/device/base/BaseOperatePresenter;", "Lcom/ido/life/module/device/contract/add/IContractAddView;", "()V", "mPhoneContracts", "", "Lcom/ido/life/module/device/contract/PhoneDto;", "addFrequentContractsV3", "", "phonesList", "", "getPhoneContracts", "mDeviceContracts", "Lcom/ido/ble/protocol/model/FrequentContactsV3;", "onOperateSetResult", "operateType", "Lcom/ido/ble/callback/OperateCallBack$OperateType;", "b", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ContractAddPresenter extends BaseOperatePresenter<IContractAddView> {
    private static final String TAG = "ContractAddPresenter";
    private List<PhoneDto> mPhoneContracts;

    public final void getPhoneContracts(List<FrequentContactsV3> mDeviceContracts) {
        IContractAddView iContractAddView;
        Object next;
        this.mPhoneContracts = PhoneUtil.getPhone();
        logP("getPhoneContracts: " + this.mPhoneContracts);
        if (ListUtils.INSTANCE.isNotEmpty(this.mPhoneContracts)) {
            if (ListUtils.INSTANCE.isNotEmpty(mDeviceContracts)) {
                List<PhoneDto> list = this.mPhoneContracts;
                if (list == null) {
                    Intrinsics.throwNpe();
                }
                ArrayList arrayList = new ArrayList();
                for (Object obj : list) {
                    PhoneDto phoneDto = (PhoneDto) obj;
                    if (mDeviceContracts == null) {
                        Intrinsics.throwNpe();
                    }
                    Iterator<T> it = mDeviceContracts.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                        String str = ((FrequentContactsV3) next).phone;
                        Intrinsics.checkExpressionValueIsNotNull(str, "it.phone");
                        if (Intrinsics.areEqual(StringsKt.replace$default(str, " ", "", false, 4, (Object) null), StringsKt.replace$default(phoneDto.getTelePhone(), " ", "", false, 4, (Object) null))) {
                            break;
                        }
                    }
                    if (next == null) {
                        arrayList.add(obj);
                    }
                }
                this.mPhoneContracts = CollectionsKt.toMutableList((Collection) arrayList);
            }
            if (ListUtils.INSTANCE.isNotEmpty(this.mPhoneContracts) && (iContractAddView = (IContractAddView) getView()) != null) {
                List<PhoneDto> list2 = this.mPhoneContracts;
                iContractAddView.setAllContract(list2 != null ? list2.size() : 0);
            }
        }
        if (ListUtils.INSTANCE.isNotEmpty(this.mPhoneContracts)) {
            IContractAddView iContractAddView2 = (IContractAddView) getView();
            if (iContractAddView2 != null) {
                List<PhoneDto> list3 = this.mPhoneContracts;
                if (list3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableList<com.ido.life.module.device.contract.PhoneDto>");
                }
                iContractAddView2.setPhoneContracts(TypeIntrinsics.asMutableList(list3));
            }
        } else {
            IContractAddView iContractAddView3 = (IContractAddView) getView();
            if (iContractAddView3 != null) {
                List<PhoneDto> list4 = this.mPhoneContracts;
                if (list4 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableList<com.ido.life.module.device.contract.PhoneDto>");
                }
                iContractAddView3.setPhoneContracts(TypeIntrinsics.asMutableList(list4));
            }
            IContractAddView iContractAddView4 = (IContractAddView) getView();
            if (iContractAddView4 != null) {
                String languageText = LanguageUtil.getLanguageText(R.string.device_no_contract);
                Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…tring.device_no_contract)");
                iContractAddView4.showMessage(languageText);
            }
        }
        IContractAddView iContractAddView5 = (IContractAddView) getView();
        if (iContractAddView5 != null) {
            iContractAddView5.setChoiceContract(mDeviceContracts != null ? mDeviceContracts.size() : 0);
        }
    }

    public final void addFrequentContractsV3(List<PhoneDto> phonesList) {
        Intrinsics.checkParameterIsNotNull(phonesList, "phonesList");
        List<PhoneDto> list = phonesList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (PhoneDto phoneDto : list) {
            FrequentContactsV3 frequentContactsV3 = new FrequentContactsV3();
            frequentContactsV3.name = phoneDto.getName();
            frequentContactsV3.phone = StringsKt.replace$default(phoneDto.getTelePhone(), " ", "", false, 4, (Object) null);
            arrayList.add(frequentContactsV3);
        }
        ArrayList arrayList2 = arrayList;
        Log.d(TAG, "addFrequentContractsV3: " + arrayList2);
        if (ListUtils.INSTANCE.isNotEmpty(arrayList2)) {
            BLEManager.setFrequentContactsV3(arrayList2);
            return;
        }
        IContractAddView iContractAddView = (IContractAddView) getView();
        if (iContractAddView != null) {
            iContractAddView.onFinish();
        }
    }

    @Override // com.ido.life.module.device.base.BaseOperatePresenter
    protected void onOperateSetResult(OperateCallBack.OperateType operateType, boolean b2) {
        super.onOperateSetResult(operateType, b2);
        Log.d(TAG, "onOperateSetResult: " + operateType + ',' + b2);
        if (operateType == OperateCallBack.OperateType.FREQUENT_CONTACTS) {
            if (b2) {
                IContractAddView iContractAddView = (IContractAddView) getView();
                if (iContractAddView != null) {
                    iContractAddView.onAddSuccess();
                    return;
                }
                return;
            }
            IContractAddView iContractAddView2 = (IContractAddView) getView();
            if (iContractAddView2 != null) {
                iContractAddView2.onAddFailed();
            }
        }
    }
}