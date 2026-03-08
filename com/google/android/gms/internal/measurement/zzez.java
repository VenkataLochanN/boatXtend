package com.google.android.gms.internal.measurement;

import java.lang.reflect.Type;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public enum zzez {
    DOUBLE(0, zzfb.SCALAR, zzfo.DOUBLE),
    FLOAT(1, zzfb.SCALAR, zzfo.FLOAT),
    INT64(2, zzfb.SCALAR, zzfo.LONG),
    UINT64(3, zzfb.SCALAR, zzfo.LONG),
    INT32(4, zzfb.SCALAR, zzfo.INT),
    FIXED64(5, zzfb.SCALAR, zzfo.LONG),
    FIXED32(6, zzfb.SCALAR, zzfo.INT),
    BOOL(7, zzfb.SCALAR, zzfo.BOOLEAN),
    STRING(8, zzfb.SCALAR, zzfo.STRING),
    MESSAGE(9, zzfb.SCALAR, zzfo.MESSAGE),
    BYTES(10, zzfb.SCALAR, zzfo.BYTE_STRING),
    UINT32(11, zzfb.SCALAR, zzfo.INT),
    ENUM(12, zzfb.SCALAR, zzfo.ENUM),
    SFIXED32(13, zzfb.SCALAR, zzfo.INT),
    SFIXED64(14, zzfb.SCALAR, zzfo.LONG),
    SINT32(15, zzfb.SCALAR, zzfo.INT),
    SINT64(16, zzfb.SCALAR, zzfo.LONG),
    GROUP(17, zzfb.SCALAR, zzfo.MESSAGE),
    DOUBLE_LIST(18, zzfb.VECTOR, zzfo.DOUBLE),
    FLOAT_LIST(19, zzfb.VECTOR, zzfo.FLOAT),
    INT64_LIST(20, zzfb.VECTOR, zzfo.LONG),
    UINT64_LIST(21, zzfb.VECTOR, zzfo.LONG),
    INT32_LIST(22, zzfb.VECTOR, zzfo.INT),
    FIXED64_LIST(23, zzfb.VECTOR, zzfo.LONG),
    FIXED32_LIST(24, zzfb.VECTOR, zzfo.INT),
    BOOL_LIST(25, zzfb.VECTOR, zzfo.BOOLEAN),
    STRING_LIST(26, zzfb.VECTOR, zzfo.STRING),
    MESSAGE_LIST(27, zzfb.VECTOR, zzfo.MESSAGE),
    BYTES_LIST(28, zzfb.VECTOR, zzfo.BYTE_STRING),
    UINT32_LIST(29, zzfb.VECTOR, zzfo.INT),
    ENUM_LIST(30, zzfb.VECTOR, zzfo.ENUM),
    SFIXED32_LIST(31, zzfb.VECTOR, zzfo.INT),
    SFIXED64_LIST(32, zzfb.VECTOR, zzfo.LONG),
    SINT32_LIST(33, zzfb.VECTOR, zzfo.INT),
    SINT64_LIST(34, zzfb.VECTOR, zzfo.LONG),
    DOUBLE_LIST_PACKED(35, zzfb.PACKED_VECTOR, zzfo.DOUBLE),
    FLOAT_LIST_PACKED(36, zzfb.PACKED_VECTOR, zzfo.FLOAT),
    INT64_LIST_PACKED(37, zzfb.PACKED_VECTOR, zzfo.LONG),
    UINT64_LIST_PACKED(38, zzfb.PACKED_VECTOR, zzfo.LONG),
    INT32_LIST_PACKED(39, zzfb.PACKED_VECTOR, zzfo.INT),
    FIXED64_LIST_PACKED(40, zzfb.PACKED_VECTOR, zzfo.LONG),
    FIXED32_LIST_PACKED(41, zzfb.PACKED_VECTOR, zzfo.INT),
    BOOL_LIST_PACKED(42, zzfb.PACKED_VECTOR, zzfo.BOOLEAN),
    UINT32_LIST_PACKED(43, zzfb.PACKED_VECTOR, zzfo.INT),
    ENUM_LIST_PACKED(44, zzfb.PACKED_VECTOR, zzfo.ENUM),
    SFIXED32_LIST_PACKED(45, zzfb.PACKED_VECTOR, zzfo.INT),
    SFIXED64_LIST_PACKED(46, zzfb.PACKED_VECTOR, zzfo.LONG),
    SINT32_LIST_PACKED(47, zzfb.PACKED_VECTOR, zzfo.INT),
    SINT64_LIST_PACKED(48, zzfb.PACKED_VECTOR, zzfo.LONG),
    GROUP_LIST(49, zzfb.VECTOR, zzfo.MESSAGE),
    MAP(50, zzfb.MAP, zzfo.VOID);

    private static final zzez[] zzbe;
    private static final Type[] zzbf = new Type[0];
    private final zzfo zzaz;
    private final int zzba;
    private final zzfb zzbb;
    private final Class<?> zzbc;
    private final boolean zzbd;

    zzez(int i, zzfb zzfbVar, zzfo zzfoVar) {
        int i2;
        this.zzba = i;
        this.zzbb = zzfbVar;
        this.zzaz = zzfoVar;
        int i3 = zzey.zza[zzfbVar.ordinal()];
        if (i3 == 1 || i3 == 2) {
            this.zzbc = zzfoVar.zza();
        } else {
            this.zzbc = null;
        }
        boolean z = false;
        if (zzfbVar == zzfb.SCALAR && (i2 = zzey.zzb[zzfoVar.ordinal()]) != 1 && i2 != 2 && i2 != 3) {
            z = true;
        }
        this.zzbd = z;
    }

    public final int zza() {
        return this.zzba;
    }

    static {
        zzez[] zzezVarArrValues = values();
        zzbe = new zzez[zzezVarArrValues.length];
        for (zzez zzezVar : zzezVarArrValues) {
            zzbe[zzezVar.zzba] = zzezVar;
        }
    }
}