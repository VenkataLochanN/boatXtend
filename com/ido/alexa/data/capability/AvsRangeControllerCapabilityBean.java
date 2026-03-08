package com.ido.alexa.data.capability;

/* JADX INFO: loaded from: classes2.dex */
public class AvsRangeControllerCapabilityBean extends AvsControllerBaseBean {
    private AvsConfigurationBean configuration;

    public AvsRangeControllerCapabilityBean(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    public AvsConfigurationBean getConfigurationBean() {
        return this.configuration;
    }

    public void setConfigurationBean(AvsConfigurationBean avsConfigurationBean) {
        this.configuration = avsConfigurationBean;
    }
}