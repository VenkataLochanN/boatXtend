package com.ido.life.module.device.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.BitmapUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.ImageLoaderUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseFragment;
import com.ido.life.base.BaseMessage;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.bean.MyDialProxy;
import com.ido.life.constants.WallpaperDialConstants;
import com.ido.life.customview.CircleImageView;
import com.ido.life.customview.recyclerview.BaseGridLayoutManager;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.data.api.entity.DialMarket;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.module.device.activity.DialDefinedActivity;
import com.ido.life.module.device.activity.DialDefinedFunctionActivity;
import com.ido.life.module.device.activity.DialDetailActivity;
import com.ido.life.module.device.activity.DialTypeActivity;
import com.ido.life.module.device.activity.MyDialEditActivity;
import com.ido.life.module.device.activity.WallpaperDialActivity;
import com.ido.life.module.device.presenter.BaseDialPresenter;
import com.ido.life.module.device.presenter.MyDialPresenter;
import com.ido.life.module.device.view.IMyDialView;
import com.ido.life.util.WallpaperDialManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MyDialFragment extends BaseFragment<MyDialPresenter> implements IMyDialView, MultiItemTypeAdapterForRV.OnItemClickListener, View.OnClickListener {
    public static final int DIAL_DETAIL_REQUEST_CODE = 88;
    public static final String DIAL_NAME = "dial_name";

    @BindView(R.id.clv_collect)
    CustomItemLabelView clv_collect;

    @BindView(R.id.clv_record)
    CustomItemLabelView clv_record;
    private String currentDialName;
    private ArrayList<MyDialListEntity.DialInfo> datas;
    private LinearLayout llDialWidget;
    private CommonRecyclerViewAdapter<MyDialListEntity.DialInfo> mAdapter;
    private List<MyDialListEntity.DialInfo> mDataList;

    @BindView(R.id.dial_content)
    LinearLayout mDialContent;
    private boolean mIsBracelet;
    private boolean mIsCircle;

    @BindView(R.id.iv_loading)
    AppCompatImageView mIvLoading;
    private AppCompatImageView mIvWallPaper;
    private AppCompatImageView mIvWallPaperBg;
    private AppCompatImageView mIvWallPaperCircle;
    private AppCompatImageView mIvWallPaperFunction;
    private AppCompatImageView mIvWallPaperTime;
    private float mIvWidth;

    @BindView(R.id.layout_load_failed)
    LinearLayout mLayoutLoadFailed;
    private BaseGridLayoutManager mLayoutManager;
    private float mRadius;
    private float mRatio;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.rtv_memory)
    RegularTextView rTvMemory;
    private Bitmap wallPaperBitmap;
    private final List<String> mInstalledDialList = new ArrayList();
    private final List<MyDialListEntity.DialInfo> mDialInfoList = new ArrayList();
    List<DialPlateParam.PlateFileInfo> mInstalledV3DialList = new ArrayList();
    private int[] mWidgetRules = {19};
    MyDialListEntity.DialInfo wallPaperDial = null;

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_my_dial;
    }

    @Override // com.ido.life.base.BaseFragment
    protected boolean needEventBus() {
        return true;
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onDeleteDial(boolean z) {
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onDialOrder(boolean z) {
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        return false;
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void startLoading() {
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void initView() {
        super.initView();
        BLEManager.getCurrentWatchPlate();
        this.mIsBracelet = ((MyDialPresenter) this.mPresenter).isBracelet();
        float dimension = getResources().getDimension(R.dimen.sw_dp_15);
        if (this.mIsBracelet) {
            this.mRadius = getResources().getDimension(R.dimen.sw_dp_16);
            this.mIvWidth = (ScreenUtil.getScreenW() - (dimension * 5.5f)) / 4.0f;
        } else {
            this.mRadius = getResources().getDimension(R.dimen.sw_dp_18);
            this.mIvWidth = (ScreenUtil.getScreenW() - (dimension * 4.5f)) / 3.0f;
        }
        this.mRatio = ((MyDialPresenter) this.mPresenter).getDialImageAspectRatio();
        startTopAnimation();
        this.mIsCircle = 1 == ((MyDialPresenter) this.mPresenter).getDeviceShape();
        this.mDataList = new ArrayList();
        this.mLayoutManager = new BaseGridLayoutManager(this.mActivity, this.mIsBracelet ? 4 : 3);
        this.mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.ido.life.module.device.fragment.MyDialFragment.1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                if (((MyDialListEntity.DialInfo) MyDialFragment.this.mDataList.get(i)).getId() < 0) {
                    return MyDialFragment.this.mLayoutManager.getSpanCount();
                }
                return 1;
            }
        });
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mAdapter = new CommonRecyclerViewAdapter<MyDialListEntity.DialInfo>(this.mActivity, R.layout.item_my_dial, this.mDataList) { // from class: com.ido.life.module.device.fragment.MyDialFragment.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, MyDialListEntity.DialInfo dialInfo, int i) {
                MyDialFragment.this.bindData2View(commonRecyclerViewHolder, dialInfo, i);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onViewRecycled(CommonRecyclerViewHolder commonRecyclerViewHolder) {
                ImageLoaderUtil.clear((ImageView) commonRecyclerViewHolder.getView(R.id.iv_dial));
                super.onViewRecycled(commonRecyclerViewHolder);
            }
        };
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this);
        this.clv_record.setOnClickListener(this);
        this.clv_collect.setOnClickListener(this);
        ((MyDialPresenter) this.mPresenter).requestDialUseRecord();
        ((MyDialPresenter) this.mPresenter).requestDialCollect();
        if (((MyDialPresenter) this.mPresenter).isSupportWallpaper()) {
            setWallPaper();
        }
        this.clv_collect.setDrawLeftPadding(ResourceUtil.getDimens(R.dimen.sw_dp_4));
        this.clv_record.setDrawLeftPadding(ResourceUtil.getDimens(R.dimen.sw_dp_4));
    }

    private void setWallPaper() {
        int i;
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.item_wallpaper_dial, (ViewGroup) this.mRecyclerView, false);
        this.llDialWidget = (LinearLayout) viewInflate.findViewById(R.id.ll_dial_widget);
        this.mIvWallPaperBg = (AppCompatImageView) viewInflate.findViewById(R.id.iv_wallpaper_dial_bg);
        this.mIvWallPaper = (AppCompatImageView) viewInflate.findViewById(R.id.iv_wallpaper_dial);
        this.mIvWallPaperTime = (AppCompatImageView) viewInflate.findViewById(R.id.iv_dial_time);
        this.mIvWallPaperFunction = (AppCompatImageView) viewInflate.findViewById(R.id.iv_dial_function);
        this.mIvWallPaperCircle = (AppCompatImageView) viewInflate.findViewById(R.id.iv_dial_circle);
        viewInflate.findViewById(R.id.item_wall_paper).setOnClickListener(this);
        FrameLayout frameLayout = (FrameLayout) viewInflate.findViewById(R.id.layout_pic);
        if (1 == ((MyDialPresenter) this.mPresenter).getDeviceShape()) {
            i = R.drawable.dial_frame_circle_bg;
        } else {
            i = ((MyDialPresenter) this.mPresenter).isBracelet() ? R.drawable.dial_frame_bracelet_bg : R.drawable.dial_frame_watch_bg;
        }
        frameLayout.setBackgroundResource(i);
        frameLayout.setOnClickListener(this);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
        float f2 = this.mIvWidth;
        layoutParams.width = (int) f2;
        layoutParams.height = (int) (f2 * this.mRatio);
        frameLayout.setLayoutParams(layoutParams);
        this.mDialContent.addView(viewInflate);
        showWallPaper();
    }

    private void showWallPaper() {
        this.llDialWidget.setVisibility(0);
        if (this.mIsCircle) {
            this.mIvWallPaper.setImageDrawable(null);
            this.llDialWidget.setVisibility(8);
            this.mIvWallPaperBg.setVisibility(8);
            this.mIvWallPaperCircle.setVisibility(0);
        } else {
            this.llDialWidget.setVisibility(0);
            this.mIvWallPaperBg.setVisibility(0);
            this.mIvWallPaperCircle.setVisibility(8);
        }
        this.wallPaperBitmap = BitmapFactory.decodeFile(((MyDialPresenter) this.mPresenter).getWallPaperDialPath());
        Bitmap bitmap = this.wallPaperBitmap;
        if (bitmap != null) {
            if (this.mIsCircle) {
                this.mIvWallPaperCircle.setImageBitmap(bitmap);
            } else {
                this.mIvWallPaperBg.setImageBitmap(BitmapUtil.transform2CornerBitmap(bitmap, this.mRadius));
                this.mIvWallPaper.setImageDrawable(null);
            }
        } else if (this.mIsCircle) {
            this.mIvWallPaperCircle.setImageResource(R.mipmap.wallpaper_set_circle_bg);
        } else {
            this.mIvWallPaperBg.setImageResource(this.mIsBracelet ? R.mipmap.wallpaper_set_bracelet_bg : R.mipmap.wallpaper_set_photo_default);
        }
        if (((MyDialPresenter) this.mPresenter).isSupportWallpaperFunctionSetting()) {
            ((MyDialPresenter) this.mPresenter).getWallpaperDialInfo();
            this.mIvWallPaperFunction.setVisibility(0);
            this.mIvWallPaperTime.setImageResource(R.mipmap.icon_wallpaper_dial_time);
        } else {
            this.mIvWallPaperFunction.setVisibility(8);
            this.mIvWallPaperTime.setImageResource(R.mipmap.icon_dial_time);
        }
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onGetDialInfoFailed() {
        setDialTimeColor(0);
        changeDialWidgetLocation(3);
        updateWidgetType(true, 2);
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onGetWallpaperDialInfo(PhotoWallpaperOperation.ResponseInfo responseInfo) {
        List<String> wallpaperColorList = ((MyDialPresenter) this.mPresenter).getWallpaperColorList();
        int i = -1;
        int i2 = -1;
        for (int i3 = 0; i3 < wallpaperColorList.size(); i3++) {
            if (responseInfo.time_color == Long.parseLong(wallpaperColorList.get(i3).replace("#", ""), 16)) {
                i = i3;
            }
            if (responseInfo.widget_num_color == Long.parseLong(wallpaperColorList.get(i3).replace("#", ""), 16)) {
                i2 = i3;
            }
        }
        if (i >= 0) {
            setDialTimeColor(Color.parseColor(((MyDialPresenter) this.mPresenter).getWallpaperColorList().get(i)));
        }
        if (i2 >= 0 && (WallpaperDialManager.ifChangeBatteryColor() || responseInfo.widget_type != 6)) {
            setDialFunctionColor(Color.parseColor(((MyDialPresenter) this.mPresenter).getWallpaperColorList().get(i2)));
        }
        changeDialWidgetLocation(responseInfo.location);
        updateWidgetType(((MyDialPresenter) this.mPresenter).isNotSupportWallpaperFunctionEdit() || responseInfo.hide_type == 0, responseInfo.widget_type);
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onGetCollectDialNum(int i) {
        String str;
        CustomItemLabelView customItemLabelView = this.clv_collect;
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.user_collect));
        if (i > 0) {
            str = "(" + i + ")";
        } else {
            str = "";
        }
        sb.append(str);
        customItemLabelView.setTitle(sb.toString());
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onGetMyDialNum(int i) {
        String str;
        CustomItemLabelView customItemLabelView = this.clv_record;
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.user_record));
        if (i > 0) {
            str = "(" + i + ")";
        } else {
            str = "";
        }
        sb.append(str);
        customItemLabelView.setTitle(sb.toString());
    }

    private void setDialTimeColor(int i) {
        this.mIvWallPaperTime.setImageResource(R.mipmap.icon_wallpaper_dial_time);
        this.mIvWallPaperTime.setColorFilter(i);
    }

    private void setDialFunctionColor(int i) {
        this.mIvWallPaperFunction.setColorFilter(i);
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
            this.mWidgetRules = layoutRulesByLocation;
            this.llDialWidget.setGravity((i == 1 || i == 7) ? 3 : 5);
        } catch (Exception e2) {
            e2.printStackTrace();
            CommonLogUtil.printAndSave("设置表盘控件位置出错！");
        }
    }

    private void updateWidgetType(boolean z, int i) {
        this.llDialWidget.setVisibility(z ? 0 : 8);
        this.mIvWallPaperFunction.setImageResource(WallpaperDialManager.getFunctionIcon(i));
        if (WallpaperDialManager.ifChangeBatteryColor() || i != 6) {
            return;
        }
        this.mIvWallPaperFunction.clearColorFilter();
    }

    private void getWallpaperDialInfo() {
        if (!((MyDialPresenter) this.mPresenter).isSupportWallpaperFunctionSetting() || this.wallPaperBitmap == null) {
            return;
        }
        ((MyDialPresenter) this.mPresenter).getWallpaperDialInfo();
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void initData() {
        super.initData();
    }

    private void stopTopAnimation() {
        this.mIvLoading.clearAnimation();
        this.mIvLoading.setVisibility(8);
    }

    private void startTopAnimation() {
        this.mIvLoading.setVisibility(0);
        this.mIvLoading.setImageResource(R.mipmap.icon_loading_dial);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 1800.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(4000L);
        rotateAnimation.setRepeatCount(-1);
        this.mIvLoading.startAnimation(rotateAnimation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindData2View(CommonRecyclerViewHolder commonRecyclerViewHolder, final MyDialListEntity.DialInfo dialInfo, final int i) {
        int i2;
        String str;
        ImageView imageView = (ImageView) commonRecyclerViewHolder.getView(R.id.iv_dial);
        RelativeLayout relativeLayout = (RelativeLayout) commonRecyclerViewHolder.getView(R.id.rtl_dial);
        final ImageView imageView2 = (ImageView) commonRecyclerViewHolder.getView(R.id.iv_dial_bg);
        ImageView imageView3 = (ImageView) commonRecyclerViewHolder.getView(R.id.iv_dial_time);
        TextView textView = (TextView) commonRecyclerViewHolder.getView(R.id.dial_name_tv);
        commonRecyclerViewHolder.getView(R.id.view_new_version).setVisibility(dialInfo.hasNewVersion ? 0 : 8);
        CircleImageView circleImageView = (CircleImageView) commonRecyclerViewHolder.getView(R.id.iv_dial_circle);
        imageView.setVisibility(8);
        imageView3.setVisibility(8);
        circleImageView.setVisibility(8);
        imageView2.setImageDrawable(null);
        imageView2.setTag(R.id.tag_dial_item, Integer.valueOf(i));
        circleImageView.setTag(R.id.tag_dial_item, Integer.valueOf(i));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
        float f2 = this.mIvWidth;
        layoutParams.width = (int) f2;
        layoutParams.height = (int) (f2 * ((MyDialPresenter) this.mPresenter).getDialImageAspectRatio());
        relativeLayout.setLayoutParams(layoutParams);
        if (1 == ((MyDialPresenter) this.mPresenter).getDeviceShape()) {
            i2 = R.drawable.dial_frame_circle_bg;
        } else {
            i2 = ((MyDialPresenter) this.mPresenter).isBracelet() ? R.drawable.dial_frame_bracelet_bg : R.drawable.dial_frame_watch_bg;
        }
        relativeLayout.setBackgroundResource(i2);
        String otaFaceName = dialInfo.getOtaFaceName();
        Object tag = imageView2.getTag(R.id.tag_dial_item);
        boolean z = BaseDialPresenter.WALLPAPER_DIAL_NAME.equals(otaFaceName) || dialInfo.type == 2;
        textView.setText(dialInfo.getName());
        if (z) {
            if (tag == null || i != ((Integer) tag).intValue()) {
                ImageLoaderUtil.clear(imageView2);
            }
            if (this.mIsCircle) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) circleImageView.getLayoutParams();
                float f3 = this.mIvWidth;
                layoutParams2.width = (int) f3;
                layoutParams2.height = (int) (f3 * this.mRatio);
                circleImageView.setLayoutParams(layoutParams2);
                circleImageView.setVisibility(0);
            }
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(((MyDialPresenter) this.mPresenter).getWallPaperDialPathNew(otaFaceName));
            if (bitmapDecodeFile != null) {
                if (this.mIsCircle) {
                    circleImageView.setImageBitmap(bitmapDecodeFile);
                } else {
                    circleImageView.setVisibility(8);
                    imageView2.setImageBitmap(BitmapUtil.transform2CornerBitmap(bitmapDecodeFile, this.mRadius));
                }
            } else if (this.mIsCircle) {
                circleImageView.setImageResource(R.mipmap.icon_wallpaper_default_oval);
            } else {
                imageView3.setVisibility(0);
                imageView2.setImageResource(this.mIsBracelet ? R.mipmap.wallpaper_set_bracelet_bg : R.mipmap.wallpaper_set_photo_default);
                imageView.setVisibility(0);
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
                float f4 = this.mIvWidth;
                layoutParams3.width = (int) f4;
                layoutParams3.height = (int) f4;
                imageView.setLayoutParams(layoutParams3);
            }
        } else if (TextUtils.isEmpty(dialInfo.getImageUrl())) {
            ((MyDialPresenter) this.mPresenter).saveDialLog("---dialname:" + otaFaceName + "--ivdialbg:" + imageView2 + "--tag:" + tag);
            imageView2.setBackgroundResource(this.mIsCircle ? R.mipmap.ic_default_dial_oval : this.mIsBracelet ? R.mipmap.ic_default_dial_bracelet : this.mRatio > 1.0f ? R.mipmap.ic_default_dial_rectangle : R.mipmap.ic_default_dial_square);
        } else {
            ((MyDialPresenter) this.mPresenter).saveDialLog("---dialname:" + otaFaceName + "--ivdialbg:" + imageView2 + "--tag:" + tag);
            final ProgressBar progressBar = (ProgressBar) commonRecyclerViewHolder.getView(R.id.dial_progress_bar);
            progressBar.setVisibility(0);
            final ImageView imageView4 = (ImageView) commonRecyclerViewHolder.getView(R.id.iv_dial_failed);
            showImageViewbg(dialInfo, i, imageView2, progressBar, imageView4);
            imageView4.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.fragment.MyDialFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MyDialFragment.this.showImageViewbg(dialInfo, i, imageView2, progressBar, imageView4);
                }
            });
        }
        ((MyDialPresenter) this.mPresenter).saveDialLog("currentDialName:" + this.currentDialName + "---dialname:" + otaFaceName);
        if (otaFaceName == null || (str = this.currentDialName) == null || !TextUtils.equals(str.split("\\.")[0], otaFaceName.split("\\.")[0])) {
            return;
        }
        dialInfo.setCurrentDial(true);
        if (this.mIsCircle) {
            relativeLayout.setBackgroundResource(R.drawable.shape_circle_dial_selected_bg);
        } else {
            relativeLayout.setBackgroundResource(this.mIsBracelet ? R.drawable.shape_bracelet_dial_selected_bg : R.drawable.shape_dial_selected_bg);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showImageViewbg(MyDialListEntity.DialInfo dialInfo, int i, final ImageView imageView, final ProgressBar progressBar, final ImageView imageView2) {
        MyDialProxy myDialProxy = new MyDialProxy(dialInfo);
        ImageLoaderUtil.loadImgFillet(imageView, myDialProxy.getImageUrl(), myDialProxy.hasRefresh(), (int) this.mRadius, new RequestListener<String, GlideDrawable>() { // from class: com.ido.life.module.device.fragment.MyDialFragment.4
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onException(Exception exc, String str, Target<GlideDrawable> target, boolean z) {
                progressBar.setVisibility(8);
                imageView2.setVisibility(0);
                ImageView imageView3 = imageView;
                boolean z2 = MyDialFragment.this.mIsCircle;
                int i2 = R.drawable.dial_detail_frame_watch_bg;
                if (z2) {
                    i2 = R.drawable.dial_detail_frame_circle_bg;
                } else if (MyDialFragment.this.mIsBracelet) {
                    i2 = R.drawable.dial_detail_frame_bracelet_bg;
                } else {
                    int i3 = (MyDialFragment.this.mRatio > 1.0f ? 1 : (MyDialFragment.this.mRatio == 1.0f ? 0 : -1));
                }
                imageView3.setBackgroundResource(i2);
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(GlideDrawable glideDrawable, String str, Target<GlideDrawable> target, boolean z, boolean z2) {
                progressBar.setVisibility(8);
                imageView2.setVisibility(8);
                return false;
            }
        });
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void initEvent() {
        super.initEvent();
        this.mRecyclerView.setVisibility(8);
        ((MyDialPresenter) this.mPresenter).getInstalledDialInfo();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != 88 || intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra(DIAL_NAME);
        boolean booleanExtra = intent.getBooleanExtra(WallpaperDialActivity.WALLPAPER_CHANGED, false);
        if ((TextUtils.isEmpty(stringExtra) || TextUtils.equals(stringExtra, this.currentDialName)) && !booleanExtra) {
            return;
        }
        if (TextUtils.isEmpty(stringExtra)) {
            BLEManager.getCurrentWatchPlate();
        } else {
            onGetCurrentDial(stringExtra);
        }
        if (booleanExtra && ((MyDialPresenter) this.mPresenter).isSupportWallpaper()) {
            showWallPaper();
        }
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetCurrentDial(String str) {
        this.currentDialName = str;
        ((MyDialPresenter) this.mPresenter).saveDialLog("currentDialName:" + this.currentDialName);
        this.mAdapter.addAll(new ArrayList(this.mDataList));
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialList(List<String> list) {
        this.mInstalledDialList.clear();
        for (String str : list) {
            if (str.equals(BaseDialPresenter.WALLPAPER_DIAL_NAME)) {
                setWallPaperDial(str);
            } else {
                this.mInstalledDialList.add(str.replace(".iwf", ""));
            }
        }
        ((MyDialPresenter) this.mPresenter).saveDialLog("onGetInstalledDialList");
        ((MyDialPresenter) this.mPresenter).requestDialInfoFromNames(this.mInstalledDialList);
        getWallpaperDialInfo();
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialListV3(List<DialPlateParam.PlateFileInfo> list) {
        ((MyDialPresenter) this.mPresenter).saveDialLog("mydial onGetInstalledDialListV3");
        this.mInstalledDialList.clear();
        if (list != null) {
            for (DialPlateParam.PlateFileInfo plateFileInfo : list) {
                if (plateFileInfo != null) {
                    String str = plateFileInfo.name;
                    if (str.equals(BaseDialPresenter.WALLPAPER_DIAL_NAME)) {
                        setWallPaperDial(plateFileInfo.name);
                    } else {
                        this.mInstalledDialList.add(plateFileInfo.name.replace(".iwf", ""));
                    }
                }
            }
            ((MyDialPresenter) this.mPresenter).requestDialInfoFromNames(this.mInstalledDialList);
        } else {
            ((MyDialPresenter) this.mPresenter).saveDialLog("onGetInstalledDialListV3 null");
            this.mRecyclerView.setVisibility(0);
            stopTopAnimation();
            this.mLayoutLoadFailed.setVisibility(8);
            setTvMemory();
        }
        getWallpaperDialInfo();
    }

    private void setWallPaperDial(String str) {
        this.wallPaperDial = ((MyDialPresenter) this.mPresenter).getWallPaperDialNew(str);
        MyDialListEntity.DialInfo dialInfo = this.wallPaperDial;
        if (dialInfo != null) {
            dialInfo.isInstalledDial = true;
        }
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onGetMyDialList(List<MyDialListEntity.DialInfo> list) {
        this.mRecyclerView.setVisibility(0);
        stopTopAnimation();
        this.mLayoutLoadFailed.setVisibility(8);
        ((MyDialPresenter) this.mPresenter).saveDialLog("onGetMyDialList");
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            arrayList.addAll(list);
            ((MyDialPresenter) this.mPresenter).saveDialLog("onGetMyDialList adapternofify");
            this.mAdapter.addAll(arrayList);
        }
        setTvMemory();
    }

    private void setTvMemory() {
        if (LocalDataManager.getSupportFunctionInfo() != null && LocalDataManager.getSupportFunctionInfo().V3_support_watch_capacity_size_display) {
            this.rTvMemory.setText(((MyDialPresenter) this.mPresenter).getWatchUseCapacitySize() + "kb/" + ((MyDialPresenter) this.mPresenter).getWatchCapacitySize() + "kb");
            return;
        }
        this.rTvMemory.setText(((MyDialPresenter) this.mPresenter).getInstalledDialCount() + "/" + ((MyDialPresenter) this.mPresenter).getMaxDialCount());
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onGetMyDialFailed() {
        this.mRecyclerView.setVisibility(8);
        this.mLayoutLoadFailed.setVisibility(0);
        stopTopAnimation();
        ((MyDialPresenter) this.mPresenter).saveDialLog("onGetMyDialFailed");
    }

    @Override // com.ido.life.module.device.view.IMyDialView
    public void onGetDialInfoFromName(DialMarket.DialType.Dial dial) {
        if (dial == null) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "mydialpresenter", "onGetDialInfoFromName ：" + GsonUtil.toJson(dial));
        for (int i = 0; i < this.datas.size(); i++) {
            MyDialListEntity.DialInfo dialInfo = this.datas.get(i);
            if (dialInfo.getOtaFaceName() != null && !TextUtils.isEmpty(dialInfo.getOtaFaceName()) && dialInfo.getOtaFaceName().equals(dial.getOtaFaceName())) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "mydialpresenter", i + "改变了");
                dialInfo.setImageUrl(dial.getImageUrl());
                this.mAdapter.notifyItemChanged(i);
            }
        }
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        if (clickValid() && !((MyDialPresenter) this.mPresenter).isLoading() && i >= 0) {
            MyDialListEntity.DialInfo dialInfo = this.mDataList.get(i);
            if (dialInfo.getId() < 0) {
                return;
            }
            Intent singleTopIntent = null;
            if (BaseDialPresenter.WALLPAPER_DIAL_NAME.equals(dialInfo.getOtaFaceName())) {
                singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) WallpaperDialActivity.class);
            } else if (TextUtils.equals(dialInfo.getFaceType(), "SELF_CUSTOMIZE")) {
                if (TextUtils.isEmpty(dialInfo.getCustomFaceType())) {
                    return;
                }
                if (dialInfo.getCustomFaceType().equals("CUSTOM_SIMPLE")) {
                    singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) DialDefinedActivity.class);
                } else if (dialInfo.getCustomFaceType().equals("CUSTOM_FUNCTION")) {
                    singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) DialDefinedFunctionActivity.class);
                } else if (dialInfo.getCustomFaceType().equals("CUSTOM_PHOTO")) {
                    singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) WallpaperDialActivity.class);
                }
                if (singleTopIntent != null) {
                    singleTopIntent.putExtra("come_from_my_dial", true);
                    singleTopIntent.putExtra("dial", dialInfo);
                }
            } else {
                singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) DialDetailActivity.class);
                singleTopIntent.putExtra("dial", dialInfo);
                singleTopIntent.putExtra("come_from_my_dial", true);
            }
            if (singleTopIntent != null) {
                startActivityForResult(singleTopIntent, 88);
            }
        }
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        super.handleMessage(baseMessage);
        int type = baseMessage.getType();
        if (type == 400) {
            ((MyDialPresenter) this.mPresenter).saveDialLog("mydial changed");
            ((MyDialPresenter) this.mPresenter).requestDialUseRecord();
        } else if (type == 909) {
            ((MyDialPresenter) this.mPresenter).requestDialCollect();
        } else {
            if (type != 914) {
                return;
            }
            ((MyDialPresenter) this.mPresenter).saveDialLog("表盘删除，刷新列表");
            BLEManager.getCurrentWatchPlate();
            ((MyDialPresenter) this.mPresenter).getInstalledDialInfo();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (clickValid()) {
            switch (view.getId()) {
                case R.id.clv_collect /* 2131362038 */:
                    SingleTopIntent singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) DialTypeActivity.class);
                    singleTopIntent.putExtra("type", 2);
                    startActivity(singleTopIntent);
                    break;
                case R.id.clv_record /* 2131362039 */:
                    SingleTopIntent singleTopIntent2 = new SingleTopIntent(this.mActivity, (Class<?>) DialTypeActivity.class);
                    singleTopIntent2.putExtra("type", 1);
                    startActivity(singleTopIntent2);
                    break;
                case R.id.item_wall_paper /* 2131362505 */:
                case R.id.layout_pic /* 2131362853 */:
                    startActivityForResult(new SingleTopIntent(this.mActivity, (Class<?>) WallpaperDialActivity.class), 88);
                    break;
            }
        }
    }

    public void onEditMyDial() {
        startActivity(new SingleTopIntent(this.mActivity, (Class<?>) MyDialEditActivity.class));
    }
}