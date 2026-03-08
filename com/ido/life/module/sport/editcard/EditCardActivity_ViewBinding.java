package com.ido.life.module.sport.editcard;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class EditCardActivity_ViewBinding implements Unbinder {
    private EditCardActivity target;
    private View view7f0a0776;

    public EditCardActivity_ViewBinding(EditCardActivity editCardActivity) {
        this(editCardActivity, editCardActivity.getWindow().getDecorView());
    }

    public EditCardActivity_ViewBinding(final EditCardActivity editCardActivity, View view) {
        this.target = editCardActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mTitleLeftBtn' and method 'back'");
        editCardActivity.mTitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mTitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.editcard.EditCardActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                editCardActivity.back(view2);
            }
        });
        editCardActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        editCardActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        EditCardActivity editCardActivity = this.target;
        if (editCardActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        editCardActivity.mTitleLeftBtn = null;
        editCardActivity.mTitleText = null;
        editCardActivity.mRecyclerView = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
    }
}