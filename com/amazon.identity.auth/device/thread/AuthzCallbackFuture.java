package com.amazon.identity.auth.device.thread;

import android.os.Bundle;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.authorization.api.AuthorizationListener;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.shared.APIListener;
import com.amazon.identity.auth.map.device.utils.MAPLog;

/* JADX INFO: loaded from: classes.dex */
public class AuthzCallbackFuture extends MAPCallbackFuture implements AuthorizationListener {
    private static final String LOG_TAG = AuthzCallbackFuture.class.getName();
    private Bundle mCancelResult;

    public AuthzCallbackFuture() {
        this((AuthorizationListener) null);
    }

    public AuthzCallbackFuture(AuthorizationListener authorizationListener) {
        super(authorizationListener);
    }

    public AuthzCallbackFuture(final APIListener aPIListener) {
        super(new AuthorizationListener() { // from class: com.amazon.identity.auth.device.thread.AuthzCallbackFuture.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.Listener
            public void onSuccess(Bundle bundle) {
                APIListener aPIListener2 = aPIListener;
                if (aPIListener2 != null) {
                    aPIListener2.onSuccess(bundle);
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.Listener
            public void onError(AuthError authError) {
                APIListener aPIListener2 = aPIListener;
                if (aPIListener2 != null) {
                    aPIListener2.onError(authError);
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.CancellableListener
            public void onCancel(Bundle bundle) {
                MAPLog.w(AuthzCallbackFuture.LOG_TAG, "onCancel called in for APIListener");
            }
        });
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.identity.auth.device.api.CancellableListener
    public void onCancel(Bundle bundle) {
        this.mCancelResult = bundle;
        this.mCancelResult.putSerializable(AuthzConstants.BUNDLE_KEY.FUTURE.val, AuthzConstants.FUTURE_TYPE.CANCEL);
        this.mLatch.countDown();
        this.mListener.onCancel(this.mCancelResult);
    }

    @Override // com.amazon.identity.auth.device.thread.MAPCallbackFuture
    protected Bundle getResultHelper() {
        Bundle bundle = this.mCancelResult;
        return bundle != null ? bundle : super.getResultHelper();
    }
}