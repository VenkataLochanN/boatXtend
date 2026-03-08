package com.ido.life.module.user.userdata.message;

import com.ido.life.base.BasePresenter;
import com.ido.life.database.model.MessageEntity;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserMessagePresenter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/ido/life/module/user/userdata/message/UserMessagePresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/user/userdata/message/IUserMessageView;", "()V", "getMessageList", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final class UserMessagePresenter extends BasePresenter<IUserMessageView> {
    public final void getMessageList() {
        IUserMessageView view;
        if (getView() == null) {
            return;
        }
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        long userId = runTimeUtil.getUserId();
        RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
        List<MessageEntity> listQueryReportByWeekStart = GreenDaoUtil.queryReportByWeekStart(userId, runTimeUtil2.getWeekStart());
        RunTimeUtil runTimeUtil3 = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil3, "RunTimeUtil.getInstance()");
        long jQueryUnreadReportCount = GreenDaoUtil.queryUnreadReportCount(runTimeUtil3.getUserId());
        boolean z = true;
        boolean z2 = jQueryUnreadReportCount > 0;
        List<MessageEntity> list = listQueryReportByWeekStart;
        if (list != null && !list.isEmpty()) {
            z = false;
        }
        if (z || (view = getView()) == null) {
            return;
        }
        MessageEntity messageEntity = listQueryReportByWeekStart.get(0);
        Intrinsics.checkExpressionValueIsNotNull(messageEntity, "reportMessageList[0]");
        view.onReceiveNewHealthMessage(messageEntity, z2);
    }
}