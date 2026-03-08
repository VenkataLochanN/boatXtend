package com.king.zxing.camera;

import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: loaded from: classes3.dex */
public final class CameraConfigurationUtils {
    private static final int AREA_PER_1000 = 400;
    private static final double MAX_ASPECT_DISTORTION = 0.05d;
    private static final float MAX_EXPOSURE_COMPENSATION = 1.5f;
    private static final int MAX_FPS = 20;
    private static final float MIN_EXPOSURE_COMPENSATION = 0.0f;
    private static final int MIN_FPS = 10;
    private static final int MIN_PREVIEW_PIXELS = 153600;
    private static final Pattern SEMICOLON = Pattern.compile(";");
    private static final String TAG = "CameraConfiguration";

    private CameraConfigurationUtils() {
    }

    public static void setFocus(Camera.Parameters parameters, boolean z, boolean z2, boolean z3) {
        String strFindSettableValue;
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (!z) {
            strFindSettableValue = null;
        } else if (z3 || z2) {
            strFindSettableValue = findSettableValue("focus mode", supportedFocusModes, "auto");
        } else {
            strFindSettableValue = findSettableValue("focus mode", supportedFocusModes, "continuous-picture", "continuous-video", "auto");
        }
        if (!z3 && strFindSettableValue == null) {
            strFindSettableValue = findSettableValue("focus mode", supportedFocusModes, "macro", "edof");
        }
        if (strFindSettableValue != null) {
            if (strFindSettableValue.equals(parameters.getFocusMode())) {
                Log.i(TAG, "Focus mode already set to " + strFindSettableValue);
                return;
            }
            parameters.setFocusMode(strFindSettableValue);
        }
    }

    public static void setTorch(Camera.Parameters parameters, boolean z) {
        String strFindSettableValue;
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        if (z) {
            strFindSettableValue = findSettableValue("flash mode", supportedFlashModes, "torch", DebugKt.DEBUG_PROPERTY_VALUE_ON);
        } else {
            strFindSettableValue = findSettableValue("flash mode", supportedFlashModes, DebugKt.DEBUG_PROPERTY_VALUE_OFF);
        }
        if (strFindSettableValue != null) {
            if (strFindSettableValue.equals(parameters.getFlashMode())) {
                Log.i(TAG, "Flash mode already set to " + strFindSettableValue);
                return;
            }
            Log.i(TAG, "Setting flash mode to " + strFindSettableValue);
            parameters.setFlashMode(strFindSettableValue);
        }
    }

    public static void setBestExposure(Camera.Parameters parameters, boolean z) {
        int minExposureCompensation = parameters.getMinExposureCompensation();
        int maxExposureCompensation = parameters.getMaxExposureCompensation();
        float exposureCompensationStep = parameters.getExposureCompensationStep();
        if (minExposureCompensation != 0 || maxExposureCompensation != 0) {
            if (exposureCompensationStep > 0.0f) {
                int iRound = Math.round((z ? 0.0f : MAX_EXPOSURE_COMPENSATION) / exposureCompensationStep);
                float f2 = exposureCompensationStep * iRound;
                int iMax = Math.max(Math.min(iRound, maxExposureCompensation), minExposureCompensation);
                if (parameters.getExposureCompensation() == iMax) {
                    Log.i(TAG, "Exposure compensation already set to " + iMax + " / " + f2);
                    return;
                }
                Log.i(TAG, "Setting exposure compensation to " + iMax + " / " + f2);
                parameters.setExposureCompensation(iMax);
                return;
            }
        }
        Log.i(TAG, "Camera does not support exposure compensation");
    }

    public static void setBestPreviewFPS(Camera.Parameters parameters) {
        setBestPreviewFPS(parameters, 10, 20);
    }

