package com.ido.alexa.smarthome;

import android.text.TextUtils;
import com.ido.alexa.AlexaApp;
import com.ido.alexa.AlexaConstant;
import com.ido.alexa.R;
import com.ido.alexa.bean.AlexaRangerControllerBean;
import com.ido.alexa.bean.AlexaToggleControllerBean;
import com.ido.alexa.data.AlexaDiscoveryBean;
import com.ido.alexa.data.Directive;
import com.ido.alexa.data.capability.AvsAlertsCapabilityBean;
import com.ido.alexa.data.capability.AvsCapabilityBaseBean;
import com.ido.alexa.data.capability.AvsEndpoint;
import com.ido.alexa.data.capability.AvsSystemCapabilityBean;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.alexa.manager.AlexaEndpointIdDefine;
import com.ido.alexa.manager.LoadSmartHomeManager;
import com.ido.alexa.manager.SpManager;
import com.ido.alexa.util.AlexaSendCmdToDeviceUtil;
import com.ido.alexa.util.GsonUtil;
import com.ido.alexa.util.Util;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.life.constants.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CreateEndpoint {
    private static final String CATEGORY = "WEARABLE";
    private static final String MANUFACTURERNAME = "IDO";
    private static String PREFIX_FRIENDLYNAME = "Veryfit";
    private static final List<AvsCapabilityBaseBean> mCommonCapabilitiesBeans = new ArrayList();

    static {
        mCommonCapabilitiesBeans.add(new AvsCapabilityBaseBean("AlexaInterface", "InteractionModel", "1.2"));
        mCommonCapabilitiesBeans.add(new AvsCapabilityBaseBean("AlexaInterface", "Alexa", Constants.DIALDEFNED_VERSION_CONNECT));
        mCommonCapabilitiesBeans.add(new AvsCapabilityBaseBean("AlexaInterface", "Alexa.ApiGateway", "1.0"));
        mCommonCapabilitiesBeans.add(new AvsCapabilityBaseBean("AlexaInterface", "SpeechSynthesizer", "1.3"));
        mCommonCapabilitiesBeans.add(new AvsCapabilityBaseBean("AlexaInterface", Directive.TYPE_AUDIOPLAYER, "1.4"));
        mCommonCapabilitiesBeans.add(new AvsCapabilityBaseBean("AlexaInterface", "SpeechRecognizer", "2.3"));
        mCommonCapabilitiesBeans.add(new AvsCapabilityBaseBean("AlexaInterface", "Notifications", "1.0"));
        mCommonCapabilitiesBeans.add(new AvsCapabilityBaseBean("AlexaInterface", "Geolocation", "1.1"));
        mCommonCapabilitiesBeans.add(new AvsCapabilityBaseBean("AlexaInterface", "TemplateRuntime", "1.0"));
        mCommonCapabilitiesBeans.add(new AvsCapabilityBaseBean("AlexaInterface", "Speaker", "1.0"));
        mCommonCapabilitiesBeans.add(new AvsCapabilityBaseBean("AlexaInterface", "Alexa.SoftwareComponentReporter", "1.0"));
        AvsAlertsCapabilityBean.ConfigurationsBean configurationsBean = new AvsAlertsCapabilityBean.ConfigurationsBean();
        configurationsBean.setMaximumAlerts(new AvsAlertsCapabilityBean.ConfigurationsBean.MaximumAlertsBean(53, 50, 3));
        mCommonCapabilitiesBeans.add(new AvsAlertsCapabilityBean("AlexaInterface", "Alerts", "1.3", configurationsBean));
        AvsSystemCapabilityBean.ConfigurationsBean configurationsBean2 = new AvsSystemCapabilityBean.ConfigurationsBean();
        configurationsBean2.setLocales(Arrays.asList(AlexaConstant.LANGUAGE_DE_DE, AlexaConstant.LANGUAGE_EN_AU, AlexaConstant.LANGUAGE_EN_CA, AlexaConstant.LANGUAGE_EN_GB, AlexaConstant.LANGUAGE_EN_IN, AlexaConstant.LANGUAGE_EN_US, AlexaConstant.LANGUAGE_ES_ES, AlexaConstant.LANGUAGE_ES_MX, AlexaConstant.LANGUAGE_ES_US, AlexaConstant.LANGUAGE_FR_CA, AlexaConstant.LANGUAGE_FR_FR, AlexaConstant.LANGUAGE_IT_IT, AlexaConstant.LANGUAGE_JA_JP, AlexaConstant.LANGUAGE_PT_BR));
        mCommonCapabilitiesBeans.add(new AvsSystemCapabilityBean("AlexaInterface", "System", "2.1", configurationsBean2));
    }

    private static String getAlexaDiscoveryEvent(List<AvsEndpoint> list) {
        AlexaDiscoveryBean alexaDiscoveryBean = new AlexaDiscoveryBean();
        AlexaDiscoveryBean.EventBean eventBean = new AlexaDiscoveryBean.EventBean();
        alexaDiscoveryBean.setEvent(eventBean);
        AlexaDiscoveryBean.EventBean.HeaderBean headerBean = new AlexaDiscoveryBean.EventBean.HeaderBean();
        eventBean.setHeader(headerBean);
        headerBean.setNamespace("Alexa.Discovery");
        headerBean.setName("AddOrUpdateReport");
        headerBean.setPayloadVersion(Constants.DIALDEFNED_VERSION_CONNECT);
        headerBean.setMessageId(Util.getUuid());
        headerBean.setEventCorrelationToken(Util.getUuid());
        AlexaDiscoveryBean.EventBean.PayLoadBean payLoadBean = new AlexaDiscoveryBean.EventBean.PayLoadBean();
        eventBean.setPayload(payLoadBean);
        AlexaDiscoveryBean.EventBean.PayLoadBean.ScopeBean scopeBean = new AlexaDiscoveryBean.EventBean.PayLoadBean.ScopeBean();
        payLoadBean.setScope(scopeBean);
        scopeBean.setType("BearerToken");
        scopeBean.setToken(SpManager.getAccessToken());
        payLoadBean.setEndpoints(list);
        return GsonUtil.toJson(alexaDiscoveryBean);
    }

    public static String getDiscoveryEvent() {
        PREFIX_FRIENDLYNAME = AlexaApp.getAppContext().getString(R.string.app_name);
        ArrayList arrayList = new ArrayList();
        SupportFunctionInfo supportFunctionInfo = AlexaSendCmdToDeviceUtil.getSupportFunctionInfo();
        AlexaLogUtil.printAndSave("是否支持Alexa二期功能：" + supportFunctionInfo.V3_alexa_set_jump_ui + "  " + PREFIX_FRIENDLYNAME);
        if (supportFunctionInfo.V3_alexa_set_jump_ui) {
            arrayList.add(createToggle(getCurrentEndpointId(), PREFIX_FRIENDLYNAME, LoadSmartHomeManager.getOpenViewToggleControllerBean()));
            arrayList.addAll(createRange());
            boolean z = supportFunctionInfo.V3_set_100_sport_sort;
            AlexaLogUtil.printAndSave("是否支持Alexa 新增的运动功能：" + z + "  " + PREFIX_FRIENDLYNAME);
            if (z) {
                arrayList.add(createToggle(getCurrentEndpointId() + "-" + AlexaEndpointIdDefine.SPORT, PREFIX_FRIENDLYNAME + " " + AlexaEndpointIdDefine.SPORT, LoadSmartHomeManager.getAlexaSportToggleControllerBean()));
            }
        } else {
            arrayList.add(createCommonCapabilities());
        }
        return getAlexaDiscoveryEvent(arrayList);
    }

    public static String getCurrentEndpointId() {
        return SpManager.getClientId() + "::" + SpManager.getProductId() + "::" + SpManager.getDeviceSerialNumber();
    }

    private static AvsEndpoint createCommonCapabilities() {
        AvsEndpoint avsEndpoint = new AvsEndpoint();
        ArrayList arrayList = new ArrayList();
        AvsEndpoint.ConnectionsBean connectionsBean = new AvsEndpoint.ConnectionsBean();
        connectionsBean.setType("UNKNOWN");
        connectionsBean.setValue(SpManager.getMacAdress());
        arrayList.add(connectionsBean);
        avsEndpoint.setConnections(arrayList);
        String productId = SpManager.getProductId();
        String deviceSerialNumber = SpManager.getDeviceSerialNumber();
        String bleName = SpManager.getBleName();
        avsEndpoint.setEndpointId(getCurrentEndpointId());
        avsEndpoint.setFriendlyName(PREFIX_FRIENDLYNAME);
        avsEndpoint.setDescription(bleName + " on Android");
        avsEndpoint.setManufacturerName(MANUFACTURERNAME);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(CATEGORY);
        avsEndpoint.setDisplayCategories(arrayList2);
        AvsEndpoint.RegistrationBean registrationBean = new AvsEndpoint.RegistrationBean();
        avsEndpoint.setRegistration(registrationBean);
        registrationBean.setProductId(productId);
        registrationBean.setDeviceSerialNumber(deviceSerialNumber);
        avsEndpoint.setCapabilities(mCommonCapabilitiesBeans);
        return avsEndpoint;
    }

    private static AvsEndpoint createToggle(String str, String str2, AlexaToggleControllerBean alexaToggleControllerBean) {
        AvsEndpoint avsEndpoint = new AvsEndpoint();
        String bleName = SpManager.getBleName();
        avsEndpoint.setEndpointId(str);
        avsEndpoint.setFriendlyName(str2);
        avsEndpoint.setDescription(bleName + " on Android");
        avsEndpoint.setManufacturerName(MANUFACTURERNAME);
        ArrayList arrayList = new ArrayList();
        arrayList.add(CATEGORY);
        avsEndpoint.setDisplayCategories(arrayList);
        ArrayList arrayList2 = new ArrayList(mCommonCapabilitiesBeans);
        avsEndpoint.setCapabilities(arrayList2);
        AlexaLogUtil.d("Alexa endPointId=" + str + " ,friendlyName=" + str2);
        if (alexaToggleControllerBean != null && alexaToggleControllerBean.getSkill() != null) {
            Iterator<AlexaToggleControllerBean.SkillBean> it = alexaToggleControllerBean.getSkill().iterator();
            while (it.hasNext()) {
                List<AlexaToggleControllerBean.SkillBean.CapabilityBean> capability = it.next().getCapability();
                if (capability != null) {
                    for (AlexaToggleControllerBean.SkillBean.CapabilityBean capabilityBean : capability) {
                        arrayList2.add(CreateToggleController.create(capabilityBean.getInstance(), capabilityBean.getFriendlyNames()));
                    }
                }
            }
        }
        return avsEndpoint;
    }

    private static ArrayList<AvsEndpoint> createRange() {
        ArrayList<AvsEndpoint> arrayList = new ArrayList<>();
        AlexaRangerControllerBean alexaRangeControllerBean = LoadSmartHomeManager.getAlexaRangeControllerBean();
        if (alexaRangeControllerBean != null && alexaRangeControllerBean.getSkill() != null) {
            Iterator<AlexaRangerControllerBean.SkillBean> it = alexaRangeControllerBean.getSkill().iterator();
            while (it.hasNext()) {
                List<AlexaRangerControllerBean.SkillBean.CapabilityBean> capability = it.next().getCapability();
                if (capability != null) {
                    Iterator<AlexaRangerControllerBean.SkillBean.CapabilityBean> it2 = capability.iterator();
                    while (it2.hasNext()) {
                        arrayList.add(createCommonRange(it2.next()));
                    }
                }
            }
        }
        return arrayList;
    }

    private static AvsEndpoint createCommonRange(AlexaRangerControllerBean.SkillBean.CapabilityBean capabilityBean) {
        AvsEndpoint avsEndpoint = new AvsEndpoint();
        String bleName = SpManager.getBleName();
        String capabilityBean2 = capabilityBean.getInstance();
        avsEndpoint.setEndpointId(getCurrentEndpointId() + "-" + capabilityBean2);
        avsEndpoint.setFriendlyName(PREFIX_FRIENDLYNAME + " " + capabilityBean2);
        StringBuilder sb = new StringBuilder();
        sb.append(bleName);
        sb.append(" on Android");
        avsEndpoint.setDescription(sb.toString());
        avsEndpoint.setManufacturerName(MANUFACTURERNAME);
        ArrayList arrayList = new ArrayList();
        arrayList.add(CATEGORY);
        avsEndpoint.setDisplayCategories(arrayList);
        ArrayList arrayList2 = new ArrayList();
        avsEndpoint.setCapabilities(arrayList2);
        if (TextUtils.equals(AlexaEndpointIdDefine.BRIGHTNESS, capabilityBean.getInstance())) {
            SupportFunctionInfo supportFunctionInfo = AlexaSendCmdToDeviceUtil.getSupportFunctionInfo();
            AlexaLogUtil.printAndSave("Alexa 亮度级别支持5级：" + supportFunctionInfo.screen_brightness_5_level + " 支持3级：" + supportFunctionInfo.ex_table_main8_screen_brightness_3_level + " 支持100：" + supportFunctionInfo.v2_set_alexa_operation_100brightness);
            int i = 0;
            if (supportFunctionInfo.screen_brightness_5_level) {
                i = 5;
            } else if (supportFunctionInfo.ex_table_main8_screen_brightness_3_level) {
                i = 3;
            } else if (supportFunctionInfo.v2_set_alexa_operation_100brightness) {
                i = 100;
            }
            if (i != 0) {
                arrayList2.add(CreateRangeController.createBrightness(capabilityBean.getInstance(), capabilityBean.getFriendlyNames(), i));
            }
        } else {
            arrayList2.add(CreateRangeController.create(capabilityBean.getInstance(), capabilityBean.getFriendlyNames()));
        }
        return avsEndpoint;
    }
}