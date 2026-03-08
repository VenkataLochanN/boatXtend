package com.ido.life.module.bind;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes2.dex */
public class SearchActivity_ViewBinding implements Unbinder {
    private SearchActivity target;
    private View view7f0a0641;
    private View view7f0a0927;

    public SearchActivity_ViewBinding(SearchActivity searchActivity) {
        this(searchActivity, searchActivity.getWindow().getDecorView());
    }

    public SearchActivity_ViewBinding(final SearchActivity searchActivity, View view) {
        this.target = searchActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.rtv_cancel, "field 'mRtvCancel' and method 'onViewClicked'");
        searchActivity.mRtvCancel = (RegularTextView) Utils.castView(viewFindRequiredView, R.id.rtv_cancel, "field 'mRtvCancel'", RegularTextView.class);
        this.view7f0a0641 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.bind.SearchActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                searchActivity.onViewClicked(view2);
            }
        });
        searchActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        searchActivity.mLayoutRefresh = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.layout_refresh, "field 'mLayoutRefresh'", SwipeRefreshLayout.class);
        searchActivity.mIvIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_icon, "field 'mIvIcon'", ImageView.class);
        searchActivity.mLayoutSearch = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.layout_search, "field 'mLayoutSearch'", RelativeLayout.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_search_again, "field 'mTvSearchAgain' and method 'onViewClicked'");
        searchActivity.mTvSearchAgain = (RegularTextView) Utils.castView(viewFindRequiredView2, R.id.tv_search_again, "field 'mTvSearchAgain'", RegularTextView.class);
        this.view7f0a0927 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.bind.SearchActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                searchActivity.onViewClicked(view2);
            }
        });
        searchActivity.mIvSearch = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_search, "field 'mIvSearch'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SearchActivity searchActivity = this.target;
        if (searchActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        searchActivity.mRtvCancel = null;
        searchActivity.mRecyclerView = null;
        searchActivity.mLayoutRefresh = null;
        searchActivity.mIvIcon = null;
        searchActivity.mLayoutSearch = null;
        searchActivity.mTvSearchAgain = null;
        searchActivity.mIvSearch = null;
        this.view7f0a0641.setOnClickListener(null);
        this.view7f0a0641 = null;
        this.view7f0a0927.setOnClickListener(null);
        this.view7f0a0927 = null;
    }
}