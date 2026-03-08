package com.watch.life.wheelview.adapter;

/* JADX INFO: loaded from: classes3.dex */
public class NumericWheelAdapter implements WheelAdapter {
    private boolean isLoop = false;
    private int maxValue;
    private int minValue;

    public void setLoop(boolean z) {
        this.isLoop = z;
    }

    public NumericWheelAdapter(int i, int i2) {
        this.minValue = i;
        this.maxValue = i2;
    }

    @Override // com.watch.life.wheelview.adapter.WheelAdapter
    public Object getItem(int i) {
        if (i >= 0 && i < getItemsCount()) {
            int i2 = this.minValue + i;
            if (this.isLoop) {
                int i3 = i2 % 12;
                return Integer.valueOf(i3 != 0 ? i3 : 12);
            }
            return Integer.valueOf(i2);
        }
        return 0;
    }

    @Override // com.watch.life.wheelview.adapter.WheelAdapter
    public int getItemsCount() {
        return (this.maxValue - this.minValue) + 1;
    }

    @Override // com.watch.life.wheelview.adapter.WheelAdapter
    public int indexOf(Object obj) {
        try {
            return ((Integer) obj).intValue() - this.minValue;
        } catch (Exception unused) {
            return -1;
        }
    }
}