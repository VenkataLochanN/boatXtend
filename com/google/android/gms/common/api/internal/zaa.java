package com.google.android.gms.common.api.internal;

import android.app.Activity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zaa extends ActivityLifecycleObserver {
    private final WeakReference<C0039zaa> zacl;

    public zaa(Activity activity) {
        this(C0039zaa.zaa(activity));
    }

    private zaa(C0039zaa c0039zaa) {
        this.zacl = new WeakReference<>(c0039zaa);
    }

    @Override // com.google.android.gms.common.api.internal.ActivityLifecycleObserver
    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        C0039zaa c0039zaa = this.zacl.get();
        if (c0039zaa == null) {
            throw new IllegalStateException("The target activity has already been GC'd");
        }
        c0039zaa.zaa(runnable);
        return this;
    }

    /* JADX INFO: renamed from: com.google.android.gms.common.api.internal.zaa$zaa, reason: collision with other inner class name */
    static class C0039zaa extends LifecycleCallback {
        private List<Runnable> zacm;

        /* JADX INFO: Access modifiers changed from: private */
        public static C0039zaa zaa(Activity activity) {
            C0039zaa c0039zaa;
            synchronized (activity) {
                LifecycleFragment fragment = getFragment(activity);
                c0039zaa = (C0039zaa) fragment.getCallbackOrNull("LifecycleObserverOnStop", C0039zaa.class);
                if (c0039zaa == null) {
                    c0039zaa = new C0039zaa(fragment);
                }
            }
            return c0039zaa;
        }

        private C0039zaa(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.zacm = new ArrayList();
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final synchronized void zaa(Runnable runnable) {
            this.zacm.add(runnable);
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        public void onStop() {
            List<Runnable> list;
            synchronized (this) {
                list = this.zacm;
                this.zacm = new ArrayList();
            }
            Iterator<Runnable> it = list.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
        }
    }
}