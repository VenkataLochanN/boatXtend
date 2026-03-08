package com.ido.life.module.sport.view;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class SportDeletePopupWindow_ViewBinding implements Unbinder {
    private SportDeletePopupWindow target;

    public SportDeletePopupWindow_ViewBinding(SportDeletePopupWindow sportDeletePopupWindow, View view) {
        this.target = sportDeletePopupWindow;
        sportDeletePopupWindow.mTvDelete = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_delete, "field 'mTvDelete'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportDeletePopupWindow sportDeletePopupWindow = this.target;
        if (sportDeletePopupWindow == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportDeletePopupWindow.mTvDelete = null;
    }
}