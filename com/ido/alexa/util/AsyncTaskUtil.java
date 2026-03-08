package com.ido.alexa.util;

import android.R;
import android.os.AsyncTask;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public class AsyncTaskUtil {
    private IAsyncTaskCallBack iAsyncTaskCallBack;
    private MyTask myTask;

    public static class AsyncTaskCallBackAdapter implements IAsyncTaskCallBack {
        @Override // com.ido.alexa.util.AsyncTaskUtil.IAsyncTaskCallBack
        public Object doInBackground(String... strArr) {
            return null;
        }

        @Override // com.ido.alexa.util.AsyncTaskUtil.IAsyncTaskCallBack
        public void onPostExecute(Object obj) {
        }
    }

    public interface IAsyncTaskCallBack {
        Object doInBackground(String... strArr);

        void onPostExecute(Object obj);
    }

    public AsyncTaskUtil() {
    }

    public AsyncTaskUtil(IAsyncTaskCallBack iAsyncTaskCallBack) {
        this.iAsyncTaskCallBack = iAsyncTaskCallBack;
    }

    public AsyncTaskUtil setIAsyncTaskCallBack(IAsyncTaskCallBack iAsyncTaskCallBack) {
        this.iAsyncTaskCallBack = iAsyncTaskCallBack;
        return this;
    }

    public void execute(String... strArr) {
        this.myTask = new MyTask();
        this.myTask.execute(strArr);
    }

    public void executeOnExecutor(Executor executor, String... strArr) {
        this.myTask = new MyTask();
        this.myTask.executeOnExecutor(executor, strArr);
    }

    public void cancel(boolean z) {
        MyTask myTask = this.myTask;
        if (myTask != null) {
            myTask.cancel(z);
        }
    }

    class MyTask extends AsyncTask<String, R.integer, Object> {
        @Override // android.os.AsyncTask
        protected void onPreExecute() {
        }

        MyTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Object doInBackground(String... strArr) {
            return AsyncTaskUtil.this.iAsyncTaskCallBack.doInBackground(strArr);
        }

        @Override // android.os.AsyncTask
        protected void onPostExecute(Object obj) {
            AsyncTaskUtil.this.iAsyncTaskCallBack.onPostExecute(obj);
        }
    }
}