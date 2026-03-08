package com.ido.life.transmitter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: ModuleType.kt */
/* JADX INFO: loaded from: classes3.dex */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0087\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lcom/ido/life/transmitter/ModuleType;", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION})
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleType {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final int TYPE_DIAL = 8;
    public static final int TYPE_LANGUAGE_PACK = 10;
    public static final int TYPE_MSG = 1;
    public static final int TYPE_MUSIC = 6;
    public static final int TYPE_OTA_UPGRADE = 9;
    public static final int TYPE_SPORT_ANIMATION = 4;
    public static final int TYPE_SPORT_BIG = 3;
    public static final int TYPE_SPORT_MIDDLE = 5;
    public static final int TYPE_SPORT_SMALL = 2;
    public static final int TYPE_WALLPAPER_DIAL = 7;

    /* JADX INFO: compiled from: ModuleType.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/ido/life/transmitter/ModuleType$Companion;", "", "()V", "GROUP_TYPE_MOTION", "", "", "getGROUP_TYPE_MOTION", "()Ljava/util/List;", "TYPE_DIAL", "TYPE_LANGUAGE_PACK", "TYPE_MSG", "TYPE_MUSIC", "TYPE_OTA_UPGRADE", "TYPE_SPORT_ANIMATION", "TYPE_SPORT_BIG", "TYPE_SPORT_MIDDLE", "TYPE_SPORT_SMALL", "TYPE_WALLPAPER_DIAL", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final List<Integer> GROUP_TYPE_MOTION = CollectionsKt.mutableListOf(4, 2, 5, 3);
        public static final int TYPE_DIAL = 8;
        public static final int TYPE_LANGUAGE_PACK = 10;
        public static final int TYPE_MSG = 1;
        public static final int TYPE_MUSIC = 6;
        public static final int TYPE_OTA_UPGRADE = 9;
        public static final int TYPE_SPORT_ANIMATION = 4;
        public static final int TYPE_SPORT_BIG = 3;
        public static final int TYPE_SPORT_MIDDLE = 5;
        public static final int TYPE_SPORT_SMALL = 2;
        public static final int TYPE_WALLPAPER_DIAL = 7;

        private Companion() {
        }

        public final List<Integer> getGROUP_TYPE_MOTION() {
            return GROUP_TYPE_MOTION;
        }
    }
}