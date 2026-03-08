package com.baidu.lbsapi.auth;

import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
class j implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f2005a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ boolean f2006b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f2007c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ String f2008d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ Hashtable f2009e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final /* synthetic */ LBSAuthManager f2010f;

    j(LBSAuthManager lBSAuthManager, int i, boolean z, String str, String str2, Hashtable hashtable) {
        this.f2010f = lBSAuthManager;
        this.f2005a = i;
        this.f2006b = z;
        this.f2007c = str;
        this.f2008d = str2;
        this.f2009e = hashtable;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (a.f1989a) {
            a.a("status = " + this.f2005a + "; forced = " + this.f2006b + "checkAK = " + this.f2010f.b(this.f2007c));
        }
        int i = this.f2005a;
        if (i != 601 && !this.f2006b && i != -1 && !this.f2010f.b(this.f2007c)) {
            if (602 == this.f2005a) {
                if (a.f1989a) {
                    a.a("authenticate wait  ");
                }
                if (LBSAuthManager.f1982d != null) {
                    LBSAuthManager.f1982d.b();
                }
            } else if (a.f1989a) {
                a.a("authenticate else  ");
            }
            this.f2010f.a((String) null, this.f2007c);
            return;
        }
        if (a.f1989a) {
            a.a("authenticate sendAuthRequest");
        }
        String[] strArrB = b.b(LBSAuthManager.f1981a);
        if (a.f1989a) {
            a.a("authStrings.length:" + strArrB.length);
        }
        if (strArrB == null || strArrB.length <= 1) {
            this.f2010f.a(this.f2006b, this.f2008d, this.f2009e, this.f2007c);
            return;
        }
        if (a.f1989a) {
            a.a("more sha1 auth");
        }
        this.f2010f.a(this.f2006b, this.f2008d, (Hashtable<String, String>) this.f2009e, strArrB, this.f2007c);
    }
}