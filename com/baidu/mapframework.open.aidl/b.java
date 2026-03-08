package com.baidu.mapframework.open.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    public static abstract class a extends Binder implements b {

        /* JADX INFO: renamed from: com.baidu.mapframework.open.aidl.b$a$a, reason: collision with other inner class name */
        private static class C0030a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f3390a;

            C0030a(IBinder iBinder) {
                this.f3390a = iBinder;
            }

            @Override // com.baidu.mapframework.open.aidl.b
            public void a(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.baidu.mapframework.open.aidl.IOpenClientCallback");
                    parcelObtain.writeStrongBinder(iBinder);
                    this.f3390a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3390a;
            }
        }

        public a() {
            attachInterface(this, "com.baidu.mapframework.open.aidl.IOpenClientCallback");
        }

        public static b b(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.baidu.mapframework.open.aidl.IOpenClientCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0030a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.baidu.mapframework.open.aidl.IOpenClientCallback");
                return true;
            }
            parcel.enforceInterface("com.baidu.mapframework.open.aidl.IOpenClientCallback");
            a(parcel.readStrongBinder());
            parcel2.writeNoException();
            return true;
        }
    }

    void a(IBinder iBinder) throws RemoteException;
}