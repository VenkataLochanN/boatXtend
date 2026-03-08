package com.ido.alexa.manager;

import com.ido.alexa.AlexaApp;
import com.ido.alexa.bean.AlexaRangerControllerBean;
import com.ido.alexa.bean.AlexaToggleControllerBean;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.alexa.util.GsonUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes2.dex */
public class LoadSmartHomeManager {
    private static AlexaToggleControllerBean alexaOpenViewToggleControllerBean;
    private static AlexaRangerControllerBean alexaRangeControllerBean;
    private static AlexaToggleControllerBean alexaSportToggleControllerBean;

    public static void init() {
        AlexaLogUtil.printAndSave("Load Alexa SmartHomeJson");
        new Thread(new Runnable() { // from class: com.ido.alexa.manager.LoadSmartHomeManager.1
            @Override // java.lang.Runnable
            public void run() {
                LoadSmartHomeManager.loadSportToggleSkillJson();
                LoadSmartHomeManager.loadToggleSkillJson();
                LoadSmartHomeManager.loadRangeSkillJson();
            }
        }).start();
    }

    public static AlexaToggleControllerBean getAlexaSportToggleControllerBean() {
        if (alexaSportToggleControllerBean == null) {
            loadSportToggleSkillJson();
        }
        return alexaSportToggleControllerBean;
    }

    public static AlexaToggleControllerBean getOpenViewToggleControllerBean() {
        if (alexaOpenViewToggleControllerBean == null) {
            loadToggleSkillJson();
        }
        return alexaOpenViewToggleControllerBean;
    }

    public static AlexaRangerControllerBean getAlexaRangeControllerBean() {
        if (alexaRangeControllerBean == null) {
            loadRangeSkillJson();
        }
        return alexaRangeControllerBean;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loadSportToggleSkillJson() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(AlexaApp.getAppContext().getResources().getAssets().open("toggleControllerSkill_sport.json")));
            String str = "";
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    str = str + line;
                } else {
                    alexaSportToggleControllerBean = (AlexaToggleControllerBean) GsonUtil.fromJson(str, AlexaToggleControllerBean.class);
                    return;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loadToggleSkillJson() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(AlexaApp.getAppContext().getResources().getAssets().open("toggleControllerSkill.json")));
            String str = "";
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    str = str + line;
                } else {
                    alexaOpenViewToggleControllerBean = (AlexaToggleControllerBean) GsonUtil.fromJson(str, AlexaToggleControllerBean.class);
                    return;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loadRangeSkillJson() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(AlexaApp.getAppContext().getResources().getAssets().open("rangeControllerSkill.json")));
            String str = "";
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    str = str + line;
                } else {
                    alexaRangeControllerBean = (AlexaRangerControllerBean) GsonUtil.fromJson(str, AlexaRangerControllerBean.class);
                    return;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}