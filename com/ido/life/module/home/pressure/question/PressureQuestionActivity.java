package com.ido.life.module.home.pressure.question;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import com.boat.Xtend.two.R;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.life.base.BaseActivity;
import com.ido.life.module.home.pressure.guide.PressureAjustGuideActivity;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: PressureQuestionActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u0005B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0016\u001a\u00020\u000fH\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u000fH\u0014J\b\u0010\u001a\u001a\u00020\u0015H\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u001cH\u0016J\u0012\u0010\u001e\u001a\u00020\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0010\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020\u000fH\u0016J\u0018\u0010#\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u000fH\u0016R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR&\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/ido/life/module/home/pressure/question/PressureQuestionActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/home/pressure/question/PressureQuestionActivityPresenter;", "Lcom/ido/life/module/home/pressure/question/PressureQuestionActivityView;", "Lcom/ido/life/module/home/pressure/question/PressureQuestionCallback;", "Landroid/view/View$OnClickListener;", "()V", "mFragment", "Lcom/ido/life/module/home/pressure/question/PressureQuestionFragment;", "getMFragment", "()Lcom/ido/life/module/home/pressure/question/PressureQuestionFragment;", "setMFragment", "(Lcom/ido/life/module/home/pressure/question/PressureQuestionFragment;)V", "mScoreMap", "", "", "getMScoreMap", "()Ljava/util/Map;", "setMScoreMap", "(Ljava/util/Map;)V", "mSubmitDialog", "Lcom/ido/common/dialog/CommBottomConfirmDialog;", "caluteScore", "canDoubleClick", "", "getLayoutResId", "getSubmitDialog", "initLabelLanguage", "", "initViews", "onClick", "v", "Landroid/view/View;", "selectAnswer", "score", "updateQuestionProgress", "count", "total", "app_release"}, k = 1, mv = {1, 1, 16})
public final class PressureQuestionActivity extends BaseActivity<PressureQuestionActivityPresenter> implements PressureQuestionActivityView, PressureQuestionCallback, View.OnClickListener {
    private HashMap _$_findViewCache;
    public PressureQuestionFragment mFragment;
    private Map<Integer, Integer> mScoreMap = new LinkedHashMap();
    private CommBottomConfirmDialog mSubmitDialog;

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected boolean canDoubleClick() {
        return true;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_pressure_question_layout;
    }

    public final Map<Integer, Integer> getMScoreMap() {
        return this.mScoreMap;
    }

    public final void setMScoreMap(Map<Integer, Integer> map) {
        Intrinsics.checkParameterIsNotNull(map, "<set-?>");
        this.mScoreMap = map;
    }

    public final PressureQuestionFragment getMFragment() {
        PressureQuestionFragment pressureQuestionFragment = this.mFragment;
        if (pressureQuestionFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragment");
        }
        return pressureQuestionFragment;
    }

