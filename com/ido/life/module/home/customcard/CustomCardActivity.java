package com.ido.life.module.home.customcard;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.life.base.BaseActivity;
import com.ido.life.bean.SortBean;
import com.ido.life.customview.recyclerview.BaseLinearLayoutManager;
import com.ido.life.customview.recyclerview.DefaultItemTouchHelper;
import com.ido.life.customview.recyclerview.DefaultItemTouchHelperCallback;
import com.ido.life.customview.recyclerview.OnStartDragListener;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CustomCardActivity extends BaseActivity<CustomCardPresenter> implements OnStartDragListener {
    public static final int RESULT_CODE = 88;
    private CustomDragAdapter mAdapter;
    private List<SortBean> mDataList = new ArrayList();
    private DefaultItemTouchHelper mItemTouchHelper;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_custom_card;
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, (Class<?>) CustomCardActivity.class));
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mTitleBar.setTitle(R.string.home_main_custom_card).setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.home.customcard.-$$Lambda$CustomCardActivity$UUOku3hsuloRPWutg9lHA_7A5dU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initViews$0$CustomCardActivity(view);
            }
        });
        initRecyclerView();
    }

    public /* synthetic */ void lambda$initViews$0$CustomCardActivity(View view) {
        onBackPressed();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mAdapter.setData(((CustomCardPresenter) this.mPresenter).getItemList());
    }

    private void initRecyclerView() {
        this.mRecyclerView.setLayoutManager(new BaseLinearLayoutManager(this));
        this.mAdapter = new CustomDragAdapter(this.mDataList, this);
        this.mItemTouchHelper = new DefaultItemTouchHelper(new DefaultItemTouchHelperCallback(this.mAdapter));
        this.mItemTouchHelper.setDragEnable(true);
        this.mItemTouchHelper.setSwipeEnable(false);
        this.mItemTouchHelper.attachToRecyclerView(this.mRecyclerView);
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    @Override // com.ido.life.customview.recyclerview.OnStartDragListener
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        this.mItemTouchHelper.onStartDrag(viewHolder);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        ((CustomCardPresenter) this.mPresenter).saveCardStatus(this.mAdapter.getDatas());
        setResult(88, null);
        finish();
    }
}