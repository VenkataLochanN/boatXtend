package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory;
import com.alibaba.fastjson.parser.deserializer.ArrayListTypeFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.EnumDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JSONPDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec;
import com.alibaba.fastjson.parser.deserializer.MapDeserializer;
import com.alibaba.fastjson.parser.deserializer.NumberDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.OptionalCodec;
import com.alibaba.fastjson.parser.deserializer.PropertyProcessable;
import com.alibaba.fastjson.parser.deserializer.PropertyProcessableDeserializer;
import com.alibaba.fastjson.parser.deserializer.SqlDateDeserializer;
import com.alibaba.fastjson.parser.deserializer.StackTraceElementDeserializer;
import com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer;
import com.alibaba.fastjson.parser.deserializer.TimeDeserializer;
import com.alibaba.fastjson.serializer.AtomicCodec;
import com.alibaba.fastjson.serializer.AwtCodec;
import com.alibaba.fastjson.serializer.BigDecimalCodec;
import com.alibaba.fastjson.serializer.BigIntegerCodec;
import com.alibaba.fastjson.serializer.BooleanCodec;
import com.alibaba.fastjson.serializer.CalendarCodec;
import com.alibaba.fastjson.serializer.CharArrayCodec;
import com.alibaba.fastjson.serializer.CharacterCodec;
import com.alibaba.fastjson.serializer.CollectionCodec;
import com.alibaba.fastjson.serializer.DateCodec;
import com.alibaba.fastjson.serializer.FloatCodec;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.MiscCodec;
import com.alibaba.fastjson.serializer.ObjectArrayCodec;
import com.alibaba.fastjson.serializer.ReferenceCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.ASMClassLoader;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.ServiceLoader;
import com.alibaba.fastjson.util.TypeUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.tencent.bugly.Bugly;
import java.io.Closeable;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.AccessControlException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import javax.sql.DataSource;
import javax.xml.datatype.XMLGregorianCalendar;
import kotlin.text.Typography;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: loaded from: classes.dex */
public class ParserConfig {
    public static final String AUTOTYPE_ACCEPT = "fastjson.parser.autoTypeAccept";
    private static final String[] AUTO_TYPE_ACCEPT_LIST;
    private static boolean awtError;
    public static ParserConfig global;
    private static boolean jdk8Error;
    private String[] acceptList;
    private boolean asmEnable;
    protected ASMDeserializerFactory asmFactory;
    private boolean autoTypeSupport;
    public boolean compatibleWithJavaBean;
    protected ClassLoader defaultClassLoader;
    private String[] denyList;
    private final IdentityHashMap<Type, ObjectDeserializer> deserializers;
    public final boolean fieldBased;
    private int maxTypeNameLength;
    public PropertyNamingStrategy propertyNamingStrategy;
    public final SymbolTable symbolTable;
    public static final String DENY_PROPERTY = "fastjson.parser.deny";
    public static final String[] DENYS = splitItemsFormProperty(IOUtils.getStringProperty(DENY_PROPERTY));
    public static final String AUTOTYPE_SUPPORT_PROPERTY = "fastjson.parser.autoTypeSupport";
    public static final boolean AUTO_SUPPORT = "true".equals(IOUtils.getStringProperty(AUTOTYPE_SUPPORT_PROPERTY));

    static {
        String[] strArrSplitItemsFormProperty = splitItemsFormProperty(IOUtils.getStringProperty(AUTOTYPE_ACCEPT));
        if (strArrSplitItemsFormProperty == null) {
            strArrSplitItemsFormProperty = new String[0];
        }
        AUTO_TYPE_ACCEPT_LIST = strArrSplitItemsFormProperty;
        global = new ParserConfig();
        awtError = false;
        jdk8Error = false;
    }

    public static ParserConfig getGlobalInstance() {
        return global;
    }

    public ParserConfig() {
        this(false);
    }

    public ParserConfig(boolean z) {
        this(null, null, z);
    }

