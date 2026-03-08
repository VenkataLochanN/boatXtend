package com.ido.life.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.dialog.CommonDialog;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CommonDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0016\u0018\u0000 .2\u00020\u00012\u00020\u0002:\u0003./0B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\tH\u0014J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fJ\u0006\u0010\u000e\u001a\u00020\fJ\b\u0010\u000f\u001a\u00020\fH\u0014J\u0012\u0010\u0010\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0012\u0010\u0013\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0006\u0010\u0014\u001a\u00020\fJ\u0012\u0010\u0015\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0012H\u0016J\u001a\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u000e\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\u001fJ\u0010\u0010\"\u001a\u00020\f2\b\u0010#\u001a\u0004\u0018\u00010\u0012J\u0010\u0010$\u001a\u00020\f2\b\u0010%\u001a\u0004\u0018\u00010\u0007J\u000e\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\u001fJ\u000e\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\tJ\u0006\u0010*\u001a\u00020\fJ\u000e\u0010+\u001a\u00020\f2\u0006\u0010,\u001a\u00020\u0005J\u000e\u0010-\u001a\u00020\f2\u0006\u0010,\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/ido/life/dialog/CommonDialog;", "Lcom/ido/common/base/BaseDialogFragment;", "Landroid/view/View$OnClickListener;", "()V", "mIsBottom", "", "mOnDialogEventListener", "Lcom/ido/life/dialog/CommonDialog$OnDialogEventListener;", "getLayoutResId", "", "getWindowAnimations", "hideButton", "", "hideCancel", "hideConfirm", "initData", "initListener", "view", "Landroid/view/View;", "initUI", "noTips", "onClick", "v", "onViewCreated", "savedInstanceState", "Landroid/os/Bundle;", "setBackground", "bg", "Landroid/graphics/drawable/Drawable;", "setCancelText", "cancelTxt", "", "setConfirmText", "confirmTxt", "setContentView", "layout", "setOnConfirmClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setTips", "tips", "setTipsGravity", CommonDialog.EXTRA_GRAVITY, "showButton", "showTips", "show", "showTitle", "Companion", "OnDialogEventListener", "SampleDialogEventListener", "app_release"}, k = 1, mv = {1, 1, 16})
public class CommonDialog extends BaseDialogFragment implements View.OnClickListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String EXTRA_CANCEL = "cancel";
    public static final String EXTRA_CONFIRM = "confirm";
    public static final String EXTRA_GRAVITY = "gravity";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_WIDTH_RATIO = "width_ratio";
    private HashMap _$_findViewCache;
    private boolean mIsBottom;
    private OnDialogEventListener mOnDialogEventListener;

    /* JADX INFO: compiled from: CommonDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\b\u0010\u0007\u001a\u00020\u0003H&¨\u0006\b"}, d2 = {"Lcom/ido/life/dialog/CommonDialog$OnDialogEventListener;", "", "onCancelClick", "", "dialog", "Lcom/ido/life/dialog/CommonDialog;", "onConfirmClick", "onDismiss", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface OnDialogEventListener {
        void onCancelClick(CommonDialog dialog);

        void onConfirmClick(CommonDialog dialog);

        void onDismiss();
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
        return R.layout.dialog_common_bottom;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* JADX INFO: compiled from: CommonDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/ido/life/dialog/CommonDialog$Companion;", "", "()V", "EXTRA_CANCEL", "", "EXTRA_CONFIRM", "EXTRA_GRAVITY", "EXTRA_TITLE", "EXTRA_WIDTH_RATIO", "newInstance", "Lcom/ido/life/dialog/CommonDialog;", CommonDialog.EXTRA_TITLE, CommonDialog.EXTRA_CANCEL, CommonDialog.EXTRA_CONFIRM, CommonDialog.EXTRA_GRAVITY, "", "width", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ CommonDialog newInstance$default(Companion companion, String str, String str2, String str3, int i, float f2, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                str2 = LanguageUtil.getLanguageText(R.string.public_cancel);
                Intrinsics.checkExpressionValueIsNotNull(str2, "LanguageUtil.getLanguage…t(R.string.public_cancel)");
            }
            String str4 = str2;
            if ((i2 & 4) != 0) {
                str3 = LanguageUtil.getLanguageText(R.string.public_confirm);
                Intrinsics.checkExpressionValueIsNotNull(str3, "LanguageUtil.getLanguage…(R.string.public_confirm)");
            }
            String str5 = str3;
            if ((i2 & 8) != 0) {
                i = 81;
            }
            int i3 = i;
            if ((i2 & 16) != 0) {
                f2 = -1.0f;
            }
            return companion.newInstance(str, str4, str5, i3, f2);
        }

        public final CommonDialog newInstance(String title, String cancel, String confirm, int gravity, float width) {
            Intrinsics.checkParameterIsNotNull(title, "title");
            Intrinsics.checkParameterIsNotNull(cancel, "cancel");
            Intrinsics.checkParameterIsNotNull(confirm, "confirm");
            Bundle bundle = new Bundle();
            bundle.putString(CommonDialog.EXTRA_TITLE, title);
            bundle.putString(CommonDialog.EXTRA_CANCEL, cancel);
            bundle.putString(CommonDialog.EXTRA_CONFIRM, confirm);
            bundle.putInt(CommonDialog.EXTRA_GRAVITY, gravity);
            bundle.putFloat(CommonDialog.EXTRA_WIDTH_RATIO, width);
            CommonDialog commonDialog = new CommonDialog();
            commonDialog.setStyle(1, 2131886083);
            commonDialog.setArguments(bundle);
            return commonDialog;
        }
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getWindowAnimations() {
        return this.mIsBottom ? R.style.DialogAnimSlideInBottom : R.style.DialogAnimationsFade;
    }

    @Override // com.ido.common.base.BaseDialogFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Bundle arguments = getArguments();
        this.mIsBottom = (arguments != null ? arguments.getInt(EXTRA_GRAVITY) : 17) == 81;
        super.onViewCreated(view, savedInstanceState);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        super.initUI(view);
        Dialog it = getDialog();
        if (it != null) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            Window window = it.getWindow();
            if (window != null) {
                window.getDecorView().setPadding(DipPixelUtil.dip2px(8.0f), 0, DipPixelUtil.dip2px(8.0f), DipPixelUtil.dip2px(15.0f));
                WindowManager.LayoutParams attributes = window.getAttributes();
                Bundle arguments = getArguments();
                attributes.gravity = arguments != null ? arguments.getInt(EXTRA_GRAVITY) : 17;
                Bundle arguments2 = getArguments();
                float f2 = arguments2 != null ? arguments2.getFloat(EXTRA_WIDTH_RATIO) : -1.0f;
                attributes.width = f2 <= 0.0f ? -1 : (int) (f2 * ScreenUtil.getScreenW());
                window.setAttributes(attributes);
            }
            it.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.ido.life.dialog.CommonDialog$initUI$$inlined$let$lambda$1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    CommonDialog.OnDialogEventListener onDialogEventListener = this.this$0.mOnDialogEventListener;
                    if (onDialogEventListener != null) {
                        onDialogEventListener.onDismiss();
                    }
                }
            });
        }
        if (this.mIsBottom) {
            return;
        }
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.llLayout)).setPadding(0, ResourceUtil.getDimens(R.dimen.sw_dp_24), 0, ResourceUtil.getDimens(R.dimen.sw_dp_24));
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        String string;
        super.initData();
        TextView tv_title = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title);
        Intrinsics.checkExpressionValueIsNotNull(tv_title, "tv_title");
        Bundle arguments = getArguments();
        if (arguments == null || (string = arguments.getString(EXTRA_TITLE)) == null) {
            string = "";
        }
        tv_title.setText(string);
        TextView tv_cancel = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_cancel);
        Intrinsics.checkExpressionValueIsNotNull(tv_cancel, "tv_cancel");
        Bundle arguments2 = getArguments();
        tv_cancel.setText(arguments2 != null ? arguments2.getString(EXTRA_CANCEL) : null);
        TextView tv_confirm = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_confirm);
        Intrinsics.checkExpressionValueIsNotNull(tv_confirm, "tv_confirm");
        Bundle arguments3 = getArguments();
        tv_confirm.setText(arguments3 != null ? arguments3.getString(EXTRA_CONFIRM) : null);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initListener(View view) {
        CommonDialog commonDialog = this;
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_confirm)).setOnClickListener(commonDialog);
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_cancel)).setOnClickListener(commonDialog);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        OnDialogEventListener onDialogEventListener;
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_cancel) {
            OnDialogEventListener onDialogEventListener2 = this.mOnDialogEventListener;
            if (onDialogEventListener2 != null) {
                onDialogEventListener2.onCancelClick(this);
            }
            dismissAllowingStateLoss();
            return;
        }
        if (numValueOf == null || numValueOf.intValue() != R.id.tv_confirm || (onDialogEventListener = this.mOnDialogEventListener) == null) {
            return;
        }
        onDialogEventListener.onConfirmClick(this);
    }

    public final void setOnConfirmClickListener(OnDialogEventListener listener) {
        this.mOnDialogEventListener = listener;
    }

    public final void setContentView(View layout) {
        if (((FrameLayout) _$_findCachedViewById(com.ido.life.R.id.flContent)) != null) {
            if (layout != null) {
                FrameLayout flContent = (FrameLayout) _$_findCachedViewById(com.ido.life.R.id.flContent);
                Intrinsics.checkExpressionValueIsNotNull(flContent, "flContent");
                flContent.setVisibility(0);
                ((FrameLayout) _$_findCachedViewById(com.ido.life.R.id.flContent)).removeAllViews();
                ((FrameLayout) _$_findCachedViewById(com.ido.life.R.id.flContent)).addView(layout);
                return;
            }
            FrameLayout flContent2 = (FrameLayout) _$_findCachedViewById(com.ido.life.R.id.flContent);
            Intrinsics.checkExpressionValueIsNotNull(flContent2, "flContent");
            flContent2.setVisibility(8);
        }
    }

    public final void setBackground(Drawable bg) {
        Intrinsics.checkParameterIsNotNull(bg, "bg");
        LinearLayout llLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.llLayout);
        Intrinsics.checkExpressionValueIsNotNull(llLayout, "llLayout");
        llLayout.setBackground(bg);
    }

    public final void setTips(String tips) {
        Intrinsics.checkParameterIsNotNull(tips, "tips");
        TextView tvTips = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvTips);
        Intrinsics.checkExpressionValueIsNotNull(tvTips, "tvTips");
        tvTips.setText(tips);
    }

    public final void noTips() {
        TextView tvTips = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvTips);
        Intrinsics.checkExpressionValueIsNotNull(tvTips, "tvTips");
        tvTips.setVisibility(8);
    }

    public final void showTips(boolean show) {
        TextView tvTips = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvTips);
        Intrinsics.checkExpressionValueIsNotNull(tvTips, "tvTips");
        tvTips.setVisibility(show ? 0 : 8);
    }

    public final void showTitle(boolean show) {
        TextView tv_title = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title);
        Intrinsics.checkExpressionValueIsNotNull(tv_title, "tv_title");
        tv_title.setVisibility(show ? 0 : 8);
    }

    public final void hideCancel() {
        TextView tv_cancel = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_cancel);
        Intrinsics.checkExpressionValueIsNotNull(tv_cancel, "tv_cancel");
        tv_cancel.setVisibility(8);
        View divider = _$_findCachedViewById(com.ido.life.R.id.divider);
        Intrinsics.checkExpressionValueIsNotNull(divider, "divider");
        divider.setVisibility(8);
    }

    public final void hideConfirm() {
        TextView tv_confirm = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_confirm);
        Intrinsics.checkExpressionValueIsNotNull(tv_confirm, "tv_confirm");
        tv_confirm.setVisibility(8);
        View divider = _$_findCachedViewById(com.ido.life.R.id.divider);
        Intrinsics.checkExpressionValueIsNotNull(divider, "divider");
        divider.setVisibility(8);
    }

    public final void hideButton() {
        LinearLayout ll_button = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_button);
        Intrinsics.checkExpressionValueIsNotNull(ll_button, "ll_button");
        ll_button.setVisibility(8);
    }

    public final void showButton() {
        LinearLayout ll_button = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_button);
        Intrinsics.checkExpressionValueIsNotNull(ll_button, "ll_button");
        ll_button.setVisibility(0);
    }

    public final void setTipsGravity(int gravity) {
        TextView tvTips = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvTips);
        Intrinsics.checkExpressionValueIsNotNull(tvTips, "tvTips");
        tvTips.setGravity(gravity);
    }

    public final void setConfirmText(String confirmTxt) {
        Intrinsics.checkParameterIsNotNull(confirmTxt, "confirmTxt");
        TextView tv_confirm = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_confirm);
        Intrinsics.checkExpressionValueIsNotNull(tv_confirm, "tv_confirm");
        tv_confirm.setText(confirmTxt);
    }

    public final void setCancelText(String cancelTxt) {
        Intrinsics.checkParameterIsNotNull(cancelTxt, "cancelTxt");
        TextView tv_cancel = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_cancel);
        Intrinsics.checkExpressionValueIsNotNull(tv_cancel, "tv_cancel");
        tv_cancel.setText(cancelTxt);
    }

    /* JADX INFO: compiled from: CommonDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016¨\u0006\t"}, d2 = {"Lcom/ido/life/dialog/CommonDialog$SampleDialogEventListener;", "Lcom/ido/life/dialog/CommonDialog$OnDialogEventListener;", "()V", "onCancelClick", "", "dialog", "Lcom/ido/life/dialog/CommonDialog;", "onConfirmClick", "onDismiss", "app_release"}, k = 1, mv = {1, 1, 16})
    public static class SampleDialogEventListener implements OnDialogEventListener {
        @Override // com.ido.life.dialog.CommonDialog.OnDialogEventListener
        public void onDismiss() {
        }

        @Override // com.ido.life.dialog.CommonDialog.OnDialogEventListener
        public void onConfirmClick(CommonDialog dialog) {
            if (dialog != null) {
                dialog.dismissAllowingStateLoss();
            }
        }

        @Override // com.ido.life.dialog.CommonDialog.OnDialogEventListener
        public void onCancelClick(CommonDialog dialog) {
            if (dialog != null) {
                dialog.dismissAllowingStateLoss();
            }
        }
    }
}