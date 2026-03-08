package com.ido.life.module.device.contract.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.InputMethodUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.enums.ContractEnum;
import com.ido.life.module.device.contract.PhoneDto;
import com.ido.life.util.CoroutinesUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: ContractSearchActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0002\u0018\u0019B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u000eH\u0014J\b\u0010\u0010\u001a\u00020\u000eH\u0016J\u0012\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000eH\u0014J\u0016\u0010\u0015\u001a\u00020\u000e2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\u0017H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/ido/life/module/device/contract/search/ContractSearchActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/contract/search/ContractSearchPresenter;", "Lcom/ido/life/module/device/contract/search/IContractSearchView;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/ido/life/module/device/contract/search/ContractSearchActivity$ContractSearchAdapter;", "photoDtosList", "", "Lcom/ido/life/module/device/contract/PhoneDto;", "getLayoutResId", "", "initData", "", "initEvent", "initViews", "onClick", "v", "Landroid/view/View;", "onDestroy", "setPhoneDtosList", "photoDtos", "", "Companion", "ContractSearchAdapter", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ContractSearchActivity extends BaseActivity<ContractSearchPresenter> implements IContractSearchView, View.OnClickListener {
    public static final String EXTRA_CONTRA_DEVICE = "contract_device";
    public static final int RESULT_CODE = 1000;
    public static final String RESULT_PHONE = "contract_phone";
    private static final String TAG = "ContractSearchActivity";
    private HashMap _$_findViewCache;
    private ContractSearchAdapter adapter;
    private List<PhoneDto> photoDtosList;

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
        return R.layout.activity_contract_search;
    }

    /* JADX INFO: renamed from: com.ido.life.module.device.contract.search.ContractSearchActivity$initEvent$1, reason: invalid class name */
    /* JADX INFO: compiled from: ContractSearchActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lkotlinx/coroutines/channels/ProducerScope;", "", "invoke"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass1 extends Lambda implements Function1<ProducerScope<? super String>, Unit> {
        final /* synthetic */ Ref.ObjectRef $mTextWatcher;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Ref.ObjectRef objectRef) {
            super(1);
            this.$mTextWatcher = objectRef;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ProducerScope<? super String> producerScope) {
            invoke2(producerScope);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.ido.life.module.device.contract.search.ContractSearchActivity$initEvent$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: ContractSearchActivity.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J(\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0016J(\u0010\r\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0016¨\u0006\u000e"}, d2 = {"com/ido/life/module/device/contract/search/ContractSearchActivity$initEvent$1$1", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "charSequence", "", "i", "", "i1", "i2", "onTextChanged", "app_release"}, k = 1, mv = {1, 1, 16})
        public static final class C01081 implements TextWatcher {
            final /* synthetic */ ProducerScope $it;

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Intrinsics.checkParameterIsNotNull(charSequence, "charSequence");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Intrinsics.checkParameterIsNotNull(charSequence, "charSequence");
            }

            C01081(ProducerScope producerScope) {
                this.$it = producerScope;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                Intrinsics.checkParameterIsNotNull(s, "s");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new ContractSearchActivity$initEvent$1$1$afterTextChanged$1(this, s, null), 2, null);
                this.$it.offer(s.toString());
            }
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [T, android.text.TextWatcher] */
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(ProducerScope<? super String> it) {
            Intrinsics.checkParameterIsNotNull(it, "it");
            this.$mTextWatcher.element = new C01081(it);
            ((EditText) ContractSearchActivity.this._$_findCachedViewById(com.ido.life.R.id.et_search_key)).addTextChangedListener((TextWatcher) this.$mTextWatcher.element);
        }
    }

    public static final /* synthetic */ ContractSearchPresenter access$getMPresenter$p(ContractSearchActivity contractSearchActivity) {
        return (ContractSearchPresenter) contractSearchActivity.mPresenter;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        setStatusBarColor(getColor(R.color.color_1E1E1E));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        Bundle extras = intent.getExtras();
        ArrayList arrayList = new ArrayList();
        if (extras != null && extras.getSerializable(EXTRA_CONTRA_DEVICE) != null) {
            Serializable serializable = extras.getSerializable(EXTRA_CONTRA_DEVICE);
            if (serializable == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableList<com.ido.ble.protocol.model.FrequentContactsV3>");
            }
            arrayList = TypeIntrinsics.asMutableList(serializable);
        }
        ((ContractSearchPresenter) this.mPresenter).getPhoneContracts(arrayList);
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [T, android.text.TextWatcher] */
    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        ContractSearchActivity contractSearchActivity = this;
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_contract_search_cancel)).setOnClickListener(contractSearchActivity);
        EditText et_search_key = (EditText) _$_findCachedViewById(com.ido.life.R.id.et_search_key);
        Intrinsics.checkExpressionValueIsNotNull(et_search_key, "et_search_key");
        et_search_key.setFocusable(true);
        EditText et_search_key2 = (EditText) _$_findCachedViewById(com.ido.life.R.id.et_search_key);
        Intrinsics.checkExpressionValueIsNotNull(et_search_key2, "et_search_key");
        et_search_key2.setFocusableInTouchMode(true);
        ((EditText) _$_findCachedViewById(com.ido.life.R.id.et_search_key)).requestFocus();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (TextWatcher) 0;
        CoroutinesUtils.INSTANCE.debounce(400L, new AnonymousClass1(objectRef), (4 & 4) != 0 ? (Function0) null : new Function0<Unit>() { // from class: com.ido.life.module.device.contract.search.ContractSearchActivity.initEvent.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ((EditText) ContractSearchActivity.this._$_findCachedViewById(com.ido.life.R.id.et_search_key)).removeTextChangedListener((TextWatcher) objectRef.element);
            }
        }, (4 & 8) != 0 ? (Function1) null : null, new Function1<String, Unit>() { // from class: com.ido.life.module.device.contract.search.ContractSearchActivity.initEvent.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: com.ido.life.module.device.contract.search.ContractSearchActivity$initEvent$3$1, reason: invalid class name */
            /* JADX INFO: compiled from: ContractSearchActivity.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
            @DebugMetadata(c = "com.ido.life.module.device.contract.search.ContractSearchActivity$initEvent$3$1", f = "ContractSearchActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $it;
                int label;
                private CoroutineScope p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(String str, Continuation continuation) {
                    super(2, continuation);
                    this.$it = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    AnonymousClass1 anonymousClass1 = AnonymousClass3.this.new AnonymousClass1(this.$it, completion);
                    anonymousClass1.p$ = (CoroutineScope) obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) throws Throwable {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.p$;
                    ContractSearchActivity.access$getMPresenter$p(ContractSearchActivity.this).doSearch(this.$it);
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(ContractSearchActivity.this), Dispatchers.getMain(), null, new AnonymousClass1(it, null), 2, null);
            }
        });
        InputMethodUtil.showInput(this, (EditText) _$_findCachedViewById(com.ido.life.R.id.et_search_key));
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.search_iv_delete)).setOnClickListener(contractSearchActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_contract_search_cancel) {
            finishAfterTransition();
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_contract_search) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            Object tag = v.getTag();
            List<PhoneDto> list = this.photoDtosList;
            if (list == null) {
                Intrinsics.throwUninitializedPropertyAccessException("photoDtosList");
            }
            if (tag == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
            bundle.putString(RESULT_PHONE, list.get(((Integer) tag).intValue()).getTelePhone());
            intent.putExtras(bundle);
            setResult(1000, intent);
            finishAfterTransition();
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.search_iv_delete) {
            ((EditText) _$_findCachedViewById(com.ido.life.R.id.et_search_key)).setText("");
            ((EditText) _$_findCachedViewById(com.ido.life.R.id.et_search_key)).requestFocus();
        }
    }

    /* JADX INFO: compiled from: ContractSearchActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001dB\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0012H\u0016J\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0012H\u0016J\u000e\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u000eR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001e"}, d2 = {"Lcom/ido/life/module/device/contract/search/ContractSearchActivity$ContractSearchAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/module/device/contract/search/ContractSearchActivity$ContractSearchAdapter$ViewHolder;", "phonesList", "", "Lcom/ido/life/module/device/contract/PhoneDto;", "mClickListenter", "Landroid/view/View$OnClickListener;", "(Ljava/util/List;Landroid/view/View$OnClickListener;)V", "getMClickListenter", "()Landroid/view/View$OnClickListener;", "setMClickListenter", "(Landroid/view/View$OnClickListener;)V", "mState", "Lcom/ido/life/enums/ContractEnum;", "getPhonesList", "()Ljava/util/List;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setState", "state", "ViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class ContractSearchAdapter extends RecyclerView.Adapter<ViewHolder> {
        private View.OnClickListener mClickListenter;
        private ContractEnum mState;
        private final List<PhoneDto> phonesList;

        public final List<PhoneDto> getPhonesList() {
            return this.phonesList;
        }

        public final View.OnClickListener getMClickListenter() {
            return this.mClickListenter;
        }

        public final void setMClickListenter(View.OnClickListener onClickListener) {
            Intrinsics.checkParameterIsNotNull(onClickListener, "<set-?>");
            this.mClickListenter = onClickListener;
        }

        public ContractSearchAdapter(List<PhoneDto> phonesList, View.OnClickListener mClickListenter) {
            Intrinsics.checkParameterIsNotNull(phonesList, "phonesList");
            Intrinsics.checkParameterIsNotNull(mClickListenter, "mClickListenter");
            this.phonesList = phonesList;
            this.mClickListenter = mClickListenter;
            this.mState = ContractEnum.CONTRACT_EDIT;
        }

        /* JADX INFO: compiled from: ContractSearchActivity.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/ido/life/module/device/contract/search/ContractSearchActivity$ContractSearchAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "tvName", "Landroid/widget/TextView;", "getTvName", "()Landroid/widget/TextView;", "tvPhone", "getTvPhone", "app_release"}, k = 1, mv = {1, 1, 16})
        public static final class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView tvName;
            private final TextView tvPhone;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ViewHolder(View view) {
                super(view);
                Intrinsics.checkParameterIsNotNull(view, "view");
                View viewFindViewById = view.findViewById(R.id.tv_contract_name);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "view.findViewById(R.id.tv_contract_name)");
                this.tvName = (TextView) viewFindViewById;
                View viewFindViewById2 = view.findViewById(R.id.tv_contract_phone);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "view.findViewById(R.id.tv_contract_phone)");
                this.tvPhone = (TextView) viewFindViewById2;
            }

            public final TextView getTvName() {
                return this.tvName;
            }

            public final TextView getTvPhone() {
                return this.tvPhone;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Intrinsics.checkParameterIsNotNull(parent, "parent");
            View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_contract_search, parent, false);
            Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(pare…ct_search, parent, false)");
            return new ViewHolder(viewInflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder holder, int position) {
            Intrinsics.checkParameterIsNotNull(holder, "holder");
            PhoneDto phoneDto = this.phonesList.get(position);
            holder.getTvName().setText(phoneDto.getName());
            holder.getTvPhone().setText(phoneDto.getTelePhone());
            View view = holder.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
            view.setTag(Integer.valueOf(position));
            holder.itemView.setOnClickListener(this.mClickListenter);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.phonesList.size();
        }

        public final void setState(ContractEnum state) {
            Intrinsics.checkParameterIsNotNull(state, "state");
            this.mState = state;
        }
    }

    @Override // com.ido.life.module.device.contract.search.IContractSearchView
    public void setPhoneDtosList(List<PhoneDto> photoDtos) {
        Intrinsics.checkParameterIsNotNull(photoDtos, "photoDtos");
        this.photoDtosList = new ArrayList();
        List<PhoneDto> list = this.photoDtosList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("photoDtosList");
        }
        list.addAll(photoDtos);
        this.adapter = new ContractSearchAdapter(photoDtos, this);
        RecyclerView recycler_contract_search = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_contract_search);
        Intrinsics.checkExpressionValueIsNotNull(recycler_contract_search, "recycler_contract_search");
        ContractSearchAdapter contractSearchAdapter = this.adapter;
        if (contractSearchAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        recycler_contract_search.setAdapter(contractSearchAdapter);
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        InputMethodUtil.hiddenInput(this, (EditText) _$_findCachedViewById(com.ido.life.R.id.et_search_key));
    }
}