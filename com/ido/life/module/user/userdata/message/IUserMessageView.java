package com.ido.life.module.user.userdata.message;

import com.ido.life.base.IBaseView;
import com.ido.life.database.model.MessageEntity;
import kotlin.Metadata;

/* JADX INFO: compiled from: IUserMessageView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\n"}, d2 = {"Lcom/ido/life/module/user/userdata/message/IUserMessageView;", "Lcom/ido/life/base/IBaseView;", "onReceiveNewHealthMessage", "", "entity", "Lcom/ido/life/database/model/MessageEntity;", "hasUnRead", "", "onReceiveNewMedalMessage", "onReceiveOtherMessage", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IUserMessageView extends IBaseView {
    void onReceiveNewHealthMessage(MessageEntity entity, boolean hasUnRead);

    void onReceiveNewMedalMessage(MessageEntity entity, boolean hasUnRead);

    void onReceiveOtherMessage(MessageEntity entity, boolean hasUnRead);
}