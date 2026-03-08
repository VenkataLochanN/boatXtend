package com.baidu.mapapi.map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class aa extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ View f3032a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ WearMapView f3033b;

    aa(WearMapView wearMapView, View view) {
        this.f3033b = wearMapView;
        this.f3032a = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f3032a.setVisibility(4);
        super.onAnimationEnd(animator);
    }
}