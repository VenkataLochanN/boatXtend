package com.ido.life.database.model.middleModel;

import com.ido.common.net.BaseEntity;
import com.ido.life.database.model.StepDayData;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ServerMultiDaysStepEntity extends BaseEntity {
    private List<StepDayData> result;

    public List<StepDayData> getDatas() {
        return this.result;
    }

    public void setDatas(List<StepDayData> list) {
        this.result = list;
    }
}