    public ParserConfig(ClassLoader classLoader) {
        this(null, classLoader, false);
    }

    public ParserConfig(ASMDeserializerFactory aSMDeserializerFactory) {
        this(aSMDeserializerFactory, null, false);
    }

    private ParserConfig(ASMDeserializerFactory aSMDeserializerFactory, ClassLoader classLoader, boolean z) {
        this.deserializers = new IdentityHashMap<>();
        this.asmEnable = !ASMUtils.IS_ANDROID;
        this.symbolTable = new SymbolTable(4096);
        this.autoTypeSupport = AUTO_SUPPORT;
        this.denyList = "bsh,com.mchange,com.sun.,java.lang.Thread,java.net.Socket,java.rmi,javax.xml,org.apache.bcel,org.apache.commons.beanutils,org.apache.commons.collections.Transformer,org.apache.commons.collections.functors,org.apache.commons.collections4.comparators,org.apache.commons.fileupload,org.apache.myfaces.context.servlet,org.apache.tomcat,org.apache.wicket.util,org.apache.xalan,org.codehaus.groovy.runtime,org.hibernate,org.jboss,org.mozilla.javascript,org.python.core,org.springframework".split(AppInfo.DELIM);
        this.acceptList = AUTO_TYPE_ACCEPT_LIST;
        this.maxTypeNameLength = 256;
        this.compatibleWithJavaBean = TypeUtils.compatibleWithJavaBean;
        this.fieldBased = z;
        if (aSMDeserializerFactory == null && !ASMUtils.IS_ANDROID) {
            try {
                if (classLoader == null) {
                    aSMDeserializerFactory = new ASMDeserializerFactory(new ASMClassLoader());
                } else {
                    aSMDeserializerFactory = new ASMDeserializerFactory(classLoader);
                }
            } catch (ExceptionInInitializerError | NoClassDefFoundError | AccessControlException unused) {
            }
        }
        this.asmFactory = aSMDeserializerFactory;
        if (aSMDeserializerFactory == null) {
            this.asmEnable = false;
        }
        this.deserializers.put(SimpleDateFormat.class, MiscCodec.instance);
        this.deserializers.put(Timestamp.class, SqlDateDeserializer.instance_timestamp);
        this.deserializers.put(Date.class, SqlDateDeserializer.instance);
        this.deserializers.put(Time.class, TimeDeserializer.instance);
        this.deserializers.put(java.util.Date.class, DateCodec.instance);
        this.deserializers.put(Calendar.class, CalendarCodec.instance);
        this.deserializers.put(XMLGregorianCalendar.class, CalendarCodec.instance);
        this.deserializers.put(JSONObject.class, MapDeserializer.instance);
        this.deserializers.put(JSONArray.class, CollectionCodec.instance);
        this.deserializers.put(Map.class, MapDeserializer.instance);
        this.deserializers.put(HashMap.class, MapDeserializer.instance);
        this.deserializers.put(LinkedHashMap.class, MapDeserializer.instance);
        this.deserializers.put(TreeMap.class, MapDeserializer.instance);
        this.deserializers.put(ConcurrentMap.class, MapDeserializer.instance);
        this.deserializers.put(ConcurrentHashMap.class, MapDeserializer.instance);
        this.deserializers.put(Collection.class, CollectionCodec.instance);
        this.deserializers.put(List.class, CollectionCodec.instance);
        this.deserializers.put(ArrayList.class, CollectionCodec.instance);
        this.deserializers.put(Object.class, JavaObjectDeserializer.instance);
        this.deserializers.put(String.class, StringCodec.instance);
        this.deserializers.put(StringBuffer.class, StringCodec.instance);
        this.deserializers.put(StringBuilder.class, StringCodec.instance);
        this.deserializers.put(Character.TYPE, CharacterCodec.instance);
        this.deserializers.put(Character.class, CharacterCodec.instance);
        this.deserializers.put(Byte.TYPE, NumberDeserializer.instance);
        this.deserializers.put(Byte.class, NumberDeserializer.instance);
        this.deserializers.put(Short.TYPE, NumberDeserializer.instance);
        this.deserializers.put(Short.class, NumberDeserializer.instance);
        this.deserializers.put(Integer.TYPE, IntegerCodec.instance);
        this.deserializers.put(Integer.class, IntegerCodec.instance);
        this.deserializers.put(Long.TYPE, LongCodec.instance);
        this.deserializers.put(Long.class, LongCodec.instance);
        this.deserializers.put(BigInteger.class, BigIntegerCodec.instance);
        this.deserializers.put(BigDecimal.class, BigDecimalCodec.instance);
        this.deserializers.put(Float.TYPE, FloatCodec.instance);
        this.deserializers.put(Float.class, FloatCodec.instance);
        this.deserializers.put(Double.TYPE, NumberDeserializer.instance);
        this.deserializers.put(Double.class, NumberDeserializer.instance);
        this.deserializers.put(Boolean.TYPE, BooleanCodec.instance);
        this.deserializers.put(Boolean.class, BooleanCodec.instance);
        this.deserializers.put(Class.class, MiscCodec.instance);
        this.deserializers.put(char[].class, new CharArrayCodec());
        this.deserializers.put(AtomicBoolean.class, BooleanCodec.instance);
        this.deserializers.put(AtomicInteger.class, IntegerCodec.instance);
        this.deserializers.put(AtomicLong.class, LongCodec.instance);
        this.deserializers.put(AtomicReference.class, ReferenceCodec.instance);
        this.deserializers.put(WeakReference.class, ReferenceCodec.instance);
        this.deserializers.put(SoftReference.class, ReferenceCodec.instance);
        this.deserializers.put(UUID.class, MiscCodec.instance);
        this.deserializers.put(TimeZone.class, MiscCodec.instance);
        this.deserializers.put(Locale.class, MiscCodec.instance);
        this.deserializers.put(Currency.class, MiscCodec.instance);
        this.deserializers.put(InetAddress.class, MiscCodec.instance);
        this.deserializers.put(Inet4Address.class, MiscCodec.instance);
        this.deserializers.put(Inet6Address.class, MiscCodec.instance);
        this.deserializers.put(InetSocketAddress.class, MiscCodec.instance);
        this.deserializers.put(File.class, MiscCodec.instance);
        this.deserializers.put(URI.class, MiscCodec.instance);
        this.deserializers.put(URL.class, MiscCodec.instance);
        this.deserializers.put(Pattern.class, MiscCodec.instance);
        this.deserializers.put(Charset.class, MiscCodec.instance);
        this.deserializers.put(JSONPath.class, MiscCodec.instance);
        this.deserializers.put(Number.class, NumberDeserializer.instance);
        this.deserializers.put(AtomicIntegerArray.class, AtomicCodec.instance);
        this.deserializers.put(AtomicLongArray.class, AtomicCodec.instance);
        this.deserializers.put(StackTraceElement.class, StackTraceElementDeserializer.instance);
        this.deserializers.put(Serializable.class, JavaObjectDeserializer.instance);
        this.deserializers.put(Cloneable.class, JavaObjectDeserializer.instance);
        this.deserializers.put(Comparable.class, JavaObjectDeserializer.instance);
        this.deserializers.put(Closeable.class, JavaObjectDeserializer.instance);
        this.deserializers.put(JSONPObject.class, new JSONPDeserializer());
        addItemsToDeny(DENYS);
        addItemsToAccept(AUTO_TYPE_ACCEPT_LIST);
    }

