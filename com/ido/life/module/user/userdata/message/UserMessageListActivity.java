package com.ido.life.module.user.userdata.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.database.model.MessageEntity;
import com.ido.life.enums.UserModelEnum;
import com.ido.life.module.home.chartdetail.vertical.ChartDetailActivity;
import com.ido.life.module.user.healthreport.HealthReportNewActivity;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserMessageListActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \"2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\"B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001b\u001a\u00020\u0013H\u0014J\b\u0010\u001c\u001a\u00020\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u001dH\u0016J\u0012\u0010\u001f\u001a\u00020\u001d2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017¨\u0006#"}, d2 = {"Lcom/ido/life/module/user/userdata/message/UserMessageListActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/user/userdata/message/UserMessageListPresenter;", "Landroid/view/View$OnClickListener;", "()V", "mAdapter", "Lcom/ido/life/customview/recyclerview/CommonRecyclerViewAdapter;", "Lcom/ido/life/database/model/MessageEntity;", "getMAdapter", "()Lcom/ido/life/customview/recyclerview/CommonRecyclerViewAdapter;", "setMAdapter", "(Lcom/ido/life/customview/recyclerview/CommonRecyclerViewAdapter;)V", "mList", "", "getMList", "()Ljava/util/List;", "setMList", "(Ljava/util/List;)V", "mPageType", "", "getMPageType", "()I", "setMPageType", "(I)V", "mWeekStart", "getMWeekStart", "setMWeekStart", "getLayoutResId", "initLabelLanguage", "", "initViews", "onClick", "v", "Landroid/view/View;", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class UserMessageListActivity extends BaseActivity<UserMessageListPresenter> implements View.OnClickListener {
    private HashMap _$_findViewCache;
    private CommonRecyclerViewAdapter<MessageEntity> mAdapter;
    private List<MessageEntity> mList = new ArrayList();
    private int mPageType;
    private int mWeekStart;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String PAGE_TYPE = ChartDetailActivity.PAGE_TYPE;
    private static final int TYPE_MEDAL = 1;
    private static final int TYPE_HEALTH = 2;
    private static final int TYPE_OTHER = 3;

    public static final String getPAGE_TYPE() {
        Companion companion = INSTANCE;
        return PAGE_TYPE;
    }

    public static final int getTYPE_HEALTH() {
        Companion companion = INSTANCE;
        return TYPE_HEALTH;
    }

    public static final int getTYPE_MEDAL() {
        Companion companion = INSTANCE;
        return TYPE_MEDAL;
    }

    public static final int getTYPE_OTHER() {
        Companion companion = INSTANCE;
        return TYPE_OTHER;
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
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_user_message_list_layout;
    }

    /* JADX INFO: compiled from: UserMessageListActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u00020\t8\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u0002\u001a\u0004\b\u000f\u0010\fR\u001c\u0010\u0010\u001a\u00020\t8\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0002\u001a\u0004\b\u0012\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/ido/life/module/user/userdata/message/UserMessageListActivity$Companion;", "", "()V", "PAGE_TYPE", "", "PAGE_TYPE$annotations", "getPAGE_TYPE", "()Ljava/lang/String;", "TYPE_HEALTH", "", "TYPE_HEALTH$annotations", "getTYPE_HEALTH", "()I", "TYPE_MEDAL", "TYPE_MEDAL$annotations", "getTYPE_MEDAL", "TYPE_OTHER", "TYPE_OTHER$annotations", "getTYPE_OTHER", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void PAGE_TYPE$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_HEALTH$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_MEDAL$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void TYPE_OTHER$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getPAGE_TYPE() {
            return UserMessageListActivity.PAGE_TYPE;
        }

        public final int getTYPE_MEDAL() {
            return UserMessageListActivity.TYPE_MEDAL;
        }

        public final int getTYPE_HEALTH() {
            return UserMessageListActivity.TYPE_HEALTH;
        }

        public final int getTYPE_OTHER() {
            return UserMessageListActivity.TYPE_OTHER;
        }
    }

    public final int getMPageType() {
        return this.mPageType;
    }

    public final void setMPageType(int i) {
        this.mPageType = i;
    }

    public final CommonRecyclerViewAdapter<MessageEntity> getMAdapter() {
        return this.mAdapter;
    }

    public final void setMAdapter(CommonRecyclerViewAdapter<MessageEntity> commonRecyclerViewAdapter) {
        this.mAdapter = commonRecyclerViewAdapter;
    }

    public final List<MessageEntity> getMList() {
        return this.mList;
    }

    public final void setMList(List<MessageEntity> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.mList = list;
    }

    public final int getMWeekStart() {
        return this.mWeekStart;
    }

    public final void setMWeekStart(int i) {
        this.mWeekStart = i;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mPageType = getIntent().getIntExtra(PAGE_TYPE, this.mPageType);
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        this.mWeekStart = runTimeUtil.getWeekStart();
        if (this.mPageType == 0) {
            finish();
        }
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        int i = this.mPageType;
        if (i == TYPE_MEDAL) {
            this.mTitleBar.setTitle(getLanguageText(R.string.title_medal));
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            List<MessageEntity> listQueryMessageListByType = GreenDaoUtil.queryMessageListByType(runTimeUtil.getUserId(), 2);
            Intrinsics.checkExpressionValueIsNotNull(listQueryMessageListByType, "GreenDaoUtil.queryMessag…ageEntity.TYPE_NEW_MEDAL)");
            this.mList = listQueryMessageListByType;
        } else if (i == TYPE_HEALTH) {
            this.mTitleBar.setTitle(getLanguageText(R.string.title_health));
            RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
            long userId = runTimeUtil2.getUserId();
            RunTimeUtil runTimeUtil3 = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil3, "RunTimeUtil.getInstance()");
            List<MessageEntity> listQueryReportByWeekStart = GreenDaoUtil.queryReportByWeekStart(userId, runTimeUtil3.getWeekStart());
            Intrinsics.checkExpressionValueIsNotNull(listQueryReportByWeekStart, "GreenDaoUtil.queryReport….getInstance().weekStart)");
            this.mList = listQueryReportByWeekStart;
        } else if (i == TYPE_OTHER) {
            this.mTitleBar.setTitle(getLanguageText(R.string.me_other_ios));
            RunTimeUtil runTimeUtil4 = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil4, "RunTimeUtil.getInstance()");
            List<MessageEntity> listQueryMessageListByType2 = GreenDaoUtil.queryMessageListByType(runTimeUtil4.getUserId(), 1);
            Intrinsics.checkExpressionValueIsNotNull(listQueryMessageListByType2, "GreenDaoUtil.queryMessag…eEntity.TYPE_NEW_VERSION)");
            this.mList = listQueryMessageListByType2;
        }
        CommonRecyclerViewAdapter<MessageEntity> commonRecyclerViewAdapter = this.mAdapter;
        if (commonRecyclerViewAdapter != null) {
            if (commonRecyclerViewAdapter != null) {
                commonRecyclerViewAdapter.notifyDataSetChanged();
            }
        } else {
            this.mAdapter = new CommonRecyclerViewAdapter<MessageEntity>(this, R.layout.item_message_list_layout, this.mList) { // from class: com.ido.life.module.user.userdata.message.UserMessageListActivity.initLabelLanguage.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
                public void convert(CommonRecyclerViewHolder holder, MessageEntity t, int position) {
                    View view;
                    ImageView imageView;
                    View view2;
                    TextView textView;
                    View view3;
                    TextView textView2;
                    View view4;
                    ImageView imageView2;
                    View view5;
                    View view6;
                    View view7;
                    ImageView imageView3;
                    View view8;
                    TextView textView3;
                    View view9;
                    TextView textView4;
                    View view10;
                    TextView textView5;
                    View view11;
                    ImageView imageView4;
                    View view12;
                    ImageView imageView5;
                    View view13;
                    TextView textView6;
                    View view14;
                    TextView textView7;
                    View view15;
                    TextView textView8;
                    View view16;
                    TextView textView9;
                    View view17;
                    ImageView imageView6;
                    MessageEntity messageEntity = UserMessageListActivity.this.getMList().get(position);
                    int type = messageEntity.getType();
                    if (type == 1) {
                        if (holder != null && (view4 = holder.itemView) != null && (imageView2 = (ImageView) view4.findViewById(R.id.img)) != null) {
                            imageView2.setImageResource(R.mipmap.message_other);
                        }
                        if (holder != null && (view3 = holder.itemView) != null && (textView2 = (TextView) view3.findViewById(R.id.tv_title)) != null) {
                            textView2.setText(UserMessageListActivity.this.getLanguageText(R.string.me_other_ios));
                        }
                        if (holder != null && (view2 = holder.itemView) != null && (textView = (TextView) view2.findViewById(R.id.tv_desc)) != null) {
                            textView.setVisibility(8);
                        }
                        if (holder != null && (view = holder.itemView) != null && (imageView = (ImageView) view.findViewById(R.id.img_unread)) != null) {
                            imageView.setVisibility(messageEntity.getHasRead() ? 8 : 0);
                        }
                    } else if (type == 2) {
                        if (holder != null && (view11 = holder.itemView) != null && (imageView4 = (ImageView) view11.findViewById(R.id.img)) != null) {
                            imageView4.setImageResource(R.mipmap.message_medal);
                        }
                        if (holder != null && (view10 = holder.itemView) != null && (textView5 = (TextView) view10.findViewById(R.id.tv_title)) != null) {
                            textView5.setText(UserMessageListActivity.this.getLanguageText(R.string.title_medal));
                        }
                        if (holder != null && (view9 = holder.itemView) != null && (textView4 = (TextView) view9.findViewById(R.id.tv_sub_title)) != null) {
                            textView4.setText(UserMessageListActivity.this.getLanguageText(R.string.message_receive_new_medal));
                        }
                        UserModelEnum userModelEnumById = UserModelEnum.INSTANCE.getUserModelEnumById(messageEntity.getSubType());
                        if (holder != null && (view8 = holder.itemView) != null && (textView3 = (TextView) view8.findViewById(R.id.tv_desc)) != null) {
                            UserMessageListActivity userMessageListActivity = UserMessageListActivity.this;
                            Integer numValueOf = userModelEnumById != null ? Integer.valueOf(userModelEnumById.getMedalDescResId()) : null;
                            if (numValueOf == null) {
                                Intrinsics.throwNpe();
                            }
                            textView3.setText(userMessageListActivity.getLanguageText(numValueOf.intValue()));
                        }
                        if (holder != null && (view7 = holder.itemView) != null && (imageView3 = (ImageView) view7.findViewById(R.id.img_unread)) != null) {
                            imageView3.setVisibility(messageEntity.getHasRead() ? 8 : 0);
                        }
                    } else if (type == 3) {
                        if (holder != null && (view17 = holder.itemView) != null && (imageView6 = (ImageView) view17.findViewById(R.id.img)) != null) {
                            imageView6.setImageResource(R.mipmap.message_health);
                        }
                        if (holder != null && (view16 = holder.itemView) != null && (textView9 = (TextView) view16.findViewById(R.id.tv_title)) != null) {
                            textView9.setText(UserMessageListActivity.this.getLanguageText(R.string.title_health));
                        }
                        if (holder != null && (view15 = holder.itemView) != null && (textView8 = (TextView) view15.findViewById(R.id.tv_sub_title)) != null) {
                            textView8.setText(UserMessageListActivity.this.getLanguageText(R.string.message_receive_new_health));
                        }
                        Calendar calendar = Calendar.getInstance(Locale.CHINA);
                        int mWeekStart = UserMessageListActivity.this.getMWeekStart();
                        if (mWeekStart == 2) {
                            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                            calendar.setTime(DateUtil.string2Date(messageEntity.getStartDayMonday(), DateUtil.DATE_FORMAT_YMD));
                        } else if (mWeekStart == 7) {
                            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                            calendar.setTime(DateUtil.string2Date(messageEntity.getStartDaySaturday(), DateUtil.DATE_FORMAT_YMD));
                        } else {
                            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                            calendar.setTime(DateUtil.string2Date(messageEntity.getStartDaySunday(), DateUtil.DATE_FORMAT_YMD));
                        }
                        String str = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DMY_1);
                        calendar.add(5, 6);
                        String str2 = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DM_1);
                        if (holder != null && (view14 = holder.itemView) != null && (textView7 = (TextView) view14.findViewById(R.id.tv_desc)) != null) {
                            textView7.setText(str + '~' + str2);
                        }
                        calendar.add(5, 1);
                        calendar.set(11, 0);
                        calendar.set(12, 0);
                        calendar.set(13, 0);
                        if (holder != null && (view13 = holder.itemView) != null && (textView6 = (TextView) view13.findViewById(R.id.tv_create_date)) != null) {
                            textView6.setText(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DMY) + " 08:00");
                        }
                        if (holder != null && (view12 = holder.itemView) != null && (imageView5 = (ImageView) view12.findViewById(R.id.img_unread)) != null) {
                            imageView5.setVisibility(messageEntity.getHasRead() ? 8 : 0);
                        }
                    }
                    if (holder != null && (view6 = holder.itemView) != null) {
                        view6.setTag(Integer.valueOf(position));
                    }
                    if (holder == null || (view5 = holder.itemView) == null) {
                        return;
                    }
                    view5.setOnClickListener(UserMessageListActivity.this);
                }
            };
            RecyclerView recycler_view = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view);
            Intrinsics.checkExpressionValueIsNotNull(recycler_view, "recycler_view");
            recycler_view.setAdapter(this.mAdapter);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        List<MessageEntity> list;
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_item && (v.getTag() instanceof Integer)) {
            Object tag = v.getTag();
            if (tag == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
            int iIntValue = ((Integer) tag).intValue();
            if (iIntValue <= -1 || (list = this.mList) == null || list.size() <= iIntValue) {
                return;
            }
            MessageEntity messageEntity = this.mList.get(iIntValue);
            if (messageEntity.getType() != 3) {
                return;
            }
            Intent intent = new Intent(this, (Class<?>) HealthReportNewActivity.class);
            Bundle bundle = new Bundle();
            int i = this.mWeekStart;
            if (i == 2) {
                bundle.putString(HealthReportNewActivity.INSTANCE.getDATE(), messageEntity.getStartDayMonday());
            } else if (i == 7) {
                bundle.putString(HealthReportNewActivity.INSTANCE.getDATE(), messageEntity.getStartDaySaturday());
            } else {
                bundle.putString(HealthReportNewActivity.INSTANCE.getDATE(), messageEntity.getStartDaySunday());
            }
            messageEntity.setHasRead(true);
            messageEntity.update();
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}