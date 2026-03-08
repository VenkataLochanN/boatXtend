package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.adapter.SportsDataViewDetailListAdapter;
import com.ido.life.base.BaseActivity;
import com.ido.life.bean.MotionTypeBean;
import com.ido.life.bean.SportsDataViewDetailBean;
import com.ido.life.customview.CustomItemDecoration;
import com.ido.life.customview.LoadingLayout;
import com.ido.life.customview.OnItemClickListener;
import com.ido.life.customview.OnItemMoveListener;
import com.ido.life.customview.recyclerview.DefaultItemTouchHelper;
import com.ido.life.customview.recyclerview.DefaultItemTouchHelperCallback;
import com.ido.life.customview.recyclerview.OnStartDragListener;
import com.ido.life.data.cache.MotionTypeManager;
import com.ido.life.module.device.presenter.SportsDataViewDetailListPresenter;
import com.ido.life.module.device.view.ISportsDataViewDetailListView;
import com.ido.life.util.DialogUtils;
import com.ido.life.util.ListUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SportsDataViewDetailListActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 42\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u0005:\u00014B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0017\u001a\u00020\nH\u0002J\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u0019H\u0014J\b\u0010\u001e\u001a\u00020\u0019H\u0014J\b\u0010\u001f\u001a\u00020\u0019H\u0002J\b\u0010 \u001a\u00020\u0019H\u0002J\b\u0010!\u001a\u00020\u0019H\u0016J\b\u0010\"\u001a\u00020\u0019H\u0016J\b\u0010#\u001a\u00020\u0019H\u0016J$\u0010$\u001a\u00020\u00192\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0018\u0010'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020\u001cH\u0016J\b\u0010*\u001a\u00020\u0019H\u0016J\b\u0010+\u001a\u00020\u0019H\u0016J\u0012\u0010,\u001a\u00020\u00192\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020\u0019H\u0002J\b\u00100\u001a\u00020\u0019H\u0002J\b\u00101\u001a\u00020\u0019H\u0002J\b\u00102\u001a\u00020\u0019H\u0002J\b\u00103\u001a\u00020\u0019H\u0002R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/ido/life/module/device/activity/SportsDataViewDetailListActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/presenter/SportsDataViewDetailListPresenter;", "Lcom/ido/life/module/device/view/ISportsDataViewDetailListView;", "Lcom/ido/life/customview/OnItemMoveListener;", "Lcom/ido/life/customview/recyclerview/OnStartDragListener;", "()V", "dataViewBean", "Lcom/ido/life/bean/MotionTypeBean;", "isEdit", "", "mAdapter", "Lcom/ido/life/adapter/SportsDataViewDetailListAdapter;", "mItemTouchHelper", "Lcom/ido/life/customview/recyclerview/DefaultItemTouchHelper;", "mList", "", "Lcom/ido/life/bean/SportsDataViewDetailBean;", "mNotAddedAdapter", "mNotAddedList", "mNotTmpAddedList", "mOriginalList", "mTmpList", "checkDataChanged", "checkEmpty", "", "commit", "getLayoutResId", "", "initData", "initEvent", "initNotAddedRecyclerView", "initRecyclerView", "initViews", "onBackPressed", "onGetDataFailed", "onGetDataSuccess", "list", "notAddedList", "onItemMove", "fromPosition", "toPosition", "onSetDataFailed", "onSetDataSuccess", "onStartDrag", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "restoreData", "switchEditStatus", "updateAddedList", "updateList", "updateNotAddedList", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SportsDataViewDetailListActivity extends BaseActivity<SportsDataViewDetailListPresenter> implements ISportsDataViewDetailListView, OnItemMoveListener, OnStartDragListener {
    public static final int MAX_RETENTION = 1000;
    public static final int MIN_RETENTION = 2;
    private HashMap _$_findViewCache;
    private MotionTypeBean dataViewBean;
    private boolean isEdit;
    private SportsDataViewDetailListAdapter mAdapter;
    private DefaultItemTouchHelper mItemTouchHelper;
    private SportsDataViewDetailListAdapter mNotAddedAdapter;
    private List<SportsDataViewDetailBean> mOriginalList = new ArrayList();
    private List<SportsDataViewDetailBean> mList = new ArrayList();
    private List<SportsDataViewDetailBean> mTmpList = new ArrayList();
    private List<SportsDataViewDetailBean> mNotAddedList = new ArrayList();
    private List<SportsDataViewDetailBean> mNotTmpAddedList = new ArrayList();

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
        return R.layout.activity_sports_data_view_detail_list;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.dataViewBean = (MotionTypeBean) getIntent().getSerializableExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME);
        this.mTitleBar.initLayout(4).setRightText(getLanguageText(R.string.public_edit)).setRightTextColor(ResourceUtil.getColor(R.color.color_4F6EB9)).setLeftText(getLanguageText(R.string.public_cancel)).setLeftTextColor(ResourceUtil.getColor(R.color.color_4F6EB9)).switchLeftImgAndTextVisibility(true).setLeftTextOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.SportsDataViewDetailListActivity.initViews.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsDataViewDetailListActivity.this.restoreData();
            }
        }).setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.SportsDataViewDetailListActivity.initViews.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsDataViewDetailListActivity.this.onBackPressed();
            }
        }).setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.SportsDataViewDetailListActivity.initViews.3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (SportsDataViewDetailListActivity.this.isEdit) {
                    SportsDataViewDetailListActivity.this.commit();
                } else {
                    SportsDataViewDetailListActivity.this.switchEditStatus();
                }
            }
        });
        initRecyclerView();
        initNotAddedRecyclerView();
        MotionTypeBean motionTypeBean = this.dataViewBean;
        if (motionTypeBean != null) {
            this.mTitleBar.setTitle(MotionTypeManager.INSTANCE.getMotionTypeName(motionTypeBean.getType()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void restoreData() {
        switchEditStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void switchEditStatus() {
        this.isEdit = !this.isEdit;
        this.mTitleBar.setRightText(getLanguageText(this.isEdit ? R.string.public_complete : R.string.public_edit));
        SportsDataViewDetailListAdapter sportsDataViewDetailListAdapter = this.mAdapter;
        if (sportsDataViewDetailListAdapter != null) {
            sportsDataViewDetailListAdapter.setEdit(this.isEdit);
        }
        SportsDataViewDetailListAdapter sportsDataViewDetailListAdapter2 = this.mNotAddedAdapter;
        if (sportsDataViewDetailListAdapter2 != null) {
            sportsDataViewDetailListAdapter2.setEdit(this.isEdit);
        }
        DefaultItemTouchHelper defaultItemTouchHelper = this.mItemTouchHelper;
        if (defaultItemTouchHelper != null) {
            defaultItemTouchHelper.attachToRecyclerView(this.isEdit ? (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview) : null);
        }
        this.mTitleBar.switchLeftImgAndTextVisibility(!this.isEdit);
        if (this.isEdit) {
            this.mTmpList.clear();
            this.mTmpList.addAll(this.mList);
            this.mNotTmpAddedList.clear();
            this.mNotTmpAddedList.addAll(this.mNotAddedList);
            return;
        }
        this.mList.clear();
        this.mList.addAll(this.mTmpList);
        this.mNotAddedList.clear();
        this.mNotAddedList.addAll(this.mNotTmpAddedList);
        updateList();
    }

    private final void initRecyclerView() {
        SportsDataViewDetailListActivity sportsDataViewDetailListActivity = this;
        this.mAdapter = new SportsDataViewDetailListAdapter(sportsDataViewDetailListActivity, this.mList, 1);
        RecyclerView recyclerview = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview, "recyclerview");
        recyclerview.setAdapter(this.mAdapter);
        RecyclerView recyclerview2 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview2, "recyclerview");
        ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview)).addItemDecoration(new CustomItemDecoration(recyclerview2).color(ContextCompat.getColor(sportsDataViewDetailListActivity, R.color.color_F2F2F6)).marginLeft(DipPixelUtil.dip2pxF(16.0f)).marginRight(DipPixelUtil.dip2pxF(0.0f)).height(DipPixelUtil.dip2pxF(0.5f)));
        this.mItemTouchHelper = new DefaultItemTouchHelper(new DefaultItemTouchHelperCallback(this.mAdapter));
        DefaultItemTouchHelper defaultItemTouchHelper = this.mItemTouchHelper;
        if (defaultItemTouchHelper != null) {
            defaultItemTouchHelper.setSwipeEnable(false);
        }
        DefaultItemTouchHelper defaultItemTouchHelper2 = this.mItemTouchHelper;
        if (defaultItemTouchHelper2 != null) {
            defaultItemTouchHelper2.setDragEnable(true);
        }
    }

    private final void initNotAddedRecyclerView() {
        SportsDataViewDetailListActivity sportsDataViewDetailListActivity = this;
        this.mNotAddedAdapter = new SportsDataViewDetailListAdapter(sportsDataViewDetailListActivity, this.mNotAddedList, 2);
        RecyclerView not_added_recyclerview = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.not_added_recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(not_added_recyclerview, "not_added_recyclerview");
        not_added_recyclerview.setAdapter(this.mNotAddedAdapter);
        RecyclerView recyclerview = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview, "recyclerview");
        ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview)).addItemDecoration(new CustomItemDecoration(recyclerview).color(ContextCompat.getColor(sportsDataViewDetailListActivity, R.color.color_F2F2F6)).marginLeft(DipPixelUtil.dip2pxF(16.0f)).marginRight(DipPixelUtil.dip2pxF(0.0f)).height(DipPixelUtil.dip2pxF(0.5f)));
    }

    private final void updateAddedList() {
        SportsDataViewDetailListAdapter sportsDataViewDetailListAdapter = this.mAdapter;
        if (sportsDataViewDetailListAdapter != null) {
            sportsDataViewDetailListAdapter.setEnable(this.mList.size() > 2);
        }
        SportsDataViewDetailListAdapter sportsDataViewDetailListAdapter2 = this.mAdapter;
        if (sportsDataViewDetailListAdapter2 != null) {
            sportsDataViewDetailListAdapter2.notifyDataSetChanged();
        }
    }

    private final void updateNotAddedList() {
        SportsDataViewDetailListAdapter sportsDataViewDetailListAdapter = this.mNotAddedAdapter;
        if (sportsDataViewDetailListAdapter != null) {
            sportsDataViewDetailListAdapter.setEnable(this.mList.size() < 1000);
        }
        SportsDataViewDetailListAdapter sportsDataViewDetailListAdapter2 = this.mNotAddedAdapter;
        if (sportsDataViewDetailListAdapter2 != null) {
            sportsDataViewDetailListAdapter2.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateList() {
        updateAddedList();
        updateNotAddedList();
        checkEmpty();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        SportsDataViewDetailListAdapter sportsDataViewDetailListAdapter = this.mAdapter;
        if (sportsDataViewDetailListAdapter != null) {
            sportsDataViewDetailListAdapter.setOnItemMoveListener(this);
        }
        SportsDataViewDetailListAdapter sportsDataViewDetailListAdapter2 = this.mAdapter;
        if (sportsDataViewDetailListAdapter2 != null) {
            sportsDataViewDetailListAdapter2.setOnStartDragListener(this);
        }
        SportsDataViewDetailListAdapter sportsDataViewDetailListAdapter3 = this.mAdapter;
        if (sportsDataViewDetailListAdapter3 != null) {
            sportsDataViewDetailListAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.ido.life.module.device.activity.SportsDataViewDetailListActivity.initEvent.1
                @Override // com.ido.life.customview.OnItemClickListener
                public void onItemClick(int position) {
                    if (SportsDataViewDetailListActivity.this.isEdit) {
                        SportsDataViewDetailListActivity.this.mNotAddedList.add((SportsDataViewDetailBean) SportsDataViewDetailListActivity.this.mList.remove(position));
                        SportsDataViewDetailListActivity.this.updateList();
                    }
                }
            });
        }
        SportsDataViewDetailListAdapter sportsDataViewDetailListAdapter4 = this.mNotAddedAdapter;
        if (sportsDataViewDetailListAdapter4 != null) {
            sportsDataViewDetailListAdapter4.setOnItemClickListener(new OnItemClickListener() { // from class: com.ido.life.module.device.activity.SportsDataViewDetailListActivity.initEvent.2
                @Override // com.ido.life.customview.OnItemClickListener
                public void onItemClick(int position) {
                    if (SportsDataViewDetailListActivity.this.isEdit) {
                        SportsDataViewDetailListActivity.this.mList.add((SportsDataViewDetailBean) SportsDataViewDetailListActivity.this.mNotAddedList.remove(position));
                        SportsDataViewDetailListActivity.this.updateList();
                    }
                }
            });
        }
    }

    public final void checkEmpty() {
        if (ListUtils.INSTANCE.isNotEmpty(this.mList) || ListUtils.INSTANCE.isNotEmpty(this.mNotAddedList)) {
            ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer)).showContent();
            LinearLayout titleLayoutRight = this.mTitleBar.getTitleLayoutRight(this);
            Intrinsics.checkExpressionValueIsNotNull(titleLayoutRight, "mTitleBar.getTitleLayoutRight(this)");
            titleLayoutRight.setVisibility(0);
            TextView tvNotAddTitle = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvNotAddTitle);
            Intrinsics.checkExpressionValueIsNotNull(tvNotAddTitle, "tvNotAddTitle");
            tvNotAddTitle.setVisibility(ListUtils.INSTANCE.isNullOrEmpty(this.mNotAddedList) ? 8 : 0);
            return;
        }
        LoadingLayout.showEmpty$default((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer), null, 0, 3, null);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.ido.life.customview.OnItemMoveListener
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            int i = fromPosition;
            while (i < toPosition) {
                int i2 = i + 1;
                Collections.swap(this.mList, i, i2);
                i = i2;
            }
        } else {
            int i3 = toPosition + 1;
            if (fromPosition >= i3) {
                int i4 = fromPosition;
                while (true) {
                    Collections.swap(this.mList, i4, i4 - 1);
                    if (i4 == i3) {
                        break;
                    } else {
                        i4--;
                    }
                }
            }
        }
        SportsDataViewDetailListAdapter sportsDataViewDetailListAdapter = this.mAdapter;
        if (sportsDataViewDetailListAdapter != null) {
            sportsDataViewDetailListAdapter.notifyItemMoved(fromPosition, toPosition);
        }
    }

    @Override // com.ido.life.customview.recyclerview.OnStartDragListener
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        DefaultItemTouchHelper defaultItemTouchHelper = this.mItemTouchHelper;
        if (defaultItemTouchHelper != null) {
            defaultItemTouchHelper.onStartDrag(viewHolder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void commit() {
        if (checkDataChanged() && this.dataViewBean != null) {
            showLoadingDialog(true);
            SportsDataViewDetailListPresenter sportsDataViewDetailListPresenter = (SportsDataViewDetailListPresenter) this.mPresenter;
            if (sportsDataViewDetailListPresenter != null) {
                MotionTypeBean motionTypeBean = this.dataViewBean;
                if (motionTypeBean == null) {
                    Intrinsics.throwNpe();
                }
                sportsDataViewDetailListPresenter.setSportSubItemParaSort(motionTypeBean.getType(), this.mList, this.mNotAddedList);
                return;
            }
            return;
        }
        finish();
    }

    private final boolean checkDataChanged() {
        return ListUtils.INSTANCE.isNotEmpty(this.mList) && (Intrinsics.areEqual(this.mList, this.mOriginalList) ^ true);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        LinearLayout titleLayoutRight = this.mTitleBar.getTitleLayoutRight(this);
        Intrinsics.checkExpressionValueIsNotNull(titleLayoutRight, "mTitleBar.getTitleLayoutRight(this)");
        titleLayoutRight.setVisibility(4);
        ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer)).showLoading();
        if (this.dataViewBean != null) {
            SportsDataViewDetailListPresenter sportsDataViewDetailListPresenter = (SportsDataViewDetailListPresenter) this.mPresenter;
            if (sportsDataViewDetailListPresenter != null) {
                MotionTypeBean motionTypeBean = this.dataViewBean;
                if (motionTypeBean == null) {
                    Intrinsics.throwNpe();
                }
                sportsDataViewDetailListPresenter.querySportSubItemParaSort(motionTypeBean.getType());
                return;
            }
            return;
        }
        CommonLogUtil.printAndSave("运动类型数据异常，退出！");
        finish();
    }

    @Override // com.ido.life.module.device.view.ISportsDataViewDetailListView
    public void onGetDataSuccess(List<SportsDataViewDetailBean> list, List<SportsDataViewDetailBean> notAddedList) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Intrinsics.checkParameterIsNotNull(notAddedList, "notAddedList");
        this.mList.clear();
        List<SportsDataViewDetailBean> list2 = list;
        this.mList.addAll(list2);
        this.mOriginalList.clear();
        this.mOriginalList.addAll(list2);
        SportsDataViewDetailListAdapter sportsDataViewDetailListAdapter = this.mAdapter;
        if (sportsDataViewDetailListAdapter != null) {
            sportsDataViewDetailListAdapter.notifyDataSetChanged();
        }
        this.mNotAddedList.clear();
        this.mNotAddedList.addAll(notAddedList);
        updateList();
    }

    @Override // com.ido.life.module.device.view.ISportsDataViewDetailListView
    public void onGetDataFailed() {
        LoadingLayout.showError$default((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer), null, false, 0, 7, null);
        ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer)).setRetryListener(new Function0<Unit>() { // from class: com.ido.life.module.device.activity.SportsDataViewDetailListActivity.onGetDataFailed.1
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
                SportsDataViewDetailListActivity.this.initData();
            }
        });
    }

    @Override // com.ido.life.module.device.view.ISportsDataViewDetailListView
    public void onSetDataSuccess() {
        dismissLoadingDialog();
        this.mOriginalList.clear();
        this.mOriginalList.addAll(this.mList);
        this.mTmpList.clear();
        this.mTmpList.addAll(this.mList);
        this.mNotTmpAddedList.clear();
        this.mNotTmpAddedList.addAll(this.mNotAddedList);
        finish();
    }

    @Override // com.ido.life.module.device.view.ISportsDataViewDetailListView
    public void onSetDataFailed() {
        dismissLoadingDialog();
        DialogUtils dialogUtils = DialogUtils.INSTANCE;
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "supportFragmentManager");
        DialogUtils.showSyncFailedDialog$default(dialogUtils, supportFragmentManager, 0, null, null, new Function0<Unit>() { // from class: com.ido.life.module.device.activity.SportsDataViewDetailListActivity.onSetDataFailed.1
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
                SportsDataViewDetailListActivity.this.commit();
            }
        }, 14, null);
    }
}