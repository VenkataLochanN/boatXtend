package com.amazon.identity.auth.device;

/* JADX INFO: loaded from: classes.dex */
public class CodePairError extends Exception {
    public static final String CODE_PAIR_ERROR_EXECEPTION = "CODE_PAIR_ERROR_EXECEPTION";
    public static final int RESULT_CODE_PAIR_ERROR = 1;
    private static final long serialVersionUID = 1;
    private final ERROR_TYPE _type;

    public enum ERROR_CATEGORY {
        ACTION,
        BAD_REQUEST,
        NETWORK,
        INTERNAL,
        UNKNOWN
    }

    public CodePairError(String str, ERROR_TYPE error_type) {
        super(str);
        this._type = error_type;
    }

    public CodePairError(String str, Throwable th, ERROR_TYPE error_type) {
        super(str, th);
        this._type = error_type;
    }

    public ERROR_CATEGORY getCategory() {
        return this._type.getCategory();
    }

    public ERROR_TYPE getType() {
        return this._type;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "CodePairError cat= " + this._type.getCategory() + " type=" + this._type + " - " + super.toString();
    }

    public static <T> CodePairError getCodePairError(Throwable th, Class<T> cls) {
        return getCodePairError(th, cls, ERROR_TYPE.ERROR_UNKNOWN);
    }

    public static <T> CodePairError getCodePairError(Throwable th, Class<T> cls, ERROR_TYPE error_type) {
        return new CodePairError("Unexpected error in " + cls.getName(), th, error_type);
    }

    public enum ERROR_TYPE {
        ERROR_INVALID_REQUEST(ERROR_CATEGORY.ACTION, 1),
        ERROR_INVALID_GRANT(ERROR_CATEGORY.ACTION, 2),
        ERROR_INVALID_CLIENT(ERROR_CATEGORY.ACTION, 3),
        ERROR_INVALID_SCOPE(ERROR_CATEGORY.ACTION, 4),
        ERROR_UNAUTHORIZED_CLIENT(ERROR_CATEGORY.ACTION, 5),
        ERROR_WEBVIEW_SSL(ERROR_CATEGORY.ACTION, 6),
        ERROR_ACCESS_DENIED(ERROR_CATEGORY.ACTION, 7),
        ERROR_COM(ERROR_CATEGORY.NETWORK, 8),
        ERROR_IO(ERROR_CATEGORY.NETWORK, 9),
        ERROR_BAD_PARAM(ERROR_CATEGORY.INTERNAL, 10),
        ERROR_JSON(ERROR_CATEGORY.INTERNAL, 11),
        ERROR_PARSE(ERROR_CATEGORY.INTERNAL, 12),
        ERROR_SERVER_REPSONSE(ERROR_CATEGORY.INTERNAL, 13),
        ERROR_DATA_STORAGE(ERROR_CATEGORY.INTERNAL, 14),
        ERROR_BAD_API_PARAM(ERROR_CATEGORY.BAD_REQUEST, 15),
        ERROR_INIT(ERROR_CATEGORY.BAD_REQUEST, 16),
        ERROR_RESOURCES(ERROR_CATEGORY.BAD_REQUEST, 17),
        ERROR_INVALID_API(ERROR_CATEGORY.BAD_REQUEST, 18),
        ERROR_SECURITY(ERROR_CATEGORY.BAD_REQUEST, 19),
        ERROR_UNKNOWN(ERROR_CATEGORY.UNKNOWN, 20),
        ERROR_REGISTRATION(ERROR_CATEGORY.ACTION, 21);

        private final ERROR_CATEGORY _category;
        private final int _value;

        ERROR_TYPE(ERROR_CATEGORY error_category, int i) {
            this._category = error_category;
            this._value = i;
        }

        public ERROR_CATEGORY getCategory() {
            return this._category;
        }

        public int getValue() {
            return this._value;
        }
    }
}