package com.ido.life.database.upgrade;

/* JADX INFO: loaded from: classes2.dex */
public class UpgradeDataBaseTo2 extends BaseUpgrateDataBase {
    private static final String TAG = "UpgradeDataBaseTo2";

    private void processSportHealthTable(StandardDatabaseWraper standardDatabaseWraper) {
        standardDatabaseWraper.execSql("alter table SPORT_HEALTH add column REAL_TIME_PACE text default \"\" ");
        standardDatabaseWraper.execSql("alter table SPORT_HEALTH add column DISCOVER_DATE_TIME text default \"\" ");
        standardDatabaseWraper.execSql("alter table SPORT_HEALTH add column VO2MAX Integer default 0 ");
        standardDatabaseWraper.execSql("alter table SPORT_HEALTH add column RECOVER_TIME Integer default 0 ");
        standardDatabaseWraper.execSql("alter table SPORT_HEALTH add column IS_SUPPORT_TRAINING_EFFECT Integer default 0 ");
        standardDatabaseWraper.execSql("alter table SPORT_HEALTH add column UPLOADED_STRAVA Integer default 0 ");
        standardDatabaseWraper.execSql("alter table SPORT_HEALTH add column CUMULATIVE_CLIMB Integer default 0 ");
        standardDatabaseWraper.execSql("alter table SPORT_HEALTH add column CUMULATIVE_DECLINE Integer default 0 ");
        standardDatabaseWraper.execSql("alter table SPORT_HEALTH add column TRAINING_EFFECT_SCORE REAL default 0");
    }

    @Override // com.ido.life.database.DatabaseUpgradeUtil.UpgrateDataBase
    public boolean processUpgrade(StandardDatabaseWraper standardDatabaseWraper) {
        processSportHealthTable(standardDatabaseWraper);
        return true;
    }
}