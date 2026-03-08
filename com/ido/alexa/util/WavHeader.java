package com.ido.alexa.util;

import com.autonavi.base.amap.mapcore.AeUtil;

/* JADX INFO: loaded from: classes2.dex */
public class WavHeader {
    short blockAlign;
    int byteRate;
    short channels;
    int dataChunkSize;
    int riffChunkSize;
    short sampleBits;
    int sampleRate;
    final String riffChunkId = "RIFF";
    final String riffType = "WAVE";
    final String formatChunkId = "fmt ";
    final int formatChunkSize = 16;
    final short audioFormat = 1;
    final String dataChunkId = AeUtil.ROOT_DATA_PATH_OLD_NAME;

    public WavHeader(int i, int i2, short s, short s2) {
        this.riffChunkSize = i;
        this.channels = s;
        this.sampleRate = i2;
        this.byteRate = ((i2 * s2) / 8) * s;
        this.blockAlign = (short) ((s * s2) / 8);
        this.sampleBits = s2;
        this.dataChunkSize = i - 44;
    }

    public byte[] getHeader() {
        return ByteUtils.merger(ByteUtils.merger(ByteUtils.merger(ByteUtils.merger(ByteUtils.merger(ByteUtils.merger(ByteUtils.merger(ByteUtils.merger(ByteUtils.merger(ByteUtils.merger(ByteUtils.merger(ByteUtils.merger(ByteUtils.toBytes("RIFF"), ByteUtils.toBytes(this.riffChunkSize)), ByteUtils.toBytes("WAVE")), ByteUtils.toBytes("fmt ")), ByteUtils.toBytes(16)), ByteUtils.toBytes((short) 1)), ByteUtils.toBytes(this.channels)), ByteUtils.toBytes(this.sampleRate)), ByteUtils.toBytes(this.byteRate)), ByteUtils.toBytes(this.blockAlign)), ByteUtils.toBytes(this.sampleBits)), ByteUtils.toBytes(AeUtil.ROOT_DATA_PATH_OLD_NAME)), ByteUtils.toBytes(this.dataChunkSize));
    }
}