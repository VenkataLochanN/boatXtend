package com.baidu.mapapi.map;

/* JADX INFO: loaded from: classes.dex */
public class MyLocationData {
    public final float accuracy;
    public final float direction;
    public final double latitude;
    public final double longitude;
    public final int satellitesNum;
    public final float speed;

    public static class Builder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private double f2928a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private double f2929b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f2930c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private float f2931d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private float f2932e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f2933f;

        public Builder accuracy(float f2) {
            this.f2932e = f2;
            return this;
        }

        public MyLocationData build() {
            return new MyLocationData(this.f2928a, this.f2929b, this.f2930c, this.f2931d, this.f2932e, this.f2933f);
        }

        public Builder direction(float f2) {
            this.f2931d = f2;
            return this;
        }

        public Builder latitude(double d2) {
            this.f2928a = d2;
            return this;
        }

        public Builder longitude(double d2) {
            this.f2929b = d2;
            return this;
        }

        public Builder satellitesNum(int i) {
            this.f2933f = i;
            return this;
        }

        public Builder speed(float f2) {
            this.f2930c = f2;
            return this;
        }
    }

    MyLocationData(double d2, double d3, float f2, float f3, float f4, int i) {
        this.latitude = d2;
        this.longitude = d3;
        this.speed = f2;
        this.direction = f3;
        this.accuracy = f4;
        this.satellitesNum = i;
    }
}