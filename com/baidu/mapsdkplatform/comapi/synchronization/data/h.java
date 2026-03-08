package com.baidu.mapsdkplatform.comapi.synchronization.data;

import android.os.Message;
import com.baidu.mapsdkplatform.comapi.synchronization.c.c;
import com.tencent.bugly.BuglyStrategy;

/* JADX INFO: loaded from: classes.dex */
class h extends com.baidu.mapsdkplatform.comapi.synchronization.c.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ g f3759a;

    h(g gVar) {
        this.f3759a = gVar;
    }

    @Override // com.baidu.mapsdkplatform.comapi.synchronization.c.e
    public void a(c.a aVar) {
        Message messageObtainMessage = this.f3759a.j.obtainMessage();
        messageObtainMessage.what = 100001;
        messageObtainMessage.obj = aVar;
        if (this.f3759a.j != null) {
            this.f3759a.j.sendMessage(messageObtainMessage);
        }
        if (c.a.SERVER_ERROR == aVar || c.a.NETWORK_ERROR == aVar || c.a.INNER_ERROR == aVar) {
            g.b(this.f3759a);
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.synchronization.c.e
    public void a(String str) {
        Message messageObtainMessage = this.f3759a.j.obtainMessage();
        messageObtainMessage.what = BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH;
        messageObtainMessage.obj = str;
        if (this.f3759a.j != null) {
            this.f3759a.j.sendMessage(messageObtainMessage);
        }
        this.f3759a.k = 0;
    }
}