package com.amazon.identity.auth.device.interactive;

import com.amazon.identity.auth.device.api.workflow.RequestContext;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class InteractiveRequestMap {
    private static InteractiveRequestMap INSTANCE;
    private final WeakHashMap<Object, RequestContext> sourceContextMap = new WeakHashMap<>();

    InteractiveRequestMap() {
    }

    public static synchronized InteractiveRequestMap getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InteractiveRequestMap();
        }
        return INSTANCE;
    }

    public void putRequestContextForSource(Object obj, RequestContext requestContext) {
        this.sourceContextMap.put(obj, requestContext);
    }

    public RequestContext getRequestContextForSource(Object obj) {
        return this.sourceContextMap.get(obj);
    }
}