package com.baidu.location.indoor.mapversion.a;

import com.realsil.sdk.dfu.DfuConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes.dex */
class e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ d f2645a;

    e(d dVar) {
        this.f2645a = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://loc.map.baidu.com/check_indoor_data_update").openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setReadTimeout(DfuConstants.DFU_UPLOAD_IMAGE_TIMEOUT);
                httpURLConnection.setConnectTimeout(DfuConstants.DFU_UPLOAD_IMAGE_TIMEOUT);
                StringBuilder sb = new StringBuilder();
                sb.append("&data_type=ps");
                sb.append("&bid=");
                sb.append(this.f2645a.f2643e);
                if (this.f2645a.f2644f != null) {
                    sb.append("&md5=");
                    sb.append(this.f2645a.f2644f);
                }
                httpURLConnection.getOutputStream().write(sb.toString().getBytes());
                if (httpURLConnection.getResponseCode() == 200) {
                    String headerField = httpURLConnection.getHeaderField("Hash");
                    if (headerField != null && (this.f2645a.f2644f == null || !headerField.equalsIgnoreCase(this.f2645a.f2644f))) {
                        this.f2645a.f2644f = headerField;
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        FileWriter fileWriter = new FileWriter(new File(this.f2645a.f2641c, this.f2645a.f2643e));
                        fileWriter.write(headerField + IOUtils.LINE_SEPARATOR_UNIX);
                        while (true) {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            fileWriter.write(line);
                            fileWriter.write(IOUtils.LINE_SEPARATOR_UNIX);
                        }
                        fileWriter.flush();
                        fileWriter.close();
                    }
                    this.f2645a.d();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            this.f2645a.f2642d = false;
        }
    }
}