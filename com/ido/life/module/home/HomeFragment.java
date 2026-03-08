package com.ido.life.module.home;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.SpannableStringBuilder;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.common.IdoApp;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.adapter.HomeAdapter;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseFragment;
import com.ido.life.base.BaseMessage;
import com.ido.life.base.IDeviceDataListener;
import com.ido.life.bean.BarChartPoint;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.bean.MainData;
import com.ido.life.ble.BleHelper;
import com.ido.life.ble.SyncDeviceDataProxy;
import com.ido.life.boatservice.DataUploadService;
import com.ido.life.constants.Constants;
import com.ido.life.customview.HomeDeviceStateView;
import com.ido.life.customview.HomeHistoryProgress;
import com.ido.life.customview.HomeSyncProgressView;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.maincard.MainPannelCircleView;
import com.ido.life.customview.recyclerview.CommRefreshHeader;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.HealthVolumeData;
import com.ido.life.database.model.ServerBloodOxyDayData;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.enums.UserModelEnum;
import com.ido.life.module.bind.ChoiceBlueTypeActivity;
import com.ido.life.module.bind.scan.ScanCodeActivity;
import com.ido.life.module.device.activity.DeviceUpgradeNewActivity;
import com.ido.life.module.home.chartdetail.vertical.ChartDetailActivity;
import com.ido.life.module.home.customcard.CustomCardActivity;
import com.ido.life.module.nodatapage.bind.HasBindNoDataActivity;
import com.ido.life.module.nodatapage.unbind.UnBindNoDataActivity;
import com.ido.life.module.sport.map.BaseMap;
import com.ido.life.module.sport.map.MapFactory;
import com.ido.life.module.user.emailcheck.CheckEmailActivity;
import com.ido.life.syncdownload.DataDownLoadService;
import com.ido.life.util.ConnectLogHelper;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.viewholder.BaseHomeItemViewHolder;
import com.ido.life.viewholder.HomePannelViewHolder;
import com.ido.smartrefresh.layout.SmartRefreshLayout;
import com.ido.smartrefresh.layout.api.RefreshLayout;
import com.ido.smartrefresh.layout.listener.OnRefreshListener;
import com.ido.smartrefresh.layoutkernel.constant.RefreshState;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: HomeFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¤\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0018\u0018\u0000 Ô\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u0005:\u0006Ô\u0001Õ\u0001Ö\u0001B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010.\u001a\u00020/H\u0002J\b\u00100\u001a\u00020/H\u0016J\b\u00101\u001a\u00020)H\u0002J\b\u00102\u001a\u00020/H\u0002J\b\u00103\u001a\u00020/H\u0002J\b\u00104\u001a\u00020/H\u0002J\b\u00105\u001a\u00020/H\u0002J\b\u00106\u001a\u00020)H\u0002J\n\u00107\u001a\u0004\u0018\u000108H\u0016J\u0010\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<H\u0016J\b\u0010=\u001a\u00020)H\u0016J:\u0010>\u001a4\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020)\u0018\u00010?\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020A\u0018\u00010@\u0018\u00010?\u0018\u00010?H\u0016J \u0010B\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010C\u0012\f\u0012\n\u0012\u0004\u0012\u00020D\u0018\u00010@\u0018\u00010?H\u0016J\b\u0010E\u001a\u00020\u0016H\u0002J\n\u0010F\u001a\u0004\u0018\u00010GH\u0016J\b\u0010H\u001a\u00020)H\u0014J\u0014\u0010I\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0002\b\u0003\u0018\u00010\u001aH\u0016J\n\u0010J\u001a\u0004\u0018\u00010KH\u0016J\b\u0010L\u001a\u00020&H\u0002J\n\u0010M\u001a\u0004\u0018\u00010NH\u0016J\u0016\u0010O\u001a\u0010\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020)\u0018\u00010?H\u0016J\n\u0010P\u001a\u0004\u0018\u00010QH\u0016J\"\u0010R\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020)0?\u0012\u0004\u0012\u00020)\u0018\u00010?H\u0016J \u0010S\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020)0?\u0012\u0004\u0012\u00020)0?H\u0016J(\u0010T\u001a\"\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00100?\u0012\n\u0012\b\u0012\u0004\u0012\u00020U0@\u0018\u00010?H\u0016J\"\u0010V\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020)0?\u0012\u0004\u0012\u00020)\u0018\u00010?H\u0016J\b\u0010W\u001a\u00020XH\u0016J\u0010\u0010Y\u001a\n\u0012\u0004\u0012\u00020Z\u0018\u00010@H\u0016J\u0016\u0010[\u001a\u00020/2\f\u0010\\\u001a\b\u0012\u0002\b\u0003\u0018\u00010]H\u0016J\b\u0010^\u001a\u00020\u0010H\u0016J\b\u0010_\u001a\u00020\u0010H\u0016J\b\u0010`\u001a\u00020\u0010H\u0016J\b\u0010a\u001a\u00020\u0010H\u0016J\b\u0010b\u001a\u00020\u0010H\u0016J\b\u0010c\u001a\u00020\u0010H\u0016J\b\u0010d\u001a\u00020\u0010H\u0016J\b\u0010e\u001a\u00020\u0010H\u0016J\b\u0010f\u001a\u00020\u0010H\u0016J\b\u0010g\u001a\u00020\u0010H\u0016J\b\u0010h\u001a\u00020\u0010H\u0016J\b\u0010i\u001a\u00020\u0010H\u0016J\b\u0010j\u001a\u00020/H\u0016J\b\u0010k\u001a\u00020/H\u0016J\b\u0010l\u001a\u00020/H\u0014J\b\u0010m\u001a\u00020/H\u0002J\u0012\u0010n\u001a\u00020/2\b\u0010o\u001a\u0004\u0018\u00010pH\u0002J\b\u0010q\u001a\u00020/H\u0002J\b\u0010r\u001a\u00020/H\u0014J!\u0010s\u001a\u00020\u00102\u0012\u0010t\u001a\n\u0012\u0006\b\u0001\u0012\u00020:0u\"\u00020:H\u0002¢\u0006\u0002\u0010vJ\b\u0010w\u001a\u00020)H\u0002J\b\u0010x\u001a\u00020)H\u0002J\b\u0010y\u001a\u00020\u0010H\u0014J\"\u0010z\u001a\u00020/2\u0006\u0010{\u001a\u00020)2\u0006\u0010|\u001a\u00020)2\b\u0010}\u001a\u0004\u0018\u00010~H\u0016J\b\u0010\u007f\u001a\u00020/H\u0016J\t\u0010\u0080\u0001\u001a\u00020/H\u0016J\u0012\u0010\u0081\u0001\u001a\u00020/2\u0007\u0010\u0082\u0001\u001a\u00020\u001bH\u0016J\t\u0010\u0083\u0001\u001a\u00020/H\u0016J\t\u0010\u0084\u0001\u001a\u00020/H\u0016J\t\u0010\u0085\u0001\u001a\u00020/H\u0016J\t\u0010\u0086\u0001\u001a\u00020/H\u0016J\t\u0010\u0087\u0001\u001a\u00020/H\u0016J\t\u0010\u0088\u0001\u001a\u00020/H\u0016J\u001a\u0010\u0089\u0001\u001a\u00020/2\u000f\u0010\u008a\u0001\u001a\n\u0012\u0004\u0012\u00020,\u0018\u00010+H\u0016J\t\u0010\u008b\u0001\u001a\u00020/H\u0016J\u0015\u0010\u008c\u0001\u001a\u00020/2\n\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u0001H\u0016J\t\u0010\u008f\u0001\u001a\u00020/H\u0016J\t\u0010\u0090\u0001\u001a\u00020/H\u0016J\t\u0010\u0091\u0001\u001a\u00020/H\u0016J\t\u0010\u0092\u0001\u001a\u00020/H\u0016J\u0013\u0010\u0093\u0001\u001a\u00020/2\b\u0010\u0094\u0001\u001a\u00030\u0095\u0001H\u0016J/\u0010\u0096\u0001\u001a\u00020/2\u0006\u0010{\u001a\u00020)2\f\u0010t\u001a\b\u0012\u0004\u0012\u00020:0u2\b\u0010\u0097\u0001\u001a\u00030\u0098\u0001H\u0016¢\u0006\u0003\u0010\u0099\u0001J\t\u0010\u009a\u0001\u001a\u00020/H\u0016J\t\u0010\u009b\u0001\u001a\u00020/H\u0016J\t\u0010\u009c\u0001\u001a\u00020/H\u0016J\u0012\u0010\u009d\u0001\u001a\u00020/2\u0007\u0010\u009e\u0001\u001a\u00020)H\u0016J\t\u0010\u009f\u0001\u001a\u00020/H\u0016J\t\u0010 \u0001\u001a\u00020/H\u0016J\t\u0010¡\u0001\u001a\u00020/H\u0016J\u001c\u0010¢\u0001\u001a\u00020/2\u0007\u0010£\u0001\u001a\u00020\u001b2\b\u0010o\u001a\u0004\u0018\u00010pH\u0016J\t\u0010¤\u0001\u001a\u00020/H\u0014J\t\u0010¥\u0001\u001a\u00020/H\u0002J\t\u0010¦\u0001\u001a\u00020/H\u0016J\t\u0010§\u0001\u001a\u00020/H\u0016J\t\u0010¨\u0001\u001a\u00020/H\u0016J\t\u0010©\u0001\u001a\u00020\u0010H\u0016J\t\u0010ª\u0001\u001a\u00020/H\u0014J\t\u0010«\u0001\u001a\u00020/H\u0016J\t\u0010¬\u0001\u001a\u00020\u0010H\u0016J\t\u0010\u00ad\u0001\u001a\u00020/H\u0016J\t\u0010®\u0001\u001a\u00020/H\u0016J\t\u0010¯\u0001\u001a\u00020/H\u0016J\t\u0010°\u0001\u001a\u00020\u0010H\u0016J\t\u0010±\u0001\u001a\u00020\u0010H\u0016J\t\u0010²\u0001\u001a\u00020/H\u0016J\t\u0010³\u0001\u001a\u00020\u0010H\u0016J\t\u0010´\u0001\u001a\u00020/H\u0016J\t\u0010µ\u0001\u001a\u00020/H\u0016J\t\u0010¶\u0001\u001a\u00020/H\u0016J\t\u0010·\u0001\u001a\u00020/H\u0016J\t\u0010¸\u0001\u001a\u00020\u0010H\u0016J\t\u0010¹\u0001\u001a\u00020/H\u0002J\t\u0010º\u0001\u001a\u00020/H\u0002J\t\u0010»\u0001\u001a\u00020/H\u0002J\t\u0010¼\u0001\u001a\u00020\u0010H\u0002J\u0015\u0010½\u0001\u001a\u00020/2\n\u0010¾\u0001\u001a\u0005\u0018\u00010¿\u0001H\u0002J\t\u0010À\u0001\u001a\u00020/H\u0002J\u0015\u0010Á\u0001\u001a\u00020/2\n\u0010¾\u0001\u001a\u0005\u0018\u00010¿\u0001H\u0016J\t\u0010Â\u0001\u001a\u00020/H\u0016J\t\u0010Ã\u0001\u001a\u00020/H\u0016J\t\u0010Ä\u0001\u001a\u00020/H\u0002J\t\u0010Å\u0001\u001a\u00020/H\u0016J\t\u0010Æ\u0001\u001a\u00020/H\u0016J\t\u0010Ç\u0001\u001a\u00020/H\u0016J\t\u0010È\u0001\u001a\u00020/H\u0002J\t\u0010É\u0001\u001a\u00020/H\u0016J\t\u0010Ê\u0001\u001a\u00020/H\u0016J\t\u0010Ë\u0001\u001a\u00020/H\u0016J\t\u0010Ì\u0001\u001a\u00020/H\u0016J\t\u0010Í\u0001\u001a\u00020/H\u0002J\t\u0010Î\u0001\u001a\u00020/H\u0016J\t\u0010Ï\u0001\u001a\u00020/H\u0016J\t\u0010Ð\u0001\u001a\u00020/H\u0002J\t\u0010Ñ\u0001\u001a\u00020/H\u0016J\u0012\u0010Ò\u0001\u001a\u00020/2\u0007\u0010Ó\u0001\u001a\u00020\u0010H\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0018\u00010\u0018R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0002\b\u0003\u0018\u00010\u001aX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010\u001bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006×\u0001"}, d2 = {"Lcom/ido/life/module/home/HomeFragment;", "Lcom/ido/life/base/BaseFragment;", "Lcom/ido/life/module/home/HomeFragmentPresenter;", "Lcom/ido/life/module/home/IHomeView;", "Lcom/ido/smartrefresh/layout/listener/OnRefreshListener;", "Lcom/ido/life/base/IDeviceDataListener;", "()V", "mAdapter", "Lcom/ido/life/adapter/HomeAdapter;", "mGlobalListener", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "mGpsOpenDialog", "Lcom/ido/common/dialog/CommBottomConfirmDialog;", "mGradientDrawable", "Landroid/graphics/drawable/GradientDrawable;", "mHasShow", "", "mHistoryDownloadProgressView", "Landroid/widget/ProgressBar;", "mHistoryHasDownloadView", "Landroid/widget/TextView;", "mHistorySyncInfoDialog", "Landroid/app/Dialog;", "mHomeScrollListener", "Lcom/ido/life/module/home/HomeFragment$HomeScrollListener;", "mMapModel", "Lcom/ido/life/module/sport/map/BaseMap;", "Landroid/view/View;", "getMMapModel", "()Lcom/ido/life/module/sport/map/BaseMap;", "setMMapModel", "(Lcom/ido/life/module/sport/map/BaseMap;)V", "mMapView", "getMMapView", "()Landroid/view/View;", "setMMapView", "(Landroid/view/View;)V", "mMorePop", "Landroid/widget/PopupWindow;", "mShouldCheckEmailBindState", "mStepUIHeight", "", "mTypeDataList", "Ljava/util/LinkedList;", "Lcom/ido/life/bean/MainData;", "mUnbindUIHeight", "addDeviceUnBindView", "", "backFromBackground", "caluteBackgroundSize", "checkGpsSwitch", "connectFailed", "connectSuccess", "connecting", "findStepCardPosition", "getAmbientNoiseData", "Lcom/ido/life/database/model/HealthVolumeData;", "getDateShowByTimeStamp", "", "timeStamp", "", "getHeaderCount", "getHealthPressure", "Landroid/util/Pair;", "", "Lcom/ido/life/bean/BarChartPoint;", "getHeartRateData", "Lcom/ido/life/database/model/ServerHeartRateDayData;", "Lcom/ido/life/bean/BaseCharBean;", "getHistoryInfoDialog", "getLastestSportRecord", "Lcom/ido/life/database/model/SportHealth;", "getLayoutResId", "getMap", "getMenstrual", "Lcom/ido/life/module/home/WholeLifeCycleInfo;", "getMorePopWindow", "getNearOxyData", "Lcom/ido/life/database/model/ServerBloodOxyDayData;", "getOxygenUptakeData", "getSleepData", "Lcom/ido/life/database/model/ServerSleepDayData;", "getTodayActive", "getTodayActiveTime", "getTodayStepData", "Landroid/graphics/Point;", "getTodayWalk", "getTotalDistance", "", "getWeightList", "Lcom/ido/life/database/model/WeightItemBean;", "handleMessage", "message", "Lcom/ido/life/base/BaseMessage;", "hasActivityData", "hasBloodOxyData", "hasCalorie", "hasDistance", "hasHeartRate", "hasLifeCycle", "hasLogin", "hasPressure", "hasSleepData", "hasSportRecord", "hasStepData", "hasWalkData", "historyDataLoadFailed", "historyDataLoadSuccess", "initData", "initDefaultBg", "initMap", "savedInstanceState", "Landroid/os/Bundle;", "initRecyclerView", "initView", "isDeniedByNoAsk", "permissions", "", "([Ljava/lang/String;)Z", "measureStepUIHeight", "measureUnBindUIHeight", "needEventBus", "onActivityResult", "requestCode", "resultCode", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Landroid/content/Intent;", "onBlueToothConnect", "onBlueToothDisconnect", "onClick", "v", "onConnectFailed", "onDestroyView", "onDeviceBindCrossDay", "onDeviceBindSuccess", "onDeviceRestarted", "onDeviceUnBindSuccess", "onGetCardDataList", "mainDataList", "onGetUserEmailBindStateFailed", "onInDfuMode", "device", "Lcom/ido/ble/bluetooth/device/BLEDevice;", "onInVisible", "onNeedLocationPermission", "onNeedOpenBle", "onNeedOpenGps", "onRefresh", "refreshLayout", "Lcom/ido/smartrefresh/layout/api/RefreshLayout;", "onRequestPermissionsResult", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onStart", "onStop", "onSyncFailed", "onSyncProgress", NotificationCompat.CATEGORY_PROGRESS, "onSyncSuccess", "onTargetChanged", "onUserInfoChanged", "onViewCreated", "view", "onVisible", "processBlueConnectSuccessAction", "refreshAllCard", "refreshBloodOxyCard", "refreshHeartRateCard", "refreshHeartRateTime", "refreshLanguage", "refreshMenstrualCard", "refreshOxyTime", "refreshOxygenUptakeCard", "refreshPanelCard", "refreshPressureCard", "refreshPressureTime", "refreshRecordTime", "refreshSleepCard", "refreshSleepTime", "refreshSportCard", "refreshStepCard", "refreshVolumeCard", "refreshWeightCard", "refreshWeightTime", "removeDeviceUnBindView", "resetBackgroundStyle", "showBlueToothPermissionDialog", "showDeviceUnbindUI", "showMedalGetSuccess", "modelEnum", "Lcom/ido/life/enums/UserModelEnum;", "showOpenGpsDialog", "showUserMedalDialog", "startLoadHistoryData", "startRefresh", "startSyncData", "startUpdateTime", "syncActiveDataSuccess", "syncBloodDataSuccess", "syncFailed", "syncHeartRateDataSuccess", "syncPressureDataSuccess", "syncSleepDataSuccess", "syncSportDataSuccess", "syncSuccess", "syncSwimmingDataSuccess", "syncVolumeDataSuccess", "syncing", "updateHistoryPullProgress", "updateUserEmailBindState", "hasBind", "Companion", "HomeGlobalLayoutListener", "HomeScrollListener", "app_release"}, k = 1, mv = {1, 1, 16})
public final class HomeFragment extends BaseFragment<HomeFragmentPresenter> implements IHomeView, OnRefreshListener, IDeviceDataListener {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private HashMap _$_findViewCache;
    private HomeAdapter mAdapter;
    private ViewTreeObserver.OnGlobalLayoutListener mGlobalListener;
    private CommBottomConfirmDialog mGpsOpenDialog;
    private GradientDrawable mGradientDrawable;
    private boolean mHasShow;
    private ProgressBar mHistoryDownloadProgressView;
    private TextView mHistoryHasDownloadView;
    private Dialog mHistorySyncInfoDialog;
    private HomeScrollListener mHomeScrollListener;
    private BaseMap<View, ?> mMapModel;
    private View mMapView;
    private PopupWindow mMorePop;
    private int mStepUIHeight;
    private int mUnbindUIHeight;
    private boolean mShouldCheckEmailBindState = true;
    private final LinkedList<MainData> mTypeDataList = new LinkedList<>();

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View viewFindViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override // com.ido.life.base.BaseFragment
    protected boolean needEventBus() {
        return true;
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onTargetChanged() {
    }

    public static final /* synthetic */ HomeFragmentPresenter access$getMPresenter$p(HomeFragment homeFragment) {
        return (HomeFragmentPresenter) homeFragment.mPresenter;
    }

    protected final BaseMap<View, ?> getMMapModel() {
        return this.mMapModel;
    }

    protected final void setMMapModel(BaseMap<View, ?> baseMap) {
        this.mMapModel = baseMap;
    }

    protected final View getMMapView() {
        return this.mMapView;
    }

    protected final void setMMapView(View view) {
        this.mMapView = view;
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void initView() {
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_more);
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_more);
        if (linearLayout2 != null) {
            linearLayout2.setOnClickListener(this);
        }
        initRecyclerView();
        if (SPHelper.autoShowHistoryDataPullState()) {
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            if (GreenDaoUtil.queryDataPullConfigInfo(runTimeUtil.getUserId()) != null) {
                RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
                if (HomeHelperKt.historyDataPullSuccess(runTimeUtil2.getUserId())) {
                    CommonLogUtil.d(TAG, "历史数据下拉成功,显示数据下拉成功UI");
                    HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
                    if (homeFragmentPresenter != null) {
                        homeFragmentPresenter.setHistoryDataPullState(3);
                    }
                    ImageView imageView = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_sync_state_loading);
                    if (imageView != null) {
                        imageView.setVisibility(8);
                    }
                    ImageView imageView2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_sync_state_background);
                    if (imageView2 != null) {
                        imageView2.setImageResource(R.mipmap.history_data_sync_success);
                    }
                    TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_history_sync_state);
                    if (textView != null) {
                        textView.setText(getLanguageText(R.string.history_data_sync_success));
                    }
                    LinearLayout linearLayout3 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_history_sync_progress);
                    if (linearLayout3 != null) {
                        linearLayout3.setVisibility(8);
                    }
                    TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_history_sync_state);
                    if (textView2 != null) {
                        textView2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                    }
                } else {
                    CommonLogUtil.d(TAG, "历史数据下拉失败，显示数据下拉失败UI");
                    HomeFragmentPresenter homeFragmentPresenter2 = (HomeFragmentPresenter) this.mPresenter;
                    if (homeFragmentPresenter2 != null) {
                        homeFragmentPresenter2.setHistoryDataPullState(4);
                    }
                    ImageView imageView3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_sync_state_loading);
                    if (imageView3 != null) {
                        imageView3.setVisibility(8);
                    }
                    ImageView imageView4 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_sync_state_background);
                    if (imageView4 != null) {
                        imageView4.setImageResource(R.mipmap.history_data_sync_failed);
                    }
                    TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_history_sync_state);
                    if (textView3 != null) {
                        textView3.setText(getLanguageText(R.string.history_data_sync_failed));
                    }
                    HomeHistoryProgress homeHistoryProgress = (HomeHistoryProgress) _$_findCachedViewById(com.ido.life.R.id.progress_sync_history);
                    if (homeHistoryProgress != null) {
                        homeHistoryProgress.setMax(100);
                    }
                    P p = this.mPresenter;
                    if (p == 0) {
                        Intrinsics.throwNpe();
                    }
                    int historyDataDownloadProgress = ((HomeFragmentPresenter) p).getHistoryDataDownloadProgress();
                    HomeHistoryProgress homeHistoryProgress2 = (HomeHistoryProgress) _$_findCachedViewById(com.ido.life.R.id.progress_sync_history);
                    if (homeHistoryProgress2 != null) {
                        homeHistoryProgress2.updateProgress(historyDataDownloadProgress);
                    }
                    TextView textView4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_history_sync_progress);
                    if (textView4 != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(historyDataDownloadProgress);
                        sb.append('%');
                        textView4.setText(sb.toString());
                    }
                }
                FrameLayout frameLayout = (FrameLayout) _$_findCachedViewById(com.ido.life.R.id.lay_history_sync);
                if (frameLayout != null) {
                    frameLayout.setVisibility(0);
                }
                FrameLayout frameLayout2 = (FrameLayout) _$_findCachedViewById(com.ido.life.R.id.lay_history_sync);
                if (frameLayout2 != null) {
                    frameLayout2.setOnClickListener(this);
                }
            }
        }
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initMap(savedInstanceState);
    }

    private final void initMap(Bundle savedInstanceState) {
        if (getView() == null || ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_map)) == null) {
            return;
        }
        this.mMapView = MapFactory.getMapView(getView());
        if (this.mMapModel == null) {
            this.mMapModel = MapFactory.getMap();
        }
        BaseMap<View, ?> baseMap = this.mMapModel;
        if (baseMap != null) {
            baseMap.setActivity(this.mActivity);
        }
        BaseMap<View, ?> baseMap2 = this.mMapModel;
        if (baseMap2 != null) {
            baseMap2.initMapView(this.mMapView);
        }
        BaseMap<View, ?> baseMap3 = this.mMapModel;
        if (baseMap3 != null) {
            baseMap3.onCreate(savedInstanceState);
        }
        LinearLayout lay_map = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_map);
        Intrinsics.checkExpressionValueIsNotNull(lay_map, "lay_map");
        ViewGroup.LayoutParams layoutParams = lay_map.getLayoutParams();
        layoutParams.height = DipPixelUtil.dip2px(350.0f);
        LinearLayout lay_map2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_map);
        Intrinsics.checkExpressionValueIsNotNull(lay_map2, "lay_map");
        lay_map2.setLayoutParams(layoutParams);
        BaseMap<View, ?> baseMap4 = this.mMapModel;
        if (baseMap4 != null) {
            baseMap4.setIsRunMap(false);
        }
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void onVisible() {
        ViewTreeObserver viewTreeObserver;
        super.onVisible();
        ConnectLogHelper.saveLog(TAG, "HomeFragment onVisible");
        if (getView() == null) {
            return;
        }
        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
        if (smartRefreshLayout != null) {
            smartRefreshLayout.setOnRefreshListener(this);
        }
        HomeAdapter homeAdapter = this.mAdapter;
        if (homeAdapter != null) {
            homeAdapter.notifyDataSetChanged(true);
        }
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
        if (recyclerView != null) {
            recyclerView.setOnScrollListener(null);
        }
        if (this.mHomeScrollListener == null) {
            this.mHomeScrollListener = new HomeScrollListener();
        }
        if (this.mGradientDrawable == null) {
            CommonLogUtil.d(TAG, "重新初始化");
            RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
            if (recyclerView2 != null) {
                recyclerView2.smoothScrollToPosition(0);
            }
            initDefaultBg();
            if (this.mGlobalListener == null) {
                this.mGlobalListener = new HomeGlobalLayoutListener();
            }
            View view = getView();
            if (view != null && (viewTreeObserver = view.getViewTreeObserver()) != null) {
                viewTreeObserver.addOnGlobalLayoutListener(this.mGlobalListener);
            }
        } else {
            ImageView imageView = (ImageView) _$_findCachedViewById(com.ido.life.R.id.lay_top_bg);
            if (imageView != null) {
                imageView.setBackground(this.mGradientDrawable);
            }
        }
        RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
        if (recyclerView3 != null) {
            recyclerView3.setOnScrollListener(this.mHomeScrollListener);
        }
        if (this.mShouldCheckEmailBindState && this.mPresenter != 0) {
            this.mShouldCheckEmailBindState = false;
            HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
            if (homeFragmentPresenter != null) {
                homeFragmentPresenter.veryEmailHasBind();
            }
        }
        SyncDeviceDataProxy companion = SyncDeviceDataProxy.INSTANCE.getInstance();
        if (companion != null) {
            companion.registerDeviceDataListener(this);
        }
    }

    @Override // com.ido.common.base.BaseCoreFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
        if (homeFragmentPresenter != null) {
            homeFragmentPresenter.stopUpdateTime();
        }
        SyncDeviceDataProxy companion = SyncDeviceDataProxy.INSTANCE.getInstance();
        if (companion != null) {
            companion.unregisterDeviceDataListener(this);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
        if (homeFragmentPresenter != null) {
            homeFragmentPresenter.startUpdateTime();
        }
        SyncDeviceDataProxy companion = SyncDeviceDataProxy.INSTANCE.getInstance();
        if (companion != null) {
            companion.registerDeviceDataListener(this);
        }
    }

    @Override // com.ido.common.base.BaseCoreFragment
    public void onInVisible() {
        super.onInVisible();
        ConnectLogHelper.saveLog(TAG, "HomeFragment onInVisible");
        HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
        if (homeFragmentPresenter != null) {
            homeFragmentPresenter.stopUpdateTime();
        }
        SyncDeviceDataProxy companion = SyncDeviceDataProxy.INSTANCE.getInstance();
        if (companion != null) {
            companion.unregisterDeviceDataListener(this);
        }
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void initData() {
        super.initData();
        HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
        if (homeFragmentPresenter != null) {
            homeFragmentPresenter.getCardData();
        }
        HomeFragmentPresenter homeFragmentPresenter2 = (HomeFragmentPresenter) this.mPresenter;
        if (homeFragmentPresenter2 != null) {
            homeFragmentPresenter2.geneWeekReport();
        }
        startSyncData();
    }

    private final void checkGpsSwitch() {
        CommBottomConfirmDialog commBottomConfirmDialog;
        CommBottomConfirmDialog commBottomConfirmDialog2 = this.mGpsOpenDialog;
        if (commBottomConfirmDialog2 != null) {
            commBottomConfirmDialog2.dismissAllowingStateLoss();
        }
        if (BleHelper.isOpenGPS(IdoApp.getAppContext())) {
            return;
        }
        if (this.mGpsOpenDialog == null) {
            this.mGpsOpenDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.gps_open_tips), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.home.HomeFragment.checkGpsSwitch.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
                    intent.addFlags(268435456);
                    HomeFragment.this.startActivity(intent);
                }
            });
        }
        if (this.mActivity == null || (commBottomConfirmDialog = this.mGpsOpenDialog) == null) {
            return;
        }
        BaseActivity mActivity = this.mActivity;
        Intrinsics.checkExpressionValueIsNotNull(mActivity, "mActivity");
        commBottomConfirmDialog.show(mActivity.getSupportFragmentManager());
    }

    @Override // com.ido.life.base.BaseFragment
    protected void refreshLanguage() {
        super.refreshLanguage();
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_safe_verify);
        if (linearLayout != null && linearLayout.getVisibility() == 0) {
            TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_safe_verify_desc);
            if (textView != null) {
                textView.setText(getLanguageText(R.string.home_safe_verify_desc));
            }
            TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_safe_verify_action);
            if (textView2 != null) {
                textView2.setText(getLanguageText(R.string.home_verify_action));
            }
        }
        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
        if (smartRefreshLayout != null) {
            smartRefreshLayout.setReboundDuration(10);
        }
        CommRefreshHeader commRefreshHeader = (CommRefreshHeader) _$_findCachedViewById(com.ido.life.R.id.refresh_header);
        if (commRefreshHeader != null) {
            commRefreshHeader.setFinishDuration(10);
        }
        CommRefreshHeader commRefreshHeader2 = (CommRefreshHeader) _$_findCachedViewById(com.ido.life.R.id.refresh_header);
        if (commRefreshHeader2 != null) {
            commRefreshHeader2.setTextFailedWithOutRefreshing("");
        }
        CommRefreshHeader commRefreshHeader3 = (CommRefreshHeader) _$_findCachedViewById(com.ido.life.R.id.refresh_header);
        if (commRefreshHeader3 != null) {
            commRefreshHeader3.setTextFinishWithOutRefreshing("");
        }
        CommRefreshHeader commRefreshHeader4 = (CommRefreshHeader) _$_findCachedViewById(com.ido.life.R.id.refresh_header);
        if (commRefreshHeader4 != null) {
            commRefreshHeader4.setTextRefreshing("");
        }
        CommRefreshHeader commRefreshHeader5 = (CommRefreshHeader) _$_findCachedViewById(com.ido.life.R.id.refresh_header);
        if (commRefreshHeader5 != null) {
            commRefreshHeader5.setTextPulling(getLanguageText(R.string.home_main_down_update_ios));
        }
        CommRefreshHeader commRefreshHeader6 = (CommRefreshHeader) _$_findCachedViewById(com.ido.life.R.id.refresh_header);
        if (commRefreshHeader6 != null) {
            commRefreshHeader6.setTextRelease(getLanguageText(R.string.home_main_down_update_ios));
        }
        CommRefreshHeader commRefreshHeader7 = (CommRefreshHeader) _$_findCachedViewById(com.ido.life.R.id.refresh_header);
        if (commRefreshHeader7 != null) {
            commRefreshHeader7.setProgressDrawable(null);
        }
        CommRefreshHeader commRefreshHeader8 = (CommRefreshHeader) _$_findCachedViewById(com.ido.life.R.id.refresh_header);
        if (commRefreshHeader8 != null) {
            commRefreshHeader8.setProgressViewVisible(8);
        }
    }

    private final void initRecyclerView() {
        this.mTypeDataList.clear();
        GridLayoutManager gridLayoutManager = new GridLayoutManager((Context) this.mActivity, 2, 1, false);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
        if (recyclerView2 != null) {
            recyclerView2.setItemAnimator((RecyclerView.ItemAnimator) null);
        }
        this.mAdapter = new HomeAdapter(this.mActivity, this.mTypeDataList, this);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.ido.life.module.home.HomeFragment.initRecyclerView.1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int position) {
                Object obj = HomeFragment.this.mTypeDataList.get(position);
                Intrinsics.checkExpressionValueIsNotNull(obj, "mTypeDataList[position]");
                int viewType = ((MainData) obj).getViewType();
                return (viewType == 10 || viewType == 2 || viewType == 1 || viewType == 0) ? 2 : 1;
            }
        });
        RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
        if (recyclerView3 != null) {
            recyclerView3.setAdapter(this.mAdapter);
        }
    }

    @Override // com.ido.common.base.BaseCoreFragment, androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkParameterIsNotNull(permissions, "permissions");
        Intrinsics.checkParameterIsNotNull(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != 300) {
            if (requestCode == 400) {
                if (PermissionUtil.grantedPermission(grantResults)) {
                    CommonLogUtil.printAndSave("连接同步流程-->=>蓝牙权限获取成功");
                    startSyncData();
                    return;
                }
                CommonLogUtil.printAndSave("连接同步流程-->=>蓝牙权限获取失败");
                connectFailed();
                SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
                if (smartRefreshLayout != null) {
                    smartRefreshLayout.finishRefresh(10);
                    return;
                }
                return;
            }
            if (requestCode == 503 && grantResults.length == 1 && grantResults[0] == 0) {
                Object systemService = this.mActivity.getSystemService("vibrator");
                if (systemService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.os.Vibrator");
                }
                Vibrator vibrator = (Vibrator) systemService;
                if (vibrator != null && vibrator.hasVibrator()) {
                    vibrator.vibrate(2000L);
                    return;
                } else {
                    CommonLogUtil.printAndSave("连接同步流程-->=>设备不支持震动");
                    return;
                }
            }
            return;
        }
        String[] locationPermission = PermissionUtil.getLocationPermission();
        if (checkSelfPermission((String[]) Arrays.copyOf(locationPermission, locationPermission.length))) {
            CommonLogUtil.printAndSave("连接同步流程-->=>定位权限请求成功");
            startSyncData();
            return;
        }
        CommonLogUtil.printAndSave("连接同步流程-->=>定位权限获取失败,继续尝试连接设备或者同步数据");
        if (this.mPresenter != 0) {
            P p = this.mPresenter;
            if (p == 0) {
                Intrinsics.throwNpe();
            }
            if (((HomeFragmentPresenter) p).isBindAndConnected()) {
                syncing();
                P p2 = this.mPresenter;
                if (p2 == 0) {
                    Intrinsics.throwNpe();
                }
                ((HomeFragmentPresenter) p2).syncDeviceData(false);
                return;
            }
            P p3 = this.mPresenter;
            if (p3 == 0) {
                Intrinsics.throwNpe();
            }
            if (((HomeFragmentPresenter) p3).isBind()) {
                SmartRefreshLayout smartRefreshLayout2 = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
                if ((smartRefreshLayout2 != null ? smartRefreshLayout2.getState() : null) == RefreshState.Refreshing) {
                    connecting();
                }
                P p4 = this.mPresenter;
                if (p4 == 0) {
                    Intrinsics.throwNpe();
                }
                ((HomeFragmentPresenter) p4).connectDevice();
            }
        }
    }

    private final void startSyncData() {
        String[] onlyBluetoothPermission = PermissionUtil.getOnlyBluetoothPermission();
        if (!checkSelfPermission((String[]) Arrays.copyOf(onlyBluetoothPermission, onlyBluetoothPermission.length))) {
            CommonLogUtil.printAndSave("连接同步流程-->=>申请蓝牙权限");
            requestPermissions(PermissionUtil.getOnlyBluetoothPermission(), 400);
            return;
        }
        if (!BleHelper.isBluetoothOpen()) {
            CommonLogUtil.printAndSave("连接同步流程-->=>开启蓝牙开关");
            BleHelper.openBLE(this);
            return;
        }
        String[] locationPermission = PermissionUtil.getLocationPermission();
        if (!checkSelfPermission((String[]) Arrays.copyOf(locationPermission, locationPermission.length))) {
            CommonLogUtil.printAndSave("连接同步流程-->=>申请定位权限");
            requestPermissions(PermissionUtil.getLocationPermission(), 300);
            return;
        }
        if (!BleHelper.isOpenGPS(this.mActivity)) {
            CommonLogUtil.printAndSave("连接同步流程-->=>申请GPS开关");
            checkGpsSwitch();
            return;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        if (((HomeFragmentPresenter) p).isBindAndConnected()) {
            StringBuilder sb = new StringBuilder();
            sb.append("连接同步流程-->=>BindAndConnected, state is Refreshing ");
            SmartRefreshLayout refreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
            Intrinsics.checkExpressionValueIsNotNull(refreshLayout, "refreshLayout");
            sb.append(refreshLayout.getState() == RefreshState.Refreshing);
            CommonLogUtil.printAndSave(sb.toString());
            SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
            if ((smartRefreshLayout != null ? smartRefreshLayout.getState() : null) == RefreshState.Refreshing) {
                syncing();
                P p2 = this.mPresenter;
                if (p2 == 0) {
                    Intrinsics.throwNpe();
                }
                ((HomeFragmentPresenter) p2).syncDeviceData(false);
                return;
            }
            SmartRefreshLayout smartRefreshLayout2 = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
            if (smartRefreshLayout2 != null) {
                smartRefreshLayout2.autoRefresh();
                return;
            }
            return;
        }
        P p3 = this.mPresenter;
        if (p3 == 0) {
            Intrinsics.throwNpe();
        }
        if (!((HomeFragmentPresenter) p3).isBindDevice()) {
            CommonLogUtil.printAndSave("连接同步流程-->=>no bind");
            return;
        }
        P p4 = this.mPresenter;
        if (p4 == 0) {
            Intrinsics.throwNpe();
        }
        if (((HomeFragmentPresenter) p4).isConnected()) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("连接同步流程-->=>no Connected, state is Refreshing ");
        SmartRefreshLayout smartRefreshLayout3 = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
        sb2.append((smartRefreshLayout3 != null ? smartRefreshLayout3.getState() : null) == RefreshState.Refreshing);
        CommonLogUtil.printAndSave(sb2.toString());
        SmartRefreshLayout smartRefreshLayout4 = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
        if ((smartRefreshLayout4 != null ? smartRefreshLayout4.getState() : null) == RefreshState.Refreshing) {
            connecting();
            P p5 = this.mPresenter;
            if (p5 == 0) {
                Intrinsics.throwNpe();
            }
            ((HomeFragmentPresenter) p5).connectDevice();
            return;
        }
        SmartRefreshLayout smartRefreshLayout5 = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
        if (smartRefreshLayout5 != null) {
            smartRefreshLayout5.autoRefresh();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeDeviceUnBindView() {
        ViewTreeObserver viewTreeObserver;
        LinkedList<MainData> linkedList = this.mTypeDataList;
        if (linkedList == null || linkedList.size() <= 0 || this.mAdapter == null) {
            return;
        }
        MainData mainData = this.mTypeDataList.get(0);
        Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[0]");
        if (mainData.getViewType() == 0) {
            this.mTypeDataList.removeFirst();
            HomeAdapter homeAdapter = this.mAdapter;
            if (homeAdapter == null) {
                Intrinsics.throwNpe();
            }
            homeAdapter.notifyItemRemoved(0);
            if (this.mGlobalListener == null) {
                this.mGlobalListener = new HomeGlobalLayoutListener();
            }
            View view = getView();
            if (view == null || (viewTreeObserver = view.getViewTreeObserver()) == null) {
                return;
            }
            viewTreeObserver.addOnGlobalLayoutListener(this.mGlobalListener);
        }
    }

    private final void addDeviceUnBindView() {
        LinkedList<MainData> linkedList = this.mTypeDataList;
        if (linkedList == null || linkedList.size() <= 0 || this.mAdapter == null) {
            return;
        }
        MainData mainData = this.mTypeDataList.get(0);
        Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[0]");
        if (mainData.getViewType() != 0) {
            this.mTypeDataList.addFirst(new MainData(0));
            HomeAdapter homeAdapter = this.mAdapter;
            if (homeAdapter == null) {
                Intrinsics.throwNpe();
            }
            homeAdapter.notifyItemInserted(0);
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
            if (recyclerView != null) {
                recyclerView.scrollToPosition(0);
            }
            this.mHasShow = true;
        }
    }

    private final synchronized void syncing() {
        SmartRefreshLayout smartRefreshLayout;
        CommonLogUtil.d(TAG, "数据正在同步中。");
        SmartRefreshLayout smartRefreshLayout2 = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
        if ((smartRefreshLayout2 != null ? smartRefreshLayout2.getState() : null) == RefreshState.Refreshing && (smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout)) != null) {
            smartRefreshLayout.finishRefresh(10);
        }
        HomeDeviceStateView homeDeviceStateView = (HomeDeviceStateView) _$_findCachedViewById(com.ido.life.R.id.home_device_state);
        if (homeDeviceStateView != null) {
            homeDeviceStateView.syncing();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void syncFailed() {
        SmartRefreshLayout smartRefreshLayout;
        CommonLogUtil.d(TAG, "数据同步失败。");
        SmartRefreshLayout smartRefreshLayout2 = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
        if ((smartRefreshLayout2 != null ? smartRefreshLayout2.getState() : null) == RefreshState.Refreshing && (smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout)) != null) {
            smartRefreshLayout.finishRefresh(10);
        }
        HomeDeviceStateView homeDeviceStateView = (HomeDeviceStateView) _$_findCachedViewById(com.ido.life.R.id.home_device_state);
        if (homeDeviceStateView != null) {
            homeDeviceStateView.syncFailed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void syncSuccess() {
        SmartRefreshLayout smartRefreshLayout;
        CommonLogUtil.d(TAG, "数据同步成功。");
        SmartRefreshLayout smartRefreshLayout2 = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
        if ((smartRefreshLayout2 != null ? smartRefreshLayout2.getState() : null) == RefreshState.Refreshing && (smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout)) != null) {
            smartRefreshLayout.finishRefresh(10);
        }
        HomeDeviceStateView homeDeviceStateView = (HomeDeviceStateView) _$_findCachedViewById(com.ido.life.R.id.home_device_state);
        if (homeDeviceStateView != null) {
            homeDeviceStateView.syncSuccess();
        }
    }

    private final synchronized void connecting() {
        SmartRefreshLayout refreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
        Intrinsics.checkExpressionValueIsNotNull(refreshLayout, "refreshLayout");
        if (refreshLayout.getState() == RefreshState.Refreshing) {
            ((SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout)).finishRefresh(10);
        }
        HomeDeviceStateView homeDeviceStateView = (HomeDeviceStateView) _$_findCachedViewById(com.ido.life.R.id.home_device_state);
        if (homeDeviceStateView != null) {
            homeDeviceStateView.startConnectDevice();
        }
    }

    private final synchronized void connectSuccess() {
        SmartRefreshLayout smartRefreshLayout;
        SmartRefreshLayout smartRefreshLayout2 = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
        if ((smartRefreshLayout2 != null ? smartRefreshLayout2.getState() : null) == RefreshState.Refreshing && (smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout)) != null) {
            smartRefreshLayout.finishRefresh(10);
        }
        HomeDeviceStateView homeDeviceStateView = (HomeDeviceStateView) _$_findCachedViewById(com.ido.life.R.id.home_device_state);
        if (homeDeviceStateView != null) {
            homeDeviceStateView.connectSuccess();
        }
    }

    private final synchronized void connectFailed() {
        SmartRefreshLayout smartRefreshLayout;
        SmartRefreshLayout smartRefreshLayout2 = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
        if ((smartRefreshLayout2 != null ? smartRefreshLayout2.getState() : null) == RefreshState.Refreshing && (smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout)) != null) {
            smartRefreshLayout.finishRefresh(10);
        }
        HomeDeviceStateView homeDeviceStateView = (HomeDeviceStateView) _$_findCachedViewById(com.ido.life.R.id.home_device_state);
        if (homeDeviceStateView != null) {
            homeDeviceStateView.connectFailed();
        }
    }

    private final boolean isDeniedByNoAsk(String... permissions) {
        if (getActivity() == null) {
            return false;
        }
        for (String str : permissions) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                Intrinsics.throwNpe();
            }
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, str)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.ido.smartrefresh.layout.listener.OnRefreshListener
    public void onRefresh(RefreshLayout refreshLayout) {
        Intrinsics.checkParameterIsNotNull(refreshLayout, "refreshLayout");
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("开始下拉刷新,mPresenter=");
        sb.append((HomeFragmentPresenter) this.mPresenter);
        sb.append(",img_left=");
        ImageView imageView = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_left);
        sb.append(imageView != null && imageView.getVisibility() == 0);
        CommonLogUtil.d(str, sb.toString());
        if (this.mPresenter == 0) {
            return;
        }
        String str2 = TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("onRefresh，bind ：");
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        sb2.append(((HomeFragmentPresenter) p).isBind());
        sb2.append(";    connected ：");
        P p2 = this.mPresenter;
        if (p2 == 0) {
            Intrinsics.throwNpe();
        }
        sb2.append(((HomeFragmentPresenter) p2).isConnected());
        ConnectLogHelper.saveLog(str2, sb2.toString());
        ImageView imageView2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_left);
        if (imageView2 == null || imageView2.getVisibility() != 0) {
            refreshLayout.finishRefresh(10);
            return;
        }
        CommonLogUtil.printAndSave("连接同步流程-->=>开始刷新界面");
        P p3 = this.mPresenter;
        if (p3 == 0) {
            Intrinsics.throwNpe();
        }
        if (((HomeFragmentPresenter) p3).isBindAndConnected()) {
            removeDeviceUnBindView();
            syncing();
            P p4 = this.mPresenter;
            if (p4 == 0) {
                Intrinsics.throwNpe();
            }
            ((HomeFragmentPresenter) p4).syncDeviceData(false);
            return;
        }
        P p5 = this.mPresenter;
        if (p5 == 0) {
            Intrinsics.throwNpe();
        }
        if (!((HomeFragmentPresenter) p5).isBindDevice()) {
            refreshLayout.finishRefresh(10, true, true);
            DataUploadService.Companion.start$default(DataUploadService.INSTANCE, false, false, 3, null);
            if (!this.mHasShow) {
                addDeviceUnBindView();
            }
            String[] locationPermission = PermissionUtil.getLocationPermission();
            if (!checkSelfPermission((String[]) Arrays.copyOf(locationPermission, locationPermission.length))) {
                String[] locationPermission2 = PermissionUtil.getLocationPermission();
                Intrinsics.checkExpressionValueIsNotNull(locationPermission2, "PermissionUtil.getLocationPermission()");
                if (isDeniedByNoAsk((String[]) Arrays.copyOf(locationPermission2, locationPermission2.length))) {
                    showOpenGpsDialog();
                    return;
                } else {
                    CommonLogUtil.printAndSave("连接同步流程-->=>设备未绑定,请求GPS权限");
                    requestPermissions(PermissionUtil.getLocationPermission(), 300);
                    return;
                }
            }
            if (BleHelper.isOpenGPS(this.mActivity)) {
                return;
            }
            CommonLogUtil.printAndSave("连接同步流程-->=>设备未绑定,进入GPS权限设备界面");
            checkGpsSwitch();
            return;
        }
        P p6 = this.mPresenter;
        if (p6 == 0) {
            Intrinsics.throwNpe();
        }
        if (((HomeFragmentPresenter) p6).isConnected()) {
            return;
        }
        DataUploadService.Companion.start$default(DataUploadService.INSTANCE, false, false, 3, null);
        String[] onlyBluetoothPermission = PermissionUtil.getOnlyBluetoothPermission();
        if (!checkSelfPermission((String[]) Arrays.copyOf(onlyBluetoothPermission, onlyBluetoothPermission.length))) {
            String[] onlyBluetoothPermission2 = PermissionUtil.getOnlyBluetoothPermission();
            Intrinsics.checkExpressionValueIsNotNull(onlyBluetoothPermission2, "PermissionUtil.getOnlyBluetoothPermission()");
            if (isDeniedByNoAsk((String[]) Arrays.copyOf(onlyBluetoothPermission2, onlyBluetoothPermission2.length))) {
                showBlueToothPermissionDialog();
                return;
            } else {
                CommonLogUtil.printAndSave("连接同步流程-->=>设备未连接,请求蓝牙权限");
                requestPermissions(PermissionUtil.getOnlyBluetoothPermission(), 400);
                return;
            }
        }
        if (!BleHelper.isBluetoothOpen()) {
            CommonLogUtil.printAndSave("连接同步流程-->=>设备未连接,请求打开蓝牙");
            BleHelper.openBLE(this);
            return;
        }
        String[] locationPermission3 = PermissionUtil.getLocationPermission();
        if (!checkSelfPermission((String[]) Arrays.copyOf(locationPermission3, locationPermission3.length))) {
            String[] locationPermission4 = PermissionUtil.getLocationPermission();
            Intrinsics.checkExpressionValueIsNotNull(locationPermission4, "PermissionUtil.getLocationPermission()");
            if (isDeniedByNoAsk((String[]) Arrays.copyOf(locationPermission4, locationPermission4.length))) {
                showOpenGpsDialog();
                return;
            } else {
                CommonLogUtil.printAndSave("连接同步流程-->=>设备未连接,请求GPS权限");
                requestPermissions(PermissionUtil.getLocationPermission(), 300);
                return;
            }
        }
        if (!BleHelper.isOpenGPS(this.mActivity)) {
            CommonLogUtil.printAndSave("连接同步流程-->=>设备未连接,进入GPS权限设备界面");
            connecting();
            P p7 = this.mPresenter;
            if (p7 == 0) {
                Intrinsics.throwNpe();
            }
            ((HomeFragmentPresenter) p7).connectDevice();
            checkGpsSwitch();
            return;
        }
        connecting();
        P p8 = this.mPresenter;
        if (p8 == 0) {
            Intrinsics.throwNpe();
        }
        ((HomeFragmentPresenter) p8).connectDevice();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.mPresenter == 0) {
            return;
        }
        if (requestCode == 88) {
            HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
            if (homeFragmentPresenter != null) {
                homeFragmentPresenter.getCardData();
                return;
            }
            return;
        }
        if (requestCode != 101) {
            return;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        if (((HomeFragmentPresenter) p).isBleEnable()) {
            CommonLogUtil.printAndSave("连接同步流程-->=>蓝牙开启成功");
            startSyncData();
        } else {
            CommonLogUtil.printAndSave("连接同步流程-->=>打开蓝牙失败");
            onConnectFailed();
        }
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage<?> message) {
        super.handleMessage(message);
        HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
        if (homeFragmentPresenter != null) {
            homeFragmentPresenter.processEventBus(message);
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void refreshAllCard() {
        HomeAdapter homeAdapter = this.mAdapter;
        if (homeAdapter != null) {
            homeAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void refreshVolumeCard() {
        if (this.mAdapter == null || !(!this.mTypeDataList.isEmpty())) {
            return;
        }
        int size = this.mTypeDataList.size();
        for (int i = 0; i < size; i++) {
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 11) {
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i) : null;
                if ((viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null) != null) {
                    CommonLogUtil.d(TAG, "开始刷新环境音量卡片");
                    if (viewHolderFindViewHolderForAdapterPosition instanceof BaseHomeItemViewHolder) {
                        BaseHomeItemViewHolder baseHomeItemViewHolder = (BaseHomeItemViewHolder) viewHolderFindViewHolderForAdapterPosition;
                        baseHomeItemViewHolder.refreshPage();
                        baseHomeItemViewHolder.checkAnimator();
                        return;
                    } else {
                        HomeAdapter homeAdapter = this.mAdapter;
                        if (homeAdapter != null) {
                            homeAdapter.notifyItemChanged(i);
                            return;
                        }
                        return;
                    }
                }
                HomeAdapter homeAdapter2 = this.mAdapter;
                if (homeAdapter2 != null) {
                    homeAdapter2.notifyItemChanged(i);
                    return;
                }
                return;
            }
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onNeedOpenBle() {
        BleHelper.openBLE(this);
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onNeedLocationPermission() {
        String[] locationPermission = PermissionUtil.getLocationPermission();
        if (checkSelfPermission((String[]) Arrays.copyOf(locationPermission, locationPermission.length))) {
            return;
        }
        String[] locationPermission2 = PermissionUtil.getLocationPermission();
        requestPermissions(300, null, (String[]) Arrays.copyOf(locationPermission2, locationPermission2.length));
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onNeedOpenGps() {
        checkGpsSwitch();
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.HomeFragment$onSyncProgress$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.HomeFragment$onSyncProgress$1", f = "HomeFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $progress;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02811(int i, Continuation continuation) {
            super(2, continuation);
            this.$progress = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02811 c02811 = HomeFragment.this.new C02811(this.$progress, completion);
            c02811.p$ = (CoroutineScope) obj;
            return c02811;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02811) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            HomeDeviceStateView homeDeviceStateView = (HomeDeviceStateView) HomeFragment.this._$_findCachedViewById(com.ido.life.R.id.home_device_state);
            if (homeDeviceStateView != null) {
                homeDeviceStateView.syncProgress(this.$progress);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onSyncProgress(int progress) {
        CommonLogUtil.d(TAG, "数据同步进度 progress=" + progress);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C02811(progress, null), 2, null);
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.HomeFragment$onSyncSuccess$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.HomeFragment$onSyncSuccess$1", f = "HomeFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02821 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C02821(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02821 c02821 = HomeFragment.this.new C02821(completion);
            c02821.p$ = (CoroutineScope) obj;
            return c02821;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02821) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            HomeFragment.this.syncSuccess();
            DataUploadService.Companion.start$default(DataUploadService.INSTANCE, false, false, 3, null);
            return Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onSyncSuccess() {
        if (((SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout)) == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C02821(null), 2, null);
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.HomeFragment$onSyncFailed$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.HomeFragment$onSyncFailed$1", f = "HomeFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02801 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C02801(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02801 c02801 = HomeFragment.this.new C02801(completion);
            c02801.p$ = (CoroutineScope) obj;
            return c02801;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02801) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            if (HomeFragment.this.isVisible()) {
                HomeFragment homeFragment = HomeFragment.this;
                homeFragment.showToast(homeFragment.getLanguageText(R.string.synced_failed));
            }
            if (HomeFragment.access$getMPresenter$p(HomeFragment.this) != null) {
                HomeFragmentPresenter homeFragmentPresenterAccess$getMPresenter$p = HomeFragment.access$getMPresenter$p(HomeFragment.this);
                if (homeFragmentPresenterAccess$getMPresenter$p == null) {
                    Intrinsics.throwNpe();
                }
                if (homeFragmentPresenterAccess$getMPresenter$p.isBind()) {
                    HomeFragment.this.removeDeviceUnBindView();
                }
            }
            HomeFragment.this.syncFailed();
            return Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onSyncFailed() {
        if (((SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout)) == null || this.mPresenter == 0) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C02801(null), 2, null);
    }

    @Override // com.ido.life.module.home.IHomeView
    public void startRefresh() {
        SmartRefreshLayout smartRefreshLayout;
        SmartRefreshLayout smartRefreshLayout2 = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
        if ((smartRefreshLayout2 != null ? smartRefreshLayout2.getState() : null) == RefreshState.Refreshing || (smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout)) == null) {
            return;
        }
        smartRefreshLayout.autoRefresh();
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onConnectFailed() {
        if (isVisible()) {
            showToast(getLanguageText(R.string.home_user_connectfailed));
        }
        connectFailed();
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onInDfuMode(final BLEDevice device) {
        if (((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_progress_desc)) == null || this.mActivity == null || device == null) {
            return;
        }
        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefresh(10);
        }
        ImageView imageView = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_left);
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        HomeSyncProgressView homeSyncProgressView = (HomeSyncProgressView) _$_findCachedViewById(com.ido.life.R.id.sync_progress_bar);
        if (homeSyncProgressView != null) {
            homeSyncProgressView.setVisibility(8);
        }
        ImageView imageView2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_sync_state);
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        ImageView imageView3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_connect_state);
        if (imageView3 != null) {
            imageView3.setVisibility(8);
        }
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_progress_desc);
        if (textView != null) {
            textView.setVisibility(8);
        }
        HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
        if (homeFragmentPresenter != null) {
            homeFragmentPresenter.disconnect();
        }
        CommBottomConfirmDialog onConfirmListener = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.dfu_mode_tip), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.home.HomeFragment.onInDfuMode.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getOtaLogPath(), "HomeFragment", "onInDfuMode，click to upgrade");
                Intent intent = new Intent(HomeFragment.this.mActivity, (Class<?>) DeviceUpgradeNewActivity.class);
                intent.putExtra("device_info", device);
                HomeFragment.this.startActivity(intent);
            }
        });
        BaseActivity mActivity = this.mActivity;
        Intrinsics.checkExpressionValueIsNotNull(mActivity, "mActivity");
        onConfirmListener.show(mActivity.getSupportFragmentManager());
    }

    @Override // com.ido.life.module.home.IHomeView
    public SportHealth getLastestSportRecord() {
        HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
        if (homeFragmentPresenter != null) {
            return homeFragmentPresenter.getLatestSportRecord();
        }
        return null;
    }

    @Override // com.ido.life.module.home.IHomeView
    public Pair<ServerHeartRateDayData, List<BaseCharBean>> getHeartRateData() {
        HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
        if (homeFragmentPresenter != null) {
            return homeFragmentPresenter.getHeartRateData();
        }
        return null;
    }

    @Override // com.ido.life.module.home.IHomeView
    public List<WeightItemBean> getWeightList() {
        HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
        if (homeFragmentPresenter != null) {
            return homeFragmentPresenter.getWeightList();
        }
        return null;
    }

    @Override // com.ido.life.module.home.IHomeView
    public Pair<Pair<Integer, Boolean>, List<Point>> getTodayStepData() {
        HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
        if (homeFragmentPresenter != null) {
            return homeFragmentPresenter.getTodayStepData();
        }
        return null;
    }

    @Override // com.ido.life.module.home.IHomeView
    public void showUserMedalDialog(UserModelEnum modelEnum) {
        if (modelEnum == null) {
            return;
        }
        showMedalGetSuccess(modelEnum);
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean hasSleepData() {
        if (this.mPresenter == 0) {
            return false;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).hasSleepData();
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean hasSportRecord() {
        if (this.mPresenter == 0) {
            return false;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).hasSportRecord();
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean hasBloodOxyData() {
        if (this.mPresenter == 0) {
            return false;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).hasBloodOxyData();
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean hasWalkData() {
        if (this.mPresenter == 0) {
            return false;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).hasWalkData();
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean hasStepData() {
        if (this.mPresenter == 0) {
            return false;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).hasStepData();
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean hasActivityData() {
        if (this.mPresenter == 0) {
            return false;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).hasActivityData();
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean hasCalorie() {
        if (this.mPresenter == 0) {
            return false;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).hasCalorie();
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean hasLifeCycle() {
        if (this.mPresenter == 0) {
            return false;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).hasLifeCycle();
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean hasPressure() {
        if (this.mPresenter == 0) {
            return false;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).hasPressure();
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean hasHeartRate() {
        if (this.mPresenter == 0) {
            return false;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).hasHeartRate();
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean hasDistance() {
        if (this.mPresenter == 0) {
            return false;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).hasDistance();
    }

    @Override // com.ido.life.module.home.IHomeView
    public int getHeaderCount() {
        int i = 0;
        if (this.mTypeDataList.isEmpty()) {
            return 0;
        }
        for (MainData mainData : this.mTypeDataList) {
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mainData");
            if (mainData.getViewType() == 2 || mainData.getViewType() == 1 || mainData.getViewType() == 0) {
                i++;
            }
        }
        return i;
    }

    @Override // com.ido.life.module.home.IHomeView
    public void refreshStepCard() {
        if (this.mAdapter == null || !(!this.mTypeDataList.isEmpty())) {
            return;
        }
        int size = this.mTypeDataList.size();
        for (int i = 0; i < size; i++) {
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 1) {
                CommonLogUtil.d(TAG, "开始刷新步数卡片");
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i) : null;
                if (viewHolderFindViewHolderForAdapterPosition instanceof BaseHomeItemViewHolder) {
                    BaseHomeItemViewHolder baseHomeItemViewHolder = (BaseHomeItemViewHolder) viewHolderFindViewHolderForAdapterPosition;
                    baseHomeItemViewHolder.refreshPage();
                    baseHomeItemViewHolder.checkAnimator();
                    return;
                } else {
                    HomeAdapter homeAdapter = this.mAdapter;
                    if (homeAdapter != null) {
                        homeAdapter.notifyItemChanged(i);
                        return;
                    }
                    return;
                }
            }
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void refreshPanelCard() {
        if (this.mAdapter == null || !(!this.mTypeDataList.isEmpty())) {
            return;
        }
        int size = this.mTypeDataList.size();
        for (int i = 0; i < size; i++) {
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 2) {
                CommonLogUtil.printAndSave(TAG, "开始刷新活动/中高强度/走动卡片");
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i) : null;
                if (viewHolderFindViewHolderForAdapterPosition instanceof HomePannelViewHolder) {
                    HomePannelViewHolder homePannelViewHolder = (HomePannelViewHolder) viewHolderFindViewHolderForAdapterPosition;
                    homePannelViewHolder.refreshPage();
                    homePannelViewHolder.checkAnimator();
                    return;
                } else {
                    HomeAdapter homeAdapter = this.mAdapter;
                    if (homeAdapter != null) {
                        homeAdapter.notifyItemChanged(i);
                        return;
                    }
                    return;
                }
            }
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void refreshSportCard() {
        if (this.mAdapter == null || !(!this.mTypeDataList.isEmpty())) {
            return;
        }
        int size = this.mTypeDataList.size();
        for (int i = 0; i < size; i++) {
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 3) {
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i) : null;
                if ((viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null) != null) {
                    CommonLogUtil.d(TAG, "开始刷新运动卡片");
                    if (viewHolderFindViewHolderForAdapterPosition instanceof BaseHomeItemViewHolder) {
                        BaseHomeItemViewHolder baseHomeItemViewHolder = (BaseHomeItemViewHolder) viewHolderFindViewHolderForAdapterPosition;
                        baseHomeItemViewHolder.refreshPage();
                        baseHomeItemViewHolder.checkAnimator();
                        return;
                    } else {
                        HomeAdapter homeAdapter = this.mAdapter;
                        if (homeAdapter != null) {
                            homeAdapter.notifyItemChanged(i);
                            return;
                        }
                        return;
                    }
                }
                HomeAdapter homeAdapter2 = this.mAdapter;
                if (homeAdapter2 != null) {
                    homeAdapter2.notifyItemChanged(i);
                    return;
                }
                return;
            }
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void refreshSleepCard() {
        if (this.mAdapter == null || !(!this.mTypeDataList.isEmpty())) {
            return;
        }
        int size = this.mTypeDataList.size();
        for (int i = 0; i < size; i++) {
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 4) {
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i) : null;
                if ((viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null) != null) {
                    CommonLogUtil.d(TAG, "开始刷新睡眠卡片");
                    if (viewHolderFindViewHolderForAdapterPosition instanceof BaseHomeItemViewHolder) {
                        BaseHomeItemViewHolder baseHomeItemViewHolder = (BaseHomeItemViewHolder) viewHolderFindViewHolderForAdapterPosition;
                        baseHomeItemViewHolder.refreshPage();
                        baseHomeItemViewHolder.checkAnimator();
                        return;
                    } else {
                        HomeAdapter homeAdapter = this.mAdapter;
                        if (homeAdapter != null) {
                            homeAdapter.notifyItemChanged(i);
                            return;
                        }
                        return;
                    }
                }
                HomeAdapter homeAdapter2 = this.mAdapter;
                if (homeAdapter2 != null) {
                    homeAdapter2.notifyItemChanged(i);
                    return;
                }
                return;
            }
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void refreshHeartRateCard() {
        if (this.mAdapter == null || !(!this.mTypeDataList.isEmpty())) {
            return;
        }
        int size = this.mTypeDataList.size();
        for (int i = 0; i < size; i++) {
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 5) {
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i) : null;
                if ((viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null) != null) {
                    CommonLogUtil.d(TAG, "开始刷新心率卡片");
                    if (viewHolderFindViewHolderForAdapterPosition instanceof BaseHomeItemViewHolder) {
                        BaseHomeItemViewHolder baseHomeItemViewHolder = (BaseHomeItemViewHolder) viewHolderFindViewHolderForAdapterPosition;
                        baseHomeItemViewHolder.refreshPage();
                        baseHomeItemViewHolder.checkAnimator();
                        return;
                    } else {
                        HomeAdapter homeAdapter = this.mAdapter;
                        if (homeAdapter != null) {
                            homeAdapter.notifyItemChanged(i);
                            return;
                        }
                        return;
                    }
                }
                HomeAdapter homeAdapter2 = this.mAdapter;
                if (homeAdapter2 != null) {
                    homeAdapter2.notifyItemChanged(i);
                    return;
                }
                return;
            }
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void refreshPressureCard() {
        if (this.mAdapter == null || !(!this.mTypeDataList.isEmpty())) {
            return;
        }
        int size = this.mTypeDataList.size();
        for (int i = 0; i < size; i++) {
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 6) {
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i) : null;
                if ((viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null) != null) {
                    if (viewHolderFindViewHolderForAdapterPosition instanceof BaseHomeItemViewHolder) {
                        BaseHomeItemViewHolder baseHomeItemViewHolder = (BaseHomeItemViewHolder) viewHolderFindViewHolderForAdapterPosition;
                        baseHomeItemViewHolder.refreshPage();
                        baseHomeItemViewHolder.checkAnimator();
                        return;
                    } else {
                        HomeAdapter homeAdapter = this.mAdapter;
                        if (homeAdapter != null) {
                            homeAdapter.notifyItemChanged(i);
                            return;
                        }
                        return;
                    }
                }
                HomeAdapter homeAdapter2 = this.mAdapter;
                if (homeAdapter2 != null) {
                    homeAdapter2.notifyItemChanged(i);
                    return;
                }
                return;
            }
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void refreshOxygenUptakeCard() {
        if (this.mAdapter == null || !(!this.mTypeDataList.isEmpty())) {
            return;
        }
        int size = this.mTypeDataList.size();
        for (int i = 0; i < size; i++) {
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 12) {
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i) : null;
                if ((viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null) != null) {
                    if (viewHolderFindViewHolderForAdapterPosition instanceof BaseHomeItemViewHolder) {
                        BaseHomeItemViewHolder baseHomeItemViewHolder = (BaseHomeItemViewHolder) viewHolderFindViewHolderForAdapterPosition;
                        baseHomeItemViewHolder.refreshPage();
                        baseHomeItemViewHolder.checkAnimator();
                        return;
                    } else {
                        HomeAdapter homeAdapter = this.mAdapter;
                        if (homeAdapter != null) {
                            homeAdapter.notifyItemChanged(i);
                            return;
                        }
                        return;
                    }
                }
                HomeAdapter homeAdapter2 = this.mAdapter;
                if (homeAdapter2 != null) {
                    homeAdapter2.notifyItemChanged(i);
                    return;
                }
                return;
            }
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void refreshBloodOxyCard() {
        if (this.mAdapter == null || !(!this.mTypeDataList.isEmpty())) {
            return;
        }
        int size = this.mTypeDataList.size();
        for (int i = 0; i < size; i++) {
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 7) {
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i) : null;
                if ((viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null) != null) {
                    CommonLogUtil.d(TAG, "开始刷新血氧卡片");
                    if (viewHolderFindViewHolderForAdapterPosition instanceof BaseHomeItemViewHolder) {
                        BaseHomeItemViewHolder baseHomeItemViewHolder = (BaseHomeItemViewHolder) viewHolderFindViewHolderForAdapterPosition;
                        baseHomeItemViewHolder.refreshPage();
                        baseHomeItemViewHolder.checkAnimator();
                        return;
                    } else {
                        HomeAdapter homeAdapter = this.mAdapter;
                        if (homeAdapter != null) {
                            homeAdapter.notifyItemChanged(i);
                            return;
                        }
                        return;
                    }
                }
                HomeAdapter homeAdapter2 = this.mAdapter;
                if (homeAdapter2 != null) {
                    homeAdapter2.notifyItemChanged(i);
                    return;
                }
                return;
            }
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void refreshWeightCard() {
        if (this.mAdapter == null || !(!this.mTypeDataList.isEmpty())) {
            return;
        }
        int size = this.mTypeDataList.size();
        for (int i = 0; i < size; i++) {
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 8) {
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i) : null;
                if ((viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null) != null) {
                    CommonLogUtil.d(TAG, "开始刷新体重卡片");
                    if (viewHolderFindViewHolderForAdapterPosition instanceof BaseHomeItemViewHolder) {
                        BaseHomeItemViewHolder baseHomeItemViewHolder = (BaseHomeItemViewHolder) viewHolderFindViewHolderForAdapterPosition;
                        baseHomeItemViewHolder.refreshPage();
                        baseHomeItemViewHolder.checkAnimator();
                        return;
                    } else {
                        HomeAdapter homeAdapter = this.mAdapter;
                        if (homeAdapter != null) {
                            homeAdapter.notifyItemChanged(i);
                            return;
                        }
                        return;
                    }
                }
                HomeAdapter homeAdapter2 = this.mAdapter;
                if (homeAdapter2 != null) {
                    homeAdapter2.notifyItemChanged(i);
                    return;
                }
                return;
            }
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void refreshMenstrualCard() {
        if (this.mAdapter == null || !(!this.mTypeDataList.isEmpty())) {
            return;
        }
        int size = this.mTypeDataList.size();
        for (int i = 0; i < size; i++) {
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 9) {
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i) : null;
                if ((viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null) != null) {
                    CommonLogUtil.d(TAG, "开始刷新生理周期卡片");
                    if (viewHolderFindViewHolderForAdapterPosition instanceof BaseHomeItemViewHolder) {
                        BaseHomeItemViewHolder baseHomeItemViewHolder = (BaseHomeItemViewHolder) viewHolderFindViewHolderForAdapterPosition;
                        baseHomeItemViewHolder.refreshPage();
                        baseHomeItemViewHolder.checkAnimator();
                        return;
                    } else {
                        HomeAdapter homeAdapter = this.mAdapter;
                        if (homeAdapter != null) {
                            homeAdapter.notifyItemChanged(i);
                            return;
                        }
                        return;
                    }
                }
                HomeAdapter homeAdapter2 = this.mAdapter;
                if (homeAdapter2 != null) {
                    homeAdapter2.notifyItemChanged(i);
                    return;
                }
                return;
            }
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onBlueToothConnect() {
        CommonLogUtil.printAndSave("连接同步流程-->=>蓝牙连接成功");
        if (this.mPresenter == 0) {
            removeDeviceUnBindView();
            SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
            if (smartRefreshLayout != null) {
                smartRefreshLayout.finishRefresh(10);
                return;
            }
            return;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        if (((HomeFragmentPresenter) p).isBindAndConnected()) {
            processBlueConnectSuccessAction();
        }
    }

    private final void processBlueConnectSuccessAction() {
        SmartRefreshLayout smartRefreshLayout;
        try {
            if (this.mPresenter != 0 && ((HomeSyncProgressView) _$_findCachedViewById(com.ido.life.R.id.sync_progress_bar)) != null) {
                removeDeviceUnBindView();
                if (this.mActivity != null && !this.mActivity.inBackGround()) {
                    ImageView imageView = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_left);
                    if (imageView == null || imageView.getVisibility() != 0) {
                        CommonLogUtil.printAndSave("连接同步流程-->=>开始同步数据");
                        syncing();
                        HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
                        if (homeFragmentPresenter != null) {
                            homeFragmentPresenter.syncDeviceData(false);
                        }
                    } else if (!SPHelper.isConfigSynced() && (smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout)) != null) {
                        smartRefreshLayout.autoRefresh();
                    }
                } else {
                    SmartRefreshLayout smartRefreshLayout2 = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
                    if (smartRefreshLayout2 != null) {
                        smartRefreshLayout2.finishRefresh(10);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onBlueToothDisconnect() {
        CommonLogUtil.printAndSave("连接同步流程-->=>蓝牙断开连接");
        connectFailed();
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onDeviceUnBindSuccess() {
        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
        if ((smartRefreshLayout != null ? smartRefreshLayout.getState() : null) == RefreshState.Refreshing) {
            connectFailed();
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onDeviceBindSuccess() {
        CommonLogUtil.printAndSave("连接同步流程-->=>设备绑定成功");
        removeDeviceUnBindView();
        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
        if (smartRefreshLayout != null) {
            smartRefreshLayout.postDelayed(new Runnable() { // from class: com.ido.life.module.home.HomeFragment.onDeviceBindSuccess.1
                @Override // java.lang.Runnable
                public final void run() {
                    SmartRefreshLayout smartRefreshLayout2;
                    HomeFragmentPresenter homeFragmentPresenterAccess$getMPresenter$p = HomeFragment.access$getMPresenter$p(HomeFragment.this);
                    if (homeFragmentPresenterAccess$getMPresenter$p == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!homeFragmentPresenterAccess$getMPresenter$p.isBindAndConnected() || (smartRefreshLayout2 = (SmartRefreshLayout) HomeFragment.this._$_findCachedViewById(com.ido.life.R.id.refreshLayout)) == null) {
                        return;
                    }
                    smartRefreshLayout2.autoRefresh();
                }
            }, 1000L);
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onDeviceBindCrossDay() {
        if (this.mPresenter == 0) {
            return;
        }
        HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
        if (homeFragmentPresenter != null) {
            homeFragmentPresenter.getCardData();
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        if (((HomeFragmentPresenter) p).isBindAndConnected()) {
            SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
            if (smartRefreshLayout != null) {
                smartRefreshLayout.autoRefresh();
                return;
            }
            return;
        }
        P p2 = this.mPresenter;
        if (p2 == 0) {
            Intrinsics.throwNpe();
        }
        if (((HomeFragmentPresenter) p2).isBind()) {
            return;
        }
        connectFailed();
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onDeviceRestarted() {
        if (this.mPresenter == 0) {
            return;
        }
        HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
        if (homeFragmentPresenter != null) {
            homeFragmentPresenter.getCardData();
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        if (((HomeFragmentPresenter) p).isBindAndConnected()) {
            SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
            if (smartRefreshLayout != null) {
                smartRefreshLayout.autoRefresh();
                return;
            }
            return;
        }
        P p2 = this.mPresenter;
        if (p2 == 0) {
            Intrinsics.throwNpe();
        }
        if (((HomeFragmentPresenter) p2).isBind()) {
            return;
        }
        connectFailed();
    }

    @Override // com.ido.life.module.home.IHomeView
    public void backFromBackground() {
        CommonLogUtil.printAndSave("连接同步流程-->=>APP从后台退到前台");
        if (this.mPresenter == 0) {
            return;
        }
        this.mHasShow = false;
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        ((HomeFragmentPresenter) p).veryEmailHasBind();
        P p2 = this.mPresenter;
        if (p2 == 0) {
            Intrinsics.throwNpe();
        }
        if (((HomeFragmentPresenter) p2).isBindAndConnected()) {
            removeDeviceUnBindView();
            SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
            if ((smartRefreshLayout != null ? smartRefreshLayout.getState() : null) != RefreshState.Refreshing) {
                P p3 = this.mPresenter;
                if (p3 == 0) {
                    Intrinsics.throwNpe();
                }
                if (((HomeFragmentPresenter) p3).autoSyncDeviceData()) {
                    CommonLogUtil.printAndSave("连接同步流程-->=>APP从后台退到前台距离上次数据同步超过30分钟，自动开启从设备同步数据");
                    SmartRefreshLayout smartRefreshLayout2 = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
                    if (smartRefreshLayout2 != null) {
                        smartRefreshLayout2.autoRefresh();
                        return;
                    }
                    return;
                }
                CommonLogUtil.printAndSave("连接同步流程-->=>APP从后台退到前台距离上次数据同步不超过30分钟，不自动开启从设备同步数据");
                return;
            }
            return;
        }
        P p4 = this.mPresenter;
        if (p4 == 0) {
            Intrinsics.throwNpe();
        }
        if (((HomeFragmentPresenter) p4).isBind()) {
            P p5 = this.mPresenter;
            if (p5 == 0) {
                Intrinsics.throwNpe();
            }
            if (((HomeFragmentPresenter) p5).isConnected()) {
                return;
            }
            removeDeviceUnBindView();
            SmartRefreshLayout smartRefreshLayout3 = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
            if (smartRefreshLayout3 != null) {
                smartRefreshLayout3.finishRefresh(10);
            }
            SmartRefreshLayout smartRefreshLayout4 = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
            if (smartRefreshLayout4 != null) {
                smartRefreshLayout4.autoRefresh();
            }
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onUserInfoChanged() {
        HomeAdapter homeAdapter = this.mAdapter;
        if (homeAdapter != null) {
            homeAdapter.notifyDataSetChanged();
        }
    }

    private final void showMedalGetSuccess(UserModelEnum modelEnum) {
        if (modelEnum == null || this.mActivity == null) {
            return;
        }
        View contentView = getLayoutInflater().inflate(R.layout.dialog_get_medal_success_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(contentView, "contentView");
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        contentView.setMinimumWidth((resources.getDisplayMetrics().widthPixels * 4) / 5);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this.mActivity, R.style.dialog_translate).setView(contentView).setCancelable(true).create();
        Intrinsics.checkExpressionValueIsNotNull(alertDialogCreate, "AlertDialog.Builder(mAct…ue)\n            .create()");
        View viewFindViewById = contentView.findViewById(R.id.tv_title);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "contentView.findViewById(R.id.tv_title)");
        ImageView imageView = (ImageView) contentView.findViewById(R.id.img_medal);
        TextView tvMedalTitle = (TextView) contentView.findViewById(R.id.tv_medal_title);
        ((RegularTextView) viewFindViewById).setText(getLanguageText(R.string.tip_sucess_get_medal));
        String title = getLanguageText(modelEnum.getTitleResId());
        String target = getLanguageText(R.string.detail_qualified);
        Intrinsics.checkExpressionValueIsNotNull(title, "title");
        Intrinsics.checkExpressionValueIsNotNull(target, "target");
        if (!StringsKt.endsWith$default(title, target, false, 2, (Object) null)) {
            title = title + target;
        }
        Intrinsics.checkExpressionValueIsNotNull(tvMedalTitle, "tvMedalTitle");
        tvMedalTitle.setText(title);
        imageView.setImageResource(modelEnum.getMedalImageId_KM());
        alertDialogCreate.show();
    }

    @Override // com.ido.life.module.home.IHomeView
    public ServerSleepDayData getSleepData() {
        if (this.mPresenter == 0) {
            return null;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).getNearestSleepData();
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onGetCardDataList(LinkedList<MainData> mainDataList) {
        ViewTreeObserver viewTreeObserver;
        if (this.mPresenter == 0) {
            return;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        if (((HomeFragmentPresenter) p).cardChanged(mainDataList, this.mTypeDataList)) {
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
            if (recyclerView != null) {
                recyclerView.smoothScrollToPosition(0);
            }
            if (this.mGlobalListener == null) {
                this.mGlobalListener = new HomeGlobalLayoutListener();
            }
            View view = getView();
            if (view != null && (viewTreeObserver = view.getViewTreeObserver()) != null) {
                viewTreeObserver.addOnGlobalLayoutListener(this.mGlobalListener);
            }
        }
        this.mTypeDataList.clear();
        if (mainDataList != null && mainDataList.size() > 0) {
            this.mTypeDataList.addAll(mainDataList);
        }
        HomeAdapter homeAdapter = this.mAdapter;
        if (homeAdapter != null) {
            homeAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public ServerBloodOxyDayData getNearOxyData() {
        if (this.mPresenter == 0) {
            return null;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).getNearBloodOxyDailyData();
    }

    @Override // com.ido.life.module.home.IHomeView
    public Pair<Pair<Boolean, Integer>, Integer> getTodayActive() {
        if (this.mPresenter == 0) {
            return new Pair<>(new Pair(false, 0), 500);
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).getTodayActive();
    }

    @Override // com.ido.life.module.home.IHomeView
    public Pair<Pair<Boolean, Integer>, Integer> getTodayActiveTime() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(runTimeUtil.getUserId());
        int activityTime = (userTargetNewQueryUserLatestTarget == null || userTargetNewQueryUserLatestTarget.getActivityTime() <= 0) ? 30 : userTargetNewQueryUserLatestTarget.getActivityTime() / 60;
        if (this.mPresenter == 0) {
            return new Pair<>(new Pair(false, 0), Integer.valueOf(activityTime));
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        ActiveTimeDayData todayTime = ((HomeFragmentPresenter) p).getTodayTime();
        if (todayTime != null) {
            return new Pair<>(new Pair(Boolean.valueOf(!todayTime.isUploaded()), Integer.valueOf(todayTime.getMediumOrHigherSeconds() / 60)), Integer.valueOf(activityTime));
        }
        return new Pair<>(new Pair(false, 0), Integer.valueOf(activityTime));
    }

    @Override // com.ido.life.module.home.IHomeView
    public Pair<Pair<Boolean, Integer>, Integer> getTodayWalk() {
        if (this.mPresenter == 0) {
            return new Pair<>(new Pair(false, 0), 12);
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).getTodayWalkHour();
    }

    @Override // com.ido.life.module.home.IHomeView
    public float getTotalDistance() {
        if (this.mPresenter == 0) {
            return 0.0f;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).getTotalDistance();
    }

    @Override // com.ido.life.module.home.IHomeView
    public WholeLifeCycleInfo getMenstrual() {
        if (this.mPresenter == 0) {
            return null;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).getMenstrual();
    }

    @Override // com.ido.life.module.home.IHomeView
    public HealthVolumeData getAmbientNoiseData() {
        if (this.mPresenter == 0) {
            return null;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).getRecentVolume();
    }

    @Override // com.ido.life.module.home.IHomeView
    public Pair<Long, Integer> getOxygenUptakeData() {
        if (this.mPresenter == 0) {
            return null;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).getRecentOxygenUptake();
    }

    @Override // com.ido.life.module.home.IHomeView
    public Pair<Pair<Long, Integer>, Pair<Boolean, List<BarChartPoint>>> getHealthPressure() {
        if (this.mPresenter == 0) {
            return null;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        return ((HomeFragmentPresenter) p).getNearPressureData();
    }

    @Override // com.ido.life.module.home.IHomeView
    public String getDateShowByTimeStamp(long timeStamp) {
        if (this.mPresenter == 0) {
            return "";
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        String dateShowByTimeStamp = ((HomeFragmentPresenter) p).getDateShowByTimeStamp(timeStamp);
        Intrinsics.checkExpressionValueIsNotNull(dateShowByTimeStamp, "mPresenter!!.getDateShowByTimeStamp(timeStamp)");
        return dateShowByTimeStamp;
    }

    @Override // com.ido.life.module.home.IHomeView
    public void updateUserEmailBindState(boolean hasBind) {
        ViewTreeObserver viewTreeObserver;
        ViewTreeObserver viewTreeObserver2;
        CommonLogUtil.d(TAG, hasBind ? "用户已经绑定了邮箱" : "用户未绑定邮箱");
        if (((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_safe_verify)) == null) {
            return;
        }
        if (hasBind) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_safe_verify);
            if (linearLayout == null || linearLayout.getVisibility() != 8) {
                LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_safe_verify);
                if (linearLayout2 != null) {
                    linearLayout2.setVisibility(8);
                }
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                if (recyclerView != null) {
                    recyclerView.smoothScrollToPosition(0);
                }
                if (this.mGlobalListener == null) {
                    this.mGlobalListener = new HomeGlobalLayoutListener();
                }
                View view = getView();
                if (view == null || (viewTreeObserver2 = view.getViewTreeObserver()) == null) {
                    return;
                }
                viewTreeObserver2.addOnGlobalLayoutListener(this.mGlobalListener);
                return;
            }
            return;
        }
        LinearLayout linearLayout3 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_safe_verify);
        if (linearLayout3 == null || linearLayout3.getVisibility() != 0) {
            RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
            if (recyclerView2 != null) {
                recyclerView2.smoothScrollToPosition(0);
            }
            LinearLayout linearLayout4 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_safe_verify);
            if (linearLayout4 != null) {
                linearLayout4.setVisibility(0);
            }
            if (this.mGlobalListener == null) {
                this.mGlobalListener = new HomeGlobalLayoutListener();
            }
            View view2 = getView();
            if (view2 != null && (viewTreeObserver = view2.getViewTreeObserver()) != null) {
                viewTreeObserver.addOnGlobalLayoutListener(this.mGlobalListener);
            }
            TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_safe_verify_action);
            if (textView != null) {
                textView.setText(getLanguageText(R.string.home_verify_action));
            }
            TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_safe_verify_desc);
            if (textView2 != null) {
                textView2.setText(getLanguageText(R.string.home_safe_verify_desc));
            }
            ImageView imageView = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_safe_verify_close);
            if (imageView != null) {
                imageView.setOnClickListener(this);
            }
            LinearLayout linearLayout5 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_safe_verify_action);
            if (linearLayout5 != null) {
                linearLayout5.setOnClickListener(this);
            }
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void onGetUserEmailBindStateFailed() {
        ViewTreeObserver viewTreeObserver;
        if (((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_safe_verify)) == null) {
            return;
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_safe_verify);
        if (linearLayout == null) {
            Intrinsics.throwNpe();
        }
        if (linearLayout.getVisibility() == 8) {
            return;
        }
        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_safe_verify);
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(8);
        }
        View view = getView();
        if (view == null || (viewTreeObserver = view.getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(this.mGlobalListener);
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean refreshRecordTime() {
        if (!isVisible() || this.mPresenter == 0) {
            return false;
        }
        CommonLogUtil.d(TAG, "开始刷新运动记录时间");
        RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = (RecyclerView.ViewHolder) null;
        int size = this.mTypeDataList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 3) {
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i) : null;
            } else {
                i++;
            }
        }
        if ((viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null) == null) {
            return false;
        }
        TextView textView = (TextView) viewHolderFindViewHolderForAdapterPosition.itemView.findViewById(R.id.tv_desc);
        TextView tvName = (TextView) viewHolderFindViewHolderForAdapterPosition.itemView.findViewById(R.id.tv_name);
        if (textView == null || textView.getVisibility() != 0) {
            return false;
        }
        Object tag = textView.getTag(R.id.leftId);
        Object tag2 = textView.getTag(R.id.rightId);
        if (tag == null || tag2 == null || !(tag instanceof Long) || !(tag2 instanceof String)) {
            return false;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = new Object[2];
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        Number number = (Number) tag;
        objArr[0] = ((HomeFragmentPresenter) p).getDateShowByTimeStamp(number.longValue());
        objArr[1] = "";
        String str = String.format("%s  %s", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        textView.setText(new SpannableStringBuilder(str));
        Intrinsics.checkExpressionValueIsNotNull(tvName, "tvName");
        tvName.setText((CharSequence) tag2);
        return DateUtil.isToday(number.longValue());
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean refreshSleepTime() {
        TextView textView;
        Object tag;
        if (!isVisible() || this.mPresenter == 0) {
            return false;
        }
        CommonLogUtil.d(TAG, "开始刷新睡眠时间");
        RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = (RecyclerView.ViewHolder) null;
        int size = this.mTypeDataList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 4) {
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i) : null;
            } else {
                i++;
            }
        }
        if ((viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null) == null || (textView = (TextView) viewHolderFindViewHolderForAdapterPosition.itemView.findViewById(R.id.tv_date)) == null || textView.getVisibility() != 0 || (tag = textView.getTag()) == null || !(tag instanceof Long)) {
            return false;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        Number number = (Number) tag;
        textView.setText(((HomeFragmentPresenter) p).getDateShowByTimeStamp(number.longValue()));
        return DateUtil.isToday(number.longValue());
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean refreshHeartRateTime() {
        TextView textView;
        Object tag;
        if (!isVisible() || this.mPresenter == 0) {
            return false;
        }
        CommonLogUtil.d(TAG, "开始刷新心率时间");
        RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = (RecyclerView.ViewHolder) null;
        int size = this.mTypeDataList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 5) {
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i) : null;
            } else {
                i++;
            }
        }
        if ((viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null) == null || (textView = (TextView) viewHolderFindViewHolderForAdapterPosition.itemView.findViewById(R.id.tv_date)) == null || textView.getVisibility() != 0 || (tag = textView.getTag()) == null || !(tag instanceof Long)) {
            return false;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        Number number = (Number) tag;
        textView.setText(((HomeFragmentPresenter) p).getDateShowByTimeStamp(number.longValue()));
        return DateUtil.isToday(number.longValue());
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean refreshPressureTime() {
        TextView textView;
        Object tag;
        if (!isVisible() || this.mPresenter == 0) {
            return false;
        }
        CommonLogUtil.d(TAG, "开始刷新压力时间");
        RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = (RecyclerView.ViewHolder) null;
        int size = this.mTypeDataList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 6) {
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i) : null;
            } else {
                i++;
            }
        }
        if ((viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null) == null || (textView = (TextView) viewHolderFindViewHolderForAdapterPosition.itemView.findViewById(R.id.tv_date)) == null || textView.getVisibility() != 0 || (tag = textView.getTag()) == null || !(tag instanceof Long)) {
            return false;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        Number number = (Number) tag;
        textView.setText(((HomeFragmentPresenter) p).getDateShowByTimeStamp(number.longValue()));
        return DateUtil.isToday(number.longValue());
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean refreshOxyTime() {
        TextView textView;
        Object tag;
        if (!isVisible() || this.mPresenter == 0) {
            return false;
        }
        CommonLogUtil.d(TAG, "开始刷新血氧时间");
        RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = (RecyclerView.ViewHolder) null;
        int size = this.mTypeDataList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 7) {
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i) : null;
            } else {
                i++;
            }
        }
        if ((viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null) == null || (textView = (TextView) viewHolderFindViewHolderForAdapterPosition.itemView.findViewById(R.id.tv_date)) == null || textView.getVisibility() != 0 || (tag = textView.getTag()) == null || !(tag instanceof Long)) {
            return false;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        Number number = (Number) tag;
        textView.setText(((HomeFragmentPresenter) p).getDateShowByTimeStamp(number.longValue()));
        return DateUtil.isToday(number.longValue());
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean refreshWeightTime() {
        TextView textView;
        Object tag;
        if (!isVisible() || this.mPresenter == 0) {
            return false;
        }
        CommonLogUtil.d(TAG, "开始刷新体重时间");
        RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = (RecyclerView.ViewHolder) null;
        int size = this.mTypeDataList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 8) {
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i) : null;
            } else {
                i++;
            }
        }
        if ((viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null) == null || (textView = (TextView) viewHolderFindViewHolderForAdapterPosition.itemView.findViewById(R.id.tv_date)) == null || textView.getVisibility() != 0 || (tag = textView.getTag()) == null || !(tag instanceof Long)) {
            return false;
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        Number number = (Number) tag;
        textView.setText(((HomeFragmentPresenter) p).getDateShowByTimeStamp(number.longValue()));
        return DateUtil.isToday(number.longValue());
    }

    @Override // com.ido.life.module.home.IHomeView
    public void startUpdateTime() {
        HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
        if (homeFragmentPresenter != null) {
            homeFragmentPresenter.startUpdateTime();
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public boolean hasLogin() {
        return RunTimeUtil.getInstance().hasLogin();
    }

    @Override // com.ido.life.module.home.IHomeView
    public BaseMap<View, ?> getMap() {
        return this.mMapModel;
    }

    @Override // com.ido.life.module.home.IHomeView
    public void startLoadHistoryData() {
        if (((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_history_sync_state)) != null) {
            TextView tv_history_sync_state = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_history_sync_state);
            Intrinsics.checkExpressionValueIsNotNull(tv_history_sync_state, "tv_history_sync_state");
            ViewGroup.LayoutParams layoutParams = tv_history_sync_state.getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            }
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.weight = 1.0f;
            layoutParams2.width = 0;
            TextView tv_history_sync_state2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_history_sync_state);
            Intrinsics.checkExpressionValueIsNotNull(tv_history_sync_state2, "tv_history_sync_state");
            tv_history_sync_state2.setLayoutParams(layoutParams2);
        }
        ImageView imageView = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_sync_state_loading);
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_history_sync_progress);
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        ImageView imageView2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_sync_state_background);
        if (imageView2 != null) {
            imageView2.setImageResource(R.mipmap.history_data_syncing);
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat((ImageView) _$_findCachedViewById(com.ido.life.R.id.img_sync_state_loading), "rotation", 0.0f, 360.0f);
        Intrinsics.checkExpressionValueIsNotNull(animator, "animator");
        animator.setDuration(1000L);
        animator.setRepeatCount(-1);
        animator.setRepeatMode(1);
        animator.start();
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_history_sync_state);
        if (textView != null) {
            textView.setText(getLanguageText(R.string.history_data_syncing));
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        int historyDataDownloadProgress = ((HomeFragmentPresenter) p).getHistoryDataDownloadProgress();
        HomeHistoryProgress homeHistoryProgress = (HomeHistoryProgress) _$_findCachedViewById(com.ido.life.R.id.progress_sync_history);
        if (homeHistoryProgress != null) {
            homeHistoryProgress.updateProgress(historyDataDownloadProgress);
        }
        HomeHistoryProgress homeHistoryProgress2 = (HomeHistoryProgress) _$_findCachedViewById(com.ido.life.R.id.progress_sync_history);
        if (homeHistoryProgress2 != null) {
            homeHistoryProgress2.setMax(100);
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_history_sync_progress);
        if (textView2 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(historyDataDownloadProgress);
            sb.append('%');
            textView2.setText(sb.toString());
        }
        FrameLayout frameLayout = (FrameLayout) _$_findCachedViewById(com.ido.life.R.id.lay_history_sync);
        if (frameLayout != null) {
            frameLayout.setVisibility(0);
        }
        FrameLayout frameLayout2 = (FrameLayout) _$_findCachedViewById(com.ido.life.R.id.lay_history_sync);
        if (frameLayout2 != null) {
            frameLayout2.setOnClickListener(this);
        }
        SPHelper.saveAutoShowHistoryDataPullState(true);
    }

    @Override // com.ido.life.module.home.IHomeView
    public void historyDataLoadFailed() {
        Window window;
        Window window2;
        TextView textView;
        Window window3;
        ImageView imageView;
        ImageView imageView2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_sync_state_loading);
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        ImageView imageView3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_sync_state_background);
        if (imageView3 != null) {
            imageView3.setImageResource(R.mipmap.history_data_sync_failed);
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_history_sync_state);
        if (textView2 != null) {
            textView2.setText(getLanguageText(R.string.history_data_sync_failed));
        }
        FrameLayout frameLayout = (FrameLayout) _$_findCachedViewById(com.ido.life.R.id.lay_history_sync);
        if (frameLayout != null) {
            frameLayout.setOnClickListener(this);
        }
        Dialog dialog = this.mHistorySyncInfoDialog;
        if (dialog != null) {
            if (dialog == null) {
                Intrinsics.throwNpe();
            }
            if (dialog.isShowing()) {
                Dialog dialog2 = this.mHistorySyncInfoDialog;
                if (dialog2 != null && (window3 = dialog2.getWindow()) != null && (imageView = (ImageView) window3.findViewById(R.id.img_state)) != null) {
                    imageView.setImageResource(R.mipmap.history_data_info_sync_failed);
                }
                Dialog dialog3 = this.mHistorySyncInfoDialog;
                if (dialog3 != null && (window2 = dialog3.getWindow()) != null && (textView = (TextView) window2.findViewById(R.id.tv_history_sync_state)) != null) {
                    textView.setText(getLanguageText(R.string.history_data_sync_failed));
                }
                Dialog dialog4 = this.mHistorySyncInfoDialog;
                TextView textView3 = (dialog4 == null || (window = dialog4.getWindow()) == null) ? null : (TextView) window.findViewById(R.id.tv_action_retry);
                if (textView3 != null) {
                    textView3.setVisibility(0);
                }
                if (textView3 != null) {
                    textView3.setOnClickListener(this);
                }
            }
        }
        if (!NetworkUtil.isConnected(this.mActivity)) {
            NormalToast.showToast(getLanguageText(R.string.public_net_unuse));
        }
        HomeAdapter homeAdapter = this.mAdapter;
        if (homeAdapter != null) {
            homeAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void historyDataLoadSuccess() {
        Window window;
        TextView textView;
        Window window2;
        TextView textView2;
        Window window3;
        TextView textView3;
        Window window4;
        Window window5;
        Window window6;
        TextView textView4;
        Window window7;
        ImageView imageView;
        ImageView imageView2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_sync_state_loading);
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        ImageView imageView3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_sync_state_background);
        if (imageView3 != null) {
            imageView3.setImageResource(R.mipmap.history_data_sync_success);
        }
        TextView textView5 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_history_sync_state);
        if (textView5 != null) {
            textView5.setText(getLanguageText(R.string.history_data_sync_success));
        }
        FrameLayout frameLayout = (FrameLayout) _$_findCachedViewById(com.ido.life.R.id.lay_history_sync);
        if (frameLayout != null) {
            frameLayout.setOnClickListener(this);
        }
        HomeHistoryProgress homeHistoryProgress = (HomeHistoryProgress) _$_findCachedViewById(com.ido.life.R.id.progress_sync_history);
        if (homeHistoryProgress != null) {
            homeHistoryProgress.updateProgress(100);
        }
        TextView textView6 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_history_sync_progress);
        if (textView6 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(100);
            sb.append('%');
            textView6.setText(sb.toString());
        }
        Dialog dialog = this.mHistorySyncInfoDialog;
        if (dialog != null) {
            if (dialog == null) {
                Intrinsics.throwNpe();
            }
            if (dialog.isShowing()) {
                Dialog dialog2 = this.mHistorySyncInfoDialog;
                if (dialog2 != null && (window7 = dialog2.getWindow()) != null && (imageView = (ImageView) window7.findViewById(R.id.img_state)) != null) {
                    imageView.setImageResource(R.mipmap.history_data_info_sync_success);
                }
                Dialog dialog3 = this.mHistorySyncInfoDialog;
                if (dialog3 != null && (window6 = dialog3.getWindow()) != null && (textView4 = (TextView) window6.findViewById(R.id.tv_history_sync_state)) != null) {
                    textView4.setText(getLanguageText(R.string.history_data_sync_success));
                }
                ProgressBar progressBar = null;
                if (this.mHistoryHasDownloadView == null) {
                    Dialog dialog4 = this.mHistorySyncInfoDialog;
                    this.mHistoryHasDownloadView = (dialog4 == null || (window5 = dialog4.getWindow()) == null) ? null : (TextView) window5.findViewById(R.id.tv_history_sync_progress);
                }
                if (this.mHistoryDownloadProgressView == null) {
                    Dialog dialog5 = this.mHistorySyncInfoDialog;
                    if (dialog5 != null && (window4 = dialog5.getWindow()) != null) {
                        progressBar = (ProgressBar) window4.findViewById(R.id.history_info_progress);
                    }
                    this.mHistoryDownloadProgressView = progressBar;
                }
                TextView textView7 = this.mHistoryHasDownloadView;
                if (textView7 != null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(100);
                    sb2.append('%');
                    textView7.setText(sb2.toString());
                }
                ProgressBar progressBar2 = this.mHistoryDownloadProgressView;
                if (progressBar2 != null) {
                    progressBar2.setProgress(100);
                }
                Dialog dialog6 = this.mHistorySyncInfoDialog;
                if (dialog6 != null && (window3 = dialog6.getWindow()) != null && (textView3 = (TextView) window3.findViewById(R.id.tv_action_stop)) != null) {
                    textView3.setVisibility(8);
                }
                Dialog dialog7 = this.mHistorySyncInfoDialog;
                if (dialog7 != null && (window2 = dialog7.getWindow()) != null && (textView2 = (TextView) window2.findViewById(R.id.tv_history_sync_tip)) != null) {
                    textView2.setVisibility(8);
                }
                Dialog dialog8 = this.mHistorySyncInfoDialog;
                if (dialog8 != null && (window = dialog8.getWindow()) != null && (textView = (TextView) window.findViewById(R.id.tv_action_retry)) != null) {
                    textView.setVisibility(8);
                }
            }
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_history_sync_progress);
        if (linearLayout != null) {
            linearLayout.postDelayed(new Runnable() { // from class: com.ido.life.module.home.HomeFragment.historyDataLoadSuccess.1
                @Override // java.lang.Runnable
                public final void run() {
                    if (((TextView) HomeFragment.this._$_findCachedViewById(com.ido.life.R.id.tv_history_sync_state)) != null) {
                        TextView tv_history_sync_state = (TextView) HomeFragment.this._$_findCachedViewById(com.ido.life.R.id.tv_history_sync_state);
                        Intrinsics.checkExpressionValueIsNotNull(tv_history_sync_state, "tv_history_sync_state");
                        tv_history_sync_state.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                    }
                    LinearLayout linearLayout2 = (LinearLayout) HomeFragment.this._$_findCachedViewById(com.ido.life.R.id.lay_history_sync_progress);
                    if (linearLayout2 != null) {
                        linearLayout2.setVisibility(8);
                    }
                }
            }, 1000L);
        }
    }

    @Override // com.ido.life.module.home.IHomeView
    public void updateHistoryPullProgress() {
        Window window;
        Window window2;
        ImageView imageView = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_sync_state_background);
        if (imageView != null) {
            imageView.setImageResource(R.mipmap.history_data_syncing);
        }
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_history_sync_state);
        if (textView != null) {
            textView.setText(getLanguageText(R.string.history_data_syncing));
        }
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        int historyDataDownloadProgress = ((HomeFragmentPresenter) p).getHistoryDataDownloadProgress();
        HomeHistoryProgress homeHistoryProgress = (HomeHistoryProgress) _$_findCachedViewById(com.ido.life.R.id.progress_sync_history);
        if (homeHistoryProgress != null) {
            homeHistoryProgress.updateProgress(historyDataDownloadProgress);
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_history_sync_progress);
        if (textView2 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(historyDataDownloadProgress);
            sb.append('%');
            textView2.setText(sb.toString());
        }
        Dialog dialog = this.mHistorySyncInfoDialog;
        if (dialog != null) {
            if (dialog == null) {
                Intrinsics.throwNpe();
            }
            if (dialog.isShowing()) {
                ProgressBar progressBar = null;
                if (this.mHistoryHasDownloadView == null) {
                    Dialog dialog2 = this.mHistorySyncInfoDialog;
                    this.mHistoryHasDownloadView = (dialog2 == null || (window2 = dialog2.getWindow()) == null) ? null : (TextView) window2.findViewById(R.id.tv_history_sync_progress);
                }
                if (this.mHistoryDownloadProgressView == null) {
                    Dialog dialog3 = this.mHistorySyncInfoDialog;
                    if (dialog3 != null && (window = dialog3.getWindow()) != null) {
                        progressBar = (ProgressBar) window.findViewById(R.id.history_info_progress);
                    }
                    this.mHistoryDownloadProgressView = progressBar;
                }
                TextView textView3 = this.mHistoryHasDownloadView;
                if (textView3 != null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(historyDataDownloadProgress);
                    sb2.append('%');
                    textView3.setText(sb2.toString());
                }
                ProgressBar progressBar2 = this.mHistoryDownloadProgressView;
                if (progressBar2 != null) {
                    progressBar2.setProgress(historyDataDownloadProgress);
                }
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        ViewTreeObserver viewTreeObserver;
        Window window;
        Window window2;
        TextView textView;
        Window window3;
        Window window4;
        Window window5;
        Window window6;
        Window window7;
        TextView textView2;
        Window window8;
        ImageView imageView;
        Window window9;
        Window window10;
        Window window11;
        Window window12;
        Window window13;
        Window window14;
        Window window15;
        TextView textView3;
        Window window16;
        ImageView imageView2;
        Window window17;
        Window window18;
        TextView textView4;
        Window window19;
        TextView textView5;
        Window window20;
        TextView textView6;
        Window window21;
        Window window22;
        Window window23;
        TextView textView7;
        Window window24;
        ImageView imageView3;
        ViewTreeObserver viewTreeObserver2;
        ViewTreeObserver viewTreeObserver3;
        Intrinsics.checkParameterIsNotNull(v, "v");
        switch (v.getId()) {
            case R.id.chat_step /* 2131362011 */:
            case R.id.lay_step_top /* 2131362771 */:
                HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
                if (homeFragmentPresenter != null) {
                    homeFragmentPresenter.gotoDetailPage(this.mActivity, 0);
                    Unit unit = Unit.INSTANCE;
                    return;
                }
                return;
            case R.id.img_active /* 2131362280 */:
                P p = this.mPresenter;
                if (p == 0) {
                    Intrinsics.throwNpe();
                }
                if (!((HomeFragmentPresenter) p).hasCalorie()) {
                    ((MainPannelCircleView) v).startAnim();
                }
                P p2 = this.mPresenter;
                if (p2 == 0) {
                    Intrinsics.throwNpe();
                }
                ((HomeFragmentPresenter) p2).gotoDetailPage(this.mActivity, 2);
                return;
            case R.id.img_device_state_close /* 2131362297 */:
                this.mHasShow = true;
                removeDeviceUnBindView();
                return;
            case R.id.img_press /* 2131362328 */:
                P p3 = this.mPresenter;
                if (p3 == 0) {
                    Intrinsics.throwNpe();
                }
                if (!((HomeFragmentPresenter) p3).hasActivityData()) {
                    ((MainPannelCircleView) v).startAnim();
                }
                P p4 = this.mPresenter;
                if (p4 == 0) {
                    Intrinsics.throwNpe();
                }
                ((HomeFragmentPresenter) p4).gotoDetailPage(this.mActivity, 3);
                return;
            case R.id.img_safe_verify_close /* 2131362335 */:
                LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_safe_verify);
                if (linearLayout != null) {
                    linearLayout.setVisibility(8);
                }
                View view = getView();
                if (view == null || (viewTreeObserver = view.getViewTreeObserver()) == null) {
                    return;
                }
                viewTreeObserver.addOnGlobalLayoutListener(this.mGlobalListener);
                Unit unit2 = Unit.INSTANCE;
                return;
            case R.id.img_walk /* 2131362369 */:
                P p5 = this.mPresenter;
                if (p5 == 0) {
                    Intrinsics.throwNpe();
                }
                if (!((HomeFragmentPresenter) p5).hasWalkData()) {
                    ((MainPannelCircleView) v).startAnim();
                }
                P p6 = this.mPresenter;
                if (p6 == 0) {
                    Intrinsics.throwNpe();
                }
                ((HomeFragmentPresenter) p6).gotoDetailPage(this.mActivity, 4);
                return;
            case R.id.lay_distance /* 2131362686 */:
                P p7 = this.mPresenter;
                if (p7 == 0) {
                    Intrinsics.throwNpe();
                }
                if (((HomeFragmentPresenter) p7).hasDistance()) {
                    Intent intent = new Intent(this.mActivity, (Class<?>) ChartDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(ChartDetailActivity.PAGE_TYPE, 1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    return;
                }
                P p8 = this.mPresenter;
                if (p8 == 0) {
                    Intrinsics.throwNpe();
                }
                if (((HomeFragmentPresenter) p8).isBind()) {
                    Intent intent2 = new Intent(this.mActivity, (Class<?>) HasBindNoDataActivity.class);
                    intent2.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 1);
                    startActivity(intent2);
                    return;
                } else {
                    Intent intent3 = new Intent(this.mActivity, (Class<?>) UnBindNoDataActivity.class);
                    intent3.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 1);
                    startActivity(intent3);
                    return;
                }
            case R.id.lay_history_sync /* 2131362701 */:
                Dialog dialog = this.mHistorySyncInfoDialog;
                if (dialog != null) {
                    if (dialog == null) {
                        Intrinsics.throwNpe();
                    }
                    if (dialog.isShowing()) {
                        return;
                    }
                }
                if (this.mHistorySyncInfoDialog == null) {
                    this.mHistorySyncInfoDialog = getHistoryInfoDialog();
                }
                HomeFragmentPresenter homeFragmentPresenter2 = (HomeFragmentPresenter) this.mPresenter;
                Integer numValueOf = homeFragmentPresenter2 != null ? Integer.valueOf(homeFragmentPresenter2.getHistoryDataPullState()) : null;
                if (numValueOf != null && numValueOf.intValue() == 3) {
                    Dialog dialog2 = this.mHistorySyncInfoDialog;
                    if (dialog2 != null && (window24 = dialog2.getWindow()) != null && (imageView3 = (ImageView) window24.findViewById(R.id.img_state)) != null) {
                        imageView3.setImageResource(R.mipmap.history_data_info_sync_success);
                        Unit unit3 = Unit.INSTANCE;
                    }
                    Dialog dialog3 = this.mHistorySyncInfoDialog;
                    if (dialog3 != null && (window23 = dialog3.getWindow()) != null && (textView7 = (TextView) window23.findViewById(R.id.tv_history_sync_state)) != null) {
                        textView7.setText(getLanguageText(R.string.history_data_sync_success));
                    }
                    Dialog dialog4 = this.mHistorySyncInfoDialog;
                    this.mHistoryHasDownloadView = (dialog4 == null || (window22 = dialog4.getWindow()) == null) ? null : (TextView) window22.findViewById(R.id.tv_history_sync_progress);
                    TextView textView8 = this.mHistoryHasDownloadView;
                    if (textView8 != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(100);
                        sb.append('%');
                        textView8.setText(sb.toString());
                    }
                    Dialog dialog5 = this.mHistorySyncInfoDialog;
                    this.mHistoryDownloadProgressView = (dialog5 == null || (window21 = dialog5.getWindow()) == null) ? null : (ProgressBar) window21.findViewById(R.id.history_info_progress);
                    ProgressBar progressBar = this.mHistoryDownloadProgressView;
                    if (progressBar != null) {
                        progressBar.setProgress(100);
                    }
                    Dialog dialog6 = this.mHistorySyncInfoDialog;
                    if (dialog6 != null && (window20 = dialog6.getWindow()) != null && (textView6 = (TextView) window20.findViewById(R.id.tv_action_stop)) != null) {
                        textView6.setVisibility(8);
                    }
                    Dialog dialog7 = this.mHistorySyncInfoDialog;
                    if (dialog7 != null && (window19 = dialog7.getWindow()) != null && (textView5 = (TextView) window19.findViewById(R.id.tv_history_sync_tip)) != null) {
                        textView5.setVisibility(8);
                    }
                    Dialog dialog8 = this.mHistorySyncInfoDialog;
                    if (dialog8 != null && (window18 = dialog8.getWindow()) != null && (textView4 = (TextView) window18.findViewById(R.id.tv_action_retry)) != null) {
                        textView4.setVisibility(8);
                    }
                    Dialog dialog9 = this.mHistorySyncInfoDialog;
                    TextView textView9 = (dialog9 == null || (window17 = dialog9.getWindow()) == null) ? null : (TextView) window17.findViewById(R.id.tv_action_ok);
                    if (textView9 != null) {
                        textView9.setOnClickListener(this);
                        Unit unit4 = Unit.INSTANCE;
                    }
                    if (textView9 != null) {
                        textView9.setText(getLanguageText(R.string.sync_ok));
                    }
                    Dialog dialog10 = this.mHistorySyncInfoDialog;
                    if (dialog10 != null) {
                        dialog10.show();
                        Unit unit5 = Unit.INSTANCE;
                        return;
                    }
                    return;
                }
                if (numValueOf != null && numValueOf.intValue() == 4) {
                    Dialog dialog11 = this.mHistorySyncInfoDialog;
                    if (dialog11 != null && (window16 = dialog11.getWindow()) != null && (imageView2 = (ImageView) window16.findViewById(R.id.img_state)) != null) {
                        imageView2.setImageResource(R.mipmap.history_data_info_sync_failed);
                        Unit unit6 = Unit.INSTANCE;
                    }
                    Dialog dialog12 = this.mHistorySyncInfoDialog;
                    if (dialog12 != null && (window15 = dialog12.getWindow()) != null && (textView3 = (TextView) window15.findViewById(R.id.tv_history_sync_state)) != null) {
                        textView3.setText(getLanguageText(R.string.history_data_sync_failed));
                    }
                    P p9 = this.mPresenter;
                    if (p9 == 0) {
                        Intrinsics.throwNpe();
                    }
                    int historyDataDownloadProgress = ((HomeFragmentPresenter) p9).getHistoryDataDownloadProgress();
                    Dialog dialog13 = this.mHistorySyncInfoDialog;
                    this.mHistoryHasDownloadView = (dialog13 == null || (window14 = dialog13.getWindow()) == null) ? null : (TextView) window14.findViewById(R.id.tv_history_sync_progress);
                    TextView textView10 = this.mHistoryHasDownloadView;
                    if (textView10 != null) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(historyDataDownloadProgress);
                        sb2.append('%');
                        textView10.setText(sb2.toString());
                    }
                    Dialog dialog14 = this.mHistorySyncInfoDialog;
                    this.mHistoryDownloadProgressView = (dialog14 == null || (window13 = dialog14.getWindow()) == null) ? null : (ProgressBar) window13.findViewById(R.id.history_info_progress);
                    ProgressBar progressBar2 = this.mHistoryDownloadProgressView;
                    if (progressBar2 != null) {
                        progressBar2.setProgress(historyDataDownloadProgress);
                    }
                    Dialog dialog15 = this.mHistorySyncInfoDialog;
                    TextView textView11 = (dialog15 == null || (window12 = dialog15.getWindow()) == null) ? null : (TextView) window12.findViewById(R.id.tv_action_stop);
                    if (textView11 != null) {
                        textView11.setVisibility(0);
                    }
                    if (textView11 != null) {
                        textView11.setText(getLanguageText(R.string.stop_sync));
                    }
                    if (textView11 != null) {
                        textView11.setOnClickListener(this);
                        Unit unit7 = Unit.INSTANCE;
                    }
                    Dialog dialog16 = this.mHistorySyncInfoDialog;
                    TextView textView12 = (dialog16 == null || (window11 = dialog16.getWindow()) == null) ? null : (TextView) window11.findViewById(R.id.tv_history_sync_tip);
                    if (textView12 != null) {
                        textView12.setVisibility(0);
                    }
                    if (textView12 != null) {
                        textView12.setText(getLanguageText(R.string.sync_all_history_tip));
                    }
                    Dialog dialog17 = this.mHistorySyncInfoDialog;
                    TextView textView13 = (dialog17 == null || (window10 = dialog17.getWindow()) == null) ? null : (TextView) window10.findViewById(R.id.tv_action_retry);
                    if (textView13 != null) {
                        textView13.setVisibility(0);
                    }
                    if (textView13 != null) {
                        textView13.setText(getLanguageText(R.string.device_retry));
                    }
                    if (textView13 != null) {
                        textView13.setOnClickListener(this);
                        Unit unit8 = Unit.INSTANCE;
                    }
                    Dialog dialog18 = this.mHistorySyncInfoDialog;
                    TextView textView14 = (dialog18 == null || (window9 = dialog18.getWindow()) == null) ? null : (TextView) window9.findViewById(R.id.tv_action_ok);
                    if (textView14 != null) {
                        textView14.setOnClickListener(this);
                        Unit unit9 = Unit.INSTANCE;
                    }
                    if (textView14 != null) {
                        textView14.setText(getLanguageText(R.string.sync_ok));
                    }
                    Dialog dialog19 = this.mHistorySyncInfoDialog;
                    if (dialog19 != null) {
                        dialog19.show();
                        Unit unit10 = Unit.INSTANCE;
                        return;
                    }
                    return;
                }
                if (numValueOf != null && numValueOf.intValue() == 2) {
                    Dialog dialog20 = this.mHistorySyncInfoDialog;
                    if (dialog20 != null && (window8 = dialog20.getWindow()) != null && (imageView = (ImageView) window8.findViewById(R.id.img_state)) != null) {
                        imageView.setImageResource(R.mipmap.history_data_info_syncing);
                        Unit unit11 = Unit.INSTANCE;
                    }
                    Dialog dialog21 = this.mHistorySyncInfoDialog;
                    if (dialog21 != null && (window7 = dialog21.getWindow()) != null && (textView2 = (TextView) window7.findViewById(R.id.tv_history_sync_state)) != null) {
                        textView2.setText(getLanguageText(R.string.history_data_info_syncing));
                    }
                    P p10 = this.mPresenter;
                    if (p10 == 0) {
                        Intrinsics.throwNpe();
                    }
                    int historyDataDownloadProgress2 = ((HomeFragmentPresenter) p10).getHistoryDataDownloadProgress();
                    Dialog dialog22 = this.mHistorySyncInfoDialog;
                    this.mHistoryHasDownloadView = (dialog22 == null || (window6 = dialog22.getWindow()) == null) ? null : (TextView) window6.findViewById(R.id.tv_history_sync_progress);
                    TextView textView15 = this.mHistoryHasDownloadView;
                    if (textView15 != null) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(historyDataDownloadProgress2);
                        sb3.append('%');
                        textView15.setText(sb3.toString());
                    }
                    Dialog dialog23 = this.mHistorySyncInfoDialog;
                    this.mHistoryDownloadProgressView = (dialog23 == null || (window5 = dialog23.getWindow()) == null) ? null : (ProgressBar) window5.findViewById(R.id.history_info_progress);
                    ProgressBar progressBar3 = this.mHistoryDownloadProgressView;
                    if (progressBar3 != null) {
                        progressBar3.setProgress(historyDataDownloadProgress2);
                    }
                    Dialog dialog24 = this.mHistorySyncInfoDialog;
                    TextView textView16 = (dialog24 == null || (window4 = dialog24.getWindow()) == null) ? null : (TextView) window4.findViewById(R.id.tv_action_stop);
                    if (textView16 != null) {
                        textView16.setVisibility(0);
                    }
                    if (textView16 != null) {
                        textView16.setText(getLanguageText(R.string.stop_sync));
                    }
                    if (textView16 != null) {
                        textView16.setOnClickListener(this);
                        Unit unit12 = Unit.INSTANCE;
                    }
                    Dialog dialog25 = this.mHistorySyncInfoDialog;
                    TextView textView17 = (dialog25 == null || (window3 = dialog25.getWindow()) == null) ? null : (TextView) window3.findViewById(R.id.tv_history_sync_tip);
                    if (textView17 != null) {
                        textView17.setVisibility(0);
                    }
                    if (textView17 != null) {
                        textView17.setText(getLanguageText(R.string.sync_all_history_tip));
                    }
                    Dialog dialog26 = this.mHistorySyncInfoDialog;
                    if (dialog26 != null && (window2 = dialog26.getWindow()) != null && (textView = (TextView) window2.findViewById(R.id.tv_action_retry)) != null) {
                        textView.setVisibility(8);
                    }
                    Dialog dialog27 = this.mHistorySyncInfoDialog;
                    TextView textView18 = (dialog27 == null || (window = dialog27.getWindow()) == null) ? null : (TextView) window.findViewById(R.id.tv_action_ok);
                    if (textView18 != null) {
                        textView18.setOnClickListener(this);
                        Unit unit13 = Unit.INSTANCE;
                    }
                    if (textView18 != null) {
                        textView18.setText(getLanguageText(R.string.sync_ok));
                    }
                    Dialog dialog28 = this.mHistorySyncInfoDialog;
                    if (dialog28 != null) {
                        dialog28.show();
                        Unit unit14 = Unit.INSTANCE;
                        return;
                    }
                    return;
                }
                return;
            case R.id.lay_item /* 2131362706 */:
                if (v.getTag() instanceof Integer) {
                    P p11 = this.mPresenter;
                    if (p11 == 0) {
                        Intrinsics.throwNpe();
                    }
                    HomeFragmentPresenter homeFragmentPresenter3 = (HomeFragmentPresenter) p11;
                    BaseActivity baseActivity = this.mActivity;
                    Object tag = v.getTag();
                    if (tag == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                    }
                    homeFragmentPresenter3.gotoDetailPage(baseActivity, ((Integer) tag).intValue());
                    return;
                }
                return;
            case R.id.lay_more /* 2131362720 */:
                PopupWindow popupWindow = this.mMorePop;
                if (popupWindow != null) {
                    if (popupWindow == null) {
                        Intrinsics.throwNpe();
                    }
                    if (popupWindow.isShowing()) {
                        PopupWindow popupWindow2 = this.mMorePop;
                        if (popupWindow2 == null) {
                            Intrinsics.throwNpe();
                        }
                        popupWindow2.dismiss();
                        return;
                    }
                }
                if (this.mMorePop == null) {
                    this.mMorePop = getMorePopWindow();
                }
                PopupWindow popupWindow3 = this.mMorePop;
                if (popupWindow3 == null) {
                    Intrinsics.throwNpe();
                }
                popupWindow3.showAsDropDown((HomeDeviceStateView) _$_findCachedViewById(com.ido.life.R.id.home_device_state), 0, 0, 5);
                return;
            case R.id.lay_safe_verify_action /* 2131362752 */:
                this.mShouldCheckEmailBindState = true;
                Intent intent4 = new Intent(this.mActivity, (Class<?>) CheckEmailActivity.class);
                intent4.putExtra(Constants.IS_FROM_HOME, true);
                startActivity(intent4);
                return;
            case R.id.ll_activity_bottom /* 2131362922 */:
            case R.id.ll_activity_top /* 2131362923 */:
                P p12 = this.mPresenter;
                if (p12 == 0) {
                    Intrinsics.throwNpe();
                }
                ((HomeFragmentPresenter) p12).gotoDetailPage(this.mActivity, 2);
                return;
            case R.id.ll_press_bottom /* 2131362955 */:
            case R.id.ll_press_top /* 2131362956 */:
                P p13 = this.mPresenter;
                if (p13 == 0) {
                    Intrinsics.throwNpe();
                }
                ((HomeFragmentPresenter) p13).gotoDetailPage(this.mActivity, 3);
                return;
            case R.id.ll_walk_bottom /* 2131362985 */:
            case R.id.ll_walk_top /* 2131362986 */:
                P p14 = this.mPresenter;
                if (p14 == 0) {
                    Intrinsics.throwNpe();
                }
                ((HomeFragmentPresenter) p14).gotoDetailPage(this.mActivity, 4);
                return;
            case R.id.step_progress_circle /* 2131363618 */:
                if (this.mPresenter != 0) {
                    P p15 = this.mPresenter;
                    if (p15 == 0) {
                        Intrinsics.throwNpe();
                    }
                    if (!((HomeFragmentPresenter) p15).hasStepData()) {
                        ((MainPannelCircleView) v).startAnim();
                    }
                }
                HomeFragmentPresenter homeFragmentPresenter4 = (HomeFragmentPresenter) this.mPresenter;
                if (homeFragmentPresenter4 != null) {
                    homeFragmentPresenter4.gotoDetailPage(this.mActivity, 0);
                    Unit unit15 = Unit.INSTANCE;
                    return;
                }
                return;
            case R.id.tv_action_ok /* 2131363786 */:
                Dialog dialog29 = this.mHistorySyncInfoDialog;
                if (dialog29 != null) {
                    dialog29.dismiss();
                    Unit unit16 = Unit.INSTANCE;
                }
                P p16 = this.mPresenter;
                if (p16 == 0) {
                    Intrinsics.throwNpe();
                }
                if (((HomeFragmentPresenter) p16).getHistoryDataPullState() == 3) {
                    FrameLayout frameLayout = (FrameLayout) _$_findCachedViewById(com.ido.life.R.id.lay_history_sync);
                    if (frameLayout != null) {
                        frameLayout.setVisibility(8);
                    }
                    View view2 = getView();
                    if (view2 != null && (viewTreeObserver2 = view2.getViewTreeObserver()) != null) {
                        viewTreeObserver2.addOnGlobalLayoutListener(this.mGlobalListener);
                        Unit unit17 = Unit.INSTANCE;
                    }
                    SPHelper.saveAutoShowHistoryDataPullState(false);
                    return;
                }
                return;
            case R.id.tv_action_retry /* 2131363787 */:
                if (NetworkUtil.isConnected(this.mActivity)) {
                    Dialog dialog30 = this.mHistorySyncInfoDialog;
                    if (dialog30 != null) {
                        dialog30.dismiss();
                        Unit unit18 = Unit.INSTANCE;
                    }
                    DataDownLoadService.Companion companion = DataDownLoadService.INSTANCE;
                    RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
                    companion.startSingleUserTask(runTimeUtil.getUserId());
                    return;
                }
                CommonLogUtil.d(TAG, "用户主动请求重新下拉历史数据，但是这个时候没有网络。");
                NormalToast.showToast(getLanguageText(R.string.public_net_unuse));
                return;
            case R.id.tv_action_stop /* 2131363788 */:
                DataDownLoadService.Companion companion2 = DataDownLoadService.INSTANCE;
                RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
                companion2.stopSingleUserTask(runTimeUtil2.getUserId(), false, true);
                Dialog dialog31 = this.mHistorySyncInfoDialog;
                if (dialog31 != null) {
                    dialog31.dismiss();
                    Unit unit19 = Unit.INSTANCE;
                }
                SPHelper.saveAutoShowHistoryDataPullState(false);
                FrameLayout frameLayout2 = (FrameLayout) _$_findCachedViewById(com.ido.life.R.id.lay_history_sync);
                if (frameLayout2 != null) {
                    frameLayout2.setVisibility(8);
                }
                View view3 = getView();
                if (view3 == null || (viewTreeObserver3 = view3.getViewTreeObserver()) == null) {
                    return;
                }
                viewTreeObserver3.addOnGlobalLayoutListener(this.mGlobalListener);
                Unit unit20 = Unit.INSTANCE;
                return;
            case R.id.tv_device_state_action /* 2131363889 */:
                this.mHasShow = true;
                startActivity(new Intent(this.mActivity, (Class<?>) ChoiceBlueTypeActivity.class));
                removeDeviceUnBindView();
                return;
            case R.id.tv_edit_card /* 2131363913 */:
                startActivityForResult(new Intent(this.mActivity, (Class<?>) CustomCardActivity.class), 88);
                return;
            default:
                return;
        }
    }

    private final PopupWindow getMorePopWindow() {
        final PopupWindow popupWindow = new PopupWindow(-2, -2);
        View viewInflate = getLayoutInflater().inflate(R.layout.device_more_pop_layout, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_add_device);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_scan);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.home.HomeFragment.getMorePopWindow.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment homeFragment = HomeFragment.this;
                homeFragment.startActivity(new Intent(homeFragment.mActivity, (Class<?>) ChoiceBlueTypeActivity.class));
                popupWindow.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.home.HomeFragment.getMorePopWindow.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment homeFragment = HomeFragment.this;
                homeFragment.startActivity(new Intent(homeFragment.mActivity, (Class<?>) ScanCodeActivity.class));
                popupWindow.dismiss();
            }
        });
        popupWindow.setContentView(viewInflate);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.pop_more);
        return popupWindow;
    }

    private final void initDefaultBg() {
        this.mGradientDrawable = new GradientDrawable();
        GradientDrawable gradientDrawable = this.mGradientDrawable;
        if (gradientDrawable == null) {
            Intrinsics.throwNpe();
        }
        gradientDrawable.setGradientType(0);
        GradientDrawable gradientDrawable2 = this.mGradientDrawable;
        if (gradientDrawable2 == null) {
            Intrinsics.throwNpe();
        }
        gradientDrawable2.setColors(new int[]{getResources().getColor(R.color.black), getResources().getColor(R.color.color_121212), getResources().getColor(R.color.black)});
        GradientDrawable gradientDrawable3 = this.mGradientDrawable;
        if (gradientDrawable3 == null) {
            Intrinsics.throwNpe();
        }
        gradientDrawable3.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
        GradientDrawable gradientDrawable4 = this.mGradientDrawable;
        if (gradientDrawable4 == null) {
            Intrinsics.throwNpe();
        }
        gradientDrawable4.setDither(true);
        GradientDrawable gradientDrawable5 = this.mGradientDrawable;
        if (gradientDrawable5 == null) {
            Intrinsics.throwNpe();
        }
        gradientDrawable5.setAutoMirrored(false);
        GradientDrawable gradientDrawable6 = this.mGradientDrawable;
        if (gradientDrawable6 == null) {
            Intrinsics.throwNpe();
        }
        gradientDrawable6.setGradientCenter(0.5f, 0.5f);
    }

    private final void showOpenGpsDialog() {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.get_gps_permission_title), getLanguageText(R.string.get_gps_permission_content), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true);
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.home.HomeFragment.showOpenGpsDialog.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.home.HomeFragment.showOpenGpsDialog.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                try {
                    Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
                    intent.addFlags(268435456);
                    HomeFragment.this.startActivity(intent);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.show(getFragmentManager());
    }

    private final void showBlueToothPermissionDialog() {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.get_bluetooth_permission_title), getLanguageText(R.string.get_bluetooth_permission_content), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true);
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.home.HomeFragment.showBlueToothPermissionDialog.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.home.HomeFragment.showBlueToothPermissionDialog.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                try {
                    Intent intent = new Intent();
                    intent.addFlags(268435456);
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    BaseActivity mActivity = HomeFragment.this.mActivity;
                    Intrinsics.checkExpressionValueIsNotNull(mActivity, "mActivity");
                    intent.setData(Uri.fromParts("package", mActivity.getPackageName(), null));
                    HomeFragment.this.startActivity(intent);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.show(getFragmentManager());
    }

    @Override // com.ido.life.base.IDeviceDataListener
    public void syncActiveDataSuccess() {
        refreshStepCard();
        refreshPanelCard();
    }

    @Override // com.ido.life.base.IDeviceDataListener
    public void syncSleepDataSuccess() {
        refreshSleepCard();
    }

    @Override // com.ido.life.base.IDeviceDataListener
    public void syncHeartRateDataSuccess() {
        refreshHeartRateCard();
    }

    @Override // com.ido.life.base.IDeviceDataListener
    public void syncSportDataSuccess() {
        refreshSportCard();
    }

    @Override // com.ido.life.base.IDeviceDataListener
    public void syncBloodDataSuccess() {
        refreshBloodOxyCard();
    }

    @Override // com.ido.life.base.IDeviceDataListener
    public void syncPressureDataSuccess() {
        refreshPressureCard();
    }

    @Override // com.ido.life.base.IDeviceDataListener
    public void syncSwimmingDataSuccess() {
        refreshSportCard();
        refreshOxygenUptakeCard();
    }

    @Override // com.ido.life.base.IDeviceDataListener
    public void syncVolumeDataSuccess() {
        refreshVolumeCard();
    }

    /* JADX INFO: compiled from: HomeFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J \u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0016¨\u0006\f"}, d2 = {"Lcom/ido/life/module/home/HomeFragment$HomeScrollListener;", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "(Lcom/ido/life/module/home/HomeFragment;)V", "onScrollStateChanged", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "newState", "", "onScrolled", "dx", "dy", "app_release"}, k = 1, mv = {1, 1, 16})
    private final class HomeScrollListener extends RecyclerView.OnScrollListener {
        public HomeScrollListener() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
            super.onScrolled(recyclerView, dx, dy);
            HomeFragment.this.resetBackgroundStyle();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            HomeFragmentPresenter homeFragmentPresenterAccess$getMPresenter$p;
            Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
            super.onScrollStateChanged(recyclerView, newState);
            if (newState != 0 || (homeFragmentPresenterAccess$getMPresenter$p = HomeFragment.access$getMPresenter$p(HomeFragment.this)) == null) {
                return;
            }
            homeFragmentPresenterAccess$getMPresenter$p.startUpdateTime();
        }
    }

    /* JADX INFO: compiled from: HomeFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/ido/life/module/home/HomeFragment$HomeGlobalLayoutListener;", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "(Lcom/ido/life/module/home/HomeFragment;)V", "onGlobalLayout", "", "app_release"}, k = 1, mv = {1, 1, 16})
    private final class HomeGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        public HomeGlobalLayoutListener() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            ViewTreeObserver viewTreeObserver;
            View view = HomeFragment.this.getView();
            if (view != null && (viewTreeObserver = view.getViewTreeObserver()) != null) {
                viewTreeObserver.removeOnGlobalLayoutListener(this);
            }
            HomeFragment.this.resetBackgroundStyle();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetBackgroundStyle() {
        if (this.mGradientDrawable == null) {
            return;
        }
        int iCaluteBackgroundSize = caluteBackgroundSize();
        RecyclerView recycler_view_home = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
        Intrinsics.checkExpressionValueIsNotNull(recycler_view_home, "recycler_view_home");
        RecyclerView.LayoutManager layoutManager = recycler_view_home.getLayoutManager();
        if (layoutManager == null) {
            Intrinsics.throwNpe();
        }
        View viewFindViewByPosition = layoutManager.findViewByPosition(findStepCardPosition());
        if (viewFindViewByPosition != null) {
            if (viewFindViewByPosition.getTop() < -20) {
                GradientDrawable gradientDrawable = this.mGradientDrawable;
                if (gradientDrawable == null) {
                    Intrinsics.throwNpe();
                }
                gradientDrawable.setColors(new int[]{getResources().getColor(R.color.black), getResources().getColor(R.color.black)});
                GradientDrawable gradientDrawable2 = this.mGradientDrawable;
                if (gradientDrawable2 == null) {
                    Intrinsics.throwNpe();
                }
                gradientDrawable2.setGradientCenter(0.5f, 1.0f);
                ImageView lay_top_bg = (ImageView) _$_findCachedViewById(com.ido.life.R.id.lay_top_bg);
                Intrinsics.checkExpressionValueIsNotNull(lay_top_bg, "lay_top_bg");
                ViewGroup.LayoutParams layoutParams = lay_top_bg.getLayoutParams();
                HomeDeviceStateView home_device_state = (HomeDeviceStateView) _$_findCachedViewById(com.ido.life.R.id.home_device_state);
                Intrinsics.checkExpressionValueIsNotNull(home_device_state, "home_device_state");
                layoutParams.height = home_device_state.getBottom();
                ImageView lay_top_bg2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.lay_top_bg);
                Intrinsics.checkExpressionValueIsNotNull(lay_top_bg2, "lay_top_bg");
                lay_top_bg2.setLayoutParams(layoutParams);
            } else {
                ImageView lay_top_bg3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.lay_top_bg);
                Intrinsics.checkExpressionValueIsNotNull(lay_top_bg3, "lay_top_bg");
                ViewGroup.LayoutParams layoutParams2 = lay_top_bg3.getLayoutParams();
                RecyclerView recycler_view_home2 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                Intrinsics.checkExpressionValueIsNotNull(recycler_view_home2, "recycler_view_home");
                layoutParams2.height = iCaluteBackgroundSize - recycler_view_home2.getScrollY();
                ImageView lay_top_bg4 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.lay_top_bg);
                Intrinsics.checkExpressionValueIsNotNull(lay_top_bg4, "lay_top_bg");
                lay_top_bg4.setLayoutParams(layoutParams2);
                GradientDrawable gradientDrawable3 = this.mGradientDrawable;
                if (gradientDrawable3 == null) {
                    Intrinsics.throwNpe();
                }
                gradientDrawable3.setColors(new int[]{getResources().getColor(R.color.black), getResources().getColor(R.color.black), getResources().getColor(R.color.black)});
                GradientDrawable gradientDrawable4 = this.mGradientDrawable;
                if (gradientDrawable4 == null) {
                    Intrinsics.throwNpe();
                }
                HomeDeviceStateView home_device_state2 = (HomeDeviceStateView) _$_findCachedViewById(com.ido.life.R.id.home_device_state);
                Intrinsics.checkExpressionValueIsNotNull(home_device_state2, "home_device_state");
                gradientDrawable4.setGradientCenter(0.5f, (home_device_state2.getBottom() * 1.0f) / layoutParams2.height);
            }
        } else {
            GradientDrawable gradientDrawable5 = this.mGradientDrawable;
            if (gradientDrawable5 == null) {
                Intrinsics.throwNpe();
            }
            gradientDrawable5.setColors(new int[]{getResources().getColor(R.color.black), getResources().getColor(R.color.black)});
            GradientDrawable gradientDrawable6 = this.mGradientDrawable;
            if (gradientDrawable6 == null) {
                Intrinsics.throwNpe();
            }
            gradientDrawable6.setGradientCenter(0.5f, 1.0f);
            ImageView lay_top_bg5 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.lay_top_bg);
            Intrinsics.checkExpressionValueIsNotNull(lay_top_bg5, "lay_top_bg");
            ViewGroup.LayoutParams layoutParams3 = lay_top_bg5.getLayoutParams();
            HomeDeviceStateView home_device_state3 = (HomeDeviceStateView) _$_findCachedViewById(com.ido.life.R.id.home_device_state);
            Intrinsics.checkExpressionValueIsNotNull(home_device_state3, "home_device_state");
            layoutParams3.height = home_device_state3.getBottom();
            ImageView lay_top_bg6 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.lay_top_bg);
            Intrinsics.checkExpressionValueIsNotNull(lay_top_bg6, "lay_top_bg");
            lay_top_bg6.setLayoutParams(layoutParams3);
        }
        ImageView lay_top_bg7 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.lay_top_bg);
        Intrinsics.checkExpressionValueIsNotNull(lay_top_bg7, "lay_top_bg");
        lay_top_bg7.setBackground(this.mGradientDrawable);
    }

    private final int caluteBackgroundSize() {
        SmartRefreshLayout refreshLayout = (SmartRefreshLayout) _$_findCachedViewById(com.ido.life.R.id.refreshLayout);
        Intrinsics.checkExpressionValueIsNotNull(refreshLayout, "refreshLayout");
        return refreshLayout.getTop() + ((measureStepUIHeight() * 2) / 3) + (showDeviceUnbindUI() ? measureUnBindUIHeight() : 0);
    }

    private final int findStepCardPosition() {
        if (!(!this.mTypeDataList.isEmpty())) {
            return 0;
        }
        int size = this.mTypeDataList.size();
        for (int i = 0; i < size; i++) {
            MainData mainData = this.mTypeDataList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
            if (mainData.getViewType() == 1) {
                return i;
            }
        }
        return 0;
    }

    private final int measureUnBindUIHeight() {
        int i = this.mUnbindUIHeight;
        if (i > 0) {
            return i;
        }
        if (this.mAdapter != null && (!this.mTypeDataList.isEmpty())) {
            int size = this.mTypeDataList.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                MainData mainData = this.mTypeDataList.get(i2);
                Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
                if (mainData.getViewType() == 0) {
                    RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                    RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i2) : null;
                    if ((viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null) != null) {
                        if (viewHolderFindViewHolderForAdapterPosition == null) {
                            Intrinsics.throwNpe();
                        }
                        View view = viewHolderFindViewHolderForAdapterPosition.itemView;
                        Intrinsics.checkExpressionValueIsNotNull(view, "holder!!.itemView");
                        this.mUnbindUIHeight = view.getHeight();
                    }
                } else {
                    i2++;
                }
            }
        }
        return this.mUnbindUIHeight;
    }

    private final int measureStepUIHeight() {
        int i = this.mStepUIHeight;
        if (i > 0) {
            return i;
        }
        if (this.mAdapter != null && (!this.mTypeDataList.isEmpty())) {
            int size = this.mTypeDataList.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                MainData mainData = this.mTypeDataList.get(i2);
                Intrinsics.checkExpressionValueIsNotNull(mainData, "mTypeDataList[i]");
                if (mainData.getViewType() == 1) {
                    RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view_home);
                    RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = recyclerView != null ? recyclerView.findViewHolderForAdapterPosition(i2) : null;
                    if ((viewHolderFindViewHolderForAdapterPosition != null ? viewHolderFindViewHolderForAdapterPosition.itemView : null) != null) {
                        if (viewHolderFindViewHolderForAdapterPosition == null) {
                            Intrinsics.throwNpe();
                        }
                        View view = viewHolderFindViewHolderForAdapterPosition.itemView;
                        Intrinsics.checkExpressionValueIsNotNull(view, "holder!!.itemView");
                        this.mStepUIHeight = view.getHeight();
                    }
                } else {
                    i2++;
                }
            }
        }
        return this.mStepUIHeight;
    }

    private final boolean showDeviceUnbindUI() {
        if (!this.mTypeDataList.isEmpty()) {
            for (MainData item : this.mTypeDataList) {
                Intrinsics.checkExpressionValueIsNotNull(item, "item");
                if (item.getViewType() == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private final Dialog getHistoryInfoDialog() {
        View viewInflate = getLayoutInflater().inflate(R.layout.dialog_history_data_info_layout, (ViewGroup) null);
        Dialog dialog = new Dialog(this.mActivity, R.style.data_sync_dialog_translate);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -2);
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        layoutParams.width = (resources.getDisplayMetrics().widthPixels * 4) / 5;
        dialog.setContentView(viewInflate, layoutParams);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    @Override // com.ido.life.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        BaseMap<View, ?> baseMap = this.mMapModel;
        if (baseMap != null) {
            baseMap.onDestroy();
        }
        HomeDeviceStateView homeDeviceStateView = (HomeDeviceStateView) _$_findCachedViewById(com.ido.life.R.id.home_device_state);
        if (homeDeviceStateView != null) {
            homeDeviceStateView.destroy();
        }
        HomeFragmentPresenter homeFragmentPresenter = (HomeFragmentPresenter) this.mPresenter;
        if (homeFragmentPresenter != null) {
            homeFragmentPresenter.stopUpdateTime();
        }
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }
}