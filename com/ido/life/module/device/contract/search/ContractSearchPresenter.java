package com.ido.life.module.device.contract.search;

import android.text.TextUtils;
import com.ido.ble.protocol.model.FrequentContactsV3;
import com.ido.life.base.BasePresenter;
import com.ido.life.module.device.contract.PhoneDto;
import com.ido.life.util.PhoneUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ContractSearchPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0014\u0010\f\u001a\u00020\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/ido/life/module/device/contract/search/ContractSearchPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/device/contract/search/IContractSearchView;", "()V", "mAllPhoneContracts", "", "Lcom/ido/life/module/device/contract/PhoneDto;", "mSearchPhoneContracts", "doSearch", "", "text", "", "getPhoneContracts", "mDeviceContracts", "Lcom/ido/ble/protocol/model/FrequentContactsV3;", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ContractSearchPresenter extends BasePresenter<IContractSearchView> {
    private static final String TAG = "ContractSearchPresenter";
    private List<PhoneDto> mAllPhoneContracts;
    private List<PhoneDto> mSearchPhoneContracts;

    public final void getPhoneContracts(List<FrequentContactsV3> mDeviceContracts) {
        Object next;
        Intrinsics.checkParameterIsNotNull(mDeviceContracts, "mDeviceContracts");
        List<PhoneDto> phone = PhoneUtil.getPhone();
        Intrinsics.checkExpressionValueIsNotNull(phone, "PhoneUtil.getPhone()");
        this.mAllPhoneContracts = phone;
        this.mSearchPhoneContracts = new ArrayList();
        List<PhoneDto> list = this.mSearchPhoneContracts;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSearchPhoneContracts");
        }
        List<PhoneDto> list2 = this.mAllPhoneContracts;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAllPhoneContracts");
        }
        list.addAll(list2);
        List<PhoneDto> list3 = this.mSearchPhoneContracts;
        if (list3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSearchPhoneContracts");
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list3) {
            PhoneDto phoneDto = (PhoneDto) obj;
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
        this.mSearchPhoneContracts = CollectionsKt.toMutableList((Collection) arrayList);
    }

    public final void doSearch(String text) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        String str = text;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        List<PhoneDto> list = this.mSearchPhoneContracts;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSearchPhoneContracts");
        }
        for (PhoneDto phoneDto : list) {
            if (StringsKt.contains((CharSequence) phoneDto.getName(), (CharSequence) str, true)) {
                arrayList.add(phoneDto);
            }
        }
        getView().setPhoneDtosList(arrayList);
    }
}