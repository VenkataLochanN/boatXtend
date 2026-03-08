package com.ido.life.module.device.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.boat.Xtend.two.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
import com.ido.common.IdoApp;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.BitmapUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.base.GlideImageLoader;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.bean.CwdAppBean;
import com.ido.life.bean.UsageDialBean;
import com.ido.life.constants.WallpaperDialConstants;
import com.ido.life.customview.CircleImageView;
import com.ido.life.customview.OnItemClickListener;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.customview.transform.GlideRoundTransform;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.LoadingTextView;
import com.ido.life.data.ExecutorDispatcher;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.dialog.WallPaperDialFunctionSetDialog;
import com.ido.life.module.device.fragment.MyDialFragment;
import com.ido.life.module.device.presenter.BaseDialPresenter;
import com.ido.life.module.device.presenter.WallpaperDialPresenter;
import com.ido.life.module.device.view.IWallPaperDialView;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.util.AsyncTaskUtil;
import com.ido.life.util.DialogUtils;
import com.ido.life.util.ImageUtil;
import com.ido.life.util.ListUtils;
import com.ido.life.util.WallpaperDialManager;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class WallpaperDialActivity extends BaseActivity<WallpaperDialPresenter> implements IWallPaperDialView {
    private static final int COLOR_COUNT_OF_A_SCREEN = 6;
    public static final String IS_CWD = "is_cwd";
    public static final int REQUEST_CODE_DIAL_DELETE = 1000;
    public static final int REQUEST_CODE_PHOTO_CAMERA = 6;
    public static final int REQUEST_CODE_PHOTO_PICK_WALLPAPER = 20;
    public static final int REQUEST_CODE_WALLPAPER_CANCEL = 8;
    public static final int REQUEST_CODE_WALLPAPER_CROP = 7;
    public static final int REQUEST_CODE_WALLPAPER_RESULT = 9;
    private static final int TYPE_FUNCTION_COLOR = 2;
    private static final int TYPE_TIME_COLOR = 1;
    public static final String WALLPAPER_CHANGED = "wallpaper_changed";

    @BindView(R.id.ll_dial_widget)
    LinearLayout llDialWidget;

    @BindView(R.id.ll_dial_widget_preview)
    LinearLayout llDialWidgetPreview;

    @BindView(R.id.dial_load_Ll)
    LinearLayout llLoad;

    @BindView(R.id.ll_content)
    LinearLayout ll_content;

    @BindView(R.id.ll_dial_usage_history)
    LinearLayout ll_dial_usage_history;

    @BindView(R.id.ll_function)
    LinearLayout ll_function;

    @BindView(R.id.ll_time_location)
    LinearLayout ll_time_location;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;
    private int mDefaultFunction;
    private int mDefaultFunctionColorIndex;
    private boolean mDefaultFunctionShow;

    @WallpaperDialConstants.WidgetLocation
    private int mDefaultLocation;
    private int mDefaultTimeColorIndex;
    private int mDefaultUsageIndex;
    private Point mDeviceScreenSize;
    private CommBottomConfirmDialog mDialMemoryFullDialog;

    @BindView(R.id.divider_bottom)
    View mDividerBottom;

    @BindView(R.id.divider_delete)
    View mDividerDelete;

    @BindView(R.id.divider_install)
    View mDividerInstall;
    private int mFunctionColorIndex;
    private boolean mIsCircle;
    private boolean mIsRectangle;

    @BindView(R.id.item_delete_dial)
    LoadingTextView mItemDeleteDial;

    @BindView(R.id.item_install_dial)
    LoadingTextView mItemInstallDial;

    @BindView(R.id.iv_dial_circle)
    CircleImageView mIvDialCircle;

    @BindView(R.id.iv_dial_circle_preview)
    CircleImageView mIvDialCirclePreview;

    @BindView(R.id.iv_dial_function)
    AppCompatImageView mIvDialFunction;

    @BindView(R.id.iv_dial_function_preview)
    AppCompatImageView mIvDialFunctionPreview;

    @BindView(R.id.iv_dial_time)
    AppCompatImageView mIvDialTime;

    @BindView(R.id.iv_dial_time_preview)
    AppCompatImageView mIvDialTimePreview;

    @BindView(R.id.iv_loading)
    AppCompatImageView mIvLoading;

    @BindView(R.id.iv_wallpaper_dial)
    ImageView mIvWallpaperDial;

    @BindView(R.id.iv_wallpaper_dial_bg)
    AppCompatImageView mIvWallpaperDialBg;

    @BindView(R.id.iv_wallpaper_dial_bg_preview)
    AppCompatImageView mIvWallpaperDialBgPreview;

    @BindView(R.id.iv_wallpaper_dial_preview)
    ImageView mIvWallpaperDialPreview;

    @BindView(R.id.layout_add_and_install)
    LinearLayout mLayoutAddAndInstall;

    @BindView(R.id.layout_color_container)
    LinearLayout mLayoutColorContainer;

    @BindView(R.id.layout_color_select)
    LinearLayout mLayoutColorSelect;

    @BindView(R.id.layout_function_color_container)
    LinearLayout mLayoutFunctionColorContainer;

    @BindView(R.id.layout_function_color_select)
    LinearLayout mLayoutFunctionColorSelect;

    @BindView(R.id.layout_pic)
    FrameLayout mLayoutPic;

    @BindView(R.id.layout_pic_content)
    RelativeLayout mLayoutPicContent;

    @BindView(R.id.layout_pic_content_preview)
    RelativeLayout mLayoutPicContentPreview;
    private List<Integer> mLocationValues;

    @BindView(R.id.mtv_description)
    MediumTextView mMtvDescription;

    @BindView(R.id.mtv_photo)
    MediumTextView mMtvPhoto;

    @BindView(R.id.rtv_add_and_install)
    RegularTextView mRtvAddAndInstall;

    @BindView(R.id.rtv_album)
    TextView mRtvAlbum;

    @BindView(R.id.rtv_take_photo)
    TextView mRtvTakePhoto;

    @BindView(R.id.scroll_view)
    NestedScrollView mScrollView;
    private int mTimeColorIndex;
    private CommonRecyclerViewAdapter<Integer> mTimeLocationAdapter;
    private int mType;
    private UsageDialAdapter mUsageDialAdapter;

    @BindView(R.id.rv_dial_record)
    RecyclerView rv_dial_usage_history;

    @BindView(R.id.rv_time_location)
    RecyclerView rv_time_location;
    private boolean setSucceed;

    @BindView(R.id.tv_dial_size)
    TextView tvDialSize;

    @BindView(R.id.tv_function_name)
    TextView tvFunctionName;

    @BindView(R.id.tv_no_usage_history)
    TextView tvNoUsageHistory;

    @BindView(R.id.tv_color_title)
    TextView tv_color_title;

    @BindView(R.id.vLineColor)
    View vLineColor;
    private Bitmap wallPaperBgBitmap;
    private int wallPaperBgResId;
    private Bitmap wallPaperBitmap;
    private int wallPaperResId;
    private ArrayList<Integer> mFunctionList = new ArrayList<>();
    private int mFunction = 1;
    private boolean mFunctionShow = true;

    @WallpaperDialConstants.WidgetLocation
    private int mLocation = 3;
    private int mUsageIndex = -1;
    private List<UsageDialBean> usageDialList = new ArrayList();
    private int[] mWidgetRules = {19};
    private float watchCorner = 0.0f;
    private boolean hasNewWallpaperCreated = false;
    private boolean isCloudWallpaper = false;
    private boolean hasNewVersion = false;
    private int mDialNum = 5;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_wallpaper_dial;
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onAddAccountsuccess() {
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onDownloadsuccess() {
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetCurrentDial(String str) {
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialList(List<String> list) {
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onNoDataChanged() {
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        int i;
        super.initData();
        ((WallpaperDialPresenter) this.mPresenter).setDialInfo((MyDialListEntity.DialInfo) getIntent().getSerializableExtra("dial"));
        this.isCloudWallpaper = ((WallpaperDialPresenter) this.mPresenter).isCwd;
        this.watchCorner = getResources().getDimension(((WallpaperDialPresenter) this.mPresenter).isBracelet() ? R.dimen.sw_dp_16 : R.dimen.sw_dp_18);
        ((WallpaperDialPresenter) this.mPresenter).getDeviceScreenInfo();
        this.mIsCircle = 1 == ((WallpaperDialPresenter) this.mPresenter).getDeviceShape();
        this.mIsRectangle = ((WallpaperDialPresenter) this.mPresenter).getDialImageAspectRatio() > 1.0f;
        if (this.mIsCircle) {
            i = R.mipmap.ic_default_dial_oval;
        } else {
            i = ((WallpaperDialPresenter) this.mPresenter).isBracelet() ? R.mipmap.ic_default_dial_bracelet : this.mIsRectangle ? R.mipmap.ic_default_dial_rectangle : R.mipmap.ic_default_dial_square;
        }
        if (this.mIsCircle) {
            this.mIvDialCircle.setImageResource(i);
            this.mIvWallpaperDialPreview.setImageDrawable(null);
            this.mIvWallpaperDial.setImageDrawable(null);
            this.llDialWidgetPreview.setVisibility(4);
            this.llDialWidget.setVisibility(4);
            this.mIvWallpaperDialBg.setVisibility(4);
            this.mIvWallpaperDialBgPreview.setVisibility(4);
        } else {
            this.mIvDialCircle.setVisibility(4);
            this.mIvDialCirclePreview.setVisibility(4);
            this.mIvWallpaperDialBg.setImageResource(i);
            this.mIvWallpaperDialBgPreview.setImageResource(i);
        }
        ((WallpaperDialPresenter) this.mPresenter).getInstalledDialInfo();
        if (this.isCloudWallpaper) {
            this.mMtvPhoto.setText(((WallpaperDialPresenter) this.mPresenter).getWallpaperName());
            showLoadLayout();
        } else {
            hideLoadLayout();
        }
        initStatusAndView();
    }

    private void showLoadLayout() {
        this.ll_content.setVisibility(8);
    }

    private void hideLoadLayout() {
        this.ll_content.setVisibility(0);
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mTitleBar.setTitle(R.string.device_dial);
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$WallpaperDialActivity$WNbmsDvHefVo5hwQkPMmTBGgHrY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initViews$0$WallpaperDialActivity(view);
            }
        });
        updateInstallBtStatus();
        this.mLayoutPic.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.ido.life.module.device.activity.WallpaperDialActivity.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                WallpaperDialActivity.this.mLayoutPic.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) WallpaperDialActivity.this.mLayoutPic.getLayoutParams();
                layoutParams.width = ScreenUtil.getScreenW() / (((WallpaperDialPresenter) WallpaperDialActivity.this.mPresenter).isBracelet() ? 4 : 3);
                layoutParams.height = (int) (layoutParams.width * ((WallpaperDialPresenter) WallpaperDialActivity.this.mPresenter).getDialImageAspectRatio());
                WallpaperDialActivity.this.mLayoutPic.setLayoutParams(layoutParams);
            }
        });
        initImagePicker();
        this.mCommLoadingView.setVisibility(0);
        this.ll_content.setVisibility(8);
        showUsageHistoryView();
    }

    public /* synthetic */ void lambda$initViews$0$WallpaperDialActivity(View view) {
        onBackPressed();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
    }

    private void initStatusAndView() {
        int i;
        if (((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperSetting()) {
            this.mLayoutColorSelect.setVisibility(0);
            initColorSelectLayout(((WallpaperDialPresenter) this.mPresenter).getWallpaperColorList());
        } else {
            this.mLayoutColorSelect.setVisibility(8);
        }
        if (((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperFunctionColorSetting()) {
            this.mLayoutFunctionColorSelect.setVisibility(0);
            initFunctionColorSelectLayout(((WallpaperDialPresenter) this.mPresenter).getWallpaperColorList());
            this.tv_color_title.setText(getLanguageText(R.string.wallpaper_color_2));
            this.vLineColor.setVisibility(0);
        } else {
            this.tv_color_title.setText(getLanguageText(R.string.dial_defined_color_catalog));
            this.mLayoutFunctionColorSelect.setVisibility(8);
            this.vLineColor.setVisibility(8);
        }
        if (((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperTimeLocationSetting()) {
            initTimeLocationView();
            this.ll_time_location.setVisibility(0);
        } else {
            this.ll_time_location.setVisibility(8);
        }
        if (((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperFunction() || ((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperFunctionColorSetting() || ((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperTimeLocationSetting()) {
            this.mIvDialFunction.setVisibility(0);
            this.mIvDialFunctionPreview.setVisibility(0);
            this.mIvDialTime.setImageResource(R.mipmap.icon_wallpaper_dial_time);
            this.mIvDialTimePreview.setImageResource(R.mipmap.icon_wallpaper_dial_time);
        } else {
            this.mIvDialFunction.setVisibility(8);
            this.mIvDialFunctionPreview.setVisibility(8);
            this.mIvDialTime.setImageResource(R.mipmap.icon_dial_time);
            this.mIvDialTimePreview.setImageResource(R.mipmap.icon_dial_time);
        }
        if (((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperFunction()) {
            this.ll_function.setVisibility(0);
            ((WallpaperDialPresenter) this.mPresenter).getFunctionList();
        } else {
            this.ll_function.setVisibility(8);
        }
        FrameLayout frameLayout = this.mLayoutPic;
        if (1 == ((WallpaperDialPresenter) this.mPresenter).getDeviceShape()) {
            i = R.drawable.dial_frame_circle_bg;
        } else {
            i = ((WallpaperDialPresenter) this.mPresenter).isBracelet() ? R.drawable.wallpaper_dial_frame_bracelet_bg : R.drawable.wallpaper_dial_frame_watch_bg;
        }
        frameLayout.setBackgroundResource(i);
    }

    private void showUsageHistoryView() {
        if (((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperUsageHistory()) {
            initDialUsageHistoryView();
            ((WallpaperDialPresenter) this.mPresenter).getUsageDialList();
        } else {
            this.ll_dial_usage_history.setVisibility(8);
        }
    }

    private void initDialUsageHistoryView() {
        this.mUsageDialAdapter = new UsageDialAdapter(((WallpaperDialPresenter) this.mPresenter).getDialImageAspectRatio());
        this.rv_dial_usage_history.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.rv_dial_usage_history.setAdapter(this.mUsageDialAdapter);
        this.mUsageDialAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$WallpaperDialActivity$jZsO_8-5s5xyrjkJFCfu0_5Brkg
            @Override // com.ido.life.customview.OnItemClickListener
            public final void onItemClick(int i) {
                this.f$0.lambda$initDialUsageHistoryView$1$WallpaperDialActivity(i);
            }
        });
    }

    public /* synthetic */ void lambda$initDialUsageHistoryView$1$WallpaperDialActivity(int i) {
        if (this.mUsageIndex != i) {
            this.mUsageIndex = i;
            this.mUsageDialAdapter.notifyDataSetChanged();
            setWallpaperDialFromUsageList();
        }
    }

    private void setWallpaperDialFromUsageList() {
        createWallPaperByGlide(this.usageDialList.get(this.mUsageIndex).getLinkUrl());
    }

    private void initTimeLocationView() {
        this.mLocationValues = ((WallpaperDialPresenter) this.mPresenter).getTimeLocationIndex();
        int dimens = ResourceUtil.getDimens(R.dimen.sw_dp_66);
        float dialImageAspectRatio = ((WallpaperDialPresenter) this.mPresenter).getDialImageAspectRatio();
        if (!this.mIsCircle) {
            dimens = (int) (dimens * dialImageAspectRatio);
        }
        final int i = dimens;
        this.mTimeLocationAdapter = new CommonRecyclerViewAdapter<Integer>(this, R.layout.item_dial_time_location, this.mLocationValues) { // from class: com.ido.life.module.device.activity.WallpaperDialActivity.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, Integer num, int i2) {
                FrameLayout frameLayout = (FrameLayout) commonRecyclerViewHolder.getView(R.id.lay_location);
                ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
                layoutParams.height = i;
                frameLayout.setLayoutParams(layoutParams);
                TextView textView = (TextView) commonRecyclerViewHolder.getView(R.id.tv_time);
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) textView.getLayoutParams();
                layoutParams2.gravity = WallpaperDialManager.getGravityByLocation(num.intValue());
                textView.setLayoutParams(layoutParams2);
                commonRecyclerViewHolder.getView(R.id.ivWallpaperDial).setBackground(ResourceUtil.getDrawable(WallpaperDialActivity.this.mIsCircle ? R.drawable.bg_e2e3ea_circle : R.drawable.bg_e2e3ea_10_corner));
                Drawable drawable = ResourceUtil.getDrawable(WallpaperDialActivity.this.mIsCircle ? R.drawable.bg_usage_dial_circle : R.drawable.bg_usage_dial);
                DrawableCompat.setTint(drawable, ResourceUtil.getColor(((Integer) WallpaperDialActivity.this.mLocationValues.get(i2)).intValue() == WallpaperDialActivity.this.mLocation ? R.color.color_FF4A00 : R.color.translate));
                commonRecyclerViewHolder.getView(R.id.lay_location).setBackground(drawable);
            }
        };
        this.rv_time_location.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.rv_time_location.setAdapter(this.mTimeLocationAdapter);
        this.mTimeLocationAdapter.setOnItemClickListener(new MultiItemTypeAdapterForRV.OnItemClickListener() { // from class: com.ido.life.module.device.activity.WallpaperDialActivity.3
            @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i2) {
                return false;
            }

            @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i2) {
                if (WallpaperDialActivity.this.mLocation != ((Integer) WallpaperDialActivity.this.mLocationValues.get(i2)).intValue()) {
                    WallpaperDialActivity wallpaperDialActivity = WallpaperDialActivity.this;
                    wallpaperDialActivity.mLocation = ((Integer) wallpaperDialActivity.mLocationValues.get(i2)).intValue();
                    WallpaperDialActivity.this.updateWidget();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWidget() {
        CommonRecyclerViewAdapter<Integer> commonRecyclerViewAdapter = this.mTimeLocationAdapter;
        if (commonRecyclerViewAdapter != null) {
            commonRecyclerViewAdapter.notifyDataSetChanged();
        }
        changeDialWidgetLocation(this.mLocation);
        updateWidgetType();
    }

    private void initColorSelectLayout(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int dimension = (int) getResources().getDimension(R.dimen.sw_dp_3);
        int screenW = (int) ((((ScreenUtil.getScreenW() - (getResources().getDimension(R.dimen.sw_dp_17) * 2.0f)) - ((dimension * 2) * 6)) * 1.0f) / 6.0f);
        int dimension2 = (int) getResources().getDimension(R.dimen.sw_dp_6);
        this.mLayoutColorContainer.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            this.mLayoutColorContainer.addView(createImageView(1, i, screenW, dimension, dimension2));
        }
    }

    private void initFunctionColorSelectLayout(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int dimension = (int) getResources().getDimension(R.dimen.sw_dp_3);
        int screenW = (int) ((((ScreenUtil.getScreenW() - (getResources().getDimension(R.dimen.sw_dp_17) * 2.0f)) - ((dimension * 2) * 6)) * 1.0f) / 6.0f);
        int dimension2 = (int) getResources().getDimension(R.dimen.sw_dp_6);
        this.mLayoutFunctionColorContainer.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            this.mLayoutFunctionColorContainer.addView(createImageView(2, i, screenW, dimension, dimension2));
        }
    }

    private ImageView createImageView(final int i, final int i2, int i3, int i4, int i5) {
        AppCompatImageView appCompatImageView = new AppCompatImageView(IdoApp.getAppContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.width = i3;
        layoutParams.height = i3;
        layoutParams.setMarginStart((int) (i4 + (i2 == 0 ? getResources().getDimension(R.dimen.sw_dp_17) : 0.0f)));
        layoutParams.setMarginEnd(i4);
        appCompatImageView.setPadding(i5, i5, i5, i5);
        appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        appCompatImageView.setLayoutParams(layoutParams);
        Drawable drawable = getDrawable(R.drawable.shape_wallpaper_color_circle);
        if (drawable != null) {
            DrawableCompat.setTint(drawable, Color.parseColor(((WallpaperDialPresenter) this.mPresenter).getWallpaperColorList().get(i2)));
        }
        appCompatImageView.setImageDrawable(drawable);
        appCompatImageView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$WallpaperDialActivity$lq4TuVNztJR45o-Crd1DU2MR1Ew
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$createImageView$2$WallpaperDialActivity(i, i2, view);
            }
        });
        return appCompatImageView;
    }

    public /* synthetic */ void lambda$createImageView$2$WallpaperDialActivity(int i, int i2, View view) {
        if (i == 1) {
            setAllColorUnselected();
            showColorSelected(i2);
        } else {
            setAllFunctionColorUnselected();
            showFunctionColorSelected(i2);
        }
    }

    private void changeDialWidgetLocation(@WallpaperDialConstants.WidgetLocation int i) {
        CommonLogUtil.printAndSave("changeDialWidgetLocation location = " + i);
        int[] layoutRulesByLocation = WallpaperDialManager.getLayoutRulesByLocation(i);
        try {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.llDialWidget.getLayoutParams();
            if (this.mWidgetRules != null && this.mWidgetRules.length > 0) {
                for (int i2 : this.mWidgetRules) {
                    layoutParams.removeRule(i2);
                }
            }
            for (int i3 : layoutRulesByLocation) {
                layoutParams.addRule(i3, R.id.iv_wallpaper_dial_bg);
            }
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.llDialWidgetPreview.getLayoutParams();
            if (this.mWidgetRules != null && this.mWidgetRules.length > 0) {
                for (int i4 : this.mWidgetRules) {
                    layoutParams2.removeRule(i4);
                }
            }
            for (int i5 : layoutRulesByLocation) {
                layoutParams2.addRule(i5, R.id.iv_wallpaper_dial_bg_preview);
            }
            this.mWidgetRules = layoutRulesByLocation;
            int i6 = (i == 1 || i == 7) ? 3 : 5;
            this.llDialWidget.setGravity(i6);
            this.llDialWidgetPreview.setGravity(i6);
        } catch (Exception e2) {
            e2.printStackTrace();
            CommonLogUtil.printAndSave("设置表盘控件位置出错！");
        }
    }

    private void setDialTimeColor(int i) {
        this.mTimeColorIndex = i;
        this.mIvDialTime.setColorFilter(Color.parseColor(((WallpaperDialPresenter) this.mPresenter).getWallpaperColorList().get(i)));
        this.mIvDialTimePreview.setColorFilter(Color.parseColor(((WallpaperDialPresenter) this.mPresenter).getWallpaperColorList().get(i)));
    }

    public void setDialFunctionColor(int i) {
        if (!((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperFunctionColorSetting()) {
            CommonLogUtil.printAndSave("not support set function color");
            return;
        }
        this.mFunctionColorIndex = i;
        if (WallpaperDialManager.ifChangeBatteryColor() || this.mFunction != 6) {
            this.mIvDialFunction.setColorFilter(Color.parseColor(((WallpaperDialPresenter) this.mPresenter).getWallpaperColorList().get(i)));
            this.mIvDialFunctionPreview.setColorFilter(Color.parseColor(((WallpaperDialPresenter) this.mPresenter).getWallpaperColorList().get(i)));
        }
        clearFunctionColor();
    }

    private void clearFunctionColor() {
        if (WallpaperDialManager.ifChangeBatteryColor() || this.mFunction != 6) {
            return;
        }
        this.mIvDialFunction.clearColorFilter();
        this.mIvDialFunctionPreview.clearColorFilter();
    }

    private void setAllColorUnselected() {
        for (int i = 0; i < this.mLayoutColorContainer.getChildCount(); i++) {
            this.mLayoutColorContainer.getChildAt(i).setBackground(null);
        }
    }

    private void setAllFunctionColorUnselected() {
        for (int i = 0; i < this.mLayoutFunctionColorContainer.getChildCount(); i++) {
            this.mLayoutFunctionColorContainer.getChildAt(i).setBackground(null);
        }
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setShowCamera(false);
        imagePicker.setCrop(true);
        imagePicker.setSaveRectangle(false);
        imagePicker.setSelectLimit(1);
        imagePicker.setStyle(this.mIsCircle ? CropImageView.Style.CIRCLE : CropImageView.Style.RECTANGLE);
        imagePicker.setFocusWidth(getResources().getDisplayMetrics().widthPixels - 40);
        imagePicker.setFocusHeight((int) (imagePicker.getFocusWidth() * ((WallpaperDialPresenter) this.mPresenter).getDialImageAspectRatio()));
        imagePicker.setOutPutX(imagePicker.getFocusWidth());
        imagePicker.setOutPutY(imagePicker.getFocusHeight());
        imagePicker.setMultiMode(false);
    }

    @OnClick({R.id.rtv_add_and_install, R.id.rtv_album, R.id.rtv_take_photo, R.id.item_install_dial, R.id.item_delete_dial, R.id.tvFunctionEdit})
    public void onViewClicked(View view) {
        if (!((WallpaperDialPresenter) this.mPresenter).isConnected()) {
            showToast(getLanguageText(R.string.device_pls_connect_device));
        }
        if (((WallpaperDialPresenter) this.mPresenter).isSetting) {
            return;
        }
        if (((WallpaperDialPresenter) this.mPresenter).isTelephone()) {
            showToast(getLanguageText(R.string.sport_start_incalling));
            return;
        }
        switch (view.getId()) {
            case R.id.item_delete_dial /* 2131362392 */:
                ((WallpaperDialPresenter) this.mPresenter).deleteDial();
                break;
            case R.id.item_install_dial /* 2131362422 */:
                if (HomeFragmentPresenter.mIsSyncing) {
                    showToast(getLanguageText(R.string.syncing_pls_try_again_later));
                } else {
                    installOrSwitchDial();
                }
                break;
            case R.id.rtv_add_and_install /* 2131363380 */:
                if (HomeFragmentPresenter.mIsSyncing) {
                    showToast(getLanguageText(R.string.syncing_pls_try_again_later));
                } else if (isDefaultStatus()) {
                    showToast(getLanguageText(R.string.tips_wallpaper_no_pic));
                } else {
                    installWallpaperDial();
                }
                break;
            case R.id.rtv_album /* 2131363383 */:
                clickAlbum();
                break;
            case R.id.rtv_take_photo /* 2131363477 */:
                clickTakePhoto();
                break;
            case R.id.tvFunctionEdit /* 2131363741 */:
                showFunctionDialog();
                break;
        }
    }

    private void showDialMemoryFullDialog() {
        if (!((WallpaperDialPresenter) this.mPresenter).isConnected()) {
            showToast(R.string.device_pls_connect_device);
            return;
        }
        if (this.mDialMemoryFullDialog == null) {
            this.mDialMemoryFullDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.dial_memory_full), getLanguageText(R.string.dial_memory_full_tip), getLanguageText(R.string.go_settting), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$WallpaperDialActivity$2JaGSV5vFfuMfhDsU66Lsv7W3FI
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showDialMemoryFullDialog$3$WallpaperDialActivity(view);
                }
            });
        }
        this.mDialMemoryFullDialog.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showDialMemoryFullDialog$3$WallpaperDialActivity(View view) {
        goDeleteDial();
    }

    private void goDeleteDial() {
        startActivityForResult(new Intent(this, (Class<?>) MyDialEditActivity.class), 1000);
    }

    private void showFunctionDialog() {
        DialogUtils.INSTANCE.showDialFunctionSetDialog(getSupportFragmentManager(), this.mFunctionList, this.mFunction, this.mFunctionShow, this.mLocation, this.mIvWallpaperDial.getWidth(), this.mIvWallpaperDial.getHeight(), this.mIvWallpaperDialBg.getWidth(), this.mIvWallpaperDialBg.getHeight(), ((WallpaperDialPresenter) this.mPresenter).getDialImageAspectRatio(), this.wallPaperBgBitmap, this.wallPaperBgResId, this.mIsCircle ? this.wallPaperBitmap : null, this.wallPaperResId, Color.parseColor(((WallpaperDialPresenter) this.mPresenter).getWallpaperColorList().get(this.mTimeColorIndex)), Color.parseColor(((WallpaperDialPresenter) this.mPresenter).getWallpaperColorList().get(this.mFunctionColorIndex)), this.mIsCircle, ((WallpaperDialPresenter) this.mPresenter).isBracelet(), new WallPaperDialFunctionSetDialog.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$WallpaperDialActivity$MTvO2AMc22-oJw2ffI0LleVJEdI
            @Override // com.ido.life.dialog.WallPaperDialFunctionSetDialog.OnClickListener
            public final void onCompleteClick(int i, boolean z) {
                this.f$0.lambda$showFunctionDialog$4$WallpaperDialActivity(i, z);
            }
        });
    }

    public /* synthetic */ void lambda$showFunctionDialog$4$WallpaperDialActivity(int i, boolean z) {
        CommonLogUtil.d("onCompleteClick function = " + i + ", functionStatus = " + z);
        this.mFunction = i;
        this.mFunctionShow = z;
        setDialFunctionColor(this.mFunctionColorIndex);
        updateWidget();
    }

    private void updateWidgetType() {
        this.llDialWidget.setVisibility(this.mFunctionShow ? 0 : 8);
        this.mIvDialFunction.setImageResource(WallpaperDialManager.getFunctionIcon(this.mFunction));
        this.tvFunctionName.setText(WallpaperDialManager.getFunctionName(this.mFunction));
        this.llDialWidgetPreview.setVisibility(this.mFunctionShow ? 0 : 8);
        this.mIvDialFunctionPreview.setImageResource(WallpaperDialManager.getFunctionIcon(this.mFunction));
    }

    private void installOrSwitchDial() {
        if (this.isCloudWallpaper) {
            setCwdDeviceConfig(true);
            return;
        }
        if (this.mRtvAddAndInstall.isEnabled() || this.hasNewWallpaperCreated) {
            installWallpaperDial();
            return;
        }
        if (((WallpaperDialPresenter) this.mPresenter).isCurrentDial) {
            if (!((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperSetting()) {
                showToast(R.string.dial_pls_select_new_photo_to_update);
                return;
            }
            ((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperTimeLocationSetting();
            if (((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperFunctionColorSetting()) {
                int i = this.mDefaultFunctionColorIndex;
                int i2 = this.mFunctionColorIndex;
            }
            int i3 = this.mDefaultTimeColorIndex;
            int i4 = this.mTimeColorIndex;
            setDialConfig2Device();
            return;
        }
        ((WallpaperDialPresenter) this.mPresenter).switch2CurrentDial();
    }

    private void installWallpaperDial() {
        if (this.isCloudWallpaper) {
            setCwdDeviceConfig(false);
        } else {
            ((WallpaperDialPresenter) this.mPresenter).sendWallPaper2Device();
        }
    }

    private void setCwdDeviceConfig(boolean z) {
        ((WallpaperDialPresenter) this.mPresenter).setCwdDeviceConfig(z, this.hasNewWallpaperCreated, this.mFunctionColorIndex, this.mTimeColorIndex, this.mLocation, this.mFunction, !this.mFunctionShow ? 1 : 0);
    }

    private void clickTakePhoto() {
        if (!checkSelfPermission(PermissionUtil.getCameraPermission())) {
            requestPermissions(200, PermissionUtil.getCameraPermission());
            return;
        }
        SingleTopIntent singleTopIntent = new SingleTopIntent(this, (Class<?>) WallpaperImageGridActivity.class);
        singleTopIntent.putExtra("TAKE", true);
        startActivityForResult(singleTopIntent, 6);
    }

    private void clickAlbum() {
        if (checkSelfPermission(PermissionUtil.getStoragePermission())) {
            startActivityForResult(new Intent(this, (Class<?>) WallpaperImageGridActivity.class), 20);
        } else {
            requestPermissions(100, PermissionUtil.getStoragePermission());
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1000 || i2 != 1004 || intent == null) {
            return;
        }
        List list = (List) intent.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
        ((WallpaperDialPresenter) this.mPresenter).saveDialLog("用户选择照片返回：" + list);
        if (list == null || list.size() == 0) {
            return;
        }
        String str = ((ImageItem) list.get(0)).path;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (i == 20 || i == 6) {
            createWallPaper(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveDialLog(String str) {
        ((WallpaperDialPresenter) this.mPresenter).saveDialLog(str);
    }

    /* JADX INFO: renamed from: com.ido.life.module.device.activity.WallpaperDialActivity$4, reason: invalid class name */
    class AnonymousClass4 extends SimpleTarget<Bitmap> {
        AnonymousClass4() {
        }

        @Override // com.bumptech.glide.request.target.Target
        public /* bridge */ /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
            onResourceReady((Bitmap) obj, (GlideAnimation<? super Bitmap>) glideAnimation);
        }

        public void onResourceReady(final Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
            ExecutorDispatcher.getInstance().dispatch(new Runnable() { // from class: com.ido.life.module.device.activity.-$$Lambda$WallpaperDialActivity$4$761uwqZ2SKYG8YN0iP7SNGK9ek0
                @Override // java.lang.Runnable
                public final void run() throws Throwable {
                    this.f$0.lambda$onResourceReady$1$WallpaperDialActivity$4(bitmap);
                }
            });
        }

        public /* synthetic */ void lambda$onResourceReady$1$WallpaperDialActivity$4(Bitmap bitmap) throws Throwable {
            WallpaperDialActivity.this.saveDialLog("createWallPaperByGlide onResourceReady：" + WallpaperDialActivity.this.mDeviceScreenSize);
            if (WallpaperDialActivity.this.mDeviceScreenSize == null || WallpaperDialActivity.this.mDeviceScreenSize.x == 0 || WallpaperDialActivity.this.mDeviceScreenSize.y == 0) {
                WallpaperDialActivity wallpaperDialActivity = WallpaperDialActivity.this;
                wallpaperDialActivity.mDeviceScreenSize = new Point(GlMapUtil.DEVICE_DISPLAY_DPI_HIGH, (int) (((WallpaperDialPresenter) wallpaperDialActivity.mPresenter).getDialImageAspectRatio() * 320.0f));
            }
            if (bitmap.getWidth() != WallpaperDialActivity.this.mDeviceScreenSize.x || bitmap.getHeight() != WallpaperDialActivity.this.mDeviceScreenSize.y) {
                ImageUtil.saveWallPaper(Bitmap.createScaledBitmap(bitmap, WallpaperDialActivity.this.mDeviceScreenSize.x, WallpaperDialActivity.this.mDeviceScreenSize.y, true), ((WallpaperDialPresenter) WallpaperDialActivity.this.mPresenter).getTempWallpaperPath());
            } else {
                ImageUtil.saveWallPaper(bitmap, ((WallpaperDialPresenter) WallpaperDialActivity.this.mPresenter).getTempWallpaperPath());
            }
            ((WallpaperDialPresenter) WallpaperDialActivity.this.mPresenter).createWallpaper(WallpaperDialActivity.this.mIsCircle);
            ExecutorDispatcher.getInstance().dispatchOnMainThread(new Runnable() { // from class: com.ido.life.module.device.activity.-$$Lambda$WallpaperDialActivity$4$IoOnDs9UIt_DYDp9_xHayeoO3oE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$null$0$WallpaperDialActivity$4();
                }
            });
        }

        public /* synthetic */ void lambda$null$0$WallpaperDialActivity$4() {
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(((WallpaperDialPresenter) WallpaperDialActivity.this.mPresenter).getTempWallpaperPath());
            if (bitmapDecodeFile != null) {
                if (WallpaperDialActivity.this.mIsCircle) {
                    WallpaperDialActivity.this.wallPaperBitmap = bitmapDecodeFile;
                    WallpaperDialActivity.this.mIvDialCircle.setImageBitmap(WallpaperDialActivity.this.wallPaperBitmap);
                    WallpaperDialActivity.this.mIvDialCirclePreview.setImageBitmap(bitmapDecodeFile);
                } else {
                    WallpaperDialActivity.this.wallPaperResId = 0;
                    WallpaperDialActivity wallpaperDialActivity = WallpaperDialActivity.this;
                    wallpaperDialActivity.wallPaperBgBitmap = BitmapUtil.transform2CornerBitmap(bitmapDecodeFile, wallpaperDialActivity.watchCorner);
                    WallpaperDialActivity.this.mIvWallpaperDialBg.setImageBitmap(WallpaperDialActivity.this.wallPaperBgBitmap);
                    WallpaperDialActivity.this.mIvWallpaperDialBgPreview.setImageBitmap(bitmapDecodeFile);
                }
                WallpaperDialActivity.this.mIvWallpaperDial.setVisibility(4);
                WallpaperDialActivity.this.mIvWallpaperDialPreview.setVisibility(4);
                if (!((WallpaperDialPresenter) WallpaperDialActivity.this.mPresenter).installed) {
                    WallpaperDialActivity.this.updateInstallBtStatus();
                }
                WallpaperDialActivity.this.hasNewWallpaperCreated = true;
                WallpaperDialActivity.this.setWallpaperSize();
            }
        }

        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadFailed(Exception exc, Drawable drawable) {
            super.onLoadFailed(exc, drawable);
            CommonLogUtil.printAndSave("createWallPaperByGlide onLoadFailed");
        }
    }

    private void createWallPaperByGlide(String str) {
        saveDialLog("createWallPaperByGlide：" + str);
        Glide.with((FragmentActivity) this).load(str).asBitmap().into(new AnonymousClass4());
    }

    private void createWallPaper(final String str) {
        new AsyncTaskUtil().setIAsyncTaskCallBack(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.device.activity.WallpaperDialActivity.5
            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... strArr) throws Throwable {
                Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
                if (WallpaperDialActivity.this.mDeviceScreenSize == null || WallpaperDialActivity.this.mDeviceScreenSize.x == 0 || WallpaperDialActivity.this.mDeviceScreenSize.y == 0) {
                    WallpaperDialActivity wallpaperDialActivity = WallpaperDialActivity.this;
                    wallpaperDialActivity.mDeviceScreenSize = new Point(GlMapUtil.DEVICE_DISPLAY_DPI_HIGH, (int) (((WallpaperDialPresenter) wallpaperDialActivity.mPresenter).getDialImageAspectRatio() * 320.0f));
                }
                if (bitmapDecodeFile.getWidth() != WallpaperDialActivity.this.mDeviceScreenSize.x || bitmapDecodeFile.getHeight() != WallpaperDialActivity.this.mDeviceScreenSize.y) {
                    ImageUtil.saveWallPaper(Bitmap.createScaledBitmap(bitmapDecodeFile, WallpaperDialActivity.this.mDeviceScreenSize.x, WallpaperDialActivity.this.mDeviceScreenSize.y, true), ((WallpaperDialPresenter) WallpaperDialActivity.this.mPresenter).getTempWallpaperPath());
                } else {
                    ImageUtil.saveWallPaper(bitmapDecodeFile, ((WallpaperDialPresenter) WallpaperDialActivity.this.mPresenter).getTempWallpaperPath());
                }
                ((WallpaperDialPresenter) WallpaperDialActivity.this.mPresenter).createWallpaper(WallpaperDialActivity.this.mIsCircle);
                return null;
            }

            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public void onPostExecute(Object obj) {
                Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(((WallpaperDialPresenter) WallpaperDialActivity.this.mPresenter).getTempWallpaperPath());
                if (bitmapDecodeFile != null) {
                    if (WallpaperDialActivity.this.mIsCircle) {
                        WallpaperDialActivity.this.wallPaperBitmap = bitmapDecodeFile;
                        WallpaperDialActivity.this.mIvDialCircle.setImageBitmap(WallpaperDialActivity.this.wallPaperBitmap);
                        WallpaperDialActivity.this.mIvDialCirclePreview.setImageBitmap(WallpaperDialActivity.this.wallPaperBitmap);
                    } else {
                        WallpaperDialActivity.this.wallPaperResId = 0;
                        WallpaperDialActivity wallpaperDialActivity = WallpaperDialActivity.this;
                        wallpaperDialActivity.wallPaperBgBitmap = BitmapUtil.transform2CornerBitmap(bitmapDecodeFile, wallpaperDialActivity.watchCorner);
                        WallpaperDialActivity.this.mIvWallpaperDialBg.setImageBitmap(WallpaperDialActivity.this.wallPaperBgBitmap);
                        WallpaperDialActivity.this.mIvWallpaperDialBgPreview.setImageBitmap(bitmapDecodeFile);
                    }
                    WallpaperDialActivity.this.mIvWallpaperDial.setVisibility(4);
                    WallpaperDialActivity.this.mIvWallpaperDialPreview.setVisibility(4);
                    if (!((WallpaperDialPresenter) WallpaperDialActivity.this.mPresenter).installed) {
                        WallpaperDialActivity.this.updateInstallBtStatus();
                    }
                    WallpaperDialActivity.this.hasNewWallpaperCreated = true;
                    WallpaperDialActivity.this.setWallpaperSize();
                }
            }
        }).execute("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateInstallBtStatus() {
        boolean z = true;
        boolean z2 = this.isCloudWallpaper && ((WallpaperDialPresenter) this.mPresenter).hasNewVersion();
        boolean z3 = (((WallpaperDialPresenter) this.mPresenter).installed || ((WallpaperDialPresenter) this.mPresenter).isSetting || (!this.isCloudWallpaper && this.wallPaperBgBitmap == null && this.wallPaperBitmap == null)) ? false : true;
        RegularTextView regularTextView = this.mRtvAddAndInstall;
        if (!z3 && !z2 && !isDefaultStatus()) {
            z = false;
        }
        regularTextView.setEnabled(z);
    }

    private boolean isDefaultStatus() {
        boolean z = (this.isCloudWallpaper || ((WallpaperDialPresenter) this.mPresenter).installed || ((WallpaperDialPresenter) this.mPresenter).isSetting || (!this.mIsCircle ? this.wallPaperBgBitmap == null : this.wallPaperBitmap == null)) ? false : true;
        saveDialLog("isDefaultStatus = " + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWallpaperSize() {
        try {
            this.tvDialSize.setText(((WallpaperDialPresenter) this.mPresenter).getWallpaperSize());
            if (((WallpaperDialPresenter) this.mPresenter).installed) {
                return;
            }
            this.tvDialSize.setVisibility(0);
        } catch (Exception e2) {
            e2.printStackTrace();
            this.tvDialSize.setVisibility(8);
        }
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onGetFunctionListSuccess(List<Integer> list) {
        this.ll_function.setVisibility(0);
        this.mFunctionList.clear();
        this.mFunctionList.addAll(list);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onGetFunctionListFailed() {
        this.ll_function.setVisibility(8);
    }

    private void loadWallpaperImage() {
        ((WallpaperDialPresenter) this.mPresenter).loadWallpaperImage(this);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onLoadWallpaperImageSuccess(Bitmap bitmap) {
        if (bitmap != null) {
            this.mIvWallpaperDial.setImageDrawable(null);
            this.mIvWallpaperDialPreview.setImageDrawable(null);
            if (this.mIsCircle) {
                this.wallPaperBitmap = bitmap;
                this.mIvDialCircle.setImageBitmap(bitmap);
                this.mIvDialCirclePreview.setImageBitmap(bitmap);
            } else {
                this.wallPaperBgBitmap = BitmapUtil.transform2CornerBitmap(bitmap, this.watchCorner);
                this.mIvWallpaperDialBg.setImageBitmap(this.wallPaperBgBitmap);
                this.mIvWallpaperDialBgPreview.setImageBitmap(bitmap);
            }
            setWallpaperSize();
        } else if (this.mIsCircle) {
            this.wallPaperResId = R.mipmap.ic_default_dial_oval;
            this.mIvDialCircle.setImageResource(this.wallPaperResId);
            this.mIvDialCirclePreview.setImageResource(this.wallPaperResId);
        } else {
            this.wallPaperBgResId = ((WallpaperDialPresenter) this.mPresenter).isBracelet() ? R.mipmap.ic_default_dial_bracelet : this.mIsRectangle ? R.mipmap.ic_default_dial_rectangle : R.mipmap.ic_default_dial_square;
            this.mIvWallpaperDialBg.setImageResource(this.wallPaperBgResId);
            this.mIvWallpaperDialBgPreview.setImageResource(this.wallPaperBgResId);
            this.mIvWallpaperDial.setImageResource(this.wallPaperResId);
            this.mIvWallpaperDialPreview.setImageResource(this.wallPaperResId);
        }
        updateInstallBtStatus();
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onLoadWallpaperImageFailed() {
        onLoadWallpaperImageSuccess(null);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onProgress(int i, int i2) {
        updateDownloadProgress(i2);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onDownloadFailed() {
        onCwdInfoGetFailed(true);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onGetUsageDialListSuccess(List<UsageDialBean> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.usageDialList.clear();
        this.usageDialList.addAll(list);
        if (ListUtils.INSTANCE.isNotEmpty(this.usageDialList)) {
            this.ll_dial_usage_history.setVisibility(0);
            this.rv_dial_usage_history.setVisibility(0);
            this.tvNoUsageHistory.setVisibility(8);
            UsageDialAdapter usageDialAdapter = this.mUsageDialAdapter;
            if (usageDialAdapter != null) {
                usageDialAdapter.notifyDataSetChanged();
                return;
            }
            return;
        }
        this.ll_dial_usage_history.setVisibility(8);
        this.rv_dial_usage_history.setVisibility(8);
        this.tvNoUsageHistory.setVisibility(0);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onGetUsageDialListFailed() {
        this.rv_dial_usage_history.setVisibility(8);
        this.tvNoUsageHistory.setVisibility(0);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onGetDeviceScreenSize(Point point) {
        this.mDeviceScreenSize = point;
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setOutPutX(point.x);
        imagePicker.setOutPutY(point.y);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onCwdInstallStatusUpdate() {
        showViewByWallpaperStatus();
        showDialUpdate();
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onCwdInfoGetFailed(boolean z) {
        if (z) {
            showToast(R.string.chart_detail_data_load_failed);
        }
        this.mCommLoadingView.setVisibility(8);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onGetInstalledDialInfo(boolean z) {
        if (((WallpaperDialPresenter) this.mPresenter).isCwd) {
            ((WallpaperDialPresenter) this.mPresenter).requestDialDetailInfo();
            return;
        }
        if (z) {
            ((WallpaperDialPresenter) this.mPresenter).getWallpaperDialInfo();
            return;
        }
        this.mItemDeleteDial.setVisibility(8);
        this.mDividerInstall.setVisibility(8);
        this.mDividerDelete.setVisibility(8);
        loadWallpaperImage();
        showViewByWallpaperStatus();
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onGetDialInfoFailed(boolean z) {
        if (z) {
            showToast(R.string.chart_detail_data_load_failed);
        }
        if (this.isCloudWallpaper) {
            ((WallpaperDialPresenter) this.mPresenter).requestDialDetailInfo();
            return;
        }
        loadWallpaperImage();
        showWallpaperByDefaultConfig();
        showViewByWallpaperStatus();
    }

    private void showWallpaperByDefaultConfig() {
        this.mCommLoadingView.setVisibility(8);
        this.mMtvPhoto.setVisibility(0);
        this.mRtvAddAndInstall.setVisibility(0);
        this.mItemInstallDial.setVisibility(8);
        this.mItemDeleteDial.setVisibility(8);
        this.mDividerInstall.setVisibility(8);
        this.mDividerDelete.setVisibility(8);
        this.mDividerBottom.setVisibility(8);
        if (((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperSetting()) {
            setAllColorUnselected();
            this.mTimeColorIndex = 0;
            this.mDefaultFunctionColorIndex = 0;
            showColorSelected(0);
            if (((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperFunctionColorSetting()) {
                setAllFunctionColorUnselected();
                this.mFunctionColorIndex = 0;
                this.mDefaultFunctionColorIndex = 0;
                showFunctionColorSelected(0);
            }
            this.mFunction = 2;
            this.mFunctionShow = true;
            this.mLocation = 3;
            this.mDefaultFunctionShow = this.mFunctionShow;
            this.mDefaultFunction = this.mFunction;
            updateWidget();
        }
        this.ll_content.setVisibility(0);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onGetWallpaperDialInfo(PhotoWallpaperOperation.ResponseInfo responseInfo) {
        boolean z;
        CommonLogUtil.printAndSave("onGetWallpaperDialInfo：" + GsonUtil.toJson(responseInfo));
        loadWallpaperImage();
        if (((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperFunction()) {
            this.mFunctionShow = responseInfo.hide_type == 0;
            this.mFunction = responseInfo.widget_type;
            if (this.mFunction <= 0) {
                this.mFunction = 1;
            }
            this.mDefaultFunction = this.mFunction;
            this.mDefaultFunctionShow = this.mFunctionShow;
            z = true;
        } else {
            z = false;
        }
        if (((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperTimeLocationSetting()) {
            this.mLocation = responseInfo.location;
            if (this.mLocation <= 0) {
                this.mLocation = 3;
            }
            this.mDefaultLocation = this.mLocation;
            z = true;
        }
        parseColors(responseInfo.time_color, responseInfo.widget_num_color);
        if (z) {
            updateWidget();
        }
        if (((WallpaperDialPresenter) this.mPresenter).installed) {
            this.mDividerInstall.setVisibility(0);
            this.mItemDeleteDial.setVisibility(this.mDialNum > 1 ? 0 : 8);
            this.mDividerDelete.setVisibility(0);
        }
        showViewByWallpaperStatus();
    }

    private void parseColors(long j, long j2) {
        List<String> wallpaperColorList = ((WallpaperDialPresenter) this.mPresenter).getWallpaperColorList();
        setAllColorUnselected();
        setAllFunctionColorUnselected();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < wallpaperColorList.size(); i3++) {
            if (j == Long.parseLong(wallpaperColorList.get(i3).replace("#", ""), 16)) {
                i = i3;
            }
            if (j2 == Long.parseLong(wallpaperColorList.get(i3).replace("#", ""), 16)) {
                i2 = i3;
            }
        }
        this.mTimeColorIndex = i;
        this.mDefaultTimeColorIndex = i;
        showColorSelected(i);
        this.mFunctionColorIndex = i2;
        this.mDefaultFunctionColorIndex = i2;
        showFunctionColorSelected(i2);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onGetCwdConfigSuccess(CwdAppBean cwdAppBean) {
        boolean z;
        hideLoadLayout();
        refreshFuncView();
        loadWallpaperImage();
        int timeColorIndex = cwdAppBean.getSelect().getTimeColorIndex();
        int funcColorIndex = cwdAppBean.getSelect().getFuncColorIndex();
        List<String> wallpaperColorList = ((WallpaperDialPresenter) this.mPresenter).getWallpaperColorList();
        if (((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperFunction()) {
            List<Integer> function = cwdAppBean.getSelect().getFunction();
            this.mFunctionShow = ListUtils.INSTANCE.isNotEmpty(function);
            this.mFunction = ListUtils.INSTANCE.isNotEmpty(function) ? function.get(0).intValue() : 2;
            this.mDefaultFunction = this.mFunction;
            this.mDefaultFunctionShow = this.mFunctionShow;
            z = true;
        } else {
            z = false;
        }
        if (((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperTimeLocationSetting()) {
            this.mLocation = cwdAppBean.getSelect().getTimeFuncLocation();
            if (this.mLocation <= 0) {
                this.mLocation = 3;
            }
            this.mDefaultLocation = this.mLocation;
            z = true;
        }
        if (z) {
            updateWidget();
        }
        if (timeColorIndex < 0 || timeColorIndex >= wallpaperColorList.size()) {
            timeColorIndex = 0;
        }
        long jColorTo16Long = WallpaperDialManager.colorTo16Long(wallpaperColorList.get(timeColorIndex));
        if (funcColorIndex < 0 || funcColorIndex >= wallpaperColorList.size()) {
            funcColorIndex = 0;
        }
        parseColors(jColorTo16Long, WallpaperDialManager.colorTo16Long(wallpaperColorList.get(funcColorIndex)));
        if (((WallpaperDialPresenter) this.mPresenter).installed) {
            this.mDividerInstall.setVisibility(0);
            this.mItemDeleteDial.setVisibility(this.mDialNum > 1 ? 0 : 8);
            this.mDividerDelete.setVisibility(0);
        }
        showViewByWallpaperStatus();
        showDialUpdate();
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public View getPreviewView() {
        return this.mLayoutPicContentPreview;
    }

    private void refreshFuncView() {
        initStatusAndView();
    }

    private void showColorSelected(int i) {
        if (this.mLayoutColorContainer.getChildCount() > 0) {
            Drawable drawable = ResourceUtil.getDrawable(R.drawable.dial_color_checks_select);
            Drawable drawable2 = ResourceUtil.getDrawable(R.drawable.dial_color_checks_not_select);
            int i2 = 0;
            while (i2 < this.mLayoutColorContainer.getChildCount()) {
                this.mLayoutColorContainer.getChildAt(i2).setBackground(i2 == i ? drawable : drawable2);
                i2++;
            }
            setDialTimeColor(i);
        }
    }

    private void showFunctionColorSelected(int i) {
        if (this.mLayoutFunctionColorContainer.getChildCount() > 0) {
            Drawable drawable = ResourceUtil.getDrawable(R.drawable.dial_color_checks_select);
            Drawable drawable2 = ResourceUtil.getDrawable(R.drawable.dial_color_checks_not_select);
            this.mLayoutFunctionColorContainer.getChildAt(i).setBackground(drawable);
            int i2 = 0;
            while (i2 < this.mLayoutFunctionColorContainer.getChildCount()) {
                this.mLayoutFunctionColorContainer.getChildAt(i2).setBackground(i2 == i ? drawable : drawable2);
                i2++;
            }
            setDialFunctionColor(i);
        }
    }

    private void showViewByWallpaperStatus() {
        ((WallpaperDialPresenter) this.mPresenter).saveDialLog("showViewByWallpaperStatus：installed = " + ((WallpaperDialPresenter) this.mPresenter).installed + ", isSetting = " + ((WallpaperDialPresenter) this.mPresenter).isSetting);
        if (((WallpaperDialPresenter) this.mPresenter).isSetting) {
            return;
        }
        this.mCommLoadingView.setVisibility(8);
        if (((WallpaperDialPresenter) this.mPresenter).installed) {
            this.mDividerBottom.setVisibility(0);
            this.mMtvPhoto.setVisibility(0);
            this.tvDialSize.setVisibility(0);
            this.mRtvAddAndInstall.setVisibility(0);
            this.mItemInstallDial.setVisibility(0);
            this.mItemDeleteDial.setVisibility(this.mDialNum > 1 ? 0 : 8);
            this.mDividerInstall.setVisibility(0);
            this.mDividerDelete.setVisibility(0);
            this.mItemInstallDial.setText(((WallpaperDialPresenter) this.mPresenter).isCurrentDial ? R.string.dial_update_to_device : R.string.device_set_current_dial);
            this.mItemInstallDial.stopAnimation();
            updateProgressStatus(4);
            setBottomItemEnable(true);
        } else {
            this.mDividerBottom.setVisibility(8);
            this.mMtvPhoto.setVisibility(0);
            this.tvDialSize.setVisibility(0);
            this.mRtvAddAndInstall.setVisibility(0);
            this.mIvLoading.clearAnimation();
            this.mIvLoading.setVisibility(8);
            this.mItemInstallDial.setVisibility(8);
            this.mItemDeleteDial.setVisibility(8);
            this.mDividerInstall.setVisibility(8);
            this.mDividerDelete.setVisibility(8);
            this.mRtvAddAndInstall.setText(getLanguageText(R.string.public_install));
            updateProgressStatus(0);
        }
        updateInstallBtStatus();
        this.ll_content.setVisibility(0);
    }

    private void showDialUpdate() {
        try {
            if (((WallpaperDialPresenter) this.mPresenter).hasNewVersion()) {
                this.hasNewVersion = true;
                this.mRtvAddAndInstall.setEnabled(true);
                this.mRtvAddAndInstall.setText(getLanguageText(R.string.device_update_dial));
                this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_bg);
                this.mRtvAddAndInstall.setTextColor(getColor(R.color.white));
            }
        } catch (Exception e2) {
            ((WallpaperDialPresenter) this.mPresenter).saveDialLog("onGetDialInfo Exception：" + e2.toString());
        }
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onDialInstallStart() {
        updateInstallBtStatus();
        setBottomItemEnable(false);
        updateProgress(0);
    }

    private void setBottomItemEnable(boolean z) {
        this.mItemInstallDial.setEnabled(z);
        this.mItemDeleteDial.setEnabled(z);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onDialInstallProgress(int i) {
        updateProgress(i);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onDialInstallSuccess() throws Throwable {
        ((WallpaperDialPresenter) this.mPresenter).saveView(this.mLayoutPicContentPreview);
        if (((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperSetting()) {
            setDialConfig2Device();
            return;
        }
        this.setSucceed = true;
        this.hasNewWallpaperCreated = false;
        updateProgress(100);
        onBackPressed();
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onCwdInstallFailed(boolean z) {
        if (z) {
            showCmdResultToast(false);
        }
        showViewByWallpaperStatus();
        showDialUpdate();
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onCwdInstallSuccess() {
        this.hasNewWallpaperCreated = false;
        this.setSucceed = true;
        updateProgress(100);
        onBackPressed();
    }

    private void setDialConfig2Device() {
        ((WallpaperDialPresenter) this.mPresenter).setDialConfig2Device(this.mFunctionColorIndex, this.mTimeColorIndex, this.mLocation, this.mFunction, !this.mFunctionShow ? 1 : 0);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onDeviceNotEnoughStorage() {
        showDialMemoryFullDialog();
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onDialInstallFailed(boolean z) {
        showCmdResultToast(false);
        this.mRtvAddAndInstall.setEnabled(true);
        setBottomItemEnable(true);
        this.mIvLoading.clearAnimation();
        this.mIvLoading.setVisibility(8);
        this.mRtvAddAndInstall.setText(getLanguageText(R.string.public_install));
        updateProgressStatus(0);
        showViewByWallpaperStatus();
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onSwitchDialStart() {
        this.mItemInstallDial.startAnimation();
        this.mItemInstallDial.setText(R.string.device_setting);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onSwitchDialComplete(boolean z) {
        if (((WallpaperDialPresenter) this.mPresenter).isSupportWallpaperSetting()) {
            setDialConfig2Device();
            return;
        }
        setBottomItemEnable(true);
        this.mItemInstallDial.stopAnimation();
        if (z) {
            this.mItemInstallDial.setText(R.string.setting_success);
            Intent intent = new Intent();
            intent.putExtra(WALLPAPER_CHANGED, true);
            intent.putExtra(MyDialFragment.DIAL_NAME, BaseDialPresenter.WALLPAPER_DIAL_NAME);
            setResult(88, intent);
            finishAfterTransition();
            return;
        }
        showToast(R.string.setting_failed);
        this.mItemInstallDial.setText(R.string.device_set_current_dial);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onDialDeleteStart() {
        this.mItemDeleteDial.startAnimation();
        this.mItemDeleteDial.setText(R.string.device_deleting);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onDialDeleteComplete(boolean z) {
        setBottomItemEnable(true);
        this.mItemDeleteDial.stopAnimation();
        if (z) {
            this.mItemDeleteDial.setText(R.string.device_delete_success);
            Intent intent = new Intent();
            intent.putExtra(WALLPAPER_CHANGED, true);
            setResult(88, intent);
            finishAfterTransition();
            return;
        }
        showToast(R.string.device_delete_failed);
        this.mItemDeleteDial.setText(R.string.device_delete_dial);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onSetDialColorStart() {
        this.mItemInstallDial.startAnimation();
        this.mItemInstallDial.setText(R.string.device_setting);
    }

    @Override // com.ido.life.module.device.view.IWallPaperDialView
    public void onSetDialColorComplete(boolean z) {
        setBottomItemEnable(true);
        this.mItemInstallDial.stopAnimation();
        if (z) {
            this.hasNewWallpaperCreated = false;
            this.mItemInstallDial.setText(R.string.setting_success);
            Intent intent = new Intent();
            intent.putExtra(WALLPAPER_CHANGED, true);
            intent.putExtra(MyDialFragment.DIAL_NAME, BaseDialPresenter.WALLPAPER_DIAL_NAME);
            setResult(88, intent);
            finishAfterTransition();
            return;
        }
        showToast(R.string.setting_failed);
        this.mItemInstallDial.setText(((WallpaperDialPresenter) this.mPresenter).isCurrentDial ? R.string.dial_update_to_device : R.string.device_set_current_dial);
    }

    private void updateProgress(int i) {
        if (i < 100) {
            if (((WallpaperDialPresenter) this.mPresenter).installed) {
                if (i == 0) {
                    this.mItemInstallDial.startAnimation();
                }
                this.mItemInstallDial.setText(String.format(getLanguageText(R.string.device_installing_x), Integer.valueOf(i)));
                return;
            } else {
                if (i == 0) {
                    startAnimation();
                }
                this.mRtvAddAndInstall.setText(String.format(getLanguageText(R.string.device_installing_x), Integer.valueOf(i)));
                updateProgressStatus(1);
                return;
            }
        }
        if (((WallpaperDialPresenter) this.mPresenter).installed) {
            this.mItemInstallDial.stopAnimation();
            this.mItemInstallDial.setText(getLanguageText(R.string.device_install_success));
        } else {
            stopAnimation();
            this.mRtvAddAndInstall.setText(getLanguageText(R.string.device_install_success));
            updateProgressStatus(3);
        }
    }

    private void updateDownloadProgress(int i) {
        if (i < 100) {
            if (((WallpaperDialPresenter) this.mPresenter).installed) {
                if (i == 0) {
                    this.mItemInstallDial.startAnimation();
                }
                this.mItemInstallDial.setText(String.format(getLanguageText(R.string.device_downloading_x), Integer.valueOf(i)));
                return;
            } else {
                if (i == 0) {
                    startAnimation();
                }
                this.mRtvAddAndInstall.setText(String.format(getLanguageText(R.string.device_downloading_x), Integer.valueOf(i)));
                updateProgressStatus(5);
                return;
            }
        }
        if (((WallpaperDialPresenter) this.mPresenter).installed) {
            this.mItemInstallDial.stopAnimation();
            this.mItemInstallDial.setText(getLanguageText(R.string.device_download_success));
        } else {
            stopAnimation();
            this.mRtvAddAndInstall.setText(getLanguageText(R.string.device_download_success));
            updateProgressStatus(6);
        }
    }

    private void updateProgressStatus(@WallpaperDialConstants.InstallStatus int i) {
        CommonLogUtil.d("updateProgressStatus：" + i);
        if (i == 0) {
            this.mRtvAddAndInstall.setTextColor(getColor(R.color.white));
            this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_bg);
            return;
        }
        if (i == 1 || i == 2 || i == 3) {
            this.mRtvAddAndInstall.setTextColor(getColor(R.color.color_F3F3F3));
            this.mRtvAddAndInstall.setBackgroundResource(R.color.translate);
        } else if (i == 4) {
            this.mRtvAddAndInstall.setTextColor(getColor(R.color.color_D6D7DA));
            this.mRtvAddAndInstall.setBackgroundResource(R.drawable.bg_wall_paper_dial_btn_disabled);
            this.mRtvAddAndInstall.setText(getLanguageText(R.string.device_installed));
        }
    }

    private void stopAnimation() {
        this.mIvLoading.clearAnimation();
        this.mIvLoading.setImageResource(R.mipmap.icon_set_complete);
    }

    private void startAnimation() {
        this.mRtvAddAndInstall.setTextColor(getColor(R.color.color_F3F3F3));
        this.mRtvAddAndInstall.setBackgroundResource(R.color.translate);
        this.mIvLoading.setVisibility(0);
        this.mIvLoading.setImageResource(R.mipmap.icon_loading_dial);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 1800.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(4000L);
        rotateAnimation.setRepeatCount(-1);
        this.mIvLoading.startAnimation(rotateAnimation);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (((WallpaperDialPresenter) this.mPresenter).isSetting) {
            showToast(R.string.device_setting_not_exit);
            return;
        }
        if (this.setSucceed) {
            Intent intent = new Intent();
            intent.putExtra(WALLPAPER_CHANGED, true);
            intent.putExtra(MyDialFragment.DIAL_NAME, BaseDialPresenter.WALLPAPER_DIAL_NAME);
            intent.putExtra(IS_CWD, this.isCloudWallpaper);
            setResult(88, intent);
        }
        finish();
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        super.handleMessage(baseMessage);
        if (baseMessage.getType() == 101) {
            if (this.mDeviceScreenSize == null) {
                ((WallpaperDialPresenter) this.mPresenter).getDeviceScreenInfo();
            }
        } else {
            if (baseMessage.getType() != 914 || baseMessage.getData() == null) {
                return;
            }
            String str = (String) baseMessage.getData();
            ((WallpaperDialPresenter) this.mPresenter).saveDialLog(str + "：表盘被删除了~");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            ((WallpaperDialPresenter) this.mPresenter).getInstalledDialInfo();
        }
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialListV3(List<DialPlateParam.PlateFileInfo> list) {
        if (!list.isEmpty()) {
            this.mDialNum = list.size();
        } else {
            this.mDialNum = 1;
        }
        if (((WallpaperDialPresenter) this.mPresenter).installed) {
            this.mItemDeleteDial.setVisibility(this.mDialNum > 1 ? 0 : 8);
            this.mDividerDelete.setVisibility(0);
        }
    }

    class UsageDialAdapter extends RecyclerView.Adapter<ViewHolder> {
        private boolean isBracelet;
        private float mDialImageAspectRatio;
        private OnItemClickListener onItemClickListener;

        /* JADX INFO: Access modifiers changed from: private */
        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public UsageDialAdapter(float f2) {
            this.mDialImageAspectRatio = 1.0f;
            this.isBracelet = false;
            this.mDialImageAspectRatio = f2;
            CommonLogUtil.d("mDialImageAspectRatio = " + this.mDialImageAspectRatio);
            this.isBracelet = ((WallpaperDialPresenter) WallpaperDialActivity.this.mPresenter).isBracelet();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(WallpaperDialActivity.this).inflate(R.layout.item_wall_paper_usage_dial, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            UsageDialBean usageDialBean = (UsageDialBean) WallpaperDialActivity.this.usageDialList.get(i);
            Drawable drawable = ResourceUtil.getDrawable(R.drawable.bg_usage_dial);
            DrawableCompat.setTint(drawable, ResourceUtil.getColor(i == WallpaperDialActivity.this.mUsageIndex ? R.color.color_FF4A00 : R.color.translate));
            viewHolder.layout.setBackground(drawable);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) viewHolder.layout.getLayoutParams();
            if (this.mDialImageAspectRatio == 0.0f) {
                layoutParams.width = layoutParams.height / (this.isBracelet ? 4 : 3);
            } else {
                layoutParams.width = (int) (layoutParams.height / this.mDialImageAspectRatio);
            }
            viewHolder.layout.setLayoutParams(layoutParams);
            Glide.with((FragmentActivity) WallpaperDialActivity.this).load(usageDialBean.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).transform(new GlideRoundTransform(viewHolder.ivWallpaperDial.getContext(), 10)).into(viewHolder.ivWallpaperDial);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (WallpaperDialActivity.this.usageDialList != null) {
                return WallpaperDialActivity.this.usageDialList.size();
            }
            return 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView ivWallpaperDial;
            FrameLayout layout;

            public ViewHolder(View view) {
                super(view);
                this.layout = (FrameLayout) view.findViewById(R.id.layout);
                this.ivWallpaperDial = (ImageView) view.findViewById(R.id.ivWallpaperDial);
                view.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$WallpaperDialActivity$UsageDialAdapter$ViewHolder$D8eslNrcUjPtt8yRDqGZhBfaUjU
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f$0.lambda$new$0$WallpaperDialActivity$UsageDialAdapter$ViewHolder(view2);
                    }
                });
            }

            public /* synthetic */ void lambda$new$0$WallpaperDialActivity$UsageDialAdapter$ViewHolder(View view) {
                if (UsageDialAdapter.this.onItemClickListener != null) {
                    UsageDialAdapter.this.onItemClickListener.onItemClick(getAdapterPosition());
                }
            }
        }
    }
}