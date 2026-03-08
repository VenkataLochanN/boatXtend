package com.lzy.imagepicker.bean;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public class ImageItem implements Serializable, Parcelable {
    public static final Parcelable.Creator<ImageItem> CREATOR = new Parcelable.Creator<ImageItem>() { // from class: com.lzy.imagepicker.bean.ImageItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImageItem createFromParcel(Parcel parcel) {
            return new ImageItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImageItem[] newArray(int i) {
            return new ImageItem[i];
        }
    };
    public long addTime;
    public int height;
    public Uri mUri;
    public String mimeType;
    public String name;
    public String path;
    public long size;
    public int width;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected ImageItem(Parcel parcel) {
        this.name = parcel.readString();
        this.path = parcel.readString();
        this.size = parcel.readLong();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.mimeType = parcel.readString();
        this.addTime = parcel.readLong();
        this.mUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
    }

    public boolean equals(Object obj) {
        if (obj instanceof ImageItem) {
            ImageItem imageItem = (ImageItem) obj;
            return this.path.equalsIgnoreCase(imageItem.path) && this.addTime == imageItem.addTime;
        }
        return super.equals(obj);
    }

    public ImageItem() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.path);
        parcel.writeLong(this.size);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeString(this.mimeType);
        parcel.writeLong(this.addTime);
        parcel.writeParcelable(this.mUri, i);
    }

    public String toString() {
        return "ImageItem{name='" + this.name + "', path='" + this.path + "', size=" + this.size + ", width=" + this.width + ", height=" + this.height + ", mimeType='" + this.mimeType + "', addTime=" + this.addTime + ", mUri=" + this.mUri + '}';
    }
}