    private static String[] splitItemsFormProperty(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        return str.split(AppInfo.DELIM);
    }

    public void configFromPropety(Properties properties) {
        addItemsToDeny(splitItemsFormProperty(properties.getProperty(DENY_PROPERTY)));
        addItemsToAccept(splitItemsFormProperty(properties.getProperty(AUTOTYPE_ACCEPT)));
        String property = properties.getProperty(AUTOTYPE_SUPPORT_PROPERTY);
        if ("true".equals(property)) {
            this.autoTypeSupport = true;
        } else if (Bugly.SDK_IS_DEV.equals(property)) {
            this.autoTypeSupport = false;
        }
    }

    private void addItemsToDeny(String[] strArr) {
        if (strArr == null) {
            return;
        }
        for (String str : strArr) {
            addDeny(str);
        }
    }

    private void addItemsToAccept(String[] strArr) {
        if (strArr == null) {
            return;
        }
        for (String str : strArr) {
            addAccept(str);
        }
    }

    public boolean isAutoTypeSupport() {
        return this.autoTypeSupport;
    }

    public void setAutoTypeSupport(boolean z) {
        this.autoTypeSupport = z;
    }

    public boolean isAsmEnable() {
        return this.asmEnable;
    }

    public void setAsmEnable(boolean z) {
        this.asmEnable = z;
    }

