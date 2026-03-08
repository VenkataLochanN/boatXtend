package com.ido.life.util;

import android.content.Intent;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.text.TextUtils;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.MusicControlInfo;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.life.VeryFitApp;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.MusicMsgBean;
import com.ido.life.boatservice.NBoatService;
import com.ido.life.data.cache.AbsDataCacheManager;
import com.ido.life.data.cache.AppNameLanguageManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProducerScope;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: MusicHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 52\b\u0012\u0004\u0012\u00020\u00020\u0001:\u000256B\u0005¢\u0006\u0002\u0010\u0003J\u001a\u0010\u001e\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010 H\u0002J$\u0010!\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00050 2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%J\u0010\u0010'\u001a\u00020%2\u0006\u0010(\u001a\u00020\u0005H\u0002J\u0010\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020\u0005H\u0002J\u0012\u0010+\u001a\u00020\"2\b\u0010,\u001a\u0004\u0018\u00010\fH\u0016J\u0016\u0010-\u001a\u00020\"2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020%0/H\u0007J\u0014\u00100\u001a\u00020\"2\n\u0010.\u001a\u0006\u0012\u0002\b\u00030/H\u0007J\u0012\u00101\u001a\u00020\"2\b\u0010,\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u00102\u001a\u00020\"2\u0006\u00103\u001a\u00020\fH\u0002J\b\u00104\u001a\u00020\"H\u0002R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR*\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f\u0012\b\u0012\u00060\rR\u00020\u00000\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u00067"}, d2 = {"Lcom/ido/life/util/MusicHelper;", "Lcom/ido/life/data/cache/AbsDataCacheManager;", "", "()V", "mActivedMediaController", "Landroid/media/session/MediaController;", "getMActivedMediaController", "()Landroid/media/session/MediaController;", "setMActivedMediaController", "(Landroid/media/session/MediaController;)V", "mCallbackMap", "", "", "Lcom/ido/life/util/MusicHelper$MediaControllerCallback;", "getMCallbackMap", "()Ljava/util/Map;", "setMCallbackMap", "(Ljava/util/Map;)V", "mMediaControllerList", "", "getMMediaControllerList", "()Ljava/util/List;", "setMMediaControllerList", "(Ljava/util/List;)V", "musicMsgBean", "Lcom/ido/life/bean/MusicMsgBean;", "getMusicMsgBean", "()Lcom/ido/life/bean/MusicMsgBean;", "setMusicMsgBean", "(Lcom/ido/life/bean/MusicMsgBean;)V", "findActivedController", "controllerList", "", "handleActiveSessionChanged", "", "controllers", "forceUpdate", "", "isSendUpdateCmd", "isFilterWebController", "activedMediaController", "isPlayStatus", "mediaController", "onBind", "macAddress", "onMusicControlChanged", "message", "Lcom/ido/life/base/BaseMessage;", "onNotificationPermissionChanged", "onSdkInitComplete", "printAndSave", "msg", "reSetMusicInfo2Device", "Companion", "MediaControllerCallback", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MusicHelper extends AbsDataCacheManager<Object> {
    private static final String ACTION_PLAYER = "android.intent.action.MUSIC_PLAYER";
    private static final String CHROME_PACKAGE_NAME = "com.android.chrome";
    private static final String KUGOU_PACKAGE_NAME = "com.kugou.android";
    public static final int MUSIC_STATE_PAUSE = 2;
    public static final int MUSIC_STATE_PLAY = 1;
    private static final String QQ_PACKAGE_NAME = "com.tencent.mtt";
    private static final String TAG = "MusicHelper";
    private static final String UC_PACKAGE_NAME = "com.UCMobile";
    private static boolean isSendCmd;
    private static int playState;
    private MediaController mActivedMediaController;
    private MusicMsgBean musicMsgBean;
    private List<MediaController> mMediaControllerList = new ArrayList();
    private Map<String, MediaControllerCallback> mCallbackMap = new HashMap();

    public final List<MediaController> getMMediaControllerList() {
        return this.mMediaControllerList;
    }

    public final void setMMediaControllerList(List<MediaController> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.mMediaControllerList = list;
    }

    public final MediaController getMActivedMediaController() {
        return this.mActivedMediaController;
    }

    public final void setMActivedMediaController(MediaController mediaController) {
        this.mActivedMediaController = mediaController;
    }

    public final Map<String, MediaControllerCallback> getMCallbackMap() {
        return this.mCallbackMap;
    }

    public final void setMCallbackMap(Map<String, MediaControllerCallback> map) {
        Intrinsics.checkParameterIsNotNull(map, "<set-?>");
        this.mCallbackMap = map;
    }

    public final MusicMsgBean getMusicMsgBean() {
        return this.musicMsgBean;
    }

    public final void setMusicMsgBean(MusicMsgBean musicMsgBean) {
        this.musicMsgBean = musicMsgBean;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMusicControlChanged(BaseMessage<Boolean> message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        if (message.getType() == 908) {
            Boolean data = message.getData();
            Intrinsics.checkExpressionValueIsNotNull(data, "message.data");
            if (data.booleanValue()) {
                printAndSave("onMusicControlChanged");
                reSetMusicInfo2Device();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onNotificationPermissionChanged(BaseMessage<?> message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        if (message.getType() == 903) {
            printAndSave("onNotificationPermissionChanged");
            VeryFitApp.getApp().startService(new Intent(VeryFitApp.getApp(), (Class<?>) NBoatService.class));
        }
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onSdkInitComplete(String macAddress) {
        super.onSdkInitComplete(macAddress);
        reSetMusicInfo2Device();
    }

    @Override // com.ido.life.data.cache.AbsDataCacheManager
    public void onBind(String macAddress) {
        super.onBind(macAddress);
        reSetMusicInfo2Device();
    }

    private final void reSetMusicInfo2Device() {
        if (!this.mCallbackMap.isEmpty()) {
            Iterator<T> it = this.mCallbackMap.values().iterator();
            while (it.hasNext()) {
                ((MediaControllerCallback) it.next()).reSetMusicInfo();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void printAndSave(String msg) {
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getNotificationLogPath(), getTAG(), msg);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00dc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void handleActiveSessionChanged(java.util.List<android.media.session.MediaController> r5, boolean r6, boolean r7) {
        /*
            Method dump skipped, instruction units count: 325
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.util.MusicHelper.handleActiveSessionChanged(java.util.List, boolean, boolean):void");
    }

    private final boolean isFilterWebController(MediaController activedMediaController) {
        String packageName = activedMediaController.getPackageName();
        return Intrinsics.areEqual(packageName, QQ_PACKAGE_NAME) || Intrinsics.areEqual(packageName, "com.UCMobile") || Intrinsics.areEqual(packageName, CHROME_PACKAGE_NAME);
    }

    private final MediaController findActivedController(List<MediaController> controllerList) {
        MediaController mediaController;
        if (controllerList == null || controllerList.isEmpty() || (mediaController = controllerList.get(0)) == null) {
            return null;
        }
        PlaybackState playbackState = mediaController.getPlaybackState();
        if (playbackState != null) {
            printAndSave("findActivedController: " + playbackState);
        }
        if (playbackState == null) {
            printAndSave("findActivedController: playbackState 为空");
            for (MediaController mediaController2 : controllerList) {
                if (mediaController == null) {
                    Intrinsics.throwNpe();
                }
                if ((mediaController.getPlaybackState() == null && mediaController2.getPlaybackState() != null) || ((!Intrinsics.areEqual(mediaController, mediaController2)) && isPlayStatus(mediaController2))) {
                    mediaController = mediaController2;
                }
            }
        } else {
            printAndSave("findActivedController: playbackState 不为空");
            try {
                if (playbackState.getState() != 3 && playbackState.getState() != 6) {
                    for (MediaController mediaController3 : controllerList) {
                        if (mediaController == null) {
                            Intrinsics.throwNpe();
                        }
                        if ((mediaController.getPlaybackState() == null && mediaController3.getPlaybackState() != null) || ((!Intrinsics.areEqual(mediaController, mediaController3)) && isPlayStatus(mediaController3))) {
                            mediaController = mediaController3;
                        }
                    }
                }
            } catch (Exception unused) {
                printAndSave("findActivedController: 获取 playbackState 异常");
            }
        }
        MediaController mediaController4 = this.mActivedMediaController;
        if (mediaController4 != null) {
            if (mediaController4 == null) {
                Intrinsics.throwNpe();
            }
            String packageName = mediaController4.getPackageName();
            if (mediaController == null) {
                Intrinsics.throwNpe();
            }
            if (!TextUtils.equals(packageName, mediaController.getPackageName())) {
                for (MediaController mediaController5 : controllerList) {
                    if (isPlayStatus(mediaController5)) {
                        MediaController mediaController6 = this.mActivedMediaController;
                        if (mediaController6 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (TextUtils.equals(mediaController6.getPackageName(), mediaController5.getPackageName())) {
                            return mediaController5;
                        }
                    }
                }
            }
        }
        return mediaController;
    }

    private final boolean isPlayStatus(MediaController mediaController) {
        try {
            PlaybackState playbackState = mediaController.getPlaybackState();
            if (playbackState == null) {
                return false;
            }
            printAndSave("isPlayStatus: " + playbackState);
            if (playbackState.getState() == 3 || playbackState.getState() == 6) {
                printAndSave("isPlayStatus: 获取状态为空");
                return true;
            }
        } catch (Exception unused) {
            printAndSave("isPlayStatus: 捕获异常");
        }
        return false;
    }

    /* JADX INFO: compiled from: MusicHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0012\u0010\u001f\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0012\u0010\"\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00010$H\u0017J\b\u0010%\u001a\u00020\u001eH\u0016J\u001a\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\u00152\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u0006\u0010*\u001a\u00020\u001eJ\u001a\u0010+\u001a\u00020\u001e2\b\u0010,\u001a\u0004\u0018\u00010\u000f2\u0006\u0010-\u001a\u00020.H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0005\"\u0004\b\u001c\u0010\u0007¨\u0006/"}, d2 = {"Lcom/ido/life/util/MusicHelper$MediaControllerCallback;", "Landroid/media/session/MediaController$Callback;", "(Lcom/ido/life/util/MusicHelper;)V", "isActived", "", "()Z", "setActived", "(Z)V", "mOnMusicControlInfoChangedListener", "Lcom/ido/life/util/OnMusicControlInfoChangedListener;", "getMOnMusicControlInfoChangedListener", "()Lcom/ido/life/util/OnMusicControlInfoChangedListener;", "setMOnMusicControlInfoChangedListener", "(Lcom/ido/life/util/OnMusicControlInfoChangedListener;)V", "musicMsgBean", "Lcom/ido/life/bean/MusicMsgBean;", "getMusicMsgBean", "()Lcom/ido/life/bean/MusicMsgBean;", "setMusicMsgBean", "(Lcom/ido/life/bean/MusicMsgBean;)V", "musicPkg", "", "getMusicPkg", "()Ljava/lang/String;", "setMusicPkg", "(Ljava/lang/String;)V", "useAlbumAsTitle", "getUseAlbumAsTitle", "setUseAlbumAsTitle", "initMusicControlInfoDelay", "", "onMetadataChanged", "metadata", "Landroid/media/MediaMetadata;", "onPlaybackStateChanged", "state", "Landroid/media/session/PlaybackState;", "onSessionDestroyed", "onSessionEvent", "event", "extras", "Landroid/os/Bundle;", "reSetMusicInfo", "setMusicInfo", "music", "playState", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public final class MediaControllerCallback extends MediaController.Callback {
        private boolean isActived;
        private OnMusicControlInfoChangedListener mOnMusicControlInfoChangedListener;
        private MusicMsgBean musicMsgBean;
        private String musicPkg;
        private boolean useAlbumAsTitle;

        public MediaControllerCallback() {
            initMusicControlInfoDelay();
        }

        /* JADX INFO: renamed from: isActived, reason: from getter */
        public final boolean getIsActived() {
            return this.isActived;
        }

        public final void setActived(boolean z) {
            this.isActived = z;
        }

        public final MusicMsgBean getMusicMsgBean() {
            return this.musicMsgBean;
        }

        public final void setMusicMsgBean(MusicMsgBean musicMsgBean) {
            this.musicMsgBean = musicMsgBean;
        }

        public final boolean getUseAlbumAsTitle() {
            return this.useAlbumAsTitle;
        }

        public final void setUseAlbumAsTitle(boolean z) {
            this.useAlbumAsTitle = z;
        }

        public final String getMusicPkg() {
            return this.musicPkg;
        }

        public final void setMusicPkg(String str) {
            this.musicPkg = str;
        }

        @Override // android.media.session.MediaController.Callback
        public void onSessionDestroyed() {
            super.onSessionDestroyed();
            if (this.isActived) {
                if (this.musicMsgBean == null) {
                    this.musicMsgBean = new MusicMsgBean("", "", "", 333);
                }
                MusicMsgBean musicMsgBean = this.musicMsgBean;
                if (musicMsgBean != null) {
                    musicMsgBean.setPlayState(2);
                }
                CommonLogUtil.d(MusicHelper.this.getTAG(), "onSessionDestroyed send cmd to device: " + String.valueOf(this.musicMsgBean));
            }
        }

        @Override // android.media.session.MediaController.Callback
        public void onSessionEvent(String event, Bundle extras) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            super.onSessionEvent(event, extras);
        }

        @Override // android.media.session.MediaController.Callback
        public void onPlaybackStateChanged(PlaybackState state) {
            super.onPlaybackStateChanged(state);
            if (!this.isActived || state == null) {
                MusicHelper.this.printAndSave("onPlaybackStateChanged--isActived: " + this.isActived + "  state=" + state);
                return;
            }
            if (this.musicMsgBean == null) {
                this.musicMsgBean = new MusicMsgBean("", "", "", 333);
            }
            int state2 = state.getState();
            if (state2 == 2) {
                MusicHelper.playState = 2;
                MusicMsgBean musicMsgBean = this.musicMsgBean;
                if (musicMsgBean == null) {
                    Intrinsics.throwNpe();
                }
                musicMsgBean.setPlayState(2);
                MusicHelper.this.printAndSave("onPlaybackStateChanged--paused: " + state + "  musicMsgBean=" + String.valueOf(this.musicMsgBean));
                if (!MusicHelper.isSendCmd) {
                    MusicHelper.isSendCmd = true;
                    return;
                }
            } else if (state2 == 3) {
                MusicHelper.playState = 1;
                MusicMsgBean musicMsgBean2 = this.musicMsgBean;
                if (musicMsgBean2 == null) {
                    Intrinsics.throwNpe();
                }
                musicMsgBean2.setPlayState(1);
                MusicHelper.this.printAndSave("onPlaybackStateChanged--play: " + state + "  musicMsgBean=" + String.valueOf(this.musicMsgBean));
                if (!MusicHelper.isSendCmd) {
                    MusicHelper.isSendCmd = true;
                    return;
                }
            } else {
                MusicHelper.this.printAndSave("onPlaybackStateChanged-other--state:" + state + "  musicMsgBean=" + String.valueOf(this.musicMsgBean));
                return;
            }
            if (state.getPosition() != 0) {
                MusicMsgBean musicMsgBean3 = this.musicMsgBean;
                if (musicMsgBean3 != null) {
                    musicMsgBean3.setPlayTime(state.getPosition() / ((long) 1000));
                }
            } else {
                MusicMsgBean musicMsgBean4 = this.musicMsgBean;
                if (musicMsgBean4 != null) {
                    musicMsgBean4.setPlayTime(state.getPosition());
                }
            }
            LocalDataManager.getSupportFunctionInfo();
            MusicMsgBean musicMsgBean5 = this.musicMsgBean;
            if (musicMsgBean5 != null) {
                setMusicInfo(musicMsgBean5, MusicHelper.playState);
            }
            MusicHelper.this.printAndSave("onPlaybackStateChanged send cmd to device: " + String.valueOf(this.musicMsgBean));
        }

        @Override // android.media.session.MediaController.Callback
        public void onMetadataChanged(MediaMetadata metadata) {
            super.onMetadataChanged(metadata);
            MusicHelper.this.printAndSave("onMetadataChanged");
            if (metadata == null || !this.isActived) {
                return;
            }
            String string = metadata.getString(MediaMetadataCompat.METADATA_KEY_ARTIST);
            String string2 = metadata.getString(MediaMetadataCompat.METADATA_KEY_ALBUM);
            String string3 = metadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE);
            long j = metadata.getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
            TextUtils.isEmpty(this.musicPkg);
            if (this.musicMsgBean == null) {
                this.musicMsgBean = new MusicMsgBean("", "", "", 0);
            }
            if (j != 0) {
                j /= (long) 1000;
            }
            MusicHelper.this.printAndSave("onMetadataChanged: artist=" + string + " album=" + string2 + " title=" + string3 + " duration=" + j);
            if (TextUtils.isEmpty(string2) && TextUtils.isEmpty(string3)) {
                return;
            }
            try {
                MusicMsgBean musicMsgBean = this.musicMsgBean;
                if (musicMsgBean == null) {
                    Intrinsics.throwNpe();
                }
                musicMsgBean.setPlayState(MusicHelper.playState);
                MusicMsgBean musicMsgBean2 = this.musicMsgBean;
                if (musicMsgBean2 == null) {
                    Intrinsics.throwNpe();
                }
                if (TextUtils.equals(musicMsgBean2.artist, string)) {
                    MusicMsgBean musicMsgBean3 = this.musicMsgBean;
                    if (musicMsgBean3 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (TextUtils.equals(musicMsgBean3.album, string2)) {
                        MusicMsgBean musicMsgBean4 = this.musicMsgBean;
                        if (musicMsgBean4 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (TextUtils.equals(musicMsgBean4.title, string3)) {
                            MusicMsgBean musicMsgBean5 = this.musicMsgBean;
                            if (musicMsgBean5 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (musicMsgBean5.duration == j) {
                                return;
                            }
                        }
                    }
                }
                MusicMsgBean musicMsgBean6 = this.musicMsgBean;
                if (musicMsgBean6 == null) {
                    Intrinsics.throwNpe();
                }
                musicMsgBean6.setArtist(string);
                MusicMsgBean musicMsgBean7 = this.musicMsgBean;
                if (musicMsgBean7 == null) {
                    Intrinsics.throwNpe();
                }
                musicMsgBean7.setAlbum(string2);
                MusicMsgBean musicMsgBean8 = this.musicMsgBean;
                if (musicMsgBean8 == null) {
                    Intrinsics.throwNpe();
                }
                musicMsgBean8.setTitle(string3);
                MusicMsgBean musicMsgBean9 = this.musicMsgBean;
                if (musicMsgBean9 == null) {
                    Intrinsics.throwNpe();
                }
                musicMsgBean9.setDuration(j);
                setMusicInfo(this.musicMsgBean, MusicHelper.playState);
                MusicMsgBean musicMsgBean10 = this.musicMsgBean;
                if (musicMsgBean10 == null) {
                    Intrinsics.throwNpe();
                }
                if (musicMsgBean10.getPlayState() != 2) {
                    MusicHelper.this.printAndSave("onMetadataChanged send cmd to device: " + String.valueOf(this.musicMsgBean));
                }
                MusicHelper.this.printAndSave("onPlaybackStateChanged onMetadataChanged: " + this.musicMsgBean);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        private final void setMusicInfo(MusicMsgBean music, int playState) {
            OnMusicControlInfoChangedListener onMusicControlInfoChangedListener;
            String str;
            String str2;
            MusicControlInfo musicControlInfo = new MusicControlInfo();
            musicControlInfo.status = playState;
            SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
            if (supportFunctionInfo == null) {
                MusicHelper.this.printAndSave("功能表异常！");
                return;
            }
            MusicHelper musicHelper = MusicHelper.this;
            StringBuilder sb = new StringBuilder();
            sb.append("setMusicInfo: \n是否连接设备");
            sb.append(BLEManager.isConnected());
            sb.append("\n音乐名称开关状态：");
            sb.append(SPHelper.getSwitchStatus().musicNameSwitched);
            sb.append("\n音乐控制开关：");
            sb.append(LocalDataManager.getMusicSwitch());
            sb.append("\n音乐名称：");
            sb.append(music != null ? music.title : null);
            sb.append("\n歌手名称：");
            sb.append(music != null ? music.artist : null);
            sb.append("\n时间：");
            sb.append(music != null ? Long.valueOf(music.duration) : null);
            sb.append("\n音乐信息：");
            sb.append(musicControlInfo.toString());
            sb.append("\n 功能表bleControlMusic：");
            sb.append(supportFunctionInfo.bleControlMusic);
            sb.append("\n 功能表V3_music_control_02_add_singer_name：");
            sb.append(supportFunctionInfo.V3_music_control_02_add_singer_name);
            musicHelper.printAndSave(sb.toString());
            if (supportFunctionInfo.bleControlMusic && LocalDataManager.getMusicSwitch()) {
                musicControlInfo.totalTimeSecond = (int) (music != null ? music.duration : 0L);
                musicControlInfo.curTimeSecond = (int) (music != null ? music.playTime : 0L);
            }
            if (SPHelper.getSwitchStatus().musicNameSwitched) {
                if (supportFunctionInfo.V3_music_control_02_add_singer_name) {
                    if (music == null || (str2 = music.artist) == null) {
                        str2 = "";
                    }
                    musicControlInfo.singerName = str2;
                    if (TextUtils.isEmpty(musicControlInfo.singerName)) {
                        musicControlInfo.singerName = " ";
                    }
                } else {
                    musicControlInfo.singerName = "";
                }
                AppNameLanguageManager.Companion companion = AppNameLanguageManager.INSTANCE;
                if (music == null || (str = music.title) == null) {
                    str = "";
                }
                musicControlInfo.musicName = companion.convertChinese2Pinyin(str);
                MusicHelper.this.printAndSave("音乐信息 " + musicControlInfo);
            } else {
                musicControlInfo.musicName = " ";
                musicControlInfo.singerName = "";
            }
            if (BLEManager.isConnected() && supportFunctionInfo.bleControlMusic && LocalDataManager.getMusicSwitch() && (onMusicControlInfoChangedListener = this.mOnMusicControlInfoChangedListener) != null) {
                onMusicControlInfoChangedListener.onMusicControlInfoChanged(musicControlInfo);
            }
        }

        public final void reSetMusicInfo() {
            try {
                if (this.musicMsgBean != null) {
                    setMusicInfo(this.musicMsgBean, MusicHelper.playState);
                }
            } catch (Exception unused) {
                MusicHelper.this.printAndSave("重发失败：" + this.musicMsgBean + ", playState = " + MusicHelper.playState);
            }
        }

        private final void initMusicControlInfoDelay() {
            CoroutinesUtils.INSTANCE.debounce(1000L, new Function1<ProducerScope<? super MusicControlInfo>, Unit>() { // from class: com.ido.life.util.MusicHelper$MediaControllerCallback$initMusicControlInfoDelay$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ProducerScope<? super MusicControlInfo> producerScope) {
                    invoke2(producerScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final ProducerScope<? super MusicControlInfo> it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    this.this$0.setMOnMusicControlInfoChangedListener(new OnMusicControlInfoChangedListener() { // from class: com.ido.life.util.MusicHelper$MediaControllerCallback$initMusicControlInfoDelay$1.1
                        @Override // com.ido.life.util.OnMusicControlInfoChangedListener
                        public void onMusicControlInfoChanged(MusicControlInfo musicControlInfo) {
                            Intrinsics.checkParameterIsNotNull(musicControlInfo, "musicControlInfo");
                            it.offer(musicControlInfo);
                        }
                    });
                }
            }, (4 & 4) != 0 ? (Function0) null : null, (4 & 8) != 0 ? (Function1) null : new Function1<Throwable, Unit>() { // from class: com.ido.life.util.MusicHelper$MediaControllerCallback$initMusicControlInfoDelay$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    MusicHelper.this.logP("setMusicControlInfo error：" + it);
                }
            }, new Function1<MusicControlInfo, Unit>() { // from class: com.ido.life.util.MusicHelper$MediaControllerCallback$initMusicControlInfoDelay$3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(MusicControlInfo musicControlInfo) {
                    invoke2(musicControlInfo);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(MusicControlInfo it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    MusicHelper.this.printAndSave("发送音乐信息: " + it);
                    BLEManager.setMusicControlInfo(it);
                }
            });
        }

        public final OnMusicControlInfoChangedListener getMOnMusicControlInfoChangedListener() {
            return this.mOnMusicControlInfoChangedListener;
        }

        public final void setMOnMusicControlInfoChangedListener(OnMusicControlInfoChangedListener onMusicControlInfoChangedListener) {
            this.mOnMusicControlInfoChangedListener = onMusicControlInfoChangedListener;
        }
    }
}