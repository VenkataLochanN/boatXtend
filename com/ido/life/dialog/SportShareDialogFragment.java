package com.ido.life.dialog;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.BitmapUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.util.ShareUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportShareDialogFragment extends BaseDialogFragment {
    private CommonRecyclerViewAdapter<ResolveInfo> mAdapter;

    @BindView(R.id.ll_share_layout)
    LinearLayout mLlShareLayout;
    private OnShareChooseListener mOnShareChooseListener;

    @BindView(R.id.recycler_share)
    RecyclerView mRecyclerShare;
    private Uri mUri = null;
    private List<ResolveInfo> mAppInfoList = new ArrayList();

    public interface OnShareChooseListener {
        void onSharePlatChoose(int i);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_sport_share;
    }

    public static SportShareDialogFragment newInstance() {
        Bundle bundle = new Bundle();
        SportShareDialogFragment sportShareDialogFragment = new SportShareDialogFragment();
        sportShareDialogFragment.setStyle(1, 2131886083);
        sportShareDialogFragment.setArguments(bundle);
        return sportShareDialogFragment;
    }

    public void setOnShareResultListener(OnShareChooseListener onShareChooseListener) {
        this.mOnShareChooseListener = onShareChooseListener;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        super.initUI(view);
        if (getDialog() != null && getDialog().getWindow() != null) {
            Window window = getDialog().getWindow();
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 80;
            attributes.width = -1;
            attributes.height = getActivity().getResources().getDimensionPixelSize(R.dimen.sw_dp_150);
            window.setAttributes(attributes);
        }
        this.mAdapter = new CommonRecyclerViewAdapter<ResolveInfo>(getActivity(), R.layout.item_health_report_share_layout, this.mAppInfoList) { // from class: com.ido.life.dialog.SportShareDialogFragment.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, ResolveInfo resolveInfo, int i) {
                ImageView imageView = (ImageView) commonRecyclerViewHolder.getView(R.id.img_app_icon);
                TextView textView = (TextView) commonRecyclerViewHolder.getView(R.id.tv_app_name);
                if (i != 0) {
                    imageView.setImageDrawable(((ResolveInfo) SportShareDialogFragment.this.mAppInfoList.get(i)).loadIcon(SportShareDialogFragment.this.getActivity().getPackageManager()));
                    textView.setText(((ResolveInfo) SportShareDialogFragment.this.mAppInfoList.get(i)).loadLabel(SportShareDialogFragment.this.getActivity().getPackageManager()));
                } else {
                    imageView.setImageDrawable(SportShareDialogFragment.this.getResources().getDrawable(R.mipmap.ic_sport_share_save));
                    textView.setText(LanguageUtil.getLanguageText(R.string.sport_share_save_img));
                }
            }
        };
        ResourceUtil.getDimens(R.dimen.sw_dp_20);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/*");
        List<ResolveInfo> listQueryIntentActivities = getActivity().getPackageManager().queryIntentActivities(intent, 65536);
        this.mAppInfoList.add(new ResolveInfo());
        if (listQueryIntentActivities != null) {
            this.mAppInfoList.addAll(listQueryIntentActivities);
        }
        this.mRecyclerShare.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(new MultiItemTypeAdapterForRV.OnItemClickListener() { // from class: com.ido.life.dialog.SportShareDialogFragment.2
            @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
            public boolean onItemLongClick(View view2, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }

            @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
            public void onItemClick(View view2, RecyclerView.ViewHolder viewHolder, int i) {
                SportShareDialogFragment.this.toShare(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toShare(int i) {
        if (i == 0) {
            BitmapUtil.saveBitmap(getActivity(), BitmapUtil.readBitmapFromFileDescriptor(ShareUtil.path), Calendar.getInstance().getTime().toString());
            OnShareChooseListener onShareChooseListener = this.mOnShareChooseListener;
            if (onShareChooseListener != null) {
                onShareChooseListener.onSharePlatChoose(i);
            }
            dismissAllowingStateLoss();
            return;
        }
        try {
            if (this.mUri == null) {
                this.mUri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), BitmapFactory.decodeFile(ShareUtil.path), "IMG" + Calendar.getInstance().getTime(), (String) null));
            }
            ResolveInfo resolveInfo = this.mAppInfoList.get(i);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("image/*");
            intent.putExtra("android.intent.extra.STREAM", this.mUri);
            intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            getActivity().startActivity(intent);
            if (this.mOnShareChooseListener != null) {
                this.mOnShareChooseListener.onSharePlatChoose(i);
            }
            dismissAllowingStateLoss();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void shareImage(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "IMG" + Calendar.getInstance().getTime(), (String) null));
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/*");
        intent.putExtra("android.intent.extra.STREAM", uri);
        List<ResolveInfo> listQueryIntentActivities = getActivity().getPackageManager().queryIntentActivities(intent, 65536);
        if (listQueryIntentActivities == null || listQueryIntentActivities.size() <= 0) {
            return;
        }
        getActivity().startActivity(intent);
    }

    @Override // com.ido.common.base.BaseDialogFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mLlShareLayout.measure(0, getActivity().getResources().getDimensionPixelSize(R.dimen.sw_dp_150));
        int measuredHeight = this.mLlShareLayout.getMeasuredHeight();
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(-1, measuredHeight);
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            dismissAllowingStateLoss();
        }
    }

    @OnClick({R.id.tv_cancel})
    public void toCancel(View view) {
        share(R.id.tv_cancel);
    }

    protected void share(int i) {
        OnShareChooseListener onShareChooseListener = this.mOnShareChooseListener;
        if (onShareChooseListener != null) {
            onShareChooseListener.onSharePlatChoose(i);
            dismissAllowingStateLoss();
        }
    }
}