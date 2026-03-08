package com.ido.ble.protocol.model;

import android.text.TextUtils;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class DrinkWaterReminder implements Serializable {
    public static final int STATUS_OFF = 0;
    public static final int STATUS_ON = 1;
    private static final long serialVersionUID = 1;
    private int endHour;
    private int endMinute;
    private int interval;
    public int notifyFlag;

    @JsonAdapter(BooleanTypeAdapter.class)
    private boolean onOff;
    private int repeat;
    private int startHour;
    private int startMinute;
    private boolean[] weeks = new boolean[7];

    /* JADX INFO: renamed from: com.ido.ble.protocol.model.DrinkWaterReminder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static class BooleanTypeAdapter extends TypeAdapter<Boolean> {
        public static boolean toBoolean(String str) {
            return !TextUtils.isEmpty(str) && (str.equalsIgnoreCase("true") || !str.equals(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* JADX INFO: renamed from: read */
        public Boolean read2(JsonReader jsonReader) throws IOException {
            JsonToken jsonTokenPeek = jsonReader.peek();
            int i = AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonToken[jsonTokenPeek.ordinal()];
            if (i == 1) {
                return Boolean.valueOf(jsonReader.nextBoolean());
            }
            if (i == 2) {
                jsonReader.nextNull();
                return null;
            }
            if (i == 3) {
                return Boolean.valueOf(jsonReader.nextInt() != 0);
            }
            if (i == 4) {
                return Boolean.valueOf(toBoolean(jsonReader.nextString()));
            }
            throw new JsonParseException("Expected BOOLEAN or NUMBER but was " + jsonTokenPeek);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Boolean bool) throws IOException {
            if (bool == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(bool.booleanValue() ? 1L : 0L);
            }
        }
    }

    private void byteToWeekRepeat() {
        this.weeks = new boolean[7];
        int i = 0;
        while (i < 7) {
            int i2 = i + 1;
            if ((this.repeat & (1 << i2)) != 0) {
                this.weeks[i] = true;
            } else {
                this.weeks[i] = false;
            }
            i = i2;
        }
        if ((this.repeat & 1) == 1) {
            this.onOff = true;
        } else {
            this.onOff = false;
        }
    }

    private int toByte(boolean[] zArr, boolean z) {
        int i = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            if (zArr[i2]) {
                i |= 1 << (i2 + 1);
            }
        }
        return z ? i | 1 : i;
    }

    public int getEndHour() {
        return this.endHour;
    }

    public int getEndMinute() {
        return this.endMinute;
    }

    public int getInterval() {
        return this.interval;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public int getStartMinute() {
        return this.startMinute;
    }

    public boolean[] getWeekRepeat() {
        byteToWeekRepeat();
        return this.weeks;
    }

    public boolean[] getWeeks() {
        return this.weeks;
    }

    public boolean isOnOff() {
        return this.onOff;
    }

    public void setEndHour(int i) {
        this.endHour = i;
    }

    public void setEndMinute(int i) {
        this.endMinute = i;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    public void setOnOff(boolean z) {
        this.repeat = toByte(this.weeks, z);
        this.onOff = z;
    }

    public void setStartHour(int i) {
        this.startHour = i;
    }

    public void setStartMinute(int i) {
        this.startMinute = i;
    }

    public void setWeeks(boolean[] zArr) {
        this.weeks = zArr;
        this.repeat = toByte(zArr, this.onOff);
    }

    public String toString() {
        return "DrinkWaterReminder{startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", interval=" + this.interval + ", repeat=" + this.repeat + ", notifyFlag=" + this.notifyFlag + ", onOff=" + this.onOff + ", weeks=" + Arrays.toString(this.weeks) + '}';
    }
}