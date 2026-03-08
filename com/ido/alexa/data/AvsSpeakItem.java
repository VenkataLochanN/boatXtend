package com.ido.alexa.data;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes2.dex */
public class AvsSpeakItem extends AvsItem {
    private byte[] mAudio;
    private String mCid;

    public AvsSpeakItem(String str, String str2, ByteArrayInputStream byteArrayInputStream) throws IOException {
        this(str, str2, IOUtils.toByteArray(byteArrayInputStream));
        byteArrayInputStream.close();
    }

    public AvsSpeakItem(String str, String str2, byte[] bArr) {
        super(str);
        this.mCid = str2;
        this.mAudio = bArr;
    }

    public String getCid() {
        return this.mCid;
    }

    public byte[] getAudio() {
        return this.mAudio;
    }
}