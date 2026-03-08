package com.ido.life.module.device.contract;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
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
import com.ido.life.customview.LoadingLayout;
import com.ido.life.customview.NormalToast;
import com.ido.life.data.Constant;
import com.ido.life.enums.ContractEnum;
import com.ido.life.module.device.contract.ContractActivity;
import com.ido.life.module.device.contract.add.ContractAddActivity;
import com.ido.life.module.device.contract.search.ContractSearchActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: ContractActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\b\u0018\u0000 12\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u000212B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0018H\u0014J\b\u0010\u001a\u001a\u00020\u0018H\u0014J\b\u0010\u001b\u001a\u00020\u0018H\u0016J\"\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u00162\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\u0012\u0010!\u001a\u00020\u00182\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u0018H\u0016J\b\u0010%\u001a\u00020\u0018H\u0002J\b\u0010&\u001a\u00020\u0018H\u0014J\u0010\u0010'\u001a\u00020\u00182\u0006\u0010(\u001a\u00020\u0016H\u0016J\u0018\u0010)\u001a\u00020\u00182\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010+H\u0016J\u0010\u0010,\u001a\u00020\u00182\u0006\u0010-\u001a\u00020\u0014H\u0016J\u0010\u0010.\u001a\u00020\u00182\u0006\u0010*\u001a\u00020\u000eH\u0002J\b\u0010/\u001a\u00020\u0018H\u0016J\b\u00100\u001a\u00020\u0018H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/ido/life/module/device/contract/ContractActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/contract/ContractPresenter;", "Landroid/view/View$OnClickListener;", "Lcom/ido/life/module/device/contract/IContractView;", "()V", "mContractDeviceAdapter", "Lcom/ido/life/module/device/contract/ContractActivity$ContractDeviceAdapter;", "getMContractDeviceAdapter", "()Lcom/ido/life/module/device/contract/ContractActivity$ContractDeviceAdapter;", "setMContractDeviceAdapter", "(Lcom/ido/life/module/device/contract/ContractActivity$ContractDeviceAdapter;)V", "mFrequentContactsV3List", "", "Lcom/ido/ble/protocol/model/FrequentContactsV3;", "mOriginalFrequentContactsV3List", "mState", "Lcom/ido/life/enums/ContractEnum;", "mTmpFrequentContactsV3List", "checkDataChanged", "", "getLayoutResId", "", "initData", "", "initEvent", "initLabelLanguage", "initViews", "onActivityResult", "requestCode", "resultCode", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "onSetFrequentFail", "restoreData", "sendCmd", "setChoiceContract", "count", "setDeviceContracts", "frequentContactsV3", "", "setNoContractLayout", "visible", "showDelete", "showDeleteLayout", "showEditLayout", "Companion", "ContractDeviceAdapter", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ContractActivity extends BaseActivity<ContractPresenter> implements View.OnClickListener, IContractView {
    private static final int REQUEST_CODE = 201;
    private static final String TAG = "ContractActivity";
    private HashMap _$_findViewCache;
    private ContractDeviceAdapter mContractDeviceAdapter;
    private List<FrequentContactsV3> mFrequentContactsV3List = new ArrayList();
    private List<FrequentContactsV3> mOriginalFrequentContactsV3List = new ArrayList();
    private List<FrequentContactsV3> mTmpFrequentContactsV3List = new ArrayList();
    private ContractEnum mState = ContractEnum.CONTRACT_EDIT;

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
        return R.layout.activity_contract;
    }

    public final ContractDeviceAdapter getMContractDeviceAdapter() {
        return this.mContractDeviceAdapter;
    }

    public final void setMContractDeviceAdapter(ContractDeviceAdapter contractDeviceAdapter) {
        this.mContractDeviceAdapter = contractDeviceAdapter;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mTitleBar.initLayout(4).setRightTextColor(getColor(R.color.color_4F6EB9)).setLeftText(getLanguageText(R.string.public_cancel)).setLeftTextColor(ResourceUtil.getColor(R.color.color_4F6EB9)).switchLeftImgAndTextVisibility(true).setLeftTextOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.contract.ContractActivity.initViews.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ContractActivity.this.restoreData();
            }
        }).setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.contract.ContractActivity.initViews.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ContractActivity.this.onBackPressed();
            }
        }).setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.contract.ContractActivity.initViews.3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (ContractActivity.this.mState != ContractEnum.CONTRACT_EDIT) {
                    if (ContractActivity.this.mState == ContractEnum.CONTRACT_DELETE) {
                        ContractActivity.this.saveData();
                        return;
                    }
                    return;
                }
                ContractActivity.this.showDeleteLayout();
            }
        }).setRightVisible(false);
        this.mTitleBar.setTitle(LanguageUtil.getLanguageText(R.string.device_contract));
        this.mTitleBar.setRightText(LanguageUtil.getLanguageText(R.string.edit));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void restoreData() {
        showEditLayout();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_add)).setOnClickListener(this);
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        if (checkDataChanged()) {
            showLoadingDialog();
            ((ContractPresenter) this.mPresenter).updateFrequentContacts(this.mFrequentContactsV3List);
        } else {
            showEditLayout();
        }
    }

    private final boolean checkDataChanged() {
        return !Intrinsics.areEqual(this.mFrequentContactsV3List.toString(), this.mOriginalFrequentContactsV3List.toString());
    }

    @Override // com.ido.life.module.device.contract.IContractView
    public void showEditLayout() {
        this.mTitleBar.setRightText(LanguageUtil.getLanguageText(R.string.edit));
        TextView tv_add = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_add);
        Intrinsics.checkExpressionValueIsNotNull(tv_add, "tv_add");
        tv_add.setVisibility(0);
        this.mState = ContractEnum.CONTRACT_EDIT;
        RegularTextView tv_contract = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_contract);
        Intrinsics.checkExpressionValueIsNotNull(tv_contract, "tv_contract");
        tv_contract.setText(LanguageUtil.getLanguageText(R.string.device_can_add_contract));
        RegularTextView tv_contract2 = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_contract);
        Intrinsics.checkExpressionValueIsNotNull(tv_contract2, "tv_contract");
        tv_contract2.setVisibility(0);
        NestedScrollView scroll_view = (NestedScrollView) _$_findCachedViewById(com.ido.life.R.id.scroll_view);
        Intrinsics.checkExpressionValueIsNotNull(scroll_view, "scroll_view");
        scroll_view.setVisibility(0);
        this.mTitleBar.switchLeftImgAndTextVisibility(true);
        this.mFrequentContactsV3List.clear();
        this.mFrequentContactsV3List.addAll(this.mTmpFrequentContactsV3List);
        ContractDeviceAdapter contractDeviceAdapter = this.mContractDeviceAdapter;
        if (contractDeviceAdapter != null) {
            contractDeviceAdapter.setState(this.mState);
        }
        ContractDeviceAdapter contractDeviceAdapter2 = this.mContractDeviceAdapter;
        if (contractDeviceAdapter2 != null) {
            contractDeviceAdapter2.notifyDataSetChanged();
        }
        this.mTitleBar.setRightVisible(this.mFrequentContactsV3List.size() > 0);
        if (this.mFrequentContactsV3List.size() == 0) {
            LinearLayout ll_no_contract = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_no_contract);
            Intrinsics.checkExpressionValueIsNotNull(ll_no_contract, "ll_no_contract");
            ll_no_contract.setVisibility(0);
        } else {
            if (this.mFrequentContactsV3List.size() == 10) {
                ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_add)).setBackgroundResource(R.drawable.selector_comm_btn_grey_pro);
            } else {
                ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_add)).setBackgroundResource(R.drawable.selector_item_bottom_16_corner_bg);
            }
            LinearLayout ll_no_contract2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_no_contract);
            Intrinsics.checkExpressionValueIsNotNull(ll_no_contract2, "ll_no_contract");
            ll_no_contract2.setVisibility(8);
        }
    }

    @Override // com.ido.life.module.device.contract.IContractView
    public void showDeleteLayout() {
        this.mTitleBar.setRightText(LanguageUtil.getLanguageText(R.string.mine_complete));
        TextView tv_add = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_add);
        Intrinsics.checkExpressionValueIsNotNull(tv_add, "tv_add");
        tv_add.setVisibility(8);
        this.mState = ContractEnum.CONTRACT_DELETE;
        RegularTextView tv_contract = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_contract);
        Intrinsics.checkExpressionValueIsNotNull(tv_contract, "tv_contract");
        tv_contract.setText(LanguageUtil.getLanguageText(R.string.tip_contacts_edit));
        ContractDeviceAdapter contractDeviceAdapter = this.mContractDeviceAdapter;
        if (contractDeviceAdapter != null) {
            if (contractDeviceAdapter != null) {
                contractDeviceAdapter.setState(this.mState);
            }
            ContractDeviceAdapter contractDeviceAdapter2 = this.mContractDeviceAdapter;
            if (contractDeviceAdapter2 != null) {
                contractDeviceAdapter2.notifyDataSetChanged();
            }
        }
        ContractDeviceAdapter contractDeviceAdapter3 = this.mContractDeviceAdapter;
        if (contractDeviceAdapter3 != null) {
            contractDeviceAdapter3.setOnItemListener(new ContractDeviceAdapter.OnItemListener() { // from class: com.ido.life.module.device.contract.ContractActivity.showDeleteLayout.1
                @Override // com.ido.life.module.device.contract.ContractActivity.ContractDeviceAdapter.OnItemListener
                public void onLongClickListener(FrequentContactsV3 frequentContactsV3) {
                    Intrinsics.checkParameterIsNotNull(frequentContactsV3, "frequentContactsV3");
                    ContractActivity.this.showDelete(frequentContactsV3);
                }
            });
        }
        this.mTitleBar.switchLeftImgAndTextVisibility(false);
        this.mTmpFrequentContactsV3List.clear();
        this.mTmpFrequentContactsV3List.addAll(this.mFrequentContactsV3List);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mContractDeviceAdapter = new ContractDeviceAdapter(this.mFrequentContactsV3List);
        ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_contract)).addItemDecoration(new CustomItemDecoration().marginLeft(DipPixelUtil.dip2pxF(16.0f)).color(ContextCompat.getColor(this, R.color.color_EBEEF4)).height(DipPixelUtil.dip2pxF(0.5f)));
        RecyclerView recycler_contract = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_contract);
        Intrinsics.checkExpressionValueIsNotNull(recycler_contract, "recycler_contract");
        recycler_contract.setAdapter(this.mContractDeviceAdapter);
        ((ContractPresenter) this.mPresenter).getDeviceContracts();
        ContractDeviceAdapter contractDeviceAdapter = this.mContractDeviceAdapter;
        if (contractDeviceAdapter != null) {
            contractDeviceAdapter.setOnItemListener(new ContractDeviceAdapter.OnItemListener() { // from class: com.ido.life.module.device.contract.ContractActivity.initData.1
                @Override // com.ido.life.module.device.contract.ContractActivity.ContractDeviceAdapter.OnItemListener
                public void onLongClickListener(FrequentContactsV3 frequentContactsV3) {
                    Intrinsics.checkParameterIsNotNull(frequentContactsV3, "frequentContactsV3");
                    ContractActivity.this.showDelete(frequentContactsV3);
                }
            });
        }
    }

    /* JADX INFO: compiled from: ContractActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u001b\u001cB\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u000eH\u0016J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000eH\u0016J\u000e\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\nJ\u000e\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\fR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/ido/life/module/device/contract/ContractActivity$ContractDeviceAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/module/device/contract/ContractActivity$ContractDeviceAdapter$ViewHolder;", "frequentContactsV3List", "", "Lcom/ido/ble/protocol/model/FrequentContactsV3;", "(Ljava/util/List;)V", "getFrequentContactsV3List", "()Ljava/util/List;", "mOnItemListener", "Lcom/ido/life/module/device/contract/ContractActivity$ContractDeviceAdapter$OnItemListener;", "mState", "Lcom/ido/life/enums/ContractEnum;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setOnItemListener", "onItemListener", "setState", "state", "OnItemListener", "ViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class ContractDeviceAdapter extends RecyclerView.Adapter<ViewHolder> {
        private final List<FrequentContactsV3> frequentContactsV3List;
        private OnItemListener mOnItemListener;
        private ContractEnum mState;

        /* JADX INFO: compiled from: ContractActivity.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/ido/life/module/device/contract/ContractActivity$ContractDeviceAdapter$OnItemListener;", "", "onLongClickListener", "", "frequentContactsV3", "Lcom/ido/ble/protocol/model/FrequentContactsV3;", "app_release"}, k = 1, mv = {1, 1, 16})
        public interface OnItemListener {
            void onLongClickListener(FrequentContactsV3 frequentContactsV3);
        }

        public final List<FrequentContactsV3> getFrequentContactsV3List() {
            return this.frequentContactsV3List;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ContractDeviceAdapter(List<? extends FrequentContactsV3> frequentContactsV3List) {
            Intrinsics.checkParameterIsNotNull(frequentContactsV3List, "frequentContactsV3List");
            this.frequentContactsV3List = frequentContactsV3List;
            this.mState = ContractEnum.CONTRACT_EDIT;
        }

        public final void setOnItemListener(OnItemListener onItemListener) {
            Intrinsics.checkParameterIsNotNull(onItemListener, "onItemListener");
            this.mOnItemListener = onItemListener;
        }

        /* JADX INFO: compiled from: ContractActivity.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/ido/life/module/device/contract/ContractActivity$ContractDeviceAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "contractCheck", "Landroid/widget/CheckBox;", "getContractCheck", "()Landroid/widget/CheckBox;", "tvName", "Landroid/widget/TextView;", "getTvName", "()Landroid/widget/TextView;", "tvPhone", "getTvPhone", "app_release"}, k = 1, mv = {1, 1, 16})
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
        /* JADX WARN: Type inference failed for: r4v2, types: [T, com.ido.ble.protocol.model.FrequentContactsV3] */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder holder, int position) {
            Intrinsics.checkParameterIsNotNull(holder, "holder");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = this.frequentContactsV3List.get(position);
            holder.getTvName().setText(((FrequentContactsV3) objectRef.element).name);
            holder.getTvPhone().setText(((FrequentContactsV3) objectRef.element).phone);
            holder.getContractCheck().setVisibility(8);
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.ido.life.module.device.contract.ContractActivity$ContractDeviceAdapter$onBindViewHolder$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    ContractActivity.ContractDeviceAdapter.OnItemListener onItemListener;
                    if (this.this$0.mState != ContractEnum.CONTRACT_DELETE || this.this$0.mOnItemListener == null || (onItemListener = this.this$0.mOnItemListener) == null) {
                        return true;
                    }
                    onItemListener.onLongClickListener((FrequentContactsV3) objectRef.element);
                    return true;
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.frequentContactsV3List.size();
        }

        public final void setState(ContractEnum state) {
            Intrinsics.checkParameterIsNotNull(state, "state");
            this.mState = state;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_add) {
            if (this.mFrequentContactsV3List.size() == Constant.MAX_CONTACT_COUNT) {
                NormalToast.showToast(getLanguageText(R.string.contact_out_of_range_tips));
                return;
            }
            Intent intent = new Intent(this, (Class<?>) ContractAddActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(ContractAddActivity.EXTRA_FREQUENT_CONTRACT_LIST, (Serializable) this.mFrequentContactsV3List);
            intent.putExtras(bundle);
            startActivityForResult(intent, 201);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_search_key) {
            Intent intent2 = new Intent(this, (Class<?>) ContractSearchActivity.class);
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable(ContractSearchActivity.EXTRA_CONTRA_DEVICE, (Serializable) this.mFrequentContactsV3List);
            intent2.putExtras(bundle2);
            startActivityForResult(intent2, 201);
        }
    }

    @Override // com.ido.life.module.device.contract.IContractView
    public void setChoiceContract(int count) {
        RegularTextView tv_contract = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_contract);
        Intrinsics.checkExpressionValueIsNotNull(tv_contract, "tv_contract");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String languageText = LanguageUtil.getLanguageText(R.string.device_choice_contract);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…g.device_choice_contract)");
        Object[] objArr = {Integer.valueOf(count)};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        tv_contract.setText(str);
    }

    @Override // com.ido.life.module.device.contract.IContractView
    public void onSetFrequentFail() {
        showCmdResultToast(false);
        dismissLoadingDialog();
    }

    @Override // com.ido.life.module.device.contract.IContractView
    public void setDeviceContracts(List<? extends FrequentContactsV3> frequentContactsV3) {
        Log.d(TAG, "setDeviceContracts: " + String.valueOf(frequentContactsV3));
        dismissLoadingDialog();
        ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.loadLayout)).showContent();
        this.mOriginalFrequentContactsV3List.clear();
        this.mTmpFrequentContactsV3List.clear();
        if (frequentContactsV3 != null) {
            List<? extends FrequentContactsV3> list = frequentContactsV3;
            if (!list.isEmpty()) {
                List<FrequentContactsV3> list2 = this.mFrequentContactsV3List;
                if (list2 != null) {
                    list2.clear();
                }
                List<FrequentContactsV3> list3 = this.mFrequentContactsV3List;
                if (list3 != null) {
                    list3.addAll(list);
                }
                this.mTmpFrequentContactsV3List.addAll(this.mFrequentContactsV3List);
                this.mOriginalFrequentContactsV3List.addAll(this.mFrequentContactsV3List);
                showEditLayout();
                return;
            }
        }
        showEditLayout();
        setNoContractLayout(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDelete(FrequentContactsV3 frequentContactsV3) {
        Iterator<FrequentContactsV3> it = this.mFrequentContactsV3List.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(it.next().phone, frequentContactsV3.phone)) {
                it.remove();
            }
        }
        ContractDeviceAdapter contractDeviceAdapter = this.mContractDeviceAdapter;
        if (contractDeviceAdapter != null) {
            contractDeviceAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.ido.life.module.device.contract.IContractView
    public void setNoContractLayout(boolean visible) {
        ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.loadLayout)).showContent();
        if (visible) {
            LinearLayout ll_no_contract = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_no_contract);
            Intrinsics.checkExpressionValueIsNotNull(ll_no_contract, "ll_no_contract");
            ll_no_contract.setVisibility(0);
            RegularTextView tv_contract = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_contract);
            Intrinsics.checkExpressionValueIsNotNull(tv_contract, "tv_contract");
            tv_contract.setVisibility(8);
            NestedScrollView scroll_view = (NestedScrollView) _$_findCachedViewById(com.ido.life.R.id.scroll_view);
            Intrinsics.checkExpressionValueIsNotNull(scroll_view, "scroll_view");
            scroll_view.setVisibility(8);
            this.mTitleBar.setRightVisible(false);
            TextView tv_add = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_add);
            Intrinsics.checkExpressionValueIsNotNull(tv_add, "tv_add");
            tv_add.setVisibility(0);
            return;
        }
        this.mTitleBar.setRightText(LanguageUtil.getLanguageText(R.string.edit));
        RegularTextView tv_contract2 = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_contract);
        Intrinsics.checkExpressionValueIsNotNull(tv_contract2, "tv_contract");
        tv_contract2.setVisibility(0);
        NestedScrollView scroll_view2 = (NestedScrollView) _$_findCachedViewById(com.ido.life.R.id.scroll_view);
        Intrinsics.checkExpressionValueIsNotNull(scroll_view2, "scroll_view");
        scroll_view2.setVisibility(0);
        LinearLayout ll_no_contract2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_no_contract);
        Intrinsics.checkExpressionValueIsNotNull(ll_no_contract2, "ll_no_contract");
        ll_no_contract2.setVisibility(8);
        this.mTitleBar.setRightVisible(this.mFrequentContactsV3List.size() > 0);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: " + requestCode + ',' + resultCode);
        if (requestCode == 201) {
            Bundle extras = data != null ? data.getExtras() : null;
            if (extras != null) {
                Serializable serializable = extras.getSerializable(ContractAddActivity.EXTRA_FREQUENT_CONTRACT_LIST);
                if (serializable == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableList<com.ido.ble.protocol.model.FrequentContactsV3>");
                }
                List listAsMutableList = TypeIntrinsics.asMutableList(serializable);
                List<FrequentContactsV3> list = this.mFrequentContactsV3List;
                if (list != null) {
                    list.clear();
                }
                List<FrequentContactsV3> list2 = this.mFrequentContactsV3List;
                if (list2 != null) {
                    list2.addAll(listAsMutableList);
                }
                this.mTmpFrequentContactsV3List.clear();
                this.mTmpFrequentContactsV3List.addAll(this.mFrequentContactsV3List);
                this.mOriginalFrequentContactsV3List.clear();
                this.mOriginalFrequentContactsV3List.addAll(this.mFrequentContactsV3List);
                showEditLayout();
            }
        }
    }
}