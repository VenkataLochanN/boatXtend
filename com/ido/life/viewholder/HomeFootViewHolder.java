package com.ido.life.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.module.home.IHomeView;

/* JADX INFO: loaded from: classes3.dex */
public class HomeFootViewHolder extends BaseHomeViewHolder {

    @BindView(R.id.lay_edit_card)
    public LinearLayout mLayCard;

    @BindView(R.id.tv_edit_card)
    public TextView mTvTitle;

    public HomeFootViewHolder(View view, IHomeView iHomeView) {
        super(view, iHomeView);
    }

    @Override // com.ido.life.viewholder.BaseHomeViewHolder
    public void refreshPage() {
        this.mTvTitle.setText(LanguageUtil.getLanguageText(R.string.home_main_edit_card));
        this.mTvTitle.setOnClickListener(this.mHomeView);
        this.mFirstRefresh = false;
    }
}