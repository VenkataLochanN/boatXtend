package com.ido.alexa.data.capability;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AvsSystemCapabilityBean extends AvsCapabilityBaseBean {
    private ConfigurationsBean configurations;

    public ConfigurationsBean getConfigurations() {
        return this.configurations;
    }

    public void setConfigurations(ConfigurationsBean configurationsBean) {
        this.configurations = configurationsBean;
    }

    public AvsSystemCapabilityBean(String str, String str2, String str3, ConfigurationsBean configurationsBean) {
        super(str, str2, str3);
        setConfigurations(configurationsBean);
    }

    public static class ConfigurationsBean {
        private List<String> locales;

        public void setLocales(List<String> list) {
            this.locales = list;
        }
    }
}