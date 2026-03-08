package com.ido.life.dialog;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.life.constants.Constants;
import com.ido.life.customview.HealthReportCircleImageView;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.util.ShareUtil;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HealthReportShareDialogFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 $2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002$%B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u000fH\u0014J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0014J\u0012\u0010\u0017\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0016H\u0016J\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J$\u0010\u001c\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\u000fH\u0016J$\u0010 \u001a\u00020!2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\u000fH\u0016J\u0010\u0010\"\u001a\u00020\u00142\b\u0010#\u001a\u0004\u0018\u00010\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/ido/life/dialog/HealthReportShareDialogFragment;", "Lcom/ido/common/base/BaseDialogFragment;", "Landroid/view/View$OnClickListener;", "Lcom/ido/life/customview/recyclerview/MultiItemTypeAdapterForRV$OnItemClickListener;", "()V", "TAG", "", "mAdapter", "Lcom/ido/life/customview/recyclerview/CommonRecyclerViewAdapter;", "Landroid/content/pm/ResolveInfo;", "mAppInfoList", "", "mOnShareChooseListener", "Lcom/ido/life/dialog/HealthReportShareDialogFragment$OnShareChooseListener;", "mPadding", "", "mUri", "Landroid/net/Uri;", "getLayoutResId", "initUI", "", "view", "Landroid/view/View;", "onClick", "v", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onItemClick", "holder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "position", "onItemLongClick", "", "setOnShareResultListener", "onShareResultListener", "Companion", "OnShareChooseListener", "app_release"}, k = 1, mv = {1, 1, 16})
public final class HealthReportShareDialogFragment extends BaseDialogFragment implements View.OnClickListener, MultiItemTypeAdapterForRV.OnItemClickListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private HashMap _$_findViewCache;
    private CommonRecyclerViewAdapter<ResolveInfo> mAdapter;
    private OnShareChooseListener mOnShareChooseListener;
    private int mPadding;
    private Uri mUri;
    private final String TAG = "HealthReportShareDialogFragment";
    private List<ResolveInfo> mAppInfoList = new ArrayList();

    /* JADX INFO: compiled from: HealthReportShareDialogFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/ido/life/dialog/HealthReportShareDialogFragment$OnShareChooseListener;", "", "onSharePlatChoose", "", "id", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface OnShareChooseListener {
        void onSharePlatChoose(int id);
    }

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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View viewFindViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_health_report_share;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    public final void setOnShareResultListener(OnShareChooseListener onShareResultListener) {
        this.mOnShareChooseListener = onShareResultListener;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        PackageManager packageManager;
        Resources resources;
        Resources resources2;
        View decorView;
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.initUI(view);
        List<ResolveInfo> listQueryIntentActivities = null;
        if (getDialog() != null) {
            Dialog dialog = getDialog();
            if ((dialog != null ? dialog.getWindow() : null) != null) {
                Dialog dialog2 = getDialog();
                Window window = dialog2 != null ? dialog2.getWindow() : null;
                if (window != null && (decorView = window.getDecorView()) != null) {
                    decorView.setPadding(0, 0, 0, 0);
                }
                WindowManager.LayoutParams attributes = window != null ? window.getAttributes() : null;
                if (attributes != null) {
                    attributes.gravity = 80;
                }
                if (attributes != null) {
                    attributes.width = -1;
                }
                if (attributes != null) {
                    Context context = getContext();
                    attributes.height = ((context == null || (resources2 = context.getResources()) == null) ? null : Integer.valueOf(resources2.getDimensionPixelSize(R.dimen.sw_dp_300))).intValue();
                }
                if (window != null) {
                    window.setAttributes(attributes);
                }
            }
        }
        this.mAdapter = new CommonRecyclerViewAdapter<ResolveInfo>(getContext(), R.layout.item_health_report_share_layout, this.mAppInfoList) { // from class: com.ido.life.dialog.HealthReportShareDialogFragment.initUI.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder holder, ResolveInfo t, int position) {
                HealthReportCircleImageView healthReportCircleImageView = holder != null ? (HealthReportCircleImageView) holder.getView(R.id.img_app_icon) : null;
                TextView textView = holder != null ? (TextView) holder.getView(R.id.tv_app_name) : null;
                ResolveInfo resolveInfo = (ResolveInfo) HealthReportShareDialogFragment.this.mAppInfoList.get(position);
                if (position == 0) {
                    if (healthReportCircleImageView != null) {
                        healthReportCircleImageView.setImageDrawable(IdoApp.getAppContext().getDrawable(R.mipmap.report_save_img));
                    }
                    if (textView != null) {
                        textView.setText(HealthReportShareDialogFragment.this.getText(R.string.sport_share_save_img));
                        return;
                    }
                    return;
                }
                if (healthReportCircleImageView != null) {
                    Context context2 = HealthReportShareDialogFragment.this.getContext();
                    healthReportCircleImageView.setImageDrawable(resolveInfo.loadIcon(context2 != null ? context2.getPackageManager() : null));
                }
                if (textView != null) {
                    Context context3 = HealthReportShareDialogFragment.this.getContext();
                    textView.setText(resolveInfo.loadLabel(context3 != null ? context3.getPackageManager() : null));
                }
            }
        };
        Context context2 = getContext();
        Integer numValueOf = (context2 == null || (resources = context2.getResources()) == null) ? null : Integer.valueOf(resources.getDimensionPixelSize(R.dimen.sw_dp_20));
        if (numValueOf == null) {
            Intrinsics.throwNpe();
        }
        this.mPadding = numValueOf.intValue();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/*");
        Context context3 = getContext();
        if (context3 != null && (packageManager = context3.getPackageManager()) != null) {
            listQueryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        }
        List<ResolveInfo> list = listQueryIntentActivities;
        if (!(list == null || list.isEmpty())) {
            this.mAppInfoList.add(new ResolveInfo());
            this.mAppInfoList.addAll(list);
        }
        RecyclerView recycler_share = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_share);
        Intrinsics.checkExpressionValueIsNotNull(recycler_share, "recycler_share");
        recycler_share.setAdapter(this.mAdapter);
        CommonRecyclerViewAdapter<ResolveInfo> commonRecyclerViewAdapter = this.mAdapter;
        if (commonRecyclerViewAdapter != null) {
            commonRecyclerViewAdapter.setOnItemClickListener(this);
        }
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_cancel)).setOnClickListener(this);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkParameterIsNotNull(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == 2) {
            dismissAllowingStateLoss();
        }
    }

    /* JADX INFO: compiled from: HealthReportShareDialogFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/ido/life/dialog/HealthReportShareDialogFragment$Companion;", "", "()V", "newInstance", "Lcom/ido/life/dialog/HealthReportShareDialogFragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final HealthReportShareDialogFragment newInstance() {
            Bundle bundle = new Bundle();
            HealthReportShareDialogFragment healthReportShareDialogFragment = new HealthReportShareDialogFragment();
            healthReportShareDialogFragment.setStyle(1, 2131886083);
            healthReportShareDialogFragment.setArguments(bundle);
            return healthReportShareDialogFragment;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v == null || v.getId() != R.id.tv_cancel) {
            return;
        }
        dismissAllowingStateLoss();
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        try {
            if (position == 0) {
                EventBusHelper.post(Constants.EventConstants.EVENT_REPORT_SAVE_SCREEN_PHOTO);
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "点击保存周报 发送activity保存的通知");
                dismissAllowingStateLoss();
                return;
            }
            if (this.mUri == null) {
                Context context = getContext();
                ContentResolver contentResolver = context != null ? context.getContentResolver() : null;
                Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(ShareUtil.path);
                Context context2 = getContext();
                this.mUri = Uri.parse(MediaStore.Images.Media.insertImage(contentResolver, bitmapDecodeFile, context2 != null ? context2.getPackageName() : null, (String) null));
            }
            ResolveInfo resolveInfo = this.mAppInfoList.get(position);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("image/*");
            intent.putExtra("android.intent.extra.STREAM", this.mUri);
            intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            Context context3 = getContext();
            if (context3 != null) {
                context3.startActivity(intent);
            }
            dismissAllowingStateLoss();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}