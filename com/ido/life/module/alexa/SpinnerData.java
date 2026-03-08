package com.ido.life.module.alexa;

import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaConstant;
import com.ido.common.utils.LanguageUtil;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class SpinnerData {
    private String code;
    private int imageRes;
    private String name;
    public static final String[] languages = {AlexaConstant.LANGUAGE_DE_DE, AlexaConstant.LANGUAGE_EN_AU, AlexaConstant.LANGUAGE_EN_CA, AlexaConstant.LANGUAGE_EN_GB, AlexaConstant.LANGUAGE_EN_IN, AlexaConstant.LANGUAGE_EN_US, AlexaConstant.LANGUAGE_ES_ES, AlexaConstant.LANGUAGE_ES_MX, AlexaConstant.LANGUAGE_ES_US, AlexaConstant.LANGUAGE_FR_CA, AlexaConstant.LANGUAGE_FR_FR, AlexaConstant.LANGUAGE_IT_IT, AlexaConstant.LANGUAGE_JA_JP, AlexaConstant.LANGUAGE_PT_BR};
    public static String[] arrays = {LanguageUtil.getLanguageText(R.string.alexa_lang_de), LanguageUtil.getLanguageText(R.string.alexa_lang_en_au), LanguageUtil.getLanguageText(R.string.alexa_lang_en_ca), LanguageUtil.getLanguageText(R.string.alexa_lang_en_gb), LanguageUtil.getLanguageText(R.string.alexa_lang_en_in), LanguageUtil.getLanguageText(R.string.alexa_lang_en_us), LanguageUtil.getLanguageText(R.string.alexa_lang_es_es), LanguageUtil.getLanguageText(R.string.alexa_lang_es_mx), LanguageUtil.getLanguageText(R.string.alexa_lang_es_us), LanguageUtil.getLanguageText(R.string.alexa_lang_fr_ca), LanguageUtil.getLanguageText(R.string.alexa_lang_fr_fr), LanguageUtil.getLanguageText(R.string.alexa_lang_it_it), LanguageUtil.getLanguageText(R.string.alexa_lang_ja_jp), LanguageUtil.getLanguageText(R.string.alexa_lang_pt_br)};
    public static int[] res = {R.mipmap.alexa_icon_de, R.mipmap.alexa_icon_au, R.mipmap.alexa_icon_ca, R.mipmap.alexa_icon_gb, R.mipmap.alexa_icon_in, R.mipmap.alexa_icon_us, R.mipmap.alexa_icon_es, R.mipmap.alexa_icon_mx, R.mipmap.alexa_icon_us, R.mipmap.alexa_icon_ca, R.mipmap.alexa_icon_fr, R.mipmap.alexa_icon_it, R.mipmap.alexa_icon_jp, R.mipmap.alexa_icon_pt};
    public static ArrayList<SpinnerData> data = new ArrayList<>();

    static {
        for (int i = 0; i < languages.length; i++) {
            SpinnerData spinnerData = new SpinnerData();
            spinnerData.setName(arrays[i]);
            spinnerData.setCode(languages[i]);
            spinnerData.setImageRes(res[i]);
            data.add(spinnerData);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public int getImageRes() {
        return this.imageRes;
    }

    public void setImageRes(int i) {
        this.imageRes = i;
    }
}