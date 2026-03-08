package com.ido.life.module.device.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.BitmapUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.ImageLoaderUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.base.BaseFragment;
import com.ido.life.base.BaseMessage;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.bean.MarketDialProxy;
import com.ido.life.constants.WallpaperDialConstants;
import com.ido.life.customview.recyclerview.BaseLinearLayoutManager;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.data.api.entity.DialMarket;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.module.device.activity.DialDefinedActivity;
import com.ido.life.module.device.activity.DialDefinedFunctionActivity;
import com.ido.life.module.device.activity.DialDetailActivity;
import com.ido.life.module.device.activity.DialTypeActivity;
import com.ido.life.module.device.activity.WallpaperDialActivity;
import com.ido.life.module.device.presenter.DialMarketPresenter;
import com.ido.life.module.device.view.IDialMarketView;
import com.ido.life.util.WallpaperDialManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialMarketFragment extends BaseFragment<DialMarketPresenter> implements IDialMarketView, View.OnClickListener {
    private static final int DIAL_SHOW_MAX = 7;
    View itemWallPaper;
    private LinearLayout llDialWidget;
    private CommonRecyclerViewAdapter<DialMarket.DialType> mAdapter;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;
    private String mCurrentDialName;
    private ArrayList<DialMarket.DialType> mDialList;
    private List<String> mInstalledDialNameList;
    private boolean mIsBracelet;
    private boolean mIsCircle;
    private AppCompatImageView mIvWallPaper;
    private AppCompatImageView mIvWallPaperBg;
    private AppCompatImageView mIvWallPaperCircle;
    private AppCompatImageView mIvWallPaperFunction;
    private AppCompatImageView mIvWallPaperTime;
    private int mIvWidth;
    private int mMarginStart;
    private float mRadius;
    private float mRatio;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private int[] mWidgetRules = {19};

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_dial_market;
    }

    @Override // com.ido.life.base.BaseFragment
    protected boolean needEventBus() {
        return true;
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void initData() {
        super.initData();
        this.mMarginStart = (int) getResources().getDimension(R.dimen.sw_dp_15);
        this.mIsBracelet = ((DialMarketPresenter) this.mPresenter).isBracelet();
        this.mRatio = ((DialMarketPresenter) this.mPresenter).getDialImageAspectRatio();
        if (this.mIsBracelet) {
            this.mIvWidth = (int) ((ScreenUtil.getScreenW() - (this.mMarginStart * 4)) / 3.5f);
        } else {
            this.mIvWidth = (int) ((ScreenUtil.getScreenW() - (this.mMarginStart * 4)) / 3.2f);
        }
        this.mIsCircle = 1 == ((DialMarketPresenter) this.mPresenter).getDeviceShape();
        this.mRadius = (int) getResources().getDimension(((DialMarketPresenter) this.mPresenter).isBracelet() ? R.dimen.sw_dp_16 : R.dimen.sw_dp_18);
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void initEvent() {
        super.initEvent();
        initRecyclerView();
        this.mCommLoadingView.setVisibility(0);
        initWallPaperDialView();
        this.mRecyclerView.setVisibility(8);
        ((DialMarketPresenter) this.mPresenter).requestDialList();
    }

    private void initRecyclerView() {
        this.mDialList = new ArrayList<>();
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mRecyclerView.setLayoutManager(new BaseLinearLayoutManager(getContext()));
        this.mAdapter = new CommonRecyclerViewAdapter<DialMarket.DialType>(getContext(), R.layout.item_dial_fragment, this.mDialList) { // from class: com.ido.life.module.device.fragment.DialMarketFragment.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, DialMarket.DialType dialType, int i) {
                DialMarketFragment.this.bindData2View(commonRecyclerViewHolder, dialType);
            }
        };
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    private void initWallPaperDialView() {
        int i;
        this.itemWallPaper = LayoutInflater.from(getContext()).inflate(R.layout.item_wallpaper_dial, (ViewGroup) null, false);
        this.llDialWidget = (LinearLayout) this.itemWallPaper.findViewById(R.id.ll_dial_widget);
        this.mIvWallPaperBg = (AppCompatImageView) this.itemWallPaper.findViewById(R.id.iv_wallpaper_dial_bg);
        this.mIvWallPaper = (AppCompatImageView) this.itemWallPaper.findViewById(R.id.iv_wallpaper_dial);
        this.mIvWallPaperTime = (AppCompatImageView) this.itemWallPaper.findViewById(R.id.iv_dial_time);
        this.mIvWallPaperFunction = (AppCompatImageView) this.itemWallPaper.findViewById(R.id.iv_dial_function);
        this.mIvWallPaperCircle = (AppCompatImageView) this.itemWallPaper.findViewById(R.id.iv_dial_circle);
        this.itemWallPaper.findViewById(R.id.item_wall_paper).setOnClickListener(this);
        FrameLayout frameLayout = (FrameLayout) this.itemWallPaper.findViewById(R.id.layout_pic);
        if (1 == ((DialMarketPresenter) this.mPresenter).getDeviceShape()) {
            i = R.drawable.dial_frame_circle_bg;
        } else {
            i = ((DialMarketPresenter) this.mPresenter).isBracelet() ? R.drawable.dial_frame_bracelet_bg : R.drawable.dial_frame_watch_bg;
        }
        frameLayout.setBackgroundResource(i);
        frameLayout.setOnClickListener(this);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
        int i2 = this.mIvWidth;
        layoutParams.width = i2;
        layoutParams.height = (int) (i2 * this.mRatio);
        frameLayout.setLayoutParams(layoutParams);
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
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(((DialMarketPresenter) this.mPresenter).getWallPaperDialPath());
        if (bitmapDecodeFile != null) {
            if (this.mIsCircle) {
                this.mIvWallPaperCircle.setImageBitmap(bitmapDecodeFile);
            } else {
                this.mIvWallPaperBg.setImageBitmap(BitmapUtil.transform2CornerBitmap(bitmapDecodeFile, this.mRadius));
                this.mIvWallPaper.setImageDrawable(null);
            }
        } else if (this.mIsCircle) {
            this.mIvWallPaperCircle.setImageResource(R.mipmap.wallpaper_set_circle_bg);
        } else {
            this.mIvWallPaperBg.setImageResource(this.mIsBracelet ? R.mipmap.wallpaper_set_bracelet_bg : R.mipmap.wallpaper_set_photo_default);
        }
        if (((DialMarketPresenter) this.mPresenter).isSupportWallpaperFunctionSetting()) {
            ((DialMarketPresenter) this.mPresenter).getWallpaperDialInfo();
            this.mIvWallPaperFunction.setVisibility(0);
            this.mIvWallPaperTime.setImageResource(R.mipmap.icon_wallpaper_dial_time);
        } else {
            this.mIvWallPaperFunction.setVisibility(8);
            this.mIvWallPaperTime.setImageResource(R.mipmap.icon_dial_time);
        }
    }

    @Override // com.ido.life.module.device.view.IDialMarketView
    public void onGetDialInfoFailed() {
        CommonLogUtil.printAndSave("onGetDialInfoFailed");
        setDialTimeColor(0);
        this.mIvWallPaperFunction.setImageResource(WallpaperDialManager.getFunctionIcon(2));
        setDialFunctionColor(0);
        changeDialWidgetLocation(3);
        updateWidgetType(true, 2);
    }

    @Override // com.ido.life.module.device.view.IDialMarketView
    public void onGetWallpaperDialInfo(PhotoWallpaperOperation.ResponseInfo responseInfo) {
        CommonLogUtil.printAndSave("onGetWallpaperDialInfo: " + GsonUtil.toJson(responseInfo));
        List<String> wallpaperColorList = ((DialMarketPresenter) this.mPresenter).getWallpaperColorList();
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
            setDialTimeColor(i);
        }
        if (i2 >= 0 && (WallpaperDialManager.ifChangeBatteryColor() || responseInfo.widget_type != 6)) {
            setDialFunctionColor(i2);
        }
        changeDialWidgetLocation(responseInfo.location);
        updateWidgetType(((DialMarketPresenter) this.mPresenter).isNotSupportWallpaperFunctionEdit() || responseInfo.hide_type == 0, responseInfo.widget_type);
    }

    private void setDialTimeColor(int i) {
        this.mIvWallPaperTime.setColorFilter(Color.parseColor(((DialMarketPresenter) this.mPresenter).getWallpaperColorList().get(i)));
    }

    private void setDialFunctionColor(int i) {
        this.mIvWallPaperFunction.setColorFilter(Color.parseColor(((DialMarketPresenter) this.mPresenter).getWallpaperColorList().get(i)));
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
        if (((DialMarketPresenter) this.mPresenter).isSupportWallpaperFunctionSetting()) {
            ((DialMarketPresenter) this.mPresenter).getWallpaperDialInfo();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindData2View(CommonRecyclerViewHolder commonRecyclerViewHolder, final DialMarket.DialType dialType) {
        if (dialType != null) {
            CustomItemLabelView customItemLabelView = (CustomItemLabelView) commonRecyclerViewHolder.getView(R.id.item_type);
            if (dialType.getType() == 1) {
                LinearLayout linearLayout = (LinearLayout) commonRecyclerViewHolder.getView(R.id.wallpaperDial_ll);
                linearLayout.setVisibility(0);
                ((HorizontalScrollView) commonRecyclerViewHolder.getView(R.id.dial_horizontalScrollview)).setVisibility(8);
                customItemLabelView.setVisibility(8);
                linearLayout.addView(this.itemWallPaper);
                return;
            }
            customItemLabelView.setTitleSingleLine();
            int id = dialType.getId();
            customItemLabelView.setTitle(id < 0 ? ((DialMarketPresenter) this.mPresenter).formatTypeNameById(id) : dialType.getName());
            customItemLabelView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.fragment.-$$Lambda$DialMarketFragment$r4rB4VMRYiQNU0ZIxUNVPvlDLzA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$bindData2View$0$DialMarketFragment(dialType, view);
                }
            });
            final List<DialMarket.DialType.Dial> faceList = dialType.getFaceList();
            if (faceList != null) {
                formatDialState(faceList);
                LinearLayout linearLayout2 = (LinearLayout) commonRecyclerViewHolder.getView(R.id.layout_dial_img);
                linearLayout2.removeAllViews();
                for (final int i = 0; i < Math.min(7, faceList.size()); i++) {
                    View viewCreateImageView = createImageView(i, faceList.get(i));
                    linearLayout2.addView(viewCreateImageView);
                    viewCreateImageView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.fragment.-$$Lambda$DialMarketFragment$W9HM3yKHF3YDQ4jmN2JfySzu33w
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            this.f$0.lambda$bindData2View$1$DialMarketFragment(faceList, i, dialType, view);
                        }
                    });
                }
            }
        }
    }

    public /* synthetic */ void lambda$bindData2View$0$DialMarketFragment(DialMarket.DialType dialType, View view) {
        jump2DialTypeActivity(dialType);
    }

    public /* synthetic */ void lambda$bindData2View$1$DialMarketFragment(List list, int i, DialMarket.DialType dialType, View view) {
        if (clickValid()) {
            DialMarket.DialType.Dial dial = (DialMarket.DialType.Dial) list.get(i);
            if (i == 6) {
                jump2DialTypeActivity(dialType);
                return;
            }
            if (TextUtils.equals(dial.getFaceType(), "SELF_CUSTOMIZE")) {
                SingleTopIntent singleTopIntent = null;
                if (TextUtils.isEmpty(dial.getCustomFaceType())) {
                    return;
                }
                if (dial.getCustomFaceType().equals("CUSTOM_SIMPLE")) {
                    singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) DialDefinedActivity.class);
                } else if (dial.getCustomFaceType().equals("CUSTOM_FUNCTION")) {
                    singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) DialDefinedFunctionActivity.class);
                } else if (dial.getCustomFaceType().equals("CUSTOM_PHOTO")) {
                    SingleTopIntent singleTopIntent2 = new SingleTopIntent(this.mActivity, (Class<?>) WallpaperDialActivity.class);
                    singleTopIntent2.putExtra("dial", new MyDialListEntity.DialInfo(dial));
                    startActivityForResult(singleTopIntent2, 88);
                    return;
                }
                if (singleTopIntent != null) {
                    singleTopIntent.putExtra("dial", new MyDialListEntity.DialInfo(dial));
                    startActivity(singleTopIntent);
                    return;
                }
                return;
            }
            SingleTopIntent singleTopIntent3 = new SingleTopIntent(this.mActivity, (Class<?>) DialDetailActivity.class);
            singleTopIntent3.putExtra("dial", new MyDialListEntity.DialInfo((DialMarket.DialType.Dial) list.get(i)));
            startActivity(singleTopIntent3);
        }
    }

    private void formatDialState(List<DialMarket.DialType.Dial> list) {
        if (this.mInstalledDialNameList != null) {
            for (DialMarket.DialType.Dial dial : list) {
                if (dial != null) {
                    Iterator<String> it = this.mInstalledDialNameList.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            String next = it.next();
                            if (!TextUtils.isEmpty(next)) {
                                if (!TextUtils.isEmpty(this.mCurrentDialName) && this.mCurrentDialName.contains(dial.getOtaFaceName())) {
                                    dial.isCurrentDial = true;
                                    dial.isInstalledDial = true;
                                    break;
                                } else if (next.contains(dial.getOtaFaceName())) {
                                    dial.isCurrentDial = false;
                                    dial.isInstalledDial = true;
                                    break;
                                } else {
                                    dial.isCurrentDial = false;
                                    dial.isInstalledDial = false;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void jump2DialTypeActivity(DialMarket.DialType dialType) {
        SingleTopIntent singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) DialTypeActivity.class);
        singleTopIntent.putExtra(DialTypeActivity.DIAL_TYPE, dialType);
        startActivity(singleTopIntent);
    }

    private View createImageView(int i, final DialMarket.DialType.Dial dial) {
        int i2;
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.item_dial_type, (ViewGroup) null);
        final ImageView imageView = (ImageView) viewInflate.findViewById(R.id.iv_dial);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.rtl_dial);
        viewInflate.findViewById(R.id.rtv_name).setVisibility(8);
        final ProgressBar progressBar = (ProgressBar) viewInflate.findViewById(R.id.dial_progress_bar);
        progressBar.setVisibility(0);
        final ImageView imageView2 = (ImageView) viewInflate.findViewById(R.id.iv_dial_failed);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
        int i3 = this.mIvWidth;
        layoutParams.width = i3;
        layoutParams.height = (int) (i3 * this.mRatio);
        relativeLayout.setLayoutParams(layoutParams);
        if (1 == ((DialMarketPresenter) this.mPresenter).getDeviceShape()) {
            i2 = R.drawable.dial_frame_circle_bg;
        } else {
            i2 = ((DialMarketPresenter) this.mPresenter).isBracelet() ? R.drawable.dial_frame_bracelet_bg : R.drawable.dial_frame_watch_bg;
        }
        relativeLayout.setBackgroundResource(i2);
        if (i == 6) {
            progressBar.setVisibility(8);
            relativeLayout.setBackgroundResource(0);
            if (this.mIsCircle) {
                imageView.setImageResource(R.mipmap.dial_more_circle_img);
            } else {
                imageView.setImageResource(this.mIsBracelet ? R.mipmap.dial_more_bracelet_img : R.mipmap.dial_more_watch_img);
            }
        } else {
            loadDialImage(dial, imageView, progressBar, imageView2);
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.fragment.DialMarketFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DialMarketFragment.this.loadDialImage(dial, imageView, progressBar, imageView2);
                }
            });
        }
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadDialImage(DialMarket.DialType.Dial dial, final ImageView imageView, final ProgressBar progressBar, final ImageView imageView2) {
        MarketDialProxy marketDialProxy = new MarketDialProxy(dial);
        ImageLoaderUtil.loadImgFillet(imageView, marketDialProxy.getImageUrl(), marketDialProxy.hasRefresh(), (int) this.mRadius, new RequestListener<String, GlideDrawable>() { // from class: com.ido.life.module.device.fragment.DialMarketFragment.3
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onException(Exception exc, String str, Target<GlideDrawable> target, boolean z) {
                progressBar.setVisibility(8);
                imageView2.setVisibility(0);
                ImageView imageView3 = imageView;
                boolean z2 = DialMarketFragment.this.mIsCircle;
                int i = R.drawable.dial_detail_frame_watch_bg;
                if (z2) {
                    i = R.drawable.dial_detail_frame_circle_bg;
                } else if (DialMarketFragment.this.mIsBracelet) {
                    i = R.drawable.dial_detail_frame_bracelet_bg;
                } else {
                    int i2 = (DialMarketFragment.this.mRatio > 1.0f ? 1 : (DialMarketFragment.this.mRatio == 1.0f ? 0 : -1));
                }
                imageView3.setBackgroundResource(i);
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

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (clickValid()) {
            startActivityForResult(new SingleTopIntent(this.mActivity, (Class<?>) WallpaperDialActivity.class), 88);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        CommonRecyclerViewAdapter<DialMarket.DialType> commonRecyclerViewAdapter;
        super.onActivityResult(i, i2, intent);
        if (i != 88 || intent == null) {
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra(WallpaperDialActivity.WALLPAPER_CHANGED, false);
        boolean booleanExtra2 = intent.getBooleanExtra(WallpaperDialActivity.IS_CWD, false);
        if (booleanExtra && this.mIvWallPaper != null && !booleanExtra2) {
            showWallPaper();
        }
        if (!booleanExtra2 || (commonRecyclerViewAdapter = this.mAdapter) == null) {
            return;
        }
        commonRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        CommonRecyclerViewAdapter<DialMarket.DialType> commonRecyclerViewAdapter;
        super.handleMessage(baseMessage);
        if (baseMessage.getType() == 401) {
            showWallPaper();
        } else if ((baseMessage.getType() == 917 || baseMessage.getType() == 914) && (commonRecyclerViewAdapter = this.mAdapter) != null) {
            commonRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.ido.life.module.device.view.IDialMarketView
    public void onGetDialList(List<DialMarket.DialType> list) {
        this.mCommLoadingView.setVisibility(8);
        this.mRecyclerView.setVisibility(0);
        this.mDialList.clear();
        DialMarket.DialType dialType = new DialMarket.DialType();
        dialType.setType(1);
        if (list == null) {
            if (((DialMarketPresenter) this.mPresenter).isSupportWallpaper()) {
                this.mDialList.add(dialType);
                this.mAdapter.notifyDataSetChanged();
                return;
            }
            return;
        }
        this.mDialList.addAll(list);
        if (this.mDialList.size() >= 4) {
            if (((DialMarketPresenter) this.mPresenter).isSupportWallpaper()) {
                this.mDialList.add(3, dialType);
            }
        } else if (((DialMarketPresenter) this.mPresenter).isSupportWallpaper()) {
            this.mDialList.add(dialType);
        }
        this.mAdapter.notifyDataSetChanged();
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetCurrentDial(String str) {
        this.mCurrentDialName = str;
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialList(List<String> list) {
        this.mInstalledDialNameList = list;
        getWallpaperDialInfo();
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialListV3(List<DialPlateParam.PlateFileInfo> list) {
        getWallpaperDialInfo();
    }

    private void refreshDataList() {
        if (this.mDialList.size() > 0) {
            ArrayList arrayList = new ArrayList(this.mDialList);
            this.mDialList.clear();
            this.mDialList.addAll(arrayList);
            this.mAdapter.notifyDataSetChanged();
        }
    }
}