package com.ido.life.util;

import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Pair;
import com.boat.Xtend.two.R;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.DeviceParaChangedCallBack;
import com.ido.ble.event.stat.one.d;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.DeviceChangedPara;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.FileDialDefinedUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.base.BaseMessage;
import com.ido.life.constants.WallpaperDialConstants;
import com.ido.life.data.cache.AbsDataCacheManager;
import com.ido.life.module.device.presenter.BaseDialPresenter;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: WallpaperDialManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\b\u0010*\u001a\u00020\u0012H\u0002J\u0010\u0010+\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\tH\u0007J\u0010\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020\tH\u0007J\u0010\u0010/\u001a\u0002002\u0006\u0010.\u001a\u00020\tH\u0007J\u0018\u00101\u001a\u00020\u00122\u0006\u00102\u001a\u00020\t2\u0006\u00103\u001a\u00020\tH\u0007J\u0018\u00104\u001a\u00020\u00122\u0006\u00102\u001a\u00020\t2\u0006\u00103\u001a\u00020\tH\u0007J\u0018\u00105\u001a\u00020\u00122\u0006\u00106\u001a\u00020\t2\u0006\u00107\u001a\u00020\tH\u0007J\u0018\u00108\u001a\u0002092\u0006\u00102\u001a\u00020\t2\u0006\u00103\u001a\u00020\tH\u0007J\u0010\u0010:\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\tH\u0007J\u0010\u0010;\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\tH\u0007J\u0010\u0010<\u001a\u0002092\u0006\u0010=\u001a\u00020\tH\u0007J\u0010\u0010>\u001a\u0002092\u0006\u0010?\u001a\u00020\tH\u0002J\u0010\u0010@\u001a\u0002092\u0006\u0010,\u001a\u00020\tH\u0007J\u0010\u0010A\u001a\u00020\t2\u0006\u0010,\u001a\u00020\tH\u0007J\b\u0010B\u001a\u00020\tH\u0007J\b\u0010C\u001a\u00020\tH\u0007J\u0010\u0010C\u001a\u00020\t2\u0006\u0010D\u001a\u00020\tH\u0007J\b\u0010E\u001a\u00020\tH\u0007J\u0010\u0010F\u001a\u00020G2\u0006\u0010,\u001a\u00020\tH\u0007J\u0010\u0010H\u001a\u00020\t2\u0006\u0010,\u001a\u00020\tH\u0007J\u0010\u0010I\u001a\u00020\t2\u0006\u0010,\u001a\u00020\tH\u0007J\b\u0010J\u001a\u00020\tH\u0007J\u0010\u0010K\u001a\u00020\t2\u0006\u0010,\u001a\u00020\tH\u0007J\u0010\u0010L\u001a\u00020\t2\u0006\u0010,\u001a\u00020\tH\u0007J\u0010\u0010M\u001a\u0002002\u0006\u0010,\u001a\u00020\tH\u0007J\b\u0010N\u001a\u00020\u0006H\u0007J\n\u0010O\u001a\u0004\u0018\u00010(H\u0002J\b\u0010P\u001a\u00020\tH\u0007J\u0010\u0010Q\u001a\u00020\t2\u0006\u0010,\u001a\u00020\tH\u0007J\b\u0010R\u001a\u00020\tH\u0007J\u0010\u0010S\u001a\u00020\u00062\u0006\u0010T\u001a\u00020(H\u0002J\u0010\u0010U\u001a\u00020\t2\u0006\u0010V\u001a\u00020\u0006H\u0007J\u0010\u0010W\u001a\u00020\u00062\u0006\u0010X\u001a\u00020\u0006H\u0007J\u0010\u0010Y\u001a\u00020\u00062\u0006\u0010X\u001a\u00020\u0006H\u0007J\u0010\u0010Z\u001a\u00020\t2\u0006\u0010X\u001a\u00020\u0006H\u0007J\u0012\u0010[\u001a\u00020\u00062\b\b\u0001\u0010\\\u001a\u00020\u0006H\u0007J\u001e\u0010]\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010^2\u0006\u0010_\u001a\u00020\tH\u0007J\u0012\u0010`\u001a\u00020\u00102\b\b\u0001\u0010\\\u001a\u00020\u0006H\u0007J\b\u0010a\u001a\u00020\tH\u0007J\b\u0010b\u001a\u00020\tH\u0007J\u0010\u0010c\u001a\u00020\t2\u0006\u0010d\u001a\u00020\tH\u0007J\b\u0010e\u001a\u00020\tH\u0007J\b\u0010f\u001a\u00020\tH\u0007J\u0010\u0010f\u001a\u00020\t2\u0006\u0010D\u001a\u00020\tH\u0007J\b\u0010g\u001a\u000200H\u0007J\b\u0010h\u001a\u00020\tH\u0007J\u000e\u0010i\u001a\u00020\t2\u0006\u0010?\u001a\u00020\tJ\u000e\u0010j\u001a\b\u0012\u0004\u0012\u00020\t0kH\u0007J\b\u0010l\u001a\u00020\u0012H\u0007J\u0006\u0010m\u001a\u000209J\u0010\u0010n\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\tH\u0007J\u0010\u0010o\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\tH\u0007J\u0010\u0010p\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\tH\u0007J\b\u0010q\u001a\u00020\u0012H\u0007J\b\u0010r\u001a\u00020\u0012H\u0007J\u0012\u0010s\u001a\u0002092\b\u0010t\u001a\u0004\u0018\u00010uH\u0016J\u0016\u0010v\u001a\u0002092\f\u0010w\u001a\b\u0012\u0004\u0012\u00020\t0xH\u0007J\u0012\u0010y\u001a\u0002092\b\u0010?\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010z\u001a\u0002092\u0006\u0010?\u001a\u00020\tH\u0016J\u0010\u0010{\u001a\u00020\t2\u0006\u0010,\u001a\u00020\tH\u0007J\u0010\u0010|\u001a\u00020\u00122\u0006\u0010}\u001a\u00020\tH\u0007J \u0010~\u001a\u00020\u00122\u0006\u00102\u001a\u00020\t2\u0006\u00103\u001a\u00020\t2\u0006\u0010r\u001a\u00020\u0012H\u0007J \u0010\u007f\u001a\u00020\u00122\u0006\u00102\u001a\u00020\t2\u0006\u00103\u001a\u00020\t2\u0006\u0010r\u001a\u00020\u0012H\u0007J \u0010\u0080\u0001\u001a\t\u0012\u0004\u0012\u00020\u00060\u0081\u00012\u000e\u0010\u0082\u0001\u001a\t\u0012\u0004\u0012\u00020\u00060\u0081\u0001H\u0007J\u0011\u0010\u0083\u0001\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\tH\u0007J\u0011\u0010\u0084\u0001\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\tH\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0085\u0001"}, d2 = {"Lcom/ido/life/util/WallpaperDialManager;", "Lcom/ido/ble/callback/DeviceParaChangedCallBack$ICallBack;", "Lcom/ido/life/data/cache/AbsDataCacheManager;", "", "()V", "BMP_FORMAT_16", "", "BMP_FORMAT_32", "CWD_APP_JSON", "", "CWD_DEVICE_BACKGROUND_IMAGE", "CWD_DEVICE_JSON", "CWD_DEVICE_PREVIEW_IMAGE", "CWD_PACK_SUFFIX", "FILE_DIR_NAME_FUNCTION", "HEALTH_GROUP", "", "IS_SUPPORT_BMP", "", "PREVIEW_IMAGE_DIR", "ROOT_DIR", "SPORTS_GROUP", "TEMP_BG_BMP", "TEMP_BG_PNG", "TEMP_DIR", "TEMP_NAME", "TEMP_PREVIEW_IMAGE", "TEMP_PREVIEW_IMAGE_BMP", "TOOLS_GROUP", "WALLPAPER_DIAL_ICON_PREFIX", "WALLPAPER_DIAL_PREFIX", "ZIP_DIR", "deviceUniqueCode", "getDeviceUniqueCode", "()Ljava/lang/String;", "isTelephone", "()Z", "setTelephone", "(Z)V", "mCurrentDevice", "Lcom/ido/ble/bluetooth/device/BLEDevice;", "mOperateDialName", "checkDeviceNull", "clearCwd", "otaFaceName", "colorTo16", "color", "colorTo16Long", "", "convertImage2Bmp", "src", "des", "convertImage2BmpByUtil", "copyDir", "srcDir", "desDir", "copyFile", "", "deleteCwdPackFile", "deleteDeviceCwdFile", "deleteFile", "path", "deleteLocalWallpaper", "macAddress", "deleteTempCwdPack", "getAppJsonPath", "getCwdBaseDir", "getCwdBgImageName", AppMeasurementSdk.ConditionalUserProperty.NAME, "getCwdPackDir", "getCwdPackFile", "Ljava/io/File;", "getCwdPackFilePath", "getDeviceBackgroundImagePath", "getDeviceCwdBaseDir", "getDeviceCwdPackPath", "getDeviceCwdParentDir", "getDeviceDialSize", "getDeviceId", "getDeviceInfo", "getDeviceMac", "getDevicePreviewImagePath", "getDeviceTempJsonPath", "getDeviceType", "device", "getFunctionGroupName", "groupType", "getFunctionGroupType", "function", "getFunctionIcon", "getFunctionName", "getGravityByLocation", FirebaseAnalytics.Param.LOCATION, "getImageSize", "Landroid/util/Pair;", "imagePath", "getLayoutRulesByLocation", "getTempBgImagePath", "getTempCwdBaseDir", "getTempCwdBgImagePath", "fileName", "getTempCwdDir", "getTempCwdPreviewImagePath", "getTempCwdSize", "getTempZipPath", "getWallPaperDialPath", "getWallpaperColorList", "", "ifChangeBatteryColor", d.m, "isCwdPackFileExist", "isDeviceCwdDirExist", "isDeviceCwdPackExist", "isTempCwdExist", "isUseBmpBg", "onChanged", "deviceChangedPara", "Lcom/ido/ble/protocol/model/DeviceChangedPara;", "onDialDeleteEvent", "msg", "Lcom/ido/life/base/BaseMessage;", "onSdkInitComplete", "onUnBind", "packTempCwdPackage", "prepareDeviceTempCwdResource", "mDialOtaFaceName", "replaceCwrBgImageWithTemp", "replaceCwrPreviewImageWithTemp", "sortFunctionByGroup", "", "functions", "unpackCwdPackage", "unpackTempCwdPackage", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WallpaperDialManager extends AbsDataCacheManager<Object> implements DeviceParaChangedCallBack.ICallBack {
    private static final int BMP_FORMAT_16 = 5;
    private static final int BMP_FORMAT_32 = 8;
    private static final String CWD_APP_JSON = "app.json";
    private static final String CWD_DEVICE_BACKGROUND_IMAGE = "bg.bmp";
    private static final String CWD_DEVICE_JSON = "iwf.json";
    private static final String CWD_DEVICE_PREVIEW_IMAGE = "preview.png";
    private static final String CWD_PACK_SUFFIX = ".zip";
    private static final String FILE_DIR_NAME_FUNCTION = "watchFileFunction";
    private static final int[] HEALTH_GROUP;
    public static final WallpaperDialManager INSTANCE = new WallpaperDialManager();
    private static final boolean IS_SUPPORT_BMP = true;
    private static final String PREVIEW_IMAGE_DIR = "images/";
    private static final String ROOT_DIR = "/boatWave/wallpaper_defined/";
    private static final int[] SPORTS_GROUP;
    public static final String TEMP_BG_BMP = "bg.bmp";
    public static final String TEMP_BG_PNG = "bg.png";
    private static final String TEMP_DIR = "temp/";
    private static final String TEMP_NAME = "temp";
    public static final String TEMP_PREVIEW_IMAGE = "preview.png";
    public static final String TEMP_PREVIEW_IMAGE_BMP = "preview.png";
    private static final int[] TOOLS_GROUP;
    private static final String WALLPAPER_DIAL_ICON_PREFIX = "icon_wallpaper_dial_function_";
    private static final String WALLPAPER_DIAL_PREFIX = "wallpaper_dial_function_";
    private static final String ZIP_DIR = "zip/";
    private static boolean isTelephone;
    private static BLEDevice mCurrentDevice;
    private static String mOperateDialName;

    @JvmStatic
    public static final int getGravityByLocation(@WallpaperDialConstants.WidgetLocation int location) {
        if (location == 1) {
            return 51;
        }
        if (location == 3) {
            return 53;
        }
        if (location != 7) {
            return location != 9 ? 53 : 85;
        }
        return 83;
    }

    @JvmStatic
    public static final boolean ifChangeBatteryColor() {
        return true;
    }

    @JvmStatic
    public static final boolean isUseBmpBg() {
        return true;
    }

    static {
        int[] integerArray = ResourceUtil.getIntegerArray(R.array.dial_function_health);
        Intrinsics.checkExpressionValueIsNotNull(integerArray, "ResourceUtil.getIntegerA…ray.dial_function_health)");
        HEALTH_GROUP = integerArray;
        int[] integerArray2 = ResourceUtil.getIntegerArray(R.array.dial_function_sports);
        Intrinsics.checkExpressionValueIsNotNull(integerArray2, "ResourceUtil.getIntegerA…ray.dial_function_sports)");
        SPORTS_GROUP = integerArray2;
        int[] integerArray3 = ResourceUtil.getIntegerArray(R.array.dial_function_tools);
        Intrinsics.checkExpressionValueIsNotNull(integerArray3, "ResourceUtil.getIntegerA…rray.dial_function_tools)");
        TOOLS_GROUP = integerArray3;
    }

    private WallpaperDialManager() {
    }

    public final boolean isTelephone() {
        return isTelephone;
    }

    public final void setTelephone(boolean z) {
        isTelephone = z;
    }

    @JvmStatic
    public static final int getDeviceId() {
        BLEDevice bLEDevice = mCurrentDevice;
        if (bLEDevice != null) {
            return bLEDevice.mDeviceId;
        }
        return 0;
    }

    @JvmStatic
    public static final String getDeviceMac() {
        String str;
        BLEDevice bLEDevice = mCurrentDevice;
        return (bLEDevice == null || (str = bLEDevice.mDeviceAddress) == null) ? "" : str;
    }

    public final void init() {
        BLEManager.registerDeviceParaChangedCallBack(this);
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        String dialLogPath = logPathImpl.getDialLogPath();
        Intrinsics.checkExpressionValueIsNotNull(dialLogPath, "LogPathImpl.getInstance().dialLogPath");
        setMLogPath(dialLogPath);
    }

    @JvmStatic
    public static final int getFunctionGroupType(int function) {
        if (ArraysKt.contains(HEALTH_GROUP, function)) {
            return 1;
        }
        if (ArraysKt.contains(SPORTS_GROUP, function)) {
            return 2;
        }
        return ArraysKt.contains(TOOLS_GROUP, function) ? 5 : 1;
    }

    @JvmStatic
    public static final String getFunctionGroupName(int groupType) {
        int i = R.string.dial_function_health;
        if (groupType != 1) {
            if (groupType == 2) {
                i = R.string.dial_function_sports;
            } else if (groupType == 5) {
                i = R.string.dial_function_tools;
            }
        }
        String languageText = LanguageUtil.getLanguageText(i);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguageText(resId)");
        return languageText;
    }

    @JvmStatic
    public static final String getFunctionName(int function) {
        int stringResId = ResourceUtil.getStringResId(WALLPAPER_DIAL_PREFIX + function);
        if (stringResId <= 0) {
            return "";
        }
        String languageText = LanguageUtil.getLanguageText(stringResId);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguageText(nameRes)");
        return languageText;
    }

    @JvmStatic
    public static final int getFunctionIcon(int function) {
        if (function <= 0) {
            function = 1;
        }
        return ResourceUtils.INSTANCE.getMipmapResId(WALLPAPER_DIAL_ICON_PREFIX + function);
    }

    @JvmStatic
    public static final List<Integer> sortFunctionByGroup(List<Integer> functions) {
        Intrinsics.checkParameterIsNotNull(functions, "functions");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        Iterator<T> it = functions.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            if (ArraysKt.contains(HEALTH_GROUP, iIntValue)) {
                arrayList2.add(Integer.valueOf(iIntValue));
            } else if (ArraysKt.contains(SPORTS_GROUP, iIntValue)) {
                arrayList3.add(Integer.valueOf(iIntValue));
            } else if (ArraysKt.contains(TOOLS_GROUP, iIntValue)) {
                arrayList4.add(Integer.valueOf(iIntValue));
            }
        }
        arrayList.addAll(arrayList2);
        arrayList.addAll(arrayList3);
        arrayList.addAll(arrayList4);
        return arrayList;
    }

    @JvmStatic
    public static final int[] getLayoutRulesByLocation(@WallpaperDialConstants.WidgetLocation int location) {
        return location != 1 ? location != 3 ? location != 7 ? location != 9 ? new int[]{19, 6} : new int[]{19, 8} : new int[]{18, 8} : new int[]{19, 6} : new int[]{18, 6};
    }

    @JvmStatic
    public static final List<String> getWallpaperColorList() {
        ArrayList arrayList = new ArrayList();
        if (arrayList.isEmpty()) {
            for (WallpaperDialConstants.WidgetColor widgetColor : WallpaperDialConstants.WidgetColor.values()) {
                arrayList.add(widgetColor.getColor());
            }
        }
        return arrayList;
    }

    private final String getDeviceUniqueCode() {
        String str;
        BLEDevice bLEDevice = mCurrentDevice;
        return (bLEDevice == null || (str = bLEDevice.mDeviceAddress) == null) ? "" : str;
    }

    @JvmStatic
    public static final String getCwdBgImageName() {
        return isUseBmpBg() ? "bg.bmp" : TEMP_BG_PNG;
    }

    @JvmStatic
    public static final String getCwdBgImageName(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return StringsKt.endsWith(name, ".bmp", true) ? "bg.bmp" : TEMP_BG_PNG;
    }

    @JvmStatic
    public static final String getCwdBaseDir() {
        StringBuilder sb = new StringBuilder();
        VeryFitApp app = VeryFitApp.getApp();
        Intrinsics.checkExpressionValueIsNotNull(app, "VeryFitApp.getApp()");
        File filesDir = app.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "VeryFitApp.getApp().filesDir");
        sb.append(filesDir.getPath());
        sb.append(ROOT_DIR);
        return sb.toString();
    }

    @JvmStatic
    public static final String getTempCwdBaseDir() {
        return getCwdBaseDir() + TEMP_DIR;
    }

    @JvmStatic
    public static final String getTempCwdDir() {
        return getTempCwdBaseDir() + "temp" + File.separator;
    }

    @JvmStatic
    public static final String getTempZipPath() {
        return getTempCwdBaseDir() + ZIP_DIR + "temp.zip";
    }

    @JvmStatic
    public static final String getTempBgImagePath() {
        return getTempCwdBaseDir() + TEMP_BG_PNG;
    }

    @JvmStatic
    public static final String getTempCwdBgImagePath(String fileName) {
        Intrinsics.checkParameterIsNotNull(fileName, "fileName");
        return getTempCwdDir() + fileName;
    }

    @JvmStatic
    public static final String getTempCwdPreviewImagePath() {
        return getTempCwdBaseDir() + "preview.png";
    }

    @JvmStatic
    public static final String getTempCwdPreviewImagePath(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return getTempCwdDir() + name;
    }

    @JvmStatic
    public static final boolean isTempCwdExist() {
        return new File(getTempCwdDir()).exists();
    }

    @JvmStatic
    public static final long getTempCwdSize() {
        File file = new File(getTempCwdDir());
        if (file.exists()) {
            return FileUtil.sizeOfDirectory(file);
        }
        return 0L;
    }

    @JvmStatic
    public static final void deleteTempCwdPack(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        try {
            File file = new File(getTempCwdDir());
            if (file.exists() && file.isDirectory()) {
                FileUtil.deleteDirectory(file);
            }
        } catch (Exception unused) {
        }
    }

    @JvmStatic
    public static final boolean prepareDeviceTempCwdResource(String mDialOtaFaceName) {
        Intrinsics.checkParameterIsNotNull(mDialOtaFaceName, "mDialOtaFaceName");
        return copyDir(getDeviceCwdPackPath(mDialOtaFaceName), getTempCwdDir());
    }

    @JvmStatic
    public static final String getDeviceTempJsonPath() {
        return getTempCwdDir() + File.separator + "iwf.json";
    }

    @JvmStatic
    public static final String packTempCwdPackage(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        try {
            String tempZipPath = getTempZipPath();
            ZipUtils.zip(getTempCwdDir(), getTempCwdBaseDir() + ZIP_DIR, "temp.zip");
            return tempZipPath;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    @JvmStatic
    public static final boolean unpackTempCwdPackage(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        try {
            boolean zUnpackCopyZip = FileDialDefinedUtil.unpackCopyZip(getTempCwdDir(), getDeviceCwdPackPath(otaFaceName));
            INSTANCE.logP("unpackTempCwdPackage：");
            if (!zUnpackCopyZip) {
                return zUnpackCopyZip;
            }
            File file = new File(getTempCwdBaseDir() + otaFaceName);
            if (!file.exists()) {
                return zUnpackCopyZip;
            }
            File file2 = new File(file.getParent() + File.separator + "temp");
            INSTANCE.logP("重命名：" + file.getName() + " -> " + file2.getName());
            return file.renameTo(file2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @JvmStatic
    public static final boolean unpackCwdPackage(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        try {
            return FileDialDefinedUtil.unpackCopyZip(getDeviceCwdBaseDir(), getCwdPackFilePath(otaFaceName));
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @JvmStatic
    public static final String getCwdPackDir() {
        return getCwdBaseDir() + ZIP_DIR;
    }

    @JvmStatic
    public static final String getCwdPackFilePath(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        return getCwdBaseDir() + ZIP_DIR + otaFaceName + ".zip";
    }

    @JvmStatic
    public static final File getCwdPackFile(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        return new File(getCwdPackFilePath(otaFaceName));
    }

    @JvmStatic
    public static final boolean isCwdPackFileExist(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        try {
            return new File(getCwdPackFilePath(otaFaceName)).exists();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @JvmStatic
    public static final boolean deleteCwdPackFile(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        try {
            File file = new File(getCwdPackFilePath(otaFaceName));
            if (file.exists()) {
                file.delete();
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @JvmStatic
    public static final String getDeviceCwdBaseDir() {
        return getCwdBaseDir() + INSTANCE.getDeviceUniqueCode() + File.separator + "watchFileFunction" + File.separator;
    }

    @JvmStatic
    public static final String getDeviceCwdParentDir(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        return getDeviceCwdBaseDir() + otaFaceName + File.separator;
    }

    @JvmStatic
    public static final String getDeviceCwdPackPath(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        return getDeviceCwdParentDir(otaFaceName) + otaFaceName + ".zip";
    }

    @JvmStatic
    public static final boolean deleteDeviceCwdFile(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        try {
            File file = new File(getDeviceCwdParentDir(otaFaceName));
            if (file.exists() && file.isDirectory()) {
                FileUtil.deleteDirectory(file);
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @JvmStatic
    public static final boolean clearCwd(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        INSTANCE.logP("clearCwd：deviceId = " + INSTANCE.getDeviceUniqueCode() + ", otaFaceName = " + otaFaceName);
        boolean zDeleteDeviceCwdFile = deleteDeviceCwdFile(otaFaceName);
        INSTANCE.logP("deleteDeviceCwdFile：" + zDeleteDeviceCwdFile);
        boolean zDeleteCwdPackFile = deleteCwdPackFile(otaFaceName);
        INSTANCE.logP("deleteCwdPackFile：" + zDeleteCwdPackFile);
        return zDeleteDeviceCwdFile && zDeleteCwdPackFile;
    }

    @JvmStatic
    public static final boolean isDeviceCwdDirExist(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        try {
            if (INSTANCE.checkDeviceNull()) {
                return false;
            }
            return new File(getDeviceCwdParentDir(otaFaceName)).exists();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @JvmStatic
    public static final boolean isDeviceCwdPackExist(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        try {
            return new File(getDeviceCwdPackPath(otaFaceName)).exists();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @JvmStatic
    public static final String getAppJsonPath(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        return getDeviceCwdParentDir(otaFaceName) + File.separator + "app.json";
    }

    @JvmStatic
    public static final String getDeviceBackgroundImagePath(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        String str = getDeviceCwdParentDir(otaFaceName) + PREVIEW_IMAGE_DIR + TEMP_BG_PNG;
        return new File(str).exists() ? str : "";
    }

    @JvmStatic
    public static final String getDevicePreviewImagePath(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        if (INSTANCE.checkDeviceNull()) {
            return "";
        }
        String str = getDeviceCwdParentDir(otaFaceName) + PREVIEW_IMAGE_DIR + "preview.png";
        return new File(str).exists() ? str : "";
    }

    @JvmStatic
    public static final long getDeviceDialSize(String otaFaceName) {
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        if (isDeviceCwdDirExist(otaFaceName)) {
            return FileUtil.sizeOfDirectory(new File(getDeviceCwdPackPath(otaFaceName)));
        }
        return 0L;
    }

    @JvmStatic
    public static final boolean copyDir(String srcDir, String desDir) {
        Intrinsics.checkParameterIsNotNull(srcDir, "srcDir");
        Intrinsics.checkParameterIsNotNull(desDir, "desDir");
        return FileDialDefinedUtil.copyFolder(srcDir, desDir);
    }

    @JvmStatic
    public static final void copyFile(String src, String des) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        Intrinsics.checkParameterIsNotNull(des, "des");
        FileDialDefinedUtil.copyFile(src, des);
    }

    @JvmStatic
    public static final void deleteFile(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        FileUtil.deleteFile(path);
    }

    @JvmStatic
    public static final boolean replaceCwrBgImageWithTemp(String src, String des, boolean isUseBmpBg) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        Intrinsics.checkParameterIsNotNull(des, "des");
        try {
            if (isUseBmpBg) {
                return convertImage2Bmp(src, des);
            }
            if (!new File(src).exists()) {
                return false;
            }
            copyFile(src, des);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @JvmStatic
    public static final boolean replaceCwrPreviewImageWithTemp(String src, String des, boolean isUseBmpBg) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        Intrinsics.checkParameterIsNotNull(des, "des");
        try {
            if (isUseBmpBg) {
                return convertImage2Bmp(src, des);
            }
            if (!new File(src).exists()) {
                return false;
            }
            copyFile(src, des);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @JvmStatic
    public static final boolean convertImage2Bmp(String src, String des) {
        File parentFile;
        Intrinsics.checkParameterIsNotNull(src, "src");
        Intrinsics.checkParameterIsNotNull(des, "des");
        try {
            if (new File(src).exists()) {
                File file = new File(des);
                if (!file.exists() && (parentFile = file.getParentFile()) != null) {
                    parentFile.mkdirs();
                }
                return BLEManager.png2Bmp(src, des, 5) == 0;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    @JvmStatic
    public static final boolean convertImage2BmpByUtil(String src, String des) {
        File parentFile;
        Intrinsics.checkParameterIsNotNull(src, "src");
        Intrinsics.checkParameterIsNotNull(des, "des");
        try {
            if (!new File(src).exists()) {
                return false;
            }
            File file = new File(des);
            if (!file.exists() && (parentFile = file.getParentFile()) != null) {
                parentFile.mkdirs();
            }
            AndroidBmpUtil.save(BitmapFactory.decodeFile(src), des);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @JvmStatic
    public static final Pair<Integer, Integer> getImageSize(String imagePath) {
        Intrinsics.checkParameterIsNotNull(imagePath, "imagePath");
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(imagePath, options);
            options.inSampleSize = 1;
            options.inJustDecodeBounds = false;
            return new Pair<>(Integer.valueOf(options.outWidth), Integer.valueOf(options.outHeight));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @JvmStatic
    public static final String colorTo16(String color) {
        Intrinsics.checkParameterIsNotNull(color, "color");
        if (Long.parseLong(StringsKt.replace$default(color, "#", "", false, 4, (Object) null), CharsKt.checkRadix(16)) > Long.parseLong("FFFFFF", CharsKt.checkRadix(16))) {
            return StringsKt.replace$default(color, "#", "0x", false, 4, (Object) null);
        }
        return StringsKt.replace$default(color, "#", "0xFF", false, 4, (Object) null);
    }

    @JvmStatic
    public static final long colorTo16Long(String color) {
        Intrinsics.checkParameterIsNotNull(color, "color");
        return Long.parseLong(StringsKt.replace$default(color, "#", "", false, 4, (Object) null), CharsKt.checkRadix(16));
    }

    @Override // com.ido.ble.callback.DeviceParaChangedCallBack.ICallBack
    public void onChanged(DeviceChangedPara deviceChangedPara) {
        if (deviceChangedPara != null) {
            if (deviceChangedPara.dataType == 26) {
                isTelephone = true;
                logP("蓝牙正在通话");
            }
            if (deviceChangedPara.dataType == 27) {
                isTelephone = false;
                logP("蓝牙结束通话");
            }
        }
    }

    /* JADX INFO: renamed from: com.ido.life.util.WallpaperDialManager$onSdkInitComplete$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: WallpaperDialManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.util.WallpaperDialManager$onSdkInitComplete$1", f = "WallpaperDialManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04671 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C04671(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04671 c04671 = new C04671(completion);
            c04671.p$ = (CoroutineScope) obj;
            return c04671;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04671) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            WallpaperDialManager wallpaperDialManager = WallpaperDialManager.INSTANCE;
            WallpaperDialManager.mCurrentDevice = WallpaperDialManager.INSTANCE.getDeviceInfo();
            return Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onSdkInitComplete(String macAddress) {
        super.onSdkInitComplete(macAddress);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C04671(null), 2, null);
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onUnBind(String macAddress) {
        Intrinsics.checkParameterIsNotNull(macAddress, "macAddress");
        super.onUnBind(macAddress);
        deleteLocalWallpaper(macAddress);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public final void onDialDeleteEvent(BaseMessage<String> msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (msg.getType() != 914 || checkDeviceNull() || TextUtils.isEmpty(msg.getData()) || msg.getData().equals(mOperateDialName)) {
            return;
        }
        logP("onDialDeleteEvent，dialName：" + msg.getData());
        String data = msg.getData();
        Intrinsics.checkExpressionValueIsNotNull(data, "msg.data");
        deleteDeviceCwdFile(data);
    }

    /* JADX INFO: renamed from: com.ido.life.util.WallpaperDialManager$deleteLocalWallpaper$1, reason: invalid class name */
    /* JADX INFO: compiled from: WallpaperDialManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.util.WallpaperDialManager$deleteLocalWallpaper$1", f = "WallpaperDialManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $macAddress;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Continuation continuation) {
            super(2, continuation);
            this.$macAddress = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$macAddress, completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            try {
                String wallPaperDialPath = WallpaperDialManager.INSTANCE.getWallPaperDialPath(this.$macAddress);
                WallpaperDialManager.INSTANCE.logP("deleteLocalWallpaper，path = " + wallPaperDialPath);
                if (!TextUtils.isEmpty(wallPaperDialPath)) {
                    File file = new File(wallPaperDialPath);
                    if (!file.exists()) {
                        WallpaperDialManager.INSTANCE.logP("设备本地壁纸表盘不存在");
                    } else {
                        file.delete();
                        WallpaperDialManager.INSTANCE.logP("设备本地壁纸表盘删除成功！");
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                WallpaperDialManager.INSTANCE.logP("设备本地壁纸表盘删除失败：" + e2);
            }
            return Unit.INSTANCE;
        }
    }

    private final void deleteLocalWallpaper(String macAddress) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass1(macAddress, null), 2, null);
    }

    public final String getWallPaperDialPath(String macAddress) {
        Intrinsics.checkParameterIsNotNull(macAddress, "macAddress");
        String str = BaseDialPresenter.WALL_PAPER_DIAL_FOLDER_PATH + macAddress;
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        return str + "/wallpaper.png";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BLEDevice getDeviceInfo() {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo == null) {
            currentDeviceInfo = new BLEDevice();
        }
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            logP("获取设备信息--basicInfo" + basicInfo);
            currentDeviceInfo.mDeviceId = basicInfo.deivceId;
            currentDeviceInfo.version = basicInfo.firmwareVersion;
            currentDeviceInfo.type = basicInfo.dev_type == 1 ? 3 : 4;
            if (currentDeviceInfo.mDeviceId <= 0) {
                logP("DeviceId异常，device.mDeviceId=" + currentDeviceInfo.mDeviceId + " ,basicInfo.deivceId=" + basicInfo.deivceId);
                currentDeviceInfo.mDeviceId = basicInfo.deivceId;
            }
        }
        currentDeviceInfo.type = getDeviceType(currentDeviceInfo);
        logP("获取设备信息--device" + currentDeviceInfo);
        return currentDeviceInfo;
    }

    private final int getDeviceType(BLEDevice device) {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            if (basicInfo.dev_type == 1) {
                return 3;
            }
            if (basicInfo.shape == 1) {
                return 5;
            }
        } else if (device.type == 2) {
            return 3;
        }
        return 4;
    }

    private final boolean checkDeviceNull() {
        return mCurrentDevice == null;
    }
}