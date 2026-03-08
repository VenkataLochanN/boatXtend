package com.amap.api.maps.model;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GL3DModelOptions {
    private BitmapDescriptor bitmapDescriptor;
    private int fixedLength;
    private LatLng latLng;
    private float rotate;
    private List<Float> vertextList = new ArrayList();
    private List<Float> textrueList = new ArrayList();

    public GL3DModelOptions textureDrawable(BitmapDescriptor bitmapDescriptor) {
        this.bitmapDescriptor = bitmapDescriptor;
        return this;
    }

    public GL3DModelOptions vertexData(List<Float> list, List<Float> list2) {
        this.vertextList = list;
        this.textrueList = list2;
        return this;
    }

    public GL3DModelOptions position(LatLng latLng) {
        this.latLng = latLng;
        return this;
    }

    public GL3DModelOptions angle(float f2) {
        this.rotate = f2;
        return this;
    }

    public List<Float> getVertext() {
        return this.vertextList;
    }

    public List<Float> getTextrue() {
        return this.textrueList;
    }

    public float getAngle() {
        return this.rotate;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public BitmapDescriptor getBitmapDescriptor() {
        return this.bitmapDescriptor;
    }

    public GL3DModelOptions setModelFixedLength(int i) {
        this.fixedLength = i;
        return this;
    }

    public int getModelFixedLength() {
        return this.fixedLength;
    }
}