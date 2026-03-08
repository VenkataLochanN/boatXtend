package com.amazon.identity.auth.device.workflow;

import android.content.Context;
import android.net.Uri;
import com.amazon.identity.auth.device.interactive.InteractiveListener;
import com.amazon.identity.auth.device.interactive.InteractiveRequestRecord;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseWorkflowListener<T, U, V> implements InteractiveListener<T, U, V> {
    private static final String LOG_TAG = BaseWorkflowListener.class.getName();

    protected abstract void onWorkflowCancel(Context context, InteractiveRequestRecord interactiveRequestRecord, WorkflowCancellation workflowCancellation);

    protected abstract void onWorkflowError(Context context, InteractiveRequestRecord interactiveRequestRecord, Exception exc);

    protected abstract void onWorkflowSuccess(Context context, InteractiveRequestRecord interactiveRequestRecord, JSONObject jSONObject);

    @Override // com.amazon.identity.auth.device.interactive.InternalInteractiveListener
    public final void onRequestCompletion(Context context, InteractiveRequestRecord interactiveRequestRecord, Uri uri) {
        WorkflowResponse workflowResponse = new WorkflowResponse(uri);
        if (workflowResponse.isError()) {
            MAPLog.d(LOG_TAG, "onRequestCompletion failure: " + workflowResponse.getError().getMessage());
            onWorkflowError(context, interactiveRequestRecord, workflowResponse.getError());
            return;
        }
        MAPLog.pii(LOG_TAG, "onRequestCompletion success", "result=" + workflowResponse.getResultJson());
        onWorkflowSuccess(context, interactiveRequestRecord, workflowResponse.getResultJson());
    }

    @Override // com.amazon.identity.auth.device.interactive.InternalInteractiveListener
    public final void onRequestError(Context context, InteractiveRequestRecord interactiveRequestRecord, Exception exc) {
        MAPLog.d(LOG_TAG, "onRequestError: " + exc.getMessage());
        onWorkflowError(context, interactiveRequestRecord, exc);
    }

    @Override // com.amazon.identity.auth.device.interactive.InternalInteractiveListener
    public final void onRequestCancel(Context context, InteractiveRequestRecord interactiveRequestRecord, WorkflowCancellation workflowCancellation) {
        MAPLog.d(LOG_TAG, "onRequestCancel");
        onWorkflowCancel(context, interactiveRequestRecord, workflowCancellation);
    }
}