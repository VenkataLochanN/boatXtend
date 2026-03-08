package com.realsil.sdk.core.utility;

import com.bumptech.glide.load.Key;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes3.dex */
public class StreamUtils {
    public static String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(inputStream, Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            inputStreamReader = null;
        }
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder sb = new StringBuilder("");
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        return sb.toString();
    }
}