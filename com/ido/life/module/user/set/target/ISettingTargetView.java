package com.ido.life.module.user.set.target;

import com.ido.life.base.IBaseLoadingView;
import com.ido.life.database.model.UserTargetNew;
import kotlin.Metadata;

/* JADX INFO: compiled from: ISettingTargetView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\b\u0010\n\u001a\u00020\u0003H&¨\u0006\u000b"}, d2 = {"Lcom/ido/life/module/user/set/target/ISettingTargetView;", "Lcom/ido/life/base/IBaseLoadingView;", "getTargetInfoFailed", "", "getTargetInfoSuccess", "target", "Lcom/ido/life/database/model/UserTargetNew;", "setTargetInfoFailed", "errMsg", "", "setTargetInfoSuccess", "app_release"}, k = 1, mv = {1, 1, 16})
public interface ISettingTargetView extends IBaseLoadingView {
    void getTargetInfoFailed();

    void getTargetInfoSuccess(UserTargetNew target);

    void setTargetInfoFailed(String errMsg);

    void setTargetInfoSuccess();
}