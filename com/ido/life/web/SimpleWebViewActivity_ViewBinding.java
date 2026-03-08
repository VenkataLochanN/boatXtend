package com.ido.life.web;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes3.dex */
public class SimpleWebViewActivity_ViewBinding implements Unbinder {
    private SimpleWebViewActivity target;

    public SimpleWebViewActivity_ViewBinding(SimpleWebViewActivity simpleWebViewActivity) {
        this(simpleWebViewActivity, simpleWebViewActivity.getWindow().getDecorView());
    }

    public SimpleWebViewActivity_ViewBinding(SimpleWebViewActivity simpleWebViewActivity, View view) {
        this.target = simpleWebViewActivity;
        simpleWebViewActivity.mTitleLeftBtn = (ImageButton) Utils.findRequiredViewAsType(view, R.id.title_leftBtn, "field 'mTitleLeftBtn'", ImageButton.class);
        simpleWebViewActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SimpleWebViewActivity simpleWebViewActivity = this.target;
        if (simpleWebViewActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        simpleWebViewActivity.mTitleLeftBtn = null;
        simpleWebViewActivity.mTitleText = null;
    }
}