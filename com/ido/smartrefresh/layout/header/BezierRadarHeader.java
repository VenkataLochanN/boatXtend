package com.ido.smartrefresh.layout.header;

import android.content.Context;
import android.util.AttributeSet;
import com.ido.smartrefresh.layout.api.RefreshHeader;

/* JADX INFO: loaded from: classes3.dex */
public class BezierRadarHeader extends com.ido.smartrefresh.headerradar.BezierRadarHeader implements RefreshHeader {
    public BezierRadarHeader(Context context) {
        this(context, null);
    }

    public BezierRadarHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.ido.smartrefresh.headerradar.BezierRadarHeader
    public BezierRadarHeader setPrimaryColor(int i) {
        super.setPrimaryColor(i);
        return this;
    }

    @Override // com.ido.smartrefresh.headerradar.BezierRadarHeader
    public BezierRadarHeader setAccentColor(int i) {
        super.setAccentColor(i);
        return this;
    }

    @Override // com.ido.smartrefresh.headerradar.BezierRadarHeader
    public BezierRadarHeader setPrimaryColorId(int i) {
        super.setPrimaryColorId(i);
        return this;
    }

    @Override // com.ido.smartrefresh.headerradar.BezierRadarHeader
    public BezierRadarHeader setAccentColorId(int i) {
        super.setAccentColorId(i);
        return this;
    }

    @Override // com.ido.smartrefresh.headerradar.BezierRadarHeader
    public BezierRadarHeader setEnableHorizontalDrag(boolean z) {
        super.setEnableHorizontalDrag(z);
        return this;
    }
}