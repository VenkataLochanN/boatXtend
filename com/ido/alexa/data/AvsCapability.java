package com.ido.alexa.data;

import com.ido.alexa.data.capability.AvsCapabilityBaseBean;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AvsCapability {
    private List<AvsCapabilityBaseBean> capabilities;
    private String envelopeVersion;

    public String getEnvelopeVersion() {
        return this.envelopeVersion;
    }

    public void setEnvelopeVersion(String str) {
        this.envelopeVersion = str;
    }

    public List<AvsCapabilityBaseBean> getCapabilities() {
        return this.capabilities;
    }

    public void setCapabilities(List<AvsCapabilityBaseBean> list) {
        this.capabilities = list;
    }
}