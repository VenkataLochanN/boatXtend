package com.loc;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.loc.ao;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: compiled from: LogEngine.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ay {
    public static int a(ax axVar) {
        ao aoVarA;
        ao aoVar = null;
        try {
            try {
                if (axVar.f4819f.c()) {
                    axVar.f4819f.a(true);
                    aoVarA = ao.a(new File(axVar.f4814a), axVar.f4815b);
                    try {
                        ArrayList arrayList = new ArrayList();
                        byte[] bArrA = a(aoVarA, axVar, arrayList);
                        if (bArrA != null && bArrA.length != 0) {
                            aa aaVar = new aa(bArrA, axVar.f4816c);
                            aq.a();
                            JSONObject jSONObject = new JSONObject(new String(aq.b(aaVar)));
                            if (jSONObject.has(AuthorizationResponseParser.CODE) && jSONObject.getInt(AuthorizationResponseParser.CODE) == 1) {
                                if (axVar.f4819f != null && bArrA != null) {
                                    axVar.f4819f.a(bArrA.length);
                                }
                                if (axVar.f4819f.b() < Integer.MAX_VALUE) {
                                    a(aoVarA, arrayList);
                                } else {
                                    try {
                                        aoVarA.d();
                                    } catch (Throwable th) {
                                        ab.b(th, "ofm", "dlo");
                                    }
                                }
                                return bArrA.length;
                            }
                            aoVar = aoVarA;
                        }
                        return -1;
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            ab.b(th, "leg", "uts");
                            if (aoVarA != null) {
                                aoVarA.close();
                            }
                            return -1;
                        } finally {
                            if (aoVarA != null) {
                                try {
                                    aoVarA.close();
                                } catch (Throwable th3) {
                                    th3.printStackTrace();
                                }
                            }
                        }
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                aoVarA = null;
            }
            if (aoVar != null) {
                aoVar.close();
            }
        } catch (Throwable th5) {
            th5.printStackTrace();
        }
        return -1;
    }

    private static void a(ao aoVar, List<String> list) {
        if (aoVar != null) {
            try {
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    aoVar.c(it.next());
                }
                aoVar.close();
            } catch (Throwable th) {
                ab.b(th, "ofm", "dlo");
            }
        }
    }

    public static void a(String str, byte[] bArr, ax axVar) throws Throwable {
        ao aoVarA;
        OutputStream outputStreamA = null;
        try {
            if (a(axVar.f4814a, str)) {
                return;
            }
            File file = new File(axVar.f4814a);
            if (!file.exists()) {
                file.mkdirs();
            }
            aoVarA = ao.a(file, axVar.f4815b);
            try {
                aoVarA.a(axVar.f4817d);
                byte[] bArrB = axVar.f4818e.b(bArr);
                ao.a aVarB = aoVarA.b(str);
                outputStreamA = aVarB.a();
                outputStreamA.write(bArrB);
                aVarB.b();
                aoVarA.c();
                if (outputStreamA != null) {
                    try {
                        outputStreamA.close();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                if (aoVarA != null) {
                    try {
                        aoVarA.close();
                        return;
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                        return;
                    }
                }
                return;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            aoVarA = null;
        }
        if (outputStreamA != null) {
            try {
                outputStreamA.close();
            } catch (Throwable th5) {
                th5.printStackTrace();
            }
        }
        if (aoVarA == null) {
            throw th;
        }
        try {
            aoVarA.close();
            throw th;
        } catch (Throwable th6) {
            th6.printStackTrace();
            throw th;
        }
    }

    private static boolean a(String str, String str2) {
        try {
            return new File(str, str2 + ".0").exists();
        } catch (Throwable th) {
            ab.b(th, "leg", "fet");
            return false;
        }
    }

    private static byte[] a(ao aoVar, ax axVar, List<String> list) {
        try {
            File fileB = aoVar.b();
            if (fileB != null && fileB.exists()) {
                int length = 0;
                for (String str : fileB.list()) {
                    if (str.contains(".0")) {
                        String str2 = str.split("\\.")[0];
                        byte[] bArrA = bd.a(aoVar, str2);
                        length += bArrA.length;
                        list.add(str2);
                        if (length > axVar.f4819f.b()) {
                            break;
                        }
                        axVar.f4820g.b(bArrA);
                    }
                }
                if (length <= 0) {
                    return null;
                }
                return axVar.f4820g.a();
            }
        } catch (Throwable th) {
            ab.b(th, "leg", "gCo");
        }
        return new byte[0];
    }
}