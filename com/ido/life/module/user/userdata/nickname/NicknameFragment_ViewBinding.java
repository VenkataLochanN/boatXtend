package com.ido.life.module.user.userdata.nickname;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes3.dex */
public class NicknameFragment_ViewBinding implements Unbinder {
    private NicknameFragment target;
    private View view7f0a0311;
    private View view7f0a032c;

    public NicknameFragment_ViewBinding(final NicknameFragment nicknameFragment, View view) {
        this.target = nicknameFragment;
        nicknameFragment.mTvTitleNickname = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_nickname, "field 'mTvTitleNickname'", TextView.class);
        nicknameFragment.mTvNicknameTip = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_nickname_tip, "field 'mTvNicknameTip'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.iv_forward, "field 'mIvForward' and method 'toUpdateNickname'");
        nicknameFragment.mIvForward = (ImageButton) Utils.castView(viewFindRequiredView, R.id.iv_forward, "field 'mIvForward'", ImageButton.class);
        this.view7f0a0311 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.userdata.nickname.NicknameFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                nicknameFragment.toUpdateNickname(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.iv_register_avatar, "field 'mIvAvatar' and method 'toUpdateAvatar'");
        nicknameFragment.mIvAvatar = (ImageView) Utils.castView(viewFindRequiredView2, R.id.iv_register_avatar, "field 'mIvAvatar'", ImageView.class);
        this.view7f0a032c = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.userdata.nickname.NicknameFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                nicknameFragment.toUpdateAvatar(view2);
            }
        });
        nicknameFragment.mEtNickname = (EditText) Utils.findRequiredViewAsType(view, R.id.et_nickname, "field 'mEtNickname'", EditText.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        NicknameFragment nicknameFragment = this.target;
        if (nicknameFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        nicknameFragment.mTvTitleNickname = null;
        nicknameFragment.mTvNicknameTip = null;
        nicknameFragment.mIvForward = null;
        nicknameFragment.mIvAvatar = null;
        nicknameFragment.mEtNickname = null;
        this.view7f0a0311.setOnClickListener(null);
        this.view7f0a0311 = null;
        this.view7f0a032c.setOnClickListener(null);
        this.view7f0a032c = null;
    }
}