package com.ido.life.module.user.userdata.usermedal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.database.model.UserMedalInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.enums.UserModelEnum;
import com.ido.life.module.user.userdata.usermedal.UserMedalActivity;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserMedalAdapter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010(\u001a\u00020\fH\u0016J\u0010\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020\fH\u0016J\u0018\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00022\u0006\u0010*\u001a\u00020\fH\u0016J\u0018\u0010.\u001a\u00020\u00022\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\fH\u0016R\u0014\u0010\u000b\u001a\u00020\fX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000e\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000e\"\u0004\b\u0015\u0010\u0012R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u00062"}, d2 = {"Lcom/ido/life/module/user/userdata/usermedal/UserMedalAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/module/user/userdata/usermedal/UserMedalViewHolder;", "context", "Landroid/content/Context;", "list", "", "Lcom/ido/life/module/user/userdata/usermedal/UserMedalActivity$UserModelEnumInfo;", "clickListener", "Landroid/view/View$OnClickListener;", "(Landroid/content/Context;Ljava/util/List;Landroid/view/View$OnClickListener;)V", "HEADER_ONE", "", "getHEADER_ONE", "()I", "HEADER_TWO", "getHEADER_TWO", "setHEADER_TWO", "(I)V", "USER_MEDAL", "getUSER_MEDAL", "setUSER_MEDAL", "getClickListener", "()Landroid/view/View$OnClickListener;", "setClickListener", "(Landroid/view/View$OnClickListener;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "mLayoutInflater", "Landroid/view/LayoutInflater;", "getMLayoutInflater", "()Landroid/view/LayoutInflater;", "setMLayoutInflater", "(Landroid/view/LayoutInflater;)V", "getItemCount", "getItemViewType", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "app_release"}, k = 1, mv = {1, 1, 16})
public final class UserMedalAdapter extends RecyclerView.Adapter<UserMedalViewHolder> {
    private final int HEADER_ONE;
    private int HEADER_TWO;
    private int USER_MEDAL;
    private View.OnClickListener clickListener;
    private Context context;
    private List<UserMedalActivity.UserModelEnumInfo> list;
    private LayoutInflater mLayoutInflater;

    public final View.OnClickListener getClickListener() {
        return this.clickListener;
    }

    public final Context getContext() {
        return this.context;
    }

    public final List<UserMedalActivity.UserModelEnumInfo> getList() {
        return this.list;
    }

    public final void setClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkParameterIsNotNull(onClickListener, "<set-?>");
        this.clickListener = onClickListener;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    public final void setList(List<UserMedalActivity.UserModelEnumInfo> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.list = list;
    }

    public UserMedalAdapter(Context context, List<UserMedalActivity.UserModelEnumInfo> list, View.OnClickListener clickListener) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(list, "list");
        Intrinsics.checkParameterIsNotNull(clickListener, "clickListener");
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
        this.HEADER_ONE = 1;
        this.HEADER_TWO = 2;
        this.USER_MEDAL = 3;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.context);
        Intrinsics.checkExpressionValueIsNotNull(layoutInflaterFrom, "LayoutInflater.from(context)");
        this.mLayoutInflater = layoutInflaterFrom;
    }

    public final int getHEADER_ONE() {
        return this.HEADER_ONE;
    }

    public final int getHEADER_TWO() {
        return this.HEADER_TWO;
    }

    public final void setHEADER_TWO(int i) {
        this.HEADER_TWO = i;
    }

    public final int getUSER_MEDAL() {
        return this.USER_MEDAL;
    }

    public final void setUSER_MEDAL(int i) {
        this.USER_MEDAL = i;
    }

    public final LayoutInflater getMLayoutInflater() {
        return this.mLayoutInflater;
    }

    public final void setMLayoutInflater(LayoutInflater layoutInflater) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "<set-?>");
        this.mLayoutInflater = layoutInflater;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public UserMedalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View viewInflate = this.mLayoutInflater.inflate(R.layout.item_medal_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "mLayoutInflater.inflate(….item_medal_layout, null)");
        return new UserMedalViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        int i = this.USER_MEDAL;
        if (position == 0) {
            return this.HEADER_ONE;
        }
        return position == 7 ? this.HEADER_TWO : i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<UserMedalActivity.UserModelEnumInfo> list = this.list;
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return this.list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(UserMedalViewHolder holder, int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        int itemViewType = getItemViewType(position);
        holder.getMLayMedal().setOnClickListener(null);
        if (itemViewType == this.USER_MEDAL) {
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            runTimeUtil.getUnitSet();
            UserMedalActivity.UserModelEnumInfo userModelEnumInfo = this.list.get(position);
            RegularTextView mTvTitle = holder.getMTvTitle();
            Intrinsics.checkExpressionValueIsNotNull(mTvTitle, "holder.mTvTitle");
            mTvTitle.setVisibility(8);
            LinearLayout mLayMedal = holder.getMLayMedal();
            Intrinsics.checkExpressionValueIsNotNull(mLayMedal, "holder.mLayMedal");
            mLayMedal.setVisibility(0);
            UserMedalInfo medalInfo = userModelEnumInfo.getMedalInfo();
            UserModelEnum modelEnum = userModelEnumInfo.getModelEnum();
            if (modelEnum != null) {
                switch (modelEnum) {
                    case STEP_TARGET:
                        RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
                        UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(runTimeUtil2.getUserId());
                        if (medalInfo != null) {
                            try {
                                TextView mTvMedalDesc = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc, "holder.mTvMedalDesc");
                                mTvMedalDesc.setText(DateUtil.format(DateUtil.string2Date(medalInfo.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_DMY_1));
                            } catch (Exception e2) {
                                if (userTargetNewQueryUserLatestTarget != null) {
                                    TextView mTvMedalDesc2 = holder.getMTvMedalDesc();
                                    Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc2, "holder.mTvMedalDesc");
                                    mTvMedalDesc2.setText(userTargetNewQueryUserLatestTarget.getStep() + LanguageUtil.getLanguageText(R.string.public_sport_step));
                                } else {
                                    TextView mTvMedalDesc3 = holder.getMTvMedalDesc();
                                    Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc3, "holder.mTvMedalDesc");
                                    mTvMedalDesc3.setText(modelEnum.getTargetValue() + LanguageUtil.getLanguageText(R.string.public_sport_step));
                                }
                                e2.printStackTrace();
                            }
                        } else if (userTargetNewQueryUserLatestTarget != null) {
                            TextView mTvMedalDesc4 = holder.getMTvMedalDesc();
                            Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc4, "holder.mTvMedalDesc");
                            mTvMedalDesc4.setText(userTargetNewQueryUserLatestTarget.getStep() + LanguageUtil.getLanguageText(R.string.public_sport_step));
                        } else {
                            TextView mTvMedalDesc5 = holder.getMTvMedalDesc();
                            Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc5, "holder.mTvMedalDesc");
                            mTvMedalDesc5.setText(modelEnum.getTargetValue() + LanguageUtil.getLanguageText(R.string.public_sport_step));
                        }
                        break;
                    case STEP_7:
                        if (medalInfo == null) {
                            TextView mTvMedalDesc6 = holder.getMTvMedalDesc();
                            Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc6, "holder.mTvMedalDesc");
                            String languageText = LanguageUtil.getLanguageText(R.string.medal_target_step_format);
                            Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…medal_target_step_format)");
                            Object[] objArr = {Integer.valueOf(modelEnum.getTargetValue())};
                            String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
                            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                            mTvMedalDesc6.setText(str);
                        } else {
                            try {
                                TextView mTvMedalDesc7 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc7, "holder.mTvMedalDesc");
                                mTvMedalDesc7.setText(DateUtil.format(DateUtil.string2Date(medalInfo.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_DMY_1));
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                TextView mTvMedalDesc8 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc8, "holder.mTvMedalDesc");
                                String languageText2 = LanguageUtil.getLanguageText(R.string.medal_target_step_format);
                                Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…medal_target_step_format)");
                                Object[] objArr2 = {Integer.valueOf(modelEnum.getTargetValue())};
                                String str2 = String.format(languageText2, Arrays.copyOf(objArr2, objArr2.length));
                                Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(this, *args)");
                                mTvMedalDesc8.setText(str2);
                            }
                        }
                        break;
                    case STEP_21:
                        if (medalInfo == null) {
                            TextView mTvMedalDesc9 = holder.getMTvMedalDesc();
                            Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc9, "holder.mTvMedalDesc");
                            String languageText3 = LanguageUtil.getLanguageText(R.string.medal_target_step_format);
                            Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…medal_target_step_format)");
                            Object[] objArr3 = {Integer.valueOf(modelEnum.getTargetValue())};
                            String str3 = String.format(languageText3, Arrays.copyOf(objArr3, objArr3.length));
                            Intrinsics.checkNotNullExpressionValue(str3, "java.lang.String.format(this, *args)");
                            mTvMedalDesc9.setText(str3);
                        } else {
                            try {
                                TextView mTvMedalDesc10 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc10, "holder.mTvMedalDesc");
                                mTvMedalDesc10.setText(DateUtil.format(DateUtil.string2Date(medalInfo.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_DMY_1));
                            } catch (Exception e4) {
                                e4.printStackTrace();
                                TextView mTvMedalDesc11 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc11, "holder.mTvMedalDesc");
                                String languageText4 = LanguageUtil.getLanguageText(R.string.medal_target_step_format);
                                Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…medal_target_step_format)");
                                Object[] objArr4 = {Integer.valueOf(modelEnum.getTargetValue())};
                                String str4 = String.format(languageText4, Arrays.copyOf(objArr4, objArr4.length));
                                Intrinsics.checkNotNullExpressionValue(str4, "java.lang.String.format(this, *args)");
                                mTvMedalDesc11.setText(str4);
                            }
                        }
                        break;
                    case ACTIVE_TARGET:
                        if (medalInfo == null) {
                            TextView mTvMedalDesc12 = holder.getMTvMedalDesc();
                            Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc12, "holder.mTvMedalDesc");
                            mTvMedalDesc12.setText(LanguageUtil.getLanguageText(R.string.medal_target_active_first));
                        } else {
                            try {
                                TextView mTvMedalDesc13 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc13, "holder.mTvMedalDesc");
                                mTvMedalDesc13.setText(DateUtil.format(DateUtil.string2Date(medalInfo.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_DMY_1));
                            } catch (Exception e5) {
                                e5.printStackTrace();
                                TextView mTvMedalDesc14 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc14, "holder.mTvMedalDesc");
                                mTvMedalDesc14.setText(LanguageUtil.getLanguageText(R.string.medal_target_active_first));
                            }
                        }
                        break;
                    case ACTIVE_7:
                        if (medalInfo == null) {
                            TextView mTvMedalDesc15 = holder.getMTvMedalDesc();
                            Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc15, "holder.mTvMedalDesc");
                            String languageText5 = LanguageUtil.getLanguageText(R.string.medal_target_step_format);
                            Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…medal_target_step_format)");
                            Object[] objArr5 = {Integer.valueOf(modelEnum.getTargetValue())};
                            String str5 = String.format(languageText5, Arrays.copyOf(objArr5, objArr5.length));
                            Intrinsics.checkNotNullExpressionValue(str5, "java.lang.String.format(this, *args)");
                            mTvMedalDesc15.setText(str5);
                        } else {
                            try {
                                TextView mTvMedalDesc16 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc16, "holder.mTvMedalDesc");
                                mTvMedalDesc16.setText(DateUtil.format(DateUtil.string2Date(medalInfo.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_DMY_1));
                            } catch (Exception e6) {
                                e6.printStackTrace();
                                TextView mTvMedalDesc17 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc17, "holder.mTvMedalDesc");
                                String languageText6 = LanguageUtil.getLanguageText(R.string.medal_target_step_format);
                                Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…medal_target_step_format)");
                                Object[] objArr6 = {Integer.valueOf(modelEnum.getTargetValue())};
                                String str6 = String.format(languageText6, Arrays.copyOf(objArr6, objArr6.length));
                                Intrinsics.checkNotNullExpressionValue(str6, "java.lang.String.format(this, *args)");
                                mTvMedalDesc17.setText(str6);
                            }
                        }
                        break;
                    case ACTIVE_21:
                        if (medalInfo == null) {
                            TextView mTvMedalDesc18 = holder.getMTvMedalDesc();
                            Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc18, "holder.mTvMedalDesc");
                            String languageText7 = LanguageUtil.getLanguageText(R.string.medal_target_step_format);
                            Intrinsics.checkExpressionValueIsNotNull(languageText7, "LanguageUtil.getLanguage…medal_target_step_format)");
                            Object[] objArr7 = {Integer.valueOf(modelEnum.getTargetValue())};
                            String str7 = String.format(languageText7, Arrays.copyOf(objArr7, objArr7.length));
                            Intrinsics.checkNotNullExpressionValue(str7, "java.lang.String.format(this, *args)");
                            mTvMedalDesc18.setText(str7);
                        } else {
                            try {
                                TextView mTvMedalDesc19 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc19, "holder.mTvMedalDesc");
                                mTvMedalDesc19.setText(DateUtil.format(DateUtil.string2Date(medalInfo.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_DMY_1));
                            } catch (Exception e7) {
                                e7.printStackTrace();
                                TextView mTvMedalDesc20 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc20, "holder.mTvMedalDesc");
                                String languageText8 = LanguageUtil.getLanguageText(R.string.medal_target_step_format);
                                Intrinsics.checkExpressionValueIsNotNull(languageText8, "LanguageUtil.getLanguage…medal_target_step_format)");
                                Object[] objArr8 = {Integer.valueOf(modelEnum.getTargetValue())};
                                String str8 = String.format(languageText8, Arrays.copyOf(objArr8, objArr8.length));
                                Intrinsics.checkNotNullExpressionValue(str8, "java.lang.String.format(this, *args)");
                                mTvMedalDesc20.setText(str8);
                            }
                        }
                        break;
                    case FIRST_RUN_OUTSIDE:
                        if (medalInfo == null) {
                            TextView mTvMedalDesc21 = holder.getMTvMedalDesc();
                            Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc21, "holder.mTvMedalDesc");
                            mTvMedalDesc21.setText(modelEnum.getTargetValue() + LanguageUtil.getLanguageText(R.string.km_short));
                        } else {
                            try {
                                TextView mTvMedalDesc22 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc22, "holder.mTvMedalDesc");
                                mTvMedalDesc22.setText(DateUtil.format(DateUtil.string2Date(medalInfo.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_DMY_1));
                            } catch (Exception e8) {
                                e8.printStackTrace();
                                TextView mTvMedalDesc23 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc23, "holder.mTvMedalDesc");
                                mTvMedalDesc23.setText(modelEnum.getTargetValue() + LanguageUtil.getLanguageText(R.string.km_short));
                            }
                        }
                        break;
                    case FIRST_RUN_HOME:
                        if (medalInfo == null) {
                            TextView mTvMedalDesc24 = holder.getMTvMedalDesc();
                            Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc24, "holder.mTvMedalDesc");
                            mTvMedalDesc24.setText(modelEnum.getTargetValue() + LanguageUtil.getLanguageText(R.string.km_short));
                        } else {
                            try {
                                TextView mTvMedalDesc25 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc25, "holder.mTvMedalDesc");
                                mTvMedalDesc25.setText(DateUtil.format(DateUtil.string2Date(medalInfo.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_DMY_1));
                            } catch (Exception e9) {
                                e9.printStackTrace();
                                TextView mTvMedalDesc26 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc26, "holder.mTvMedalDesc");
                                mTvMedalDesc26.setText(modelEnum.getTargetValue() + LanguageUtil.getLanguageText(R.string.km_short));
                            }
                        }
                        break;
                    case FIRST_WALK_OUTSIDE:
                        if (medalInfo == null) {
                            TextView mTvMedalDesc27 = holder.getMTvMedalDesc();
                            Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc27, "holder.mTvMedalDesc");
                            mTvMedalDesc27.setText(modelEnum.getTargetValue() + LanguageUtil.getLanguageText(R.string.km_short));
                        } else {
                            try {
                                TextView mTvMedalDesc28 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc28, "holder.mTvMedalDesc");
                                mTvMedalDesc28.setText(DateUtil.format(DateUtil.string2Date(medalInfo.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_DMY_1));
                            } catch (Exception e10) {
                                e10.printStackTrace();
                                TextView mTvMedalDesc29 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc29, "holder.mTvMedalDesc");
                                mTvMedalDesc29.setText(modelEnum.getTargetValue() + LanguageUtil.getLanguageText(R.string.km_short));
                            }
                        }
                        break;
                    case FIRST_WALK_HOME:
                        if (medalInfo == null) {
                            TextView mTvMedalDesc30 = holder.getMTvMedalDesc();
                            Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc30, "holder.mTvMedalDesc");
                            mTvMedalDesc30.setText(modelEnum.getTargetValue() + LanguageUtil.getLanguageText(R.string.km_short));
                        } else {
                            try {
                                TextView mTvMedalDesc31 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc31, "holder.mTvMedalDesc");
                                mTvMedalDesc31.setText(DateUtil.format(DateUtil.string2Date(medalInfo.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_DMY_1));
                            } catch (Exception e11) {
                                e11.printStackTrace();
                                TextView mTvMedalDesc32 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc32, "holder.mTvMedalDesc");
                                mTvMedalDesc32.setText(modelEnum.getTargetValue() + LanguageUtil.getLanguageText(R.string.km_short));
                            }
                        }
                        break;
                    case FIRST_WALK:
                        if (medalInfo == null) {
                            TextView mTvMedalDesc33 = holder.getMTvMedalDesc();
                            Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc33, "holder.mTvMedalDesc");
                            mTvMedalDesc33.setText(modelEnum.getTargetValue() + LanguageUtil.getLanguageText(R.string.km_short));
                        } else {
                            try {
                                TextView mTvMedalDesc34 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc34, "holder.mTvMedalDesc");
                                mTvMedalDesc34.setText(DateUtil.format(DateUtil.string2Date(medalInfo.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_DMY_1));
                            } catch (Exception e12) {
                                e12.printStackTrace();
                                TextView mTvMedalDesc35 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc35, "holder.mTvMedalDesc");
                                mTvMedalDesc35.setText(modelEnum.getTargetValue() + LanguageUtil.getLanguageText(R.string.km_short));
                            }
                        }
                        break;
                    case FIRST_DRIVE_OUTSIDE:
                        if (medalInfo == null) {
                            TextView mTvMedalDesc36 = holder.getMTvMedalDesc();
                            Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc36, "holder.mTvMedalDesc");
                            mTvMedalDesc36.setText(modelEnum.getTargetValue() + LanguageUtil.getLanguageText(R.string.km_short));
                        } else {
                            try {
                                TextView mTvMedalDesc37 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc37, "holder.mTvMedalDesc");
                                mTvMedalDesc37.setText(DateUtil.format(DateUtil.string2Date(medalInfo.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_DMY_1));
                            } catch (Exception e13) {
                                e13.printStackTrace();
                                TextView mTvMedalDesc38 = holder.getMTvMedalDesc();
                                Intrinsics.checkExpressionValueIsNotNull(mTvMedalDesc38, "holder.mTvMedalDesc");
                                mTvMedalDesc38.setText(modelEnum.getTargetValue() + LanguageUtil.getLanguageText(R.string.km_short));
                            }
                        }
                        break;
                }
            }
            holder.getMLayMedal().setOnClickListener(this.clickListener);
            LinearLayout mLayMedal2 = holder.getMLayMedal();
            Intrinsics.checkExpressionValueIsNotNull(mLayMedal2, "holder.mLayMedal");
            mLayMedal2.setTag(modelEnum != null ? Integer.valueOf(modelEnum.getMedalId()) : null);
            TextView mTvMedalTitle = holder.getMTvMedalTitle();
            Intrinsics.checkExpressionValueIsNotNull(mTvMedalTitle, "holder.mTvMedalTitle");
            Integer numValueOf = modelEnum != null ? Integer.valueOf(modelEnum.getTitleResId()) : null;
            if (numValueOf == null) {
                Intrinsics.throwNpe();
            }
            mTvMedalTitle.setText(LanguageUtil.getLanguageText(numValueOf.intValue()));
            if (medalInfo == null) {
                ImageView mImg = holder.getMImg();
                Integer numValueOf2 = modelEnum != null ? Integer.valueOf(modelEnum.getMedalNormalImageId_KM()) : null;
                if (numValueOf2 == null) {
                    Intrinsics.throwNpe();
                }
                mImg.setImageResource(numValueOf2.intValue());
                return;
            }
            ImageView mImg2 = holder.getMImg();
            Integer numValueOf3 = modelEnum != null ? Integer.valueOf(modelEnum.getMedalImageId_KM()) : null;
            if (numValueOf3 == null) {
                Intrinsics.throwNpe();
            }
            mImg2.setImageResource(numValueOf3.intValue());
            return;
        }
        if (itemViewType == this.HEADER_ONE) {
            RegularTextView mTvTitle2 = holder.getMTvTitle();
            Intrinsics.checkExpressionValueIsNotNull(mTvTitle2, "holder.mTvTitle");
            mTvTitle2.setVisibility(0);
            LinearLayout mLayMedal3 = holder.getMLayMedal();
            Intrinsics.checkExpressionValueIsNotNull(mLayMedal3, "holder.mLayMedal");
            mLayMedal3.setVisibility(8);
            RegularTextView mTvTitle3 = holder.getMTvTitle();
            Intrinsics.checkExpressionValueIsNotNull(mTvTitle3, "holder.mTvTitle");
            mTvTitle3.setText(LanguageUtil.getLanguageText(R.string.medal_target));
            return;
        }
        if (itemViewType == this.HEADER_TWO) {
            RegularTextView mTvTitle4 = holder.getMTvTitle();
            Intrinsics.checkExpressionValueIsNotNull(mTvTitle4, "holder.mTvTitle");
            mTvTitle4.setVisibility(0);
            LinearLayout mLayMedal4 = holder.getMLayMedal();
            Intrinsics.checkExpressionValueIsNotNull(mLayMedal4, "holder.mLayMedal");
            mLayMedal4.setVisibility(8);
            RegularTextView mTvTitle5 = holder.getMTvTitle();
            Intrinsics.checkExpressionValueIsNotNull(mTvTitle5, "holder.mTvTitle");
            mTvTitle5.setText(LanguageUtil.getLanguageText(R.string.medal_like_sport));
        }
    }
}