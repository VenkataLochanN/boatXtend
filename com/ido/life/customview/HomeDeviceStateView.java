package com.ido.life.customview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.R;
import com.ido.life.base.SimpleAnimatorListener;
import com.ido.life.module.bind.ChoiceBlueTypeActivity;
import com.ido.life.module.bind.scan.ScanCodeActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeDeviceStateView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u001bJ\u0006\u0010\u001d\u001a\u00020\u001bJ\b\u0010\u001e\u001a\u00020\u0018H\u0002J\u0012\u0010\u001f\u001a\u00020\u001b2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\u000fH\u0002J\u0006\u0010$\u001a\u00020\u001bJ\b\u0010%\u001a\u00020\u001bH\u0002J\b\u0010&\u001a\u00020\u001bH\u0002J\b\u0010'\u001a\u00020\u001bH\u0002J\b\u0010(\u001a\u00020\u001bH\u0002J\b\u0010)\u001a\u00020\u001bH\u0002J\b\u0010*\u001a\u00020\u001bH\u0002J\u0006\u0010+\u001a\u00020\u001bJ\u000e\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\tJ\u0006\u0010.\u001a\u00020\u001bJ\u0006\u0010/\u001a\u00020\u001bR\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/ido/life/customview/HomeDeviceStateView;", "Landroid/widget/LinearLayout;", "Landroid/view/View$OnClickListener;", "context", "Landroid/content/Context;", "attributes", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DEVICE_SYNC_COMPLETE_DURATION", "", "DISMISS_DURATION", "", "SYNC_SUCCESS_DURATION", "SYNC_TIMEOUT_DISMISS_DURATION", "TAG", "", "kotlin.jvm.PlatformType", "mDelayShowNormalStateRunnable", "Ljava/lang/Runnable;", "mDeviceSyncCompleteAnimator", "Landroid/animation/ValueAnimator;", "mDeviceSyncSuccessAnimator", "mHideToShowAnimator", "mMorePop", "Landroid/widget/PopupWindow;", "mShowToHideAnimator", "connectFailed", "", "connectSuccess", "destroy", "getMorePopWindow", "onClick", "v", "Landroid/view/View;", "printAndSaveLog", "message", "startConnectDevice", "startDeviceConnectingAnimator", "startDeviceSyncCompleteAnimator", "startDeviceSyncSuccessAnimator", "stopDeviceConnectionAnimator", "stopDeviceSyncCompleteAnimator", "stopDeviceSyncSuccessAnimator", "syncFailed", "syncProgress", NotificationCompat.CATEGORY_PROGRESS, "syncSuccess", "syncing", "app_release"}, k = 1, mv = {1, 1, 16})
public final class HomeDeviceStateView extends LinearLayout implements View.OnClickListener {
    private final int DEVICE_SYNC_COMPLETE_DURATION;
    private final long DISMISS_DURATION;
    private final int SYNC_SUCCESS_DURATION;
    private final long SYNC_TIMEOUT_DISMISS_DURATION;
    private final String TAG;
    private HashMap _$_findViewCache;
    private Runnable mDelayShowNormalStateRunnable;
    private ValueAnimator mDeviceSyncCompleteAnimator;
    private ValueAnimator mDeviceSyncSuccessAnimator;
    private ValueAnimator mHideToShowAnimator;
    private PopupWindow mMorePop;
    private ValueAnimator mShowToHideAnimator;

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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeDeviceStateView(Context context, AttributeSet attributes) {
        super(context, attributes);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributes, "attributes");
        this.TAG = HomeDeviceStateView.class.getSimpleName();
        this.DISMISS_DURATION = 1100L;
        this.SYNC_TIMEOUT_DISMISS_DURATION = 120000L;
        this.SYNC_SUCCESS_DURATION = 300;
        this.DEVICE_SYNC_COMPLETE_DURATION = 500;
        this.mDelayShowNormalStateRunnable = new Runnable() { // from class: com.ido.life.customview.HomeDeviceStateView$mDelayShowNormalStateRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                this.this$0.printAndSaveLog("开始显示字体");
                HomeSyncProgressView homeSyncProgressView = (HomeSyncProgressView) this.this$0._$_findCachedViewById(R.id.sync_progress_bar);
                if (homeSyncProgressView != null) {
                    homeSyncProgressView.setVisibility(8);
                }
                ImageView imageView = (ImageView) this.this$0._$_findCachedViewById(R.id.img_sync_state);
                if (imageView != null) {
                    imageView.setVisibility(8);
                }
                ImageView imageView2 = (ImageView) this.this$0._$_findCachedViewById(R.id.img_connect_state);
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                }
                TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tv_progress_desc);
                if (textView != null) {
                    textView.setVisibility(8);
                }
                this.this$0.stopDeviceConnectionAnimator();
                this.this$0.stopDeviceSyncSuccessAnimator();
                this.this$0.stopDeviceSyncCompleteAnimator();
                this.this$0.startDeviceSyncCompleteAnimator();
            }
        };
        LayoutInflater.from(context).inflate(com.boat.Xtend.two.R.layout.include_layout_home_title_bar, (ViewGroup) this, true);
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.lay_more);
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.lay_more);
        if (linearLayout2 != null) {
            linearLayout2.setOnClickListener(this);
        }
    }

    public final void startConnectDevice() {
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.img_left);
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        HomeSyncProgressView homeSyncProgressView = (HomeSyncProgressView) _$_findCachedViewById(R.id.sync_progress_bar);
        if (homeSyncProgressView != null) {
            homeSyncProgressView.setVisibility(8);
        }
        ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.img_sync_state);
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        ImageView imageView3 = (ImageView) _$_findCachedViewById(R.id.img_connect_state);
        if (imageView3 != null) {
            imageView3.setVisibility(0);
        }
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_progress_desc);
        if (textView != null) {
            textView.setVisibility(8);
        }
        ImageView img_connect_state = (ImageView) _$_findCachedViewById(R.id.img_connect_state);
        Intrinsics.checkExpressionValueIsNotNull(img_connect_state, "img_connect_state");
        img_connect_state.setAlpha(0.3f);
        ImageView imageView4 = (ImageView) _$_findCachedViewById(R.id.img_connect_state);
        if (imageView4 != null) {
            imageView4.setImageResource(com.boat.Xtend.two.R.mipmap.home_device_connecting);
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.lay_refresh_state);
        if (linearLayout != null) {
            linearLayout.removeCallbacks(this.mDelayShowNormalStateRunnable);
        }
        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.lay_refresh_state);
        if (linearLayout2 != null) {
            linearLayout2.postDelayed(this.mDelayShowNormalStateRunnable, this.SYNC_TIMEOUT_DISMISS_DURATION);
        }
        startDeviceConnectingAnimator();
    }

    public final void connectSuccess() {
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.img_left);
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        HomeSyncProgressView homeSyncProgressView = (HomeSyncProgressView) _$_findCachedViewById(R.id.sync_progress_bar);
        if (homeSyncProgressView != null) {
            homeSyncProgressView.setVisibility(8);
        }
        ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.img_sync_state);
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        ImageView imageView3 = (ImageView) _$_findCachedViewById(R.id.img_connect_state);
        if (imageView3 != null) {
            imageView3.setVisibility(0);
        }
        ImageView imageView4 = (ImageView) _$_findCachedViewById(R.id.img_connect_state);
        if (imageView4 != null) {
            imageView4.setAlpha(1.0f);
        }
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_progress_desc);
        if (textView != null) {
            textView.setVisibility(8);
        }
        ImageView imageView5 = (ImageView) _$_findCachedViewById(R.id.img_connect_state);
        if (imageView5 != null) {
            imageView5.setImageResource(com.boat.Xtend.two.R.mipmap.home_device_connect_success);
        }
        stopDeviceConnectionAnimator();
        stopDeviceSyncSuccessAnimator();
        stopDeviceSyncCompleteAnimator();
    }

    public final void connectFailed() {
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.img_left);
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        HomeSyncProgressView homeSyncProgressView = (HomeSyncProgressView) _$_findCachedViewById(R.id.sync_progress_bar);
        if (homeSyncProgressView != null) {
            homeSyncProgressView.setVisibility(8);
        }
        ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.img_sync_state);
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        ImageView imageView3 = (ImageView) _$_findCachedViewById(R.id.img_connect_state);
        if (imageView3 != null) {
            imageView3.setVisibility(0);
        }
        ImageView imageView4 = (ImageView) _$_findCachedViewById(R.id.img_connect_state);
        if (imageView4 != null) {
            imageView4.setAlpha(1.0f);
        }
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_progress_desc);
        if (textView != null) {
            textView.setVisibility(0);
        }
        ImageView imageView5 = (ImageView) _$_findCachedViewById(R.id.img_connect_state);
        if (imageView5 != null) {
            imageView5.setImageResource(com.boat.Xtend.two.R.mipmap.home_device_connect_failed);
        }
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tv_progress_desc);
        if (textView2 != null) {
            textView2.setText(LanguageUtil.getLanguageText(com.boat.Xtend.two.R.string.device_connect_failed));
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.lay_refresh_state);
        if (linearLayout != null) {
            linearLayout.removeCallbacks(this.mDelayShowNormalStateRunnable);
        }
        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.lay_refresh_state);
        if (linearLayout2 != null) {
            linearLayout2.postDelayed(this.mDelayShowNormalStateRunnable, this.DISMISS_DURATION);
        }
        stopDeviceConnectionAnimator();
        stopDeviceSyncSuccessAnimator();
        stopDeviceSyncCompleteAnimator();
    }

    public final void syncing() {
        printAndSaveLog("数据正在同步中。");
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.img_left);
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        HomeSyncProgressView homeSyncProgressView = (HomeSyncProgressView) _$_findCachedViewById(R.id.sync_progress_bar);
        if (homeSyncProgressView != null) {
            homeSyncProgressView.setVisibility(0);
        }
        HomeSyncProgressView homeSyncProgressView2 = (HomeSyncProgressView) _$_findCachedViewById(R.id.sync_progress_bar);
        if (homeSyncProgressView2 != null) {
            homeSyncProgressView2.setProgress(0);
        }
        ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.img_sync_state);
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        ImageView imageView3 = (ImageView) _$_findCachedViewById(R.id.img_connect_state);
        if (imageView3 != null) {
            imageView3.setVisibility(8);
        }
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_progress_desc);
        if (textView != null) {
            textView.setVisibility(8);
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.lay_refresh_state);
        if (linearLayout != null) {
            linearLayout.removeCallbacks(this.mDelayShowNormalStateRunnable);
        }
        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.lay_refresh_state);
        if (linearLayout2 != null) {
            linearLayout2.postDelayed(this.mDelayShowNormalStateRunnable, this.SYNC_TIMEOUT_DISMISS_DURATION);
        }
    }

    public final void syncFailed() {
        printAndSaveLog("数据同步失败。");
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.img_left);
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        HomeSyncProgressView homeSyncProgressView = (HomeSyncProgressView) _$_findCachedViewById(R.id.sync_progress_bar);
        if (homeSyncProgressView != null) {
            homeSyncProgressView.setVisibility(8);
        }
        ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.img_sync_state);
        if (imageView2 != null) {
            imageView2.setVisibility(0);
        }
        ImageView imageView3 = (ImageView) _$_findCachedViewById(R.id.img_connect_state);
        if (imageView3 != null) {
            imageView3.setVisibility(8);
        }
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_progress_desc);
        if (textView != null) {
            textView.setVisibility(0);
        }
        ImageView imageView4 = (ImageView) _$_findCachedViewById(R.id.img_sync_state);
        if (imageView4 != null) {
            imageView4.setImageResource(com.boat.Xtend.two.R.mipmap.home_device_sync_failed);
        }
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tv_progress_desc);
        if (textView2 != null) {
            textView2.setText(LanguageUtil.getLanguageText(com.boat.Xtend.two.R.string.synced_failed));
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.lay_refresh_state);
        if (linearLayout != null) {
            linearLayout.removeCallbacks(this.mDelayShowNormalStateRunnable);
        }
        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.lay_refresh_state);
        if (linearLayout2 != null) {
            linearLayout2.postDelayed(this.mDelayShowNormalStateRunnable, this.DISMISS_DURATION);
        }
    }

    public final void syncSuccess() {
        printAndSaveLog("数据同步成功。");
        if (((HomeSyncProgressView) _$_findCachedViewById(R.id.sync_progress_bar)) != null) {
            HomeSyncProgressView homeSyncProgressView = (HomeSyncProgressView) _$_findCachedViewById(R.id.sync_progress_bar);
            if (homeSyncProgressView == null) {
                Intrinsics.throwNpe();
            }
            if (homeSyncProgressView.getVisibility() == 8) {
                return;
            }
            ImageView imageView = (ImageView) _$_findCachedViewById(R.id.img_left);
            if (imageView != null) {
                imageView.setVisibility(8);
            }
            ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.img_connect_state);
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            TextView textView = (TextView) _$_findCachedViewById(R.id.tv_progress_desc);
            if (textView != null) {
                textView.setVisibility(8);
            }
            HomeSyncProgressView homeSyncProgressView2 = (HomeSyncProgressView) _$_findCachedViewById(R.id.sync_progress_bar);
            if (homeSyncProgressView2 != null) {
                homeSyncProgressView2.setVisibility(8);
            }
            ImageView imageView3 = (ImageView) _$_findCachedViewById(R.id.img_sync_state);
            if (imageView3 != null) {
                imageView3.setImageResource(com.boat.Xtend.two.R.mipmap.home_data_sync_success);
            }
            ImageView imageView4 = (ImageView) _$_findCachedViewById(R.id.img_sync_state);
            if (imageView4 != null) {
                imageView4.setVisibility(0);
            }
            ImageView imageView5 = (ImageView) _$_findCachedViewById(R.id.img_sync_state);
            if (imageView5 != null) {
                imageView5.setAlpha(1.0f);
            }
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.lay_refresh_state);
            if (linearLayout != null) {
                linearLayout.removeCallbacks(this.mDelayShowNormalStateRunnable);
            }
            LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.lay_refresh_state);
            if (linearLayout2 != null) {
                linearLayout2.postDelayed(this.mDelayShowNormalStateRunnable, this.DISMISS_DURATION);
            }
            stopDeviceSyncSuccessAnimator();
            startDeviceSyncSuccessAnimator();
        }
    }

    public final void syncProgress(int progress) {
        printAndSaveLog("数据同步进度 progress=" + progress);
        HomeSyncProgressView homeSyncProgressView = (HomeSyncProgressView) _$_findCachedViewById(R.id.sync_progress_bar);
        if (homeSyncProgressView != null) {
            homeSyncProgressView.setProgress(progress);
        }
    }

    private final void startDeviceConnectingAnimator() {
        stopDeviceConnectionAnimator();
        this.mHideToShowAnimator = ObjectAnimator.ofFloat((ImageView) _$_findCachedViewById(R.id.img_connect_state), "alpha", 0.3f, 1.0f);
        ValueAnimator valueAnimator = this.mHideToShowAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator.setStartDelay(600L);
        ValueAnimator valueAnimator2 = this.mHideToShowAnimator;
        if (valueAnimator2 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator2.setDuration(800L);
        ValueAnimator valueAnimator3 = this.mHideToShowAnimator;
        if (valueAnimator3 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator3.setRepeatCount(1);
        ValueAnimator valueAnimator4 = this.mHideToShowAnimator;
        if (valueAnimator4 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator4.setRepeatMode(1);
        ValueAnimator valueAnimator5 = this.mHideToShowAnimator;
        if (valueAnimator5 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator5.setInterpolator(new LinearInterpolator());
        this.mShowToHideAnimator = ObjectAnimator.ofFloat((ImageView) _$_findCachedViewById(R.id.img_connect_state), "alpha", 1.0f, 0.3f);
        ValueAnimator valueAnimator6 = this.mShowToHideAnimator;
        if (valueAnimator6 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator6.setStartDelay(600L);
        ValueAnimator valueAnimator7 = this.mShowToHideAnimator;
        if (valueAnimator7 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator7.setDuration(800L);
        ValueAnimator valueAnimator8 = this.mShowToHideAnimator;
        if (valueAnimator8 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator8.setRepeatCount(1);
        ValueAnimator valueAnimator9 = this.mShowToHideAnimator;
        if (valueAnimator9 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator9.setRepeatMode(1);
        ValueAnimator valueAnimator10 = this.mShowToHideAnimator;
        if (valueAnimator10 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator10.setInterpolator(new LinearInterpolator());
        ValueAnimator valueAnimator11 = this.mHideToShowAnimator;
        if (valueAnimator11 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator11.addListener(new SimpleAnimatorListener() { // from class: com.ido.life.customview.HomeDeviceStateView.startDeviceConnectingAnimator.1
            @Override // com.ido.life.base.SimpleAnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                ValueAnimator valueAnimator12 = HomeDeviceStateView.this.mShowToHideAnimator;
                if (valueAnimator12 != null) {
                    valueAnimator12.start();
                }
            }
        });
        ValueAnimator valueAnimator12 = this.mShowToHideAnimator;
        if (valueAnimator12 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator12.addListener(new SimpleAnimatorListener() { // from class: com.ido.life.customview.HomeDeviceStateView.startDeviceConnectingAnimator.2
            @Override // com.ido.life.base.SimpleAnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                ValueAnimator valueAnimator13 = HomeDeviceStateView.this.mHideToShowAnimator;
                if (valueAnimator13 != null) {
                    valueAnimator13.start();
                }
            }
        });
        ValueAnimator valueAnimator13 = this.mHideToShowAnimator;
        if (valueAnimator13 != null) {
            valueAnimator13.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void stopDeviceConnectionAnimator() {
        /*
            r1 = this;
            android.animation.ValueAnimator r0 = r1.mHideToShowAnimator
            if (r0 == 0) goto L26
            if (r0 != 0) goto L9
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L9:
            boolean r0 = r0.isRunning()
            if (r0 != 0) goto L1c
            android.animation.ValueAnimator r0 = r1.mHideToShowAnimator
            if (r0 != 0) goto L16
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L16:
            boolean r0 = r0.isStarted()
            if (r0 == 0) goto L26
        L1c:
            android.animation.ValueAnimator r0 = r1.mHideToShowAnimator
            if (r0 != 0) goto L23
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L23:
            r0.cancel()
        L26:
            android.animation.ValueAnimator r0 = r1.mShowToHideAnimator
            if (r0 == 0) goto L4c
            if (r0 != 0) goto L2f
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L2f:
            boolean r0 = r0.isRunning()
            if (r0 != 0) goto L42
            android.animation.ValueAnimator r0 = r1.mShowToHideAnimator
            if (r0 != 0) goto L3c
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L3c:
            boolean r0 = r0.isStarted()
            if (r0 == 0) goto L4c
        L42:
            android.animation.ValueAnimator r0 = r1.mShowToHideAnimator
            if (r0 != 0) goto L49
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L49:
            r0.cancel()
        L4c:
            r0 = 0
            android.animation.ValueAnimator r0 = (android.animation.ValueAnimator) r0
            r1.mHideToShowAnimator = r0
            r1.mShowToHideAnimator = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.HomeDeviceStateView.stopDeviceConnectionAnimator():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void stopDeviceSyncSuccessAnimator() {
        /*
            r1 = this;
            android.animation.ValueAnimator r0 = r1.mDeviceSyncSuccessAnimator
            if (r0 == 0) goto L26
            if (r0 != 0) goto L9
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L9:
            boolean r0 = r0.isRunning()
            if (r0 != 0) goto L1c
            android.animation.ValueAnimator r0 = r1.mDeviceSyncSuccessAnimator
            if (r0 != 0) goto L16
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L16:
            boolean r0 = r0.isStarted()
            if (r0 == 0) goto L26
        L1c:
            android.animation.ValueAnimator r0 = r1.mDeviceSyncSuccessAnimator
            if (r0 != 0) goto L23
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L23:
            r0.cancel()
        L26:
            r0 = 0
            android.animation.ValueAnimator r0 = (android.animation.ValueAnimator) r0
            r1.mDeviceSyncSuccessAnimator = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.HomeDeviceStateView.stopDeviceSyncSuccessAnimator():void");
    }

    private final void startDeviceSyncSuccessAnimator() {
        if (((ImageView) _$_findCachedViewById(R.id.img_sync_state)) != null) {
            ImageView imageView = (ImageView) _$_findCachedViewById(R.id.img_sync_state);
            if (imageView == null) {
                Intrinsics.throwNpe();
            }
            if (imageView.getVisibility() != 0) {
                return;
            }
            printAndSaveLog("开始执行动画。");
            this.mDeviceSyncSuccessAnimator = ObjectAnimator.ofFloat((ImageView) _$_findCachedViewById(R.id.img_sync_state), "alpha", 1.0f, 0.0f);
            ValueAnimator valueAnimator = this.mDeviceSyncSuccessAnimator;
            if (valueAnimator == null) {
                Intrinsics.throwNpe();
            }
            valueAnimator.setRepeatCount(0);
            ValueAnimator valueAnimator2 = this.mDeviceSyncSuccessAnimator;
            if (valueAnimator2 == null) {
                Intrinsics.throwNpe();
            }
            valueAnimator2.setRepeatMode(1);
            ValueAnimator valueAnimator3 = this.mDeviceSyncSuccessAnimator;
            if (valueAnimator3 == null) {
                Intrinsics.throwNpe();
            }
            valueAnimator3.setDuration(this.SYNC_SUCCESS_DURATION);
            ValueAnimator valueAnimator4 = this.mDeviceSyncSuccessAnimator;
            if (valueAnimator4 == null) {
                Intrinsics.throwNpe();
            }
            valueAnimator4.setInterpolator(new LinearInterpolator());
            ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.img_sync_state);
            if (imageView2 != null) {
                imageView2.postDelayed(new Runnable() { // from class: com.ido.life.customview.HomeDeviceStateView.startDeviceSyncSuccessAnimator.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ValueAnimator valueAnimator5 = HomeDeviceStateView.this.mDeviceSyncSuccessAnimator;
                        if (valueAnimator5 != null) {
                            valueAnimator5.start();
                        }
                    }
                }, 1000L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void stopDeviceSyncCompleteAnimator() {
        /*
            r1 = this;
            android.animation.ValueAnimator r0 = r1.mDeviceSyncCompleteAnimator
            if (r0 == 0) goto L26
            if (r0 != 0) goto L9
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L9:
            boolean r0 = r0.isStarted()
            if (r0 != 0) goto L1c
            android.animation.ValueAnimator r0 = r1.mDeviceSyncCompleteAnimator
            if (r0 != 0) goto L16
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L16:
            boolean r0 = r0.isRunning()
            if (r0 == 0) goto L26
        L1c:
            android.animation.ValueAnimator r0 = r1.mDeviceSyncCompleteAnimator
            if (r0 != 0) goto L23
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L23:
            r0.cancel()
        L26:
            r0 = 0
            android.animation.ValueAnimator r0 = (android.animation.ValueAnimator) r0
            r1.mDeviceSyncCompleteAnimator = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.HomeDeviceStateView.stopDeviceSyncCompleteAnimator():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startDeviceSyncCompleteAnimator() {
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.img_left);
        if (imageView != null) {
            imageView.setAlpha(0.0f);
        }
        ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.img_left);
        if (imageView2 != null) {
            imageView2.setVisibility(0);
        }
        this.mDeviceSyncCompleteAnimator = ObjectAnimator.ofFloat((ImageView) _$_findCachedViewById(R.id.img_left), "alpha", 0.0f, 1.0f);
        ValueAnimator valueAnimator = this.mDeviceSyncCompleteAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator.setDuration(this.DEVICE_SYNC_COMPLETE_DURATION);
        ValueAnimator valueAnimator2 = this.mDeviceSyncCompleteAnimator;
        if (valueAnimator2 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator2.setInterpolator(new LinearInterpolator());
        ValueAnimator valueAnimator3 = this.mDeviceSyncCompleteAnimator;
        if (valueAnimator3 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator3.start();
    }

    public final void destroy() {
        stopDeviceConnectionAnimator();
        stopDeviceSyncCompleteAnimator();
        stopDeviceSyncSuccessAnimator();
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.lay_refresh_state);
        if (linearLayout != null) {
            linearLayout.removeCallbacks(this.mDelayShowNormalStateRunnable);
        }
        HomeSyncProgressView homeSyncProgressView = (HomeSyncProgressView) _$_findCachedViewById(R.id.sync_progress_bar);
        if (homeSyncProgressView != null) {
            homeSyncProgressView.destroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void printAndSaveLog(String message) {
        if (message.length() == 0) {
            return;
        }
        CommonLogUtil.d(this.TAG, message);
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getLogPath(), this.TAG, message);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == com.boat.Xtend.two.R.id.lay_more) {
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
            popupWindow3.showAsDropDown((ConstraintLayout) _$_findCachedViewById(R.id.layout_title), 0, 0, 5);
        }
    }

    private final PopupWindow getMorePopWindow() {
        final PopupWindow popupWindow = new PopupWindow(-2, -2);
        View viewInflate = LayoutInflater.from(getContext()).inflate(com.boat.Xtend.two.R.layout.device_more_pop_layout, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(com.boat.Xtend.two.R.id.tv_add_device);
        TextView textView2 = (TextView) viewInflate.findViewById(com.boat.Xtend.two.R.id.tv_scan);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.customview.HomeDeviceStateView.getMorePopWindow.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeDeviceStateView.this.getContext().startActivity(new Intent(HomeDeviceStateView.this.getContext(), (Class<?>) ChoiceBlueTypeActivity.class));
                popupWindow.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.customview.HomeDeviceStateView.getMorePopWindow.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeDeviceStateView.this.getContext().startActivity(new Intent(HomeDeviceStateView.this.getContext(), (Class<?>) ScanCodeActivity.class));
                popupWindow.dismiss();
            }
        });
        popupWindow.setContentView(viewInflate);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(com.boat.Xtend.two.R.style.pop_more);
        return popupWindow;
    }
}