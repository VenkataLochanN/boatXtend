package com.tencent.bugly.crashreport.crash.anr;

import com.tencent.bugly.proguard.y;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class TraceFileHelper {

    /* JADX INFO: compiled from: BUGLY */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f5468a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f5469b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f5470c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Map<String, String[]> f5471d;
    }

    /* JADX INFO: compiled from: BUGLY */
    public interface b {
        boolean a(long j);

        boolean a(long j, long j2, String str);

        boolean a(String str, int i, String str2, String str3);
    }

    public static a readTargetDumpInfo(final String str, String str2, final boolean z) throws Throwable {
        if (str != null && str2 != null) {
            final a aVar = new a();
            readTraceFile(str2, new b() { // from class: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.1
                @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.b
                public final boolean a(String str3, int i, String str4, String str5) {
                    y.c("new thread %s", str3);
                    if (aVar.f5468a > 0 && aVar.f5470c > 0 && aVar.f5469b != null) {
                        if (aVar.f5471d == null) {
                            aVar.f5471d = new HashMap();
                        }
                        Map<String, String[]> map = aVar.f5471d;
                        StringBuilder sb = new StringBuilder();
                        sb.append(i);
                        map.put(str3, new String[]{str4, str5, sb.toString()});
                    }
                    return true;
                }

                @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.b
                public final boolean a(long j, long j2, String str3) {
                    y.c("new process %s", str3);
                    if (!str3.equals(str)) {
                        return true;
                    }
                    a aVar2 = aVar;
                    aVar2.f5468a = j;
                    aVar2.f5469b = str3;
                    aVar2.f5470c = j2;
                    return z;
                }

                @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.b
                public final boolean a(long j) {
                    y.c("process end %d", Long.valueOf(j));
                    return aVar.f5468a <= 0 || aVar.f5470c <= 0 || aVar.f5469b == null;
                }
            });
            if (aVar.f5468a > 0 && aVar.f5470c > 0 && aVar.f5469b != null) {
                return aVar;
            }
        }
        return null;
    }

    public static a readFirstDumpInfo(String str, final boolean z) throws Throwable {
        if (str == null) {
            y.e("path:%s", str);
            return null;
        }
        final a aVar = new a();
        readTraceFile(str, new b() { // from class: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.2
            @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.b
            public final boolean a(String str2, int i, String str3, String str4) {
                y.c("new thread %s", str2);
                if (aVar.f5471d == null) {
                    aVar.f5471d = new HashMap();
                }
                Map<String, String[]> map = aVar.f5471d;
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                map.put(str2, new String[]{str3, str4, sb.toString()});
                return true;
            }

            @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.b
            public final boolean a(long j, long j2, String str2) {
                y.c("new process %s", str2);
                a aVar2 = aVar;
                aVar2.f5468a = j;
                aVar2.f5469b = str2;
                aVar2.f5470c = j2;
                return z;
            }

            @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.b
            public final boolean a(long j) {
                y.c("process end %d", Long.valueOf(j));
                return false;
            }
        });
        if (aVar.f5468a > 0 && aVar.f5470c > 0 && aVar.f5469b != null) {
            return aVar;
        }
        y.e("first dump error %s", aVar.f5468a + " " + aVar.f5470c + " " + aVar.f5469b);
        return null;
    }

    public static void readTraceFile(String str, b bVar) throws Throwable {
        Throwable th;
        BufferedReader bufferedReader;
        if (str == null || bVar == null) {
            return;
        }
        File file = new File(str);
        if (!file.exists()) {
            return;
        }
        file.lastModified();
        file.length();
        BufferedReader bufferedReader2 = null;
        int i = 0;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                try {
                    Pattern patternCompile = Pattern.compile("-{5}\\spid\\s\\d+\\sat\\s\\d+-\\d+-\\d+\\s\\d{2}:\\d{2}:\\d{2}\\s-{5}");
                    Pattern patternCompile2 = Pattern.compile("-{5}\\send\\s\\d+\\s-{5}");
                    Pattern patternCompile3 = Pattern.compile("Cmd\\sline:\\s(\\S+)");
                    Pattern patternCompile4 = Pattern.compile("\".+\"\\s(daemon\\s){0,1}prio=\\d+\\stid=\\d+\\s.*");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                    while (true) {
                        Pattern[] patternArr = new Pattern[1];
                        patternArr[i] = patternCompile;
                        Object[] objArrA = a(bufferedReader, patternArr);
                        if (objArrA == null) {
                            try {
                                bufferedReader.close();
                                return;
                            } catch (IOException e2) {
                                if (y.a(e2)) {
                                    return;
                                }
                                e2.printStackTrace();
                                return;
                            }
                        }
                        Pattern[] patternArr2 = new Pattern[1];
                        patternArr2[i] = patternCompile3;
                        Object[] objArrA2 = a(bufferedReader, patternArr2);
                        if (objArrA2 == null) {
                            y.d("Failed to find process name.", new Object[i]);
                            try {
                                bufferedReader.close();
                                return;
                            } catch (IOException e3) {
                                if (y.a(e3)) {
                                    return;
                                }
                                e3.printStackTrace();
                                return;
                            }
                        }
                        String[] strArrSplit = objArrA[1].toString().split("\\s");
                        long j = Long.parseLong(strArrSplit[2]);
                        long time = simpleDateFormat.parse(strArrSplit[4] + " " + strArrSplit[5]).getTime();
                        Matcher matcher = patternCompile3.matcher(objArrA2[1].toString());
                        matcher.find();
                        matcher.group(1);
                        SimpleDateFormat simpleDateFormat2 = simpleDateFormat;
                        if (!bVar.a(j, time, matcher.group(1))) {
                            try {
                                bufferedReader.close();
                                return;
                            } catch (IOException e4) {
                                if (y.a(e4)) {
                                    return;
                                }
                                e4.printStackTrace();
                                return;
                            }
                        }
                        while (true) {
                            Object[] objArrA3 = a(bufferedReader, patternCompile4, patternCompile2);
                            if (objArrA3 == null) {
                                break;
                            }
                            if (objArrA3[0] == patternCompile4) {
                                String string = objArrA3[1].toString();
                                Matcher matcher2 = Pattern.compile("\".+\"").matcher(string);
                                matcher2.find();
                                String strGroup = matcher2.group();
                                String strSubstring = strGroup.substring(1, strGroup.length() - 1);
                                string.contains("NATIVE");
                                Matcher matcher3 = Pattern.compile("tid=\\d+").matcher(string);
                                matcher3.find();
                                String strGroup2 = matcher3.group();
                                bVar.a(strSubstring, Integer.parseInt(strGroup2.substring(strGroup2.indexOf("=") + 1)), a(bufferedReader), b(bufferedReader));
                            } else if (!bVar.a(Long.parseLong(objArrA3[1].toString().split("\\s")[2]))) {
                                try {
                                    bufferedReader.close();
                                    return;
                                } catch (IOException e5) {
                                    if (y.a(e5)) {
                                        return;
                                    }
                                    e5.printStackTrace();
                                    return;
                                }
                            }
                        }
                        simpleDateFormat = simpleDateFormat2;
                        i = 0;
                    }
                } catch (Exception e6) {
                    e = e6;
                    bufferedReader2 = bufferedReader;
                    if (!y.a(e)) {
                        e.printStackTrace();
                    }
                    y.d("trace open fail:%s : %s", e.getClass().getName(), e.getMessage());
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e7) {
                            if (y.a(e7)) {
                                return;
                            }
                            e7.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader == null) {
                        throw th;
                    }
                    try {
                        bufferedReader.close();
                        throw th;
                    } catch (IOException e8) {
                        if (y.a(e8)) {
                            throw th;
                        }
                        e8.printStackTrace();
                        throw th;
                    }
                }
            } catch (Exception e9) {
                e = e9;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
        }
    }

    private static Object[] a(BufferedReader bufferedReader, Pattern... patternArr) throws IOException {
        if (bufferedReader != null && patternArr != null) {
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                for (Pattern pattern : patternArr) {
                    if (pattern.matcher(line).matches()) {
                        return new Object[]{pattern, line};
                    }
                }
            }
        }
        return null;
    }

    private static String a(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            String line = bufferedReader.readLine();
            if (line == null) {
                return null;
            }
            stringBuffer.append(line + IOUtils.LINE_SEPARATOR_UNIX);
        }
        return stringBuffer.toString();
    }

    private static String b(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null || line.trim().length() <= 0) {
                break;
            }
            stringBuffer.append(line + IOUtils.LINE_SEPARATOR_UNIX);
        }
        return stringBuffer.toString();
    }
}