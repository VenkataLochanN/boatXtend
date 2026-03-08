package com.ido.life.module.user.userdata.usermedal;

import com.ido.life.base.IBaseView;
import kotlin.Metadata;

/* JADX INFO: compiled from: IUserMedalView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/ido/life/module/user/userdata/usermedal/IUserMedalView;", "Lcom/ido/life/base/IBaseView;", "onGetUserMedalFailed", "", "errMsg", "", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IUserMedalView extends IBaseView {
    void onGetUserMedalFailed(String errMsg);
}