package com.amazon.identity.auth.device.workflow;

import android.net.Uri;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class WorkflowResponse {
    private static final String ERROR_DESCRIPTION_PARAMETER_NAME = "error_description";
    private static final String ERROR_PARAMETER_NAME = "error";
    private static final String ERROR_RECOVERABLE_PARAMETER_NAME = "error_recoverable";
    private static final String LOG_TAG = WorkflowResponse.class.getName();
    private static final String WORKFLOW_RESULT_PARAMETER_NAME = "workflow_result";
    private final String error;
    private final String errorDescription;
    private final boolean isRecoverable;
    private final JSONObject workflowResult;

    public WorkflowResponse(Uri uri) {
        this.workflowResult = createResultJson(uri.getQueryParameter(WORKFLOW_RESULT_PARAMETER_NAME));
        this.error = uri.getQueryParameter("error");
        this.errorDescription = uri.getQueryParameter("error_description");
        this.isRecoverable = Boolean.parseBoolean(uri.getQueryParameter(ERROR_RECOVERABLE_PARAMETER_NAME));
    }

    public boolean isError() {
        return getError() != null;
    }

    public AuthError getError() {
        String str;
        String str2 = this.error;
        if (str2 != null && (str = this.errorDescription) != null) {
            return new AuthError(String.format("error=%s error_description=%s", str2, str), AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
        }
        if (this.workflowResult == null) {
            return new AuthError("Failed to parse workflow response", AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
        }
        return null;
    }

    public boolean isRecoverable() {
        return this.isRecoverable;
    }

    public JSONObject getResultJson() {
        return this.workflowResult;
    }

    private JSONObject createResultJson(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (JSONException e2) {
            MAPLog.e(LOG_TAG, "Caught JSONException while parsing workflow result", e2);
            return null;
        }
    }
}