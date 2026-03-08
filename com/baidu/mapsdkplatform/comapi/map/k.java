package com.baidu.mapsdkplatform.comapi.map;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import com.ido.life.location.MapHelper;

/* JADX INFO: loaded from: classes.dex */
class k extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ j f3598a;

    k(j jVar) {
        this.f3598a = jVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (this.f3598a.f3589g != null && ((Long) message.obj).longValue() == this.f3598a.f3589g.j) {
            boolean z = false;
            if (message.what == 4000) {
                if (this.f3598a.f3589g.f3567h == null) {
                    return;
                }
                for (l lVar : this.f3598a.f3589g.f3567h) {
                    Bitmap bitmapCreateBitmap = null;
                    if (message.arg2 == 1) {
                        int[] iArr = new int[this.f3598a.f3586d * this.f3598a.f3587e];
                        int[] iArr2 = new int[this.f3598a.f3586d * this.f3598a.f3587e];
                        if (this.f3598a.f3589g.i == null) {
                            return;
                        }
                        int[] iArrA = this.f3598a.f3589g.i.a(iArr, this.f3598a.f3586d, this.f3598a.f3587e);
                        for (int i = 0; i < this.f3598a.f3587e; i++) {
                            for (int i2 = 0; i2 < this.f3598a.f3586d; i2++) {
                                int i3 = iArrA[(this.f3598a.f3586d * i) + i2];
                                iArr2[(((this.f3598a.f3587e - i) - 1) * this.f3598a.f3586d) + i2] = (i3 & MapHelper.Standard_Color) | ((i3 << 16) & 16711680) | ((i3 >> 16) & 255);
                            }
                        }
                        bitmapCreateBitmap = Bitmap.createBitmap(iArr2, this.f3598a.f3586d, this.f3598a.f3587e, Bitmap.Config.RGB_565);
                    }
                    if (lVar != null) {
                        lVar.a(bitmapCreateBitmap);
                    }
                }
                return;
            }
            if (message.what == 39) {
                if (this.f3598a.f3589g == null || this.f3598a.f3589g.f3567h == null) {
                    return;
                }
                if (message.arg1 == 100) {
                    this.f3598a.f3589g.B();
                } else if (message.arg1 == 200) {
                    this.f3598a.f3589g.L();
                } else if (message.arg1 == 1) {
                    this.f3598a.requestRender();
                } else if (message.arg1 == 0) {
                    this.f3598a.requestRender();
                    if (!this.f3598a.f3589g.b() && this.f3598a.getRenderMode() != 0) {
                        this.f3598a.setRenderMode(0);
                    }
                } else if (message.arg1 == 2) {
                    if (this.f3598a.f3589g.f3567h == null) {
                        return;
                    }
                    for (l lVar2 : this.f3598a.f3589g.f3567h) {
                        if (lVar2 != null) {
                            lVar2.c();
                        }
                    }
                }
                if (!this.f3598a.f3589g.k && this.f3598a.f3587e > 0 && this.f3598a.f3586d > 0 && this.f3598a.f3589g.b(0, 0) != null) {
                    this.f3598a.f3589g.k = true;
                    for (l lVar3 : this.f3598a.f3589g.f3567h) {
                        if (lVar3 != null) {
                            lVar3.b();
                        }
                    }
                }
                for (l lVar4 : this.f3598a.f3589g.f3567h) {
                    if (lVar4 != null) {
                        lVar4.a();
                    }
                }
                return;
            }
            if (message.what == 41) {
                if (this.f3598a.f3589g == null || this.f3598a.f3589g.f3567h == null) {
                    return;
                }
                if (this.f3598a.f3589g.n || this.f3598a.f3589g.o) {
                    for (l lVar5 : this.f3598a.f3589g.f3567h) {
                        if (lVar5 != null) {
                            lVar5.b(this.f3598a.f3589g.E());
                        }
                    }
                    return;
                }
                return;
            }
            if (message.what == 999) {
                if (this.f3598a.f3589g.f3567h == null) {
                    return;
                }
                for (l lVar6 : this.f3598a.f3589g.f3567h) {
                    if (lVar6 != null) {
                        lVar6.e();
                    }
                }
                return;
            }
            if (message.what == 50) {
                if (this.f3598a.f3589g.f3567h == null) {
                    return;
                }
                for (l lVar7 : this.f3598a.f3589g.f3567h) {
                    if (lVar7 != null) {
                        if (message.arg1 != 0) {
                            if (message.arg1 == 1) {
                                if (this.f3598a.f3589g.E().f3518a >= 18.0f) {
                                    lVar7.a(true);
                                }
                            }
                        }
                        lVar7.a(false);
                    }
                }
                return;
            }
            if (message.what == 65289) {
                int i4 = message.arg2;
                if (message.arg1 == 300) {
                    if (message.arg2 == 1003) {
                        i4 = 0;
                        z = true;
                    } else if (message.arg2 >= 1004) {
                        int i5 = message.arg2;
                    }
                    for (l lVar8 : this.f3598a.f3589g.f3567h) {
                        if (lVar8 != null) {
                            lVar8.a(z, i4);
                        }
                    }
                }
            }
        }
    }
}