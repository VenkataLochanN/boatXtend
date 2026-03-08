package com.ido.life.dialog;

import android.content.Context;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.ido.common.IdoApp;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.dialog.CommonDialog;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: DialogUtils.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001aB\u0007\b\u0002¢\u0006\u0002\u0010\u0002JJ\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010JB\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0006\u0010\u0017\u001a\u00020\u000e2\u0014\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0019¨\u0006\u001b"}, d2 = {"Lcom/ido/life/dialog/DialogUtils;", "", "()V", "showCustomDialog", "Lcom/ido/life/dialog/CommonDialog;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", CommonDialog.EXTRA_TITLE, "", "view", "Landroid/view/View;", "cancelTxt", "confirmTxt", CommonDialog.EXTRA_GRAVITY, "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/ido/life/dialog/CommonDialog$OnDialogEventListener;", "showHeartRateThresholdSelectDialog", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "values", "", "selPosition", "callback", "Lkotlin/Function1;", "SampleDialogEventListener", "app_release"}, k = 1, mv = {1, 1, 16})
public final class DialogUtils {
    public static final DialogUtils INSTANCE = new DialogUtils();

    private DialogUtils() {
    }

    /* JADX WARN: Type inference failed for: r12v0, types: [com.ido.life.dialog.DialogUtils$showHeartRateThresholdSelectDialog$adapter$1] */
    public final void showHeartRateThresholdSelectDialog(FragmentActivity activity, String title, final List<String> values, int selPosition, final Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(values, "values");
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = selPosition;
        View viewInflate = LayoutInflater.from(activity).inflate(R.layout.dialog_heart_rate_remind, (ViewGroup) null);
        View viewFindViewById = viewInflate.findViewById(R.id.recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "view.findViewById(R.id.recyclerview)");
        final Context appContext = IdoApp.getAppContext();
        final int i = R.layout.item_heart_rate_remind_set;
        final ?? r12 = new CommonRecyclerViewAdapter<String>(appContext, i, values) { // from class: com.ido.life.dialog.DialogUtils$showHeartRateThresholdSelectDialog$adapter$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder holder, String t, int position) {
                String str;
                TextView textView;
                TextPaint paint;
                if (position > 0) {
                    String languageText = LanguageUtil.getLanguageText(R.string.heart_rate_unit);
                    Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…R.string.heart_rate_unit)");
                    Object[] objArr = {values.get(position)};
                    str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                } else {
                    str = (String) values.get(position);
                }
                if (holder != null) {
                    holder.setText(R.id.tvTitle, str);
                }
                if (holder != null) {
                    holder.setChecked(R.id.cbRemind, position == intRef.element);
                }
                if (holder != null) {
                    int i2 = intRef.element;
                    holder.setTextColorRes(R.id.tvTitle, R.color.white);
                }
                if (holder == null || (textView = (TextView) holder.getView(R.id.tvTitle)) == null || (paint = textView.getPaint()) == null) {
                    return;
                }
                paint.setFakeBoldText(position == intRef.element);
            }
        };
        ((RecyclerView) viewFindViewById).setAdapter((RecyclerView.Adapter) r12);
        r12.setOnItemClickListener(new MultiItemTypeAdapterForRV.OnItemClickListener() { // from class: com.ido.life.dialog.DialogUtils.showHeartRateThresholdSelectDialog.1
            @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }

            @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                intRef.element = position;
                notifyDataSetChanged();
            }
        });
        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "activity.supportFragmentManager");
        showCustomDialog$default(this, supportFragmentManager, title, viewInflate, null, null, 81, new SampleDialogEventListener() { // from class: com.ido.life.dialog.DialogUtils.showHeartRateThresholdSelectDialog.2
            @Override // com.ido.life.dialog.DialogUtils.SampleDialogEventListener, com.ido.life.dialog.CommonDialog.OnDialogEventListener
            public void onConfirmClick(CommonDialog dialog) {
                super.onConfirmClick(dialog);
                Function1 function1 = callback;
                if (function1 != null) {
                    function1.invoke(Integer.valueOf(intRef.element));
                }
            }
        }, 24, null);
    }

    public static /* synthetic */ CommonDialog showCustomDialog$default(DialogUtils dialogUtils, FragmentManager fragmentManager, String str, View view, String str2, String str3, int i, CommonDialog.OnDialogEventListener onDialogEventListener, int i2, Object obj) {
        String str4;
        String str5;
        if ((i2 & 8) != 0) {
            String languageText = LanguageUtil.getLanguageText(R.string.alexa_logout_cancel);
            Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…ring.alexa_logout_cancel)");
            str4 = languageText;
        } else {
            str4 = str2;
        }
        if ((i2 & 16) != 0) {
            String languageText2 = LanguageUtil.getLanguageText(R.string.alexa_logout_ok);
            Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…R.string.alexa_logout_ok)");
            str5 = languageText2;
        } else {
            str5 = str3;
        }
        return dialogUtils.showCustomDialog(fragmentManager, str, view, str4, str5, (i2 & 32) != 0 ? 81 : i, (i2 & 64) != 0 ? (CommonDialog.OnDialogEventListener) null : onDialogEventListener);
    }

    public final CommonDialog showCustomDialog(FragmentManager fragmentManager, String title, View view, String cancelTxt, String confirmTxt, int gravity, CommonDialog.OnDialogEventListener listener) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(cancelTxt, "cancelTxt");
        Intrinsics.checkParameterIsNotNull(confirmTxt, "confirmTxt");
        CommonDialog commonDialogNewInstance$default = CommonDialog.Companion.newInstance$default(CommonDialog.INSTANCE, title, cancelTxt, confirmTxt, gravity, 0.0f, 16, null);
        commonDialogNewInstance$default.setOnConfirmClickListener(listener);
        commonDialogNewInstance$default.show(fragmentManager);
        commonDialogNewInstance$default.setContentView(view);
        commonDialogNewInstance$default.showButton();
        commonDialogNewInstance$default.noTips();
        return commonDialogNewInstance$default;
    }

    /* JADX INFO: compiled from: DialogUtils.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016¨\u0006\t"}, d2 = {"Lcom/ido/life/dialog/DialogUtils$SampleDialogEventListener;", "Lcom/ido/life/dialog/CommonDialog$OnDialogEventListener;", "()V", "onCancelClick", "", "dialog", "Lcom/ido/life/dialog/CommonDialog;", "onConfirmClick", "onDismiss", "app_release"}, k = 1, mv = {1, 1, 16})
    public static class SampleDialogEventListener implements CommonDialog.OnDialogEventListener {
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