package com.loc;

import android.content.Context;
import android.net.SSLSessionCache;
import android.os.Build;
import com.loc.l;
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
/* JADX INFO: loaded from: classes3.dex */
public final class au extends SSLSocketFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SSLSocketFactory f4803a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f4804b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private SSLContext f4805c;

    public au(Context context, SSLContext sSLContext) {
        if (context != null) {
            try {
                this.f4804b = context.getApplicationContext();
            } catch (Throwable th) {
                try {
                    ab.b(th, "myssl", "<init>");
                    try {
                        if (this.f4805c == null && Build.VERSION.SDK_INT >= 9) {
                            this.f4805c = SSLContext.getDefault();
                        }
                    } catch (Throwable th2) {
                        ab.b(th2, "myssl", "<init2>");
                    }
                    try {
                        if (this.f4803a == null) {
                            this.f4803a = (SSLSocketFactory) SSLSocketFactory.getDefault();
                            return;
                        }
                        return;
                    } catch (Throwable th3) {
                        ab.b(th3, "myssl", "<init3>");
                        return;
                    }
                } finally {
                }
            }
        }
        this.f4805c = sSLContext;
        if (sSLContext != null) {
            this.f4803a = sSLContext.getSocketFactory();
        }
        try {
            if (this.f4805c == null && Build.VERSION.SDK_INT >= 9) {
                this.f4805c = SSLContext.getDefault();
            }
        } catch (Throwable th4) {
            ab.b(th4, "myssl", "<init2>");
        }
        try {
            if (this.f4803a == null) {
                this.f4803a = (SSLSocketFactory) SSLSocketFactory.getDefault();
            }
        } catch (Throwable th5) {
            ab.b(th5, "myssl", "<init3>");
        }
    }

    private static Socket a(Socket socket) {
        try {
            if (Build.VERSION.SDK_INT >= 21 && l.f.f5278b && (socket instanceof SSLSocket)) {
                ((SSLSocket) socket).setEnabledProtocols(new String[]{"TLSv1.2"});
            }
        } catch (Throwable th) {
            ab.b(th, "myssl", "stlv2");
        }
        return socket;
    }

    private void a(SSLSessionCache sSLSessionCache) {
        SSLContext sSLContext = this.f4805c;
        if (sSLContext == null) {
            return;
        }
        try {
            SSLSessionContext clientSessionContext = sSLContext.getClientSessionContext();
            Field declaredField = sSLSessionCache.getClass().getDeclaredField(u.c("UbVNlc3Npb25DYWNoZQ"));
            declaredField.setAccessible(true);
            Object obj = declaredField.get(sSLSessionCache);
            Method[] methods = clientSessionContext.getClass().getMethods();
            String strC = u.c("Yc2V0UGVyc2lzdGVudENhY2hl");
            for (Method method : methods) {
                if (method.getName().equals(strC)) {
                    method.invoke(clientSessionContext, obj);
                    return;
                }
            }
        } catch (Throwable th) {
            ab.b(th, "myssl", "isc2");
        }
    }

    private static void b(Socket socket) {
        if (Build.VERSION.SDK_INT >= 17 && l.f.f5279c && l.f.f5281e && (socket instanceof SSLSocket)) {
            int i = l.f.f5282f > l.f.f5280d ? l.f.f5280d : l.f.f5282f;
            if (i <= 17 || Build.VERSION.SDK_INT <= i) {
                try {
                    socket.getClass().getMethod(u.c("Cc2V0VXNlU2Vzc2lvblRpY2tldHM"), Boolean.TYPE).invoke(socket, true);
                } catch (Throwable th) {
                    ab.b(th, "myssl", "sust");
                }
            }
        }
    }

    public final void a() {
        if (Build.VERSION.SDK_INT >= 17 && l.f.f5279c && this.f4804b != null && this.f4805c != null) {
            int i = l.f.f5280d;
            if (i <= 17 || Build.VERSION.SDK_INT <= i) {
                SSLSessionCache sSLSessionCache = new SSLSessionCache(this.f4804b);
                if (Build.VERSION.SDK_INT <= 20 || Build.VERSION.SDK_INT >= 28) {
                    a(sSLSessionCache);
                    return;
                }
                try {
                    sSLSessionCache.getClass().getMethod(u.c("MaW5zdGFsbA"), SSLSessionCache.class, SSLContext.class).invoke(sSLSessionCache, sSLSessionCache, this.f4805c);
                } catch (Throwable th) {
                    ab.b(th, "myssl", "isc1");
                    a(sSLSessionCache);
                }
            }
        }
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket() throws IOException {
        boolean z;
        IOException iOException;
        try {
            if (this.f4803a == null) {
                return null;
            }
            Socket socketA = a(this.f4803a.createSocket());
            b(socketA);
            return socketA;
        } finally {
            if (!z) {
            }
        }
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int i) throws IOException {
        try {
            if (this.f4803a == null) {
                return null;
            }
            Socket socketA = a(this.f4803a.createSocket(str, i));
            b(socketA);
            return socketA;
        } catch (Throwable th) {
            ab.b(th, "myssl", "cs3");
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
    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        try {
            if (this.f4803a == null) {
                return null;
            }
            Socket socketA = a(this.f4803a.createSocket(str, i, inetAddress, i2));
            b(socketA);
            return socketA;
        } catch (Throwable th) {
            ab.b(th, "myssl", "cs4");
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
    public final Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        boolean z;
        IOException iOException;
        try {
            if (this.f4803a == null) {
                return null;
            }
            Socket socketA = a(this.f4803a.createSocket(inetAddress, i));
            b(socketA);
            return socketA;
        } finally {
            if (!z) {
            }
        }
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        boolean z;
        IOException iOException;
        try {
            if (this.f4803a == null) {
                return null;
            }
            Socket socketA = a(this.f4803a.createSocket(inetAddress, i, inetAddress2, i2));
            b(socketA);
            return socketA;
        } finally {
            if (!z) {
            }
        }
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        boolean z2;
        IOException iOException;
        try {
            if (this.f4803a == null) {
                return null;
            }
            Socket socketA = a(this.f4803a.createSocket(socket, str, i, z));
            b(socketA);
            return socketA;
        } finally {
            if (!z2) {
            }
        }
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getDefaultCipherSuites() {
        try {
            if (this.f4803a != null) {
                return this.f4803a.getDefaultCipherSuites();
            }
        } catch (Throwable th) {
            ab.b(th, "myssl", "gdcs");
        }
        return new String[0];
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getSupportedCipherSuites() {
        try {
            if (this.f4803a != null) {
                return this.f4803a.getSupportedCipherSuites();
            }
        } catch (Throwable th) {
            ab.b(th, "myssl", "gscs");
        }
        return new String[0];
    }
}