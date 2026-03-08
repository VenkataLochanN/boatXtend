package com.ido.life.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.module.home.IHomeView;

/* JADX INFO: loaded from: classes3.dex */
public class HomeDeviceStateHolder extends BaseHomeViewHolder {

    @BindView(R.id.img_device_state_close)
    ImageView mImgClose;

    @BindView(R.id.tv_device_state_action)
    TextView mTvAction;

    @BindView(R.id.tv_device_state_message)
    TextView mTvMessage;

    public HomeDeviceStateHolder(View view, IHomeView iHomeView) {
        super(view, iHomeView);
    }

    @Override // com.ido.life.viewholder.BaseHomeViewHolder
    public void refreshPage() {
        this.mTvMessage.setText(LanguageUtil.getLanguageText(R.string.has_no_add_device));
        this.mTvAction.setText(LanguageUtil.getLanguageText(R.string.add_device));
        this.mImgClose.setOnClickListener(this.mHomeView);
        this.mTvAction.setOnClickListener(this.mHomeView);
        this.mFirstRefresh = false;
    }
}