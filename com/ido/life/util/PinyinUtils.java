package com.ido.life.util;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/* JADX INFO: compiled from: PinyinUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¨\u0006\u0006"}, d2 = {"Lcom/ido/life/util/PinyinUtils;", "", "()V", "convertChinese2Pinyin", "", "chinese", "app_release"}, k = 1, mv = {1, 1, 16})
public final class PinyinUtils {
    public static final PinyinUtils INSTANCE = new PinyinUtils();

    private PinyinUtils() {
    }

    @JvmStatic
    public static final String convertChinese2Pinyin(String chinese) throws BadHanyuPinyinOutputFormatCombination {
        Intrinsics.checkParameterIsNotNull(chinese, "chinese");
        HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String hanYuPinyinString = PinyinHelper.toHanYuPinyinString(chinese, hanyuPinyinOutputFormat, "", true);
        Intrinsics.checkExpressionValueIsNotNull(hanYuPinyinString, "PinyinHelper.toHanYuPiny…hinese, format, \"\", true)");
        return hanYuPinyinString;
    }
}