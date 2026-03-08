package com.ido.life.database.upgrade;

/* JADX INFO: loaded from: classes2.dex */
public class UpgradeDataBaseTo4 extends BaseUpgrateDataBase {
    @Override // com.ido.life.database.DatabaseUpgradeUtil.UpgrateDataBase
    public boolean processUpgrade(StandardDatabaseWraper standardDatabaseWraper) {
        return processHomeCard(standardDatabaseWraper);
    }

    private boolean processHomeCard(StandardDatabaseWraper standardDatabaseWraper) {
        return standardDatabaseWraper.execSql("alter table HOME_CARD add column HIDE_VALUE_LIST text default null");
    }
}