    public IdentityHashMap<Type, ObjectDeserializer> getDeserializers() {
        return this.deserializers;
    }

    public ObjectDeserializer getDeserializer(Type type) {
        ObjectDeserializer objectDeserializer = this.deserializers.get(type);
        if (objectDeserializer != null) {
            return objectDeserializer;
        }
        if (type instanceof Class) {
            return getDeserializer((Class) type, type);
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            if (rawType instanceof Class) {
                return getDeserializer((Class) rawType, type);
            }
            return getDeserializer(rawType);
        }
        return JavaObjectDeserializer.instance;
    }

    public ObjectDeserializer getDeserializer(Class<?> cls, Type type) {
        ObjectDeserializer objectDeserializerCreateJavaBeanDeserializer;
        int i;
        Class<?> clsMappingTo;
        Type type2 = type;
        ObjectDeserializer objectDeserializer = this.deserializers.get(type2);
        if (objectDeserializer != null) {
            return objectDeserializer;
        }
        if (type2 == null) {
            type2 = cls;
        }
        ObjectDeserializer objectDeserializer2 = this.deserializers.get(type2);
        if (objectDeserializer2 != null) {
            return objectDeserializer2;
        }
        JSONType jSONType = (JSONType) TypeUtils.getAnnotation(cls, JSONType.class);
        if (jSONType != null && (clsMappingTo = jSONType.mappingTo()) != Void.class) {
            return getDeserializer(clsMappingTo, clsMappingTo);
        }
        if ((type2 instanceof WildcardType) || (type2 instanceof TypeVariable) || (type2 instanceof ParameterizedType)) {
            objectDeserializer2 = this.deserializers.get(cls);
        }
        if (objectDeserializer2 != null) {
            return objectDeserializer2;
        }
        String strReplace = cls.getName().replace(Typography.dollar, FilenameUtils.EXTENSION_SEPARATOR);
        int i2 = 0;
        if (strReplace.startsWith("java.awt.") && AwtCodec.support(cls) && !awtError) {
            try {
            } catch (Throwable unused) {
                awtError = true;
            }
            for (String str : new String[]{"java.awt.Point", "java.awt.Font", "java.awt.Rectangle", "java.awt.Color"}) {
                if (str.equals(strReplace)) {
                    IdentityHashMap<Type, ObjectDeserializer> identityHashMap = this.deserializers;
                    Class<?> cls2 = Class.forName(str);
                    AwtCodec awtCodec = AwtCodec.instance;
                    identityHashMap.put(cls2, awtCodec);
                    return awtCodec;
                }
                objectDeserializer2 = AwtCodec.instance;
            }
            objectDeserializer2 = AwtCodec.instance;
        }
        if (!jdk8Error) {
            try {
                if (strReplace.startsWith("java.time.")) {
                    String[] strArr = {"java.time.LocalDateTime", "java.time.LocalDate", "java.time.LocalTime", "java.time.ZonedDateTime", "java.time.OffsetDateTime", "java.time.OffsetTime", "java.time.ZoneOffset", "java.time.ZoneRegion", "java.time.ZoneId", "java.time.Period", "java.time.Duration", "java.time.Instant"};
                    int length = strArr.length;
                    while (i2 < length) {
                        String str2 = strArr[i2];
                        if (str2.equals(strReplace)) {
                            IdentityHashMap<Type, ObjectDeserializer> identityHashMap2 = this.deserializers;
                            Class<?> cls3 = Class.forName(str2);
                            Jdk8DateCodec jdk8DateCodec = Jdk8DateCodec.instance;
                            identityHashMap2.put(cls3, jdk8DateCodec);
                            return jdk8DateCodec;
                        }
                        i2++;
                    }
                } else if (strReplace.startsWith("java.util.Optional")) {
                    String[] strArr2 = {"java.util.Optional", "java.util.OptionalDouble", "java.util.OptionalInt", "java.util.OptionalLong"};
                    int length2 = strArr2.length;
                    while (i2 < length2) {
                        String str3 = strArr2[i2];
                        if (str3.equals(strReplace)) {
                            IdentityHashMap<Type, ObjectDeserializer> identityHashMap3 = this.deserializers;
                            Class<?> cls4 = Class.forName(str3);
                            OptionalCodec optionalCodec = OptionalCodec.instance;
                            identityHashMap3.put(cls4, optionalCodec);
                            return optionalCodec;
                        }
                        i2++;
                    }
                }
            } catch (Throwable unused2) {
                jdk8Error = true;
            }
        }
        if (strReplace.equals("java.nio.file.Path")) {
            IdentityHashMap<Type, ObjectDeserializer> identityHashMap4 = this.deserializers;
            objectDeserializer2 = MiscCodec.instance;
            identityHashMap4.put(cls, objectDeserializer2);
        }
        if (cls == Map.Entry.class) {
            IdentityHashMap<Type, ObjectDeserializer> identityHashMap5 = this.deserializers;
            objectDeserializer2 = MiscCodec.instance;
            identityHashMap5.put(cls, objectDeserializer2);
        }
        try {
            for (AutowiredObjectDeserializer autowiredObjectDeserializer : ServiceLoader.load(AutowiredObjectDeserializer.class, Thread.currentThread().getContextClassLoader())) {
                Iterator<Type> it = autowiredObjectDeserializer.getAutowiredFor().iterator();
                while (it.hasNext()) {
                    this.deserializers.put(it.next(), autowiredObjectDeserializer);
                }
            }
        } catch (Exception unused3) {
        }
        if (objectDeserializer2 == null) {
            objectDeserializer2 = this.deserializers.get(type2);
        }
        if (objectDeserializer2 != null) {
            return objectDeserializer2;
        }
        if (cls.isEnum()) {
            JSONType jSONType2 = (JSONType) cls.getAnnotation(JSONType.class);
            if (jSONType2 != null) {
                try {
                    ObjectDeserializer objectDeserializer3 = (ObjectDeserializer) jSONType2.deserializer().newInstance();
                    this.deserializers.put(cls, objectDeserializer3);
                    return objectDeserializer3;
                } catch (Throwable unused4) {
                }
            }
            objectDeserializerCreateJavaBeanDeserializer = new EnumDeserializer(cls);
        } else if (cls.isArray()) {
            objectDeserializerCreateJavaBeanDeserializer = ObjectArrayCodec.instance;
        } else if (cls == Set.class || cls == HashSet.class || cls == Collection.class || cls == List.class || cls == ArrayList.class || Collection.class.isAssignableFrom(cls)) {
            objectDeserializerCreateJavaBeanDeserializer = CollectionCodec.instance;
        } else if (Map.class.isAssignableFrom(cls)) {
            objectDeserializerCreateJavaBeanDeserializer = MapDeserializer.instance;
        } else if (Throwable.class.isAssignableFrom(cls)) {
            objectDeserializerCreateJavaBeanDeserializer = new ThrowableDeserializer(this, cls);
        } else if (PropertyProcessable.class.isAssignableFrom(cls)) {
            objectDeserializerCreateJavaBeanDeserializer = new PropertyProcessableDeserializer(cls);
        } else {
            objectDeserializerCreateJavaBeanDeserializer = createJavaBeanDeserializer(cls, type2);
        }
        putDeserializer(type2, objectDeserializerCreateJavaBeanDeserializer);
        return objectDeserializerCreateJavaBeanDeserializer;
    }

