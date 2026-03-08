package com.ido.life.module.device.contract.add;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.FrequentContactsV3;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.CustomItemDecoration;
import com.ido.life.customview.NormalToast;
import com.ido.life.module.device.contract.PhoneDto;
import com.ido.life.module.device.contract.add.ContractAddActivity;
import com.ido.life.module.device.contract.search.ContractSearchActivity;
import com.ido.life.util.ListUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: ContractAddActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 12\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u000212B\u0005¢\u0006\u0002\u0010\u0005J\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0010H\u0014J\b\u0010\u0014\u001a\u00020\u0010H\u0014J\b\u0010\u0015\u001a\u00020\u0010H\u0014J\b\u0010\u0016\u001a\u00020\u0010H\u0016J\"\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00122\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u0010H\u0016J\b\u0010\u001d\u001a\u00020\u0010H\u0016J\u0012\u0010\u001e\u001a\u00020\u00102\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010!\u001a\u00020\u0010H\u0016J-\u0010\"\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00122\u000e\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020%0$2\u0006\u0010&\u001a\u00020'H\u0016¢\u0006\u0002\u0010(J\u0010\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\u0012H\u0016J\u0010\u0010+\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\u0012H\u0016J\u0016\u0010,\u001a\u00020\u00102\f\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\rH\u0016J\u0010\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u00020%H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/ido/life/module/device/contract/add/ContractAddActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/contract/add/ContractAddPresenter;", "Landroid/view/View$OnClickListener;", "Lcom/ido/life/module/device/contract/add/IContractAddView;", "()V", "contractAdapter", "Lcom/ido/life/module/device/contract/add/ContractAddActivity$ContractAdapter;", "getContractAdapter", "()Lcom/ido/life/module/device/contract/add/ContractAddActivity$ContractAdapter;", "setContractAdapter", "(Lcom/ido/life/module/device/contract/add/ContractAddActivity$ContractAdapter;)V", "mFrequentContactsV3List", "", "Lcom/ido/ble/protocol/model/FrequentContactsV3;", "check", "", "getLayoutResId", "", "initData", "initEvent", "initLabelLanguage", "initViews", "onActivityResult", "requestCode", "resultCode", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Landroid/content/Intent;", "onAddFailed", "onAddSuccess", "onClick", "v", "Landroid/view/View;", "onFinish", "onRequestPermissionsResult", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "setAllContract", "count", "setChoiceContract", "setPhoneContracts", "phoneDtos", "Lcom/ido/life/module/device/contract/PhoneDto;", "showMessage", "message", "Companion", "ContractAdapter", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ContractAddActivity extends BaseActivity<ContractAddPresenter> implements View.OnClickListener, IContractAddView {
    public static final String EXTRA_FREQUENT_CONTRACT_LIST = "frequent_contract_list";
    public static final int MAX_CONTACT = 10;
    private static final int REQUEST_CODE = 201;
    private static final String TAG = "ContractAddActivity";
    private HashMap _$_findViewCache;
    private ContractAdapter contractAdapter;
    private List<FrequentContactsV3> mFrequentContactsV3List;

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_contract_add;
    }

    public static final /* synthetic */ ContractAddPresenter access$getMPresenter$p(ContractAddActivity contractAddActivity) {
        return (ContractAddPresenter) contractAddActivity.mPresenter;
    }

    public final ContractAdapter getContractAdapter() {
        return this.contractAdapter;
    }

    public final void setContractAdapter(ContractAdapter contractAdapter) {
        this.contractAdapter = contractAdapter;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        Bundle extras = intent.getExtras();
        if (extras != null && extras.getSerializable(EXTRA_FREQUENT_CONTRACT_LIST) != null) {
            Serializable serializable = extras.getSerializable(EXTRA_FREQUENT_CONTRACT_LIST);
            if (serializable == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableList<com.ido.ble.protocol.model.FrequentContactsV3>");
            }
            this.mFrequentContactsV3List = TypeIntrinsics.asMutableList(serializable);
        }
        check();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mTitleBar.initLayout(3).setRightTextColor(getColor(R.color.color_4F6EB9)).setLeftText(getLanguageText(R.string.public_cancel)).setLeftTextColor(getColor(R.color.color_4F6EB9)).setLeftTextOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.contract.add.ContractAddActivity.initViews.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ContractAddActivity.this.onBackPressed();
            }
        });
        this.mTitleBar.setTitle(LanguageUtil.getLanguageText(R.string.device_contract));
        this.mTitleBar.setRightText(LanguageUtil.getLanguageText(R.string.mine_complete));
    }

    public final void check() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_CONTACTS"}, 201);
        } else {
            ((ContractAddPresenter) this.mPresenter).getPhoneContracts(this.mFrequentContactsV3List);
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkParameterIsNotNull(permissions, "permissions");
        Intrinsics.checkParameterIsNotNull(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 201) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS") != 0) {
                showToast(LanguageUtil.getLanguageText(R.string.device_contract_permission));
            } else {
                ((ContractAddPresenter) this.mPresenter).getPhoneContracts(this.mFrequentContactsV3List);
            }
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.contract.add.ContractAddActivity.initEvent.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (ContractAddActivity.this.getContractAdapter() != null) {
                    ContractAdapter contractAdapter = ContractAddActivity.this.getContractAdapter();
                    if (contractAdapter == null) {
                        Intrinsics.throwNpe();
                    }
                    List<PhoneDto> selectPhones = contractAdapter.getSelectPhones();
                    if (ListUtils.INSTANCE.isNotEmpty(selectPhones)) {
                        ContractAddActivity.this.showLoadingDialog();
                        if (ListUtils.INSTANCE.isNotEmpty(ContractAddActivity.this.mFrequentContactsV3List)) {
                            List list = ContractAddActivity.this.mFrequentContactsV3List;
                            if (list == null) {
                                Intrinsics.throwNpe();
                            }
                            List<FrequentContactsV3> list2 = list;
                            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                            for (FrequentContactsV3 frequentContactsV3 : list2) {
                                String str = frequentContactsV3.name;
                                Intrinsics.checkExpressionValueIsNotNull(str, "it.name");
                                String str2 = frequentContactsV3.phone;
                                Intrinsics.checkExpressionValueIsNotNull(str2, "it.phone");
                                arrayList.add(new PhoneDto(str, str2, true, true));
                            }
                            selectPhones.addAll(arrayList);
                        }
                        ContractAddActivity.access$getMPresenter$p(ContractAddActivity.this).addFrequentContractsV3(selectPhones);
                        return;
                    }
                    ContractAddActivity.this.finish();
                    return;
                }
                ContractAddActivity.this.finish();
            }
        });
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_search_key)).setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_search_key) {
            Intent intent = new Intent(this, (Class<?>) ContractSearchActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(ContractSearchActivity.EXTRA_CONTRA_DEVICE, (Serializable) this.mFrequentContactsV3List);
            intent.putExtras(bundle);
            startActivityForResult(intent, 201);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ContractAdapter contractAdapter;
        Bundle extras;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 201) {
            String string = (data == null || (extras = data.getExtras()) == null) ? null : extras.getString(ContractSearchActivity.RESULT_PHONE);
            if (string == null || (contractAdapter = this.contractAdapter) == null) {
                return;
            }
            contractAdapter.setPhone(string);
        }
    }

    @Override // com.ido.life.module.device.contract.add.IContractAddView
    public void setAllContract(int count) {
        TextView tv_search_key = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_search_key);
        Intrinsics.checkExpressionValueIsNotNull(tv_search_key, "tv_search_key");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String languageText = LanguageUtil.getLanguageText(R.string.device_all_contract);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…ring.device_all_contract)");
        Object[] objArr = {Integer.valueOf(count)};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        tv_search_key.setText(str);
    }

    @Override // com.ido.life.module.device.contract.add.IContractAddView
    public void setChoiceContract(int count) {
        RegularTextView tv_contract = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_contract);
        Intrinsics.checkExpressionValueIsNotNull(tv_contract, "tv_contract");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String languageText = LanguageUtil.getLanguageText(R.string.device_contact_limit);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…ing.device_contact_limit)");
        Object[] objArr = {Integer.valueOf(count), 10};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        tv_contract.setText(str);
    }

    @Override // com.ido.life.module.device.contract.add.IContractAddView
    public void setPhoneContracts(List<PhoneDto> phoneDtos) {
        Intrinsics.checkParameterIsNotNull(phoneDtos, "phoneDtos");
        List<FrequentContactsV3> list = this.mFrequentContactsV3List;
        this.contractAdapter = list != null ? new ContractAdapter(phoneDtos, list.size()) : null;
        ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_contract)).addItemDecoration(new CustomItemDecoration().marginLeft(DipPixelUtil.dip2pxF(16.0f)).color(ContextCompat.getColor(this, R.color.color_6F6F6F)).height(DipPixelUtil.dip2pxF(0.5f)));
        RecyclerView recycler_contract = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_contract);
        Intrinsics.checkExpressionValueIsNotNull(recycler_contract, "recycler_contract");
        recycler_contract.setAdapter(this.contractAdapter);
        ContractAdapter contractAdapter = this.contractAdapter;
        if (contractAdapter != null) {
            contractAdapter.setCheckListener(new ContractAdapter.OnCheckListener() { // from class: com.ido.life.module.device.contract.add.ContractAddActivity.setPhoneContracts.2
                @Override // com.ido.life.module.device.contract.add.ContractAddActivity.ContractAdapter.OnCheckListener
                public void check(boolean z) {
                    List<PhoneDto> selectPhones;
                    ContractAddActivity contractAddActivity = ContractAddActivity.this;
                    ContractAdapter contractAdapter2 = contractAddActivity.getContractAdapter();
                    int size = (contractAdapter2 == null || (selectPhones = contractAdapter2.getSelectPhones()) == null) ? 0 : selectPhones.size();
                    List list2 = ContractAddActivity.this.mFrequentContactsV3List;
                    contractAddActivity.setChoiceContract(size + (list2 != null ? list2.size() : 0));
                }
            });
        }
    }

    @Override // com.ido.life.module.device.contract.add.IContractAddView
    public void showMessage(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        showToast(message);
    }

    @Override // com.ido.life.module.device.contract.add.IContractAddView
    public void onAddSuccess() {
        dismissLoadingDialog();
        ArrayList arrayList = new ArrayList();
        if (ListUtils.INSTANCE.isNotEmpty(this.mFrequentContactsV3List)) {
            List<FrequentContactsV3> list = this.mFrequentContactsV3List;
            if (list == null) {
                Intrinsics.throwNpe();
            }
            arrayList.addAll(list);
        }
        ContractAdapter contractAdapter = this.contractAdapter;
        if (contractAdapter == null) {
            Intrinsics.throwNpe();
        }
        List<PhoneDto> selectPhones = contractAdapter.getSelectPhones();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(selectPhones, 10));
        for (PhoneDto phoneDto : selectPhones) {
            FrequentContactsV3 frequentContactsV3 = new FrequentContactsV3();
            frequentContactsV3.name = phoneDto.getName();
            frequentContactsV3.phone = phoneDto.getTelePhone();
            arrayList2.add(frequentContactsV3);
        }
        arrayList.addAll(arrayList2);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_FREQUENT_CONTRACT_LIST, arrayList);
        intent.putExtras(bundle);
        setResult(-1, intent);
        finish();
    }

    @Override // com.ido.life.module.device.contract.add.IContractAddView
    public void onAddFailed() {
        dismissLoadingDialog();
        showToast(getLanguageText(R.string.public_set_failed));
        finish();
    }

    @Override // com.ido.life.module.device.contract.add.IContractAddView
    public void onFinish() {
        dismissLoadingDialog();
        finish();
    }

    /* JADX INFO: compiled from: ContractAddActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002'(B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\u0014\u001a\u00020\u00152\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\b\u0010\u0016\u001a\u00020\u0007H\u0016J\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u0019J\u0018\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J\u0018\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0007H\u0016J\u000e\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\fJ\u000e\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020%J\u0010\u0010&\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006)"}, d2 = {"Lcom/ido/life/module/device/contract/add/ContractAddActivity$ContractAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/module/device/contract/add/ContractAddActivity$ContractAdapter$ViewHolder;", "phonesList", "", "Lcom/ido/life/module/device/contract/PhoneDto;", "int", "", "(Ljava/util/List;I)V", "isOutOfMax", "", "mOnCheckListener", "Lcom/ido/life/module/device/contract/add/ContractAddActivity$ContractAdapter$OnCheckListener;", "mSelectContract", "getMSelectContract", "()I", "setMSelectContract", "(I)V", "getPhonesList", "()Ljava/util/List;", "checkContractList", "", "getItemCount", "getPhones", "getSelectPhones", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setCheckListener", "onCheckListener", "setPhone", "phone", "", "updateSelectStatus", "OnCheckListener", "ViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class ContractAdapter extends RecyclerView.Adapter<ViewHolder> {
        private boolean isOutOfMax;
        private OnCheckListener mOnCheckListener;
        private int mSelectContract;
        private final List<PhoneDto> phonesList;

        /* JADX INFO: compiled from: ContractAddActivity.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/ido/life/module/device/contract/add/ContractAddActivity$ContractAdapter$OnCheckListener;", "", "check", "", "boolean", "", "app_release"}, k = 1, mv = {1, 1, 16})
        public interface OnCheckListener {
            void check(boolean z);
        }

        public final List<PhoneDto> getPhonesList() {
            return this.phonesList;
        }

        public ContractAdapter(List<PhoneDto> phonesList, int i) {
            Intrinsics.checkParameterIsNotNull(phonesList, "phonesList");
            this.phonesList = phonesList;
            this.mSelectContract = i;
            updateSelectStatus(i);
        }

        public final int getMSelectContract() {
            return this.mSelectContract;
        }

        public final void setMSelectContract(int i) {
            this.mSelectContract = i;
        }

        public final void setCheckListener(OnCheckListener onCheckListener) {
            Intrinsics.checkParameterIsNotNull(onCheckListener, "onCheckListener");
            this.mOnCheckListener = onCheckListener;
        }

        /* JADX INFO: compiled from: ContractAddActivity.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/ido/life/module/device/contract/add/ContractAddActivity$ContractAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "contractCheck", "Landroid/widget/CheckBox;", "getContractCheck", "()Landroid/widget/CheckBox;", "tvName", "Landroid/widget/TextView;", "getTvName", "()Landroid/widget/TextView;", "tvPhone", "getTvPhone", "app_release"}, k = 1, mv = {1, 1, 16})
        public static final class ViewHolder extends RecyclerView.ViewHolder {
            private final CheckBox contractCheck;
            private final TextView tvName;
            private final TextView tvPhone;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ViewHolder(View view) {
                super(view);
                Intrinsics.checkParameterIsNotNull(view, "view");
                View viewFindViewById = view.findViewById(R.id.tv_contract_name);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "view.findViewById(R.id.tv_contract_name)");
                this.tvName = (TextView) viewFindViewById;
                View viewFindViewById2 = view.findViewById(R.id.check_contract);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "view.findViewById(R.id.check_contract)");
                this.contractCheck = (CheckBox) viewFindViewById2;
                View viewFindViewById3 = view.findViewById(R.id.tv_contract_phone);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "view.findViewById(R.id.tv_contract_phone)");
                this.tvPhone = (TextView) viewFindViewById3;
            }

            public final TextView getTvName() {
                return this.tvName;
            }

            public final CheckBox getContractCheck() {
                return this.contractCheck;
            }

            public final TextView getTvPhone() {
                return this.tvPhone;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Intrinsics.checkParameterIsNotNull(parent, "parent");
            View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_contract, parent, false);
            Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(pare…_contract, parent, false)");
            return new ViewHolder(viewInflate);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v2, types: [T, com.ido.life.module.device.contract.PhoneDto] */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            Intrinsics.checkParameterIsNotNull(holder, "holder");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = this.phonesList.get(position);
            holder.getTvName().setText(((PhoneDto) objectRef.element).getName());
            holder.getTvPhone().setText(((PhoneDto) objectRef.element).getTelePhone());
            if (((PhoneDto) objectRef.element).isSelected()) {
                holder.getTvName().setTextColor(ResourceUtil.getColor(R.color.com_color_white_54));
                holder.getContractCheck().setBackground(ResourceUtil.getDrawable(R.mipmap.ic_radio_contract_seleted));
            } else {
                holder.getTvName().setTextColor(ResourceUtil.getColor(R.color.white));
                holder.getContractCheck().setBackground(ResourceUtil.getDrawable((!this.isOutOfMax || ((PhoneDto) objectRef.element).isCheck()) ? R.drawable.selector_check_box_normal : R.mipmap.control_multi_choose_empty));
            }
            holder.getContractCheck().setVisibility(0);
            holder.getContractCheck().setChecked(((PhoneDto) objectRef.element).isCheck());
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.contract.add.ContractAddActivity$ContractAdapter$onBindViewHolder$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    if (((PhoneDto) objectRef.element).isSelected()) {
                        return;
                    }
                    Log.d("ContractAddActivity", "onBindViewHolder: " + position);
                    if (!((PhoneDto) objectRef.element).isCheck() && this.this$0.isOutOfMax) {
                        NormalToast.showToast(LanguageUtil.getLanguageText(R.string.device_can_add_contract));
                        return;
                    }
                    holder.getContractCheck().setChecked(!((PhoneDto) objectRef.element).isCheck());
                    ((PhoneDto) objectRef.element).setCheck(!((PhoneDto) objectRef.element).isCheck());
                    this.this$0.notifyDataSetChanged();
                    ContractAddActivity.ContractAdapter contractAdapter = this.this$0;
                    contractAdapter.updateSelectStatus(contractAdapter.getMSelectContract());
                    ContractAddActivity.ContractAdapter.OnCheckListener onCheckListener = this.this$0.mOnCheckListener;
                    if (onCheckListener != null) {
                        onCheckListener.check(((PhoneDto) objectRef.element).isCheck());
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void updateSelectStatus(int i) {
            this.isOutOfMax = getSelectPhones().size() + i >= 10;
        }

        private final void checkContractList(List<PhoneDto> phonesList) {
            int i = 0;
            for (PhoneDto phoneDto : phonesList) {
                if (phoneDto.isSelected() || phoneDto.isCheck()) {
                    i++;
                    if (i > 10) {
                        OnCheckListener onCheckListener = this.mOnCheckListener;
                        if (onCheckListener != null && onCheckListener != null) {
                            onCheckListener.check(true);
                        }
                    } else {
                        OnCheckListener onCheckListener2 = this.mOnCheckListener;
                        if (onCheckListener2 != null && onCheckListener2 != null) {
                            onCheckListener2.check(true);
                        }
                    }
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.phonesList.size();
        }

        public final List<PhoneDto> getSelectPhones() {
            List<PhoneDto> list = this.phonesList;
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                PhoneDto phoneDto = (PhoneDto) obj;
                if (phoneDto.isCheck() || phoneDto.isSelected()) {
                    arrayList.add(obj);
                }
            }
            return CollectionsKt.toMutableList((Collection) arrayList);
        }

        public final List<PhoneDto> getPhones() {
            return this.phonesList;
        }

        public final void setPhone(String phone) {
            Intrinsics.checkParameterIsNotNull(phone, "phone");
            for (PhoneDto phoneDto : this.phonesList) {
                if (phoneDto.getTelePhone().equals(phone)) {
                    phoneDto.setCheck(true);
                    notifyDataSetChanged();
                }
            }
        }
    }
}