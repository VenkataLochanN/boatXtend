package com.amap.api.mapcore.util;

import com.amap.api.maps.MapsInitializer;
import java.util.Map;

/* JADX INFO: compiled from: AbstractAMapRequest.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class dp extends iq {
    protected boolean isPostFlag = true;

    @Override // com.amap.api.mapcore.util.iq
    public Map<String, String> getParams() {
        return null;
    }

    protected byte[] makeHttpRequest() throws gh {
        int protocol = MapsInitializer.getProtocol();
        ip ipVarA = ip.a(false);
        if (protocol == 1) {
            if (this.isPostFlag) {
                return ipVarA.b(this);
            }
            return ipVarA.f(this);
        }
        if (protocol != 2) {
            return null;
        }
        if (this.isPostFlag) {
            return ipVarA.a(this);
        }
        return ipVarA.g(this);
    }

    protected byte[] makeHttpRequestWithInterrupted() throws gh {
        int protocol = MapsInitializer.getProtocol();
        ip ipVarA = ip.a(false);
        if (protocol == 1) {
            if (this.isPostFlag) {
                return ipVarA.d(this);
            }
            return ipVarA.h(this);
        }
        if (protocol != 2) {
            return null;
        }
        if (this.isPostFlag) {
            return ipVarA.e(this);
        }
        return ipVarA.i(this);
    }
}