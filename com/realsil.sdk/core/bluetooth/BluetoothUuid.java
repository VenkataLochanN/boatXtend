package com.realsil.sdk.core.bluetooth;

import android.os.ParcelUuid;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.core.internal.view.SupportMenu;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public final class BluetoothUuid {
    public static final int UUID_BYTES_128_BIT = 16;
    public static final int UUID_BYTES_16_BIT = 2;
    public static final int UUID_BYTES_32_BIT = 4;
    public static final ParcelUuid AUDIO_SINK = ParcelUuid.fromString("0000110B-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid AUDIO_SOURCE = ParcelUuid.fromString("0000110A-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid ADV_AUDIO_DIST = ParcelUuid.fromString("0000110D-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid HSP = ParcelUuid.fromString("00001108-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid HSP_AG = ParcelUuid.fromString("00001112-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid HANDSFREE = ParcelUuid.fromString("0000111E-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid HANDSFREE_AG = ParcelUuid.fromString("0000111F-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid AVRCP_CONTROLLER = ParcelUuid.fromString("0000110E-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid AVRCP_TARGET = ParcelUuid.fromString("0000110C-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid OBEX_OBJECT_PUSH = ParcelUuid.fromString("00001105-0000-1000-8000-00805f9b34fb");
    public static final ParcelUuid HID = ParcelUuid.fromString("00001124-0000-1000-8000-00805f9b34fb");
    public static final ParcelUuid HOGP = ParcelUuid.fromString("00001812-0000-1000-8000-00805f9b34fb");
    public static final ParcelUuid PANU = ParcelUuid.fromString("00001115-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid NAP = ParcelUuid.fromString("00001116-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid BNEP = ParcelUuid.fromString("0000000f-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid PBAP_PCE = ParcelUuid.fromString("0000112e-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid PBAP_PSE = ParcelUuid.fromString("0000112f-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid MAP = ParcelUuid.fromString("00001134-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid MNS = ParcelUuid.fromString("00001133-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid MAS = ParcelUuid.fromString("00001132-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid BASE_UUID = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid[] RESERVED_UUIDS = {AUDIO_SINK, AUDIO_SOURCE, ADV_AUDIO_DIST, HSP, HANDSFREE, AVRCP_CONTROLLER, AVRCP_TARGET, OBEX_OBJECT_PUSH, PANU, NAP, MAP, MNS, MAS};

    public static boolean a(UUID uuid) {
        return (uuid.getMostSignificantBits() & (-281470681743361L)) == 0 && uuid.getLeastSignificantBits() == 0;
    }

    public static boolean containsAllUuids(ParcelUuid[] parcelUuidArr, ParcelUuid[] parcelUuidArr2) {
        if (parcelUuidArr == null && parcelUuidArr2 == null) {
            return true;
        }
        if (parcelUuidArr == null) {
            return parcelUuidArr2.length == 0;
        }
        if (parcelUuidArr2 == null) {
            return true;
        }
        HashSet hashSet = new HashSet(Arrays.asList(parcelUuidArr));
        for (ParcelUuid parcelUuid : parcelUuidArr2) {
            if (!hashSet.contains(parcelUuid)) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsAnyUuid(ParcelUuid[] parcelUuidArr, ParcelUuid[] parcelUuidArr2) {
        if (parcelUuidArr == null && parcelUuidArr2 == null) {
            return true;
        }
        if (parcelUuidArr == null) {
            return parcelUuidArr2.length == 0;
        }
        if (parcelUuidArr2 == null) {
            return parcelUuidArr.length == 0;
        }
        HashSet hashSet = new HashSet(Arrays.asList(parcelUuidArr));
        for (ParcelUuid parcelUuid : parcelUuidArr2) {
            if (hashSet.contains(parcelUuid)) {
                return true;
            }
        }
        return false;
    }

    public static UUID fromShortValue(int i) {
        return UUID.fromString("0000" + String.format("%04X", Integer.valueOf(i & SupportMenu.USER_MASK)) + "-0000-1000-8000-00805F9B34FB");
    }

    public static UUID fromString(String str) {
        try {
            return UUID.fromString(str);
        } catch (IllegalArgumentException unused) {
            return UUID.fromString("0000" + str + "-0000-1000-8000-00805F9B34FB");
        }
    }

    public static int getServiceIdentifierFromParcelUuid(ParcelUuid parcelUuid) {
        return (int) ((parcelUuid.getUuid().getMostSignificantBits() & 281470681743360L) >>> 32);
    }

    public static boolean is16BitUuid(ParcelUuid parcelUuid) {
        UUID uuid = parcelUuid.getUuid();
        return uuid.getLeastSignificantBits() == BASE_UUID.getUuid().getLeastSignificantBits() && (uuid.getMostSignificantBits() & (-281470681743361L)) == PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
    }

    public static boolean is32BitUuid(ParcelUuid parcelUuid) {
        UUID uuid = parcelUuid.getUuid();
        return uuid.getLeastSignificantBits() == BASE_UUID.getUuid().getLeastSignificantBits() && !is16BitUuid(parcelUuid) && (uuid.getMostSignificantBits() & 4294967295L) == PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
    }

    public static boolean isAdvAudioDist(ParcelUuid parcelUuid) {
        return parcelUuid.equals(ADV_AUDIO_DIST);
    }

    public static boolean isAudioSink(ParcelUuid parcelUuid) {
        return parcelUuid.equals(AUDIO_SINK);
    }

    public static boolean isAudioSource(ParcelUuid parcelUuid) {
        return parcelUuid.equals(AUDIO_SOURCE);
    }

    public static boolean isAvrcpController(ParcelUuid parcelUuid) {
        return parcelUuid.equals(AVRCP_CONTROLLER);
    }

    public static boolean isAvrcpTarget(ParcelUuid parcelUuid) {
        return parcelUuid.equals(AVRCP_TARGET);
    }

    public static boolean isBnep(ParcelUuid parcelUuid) {
        return parcelUuid.equals(BNEP);
    }

    public static boolean isHandsfree(ParcelUuid parcelUuid) {
        return parcelUuid.equals(HANDSFREE);
    }

    public static boolean isHeadset(ParcelUuid parcelUuid) {
        return parcelUuid.equals(HSP);
    }

    public static boolean isInputDevice(ParcelUuid parcelUuid) {
        return parcelUuid.equals(HID);
    }

    public static boolean isMap(ParcelUuid parcelUuid) {
        return parcelUuid.equals(MAP);
    }

    public static boolean isMas(ParcelUuid parcelUuid) {
        return parcelUuid.equals(MAS);
    }

    public static boolean isMns(ParcelUuid parcelUuid) {
        return parcelUuid.equals(MNS);
    }

    public static boolean isNap(ParcelUuid parcelUuid) {
        return parcelUuid.equals(NAP);
    }

    public static boolean isPanu(ParcelUuid parcelUuid) {
        return parcelUuid.equals(PANU);
    }

    public static boolean isUuidPresent(ParcelUuid[] parcelUuidArr, ParcelUuid parcelUuid) {
        if ((parcelUuidArr == null || parcelUuidArr.length == 0) && parcelUuid == null) {
            return true;
        }
        if (parcelUuidArr == null) {
            return false;
        }
        for (ParcelUuid parcelUuid2 : parcelUuidArr) {
            if (parcelUuid2.equals(parcelUuid)) {
                return true;
            }
        }
        return false;
    }

    public static boolean matches(UUID uuid, UUID uuid2) {
        return (a(uuid) || a(uuid2)) ? (uuid.getMostSignificantBits() & 281470681743360L) == (uuid2.getMostSignificantBits() & 281470681743360L) : uuid.equals(uuid2);
    }

    public static ParcelUuid parcelFromShortValue(int i) {
        return ParcelUuid.fromString("0000" + String.format("%04X", Integer.valueOf(i & SupportMenu.USER_MASK)) + "-0000-1000-8000-00805F9B34FB");
    }

    public static ParcelUuid parseUuidFrom(byte[] bArr) {
        long j;
        if (bArr == null) {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
        int length = bArr.length;
        if (length != 2 && length != 4 && length != 16) {
            throw new IllegalArgumentException("uuidBytes length invalid - " + length);
        }
        if (length == 16) {
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            return new ParcelUuid(new UUID(byteBufferOrder.getLong(8), byteBufferOrder.getLong(0)));
        }
        if (length == 2) {
            j = ((long) (bArr[0] & UByte.MAX_VALUE)) + ((long) ((bArr[1] & UByte.MAX_VALUE) << 8));
        } else {
            j = ((long) ((bArr[3] & UByte.MAX_VALUE) << 24)) + ((long) (bArr[0] & UByte.MAX_VALUE)) + ((long) ((bArr[1] & UByte.MAX_VALUE) << 8)) + ((long) ((bArr[2] & UByte.MAX_VALUE) << 16));
        }
        return new ParcelUuid(new UUID(BASE_UUID.getUuid().getMostSignificantBits() + (j << 32), BASE_UUID.getUuid().getLeastSignificantBits()));
    }

    public static ParcelUuid parseUuidReverse(byte[] bArr) {
        long j;
        if (bArr == null) {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
        int length = bArr.length;
        if (length != 2 && length != 4 && length != 16) {
            throw new IllegalArgumentException("uuidBytes length invalid - " + length);
        }
        if (length == 16) {
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            return new ParcelUuid(new UUID(byteBufferOrder.getLong(8), byteBufferOrder.getLong(0)));
        }
        if (length == 2) {
            j = ((long) (bArr[1] & UByte.MAX_VALUE)) + ((long) ((bArr[0] & UByte.MAX_VALUE) << 8));
        } else {
            j = ((long) ((bArr[0] & UByte.MAX_VALUE) << 24)) + ((long) (bArr[3] & UByte.MAX_VALUE)) + ((long) ((bArr[2] & UByte.MAX_VALUE) << 8)) + ((long) ((bArr[1] & UByte.MAX_VALUE) << 16));
        }
        return new ParcelUuid(new UUID(BASE_UUID.getUuid().getMostSignificantBits() + (j << 32), BASE_UUID.getUuid().getLeastSignificantBits()));
    }

    public static int toShortValue(UUID uuid) {
        return (int) ((uuid.getMostSignificantBits() >> 32) & 65535);
    }
}