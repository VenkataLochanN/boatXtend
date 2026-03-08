package com.lzy.imagepicker.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.lzy.imagepicker.R;

/* JADX INFO: loaded from: classes3.dex */
public class FolderPopUpWindow extends PopupWindow implements View.OnClickListener {
    private ListView listView;
    private int marginPx;
    private final View marginView;
    private final View masker;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(AdapterView<?> adapterView, View view, int i, long j);
    }

    public FolderPopUpWindow(Context context, BaseAdapter baseAdapter) {
        super(context);
        final View viewInflate = View.inflate(context, R.layout.pop_folder, null);
        this.masker = viewInflate.findViewById(R.id.masker);
        this.masker.setOnClickListener(this);
        this.marginView = viewInflate.findViewById(R.id.margin);
        this.marginView.setOnClickListener(this);
        this.listView = (ListView) viewInflate.findViewById(R.id.listView);
        this.listView.setAdapter((ListAdapter) baseAdapter);
        setContentView(viewInflate);
        setWidth(-1);
        setHeight(-1);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(0));
        setAnimationStyle(0);
        viewInflate.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.lzy.imagepicker.view.FolderPopUpWindow.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                viewInflate.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int height = (viewInflate.getHeight() * 5) / 8;
                int height2 = FolderPopUpWindow.this.listView.getHeight();
                ViewGroup.LayoutParams layoutParams = FolderPopUpWindow.this.listView.getLayoutParams();
                if (height2 <= height) {
                    height = height2;
                }
                layoutParams.height = height;
                FolderPopUpWindow.this.listView.setLayoutParams(layoutParams);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) FolderPopUpWindow.this.marginView.getLayoutParams();
                layoutParams2.height = FolderPopUpWindow.this.marginPx;
                FolderPopUpWindow.this.marginView.setLayoutParams(layoutParams2);
                FolderPopUpWindow.this.enterAnimator();
            }
        });
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lzy.imagepicker.view.FolderPopUpWindow.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (FolderPopUpWindow.this.onItemClickListener != null) {
                    FolderPopUpWindow.this.onItemClickListener.onItemClick(adapterView, view, i, j);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enterAnimator() {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.masker, "alpha", 0.0f, 1.0f);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.listView, "translationY", r2.getHeight(), 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400L);
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();
    }

    @Override // android.widget.PopupWindow
    public void dismiss() {
        exitAnimator();
    }

    private void exitAnimator() {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.masker, "alpha", 1.0f, 0.0f);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.listView, "translationY", 0.0f, r2.getHeight());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300L);
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.lzy.imagepicker.view.FolderPopUpWindow.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                FolderPopUpWindow.this.listView.setVisibility(0);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                FolderPopUpWindow.super.dismiss();
            }
        });
        animatorSet.start();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setSelection(int i) {
        this.listView.setSelection(i);
    }

    public void setMargin(int i) {
        this.marginPx = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        dismiss();
    }
}