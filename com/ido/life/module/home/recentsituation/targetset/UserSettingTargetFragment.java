package com.ido.life.module.home.recentsituation.targetset;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.TargetNewSeekBar;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.module.home.fitness.FitnessHelperKt;
import com.ido.life.module.home.recentsituation.RecentSituationDetailActivity;
import com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter;
import com.ido.life.module.home.recentsituation.targetset.WalkStepSetDialogFragment;
import com.ido.life.util.RunTimeUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: UserSettingTargetFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u0000 \u001d2\u00020\u00012\u00020\u0002:\u0002\u001d\u001eB\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\rH\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\tH\u0014J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\tH\u0016J\b\u0010\u0019\u001a\u00020\u0014H\u0016J\u0010\u0010\u001a\u001a\u00020\u00142\b\u0010\u001b\u001a\u0004\u0018\u00010\u0005J\b\u0010\u001c\u001a\u00020\u0014H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/ido/life/module/home/recentsituation/targetset/UserSettingTargetFragment;", "Lcom/ido/common/base/BaseDialogFragment;", "Landroid/view/View$OnClickListener;", "()V", "mOnTargetEnsureListener", "Lcom/ido/life/module/home/recentsituation/targetset/UserSettingTargetFragment$OnTargetEnsureListener;", "mPresenter", "Lcom/ido/life/module/home/recentsituation/targetset/SettingTargetPresenter;", "addView", "Landroid/view/View;", "bean", "Lcom/ido/life/module/home/recentsituation/RecentSituationDetailPresenter$RecommandActiceBean;", "isEnd", "", "caluteMaxWidth", "", "checkCanSetTarget", "getLayoutResId", "", "initData", "", "initUI", "view", "onClick", "v", "onDestroy", "setOnTargetEnsureListener", "onTargetEnsureListener", "switchUIbyType", "Companion", "OnTargetEnsureListener", "app_release"}, k = 1, mv = {1, 1, 16})
public final class UserSettingTargetFragment extends BaseDialogFragment implements View.OnClickListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TARGET_SPORT_TYPE = "pageType";
    private HashMap _$_findViewCache;
    private OnTargetEnsureListener mOnTargetEnsureListener;
    private SettingTargetPresenter mPresenter = new SettingTargetPresenter(0, 1, null);

    /* JADX INFO: compiled from: UserSettingTargetFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H&¨\u0006\b"}, d2 = {"Lcom/ido/life/module/home/recentsituation/targetset/UserSettingTargetFragment$OnTargetEnsureListener;", "", "confirmValue", "", "targetType", "", "targetValue", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface OnTargetEnsureListener {
        void confirmValue(int targetType, List<Integer> targetValue);
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

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.fragment_setting_target_dialog;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        if (getDialog() != null) {
            Dialog dialog = getDialog();
            if (dialog == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog!!");
            if (dialog.getWindow() != null) {
                Dialog dialog2 = getDialog();
                if (dialog2 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(dialog2, "dialog!!");
                Window window = dialog2.getWindow();
                if (window == null) {
                    Intrinsics.throwNpe();
                }
                window.getDecorView().setPadding(getResources().getDimensionPixelSize(R.dimen.sw_dp_10), 0, getResources().getDimensionPixelSize(R.dimen.sw_dp_10), getResources().getDimensionPixelSize(R.dimen.sw_dp_10));
                WindowManager.LayoutParams attributes = window.getAttributes();
                if (attributes == null) {
                    Intrinsics.throwNpe();
                }
                attributes.gravity = 80;
                attributes.width = -1;
                window.setAttributes(attributes);
            }
        }
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title);
        if (textView != null) {
            textView.setText(LanguageUtil.getLanguageText(R.string.tircyclic_set_target));
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.contrast_active_tv);
        if (textView2 != null) {
            textView2.setText(LanguageUtil.getLanguageText(R.string.target_active_contrast));
        }
        TargetNewSeekBar targetNewSeekBar = (TargetNewSeekBar) _$_findCachedViewById(com.ido.life.R.id.target_seekbar);
        if (targetNewSeekBar != null) {
            targetNewSeekBar.setThumbIcon(R.mipmap.setting_target_progress);
        }
        TargetNewSeekBar targetNewSeekBar2 = (TargetNewSeekBar) _$_findCachedViewById(com.ido.life.R.id.target_seekbar);
        if (targetNewSeekBar2 != null) {
            targetNewSeekBar2.setRadius(getResources().getDimensionPixelSize(R.dimen.sw_dp_4));
        }
        ImageView imageView = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_close);
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }
        TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_confirm);
        if (textView3 != null) {
            textView3.setOnClickListener(this);
        }
        TextView textView4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_walk_step_num);
        if (textView4 != null) {
            textView4.setOnClickListener(this);
        }
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        SettingTargetPresenter settingTargetPresenter = this.mPresenter;
        Bundle arguments = getArguments();
        if (arguments == null) {
            Intrinsics.throwNpe();
        }
        settingTargetPresenter.setPageType(arguments.getInt(TARGET_SPORT_TYPE));
        TargetNewSeekBar targetNewSeekBar = (TargetNewSeekBar) _$_findCachedViewById(com.ido.life.R.id.target_seekbar);
        ViewGroup.LayoutParams layoutParams = targetNewSeekBar != null ? targetNewSeekBar.getLayoutParams() : null;
        if (layoutParams == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        }
        final LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
        float fCaluteMaxWidth = caluteMaxWidth();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sw_dp_24);
        float f2 = fCaluteMaxWidth / 2;
        layoutParams2.leftMargin = Math.max(MathKt.roundToInt(f2), dimensionPixelSize);
        layoutParams2.rightMargin = Math.max(MathKt.roundToInt(f2), dimensionPixelSize);
        TargetNewSeekBar targetNewSeekBar2 = (TargetNewSeekBar) _$_findCachedViewById(com.ido.life.R.id.target_seekbar);
        if (targetNewSeekBar2 != null) {
            targetNewSeekBar2.setLayoutParams(layoutParams2);
        }
        this.mPresenter.initData();
        switchUIbyType();
        TargetNewSeekBar targetNewSeekBar3 = (TargetNewSeekBar) _$_findCachedViewById(com.ido.life.R.id.target_seekbar);
        if (targetNewSeekBar3 != null) {
            targetNewSeekBar3.setPropertyList(this.mPresenter.getPropertyList());
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_seek_bar);
        ViewGroup.LayoutParams layoutParams3 = linearLayout != null ? linearLayout.getLayoutParams() : null;
        if (layoutParams3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        }
        final LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) layoutParams3;
        TargetNewSeekBar targetNewSeekBar4 = (TargetNewSeekBar) _$_findCachedViewById(com.ido.life.R.id.target_seekbar);
        if (targetNewSeekBar4 != null) {
            targetNewSeekBar4.setMListener(new TargetNewSeekBar.TouchListenter() { // from class: com.ido.life.module.home.recentsituation.targetset.UserSettingTargetFragment.initData.1
                @Override // com.ido.life.customview.TargetNewSeekBar.TouchListenter
                public void onProgressChanged(int progress, float centerX, boolean touching) {
                    String str;
                    int iCaluteTargetValue = UserSettingTargetFragment.this.mPresenter.caluteTargetValue(progress);
                    String descLabel = UserSettingTargetFragment.this.mPresenter.getDescLabel();
                    TextView textView = (TextView) UserSettingTargetFragment.this._$_findCachedViewById(com.ido.life.R.id.tv_seek_bar_indicator);
                    if (textView != null) {
                        textView.setText(descLabel);
                    }
                    LinearLayout linearLayout2 = (LinearLayout) UserSettingTargetFragment.this._$_findCachedViewById(com.ido.life.R.id.lay_seek_bar);
                    if (linearLayout2 != null) {
                        linearLayout2.measure(0, 0);
                    }
                    LinearLayout.LayoutParams layoutParams5 = layoutParams4;
                    float f3 = layoutParams2.leftMargin + centerX;
                    LinearLayout lay_seek_bar = (LinearLayout) UserSettingTargetFragment.this._$_findCachedViewById(com.ido.life.R.id.lay_seek_bar);
                    Intrinsics.checkExpressionValueIsNotNull(lay_seek_bar, "lay_seek_bar");
                    layoutParams5.leftMargin = MathKt.roundToInt(f3 - (lay_seek_bar.getMeasuredWidth() / 2.0f));
                    LinearLayout lay_seek_bar2 = (LinearLayout) UserSettingTargetFragment.this._$_findCachedViewById(com.ido.life.R.id.lay_seek_bar);
                    Intrinsics.checkExpressionValueIsNotNull(lay_seek_bar2, "lay_seek_bar");
                    lay_seek_bar2.setLayoutParams(layoutParams4);
                    int pageType = UserSettingTargetFragment.this.mPresenter.getPageType();
                    if (pageType == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_CALORIE()) {
                        MediumTextView tv_value = (MediumTextView) UserSettingTargetFragment.this._$_findCachedViewById(com.ido.life.R.id.tv_value);
                        Intrinsics.checkExpressionValueIsNotNull(tv_value, "tv_value");
                        tv_value.setText(String.valueOf(iCaluteTargetValue));
                        String string = "";
                        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
                        int iCalculateWalkDuration = FitnessHelperKt.calculateWalkDuration(runTimeUtil.getUserId(), iCaluteTargetValue);
                        int i = iCalculateWalkDuration / 60;
                        int i2 = iCalculateWalkDuration % 60;
                        if (i > 0) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("");
                            if (i > 1) {
                                str = String.valueOf(i) + LanguageUtil.getLanguageText(R.string.public_unit_hrs);
                            } else {
                                str = String.valueOf(i) + LanguageUtil.getLanguageText(R.string.public_unit_hr);
                            }
                            sb.append(str);
                            string = sb.toString();
                        }
                        if (i2 > 0) {
                            string = string + String.valueOf(i2) + LanguageUtil.getLanguageText(R.string.public_time_minute);
                        }
                        TextView textView2 = (TextView) UserSettingTargetFragment.this._$_findCachedViewById(com.ido.life.R.id.tv_value_desc);
                        if (textView2 != null) {
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            String languageText = LanguageUtil.getLanguageText(R.string.equivalent_to_walk_time);
                            Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage….equivalent_to_walk_time)");
                            Object[] objArr = {string};
                            String str2 = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
                            Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
                            textView2.setText(str2);
                        }
                        if (touching) {
                            return;
                        }
                        LinearLayout linearLayout3 = (LinearLayout) UserSettingTargetFragment.this._$_findCachedViewById(com.ido.life.R.id.lay_recommend);
                        if (linearLayout3 != null) {
                            linearLayout3.removeAllViews();
                        }
                        RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
                        List<RecentSituationDetailPresenter.RecommandActiceBean> listCalculateRecommendActiveList = FitnessHelperKt.calculateRecommendActiveList(runTimeUtil2.getUserId(), iCaluteTargetValue);
                        int size = listCalculateRecommendActiveList.size();
                        int i3 = 0;
                        while (i3 < size) {
                            LinearLayout linearLayout4 = (LinearLayout) UserSettingTargetFragment.this._$_findCachedViewById(com.ido.life.R.id.lay_recommend);
                            if (linearLayout4 != null) {
                                linearLayout4.addView(UserSettingTargetFragment.this.addView(listCalculateRecommendActiveList.get(i3), i3 == listCalculateRecommendActiveList.size() - 1));
                            }
                            i3++;
                        }
                        return;
                    }
                    if (pageType == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_TIME()) {
                        MediumTextView mediumTextView = (MediumTextView) UserSettingTargetFragment.this._$_findCachedViewById(com.ido.life.R.id.tv_value);
                        if (mediumTextView != null) {
                            mediumTextView.setText(String.valueOf(iCaluteTargetValue));
                            return;
                        }
                        return;
                    }
                    MediumTextView mediumTextView2 = (MediumTextView) UserSettingTargetFragment.this._$_findCachedViewById(com.ido.life.R.id.tv_value);
                    if (mediumTextView2 != null) {
                        mediumTextView2.setText(String.valueOf(iCaluteTargetValue));
                    }
                }
            });
        }
    }

    private final void switchUIbyType() {
        int pageType = this.mPresenter.getPageType();
        if (pageType == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_CALORIE()) {
            TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_desc);
            if (textView != null) {
                textView.setText(LanguageUtil.getLanguageText(R.string.average_daily_active_calorie));
            }
            ImageView imageView = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_type);
            if (imageView != null) {
                imageView.setBackgroundResource(R.mipmap.target_calorie);
            }
            int color = getResources().getColor(R.color.color_FF4826);
            MediumTextView mediumTextView = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_value);
            if (mediumTextView != null) {
                mediumTextView.setTextColor(color);
            }
            TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_unit);
            if (textView2 != null) {
                textView2.setTextColor(color);
            }
            TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_unit);
            if (textView3 != null) {
                textView3.setText(RunTimeUtil.getCalorieUnit());
            }
            TextView textView4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_desc);
            if (textView4 != null) {
                textView4.setVisibility(0);
            }
            TextView textView5 = (TextView) _$_findCachedViewById(com.ido.life.R.id.contrast_active_tv);
            if (textView5 != null) {
                textView5.setVisibility(0);
            }
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_recommend);
            if (linearLayout != null) {
                linearLayout.setVisibility(0);
            }
            TextView textView6 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_walk_step_num);
            if (textView6 != null) {
                textView6.setVisibility(8);
            }
            LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_recommend);
            if (linearLayout2 != null) {
                linearLayout2.removeAllViews();
            }
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            List<RecentSituationDetailPresenter.RecommandActiceBean> listCalculateRecommendActiveList = FitnessHelperKt.calculateRecommendActiveList(runTimeUtil.getUserId(), this.mPresenter.getMTargetValue());
            int size = listCalculateRecommendActiveList.size();
            int i = 0;
            while (i < size) {
                LinearLayout linearLayout3 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_recommend);
                if (linearLayout3 != null) {
                    linearLayout3.addView(addView(listCalculateRecommendActiveList.get(i), i == listCalculateRecommendActiveList.size() - 1));
                }
                i++;
            }
            TextView textView7 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_desc);
            if (textView7 != null) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String languageText = LanguageUtil.getLanguageText(R.string.equivalent_to_walkin);
                Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…ing.equivalent_to_walkin)");
                RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
                Object[] objArr = {Integer.valueOf(FitnessHelperKt.calculateWalkDuration(runTimeUtil2.getUserId(), this.mPresenter.getMTargetValue()))};
                String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                textView7.setText(str);
            }
        } else if (pageType == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_TIME()) {
            TextView textView8 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_desc);
            if (textView8 != null) {
                textView8.setText(LanguageUtil.getLanguageText(R.string.average_daily_active_time));
            }
            ImageView imageView2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_type);
            if (imageView2 != null) {
                imageView2.setBackgroundResource(R.mipmap.target_exercise);
            }
            int color2 = getResources().getColor(R.color.color_1ACE5E);
            MediumTextView mediumTextView2 = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_value);
            if (mediumTextView2 != null) {
                mediumTextView2.setTextColor(color2);
            }
            TextView textView9 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_unit);
            if (textView9 != null) {
                textView9.setTextColor(color2);
            }
            TextView textView10 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_unit);
            if (textView10 != null) {
                textView10.setText(LanguageUtil.getLanguageText(R.string.public_time_minute));
            }
            TextView textView11 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_desc);
            if (textView11 != null) {
                textView11.setVisibility(8);
            }
            TextView textView12 = (TextView) _$_findCachedViewById(com.ido.life.R.id.contrast_active_tv);
            if (textView12 != null) {
                textView12.setVisibility(0);
            }
            LinearLayout linearLayout4 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_recommend);
            if (linearLayout4 != null) {
                linearLayout4.setVisibility(0);
            }
            TextView textView13 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_walk_step_num);
            if (textView13 != null) {
                textView13.setVisibility(8);
            }
            List<RecentSituationDetailPresenter.RecommandActiceBean> activeTimeRecommandActiveList = FitnessHelperKt.getActiveTimeRecommandActiveList();
            LinearLayout linearLayout5 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_recommend);
            if (linearLayout5 != null) {
                linearLayout5.removeAllViews();
            }
            int size2 = activeTimeRecommandActiveList.size();
            int i2 = 0;
            while (i2 < size2) {
                LinearLayout linearLayout6 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_recommend);
                if (linearLayout6 != null) {
                    linearLayout6.addView(addView(activeTimeRecommandActiveList.get(i2), i2 == activeTimeRecommandActiveList.size() - 1));
                }
                i2++;
            }
        } else {
            TextView textView14 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_desc);
            if (textView14 != null) {
                textView14.setText(LanguageUtil.getLanguageText(R.string.average_daily_walk));
            }
            ImageView imageView3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_type);
            if (imageView3 != null) {
                imageView3.setBackgroundResource(R.mipmap.target_walk);
            }
            int color3 = getResources().getColor(R.color.color_1AA5F0);
            MediumTextView mediumTextView3 = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_value);
            if (mediumTextView3 != null) {
                mediumTextView3.setTextColor(color3);
            }
            TextView textView15 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_unit);
            if (textView15 != null) {
                textView15.setTextColor(color3);
            }
            TextView textView16 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_unit);
            if (textView16 != null) {
                textView16.setText(LanguageUtil.getLanguageText(R.string.public_unit_hrs));
            }
            TextView textView17 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_desc);
            if (textView17 != null) {
                textView17.setVisibility(8);
            }
            TextView textView18 = (TextView) _$_findCachedViewById(com.ido.life.R.id.contrast_active_tv);
            if (textView18 != null) {
                textView18.setVisibility(8);
            }
            LinearLayout linearLayout7 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_recommend);
            if (linearLayout7 != null) {
                linearLayout7.setVisibility(8);
            }
            TextView textView19 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_walk_step_num);
            if (textView19 != null) {
                textView19.setVisibility(0);
            }
            TextView textView20 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_walk_step_num);
            if (textView20 != null) {
                textView20.setText(LanguageUtil.getLanguageText(R.string.user_target_walk_hour) + ": " + this.mPresenter.getMUserTarget().getSportStep());
            }
        }
        TargetNewSeekBar targetNewSeekBar = (TargetNewSeekBar) _$_findCachedViewById(com.ido.life.R.id.target_seekbar);
        if (targetNewSeekBar != null) {
            targetNewSeekBar.setProgress(this.mPresenter.caluteProgress());
        }
    }

    public final View addView(RecentSituationDetailPresenter.RecommandActiceBean bean, boolean isEnd) {
        Intrinsics.checkParameterIsNotNull(bean, "bean");
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_target_sport_refer_active, (ViewGroup) null);
        ImageView imageView = (ImageView) view.findViewById(R.id.sport_type_img);
        LinearLayout sportTypeLl = (LinearLayout) view.findViewById(R.id.sport_type_ll);
        TextView sportTypeTv = (TextView) view.findViewById(R.id.sport_type_tv);
        TextView sportTypeSpeed = (TextView) view.findViewById(R.id.sport_type_speed);
        TextView sportTypeUnit = (TextView) view.findViewById(R.id.sport_type_unit);
        Intrinsics.checkExpressionValueIsNotNull(sportTypeLl, "sportTypeLl");
        ViewGroup.LayoutParams layoutParams = sportTypeLl.getLayoutParams();
        if (layoutParams == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        }
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        int dimensionPixelSize = (resources.getDisplayMetrics().widthPixels - getResources().getDimensionPixelSize(R.dimen.sw_dp_100)) / (this.mPresenter.calorieType() ? 4 : 5);
        layoutParams2.rightMargin = isEnd ? 0 : DipPixelUtil.dip2px(10.0f);
        layoutParams2.width = dimensionPixelSize;
        sportTypeLl.setLayoutParams(layoutParams2);
        imageView.setImageResource(bean.getIconResId());
        Intrinsics.checkExpressionValueIsNotNull(sportTypeTv, "sportTypeTv");
        sportTypeTv.setText(bean.getName());
        if (this.mPresenter.calorieType()) {
            Intrinsics.checkExpressionValueIsNotNull(sportTypeSpeed, "sportTypeSpeed");
            sportTypeSpeed.setText(String.valueOf(bean.getKcal()));
            Intrinsics.checkExpressionValueIsNotNull(sportTypeUnit, "sportTypeUnit");
            sportTypeUnit.setText(bean.getValueDesc());
        } else {
            Intrinsics.checkExpressionValueIsNotNull(sportTypeSpeed, "sportTypeSpeed");
            sportTypeSpeed.setVisibility(8);
            Intrinsics.checkExpressionValueIsNotNull(sportTypeUnit, "sportTypeUnit");
            sportTypeUnit.setVisibility(8);
        }
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        return view;
    }

    private final boolean checkCanSetTarget() {
        if (BLEManager.isBind()) {
            if (BLEManager.isConnected()) {
                if (!HomeFragmentPresenter.mIsSyncing) {
                    return true;
                }
                NormalToast.showToast(LanguageUtil.getLanguageText(R.string.set_target_syncing));
            } else {
                NormalToast.showToast(LanguageUtil.getLanguageText(R.string.set_target_unconnect_device));
            }
        } else {
            NormalToast.showToast(LanguageUtil.getLanguageText(R.string.set_target_unbind_device));
        }
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        int id = v.getId();
        if (id == R.id.img_close) {
            dismiss();
            return;
        }
        if (id != R.id.tv_confirm) {
            if (id != R.id.tv_walk_step_num) {
                return;
            }
            WalkStepSetDialogFragment companion = WalkStepSetDialogFragment.INSTANCE.getInstance((this.mPresenter.getMUserTarget().getSportStep() - 75) / 25);
            companion.setMListener(new WalkStepSetDialogFragment.ConfirmListener() { // from class: com.ido.life.module.home.recentsituation.targetset.UserSettingTargetFragment.onClick.1
                @Override // com.ido.life.module.home.recentsituation.targetset.WalkStepSetDialogFragment.ConfirmListener
                public void actionSuccess(String value) {
                    int iIntValue;
                    Intrinsics.checkParameterIsNotNull(value, "value");
                    try {
                        Integer numValueOf = Integer.valueOf(value);
                        Intrinsics.checkExpressionValueIsNotNull(numValueOf, "Integer.valueOf(value)");
                        iIntValue = numValueOf.intValue();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        iIntValue = 100;
                    }
                    UserSettingTargetFragment.this.mPresenter.getMUserTarget().setSportStep(iIntValue);
                    TextView textView = (TextView) UserSettingTargetFragment.this._$_findCachedViewById(com.ido.life.R.id.tv_walk_step_num);
                    if (textView != null) {
                        textView.setText(LanguageUtil.getLanguageText(R.string.user_target_walk_hour) + ": " + iIntValue);
                    }
                }
            });
            companion.show(getFragmentManager());
            return;
        }
        if (this.mOnTargetEnsureListener != null && checkCanSetTarget()) {
            OnTargetEnsureListener onTargetEnsureListener = this.mOnTargetEnsureListener;
            if (onTargetEnsureListener != null) {
                onTargetEnsureListener.confirmValue(this.mPresenter.getPageType(), CollectionsKt.mutableListOf(Integer.valueOf(this.mPresenter.getMTargetValue()), Integer.valueOf(this.mPresenter.getMUserTarget().getSportStep())));
            }
            dismiss();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.detachView();
    }

    private final float caluteMaxWidth() {
        List listMutableListOf = CollectionsKt.mutableListOf(LanguageUtil.getLanguageText(R.string.fitness_detail_less), LanguageUtil.getLanguageText(R.string.fitness_detail_medium), LanguageUtil.getLanguageText(R.string.fitness_detail_much), LanguageUtil.getLanguageText(R.string.fitness_detail_more));
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        TextView tv_seek_bar_indicator = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_seek_bar_indicator);
        Intrinsics.checkExpressionValueIsNotNull(tv_seek_bar_indicator, "tv_seek_bar_indicator");
        paint.setTextSize(tv_seek_bar_indicator.getTextSize());
        Iterator it = listMutableListOf.iterator();
        float fMax = 0.0f;
        while (it.hasNext()) {
            fMax = Math.max(fMax, paint.measureText((String) it.next()));
        }
        return fMax;
    }

    public final void setOnTargetEnsureListener(OnTargetEnsureListener onTargetEnsureListener) {
        this.mOnTargetEnsureListener = onTargetEnsureListener;
    }

    /* JADX INFO: compiled from: UserSettingTargetFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/ido/life/module/home/recentsituation/targetset/UserSettingTargetFragment$Companion;", "", "()V", "TARGET_SPORT_TYPE", "", "newInstance", "Lcom/ido/life/module/home/recentsituation/targetset/UserSettingTargetFragment;", "sportType", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final UserSettingTargetFragment newInstance(int sportType) {
            Bundle bundle = new Bundle();
            bundle.putInt(UserSettingTargetFragment.TARGET_SPORT_TYPE, sportType);
            UserSettingTargetFragment userSettingTargetFragment = new UserSettingTargetFragment();
            userSettingTargetFragment.setArguments(bundle);
            userSettingTargetFragment.setStyle(1, 2131886083);
            return userSettingTargetFragment;
        }
    }
}