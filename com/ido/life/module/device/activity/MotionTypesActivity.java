package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.adapter.MotionTypeAdapter;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.bean.MotionTypeBean;
import com.ido.life.customview.CustomItemDecoration;
import com.ido.life.customview.LoadingLayout;
import com.ido.life.customview.OnItemClickListener;
import com.ido.life.customview.OnItemMoveListener;
import com.ido.life.customview.OnSyncChangeListener;
import com.ido.life.customview.recyclerview.DefaultItemTouchHelper;
import com.ido.life.customview.recyclerview.DefaultItemTouchHelperCallback;
import com.ido.life.customview.recyclerview.OnStartDragListener;
import com.ido.life.customview.recyclerview.SwipeItemLayout;
import com.ido.life.data.cache.MotionTypeManager;
import com.ido.life.dialog.CommonDialog;
import com.ido.life.dialog.MotionTypeSyncDialog;
import com.ido.life.module.device.presenter.MotionTypesPresenter;
import com.ido.life.module.device.view.IMotionTypesView;
import com.ido.life.util.DialogUtils;
import com.ido.life.util.ListUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MotionTypesActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u0000 I2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0001IB\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\u001a\u001a\u00020\u000bH\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u0012\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u000bH\u0002J\b\u0010\u001f\u001a\u00020 H\u0014J\b\u0010!\u001a\u00020\u001cH\u0014J\b\u0010\"\u001a\u00020\u001cH\u0014J\b\u0010#\u001a\u00020\u001cH\u0014J\b\u0010$\u001a\u00020\u001cH\u0002J\b\u0010%\u001a\u00020\u001cH\u0016J\"\u0010&\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020 2\u0006\u0010(\u001a\u00020 2\b\u0010)\u001a\u0004\u0018\u00010*H\u0014J\b\u0010+\u001a\u00020\u001cH\u0016J\b\u0010,\u001a\u00020\u001cH\u0016J\u0016\u0010-\u001a\u00020\u001c2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0016J\u0018\u0010/\u001a\u00020\u001c2\u0006\u00100\u001a\u00020 2\u0006\u00101\u001a\u00020 H\u0016J\b\u00102\u001a\u00020\u001cH\u0016J\b\u00103\u001a\u00020\u001cH\u0016J\u0010\u00104\u001a\u00020\u001c2\u0006\u00105\u001a\u000206H\u0016J\u0018\u00107\u001a\u00020\u001c2\u0006\u00108\u001a\u00020 2\u0006\u00109\u001a\u00020 H\u0016J\u0010\u0010:\u001a\u00020\u001c2\u0006\u0010;\u001a\u00020\u000bH\u0016J\u0012\u0010<\u001a\u00020\u001c2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\b\u0010?\u001a\u00020\u001cH\u0002J\b\u0010@\u001a\u00020\u001cH\u0002J\b\u0010A\u001a\u00020\u001cH\u0002J\b\u0010B\u001a\u00020\u001cH\u0014J\u0010\u0010C\u001a\u00020\u001c2\u0006\u0010D\u001a\u00020 H\u0002J\b\u0010E\u001a\u00020\u001cH\u0002J\b\u0010F\u001a\u00020\u001cH\u0002J\b\u0010G\u001a\u00020\u001cH\u0002J\b\u0010H\u001a\u00020\u001cH\u0002R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/ido/life/module/device/activity/MotionTypesActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/presenter/MotionTypesPresenter;", "Lcom/ido/life/module/device/view/IMotionTypesView;", "Lcom/ido/life/customview/OnItemMoveListener;", "Lcom/ido/life/customview/recyclerview/OnStartDragListener;", "Lcom/ido/life/customview/OnSyncChangeListener;", "()V", "decoration", "Lcom/ido/life/customview/CustomItemDecoration;", "isEdit", "", "mAdapter", "Lcom/ido/life/adapter/MotionTypeAdapter;", "mItemTouchHelper", "Lcom/ido/life/customview/recyclerview/DefaultItemTouchHelper;", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "mMotionTypeList", "Ljava/util/ArrayList;", "Lcom/ido/life/bean/MotionTypeBean;", "mSyncDialog", "Lcom/ido/life/dialog/MotionTypeSyncDialog;", "mTmpMotionTypeList", "onSwipeItemTouchListener", "Lcom/ido/life/customview/recyclerview/SwipeItemLayout$OnSwipeItemTouchListener;", "checkDataChanged", "checkSize", "", "getDeviceMotionTypes", "forceUpdate", "getLayoutResId", "", "initData", "initEvent", "initLabelLanguage", "initRecyclerView", "initViews", "onActivityResult", "requestCode", "resultCode", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Landroid/content/Intent;", "onBackPressed", "onGetDeviceMotionTypesFailed", "onGetDeviceMotionTypesSuccess", "list", "onItemMove", "fromPosition", "toPosition", "onSetMotionTypesFailed", "onSetMotionTypesSuccess", "onStartDrag", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onSync", "value", "max", "onSyncComplete", "success", "onSyncFailed", "msg", "", "processSyncComplete", "restoreData", "saveTmpData", "sendCmd", "showDeleteDialog", "position", "showSyncFailedDialog", "showSyncProgressDialog", "switchEditStatus", "updateListView", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MotionTypesActivity extends BaseActivity<MotionTypesPresenter> implements IMotionTypesView, OnItemMoveListener, OnStartDragListener, OnSyncChangeListener {
    public static final int REQUEST_CODE_EDIT = 1000;
    private HashMap _$_findViewCache;
    private CustomItemDecoration decoration;
    private boolean isEdit;
    private MotionTypeAdapter mAdapter;
    private DefaultItemTouchHelper mItemTouchHelper;
    private LinearLayoutManager mLayoutManager;
    private MotionTypeSyncDialog mSyncDialog;
    private SwipeItemLayout.OnSwipeItemTouchListener onSwipeItemTouchListener;
    private ArrayList<MotionTypeBean> mMotionTypeList = new ArrayList<>();
    private ArrayList<MotionTypeBean> mTmpMotionTypeList = new ArrayList<>();

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
        return R.layout.activity_motion_types;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_motion_type));
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mTitleBar.initLayout(4).setLeftText(getLanguageText(R.string.public_cancel)).switchLeftImgAndTextVisibility(true).setRightText(getLanguageText(R.string.public_edit)).setRightTextColor(ResourceUtil.getColor(R.color.white)).setLeftText(getLanguageText(R.string.public_cancel)).setLeftTextColor(ResourceUtil.getColor(R.color.white)).switchLeftImgAndTextVisibility(true).setLeftTextOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.MotionTypesActivity.initViews.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MotionTypesActivity.this.restoreData();
            }
        }).setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.MotionTypesActivity.initViews.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MotionTypesActivity.this.onBackPressed();
            }
        }).setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.MotionTypesActivity.initViews.3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (MotionTypesActivity.this.isEdit) {
                    MotionTypesActivity.this.saveData();
                } else {
                    MotionTypesActivity.this.switchEditStatus();
                }
            }
        });
        LinearLayout titleLayoutRight = this.mTitleBar.getTitleLayoutRight(this);
        Intrinsics.checkExpressionValueIsNotNull(titleLayoutRight, "mTitleBar.getTitleLayoutRight(this)");
        titleLayoutRight.setVisibility(4);
        initRecyclerView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void restoreData() {
        switchEditStatus();
    }

    private final void initRecyclerView() {
        MotionTypesActivity motionTypesActivity = this;
        this.mLayoutManager = new LinearLayoutManager(motionTypesActivity);
        RecyclerView recyclerview = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview, "recyclerview");
        recyclerview.setLayoutManager(this.mLayoutManager);
        this.mAdapter = new MotionTypeAdapter(motionTypesActivity, this.mMotionTypeList);
        RecyclerView recyclerview2 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview2, "recyclerview");
        recyclerview2.setAdapter(this.mAdapter);
        RecyclerView recyclerview3 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview3, "recyclerview");
        this.decoration = new CustomItemDecoration(recyclerview3).color(ContextCompat.getColor(motionTypesActivity, R.color.color_F2F2F6)).marginLeft(DipPixelUtil.dip2pxF(16.0f)).marginRight(DipPixelUtil.dip2pxF(16.0f)).height(DipPixelUtil.dip2pxF(0.5f));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        CustomItemDecoration customItemDecoration = this.decoration;
        if (customItemDecoration == null) {
            Intrinsics.throwNpe();
        }
        recyclerView.addItemDecoration(customItemDecoration);
        this.mItemTouchHelper = new DefaultItemTouchHelper(new DefaultItemTouchHelperCallback(this.mAdapter));
        DefaultItemTouchHelper defaultItemTouchHelper = this.mItemTouchHelper;
        if (defaultItemTouchHelper != null) {
            defaultItemTouchHelper.setSwipeEnable(false);
        }
        DefaultItemTouchHelper defaultItemTouchHelper2 = this.mItemTouchHelper;
        if (defaultItemTouchHelper2 != null) {
            defaultItemTouchHelper2.setDragEnable(true);
        }
        this.onSwipeItemTouchListener = new SwipeItemLayout.OnSwipeItemTouchListener(motionTypesActivity);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        MotionTypeAdapter motionTypeAdapter = this.mAdapter;
        if (motionTypeAdapter != null) {
            motionTypeAdapter.setOnItemMoveListener(this);
        }
        MotionTypeAdapter motionTypeAdapter2 = this.mAdapter;
        if (motionTypeAdapter2 != null) {
            motionTypeAdapter2.setOnStartDragListener(this);
        }
        MotionTypeAdapter motionTypeAdapter3 = this.mAdapter;
        if (motionTypeAdapter3 != null) {
            motionTypeAdapter3.setOnDeleteClickListener(new OnItemClickListener() { // from class: com.ido.life.module.device.activity.MotionTypesActivity.initEvent.1
                @Override // com.ido.life.customview.OnItemClickListener
                public void onItemClick(int position) {
                    ArrayList arrayList = MotionTypesActivity.this.mMotionTypeList;
                    if (arrayList != null) {
                    }
                    MotionTypesActivity.this.updateListView();
                }
            });
        }
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tvAdd)).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.MotionTypesActivity.initEvent.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MotionTypesActivity motionTypesActivity = MotionTypesActivity.this;
                motionTypesActivity.startActivityForResult(new SingleTopIntent(motionTypesActivity, (Class<?>) MotionTypeChooseActivity.class).putExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME, MotionTypesActivity.this.mMotionTypeList), 1000);
            }
        });
    }

    private final void showDeleteDialog(final int position) {
        DialogUtils dialogUtils = DialogUtils.INSTANCE;
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "supportFragmentManager");
        DialogUtils.showDeleteConfirmDialog$default(dialogUtils, supportFragmentManager, new CommonDialog.SampleDialogEventListener() { // from class: com.ido.life.module.device.activity.MotionTypesActivity.showDeleteDialog.1
            @Override // com.ido.life.dialog.CommonDialog.SampleDialogEventListener, com.ido.life.dialog.CommonDialog.OnDialogEventListener
            public void onConfirmClick(CommonDialog dialog) {
                super.onConfirmClick(dialog);
                ArrayList arrayList = MotionTypesActivity.this.mMotionTypeList;
                if (arrayList != null) {
                }
                MotionTypesActivity.this.updateListView();
            }
        }, null, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateListView() {
        checkSize();
        MotionTypeAdapter motionTypeAdapter = this.mAdapter;
        if (motionTypeAdapter != null) {
            motionTypeAdapter.notifyDataSetChanged();
        }
    }

    private final void checkSize() {
        ArrayList<MotionTypeBean> arrayList = this.mMotionTypeList;
        if (arrayList != null) {
            MotionTypeAdapter motionTypeAdapter = this.mAdapter;
            if (motionTypeAdapter != null) {
                motionTypeAdapter.setEnableDelete(arrayList.size() > MotionTypeManager.INSTANCE.getMIN_MOTION_TYPE());
            }
            if (arrayList.size() > 0) {
                ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer)).showContent();
            }
            TextView tvCountTips = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvCountTips);
            Intrinsics.checkExpressionValueIsNotNull(tvCountTips, "tvCountTips");
            String languageText = getLanguageText(R.string.motion_type_count_tips);
            Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string.motion_type_count_tips)");
            Object[] objArr = {Integer.valueOf(arrayList.size()), Integer.valueOf(MotionTypeManager.INSTANCE.getMAX_MOTION_TYPE())};
            String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
            tvCountTips.setText(str);
        }
        LinearLayout titleLayoutRight = this.mTitleBar.getTitleLayoutRight(this);
        Intrinsics.checkExpressionValueIsNotNull(titleLayoutRight, "mTitleBar.getTitleLayoutRight(this)");
        titleLayoutRight.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void switchEditStatus() {
        CommonLogUtil.d("switchEditStatus isEdit = " + this.isEdit);
        this.isEdit = this.isEdit ^ true;
        this.mTitleBar.setRightText(getLanguageText(this.isEdit ? R.string.public_complete : R.string.public_edit));
        TextView tvAdd = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvAdd);
        Intrinsics.checkExpressionValueIsNotNull(tvAdd, "tvAdd");
        tvAdd.setVisibility(this.isEdit ? 0 : 8);
        MotionTypeAdapter motionTypeAdapter = this.mAdapter;
        if (motionTypeAdapter != null) {
            motionTypeAdapter.setEdit(this.isEdit);
        }
        DefaultItemTouchHelper defaultItemTouchHelper = this.mItemTouchHelper;
        if (defaultItemTouchHelper != null) {
            defaultItemTouchHelper.attachToRecyclerView(this.isEdit ? (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview) : null);
        }
        SwipeItemLayout.OnSwipeItemTouchListener onSwipeItemTouchListener = this.onSwipeItemTouchListener;
        if (onSwipeItemTouchListener != null) {
            if (this.isEdit) {
                ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview)).addOnItemTouchListener(onSwipeItemTouchListener);
            } else {
                ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview)).removeOnItemTouchListener(onSwipeItemTouchListener);
            }
        }
        this.mTitleBar.switchLeftImgAndTextVisibility(!this.isEdit);
        if (this.isEdit) {
            saveTmpData();
            return;
        }
        this.mMotionTypeList.clear();
        this.mMotionTypeList.addAll(this.mTmpMotionTypeList);
        updateListView();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        if (this.mMotionTypeList != null && checkDataChanged()) {
            if (((MotionTypesPresenter) this.mPresenter).supportIconTrans()) {
                showSyncProgressDialog();
            } else {
                showSettingLoading(false);
            }
            MotionTypesPresenter motionTypesPresenter = (MotionTypesPresenter) this.mPresenter;
            if (motionTypesPresenter != null) {
                motionTypesPresenter.setDeviceMotionTypes(this.mMotionTypeList);
                return;
            }
            return;
        }
        switchEditStatus();
    }

    private final void showSyncProgressDialog() {
        MotionTypeSyncDialog motionTypeSyncDialog;
        MotionTypeSyncDialog motionTypeSyncDialog2 = this.mSyncDialog;
        if (motionTypeSyncDialog2 != null && motionTypeSyncDialog2 != null && motionTypeSyncDialog2.isDialogShowing() && (motionTypeSyncDialog = this.mSyncDialog) != null) {
            motionTypeSyncDialog.dismissAllowingStateLoss();
        }
        MotionTypeSyncDialog.Companion companion = MotionTypeSyncDialog.INSTANCE;
        String languageText = getLanguageText(R.string.motion_type_sync_ing);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string.motion_type_sync_ing)");
        String languageText2 = getLanguageText(R.string.motion_type_sync_complete);
        Intrinsics.checkExpressionValueIsNotNull(languageText2, "getLanguageText(R.string…otion_type_sync_complete)");
        this.mSyncDialog = companion.newInstance(languageText, languageText2);
        MotionTypeSyncDialog motionTypeSyncDialog3 = this.mSyncDialog;
        if (motionTypeSyncDialog3 != null) {
            motionTypeSyncDialog3.show(getSupportFragmentManager());
        }
    }

    private final boolean checkDataChanged() {
        Object next;
        boolean zIsSupportMiddleIcon = ((MotionTypesPresenter) this.mPresenter).isSupportMiddleIcon();
        if (!Intrinsics.areEqual(this.mMotionTypeList, MotionTypeManager.INSTANCE.getInstance().getMMotionTypesInDevice())) {
            return true;
        }
        Iterator<T> it = this.mMotionTypeList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            int iconFlag = ((MotionTypeBean) next).getIconFlag();
            if (!zIsSupportMiddleIcon ? iconFlag == 3 : iconFlag == 7) {
                break;
            }
        }
        return next != null;
    }

    @Override // com.ido.life.customview.OnSyncChangeListener
    public void onSync(int value, int max) {
        CommonLogUtil.printAndSave("onSync max = " + max + ",  value = " + value);
        MotionTypeSyncDialog motionTypeSyncDialog = this.mSyncDialog;
        if (motionTypeSyncDialog != null) {
            motionTypeSyncDialog.onSync(value, max);
        }
    }

    @Override // com.ido.life.customview.OnSyncChangeListener
    public void onSyncComplete(boolean success) {
        MotionTypeSyncDialog motionTypeSyncDialog;
        CommonLogUtil.printAndSave("onSyncComplete success = " + success);
        CommonLogUtil.printAndSave(success ? "运动图标传输成功" : "运动图标传输失败！！！");
        if (success && (motionTypeSyncDialog = this.mSyncDialog) != null) {
            motionTypeSyncDialog.onSync(100, 100);
        }
        MotionTypeSyncDialog motionTypeSyncDialog2 = this.mSyncDialog;
        if (motionTypeSyncDialog2 != null) {
            motionTypeSyncDialog2.onSyncComplete(success);
        }
        if (success) {
            processSyncComplete();
        } else {
            showSyncFailedDialog();
        }
        dismissLoadingDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processSyncComplete() {
        saveTmpData();
        switchEditStatus();
        CommonLogUtil.printAndSave("数据同步成功，刷新数据");
        getDeviceMotionTypes(true);
    }

    private final void saveTmpData() {
        this.mTmpMotionTypeList.clear();
        this.mTmpMotionTypeList.addAll(this.mMotionTypeList);
    }

    @Override // com.ido.life.customview.OnSyncChangeListener
    public void onSyncFailed(String msg) {
        MotionTypeSyncDialog motionTypeSyncDialog = this.mSyncDialog;
        if (motionTypeSyncDialog != null) {
            motionTypeSyncDialog.dismissAllowingStateLoss();
        }
        showSyncFailedDialog();
    }

    @Override // com.ido.life.module.device.view.IMotionTypesView
    public void onSetMotionTypesSuccess() {
        CommonLogUtil.printAndSave("onSetMotionTypesSuccess");
    }

    @Override // com.ido.life.module.device.view.IMotionTypesView
    public void onSetMotionTypesFailed() {
        MotionTypeSyncDialog motionTypeSyncDialog = this.mSyncDialog;
        if (motionTypeSyncDialog != null) {
            motionTypeSyncDialog.dismissAllowingStateLoss();
        }
        dismissLoadingDialog();
        if (((MotionTypesPresenter) this.mPresenter).supportIconTrans()) {
            showSyncFailedDialog();
        }
        CommonLogUtil.printAndSave("onSetMotionTypesFailed");
        showToast(getLanguageText(R.string.public_set_failed));
    }

    private final void showSyncFailedDialog() {
        this.mSyncDialog = (MotionTypeSyncDialog) null;
        DialogUtils dialogUtils = DialogUtils.INSTANCE;
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "supportFragmentManager");
        String languageText = getLanguageText(R.string.motion_types_sync_failed_tip);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string…on_types_sync_failed_tip)");
        DialogUtils.showSyncFailedDialog$default(dialogUtils, supportFragmentManager, 0, languageText, new Function0<Unit>() { // from class: com.ido.life.module.device.activity.MotionTypesActivity.showSyncFailedDialog.1
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
                CommonLogUtil.printAndSave("用户点击取消重试，不补传");
                MotionTypeManager.INSTANCE.getInstance().completeIconTransStatus();
                MotionTypesActivity.this.processSyncComplete();
            }
        }, new Function0<Unit>() { // from class: com.ido.life.module.device.activity.MotionTypesActivity.showSyncFailedDialog.2
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
                MotionTypesActivity.this.saveData();
            }
        }, 2, null);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && data != null && requestCode == 1000) {
            ArrayList arrayList = (ArrayList) data.getSerializableExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME);
            if (ListUtils.INSTANCE.isNotEmpty(arrayList)) {
                ArrayList<MotionTypeBean> arrayList2 = this.mMotionTypeList;
                if (arrayList2 != null) {
                    arrayList2.clear();
                }
                ArrayList<MotionTypeBean> arrayList3 = this.mMotionTypeList;
                if (arrayList3 != null) {
                    if (arrayList == null) {
                        Intrinsics.throwNpe();
                    }
                    arrayList3.addAll(arrayList);
                }
                updateListView();
            }
        }
    }

    @Override // com.ido.life.customview.OnItemMoveListener
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            int i = fromPosition;
            while (i < toPosition) {
                int i2 = i + 1;
                Collections.swap(this.mMotionTypeList, i, i2);
                i = i2;
            }
        } else {
            int i3 = toPosition + 1;
            if (fromPosition >= i3) {
                int i4 = fromPosition;
                while (true) {
                    Collections.swap(this.mMotionTypeList, i4, i4 - 1);
                    if (i4 == i3) {
                        break;
                    } else {
                        i4--;
                    }
                }
            }
        }
        MotionTypeAdapter motionTypeAdapter = this.mAdapter;
        if (motionTypeAdapter != null) {
            motionTypeAdapter.notifyItemMoved(fromPosition, toPosition);
        }
    }

    @Override // com.ido.life.customview.recyclerview.OnStartDragListener
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        DefaultItemTouchHelper defaultItemTouchHelper = this.mItemTouchHelper;
        if (defaultItemTouchHelper != null) {
            defaultItemTouchHelper.onStartDrag(viewHolder);
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer)).showLoading();
        getDeviceMotionTypes$default(this, false, 1, null);
    }

    static /* synthetic */ void getDeviceMotionTypes$default(MotionTypesActivity motionTypesActivity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        motionTypesActivity.getDeviceMotionTypes(z);
    }

    private final void getDeviceMotionTypes(boolean forceUpdate) {
        MotionTypesPresenter motionTypesPresenter = (MotionTypesPresenter) this.mPresenter;
        if (motionTypesPresenter != null) {
            motionTypesPresenter.getDeviceMotionTypes(forceUpdate);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.ido.life.module.device.view.IMotionTypesView
    public void onGetDeviceMotionTypesSuccess(ArrayList<MotionTypeBean> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        ArrayList<MotionTypeBean> arrayList = this.mMotionTypeList;
        if (arrayList != null) {
            arrayList.clear();
        }
        ArrayList<MotionTypeBean> arrayList2 = this.mMotionTypeList;
        if (arrayList2 != null) {
            arrayList2.addAll(list);
        }
        ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer)).showContent();
        updateListView();
    }

    @Override // com.ido.life.module.device.view.IMotionTypesView
    public void onGetDeviceMotionTypesFailed() {
        LoadingLayout.showError$default((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer), null, false, 0, 7, null);
        ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.llContainer)).setRetryListener(new Function0<Unit>() { // from class: com.ido.life.module.device.activity.MotionTypesActivity.onGetDeviceMotionTypesFailed.1
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
                MotionTypesActivity.this.initData();
            }
        });
    }
}