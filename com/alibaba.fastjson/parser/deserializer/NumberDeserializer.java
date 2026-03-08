package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/* JADX INFO: loaded from: classes.dex */
public class NumberDeserializer implements ObjectDeserializer {
    public static final NumberDeserializer instance = new NumberDeserializer();

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v15, types: [T, java.lang.Object, java.math.BigDecimal] */
    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 2) {
            if (type == Double.TYPE || type == Double.class) {
                String strNumberString = jSONLexer.numberString();
                jSONLexer.nextToken(16);
                return (T) Double.valueOf(Double.parseDouble(strNumberString));
            }
            long jLongValue = jSONLexer.longValue();
            jSONLexer.nextToken(16);
            if (type == Short.TYPE || type == Short.class) {
                if (jLongValue > 32767 || jLongValue < -32768) {
                    throw new JSONException("short overflow : " + jLongValue);
                }
                return (T) Short.valueOf((short) jLongValue);
            }
            if (type != Byte.TYPE && type != Byte.class) {
                if (jLongValue >= -2147483648L && jLongValue <= 2147483647L) {
                    return (T) Integer.valueOf((int) jLongValue);
                }
                return (T) Long.valueOf(jLongValue);
            }
            if (jLongValue > 127 || jLongValue < -128) {
                throw new JSONException("short overflow : " + jLongValue);
            }
            return (T) Byte.valueOf((byte) jLongValue);
        }
        if (jSONLexer.token() == 3) {
            if (type == Double.TYPE || type == Double.class) {
                String strNumberString2 = jSONLexer.numberString();
                jSONLexer.nextToken(16);
                return (T) Double.valueOf(Double.parseDouble(strNumberString2));
            }
            ?? r11 = (T) jSONLexer.decimalValue();
            jSONLexer.nextToken(16);
            if (type != Short.TYPE && type != Short.class) {
                return (type == Byte.TYPE || type == Byte.class) ? (T) Byte.valueOf(r11.byteValue()) : r11;
            }
            if (r11.compareTo(BigDecimal.valueOf(32767L)) > 0 || r11.compareTo(BigDecimal.valueOf(-32768L)) < 0) {
                throw new JSONException("short overflow : " + ((Object) r11));
            }
            return (T) Short.valueOf(r11.shortValue());
        }
        Object obj2 = defaultJSONParser.parse();
        if (obj2 == null) {
            return null;
        }
        if (type == Double.TYPE || type == Double.class) {
            try {
                return (T) TypeUtils.castToDouble(obj2);
            } catch (Exception e2) {
                throw new JSONException("parseDouble error, field : " + obj, e2);
            }
        }
        if (type == Short.TYPE || type == Short.class) {
            try {
                return (T) TypeUtils.castToShort(obj2);
            } catch (Exception e3) {
                throw new JSONException("parseShort error, field : " + obj, e3);
            }
        }
        if (type == Byte.TYPE || type == Byte.class) {
            try {
                return (T) TypeUtils.castToByte(obj2);
            } catch (Exception e4) {
                throw new JSONException("parseByte error, field : " + obj, e4);
            }
        }
        return (T) TypeUtils.castToBigDecimal(obj2);
    }
}