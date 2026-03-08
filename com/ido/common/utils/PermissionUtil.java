package com.ido.common.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PermissionUtil {
    public static final int CODE_REQUEST_BLE = 400;
    public static final int CODE_REQUEST_CAMERA = 200;
    public static final int CODE_REQUEST_LOCATION = 300;
    public static final int CODE_REQUEST_NOTIFICATION = 500;
    public static final int CODE_REQUEST_PHONE = 502;
    public static final int CODE_REQUEST_SMS = 501;
    public static final int CODE_REQUEST_STORAGE = 100;
    public static final int CODE_REQUEST_VIBRATE = 503;
    private RequestResult mRequestResult;

    public interface RequestResult {
        void requestPermissionsFail(int i);

        void requestPermissionsSuccess(int i);
    }

    public static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public void setRequestResult(RequestResult requestResult) {
        this.mRequestResult = requestResult;
    }

    public static List<String> findDeniedPermissions(Context context, String... strArr) {
        if (context == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 23) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(context, str) != 0) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static boolean checkSelfPermission(Context context, String... strArr) {
        List<String> listFindDeniedPermissions = findDeniedPermissions(context, strArr);
        return listFindDeniedPermissions == null || listFindDeniedPermissions.size() == 0;
    }

    public static void requestPermissions(Object obj, int i, String... strArr) {
        boolean z = obj instanceof Activity;
        if ((z || (obj instanceof Fragment)) && isOverMarshmallow()) {
            if (z) {
                Activity activity = (Activity) obj;
                List<String> listFindDeniedPermissions = findDeniedPermissions(activity, strArr);
                if (listFindDeniedPermissions == null || listFindDeniedPermissions.size() <= 0) {
                    return;
                }
                activity.requestPermissions((String[]) listFindDeniedPermissions.toArray(new String[listFindDeniedPermissions.size()]), i);
                return;
            }
            if (obj instanceof Fragment) {
                Fragment fragment = (Fragment) obj;
                List<String> listFindDeniedPermissions2 = findDeniedPermissions(fragment.getContext(), strArr);
                if (listFindDeniedPermissions2 == null || listFindDeniedPermissions2.size() <= 0) {
                    return;
                }
                fragment.requestPermissions((String[]) listFindDeniedPermissions2.toArray(new String[listFindDeniedPermissions2.size()]), i);
            }
        }
    }

    public static boolean grantedPermission(int[] iArr) {
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (this.mRequestResult == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] != 0) {
                arrayList.add(strArr[i2]);
            }
        }
        if (arrayList.size() > 0) {
            this.mRequestResult.requestPermissionsFail(i);
        } else {
            this.mRequestResult.requestPermissionsSuccess(i);
        }
    }

    public static String[] getStoragePermission() {
        return new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
    }

    public static String[] getCameraPermission() {
        return new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
    }

    public static String[] getPhonePermission() {
        if (Build.VERSION.SDK_INT >= 26) {
            return new String[]{"android.permission.READ_CONTACTS", "android.permission.READ_PHONE_STATE", "android.permission.CALL_PHONE", "android.permission.READ_CALL_LOG", "android.permission.ANSWER_PHONE_CALLS"};
        }
        return new String[]{"android.permission.READ_CONTACTS", "android.permission.READ_PHONE_STATE", "android.permission.CALL_PHONE", "android.permission.READ_CALL_LOG"};
    }

    public static String[] getOnlyPhonePermission() {
        return new String[]{"android.permission.READ_CONTACTS", "android.permission.READ_PHONE_STATE"};
    }

    public static String[] getOnlyContactPermission() {
        return new String[]{"android.permission.READ_CONTACTS"};
    }

    public static String[] getSmsPermission() {
        return new String[]{"android.permission.READ_CONTACTS", "android.permission.READ_SMS"};
    }

    public static String[] getOnlySmsPermission() {
        return new String[]{"android.permission.READ_SMS"};
    }

    public static String[] getOnlyCameraPermission() {
        return new String[]{"android.permission.CAMERA"};
    }

    public static String[] getLocationPermission() {
        return new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    }

    public static String[] getLocationBackGroundPermission() {
        if (Build.VERSION.SDK_INT >= 29) {
            return new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"};
        }
        return new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    }

    public static String[] getBluetoothPermission() {
        return new String[]{"android.permission.BLUETOOTH", "android.permission.BLUETOOTH_ADMIN", "android.permission.ACCESS_COARSE_LOCATION"};
    }

    public static String[] getOnlyBluetoothPermission() {
        return new String[]{"android.permission.BLUETOOTH", "android.permission.BLUETOOTH_ADMIN"};
    }

    public static String[] getVibratePermission() {
        return new String[]{"android.permission.VIBRATE"};
    }
}