package com.ido.life.viewholder;

import android.graphics.Point;
import android.graphics.PointF;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.HookClickListenerHelper;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.bean.LatLngBean;
import com.ido.life.customview.HomeIconAnimView;
import com.ido.life.customview.charter.HomeSportRecordChat;
import com.ido.life.data.cache.MotionTypeManager;
import com.ido.life.database.model.SportHealth;
import com.ido.life.module.home.IHomeView;
import com.ido.life.util.DateUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.UnitUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class HomeSportRecordViewHolder extends BaseHomeItemViewHolder {
    private static final long CALUTE_DURATION = 1000;

    @BindView(R.id.anim_view)
    HomeIconAnimView mAnimView;
    private Runnable mCaluteLatScreenPosition;

    @BindView(R.id.img_empty)
    ImageView mImgEmpty;

    @BindView(R.id.img_sport)
    ImageView mImgSport;
    private List<LatLngBean> mLatLngBeanList;

    @BindView(R.id.lay_bottom)
    LinearLayout mLayBottom;

    @BindView(R.id.lay_content)
    LinearLayout mLayContent;

    @BindView(R.id.lay_out)
    LinearLayout mLayOut;

    @BindView(R.id.lay_sport)
    LinearLayout mLaySport;

    @BindView(R.id.lay_top)
    LinearLayout mLayTop;
    private List<PointF> mPointList;

    @BindView(R.id.chat_sport)
    HomeSportRecordChat mRecordChat;
    private SportHealth mSportHealth;

    @BindView(R.id.tv_desc)
    TextView mTvDesc;

    @BindView(R.id.tv_name)
    TextView mTvName;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @BindView(R.id.tv_unit)
    TextView mTvUnit;

    @BindView(R.id.tv_value)
    TextView mTvValue;
    String name;

    public /* synthetic */ void lambda$new$0$HomeSportRecordViewHolder() {
        try {
            this.mPointList.clear();
            List<Point> screenLocation = this.mHomeView.getMap().toScreenLocation(this.mLatLngBeanList);
            if (screenLocation != null && screenLocation.size() > 0) {
                for (Point point : screenLocation) {
                    this.mPointList.add(new PointF(point.x, point.y));
                }
            }
            this.mRecordChat.setList(this.mPointList, this.mLatLngBeanList, this.mSportHealth.getTotalSeconds(), this.mSportHealth.getDistance(), this.mSportHealth.getType());
            this.mRecordChat.invalideWithAnimator(false);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public HomeSportRecordViewHolder(View view, IHomeView iHomeView) {
        super(view, iHomeView);
        this.mPointList = new ArrayList();
        this.mCaluteLatScreenPosition = new Runnable() { // from class: com.ido.life.viewholder.-$$Lambda$HomeSportRecordViewHolder$asyetvgoZ45fNHtwvyEhWZjxKL4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$HomeSportRecordViewHolder();
            }
        };
        this.name = "";
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected void setClickListener(View.OnClickListener onClickListener) {
        this.itemView.setOnClickListener(onClickListener);
        HookClickListenerHelper.hookOnClick(this.itemView);
    }

    @Override // com.ido.life.viewholder.BaseHomeViewHolder
    public void refreshPage() {
        String languageText;
        CommonLogUtil.printAndSave("开始绑定运动数据");
        this.mLayOut.setEnabled(this.mHomeView.hasSportRecord());
        this.mLayContent.removeCallbacks(this.mCaluteLatScreenPosition);
        String languageText2 = LanguageUtil.getLanguageText(R.string.mine_sport_record);
        if (languageText2.length() > 4) {
            languageText2 = LanguageUtil.getLanguageText(R.string.sport_training);
        }
        this.mTvTitle.setText(languageText2);
        this.itemView.setTag(6);
        this.mSportHealth = this.mHomeView.getLastestSportRecord();
        this.mAnimView.setAnimatorIcon(R.mipmap.home_sport_record_anim_icon);
        if (showLeftSpace()) {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_5), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_10), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_10));
        } else {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_10), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_5), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_10));
        }
        this.mHomeView.startUpdateTime();
        this.mTvDesc.setTag(R.id.leftId, null);
        this.mTvDesc.setTag(R.id.rightId, null);
        this.mLayContent.setVisibility(8);
        this.mRecordChat.setVisibility(8);
        this.mImgEmpty.setVisibility(8);
        this.mLaySport.setVisibility(8);
        this.mImgSport.setVisibility(8);
        SportHealth sportHealth = this.mSportHealth;
        if (sportHealth == null || TextUtils.isEmpty(sportHealth.getDateTime())) {
            this.mHasData = false;
            this.mLayTop.setVisibility(8);
            this.mLayBottom.setVisibility(8);
            this.mAnimView.setVisibility(8);
            this.mImgEmpty.setImageResource(R.mipmap.main_sport_record);
            this.mImgEmpty.setVisibility(0);
            this.mLayContent.setVisibility(0);
        } else {
            this.mHasData = true;
            this.mLayTop.setVisibility(0);
            this.mLayBottom.setVisibility(0);
            if (!this.mFirstRefresh && !this.mSportHealth.isUploaded()) {
                Object tag = this.mAnimView.getTag();
                if (tag == null || !TextUtils.equals(this.mSportHealth.getStartTime(), (CharSequence) tag)) {
                    this.mAnimView.stopAnimator();
                    this.mAnimView.setVisibility(0);
                    this.mAnimView.startAnimator();
                } else {
                    this.mAnimView.setVisibility(8);
                }
            } else {
                this.mAnimView.setVisibility(8);
            }
            this.mAnimView.setTag(this.mSportHealth.getStartTime());
            float distance = (this.mSportHealth.getDistance() * 1.0f) / 1000.0f;
            if (RunTimeUtil.getInstance().getUnitSet() == 2) {
                distance = UnitUtil.km2mile(distance);
                languageText = LanguageUtil.getLanguageText(R.string.mile_short);
            } else {
                languageText = LanguageUtil.getLanguageText(R.string.km_short);
            }
            if (distance > 0.0f && distance < 0.01d) {
                distance = 0.01f;
            }
            String strValueOf = String.valueOf(this.mSportHealth.getNumCalories());
            if (!showMap(distance, languageText, strValueOf) && !showColory(strValueOf) && !showDistanceOrCalory(distance, languageText, strValueOf) && !showColoryOrHr(strValueOf)) {
                this.mLayTop.setVisibility(8);
                this.mLayBottom.setVisibility(8);
                this.mImgEmpty.setVisibility(0);
                this.mImgEmpty.setImageResource(R.mipmap.main_sport_record);
            }
            try {
                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                calendar.setTime(DateUtil.string2Date(this.mSportHealth.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
                this.mTvDesc.setText(new SpannableStringBuilder(String.format("%s  %s", this.mHomeView.getDateShowByTimeStamp(calendar.getTimeInMillis()), "")));
                this.mTvDesc.setTag(R.id.leftId, Long.valueOf(calendar.getTimeInMillis()));
                this.mTvDesc.setTag(R.id.rightId, this.name);
                this.mTvName.setText(this.name);
                if (DateUtil.isToday(calendar.getTimeInMillis())) {
                    this.mHomeView.startUpdateTime();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.mFirstRefresh = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean showMap(float r9, java.lang.String r10, java.lang.String r11) {
        /*
            Method dump skipped, instruction units count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.viewholder.HomeSportRecordViewHolder.showMap(float, java.lang.String, java.lang.String):boolean");
    }

    private boolean showColory(String str) {
        boolean z;
        int type = this.mSportHealth.getType();
        if (type == 5 || type == 55 || type == 178) {
            this.name = MotionTypeManager.INSTANCE.getMotionTypeName(this.mSportHealth.getType());
            z = true;
        } else {
            z = false;
        }
        if (z) {
            this.mLayContent.setVisibility(0);
            this.mImgEmpty.setVisibility(8);
            this.mLaySport.setVisibility(0);
            this.mImgSport.setVisibility(0);
            this.mImgSport.setImageResource(MotionTypeManager.INSTANCE.getSportHomeTypeIcon(this.mSportHealth.getType()));
            this.mRecordChat.setVisibility(8);
            this.mTvValue.setText(str);
            this.mTvUnit.setText(RunTimeUtil.getCalorieUnit());
        }
        return z;
    }

    private boolean showDistanceOrCalory(float f2, String str, String str2) {
        boolean z;
        if (this.mSportHealth.getType() != 54) {
            z = false;
        } else {
            this.name = MotionTypeManager.INSTANCE.getMotionTypeName(this.mSportHealth.getType());
            z = true;
        }
        if (z) {
            this.mLayContent.setGravity(80);
            this.mLayContent.setVisibility(0);
            this.mImgEmpty.setVisibility(8);
            this.mLaySport.setVisibility(0);
            this.mImgSport.setVisibility(0);
            this.mImgSport.setImageResource(MotionTypeManager.INSTANCE.getSportHomeTypeIcon(this.mSportHealth.getType()));
            this.mRecordChat.setVisibility(8);
            if (f2 > 0.0f) {
                this.mTvValue.setText(new BigDecimal(String.valueOf(f2)).setScale(2, RoundingMode.HALF_UP).toString());
                this.mTvUnit.setText(str);
            } else {
                this.mTvValue.setText(str2);
                this.mTvUnit.setText(RunTimeUtil.getCalorieUnit());
            }
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0039 A[FALL_THROUGH] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean showColoryOrHr(java.lang.String r6) {
        /*
            Method dump skipped, instruction units count: 408
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.viewholder.HomeSportRecordViewHolder.showColoryOrHr(java.lang.String):boolean");
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected String getDownloadDataName() {
        return SportHealth.class.getSimpleName();
    }
}