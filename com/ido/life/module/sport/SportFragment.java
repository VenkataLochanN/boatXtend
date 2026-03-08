package com.ido.life.module.sport;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.google.android.material.badge.BadgeDrawable;
import com.ido.common.adapter.FragmentPagerStateAdapter;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.SetUpUtil;
import com.ido.common.utils.WindowUtil;
import com.ido.life.base.BaseFragment;
import com.ido.life.bean.SortBean;
import com.ido.life.module.sport.SportContract;
import com.ido.life.module.sport.SportPopupWindow;
import com.ido.life.module.sport.editcard.EditCardActivity;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportFragment extends BaseFragment implements SportContract.View {
    private static final String TAG = "SportFragment";
    public static boolean mRefuse;
    public static int mRefuseIndex;

    @BindView(R.id.ll_fg_sport_main)
    LinearLayout llFgSportMain;

    @BindView(R.id.hsv_sport)
    HorizontalScrollView mHorizontalScrollView;

    @BindView(R.id.iv_device_status)
    ImageView mIvDeviceStatus;
    private FragmentPagerStateAdapter mPagerAdapter;
    private SportContract.Presenter mPresenter;

    @BindView(R.id.sport_rg_tab)
    RadioGroup mSportRgTab;

    @BindView(R.id.sport_viewpager)
    ViewPager mSportViewPager;

    @BindView(R.id.tv_sport)
    TextView mTvSport;

    @BindView(R.id.rl_sport_top)
    RelativeLayout rlSportTop;
    private int mType = 1;
    protected List<Fragment> mFragmentList = new ArrayList();

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_sport;
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void onVisible() {
        super.onVisible();
        this.mPresenter.getDeviceStatus();
        CommonLogUtil.d(TAG, "onVisible: " + this.mPresenter);
        SportContract.Presenter presenter = this.mPresenter;
        if (presenter != null) {
            presenter.startLocation();
        }
    }

    @Override // com.ido.life.module.sport.SportContract.View
    public void setGpsStatus(int i) {
        if (this.mPagerAdapter.getItem(this.mSportViewPager.getCurrentItem()) instanceof SportBgFragment) {
            ((SportBgFragment) this.mPagerAdapter.getItem(this.mSportViewPager.getCurrentItem())).setGpsStatus(i);
        }
    }

    @Override // com.ido.common.base.BaseCoreFragment
    public void onInVisible() {
        super.onInVisible();
        SportContract.Presenter presenter = this.mPresenter;
        if (presenter != null) {
            presenter.stopLocation();
        }
    }

    private void addRadioButton(String str) {
        final RadioButton radioButton = new RadioButton(getContext());
        radioButton.setButtonDrawable((Drawable) null);
        radioButton.setText(str);
        radioButton.setTextColor(ResourceUtil.getColor(R.color.com_color_text_A9));
        radioButton.setTextSize(0, DipPixelUtil.sp2px(17.0f));
        radioButton.setGravity(17);
        final Drawable drawable = ResourceUtil.getDrawable(R.drawable.shape_orange_line_indicator);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.ido.life.module.sport.SportFragment.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    radioButton.setTextColor(ResourceUtil.getColor(R.color.white));
                    if (compoundButton.getText().length() == 2) {
                        Drawable drawable2 = drawable;
                        drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth() / 2, drawable.getIntrinsicHeight());
                    } else {
                        Drawable drawable3 = drawable;
                        drawable3.setBounds(0, 0, drawable3.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    }
                    radioButton.setCompoundDrawables(null, null, null, drawable);
                    SportFragment.this.mHorizontalScrollView.smoothScrollTo(compoundButton.getLeft() - (WindowUtil.getWindowSize(SportFragment.this.getActivity()).getWidth() / 3), 0);
                    return;
                }
                radioButton.setTextColor(ResourceUtil.getColor(R.color.com_color_text_A9));
                radioButton.setButtonDrawable((Drawable) null);
                radioButton.setCompoundDrawables(null, null, null, null);
            }
        });
        int iDip2px = DipPixelUtil.dip2px(10.0f);
        radioButton.setPadding(iDip2px, 0, iDip2px, 0);
        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(-2, -1);
        layoutParams.setMarginEnd(DipPixelUtil.dip2px(12.0f));
        this.mSportRgTab.addView(radioButton, layoutParams);
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void initData() {
        super.initData();
        this.mPresenter = new SportPresenter(this);
        this.mPresenter.getCardData();
        initViewPager();
    }

    private void initViewPager() {
        this.mPagerAdapter = new FragmentPagerStateAdapter(getChildFragmentManager(), this.mFragmentList);
        this.mSportViewPager.setAdapter(this.mPagerAdapter);
        SetUpUtil.setUpWithRadioGroupAndViewPager(this.mSportRgTab, this.mSportViewPager);
        checkFirst();
        this.mSportViewPager.setOffscreenPageLimit(1);
    }

    private void checkFirst() {
        if (this.mSportRgTab.getChildCount() > 0) {
            RadioGroup radioGroup = this.mSportRgTab;
            radioGroup.check(radioGroup.getChildAt(0).getId());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 88) {
            return;
        }
        this.mPresenter.getCardData();
    }

    @OnClick({R.id.img_more})
    public void toMore(View view) {
        SportPopupWindow sportPopupWindow = new SportPopupWindow(getActivity());
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        sportPopupWindow.showAtLocation(view, BadgeDrawable.TOP_END, DipPixelUtil.dip2px(18.0f), iArr[1] + DipPixelUtil.dip2px(30.0f));
        sportPopupWindow.setOnItemClickListener(new SportPopupWindow.OnItemClickListener() { // from class: com.ido.life.module.sport.SportFragment.2
            @Override // com.ido.life.module.sport.SportPopupWindow.OnItemClickListener
            public void onEditCardClick(View view2) {
                SportFragment.this.startActivityForResult(new Intent(SportFragment.this.getActivity(), (Class<?>) EditCardActivity.class), 88);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mTvSport.setText(LanguageUtil.getLanguageText(R.string.sport_training));
        this.llFgSportMain.setBackgroundColor(getResources().getColor(R.color.black));
    }

    @Override // com.ido.life.module.sport.SportContract.View
    public void showConnectDevice() {
        this.mIvDeviceStatus.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_device_connect));
    }

    @Override // com.ido.life.module.sport.SportContract.View
    public void showDisconnectDevice() {
        this.mIvDeviceStatus.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_device_disconnect));
    }

    @Override // com.ido.life.module.sport.SportContract.View
    public void setSportType(int i) {
        this.mType = i;
    }

    @Override // com.ido.life.module.sport.SportContract.View
    public int getSportType() {
        return this.mType;
    }

    @Override // com.ido.life.module.sport.SportContract.View
    public void showLocationServiceDialog() {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(ResourceUtil.getString(R.string.sport_device_gps_tips), ResourceUtil.getString(R.string.sport_device_gps_open), ResourceUtil.getString(R.string.public_tip_cancel), true);
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.SportFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.SportFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SportFragment.this.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
            }
        });
        commBottomConfirmDialogNewInstance.show(getActivity().getSupportFragmentManager());
    }

    @Override // com.ido.life.module.sport.SportContract.View
    public void setSportChoose(List<SortBean> list) {
        this.mSportRgTab.removeAllViews();
        this.mFragmentList.clear();
        initViewPager();
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                this.mType = list.get(i).getType();
            }
            SportBgFragment sportBgFragmentNewInstance = SportBgFragment.newInstance(list.get(i).getType());
            sportBgFragmentNewInstance.setTypeId(list.get(i).getType());
            this.mFragmentList.add(sportBgFragmentNewInstance);
            addRadioButton(ResourceUtil.getString(list.get(i).getNameId()));
        }
        FragmentPagerStateAdapter fragmentPagerStateAdapter = this.mPagerAdapter;
        if (fragmentPagerStateAdapter != null) {
            fragmentPagerStateAdapter.notifyDataSetChanged();
        }
        SetUpUtil.setUpWithRadioGroupAndViewPager(this.mSportRgTab, this.mSportViewPager);
        checkFirst();
    }

    @OnClick({R.id.iv_device_status})
    public void getDeviceStatus(View view) {
        this.mPresenter.showDeviceStatus();
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(SportContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.stopLocation();
        List<Fragment> list = this.mFragmentList;
        if (list != null) {
            list.clear();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // com.ido.common.base.BaseCoreFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        SportContract.Presenter presenter = this.mPresenter;
        if (presenter != null) {
            presenter.stopLocation();
        }
    }
}