package com.ido.life.module.user.userdata.mydata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.customview.viewgroup.OptionView;
import com.ido.life.module.home.chartdetail.vertical.ChartDetailActivity;
import com.ido.life.module.home.detail.DetailActivity;
import com.ido.life.module.nodatapage.bind.HasBindNoDataActivity;
import com.ido.life.module.nodatapage.unbind.UnBindNoDataActivity;
import com.ido.life.module.user.sportrecord.SportRecordActivity;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.UnitUtil;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: MyDataActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\u0016\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000bH\u0016J\b\u0010\f\u001a\u00020\tH\u0014J\b\u0010\r\u001a\u00020\tH\u0016J\u0012\u0010\u000e\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0007H\u0016J\u0010\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u0007H\u0016J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u0007H\u0016J\u0010\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u0007H\u0016J\u0010\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\u0007H\u0016J\u0010\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020\u0007H\u0016J\u0010\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\u0007H\u0016J\u0018\u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020\u00152\u0006\u0010)\u001a\u00020\u0007H\u0016¨\u0006*"}, d2 = {"Lcom/ido/life/module/user/userdata/mydata/MyDataActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/user/userdata/mydata/MyDataPresenter;", "Lcom/ido/life/module/user/userdata/mydata/IMyDataView;", "Landroid/view/View$OnClickListener;", "()V", "getLayoutResId", "", "handleMessage", "", "message", "Lcom/ido/life/base/BaseMessage;", "initData", "initViews", "onClick", "v", "Landroid/view/View;", "onGetCagories", "cagory", "onGetDistance", "distance", "", "onGetHeartRate", "heartRate", "onGetOxy", "oxy", "onGetPressure", "pressure", "onGetSleepDuration", "sleepDuration", "onGetSportRecord", "sportCount", "", "onGetStep", "step", "onGetStronger", "stronger", "onGetWalkDuration", "walkDuration", "onGetWeight", "weight", "weightUnit", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MyDataActivity extends BaseActivity<MyDataPresenter> implements IMyDataView, View.OnClickListener {
    private HashMap _$_findViewCache;

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
        return R.layout.activity_my_data_layout;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        OptionView lay_step = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_step);
        Intrinsics.checkExpressionValueIsNotNull(lay_step, "lay_step");
        ImageView imageView = (ImageView) lay_step.findViewById(com.ido.life.R.id.option_start_img);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "lay_step.option_start_img");
        imageView.setVisibility(8);
        OptionView lay_distance = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_distance);
        Intrinsics.checkExpressionValueIsNotNull(lay_distance, "lay_distance");
        ImageView imageView2 = (ImageView) lay_distance.findViewById(com.ido.life.R.id.option_start_img);
        Intrinsics.checkExpressionValueIsNotNull(imageView2, "lay_distance.option_start_img");
        imageView2.setVisibility(8);
        OptionView lay_cagory = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_cagory);
        Intrinsics.checkExpressionValueIsNotNull(lay_cagory, "lay_cagory");
        ImageView imageView3 = (ImageView) lay_cagory.findViewById(com.ido.life.R.id.option_start_img);
        Intrinsics.checkExpressionValueIsNotNull(imageView3, "lay_cagory.option_start_img");
        imageView3.setVisibility(8);
        OptionView lay_stronger_duration = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_stronger_duration);
        Intrinsics.checkExpressionValueIsNotNull(lay_stronger_duration, "lay_stronger_duration");
        ImageView imageView4 = (ImageView) lay_stronger_duration.findViewById(com.ido.life.R.id.option_start_img);
        Intrinsics.checkExpressionValueIsNotNull(imageView4, "lay_stronger_duration.option_start_img");
        imageView4.setVisibility(8);
        OptionView lay_walk_duration = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_walk_duration);
        Intrinsics.checkExpressionValueIsNotNull(lay_walk_duration, "lay_walk_duration");
        ImageView imageView5 = (ImageView) lay_walk_duration.findViewById(com.ido.life.R.id.option_start_img);
        Intrinsics.checkExpressionValueIsNotNull(imageView5, "lay_walk_duration.option_start_img");
        imageView5.setVisibility(8);
        OptionView lay_heart_rate = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_heart_rate);
        Intrinsics.checkExpressionValueIsNotNull(lay_heart_rate, "lay_heart_rate");
        ImageView imageView6 = (ImageView) lay_heart_rate.findViewById(com.ido.life.R.id.option_start_img);
        Intrinsics.checkExpressionValueIsNotNull(imageView6, "lay_heart_rate.option_start_img");
        imageView6.setVisibility(8);
        OptionView lay_sleep = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_sleep);
        Intrinsics.checkExpressionValueIsNotNull(lay_sleep, "lay_sleep");
        ImageView imageView7 = (ImageView) lay_sleep.findViewById(com.ido.life.R.id.option_start_img);
        Intrinsics.checkExpressionValueIsNotNull(imageView7, "lay_sleep.option_start_img");
        imageView7.setVisibility(8);
        OptionView lay_pressure = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_pressure);
        Intrinsics.checkExpressionValueIsNotNull(lay_pressure, "lay_pressure");
        ImageView imageView8 = (ImageView) lay_pressure.findViewById(com.ido.life.R.id.option_start_img);
        Intrinsics.checkExpressionValueIsNotNull(imageView8, "lay_pressure.option_start_img");
        imageView8.setVisibility(8);
        OptionView lay_oxy = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_oxy);
        Intrinsics.checkExpressionValueIsNotNull(lay_oxy, "lay_oxy");
        ImageView imageView9 = (ImageView) lay_oxy.findViewById(com.ido.life.R.id.option_start_img);
        Intrinsics.checkExpressionValueIsNotNull(imageView9, "lay_oxy.option_start_img");
        imageView9.setVisibility(8);
        OptionView lay_weight = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_weight);
        Intrinsics.checkExpressionValueIsNotNull(lay_weight, "lay_weight");
        ImageView imageView10 = (ImageView) lay_weight.findViewById(com.ido.life.R.id.option_start_img);
        Intrinsics.checkExpressionValueIsNotNull(imageView10, "lay_weight.option_start_img");
        imageView10.setVisibility(8);
        OptionView lay_sport = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_sport);
        Intrinsics.checkExpressionValueIsNotNull(lay_sport, "lay_sport");
        ImageView imageView11 = (ImageView) lay_sport.findViewById(com.ido.life.R.id.option_start_img);
        Intrinsics.checkExpressionValueIsNotNull(imageView11, "lay_sport.option_start_img");
        imageView11.setVisibility(8);
        MyDataActivity myDataActivity = this;
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_step)).setOnClickListener(myDataActivity);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_distance)).setOnClickListener(myDataActivity);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_cagory)).setOnClickListener(myDataActivity);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_stronger_duration)).setOnClickListener(myDataActivity);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_walk_duration)).setOnClickListener(myDataActivity);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_heart_rate)).setOnClickListener(myDataActivity);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_sleep)).setOnClickListener(myDataActivity);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_pressure)).setOnClickListener(myDataActivity);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_oxy)).setOnClickListener(myDataActivity);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_weight)).setOnClickListener(myDataActivity);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_sport)).setOnClickListener(myDataActivity);
        this.mTitleBar.setTitle(getLanguageText(R.string.user_center_my_data));
        TextView tv_title_active = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_active);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_active, "tv_title_active");
        tv_title_active.setText(getLanguageText(R.string.mydata_title_active_data));
        OptionView lay_step2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_step);
        Intrinsics.checkExpressionValueIsNotNull(lay_step2, "lay_step");
        lay_step2.setStartText(getLanguageText(R.string.home_steps_tittle));
        OptionView lay_step3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_step);
        Intrinsics.checkExpressionValueIsNotNull(lay_step3, "lay_step");
        RegularTextView regularTextView = (RegularTextView) lay_step3.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView, "lay_step.option_end_text");
        regularTextView.setText("--");
        OptionView lay_distance2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_distance);
        Intrinsics.checkExpressionValueIsNotNull(lay_distance2, "lay_distance");
        lay_distance2.setStartText(getLanguageText(R.string.sport_distance));
        OptionView lay_distance3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_distance);
        Intrinsics.checkExpressionValueIsNotNull(lay_distance3, "lay_distance");
        RegularTextView regularTextView2 = (RegularTextView) lay_distance3.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView2, "lay_distance.option_end_text");
        regularTextView2.setText("--");
        OptionView lay_cagory2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_cagory);
        Intrinsics.checkExpressionValueIsNotNull(lay_cagory2, "lay_cagory");
        lay_cagory2.setStartText(getLanguageText(R.string.sport_calorie));
        OptionView lay_cagory3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_cagory);
        Intrinsics.checkExpressionValueIsNotNull(lay_cagory3, "lay_cagory");
        RegularTextView regularTextView3 = (RegularTextView) lay_cagory3.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView3, "lay_cagory.option_end_text");
        regularTextView3.setText("--");
        OptionView lay_stronger_duration2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_stronger_duration);
        Intrinsics.checkExpressionValueIsNotNull(lay_stronger_duration2, "lay_stronger_duration");
        lay_stronger_duration2.setStartText(getLanguageText(R.string.home_card_activity_stronger_walk));
        OptionView lay_stronger_duration3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_stronger_duration);
        Intrinsics.checkExpressionValueIsNotNull(lay_stronger_duration3, "lay_stronger_duration");
        RegularTextView regularTextView4 = (RegularTextView) lay_stronger_duration3.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView4, "lay_stronger_duration.option_end_text");
        regularTextView4.setText("--");
        OptionView lay_walk_duration2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_walk_duration);
        Intrinsics.checkExpressionValueIsNotNull(lay_walk_duration2, "lay_walk_duration");
        lay_walk_duration2.setStartText(getLanguageText(R.string.mydata_walk_duration));
        OptionView lay_walk_duration3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_walk_duration);
        Intrinsics.checkExpressionValueIsNotNull(lay_walk_duration3, "lay_walk_duration");
        RegularTextView regularTextView5 = (RegularTextView) lay_walk_duration3.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView5, "lay_walk_duration.option_end_text");
        regularTextView5.setText("--");
        TextView tv_title_setting = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_setting);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_setting, "tv_title_setting");
        tv_title_setting.setText(getLanguageText(R.string.health_state));
        OptionView lay_heart_rate2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_heart_rate);
        Intrinsics.checkExpressionValueIsNotNull(lay_heart_rate2, "lay_heart_rate");
        lay_heart_rate2.setStartText(getLanguageText(R.string.home_card_heartbeat_title));
        OptionView lay_heart_rate3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_heart_rate);
        Intrinsics.checkExpressionValueIsNotNull(lay_heart_rate3, "lay_heart_rate");
        RegularTextView regularTextView6 = (RegularTextView) lay_heart_rate3.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView6, "lay_heart_rate.option_end_text");
        regularTextView6.setText("--");
        OptionView lay_sleep2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_sleep);
        Intrinsics.checkExpressionValueIsNotNull(lay_sleep2, "lay_sleep");
        lay_sleep2.setStartText(getLanguageText(R.string.home_card_sleep));
        OptionView lay_sleep3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_sleep);
        Intrinsics.checkExpressionValueIsNotNull(lay_sleep3, "lay_sleep");
        RegularTextView regularTextView7 = (RegularTextView) lay_sleep3.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView7, "lay_sleep.option_end_text");
        regularTextView7.setText("--");
        OptionView lay_pressure2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_pressure);
        Intrinsics.checkExpressionValueIsNotNull(lay_pressure2, "lay_pressure");
        lay_pressure2.setStartText(getLanguageText(R.string.home_card_pressure));
        OptionView lay_pressure3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_pressure);
        Intrinsics.checkExpressionValueIsNotNull(lay_pressure3, "lay_pressure");
        RegularTextView regularTextView8 = (RegularTextView) lay_pressure3.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView8, "lay_pressure.option_end_text");
        regularTextView8.setText("--");
        OptionView lay_oxy2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_oxy);
        Intrinsics.checkExpressionValueIsNotNull(lay_oxy2, "lay_oxy");
        lay_oxy2.setStartText(getLanguageText(R.string.home_card_blood_oxygen));
        OptionView lay_oxy3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_oxy);
        Intrinsics.checkExpressionValueIsNotNull(lay_oxy3, "lay_oxy");
        RegularTextView regularTextView9 = (RegularTextView) lay_oxy3.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView9, "lay_oxy.option_end_text");
        regularTextView9.setText("--");
        OptionView lay_weight2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_weight);
        Intrinsics.checkExpressionValueIsNotNull(lay_weight2, "lay_weight");
        lay_weight2.setStartText(getLanguageText(R.string.home_card_weight));
        OptionView lay_weight3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_weight);
        Intrinsics.checkExpressionValueIsNotNull(lay_weight3, "lay_weight");
        RegularTextView regularTextView10 = (RegularTextView) lay_weight3.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView10, "lay_weight.option_end_text");
        regularTextView10.setText("--");
        TextView tv_title_sport = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_sport);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_sport, "tv_title_sport");
        tv_title_sport.setText(getLanguageText(R.string.device_sport));
        OptionView lay_sport2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_sport);
        Intrinsics.checkExpressionValueIsNotNull(lay_sport2, "lay_sport");
        lay_sport2.setStartText(getLanguageText(R.string.mine_sport_record));
        OptionView lay_sport3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_sport);
        Intrinsics.checkExpressionValueIsNotNull(lay_sport3, "lay_sport");
        RegularTextView regularTextView11 = (RegularTextView) lay_sport3.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView11, "lay_sport.option_end_text");
        regularTextView11.setText("$0" + getLanguageText(R.string.home_turn));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        ((MyDataPresenter) this.mPresenter).getPageData();
    }

    @Override // com.ido.life.module.user.userdata.mydata.IMyDataView
    public void onGetStep(int step) {
        String str;
        OptionView lay_step = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_step);
        Intrinsics.checkExpressionValueIsNotNull(lay_step, "lay_step");
        RegularTextView regularTextView = (RegularTextView) lay_step.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView, "lay_step.option_end_text");
        if (step > 0) {
            str = step + getLanguageText(R.string.public_sport_step);
        } else {
            str = "--";
        }
        regularTextView.setText(str);
    }

    @Override // com.ido.life.module.user.userdata.mydata.IMyDataView
    public void onGetDistance(float distance) {
        String str;
        if (distance > 0) {
            OptionView lay_distance = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_distance);
            Intrinsics.checkExpressionValueIsNotNull(lay_distance, "lay_distance");
            RegularTextView regularTextView = (RegularTextView) lay_distance.findViewById(com.ido.life.R.id.option_end_text);
            Intrinsics.checkExpressionValueIsNotNull(regularTextView, "lay_distance.option_end_text");
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            if (runTimeUtil.getUnitSet() == 1) {
                String str2 = "%.2f" + getLanguageText(R.string.km_short);
                Object[] objArr = {Float.valueOf(distance)};
                String str3 = String.format(str2, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str3, "java.lang.String.format(this, *args)");
                str = str3;
            } else {
                String str4 = "%.2f" + getLanguageText(R.string.mile_short);
                Object[] objArr2 = {Float.valueOf(UnitUtil.km2mile(distance))};
                String str5 = String.format(str4, Arrays.copyOf(objArr2, objArr2.length));
                Intrinsics.checkNotNullExpressionValue(str5, "java.lang.String.format(this, *args)");
                str = str5;
            }
            regularTextView.setText(str);
        }
    }

    @Override // com.ido.life.module.user.userdata.mydata.IMyDataView
    public void onGetCagories(int cagory) {
        String str;
        OptionView lay_cagory = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_cagory);
        Intrinsics.checkExpressionValueIsNotNull(lay_cagory, "lay_cagory");
        RegularTextView regularTextView = (RegularTextView) lay_cagory.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView, "lay_cagory.option_end_text");
        if (cagory > 0) {
            str = cagory + getLanguageText(R.string.public_heat_calorie_short);
        } else {
            str = "--";
        }
        regularTextView.setText(str);
    }

    @Override // com.ido.life.module.user.userdata.mydata.IMyDataView
    public void onGetStronger(int stronger) {
        String str;
        OptionView lay_stronger_duration = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_stronger_duration);
        Intrinsics.checkExpressionValueIsNotNull(lay_stronger_duration, "lay_stronger_duration");
        RegularTextView regularTextView = (RegularTextView) lay_stronger_duration.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView, "lay_stronger_duration.option_end_text");
        if (stronger > 0) {
            str = stronger + getLanguageText(R.string.min_unit_short);
        } else {
            str = "--";
        }
        regularTextView.setText(str);
    }

    @Override // com.ido.life.module.user.userdata.mydata.IMyDataView
    public void onGetWalkDuration(int walkDuration) {
        String str;
        OptionView lay_walk_duration = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_walk_duration);
        Intrinsics.checkExpressionValueIsNotNull(lay_walk_duration, "lay_walk_duration");
        RegularTextView regularTextView = (RegularTextView) lay_walk_duration.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView, "lay_walk_duration.option_end_text");
        if (walkDuration > 0) {
            str = walkDuration + getLanguageText(R.string.public_time_hour);
        } else {
            str = "--";
        }
        regularTextView.setText(str);
    }

    @Override // com.ido.life.module.user.userdata.mydata.IMyDataView
    public void onGetHeartRate(int heartRate) {
        String str;
        OptionView lay_heart_rate = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_heart_rate);
        Intrinsics.checkExpressionValueIsNotNull(lay_heart_rate, "lay_heart_rate");
        RegularTextView regularTextView = (RegularTextView) lay_heart_rate.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView, "lay_heart_rate.option_end_text");
        if (heartRate > 0) {
            str = heartRate + getLanguageText(R.string.device_heart_rate_unit);
        } else {
            str = "--";
        }
        regularTextView.setText(str);
    }

    @Override // com.ido.life.module.user.userdata.mydata.IMyDataView
    public void onGetSleepDuration(int sleepDuration) {
        String str;
        OptionView lay_sleep = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_sleep);
        Intrinsics.checkExpressionValueIsNotNull(lay_sleep, "lay_sleep");
        RegularTextView regularTextView = (RegularTextView) lay_sleep.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView, "lay_sleep.option_end_text");
        if (sleepDuration > 0) {
            str = (sleepDuration / 3600) + getLanguageText(R.string.public_time_hour) + ((sleepDuration % 3600) / 60) + getLanguageText(R.string.public_time_minute);
        }
        regularTextView.setText(str);
    }

    @Override // com.ido.life.module.user.userdata.mydata.IMyDataView
    public void onGetPressure(int pressure) {
        OptionView lay_pressure = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_pressure);
        Intrinsics.checkExpressionValueIsNotNull(lay_pressure, "lay_pressure");
        RegularTextView regularTextView = (RegularTextView) lay_pressure.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView, "lay_pressure.option_end_text");
        regularTextView.setText(pressure > 0 ? String.valueOf(pressure) : "--");
    }

    @Override // com.ido.life.module.user.userdata.mydata.IMyDataView
    public void onGetOxy(int oxy) {
        String string;
        OptionView lay_oxy = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_oxy);
        Intrinsics.checkExpressionValueIsNotNull(lay_oxy, "lay_oxy");
        RegularTextView regularTextView = (RegularTextView) lay_oxy.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView, "lay_oxy.option_end_text");
        if (oxy > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(oxy);
            sb.append('%');
            string = sb.toString();
        } else {
            string = "--";
        }
        regularTextView.setText(string);
    }

    @Override // com.ido.life.module.user.userdata.mydata.IMyDataView
    public void onGetWeight(float weight, int weightUnit) {
        if (weight <= 0) {
            return;
        }
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        if (runTimeUtil.getUnitSet() != 1) {
            if (weightUnit == 1) {
                OptionView lay_weight = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_weight);
                Intrinsics.checkExpressionValueIsNotNull(lay_weight, "lay_weight");
                RegularTextView regularTextView = (RegularTextView) lay_weight.findViewById(com.ido.life.R.id.option_end_text);
                Intrinsics.checkExpressionValueIsNotNull(regularTextView, "lay_weight.option_end_text");
                regularTextView.setText(MathKt.roundToInt(UnitUtil.getKg2Pound(weight)) + getLanguageText(R.string.weight_pound_unit_short));
                return;
            }
            OptionView lay_weight2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_weight);
            Intrinsics.checkExpressionValueIsNotNull(lay_weight2, "lay_weight");
            RegularTextView regularTextView2 = (RegularTextView) lay_weight2.findViewById(com.ido.life.R.id.option_end_text);
            Intrinsics.checkExpressionValueIsNotNull(regularTextView2, "lay_weight.option_end_text");
            regularTextView2.setText(MathKt.roundToInt(weight) + getLanguageText(R.string.weight_pound_unit_short));
            return;
        }
        if (weightUnit == 1) {
            OptionView lay_weight3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_weight);
            Intrinsics.checkExpressionValueIsNotNull(lay_weight3, "lay_weight");
            RegularTextView regularTextView3 = (RegularTextView) lay_weight3.findViewById(com.ido.life.R.id.option_end_text);
            Intrinsics.checkExpressionValueIsNotNull(regularTextView3, "lay_weight.option_end_text");
            StringBuilder sb = new StringBuilder();
            Object[] objArr = {Float.valueOf(weight)};
            String str = String.format("%.1f", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
            sb.append(str);
            sb.append(getLanguageText(R.string.weight_kg_unit_short));
            regularTextView3.setText(sb.toString());
            return;
        }
        OptionView lay_weight4 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_weight);
        Intrinsics.checkExpressionValueIsNotNull(lay_weight4, "lay_weight");
        RegularTextView regularTextView4 = (RegularTextView) lay_weight4.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView4, "lay_weight.option_end_text");
        StringBuilder sb2 = new StringBuilder();
        Object[] objArr2 = {Float.valueOf(UnitUtil.getPound2Kg(weight))};
        String str2 = String.format("%.1f", Arrays.copyOf(objArr2, objArr2.length));
        Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(this, *args)");
        sb2.append(str2);
        sb2.append(getLanguageText(R.string.weight_kg_unit_short));
        regularTextView4.setText(sb2.toString());
    }

    @Override // com.ido.life.module.user.userdata.mydata.IMyDataView
    public void onGetSportRecord(long sportCount) {
        OptionView lay_sport = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_sport);
        Intrinsics.checkExpressionValueIsNotNull(lay_sport, "lay_sport");
        RegularTextView regularTextView = (RegularTextView) lay_sport.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView, "lay_sport.option_end_text");
        regularTextView.setText(sportCount + getLanguageText(R.string.home_turn));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_step) {
            if (((MyDataPresenter) this.mPresenter).hasStepData()) {
                Intent intent = new Intent(this, (Class<?>) ChartDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(ChartDetailActivity.PAGE_TYPE, 0);
                intent.putExtras(bundle);
                startActivity(intent);
                Unit unit = Unit.INSTANCE;
                return;
            }
            P mPresenter = this.mPresenter;
            Intrinsics.checkExpressionValueIsNotNull(mPresenter, "mPresenter");
            if (((MyDataPresenter) mPresenter).isBind()) {
                Intent intent2 = new Intent(this, (Class<?>) HasBindNoDataActivity.class);
                intent2.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 0);
                startActivity(intent2);
                Unit unit2 = Unit.INSTANCE;
                return;
            }
            Intent intent3 = new Intent(this, (Class<?>) UnBindNoDataActivity.class);
            intent3.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 0);
            startActivity(intent3);
            Unit unit3 = Unit.INSTANCE;
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_distance) {
            if (((MyDataPresenter) this.mPresenter).hasDistance()) {
                Intent intent4 = new Intent(this, (Class<?>) ChartDetailActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt(ChartDetailActivity.PAGE_TYPE, 1);
                intent4.putExtras(bundle2);
                startActivity(intent4);
                Unit unit4 = Unit.INSTANCE;
                return;
            }
            P mPresenter2 = this.mPresenter;
            Intrinsics.checkExpressionValueIsNotNull(mPresenter2, "mPresenter");
            if (((MyDataPresenter) mPresenter2).isBind()) {
                Intent intent5 = new Intent(this, (Class<?>) HasBindNoDataActivity.class);
                intent5.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 1);
                startActivity(intent5);
                Unit unit5 = Unit.INSTANCE;
                return;
            }
            Intent intent6 = new Intent(this, (Class<?>) UnBindNoDataActivity.class);
            intent6.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 1);
            startActivity(intent6);
            Unit unit6 = Unit.INSTANCE;
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_cagory) {
            if (((MyDataPresenter) this.mPresenter).hasCalorie()) {
                Intent intent7 = new Intent(this, (Class<?>) ChartDetailActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putInt(ChartDetailActivity.PAGE_TYPE, 2);
                intent7.putExtras(bundle3);
                startActivity(intent7);
                Unit unit7 = Unit.INSTANCE;
                return;
            }
            P mPresenter3 = this.mPresenter;
            Intrinsics.checkExpressionValueIsNotNull(mPresenter3, "mPresenter");
            if (((MyDataPresenter) mPresenter3).isBind()) {
                Intent intent8 = new Intent(this, (Class<?>) HasBindNoDataActivity.class);
                intent8.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 2);
                startActivity(intent8);
                Unit unit8 = Unit.INSTANCE;
                return;
            }
            Intent intent9 = new Intent(this, (Class<?>) UnBindNoDataActivity.class);
            intent9.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 2);
            startActivity(intent9);
            Unit unit9 = Unit.INSTANCE;
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_stronger_duration) {
            if (((MyDataPresenter) this.mPresenter).hasActivityData()) {
                Intent intent10 = new Intent(this, (Class<?>) ChartDetailActivity.class);
                Bundle bundle4 = new Bundle();
                bundle4.putInt(ChartDetailActivity.PAGE_TYPE, 15);
                intent10.putExtras(bundle4);
                startActivity(intent10);
                Unit unit10 = Unit.INSTANCE;
                return;
            }
            P mPresenter4 = this.mPresenter;
            Intrinsics.checkExpressionValueIsNotNull(mPresenter4, "mPresenter");
            if (((MyDataPresenter) mPresenter4).isBind()) {
                Intent intent11 = new Intent(this, (Class<?>) HasBindNoDataActivity.class);
                intent11.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 3);
                startActivity(intent11);
                Unit unit11 = Unit.INSTANCE;
                return;
            }
            Intent intent12 = new Intent(this, (Class<?>) UnBindNoDataActivity.class);
            intent12.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 3);
            startActivity(intent12);
            Unit unit12 = Unit.INSTANCE;
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_walk_duration) {
            if (((MyDataPresenter) this.mPresenter).hasWalkData()) {
                Intent intent13 = new Intent(this, (Class<?>) DetailActivity.class);
                intent13.putExtra(DetailActivity.DATA_TYPE, 4);
                startActivity(intent13);
                Unit unit13 = Unit.INSTANCE;
                return;
            }
            P mPresenter5 = this.mPresenter;
            Intrinsics.checkExpressionValueIsNotNull(mPresenter5, "mPresenter");
            if (((MyDataPresenter) mPresenter5).isBind()) {
                Intent intent14 = new Intent(this, (Class<?>) HasBindNoDataActivity.class);
                intent14.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 4);
                startActivity(intent14);
                Unit unit14 = Unit.INSTANCE;
                return;
            }
            Intent intent15 = new Intent(this, (Class<?>) UnBindNoDataActivity.class);
            intent15.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 4);
            startActivity(intent15);
            Unit unit15 = Unit.INSTANCE;
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_heart_rate) {
            if (((MyDataPresenter) this.mPresenter).hasHeartRate()) {
                Intent intent16 = new Intent(this, (Class<?>) ChartDetailActivity.class);
                Bundle bundle5 = new Bundle();
                bundle5.putInt(ChartDetailActivity.PAGE_TYPE, 8);
                intent16.putExtras(bundle5);
                startActivity(intent16);
                Unit unit16 = Unit.INSTANCE;
                return;
            }
            P mPresenter6 = this.mPresenter;
            Intrinsics.checkExpressionValueIsNotNull(mPresenter6, "mPresenter");
            if (((MyDataPresenter) mPresenter6).isBind()) {
                Intent intent17 = new Intent(this, (Class<?>) HasBindNoDataActivity.class);
                intent17.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 8);
                startActivity(intent17);
                Unit unit17 = Unit.INSTANCE;
                return;
            }
            Intent intent18 = new Intent(this, (Class<?>) UnBindNoDataActivity.class);
            intent18.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 8);
            startActivity(intent18);
            Unit unit18 = Unit.INSTANCE;
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_sleep) {
            if (((MyDataPresenter) this.mPresenter).hasSleepData()) {
                Intent intent19 = new Intent(this, (Class<?>) DetailActivity.class);
                intent19.putExtra(DetailActivity.DATA_TYPE, 7);
                startActivity(intent19);
                Unit unit19 = Unit.INSTANCE;
                return;
            }
            P mPresenter7 = this.mPresenter;
            Intrinsics.checkExpressionValueIsNotNull(mPresenter7, "mPresenter");
            if (((MyDataPresenter) mPresenter7).isBind()) {
                Intent intent20 = new Intent(this, (Class<?>) HasBindNoDataActivity.class);
                intent20.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 7);
                startActivity(intent20);
                Unit unit20 = Unit.INSTANCE;
                return;
            }
            Intent intent21 = new Intent(this, (Class<?>) UnBindNoDataActivity.class);
            intent21.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 7);
            startActivity(intent21);
            Unit unit21 = Unit.INSTANCE;
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_pressure) {
            if (((MyDataPresenter) this.mPresenter).hasPressure()) {
                Intent intent22 = new Intent(this, (Class<?>) ChartDetailActivity.class);
                Bundle bundle6 = new Bundle();
                bundle6.putInt(ChartDetailActivity.PAGE_TYPE, 9);
                intent22.putExtras(bundle6);
                startActivity(intent22);
                Unit unit22 = Unit.INSTANCE;
                return;
            }
            P mPresenter8 = this.mPresenter;
            Intrinsics.checkExpressionValueIsNotNull(mPresenter8, "mPresenter");
            if (((MyDataPresenter) mPresenter8).isBind()) {
                Intent intent23 = new Intent(this, (Class<?>) HasBindNoDataActivity.class);
                intent23.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 9);
                startActivity(intent23);
                Unit unit23 = Unit.INSTANCE;
                return;
            }
            Intent intent24 = new Intent(this, (Class<?>) UnBindNoDataActivity.class);
            intent24.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 9);
            startActivity(intent24);
            Unit unit24 = Unit.INSTANCE;
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_oxy) {
            if (((MyDataPresenter) this.mPresenter).hasBloodOxyData()) {
                Intent intent25 = new Intent(this, (Class<?>) DetailActivity.class);
                intent25.putExtra(DetailActivity.DATA_TYPE, 10);
                startActivity(intent25);
                Unit unit25 = Unit.INSTANCE;
                return;
            }
            P mPresenter9 = this.mPresenter;
            Intrinsics.checkExpressionValueIsNotNull(mPresenter9, "mPresenter");
            if (((MyDataPresenter) mPresenter9).isBind()) {
                Intent intent26 = new Intent(this, (Class<?>) HasBindNoDataActivity.class);
                intent26.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 10);
                startActivity(intent26);
                Unit unit26 = Unit.INSTANCE;
                return;
            }
            Intent intent27 = new Intent(this, (Class<?>) UnBindNoDataActivity.class);
            intent27.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 10);
            startActivity(intent27);
            Unit unit27 = Unit.INSTANCE;
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_weight) {
            Intent intent28 = new Intent(this, (Class<?>) ChartDetailActivity.class);
            Bundle bundle7 = new Bundle();
            bundle7.putInt(ChartDetailActivity.PAGE_TYPE, 11);
            intent28.putExtras(bundle7);
            startActivity(intent28);
            Unit unit28 = Unit.INSTANCE;
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_sport) {
            if (((MyDataPresenter) this.mPresenter).hasSportRecord()) {
                startActivity(new Intent(this, (Class<?>) SportRecordActivity.class));
                return;
            }
            Intent intent29 = new Intent(this, (Class<?>) UnBindNoDataActivity.class);
            intent29.putExtra(HasBindNoDataActivity.INSTANCE.getKEY_TYPE(), 6);
            startActivity(intent29);
            supportFinishAfterTransition();
            Unit unit29 = Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage<?> message) {
        super.handleMessage(message);
        if (message == null || message.getType() != 841) {
            return;
        }
        ((MyDataPresenter) this.mPresenter).getPageData();
    }
}