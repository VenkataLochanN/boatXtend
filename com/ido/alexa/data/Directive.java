package com.ido.alexa.data;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Directive {
    private static final String PLAY_BEHAVIOR_ENQUEUE = "ENQUEUE";
    private static final String PLAY_BEHAVIOR_REPLACE_ALL = "REPLACE_ALL";
    private static final String PLAY_BEHAVIOR_REPLACE_ENQUEUED = "REPLACE_ENQUEUED";
    public static final String TYPE_ADJUSTRANGEVALUE = "AdjustRangeValue";
    public static final String TYPE_ADJUST_VOLUME = "AdjustVolume";
    public static final String TYPE_AUDIOPLAYER = "AudioPlayer";
    public static final String TYPE_BODY_TEMPLATE = "BodyTemplate";
    public static final String TYPE_CLEARINDICATOR = "ClearIndicator";
    public static final String TYPE_DELETE_ALERT = "DeleteAlert";
    public static final String TYPE_DELETE_ALERTS = "DeleteAlerts";
    public static final String TYPE_EXCEPTION = "Exception";
    public static final String TYPE_EXPECT_SPEECH = "ExpectSpeech";
    public static final String TYPE_LIST_TEMPLATE = "ListTemplate1";
    public static final String TYPE_MEDIA_NEXT = "NextCommandIssued";
    public static final String TYPE_MEDIA_PAUSE = "PauseCommandIssued";
    public static final String TYPE_MEDIA_PLAY = "PlayCommandIssued";
    public static final String TYPE_MEDIA_PREVIOUS = "PreviousCommandIssue";
    public static final String TYPE_PLAY = "Play";
    private static final String TYPE_RENDER_TEMPLATE = "RenderTemplate";
    public static final String TYPE_REPORTSTATE = "ReportState";
    public static final String TYPE_RESET = "ResetUserInactivity";
    public static final String TYPE_SETINDICATOR = "SetIndicator";
    public static final String TYPE_SETLOCALES = "SetLocales";
    public static final String TYPE_SETRANGEVALUE = "SetRangeValue";
    public static final String TYPE_SETTIMEZONE = "SetTimeZone";
    public static final String TYPE_SET_ALERT = "SetAlert";
    public static final String TYPE_SET_GATEWAY = "SetGateway";
    public static final String TYPE_SET_MUTE = "SetMute";
    public static final String TYPE_SET_VOLUME = "SetVolume";
    public static final String TYPE_SPEAK = "Speak";
    public static final String TYPE_STOP = "Stop";
    public static final String TYPE_STOP_CAPTURE = "StopCapture";
    public static final String TYPE_TURNOFF = "TurnOff";
    public static final String TYPE_TURNON = "TurnOn";
    public static final String TYPE_WATHER_TEMPLATE = "WeatherTemplate";
    private Endpoint endpoint;
    private Header header;
    private Payload payload;

    public boolean isTypeTemplate() {
        return TextUtils.equals(this.header.getName(), TYPE_RENDER_TEMPLATE);
    }

    public boolean isTypeSpeak() {
        return TextUtils.equals(this.header.getName(), TYPE_SPEAK);
    }

    public boolean isTypePlay() {
        return TextUtils.equals(this.header.getName(), TYPE_PLAY);
    }

    public boolean isTypeAudioPlay() {
        return TextUtils.equals(this.header.getNamespace(), TYPE_AUDIOPLAYER);
    }

    public boolean isSetAlert() {
        return TextUtils.equals(this.header.getName(), TYPE_SET_ALERT);
    }

    public boolean isPlayBehaviorReplaceAll() {
        return TextUtils.equals(this.payload.getPlayBehavior(), PLAY_BEHAVIOR_REPLACE_ALL);
    }

    public boolean isPlayBehaviorEnqueue() {
        return TextUtils.equals(this.payload.getPlayBehavior(), PLAY_BEHAVIOR_ENQUEUE);
    }

    public boolean isPlayBehaviorReplaceEnqueued() {
        return TextUtils.equals(this.payload.getPlayBehavior(), PLAY_BEHAVIOR_REPLACE_ENQUEUED);
    }

    public Header getHeader() {
        return this.header;
    }

    public Payload getPayload() {
        return this.payload;
    }

    public Endpoint getEndpoint() {
        return this.endpoint;
    }

    public static class Endpoint {
        String endpointId;

        public String getEndpointId() {
            return this.endpointId;
        }
    }

    public static class Header {
        String correlationToken;
        String dialogRequestId;
        String instance;
        String messageId;
        String name;
        String namespace;

        public String getNamespace() {
            return this.namespace;
        }

        public String getName() {
            return this.name;
        }

        public String getMessageId() {
            return this.messageId;
        }

        public String getDialogRequestId() {
            return this.dialogRequestId;
        }

        public String getCorrelationToken() {
            return this.correlationToken;
        }

        public String getInstance() {
            return this.instance;
        }
    }

    public static class Payload {
        AudioItem audioItem;
        Caption caption;
        String code;
        String currentWeather;
        CurrentWeatherIcon currentWeatherIcon;
        String description;
        String format;
        String gateway;
        TodayWeatherItem highTemperature;
        Image image;
        String label;
        List<ListItem> listItems;
        ArrayList<String> locales;
        TodayWeatherItem lowTemperature;
        boolean mute;
        String playBehavior;
        int rangeValue;
        int rangeValueDelta;
        boolean rangeValueDeltaDefault;
        String scheduledTime;
        String textField;
        String timeZone;
        long timeoutInMilliseconds;
        Title title;
        String token;
        List<String> tokens;
        String type;
        String url;
        long volume;
        List<WeatherForecast> weatherForecast;

        public int getRangeValueDelta() {
            return this.rangeValueDelta;
        }

        public void setRangeValueDelta(int i) {
            this.rangeValueDelta = i;
        }

        public int getRangeValue() {
            return this.rangeValue;
        }

        public void setRangeValue(int i) {
            this.rangeValue = i;
        }

        public void setRangeValueDeltaDefault(boolean z) {
            this.rangeValueDeltaDefault = z;
        }

        public boolean isRangeValueDeltaDefault() {
            return this.rangeValueDeltaDefault;
        }

        public String getTimeZone() {
            return this.timeZone;
        }

        public void setTimeZone(String str) {
            this.timeZone = str;
        }

        public ArrayList<String> getLocales() {
            return this.locales;
        }

        public void setLocales(ArrayList<String> arrayList) {
            this.locales = arrayList;
        }

        public List<ListItem> getListItems() {
            return this.listItems;
        }

        public String getLabel() {
            return this.label;
        }

        public void setLabel(String str) {
            this.label = str;
        }

        public void setListItems(List<ListItem> list) {
            this.listItems = list;
        }

        public Caption getCaption() {
            return this.caption;
        }

        public void setCaption(Caption caption) {
            this.caption = caption;
        }

        public String getUrl() {
            return this.url;
        }

        public String getFormat() {
            return this.format;
        }

        public String getToken() {
            AudioItem audioItem;
            if (this.token == null && (audioItem = this.audioItem) != null && audioItem.getStream() != null) {
                return this.audioItem.getStream().getToken();
            }
            return this.token;
        }

        public List<String> getTokens() {
            return this.tokens;
        }

        public String getType() {
            return this.type;
        }

        public String getScheduledTime() {
            return this.scheduledTime;
        }

        public String getPlayBehavior() {
            return this.playBehavior;
        }

        public AudioItem getAudioItem() {
            return this.audioItem;
        }

        public long getVolume() {
            return this.volume;
        }

        public boolean isMute() {
            return this.mute;
        }

        public long getTimeoutInMilliseconds() {
            return this.timeoutInMilliseconds;
        }

        public String getDescription() {
            return this.description;
        }

        public String getCode() {
            return this.code;
        }

        public String getGateway() {
            return this.gateway;
        }

        public String getTextField() {
            return this.textField;
        }

        public Title getTitle() {
            return this.title;
        }

        public Image getImage() {
            return this.image;
        }

        public String getCurrentWeather() {
            return this.currentWeather;
        }

        public CurrentWeatherIcon getCurrentWeatherIcon() {
            return this.currentWeatherIcon;
        }

        public TodayWeatherItem getLowTemperature() {
            return this.lowTemperature;
        }

        public TodayWeatherItem getHighTemperature() {
            return this.highTemperature;
        }

        public List<WeatherForecast> getWeatherForecast() {
            return this.weatherForecast;
        }

        public static class CurrentWeatherIcon {
            private String contentDescription;
            private List<Sources> sources;

            public List<Sources> getSources() {
                return this.sources;
            }

            public String getContentDescription() {
                return this.contentDescription;
            }

            public class Sources {
                private String darkBackgroundUrl;
                private int heightPixels;
                private String size;
                private int widthPixels;

                public Sources() {
                }

                public String getDarkBackgroundUrl() {
                    return this.darkBackgroundUrl;
                }

                public int getWidthPixels() {
                    return this.widthPixels;
                }

                public int getHeightPixels() {
                    return this.heightPixels;
                }

                public String getSize() {
                    return this.size;
                }
            }
        }

        public static class WeatherForecast {
            String date;
            String day;
            String highTemperature;
            Image image;
            String lowTemperature;

            public String getHighTemperature() {
                return this.highTemperature;
            }

            public String getLowTemperature() {
                return this.lowTemperature;
            }

            public String getDate() {
                return this.date;
            }

            public String getDay() {
                return this.day;
            }

            public Image getImage() {
                return this.image;
            }

            public class Image {
                private String contentDescription;
                private List<Sources> sources;

                public Image() {
                }

                public List<Sources> getSources() {
                    return this.sources;
                }

                public String getContentDescription() {
                    return this.contentDescription;
                }

                public class Sources {
                    private String darkBackgroundUrl;
                    private int heightPixels;
                    private String size;
                    private int widthPixels;

                    public Sources() {
                    }

                    public String getDarkBackgroundUrl() {
                        return this.darkBackgroundUrl;
                    }

                    public int getWidthPixels() {
                        return this.widthPixels;
                    }

                    public int getHeightPixels() {
                        return this.heightPixels;
                    }

                    public String getSize() {
                        return this.size;
                    }
                }
            }
        }

        public static class Caption {
            String content;
            String type;

            public String getContent() {
                return this.content;
            }

            public void setContent(String str) {
                this.content = str;
            }

            public String getType() {
                return this.type;
            }

            public void setType(String str) {
                this.type = str;
            }
        }

        public static class ListItem {
            String leftTextField;
            String rightTextField;

            public String getLeftTextField() {
                return this.leftTextField;
            }

            public void setLeftTextField(String str) {
                this.leftTextField = str;
            }

            public String getRightTextField() {
                return this.rightTextField;
            }

            public void setRightTextField(String str) {
                this.rightTextField = str;
            }
        }

        public static class Title {
            String mainTitle;
            String subTitle;

            public String getMainTitle() {
                return this.mainTitle;
            }

            public String getSubTitle() {
                return this.subTitle;
            }
        }

        public static class Image {
            List<source> sources;

            public List<source> getSources() {
                return this.sources;
            }

            public static class source {
                String size;
                String url;

                public String getSize() {
                    return this.size;
                }

                public String getUrl() {
                    return this.url;
                }
            }
        }
    }

    public static class AudioItem {
        String audioItemId;
        Stream stream;

        public String getAudioItemId() {
            return this.audioItemId;
        }

        public Stream getStream() {
            return this.stream;
        }
    }

    public static class Stream {
        private Payload.Caption caption;
        String expectedPreviousToken;
        String expiryTime;
        long offsetInMilliseconds;
        String streamFormat;
        String token;
        String url;

        public Payload.Caption getCaption() {
            return this.caption;
        }

        public void setCaption(Payload.Caption caption) {
            this.caption = caption;
        }

        public String getUrl() {
            return this.url;
        }

        public String getStreamFormat() {
            return this.streamFormat;
        }

        public long getOffsetInMilliseconds() {
            return this.offsetInMilliseconds;
        }

        public String getExpiryTime() {
            return this.expiryTime;
        }

        public String getToken() {
            return this.token;
        }

        public String getExpectedPreviousToken() {
            return this.expectedPreviousToken;
        }
    }

    public static class DirectiveWrapper {
        Directive directive;

        public Directive getDirective() {
            return this.directive;
        }
    }
}