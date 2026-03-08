package com.alibaba.fastjson.support.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.util.IOUtils;
import com.bumptech.glide.load.Key;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.nio.charset.Charset;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

/* JADX INFO: loaded from: classes.dex */
public class FastJsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> implements GenericHttpMessageConverter<Object> {
    public static final MediaType APPLICATION_JAVASCRIPT = new MediaType("application", "javascript");
    private static final byte[] JSONP_FUNCTION_PREFIX_BYTES = "/**/".getBytes(IOUtils.UTF8);
    private static final byte[] JSONP_FUNCTION_SUFFIX_BYTES = ");".getBytes(IOUtils.UTF8);
    private Charset charset;

    @Deprecated
    protected String dateFormat;
    private FastJsonConfig fastJsonConfig;

    @Deprecated
    protected SerializerFeature[] features;

    @Deprecated
    protected SerializeFilter[] filters;

    protected boolean supports(Class<?> cls) {
        return true;
    }

    public FastJsonConfig getFastJsonConfig() {
        return this.fastJsonConfig;
    }

    public void setFastJsonConfig(FastJsonConfig fastJsonConfig) {
        this.fastJsonConfig = fastJsonConfig;
    }

    public FastJsonHttpMessageConverter() {
        super(MediaType.ALL);
        this.charset = Charset.forName(Key.STRING_CHARSET_NAME);
        this.features = new SerializerFeature[0];
        this.filters = new SerializeFilter[0];
        this.fastJsonConfig = new FastJsonConfig();
    }

    @Deprecated
    public Charset getCharset() {
        return this.fastJsonConfig.getCharset();
    }

    @Deprecated
    public void setCharset(Charset charset) {
        this.fastJsonConfig.setCharset(charset);
    }

    @Deprecated
    public String getDateFormat() {
        return this.fastJsonConfig.getDateFormat();
    }

    @Deprecated
    public void setDateFormat(String str) {
        this.fastJsonConfig.setDateFormat(str);
    }

    @Deprecated
    public SerializerFeature[] getFeatures() {
        return this.fastJsonConfig.getSerializerFeatures();
    }

    @Deprecated
    public void setFeatures(SerializerFeature... serializerFeatureArr) {
        this.fastJsonConfig.setSerializerFeatures(serializerFeatureArr);
    }

    @Deprecated
    public SerializeFilter[] getFilters() {
        return this.fastJsonConfig.getSerializeFilters();
    }

    @Deprecated
    public void setFilters(SerializeFilter... serializeFilterArr) {
        this.fastJsonConfig.setSerializeFilters(serializeFilterArr);
    }

    @Deprecated
    public void addSerializeFilter(SerializeFilter serializeFilter) {
        if (serializeFilter == null) {
            return;
        }
        int length = this.fastJsonConfig.getSerializeFilters().length;
        SerializeFilter[] serializeFilterArr = new SerializeFilter[length + 1];
        System.arraycopy(this.fastJsonConfig.getSerializeFilters(), 0, serializeFilterArr, 0, length);
        serializeFilterArr[serializeFilterArr.length - 1] = serializeFilter;
        this.fastJsonConfig.setSerializeFilters(serializeFilterArr);
    }

    public boolean canRead(Type type, Class<?> cls, MediaType mediaType) {
        return super.canRead(cls, mediaType);
    }

    public boolean canWrite(Type type, Class<?> cls, MediaType mediaType) {
        return super.canWrite(cls, mediaType);
    }

    public Object read(Type type, Class<?> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return readType(getType(type, cls), httpInputMessage);
    }

    public void write(Object obj, Type type, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        super.write(obj, mediaType, httpOutputMessage);
    }

