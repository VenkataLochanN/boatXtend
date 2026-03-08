package com.ido.life.module.device.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class MotionRecognitionActivity_ViewBinding implements Unbinder {
    private MotionRecognitionActivity target;

    public MotionRecognitionActivity_ViewBinding(MotionRecognitionActivity motionRecognitionActivity) {
        this(motionRecognitionActivity, motionRecognitionActivity.getWindow().getDecorView());
    }

    public MotionRecognitionActivity_ViewBinding(MotionRecognitionActivity motionRecognitionActivity, View view) {
        this.target = motionRecognitionActivity;
        motionRecognitionActivity.mItemMotionIntelligentRecognition = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_motion_intelligent_recognition, "field 'mItemMotionIntelligentRecognition'", CustomItemLabelView.class);
        motionRecognitionActivity.mItemRun = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_run, "field 'mItemRun'", CustomItemLabelView.class);
        motionRecognitionActivity.mItemWalk = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_walk, "field 'mItemWalk'", CustomItemLabelView.class);
        motionRecognitionActivity.mRtvMotionIntelligentRecognitionTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_motion_intelligent_recognition_tip, "field 'mRtvMotionIntelligentRecognitionTip'", RegularTextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MotionRecognitionActivity motionRecognitionActivity = this.target;
        if (motionRecognitionActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        motionRecognitionActivity.mItemMotionIntelligentRecognition = null;
        motionRecognitionActivity.mItemRun = null;
        motionRecognitionActivity.mItemWalk = null;
        motionRecognitionActivity.mRtvMotionIntelligentRecognitionTip = null;
    }
}