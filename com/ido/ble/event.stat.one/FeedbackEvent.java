package com.ido.ble.event.stat.one;

/* JADX INFO: loaded from: classes2.dex */
class FeedbackEvent {
    FeedbackEvent() {
    }

    static com.ido.ble.g.a.a createFeedbackLog(String str, String str2) {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, d.O);
        aVar.a(d.M, str);
        aVar.a(d.N, str2);
        a.a(aVar);
        return aVar;
    }
}