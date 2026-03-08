package com.ido.alexa.data.capability;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AvsControllerBaseBean extends AvsCapabilityBaseBean {
    private CapabilityResourcesBean capabilityResources;
    private String instance;
    private AvsPropertiesBean properties;
    private AvsSemanticsBean semantics;

    public AvsControllerBaseBean(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    public void setInstance(String str) {
        this.instance = str;
    }

    public void setProperties(AvsPropertiesBean avsPropertiesBean) {
        this.properties = avsPropertiesBean;
    }

    public void setSemantics(AvsSemanticsBean avsSemanticsBean) {
        this.semantics = avsSemanticsBean;
    }

    public void setCapabilityResources(CapabilityResourcesBean capabilityResourcesBean) {
        this.capabilityResources = capabilityResourcesBean;
    }

    public static class CapabilityResourcesBean {
        private List<AvsFriendlyNamesBean> friendlyNames;

        public void setFriendlyNames(List<AvsFriendlyNamesBean> list) {
            this.friendlyNames = list;
        }
    }
}