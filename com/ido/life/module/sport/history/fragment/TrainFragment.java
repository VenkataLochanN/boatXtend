package com.ido.life.module.sport.history.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseFragment;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.customview.TrainingEffectProgressView;
import com.ido.life.database.model.SportHealth;
import com.ido.life.module.device.activity.CommonWebViewActivity;
import java.io.Serializable;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrainFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0011\u0018\u0000 '2\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u00032\u00020\u0004:\u0001'B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\tH\u0014J\u0012\u0010\u000b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0007H\u0016J\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u0017H\u0016J\u0018\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0007H\u0016J\u0010\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u0017H\u0016J\u0010\u0010\u001e\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\u0010\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u0017H\u0016J\u0010\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\u0017H\u0016J\u0010\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020\u0017H\u0016J\u0010\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\u0017H\u0016¨\u0006("}, d2 = {"Lcom/ido/life/module/sport/history/fragment/TrainFragment;", "Lcom/ido/life/base/BaseFragment;", "Lcom/ido/life/module/sport/history/fragment/TrainFragmentPresenter;", "Lcom/ido/life/module/sport/history/fragment/ITrainView;", "Landroid/view/View$OnClickListener;", "()V", "getLayoutResId", "", "initData", "", "initView", "onClick", "v", "Landroid/view/View;", "setOxygenVisible", "visible", "", "showEffectColor", "color", "showEffectProgress", "position", "showEffectText", "effect", "", "showEffectTip", "effectTip", "showOxygenProgress", "type", "showOxygenText", "oxygen", "showOxygenTextColor", "showOxygenTip", "oxygenTip", "showResumeDate", "dataStr", "showResumeTime", "resumeTime", "showResumeTimeTip", "tip", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class TrainFragment extends BaseFragment<TrainFragmentPresenter> implements ITrainView, View.OnClickListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String EXTRA_SPORT = "extra_sport";
    private HashMap _$_findViewCache;

    @JvmStatic
    public static final TrainFragment newInstance(SportHealth sportHealth) {
        return INSTANCE.newInstance(sportHealth);
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
    protected int getLayoutResId() {
        return R.layout.fragment_train;
    }

    @Override // com.ido.life.base.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        Serializable serializable = arguments != null ? arguments.getSerializable(EXTRA_SPORT) : null;
        if (serializable == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ido.life.database.model.SportHealth");
        }
        SportHealth sportHealth = (SportHealth) serializable;
        TrainFragmentPresenter trainFragmentPresenter = (TrainFragmentPresenter) this.mPresenter;
        if (trainFragmentPresenter != null) {
            trainFragmentPresenter.actionSportHealthData(sportHealth);
        }
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_about)).setOnClickListener(this);
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void initView() {
        super.initView();
    }

    /* JADX INFO: compiled from: TrainFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/ido/life/module/sport/history/fragment/TrainFragment$Companion;", "", "()V", "EXTRA_SPORT", "", "newInstance", "Lcom/ido/life/module/sport/history/fragment/TrainFragment;", "sportHealth", "Lcom/ido/life/database/model/SportHealth;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final TrainFragment newInstance(SportHealth sportHealth) {
            Intrinsics.checkParameterIsNotNull(sportHealth, "sportHealth");
            TrainFragment trainFragment = new TrainFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(TrainFragment.EXTRA_SPORT, sportHealth);
            trainFragment.setArguments(bundle);
            return trainFragment;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.iv_about) {
            SingleTopIntent singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) CommonWebViewActivity.class);
            singleTopIntent.putExtra("type", 18);
            startActivity(singleTopIntent);
        }
    }

    @Override // com.ido.life.module.sport.history.fragment.ITrainView
    public void showEffectProgress(int position) {
        TrainingEffectProgressView trainingEffectProgressView = (TrainingEffectProgressView) _$_findCachedViewById(com.ido.life.R.id.effect_progress);
        if (trainingEffectProgressView != null) {
            trainingEffectProgressView.setCurrProgress(position - 2);
        }
        TrainingEffectProgressView trainingEffectProgressView2 = (TrainingEffectProgressView) _$_findCachedViewById(com.ido.life.R.id.effect_progress);
        if (trainingEffectProgressView2 != null) {
            P p = this.mPresenter;
            if (p == 0) {
                Intrinsics.throwNpe();
            }
            trainingEffectProgressView2.setMPropertyList(((TrainFragmentPresenter) p).getEffectPorgressList());
        }
        RegularTextView regularTextView = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.rtv_effect_type_name);
        if (regularTextView != null) {
            P p2 = this.mPresenter;
            if (p2 == 0) {
                Intrinsics.throwNpe();
            }
            regularTextView.setText(((TrainFragmentPresenter) p2).getEffectList().get(position));
        }
    }

    @Override // com.ido.life.module.sport.history.fragment.ITrainView
    public void showEffectText(String effect) {
        Intrinsics.checkParameterIsNotNull(effect, "effect");
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.rtv_effect_score);
        if (textView != null) {
            textView.setText(effect);
        }
    }

    @Override // com.ido.life.module.sport.history.fragment.ITrainView
    public void showEffectColor(int color) {
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.rtv_effect_score)).setTextColor(color);
    }

    @Override // com.ido.life.module.sport.history.fragment.ITrainView
    public void showOxygenProgress(int type, int position) {
        TrainingEffectProgressView trainingEffectProgressView = (TrainingEffectProgressView) _$_findCachedViewById(com.ido.life.R.id.oxygen_progress);
        if (trainingEffectProgressView != null) {
            trainingEffectProgressView.setCurrProgress(position);
        }
        TrainingEffectProgressView trainingEffectProgressView2 = (TrainingEffectProgressView) _$_findCachedViewById(com.ido.life.R.id.oxygen_progress);
        if (trainingEffectProgressView2 != null) {
            P p = this.mPresenter;
            if (p == 0) {
                Intrinsics.throwNpe();
            }
            trainingEffectProgressView2.setMPropertyList(((TrainFragmentPresenter) p).getOxygenList());
        }
    }

    @Override // com.ido.life.module.sport.history.fragment.ITrainView
    public void showOxygenText(String oxygen) {
        Intrinsics.checkParameterIsNotNull(oxygen, "oxygen");
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.rtv_Oxygen_score);
        if (textView != null) {
            textView.setText(oxygen);
        }
    }

    @Override // com.ido.life.module.sport.history.fragment.ITrainView
    public void showOxygenTextColor(int color) {
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.rtv_Oxygen_score)).setTextColor(color);
    }

    @Override // com.ido.life.module.sport.history.fragment.ITrainView
    public void showEffectTip(String effectTip) {
        Intrinsics.checkParameterIsNotNull(effectTip, "effectTip");
        RegularTextView regularTextView = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.rtv_effect_tip);
        if (regularTextView != null) {
            regularTextView.setText(effectTip);
        }
    }

    @Override // com.ido.life.module.sport.history.fragment.ITrainView
    public void showOxygenTip(String oxygenTip) {
        Intrinsics.checkParameterIsNotNull(oxygenTip, "oxygenTip");
        RegularTextView regularTextView = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.rtv_Oxygen_tip);
        if (regularTextView != null) {
            regularTextView.setText(oxygenTip);
        }
    }

    @Override // com.ido.life.module.sport.history.fragment.ITrainView
    public void setOxygenVisible(boolean visible) {
        if (visible) {
            View view_oxygen_line = _$_findCachedViewById(com.ido.life.R.id.view_oxygen_line);
            Intrinsics.checkExpressionValueIsNotNull(view_oxygen_line, "view_oxygen_line");
            view_oxygen_line.setVisibility(0);
            LinearLayout ll_oxygen = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_oxygen);
            Intrinsics.checkExpressionValueIsNotNull(ll_oxygen, "ll_oxygen");
            ll_oxygen.setVisibility(0);
            return;
        }
        View view_oxygen_line2 = _$_findCachedViewById(com.ido.life.R.id.view_oxygen_line);
        Intrinsics.checkExpressionValueIsNotNull(view_oxygen_line2, "view_oxygen_line");
        view_oxygen_line2.setVisibility(8);
        LinearLayout ll_oxygen2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_oxygen);
        Intrinsics.checkExpressionValueIsNotNull(ll_oxygen2, "ll_oxygen");
        ll_oxygen2.setVisibility(8);
    }

    @Override // com.ido.life.module.sport.history.fragment.ITrainView
    public void showResumeTime(String resumeTime) {
        Intrinsics.checkParameterIsNotNull(resumeTime, "resumeTime");
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.rtv_resume_time);
        if (textView != null) {
            textView.setText(resumeTime);
        }
    }

    @Override // com.ido.life.module.sport.history.fragment.ITrainView
    public void showResumeDate(String dataStr) {
        Intrinsics.checkParameterIsNotNull(dataStr, "dataStr");
        RegularTextView regularTextView = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.rtv_discovertime);
        if (regularTextView != null) {
            regularTextView.setText(dataStr);
        }
    }

    @Override // com.ido.life.module.sport.history.fragment.ITrainView
    public void showResumeTimeTip(String tip) {
        Intrinsics.checkParameterIsNotNull(tip, "tip");
        RegularTextView regularTextView = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.rtv_resume_tip);
        if (regularTextView != null) {
            regularTextView.setText(tip);
        }
    }
}