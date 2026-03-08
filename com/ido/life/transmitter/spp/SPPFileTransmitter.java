package com.ido.life.transmitter.spp;

import com.ido.life.transmitter.Transmitter;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SPPFileTransmitter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00032\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/ido/life/transmitter/spp/SPPFileTransmitter;", "Lcom/ido/life/transmitter/Transmitter;", "()V", "Companion", "SingleInstanceHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SPPFileTransmitter extends Transmitter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final SPPFileTransmitter instance = SingleInstanceHolder.INSTANCE.getINSTANCE();

    public static final SPPFileTransmitter getInstance() {
        Companion companion = INSTANCE;
        return instance;
    }

    /* JADX INFO: compiled from: SPPFileTransmitter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/ido/life/transmitter/spp/SPPFileTransmitter$Companion;", "", "()V", "instance", "Lcom/ido/life/transmitter/spp/SPPFileTransmitter;", "instance$annotations", "getInstance", "()Lcom/ido/life/transmitter/spp/SPPFileTransmitter;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void instance$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SPPFileTransmitter getInstance() {
            return SPPFileTransmitter.instance;
        }
    }

    private SPPFileTransmitter() {
    }

    public /* synthetic */ SPPFileTransmitter(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: SPPFileTransmitter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/ido/life/transmitter/spp/SPPFileTransmitter$SingleInstanceHolder;", "", "()V", "INSTANCE", "Lcom/ido/life/transmitter/spp/SPPFileTransmitter;", "getINSTANCE", "()Lcom/ido/life/transmitter/spp/SPPFileTransmitter;", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final class SingleInstanceHolder {
        public static final SingleInstanceHolder INSTANCE = new SingleInstanceHolder();
        private static final SPPFileTransmitter INSTANCE = new SPPFileTransmitter(null);

        private SingleInstanceHolder() {
        }

        public final SPPFileTransmitter getINSTANCE() {
            return INSTANCE;
        }
    }
}