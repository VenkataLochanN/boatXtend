package com.ido.life.base;

/* JADX INFO: loaded from: classes2.dex */
public class BaseMessage<T> {
    private T data;
    private int type;

    public BaseMessage(int i, T t) {
        this.type = i;
        this.data = t;
    }

    public BaseMessage(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public String toString() {
        return "BaseMessage{type=" + this.type + ", data=" + this.data + '}';
    }
}