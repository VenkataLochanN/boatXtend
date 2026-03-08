package com.ido.alexa.data.capability;

/* JADX INFO: loaded from: classes2.dex */
public class AvsAlertsCapabilityBean extends AvsCapabilityBaseBean {
    private ConfigurationsBean configurations;

    public ConfigurationsBean getConfigurations() {
        return this.configurations;
    }

    public void setConfigurations(ConfigurationsBean configurationsBean) {
        this.configurations = configurationsBean;
    }

    public AvsAlertsCapabilityBean(String str, String str2, String str3, ConfigurationsBean configurationsBean) {
        super(str, str2, str3);
        setConfigurations(configurationsBean);
    }

    public static class ConfigurationsBean {
        private MaximumAlertsBean maximumAlerts;

        public void setMaximumAlerts(MaximumAlertsBean maximumAlertsBean) {
            this.maximumAlerts = maximumAlertsBean;
        }

        public static class MaximumAlertsBean {
            private int alarms;
            private int overall;
            private int timers;

            public MaximumAlertsBean(int i, int i2, int i3) {
                this.overall = i;
                this.alarms = i2;
                this.timers = i3;
            }
        }
    }
}