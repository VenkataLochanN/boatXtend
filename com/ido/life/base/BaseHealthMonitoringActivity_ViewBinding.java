package com.ido.life.base;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class BaseHealthMonitoringActivity_ViewBinding implements Unbinder {
    private BaseHealthMonitoringActivity target;

    public BaseHealthMonitoringActivity_ViewBinding(BaseHealthMonitoringActivity baseHealthMonitoringActivity) {
        this(baseHealthMonitoringActivity, baseHealthMonitoringActivity.getWindow().getDecorView());
    }

    public BaseHealthMonitoringActivity_ViewBinding(BaseHealthMonitoringActivity baseHealthMonitoringActivity, View view) {
        this.target = baseHealthMonitoringActivity;
        baseHealthMonitoringActivity.lay_content = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_content, "field 'lay_content'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BaseHealthMonitoringActivity baseHealthMonitoringActivity = this.target;
        if (baseHealthMonitoringActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        baseHealthMonitoringActivity.lay_content = null;
    }
}