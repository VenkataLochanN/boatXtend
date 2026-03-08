package com.ido.life.module.device.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.adapter.WorldTimeListAdapter;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.bean.WorldTimeCity;
import com.ido.life.customview.CustomItemDecoration;
import com.ido.life.customview.OnItemClickListener;
import com.ido.life.customview.OnItemMoveListener;
import com.ido.life.customview.recyclerview.DefaultItemTouchHelper;
import com.ido.life.customview.recyclerview.DefaultItemTouchHelperCallback;
import com.ido.life.customview.recyclerview.OnStartDragListener;
import com.ido.life.customview.recyclerview.SwipeItemLayout;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.dialog.CommonDialog;
import com.ido.life.module.device.presenter.WorldTimeListPresenter;
import com.ido.life.module.device.view.IWorldTimeListView;
import com.ido.life.util.DialogUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorldTimeListActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 B2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u0005:\u0001BB\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001aH\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0014J\u0006\u0010\u001e\u001a\u00020\u001aJ\b\u0010\u001f\u001a\u00020\u001aH\u0014J\b\u0010 \u001a\u00020\u001aH\u0014J\b\u0010!\u001a\u00020\u001aH\u0014J\b\u0010\"\u001a\u00020\u001aH\u0002J\b\u0010#\u001a\u00020\u001aH\u0016J\"\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u001d2\b\u0010'\u001a\u0004\u0018\u00010(H\u0014J\b\u0010)\u001a\u00020\u001aH\u0016J\b\u0010*\u001a\u00020\u001aH\u0016J\u0016\u0010+\u001a\u00020\u001a2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00150-H\u0016J\u0018\u0010.\u001a\u00020\u001a2\u0006\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u00020\u001dH\u0016J\b\u00101\u001a\u00020\u001aH\u0014J\b\u00102\u001a\u00020\u001aH\u0014J\b\u00103\u001a\u00020\u001aH\u0016J\b\u00104\u001a\u00020\u001aH\u0016J\u0010\u00105\u001a\u00020\u001a2\u0006\u00106\u001a\u000207H\u0016J\b\u00108\u001a\u00020\u001aH\u0002J\b\u00109\u001a\u00020\u001aH\u0014J\u0010\u0010:\u001a\u00020\u001a2\u0006\u0010;\u001a\u00020\u001dH\u0002J\u0006\u0010<\u001a\u00020\u001aJ\b\u0010=\u001a\u00020\u001aH\u0002J\b\u0010>\u001a\u00020\u001aH\u0002J\b\u0010?\u001a\u00020\u001aH\u0002J\b\u0010@\u001a\u00020\bH\u0002J\b\u0010A\u001a\u00020\u001aH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/ido/life/module/device/activity/WorldTimeListActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/presenter/WorldTimeListPresenter;", "Lcom/ido/life/module/device/view/IWorldTimeListView;", "Lcom/ido/life/customview/OnItemMoveListener;", "Lcom/ido/life/customview/recyclerview/OnStartDragListener;", "()V", "isBack", "", "isEdit", "isTimerStarted", "mAdapter", "Lcom/ido/life/adapter/WorldTimeListAdapter;", "mItemTouchHelper", "Lcom/ido/life/customview/recyclerview/DefaultItemTouchHelper;", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "mTimerHandler", "Landroid/os/Handler;", "mTmpWorldTimeList", "Ljava/util/ArrayList;", "Lcom/ido/life/bean/WorldTimeCity;", "mWorldTimeList", "onSwipeItemTouchListener", "Lcom/ido/life/customview/recyclerview/SwipeItemLayout$OnSwipeItemTouchListener;", "changeAddBtnStatus", "", "checkEmpty", "getLayoutResId", "", "hideLoading", "initData", "initEvent", "initLabelLanguage", "initRecyclerView", "initViews", "onActivityResult", "requestCode", "resultCode", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Landroid/content/Intent;", "onBackPressed", "onGetWorldTimeListFailed", "onGetWorldTimeListSuccess", "list", "", "onItemMove", "fromPosition", "toPosition", "onPause", "onResume", "onSetWorldTimeFailed", "onSetWorldTimeSuccess", "onStartDrag", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "restoreData", "sendCmd", "showDeleteDialog", "position", "showLoading", "startTimer", "stopTimer", "switchEditStatus", "syncData", "update", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WorldTimeListActivity extends BaseActivity<WorldTimeListPresenter> implements IWorldTimeListView, OnItemMoveListener, OnStartDragListener {
    public static final int MAX_COUNT = 10;
    public static final int MIN_COUNT = 1;
    public static final int REQUEST_CODE_ADD_CITY = 1000;
    public static final String TAG = "WorldTimeListActivity";
    public static final long TIME_REFRESH = 2000;
    private HashMap _$_findViewCache;
    private boolean isBack;
    private boolean isEdit;
    private boolean isTimerStarted;
    private WorldTimeListAdapter mAdapter;
    private DefaultItemTouchHelper mItemTouchHelper;
    private LinearLayoutManager mLayoutManager;
    private SwipeItemLayout.OnSwipeItemTouchListener onSwipeItemTouchListener;
    private ArrayList<WorldTimeCity> mWorldTimeList = new ArrayList<>();
    private ArrayList<WorldTimeCity> mTmpWorldTimeList = new ArrayList<>();
    private Handler mTimerHandler = new Handler(Looper.getMainLooper());

    private final void checkEmpty() {
    }

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
        return R.layout.activity_world_time_list;
    }

    private final void startTimer() {
        if (this.isTimerStarted) {
            return;
        }
        this.isTimerStarted = true;
        this.mTimerHandler.postDelayed(new Runnable() { // from class: com.ido.life.module.device.activity.WorldTimeListActivity.startTimer.1
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList;
                if (!WorldTimeListActivity.this.isTimerStarted || (arrayList = WorldTimeListActivity.this.mWorldTimeList) == null || !(!arrayList.isEmpty())) {
                    WorldTimeListActivity.this.isTimerStarted = false;
                    return;
                }
                WorldTimeListAdapter worldTimeListAdapter = WorldTimeListActivity.this.mAdapter;
                if (worldTimeListAdapter != null) {
                    worldTimeListAdapter.refreshTimer();
                }
                WorldTimeListActivity.this.mTimerHandler.postDelayed(this, 2000L);
            }
        }, 2000L);
    }

    private final void stopTimer() {
        this.isTimerStarted = false;
        this.mTimerHandler.removeCallbacksAndMessages(null);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        update();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        stopTimer();
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mTitleBar.initLayout(4).setRightText(getLanguageText(R.string.public_edit)).setRightTextColor(ResourceUtil.getColor(R.color.white)).setLeftText(getLanguageText(R.string.public_cancel)).setLeftTextColor(ResourceUtil.getColor(R.color.white)).switchLeftImgAndTextVisibility(true).setLeftTextOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.WorldTimeListActivity.initViews.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WorldTimeListActivity.this.restoreData();
            }
        }).setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.WorldTimeListActivity.initViews.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WorldTimeListActivity.this.onBackPressed();
            }
        }).setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.WorldTimeListActivity.initViews.3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (WorldTimeListActivity.this.isEdit) {
                    WorldTimeListActivity.this.saveData();
                } else {
                    WorldTimeListActivity.this.switchEditStatus();
                }
            }
        });
        LinearLayout titleLayoutRight = this.mTitleBar.getTitleLayoutRight(this);
        Intrinsics.checkExpressionValueIsNotNull(titleLayoutRight, "mTitleBar.getTitleLayoutRight(this)");
        titleLayoutRight.setVisibility(4);
        TextView tvAdd = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvAdd);
        Intrinsics.checkExpressionValueIsNotNull(tvAdd, "tvAdd");
        tvAdd.setVisibility(8);
        TextView tvMaxCity = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvMaxCity);
        Intrinsics.checkExpressionValueIsNotNull(tvMaxCity, "tvMaxCity");
        String languageText = getLanguageText(R.string.world_time_max_item);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string.world_time_max_item)");
        Object[] objArr = {10};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        tvMaxCity.setText(str);
        initRecyclerView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void restoreData() {
        switchEditStatus();
    }

    private final void initRecyclerView() {
        WorldTimeListActivity worldTimeListActivity = this;
        this.mLayoutManager = new LinearLayoutManager(worldTimeListActivity);
        RecyclerView recyclerview = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview, "recyclerview");
        recyclerview.setLayoutManager(this.mLayoutManager);
        this.mAdapter = new WorldTimeListAdapter(worldTimeListActivity, this.mWorldTimeList);
        RecyclerView recyclerview2 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview2, "recyclerview");
        recyclerview2.setAdapter(this.mAdapter);
        ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview)).addItemDecoration(new CustomItemDecoration().color(ContextCompat.getColor(worldTimeListActivity, R.color.translate)).height(DipPixelUtil.dip2pxF(12.0f)));
        this.mItemTouchHelper = new DefaultItemTouchHelper(new DefaultItemTouchHelperCallback(this.mAdapter));
        DefaultItemTouchHelper defaultItemTouchHelper = this.mItemTouchHelper;
        if (defaultItemTouchHelper != null) {
            defaultItemTouchHelper.setSwipeEnable(false);
        }
        DefaultItemTouchHelper defaultItemTouchHelper2 = this.mItemTouchHelper;
        if (defaultItemTouchHelper2 != null) {
            defaultItemTouchHelper2.setDragEnable(true);
        }
        this.onSwipeItemTouchListener = new SwipeItemLayout.OnSwipeItemTouchListener(worldTimeListActivity);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        WorldTimeListAdapter worldTimeListAdapter = this.mAdapter;
        if (worldTimeListAdapter != null) {
            worldTimeListAdapter.setOnItemMoveListener(this);
        }
        WorldTimeListAdapter worldTimeListAdapter2 = this.mAdapter;
        if (worldTimeListAdapter2 != null) {
            worldTimeListAdapter2.setOnStartDragListener(this);
        }
        WorldTimeListAdapter worldTimeListAdapter3 = this.mAdapter;
        if (worldTimeListAdapter3 != null) {
            worldTimeListAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.ido.life.module.device.activity.WorldTimeListActivity.initEvent.1
                @Override // com.ido.life.customview.OnItemClickListener
                public void onItemClick(int position) {
                    WorldTimeListActivity worldTimeListActivity = WorldTimeListActivity.this;
                    SingleTopIntent singleTopIntent = new SingleTopIntent(worldTimeListActivity, (Class<?>) WorldTimeDetailActivity.class);
                    ArrayList arrayList = WorldTimeListActivity.this.mWorldTimeList;
                    worldTimeListActivity.startActivity(singleTopIntent.putExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME, arrayList != null ? (WorldTimeCity) arrayList.get(position) : null));
                }
            });
        }
        WorldTimeListAdapter worldTimeListAdapter4 = this.mAdapter;
        if (worldTimeListAdapter4 != null) {
            worldTimeListAdapter4.setOnDeleteClickListener(new OnItemClickListener() { // from class: com.ido.life.module.device.activity.WorldTimeListActivity.initEvent.2
                @Override // com.ido.life.customview.OnItemClickListener
                public void onItemClick(int position) {
                    ArrayList arrayList = WorldTimeListActivity.this.mWorldTimeList;
                    if (arrayList == null || arrayList.size() <= 1) {
                        return;
                    }
                    ArrayList arrayList2 = WorldTimeListActivity.this.mWorldTimeList;
                    if (arrayList2 != null) {
                    }
                    WorldTimeListActivity.this.update();
                }
            });
        }
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tvAdd)).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.WorldTimeListActivity.initEvent.3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WorldTimeListActivity worldTimeListActivity = WorldTimeListActivity.this;
                worldTimeListActivity.startActivityForResult(new SingleTopIntent(worldTimeListActivity, (Class<?>) WorldTimeCityChooseActivity.class).putExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME, WorldTimeListActivity.this.mWorldTimeList), 1000);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void switchEditStatus() {
        this.isEdit = !this.isEdit;
        this.mTitleBar.setRightText(getLanguageText(this.isEdit ? R.string.public_complete : R.string.public_edit));
        WorldTimeListAdapter worldTimeListAdapter = this.mAdapter;
        if (worldTimeListAdapter != null) {
            worldTimeListAdapter.setEdit(this.isEdit);
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
        changeAddBtnStatus();
        this.mTitleBar.switchLeftImgAndTextVisibility(!this.isEdit);
        if (this.isEdit) {
            this.mTmpWorldTimeList.clear();
            this.mTmpWorldTimeList.addAll(this.mWorldTimeList);
            return;
        }
        this.mWorldTimeList.clear();
        this.mWorldTimeList.addAll(this.mTmpWorldTimeList);
        WorldTimeListAdapter worldTimeListAdapter2 = this.mAdapter;
        if (worldTimeListAdapter2 != null) {
            worldTimeListAdapter2.notifyDataSetChanged();
        }
    }

    private final void changeAddBtnStatus() {
        TextView tvAdd = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvAdd);
        Intrinsics.checkExpressionValueIsNotNull(tvAdd, "tvAdd");
        tvAdd.setVisibility((this.isEdit || this.mWorldTimeList.size() >= 10) ? 8 : 0);
        WorldTimeListAdapter worldTimeListAdapter = this.mAdapter;
        if (worldTimeListAdapter != null) {
            worldTimeListAdapter.setEnableDelete(this.mWorldTimeList.size() > 1);
        }
        checkEmpty();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        showLoadingDialog(true);
        if (syncData()) {
            return;
        }
        dismissLoadingDialog();
        if (this.isBack) {
            finish();
        } else if (this.isEdit) {
            this.mTmpWorldTimeList.clear();
            this.mTmpWorldTimeList.addAll(this.mWorldTimeList);
            switchEditStatus();
        }
    }

    private final boolean syncData() {
        WorldTimeListPresenter worldTimeListPresenter = (WorldTimeListPresenter) this.mPresenter;
        ArrayList<WorldTimeCity> arrayList = this.mWorldTimeList;
        if (arrayList == null) {
            Intrinsics.throwNpe();
        }
        return worldTimeListPresenter.setWorldTime(arrayList);
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.world_time_title));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        showLoading();
        WorldTimeListPresenter worldTimeListPresenter = (WorldTimeListPresenter) this.mPresenter;
        if (worldTimeListPresenter != null) {
            worldTimeListPresenter.getWorldTimeList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void update() {
        startTimer();
        changeAddBtnStatus();
        WorldTimeListAdapter worldTimeListAdapter = this.mAdapter;
        if (worldTimeListAdapter != null) {
            worldTimeListAdapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        WorldTimeCity worldTimeCity;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1 || data == null || requestCode != 1000 || (worldTimeCity = (WorldTimeCity) data.getSerializableExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME)) == null) {
            return;
        }
        ArrayList<WorldTimeCity> arrayList = this.mWorldTimeList;
        if (arrayList != null) {
            arrayList.add(worldTimeCity);
        }
        update();
    }

    @Override // com.ido.life.customview.OnItemMoveListener
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            int i = fromPosition;
            while (i < toPosition) {
                int i2 = i + 1;
                Collections.swap(this.mWorldTimeList, i, i2);
                i = i2;
            }
        } else {
            int i3 = toPosition + 1;
            if (fromPosition >= i3) {
                int i4 = fromPosition;
                while (true) {
                    Collections.swap(this.mWorldTimeList, i4, i4 - 1);
                    if (i4 == i3) {
                        break;
                    } else {
                        i4--;
                    }
                }
            }
        }
        WorldTimeListAdapter worldTimeListAdapter = this.mAdapter;
        if (worldTimeListAdapter != null) {
            worldTimeListAdapter.notifyItemMoved(fromPosition, toPosition);
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

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        this.isBack = true;
        saveData();
    }

    public final void showLoading() {
        CommLoadingView comm_loading_view = (CommLoadingView) _$_findCachedViewById(com.ido.life.R.id.comm_loading_view);
        Intrinsics.checkExpressionValueIsNotNull(comm_loading_view, "comm_loading_view");
        comm_loading_view.setVisibility(0);
    }

    public final void hideLoading() {
        CommLoadingView comm_loading_view = (CommLoadingView) _$_findCachedViewById(com.ido.life.R.id.comm_loading_view);
        Intrinsics.checkExpressionValueIsNotNull(comm_loading_view, "comm_loading_view");
        comm_loading_view.setVisibility(8);
    }

    private final void showDeleteDialog(final int position) {
        DialogUtils dialogUtils = DialogUtils.INSTANCE;
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "supportFragmentManager");
        DialogUtils.showDeleteConfirmDialog$default(dialogUtils, supportFragmentManager, new CommonDialog.SampleDialogEventListener() { // from class: com.ido.life.module.device.activity.WorldTimeListActivity.showDeleteDialog.1
            @Override // com.ido.life.dialog.CommonDialog.SampleDialogEventListener, com.ido.life.dialog.CommonDialog.OnDialogEventListener
            public void onConfirmClick(CommonDialog dialog) {
                super.onConfirmClick(dialog);
                ArrayList arrayList = WorldTimeListActivity.this.mWorldTimeList;
                if (arrayList != null) {
                }
                WorldTimeListActivity.this.update();
            }
        }, null, 4, null);
    }

    @Override // com.ido.life.module.device.view.IWorldTimeListView
    public void onGetWorldTimeListSuccess(List<WorldTimeCity> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        hideLoading();
        LinearLayout layout_content = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_content);
        Intrinsics.checkExpressionValueIsNotNull(layout_content, "layout_content");
        layout_content.setVisibility(0);
        LinearLayout layout_load_failed = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_load_failed);
        Intrinsics.checkExpressionValueIsNotNull(layout_load_failed, "layout_load_failed");
        layout_load_failed.setVisibility(8);
        TextView tvAdd = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvAdd);
        Intrinsics.checkExpressionValueIsNotNull(tvAdd, "tvAdd");
        tvAdd.setVisibility(0);
        ArrayList<WorldTimeCity> arrayList = this.mWorldTimeList;
        if (arrayList != null) {
            arrayList.clear();
        }
        ArrayList<WorldTimeCity> arrayList2 = this.mWorldTimeList;
        if (arrayList2 != null) {
            arrayList2.addAll(list);
        }
        LinearLayout titleLayoutRight = this.mTitleBar.getTitleLayoutRight(this);
        Intrinsics.checkExpressionValueIsNotNull(titleLayoutRight, "mTitleBar.getTitleLayoutRight(this)");
        ArrayList<WorldTimeCity> arrayList3 = this.mWorldTimeList;
        titleLayoutRight.setVisibility((arrayList3 != null ? Integer.valueOf(arrayList3.size()) : null).intValue() <= 0 ? 4 : 0);
        update();
    }

    @Override // com.ido.life.module.device.view.IWorldTimeListView
    public void onGetWorldTimeListFailed() {
        hideLoading();
        LinearLayout layout_content = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_content);
        Intrinsics.checkExpressionValueIsNotNull(layout_content, "layout_content");
        layout_content.setVisibility(8);
        LinearLayout layout_load_failed = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_load_failed);
        Intrinsics.checkExpressionValueIsNotNull(layout_load_failed, "layout_load_failed");
        layout_load_failed.setVisibility(0);
    }

    @Override // com.ido.life.module.device.view.IWorldTimeListView
    public void onSetWorldTimeSuccess() {
        dismissLoadingDialog();
        if (this.isBack) {
            finish();
            return;
        }
        this.mTmpWorldTimeList.clear();
        this.mTmpWorldTimeList.addAll(this.mWorldTimeList);
        switchEditStatus();
        update();
    }

    @Override // com.ido.life.module.device.view.IWorldTimeListView
    public void onSetWorldTimeFailed() {
        dismissLoadingDialog();
        showCmdResultToast(false);
        if (this.isBack) {
            finish();
        } else {
            switchEditStatus();
        }
    }
}