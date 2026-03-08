package com.ido.life.module.user.userdata.message;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.database.model.MessageEntity;
import com.ido.life.util.DateUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserMessageActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0014J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/ido/life/module/user/userdata/message/UserMessageActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/user/userdata/message/UserMessagePresenter;", "Lcom/ido/life/module/user/userdata/message/IUserMessageView;", "Landroid/view/View$OnClickListener;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getLayoutResId", "", "initLabelLanguage", "", "onClick", "v", "Landroid/view/View;", "onReceiveNewHealthMessage", "entity", "Lcom/ido/life/database/model/MessageEntity;", "hasUnRead", "", "onReceiveNewMedalMessage", "onReceiveOtherMessage", "app_release"}, k = 1, mv = {1, 1, 16})
public final class UserMessageActivity extends BaseActivity<UserMessagePresenter> implements IUserMessageView, View.OnClickListener {
    private final String TAG = UserMessageActivity.class.getSimpleName();
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
        return R.layout.activity_user_message_layout;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        LinearLayout lay_message = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_message);
        Intrinsics.checkExpressionValueIsNotNull(lay_message, "lay_message");
        lay_message.setVisibility(8);
        View lay_empty = _$_findCachedViewById(com.ido.life.R.id.lay_empty);
        Intrinsics.checkExpressionValueIsNotNull(lay_empty, "lay_empty");
        lay_empty.setVisibility(0);
        this.mTitleBar.setTitle(getLanguageText(R.string.title_message));
        RegularTextView tv_title_medal = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_medal);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_medal, "tv_title_medal");
        tv_title_medal.setText(getLanguageText(R.string.title_medal));
        RegularTextView tv_title_health = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_health);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_health, "tv_title_health");
        tv_title_health.setText(getLanguageText(R.string.title_health));
        RegularTextView tv_title_other = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_other);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_other, "tv_title_other");
        tv_title_other.setText(getLanguageText(R.string.me_other_ios));
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        calendar.setFirstDayOfWeek(runTimeUtil.getWeekStart());
        calendar.set(7, calendar.getFirstDayOfWeek());
        TextView tv_health_date = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_health_date);
        Intrinsics.checkExpressionValueIsNotNull(tv_health_date, "tv_health_date");
        tv_health_date.setText(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DM_1));
        ((UserMessagePresenter) this.mPresenter).getMessageList();
    }

    @Override // com.ido.life.module.user.userdata.message.IUserMessageView
    public void onReceiveNewMedalMessage(MessageEntity entity, boolean hasUnRead) {
        Intrinsics.checkParameterIsNotNull(entity, "entity");
        CommonLogUtil.d(this.TAG, hasUnRead ? "有未读勋章消息" : "勋章消息全部已读");
        View lay_empty = _$_findCachedViewById(com.ido.life.R.id.lay_empty);
        Intrinsics.checkExpressionValueIsNotNull(lay_empty, "lay_empty");
        lay_empty.setVisibility(8);
        ConstraintLayout lay_other = (ConstraintLayout) _$_findCachedViewById(com.ido.life.R.id.lay_other);
        Intrinsics.checkExpressionValueIsNotNull(lay_other, "lay_other");
        lay_other.setVisibility(8);
        ConstraintLayout lay_health = (ConstraintLayout) _$_findCachedViewById(com.ido.life.R.id.lay_health);
        Intrinsics.checkExpressionValueIsNotNull(lay_health, "lay_health");
        lay_health.setVisibility(8);
        LinearLayout lay_message = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_message);
        Intrinsics.checkExpressionValueIsNotNull(lay_message, "lay_message");
        lay_message.setVisibility(0);
        ConstraintLayout lay_medal = (ConstraintLayout) _$_findCachedViewById(com.ido.life.R.id.lay_medal);
        Intrinsics.checkExpressionValueIsNotNull(lay_medal, "lay_medal");
        lay_medal.setVisibility(0);
        RegularTextView tv_medal_desc = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_medal_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_medal_desc, "tv_medal_desc");
        tv_medal_desc.setText(getLanguageText(R.string.message_receive_new_medal));
        ImageView img_new_medal = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_new_medal);
        Intrinsics.checkExpressionValueIsNotNull(img_new_medal, "img_new_medal");
        img_new_medal.setVisibility(hasUnRead ? 0 : 8);
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTimeInMillis(entity.getCreateTime());
        TextView tv_medal_date = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_medal_date);
        Intrinsics.checkExpressionValueIsNotNull(tv_medal_date, "tv_medal_date");
        tv_medal_date.setText(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DM_1));
    }

    @Override // com.ido.life.module.user.userdata.message.IUserMessageView
    public void onReceiveNewHealthMessage(MessageEntity entity, boolean hasUnRead) {
        Intrinsics.checkParameterIsNotNull(entity, "entity");
        CommonLogUtil.d(this.TAG, hasUnRead ? "有未读健康周报消息" : "没有未读健康周报消息");
        View lay_empty = _$_findCachedViewById(com.ido.life.R.id.lay_empty);
        Intrinsics.checkExpressionValueIsNotNull(lay_empty, "lay_empty");
        lay_empty.setVisibility(8);
        ConstraintLayout lay_medal = (ConstraintLayout) _$_findCachedViewById(com.ido.life.R.id.lay_medal);
        Intrinsics.checkExpressionValueIsNotNull(lay_medal, "lay_medal");
        lay_medal.setVisibility(8);
        ConstraintLayout lay_other = (ConstraintLayout) _$_findCachedViewById(com.ido.life.R.id.lay_other);
        Intrinsics.checkExpressionValueIsNotNull(lay_other, "lay_other");
        lay_other.setVisibility(8);
        LinearLayout lay_message = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_message);
        Intrinsics.checkExpressionValueIsNotNull(lay_message, "lay_message");
        lay_message.setVisibility(0);
        ConstraintLayout lay_health = (ConstraintLayout) _$_findCachedViewById(com.ido.life.R.id.lay_health);
        Intrinsics.checkExpressionValueIsNotNull(lay_health, "lay_health");
        lay_health.setVisibility(0);
        RegularTextView tv_medal_health = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_medal_health);
        Intrinsics.checkExpressionValueIsNotNull(tv_medal_health, "tv_medal_health");
        tv_medal_health.setText(getLanguageText(R.string.message_receive_new_health));
        ImageView img_new_health = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_new_health);
        Intrinsics.checkExpressionValueIsNotNull(img_new_health, "img_new_health");
        img_new_health.setVisibility(hasUnRead ? 0 : 8);
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        int weekStart = runTimeUtil.getWeekStart();
        if (weekStart == 2) {
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(DateUtil.string2Date(entity.getStartDayMonday(), DateUtil.DATE_FORMAT_YMD));
        } else if (weekStart == 7) {
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(DateUtil.string2Date(entity.getStartDaySaturday(), DateUtil.DATE_FORMAT_YMD));
        } else {
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(DateUtil.string2Date(entity.getStartDaySunday(), DateUtil.DATE_FORMAT_YMD));
        }
        calendar.add(5, 7);
        if (calendar.get(1) == Calendar.getInstance(Locale.CHINA).get(1)) {
            TextView tv_health_date = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_health_date);
            Intrinsics.checkExpressionValueIsNotNull(tv_health_date, "tv_health_date");
            tv_health_date.setText(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DM_1));
        } else {
            TextView tv_health_date2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_health_date);
            Intrinsics.checkExpressionValueIsNotNull(tv_health_date2, "tv_health_date");
            tv_health_date2.setText(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DMY_1));
        }
    }

    @Override // com.ido.life.module.user.userdata.message.IUserMessageView
    public void onReceiveOtherMessage(MessageEntity entity, boolean hasUnRead) {
        Intrinsics.checkParameterIsNotNull(entity, "entity");
        CommonLogUtil.d(this.TAG, hasUnRead ? "有其它未读消息" : "没有其它未读消息");
        View lay_empty = _$_findCachedViewById(com.ido.life.R.id.lay_empty);
        Intrinsics.checkExpressionValueIsNotNull(lay_empty, "lay_empty");
        lay_empty.setVisibility(8);
        ConstraintLayout lay_medal = (ConstraintLayout) _$_findCachedViewById(com.ido.life.R.id.lay_medal);
        Intrinsics.checkExpressionValueIsNotNull(lay_medal, "lay_medal");
        lay_medal.setVisibility(8);
        ConstraintLayout lay_health = (ConstraintLayout) _$_findCachedViewById(com.ido.life.R.id.lay_health);
        Intrinsics.checkExpressionValueIsNotNull(lay_health, "lay_health");
        lay_health.setVisibility(8);
        LinearLayout lay_message = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_message);
        Intrinsics.checkExpressionValueIsNotNull(lay_message, "lay_message");
        lay_message.setVisibility(0);
        ConstraintLayout lay_other = (ConstraintLayout) _$_findCachedViewById(com.ido.life.R.id.lay_other);
        Intrinsics.checkExpressionValueIsNotNull(lay_other, "lay_other");
        lay_other.setVisibility(0);
        ImageView img_new_other = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_new_other);
        Intrinsics.checkExpressionValueIsNotNull(img_new_other, "img_new_other");
        img_new_other.setVisibility(hasUnRead ? 0 : 8);
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTimeInMillis(entity.getCreateTime());
        TextView tv_other_date = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_other_date);
        Intrinsics.checkExpressionValueIsNotNull(tv_other_date, "tv_other_date");
        tv_other_date.setText(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DM_1));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_medal) {
            Intent intent = new Intent(this, (Class<?>) UserMessageListActivity.class);
            intent.putExtra(UserMessageListActivity.INSTANCE.getPAGE_TYPE(), UserMessageListActivity.INSTANCE.getTYPE_MEDAL());
            startActivity(intent);
        } else if (numValueOf != null && numValueOf.intValue() == R.id.lay_health) {
            Intent intent2 = new Intent(this, (Class<?>) UserMessageListActivity.class);
            intent2.putExtra(UserMessageListActivity.INSTANCE.getPAGE_TYPE(), UserMessageListActivity.INSTANCE.getTYPE_HEALTH());
            startActivity(intent2);
        } else if (numValueOf != null && numValueOf.intValue() == R.id.lay_other) {
            Intent intent3 = new Intent(this, (Class<?>) UserMessageListActivity.class);
            intent3.putExtra(UserMessageListActivity.INSTANCE.getPAGE_TYPE(), UserMessageListActivity.INSTANCE.getTYPE_OTHER());
            startActivity(intent3);
        }
    }
}