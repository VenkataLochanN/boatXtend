package com.ido.alexa.requestbody;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import no.nordicsemi.android.dfu.DfuBaseService;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Pipe;

/* JADX INFO: loaded from: classes2.dex */
public class PipeBody extends RequestBody {
    private final Pipe pipe = new Pipe(PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED);
    private final BufferedSink sink = Okio.buffer(this.pipe.sink());

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return MediaType.parse(DfuBaseService.MIME_TYPE_OCTET_STREAM);
    }

    public BufferedSink sink() {
        return this.sink;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        bufferedSink.writeAll(this.pipe.source());
    }
}