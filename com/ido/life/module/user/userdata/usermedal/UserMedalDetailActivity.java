package com.ido.life.module.user.userdata.usermedal;

import android.animation.ValueAnimator;
import android.view.View;
import com.boat.Xtend.two.R;
import com.ido.life.base.BaseActivity;
import com.ido.life.database.model.UserMedalInfo;
import com.ido.life.module.user.healthreport.UserMedalShareDialog;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserMedalDetailActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/ido/life/module/user/userdata/usermedal/UserMedalDetailActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/user/userdata/usermedal/UserMedalDetailPresenter;", "Landroid/view/View$OnClickListener;", "()V", "mModelInfo", "Lcom/ido/life/database/model/UserMedalInfo;", "mShareDialog", "Lcom/ido/life/module/user/healthreport/UserMedalShareDialog;", "mValueAnimator", "Landroid/animation/ValueAnimator;", "getLayoutResId", "", "initViews", "", "onClick", "v", "Landroid/view/View;", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class UserMedalDetailActivity extends BaseActivity<UserMedalDetailPresenter> implements View.OnClickListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String MEDAL_Id = "medal_id";
    private HashMap _$_findViewCache;
    private UserMedalInfo mModelInfo;
    private UserMedalShareDialog mShareDialog;
    private ValueAnimator mValueAnimator;

    public static final String getMEDAL_Id() {
        Companion companion = INSTANCE;
        return MEDAL_Id;
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
        return R.layout.activity_medal_detail_layout;
    }

    /* JADX INFO: compiled from: UserMedalDetailActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/ido/life/module/user/userdata/usermedal/UserMedalDetailActivity$Companion;", "", "()V", "MEDAL_Id", "", "MEDAL_Id$annotations", "getMEDAL_Id", "()Ljava/lang/String;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void MEDAL_Id$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getMEDAL_Id() {
            return UserMedalDetailActivity.MEDAL_Id;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x06c2  */
    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void initViews() {
        /*
            Method dump skipped, instruction units count: 2144
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.user.userdata.usermedal.UserMedalDetailActivity.initViews():void");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.layout_right) {
            if (this.mShareDialog == null) {
                UserMedalDetailActivity userMedalDetailActivity = this;
                UserMedalInfo userMedalInfo = this.mModelInfo;
                if (userMedalInfo == null) {
                    Intrinsics.throwNpe();
                }
                this.mShareDialog = new UserMedalShareDialog(userMedalDetailActivity, userMedalInfo);
            }
            UserMedalShareDialog userMedalShareDialog = this.mShareDialog;
            if (userMedalShareDialog != null) {
                userMedalShareDialog.show();
            }
        }
    }
}