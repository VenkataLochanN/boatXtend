package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes2.dex */
public class MsgEditActivity_ViewBinding implements Unbinder {
    private MsgEditActivity target;

    public MsgEditActivity_ViewBinding(MsgEditActivity msgEditActivity) {
        this(msgEditActivity, msgEditActivity.getWindow().getDecorView());
    }

    public MsgEditActivity_ViewBinding(MsgEditActivity msgEditActivity, View view) {
        this.target = msgEditActivity;
        msgEditActivity.mEtMsg = (EditText) Utils.findRequiredViewAsType(view, R.id.et_msg, "field 'mEtMsg'", EditText.class);
        msgEditActivity.mRtvMsgEditTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_msg_edit_tip, "field 'mRtvMsgEditTip'", RegularTextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MsgEditActivity msgEditActivity = this.target;
        if (msgEditActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        msgEditActivity.mEtMsg = null;
        msgEditActivity.mRtvMsgEditTip = null;
    }
}