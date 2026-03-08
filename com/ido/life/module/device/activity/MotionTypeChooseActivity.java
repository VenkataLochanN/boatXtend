package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.life.adapter.MotionTypeChooseAdapter;
import com.ido.life.base.BaseActivity;
import com.ido.life.bean.MotionTypeBean;
import com.ido.life.customview.CustomItemDecoration;
import com.ido.life.customview.LoadingLayout;
import com.ido.life.customview.OnItemClickListener;
import com.ido.life.data.cache.MotionTypeManager;
import com.ido.life.module.device.presenter.MotionTypeChoosePresenter;
import com.ido.life.module.device.view.IMotionTypeChooseView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MotionTypeChooseActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0012H\u0014J\b\u0010\u0017\u001a\u00020\u0012H\u0014J\b\u0010\u0018\u001a\u00020\u0012H\u0002J\b\u0010\u0019\u001a\u00020\u0012H\u0016J\b\u0010\u001a\u001a\u00020\u0012H\u0016J\u0010\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0012H\u0016J\u0016\u0010\u001f\u001a\u00020\u00122\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00100!H\u0016J\b\u0010\"\u001a\u00020\u0012H\u0002J\b\u0010#\u001a\u00020\u0012H\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/ido/life/module/device/activity/MotionTypeChooseActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/presenter/MotionTypeChoosePresenter;", "Lcom/ido/life/module/device/view/IMotionTypeChooseView;", "Landroid/view/View$OnClickListener;", "()V", "decoration", "Lcom/ido/life/customview/CustomItemDecoration;", "isEdit", "", "mAdapter", "Lcom/ido/life/adapter/MotionTypeChooseAdapter;", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "mMotionTypeList", "", "Lcom/ido/life/bean/MotionTypeBean;", "checkSize", "", "commit", "getLayoutResId", "", "initData", "initEvent", "initRecyclerView", "initViews", "onBackPressed", "onClick", "view", "Landroid/view/View;", "onGetAllMotionTypesFailed", "onGetAllMotionTypesSuccess", "types", "", "switchEditStatus", "updateView", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MotionTypeChooseActivity extends BaseActivity<MotionTypeChoosePresenter> implements IMotionTypeChooseView, View.OnClickListener {
    private HashMap _$_findViewCache;
    private CustomItemDecoration decoration;
    private boolean isEdit;
    private MotionTypeChooseAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<MotionTypeBean> mMotionTypeList = new ArrayList();

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
        return R.layout.activity_motion_type_choose;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        initRecyclerView();
    }

    private final void initRecyclerView() {
        ArrayList arrayList = (ArrayList) getIntent().getSerializableExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        MotionTypeChooseActivity motionTypeChooseActivity = this;
        this.mLayoutManager = new LinearLayoutManager(motionTypeChooseActivity);
        RecyclerView recyclerview = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview, "recyclerview");
        recyclerview.setLayoutManager(this.mLayoutManager);
        this.mAdapter = new MotionTypeChooseAdapter(motionTypeChooseActivity, this.mMotionTypeList, arrayList);
        RecyclerView recyclerview2 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview2, "recyclerview");
        recyclerview2.setAdapter(this.mAdapter);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        MotionTypeChooseAdapter motionTypeChooseAdapter = this.mAdapter;
        if (motionTypeChooseAdapter != null) {
            motionTypeChooseAdapter.setOnAddClickListener(new OnItemClickListener() { // from class: com.ido.life.module.device.activity.MotionTypeChooseActivity.initEvent.1
                @Override // com.ido.life.customview.OnItemClickListener
                public void onItemClick(int position) {
                    MotionTypeChooseActivity.this.updateView();
                }
            });
        }
        MotionTypeChooseAdapter motionTypeChooseAdapter2 = this.mAdapter;
        if (motionTypeChooseAdapter2 != null) {
            motionTypeChooseAdapter2.setOnDeleteClickListener(new OnItemClickListener() { // from class: com.ido.life.module.device.activity.MotionTypeChooseActivity.initEvent.2
                @Override // com.ido.life.customview.OnItemClickListener
                public void onItemClick(int position) {
                    MotionTypeChooseActivity.this.updateView();
                }
            });
        }
        MotionTypeChooseActivity motionTypeChooseActivity = this;
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tvRight)).setOnClickListener(motionTypeChooseActivity);
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.ivLeft)).setOnClickListener(motionTypeChooseActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == R.id.ivLeft) {
            onBackPressed();
        } else {
            if (id != R.id.tvRight) {
                return;
            }
            if (this.isEdit) {
                commit();
            } else {
                switchEditStatus();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateView() {
        checkSize();
    }

    private final void checkSize() {
        List<MotionTypeBean> list = this.mMotionTypeList;
        if (list != null) {
            if (list.size() > 0) {
                ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer)).showContent();
            } else {
                LoadingLayout.showEmpty$default((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer), null, 0, 3, null);
            }
            MotionTypeChooseAdapter motionTypeChooseAdapter = this.mAdapter;
            ArrayList<MotionTypeBean> selectedDatas = motionTypeChooseAdapter != null ? motionTypeChooseAdapter.getSelectedDatas() : null;
            if (selectedDatas != null) {
                TextView tvSubTitle = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvSubTitle);
                Intrinsics.checkExpressionValueIsNotNull(tvSubTitle, "tvSubTitle");
                String languageText = getLanguageText(R.string.motion_type_count_max);
                Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string.motion_type_count_max)");
                Object[] objArr = {Integer.valueOf(selectedDatas.size()), Integer.valueOf(MotionTypeManager.INSTANCE.getMAX_MOTION_TYPE())};
                String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                tvSubTitle.setText(str);
            }
        }
    }

    private final void switchEditStatus() {
        this.isEdit = !this.isEdit;
        TextView tvRight = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvRight);
        Intrinsics.checkExpressionValueIsNotNull(tvRight, "tvRight");
        tvRight.setText(getLanguageText(this.isEdit ? R.string.public_complete : R.string.public_edit));
        ImageView ivLeft = (ImageView) _$_findCachedViewById(com.ido.life.R.id.ivLeft);
        Intrinsics.checkExpressionValueIsNotNull(ivLeft, "ivLeft");
        ivLeft.setVisibility(this.isEdit ? 4 : 0);
        MotionTypeChooseAdapter motionTypeChooseAdapter = this.mAdapter;
        if (motionTypeChooseAdapter != null) {
            motionTypeChooseAdapter.setEdit(this.isEdit);
        }
    }

    private final void commit() {
        Intent intent = new Intent(this, (Class<?>) MotionTypesActivity.class);
        MotionTypeChooseAdapter motionTypeChooseAdapter = this.mAdapter;
        setResult(-1, intent.putExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME, motionTypeChooseAdapter != null ? motionTypeChooseAdapter.getSelectedDatas() : null));
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.isEdit) {
            return;
        }
        super.onBackPressed();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer)).showLoading();
        MotionTypeChoosePresenter motionTypeChoosePresenter = (MotionTypeChoosePresenter) this.mPresenter;
        if (motionTypeChoosePresenter != null) {
            motionTypeChoosePresenter.getAllMotionTypes();
        }
    }

    @Override // com.ido.life.module.device.view.IMotionTypeChooseView
    public void onGetAllMotionTypesSuccess(List<MotionTypeBean> types) {
        Intrinsics.checkParameterIsNotNull(types, "types");
        List<MotionTypeBean> list = this.mMotionTypeList;
        if (list != null) {
            list.clear();
        }
        List<MotionTypeBean> list2 = this.mMotionTypeList;
        if (list2 != null) {
            list2.addAll(types);
        }
        MotionTypeChooseAdapter motionTypeChooseAdapter = this.mAdapter;
        if (motionTypeChooseAdapter != null) {
            motionTypeChooseAdapter.update();
        }
        updateView();
    }

    @Override // com.ido.life.module.device.view.IMotionTypeChooseView
    public void onGetAllMotionTypesFailed() {
        LoadingLayout.showError$default((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer), null, false, 0, 7, null);
        ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer)).setRetryListener(new Function0<Unit>() { // from class: com.ido.life.module.device.activity.MotionTypeChooseActivity.onGetAllMotionTypesFailed.1
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
                MotionTypeChooseActivity.this.initData();
            }
        });
    }
}