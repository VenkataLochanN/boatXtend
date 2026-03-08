package com.ido.life.module.user.userdata.usermedal;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.google.android.gms.fitness.FitnessActivities;
import com.ido.life.base.BaseActivity;
import com.ido.life.database.model.UserMedalInfo;
import com.ido.life.enums.UserModelEnum;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserMedalActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u0012\u0010\u0010\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/ido/life/module/user/userdata/usermedal/UserMedalActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/user/userdata/usermedal/UserMedalPresenter;", "Lcom/ido/life/module/user/userdata/usermedal/IUserMedalView;", "Landroid/view/View$OnClickListener;", "()V", "mAdapter", "Lcom/ido/life/module/user/userdata/usermedal/UserMedalAdapter;", "mList", "", "Lcom/ido/life/module/user/userdata/usermedal/UserMedalActivity$UserModelEnumInfo;", "getLayoutResId", "", "initLabelLanguage", "", "initViews", "onClick", "v", "Landroid/view/View;", "onGetUserMedalFailed", "errMsg", "", "UserModelEnumInfo", "app_release"}, k = 1, mv = {1, 1, 16})
public final class UserMedalActivity extends BaseActivity<UserMedalPresenter> implements IUserMedalView, View.OnClickListener {
    private HashMap _$_findViewCache;
    private UserMedalAdapter mAdapter;
    private List<UserModelEnumInfo> mList = new ArrayList();

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
        return R.layout.activity_medal_list_activity;
    }

    @Override // com.ido.life.module.user.userdata.usermedal.IUserMedalView
    public void onGetUserMedalFailed(String errMsg) {
        Intrinsics.checkParameterIsNotNull(errMsg, "errMsg");
    }

    public UserMedalActivity() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        long userId = runTimeUtil.getUserId();
        for (int i = 0; i < 13; i++) {
            UserModelEnum userModelEnumById = UserModelEnum.INSTANCE.getUserModelEnumById(i);
            if (userModelEnumById != null) {
                this.mList.add(new UserModelEnumInfo(userModelEnumById, GreenDaoUtil.queryUserMedalInfo(userId, i)));
            }
        }
        if (!this.mList.isEmpty()) {
            CollectionsKt.sortWith(this.mList, new Comparator<UserModelEnumInfo>() { // from class: com.ido.life.module.user.userdata.usermedal.UserMedalActivity.1
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Removed duplicated region for block: B:27:0x0054  */
                /* JADX WARN: Removed duplicated region for block: B:50:0x00a5  */
                @Override // java.util.Comparator
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final int compare(com.ido.life.module.user.userdata.usermedal.UserMedalActivity.UserModelEnumInfo r8, com.ido.life.module.user.userdata.usermedal.UserMedalActivity.UserModelEnumInfo r9) {
                    /*
                        Method dump skipped, instruction units count: 313
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.user.userdata.usermedal.UserMedalActivity.AnonymousClass1.compare(com.ido.life.module.user.userdata.usermedal.UserMedalActivity$UserModelEnumInfo, com.ido.life.module.user.userdata.usermedal.UserMedalActivity$UserModelEnumInfo):int");
                }
            });
        }
        int i2 = 3;
        this.mList.add(0, new UserModelEnumInfo(null, 0 == true ? 1 : 0, i2, 0 == true ? 1 : 0));
        this.mList.add(7, new UserModelEnumInfo(0 == true ? 1 : 0, 0 == true ? 1 : 0, i2, 0 == true ? 1 : 0));
    }

    /* JADX INFO: compiled from: UserMedalActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/ido/life/module/user/userdata/usermedal/UserMedalActivity$UserModelEnumInfo;", "", "modelEnum", "Lcom/ido/life/enums/UserModelEnum;", "medalInfo", "Lcom/ido/life/database/model/UserMedalInfo;", "(Lcom/ido/life/enums/UserModelEnum;Lcom/ido/life/database/model/UserMedalInfo;)V", "getMedalInfo", "()Lcom/ido/life/database/model/UserMedalInfo;", "setMedalInfo", "(Lcom/ido/life/database/model/UserMedalInfo;)V", "getModelEnum", "()Lcom/ido/life/enums/UserModelEnum;", "setModelEnum", "(Lcom/ido/life/enums/UserModelEnum;)V", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class UserModelEnumInfo {
        private UserMedalInfo medalInfo;
        private UserModelEnum modelEnum;

        public UserModelEnumInfo() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ UserModelEnumInfo copy$default(UserModelEnumInfo userModelEnumInfo, UserModelEnum userModelEnum, UserMedalInfo userMedalInfo, int i, Object obj) {
            if ((i & 1) != 0) {
                userModelEnum = userModelEnumInfo.modelEnum;
            }
            if ((i & 2) != 0) {
                userMedalInfo = userModelEnumInfo.medalInfo;
            }
            return userModelEnumInfo.copy(userModelEnum, userMedalInfo);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final UserModelEnum getModelEnum() {
            return this.modelEnum;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final UserMedalInfo getMedalInfo() {
            return this.medalInfo;
        }

        public final UserModelEnumInfo copy(UserModelEnum modelEnum, UserMedalInfo medalInfo) {
            return new UserModelEnumInfo(modelEnum, medalInfo);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UserModelEnumInfo)) {
                return false;
            }
            UserModelEnumInfo userModelEnumInfo = (UserModelEnumInfo) other;
            return Intrinsics.areEqual(this.modelEnum, userModelEnumInfo.modelEnum) && Intrinsics.areEqual(this.medalInfo, userModelEnumInfo.medalInfo);
        }

        public int hashCode() {
            UserModelEnum userModelEnum = this.modelEnum;
            int iHashCode = (userModelEnum != null ? userModelEnum.hashCode() : 0) * 31;
            UserMedalInfo userMedalInfo = this.medalInfo;
            return iHashCode + (userMedalInfo != null ? userMedalInfo.hashCode() : 0);
        }

        public String toString() {
            return "UserModelEnumInfo(modelEnum=" + this.modelEnum + ", medalInfo=" + this.medalInfo + ")";
        }

        public UserModelEnumInfo(UserModelEnum userModelEnum, UserMedalInfo userMedalInfo) {
            this.modelEnum = userModelEnum;
            this.medalInfo = userMedalInfo;
        }

        public /* synthetic */ UserModelEnumInfo(UserModelEnum userModelEnum, UserMedalInfo userMedalInfo, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? (UserModelEnum) null : userModelEnum, (i & 2) != 0 ? (UserMedalInfo) null : userMedalInfo);
        }

        public final UserMedalInfo getMedalInfo() {
            return this.medalInfo;
        }

        public final UserModelEnum getModelEnum() {
            return this.modelEnum;
        }

        public final void setMedalInfo(UserMedalInfo userMedalInfo) {
            this.medalInfo = userMedalInfo;
        }

        public final void setModelEnum(UserModelEnum userModelEnum) {
            this.modelEnum = userModelEnum;
        }
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mTitleBar.setTitleLayoutAllBgcolor(this, getColor(R.color.color_1C1C1C));
        this.mTitleBar.setTitleColor(-1);
        setStatusBarColor(getColor(R.color.color_1C1C1C));
        UserMedalActivity userMedalActivity = this;
        this.mAdapter = new UserMedalAdapter(userMedalActivity, this.mList, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager((Context) userMedalActivity, 3, 1, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.ido.life.module.user.userdata.usermedal.UserMedalActivity.initViews.1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int position) {
                return (position == 0 || position == 7) ? 3 : 1;
            }
        });
        RecyclerView recycler_view = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(recycler_view, "recycler_view");
        recycler_view.setLayoutManager(gridLayoutManager);
        RecyclerView recycler_view2 = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(recycler_view2, "recycler_view");
        UserMedalAdapter userMedalAdapter = this.mAdapter;
        if (userMedalAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        }
        recycler_view2.setAdapter(userMedalAdapter);
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.title_medal));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_medal && (v.getTag() instanceof Integer)) {
            Intent intent = new Intent(this, (Class<?>) UserMedalDetailActivity.class);
            String mEDAL_Id = UserMedalDetailActivity.INSTANCE.getMEDAL_Id();
            Object tag = v.getTag();
            if (tag == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
            intent.putExtra(mEDAL_Id, ((Integer) tag).intValue());
            startActivity(intent);
        }
    }
}