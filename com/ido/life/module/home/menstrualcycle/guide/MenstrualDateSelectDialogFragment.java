package com.ido.life.module.home.menstrualcycle.guide;

import android.app.Dialog;
import android.content.res.Resources;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MenstrualDateSelectDialogFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014¨\u0006\t"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/guide/MenstrualDateSelectDialogFragment;", "Lcom/ido/common/base/BaseDialogFragment;", "()V", "getLayoutResId", "", "initUI", "", "view", "Landroid/view/View;", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MenstrualDateSelectDialogFragment extends BaseDialogFragment {
    private HashMap _$_findViewCache;

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
        return R.layout.dialog_menstrual_date_select;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        super.initUI(view);
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
                window.getDecorView().setPadding(10, 0, 10, 10);
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.gravity = 80;
                attributes.width = -1;
                Resources resources = getResources();
                Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
                attributes.height = resources.getDisplayMetrics().heightPixels / 3;
                window.setAttributes(attributes);
            }
        }
    }
}