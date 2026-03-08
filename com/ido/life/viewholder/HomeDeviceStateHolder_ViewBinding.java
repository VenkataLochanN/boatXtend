package com.ido.life.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes3.dex */
public class HomeDeviceStateHolder_ViewBinding implements Unbinder {
    private HomeDeviceStateHolder target;

    public HomeDeviceStateHolder_ViewBinding(HomeDeviceStateHolder homeDeviceStateHolder, View view) {
        this.target = homeDeviceStateHolder;
        homeDeviceStateHolder.mTvMessage = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_device_state_message, "field 'mTvMessage'", TextView.class);
        homeDeviceStateHolder.mTvAction = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_device_state_action, "field 'mTvAction'", TextView.class);
        homeDeviceStateHolder.mImgClose = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_device_state_close, "field 'mImgClose'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        HomeDeviceStateHolder homeDeviceStateHolder = this.target;
        if (homeDeviceStateHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        homeDeviceStateHolder.mTvMessage = null;
        homeDeviceStateHolder.mTvAction = null;
        homeDeviceStateHolder.mImgClose = null;
    }
}