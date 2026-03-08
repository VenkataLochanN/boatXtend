package com.ido.alexa.requestbody;

import com.autonavi.amap.mapcore.tools.GlMapUtil;
import no.nordicsemi.android.dfu.DfuBaseService;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/* JADX INFO: loaded from: classes2.dex */
public class ChunkRequestBody extends RequestBody {
    int SEGMENT_SIZE = GlMapUtil.DEVICE_DISPLAY_DPI_HIGH;
    private byte[] content;

    public ChunkRequestBody(byte[] bArr) {
        this.content = bArr;
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return MediaType.parse(DfuBaseService.MIME_TYPE_OCTET_STREAM);
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) {
        byte[] bArr = this.content;
        int length = bArr.length;
        int i = this.SEGMENT_SIZE;
        int i2 = length / i;
        int length2 = bArr.length % i;
        int i3 = 0;
        int i4 = i2 + (length2 != 0 ? 1 : 0);
        int i5 = 0;
        while (i3 < i4) {
            int length3 = i3 != i4 + (-1) ? this.SEGMENT_SIZE : this.content.length - i5;
            bufferedSink.buffer().write(this.content, i5, length3);
            bufferedSink.buffer().flush();
            i5 += length3;
            i3++;
        }
    }
}