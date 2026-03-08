package com.ido.life.dialog;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.LanguageUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: AmbientVolumeLevelInfoDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0015J\u0012\u0010\t\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\f\u001a\u00020\u0006H\u0016¨\u0006\r"}, d2 = {"Lcom/ido/life/dialog/AmbientVolumeLevelInfoDialog;", "Lcom/ido/common/base/BaseDialogFragment;", "()V", "getLayoutResId", "", "initUI", "", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "app_release"}, k = 1, mv = {1, 1, 16})
public final class AmbientVolumeLevelInfoDialog extends BaseDialogFragment {
    private HashMap _$_findViewCache;

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
        return R.layout.dialog_ambient_volume_level_info_layout;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, R.style.dialog_translate);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        super.initUI(view);
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_one);
        if (textView != null) {
            textView.setText("75 " + LanguageUtil.getLanguageText(R.string.public_volume_unit));
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_two);
        if (textView2 != null) {
            textView2.setText("80 " + LanguageUtil.getLanguageText(R.string.public_volume_unit));
        }
        TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_three);
        if (textView3 != null) {
            textView3.setText("90 " + LanguageUtil.getLanguageText(R.string.public_volume_unit));
        }
        TextView textView4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_four);
        if (textView4 != null) {
            textView4.setText("100 " + LanguageUtil.getLanguageText(R.string.public_volume_unit));
        }
        TextView textView5 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_five);
        if (textView5 != null) {
            textView5.setText("110 " + LanguageUtil.getLanguageText(R.string.public_volume_unit));
        }
        TextView textView6 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_value_sex);
        if (textView6 != null) {
            textView6.setText("120 " + LanguageUtil.getLanguageText(R.string.public_volume_unit));
        }
        TextView textView7 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_duration_one);
        if (textView7 != null) {
            textView7.setText("127" + LanguageUtil.getLanguageText(R.string.public_time_hour) + " / 7" + LanguageUtil.getLanguageText(R.string.fitness_detail_day));
        }
        TextView textView8 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_duration_two);
        if (textView8 != null) {
            textView8.setText("40" + LanguageUtil.getLanguageText(R.string.public_time_hour) + " / 7" + LanguageUtil.getLanguageText(R.string.fitness_detail_day));
        }
        TextView textView9 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_duration_three);
        if (textView9 != null) {
            textView9.setText('4' + LanguageUtil.getLanguageText(R.string.public_time_hour) + " / 7" + LanguageUtil.getLanguageText(R.string.fitness_detail_day));
        }
        TextView textView10 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_duration_four);
        if (textView10 != null) {
            textView10.setText("24" + LanguageUtil.getLanguageText(R.string.public_time_minute) + " / 7" + LanguageUtil.getLanguageText(R.string.fitness_detail_day));
        }
        TextView textView11 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_duration_five);
        if (textView11 != null) {
            textView11.setText('4' + LanguageUtil.getLanguageText(R.string.public_time_minute) + " / 7" + LanguageUtil.getLanguageText(R.string.fitness_detail_day));
        }
        TextView textView12 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_duration_sex);
        if (textView12 != null) {
            textView12.setText('1' + LanguageUtil.getLanguageText(R.string.public_time_minute) + " / 7" + LanguageUtil.getLanguageText(R.string.fitness_detail_day));
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_close);
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.dialog.AmbientVolumeLevelInfoDialog.initUI.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AmbientVolumeLevelInfoDialog.this.dismissAllowingStateLoss();
                }
            });
        }
    }

    @Override // com.ido.common.base.BaseDialogFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Dialog dialog = getDialog();
        if ((dialog != null ? dialog.getWindow() : null) != null) {
            Dialog dialog2 = getDialog();
            Window window = dialog2 != null ? dialog2.getWindow() : null;
            if (window == null) {
                Intrinsics.throwNpe();
            }
            Resources resources = getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
            window.setLayout(-1, MathKt.roundToInt(resources.getDisplayMetrics().heightPixels * 0.8f));
        }
    }
}