    public void initJavaBeanDeserializers(Class<?>... clsArr) {
        if (clsArr == null) {
            return;
        }
        for (Class<?> cls : clsArr) {
            if (cls != null) {
                putDeserializer(cls, createJavaBeanDeserializer(cls, cls));
            }
        }
    }

    public ObjectDeserializer createJavaBeanDeserializer(Class<?> cls, Type type) {
        JSONField annotation;
        ASMDeserializerFactory aSMDeserializerFactory;
        boolean zCheckName = this.asmEnable & (!this.fieldBased);
        if (zCheckName) {
            JSONType jSONType = (JSONType) TypeUtils.getAnnotation(cls, JSONType.class);
            if (jSONType != null) {
                Class<?> clsDeserializer = jSONType.deserializer();
                if (clsDeserializer != Void.class) {
                    try {
                        Object objNewInstance = clsDeserializer.newInstance();
                        if (objNewInstance instanceof ObjectDeserializer) {
                            return (ObjectDeserializer) objNewInstance;
                        }
                    } catch (Throwable unused) {
                    }
                }
                zCheckName = jSONType.asm();
            }
            if (zCheckName) {
                Class<?> builderClass = JavaBeanInfo.getBuilderClass(cls, jSONType);
                if (builderClass == null) {
                    builderClass = cls;
                }
                while (true) {
                    if (!Modifier.isPublic(builderClass.getModifiers())) {
                        zCheckName = false;
                        break;
                    }
                    builderClass = builderClass.getSuperclass();
                    if (builderClass == Object.class || builderClass == null) {
                        break;
                    }
                }
            }
        }
        if (cls.getTypeParameters().length != 0) {
            zCheckName = false;
        }
        if (zCheckName && (aSMDeserializerFactory = this.asmFactory) != null && aSMDeserializerFactory.classLoader.isExternalClass(cls)) {
            zCheckName = false;
        }
        if (zCheckName) {
            zCheckName = ASMUtils.checkName(cls.getSimpleName());
        }
        if (zCheckName) {
            if (cls.isInterface()) {
                zCheckName = false;
            }
            JavaBeanInfo javaBeanInfoBuild = JavaBeanInfo.build(cls, type, this.propertyNamingStrategy);
            if (zCheckName && javaBeanInfoBuild.fields.length > 200) {
                zCheckName = false;
            }
            Constructor<?> constructor = javaBeanInfoBuild.defaultConstructor;
            if (zCheckName && constructor == null && !cls.isInterface()) {
                zCheckName = false;
            }
            for (FieldInfo fieldInfo : javaBeanInfoBuild.fields) {
                if (!fieldInfo.getOnly) {
                    Class<?> cls2 = fieldInfo.fieldClass;
                    if (Modifier.isPublic(cls2.getModifiers()) && ((!cls2.isMemberClass() || Modifier.isStatic(cls2.getModifiers())) && ((fieldInfo.getMember() == null || ASMUtils.checkName(fieldInfo.getMember().getName())) && (((annotation = fieldInfo.getAnnotation()) == null || (ASMUtils.checkName(annotation.name()) && annotation.format().length() == 0 && annotation.deserializeUsing() == Void.class && !annotation.unwrapped())) && (!cls2.isEnum() || (getDeserializer(cls2) instanceof EnumDeserializer)))))) {
                    }
                }
                zCheckName = false;
                break;
            }
        }
        if (zCheckName && cls.isMemberClass() && !Modifier.isStatic(cls.getModifiers())) {
            zCheckName = false;
        }
        if (!zCheckName) {
            return new JavaBeanDeserializer(this, cls, type);
        }
        JavaBeanInfo javaBeanInfoBuild2 = JavaBeanInfo.build(cls, type, this.propertyNamingStrategy);
        try {
            return this.asmFactory.createJavaBeanDeserializer(this, javaBeanInfoBuild2);
        } catch (JSONException unused2) {
            return new JavaBeanDeserializer(this, javaBeanInfoBuild2);
        } catch (NoSuchMethodException unused3) {
            return new JavaBeanDeserializer(this, cls, type);
        } catch (Exception e2) {
            throw new JSONException("create asm deserializer error, " + cls.getName(), e2);
        }
    }

