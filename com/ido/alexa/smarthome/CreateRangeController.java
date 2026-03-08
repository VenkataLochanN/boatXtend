package com.ido.alexa.smarthome;

import android.text.TextUtils;
import com.ido.alexa.AlexaConstant;
import com.ido.alexa.data.capability.AvsConfigurationBean;
import com.ido.alexa.data.capability.AvsControllerBaseBean;
import com.ido.alexa.data.capability.AvsFriendlyNamesBean;
import com.ido.alexa.data.capability.AvsPropertiesBean;
import com.ido.alexa.data.capability.AvsRangeControllerCapabilityBean;
import com.ido.life.constants.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CreateRangeController {
    public static AvsRangeControllerCapabilityBean create(String str, List<AvsFriendlyNamesBean.ValueBean> list) {
        AvsRangeControllerCapabilityBean avsRangeControllerCapabilityBean = new AvsRangeControllerCapabilityBean("AlexaInterface", "Alexa.RangeController", Constants.DIALDEFNED_VERSION_CONNECT);
        avsRangeControllerCapabilityBean.setInstance(str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new AvsPropertiesBean.SupportedBean("rangeValue"));
        avsRangeControllerCapabilityBean.setProperties(new AvsPropertiesBean(false, true, true, arrayList));
        AvsConfigurationBean avsConfigurationBean = new AvsConfigurationBean();
        avsRangeControllerCapabilityBean.setConfigurationBean(avsConfigurationBean);
        AvsConfigurationBean.SupportedRangeBean supportedRangeBean = new AvsConfigurationBean.SupportedRangeBean();
        supportedRangeBean.setMinimumValue(0);
        supportedRangeBean.setMaximumValue(100);
        supportedRangeBean.setPrecision(1);
        avsConfigurationBean.setSupportedRange(supportedRangeBean);
        AvsControllerBaseBean.CapabilityResourcesBean capabilityResourcesBean = new AvsControllerBaseBean.CapabilityResourcesBean();
        ArrayList arrayList2 = new ArrayList();
        if (list != null) {
            ArrayList arrayList3 = new ArrayList();
            for (AvsFriendlyNamesBean.ValueBean valueBean : list) {
                if (TextUtils.equals(AlexaConstant.LANGUAGE_ES_ES, valueBean.getLocale())) {
                    arrayList3.add(valueBean.getText());
                }
                arrayList2.add(new AvsFriendlyNamesBean("text", valueBean));
            }
            Iterator it = arrayList3.iterator();
            while (it.hasNext()) {
                arrayList2.add(createFriendlyNames(AlexaConstant.LANGUAGE_ES_US, (String) it.next()));
            }
        }
        capabilityResourcesBean.setFriendlyNames(arrayList2);
        avsRangeControllerCapabilityBean.setCapabilityResources(capabilityResourcesBean);
        return avsRangeControllerCapabilityBean;
    }

    public static AvsRangeControllerCapabilityBean createBrightness(String str, List<AvsFriendlyNamesBean.ValueBean> list, int i) {
        AvsRangeControllerCapabilityBean avsRangeControllerCapabilityBean = new AvsRangeControllerCapabilityBean("AlexaInterface", "Alexa.RangeController", Constants.DIALDEFNED_VERSION_CONNECT);
        avsRangeControllerCapabilityBean.setInstance(str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new AvsPropertiesBean.SupportedBean("rangeValue"));
        avsRangeControllerCapabilityBean.setProperties(new AvsPropertiesBean(false, true, false, arrayList));
        AvsConfigurationBean avsConfigurationBean = new AvsConfigurationBean();
        avsRangeControllerCapabilityBean.setConfigurationBean(avsConfigurationBean);
        AvsConfigurationBean.SupportedRangeBean supportedRangeBean = new AvsConfigurationBean.SupportedRangeBean();
        supportedRangeBean.setMinimumValue(1);
        supportedRangeBean.setMaximumValue(i);
        supportedRangeBean.setPrecision(1);
        avsConfigurationBean.setSupportedRange(supportedRangeBean);
        ArrayList arrayList2 = new ArrayList();
        avsConfigurationBean.setPresets(arrayList2);
        AvsConfigurationBean.PresetsBean presetsBean = new AvsConfigurationBean.PresetsBean();
        presetsBean.setRangeValue(1);
        AvsConfigurationBean.PresetsBean.PresetResourcesBean presetResourcesBean = new AvsConfigurationBean.PresetsBean.PresetResourcesBean();
        ArrayList arrayList3 = new ArrayList();
        AvsFriendlyNamesBean.ValueBean valueBean = new AvsFriendlyNamesBean.ValueBean();
        valueBean.setAssetId("Alexa.Value.Minimum");
        arrayList3.add(new AvsFriendlyNamesBean("asset", valueBean));
        arrayList3.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_AU, "min"));
        arrayList3.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_CA, "min"));
        arrayList3.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_GB, "min"));
        arrayList3.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_IN, "min"));
        arrayList3.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_IN, "main"));
        arrayList3.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_US, "min"));
        arrayList3.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_AU, "Minimize"));
        arrayList3.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_CA, "Minimize"));
        arrayList3.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_GB, "Minimize"));
        arrayList3.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_IN, "Minimize"));
        arrayList3.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_US, "Minimize"));
        presetResourcesBean.setFriendlyNames(arrayList3);
        presetsBean.setPresetResources(presetResourcesBean);
        arrayList2.add(presetsBean);
        AvsConfigurationBean.PresetsBean presetsBean2 = new AvsConfigurationBean.PresetsBean();
        presetsBean2.setRangeValue(i);
        AvsConfigurationBean.PresetsBean.PresetResourcesBean presetResourcesBean2 = new AvsConfigurationBean.PresetsBean.PresetResourcesBean();
        ArrayList arrayList4 = new ArrayList();
        AvsFriendlyNamesBean.ValueBean valueBean2 = new AvsFriendlyNamesBean.ValueBean();
        valueBean2.setAssetId("Alexa.Value.Maximum");
        arrayList4.add(new AvsFriendlyNamesBean("asset", valueBean2));
        arrayList4.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_AU, "max"));
        arrayList4.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_CA, "max"));
        arrayList4.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_GB, "max"));
        arrayList4.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_IN, "max"));
        arrayList4.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_US, "max"));
        arrayList4.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_AU, "Maximize"));
        arrayList4.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_CA, "Maximize"));
        arrayList4.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_GB, "Maximize"));
        arrayList4.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_IN, "Maximize"));
        arrayList4.add(createFriendlyNames(AlexaConstant.LANGUAGE_EN_US, "Maximize"));
        presetResourcesBean2.setFriendlyNames(arrayList4);
        presetsBean2.setPresetResources(presetResourcesBean2);
        arrayList2.add(presetsBean2);
        AvsControllerBaseBean.CapabilityResourcesBean capabilityResourcesBean = new AvsControllerBaseBean.CapabilityResourcesBean();
        ArrayList arrayList5 = new ArrayList();
        if (list != null) {
            Iterator<AvsFriendlyNamesBean.ValueBean> it = list.iterator();
            while (it.hasNext()) {
                arrayList5.add(new AvsFriendlyNamesBean("text", it.next()));
            }
        }
        capabilityResourcesBean.setFriendlyNames(arrayList5);
        avsRangeControllerCapabilityBean.setCapabilityResources(capabilityResourcesBean);
        return avsRangeControllerCapabilityBean;
    }

    private static AvsFriendlyNamesBean createFriendlyNames(String str, String str2) {
        AvsFriendlyNamesBean.ValueBean valueBean = new AvsFriendlyNamesBean.ValueBean();
        valueBean.setLocale(str);
        valueBean.setText(str2);
        return new AvsFriendlyNamesBean("text", valueBean);
    }
}