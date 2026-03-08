package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.JSONPathException;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessable;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.ResolveFieldDeserializer;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public class DefaultJSONParser implements Closeable {
    public static final int NONE = 0;
    public static final int NeedToResolve = 1;
    public static final int TypeNameRedirect = 2;
    private static final Set<Class<?>> primitiveClasses = new HashSet();
    private String[] autoTypeAccept;
    private boolean autoTypeEnable;
    protected ParserConfig config;
    protected ParseContext context;
    private ParseContext[] contextArray;
    private int contextArrayIndex;
    private DateFormat dateFormat;
    private String dateFormatPattern;
    private List<ExtraProcessor> extraProcessors;
    private List<ExtraTypeProvider> extraTypeProviders;
    protected FieldTypeResolver fieldTypeResolver;
    public final Object input;
    protected transient BeanContext lastBeanContext;
    public final JSONLexer lexer;
    public int resolveStatus;
    private List<ResolveTask> resolveTaskList;
    public final SymbolTable symbolTable;

    static {
        for (Class<?> cls : new Class[]{Boolean.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Boolean.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, BigInteger.class, BigDecimal.class, String.class}) {
            primitiveClasses.add(cls);
        }
    }

    public String getDateFomartPattern() {
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null) {
            this.dateFormat = new SimpleDateFormat(this.dateFormatPattern, this.lexer.getLocale());
            this.dateFormat.setTimeZone(this.lexer.getTimeZone());
        }
        return this.dateFormat;
    }

    public void setDateFormat(String str) {
        this.dateFormatPattern = str;
        this.dateFormat = null;
    }

    public void setDateFomrat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DefaultJSONParser(String str) {
        this(str, ParserConfig.getGlobalInstance(), JSON.DEFAULT_PARSER_FEATURE);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig) {
        this(str, new JSONScanner(str, JSON.DEFAULT_PARSER_FEATURE), parserConfig);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig, int i) {
        this(str, new JSONScanner(str, i), parserConfig);
    }

    public DefaultJSONParser(char[] cArr, int i, ParserConfig parserConfig, int i2) {
        this(cArr, new JSONScanner(cArr, i, i2), parserConfig);
    }

    public DefaultJSONParser(JSONLexer jSONLexer) {
        this(jSONLexer, ParserConfig.getGlobalInstance());
    }

    public DefaultJSONParser(JSONLexer jSONLexer, ParserConfig parserConfig) {
        this((Object) null, jSONLexer, parserConfig);
    }

    public DefaultJSONParser(Object obj, JSONLexer jSONLexer, ParserConfig parserConfig) {
        this.dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
        this.contextArrayIndex = 0;
        this.resolveStatus = 0;
        this.extraTypeProviders = null;
        this.extraProcessors = null;
        this.fieldTypeResolver = null;
        this.autoTypeAccept = null;
        this.lexer = jSONLexer;
        this.input = obj;
        this.config = parserConfig;
        this.symbolTable = parserConfig.symbolTable;
        char current = jSONLexer.getCurrent();
        if (current == '{') {
            jSONLexer.next();
            ((JSONLexerBase) jSONLexer).token = 12;
        } else if (current == '[') {
            jSONLexer.next();
            ((JSONLexerBase) jSONLexer).token = 14;
        } else {
            jSONLexer.nextToken();
        }
    }

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public String getInput() {
        Object obj = this.input;
        if (obj instanceof char[]) {
            return new String((char[]) obj);
        }
        return obj.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:108:0x021e, code lost:
    
        r3.nextToken(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0229, code lost:
    
        if (r3.token() != 13) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x022b, code lost:
    
        r3.nextToken(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x022e, code lost:
    
        r0 = r16.config.getDeserializer(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0236, code lost:
    
        if ((r0 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) == false) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0238, code lost:
    
        r0 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r0;
        r2 = r0.createInstance(r16, r6);
        r3 = r7.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x024a, code lost:
    
        if (r3.hasNext() == false) goto L358;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x024c, code lost:
    
        r4 = (java.util.Map.Entry) r3.next();
        r7 = r4.getKey();
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0258, code lost:
    
        if ((r7 instanceof java.lang.String) == false) goto L362;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x025a, code lost:
    
        r7 = r0.getFieldDeserializer((java.lang.String) r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0260, code lost:
    
        if (r7 == null) goto L363;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0262, code lost:
    
        r7.setValue(r2, r4.getValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x026a, code lost:
    
        r2 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x026b, code lost:
    
        if (r2 != null) goto L130;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x026f, code lost:
    
        if (r6 != java.lang.Cloneable.class) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0271, code lost:
    
        r2 = new java.util.HashMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x027d, code lost:
    
        if ("java.util.Collections$EmptyMap".equals(r5) == false) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x027f, code lost:
    
        r2 = java.util.Collections.emptyMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0284, code lost:
    
        r2 = r6.newInstance();
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0288, code lost:
    
        setContext(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x028b, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x028c, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0294, code lost:
    
        throw new com.alibaba.fastjson.JSONException("create instance error", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x0295, code lost:
    
        setResolveStatus(2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x029b, code lost:
    
        if (r16.context == null) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x029d, code lost:
    
        if (r18 == null) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x02a1, code lost:
    
        if ((r18 instanceof java.lang.Integer) != false) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x02a9, code lost:
    
        if ((r16.context.fieldName instanceof java.lang.Integer) != false) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x02ab, code lost:
    
        popContext();
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x02b2, code lost:
    
        if (r17.size() <= 0) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x02b4, code lost:
    
        r0 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r17, (java.lang.Class<java.lang.Object>) r6, r16.config);
        parseObject(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x02bd, code lost:
    
        setContext(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x02c0, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x02c1, code lost:
    
        r0 = r16.config.getDeserializer(r6).deserialze(r16, r6, r18);
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x02cb, code lost:
    
        setContext(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x02ce, code lost:
    
        return r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01e8 A[Catch: all -> 0x059d, TryCatch #2 {all -> 0x059d, blocks: (B:24:0x006a, B:27:0x007d, B:31:0x0097, B:100:0x01e8, B:101:0x01ee, B:103:0x01f9, B:105:0x0201, B:107:0x0212, B:108:0x021e, B:110:0x022b, B:111:0x022e, B:113:0x0238, B:114:0x0246, B:116:0x024c, B:118:0x025a, B:120:0x0262, B:125:0x0271, B:126:0x0277, B:128:0x027f, B:129:0x0284, B:133:0x028d, B:134:0x0294, B:135:0x0295, B:138:0x029f, B:140:0x02a3, B:142:0x02ab, B:143:0x02ae, B:145:0x02b4, B:148:0x02c1, B:155:0x02d6, B:157:0x02de, B:159:0x02e9, B:161:0x02fa, B:163:0x02fe, B:165:0x0306, B:168:0x030b, B:170:0x030f, B:189:0x035f, B:191:0x0367, B:194:0x0370, B:195:0x0375, B:171:0x0314, B:173:0x031c, B:175:0x0320, B:176:0x0323, B:177:0x032f, B:180:0x0338, B:182:0x033c, B:183:0x033f, B:185:0x0343, B:186:0x0347, B:187:0x0353, B:196:0x0376, B:197:0x0394, B:199:0x0397, B:201:0x039b, B:203:0x03a1, B:205:0x03a7, B:206:0x03ab, B:211:0x03b5, B:217:0x03c5, B:219:0x03d4, B:221:0x03df, B:222:0x03e7, B:223:0x03ea, B:235:0x0416, B:237:0x041f, B:240:0x042a, B:243:0x043a, B:244:0x045a, B:230:0x03fa, B:232:0x0404, B:234:0x0413, B:233:0x0409, B:247:0x045f, B:249:0x0469, B:251:0x0471, B:252:0x0474, B:254:0x047f, B:255:0x0483, B:257:0x048e, B:260:0x0495, B:263:0x04a2, B:264:0x04a7, B:267:0x04ac, B:269:0x04b1, B:273:0x04bc, B:275:0x04c9, B:277:0x04cf, B:280:0x04d5, B:282:0x04db, B:284:0x04e3, B:287:0x04f2, B:290:0x04fa, B:291:0x04fc, B:293:0x0508, B:294:0x050b, B:296:0x0513, B:299:0x051d, B:302:0x0527, B:303:0x052c, B:304:0x0531, B:305:0x054b, B:306:0x054c, B:308:0x055e, B:311:0x0565, B:314:0x0570, B:315:0x0590, B:34:0x00a9, B:35:0x00c7, B:37:0x00ca, B:39:0x00d5, B:41:0x00d9, B:43:0x00df, B:45:0x00e5, B:46:0x00e8, B:53:0x00f7, B:55:0x00ff, B:58:0x010f, B:59:0x0127, B:60:0x0128, B:61:0x012d, B:71:0x0140, B:72:0x0146, B:74:0x014d, B:77:0x0157, B:80:0x015f, B:81:0x0179, B:75:0x0152, B:82:0x017a, B:83:0x0194, B:89:0x019e, B:91:0x01a6, B:94:0x01b7, B:95:0x01d7, B:96:0x01d8, B:97:0x01dd, B:98:0x01de, B:316:0x0591, B:317:0x0596, B:318:0x0597, B:319:0x059c), top: B:329:0x006a, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0397 A[Catch: all -> 0x059d, TryCatch #2 {all -> 0x059d, blocks: (B:24:0x006a, B:27:0x007d, B:31:0x0097, B:100:0x01e8, B:101:0x01ee, B:103:0x01f9, B:105:0x0201, B:107:0x0212, B:108:0x021e, B:110:0x022b, B:111:0x022e, B:113:0x0238, B:114:0x0246, B:116:0x024c, B:118:0x025a, B:120:0x0262, B:125:0x0271, B:126:0x0277, B:128:0x027f, B:129:0x0284, B:133:0x028d, B:134:0x0294, B:135:0x0295, B:138:0x029f, B:140:0x02a3, B:142:0x02ab, B:143:0x02ae, B:145:0x02b4, B:148:0x02c1, B:155:0x02d6, B:157:0x02de, B:159:0x02e9, B:161:0x02fa, B:163:0x02fe, B:165:0x0306, B:168:0x030b, B:170:0x030f, B:189:0x035f, B:191:0x0367, B:194:0x0370, B:195:0x0375, B:171:0x0314, B:173:0x031c, B:175:0x0320, B:176:0x0323, B:177:0x032f, B:180:0x0338, B:182:0x033c, B:183:0x033f, B:185:0x0343, B:186:0x0347, B:187:0x0353, B:196:0x0376, B:197:0x0394, B:199:0x0397, B:201:0x039b, B:203:0x03a1, B:205:0x03a7, B:206:0x03ab, B:211:0x03b5, B:217:0x03c5, B:219:0x03d4, B:221:0x03df, B:222:0x03e7, B:223:0x03ea, B:235:0x0416, B:237:0x041f, B:240:0x042a, B:243:0x043a, B:244:0x045a, B:230:0x03fa, B:232:0x0404, B:234:0x0413, B:233:0x0409, B:247:0x045f, B:249:0x0469, B:251:0x0471, B:252:0x0474, B:254:0x047f, B:255:0x0483, B:257:0x048e, B:260:0x0495, B:263:0x04a2, B:264:0x04a7, B:267:0x04ac, B:269:0x04b1, B:273:0x04bc, B:275:0x04c9, B:277:0x04cf, B:280:0x04d5, B:282:0x04db, B:284:0x04e3, B:287:0x04f2, B:290:0x04fa, B:291:0x04fc, B:293:0x0508, B:294:0x050b, B:296:0x0513, B:299:0x051d, B:302:0x0527, B:303:0x052c, B:304:0x0531, B:305:0x054b, B:306:0x054c, B:308:0x055e, B:311:0x0565, B:314:0x0570, B:315:0x0590, B:34:0x00a9, B:35:0x00c7, B:37:0x00ca, B:39:0x00d5, B:41:0x00d9, B:43:0x00df, B:45:0x00e5, B:46:0x00e8, B:53:0x00f7, B:55:0x00ff, B:58:0x010f, B:59:0x0127, B:60:0x0128, B:61:0x012d, B:71:0x0140, B:72:0x0146, B:74:0x014d, B:77:0x0157, B:80:0x015f, B:81:0x0179, B:75:0x0152, B:82:0x017a, B:83:0x0194, B:89:0x019e, B:91:0x01a6, B:94:0x01b7, B:95:0x01d7, B:96:0x01d8, B:97:0x01dd, B:98:0x01de, B:316:0x0591, B:317:0x0596, B:318:0x0597, B:319:0x059c), top: B:329:0x006a, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:217:0x03c5 A[Catch: all -> 0x059d, TryCatch #2 {all -> 0x059d, blocks: (B:24:0x006a, B:27:0x007d, B:31:0x0097, B:100:0x01e8, B:101:0x01ee, B:103:0x01f9, B:105:0x0201, B:107:0x0212, B:108:0x021e, B:110:0x022b, B:111:0x022e, B:113:0x0238, B:114:0x0246, B:116:0x024c, B:118:0x025a, B:120:0x0262, B:125:0x0271, B:126:0x0277, B:128:0x027f, B:129:0x0284, B:133:0x028d, B:134:0x0294, B:135:0x0295, B:138:0x029f, B:140:0x02a3, B:142:0x02ab, B:143:0x02ae, B:145:0x02b4, B:148:0x02c1, B:155:0x02d6, B:157:0x02de, B:159:0x02e9, B:161:0x02fa, B:163:0x02fe, B:165:0x0306, B:168:0x030b, B:170:0x030f, B:189:0x035f, B:191:0x0367, B:194:0x0370, B:195:0x0375, B:171:0x0314, B:173:0x031c, B:175:0x0320, B:176:0x0323, B:177:0x032f, B:180:0x0338, B:182:0x033c, B:183:0x033f, B:185:0x0343, B:186:0x0347, B:187:0x0353, B:196:0x0376, B:197:0x0394, B:199:0x0397, B:201:0x039b, B:203:0x03a1, B:205:0x03a7, B:206:0x03ab, B:211:0x03b5, B:217:0x03c5, B:219:0x03d4, B:221:0x03df, B:222:0x03e7, B:223:0x03ea, B:235:0x0416, B:237:0x041f, B:240:0x042a, B:243:0x043a, B:244:0x045a, B:230:0x03fa, B:232:0x0404, B:234:0x0413, B:233:0x0409, B:247:0x045f, B:249:0x0469, B:251:0x0471, B:252:0x0474, B:254:0x047f, B:255:0x0483, B:257:0x048e, B:260:0x0495, B:263:0x04a2, B:264:0x04a7, B:267:0x04ac, B:269:0x04b1, B:273:0x04bc, B:275:0x04c9, B:277:0x04cf, B:280:0x04d5, B:282:0x04db, B:284:0x04e3, B:287:0x04f2, B:290:0x04fa, B:291:0x04fc, B:293:0x0508, B:294:0x050b, B:296:0x0513, B:299:0x051d, B:302:0x0527, B:303:0x052c, B:304:0x0531, B:305:0x054b, B:306:0x054c, B:308:0x055e, B:311:0x0565, B:314:0x0570, B:315:0x0590, B:34:0x00a9, B:35:0x00c7, B:37:0x00ca, B:39:0x00d5, B:41:0x00d9, B:43:0x00df, B:45:0x00e5, B:46:0x00e8, B:53:0x00f7, B:55:0x00ff, B:58:0x010f, B:59:0x0127, B:60:0x0128, B:61:0x012d, B:71:0x0140, B:72:0x0146, B:74:0x014d, B:77:0x0157, B:80:0x015f, B:81:0x0179, B:75:0x0152, B:82:0x017a, B:83:0x0194, B:89:0x019e, B:91:0x01a6, B:94:0x01b7, B:95:0x01d7, B:96:0x01d8, B:97:0x01dd, B:98:0x01de, B:316:0x0591, B:317:0x0596, B:318:0x0597, B:319:0x059c), top: B:329:0x006a, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:224:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x041f A[Catch: all -> 0x059d, TryCatch #2 {all -> 0x059d, blocks: (B:24:0x006a, B:27:0x007d, B:31:0x0097, B:100:0x01e8, B:101:0x01ee, B:103:0x01f9, B:105:0x0201, B:107:0x0212, B:108:0x021e, B:110:0x022b, B:111:0x022e, B:113:0x0238, B:114:0x0246, B:116:0x024c, B:118:0x025a, B:120:0x0262, B:125:0x0271, B:126:0x0277, B:128:0x027f, B:129:0x0284, B:133:0x028d, B:134:0x0294, B:135:0x0295, B:138:0x029f, B:140:0x02a3, B:142:0x02ab, B:143:0x02ae, B:145:0x02b4, B:148:0x02c1, B:155:0x02d6, B:157:0x02de, B:159:0x02e9, B:161:0x02fa, B:163:0x02fe, B:165:0x0306, B:168:0x030b, B:170:0x030f, B:189:0x035f, B:191:0x0367, B:194:0x0370, B:195:0x0375, B:171:0x0314, B:173:0x031c, B:175:0x0320, B:176:0x0323, B:177:0x032f, B:180:0x0338, B:182:0x033c, B:183:0x033f, B:185:0x0343, B:186:0x0347, B:187:0x0353, B:196:0x0376, B:197:0x0394, B:199:0x0397, B:201:0x039b, B:203:0x03a1, B:205:0x03a7, B:206:0x03ab, B:211:0x03b5, B:217:0x03c5, B:219:0x03d4, B:221:0x03df, B:222:0x03e7, B:223:0x03ea, B:235:0x0416, B:237:0x041f, B:240:0x042a, B:243:0x043a, B:244:0x045a, B:230:0x03fa, B:232:0x0404, B:234:0x0413, B:233:0x0409, B:247:0x045f, B:249:0x0469, B:251:0x0471, B:252:0x0474, B:254:0x047f, B:255:0x0483, B:257:0x048e, B:260:0x0495, B:263:0x04a2, B:264:0x04a7, B:267:0x04ac, B:269:0x04b1, B:273:0x04bc, B:275:0x04c9, B:277:0x04cf, B:280:0x04d5, B:282:0x04db, B:284:0x04e3, B:287:0x04f2, B:290:0x04fa, B:291:0x04fc, B:293:0x0508, B:294:0x050b, B:296:0x0513, B:299:0x051d, B:302:0x0527, B:303:0x052c, B:304:0x0531, B:305:0x054b, B:306:0x054c, B:308:0x055e, B:311:0x0565, B:314:0x0570, B:315:0x0590, B:34:0x00a9, B:35:0x00c7, B:37:0x00ca, B:39:0x00d5, B:41:0x00d9, B:43:0x00df, B:45:0x00e5, B:46:0x00e8, B:53:0x00f7, B:55:0x00ff, B:58:0x010f, B:59:0x0127, B:60:0x0128, B:61:0x012d, B:71:0x0140, B:72:0x0146, B:74:0x014d, B:77:0x0157, B:80:0x015f, B:81:0x0179, B:75:0x0152, B:82:0x017a, B:83:0x0194, B:89:0x019e, B:91:0x01a6, B:94:0x01b7, B:95:0x01d7, B:96:0x01d8, B:97:0x01dd, B:98:0x01de, B:316:0x0591, B:317:0x0596, B:318:0x0597, B:319:0x059c), top: B:329:0x006a, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:285:0x04ee  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x04f2 A[Catch: all -> 0x059d, TryCatch #2 {all -> 0x059d, blocks: (B:24:0x006a, B:27:0x007d, B:31:0x0097, B:100:0x01e8, B:101:0x01ee, B:103:0x01f9, B:105:0x0201, B:107:0x0212, B:108:0x021e, B:110:0x022b, B:111:0x022e, B:113:0x0238, B:114:0x0246, B:116:0x024c, B:118:0x025a, B:120:0x0262, B:125:0x0271, B:126:0x0277, B:128:0x027f, B:129:0x0284, B:133:0x028d, B:134:0x0294, B:135:0x0295, B:138:0x029f, B:140:0x02a3, B:142:0x02ab, B:143:0x02ae, B:145:0x02b4, B:148:0x02c1, B:155:0x02d6, B:157:0x02de, B:159:0x02e9, B:161:0x02fa, B:163:0x02fe, B:165:0x0306, B:168:0x030b, B:170:0x030f, B:189:0x035f, B:191:0x0367, B:194:0x0370, B:195:0x0375, B:171:0x0314, B:173:0x031c, B:175:0x0320, B:176:0x0323, B:177:0x032f, B:180:0x0338, B:182:0x033c, B:183:0x033f, B:185:0x0343, B:186:0x0347, B:187:0x0353, B:196:0x0376, B:197:0x0394, B:199:0x0397, B:201:0x039b, B:203:0x03a1, B:205:0x03a7, B:206:0x03ab, B:211:0x03b5, B:217:0x03c5, B:219:0x03d4, B:221:0x03df, B:222:0x03e7, B:223:0x03ea, B:235:0x0416, B:237:0x041f, B:240:0x042a, B:243:0x043a, B:244:0x045a, B:230:0x03fa, B:232:0x0404, B:234:0x0413, B:233:0x0409, B:247:0x045f, B:249:0x0469, B:251:0x0471, B:252:0x0474, B:254:0x047f, B:255:0x0483, B:257:0x048e, B:260:0x0495, B:263:0x04a2, B:264:0x04a7, B:267:0x04ac, B:269:0x04b1, B:273:0x04bc, B:275:0x04c9, B:277:0x04cf, B:280:0x04d5, B:282:0x04db, B:284:0x04e3, B:287:0x04f2, B:290:0x04fa, B:291:0x04fc, B:293:0x0508, B:294:0x050b, B:296:0x0513, B:299:0x051d, B:302:0x0527, B:303:0x052c, B:304:0x0531, B:305:0x054b, B:306:0x054c, B:308:0x055e, B:311:0x0565, B:314:0x0570, B:315:0x0590, B:34:0x00a9, B:35:0x00c7, B:37:0x00ca, B:39:0x00d5, B:41:0x00d9, B:43:0x00df, B:45:0x00e5, B:46:0x00e8, B:53:0x00f7, B:55:0x00ff, B:58:0x010f, B:59:0x0127, B:60:0x0128, B:61:0x012d, B:71:0x0140, B:72:0x0146, B:74:0x014d, B:77:0x0157, B:80:0x015f, B:81:0x0179, B:75:0x0152, B:82:0x017a, B:83:0x0194, B:89:0x019e, B:91:0x01a6, B:94:0x01b7, B:95:0x01d7, B:96:0x01d8, B:97:0x01dd, B:98:0x01de, B:316:0x0591, B:317:0x0596, B:318:0x0597, B:319:0x059c), top: B:329:0x006a, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0508 A[Catch: all -> 0x059d, TryCatch #2 {all -> 0x059d, blocks: (B:24:0x006a, B:27:0x007d, B:31:0x0097, B:100:0x01e8, B:101:0x01ee, B:103:0x01f9, B:105:0x0201, B:107:0x0212, B:108:0x021e, B:110:0x022b, B:111:0x022e, B:113:0x0238, B:114:0x0246, B:116:0x024c, B:118:0x025a, B:120:0x0262, B:125:0x0271, B:126:0x0277, B:128:0x027f, B:129:0x0284, B:133:0x028d, B:134:0x0294, B:135:0x0295, B:138:0x029f, B:140:0x02a3, B:142:0x02ab, B:143:0x02ae, B:145:0x02b4, B:148:0x02c1, B:155:0x02d6, B:157:0x02de, B:159:0x02e9, B:161:0x02fa, B:163:0x02fe, B:165:0x0306, B:168:0x030b, B:170:0x030f, B:189:0x035f, B:191:0x0367, B:194:0x0370, B:195:0x0375, B:171:0x0314, B:173:0x031c, B:175:0x0320, B:176:0x0323, B:177:0x032f, B:180:0x0338, B:182:0x033c, B:183:0x033f, B:185:0x0343, B:186:0x0347, B:187:0x0353, B:196:0x0376, B:197:0x0394, B:199:0x0397, B:201:0x039b, B:203:0x03a1, B:205:0x03a7, B:206:0x03ab, B:211:0x03b5, B:217:0x03c5, B:219:0x03d4, B:221:0x03df, B:222:0x03e7, B:223:0x03ea, B:235:0x0416, B:237:0x041f, B:240:0x042a, B:243:0x043a, B:244:0x045a, B:230:0x03fa, B:232:0x0404, B:234:0x0413, B:233:0x0409, B:247:0x045f, B:249:0x0469, B:251:0x0471, B:252:0x0474, B:254:0x047f, B:255:0x0483, B:257:0x048e, B:260:0x0495, B:263:0x04a2, B:264:0x04a7, B:267:0x04ac, B:269:0x04b1, B:273:0x04bc, B:275:0x04c9, B:277:0x04cf, B:280:0x04d5, B:282:0x04db, B:284:0x04e3, B:287:0x04f2, B:290:0x04fa, B:291:0x04fc, B:293:0x0508, B:294:0x050b, B:296:0x0513, B:299:0x051d, B:302:0x0527, B:303:0x052c, B:304:0x0531, B:305:0x054b, B:306:0x054c, B:308:0x055e, B:311:0x0565, B:314:0x0570, B:315:0x0590, B:34:0x00a9, B:35:0x00c7, B:37:0x00ca, B:39:0x00d5, B:41:0x00d9, B:43:0x00df, B:45:0x00e5, B:46:0x00e8, B:53:0x00f7, B:55:0x00ff, B:58:0x010f, B:59:0x0127, B:60:0x0128, B:61:0x012d, B:71:0x0140, B:72:0x0146, B:74:0x014d, B:77:0x0157, B:80:0x015f, B:81:0x0179, B:75:0x0152, B:82:0x017a, B:83:0x0194, B:89:0x019e, B:91:0x01a6, B:94:0x01b7, B:95:0x01d7, B:96:0x01d8, B:97:0x01dd, B:98:0x01de, B:316:0x0591, B:317:0x0596, B:318:0x0597, B:319:0x059c), top: B:329:0x006a, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:299:0x051d A[Catch: all -> 0x059d, TRY_ENTER, TryCatch #2 {all -> 0x059d, blocks: (B:24:0x006a, B:27:0x007d, B:31:0x0097, B:100:0x01e8, B:101:0x01ee, B:103:0x01f9, B:105:0x0201, B:107:0x0212, B:108:0x021e, B:110:0x022b, B:111:0x022e, B:113:0x0238, B:114:0x0246, B:116:0x024c, B:118:0x025a, B:120:0x0262, B:125:0x0271, B:126:0x0277, B:128:0x027f, B:129:0x0284, B:133:0x028d, B:134:0x0294, B:135:0x0295, B:138:0x029f, B:140:0x02a3, B:142:0x02ab, B:143:0x02ae, B:145:0x02b4, B:148:0x02c1, B:155:0x02d6, B:157:0x02de, B:159:0x02e9, B:161:0x02fa, B:163:0x02fe, B:165:0x0306, B:168:0x030b, B:170:0x030f, B:189:0x035f, B:191:0x0367, B:194:0x0370, B:195:0x0375, B:171:0x0314, B:173:0x031c, B:175:0x0320, B:176:0x0323, B:177:0x032f, B:180:0x0338, B:182:0x033c, B:183:0x033f, B:185:0x0343, B:186:0x0347, B:187:0x0353, B:196:0x0376, B:197:0x0394, B:199:0x0397, B:201:0x039b, B:203:0x03a1, B:205:0x03a7, B:206:0x03ab, B:211:0x03b5, B:217:0x03c5, B:219:0x03d4, B:221:0x03df, B:222:0x03e7, B:223:0x03ea, B:235:0x0416, B:237:0x041f, B:240:0x042a, B:243:0x043a, B:244:0x045a, B:230:0x03fa, B:232:0x0404, B:234:0x0413, B:233:0x0409, B:247:0x045f, B:249:0x0469, B:251:0x0471, B:252:0x0474, B:254:0x047f, B:255:0x0483, B:257:0x048e, B:260:0x0495, B:263:0x04a2, B:264:0x04a7, B:267:0x04ac, B:269:0x04b1, B:273:0x04bc, B:275:0x04c9, B:277:0x04cf, B:280:0x04d5, B:282:0x04db, B:284:0x04e3, B:287:0x04f2, B:290:0x04fa, B:291:0x04fc, B:293:0x0508, B:294:0x050b, B:296:0x0513, B:299:0x051d, B:302:0x0527, B:303:0x052c, B:304:0x0531, B:305:0x054b, B:306:0x054c, B:308:0x055e, B:311:0x0565, B:314:0x0570, B:315:0x0590, B:34:0x00a9, B:35:0x00c7, B:37:0x00ca, B:39:0x00d5, B:41:0x00d9, B:43:0x00df, B:45:0x00e5, B:46:0x00e8, B:53:0x00f7, B:55:0x00ff, B:58:0x010f, B:59:0x0127, B:60:0x0128, B:61:0x012d, B:71:0x0140, B:72:0x0146, B:74:0x014d, B:77:0x0157, B:80:0x015f, B:81:0x0179, B:75:0x0152, B:82:0x017a, B:83:0x0194, B:89:0x019e, B:91:0x01a6, B:94:0x01b7, B:95:0x01d7, B:96:0x01d8, B:97:0x01dd, B:98:0x01de, B:316:0x0591, B:317:0x0596, B:318:0x0597, B:319:0x059c), top: B:329:0x006a, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:346:0x0513 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:352:0x0428 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object parseObject(java.util.Map r17, java.lang.Object r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1445
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(java.util.Map, java.lang.Object):java.lang.Object");
    }

    public ParserConfig getConfig() {
        return this.config;
    }

    public void setConfig(ParserConfig parserConfig) {
        this.config = parserConfig;
    }

    public <T> T parseObject(Class<T> cls) {
        return (T) parseObject(cls, (Object) null);
    }

    public <T> T parseObject(Type type) {
        return (T) parseObject(type, (Object) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T parseObject(Type type, Object obj) {
        int i = this.lexer.token();
        if (i == 8) {
            this.lexer.nextToken();
            return null;
        }
        if (i == 4) {
            if (type == byte[].class) {
                T t = (T) this.lexer.bytesValue();
                this.lexer.nextToken();
                return t;
            }
            if (type == char[].class) {
                String strStringVal = this.lexer.stringVal();
                this.lexer.nextToken();
                return (T) strStringVal.toCharArray();
            }
        }
        try {
            return (T) this.config.getDeserializer(type).deserialze(this, type, obj);
        } catch (JSONException e2) {
            throw e2;
        } catch (Throwable th) {
            throw new JSONException(th.getMessage(), th);
        }
    }

    public <T> List<T> parseArray(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        parseArray((Class<?>) cls, (Collection) arrayList);
        return arrayList;
    }

    public void parseArray(Class<?> cls, Collection collection) {
        parseArray((Type) cls, collection);
    }

    public void parseArray(Type type, Collection collection) {
        parseArray(type, collection, null);
    }

    public void parseArray(Type type, Collection collection, Object obj) {
        ObjectDeserializer deserializer;
        int i = this.lexer.token();
        if (i == 21 || i == 22) {
            this.lexer.nextToken();
            i = this.lexer.token();
        }
        if (i != 14) {
            throw new JSONException("exepct '[', but " + JSONToken.name(i) + ", " + this.lexer.info());
        }
        if (Integer.TYPE == type) {
            deserializer = IntegerCodec.instance;
            this.lexer.nextToken(2);
        } else if (String.class == type) {
            deserializer = StringCodec.instance;
            this.lexer.nextToken(4);
        } else {
            deserializer = this.config.getDeserializer(type);
            this.lexer.nextToken(deserializer.getFastMatchToken());
        }
        ParseContext parseContext = this.context;
        setContext(collection, obj);
        int i2 = 0;
        while (true) {
            try {
                if (this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    while (this.lexer.token() == 16) {
                        this.lexer.nextToken();
                    }
                }
                if (this.lexer.token() != 15) {
                    Object objDeserialze = null;
                    if (Integer.TYPE == type) {
                        collection.add(IntegerCodec.instance.deserialze(this, null, null));
                    } else if (String.class == type) {
                        if (this.lexer.token() == 4) {
                            objDeserialze = this.lexer.stringVal();
                            this.lexer.nextToken(16);
                        } else {
                            Object obj2 = parse();
                            if (obj2 != null) {
                                objDeserialze = obj2.toString();
                            }
                        }
                        collection.add(objDeserialze);
                    } else {
                        if (this.lexer.token() == 8) {
                            this.lexer.nextToken();
                        } else {
                            objDeserialze = deserializer.deserialze(this, type, Integer.valueOf(i2));
                        }
                        collection.add(objDeserialze);
                        checkListResolve(collection);
                    }
                    if (this.lexer.token() == 16) {
                        this.lexer.nextToken(deserializer.getFastMatchToken());
                    }
                    i2++;
                } else {
                    setContext(parseContext);
                    this.lexer.nextToken(16);
                    return;
                }
            } catch (Throwable th) {
                setContext(parseContext);
                throw th;
            }
        }
    }

    public Object[] parseArray(Type[] typeArr) {
        Object objCast;
        Class<?> componentType;
        boolean zIsArray;
        Object objCast2;
        int i = 8;
        if (this.lexer.token() == 8) {
            this.lexer.nextToken(16);
            return null;
        }
        int i2 = 14;
        if (this.lexer.token() != 14) {
            throw new JSONException("syntax error : " + this.lexer.tokenName());
        }
        Object[] objArr = new Object[typeArr.length];
        if (typeArr.length == 0) {
            this.lexer.nextToken(15);
            if (this.lexer.token() != 15) {
                throw new JSONException("syntax error");
            }
            this.lexer.nextToken(16);
            return new Object[0];
        }
        this.lexer.nextToken(2);
        int i3 = 0;
        while (i3 < typeArr.length) {
            if (this.lexer.token() == i) {
                this.lexer.nextToken(16);
                objCast = null;
            } else {
                Type type = typeArr[i3];
                if (type == Integer.TYPE || type == Integer.class) {
                    if (this.lexer.token() == 2) {
                        objCast = Integer.valueOf(this.lexer.intValue());
                        this.lexer.nextToken(16);
                    } else {
                        objCast = TypeUtils.cast(parse(), type, this.config);
                    }
                } else if (type == String.class) {
                    if (this.lexer.token() == 4) {
                        objCast2 = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        objCast2 = TypeUtils.cast(parse(), type, this.config);
                    }
                    objCast = objCast2;
                } else {
                    if (i3 == typeArr.length - 1 && (type instanceof Class)) {
                        Class cls = (Class) type;
                        zIsArray = cls.isArray();
                        componentType = cls.getComponentType();
                    } else {
                        componentType = null;
                        zIsArray = false;
                    }
                    if (zIsArray && this.lexer.token() != i2) {
                        ArrayList arrayList = new ArrayList();
                        ObjectDeserializer deserializer = this.config.getDeserializer(componentType);
                        int fastMatchToken = deserializer.getFastMatchToken();
                        if (this.lexer.token() != 15) {
                            while (true) {
                                arrayList.add(deserializer.deserialze(this, type, null));
                                if (this.lexer.token() != 16) {
                                    break;
                                }
                                this.lexer.nextToken(fastMatchToken);
                            }
                            if (this.lexer.token() != 15) {
                                throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
                            }
                        }
                        objCast = TypeUtils.cast(arrayList, type, this.config);
                    } else {
                        objCast = this.config.getDeserializer(type).deserialze(this, type, Integer.valueOf(i3));
                    }
                }
            }
            objArr[i3] = objCast;
            if (this.lexer.token() == 15) {
                break;
            }
            if (this.lexer.token() != 16) {
                throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
            }
            if (i3 == typeArr.length - 1) {
                this.lexer.nextToken(15);
            } else {
                this.lexer.nextToken(2);
            }
            i3++;
            i = 8;
            i2 = 14;
        }
        if (this.lexer.token() != 15) {
            throw new JSONException("syntax error");
        }
        this.lexer.nextToken(16);
        return objArr;
    }

    public void parseObject(Object obj) {
        Object objDeserialze;
        Class<?> cls = obj.getClass();
        ObjectDeserializer deserializer = this.config.getDeserializer(cls);
        JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
        if (this.lexer.token() != 12 && this.lexer.token() != 16) {
            throw new JSONException("syntax error, expect {, actual " + this.lexer.tokenName());
        }
        while (true) {
            String strScanSymbol = this.lexer.scanSymbol(this.symbolTable);
            if (strScanSymbol == null) {
                if (this.lexer.token() == 13) {
                    this.lexer.nextToken(16);
                    return;
                } else if (this.lexer.token() != 16 || !this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                }
            }
            FieldDeserializer fieldDeserializer = javaBeanDeserializer != null ? javaBeanDeserializer.getFieldDeserializer(strScanSymbol) : null;
            if (fieldDeserializer == null) {
                if (!this.lexer.isEnabled(Feature.IgnoreNotMatch)) {
                    throw new JSONException("setter not found, class " + cls.getName() + ", property " + strScanSymbol);
                }
                this.lexer.nextTokenWithColon();
                parse();
                if (this.lexer.token() == 13) {
                    this.lexer.nextToken();
                    return;
                }
            } else {
                Class<?> cls2 = fieldDeserializer.fieldInfo.fieldClass;
                Type type = fieldDeserializer.fieldInfo.fieldType;
                if (cls2 == Integer.TYPE) {
                    this.lexer.nextTokenWithColon(2);
                    objDeserialze = IntegerCodec.instance.deserialze(this, type, null);
                } else if (cls2 == String.class) {
                    this.lexer.nextTokenWithColon(4);
                    objDeserialze = StringCodec.deserialze(this);
                } else if (cls2 == Long.TYPE) {
                    this.lexer.nextTokenWithColon(2);
                    objDeserialze = LongCodec.instance.deserialze(this, type, null);
                } else {
                    ObjectDeserializer deserializer2 = this.config.getDeserializer(cls2, type);
                    this.lexer.nextTokenWithColon(deserializer2.getFastMatchToken());
                    objDeserialze = deserializer2.deserialze(this, type, null);
                }
                fieldDeserializer.setValue(obj, objDeserialze);
                if (this.lexer.token() != 16 && this.lexer.token() == 13) {
                    this.lexer.nextToken(16);
                    return;
                }
            }
        }
    }

    public Object parseArrayWithType(Type type) {
        if (this.lexer.token() == 8) {
            this.lexer.nextToken();
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypeArguments.length != 1) {
            throw new JSONException("not support type " + type);
        }
        Type type2 = actualTypeArguments[0];
        if (type2 instanceof Class) {
            ArrayList arrayList = new ArrayList();
            parseArray((Class<?>) type2, (Collection) arrayList);
            return arrayList;
        }
        if (type2 instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type2;
            Type type3 = wildcardType.getUpperBounds()[0];
            if (Object.class.equals(type3)) {
                if (wildcardType.getLowerBounds().length == 0) {
                    return parse();
                }
                throw new JSONException("not support type : " + type);
            }
            ArrayList arrayList2 = new ArrayList();
            parseArray((Class<?>) type3, (Collection) arrayList2);
            return arrayList2;
        }
        if (type2 instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type2;
            Type[] bounds = typeVariable.getBounds();
            if (bounds.length != 1) {
                throw new JSONException("not support : " + typeVariable);
            }
            Type type4 = bounds[0];
            if (type4 instanceof Class) {
                ArrayList arrayList3 = new ArrayList();
                parseArray((Class<?>) type4, (Collection) arrayList3);
                return arrayList3;
            }
        }
        if (type2 instanceof ParameterizedType) {
            ArrayList arrayList4 = new ArrayList();
            parseArray((ParameterizedType) type2, arrayList4);
            return arrayList4;
        }
        throw new JSONException("TODO : " + type);
    }

    public void acceptType(String str) {
        JSONLexer jSONLexer = this.lexer;
        jSONLexer.nextTokenWithColon();
        if (jSONLexer.token() != 4) {
            throw new JSONException("type not match error");
        }
        if (str.equals(jSONLexer.stringVal())) {
            jSONLexer.nextToken();
            if (jSONLexer.token() == 16) {
                jSONLexer.nextToken();
                return;
            }
            return;
        }
        throw new JSONException("type not match error");
    }

    public int getResolveStatus() {
        return this.resolveStatus;
    }

    public void setResolveStatus(int i) {
        this.resolveStatus = i;
    }

    public Object getObject(String str) {
        for (int i = 0; i < this.contextArrayIndex; i++) {
            if (str.equals(this.contextArray[i].toString())) {
                return this.contextArray[i].object;
            }
        }
        return null;
    }

    public void checkListResolve(Collection collection) {
        if (this.resolveStatus == 1) {
            if (collection instanceof List) {
                int size = collection.size() - 1;
                ResolveTask lastResolveTask = getLastResolveTask();
                lastResolveTask.fieldDeserializer = new ResolveFieldDeserializer(this, (List) collection, size);
                lastResolveTask.ownerContext = this.context;
                setResolveStatus(0);
                return;
            }
            ResolveTask lastResolveTask2 = getLastResolveTask();
            lastResolveTask2.fieldDeserializer = new ResolveFieldDeserializer(collection);
            lastResolveTask2.ownerContext = this.context;
            setResolveStatus(0);
        }
    }

    public void checkMapResolve(Map map, Object obj) {
        if (this.resolveStatus == 1) {
            ResolveFieldDeserializer resolveFieldDeserializer = new ResolveFieldDeserializer(map, obj);
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.fieldDeserializer = resolveFieldDeserializer;
            lastResolveTask.ownerContext = this.context;
            setResolveStatus(0);
        }
    }

    public Object parseObject(Map map) {
        return parseObject(map, (Object) null);
    }

    public JSONObject parseObject() {
        return (JSONObject) parseObject((Map) new JSONObject(this.lexer.isEnabled(Feature.OrderedField)));
    }

    public final void parseArray(Collection collection) {
        parseArray(collection, (Object) null);
    }

    public final void parseArray(Collection collection, Object obj) {
        Number numberDecimalValue;
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == 21 || jSONLexer.token() == 22) {
            jSONLexer.nextToken();
        }
        if (jSONLexer.token() != 14) {
            throw new JSONException("syntax error, expect [, actual " + JSONToken.name(jSONLexer.token()) + ", pos " + jSONLexer.pos() + ", fieldName " + obj);
        }
        jSONLexer.nextToken(4);
        ParseContext parseContext = this.context;
        setContext(collection, obj);
        int i = 0;
        while (true) {
            try {
                if (jSONLexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    while (jSONLexer.token() == 16) {
                        jSONLexer.nextToken();
                    }
                }
                int i2 = jSONLexer.token();
                Object object = null;
                object = null;
                if (i2 == 2) {
                    Number numberIntegerValue = jSONLexer.integerValue();
                    jSONLexer.nextToken(16);
                    object = numberIntegerValue;
                } else if (i2 == 3) {
                    if (jSONLexer.isEnabled(Feature.UseBigDecimal)) {
                        numberDecimalValue = jSONLexer.decimalValue(true);
                    } else {
                        numberDecimalValue = jSONLexer.decimalValue(false);
                    }
                    object = numberDecimalValue;
                    jSONLexer.nextToken(16);
                } else if (i2 == 4) {
                    String strStringVal = jSONLexer.stringVal();
                    jSONLexer.nextToken(16);
                    object = strStringVal;
                    if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                        JSONScanner jSONScanner = new JSONScanner(strStringVal);
                        Object time = strStringVal;
                        if (jSONScanner.scanISO8601DateIfMatch()) {
                            time = jSONScanner.getCalendar().getTime();
                        }
                        jSONScanner.close();
                        object = time;
                    }
                } else if (i2 == 6) {
                    Boolean bool = Boolean.TRUE;
                    jSONLexer.nextToken(16);
                    object = bool;
                } else if (i2 == 7) {
                    Boolean bool2 = Boolean.FALSE;
                    jSONLexer.nextToken(16);
                    object = bool2;
                } else if (i2 == 8) {
                    jSONLexer.nextToken(4);
                } else if (i2 == 12) {
                    object = parseObject(new JSONObject(jSONLexer.isEnabled(Feature.OrderedField)), Integer.valueOf(i));
                } else {
                    if (i2 == 20) {
                        throw new JSONException("unclosed jsonArray");
                    }
                    if (i2 == 23) {
                        jSONLexer.nextToken(4);
                    } else if (i2 == 14) {
                        JSONArray jSONArray = new JSONArray();
                        parseArray(jSONArray, Integer.valueOf(i));
                        object = jSONArray;
                        if (jSONLexer.isEnabled(Feature.UseObjectArray)) {
                            object = jSONArray.toArray();
                        }
                    } else {
                        if (i2 == 15) {
                            jSONLexer.nextToken(16);
                            return;
                        }
                        object = parse();
                    }
                }
                collection.add(object);
                checkListResolve(collection);
                if (jSONLexer.token() == 16) {
                    jSONLexer.nextToken(4);
                }
                i++;
            } finally {
                setContext(parseContext);
            }
        }
    }

    public ParseContext getContext() {
        return this.context;
    }

    public List<ResolveTask> getResolveTaskList() {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        return this.resolveTaskList;
    }

    public void addResolveTask(ResolveTask resolveTask) {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        this.resolveTaskList.add(resolveTask);
    }

    public ResolveTask getLastResolveTask() {
        return this.resolveTaskList.get(r0.size() - 1);
    }

    public List<ExtraProcessor> getExtraProcessors() {
        if (this.extraProcessors == null) {
            this.extraProcessors = new ArrayList(2);
        }
        return this.extraProcessors;
    }

    public List<ExtraTypeProvider> getExtraTypeProviders() {
        if (this.extraTypeProviders == null) {
            this.extraTypeProviders = new ArrayList(2);
        }
        return this.extraTypeProviders;
    }

    public FieldTypeResolver getFieldTypeResolver() {
        return this.fieldTypeResolver;
    }

    public void setFieldTypeResolver(FieldTypeResolver fieldTypeResolver) {
        this.fieldTypeResolver = fieldTypeResolver;
    }

    public void setContext(ParseContext parseContext) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.context = parseContext;
    }

    public void popContext() {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.context = this.context.parent;
        int i = this.contextArrayIndex;
        if (i <= 0) {
            return;
        }
        this.contextArrayIndex = i - 1;
        this.contextArray[this.contextArrayIndex] = null;
    }

    public ParseContext setContext(Object obj, Object obj2) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        return setContext(this.context, obj, obj2);
    }

    public ParseContext setContext(ParseContext parseContext, Object obj, Object obj2) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        this.context = new ParseContext(parseContext, obj, obj2);
        addContext(this.context);
        return this.context;
    }

    private void addContext(ParseContext parseContext) {
        int i = this.contextArrayIndex;
        this.contextArrayIndex = i + 1;
        ParseContext[] parseContextArr = this.contextArray;
        if (parseContextArr == null) {
            this.contextArray = new ParseContext[8];
        } else if (i >= parseContextArr.length) {
            ParseContext[] parseContextArr2 = new ParseContext[(parseContextArr.length * 3) / 2];
            System.arraycopy(parseContextArr, 0, parseContextArr2, 0, parseContextArr.length);
            this.contextArray = parseContextArr2;
        }
        this.contextArray[i] = parseContext;
    }

    public Object parse() {
        return parse(null);
    }

    public Object parseKey() {
        if (this.lexer.token() == 18) {
            String strStringVal = this.lexer.stringVal();
            this.lexer.nextToken(16);
            return strStringVal;
        }
        return parse(null);
    }

    public Object parse(Object obj) {
        JSONLexer jSONLexer = this.lexer;
        int i = jSONLexer.token();
        if (i == 2) {
            Number numberIntegerValue = jSONLexer.integerValue();
            jSONLexer.nextToken();
            return numberIntegerValue;
        }
        if (i == 3) {
            Number numberDecimalValue = jSONLexer.decimalValue(jSONLexer.isEnabled(Feature.UseBigDecimal));
            jSONLexer.nextToken();
            return numberDecimalValue;
        }
        if (i == 4) {
            String strStringVal = jSONLexer.stringVal();
            jSONLexer.nextToken(16);
            if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                JSONScanner jSONScanner = new JSONScanner(strStringVal);
                try {
                    if (jSONScanner.scanISO8601DateIfMatch()) {
                        return jSONScanner.getCalendar().getTime();
                    }
                } finally {
                    jSONScanner.close();
                }
            }
            return strStringVal;
        }
        if (i == 12) {
            return parseObject(new JSONObject(jSONLexer.isEnabled(Feature.OrderedField)), obj);
        }
        if (i == 14) {
            JSONArray jSONArray = new JSONArray();
            parseArray(jSONArray, obj);
            return jSONLexer.isEnabled(Feature.UseObjectArray) ? jSONArray.toArray() : jSONArray;
        }
        if (i != 26) {
            switch (i) {
                case 6:
                    jSONLexer.nextToken();
                    return Boolean.TRUE;
                case 7:
                    jSONLexer.nextToken();
                    return Boolean.FALSE;
                case 8:
                    jSONLexer.nextToken();
                    return null;
                case 9:
                    jSONLexer.nextToken(18);
                    if (jSONLexer.token() != 18) {
                        throw new JSONException("syntax error");
                    }
                    jSONLexer.nextToken(10);
                    accept(10);
                    long jLongValue = jSONLexer.integerValue().longValue();
                    accept(2);
                    accept(11);
                    return new Date(jLongValue);
                default:
                    switch (i) {
                        case 20:
                            if (jSONLexer.isBlankInput()) {
                                return null;
                            }
                            throw new JSONException("unterminated json string, " + jSONLexer.info());
                        case 21:
                            jSONLexer.nextToken();
                            HashSet hashSet = new HashSet();
                            parseArray(hashSet, obj);
                            return hashSet;
                        case 22:
                            jSONLexer.nextToken();
                            TreeSet treeSet = new TreeSet();
                            parseArray(treeSet, obj);
                            return treeSet;
                        case 23:
                            jSONLexer.nextToken();
                            return null;
                        default:
                            throw new JSONException("syntax error, " + jSONLexer.info());
                    }
            }
        }
        byte[] bArrBytesValue = jSONLexer.bytesValue();
        jSONLexer.nextToken();
        return bArrBytesValue;
    }

    public void config(Feature feature, boolean z) {
        this.lexer.config(feature, z);
    }

    public boolean isEnabled(Feature feature) {
        return this.lexer.isEnabled(feature);
    }

    public JSONLexer getLexer() {
        return this.lexer;
    }

    public final void accept(int i) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == i) {
            jSONLexer.nextToken();
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(jSONLexer.token()));
    }

    public final void accept(int i, int i2) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == i) {
            jSONLexer.nextToken(i2);
        } else {
            throwException(i);
        }
    }

    public void throwException(int i) {
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(this.lexer.token()));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        JSONLexer jSONLexer = this.lexer;
        try {
            if (jSONLexer.isEnabled(Feature.AutoCloseSource) && jSONLexer.token() != 20) {
                throw new JSONException("not close json text, token : " + JSONToken.name(jSONLexer.token()));
            }
        } finally {
            jSONLexer.close();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0022, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object resolveReference(java.lang.String r5) {
        /*
            r4 = this;
            com.alibaba.fastjson.parser.ParseContext[] r0 = r4.contextArray
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            r0 = 0
        L7:
            com.alibaba.fastjson.parser.ParseContext[] r2 = r4.contextArray
            int r3 = r2.length
            if (r0 >= r3) goto L22
            int r3 = r4.contextArrayIndex
            if (r0 >= r3) goto L22
            r2 = r2[r0]
            java.lang.String r3 = r2.toString()
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L1f
            java.lang.Object r5 = r2.object
            return r5
        L1f:
            int r0 = r0 + 1
            goto L7
        L22:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.resolveReference(java.lang.String):java.lang.Object");
    }

    public void handleResovleTask(Object obj) {
        Object objEval;
        List<ResolveTask> list = this.resolveTaskList;
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ResolveTask resolveTask = this.resolveTaskList.get(i);
            String str = resolveTask.referenceValue;
            Object obj2 = resolveTask.ownerContext != null ? resolveTask.ownerContext.object : null;
            if (str.startsWith("$")) {
                objEval = getObject(str);
                if (objEval == null) {
                    try {
                        objEval = JSONPath.eval(obj, str);
                    } catch (JSONPathException unused) {
                    }
                }
            } else {
                objEval = resolveTask.context.object;
            }
            FieldDeserializer fieldDeserializer = resolveTask.fieldDeserializer;
            if (fieldDeserializer != null) {
                if (objEval != null && objEval.getClass() == JSONObject.class && fieldDeserializer.fieldInfo != null && !Map.class.isAssignableFrom(fieldDeserializer.fieldInfo.fieldClass)) {
                    objEval = JSONPath.eval(this.contextArray[0].object, str);
                }
                fieldDeserializer.setValue(obj2, objEval);
            }
        }
    }

    public static class ResolveTask {
        public final ParseContext context;
        public FieldDeserializer fieldDeserializer;
        public ParseContext ownerContext;
        public final String referenceValue;

        public ResolveTask(ParseContext parseContext, String str) {
            this.context = parseContext;
            this.referenceValue = str;
        }
    }

    public void parseExtra(Object obj, String str) {
        Object object;
        this.lexer.nextTokenWithColon();
        List<ExtraTypeProvider> list = this.extraTypeProviders;
        Type extraType = null;
        if (list != null) {
            Iterator<ExtraTypeProvider> it = list.iterator();
            while (it.hasNext()) {
                extraType = it.next().getExtraType(obj, str);
            }
        }
        if (extraType == null) {
            object = parse();
        } else {
            object = parseObject(extraType);
        }
        if (obj instanceof ExtraProcessable) {
            ((ExtraProcessable) obj).processExtra(str, object);
            return;
        }
        List<ExtraProcessor> list2 = this.extraProcessors;
        if (list2 != null) {
            Iterator<ExtraProcessor> it2 = list2.iterator();
            while (it2.hasNext()) {
                it2.next().processExtra(obj, str, object);
            }
        }
        if (this.resolveStatus == 1) {
            this.resolveStatus = 0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:86:0x0230, code lost:
    
        return r11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object parse(com.alibaba.fastjson.parser.deserializer.PropertyProcessable r11, java.lang.Object r12) {
        /*
            Method dump skipped, instruction units count: 607
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parse(com.alibaba.fastjson.parser.deserializer.PropertyProcessable, java.lang.Object):java.lang.Object");
    }
}