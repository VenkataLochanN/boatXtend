package com.ido.life.module.alexa;

import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class AwakeAlexaActivity_ViewBinding implements Unbinder {
    private AwakeAlexaActivity target;
    private View view7f0a0643;

    public AwakeAlexaActivity_ViewBinding(AwakeAlexaActivity awakeAlexaActivity) {
        this(awakeAlexaActivity, awakeAlexaActivity.getWindow().getDecorView());
    }

    public AwakeAlexaActivity_ViewBinding(final AwakeAlexaActivity awakeAlexaActivity, View view) {
        this.target = awakeAlexaActivity;
        awakeAlexaActivity.mAwakeImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.awakeImg, "field 'mAwakeImg'", ImageView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.rtv_close, "method 'onCloseClick'");
        this.view7f0a0643 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AwakeAlexaActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                awakeAlexaActivity.onCloseClick();
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AwakeAlexaActivity awakeAlexaActivity = this.target;
        if (awakeAlexaActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        awakeAlexaActivity.mAwakeImg = null;
        this.view7f0a0643.setOnClickListener(null);
        this.view7f0a0643 = null;
    }
}