package com.ido.life.module.device.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.common.utils.BitmapUtil;
import com.ido.common.utils.ImageLoaderUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.MyDialProxy;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.module.device.fragment.DialMarketFragment;
import com.ido.life.module.device.presenter.BaseDialPresenter;
import com.ido.life.module.device.presenter.DialMarketActivityPresenter;
import com.ido.life.module.device.view.IDialMarketActivityView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialMarketActivity extends BaseActivity<DialMarketActivityPresenter> implements IDialMarketActivityView {

    @BindView(R.id.device_intall_count_tv)
    TextView device_intall_count_tv;
    int height;
    private boolean mIsBracelet;
    private boolean mIsCircle;

    @BindView(R.id.iv_dial_1)
    AppCompatImageView mIvDial1;

    @BindView(R.id.iv_dial_2)
    AppCompatImageView mIvDial2;

    @BindView(R.id.iv_dial_3)
    AppCompatImageView mIvDial3;

    @BindView(R.id.iv_right_icon)
    AppCompatImageView mIvRight;

    @BindView(R.id.layout_dial)
    LinearLayout mLayoutDial;

    @BindView(R.id.new_tv)
    TextView mNewTv;
    private int mRadius;

    @BindView(R.id.tvTitleDial)
    TextView tvTitleDial;
    int width;
    private final List<String> mInstalledDialList = new ArrayList();
    List<DialPlateParam.PlateFileInfo> mInstalledV3DialList = new ArrayList();
    private final List<MyDialListEntity.DialInfo> mDialInfoList = new ArrayList();
    private List<MyDialListEntity.DialInfo> datas = new ArrayList();
    MyDialListEntity.DialInfo wallPaperDial = null;
    private int mWallpaperDialPosition = -1;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_multi_dial;
    }

    @Override // com.ido.life.module.device.view.IDialMarketActivityView
    public void startLoading() {
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        if (isSupportDialMarket()) {
            getSupportFragmentManager().beginTransaction().add(R.id.mainFragment, new DialMarketFragment()).commitAllowingStateLoss();
        }
        initDial();
        ((DialMarketActivityPresenter) this.mPresenter).getInstalledDialInfo();
    }

    private void initDial() {
        Drawable drawable;
        this.mNewTv.setVisibility(8);
        this.mIsBracelet = LocalDataManager.getCurrentDeviceInfo().type == 3;
        this.mRadius = (int) getResources().getDimension(this.mIsBracelet ? R.dimen.sw_dp_4 : R.dimen.sw_dp_7);
        this.mIsCircle = 1 == ((DialMarketActivityPresenter) this.mPresenter).getDeviceShape();
        for (int i = 0; i < this.mLayoutDial.getChildCount(); i++) {
            if (this.mIsCircle) {
                drawable = getDrawable(R.drawable.dial_detail_frame_circle_bg);
            } else if (this.mIsBracelet) {
                drawable = getDrawable(R.drawable.dial_detail_frame_bracelet_bg);
            } else {
                drawable = getDrawable(R.drawable.dial_detail_frame_watch_bg);
            }
            GradientDrawable gradientDrawable = (GradientDrawable) drawable;
            gradientDrawable.setCornerRadius(this.mRadius);
            ((ImageView) this.mLayoutDial.getChildAt(i)).setImageDrawable(gradientDrawable);
        }
        initDialImageSize();
    }

    private void initDialImageSize() {
        this.mIvDial1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.ido.life.module.device.activity.DialMarketActivity.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                DialMarketActivity.this.mIvDial1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                DialMarketActivity.this.setDialLayoutParams();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDialLayoutParams() {
        float dialImageAspectRatio = ((DialMarketActivityPresenter) this.mPresenter).getDialImageAspectRatio();
        for (int i = 0; i < this.mLayoutDial.getChildCount(); i++) {
            View childAt = this.mLayoutDial.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            this.width = childAt.getWidth();
            this.height = (int) (childAt.getWidth() * dialImageAspectRatio);
            layoutParams.height = (int) (childAt.getWidth() * dialImageAspectRatio);
            childAt.setLayoutParams(layoutParams);
        }
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_dial_market));
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        super.handleMessage(baseMessage);
        if (102 == baseMessage.getType()) {
            ((DialMarketActivityPresenter) this.mPresenter).saveDialLog("设备断开了");
            DialMarketActivityPresenter.isTelephone = false;
        }
        baseMessage.getType();
        if (baseMessage.getType() != 853) {
            return;
        }
        int iIntValue = ((Integer) baseMessage.getData()).intValue();
        if (iIntValue > 0) {
            this.mNewTv.setVisibility(0);
            this.mNewTv.setText(iIntValue + "");
            return;
        }
        this.mNewTv.setVisibility(8);
    }

    private boolean isSupportDialMarket() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo != null && (!supportFunctionInfo.V3_Veryfit_not_support_photo_wallpaper || supportFunctionInfo.ex_table_main11_support_cloud_dial);
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetCurrentDial(String str) {
        setCapacity();
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialList(List<String> list) {
        this.mInstalledDialList.clear();
        for (String str : list) {
            if (str.equals(BaseDialPresenter.WALLPAPER_DIAL_NAME)) {
                setWallPaperDial(str, list.indexOf(str));
            } else {
                this.mInstalledDialList.add(str.replace(".iwf", ""));
            }
        }
        ((DialMarketActivityPresenter) this.mPresenter).saveDialLog("onGetInstalledDialList：" + list);
        ((DialMarketActivityPresenter) this.mPresenter).requestDialInfoFromNames(this.mInstalledDialList);
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialListV3(List<DialPlateParam.PlateFileInfo> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        ((DialMarketActivityPresenter) this.mPresenter).saveDialLog("onGetInstalledDialListV3: " + list);
        this.mInstalledDialList.clear();
        for (DialPlateParam.PlateFileInfo plateFileInfo : list) {
            if (plateFileInfo != null) {
                String str = plateFileInfo.name;
                if (str.equals(BaseDialPresenter.WALLPAPER_DIAL_NAME)) {
                    setWallPaperDial(plateFileInfo.name, list.indexOf(plateFileInfo));
                } else {
                    this.mInstalledDialList.add(plateFileInfo.name.replace(".iwf", ""));
                }
            }
        }
        ((DialMarketActivityPresenter) this.mPresenter).requestDialInfoFromNames(this.mInstalledDialList);
    }

    private void setWallPaperDial(String str, int i) {
        this.wallPaperDial = ((DialMarketActivityPresenter) this.mPresenter).getWallPaperDialNew(str);
        this.mWallpaperDialPosition = i;
        MyDialListEntity.DialInfo dialInfo = this.wallPaperDial;
        if (dialInfo != null) {
            dialInfo.isInstalledDial = true;
            dialInfo.setImageUrl(((DialMarketActivityPresenter) this.mPresenter).getWallPaperDialPathNew(str));
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x001e. Please report as an issue. */
    @OnClick({R.id.iv_dial_1, R.id.iv_dial_2, R.id.iv_dial_3, R.id.my_dial_rl})
    public void onViewClicked(View view) {
        if (!((DialMarketActivityPresenter) this.mPresenter).isConnected()) {
            showToast(getLanguageText(R.string.device_pls_connect_device));
            return;
        }
        int id = view.getId();
        if (id != R.id.my_dial_rl) {
            switch (id) {
            }
        } else {
            startActivity(new Intent(this, (Class<?>) MyDialActivity.class));
        }
    }

    @Override // com.ido.life.module.device.view.IDialMarketActivityView
    public void onGetMyDialList(List<MyDialListEntity.DialInfo> list) {
        this.datas.clear();
        this.datas.addAll(list);
        if (this.wallPaperDial != null) {
            int i = this.mWallpaperDialPosition;
            if (i >= 0 && i < this.datas.size()) {
                this.datas.add(this.mWallpaperDialPosition, this.wallPaperDial);
            } else {
                this.datas.add(this.wallPaperDial);
            }
        }
        ((DialMarketActivityPresenter) this.mPresenter).saveDialLog("刷新最近安装表盘列表：" + this.datas);
        setMyDialView();
    }

    private void setMyDialView() {
        AppCompatImageView appCompatImageView;
        int i = 0;
        boolean z = ((DialMarketActivityPresenter) this.mPresenter).getDialImageAspectRatio() > 1.0f;
        Collections.reverse(this.datas);
        int i2 = this.mIsCircle ? R.mipmap.ic_default_dial_oval : this.mIsBracelet ? R.mipmap.ic_default_dial_bracelet : z ? R.mipmap.ic_default_dial_rectangle : R.mipmap.ic_default_dial_square;
        this.mRadius = this.mIsCircle ? this.width / 2 : this.mRadius;
        while (i < 3) {
            if (i < this.datas.size()) {
                MyDialListEntity.DialInfo dialInfo = this.datas.get(i);
                if (dialInfo != null) {
                    ((DialMarketActivityPresenter) this.mPresenter).saveDialLog("dialPlate url:" + dialInfo.getImageUrl());
                    if (i == 0) {
                        setImageView(dialInfo, this.mIvDial1, i2);
                    } else if (i == 1) {
                        setImageView(dialInfo, this.mIvDial2, i2);
                    } else if (i == 2) {
                        setImageView(dialInfo, this.mIvDial3, i2);
                    }
                }
            } else {
                if (i == 0) {
                    appCompatImageView = this.mIvDial1;
                } else {
                    appCompatImageView = i == 1 ? this.mIvDial2 : this.mIvDial3;
                }
                ImageLoaderUtil.loadResource(appCompatImageView, i2);
            }
            i++;
        }
        setCapacity();
    }

    private void setImageView(MyDialListEntity.DialInfo dialInfo, AppCompatImageView appCompatImageView, int i) {
        if (!TextUtils.isEmpty(dialInfo.getOtaFaceName())) {
            String otaFaceName = dialInfo.getOtaFaceName();
            if (otaFaceName.equals(BaseDialPresenter.WALLPAPER_DIAL_NAME)) {
                Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(((DialMarketActivityPresenter) this.mPresenter).getWallPaperDialPathNew(dialInfo.getOtaFaceName()));
                if (bitmapDecodeFile != null) {
                    if (this.mIsCircle) {
                        ImageLoaderUtil.loadBitmap(appCompatImageView, bitmapDecodeFile);
                        return;
                    } else {
                        ImageLoaderUtil.loadBitmap(appCompatImageView, BitmapUtil.transform2CornerBitmap(bitmapDecodeFile, this.mRadius));
                        return;
                    }
                }
                appCompatImageView.setImageResource(i);
                return;
            }
        }
        MyDialProxy myDialProxy = new MyDialProxy(dialInfo);
        ImageLoaderUtil.loadImgFillet(appCompatImageView, myDialProxy.getImageUrl(), myDialProxy.hasRefresh(), this.mRadius, i, this.width, this.height);
    }

    private void setCapacity() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo != null && supportFunctionInfo.V3_support_watch_capacity_size_display) {
            if (((DialMarketActivityPresenter) this.mPresenter).getWatchCapacitySize() != 0) {
                this.device_intall_count_tv.setText(((DialMarketActivityPresenter) this.mPresenter).getWatchUseCapacitySize() + "kb/" + ((DialMarketActivityPresenter) this.mPresenter).getWatchCapacitySize() + "kb");
                return;
            }
            return;
        }
        if (((DialMarketActivityPresenter) this.mPresenter).getMaxDialCount() > 1) {
            this.device_intall_count_tv.setText(((DialMarketActivityPresenter) this.mPresenter).getInstalledDialCount() + "/" + ((DialMarketActivityPresenter) this.mPresenter).getMaxDialCount());
        }
    }
}