package com.ido.life.module.device.activity;

import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.common.utils.DipPixelUtil;
import com.ido.life.adapter.SportsDataViewListAdapter;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.bean.MotionTypeBean;
import com.ido.life.customview.CustomItemDecoration;
import com.ido.life.customview.LoadingLayout;
import com.ido.life.customview.OnItemClickListener;
import com.ido.life.module.device.presenter.SportsDataViewListPresenter;
import com.ido.life.module.device.view.ISportsDataViewListView;
import com.ido.life.util.ListUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SportsDataViewListActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0014J\b\u0010\u0010\u001a\u00020\rH\u0014J\b\u0010\u0011\u001a\u00020\rH\u0014J\b\u0010\u0012\u001a\u00020\rH\u0014J\b\u0010\u0013\u001a\u00020\rH\u0002J\b\u0010\u0014\u001a\u00020\rH\u0016J\b\u0010\u0015\u001a\u00020\rH\u0016J\u0016\u0010\u0016\u001a\u00020\r2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/ido/life/module/device/activity/SportsDataViewListActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/presenter/SportsDataViewListPresenter;", "Lcom/ido/life/module/device/view/ISportsDataViewListView;", "()V", "decoration", "Lcom/ido/life/customview/CustomItemDecoration;", "mAdapter", "Lcom/ido/life/adapter/SportsDataViewListAdapter;", "mList", "", "Lcom/ido/life/bean/MotionTypeBean;", "checkEmpty", "", "getLayoutResId", "", "initData", "initEvent", "initLabelLanguage", "initRecyclerView", "initViews", "onGetDeviceMotionTypesFailed", "onGetDeviceMotionTypesSuccess", "list", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SportsDataViewListActivity extends BaseActivity<SportsDataViewListPresenter> implements ISportsDataViewListView {
    private HashMap _$_findViewCache;
    private CustomItemDecoration decoration;
    private SportsDataViewListAdapter mAdapter;
    private List<MotionTypeBean> mList = new ArrayList();

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
        return R.layout.activity_sports_data_view_list;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.sports_data_view_title));
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        initRecyclerView();
    }

    private final void initRecyclerView() {
        SportsDataViewListActivity sportsDataViewListActivity = this;
        this.mAdapter = new SportsDataViewListAdapter(sportsDataViewListActivity, this.mList);
        RecyclerView recyclerview = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview, "recyclerview");
        recyclerview.setAdapter(this.mAdapter);
        RecyclerView recyclerview2 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview2, "recyclerview");
        this.decoration = new CustomItemDecoration(recyclerview2).color(ContextCompat.getColor(sportsDataViewListActivity, R.color.color_F2F2F6)).marginLeft(DipPixelUtil.dip2pxF(32.0f)).marginRight(DipPixelUtil.dip2pxF(0.0f)).height(DipPixelUtil.dip2pxF(0.5f));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        CustomItemDecoration customItemDecoration = this.decoration;
        if (customItemDecoration == null) {
            Intrinsics.throwNpe();
        }
        recyclerView.addItemDecoration(customItemDecoration);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        SportsDataViewListAdapter sportsDataViewListAdapter = this.mAdapter;
        if (sportsDataViewListAdapter != null) {
            sportsDataViewListAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.ido.life.module.device.activity.SportsDataViewListActivity.initEvent.1
                @Override // com.ido.life.customview.OnItemClickListener
                public void onItemClick(int position) {
                    SportsDataViewListActivity sportsDataViewListActivity = SportsDataViewListActivity.this;
                    SingleTopIntent singleTopIntent = new SingleTopIntent(sportsDataViewListActivity, (Class<?>) SportsDataViewDetailListActivity.class);
                    List list = SportsDataViewListActivity.this.mList;
                    sportsDataViewListActivity.startActivity(singleTopIntent.putExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME, list != null ? (MotionTypeBean) list.get(position) : null));
                }
            });
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer)).showLoading();
        SportsDataViewListPresenter sportsDataViewListPresenter = (SportsDataViewListPresenter) this.mPresenter;
        if (sportsDataViewListPresenter != null) {
            sportsDataViewListPresenter.queryBasicMotionTypes();
        }
    }

    public final void checkEmpty() {
        if (ListUtils.INSTANCE.isNotEmpty(this.mList)) {
            ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer)).showContent();
        } else {
            LoadingLayout.showEmpty$default((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer), null, 0, 3, null);
        }
    }

    @Override // com.ido.life.module.device.view.ISportsDataViewListView
    public void onGetDeviceMotionTypesSuccess(List<MotionTypeBean> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        List<MotionTypeBean> list2 = this.mList;
        if (list2 != null) {
            list2.clear();
        }
        List<MotionTypeBean> list3 = this.mList;
        if (list3 != null) {
            list3.addAll(list);
        }
        SportsDataViewListAdapter sportsDataViewListAdapter = this.mAdapter;
        if (sportsDataViewListAdapter != null) {
            sportsDataViewListAdapter.notifyDataSetChanged();
        }
        checkEmpty();
    }

    @Override // com.ido.life.module.device.view.ISportsDataViewListView
    public void onGetDeviceMotionTypesFailed() {
        LoadingLayout.showError$default((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer), null, false, 0, 7, null);
        ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer)).setRetryListener(new Function0<Unit>() { // from class: com.ido.life.module.device.activity.SportsDataViewListActivity.onGetDeviceMotionTypesFailed.1
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
                SportsDataViewListActivity.this.initData();
            }
        });
    }
}