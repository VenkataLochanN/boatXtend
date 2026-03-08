package com.ido.life.module.device.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.FileSizeUtil;
import com.ido.life.adapter.WorldTimeCityAdapter;
import com.ido.life.base.BaseActivity;
import com.ido.life.bean.WorldTimeCity;
import com.ido.life.customview.CitySideBar;
import com.ido.life.customview.CustomItemDecoration;
import com.ido.life.customview.OnItemClickListener;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.module.device.presenter.WorldTimeCityChoosePresenter;
import com.ido.life.module.device.view.IWorldTimeCityChooseView;
import com.ido.life.util.KeyboardUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorldTimeCityChooseActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u0000 H2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u0005:\u0002HIB\u0005¢\u0006\u0002\u0010\u0006J\b\u0010'\u001a\u00020\u0014H\u0014J\u0010\u0010(\u001a\u00020\u00142\u0006\u0010)\u001a\u00020\tH\u0002J\b\u0010*\u001a\u00020+H\u0014J\b\u0010,\u001a\u00020+H\u0014J\b\u0010-\u001a\u00020+H\u0014J\b\u0010.\u001a\u00020+H\u0002J\b\u0010/\u001a\u00020+H\u0002J\b\u00100\u001a\u00020+H\u0002J\b\u00101\u001a\u00020+H\u0002J\b\u00102\u001a\u00020+H\u0016J\b\u00103\u001a\u00020+H\u0016J\u0010\u00104\u001a\u00020+2\u0006\u00105\u001a\u000206H\u0016J\b\u00107\u001a\u00020+H\u0016J\u0016\u00108\u001a\u00020+2\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016J\u0010\u0010:\u001a\u00020+2\u0006\u0010;\u001a\u00020\u0014H\u0016J\b\u0010<\u001a\u00020+H\u0016J\u0016\u0010=\u001a\u00020+2\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016J\b\u0010>\u001a\u00020+H\u0016J\u001c\u0010?\u001a\u00020+2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00140$H\u0016J\u0010\u0010A\u001a\u00020+2\u0006\u0010B\u001a\u00020\u0014H\u0002J\u0012\u0010C\u001a\u00020+2\b\u0010D\u001a\u0004\u0018\u00010\u0012H\u0002J\b\u0010E\u001a\u00020+H\u0002J\b\u0010F\u001a\u00020+H\u0002J\b\u0010G\u001a\u00020+H\u0002R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0014\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/ido/life/module/device/activity/WorldTimeCityChooseActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/presenter/WorldTimeCityChoosePresenter;", "Lcom/ido/life/module/device/view/IWorldTimeCityChooseView;", "Lcom/ido/life/customview/OnItemClickListener;", "Landroid/view/View$OnClickListener;", "()V", "alphabet", "", "", "[Ljava/lang/String;", "isFromDial", "", "isZH", "mAdapter", "Lcom/ido/life/adapter/WorldTimeCityAdapter;", "mCities", "", "Lcom/ido/life/bean/WorldTimeCity;", "mCountInScreen", "", "mCurrentPosition", "mCurrentSession", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "mLayoutType", "mScrollPos", "mSearchAdapter", "Lcom/ido/life/customview/recyclerview/CommonRecyclerViewAdapter;", "mSearchCities", "mSearchHandler", "Lcom/ido/life/module/device/activity/WorldTimeCityChooseActivity$SearchHandler;", "mSearchLayoutManager", "mSelectedCities", "Ljava/util/ArrayList;", "mSessions", "Ljava/util/HashMap;", "mShouldScroll", "move", "getLayoutResId", "getSessionPosition", "it", "initData", "", "initEvent", "initLabelLanguage", "initMainRv", "initRecyclerView", "initSearchRv", "initSideBar", "initViews", "onBackPressed", "onClick", "v", "Landroid/view/View;", "onGetCityFailed", "onGetCitySuccess", "cities", "onItemClick", "position", "onSearchCityFailed", "onSearchCitySuccess", "onTakeCharacterFailed", "onTakeCharacterSuccess", "characters", "scrollToTop", "pos", "setResult", "city", "showMainLayout", "showSearchLayout", "takeCharacter", "Companion", "SearchHandler", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WorldTimeCityChooseActivity extends BaseActivity<WorldTimeCityChoosePresenter> implements IWorldTimeCityChooseView, OnItemClickListener, View.OnClickListener {
    public static final long DURATION = 300;
    public static final int MSG_SEARCH = 10;
    public static final long SEARCH_DEBOUNCE_IN_MILLION_SECOND = 500;
    public static final int TYPE_MAIN = 1;
    public static final int TYPE_SEARCH = 2;
    private HashMap _$_findViewCache;
    private boolean isFromDial;
    private boolean isZH;
    private WorldTimeCityAdapter mAdapter;
    private int mCountInScreen;
    private int mCurrentPosition;
    private String mCurrentSession;
    private LinearLayoutManager mLayoutManager;
    private CommonRecyclerViewAdapter<WorldTimeCity> mSearchAdapter;
    private SearchHandler mSearchHandler;
    private LinearLayoutManager mSearchLayoutManager;
    private HashMap<String, Integer> mSessions;
    private boolean mShouldScroll;
    private boolean move;
    private List<WorldTimeCity> mCities = new ArrayList();
    private List<WorldTimeCity> mSearchCities = new ArrayList();
    private ArrayList<WorldTimeCity> mSelectedCities = new ArrayList<>();
    private int mLayoutType = 1;
    private final String[] alphabet = {"A", FileSizeUtil.UNIT_BIT, "C", "D", "E", "F", FileSizeUtil.UNIT_GB, "H", "I", "J", FileSizeUtil.UNIT_KB, "L", FileSizeUtil.UNIT_MB, "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int mScrollPos = -1;

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
        return R.layout.activity_world_time_city_choose;
    }

    @Override // com.ido.life.module.device.view.IWorldTimeCityChooseView
    public void onSearchCityFailed() {
    }

    public static final /* synthetic */ WorldTimeCityChoosePresenter access$getMPresenter$p(WorldTimeCityChooseActivity worldTimeCityChooseActivity) {
        return (WorldTimeCityChoosePresenter) worldTimeCityChooseActivity.mPresenter;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.world_time_city_choose_title));
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.isFromDial = getIntent().getBooleanExtra("from", false);
        if (!this.isFromDial) {
            this.mSelectedCities = (ArrayList) getIntent().getSerializableExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME);
        }
        this.mSearchHandler = new SearchHandler(this);
        this.isZH = ((WorldTimeCityChoosePresenter) this.mPresenter).isZH();
        initRecyclerView();
        initSideBar();
    }

    private final void initSideBar() {
        ((CitySideBar) _$_findCachedViewById(com.ido.life.R.id.sidebar)).setMSessions(this.alphabet);
        ((CitySideBar) _$_findCachedViewById(com.ido.life.R.id.sidebar)).setOnTouchingSessionChangedListener(new CitySideBar.OnTouchSessionChangedListener() { // from class: com.ido.life.module.device.activity.WorldTimeCityChooseActivity.initSideBar.1
            @Override // com.ido.life.customview.CitySideBar.OnTouchSessionChangedListener
            public void onTouchSessionChanged(String session) {
                Intrinsics.checkParameterIsNotNull(session, "session");
                WorldTimeCityChooseActivity.this.scrollToTop(WorldTimeCityChooseActivity.this.getSessionPosition(session));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getSessionPosition(String it) {
        HashMap<String, Integer> map = this.mSessions;
        if (map == null) {
            return -1;
        }
        if (map == null) {
            Intrinsics.throwNpe();
        }
        if (!map.containsKey(it)) {
            return -1;
        }
        HashMap<String, Integer> map2 = this.mSessions;
        if (map2 == null) {
            Intrinsics.throwNpe();
        }
        Integer num = map2.get(it);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    private final void initRecyclerView() {
        initMainRv();
        initSearchRv();
    }

    private final void initSearchRv() {
        WorldTimeCityChooseActivity worldTimeCityChooseActivity = this;
        this.mSearchLayoutManager = new LinearLayoutManager(worldTimeCityChooseActivity);
        RecyclerView recyclerview_search = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview_search);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview_search, "recyclerview_search");
        recyclerview_search.setLayoutManager(this.mSearchLayoutManager);
        this.mSearchAdapter = new CommonRecyclerViewAdapter<WorldTimeCity>(worldTimeCityChooseActivity, R.layout.item_world_time_city_search, this.mSearchCities) { // from class: com.ido.life.module.device.activity.WorldTimeCityChooseActivity.initSearchRv.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder holder, WorldTimeCity city, int position) {
                Intrinsics.checkParameterIsNotNull(holder, "holder");
                Intrinsics.checkParameterIsNotNull(city, "city");
                TextView textView = (TextView) holder.getView(R.id.tvCityName);
                if (textView != null) {
                    Context context = textView.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context, "it.context");
                    textView.setTextColor(context.getResources().getColor(R.color.white));
                    textView.setText(city.getName() + (char) 65288 + city.getCountry() + (char) 65289);
                }
            }
        };
        ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview_search)).addItemDecoration(new CustomItemDecoration().color(ContextCompat.getColor(worldTimeCityChooseActivity, R.color.color_EBEEF4)).marginLeft(DipPixelUtil.dip2pxF(16.0f)).marginRight(DipPixelUtil.dip2pxF(16.0f)).height(DipPixelUtil.dip2pxF(0.5f)));
        RecyclerView recyclerview_search2 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview_search);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview_search2, "recyclerview_search");
        recyclerview_search2.setAdapter(this.mSearchAdapter);
        CommonRecyclerViewAdapter<WorldTimeCity> commonRecyclerViewAdapter = this.mSearchAdapter;
        if (commonRecyclerViewAdapter != null) {
            commonRecyclerViewAdapter.setOnItemClickListener(new MultiItemTypeAdapterForRV.OnItemClickListener() { // from class: com.ido.life.module.device.activity.WorldTimeCityChooseActivity.initSearchRv.2
                @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }

                @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    WorldTimeCityChooseActivity worldTimeCityChooseActivity2 = WorldTimeCityChooseActivity.this;
                    List list = worldTimeCityChooseActivity2.mSearchCities;
                    worldTimeCityChooseActivity2.setResult(list != null ? (WorldTimeCity) list.get(position) : null);
                }
            });
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.llSearchLayout);
        if (linearLayout != null) {
            linearLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.ido.life.module.device.activity.WorldTimeCityChooseActivity.initSearchRv.3
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
        }
    }

    private final void initMainRv() {
        WorldTimeCityChooseActivity worldTimeCityChooseActivity = this;
        this.mLayoutManager = new LinearLayoutManager(worldTimeCityChooseActivity);
        RecyclerView recyclerview = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview, "recyclerview");
        recyclerview.setLayoutManager(this.mLayoutManager);
        this.mAdapter = new WorldTimeCityAdapter(worldTimeCityChooseActivity, this.mCities, this.mSelectedCities);
        RecyclerView recyclerview2 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview2, "recyclerview");
        recyclerview2.setAdapter(this.mAdapter);
        WorldTimeCityAdapter worldTimeCityAdapter = this.mAdapter;
        if (worldTimeCityAdapter != null) {
            worldTimeCityAdapter.setOnItemClickListener(this);
        }
    }

    @Override // com.ido.life.customview.OnItemClickListener
    public void onItemClick(int position) {
        List<WorldTimeCity> list = this.mCities;
        setResult(list != null ? list.get(position) : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setResult(WorldTimeCity city) {
        if (this.isFromDial) {
            setResult(-1, new Intent(this, (Class<?>) DialDefinedFunctionActivity.class).putExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME, city));
        } else {
            setResult(-1, new Intent(this, (Class<?>) WorldTimeListActivity.class).putExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME, city));
        }
        finish();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        CommLoadingView comm_loading_view = (CommLoadingView) _$_findCachedViewById(com.ido.life.R.id.comm_loading_view);
        Intrinsics.checkExpressionValueIsNotNull(comm_loading_view, "comm_loading_view");
        comm_loading_view.setVisibility(0);
        WorldTimeCityChoosePresenter worldTimeCityChoosePresenter = (WorldTimeCityChoosePresenter) this.mPresenter;
        if (worldTimeCityChoosePresenter != null) {
            worldTimeCityChoosePresenter.getCities();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scrollToTop(int pos) {
        if (pos >= 0) {
            LinearLayoutManager linearLayoutManager = this.mLayoutManager;
            int iFindFirstVisibleItemPosition = linearLayoutManager != null ? linearLayoutManager.findFirstVisibleItemPosition() : 0;
            LinearLayoutManager linearLayoutManager2 = this.mLayoutManager;
            int iFindLastVisibleItemPosition = linearLayoutManager2 != null ? linearLayoutManager2.findLastVisibleItemPosition() : 0;
            if (pos < iFindFirstVisibleItemPosition) {
                ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview)).scrollToPosition(pos);
                return;
            }
            if (pos <= iFindLastVisibleItemPosition) {
                int i = pos - iFindFirstVisibleItemPosition;
                if (i >= 0) {
                    View childAt = ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview)).getChildAt(i);
                    Intrinsics.checkExpressionValueIsNotNull(childAt, "recyclerview.getChildAt(movePosition)");
                    ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview)).scrollBy(0, childAt.getTop());
                    return;
                }
                return;
            }
            this.mCountInScreen = iFindLastVisibleItemPosition - iFindFirstVisibleItemPosition;
            int i2 = (pos + this.mCountInScreen) - 1;
            List<WorldTimeCity> list = this.mCities;
            int size = (list != null ? list.size() : 0) - 1;
            if (i2 > size) {
                i2 = size;
            }
            ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview)).scrollToPosition(i2);
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        if (((WorldTimeCityChoosePresenter) this.mPresenter).ifSideBarNecessary()) {
            ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview)).addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.ido.life.module.device.activity.WorldTimeCityChooseActivity.initEvent.1
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    String name;
                    WorldTimeCity worldTimeCity;
                    Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
                    super.onScrolled(recyclerView, dx, dy);
                    if (((CitySideBar) WorldTimeCityChooseActivity.this._$_findCachedViewById(com.ido.life.R.id.sidebar)).getIsOnTouch()) {
                        return;
                    }
                    LinearLayoutManager linearLayoutManager = WorldTimeCityChooseActivity.this.mLayoutManager;
                    int iFindFirstVisibleItemPosition = linearLayoutManager != null ? linearLayoutManager.findFirstVisibleItemPosition() : -1;
                    if (iFindFirstVisibleItemPosition >= 0) {
                        List list = WorldTimeCityChooseActivity.this.mCities;
                        if (list == null || (worldTimeCity = (WorldTimeCity) list.get(iFindFirstVisibleItemPosition)) == null || (name = worldTimeCity.getName()) == null) {
                            name = "";
                        }
                        String firstCharacterUpperCase = WorldTimeCityChooseActivity.access$getMPresenter$p(WorldTimeCityChooseActivity.this).getFirstCharacterUpperCase(name, WorldTimeCityChooseActivity.this.isZH);
                        if (TextUtils.isEmpty(firstCharacterUpperCase) || !(!Intrinsics.areEqual(firstCharacterUpperCase, WorldTimeCityChooseActivity.this.mCurrentSession))) {
                            return;
                        }
                        WorldTimeCityChooseActivity.this.mCurrentSession = firstCharacterUpperCase;
                        CitySideBar citySideBar = (CitySideBar) WorldTimeCityChooseActivity.this._$_findCachedViewById(com.ido.life.R.id.sidebar);
                        String str = WorldTimeCityChooseActivity.this.mCurrentSession;
                        if (str == null) {
                            Intrinsics.throwNpe();
                        }
                        citySideBar.setSelectedSession(str);
                    }
                }
            });
        }
        ((EditText) _$_findCachedViewById(com.ido.life.R.id.etSearch)).addTextChangedListener(new TextWatcher() { // from class: com.ido.life.module.device.activity.WorldTimeCityChooseActivity.initEvent.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Intrinsics.checkParameterIsNotNull(charSequence, "charSequence");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Intrinsics.checkParameterIsNotNull(charSequence, "charSequence");
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                Intrinsics.checkParameterIsNotNull(s, "s");
                String string = s.toString();
                ImageView ivSearchClear = (ImageView) WorldTimeCityChooseActivity.this._$_findCachedViewById(com.ido.life.R.id.ivSearchClear);
                Intrinsics.checkExpressionValueIsNotNull(ivSearchClear, "ivSearchClear");
                ivSearchClear.setVisibility(TextUtils.isEmpty(string) ? 8 : 0);
                SearchHandler searchHandler = WorldTimeCityChooseActivity.this.mSearchHandler;
                if (searchHandler != null) {
                    if (searchHandler.hasMessages(10)) {
                        searchHandler.removeMessages(10);
                    }
                    Message messageObtain = Message.obtain();
                    messageObtain.what = 10;
                    messageObtain.obj = string;
                    searchHandler.sendMessageDelayed(messageObtain, 500L);
                }
            }
        });
        WorldTimeCityChooseActivity worldTimeCityChooseActivity = this;
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.ivSearchClear)).setOnClickListener(worldTimeCityChooseActivity);
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.llSearchHint)).setOnClickListener(worldTimeCityChooseActivity);
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tvCancel)).setOnClickListener(worldTimeCityChooseActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        int id = v.getId();
        if (id == R.id.ivSearchClear) {
            ((EditText) _$_findCachedViewById(com.ido.life.R.id.etSearch)).setText("");
        } else if (id == R.id.llSearchHint) {
            showSearchLayout();
        } else {
            if (id != R.id.tvCancel) {
                return;
            }
            showMainLayout();
        }
    }

    private final void showSearchLayout() {
        this.mLayoutType = 2;
        LinearLayout llSearchLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.llSearchLayout);
        Intrinsics.checkExpressionValueIsNotNull(llSearchLayout, "llSearchLayout");
        llSearchLayout.setAlpha(0.0f);
        LinearLayout llSearchLayout2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.llSearchLayout);
        Intrinsics.checkExpressionValueIsNotNull(llSearchLayout2, "llSearchLayout");
        llSearchLayout2.setVisibility(0);
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.llSearchLayout)).animate().alpha(1.0f).setDuration(300L).setListener(new AnimatorListenerAdapter() { // from class: com.ido.life.module.device.activity.WorldTimeCityChooseActivity.showSearchLayout.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                LinearLayout llSearchLayout3 = (LinearLayout) WorldTimeCityChooseActivity.this._$_findCachedViewById(com.ido.life.R.id.llSearchLayout);
                Intrinsics.checkExpressionValueIsNotNull(llSearchLayout3, "llSearchLayout");
                llSearchLayout3.setAlpha(1.0f);
                KeyboardUtils keyboardUtils = KeyboardUtils.INSTANCE;
                WorldTimeCityChooseActivity worldTimeCityChooseActivity = WorldTimeCityChooseActivity.this;
                WorldTimeCityChooseActivity worldTimeCityChooseActivity2 = worldTimeCityChooseActivity;
                EditText etSearch = (EditText) worldTimeCityChooseActivity._$_findCachedViewById(com.ido.life.R.id.etSearch);
                Intrinsics.checkExpressionValueIsNotNull(etSearch, "etSearch");
                keyboardUtils.showInput(worldTimeCityChooseActivity2, etSearch);
            }
        });
    }

    private final void showMainLayout() {
        this.mLayoutType = 1;
        LinearLayout llSearchLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.llSearchLayout);
        Intrinsics.checkExpressionValueIsNotNull(llSearchLayout, "llSearchLayout");
        llSearchLayout.setAlpha(1.0f);
        KeyboardUtils.INSTANCE.hideInput(this);
        ((EditText) _$_findCachedViewById(com.ido.life.R.id.etSearch)).setText("");
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.llSearchLayout)).animate().alpha(0.0f).setDuration(300L).setListener(new AnimatorListenerAdapter() { // from class: com.ido.life.module.device.activity.WorldTimeCityChooseActivity.showMainLayout.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                LinearLayout llSearchLayout2 = (LinearLayout) WorldTimeCityChooseActivity.this._$_findCachedViewById(com.ido.life.R.id.llSearchLayout);
                Intrinsics.checkExpressionValueIsNotNull(llSearchLayout2, "llSearchLayout");
                llSearchLayout2.setVisibility(8);
                LinearLayout llSearchLayout3 = (LinearLayout) WorldTimeCityChooseActivity.this._$_findCachedViewById(com.ido.life.R.id.llSearchLayout);
                Intrinsics.checkExpressionValueIsNotNull(llSearchLayout3, "llSearchLayout");
                llSearchLayout3.setAlpha(0.0f);
                List list = WorldTimeCityChooseActivity.this.mSearchCities;
                if (list != null) {
                    list.clear();
                }
                CommonRecyclerViewAdapter commonRecyclerViewAdapter = WorldTimeCityChooseActivity.this.mSearchAdapter;
                if (commonRecyclerViewAdapter != null) {
                    commonRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mLayoutType == 2) {
            showMainLayout();
        } else {
            super.onBackPressed();
        }
    }

    private final void takeCharacter() {
        if (this.mCities != null) {
            WorldTimeCityChoosePresenter worldTimeCityChoosePresenter = (WorldTimeCityChoosePresenter) this.mPresenter;
            List<WorldTimeCity> list = this.mCities;
            if (list == null) {
                Intrinsics.throwNpe();
            }
            worldTimeCityChoosePresenter.takeCharacter(list, this.isZH);
        }
    }

    @Override // com.ido.life.module.device.view.IWorldTimeCityChooseView
    public void onSearchCitySuccess(List<WorldTimeCity> cities) {
        Intrinsics.checkParameterIsNotNull(cities, "cities");
        List<WorldTimeCity> list = this.mSearchCities;
        if (list != null) {
            list.clear();
        }
        List<WorldTimeCity> list2 = this.mSearchCities;
        if (list2 != null) {
            list2.addAll(cities);
        }
        CommonRecyclerViewAdapter<WorldTimeCity> commonRecyclerViewAdapter = this.mSearchAdapter;
        if (commonRecyclerViewAdapter != null) {
            commonRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.ido.life.module.device.view.IWorldTimeCityChooseView
    public void onGetCitySuccess(List<WorldTimeCity> cities) {
        Intrinsics.checkParameterIsNotNull(cities, "cities");
        FrameLayout layout_content = (FrameLayout) _$_findCachedViewById(com.ido.life.R.id.layout_content);
        Intrinsics.checkExpressionValueIsNotNull(layout_content, "layout_content");
        layout_content.setVisibility(0);
        CommLoadingView comm_loading_view = (CommLoadingView) _$_findCachedViewById(com.ido.life.R.id.comm_loading_view);
        Intrinsics.checkExpressionValueIsNotNull(comm_loading_view, "comm_loading_view");
        comm_loading_view.setVisibility(8);
        LinearLayout layout_load_failed = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_load_failed);
        Intrinsics.checkExpressionValueIsNotNull(layout_load_failed, "layout_load_failed");
        layout_load_failed.setVisibility(8);
        List<WorldTimeCity> list = this.mCities;
        if (list != null) {
            list.clear();
        }
        List<WorldTimeCity> list2 = this.mCities;
        if (list2 != null) {
            list2.addAll(cities);
        }
        WorldTimeCityAdapter worldTimeCityAdapter = this.mAdapter;
        if (worldTimeCityAdapter != null) {
            worldTimeCityAdapter.notifyDataSetChanged();
        }
        if (((WorldTimeCityChoosePresenter) this.mPresenter).ifSideBarNecessary()) {
            takeCharacter();
        }
    }

    @Override // com.ido.life.module.device.view.IWorldTimeCityChooseView
    public void onGetCityFailed() {
        CommLoadingView comm_loading_view = (CommLoadingView) _$_findCachedViewById(com.ido.life.R.id.comm_loading_view);
        Intrinsics.checkExpressionValueIsNotNull(comm_loading_view, "comm_loading_view");
        comm_loading_view.setVisibility(8);
        FrameLayout layout_content = (FrameLayout) _$_findCachedViewById(com.ido.life.R.id.layout_content);
        Intrinsics.checkExpressionValueIsNotNull(layout_content, "layout_content");
        layout_content.setVisibility(8);
        LinearLayout layout_load_failed = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_load_failed);
        Intrinsics.checkExpressionValueIsNotNull(layout_load_failed, "layout_load_failed");
        layout_load_failed.setVisibility(0);
    }

    @Override // com.ido.life.module.device.view.IWorldTimeCityChooseView
    public void onTakeCharacterSuccess(HashMap<String, Integer> characters) {
        Intrinsics.checkParameterIsNotNull(characters, "characters");
        this.mSessions = characters;
        HashMap<String, Integer> map = this.mSessions;
        if (map != null) {
            if (map == null) {
                Intrinsics.throwNpe();
            }
            if (!map.isEmpty()) {
                CitySideBar sidebar = (CitySideBar) _$_findCachedViewById(com.ido.life.R.id.sidebar);
                Intrinsics.checkExpressionValueIsNotNull(sidebar, "sidebar");
                sidebar.setVisibility(0);
            }
        }
    }

    @Override // com.ido.life.module.device.view.IWorldTimeCityChooseView
    public void onTakeCharacterFailed() {
        CitySideBar sidebar = (CitySideBar) _$_findCachedViewById(com.ido.life.R.id.sidebar);
        Intrinsics.checkExpressionValueIsNotNull(sidebar, "sidebar");
        sidebar.setVisibility(8);
    }

    /* JADX INFO: compiled from: WorldTimeCityChooseActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/ido/life/module/device/activity/WorldTimeCityChooseActivity$SearchHandler;", "Landroid/os/Handler;", "activity", "Lcom/ido/life/module/device/activity/WorldTimeCityChooseActivity;", "(Lcom/ido/life/module/device/activity/WorldTimeCityChooseActivity;)V", "mActivity", "Ljava/lang/ref/WeakReference;", "getMActivity", "()Ljava/lang/ref/WeakReference;", "setMActivity", "(Ljava/lang/ref/WeakReference;)V", "handleMessage", "", "msg", "Landroid/os/Message;", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final class SearchHandler extends Handler {
        private WeakReference<WorldTimeCityChooseActivity> mActivity;

        public SearchHandler(WorldTimeCityChooseActivity activity) {
            Intrinsics.checkParameterIsNotNull(activity, "activity");
            this.mActivity = new WeakReference<>(activity);
        }

        public final WeakReference<WorldTimeCityChooseActivity> getMActivity() {
            return this.mActivity;
        }

        public final void setMActivity(WeakReference<WorldTimeCityChooseActivity> weakReference) {
            this.mActivity = weakReference;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            WorldTimeCityChooseActivity worldTimeCityChooseActivity;
            WorldTimeCityChoosePresenter worldTimeCityChoosePresenterAccess$getMPresenter$p;
            WorldTimeCityChooseActivity worldTimeCityChooseActivity2;
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            WeakReference<WorldTimeCityChooseActivity> weakReference = this.mActivity;
            if (weakReference == null || (worldTimeCityChooseActivity = weakReference.get()) == null || (worldTimeCityChoosePresenterAccess$getMPresenter$p = WorldTimeCityChooseActivity.access$getMPresenter$p(worldTimeCityChooseActivity)) == null) {
                return;
            }
            Object obj = msg.obj;
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
            String str = (String) obj;
            WeakReference<WorldTimeCityChooseActivity> weakReference2 = this.mActivity;
            worldTimeCityChoosePresenterAccess$getMPresenter$p.doSearch(str, (weakReference2 == null || (worldTimeCityChooseActivity2 = weakReference2.get()) == null) ? null : worldTimeCityChooseActivity2.mSelectedCities);
        }
    }
}