package com.ido.ble.dfu.nodic.firmware;

/* JADX INFO: loaded from: classes2.dex */
public class CheckNewVersionPara {
    public int deviceId;
    public FilterPara filterPara;
    public int firmwareVersion;

    public static class FilterPara {
        public static final int ENVIRONMENT_FORMAL = 1;
        public static final int ENVIRONMENT_TEST = 2;
        public static final int GENDER_FEMALE = 2;
        public static final int GENDER_MAN = 1;
        public int age;
        public int environment = 1;
        public int gender;
        public String macAddress;
    }
}