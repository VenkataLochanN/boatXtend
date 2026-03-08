package com.ido.alexa.data.capability;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AvsEndpoint {
    private AdditionalAttributesABean additionalAttributes;
    private List<AvsCapabilityBaseBean> capabilities;
    private List<ConnectionsBean> connections;
    private String description;
    private List<String> displayCategories;
    private String endpointId;
    private String friendlyName;
    private String manufacturerName;
    private RegistrationBean registration;

    public AdditionalAttributesABean getAdditionalAttributes() {
        return this.additionalAttributes;
    }

    public void setAdditionalAttributes(AdditionalAttributesABean additionalAttributesABean) {
        this.additionalAttributes = additionalAttributesABean;
    }

    public List<ConnectionsBean> getConnections() {
        return this.connections;
    }

    public void setConnections(List<ConnectionsBean> list) {
        this.connections = list;
    }

    public String getEndpointId() {
        return this.endpointId;
    }

    public void setEndpointId(String str) {
        this.endpointId = str;
    }

    public String getFriendlyName() {
        return this.friendlyName;
    }

    public void setFriendlyName(String str) {
        this.friendlyName = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getManufacturerName() {
        return this.manufacturerName;
    }

    public void setManufacturerName(String str) {
        this.manufacturerName = str;
    }

    public RegistrationBean getRegistration() {
        return this.registration;
    }

    public void setRegistration(RegistrationBean registrationBean) {
        this.registration = registrationBean;
    }

    public List<String> getDisplayCategories() {
        return this.displayCategories;
    }

    public void setDisplayCategories(List<String> list) {
        this.displayCategories = list;
    }

    public List<AvsCapabilityBaseBean> getCapabilities() {
        return this.capabilities;
    }

    public void setCapabilities(List<AvsCapabilityBaseBean> list) {
        this.capabilities = list;
    }

    public static class RegistrationBean {
        private String deviceSerialNumber;
        private String productId;

        public String getProductId() {
            return this.productId;
        }

        public void setProductId(String str) {
            this.productId = str;
        }

        public String getDeviceSerialNumber() {
            return this.deviceSerialNumber;
        }

        public void setDeviceSerialNumber(String str) {
            this.deviceSerialNumber = str;
        }
    }

    public static class ConnectionsBean {
        private String homeId;
        private String macAddress;
        private String nodeId;
        private String type;
        private String value;

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String getMacAddress() {
            return this.macAddress;
        }

        public void setMacAddress(String str) {
            this.macAddress = str;
        }

        public String getHomeId() {
            return this.homeId;
        }

        public void setHomeId(String str) {
            this.homeId = str;
        }

        public String getNodeId() {
            return this.nodeId;
        }

        public void setNodeId(String str) {
            this.nodeId = str;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String str) {
            this.value = str;
        }
    }

    public static class AdditionalAttributesABean {
        private String customIdentifier;
        private String firmwareVersion;
        private String manufacturer;
        private String model;
        private String serialNumber;
        private String softwareVersion;

        public String getManufacturer() {
            return this.manufacturer;
        }

        public void setManufacturer(String str) {
            this.manufacturer = str;
        }

        public String getModel() {
            return this.model;
        }

        public void setModel(String str) {
            this.model = str;
        }

        public String getSerialNumber() {
            return this.serialNumber;
        }

        public void setSerialNumber(String str) {
            this.serialNumber = str;
        }

        public String getFirmwareVersion() {
            return this.firmwareVersion;
        }

        public void setFirmwareVersion(String str) {
            this.firmwareVersion = str;
        }

        public String getSoftwareVersion() {
            return this.softwareVersion;
        }

        public void setSoftwareVersion(String str) {
            this.softwareVersion = str;
        }

        public String getCustomIdentifier() {
            return this.customIdentifier;
        }

        public void setCustomIdentifier(String str) {
            this.customIdentifier = str;
        }
    }
}