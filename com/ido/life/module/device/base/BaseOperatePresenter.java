package com.ido.life.module.device.base;

import com.ido.ble.BLEManager;
import com.ido.ble.callback.OperateCallBack;
import com.ido.life.module.device.base.IBaseOperateView;
import com.ido.life.module.device.presenter.BaseMonitoringPresenter;

/* JADX INFO: loaded from: classes2.dex */
public class BaseOperatePresenter<T extends IBaseOperateView> extends BaseMonitoringPresenter<T> {
    private final OperateCallBack.ICallBack mOperateCallback = new OperateCallBack.ICallBack() { // from class: com.ido.life.module.device.base.BaseOperatePresenter.1
        @Override // com.ido.ble.callback.OperateCallBack.ICallBack
        public void onSetResult(OperateCallBack.OperateType operateType, boolean z) {
            BaseOperatePresenter.this.onOperateSetResult(operateType, z);
        }

        @Override // com.ido.ble.callback.OperateCallBack.ICallBack
        public void onAddResult(OperateCallBack.OperateType operateType, boolean z) {
            BaseOperatePresenter.this.onOperateAddResult(operateType, z);
        }

        @Override // com.ido.ble.callback.OperateCallBack.ICallBack
        public void onDeleteResult(OperateCallBack.OperateType operateType, boolean z) {
            BaseOperatePresenter.this.onOperateDeleteResult(operateType, z);
        }

        @Override // com.ido.ble.callback.OperateCallBack.ICallBack
        public void onModifyResult(OperateCallBack.OperateType operateType, boolean z) {
            BaseOperatePresenter.this.onOperateModifyResult(operateType, z);
        }

        @Override // com.ido.ble.callback.OperateCallBack.ICallBack
        public void onQueryResult(OperateCallBack.OperateType operateType, Object obj) {
            BaseOperatePresenter.this.onOperateQueryResult(operateType, obj);
        }
    };

    protected void onOperateAddResult(OperateCallBack.OperateType operateType, boolean z) {
    }

    protected void onOperateDeleteResult(OperateCallBack.OperateType operateType, boolean z) {
    }

    protected void onOperateModifyResult(OperateCallBack.OperateType operateType, boolean z) {
    }

    protected void onOperateQueryResult(OperateCallBack.OperateType operateType, Object obj) {
    }

    protected void onOperateSetResult(OperateCallBack.OperateType operateType, boolean z) {
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        addCallBack();
    }

    @Override // com.ido.life.module.device.presenter.BaseMonitoringPresenter, com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        removeCallBack();
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    public void removeCallBack() {
        BLEManager.unregisterOperateCallBack(this.mOperateCallback);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    public void addCallBack() {
        BLEManager.registerOperateCallBack(this.mOperateCallback);
    }
}