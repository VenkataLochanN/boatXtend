package com.ido.common.utils;

import android.text.TextUtils;
import com.google.zxing.common.StringUtils;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.UByte;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes2.dex */
public class StringUtil {
    private static String CHAR_ENCODE = "UTF-8";
    private static Object initLock = new Object();
    private static char[] numbersAndLetters = null;
    private static Random randGen;

    public static String format(String str, Object... objArr) {
        try {
            return String.format(Locale.CHINA, str, objArr);
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    public static final int getCharnum(String str, char c2) {
        int i = 0;
        for (char c3 : str.toCharArray()) {
            if (c2 == c3) {
                i++;
            }
        }
        return i;
    }

    public static String[] split(String str, int i) {
        if (str == null || str.equals("")) {
            return null;
        }
        int length = str.length();
        int i2 = 0;
        if (length <= i) {
            return new String[]{str};
        }
        int i3 = (length / i) + 1;
        System.out.println("i:" + i3);
        String[] strArr = new String[i3];
        while (i2 < i3) {
            int i4 = i2 * i;
            int i5 = i2 + 1;
            int i6 = i5 * i;
            if (i6 > length) {
                i6 = length;
            }
            strArr[i2] = str.substring(i4, i6);
            i2 = i5;
        }
        return strArr;
    }

    public static boolean emailFormat(String str) {
        return Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").matcher(str).find();
    }

    public static String checkStr(String str) {
        return str == null ? "" : str.length() > 256 ? str.substring(256) : str;
    }

    public static boolean validateInt(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt < '0' || cCharAt > '9') {
                return false;
            }
        }
        return true;
    }

