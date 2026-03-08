package com.amazon.identity.auth.device.workflow;

import android.os.Bundle;
import com.amazon.identity.auth.device.workflow.WorkflowConstants;

/* JADX INFO: loaded from: classes.dex */
public final class WorkflowCancellation {
    private final Cause cause;
    private final String description;

    public enum Cause {
        USER_TERMINATED;

        static Cause fromCode(int i) {
            return USER_TERMINATED;
        }
    }

    public WorkflowCancellation(Bundle bundle) {
        this(Cause.fromCode(bundle.getInt(WorkflowConstants.API.CANCELLATION_CODE.val)), bundle.getString(WorkflowConstants.API.CANCELLATION_DESCRIPTION.val));
    }

    public WorkflowCancellation(Cause cause, String str) {
        this.cause = cause;
        this.description = str;
    }

    public Cause getCause() {
        return this.cause;
    }

    public String getDescription() {
        return this.description;
    }

    public int hashCode() {
        Cause cause = this.cause;
        int iHashCode = ((cause == null ? 0 : cause.hashCode()) + 31) * 31;
        String str = this.description;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WorkflowCancellation workflowCancellation = (WorkflowCancellation) obj;
        if (this.cause != workflowCancellation.cause) {
            return false;
        }
        String str = this.description;
        if (str == null) {
            if (workflowCancellation.description != null) {
                return false;
            }
        } else if (!str.equals(workflowCancellation.description)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return String.format("%s {cause='%s', description='%s'}", super.toString(), this.cause.toString(), this.description);
    }
}