package com.ido.life.module.user.sportrecord;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.dialog.ChooseSportTypeDialogFragment;
import com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity;
import com.ido.life.module.sport.history.swim.SportSwimActivity;
import com.ido.life.module.user.sportrecord.adapter.ExpandableRecyclerAdapter;
import com.ido.life.module.user.sportrecord.model.SportGroupItem;
import com.ido.life.module.user.sportrecord.model.SportScreenType;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SportRecordActivity extends BaseActivity<SportRecordPresenter> implements ISportRecordView {
    private static final int DELAY_DURATION = 500;
    private static final String EXTRA_TYPE = "sport_type";
    private static final String TAG = "SportRecordActivity";
    private long clickNowTime;
    private ExpandableRecyclerAdapter mAdapter;

    @BindView(R.id.empty_tv_text)
    TextView mEmptyTv;

    @BindView(R.id.expand_list)
    RecyclerView mRvExpandList;
    private List<SportScreenType> mSportScreenTypeList;

    @BindView(R.id.title_leftBtn)
    ImageButton mTitleLeftBtn;

    @BindView(R.id.tv_sport_type)
    TextView mTvSportType;

    @BindView(R.id.title_text)
    TextView mTvTitle;

    @BindView(R.id.rv_sport_type)
    RelativeLayout rvSportType;
    private int mChooseIndex = 0;
    private int mType = -1;
    private long mUserId = RunTimeUtil.getInstance().getUserId();

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_sport_record;
    }

    @Override // com.ido.life.module.user.sportrecord.ISportRecordView
    public void toIndoorRun(int i, String str) {
    }

    @Override // com.ido.life.module.user.sportrecord.ISportRecordView
    public void toSportTravel(int i, String str) {
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) SportRecordActivity.class));
    }

    public static void startActivity(Activity activity, int i) {
        Intent intent = new Intent(activity, (Class<?>) SportRecordActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("sport_type", i);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        setView();
        initListener();
    }

    private void setView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        this.mRvExpandList.setLayoutManager(linearLayoutManager);
        this.mAdapter = new ExpandableRecyclerAdapter(new ArrayList());
        this.mRvExpandList.setAdapter(this.mAdapter);
    }

    private void initListener() {
        this.mAdapter.setOnClickListener(new ExpandableRecyclerAdapter.OnItemClickListener() { // from class: com.ido.life.module.user.sportrecord.-$$Lambda$SportRecordActivity$ayH66iQ6UrTtff8LZasUOnM72q8
            @Override // com.ido.life.module.user.sportrecord.adapter.ExpandableRecyclerAdapter.OnItemClickListener
            public final void onChildItemClick(int i, String str, int i2) {
                this.f$0.lambda$initListener$0$SportRecordActivity(i, str, i2);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$SportRecordActivity(int i, String str, int i2) {
        if (System.currentTimeMillis() - this.clickNowTime < 500) {
            return;
        }
        this.clickNowTime = System.currentTimeMillis();
        ((SportRecordPresenter) this.mPresenter).toSportDetailByType(i, str, i2);
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        List<SportScreenType> list = this.mSportScreenTypeList;
        if (list != null) {
            int size = list.size();
            int i = this.mChooseIndex;
            if (size > i) {
                this.mTvSportType.setText(this.mSportScreenTypeList.get(i).getTypeName());
            }
        }
        this.mTvTitle.setText(LanguageUtil.getLanguageText(R.string.mine_sport_record));
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mUserId = getIntent().getLongExtra(Constants.INTENT_USER_ID, this.mUserId);
        ((SportRecordPresenter) this.mPresenter).getRecordSport(this.mType, false);
    }

    @OnClick({R.id.title_leftBtn})
    public void toBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @OnClick({R.id.tv_sport_type, R.id.iv_sport_type, R.id.rv_sport_type})
    public void toChooseSportType(View view) {
        ChooseSportTypeDialogFragment chooseSportTypeDialogFragmentNewInstance = ChooseSportTypeDialogFragment.newInstance(this.mSportScreenTypeList, this.mChooseIndex);
        chooseSportTypeDialogFragmentNewInstance.setOnChooseListener(new ChooseSportTypeDialogFragment.OnChooseListener() { // from class: com.ido.life.module.user.sportrecord.SportRecordActivity.1
            @Override // com.ido.life.dialog.ChooseSportTypeDialogFragment.OnChooseListener
            public void choose(int i) {
                SportRecordActivity.this.mChooseIndex = i;
                SportRecordActivity.this.mTvSportType.setText(((SportScreenType) SportRecordActivity.this.mSportScreenTypeList.get(SportRecordActivity.this.mChooseIndex)).getTypeName());
                int typeId = ((SportScreenType) SportRecordActivity.this.mSportScreenTypeList.get(SportRecordActivity.this.mChooseIndex)).getTypeId();
                SportRecordActivity.this.mType = typeId;
                ((SportRecordPresenter) SportRecordActivity.this.mPresenter).getRecordSport(typeId, false);
            }
        });
        chooseSportTypeDialogFragmentNewInstance.show(getSupportFragmentManager());
    }

    @Override // com.ido.life.module.user.sportrecord.ISportRecordView
    public void showLoading() {
        showLoadingDialog();
    }

    @Override // com.ido.life.module.user.sportrecord.ISportRecordView
    public void hideLoading() {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "hideLoading: ");
        runOnUiThread(new Runnable() { // from class: com.ido.life.module.user.sportrecord.SportRecordActivity.2
            @Override // java.lang.Runnable
            public void run() {
                SportRecordActivity.this.dismissLoadingDialog();
            }
        });
    }

    @Override // com.ido.life.module.user.sportrecord.ISportRecordView
    public void showMessage(String str) {
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.sportrecord.ISportRecordView
    public void showSportRecord(final List<SportGroupItem> list, final List<Integer> list2) {
        runOnUiThread(new Runnable() { // from class: com.ido.life.module.user.sportrecord.SportRecordActivity.3
            @Override // java.lang.Runnable
            public void run() {
                if (SportRecordActivity.this.mAdapter == null) {
                    return;
                }
                SportRecordActivity.this.mAdapter.clearGroup();
                SportRecordActivity.this.mAdapter.setGroupItemList(list2);
                SportRecordActivity.this.mAdapter.addGroupList(list);
                SportRecordActivity.this.mAdapter.expand(0);
                SportRecordActivity.this.mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override // com.ido.life.module.user.sportrecord.ISportRecordView
    public void setEmptyText(final String str) {
        runOnUiThread(new Runnable() { // from class: com.ido.life.module.user.sportrecord.SportRecordActivity.4
            @Override // java.lang.Runnable
            public void run() {
                if (SportRecordActivity.this.mEmptyTv != null) {
                    SportRecordActivity.this.mEmptyTv.setText(str);
                }
            }
        });
    }

    @Override // com.ido.life.module.user.sportrecord.ISportRecordView
    public void setEmptyDrawable(int i) {
        Drawable drawable = ResourceUtil.getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.mEmptyTv.setCompoundDrawables(null, drawable, null, null);
    }

    @Override // com.ido.life.module.user.sportrecord.ISportRecordView
    public void showEmpty() {
        runOnUiThread(new Runnable() { // from class: com.ido.life.module.user.sportrecord.SportRecordActivity.5
            @Override // java.lang.Runnable
            public void run() {
                if (SportRecordActivity.this.mRvExpandList != null) {
                    SportRecordActivity.this.mRvExpandList.setVisibility(8);
                }
                if (SportRecordActivity.this.rvSportType != null) {
                    SportRecordActivity.this.rvSportType.setVisibility(8);
                }
                if (SportRecordActivity.this.mEmptyTv != null) {
                    SportRecordActivity.this.mEmptyTv.setVisibility(0);
                }
            }
        });
    }

    @Override // com.ido.life.module.user.sportrecord.ISportRecordView
    public void hideEmpty() {
        runOnUiThread(new Runnable() { // from class: com.ido.life.module.user.sportrecord.SportRecordActivity.6
            @Override // java.lang.Runnable
            public void run() {
                if (SportRecordActivity.this.mEmptyTv != null) {
                    SportRecordActivity.this.mEmptyTv.setVisibility(8);
                }
                if (SportRecordActivity.this.rvSportType != null) {
                    SportRecordActivity.this.rvSportType.setVisibility(0);
                }
                if (SportRecordActivity.this.mRvExpandList != null) {
                    SportRecordActivity.this.mRvExpandList.setVisibility(0);
                }
            }
        });
    }

    @Override // com.ido.life.module.user.sportrecord.ISportRecordView
    public void toSwim(int i, String str) {
        SportSwimActivity.startActivityResult(this, i, str, this.mUserId);
    }

    @Override // com.ido.life.module.user.sportrecord.ISportRecordView
    public void setSportScreenType(List<SportScreenType> list) {
        List<SportScreenType> list2 = this.mSportScreenTypeList;
        if (list2 != null) {
            list2.clear();
        }
        if (list != null) {
            this.mSportScreenTypeList = new ArrayList();
            this.mSportScreenTypeList.addAll(list);
        }
    }

    @Override // com.ido.life.module.user.sportrecord.ISportRecordView
    public void toSportDetail(int i, String str) {
        SportHistoryDetailNewActivity.INSTANCE.startActivityResult(this, i, str, 1);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001 && i2 == 1002) {
            ((SportRecordPresenter) this.mPresenter).getRecordSport(this.mType, false);
        }
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        if (this.mPresenter != 0) {
            ((SportRecordPresenter) this.mPresenter).activityFinish();
        }
        super.onDestroy();
    }
}