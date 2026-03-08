package com.ido.alexa.data.capability;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AvsConfigurationBean {
    private List<PresetsBean> presets;
    private SupportedRangeBean supportedRange;

    public SupportedRangeBean getSupportedRange() {
        return this.supportedRange;
    }

    public void setSupportedRange(SupportedRangeBean supportedRangeBean) {
        this.supportedRange = supportedRangeBean;
    }

    public List<PresetsBean> getPresets() {
        return this.presets;
    }

    public void setPresets(List<PresetsBean> list) {
        this.presets = list;
    }

    public static class SupportedRangeBean {
        private int maximumValue;
        private int minimumValue;
        private int precision;

        public int getMinimumValue() {
            return this.minimumValue;
        }

        public void setMinimumValue(int i) {
            this.minimumValue = i;
        }

        public int getMaximumValue() {
            return this.maximumValue;
        }

        public void setMaximumValue(int i) {
            this.maximumValue = i;
        }

        public int getPrecision() {
            return this.precision;
        }

        public void setPrecision(int i) {
            this.precision = i;
        }
    }

    public static class PresetsBean {
        private PresetResourcesBean presetResources;
        private int rangeValue;

        public int getRangeValue() {
            return this.rangeValue;
        }

        public void setRangeValue(int i) {
            this.rangeValue = i;
        }

        public PresetResourcesBean getPresetResources() {
            return this.presetResources;
        }

        public void setPresetResources(PresetResourcesBean presetResourcesBean) {
            this.presetResources = presetResourcesBean;
        }

        public static class PresetResourcesBean {
            private List<AvsFriendlyNamesBean> friendlyNames;

            public List<AvsFriendlyNamesBean> getFriendlyNames() {
                return this.friendlyNames;
            }

            public void setFriendlyNames(List<AvsFriendlyNamesBean> list) {
                this.friendlyNames = list;
            }
        }
    }
}