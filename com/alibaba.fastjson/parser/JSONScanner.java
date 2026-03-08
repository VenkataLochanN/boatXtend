package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.IOUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.TimeZone;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes.dex */
public final class JSONScanner extends JSONLexerBase {
    private final int len;
    private final String text;

    static boolean checkDate(char c2, char c3, char c4, char c5, char c6, char c7, int i, int i2) {
        if (c2 >= '1' && c2 <= '3' && c3 >= '0' && c3 <= '9' && c4 >= '0' && c4 <= '9' && c5 >= '0' && c5 <= '9') {
            if (c6 == '0') {
                if (c7 < '1' || c7 > '9') {
                    return false;
                }
            } else if (c6 != '1' || (c7 != '0' && c7 != '1' && c7 != '2')) {
                return false;
            }
            if (i == 48) {
                return i2 >= 49 && i2 <= 57;
            }
            if (i != 49 && i != 50) {
                return i == 51 && (i2 == 48 || i2 == 49);
            }
            if (i2 >= 48 && i2 <= 57) {
                return true;
            }
        }
        return false;
    }

    private boolean checkTime(char c2, char c3, char c4, char c5, char c6, char c7) {
        if (c2 == '0') {
            if (c3 < '0' || c3 > '9') {
                return false;
            }
        } else {
            if (c2 != '1') {
                if (c2 == '2' && c3 >= '0' && c3 <= '4') {
                }
                return false;
            }
            if (c3 < '0' || c3 > '9') {
                return false;
            }
        }
        if (c4 < '0' || c4 > '5') {
            if (c4 != '6' || c5 != '0') {
                return false;
            }
        } else if (c5 < '0' || c5 > '9') {
            return false;
        }
        return (c6 < '0' || c6 > '5') ? c6 == '6' && c7 == '0' : c7 >= '0' && c7 <= '9';
    }

