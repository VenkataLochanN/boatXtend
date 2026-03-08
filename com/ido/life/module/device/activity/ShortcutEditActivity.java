package com.ido.life.module.device.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.adapter.ShortcutAppEditAdapter;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.bean.ShortcutAppData;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.module.device.presenter.ShortcutAppEditPresenter;
import com.ido.life.module.device.view.IShortcutAppEditView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShortcutEditActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u0000 32\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u0005:\u00013B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0015\u001a\u00020\bH\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0014J\b\u0010\u001b\u001a\u00020\u0019H\u0014J\b\u0010\u001c\u001a\u00020\u0019H\u0014J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\b\u0010\u001e\u001a\u00020\u0019H\u0016J\"\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u00172\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\b\u0010$\u001a\u00020\u0019H\u0016J\b\u0010%\u001a\u00020\u0019H\u0016J\u0016\u0010&\u001a\u00020\u00192\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016J\u0010\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u0017H\u0016J\u0010\u0010*\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u0017H\u0016J\b\u0010+\u001a\u00020\u0019H\u0016J\b\u0010,\u001a\u00020\u0019H\u0016J\b\u0010-\u001a\u00020\u0019H\u0002J\b\u0010.\u001a\u00020\u0019H\u0014J\b\u0010/\u001a\u00020\u0019H\u0002J\b\u00100\u001a\u00020\u0019H\u0002J \u00101\u001a\u00020\b*\b\u0012\u0004\u0012\u00020\u00130\u00122\u000e\u00102\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/ido/life/module/device/activity/ShortcutEditActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/presenter/ShortcutAppEditPresenter;", "Lcom/ido/life/module/device/view/IShortcutAppEditView;", "Lcom/ido/life/adapter/ShortcutAppEditAdapter$OnItemLongClickListener;", "Lcom/ido/life/adapter/ShortcutAppEditAdapter$OnItemDeleteClickListener;", "()V", "isEdit", "", "mAdapter", "Lcom/ido/life/adapter/ShortcutAppEditAdapter;", "mItemTouchHelper", "Landroidx/recyclerview/widget/ItemTouchHelper;", "mLayoutManager", "Landroidx/recyclerview/widget/GridLayoutManager;", "mOriginalShortcutAppData", "", "mShortcutAppData", "Ljava/util/ArrayList;", "Lcom/ido/life/bean/ShortcutAppData;", "mTmpShortcutAppData", "checkDataChanged", "getLayoutResId", "", "hideLoading", "", "initData", "initEvent", "initLabelLanguage", "initRecyclerView", "initViews", "onActivityResult", "requestCode", "resultCode", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Landroid/content/Intent;", "onBackPressed", "onGetEditedShortcutAppFailed", "onGetEditedShortcutAppSuccess", "shortcutAppData", "onItemDeleteClick", "position", "onItemLongClick", "onSetShortcutAppFailed", "onSetShortcutAppSuccess", "restoreData", "sendCmd", "showLoading", "switchEditStatus", "same", "list", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ShortcutEditActivity extends BaseActivity<ShortcutAppEditPresenter> implements IShortcutAppEditView, ShortcutAppEditAdapter.OnItemLongClickListener, ShortcutAppEditAdapter.OnItemDeleteClickListener {
    public static final int BIG_SPAN = 2;
    public static final int REQUEST_CODE_ADD = 1000;
    public static final int SMALL_SPAN = 1;
    private HashMap _$_findViewCache;
    private boolean isEdit;
    private ShortcutAppEditAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private GridLayoutManager mLayoutManager;
    private String mOriginalShortcutAppData = "";
    private ArrayList<ShortcutAppData> mShortcutAppData = new ArrayList<>();
    private ArrayList<ShortcutAppData> mTmpShortcutAppData = new ArrayList<>();

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
        return R.layout.activity_shortcut_edit;
    }

    public final boolean same(ArrayList<ShortcutAppData> same, ArrayList<ShortcutAppData> arrayList) {
        Intrinsics.checkParameterIsNotNull(same, "$this$same");
        if (arrayList == null) {
            return false;
        }
        int size = same.size();
        for (int i = 0; i < size; i++) {
            if (same.get(i).getSize_type() != arrayList.get(i).getSize_type()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mTitleBar.initLayout(4).setRightText(getLanguageText(R.string.public_edit)).setRightTextColor(ResourceUtil.getColor(R.color.color_0989FF)).setLeftText(getLanguageText(R.string.public_cancel)).setLeftImg(R.mipmap.btn_nav_back_dark).setLeftTextColor(ResourceUtil.getColor(R.color.color_0989FF)).switchLeftImgAndTextVisibility(true).setLeftTextOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.ShortcutEditActivity.initViews.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShortcutEditActivity.this.restoreData();
            }
        }).setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.ShortcutEditActivity.initViews.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (ShortcutEditActivity.this.isEdit) {
                    ShortcutEditActivity.this.saveData();
                } else {
                    ShortcutEditActivity.this.switchEditStatus();
                }
            }
        });
        initRecyclerView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void restoreData() {
        switchEditStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void switchEditStatus() {
        this.isEdit = !this.isEdit;
        this.mTitleBar.setRightText(getLanguageText(this.isEdit ? R.string.public_complete : R.string.public_edit));
        TextView tvAdd = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvAdd);
        Intrinsics.checkExpressionValueIsNotNull(tvAdd, "tvAdd");
        tvAdd.setVisibility(this.isEdit ? 0 : 8);
        ShortcutAppEditAdapter shortcutAppEditAdapter = this.mAdapter;
        if (shortcutAppEditAdapter != null) {
            shortcutAppEditAdapter.setEdit(this.isEdit);
        }
        ItemTouchHelper itemTouchHelper = this.mItemTouchHelper;
        if (itemTouchHelper != null) {
            itemTouchHelper.attachToRecyclerView(this.isEdit ? (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview) : null);
        }
        this.mTitleBar.switchLeftImgAndTextVisibility(!this.isEdit);
        if (this.isEdit) {
            this.mTmpShortcutAppData.clear();
            this.mTmpShortcutAppData.addAll(this.mShortcutAppData);
            return;
        }
        this.mShortcutAppData.clear();
        this.mShortcutAppData.addAll(this.mTmpShortcutAppData);
        ShortcutAppEditAdapter shortcutAppEditAdapter2 = this.mAdapter;
        if (shortcutAppEditAdapter2 != null) {
            shortcutAppEditAdapter2.notifyDataSetChanged();
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tvAdd)).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.ShortcutEditActivity.initEvent.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShortcutEditActivity shortcutEditActivity = ShortcutEditActivity.this;
                shortcutEditActivity.startActivityForResult(new SingleTopIntent(shortcutEditActivity, (Class<?>) ShortcutAppListActivity.class).putExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME, ShortcutEditActivity.this.mShortcutAppData), 1000);
            }
        });
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.mini_widget));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        showLoading();
        ((ShortcutAppEditPresenter) this.mPresenter).getEditedShortcutAppItemList();
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

    private final void initRecyclerView() {
        ShortcutEditActivity shortcutEditActivity = this;
        this.mLayoutManager = new GridLayoutManager((Context) shortcutEditActivity, 2, 1, false);
        GridLayoutManager gridLayoutManager = this.mLayoutManager;
        if (gridLayoutManager != null) {
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.ido.life.module.device.activity.ShortcutEditActivity.initRecyclerView.1
                @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int position) {
                    return ((ShortcutAppData) ShortcutEditActivity.this.mShortcutAppData.get(position)).getSize_type() == 1 ? 2 : 1;
                }
            });
        }
        RecyclerView recyclerview = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview, "recyclerview");
        recyclerview.setLayoutManager(this.mLayoutManager);
        this.mAdapter = new ShortcutAppEditAdapter(shortcutEditActivity, this.mShortcutAppData);
        RecyclerView recyclerview2 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview2, "recyclerview");
        recyclerview2.setAdapter(this.mAdapter);
        ShortcutAppEditAdapter shortcutAppEditAdapter = this.mAdapter;
        if (shortcutAppEditAdapter != null) {
            shortcutAppEditAdapter.setOnItemDeleteClickListener(this);
        }
        ShortcutAppEditAdapter shortcutAppEditAdapter2 = this.mAdapter;
        if (shortcutAppEditAdapter2 != null) {
            shortcutAppEditAdapter2.setOnItemLongClickListener(this);
        }
        this.mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() { // from class: com.ido.life.module.device.activity.ShortcutEditActivity.initRecyclerView.2
            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
                Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
                return ItemTouchHelper.Callback.makeMovementFlags(recyclerView.getLayoutManager() instanceof GridLayoutManager ? 15 : 3, 0);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
                Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
                Intrinsics.checkParameterIsNotNull(target, "target");
                int adapterPosition = viewHolder.getAdapterPosition();
                int adapterPosition2 = target.getAdapterPosition();
                if (adapterPosition < adapterPosition2) {
                    int i = adapterPosition;
                    while (i < adapterPosition2) {
                        int i2 = i + 1;
                        Collections.swap(ShortcutEditActivity.this.mShortcutAppData, i, i2);
                        i = i2;
                    }
                } else {
                    int i3 = adapterPosition2 + 1;
                    if (adapterPosition >= i3) {
                        int i4 = adapterPosition;
                        while (true) {
                            Collections.swap(ShortcutEditActivity.this.mShortcutAppData, i4, i4 - 1);
                            if (i4 == i3) {
                                break;
                            }
                            i4--;
                        }
                    }
                }
                ShortcutAppEditAdapter shortcutAppEditAdapter3 = ShortcutEditActivity.this.mAdapter;
                if (shortcutAppEditAdapter3 == null) {
                    return true;
                }
                shortcutAppEditAdapter3.notifyItemMoved(adapterPosition, adapterPosition2);
                return true;
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ShortcutAppData shortcutAppData;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1 || data == null || requestCode != 1000 || (shortcutAppData = (ShortcutAppData) data.getSerializableExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME)) == null) {
            return;
        }
        int iIndexOf = this.mShortcutAppData.indexOf(shortcutAppData);
        if (iIndexOf >= 0 && shortcutAppData.getSize_type() != this.mShortcutAppData.get(iIndexOf).getSize_type()) {
            this.mShortcutAppData.get(iIndexOf).setSize_type(shortcutAppData.getSize_type());
            ShortcutAppEditAdapter shortcutAppEditAdapter = this.mAdapter;
            if (shortcutAppEditAdapter != null) {
                shortcutAppEditAdapter.notifyItemChanged(iIndexOf);
                return;
            }
            return;
        }
        this.mShortcutAppData.add(shortcutAppData);
        ShortcutAppEditAdapter shortcutAppEditAdapter2 = this.mAdapter;
        if (shortcutAppEditAdapter2 != null) {
            shortcutAppEditAdapter2.notifyDataSetChanged();
        }
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        if (this.mShortcutAppData == null) {
            Intrinsics.throwNpe();
        }
        recyclerView.scrollToPosition(r3.size() - 1);
    }

    @Override // com.ido.life.adapter.ShortcutAppEditAdapter.OnItemLongClickListener
    public void onItemLongClick(int position) {
        switchEditStatus();
    }

    @Override // com.ido.life.adapter.ShortcutAppEditAdapter.OnItemDeleteClickListener
    public void onItemDeleteClick(int position) {
        if (position < 0) {
            return;
        }
        this.mShortcutAppData.remove(position);
        if (this.mShortcutAppData.size() == 1) {
            ShortcutAppEditAdapter shortcutAppEditAdapter = this.mAdapter;
            if (shortcutAppEditAdapter != null) {
                shortcutAppEditAdapter.notifyDataSetChanged();
                return;
            }
            return;
        }
        ShortcutAppEditAdapter shortcutAppEditAdapter2 = this.mAdapter;
        if (shortcutAppEditAdapter2 != null) {
            shortcutAppEditAdapter2.notifyItemRemoved(position);
        }
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        if (checkDataChanged()) {
            showLoadingDialog(null, false, getResources().getDrawable(R.drawable.shape_progress_bg_white));
            ShortcutAppEditPresenter shortcutAppEditPresenter = (ShortcutAppEditPresenter) this.mPresenter;
            if (shortcutAppEditPresenter != null) {
                ArrayList<ShortcutAppData> arrayList = this.mShortcutAppData;
                if (arrayList == null) {
                    Intrinsics.throwNpe();
                }
                shortcutAppEditPresenter.setShortcutApps(arrayList);
                return;
            }
            return;
        }
        switchEditStatus();
    }

    private final boolean checkDataChanged() {
        return !Intrinsics.areEqual(this.mShortcutAppData.toString(), this.mOriginalShortcutAppData);
    }

    @Override // com.ido.life.module.device.view.IShortcutAppEditView
    public void onGetEditedShortcutAppSuccess(ArrayList<ShortcutAppData> shortcutAppData) {
        Intrinsics.checkParameterIsNotNull(shortcutAppData, "shortcutAppData");
        hideLoading();
        LinearLayout layout_content = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_content);
        Intrinsics.checkExpressionValueIsNotNull(layout_content, "layout_content");
        layout_content.setVisibility(0);
        String string = shortcutAppData.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "shortcutAppData.toString()");
        this.mOriginalShortcutAppData = string;
        this.mShortcutAppData.clear();
        this.mShortcutAppData.addAll(shortcutAppData);
        ShortcutAppEditAdapter shortcutAppEditAdapter = this.mAdapter;
        if (shortcutAppEditAdapter != null) {
            shortcutAppEditAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.ido.life.module.device.view.IShortcutAppEditView
    public void onGetEditedShortcutAppFailed() {
        hideLoading();
        LinearLayout layout_content = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_content);
        Intrinsics.checkExpressionValueIsNotNull(layout_content, "layout_content");
        layout_content.setVisibility(8);
        LinearLayout layout_load_failed = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_load_failed);
        Intrinsics.checkExpressionValueIsNotNull(layout_load_failed, "layout_load_failed");
        layout_load_failed.setVisibility(0);
    }

    @Override // com.ido.life.module.device.view.IShortcutAppEditView
    public void onSetShortcutAppSuccess() {
        dismissLoadingDialog();
        String string = this.mShortcutAppData.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "mShortcutAppData.toString()");
        this.mOriginalShortcutAppData = string;
        this.mTmpShortcutAppData.clear();
        this.mTmpShortcutAppData.addAll(this.mShortcutAppData);
        switchEditStatus();
    }

    @Override // com.ido.life.module.device.view.IShortcutAppEditView
    public void onSetShortcutAppFailed() {
        dismissLoadingDialog();
        showToast(getLanguageText(R.string.public_set_failed));
    }
}