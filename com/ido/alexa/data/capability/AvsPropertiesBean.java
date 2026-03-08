package com.ido.alexa.data.capability;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AvsPropertiesBean {
    private boolean nonControllable;
    private boolean proactivelyReported;
    private boolean retrievable;
    private List<SupportedBean> supported;

    public AvsPropertiesBean(boolean z, boolean z2, boolean z3, List<SupportedBean> list) {
        this.proactivelyReported = z;
        this.retrievable = z2;
        this.nonControllable = z3;
        this.supported = list;
    }

    public static class SupportedBean {
        private String name;

        public SupportedBean(String str) {
            this.name = str;
        }
    }
}