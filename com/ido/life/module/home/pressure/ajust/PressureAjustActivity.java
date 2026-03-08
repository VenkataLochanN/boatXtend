package com.ido.life.module.home.pressure.ajust;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import com.boat.Xtend.two.R;
import com.ido.ble.event.stat.one.d;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.PressureAjustProgressView;
import com.ido.life.enums.PressureEnum;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PressureAjustActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\fH\u0014J\b\u0010\u0012\u001a\u00020\nH\u0002J\b\u0010\u0013\u001a\u00020\u000eH\u0014J\b\u0010\u0014\u001a\u00020\u000eH\u0014J\b\u0010\u0015\u001a\u00020\u000eH\u0016J\u0012\u0010\u0016\u001a\u00020\u000e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fH\u0016R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/ido/life/module/home/pressure/ajust/PressureAjustActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/home/pressure/ajust/PressureAjustPresenter;", "Lcom/ido/life/module/home/pressure/ajust/PressureAjustView;", "Landroid/view/View$OnClickListener;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "mPressureDialog", "Lcom/ido/common/dialog/CommBottomConfirmDialog;", "mScore", "", "ajustFailed", "", "ajustSuccess", "pressure", "getLayoutResId", "getPressureExitDialog", "initData", "initLabelLanguage", "onBackPressed", "onClick", "v", "Landroid/view/View;", "updateAjustProgress", NotificationCompat.CATEGORY_PROGRESS, d.C, "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class PressureAjustActivity extends BaseActivity<PressureAjustPresenter> implements PressureAjustView, View.OnClickListener {
    public static final String SCORE = "score";
    private final String TAG = PressureAjustActivity.class.getSimpleName();
    private HashMap _$_findViewCache;
    private CommBottomConfirmDialog mPressureDialog;
    private int mScore;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[PressureEnum.values().length];

        static {
            $EnumSwitchMapping$0[PressureEnum.RELAX.ordinal()] = 1;
            $EnumSwitchMapping$0[PressureEnum.NORMAL.ordinal()] = 2;
            $EnumSwitchMapping$0[PressureEnum.HIGH.ordinal()] = 3;
        }
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
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_pressure_ajust_layout;
    }

    public static final /* synthetic */ PressureAjustPresenter access$getMPresenter$p(PressureAjustActivity pressureAjustActivity) {
        return (PressureAjustPresenter) pressureAjustActivity.mPresenter;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.pressure_ajust));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mScore = getIntent().getIntExtra("score", 0);
        CommonLogUtil.d(this.TAG, "score=" + this.mScore);
        ((PressureAjustPresenter) this.mPresenter).startAjust();
        ((PressureAjustProgressView) _$_findCachedViewById(com.ido.life.R.id.progress_bar)).setMMax(183);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_retry) {
            LinearLayout lay_success = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_success);
            Intrinsics.checkExpressionValueIsNotNull(lay_success, "lay_success");
            if (lay_success.getVisibility() == 0) {
                finish();
            } else {
                ((PressureAjustPresenter) this.mPresenter).startAjust();
            }
        }
    }

    @Override // com.ido.life.module.home.pressure.ajust.PressureAjustView
    public void ajustSuccess(int pressure) {
        CardView lay_testing = (CardView) _$_findCachedViewById(com.ido.life.R.id.lay_testing);
        Intrinsics.checkExpressionValueIsNotNull(lay_testing, "lay_testing");
        lay_testing.setVisibility(8);
        TextView tv_check_failed = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_check_failed);
        Intrinsics.checkExpressionValueIsNotNull(tv_check_failed, "tv_check_failed");
        tv_check_failed.setVisibility(8);
        LinearLayout lay_success = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_success);
        Intrinsics.checkExpressionValueIsNotNull(lay_success, "lay_success");
        lay_success.setVisibility(0);
        TextView tv_state = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_state);
        Intrinsics.checkExpressionValueIsNotNull(tv_state, "tv_state");
        tv_state.setVisibility(0);
        TextView tv_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_desc, "tv_desc");
        tv_desc.setVisibility(0);
        ((PressureAjustProgressView) _$_findCachedViewById(com.ido.life.R.id.progress_bar)).setMProgress(((PressureAjustProgressView) _$_findCachedViewById(com.ido.life.R.id.progress_bar)).getMMax());
        TextView tv_retry = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_retry);
        Intrinsics.checkExpressionValueIsNotNull(tv_retry, "tv_retry");
        tv_retry.setVisibility(0);
        TextView tv_retry2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_retry);
        Intrinsics.checkExpressionValueIsNotNull(tv_retry2, "tv_retry");
        tv_retry2.setText(getLanguageText(R.string.mine_complete));
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_retry)).setOnClickListener(this);
        TextView tv_current_value_title = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_current_value_title);
        Intrinsics.checkExpressionValueIsNotNull(tv_current_value_title, "tv_current_value_title");
        tv_current_value_title.setText(getLanguageText(R.string.current_pressure));
        TextView tv_current_value = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_current_value);
        Intrinsics.checkExpressionValueIsNotNull(tv_current_value, "tv_current_value");
        tv_current_value.setText(String.valueOf(pressure));
        PressureEnum pressureEnumByValue = PressureEnum.getPressureEnumByValue(pressure);
        if (pressureEnumByValue != null) {
            ((PressureAjustProgressView) _$_findCachedViewById(com.ido.life.R.id.progress_bar)).setMColorProgress(pressureEnumByValue.Color);
            ((PressureAjustProgressView) _$_findCachedViewById(com.ido.life.R.id.progress_bar)).invalidate();
            int i = WhenMappings.$EnumSwitchMapping$0[pressureEnumByValue.ordinal()];
            if (i == 1) {
                TextView tv_state2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_state);
                Intrinsics.checkExpressionValueIsNotNull(tv_state2, "tv_state");
                tv_state2.setText(getLanguageText(R.string.home_pressure_relax));
                TextView tv_desc2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_desc2, "tv_desc");
                tv_desc2.setText(getLanguageText(R.string.pressure_relax_state_desc));
                return;
            }
            if (i == 2) {
                TextView tv_state3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_state);
                Intrinsics.checkExpressionValueIsNotNull(tv_state3, "tv_state");
                tv_state3.setText(getLanguageText(R.string.home_pressure_normal));
                TextView tv_desc3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_desc3, "tv_desc");
                tv_desc3.setText(getLanguageText(R.string.pressure_normal_state_desc));
                return;
            }
            if (i == 3) {
                TextView tv_state4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_state);
                Intrinsics.checkExpressionValueIsNotNull(tv_state4, "tv_state");
                tv_state4.setText(getLanguageText(R.string.home_pressure_high));
                TextView tv_desc4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_desc4, "tv_desc");
                tv_desc4.setText(getLanguageText(R.string.pressure_high_state_desc));
                return;
            }
            TextView tv_state5 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_state);
            Intrinsics.checkExpressionValueIsNotNull(tv_state5, "tv_state");
            tv_state5.setText(getLanguageText(R.string.home_pressure_middle));
            TextView tv_desc5 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
            Intrinsics.checkExpressionValueIsNotNull(tv_desc5, "tv_desc");
            tv_desc5.setText(getLanguageText(R.string.pressure_medium_state_desc));
        }
    }

    @Override // com.ido.life.module.home.pressure.ajust.PressureAjustView
    public void updateAjustProgress(int progress, int duration) {
        TextView tv_retry = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_retry);
        Intrinsics.checkExpressionValueIsNotNull(tv_retry, "tv_retry");
        tv_retry.setVisibility(8);
        LinearLayout lay_success = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_success);
        Intrinsics.checkExpressionValueIsNotNull(lay_success, "lay_success");
        lay_success.setVisibility(8);
        TextView tv_check_failed = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_check_failed);
        Intrinsics.checkExpressionValueIsNotNull(tv_check_failed, "tv_check_failed");
        tv_check_failed.setVisibility(8);
        CardView lay_testing = (CardView) _$_findCachedViewById(com.ido.life.R.id.lay_testing);
        Intrinsics.checkExpressionValueIsNotNull(lay_testing, "lay_testing");
        lay_testing.setVisibility(0);
        TextView tv_state = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_state);
        Intrinsics.checkExpressionValueIsNotNull(tv_state, "tv_state");
        tv_state.setVisibility(0);
        TextView tv_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_desc, "tv_desc");
        tv_desc.setVisibility(0);
        TextView tv_state2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_state);
        Intrinsics.checkExpressionValueIsNotNull(tv_state2, "tv_state");
        tv_state2.setText(getLanguageText(R.string.pressure_testing));
        TextView tv_desc2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_desc2, "tv_desc");
        tv_desc2.setText(getLanguageText(R.string.pressure_testing_desc));
        ((PressureAjustProgressView) _$_findCachedViewById(com.ido.life.R.id.progress_bar)).setMProgress(progress);
        ((PressureAjustProgressView) _$_findCachedViewById(com.ido.life.R.id.progress_bar)).invalidate();
        TextView tv_duration = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_duration);
        Intrinsics.checkExpressionValueIsNotNull(tv_duration, "tv_duration");
        tv_duration.setText(String.valueOf(duration));
    }

    @Override // com.ido.life.module.home.pressure.ajust.PressureAjustView
    public void ajustFailed() {
        LinearLayout lay_success = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_success);
        Intrinsics.checkExpressionValueIsNotNull(lay_success, "lay_success");
        lay_success.setVisibility(8);
        CardView lay_testing = (CardView) _$_findCachedViewById(com.ido.life.R.id.lay_testing);
        Intrinsics.checkExpressionValueIsNotNull(lay_testing, "lay_testing");
        lay_testing.setVisibility(8);
        TextView tv_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_desc, "tv_desc");
        tv_desc.setVisibility(8);
        TextView tv_retry = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_retry);
        Intrinsics.checkExpressionValueIsNotNull(tv_retry, "tv_retry");
        tv_retry.setVisibility(0);
        TextView tv_retry2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_retry);
        Intrinsics.checkExpressionValueIsNotNull(tv_retry2, "tv_retry");
        tv_retry2.setText(getLanguageText(R.string.public_retry));
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_retry)).setOnClickListener(this);
        TextView tv_check_failed = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_check_failed);
        Intrinsics.checkExpressionValueIsNotNull(tv_check_failed, "tv_check_failed");
        tv_check_failed.setVisibility(0);
        ((PressureAjustProgressView) _$_findCachedViewById(com.ido.life.R.id.progress_bar)).setMProgress(((PressureAjustProgressView) _$_findCachedViewById(com.ido.life.R.id.progress_bar)).getMMax());
        ((PressureAjustProgressView) _$_findCachedViewById(com.ido.life.R.id.progress_bar)).invalidate();
        TextView tv_check_failed2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_check_failed);
        Intrinsics.checkExpressionValueIsNotNull(tv_check_failed2, "tv_check_failed");
        tv_check_failed2.setText(getLanguageText(R.string.pressure_check_failed));
        TextView tv_state = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_state);
        Intrinsics.checkExpressionValueIsNotNull(tv_state, "tv_state");
        tv_state.setText(getLanguageText(R.string.pressure_check_failed_desc));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        CommBottomConfirmDialog commBottomConfirmDialog;
        if (((PressureAjustPresenter) this.mPresenter).getMPressureAjusting()) {
            if (this.mPressureDialog == null) {
                this.mPressureDialog = getPressureExitDialog();
            }
            CommBottomConfirmDialog commBottomConfirmDialog2 = this.mPressureDialog;
            if (commBottomConfirmDialog2 == null) {
                Intrinsics.throwNpe();
            }
            if (commBottomConfirmDialog2.isVisible()) {
                return;
            }
            CommBottomConfirmDialog commBottomConfirmDialog3 = this.mPressureDialog;
            if (commBottomConfirmDialog3 == null) {
                Intrinsics.throwNpe();
            }
            if (commBottomConfirmDialog3.isAdded() || (commBottomConfirmDialog = this.mPressureDialog) == null) {
                return;
            }
            commBottomConfirmDialog.show(getSupportFragmentManager());
            return;
        }
        super.onBackPressed();
    }

    private final CommBottomConfirmDialog getPressureExitDialog() {
        CommBottomConfirmDialog dialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.tips), getLanguageText(R.string.pressure_ajust_exit_message), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true);
        dialog.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.home.pressure.ajust.PressureAjustActivity.getPressureExitDialog.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CommBottomConfirmDialog commBottomConfirmDialog = PressureAjustActivity.this.mPressureDialog;
                if (commBottomConfirmDialog != null) {
                    commBottomConfirmDialog.dismiss();
                }
                PressureAjustActivity.access$getMPresenter$p(PressureAjustActivity.this).stopAjust();
                PressureAjustActivity.this.supportFinishAfterTransition();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog");
        return dialog;
    }
}