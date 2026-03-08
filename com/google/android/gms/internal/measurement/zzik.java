package com.google.android.gms.internal.measurement;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zzi' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public class zzik {
    public static final zzik zza = new zzik("DOUBLE", 0, zzir.DOUBLE, 1);
    public static final zzik zzb = new zzik("FLOAT", 1, zzir.FLOAT, 5);
    public static final zzik zzc = new zzik("INT64", 2, zzir.LONG, 0);
    public static final zzik zzd = new zzik("UINT64", 3, zzir.LONG, 0);
    public static final zzik zze = new zzik("INT32", 4, zzir.INT, 0);
    public static final zzik zzf = new zzik("FIXED64", 5, zzir.LONG, 1);
    public static final zzik zzg = new zzik("FIXED32", 6, zzir.INT, 5);
    public static final zzik zzh = new zzik("BOOL", 7, zzir.BOOLEAN, 0);
    public static final zzik zzi;
    public static final zzik zzj;
    public static final zzik zzk;
    public static final zzik zzl;
    public static final zzik zzm;
    public static final zzik zzn;
    public static final zzik zzo;
    public static final zzik zzp;
    public static final zzik zzq;
    public static final zzik zzr;
    private static final /* synthetic */ zzik[] zzu;
    private final zzir zzs;
    private final int zzt;

    public static zzik[] values() {
        return (zzik[]) zzu.clone();
    }

    private zzik(String str, int i, zzir zzirVar, int i2) {
        this.zzs = zzirVar;
        this.zzt = i2;
    }

    public final zzir zza() {
        return this.zzs;
    }

    public final int zzb() {
        return this.zzt;
    }

    /* synthetic */ zzik(String str, int i, zzir zzirVar, int i2, zzil zzilVar) {
        this(str, i, zzirVar, i2);
    }

    static {
        final int i = 2;
        final int i2 = 3;
        final zzir zzirVar = zzir.STRING;
        final int i3 = 8;
        final String str = "STRING";
        zzi = new zzik(str, i3, zzirVar, i) { // from class: com.google.android.gms.internal.measurement.zzin
            {
                int i4 = 8;
                int i5 = 2;
                zzil zzilVar = null;
            }
        };
        final zzir zzirVar2 = zzir.MESSAGE;
        final int i4 = 9;
        final String str2 = "GROUP";
        zzj = new zzik(str2, i4, zzirVar2, i2) { // from class: com.google.android.gms.internal.measurement.zzim
            {
                int i5 = 9;
                int i6 = 3;
                zzil zzilVar = null;
            }
        };
        final zzir zzirVar3 = zzir.MESSAGE;
        final int i5 = 10;
        final String str3 = "MESSAGE";
        zzk = new zzik(str3, i5, zzirVar3, i) { // from class: com.google.android.gms.internal.measurement.zzip
            {
                int i6 = 10;
                int i7 = 2;
                zzil zzilVar = null;
            }
        };
        final zzir zzirVar4 = zzir.BYTE_STRING;
        final int i6 = 11;
        final String str4 = "BYTES";
        zzl = new zzik(str4, i6, zzirVar4, i) { // from class: com.google.android.gms.internal.measurement.zzio
            {
                int i7 = 11;
                int i8 = 2;
                zzil zzilVar = null;
            }
        };
        zzm = new zzik("UINT32", 12, zzir.INT, 0);
        zzn = new zzik("ENUM", 13, zzir.ENUM, 0);
        zzo = new zzik("SFIXED32", 14, zzir.INT, 5);
        zzp = new zzik("SFIXED64", 15, zzir.LONG, 1);
        zzq = new zzik("SINT32", 16, zzir.INT, 0);
        zzr = new zzik("SINT64", 17, zzir.LONG, 0);
        zzu = new zzik[]{zza, zzb, zzc, zzd, zze, zzf, zzg, zzh, zzi, zzj, zzk, zzl, zzm, zzn, zzo, zzp, zzq, zzr};
    }
}