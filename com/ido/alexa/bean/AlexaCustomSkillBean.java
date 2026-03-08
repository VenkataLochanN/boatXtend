package com.ido.alexa.bean;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaCustomSkillBean {
    private DirectiveBean directive;
    private String sessionId;

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public DirectiveBean getDirective() {
        return this.directive;
    }

    public void setDirective(DirectiveBean directiveBean) {
        this.directive = directiveBean;
    }

    public String toString() {
        return "AlexaCustomSkillBean{sessionId='" + this.sessionId + "', directive=" + this.directive + '}';
    }

    public static class DirectiveBean {
        private String data;
        private String type;

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String getData() {
            return this.data;
        }

        public void setData(String str) {
            this.data = str;
        }
    }
}