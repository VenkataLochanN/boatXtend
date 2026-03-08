package com.ido.life.viewholder;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.cardview.CustomCardView;

/* JADX INFO: loaded from: classes3.dex */
public class BaseHomeItemViewHolder_ViewBinding implements Unbinder {
    private BaseHomeItemViewHolder target;

    public BaseHomeItemViewHolder_ViewBinding(BaseHomeItemViewHolder baseHomeItemViewHolder, View view) {
        this.target = baseHomeItemViewHolder;
        baseHomeItemViewHolder.mCardView = (CustomCardView) Utils.findRequiredViewAsType(view, R.id.card_out, "field 'mCardView'", CustomCardView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BaseHomeItemViewHolder baseHomeItemViewHolder = this.target;
        if (baseHomeItemViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        baseHomeItemViewHolder.mCardView = null;
    }
}