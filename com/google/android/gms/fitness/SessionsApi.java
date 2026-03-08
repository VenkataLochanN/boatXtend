package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResult;
import com.google.android.gms.fitness.result.SessionStopResult;

/* JADX INFO: loaded from: classes.dex */
public interface SessionsApi {

    public static class ViewIntentBuilder {
        private boolean zzaa = false;
        private final Context zzp;
        private String zzu;
        private Session zzz;

        public ViewIntentBuilder(Context context) {
            this.zzp = context;
        }

        public Intent build() {
            Intent intent;
            ResolveInfo resolveInfoResolveActivity;
            Preconditions.checkState(this.zzz != null, "Session must be set");
            Intent intent2 = new Intent(Fitness.ACTION_VIEW);
            intent2.setType(Session.getMimeType(this.zzz.getActivity()));
            SafeParcelableSerializer.serializeToIntentExtra(this.zzz, intent2, Session.EXTRA_SESSION);
            if (!this.zzaa) {
                this.zzu = this.zzz.getAppPackageName();
            }
            if (this.zzu == null || (resolveInfoResolveActivity = this.zzp.getPackageManager().resolveActivity((intent = new Intent(intent2).setPackage(this.zzu)), 0)) == null) {
                return intent2;
            }
            intent.setComponent(new ComponentName(this.zzu, resolveInfoResolveActivity.activityInfo.name));
            return intent;
        }

        public ViewIntentBuilder setPreferredApplication(String str) {
            this.zzu = str;
            this.zzaa = true;
            return this;
        }

        public ViewIntentBuilder setSession(Session session) {
            this.zzz = session;
            return this;
        }
    }

    PendingResult<Status> insertSession(GoogleApiClient googleApiClient, SessionInsertRequest sessionInsertRequest);

    PendingResult<SessionReadResult> readSession(GoogleApiClient googleApiClient, SessionReadRequest sessionReadRequest);

    PendingResult<Status> registerForSessions(GoogleApiClient googleApiClient, PendingIntent pendingIntent);

    PendingResult<Status> startSession(GoogleApiClient googleApiClient, Session session);

    PendingResult<SessionStopResult> stopSession(GoogleApiClient googleApiClient, String str);

    PendingResult<Status> unregisterForSessions(GoogleApiClient googleApiClient, PendingIntent pendingIntent);
}