package com.ido.life.viewholder;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;
import com.ido.life.bean.MainData;
import com.ido.life.module.home.IHomeView;
import com.ido.life.util.RunTimeUtil;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseHomeViewHolder extends RecyclerView.ViewHolder {
    public boolean mFirstRefresh;
    protected IHomeView mHomeView;
    protected boolean mShowAnimator;

    public abstract void refreshPage();

    public BaseHomeViewHolder(View view, IHomeView iHomeView) {
        super(view);
        this.mFirstRefresh = true;
        this.mShowAnimator = true;
        this.mHomeView = iHomeView;
        ButterKnife.bind(this, view);
    }

    public void setShowAnimator(boolean z) {
        this.mShowAnimator = z;
    }

    public void bindData(MainData mainData) {
        refreshPage();
    }

    public void refreshPage(boolean z) {
        this.mShowAnimator = z;
        refreshPage();
    }

    protected long getUserId() {
        return RunTimeUtil.getInstance().getUserId();
    }
}