    public FieldDeserializer createFieldDeserializer(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, FieldInfo fieldInfo) {
        Class<?> clsDeserializeUsing;
        Class<?> cls = javaBeanInfo.clazz;
        Class<?> cls2 = fieldInfo.fieldClass;
        JSONField annotation = fieldInfo.getAnnotation();
        Class<?> cls3 = null;
        if (annotation != null && (clsDeserializeUsing = annotation.deserializeUsing()) != Void.class) {
            cls3 = clsDeserializeUsing;
        }
        if (cls3 == null && (cls2 == List.class || cls2 == ArrayList.class)) {
            return new ArrayListTypeFieldDeserializer(parserConfig, cls, fieldInfo);
        }
        return new DefaultFieldDeserializer(parserConfig, cls, fieldInfo);
    }

    public void putDeserializer(Type type, ObjectDeserializer objectDeserializer) {
        this.deserializers.put(type, objectDeserializer);
    }

    public ObjectDeserializer getDeserializer(FieldInfo fieldInfo) {
        return getDeserializer(fieldInfo.fieldClass, fieldInfo.fieldType);
    }

    public boolean isPrimitive(Class<?> cls) {
        return isPrimitive2(cls);
    }

    public static boolean isPrimitive2(Class<?> cls) {
        return cls.isPrimitive() || cls == Boolean.class || cls == Character.class || cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == BigInteger.class || cls == BigDecimal.class || cls == String.class || cls == java.util.Date.class || cls == Date.class || cls == Time.class || cls == Timestamp.class || cls.isEnum();
    }

