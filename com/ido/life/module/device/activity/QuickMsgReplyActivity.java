package com.ido.life.module.device.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.recyclerview.BaseLinearLayoutManager;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.HeaderAndFooterWrapper;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.dialog.CommLoadingDialog;
import com.ido.life.module.device.presenter.QuickMsgReplyPresenter;
import com.ido.life.module.device.view.IQuickMsgReplyView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class QuickMsgReplyActivity extends BaseActivity<QuickMsgReplyPresenter> implements IQuickMsgReplyView, MultiItemTypeAdapterForRV.OnItemClickListener {
    private static final int MSG_COUNT_MAX = 5;
    private HeaderAndFooterWrapper mAdapter;
    private View mFootView;

    @BindView(R.id.item_msg_content)
    CustomItemLabelView mItemMsgContent;

    @BindView(R.id.iv_add_msg)
    ImageView mIvAddMsg;
    private List<String> mLastQuickMsgList;

    @BindView(R.id.layout_msg_content)
    RelativeLayout mLayoutMsgContent;
    private CommLoadingDialog mLoadingDialog;
    private List<String> mQuickMsgList;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_quick_msg_reply;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mQuickMsgList = ((QuickMsgReplyPresenter) this.mPresenter).getQuickMsgList();
        this.mLastQuickMsgList = new ArrayList();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        CommonRecyclerViewAdapter<String> commonRecyclerViewAdapter = new CommonRecyclerViewAdapter<String>(this, R.layout.item_quick_msg, this.mQuickMsgList) { // from class: com.ido.life.module.device.activity.QuickMsgReplyActivity.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, String str, int i) {
                ((MediumTextView) commonRecyclerViewHolder.getView(R.id.mtv_msg)).setText(str);
            }
        };
        this.mRecyclerView.setLayoutManager(new BaseLinearLayoutManager(this));
        this.mAdapter = new HeaderAndFooterWrapper(commonRecyclerViewAdapter);
        this.mRecyclerView.setAdapter(this.mAdapter);
        commonRecyclerViewAdapter.setOnItemClickListener(this);
        this.mFootView = LayoutInflater.from(this).inflate(R.layout.item_msg_foot, (ViewGroup) this.mRecyclerView, false);
        updateView();
    }

    private void updateView() {
        this.mAdapter.removeFootView(this.mFootView);
        if (this.mQuickMsgList.size() > 0) {
            this.mAdapter.addFootView(this.mFootView);
        }
        this.mAdapter.notifyDataSetChanged();
        this.mIvAddMsg.setVisibility(this.mQuickMsgList.size() >= 5 ? 8 : 0);
    }

    private void showBottomLoadingDialog() {
        CommLoadingDialog commLoadingDialog = this.mLoadingDialog;
        if (commLoadingDialog != null) {
            commLoadingDialog.dismissAllowingStateLoss();
        }
        this.mLoadingDialog = CommLoadingDialog.getInstance(getLanguageText(R.string.device_setting), getLanguageText(R.string.device_set_success), getLanguageText(R.string.device_set_failed));
        this.mLoadingDialog.show(getSupportFragmentManager());
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_quick_msg_reply));
        this.mItemMsgContent.setTitle(getLanguageText(R.string.device_msg_content));
    }

    @OnClick({R.id.iv_add_msg})
    public void onViewClicked() {
        resetLastMsgList();
        Intent intent = new Intent(this, (Class<?>) MsgEditActivity.class);
        intent.putExtra("index", -1);
        startActivityForResult(intent, 11);
    }

    private void resetLastMsgList() {
        this.mLastQuickMsgList.clear();
        this.mLastQuickMsgList.addAll(this.mQuickMsgList);
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        resetLastMsgList();
        Intent intent = new Intent(this, (Class<?>) MsgEditActivity.class);
        intent.putExtra("msg", this.mQuickMsgList.get(i));
        intent.putExtra("index", i);
        startActivityForResult(intent, 11);
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        showDeleteDialog(i);
        return true;
    }

    private void showDeleteDialog(final int i) {
        if (i < 0 || i >= this.mQuickMsgList.size() || this.mQuickMsgList.size() == 1) {
            return;
        }
        CommBottomConfirmDialog.newInstance(getLanguageText(R.string.device_delete_msg), getLanguageText(R.string.device_delete_msg_tip), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$QuickMsgReplyActivity$IHeBqh1t6Wf5ztxbfcsTrJFs5zA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showDeleteDialog$0$QuickMsgReplyActivity(i, view);
            }
        }).show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showDeleteDialog$0$QuickMsgReplyActivity(int i, View view) {
        deleteQuickMsg(i);
    }

    private void deleteQuickMsg(int i) {
        if (!((QuickMsgReplyPresenter) this.mPresenter).isConnected()) {
            showToast(getLanguageText(R.string.device_pls_connect_device));
            return;
        }
        showBottomLoadingDialog();
        resetLastMsgList();
        this.mQuickMsgList.remove(i);
        ((QuickMsgReplyPresenter) this.mPresenter).sendQuickReplyInfo2Device(this.mQuickMsgList);
    }

    private void setQuickMsg() {
        showBottomLoadingDialog();
        ((QuickMsgReplyPresenter) this.mPresenter).sendQuickReplyInfo2Device(this.mQuickMsgList);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != 11 || intent == null) {
            return;
        }
        int intExtra = intent.getIntExtra("index", -1);
        String stringExtra = intent.getStringExtra("msg");
        if (intExtra == -1) {
            if (TextUtils.isEmpty(stringExtra)) {
                return;
            } else {
                this.mQuickMsgList.add(stringExtra);
            }
        } else {
            if (this.mQuickMsgList.size() <= 1 && TextUtils.isEmpty(stringExtra)) {
                return;
            }
            this.mQuickMsgList.remove(intExtra);
            if (!TextUtils.isEmpty(stringExtra)) {
                this.mQuickMsgList.add(intExtra, stringExtra);
            }
        }
        updateView();
        setQuickMsg();
    }

    @Override // com.ido.life.module.device.view.IQuickMsgReplyView
    public void onSetQuickReplyInfoSuccess() {
        ((QuickMsgReplyPresenter) this.mPresenter).saveQuickMsgList(this.mQuickMsgList);
        updateView();
        CommLoadingDialog commLoadingDialog = this.mLoadingDialog;
        if (commLoadingDialog != null) {
            commLoadingDialog.setComplete(true);
        }
    }

    @Override // com.ido.life.module.device.view.IQuickMsgReplyView
    public void onSetQuickReplyInfoFailed() {
        this.mQuickMsgList.clear();
        this.mQuickMsgList.addAll(this.mLastQuickMsgList);
        this.mAdapter.notifyDataSetChanged();
        CommLoadingDialog commLoadingDialog = this.mLoadingDialog;
        if (commLoadingDialog != null) {
            commLoadingDialog.setComplete(false);
        }
    }
}