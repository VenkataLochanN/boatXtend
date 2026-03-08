package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.life.adapter.ShortcutAppListAdapter;
import com.ido.life.base.BaseActivity;
import com.ido.life.bean.ShortcutAppData;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.data.cache.ShortcutAppWrapper;
import com.ido.life.dialog.ShortcutAppSetDialog;
import com.ido.life.module.device.presenter.ShortcutAppListPresenter;
import com.ido.life.module.device.view.IShortcutAppListView;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShortcutAppListActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u0005B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0013\u001a\u00020\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0014J\b\u0010\u0018\u001a\u00020\u0016H\u0014J\b\u0010\u0019\u001a\u00020\u0016H\u0002J\b\u0010\u001a\u001a\u00020\u0016H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0012\u0010\u001d\u001a\u00020\u00162\b\u0010\u001e\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\u001f\u001a\u00020\u0016H\u0016J,\u0010 \u001a\u00020\u00162\"\u0010\u001e\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00100\nj\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u0010`\fH\u0016J\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u0014H\u0016J\b\u0010#\u001a\u00020\u0016H\u0002R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nj\n\u0012\u0004\u0012\u00020\u000b\u0018\u0001`\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010\u000f\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u0010\u0018\u00010\nj\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u0010\u0018\u0001`\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/ido/life/module/device/activity/ShortcutAppListActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/presenter/ShortcutAppListPresenter;", "Lcom/ido/life/module/device/view/IShortcutAppListView;", "Lcom/ido/life/adapter/ShortcutAppListAdapter$OnItemClickListener;", "Lcom/ido/life/dialog/ShortcutAppSetDialog$OnAddClickListener;", "()V", "mAdapter", "Lcom/ido/life/adapter/ShortcutAppListAdapter;", "mAddedShortcutAppData", "Ljava/util/ArrayList;", "Lcom/ido/life/bean/ShortcutAppData;", "Lkotlin/collections/ArrayList;", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "mShortcutAppData", "Lcom/ido/life/data/cache/ShortcutAppWrapper;", "mShortcutAppSetDialog", "Lcom/ido/life/dialog/ShortcutAppSetDialog;", "getLayoutResId", "", "hideLoading", "", "initData", "initLabelLanguage", "initRecyclerView", "initViews", "isStatusBgWhite", "", "onAddClick", "shortcutAppData", "onGetShortcutAppFailed", "onGetShortcutAppSuccess", "onItemClick", "position", "showLoading", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ShortcutAppListActivity extends BaseActivity<ShortcutAppListPresenter> implements IShortcutAppListView, ShortcutAppListAdapter.OnItemClickListener, ShortcutAppSetDialog.OnAddClickListener {
    private HashMap _$_findViewCache;
    private ShortcutAppListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ShortcutAppSetDialog mShortcutAppSetDialog;
    private ArrayList<ShortcutAppData> mAddedShortcutAppData = new ArrayList<>();
    private ArrayList<ShortcutAppWrapper<ShortcutAppData>> mShortcutAppData = new ArrayList<>();

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
        return R.layout.activity_shortcut_app_list;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    public boolean isStatusBgWhite() {
        return false;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        setStatusBarColor(getColor(R.color.black));
        this.mTitleBar.setTitleColor(getColor(R.color.white));
        this.mTitleBar.setBarBackground(R.color.black);
        this.mTitleBar.setLeftImg(R.mipmap.btn_nav_back_dark);
        initRecyclerView();
    }

    private final void initRecyclerView() {
        ShortcutAppListActivity shortcutAppListActivity = this;
        this.mLayoutManager = new LinearLayoutManager(shortcutAppListActivity);
        RecyclerView recyclerview = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview, "recyclerview");
        recyclerview.setLayoutManager(this.mLayoutManager);
        this.mAdapter = new ShortcutAppListAdapter(shortcutAppListActivity, this.mShortcutAppData);
        RecyclerView recyclerview2 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview2, "recyclerview");
        recyclerview2.setAdapter(this.mAdapter);
        ShortcutAppListAdapter shortcutAppListAdapter = this.mAdapter;
        if (shortcutAppListAdapter != null) {
            shortcutAppListAdapter.setOnItemClickListener(this);
        }
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.shortcut_app_add_app_title));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        showLoading();
        this.mAddedShortcutAppData = (ArrayList) getIntent().getSerializableExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME);
        ((ShortcutAppListPresenter) this.mPresenter).getShortcutAppItemList();
    }

    private final void showLoading() {
        CommLoadingView comm_loading_view = (CommLoadingView) _$_findCachedViewById(com.ido.life.R.id.comm_loading_view);
        Intrinsics.checkExpressionValueIsNotNull(comm_loading_view, "comm_loading_view");
        comm_loading_view.setVisibility(0);
    }

    private final void hideLoading() {
        CommLoadingView comm_loading_view = (CommLoadingView) _$_findCachedViewById(com.ido.life.R.id.comm_loading_view);
        Intrinsics.checkExpressionValueIsNotNull(comm_loading_view, "comm_loading_view");
        comm_loading_view.setVisibility(8);
    }

    @Override // com.ido.life.module.device.view.IShortcutAppListView
    public void onGetShortcutAppSuccess(ArrayList<ShortcutAppWrapper<ShortcutAppData>> shortcutAppData) {
        Intrinsics.checkParameterIsNotNull(shortcutAppData, "shortcutAppData");
        hideLoading();
        LinearLayout layout_content = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_content);
        Intrinsics.checkExpressionValueIsNotNull(layout_content, "layout_content");
        layout_content.setVisibility(0);
        ArrayList<ShortcutAppWrapper<ShortcutAppData>> arrayList = this.mShortcutAppData;
        if (arrayList != null) {
            arrayList.clear();
        }
        ArrayList<ShortcutAppWrapper<ShortcutAppData>> arrayList2 = this.mShortcutAppData;
        if (arrayList2 != null) {
            arrayList2.addAll(shortcutAppData);
        }
        ShortcutAppListAdapter shortcutAppListAdapter = this.mAdapter;
        if (shortcutAppListAdapter != null) {
            shortcutAppListAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.ido.life.module.device.view.IShortcutAppListView
    public void onGetShortcutAppFailed() {
        hideLoading();
    }

    @Override // com.ido.life.adapter.ShortcutAppListAdapter.OnItemClickListener
    public void onItemClick(int position) {
        ShortcutAppSetDialog shortcutAppSetDialog;
        ShortcutAppWrapper<ShortcutAppData> shortcutAppWrapper;
        ArrayList<ShortcutAppWrapper<ShortcutAppData>> arrayList = this.mShortcutAppData;
        ShortcutAppData data = (arrayList == null || (shortcutAppWrapper = arrayList.get(position)) == null) ? null : shortcutAppWrapper.getData();
        if (data != null) {
            int size_type = 0;
            ArrayList<ShortcutAppData> arrayList2 = this.mAddedShortcutAppData;
            if (arrayList2 != null) {
                if (arrayList2 == null) {
                    Intrinsics.throwNpe();
                }
                if (arrayList2.size() > 0) {
                    ArrayList<ShortcutAppData> arrayList3 = this.mAddedShortcutAppData;
                    if (arrayList3 == null) {
                        Intrinsics.throwNpe();
                    }
                    int iIndexOf = arrayList3.indexOf(data);
                    if (iIndexOf >= 0) {
                        ArrayList<ShortcutAppData> arrayList4 = this.mAddedShortcutAppData;
                        if (arrayList4 == null) {
                            Intrinsics.throwNpe();
                        }
                        size_type = arrayList4.get(iIndexOf).getSize_type();
                    }
                }
            }
            this.mShortcutAppSetDialog = ShortcutAppSetDialog.INSTANCE.newInstance(size_type, data);
            ShortcutAppSetDialog shortcutAppSetDialog2 = this.mShortcutAppSetDialog;
            if (shortcutAppSetDialog2 != null) {
                shortcutAppSetDialog2.setOnAddClickListener(this);
            }
            ShortcutAppSetDialog shortcutAppSetDialog3 = this.mShortcutAppSetDialog;
            if (shortcutAppSetDialog3 == null || shortcutAppSetDialog3.isDialogShowing() || (shortcutAppSetDialog = this.mShortcutAppSetDialog) == null) {
                return;
            }
            shortcutAppSetDialog.show(getSupportFragmentManager());
        }
    }

    @Override // com.ido.life.dialog.ShortcutAppSetDialog.OnAddClickListener
    public void onAddClick(ShortcutAppData shortcutAppData) {
        ShortcutAppSetDialog shortcutAppSetDialog;
        ShortcutAppSetDialog shortcutAppSetDialog2 = this.mShortcutAppSetDialog;
        if (shortcutAppSetDialog2 != null && shortcutAppSetDialog2.isDialogShowing() && (shortcutAppSetDialog = this.mShortcutAppSetDialog) != null) {
            shortcutAppSetDialog.dismiss();
        }
        if (shortcutAppData != null) {
            setResult(-1, new Intent(this, (Class<?>) ShortcutEditActivity.class).putExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME, shortcutAppData));
        }
        finish();
    }
}