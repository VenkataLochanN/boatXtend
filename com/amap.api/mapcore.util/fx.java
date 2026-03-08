package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: PluginContext.java */
/* JADX INFO: loaded from: classes.dex */
public class fx extends ContextThemeWrapper {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String[] f984d = {"android.widget", "android.webkit", "android.app"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Resources f985a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private LayoutInflater f986b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ClassLoader f987c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private a f988e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private LayoutInflater.Factory f989f;

    public fx(Context context, int i, ClassLoader classLoader) {
        super(context, i);
        this.f988e = new a();
        this.f989f = new LayoutInflater.Factory() { // from class: com.amap.api.mapcore.util.fx.1
            @Override // android.view.LayoutInflater.Factory
            public View onCreateView(String str, Context context2, AttributeSet attributeSet) {
                return fx.this.a(str, context2, attributeSet);
            }
        };
        this.f985a = fy.a();
        this.f987c = classLoader;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources resources = this.f985a;
        return resources != null ? resources : super.getResources();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if ("layout_inflater".equals(str)) {
            if (this.f986b == null) {
                LayoutInflater layoutInflater = (LayoutInflater) super.getSystemService(str);
                if (layoutInflater != null) {
                    this.f986b = layoutInflater.cloneInContext(this);
                }
                this.f986b.setFactory(this.f989f);
                this.f986b = this.f986b.cloneInContext(this);
            }
            return this.f986b;
        }
        return super.getSystemService(str);
    }

    /* JADX INFO: compiled from: PluginContext.java */
    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public HashSet<String> f991a = new HashSet<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public HashMap<String, Constructor<?>> f992b = new HashMap<>();

        public a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0066 A[PHI: r5
  0x0066: PHI (r5v1 java.lang.Class<?>) = 
  (r5v0 java.lang.Class<?>)
  (r5v14 java.lang.Class<?>)
  (r5v14 java.lang.Class<?>)
  (r5v14 java.lang.Class<?>)
  (r5v14 java.lang.Class<?>)
 binds: [B:26:0x0065, B:17:0x0051, B:38:0x0066, B:23:0x005f, B:21:0x0058] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View a(java.lang.String r12, android.content.Context r13, android.util.AttributeSet r14) {
        /*
            r11 = this;
            com.amap.api.mapcore.util.fx$a r0 = r11.f988e
            java.util.HashSet<java.lang.String> r0 = r0.f991a
            boolean r0 = r0.contains(r12)
            r1 = 0
            if (r0 == 0) goto Lc
            return r1
        Lc:
            com.amap.api.mapcore.util.fx$a r0 = r11.f988e
            java.util.HashMap<java.lang.String, java.lang.reflect.Constructor<?>> r0 = r0.f992b
            java.lang.Object r0 = r0.get(r12)
            java.lang.reflect.Constructor r0 = (java.lang.reflect.Constructor) r0
            r2 = 2
            r3 = 1
            r4 = 0
            if (r0 != 0) goto L87
            java.lang.String r5 = "api.navi"
            boolean r5 = r12.contains(r5)     // Catch: java.lang.Throwable -> L65
            if (r5 == 0) goto L2a
            java.lang.ClassLoader r5 = r11.f987c     // Catch: java.lang.Throwable -> L65
            java.lang.Class r5 = r5.loadClass(r12)     // Catch: java.lang.Throwable -> L65
            goto L51
        L2a:
            java.lang.String[] r5 = com.amap.api.mapcore.util.fx.f984d     // Catch: java.lang.Throwable -> L65
            int r6 = r5.length     // Catch: java.lang.Throwable -> L65
            r7 = r4
        L2e:
            if (r7 >= r6) goto L50
            r8 = r5[r7]     // Catch: java.lang.Throwable -> L65
            java.lang.ClassLoader r9 = r11.f987c     // Catch: java.lang.Throwable -> L4d
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4d
            r10.<init>()     // Catch: java.lang.Throwable -> L4d
            r10.append(r8)     // Catch: java.lang.Throwable -> L4d
            java.lang.String r8 = "."
            r10.append(r8)     // Catch: java.lang.Throwable -> L4d
            r10.append(r12)     // Catch: java.lang.Throwable -> L4d
            java.lang.String r8 = r10.toString()     // Catch: java.lang.Throwable -> L4d
            java.lang.Class r5 = r9.loadClass(r8)     // Catch: java.lang.Throwable -> L4d
            goto L51
        L4d:
            int r7 = r7 + 1
            goto L2e
        L50:
            r5 = r1
        L51:
            if (r5 != 0) goto L54
            goto L61
        L54:
            java.lang.Class<android.view.ViewStub> r6 = android.view.ViewStub.class
            if (r5 != r6) goto L59
            goto L61
        L59:
            java.lang.ClassLoader r6 = r5.getClassLoader()     // Catch: java.lang.Throwable -> L66
            java.lang.ClassLoader r7 = r11.f987c     // Catch: java.lang.Throwable -> L66
            if (r6 == r7) goto L62
        L61:
            goto L66
        L62:
            r6 = r5
            r5 = r3
            goto L68
        L65:
            r5 = r1
        L66:
            r6 = r5
            r5 = r4
        L68:
            if (r5 != 0) goto L72
            com.amap.api.mapcore.util.fx$a r13 = r11.f988e
            java.util.HashSet<java.lang.String> r13 = r13.f991a
            r13.add(r12)
            return r1
        L72:
            java.lang.Class[] r5 = new java.lang.Class[r2]     // Catch: java.lang.Throwable -> L87
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r5[r4] = r7     // Catch: java.lang.Throwable -> L87
            java.lang.Class<android.util.AttributeSet> r7 = android.util.AttributeSet.class
            r5[r3] = r7     // Catch: java.lang.Throwable -> L87
            java.lang.reflect.Constructor r0 = r6.getConstructor(r5)     // Catch: java.lang.Throwable -> L87
            com.amap.api.mapcore.util.fx$a r5 = r11.f988e     // Catch: java.lang.Throwable -> L87
            java.util.HashMap<java.lang.String, java.lang.reflect.Constructor<?>> r5 = r5.f992b     // Catch: java.lang.Throwable -> L87
            r5.put(r12, r0)     // Catch: java.lang.Throwable -> L87
        L87:
            if (r0 == 0) goto L97
            java.lang.Object[] r12 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L96
            r12[r4] = r13     // Catch: java.lang.Throwable -> L96
            r12[r3] = r14     // Catch: java.lang.Throwable -> L96
            java.lang.Object r12 = r0.newInstance(r12)     // Catch: java.lang.Throwable -> L96
            android.view.View r12 = (android.view.View) r12     // Catch: java.lang.Throwable -> L96
            goto L98
        L96:
            return r1
        L97:
            r12 = r1
        L98:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.fx.a(java.lang.String, android.content.Context, android.util.AttributeSet):android.view.View");
    }
}