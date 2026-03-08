package com.ido.life.enums;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ContractEnum.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\u0001\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, d2 = {"Lcom/ido/life/enums/ContractEnum;", "", "state", "", "(Ljava/lang/String;II)V", "State", "getState", "()I", "setState", "(I)V", "CONTRACT_EDIT", "CONTRACT_DELETE", "CONTRACT_CHOICE", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public enum ContractEnum {
    CONTRACT_EDIT(0),
    CONTRACT_DELETE(1),
    CONTRACT_CHOICE(2);


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int State;

    @JvmStatic
    public static final void getContractByState(int i) {
        INSTANCE.getContractByState(i);
    }

    public final int getState() {
        return this.State;
    }

    public final void setState(int i) {
        this.State = i;
    }

    ContractEnum(int i) {
        this.State = i;
    }

    /* JADX INFO: compiled from: ContractEnum.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/ido/life/enums/ContractEnum$Companion;", "", "()V", "getContractByState", "", "state", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void getContractByState(int state) {
            if (state == 0) {
                ContractEnum contractEnum = ContractEnum.CONTRACT_EDIT;
            } else if (state == 1) {
                ContractEnum contractEnum2 = ContractEnum.CONTRACT_DELETE;
            } else {
                if (state != 2) {
                    return;
                }
                ContractEnum contractEnum3 = ContractEnum.CONTRACT_CHOICE;
            }
        }
    }
}