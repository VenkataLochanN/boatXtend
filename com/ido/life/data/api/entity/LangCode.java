package com.ido.life.data.api.entity;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class LangCode implements Serializable {
    protected String code;
    protected String description;
    protected int id;
    protected String name;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String toString() {
        return "LangCode{id=" + this.id + ", code='" + this.code + "', name='" + this.name + "', description=" + this.description + '}';
    }
}