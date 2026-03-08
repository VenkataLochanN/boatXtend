package com.realsil.sdk.core.impl;

import android.net.Uri;
import com.ido.life.constants.Constants;

/* JADX INFO: loaded from: classes3.dex */
public class UriImpl {
    public static String toSafeString(Uri uri) {
        String scheme = uri.getScheme();
        String schemeSpecificPart = uri.getSchemeSpecificPart();
        if (scheme != null) {
            if (scheme.equalsIgnoreCase("tel") || scheme.equalsIgnoreCase("sip") || scheme.equalsIgnoreCase(Constants.AppPackage.SMS) || scheme.equalsIgnoreCase("smsto") || scheme.equalsIgnoreCase("mailto") || scheme.equalsIgnoreCase("nfc")) {
                StringBuilder sb = new StringBuilder(64);
                sb.append(scheme);
                sb.append(':');
                if (schemeSpecificPart != null) {
                    for (int i = 0; i < schemeSpecificPart.length(); i++) {
                        char cCharAt = schemeSpecificPart.charAt(i);
                        if (cCharAt != '-' && cCharAt != '@' && cCharAt != '.') {
                            cCharAt = 'x';
                        }
                        sb.append(cCharAt);
                    }
                }
                return sb.toString();
            }
            if (scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("https") || scheme.equalsIgnoreCase("ftp")) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("//");
                sb2.append(uri.getHost() != null ? uri.getHost() : "");
                sb2.append(uri.getPort() != -1 ? ":" + uri.getPort() : "");
                sb2.append("/...");
                schemeSpecificPart = sb2.toString();
            }
        }
        StringBuilder sb3 = new StringBuilder(64);
        if (scheme != null) {
            sb3.append(scheme);
            sb3.append(':');
        }
        if (schemeSpecificPart != null) {
            sb3.append(schemeSpecificPart);
        }
        return sb3.toString();
    }
}