package com.ido.life.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.constants.Constants;
import com.ido.life.data.api.entity.AppInfoEntity;
import com.ido.life.dialog.NotificationExceptionDialog;
import com.ido.life.dialog.NotificationPermissionSettingDialog;
import com.ido.life.module.home.fitness.FitnessHelperKt;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: DialogFactory.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0016\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0010\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u000e\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0006\u0010\n\u001a\u00020\u000b\u001a\u0006\u0010\f\u001a\u00020\u000b¨\u0006\r"}, d2 = {"getAppForceUpdateDialog", "Landroidx/appcompat/app/AlertDialog;", "context", "Landroid/content/Context;", "getAppNormalUpdateDialog", "appInfo", "Lcom/ido/life/data/api/entity/AppInfoEntity$AppInfo;", "getFitnessCalorieSummaryDialog", "getFitnessTimeSummaryDialog", "getFitnessWalkSummaryDialog", "getNotificationExceptionDialog", "Lcom/ido/common/base/BaseDialogFragment;", "getNotificationTipDialog", "app_release"}, k = 2, mv = {1, 1, 16})
public final class DialogFactoryKt {
    public static final AlertDialog getAppForceUpdateDialog(final Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_app_force_update_layout, (ViewGroup) null);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context, R.style.dialog_translate).setView(view).create();
        Intrinsics.checkExpressionValueIsNotNull(alertDialogCreate, "AlertDialog.Builder(cont…  .setView(view).create()");
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        view.setMinimumWidth((resources.getDisplayMetrics().widthPixels * 4) / 5);
        TextView textView = (TextView) view.findViewById(R.id.tv_content);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_update);
        if (textView != null) {
            textView.setText(LanguageUtil.getLanguageText(R.string.app_update_desc));
        }
        if (textView2 != null) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.util.DialogFactoryKt.getAppForceUpdateDialog.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AppMarketUtils.INSTANCE.gotoMarket(context);
                }
            });
        }
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.setCancelable(false);
        return alertDialogCreate;
    }

    public static final BaseDialogFragment getNotificationTipDialog() {
        NotificationPermissionSettingDialog notificationPermissionSettingDialog = NotificationPermissionSettingDialog.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(notificationPermissionSettingDialog, "NotificationPermissionSettingDialog.getInstance()");
        return notificationPermissionSettingDialog;
    }

    public static final AlertDialog getAppNormalUpdateDialog(final Context context, AppInfoEntity.AppInfo appInfo) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(appInfo, "appInfo");
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_app_normal_update_layout, (ViewGroup) null);
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context, R.style.dialog_translate).setView(inflate).create();
        Intrinsics.checkExpressionValueIsNotNull(alertDialogCreate, "AlertDialog.Builder(cont…setView(inflate).create()");
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflate");
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        inflate.setMinimumWidth((resources.getDisplayMetrics().widthPixels * 4) / 5);
        TextView textView = (TextView) inflate.findViewById(R.id.app_version_size);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_update);
        TextView textView3 = (TextView) inflate.findViewById(R.id.tv_update_later);
        if (textView != null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = IdoApp.getString(R.string.app_version_s);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.app_version_s)");
            Object[] objArr = {appInfo.getVersion()};
            String str = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
            textView.setText(str);
        }
        if (textView2 != null) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.util.DialogFactoryKt.getAppNormalUpdateDialog.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SPUtils.put(Constants.APP_UPDATE_TIME, Long.valueOf(System.currentTimeMillis()));
                    AppMarketUtils.INSTANCE.gotoMarket(context);
                }
            });
        }
        if (textView3 != null) {
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.util.DialogFactoryKt.getAppNormalUpdateDialog.2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SPUtils.put(Constants.APP_UPDATE_TIME, Long.valueOf(System.currentTimeMillis()));
                    AlertDialog alertDialog = alertDialogCreate;
                    if (alertDialog != null) {
                        alertDialog.dismiss();
                    }
                }
            });
        }
        alertDialogCreate.setCanceledOnTouchOutside(true);
        alertDialogCreate.setCancelable(true);
        return alertDialogCreate;
    }

    public static final BaseDialogFragment getNotificationExceptionDialog() {
        NotificationExceptionDialog notificationExceptionDialog = NotificationExceptionDialog.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(notificationExceptionDialog, "NotificationExceptionDialog.getInstance()");
        return notificationExceptionDialog;
    }

    public static final AlertDialog getFitnessCalorieSummaryDialog(Context context) {
        int iRoundToInt;
        int iRoundToInt2;
        TextView textView;
        int i;
        int i2;
        int i3;
        Intrinsics.checkParameterIsNotNull(context, "context");
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fitness_summary_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        view.setMinimumWidth((resources.getDisplayMetrics().widthPixels * 5) / 6);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context, R.style.dialog_translate).setView(view).create();
        Intrinsics.checkExpressionValueIsNotNull(alertDialogCreate, "AlertDialog.Builder(cont…e).setView(view).create()");
        alertDialogCreate.setCanceledOnTouchOutside(true);
        alertDialogCreate.setCancelable(true);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvDesc = (TextView) view.findViewById(R.id.tv_desc);
        TextView tvValueLitter = (TextView) view.findViewById(R.id.tv_value_litter);
        ImageView imgLess = (ImageView) view.findViewById(R.id.img_less);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_title_less);
        TextView tvValueLess = (TextView) view.findViewById(R.id.tv_value_less);
        ImageView imgMedium = (ImageView) view.findViewById(R.id.img_medium);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_title_medium);
        TextView tvValueMedium = (TextView) view.findViewById(R.id.tv_value_medium);
        ImageView imgMuch = (ImageView) view.findViewById(R.id.img_much);
        TextView textView4 = (TextView) view.findViewById(R.id.tv_title_much);
        TextView tvValueMuch = (TextView) view.findViewById(R.id.tv_value_much);
        ImageView imgMore = (ImageView) view.findViewById(R.id.img_more);
        TextView textView5 = (TextView) view.findViewById(R.id.tv_title_more);
        TextView tvValueMore = (TextView) view.findViewById(R.id.tv_value_more);
        TextView tvValueSoMuch = (TextView) view.findViewById(R.id.tv_value_so_much);
        Intrinsics.checkExpressionValueIsNotNull(tvTitle, "tvTitle");
        tvTitle.setText(LanguageUtil.getLanguageText(R.string.activity));
        Intrinsics.checkExpressionValueIsNotNull(tvDesc, "tvDesc");
        tvDesc.setText(LanguageUtil.getLanguageText(R.string.fitness_summary_calorie_desc));
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        double dMax = Math.max(FitnessHelperKt.caluteBMR(runTimeUtil.getUserId()), 0);
        double d2 = 0.8d * dMax;
        if (MathKt.roundToInt(d2) < 60) {
            textView = textView5;
            i = 40;
            i2 = 50;
            i3 = 30;
            iRoundToInt2 = 20;
            iRoundToInt = 10;
        } else {
            iRoundToInt = (MathKt.roundToInt(0.15d * dMax) / 50) * 50;
            iRoundToInt2 = (MathKt.roundToInt(0.25d * dMax) / 10) * 10;
            int iRoundToInt3 = (MathKt.roundToInt(0.35d * dMax) / 10) * 10;
            int iRoundToInt4 = (MathKt.roundToInt(dMax * 0.55d) / 10) * 10;
            int iRoundToInt5 = MathKt.roundToInt(d2);
            int i4 = ((iRoundToInt5 / 50) + (iRoundToInt5 % 50 == 0 ? 0 : 1)) * 50;
            textView = textView5;
            i = iRoundToInt4;
            i2 = i4;
            i3 = iRoundToInt3;
        }
        Intrinsics.checkExpressionValueIsNotNull(tvValueLitter, "tvValueLitter");
        String languageText = LanguageUtil.getLanguageText(R.string.less_than);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguageText(R.string.less_than)");
        int i5 = i;
        Object[] objArr = {Integer.valueOf(iRoundToInt), LanguageUtil.getLanguageText(R.string.public_heat_calorie)};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        tvValueLitter.setText(String.valueOf(str));
        Intrinsics.checkExpressionValueIsNotNull(imgLess, "imgLess");
        imgLess.setBackgroundTintList(ColorStateList.valueOf(ResourceUtil.getColor(R.color.color_FFE451)));
        textView2.setTextColor(ResourceUtil.getColor(R.color.color_CAB542));
        Intrinsics.checkExpressionValueIsNotNull(tvValueLess, "tvValueLess");
        tvValueLess.setText(iRoundToInt + '~' + iRoundToInt2 + ' ' + LanguageUtil.getLanguageText(R.string.public_heat_calorie));
        Intrinsics.checkExpressionValueIsNotNull(imgMedium, "imgMedium");
        imgMedium.setBackgroundTintList(ColorStateList.valueOf(ResourceUtil.getColor(R.color.color_FFAE6A)));
        textView3.setTextColor(ResourceUtil.getColor(R.color.color_FFAE6A));
        Intrinsics.checkExpressionValueIsNotNull(tvValueMedium, "tvValueMedium");
        tvValueMedium.setText(iRoundToInt2 + '~' + i3 + ' ' + LanguageUtil.getLanguageText(R.string.public_heat_calorie));
        Intrinsics.checkExpressionValueIsNotNull(imgMuch, "imgMuch");
        imgMuch.setBackgroundTintList(ColorStateList.valueOf(ResourceUtil.getColor(R.color.color_FF7550)));
        textView4.setTextColor(ResourceUtil.getColor(R.color.color_FF7550));
        Intrinsics.checkExpressionValueIsNotNull(tvValueMuch, "tvValueMuch");
        tvValueMuch.setText(i3 + '~' + i5 + ' ' + LanguageUtil.getLanguageText(R.string.public_heat_calorie));
        Intrinsics.checkExpressionValueIsNotNull(imgMore, "imgMore");
        imgMore.setBackgroundTintList(ColorStateList.valueOf(ResourceUtil.getColor(R.color.color_FF3519)));
        textView.setTextColor(ResourceUtil.getColor(R.color.color_FF3519));
        Intrinsics.checkExpressionValueIsNotNull(tvValueMore, "tvValueMore");
        StringBuilder sb = new StringBuilder();
        sb.append(i5);
        sb.append('~');
        int i6 = i2;
        sb.append(i6);
        sb.append(' ');
        sb.append(LanguageUtil.getLanguageText(R.string.public_heat_calorie));
        tvValueMore.setText(sb.toString());
        Intrinsics.checkExpressionValueIsNotNull(tvValueSoMuch, "tvValueSoMuch");
        String languageText2 = LanguageUtil.getLanguageText(R.string.more_than);
        Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguageText(R.string.more_than)");
        Object[] objArr2 = {Integer.valueOf(i6), LanguageUtil.getLanguageText(R.string.public_heat_calorie)};
        String str2 = String.format(languageText2, Arrays.copyOf(objArr2, objArr2.length));
        Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(this, *args)");
        tvValueSoMuch.setText(String.valueOf(str2));
        return alertDialogCreate;
    }

    public static final AlertDialog getFitnessTimeSummaryDialog(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fitness_summary_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        view.setMinimumWidth((resources.getDisplayMetrics().widthPixels * 5) / 6);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context, R.style.dialog_translate).setView(view).create();
        Intrinsics.checkExpressionValueIsNotNull(alertDialogCreate, "AlertDialog.Builder(cont…e).setView(view).create()");
        alertDialogCreate.setCanceledOnTouchOutside(true);
        alertDialogCreate.setCancelable(true);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvDesc = (TextView) view.findViewById(R.id.tv_desc);
        TextView tvValueLitter = (TextView) view.findViewById(R.id.tv_value_litter);
        ImageView imgLess = (ImageView) view.findViewById(R.id.img_less);
        TextView textView = (TextView) view.findViewById(R.id.tv_title_less);
        TextView tvValueLess = (TextView) view.findViewById(R.id.tv_value_less);
        ImageView imgMedium = (ImageView) view.findViewById(R.id.img_medium);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_title_medium);
        TextView tvValueMedium = (TextView) view.findViewById(R.id.tv_value_medium);
        ImageView imgMuch = (ImageView) view.findViewById(R.id.img_much);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_title_much);
        TextView tvValueMuch = (TextView) view.findViewById(R.id.tv_value_much);
        ImageView imgMore = (ImageView) view.findViewById(R.id.img_more);
        TextView textView4 = (TextView) view.findViewById(R.id.tv_title_more);
        TextView tvValueMore = (TextView) view.findViewById(R.id.tv_value_more);
        TextView tvValueSoMuch = (TextView) view.findViewById(R.id.tv_value_so_much);
        Intrinsics.checkExpressionValueIsNotNull(tvTitle, "tvTitle");
        tvTitle.setText(LanguageUtil.getLanguageText(R.string.exercise));
        Intrinsics.checkExpressionValueIsNotNull(tvDesc, "tvDesc");
        tvDesc.setText(LanguageUtil.getLanguageText(R.string.fitness_summary_exercise_desc));
        Intrinsics.checkExpressionValueIsNotNull(tvValueLitter, "tvValueLitter");
        String languageText = LanguageUtil.getLanguageText(R.string.less_than);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguageText(R.string.less_than)");
        Object[] objArr = {5, LanguageUtil.getLanguageText(R.string.public_time_minute)};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        tvValueLitter.setText(String.valueOf(str));
        Intrinsics.checkExpressionValueIsNotNull(imgLess, "imgLess");
        imgLess.setBackgroundTintList(ColorStateList.valueOf(ResourceUtil.getColor(R.color.color_D1F3A7)));
        textView.setTextColor(ResourceUtil.getColor(R.color.color_ADD978));
        Intrinsics.checkExpressionValueIsNotNull(tvValueLess, "tvValueLess");
        tvValueLess.setText("5~10" + LanguageUtil.getLanguageText(R.string.public_time_minute));
        Intrinsics.checkExpressionValueIsNotNull(imgMedium, "imgMedium");
        imgMedium.setBackgroundTintList(ColorStateList.valueOf(ResourceUtil.getColor(R.color.color_81ED74)));
        textView2.setTextColor(ResourceUtil.getColor(R.color.color_81ED74));
        Intrinsics.checkExpressionValueIsNotNull(tvValueMedium, "tvValueMedium");
        tvValueMedium.setText("10~20" + LanguageUtil.getLanguageText(R.string.public_time_minute));
        Intrinsics.checkExpressionValueIsNotNull(imgMuch, "imgMuch");
        imgMuch.setBackgroundTintList(ColorStateList.valueOf(ResourceUtil.getColor(R.color.color_30E146)));
        textView3.setTextColor(ResourceUtil.getColor(R.color.color_30E146));
        Intrinsics.checkExpressionValueIsNotNull(tvValueMuch, "tvValueMuch");
        tvValueMuch.setText("20~40" + LanguageUtil.getLanguageText(R.string.public_time_minute));
        Intrinsics.checkExpressionValueIsNotNull(imgMore, "imgMore");
        imgMore.setBackgroundTintList(ColorStateList.valueOf(ResourceUtil.getColor(R.color.color_00BE47)));
        textView4.setTextColor(ResourceUtil.getColor(R.color.color_00BE47));
        Intrinsics.checkExpressionValueIsNotNull(tvValueMore, "tvValueMore");
        tvValueMore.setText("40~60" + LanguageUtil.getLanguageText(R.string.public_time_minute));
        Intrinsics.checkExpressionValueIsNotNull(tvValueSoMuch, "tvValueSoMuch");
        String languageText2 = LanguageUtil.getLanguageText(R.string.more_than);
        Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguageText(R.string.more_than)");
        Object[] objArr2 = {60, LanguageUtil.getLanguageText(R.string.public_time_minute)};
        String str2 = String.format(languageText2, Arrays.copyOf(objArr2, objArr2.length));
        Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(this, *args)");
        tvValueSoMuch.setText(String.valueOf(str2));
        return alertDialogCreate;
    }

    public static final AlertDialog getFitnessWalkSummaryDialog(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fitness_summary_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        view.setMinimumWidth((resources.getDisplayMetrics().widthPixels * 5) / 6);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context, R.style.dialog_translate).setView(view).create();
        Intrinsics.checkExpressionValueIsNotNull(alertDialogCreate, "AlertDialog.Builder(cont…e).setView(view).create()");
        alertDialogCreate.setCanceledOnTouchOutside(true);
        alertDialogCreate.setCancelable(true);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvDesc = (TextView) view.findViewById(R.id.tv_desc);
        TextView tvValueLitter = (TextView) view.findViewById(R.id.tv_value_litter);
        ImageView imgLess = (ImageView) view.findViewById(R.id.img_less);
        TextView textView = (TextView) view.findViewById(R.id.tv_title_less);
        TextView tvValueLess = (TextView) view.findViewById(R.id.tv_value_less);
        ImageView imgMedium = (ImageView) view.findViewById(R.id.img_medium);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_title_medium);
        TextView tvValueMedium = (TextView) view.findViewById(R.id.tv_value_medium);
        ImageView imgMuch = (ImageView) view.findViewById(R.id.img_much);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_title_much);
        TextView tvValueMuch = (TextView) view.findViewById(R.id.tv_value_much);
        ImageView imgMore = (ImageView) view.findViewById(R.id.img_more);
        TextView textView4 = (TextView) view.findViewById(R.id.tv_title_more);
        TextView tvValueMore = (TextView) view.findViewById(R.id.tv_value_more);
        TextView tvValueSoMuch = (TextView) view.findViewById(R.id.tv_value_so_much);
        Intrinsics.checkExpressionValueIsNotNull(tvTitle, "tvTitle");
        tvTitle.setText(LanguageUtil.getLanguageText(R.string.exercise));
        Intrinsics.checkExpressionValueIsNotNull(tvDesc, "tvDesc");
        tvDesc.setText(LanguageUtil.getLanguageText(R.string.fitness_summary_exercise_desc));
        Intrinsics.checkExpressionValueIsNotNull(tvValueLitter, "tvValueLitter");
        String languageText = LanguageUtil.getLanguageText(R.string.less_than);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguageText(R.string.less_than)");
        Object[] objArr = {6, LanguageUtil.getLanguageText(R.string.public_unit_hrs)};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        tvValueLitter.setText(String.valueOf(str));
        Intrinsics.checkExpressionValueIsNotNull(imgLess, "imgLess");
        imgLess.setBackgroundTintList(ColorStateList.valueOf(ResourceUtil.getColor(R.color.color_9AE4F4)));
        textView.setTextColor(ResourceUtil.getColor(R.color.color_7BD7EB));
        Intrinsics.checkExpressionValueIsNotNull(tvValueLess, "tvValueLess");
        tvValueLess.setText("6~8" + LanguageUtil.getLanguageText(R.string.public_unit_hrs));
        Intrinsics.checkExpressionValueIsNotNull(imgMedium, "imgMedium");
        imgMedium.setBackgroundTintList(ColorStateList.valueOf(ResourceUtil.getColor(R.color.color_67DAF4)));
        textView2.setTextColor(ResourceUtil.getColor(R.color.color_53C9E4));
        Intrinsics.checkExpressionValueIsNotNull(tvValueMedium, "tvValueMedium");
        tvValueMedium.setText("8~10" + LanguageUtil.getLanguageText(R.string.public_unit_hrs));
        Intrinsics.checkExpressionValueIsNotNull(imgMuch, "imgMuch");
        imgMuch.setBackgroundTintList(ColorStateList.valueOf(ResourceUtil.getColor(R.color.color_3DB6F1)));
        textView3.setTextColor(ResourceUtil.getColor(R.color.color_3DB6F1));
        Intrinsics.checkExpressionValueIsNotNull(tvValueMuch, "tvValueMuch");
        tvValueMuch.setText("10~12" + LanguageUtil.getLanguageText(R.string.public_unit_hrs));
        Intrinsics.checkExpressionValueIsNotNull(imgMore, "imgMore");
        imgMore.setBackgroundTintList(ColorStateList.valueOf(ResourceUtil.getColor(R.color.color_079BEC)));
        textView4.setTextColor(ResourceUtil.getColor(R.color.color_079BEC));
        Intrinsics.checkExpressionValueIsNotNull(tvValueMore, "tvValueMore");
        tvValueMore.setText("12~14" + LanguageUtil.getLanguageText(R.string.public_unit_hrs));
        Intrinsics.checkExpressionValueIsNotNull(tvValueSoMuch, "tvValueSoMuch");
        String languageText2 = LanguageUtil.getLanguageText(R.string.more_than);
        Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguageText(R.string.more_than)");
        Object[] objArr2 = {14, LanguageUtil.getLanguageText(R.string.public_unit_hrs)};
        String str2 = String.format(languageText2, Arrays.copyOf(objArr2, objArr2.length));
        Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(this, *args)");
        tvValueSoMuch.setText(String.valueOf(str2));
        return alertDialogCreate;
    }
}