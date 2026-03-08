package com.baidu.mapframework.open.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.baidu.mapframework.open.aidl.b;

/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: com.baidu.mapframework.open.aidl.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0028a extends Binder implements a {

        /* JADX INFO: renamed from: com.baidu.mapframework.open.aidl.a$a$a, reason: collision with other inner class name */
        private static class C0029a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f3389a;

            C0029a(IBinder iBinder) {
                this.f3389a = iBinder;
            }

            @Override // com.baidu.mapframework.open.aidl.a
            public void a(b bVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.baidu.mapframework.open.aidl.IMapOpenService");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    this.f3389a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3389a;
            }
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.baidu.mapframework.open.aidl.IMapOpenService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0029a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.baidu.mapframework.open.aidl.IMapOpenService");
                return true;
            }
            parcel.enforceInterface("com.baidu.mapframework.open.aidl.IMapOpenService");
            a(b.a.b(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }
    }

    void a(b bVar) throws RemoteException;
}