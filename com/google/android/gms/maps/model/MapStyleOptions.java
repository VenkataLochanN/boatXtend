package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import com.bumptech.glide.load.Key;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.IOUtils;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class MapStyleOptions extends AbstractSafeParcelable {
    private String zzdk;
    private static final String TAG = MapStyleOptions.class.getSimpleName();
    public static final Parcelable.Creator<MapStyleOptions> CREATOR = new zzg();

    public MapStyleOptions(String str) {
        this.zzdk = str;
    }

    public static MapStyleOptions loadRawResourceStyle(Context context, int i) throws Resources.NotFoundException {
        try {
            return new MapStyleOptions(new String(IOUtils.readInputStreamFully(context.getResources().openRawResource(i)), Key.STRING_CHARSET_NAME));
        } catch (IOException e2) {
            String strValueOf = String.valueOf(e2);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 37);
            sb.append("Failed to read resource ");
            sb.append(i);
            sb.append(": ");
            sb.append(strValueOf);
            throw new Resources.NotFoundException(sb.toString());
        }
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzdk, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}