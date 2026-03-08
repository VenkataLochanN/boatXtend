package com.ido.life.module.device.view;

import androidx.core.app.NotificationCompat;
import com.ido.life.base.IBaseView;
import kotlin.Metadata;

/* JADX INFO: compiled from: IQuickReplyMsgView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\t"}, d2 = {"Lcom/ido/life/module/device/view/IQuickReplyMsgView;", "Lcom/ido/life/base/IBaseView;", "onGetQuickReplyFailed", "", "onGetQuickReplySuccess", NotificationCompat.CATEGORY_STATUS, "", "onSetQuickReplyFailed", "onSetQuickReplySuccess", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IQuickReplyMsgView extends IBaseView {
    void onGetQuickReplyFailed();

    void onGetQuickReplySuccess(boolean status);

    void onSetQuickReplyFailed();

    void onSetQuickReplySuccess();
}