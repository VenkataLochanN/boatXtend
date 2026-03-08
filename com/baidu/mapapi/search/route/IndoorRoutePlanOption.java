package com.baidu.mapapi.search.route;

/* JADX INFO: loaded from: classes.dex */
public class IndoorRoutePlanOption {
    public IndoorPlanNode mFrom = null;
    public IndoorPlanNode mTo = null;

    public IndoorRoutePlanOption from(IndoorPlanNode indoorPlanNode) {
        this.mFrom = indoorPlanNode;
        return this;
    }

    public IndoorRoutePlanOption to(IndoorPlanNode indoorPlanNode) {
        this.mTo = indoorPlanNode;
        return this;
    }
}