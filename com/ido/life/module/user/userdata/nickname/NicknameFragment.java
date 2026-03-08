package com.ido.life.module.user.userdata.nickname;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.OnClick;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.ImageLoaderUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.base.BaseFragment;
import com.ido.life.base.GlideImageLoader;
import com.ido.life.customview.NormalToast;
import com.ido.life.database.model.UserInfo;
import com.ido.life.dialog.ChoosePhotoDialogFragment;
import com.ido.life.module.user.userdata.nickname.NicknameContract;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.PhoneUtil;
import com.ido.life.util.RunTimeUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class NicknameFragment extends BaseFragment implements NicknameContract.View, PermissionUtil.RequestResult {
    private static final int ALBUM_REQUEST_CODE = 10001;
    static final int CAMERA_REQUEST_CODE = 10002;
    private static final String TAG = "NicknameFragment";
    private final int OUTPUT_FILEXY = 200;
    private final int SELECT_FILE_SIZE = 1;

    @BindView(R.id.et_nickname)
    EditText mEtNickname;
    boolean mIsLocalAvatar;

    @BindView(R.id.iv_register_avatar)
    ImageView mIvAvatar;

    @BindView(R.id.iv_forward)
    ImageButton mIvForward;
    private OnChangeListener mOnChangeListener;
    String mPathAvatar;
    private NicknameContract.Presenter mPresenter;

    @BindView(R.id.tv_nickname_tip)
    TextView mTvNicknameTip;

    @BindView(R.id.tv_title_nickname)
    TextView mTvTitleNickname;

    public interface OnChangeListener {
        void onForward();
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_nickname;
    }

    @Override // com.ido.common.utils.PermissionUtil.RequestResult
    public void requestPermissionsFail(int i) {
    }

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.mOnChangeListener = onChangeListener;
    }

    public static NicknameFragment newInstance() {
        Bundle bundle = new Bundle();
        NicknameFragment nicknameFragment = new NicknameFragment();
        nicknameFragment.setArguments(bundle);
        return nicknameFragment;
    }

    @Override // com.ido.life.module.user.userdata.nickname.NicknameContract.View
    public void changeForwardEnable(boolean z) {
        CommonLogUtil.d(TAG, "changeForwardEnable: " + z);
        this.mIvForward.setEnabled(z);
    }

    @Override // com.ido.life.module.user.userdata.nickname.NicknameContract.View
    public void setAvatar(String str) {
        ImageLoaderUtil.loadCircleImage(this.mIvAvatar, str, R.mipmap.ic_avatar_default);
    }

    @Override // com.ido.life.module.user.userdata.nickname.NicknameContract.View
    public void saveAvatarPath(String str, boolean z) {
        this.mPathAvatar = str;
        this.mIsLocalAvatar = z;
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void initView() {
        super.initView();
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo == null) {
            changeForwardEnable(false);
        } else {
            this.mEtNickname.setText(userInfoQueryUserInfo.getEmail());
            changeForwardEnable(true);
        }
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
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
        imagePicker.setOutPutX(200);
        imagePicker.setOutPutY(200);
        imagePicker.setMultiMode(false);
        this.mPresenter = new NicknamePresenter(this);
        this.mEtNickname.addTextChangedListener(new TextWatcher() { // from class: com.ido.life.module.user.userdata.nickname.NicknameFragment.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    NicknameFragment.this.changeForwardEnable(true);
                } else {
                    NicknameFragment.this.changeForwardEnable(false);
                }
            }
        });
    }

    @OnClick({R.id.iv_register_avatar})
    public void toUpdateAvatar(View view) {
        setAvatar2(view, getFragmentManager());
    }

    public void setAvatar2(View view, FragmentManager fragmentManager) {
        final ChoosePhotoDialogFragment choosePhotoDialogFragmentNewInstance = ChoosePhotoDialogFragment.newInstance(ResourceUtil.getString(R.string.device_user_album), ResourceUtil.getString(R.string.device_user_take_photo));
        choosePhotoDialogFragmentNewInstance.show(fragmentManager);
        choosePhotoDialogFragmentNewInstance.setLeftClickListener(new View.OnClickListener() { // from class: com.ido.life.module.user.userdata.nickname.-$$Lambda$NicknameFragment$0ruLt3h6WedV4x3TEu_XXZxzdOE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$setAvatar2$0$NicknameFragment(choosePhotoDialogFragmentNewInstance, view2);
            }
        });
        choosePhotoDialogFragmentNewInstance.setRightClickListener(new View.OnClickListener() { // from class: com.ido.life.module.user.userdata.nickname.-$$Lambda$NicknameFragment$ivj3qaZVZ7p__3xkzwQKPLnCPuU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$setAvatar2$1$NicknameFragment(choosePhotoDialogFragmentNewInstance, view2);
            }
        });
    }

    public /* synthetic */ void lambda$setAvatar2$0$NicknameFragment(ChoosePhotoDialogFragment choosePhotoDialogFragment, View view) {
        CommonLogUtil.d(TAG, "onClick: ");
        requestCameraPermission();
        choosePhotoDialogFragment.dismissAllowingStateLoss();
    }

    public /* synthetic */ void lambda$setAvatar2$1$NicknameFragment(ChoosePhotoDialogFragment choosePhotoDialogFragment, View view) {
        requestStoragePermission();
        choosePhotoDialogFragment.dismissAllowingStateLoss();
    }

    private void requestStoragePermission() {
        if (checkSelfPermission(PermissionUtil.getStoragePermission())) {
            openChooser(10001);
        } else {
            requestPermissions(10001, this, PermissionUtil.getStoragePermission());
        }
    }

    private void requestCameraPermission() {
        if (checkSelfPermission(PermissionUtil.getCameraPermission())) {
            toCamera();
        } else {
            requestPermissions(10002, this, PermissionUtil.getCameraPermission());
        }
    }

    private void toCamera() {
        Intent intent = new Intent(IdoApp.getAppContext(), (Class<?>) ImageGridActivity.class);
        intent.putExtra("TAKE", true);
        startActivityForResult(intent, 10002);
    }

    @Override // com.ido.common.utils.PermissionUtil.RequestResult
    public void requestPermissionsSuccess(int i) {
        CommonLogUtil.d(TAG, "requestPermissionsSuccess: 权限申请成功" + i);
        if (10001 == i) {
            openChooser(10001);
        } else if (10002 == i) {
            toCamera();
        }
    }

    private void openChooser(int i) {
        startActivityForResult(new Intent(IdoApp.getAppContext(), (Class<?>) ImageGridActivity.class), i);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        ArrayList arrayList;
        super.onActivityResult(i, i2, intent);
        CommonLogUtil.d(TAG, "onActivityResult: " + i + AppInfo.DELIM + i2);
        if (i2 != 1004 || intent == null || (arrayList = (ArrayList) intent.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS)) == null || arrayList.size() == 0) {
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
            this.mPresenter.updateProfile(str);
        } else {
            CommonLogUtil.d(TAG, "onActivityResult: " + str);
            this.mPresenter.updateProfile(str);
        }
    }

    @OnClick({R.id.iv_forward})
    public void toUpdateNickname(View view) {
        PhoneUtil.hideKeyboard(getActivity());
        this.mPresenter.checkNicknameSize(this.mEtNickname.getText().toString(), this.mPathAvatar, this.mIsLocalAvatar);
    }

    @Override // com.ido.common.base.BaseCoreFragment
    public void onInVisible() {
        super.onInVisible();
    }

    @Override // com.ido.life.module.user.userdata.nickname.NicknameContract.View
    public void toForward() {
        OnChangeListener onChangeListener = this.mOnChangeListener;
        if (onChangeListener != null) {
            onChangeListener.onForward();
        }
    }

    @Override // com.ido.life.module.user.userdata.nickname.NicknameContract.View
    public void showLoading() {
        WaitingDialog.showDialog(getActivity());
    }

    @Override // com.ido.life.module.user.userdata.nickname.NicknameContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.userdata.nickname.NicknameContract.View
    public void showMessage(String str) {
        NormalToast.showToast(str);
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(NicknameContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}