    public static void parserAllFieldToCache(Class<?> cls, Map<String, Field> map) {
        for (Field field : cls.getDeclaredFields()) {
            String name = field.getName();
            if (!map.containsKey(name)) {
                map.put(name, field);
            }
        }
        if (cls.getSuperclass() == null || cls.getSuperclass() == Object.class) {
            return;
        }
        parserAllFieldToCache(cls.getSuperclass(), map);
    }

    public static Field getFieldFromCache(String str, Map<String, Field> map) {
        char cCharAt;
        Field field = map.get(str);
        if (field == null) {
            field = map.get("_" + str);
        }
        if (field == null) {
            field = map.get("m_" + str);
        }
        if (field != null || (cCharAt = str.charAt(0)) < 'a' || cCharAt > 'z') {
            return field;
        }
        char[] charArray = str.toCharArray();
        charArray[0] = (char) (charArray[0] - ' ');
        return map.get(new String(charArray));
    }

    public ClassLoader getDefaultClassLoader() {
        return this.defaultClassLoader;
    }

    public void setDefaultClassLoader(ClassLoader classLoader) {
        this.defaultClassLoader = classLoader;
    }

    public void addDeny(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        for (String str2 : this.denyList) {
            if (str.equals(str2)) {
                return;
            }
        }
        String[] strArr = this.denyList;
        String[] strArr2 = new String[strArr.length + 1];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        strArr2[strArr2.length - 1] = str;
        this.denyList = strArr2;
    }

