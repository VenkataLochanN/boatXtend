package com.ido.alexa.data;

import com.google.gson.Gson;
import com.ido.alexa.data.capability.AvsStateReportPropertiesBean;
import com.ido.alexa.util.GsonUtil;
import com.ido.alexa.util.Util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes2.dex */
public class Event {
    public static String mSpeechRecognizerEventRequestId;
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.getDefault());
    private Header header;
    private Payload payload;

    public static String getSpeechRecognizerEvent(String str) {
        mSpeechRecognizerEventRequestId = "dialogRequest-" + Util.getUuid();
        Builder builder = new Builder();
        builder.setHeaderNamespace("SpeechRecognizer").setHeaderName("Recognize").setHeaderMessageId(Util.getUuid()).setHeaderDialogRequestId(mSpeechRecognizerEventRequestId).setPayloadFormat(str).setPayloadProfile("NEAR_FIELD").setPayloadInitiator(new Payload.Initiator("TAP"));
        return builder.toJson();
    }

    public static String getSystemLocalesChangeEvent(ArrayList<String> arrayList) {
        Builder builder = new Builder();
        builder.setHeaderNamespace("System").setHeaderName("LocalesChanged").setHeaderMessageId(Util.getUuid()).setPayloadLocales(arrayList);
        return builder.toJson();
    }

    public static String getSystemLocalesReportEvent(ArrayList<String> arrayList) {
        Builder builder = new Builder();
        builder.setHeaderNamespace("System").setHeaderName("LocalesReport").setHeaderMessageId(Util.getUuid()).setPayloadLocales(arrayList);
        return builder.toJson();
    }

    public static String getSystemTimeZoneChangeEvent(String str) {
        Builder builder = new Builder();
        builder.setHeaderNamespace("System").setHeaderName("TimeZoneChanged").setHeaderMessageId(Util.getUuid()).setPayloadTimeZone(str);
        return builder.toJson();
    }

    public static String getSystemTimeZoneReportEvent(String str) {
        Builder builder = new Builder();
        builder.setHeaderNamespace("System").setHeaderName("TimeZoneReport").setHeaderMessageId(Util.getUuid()).setPayloadTimeZone(str);
        return builder.toJson();
    }

    public static String getVolumeChangedEvent(long j) {
        return " {\"event\":{\"header\":{\"messageId\":\"" + Util.getUuid() + "\",\"name\":\"VolumeChanged\",\"namespace\":\"Speaker\"},\"payload\":{\"muted\":false,\"volume\":" + j + "}}}";
    }

    public static String getUserInactivityReportEvent(long j) {
        return "{\"event\":{\"header\":{\"messageId\":\"" + Util.getUuid() + "\",\"name\":\"UserInactivityReport\",\"namespace\":\"System\"},\"payload\":{\"inactiveTimeInSeconds\":" + j + "}}}";
    }

    public static String getSoftwareInfoEvent(long j) {
        return "{\"event\":{\"header\":{\"messageId\":\"" + Util.getUuid() + "\",\"name\":\"SoftwareInfo\",\"namespace\":\"System\"},\"payload\":{\"firmwareVersion\":" + j + "}}}";
    }

    public static String getNotifyStateEvent(boolean z) {
        return "{\"context\":[{\"header\":{\"name\":\"IndicatorState\",\"namespace\":\"Notifications\"},\"payload\":{\"isEnabled\":" + z + ",\"isVisualIndicatorPersisted\":true}}],\"event\":{\"header\":{\"messageId\":\"" + Util.getUuid() + "\",\"name\":\"SynchronizeState\",\"namespace\":\"System\"},\"payload\":{}}}";
    }

    public static String getSpeechFinishedEvent(String str) {
        Builder builder = new Builder();
        builder.setHeaderNamespace("SpeechSynthesizer").setHeaderName("SpeechFinished").setHeaderMessageId(Util.getUuid()).setPayloadToken(str);
        return builder.toJson();
    }

    public static String getGatewayEvent() {
        Builder builder = new Builder();
        builder.setHeaderNamespace("Alexa.ApiGateway").setHeaderName("VerifyGateway").setHeaderMessageId(Util.getUuid());
        return builder.toJson();
    }

    public static String getResponseControllerReplyEvent(String str, String str2, String str3) {
        return "{\"context\":{\"properties\":" + str + "},\"event\":{\"header\":{\"namespace\":\"Alexa\",\"name\":\"Response\",\"payloadVersion\":\"3\",\"messageId\":\"" + Util.getUuid() + "\",\"correlationToken\":\"" + str2 + "\"},\"endpoint\":{\"endpointId\":\"" + str3 + "\"},\"payload\":{}}}";
    }

    public static String getStateReportEvent(String str, String str2, String str3, String str4) {
        return "{\"event\":{\"header\":{\"namespace\":\"Alexa\",\"name\":\"StateReport\",\"payloadVersion\":\"3\",\"messageId\":\"" + str + "\",\"correlationToken\":\"" + str2 + "\"},\"endpoint\":{\"endpointId\":\"" + str3 + "\"},\"payload\":{}},\"context\":{\"properties\":" + str4 + "}}";
    }

    public static String getState(String str, String str2, String str3, Object obj) {
        ArrayList arrayList = new ArrayList();
        AvsStateReportPropertiesBean avsStateReportPropertiesBean = new AvsStateReportPropertiesBean();
        avsStateReportPropertiesBean.setNamespace(str);
        avsStateReportPropertiesBean.setName(str2);
        avsStateReportPropertiesBean.setInstance(str3);
        avsStateReportPropertiesBean.setValue(obj);
        avsStateReportPropertiesBean.setTimeOfSample(simpleDateFormat.format(new Date()));
        avsStateReportPropertiesBean.setUncertaintyInMilliseconds(500);
        arrayList.add(avsStateReportPropertiesBean);
        return GsonUtil.toJson(arrayList);
    }

    public static String getDeleteReportEvent(String str, String str2) {
        return "{\"event\":{\"header\":{\"namespace\":\"Alexa.Discovery\",\"name\":\"DeleteReport\",\"payloadVersion\":\"3\",\"messageId\":\"" + Util.getUuid() + "\"},\"payload\":{\"scope\":{\"type\":\"BearerToken\",\"token\":\"" + str + "\"},\"endpoints\":" + str2 + "}}}";
    }

    public Header getHeader() {
        return this.header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Payload getPayload() {
        return this.payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public static class Builder {
        Event event = new Event();
        Payload payload = new Payload();
        Header header = new Header();
        List<Event> context = new ArrayList();

        public Builder() {
            this.event.setPayload(this.payload);
            this.event.setHeader(this.header);
        }

        public EventWrapper build() {
            EventWrapper eventWrapper = new EventWrapper();
            eventWrapper.event = this.event;
            List<Event> list = this.context;
            if (list != null && !list.isEmpty() && (this.context.size() != 1 || this.context.get(0) != null)) {
                eventWrapper.context = this.context;
            }
            return eventWrapper;
        }

        public String toJson() {
            return build().toJson();
        }

        public Builder setContext(List<Event> list) {
            if (list == null) {
                return this;
            }
            this.context = list;
            return this;
        }

        Builder setHeaderNamespace(String str) {
            this.header.namespace = str;
            return this;
        }

        Builder setHeaderName(String str) {
            this.header.name = str;
            return this;
        }

        Builder setHeaderMessageId(String str) {
            this.header.messageId = str;
            return this;
        }

        Builder setHeaderDialogRequestId(String str) {
            this.header.dialogRequestId = str;
            return this;
        }

        Builder setPayloadLocales(ArrayList<String> arrayList) {
            this.payload.locales = arrayList;
            return this;
        }

        Builder setPayloadTimeZone(String str) {
            this.payload.timeZone = str;
            return this;
        }

        Builder setPayloadProfile(String str) {
            this.payload.profile = str;
            return this;
        }

        Builder setPayloadFormat(String str) {
            this.payload.format = str;
            return this;
        }

        void setPayloadInitiator(Payload.Initiator initiator) {
            this.payload.initiator = initiator;
        }

        void setPayloadToken(String str) {
            this.payload.token = str;
        }
    }

    public static class Payload {
        String format;
        Initiator initiator;
        ArrayList<String> locales;
        String profile;
        String timeZone;
        String token;

        public String getFormat() {
            return this.format;
        }

        public static class Initiator {
            public String type;

            Initiator(String str) {
                this.type = str;
            }
        }
    }

    public static class Header {
        String dialogRequestId;
        String messageId;
        String name;
        String namespace;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }
    }

    public static class EventWrapper {
        List<Event> context = new ArrayList();
        Event event;

        public Event getEvent() {
            return this.event;
        }

        public List<Event> getContext() {
            return this.context;
        }

        public String toJson() {
            return new Gson().toJson(this) + IOUtils.LINE_SEPARATOR_UNIX;
        }
    }
}