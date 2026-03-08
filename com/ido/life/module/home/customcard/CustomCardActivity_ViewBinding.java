package com.ido.life.module.home.customcard;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class CustomCardActivity_ViewBinding implements Unbinder {
    private CustomCardActivity target;

    public CustomCardActivity_ViewBinding(CustomCardActivity customCardActivity) {
        this(customCardActivity, customCardActivity.getWindow().getDecorView());
    }

    public CustomCardActivity_ViewBinding(CustomCardActivity customCardActivity, View view) {
        this.target = customCardActivity;
        customCardActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CustomCardActivity customCardActivity = this.target;
        if (customCardActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        customCardActivity.mRecyclerView = null;
    }
}