    public static void setBestPreviewFPS(Camera.Parameters parameters, int i, int i2) {
        List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        Log.i(TAG, "Supported FPS ranges: " + toString((Collection<int[]>) supportedPreviewFpsRange));
        if (supportedPreviewFpsRange == null || supportedPreviewFpsRange.isEmpty()) {
            return;
        }
        int[] iArr = null;
        Iterator<int[]> it = supportedPreviewFpsRange.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            int[] next = it.next();
            int i3 = next[0];
            int i4 = next[1];
            if (i3 >= i * 1000 && i4 <= i2 * 1000) {
                iArr = next;
                break;
            }
        }
        if (iArr == null) {
            Log.i(TAG, "No suitable FPS range?");
            return;
        }
        int[] iArr2 = new int[2];
        parameters.getPreviewFpsRange(iArr2);
        if (Arrays.equals(iArr2, iArr)) {
            Log.i(TAG, "FPS range already set to " + Arrays.toString(iArr));
            return;
        }
        Log.i(TAG, "Setting FPS range to " + Arrays.toString(iArr));
        parameters.setPreviewFpsRange(iArr[0], iArr[1]);
    }

    public static void setFocusArea(Camera.Parameters parameters) {
        if (parameters.getMaxNumFocusAreas() > 0) {
            Log.i(TAG, "Old focus areas: " + toString((Iterable<Camera.Area>) parameters.getFocusAreas()));
            List<Camera.Area> listBuildMiddleArea = buildMiddleArea(400);
            Log.i(TAG, "Setting focus area to : " + toString((Iterable<Camera.Area>) listBuildMiddleArea));
            parameters.setFocusAreas(listBuildMiddleArea);
            return;
        }
        Log.i(TAG, "Device does not support focus areas");
    }

    public static void setMetering(Camera.Parameters parameters) {
        if (parameters.getMaxNumMeteringAreas() > 0) {
            Log.i(TAG, "Old metering areas: " + parameters.getMeteringAreas());
            List<Camera.Area> listBuildMiddleArea = buildMiddleArea(400);
            Log.i(TAG, "Setting metering area to : " + toString((Iterable<Camera.Area>) listBuildMiddleArea));
            parameters.setMeteringAreas(listBuildMiddleArea);
            return;
        }
        Log.i(TAG, "Device does not support metering areas");
    }

    private static List<Camera.Area> buildMiddleArea(int i) {
        int i2 = -i;
        return Collections.singletonList(new Camera.Area(new Rect(i2, i2, i, i), 1));
    }

    public static void setVideoStabilization(Camera.Parameters parameters) {
        if (parameters.isVideoStabilizationSupported()) {
            if (parameters.getVideoStabilization()) {
                Log.i(TAG, "Video stabilization already enabled");
                return;
            } else {
                Log.i(TAG, "Enabling video stabilization...");
                parameters.setVideoStabilization(true);
                return;
            }
        }
        Log.i(TAG, "This device does not support video stabilization");
    }

    public static void setBarcodeSceneMode(Camera.Parameters parameters) {
        if ("barcode".equals(parameters.getSceneMode())) {
            Log.i(TAG, "Barcode scene mode already set");
            return;
        }
        String strFindSettableValue = findSettableValue("scene mode", parameters.getSupportedSceneModes(), "barcode");
        if (strFindSettableValue != null) {
            parameters.setSceneMode(strFindSettableValue);
        }
    }

    public static void setZoom(Camera.Parameters parameters, double d2) {
        if (parameters.isZoomSupported()) {
            Integer numIndexOfClosestZoom = indexOfClosestZoom(parameters, d2);
            if (numIndexOfClosestZoom == null) {
                return;
            }
            if (parameters.getZoom() == numIndexOfClosestZoom.intValue()) {
                Log.i(TAG, "Zoom is already set to " + numIndexOfClosestZoom);
                return;
            }
            Log.i(TAG, "Setting zoom to " + numIndexOfClosestZoom);
            parameters.setZoom(numIndexOfClosestZoom.intValue());
            return;
        }
        Log.i(TAG, "Zoom is not supported");
    }

    private static Integer indexOfClosestZoom(Camera.Parameters parameters, double d2) {
        List<Integer> zoomRatios = parameters.getZoomRatios();
        Log.i(TAG, "Zoom ratios: " + zoomRatios);
        int maxZoom = parameters.getMaxZoom();
        if (zoomRatios == null || zoomRatios.isEmpty() || zoomRatios.size() != maxZoom + 1) {
            Log.w(TAG, "Invalid zoom ratios!");
            return null;
        }
        double d3 = d2 * 100.0d;
        double d4 = Double.POSITIVE_INFINITY;
        int i = 0;
        for (int i2 = 0; i2 < zoomRatios.size(); i2++) {
            double dAbs = Math.abs(((double) zoomRatios.get(i2).intValue()) - d3);
            if (dAbs < d4) {
                i = i2;
                d4 = dAbs;
            }
        }
        Log.i(TAG, "Chose zoom ratio of " + (((double) zoomRatios.get(i).intValue()) / 100.0d));
        return Integer.valueOf(i);
    }

    public static void setInvertColor(Camera.Parameters parameters) {
        if ("negative".equals(parameters.getColorEffect())) {
            Log.i(TAG, "Negative effect already set");
            return;
        }
        String strFindSettableValue = findSettableValue("color effect", parameters.getSupportedColorEffects(), "negative");
        if (strFindSettableValue != null) {
            parameters.setColorEffect(strFindSettableValue);
        }
    }

    public static Point findBestPreviewSizeValue(Camera.Parameters parameters, Point point) {
        double d2;
        int i;
        Iterator<Camera.Size> it;
        Camera.Size size;
        String str;
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        String str2 = "Parameters contained no preview size!";
        if (supportedPreviewSizes == null) {
            Log.w(TAG, "Device returned no supported preview sizes; using default");
            Camera.Size previewSize = parameters.getPreviewSize();
            if (previewSize == null) {
                throw new IllegalStateException("Parameters contained no preview size!");
            }
            return new Point(previewSize.width, previewSize.height);
        }
        if (Log.isLoggable(TAG, 4)) {
            StringBuilder sb = new StringBuilder();
            for (Camera.Size size2 : supportedPreviewSizes) {
                sb.append(size2.width);
                sb.append('x');
                sb.append(size2.height);
                sb.append(' ');
            }
            Log.i(TAG, "Supported preview sizes: " + ((Object) sb));
        }
        if (point.x < point.y) {
            d2 = point.x;
            i = point.y;
        } else {
            d2 = point.y;
            i = point.x;
        }
        double d3 = d2 / ((double) i);
        Log.i(TAG, "screenAspectRatio: " + d3);
        Camera.Size size3 = null;
        Iterator<Camera.Size> it2 = supportedPreviewSizes.iterator();
        char c2 = 0;
        int i2 = 0;
        while (it2.hasNext()) {
            Camera.Size next = it2.next();
            int i3 = next.width;
            int i4 = next.height;
            int i5 = i3 * i4;
            if (i5 < MIN_PREVIEW_PIXELS) {
                it = it2;
                str = str2;
                size = size3;
            } else {
                char c3 = i3 < i4 ? (char) 1 : c2;
                int i6 = c3 != 0 ? i3 : i4;
                int i7 = c3 != 0 ? i4 : i3;
                Object[] objArr = new Object[2];
                objArr[c2] = Integer.valueOf(i6);
                objArr[1] = Integer.valueOf(i7);
                Log.i(TAG, String.format("maybeFlipped:%d * %d", objArr));
                it = it2;
                size = size3;
                str = str2;
                double d4 = ((double) i6) / ((double) i7);
                Log.i(TAG, "aspectRatio: " + d4);
                double dAbs = Math.abs(d4 - d3);
                Log.i(TAG, "distortion: " + dAbs);
                if (dAbs <= MAX_ASPECT_DISTORTION) {
                    if (i6 == point.x && i7 == point.y) {
                        Point point2 = new Point(i3, i4);
                        Log.i(TAG, "Found preview size exactly matching screen size: " + point2);
                        return point2;
                    }
                    if (i5 > i2) {
                        size3 = next;
                        i2 = i5;
                    } else {
                        size3 = size;
                    }
                    it2 = it;
                }
                str2 = str;
                c2 = 0;
            }
            it2 = it;
            size3 = size;
            str2 = str;
            c2 = 0;
        }
        String str3 = str2;
        Camera.Size size4 = size3;
        if (size4 != null) {
            Point point3 = new Point(size4.width, size4.height);
            Log.i(TAG, "Using largest suitable preview size: " + point3);
            return point3;
        }
        Camera.Size previewSize2 = parameters.getPreviewSize();
        if (previewSize2 == null) {
            throw new IllegalStateException(str3);
        }
        Point point4 = new Point(previewSize2.width, previewSize2.height);
        Log.i(TAG, "No suitable preview sizes, using default: " + point4);
        return point4;
    }

    private static String findSettableValue(String str, Collection<String> collection, String... strArr) {
        Log.i(TAG, "Requesting " + str + " value from among: " + Arrays.toString(strArr));
        Log.i(TAG, "Supported " + str + " values: " + collection);
        if (collection != null) {
            for (String str2 : strArr) {
                if (collection.contains(str2)) {
                    Log.i(TAG, "Can set " + str + " to: " + str2);
                    return str2;
                }
            }
        }
        Log.i(TAG, "No supported values match");
        return null;
    }

    private static String toString(Collection<int[]> collection) {
        if (collection == null || collection.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Iterator<int[]> it = collection.iterator();
        while (it.hasNext()) {
            sb.append(Arrays.toString(it.next()));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    private static String toString(Iterable<Camera.Area> iterable) {
        if (iterable == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Camera.Area area : iterable) {
            sb.append(area.rect);
            sb.append(':');
            sb.append(area.weight);
            sb.append(' ');
        }
        return sb.toString();
    }

    public static String collectStats(Camera.Parameters parameters) {
        return collectStats(parameters.flatten());
    }

    public static String collectStats(CharSequence charSequence) {
        StringBuilder sb = new StringBuilder(1000);
        sb.append("BOARD=");
        sb.append(Build.BOARD);
        sb.append('\n');
        sb.append("BRAND=");
        sb.append(Build.BRAND);
        sb.append('\n');
        sb.append("CPU_ABI=");
        sb.append(Build.CPU_ABI);
        sb.append('\n');
        sb.append("DEVICE=");
        sb.append(Build.DEVICE);
        sb.append('\n');
        sb.append("DISPLAY=");
        sb.append(Build.DISPLAY);
        sb.append('\n');
        sb.append("FINGERPRINT=");
        sb.append(Build.FINGERPRINT);
        sb.append('\n');
        sb.append("HOST=");
        sb.append(Build.HOST);
        sb.append('\n');
        sb.append("ID=");
        sb.append(Build.ID);
        sb.append('\n');
        sb.append("MANUFACTURER=");
        sb.append(Build.MANUFACTURER);
        sb.append('\n');
        sb.append("MODEL=");
        sb.append(Build.MODEL);
        sb.append('\n');
        sb.append("PRODUCT=");
        sb.append(Build.PRODUCT);
        sb.append('\n');
        sb.append("TAGS=");
        sb.append(Build.TAGS);
        sb.append('\n');
        sb.append("TIME=");
        sb.append(Build.TIME);
        sb.append('\n');
        sb.append("TYPE=");
        sb.append(Build.TYPE);
        sb.append('\n');
        sb.append("USER=");
        sb.append(Build.USER);
        sb.append('\n');
        sb.append("VERSION.CODENAME=");
        sb.append(Build.VERSION.CODENAME);
        sb.append('\n');
        sb.append("VERSION.INCREMENTAL=");
        sb.append(Build.VERSION.INCREMENTAL);
        sb.append('\n');
        sb.append("VERSION.RELEASE=");
        sb.append(Build.VERSION.RELEASE);
        sb.append('\n');
        sb.append("VERSION.SDK_INT=");
        sb.append(Build.VERSION.SDK_INT);
        sb.append('\n');
        if (charSequence != null) {
            String[] strArrSplit = SEMICOLON.split(charSequence);
            Arrays.sort(strArrSplit);
            for (String str : strArrSplit) {
                sb.append(str);
                sb.append('\n');
            }
        }
        return sb.toString();
    }
}