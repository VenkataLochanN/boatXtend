package com.ido.life.dialog;

import android.view.View;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.AppUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AlexSupportPermissionDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\u0012\u0010\t\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014¨\u0006\u000b"}, d2 = {"Lcom/ido/life/dialog/AlexSupportPermissionDialog;", "Lcom/ido/common/base/BaseDialogFragment;", "()V", "getLayoutResId", "", "initListener", "", "view", "Landroid/view/View;", "initUI", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class AlexSupportPermissionDialog extends BaseDialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
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
        return R.layout.alexa_permission_dialog;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* JADX INFO: compiled from: AlexSupportPermissionDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/ido/life/dialog/AlexSupportPermissionDialog$Companion;", "", "()V", "newInstance", "Lcom/ido/life/dialog/AlexSupportPermissionDialog;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AlexSupportPermissionDialog newInstance() {
            AlexSupportPermissionDialog alexSupportPermissionDialog = new AlexSupportPermissionDialog();
            alexSupportPermissionDialog.setStyle(1, 2131886083);
            return alexSupportPermissionDialog;
        }
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initListener(View view) {
        super.initListener(view);
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_confirm)).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.dialog.AlexSupportPermissionDialog.initListener.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AppUtil.toSelfSetting(IdoApp.getAppContext());
            }
        });
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.dialog.AlexSupportPermissionDialog.initListener.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AlexSupportPermissionDialog.this.dismiss();
            }
        });
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        super.initUI(view);
    }
}