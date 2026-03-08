package com.ido.life.module.home.pressure.question;

import com.ido.life.base.IBaseView;
import kotlin.Metadata;

/* JADX INFO: compiled from: PressureQuestionActivityView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/ido/life/module/home/pressure/question/PressureQuestionActivityView;", "Lcom/ido/life/base/IBaseView;", "updateQuestionProgress", "", "count", "", "total", "app_release"}, k = 1, mv = {1, 1, 16})
public interface PressureQuestionActivityView extends IBaseView {
    void updateQuestionProgress(int count, int total);
}