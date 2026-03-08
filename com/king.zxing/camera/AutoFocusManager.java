package com.king.zxing.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import com.king.zxing.Preferences;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes3.dex */
final class AutoFocusManager implements Camera.AutoFocusCallback {
    private static final long AUTO_FOCUS_INTERVAL_MS = 1200;
    private final Camera camera;
    private boolean focusing;
    private AsyncTask<?, ?, ?> outstandingTask;
    private boolean stopped;
    private final boolean useAutoFocus;
    private static final String TAG = AutoFocusManager.class.getSimpleName();
    private static final Collection<String> FOCUS_MODES_CALLING_AF = new ArrayList(2);

    static {
        FOCUS_MODES_CALLING_AF.add("auto");
        FOCUS_MODES_CALLING_AF.add("macro");
    }

    AutoFocusManager(Context context, Camera camera) {
        this.camera = camera;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String focusMode = camera.getParameters().getFocusMode();
        this.useAutoFocus = defaultSharedPreferences.getBoolean(Preferences.KEY_AUTO_FOCUS, true) && FOCUS_MODES_CALLING_AF.contains(focusMode);
        Log.i(TAG, "Current focus mode '" + focusMode + "'; use auto focus? " + this.useAutoFocus);
        start();
    }

    @Override // android.hardware.Camera.AutoFocusCallback
    public synchronized void onAutoFocus(boolean z, Camera camera) {
        this.focusing = false;
        autoFocusAgainLater();
    }

    private synchronized void autoFocusAgainLater() {
        if (!this.stopped && this.outstandingTask == null) {
            AutoFocusTask autoFocusTask = new AutoFocusTask(this);
            try {
                autoFocusTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                this.outstandingTask = autoFocusTask;
            } catch (RejectedExecutionException e2) {
                Log.w(TAG, "Could not request auto focus", e2);
            }
        }
    }

    synchronized void start() {
        if (this.useAutoFocus) {
            this.outstandingTask = null;
            if (!this.stopped && !this.focusing) {
                try {
                    this.camera.autoFocus(this);
                    this.focusing = true;
                } catch (RuntimeException e2) {
                    Log.w(TAG, "Unexpected exception while focusing", e2);
                    autoFocusAgainLater();
                }
            }
        }
    }

    private synchronized void cancelOutstandingTask() {
        if (this.outstandingTask != null) {
            if (this.outstandingTask.getStatus() != AsyncTask.Status.FINISHED) {
                this.outstandingTask.cancel(true);
            }
            this.outstandingTask = null;
        }
    }

    synchronized void stop() {
        this.stopped = true;
        if (this.useAutoFocus) {
            cancelOutstandingTask();
            try {
                this.camera.cancelAutoFocus();
            } catch (RuntimeException e2) {
                Log.w(TAG, "Unexpected exception while cancelling focusing", e2);
            }
        }
    }

    private static class AutoFocusTask extends AsyncTask<Object, Object, Object> {
        private WeakReference<AutoFocusManager> weakReference;

        public AutoFocusTask(AutoFocusManager autoFocusManager) {
            this.weakReference = new WeakReference<>(autoFocusManager);
        }

        @Override // android.os.AsyncTask
        protected Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(AutoFocusManager.AUTO_FOCUS_INTERVAL_MS);
            } catch (InterruptedException unused) {
            }
            AutoFocusManager autoFocusManager = this.weakReference.get();
            if (autoFocusManager == null) {
                return null;
            }
            autoFocusManager.start();
            return null;
        }
    }
}