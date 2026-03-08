package com.ido.life.module.sport.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class SportDeletePopupWindow extends PopupWindow {
    private OnItemClickListener mOnItemClickListener;

    @BindView(R.id.tv_delete)
    TextView mTvDelete;

    public interface OnItemClickListener {
        void onDeleteClick(View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public SportDeletePopupWindow(Context context) {
        super(context);
        initWindow(context);
        initListener(context);
    }

    private void initListener(Context context) {
        this.mTvDelete.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.view.SportDeletePopupWindow.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SportDeletePopupWindow.this.dismiss();
                if (SportDeletePopupWindow.this.mOnItemClickListener != null) {
                    SportDeletePopupWindow.this.mOnItemClickListener.onDeleteClick(view);
                }
            }
        });
    }

    private void initWindow(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.popup_sport_delete, (ViewGroup) null);
        setContentView(viewInflate);
        ButterKnife.bind(this, viewInflate);
        setWidth(-2);
        setHeight(-2);
        setBackgroundDrawable(new ColorDrawable(0));
        setAnimationStyle(R.style.pop_more);
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
    }
}