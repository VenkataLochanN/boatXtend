package com.ido.alexa.smarthome;

import android.text.TextUtils;
import com.ido.alexa.AlexaConstant;
import com.ido.alexa.data.capability.AvsControllerBaseBean;
import com.ido.alexa.data.capability.AvsFriendlyNamesBean;
import com.ido.alexa.data.capability.AvsPropertiesBean;
import com.ido.alexa.data.capability.AvsSemanticsBean;
import com.ido.alexa.data.capability.AvsToggleControllerCapabilityBean;
import com.ido.life.bean.SavePrivateSafeSettingBean;
import com.ido.life.constants.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CreateToggleController {
    public static AvsToggleControllerCapabilityBean create(String str, List<AvsFriendlyNamesBean.ValueBean> list) {
        AvsToggleControllerCapabilityBean avsToggleControllerCapabilityBean = new AvsToggleControllerCapabilityBean("AlexaInterface", "Alexa.ToggleController", Constants.DIALDEFNED_VERSION_CONNECT);
        avsToggleControllerCapabilityBean.setInstance(str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new AvsPropertiesBean.SupportedBean("toggleState"));
        avsToggleControllerCapabilityBean.setProperties(new AvsPropertiesBean(false, true, false, arrayList));
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
        avsToggleControllerCapabilityBean.setCapabilityResources(capabilityResourcesBean);
        return avsToggleControllerCapabilityBean;
    }

    private static AvsFriendlyNamesBean createFriendlyNames(String str, String str2) {
        AvsFriendlyNamesBean.ValueBean valueBean = new AvsFriendlyNamesBean.ValueBean();
        valueBean.setLocale(str);
        valueBean.setText(str2);
        return new AvsFriendlyNamesBean("text", valueBean);
    }

    private static void addOpenSemantics(String str, AvsToggleControllerCapabilityBean avsToggleControllerCapabilityBean) {
        AvsSemanticsBean avsSemanticsBean = new AvsSemanticsBean();
        if (TextUtils.equals(str, "sport_list")) {
            avsToggleControllerCapabilityBean.setSemantics(avsSemanticsBean);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("Alexa.States.Open");
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new AvsSemanticsBean.StateMappingsBean("StatesToValue", SavePrivateSafeSettingBean.ON, arrayList));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add("Alexa.States.Closed");
        arrayList2.add(new AvsSemanticsBean.StateMappingsBean("StatesToValue", SavePrivateSafeSettingBean.OFF, arrayList3));
        avsSemanticsBean.setStateMappings(arrayList2);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add("Alexa.Actions.Open");
        AvsSemanticsBean.ActionMappingsBean.DirectiveBean directiveBean = new AvsSemanticsBean.ActionMappingsBean.DirectiveBean();
        directiveBean.setName("TurnOn");
        directiveBean.setPayload(new AvsSemanticsBean.ActionMappingsBean.DirectiveBean.PayloadBean());
        ArrayList arrayList5 = new ArrayList();
        arrayList5.add(new AvsSemanticsBean.ActionMappingsBean("ActionsToDirective", arrayList4, directiveBean));
        ArrayList arrayList6 = new ArrayList();
        arrayList6.add("Alexa.Actions.Close");
        AvsSemanticsBean.ActionMappingsBean.DirectiveBean directiveBean2 = new AvsSemanticsBean.ActionMappingsBean.DirectiveBean();
        directiveBean2.setName("TurnOff");
        directiveBean2.setPayload(new AvsSemanticsBean.ActionMappingsBean.DirectiveBean.PayloadBean());
        arrayList5.add(new AvsSemanticsBean.ActionMappingsBean("ActionsToDirective", arrayList6, directiveBean2));
        avsSemanticsBean.setActionMappings(arrayList5);
    }
}