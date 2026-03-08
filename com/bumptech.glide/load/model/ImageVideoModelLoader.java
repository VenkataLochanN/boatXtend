package com.bumptech.glide.load.model;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class ImageVideoModelLoader<A> implements ModelLoader<A, ImageVideoWrapper> {
    private static final String TAG = "IVML";
    private final ModelLoader<A, ParcelFileDescriptor> fileDescriptorLoader;
    private final ModelLoader<A, InputStream> streamLoader;

    public ImageVideoModelLoader(ModelLoader<A, InputStream> modelLoader, ModelLoader<A, ParcelFileDescriptor> modelLoader2) {
        if (modelLoader == null && modelLoader2 == null) {
            throw new NullPointerException("At least one of streamLoader and fileDescriptorLoader must be non null");
        }
        this.streamLoader = modelLoader;
        this.fileDescriptorLoader = modelLoader2;
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public DataFetcher<ImageVideoWrapper> getResourceFetcher(A a2, int i, int i2) {
        ModelLoader<A, InputStream> modelLoader = this.streamLoader;
        DataFetcher<InputStream> resourceFetcher = modelLoader != null ? modelLoader.getResourceFetcher(a2, i, i2) : null;
        ModelLoader<A, ParcelFileDescriptor> modelLoader2 = this.fileDescriptorLoader;
        DataFetcher<ParcelFileDescriptor> resourceFetcher2 = modelLoader2 != null ? modelLoader2.getResourceFetcher(a2, i, i2) : null;
        if (resourceFetcher == null && resourceFetcher2 == null) {
            return null;
        }
        return new ImageVideoFetcher(resourceFetcher, resourceFetcher2);
    }

    static class ImageVideoFetcher implements DataFetcher<ImageVideoWrapper> {
        private final DataFetcher<ParcelFileDescriptor> fileDescriptorFetcher;
        private final DataFetcher<InputStream> streamFetcher;

        public ImageVideoFetcher(DataFetcher<InputStream> dataFetcher, DataFetcher<ParcelFileDescriptor> dataFetcher2) {
            this.streamFetcher = dataFetcher;
            this.fileDescriptorFetcher = dataFetcher2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bumptech.glide.load.data.DataFetcher
        public ImageVideoWrapper loadData(Priority priority) throws Exception {
            InputStream inputStreamLoadData;
            ParcelFileDescriptor parcelFileDescriptorLoadData;
            DataFetcher<InputStream> dataFetcher = this.streamFetcher;
            if (dataFetcher != null) {
                try {
                    inputStreamLoadData = dataFetcher.loadData(priority);
                } catch (Exception e2) {
                    if (Log.isLoggable(ImageVideoModelLoader.TAG, 2)) {
                        Log.v(ImageVideoModelLoader.TAG, "Exception fetching input stream, trying ParcelFileDescriptor", e2);
                    }
                    if (this.fileDescriptorFetcher == null) {
                        throw e2;
                    }
                    inputStreamLoadData = null;
                }
            } else {
                inputStreamLoadData = null;
            }
            DataFetcher<ParcelFileDescriptor> dataFetcher2 = this.fileDescriptorFetcher;
            if (dataFetcher2 != null) {
                try {
                    parcelFileDescriptorLoadData = dataFetcher2.loadData(priority);
                } catch (Exception e3) {
                    if (Log.isLoggable(ImageVideoModelLoader.TAG, 2)) {
                        Log.v(ImageVideoModelLoader.TAG, "Exception fetching ParcelFileDescriptor", e3);
                    }
                    if (inputStreamLoadData == null) {
                        throw e3;
                    }
                    parcelFileDescriptorLoadData = null;
                }
            } else {
                parcelFileDescriptorLoadData = null;
            }
            return new ImageVideoWrapper(inputStreamLoadData, parcelFileDescriptorLoadData);
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cleanup() {
            DataFetcher<InputStream> dataFetcher = this.streamFetcher;
            if (dataFetcher != null) {
                dataFetcher.cleanup();
            }
            DataFetcher<ParcelFileDescriptor> dataFetcher2 = this.fileDescriptorFetcher;
            if (dataFetcher2 != null) {
                dataFetcher2.cleanup();
            }
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public String getId() {
            DataFetcher<InputStream> dataFetcher = this.streamFetcher;
            if (dataFetcher != null) {
                return dataFetcher.getId();
            }
            return this.fileDescriptorFetcher.getId();
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cancel() {
            DataFetcher<InputStream> dataFetcher = this.streamFetcher;
            if (dataFetcher != null) {
                dataFetcher.cancel();
            }
            DataFetcher<ParcelFileDescriptor> dataFetcher2 = this.fileDescriptorFetcher;
            if (dataFetcher2 != null) {
                dataFetcher2.cancel();
            }
        }
    }
}