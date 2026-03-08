package com.ido.life.transmitter;

import com.ido.ble.BLEManager;
import com.ido.life.ble.BaseConnCallback;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FileTransmitter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u0004\u0018\u0000 \n2\u00020\u0001:\u0002\n\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\u0007H\u0016R\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\f"}, d2 = {"Lcom/ido/life/transmitter/FileTransmitter;", "Lcom/ido/life/transmitter/Transmitter;", "()V", "mConnectCallback", "com/ido/life/transmitter/FileTransmitter$mConnectCallback$1", "Lcom/ido/life/transmitter/FileTransmitter$mConnectCallback$1;", "registerConnectCallback", "", "shutdown", "start", "Companion", "SingleInstanceHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class FileTransmitter extends Transmitter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final FileTransmitter instance = SingleInstanceHolder.INSTANCE.getINSTANCE();
    private final FileTransmitter$mConnectCallback$1 mConnectCallback;

    public static final FileTransmitter getInstance() {
        Companion companion = INSTANCE;
        return instance;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.ido.life.transmitter.FileTransmitter$mConnectCallback$1] */
    private FileTransmitter() {
        this.mConnectCallback = new BaseConnCallback() { // from class: com.ido.life.transmitter.FileTransmitter$mConnectCallback$1
            @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onConnectSuccess(String macAddress) {
                super.onConnectSuccess(macAddress);
                FileTransmitter fileTransmitter = this.this$0;
                StringBuilder sb = new StringBuilder();
                sb.append("The device connect succeed, the transmitter is ");
                sb.append(this.this$0.isStarted() ? "started" : "shutdown");
                sb.append(" now!!!");
                fileTransmitter.printAndSave(sb.toString());
            }

            @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onConnectBreak(String macAddress) {
                super.onConnectBreak(macAddress);
                FileTransmitter fileTransmitter = this.this$0;
                StringBuilder sb = new StringBuilder();
                sb.append("The device connect break, the transmitter is ");
                sb.append(this.this$0.isStarted() ? "started" : "shutdown");
                sb.append(" and it's ");
                sb.append(this.this$0.isTransmitting() ? "transmitting" : "idle");
                sb.append(" now!!!");
                fileTransmitter.printAndSave(sb.toString());
                this.this$0.shutdownUnexpected();
            }
        };
    }

    public /* synthetic */ FileTransmitter(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: FileTransmitter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/ido/life/transmitter/FileTransmitter$Companion;", "", "()V", "instance", "Lcom/ido/life/transmitter/FileTransmitter;", "instance$annotations", "getInstance", "()Lcom/ido/life/transmitter/FileTransmitter;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void instance$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FileTransmitter getInstance() {
            return FileTransmitter.instance;
        }
    }

    /* JADX INFO: compiled from: FileTransmitter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/ido/life/transmitter/FileTransmitter$SingleInstanceHolder;", "", "()V", "INSTANCE", "Lcom/ido/life/transmitter/FileTransmitter;", "getINSTANCE", "()Lcom/ido/life/transmitter/FileTransmitter;", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final class SingleInstanceHolder {
        public static final SingleInstanceHolder INSTANCE = new SingleInstanceHolder();
        private static final FileTransmitter INSTANCE = new FileTransmitter(null);

        private SingleInstanceHolder() {
        }

        public final FileTransmitter getINSTANCE() {
            return INSTANCE;
        }
    }

    @Override // com.ido.life.transmitter.Transmitter, com.ido.life.transmitter.ITransmitter
    public void start() {
        super.start();
        registerConnectCallback();
    }

    @Override // com.ido.life.transmitter.Transmitter, com.ido.life.transmitter.ITransmitter
    public void shutdown() {
        BLEManager.unregisterConnectCallBack(this.mConnectCallback);
        super.shutdown();
    }

    private final void registerConnectCallback() {
        BLEManager.unregisterConnectCallBack(this.mConnectCallback);
        BLEManager.registerConnectCallBack(this.mConnectCallback);
    }
}