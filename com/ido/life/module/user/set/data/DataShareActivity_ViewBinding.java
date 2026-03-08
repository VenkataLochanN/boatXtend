package com.ido.life.module.user.set.data;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes3.dex */
public class DataShareActivity_ViewBinding implements Unbinder {
    private DataShareActivity target;
    private View view7f0a0776;

    public DataShareActivity_ViewBinding(DataShareActivity dataShareActivity) {
        this(dataShareActivity, dataShareActivity.getWindow().getDecorView());
    }

    public DataShareActivity_ViewBinding(final DataShareActivity dataShareActivity, View view) {
        this.target = dataShareActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mTitleLeftBtn' and method 'toBack'");
        dataShareActivity.mTitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mTitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.data.DataShareActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dataShareActivity.toBack(view2);
            }
        });
        dataShareActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        dataShareActivity.mSwitchGoogleFit = (Switch) Utils.findRequiredViewAsType(view, R.id.switch_google_fit, "field 'mSwitchGoogleFit'", Switch.class);
        dataShareActivity.mSwitchStava = (ImageView) Utils.findRequiredViewAsType(view, R.id.switch_stava, "field 'mSwitchStava'", ImageView.class);
        dataShareActivity.mRelativeLayoutStava = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.relative_strava, "field 'mRelativeLayoutStava'", RelativeLayout.class);
        dataShareActivity.mRelativeGoogleFit = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.relative_google_fit, "field 'mRelativeGoogleFit'", RelativeLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DataShareActivity dataShareActivity = this.target;
        if (dataShareActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dataShareActivity.mTitleLeftBtn = null;
        dataShareActivity.mTitleText = null;
        dataShareActivity.mSwitchGoogleFit = null;
        dataShareActivity.mSwitchStava = null;
        dataShareActivity.mRelativeLayoutStava = null;
        dataShareActivity.mRelativeGoogleFit = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
    }
}