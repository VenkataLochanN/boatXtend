package com.ido.life.module.user.lang;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes3.dex */
public class LanguageActivity_ViewBinding implements Unbinder {
    private LanguageActivity target;
    private View view7f0a0776;

    public LanguageActivity_ViewBinding(LanguageActivity languageActivity) {
        this(languageActivity, languageActivity.getWindow().getDecorView());
    }

    public LanguageActivity_ViewBinding(final LanguageActivity languageActivity, View view) {
        this.target = languageActivity;
        languageActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        languageActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "method 'toBack'");
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.lang.LanguageActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                languageActivity.toBack(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        LanguageActivity languageActivity = this.target;
        if (languageActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        languageActivity.mTitleText = null;
        languageActivity.mRecyclerView = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
    }
}