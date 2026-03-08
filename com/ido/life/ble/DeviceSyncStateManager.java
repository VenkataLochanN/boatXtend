package com.ido.life.ble;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceSyncStateManager {
    private static boolean batteryLogSyncing;
    private static boolean healthDataSyncing;
    private static boolean restartLogSyncing;

    public boolean isDeviceSyncing() {
        return healthDataSyncing || restartLogSyncing || batteryLogSyncing;
    }

    public static void setHealthDataSyncState(boolean z) {
        healthDataSyncing = z;
    }

    public static void setRestartLogSyncState(boolean z) {
        restartLogSyncing = z;
    }

    public static void setBatteryLogSyncState(boolean z) {
        batteryLogSyncing = z;
    }
}