    public JSONScanner(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(String str, int i) {
        super(i);
        this.text = str;
        this.len = this.text.length();
        this.bp = -1;
        next();
        if (this.ch == 65279) {
            next();
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char charAt(int i) {
        return i >= this.len ? JSONLexer.EOI : this.text.charAt(i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final char next() {
        int i = this.bp + 1;
        this.bp = i;
        char cCharAt = i >= this.len ? JSONLexer.EOI : this.text.charAt(i);
        this.ch = cCharAt;
        return cCharAt;
    }

    public JSONScanner(char[] cArr, int i) {
        this(cArr, i, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(char[] cArr, int i, int i2) {
        this(new String(cArr, 0, i), i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    protected final void copyTo(int i, int i2, char[] cArr) {
        this.text.getChars(i, i2 + i, cArr, 0);
    }

    static boolean charArrayCompare(String str, int i, char[] cArr) {
        int length = cArr.length;
        if (length + i > str.length()) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (cArr[i2] != str.charAt(i + i2)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final boolean charArrayCompare(char[] cArr) {
        return charArrayCompare(this.text, this.bp, cArr);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final int indexOf(char c2, int i) {
        return this.text.indexOf(c2, i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String addSymbol(int i, int i2, int i3, SymbolTable symbolTable) {
        return symbolTable.addSymbol(this.text, i, i2, i3);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public byte[] bytesValue() {
        if (this.token == 26) {
            int i = this.np + 1;
            int i2 = this.sp;
            if (i2 % 2 != 0) {
                throw new JSONException("illegal state. " + i2);
            }
            byte[] bArr = new byte[i2 / 2];
            for (int i3 = 0; i3 < bArr.length; i3++) {
                int i4 = (i3 * 2) + i;
                char cCharAt = this.text.charAt(i4);
                char cCharAt2 = this.text.charAt(i4 + 1);
                char c2 = '0';
                int i5 = cCharAt - (cCharAt <= '9' ? '0' : '7');
                if (cCharAt2 > '9') {
                    c2 = '7';
                }
                bArr[i3] = (byte) ((i5 << 4) | (cCharAt2 - c2));
            }
            return bArr;
        }
        return IOUtils.decodeBase64(this.text, this.np + 1, this.sp);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String stringVal() {
        if (!this.hasSpecial) {
            return subString(this.np + 1, this.sp);
        }
        return new String(this.sbuf, 0, this.sp);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String subString(int i, int i2) {
        if (ASMUtils.IS_ANDROID) {
            if (i2 < this.sbuf.length) {
                this.text.getChars(i, i + i2, this.sbuf, 0);
                return new String(this.sbuf, 0, i2);
            }
            char[] cArr = new char[i2];
            this.text.getChars(i, i2 + i, cArr, 0);
            return new String(cArr);
        }
        return this.text.substring(i, i2 + i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char[] sub_chars(int i, int i2) {
        if (ASMUtils.IS_ANDROID && i2 < this.sbuf.length) {
            this.text.getChars(i, i2 + i, this.sbuf, 0);
            return this.sbuf;
        }
        char[] cArr = new char[i2];
        this.text.getChars(i, i2 + i, cArr, 0);
        return cArr;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String numberString() {
        char cCharAt = charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B' || cCharAt == 'F' || cCharAt == 'D') {
            i--;
        }
        return subString(this.np, i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final BigDecimal decimalValue() {
        char cCharAt = charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B' || cCharAt == 'F' || cCharAt == 'D') {
            i--;
        }
        int i2 = this.np;
        if (i < this.sbuf.length) {
            this.text.getChars(i2, i2 + i, this.sbuf, 0);
            return new BigDecimal(this.sbuf, 0, i);
        }
        char[] cArr = new char[i];
        this.text.getChars(i2, i + i2, cArr, 0);
        return new BigDecimal(cArr);
    }

    public boolean scanISO8601DateIfMatch() {
        return scanISO8601DateIfMatch(true);
    }

    public boolean scanISO8601DateIfMatch(boolean z) {
        return scanISO8601DateIfMatch(z, this.len - this.bp);
    }

    /* JADX WARN: Removed duplicated region for block: B:133:0x0201 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0203  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean scanISO8601DateIfMatch(boolean r36, int r37) {
        /*
            Method dump skipped, instruction units count: 1596
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanISO8601DateIfMatch(boolean, int):boolean");
    }

    protected void setTime(char c2, char c3, char c4, char c5, char c6, char c7) {
        this.calendar.set(11, ((c2 - '0') * 10) + (c3 - '0'));
        this.calendar.set(12, ((c4 - '0') * 10) + (c5 - '0'));
        this.calendar.set(13, ((c6 - '0') * 10) + (c7 - '0'));
    }

    protected void setTimeZone(char c2, char c3, char c4) {
        int i = (((c3 - '0') * 10) + (c4 - '0')) * 3600 * 1000;
        if (c2 == '-') {
            i = -i;
        }
        if (this.calendar.getTimeZone().getRawOffset() != i) {
            String[] availableIDs = TimeZone.getAvailableIDs(i);
            if (availableIDs.length > 0) {
                this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs[0]));
            }
        }
    }

    private void setCalendar(char c2, char c3, char c4, char c5, char c6, char c7, char c8, char c9) {
        this.calendar = Calendar.getInstance(this.timeZone, this.locale);
        this.calendar.set(1, ((c2 - '0') * 1000) + ((c3 - '0') * 100) + ((c4 - '0') * 10) + (c5 - '0'));
        this.calendar.set(2, (((c6 - '0') * 10) + (c7 - '0')) - 1);
        this.calendar.set(5, ((c8 - '0') * 10) + (c9 - '0'));
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean isEOF() {
        if (this.bp != this.len) {
            return this.ch == 26 && this.bp + 1 == this.len;
        }
        return true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public int scanFieldInt(char[] cArr) {
        int i;
        char cCharAt;
        int i2;
        char cCharAt2;
        this.matchStat = 0;
        int i3 = this.bp;
        char c2 = this.ch;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return 0;
        }
        int length = this.bp + cArr.length;
        int i4 = length + 1;
        char cCharAt3 = charAt(length);
        boolean z = cCharAt3 == '\"';
        if (z) {
            i = i4 + 1;
            cCharAt = charAt(i4);
        } else {
            i = i4;
            cCharAt = cCharAt3;
        }
        boolean z2 = cCharAt == '-';
        if (z2) {
            int i5 = i + 1;
            char cCharAt4 = charAt(i);
            i = i5;
            cCharAt = cCharAt4;
        }
        if (cCharAt < '0' || cCharAt > '9') {
            this.matchStat = -1;
            return 0;
        }
        int i6 = cCharAt - '0';
        while (true) {
            i2 = i + 1;
            cCharAt2 = charAt(i);
            if (cCharAt2 < '0' || cCharAt2 > '9') {
                break;
            }
            i6 = (i6 * 10) + (cCharAt2 - '0');
            i = i2;
        }
        if (cCharAt2 == '.') {
            this.matchStat = -1;
            return 0;
        }
        if (i6 < 0) {
            this.matchStat = -1;
            return 0;
        }
        if (z) {
            if (cCharAt2 != '\"') {
                this.matchStat = -1;
                return 0;
            }
            int i7 = i2 + 1;
            char cCharAt5 = charAt(i2);
            i2 = i7;
            cCharAt2 = cCharAt5;
        }
        while (cCharAt2 != ',' && cCharAt2 != '}') {
            if (isWhitespace(cCharAt2)) {
                int i8 = i2 + 1;
                char cCharAt6 = charAt(i2);
                i2 = i8;
                cCharAt2 = cCharAt6;
            } else {
                this.matchStat = -1;
                return 0;
            }
        }
        int i9 = i2 - 1;
        this.bp = i9;
        if (cCharAt2 == ',') {
            int i10 = this.bp + 1;
            this.bp = i10;
            this.ch = charAt(i10);
            this.matchStat = 3;
            this.token = 16;
            return z2 ? -i6 : i6;
        }
        if (cCharAt2 == '}') {
            this.bp = i9;
            int i11 = this.bp + 1;
            this.bp = i11;
            char cCharAt7 = charAt(i11);
            while (true) {
                if (cCharAt7 == ',') {
                    this.token = 16;
                    int i12 = this.bp + 1;
                    this.bp = i12;
                    this.ch = charAt(i12);
                    break;
                }
                if (cCharAt7 == ']') {
                    this.token = 15;
                    int i13 = this.bp + 1;
                    this.bp = i13;
                    this.ch = charAt(i13);
                    break;
                }
                if (cCharAt7 == '}') {
                    this.token = 13;
                    int i14 = this.bp + 1;
                    this.bp = i14;
                    this.ch = charAt(i14);
                    break;
                }
                if (cCharAt7 == 26) {
                    this.token = 20;
                    break;
                }
                if (isWhitespace(cCharAt7)) {
                    int i15 = this.bp + 1;
                    this.bp = i15;
                    cCharAt7 = charAt(i15);
                } else {
                    this.bp = i3;
                    this.ch = c2;
                    this.matchStat = -1;
                    return 0;
                }
            }
            this.matchStat = 4;
        }
        return z2 ? -i6 : i6;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public String scanFieldString(char[] cArr) {
        this.matchStat = 0;
        int i = this.bp;
        char c2 = this.ch;
        while (!charArrayCompare(this.text, this.bp, cArr)) {
            if (isWhitespace(this.ch)) {
                next();
            } else {
                this.matchStat = -2;
                return stringDefaultValue();
            }
        }
        int length = this.bp + cArr.length;
        int i2 = length + 1;
        if (charAt(length) != '\"') {
            this.matchStat = -1;
            return stringDefaultValue();
        }
        int iIndexOf = indexOf(Typography.quote, i2);
        if (iIndexOf == -1) {
            throw new JSONException("unclosed str");
        }
        String strSubString = subString(i2, iIndexOf - i2);
        if (strSubString.indexOf(92) != -1) {
            while (true) {
                int i3 = 0;
                for (int i4 = iIndexOf - 1; i4 >= 0 && charAt(i4) == '\\'; i4--) {
                    i3++;
                }
                if (i3 % 2 == 0) {
                    break;
                }
                iIndexOf = indexOf(Typography.quote, iIndexOf + 1);
            }
            int length2 = iIndexOf - ((this.bp + cArr.length) + 1);
            strSubString = readString(sub_chars(this.bp + cArr.length + 1, length2), length2);
        }
        char cCharAt = charAt(iIndexOf + 1);
        while (cCharAt != ',' && cCharAt != '}') {
            if (isWhitespace(cCharAt)) {
                iIndexOf++;
                cCharAt = charAt(iIndexOf + 1);
            } else {
                this.matchStat = -1;
                return stringDefaultValue();
            }
        }
        this.bp = iIndexOf + 1;
        this.ch = cCharAt;
        if (cCharAt == ',') {
            int i5 = this.bp + 1;
            this.bp = i5;
            this.ch = charAt(i5);
            this.matchStat = 3;
            return strSubString;
        }
        int i6 = this.bp + 1;
        this.bp = i6;
        char cCharAt2 = charAt(i6);
        if (cCharAt2 == ',') {
            this.token = 16;
            int i7 = this.bp + 1;
            this.bp = i7;
            this.ch = charAt(i7);
        } else if (cCharAt2 == ']') {
            this.token = 15;
            int i8 = this.bp + 1;
            this.bp = i8;
            this.ch = charAt(i8);
        } else if (cCharAt2 == '}') {
            this.token = 13;
            int i9 = this.bp + 1;
            this.bp = i9;
            this.ch = charAt(i9);
        } else if (cCharAt2 == 26) {
            this.token = 20;
        } else {
            this.bp = i;
            this.ch = c2;
            this.matchStat = -1;
            return stringDefaultValue();
        }
        this.matchStat = 4;
        return strSubString;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public Date scanFieldDate(char[] cArr) {
        char cCharAt;
        long j;
        Date date;
        int i;
        boolean z = false;
        this.matchStat = 0;
        int i2 = this.bp;
        char c2 = this.ch;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = this.bp + cArr.length;
        int i3 = length + 1;
        char cCharAt2 = charAt(length);
        if (cCharAt2 == '\"') {
            int iIndexOf = indexOf(Typography.quote, i3);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            this.bp = i3;
            if (scanISO8601DateIfMatch(false, iIndexOf - i3)) {
                Date time = this.calendar.getTime();
                char cCharAt3 = charAt(iIndexOf + 1);
                this.bp = i2;
                while (cCharAt3 != ',' && cCharAt3 != '}') {
                    if (isWhitespace(cCharAt3)) {
                        iIndexOf++;
                        cCharAt3 = charAt(iIndexOf + 1);
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                }
                this.bp = iIndexOf + 1;
                this.ch = cCharAt3;
                char c3 = cCharAt3;
                date = time;
                cCharAt = c3;
            } else {
                this.bp = i2;
                this.matchStat = -1;
                return null;
            }
        } else {
            char c4 = '9';
            char c5 = '0';
            if (cCharAt2 != '-' && (cCharAt2 < '0' || cCharAt2 > '9')) {
                this.matchStat = -1;
                return null;
            }
            if (cCharAt2 == '-') {
                cCharAt2 = charAt(i3);
                i3++;
                z = true;
            }
            if (cCharAt2 < '0' || cCharAt2 > '9') {
                cCharAt = cCharAt2;
                j = 0;
            } else {
                j = cCharAt2 - '0';
                while (true) {
                    i = i3 + 1;
                    cCharAt = charAt(i3);
                    if (cCharAt < c5 || cCharAt > c4) {
                        break;
                    }
                    j = (j * 10) + ((long) (cCharAt - '0'));
                    i3 = i;
                    c4 = '9';
                    c5 = '0';
                }
                if (cCharAt == ',' || cCharAt == '}') {
                    this.bp = i - 1;
                }
            }
            if (j < 0) {
                this.matchStat = -1;
                return null;
            }
            if (z) {
                j = -j;
            }
            date = new Date(j);
        }
        if (cCharAt == ',') {
            int i4 = this.bp + 1;
            this.bp = i4;
            this.ch = charAt(i4);
            this.matchStat = 3;
            return date;
        }
        int i5 = this.bp + 1;
        this.bp = i5;
        char cCharAt4 = charAt(i5);
        if (cCharAt4 == ',') {
            this.token = 16;
            int i6 = this.bp + 1;
            this.bp = i6;
            this.ch = charAt(i6);
        } else if (cCharAt4 == ']') {
            this.token = 15;
            int i7 = this.bp + 1;
            this.bp = i7;
            this.ch = charAt(i7);
        } else if (cCharAt4 == '}') {
            this.token = 13;
            int i8 = this.bp + 1;
            this.bp = i8;
            this.ch = charAt(i8);
        } else if (cCharAt4 == 26) {
            this.token = 20;
        } else {
            this.bp = i2;
            this.ch = c2;
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 4;
        return date;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public long scanFieldSymbol(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = this.bp + cArr.length;
        int i = length + 1;
        if (charAt(length) != '\"') {
            this.matchStat = -1;
            return 0L;
        }
        long j = -3750763034362895579L;
        while (true) {
            int i2 = i + 1;
            char cCharAt = charAt(i);
            if (cCharAt == '\"') {
                this.bp = i2;
                char cCharAt2 = charAt(this.bp);
                this.ch = cCharAt2;
                while (cCharAt2 != ',') {
                    if (cCharAt2 == '}') {
                        next();
                        skipWhitespace();
                        char current = getCurrent();
                        if (current == ',') {
                            this.token = 16;
                            int i3 = this.bp + 1;
                            this.bp = i3;
                            this.ch = charAt(i3);
                        } else if (current == ']') {
                            this.token = 15;
                            int i4 = this.bp + 1;
                            this.bp = i4;
                            this.ch = charAt(i4);
                        } else if (current == '}') {
                            this.token = 13;
                            int i5 = this.bp + 1;
                            this.bp = i5;
                            this.ch = charAt(i5);
                        } else if (current == 26) {
                            this.token = 20;
                        } else {
                            this.matchStat = -1;
                            return 0L;
                        }
                        this.matchStat = 4;
                        return j;
                    }
                    if (isWhitespace(cCharAt2)) {
                        int i6 = this.bp + 1;
                        this.bp = i6;
                        cCharAt2 = charAt(i6);
                    } else {
                        this.matchStat = -1;
                        return 0L;
                    }
                }
                int i7 = this.bp + 1;
                this.bp = i7;
                this.ch = charAt(i7);
                this.matchStat = 3;
                return j;
            }
            if (i2 > this.len) {
                this.matchStat = -1;
                return 0L;
            }
            j = (j ^ ((long) cCharAt)) * 1099511628211L;
            i = i2;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public Collection<String> newCollectionByType(Class<?> cls) {
        if (cls.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (cls.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        try {
            return (Collection) cls.newInstance();
        } catch (Exception e2) {
            throw new JSONException(e2.getMessage(), e2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x00ca, code lost:
    
        if (r1 != ']') goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d0, code lost:
    
        if (r3.size() != 0) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00d2, code lost:
    
        r1 = r5 + 1;
        r2 = charAt(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00d9, code lost:
    
        r17.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00db, code lost:
    
        return null;
     */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Collection<java.lang.String> scanFieldStringArray(char[] r18, java.lang.Class<?> r19) {
        /*
            Method dump skipped, instruction units count: 366
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanFieldStringArray(char[], java.lang.Class):java.util.Collection");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public long scanFieldLong(char[] cArr) {
        int i;
        char cCharAt;
        boolean z;
        int i2;
        char cCharAt2;
        int i3;
        char cCharAt3;
        this.matchStat = 0;
        int i4 = this.bp;
        char c2 = this.ch;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = this.bp + cArr.length;
        int i5 = length + 1;
        char cCharAt4 = charAt(length);
        boolean z2 = cCharAt4 == '\"';
        if (z2) {
            i = i5 + 1;
            cCharAt = charAt(i5);
        } else {
            i = i5;
            cCharAt = cCharAt4;
        }
        if (cCharAt == '-') {
            int i6 = i + 1;
            char cCharAt5 = charAt(i);
            z = true;
            i = i6;
            cCharAt = cCharAt5;
        } else {
            z = false;
        }
        if (cCharAt >= '0') {
            char c3 = '9';
            if (cCharAt <= '9') {
                long j = cCharAt - '0';
                while (true) {
                    i2 = i + 1;
                    cCharAt2 = charAt(i);
                    if (cCharAt2 < '0' || cCharAt2 > c3) {
                        break;
                    }
                    j = (j * 10) + ((long) (cCharAt2 - '0'));
                    i = i2;
                    c3 = '9';
                }
                if (cCharAt2 == '.') {
                    this.matchStat = -1;
                    return 0L;
                }
                if (!z2) {
                    i3 = i2;
                    cCharAt3 = cCharAt2;
                } else {
                    if (cCharAt2 != '\"') {
                        this.matchStat = -1;
                        return 0L;
                    }
                    i3 = i2 + 1;
                    cCharAt3 = charAt(i2);
                }
                if (cCharAt3 == ',' || cCharAt3 == '}') {
                    this.bp = i3 - 1;
                }
                if (!(j >= 0 || (j == Long.MIN_VALUE && z))) {
                    this.bp = i4;
                    this.ch = c2;
                    this.matchStat = -1;
                    return 0L;
                }
                while (cCharAt3 != ',') {
                    if (cCharAt3 == '}') {
                        int i7 = this.bp + 1;
                        this.bp = i7;
                        char cCharAt6 = charAt(i7);
                        while (true) {
                            if (cCharAt6 == ',') {
                                this.token = 16;
                                int i8 = this.bp + 1;
                                this.bp = i8;
                                this.ch = charAt(i8);
                                break;
                            }
                            if (cCharAt6 == ']') {
                                this.token = 15;
                                int i9 = this.bp + 1;
                                this.bp = i9;
                                this.ch = charAt(i9);
                                break;
                            }
                            if (cCharAt6 == '}') {
                                this.token = 13;
                                int i10 = this.bp + 1;
                                this.bp = i10;
                                this.ch = charAt(i10);
                                break;
                            }
                            if (cCharAt6 == 26) {
                                this.token = 20;
                                break;
                            }
                            if (isWhitespace(cCharAt6)) {
                                int i11 = this.bp + 1;
                                this.bp = i11;
                                cCharAt6 = charAt(i11);
                            } else {
                                this.bp = i4;
                                this.ch = c2;
                                this.matchStat = -1;
                                return 0L;
                            }
                        }
                        this.matchStat = 4;
                        return z ? -j : j;
                    }
                    if (isWhitespace(cCharAt3)) {
                        this.bp = i3;
                        int i12 = i3 + 1;
                        char cCharAt7 = charAt(i3);
                        i3 = i12;
                        cCharAt3 = cCharAt7;
                    } else {
                        this.matchStat = -1;
                        return 0L;
                    }
                }
                int i13 = this.bp + 1;
                this.bp = i13;
                this.ch = charAt(i13);
                this.matchStat = 3;
                this.token = 16;
                return z ? -j : j;
            }
        }
        this.bp = i4;
        this.ch = c2;
        this.matchStat = -1;
        return 0L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:96:0x015d, code lost:
    
        return r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:109:0x00fe A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x010f  */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean scanFieldBoolean(char[] r11) {
        /*
            Method dump skipped, instruction units count: 399
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanFieldBoolean(char[]):boolean");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final int scanInt(char c2) {
        int i;
        char cCharAt;
        char cCharAt2;
        int i2;
        this.matchStat = 0;
        int i3 = this.bp;
        int i4 = i3 + 1;
        char cCharAt3 = charAt(i3);
        while (isWhitespace(cCharAt3)) {
            int i5 = i4 + 1;
            char cCharAt4 = charAt(i4);
            i4 = i5;
            cCharAt3 = cCharAt4;
        }
        boolean z = cCharAt3 == '\"';
        if (z) {
            int i6 = i4 + 1;
            char cCharAt5 = charAt(i4);
            i4 = i6;
            cCharAt3 = cCharAt5;
        }
        boolean z2 = cCharAt3 == '-';
        if (z2) {
            int i7 = i4 + 1;
            char cCharAt6 = charAt(i4);
            i4 = i7;
            cCharAt3 = cCharAt6;
        }
        if (cCharAt3 >= '0' && cCharAt3 <= '9') {
            int i8 = cCharAt3 - '0';
            while (true) {
                i = i4 + 1;
                cCharAt = charAt(i4);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i8 = (i8 * 10) + (cCharAt - '0');
                i4 = i;
            }
            if (cCharAt == '.') {
                this.matchStat = -1;
                return 0;
            }
            if (!z) {
                cCharAt2 = cCharAt;
                i2 = i;
            } else {
                if (cCharAt != '\"') {
                    this.matchStat = -1;
                    return 0;
                }
                i2 = i + 1;
                cCharAt2 = charAt(i);
            }
            if (i8 < 0) {
                this.matchStat = -1;
                return 0;
            }
            while (cCharAt2 != c2) {
                if (isWhitespace(cCharAt2)) {
                    cCharAt2 = charAt(i2);
                    i2++;
                } else {
                    this.matchStat = -1;
                    return z2 ? -i8 : i8;
                }
            }
            this.bp = i2;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return z2 ? -i8 : i8;
        }
        if (cCharAt3 == 'n') {
            int i9 = i4 + 1;
            if (charAt(i4) == 'u') {
                int i10 = i9 + 1;
                if (charAt(i9) == 'l') {
                    int i11 = i10 + 1;
                    if (charAt(i10) == 'l') {
                        this.matchStat = 5;
                        int i12 = i11 + 1;
                        char cCharAt7 = charAt(i11);
                        if (z && cCharAt7 == '\"') {
                            int i13 = i12 + 1;
                            char cCharAt8 = charAt(i12);
                            i12 = i13;
                            cCharAt7 = cCharAt8;
                        }
                        while (cCharAt7 != ',') {
                            if (cCharAt7 == ']') {
                                this.bp = i12;
                                this.ch = charAt(this.bp);
                                this.matchStat = 5;
                                this.token = 15;
                                return 0;
                            }
                            if (isWhitespace(cCharAt7)) {
                                int i14 = i12 + 1;
                                char cCharAt9 = charAt(i12);
                                i12 = i14;
                                cCharAt7 = cCharAt9;
                            } else {
                                this.matchStat = -1;
                                return 0;
                            }
                        }
                        this.bp = i12;
                        this.ch = charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return 0;
                    }
                }
            }
        }
        this.matchStat = -1;
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00c0  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x00c4 -> B:52:0x00b4). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public double scanDouble(char r22) {
        /*
            Method dump skipped, instruction units count: 397
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanDouble(char):double");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public long scanLong(char c2) {
        int i;
        char cCharAt;
        boolean z = false;
        this.matchStat = 0;
        int i2 = this.bp;
        int i3 = i2 + 1;
        char cCharAt2 = charAt(i2);
        boolean z2 = cCharAt2 == '\"';
        if (z2) {
            int i4 = i3 + 1;
            char cCharAt3 = charAt(i3);
            i3 = i4;
            cCharAt2 = cCharAt3;
        }
        boolean z3 = cCharAt2 == '-';
        if (z3) {
            int i5 = i3 + 1;
            char cCharAt4 = charAt(i3);
            i3 = i5;
            cCharAt2 = cCharAt4;
        }
        char c3 = '0';
        if (cCharAt2 >= '0' && cCharAt2 <= '9') {
            long j = cCharAt2 - '0';
            while (true) {
                i = i3 + 1;
                cCharAt = charAt(i3);
                if (cCharAt < c3 || cCharAt > '9') {
                    break;
                }
                j = (j * 10) + ((long) (cCharAt - '0'));
                i3 = i;
                c3 = '0';
            }
            if (cCharAt == '.') {
                this.matchStat = -1;
                return 0L;
            }
            if (z2) {
                if (cCharAt != '\"') {
                    this.matchStat = -1;
                    return 0L;
                }
                cCharAt = charAt(i);
                i++;
            }
            if (j >= 0 || (j == Long.MIN_VALUE && z3)) {
                z = true;
            }
            if (!z) {
                this.matchStat = -1;
                return 0L;
            }
            while (cCharAt != c2) {
                if (isWhitespace(cCharAt)) {
                    cCharAt = charAt(i);
                    i++;
                } else {
                    this.matchStat = -1;
                    return j;
                }
            }
            this.bp = i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return z3 ? -j : j;
        }
        if (cCharAt2 == 'n') {
            int i6 = i3 + 1;
            if (charAt(i3) == 'u') {
                int i7 = i6 + 1;
                if (charAt(i6) == 'l') {
                    int i8 = i7 + 1;
                    if (charAt(i7) == 'l') {
                        this.matchStat = 5;
                        int i9 = i8 + 1;
                        char cCharAt5 = charAt(i8);
                        if (z2 && cCharAt5 == '\"') {
                            int i10 = i9 + 1;
                            char cCharAt6 = charAt(i9);
                            i9 = i10;
                            cCharAt5 = cCharAt6;
                        }
                        while (cCharAt5 != ',') {
                            if (cCharAt5 == ']') {
                                this.bp = i9;
                                this.ch = charAt(this.bp);
                                this.matchStat = 5;
                                this.token = 15;
                                return 0L;
                            }
                            if (isWhitespace(cCharAt5)) {
                                int i11 = i9 + 1;
                                char cCharAt7 = charAt(i9);
                                i9 = i11;
                                cCharAt5 = cCharAt7;
                            } else {
                                this.matchStat = -1;
                                return 0L;
                            }
                        }
                        this.bp = i9;
                        this.ch = charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return 0L;
                    }
                }
            }
        }
        this.matchStat = -1;
        return 0L;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public Date scanDate(char c2) {
        boolean z;
        int i;
        char cCharAt;
        long j;
        char cCharAt2;
        Date date;
        int i2;
        this.matchStat = 0;
        int i3 = this.bp;
        char c3 = this.ch;
        int i4 = this.bp;
        int i5 = i4 + 1;
        char cCharAt3 = charAt(i4);
        if (cCharAt3 == '\"') {
            int iIndexOf = indexOf(Typography.quote, i5);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            this.bp = i5;
            if (scanISO8601DateIfMatch(false, iIndexOf - i5)) {
                date = this.calendar.getTime();
                cCharAt2 = charAt(iIndexOf + 1);
                this.bp = i3;
                while (cCharAt2 != ',' && cCharAt2 != ']') {
                    if (isWhitespace(cCharAt2)) {
                        iIndexOf++;
                        cCharAt2 = charAt(iIndexOf + 1);
                    } else {
                        this.bp = i3;
                        this.ch = c3;
                        this.matchStat = -1;
                        return null;
                    }
                }
                this.bp = iIndexOf + 1;
                this.ch = cCharAt2;
            } else {
                this.bp = i3;
                this.ch = c3;
                this.matchStat = -1;
                return null;
            }
        } else {
            char c4 = '9';
            char c5 = '0';
            if (cCharAt3 != '-' && (cCharAt3 < '0' || cCharAt3 > '9')) {
                if (cCharAt3 == 'n') {
                    int i6 = i5 + 1;
                    if (charAt(i5) == 'u') {
                        int i7 = i6 + 1;
                        if (charAt(i6) == 'l') {
                            int i8 = i7 + 1;
                            if (charAt(i7) == 'l') {
                                cCharAt2 = charAt(i8);
                                this.bp = i8;
                                date = null;
                            }
                        }
                    }
                }
                this.bp = i3;
                this.ch = c3;
                this.matchStat = -1;
                return null;
            }
            if (cCharAt3 == '-') {
                i = i5 + 1;
                cCharAt3 = charAt(i5);
                z = true;
            } else {
                z = false;
                i = i5;
            }
            if (cCharAt3 < '0' || cCharAt3 > '9') {
                cCharAt = cCharAt3;
                j = 0;
            } else {
                j = cCharAt3 - '0';
                while (true) {
                    i2 = i + 1;
                    cCharAt = charAt(i);
                    if (cCharAt < c5 || cCharAt > c4) {
                        break;
                    }
                    j = (j * 10) + ((long) (cCharAt - '0'));
                    i = i2;
                    c4 = '9';
                    c5 = '0';
                }
                if (cCharAt == ',' || cCharAt == ']') {
                    this.bp = i2 - 1;
                }
            }
            if (j < 0) {
                this.bp = i3;
                this.ch = c3;
                this.matchStat = -1;
                return null;
            }
            if (z) {
                j = -j;
            }
            cCharAt2 = cCharAt;
            date = new Date(j);
        }
        if (cCharAt2 == ',') {
            int i9 = this.bp + 1;
            this.bp = i9;
            this.ch = charAt(i9);
            this.matchStat = 3;
            return date;
        }
        int i10 = this.bp + 1;
        this.bp = i10;
        char cCharAt4 = charAt(i10);
        if (cCharAt4 == ',') {
            this.token = 16;
            int i11 = this.bp + 1;
            this.bp = i11;
            this.ch = charAt(i11);
        } else if (cCharAt4 == ']') {
            this.token = 15;
            int i12 = this.bp + 1;
            this.bp = i12;
            this.ch = charAt(i12);
        } else if (cCharAt4 == '}') {
            this.token = 13;
            int i13 = this.bp + 1;
            this.bp = i13;
            this.ch = charAt(i13);
        } else if (cCharAt4 == 26) {
            this.ch = JSONLexer.EOI;
            this.token = 20;
        } else {
            this.bp = i3;
            this.ch = c3;
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 4;
        return date;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    protected final void arrayCopy(int i, char[] cArr, int i2, int i3) {
        this.text.getChars(i, i3 + i, cArr, i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public String info() {
        StringBuilder sb = new StringBuilder();
        sb.append("pos ");
        sb.append(this.bp);
        sb.append(", json : ");
        sb.append(this.text.length() < 65536 ? this.text : this.text.substring(0, 65536));
        return sb.toString();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public String[] scanFieldStringArray(char[] cArr, int i, SymbolTable symbolTable) {
        int i2;
        char cCharAt;
        int i3 = this.bp;
        char c2 = this.ch;
        while (isWhitespace(this.ch)) {
            next();
        }
        if (cArr != null) {
            this.matchStat = 0;
            if (!charArrayCompare(cArr)) {
                this.matchStat = -2;
                return null;
            }
            int length = this.bp + cArr.length;
            int i4 = length + 1;
            char cCharAt2 = this.text.charAt(length);
            while (isWhitespace(cCharAt2)) {
                cCharAt2 = this.text.charAt(i4);
                i4++;
            }
            if (cCharAt2 == ':') {
                i2 = i4 + 1;
                cCharAt = this.text.charAt(i4);
                while (isWhitespace(cCharAt)) {
                    cCharAt = this.text.charAt(i2);
                    i2++;
                }
            } else {
                this.matchStat = -1;
                return null;
            }
        } else {
            i2 = this.bp + 1;
            cCharAt = this.ch;
        }
        if (cCharAt == '[') {
            this.bp = i2;
            this.ch = this.text.charAt(this.bp);
            String[] strArr = i >= 0 ? new String[i] : new String[4];
            int i5 = 0;
            while (true) {
                if (isWhitespace(this.ch)) {
                    next();
                } else {
                    if (this.ch != '\"') {
                        this.bp = i3;
                        this.ch = c2;
                        this.matchStat = -1;
                        return null;
                    }
                    String strScanSymbol = scanSymbol(symbolTable, Typography.quote);
                    if (i5 == strArr.length) {
                        String[] strArr2 = new String[strArr.length + (strArr.length >> 1) + 1];
                        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
                        strArr = strArr2;
                    }
                    int i6 = i5 + 1;
                    strArr[i5] = strScanSymbol;
                    while (isWhitespace(this.ch)) {
                        next();
                    }
                    if (this.ch == ',') {
                        next();
                        i5 = i6;
                    } else {
                        if (strArr.length != i6) {
                            String[] strArr3 = new String[i6];
                            System.arraycopy(strArr, 0, strArr3, 0, i6);
                            strArr = strArr3;
                        }
                        while (isWhitespace(this.ch)) {
                            next();
                        }
                        if (this.ch == ']') {
                            next();
                            return strArr;
                        }
                        this.bp = i3;
                        this.ch = c2;
                        this.matchStat = -1;
                        return null;
                    }
                }
            }
        } else {
            if (cCharAt == 'n' && this.text.startsWith("ull", this.bp + 1)) {
                this.bp += 4;
                this.ch = this.text.charAt(this.bp);
                return null;
            }
            this.matchStat = -1;
            return null;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean matchField2(char[] cArr) {
        while (isWhitespace(this.ch)) {
            next();
        }
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return false;
        }
        int length = this.bp + cArr.length;
        int i = length + 1;
        char cCharAt = this.text.charAt(length);
        while (isWhitespace(cCharAt)) {
            cCharAt = this.text.charAt(i);
            i++;
        }
        if (cCharAt == ':') {
            this.bp = i;
            this.ch = charAt(this.bp);
            return true;
        }
        this.matchStat = -2;
        return false;
    }
}