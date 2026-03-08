package com.ido.life.module.device.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.utils.DipPixelUtil;
import com.ido.life.adapter.MydialEditListAdapter;
import com.ido.life.adapter.ShortcutAppEditAdapter;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.CustomItemDecoration;
import com.ido.life.customview.LoadingLayout;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.OnItemClickListener;
import com.ido.life.customview.OnItemMoveListener;
import com.ido.life.customview.recyclerview.DefaultItemTouchHelper;
import com.ido.life.customview.recyclerview.DefaultItemTouchHelperCallback;
import com.ido.life.customview.recyclerview.OnStartDragListener;
import com.ido.life.data.api.entity.DialMarket;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.module.device.presenter.BaseDialPresenter;
import com.ido.life.module.device.presenter.MydialEditPresenter;
import com.ido.life.module.device.view.IMyDialView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MyDialEditActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u0007B\u0005¢\u0006\u0002\u0010\bJ\b\u0010+\u001a\u00020\rH\u0014J\b\u0010,\u001a\u00020-H\u0014J\b\u0010.\u001a\u00020-H\u0002J\b\u0010/\u001a\u00020-H\u0016J\b\u00100\u001a\u00020\u000fH\u0002J\u0010\u00101\u001a\u00020-2\u0006\u00102\u001a\u000203H\u0016J\u0010\u00104\u001a\u00020-2\u0006\u00105\u001a\u00020\u000fH\u0016J\u0010\u00106\u001a\u00020-2\u0006\u00107\u001a\u00020\u000fH\u0016J\u0010\u00108\u001a\u00020-2\u0006\u00109\u001a\u00020\rH\u0016J\u0012\u0010:\u001a\u00020-2\b\u0010;\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010<\u001a\u00020-H\u0016J\u0012\u0010=\u001a\u00020-2\b\u0010>\u001a\u0004\u0018\u00010?H\u0016J\u0016\u0010@\u001a\u00020-2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\n0\u0016H\u0016J\u0018\u0010B\u001a\u00020-2\u000e\u0010A\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0016H\u0016J\b\u0010C\u001a\u00020-H\u0016J\u0018\u0010D\u001a\u00020-2\u000e\u0010E\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016H\u0016J\u0010\u0010F\u001a\u00020-2\u0006\u00109\u001a\u00020\rH\u0016J\u0012\u0010G\u001a\u00020-2\b\u0010H\u001a\u0004\u0018\u00010IH\u0016J\u0010\u0010J\u001a\u00020-2\u0006\u0010K\u001a\u00020\rH\u0016J\u0018\u0010L\u001a\u00020-2\u0006\u0010M\u001a\u00020\r2\u0006\u0010N\u001a\u00020\rH\u0016J\u0012\u0010O\u001a\u00020-2\b\u0010P\u001a\u0004\u0018\u00010QH\u0016J\u0010\u0010)\u001a\u00020-2\u0006\u0010R\u001a\u00020\nH\u0002J\u0010\u0010S\u001a\u00020-2\u0006\u0010K\u001a\u00020\rH\u0002J\b\u0010T\u001a\u00020-H\u0016J\b\u0010U\u001a\u00020-H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010&\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*¨\u0006V"}, d2 = {"Lcom/ido/life/module/device/activity/MyDialEditActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/presenter/MydialEditPresenter;", "Landroid/view/View$OnClickListener;", "Lcom/ido/life/customview/OnItemMoveListener;", "Lcom/ido/life/customview/recyclerview/OnStartDragListener;", "Lcom/ido/life/adapter/ShortcutAppEditAdapter$OnItemDeleteClickListener;", "Lcom/ido/life/module/device/view/IMyDialView;", "()V", "TAG", "", "currentDialName", "index", "", "isDelete", "", "isEdit", "mDialAdapter", "Lcom/ido/life/adapter/MydialEditListAdapter;", "mDialDeleteDialog", "Lcom/ido/common/dialog/CommBottomConfirmDialog;", "mDialInfoList", "", "Lcom/ido/life/data/api/entity/MyDialListEntity$DialInfo;", "mInstalledDialList", "mInstalledV3DialList", "Lcom/ido/ble/watch/custom/model/DialPlateParam$PlateFileInfo;", "getMInstalledV3DialList", "()Ljava/util/List;", "setMInstalledV3DialList", "(Ljava/util/List;)V", "mItemTouchHelper", "Lcom/ido/life/customview/recyclerview/DefaultItemTouchHelper;", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "mMyDialDeleteList", "mMyDialList", "mTotalCount", "wallPaperDial", "getWallPaperDial", "()Lcom/ido/life/data/api/entity/MyDialListEntity$DialInfo;", "setWallPaperDial", "(Lcom/ido/life/data/api/entity/MyDialListEntity$DialInfo;)V", "getLayoutResId", "initEvent", "", "initRecyclerView", "initViews", "isSupportDialSort", "onClick", "view", "Landroid/view/View;", "onDeleteDial", "succeed", "onDialOrder", "succees", "onGetCollectDialNum", "number", "onGetCurrentDial", "dialName", "onGetDialInfoFailed", "onGetDialInfoFromName", "dial", "Lcom/ido/life/data/api/entity/DialMarket$DialType$Dial;", "onGetInstalledDialList", "list", "onGetInstalledDialListV3", "onGetMyDialFailed", "onGetMyDialList", "dialInfoList", "onGetMyDialNum", "onGetWallpaperDialInfo", "responseInfo", "Lcom/ido/ble/watch/custom/model/PhotoWallpaperOperation$ResponseInfo;", "onItemDeleteClick", "position", "onItemMove", "fromPosition", "toPosition", "onStartDrag", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", AppMeasurementSdk.ConditionalUserProperty.NAME, "showDeleteDialog", "startLoading", "submmit", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MyDialEditActivity extends BaseActivity<MydialEditPresenter> implements View.OnClickListener, OnItemMoveListener, OnStartDragListener, ShortcutAppEditAdapter.OnItemDeleteClickListener, IMyDialView {
    private HashMap _$_findViewCache;
    private String currentDialName;
    private int index;
    private boolean isDelete;
    private boolean isEdit;
    private MydialEditListAdapter mDialAdapter;
    private CommBottomConfirmDialog mDialDeleteDialog;
    private DefaultItemTouchHelper mItemTouchHelper;
    private LinearLayoutManager mLayoutManager;
    private int mTotalCount;
    private MyDialListEntity.DialInfo wallPaperDial;
    private String TAG = "MyDialEditActivity";
    private List<MyDialListEntity.DialInfo> mMyDialList = new ArrayList();
    private List<MyDialListEntity.DialInfo> mMyDialDeleteList = new ArrayList();
    private final List<String> mInstalledDialList = new ArrayList();
    private List<DialPlateParam.PlateFileInfo> mInstalledV3DialList = new ArrayList();
    private final List<MyDialListEntity.DialInfo> mDialInfoList = new ArrayList();

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
        return R.layout.activity_mydial_edit;
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onGetCollectDialNum(int number) {
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onGetDialInfoFailed() {
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onGetDialInfoFromName(DialMarket.DialType.Dial dial) {
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onGetMyDialFailed() {
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onGetMyDialNum(int number) {
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onGetWallpaperDialInfo(PhotoWallpaperOperation.ResponseInfo responseInfo) {
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void startLoading() {
    }

    public static final /* synthetic */ MydialEditPresenter access$getMPresenter$p(MyDialEditActivity myDialEditActivity) {
        return (MydialEditPresenter) myDialEditActivity.mPresenter;
    }

    public final MyDialListEntity.DialInfo getWallPaperDial() {
        return this.wallPaperDial;
    }

    public final void setWallPaperDial(MyDialListEntity.DialInfo dialInfo) {
        this.wallPaperDial = dialInfo;
    }

    public final List<DialPlateParam.PlateFileInfo> getMInstalledV3DialList() {
        return this.mInstalledV3DialList;
    }

    public final void setMInstalledV3DialList(List<DialPlateParam.PlateFileInfo> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.mInstalledV3DialList = list;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        ((MydialEditPresenter) this.mPresenter).getInstalledDialInfo();
        BLEManager.getCurrentWatchPlate();
        ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.mydial_llContainer)).showLoading();
        TextView tvRight = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvRight);
        Intrinsics.checkExpressionValueIsNotNull(tvRight, "tvRight");
        tvRight.setVisibility(0);
        initRecyclerView();
    }

    private final void initRecyclerView() {
        P mPresenter = this.mPresenter;
        Intrinsics.checkExpressionValueIsNotNull(mPresenter, "mPresenter");
        float dialImageAspectRatio = ((MydialEditPresenter) mPresenter).getDialImageAspectRatio();
        P mPresenter2 = this.mPresenter;
        Intrinsics.checkExpressionValueIsNotNull(mPresenter2, "mPresenter");
        boolean zIsBracelet = ((MydialEditPresenter) mPresenter2).isBracelet();
        boolean z = 1 == ((MydialEditPresenter) this.mPresenter).getDeviceShape();
        MyDialEditActivity myDialEditActivity = this;
        this.mLayoutManager = new LinearLayoutManager(myDialEditActivity);
        RecyclerView recyclerview = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview, "recyclerview");
        recyclerview.setLayoutManager(this.mLayoutManager);
        this.mDialAdapter = new MydialEditListAdapter(this, this.mMyDialList, dialImageAspectRatio, zIsBracelet, z);
        RecyclerView recyclerview2 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview2, "recyclerview");
        ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview)).addItemDecoration(new CustomItemDecoration(recyclerview2).color(ContextCompat.getColor(myDialEditActivity, R.color.color_F2F2F6)).marginLeft(DipPixelUtil.dip2pxF(16.0f)).marginRight(DipPixelUtil.dip2pxF(0.0f)).height(DipPixelUtil.dip2pxF(0.5f)));
        this.mItemTouchHelper = new DefaultItemTouchHelper(new DefaultItemTouchHelperCallback(this.mDialAdapter));
        DefaultItemTouchHelper defaultItemTouchHelper = this.mItemTouchHelper;
        if (defaultItemTouchHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mItemTouchHelper");
        }
        defaultItemTouchHelper.setSwipeEnable(false);
        DefaultItemTouchHelper defaultItemTouchHelper2 = this.mItemTouchHelper;
        if (defaultItemTouchHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mItemTouchHelper");
        }
        defaultItemTouchHelper2.setDragEnable(true);
        DefaultItemTouchHelper defaultItemTouchHelper3 = this.mItemTouchHelper;
        if (defaultItemTouchHelper3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mItemTouchHelper");
        }
        defaultItemTouchHelper3.attachToRecyclerView((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview));
        RecyclerView recyclerview3 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview3, "recyclerview");
        recyclerview3.setAdapter(this.mDialAdapter);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        MyDialEditActivity myDialEditActivity = this;
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tvRight)).setOnClickListener(myDialEditActivity);
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tvLeft)).setOnClickListener(myDialEditActivity);
        MydialEditListAdapter mydialEditListAdapter = this.mDialAdapter;
        if (mydialEditListAdapter != null) {
            mydialEditListAdapter.setOnItemMoveListener(this);
        }
        MydialEditListAdapter mydialEditListAdapter2 = this.mDialAdapter;
        if (mydialEditListAdapter2 != null) {
            mydialEditListAdapter2.setOnStartDragListener(this);
        }
        MydialEditListAdapter mydialEditListAdapter3 = this.mDialAdapter;
        if (mydialEditListAdapter3 != null) {
            mydialEditListAdapter3.setEdit(this.isEdit);
        }
        MydialEditListAdapter mydialEditListAdapter4 = this.mDialAdapter;
        if (mydialEditListAdapter4 != null) {
            mydialEditListAdapter4.setOnDeleteClickListener(new OnItemClickListener() { // from class: com.ido.life.module.device.activity.MyDialEditActivity.initEvent.1
                @Override // com.ido.life.customview.OnItemClickListener
                public void onItemClick(int position) {
                    if (MyDialEditActivity.this.mTotalCount > 1) {
                        MyDialEditActivity.this.showDeleteDialog(position);
                    } else {
                        NormalToast.showToast(MyDialEditActivity.this.getString(R.string.dial_limit));
                    }
                }
            });
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == R.id.tvLeft) {
            onBackPressed();
        } else {
            if (id != R.id.tvRight) {
                return;
            }
            if (isSupportDialSort()) {
                submmit();
            } else {
                onBackPressed();
            }
        }
    }

    private final void submmit() {
        ((MydialEditPresenter) this.mPresenter).setDialOrder(this.mMyDialList);
    }

    @Override // com.ido.life.customview.OnItemMoveListener
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            int i = fromPosition;
            while (i < toPosition) {
                int i2 = i + 1;
                Collections.swap(this.mMyDialList, i, i2);
                i = i2;
            }
        } else {
            int i3 = toPosition + 1;
            if (fromPosition >= i3) {
                int i4 = fromPosition;
                while (true) {
                    Collections.swap(this.mMyDialList, i4, i4 - 1);
                    if (i4 == i3) {
                        break;
                    } else {
                        i4--;
                    }
                }
            }
        }
        MydialEditListAdapter mydialEditListAdapter = this.mDialAdapter;
        if (mydialEditListAdapter != null) {
            mydialEditListAdapter.notifyItemMoved(fromPosition, toPosition);
        }
    }

    @Override // com.ido.life.customview.recyclerview.OnStartDragListener
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        DefaultItemTouchHelper defaultItemTouchHelper = this.mItemTouchHelper;
        if (defaultItemTouchHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mItemTouchHelper");
        }
        if (defaultItemTouchHelper != null) {
            defaultItemTouchHelper.onStartDrag(viewHolder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDeleteDialog(final int position) {
        P mPresenter = this.mPresenter;
        Intrinsics.checkExpressionValueIsNotNull(mPresenter, "mPresenter");
        if (!((MydialEditPresenter) mPresenter).isConnected()) {
            showToast(R.string.device_pls_connect_device);
            return;
        }
        this.mDialDeleteDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.device_delete_dial), getLanguageText(R.string.dial_delete_tip), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.MyDialEditActivity.showDeleteDialog.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyDialListEntity.DialInfo dialInfo;
                List list = MyDialEditActivity.this.mMyDialList;
                String name = (list == null || (dialInfo = (MyDialListEntity.DialInfo) list.get(position)) == null) ? null : dialInfo.getOtaFaceName();
                if (TextUtils.isEmpty(name)) {
                    MyDialEditActivity.access$getMPresenter$p(MyDialEditActivity.this).saveDialLog("表盘名称为空，不能删除！");
                    return;
                }
                MyDialEditActivity myDialEditActivity = MyDialEditActivity.this;
                myDialEditActivity.showLoadingDialog(myDialEditActivity.getString(R.string.device_deleting));
                MyDialEditActivity.this.isDelete = true;
                MydialEditPresenter mydialEditPresenterAccess$getMPresenter$p = MyDialEditActivity.access$getMPresenter$p(MyDialEditActivity.this);
                Intrinsics.checkExpressionValueIsNotNull(name, "name");
                mydialEditPresenterAccess$getMPresenter$p.deleteDial(name);
                MyDialEditActivity.this.index = position;
            }
        });
        CommBottomConfirmDialog commBottomConfirmDialog = this.mDialDeleteDialog;
        if (commBottomConfirmDialog != null) {
            commBottomConfirmDialog.show(getSupportFragmentManager());
        }
    }

    @Override // com.ido.life.adapter.ShortcutAppEditAdapter.OnItemDeleteClickListener
    public void onItemDeleteClick(int position) {
        showDeleteDialog(position);
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onDeleteDial(boolean succeed) {
        dismissLoadingDialog();
        if (succeed) {
            List<MyDialListEntity.DialInfo> list = this.mMyDialList;
            if (list != null) {
                list.remove(this.index);
            }
            MydialEditListAdapter mydialEditListAdapter = this.mDialAdapter;
            if (mydialEditListAdapter != null) {
                mydialEditListAdapter.notifyItemRemoved(this.index);
            }
            MydialEditListAdapter mydialEditListAdapter2 = this.mDialAdapter;
            if (mydialEditListAdapter2 != null) {
                mydialEditListAdapter2.notifyItemChanged(this.index);
            }
            this.mTotalCount--;
            MydialEditListAdapter mydialEditListAdapter3 = this.mDialAdapter;
            if (mydialEditListAdapter3 != null) {
                mydialEditListAdapter3.setDeletable(this.mTotalCount > 1);
            }
            ((TextView) _$_findCachedViewById(com.ido.life.R.id.tvTitle)).setText(getString(R.string.my_dial) + "(" + this.mMyDialList.size() + ")");
            NormalToast.showToast(getString(R.string.device_delete_success));
            return;
        }
        NormalToast.showToast(getString(R.string.device_delete_failed));
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onDialOrder(boolean succees) {
        if (succees) {
            NormalToast.showToast(getString(R.string.setting_success));
            finish();
        } else {
            NormalToast.showToast(getString(R.string.setting_failed));
        }
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetCurrentDial(String dialName) {
        if (this.isDelete) {
            return;
        }
        this.currentDialName = dialName;
        MydialEditListAdapter mydialEditListAdapter = this.mDialAdapter;
        if (mydialEditListAdapter != null) {
            mydialEditListAdapter.updateCurrentDial(this.currentDialName);
        }
    }

    private final void setWallPaperDial(String name) {
        this.wallPaperDial = ((MydialEditPresenter) this.mPresenter).getWallPaperDialNew(name);
        MyDialListEntity.DialInfo dialInfo = this.wallPaperDial;
        if (dialInfo != null) {
            if (dialInfo != null) {
                dialInfo.isInstalledDial = true;
            }
            MyDialListEntity.DialInfo dialInfo2 = this.wallPaperDial;
            if (dialInfo2 != null) {
                dialInfo2.setImageUrl(((MydialEditPresenter) this.mPresenter).getWallPaperDialPathNew(name));
            }
        }
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialList(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        if (this.isDelete) {
            return;
        }
        this.mInstalledDialList.clear();
        for (String str : list) {
            if (Intrinsics.areEqual(str, BaseDialPresenter.WALLPAPER_DIAL_NAME)) {
                setWallPaperDial(str);
            } else {
                this.mInstalledDialList.add(StringsKt.replace$default(str, ".iwf", "", false, 4, (Object) null));
            }
        }
        ((MydialEditPresenter) this.mPresenter).saveDialLog("onGetInstalledDialList");
        ((MydialEditPresenter) this.mPresenter).requestDialInfoFromNames(this.mInstalledDialList);
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialListV3(List<DialPlateParam.PlateFileInfo> list) {
        if (this.isDelete || list == null) {
            return;
        }
        ((MydialEditPresenter) this.mPresenter).saveDialLog("onGetInstalledDialListV3");
        this.mInstalledDialList.clear();
        for (DialPlateParam.PlateFileInfo plateFileInfo : list) {
            if (plateFileInfo != null) {
                if (Intrinsics.areEqual(plateFileInfo.name, BaseDialPresenter.WALLPAPER_DIAL_NAME)) {
                    String str = plateFileInfo.name;
                    Intrinsics.checkExpressionValueIsNotNull(str, "info.name");
                    setWallPaperDial(str);
                } else {
                    List<String> list2 = this.mInstalledDialList;
                    String str2 = plateFileInfo.name;
                    Intrinsics.checkExpressionValueIsNotNull(str2, "info.name");
                    list2.add(StringsKt.replace$default(str2, ".iwf", "", false, 4, (Object) null));
                }
            }
        }
        ((MydialEditPresenter) this.mPresenter).requestDialInfoFromNames(this.mInstalledDialList);
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onGetMyDialList(List<MyDialListEntity.DialInfo> dialInfoList) {
        ArrayList arrayList;
        ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.mydial_llContainer)).showContent();
        this.mDialInfoList.clear();
        this.mMyDialList.clear();
        this.mTotalCount = dialInfoList != null ? dialInfoList.size() : 0;
        List<MyDialListEntity.DialInfo> list = this.mMyDialList;
        if (dialInfoList == null) {
            arrayList = new ArrayList();
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : dialInfoList) {
                if (!((MydialEditPresenter) this.mPresenter).isLocalDial(((MyDialListEntity.DialInfo) obj).getOtaFaceName())) {
                    arrayList2.add(obj);
                }
            }
            arrayList = arrayList2;
        }
        list.addAll(arrayList);
        MyDialListEntity.DialInfo dialInfo = this.wallPaperDial;
        if (dialInfo != null) {
            List<MyDialListEntity.DialInfo> list2 = this.mMyDialList;
            if (dialInfo == null) {
                Intrinsics.throwNpe();
            }
            list2.add(dialInfo);
            this.mTotalCount++;
        }
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tvTitle)).setText(getString(R.string.my_dial) + "(" + this.mMyDialList.size() + ")");
        MydialEditListAdapter mydialEditListAdapter = this.mDialAdapter;
        if (mydialEditListAdapter != null) {
            mydialEditListAdapter.setDeletable(this.mTotalCount > 1);
        }
    }

    private final boolean isSupportDialSort() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo != null && supportFunctionInfo.V3_set_watch_dial_sort_33_3E;
    }
}