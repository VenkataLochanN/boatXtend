package com.ido.life.module.device.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.amap.api.maps.AMapException;
import com.boat.Xtend.two.R;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.common.IdoApp;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.BitmapUtil;
import com.ido.common.utils.FileDialDefinedUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.MemoryManagerUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.DialPercentView;
import com.ido.life.customview.DialRoundRelativelayout;
import com.ido.life.customview.ExpandLayout;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.recyclerview.DialStyleAdapter;
import com.ido.life.customview.viewgroup.LoadingTextView;
import com.ido.life.data.api.entity.DialDefinedEntityNew;
import com.ido.life.data.api.entity.DialDefinedFunctionEntity;
import com.ido.life.data.api.entity.DialStyleEntity;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.module.device.fragment.MyDialFragment;
import com.ido.life.module.device.presenter.BaseDialPresenter;
import com.ido.life.module.device.presenter.DialDefinedPresenter;
import com.ido.life.module.device.view.DialDefinedView;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.util.DevicePicUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialDefinedActivity extends BaseActivity<DialDefinedPresenter> implements DialDefinedView {
    public static final String COME_FROM_DEVICE_INFO_ACTIVITY = "come_from_device_info_activity";
    public static final String COME_FROM_MY_DIAL = "come_from_my_dial";
    public static final String DIAL = "dial";
    private static final int ITEM_ADD_AND_INSTALL = 1;
    private static final int ITEM_DELETE_DIAL = 5;
    private static final int ITEM_INSTALL_TO_DEVICE = 3;
    private static final int ITEM_REMOVE_FROM_DEVICE = 4;
    private static final int ITEM_SET_CURRENT_DIAL = 2;
    private int clickedItem;
    private String currentDialName;
    private DialDefinedEntityNew definedEntity;

    @BindView(R.id.dial_color_ll)
    LinearLayout dialColorLl;
    private DialStyleAdapter dialStyleAdapter;

    @BindView(R.id.dial_sv)
    NestedScrollView dialSv;
    private boolean installed;
    private boolean isBuiltInDial;

    @BindView(R.id.iv_dial_rl)
    DialRoundRelativelayout ivDialRl;
    private boolean mComeFromDeviceInfo;

    @BindView(R.id.content_ll)
    LinearLayout mContentLL;
    private CommBottomConfirmDialog mDialDeleteDialog;
    private MyDialListEntity.DialInfo mDialInfo;

    @BindView(R.id.dial_load_Ll)
    LinearLayout mDialLoadll;
    private CommBottomConfirmDialog mDialMemoryFullDialog;

    @BindView(R.id.divider_bottom)
    View mDividerBottom;

    @BindView(R.id.divider_top)
    View mDividerTop;
    private boolean mExistZip;
    private boolean mIsBracelet;
    private boolean mIsCurrentDial;

    @BindView(R.id.iv_loading)
    AppCompatImageView mIvLoading;

    @BindView(R.id.layout_bottom)
    LinearLayout mLayoutBottom;

    @BindView(R.id.layout_description)
    LinearLayout mLayoutDesc;

    @BindView(R.id.layout_dial_info)
    RelativeLayout mLayoutDialInfo;

    @BindView(R.id.layout_network_error)
    LinearLayout mLayoutNetworkError;

    @BindView(R.id.mtv_description)
    ExpandLayout mMtvDescription;

    @BindView(R.id.mtv_dial_name)
    TextView mMtvDialName;

    @BindView(R.id.mtv_dial_size)
    TextView mMtvDialSize;
    private boolean mOwned;
    private float mRatio;

    @BindView(R.id.rtv_add_and_install)
    RegularTextView mRtvAddAndInstall;
    private boolean mUpdateZip;

    @BindView(R.id.view_delete_dial)
    LoadingTextView mViewDeleteDial;

    @BindView(R.id.view_set_use)
    LoadingTextView mViewSetUse;

    @BindView(R.id.recyclerView_defined)
    RecyclerView recyclerViewDefined;

    @BindView(R.id.rtl_dial)
    RelativeLayout rtlDial;
    private ArrayList<DialStyleEntity> styleDrawables;
    private int installedVersion = Integer.MAX_VALUE;
    private Context context = this;
    private int colorIndex = 0;
    private int styleIndex = 0;
    private int mDeviceId = 0;
    private boolean mIsRemovedFromServer = false;

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void getDefinedFuctionEntity(DialDefinedFunctionEntity dialDefinedFunctionEntity) {
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_dial_defined;
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void onGetDialState(boolean z) {
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void onSwitchDialStart() {
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mDialInfo = (MyDialListEntity.DialInfo) getIntent().getSerializableExtra("dial");
        if (this.mDialInfo == null) {
            this.mDialInfo = new MyDialListEntity.DialInfo();
        }
        initRlView();
        this.mComeFromDeviceInfo = getIntent().getBooleanExtra("come_from_device_info_activity", false);
        this.mIsCurrentDial = this.mDialInfo.isCurrentDial;
        this.installed = this.mDialInfo.isInstalledDial;
        String otaFaceName = this.mDialInfo.getOtaFaceName();
        this.dialSv.setVisibility(8);
        if (!TextUtils.isEmpty(otaFaceName) && (otaFaceName.startsWith(BaseDialPresenter.BUILT_IN_DIAL_NAME_START) || otaFaceName.startsWith(BaseDialPresenter.BUILT_IN_DIAL_NAME_START_OLD))) {
            this.currentDialName = otaFaceName;
            this.isBuiltInDial = true;
            this.mOwned = true;
            hideDeleteView();
            this.mLayoutDesc.setVisibility(TextUtils.isEmpty(this.mDialInfo.getDescription()) ? 8 : 0);
            this.mContentLL.setVisibility(0);
            this.mDialLoadll.setVisibility(8);
        } else {
            if (TextUtils.isEmpty(otaFaceName) || !otaFaceName.endsWith(".iwf")) {
                otaFaceName = otaFaceName + ".iwf";
            }
            this.currentDialName = otaFaceName;
            ((DialDefinedPresenter) this.mPresenter).requestDialInfo(this.mDialInfo.getId());
            bindDialInfo2View(this.mDialInfo);
        }
        if (getIntent().getBooleanExtra("come_from_my_dial", false)) {
            this.mOwned = true;
            this.installed = true;
            this.mRtvAddAndInstall.setText(getString(R.string.device_installed));
            this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_diable_bg);
            if (!this.isBuiltInDial) {
                this.mTitleBar.setTitle(TextUtils.isEmpty(this.mDialInfo.getName()) ? LanguageUtil.getLanguageText(R.string.device_dial) : this.mDialInfo.getName());
            }
        } else {
            this.mLayoutBottom.setVisibility(8);
            this.mRtvAddAndInstall.setVisibility(8);
            if (!NetworkUtil.isConnected(this)) {
                this.mLayoutNetworkError.setVisibility(0);
            }
        }
        this.mTitleBar.initLayout(1);
        if (this.mDialInfo.isCollected()) {
            this.mTitleBar.setRightImg(R.mipmap.collect_select_icon);
        } else {
            this.mTitleBar.setRightImg(R.mipmap.collect_icon);
        }
        this.mTitleBar.setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.DialDefinedActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DialDefinedActivity.this.mDialInfo.isCollected()) {
                    ((DialDefinedPresenter) DialDefinedActivity.this.mPresenter).updateDialCollect(2);
                } else {
                    ((DialDefinedPresenter) DialDefinedActivity.this.mPresenter).updateDialCollect(1);
                }
            }
        });
    }

    private void hideDeleteView() {
        this.mViewDeleteDial.setVisibility(8);
        this.mDividerBottom.setVisibility(8);
    }

    private void initRlView() {
        int i;
        this.mIsBracelet = ((DialDefinedPresenter) this.mPresenter).isBracelet();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.rtlDial.getLayoutParams();
        layoutParams.width = ScreenUtil.getScreenW() / (this.mIsBracelet ? 4 : 3);
        this.mRatio = ((DialDefinedPresenter) this.mPresenter).getDialImageAspectRatio();
        layoutParams.height = (int) (layoutParams.width * this.mRatio);
        this.rtlDial.setLayoutParams(layoutParams);
        RelativeLayout relativeLayout = this.rtlDial;
        if (1 == ((DialDefinedPresenter) this.mPresenter).getDeviceShape()) {
            i = R.drawable.dial_frame_circle_bg;
        } else {
            i = ((DialDefinedPresenter) this.mPresenter).isBracelet() ? R.drawable.dial_frame_bracelet_bg : R.drawable.dial_frame_watch_bg;
        }
        relativeLayout.setBackgroundResource(i);
    }

    private void initRecyclerView() {
        this.styleDrawables = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        this.recyclerViewDefined.setLayoutManager(linearLayoutManager);
        for (int i = 0; i < this.definedEntity.getDialStyle().size(); i++) {
            DialStyleEntity dialStyleEntity = new DialStyleEntity();
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < this.definedEntity.getDialStyle().get(i).getStyleimg().size(); i2++) {
                if (MemoryManagerUtil.getSurplusMemory(this.context)) {
                    arrayList.add(BitmapUtil.readBitmapFromFileDrawable(FileDialDefinedUtil.appFilePng(this.context, this.mDeviceId) + File.separator + this.definedEntity.getDialStyle().get(i).getStyleimg().get(i2)));
                } else {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "内存不足，请清理后重试");
                    NormalToast.showToast(this.context.getString(R.string.insufficient_memory));
                    finish();
                }
            }
            dialStyleEntity.setStyleimg(arrayList);
            dialStyleEntity.setDescription(this.definedEntity.getDialStyle().get(i).getDescription());
            this.styleDrawables.add(dialStyleEntity);
        }
        this.colorIndex = this.definedEntity.getSelectItem().getColors().intValue();
        this.dialStyleAdapter = new DialStyleAdapter(this.definedEntity.getSelectItem().getDialStyle().intValue(), this.styleDrawables, this.definedEntity.getColors().get(this.colorIndex), this.definedEntity.getStylemodul(), this.definedEntity.getOutFile().getImagegroupsize(), this, new DialStyleAdapter.OnItemClickListener() { // from class: com.ido.life.module.device.activity.DialDefinedActivity.2
            @Override // com.ido.life.customview.recyclerview.DialStyleAdapter.OnItemClickListener
            public void onClick(int i3) {
                DialDefinedActivity.this.dialStyleAdapter.setCurrentIndex(i3, true);
                DialDefinedActivity.this.definedEntity.getSelectItem().setDialStyle(Integer.valueOf(i3));
                DialDefinedActivity.this.upDateUseView();
            }

            @Override // com.ido.life.customview.recyclerview.DialStyleAdapter.OnItemClickListener
            public void selectView(int i3) {
                DialDefinedActivity.this.setVisPreView(i3);
            }
        }, this.mDeviceId);
        this.recyclerViewDefined.setAdapter(this.dialStyleAdapter);
    }

    public View addView(String str) {
        final View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.item_color_dial, (ViewGroup) null);
        DialPercentView dialPercentView = (DialPercentView) viewInflate.findViewById(R.id.color_style_img);
        final RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.dial_color_rl);
        dialPercentView.init(str);
        if (this.definedEntity.getSelectItem().getColors().intValue() == this.dialColorLl.getChildCount()) {
            relativeLayout.setBackgroundResource(R.drawable.dial_color_checks_select);
            this.colorIndex = this.definedEntity.getSelectItem().getColors().intValue();
        } else {
            relativeLayout.setBackgroundResource(R.drawable.dial_color_checks_not_select);
        }
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.DialDefinedActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DialDefinedActivity.this.setDoalColorBg();
                DialDefinedActivity.this.upDateUseView();
                relativeLayout.setBackgroundResource(R.drawable.dial_color_checks_select);
                DialDefinedActivity dialDefinedActivity = DialDefinedActivity.this;
                dialDefinedActivity.colorIndex = dialDefinedActivity.dialColorLl.indexOfChild(viewInflate);
                DialDefinedActivity.this.definedEntity.getSelectItem().setColors(Integer.valueOf(DialDefinedActivity.this.colorIndex));
                DialDefinedActivity.this.dialStyleAdapter.setCurrentIndex(DialDefinedActivity.this.definedEntity.getColors().get(DialDefinedActivity.this.colorIndex), true);
            }
        });
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVisPreView(int i) {
        Drawable bitmapFromFileDrawable;
        Drawable bitmapFromFileDrawable2;
        this.ivDialRl.removeAllViews();
        this.styleIndex = i;
        for (int i2 = 0; i2 < this.definedEntity.getPreviewmodul().size(); i2++) {
            if (TextUtils.equals(FileDialDefinedUtil.STYLE_BG, this.definedEntity.getPreviewmodul().get(i2))) {
                for (int i3 = 0; i3 < this.definedEntity.getDialStyle().get(i).getStyleimg().size(); i3++) {
                    if (MemoryManagerUtil.getSurplusMemory(this.context)) {
                        bitmapFromFileDrawable2 = BitmapUtil.readBitmapFromFileDrawable(FileDialDefinedUtil.appFilePng(this.context, this.mDeviceId) + File.separator + this.definedEntity.getDialStyle().get(i).getStyleimg().get(i3));
                    } else {
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "内存不足，请清理后重试");
                        NormalToast.showToast(this.context.getString(R.string.insufficient_memory));
                        finish();
                        bitmapFromFileDrawable2 = null;
                    }
                    this.ivDialRl.addView(com.ido.life.customview.viewgroup.DialDefinedView.addView(i3, this.context, bitmapFromFileDrawable2, this.definedEntity.getColors().get(this.colorIndex), true, false));
                }
            } else {
                if (MemoryManagerUtil.getSurplusMemory(this.context)) {
                    bitmapFromFileDrawable = BitmapUtil.readBitmapFromFileDrawable(FileDialDefinedUtil.appFilePng(this.context, this.mDeviceId) + File.separator + this.definedEntity.getPreviewmodul().get(i2));
                } else {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "内存不足，请清理后重试");
                    NormalToast.showToast(this.context.getString(R.string.insufficient_memory));
                    finish();
                    bitmapFromFileDrawable = null;
                }
                this.ivDialRl.addView(com.ido.life.customview.viewgroup.DialDefinedView.addView(i2, this.context, bitmapFromFileDrawable, this.definedEntity.getColors().get(this.colorIndex), false, false));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDoalColorBg() {
        for (int i = 0; i < this.dialColorLl.getChildCount(); i++) {
            ((RelativeLayout) this.dialColorLl.getChildAt(i).findViewById(R.id.dial_color_rl)).setBackgroundResource(R.drawable.dial_color_checks_not_select);
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DialDefinedActivity$nWhG9dr88XOoP7NZ7eJGZGOR0OI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$DialDefinedActivity(view);
            }
        });
        if (!this.isBuiltInDial) {
            ((DialDefinedPresenter) this.mPresenter).getInstalledDialInfo();
        }
        initState();
    }

    public /* synthetic */ void lambda$initEvent$0$DialDefinedActivity(View view) {
        onBackPressed();
    }

    private void initState() {
        this.mViewSetUse.setText(getLanguageText(this.mIsCurrentDial ? R.string.dial_device_update : R.string.device_install_to_device));
        this.mViewDeleteDial.setText(getLanguageText(R.string.device_delete_dial));
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        updateDeleteViewState();
    }

    private void updateDeleteViewState() {
        if (this.isBuiltInDial || ((DialDefinedPresenter) this.mPresenter).isOffline()) {
            return;
        }
        this.mViewDeleteDial.setVisibility(NetworkUtil.isConnected(this) ? 0 : 8);
        this.mDividerBottom.setVisibility(NetworkUtil.isConnected(this) ? 0 : 8);
    }

    private void showDialUpdate() {
        try {
            MyDialListEntity.DialInfo.OtaFaceVersion otaFaceVersion = this.mDialInfo.getOtaFaceVersion();
            if (otaFaceVersion == null || Integer.parseInt(otaFaceVersion.getVersion()) <= this.installedVersion || TextUtils.isEmpty(otaFaceVersion.getLinkUrl())) {
                return;
            }
            this.mRtvAddAndInstall.setEnabled(true);
            this.mRtvAddAndInstall.setText(getLanguageText(R.string.device_update_dial));
            this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_bg);
            this.mRtvAddAndInstall.setTextColor(getColor(R.color.white));
        } catch (Exception e2) {
            ((DialDefinedPresenter) this.mPresenter).saveDialLog("onGetDialInfo Exception：" + e2.toString());
        }
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void getDefinedEntity(DialDefinedEntityNew dialDefinedEntityNew) {
        this.mContentLL.setVisibility(0);
        this.mDialLoadll.setVisibility(8);
        if (dialDefinedEntityNew != null) {
            this.dialSv.setVisibility(0);
            this.mDeviceId = ((DialDefinedPresenter) this.mPresenter).getDeviceInfo().mDeviceId;
            this.definedEntity = dialDefinedEntityNew;
            if (dialDefinedEntityNew.getOutFile().getImagegroupsize() != null && dialDefinedEntityNew.getOutFile().getImagegroupsize().getWidth() != null) {
                MemoryManagerUtil.WIDTH = dialDefinedEntityNew.getOutFile().getImagegroupsize().getWidth().intValue();
                MemoryManagerUtil.HEIGHT = dialDefinedEntityNew.getOutFile().getImagegroupsize().getHeight().intValue();
            }
            this.mMtvDescription.setContent(this.mDialInfo.getDescription());
            initRecyclerView();
            for (int i = 0; i < dialDefinedEntityNew.getColors().size(); i++) {
                this.dialColorLl.addView(addView(dialDefinedEntityNew.getColors().get(i).getColor()));
            }
            return;
        }
        ((DialDefinedPresenter) this.mPresenter).saveDialLog("json格式化错误或不是DialDefinedEntityNew固定的格式类型");
        NormalToast.showToast(this.context.getString(R.string.device_install_failed));
    }

    private void goDeleteDial() {
        startActivity(new Intent(this, (Class<?>) MyDialEditActivity.class));
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void onAddAccountsuccess() {
        saveDial();
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void onCollectDial(boolean z) {
        if (z) {
            if (this.mDialInfo.isCollected()) {
                this.mDialInfo.setCollected(false);
                this.mTitleBar.setRightImg(R.mipmap.collect_icon);
                showToast(getString(R.string.cancel_collect_success));
                return;
            } else {
                this.mDialInfo.setCollected(true);
                this.mTitleBar.setRightImg(R.mipmap.collect_select_icon);
                showToast(getString(R.string.collect_success));
                return;
            }
        }
        if (this.mDialInfo.isCollected()) {
            showToast(getString(R.string.cancel_collect_failed));
        } else {
            showToast(getString(R.string.collect_failed));
        }
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void onInstallFailByNotMemory() {
        updateFailedView();
        showDialMemoryFullDialog();
    }

    private void showDeleteDialog() {
        if (!((DialDefinedPresenter) this.mPresenter).isConnected()) {
            showToast(R.string.device_pls_connect_device);
            return;
        }
        if (this.mDialDeleteDialog == null) {
            this.mDialDeleteDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.device_delete_dial), getLanguageText(R.string.dial_delete_tip), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DialDefinedActivity$afdcnTf-eV1q4-z_8tMo3WOsqDg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showDeleteDialog$1$DialDefinedActivity(view);
                }
            });
        }
        this.mDialDeleteDialog.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showDeleteDialog$1$DialDefinedActivity(View view) {
        deleteDial(false);
    }

    private void deleteDial(boolean z) {
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            showToast(getLanguageText(R.string.logn_all_pleaseCheckNetWork_ios));
            return;
        }
        this.clickedItem = 5;
        this.mViewDeleteDial.startAnimation();
        this.mViewDeleteDial.setText(getLanguageText(R.string.device_setting));
        ((DialDefinedPresenter) this.mPresenter).removeDialFromDevice(this.currentDialName, z);
    }

    private void updateDial() {
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            showToast(getLanguageText(R.string.logn_all_pleaseCheckNetWork_ios));
            return;
        }
        this.clickedItem = 2;
        this.mViewSetUse.startAnimation();
        this.mViewSetUse.setText(getLanguageText(R.string.device_setting));
        ((DialDefinedPresenter) this.mPresenter).removeDialFromDevice(this.currentDialName, true);
    }

    @OnClick({R.id.rtv_add_and_install, R.id.view_set_use, R.id.view_delete_dial})
    public void onViewClicked(View view) {
        if (((DialDefinedPresenter) this.mPresenter).isSetting) {
            return;
        }
        int id = view.getId();
        if (id == R.id.rtv_add_and_install) {
            if (HomeFragmentPresenter.mIsSyncing) {
                showToast(getLanguageText(R.string.syncing_pls_try_again_later));
                return;
            } else if (HomeFragmentPresenter.mIsTelephone) {
                showToast(getLanguageText(R.string.sport_start_incalling));
                return;
            } else {
                this.clickedItem = 1;
                setSaveDial();
                return;
            }
        }
        if (id == R.id.view_delete_dial) {
            if (!this.mDialInfo.isEnabled() && !this.installed) {
                deleteDial(true);
                return;
            } else {
                this.mUpdateZip = false;
                showDeleteDialog();
                return;
            }
        }
        if (id != R.id.view_set_use) {
            return;
        }
        if (HomeFragmentPresenter.mIsSyncing) {
            showToast(getLanguageText(R.string.syncing_pls_try_again_later));
        } else if (HomeFragmentPresenter.mIsTelephone) {
            showToast(getLanguageText(R.string.sport_start_incalling));
        } else {
            this.clickedItem = 2;
            setSaveDial();
        }
    }

    private void setDownDial() {
        ((DialDefinedPresenter) this.mPresenter).setIsDownDial(true);
        if (((DialDefinedPresenter) this.mPresenter).isConnected()) {
            ((DialDefinedPresenter) this.mPresenter).downDialZip(this.mDialInfo, false, this.context);
        }
    }

    private void setSaveDial() {
        MyDialListEntity.DialInfo dialInfo = this.mDialInfo;
        if (dialInfo == null || dialInfo.getOtaFaceVersion() == null) {
            return;
        }
        if (((DialDefinedPresenter) this.mPresenter).hasMemoryInstall(this.mDialInfo.getOtaFaceVersion().getSize())) {
            updateItemProgress(2, 0);
            if (!this.mOwned) {
                ((DialDefinedPresenter) this.mPresenter).addDefinedDial2Account(true);
                return;
            } else {
                saveDial();
                return;
            }
        }
        ((DialDefinedPresenter) this.mPresenter).saveDialLog(AMapException.ERROR_NOT_ENOUGH_SPACE);
        showDialMemoryFullDialog();
    }

    private void bindDialInfo2View(MyDialListEntity.DialInfo dialInfo) {
        this.mMtvDialName.setText(dialInfo.getName());
        this.mLayoutDesc.setVisibility(TextUtils.isEmpty(dialInfo.getDescription()) ? 8 : 0);
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void onGetDialInfo(MyDialListEntity.DialInfo dialInfo) {
        this.mDialInfo = dialInfo;
        this.mDeviceId = ((DialDefinedPresenter) this.mPresenter).getDeviceInfo().mDeviceId;
        FileDialDefinedUtil.deleteDirectory(FileDialDefinedUtil.jsonDir(this.context, this.mDeviceId));
        this.mExistZip = FileDialDefinedUtil.checkFileExist(FileDialDefinedUtil.saveZipDir(this.context), this.mDialInfo.getOtaFaceName());
        if (!this.mExistZip) {
            setDownDial();
        } else {
            ((DialDefinedPresenter) this.mPresenter).unpackZip(this.context, this.mDialInfo);
        }
        bindDialInfo2View(dialInfo);
        if (this.mDialInfo.isCollected()) {
            this.mTitleBar.setRightImg(R.mipmap.collect_select_icon);
        } else {
            this.mTitleBar.setRightImg(R.mipmap.collect_icon);
        }
        if (this.mDialInfo.getOtaFaceVersion() != null) {
            this.mMtvDialSize.setText((this.mDialInfo.getOtaFaceVersion().getSize() / 1024) + "kb");
        }
        this.mIsRemovedFromServer = !this.mDialInfo.isEnabled();
        updateRemovedView();
        showDialUpdate();
    }

    private void refreshViewState() {
        if (this.installed || this.mIsCurrentDial || this.mOwned) {
            this.mRtvAddAndInstall.setText(getString(R.string.device_installed));
            this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_diable_bg);
            this.mRtvAddAndInstall.setVisibility(0);
            this.mRtvAddAndInstall.setEnabled(false);
            this.mLayoutBottom.setVisibility(0);
            this.mLayoutDialInfo.setVisibility(0);
            if (!this.isBuiltInDial) {
                this.mTitleBar.setTitle(TextUtils.isEmpty(this.mDialInfo.getName()) ? LanguageUtil.getLanguageText(R.string.device_dial) : this.mDialInfo.getName());
            }
            if (((DialDefinedPresenter) this.mPresenter).canDelete()) {
                updateDeleteViewState();
            } else {
                hideDeleteView();
            }
        } else {
            this.mLayoutBottom.setVisibility(8);
            if (!((DialDefinedPresenter) this.mPresenter).isOffline()) {
                this.mRtvAddAndInstall.setVisibility(0);
                this.mRtvAddAndInstall.setEnabled(true);
            } else {
                this.mRtvAddAndInstall.setVisibility(8);
            }
            this.mLayoutDialInfo.setVisibility(0);
        }
        updateRemovedView();
    }

    private void updateRemovedView() {
        if (this.mIsRemovedFromServer) {
            this.mRtvAddAndInstall.setEnabled(false);
            this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_diable_bg);
            if (!this.installed && NetworkUtil.isConnected(this)) {
                ((DialDefinedPresenter) this.mPresenter).saveDialLog("updateRemovedView 处理已下架，未安装情况");
                this.mRtvAddAndInstall.setText(getString(R.string.dial_aready_undercarriage));
                this.mViewSetUse.setVisibility(8);
                this.mDividerTop.setVisibility(8);
                this.mViewDeleteDial.setVisibility(0);
                this.mViewDeleteDial.setText(getString(R.string.dial_delete_from_record));
                this.mLayoutBottom.setVisibility(0);
                return;
            }
            ((DialDefinedPresenter) this.mPresenter).saveDialLog("updateRemovedView 处理已下架，已安装情况");
            this.mRtvAddAndInstall.setText(getString(R.string.device_installed));
        }
    }

    private void updateItemProgress(int i, int i2) {
        int i3 = this.clickedItem;
        if (i3 == 1) {
            updateTopLabelProgress(i, i2);
        } else {
            if (i3 != 2) {
                return;
            }
            updateProgress(i, i2, this.mViewSetUse);
        }
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void onDialSwitched(boolean z) {
        CommonLogUtil.d("onDialSwitched : " + z);
        if (z) {
            updateSuccessView();
            this.mIsCurrentDial = true;
            this.installed = true;
            notifyDialChanged();
            onBackPressed();
            return;
        }
        showCmdResultToast(false);
        updateFailedView();
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetCurrentDial(String str) {
        if (((DialDefinedPresenter) this.mPresenter).isSetting) {
            return;
        }
        if (!TextUtils.isEmpty(this.currentDialName)) {
            boolean zContains = str.contains(this.currentDialName);
            if (!this.isBuiltInDial && this.mIsCurrentDial != zContains) {
                this.mIsCurrentDial = zContains;
                this.installed = true;
            }
        }
        this.mViewSetUse.setEnabled(!this.mIsCurrentDial);
        this.mViewSetUse.setText(getLanguageText(this.mIsCurrentDial ? R.string.device_using : R.string.device_set_current_dial));
        refreshViewState();
    }

    private void saveDial() {
        if (this.installed) {
            ((DialDefinedPresenter) this.mPresenter).saveDialLog("已安装，重新安装");
            updateItemProgress(2, 0);
            ((DialDefinedPresenter) this.mPresenter).saveDialDefined(this.context, this.mDialInfo, this.definedEntity, this.colorIndex, this.styleIndex, this.ivDialRl);
            ((DialDefinedPresenter) this.mPresenter).installDial2Device(FileDialDefinedUtil.zipDir(this.context, this.mDeviceId) + this.mDialInfo.getOtaFaceName() + FileDialDefinedUtil.FILE_ZIP, this.mDialInfo.getOtaFaceName(), this.installed);
            return;
        }
        if (((DialDefinedPresenter) this.mPresenter).hasMemoryInstall(this.mDialInfo.getOtaFaceVersion().getSize())) {
            ((DialDefinedPresenter) this.mPresenter).saveDialDefined(this.context, this.mDialInfo, this.definedEntity, this.colorIndex, this.styleIndex, this.ivDialRl);
            ((DialDefinedPresenter) this.mPresenter).installDial2Device(FileDialDefinedUtil.zipDir(this.context, this.mDeviceId) + this.mDialInfo.getOtaFaceName() + FileDialDefinedUtil.FILE_ZIP, this.mDialInfo.getOtaFaceName(), this.installed);
            return;
        }
        showDialMemoryFullDialog();
    }

    private void showDialMemoryFullDialog() {
        if (!((DialDefinedPresenter) this.mPresenter).isConnected()) {
            showToast(R.string.device_pls_connect_device);
            return;
        }
        if (this.mDialMemoryFullDialog == null) {
            this.mDialMemoryFullDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.dial_memory_full), getLanguageText(R.string.dial_memory_full_tip), getLanguageText(R.string.go_settting), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DialDefinedActivity$dlXyTEC1fWgKnk1Mf8vlH37ybR4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showDialMemoryFullDialog$2$DialDefinedActivity(view);
                }
            });
        }
        this.mDialMemoryFullDialog.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showDialMemoryFullDialog$2$DialDefinedActivity(View view) {
        goDeleteDial();
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialList(List<String> list) {
        if (((DialDefinedPresenter) this.mPresenter).isSetting) {
            return;
        }
        if (list.contains(this.currentDialName)) {
            this.installed = true;
        } else {
            this.installed = false;
        }
        refreshViewState();
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialListV3(List<DialPlateParam.PlateFileInfo> list) {
        if (((DialDefinedPresenter) this.mPresenter).isSetting || list == null || list.isEmpty()) {
            return;
        }
        Iterator<DialPlateParam.PlateFileInfo> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DialPlateParam.PlateFileInfo next = it.next();
            if (next != null && TextUtils.equals(next.name, this.currentDialName)) {
                this.installed = true;
                this.installedVersion = next.watch_version;
                showDialUpdate();
                break;
            }
        }
        refreshViewState();
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void onDeleteDial(boolean z) {
        if (this.mUpdateZip) {
            setSaveDial();
            return;
        }
        if (z) {
            this.mIsCurrentDial = false;
            this.installed = false;
            updateSuccessView();
            finish();
            return;
        }
        showToast(getLanguageText(R.string.device_delete_failed));
        updateFailedView();
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void onProgress(int i, int i2) {
        int i3 = this.clickedItem;
        if (i3 == 1) {
            updateTopLabelProgress(i, i2);
        } else {
            if (i3 != 2) {
                return;
            }
            updateProgress(i, i2, this.mViewSetUse);
        }
    }

    private void updateTopLabelProgress(int i, int i2) {
        if (i2 < 100) {
            if (i2 == 0) {
                startTopAnimation();
            }
            RegularTextView regularTextView = this.mRtvAddAndInstall;
            regularTextView.setText(String.format(getLanguageText(i == 1 ? R.string.device_downloading_x : R.string.device_installing_x), Integer.valueOf(i2)));
            return;
        }
        this.mRtvAddAndInstall.setBackgroundResource(R.color.translate);
        if (i == 1) {
            stopTopAnimation();
            this.mRtvAddAndInstall.setText(getLanguageText(R.string.device_install_success));
        }
    }

    private void stopTopAnimation() {
        this.mIvLoading.clearAnimation();
        this.mIvLoading.setImageResource(R.mipmap.icon_set_complete);
    }

    private void startTopAnimation() {
        this.mRtvAddAndInstall.setTextColor(getColor(R.color.color_545657));
        this.mRtvAddAndInstall.setBackgroundResource(R.color.translate);
        this.mIvLoading.setVisibility(0);
        this.mIvLoading.setImageResource(R.mipmap.icon_loading_dial);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 1800.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(4000L);
        rotateAnimation.setRepeatCount(-1);
        this.mIvLoading.startAnimation(rotateAnimation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upDateUseView() {
        if (this.mIsCurrentDial) {
            this.mViewSetUse.setEnabled(true);
            this.mViewSetUse.setText(getLanguageText(R.string.device_set_current_dial));
        }
    }

    private void updateProgress(int i, int i2, LoadingTextView loadingTextView) {
        int i3 = R.string.device_downloading_x;
        if (i2 == 0) {
            loadingTextView.startAnimation();
            if (i != 1) {
                i3 = R.string.device_installing_x;
            }
            loadingTextView.setText(String.format(getLanguageText(i3), Integer.valueOf(i2)));
            return;
        }
        if (i2 != 100) {
            if (i != 1) {
                i3 = R.string.device_installing_x;
            }
            loadingTextView.setText(String.format(getLanguageText(i3), Integer.valueOf(i2)));
        } else if (i == 1) {
            loadingTextView.setImageResource(R.mipmap.icon_set_complete);
            loadingTextView.setText(getLanguageText(R.string.device_install_success));
        }
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void onDownloadFailed() {
        this.mContentLL.setVisibility(0);
        this.mDialLoadll.setVisibility(8);
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            showToast(getLanguageText(R.string.download_failed));
        } else {
            showToast(getLanguageText(R.string.logn_all_pleaseCheckNetWork_ios));
        }
        updateFailedView();
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void onDownloadsuccess() throws Throwable {
        this.mDeviceId = ((DialDefinedPresenter) this.mPresenter).getDeviceInfo().mDeviceId;
        this.dialSv.setVisibility(0);
        FileDialDefinedUtil.unpackCopyZip(FileDialDefinedUtil.jsonDir(this.context, this.mDeviceId) + File.separator, FileDialDefinedUtil.saveZipDir(this.context) + this.mDialInfo.getOtaFaceName() + FileDialDefinedUtil.FILE_ZIP);
        ((DialDefinedPresenter) this.mPresenter).getDefinedEntity(this.context);
    }

    private void updateFailedView() {
        int i = this.clickedItem;
        if (i == 1) {
            this.mIvLoading.clearAnimation();
            this.mIvLoading.setVisibility(8);
            this.mRtvAddAndInstall.setText(getLanguageText(R.string.public_install));
            this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_bg);
            this.mRtvAddAndInstall.setTextColor(getColor(R.color.white));
            return;
        }
        if (i == 2) {
            this.mViewSetUse.stopAnimation();
            this.mViewSetUse.setText(getLanguageText(R.string.device_set_current_dial));
        } else {
            if (i != 5) {
                return;
            }
            this.mViewDeleteDial.stopAnimation();
            this.mViewDeleteDial.setText(getLanguageText(R.string.device_delete_dial));
        }
    }

    private void updateSuccessView() {
        int i = this.clickedItem;
        if (i == 1) {
            stopTopAnimation();
            this.mRtvAddAndInstall.setText(getLanguageText(R.string.device_install_success));
        } else if (i == 2) {
            this.mViewSetUse.setImageResource(R.mipmap.icon_set_complete);
            this.mViewSetUse.setText(getLanguageText(R.string.device_set_success));
        } else {
            if (i != 5) {
                return;
            }
            this.mViewDeleteDial.setImageResource(R.mipmap.icon_set_complete);
            this.mViewDeleteDial.setText(getLanguageText(R.string.device_set_success));
        }
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void onDialInstallSuccess() {
        this.mIsCurrentDial = true;
        this.installed = true;
        notifyDialChanged();
        updateSuccessView();
        onBackPressed();
    }

    private void notifyDialChanged() {
        DevicePicUtils.spSaveDial(((DialDefinedPresenter) this.mPresenter).getDeviceInfo().mDeviceAddress + "", this.currentDialName, true);
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void onDialInstallFailed() {
        showCmdResultToast(false);
        updateFailedView();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (((DialDefinedPresenter) this.mPresenter).isSetting) {
            showToast(getLanguageText(R.string.device_setting_not_exit));
            return;
        }
        CommonLogUtil.d("onDialSwitched : onBackPressed");
        if (this.mIsCurrentDial) {
            Intent intent = new Intent();
            if (this.isBuiltInDial) {
                intent.putExtra(MyDialFragment.DIAL_NAME, this.mDialInfo.getOtaFaceName());
            } else {
                intent.putExtra(MyDialFragment.DIAL_NAME, this.mDialInfo.getOtaFaceName() + ".iwf");
            }
            setResult(88, intent);
        }
        finish();
    }
}