package com.ido.life.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.bean.ShortcutAppData;
import com.ido.life.data.cache.ShortcutAppManager;
import com.ido.life.viewholder.shortcut.ShortcutAppViewHolderManager;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShortcutAppSetDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 #2\u00020\u0001:\u0003#$%B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0006H\u0014J\b\u0010\u001b\u001a\u00020\u0006H\u0014J\b\u0010\u001c\u001a\u00020\u0019H\u0014J\u0012\u0010\u001d\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\b\u0010 \u001a\u00020\u0019H\u0002J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u0006H\u0002R\u0014\u0010\u0003\u001a\b\u0018\u00010\u0004R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/ido/life/dialog/ShortcutAppSetDialog;", "Lcom/ido/common/base/BaseDialogFragment;", "()V", "adapter", "Lcom/ido/life/dialog/ShortcutAppSetDialog$ShowTypeAdapter;", "appUnique", "", "mAppInfoPair", "Lkotlin/Pair;", "mCurrentShowType", "mItemHeight", "mPosition", "mSelectedShowType", "mSmallItemWidth", "onAddClickListener", "Lcom/ido/life/dialog/ShortcutAppSetDialog$OnAddClickListener;", "getOnAddClickListener", "()Lcom/ido/life/dialog/ShortcutAppSetDialog$OnAddClickListener;", "setOnAddClickListener", "(Lcom/ido/life/dialog/ShortcutAppSetDialog$OnAddClickListener;)V", "shortcutAppData", "Lcom/ido/life/bean/ShortcutAppData;", "showTypes", "", "changeAddBtnStatus", "", "getLayoutResId", "getWindowAnimations", "initData", "initListener", "view", "Landroid/view/View;", "initViewpager", "switchPoint", "position", "Companion", "OnAddClickListener", "ShowTypeAdapter", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ShortcutAppSetDialog extends BaseDialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DATA = "DATA";
    private static final String SHOW_TYPE = "SHOW_TYPE";
    private HashMap _$_findViewCache;
    private ShowTypeAdapter adapter;
    private int appUnique = -1;
    private Pair<Integer, Integer> mAppInfoPair;
    private int mCurrentShowType;
    private int mItemHeight;
    private int mPosition;
    private int mSelectedShowType;
    private int mSmallItemWidth;
    private OnAddClickListener onAddClickListener;
    private ShortcutAppData shortcutAppData;
    private List<Integer> showTypes;

    /* JADX INFO: compiled from: ShortcutAppSetDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/ido/life/dialog/ShortcutAppSetDialog$OnAddClickListener;", "", "onAddClick", "", "shortcutAppData", "Lcom/ido/life/bean/ShortcutAppData;", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface OnAddClickListener {
        void onAddClick(ShortcutAppData shortcutAppData);
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
        return R.layout.dialog_shortcut_app_set;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getWindowAnimations() {
        return R.style.DialogAnimSlideInBottom;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final OnAddClickListener getOnAddClickListener() {
        return this.onAddClickListener;
    }

    public final void setOnAddClickListener(OnAddClickListener onAddClickListener) {
        this.onAddClickListener = onAddClickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void switchPoint(int position) {
        RadioButton rbPoint1 = (RadioButton) _$_findCachedViewById(com.ido.life.R.id.rbPoint1);
        Intrinsics.checkExpressionValueIsNotNull(rbPoint1, "rbPoint1");
        rbPoint1.setChecked(position == 0);
        RadioButton rbPoint2 = (RadioButton) _$_findCachedViewById(com.ido.life.R.id.rbPoint2);
        Intrinsics.checkExpressionValueIsNotNull(rbPoint2, "rbPoint2");
        rbPoint2.setChecked(position == 1);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            Intrinsics.checkExpressionValueIsNotNull(arguments, "arguments ?: return");
            ShortcutAppData shortcutAppData = (ShortcutAppData) arguments.getSerializable(DATA);
            if (shortcutAppData != null) {
                this.shortcutAppData = shortcutAppData;
                this.mCurrentShowType = arguments.getInt(SHOW_TYPE, 0);
                this.mSelectedShowType = this.mCurrentShowType;
                ShortcutAppManager companion = ShortcutAppManager.INSTANCE.getInstance();
                ShortcutAppData shortcutAppData2 = this.shortcutAppData;
                if (shortcutAppData2 == null) {
                    Intrinsics.throwNpe();
                }
                this.mAppInfoPair = companion.getShortcutAppSampleInfo(shortcutAppData2.getWidgets_type());
                Pair<Integer, Integer> pair = this.mAppInfoPair;
                if (pair != null) {
                    TextView tvAppName = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvAppName);
                    Intrinsics.checkExpressionValueIsNotNull(tvAppName, "tvAppName");
                    tvAppName.setText(LanguageUtil.getLanguageText(pair.getFirst().intValue()));
                }
                ShortcutAppData shortcutAppData3 = this.shortcutAppData;
                if (shortcutAppData3 == null) {
                    Intrinsics.throwNpe();
                }
                this.showTypes = shortcutAppData3.getShowTypes();
                ShortcutAppData shortcutAppData4 = this.shortcutAppData;
                if (shortcutAppData4 == null) {
                    Intrinsics.throwNpe();
                }
                this.appUnique = shortcutAppData4.getWidgets_type();
                List<Integer> list = this.showTypes;
                if ((list != null ? list.size() : 0) > 1) {
                    RadioButton rbPoint2 = (RadioButton) _$_findCachedViewById(com.ido.life.R.id.rbPoint2);
                    Intrinsics.checkExpressionValueIsNotNull(rbPoint2, "rbPoint2");
                    rbPoint2.setVisibility(0);
                } else {
                    RadioButton rbPoint22 = (RadioButton) _$_findCachedViewById(com.ido.life.R.id.rbPoint2);
                    Intrinsics.checkExpressionValueIsNotNull(rbPoint22, "rbPoint2");
                    rbPoint22.setVisibility(8);
                }
                Log.e("ShortcutAppSetDialog", "ScreenUtil.getScreenW() = " + ScreenUtil.getScreenW() + ", DipPixelUtil.dip2px(3 * 25f) = " + DipPixelUtil.dip2px(75.0f));
                this.mSmallItemWidth = (ScreenUtil.getScreenW() - DipPixelUtil.dip2px(75.0f)) / 2;
                this.mItemHeight = this.mSmallItemWidth;
                if (this.mItemHeight <= 0) {
                    this.mSmallItemWidth = DipPixelUtil.dip2px(150.0f);
                }
                initViewpager();
                List<Integer> list2 = this.showTypes;
                if (list2 != null) {
                    if (list2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (list2.size() > 0 && this.mCurrentShowType != 0) {
                        List<Integer> list3 = this.showTypes;
                        if (list3 == null) {
                            Intrinsics.throwNpe();
                        }
                        this.mPosition = list3.indexOf(Integer.valueOf(this.mCurrentShowType));
                        if (this.mPosition >= 0) {
                            ViewPager viewpager = (ViewPager) _$_findCachedViewById(com.ido.life.R.id.viewpager);
                            Intrinsics.checkExpressionValueIsNotNull(viewpager, "viewpager");
                            viewpager.setCurrentItem(this.mPosition);
                        }
                        changeAddBtnStatus();
                        return;
                    }
                }
                this.mPosition = 0;
            }
        }
    }

    private final void initViewpager() {
        View layout = View.inflate(getContext(), R.layout.item_shortcut_app_vp, null);
        View viewFindViewById = layout.findViewById(R.id.llContainer);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "layout.findViewById<Line…Layout>(R.id.llContainer)");
        ((LinearLayout) viewFindViewById).getLayoutParams().height = this.mItemHeight;
        layout.measure(0, 0);
        ViewPager viewpager = (ViewPager) _$_findCachedViewById(com.ido.life.R.id.viewpager);
        Intrinsics.checkExpressionValueIsNotNull(viewpager, "viewpager");
        ViewGroup.LayoutParams layoutParams = viewpager.getLayoutParams();
        Intrinsics.checkExpressionValueIsNotNull(layout, "layout");
        layoutParams.height = layout.getMeasuredHeight();
        this.adapter = new ShowTypeAdapter();
        ViewPager viewpager2 = (ViewPager) _$_findCachedViewById(com.ido.life.R.id.viewpager);
        Intrinsics.checkExpressionValueIsNotNull(viewpager2, "viewpager");
        viewpager2.setAdapter(this.adapter);
        ((ViewPager) _$_findCachedViewById(com.ido.life.R.id.viewpager)).addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.ido.life.dialog.ShortcutAppSetDialog.initViewpager.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                Integer num;
                ShortcutAppSetDialog.this.mPosition = position;
                ShortcutAppSetDialog shortcutAppSetDialog = ShortcutAppSetDialog.this;
                List list = shortcutAppSetDialog.showTypes;
                shortcutAppSetDialog.mSelectedShowType = (list == null || (num = (Integer) list.get(position)) == null) ? ShortcutAppSetDialog.this.mSelectedShowType : num.intValue();
                ShortcutAppSetDialog.this.switchPoint(position);
                ShortcutAppSetDialog.this.changeAddBtnStatus();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeAddBtnStatus() {
        boolean z = this.mSelectedShowType != this.mCurrentShowType;
        TextView tvAdd = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvAdd);
        Intrinsics.checkExpressionValueIsNotNull(tvAdd, "tvAdd");
        tvAdd.setEnabled(z);
        TextView tvAdd2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvAdd);
        Intrinsics.checkExpressionValueIsNotNull(tvAdd2, "tvAdd");
        tvAdd2.setText(z ? LanguageUtil.getLanguageText(R.string.sport_device_add_yes) : "已添加");
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initListener(View view) {
        super.initListener(view);
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.ivShortcutAppClose)).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.dialog.ShortcutAppSetDialog.initListener.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ShortcutAppSetDialog.this.dismiss();
            }
        });
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tvAdd)).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.dialog.ShortcutAppSetDialog.initListener.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ShortcutAppData shortcutAppData;
                if (ShortcutAppSetDialog.this.showTypes != null && ShortcutAppSetDialog.this.mPosition >= 0 && (shortcutAppData = ShortcutAppSetDialog.this.shortcutAppData) != null) {
                    List list = ShortcutAppSetDialog.this.showTypes;
                    if (list == null) {
                        Intrinsics.throwNpe();
                    }
                    shortcutAppData.setSize_type(((Number) list.get(ShortcutAppSetDialog.this.mPosition)).intValue());
                }
                OnAddClickListener onAddClickListener = ShortcutAppSetDialog.this.getOnAddClickListener();
                if (onAddClickListener != null) {
                    onAddClickListener.onAddClick(ShortcutAppSetDialog.this.shortcutAppData);
                }
            }
        });
    }

    /* JADX INFO: compiled from: ShortcutAppSetDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/ido/life/dialog/ShortcutAppSetDialog$Companion;", "", "()V", ShortcutAppSetDialog.DATA, "", ShortcutAppSetDialog.SHOW_TYPE, "newInstance", "Lcom/ido/life/dialog/ShortcutAppSetDialog;", "mCurrentShowType", "", "shortcutAppData", "Lcom/ido/life/bean/ShortcutAppData;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ShortcutAppSetDialog newInstance(int mCurrentShowType, ShortcutAppData shortcutAppData) {
            Intrinsics.checkParameterIsNotNull(shortcutAppData, "shortcutAppData");
            Bundle bundle = new Bundle();
            bundle.putInt(ShortcutAppSetDialog.SHOW_TYPE, mCurrentShowType);
            bundle.putSerializable(ShortcutAppSetDialog.DATA, shortcutAppData);
            ShortcutAppSetDialog shortcutAppSetDialog = new ShortcutAppSetDialog();
            shortcutAppSetDialog.setArguments(bundle);
            shortcutAppSetDialog.setStyle(1, 2131886083);
            return shortcutAppSetDialog;
        }
    }

    /* JADX INFO: compiled from: ShortcutAppSetDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u0004H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000eH\u0016¨\u0006\u0015"}, d2 = {"Lcom/ido/life/dialog/ShortcutAppSetDialog$ShowTypeAdapter;", "Landroidx/viewpager/widget/PagerAdapter;", "(Lcom/ido/life/dialog/ShortcutAppSetDialog;)V", "getCount", "", "getShowTypeName", "", "showType", "initLayout", "", "layout", "Landroid/view/View;", "position", "instantiateItem", "", "container", "Landroid/view/ViewGroup;", "isViewFromObject", "", "view", "object", "app_release"}, k = 1, mv = {1, 1, 16})
    public final class ShowTypeAdapter extends PagerAdapter {
        public ShowTypeAdapter() {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            List list = ShortcutAppSetDialog.this.showTypes;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object object) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            Intrinsics.checkParameterIsNotNull(object, "object");
            return Intrinsics.areEqual(view, object);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup container, int position) {
            Intrinsics.checkParameterIsNotNull(container, "container");
            View layout = View.inflate(container.getContext(), R.layout.item_shortcut_app_vp, null);
            Intrinsics.checkExpressionValueIsNotNull(layout, "layout");
            initLayout(layout, position);
            container.addView(layout);
            return layout;
        }

        private final void initLayout(View layout, int position) {
            int shortcutAppSmallLayoutRes;
            Integer num;
            View viewFindViewById = layout.findViewById(R.id.llContainer);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "layout.findViewById(R.id.llContainer)");
            LinearLayout linearLayout = (LinearLayout) viewFindViewById;
            View viewFindViewById2 = layout.findViewById(R.id.tvShowType);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "layout.findViewById(R.id.tvShowType)");
            TextView textView = (TextView) viewFindViewById2;
            ImageView imageView = (ImageView) layout.findViewById(R.id.ivShortcutAppIcon);
            TextView textView2 = (TextView) layout.findViewById(R.id.tvShortcutAppName);
            Pair pair = ShortcutAppSetDialog.this.mAppInfoPair;
            if (pair != null) {
                if (textView2 != null) {
                    textView2.setText(LanguageUtil.getLanguageText(((Number) pair.getFirst()).intValue()));
                }
                if (imageView != null) {
                    imageView.setImageResource(((Number) pair.getSecond()).intValue());
                }
            }
            linearLayout.getLayoutParams().height = ShortcutAppSetDialog.this.mItemHeight;
            List list = ShortcutAppSetDialog.this.showTypes;
            final int iIntValue = (list == null || (num = (Integer) list.get(position)) == null) ? -1 : num.intValue();
            textView.setText(getShowTypeName(iIntValue));
            View viewFindViewById3 = layout.findViewById(R.id.vsContainer);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "layout.findViewById(R.id.vsContainer)");
            ViewStub viewStub = (ViewStub) viewFindViewById3;
            if (iIntValue == 1) {
                shortcutAppSmallLayoutRes = ShortcutAppViewHolderManager.INSTANCE.getInstance().getShortcutAppBigLayoutRes(ShortcutAppSetDialog.this.appUnique);
            } else {
                linearLayout.getLayoutParams().width = ShortcutAppSetDialog.this.mItemHeight;
                shortcutAppSmallLayoutRes = ShortcutAppViewHolderManager.INSTANCE.getInstance().getShortcutAppSmallLayoutRes(ShortcutAppSetDialog.this.appUnique);
            }
            viewStub.setLayoutResource(shortcutAppSmallLayoutRes);
            viewStub.setOnInflateListener(new ViewStub.OnInflateListener() { // from class: com.ido.life.dialog.ShortcutAppSetDialog$ShowTypeAdapter$initLayout$2
                @Override // android.view.ViewStub.OnInflateListener
                public final void onInflate(ViewStub viewStub2, View view) {
                    if (iIntValue == 2) {
                        Pair<String, String> shortcutAppContent = ShortcutAppManager.INSTANCE.getInstance().getShortcutAppContent(ShortcutAppSetDialog.this.appUnique);
                        TextView textView3 = view != null ? (TextView) view.findViewById(R.id.tvContent) : null;
                        TextView textView4 = view != null ? (TextView) view.findViewById(R.id.tvDesc) : null;
                        if (textView3 != null) {
                            textView3.setText(shortcutAppContent.getFirst());
                        }
                        if (textView4 != null) {
                            textView4.setText(shortcutAppContent.getSecond());
                        }
                    }
                }
            });
            Log.e("Davy", " view = " + viewStub.inflate());
        }

        private final String getShowTypeName(int showType) {
            String languageText;
            String str;
            if (showType == 1) {
                languageText = LanguageUtil.getLanguageText(R.string.shortcut_big_size);
                str = "LanguageUtil.getLanguage…string.shortcut_big_size)";
            } else {
                languageText = LanguageUtil.getLanguageText(R.string.shortcut_small_size);
                str = "LanguageUtil.getLanguage…_small_size\n            )";
            }
            Intrinsics.checkExpressionValueIsNotNull(languageText, str);
            return languageText;
        }
    }
}