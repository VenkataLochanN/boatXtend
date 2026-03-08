package com.ido.life.module.device.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.common.utils.ImageLoaderUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.bean.MarketDialProxy;
import com.ido.life.customview.recyclerview.BaseGridLayoutManager;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.data.api.entity.DialMarket;
import com.ido.life.data.api.entity.DialMarketDetail;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.module.device.presenter.DialTypePresenter;
import com.ido.life.module.device.view.IDialTypeView;
import com.ido.smartrefresh.layout.SmartRefreshLayout;
import com.ido.smartrefresh.layout.api.RefreshLayout;
import com.ido.smartrefresh.layout.listener.OnLoadMoreListener;
import com.ido.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialTypeActivity extends BaseActivity<DialTypePresenter> implements MultiItemTypeAdapterForRV.OnItemClickListener, OnLoadMoreListener, OnRefreshListener, IDialTypeView {
    public static final String DIAL_TYPE = "dial_type";
    public static final int FROM_COLLECT = 2;
    public static final int FROM_MARKET = 0;
    public static final int FROM_RECORD = 1;
    public static final String FROM_TYPE = "type";
    CommonRecyclerViewAdapter<DialMarket.DialType.Dial> adapter;
    int count;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;
    private DialMarket.DialType mDialType;
    private float mIvWidth;
    private float mRadius;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    int pageId = 1;
    int pageSize = 15;
    int from_type = 0;
    List<DialMarket.DialType.Dial> mListDial = new ArrayList();

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_dial_type;
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetCurrentDial(String str) {
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialList(List<String> list) {
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialListV3(List<DialPlateParam.PlateFileInfo> list) {
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        return false;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        Resources resources = getResources();
        int i = R.dimen.sw_dp_16;
        this.mIvWidth = (ScreenUtil.getScreenW() - (resources.getDimension(R.dimen.sw_dp_16) * 4.5f)) / 3.0f;
        this.from_type = getIntent().getIntExtra("type", 0);
        this.mDialType = (DialMarket.DialType) getIntent().getSerializableExtra(DIAL_TYPE);
        if (this.mDialType == null) {
            this.mDialType = new DialMarket.DialType();
        }
        if (this.mDialType.getFaceList() == null) {
            this.mDialType.setFaceList(new ArrayList());
        }
        this.mCommLoadingView.setVisibility(0);
        Resources resources2 = getResources();
        if (!((DialTypePresenter) this.mPresenter).isBracelet()) {
            i = R.dimen.sw_dp_18;
        }
        this.mRadius = (int) resources2.getDimension(i);
        saveDialLog("表盘类型：" + this.mDialType);
        getData();
    }

    private void saveDialLog(String str) {
        ((DialTypePresenter) this.mPresenter).saveDialLog(str);
    }

    private void getData() {
        int i = this.from_type;
        if (i == 0) {
            ((DialTypePresenter) this.mPresenter).requestDialListById(this.mDialType.getId(), this.pageId, this.pageSize);
            this.mTitleBar.setTitle(getDialTypeName());
        } else if (i == 1) {
            this.mTitleBar.setTitle(R.string.user_record);
            ((DialTypePresenter) this.mPresenter).requestDialUseRecord(this.pageId, this.pageSize);
        } else {
            if (i != 2) {
                return;
            }
            ((DialTypePresenter) this.mPresenter).requestDialCollect(this.pageId, this.pageSize);
            this.mTitleBar.setTitle(R.string.user_collect);
        }
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        super.handleMessage(baseMessage);
        if (baseMessage.getType() != 400) {
            return;
        }
        ((DialTypePresenter) this.mPresenter).saveDialLog("dialtypeactivity mydial changed");
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mRefreshLayout.setOnRefreshListener(this);
        this.mRefreshLayout.setOnLoadMoreListener(this);
        final float dialImageAspectRatio = ((DialTypePresenter) this.mPresenter).getDialImageAspectRatio();
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mRecyclerView.setLayoutManager(new BaseGridLayoutManager(this, 3));
        this.adapter = new CommonRecyclerViewAdapter<DialMarket.DialType.Dial>(this, R.layout.item_dial_type, this.mListDial) { // from class: com.ido.life.module.device.activity.DialTypeActivity.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, final DialMarket.DialType.Dial dial, int i) {
                int i2;
                if (dial == null) {
                    return;
                }
                final ImageView imageView = (ImageView) commonRecyclerViewHolder.getView(R.id.iv_dial);
                final ProgressBar progressBar = (ProgressBar) commonRecyclerViewHolder.getView(R.id.dial_progress_bar);
                progressBar.setVisibility(0);
                final ImageView imageView2 = (ImageView) commonRecyclerViewHolder.getView(R.id.iv_dial_failed);
                RelativeLayout relativeLayout = (RelativeLayout) commonRecyclerViewHolder.getView(R.id.rtl_dial);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
                layoutParams.width = (int) DialTypeActivity.this.mIvWidth;
                layoutParams.height = (int) (DialTypeActivity.this.mIvWidth * dialImageAspectRatio);
                relativeLayout.setLayoutParams(layoutParams);
                if (1 == ((DialTypePresenter) DialTypeActivity.this.mPresenter).getDeviceShape()) {
                    i2 = R.drawable.dial_frame_circle_bg;
                } else {
                    i2 = ((DialTypePresenter) DialTypeActivity.this.mPresenter).isBracelet() ? R.drawable.dial_frame_bracelet_bg : R.drawable.dial_frame_watch_bg;
                }
                relativeLayout.setBackgroundResource(i2);
                DialTypeActivity.this.loadDialImage(dial, imageView, progressBar, imageView2);
                imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.DialTypeActivity.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        DialTypeActivity.this.loadDialImage(dial, imageView, progressBar, imageView2);
                    }
                });
                ((TextView) commonRecyclerViewHolder.getView(R.id.rtv_name)).setText(dial.getName());
            }
        };
        this.mRecyclerView.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadDialImage(DialMarket.DialType.Dial dial, final ImageView imageView, final ProgressBar progressBar, final ImageView imageView2) {
        MarketDialProxy marketDialProxy = new MarketDialProxy(dial);
        ImageLoaderUtil.loadImgFillet(imageView, marketDialProxy.getImageUrl(), marketDialProxy.hasRefresh(), (int) this.mRadius, new RequestListener<String, GlideDrawable>() { // from class: com.ido.life.module.device.activity.DialTypeActivity.2
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onException(Exception exc, String str, Target<GlideDrawable> target, boolean z) {
                progressBar.setVisibility(8);
                imageView2.setVisibility(0);
                ImageView imageView3 = imageView;
                boolean zIsBracelet = ((DialTypePresenter) DialTypeActivity.this.mPresenter).isBracelet();
                int i = R.drawable.dial_detail_frame_watch_bg;
                if (zIsBracelet) {
                    i = R.drawable.dial_detail_frame_circle_bg;
                } else if (((DialTypePresenter) DialTypeActivity.this.mPresenter).isBracelet()) {
                    i = R.drawable.dial_detail_frame_bracelet_bg;
                } else {
                    int i2 = (((DialTypePresenter) DialTypeActivity.this.mPresenter).getDialImageAspectRatio() > 1.0f ? 1 : (((DialTypePresenter) DialTypeActivity.this.mPresenter).getDialImageAspectRatio() == 1.0f ? 0 : -1));
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

    public String getDialTypeName() {
        int id = this.mDialType.getId();
        if (id == -2) {
            return LanguageUtil.getLanguageText(R.string.device_dial_fine_recommended);
        }
        if (id == -1) {
            return LanguageUtil.getLanguageText(R.string.device_dial_latest_online);
        }
        return this.mDialType.getName();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        int i2 = this.from_type;
        if (clickValid()) {
            if (TextUtils.equals(this.mListDial.get(i).getFaceType(), "SELF_CUSTOMIZE")) {
                SingleTopIntent singleTopIntent = null;
                if (TextUtils.isEmpty(this.mListDial.get(i).getCustomFaceType())) {
                    return;
                }
                DialMarket.DialType.Dial dial = this.mListDial.get(i);
                if (this.from_type == 2) {
                    dial.setId(dial.getFaceId());
                }
                if (this.mListDial.get(i).getCustomFaceType().equals("CUSTOM_SIMPLE")) {
                    singleTopIntent = new SingleTopIntent(this, (Class<?>) DialDefinedActivity.class);
                } else if (this.mListDial.get(i).getCustomFaceType().equals("CUSTOM_FUNCTION")) {
                    singleTopIntent = new SingleTopIntent(this, (Class<?>) DialDefinedFunctionActivity.class);
                } else if (dial.getCustomFaceType().equals("CUSTOM_PHOTO")) {
                    singleTopIntent = new SingleTopIntent(this, (Class<?>) WallpaperDialActivity.class);
                }
                if (singleTopIntent != null) {
                    singleTopIntent.putExtra("dial", new MyDialListEntity.DialInfo(dial));
                    startActivity(singleTopIntent);
                    return;
                }
                return;
            }
            DialMarket.DialType.Dial dial2 = this.mListDial.get(i);
            if (this.from_type == 2) {
                dial2.setId(dial2.getFaceId());
            }
            Intent intent = new Intent(this, (Class<?>) DialDetailActivity.class);
            intent.putExtra("dial", new MyDialListEntity.DialInfo(dial2));
            startActivity(intent);
        }
    }

    @Override // com.ido.smartrefresh.layout.listener.OnLoadMoreListener
    public void onLoadMore(RefreshLayout refreshLayout) {
        this.pageId++;
        if (this.count >= this.pageId) {
            getData();
        } else {
            this.mRefreshLayout.setNoMoreData(true);
        }
    }

    @Override // com.ido.smartrefresh.layout.listener.OnRefreshListener
    public void onRefresh(RefreshLayout refreshLayout) {
        this.mListDial.clear();
        this.pageId = 1;
        getData();
    }

    @Override // com.ido.life.module.device.view.IDialTypeView
    public void onGetDialListById(DialMarketDetail.DialInfoDetail dialInfoDetail) {
        if (dialInfoDetail != null && dialInfoDetail.getItems() != null) {
            if (this.pageId == 1) {
                this.mListDial.clear();
            }
            this.mListDial.addAll(dialInfoDetail.getItems());
            this.adapter.notifyDataSetChanged();
            this.count = (dialInfoDetail.getNumRows() / this.pageSize) + (dialInfoDetail.getNumRows() % this.pageSize == 0 ? 0 : 1);
            ((DialTypePresenter) this.mPresenter).saveDialLog("表盘列表数据：" + dialInfoDetail.toString());
            int i = this.from_type;
            if (i == 1) {
                this.mTitleBar.setTitle(getString(R.string.user_record) + "(" + dialInfoDetail.getNumRows() + ")");
            } else if (i == 2) {
                this.mTitleBar.setTitle(getString(R.string.user_collect) + "(" + dialInfoDetail.getNumRows() + ")");
            }
        }
        this.mRefreshLayout.finishRefresh();
        this.mRefreshLayout.finishLoadMore();
        if (this.count == 1) {
            this.mRefreshLayout.setNoMoreData(true);
        }
        this.mCommLoadingView.setVisibility(8);
    }
}