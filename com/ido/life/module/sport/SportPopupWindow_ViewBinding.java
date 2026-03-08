package com.ido.life.module.sport;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class SportPopupWindow_ViewBinding implements Unbinder {
    private SportPopupWindow target;

    public SportPopupWindow_ViewBinding(SportPopupWindow sportPopupWindow, View view) {
        this.target = sportPopupWindow;
        sportPopupWindow.mTvAddDevice = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_add_device, "field 'mTvAddDevice'", TextView.class);
        sportPopupWindow.mTvEditCard = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_edit_card, "field 'mTvEditCard'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportPopupWindow sportPopupWindow = this.target;
        if (sportPopupWindow == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportPopupWindow.mTvAddDevice = null;
        sportPopupWindow.mTvEditCard = null;
    }
}