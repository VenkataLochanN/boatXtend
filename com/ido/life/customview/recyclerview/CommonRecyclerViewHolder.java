package com.ido.life.customview.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class CommonRecyclerViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private View mConvertView;
    private SparseArray<View> mViews;

    public CommonRecyclerViewHolder(Context context, View view) {
        super(view);
        this.mContext = context;
        this.mConvertView = view;
        this.mViews = new SparseArray<>();
    }

    public static CommonRecyclerViewHolder createViewHolder(Context context, View view) {
        return new CommonRecyclerViewHolder(context, view);
    }

    public static CommonRecyclerViewHolder createViewHolder(Context context, ViewGroup viewGroup, int i) {
        return new CommonRecyclerViewHolder(context, LayoutInflater.from(context).inflate(i, viewGroup, false));
    }

    public <T extends View> T getView(int i) {
        T t = (T) this.mViews.get(i);
        if (t != null) {
            return t;
        }
        T t2 = (T) this.mConvertView.findViewById(i);
        this.mViews.put(i, t2);
        return t2;
    }

    public View getConvertView() {
        return this.mConvertView;
    }

    public CommonRecyclerViewHolder setText(int i, String str) {
        ((TextView) getView(i)).setText(str);
        return this;
    }

    public CommonRecyclerViewHolder setTextDrawable(int i, String str, int i2) {
        TextView textView = (TextView) getView(i);
        textView.setText(str);
        if (i2 == 0) {
            textView.setCompoundDrawables(null, null, null, null);
        } else {
            Drawable drawable = this.mContext.getResources().getDrawable(i2);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(drawable, null, null, null);
            textView.setCompoundDrawablePadding(this.mContext.getResources().getDimensionPixelOffset(R.dimen.sw_dp_2));
        }
        return this;
    }

    public CommonRecyclerViewHolder setText(int i, int i2) {
        ((TextView) getView(i)).setText(i2);
        return this;
    }

    public CommonRecyclerViewHolder setImageResource(int i, int i2) {
        ((ImageView) getView(i)).setImageResource(i2);
        return this;
    }

    public CommonRecyclerViewHolder setImageBitmap(int i, Bitmap bitmap) {
        ((ImageView) getView(i)).setImageBitmap(bitmap);
        return this;
    }

    public CommonRecyclerViewHolder setImageDrawable(int i, Drawable drawable) {
        ((ImageView) getView(i)).setImageDrawable(drawable);
        return this;
    }

    public CommonRecyclerViewHolder setBackgroundColor(int i, int i2) {
        getView(i).setBackgroundColor(i2);
        return this;
    }

    public CommonRecyclerViewHolder setBackgroundRes(int i, int i2) {
        getView(i).setBackgroundResource(i2);
        return this;
    }

    public CommonRecyclerViewHolder setTextColor(int i, int i2) {
        ((TextView) getView(i)).setTextColor(i2);
        return this;
    }

    public CommonRecyclerViewHolder setTextColorRes(int i, int i2) {
        ((TextView) getView(i)).setTextColor(this.mContext.getResources().getColor(i2));
        return this;
    }

    public CommonRecyclerViewHolder setAlpha(int i, float f2) {
        if (Build.VERSION.SDK_INT >= 11) {
            getView(i).setAlpha(f2);
        } else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(f2, f2);
            alphaAnimation.setDuration(0L);
            alphaAnimation.setFillAfter(true);
            getView(i).startAnimation(alphaAnimation);
        }
        return this;
    }

    public CommonRecyclerViewHolder setVisible(int i, boolean z) {
        getView(i).setVisibility(z ? 0 : 8);
        return this;
    }

    public CommonRecyclerViewHolder linkify(int i) {
        Linkify.addLinks((TextView) getView(i), 15);
        return this;
    }

    public CommonRecyclerViewHolder setTypeface(Typeface typeface, int... iArr) {
        for (int i : iArr) {
            TextView textView = (TextView) getView(i);
            textView.setTypeface(typeface);
            textView.setPaintFlags(textView.getPaintFlags() | 128);
        }
        return this;
    }

    public CommonRecyclerViewHolder setProgress(int i, int i2) {
        ((ProgressBar) getView(i)).setProgress(i2);
        return this;
    }

    public CommonRecyclerViewHolder setProgress(int i, int i2, int i3) {
        ProgressBar progressBar = (ProgressBar) getView(i);
        progressBar.setMax(i3);
        progressBar.setProgress(i2);
        return this;
    }

    public CommonRecyclerViewHolder setMax(int i, int i2) {
        ((ProgressBar) getView(i)).setMax(i2);
        return this;
    }

    public CommonRecyclerViewHolder setRating(int i, float f2) {
        ((RatingBar) getView(i)).setRating(f2);
        return this;
    }

    public CommonRecyclerViewHolder setRating(int i, float f2, int i2) {
        RatingBar ratingBar = (RatingBar) getView(i);
        ratingBar.setMax(i2);
        ratingBar.setRating(f2);
        return this;
    }

    public CommonRecyclerViewHolder setTag(int i, Object obj) {
        getView(i).setTag(obj);
        return this;
    }

    public CommonRecyclerViewHolder setTag(int i, int i2, Object obj) {
        getView(i).setTag(i2, obj);
        return this;
    }

    public CommonRecyclerViewHolder setChecked(int i, boolean z) {
        ((Checkable) getView(i)).setChecked(z);
        return this;
    }

    public CommonRecyclerViewHolder setOnClickListener(int i, View.OnClickListener onClickListener) {
        getView(i).setOnClickListener(onClickListener);
        return this;
    }

    public CommonRecyclerViewHolder setOnTouchListener(int i, View.OnTouchListener onTouchListener) {
        getView(i).setOnTouchListener(onTouchListener);
        return this;
    }

    public CommonRecyclerViewHolder setOnLongClickListener(int i, View.OnLongClickListener onLongClickListener) {
        getView(i).setOnLongClickListener(onLongClickListener);
        return this;
    }
}