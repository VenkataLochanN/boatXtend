package com.ido.ble.callback;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class PhoneMsgNoticeCallBack {

    public interface ICallBack {
        void onCalling();

        void onNewMessage();

        void onStopCall();

        @Deprecated
        void onUnReadMessage();

        void onV3MessageNotice(int i);
    }

    public static void a() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.PhoneMsgNoticeCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().w().iterator();
                while (it.hasNext()) {
                    it.next().onCalling();
                }
            }
        });
    }

    public static void a(final int i) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.PhoneMsgNoticeCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().w().iterator();
                while (it.hasNext()) {
                    it.next().onV3MessageNotice(i);
                }
            }
        });
    }

    public static void b() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.PhoneMsgNoticeCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().w().iterator();
                while (it.hasNext()) {
                    it.next().onNewMessage();
                }
            }
        });
    }

    public static void c() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.PhoneMsgNoticeCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().w().iterator();
                while (it.hasNext()) {
                    it.next().onStopCall();
                }
            }
        });
    }

    @Deprecated
    public static void d() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.PhoneMsgNoticeCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().w().iterator();
                while (it.hasNext()) {
                    it.next().onUnReadMessage();
                }
            }
        });
    }
}