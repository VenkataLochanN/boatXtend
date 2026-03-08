package com.ido.life.module.home.recentsituation.targetset;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.core.os.BundleKt;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.life.constants.Constants;
import com.ido.life.customview.CustomWheelView;
import com.watch.life.wheelview.adapter.ArrayWheelAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WalkStepSetDialogFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00172\u00020\u0001:\u0002\u0017\u0018B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\b\u0010\u0010\u001a\u00020\rH\u0014J\u0012\u0010\u0011\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0012\u0010\u0014\u001a\u00020\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/ido/life/module/home/recentsituation/targetset/WalkStepSetDialogFragment;", "Lcom/ido/common/base/BaseDialogFragment;", "()V", "mListener", "Lcom/ido/life/module/home/recentsituation/targetset/WalkStepSetDialogFragment$ConfirmListener;", "getMListener", "()Lcom/ido/life/module/home/recentsituation/targetset/WalkStepSetDialogFragment$ConfirmListener;", "setMListener", "(Lcom/ido/life/module/home/recentsituation/targetset/WalkStepSetDialogFragment$ConfirmListener;)V", "mValueList", "", "", "getGoalStepItems", "", "getLayoutResId", "", "initData", "initUI", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "ConfirmListener", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WalkStepSetDialogFragment extends BaseDialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private HashMap _$_findViewCache;
    private ConfirmListener mListener;
    private final List<String> mValueList = new ArrayList();

    /* JADX INFO: compiled from: WalkStepSetDialogFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/ido/life/module/home/recentsituation/targetset/WalkStepSetDialogFragment$ConfirmListener;", "", "actionSuccess", "", "value", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface ConfirmListener {
        void actionSuccess(String value);
    }

    @JvmStatic
    public static final WalkStepSetDialogFragment getInstance(int i) {
        return INSTANCE.getInstance(i);
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
        return R.layout.dialog_walk_step_set_layout;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final ConfirmListener getMListener() {
        return this.mListener;
    }

    public final void setMListener(ConfirmListener confirmListener) {
        this.mListener = confirmListener;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, R.style.dialog_translate);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        CustomWheelView customWheelView;
        if (getDialog() != null) {
            Dialog dialog = getDialog();
            if (dialog == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog!!");
            if (dialog.getWindow() != null) {
                Dialog dialog2 = getDialog();
                if (dialog2 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(dialog2, "dialog!!");
                Window window = dialog2.getWindow();
                if (window == null) {
                    Intrinsics.throwNpe();
                }
                window.getDecorView().setPadding(getResources().getDimensionPixelSize(R.dimen.sw_dp_10), 0, getResources().getDimensionPixelSize(R.dimen.sw_dp_10), getResources().getDimensionPixelSize(R.dimen.sw_dp_10));
                WindowManager.LayoutParams attributes = window.getAttributes();
                if (attributes == null) {
                    Intrinsics.throwNpe();
                }
                attributes.gravity = 80;
                attributes.width = -1;
                window.setAttributes(attributes);
            }
        }
        CustomWheelView customWheelView2 = (CustomWheelView) _$_findCachedViewById(com.ido.life.R.id.wheel_view);
        if (customWheelView2 != null) {
            customWheelView2.setCyclic(false);
        }
        CustomWheelView customWheelView3 = (CustomWheelView) _$_findCachedViewById(com.ido.life.R.id.wheel_view);
        if (customWheelView3 != null) {
            customWheelView3.setAdapter(new ArrayWheelAdapter(this.mValueList));
        }
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            if (arguments == null) {
                Intrinsics.throwNpe();
            }
            int i = arguments.getInt(Constants.INTENT_DATA_KEY);
            int size = this.mValueList.size();
            if (i >= 0 && size > i && (customWheelView = (CustomWheelView) _$_findCachedViewById(com.ido.life.R.id.wheel_view)) != null) {
                customWheelView.setCurrentItem(i);
            }
        }
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_confirm);
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.home.recentsituation.targetset.WalkStepSetDialogFragment.initUI.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ConfirmListener mListener = WalkStepSetDialogFragment.this.getMListener();
                    if (mListener != null) {
                        List list = WalkStepSetDialogFragment.this.mValueList;
                        CustomWheelView customWheelView4 = (CustomWheelView) WalkStepSetDialogFragment.this._$_findCachedViewById(com.ido.life.R.id.wheel_view);
                        if (customWheelView4 == null) {
                            Intrinsics.throwNpe();
                        }
                        mListener.actionSuccess((String) list.get(customWheelView4.getCurrentItem()));
                    }
                    WalkStepSetDialogFragment.this.dismissAllowingStateLoss();
                }
            });
        }
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        super.initData();
        getGoalStepItems();
    }

    private final void getGoalStepItems() {
        for (int i = 0; i < 18; i++) {
            this.mValueList.add(String.valueOf((i * 25) + 75));
        }
    }

    /* JADX INFO: compiled from: WalkStepSetDialogFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/ido/life/module/home/recentsituation/targetset/WalkStepSetDialogFragment$Companion;", "", "()V", "getInstance", "Lcom/ido/life/module/home/recentsituation/targetset/WalkStepSetDialogFragment;", "selectedIndex", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final WalkStepSetDialogFragment getInstance(int selectedIndex) {
            WalkStepSetDialogFragment walkStepSetDialogFragment = new WalkStepSetDialogFragment();
            walkStepSetDialogFragment.setArguments(BundleKt.bundleOf(new Pair(Constants.INTENT_DATA_KEY, Integer.valueOf(selectedIndex))));
            return walkStepSetDialogFragment;
        }
    }
}