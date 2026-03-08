package com.bumptech.glide.load.resource.file;

import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class FileToStreamDecoder<T> implements ResourceDecoder<File, T> {
    private static final FileOpener DEFAULT_FILE_OPENER = new FileOpener();
    private final FileOpener fileOpener;
    private ResourceDecoder<InputStream, T> streamDecoder;

    @Override // com.bumptech.glide.load.ResourceDecoder
    public String getId() {
        return "";
    }

    public FileToStreamDecoder(ResourceDecoder<InputStream, T> resourceDecoder) {
        this(resourceDecoder, DEFAULT_FILE_OPENER);
    }

    FileToStreamDecoder(ResourceDecoder<InputStream, T> resourceDecoder, FileOpener fileOpener) {
        this.streamDecoder = resourceDecoder;
        this.fileOpener = fileOpener;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public Resource<T> decode(File file, int i, int i2) throws IOException {
        InputStream inputStreamOpen = null;
        try {
            inputStreamOpen = this.fileOpener.open(file);
            Resource<T> resourceDecode = this.streamDecoder.decode(inputStreamOpen, i, i2);
            if (inputStreamOpen != null) {
                try {
                    inputStreamOpen.close();
                } catch (IOException unused) {
                }
            }
            return resourceDecode;
        } catch (Throwable th) {
            if (inputStreamOpen != null) {
                try {
                    inputStreamOpen.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    static class FileOpener {
        FileOpener() {
        }

        public InputStream open(File file) throws FileNotFoundException {
            return new FileInputStream(file);
        }
    }
}