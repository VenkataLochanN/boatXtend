package com.ido.life.module.user.userinfo;

import android.app.Activity;
import android.content.Intent;
import android.text.Selection;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.common.dialog.BirthdayDateDialogFragment;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.ImageLoaderUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.GlideImageLoader;
import com.ido.life.base.TextLengthWatcher;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.UserInfoNumberSelectDialogFragment;
import com.ido.life.dialog.ChoosePhotoDialogFragment;
import com.ido.life.dialog.ChooseSexDialogFragment;
import com.ido.life.module.user.view.ViewMeInfo;
import com.ido.life.util.DateUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPUtils;
import com.ido.life.util.eventbus.EventBusHelper;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class UserInfoActivity extends BaseActivity<UserInfoPresenter> implements IUserInfoView, View.OnClickListener {
    private static final int ALBUM_REQUEST_CODE = 10001;
    static final int CAMERA_REQUEST_CODE = 10002;
    private static final String TAG = UserInfoActivity.class.getSimpleName();

    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    private TextLengthWatcher mLengthWatcher;

    @BindView(R.id.tv_name_value)
    EditText mTvNameValue;

    @BindView(R.id.tv_nick_name)
    TextView mTvNickName;

    @BindView(R.id.tv_title_header)
    TextView mTvUserHeader;

    @BindView(R.id.view_avatar)
    ConstraintLayout mViewAvatar;

    @BindView(R.id.view_info_age)
    ViewMeInfo mViewInfoAge;

    @BindView(R.id.view_info_area)
    ViewMeInfo mViewInfoArea;

    @BindView(R.id.view_info_email)
    ViewMeInfo mViewInfoEmail;

    @BindView(R.id.view_info_height)
    ViewMeInfo mViewInfoHeight;

    @BindView(R.id.view_info_weight)
    ViewMeInfo mViewInfoWeight;

    @BindView(R.id.view_info_sex)
    ViewMeInfo mViewMeInfoSex;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_user_info;
    }

    @Override // com.ido.common.base.BaseCoreActivity, com.ido.common.utils.PermissionUtil.RequestResult
    public void requestPermissionsFail(int i) {
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void saveInfoFailed(String str) {
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void saveInfoSuccess() {
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void setHeightUnit(String str) {
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void setWeightUnit(String str) {
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) UserInfoActivity.class));
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mTitleBar.setTitle(getLanguageText(R.string.my_info));
        this.mTvUserHeader.setText(getLanguageText(R.string.mine_head_portrait));
        this.mTvNickName.setText(getLanguageText(R.string.mine_nickname));
        this.mViewMeInfoSex.setStartText(getLanguageText(R.string.mine_data_gender));
        this.mViewInfoAge.setStartText(getLanguageText(R.string.user_birthday));
        this.mViewInfoHeight.setStartText(getLanguageText(R.string.mine_data_height));
        this.mViewInfoWeight.setStartText(getLanguageText(R.string.mine_data_weight));
        this.mViewInfoEmail.setStartText(getLanguageText(R.string.mine_data_email));
        if (RunTimeUtil.getInstance().getUserId() != -1) {
            this.mViewInfoEmail.setVisibility(0);
        } else {
            this.mViewInfoEmail.setVisibility(8);
        }
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.user.userinfo.-$$Lambda$UserInfoActivity$zxJrm4_nBPGzUp4z42E8mLmJCpo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initViews$0$UserInfoActivity(view);
            }
        });
        ((UserInfoPresenter) this.mPresenter).initUserData();
        this.mLengthWatcher = new TextLengthWatcher(64, new TextLengthWatcher.OnTextChangedListener() { // from class: com.ido.life.module.user.userinfo.-$$Lambda$UserInfoActivity$kY-T_YOAc1I5TndfIyEQ_yZap9k
            @Override // com.ido.life.base.TextLengthWatcher.OnTextChangedListener
            public final void onAfterTextChanged(String str) {
                this.f$0.lambda$initViews$1$UserInfoActivity(str);
            }
        });
        this.mTvNameValue.addTextChangedListener(this.mLengthWatcher);
    }

    public /* synthetic */ void lambda$initViews$0$UserInfoActivity(View view) {
        String string = this.mTvNameValue.getText().toString();
        if (TextUtils.isEmpty(string)) {
            onEmptyName();
            return;
        }
        ((UserInfoPresenter) this.mPresenter).setDisplayName(string);
        ((UserInfoPresenter) this.mPresenter).saveUserInfo();
        finish();
    }

    public /* synthetic */ void lambda$initViews$1$UserInfoActivity(String str) {
        int selectionEnd = this.mTvNameValue.getSelectionEnd();
        this.mTvNameValue.removeTextChangedListener(this.mLengthWatcher);
        this.mTvNameValue.setText(str);
        ((UserInfoPresenter) this.mPresenter).setDisplayName(str);
        this.mTvNameValue.addTextChangedListener(this.mLengthWatcher);
        Selection.setSelection(this.mTvNameValue.getText(), Math.min(Math.max(selectionEnd, 0), str.length()));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(true);
        imagePicker.setSaveRectangle(true);
        imagePicker.setSelectLimit(1);
        imagePicker.setStyle(CropImageView.Style.CIRCLE);
        imagePicker.setFocusWidth((getResources().getDisplayMetrics().widthPixels * 3) / 5);
        imagePicker.setFocusHeight((getResources().getDisplayMetrics().widthPixels * 3) / 5);
        imagePicker.setOutPutX(100);
        imagePicker.setOutPutY(100);
        imagePicker.setMultiMode(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ChooseSexDialogFragment chooseSexDialogFragmentNewInstance;
        int id = view.getId();
        if (id == R.id.view_avatar) {
            showChoosePhotoDialog();
            return;
        }
        if (id != R.id.view_info_age) {
            switch (id) {
                case R.id.view_info_height /* 2131364412 */:
                    ((UserInfoPresenter) this.mPresenter).setHeight(this.mViewInfoHeight.getEndText());
                    break;
                case R.id.view_info_sex /* 2131364413 */:
                    if (LanguageUtil.getLanguageText(R.string.me_all_female_ios).contentEquals(this.mViewMeInfoSex.getEndText())) {
                        chooseSexDialogFragmentNewInstance = ChooseSexDialogFragment.newInstance(2);
                    } else {
                        chooseSexDialogFragmentNewInstance = ChooseSexDialogFragment.newInstance(1);
                    }
                    chooseSexDialogFragmentNewInstance.show(getSupportFragmentManager());
                    chooseSexDialogFragmentNewInstance.setOnChooseListener(new ChooseSexDialogFragment.OnChooseListener() { // from class: com.ido.life.module.user.userinfo.-$$Lambda$UserInfoActivity$hSoJVC0LCfHMIMAvDwOXDr2IqAQ
                        @Override // com.ido.life.dialog.ChooseSexDialogFragment.OnChooseListener
                        public final void choose(int i) {
                            this.f$0.lambda$onClick$2$UserInfoActivity(i);
                        }
                    });
                    break;
                case R.id.view_info_weight /* 2131364414 */:
                    ((UserInfoPresenter) this.mPresenter).setWeight(this.mViewInfoWeight.getEndText());
                    break;
            }
            return;
        }
        ((UserInfoPresenter) this.mPresenter).showCurrentBirthday(this.mViewInfoAge.getEndText());
    }

    public /* synthetic */ void lambda$onClick$2$UserInfoActivity(int i) {
        ((UserInfoPresenter) this.mPresenter).doChooseSex(i);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        String string = this.mTvNameValue.getText().toString();
        if (TextUtils.isEmpty(string)) {
            onEmptyName();
            return;
        }
        ((UserInfoPresenter) this.mPresenter).setDisplayName(string);
        ((UserInfoPresenter) this.mPresenter).saveUserInfo();
        super.onBackPressed();
    }

    private void showChoosePhotoDialog() {
        final ChoosePhotoDialogFragment choosePhotoDialogFragmentNewInstance = ChoosePhotoDialogFragment.newInstance(getLanguageText(R.string.device_user_album), getLanguageText(R.string.device_user_take_photo));
        choosePhotoDialogFragmentNewInstance.show(getSupportFragmentManager());
        choosePhotoDialogFragmentNewInstance.setLeftClickListener(new View.OnClickListener() { // from class: com.ido.life.module.user.userinfo.-$$Lambda$UserInfoActivity$49v7I0Sf9G4EFUY2ijmKN06-nHI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showChoosePhotoDialog$3$UserInfoActivity(choosePhotoDialogFragmentNewInstance, view);
            }
        });
        choosePhotoDialogFragmentNewInstance.setRightClickListener(new View.OnClickListener() { // from class: com.ido.life.module.user.userinfo.-$$Lambda$UserInfoActivity$cOYtvHSomgKyxKFbxzb8Vj2OqYg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showChoosePhotoDialog$4$UserInfoActivity(choosePhotoDialogFragmentNewInstance, view);
            }
        });
    }

    public /* synthetic */ void lambda$showChoosePhotoDialog$3$UserInfoActivity(ChoosePhotoDialogFragment choosePhotoDialogFragment, View view) {
        requestCameraPermission();
        choosePhotoDialogFragment.dismissAllowingStateLoss();
    }

    public /* synthetic */ void lambda$showChoosePhotoDialog$4$UserInfoActivity(ChoosePhotoDialogFragment choosePhotoDialogFragment, View view) {
        requestStoragePermission();
        choosePhotoDialogFragment.dismissAllowingStateLoss();
    }

    private void requestStoragePermission() {
        if (checkSelfPermission(PermissionUtil.getStoragePermission())) {
            openChooser(10001);
        } else {
            requestPermissions(10001, PermissionUtil.getStoragePermission());
        }
    }

    private void requestCameraPermission() {
        if (checkSelfPermission(PermissionUtil.getCameraPermission())) {
            toCamera();
        } else {
            requestPermissions(10002, PermissionUtil.getCameraPermission());
        }
    }

    private void toCamera() {
        Intent intent = new Intent(this, (Class<?>) ImageGridActivity.class);
        intent.putExtra("TAKE", true);
        startActivityForResult(intent, 10002);
    }

    @Override // com.ido.common.base.BaseCoreActivity, com.ido.common.utils.PermissionUtil.RequestResult
    public void requestPermissionsSuccess(int i) {
        CommonLogUtil.d(TAG, "requestPermissionsSuccess: 权限申请成功" + i);
        if (10001 == i) {
            openChooser(10001);
        } else {
            toCamera();
        }
    }

    private void openChooser(int i) {
        startActivityForResult(new Intent(this, (Class<?>) ImageGridActivity.class), i);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        CommonLogUtil.d(TAG, "onActivityResult: " + i + AppInfo.DELIM + i2);
        if (i2 != 1004 || intent == null) {
            return;
        }
        ArrayList arrayList = null;
        try {
            arrayList = (ArrayList) intent.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        String str = ((ImageItem) arrayList.get(0)).path;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (i != 10001) {
            if (i != 10002) {
                return;
            }
            ((UserInfoPresenter) this.mPresenter).updateProfile(str);
        } else {
            CommonLogUtil.d(TAG, "onActivityResult: " + str);
            ((UserInfoPresenter) this.mPresenter).updateProfile(str);
        }
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void setUserName(String str) {
        this.mTvNameValue.setText(str);
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void onEmptyName() {
        NormalToast.showToast(getLanguageText(R.string.nickname_empty), 2000);
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void setAvatarUrl(String str) {
        ImageLoaderUtil.loadImgCircle(this.mIvAvatar, str, R.mipmap.ic_avatar_default);
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void setAvatarUrlSuccess(String str) {
        this.mIvAvatar.setImageBitmap(null);
        this.mIvAvatar.setBackground(null);
        ImageLoaderUtil.loadImgCircle(this.mIvAvatar, str, R.mipmap.ic_avatar_default);
        NormalToast.showToast(getLanguageText(R.string.logn_all_set_success_ios), 2000);
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void setAvatarUrlFailed(String str) {
        NormalToast.showToast(getLanguageText(R.string.logn_all_set_failed_ios), 2000);
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void setGender(int i) {
        if (i == 1) {
            this.mViewMeInfoSex.setEndText(LanguageUtil.getLanguageText(R.string.me_all_male_ios));
        } else if (i == 2) {
            this.mViewMeInfoSex.setEndText(LanguageUtil.getLanguageText(R.string.me_all_female_ios));
        }
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void setBirthday(String str) {
        this.mViewInfoAge.setEndText(str);
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void setEmail(String str) {
        this.mViewInfoEmail.setEndText(str);
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void setArea(String str) {
        CommonLogUtil.d(TAG, "AreaCode=" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            this.mViewInfoArea.setEndText(getResources().getIdentifier("country_".concat(str.trim()), "string", getPackageName()));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void setHeight(String str) {
        this.mViewInfoHeight.setEndText(str);
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void setWeight(String str) {
        this.mViewInfoWeight.setEndText(str);
    }

    @Override // com.ido.life.base.IBaseLoadingView
    public void showLoading() {
        showLoadingDialog();
    }

    @Override // com.ido.life.base.IBaseLoadingView
    public void dismissLoading() {
        dismissLoadingDialog();
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void showMessage(String str) {
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void showPickDialog(int i, String str) {
        UserInfoNumberSelectDialogFragment userInfoNumberSelectDialogFragmentNewInstance = UserInfoNumberSelectDialogFragment.newInstance(i, str);
        userInfoNumberSelectDialogFragmentNewInstance.show(getSupportFragmentManager());
        userInfoNumberSelectDialogFragmentNewInstance.setOnItemSelectedListener(new UserInfoNumberSelectDialogFragment.OnItemSelectedListener() { // from class: com.ido.life.module.user.userinfo.-$$Lambda$UserInfoActivity$jyb6dxvCxn9YWWC2XM6VdXchxFI
            @Override // com.ido.life.customview.UserInfoNumberSelectDialogFragment.OnItemSelectedListener
            public final void onItemSelected(int i2, String str2) {
                this.f$0.lambda$showPickDialog$5$UserInfoActivity(i2, str2);
            }
        });
    }

    public /* synthetic */ void lambda$showPickDialog$5$UserInfoActivity(int i, String str) {
        if (i == 1) {
            ((UserInfoPresenter) this.mPresenter).updateHeight(str, 1);
            return;
        }
        if (i == 2) {
            ((UserInfoPresenter) this.mPresenter).updateHeight(str, 2);
        } else if (i == 3) {
            ((UserInfoPresenter) this.mPresenter).updateWeight(str, 1);
        } else {
            if (i != 4) {
                return;
            }
            ((UserInfoPresenter) this.mPresenter).updateWeight(str, 2);
        }
    }

    @Override // com.ido.life.module.user.userinfo.IUserInfoView
    public void setBirthday(int[] iArr, int[] iArr2, int[] iArr3) {
        final boolean zHasLowIntervalCountry = LanguageUtil.hasLowIntervalCountry((String) SPUtils.get(Constants.CHOOSE_COUNTRY_CODE, ""));
        BirthdayDateDialogFragment birthdayDateDialogFragmentNewInstance = BirthdayDateDialogFragment.newInstance(iArr, iArr2, iArr3, zHasLowIntervalCountry ? getLanguageText(R.string.low_age_message) : "", zHasLowIntervalCountry);
        birthdayDateDialogFragmentNewInstance.setOnDateSelectedListener(new BirthdayDateDialogFragment.OnDateSelectedListener() { // from class: com.ido.life.module.user.userinfo.-$$Lambda$UserInfoActivity$5tzUbdRa5I77b8YqNRXyF5Cwj_Q
            @Override // com.ido.common.dialog.BirthdayDateDialogFragment.OnDateSelectedListener
            public final void onDateSelected(int i, int i2, int i3) {
                this.f$0.lambda$setBirthday$6$UserInfoActivity(zHasLowIntervalCountry, i, i2, i3);
            }
        });
        birthdayDateDialogFragmentNewInstance.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$setBirthday$6$UserInfoActivity(boolean z, int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(1, i);
        calendar.set(2, i2);
        calendar.add(2, -1);
        calendar.set(5, i3);
        String str = (String) SPUtils.get(Constants.CHOOSE_COUNTRY_CODE, "");
        String str2 = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "setBirthday: 拿到的生日 -- " + str2);
        int ageByBirthday = DateUtil.getAgeByBirthday(str2, DateUtil.DATE_FORMAT_YMD);
        if (z && ageByBirthday < 16 && LanguageUtil.hasLowIntervalCountry(str)) {
            showLowDialog(str2);
        } else {
            ((UserInfoPresenter) this.mPresenter).setBirthday(str2);
        }
    }

    private void showLowDialog(final String str) {
        CommBottomConfirmDialog.newInstance(getString(R.string.register_cloud_sync_title), getString(R.string.low_age_message), getString(R.string.me_all_sure_ios), getString(R.string.public_tip_cancel), true, false).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.userinfo.-$$Lambda$UserInfoActivity$5wGfV6GJZD7xMk7UswsKKZLIhWE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EventBusHelper.post(306);
            }
        }).setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.user.userinfo.-$$Lambda$UserInfoActivity$6rnTv8ELchT0hbMhDj18FqqgXTA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showLowDialog$8$UserInfoActivity(str, view);
            }
        }).show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showLowDialog$8$UserInfoActivity(String str, View view) {
        ((UserInfoPresenter) this.mPresenter).setBirthday(str);
    }
}