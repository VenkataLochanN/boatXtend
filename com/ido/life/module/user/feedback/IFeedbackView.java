package com.ido.life.module.user.feedback;

import android.view.View;
import com.ido.life.base.IBaseLoadingView;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: IFeedbackView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&J\b\u0010\u0007\u001a\u00020\bH&J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\u0012\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u000eH&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\bH&J\u0012\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0017"}, d2 = {"Lcom/ido/life/module/user/feedback/IFeedbackView;", "Lcom/ido/life/base/IBaseLoadingView;", "commitFeedBackSuccess", "", "getContract", "", "getDes", "getFeedbackType", "", "getPicList", "", "getView", "Landroid/view/View;", "isCheck", "", "notifyAdapter", "path", "setFeedbackEnable", "isClick", "setSubmitBg", "resourceId", "showError", "message", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IFeedbackView extends IBaseLoadingView {
    void commitFeedBackSuccess();

    String getContract();

    String getDes();

    int getFeedbackType();

    List<String> getPicList();

    View getView();

    boolean isCheck();

    void notifyAdapter(String path);

    void setFeedbackEnable(boolean isClick);

    void setSubmitBg(int resourceId);

    void showError(String message);
}