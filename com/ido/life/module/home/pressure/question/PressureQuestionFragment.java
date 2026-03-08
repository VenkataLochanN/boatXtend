package com.ido.life.module.home.pressure.question;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.FileSizeUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseFragment;
import com.ido.life.base.BasePresenter;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PressureQuestionFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00152\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u00012\u00020\u0003:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0014J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\fH\u0014J\u0016\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\nR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/ido/life/module/home/pressure/question/PressureQuestionFragment;", "Lcom/ido/life/base/BaseFragment;", "Lcom/ido/life/base/BasePresenter;", "Landroid/view/View$OnClickListener;", "()V", "mCallback", "Lcom/ido/life/module/home/pressure/question/PressureQuestionCallback;", "canDoubleClick", "", "getLayoutResId", "", "initData", "", "onClick", "v", "Landroid/view/View;", "refreshLanguage", "updateQuestionTitle", "questionTitle", "", "score", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class PressureQuestionFragment extends BaseFragment<BasePresenter<?>> implements View.OnClickListener {
    public static final int A = 1;
    public static final int B = 2;
    public static final int C = 3;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int D = 4;
    public static final String QUESTION_TITLE = "question_title";
    private HashMap _$_findViewCache;
    private PressureQuestionCallback mCallback;

    @JvmStatic
    public static final PressureQuestionFragment getInstance(String str) {
        return INSTANCE.getInstance(str);
    }

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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View viewFindViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected boolean canDoubleClick() {
        return true;
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_pressure_question_layout;
    }

    @Override // com.ido.life.base.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* JADX INFO: compiled from: PressureQuestionFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/ido/life/module/home/pressure/question/PressureQuestionFragment$Companion;", "", "()V", "A", "", FileSizeUtil.UNIT_BIT, "C", "D", "QUESTION_TITLE", "", "getInstance", "Lcom/ido/life/module/home/pressure/question/PressureQuestionFragment;", "questionTitle", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final PressureQuestionFragment getInstance(String questionTitle) {
            Intrinsics.checkParameterIsNotNull(questionTitle, "questionTitle");
            PressureQuestionFragment pressureQuestionFragment = new PressureQuestionFragment();
            Bundle bundle = new Bundle();
            bundle.putString(PressureQuestionFragment.QUESTION_TITLE, questionTitle);
            pressureQuestionFragment.setArguments(bundle);
            return pressureQuestionFragment;
        }
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void initData() {
        super.initData();
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            String string = arguments != null ? arguments.getString(QUESTION_TITLE, "") : null;
            String str = string;
            if (!(str == null || str.length() == 0)) {
                updateQuestionTitle(string, 0);
            }
        }
        if (getContext() instanceof PressureQuestionCallback) {
            Object context = getContext();
            if (context == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.ido.life.module.home.pressure.question.PressureQuestionCallback");
            }
            this.mCallback = (PressureQuestionCallback) context;
        }
        PressureQuestionFragment pressureQuestionFragment = this;
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_never)).setOnClickListener(pressureQuestionFragment);
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_sometime)).setOnClickListener(pressureQuestionFragment);
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_often)).setOnClickListener(pressureQuestionFragment);
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_always)).setOnClickListener(pressureQuestionFragment);
    }

    @Override // com.ido.life.base.BaseFragment
    protected void refreshLanguage() {
        super.refreshLanguage();
        TextView tv_answer_never = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_never);
        Intrinsics.checkExpressionValueIsNotNull(tv_answer_never, "tv_answer_never");
        tv_answer_never.setText(LanguageUtil.getLanguageText(R.string.anser_never));
        TextView tv_answer_sometime = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_sometime);
        Intrinsics.checkExpressionValueIsNotNull(tv_answer_sometime, "tv_answer_sometime");
        tv_answer_sometime.setText(LanguageUtil.getLanguageText(R.string.anser_sometime));
        TextView tv_answer_often = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_often);
        Intrinsics.checkExpressionValueIsNotNull(tv_answer_often, "tv_answer_often");
        tv_answer_often.setText(LanguageUtil.getLanguageText(R.string.anser_often));
        TextView tv_answer_always = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_always);
        Intrinsics.checkExpressionValueIsNotNull(tv_answer_always, "tv_answer_always");
        tv_answer_always.setText(LanguageUtil.getLanguageText(R.string.anser_always));
    }

    public final void updateQuestionTitle(String questionTitle, int score) {
        Intrinsics.checkParameterIsNotNull(questionTitle, "questionTitle");
        if (this.mActivity != null) {
            TextView tv_question = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_question);
            Intrinsics.checkExpressionValueIsNotNull(tv_question, "tv_question");
            Resources resources = getResources();
            BaseActivity mActivity = this.mActivity;
            Intrinsics.checkExpressionValueIsNotNull(mActivity, "mActivity");
            tv_question.setText(LanguageUtil.getLanguageText(resources.getIdentifier(questionTitle, "string", mActivity.getPackageName())));
            ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_never)).setBackgroundResource(R.drawable.pressure_answer_normal);
            ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_sometime)).setBackgroundResource(R.drawable.pressure_answer_normal);
            ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_often)).setBackgroundResource(R.drawable.pressure_answer_normal);
            ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_always)).setBackgroundResource(R.drawable.pressure_answer_normal);
            if (score == 1) {
                ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_never)).setBackgroundResource(R.drawable.pressure_answer_press);
                return;
            }
            if (score == 2) {
                ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_sometime)).setBackgroundResource(R.drawable.pressure_answer_press);
            } else if (score == 3) {
                ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_often)).setBackgroundResource(R.drawable.pressure_answer_press);
            } else {
                if (score != 4) {
                    return;
                }
                ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_always)).setBackgroundResource(R.drawable.pressure_answer_press);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_answer_never) {
            ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_never)).setBackgroundResource(R.drawable.pressure_answer_press);
            PressureQuestionCallback pressureQuestionCallback = this.mCallback;
            if (pressureQuestionCallback != null) {
                pressureQuestionCallback.selectAnswer(1);
                return;
            }
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_answer_sometime) {
            ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_sometime)).setBackgroundResource(R.drawable.pressure_answer_press);
            PressureQuestionCallback pressureQuestionCallback2 = this.mCallback;
            if (pressureQuestionCallback2 != null) {
                pressureQuestionCallback2.selectAnswer(2);
                return;
            }
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_answer_often) {
            ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_often)).setBackgroundResource(R.drawable.pressure_answer_press);
            PressureQuestionCallback pressureQuestionCallback3 = this.mCallback;
            if (pressureQuestionCallback3 != null) {
                pressureQuestionCallback3.selectAnswer(3);
                return;
            }
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_answer_always) {
            ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_answer_always)).setBackgroundResource(R.drawable.pressure_answer_press);
            PressureQuestionCallback pressureQuestionCallback4 = this.mCallback;
            if (pressureQuestionCallback4 != null) {
                pressureQuestionCallback4.selectAnswer(4);
            }
        }
    }
}