package com.ido.life.util;

import android.media.AudioManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.VeryFitApp;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: AudioUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/ido/life/util/AudioUtils;", "", "()V", "setVolume", "", FirebaseAnalytics.Param.LEVEL, "", "app_release"}, k = 1, mv = {1, 1, 16})
public final class AudioUtils {
    public static final AudioUtils INSTANCE = new AudioUtils();

    private AudioUtils() {
    }

    @JvmStatic
    public static final void setVolume(int level) {
        AudioManager audioManager = (AudioManager) VeryFitApp.getApp().getSystemService("audio");
        int streamMaxVolume = audioManager != null ? audioManager.getStreamMaxVolume(3) : 100;
        CommonLogUtil.printAndSave("音乐最大音量：" + streamMaxVolume + ", 当前音量值：" + (audioManager != null ? audioManager.getStreamVolume(3) : 0) + ", 设置音量百分比：" + level);
        if (audioManager != null) {
            audioManager.setStreamVolume(3, (int) ((level / 100.0f) * streamMaxVolume), 1);
        }
    }
}