    public void addAccept(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        for (String str2 : this.acceptList) {
            if (str.equals(str2)) {
                return;
            }
        }
        String[] strArr = this.acceptList;
        String[] strArr2 = new String[strArr.length + 1];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        strArr2[strArr2.length - 1] = str;
        this.acceptList = strArr2;
    }

    public Class<?> checkAutoType(String str, Class<?> cls) {
        if (str == null) {
            return null;
        }
        if (str.length() >= this.maxTypeNameLength) {
            throw new JSONException("autoType is not support. " + str);
        }
        String strReplace = str.replace(Typography.dollar, FilenameUtils.EXTENSION_SEPARATOR);
        int i = 0;
        if (this.autoTypeSupport || cls != null) {
            int i2 = 0;
            while (true) {
                String[] strArr = this.acceptList;
                if (i2 >= strArr.length) {
                    int i3 = 0;
                    while (true) {
                        String[] strArr2 = this.denyList;
                        if (i3 >= strArr2.length) {
                            break;
                        }
                        if (strReplace.startsWith(strArr2[i3]) && TypeUtils.getClassFromMapping(str) == null) {
                            throw new JSONException("autoType is not support. " + str);
                        }
                        i3++;
                    }
                } else {
                    if (strReplace.startsWith(strArr[i2])) {
                        return TypeUtils.loadClass(str, this.defaultClassLoader);
                    }
                    i2++;
                }
            }
        }
        Class<?> classFromMapping = TypeUtils.getClassFromMapping(str);
        if (classFromMapping == null) {
            classFromMapping = this.deserializers.findClass(str);
        }
        if (classFromMapping != null) {
            if (cls == null || cls.isAssignableFrom(classFromMapping)) {
                return classFromMapping;
            }
            throw new JSONException("type not match. " + str + " -> " + cls.getName());
        }
        if (!this.autoTypeSupport) {
            int i4 = 0;
            while (true) {
                String[] strArr3 = this.denyList;
                if (i4 >= strArr3.length) {
                    while (true) {
                        String[] strArr4 = this.acceptList;
                        if (i >= strArr4.length) {
                            break;
                        }
                        if (strReplace.startsWith(strArr4[i])) {
                            Class<?> clsLoadClass = TypeUtils.loadClass(str, this.defaultClassLoader);
                            if (cls == null || !cls.isAssignableFrom(clsLoadClass)) {
                                return clsLoadClass;
                            }
                            throw new JSONException("type not match. " + str + " -> " + cls.getName());
                        }
                        i++;
                    }
                } else {
                    if (strReplace.startsWith(strArr3[i4])) {
                        throw new JSONException("autoType is not support. " + str);
                    }
                    i4++;
                }
            }
        }
        Class<?> clsLoadClass2 = TypeUtils.loadClass(str, this.defaultClassLoader);
        if (clsLoadClass2 != null) {
            if (TypeUtils.getAnnotation(clsLoadClass2, JSONType.class) != null) {
                return clsLoadClass2;
            }
            if (ClassLoader.class.isAssignableFrom(clsLoadClass2) || DataSource.class.isAssignableFrom(clsLoadClass2)) {
                throw new JSONException("autoType is not support. " + str);
            }
            if (cls != null) {
                if (cls.isAssignableFrom(clsLoadClass2)) {
                    return clsLoadClass2;
                }
                throw new JSONException("type not match. " + str + " -> " + cls.getName());
            }
            if (JavaBeanInfo.build(clsLoadClass2, clsLoadClass2, this.propertyNamingStrategy).creatorConstructor != null && this.autoTypeSupport) {
                throw new JSONException("autoType is not support. " + str);
            }
        }
        if (this.autoTypeSupport) {
            return clsLoadClass2;
        }
        throw new JSONException("autoType is not support. " + str);
    }
}