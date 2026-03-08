package com.ido.ble.bluetooth.connect;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.DeviceGattCallBack;
import com.ido.ble.logs.LogTool;
import java.lang.reflect.Field;
import java.util.LinkedList;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
abstract class c extends com.ido.ble.bluetooth.connect.b {
    private static final int v = 1;
    private static final long w = 3000;
    private static final int x = 10;
    private static long y = 2000;
    private BluetoothGattCharacteristic s;
    private BluetoothGattCharacteristic t;
    private LinkedList<byte[]> o = new LinkedList<>();
    private boolean p = false;
    private boolean q = false;
    private int r = 0;
    private Handler u = new a(Looper.getMainLooper());

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1 && c.this.p) {
                c.this.p = false;
                if (c.this.o.size() > 10) {
                    LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] last send out time, mCmdDataQueue.size() > 10, clear and disconnect");
                    c.this.u();
                } else {
                    LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] no respond on last cmd, send next ...");
                    c.this.A();
                }
            }
        }
    }

    class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ byte[] f4041a;

        b(byte[] bArr) {
            this.f4041a = bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.q = true;
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] retry send => " + com.ido.ble.common.c.b(this.f4041a));
            c.this.i(this.f4041a);
        }
    }

    c() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void A() {
        if (this.p) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] sendNextCmdData is ing");
            return;
        }
        if (this.q) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] retrySendData is ing");
            return;
        }
        if (this.o.size() == 0) {
            return;
        }
        this.p = true;
        byte[] bArrPoll = this.o.poll();
        if (bArrPoll == null) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] sendNextCmdData data is null");
            this.p = false;
            A();
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] send => " + com.ido.ble.common.c.b(bArrPoll));
        if (r()) {
            i(bArrPoll);
        } else {
            this.p = false;
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] send(), isCanSendData = false. send failed");
        }
    }

    private BluetoothGattCharacteristic a(BluetoothGatt bluetoothGatt, byte[] bArr) {
        if (d(bArr)) {
            if (this.t == null) {
                this.t = com.ido.ble.bluetooth.e.a.a(bluetoothGatt);
            }
            return this.t;
        }
        if (this.s == null) {
            this.s = com.ido.ble.bluetooth.e.a.b(bluetoothGatt);
        }
        return this.s;
    }

    private void a(byte[] bArr, int i) {
        if (i == 0) {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] onDeviceResponseOnLastSend( " + com.ido.ble.common.c.b(bArr) + ")");
        } else {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] onDeviceResponseOnLastSend[failed]( " + com.ido.ble.common.c.b(bArr) + ")");
        }
        this.p = false;
        this.u.removeMessages(1);
        A();
    }

    private void b(byte[] bArr) {
        if (!r()) {
            this.q = false;
            this.r = 0;
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] handleWriteFailedStatus(), isCanSendData = false. send failed");
            return;
        }
        this.r++;
        if (this.r > 10) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] handleWriteFailedStatus, mSendFailedCount > 10, clear and disconnect");
            u();
            this.q = false;
            this.r = 0;
            return;
        }
        if (!c(bArr) && !f(bArr)) {
            this.p = false;
            A();
            return;
        }
        this.q = true;
        LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] isFileTranCmd  mIsReSendData:" + this.q);
        h(bArr);
    }

    private boolean c(byte[] bArr) {
        return (bArr[0] & UByte.MAX_VALUE) == 209;
    }

    private boolean d(byte[] bArr) {
        return bArr[0] == 8 || bArr[0] == 9;
    }

    private boolean e(byte[] bArr) {
        return (bArr[0] & UByte.MAX_VALUE) == 209 || (bArr[0] & UByte.MAX_VALUE) == 19;
    }

    private boolean f(byte[] bArr) {
        return (bArr[0] & UByte.MAX_VALUE) == 19 || (bArr[0] & UByte.MAX_VALUE) == 17;
    }

    private void g(byte[] bArr) {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] receive <= " + com.ido.ble.common.c.b(bArr));
    }

    private void h(byte[] bArr) {
        this.u.postDelayed(new b(bArr), 300L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(byte[] bArr) {
        if (!j(bArr)) {
            b(bArr);
            return;
        }
        this.r = 0;
        this.q = false;
        LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] mIsReSendData " + this.q);
        if (!e(bArr)) {
            this.u.sendEmptyMessageDelayed(1, 3000L);
        } else {
            this.p = false;
            A();
        }
    }

    private boolean j(byte[] bArr) {
        String str;
        String str2;
        BluetoothGattCharacteristic bluetoothGattCharacteristicA = a(w(), bArr);
        if (bluetoothGattCharacteristicA == null || (bluetoothGattCharacteristicA.getProperties() & 8) <= 0) {
            str = com.ido.ble.bluetooth.e.b.f4128a;
            str2 = "[BytesDataConnect] send(), characteristic error!";
        } else {
            bluetoothGattCharacteristicA.setValue(bArr);
            bluetoothGattCharacteristicA.setWriteType(e(bArr) ? 1 : 2);
            if ((bluetoothGattCharacteristicA.getProperties() & 12) != 0) {
                boolean zWriteCharacteristic = w().writeCharacteristic(bluetoothGattCharacteristicA);
                if (!zWriteCharacteristic) {
                    LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] send(), writeCharacteristic() error!");
                }
                return zWriteCharacteristic;
            }
            str = com.ido.ble.bluetooth.e.b.f4128a;
            str2 = "[BytesDataConnect] send(), characteristic.properties error!";
        }
        LogTool.b(str, str2);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] clearQueueAndDisconnect");
        this.o.clear();
        o();
    }

    private void v() {
        this.o.clear();
        this.p = false;
        this.q = false;
        this.r = 0;
        this.u.removeCallbacksAndMessages(null);
        this.t = null;
        this.s = null;
    }

    private BluetoothGatt w() {
        return q();
    }

    private void x() {
        if (this.o.size() > 10) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] cmd queue is out of max size, handle it...");
            for (int i = 0; i < 5; i++) {
                this.o.pollFirst();
            }
        }
    }

    private boolean y() {
        try {
            return ((Boolean) a(w(), "mDeviceBusy")).booleanValue();
        } catch (IllegalAccessException | NoSuchFieldException e2) {
            e2.printStackTrace();
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] isDeviceBusy()，e.printStackTrace() :" + e2.toString());
            return false;
        }
    }

    private void z() {
        v();
    }

    public Object a(Object obj, String str) throws NoSuchFieldException {
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void a(int i, int i2) {
        v();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        g(bluetoothGattCharacteristic.getValue());
        DeviceGattCallBack.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        a(bluetoothGattCharacteristic.getValue(), i);
        DeviceGattCallBack.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
    }

    protected void a(byte[] bArr, boolean z) {
        if (bArr == null || bArr.length == 0) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] onAddCmd() ignore, data is null");
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] addCmdData( " + com.ido.ble.common.c.b(bArr) + ")");
        if (z) {
            this.o.addFirst(bArr);
        } else {
            this.o.add(bArr);
        }
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] addCmdData que size = " + this.o.size());
        A();
    }

    public boolean a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (w() == null) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] getRealGatt() == null!");
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - jCurrentTimeMillis < y) {
                if (!y()) {
                    LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] mDeviceBusy == false，break");
                    break;
                }
                try {
                    Thread.sleep(100L);
                    LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] mDeviceBusy == true，sleep 100ms");
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                    LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BytesDataConnect] mDeviceBusy == true，e.printStackTrace() :" + e2.toString());
                }
            } else {
                break;
            }
        }
        return w().writeCharacteristic(bluetoothGattCharacteristic);
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void b(BLEDevice bLEDevice, long j) {
        super.b(bLEDevice, j);
        z();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void d(BLEDevice bLEDevice) {
        super.d(bLEDevice);
        z();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void e() {
        v();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void j() {
    }
}