    public final void setMFragment(PressureQuestionFragment pressureQuestionFragment) {
        Intrinsics.checkParameterIsNotNull(pressureQuestionFragment, "<set-?>");
        this.mFragment = pressureQuestionFragment;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        ((PressureQuestionActivityPresenter) this.mPresenter).getQuestionAnswerProgress();
        PressureQuestionActivity pressureQuestionActivity = this;
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_previous)).setOnClickListener(pressureQuestionActivity);
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_submit)).setOnClickListener(pressureQuestionActivity);
        this.mFragment = PressureQuestionFragment.INSTANCE.getInstance(((PressureQuestionActivityPresenter) this.mPresenter).getCurrentQuestion());
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        PressureQuestionFragment pressureQuestionFragment = this.mFragment;
        if (pressureQuestionFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragment");
        }
        fragmentTransactionBeginTransaction.replace(R.id.lay_content, pressureQuestionFragment).commit();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.pressure_ajust));
        TextView tv_submit = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_submit);
        Intrinsics.checkExpressionValueIsNotNull(tv_submit, "tv_submit");
        tv_submit.setText(getLanguageText(R.string.mine_commit));
        TextView tv_previous = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_previous);
        Intrinsics.checkExpressionValueIsNotNull(tv_previous, "tv_previous");
        tv_previous.setText(getLanguageText(R.string.previous_question));
        String currentQuestion = ((PressureQuestionActivityPresenter) this.mPresenter).getCurrentQuestion();
        if (this.mScoreMap.containsKey(Integer.valueOf(((PressureQuestionActivityPresenter) this.mPresenter).getMQuestionCount()))) {
            PressureQuestionFragment pressureQuestionFragment = this.mFragment;
            if (pressureQuestionFragment == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFragment");
            }
            Integer num = this.mScoreMap.get(Integer.valueOf(((PressureQuestionActivityPresenter) this.mPresenter).getMQuestionCount()));
            if (num == null) {
                Intrinsics.throwNpe();
            }
            pressureQuestionFragment.updateQuestionTitle(currentQuestion, num.intValue());
            return;
        }
        PressureQuestionFragment pressureQuestionFragment2 = this.mFragment;
        if (pressureQuestionFragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragment");
        }
        pressureQuestionFragment2.updateQuestionTitle(currentQuestion, 0);
    }

    @Override // com.ido.life.module.home.pressure.question.PressureQuestionActivityView
    public void updateQuestionProgress(int count, int total) {
        ProgressBar progress_bar = (ProgressBar) _$_findCachedViewById(com.ido.life.R.id.progress_bar);
        Intrinsics.checkExpressionValueIsNotNull(progress_bar, "progress_bar");
        progress_bar.setMax(total);
        ProgressBar progress_bar2 = (ProgressBar) _$_findCachedViewById(com.ido.life.R.id.progress_bar);
        Intrinsics.checkExpressionValueIsNotNull(progress_bar2, "progress_bar");
        progress_bar2.setProgress(count);
        TextView tv_progress = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_progress);
        Intrinsics.checkExpressionValueIsNotNull(tv_progress, "tv_progress");
        StringBuilder sb = new StringBuilder();
        sb.append(count);
        sb.append(IOUtils.DIR_SEPARATOR_UNIX);
        sb.append(total);
        tv_progress.setText(sb.toString());
    }

    @Override // com.ido.life.module.home.pressure.question.PressureQuestionCallback
    public void selectAnswer(int score) {
        this.mScoreMap.put(Integer.valueOf(((PressureQuestionActivityPresenter) this.mPresenter).getMQuestionCount()), Integer.valueOf(score));
        TextView tv_previous = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_previous);
        Intrinsics.checkExpressionValueIsNotNull(tv_previous, "tv_previous");
        tv_previous.setVisibility(0);
        boolean z = true;
        if (((PressureQuestionActivityPresenter) this.mPresenter).hasNextQuestion()) {
            String nextQuestion = ((PressureQuestionActivityPresenter) this.mPresenter).getNextQuestion();
            String str = nextQuestion;
            if (str != null && str.length() != 0) {
                z = false;
            }
            if (!z) {
                if (this.mScoreMap.containsKey(Integer.valueOf(((PressureQuestionActivityPresenter) this.mPresenter).getMQuestionCount()))) {
                    PressureQuestionFragment pressureQuestionFragment = this.mFragment;
                    if (pressureQuestionFragment == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mFragment");
                    }
                    Integer num = this.mScoreMap.get(Integer.valueOf(((PressureQuestionActivityPresenter) this.mPresenter).getMQuestionCount()));
                    if (num == null) {
                        Intrinsics.throwNpe();
                    }
                    pressureQuestionFragment.updateQuestionTitle(nextQuestion, num.intValue());
                } else {
                    PressureQuestionFragment pressureQuestionFragment2 = this.mFragment;
                    if (pressureQuestionFragment2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mFragment");
                    }
                    pressureQuestionFragment2.updateQuestionTitle(nextQuestion, 0);
                }
            }
            if (((PressureQuestionActivityPresenter) this.mPresenter).hasNextQuestion()) {
                return;
            }
            TextView tv_submit = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_submit);
            Intrinsics.checkExpressionValueIsNotNull(tv_submit, "tv_submit");
            tv_submit.setVisibility(0);
            TextView tv_submit2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_submit);
            Intrinsics.checkExpressionValueIsNotNull(tv_submit2, "tv_submit");
            tv_submit2.setEnabled(false);
            TextView tv_submit3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_submit);
            Intrinsics.checkExpressionValueIsNotNull(tv_submit3, "tv_submit");
            tv_submit3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.color_A9ABAC)));
            return;
        }
        ProgressBar progress_bar = (ProgressBar) _$_findCachedViewById(com.ido.life.R.id.progress_bar);
        Intrinsics.checkExpressionValueIsNotNull(progress_bar, "progress_bar");
        ProgressBar progress_bar2 = (ProgressBar) _$_findCachedViewById(com.ido.life.R.id.progress_bar);
        Intrinsics.checkExpressionValueIsNotNull(progress_bar2, "progress_bar");
        progress_bar.setProgress(progress_bar2.getMax());
        TextView tv_progress = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_progress);
        Intrinsics.checkExpressionValueIsNotNull(tv_progress, "tv_progress");
        StringBuilder sb = new StringBuilder();
        ProgressBar progress_bar3 = (ProgressBar) _$_findCachedViewById(com.ido.life.R.id.progress_bar);
        Intrinsics.checkExpressionValueIsNotNull(progress_bar3, "progress_bar");
        sb.append(progress_bar3.getMax());
        sb.append(IOUtils.DIR_SEPARATOR_UNIX);
        ProgressBar progress_bar4 = (ProgressBar) _$_findCachedViewById(com.ido.life.R.id.progress_bar);
        Intrinsics.checkExpressionValueIsNotNull(progress_bar4, "progress_bar");
        sb.append(progress_bar4.getMax());
        tv_progress.setText(sb.toString());
        TextView tv_submit4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_submit);
        Intrinsics.checkExpressionValueIsNotNull(tv_submit4, "tv_submit");
        tv_submit4.setEnabled(true);
        TextView tv_submit5 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_submit);
        Intrinsics.checkExpressionValueIsNotNull(tv_submit5, "tv_submit");
        tv_submit5.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.color_FF4A00)));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_previous) {
            if (((PressureQuestionActivityPresenter) this.mPresenter).hasPreviousQuestion()) {
                TextView tv_submit = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_submit);
                Intrinsics.checkExpressionValueIsNotNull(tv_submit, "tv_submit");
                tv_submit.setVisibility(8);
                String previourQuestion = ((PressureQuestionActivityPresenter) this.mPresenter).getPreviourQuestion();
                String str = previourQuestion;
                if (!(str == null || str.length() == 0)) {
                    if (this.mScoreMap.containsKey(Integer.valueOf(((PressureQuestionActivityPresenter) this.mPresenter).getMQuestionCount()))) {
                        PressureQuestionFragment pressureQuestionFragment = this.mFragment;
                        if (pressureQuestionFragment == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mFragment");
                        }
                        Integer num = this.mScoreMap.get(Integer.valueOf(((PressureQuestionActivityPresenter) this.mPresenter).getMQuestionCount()));
                        if (num == null) {
                            Intrinsics.throwNpe();
                        }
                        pressureQuestionFragment.updateQuestionTitle(previourQuestion, num.intValue());
                    } else {
                        PressureQuestionFragment pressureQuestionFragment2 = this.mFragment;
                        if (pressureQuestionFragment2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mFragment");
                        }
                        pressureQuestionFragment2.updateQuestionTitle(previourQuestion, 0);
                    }
                }
                if (((PressureQuestionActivityPresenter) this.mPresenter).hasPreviousQuestion()) {
                    return;
                }
                TextView tv_previous = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_previous);
                Intrinsics.checkExpressionValueIsNotNull(tv_previous, "tv_previous");
                tv_previous.setVisibility(8);
                return;
            }
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_submit) {
            if (this.mSubmitDialog == null) {
                this.mSubmitDialog = getSubmitDialog();
            }
            CommBottomConfirmDialog commBottomConfirmDialog = this.mSubmitDialog;
            if (commBottomConfirmDialog != null) {
                commBottomConfirmDialog.show(getSupportFragmentManager());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int caluteScore() {
        Map<Integer, Integer> map = this.mScoreMap;
        int iIntValue = 0;
        if (map == null || map.isEmpty()) {
            return 0;
        }
        Iterator<Map.Entry<Integer, Integer>> it = this.mScoreMap.entrySet().iterator();
        while (it.hasNext()) {
            iIntValue += it.next().getValue().intValue();
        }
        return iIntValue;
    }

    private final CommBottomConfirmDialog getSubmitDialog() {
        CommBottomConfirmDialog dialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.tips), getLanguageText(R.string.pressure_submit_message), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true);
        dialog.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.home.pressure.question.PressureQuestionActivity.getSubmitDialog.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Intent intent = new Intent(PressureQuestionActivity.this, (Class<?>) PressureAjustGuideActivity.class);
                intent.putExtra("score", PressureQuestionActivity.this.caluteScore());
                PressureQuestionActivity.this.startActivity(intent);
                CommBottomConfirmDialog commBottomConfirmDialog = PressureQuestionActivity.this.mSubmitDialog;
                if (commBottomConfirmDialog != null) {
                    commBottomConfirmDialog.dismiss();
                }
                PressureQuestionActivity.this.supportFinishAfterTransition();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog");
        return dialog;
    }
}