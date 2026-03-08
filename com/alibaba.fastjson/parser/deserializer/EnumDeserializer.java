package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import java.lang.reflect.Type;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class EnumDeserializer implements ObjectDeserializer {
    private final Class<?> enumClass;
    protected long[] enumNameHashCodes;
    protected final Enum[] enums;
    protected final Enum[] ordinalEnums;

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 2;
    }

    public EnumDeserializer(Class<?> cls) {
        this.enumClass = cls;
        this.ordinalEnums = (Enum[]) cls.getEnumConstants();
        Enum[] enumArr = this.ordinalEnums;
        long[] jArr = new long[enumArr.length];
        this.enumNameHashCodes = new long[enumArr.length];
        int i = 0;
        while (true) {
            Enum[] enumArr2 = this.ordinalEnums;
            if (i >= enumArr2.length) {
                break;
            }
            String strName = enumArr2[i].name();
            long jCharAt = -3750763034362895579L;
            for (int i2 = 0; i2 < strName.length(); i2++) {
                jCharAt = (jCharAt ^ ((long) strName.charAt(i2))) * 1099511628211L;
            }
            jArr[i] = jCharAt;
            this.enumNameHashCodes[i] = jCharAt;
            i++;
        }
        Arrays.sort(this.enumNameHashCodes);
        this.enums = new Enum[this.ordinalEnums.length];
        for (int i3 = 0; i3 < this.enumNameHashCodes.length; i3++) {
            int i4 = 0;
            while (true) {
                if (i4 >= jArr.length) {
                    break;
                }
                if (this.enumNameHashCodes[i3] == jArr[i4]) {
                    this.enums[i3] = this.ordinalEnums[i4];
                    break;
                }
                i4++;
            }
        }
    }

    public Enum getEnumByHashCode(long j) {
        int iBinarySearch;
        if (this.enums != null && (iBinarySearch = Arrays.binarySearch(this.enumNameHashCodes, j)) >= 0) {
            return this.enums[iBinarySearch];
        }
        return null;
    }

    public Enum<?> valueOf(int i) {
        return this.ordinalEnums[i];
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        try {
            JSONLexer jSONLexer = defaultJSONParser.lexer;
            int i = jSONLexer.token();
            if (i == 2) {
                int iIntValue = jSONLexer.intValue();
                jSONLexer.nextToken(16);
                if (iIntValue < 0 || iIntValue > this.ordinalEnums.length) {
                    throw new JSONException("parse enum " + this.enumClass.getName() + " error, value : " + iIntValue);
                }
                return (T) this.ordinalEnums[iIntValue];
            }
            if (i == 4) {
                String strStringVal = jSONLexer.stringVal();
                jSONLexer.nextToken(16);
                if (strStringVal.length() == 0) {
                    return null;
                }
                return (T) Enum.valueOf(this.enumClass, strStringVal);
            }
            if (i == 8) {
                jSONLexer.nextToken(16);
                return null;
            }
            throw new JSONException("parse enum " + this.enumClass.getName() + " error, value : " + defaultJSONParser.parse());
        } catch (JSONException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new JSONException(e3.getMessage(), e3);
        }
    }
}