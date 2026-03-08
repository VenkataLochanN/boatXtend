package com.ido.life.module.user.healthreport;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.BitmapUtil;
import com.ido.common.utils.ImageLoaderUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.customview.HealthReportCircleImageView;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserMedalInfo;
import com.ido.life.enums.UserModelEnum;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.UnitUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: UserMedalShareDialog.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J$\u0010\u0018\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J$\u0010\u001e\u001a\u00020\u001f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u001a\u0010 \u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00172\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006#"}, d2 = {"Lcom/ido/life/module/user/healthreport/UserMedalShareDialog;", "Landroid/app/Dialog;", "Landroid/view/View$OnClickListener;", "Lcom/ido/life/customview/recyclerview/MultiItemTypeAdapterForRV$OnItemClickListener;", "context", "Landroid/content/Context;", "modalInfo", "Lcom/ido/life/database/model/UserMedalInfo;", "(Landroid/content/Context;Lcom/ido/life/database/model/UserMedalInfo;)V", "mAdapter", "Lcom/ido/life/customview/recyclerview/CommonRecyclerViewAdapter;", "Landroid/content/pm/ResolveInfo;", "mAppInfoList", "", "mUri", "Landroid/net/Uri;", "getModalInfo", "()Lcom/ido/life/database/model/UserMedalInfo;", "setModalInfo", "(Lcom/ido/life/database/model/UserMedalInfo;)V", "onClick", "", "v", "Landroid/view/View;", "onItemClick", "view", "holder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "position", "", "onItemLongClick", "", "setContentView", "params", "Landroid/view/ViewGroup$LayoutParams;", "app_release"}, k = 1, mv = {1, 1, 16})
public final class UserMedalShareDialog extends Dialog implements View.OnClickListener, MultiItemTypeAdapterForRV.OnItemClickListener {
    private CommonRecyclerViewAdapter<ResolveInfo> mAdapter;
    private List<ResolveInfo> mAppInfoList;
    private Uri mUri;
    private UserMedalInfo modalInfo;

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserMedalShareDialog(final Context context, UserMedalInfo modalInfo) {
        super(context, R.style.dialog_translate2);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(modalInfo, "modalInfo");
        this.modalInfo = modalInfo;
        this.mAppInfoList = new ArrayList();
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        int i = resources.getDisplayMetrics().widthPixels;
        Resources resources2 = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources2, "context.resources");
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(i, resources2.getDisplayMetrics().heightPixels);
        this.mAdapter = new CommonRecyclerViewAdapter<ResolveInfo>(context, R.layout.item_share_layout, this.mAppInfoList) { // from class: com.ido.life.module.user.healthreport.UserMedalShareDialog.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder holder, ResolveInfo t, int position) {
                HealthReportCircleImageView healthReportCircleImageView = holder != null ? (HealthReportCircleImageView) holder.getView(R.id.img_app_icon) : null;
                TextView textView = holder != null ? (TextView) holder.getView(R.id.tv_app_name) : null;
                ResolveInfo resolveInfo = (ResolveInfo) UserMedalShareDialog.this.mAppInfoList.get(position);
                if (position != 0) {
                    if (healthReportCircleImageView != null) {
                        healthReportCircleImageView.setImageDrawable(resolveInfo.loadIcon(context.getPackageManager()));
                    }
                    if (textView != null) {
                        textView.setText(resolveInfo.loadLabel(context.getPackageManager()));
                        return;
                    }
                    return;
                }
                if (healthReportCircleImageView == null) {
                    Intrinsics.throwNpe();
                }
                healthReportCircleImageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_sport_share_save));
                if (textView == null) {
                    Intrinsics.throwNpe();
                }
                textView.setText(LanguageUtil.getLanguageText(R.string.sport_share_save_img));
            }
        };
        View viewInflate = getLayoutInflater().inflate(R.layout.dialog_user_model_share_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "layoutInflater.inflate(R…model_share_layout, null)");
        setContentView(viewInflate, layoutParams);
    }

    public final UserMedalInfo getModalInfo() {
        return this.modalInfo;
    }

    public final void setModalInfo(UserMedalInfo userMedalInfo) {
        Intrinsics.checkParameterIsNotNull(userMedalInfo, "<set-?>");
        this.modalInfo = userMedalInfo;
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        String str;
        ViewGroup viewGroup;
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.setContentView(view, params);
        boolean z = true;
        setCancelable(true);
        Window window = getWindow();
        View decorView = window != null ? window.getDecorView() : null;
        if (decorView != null && (viewGroup = (ViewGroup) decorView.findViewById(android.R.id.content)) != null) {
            viewGroup.setBackgroundColor(0);
        }
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(runTimeUtil.getUserId());
        if (userInfoQueryUserInfo != null) {
            String avatarUrl = userInfoQueryUserInfo.getAvatarUrl();
            if (!(avatarUrl == null || avatarUrl.length() == 0)) {
                ImageLoaderUtil.loadCircleImage((ImageView) findViewById(com.ido.life.R.id.img_avata), userInfoQueryUserInfo.getAvatarUrl(), R.mipmap.ic_avatar_default);
            }
        }
        UserModelEnum userModelEnumById = UserModelEnum.INSTANCE.getUserModelEnumById(this.modalInfo.getMedalId());
        if (userModelEnumById != null) {
            ((ImageView) findViewById(com.ido.life.R.id.img_model)).setImageResource(userModelEnumById.getMedalImageId_KM());
            TextView tv_model_title = (TextView) findViewById(com.ido.life.R.id.tv_model_title);
            Intrinsics.checkExpressionValueIsNotNull(tv_model_title, "tv_model_title");
            tv_model_title.setText(LanguageUtil.getLanguageText(userModelEnumById.getTitleResId()));
            RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
            int unitSet = runTimeUtil2.getUnitSet();
            String desc = LanguageUtil.getLanguageText(userModelEnumById.getMedalDescShortResId());
            if (unitSet == 1) {
                Intrinsics.checkExpressionValueIsNotNull(desc, "desc");
                Object[] objArr = {Integer.valueOf(userModelEnumById.getTargetValue()), LanguageUtil.getLanguageText(R.string.km_short)};
                str = String.format(desc, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
            } else {
                Intrinsics.checkExpressionValueIsNotNull(desc, "desc");
                Object[] objArr2 = {Integer.valueOf(MathKt.roundToInt(UnitUtil.getKm2mile(userModelEnumById.getTargetValue()))), LanguageUtil.getLanguageText(R.string.mile_short)};
                str = String.format(desc, Arrays.copyOf(objArr2, objArr2.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
            }
            try {
                TextView tv_model_desc = (TextView) findViewById(com.ido.life.R.id.tv_model_desc);
                Intrinsics.checkExpressionValueIsNotNull(tv_model_desc, "tv_model_desc");
                tv_model_desc.setText(str);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(DateUtil.string2Date(this.modalInfo.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
            TextView tv_get_date = (TextView) findViewById(com.ido.life.R.id.tv_get_date);
            Intrinsics.checkExpressionValueIsNotNull(tv_get_date, "tv_get_date");
            tv_get_date.setText(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DMY_1) + " " + LanguageUtil.getLanguageText(R.string.get_medal));
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        ((TextView) findViewById(com.ido.life.R.id.tv_cancel)).setOnClickListener(this);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/*");
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
        Intrinsics.checkExpressionValueIsNotNull(listQueryIntentActivities, "context.packageManager.q…nager.MATCH_DEFAULT_ONLY)");
        List<ResolveInfo> list = listQueryIntentActivities;
        if (list != null && !list.isEmpty()) {
            z = false;
        }
        if (!z) {
            this.mAppInfoList.add(new ResolveInfo());
            this.mAppInfoList.addAll(list);
        }
        RecyclerView recycler_share = (RecyclerView) findViewById(com.ido.life.R.id.recycler_share);
        Intrinsics.checkExpressionValueIsNotNull(recycler_share, "recycler_share");
        recycler_share.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_cancel) {
            dismiss();
        }
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        this.mUri = (Uri) null;
        try {
            Bitmap bitmapFromView = BitmapUtil.getBitmapFromView((CardView) findViewById(com.ido.life.R.id.cardview));
            if (position != 0) {
                if (bitmapFromView != null) {
                    Context context = getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context, "context");
                    ContentResolver contentResolver = context.getContentResolver();
                    Context context2 = getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                    this.mUri = Uri.parse(MediaStore.Images.Media.insertImage(contentResolver, bitmapFromView, context2.getPackageName(), (String) null));
                }
                if (this.mUri != null) {
                    ResolveInfo resolveInfo = this.mAppInfoList.get(position);
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("image/*");
                    intent.putExtra("android.intent.extra.STREAM", this.mUri);
                    intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                    getContext().startActivity(intent);
                }
            } else if (bitmapFromView != null) {
                Context context3 = getContext();
                Calendar calendar = Calendar.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance()");
                BitmapUtil.saveBitmap(context3, bitmapFromView, calendar.getTime().toString());
            }
            dismiss();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}