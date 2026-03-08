package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.gifdecoder.GifHeader;
import com.bumptech.glide.gifdecoder.GifHeaderParser;
import com.bumptech.glide.gifencoder.AnimatedGifEncoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.UnitTransformation;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.util.LogTime;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class GifResourceEncoder implements ResourceEncoder<GifDrawable> {
    private static final Factory FACTORY = new Factory();
    private static final String TAG = "GifEncoder";
    private final BitmapPool bitmapPool;
    private final Factory factory;
    private final GifDecoder.BitmapProvider provider;

    @Override // com.bumptech.glide.load.Encoder
    public String getId() {
        return "";
    }

    public GifResourceEncoder(BitmapPool bitmapPool) {
        this(bitmapPool, FACTORY);
    }

    GifResourceEncoder(BitmapPool bitmapPool, Factory factory) {
        this.bitmapPool = bitmapPool;
        this.provider = new GifBitmapProvider(bitmapPool);
        this.factory = factory;
    }

    @Override // com.bumptech.glide.load.Encoder
    public boolean encode(Resource<GifDrawable> resource, OutputStream outputStream) {
        long logTime = LogTime.getLogTime();
        GifDrawable gifDrawable = resource.get();
        Transformation<Bitmap> frameTransformation = gifDrawable.getFrameTransformation();
        if (frameTransformation instanceof UnitTransformation) {
            return writeDataDirect(gifDrawable.getData(), outputStream);
        }
        GifDecoder gifDecoderDecodeHeaders = decodeHeaders(gifDrawable.getData());
        AnimatedGifEncoder animatedGifEncoderBuildEncoder = this.factory.buildEncoder();
        if (!animatedGifEncoderBuildEncoder.start(outputStream)) {
            return false;
        }
        for (int i = 0; i < gifDecoderDecodeHeaders.getFrameCount(); i++) {
            Resource<Bitmap> transformedFrame = getTransformedFrame(gifDecoderDecodeHeaders.getNextFrame(), frameTransformation, gifDrawable);
            try {
                if (!animatedGifEncoderBuildEncoder.addFrame(transformedFrame.get())) {
                    return false;
                }
                animatedGifEncoderBuildEncoder.setDelay(gifDecoderDecodeHeaders.getDelay(gifDecoderDecodeHeaders.getCurrentFrameIndex()));
                gifDecoderDecodeHeaders.advance();
                transformedFrame.recycle();
            } finally {
                transformedFrame.recycle();
            }
        }
        boolean zFinish = animatedGifEncoderBuildEncoder.finish();
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "Encoded gif with " + gifDecoderDecodeHeaders.getFrameCount() + " frames and " + gifDrawable.getData().length + " bytes in " + LogTime.getElapsedMillis(logTime) + " ms");
        }
        return zFinish;
    }

    private boolean writeDataDirect(byte[] bArr, OutputStream outputStream) {
        try {
            outputStream.write(bArr);
            return true;
        } catch (IOException e2) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to write data to output stream in GifResourceEncoder", e2);
            }
            return false;
        }
    }

    private GifDecoder decodeHeaders(byte[] bArr) {
        GifHeaderParser gifHeaderParserBuildParser = this.factory.buildParser();
        gifHeaderParserBuildParser.setData(bArr);
        GifHeader header = gifHeaderParserBuildParser.parseHeader();
        GifDecoder gifDecoderBuildDecoder = this.factory.buildDecoder(this.provider);
        gifDecoderBuildDecoder.setData(header, bArr);
        gifDecoderBuildDecoder.advance();
        return gifDecoderBuildDecoder;
    }

    private Resource<Bitmap> getTransformedFrame(Bitmap bitmap, Transformation<Bitmap> transformation, GifDrawable gifDrawable) {
        Resource<Bitmap> resourceBuildFrameResource = this.factory.buildFrameResource(bitmap, this.bitmapPool);
        Resource<Bitmap> resourceTransform = transformation.transform(resourceBuildFrameResource, gifDrawable.getIntrinsicWidth(), gifDrawable.getIntrinsicHeight());
        if (!resourceBuildFrameResource.equals(resourceTransform)) {
            resourceBuildFrameResource.recycle();
        }
        return resourceTransform;
    }

    static class Factory {
        Factory() {
        }

        public GifDecoder buildDecoder(GifDecoder.BitmapProvider bitmapProvider) {
            return new GifDecoder(bitmapProvider);
        }

        public GifHeaderParser buildParser() {
            return new GifHeaderParser();
        }

        public AnimatedGifEncoder buildEncoder() {
            return new AnimatedGifEncoder();
        }

        public Resource<Bitmap> buildFrameResource(Bitmap bitmap, BitmapPool bitmapPool) {
            return new BitmapResource(bitmap, bitmapPool);
        }
    }
}