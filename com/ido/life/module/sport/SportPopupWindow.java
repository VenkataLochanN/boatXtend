package com.ido.life.module.sport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.boat.Xtend.two.R;
import com.ido.life.module.bind.ChoiceBlueTypeActivity;

/* JADX INFO: loaded from: classes2.dex */
public class SportPopupWindow extends PopupWindow {
    private OnItemClickListener mOnItemClickListener;

    @BindView(R.id.tv_add_device)
    TextView mTvAddDevice;

    @BindView(R.id.tv_edit_card)
    TextView mTvEditCard;

    public interface OnItemClickListener {
        void onEditCardClick(View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public SportPopupWindow(Context context) {
        super(context);
        initWindow(context);
        initListener(context);
    }

    private void initListener(final Context context) {
        this.mTvAddDevice.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.SportPopupWindow.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SportPopupWindow.this.dismiss();
                context.startActivity(new Intent(context, (Class<?>) ChoiceBlueTypeActivity.class));
            }
        });
        this.mTvEditCard.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.SportPopupWindow.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SportPopupWindow.this.dismiss();
                if (SportPopupWindow.this.mOnItemClickListener != null) {
                    SportPopupWindow.this.mOnItemClickListener.onEditCardClick(view);
                }
            }
        });
    }

    private void initWindow(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.popup_sport_more, (ViewGroup) null);
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