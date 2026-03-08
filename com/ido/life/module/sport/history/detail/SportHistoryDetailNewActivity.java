package com.ido.life.module.sport.history.detail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import com.boat.Xtend.two.R;
import com.google.android.material.badge.BadgeDrawable;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.bean.LatLngBean;
import com.ido.life.customview.CirclePercentView;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.TrackPointView;
import com.ido.life.customview.charter.CubicSportStepChartBar;
import com.ido.life.customview.viewgroup.BottomSportView;
import com.ido.life.customview.viewgroup.SportKmSpace;
import com.ido.life.customview.viewgroup.UnitView;
import com.ido.life.database.model.SportHealth;
import com.ido.life.dialog.SportNameExplainDialogFragment;
import com.ido.life.dialog.SportShareDialogFragment;
import com.ido.life.log.SportLogHelper;
import com.ido.life.module.sport.history.KmProgress;
import com.ido.life.module.sport.history.fragment.TrainFragment;
import com.ido.life.module.sport.history.rate.RateFragment;
import com.ido.life.module.sport.map.BaseMap;
import com.ido.life.module.sport.map.MapFactory;
import com.ido.life.module.sport.map.MapScreenShotCallback;
import com.ido.life.module.sport.view.SportDeletePopupWindow;
import com.ido.life.module.user.feedback.FeedbackActivity;
import com.ido.life.util.DateUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.ShareUtil;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SportHistoryDetailNewActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\bE\n\u0002\u0018\u0002\n\u0002\b/\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 à\u00012\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0002à\u0001B\u0005¢\u0006\u0002\u0010\u0005J2\u0010,\u001a\u00020-2\u0010\u0010.\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u00142\u0006\u0010/\u001a\u00020\u000b2\u0006\u00100\u001a\u00020\u000b2\u0006\u00101\u001a\u00020\u000bH\u0016J\b\u00102\u001a\u00020\u000bH\u0014J\n\u00103\u001a\u0004\u0018\u00010\tH\u0004J\b\u00104\u001a\u00020-H\u0016J\b\u00105\u001a\u00020-H\u0014J\b\u00106\u001a\u00020-H\u0014J\u0012\u00107\u001a\u00020-2\b\u00108\u001a\u0004\u0018\u000109H\u0002J\b\u0010:\u001a\u00020-H\u0004J\b\u0010;\u001a\u00020-H\u0016J\u0012\u0010<\u001a\u00020-2\b\u0010=\u001a\u0004\u0018\u00010$H\u0016J\u0012\u0010>\u001a\u00020-2\b\u00108\u001a\u0004\u0018\u000109H\u0014J\b\u0010?\u001a\u00020-H\u0014J \u0010@\u001a\u00020-2\u0006\u0010/\u001a\u00020\u000b2\u0006\u00100\u001a\u00020\u000b2\u0006\u00101\u001a\u00020\u000bH\u0002J\u0010\u0010A\u001a\u00020-2\u0006\u0010B\u001a\u00020\u0007H\u0016J\u0018\u0010C\u001a\u00020-2\u0006\u0010D\u001a\u00020\t2\u0006\u0010E\u001a\u00020\u000bH\u0016J\u0010\u0010F\u001a\u00020-2\u0006\u0010B\u001a\u00020\u0007H\u0016J\u0012\u0010G\u001a\u00020-2\b\u0010H\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010I\u001a\u00020-2\b\u0010J\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010K\u001a\u00020-2\b\u0010L\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010M\u001a\u00020-2\b\u0010N\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010O\u001a\u00020-2\b\u0010P\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010Q\u001a\u00020-2\b\u0010R\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010S\u001a\u00020-2\b\u0010T\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010U\u001a\u00020-2\b\u0010V\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010W\u001a\u00020-2\b\u0010X\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010Y\u001a\u00020-2\b\u0010Z\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010[\u001a\u00020-2\b\u0010\\\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010]\u001a\u00020-2\b\u0010^\u001a\u0004\u0018\u00010\tH\u0016J\u001e\u0010_\u001a\u00020-2\f\u0010`\u001a\b\u0012\u0004\u0012\u00020b0a2\u0006\u0010c\u001a\u00020\tH\u0016J\u001e\u0010d\u001a\u00020-2\f\u0010`\u001a\b\u0012\u0004\u0012\u00020b0a2\u0006\u0010c\u001a\u00020\tH\u0016J\u0010\u0010e\u001a\u00020-2\u0006\u0010f\u001a\u00020\u0007H\u0016J\u0012\u0010g\u001a\u00020-2\b\u0010h\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010i\u001a\u00020-2\b\u0010j\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010k\u001a\u00020-2\u0006\u0010l\u001a\u00020\u0007H\u0016J\u0010\u0010m\u001a\u00020-2\u0006\u0010B\u001a\u00020\u0007H\u0016J\u0010\u0010n\u001a\u00020-2\u0006\u0010B\u001a\u00020\u0007H\u0016J\u0018\u0010o\u001a\u00020-2\u0006\u0010p\u001a\u00020\t2\u0006\u0010E\u001a\u00020\u000bH\u0016J\u0010\u0010q\u001a\u00020-2\u0006\u0010B\u001a\u00020\u0007H\u0016J\u0012\u0010r\u001a\u00020-2\b\u0010s\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010t\u001a\u00020-2\b\u0010u\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010v\u001a\u00020-2\b\u0010w\u001a\u0004\u0018\u00010\tH\u0016J\u0018\u0010x\u001a\u00020-2\u0006\u0010y\u001a\u00020\u000b2\u0006\u0010z\u001a\u00020\u000bH\u0016J\u0012\u0010{\u001a\u00020-2\b\u0010|\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010}\u001a\u00020-2\b\u0010~\u001a\u0004\u0018\u00010\tH\u0016J\u0013\u0010\u007f\u001a\u00020-2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0014\u0010\u0081\u0001\u001a\u00020-2\t\u0010\u0082\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0014\u0010\u0083\u0001\u001a\u00020-2\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0014\u0010\u0085\u0001\u001a\u00020-2\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0011\u0010\u0087\u0001\u001a\u00020-2\u0006\u0010B\u001a\u00020\u0007H\u0016J\u001a\u0010\u0088\u0001\u001a\u00020-2\u0006\u0010B\u001a\u00020\u00072\u0007\u0010\u0089\u0001\u001a\u00020\tH\u0016J\u001f\u0010\u008a\u0001\u001a\u00020-2\t\u0010\u008b\u0001\u001a\u0004\u0018\u00010\t2\t\u0010\u008c\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0014\u0010\u008d\u0001\u001a\u00020-2\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0014\u0010\u008f\u0001\u001a\u00020-2\t\u0010\u0090\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0014\u0010\u0091\u0001\u001a\u00020-2\t\u0010\u0092\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0014\u0010\u0093\u0001\u001a\u00020-2\t\u0010\u0094\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0014\u0010\u0095\u0001\u001a\u00020-2\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0014\u0010\u0097\u0001\u001a\u00020-2\t\u0010\u0098\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0014\u0010\u0099\u0001\u001a\u00020-2\t\u0010\u009a\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0014\u0010\u009b\u0001\u001a\u00020-2\t\u0010\u009c\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0014\u0010\u009d\u0001\u001a\u00020-2\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0014\u0010\u009f\u0001\u001a\u00020-2\t\u0010 \u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010¡\u0001\u001a\u00020-2\u0007\u0010¢\u0001\u001a\u00020\tH\u0016J\u0011\u0010£\u0001\u001a\u00020-2\u0006\u0010E\u001a\u00020\u000bH\u0016J\u0014\u0010¤\u0001\u001a\u00020-2\t\u0010¥\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u001b\u0010¦\u0001\u001a\u00020-2\u0010\u0010§\u0001\u001a\u000b\u0012\u0005\u0012\u00030¨\u0001\u0018\u00010\u0014H\u0016J\u0014\u0010©\u0001\u001a\u00020-2\t\u0010ª\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010«\u0001\u001a\u00020-2\u0007\u0010¬\u0001\u001a\u00020\u000bH\u0016J\u0012\u0010\u00ad\u0001\u001a\u00020-2\u0007\u0010®\u0001\u001a\u00020\u000bH\u0016J\u0012\u0010¯\u0001\u001a\u00020-2\u0007\u0010°\u0001\u001a\u00020\u000bH\u0016J\u0012\u0010±\u0001\u001a\u00020-2\u0007\u0010²\u0001\u001a\u00020\u000bH\u0016J\u0014\u0010³\u0001\u001a\u00020-2\t\u0010´\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0014\u0010µ\u0001\u001a\u00020-2\t\u0010¶\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0014\u0010·\u0001\u001a\u00020-2\t\u0010¸\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0014\u0010¹\u0001\u001a\u00020-2\t\u0010º\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u001a\u0010»\u0001\u001a\u00020-2\u0007\u0010¼\u0001\u001a\u00020\t2\u0006\u0010E\u001a\u00020\u000bH\u0016J\u0011\u0010½\u0001\u001a\u00020-2\u0006\u0010B\u001a\u00020\u0007H\u0016J\u001a\u0010¾\u0001\u001a\u00020-2\u000f\u0010¿\u0001\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0014H\u0016J\u001a\u0010À\u0001\u001a\u00020-2\u000f\u0010Á\u0001\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0014H\u0016J\t\u0010Â\u0001\u001a\u00020-H\u0002J\u0013\u0010Ã\u0001\u001a\u00020-2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010Ä\u0001\u001a\u00020-2\u0007\u0010Å\u0001\u001a\u00020\u0007H\u0016J\t\u0010Æ\u0001\u001a\u00020-H\u0016J\u0014\u0010Ç\u0001\u001a\u00020-2\t\u0010È\u0001\u001a\u0004\u0018\u00010\tH\u0016J\u0011\u0010É\u0001\u001a\u00020-2\u0006\u0010B\u001a\u00020\u0007H\u0016J\u0012\u0010Ê\u0001\u001a\u00020-2\u0007\u0010Å\u0001\u001a\u00020\u0007H\u0016J\u0011\u0010Ë\u0001\u001a\u00020-2\u0006\u0010B\u001a\u00020\u0007H\u0016J\u0011\u0010Ì\u0001\u001a\u00020-2\u0006\u0010B\u001a\u00020\u0007H\u0016J\u0011\u0010Í\u0001\u001a\u00020-2\u0006\u0010B\u001a\u00020\u0007H\u0016J\u0011\u0010Î\u0001\u001a\u00020-2\u0006\u0010B\u001a\u00020\u0007H\u0016J\u0012\u0010Ï\u0001\u001a\u00020-2\u0007\u0010Ð\u0001\u001a\u00020\u0007H\u0016J\u0012\u0010Ñ\u0001\u001a\u00020-2\u0007\u0010Å\u0001\u001a\u00020\u0007H\u0016J\u0012\u0010Ò\u0001\u001a\u00020-2\u0007\u0010Å\u0001\u001a\u00020\u0007H\u0016J\u0012\u0010Ó\u0001\u001a\u00020-2\u0007\u0010Å\u0001\u001a\u00020\u0007H\u0016J\u0012\u0010Ô\u0001\u001a\u00020-2\u0007\u0010Õ\u0001\u001a\u00020\u0007H\u0016J\u0015\u0010Ö\u0001\u001a\u00020-2\n\u0010×\u0001\u001a\u0005\u0018\u00010Ø\u0001H\u0016J\u0012\u0010Ù\u0001\u001a\u00020-2\u0007\u0010Ú\u0001\u001a\u00020\u0007H\u0016J\u0015\u0010Û\u0001\u001a\u00020-2\n\u0010×\u0001\u001a\u0005\u0018\u00010Ø\u0001H\u0016J\u0012\u0010Ü\u0001\u001a\u00020-2\u0007\u0010Å\u0001\u001a\u00020\u0007H\u0016J\t\u0010Ý\u0001\u001a\u00020-H\u0016J\t\u0010Þ\u0001\u001a\u00020-H\u0002J\t\u0010ß\u0001\u001a\u00020-H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u001c\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u001dX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010#\u001a\u0004\u0018\u00010$X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001c\u0010)\u001a\u0004\u0018\u00010$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010&\"\u0004\b+\u0010(¨\u0006á\u0001"}, d2 = {"Lcom/ido/life/module/sport/history/detail/SportHistoryDetailNewActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/sport/history/detail/SportHistoryDetailNewPresenter;", "Lcom/ido/life/module/sport/history/detail/ISportHistoryDetailNewView;", "Landroid/view/View$OnClickListener;", "()V", "isHideMap", "", "mDateTime", "", "mDistance", "", "mDuration", "mFromType", "mHandler", "Landroid/os/Handler;", "mIsGoogleLoad", "mIsLocus", "mIsScreenMap", "mLatLngBeanList", "", "Lcom/ido/life/bean/LatLngBean;", "mSportType", "mType", "mUserId", "", "mapDrawableRunnable", "Ljava/lang/Runnable;", "mapModel", "Lcom/ido/life/module/sport/map/BaseMap;", "getMapModel", "()Lcom/ido/life/module/sport/map/BaseMap;", "setMapModel", "(Lcom/ido/life/module/sport/map/BaseMap;)V", "mapScreenRunable", "mapView", "Landroid/view/View;", "getMapView", "()Landroid/view/View;", "setMapView", "(Landroid/view/View;)V", "mapView_view", "getMapView_view", "setMapView_view", "addPolylineAndMove", "", "latLngBeanList", "type", "durations", "distance", "getLayoutResId", "getShotFilePath", "hideLoading", "initData", "initEvent", "initMap", "savedInstanceState", "Landroid/os/Bundle;", "initMapData", "initViews", "onClick", "v", "onCreate", "onDestroy", "saveAndUploadSportSmallPic", "setBasicDataViewVisible", "visible", "setCalorie", "calorie", "drawableId", "setCalorieVisible", "setEightItemDesc", "eightDesc", "setEightItemUnit", "eightUnit", "setEightItemValue", "eightValue", "setFirstItemDesc", "firstItemDesc", "setFirstItemUnit", "firstItemUnit", "setFirstItemValue", "firstItemValue", "setFiveItemDesc", "fiveItemDesc", "setFiveItemUnit", "fiveItemUnit", "setFiveItemValue", "fiveItemValue", "setFourItemDesc", "fourItemDesc", "setFourItemUnit", "fourItemUnit", "setFourItemValue", "fourItemValue", "setKmSpace", "kmProgress", "", "Lcom/ido/life/module/sport/history/KmProgress;", "unit", "setKmSpeed", "setLoadLoadTitleShow", "loadLoadTitleShow", "setLoadTitleText", "titleText", "setNoDistanceRemind", "noDistanceRemind", "setNoDistanceRemindVisible", "noDistanceRemindVisible", "setOutDoorLayoutVisible", "setOutLocusShareLayout", "setRate", "rate", "setRateVisible", "setSecondItemDesc", "secondItemDesc", "setSecondItemUnit", "secondItemUnit", "setSecondItemValue", "secondItemValue", "setSeekBarProcess", "process", "max", "setSevenItemDesc", "sevenDesc", "setSevenItemUnit", "sevenUnit", "setSevenItemValue", "sevenValue", "setSixItemDesc", "sixItemDesc", "setSixItemUnit", "sixItemUnit", "setSixItemValue", "sixItemValue", "setSixItemVisible", "setSportCycleNoDistance", "tips", "setSportDistance", "sportDistance", "sportDistanceUnit", "setSportItemDistanceLabel", "sportItemDistanceLabel", "setSportItemPaceAverage", "sportPaceAverage", "setSportItemPaceAverageDesc", "sportItemPaceAverageDesc", "setSportItemPaceFaster", "sportItemPaceFaster", "setSportItemPaceTitleUnit", "sportItemPaceTitleUnit", "setSportItemSpeedAverage", "sportSpeedAverage", "setSportItemSpeedTitleUnit", "sportItemSpeedTitleUnit", "setSportName", "sportName", "setSportSpeedItemKm", "sportSpeedItemKm", "setSportStartTime", "startTime", "setSportTarget", "target", "setSportType", "setStepFrequencyAvg", "stepFrequencyAvg", "setStepFrequencyList", "baseCharBeans", "Lcom/ido/life/bean/BaseCharBean;", "setStepFrequencyMax", "stepFrequencyMax", "setStepXMaxValue", "xMaxValue", "setStepXMinValue", "rateXMinValue", "setStepYMaxValue", "rateYMaxValue", "setStepYMinValue", "rateYMinValue", "setTargetDiff", "targetDiff", "setThreeItemDesc", "threeItemDesc", "setThreeItemUnit", "threeItemUnit", "setThreeItemValue", "threeItemValue", "setTotalSecond", "time", "setTotalSecondVisible", "setXStepLabelList", "xLabelList", "setYStepLabelList", "yLabelList", "shareIndoor", "showConfirmDelete", "showFourItem", "isVisible", "showLoading", "showMessage", "message", "showRightBtn", "showSeekBarStepNum", "showSportDataItemFour", "showSportDataItemOneRight", "showSportDataItemThree", "showSportDataItemTwo", "showSportDataView", "sportDataViw", "showSportItemFrequency", "showSportItemPace", "showSportItemSpeed", "showSportNoNet", "isNet", "showSportRate", "sportHealth", "Lcom/ido/life/database/model/SportHealth;", "showSportRetryView", "retryView", "showSportTrain", "showUserTargetDiff", "toBack", "toShare", "updateData", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SportHistoryDetailNewActivity extends BaseActivity<SportHistoryDetailNewPresenter> implements ISportHistoryDetailNewView, View.OnClickListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String EXTRA_DATE = "extra_date";
    public static final String EXTRA_FROM_TYPE = "from_type";
    public static final String EXTRA_HIDE_MAP = "hide_map";
    public static final String EXTRA_SPORT_TYPE = "sport_type";
    public static final int REQUEST_CODE = 1001;
    public static final int RESULT_CODE = 1002;
    public static final String TAG = "SportHistoryDetailNewActivity";
    private HashMap _$_findViewCache;
    private boolean isHideMap;
    private String mDateTime;
    private int mDistance;
    private int mDuration;
    private int mFromType;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mIsGoogleLoad;
    private boolean mIsLocus;
    private boolean mIsScreenMap;
    private List<? extends LatLngBean> mLatLngBeanList;
    private int mSportType;
    private int mType;
    private final long mUserId;
    private final Runnable mapDrawableRunnable;
    private BaseMap<?, ?> mapModel;
    private final Runnable mapScreenRunable;
    private View mapView;
    private View mapView_view;

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
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_sport_history_detail_new;
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setNoDistanceRemind(String noDistanceRemind) {
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setNoDistanceRemindVisible(boolean noDistanceRemindVisible) {
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSportItemSpeedAverage(String sportSpeedAverage) {
    }

    public SportHistoryDetailNewActivity() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        this.mUserId = runTimeUtil.getUserId();
        this.mapDrawableRunnable = new SportHistoryDetailNewActivity$mapDrawableRunnable$1(this);
        this.mapScreenRunable = new Runnable() { // from class: com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity$mapScreenRunable$1
            @Override // java.lang.Runnable
            public final void run() {
                BaseMap<?, ?> mapModel = this.this$0.getMapModel();
                if (mapModel == null) {
                    Intrinsics.throwNpe();
                }
                mapModel.onMapScreenShot(new MapScreenShotCallback() { // from class: com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity$mapScreenRunable$1.1
                    @Override // com.ido.life.module.sport.map.MapScreenShotCallback
                    public final void shotComplet(Bitmap bitmap) {
                        SportLogHelper.saveSportLog(SportHistoryDetailNewActivity.TAG, "shotComplet: 截图完成");
                        if (SportHistoryDetailNewActivity$mapScreenRunable$1.this.this$0.getMapModel() == null || SportHistoryDetailNewActivity$mapScreenRunable$1.this.this$0.mLatLngBeanList == null) {
                            return;
                        }
                        List list = SportHistoryDetailNewActivity$mapScreenRunable$1.this.this$0.mLatLngBeanList;
                        if (list == null) {
                            Intrinsics.throwNpe();
                        }
                        if (list.size() < 1) {
                            return;
                        }
                        BaseMap<?, ?> mapModel2 = SportHistoryDetailNewActivity$mapScreenRunable$1.this.this$0.getMapModel();
                        if (mapModel2 == null) {
                            Intrinsics.throwNpe();
                        }
                        List list2 = SportHistoryDetailNewActivity$mapScreenRunable$1.this.this$0.mLatLngBeanList;
                        if (list2 == null) {
                            Intrinsics.throwNpe();
                        }
                        mapModel2.addDynamicStartMark((LatLngBean) list2.get(0), R.mipmap.ic_sport_map_detail_start);
                        BaseMap<?, ?> mapModel3 = SportHistoryDetailNewActivity$mapScreenRunable$1.this.this$0.getMapModel();
                        if (mapModel3 == null) {
                            Intrinsics.throwNpe();
                        }
                        List list3 = SportHistoryDetailNewActivity$mapScreenRunable$1.this.this$0.mLatLngBeanList;
                        if (list3 == null) {
                            Intrinsics.throwNpe();
                        }
                        List list4 = SportHistoryDetailNewActivity$mapScreenRunable$1.this.this$0.mLatLngBeanList;
                        if (list4 == null) {
                            Intrinsics.throwNpe();
                        }
                        mapModel3.addDynamicEndMark((LatLngBean) list3.get(list4.size() - 1), R.mipmap.ic_sport_map_detail_end);
                        SportHistoryDetailNewActivity.access$getMPresenter$p(SportHistoryDetailNewActivity$mapScreenRunable$1.this.this$0).saveSportData(SportHistoryDetailNewActivity$mapScreenRunable$1.this.this$0.mDateTime, SportHistoryDetailNewActivity$mapScreenRunable$1.this.this$0.getShotFilePath(), SportHistoryDetailNewActivity$mapScreenRunable$1.this.this$0.mUserId);
                        RelativeLayout rv_map_loading = (RelativeLayout) SportHistoryDetailNewActivity$mapScreenRunable$1.this.this$0._$_findCachedViewById(com.ido.life.R.id.rv_map_loading);
                        Intrinsics.checkExpressionValueIsNotNull(rv_map_loading, "rv_map_loading");
                        rv_map_loading.setVisibility(8);
                    }
                });
            }
        };
    }

    public static final /* synthetic */ SportHistoryDetailNewPresenter access$getMPresenter$p(SportHistoryDetailNewActivity sportHistoryDetailNewActivity) {
        return (SportHistoryDetailNewPresenter) sportHistoryDetailNewActivity.mPresenter;
    }

    protected final View getMapView() {
        return this.mapView;
    }

    protected final void setMapView(View view) {
        this.mapView = view;
    }

    protected final BaseMap<?, ?> getMapModel() {
        return this.mapModel;
    }

    protected final void setMapModel(BaseMap<?, ?> baseMap) {
        this.mapModel = baseMap;
    }

    public final View getMapView_view() {
        return this.mapView_view;
    }

    public final void setMapView_view(View view) {
        this.mapView_view = view;
    }

    /* JADX INFO: compiled from: SportHistoryDetailNewActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\tJ(\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/ido/life/module/sport/history/detail/SportHistoryDetailNewActivity$Companion;", "", "()V", "EXTRA_DATE", "", "EXTRA_FROM_TYPE", "EXTRA_HIDE_MAP", "EXTRA_SPORT_TYPE", "REQUEST_CODE", "", "RESULT_CODE", "TAG", "startActivityResult", "", "context", "Landroid/app/Activity;", "sportType", "date", "fromType", "startActivityResultHideMap", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void startActivityResult(Activity context, int sportType, String date, int fromType) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intent intent = new Intent(context, (Class<?>) SportHistoryDetailNewActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(SportHistoryDetailNewActivity.EXTRA_DATE, date);
            bundle.putInt(SportHistoryDetailNewActivity.EXTRA_SPORT_TYPE, sportType);
            bundle.putInt(SportHistoryDetailNewActivity.EXTRA_FROM_TYPE, fromType);
            intent.putExtras(bundle);
            context.startActivityForResult(intent, 1001);
        }

        public final void startActivityResultHideMap(Activity context, int sportType, String date, int fromType) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intent intent = new Intent(context, (Class<?>) SportHistoryDetailNewActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(SportHistoryDetailNewActivity.EXTRA_DATE, date);
            bundle.putInt(SportHistoryDetailNewActivity.EXTRA_SPORT_TYPE, sportType);
            bundle.putInt(SportHistoryDetailNewActivity.EXTRA_FROM_TYPE, fromType);
            bundle.putBoolean(SportHistoryDetailNewActivity.EXTRA_HIDE_MAP, true);
            intent.putExtras(bundle);
            context.startActivityForResult(intent, 1001);
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMap(savedInstanceState);
        initMapData();
        updateData();
    }

    protected final void initMapData() {
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.mDateTime = extras.getString(EXTRA_DATE);
            this.mFromType = extras.getInt(EXTRA_FROM_TYPE);
            this.mSportType = extras.getInt(EXTRA_SPORT_TYPE);
            this.isHideMap = extras.getBoolean(EXTRA_HIDE_MAP);
        }
    }

    private final void updateData() {
        ((SportHistoryDetailNewPresenter) this.mPresenter).getSportNameByType(this.mSportType);
        if (!TextUtils.isEmpty(this.mDateTime)) {
            ((SportHistoryDetailNewPresenter) this.mPresenter).getSportDataByDateTime(this.mDateTime, this.mFromType, this.mUserId);
        }
        ((ScrollView) _$_findCachedViewById(com.ido.life.R.id.scrollView_map)).smoothScrollTo(0, 0);
        BaseMap<?, ?> baseMap = this.mapModel;
        if (baseMap == null) {
            Intrinsics.throwNpe();
        }
        baseMap.setIsRunMap(false);
        BaseMap<?, ?> baseMap2 = this.mapModel;
        if (baseMap2 == null) {
            Intrinsics.throwNpe();
        }
        baseMap2.setAddCircle(false);
        ImageView iv_sport_about = (ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_sport_about);
        Intrinsics.checkExpressionValueIsNotNull(iv_sport_about, "iv_sport_about");
        iv_sport_about.setVisibility(((SportHistoryDetailNewPresenter) this.mPresenter).isShowSportNameExplain(this.mSportType) ? 0 : 8);
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity$onDestroy$1] */
    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.mPresenter != 0) {
            ((SportHistoryDetailNewPresenter) this.mPresenter).destroy();
        }
        this.mPresenter = (P) 0;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mapScreenRunable);
            this.mHandler.removeCallbacks(this.mapDrawableRunnable);
        }
        BaseMap<?, ?> baseMap = this.mapModel;
        if (baseMap != null) {
            if (baseMap == null) {
                Intrinsics.throwNpe();
            }
            baseMap.setMapLoadFinish(true);
            new Thread() { // from class: com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity.onDestroy.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    super.run();
                    if (SportHistoryDetailNewActivity.this.getMapModel() == null) {
                        return;
                    }
                    try {
                        try {
                            BaseMap<?, ?> mapModel = SportHistoryDetailNewActivity.this.getMapModel();
                            if (mapModel == null) {
                                Intrinsics.throwNpe();
                            }
                            mapModel.onDestroy();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } finally {
                        SportHistoryDetailNewActivity.this.setMapModel((BaseMap) null);
                    }
                }
            }.start();
        }
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        ImageView iv_sport_about = (ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_sport_about);
        Intrinsics.checkExpressionValueIsNotNull(iv_sport_about, "iv_sport_about");
        iv_sport_about.setVisibility(8);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        SportHistoryDetailNewActivity sportHistoryDetailNewActivity = this;
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_out_back)).setOnClickListener(sportHistoryDetailNewActivity);
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_out_share)).setOnClickListener(sportHistoryDetailNewActivity);
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_out_delete)).setOnClickListener(sportHistoryDetailNewActivity);
        ((ImageButton) _$_findCachedViewById(com.ido.life.R.id.title_rightBtn)).setOnClickListener(sportHistoryDetailNewActivity);
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_share)).setOnClickListener(sportHistoryDetailNewActivity);
        ((ImageButton) _$_findCachedViewById(com.ido.life.R.id.title_leftBtn)).setOnClickListener(sportHistoryDetailNewActivity);
        ((MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_retry)).setOnClickListener(sportHistoryDetailNewActivity);
        ((RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_data_feedback)).setOnClickListener(sportHistoryDetailNewActivity);
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_sport_about)).setOnClickListener(sportHistoryDetailNewActivity);
    }

    private final void initMap(Bundle savedInstanceState) {
        SportHistoryDetailNewActivity sportHistoryDetailNewActivity = this;
        this.mapView = MapFactory.getMapView(sportHistoryDetailNewActivity);
        this.mapModel = MapFactory.getMap();
        BaseMap<?, ?> baseMap = this.mapModel;
        if (baseMap != null) {
            baseMap.setActivity(sportHistoryDetailNewActivity);
        }
        BaseMap<?, ?> baseMap2 = this.mapModel;
        if (baseMap2 != null) {
            baseMap2.initMapView(this.mapView);
        }
        BaseMap<?, ?> baseMap3 = this.mapModel;
        if (baseMap3 != null) {
            baseMap3.onCreate(savedInstanceState);
        }
        this.mapView_view = findViewById(R.id.rl_map_root);
        ViewGroup.LayoutParams layoutParams = ((FrameLayout) _$_findCachedViewById(com.ido.life.R.id.rl_map_root)).getLayoutParams();
        Intrinsics.checkExpressionValueIsNotNull(layoutParams, "rl_map_root.getLayoutParams()");
        layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.sw_dp_350);
        View view = this.mapView_view;
        if (view != null) {
            view.setLayoutParams(layoutParams);
        }
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void toBack() {
        setResult(1002, new Intent());
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showLoading() {
        WaitingDialog.showDialog(this);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showRightBtn(boolean visible) {
        ImageButton title_rightBtn = (ImageButton) _$_findCachedViewById(com.ido.life.R.id.title_rightBtn);
        Intrinsics.checkExpressionValueIsNotNull(title_rightBtn, "title_rightBtn");
        title_rightBtn.setVisibility(0);
        if (visible) {
            ImageView iv_share = (ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_share);
            Intrinsics.checkExpressionValueIsNotNull(iv_share, "iv_share");
            iv_share.setVisibility(0);
            if (this.isHideMap) {
                ((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_share)).setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_share_nomap));
                return;
            } else {
                ((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_share)).setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_share));
                return;
            }
        }
        ImageView iv_share2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_share);
        Intrinsics.checkExpressionValueIsNotNull(iv_share2, "iv_share");
        iv_share2.setVisibility(8);
        if (this.isHideMap) {
            ((ImageButton) _$_findCachedViewById(com.ido.life.R.id.title_rightBtn)).setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_share_nomap));
        } else {
            ((ImageButton) _$_findCachedViewById(com.ido.life.R.id.title_rightBtn)).setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_share));
        }
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showSportRetryView(boolean retryView) {
        LinearLayout layout_sport_retry = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_sport_retry);
        Intrinsics.checkExpressionValueIsNotNull(layout_sport_retry, "layout_sport_retry");
        layout_sport_retry.setVisibility(retryView ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showSportDataView(boolean sportDataViw) {
        LinearLayout ll_sport_content = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_sport_content);
        Intrinsics.checkExpressionValueIsNotNull(ll_sport_content, "ll_sport_content");
        ll_sport_content.setVisibility(sportDataViw ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showSportNoNet(boolean isNet) {
        LinearLayout layout_network_error = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.layout_network_error);
        Intrinsics.checkExpressionValueIsNotNull(layout_network_error, "layout_network_error");
        layout_network_error.setVisibility(isNet ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setLoadLoadTitleShow(boolean loadLoadTitleShow) {
        TextView title_text = (TextView) _$_findCachedViewById(com.ido.life.R.id.title_text);
        Intrinsics.checkExpressionValueIsNotNull(title_text, "title_text");
        title_text.setVisibility(loadLoadTitleShow ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showMessage(String message) {
        NormalToast.showToast(message);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSportName(String sportName) {
        RegularTextView tv_sport_indoor_name = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_sport_indoor_name);
        Intrinsics.checkExpressionValueIsNotNull(tv_sport_indoor_name, "tv_sport_indoor_name");
        tv_sport_indoor_name.setText(sportName);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setLoadTitleText(String titleText) {
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.title_text);
        if (textView == null) {
            Intrinsics.throwNpe();
        }
        textView.setText(titleText);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSportTarget(String target) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        RegularTextView tv_target = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_target);
        Intrinsics.checkExpressionValueIsNotNull(tv_target, "tv_target");
        tv_target.setText(target);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSportStartTime(String startTime) {
        RegularTextView tv_start_time = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_start_time);
        Intrinsics.checkExpressionValueIsNotNull(tv_start_time, "tv_start_time");
        tv_start_time.setText(startTime);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSportDistance(String sportDistance, String sportDistanceUnit) {
        ((UnitView) _$_findCachedViewById(com.ido.life.R.id.uv_distance)).setDataText(sportDistance);
        ((UnitView) _$_findCachedViewById(com.ido.life.R.id.uv_distance)).setUnitText(sportDistanceUnit);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSportCycleNoDistance(boolean visible, String tips) {
        int i;
        Intrinsics.checkParameterIsNotNull(tips, "tips");
        RegularTextView tv_no_distance_tip = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_no_distance_tip);
        Intrinsics.checkExpressionValueIsNotNull(tv_no_distance_tip, "tv_no_distance_tip");
        if (visible) {
            RegularTextView tv_no_distance_tip2 = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_no_distance_tip);
            Intrinsics.checkExpressionValueIsNotNull(tv_no_distance_tip2, "tv_no_distance_tip");
            tv_no_distance_tip2.setText(tips);
            i = 0;
        } else {
            i = 8;
        }
        tv_no_distance_tip.setVisibility(i);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setCalorie(String calorie, int drawableId) {
        Intrinsics.checkParameterIsNotNull(calorie, "calorie");
        TextView tv_calorie = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_calorie);
        Intrinsics.checkExpressionValueIsNotNull(tv_calorie, "tv_calorie");
        tv_calorie.setText(calorie);
        Drawable drawable = ResourceUtil.getDrawable(drawableId);
        Intrinsics.checkExpressionValueIsNotNull(drawable, "drawable");
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_calorie)).setCompoundDrawables(drawable, null, null, null);
        setCalorieVisible(true);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setCalorieVisible(boolean visible) {
        TextView tv_calorie = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_calorie);
        Intrinsics.checkExpressionValueIsNotNull(tv_calorie, "tv_calorie");
        tv_calorie.setVisibility(visible ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setRate(String rate, int drawableId) {
        Intrinsics.checkParameterIsNotNull(rate, "rate");
        TextView tv_rate = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_rate);
        Intrinsics.checkExpressionValueIsNotNull(tv_rate, "tv_rate");
        tv_rate.setText(rate);
        Drawable drawable = ResourceUtil.getDrawable(drawableId);
        Intrinsics.checkExpressionValueIsNotNull(drawable, "drawable");
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_rate)).setCompoundDrawables(drawable, null, null, null);
        setRateVisible(true);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setRateVisible(boolean visible) {
        TextView tv_rate = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_rate);
        Intrinsics.checkExpressionValueIsNotNull(tv_rate, "tv_rate");
        tv_rate.setVisibility(visible ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setTotalSecond(String time, int drawableId) {
        Intrinsics.checkParameterIsNotNull(time, "time");
        TextView tv_total_second = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_total_second);
        Intrinsics.checkExpressionValueIsNotNull(tv_total_second, "tv_total_second");
        tv_total_second.setText(time);
        Drawable drawable = ResourceUtil.getDrawable(drawableId);
        Intrinsics.checkExpressionValueIsNotNull(drawable, "drawable");
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_total_second)).setCompoundDrawables(drawable, null, null, null);
        setTotalSecondVisible(true);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setTotalSecondVisible(boolean visible) {
        TextView tv_total_second = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_total_second);
        Intrinsics.checkExpressionValueIsNotNull(tv_total_second, "tv_total_second");
        tv_total_second.setVisibility(visible ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setBasicDataViewVisible(boolean visible) {
        View ll_base_data = _$_findCachedViewById(com.ido.life.R.id.ll_base_data);
        Intrinsics.checkExpressionValueIsNotNull(ll_base_data, "ll_base_data");
        ll_base_data.setVisibility(visible ? 0 : 8);
        View headerLineView = _$_findCachedViewById(com.ido.life.R.id.headerLineView);
        Intrinsics.checkExpressionValueIsNotNull(headerLineView, "headerLineView");
        headerLineView.setVisibility(visible ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showSportDataItemOneRight(boolean visible) {
        BottomSportView bv_sport_item_two = (BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_two);
        Intrinsics.checkExpressionValueIsNotNull(bv_sport_item_two, "bv_sport_item_two");
        bv_sport_item_two.setVisibility(visible ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showSportDataItemTwo(boolean visible) {
        LinearLayout ll_sport_detail_two_item = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_sport_detail_two_item);
        Intrinsics.checkExpressionValueIsNotNull(ll_sport_detail_two_item, "ll_sport_detail_two_item");
        ll_sport_detail_two_item.setVisibility(visible ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showSportDataItemThree(boolean visible) {
        LinearLayout ll_sport_detail_three_item = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_sport_detail_three_item);
        Intrinsics.checkExpressionValueIsNotNull(ll_sport_detail_three_item, "ll_sport_detail_three_item");
        ll_sport_detail_three_item.setVisibility(visible ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showSportDataItemFour(boolean visible) {
        LinearLayout ll_sport_detail_four_item = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_sport_detail_four_item);
        Intrinsics.checkExpressionValueIsNotNull(ll_sport_detail_four_item, "ll_sport_detail_four_item");
        ll_sport_detail_four_item.setVisibility(visible ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setFirstItemValue(String firstItemValue) {
        if (firstItemValue == null) {
            Intrinsics.throwNpe();
        }
        if (StringsKt.contains$default((CharSequence) firstItemValue, (CharSequence) "99'59''", false, 2, (Object) null)) {
            ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_one)).setDataText("00'00''");
        } else {
            ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_one)).setDataText(firstItemValue);
        }
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setFirstItemDesc(String firstItemDesc) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_one)).setDescribeText(firstItemDesc);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setFirstItemUnit(String firstItemUnit) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_one)).setUnitText(firstItemUnit);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSecondItemValue(String secondItemValue) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_two)).setDataText(secondItemValue);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSecondItemDesc(String secondItemDesc) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_two)).setDescribeText(secondItemDesc);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSecondItemUnit(String secondItemUnit) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_two)).setUnitText(secondItemUnit);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setThreeItemValue(String threeItemValue) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_three)).setDataText(threeItemValue);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setThreeItemDesc(String threeItemDesc) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_three)).setDescribeText(threeItemDesc);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setThreeItemUnit(String threeItemUnit) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_three)).setUnitText(threeItemUnit);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setFourItemValue(String fourItemValue) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_four)).setDataText(fourItemValue);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setFourItemDesc(String fourItemDesc) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_four)).setDescribeText(fourItemDesc);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setFourItemUnit(String fourItemUnit) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_four)).setUnitText(fourItemUnit);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showFourItem(boolean isVisible) {
        BottomSportView bv_sport_item_four = (BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_four);
        Intrinsics.checkExpressionValueIsNotNull(bv_sport_item_four, "bv_sport_item_four");
        bv_sport_item_four.setVisibility(isVisible ? 0 : 4);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setFiveItemValue(String fiveItemValue) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_five)).setDataText(fiveItemValue);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setFiveItemDesc(String fiveItemDesc) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_five)).setDescribeText(fiveItemDesc);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setFiveItemUnit(String fiveItemUnit) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_five)).setUnitText(fiveItemUnit);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSixItemValue(String sixItemValue) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_six)).setDataText(sixItemValue);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSixItemVisible(boolean visible) {
        BottomSportView bv_sport_item_six = (BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_six);
        Intrinsics.checkExpressionValueIsNotNull(bv_sport_item_six, "bv_sport_item_six");
        bv_sport_item_six.setVisibility(visible ? 0 : 4);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSixItemDesc(String sixItemDesc) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_six)).setDescribeText(sixItemDesc);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSixItemUnit(String sixItemUnit) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_six)).setUnitText(sixItemUnit);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSevenItemValue(String sevenValue) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_seven)).setDataText(sevenValue);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSevenItemDesc(String sevenDesc) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_seven)).setDescribeText(sevenDesc);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSevenItemUnit(String sevenUnit) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_seven)).setUnitText(sevenUnit);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setEightItemValue(String eightValue) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_eight)).setDataText(eightValue);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setEightItemDesc(String eightDesc) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_eight)).setDescribeText(eightDesc);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setEightItemUnit(String eightUnit) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_eight)).setUnitText(eightUnit);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showSportRate(SportHealth sportHealth) {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_rate, RateFragment.INSTANCE.newInstance(sportHealth)).commit();
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showSportTrain(SportHealth sportHealth) {
        TrainFragment.Companion companion = TrainFragment.INSTANCE;
        if (sportHealth == null) {
            Intrinsics.throwNpe();
        }
        getSupportFragmentManager().beginTransaction().add(R.id.fl_train, companion.newInstance(sportHealth)).commit();
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showSportItemPace(boolean isVisible) {
        LinearLayout ll_sport_detail_peace_item = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_sport_detail_peace_item);
        Intrinsics.checkExpressionValueIsNotNull(ll_sport_detail_peace_item, "ll_sport_detail_peace_item");
        ll_sport_detail_peace_item.setVisibility(isVisible ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showSportItemSpeed(boolean isVisible) {
        LinearLayout ll_sport_detail_speed_item = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_sport_detail_speed_item);
        Intrinsics.checkExpressionValueIsNotNull(ll_sport_detail_speed_item, "ll_sport_detail_speed_item");
        ll_sport_detail_speed_item.setVisibility(isVisible ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSportItemPaceAverageDesc(String sportItemPaceAverageDesc) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_pace_avg)).setDescribeText(sportItemPaceAverageDesc);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSportItemPaceAverage(String sportPaceAverage) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_pace_avg)).setDataText(sportPaceAverage);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSportItemPaceFaster(String sportItemPaceFaster) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_item_pace_faster)).setDataText(sportItemPaceFaster);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setKmSpace(List<KmProgress> kmProgress, String unit) {
        Intrinsics.checkParameterIsNotNull(kmProgress, "kmProgress");
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        ((SportKmSpace) _$_findCachedViewById(com.ido.life.R.id.sport_km_pace)).setSportData(kmProgress, unit, false);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setKmSpeed(List<KmProgress> kmProgress, String unit) {
        Intrinsics.checkParameterIsNotNull(kmProgress, "kmProgress");
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        ((SportKmSpace) _$_findCachedViewById(com.ido.life.R.id.sport_km_speed)).setSportData(kmProgress, unit, true);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSportItemPaceTitleUnit(String sportItemPaceTitleUnit) {
        RegularTextView tv_sport_pace_unit = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_sport_pace_unit);
        Intrinsics.checkExpressionValueIsNotNull(tv_sport_pace_unit, "tv_sport_pace_unit");
        tv_sport_pace_unit.setText(sportItemPaceTitleUnit);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSportItemDistanceLabel(String sportItemDistanceLabel) {
        RegularTextView tv_sport_item_distance = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_sport_item_distance);
        Intrinsics.checkExpressionValueIsNotNull(tv_sport_item_distance, "tv_sport_item_distance");
        tv_sport_item_distance.setText(sportItemDistanceLabel);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSportItemSpeedTitleUnit(String sportItemSpeedTitleUnit) {
        RegularTextView tv_sport_speed_unit = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_sport_speed_unit);
        Intrinsics.checkExpressionValueIsNotNull(tv_sport_speed_unit, "tv_sport_speed_unit");
        tv_sport_speed_unit.setText(sportItemSpeedTitleUnit);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSportSpeedItemKm(String sportSpeedItemKm) {
        RegularTextView tv_sport_item_speed_km = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_sport_item_speed_km);
        Intrinsics.checkExpressionValueIsNotNull(tv_sport_item_speed_km, "tv_sport_item_speed_km");
        tv_sport_item_speed_km.setText(sportSpeedItemKm);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showSeekBarStepNum(boolean isVisible) {
        CirclePercentView target_progress_circular = (CirclePercentView) _$_findCachedViewById(com.ido.life.R.id.target_progress_circular);
        Intrinsics.checkExpressionValueIsNotNull(target_progress_circular, "target_progress_circular");
        target_progress_circular.setVisibility(isVisible ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showUserTargetDiff(boolean isVisible) {
        RegularTextView tv_target = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_target);
        Intrinsics.checkExpressionValueIsNotNull(tv_target, "tv_target");
        tv_target.setVisibility(isVisible ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSeekBarProcess(int process, int max) {
        ((CirclePercentView) _$_findCachedViewById(com.ido.life.R.id.target_progress_circular)).setPercentage((int) (Math.min(1.0f, process / max) * 100));
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setTargetDiff(String targetDiff) {
        RegularTextView tv_target = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_target);
        Intrinsics.checkExpressionValueIsNotNull(tv_target, "tv_target");
        tv_target.setText(targetDiff);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void showSportItemFrequency(boolean isVisible) {
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_sport_detail_frequency_item)).setVisibility(isVisible ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setStepFrequencyAvg(String stepFrequencyAvg) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_frequency_avg)).setDataText(stepFrequencyAvg);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setStepFrequencyMax(String stepFrequencyMax) {
        ((BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_sport_frequency_max)).setDataText(stepFrequencyMax);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setYStepLabelList(List<String> yLabelList) {
        CubicSportStepChartBar cubicSportStepChartBar = (CubicSportStepChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart_step);
        if (cubicSportStepChartBar != null) {
            cubicSportStepChartBar.setLabelYLeftList(yLabelList);
        }
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setXStepLabelList(List<String> xLabelList) {
        CubicSportStepChartBar cubicSportStepChartBar = (CubicSportStepChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart_step);
        if (cubicSportStepChartBar != null) {
            cubicSportStepChartBar.setLabelXList(xLabelList);
        }
        CubicSportStepChartBar cubicSportStepChartBar2 = (CubicSportStepChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart_step);
        if (cubicSportStepChartBar2 != null) {
            cubicSportStepChartBar2.setBottomTitle(getString(R.string.min_unit_short));
        }
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setStepFrequencyList(List<? extends BaseCharBean> baseCharBeans) {
        CubicSportStepChartBar cubicSportStepChartBar = (CubicSportStepChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart_step);
        if (cubicSportStepChartBar != null) {
            if (baseCharBeans == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<kotlin.Nothing>");
            }
            cubicSportStepChartBar.setList(baseCharBeans);
        }
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setStepXMinValue(int rateXMinValue) {
        CubicSportStepChartBar cubicSportStepChartBar = (CubicSportStepChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart_step);
        if (cubicSportStepChartBar != null) {
            cubicSportStepChartBar.setXMinValue(rateXMinValue);
        }
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setStepXMaxValue(int xMaxValue) {
        CubicSportStepChartBar cubicSportStepChartBar = (CubicSportStepChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart_step);
        if (cubicSportStepChartBar != null) {
            cubicSportStepChartBar.setXMaxValue(xMaxValue);
        }
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setStepYMaxValue(int rateYMaxValue) {
        CubicSportStepChartBar cubicSportStepChartBar = (CubicSportStepChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart_step);
        if (cubicSportStepChartBar != null) {
            cubicSportStepChartBar.setYMaxValue(rateYMaxValue);
        }
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setStepYMinValue(int rateYMinValue) {
        CubicSportStepChartBar cubicSportStepChartBar = (CubicSportStepChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart_step);
        if (cubicSportStepChartBar != null) {
            cubicSportStepChartBar.setYMinValue(rateYMinValue);
        }
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void addPolylineAndMove(List<? extends LatLngBean> latLngBeanList, int type, int durations, int distance) {
        if (this.mPresenter == 0 || ((TrackPointView) _$_findCachedViewById(com.ido.life.R.id.track_point_view)) == null || this.mapModel == null || this.mHandler == null) {
            return;
        }
        this.mType = type;
        this.mDuration = durations;
        this.mDistance = distance;
        ((TrackPointView) _$_findCachedViewById(com.ido.life.R.id.track_point_view)).setVisibility(0);
        BaseMap<?, ?> baseMap = this.mapModel;
        if (baseMap == null) {
            Intrinsics.throwNpe();
        }
        baseMap.setMapLoadFinish(false);
        BaseMap<?, ?> baseMap2 = this.mapModel;
        if (baseMap2 == null) {
            Intrinsics.throwNpe();
        }
        baseMap2.setLatLngBeanList(latLngBeanList);
        this.mLatLngBeanList = latLngBeanList;
        BaseMap<?, ?> baseMap3 = this.mapModel;
        if (baseMap3 == null) {
            Intrinsics.throwNpe();
        }
        baseMap3.ajustMapView();
        this.mHandler.removeCallbacks(this.mapDrawableRunnable);
        this.mHandler.postDelayed(this.mapDrawableRunnable, 1000L);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setOutDoorLayoutVisible(boolean visible) {
        View ll_base_data = _$_findCachedViewById(com.ido.life.R.id.ll_base_data);
        Intrinsics.checkExpressionValueIsNotNull(ll_base_data, "ll_base_data");
        ll_base_data.setVisibility(0);
        LinearLayout ll_sport_detail_indoor = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_sport_detail_indoor);
        Intrinsics.checkExpressionValueIsNotNull(ll_sport_detail_indoor, "ll_sport_detail_indoor");
        ll_sport_detail_indoor.setVisibility(0);
        this.mIsLocus = visible;
        if (visible && !this.isHideMap) {
            FrameLayout rl_map_root = (FrameLayout) _$_findCachedViewById(com.ido.life.R.id.rl_map_root);
            Intrinsics.checkExpressionValueIsNotNull(rl_map_root, "rl_map_root");
            rl_map_root.setVisibility(0);
            RelativeLayout rv_out_sport_title = (RelativeLayout) _$_findCachedViewById(com.ido.life.R.id.rv_out_sport_title);
            Intrinsics.checkExpressionValueIsNotNull(rv_out_sport_title, "rv_out_sport_title");
            rv_out_sport_title.setVisibility(0);
            FrameLayout title_bar = (FrameLayout) _$_findCachedViewById(com.ido.life.R.id.title_bar);
            Intrinsics.checkExpressionValueIsNotNull(title_bar, "title_bar");
            title_bar.setVisibility(8);
            LinearLayout ll_bottom_out = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_bottom_out);
            Intrinsics.checkExpressionValueIsNotNull(ll_bottom_out, "ll_bottom_out");
            ViewGroup.LayoutParams layoutParams = ll_bottom_out.getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
            }
            ((RelativeLayout.LayoutParams) layoutParams).setMargins(0, getResources().getDimensionPixelOffset(R.dimen.sw_dp_350), 0, 0);
            return;
        }
        FrameLayout rl_map_root2 = (FrameLayout) _$_findCachedViewById(com.ido.life.R.id.rl_map_root);
        Intrinsics.checkExpressionValueIsNotNull(rl_map_root2, "rl_map_root");
        rl_map_root2.setVisibility(8);
        RelativeLayout rv_out_sport_title2 = (RelativeLayout) _$_findCachedViewById(com.ido.life.R.id.rv_out_sport_title);
        Intrinsics.checkExpressionValueIsNotNull(rv_out_sport_title2, "rv_out_sport_title");
        rv_out_sport_title2.setVisibility(8);
        FrameLayout title_bar2 = (FrameLayout) _$_findCachedViewById(com.ido.life.R.id.title_bar);
        Intrinsics.checkExpressionValueIsNotNull(title_bar2, "title_bar");
        title_bar2.setVisibility(0);
        ((ImageButton) _$_findCachedViewById(com.ido.life.R.id.title_leftBtn)).setBackgroundResource(R.drawable.shape_white);
        ((ImageButton) _$_findCachedViewById(com.ido.life.R.id.title_rightBtn)).setBackgroundResource(R.drawable.shape_white);
        LinearLayout ll_bottom_out2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_bottom_out);
        Intrinsics.checkExpressionValueIsNotNull(ll_bottom_out2, "ll_bottom_out");
        ViewGroup.LayoutParams layoutParams2 = ll_bottom_out2.getLayoutParams();
        if (layoutParams2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        ((RelativeLayout.LayoutParams) layoutParams2).setMargins(0, 0, 0, 0);
        LinearLayout topDetailLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.topDetailLayout);
        Intrinsics.checkExpressionValueIsNotNull(topDetailLayout, "topDetailLayout");
        topDetailLayout.setBackground(ResourceUtil.getDrawable(R.drawable.shape_white));
        LinearLayout topDetailLayout2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.topDetailLayout);
        Intrinsics.checkExpressionValueIsNotNull(topDetailLayout2, "topDetailLayout");
        ViewGroup.LayoutParams layoutParams3 = topDetailLayout2.getLayoutParams();
        if (layoutParams3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        }
        ((LinearLayout.LayoutParams) layoutParams3).setMargins(0, 0, 0, 0);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setOutLocusShareLayout(boolean visible) {
        if (visible) {
            ImageView iv_out_share = (ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_out_share);
            Intrinsics.checkExpressionValueIsNotNull(iv_out_share, "iv_out_share");
            iv_out_share.setVisibility(8);
            if (this.isHideMap) {
                ((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_out_delete)).setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_share_nomap));
                return;
            } else {
                ((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_out_delete)).setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_share));
                return;
            }
        }
        ImageView iv_out_share2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_out_share);
        Intrinsics.checkExpressionValueIsNotNull(iv_out_share2, "iv_out_share");
        iv_out_share2.setVisibility(8);
        ImageView iv_out_delete = (ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_out_delete);
        Intrinsics.checkExpressionValueIsNotNull(iv_out_delete, "iv_out_delete");
        iv_out_delete.setVisibility(0);
    }

    @Override // com.ido.life.module.sport.history.detail.ISportHistoryDetailNewView
    public void setSportType(int drawableId) {
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_sport)).setImageDrawable(ResourceUtil.getDrawable(drawableId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveAndUploadSportSmallPic(int type, int durations, int distance) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mapScreenRunable);
        }
        List<? extends LatLngBean> list = this.mLatLngBeanList;
        if (list != null) {
            if (list == null) {
                Intrinsics.throwNpe();
            }
            if (list.size() == 0 || ((TrackPointView) _$_findCachedViewById(com.ido.life.R.id.track_point_view)) == null) {
                return;
            }
            ((TrackPointView) _$_findCachedViewById(com.ido.life.R.id.track_point_view)).setVisibility(8);
            BaseMap<?, ?> baseMap = this.mapModel;
            if (baseMap == null) {
                Intrinsics.throwNpe();
            }
            baseMap.setStartTime(DateUtil.getLongFromDateStr(this.mDateTime));
            Handler handler2 = this.mHandler;
            if (handler2 != null) {
                handler2.postDelayed(this.mapScreenRunable, 2000L);
            }
        }
    }

    protected final String getShotFilePath() {
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        File file = new File(logPathImpl.getPicPath());
        if (!file.exists()) {
            file.mkdirs();
        }
        StringBuilder sb = new StringBuilder();
        LogPath logPathImpl2 = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
        sb.append(logPathImpl2.getPicPath());
        sb.append(DateUtil.getLongFromDateStr(this.mDateTime));
        sb.append(".png");
        return sb.toString();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.iv_out_back) {
            if (this.mPresenter != 0 && ((SportHistoryDetailNewPresenter) this.mPresenter).getMNeedRefreshSportRecordList()) {
                toBack();
                return;
            } else {
                ActivityCompat.finishAfterTransition(this);
                return;
            }
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.iv_out_share) {
            toShare();
            return;
        }
        if ((numValueOf != null && numValueOf.intValue() == R.id.iv_out_delete) || (numValueOf != null && numValueOf.intValue() == R.id.title_rightBtn)) {
            if (this.mFromType == 0) {
                if (this.mIsLocus) {
                    toShare();
                    return;
                } else {
                    shareIndoor();
                    return;
                }
            }
            SportDeletePopupWindow sportDeletePopupWindow = new SportDeletePopupWindow(this);
            int[] iArr = new int[2];
            ((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_out_delete)).getLocationOnScreen(iArr);
            sportDeletePopupWindow.showAtLocation((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_out_delete), BadgeDrawable.TOP_END, DipPixelUtil.dip2px(18.0f), iArr[1] + DipPixelUtil.dip2px(30.0f));
            sportDeletePopupWindow.setOnItemClickListener(new SportDeletePopupWindow.OnItemClickListener() { // from class: com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity.onClick.1
                @Override // com.ido.life.module.sport.view.SportDeletePopupWindow.OnItemClickListener
                public final void onDeleteClick(View view) {
                    SportHistoryDetailNewActivity sportHistoryDetailNewActivity = SportHistoryDetailNewActivity.this;
                    sportHistoryDetailNewActivity.showConfirmDelete(sportHistoryDetailNewActivity.mDateTime);
                }
            });
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.title_leftBtn) {
            ActivityCompat.finishAfterTransition(this);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.iv_share) {
            shareIndoor();
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_retry) {
            ((SportHistoryDetailNewPresenter) this.mPresenter).getSportDataByDateTime(this.mDateTime, this.mFromType, this.mUserId);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_data_feedback) {
            startActivity(new Intent(this, (Class<?>) FeedbackActivity.class));
        } else if (numValueOf != null && numValueOf.intValue() == R.id.iv_sport_about) {
            SportNameExplainDialogFragment.newInstance().show(getSupportFragmentManager());
        }
    }

    private final void shareIndoor() {
        WaitingDialog.showDialog(this);
        FrameLayout title_bar = (FrameLayout) _$_findCachedViewById(com.ido.life.R.id.title_bar);
        Intrinsics.checkExpressionValueIsNotNull(title_bar, "title_bar");
        title_bar.setVisibility(8);
        LinearLayout ll_sport_suggest = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_sport_suggest);
        Intrinsics.checkExpressionValueIsNotNull(ll_sport_suggest, "ll_sport_suggest");
        ll_sport_suggest.setVisibility(8);
        LinearLayout ll_share_title = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_share_title);
        Intrinsics.checkExpressionValueIsNotNull(ll_share_title, "ll_share_title");
        ll_share_title.setVisibility(8);
        new Handler().postDelayed(new Runnable() { // from class: com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity.shareIndoor.1
            @Override // java.lang.Runnable
            public final void run() {
                WaitingDialog.hideDialog();
                new ShareUtil().shotLongScreen((LinearLayout) SportHistoryDetailNewActivity.this._$_findCachedViewById(com.ido.life.R.id.ll_bottom_out), (LinearLayout) SportHistoryDetailNewActivity.this._$_findCachedViewById(com.ido.life.R.id.ll_bottom_out));
                final SportShareDialogFragment sportShareDialogFragment = SportShareDialogFragment.newInstance();
                Intrinsics.checkExpressionValueIsNotNull(sportShareDialogFragment, "sportShareDialogFragment");
                sportShareDialogFragment.setCancelable(false);
                sportShareDialogFragment.show(SportHistoryDetailNewActivity.this.getSupportFragmentManager());
                sportShareDialogFragment.setOnShareResultListener(new SportShareDialogFragment.OnShareChooseListener() { // from class: com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity.shareIndoor.1.1
                    @Override // com.ido.life.dialog.SportShareDialogFragment.OnShareChooseListener
                    public final void onSharePlatChoose(int i) {
                        LinearLayout ll_sport_suggest2 = (LinearLayout) SportHistoryDetailNewActivity.this._$_findCachedViewById(com.ido.life.R.id.ll_sport_suggest);
                        Intrinsics.checkExpressionValueIsNotNull(ll_sport_suggest2, "ll_sport_suggest");
                        ll_sport_suggest2.setVisibility(0);
                        LinearLayout ll_share_title2 = (LinearLayout) SportHistoryDetailNewActivity.this._$_findCachedViewById(com.ido.life.R.id.ll_share_title);
                        Intrinsics.checkExpressionValueIsNotNull(ll_share_title2, "ll_share_title");
                        ll_share_title2.setVisibility(8);
                        FrameLayout title_bar2 = (FrameLayout) SportHistoryDetailNewActivity.this._$_findCachedViewById(com.ido.life.R.id.title_bar);
                        Intrinsics.checkExpressionValueIsNotNull(title_bar2, "title_bar");
                        title_bar2.setVisibility(0);
                        sportShareDialogFragment.dismissAllowingStateLoss();
                    }
                });
            }
        }, 1000L);
    }

    public void showConfirmDelete(final String mDateTime) {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(ResourceUtil.getString(R.string.sport_detail_confirm_delete_content), ResourceUtil.getString(R.string.sport_detail_confirm_delete_confirm), ResourceUtil.getString(R.string.sport_detail_confirm_delete_cancel), true);
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity.showConfirmDelete.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity.showConfirmDelete.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                SportHistoryDetailNewActivity.access$getMPresenter$p(SportHistoryDetailNewActivity.this).deleteRecord(mDateTime);
            }
        });
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
    }

    private final void toShare() {
        WaitingDialog.showDialog(this);
        LinearLayout ll_sport_suggest = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_sport_suggest);
        Intrinsics.checkExpressionValueIsNotNull(ll_sport_suggest, "ll_sport_suggest");
        ll_sport_suggest.setVisibility(8);
        LinearLayout ll_share_title = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_share_title);
        Intrinsics.checkExpressionValueIsNotNull(ll_share_title, "ll_share_title");
        ll_share_title.setVisibility(8);
        new ShareUtil().shotWithMapView(this.mapModel, (RelativeLayout) _$_findCachedViewById(com.ido.life.R.id.rv_map_out_root), false, (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_bottom_out), (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_share_title));
        new Handler().postDelayed(new Runnable() { // from class: com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity.toShare.1
            @Override // java.lang.Runnable
            public final void run() {
                WaitingDialog.hideDialog();
                final SportShareDialogFragment sportShareDialogFragment = SportShareDialogFragment.newInstance();
                Intrinsics.checkExpressionValueIsNotNull(sportShareDialogFragment, "sportShareDialogFragment");
                sportShareDialogFragment.setCancelable(false);
                sportShareDialogFragment.show(SportHistoryDetailNewActivity.this.getSupportFragmentManager());
                sportShareDialogFragment.setOnShareResultListener(new SportShareDialogFragment.OnShareChooseListener() { // from class: com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity.toShare.1.1
                    @Override // com.ido.life.dialog.SportShareDialogFragment.OnShareChooseListener
                    public final void onSharePlatChoose(int i) {
                        LinearLayout ll_sport_suggest2 = (LinearLayout) SportHistoryDetailNewActivity.this._$_findCachedViewById(com.ido.life.R.id.ll_sport_suggest);
                        Intrinsics.checkExpressionValueIsNotNull(ll_sport_suggest2, "ll_sport_suggest");
                        ll_sport_suggest2.setVisibility(0);
                        LinearLayout ll_share_title2 = (LinearLayout) SportHistoryDetailNewActivity.this._$_findCachedViewById(com.ido.life.R.id.ll_share_title);
                        Intrinsics.checkExpressionValueIsNotNull(ll_share_title2, "ll_share_title");
                        ll_share_title2.setVisibility(8);
                        sportShareDialogFragment.dismissAllowingStateLoss();
                    }
                });
            }
        }, 1000L);
    }
}