    protected Object readInternal(Class<? extends Object> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return readType(getType(cls, null), httpInputMessage);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.springframework.http.converter.HttpMessageNotReadableException */
    private Object readType(Type type, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        try {
            return JSON.parseObject(httpInputMessage.getBody(), this.fastJsonConfig.getCharset(), type, this.fastJsonConfig.getFeatures());
        } catch (JSONException e2) {
            throw new HttpMessageNotReadableException("JSON parse error: " + e2.getMessage(), e2);
        } catch (IOException e3) {
            throw new HttpMessageNotReadableException("I/O error while reading input message", e3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0084 A[Catch: all -> 0x00a0, JSONException -> 0x00a2, TryCatch #1 {JSONException -> 0x00a2, blocks: (B:3:0x0005, B:5:0x0021, B:6:0x0032, B:8:0x0037, B:13:0x0046, B:15:0x0084, B:16:0x0089, B:18:0x0091, B:19:0x0095, B:9:0x003f), top: B:29:0x0005, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0091 A[Catch: all -> 0x00a0, JSONException -> 0x00a2, TryCatch #1 {JSONException -> 0x00a2, blocks: (B:3:0x0005, B:5:0x0021, B:6:0x0032, B:8:0x0037, B:13:0x0046, B:15:0x0084, B:16:0x0089, B:18:0x0091, B:19:0x0095, B:9:0x003f), top: B:29:0x0005, outer: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void writeInternal(java.lang.Object r14, org.springframework.http.HttpOutputMessage r15) throws java.io.IOException, org.springframework.http.converter.HttpMessageNotWritableException {
        /*
            r13 = this;
            java.io.ByteArrayOutputStream r8 = new java.io.ByteArrayOutputStream
            r8.<init>()
            org.springframework.http.HttpHeaders r9 = r15.getHeaders()     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            com.alibaba.fastjson.support.config.FastJsonConfig r0 = r13.fastJsonConfig     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            com.alibaba.fastjson.serializer.SerializeFilter[] r0 = r0.getSerializeFilters()     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            java.util.List r0 = java.util.Arrays.asList(r0)     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            r1.<init>(r0)     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            r0 = 0
            java.lang.Object r2 = r13.strangeCodeForJackson(r14)     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            boolean r3 = r2 instanceof com.alibaba.fastjson.support.spring.FastJsonContainer     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            if (r3 == 0) goto L32
            com.alibaba.fastjson.support.spring.FastJsonContainer r2 = (com.alibaba.fastjson.support.spring.FastJsonContainer) r2     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            com.alibaba.fastjson.support.spring.PropertyPreFilters r3 = r2.getFilters()     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            java.util.List r3 = r3.getFilters()     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            r1.addAll(r3)     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            java.lang.Object r2 = r2.getValue()     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
        L32:
            boolean r3 = r2 instanceof com.alibaba.fastjson.support.spring.MappingFastJsonValue     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            r4 = 1
            if (r3 == 0) goto L3f
            com.alibaba.fastjson.support.spring.MappingFastJsonValue r2 = (com.alibaba.fastjson.support.spring.MappingFastJsonValue) r2     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            java.lang.Object r0 = r2.getValue()     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            r2 = r0
            goto L43
        L3f:
            boolean r3 = r2 instanceof com.alibaba.fastjson.JSONPObject     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            if (r3 == 0) goto L45
        L43:
            r10 = r4
            goto L46
        L45:
            r10 = r0
        L46:
            int r11 = r13.writePrefix(r8, r14)     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            com.alibaba.fastjson.support.config.FastJsonConfig r0 = r13.fastJsonConfig     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            java.nio.charset.Charset r3 = r0.getCharset()     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            com.alibaba.fastjson.support.config.FastJsonConfig r0 = r13.fastJsonConfig     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            com.alibaba.fastjson.serializer.SerializeConfig r4 = r0.getSerializeConfig()     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            int r0 = r1.size()     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            com.alibaba.fastjson.serializer.SerializeFilter[] r0 = new com.alibaba.fastjson.serializer.SerializeFilter[r0]     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            java.lang.Object[] r0 = r1.toArray(r0)     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            r5 = r0
            com.alibaba.fastjson.serializer.SerializeFilter[] r5 = (com.alibaba.fastjson.serializer.SerializeFilter[]) r5     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            com.alibaba.fastjson.support.config.FastJsonConfig r0 = r13.fastJsonConfig     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            java.lang.String r6 = r0.getDateFormat()     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            int r7 = com.alibaba.fastjson.JSON.DEFAULT_GENERATE_FEATURE     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            com.alibaba.fastjson.support.config.FastJsonConfig r0 = r13.fastJsonConfig     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            com.alibaba.fastjson.serializer.SerializerFeature[] r12 = r0.getSerializerFeatures()     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            r0 = r8
            r1 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r12
            int r0 = com.alibaba.fastjson.JSON.writeJSONString(r0, r1, r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            int r11 = r11 + r0
            int r14 = r13.writeSuffix(r8, r14)     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            int r11 = r11 + r14
            if (r10 == 0) goto L89
            org.springframework.http.MediaType r14 = com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter.APPLICATION_JAVASCRIPT     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            r9.setContentType(r14)     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
        L89:
            com.alibaba.fastjson.support.config.FastJsonConfig r14 = r13.fastJsonConfig     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            boolean r14 = r14.isWriteContentLength()     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            if (r14 == 0) goto L95
            long r0 = (long) r11     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            r9.setContentLength(r0)     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
        L95:
            java.io.OutputStream r14 = r15.getBody()     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            r8.writeTo(r14)     // Catch: java.lang.Throwable -> La0 com.alibaba.fastjson.JSONException -> La2
            r8.close()
            return
        La0:
            r14 = move-exception
            goto Lbe
        La2:
            r14 = move-exception
            org.springframework.http.converter.HttpMessageNotWritableException r15 = new org.springframework.http.converter.HttpMessageNotWritableException     // Catch: java.lang.Throwable -> La0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La0
            r0.<init>()     // Catch: java.lang.Throwable -> La0
            java.lang.String r1 = "Could not write JSON: "
            r0.append(r1)     // Catch: java.lang.Throwable -> La0
            java.lang.String r1 = r14.getMessage()     // Catch: java.lang.Throwable -> La0
            r0.append(r1)     // Catch: java.lang.Throwable -> La0
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> La0
            r15.<init>(r0, r14)     // Catch: java.lang.Throwable -> La0
            throw r15     // Catch: java.lang.Throwable -> La0
        Lbe:
            r8.close()
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter.writeInternal(java.lang.Object, org.springframework.http.HttpOutputMessage):void");
    }

    private Object strangeCodeForJackson(Object obj) {
        return (obj == null || !"com.fasterxml.jackson.databind.node.ObjectNode".equals(obj.getClass().getName())) ? obj : obj.toString();
    }

    protected int writePrefix(ByteArrayOutputStream byteArrayOutputStream, Object obj) throws IOException {
        String jsonpFunction = obj instanceof MappingFastJsonValue ? ((MappingFastJsonValue) obj).getJsonpFunction() : null;
        if (jsonpFunction == null) {
            return 0;
        }
        byteArrayOutputStream.write(JSONP_FUNCTION_PREFIX_BYTES);
        byte[] bytes = (jsonpFunction + "(").getBytes(IOUtils.UTF8);
        byteArrayOutputStream.write(bytes);
        return 0 + JSONP_FUNCTION_PREFIX_BYTES.length + bytes.length;
    }

    protected int writeSuffix(ByteArrayOutputStream byteArrayOutputStream, Object obj) throws IOException {
        if ((obj instanceof MappingFastJsonValue ? ((MappingFastJsonValue) obj).getJsonpFunction() : null) == null) {
            return 0;
        }
        byteArrayOutputStream.write(JSONP_FUNCTION_SUFFIX_BYTES);
        return 0 + JSONP_FUNCTION_SUFFIX_BYTES.length;
    }

    protected Type getType(Type type, Class<?> cls) {
        if (cls == null) {
            return type;
        }
        ResolvableType resolvableTypeForType = ResolvableType.forType(type);
        if (type instanceof TypeVariable) {
            ResolvableType resolvableTypeResolveVariable = resolveVariable((TypeVariable) type, ResolvableType.forClass(cls));
            return resolvableTypeResolveVariable != ResolvableType.NONE ? resolvableTypeResolveVariable.resolve() : type;
        }
        if (!(type instanceof ParameterizedType) || !resolvableTypeForType.hasUnresolvableGenerics()) {
            return type;
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Class[] clsArr = new Class[parameterizedType.getActualTypeArguments().length];
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (int i = 0; i < actualTypeArguments.length; i++) {
            Type type2 = actualTypeArguments[i];
            if (type2 instanceof TypeVariable) {
                ResolvableType resolvableTypeResolveVariable2 = resolveVariable((TypeVariable) type2, ResolvableType.forClass(cls));
                if (resolvableTypeResolveVariable2 != ResolvableType.NONE) {
                    clsArr[i] = resolvableTypeResolveVariable2.resolve();
                } else {
                    clsArr[i] = ResolvableType.forType(type2).resolve();
                }
            } else {
                clsArr[i] = ResolvableType.forType(type2).resolve();
            }
        }
        return ResolvableType.forClassWithGenerics(resolvableTypeForType.getRawClass(), clsArr).getType();
    }

    private ResolvableType resolveVariable(TypeVariable<?> typeVariable, ResolvableType resolvableType) {
        if (resolvableType.hasGenerics()) {
            ResolvableType resolvableTypeForType = ResolvableType.forType(typeVariable, resolvableType);
            if (resolvableTypeForType.resolve() != null) {
                return resolvableTypeForType;
            }
        }
        ResolvableType superType = resolvableType.getSuperType();
        if (superType != ResolvableType.NONE) {
            ResolvableType resolvableTypeResolveVariable = resolveVariable(typeVariable, superType);
            if (resolvableTypeResolveVariable.resolve() != null) {
                return resolvableTypeResolveVariable;
            }
        }
        for (ResolvableType resolvableType2 : resolvableType.getInterfaces()) {
            ResolvableType resolvableTypeResolveVariable2 = resolveVariable(typeVariable, resolvableType2);
            if (resolvableTypeResolveVariable2.resolve() != null) {
                return resolvableTypeResolveVariable2;
            }
        }
        return ResolvableType.NONE;
    }
}