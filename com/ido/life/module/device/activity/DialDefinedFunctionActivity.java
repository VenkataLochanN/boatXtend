package com.ido.life.module.device.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
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
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.google.android.gms.fitness.FitnessActivities;
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
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.SingleTaskIntent;
import com.ido.life.bean.WorldTimeCity;
import com.ido.life.customview.DialPercentView;
import com.ido.life.customview.DialRoundRelativelayout;
import com.ido.life.customview.ExpandLayout;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.recyclerview.DialFunctionBGAdapter;
import com.ido.life.customview.recyclerview.DialFunctionStyleAdapter;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.customview.viewgroup.DialDefinedFunctionView;
import com.ido.life.customview.viewgroup.LoadingTextView;
import com.ido.life.data.api.entity.DialDefinedEntityNew;
import com.ido.life.data.api.entity.DialDefinedFunctionEntity;
import com.ido.life.data.api.entity.DialStyleEntity;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.data.cache.WorldTimeCityManager;
import com.ido.life.dialog.DialFunctionSetDialog;
import com.ido.life.module.device.fragment.MyDialFragment;
import com.ido.life.module.device.presenter.BaseDialPresenter;
import com.ido.life.module.device.presenter.DialDefinedFunctionPresenter;
import com.ido.life.module.device.view.DialDefinedView;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.net.BaseUrl;
import com.ido.life.util.CalendarUtils;
import com.ido.life.util.DevicePicUtils;
import com.ido.life.util.DeviceUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public class DialDefinedFunctionActivity extends BaseActivity<DialDefinedFunctionPresenter> implements DialDefinedView {
    public static final String COME_FROM_MY_DIAL = "come_from_my_dial";
    public static final String DEVICE_ID = "device_id";
    public static final String DIAL = "dial";
    public static final String DIAL_FUNCTION = "dial_function";
    public static final int DIAL_FUNCTIONL_CODE = 6;
    private static final int ITEM_ADD_AND_INSTALL = 1;
    private static final int ITEM_DELETE_DIAL = 5;
    private static final int ITEM_INSTALL_TO_DEVICE = 3;
    private static final int ITEM_REMOVE_FROM_DEVICE = 4;
    private static final int ITEM_SET_CURRENT_DIAL = 2;
    public static final int REQUEST_CODE_ADD_CITY = 7;
    public static HashMap<String, String> functionLaugeMap;
    private String AppImagePath;
    private int clickedItem;
    private String currentDialName;
    private DialDefinedFunctionEntity definedEntity;
    private DialFunctionBGAdapter dialBGAdapter;

    @BindView(R.id.dial_color_ll)
    LinearLayout dialColorLl;
    DialFunctionSetDialog dialFunctionSetDialog;
    private DialFunctionStyleAdapter dialStyleAdapter;

    @BindView(R.id.dial_sv)
    NestedScrollView dialSv;
    private boolean installed;
    private boolean isBuiltInDial;

    @BindView(R.id.iv_dial_rl)
    DialRoundRelativelayout ivDialRl;
    private List<DialDefinedFunctionEntity.DefaultFuns> listDefultFunc;
    private ArrayList<Drawable> listDrawable;
    ArrayList<DialDefinedFunctionEntity.Function> list_function;
    private List<View> list_view;

    @BindView(R.id.clv_city)
    CustomItemLabelView mClvCurrentCity;

    @BindView(R.id.dial_defined_color_ll)
    LinearLayout mColorLL;

    @BindView(R.id.content_ll)
    LinearLayout mContentLL;

    @BindView(R.id.dial_defined_bg_ll)
    LinearLayout mDialBgll;
    private CommBottomConfirmDialog mDialDeleteDialog;

    @BindView(R.id.dial_defined_function_ll)
    LinearLayout mDialFunctionll;
    private MyDialListEntity.DialInfo mDialInfo;

    @BindView(R.id.dial_load_Ll)
    LinearLayout mDialLoadll;
    private CommBottomConfirmDialog mDialMemoryFullDialog;

    @BindView(R.id.dial_defined_style_ll)
    LinearLayout mDialStylell;

    @BindView(R.id.function_edit_rtv)
    RegularTextView mDialfunctionEdit;

    @BindView(R.id.divider_bottom)
    View mDividerBottom;

    @BindView(R.id.divider_top)
    View mDividerTop;
    private boolean mExistZip;

    @BindView(R.id.function_content_rtv)
    RegularTextView mFunctionContentRtv;
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
    private List<DialDefinedFunctionEntity.DefaultFuns> mListResultFunc;

    @BindView(R.id.mtv_description)
    ExpandLayout mMtvDescription;

    @BindView(R.id.mtv_dial_name)
    TextView mMtvDialName;

    @BindView(R.id.mtv_dial_size)
    TextView mMtvDialSize;

    @BindView(R.id.rtv_add_and_install)
    RegularTextView mRtvAddAndInstall;
    private boolean mUpdateZip;

    @BindView(R.id.view_delete_dial)
    LoadingTextView mViewDeleteDial;

    @BindView(R.id.view_set_use)
    LoadingTextView mViewSetUse;

    @BindView(R.id.dial_defined_worldtime_ll)
    LinearLayout mWorldTimeLL;

    @BindView(R.id.recyclerView_defined)
    RecyclerView recyclerViewDefined;

    @BindView(R.id.recyclerView_defined_bg)
    RecyclerView recyclerViewDefined_bg;
    private ArrayList<DialStyleEntity> styleDrawables;
    private Context context = this;
    public int selectDefaultFunc = 0;
    private int colorIndex = 0;
    private int styleIndex = 0;
    private int bgIndex = 0;
    private boolean bgColorFilter = false;
    private int mDeviceId = 0;
    DialDefinedFunctionEntity.PaletteColor paletteColor = null;
    private boolean hasNewVersion = false;
    private int installedVersion = Integer.MAX_VALUE;
    private boolean mIsRemovedFromServer = false;

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void getDefinedEntity(DialDefinedEntityNew dialDefinedEntityNew) {
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_dial_defined_function;
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void onGetDialState(boolean z) {
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mDialInfo = (MyDialListEntity.DialInfo) getIntent().getSerializableExtra("dial");
        if (this.mDialInfo == null) {
            this.mDialInfo = new MyDialListEntity.DialInfo();
        }
        initFunctionLaugeMap();
        this.mIsCurrentDial = this.mDialInfo.isCurrentDial;
        this.installed = this.mDialInfo.isInstalledDial;
        String otaFaceName = this.mDialInfo.getOtaFaceName();
        this.dialSv.setVisibility(8);
        if (!TextUtils.isEmpty(otaFaceName) && (otaFaceName.startsWith(BaseDialPresenter.BUILT_IN_DIAL_NAME_START) || otaFaceName.startsWith(BaseDialPresenter.BUILT_IN_DIAL_NAME_START_OLD))) {
            this.currentDialName = otaFaceName;
            this.isBuiltInDial = true;
            hideDeleteView();
            this.mLayoutDesc.setVisibility(TextUtils.isEmpty(this.mDialInfo.getDescription()) ? 8 : 0);
            this.mContentLL.setVisibility(0);
            this.mDialLoadll.setVisibility(8);
        } else {
            if (TextUtils.isEmpty(otaFaceName) || !otaFaceName.endsWith(".iwf")) {
                otaFaceName = otaFaceName + ".iwf";
            }
            this.currentDialName = otaFaceName;
            ((DialDefinedFunctionPresenter) this.mPresenter).requestDialInfo(this.mDialInfo.getId());
            bindDialInfo2View(this.mDialInfo);
        }
        if (getIntent().getBooleanExtra("come_from_my_dial", false)) {
            this.installed = true;
            this.mRtvAddAndInstall.setText(getString(R.string.device_installed));
            this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_diable_bg);
            if (!this.isBuiltInDial) {
                this.mTitleBar.setTitle(TextUtils.isEmpty(this.mDialInfo.getName()) ? LanguageUtil.getLanguageText(R.string.device_dial) : this.mDialInfo.getName());
            }
        } else {
            this.mLayoutBottom.setVisibility(8);
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
        this.mTitleBar.setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.DialDefinedFunctionActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DialDefinedFunctionActivity.this.mDialInfo.isCollected()) {
                    ((DialDefinedFunctionPresenter) DialDefinedFunctionActivity.this.mPresenter).updateDialCollect(2);
                } else {
                    ((DialDefinedFunctionPresenter) DialDefinedFunctionActivity.this.mPresenter).updateDialCollect(1);
                }
            }
        });
    }

    private void hideDeleteView() {
        this.mViewDeleteDial.setVisibility(8);
        this.mDividerBottom.setVisibility(8);
    }

    public void setFunctionDialogView(DialRoundRelativelayout dialRoundRelativelayout) {
        float fIntValue;
        int i;
        int iIntValue;
        Drawable bitmapFromFileDrawable;
        if (this.definedEntity.getImagegroupsize() != null) {
            ViewGroup.LayoutParams layoutParams = dialRoundRelativelayout.getLayoutParams();
            i = layoutParams.height;
            fIntValue = layoutParams.height / this.definedEntity.getImagegroupsize().getHeight().intValue();
            iIntValue = (int) (this.definedEntity.getImagegroupsize().getWidth().intValue() * fIntValue);
            layoutParams.width = iIntValue;
            dialRoundRelativelayout.setLayoutParams(layoutParams);
        } else {
            fIntValue = 0.0f;
            i = 0;
            iIntValue = 0;
        }
        List<DialDefinedFunctionEntity.BackgroundInfo> backgrounds = this.definedEntity.getBackgrounds();
        for (int i2 = 0; i2 < backgrounds.size(); i2++) {
            DialDefinedFunctionEntity.BackgroundInfo backgroundInfo = backgrounds.get(i2);
            if (!TextUtils.isEmpty(backgroundInfo.getBackgroundColor())) {
                dialRoundRelativelayout.addView(DialDefinedFunctionView.addViewFromColor(this.context, backgroundInfo.getBackgroundColor()));
            }
            if (backgroundInfo.getCanChangeColor() == 0) {
                dialRoundRelativelayout.addView(DialDefinedFunctionView.addView(this.context, this.listDrawable.get(this.bgIndex)));
            } else {
                dialRoundRelativelayout.addView(DialDefinedFunctionView.addView(0, this.context, this.listDrawable.get(this.bgIndex), this.paletteColor, false));
            }
        }
        if (this.definedEntity.getStyles() != null) {
            DialDefinedFunctionEntity.StyleInfo styleInfo = this.definedEntity.getStyles().get(this.styleIndex);
            if (styleInfo.getImages() != null) {
                for (int i3 = 0; i3 < styleInfo.getImages().size(); i3++) {
                    if (MemoryManagerUtil.getSurplusMemory(this.context)) {
                        bitmapFromFileDrawable = BitmapUtil.readBitmapFromFileDrawable(this.AppImagePath + File.separator + styleInfo.getImages().get(i3));
                    } else {
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "内存不足，请清理后重试");
                        NormalToast.showToast(this.context.getString(R.string.insufficient_memory));
                        finish();
                        bitmapFromFileDrawable = null;
                    }
                    if (styleInfo.getCanChangeColor() == 1) {
                        dialRoundRelativelayout.addView(DialDefinedFunctionView.addView(i3, this.context, bitmapFromFileDrawable, this.paletteColor, false));
                    } else {
                        dialRoundRelativelayout.addView(DialDefinedFunctionView.addView(i3, this.context, bitmapFromFileDrawable, null, false));
                    }
                }
            }
        }
        dialRoundRelativelayout.addView(DialDefinedFunctionView.addShadeView(this, iIntValue, i));
        this.listDefultFunc = this.definedEntity.getFuncInfo().getDefaultFuncs();
        List<DialDefinedFunctionEntity.DefaultFuns> list = this.mListResultFunc;
        if (list == null) {
            this.mListResultFunc = new ArrayList();
        } else {
            list.clear();
        }
        try {
            this.mListResultFunc = deepCopy(this.listDefultFunc);
        } catch (IOException | ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        if (this.listDefultFunc != null) {
            this.list_view = new ArrayList();
            for (int i4 = 0; i4 < this.listDefultFunc.size(); i4++) {
                DialDefinedFunctionEntity.DefaultFuns defaultFuns = this.listDefultFunc.get(i4);
                View viewAddEdieView = addEdieView(defaultFuns.getCornerRadius(), defaultFuns.getModifyContent(), i4, defaultFuns.getContentStr(), defaultFuns.getLocation(), BitmapUtil.readBitmapFromFileDrawable(this.AppImagePath + File.separator + defaultFuns.getFuncIcon()), fIntValue);
                dialRoundRelativelayout.addView(viewAddEdieView);
                this.list_view.add(viewAddEdieView);
            }
        }
    }

    public static <T> List<T> deepCopy(List<T> list) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new ObjectOutputStream(byteArrayOutputStream).writeObject(list);
        return (List) new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
    }

    public void modifyFunctionImage(DialDefinedFunctionEntity.Function function) {
        if (this.list_view != null) {
            if (this.listDefultFunc.get(this.selectDefaultFunc).getModifyContent() == 0) {
                this.list_view.get(this.selectDefaultFunc).findViewById(R.id.iv_dial).setBackground(BitmapUtil.readBitmapFromFileDrawable(this.AppImagePath + File.separator + function.getIcon()));
            } else {
                ((TextView) this.list_view.get(this.selectDefaultFunc).findViewById(R.id.tv_dial)).setText(function.getContentStr());
            }
            DialDefinedFunctionEntity.DefaultFuns defaultFuns = this.mListResultFunc.get(this.selectDefaultFunc);
            defaultFuns.setFuncType(function.getType());
            defaultFuns.setFuncIcon(function.getIcon());
            defaultFuns.setContentStr(function.getContentStr());
            this.mListResultFunc.set(this.selectDefaultFunc, defaultFuns);
        }
    }

    private void initRecyclerView() {
        initBGView();
        initStyleView();
    }

    private void initBGView() {
        this.listDrawable = new ArrayList<>();
        this.colorIndex = this.definedEntity.getSelect().getColorselect();
        if (this.definedEntity.getPalettes() != null && this.definedEntity.getPalettes().size() > 0) {
            this.paletteColor = this.definedEntity.getPalettes().get(this.colorIndex);
        } else {
            this.mColorLL.setVisibility(8);
        }
        List<DialDefinedFunctionEntity.BackgroundInfo> backgrounds = this.definedEntity.getBackgrounds();
        if (backgrounds != null) {
            for (int i = 0; i < backgrounds.size(); i++) {
                String str = this.AppImagePath + File.separator + backgrounds.get(i).getImage();
                if (MemoryManagerUtil.getSurplusMemory(this.context)) {
                    Drawable bitmapFromFileDrawable = BitmapUtil.readBitmapFromFileDrawable(str);
                    if (backgrounds.get(i).getCanChangeColor() == 1) {
                        this.bgColorFilter = true;
                        bitmapFromFileDrawable.setColorFilter(Color.parseColor(this.paletteColor.getColors()), PorterDuff.Mode.SRC_ATOP);
                    }
                    this.listDrawable.add(bitmapFromFileDrawable);
                } else {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "内存不足，请清理后重试");
                    NormalToast.showToast(this.context.getString(R.string.insufficient_memory));
                    finish();
                }
            }
            this.dialBGAdapter = new DialFunctionBGAdapter(this.bgIndex, this.listDrawable, this.definedEntity.getImagegroupsize(), this, new DialFunctionBGAdapter.OnItemClickListener() { // from class: com.ido.life.module.device.activity.DialDefinedFunctionActivity.2
                @Override // com.ido.life.customview.recyclerview.DialFunctionBGAdapter.OnItemClickListener
                public void onClick(int i2) {
                    DialDefinedFunctionActivity.this.bgIndex = i2;
                    DialDefinedFunctionActivity.this.setVisPreView();
                    DialDefinedFunctionActivity.this.upDateUseView();
                }

                @Override // com.ido.life.customview.recyclerview.DialFunctionBGAdapter.OnItemClickListener
                public void selectView(int i2) {
                    DialDefinedFunctionActivity.this.bgIndex = i2;
                }
            });
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(0);
            this.recyclerViewDefined_bg.setLayoutManager(linearLayoutManager);
            this.recyclerViewDefined_bg.setAdapter(this.dialBGAdapter);
            if (this.listDrawable.size() < 2) {
                this.mDialBgll.setVisibility(8);
            }
        }
    }

    private void initStyleView() {
        DialDefinedFunctionEntity.BackgroundInfo backgroundInfo;
        Drawable drawable;
        this.styleDrawables = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        this.recyclerViewDefined.setLayoutManager(linearLayoutManager);
        if (this.definedEntity.getStyles() != null) {
            for (int i = 0; i < this.definedEntity.getStyles().size(); i++) {
                DialStyleEntity dialStyleEntity = new DialStyleEntity();
                ArrayList arrayList = new ArrayList();
                DialDefinedFunctionEntity.StyleInfo styleInfo = this.definedEntity.getStyles().get(i);
                if (styleInfo.getName().equals(this.definedEntity.getSelect().getStyle())) {
                    this.styleIndex = i;
                }
                if (styleInfo.getImages() != null) {
                    for (int i2 = 0; i2 < styleInfo.getImages().size(); i2++) {
                        String str = this.AppImagePath + File.separator + styleInfo.getImages().get(i2);
                        if (new File(str).exists()) {
                            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "文件存在：" + str);
                        }
                        if (MemoryManagerUtil.getSurplusMemory(this.context)) {
                            arrayList.add(BitmapUtil.readBitmapFromFileDrawable(str));
                        } else {
                            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "内存不足，请清理后重试");
                            NormalToast.showToast(this.context.getString(R.string.insufficient_memory));
                            finish();
                        }
                    }
                    dialStyleEntity.setStyleimg(arrayList);
                    dialStyleEntity.setDescription(styleInfo.getName());
                    dialStyleEntity.setCanChangeColor(styleInfo.getCanChangeColor());
                    this.styleDrawables.add(dialStyleEntity);
                }
            }
        } else {
            this.mDialStylell.setVisibility(8);
        }
        if (this.styleDrawables.size() > 0) {
            if (this.definedEntity.getBackgrounds() != null) {
                DialDefinedFunctionEntity.BackgroundInfo backgroundInfo2 = this.definedEntity.getBackgrounds().get(this.bgIndex);
                drawable = this.listDrawable.get(this.bgIndex);
                backgroundInfo = backgroundInfo2;
            } else {
                backgroundInfo = null;
                drawable = null;
            }
            this.dialStyleAdapter = new DialFunctionStyleAdapter(backgroundInfo, drawable, this.styleIndex, this.styleDrawables, this.paletteColor, this.definedEntity.getImagegroupsize(), this, new DialFunctionStyleAdapter.OnItemClickListener() { // from class: com.ido.life.module.device.activity.DialDefinedFunctionActivity.3
                @Override // com.ido.life.customview.recyclerview.DialFunctionStyleAdapter.OnItemClickListener
                public void onClick(int i3) {
                    DialDefinedFunctionActivity.this.dialStyleAdapter.setCurrentIndex(i3, true);
                    DialDefinedFunctionActivity.this.upDateUseView();
                }

                @Override // com.ido.life.customview.recyclerview.DialFunctionStyleAdapter.OnItemClickListener
                public void selectView(int i3) {
                    DialDefinedFunctionActivity.this.styleIndex = i3;
                    DialDefinedFunctionActivity.this.definedEntity.getSelect().setStyle(DialDefinedFunctionActivity.this.definedEntity.getStyles().get(DialDefinedFunctionActivity.this.styleIndex).getName());
                    DialDefinedFunctionActivity.this.setVisPreView();
                }
            }, this.mDeviceId);
            this.recyclerViewDefined.setAdapter(this.dialStyleAdapter);
            return;
        }
        setVisPreView();
    }

    public View addView(String str) {
        final View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.item_color_dial, (ViewGroup) null);
        DialPercentView dialPercentView = (DialPercentView) viewInflate.findViewById(R.id.color_style_img);
        final RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.dial_color_rl);
        dialPercentView.init(str);
        if (this.definedEntity.getSelect().getColorselect() == this.dialColorLl.getChildCount()) {
            relativeLayout.setBackgroundResource(R.drawable.dial_color_checks_select);
            this.colorIndex = this.definedEntity.getSelect().getColorselect();
        } else {
            relativeLayout.setBackgroundResource(R.drawable.dial_color_checks_not_select);
        }
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.DialDefinedFunctionActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DialDefinedFunctionActivity.this.setDoalColorBg();
                DialDefinedFunctionActivity.this.upDateUseView();
                relativeLayout.setBackgroundResource(R.drawable.dial_color_checks_select);
                DialDefinedFunctionActivity dialDefinedFunctionActivity = DialDefinedFunctionActivity.this;
                dialDefinedFunctionActivity.colorIndex = dialDefinedFunctionActivity.dialColorLl.indexOfChild(viewInflate);
                DialDefinedFunctionActivity.this.definedEntity.getSelect().setColorselect(DialDefinedFunctionActivity.this.colorIndex);
                DialDefinedFunctionActivity dialDefinedFunctionActivity2 = DialDefinedFunctionActivity.this;
                dialDefinedFunctionActivity2.paletteColor = dialDefinedFunctionActivity2.definedEntity.getPalettes().get(DialDefinedFunctionActivity.this.colorIndex);
                if (DialDefinedFunctionActivity.this.bgColorFilter) {
                    for (int i = 0; i < DialDefinedFunctionActivity.this.listDrawable.size(); i++) {
                        ((Drawable) DialDefinedFunctionActivity.this.listDrawable.get(i)).setColorFilter(Color.parseColor(DialDefinedFunctionActivity.this.paletteColor.getColors()), PorterDuff.Mode.SRC_ATOP);
                    }
                }
                DialDefinedFunctionActivity.this.dialStyleAdapter.upDateSelectColor(DialDefinedFunctionActivity.this.definedEntity.getPalettes().get(DialDefinedFunctionActivity.this.colorIndex));
            }
        });
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void setVisPreView() {
        float fIntValue;
        Drawable bitmapFromFileDrawable;
        this.ivDialRl.removeAllViews();
        if (this.definedEntity.getImagegroupsize() != null) {
            ViewGroup.LayoutParams layoutParams = this.ivDialRl.getLayoutParams();
            fIntValue = layoutParams.height / this.definedEntity.getImagegroupsize().getHeight().intValue();
            layoutParams.width = (int) (this.definedEntity.getImagegroupsize().getWidth().intValue() * fIntValue);
            this.ivDialRl.setLayoutParams(layoutParams);
        } else {
            fIntValue = 0.0f;
        }
        List<DialDefinedFunctionEntity.BackgroundInfo> backgrounds = this.definedEntity.getBackgrounds();
        if (backgrounds != null) {
            for (int i = 0; i < backgrounds.size(); i++) {
                DialDefinedFunctionEntity.BackgroundInfo backgroundInfo = backgrounds.get(i);
                if (!TextUtils.isEmpty(backgroundInfo.getBackgroundColor())) {
                    this.ivDialRl.addView(DialDefinedFunctionView.addViewFromColor(this.context, backgroundInfo.getBackgroundColor()));
                }
                if (backgroundInfo.getCanChangeColor() == 0) {
                    this.ivDialRl.addView(DialDefinedFunctionView.addView(this.context, this.listDrawable.get(this.bgIndex)));
                } else {
                    this.ivDialRl.addView(DialDefinedFunctionView.addView(0, this.context, this.listDrawable.get(this.bgIndex), this.paletteColor, false));
                }
            }
        }
        if (this.definedEntity.getStyles() != null) {
            DialDefinedFunctionEntity.StyleInfo styleInfo = this.definedEntity.getStyles().get(this.styleIndex);
            if (styleInfo.getImages() != null) {
                for (int i2 = 0; i2 < styleInfo.getImages().size(); i2++) {
                    if (MemoryManagerUtil.getSurplusMemory(this.context)) {
                        bitmapFromFileDrawable = BitmapUtil.readBitmapFromFileDrawable(this.AppImagePath + File.separator + styleInfo.getImages().get(i2));
                    } else {
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "内存不足，请清理后重试");
                        NormalToast.showToast(this.context.getString(R.string.insufficient_memory));
                        finish();
                        bitmapFromFileDrawable = null;
                    }
                    if (styleInfo.getCanChangeColor() == 1) {
                        this.ivDialRl.addView(DialDefinedFunctionView.addView(i2, this.context, bitmapFromFileDrawable, this.paletteColor, false));
                    } else {
                        this.ivDialRl.addView(DialDefinedFunctionView.addView(i2, this.context, bitmapFromFileDrawable, null, false));
                    }
                }
            }
        }
        if (this.definedEntity.getClock() != null) {
            DialDefinedFunctionEntity.ClockInfo clock = this.definedEntity.getClock();
            if (clock.getType() == 0) {
                String str = this.AppImagePath + File.separator + this.definedEntity.getClock().getImage();
                if (MemoryManagerUtil.getSurplusMemory(this.context)) {
                    this.ivDialRl.addView(DialDefinedFunctionView.addView(this.context, BitmapUtil.readBitmapFromFileDrawable(str)));
                }
            } else {
                this.mDialFunctionll.setVisibility(8);
                this.mWorldTimeLL.setVisibility(0);
                List<Integer> cityLocation = this.definedEntity.getClock().getCityLocation();
                if (TextUtils.isEmpty(clock.getCityName())) {
                    WorldTimeCity defaultTimeZoneCityInfo = WorldTimeCityManager.getInstance().getDefaultTimeZoneCityInfo();
                    clock.setCityName(defaultTimeZoneCityInfo.getName());
                    clock.setGetAbbreviation(defaultTimeZoneCityInfo.getAbbreviation());
                    clock.setOffSetTime(CalendarUtils.getTimezoneOffsetInMin(TimeZone.getTimeZone(defaultTimeZoneCityInfo.getTimeZoneName())));
                }
                this.mClvCurrentCity.setValue(clock.getCityName());
                ((DialDefinedFunctionPresenter) this.mPresenter).saveDialLog("---------wordtime:" + clock.getCityName());
                TextView textView = new TextView(this);
                textView.setText(clock.getGetAbbreviation());
                textView.setTextColor(Color.parseColor("#7F7F7F"));
                textView.setTextSize(10.0f);
                textView.setTypeface(Typeface.defaultFromStyle(1));
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams2.leftMargin = ((int) (cityLocation.get(0).intValue() * fIntValue)) + 4;
                layoutParams2.topMargin = ((int) (cityLocation.get(1).intValue() * fIntValue)) - 4;
                textView.setLayoutParams(layoutParams2);
                this.ivDialRl.addView(textView);
            }
        }
        if (this.definedEntity.getFuncInfo() != null) {
            List<DialDefinedFunctionEntity.DefaultFuns> defaultFuncs = this.definedEntity.getFuncInfo().getDefaultFuncs();
            if (defaultFuncs != null) {
                StringBuilder sb = new StringBuilder();
                for (int i3 = 0; i3 < defaultFuncs.size(); i3++) {
                    DialDefinedFunctionEntity.DefaultFuns defaultFuns = defaultFuncs.get(i3);
                    sb.append(DeviceUtil.getStringFromName(this, functionLaugeMap.get(defaultFuns.getFuncType())) + AppInfo.DELIM);
                    if (defaultFuns.getModifyContent() == 0) {
                        this.ivDialRl.addView(addImageView(i3, defaultFuns.getContentStr(), defaultFuns.getLocation(), BitmapUtil.readBitmapFromFileDrawable(this.AppImagePath + File.separator + defaultFuns.getFuncIcon()), fIntValue));
                    } else {
                        TextView textView2 = new TextView(this);
                        textView2.setText(defaultFuns.getContentStr());
                        textView2.setTextColor(-1);
                        textView2.setGravity(1);
                        textView2.setTextSize(10.0f);
                        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
                        layoutParams3.leftMargin = (int) (defaultFuns.getLocation().get(0).intValue() * fIntValue);
                        layoutParams3.topMargin = (int) (defaultFuns.getLocation().get(1).intValue() * fIntValue);
                        layoutParams3.addRule(14);
                        layoutParams3.addRule(4);
                        textView2.setLayoutParams(layoutParams3);
                        this.ivDialRl.addView(textView2);
                    }
                }
                if (sb.toString().length() > 0) {
                    this.mFunctionContentRtv.setText(sb.toString().substring(0, sb.toString().length() - 1));
                    return;
                }
                return;
            }
            return;
        }
        this.mDialFunctionll.setVisibility(8);
    }

    public View addImageView(final int i, String str, List<Integer> list, Drawable drawable, float f2) {
        ImageView imageView = new ImageView(this.context);
        imageView.setBackground(drawable);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (list.get(2).intValue() * f2), (int) (list.get(3).intValue() * f2));
        layoutParams.leftMargin = (int) (list.get(0).intValue() * f2);
        layoutParams.topMargin = (int) (list.get(1).intValue() * f2);
        imageView.setLayoutParams(layoutParams);
        imageView.setTag(Integer.valueOf(i));
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.DialDefinedFunctionActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.e("dialdefine", i + "被点击了" + view.getTag());
                DialDefinedFunctionActivity.this.selectDefaultFunc = ((Integer) view.getTag()).intValue();
            }
        });
        return imageView;
    }

    public View addEdieView(final int i, int i2, int i3, String str, List<Integer> list, Drawable drawable, float f2) {
        View viewInflate = LayoutInflater.from(this).inflate(R.layout.item_dial_function_edit, (ViewGroup) null);
        final RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.rtl_dial);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
        if (i2 == 0) {
            layoutParams.width = (int) (list.get(2).intValue() * f2);
            layoutParams.height = (int) (list.get(3).intValue() * f2);
            layoutParams.leftMargin = (int) (list.get(0).intValue() * f2);
            layoutParams.topMargin = (int) (list.get(1).intValue() * f2);
            ((ImageView) viewInflate.findViewById(R.id.iv_dial)).setBackground(drawable);
        } else {
            layoutParams.topMargin = ((int) (list.get(1).intValue() * f2)) - 10;
            layoutParams.leftMargin = 6;
            layoutParams.bottomMargin = 6;
            layoutParams.rightMargin = 6;
            ((TextView) viewInflate.findViewById(R.id.tv_dial)).setText(str);
        }
        relativeLayout.setLayoutParams(layoutParams);
        if (i == 0) {
            relativeLayout.setBackgroundResource(R.drawable.dial_function_edit_rectangle_defualt_bg);
        } else {
            relativeLayout.setBackgroundResource(R.drawable.dial_function_edit_defualt_bg);
        }
        relativeLayout.setTag(Integer.valueOf(i3));
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.DialDefinedFunctionActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DialDefinedFunctionActivity.this.selectDefaultFunc = ((Integer) view.getTag()).intValue();
                if (DialDefinedFunctionActivity.this.dialFunctionSetDialog != null) {
                    DialDefinedFunctionActivity.this.dialFunctionSetDialog.upDateSelectDefautFunction(DialDefinedFunctionActivity.this.getFunctionPosition());
                }
                DialDefinedFunctionActivity.this.initEditView();
                if (i == 0) {
                    relativeLayout.setBackgroundResource(R.drawable.dial_function_edit_rectangle_select_bg);
                } else {
                    relativeLayout.setBackgroundResource(R.drawable.dial_function_edit_select_bg);
                }
            }
        });
        if (i3 == 0) {
            this.selectDefaultFunc = 0;
            if (i == 0) {
                relativeLayout.setBackgroundResource(R.drawable.dial_function_edit_rectangle_select_bg);
            } else {
                relativeLayout.setBackgroundResource(R.drawable.dial_function_edit_select_bg);
            }
        }
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initEditView() {
        for (int i = 0; i < this.list_view.size(); i++) {
            ((RelativeLayout) this.list_view.get(i).findViewById(R.id.rtl_dial)).setBackgroundResource(R.drawable.dial_function_edit_defualt_bg);
        }
    }

    private void shadeAnim(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        alphaAnimation.setDuration(1000L);
        alphaAnimation.setRepeatCount(-1);
        alphaAnimation.setRepeatMode(2);
        alphaAnimation.start();
        view.setAnimation(alphaAnimation);
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
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DialDefinedFunctionActivity$DwmA5owKE3W5Q42pndL_2V1yaIM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$DialDefinedFunctionActivity(view);
            }
        });
        if (!this.isBuiltInDial) {
            ((DialDefinedFunctionPresenter) this.mPresenter).getInstalledDialInfo();
        }
        initState();
    }

    public /* synthetic */ void lambda$initEvent$0$DialDefinedFunctionActivity(View view) {
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
        if (this.isBuiltInDial) {
            return;
        }
        this.mViewDeleteDial.setVisibility(NetworkUtil.isConnected(this) ? 0 : 8);
        this.mDividerBottom.setVisibility(NetworkUtil.isConnected(this) ? 0 : 8);
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void getDefinedFuctionEntity(DialDefinedFunctionEntity dialDefinedFunctionEntity) {
        this.mContentLL.setVisibility(0);
        this.mDialLoadll.setVisibility(8);
        if (dialDefinedFunctionEntity != null) {
            ((DialDefinedFunctionPresenter) this.mPresenter).saveDialLog("DialDefinedFunctionEntity：" + dialDefinedFunctionEntity.toString());
            this.dialSv.setVisibility(0);
            this.mDeviceId = ((DialDefinedFunctionPresenter) this.mPresenter).getDeviceInfo().mDeviceId;
            this.AppImagePath = FileDialDefinedUtil.appFilePngFunction(this.context, this.mDeviceId, this.mDialInfo.getOtaFaceName());
            this.definedEntity = dialDefinedFunctionEntity;
            if (dialDefinedFunctionEntity.getImagegroupsize() != null && dialDefinedFunctionEntity.getImagegroupsize().getWidth() != null) {
                MemoryManagerUtil.WIDTH = dialDefinedFunctionEntity.getImagegroupsize().getWidth().intValue();
                MemoryManagerUtil.HEIGHT = dialDefinedFunctionEntity.getImagegroupsize().getHeight().intValue();
            }
            this.mMtvDescription.setContent(this.mDialInfo.getDescription());
            initRecyclerView();
            if (dialDefinedFunctionEntity.getPalettes() != null && dialDefinedFunctionEntity.getPalettes().size() > 0) {
                for (int i = 0; i < dialDefinedFunctionEntity.getPalettes().size(); i++) {
                    this.dialColorLl.addView(addView(dialDefinedFunctionEntity.getPalettes().get(i).getColors()));
                }
                return;
            }
            this.mColorLL.setVisibility(8);
            return;
        }
        ((DialDefinedFunctionPresenter) this.mPresenter).saveDialLog("json格式化错误或不是DialDefinedEntityNew固定的格式类型");
        NormalToast.showToast(this.context.getString(R.string.device_install_failed));
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
        if (!((DialDefinedFunctionPresenter) this.mPresenter).isConnected()) {
            showToast(R.string.device_pls_connect_device);
            return;
        }
        if (this.mDialDeleteDialog == null) {
            this.mDialDeleteDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.device_delete_dial), getLanguageText(R.string.dial_delete_tip), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DialDefinedFunctionActivity$hU_8ajeKwrZnO7kiu-ojOoeBErY
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showDeleteDialog$1$DialDefinedFunctionActivity(view);
                }
            });
        }
        this.mDialDeleteDialog.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showDeleteDialog$1$DialDefinedFunctionActivity(View view) {
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
        ((DialDefinedFunctionPresenter) this.mPresenter).removeDialFromDevice(this.currentDialName, z);
    }

    @OnClick({R.id.rtv_add_and_install, R.id.view_set_use, R.id.view_delete_dial, R.id.function_edit_rtv, R.id.dial_defined_worldtime_ll})
    public void onViewClicked(View view) {
        List<DialDefinedFunctionEntity.FunctionList> list;
        if (((DialDefinedFunctionPresenter) this.mPresenter).isSetting) {
        }
        switch (view.getId()) {
            case R.id.dial_defined_worldtime_ll /* 2131362113 */:
                SingleTaskIntent singleTaskIntent = new SingleTaskIntent(this, WorldTimeCityChooseActivity.class);
                singleTaskIntent.putExtra("from", true);
                startActivityForResult(singleTaskIntent, 7);
                break;
            case R.id.function_edit_rtv /* 2131362235 */:
                if (this.definedEntity.getFuncInfo() != null && this.definedEntity.getFuncInfo().getDefaultFuncs() != null && (list = this.definedEntity.getFuncInfo().getList()) != null) {
                    this.list_function = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        DialDefinedFunctionEntity.FunctionList functionList = list.get(i);
                        if (!"noParent".equals(functionList.getType())) {
                            DialDefinedFunctionEntity.Function function = new DialDefinedFunctionEntity.Function();
                            function.setId(-1);
                            function.setType(functionList.getType());
                            this.list_function.add(function);
                        }
                        functionList.getItems().get(0).setId(100);
                        functionList.getItems().get(functionList.getItems().size() - 1).setId(200);
                        if (functionList.getItems().size() == 1) {
                            functionList.getItems().get(0).setId(300);
                        }
                        this.list_function.addAll(functionList.getItems());
                    }
                    this.selectDefaultFunc = 0;
                    this.dialFunctionSetDialog = DialFunctionSetDialog.getInstance(this.list_function, getFunctionPosition(), this.definedEntity.getFuncInfo().isIssupportClose());
                    this.dialFunctionSetDialog.show(getSupportFragmentManager());
                    this.dialFunctionSetDialog.setListener(new DialFunctionSetDialog.DailFunctionListener() { // from class: com.ido.life.module.device.activity.DialDefinedFunctionActivity.7
                        @Override // com.ido.life.dialog.DialFunctionSetDialog.DailFunctionListener
                        public void onCancel() {
                        }

                        @Override // com.ido.life.dialog.DialFunctionSetDialog.DailFunctionListener
                        public void onConfirm() {
                            DialDefinedFunctionActivity.this.listDefultFunc.clear();
                            DialDefinedFunctionActivity.this.listDefultFunc.addAll(DialDefinedFunctionActivity.this.mListResultFunc);
                            DialDefinedFunctionActivity.this.setVisPreView();
                            DialDefinedFunctionActivity.this.upDateUseView();
                        }
                    });
                    break;
                }
                break;
            case R.id.rtv_add_and_install /* 2131363380 */:
                if (HomeFragmentPresenter.mIsSyncing) {
                    showToast(getLanguageText(R.string.syncing_pls_try_again_later));
                } else if (HomeFragmentPresenter.mIsTelephone) {
                    showToast(getLanguageText(R.string.sport_start_incalling));
                } else if (this.hasNewVersion) {
                    FileDialDefinedUtil.deleteDirectory(FileDialDefinedUtil.jsonDirFunction(this.context, this.mDeviceId, this.mDialInfo.getOtaFaceName()));
                    File file = new File(FileDialDefinedUtil.saveZipDir(this.context), this.mDialInfo.getOtaFaceName() + FileDialDefinedUtil.FILE_ZIP);
                    if (file.exists()) {
                        file.delete();
                    }
                    this.clickedItem = 1;
                    updateItemProgress(1, 0);
                    setDownDial();
                } else {
                    this.clickedItem = 1;
                    setSaveDial();
                }
                break;
            case R.id.view_delete_dial /* 2131364406 */:
                if (!this.mDialInfo.isEnabled() && !this.installed) {
                    deleteDial(true);
                } else {
                    this.mUpdateZip = false;
                    showDeleteDialog();
                }
                break;
            case R.id.view_set_use /* 2131364430 */:
                if (HomeFragmentPresenter.mIsSyncing) {
                    showToast(getLanguageText(R.string.syncing_pls_try_again_later));
                } else if (HomeFragmentPresenter.mIsTelephone) {
                    showToast(getLanguageText(R.string.sport_start_incalling));
                } else {
                    this.clickedItem = 2;
                    setSaveDial();
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getFunctionPosition() {
        DialDefinedFunctionEntity.DefaultFuns defaultFuns = this.definedEntity.getFuncInfo().getDefaultFuncs().get(this.selectDefaultFunc);
        for (int i = 0; i < this.list_function.size(); i++) {
            DialDefinedFunctionEntity.Function function = this.list_function.get(i);
            if (defaultFuns.getFuncType().equals(function.getType()) && function.getId() != -1) {
                return i;
            }
        }
        return 0;
    }

    private void setDownDial() {
        ((DialDefinedFunctionPresenter) this.mPresenter).setIsDownDial(true);
        if (((DialDefinedFunctionPresenter) this.mPresenter).isConnected() && this.mDialInfo.getOtaFaceVersion() != null) {
            ((DialDefinedFunctionPresenter) this.mPresenter).downDialZip(this.mDialInfo, false, this.context);
        } else {
            showToast(getLanguageText(R.string.device_download_failed_tip));
        }
    }

    private void setSaveDial() {
        MyDialListEntity.DialInfo dialInfo = this.mDialInfo;
        if (dialInfo == null || dialInfo.getOtaFaceVersion() == null) {
            return;
        }
        if (this.installed) {
            ((DialDefinedFunctionPresenter) this.mPresenter).saveDialLog("已安装，重新安装");
            updateItemProgress(2, 0);
            saveDial();
            return;
        }
        if (((DialDefinedFunctionPresenter) this.mPresenter).hasMemoryInstall(this.mDialInfo.getOtaFaceVersion().getSize())) {
            updateItemProgress(2, 0);
            if (!this.installed) {
                ((DialDefinedFunctionPresenter) this.mPresenter).addDefinedDial2Account(true);
                return;
            } else {
                saveDial();
                return;
            }
        }
        ((DialDefinedFunctionPresenter) this.mPresenter).saveDialLog(AMapException.ERROR_NOT_ENOUGH_SPACE);
        showDialMemoryFullDialog();
    }

    private void bindDialInfo2View(MyDialListEntity.DialInfo dialInfo) {
        this.mMtvDialName.setText(dialInfo.getName());
        this.mLayoutDesc.setVisibility(TextUtils.isEmpty(dialInfo.getDescription()) ? 8 : 0);
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void onGetDialInfo(MyDialListEntity.DialInfo dialInfo) {
        this.mDialInfo = dialInfo;
        this.mDeviceId = ((DialDefinedFunctionPresenter) this.mPresenter).getDeviceInfo().mDeviceId;
        if (!new File(FileDialDefinedUtil.jsonDirFunctionAppFile(this.context, this.mDeviceId, this.mDialInfo.getOtaFaceName())).exists()) {
            ((DialDefinedFunctionPresenter) this.mPresenter).saveDialLog("app.json 不存在");
            FileDialDefinedUtil.deleteDirectory(FileDialDefinedUtil.jsonDirFunction(this.context, this.mDeviceId, this.mDialInfo.getOtaFaceName()));
            this.mExistZip = FileDialDefinedUtil.checkFileExist(FileDialDefinedUtil.saveZipDir(this.context), this.mDialInfo.getOtaFaceName());
            if (!this.mExistZip) {
                ((DialDefinedFunctionPresenter) this.mPresenter).saveDialLog("zip 不存在,开始下载");
                setDownDial();
            } else {
                ((DialDefinedFunctionPresenter) this.mPresenter).unpackZip(this.context, this.mDialInfo);
            }
        } else {
            ((DialDefinedFunctionPresenter) this.mPresenter).saveDialLog("app.json 存在");
            ((DialDefinedFunctionPresenter) this.mPresenter).getDefinedEntity(this.context, this.mDialInfo.getOtaFaceName());
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

    private void updateRemovedView() {
        if (this.mIsRemovedFromServer) {
            this.mRtvAddAndInstall.setEnabled(false);
            this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_diable_bg);
            if (!this.installed && NetworkUtil.isConnected(this)) {
                ((DialDefinedFunctionPresenter) this.mPresenter).saveDialLog("updateRemovedView 处理已下架，未安装情况");
                this.mRtvAddAndInstall.setText(getString(R.string.dial_aready_undercarriage));
                this.mViewSetUse.setVisibility(8);
                this.mDividerTop.setVisibility(8);
                this.mViewDeleteDial.setVisibility(0);
                this.mViewDeleteDial.setText(getString(R.string.dial_delete_from_record));
                this.mLayoutBottom.setVisibility(0);
                return;
            }
            ((DialDefinedFunctionPresenter) this.mPresenter).saveDialLog("updateRemovedView 处理已下架，已安装情况");
            this.mRtvAddAndInstall.setText(getString(R.string.device_installed));
        }
    }

    private void refreshViewState() {
        if (this.mIsCurrentDial || this.installed) {
            this.mRtvAddAndInstall.setText(getString(R.string.device_installed));
            this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_diable_bg);
            this.mRtvAddAndInstall.setVisibility(0);
            this.mRtvAddAndInstall.setEnabled(false);
            this.mLayoutBottom.setVisibility(0);
            this.mLayoutDialInfo.setVisibility(0);
            if (!this.isBuiltInDial) {
                this.mTitleBar.setTitle(TextUtils.isEmpty(this.mDialInfo.getName()) ? LanguageUtil.getLanguageText(R.string.device_dial) : this.mDialInfo.getName());
            }
            if (((DialDefinedFunctionPresenter) this.mPresenter).canDelete()) {
                updateDeleteViewState();
            } else {
                hideDeleteView();
            }
        } else {
            this.mLayoutBottom.setVisibility(8);
            if (!((DialDefinedFunctionPresenter) this.mPresenter).isOffline()) {
                this.mRtvAddAndInstall.setVisibility(0);
                this.mRtvAddAndInstall.setEnabled(true);
            } else {
                this.mRtvAddAndInstall.setVisibility(8);
            }
            this.mLayoutDialInfo.setVisibility(0);
        }
        updateRemovedView();
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void onSwitchDialStart() {
        int i = this.clickedItem;
        if (i == 1) {
            startTopAnimation();
            this.mRtvAddAndInstall.setText(getLanguageText(R.string.device_setting));
        } else {
            if (i != 2) {
                return;
            }
            this.mViewSetUse.startAnimation();
            this.mViewSetUse.setText(getLanguageText(R.string.device_setting));
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
        if (((DialDefinedFunctionPresenter) this.mPresenter).isSetting) {
            return;
        }
        if (!TextUtils.isEmpty(this.currentDialName)) {
            boolean zContains = str.contains(this.currentDialName);
            if (!this.isBuiltInDial && this.mIsCurrentDial != zContains) {
                this.mIsCurrentDial = zContains;
                this.installed = true;
            }
        }
        refreshViewState();
        if (this.hasNewVersion) {
            this.mRtvAddAndInstall.setEnabled(true);
            this.mRtvAddAndInstall.setText(getLanguageText(R.string.device_update_dial));
            this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_bg);
            this.mRtvAddAndInstall.setTextColor(getColor(R.color.white));
            return;
        }
        this.mViewSetUse.setEnabled(!this.mIsCurrentDial);
        this.mViewSetUse.setText(getLanguageText(this.mIsCurrentDial ? R.string.device_using : R.string.device_set_current_dial));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upDateUseView() {
        if (this.mIsCurrentDial) {
            this.mViewSetUse.setEnabled(true);
            this.mViewSetUse.setText(getLanguageText(R.string.device_set_current_dial));
        }
    }

    private void saveDial() {
        ((DialDefinedFunctionPresenter) this.mPresenter).saveDialDefined(this.context, this.mDialInfo, this.definedEntity, this.colorIndex, this.styleIndex, this.ivDialRl, this.bgIndex);
        String strFirewareFunctionZip = FileDialDefinedUtil.firewareFunctionZip(this.context, this.mDeviceId, this.mDialInfo.getOtaFaceName());
        ((DialDefinedFunctionPresenter) this.mPresenter).saveDialLog("自定义表盘上传路径filepath:" + strFirewareFunctionZip);
        ((DialDefinedFunctionPresenter) this.mPresenter).installDial2Device(strFirewareFunctionZip, this.mDialInfo.getOtaFaceName(), this.installed);
    }

    private void showDialMemoryFullDialog() {
        if (!((DialDefinedFunctionPresenter) this.mPresenter).isConnected()) {
            showToast(R.string.device_pls_connect_device);
            return;
        }
        if (this.mDialMemoryFullDialog == null) {
            this.mDialMemoryFullDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.dial_memory_full), getLanguageText(R.string.dial_memory_full_tip), getLanguageText(R.string.go_settting), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DialDefinedFunctionActivity$E98if3IHxnVqkx7KEQewq4UUz0M
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showDialMemoryFullDialog$2$DialDefinedFunctionActivity(view);
                }
            });
        }
        this.mDialMemoryFullDialog.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showDialMemoryFullDialog$2$DialDefinedFunctionActivity(View view) {
        goDeleteDial();
    }

    private void goDeleteDial() {
        startActivity(new Intent(this, (Class<?>) MyDialEditActivity.class));
    }

    @Override // com.ido.life.module.device.view.BaseDialView
    public void onGetInstalledDialList(List<String> list) {
        if (((DialDefinedFunctionPresenter) this.mPresenter).isSetting) {
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
        if (((DialDefinedFunctionPresenter) this.mPresenter).isSetting || list == null || list.isEmpty()) {
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

    private void showDialUpdate() {
        try {
            MyDialListEntity.DialInfo.OtaFaceVersion otaFaceVersion = this.mDialInfo.getOtaFaceVersion();
            if (otaFaceVersion == null || Integer.parseInt(otaFaceVersion.getVersion()) <= this.installedVersion || TextUtils.isEmpty(otaFaceVersion.getLinkUrl())) {
                return;
            }
            this.hasNewVersion = true;
            this.mRtvAddAndInstall.setEnabled(true);
            this.mRtvAddAndInstall.setText(getLanguageText(R.string.device_update_dial));
            this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_bg);
            this.mRtvAddAndInstall.setTextColor(getColor(R.color.white));
        } catch (Exception e2) {
            ((DialDefinedFunctionPresenter) this.mPresenter).saveDialLog("onGetDialInfo Exception：" + e2.toString());
        }
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
        updateItemProgress(i, i2);
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

    private void updateTopLabelProgress(int i, int i2) {
        if (i2 < 100) {
            if (i2 == 0) {
                startTopAnimation();
            }
            RegularTextView regularTextView = this.mRtvAddAndInstall;
            regularTextView.setText(String.format(getLanguageText(i == 1 ? R.string.device_downloading_x : R.string.device_installing_x), Integer.valueOf(i2)));
            return;
        }
        this.mRtvAddAndInstall.setTextColor(getColor(R.color.color_545657));
        this.mRtvAddAndInstall.setBackgroundResource(R.color.translate);
        if (i == 1) {
            stopTopAnimation();
            this.mRtvAddAndInstall.setText(getLanguageText(R.string.device_install_success));
            if (this.hasNewVersion) {
                this.mIvLoading.clearAnimation();
                this.mIvLoading.setVisibility(8);
                this.mRtvAddAndInstall.setText(getString(R.string.device_installed));
                this.mRtvAddAndInstall.setBackgroundResource(R.drawable.shape_current_dial_btn_diable_bg);
                this.mViewSetUse.setEnabled(true);
                this.mViewSetUse.setText(getLanguageText(this.mIsCurrentDial ? R.string.dial_device_update : R.string.device_install_to_device));
            }
        }
    }

    private void stopTopAnimation() {
        this.mIvLoading.clearAnimation();
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
        this.mDeviceId = ((DialDefinedFunctionPresenter) this.mPresenter).getDeviceInfo().mDeviceId;
        this.dialSv.setVisibility(0);
        ((DialDefinedFunctionPresenter) this.mPresenter).saveDialLog("download success: 开始解压");
        FileDialDefinedUtil.unpackCopyZip(FileDialDefinedUtil.jsonDirNew(this.context, this.mDeviceId) + File.separator, FileDialDefinedUtil.saveZipDir(this.context) + this.mDialInfo.getOtaFaceName() + FileDialDefinedUtil.FILE_ZIP);
        ((DialDefinedFunctionPresenter) this.mPresenter).getDefinedEntity(this.context, this.mDialInfo.getOtaFaceName());
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
        } else if (i == 3 || i == 5) {
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
        } else if (i == 3 || i == 5) {
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
        DevicePicUtils.spSaveDial(((DialDefinedFunctionPresenter) this.mPresenter).getDeviceInfo().mDeviceAddress + "", this.currentDialName, true);
    }

    @Override // com.ido.life.module.device.view.DialDefinedView
    public void onDialInstallFailed() {
        showCmdResultToast(false);
        updateFailedView();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (((DialDefinedFunctionPresenter) this.mPresenter).isSetting) {
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

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 6) {
            if (intent != null) {
                String stringExtra = intent.getStringExtra(DIAL_FUNCTION);
                boolean booleanExtra = intent.getBooleanExtra(WallpaperDialActivity.WALLPAPER_CHANGED, false);
                if ((TextUtils.isEmpty(stringExtra) || TextUtils.equals(stringExtra, this.currentDialName)) && !booleanExtra) {
                    return;
                }
                onGetCurrentDial(stringExtra);
                return;
            }
            return;
        }
        if (i != 7 || intent == null) {
            return;
        }
        WorldTimeCity worldTimeCity = (WorldTimeCity) intent.getSerializableExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME);
        DialDefinedFunctionEntity.ClockInfo clock = this.definedEntity.getClock();
        clock.setCityName(worldTimeCity.getName());
        clock.setGetAbbreviation(worldTimeCity.getAbbreviation());
        clock.setOffSetTime(CalendarUtils.getTimezoneOffsetInMin(TimeZone.getTimeZone(worldTimeCity.getTimeZoneName())));
        setVisPreView();
    }

    private void initFunctionLaugeMap() {
        functionLaugeMap = new HashMap<>();
        functionLaugeMap.put("healthtitle", "logn_lauch_HealthSport_ios");
        functionLaugeMap.put(BaseUrl.URL_NAME_HEALTH, "public_fitness");
        functionLaugeMap.put("heartrate", "home_card_heart_rate");
        functionLaugeMap.put("calorie", "sport_calorie");
        functionLaugeMap.put(FitnessActivities.SLEEP, "home_card_sleep");
        functionLaugeMap.put("stress", "public_pressure");
        functionLaugeMap.put("oxygen", "shortcut_app_blood_oxygen");
        functionLaugeMap.put("breathe", "home_breath");
        functionLaugeMap.put("noise", "shortcut_app_noise");
        functionLaugeMap.put("liv", "period_tracking");
        functionLaugeMap.put("temperture", "shortcut_app_body_temperature");
        functionLaugeMap.put("sport", "device_sport");
        functionLaugeMap.put("step", "shortcut_app_steps");
        functionLaugeMap.put("distance", "sport_distance");
        functionLaugeMap.put("sounds", "dial_function_voice");
        functionLaugeMap.put("alexa", "alexa_title");
        functionLaugeMap.put("communication", "dial_function_communication");
        functionLaugeMap.put("call_list", "shortcut_app_phone");
        functionLaugeMap.put("contacts", "shortcut_app_contact");
        functionLaugeMap.put("bt_call", "speed_dial");
        functionLaugeMap.put("tools", "dial_function_tools");
        functionLaugeMap.put("weather", "device_menu_weather_android");
        functionLaugeMap.put("music", "device_music");
        functionLaugeMap.put("clock", "device_alarm_clock");
        functionLaugeMap.put("chronograph", "device_menu_second_chronograph_android");
        functionLaugeMap.put("timer", "device_menu_timer_android");
        functionLaugeMap.put("schedule", "shortcut_app_reminders");
        functionLaugeMap.put("light", "light");
        functionLaugeMap.put("camera", "device_user_take_photo");
        functionLaugeMap.put("date_week", "device_reminders_add_date");
        functionLaugeMap.put("battery", "shortcut_app_battery_power");
        functionLaugeMap.put("exercise", "exercise");
        functionLaugeMap.put("UT", "shortcut_app_world_time");
        functionLaugeMap.put("date", "device_reminders_add_date");
        functionLaugeMap.put("kcal_sport_walk", "public_fitness");
        functionLaugeMap.put("hr_unit", "sport_record_heart_rate");
        functionLaugeMap.put("distance_unit", "sport_distance");
        functionLaugeMap.put("step_unit", "shortcut_app_steps");
    }
}