package com.ido.alexa.data;

import com.ido.alexa.data.Directive;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes2.dex */
public class AvsEnqueuedItem extends AvsItem {
    ByteArrayOutputStream audioData;
    private Directive.Payload mPayLoad;

    public AvsEnqueuedItem(String str, Directive.Payload payload) {
        super(str);
        this.mPayLoad = payload;
        this.audioData = new ByteArrayOutputStream();
    }

    public AvsEnqueuedItem(String str, Directive.Payload payload, ByteArrayInputStream byteArrayInputStream) {
        super(str);
        this.mPayLoad = payload;
        this.audioData = new ByteArrayOutputStream();
        if (byteArrayInputStream == null) {
            return;
        }
        try {
            this.audioData.write(IOUtils.toByteArray(byteArrayInputStream));
            byteArrayInputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void appendAudio(ByteArrayInputStream byteArrayInputStream) {
        if (byteArrayInputStream == null) {
            return;
        }
        if (this.audioData == null) {
            this.audioData = new ByteArrayOutputStream();
        }
        try {
            this.audioData.write(IOUtils.toByteArray(byteArrayInputStream));
            byteArrayInputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void appendAudio(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        if (this.audioData == null) {
            this.audioData = new ByteArrayOutputStream();
        }
        try {
            this.audioData.write(bArr);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public Directive.Payload getPayLoad() {
        return this.mPayLoad;
    }

    public void setPayLoad(Directive.Payload payload) {
        this.mPayLoad = payload;
    }

    public boolean isWeatherType() {
        return this.mPayLoad.getType().equals(Directive.TYPE_WATHER_TEMPLATE);
    }

    public boolean isBodyType() {
        return this.mPayLoad.getType().contains(Directive.TYPE_BODY_TEMPLATE);
    }

    public byte[] getAudioData() {
        return this.audioData.toByteArray();
    }
}