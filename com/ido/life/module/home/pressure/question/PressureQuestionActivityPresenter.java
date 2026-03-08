package com.ido.life.module.home.pressure.question;

import com.ido.life.base.BasePresenter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: PressureQuestionActivityPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\u0006\u0010\r\u001a\u00020\bJ\b\u0010\u000e\u001a\u0004\u0018\u00010\bJ\b\u0010\u000f\u001a\u0004\u0018\u00010\bJ\u0006\u0010\u0010\u001a\u00020\nJ\u0006\u0010\u0011\u001a\u00020\u0005J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\u0006\u0010\u0015\u001a\u00020\u0013J\u0006\u0010\u0016\u001a\u00020\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/ido/life/module/home/pressure/question/PressureQuestionActivityPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/home/pressure/question/PressureQuestionActivityView;", "()V", "mQuestionCount", "", "mQuestionList", "", "", "attachView", "", "v", "geneQuestionList", "getCurrentQuestion", "getNextQuestion", "getPreviourQuestion", "getQuestionAnswerProgress", "getQuestionCount", "hasNextQuestion", "", "hasPreviousQuestion", "isFirstQuestion", "isLastQuestion", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class PressureQuestionActivityPresenter extends BasePresenter<PressureQuestionActivityView> {
    public static final int QUESTION_TOTAL = 12;
    private int mQuestionCount;
    private List<String> mQuestionList = new ArrayList();

    @Override // com.ido.life.base.BasePresenter
    public void attachView(PressureQuestionActivityView v) {
        super.attachView(v);
        this.mQuestionList = geneQuestionList();
    }

    public final void getQuestionAnswerProgress() {
        PressureQuestionActivityView view;
        if (!isAttachView() || (view = getView()) == null) {
            return;
        }
        view.updateQuestionProgress(this.mQuestionCount, 12);
    }

    private final List<String> geneQuestionList() {
        ArrayList arrayList = new ArrayList();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (linkedHashSet.size() < 12) {
            linkedHashSet.add(Integer.valueOf((int) (Math.random() * ((double) 70))));
        }
        int size = linkedHashSet.size();
        int i = 0;
        while (i < size) {
            StringBuilder sb = new StringBuilder();
            sb.append("question_");
            i++;
            sb.append(i);
            arrayList.add(sb.toString());
        }
        return arrayList;
    }

    public final boolean isLastQuestion() {
        return this.mQuestionCount >= 11;
    }

    public final boolean isFirstQuestion() {
        return this.mQuestionCount == 0;
    }

    public final boolean hasNextQuestion() {
        return this.mQuestionCount < 11;
    }

    public final boolean hasPreviousQuestion() {
        return this.mQuestionCount > 0;
    }

    public final String getNextQuestion() {
        PressureQuestionActivityView view;
        if (!hasNextQuestion()) {
            return null;
        }
        this.mQuestionCount++;
        int i = this.mQuestionCount;
        if (isAttachView() && (view = getView()) != null) {
            view.updateQuestionProgress(this.mQuestionCount, 12);
        }
        return this.mQuestionList.get(this.mQuestionCount);
    }

    public final String getPreviourQuestion() {
        PressureQuestionActivityView view;
        if (!hasPreviousQuestion()) {
            return null;
        }
        this.mQuestionCount--;
        int i = this.mQuestionCount;
        if (isAttachView() && (view = getView()) != null) {
            view.updateQuestionProgress(this.mQuestionCount, 12);
        }
        return this.mQuestionList.get(this.mQuestionCount);
    }

    public final String getCurrentQuestion() {
        return this.mQuestionList.get(this.mQuestionCount);
    }

    /* JADX INFO: renamed from: getQuestionCount, reason: from getter */
    public final int getMQuestionCount() {
        return this.mQuestionCount;
    }
}