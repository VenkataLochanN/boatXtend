package com.amazon.identity.auth.device.authorization;

import android.app.Activity;
import android.os.Bundle;
import com.amazon.identity.auth.device.workflow.WorkflowActivity;
import com.amazon.identity.auth.map.device.utils.MAPLog;

/* JADX INFO: loaded from: classes.dex */
public class AuthorizationActivity extends Activity {
    private static final String LOG_TAG = AuthorizationActivity.class.getName();

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MAPLog.d(LOG_TAG, "onCreate");
        WorkflowActivity.handleResponseUri(getIntent().getData(), this, LOG_TAG);
        MAPLog.d(LOG_TAG, "finish");
        finish();
    }
}