    public static String byte2hex(byte[] bArr) {
        String str = "";
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i] & UByte.MAX_VALUE);
            str = hexString.length() == 1 ? str + AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + hexString : str + hexString;
            if (i < bArr.length - 1) {
                str = str + ":";
            }
        }
        return str.toUpperCase();
    }

    public static String byte2string(byte[] bArr) {
        String str = "";
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & UByte.MAX_VALUE);
            str = hexString.length() == 1 ? str + AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + hexString : str + hexString;
        }
        return str.toUpperCase();
    }

    public static byte[] string2byte(String str) {
        byte[] bArr = new byte[str.length() / 2];
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 2;
            bArr[i / 2] = new Integer(Integer.parseInt(str.substring(i, i2), 16)).byteValue();
            i = i2;
        }
        return bArr;
    }

    public static boolean isLetterOrDigit(String str) {
        try {
            return true ^ Pattern.compile("[^0-9A-Za-z]").matcher(str).find();
        } catch (Exception unused) {
            return true;
        }
    }

    private static boolean isLowerLetter(String str) {
        try {
            return true ^ Pattern.compile("[^a-z]").matcher(str).find();
        } catch (Exception unused) {
            return true;
        }
    }

    public static String getNumbers(String str) {
        Matcher matcher = Pattern.compile("\\d+").matcher(str);
        return matcher.find() ? matcher.group(0) : "";
    }

    public static String splitNotNumber(String str) {
        Matcher matcher = Pattern.compile("\\D+").matcher(str);
        return matcher.find() ? matcher.group(0) : "";
    }

    public static String encode(String str, String str2) {
        try {
            return URLEncoder.encode(str, str2);
        } catch (Exception e2) {
            e2.fillInStackTrace();
            return "";
        }
    }

    public static String decode(String str, String str2) {
        try {
            return URLDecoder.decode(str, str2);
        } catch (Exception e2) {
            e2.fillInStackTrace();
            return "";
        }
    }

    public static String nvl(String str) {
        return str != null ? str.trim() : "";
    }

    public static int parseInt(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i;
        }
    }

    public static int parseInt(String str) {
        return parseInt(str, 0);
    }

    public static long parseLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static double parseDouble(String str) {
        return parseDouble(str, 1.0d);
    }

    public static double parseDouble(String str, double d2) {
        try {
            return Double.parseDouble(str.trim());
        } catch (Exception unused) {
            return d2;
        }
    }

    public static boolean parseBoolean(String str) {
        if (isEmpty(str)) {
            return false;
        }
        char cCharAt = str.charAt(0);
        return cCharAt == '1' || cCharAt == 'T' || cCharAt == 'Y' || cCharAt == 't' || cCharAt == 'y';
    }

    public static final String[] split(String str, String str2) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, str2);
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String substring(String str, int i) {
        if (str == null) {
            return null;
        }
        if (str.length() <= i) {
            return str;
        }
        return str.substring(0, i - 2) + "...";
    }

    public static boolean validateDouble(String str) throws RuntimeException {
        if (str == null) {
            return false;
        }
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if ((cCharAt < '0' || cCharAt > '9') && (i2 != 0 || (cCharAt != '-' && cCharAt != '+'))) {
                if (cCharAt != '.' || i2 >= length - 1 || i >= 1) {
                    return false;
                }
                i++;
            }
        }
        return true;
    }

    public static String gbToIso(String str) throws UnsupportedEncodingException {
        return covertCode(str, StringUtils.GB2312, "ISO8859-1");
    }

    public static String covertCode(String str, String str2, String str3) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return str.trim().equals("") ? "" : new String(str.getBytes(str2), str3);
    }

    public static String transCode(String str) throws UnsupportedEncodingException {
        if (str == null || str.trim().equals("")) {
            return null;
        }
        return new String(str.trim().getBytes("GBK"), "ISO8859-1");
    }

    public static String GBToUTF8(String str) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            byte[] bytes = str.getBytes("unicode");
            for (int i = 2; i < bytes.length - 1; i += 2) {
                stringBuffer.append("\\u");
                String hexString = Integer.toHexString(bytes[i + 1] & UByte.MAX_VALUE);
                for (int length = hexString.length(); length < 2; length++) {
                    stringBuffer.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                }
                stringBuffer.append(hexString);
                String hexString2 = Integer.toHexString(bytes[i] & UByte.MAX_VALUE);
                for (int length2 = hexString2.length(); length2 < 2; length2++) {
                    stringBuffer.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                }
                stringBuffer.append(hexString2);
            }
            return stringBuffer.toString();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static final String[] replaceAll(String[] strArr, String str, String str2) {
        if (strArr == null) {
            return null;
        }
        int length = strArr.length;
        String[] strArr2 = new String[length];
        for (int i = 0; i < length; i++) {
            strArr2[i] = replaceAll(strArr[i], str, str2);
        }
        return strArr2;
    }

    public static String replaceAll(String str, String str2, String str3) {
        if (str == null || str.trim().equals("")) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(str);
        int iIndexOf = str.indexOf(str2);
        while (iIndexOf > -1) {
            stringBuffer = stringBuffer.replace(iIndexOf, str2.length() + iIndexOf, str3);
            str = stringBuffer.toString();
            iIndexOf = str.indexOf(str2, iIndexOf + str3.length());
        }
        return str;
    }

    public static String toHtml(String str) {
        return (str == null || str.length() == 0) ? str : replaceAll(replaceAll(replaceAll(replaceAll(replaceAll(replaceAll(replaceAll(str, "&", "&amp;"), "<", "&lt;"), ">", "&gt;"), IOUtils.LINE_SEPARATOR_WINDOWS, IOUtils.LINE_SEPARATOR_UNIX), IOUtils.LINE_SEPARATOR_UNIX, "<br>\n"), "\t", "         "), " ", "&nbsp;");
    }

    public static final String replace(String str, String str2, String str3) {
        if (str == null) {
            return null;
        }
        int iIndexOf = str.indexOf(str2, 0);
        if (iIndexOf < 0) {
            return str;
        }
        char[] charArray = str.toCharArray();
        char[] charArray2 = str3.toCharArray();
        int length = str2.length();
        StringBuffer stringBuffer = new StringBuffer(charArray.length);
        stringBuffer.append(charArray, 0, iIndexOf);
        stringBuffer.append(charArray2);
        int i = iIndexOf + length;
        while (true) {
            int iIndexOf2 = str.indexOf(str2, i);
            if (iIndexOf2 > 0) {
                stringBuffer.append(charArray, i, iIndexOf2 - i);
                stringBuffer.append(charArray2);
                i = iIndexOf2 + length;
            } else {
                stringBuffer.append(charArray, i, charArray.length - i);
                return stringBuffer.toString();
            }
        }
    }

    public static final String replaceIgnoreCase(String str, String str2, String str3) {
        if (str == null) {
            return null;
        }
        String lowerCase = str.toLowerCase();
        String lowerCase2 = str2.toLowerCase();
        int iIndexOf = lowerCase.indexOf(lowerCase2, 0);
        if (iIndexOf < 0) {
            return str;
        }
        char[] charArray = str.toCharArray();
        char[] charArray2 = str3.toCharArray();
        int length = str2.length();
        StringBuffer stringBuffer = new StringBuffer(charArray.length);
        stringBuffer.append(charArray, 0, iIndexOf);
        stringBuffer.append(charArray2);
        int i = iIndexOf + length;
        while (true) {
            int iIndexOf2 = lowerCase.indexOf(lowerCase2, i);
            if (iIndexOf2 > 0) {
                stringBuffer.append(charArray, i, iIndexOf2 - i);
                stringBuffer.append(charArray2);
                i = iIndexOf2 + length;
            } else {
                stringBuffer.append(charArray, i, charArray.length - i);
                return stringBuffer.toString();
            }
        }
    }

    public static final String escapeHTMLTags(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length());
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '<') {
                stringBuffer.append("&lt;");
            } else if (cCharAt == '>') {
                stringBuffer.append("&gt;");
            } else {
                stringBuffer.append(cCharAt);
            }
        }
        return stringBuffer.toString();
    }

    public static final String randomString(int i) {
        if (i < 1) {
            return null;
        }
        if (randGen == null) {
            synchronized (initLock) {
                if (randGen == null) {
                    randGen = new Random();
                    numbersAndLetters = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
                }
            }
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < cArr.length; i2++) {
            cArr[i2] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(cArr);
    }

    public static String getSubstring(String str, int i) {
        if (str.length() < 11) {
            return str;
        }
        if (i < ((int) Math.ceil(((double) str.length()) / ((double) 10))) - 1) {
            return str.substring(i * 10, (i + 1) * 10);
        }
        return str.substring(i * 10);
    }

    public static String formatHTMLOutput(String str) {
        if (str == null) {
            return null;
        }
        return str.trim().equals("") ? "" : replaceAll(replaceAll(str, " ", "&nbsp;"), IOUtils.LINE_SEPARATOR_UNIX, "<br />");
    }

    public static double myround(double d2, int i) {
        return new BigDecimal(d2).setScale(i, 4).doubleValue();
    }

    public static int parseLongInt(Long l, int i) {
        try {
            return Integer.parseInt(String.valueOf(l));
        } catch (Exception unused) {
            return i;
        }
    }

    public static int parseLongInt(Long l) {
        return parseLongInt(l, 0);
    }

    public static String returnString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static String htmlEncode(String str) {
        String[][] strArr = {new String[]{"<", "&lt;"}, new String[]{">", "&gt;"}, new String[]{"\"", "&quot;"}, new String[]{"\\′", "&acute;"}, new String[]{"&", "&amp;"}};
        String strReplaceAll = str;
        for (int i = 0; i < 4; i++) {
            strReplaceAll = strReplaceAll.replaceAll(strArr[i][0], strArr[i][1]);
        }
        return strReplaceAll;
    }

    public static boolean sql_inj(String str) {
        for (String str2 : "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,".split("|")) {
            if (str.indexOf(str2) >= 0) {
                return true;
            }
        }
        return false;
    }

    public static String getSubStrByCutLastDotPartOff(String str) {
        if (str == null) {
            return "";
        }
        int iLastIndexOf = str.lastIndexOf(".");
        if (iLastIndexOf == -1) {
            return str.trim();
        }
        return str.substring(0, iLastIndexOf).trim();
    }

    public static String[] generateStringArray(int i, int i2) {
        if (i2 < i) {
            throw new IllegalArgumentException("参数有误,应当先传入最小值,然后传入最大值");
        }
        String str = "%0" + String.valueOf(i2).length() + "d";
        String[] strArr = new String[(i2 - i) + 1];
        for (int i3 = i; i3 <= i2; i3++) {
            strArr[i3 - i] = String.format(Locale.getDefault(), str, Integer.valueOf(i3 + i));
        }
        return strArr;
    }

    public static int objectToInt(Object obj) {
        try {
            return Integer.parseInt((String) obj);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public static String getStringNoEmpty(String str) {
        return !TextUtils.isEmpty(str) ? str.replaceAll(" ", "") : str;
    }

    public static String getStringNoBlank(String str) {
        return (str == null || "".equals(str)) ? str : Pattern.compile("\\t|\\r|\\n").matcher(str).replaceAll("");
    }

    public static String toDBC(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == 12288) {
                charArray[i] = ' ';
            } else if (charArray[i] > 65280 && charArray[i] < 65375) {
                charArray[i] = (char) (charArray[i] - 65248);
            }
        }
        return new String(charArray);
    }

    public static String toSBC(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ' ') {
                charArray[i] = 12288;
            } else if (charArray[i] < 127) {
                charArray[i] = (char) (charArray[i] + 65248);
            }
        }
        return new String(charArray);
    }

    public static String getTargetText(int i, String str) {
        byte[] bArrStringToByte;
        if (i <= 0) {
            return str;
        }
        if (TextUtils.isEmpty(str) || (bArrStringToByte = stringToByte(str)) == null) {
            return "";
        }
        if (bArrStringToByte.length <= i) {
            return str;
        }
        String str2 = "";
        int i2 = 0;
        while (i2 < str.length()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            int i3 = i2 + 1;
            sb.append(str.substring(i2, i3));
            String string = sb.toString();
            byte[] bArrStringToByte2 = stringToByte(string);
            if (bArrStringToByte2 == null) {
                return string;
            }
            if (bArrStringToByte2.length > i) {
                return string.substring(0, i2);
            }
            i2 = i3;
            str2 = string;
        }
        return str2;
    }

    public static byte[] stringToByte(String str) {
        return StringToByte(str, CHAR_ENCODE);
    }

    public static byte[] StringToByte(String str, String str2) {
        if (str != null) {
            try {
                if (!str.trim().equals("")) {
                    return str.getBytes(str2);
                }
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return new byte[0];
    }
}