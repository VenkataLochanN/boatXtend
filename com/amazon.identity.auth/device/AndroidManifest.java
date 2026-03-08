package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.Intent;
import com.amazon.identity.auth.device.authorization.AuthorizationActivity;
import com.amazon.identity.auth.device.workflow.WorkflowActivity;

/* JADX INFO: loaded from: classes.dex */
final class AndroidManifest {
    private Boolean authorizationActivityDeclared;
    private Boolean workflowActivityDeclared;

    AndroidManifest() {
    }

    public boolean declaresAuthorizationActivity(Context context) {
        if (this.authorizationActivityDeclared == null) {
            this.authorizationActivityDeclared = Boolean.valueOf(declaresIntent(context, new Intent(context, (Class<?>) AuthorizationActivity.class)));
        }
        return this.authorizationActivityDeclared.booleanValue();
    }

    public boolean declaresWorkflowActivity(Context context) {
        if (this.workflowActivityDeclared == null) {
            this.workflowActivityDeclared = Boolean.valueOf(declaresIntent(context, new Intent(context, (Class<?>) WorkflowActivity.class)));
        }
        return this.workflowActivityDeclared.booleanValue();
    }

    boolean declaresIntent(Context context, Intent intent) {
        return !context.getPackageManager().queryIntentActivities(intent, 65536).isEmpty();
    }
}