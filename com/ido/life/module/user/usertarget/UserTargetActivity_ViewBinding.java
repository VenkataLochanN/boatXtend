package com.ido.life.module.user.usertarget;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSeekBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes3.dex */
public class UserTargetActivity_ViewBinding implements Unbinder {
    private UserTargetActivity target;
    private View view7f0a02ec;
    private View view7f0a031f;
    private View view7f0a0776;
    private View view7f0a097e;

    public UserTargetActivity_ViewBinding(UserTargetActivity userTargetActivity) {
        this(userTargetActivity, userTargetActivity.getWindow().getDecorView());
    }

    public UserTargetActivity_ViewBinding(final UserTargetActivity userTargetActivity, View view) {
        this.target = userTargetActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mTitleLeftBtn' and method 'back'");
        userTargetActivity.mTitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mTitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.usertarget.UserTargetActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                userTargetActivity.back(view2);
            }
        });
        userTargetActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        userTargetActivity.mRightBtn = (Button) Utils.findRequiredViewAsType(view, R.id.title_rightBtn, "field 'mRightBtn'", Button.class);
        userTargetActivity.mSeekBarStepNumber = (AppCompatSeekBar) Utils.findRequiredViewAsType(view, R.id.seekBar_step_number, "field 'mSeekBarStepNumber'", AppCompatSeekBar.class);
        userTargetActivity.mTvStep = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_step, "field 'mTvStep'", TextView.class);
        userTargetActivity.mTvWeight = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_weight, "field 'mTvWeight'", TextView.class);
        userTargetActivity.mSeekBarWeightGoal = (AppCompatSeekBar) Utils.findRequiredViewAsType(view, R.id.seekBar_weight_goal, "field 'mSeekBarWeightGoal'", AppCompatSeekBar.class);
        userTargetActivity.mTvStepsGoal = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_steps_goal, "field 'mTvStepsGoal'", TextView.class);
        userTargetActivity.mTvStepUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_step_unit, "field 'mTvStepUnit'", TextView.class);
        userTargetActivity.mTvSortTimeLast = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_time_last, "field 'mTvSortTimeLast'", TextView.class);
        userTargetActivity.mTvTimeMinute = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_time_minute, "field 'mTvTimeMinute'", TextView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_submit, "field 'mTvSubmit' and method 'toSubmit'");
        userTargetActivity.mTvSubmit = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_submit, "field 'mTvSubmit'", TextView.class);
        this.view7f0a097e = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.usertarget.UserTargetActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                userTargetActivity.toSubmit(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.iv_minus, "method 'minusWeightGoal'");
        this.view7f0a031f = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.usertarget.UserTargetActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                userTargetActivity.minusWeightGoal(view2);
            }
        });
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.iv_add, "method 'addWeightGoal'");
        this.view7f0a02ec = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.usertarget.UserTargetActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                userTargetActivity.addWeightGoal(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        UserTargetActivity userTargetActivity = this.target;
        if (userTargetActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        userTargetActivity.mTitleLeftBtn = null;
        userTargetActivity.mTitleText = null;
        userTargetActivity.mRightBtn = null;
        userTargetActivity.mSeekBarStepNumber = null;
        userTargetActivity.mTvStep = null;
        userTargetActivity.mTvWeight = null;
        userTargetActivity.mSeekBarWeightGoal = null;
        userTargetActivity.mTvStepsGoal = null;
        userTargetActivity.mTvStepUnit = null;
        userTargetActivity.mTvSortTimeLast = null;
        userTargetActivity.mTvTimeMinute = null;
        userTargetActivity.mTvSubmit = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a097e.setOnClickListener(null);
        this.view7f0a097e = null;
        this.view7f0a031f.setOnClickListener(null);
        this.view7f0a031f = null;
        this.view7f0a02ec.setOnClickListener(null);
        this.view7f0a02ec = null;
    }
}