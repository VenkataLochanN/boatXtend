package com.ido.alexa.net;

import com.google.gson.Gson;
import com.ido.alexa.data.Directive;
import com.ido.alexa.log.AlexaLogUtil;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class ApiParser {
    private static final String HOME_TOKEN = "HomeAutomation";
    private static final Pattern PATTERN = Pattern.compile("<(.*?)>");
    private static final String TAG = "Alexa_ApiParser";

    /* JADX WARN: Removed duplicated region for block: B:91:0x01f2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x011f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.ido.alexa.data.AvsItem> parse(java.io.InputStream r12, java.lang.String r13) {
        /*
            Method dump skipped, instruction units count: 560
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.alexa.net.ApiParser.parse(java.io.InputStream, java.lang.String):java.util.List");
    }

    private static byte[] readMp3(String str) {
        AlexaLogUtil.printAndSave("alexa readMp3 pathUrl=" + str);
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String parseCaption(com.ido.alexa.data.Directive r6) {
        /*
            Method dump skipped, instruction units count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.alexa.net.ApiParser.parseCaption(com.ido.alexa.data.Directive):java.lang.String");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.ido.alexa.data.AvsItem parseDirective(com.ido.alexa.data.Directive r4, java.util.HashMap<java.lang.String, java.io.ByteArrayInputStream> r5) {
        /*
            Method dump skipped, instruction units count: 902
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.alexa.net.ApiParser.parseDirective(com.ido.alexa.data.Directive, java.util.HashMap):com.ido.alexa.data.AvsItem");
    }

    public static Directive getDirective(String str) {
        try {
            Gson gson = new Gson();
            Directive.DirectiveWrapper directiveWrapper = (Directive.DirectiveWrapper) gson.fromJson(str, Directive.DirectiveWrapper.class);
            if (directiveWrapper == null) {
                return null;
            }
            if (directiveWrapper.getDirective() == null) {
                return (Directive) gson.fromJson(str, Directive.class);
            }
            return directiveWrapper.getDirective();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String getCID(String str) throws IOException {
        String line;
        BufferedReader bufferedReader = new BufferedReader(new StringReader(str));
        do {
            line = bufferedReader.readLine();
            if (line == null) {
                return null;
            }
        } while (!line.startsWith("Content-ID:"));
        return line.substring(11).trim();
    }

    private static boolean isJson(String str) {
        return str.contains("application/json");
    }
}