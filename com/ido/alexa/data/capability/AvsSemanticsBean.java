package com.ido.alexa.data.capability;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AvsSemanticsBean {
    private List<ActionMappingsBean> actionMappings;
    private List<StateMappingsBean> stateMappings;

    public void setStateMappings(List<StateMappingsBean> list) {
        this.stateMappings = list;
    }

    public void setActionMappings(List<ActionMappingsBean> list) {
        this.actionMappings = list;
    }

    public static class ActionMappingsBean {

        @SerializedName("@type")
        private String _$Type227;
        private List<String> actions;
        private DirectiveBean directive;

        public ActionMappingsBean(String str, List<String> list, DirectiveBean directiveBean) {
            this._$Type227 = str;
            this.actions = list;
            this.directive = directiveBean;
        }

        public static class DirectiveBean {
            private String name;
            private PayloadBean payload;

            public static class PayloadBean {
            }

            public void setName(String str) {
                this.name = str;
            }

            public void setPayload(PayloadBean payloadBean) {
                this.payload = payloadBean;
            }
        }
    }

    public static class StateMappingsBean {

        @SerializedName("@type")
        private String _$Type32;
        private List<String> states;
        private String value;

        public StateMappingsBean(String str, String str2, List<String> list) {
            this._$Type32 = str;
            this.value = str2;
            this.states = list;
        }
    }
}