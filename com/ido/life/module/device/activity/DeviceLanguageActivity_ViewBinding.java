package com.ido.life.module.device.activity;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceLanguageActivity_ViewBinding implements Unbinder {
    private DeviceLanguageActivity target;

    public DeviceLanguageActivity_ViewBinding(DeviceLanguageActivity deviceLanguageActivity) {
        this(deviceLanguageActivity, deviceLanguageActivity.getWindow().getDecorView());
    }

    public DeviceLanguageActivity_ViewBinding(DeviceLanguageActivity deviceLanguageActivity, View view) {
        this.target = deviceLanguageActivity;
        deviceLanguageActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DeviceLanguageActivity deviceLanguageActivity = this.target;
        if (deviceLanguageActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        deviceLanguageActivity.mRecyclerView = null;
    }
}