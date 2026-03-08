package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes.dex */
public class JavaBeanDeserializer implements ObjectDeserializer {
    private final Map<String, FieldDeserializer> alterNameFieldDeserializers;
    public final JavaBeanInfo beanInfo;
    protected final Class<?> clazz;
    private ConcurrentMap<String, Object> extraFieldDeserializers;
    private final FieldDeserializer[] fieldDeserializers;
    private transient long[] hashArray;
    private transient short[] hashArrayMapping;
    private transient long[] smartMatchHashArray;
    private transient short[] smartMatchHashArrayMapping;
    protected final FieldDeserializer[] sortedFieldDeserializers;

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 12;
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls) {
        this(parserConfig, cls, cls);
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type) {
        this(parserConfig, JavaBeanInfo.build(cls, type, parserConfig.propertyNamingStrategy, parserConfig.fieldBased, parserConfig.compatibleWithJavaBean));
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo) {
        this.clazz = javaBeanInfo.clazz;
        this.beanInfo = javaBeanInfo;
        this.sortedFieldDeserializers = new FieldDeserializer[javaBeanInfo.sortedFields.length];
        int length = javaBeanInfo.sortedFields.length;
        HashMap map = null;
        int i = 0;
        while (i < length) {
            FieldInfo fieldInfo = javaBeanInfo.sortedFields[i];
            FieldDeserializer fieldDeserializerCreateFieldDeserializer = parserConfig.createFieldDeserializer(parserConfig, javaBeanInfo, fieldInfo);
            this.sortedFieldDeserializers[i] = fieldDeserializerCreateFieldDeserializer;
            HashMap map2 = map;
            for (String str : fieldInfo.alternateNames) {
                if (map2 == null) {
                    map2 = new HashMap();
                }
                map2.put(str, fieldDeserializerCreateFieldDeserializer);
            }
            i++;
            map = map2;
        }
        this.alterNameFieldDeserializers = map;
        this.fieldDeserializers = new FieldDeserializer[javaBeanInfo.fields.length];
        int length2 = javaBeanInfo.fields.length;
        for (int i2 = 0; i2 < length2; i2++) {
            this.fieldDeserializers[i2] = getFieldDeserializer(javaBeanInfo.fields[i2].name);
        }
    }

    public FieldDeserializer getFieldDeserializer(String str) {
        return getFieldDeserializer(str, null);
    }

    public FieldDeserializer getFieldDeserializer(String str, int[] iArr) {
        if (str == null) {
            return null;
        }
        int i = 0;
        int length = this.sortedFieldDeserializers.length - 1;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int iCompareTo = this.sortedFieldDeserializers[i2].fieldInfo.name.compareTo(str);
            if (iCompareTo < 0) {
                i = i2 + 1;
            } else {
                if (iCompareTo <= 0) {
                    if (isSetFlag(i2, iArr)) {
                        return null;
                    }
                    return this.sortedFieldDeserializers[i2];
                }
                length = i2 - 1;
            }
        }
        Map<String, FieldDeserializer> map = this.alterNameFieldDeserializers;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    public FieldDeserializer getFieldDeserializer(long j) {
        int i = 0;
        if (this.hashArray == null) {
            long[] jArr = new long[this.sortedFieldDeserializers.length];
            int i2 = 0;
            while (true) {
                FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
                if (i2 >= fieldDeserializerArr.length) {
                    break;
                }
                jArr[i2] = TypeUtils.fnv1a_64(fieldDeserializerArr[i2].fieldInfo.name);
                i2++;
            }
            Arrays.sort(jArr);
            this.hashArray = jArr;
        }
        int iBinarySearch = Arrays.binarySearch(this.hashArray, j);
        if (iBinarySearch < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, (short) -1);
            while (true) {
                FieldDeserializer[] fieldDeserializerArr2 = this.sortedFieldDeserializers;
                if (i >= fieldDeserializerArr2.length) {
                    break;
                }
                int iBinarySearch2 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(fieldDeserializerArr2[i].fieldInfo.name));
                if (iBinarySearch2 >= 0) {
                    sArr[iBinarySearch2] = (short) i;
                }
                i++;
            }
            this.hashArrayMapping = sArr;
        }
        short s = this.hashArrayMapping[iBinarySearch];
        if (s != -1) {
            return this.sortedFieldDeserializers[s];
        }
        return null;
    }

    static boolean isSetFlag(int i, int[] iArr) {
        if (iArr == null) {
            return false;
        }
        int i2 = i / 32;
        int i3 = i % 32;
        if (i2 < iArr.length) {
            if (((1 << i3) & iArr[i2]) != 0) {
                return true;
            }
        }
        return false;
    }

    public Object createInstance(DefaultJSONParser defaultJSONParser, Type type) {
        Object objNewInstance;
        if ((type instanceof Class) && this.clazz.isInterface()) {
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{(Class) type}, new JSONObject());
        }
        Object obj = null;
        if (this.beanInfo.defaultConstructor == null && this.beanInfo.factoryMethod == null) {
            return null;
        }
        if (this.beanInfo.factoryMethod != null && this.beanInfo.defaultConstructorParameterSize > 0) {
            return null;
        }
        try {
            Constructor<?> constructor = this.beanInfo.defaultConstructor;
            if (this.beanInfo.defaultConstructorParameterSize != 0) {
                ParseContext context = defaultJSONParser.getContext();
                if (context == null || context.object == null) {
                    throw new JSONException("can't create non-static inner class instance.");
                }
                if (type instanceof Class) {
                    String name = ((Class) type).getName();
                    String strSubstring = name.substring(0, name.lastIndexOf(36));
                    Object obj2 = context.object;
                    String name2 = obj2.getClass().getName();
                    if (!name2.equals(strSubstring)) {
                        ParseContext parseContext = context.parent;
                        if (parseContext != null && parseContext.object != null && (("java.util.ArrayList".equals(name2) || "java.util.List".equals(name2) || "java.util.Collection".equals(name2) || "java.util.Map".equals(name2) || "java.util.HashMap".equals(name2)) && parseContext.object.getClass().getName().equals(strSubstring))) {
                            obj = parseContext.object;
                        }
                        obj2 = obj;
                    }
                    if (obj2 == null) {
                        throw new JSONException("can't create non-static inner class instance.");
                    }
                    objNewInstance = constructor.newInstance(obj2);
                } else {
                    throw new JSONException("can't create non-static inner class instance.");
                }
            } else if (constructor != null) {
                objNewInstance = constructor.newInstance(new Object[0]);
            } else {
                objNewInstance = this.beanInfo.factoryMethod.invoke(null, new Object[0]);
            }
            if (defaultJSONParser != null && defaultJSONParser.lexer.isEnabled(Feature.InitStringFieldAsEmpty)) {
                for (FieldInfo fieldInfo : this.beanInfo.fields) {
                    if (fieldInfo.fieldClass == String.class) {
                        try {
                            fieldInfo.set(objNewInstance, "");
                        } catch (Exception e2) {
                            throw new JSONException("create instance error, class " + this.clazz.getName(), e2);
                        }
                    }
                }
            }
            return objNewInstance;
        } catch (JSONException e3) {
            throw e3;
        } catch (Exception e4) {
            throw new JSONException("create instance error, class " + this.clazz.getName(), e4);
        }
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return (T) deserialze(defaultJSONParser, type, obj, 0);
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, int i) {
        return (T) deserialze(defaultJSONParser, type, obj, null, i, null);
    }

    public <T> T deserialzeArrayMapping(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        Enum<?> enumScanEnum;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() != 14) {
            throw new JSONException(AuthorizationResponseParser.ERROR);
        }
        T t = (T) createInstance(defaultJSONParser, type);
        int i = 0;
        int length = this.sortedFieldDeserializers.length;
        while (true) {
            if (i >= length) {
                break;
            }
            char c2 = i == length + (-1) ? ']' : ',';
            FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i];
            Class<?> cls = fieldDeserializer.fieldInfo.fieldClass;
            if (cls == Integer.TYPE) {
                fieldDeserializer.setValue((Object) t, jSONLexer.scanInt(c2));
            } else if (cls == String.class) {
                fieldDeserializer.setValue((Object) t, jSONLexer.scanString(c2));
            } else if (cls == Long.TYPE) {
                fieldDeserializer.setValue(t, jSONLexer.scanLong(c2));
            } else if (cls.isEnum()) {
                char current = jSONLexer.getCurrent();
                if (current == '\"' || current == 'n') {
                    enumScanEnum = jSONLexer.scanEnum(cls, defaultJSONParser.getSymbolTable(), c2);
                } else if (current >= '0' && current <= '9') {
                    enumScanEnum = ((EnumDeserializer) ((DefaultFieldDeserializer) fieldDeserializer).getFieldValueDeserilizer(defaultJSONParser.getConfig())).valueOf(jSONLexer.scanInt(c2));
                } else {
                    enumScanEnum = scanEnum(jSONLexer, c2);
                }
                fieldDeserializer.setValue(t, enumScanEnum);
            } else if (cls == Boolean.TYPE) {
                fieldDeserializer.setValue(t, jSONLexer.scanBoolean(c2));
            } else if (cls == Float.TYPE) {
                fieldDeserializer.setValue(t, Float.valueOf(jSONLexer.scanFloat(c2)));
            } else if (cls == Double.TYPE) {
                fieldDeserializer.setValue(t, Double.valueOf(jSONLexer.scanDouble(c2)));
            } else if (cls == Date.class && jSONLexer.getCurrent() == '1') {
                fieldDeserializer.setValue(t, new Date(jSONLexer.scanLong(c2)));
            } else if (cls == BigDecimal.class) {
                fieldDeserializer.setValue(t, jSONLexer.scanDecimal(c2));
            } else {
                jSONLexer.nextToken(14);
                fieldDeserializer.setValue(t, defaultJSONParser.parseObject(fieldDeserializer.fieldInfo.fieldType, fieldDeserializer.fieldInfo.name));
                if (jSONLexer.token() == 15) {
                    break;
                }
                check(jSONLexer, c2 == ']' ? 15 : 16);
            }
            i++;
        }
        jSONLexer.nextToken(16);
        return t;
    }

    protected void check(JSONLexer jSONLexer, int i) {
        if (jSONLexer.token() != i) {
            throw new JSONException("syntax error");
        }
    }

    protected Enum<?> scanEnum(JSONLexer jSONLexer, char c2) {
        throw new JSONException("illegal enum. " + jSONLexer.info());
    }

    /* JADX WARN: Code restructure failed: missing block: B:215:0x02a3, code lost:
    
        if (r11.matchStat == (-2)) goto L216;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004c, code lost:
    
        r6 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:300:0x0401, code lost:
    
        r14 = r6;
        r2 = r18;
        r28 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:365:0x0510, code lost:
    
        r1 = (T) r17;
        r14 = r18;
        r2 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:366:0x0516, code lost:
    
        if (r1 != null) goto L467;
     */
    /* JADX WARN: Code restructure failed: missing block: B:367:0x0518, code lost:
    
        if (r2 != null) goto L375;
     */
    /* JADX WARN: Code restructure failed: missing block: B:368:0x051a, code lost:
    
        r1 = (T) createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r25, r26);
        r14 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:369:0x051e, code lost:
    
        if (r14 != 0) goto L371;
     */
    /* JADX WARN: Code restructure failed: missing block: B:370:0x0520, code lost:
    
        r14 = r25.setContext(r15, r1, r27);
     */
    /* JADX WARN: Code restructure failed: missing block: B:371:0x0524, code lost:
    
        if (r14 == 0) goto L373;
     */
    /* JADX WARN: Code restructure failed: missing block: B:372:0x0526, code lost:
    
        r14.object = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:373:0x0528, code lost:
    
        r25.setContext(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:374:0x052b, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:375:0x052c, code lost:
    
        r0 = r24.beanInfo.creatorConstructorParameters;
     */
    /* JADX WARN: Code restructure failed: missing block: B:376:0x0530, code lost:
    
        r10 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:377:0x0534, code lost:
    
        if (r0 == null) goto L411;
     */
    /* JADX WARN: Code restructure failed: missing block: B:378:0x0536, code lost:
    
        r7 = new java.lang.Object[r0.length];
        r12 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:380:0x053c, code lost:
    
        if (r12 >= r0.length) goto L542;
     */
    /* JADX WARN: Code restructure failed: missing block: B:381:0x053e, code lost:
    
        r13 = r2.remove(r0[r12]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:382:0x0544, code lost:
    
        if (r13 != null) goto L409;
     */
    /* JADX WARN: Code restructure failed: missing block: B:383:0x0546, code lost:
    
        r4 = r24.beanInfo.creatorConstructorParameterTypes[r12];
        r5 = r24.beanInfo.fields[r12];
     */
    /* JADX WARN: Code restructure failed: missing block: B:384:0x0554, code lost:
    
        if (r4 != java.lang.Byte.TYPE) goto L386;
     */
    /* JADX WARN: Code restructure failed: missing block: B:385:0x0556, code lost:
    
        r4 = java.lang.Byte.valueOf(r28);
     */
    /* JADX WARN: Code restructure failed: missing block: B:387:0x055d, code lost:
    
        if (r4 != java.lang.Short.TYPE) goto L389;
     */
    /* JADX WARN: Code restructure failed: missing block: B:388:0x055f, code lost:
    
        r4 = java.lang.Short.valueOf(r28);
     */
    /* JADX WARN: Code restructure failed: missing block: B:390:0x0566, code lost:
    
        if (r4 != java.lang.Integer.TYPE) goto L392;
     */
    /* JADX WARN: Code restructure failed: missing block: B:391:0x0568, code lost:
    
        r4 = java.lang.Integer.valueOf(r28);
     */
    /* JADX WARN: Code restructure failed: missing block: B:393:0x056f, code lost:
    
        if (r4 != java.lang.Long.TYPE) goto L395;
     */
    /* JADX WARN: Code restructure failed: missing block: B:394:0x0571, code lost:
    
        r4 = 0L;
     */
    /* JADX WARN: Code restructure failed: missing block: B:396:0x0578, code lost:
    
        if (r4 != java.lang.Float.TYPE) goto L398;
     */
    /* JADX WARN: Code restructure failed: missing block: B:397:0x057a, code lost:
    
        r4 = java.lang.Float.valueOf(0.0f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:399:0x0582, code lost:
    
        if (r4 != java.lang.Double.TYPE) goto L401;
     */
    /* JADX WARN: Code restructure failed: missing block: B:400:0x0584, code lost:
    
        r4 = java.lang.Double.valueOf(0.0d);
     */
    /* JADX WARN: Code restructure failed: missing block: B:402:0x058d, code lost:
    
        if (r4 != java.lang.Boolean.TYPE) goto L404;
     */
    /* JADX WARN: Code restructure failed: missing block: B:403:0x058f, code lost:
    
        r4 = java.lang.Boolean.FALSE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:405:0x0594, code lost:
    
        if (r4 != java.lang.String.class) goto L409;
     */
    /* JADX WARN: Code restructure failed: missing block: B:407:0x059d, code lost:
    
        if ((r5.parserFeatures & com.alibaba.fastjson.parser.Feature.InitStringFieldAsEmpty.mask) == 0) goto L409;
     */
    /* JADX WARN: Code restructure failed: missing block: B:408:0x059f, code lost:
    
        r4 = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:409:0x05a1, code lost:
    
        r4 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:410:0x05a2, code lost:
    
        r7[r12] = r4;
        r12 = r12 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:411:0x05a7, code lost:
    
        r4 = r24.beanInfo.fields;
        r5 = r4.length;
        r7 = new java.lang.Object[r5];
        r6 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:412:0x05b0, code lost:
    
        if (r6 >= r5) goto L557;
     */
    /* JADX WARN: Code restructure failed: missing block: B:413:0x05b2, code lost:
    
        r12 = r4[r6];
        r13 = r2.get(r12.name);
     */
    /* JADX WARN: Code restructure failed: missing block: B:414:0x05ba, code lost:
    
        if (r13 != null) goto L443;
     */
    /* JADX WARN: Code restructure failed: missing block: B:415:0x05bc, code lost:
    
        r10 = r12.fieldType;
     */
    /* JADX WARN: Code restructure failed: missing block: B:416:0x05c0, code lost:
    
        if (r10 != java.lang.Byte.TYPE) goto L420;
     */
    /* JADX WARN: Code restructure failed: missing block: B:417:0x05c2, code lost:
    
        r10 = java.lang.Byte.valueOf(r28);
     */
    /* JADX WARN: Code restructure failed: missing block: B:418:0x05c6, code lost:
    
        r16 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:421:0x05cd, code lost:
    
        if (r10 != java.lang.Short.TYPE) goto L423;
     */
    /* JADX WARN: Code restructure failed: missing block: B:422:0x05cf, code lost:
    
        r10 = java.lang.Short.valueOf(r28);
     */
    /* JADX WARN: Code restructure failed: missing block: B:424:0x05d6, code lost:
    
        if (r10 != java.lang.Integer.TYPE) goto L426;
     */
    /* JADX WARN: Code restructure failed: missing block: B:425:0x05d8, code lost:
    
        r10 = java.lang.Integer.valueOf(r28);
     */
    /* JADX WARN: Code restructure failed: missing block: B:427:0x05df, code lost:
    
        if (r10 != java.lang.Long.TYPE) goto L429;
     */
    /* JADX WARN: Code restructure failed: missing block: B:428:0x05e1, code lost:
    
        r16 = 0;
        r10 = 0L;
     */
    /* JADX WARN: Code restructure failed: missing block: B:429:0x05e8, code lost:
    
        r16 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:430:0x05ec, code lost:
    
        if (r10 != java.lang.Float.TYPE) goto L432;
     */
    /* JADX WARN: Code restructure failed: missing block: B:431:0x05ee, code lost:
    
        r10 = java.lang.Float.valueOf(0.0f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:433:0x05f6, code lost:
    
        if (r10 != java.lang.Double.TYPE) goto L435;
     */
    /* JADX WARN: Code restructure failed: missing block: B:434:0x05f8, code lost:
    
        r10 = java.lang.Double.valueOf(0.0d);
     */
    /* JADX WARN: Code restructure failed: missing block: B:436:0x0603, code lost:
    
        if (r10 != java.lang.Boolean.TYPE) goto L438;
     */
    /* JADX WARN: Code restructure failed: missing block: B:437:0x0605, code lost:
    
        r10 = java.lang.Boolean.FALSE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:439:0x060a, code lost:
    
        if (r10 != java.lang.String.class) goto L444;
     */
    /* JADX WARN: Code restructure failed: missing block: B:441:0x0613, code lost:
    
        if ((r12.parserFeatures & com.alibaba.fastjson.parser.Feature.InitStringFieldAsEmpty.mask) == 0) goto L444;
     */
    /* JADX WARN: Code restructure failed: missing block: B:442:0x0615, code lost:
    
        r10 = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:443:0x0617, code lost:
    
        r16 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:444:0x061b, code lost:
    
        r10 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:445:0x061c, code lost:
    
        r7[r6] = r10;
        r6 = r6 + 1;
        r10 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:447:0x0627, code lost:
    
        if (r24.beanInfo.creatorConstructor == null) goto L459;
     */
    /* JADX WARN: Code restructure failed: missing block: B:448:0x0629, code lost:
    
        r1 = (T) r24.beanInfo.creatorConstructor.newInstance(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:449:0x0631, code lost:
    
        if (r0 == null) goto L466;
     */
    /* JADX WARN: Code restructure failed: missing block: B:450:0x0633, code lost:
    
        r0 = r2.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:452:0x063f, code lost:
    
        if (r0.hasNext() == false) goto L552;
     */
    /* JADX WARN: Code restructure failed: missing block: B:453:0x0641, code lost:
    
        r2 = r0.next();
        r3 = getFieldDeserializer(r2.getKey());
     */
    /* JADX WARN: Code restructure failed: missing block: B:454:0x0651, code lost:
    
        if (r3 == null) goto L555;
     */
    /* JADX WARN: Code restructure failed: missing block: B:455:0x0653, code lost:
    
        r3.setValue(r1, r2.getValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:456:0x065b, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:458:0x067a, code lost:
    
        throw new com.alibaba.fastjson.JSONException("create instance error, " + r24.beanInfo.creatorConstructor.toGenericString(), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:460:0x067f, code lost:
    
        if (r24.beanInfo.factoryMethod == null) goto L466;
     */
    /* JADX WARN: Code restructure failed: missing block: B:462:0x068a, code lost:
    
        r1 = (T) r24.beanInfo.factoryMethod.invoke(null, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:463:0x068c, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:465:0x06ab, code lost:
    
        throw new com.alibaba.fastjson.JSONException("create factory method error, " + r24.beanInfo.factoryMethod.toString(), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:466:0x06ac, code lost:
    
        r14.object = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:467:0x06ae, code lost:
    
        r0 = r24.beanInfo.buildMethod;
     */
    /* JADX WARN: Code restructure failed: missing block: B:468:0x06b2, code lost:
    
        if (r0 != null) goto L473;
     */
    /* JADX WARN: Code restructure failed: missing block: B:469:0x06b4, code lost:
    
        if (r14 == 0) goto L471;
     */
    /* JADX WARN: Code restructure failed: missing block: B:470:0x06b6, code lost:
    
        r14.object = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:471:0x06b8, code lost:
    
        r25.setContext(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:472:0x06bb, code lost:
    
        return (T) r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:474:0x06be, code lost:
    
        r0 = (T) r0.invoke(r1, new java.lang.Object[r28]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:475:0x06c4, code lost:
    
        if (r14 == 0) goto L477;
     */
    /* JADX WARN: Code restructure failed: missing block: B:476:0x06c6, code lost:
    
        r14.object = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:477:0x06c8, code lost:
    
        r25.setContext(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:478:0x06cb, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:479:0x06cc, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:481:0x06d4, code lost:
    
        throw new com.alibaba.fastjson.JSONException("build object error", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:482:0x06d5, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02b0 A[Catch: all -> 0x072a, TryCatch #8 {all -> 0x072a, blocks: (B:80:0x0121, B:82:0x0126, B:85:0x013a, B:90:0x0149, B:97:0x0157, B:222:0x02b0, B:224:0x02ba, B:226:0x02c6, B:229:0x02d1, B:236:0x02e4, B:238:0x02ee, B:240:0x02fa, B:261:0x0351, B:263:0x035c, B:268:0x036c, B:269:0x0373, B:241:0x02fe, B:243:0x0306, B:245:0x030c, B:246:0x030f, B:247:0x031b, B:250:0x0324, B:252:0x0328, B:253:0x032b, B:255:0x032f, B:256:0x0332, B:257:0x033e, B:260:0x0346, B:270:0x0374, B:271:0x038e, B:273:0x0391, B:277:0x039b, B:279:0x03a5, B:281:0x03b8, B:284:0x03c1, B:286:0x03c9, B:288:0x03db, B:290:0x03e3, B:292:0x03e7, B:297:0x03f6, B:299:0x03fe, B:302:0x0414, B:303:0x041b, B:275:0x0397, B:308:0x042c, B:310:0x0432, B:311:0x043c, B:100:0x0162, B:106:0x016f, B:108:0x0173, B:111:0x017c, B:116:0x0186, B:119:0x018f, B:124:0x0199, B:127:0x01a2, B:130:0x01a8, B:135:0x01b2, B:140:0x01bc, B:145:0x01c6, B:147:0x01cc, B:150:0x01da, B:152:0x01e2, B:154:0x01e6, B:157:0x01f5, B:162:0x0200, B:165:0x020a, B:170:0x0215, B:173:0x021f, B:178:0x022a, B:181:0x0234, B:184:0x023b, B:187:0x0245, B:190:0x0252, B:193:0x0258, B:196:0x0265, B:199:0x026b, B:202:0x0278, B:205:0x027e, B:208:0x028b, B:211:0x0291, B:214:0x02a0), top: B:522:0x0121 }] */
    /* JADX WARN: Removed duplicated region for block: B:305:0x041f  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x042a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0459  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0460  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x04c0  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x0504  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x0507 A[Catch: all -> 0x0724, TryCatch #1 {all -> 0x0724, blocks: (B:490:0x06f0, B:359:0x04fc, B:362:0x0507, B:364:0x050d, B:485:0x06db, B:487:0x06e3, B:491:0x06fd, B:492:0x071b, B:350:0x04dc, B:352:0x04e2, B:354:0x04e8, B:357:0x04f4, B:493:0x071c, B:494:0x0723), top: B:511:0x06f0 }] */
    /* JADX WARN: Removed duplicated region for block: B:503:0x0733  */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v14, types: [com.alibaba.fastjson.parser.ParseContext] */
    /* JADX WARN: Type inference failed for: r14v15, types: [com.alibaba.fastjson.parser.ParseContext] */
    /* JADX WARN: Type inference failed for: r14v17 */
    /* JADX WARN: Type inference failed for: r14v24 */
    /* JADX WARN: Type inference failed for: r14v25 */
    /* JADX WARN: Type inference failed for: r14v9 */
    /* JADX WARN: Type inference failed for: r18v12 */
    /* JADX WARN: Type inference failed for: r18v13 */
    /* JADX WARN: Type inference failed for: r18v2 */
    /* JADX WARN: Type inference failed for: r18v4 */
    /* JADX WARN: Type inference failed for: r18v5 */
    /* JADX WARN: Type inference failed for: r18v8 */
    /* JADX WARN: Type inference failed for: r25v0, types: [com.alibaba.fastjson.parser.DefaultJSONParser] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [com.alibaba.fastjson.parser.ParseContext] */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v31 */
    /* JADX WARN: Type inference failed for: r6v32 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5, types: [com.alibaba.fastjson.parser.ParseContext] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r25, java.lang.reflect.Type r26, java.lang.Object r27, java.lang.Object r28, int r29, int[] r30) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1854
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object, int, int[]):java.lang.Object");
    }

    protected Enum scanEnum(JSONLexerBase jSONLexerBase, char[] cArr, ObjectDeserializer objectDeserializer) {
        EnumDeserializer enumDeserializer = objectDeserializer instanceof EnumDeserializer ? (EnumDeserializer) objectDeserializer : null;
        if (enumDeserializer == null) {
            jSONLexerBase.matchStat = -1;
            return null;
        }
        long jScanFieldSymbol = jSONLexerBase.scanFieldSymbol(cArr);
        if (jSONLexerBase.matchStat > 0) {
            return enumDeserializer.getEnumByHashCode(jScanFieldSymbol);
        }
        return null;
    }

    public boolean parseField(DefaultJSONParser defaultJSONParser, String str, Object obj, Type type, Map<String, Object> map) {
        return parseField(defaultJSONParser, str, obj, type, map, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0088 A[PHI: r2
  0x0088: PHI (r2v4 com.alibaba.fastjson.parser.deserializer.FieldDeserializer) = 
  (r2v3 com.alibaba.fastjson.parser.deserializer.FieldDeserializer)
  (r2v3 com.alibaba.fastjson.parser.deserializer.FieldDeserializer)
  (r2v30 com.alibaba.fastjson.parser.deserializer.FieldDeserializer)
  (r2v3 com.alibaba.fastjson.parser.deserializer.FieldDeserializer)
 binds: [B:10:0x0032, B:31:0x007f, B:34:0x0085, B:14:0x003f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r17v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r17v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean parseField(com.alibaba.fastjson.parser.DefaultJSONParser r22, java.lang.String r23, java.lang.Object r24, java.lang.reflect.Type r25, java.util.Map<java.lang.String, java.lang.Object> r26, int[] r27) {
        /*
            Method dump skipped, instruction units count: 475
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.parseField(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.String, java.lang.Object, java.lang.reflect.Type, java.util.Map, int[]):boolean");
    }

    public FieldDeserializer smartMatch(String str) {
        return smartMatch(str, null);
    }

    public FieldDeserializer smartMatch(String str, int[] iArr) {
        if (str == null) {
            return null;
        }
        FieldDeserializer fieldDeserializer = getFieldDeserializer(str, iArr);
        if (fieldDeserializer == null) {
            long jFnv1a_64_lower = TypeUtils.fnv1a_64_lower(str);
            int i = 0;
            if (this.smartMatchHashArray == null) {
                long[] jArr = new long[this.sortedFieldDeserializers.length];
                int i2 = 0;
                while (true) {
                    FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
                    if (i2 >= fieldDeserializerArr.length) {
                        break;
                    }
                    jArr[i2] = TypeUtils.fnv1a_64_lower(fieldDeserializerArr[i2].fieldInfo.name);
                    i2++;
                }
                Arrays.sort(jArr);
                this.smartMatchHashArray = jArr;
            }
            int iBinarySearch = Arrays.binarySearch(this.smartMatchHashArray, jFnv1a_64_lower);
            if (iBinarySearch < 0 && str.startsWith("is")) {
                iBinarySearch = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv1a_64_lower(str.substring(2)));
            }
            if (iBinarySearch >= 0) {
                if (this.smartMatchHashArrayMapping == null) {
                    short[] sArr = new short[this.smartMatchHashArray.length];
                    Arrays.fill(sArr, (short) -1);
                    while (true) {
                        FieldDeserializer[] fieldDeserializerArr2 = this.sortedFieldDeserializers;
                        if (i >= fieldDeserializerArr2.length) {
                            break;
                        }
                        int iBinarySearch2 = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv1a_64_lower(fieldDeserializerArr2[i].fieldInfo.name));
                        if (iBinarySearch2 >= 0) {
                            sArr[iBinarySearch2] = (short) i;
                        }
                        i++;
                    }
                    this.smartMatchHashArrayMapping = sArr;
                }
                short s = this.smartMatchHashArrayMapping[iBinarySearch];
                if (s != -1 && !isSetFlag(s, iArr)) {
                    fieldDeserializer = this.sortedFieldDeserializers[s];
                }
            }
            if (fieldDeserializer != null && (fieldDeserializer.fieldInfo.parserFeatures & Feature.DisableFieldSmartMatch.mask) != 0) {
                return null;
            }
        }
        return fieldDeserializer;
    }

    public Object createInstance(Map<String, Object> map, ParserConfig parserConfig) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.beanInfo.creatorConstructor == null && this.beanInfo.factoryMethod == null) {
            Object objCreateInstance = createInstance((DefaultJSONParser) null, this.clazz);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                FieldDeserializer fieldDeserializerSmartMatch = smartMatch(key);
                if (fieldDeserializerSmartMatch != null) {
                    fieldDeserializerSmartMatch.setValue(objCreateInstance, TypeUtils.cast(value, fieldDeserializerSmartMatch.fieldInfo.fieldType, parserConfig));
                }
            }
            if (this.beanInfo.buildMethod == null) {
                return objCreateInstance;
            }
            try {
                return this.beanInfo.buildMethod.invoke(objCreateInstance, new Object[0]);
            } catch (Exception e2) {
                throw new JSONException("build object error", e2);
            }
        }
        FieldInfo[] fieldInfoArr = this.beanInfo.fields;
        int length = fieldInfoArr.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            FieldInfo fieldInfo = fieldInfoArr[i];
            Object objValueOf = map.get(fieldInfo.name);
            if (objValueOf == null) {
                Class<?> cls = fieldInfo.fieldClass;
                if (cls == Integer.TYPE) {
                    objValueOf = 0;
                } else if (cls == Long.TYPE) {
                    objValueOf = 0L;
                } else if (cls == Short.TYPE) {
                    objValueOf = (short) 0;
                } else if (cls == Byte.TYPE) {
                    objValueOf = (byte) 0;
                } else if (cls == Float.TYPE) {
                    objValueOf = Float.valueOf(0.0f);
                } else if (cls == Double.TYPE) {
                    objValueOf = Double.valueOf(0.0d);
                } else if (cls == Character.TYPE) {
                    objValueOf = '0';
                } else if (cls == Boolean.TYPE) {
                    objValueOf = false;
                }
            }
            objArr[i] = objValueOf;
        }
        if (this.beanInfo.creatorConstructor != null) {
            try {
                return this.beanInfo.creatorConstructor.newInstance(objArr);
            } catch (Exception e3) {
                throw new JSONException("create instance error, " + this.beanInfo.creatorConstructor.toGenericString(), e3);
            }
        }
        if (this.beanInfo.factoryMethod == null) {
            return null;
        }
        try {
            return this.beanInfo.factoryMethod.invoke(null, objArr);
        } catch (Exception e4) {
            throw new JSONException("create factory method error, " + this.beanInfo.factoryMethod.toString(), e4);
        }
    }

    public Type getFieldType(int i) {
        return this.sortedFieldDeserializers[i].fieldInfo.fieldType;
    }

    protected Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, int i) {
        return parseRest(defaultJSONParser, type, obj, obj2, i, new int[0]);
    }

    protected Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, int i, int[] iArr) {
        return deserialze(defaultJSONParser, type, obj, obj2, i, iArr);
    }

    protected JavaBeanDeserializer getSeeAlso(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, String str) {
        if (javaBeanInfo.jsonType == null) {
            return null;
        }
        for (Class<?> cls : javaBeanInfo.jsonType.seeAlso()) {
            ObjectDeserializer deserializer = parserConfig.getDeserializer(cls);
            if (deserializer instanceof JavaBeanDeserializer) {
                JavaBeanDeserializer javaBeanDeserializer = (JavaBeanDeserializer) deserializer;
                JavaBeanInfo javaBeanInfo2 = javaBeanDeserializer.beanInfo;
                if (javaBeanInfo2.typeName.equals(str)) {
                    return javaBeanDeserializer;
                }
                JavaBeanDeserializer seeAlso = getSeeAlso(parserConfig, javaBeanInfo2, str);
                if (seeAlso != null) {
                    return seeAlso;
                }
            }
        }
        return null;
    }

    protected static void parseArray(Collection collection, ObjectDeserializer objectDeserializer, DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexerBase jSONLexerBase = (JSONLexerBase) defaultJSONParser.lexer;
        int i = jSONLexerBase.token();
        if (i == 8) {
            jSONLexerBase.nextToken(16);
            jSONLexerBase.token();
            return;
        }
        if (i != 14) {
            defaultJSONParser.throwException(i);
        }
        if (jSONLexerBase.getCurrent() == '[') {
            jSONLexerBase.next();
            jSONLexerBase.setToken(14);
        } else {
            jSONLexerBase.nextToken(14);
        }
        if (jSONLexerBase.token() == 15) {
            jSONLexerBase.nextToken();
            return;
        }
        int i2 = 0;
        while (true) {
            collection.add(objectDeserializer.deserialze(defaultJSONParser, type, Integer.valueOf(i2)));
            i2++;
            if (jSONLexerBase.token() != 16) {
                break;
            }
            if (jSONLexerBase.getCurrent() == '[') {
                jSONLexerBase.next();
                jSONLexerBase.setToken(14);
            } else {
                jSONLexerBase.nextToken(14);
            }
        }
        int i3 = jSONLexerBase.token();
        if (i3 != 15) {
            defaultJSONParser.throwException(i3);
        }
        if (jSONLexerBase.getCurrent() == ',') {
            jSONLexerBase.next();
            jSONLexerBase.setToken(16);
        } else {
            jSONLexerBase.nextToken(16);
        }
    }
}