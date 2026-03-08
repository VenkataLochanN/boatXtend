package com.google.android.gms.fitness.result;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SessionReadResponse extends Response<SessionReadResult> {
    public SessionReadResponse() {
    }

    protected SessionReadResponse(SessionReadResult sessionReadResult) {
        super(sessionReadResult);
    }

    public List<DataSet> getDataSet(Session session) {
        return getResult().getDataSet(session);
    }

    public List<DataSet> getDataSet(Session session, DataType dataType) {
        return getResult().getDataSet(session, dataType);
    }

    public List<Session> getSessions() {
        return getResult().getSessions();
    }

    public Status getStatus() {
        return getResult().getStatus();
    }
}