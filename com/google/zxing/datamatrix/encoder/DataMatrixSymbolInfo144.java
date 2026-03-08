package com.google.zxing.datamatrix.encoder;

import com.veryfit.multi.nativeprotocol.b;

/* JADX INFO: loaded from: classes2.dex */
final class DataMatrixSymbolInfo144 extends SymbolInfo {
    @Override // com.google.zxing.datamatrix.encoder.SymbolInfo
    public int getDataLengthForInterleavedBlock(int i) {
        return i <= 8 ? 156 : 155;
    }

    @Override // com.google.zxing.datamatrix.encoder.SymbolInfo
    public int getInterleavedBlockCount() {
        return 10;
    }

    DataMatrixSymbolInfo144() {
        super(false, 1558, b.J2, 22, 22, 36, -1, 62);
    }
}