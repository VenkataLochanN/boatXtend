package com.ido.alexa.bean;

import com.ido.alexa.data.capability.AvsFriendlyNamesBean;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaToggleControllerBean {
    private List<SkillBean> skill;

    public List<SkillBean> getSkill() {
        return this.skill;
    }

    public void setSkill(List<SkillBean> list) {
        this.skill = list;
    }

    public static class SkillBean {
        private List<CapabilityBean> capability;
        private String type;

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public List<CapabilityBean> getCapability() {
            return this.capability;
        }

        public void setCapability(List<CapabilityBean> list) {
            this.capability = list;
        }

        public static class CapabilityBean {
            private List<AvsFriendlyNamesBean.ValueBean> friendlyNames;
            private String instance;
            private String stopcmd;
            private String sutype;

            public String getStopcmd() {
                return this.stopcmd;
            }

            public void setStopcmd(String str) {
                this.stopcmd = str;
            }

            public String getInstance() {
                return this.instance;
            }

            public void setInstance(String str) {
                this.instance = str;
            }

            public String getSutype() {
                return this.sutype;
            }

            public void setSutype(String str) {
                this.sutype = str;
            }

            public List<AvsFriendlyNamesBean.ValueBean> getFriendlyNames() {
                return this.friendlyNames;
            }

            public void setFriendlyNames(List<AvsFriendlyNamesBean.ValueBean> list) {
                this.friendlyNames = list;
            }
        }
    }
}