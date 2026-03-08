package com.ido.life.module.user.feedback;

import android.content.Intent;
import android.content.res.Resources;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.common.widget.viewgroup.FlowLayout;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.GlideImageLoader;
import com.ido.life.customview.NormalToast;
import com.ido.life.dialog.ChoosePhotoDialogFragment;
import com.ido.life.module.device.activity.CommonWebViewActivity;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeedbackActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u0005B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0011H\u0002J\b\u0010\u001b\u001a\u00020\u0017H\u0016J\b\u0010\u001c\u001a\u00020\u0011H\u0016J\b\u0010\u001d\u001a\u00020\u0011H\u0016J\b\u0010\u001e\u001a\u00020\bH\u0016J\b\u0010\u001f\u001a\u00020\bH\u0014J\u0018\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u0012H\u0016J\u0010\u0010!\u001a\n #*\u0004\u0018\u00010\"0\"H\u0016J\b\u0010$\u001a\u00020\u0017H\u0014J\b\u0010%\u001a\u00020\u0017H\u0014J\b\u0010&\u001a\u00020\u0017H\u0016J\b\u0010'\u001a\u00020(H\u0016J\u0012\u0010)\u001a\u00020\u00172\b\u0010*\u001a\u0004\u0018\u00010\u0011H\u0016J\"\u0010+\u001a\u00020\u00172\u0006\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020\b2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\u0012\u00100\u001a\u00020\u00172\b\u00101\u001a\u0004\u0018\u00010\u0019H\u0016J\u0010\u00102\u001a\u00020\u00172\u0006\u0010,\u001a\u00020\bH\u0002J\u0010\u00103\u001a\u00020\u00172\u0006\u00104\u001a\u00020\u0011H\u0002J\b\u00105\u001a\u00020\u0017H\u0002J\u0010\u00106\u001a\u00020\u00172\u0006\u0010,\u001a\u00020\bH\u0016J\u0010\u00107\u001a\u00020\u00172\u0006\u0010,\u001a\u00020\bH\u0016J\b\u00108\u001a\u00020\u0017H\u0002J\u0010\u00109\u001a\u00020\u00172\u0006\u0010:\u001a\u00020(H\u0016J\u0010\u0010;\u001a\u00020\u00172\u0006\u0010<\u001a\u00020\bH\u0016J\u0012\u0010=\u001a\u00020\u00172\b\u0010>\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010?\u001a\u00020\u0017H\u0016J\b\u0010@\u001a\u00020\u0017H\u0002J\b\u0010A\u001a\u00020\u0017H\u0002J\u0010\u0010B\u001a\u00020\u00172\u0006\u0010C\u001a\u00020\bH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u0014X\u0082.¢\u0006\u0004\n\u0002\u0010\u0015¨\u0006D"}, d2 = {"Lcom/ido/life/module/user/feedback/FeedbackActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/user/feedback/FeedbackPresenter;", "Lcom/ido/common/utils/PermissionUtil$RequestResult;", "Lcom/ido/life/module/user/feedback/IFeedbackView;", "Landroid/view/View$OnClickListener;", "()V", "ALBUM_REQUEST_CODE", "", "CAMERA_REQUEST_CODE", "MAX_IMAGE", "MAX_LENGTH_DES", "mFeedbackPicAdapter", "Lcom/ido/life/module/user/feedback/FeedbackPicAdapter;", "mFeedbackType", "mList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "mTitles", "", "[Ljava/lang/String;", "commitFeedBackSuccess", "", "createSearchKeyView", "Landroid/view/View;", "hot", "dismissLoading", "getContract", "getDes", "getFeedbackType", "getLayoutResId", "getPicList", "getView", "Landroid/widget/EditText;", "kotlin.jvm.PlatformType", "initData", "initLabelLanguage", "initViews", "isCheck", "", "notifyAdapter", "path", "onActivityResult", "requestCode", "resultCode", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Landroid/content/Intent;", "onClick", "v", "openChooser", "reSetType", "text", "requestCameraPermission", "requestPermissionsFail", "requestPermissionsSuccess", "requestStoragePermission", "setFeedbackEnable", "isClick", "setSubmitBg", "resourceId", "showError", "message", "showLoading", "toAddPic", "toCamera", "toDeletePic", "position", "app_release"}, k = 1, mv = {1, 1, 16})
public final class FeedbackActivity extends BaseActivity<FeedbackPresenter> implements PermissionUtil.RequestResult, IFeedbackView, View.OnClickListener {
    private HashMap _$_findViewCache;
    private FeedbackPicAdapter mFeedbackPicAdapter;
    private String[] mTitles;
    private final int ALBUM_REQUEST_CODE = 10001;
    private final int CAMERA_REQUEST_CODE = 10002;
    private final int MAX_LENGTH_DES = 1000;
    private final int MAX_IMAGE = 3;
    private ArrayList<String> mList = new ArrayList<>();
    private int mFeedbackType = -1;

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_feedback;
    }

    @Override // com.ido.common.base.BaseCoreActivity, com.ido.common.utils.PermissionUtil.RequestResult
    public void requestPermissionsFail(int requestCode) {
    }

    public static final /* synthetic */ FeedbackPresenter access$getMPresenter$p(FeedbackActivity feedbackActivity) {
        return (FeedbackPresenter) feedbackActivity.mPresenter;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(imagePicker, "imagePicker");
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(true);
        imagePicker.setSaveRectangle(true);
        imagePicker.setSelectLimit(1);
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        imagePicker.setFocusWidth((resources.getDisplayMetrics().widthPixels * 3) / 5);
        Resources resources2 = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources2, "resources");
        imagePicker.setFocusHeight((resources2.getDisplayMetrics().widthPixels * 3) / 5);
        imagePicker.setOutPutX(200);
        imagePicker.setOutPutY(200);
        imagePicker.setMultiMode(false);
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.initLayout(2);
        this.mTitleBar.setRightText(getLanguageText(R.string.help));
        this.mTitleBar.setRightTextColor(getResources().getColor(R.color.color_027AFF));
        this.mTitleBar.setTitle(getLanguageText(R.string.feedback));
        RegularTextView tv_fd_type_title = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_fd_type_title);
        Intrinsics.checkExpressionValueIsNotNull(tv_fd_type_title, "tv_fd_type_title");
        tv_fd_type_title.setText(getLanguageText(R.string.mine_question_type));
        RegularTextView tv_input_title = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_input_title);
        Intrinsics.checkExpressionValueIsNotNull(tv_input_title, "tv_input_title");
        tv_input_title.setText(getLanguageText(R.string.feedback_desc));
        EditText et_fd_des = (EditText) _$_findCachedViewById(com.ido.life.R.id.et_fd_des);
        Intrinsics.checkExpressionValueIsNotNull(et_fd_des, "et_fd_des");
        et_fd_des.setHint(getLanguageText(R.string.mine_question_suggestion_detailed));
        RegularTextView tv_contact = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_contact);
        Intrinsics.checkExpressionValueIsNotNull(tv_contact, "tv_contact");
        tv_contact.setText(getLanguageText(R.string.mine_contact_information));
        EditText et_contract = (EditText) _$_findCachedViewById(com.ido.life.R.id.et_contract);
        Intrinsics.checkExpressionValueIsNotNull(et_contract, "et_contract");
        et_contract.setHint(getLanguageText(R.string.please_input_contact_play));
        RegularTextView tv_log = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_log);
        Intrinsics.checkExpressionValueIsNotNull(tv_log, "tv_log");
        tv_log.setText(getLanguageText(R.string.feedback_help_us_tip));
        TextView tv_submit = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_submit);
        Intrinsics.checkExpressionValueIsNotNull(tv_submit, "tv_submit");
        tv_submit.setText(getLanguageText(R.string.mine_commit));
        String[] stringArray = getResources().getStringArray(R.array.feedback_type_array);
        Intrinsics.checkExpressionValueIsNotNull(stringArray, "resources.getStringArray…rray.feedback_type_array)");
        this.mTitles = stringArray;
        ((FlowLayout) _$_findCachedViewById(com.ido.life.R.id.fl_fd_type)).removeAllViews();
        String[] strArr = this.mTitles;
        if (strArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTitles");
        }
        for (String str : strArr) {
            ((FlowLayout) _$_findCachedViewById(com.ido.life.R.id.fl_fd_type)).addView(createSearchKeyView(str));
        }
        if (this.mFeedbackType > 0) {
            String[] strArr2 = this.mTitles;
            if (strArr2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTitles");
            }
            if (strArr2.length >= this.mFeedbackType) {
                String[] strArr3 = this.mTitles;
                if (strArr3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTitles");
                }
                reSetType(strArr3[this.mFeedbackType - 1]);
            }
        }
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        this.mList.add("");
        this.mTitleBar.initLayout(2);
        this.mTitleBar.setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.user.feedback.FeedbackActivity.initViews.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Intent intent = new Intent(FeedbackActivity.this, (Class<?>) CommonWebViewActivity.class);
                intent.putExtra("type", 6);
                FeedbackActivity.this.startActivity(intent);
            }
        });
        ((EditText) _$_findCachedViewById(com.ido.life.R.id.et_fd_des)).setHorizontallyScrolling(false);
        LayoutInflater layoutInflater = getLayoutInflater();
        Intrinsics.checkExpressionValueIsNotNull(layoutInflater, "layoutInflater");
        this.mFeedbackPicAdapter = new FeedbackPicAdapter(this.mList, this, layoutInflater);
        RecyclerView rv_feedback_pic = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.rv_feedback_pic);
        Intrinsics.checkExpressionValueIsNotNull(rv_feedback_pic, "rv_feedback_pic");
        FeedbackPicAdapter feedbackPicAdapter = this.mFeedbackPicAdapter;
        if (feedbackPicAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFeedbackPicAdapter");
        }
        rv_feedback_pic.setAdapter(feedbackPicAdapter);
        ((EditText) _$_findCachedViewById(com.ido.life.R.id.et_fd_des)).addTextChangedListener(new TextWatcher() { // from class: com.ido.life.module.user.feedback.FeedbackActivity.initViews.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Intrinsics.checkParameterIsNotNull(s, "s");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Intrinsics.checkParameterIsNotNull(s, "s");
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                Intrinsics.checkParameterIsNotNull(s, "s");
                String str = String.valueOf(s.length()) + "/" + FeedbackActivity.this.MAX_LENGTH_DES;
                RegularTextView tv_fd_length = (RegularTextView) FeedbackActivity.this._$_findCachedViewById(com.ido.life.R.id.tv_fd_length);
                Intrinsics.checkExpressionValueIsNotNull(tv_fd_length, "tv_fd_length");
                tv_fd_length.setText(str);
                FeedbackPresenter feedbackPresenterAccess$getMPresenter$p = FeedbackActivity.access$getMPresenter$p(FeedbackActivity.this);
                if (feedbackPresenterAccess$getMPresenter$p != null) {
                    feedbackPresenterAccess$getMPresenter$p.verify();
                }
            }
        });
    }

    private final View createSearchKeyView(final String hot) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        TextView textView = new TextView(this);
        textView.setText(hot);
        int iDip2px = DipPixelUtil.dip2px(13.0f);
        int iDip2px2 = DipPixelUtil.dip2px(6.0f);
        int iDip2px3 = DipPixelUtil.dip2px(4.0f);
        textView.setPadding(iDip2px3, iDip2px2, iDip2px3, iDip2px2);
        textView.setGravity(17);
        textView.setBackground(ResourceUtil.getDrawable(R.drawable.selector_fd_type_btn));
        textView.setTextColor(ResourceUtil.getColor(R.color.color_B3_FFFFFF));
        layoutParams.rightMargin = iDip2px;
        layoutParams.bottomMargin = iDip2px;
        textView.setLayoutParams(layoutParams);
        textView.setMaxLines(1);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.user.feedback.FeedbackActivity.createSearchKeyView.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackActivity.this.reSetType(hot);
            }
        });
        return textView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reSetType(String text) {
        String[] strArr = this.mTitles;
        if (strArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTitles");
        }
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            View childAt = ((FlowLayout) _$_findCachedViewById(com.ido.life.R.id.fl_fd_type)).getChildAt(i);
            if (childAt == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
            }
            TextView textView = (TextView) childAt;
            if (TextUtils.equals(text, textView.getText())) {
                this.mFeedbackType = i + 1;
                textView.setTextColor(getColor(R.color.color_line_user_green));
                textView.setBackground(getDrawable(R.drawable.shape_rectangle_line_theme_bg_white_6_corner));
                FeedbackPresenter feedbackPresenter = (FeedbackPresenter) this.mPresenter;
                if (feedbackPresenter != null) {
                    feedbackPresenter.verify();
                }
            } else {
                textView.setTextColor(getColor(R.color.color_line_fd_type_gray));
                textView.setBackground(getDrawable(R.drawable.shape_rectangle_line_gray_bg_white_6_corner));
            }
        }
    }

    private final void toAddPic() {
        final ChoosePhotoDialogFragment choosePhotoDialogFragmentNewInstance = ChoosePhotoDialogFragment.newInstance(getLanguageText(R.string.device_user_album), getLanguageText(R.string.device_user_take_photo));
        choosePhotoDialogFragmentNewInstance.show(getSupportFragmentManager());
        choosePhotoDialogFragmentNewInstance.setLeftClickListener(new View.OnClickListener() { // from class: com.ido.life.module.user.feedback.FeedbackActivity.toAddPic.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackActivity.this.requestCameraPermission();
                choosePhotoDialogFragmentNewInstance.dismissAllowingStateLoss();
            }
        });
        choosePhotoDialogFragmentNewInstance.setRightClickListener(new View.OnClickListener() { // from class: com.ido.life.module.user.feedback.FeedbackActivity.toAddPic.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackActivity.this.requestStoragePermission();
                choosePhotoDialogFragmentNewInstance.dismissAllowingStateLoss();
            }
        });
    }

    private final void toDeletePic(int position) {
        if (this.mList.size() > position) {
            this.mList.remove(position);
            Iterator<String> it = this.mList.iterator();
            Intrinsics.checkExpressionValueIsNotNull(it, "mList.iterator()");
            while (it.hasNext()) {
                String next = it.next();
                if (next == null || next.length() == 0) {
                    it.remove();
                }
            }
            this.mList.add("");
            FeedbackPicAdapter feedbackPicAdapter = this.mFeedbackPicAdapter;
            if (feedbackPicAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFeedbackPicAdapter");
            }
            feedbackPicAdapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestStoragePermission() {
        String[] storagePermission = PermissionUtil.getStoragePermission();
        if (checkSelfPermission((String[]) Arrays.copyOf(storagePermission, storagePermission.length))) {
            openChooser(this.ALBUM_REQUEST_CODE);
        } else {
            requestPermissions(this.ALBUM_REQUEST_CODE, "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE");
        }
    }

    private final void openChooser(int requestCode) {
        startActivityForResult(new Intent(this, (Class<?>) ImageGridActivity.class), requestCode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestCameraPermission() {
        if (checkSelfPermission("android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE")) {
            toCamera();
        } else {
            requestPermissions(this.CAMERA_REQUEST_CODE, "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE");
        }
    }

    private final void toCamera() {
        Intent intent = new Intent(this, (Class<?>) ImageGridActivity.class);
        intent.putExtra("TAKE", true);
        startActivityForResult(intent, this.CAMERA_REQUEST_CODE);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        FeedbackPresenter feedbackPresenter;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != 1004 || data == null) {
            return;
        }
        Serializable serializableExtra = data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
        if (serializableExtra == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.ArrayList<com.lzy.imagepicker.bean.ImageItem>");
        }
        ArrayList arrayList = (ArrayList) serializableExtra;
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        String str = ((ImageItem) arrayList.get(0)).path;
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return;
        }
        if (requestCode == this.ALBUM_REQUEST_CODE) {
            FeedbackPresenter feedbackPresenter2 = (FeedbackPresenter) this.mPresenter;
            if (feedbackPresenter2 != null) {
                feedbackPresenter2.updateImage(str);
                return;
            }
            return;
        }
        if (requestCode != this.CAMERA_REQUEST_CODE || (feedbackPresenter = (FeedbackPresenter) this.mPresenter) == null) {
            return;
        }
        feedbackPresenter.updateImage(str);
    }

    @Override // com.ido.common.base.BaseCoreActivity, com.ido.common.utils.PermissionUtil.RequestResult
    public void requestPermissionsSuccess(int requestCode) {
        int i = this.ALBUM_REQUEST_CODE;
        if (i == requestCode) {
            openChooser(i);
        } else {
            toCamera();
        }
    }

    @Override // com.ido.life.module.user.feedback.IFeedbackView
    public void setFeedbackEnable(boolean isClick) {
        TextView tv_submit = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_submit);
        Intrinsics.checkExpressionValueIsNotNull(tv_submit, "tv_submit");
        tv_submit.setEnabled(isClick);
    }

    @Override // com.ido.life.module.user.feedback.IFeedbackView
    public String getDes() {
        EditText et_fd_des = (EditText) _$_findCachedViewById(com.ido.life.R.id.et_fd_des);
        Intrinsics.checkExpressionValueIsNotNull(et_fd_des, "et_fd_des");
        return et_fd_des.getText().toString();
    }

    @Override // com.ido.life.module.user.feedback.IFeedbackView
    public void commitFeedBackSuccess() {
        NormalToast.showToast(getLanguageText(R.string.commit_feedback_success), 2000);
        WaitingDialog.hideDialog();
        supportFinishAfterTransition();
    }

    @Override // com.ido.life.module.user.feedback.IFeedbackView
    public void showError(String message) {
        NormalToast.showToast(message, 2000);
    }

    @Override // com.ido.life.module.user.feedback.IFeedbackView
    public ArrayList<String> getPicList() {
        return this.mList;
    }

    @Override // com.ido.life.module.user.feedback.IFeedbackView
    public boolean isCheck() {
        CheckBox cb_log = (CheckBox) _$_findCachedViewById(com.ido.life.R.id.cb_log);
        Intrinsics.checkExpressionValueIsNotNull(cb_log, "cb_log");
        return cb_log.isChecked();
    }

    @Override // com.ido.life.module.user.feedback.IFeedbackView
    public void setSubmitBg(int resourceId) {
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_submit)).setBackgroundResource(resourceId);
    }

    @Override // com.ido.life.module.user.feedback.IFeedbackView
    /* JADX INFO: renamed from: getFeedbackType, reason: from getter */
    public int getMFeedbackType() {
        return this.mFeedbackType;
    }

    @Override // com.ido.life.module.user.feedback.IFeedbackView
    public void notifyAdapter(String path) {
        String str = path;
        if (str == null || str.length() == 0) {
            return;
        }
        ArrayList<String> arrayList = this.mList;
        if (!(arrayList == null || arrayList.isEmpty())) {
            Iterator<String> it = this.mList.iterator();
            Intrinsics.checkExpressionValueIsNotNull(it, "mList.iterator()");
            while (it.hasNext()) {
                String next = it.next();
                if (next == null || next.length() == 0) {
                    it.remove();
                }
            }
        }
        this.mList.add(path);
        if (this.mList.size() < this.MAX_IMAGE) {
            this.mList.add("");
        }
        FeedbackPicAdapter feedbackPicAdapter = this.mFeedbackPicAdapter;
        if (feedbackPicAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFeedbackPicAdapter");
        }
        feedbackPicAdapter.notifyDataSetChanged();
    }

    @Override // com.ido.life.module.user.feedback.IFeedbackView
    public String getContract() {
        EditText et_contract = (EditText) _$_findCachedViewById(com.ido.life.R.id.et_contract);
        Intrinsics.checkExpressionValueIsNotNull(et_contract, "et_contract");
        return et_contract.getText().toString();
    }

    @Override // com.ido.life.module.user.feedback.IFeedbackView
    public EditText getView() {
        return (EditText) _$_findCachedViewById(com.ido.life.R.id.et_contract);
    }

    @Override // com.ido.life.base.IBaseLoadingView
    public void showLoading() {
        showLoadingDialog();
    }

    @Override // com.ido.life.base.IBaseLoadingView
    public void dismissLoading() {
        dismissLoadingDialog();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_submit) {
            FeedbackPresenter feedbackPresenter = (FeedbackPresenter) this.mPresenter;
            if (feedbackPresenter != null) {
                feedbackPresenter.commitSuggest();
                return;
            }
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_item) {
            if (v.getTag() instanceof Integer) {
                Object tag = v.getTag();
                if (tag == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                }
                int iIntValue = ((Integer) tag).intValue();
                if (iIntValue < this.mList.size()) {
                    String str = this.mList.get(iIntValue);
                    if (str == null || str.length() == 0) {
                        toAddPic();
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.feedback_iv_delete && (v.getTag() instanceof Integer)) {
            Object tag2 = v.getTag();
            if (tag2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
            int iIntValue2 = ((Integer) tag2).intValue();
            if (iIntValue2 < this.mList.size()) {
                String str2 = this.mList.get(iIntValue2);
                if (str2 == null || str2.length() == 0) {
                    return;
                }
                toDeletePic(iIntValue2);
            }
        }
    }
}