package com.ido.life.module.user.userinfo;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.module.user.view.ViewMeInfo;

/* JADX INFO: loaded from: classes3.dex */
public class UserInfoActivity_ViewBinding implements Unbinder {
    private UserInfoActivity target;

    public UserInfoActivity_ViewBinding(UserInfoActivity userInfoActivity) {
        this(userInfoActivity, userInfoActivity.getWindow().getDecorView());
    }

    public UserInfoActivity_ViewBinding(UserInfoActivity userInfoActivity, View view) {
        this.target = userInfoActivity;
        userInfoActivity.mTvUserHeader = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_header, "field 'mTvUserHeader'", TextView.class);
        userInfoActivity.mTvNickName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_nick_name, "field 'mTvNickName'", TextView.class);
        userInfoActivity.mIvAvatar = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_avatar, "field 'mIvAvatar'", ImageView.class);
        userInfoActivity.mTvNameValue = (EditText) Utils.findRequiredViewAsType(view, R.id.tv_name_value, "field 'mTvNameValue'", EditText.class);
        userInfoActivity.mViewMeInfoSex = (ViewMeInfo) Utils.findRequiredViewAsType(view, R.id.view_info_sex, "field 'mViewMeInfoSex'", ViewMeInfo.class);
        userInfoActivity.mViewInfoAge = (ViewMeInfo) Utils.findRequiredViewAsType(view, R.id.view_info_age, "field 'mViewInfoAge'", ViewMeInfo.class);
        userInfoActivity.mViewInfoHeight = (ViewMeInfo) Utils.findRequiredViewAsType(view, R.id.view_info_height, "field 'mViewInfoHeight'", ViewMeInfo.class);
        userInfoActivity.mViewInfoWeight = (ViewMeInfo) Utils.findRequiredViewAsType(view, R.id.view_info_weight, "field 'mViewInfoWeight'", ViewMeInfo.class);
        userInfoActivity.mViewAvatar = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.view_avatar, "field 'mViewAvatar'", ConstraintLayout.class);
        userInfoActivity.mViewInfoEmail = (ViewMeInfo) Utils.findRequiredViewAsType(view, R.id.view_info_email, "field 'mViewInfoEmail'", ViewMeInfo.class);
        userInfoActivity.mViewInfoArea = (ViewMeInfo) Utils.findRequiredViewAsType(view, R.id.view_info_area, "field 'mViewInfoArea'", ViewMeInfo.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        UserInfoActivity userInfoActivity = this.target;
        if (userInfoActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        userInfoActivity.mTvUserHeader = null;
        userInfoActivity.mTvNickName = null;
        userInfoActivity.mIvAvatar = null;
        userInfoActivity.mTvNameValue = null;
        userInfoActivity.mViewMeInfoSex = null;
        userInfoActivity.mViewInfoAge = null;
        userInfoActivity.mViewInfoHeight = null;
        userInfoActivity.mViewInfoWeight = null;
        userInfoActivity.mViewAvatar = null;
        userInfoActivity.mViewInfoEmail = null;
        userInfoActivity.mViewInfoArea = null;
    }
}