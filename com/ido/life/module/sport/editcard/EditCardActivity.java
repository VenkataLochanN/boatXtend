package com.ido.life.module.sport.editcard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.bean.SortBean;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.recyclerview.BaseLinearLayoutManager;
import com.ido.life.customview.recyclerview.DefaultItemTouchHelper;
import com.ido.life.customview.recyclerview.DefaultItemTouchHelperCallback;
import com.ido.life.customview.recyclerview.OnStartDragListener;
import com.ido.life.module.sport.editcard.EditCardContract;
import com.ido.life.module.sport.editcard.adapter.SportSortDragAdapter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class EditCardActivity extends BaseCoreActivity implements OnStartDragListener, EditCardContract.View {
    public static final int RESULT_CODE = 88;
    public static final String SELECTED_TYPE_LIST = "selected_type_list";
    private static final String TAG = "EditCardActivity";
    private SportSortDragAdapter mAdapter;
    private List<SortBean> mDataList = new ArrayList();
    private DefaultItemTouchHelper mItemTouchHelper;
    private EditCardContract.Presenter mPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.title_leftBtn)
    ImageButton mTitleLeftBtn;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_edit_card;
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, (Class<?>) EditCardActivity.class));
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initViews() {
        super.initViews();
        initRecyclerView();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mTitleText.setText(LanguageUtil.getLanguageText(R.string.sport_edit_title));
        this.mPresenter = new EditCardPresenter(this);
        this.mAdapter.setData(this.mPresenter.getItemList());
        this.mAdapter.setOnCheckedCountListener(new SportSortDragAdapter.OnCheckedLimitedListener() { // from class: com.ido.life.module.sport.editcard.EditCardActivity.1
            @Override // com.ido.life.module.sport.editcard.adapter.SportSortDragAdapter.OnCheckedLimitedListener
            public void onCheckedCountMax() {
                CommonLogUtil.d(EditCardActivity.TAG, "onCheckedCountMax: ");
            }

            @Override // com.ido.life.module.sport.editcard.adapter.SportSortDragAdapter.OnCheckedLimitedListener
            public void onCheckedCountMin() {
                CommonLogUtil.d(EditCardActivity.TAG, "onCheckedCountMin: ");
            }
        });
    }

    private void initRecyclerView() {
        this.mRecyclerView.setLayoutManager(new BaseLinearLayoutManager(this));
        this.mAdapter = new SportSortDragAdapter(this.mDataList, this, 2);
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
        toBack();
    }

    @OnClick({R.id.title_leftBtn})
    public void back(View view) {
        toBack();
    }

    private void toBack() {
        this.mPresenter.saveCardStatus(this.mAdapter.getSelectedList());
        setResult(88, new Intent());
        finish();
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(EditCardContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // com.ido.life.module.sport.editcard.EditCardContract.View
    public void showLoading() {
        WaitingDialog.showDialog(this);
    }

    @Override // com.ido.life.module.sport.editcard.EditCardContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.sport.editcard.EditCardContract.View
    public void showMessage(String str) {
        NormalToast.showToast(str);
    }
}