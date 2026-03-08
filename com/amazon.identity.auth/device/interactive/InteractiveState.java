package com.amazon.identity.auth.device.interactive;

import com.amazon.identity.auth.device.api.workflow.RequestContext;

/* JADX INFO: loaded from: classes.dex */
public interface InteractiveState {
    public static final String FRAGMENT_WRAPPER_KEY = "wrappedFragment";

    void onRequestStart(InteractiveRequestRecord interactiveRequestRecord);

    void processPendingResponses(RequestContext requestContext);
}