package com.ido.alexa.data;

import com.ido.alexa.data.capability.AvsEndpoint;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaDiscoveryBean {
    private EventBean event;

    public void setEvent(EventBean eventBean) {
        this.event = eventBean;
    }

    public EventBean getEvent() {
        return this.event;
    }

    public static class EventBean {
        private HeaderBean header;
        private PayLoadBean payload;

        public HeaderBean getHeader() {
            return this.header;
        }

        public void setHeader(HeaderBean headerBean) {
            this.header = headerBean;
        }

        public PayLoadBean getPayload() {
            return this.payload;
        }

        public void setPayload(PayLoadBean payLoadBean) {
            this.payload = payLoadBean;
        }

        public static class HeaderBean {
            private String eventCorrelationToken;
            private String messageId;
            private String name;
            private String namespace;
            private String payloadVersion;

            public String getNamespace() {
                return this.namespace;
            }

            public void setNamespace(String str) {
                this.namespace = str;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String getMessageId() {
                return this.messageId;
            }

            public void setMessageId(String str) {
                this.messageId = str;
            }

            public String getEventCorrelationToken() {
                return this.eventCorrelationToken;
            }

            public void setEventCorrelationToken(String str) {
                this.eventCorrelationToken = str;
            }

            public String getPayloadVersion() {
                return this.payloadVersion;
            }

            public void setPayloadVersion(String str) {
                this.payloadVersion = str;
            }
        }

        public static class PayLoadBean {
            private List<AvsEndpoint> endpoints;
            private ScopeBean scope;

            public ScopeBean getScope() {
                return this.scope;
            }

            public void setScope(ScopeBean scopeBean) {
                this.scope = scopeBean;
            }

            public List<AvsEndpoint> getEndpoints() {
                return this.endpoints;
            }

            public void setEndpoints(List<AvsEndpoint> list) {
                this.endpoints = list;
            }

            public static class ScopeBean {
                private String token;
                private String type;

                public String getType() {
                    return this.type;
                }

                public void setType(String str) {
                    this.type = str;
                }

                public String getToken() {
                    return this.token;
                }

                public void setToken(String str) {
                    this.token = str;
                }
            }
        }
    }
}