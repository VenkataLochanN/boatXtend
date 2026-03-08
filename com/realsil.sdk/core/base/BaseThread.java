package com.realsil.sdk.core.base;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseThread<T> extends Thread {
    public volatile boolean hc = false;
    public BlockingQueue<T> mQueue = new LinkedBlockingDeque();

    public void addQueue(T t) {
        synchronized (this.mQueue) {
            this.mQueue.add(t);
        }
    }

    public void cancel(boolean z) {
        this.hc = z;
    }

    public void clearQueue() {
        synchronized (this.mQueue) {
            this.mQueue.clear();
        }
    }

    public boolean isCanceled() {
        return this.hc;
    }

    public T peek() {
        T tPeek;
        synchronized (this.mQueue) {
            tPeek = this.mQueue.peek();
        }
        return tPeek;
    }

    public T poll() {
        T tPoll;
        synchronized (this.mQueue) {
            tPoll = this.mQueue.poll();
        }
        return tPoll;
    }

    public T take() {
        try {
            return this.mQueue.take();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}