package com.ido.life.dialog;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.google.android.gms.stats.CodePackage;
import com.ido.common.IdoApp;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.constants.WallpaperDialConstants;
import com.ido.life.customview.CircleImageView;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.util.WallpaperDialManager;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WallPaperDialFunctionSetDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u0000 42\u00020\u0001:\u000245B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u00020\u0004H\u0014J\b\u0010\u001c\u001a\u00020\u001aH\u0014J\u0012\u0010\u001d\u001a\u00020\u001a2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\u0012\u0010 \u001a\u00020\u001a2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\b\u0010!\u001a\u00020\u001aH\u0002J\u000e\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u0004J\u0016\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u0004J\u000e\u0010*\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$J\u000e\u0010+\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u0004J\u0016\u0010,\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u0004J\u0010\u0010-\u001a\u00020\u001a2\b\b\u0001\u0010.\u001a\u00020\u0004J\u000e\u0010/\u001a\u00020\u001a2\u0006\u00100\u001a\u00020\u0004J\u000e\u00101\u001a\u00020\u001a2\u0006\u00100\u001a\u00020\u0004J\b\u00102\u001a\u00020\u001aH\u0002J\b\u00103\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u00066"}, d2 = {"Lcom/ido/life/dialog/WallPaperDialFunctionSetDialog;", "Lcom/ido/common/base/BaseDialogFragment;", "()V", "mDial", "", "mDialImageAspectRatio", "", "mFunctionColor", "mFunctionList", "Ljava/util/ArrayList;", "mFunctionStatus", "", "mIsBracelet", "mIsCircle", "mLocation", "mSelectedFunction", "mTimeColor", "mWidgetRules", "", "onClickListener", "Lcom/ido/life/dialog/WallPaperDialFunctionSetDialog$OnClickListener;", "getOnClickListener", "()Lcom/ido/life/dialog/WallPaperDialFunctionSetDialog$OnClickListener;", "setOnClickListener", "(Lcom/ido/life/dialog/WallPaperDialFunctionSetDialog$OnClickListener;)V", "clearFunctionColor", "", "getLayoutResId", "initData", "initListener", "view", "Landroid/view/View;", "initUI", "initView", "setBgBitmap", "bitmap", "Landroid/graphics/Bitmap;", "setBgResId", "resId", "setDialBgSize", "width", "height", "setDialBitmap", "setDialResId", "setDialSize", "setDialWidgetLocation", "location", "setFunctionColor", "color", "setTimeColor", "updateFunctionSwitchText", "updateFunctionWidget", "Companion", "OnClickListener", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WallPaperDialFunctionSetDialog extends BaseDialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DIAL_IMAGE_ASPECT_RATIO = "dialImageAspectRatio";
    private static final String FUNCTION_LIST = "function_list";
    private static final String FUNCTION_STATUS = "function_status";
    private static final String ICON_RESOURCE_ID_PARAMS = "icon_resource_id_params";
    private static final String IS_BRACELET = "is_bracelet";
    private static final String IS_CIRCLE = "is_circle";
    private static final String LOCATION = "location";
    private static final String SELECT_FUNCTION = "select_function";
    private HashMap _$_findViewCache;
    private int mDial;
    private float mDialImageAspectRatio;
    private boolean mFunctionStatus;
    private boolean mIsBracelet;
    private boolean mIsCircle;
    private OnClickListener onClickListener;
    private ArrayList<Integer> mFunctionList = new ArrayList<>();
    private int mSelectedFunction = -1;
    private int mLocation = 3;
    private int mFunctionColor = -1;
    private int mTimeColor = -1;
    private int[] mWidgetRules = {19};

    /* JADX INFO: compiled from: WallPaperDialFunctionSetDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/ido/life/dialog/WallPaperDialFunctionSetDialog$OnClickListener;", "", "onCompleteClick", "", "function", "", "functionStatus", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface OnClickListener {
        void onCompleteClick(int function, boolean functionStatus);
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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View viewFindViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_dial_function_select;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final OnClickListener getOnClickListener() {
        return this.onClickListener;
    }

    public final void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mDial = arguments.getInt(ICON_RESOURCE_ID_PARAMS);
            this.mFunctionStatus = arguments.getBoolean(FUNCTION_STATUS);
            ArrayList<Integer> integerArrayList = arguments.getIntegerArrayList(FUNCTION_LIST);
            if (integerArrayList == null) {
                integerArrayList = new ArrayList<>();
            }
            this.mFunctionList = integerArrayList;
            this.mSelectedFunction = arguments.getInt(SELECT_FUNCTION);
            this.mLocation = arguments.getInt("location");
            this.mIsCircle = arguments.getBoolean(IS_CIRCLE);
            this.mIsBracelet = arguments.getBoolean(IS_BRACELET);
            this.mDialImageAspectRatio = arguments.getFloat(DIAL_IMAGE_ASPECT_RATIO);
        }
        initView();
    }

    private final void initView() {
        final WallPaperDialFunctionSetDialog$initView$adapter$1 wallPaperDialFunctionSetDialog$initView$adapter$1 = new WallPaperDialFunctionSetDialog$initView$adapter$1(this, IdoApp.getAppContext(), R.layout.item_dial_function_list, this.mFunctionList);
        RecyclerView recyclerview = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview, "recyclerview");
        recyclerview.setAdapter(wallPaperDialFunctionSetDialog$initView$adapter$1);
        wallPaperDialFunctionSetDialog$initView$adapter$1.setOnItemClickListener(new MultiItemTypeAdapterForRV.OnItemClickListener() { // from class: com.ido.life.dialog.WallPaperDialFunctionSetDialog.initView.1
            @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intrinsics.checkParameterIsNotNull(holder, "holder");
                return false;
            }

            @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intrinsics.checkParameterIsNotNull(holder, "holder");
                Object obj = WallPaperDialFunctionSetDialog.this.mFunctionList.get(position);
                Intrinsics.checkExpressionValueIsNotNull(obj, "mFunctionList[position]");
                int iIntValue = ((Number) obj).intValue();
                if (WallPaperDialFunctionSetDialog.this.mSelectedFunction != iIntValue) {
                    WallPaperDialFunctionSetDialog.this.mSelectedFunction = iIntValue;
                    wallPaperDialFunctionSetDialog$initView$adapter$1.notifyDataSetChanged();
                    WallPaperDialFunctionSetDialog.this.updateFunctionWidget();
                }
            }
        });
        ((FrameLayout) _$_findCachedViewById(com.ido.life.R.id.layout_pic)).setBackgroundResource(this.mIsCircle ? R.drawable.dial_frame_circle_bg : this.mIsBracelet ? R.drawable.wallpaper_dial_frame_bracelet_bg : R.drawable.wallpaper_dial_frame_watch_bg);
        if (this.mIsCircle) {
            AppCompatImageView iv_wallpaper_dial_bg = (AppCompatImageView) _$_findCachedViewById(com.ido.life.R.id.iv_wallpaper_dial_bg);
            Intrinsics.checkExpressionValueIsNotNull(iv_wallpaper_dial_bg, "iv_wallpaper_dial_bg");
            iv_wallpaper_dial_bg.setVisibility(4);
            AppCompatImageView iv_dial_time = (AppCompatImageView) _$_findCachedViewById(com.ido.life.R.id.iv_dial_time);
            Intrinsics.checkExpressionValueIsNotNull(iv_dial_time, "iv_dial_time");
            iv_dial_time.setVisibility(4);
            ((AppCompatImageView) _$_findCachedViewById(com.ido.life.R.id.iv_wallpaper_dial)).setImageDrawable(null);
            CircleImageView iv_dial_circle = (CircleImageView) _$_findCachedViewById(com.ido.life.R.id.iv_dial_circle);
            Intrinsics.checkExpressionValueIsNotNull(iv_dial_circle, "iv_dial_circle");
            iv_dial_circle.setVisibility(0);
        } else {
            CircleImageView iv_dial_circle2 = (CircleImageView) _$_findCachedViewById(com.ido.life.R.id.iv_dial_circle);
            Intrinsics.checkExpressionValueIsNotNull(iv_dial_circle2, "iv_dial_circle");
            iv_dial_circle2.setVisibility(4);
        }
        setDialWidgetLocation(this.mLocation);
        updateFunctionSwitchText();
        updateFunctionWidget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateFunctionWidget() {
        ((AppCompatImageView) _$_findCachedViewById(com.ido.life.R.id.iv_dial_function)).setImageResource(WallpaperDialManager.getFunctionIcon(this.mSelectedFunction));
        ((AppCompatImageView) _$_findCachedViewById(com.ido.life.R.id.iv_dial_time)).setImageResource(R.mipmap.icon_wallpaper_dial_time);
        setFunctionColor(this.mFunctionColor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateFunctionSwitchText() {
        TextView tvFunctionSwitch = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvFunctionSwitch);
        Intrinsics.checkExpressionValueIsNotNull(tvFunctionSwitch, "tvFunctionSwitch");
        tvFunctionSwitch.setText(LanguageUtil.getLanguageText(this.mFunctionStatus ? R.string.dial_function_hide_tip : R.string.dial_function_show_tip));
        LinearLayout ll_dial_widget = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_dial_widget);
        Intrinsics.checkExpressionValueIsNotNull(ll_dial_widget, "ll_dial_widget");
        ll_dial_widget.setVisibility(this.mFunctionStatus ? 0 : 4);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        Window it;
        Dialog dialog = getDialog();
        if (dialog == null || (it = dialog.getWindow()) == null) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(it, "it");
        it.getDecorView().setPadding(DipPixelUtil.dip2px(10.0f), 0, DipPixelUtil.dip2px(10.0f), DipPixelUtil.dip2px(15.0f));
        WindowManager.LayoutParams attributes = it.getAttributes();
        attributes.gravity = 81;
        attributes.width = -1;
        it.setAttributes(attributes);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initListener(View view) {
        super.initListener(view);
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tvFunctionSwitch)).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.dialog.WallPaperDialFunctionSetDialog.initListener.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                WallPaperDialFunctionSetDialog.this.mFunctionStatus = !r2.mFunctionStatus;
                WallPaperDialFunctionSetDialog.this.updateFunctionSwitchText();
            }
        });
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.dialog.WallPaperDialFunctionSetDialog.initListener.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                WallPaperDialFunctionSetDialog.this.dismissAllowingStateLoss();
            }
        });
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_confirm)).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.dialog.WallPaperDialFunctionSetDialog.initListener.3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                WallPaperDialFunctionSetDialog.this.dismissAllowingStateLoss();
                OnClickListener onClickListener = WallPaperDialFunctionSetDialog.this.getOnClickListener();
                if (onClickListener != null) {
                    onClickListener.onCompleteClick(WallPaperDialFunctionSetDialog.this.mSelectedFunction, WallPaperDialFunctionSetDialog.this.mFunctionStatus);
                }
            }
        });
    }

    public final void setDialWidgetLocation(@WallpaperDialConstants.WidgetLocation int location) {
        CommonLogUtil.printAndSave("changeDialWidgetLocation location = " + location);
        int[] layoutRulesByLocation = WallpaperDialManager.getLayoutRulesByLocation(location);
        try {
            LinearLayout ll_dial_widget = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_dial_widget);
            Intrinsics.checkExpressionValueIsNotNull(ll_dial_widget, "ll_dial_widget");
            ViewGroup.LayoutParams layoutParams = ll_dial_widget.getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
            }
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            if (!(this.mWidgetRules.length == 0)) {
                for (int i : this.mWidgetRules) {
                    layoutParams2.removeRule(i);
                }
            }
            for (int i2 : layoutRulesByLocation) {
                layoutParams2.addRule(i2, R.id.iv_wallpaper_dial_bg);
            }
            this.mWidgetRules = layoutRulesByLocation;
            LinearLayout ll_dial_widget2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_dial_widget);
            Intrinsics.checkExpressionValueIsNotNull(ll_dial_widget2, "ll_dial_widget");
            ll_dial_widget2.setGravity((location == 1 || location == 7) ? 3 : 5);
        } catch (Exception e2) {
            e2.printStackTrace();
            CommonLogUtil.printAndSave("设置表盘控件位置出错！");
        }
    }

    public final void setDialSize(int width, int height) {
        AppCompatImageView iv_wallpaper_dial = (AppCompatImageView) _$_findCachedViewById(com.ido.life.R.id.iv_wallpaper_dial);
        Intrinsics.checkExpressionValueIsNotNull(iv_wallpaper_dial, "iv_wallpaper_dial");
        ViewGroup.LayoutParams layoutParams = iv_wallpaper_dial.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        AppCompatImageView iv_wallpaper_dial2 = (AppCompatImageView) _$_findCachedViewById(com.ido.life.R.id.iv_wallpaper_dial);
        Intrinsics.checkExpressionValueIsNotNull(iv_wallpaper_dial2, "iv_wallpaper_dial");
        iv_wallpaper_dial2.setLayoutParams(layoutParams);
    }

    public final void setDialBgSize(int width, int height) {
        AppCompatImageView iv_wallpaper_dial_bg = (AppCompatImageView) _$_findCachedViewById(com.ido.life.R.id.iv_wallpaper_dial_bg);
        Intrinsics.checkExpressionValueIsNotNull(iv_wallpaper_dial_bg, "iv_wallpaper_dial_bg");
        ViewGroup.LayoutParams layoutParams = iv_wallpaper_dial_bg.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        AppCompatImageView iv_wallpaper_dial_bg2 = (AppCompatImageView) _$_findCachedViewById(com.ido.life.R.id.iv_wallpaper_dial_bg);
        Intrinsics.checkExpressionValueIsNotNull(iv_wallpaper_dial_bg2, "iv_wallpaper_dial_bg");
        iv_wallpaper_dial_bg2.setLayoutParams(layoutParams);
    }

    public final void setBgBitmap(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        ((AppCompatImageView) _$_findCachedViewById(com.ido.life.R.id.iv_wallpaper_dial_bg)).setImageBitmap(bitmap);
    }

    public final void setDialBitmap(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        if (this.mIsCircle) {
            ((CircleImageView) _$_findCachedViewById(com.ido.life.R.id.iv_dial_circle)).setImageBitmap(bitmap);
        } else {
            ((AppCompatImageView) _$_findCachedViewById(com.ido.life.R.id.iv_wallpaper_dial)).setImageBitmap(bitmap);
        }
    }

    public final void setBgResId(int resId) {
        ((AppCompatImageView) _$_findCachedViewById(com.ido.life.R.id.iv_wallpaper_dial_bg)).setImageResource(resId);
    }

    public final void setDialResId(int resId) {
        if (this.mIsCircle) {
            ((CircleImageView) _$_findCachedViewById(com.ido.life.R.id.iv_dial_circle)).setImageResource(resId);
        } else {
            ((AppCompatImageView) _$_findCachedViewById(com.ido.life.R.id.iv_wallpaper_dial)).setImageResource(resId);
        }
    }

    public final void setTimeColor(int color) {
        this.mTimeColor = color;
        ((AppCompatImageView) _$_findCachedViewById(com.ido.life.R.id.iv_dial_time)).setColorFilter(color);
    }

    public final void setFunctionColor(int color) {
        this.mFunctionColor = color;
        ((AppCompatImageView) _$_findCachedViewById(com.ido.life.R.id.iv_dial_function)).setColorFilter(color);
        clearFunctionColor();
    }

    public final void clearFunctionColor() {
        if (WallpaperDialManager.ifChangeBatteryColor() || this.mSelectedFunction != 6) {
            return;
        }
        ((AppCompatImageView) _$_findCachedViewById(com.ido.life.R.id.iv_dial_function)).clearColorFilter();
    }

    /* JADX INFO: compiled from: WallPaperDialFunctionSetDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JR\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00132\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000f2\b\b\u0002\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/ido/life/dialog/WallPaperDialFunctionSetDialog$Companion;", "", "()V", "DIAL_IMAGE_ASPECT_RATIO", "", "FUNCTION_LIST", "FUNCTION_STATUS", "ICON_RESOURCE_ID_PARAMS", "IS_BRACELET", "IS_CIRCLE", CodePackage.LOCATION, "SELECT_FUNCTION", "newInstance", "Lcom/ido/life/dialog/WallPaperDialFunctionSetDialog;", "dial", "", "functionStatus", "", "functionList", "Ljava/util/ArrayList;", "selectedFunction", "location", "mIsCircle", "mIsBracelet", WallPaperDialFunctionSetDialog.DIAL_IMAGE_ASPECT_RATIO, "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final WallPaperDialFunctionSetDialog newInstance(int dial, boolean functionStatus, ArrayList<Integer> functionList, int selectedFunction, int location, boolean mIsCircle, boolean mIsBracelet, float dialImageAspectRatio) {
            Intrinsics.checkParameterIsNotNull(functionList, "functionList");
            Bundle bundle = new Bundle();
            bundle.putInt(WallPaperDialFunctionSetDialog.ICON_RESOURCE_ID_PARAMS, dial);
            bundle.putBoolean(WallPaperDialFunctionSetDialog.FUNCTION_STATUS, functionStatus);
            bundle.putIntegerArrayList(WallPaperDialFunctionSetDialog.FUNCTION_LIST, functionList);
            bundle.putInt(WallPaperDialFunctionSetDialog.SELECT_FUNCTION, selectedFunction);
            bundle.putInt("location", location);
            bundle.putBoolean(WallPaperDialFunctionSetDialog.IS_CIRCLE, mIsCircle);
            bundle.putBoolean(WallPaperDialFunctionSetDialog.IS_BRACELET, mIsBracelet);
            bundle.putFloat(WallPaperDialFunctionSetDialog.DIAL_IMAGE_ASPECT_RATIO, dialImageAspectRatio);
            WallPaperDialFunctionSetDialog wallPaperDialFunctionSetDialog = new WallPaperDialFunctionSetDialog();
            wallPaperDialFunctionSetDialog.setArguments(bundle);
            wallPaperDialFunctionSetDialog.setCancelable(false);
            wallPaperDialFunctionSetDialog.setStyle(1, 2131886083);
            return wallPaperDialFunctionSetDialog;
        }
    }
}