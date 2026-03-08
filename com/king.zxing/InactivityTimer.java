package com.king.zxing;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes3.dex */
final class InactivityTimer {
    private static final long INACTIVITY_DELAY_MS = 300000;
    private static final String TAG = InactivityTimer.class.getSimpleName();
    private final Activity activity;
    private AsyncTask<Object, Object, Object> inactivityTask;
    private final BroadcastReceiver powerStatusReceiver = new PowerStatusReceiver(this);
    private boolean registered = false;

    InactivityTimer(Activity activity) {
        this.activity = activity;
        onActivity();
    }

    void onActivity() {
        cancel();
        this.inactivityTask = new InactivityAsyncTask(this.activity);
        try {
            this.inactivityTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        } catch (RejectedExecutionException unused) {
            Log.w(TAG, "Couldn't schedule inactivity task; ignoring");
        }
    }

    void onPause() {
        cancel();
        if (this.registered) {
            this.activity.unregisterReceiver(this.powerStatusReceiver);
            this.registered = false;
        } else {
            Log.w(TAG, "PowerStatusReceiver was never registered?");
        }
    }

    void onResume() {
        if (this.registered) {
            Log.w(TAG, "PowerStatusReceiver was already registered?");
        } else {
            this.activity.registerReceiver(this.powerStatusReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            this.registered = true;
        }
        onActivity();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancel() {
        AsyncTask<Object, Object, Object> asyncTask = this.inactivityTask;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.inactivityTask = null;
        }
    }

    void shutdown() {
        cancel();
    }

    private static class PowerStatusReceiver extends BroadcastReceiver {
        private WeakReference<InactivityTimer> weakReference;

        public PowerStatusReceiver(InactivityTimer inactivityTimer) {
            this.weakReference = new WeakReference<>(inactivityTimer);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            InactivityTimer inactivityTimer;
            if (!"android.intent.action.BATTERY_CHANGED".equals(intent.getAction()) || (inactivityTimer = this.weakReference.get()) == null) {
                return;
            }
            if (!(intent.getIntExtra("plugged", -1) <= 0)) {
                inactivityTimer.cancel();
            } else {
                inactivityTimer.onActivity();
            }
        }
    }

    private static class InactivityAsyncTask extends AsyncTask<Object, Object, Object> {
        private WeakReference<Activity> weakReference;

        public InactivityAsyncTask(Activity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override // android.os.AsyncTask
        protected Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(InactivityTimer.INACTIVITY_DELAY_MS);
                Log.i(InactivityTimer.TAG, "Finishing activity due to inactivity");
                Activity activity = this.weakReference.get();
                if (activity == null) {
                    return null;
                }
                activity.finish();
                return null;
            } catch (InterruptedException unused) {
                return null;
            }
        }
    }
}