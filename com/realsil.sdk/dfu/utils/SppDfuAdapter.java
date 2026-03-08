package com.realsil.sdk.dfu.utils;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import com.realsil.sdk.bbpro.core.transportlayer.AckPacket;
import com.realsil.sdk.bbpro.core.transportlayer.SppTransportLayer;
import com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback;
import com.realsil.sdk.bbpro.core.transportlayer.TransportLayerPacket;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.model.OtaModeInfo;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public class SppDfuAdapter extends DfuAdapter {
    public static volatile SppDfuAdapter mInstance;
    public SppTransportLayer df;
    public TransportLayerCallback ef = new TransportLayerCallback() { // from class: com.realsil.sdk.dfu.utils.SppDfuAdapter.1
        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onAckReceive(AckPacket ackPacket) {
            super.onAckReceive(ackPacket);
            SppDfuAdapter.this.a(ackPacket);
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onConnectionStateChanged(BluetoothDevice bluetoothDevice, boolean z, int i) {
            super.onConnectionStateChanged(bluetoothDevice, z, i);
            if (i != 512) {
                if (i == 0) {
                    if (SppDfuAdapter.this.isPreparing()) {
                        SppDfuAdapter.this.a(DfuException.ConnectionException(6));
                    }
                    SppDfuAdapter.this.e(1025);
                    return;
                }
                return;
            }
            SppDfuAdapter.this.B();
            SppDfuAdapter.this.Vc = new OtaDeviceInfo(2);
            SppDfuAdapter.this.De = new ArrayList();
            SppDfuAdapter.this.De.add(new OtaModeInfo(16));
            SppDfuAdapter.this.e(264);
            if (SppDfuAdapter.this.G().sendCmd((short) 1536, null)) {
                return;
            }
            SppDfuAdapter sppDfuAdapter = SppDfuAdapter.this;
            ZLogger.d(sppDfuAdapter.D, sppDfuAdapter.getOtaDeviceInfo().toString());
            SppDfuAdapter.this.e(512);
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onDataReceive(TransportLayerPacket transportLayerPacket) {
            super.onDataReceive(transportLayerPacket);
            try {
                SppDfuAdapter.this.a(transportLayerPacket);
            } catch (Exception e2) {
                ZLogger.e(e2.toString());
            }
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onError(int i) {
            super.onError(i);
        }
    };

    public SppDfuAdapter(Context context) {
        this.mContext = context;
        C();
    }

    public SppDfuAdapter(Context context, DfuAdapter.DfuHelperCallback dfuHelperCallback) {
        this.mContext = context;
        addDfuHelperCallback(dfuHelperCallback);
        C();
    }

    public static SppDfuAdapter getInstance(Context context) {
        if (mInstance == null) {
            synchronized (SppDfuAdapter.class) {
                if (mInstance == null) {
                    mInstance = new SppDfuAdapter(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    public static SppDfuAdapter getInstance(Context context, DfuAdapter.DfuHelperCallback dfuHelperCallback) {
        if (mInstance == null) {
            synchronized (SppDfuAdapter.class) {
                if (mInstance == null) {
                    mInstance = new SppDfuAdapter(context.getApplicationContext(), dfuHelperCallback);
                }
            }
        }
        return mInstance;
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public void C() {
        super.C();
        G();
    }

    public final SppTransportLayer G() {
        if (this.df == null) {
            this.df = SppTransportLayer.getInstance();
            this.df.register(this.ef);
        }
        return this.df;
    }

    public final void a(AckPacket ackPacket) {
        int toAckId = ackPacket.getToAckId();
        byte status = ackPacket.getStatus();
        if (toAckId == 24) {
            ZLogger.v("ACK-CMD_GET_STATUS");
            if (status == 2 || status == 1) {
                ZLogger.w("CMD_GET_STATUS not support");
                if (this.mState == 266) {
                    e(512);
                    return;
                }
                return;
            }
            return;
        }
        if (toAckId != 1536) {
            return;
        }
        ZLogger.v("ACK-CMD_OTA_GET_DEVICE_INFO");
        if (status == 2 || status == 1) {
            ZLogger.w("CMD_OTA_GET_DEVICE_INFO not support");
            if (this.mState == 264) {
                DfuAdapter.DfuHelperCallback dfuHelperCallback = this.Je;
                if (dfuHelperCallback != null) {
                    dfuHelperCallback.onError(65536, DfuException.ERROR_DFU_SPP_OTA_NOT_SUPPORTED);
                } else {
                    ZLogger.v("no callback registed");
                }
            }
        }
    }

    public final void a(TransportLayerPacket transportLayerPacket) {
        int opcode = transportLayerPacket.getOpcode();
        transportLayerPacket.getPayload();
        byte[] parameters = transportLayerPacket.getParameters();
        ZLogger.d(String.format(Locale.US, "[0x%04X >>] %s", Integer.valueOf(opcode), DataConverter.bytes2HexWithSeparate(parameters)));
        if (opcode != 25) {
            if (opcode == 1536) {
                getOtaDeviceInfo().parse(parameters);
                ZLogger.v(getOtaDeviceInfo().toString());
                e(265);
                if (G().sendCmd((short) 1537, null)) {
                    return;
                }
            } else {
                if (opcode != 1537) {
                    return;
                }
                getOtaDeviceInfo().appendImageVersionBytes(parameters);
                ZLogger.v(getOtaDeviceInfo().toString());
                e(266);
                if (G().sendCmd((short) 24, new byte[]{2})) {
                    return;
                }
            }
            ZLogger.v(getOtaDeviceInfo().toString());
        } else {
            if (this.mState != 266 || parameters == null || parameters.length <= 0 || parameters[0] != 2) {
                return;
            }
            if (parameters.length > 1) {
                getOtaDeviceInfo().setBatteryLevel(parameters[1] & UByte.MAX_VALUE);
            }
        }
        e(512);
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public boolean connectDevice(ConnectParams connectParams) {
        if (!super.connectDevice(connectParams)) {
            return false;
        }
        this.Ae = connectParams.getAddress();
        this.ze = getRemoteDevice(connectParams.getAddress());
        this.ye = getBondState(connectParams.getAddress());
        ZLogger.v(this.D, String.format(Locale.US, ">> mBondState: %d", Integer.valueOf(this.ye)));
        e(260);
        G().register(this.ef);
        return G().connect(this.ze, null);
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public void destroy() {
        super.destroy();
        SppTransportLayer sppTransportLayer = this.df;
        if (sppTransportLayer != null) {
            sppTransportLayer.unregister(this.ef);
        }
        mInstance = null;
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public void disconnect() {
        e(1024);
        G().disconnect();
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public void reconnect() {
        super.reconnect();
        Handler handler = this.Ee;
        if (handler != null) {
            handler.postDelayed(this.Fe, 1000L);
        }
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public boolean startOtaProcedure(DfuConfig dfuConfig, boolean z) {
        if (!super.startOtaProcedure(dfuConfig, z)) {
            return false;
        }
        e(513);
        G().unregister(this.ef);
        boolean zA = this.Ie.a(dfuConfig);
        if (!zA) {
            e(2048);
        }
        return zA;
    }
}