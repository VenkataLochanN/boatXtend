package com.ido.life.ble;

import com.ido.ble.LocalDataManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportDataHelper {
    static List<Integer> trackSportType = new ArrayList();

    private static int getCarloy(int i, float f2, long j) {
        return (int) (((((((double) i) * 3.5d) * ((double) f2)) * ((double) (j / 60.0f))) / 1000.0d) * 4.92d);
    }

    public static int getSportTypeStrong(int i) {
        int i2 = 4;
        if (i == 5) {
            return 6;
        }
        if (i != 6) {
            if (i != 7) {
                if (i != 9 && i != 10) {
                    if (i != 11) {
                        if (i != 12) {
                            if (i == 13 || i == 14 || i == 8 || i == 16 || i == 17 || i == 18 || i == 19) {
                                return 6;
                            }
                            if (i != 20) {
                                if (i != 21) {
                                    if (i != 22) {
                                        if (i != 23) {
                                            if (i != 24) {
                                                if (i != 25) {
                                                    if (i == 26) {
                                                        i2 = 3;
                                                    } else if (i != 27 && i != 28) {
                                                        if (i != 29) {
                                                            return 6;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return i2;
                }
                return 5;
            }
            return 7;
        }
        return 8;
    }

    static {
        trackSportType.add(1);
        trackSportType.add(2);
        trackSportType.add(3);
        trackSportType.add(4);
        trackSportType.add(50);
        trackSportType.add(48);
        trackSportType.add(52);
    }

    public static int completCarloy(int i, int i2, int i3) {
        if (isTrackSport(i)) {
            return getSportGpsCarloy(i, ((double) i2) / 1000.0d);
        }
        return getSportCarloy(i, i3);
    }

    public static boolean isTrackSport(int i) {
        return trackSportType.contains(Integer.valueOf(i));
    }

    private static int getSportGpsCarloy(int i, double d2) {
        return (int) (((double) (LocalDataManager.getUserInfo() != null ? r0.weight : 65.0f)) * d2 * (i != 2 ? i != 3 ? 0.8214d : 0.6142d : 1.036d));
    }

    private static int getSportCarloy(int i, long j) {
        return getCarloy(getSportTypeStrong(i), j);
    }

    private static int getCarloy(int i, long j) {
        return getCarloy(i, LocalDataManager.getUserInfo() != null ? r0.weight : 65.0f, j);
    }
}