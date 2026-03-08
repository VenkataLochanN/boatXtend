package com.ido.life.database;

import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.database.upgrade.StandardDatabaseWraper;
import com.ido.life.database.upgrade.UpgradeDataBaseTo2;
import com.ido.life.database.upgrade.UpgradeDataBaseTo3;
import com.ido.life.database.upgrade.UpgradeDataBaseTo4;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class DatabaseUpgradeUtil {
    private static Map<String, UpgrateDataBase> mUpgradeMap = new HashMap();

    public interface UpgrateDataBase {
        boolean processUpgrade(StandardDatabaseWraper standardDatabaseWraper);
    }

    static {
        mUpgradeMap.put(getDatabaseUpgradeKey(1, 2), new UpgradeDataBaseTo2());
        mUpgradeMap.put(getDatabaseUpgradeKey(2, 3), new UpgradeDataBaseTo3());
        mUpgradeMap.put(getDatabaseUpgradeKey(3, 4), new UpgradeDataBaseTo4());
    }

    public static boolean upgradeDatabase(StandardDatabaseWraper standardDatabaseWraper, int i, int i2) {
        if (standardDatabaseWraper == null) {
            return false;
        }
        boolean zProcessUpgrade = true;
        if (i >= i2) {
            return true;
        }
        while (true) {
            if (i >= i2) {
                break;
            }
            int i3 = i + 1;
            String databaseUpgradeKey = getDatabaseUpgradeKey(i, i3);
            if (mUpgradeMap.containsKey(databaseUpgradeKey)) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDataBaseUpgradePath(), "数据库开始升级:" + databaseUpgradeKey);
                zProcessUpgrade = mUpgradeMap.get(databaseUpgradeKey).processUpgrade(standardDatabaseWraper);
                if (!zProcessUpgrade) {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDataBaseUpgradePath(), "数据库升级失败:" + databaseUpgradeKey);
                    break;
                }
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDataBaseUpgradePath(), "数据库升级成功:" + databaseUpgradeKey);
            }
            i = i3;
        }
        return zProcessUpgrade;
    }

    private static String getDatabaseUpgradeKey(int i, int i2) {
        return i + "-" + i2;
    }
}