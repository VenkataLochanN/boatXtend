package com.ido.alexa;

/* JADX INFO: loaded from: classes2.dex */
public interface AlexaCustomSkillConstant {
    public static final String ALEXA_ALARMS = "ALEXA_ALARMS";
    public static final String ALEXA_REMINDERS = "ALEXA_REMINDERS";
    public static final String ALEXA_TIMERS = "ALEXA_TIMERS";
    public static final String EVENT_BRIGHTNESS = "8";
    public static final String EVENT_HR = "9";
    public static final String EVENT_JUMP_EVENT = "5";
    public static final String EVENT_START_SPORT = "4";
    public static final String EVENT_UN_RECOGNIZE = "0";
    public static final int MAX_ALARMS_COUNT = 10;
    public static final int MAX_REMINDERS_COUNT = 5;

    public interface BrightnessEvent {
        public static final int CMD_DECREASE = 1;
        public static final int CMD_DECREASE_PERCENT = 7;
        public static final int CMD_INCREASE = 0;
        public static final int CMD_INCREASE_PERCENT = 6;
        public static final int MIN = 1;
    }

    public interface DNDCmdEvent {
        public static final int CLOSE = 1;
        public static final int OPEN = 0;
    }

    public interface EventInstanceName {
        public static final String WAKEUP_GESTURE = "Wakeup Gesture";
    }

    public enum Frequency {
        DAILY,
        WEEKLY
    }

    public interface MusicEvent {
        public static final int DECREASE_VOLUME = 8;
        public static final int INCREASE_VOLUME = 7;
        public static final int NEXT = 3;
        public static final int PAUSE = 4;
        public static final int PLAY = 1;
        public static final int PREVIOUS = 2;
        public static final int RESUME = 6;
        public static final int STOP = 5;
    }

    public interface ToggleControlOffEvent {
        public static final int FIND_PHONE = 18;
    }

    public interface ToggleControlOnEvent {
        public static final int FIND_PHONE = 8;
    }

    public interface VoiceProtocolEvent {
        public static final int EVT_V3_ALEXA_STE_ALARM2 = 5034;
        public static final int EVT_V3_ALEXA_STE_REMINDER = 5029;
        public static final int EVT_VOICE_ALARM = 5033;
        public static final int EVT_VOICE_JUMP = 7622;
        public static final int EVT_VOICE_OPERATION = 7621;
        public static final int EVT_VOICE_SET_ON_OFF_TYPE = 7624;
        public static final int EVT_VOICE_SET_UI_CONTROLL_STOPWATCH = 7602;
        public static final int EVT_VOICE_SPORTS = 7601;
        public static final int EVT_VOICE_WEATHER = 5032;
    }

    public enum WeekRepeat {
        MO,
        TU,
        WE,
        TH,
        FR,
        SA,
        SU
    }
}