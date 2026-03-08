package com.ido.life.transmitter;

import kotlin.Metadata;

/* JADX INFO: compiled from: ITransmitter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, d2 = {"Lcom/ido/life/transmitter/ITransmitter;", "", "pause", "", "resume", "shutdown", "start", "app_release"}, k = 1, mv = {1, 1, 16})
public interface ITransmitter {
    void pause();

    void resume();

    void shutdown();

    void start();
}