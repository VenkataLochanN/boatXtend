package com.lzy.imagepicker.bean;

import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class ImageFolder implements Serializable {
    public ImageItem cover;
    public ArrayList<ImageItem> images;
    public String name;
    public String path;

    public boolean equals(Object obj) {
        try {
            ImageFolder imageFolder = (ImageFolder) obj;
            if (this.path.equalsIgnoreCase(imageFolder.path)) {
                if (this.name.equalsIgnoreCase(imageFolder.name)) {
                    return true;
                }
            }
            return false;
        } catch (ClassCastException e2) {
            e2.printStackTrace();
            return super.equals(obj);
        }
    }
}