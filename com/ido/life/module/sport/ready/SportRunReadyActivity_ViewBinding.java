package com.ido.life.module.sport.ready;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class SportRunReadyActivity_ViewBinding implements Unbinder {
    private SportRunReadyActivity target;

    public SportRunReadyActivity_ViewBinding(SportRunReadyActivity sportRunReadyActivity) {
        this(sportRunReadyActivity, sportRunReadyActivity.getWindow().getDecorView());
    }

    public SportRunReadyActivity_ViewBinding(SportRunReadyActivity sportRunReadyActivity, View view) {
        this.target = sportRunReadyActivity;
        sportRunReadyActivity.mTvReady = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ready, "field 'mTvReady'", TextView.class);
        sportRunReadyActivity.alphaImageView = Utils.findRequiredView(view, R.id.alphaImageView, "field 'alphaImageView'");
        sportRunReadyActivity.rootLayout = Utils.findRequiredView(view, R.id.root_layout, "field 'rootLayout'");
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportRunReadyActivity sportRunReadyActivity = this.target;
        if (sportRunReadyActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportRunReadyActivity.mTvReady = null;
        sportRunReadyActivity.alphaImageView = null;
        sportRunReadyActivity.rootLayout = null;
    }
}