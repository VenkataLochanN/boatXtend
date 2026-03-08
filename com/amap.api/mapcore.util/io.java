package com.amap.api.mapcore.util;

import android.content.Context;
import android.net.SSLSessionCache;
import android.os.Build;
import com.amap.api.mapcore.util.gj;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: MySSLSocketFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class io extends SSLSocketFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SSLSocketFactory f1389a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f1390b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private SSLContext f1391c;

    public io(Context context, SSLContext sSLContext) {
        if (context != null) {
            try {
                try {
                    this.f1390b = context.getApplicationContext();
                } catch (Throwable th) {
                    hn.c(th, "myssl", "<init3>");
                    return;
                }
            } catch (Throwable th2) {
                try {
                    hn.c(th2, "myssl", "<init>");
                    try {
                        if (this.f1391c == null && Build.VERSION.SDK_INT >= 9) {
                            this.f1391c = SSLContext.getDefault();
                        }
                    } catch (Throwable th3) {
                        hn.c(th3, "myssl", "<init2>");
                    }
                    if (this.f1389a == null) {
                        this.f1389a = (SSLSocketFactory) SSLSocketFactory.getDefault();
                        return;
                    }
                    return;
                } finally {
                }
            }
        }
        this.f1391c = sSLContext;
        if (sSLContext != null) {
            this.f1389a = sSLContext.getSocketFactory();
        }
        try {
            if (this.f1391c == null && Build.VERSION.SDK_INT >= 9) {
                this.f1391c = SSLContext.getDefault();
            }
        } catch (Throwable th4) {
            hn.c(th4, "myssl", "<init2>");
        }
        if (this.f1389a == null) {
            this.f1389a = (SSLSocketFactory) SSLSocketFactory.getDefault();
        }
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        try {
            if (this.f1389a != null) {
                return this.f1389a.getDefaultCipherSuites();
            }
        } catch (Throwable th) {
            hn.c(th, "myssl", "gdcs");
        }
        return new String[0];
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        try {
            if (this.f1389a != null) {
                return this.f1389a.getSupportedCipherSuites();
            }
        } catch (Throwable th) {
            hn.c(th, "myssl", "gscs");
        }
        return new String[0];
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() throws IOException {
        boolean z;
        IOException iOException;
        try {
            if (this.f1389a == null) {
                return null;
            }
            Socket socketA = a(this.f1389a.createSocket());
            b(socketA);
            return socketA;
        } finally {
            if (!z) {
            }
        }
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        boolean z2;
        IOException iOException;
        try {
            if (this.f1389a == null) {
                return null;
            }
            Socket socketA = a(this.f1389a.createSocket(socket, str, i, z));
            b(socketA);
            return socketA;
        } finally {
            if (!z2) {
            }
        }
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException {
        try {
            if (this.f1389a == null) {
                return null;
            }
            Socket socketA = a(this.f1389a.createSocket(str, i));
            b(socketA);
            return socketA;
        } catch (Throwable th) {
            hn.c(th, "myssl", "cs3");
            if (th instanceof UnknownHostException) {
                throw th;
            }
            if (th instanceof IOException) {
                throw th;
            }
            return null;
        }
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        try {
            if (this.f1389a == null) {
                return null;
            }
            Socket socketA = a(this.f1389a.createSocket(str, i, inetAddress, i2));
            b(socketA);
            return socketA;
        } catch (Throwable th) {
            hn.c(th, "myssl", "cs4");
            if (th instanceof UnknownHostException) {
                throw th;
            }
            if (th instanceof IOException) {
                throw th;
            }
            return null;
        }
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        boolean z;
        IOException iOException;
        try {
            if (this.f1389a == null) {
                return null;
            }
            Socket socketA = a(this.f1389a.createSocket(inetAddress, i));
            b(socketA);
            return socketA;
        } finally {
            if (!z) {
            }
        }
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        boolean z;
        IOException iOException;
        try {
            if (this.f1389a == null) {
                return null;
            }
            Socket socketA = a(this.f1389a.createSocket(inetAddress, i, inetAddress2, i2));
            b(socketA);
            return socketA;
        } finally {
            if (!z) {
            }
        }
    }

    private Socket a(Socket socket) {
        try {
            if (Build.VERSION.SDK_INT >= 21 && gj.f.f1087b && (socket instanceof SSLSocket)) {
                ((SSLSocket) socket).setEnabledProtocols(new String[]{"TLSv1.2"});
            }
        } catch (Throwable th) {
            hn.c(th, "myssl", "stlv2");
        }
        return socket;
    }

    private void b(Socket socket) {
        int i;
        if (Build.VERSION.SDK_INT >= 17 && gj.f.f1088c && gj.f.f1090e && (socket instanceof SSLSocket)) {
            if (gj.f.f1091f > gj.f.f1089d) {
                i = gj.f.f1089d;
            } else {
                i = gj.f.f1091f;
            }
            if (i <= 17 || Build.VERSION.SDK_INT <= i) {
                try {
                    socket.getClass().getMethod(gt.c("Cc2V0VXNlU2Vzc2lvblRpY2tldHM"), Boolean.TYPE).invoke(socket, true);
                } catch (Throwable th) {
                    hn.c(th, "myssl", "sust");
                }
            }
        }
    }

    public void a() {
        if (Build.VERSION.SDK_INT >= 17 && gj.f.f1088c && this.f1390b != null && this.f1391c != null) {
            int i = gj.f.f1089d;
            if (i <= 17 || Build.VERSION.SDK_INT <= i) {
                SSLSessionCache sSLSessionCache = new SSLSessionCache(this.f1390b);
                if (Build.VERSION.SDK_INT > 20 && Build.VERSION.SDK_INT < 28) {
                    a(sSLSessionCache);
                } else {
                    b(sSLSessionCache);
                }
            }
        }
    }

    private void a(SSLSessionCache sSLSessionCache) {
        try {
            sSLSessionCache.getClass().getMethod(gt.c("MaW5zdGFsbA"), SSLSessionCache.class, SSLContext.class).invoke(sSLSessionCache, sSLSessionCache, this.f1391c);
        } catch (Throwable th) {
            hn.c(th, "myssl", "isc1");
            b(sSLSessionCache);
        }
    }

    private void b(SSLSessionCache sSLSessionCache) {
        SSLContext sSLContext = this.f1391c;
        if (sSLContext == null) {
            return;
        }
        try {
            SSLSessionContext clientSessionContext = sSLContext.getClientSessionContext();
            Field declaredField = sSLSessionCache.getClass().getDeclaredField(gt.c("UbVNlc3Npb25DYWNoZQ"));
            declaredField.setAccessible(true);
            Object obj = declaredField.get(sSLSessionCache);
            Method[] methods = clientSessionContext.getClass().getMethods();
            String strC = gt.c("Yc2V0UGVyc2lzdGVudENhY2hl");
            for (Method method : methods) {
                if (method.getName().equals(strC)) {
                    method.invoke(clientSessionContext, obj);
                    return;
                }
            }
        } catch (Throwable th) {
            hn.c(th, "myssl", "isc2");
        }
    }
}