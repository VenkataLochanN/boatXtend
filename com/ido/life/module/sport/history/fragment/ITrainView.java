package com.ido.life.module.sport.history.fragment;

import com.ido.life.base.IBaseView;
import kotlin.Metadata;

/* JADX INFO: compiled from: ITrainView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000e\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\bH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\rH&J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\rH&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\rH&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\rH&J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\rH&J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\rH&¨\u0006\u001b"}, d2 = {"Lcom/ido/life/module/sport/history/fragment/ITrainView;", "Lcom/ido/life/base/IBaseView;", "setOxygenVisible", "", "visible", "", "showEffectColor", "color", "", "showEffectProgress", "position", "showEffectText", "effect", "", "showEffectTip", "oxygen", "showOxygenProgress", "type", "showOxygenText", "showOxygenTextColor", "showOxygenTip", "showResumeDate", "dataStr", "showResumeTime", "resumeTime", "showResumeTimeTip", "tip", "app_release"}, k = 1, mv = {1, 1, 16})
public interface ITrainView extends IBaseView {
    void setOxygenVisible(boolean visible);

    void showEffectColor(int color);

    void showEffectProgress(int position);

    void showEffectText(String effect);

    void showEffectTip(String oxygen);

    void showOxygenProgress(int type, int position);

    void showOxygenText(String oxygen);

    void showOxygenTextColor(int color);

    void showOxygenTip(String oxygen);

    void showResumeDate(String dataStr);

    void showResumeTime(String resumeTime);

    void showResumeTimeTip(String tip);
}