package com.baidu.location.e;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.util.List;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long f2425a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static i f2426b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private WifiManager f2427c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private a f2428d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private h f2429e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private long f2430f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private long f2431g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f2432h = false;
    private Handler i = new Handler();
    private boolean j = false;
    private long k = 0;
    private long l = 0;

    /* JADX INFO: Access modifiers changed from: private */
    class a extends BroadcastReceiver {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private long f2434b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2435c;

        private a() {
            this.f2434b = 0L;
            this.f2435c = false;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null) {
                return;
            }
            String action = intent.getAction();
            if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                i.f2425a = System.currentTimeMillis() / 1000;
                i.this.i.post(new j(this, intent.getBooleanExtra("resultsUpdated", true)));
            } else if (action.equals("android.net.wifi.STATE_CHANGE") && ((NetworkInfo) intent.getParcelableExtra("networkInfo")).getState().equals(NetworkInfo.State.CONNECTED) && System.currentTimeMillis() - this.f2434b >= BootloaderScanner.TIMEOUT) {
                this.f2434b = System.currentTimeMillis();
                if (this.f2435c) {
                    return;
                }
                this.f2435c = true;
            }
        }
    }

    private i() {
    }

    public static synchronized i a() {
        if (f2426b == null) {
            f2426b = new i();
        }
        return f2426b;
    }

    private String a(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf((int) (j & 255)));
        stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
        stringBuffer.append(String.valueOf((int) ((j >> 8) & 255)));
        stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
        stringBuffer.append(String.valueOf((int) ((j >> 16) & 255)));
        stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
        stringBuffer.append(String.valueOf((int) ((j >> 24) & 255)));
        return stringBuffer.toString();
    }

    public static boolean a(h hVar, h hVar2) {
        boolean zA = a(hVar, hVar2, 0.7f);
        long jCurrentTimeMillis = System.currentTimeMillis() - com.baidu.location.a.a.f2046c;
        if (jCurrentTimeMillis <= 0 || jCurrentTimeMillis >= 30000 || !zA || hVar2.h() - hVar.h() <= 30) {
            return zA;
        }
        return false;
    }

    public static boolean a(h hVar, h hVar2, float f2) {
        if (hVar != null && hVar2 != null) {
            List<ScanResult> list = hVar.f2420a;
            List<ScanResult> list2 = hVar2.f2420a;
            if (list == list2) {
                return true;
            }
            if (list != null && list2 != null) {
                int size = list.size();
                int size2 = list2.size();
                float f3 = size + size2;
                if (size == 0 && size2 == 0) {
                    return true;
                }
                if (size != 0 && size2 != 0) {
                    int i = 0;
                    for (int i2 = 0; i2 < size; i2++) {
                        String str = list.get(i2) != null ? list.get(i2).BSSID : null;
                        if (str != null) {
                            int i3 = 0;
                            while (true) {
                                if (i3 >= size2) {
                                    break;
                                }
                                String str2 = list2.get(i3) != null ? list2.get(i3).BSSID : null;
                                if (str2 != null && str.equals(str2)) {
                                    i++;
                                    break;
                                }
                                i3++;
                            }
                        }
                    }
                    if (i * 2 >= f3 * f2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean j() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) com.baidu.location.f.getServiceContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.getType() == 1;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        WifiManager wifiManager = this.f2427c;
        if (wifiManager == null) {
            return;
        }
        try {
            List<ScanResult> scanResults = wifiManager.getScanResults();
            if (scanResults != null) {
                h hVar = new h(scanResults, System.currentTimeMillis());
                h hVar2 = this.f2429e;
                if (hVar2 == null || !hVar.a(hVar2)) {
                    this.f2429e = hVar;
                }
            }
        } catch (Exception unused) {
        }
    }

    public void b() {
        this.k = 0L;
    }

    public synchronized void c() {
        if (this.f2432h) {
            return;
        }
        if (com.baidu.location.f.isServing) {
            this.f2427c = (WifiManager) com.baidu.location.f.getServiceContext().getApplicationContext().getSystemService("wifi");
            this.f2428d = new a();
            try {
                com.baidu.location.f.getServiceContext().registerReceiver(this.f2428d, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
            } catch (Exception unused) {
            }
            this.f2432h = true;
        }
    }

    public List<WifiConfiguration> d() {
        try {
            if (this.f2427c != null) {
                return this.f2427c.getConfiguredNetworks();
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public synchronized void e() {
        if (this.f2432h) {
            try {
                com.baidu.location.f.getServiceContext().unregisterReceiver(this.f2428d);
                f2425a = 0L;
            } catch (Exception unused) {
            }
            this.f2428d = null;
            this.f2427c = null;
            this.f2432h = false;
        }
    }

    public boolean f() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.f2431g;
        if (jCurrentTimeMillis - j > 0 && jCurrentTimeMillis - j <= BootloaderScanner.TIMEOUT) {
            return false;
        }
        this.f2431g = jCurrentTimeMillis;
        b();
        return g();
    }

    public boolean g() {
        if (this.f2427c == null) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.f2430f;
        if (jCurrentTimeMillis - j > 0) {
            long j2 = jCurrentTimeMillis - j;
            long j3 = this.k;
            if (j2 <= j3 + BootloaderScanner.TIMEOUT || jCurrentTimeMillis - (f2425a * 1000) <= j3 + BootloaderScanner.TIMEOUT) {
                return false;
            }
            if (j() && jCurrentTimeMillis - this.f2430f <= this.k + DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
                return false;
            }
        }
        return i();
    }

    public String h() {
        WifiManager wifiManager = this.f2427c;
        if (wifiManager == null) {
            return "";
        }
        try {
            if (!wifiManager.isWifiEnabled()) {
                if (Build.VERSION.SDK_INT <= 17) {
                    return "";
                }
                if (!this.f2427c.isScanAlwaysAvailable()) {
                    return "";
                }
            }
            return "&wifio=1";
        } catch (Exception | NoSuchMethodError unused) {
            return "";
        }
    }

    public boolean i() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.l;
        if (jCurrentTimeMillis >= 0 && jCurrentTimeMillis <= 2000) {
            return false;
        }
        this.l = System.currentTimeMillis();
        try {
            if (!this.f2427c.isWifiEnabled() && (Build.VERSION.SDK_INT <= 17 || !this.f2427c.isScanAlwaysAvailable())) {
                return false;
            }
            this.f2427c.startScan();
            this.f2430f = System.currentTimeMillis();
            return true;
        } catch (Exception | NoSuchMethodError unused) {
            return false;
        }
    }

    public boolean k() {
        try {
            if ((this.f2427c.isWifiEnabled() || (Build.VERSION.SDK_INT > 17 && this.f2427c.isScanAlwaysAvailable())) && !j()) {
                return new h(this.f2427c.getScanResults(), 0L).e();
            }
            return false;
        } catch (Exception | NoSuchMethodError unused) {
            return false;
        }
    }

    public WifiInfo l() {
        WifiManager wifiManager = this.f2427c;
        if (wifiManager == null) {
            return null;
        }
        try {
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null && connectionInfo.getBSSID() != null && connectionInfo.getRssi() > -100) {
                String bssid = connectionInfo.getBSSID();
                if (bssid != null) {
                    String strReplace = bssid.replace(":", "");
                    if (!"000000000000".equals(strReplace)) {
                        if ("".equals(strReplace)) {
                        }
                    }
                    return null;
                }
                return connectionInfo;
            }
        } catch (Error | Exception unused) {
        }
        return null;
    }

    public String m() {
        StringBuffer stringBuffer = new StringBuffer();
        WifiInfo wifiInfoL = a().l();
        if (wifiInfoL != null && wifiInfoL.getBSSID() != null) {
            String strReplace = wifiInfoL.getBSSID().replace(":", "");
            int rssi = wifiInfoL.getRssi();
            String strN = a().n();
            if (rssi < 0) {
                rssi = -rssi;
            }
            if (strReplace != null && rssi < 100) {
                stringBuffer.append("&wf=");
                stringBuffer.append(strReplace);
                stringBuffer.append(";");
                stringBuffer.append("" + rssi + ";");
                String ssid = wifiInfoL.getSSID();
                if (ssid != null && (ssid.contains("&") || ssid.contains(";"))) {
                    ssid = ssid.replace("&", "_");
                }
                stringBuffer.append(ssid);
                stringBuffer.append("&wf_n=1");
                if (strN != null) {
                    stringBuffer.append("&wf_gw=");
                    stringBuffer.append(strN);
                }
                return stringBuffer.toString();
            }
        }
        return null;
    }

    public String n() {
        DhcpInfo dhcpInfo;
        WifiManager wifiManager = this.f2427c;
        if (wifiManager == null || (dhcpInfo = wifiManager.getDhcpInfo()) == null) {
            return null;
        }
        return a(dhcpInfo.gateway);
    }

    public h o() {
        h hVar = this.f2429e;
        return (hVar == null || !hVar.k()) ? q() : this.f2429e;
    }

    public h p() {
        h hVar = this.f2429e;
        return (hVar == null || !hVar.l()) ? q() : this.f2429e;
    }

    public h q() {
        WifiManager wifiManager = this.f2427c;
        if (wifiManager != null) {
            try {
                return new h(wifiManager.getScanResults(), this.f2430f);
            } catch (Exception unused) {
            }
        }
        return new h(null, 0L);
    }

    public boolean r() {
        try {
            if (!this.f2427c.isWifiEnabled()) {
                if (Build.VERSION.SDK_INT > 17) {
                    if (this.f2427c.isScanAlwaysAvailable()) {
                    }
                }
                return false;
            }
            return true;
        } catch (Exception | NoSuchMethodError unused) {
            return false;
        }
    }

    public String s() {
        try {
            WifiInfo connectionInfo = this.f2427c.getConnectionInfo();
            if (connectionInfo != null) {
                return connectionInfo.getMacAddress();
            }
            return null;
        } catch (Error | Exception unused) {
            return null;
        }
    }
}