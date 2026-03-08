package com.ido.alexa.data;

import android.text.TextUtils;
import com.ido.life.bean.SavePrivateSafeSettingBean;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AvsAlarmItem {
    private List<AlexaAlarmsBean> alarms;
    private LinksBean links;
    private int totalCount;

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int i) {
        this.totalCount = i;
    }

    public LinksBean getLinks() {
        return this.links;
    }

    public void setLinks(LinksBean linksBean) {
        this.links = linksBean;
    }

    public List<AlexaAlarmsBean> getAlarms() {
        return this.alarms;
    }

    public void setAlarms(List<AlexaAlarmsBean> list) {
        this.alarms = list;
    }

    public static class LinksBean {
        private Object next;

        public Object getNext() {
            return this.next;
        }

        public void setNext(Object obj) {
            this.next = obj;
        }
    }

    public static class AlexaAlarmsBean {
        private String alarmToken;
        private List<AssetsBean> assets;
        private String createdTime;
        private List<String> endpointIds;
        private NextOccurrenceBean nextOccurrence;
        private String status;
        private TriggerBean trigger;
        private String updatedTime;

        public boolean isOn() {
            return TextUtils.equals(this.status, SavePrivateSafeSettingBean.ON);
        }

        public String getAlarmToken() {
            return this.alarmToken;
        }

        public void setAlarmToken(String str) {
            this.alarmToken = str;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String str) {
            this.status = str;
        }

        public TriggerBean getTrigger() {
            return this.trigger;
        }

        public void setTrigger(TriggerBean triggerBean) {
            this.trigger = triggerBean;
        }

        public String getCreatedTime() {
            return this.createdTime;
        }

        public void setCreatedTime(String str) {
            this.createdTime = str;
        }

        public String getUpdatedTime() {
            return this.updatedTime;
        }

        public void setUpdatedTime(String str) {
            this.updatedTime = str;
        }

        public NextOccurrenceBean getNextOccurrence() {
            return this.nextOccurrence;
        }

        public void setNextOccurrence(NextOccurrenceBean nextOccurrenceBean) {
            this.nextOccurrence = nextOccurrenceBean;
        }

        public List<String> getEndpointIds() {
            return this.endpointIds;
        }

        public void setEndpointIds(List<String> list) {
            this.endpointIds = list;
        }

        public List<AssetsBean> getAssets() {
            return this.assets;
        }

        public void setAssets(List<AssetsBean> list) {
            this.assets = list;
        }

        public String toString() {
            return "AlexaAlarmsBean{alarmToken='" + this.alarmToken + "', status='" + this.status + "', trigger=" + this.trigger + ", createdTime='" + this.createdTime + "', updatedTime='" + this.updatedTime + "', nextOccurrence=" + this.nextOccurrence + ", endpointIds=" + this.endpointIds + ", assets=" + this.assets + '}';
        }

        public static class TriggerBean {
            private RecurrenceBean recurrence;
            private String scheduledTime;
            private String timeZoneId;

            public String getScheduledTime() {
                return this.scheduledTime;
            }

            public void setScheduledTime(String str) {
                this.scheduledTime = str;
            }

            public RecurrenceBean getRecurrence() {
                return this.recurrence;
            }

            public void setRecurrence(RecurrenceBean recurrenceBean) {
                this.recurrence = recurrenceBean;
            }

            public String getTimeZoneId() {
                return this.timeZoneId;
            }

            public void setTimeZoneId(String str) {
                this.timeZoneId = str;
            }

            public String toString() {
                return "TriggerBean{scheduledTime='" + this.scheduledTime + "', recurrence=" + this.recurrence + ", timeZoneId='" + this.timeZoneId + "'}";
            }

            public static class RecurrenceBean {
                private List<String> byDay;
                private String freq;
                private int interval;

                public String getFreq() {
                    return this.freq;
                }

                public void setFreq(String str) {
                    this.freq = str;
                }

                public int getInterval() {
                    return this.interval;
                }

                public void setInterval(int i) {
                    this.interval = i;
                }

                public List<String> getByDay() {
                    return this.byDay;
                }

                public void setByDay(List<String> list) {
                    this.byDay = list;
                }

                public String toString() {
                    return "RecurrenceBean{freq='" + this.freq + "', interval=" + this.interval + ", byDay=" + this.byDay + '}';
                }
            }
        }

        public static class NextOccurrenceBean {
            private String status;

            public String getStatus() {
                return this.status;
            }

            public void setStatus(String str) {
                this.status = str;
            }
        }

        public static class AssetsBean {
            private String assetId;
            private String displayName;
            private String sampleUrl;
            private String type;

            public String getType() {
                return this.type;
            }

            public void setType(String str) {
                this.type = str;
            }

            public String getAssetId() {
                return this.assetId;
            }

            public void setAssetId(String str) {
                this.assetId = str;
            }

            public String getDisplayName() {
                return this.displayName;
            }

            public void setDisplayName(String str) {
                this.displayName = str;
            }

            public String getSampleUrl() {
                return this.sampleUrl;
            }

            public void setSampleUrl(String str) {
                this.sampleUrl = str;
            }
        }
    }
}