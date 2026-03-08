package com.amazon.identity.auth.device.interactive;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
interface InteractiveStateFragment {
    public static final String TAG_ID = InteractiveState.class.getName() + ".tag";

    Object getApplicationContext();

    Object getFragment(Bundle bundle);

    Object getParentActivity();

    InteractiveState getState();
}