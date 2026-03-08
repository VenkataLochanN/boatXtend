package com.ido.ble.protocol.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class Goal implements Serializable {
    private static final long serialVersionUID = 1;

    @Deprecated
    public int sleep_time;
    public int sport_step;
    public int walk_goal_steps;

    public String toString() {
        return "Goal{sport_step=" + this.sport_step + ", sleep_time=" + this.sleep_time + ", walk_goal_steps=" + this.walk_goal_steps + '}';
    }
}