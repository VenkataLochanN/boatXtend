package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.tencent.bugly.Bugly;
import java.io.Closeable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.text.Typography;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class JSONLexerBase implements JSONLexer, Closeable {
    protected static final int INT_MULTMIN_RADIX_TEN = -214748364;
    protected static final long MULTMIN_RADIX_TEN = -922337203685477580L;
    protected int bp;
    protected char ch;
    protected int eofPos;
    protected int features;
    protected boolean hasSpecial;
    protected int np;
    protected int pos;
    protected char[] sbuf;
    protected int sp;
    protected String stringDefaultValue;
    protected int token;
    private static final ThreadLocal<char[]> SBUF_LOCAL = new ThreadLocal<>();
    protected static final char[] typeFieldName = ("\"" + JSON.DEFAULT_TYPE_KEY + "\":\"").toCharArray();
    protected static final int[] digits = new int[103];
    protected Calendar calendar = null;
    protected TimeZone timeZone = JSON.defaultTimeZone;
    protected Locale locale = JSON.defaultLocale;
    public int matchStat = 0;

    public static boolean isWhitespace(char c2) {
        return c2 <= ' ' && (c2 == ' ' || c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == '\f' || c2 == '\b');
    }

    public abstract String addSymbol(int i, int i2, int i3, SymbolTable symbolTable);

    protected abstract void arrayCopy(int i, char[] cArr, int i2, int i3);

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract byte[] bytesValue();

    protected abstract boolean charArrayCompare(char[] cArr);

    public abstract char charAt(int i);

    protected abstract void copyTo(int i, int i2, char[] cArr);

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract BigDecimal decimalValue();

    public abstract int indexOf(char c2, int i);

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public String info() {
        return "";
    }

    public abstract boolean isEOF();

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract char next();

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract String numberString();

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract String stringVal();

    public abstract String subString(int i, int i2);

    protected abstract char[] sub_chars(int i, int i2);

    protected void lexError(String str, Object... objArr) {
        this.token = 1;
    }

    static {
        for (int i = 48; i <= 57; i++) {
            digits[i] = i - 48;
        }
        for (int i2 = 97; i2 <= 102; i2++) {
            digits[i2] = (i2 - 97) + 10;
        }
        for (int i3 = 65; i3 <= 70; i3++) {
            digits[i3] = (i3 - 65) + 10;
        }
    }

    public JSONLexerBase(int i) {
        this.stringDefaultValue = null;
        this.features = i;
        if ((i & Feature.InitStringFieldAsEmpty.mask) != 0) {
            this.stringDefaultValue = "";
        }
        this.sbuf = SBUF_LOCAL.get();
        if (this.sbuf == null) {
            this.sbuf = new char[512];
        }
    }

    public final int matchStat() {
        return this.matchStat;
    }

    public void setToken(int i) {
        this.token = i;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void nextToken() {
        this.sp = 0;
        while (true) {
            this.pos = this.bp;
            char c2 = this.ch;
            if (c2 == '/') {
                skipComment();
            } else {
                if (c2 == '\"') {
                    scanString();
                    return;
                }
                if (c2 == ',') {
                    next();
                    this.token = 16;
                    return;
                }
                if (c2 >= '0' && c2 <= '9') {
                    scanNumber();
                    return;
                }
                char c3 = this.ch;
                if (c3 == '-') {
                    scanNumber();
                    return;
                }
                switch (c3) {
                    case '\b':
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                        next();
                        break;
                    case '\'':
                        if (!isEnabled(Feature.AllowSingleQuotes)) {
                            throw new JSONException("Feature.AllowSingleQuotes is false");
                        }
                        scanStringSingleQuote();
                        return;
                    case '(':
                        next();
                        this.token = 10;
                        return;
                    case ')':
                        next();
                        this.token = 11;
                        return;
                    case '+':
                        next();
                        scanNumber();
                        return;
                    case '.':
                        next();
                        this.token = 25;
                        return;
                    case ':':
                        next();
                        this.token = 17;
                        return;
                    case ';':
                        next();
                        this.token = 24;
                        return;
                    case 'N':
                    case 'S':
                    case 'T':
                    case 'u':
                        scanIdent();
                        return;
                    case '[':
                        next();
                        this.token = 14;
                        return;
                    case ']':
                        next();
                        this.token = 15;
                        return;
                    case 'f':
                        scanFalse();
                        return;
                    case 'n':
                        scanNullOrNew();
                        return;
                    case 't':
                        scanTrue();
                        return;
                    case 'x':
                        scanHex();
                        return;
                    case '{':
                        next();
                        this.token = 12;
                        return;
                    case '}':
                        next();
                        this.token = 13;
                        return;
                    default:
                        if (isEOF()) {
                            if (this.token == 20) {
                                throw new JSONException("EOF error");
                            }
                            this.token = 20;
                            int i = this.eofPos;
                            this.bp = i;
                            this.pos = i;
                            return;
                        }
                        char c4 = this.ch;
                        if (c4 <= 31 || c4 == 127) {
                            next();
                        } else {
                            lexError("illegal.char", String.valueOf((int) c4));
                            next();
                            return;
                        }
                        break;
                        break;
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0073 A[SYNTHETIC] */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void nextToken(int r10) {
        /*
            Method dump skipped, instruction units count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.nextToken(int):void");
    }

    public final void nextIdent() {
        while (isWhitespace(this.ch)) {
            next();
        }
        char c2 = this.ch;
        if (c2 == '_' || Character.isLetter(c2)) {
            scanIdent();
        } else {
            nextToken();
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void nextTokenWithColon() {
        nextTokenWithChar(':');
    }

    public final void nextTokenWithChar(char c2) {
        this.sp = 0;
        while (true) {
            char c3 = this.ch;
            if (c3 == c2) {
                next();
                nextToken();
                return;
            }
            if (c3 == ' ' || c3 == '\n' || c3 == '\r' || c3 == '\t' || c3 == '\f' || c3 == '\b') {
                next();
            } else {
                throw new JSONException("not match " + c2 + " - " + this.ch + ", info : " + info());
            }
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final int token() {
        return this.token;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String tokenName() {
        return JSONToken.name(this.token);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final int pos() {
        return this.pos;
    }

    public final String stringDefaultValue() {
        return this.stringDefaultValue;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final Number integerValue() throws NumberFormatException {
        long j;
        long j2;
        boolean z = false;
        if (this.np == -1) {
            this.np = 0;
        }
        int i = this.np;
        int i2 = this.sp + i;
        char c2 = ' ';
        char cCharAt = charAt(i2 - 1);
        if (cCharAt == 'B') {
            i2--;
            c2 = 'B';
        } else if (cCharAt == 'L') {
            i2--;
            c2 = 'L';
        } else if (cCharAt == 'S') {
            i2--;
            c2 = 'S';
        }
        if (charAt(this.np) == '-') {
            j = Long.MIN_VALUE;
            i++;
            z = true;
        } else {
            j = -9223372036854775807L;
        }
        long j3 = MULTMIN_RADIX_TEN;
        if (i < i2) {
            j2 = -(charAt(i) - '0');
            i++;
        } else {
            j2 = 0;
        }
        while (i < i2) {
            int i3 = i + 1;
            int iCharAt = charAt(i) - '0';
            if (j2 < j3) {
                return new BigInteger(numberString());
            }
            long j4 = j2 * 10;
            long j5 = iCharAt;
            if (j4 < j + j5) {
                return new BigInteger(numberString());
            }
            j2 = j4 - j5;
            i = i3;
            j3 = MULTMIN_RADIX_TEN;
        }
        if (!z) {
            long j6 = -j2;
            if (j6 > 2147483647L || c2 == 'L') {
                return Long.valueOf(j6);
            }
            if (c2 == 'S') {
                return Short.valueOf((short) j6);
            }
            if (c2 == 'B') {
                return Byte.valueOf((byte) j6);
            }
            return Integer.valueOf((int) j6);
        }
        if (i <= this.np + 1) {
            throw new NumberFormatException(numberString());
        }
        if (j2 < -2147483648L || c2 == 'L') {
            return Long.valueOf(j2);
        }
        if (c2 == 'S') {
            return Short.valueOf((short) j2);
        }
        if (c2 == 'B') {
            return Byte.valueOf((byte) j2);
        }
        return Integer.valueOf((int) j2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void nextTokenWithColon(int i) {
        nextTokenWithChar(':');
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public float floatValue() {
        char cCharAt;
        String strNumberString = numberString();
        float f2 = Float.parseFloat(strNumberString);
        if ((f2 != 0.0f && f2 != Float.POSITIVE_INFINITY) || (cCharAt = strNumberString.charAt(0)) <= '0' || cCharAt > '9') {
            return f2;
        }
        throw new JSONException("float overflow : " + strNumberString);
    }

    public double doubleValue() {
        return Double.parseDouble(numberString());
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public void config(Feature feature, boolean z) {
        this.features = Feature.config(this.features, feature, z);
        if ((this.features & Feature.InitStringFieldAsEmpty.mask) != 0) {
            this.stringDefaultValue = "";
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final boolean isEnabled(Feature feature) {
        return isEnabled(feature.mask);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final boolean isEnabled(int i) {
        return (i & this.features) != 0;
    }

    public final boolean isEnabled(int i, int i2) {
        return ((this.features & i2) == 0 && (i & i2) == 0) ? false : true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final char getCurrent() {
        return this.ch;
    }

    protected void skipComment() {
        char c2;
        next();
        char c3 = this.ch;
        if (c3 == '/') {
            do {
                next();
                c2 = this.ch;
                if (c2 == '\n') {
                    next();
                    return;
                }
            } while (c2 != 26);
            return;
        }
        if (c3 == '*') {
            next();
            while (true) {
                char c4 = this.ch;
                if (c4 == 26) {
                    return;
                }
                if (c4 == '*') {
                    next();
                    if (this.ch == '/') {
                        next();
                        return;
                    }
                } else {
                    next();
                }
            }
        } else {
            throw new JSONException("invalid comment");
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String scanSymbol(SymbolTable symbolTable) {
        skipWhitespace();
        char c2 = this.ch;
        if (c2 == '\"') {
            return scanSymbol(symbolTable, Typography.quote);
        }
        if (c2 == '\'') {
            if (!isEnabled(Feature.AllowSingleQuotes)) {
                throw new JSONException("syntax error");
            }
            return scanSymbol(symbolTable, '\'');
        }
        if (c2 == '}') {
            next();
            this.token = 13;
            return null;
        }
        if (c2 == ',') {
            next();
            this.token = 16;
            return null;
        }
        if (c2 == 26) {
            this.token = 20;
            return null;
        }
        if (!isEnabled(Feature.AllowUnQuotedFieldNames)) {
            throw new JSONException("syntax error");
        }
        return scanSymbolUnQuoted(symbolTable);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String scanSymbol(SymbolTable symbolTable, char c2) {
        String strAddSymbol;
        this.np = this.bp;
        this.sp = 0;
        boolean z = false;
        int i = 0;
        while (true) {
            char next = next();
            if (next == c2) {
                this.token = 4;
                if (!z) {
                    int i2 = this.np;
                    strAddSymbol = addSymbol(i2 == -1 ? 0 : i2 + 1, this.sp, i, symbolTable);
                } else {
                    strAddSymbol = symbolTable.addSymbol(this.sbuf, 0, this.sp, i);
                }
                this.sp = 0;
                next();
                return strAddSymbol;
            }
            if (next == 26) {
                throw new JSONException("unclosed.str");
            }
            if (next == '\\') {
                if (!z) {
                    int i3 = this.sp;
                    char[] cArr = this.sbuf;
                    if (i3 >= cArr.length) {
                        int length = cArr.length * 2;
                        if (i3 <= length) {
                            i3 = length;
                        }
                        char[] cArr2 = new char[i3];
                        char[] cArr3 = this.sbuf;
                        System.arraycopy(cArr3, 0, cArr2, 0, cArr3.length);
                        this.sbuf = cArr2;
                    }
                    arrayCopy(this.np + 1, this.sbuf, 0, this.sp);
                    z = true;
                }
                char next2 = next();
                if (next2 == '\"') {
                    i = (i * 31) + 34;
                    putChar(Typography.quote);
                } else if (next2 != '\'') {
                    if (next2 != 'F') {
                        if (next2 == '\\') {
                            i = (i * 31) + 92;
                            putChar(IOUtils.DIR_SEPARATOR_WINDOWS);
                        } else if (next2 == 'b') {
                            i = (i * 31) + 8;
                            putChar('\b');
                        } else if (next2 != 'f') {
                            if (next2 == 'n') {
                                i = (i * 31) + 10;
                                putChar('\n');
                            } else if (next2 == 'r') {
                                i = (i * 31) + 13;
                                putChar('\r');
                            } else if (next2 != 'x') {
                                switch (next2) {
                                    case '/':
                                        i = (i * 31) + 47;
                                        putChar(IOUtils.DIR_SEPARATOR_UNIX);
                                        break;
                                    case '0':
                                        i = (i * 31) + next2;
                                        putChar((char) 0);
                                        break;
                                    case '1':
                                        i = (i * 31) + next2;
                                        putChar((char) 1);
                                        break;
                                    case '2':
                                        i = (i * 31) + next2;
                                        putChar((char) 2);
                                        break;
                                    case '3':
                                        i = (i * 31) + next2;
                                        putChar((char) 3);
                                        break;
                                    case '4':
                                        i = (i * 31) + next2;
                                        putChar((char) 4);
                                        break;
                                    case '5':
                                        i = (i * 31) + next2;
                                        putChar((char) 5);
                                        break;
                                    case '6':
                                        i = (i * 31) + next2;
                                        putChar((char) 6);
                                        break;
                                    case '7':
                                        i = (i * 31) + next2;
                                        putChar((char) 7);
                                        break;
                                    default:
                                        switch (next2) {
                                            case 't':
                                                i = (i * 31) + 9;
                                                putChar('\t');
                                                break;
                                            case 'u':
                                                int i4 = Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16);
                                                i = (i * 31) + i4;
                                                putChar((char) i4);
                                                break;
                                            case 'v':
                                                i = (i * 31) + 11;
                                                putChar((char) 11);
                                                break;
                                            default:
                                                this.ch = next2;
                                                throw new JSONException("unclosed.str.lit");
                                        }
                                        break;
                                }
                            } else {
                                char next3 = next();
                                this.ch = next3;
                                char next4 = next();
                                this.ch = next4;
                                int[] iArr = digits;
                                char c3 = (char) ((iArr[next3] * 16) + iArr[next4]);
                                i = (i * 31) + c3;
                                putChar(c3);
                            }
                        }
                    }
                    i = (i * 31) + 12;
                    putChar('\f');
                } else {
                    i = (i * 31) + 39;
                    putChar('\'');
                }
            } else {
                i = (i * 31) + next;
                if (!z) {
                    this.sp++;
                } else {
                    int i5 = this.sp;
                    char[] cArr4 = this.sbuf;
                    if (i5 == cArr4.length) {
                        putChar(next);
                    } else {
                        this.sp = i5 + 1;
                        cArr4[i5] = next;
                    }
                }
            }
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void resetStringPosition() {
        this.sp = 0;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String scanSymbolUnQuoted(SymbolTable symbolTable) {
        if (this.token == 1 && this.pos == 0 && this.bp == 1) {
            this.bp = 0;
        }
        boolean[] zArr = com.alibaba.fastjson.util.IOUtils.firstIdentifierFlags;
        int i = this.ch;
        if (!(i >= zArr.length || zArr[i])) {
            throw new JSONException("illegal identifier : " + this.ch + info());
        }
        boolean[] zArr2 = com.alibaba.fastjson.util.IOUtils.identifierFlags;
        this.np = this.bp;
        this.sp = 1;
        while (true) {
            char next = next();
            if (next < zArr2.length && !zArr2[next]) {
                break;
            }
            i = (i * 31) + next;
            this.sp++;
        }
        this.ch = charAt(this.bp);
        this.token = 18;
        if (this.sp == 4 && i == 3392903 && charAt(this.np) == 'n' && charAt(this.np + 1) == 'u' && charAt(this.np + 2) == 'l' && charAt(this.np + 3) == 'l') {
            return null;
        }
        if (symbolTable == null) {
            return subString(this.np, this.sp);
        }
        return addSymbol(this.np, this.sp, i, symbolTable);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void scanString() {
        this.np = this.bp;
        this.hasSpecial = false;
        while (true) {
            char next = next();
            if (next == '\"') {
                this.token = 4;
                this.ch = next();
                return;
            }
            if (next == 26) {
                if (!isEOF()) {
                    putChar(JSONLexer.EOI);
                } else {
                    throw new JSONException("unclosed string : " + next);
                }
            } else if (next == '\\') {
                if (!this.hasSpecial) {
                    this.hasSpecial = true;
                    int i = this.sp;
                    char[] cArr = this.sbuf;
                    if (i >= cArr.length) {
                        int length = cArr.length * 2;
                        if (i <= length) {
                            i = length;
                        }
                        char[] cArr2 = new char[i];
                        char[] cArr3 = this.sbuf;
                        System.arraycopy(cArr3, 0, cArr2, 0, cArr3.length);
                        this.sbuf = cArr2;
                    }
                    copyTo(this.np + 1, this.sp, this.sbuf);
                }
                char next2 = next();
                if (next2 == '\"') {
                    putChar(Typography.quote);
                } else if (next2 != '\'') {
                    if (next2 != 'F') {
                        if (next2 == '\\') {
                            putChar(IOUtils.DIR_SEPARATOR_WINDOWS);
                        } else if (next2 == 'b') {
                            putChar('\b');
                        } else if (next2 != 'f') {
                            if (next2 == 'n') {
                                putChar('\n');
                            } else if (next2 == 'r') {
                                putChar('\r');
                            } else if (next2 != 'x') {
                                switch (next2) {
                                    case '/':
                                        putChar(IOUtils.DIR_SEPARATOR_UNIX);
                                        break;
                                    case '0':
                                        putChar((char) 0);
                                        break;
                                    case '1':
                                        putChar((char) 1);
                                        break;
                                    case '2':
                                        putChar((char) 2);
                                        break;
                                    case '3':
                                        putChar((char) 3);
                                        break;
                                    case '4':
                                        putChar((char) 4);
                                        break;
                                    case '5':
                                        putChar((char) 5);
                                        break;
                                    case '6':
                                        putChar((char) 6);
                                        break;
                                    case '7':
                                        putChar((char) 7);
                                        break;
                                    default:
                                        switch (next2) {
                                            case 't':
                                                putChar('\t');
                                                break;
                                            case 'u':
                                                putChar((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                                                break;
                                            case 'v':
                                                putChar((char) 11);
                                                break;
                                            default:
                                                this.ch = next2;
                                                throw new JSONException("unclosed string : " + next2);
                                        }
                                        break;
                                }
                            } else {
                                char next3 = next();
                                char next4 = next();
                                int[] iArr = digits;
                                putChar((char) ((iArr[next3] * 16) + iArr[next4]));
                            }
                        }
                    }
                    putChar('\f');
                } else {
                    putChar('\'');
                }
            } else if (!this.hasSpecial) {
                this.sp++;
            } else {
                int i2 = this.sp;
                char[] cArr4 = this.sbuf;
                if (i2 == cArr4.length) {
                    putChar(next);
                } else {
                    this.sp = i2 + 1;
                    cArr4[i2] = next;
                }
            }
        }
    }

    public Calendar getCalendar() {
        return this.calendar;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public Locale getLocale() {
        return this.locale;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final int intValue() {
        int i;
        boolean z;
        int i2 = 0;
        if (this.np == -1) {
            this.np = 0;
        }
        int i3 = this.np;
        int i4 = this.sp + i3;
        if (charAt(i3) == '-') {
            i3++;
            i = Integer.MIN_VALUE;
            z = true;
        } else {
            i = -2147483647;
            z = false;
        }
        if (i3 < i4) {
            i2 = -(charAt(i3) - '0');
            i3++;
        }
        while (i3 < i4) {
            int i5 = i3 + 1;
            char cCharAt = charAt(i3);
            if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B') {
                i3 = i5;
                break;
            }
            int i6 = cCharAt - '0';
            if (i2 < -214748364) {
                throw new NumberFormatException(numberString());
            }
            int i7 = i2 * 10;
            if (i7 < i + i6) {
                throw new NumberFormatException(numberString());
            }
            i2 = i7 - i6;
            i3 = i5;
        }
        if (!z) {
            return -i2;
        }
        if (i3 > this.np + 1) {
            return i2;
        }
        throw new NumberFormatException(numberString());
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        char[] cArr = this.sbuf;
        if (cArr.length <= 8192) {
            SBUF_LOCAL.set(cArr);
        }
        this.sbuf = null;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final boolean isRef() {
        return this.sp == 4 && charAt(this.np + 1) == '$' && charAt(this.np + 2) == 'r' && charAt(this.np + 3) == 'e' && charAt(this.np + 4) == 'f';
    }

    public final int scanType(String str) {
        this.matchStat = 0;
        if (!charArrayCompare(typeFieldName)) {
            return -2;
        }
        int length = this.bp + typeFieldName.length;
        int length2 = str.length();
        for (int i = 0; i < length2; i++) {
            if (str.charAt(i) != charAt(length + i)) {
                return -1;
            }
        }
        int i2 = length + length2;
        if (charAt(i2) != '\"') {
            return -1;
        }
        int i3 = i2 + 1;
        this.ch = charAt(i3);
        char c2 = this.ch;
        if (c2 == ',') {
            int i4 = i3 + 1;
            this.ch = charAt(i4);
            this.bp = i4;
            this.token = 16;
            return 3;
        }
        if (c2 == '}') {
            i3++;
            this.ch = charAt(i3);
            char c3 = this.ch;
            if (c3 == ',') {
                this.token = 16;
                i3++;
                this.ch = charAt(i3);
            } else if (c3 == ']') {
                this.token = 15;
                i3++;
                this.ch = charAt(i3);
            } else if (c3 == '}') {
                this.token = 13;
                i3++;
                this.ch = charAt(i3);
            } else {
                if (c3 != 26) {
                    return -1;
                }
                this.token = 20;
            }
            this.matchStat = 4;
        }
        this.bp = i3;
        return this.matchStat;
    }

    public final boolean matchField(char[] cArr) {
        while (!charArrayCompare(cArr)) {
            if (!isWhitespace(this.ch)) {
                return false;
            }
            next();
        }
        this.bp += cArr.length;
        this.ch = charAt(this.bp);
        char c2 = this.ch;
        if (c2 == '{') {
            next();
            this.token = 12;
        } else if (c2 == '[') {
            next();
            this.token = 14;
        } else if (c2 == 'S' && charAt(this.bp + 1) == 'e' && charAt(this.bp + 2) == 't' && charAt(this.bp + 3) == '[') {
            this.bp += 3;
            this.ch = charAt(this.bp);
            this.token = 21;
        } else {
            nextToken();
        }
        return true;
    }

    public String scanFieldString(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return stringDefaultValue();
        }
        int length = cArr.length;
        int i = length + 1;
        if (charAt(this.bp + length) != '\"') {
            this.matchStat = -1;
            return stringDefaultValue();
        }
        int iIndexOf = indexOf(Typography.quote, this.bp + cArr.length + 1);
        if (iIndexOf == -1) {
            throw new JSONException("unclosed str");
        }
        int length2 = this.bp + cArr.length + 1;
        String strSubString = subString(length2, iIndexOf - length2);
        if (strSubString.indexOf(92) != -1) {
            while (true) {
                int i2 = 0;
                for (int i3 = iIndexOf - 1; i3 >= 0 && charAt(i3) == '\\'; i3--) {
                    i2++;
                }
                if (i2 % 2 == 0) {
                    break;
                }
                iIndexOf = indexOf(Typography.quote, iIndexOf + 1);
            }
            int i4 = this.bp;
            int length3 = iIndexOf - ((cArr.length + i4) + 1);
            strSubString = readString(sub_chars(i4 + cArr.length + 1, length3), length3);
        }
        int i5 = this.bp;
        int length4 = i + (iIndexOf - ((cArr.length + i5) + 1)) + 1;
        int i6 = length4 + 1;
        char cCharAt = charAt(i5 + length4);
        if (cCharAt == ',') {
            this.bp += i6;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return strSubString;
        }
        if (cCharAt == '}') {
            int i7 = i6 + 1;
            char cCharAt2 = charAt(this.bp + i6);
            if (cCharAt2 == ',') {
                this.token = 16;
                this.bp += i7;
                this.ch = charAt(this.bp);
            } else if (cCharAt2 == ']') {
                this.token = 15;
                this.bp += i7;
                this.ch = charAt(this.bp);
            } else if (cCharAt2 == '}') {
                this.token = 13;
                this.bp += i7;
                this.ch = charAt(this.bp);
            } else if (cCharAt2 == 26) {
                this.token = 20;
                this.bp += i7 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return stringDefaultValue();
            }
            this.matchStat = 4;
            return strSubString;
        }
        this.matchStat = -1;
        return stringDefaultValue();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public String scanString(char c2) {
        this.matchStat = 0;
        char cCharAt = charAt(this.bp + 0);
        if (cCharAt == 'n') {
            if (charAt(this.bp + 1) == 'u' && charAt(this.bp + 1 + 1) == 'l' && charAt(this.bp + 1 + 2) == 'l') {
                if (charAt(this.bp + 4) == c2) {
                    this.bp += 5;
                    this.ch = charAt(this.bp);
                    this.matchStat = 3;
                    return null;
                }
                this.matchStat = -1;
                return null;
            }
            this.matchStat = -1;
            return null;
        }
        int i = 1;
        while (cCharAt != '\"') {
            if (isWhitespace(cCharAt)) {
                cCharAt = charAt(this.bp + i);
                i++;
            } else {
                this.matchStat = -1;
                return stringDefaultValue();
            }
        }
        int i2 = this.bp + i;
        int iIndexOf = indexOf(Typography.quote, i2);
        if (iIndexOf == -1) {
            throw new JSONException("unclosed str");
        }
        String strSubString = subString(this.bp + i, iIndexOf - i2);
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
            int i5 = iIndexOf - i2;
            strSubString = readString(sub_chars(this.bp + 1, i5), i5);
        }
        int i6 = i + (iIndexOf - i2) + 1;
        int i7 = i6 + 1;
        char cCharAt2 = charAt(this.bp + i6);
        while (cCharAt2 != c2) {
            if (isWhitespace(cCharAt2)) {
                cCharAt2 = charAt(this.bp + i7);
                i7++;
            } else {
                this.matchStat = -1;
                return strSubString;
            }
        }
        this.bp += i7;
        this.ch = charAt(this.bp);
        this.matchStat = 3;
        return strSubString;
    }

    public long scanFieldSymbol(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = cArr.length;
        int i = length + 1;
        if (charAt(this.bp + length) != '\"') {
            this.matchStat = -1;
            return 0L;
        }
        long j = -3750763034362895579L;
        while (true) {
            int i2 = i + 1;
            char cCharAt = charAt(this.bp + i);
            if (cCharAt == '\"') {
                int i3 = i2 + 1;
                char cCharAt2 = charAt(this.bp + i2);
                if (cCharAt2 == ',') {
                    this.bp += i3;
                    this.ch = charAt(this.bp);
                    this.matchStat = 3;
                    return j;
                }
                if (cCharAt2 == '}') {
                    int i4 = i3 + 1;
                    char cCharAt3 = charAt(this.bp + i3);
                    if (cCharAt3 == ',') {
                        this.token = 16;
                        this.bp += i4;
                        this.ch = charAt(this.bp);
                    } else if (cCharAt3 == ']') {
                        this.token = 15;
                        this.bp += i4;
                        this.ch = charAt(this.bp);
                    } else if (cCharAt3 == '}') {
                        this.token = 13;
                        this.bp += i4;
                        this.ch = charAt(this.bp);
                    } else if (cCharAt3 == 26) {
                        this.token = 20;
                        this.bp += i4 - 1;
                        this.ch = JSONLexer.EOI;
                    } else {
                        this.matchStat = -1;
                        return 0L;
                    }
                    this.matchStat = 4;
                    return j;
                }
                this.matchStat = -1;
                return 0L;
            }
            j = (j ^ ((long) cCharAt)) * 1099511628211L;
            if (cCharAt == '\\') {
                this.matchStat = -1;
                return 0L;
            }
            i = i2;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public Enum<?> scanEnum(Class<?> cls, SymbolTable symbolTable, char c2) {
        String strScanSymbolWithSeperator = scanSymbolWithSeperator(symbolTable, c2);
        if (strScanSymbolWithSeperator == null) {
            return null;
        }
        return Enum.valueOf(cls, strScanSymbolWithSeperator);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public String scanSymbolWithSeperator(SymbolTable symbolTable, char c2) {
        this.matchStat = 0;
        char cCharAt = charAt(this.bp + 0);
        if (cCharAt == 'n') {
            if (charAt(this.bp + 1) == 'u' && charAt(this.bp + 1 + 1) == 'l' && charAt(this.bp + 1 + 2) == 'l') {
                if (charAt(this.bp + 4) == c2) {
                    this.bp += 5;
                    this.ch = charAt(this.bp);
                    this.matchStat = 3;
                    return null;
                }
                this.matchStat = -1;
                return null;
            }
            this.matchStat = -1;
            return null;
        }
        if (cCharAt != '\"') {
            this.matchStat = -1;
            return null;
        }
        int i = 0;
        int i2 = 1;
        while (true) {
            int i3 = i2 + 1;
            char cCharAt2 = charAt(this.bp + i2);
            if (cCharAt2 == '\"') {
                int i4 = this.bp;
                int i5 = i4 + 0 + 1;
                String strAddSymbol = addSymbol(i5, ((i4 + i3) - i5) - 1, i, symbolTable);
                int i6 = i3 + 1;
                char cCharAt3 = charAt(this.bp + i3);
                while (cCharAt3 != c2) {
                    if (isWhitespace(cCharAt3)) {
                        cCharAt3 = charAt(this.bp + i6);
                        i6++;
                    } else {
                        this.matchStat = -1;
                        return strAddSymbol;
                    }
                }
                this.bp += i6;
                this.ch = charAt(this.bp);
                this.matchStat = 3;
                return strAddSymbol;
            }
            i = (i * 31) + cCharAt2;
            if (cCharAt2 == '\\') {
                this.matchStat = -1;
                return null;
            }
            i2 = i3;
        }
    }

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

    /* JADX WARN: Code restructure failed: missing block: B:44:0x00e9, code lost:
    
        if (r12 != ']') goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00ef, code lost:
    
        if (r13.size() != 0) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00f1, code lost:
    
        r0 = r1 + 1;
        r12 = charAt(r11.bp + r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0178, code lost:
    
        throw new com.alibaba.fastjson.JSONException("illega str");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Collection<java.lang.String> scanFieldStringArray(char[] r12, java.lang.Class<?> r13) {
        /*
            Method dump skipped, instruction units count: 377
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldStringArray(char[], java.lang.Class):java.util.Collection");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public void scanStringArray(Collection<String> collection, char c2) {
        int i;
        char cCharAt;
        int i2;
        char cCharAt2;
        this.matchStat = 0;
        char cCharAt3 = charAt(this.bp + 0);
        char c3 = 'u';
        char c4 = 'n';
        if (cCharAt3 == 'n' && charAt(this.bp + 1) == 'u' && charAt(this.bp + 1 + 1) == 'l' && charAt(this.bp + 1 + 2) == 'l' && charAt(this.bp + 1 + 3) == c2) {
            this.bp += 5;
            this.ch = charAt(this.bp);
            this.matchStat = 5;
            return;
        }
        if (cCharAt3 != '[') {
            this.matchStat = -1;
            return;
        }
        char cCharAt4 = charAt(this.bp + 1);
        int i3 = 2;
        while (true) {
            if (cCharAt4 == c4 && charAt(this.bp + i3) == c3 && charAt(this.bp + i3 + 1) == 'l' && charAt(this.bp + i3 + 2) == 'l') {
                int i4 = i3 + 3;
                i = i4 + 1;
                cCharAt = charAt(this.bp + i4);
                collection.add(null);
            } else {
                if (cCharAt4 == ']' && collection.size() == 0) {
                    i2 = i3 + 1;
                    cCharAt2 = charAt(this.bp + i3);
                    break;
                }
                if (cCharAt4 != '\"') {
                    this.matchStat = -1;
                    return;
                }
                int i5 = this.bp + i3;
                int iIndexOf = indexOf(Typography.quote, i5);
                if (iIndexOf == -1) {
                    throw new JSONException("unclosed str");
                }
                String strSubString = subString(this.bp + i3, iIndexOf - i5);
                if (strSubString.indexOf(92) != -1) {
                    while (true) {
                        int i6 = 0;
                        for (int i7 = iIndexOf - 1; i7 >= 0 && charAt(i7) == '\\'; i7--) {
                            i6++;
                        }
                        if (i6 % 2 == 0) {
                            break;
                        } else {
                            iIndexOf = indexOf(Typography.quote, iIndexOf + 1);
                        }
                    }
                    int i8 = iIndexOf - i5;
                    strSubString = readString(sub_chars(this.bp + i3, i8), i8);
                }
                int i9 = this.bp;
                int i10 = i3 + (iIndexOf - (i9 + i3)) + 1;
                i = i10 + 1;
                cCharAt = charAt(i9 + i10);
                collection.add(strSubString);
            }
            if (cCharAt == ',') {
                i3 = i + 1;
                cCharAt4 = charAt(this.bp + i);
                c3 = 'u';
                c4 = 'n';
            } else if (cCharAt == ']') {
                i2 = i + 1;
                cCharAt2 = charAt(this.bp + i);
            } else {
                this.matchStat = -1;
                return;
            }
        }
        if (cCharAt2 == c2) {
            this.bp += i2;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return;
        }
        this.matchStat = -1;
    }

    public int scanFieldInt(char[] cArr) {
        int i;
        char cCharAt;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0;
        }
        int length = cArr.length;
        int i2 = length + 1;
        char cCharAt2 = charAt(this.bp + length);
        boolean z = cCharAt2 == '-';
        if (z) {
            cCharAt2 = charAt(this.bp + i2);
            i2++;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            this.matchStat = -1;
            return 0;
        }
        int i3 = cCharAt2 - '0';
        while (true) {
            i = i2 + 1;
            cCharAt = charAt(this.bp + i2);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            i3 = (i3 * 10) + (cCharAt - '0');
            i2 = i;
        }
        if (cCharAt == '.') {
            this.matchStat = -1;
            return 0;
        }
        if ((i3 < 0 || i > cArr.length + 14) && !(i3 == Integer.MIN_VALUE && i == 17 && z)) {
            this.matchStat = -1;
            return 0;
        }
        if (cCharAt == ',') {
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return z ? -i3 : i3;
        }
        if (cCharAt == '}') {
            int i4 = i + 1;
            char cCharAt3 = charAt(this.bp + i);
            if (cCharAt3 == ',') {
                this.token = 16;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == ']') {
                this.token = 15;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == '}') {
                this.token = 13;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == 26) {
                this.token = 20;
                this.bp += i4 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return 0;
            }
            this.matchStat = 4;
            return z ? -i3 : i3;
        }
        this.matchStat = -1;
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x0122, code lost:
    
        r2 = r4;
        r18.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0125, code lost:
    
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int[] scanFieldIntArray(char[] r19) {
        /*
            Method dump skipped, instruction units count: 294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldIntArray(char[]):int[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00ad  */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean scanBoolean(char r10) {
        /*
            r9 = this;
            r0 = 0
            r9.matchStat = r0
            int r1 = r9.bp
            int r1 = r1 + r0
            char r1 = r9.charAt(r1)
            r2 = 3
            r3 = 5
            r4 = 101(0x65, float:1.42E-43)
            r5 = -1
            r6 = 2
            r7 = 1
            r8 = 116(0x74, float:1.63E-43)
            if (r1 != r8) goto L43
            int r1 = r9.bp
            int r1 = r1 + r7
            char r1 = r9.charAt(r1)
            r8 = 114(0x72, float:1.6E-43)
            if (r1 != r8) goto L40
            int r1 = r9.bp
            int r1 = r1 + r7
            int r1 = r1 + r7
            char r1 = r9.charAt(r1)
            r8 = 117(0x75, float:1.64E-43)
            if (r1 != r8) goto L40
            int r1 = r9.bp
            int r1 = r1 + r7
            int r1 = r1 + r6
            char r1 = r9.charAt(r1)
            if (r1 != r4) goto L40
            int r0 = r9.bp
            int r0 = r0 + 4
            char r1 = r9.charAt(r0)
            r6 = r3
            goto L8c
        L40:
            r9.matchStat = r5
            return r0
        L43:
            r8 = 102(0x66, float:1.43E-43)
            if (r1 != r8) goto L81
            int r1 = r9.bp
            int r1 = r1 + r7
            char r1 = r9.charAt(r1)
            r8 = 97
            if (r1 != r8) goto L7e
            int r1 = r9.bp
            int r1 = r1 + r7
            int r1 = r1 + r7
            char r1 = r9.charAt(r1)
            r8 = 108(0x6c, float:1.51E-43)
            if (r1 != r8) goto L7e
            int r1 = r9.bp
            int r1 = r1 + r7
            int r1 = r1 + r6
            char r1 = r9.charAt(r1)
            r6 = 115(0x73, float:1.61E-43)
            if (r1 != r6) goto L7e
            int r1 = r9.bp
            int r1 = r1 + r7
            int r1 = r1 + r2
            char r1 = r9.charAt(r1)
            if (r1 != r4) goto L7e
            int r1 = r9.bp
            r4 = 6
            int r1 = r1 + r3
            char r1 = r9.charAt(r1)
            r6 = r4
            goto L9b
        L7e:
            r9.matchStat = r5
            return r0
        L81:
            r3 = 49
            if (r1 != r3) goto L8e
            int r0 = r9.bp
            int r0 = r0 + r7
            char r1 = r9.charAt(r0)
        L8c:
            r0 = r7
            goto L9b
        L8e:
            r3 = 48
            if (r1 != r3) goto L9a
            int r1 = r9.bp
            int r1 = r1 + r7
            char r1 = r9.charAt(r1)
            goto L9b
        L9a:
            r6 = r7
        L9b:
            if (r1 != r10) goto Lad
            int r10 = r9.bp
            int r10 = r10 + r6
            r9.bp = r10
            int r10 = r9.bp
            char r10 = r9.charAt(r10)
            r9.ch = r10
            r9.matchStat = r2
            return r0
        Lad:
            boolean r1 = isWhitespace(r1)
            if (r1 == 0) goto Lbe
            int r1 = r9.bp
            int r3 = r6 + 1
            int r1 = r1 + r6
            char r1 = r9.charAt(r1)
            r6 = r3
            goto L9b
        Lbe:
            r9.matchStat = r5
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanBoolean(char):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00e5  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x00ce -> B:57:0x00cf). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int scanInt(char r14) {
        /*
            Method dump skipped, instruction units count: 275
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanInt(char):int");
    }

    public boolean scanFieldBoolean(char[] cArr) {
        boolean z;
        int i;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return false;
        }
        int length = cArr.length;
        int i2 = length + 1;
        char cCharAt = charAt(this.bp + length);
        if (cCharAt == 't') {
            int i3 = i2 + 1;
            if (charAt(this.bp + i2) != 'r') {
                this.matchStat = -1;
                return false;
            }
            int i4 = i3 + 1;
            if (charAt(this.bp + i3) != 'u') {
                this.matchStat = -1;
                return false;
            }
            i = i4 + 1;
            if (charAt(this.bp + i4) != 'e') {
                this.matchStat = -1;
                return false;
            }
            z = true;
        } else if (cCharAt == 'f') {
            int i5 = i2 + 1;
            if (charAt(this.bp + i2) != 'a') {
                this.matchStat = -1;
                return false;
            }
            int i6 = i5 + 1;
            if (charAt(this.bp + i5) != 'l') {
                this.matchStat = -1;
                return false;
            }
            int i7 = i6 + 1;
            if (charAt(this.bp + i6) != 's') {
                this.matchStat = -1;
                return false;
            }
            int i8 = i7 + 1;
            if (charAt(this.bp + i7) != 'e') {
                this.matchStat = -1;
                return false;
            }
            z = false;
            i = i8;
        } else {
            this.matchStat = -1;
            return false;
        }
        int i9 = i + 1;
        char cCharAt2 = charAt(this.bp + i);
        if (cCharAt2 == ',') {
            this.bp += i9;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return z;
        }
        if (cCharAt2 == '}') {
            int i10 = i9 + 1;
            char cCharAt3 = charAt(this.bp + i9);
            if (cCharAt3 == ',') {
                this.token = 16;
                this.bp += i10;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == ']') {
                this.token = 15;
                this.bp += i10;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == '}') {
                this.token = 13;
                this.bp += i10;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == 26) {
                this.token = 20;
                this.bp += i10 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return false;
            }
            this.matchStat = 4;
            return z;
        }
        this.matchStat = -1;
        return false;
    }

    public long scanFieldLong(char[] cArr) {
        int i;
        boolean z;
        int i2;
        char cCharAt;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = cArr.length;
        int i3 = length + 1;
        char cCharAt2 = charAt(this.bp + length);
        if (cCharAt2 == '-') {
            i = i3 + 1;
            cCharAt2 = charAt(this.bp + i3);
            z = true;
        } else {
            i = i3;
            z = false;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            this.matchStat = -1;
            return 0L;
        }
        long j = cCharAt2 - '0';
        while (true) {
            i2 = i + 1;
            cCharAt = charAt(this.bp + i);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            j = (j * 10) + ((long) (cCharAt - '0'));
            i = i2;
        }
        if (cCharAt == '.') {
            this.matchStat = -1;
            return 0L;
        }
        if (!(i2 - cArr.length < 21 && (j >= 0 || (j == Long.MIN_VALUE && z)))) {
            this.matchStat = -1;
            return 0L;
        }
        if (cCharAt == ',') {
            this.bp += i2;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return z ? -j : j;
        }
        if (cCharAt == '}') {
            int i4 = i2 + 1;
            char cCharAt3 = charAt(this.bp + i2);
            if (cCharAt3 == ',') {
                this.token = 16;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == ']') {
                this.token = 15;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == '}') {
                this.token = 13;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == 26) {
                this.token = 20;
                this.bp += i4 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return 0L;
            }
            this.matchStat = 4;
            return z ? -j : j;
        }
        this.matchStat = -1;
        return 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0122  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:68:0x010b -> B:69:0x010c). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long scanLong(char r21) {
        /*
            Method dump skipped, instruction units count: 336
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanLong(char):long");
    }

    public final float scanFieldFloat(char[] cArr) {
        int i;
        char cCharAt;
        int i2;
        int length;
        int i3;
        float f2;
        char cCharAt2;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0.0f;
        }
        int length2 = cArr.length;
        int i4 = length2 + 1;
        char cCharAt3 = charAt(this.bp + length2);
        boolean z = cCharAt3 == '\"';
        if (z) {
            cCharAt3 = charAt(this.bp + i4);
            i4++;
        }
        boolean z2 = cCharAt3 == '-';
        if (z2) {
            cCharAt3 = charAt(this.bp + i4);
            i4++;
        }
        if (cCharAt3 < '0' || cCharAt3 > '9') {
            if (cCharAt3 == 'n' && charAt(this.bp + i4) == 'u' && charAt(this.bp + i4 + 1) == 'l' && charAt(this.bp + i4 + 2) == 'l') {
                this.matchStat = 5;
                int i5 = i4 + 3;
                int i6 = i5 + 1;
                char cCharAt4 = charAt(this.bp + i5);
                if (z && cCharAt4 == '\"') {
                    cCharAt4 = charAt(this.bp + i6);
                    i6++;
                }
                while (cCharAt4 != ',') {
                    if (cCharAt4 == '}') {
                        this.bp += i6;
                        this.ch = charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 13;
                        return 0.0f;
                    }
                    if (isWhitespace(cCharAt4)) {
                        cCharAt4 = charAt(this.bp + i6);
                        i6++;
                    } else {
                        this.matchStat = -1;
                        return 0.0f;
                    }
                }
                this.bp += i6;
                this.ch = charAt(this.bp);
                this.matchStat = 5;
                this.token = 16;
                return 0.0f;
            }
            this.matchStat = -1;
            return 0.0f;
        }
        int i7 = cCharAt3 - '0';
        while (true) {
            i = i4 + 1;
            cCharAt = charAt(this.bp + i4);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            i7 = (i7 * 10) + (cCharAt - '0');
            i4 = i;
        }
        if (cCharAt == '.') {
            int i8 = i + 1;
            char cCharAt5 = charAt(this.bp + i);
            if (cCharAt5 < '0' || cCharAt5 > '9') {
                this.matchStat = -1;
                return 0.0f;
            }
            i7 = (i7 * 10) + (cCharAt5 - '0');
            int i9 = 10;
            while (true) {
                i = i8 + 1;
                cCharAt2 = charAt(this.bp + i8);
                if (cCharAt2 < '0' || cCharAt2 > '9') {
                    break;
                }
                i7 = (i7 * 10) + (cCharAt2 - '0');
                i9 *= 10;
                i8 = i;
            }
            i2 = i9;
            cCharAt = cCharAt2;
        } else {
            i2 = 1;
        }
        boolean z3 = cCharAt == 'e' || cCharAt == 'E';
        if (z3) {
            int i10 = i + 1;
            cCharAt = charAt(this.bp + i);
            if (cCharAt == '+' || cCharAt == '-') {
                int i11 = i10 + 1;
                cCharAt = charAt(this.bp + i10);
                i = i11;
            } else {
                i = i10;
            }
            while (cCharAt >= '0' && cCharAt <= '9') {
                int i12 = i + 1;
                cCharAt = charAt(this.bp + i);
                i = i12;
            }
        }
        if (!z) {
            int i13 = this.bp;
            length = cArr.length + i13;
            i3 = ((i13 + i) - length) - 1;
        } else {
            if (cCharAt != '\"') {
                this.matchStat = -1;
                return 0.0f;
            }
            int i14 = i + 1;
            cCharAt = charAt(this.bp + i);
            int i15 = this.bp;
            length = cArr.length + i15 + 1;
            i3 = ((i15 + i14) - length) - 2;
            i = i14;
        }
        if (z3 || i3 >= 20) {
            f2 = Float.parseFloat(subString(length, i3));
        } else {
            f2 = i7 / i2;
            if (z2) {
                f2 = -f2;
            }
        }
        if (cCharAt == ',') {
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return f2;
        }
        if (cCharAt == '}') {
            int i16 = i + 1;
            char cCharAt6 = charAt(this.bp + i);
            if (cCharAt6 == ',') {
                this.token = 16;
                this.bp += i16;
                this.ch = charAt(this.bp);
            } else if (cCharAt6 == ']') {
                this.token = 15;
                this.bp += i16;
                this.ch = charAt(this.bp);
            } else if (cCharAt6 == '}') {
                this.token = 13;
                this.bp += i16;
                this.ch = charAt(this.bp);
            } else if (cCharAt6 == 26) {
                this.bp += i16 - 1;
                this.token = 20;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return 0.0f;
            }
            this.matchStat = 4;
            return f2;
        }
        this.matchStat = -1;
        return 0.0f;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final float scanFloat(char c2) {
        int i;
        int i2;
        int i3;
        char cCharAt;
        int i4;
        int i5;
        float f2;
        int i6;
        this.matchStat = 0;
        char cCharAt2 = charAt(this.bp + 0);
        boolean z = cCharAt2 == '\"';
        if (z) {
            cCharAt2 = charAt(this.bp + 1);
            i = 2;
        } else {
            i = 1;
        }
        boolean z2 = cCharAt2 == '-';
        if (z2) {
            cCharAt2 = charAt(this.bp + i);
            i++;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            if (cCharAt2 == 'n' && charAt(this.bp + i) == 'u' && charAt(this.bp + i + 1) == 'l' && charAt(this.bp + i + 2) == 'l') {
                this.matchStat = 5;
                int i7 = i + 3;
                int i8 = i7 + 1;
                char cCharAt3 = charAt(this.bp + i7);
                if (z && cCharAt3 == '\"') {
                    i2 = i8 + 1;
                    cCharAt3 = charAt(this.bp + i8);
                } else {
                    i2 = i8;
                }
                while (cCharAt3 != ',') {
                    if (cCharAt3 == ']') {
                        this.bp += i2;
                        this.ch = charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 15;
                        return 0.0f;
                    }
                    if (isWhitespace(cCharAt3)) {
                        cCharAt3 = charAt(this.bp + i2);
                        i2++;
                    } else {
                        this.matchStat = -1;
                        return 0.0f;
                    }
                }
                this.bp += i2;
                this.ch = charAt(this.bp);
                this.matchStat = 5;
                this.token = 16;
                return 0.0f;
            }
            this.matchStat = -1;
            return 0.0f;
        }
        long j = cCharAt2 - '0';
        while (true) {
            i3 = i + 1;
            cCharAt = charAt(this.bp + i);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            j = (j * 10) + ((long) (cCharAt - '0'));
            i = i3;
        }
        long j2 = 1;
        if (cCharAt == '.') {
            int i9 = i3 + 1;
            char cCharAt4 = charAt(this.bp + i3);
            if (cCharAt4 < '0' || cCharAt4 > '9') {
                this.matchStat = -1;
                return 0.0f;
            }
            j = (j * 10) + ((long) (cCharAt4 - '0'));
            j2 = 10;
            while (true) {
                i6 = i9 + 1;
                cCharAt = charAt(this.bp + i9);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                j = (j * 10) + ((long) (cCharAt - '0'));
                j2 *= 10;
                i9 = i6;
            }
            i3 = i6;
        }
        long j3 = j2;
        boolean z3 = cCharAt == 'e' || cCharAt == 'E';
        if (z3) {
            int i10 = i3 + 1;
            char cCharAt5 = charAt(this.bp + i3);
            if (cCharAt5 == '+' || cCharAt5 == '-') {
                int i11 = i10 + 1;
                cCharAt = charAt(this.bp + i10);
                i3 = i11;
            } else {
                i3 = i10;
                cCharAt = cCharAt5;
            }
            while (cCharAt >= '0' && cCharAt <= '9') {
                int i12 = i3 + 1;
                cCharAt = charAt(this.bp + i3);
                i3 = i12;
            }
        }
        if (!z) {
            i4 = this.bp;
            i5 = ((i4 + i3) - i4) - 1;
        } else {
            if (cCharAt != '\"') {
                this.matchStat = -1;
                return 0.0f;
            }
            int i13 = i3 + 1;
            cCharAt = charAt(this.bp + i3);
            int i14 = this.bp;
            i4 = i14 + 1;
            i5 = ((i14 + i13) - i4) - 2;
            i3 = i13;
        }
        if (z3 || i5 >= 20) {
            f2 = Float.parseFloat(subString(i4, i5));
        } else {
            f2 = j / j3;
            if (z2) {
                f2 = -f2;
            }
        }
        if (cCharAt == c2) {
            this.bp += i3;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return f2;
        }
        this.matchStat = -1;
        return f2;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public double scanDouble(char c2) {
        int i;
        int i2;
        char cCharAt;
        boolean z;
        long j;
        int i3;
        int i4;
        char cCharAt2;
        int i5;
        double d2;
        int i6;
        char cCharAt3;
        this.matchStat = 0;
        char cCharAt4 = charAt(this.bp + 0);
        boolean z2 = cCharAt4 == '\"';
        if (z2) {
            cCharAt4 = charAt(this.bp + 1);
            i = 2;
        } else {
            i = 1;
        }
        boolean z3 = cCharAt4 == '-';
        if (z3) {
            cCharAt4 = charAt(this.bp + i);
            i++;
        }
        if (cCharAt4 < '0' || cCharAt4 > '9') {
            if (cCharAt4 == 'n' && charAt(this.bp + i) == 'u' && charAt(this.bp + i + 1) == 'l' && charAt(this.bp + i + 2) == 'l') {
                this.matchStat = 5;
                int i7 = i + 3;
                int i8 = i7 + 1;
                char cCharAt5 = charAt(this.bp + i7);
                if (z2 && cCharAt5 == '\"') {
                    cCharAt5 = charAt(this.bp + i8);
                    i8++;
                }
                while (cCharAt5 != ',') {
                    if (cCharAt5 == ']') {
                        this.bp += i8;
                        this.ch = charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 15;
                        return 0.0d;
                    }
                    if (isWhitespace(cCharAt5)) {
                        cCharAt5 = charAt(this.bp + i8);
                        i8++;
                    } else {
                        this.matchStat = -1;
                        return 0.0d;
                    }
                }
                this.bp += i8;
                this.ch = charAt(this.bp);
                this.matchStat = 5;
                this.token = 16;
                return 0.0d;
            }
            this.matchStat = -1;
            return 0.0d;
        }
        long j2 = cCharAt4 - '0';
        while (true) {
            i2 = i + 1;
            cCharAt = charAt(this.bp + i);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            j2 = (j2 * 10) + ((long) (cCharAt - '0'));
            i = i2;
        }
        if (cCharAt == '.') {
            int i9 = i2 + 1;
            char cCharAt6 = charAt(this.bp + i2);
            if (cCharAt6 < '0' || cCharAt6 > '9') {
                this.matchStat = -1;
                return 0.0d;
            }
            j2 = (j2 * 10) + ((long) (cCharAt6 - '0'));
            long j3 = 10;
            while (true) {
                i6 = i9 + 1;
                cCharAt3 = charAt(this.bp + i9);
                if (cCharAt3 < '0' || cCharAt3 > '9') {
                    break;
                }
                j2 = (j2 * 10) + ((long) (cCharAt3 - '0'));
                j3 *= 10;
                i9 = i6;
                z3 = z3;
            }
            z = z3;
            i2 = i6;
            long j4 = j3;
            cCharAt = cCharAt3;
            j = j4;
        } else {
            z = z3;
            j = 1;
        }
        boolean z4 = cCharAt == 'e' || cCharAt == 'E';
        if (z4) {
            int i10 = i2 + 1;
            cCharAt = charAt(this.bp + i2);
            if (cCharAt == '+' || cCharAt == '-') {
                int i11 = i10 + 1;
                cCharAt = charAt(this.bp + i10);
                i2 = i11;
            } else {
                i2 = i10;
            }
            while (cCharAt >= '0' && cCharAt <= '9') {
                int i12 = i2 + 1;
                cCharAt = charAt(this.bp + i2);
                i2 = i12;
            }
        }
        if (!z2) {
            i3 = this.bp;
            i4 = ((i3 + i2) - i3) - 1;
            cCharAt2 = cCharAt;
            i5 = i2;
        } else {
            if (cCharAt != '\"') {
                this.matchStat = -1;
                return 0.0d;
            }
            i5 = i2 + 1;
            cCharAt2 = charAt(this.bp + i2);
            int i13 = this.bp;
            i3 = i13 + 1;
            i4 = ((i13 + i5) - i3) - 2;
        }
        if (z4 || i4 >= 20) {
            d2 = Double.parseDouble(subString(i3, i4));
        } else {
            d2 = j2 / j;
            if (z) {
                d2 = -d2;
            }
        }
        if (cCharAt2 == c2) {
            this.bp += i5;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return d2;
        }
        this.matchStat = -1;
        return d2;
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00ac A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x00ae -> B:50:0x009c). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.math.BigDecimal scanDecimal(char r18) {
        /*
            Method dump skipped, instruction units count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanDecimal(char):java.math.BigDecimal");
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x01b0, code lost:
    
        r2 = r4;
        r18.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01b3, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00a0, code lost:
    
        r18.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a2, code lost:
    
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float[] scanFieldFloatArray(char[] r19) {
        /*
            Method dump skipped, instruction units count: 436
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldFloatArray(char[]):float[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:118:0x021e, code lost:
    
        r19.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0224, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b2, code lost:
    
        r19.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00b6, code lost:
    
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float[][] scanFieldFloatArray2(char[] r20) {
        /*
            Method dump skipped, instruction units count: 554
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldFloatArray2(char[]):float[][]");
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00e7  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:61:0x00eb -> B:56:0x00d7). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final double scanFieldDouble(char[] r24) {
        /*
            Method dump skipped, instruction units count: 588
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldDouble(char[]):double");
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00bc A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x00be -> B:52:0x00ac). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.math.BigDecimal scanFieldDecimal(char[] r19) {
        /*
            Method dump skipped, instruction units count: 506
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldDecimal(char[]):java.math.BigDecimal");
    }

    public BigInteger scanFieldBigInteger(char[] cArr) {
        int i;
        char cCharAt;
        int length;
        int i2;
        BigInteger bigIntegerValueOf;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length2 = cArr.length;
        int i3 = length2 + 1;
        char cCharAt2 = charAt(this.bp + length2);
        boolean z = cCharAt2 == '\"';
        if (z) {
            cCharAt2 = charAt(this.bp + i3);
            i3++;
        }
        boolean z2 = cCharAt2 == '-';
        if (z2) {
            cCharAt2 = charAt(this.bp + i3);
            i3++;
        }
        char c2 = '0';
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            if (cCharAt2 == 'n' && charAt(this.bp + i3) == 'u' && charAt(this.bp + i3 + 1) == 'l' && charAt(this.bp + i3 + 2) == 'l') {
                this.matchStat = 5;
                int i4 = i3 + 3;
                int i5 = i4 + 1;
                char cCharAt3 = charAt(this.bp + i4);
                if (z && cCharAt3 == '\"') {
                    cCharAt3 = charAt(this.bp + i5);
                    i5++;
                }
                while (cCharAt3 != ',') {
                    if (cCharAt3 == '}') {
                        this.bp += i5;
                        this.ch = charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 13;
                        return null;
                    }
                    if (isWhitespace(cCharAt3)) {
                        cCharAt3 = charAt(this.bp + i5);
                        i5++;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                }
                this.bp += i5;
                this.ch = charAt(this.bp);
                this.matchStat = 5;
                this.token = 16;
                return null;
            }
            this.matchStat = -1;
            return null;
        }
        long j = cCharAt2 - '0';
        while (true) {
            i = i3 + 1;
            cCharAt = charAt(this.bp + i3);
            if (cCharAt < c2 || cCharAt > '9') {
                break;
            }
            j = (j * 10) + ((long) (cCharAt - '0'));
            i3 = i;
            c2 = '0';
        }
        if (!z) {
            int i6 = this.bp;
            length = cArr.length + i6;
            i2 = ((i6 + i) - length) - 1;
        } else {
            if (cCharAt != '\"') {
                this.matchStat = -1;
                return null;
            }
            int i7 = i + 1;
            cCharAt = charAt(this.bp + i);
            int i8 = this.bp;
            length = cArr.length + i8 + 1;
            i2 = ((i8 + i7) - length) - 2;
            i = i7;
        }
        if (i2 < 20 || (z2 && i2 < 21)) {
            if (z2) {
                j = -j;
            }
            bigIntegerValueOf = BigInteger.valueOf(j);
        } else {
            bigIntegerValueOf = new BigInteger(subString(length, i2));
        }
        if (cCharAt == ',') {
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return bigIntegerValueOf;
        }
        if (cCharAt == '}') {
            int i9 = i + 1;
            char cCharAt4 = charAt(this.bp + i);
            if (cCharAt4 == ',') {
                this.token = 16;
                this.bp += i9;
                this.ch = charAt(this.bp);
            } else if (cCharAt4 == ']') {
                this.token = 15;
                this.bp += i9;
                this.ch = charAt(this.bp);
            } else if (cCharAt4 == '}') {
                this.token = 13;
                this.bp += i9;
                this.ch = charAt(this.bp);
            } else if (cCharAt4 == 26) {
                this.token = 20;
                this.bp += i9 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return bigIntegerValueOf;
        }
        this.matchStat = -1;
        return null;
    }

    public Date scanFieldDate(char[] cArr) {
        int i;
        long j;
        Date date;
        int i2;
        char cCharAt;
        boolean z = false;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr.length;
        int i3 = length + 1;
        char cCharAt2 = charAt(this.bp + length);
        if (cCharAt2 == '\"') {
            int iIndexOf = indexOf(Typography.quote, this.bp + cArr.length + 1);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            int length2 = this.bp + cArr.length + 1;
            String strSubString = subString(length2, iIndexOf - length2);
            if (strSubString.indexOf(92) != -1) {
                while (true) {
                    int i4 = 0;
                    for (int i5 = iIndexOf - 1; i5 >= 0 && charAt(i5) == '\\'; i5--) {
                        i4++;
                    }
                    if (i4 % 2 == 0) {
                        break;
                    }
                    iIndexOf = indexOf(Typography.quote, iIndexOf + 1);
                }
                int i6 = this.bp;
                int length3 = iIndexOf - ((cArr.length + i6) + 1);
                strSubString = readString(sub_chars(i6 + cArr.length + 1, length3), length3);
            }
            int i7 = this.bp;
            int length4 = i3 + (iIndexOf - ((cArr.length + i7) + 1)) + 1;
            i = length4 + 1;
            cCharAt2 = charAt(i7 + length4);
            JSONScanner jSONScanner = new JSONScanner(strSubString);
            try {
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    date = jSONScanner.getCalendar().getTime();
                } else {
                    this.matchStat = -1;
                    return null;
                }
            } finally {
                jSONScanner.close();
            }
        } else {
            if (cCharAt2 != '-' && (cCharAt2 < '0' || cCharAt2 > '9')) {
                this.matchStat = -1;
                return null;
            }
            if (cCharAt2 == '-') {
                cCharAt2 = charAt(this.bp + i3);
                i3++;
                z = true;
            }
            if (cCharAt2 < '0' || cCharAt2 > '9') {
                i = i3;
                j = 0;
            } else {
                j = cCharAt2 - '0';
                while (true) {
                    i2 = i3 + 1;
                    cCharAt = charAt(this.bp + i3);
                    if (cCharAt < '0' || cCharAt > '9') {
                        break;
                    }
                    j = (j * 10) + ((long) (cCharAt - '0'));
                    i3 = i2;
                }
                cCharAt2 = cCharAt;
                i = i2;
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
        if (cCharAt2 == ',') {
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return date;
        }
        if (cCharAt2 == '}') {
            int i8 = i + 1;
            char cCharAt3 = charAt(this.bp + i);
            if (cCharAt3 == ',') {
                this.token = 16;
                this.bp += i8;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == ']') {
                this.token = 15;
                this.bp += i8;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == '}') {
                this.token = 13;
                this.bp += i8;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == 26) {
                this.token = 20;
                this.bp += i8 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return date;
        }
        this.matchStat = -1;
        return null;
    }

    public Date scanDate(char c2) {
        long j;
        int i;
        Date date;
        boolean z = false;
        this.matchStat = 0;
        char cCharAt = charAt(this.bp + 0);
        if (cCharAt == '\"') {
            int iIndexOf = indexOf(Typography.quote, this.bp + 1);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            int i2 = this.bp + 1;
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
                int i5 = this.bp;
                int i6 = iIndexOf - (i5 + 1);
                strSubString = readString(sub_chars(i5 + 1, i6), i6);
            }
            int i7 = this.bp;
            int i8 = (iIndexOf - (i7 + 1)) + 1 + 1;
            int i9 = i8 + 1;
            cCharAt = charAt(i7 + i8);
            JSONScanner jSONScanner = new JSONScanner(strSubString);
            try {
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    date = jSONScanner.getCalendar().getTime();
                    jSONScanner.close();
                    i = i9;
                } else {
                    this.matchStat = -1;
                    return null;
                }
            } finally {
                jSONScanner.close();
            }
        } else {
            char c3 = '9';
            int i10 = 2;
            if (cCharAt == '-' || (cCharAt >= '0' && cCharAt <= '9')) {
                if (cCharAt == '-') {
                    cCharAt = charAt(this.bp + 1);
                    z = true;
                } else {
                    i10 = 1;
                }
                if (cCharAt < '0' || cCharAt > '9') {
                    j = 0;
                    i = i10;
                } else {
                    j = cCharAt - '0';
                    while (true) {
                        i = i10 + 1;
                        cCharAt = charAt(this.bp + i10);
                        if (cCharAt < '0' || cCharAt > c3) {
                            break;
                        }
                        j = (j * 10) + ((long) (cCharAt - '0'));
                        i10 = i;
                        c3 = '9';
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
            } else if (cCharAt == 'n' && charAt(this.bp + 1) == 'u' && charAt(this.bp + 1 + 1) == 'l' && charAt(this.bp + 1 + 2) == 'l') {
                this.matchStat = 5;
                cCharAt = charAt(this.bp + 4);
                i = 5;
                date = null;
            } else {
                this.matchStat = -1;
                return null;
            }
        }
        if (cCharAt == ',') {
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return date;
        }
        if (cCharAt == ']') {
            int i11 = i + 1;
            char cCharAt2 = charAt(this.bp + i);
            if (cCharAt2 == ',') {
                this.token = 16;
                this.bp += i11;
                this.ch = charAt(this.bp);
            } else if (cCharAt2 == ']') {
                this.token = 15;
                this.bp += i11;
                this.ch = charAt(this.bp);
            } else if (cCharAt2 == '}') {
                this.token = 13;
                this.bp += i11;
                this.ch = charAt(this.bp);
            } else if (cCharAt2 == 26) {
                this.token = 20;
                this.bp += i11 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return date;
        }
        this.matchStat = -1;
        return null;
    }

    public UUID scanFieldUUID(char[] cArr) {
        int i;
        char cCharAt;
        UUID uuid;
        int i2;
        char cCharAt2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr.length;
        int i16 = length + 1;
        char cCharAt3 = charAt(this.bp + length);
        char c2 = 4;
        if (cCharAt3 == '\"') {
            int iIndexOf = indexOf(Typography.quote, this.bp + cArr.length + 1);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            int length2 = this.bp + cArr.length + 1;
            int i17 = iIndexOf - length2;
            char c3 = 'F';
            char c4 = 'f';
            char c5 = 'A';
            char c6 = 'a';
            char c7 = '0';
            if (i17 == 36) {
                int i18 = 0;
                long j = 0;
                while (i18 < 8) {
                    char cCharAt4 = charAt(length2 + i18);
                    if (cCharAt4 < '0' || cCharAt4 > '9') {
                        if (cCharAt4 >= 'a' && cCharAt4 <= 'f') {
                            i14 = cCharAt4 - 'a';
                        } else {
                            if (cCharAt4 < c5 || cCharAt4 > c3) {
                                this.matchStat = -2;
                                return null;
                            }
                            i14 = cCharAt4 - 'A';
                        }
                        i15 = i14 + 10;
                    } else {
                        i15 = cCharAt4 - '0';
                    }
                    j = (j << 4) | ((long) i15);
                    i18++;
                    c5 = 'A';
                    c3 = 'F';
                }
                int i19 = 9;
                while (i19 < 13) {
                    char cCharAt5 = charAt(length2 + i19);
                    if (cCharAt5 < '0' || cCharAt5 > '9') {
                        if (cCharAt5 >= 'a' && cCharAt5 <= c4) {
                            i12 = cCharAt5 - 'a';
                        } else {
                            if (cCharAt5 < 'A' || cCharAt5 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i12 = cCharAt5 - 'A';
                        }
                        i13 = i12 + 10;
                    } else {
                        i13 = cCharAt5 - '0';
                    }
                    j = (j << 4) | ((long) i13);
                    i19++;
                    iIndexOf = iIndexOf;
                    c4 = 'f';
                }
                int i20 = iIndexOf;
                long j2 = j;
                for (int i21 = 14; i21 < 18; i21++) {
                    char cCharAt6 = charAt(length2 + i21);
                    if (cCharAt6 < '0' || cCharAt6 > '9') {
                        if (cCharAt6 >= 'a' && cCharAt6 <= 'f') {
                            i10 = cCharAt6 - 'a';
                        } else {
                            if (cCharAt6 < 'A' || cCharAt6 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i10 = cCharAt6 - 'A';
                        }
                        i11 = i10 + 10;
                    } else {
                        i11 = cCharAt6 - '0';
                    }
                    j2 = (j2 << 4) | ((long) i11);
                }
                int i22 = 19;
                long j3 = 0;
                while (i22 < 23) {
                    char cCharAt7 = charAt(length2 + i22);
                    if (cCharAt7 < '0' || cCharAt7 > '9') {
                        if (cCharAt7 >= 'a' && cCharAt7 <= 'f') {
                            i8 = cCharAt7 - 'a';
                        } else {
                            if (cCharAt7 < 'A' || cCharAt7 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i8 = cCharAt7 - 'A';
                        }
                        i9 = i8 + 10;
                    } else {
                        i9 = cCharAt7 - '0';
                    }
                    j3 = (j3 << c2) | ((long) i9);
                    i22++;
                    j2 = j2;
                    c2 = 4;
                }
                long j4 = j2;
                long j5 = j3;
                for (int i23 = 24; i23 < 36; i23++) {
                    char cCharAt8 = charAt(length2 + i23);
                    if (cCharAt8 < '0' || cCharAt8 > '9') {
                        if (cCharAt8 >= 'a' && cCharAt8 <= 'f') {
                            i6 = cCharAt8 - 'a';
                        } else {
                            if (cCharAt8 < 'A' || cCharAt8 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i6 = cCharAt8 - 'A';
                        }
                        i7 = i6 + 10;
                    } else {
                        i7 = cCharAt8 - '0';
                    }
                    j5 = (j5 << 4) | ((long) i7);
                }
                uuid = new UUID(j4, j5);
                int i24 = this.bp;
                int length3 = i16 + (i20 - ((cArr.length + i24) + 1)) + 1;
                i2 = length3 + 1;
                cCharAt2 = charAt(i24 + length3);
            } else if (i17 == 32) {
                int i25 = 0;
                long j6 = 0;
                for (int i26 = 16; i25 < i26; i26 = 16) {
                    char cCharAt9 = charAt(length2 + i25);
                    if (cCharAt9 < '0' || cCharAt9 > '9') {
                        if (cCharAt9 >= 'a' && cCharAt9 <= 'f') {
                            i4 = cCharAt9 - 'a';
                        } else {
                            if (cCharAt9 < 'A' || cCharAt9 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i4 = cCharAt9 - 'A';
                        }
                        i5 = i4 + 10;
                    } else {
                        i5 = cCharAt9 - '0';
                    }
                    j6 = (j6 << 4) | ((long) i5);
                    i25++;
                }
                int i27 = 16;
                long j7 = 0;
                while (i27 < 32) {
                    char cCharAt10 = charAt(length2 + i27);
                    if (cCharAt10 >= c7 && cCharAt10 <= '9') {
                        i3 = cCharAt10 - '0';
                    } else if (cCharAt10 >= c6 && cCharAt10 <= 'f') {
                        i3 = (cCharAt10 - 'a') + 10;
                    } else {
                        if (cCharAt10 < 'A' || cCharAt10 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i3 = (cCharAt10 - 'A') + 10;
                    }
                    j7 = (j7 << 4) | ((long) i3);
                    i27++;
                    c7 = '0';
                    c6 = 'a';
                }
                uuid = new UUID(j6, j7);
                int i28 = this.bp;
                int length4 = i16 + (iIndexOf - ((cArr.length + i28) + 1)) + 1;
                i2 = length4 + 1;
                cCharAt2 = charAt(i28 + length4);
            } else {
                this.matchStat = -1;
                return null;
            }
            char c8 = cCharAt2;
            i = i2;
            cCharAt = c8;
        } else {
            if (cCharAt3 == 'n') {
                int i29 = i16 + 1;
                if (charAt(this.bp + i16) == 'u') {
                    int i30 = i29 + 1;
                    if (charAt(this.bp + i29) == 'l') {
                        int i31 = i30 + 1;
                        if (charAt(this.bp + i30) == 'l') {
                            i = i31 + 1;
                            cCharAt = charAt(this.bp + i31);
                            uuid = null;
                        }
                    }
                }
            }
            this.matchStat = -1;
            return null;
        }
        if (cCharAt == ',') {
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return uuid;
        }
        if (cCharAt == '}') {
            int i32 = i + 1;
            char cCharAt11 = charAt(this.bp + i);
            if (cCharAt11 == ',') {
                this.token = 16;
                this.bp += i32;
                this.ch = charAt(this.bp);
            } else if (cCharAt11 == ']') {
                this.token = 15;
                this.bp += i32;
                this.ch = charAt(this.bp);
            } else if (cCharAt11 == '}') {
                this.token = 13;
                this.bp += i32;
                this.ch = charAt(this.bp);
            } else if (cCharAt11 == 26) {
                this.token = 20;
                this.bp += i32 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return uuid;
        }
        this.matchStat = -1;
        return null;
    }

    public UUID scanUUID(char c2) {
        int i;
        char cCharAt;
        UUID uuid;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        this.matchStat = 0;
        char cCharAt2 = charAt(this.bp + 0);
        if (cCharAt2 == '\"') {
            int iIndexOf = indexOf(Typography.quote, this.bp + 1);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            int i15 = this.bp + 1;
            int i16 = iIndexOf - i15;
            char c3 = '9';
            char c4 = 'A';
            char c5 = 'a';
            char c6 = '0';
            if (i16 == 36) {
                int i17 = 0;
                long j = 0;
                while (i17 < 8) {
                    char cCharAt3 = charAt(i15 + i17);
                    if (cCharAt3 < '0' || cCharAt3 > '9') {
                        if (cCharAt3 >= c5 && cCharAt3 <= 'f') {
                            i13 = cCharAt3 - 'a';
                        } else {
                            if (cCharAt3 < c4 || cCharAt3 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i13 = cCharAt3 - 'A';
                        }
                        i14 = i13 + 10;
                    } else {
                        i14 = cCharAt3 - '0';
                    }
                    j = (j << 4) | ((long) i14);
                    i17++;
                    c4 = 'A';
                    c5 = 'a';
                }
                int i18 = 9;
                while (i18 < 13) {
                    char cCharAt4 = charAt(i15 + i18);
                    if (cCharAt4 < '0' || cCharAt4 > c3) {
                        if (cCharAt4 >= 'a' && cCharAt4 <= 'f') {
                            i11 = cCharAt4 - 'a';
                        } else {
                            if (cCharAt4 < 'A' || cCharAt4 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i11 = cCharAt4 - 'A';
                        }
                        i12 = i11 + 10;
                    } else {
                        i12 = cCharAt4 - '0';
                    }
                    j = (j << 4) | ((long) i12);
                    i18++;
                    iIndexOf = iIndexOf;
                    c3 = '9';
                }
                int i19 = iIndexOf;
                long j2 = j;
                for (int i20 = 14; i20 < 18; i20++) {
                    char cCharAt5 = charAt(i15 + i20);
                    if (cCharAt5 < '0' || cCharAt5 > '9') {
                        if (cCharAt5 >= 'a' && cCharAt5 <= 'f') {
                            i9 = cCharAt5 - 'a';
                        } else {
                            if (cCharAt5 < 'A' || cCharAt5 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i9 = cCharAt5 - 'A';
                        }
                        i10 = i9 + 10;
                    } else {
                        i10 = cCharAt5 - '0';
                    }
                    j2 = (j2 << 4) | ((long) i10);
                }
                int i21 = 19;
                long j3 = 0;
                while (i21 < 23) {
                    char cCharAt6 = charAt(i15 + i21);
                    if (cCharAt6 < c6 || cCharAt6 > '9') {
                        if (cCharAt6 >= 'a' && cCharAt6 <= 'f') {
                            i7 = cCharAt6 - 'a';
                        } else {
                            if (cCharAt6 < 'A' || cCharAt6 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i7 = cCharAt6 - 'A';
                        }
                        i8 = i7 + 10;
                    } else {
                        i8 = cCharAt6 - '0';
                    }
                    j3 = (j3 << 4) | ((long) i8);
                    i21++;
                    c6 = '0';
                }
                long j4 = j3;
                for (int i22 = 24; i22 < 36; i22++) {
                    char cCharAt7 = charAt(i15 + i22);
                    if (cCharAt7 < '0' || cCharAt7 > '9') {
                        if (cCharAt7 >= 'a' && cCharAt7 <= 'f') {
                            i5 = cCharAt7 - 'a';
                        } else {
                            if (cCharAt7 < 'A' || cCharAt7 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i5 = cCharAt7 - 'A';
                        }
                        i6 = i5 + 10;
                    } else {
                        i6 = cCharAt7 - '0';
                    }
                    j4 = (j4 << 4) | ((long) i6);
                }
                uuid = new UUID(j2, j4);
                int i23 = this.bp;
                int i24 = (i19 - (i23 + 1)) + 1 + 1;
                i = i24 + 1;
                cCharAt = charAt(i23 + i24);
            } else if (i16 == 32) {
                long j5 = 0;
                for (int i25 = 0; i25 < 16; i25++) {
                    char cCharAt8 = charAt(i15 + i25);
                    if (cCharAt8 < '0' || cCharAt8 > '9') {
                        if (cCharAt8 >= 'a' && cCharAt8 <= 'f') {
                            i3 = cCharAt8 - 'a';
                        } else {
                            if (cCharAt8 < 'A' || cCharAt8 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i3 = cCharAt8 - 'A';
                        }
                        i4 = i3 + 10;
                    } else {
                        i4 = cCharAt8 - '0';
                    }
                    j5 = (j5 << 4) | ((long) i4);
                }
                long j6 = 0;
                for (int i26 = 16; i26 < 32; i26++) {
                    char cCharAt9 = charAt(i15 + i26);
                    if (cCharAt9 >= '0' && cCharAt9 <= '9') {
                        i2 = cCharAt9 - '0';
                    } else if (cCharAt9 >= 'a' && cCharAt9 <= 'f') {
                        i2 = (cCharAt9 - 'a') + 10;
                    } else {
                        if (cCharAt9 < 'A' || cCharAt9 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i2 = (cCharAt9 - 'A') + 10;
                        j6 = (j6 << 4) | ((long) i2);
                    }
                    j6 = (j6 << 4) | ((long) i2);
                }
                uuid = new UUID(j5, j6);
                int i27 = this.bp;
                int i28 = (iIndexOf - (i27 + 1)) + 1 + 1;
                i = i28 + 1;
                cCharAt = charAt(i27 + i28);
            } else {
                this.matchStat = -1;
                return null;
            }
        } else if (cCharAt2 == 'n' && charAt(this.bp + 1) == 'u' && charAt(this.bp + 2) == 'l' && charAt(this.bp + 3) == 'l') {
            i = 5;
            cCharAt = charAt(this.bp + 4);
            uuid = null;
        } else {
            this.matchStat = -1;
            return null;
        }
        if (cCharAt == ',') {
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return uuid;
        }
        if (cCharAt == ']') {
            int i29 = i + 1;
            char cCharAt10 = charAt(this.bp + i);
            if (cCharAt10 == ',') {
                this.token = 16;
                this.bp += i29;
                this.ch = charAt(this.bp);
            } else if (cCharAt10 == ']') {
                this.token = 15;
                this.bp += i29;
                this.ch = charAt(this.bp);
            } else if (cCharAt10 == '}') {
                this.token = 13;
                this.bp += i29;
                this.ch = charAt(this.bp);
            } else if (cCharAt10 == 26) {
                this.token = 20;
                this.bp += i29 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return uuid;
        }
        this.matchStat = -1;
        return null;
    }

    public final void scanTrue() {
        if (this.ch != 't') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'r') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'u') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'e') {
            throw new JSONException("error parse true");
        }
        next();
        char c2 = this.ch;
        if (c2 == ' ' || c2 == ',' || c2 == '}' || c2 == ']' || c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == 26 || c2 == '\f' || c2 == '\b' || c2 == ':' || c2 == '/') {
            this.token = 6;
            return;
        }
        throw new JSONException("scan true error");
    }

    public final void scanNullOrNew() {
        if (this.ch != 'n') {
            throw new JSONException("error parse null or new");
        }
        next();
        char c2 = this.ch;
        if (c2 != 'u') {
            if (c2 != 'e') {
                throw new JSONException("error parse new");
            }
            next();
            if (this.ch != 'w') {
                throw new JSONException("error parse new");
            }
            next();
            char c3 = this.ch;
            if (c3 == ' ' || c3 == ',' || c3 == '}' || c3 == ']' || c3 == '\n' || c3 == '\r' || c3 == '\t' || c3 == 26 || c3 == '\f' || c3 == '\b') {
                this.token = 9;
                return;
            }
            throw new JSONException("scan new error");
        }
        next();
        if (this.ch != 'l') {
            throw new JSONException("error parse null");
        }
        next();
        if (this.ch != 'l') {
            throw new JSONException("error parse null");
        }
        next();
        char c4 = this.ch;
        if (c4 == ' ' || c4 == ',' || c4 == '}' || c4 == ']' || c4 == '\n' || c4 == '\r' || c4 == '\t' || c4 == 26 || c4 == '\f' || c4 == '\b') {
            this.token = 8;
            return;
        }
        throw new JSONException("scan null error");
    }

    public final void scanFalse() {
        if (this.ch != 'f') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'a') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'l') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 's') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'e') {
            throw new JSONException("error parse false");
        }
        next();
        char c2 = this.ch;
        if (c2 == ' ' || c2 == ',' || c2 == '}' || c2 == ']' || c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == 26 || c2 == '\f' || c2 == '\b' || c2 == ':' || c2 == '/') {
            this.token = 7;
            return;
        }
        throw new JSONException("scan false error");
    }

    public final void scanIdent() {
        this.np = this.bp - 1;
        this.hasSpecial = false;
        do {
            this.sp++;
            next();
        } while (Character.isLetterOrDigit(this.ch));
        String strStringVal = stringVal();
        if ("null".equalsIgnoreCase(strStringVal)) {
            this.token = 8;
            return;
        }
        if ("new".equals(strStringVal)) {
            this.token = 9;
            return;
        }
        if ("true".equals(strStringVal)) {
            this.token = 6;
            return;
        }
        if (Bugly.SDK_IS_DEV.equals(strStringVal)) {
            this.token = 7;
            return;
        }
        if ("undefined".equals(strStringVal)) {
            this.token = 23;
            return;
        }
        if ("Set".equals(strStringVal)) {
            this.token = 21;
        } else if ("TreeSet".equals(strStringVal)) {
            this.token = 22;
        } else {
            this.token = 18;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String readString(char[] r12, int r13) {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.readString(char[], int):java.lang.String");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public boolean isBlankInput() {
        int i = 0;
        while (true) {
            char cCharAt = charAt(i);
            if (cCharAt == 26) {
                this.token = 20;
                return true;
            }
            if (!isWhitespace(cCharAt)) {
                return false;
            }
            i++;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void skipWhitespace() {
        while (true) {
            char c2 = this.ch;
            if (c2 > '/') {
                return;
            }
            if (c2 == ' ' || c2 == '\r' || c2 == '\n' || c2 == '\t' || c2 == '\f' || c2 == '\b') {
                next();
            } else if (c2 != '/') {
                return;
            } else {
                skipComment();
            }
        }
    }

    private void scanStringSingleQuote() {
        this.np = this.bp;
        this.hasSpecial = false;
        while (true) {
            char next = next();
            if (next == '\'') {
                this.token = 4;
                next();
                return;
            }
            if (next == 26) {
                if (!isEOF()) {
                    putChar(JSONLexer.EOI);
                } else {
                    throw new JSONException("unclosed single-quote string");
                }
            } else if (next == '\\') {
                if (!this.hasSpecial) {
                    this.hasSpecial = true;
                    int i = this.sp;
                    char[] cArr = this.sbuf;
                    if (i > cArr.length) {
                        char[] cArr2 = new char[i * 2];
                        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                        this.sbuf = cArr2;
                    }
                    copyTo(this.np + 1, this.sp, this.sbuf);
                }
                char next2 = next();
                if (next2 == '\"') {
                    putChar(Typography.quote);
                } else if (next2 != '\'') {
                    if (next2 != 'F') {
                        if (next2 == '\\') {
                            putChar(IOUtils.DIR_SEPARATOR_WINDOWS);
                        } else if (next2 == 'b') {
                            putChar('\b');
                        } else if (next2 != 'f') {
                            if (next2 == 'n') {
                                putChar('\n');
                            } else if (next2 == 'r') {
                                putChar('\r');
                            } else if (next2 != 'x') {
                                switch (next2) {
                                    case '/':
                                        putChar(IOUtils.DIR_SEPARATOR_UNIX);
                                        break;
                                    case '0':
                                        putChar((char) 0);
                                        break;
                                    case '1':
                                        putChar((char) 1);
                                        break;
                                    case '2':
                                        putChar((char) 2);
                                        break;
                                    case '3':
                                        putChar((char) 3);
                                        break;
                                    case '4':
                                        putChar((char) 4);
                                        break;
                                    case '5':
                                        putChar((char) 5);
                                        break;
                                    case '6':
                                        putChar((char) 6);
                                        break;
                                    case '7':
                                        putChar((char) 7);
                                        break;
                                    default:
                                        switch (next2) {
                                            case 't':
                                                putChar('\t');
                                                break;
                                            case 'u':
                                                putChar((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                                                break;
                                            case 'v':
                                                putChar((char) 11);
                                                break;
                                            default:
                                                this.ch = next2;
                                                throw new JSONException("unclosed single-quote string");
                                        }
                                        break;
                                }
                            } else {
                                putChar((char) ((digits[next()] * 16) + digits[next()]));
                            }
                        }
                    }
                    putChar('\f');
                } else {
                    putChar('\'');
                }
            } else if (!this.hasSpecial) {
                this.sp++;
            } else {
                int i2 = this.sp;
                char[] cArr3 = this.sbuf;
                if (i2 == cArr3.length) {
                    putChar(next);
                } else {
                    this.sp = i2 + 1;
                    cArr3[i2] = next;
                }
            }
        }
    }

    protected final void putChar(char c2) {
        int i = this.sp;
        char[] cArr = this.sbuf;
        if (i == cArr.length) {
            char[] cArr2 = new char[cArr.length * 2];
            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
            this.sbuf = cArr2;
        }
        char[] cArr3 = this.sbuf;
        int i2 = this.sp;
        this.sp = i2 + 1;
        cArr3[i2] = c2;
    }

    public final void scanHex() {
        char next;
        if (this.ch != 'x') {
            throw new JSONException("illegal state. " + this.ch);
        }
        next();
        if (this.ch != '\'') {
            throw new JSONException("illegal state. " + this.ch);
        }
        this.np = this.bp;
        next();
        while (true) {
            next = next();
            if ((next < '0' || next > '9') && (next < 'A' || next > 'F')) {
                break;
            } else {
                this.sp++;
            }
        }
        if (next == '\'') {
            this.sp++;
            next();
            this.token = 26;
        } else {
            throw new JSONException("illegal state. " + next);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ce  */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void scanNumber() {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanNumber():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0084  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x005b -> B:12:0x0032). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long longValue() throws java.lang.NumberFormatException {
        /*
            r15 = this;
            int r0 = r15.np
            r1 = 0
            r2 = -1
            if (r0 != r2) goto L8
            r15.np = r1
        L8:
            int r0 = r15.np
            int r2 = r15.sp
            int r2 = r2 + r0
            char r3 = r15.charAt(r0)
            r4 = 45
            r5 = 1
            if (r3 != r4) goto L1c
            r3 = -9223372036854775808
            int r0 = r0 + 1
            r1 = r5
            goto L21
        L1c:
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L21:
            r6 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            if (r0 >= r2) goto L34
            int r8 = r0 + 1
            char r0 = r15.charAt(r0)
            int r0 = r0 + (-48)
            int r0 = -r0
            long r9 = (long) r0
        L32:
            r0 = r8
            goto L36
        L34:
            r9 = 0
        L36:
            if (r0 >= r2) goto L72
            int r8 = r0 + 1
            char r0 = r15.charAt(r0)
            r11 = 76
            if (r0 == r11) goto L71
            r11 = 83
            if (r0 == r11) goto L71
            r11 = 66
            if (r0 != r11) goto L4b
            goto L71
        L4b:
            int r0 = r0 + (-48)
            int r11 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r11 < 0) goto L67
            r11 = 10
            long r9 = r9 * r11
            long r11 = (long) r0
            long r13 = r3 + r11
            int r0 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r0 < 0) goto L5d
            long r9 = r9 - r11
            goto L32
        L5d:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r15.numberString()
            r0.<init>(r1)
            throw r0
        L67:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r15.numberString()
            r0.<init>(r1)
            throw r0
        L71:
            r0 = r8
        L72:
            if (r1 == 0) goto L84
            int r1 = r15.np
            int r1 = r1 + r5
            if (r0 <= r1) goto L7a
            return r9
        L7a:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r15.numberString()
            r0.<init>(r1)
            throw r0
        L84:
            long r0 = -r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.longValue():long");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final Number decimalValue(boolean z) {
        char cCharAt = charAt((this.np + this.sp) - 1);
        try {
            if (cCharAt == 'F') {
                return Float.valueOf(Float.parseFloat(numberString()));
            }
            if (cCharAt == 'D') {
                return Double.valueOf(Double.parseDouble(numberString()));
            }
            if (z) {
                return decimalValue();
            }
            return Double.valueOf(doubleValue());
        } catch (NumberFormatException e2) {
            throw new JSONException(e2.getMessage() + ", " + info());
        }
    }

    public String[] scanFieldStringArray(char[] cArr, int i, SymbolTable symbolTable) {
        throw new UnsupportedOperationException();
    }

    public boolean matchField2(char[] cArr) {
        throw new UnsupportedOperationException();
    }
}