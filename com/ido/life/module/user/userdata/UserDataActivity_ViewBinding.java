package com.ido.life.module.user.userdata;

import android.view.View;
import android.widget.ImageButton;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.base.CustomViewPager;
import com.ido.life.customview.ProgressView;

/* JADX INFO: loaded from: classes3.dex */
public class UserDataActivity_ViewBinding implements Unbinder {
    private UserDataActivity target;
    private View view7f0a0311;
    private View view7f0a0776;

    public UserDataActivity_ViewBinding(UserDataActivity userDataActivity) {
        this(userDataActivity, userDataActivity.getWindow().getDecorView());
    }

    public UserDataActivity_ViewBinding(final UserDataActivity userDataActivity, View view) {
        this.target = userDataActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mTitleLeftBtn' and method 'backButton'");
        userDataActivity.mTitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mTitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.userdata.UserDataActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                userDataActivity.backButton(view2);
            }
        });
        userDataActivity.mUserViewpager = (CustomViewPager) Utils.findRequiredViewAsType(view, R.id.user_viewpager, "field 'mUserViewpager'", CustomViewPager.class);
        userDataActivity.mProgressView = (ProgressView) Utils.findRequiredViewAsType(view, R.id.progress_view, "field 'mProgressView'", ProgressView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.iv_forward, "field 'mIvForward' and method 'forward'");
        userDataActivity.mIvForward = (ImageButton) Utils.castView(viewFindRequiredView2, R.id.iv_forward, "field 'mIvForward'", ImageButton.class);
        this.view7f0a0311 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.userdata.UserDataActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                userDataActivity.forward(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        UserDataActivity userDataActivity = this.target;
        if (userDataActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        userDataActivity.mTitleLeftBtn = null;
        userDataActivity.mUserViewpager = null;
        userDataActivity.mProgressView = null;
        userDataActivity.mIvForward = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a0311.setOnClickListener(null);
        this.view7f0a0311 = null;
    }
}