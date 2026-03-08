package com.ido.ble.callback;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    static class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator<b> it = com.ido.ble.callback.b.K().H().iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
    }

    public interface b {
        void a();
    }

    public static final void a() {
        com.ido.ble.callback.b.K().a(new a());
    }
}