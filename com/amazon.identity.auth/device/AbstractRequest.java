package com.amazon.identity.auth.device;

import android.content.Context;
import android.net.Uri;
import com.amazon.identity.auth.device.interactive.InteractiveRequest;
import com.amazon.identity.auth.device.interactive.InteractiveRequestRecord;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractRequest {
    protected final InteractiveRequest<?, ?, ?, ?> originalRequest;
    protected final String requestId = UUID.randomUUID().toString();
    private int attemptCount = 0;

    public int getMaxAttemptCount() {
        return 1;
    }

    public abstract String getUrl(Context context) throws AuthError;

    public abstract boolean handleResponse(Uri uri, Context context);

    public AbstractRequest(InteractiveRequest<?, ?, ?, ?> interactiveRequest) {
        this.originalRequest = interactiveRequest;
    }

    public InteractiveRequest<?, ?, ?, ?> getOriginalRequest() {
        return this.originalRequest;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void incrementAttemptCount() {
        this.attemptCount++;
    }

    public boolean canAttempt() {
        return this.attemptCount < getMaxAttemptCount();
    }

    public void onStart() {
        InteractiveRequest<?, ?, ?, ?> interactiveRequest = this.originalRequest;
        if (interactiveRequest != null) {
            interactiveRequest.getRequestContext().onStartRequest(getInteractiveRequestRecord());
        }
    }

    public InteractiveRequestRecord getInteractiveRequestRecord() {
        return new InteractiveRequestRecord(this.requestId, this.originalRequest.getRequestExtras());
    }
}