package com.ido.life.module.user;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.google.android.gms.fitness.FitnessActivities;
import com.ido.alexa.log.AlexaLogPath;
import com.ido.alexa.log.AlexaLogPathImpl;
import com.ido.common.IdoApp;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.FileUtil;
import com.ido.common.utils.ImageLoaderUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.VeryFitApp;
import com.ido.life.autotest.AutoTestActivity;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseFragment;
import com.ido.life.base.BaseMessage;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.customview.viewgroup.OptionView;
import com.ido.life.data.api.entity.AppInfoEntity;
import com.ido.life.database.GreenDaoManager;
import com.ido.life.database.model.UserInfo;
import com.ido.life.enums.ServerEnum;
import com.ido.life.enums.UserModelEnum;
import com.ido.life.module.device.activity.CommonWebViewActivity;
import com.ido.life.module.device.activity.NativeWebViewActivity;
import com.ido.life.module.splash.SplashActivity;
import com.ido.life.module.user.about.AboutUsActivity;
import com.ido.life.module.user.feedback.FeedbackActivity;
import com.ido.life.module.user.healthreport.HealthReportActivity;
import com.ido.life.module.user.login.PreLoginAndRegisterActivity;
import com.ido.life.module.user.set.SettingNewActivity;
import com.ido.life.module.user.userdata.message.UserMessageActivity;
import com.ido.life.module.user.userdata.mydata.MyDataActivity;
import com.ido.life.module.user.userdata.usermedal.UserMedalActivity;
import com.ido.life.module.user.userdata.usermedal.UserMedalDetailActivity;
import com.ido.life.module.user.userinfo.UserInfoActivity;
import com.ido.life.syncdownload.DataDownLoadService;
import com.ido.life.util.AppMarketUtils;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.SPUtils;
import com.ido.life.util.eventbus.EventBusHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: compiled from: UserFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0010\u0018\u0000 I2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u0005:\u0002IJB\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0016J\b\u0010\u001a\u001a\u00020\u0018H\u0016J\b\u0010\u001b\u001a\u00020\u0016H\u0014J\u0006\u0010\u001c\u001a\u00020\u0018J\u0016\u0010\u001d\u001a\u00020\u00182\f\u0010\u001e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001fH\u0016J\u0006\u0010 \u001a\u00020\u0018J\b\u0010!\u001a\u00020\u0018H\u0014J\b\u0010\"\u001a\u00020\u0018H\u0002J\b\u0010#\u001a\u00020$H\u0014J\u0012\u0010%\u001a\u00020\u00182\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u0012\u0010(\u001a\u00020\u00182\b\u0010)\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010*\u001a\u00020\u00182\b\u0010+\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010,\u001a\u00020\u00182\b\u0010-\u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010.\u001a\u00020\u00182\b\u0010/\u001a\u0004\u0018\u00010\bH\u0016J\u0016\u00100\u001a\u00020\u00182\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u001102H\u0016J$\u00103\u001a\u00020\u00182\b\u00104\u001a\u0004\u0018\u00010'2\b\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u00020\u0016H\u0016J$\u00108\u001a\u00020$2\b\u00104\u001a\u0004\u0018\u00010'2\b\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u00020\u0016H\u0016J\u0010\u00109\u001a\u00020\u00182\u0006\u0010:\u001a\u00020;H\u0016J\b\u0010<\u001a\u00020\u0018H\u0014J\u0012\u0010=\u001a\u00020\u00182\b\u0010>\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010?\u001a\u00020\u00182\b\u0010@\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010A\u001a\u00020\u00182\b\u0010B\u001a\u0004\u0018\u00010\bH\u0016J\u0006\u0010C\u001a\u00020\u0018J\b\u0010D\u001a\u00020\u0018H\u0002J\b\u0010E\u001a\u00020\u0018H\u0016J\b\u0010F\u001a\u00020\u0018H\u0002J\b\u0010G\u001a\u00020\u0018H\u0016J\b\u0010H\u001a\u00020\u0018H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0013j\b\u0012\u0004\u0012\u00020\u0011`\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006K"}, d2 = {"Lcom/ido/life/module/user/UserFragment;", "Lcom/ido/life/base/BaseFragment;", "Lcom/ido/life/module/user/UserPresenter;", "Lcom/ido/life/module/user/IUserView;", "Landroid/view/View$OnClickListener;", "Lcom/ido/life/customview/recyclerview/MultiItemTypeAdapterForRV$OnItemClickListener;", "()V", "TAG", "", "ZIP_PATH", "mAppVersionInfo", "Lcom/ido/life/data/api/entity/AppInfoEntity$AppInfo;", "mInstructionUrl", "mLogDialog", "Landroidx/appcompat/app/AlertDialog;", "mModelAdapter", "Lcom/ido/life/customview/recyclerview/CommonRecyclerViewAdapter;", "Lcom/ido/life/module/user/UserFragment$UserModelnfo;", "mModelList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "mScreenWidth", "", "exitLogin", "", "flashLogCollectionFailed", "flashLogCollectionSuccess", "getLayoutResId", "goBack", "handleMessage", "message", "Lcom/ido/life/base/BaseMessage;", "hideLoading", "initView", "loginOutSuccess", "needEventBus", "", "onClick", "v", "Landroid/view/View;", "onGetDeviceInstruction", "url", "onGetNewVersionFailed", "errMsg", "onGetNewVersionSuccess", "appInfo", "onGetUserMedalFailed", "errNsg", "onGetUserMedalSuccess", "list", "", "onItemClick", "view", "holder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "position", "onItemLongClick", "onUnReadMessageCount", "count", "", "refreshLanguage", "setAvatarUrl", "avatarUrl", "setEmailAddress", "emailAddress", "setUserName", "userName", "showLoading", "showLogSelectDialog", "showLoginPage", "showServerSelectDialog", "showUnLoginPage", "toGuide", "Companion", "UserModelnfo", "app_release"}, k = 1, mv = {1, 1, 16})
public final class UserFragment extends BaseFragment<UserPresenter> implements IUserView, View.OnClickListener, MultiItemTypeAdapterForRV.OnItemClickListener {
    public static final String SHOW_SHARE = "show_share";
    private String ZIP_PATH;
    private HashMap _$_findViewCache;
    private AppInfoEntity.AppInfo mAppVersionInfo;
    private String mInstructionUrl;
    private AlertDialog mLogDialog;
    private CommonRecyclerViewAdapter<UserModelnfo> mModelAdapter;
    private int mScreenWidth;
    private final String TAG = "UserFragment";
    private ArrayList<UserModelnfo> mModelList = new ArrayList<>();

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

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_user;
    }

    @Override // com.ido.life.base.BaseFragment
    protected boolean needEventBus() {
        return true;
    }

    @Override // com.ido.life.base.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    @Override // com.ido.life.module.user.IUserView
    public void setEmailAddress(String emailAddress) {
    }

    public UserFragment() {
        StringBuilder sb = new StringBuilder();
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        sb.append(logPathImpl.getRootPath());
        sb.append("%s_log.zip");
        this.ZIP_PATH = sb.toString();
        this.mInstructionUrl = "";
    }

    public static final /* synthetic */ UserPresenter access$getMPresenter$p(UserFragment userFragment) {
        return (UserPresenter) userFragment.mPresenter;
    }

    /* JADX INFO: compiled from: UserFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/ido/life/module/user/UserFragment$UserModelnfo;", "", "userModelEnum", "Lcom/ido/life/enums/UserModelEnum;", "hasGet", "", "(Lcom/ido/life/enums/UserModelEnum;Z)V", "getHasGet", "()Z", "getUserModelEnum", "()Lcom/ido/life/enums/UserModelEnum;", "component1", "component2", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class UserModelnfo {
        private final boolean hasGet;
        private final UserModelEnum userModelEnum;

        public static /* synthetic */ UserModelnfo copy$default(UserModelnfo userModelnfo, UserModelEnum userModelEnum, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                userModelEnum = userModelnfo.userModelEnum;
            }
            if ((i & 2) != 0) {
                z = userModelnfo.hasGet;
            }
            return userModelnfo.copy(userModelEnum, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final UserModelEnum getUserModelEnum() {
            return this.userModelEnum;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getHasGet() {
            return this.hasGet;
        }

        public final UserModelnfo copy(UserModelEnum userModelEnum, boolean hasGet) {
            Intrinsics.checkParameterIsNotNull(userModelEnum, "userModelEnum");
            return new UserModelnfo(userModelEnum, hasGet);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UserModelnfo)) {
                return false;
            }
            UserModelnfo userModelnfo = (UserModelnfo) other;
            return Intrinsics.areEqual(this.userModelEnum, userModelnfo.userModelEnum) && this.hasGet == userModelnfo.hasGet;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [int] */
        /* JADX WARN: Type inference failed for: r1v2 */
        /* JADX WARN: Type inference failed for: r1v3 */
        public int hashCode() {
            UserModelEnum userModelEnum = this.userModelEnum;
            int iHashCode = (userModelEnum != null ? userModelEnum.hashCode() : 0) * 31;
            boolean z = this.hasGet;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            return iHashCode + r1;
        }

        public String toString() {
            return "UserModelnfo(userModelEnum=" + this.userModelEnum + ", hasGet=" + this.hasGet + ")";
        }

        public UserModelnfo(UserModelEnum userModelEnum, boolean z) {
            Intrinsics.checkParameterIsNotNull(userModelEnum, "userModelEnum");
            this.userModelEnum = userModelEnum;
            this.hasGet = z;
        }

        public final boolean getHasGet() {
            return this.hasGet;
        }

        public final UserModelEnum getUserModelEnum() {
            return this.userModelEnum;
        }
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void initView() {
        super.initView();
        UserFragment userFragment = this;
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_my_reward)).setOnClickListener(userFragment);
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_my_data)).setOnClickListener(userFragment);
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_my_report)).setOnClickListener(userFragment);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_message)).setOnClickListener(userFragment);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_help)).setOnClickListener(userFragment);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.user_manual)).setOnClickListener(userFragment);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_background_help)).setOnClickListener(userFragment);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_setting)).setOnClickListener(userFragment);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_feedback)).setOnClickListener(userFragment);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_check_version)).setOnClickListener(userFragment);
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_abort)).setOnClickListener(userFragment);
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_login)).setOnClickListener(userFragment);
        ((CardView) _$_findCachedViewById(com.ido.life.R.id.lay_profile)).setOnClickListener(userFragment);
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        this.mScreenWidth = resources.getDisplayMetrics().widthPixels - (getResources().getDimensionPixelSize(R.dimen.sw_dp_20) * 2);
        TextView tv_reward_count = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_reward_count);
        Intrinsics.checkExpressionValueIsNotNull(tv_reward_count, "tv_reward_count");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(R.string.user_reward_count_format);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.user_reward_count_format)");
        Object[] objArr = {0};
        String str = String.format(string, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        tv_reward_count.setText(str);
    }

    private final void showLogSelectDialog() {
        if (this.mLogDialog == null) {
            this.mLogDialog = new AlertDialog.Builder(this.mActivity).setTitle("请选择需要导出的日志").setAdapter(new ArrayAdapter(this.mActivity, android.R.layout.simple_list_item_1, CollectionsKt.mutableListOf("普通日志", "APP崩溃日志", "缓存文件", "NormalFile", "定位信息日志", "bug日志", "蓝牙状态日志", "服务器日志", "蓝牙SDK日志", "表盘文件", "壁纸表盘", "天气日志", "提示日志", "Strava日志", "GoogleFit日志", "GoogleMap日志", "语言文件", "运动日志", "jni日志", "登录注册日志", "数据库", "绑定日志", "alexa日志", "数据库升级日志", "Flash日志", "gps日志")), new DialogInterfaceOnClickListenerC04281()).create();
        }
        AlertDialog alertDialog = this.mLogDialog;
        if (alertDialog != null) {
            alertDialog.show();
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.user.UserFragment$showLogSelectDialog$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: UserFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "position", "", "onClick"}, k = 3, mv = {1, 1, 16})
    static final class DialogInterfaceOnClickListenerC04281 implements DialogInterface.OnClickListener {
        DialogInterfaceOnClickListenerC04281() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v10, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v11, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v12, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v13, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v14, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v15, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v16, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v17, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v18, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v19, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v20, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v21, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v22, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v23, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v24, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v25, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v33, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v36, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v37, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v41, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v6, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v7, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v8, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v9, types: [T, java.lang.Object, java.lang.String] */
        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            File databasePath;
            UserFragment userFragment = UserFragment.this;
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            String logZipNamePath = logPathImpl.getLogZipNamePath();
            Intrinsics.checkExpressionValueIsNotNull(logZipNamePath, "LogPathImpl.getInstance().logZipNamePath");
            userFragment.ZIP_PATH = logZipNamePath;
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = "";
            switch (i) {
                case 0:
                    UserFragment userFragment2 = UserFragment.this;
                    Object[] objArr = {"普通日志"};
                    String str = String.format(userFragment2.ZIP_PATH, Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                    userFragment2.ZIP_PATH = str;
                    LogPath logPathImpl2 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
                    ?? logPath = logPathImpl2.getLogPath();
                    Intrinsics.checkExpressionValueIsNotNull(logPath, "LogPathImpl.getInstance().logPath");
                    objectRef.element = logPath;
                    break;
                case 1:
                    UserFragment userFragment3 = UserFragment.this;
                    Object[] objArr2 = {"APP崩溃日志"};
                    String str2 = String.format(userFragment3.ZIP_PATH, Arrays.copyOf(objArr2, objArr2.length));
                    Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(this, *args)");
                    userFragment3.ZIP_PATH = str2;
                    LogPath logPathImpl3 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl3, "LogPathImpl.getInstance()");
                    ?? crashLogPath = logPathImpl3.getCrashLogPath();
                    Intrinsics.checkExpressionValueIsNotNull(crashLogPath, "LogPathImpl.getInstance().crashLogPath");
                    objectRef.element = crashLogPath;
                    break;
                case 2:
                    UserFragment userFragment4 = UserFragment.this;
                    Object[] objArr3 = {"缓存文件"};
                    String str3 = String.format(userFragment4.ZIP_PATH, Arrays.copyOf(objArr3, objArr3.length));
                    Intrinsics.checkNotNullExpressionValue(str3, "java.lang.String.format(this, *args)");
                    userFragment4.ZIP_PATH = str3;
                    LogPath logPathImpl4 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl4, "LogPathImpl.getInstance()");
                    ?? cachePath = logPathImpl4.getCachePath();
                    Intrinsics.checkExpressionValueIsNotNull(cachePath, "LogPathImpl.getInstance().cachePath");
                    objectRef.element = cachePath;
                    break;
                case 3:
                    UserFragment userFragment5 = UserFragment.this;
                    Object[] objArr4 = {"NormalFile"};
                    String str4 = String.format(userFragment5.ZIP_PATH, Arrays.copyOf(objArr4, objArr4.length));
                    Intrinsics.checkNotNullExpressionValue(str4, "java.lang.String.format(this, *args)");
                    userFragment5.ZIP_PATH = str4;
                    LogPath logPathImpl5 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl5, "LogPathImpl.getInstance()");
                    ?? normalFilePath = logPathImpl5.getNormalFilePath();
                    Intrinsics.checkExpressionValueIsNotNull(normalFilePath, "LogPathImpl.getInstance().normalFilePath");
                    objectRef.element = normalFilePath;
                    break;
                case 4:
                    UserFragment userFragment6 = UserFragment.this;
                    Object[] objArr5 = {"定位信息日志"};
                    String str5 = String.format(userFragment6.ZIP_PATH, Arrays.copyOf(objArr5, objArr5.length));
                    Intrinsics.checkNotNullExpressionValue(str5, "java.lang.String.format(this, *args)");
                    userFragment6.ZIP_PATH = str5;
                    LogPath logPathImpl6 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl6, "LogPathImpl.getInstance()");
                    ?? locationInfoPath = logPathImpl6.getLocationInfoPath();
                    Intrinsics.checkExpressionValueIsNotNull(locationInfoPath, "LogPathImpl.getInstance().locationInfoPath");
                    objectRef.element = locationInfoPath;
                    break;
                case 5:
                    UserFragment userFragment7 = UserFragment.this;
                    Object[] objArr6 = {"bug日志"};
                    String str6 = String.format(userFragment7.ZIP_PATH, Arrays.copyOf(objArr6, objArr6.length));
                    Intrinsics.checkNotNullExpressionValue(str6, "java.lang.String.format(this, *args)");
                    userFragment7.ZIP_PATH = str6;
                    LogPath logPathImpl7 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl7, "LogPathImpl.getInstance()");
                    ?? bugLogPath = logPathImpl7.getBugLogPath();
                    Intrinsics.checkExpressionValueIsNotNull(bugLogPath, "LogPathImpl.getInstance().bugLogPath");
                    objectRef.element = bugLogPath;
                    break;
                case 6:
                    UserFragment userFragment8 = UserFragment.this;
                    Object[] objArr7 = {"蓝牙连接状态日志"};
                    String str7 = String.format(userFragment8.ZIP_PATH, Arrays.copyOf(objArr7, objArr7.length));
                    Intrinsics.checkNotNullExpressionValue(str7, "java.lang.String.format(this, *args)");
                    userFragment8.ZIP_PATH = str7;
                    LogPath logPathImpl8 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl8, "LogPathImpl.getInstance()");
                    ?? connectLogPath = logPathImpl8.getConnectLogPath();
                    Intrinsics.checkExpressionValueIsNotNull(connectLogPath, "LogPathImpl.getInstance().connectLogPath");
                    objectRef.element = connectLogPath;
                    break;
                case 7:
                    UserFragment userFragment9 = UserFragment.this;
                    Object[] objArr8 = {"服务器日志"};
                    String str8 = String.format(userFragment9.ZIP_PATH, Arrays.copyOf(objArr8, objArr8.length));
                    Intrinsics.checkNotNullExpressionValue(str8, "java.lang.String.format(this, *args)");
                    userFragment9.ZIP_PATH = str8;
                    LogPath logPathImpl9 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl9, "LogPathImpl.getInstance()");
                    ?? serverLogPath = logPathImpl9.getServerLogPath();
                    Intrinsics.checkExpressionValueIsNotNull(serverLogPath, "LogPathImpl.getInstance().serverLogPath");
                    objectRef.element = serverLogPath;
                    break;
                case 8:
                    UserFragment userFragment10 = UserFragment.this;
                    Object[] objArr9 = {"蓝牙SDK日志"};
                    String str9 = String.format(userFragment10.ZIP_PATH, Arrays.copyOf(objArr9, objArr9.length));
                    Intrinsics.checkNotNullExpressionValue(str9, "java.lang.String.format(this, *args)");
                    userFragment10.ZIP_PATH = str9;
                    LogPath logPathImpl10 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl10, "LogPathImpl.getInstance()");
                    ?? bleSdkLogPath = logPathImpl10.getBleSdkLogPath();
                    Intrinsics.checkExpressionValueIsNotNull(bleSdkLogPath, "LogPathImpl.getInstance().bleSdkLogPath");
                    objectRef.element = bleSdkLogPath;
                    break;
                case 9:
                    UserFragment userFragment11 = UserFragment.this;
                    Object[] objArr10 = {"表盘文件"};
                    String str10 = String.format(userFragment11.ZIP_PATH, Arrays.copyOf(objArr10, objArr10.length));
                    Intrinsics.checkNotNullExpressionValue(str10, "java.lang.String.format(this, *args)");
                    userFragment11.ZIP_PATH = str10;
                    LogPath logPathImpl11 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl11, "LogPathImpl.getInstance()");
                    ?? dialFilePath = logPathImpl11.getDialFilePath();
                    Intrinsics.checkExpressionValueIsNotNull(dialFilePath, "LogPathImpl.getInstance().dialFilePath");
                    objectRef.element = dialFilePath;
                    break;
                case 10:
                    UserFragment userFragment12 = UserFragment.this;
                    Object[] objArr11 = {"壁纸表盘"};
                    String str11 = String.format(userFragment12.ZIP_PATH, Arrays.copyOf(objArr11, objArr11.length));
                    Intrinsics.checkNotNullExpressionValue(str11, "java.lang.String.format(this, *args)");
                    userFragment12.ZIP_PATH = str11;
                    LogPath logPathImpl12 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl12, "LogPathImpl.getInstance()");
                    ?? wallpaperDialFilePath = logPathImpl12.getWallpaperDialFilePath();
                    Intrinsics.checkExpressionValueIsNotNull(wallpaperDialFilePath, "LogPathImpl.getInstance().wallpaperDialFilePath");
                    objectRef.element = wallpaperDialFilePath;
                    break;
                case 11:
                    UserFragment userFragment13 = UserFragment.this;
                    Object[] objArr12 = {"天气日志"};
                    String str12 = String.format(userFragment13.ZIP_PATH, Arrays.copyOf(objArr12, objArr12.length));
                    Intrinsics.checkNotNullExpressionValue(str12, "java.lang.String.format(this, *args)");
                    userFragment13.ZIP_PATH = str12;
                    LogPath logPathImpl13 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl13, "LogPathImpl.getInstance()");
                    ?? weatherLogPath = logPathImpl13.getWeatherLogPath();
                    Intrinsics.checkExpressionValueIsNotNull(weatherLogPath, "LogPathImpl.getInstance().weatherLogPath");
                    objectRef.element = weatherLogPath;
                    break;
                case 12:
                    UserFragment userFragment14 = UserFragment.this;
                    Object[] objArr13 = {"提示日志"};
                    String str13 = String.format(userFragment14.ZIP_PATH, Arrays.copyOf(objArr13, objArr13.length));
                    Intrinsics.checkNotNullExpressionValue(str13, "java.lang.String.format(this, *args)");
                    userFragment14.ZIP_PATH = str13;
                    LogPath logPathImpl14 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl14, "LogPathImpl.getInstance()");
                    ?? notificationLogPath = logPathImpl14.getNotificationLogPath();
                    Intrinsics.checkExpressionValueIsNotNull(notificationLogPath, "LogPathImpl.getInstance().notificationLogPath");
                    objectRef.element = notificationLogPath;
                    break;
                case 13:
                    UserFragment userFragment15 = UserFragment.this;
                    Object[] objArr14 = {"Strava日志"};
                    String str14 = String.format(userFragment15.ZIP_PATH, Arrays.copyOf(objArr14, objArr14.length));
                    Intrinsics.checkNotNullExpressionValue(str14, "java.lang.String.format(this, *args)");
                    userFragment15.ZIP_PATH = str14;
                    LogPath logPathImpl15 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl15, "LogPathImpl.getInstance()");
                    ?? stravaLogPath = logPathImpl15.getStravaLogPath();
                    Intrinsics.checkExpressionValueIsNotNull(stravaLogPath, "LogPathImpl.getInstance().stravaLogPath");
                    objectRef.element = stravaLogPath;
                    break;
                case 14:
                    UserFragment userFragment16 = UserFragment.this;
                    Object[] objArr15 = {"GoogleFit日志"};
                    String str15 = String.format(userFragment16.ZIP_PATH, Arrays.copyOf(objArr15, objArr15.length));
                    Intrinsics.checkNotNullExpressionValue(str15, "java.lang.String.format(this, *args)");
                    userFragment16.ZIP_PATH = str15;
                    LogPath logPathImpl16 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl16, "LogPathImpl.getInstance()");
                    ?? googleFitLogPath = logPathImpl16.getGoogleFitLogPath();
                    Intrinsics.checkExpressionValueIsNotNull(googleFitLogPath, "LogPathImpl.getInstance().googleFitLogPath");
                    objectRef.element = googleFitLogPath;
                    break;
                case 15:
                    UserFragment userFragment17 = UserFragment.this;
                    Object[] objArr16 = {"GoogleMap日志"};
                    String str16 = String.format(userFragment17.ZIP_PATH, Arrays.copyOf(objArr16, objArr16.length));
                    Intrinsics.checkNotNullExpressionValue(str16, "java.lang.String.format(this, *args)");
                    userFragment17.ZIP_PATH = str16;
                    LogPath logPathImpl17 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl17, "LogPathImpl.getInstance()");
                    ?? googleMapLogPath = logPathImpl17.getGoogleMapLogPath();
                    Intrinsics.checkExpressionValueIsNotNull(googleMapLogPath, "LogPathImpl.getInstance().googleMapLogPath");
                    objectRef.element = googleMapLogPath;
                    break;
                case 16:
                    UserFragment userFragment18 = UserFragment.this;
                    Object[] objArr17 = {"语言文件"};
                    String str17 = String.format(userFragment18.ZIP_PATH, Arrays.copyOf(objArr17, objArr17.length));
                    Intrinsics.checkNotNullExpressionValue(str17, "java.lang.String.format(this, *args)");
                    userFragment18.ZIP_PATH = str17;
                    LogPath logPathImpl18 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl18, "LogPathImpl.getInstance()");
                    ?? languagePath = logPathImpl18.getLanguagePath();
                    Intrinsics.checkExpressionValueIsNotNull(languagePath, "LogPathImpl.getInstance().languagePath");
                    objectRef.element = languagePath;
                    break;
                case 17:
                    UserFragment userFragment19 = UserFragment.this;
                    Object[] objArr18 = {"运动日志"};
                    String str18 = String.format(userFragment19.ZIP_PATH, Arrays.copyOf(objArr18, objArr18.length));
                    Intrinsics.checkNotNullExpressionValue(str18, "java.lang.String.format(this, *args)");
                    userFragment19.ZIP_PATH = str18;
                    LogPath logPathImpl19 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl19, "LogPathImpl.getInstance()");
                    ?? sportLogPath = logPathImpl19.getSportLogPath();
                    Intrinsics.checkExpressionValueIsNotNull(sportLogPath, "LogPathImpl.getInstance().sportLogPath");
                    objectRef.element = sportLogPath;
                    break;
                case 18:
                    UserFragment userFragment20 = UserFragment.this;
                    Object[] objArr19 = {"SO日志"};
                    String str19 = String.format(userFragment20.ZIP_PATH, Arrays.copyOf(objArr19, objArr19.length));
                    Intrinsics.checkNotNullExpressionValue(str19, "java.lang.String.format(this, *args)");
                    userFragment20.ZIP_PATH = str19;
                    LogPath logPathImpl20 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl20, "LogPathImpl.getInstance()");
                    ?? soPath = logPathImpl20.getSoPath();
                    Intrinsics.checkExpressionValueIsNotNull(soPath, "LogPathImpl.getInstance().soPath");
                    objectRef.element = soPath;
                    break;
                case 19:
                    UserFragment userFragment21 = UserFragment.this;
                    Object[] objArr20 = {"登录注册日志"};
                    String str20 = String.format(userFragment21.ZIP_PATH, Arrays.copyOf(objArr20, objArr20.length));
                    Intrinsics.checkNotNullExpressionValue(str20, "java.lang.String.format(this, *args)");
                    userFragment21.ZIP_PATH = str20;
                    LogPath logPathImpl21 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl21, "LogPathImpl.getInstance()");
                    ?? loginRegisterLogPath = logPathImpl21.getLoginRegisterLogPath();
                    Intrinsics.checkExpressionValueIsNotNull(loginRegisterLogPath, "LogPathImpl.getInstance().loginRegisterLogPath");
                    objectRef.element = loginRegisterLogPath;
                    break;
                case 20:
                    UserFragment userFragment22 = UserFragment.this;
                    Object[] objArr21 = {"数据库"};
                    String str21 = String.format(userFragment22.ZIP_PATH, Arrays.copyOf(objArr21, objArr21.length));
                    Intrinsics.checkNotNullExpressionValue(str21, "java.lang.String.format(this, *args)");
                    userFragment22.ZIP_PATH = str21;
                    Context context = UserFragment.this.getContext();
                    T absolutePath = (context == null || (databasePath = context.getDatabasePath(GreenDaoManager.DB_NAME)) == null) ? 0 : databasePath.getAbsolutePath();
                    if (absolutePath == 0) {
                        Intrinsics.throwNpe();
                    }
                    objectRef.element = absolutePath;
                    break;
                case 21:
                    UserFragment userFragment23 = UserFragment.this;
                    Object[] objArr22 = {"绑定日志"};
                    String str22 = String.format(userFragment23.ZIP_PATH, Arrays.copyOf(objArr22, objArr22.length));
                    Intrinsics.checkNotNullExpressionValue(str22, "java.lang.String.format(this, *args)");
                    userFragment23.ZIP_PATH = str22;
                    LogPath logPathImpl22 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl22, "LogPathImpl.getInstance()");
                    ?? bindLogPath = logPathImpl22.getBindLogPath();
                    Intrinsics.checkExpressionValueIsNotNull(bindLogPath, "LogPathImpl.getInstance().bindLogPath");
                    objectRef.element = bindLogPath;
                    break;
                case 22:
                    UserFragment userFragment24 = UserFragment.this;
                    Object[] objArr23 = {"Alexa日志"};
                    String str23 = String.format(userFragment24.ZIP_PATH, Arrays.copyOf(objArr23, objArr23.length));
                    Intrinsics.checkNotNullExpressionValue(str23, "java.lang.String.format(this, *args)");
                    userFragment24.ZIP_PATH = str23;
                    AlexaLogPath alexaLogPathImpl = AlexaLogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(alexaLogPathImpl, "AlexaLogPathImpl.getInstance()");
                    ?? alexaPath = alexaLogPathImpl.getAlexaPath();
                    Intrinsics.checkExpressionValueIsNotNull(alexaPath, "AlexaLogPathImpl.getInstance().alexaPath");
                    objectRef.element = alexaPath;
                    break;
                case 23:
                    UserFragment userFragment25 = UserFragment.this;
                    Object[] objArr24 = {"数据库升级日志"};
                    String str24 = String.format(userFragment25.ZIP_PATH, Arrays.copyOf(objArr24, objArr24.length));
                    Intrinsics.checkNotNullExpressionValue(str24, "java.lang.String.format(this, *args)");
                    userFragment25.ZIP_PATH = str24;
                    LogPath logPathImpl23 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl23, "LogPathImpl.getInstance()");
                    ?? dataBaseUpgradePath = logPathImpl23.getDataBaseUpgradePath();
                    Intrinsics.checkExpressionValueIsNotNull(dataBaseUpgradePath, "LogPathImpl.getInstance().dataBaseUpgradePath");
                    objectRef.element = dataBaseUpgradePath;
                    break;
                case 24:
                    UserFragment.this.showLoadingDialog();
                    UserFragment.access$getMPresenter$p(UserFragment.this).collectionFlashLog();
                    break;
                case 25:
                    UserFragment userFragment26 = UserFragment.this;
                    Object[] objArr25 = {"gps日志"};
                    String str25 = String.format(userFragment26.ZIP_PATH, Arrays.copyOf(objArr25, objArr25.length));
                    Intrinsics.checkNotNullExpressionValue(str25, "java.lang.String.format(this, *args)");
                    userFragment26.ZIP_PATH = str25;
                    LogPath logPathImpl24 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl24, "LogPathImpl.getInstance()");
                    ?? gpsLogPath = logPathImpl24.getGpsLogPath();
                    Intrinsics.checkExpressionValueIsNotNull(gpsLogPath, "LogPathImpl.getInstance().gpsLogPath");
                    objectRef.element = gpsLogPath;
                    break;
            }
            AlertDialog alertDialog = UserFragment.this.mLogDialog;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            if (i != 24) {
                String str26 = UserFragment.this.ZIP_PATH;
                if (str26 == null || str26.length() == 0) {
                    return;
                }
                String str27 = (String) objectRef.element;
                if (str27 == null || str27.length() == 0) {
                    return;
                }
                UserFragment.this.showLoadingDialog();
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C01201(objectRef, null), 2, null);
            }
        }

        /* JADX INFO: renamed from: com.ido.life.module.user.UserFragment$showLogSelectDialog$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: UserFragment.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.module.user.UserFragment$showLogSelectDialog$1$1", f = "UserFragment.kt", i = {0, 0}, l = {289}, m = "invokeSuspend", n = {"$this$launch", "pFile"}, s = {"L$0", "L$1"})
        static final class C01201 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Ref.ObjectRef $srcPath;
            Object L$0;
            Object L$1;
            int label;
            private CoroutineScope p$;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01201(Ref.ObjectRef objectRef, Continuation continuation) {
                super(2, continuation);
                this.$srcPath = objectRef;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C01201 c01201 = DialogInterfaceOnClickListenerC04281.this.new C01201(this.$srcPath, completion);
                c01201.p$ = (CoroutineScope) obj;
                return c01201;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01201) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.p$;
                    File file = new File(UserFragment.this.ZIP_PATH);
                    if (file.exists() && file.isFile()) {
                        FileUtil.deleteFile(UserFragment.this.ZIP_PATH);
                    }
                    FileUtil.zipFolder((String) this.$srcPath.element, UserFragment.this.ZIP_PATH);
                    MainCoroutineDispatcher main = Dispatchers.getMain();
                    C01211 c01211 = new C01211(null);
                    this.L$0 = coroutineScope;
                    this.L$1 = file;
                    this.label = 1;
                    if (BuildersKt.withContext(main, c01211, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: com.ido.life.module.user.UserFragment$showLogSelectDialog$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: UserFragment.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
            @DebugMetadata(c = "com.ido.life.module.user.UserFragment$showLogSelectDialog$1$1$1", f = "UserFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            static final class C01211 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;
                private CoroutineScope p$;

                C01211(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C01211 c01211 = C01201.this.new C01211(completion);
                    c01211.p$ = (CoroutineScope) obj;
                    return c01211;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C01211) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) throws Throwable {
                    Uri uriFromFile;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.p$;
                    UserFragment.this.dismissLoadingDialog();
                    File file = new File(UserFragment.this.ZIP_PATH);
                    if (file.exists()) {
                        Intent intent = new Intent("android.intent.action.SEND");
                        intent.setType("text/plain");
                        if (Build.VERSION.SDK_INT >= 24) {
                            Context context = UserFragment.this.getContext();
                            if (context == null) {
                                Intrinsics.throwNpe();
                            }
                            uriFromFile = FileProvider.getUriForFile(context, "com.boat.Xtend.two.provider", file);
                        } else {
                            uriFromFile = Uri.fromFile(new File(UserFragment.this.ZIP_PATH));
                        }
                        intent.putExtra("android.intent.extra.STREAM", uriFromFile);
                        BaseActivity mActivity = UserFragment.this.mActivity;
                        Intrinsics.checkExpressionValueIsNotNull(mActivity, "mActivity");
                        List<ResolveInfo> listQueryIntentActivities = mActivity.getPackageManager().queryIntentActivities(intent, 65536);
                        Intrinsics.checkExpressionValueIsNotNull(listQueryIntentActivities, "mActivity.packageManager…nager.MATCH_DEFAULT_ONLY)");
                        List<ResolveInfo> list = listQueryIntentActivities;
                        if (!(list == null || list.isEmpty())) {
                            UserFragment.this.startActivity(intent);
                        }
                    }
                    return Unit.INSTANCE;
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.util.List] */
    private final void showServerSelectDialog() {
        ServerEnum[] serverEnumArrValues = ServerEnum.values();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        for (ServerEnum serverEnum : serverEnumArrValues) {
            List list = (List) objectRef.element;
            String str = serverEnum.Desc;
            Intrinsics.checkExpressionValueIsNotNull(str, "item.Desc");
            list.add(str);
        }
        new AlertDialog.Builder(this.mActivity).setTitle("请选择需要的服务器").setAdapter(new ArrayAdapter(this.mActivity, android.R.layout.simple_list_item_1, (List) objectRef.element), new DialogInterface.OnClickListener() { // from class: com.ido.life.module.user.UserFragment$showServerSelectDialog$logDialog$1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r4v4, types: [T, com.ido.life.enums.ServerEnum] */
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Object obj = SPUtils.get(Constants.SERVER_CODE, Integer.valueOf(ServerEnum.Europe.Code));
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                }
                int iIntValue = ((Integer) obj).intValue();
                final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                objectRef2.element = ServerEnum.getServerEnumByDesc((String) ((List) objectRef.element).get(i));
                if (((ServerEnum) objectRef2.element) == null || iIntValue == ((ServerEnum) objectRef2.element).Code) {
                    return;
                }
                DataDownLoadService.INSTANCE.stop();
                UserInfo userInfoQueryLatestUserInfo = GreenDaoUtil.queryLatestUserInfo();
                Intrinsics.checkExpressionValueIsNotNull(userInfoQueryLatestUserInfo, "GreenDaoUtil.queryLatestUserInfo()");
                SPUtils.put(Constants.LAST_ACCOUNT_KEY, userInfoQueryLatestUserInfo.getEmail());
                VeryFitApp.getApp().quitClearCacheThread(new VeryFitApp.ClearCacheCallBack() { // from class: com.ido.life.module.user.UserFragment$showServerSelectDialog$logDialog$1.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // com.ido.life.VeryFitApp.ClearCacheCallBack
                    public final void clearSuccess() {
                        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
                        runTimeUtil.setUserId(-1);
                        SPUtils.put(Constants.SERVER_CODE, Integer.valueOf(((ServerEnum) objectRef2.element).Code));
                        SPUtils.put(Constants.REQUEST_COUNTRY_CODE, "");
                        EventBusHelper.post(305);
                        Intent intent = new Intent(UserFragment$showServerSelectDialog$logDialog$1.this.this$0.mActivity, (Class<?>) SplashActivity.class);
                        intent.addFlags(268435456);
                        intent.addFlags(32768);
                        UserFragment$showServerSelectDialog$logDialog$1.this.this$0.startActivity(intent);
                    }
                });
            }
        }).show();
    }

    private final void exitLogin() {
        showLoading();
        DataDownLoadService.INSTANCE.stop();
        loginOutSuccess();
    }

    private final void loginOutSuccess() {
        hideLoading();
        UserInfo userInfoQueryLatestUserInfo = GreenDaoUtil.queryLatestUserInfo();
        Intrinsics.checkExpressionValueIsNotNull(userInfoQueryLatestUserInfo, "GreenDaoUtil.queryLatestUserInfo()");
        SPUtils.put(Constants.LAST_ACCOUNT_KEY, userInfoQueryLatestUserInfo.getEmail());
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "切换服务器退出登录，上一次登录的账号邮箱：" + SPUtils.get(Constants.LAST_ACCOUNT_KEY, ""));
        VeryFitApp.getApp().quitClearCacheThread(new VeryFitApp.ClearCacheCallBack() { // from class: com.ido.life.module.user.UserFragment.loginOutSuccess.1
            @Override // com.ido.life.VeryFitApp.ClearCacheCallBack
            public final void clearSuccess() {
                RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
                runTimeUtil.setUserId(-1);
                LogPath logPathImpl2 = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), UserFragment.this.TAG, "切换服务器退出登录，直接删除缓存信息，跳转到登录注册页。");
                UserFragment.this.toGuide();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toGuide() {
        goBack();
        startActivity(new Intent(IdoApp.getAppContext(), (Class<?>) PreLoginAndRegisterActivity.class));
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "退出登录，跳转到PreLoginAndRegisterActivity");
        EventBusHelper.post(Constants.EventConstants.EVENT_QUIT_LOGIN_SUCCESS_FINISH);
        LogPath logPathImpl2 = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), this.TAG, "退出登录成功---EVENT_QUIT_LOGIN_SUCCESS_FINISH");
    }

    public final void goBack() {
        ActivityCompat.finishAfterTransition(this.mActivity);
    }

    public final void showLoading() {
        WaitingDialog.showDialog(this.mActivity);
    }

    public final void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.IUserView
    public void setAvatarUrl(String avatarUrl) {
        ImageLoaderUtil.loadCircleImage((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_avatar), avatarUrl, R.mipmap.ic_avatar_default);
    }

    @Override // com.ido.life.module.user.IUserView
    public void onGetUserMedalSuccess(List<UserModelnfo> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        this.mModelList.clear();
        List<UserModelnfo> list2 = list;
        if (!list2.isEmpty()) {
            this.mModelList.addAll(list2);
        }
        ArrayList<UserModelnfo> arrayList = this.mModelList;
        if (arrayList == null || arrayList.isEmpty()) {
            TextView tv_reward_count = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_reward_count);
            Intrinsics.checkExpressionValueIsNotNull(tv_reward_count, "tv_reward_count");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(R.string.user_reward_count_format);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.user_reward_count_format)");
            Object[] objArr = {0};
            String str = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
            tv_reward_count.setText(str);
            return;
        }
        CommonRecyclerViewAdapter<UserModelnfo> commonRecyclerViewAdapter = this.mModelAdapter;
        if (commonRecyclerViewAdapter == null) {
            this.mModelAdapter = new CommonRecyclerViewAdapter<UserModelnfo>(this.mActivity, R.layout.item_user_medal_layout, this.mModelList) { // from class: com.ido.life.module.user.UserFragment.onGetUserMedalSuccess.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
                public void convert(CommonRecyclerViewHolder holder, UserModelnfo modelEnum, int position) {
                    View view;
                    Intrinsics.checkParameterIsNotNull(modelEnum, "modelEnum");
                    if (holder != null && (view = holder.itemView) != null) {
                        view.setMinimumWidth(UserFragment.this.mScreenWidth / 5);
                    }
                    ImageView imageView = holder != null ? (ImageView) holder.getView(R.id.img) : null;
                    if (imageView == null) {
                        Intrinsics.throwNpe();
                    }
                    if (modelEnum.getHasGet()) {
                        imageView.setImageResource(modelEnum.getUserModelEnum().getMedalImageId_KM());
                    } else {
                        imageView.setImageResource(modelEnum.getUserModelEnum().getMedalNormalImageId_KM());
                    }
                }
            };
            RecyclerView recycler_reward = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_reward);
            Intrinsics.checkExpressionValueIsNotNull(recycler_reward, "recycler_reward");
            recycler_reward.setAdapter(this.mModelAdapter);
            CommonRecyclerViewAdapter<UserModelnfo> commonRecyclerViewAdapter2 = this.mModelAdapter;
            if (commonRecyclerViewAdapter2 != null) {
                commonRecyclerViewAdapter2.setOnItemClickListener(this);
            }
        } else if (commonRecyclerViewAdapter != null) {
            commonRecyclerViewAdapter.notifyDataSetChanged();
        }
        Iterator<T> it = this.mModelList.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((UserModelnfo) it.next()).getHasGet()) {
                i++;
            }
        }
        TextView tv_reward_count2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_reward_count);
        Intrinsics.checkExpressionValueIsNotNull(tv_reward_count2, "tv_reward_count");
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String string2 = getString(R.string.user_reward_count_format);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.user_reward_count_format)");
        Object[] objArr2 = {Integer.valueOf(i)};
        String str2 = String.format(string2, Arrays.copyOf(objArr2, objArr2.length));
        Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
        tv_reward_count2.setText(str2);
    }

    @Override // com.ido.life.module.user.IUserView
    public void showUnLoginPage() {
        TextView tv_login = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_login);
        Intrinsics.checkExpressionValueIsNotNull(tv_login, "tv_login");
        tv_login.setVisibility(0);
    }

    @Override // com.ido.life.module.user.IUserView
    public void onGetUserMedalFailed(String errNsg) {
        TextView tv_reward_count = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_reward_count);
        Intrinsics.checkExpressionValueIsNotNull(tv_reward_count, "tv_reward_count");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(R.string.user_reward_count_format);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.user_reward_count_format)");
        Object[] objArr = {0};
        String str = String.format(string, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        tv_reward_count.setText(str);
    }

    @Override // com.ido.life.module.user.IUserView
    public void showLoginPage() {
        TextView tv_login = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_login);
        Intrinsics.checkExpressionValueIsNotNull(tv_login, "tv_login");
        tv_login.setVisibility(8);
    }

    @Override // com.ido.life.module.user.IUserView
    public void onUnReadMessageCount(long count) {
        int dimensionPixelSize;
        if (count > 0) {
            OptionView lay_message = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_message);
            Intrinsics.checkExpressionValueIsNotNull(lay_message, "lay_message");
            RegularTextView regularTextView = (RegularTextView) lay_message.findViewById(com.ido.life.R.id.option_end_text);
            Intrinsics.checkExpressionValueIsNotNull(regularTextView, "lay_message.option_end_text");
            regularTextView.setVisibility(0);
            if (count > 99) {
                OptionView lay_message2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_message);
                Intrinsics.checkExpressionValueIsNotNull(lay_message2, "lay_message");
                RegularTextView regularTextView2 = (RegularTextView) lay_message2.findViewById(com.ido.life.R.id.option_end_text);
                Intrinsics.checkExpressionValueIsNotNull(regularTextView2, "lay_message.option_end_text");
                regularTextView2.setText("99+");
                dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sw_dp_3);
            } else {
                int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.sw_dp_2);
                OptionView lay_message3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_message);
                Intrinsics.checkExpressionValueIsNotNull(lay_message3, "lay_message");
                RegularTextView regularTextView3 = (RegularTextView) lay_message3.findViewById(com.ido.life.R.id.option_end_text);
                Intrinsics.checkExpressionValueIsNotNull(regularTextView3, "lay_message.option_end_text");
                regularTextView3.setText(String.valueOf(count));
                dimensionPixelSize = dimensionPixelSize2;
            }
            OptionView lay_message4 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_message);
            Intrinsics.checkExpressionValueIsNotNull(lay_message4, "lay_message");
            ((RegularTextView) lay_message4.findViewById(com.ido.life.R.id.option_end_text)).setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
            OptionView lay_message5 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_message);
            Intrinsics.checkExpressionValueIsNotNull(lay_message5, "lay_message");
            RegularTextView regularTextView4 = (RegularTextView) lay_message5.findViewById(com.ido.life.R.id.option_end_text);
            Intrinsics.checkExpressionValueIsNotNull(regularTextView4, "lay_message.option_end_text");
            regularTextView4.setGravity(17);
            OptionView lay_message6 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_message);
            Intrinsics.checkExpressionValueIsNotNull(lay_message6, "lay_message");
            ((RegularTextView) lay_message6.findViewById(com.ido.life.R.id.option_end_text)).setBackgroundResource(R.drawable.circle_text_bg);
            BaseActivity mActivity = this.mActivity;
            Intrinsics.checkExpressionValueIsNotNull(mActivity, "mActivity");
            Window window = mActivity.getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "mActivity.window");
            View decorView = window.getDecorView();
            Intrinsics.checkExpressionValueIsNotNull(decorView, "mActivity.window.decorView");
            decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.ido.life.module.user.UserFragment.onUnReadMessageCount.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    BaseActivity mActivity2 = UserFragment.this.mActivity;
                    Intrinsics.checkExpressionValueIsNotNull(mActivity2, "mActivity");
                    Window window2 = mActivity2.getWindow();
                    Intrinsics.checkExpressionValueIsNotNull(window2, "mActivity.window");
                    View decorView2 = window2.getDecorView();
                    Intrinsics.checkExpressionValueIsNotNull(decorView2, "mActivity.window.decorView");
                    decorView2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    OptionView lay_message7 = (OptionView) UserFragment.this._$_findCachedViewById(com.ido.life.R.id.lay_message);
                    Intrinsics.checkExpressionValueIsNotNull(lay_message7, "lay_message");
                    RegularTextView regularTextView5 = (RegularTextView) lay_message7.findViewById(com.ido.life.R.id.option_end_text);
                    Intrinsics.checkExpressionValueIsNotNull(regularTextView5, "lay_message.option_end_text");
                    int width = regularTextView5.getWidth();
                    OptionView lay_message8 = (OptionView) UserFragment.this._$_findCachedViewById(com.ido.life.R.id.lay_message);
                    Intrinsics.checkExpressionValueIsNotNull(lay_message8, "lay_message");
                    RegularTextView regularTextView6 = (RegularTextView) lay_message8.findViewById(com.ido.life.R.id.option_end_text);
                    Intrinsics.checkExpressionValueIsNotNull(regularTextView6, "lay_message.option_end_text");
                    int iMax = Math.max(width, regularTextView6.getHeight());
                    OptionView lay_message9 = (OptionView) UserFragment.this._$_findCachedViewById(com.ido.life.R.id.lay_message);
                    Intrinsics.checkExpressionValueIsNotNull(lay_message9, "lay_message");
                    RegularTextView regularTextView7 = (RegularTextView) lay_message9.findViewById(com.ido.life.R.id.option_end_text);
                    Intrinsics.checkExpressionValueIsNotNull(regularTextView7, "lay_message.option_end_text");
                    regularTextView7.setWidth(iMax);
                    OptionView lay_message10 = (OptionView) UserFragment.this._$_findCachedViewById(com.ido.life.R.id.lay_message);
                    Intrinsics.checkExpressionValueIsNotNull(lay_message10, "lay_message");
                    RegularTextView regularTextView8 = (RegularTextView) lay_message10.findViewById(com.ido.life.R.id.option_end_text);
                    Intrinsics.checkExpressionValueIsNotNull(regularTextView8, "lay_message.option_end_text");
                    regularTextView8.setHeight(iMax);
                }
            });
            return;
        }
        OptionView lay_message7 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_message);
        Intrinsics.checkExpressionValueIsNotNull(lay_message7, "lay_message");
        RegularTextView regularTextView5 = (RegularTextView) lay_message7.findViewById(com.ido.life.R.id.option_end_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView5, "lay_message.option_end_text");
        regularTextView5.setVisibility(8);
    }

    /* JADX INFO: renamed from: com.ido.life.module.user.UserFragment$flashLogCollectionSuccess$1, reason: invalid class name */
    /* JADX INFO: compiled from: UserFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.user.UserFragment$flashLogCollectionSuccess$1", f = "UserFragment.kt", i = {0}, l = {509}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        private CoroutineScope p$;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = UserFragment.this.new AnonymousClass1(completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.ido.life.module.user.UserFragment$flashLogCollectionSuccess$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: UserFragment.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.module.user.UserFragment$flashLogCollectionSuccess$1$1", f = "UserFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01191 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C01191(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C01191 c01191 = AnonymousClass1.this.new C01191(completion);
                c01191.p$ = (CoroutineScope) obj;
                return c01191;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01191) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                File file = new File(UserFragment.this.ZIP_PATH);
                if (file.exists() && file.isFile()) {
                    FileUtil.deleteFile(UserFragment.this.ZIP_PATH);
                }
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                FileUtil.zipFolder(logPathImpl.getFlashPath(), UserFragment.this.ZIP_PATH);
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                BuildersKt__BuildersKt.runBlocking$default(null, new C01191(null), 1, null);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (BuildersKt.withContext(main, anonymousClass2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.ido.life.module.user.UserFragment$flashLogCollectionSuccess$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: UserFragment.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.module.user.UserFragment$flashLogCollectionSuccess$1$2", f = "UserFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            AnonymousClass2(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = AnonymousClass1.this.new AnonymousClass2(completion);
                anonymousClass2.p$ = (CoroutineScope) obj;
                return anonymousClass2;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                Uri uriFromFile;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                UserFragment.this.dismissLoadingDialog();
                File file = new File(UserFragment.this.ZIP_PATH);
                if (file.exists()) {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    if (Build.VERSION.SDK_INT >= 24) {
                        Context context = UserFragment.this.getContext();
                        if (context == null) {
                            Intrinsics.throwNpe();
                        }
                        uriFromFile = FileProvider.getUriForFile(context, "com.boat.Xtend.two.provider", file);
                    } else {
                        uriFromFile = Uri.fromFile(new File(UserFragment.this.ZIP_PATH));
                    }
                    intent.putExtra("android.intent.extra.STREAM", uriFromFile);
                    BaseActivity mActivity = UserFragment.this.mActivity;
                    Intrinsics.checkExpressionValueIsNotNull(mActivity, "mActivity");
                    List<ResolveInfo> listQueryIntentActivities = mActivity.getPackageManager().queryIntentActivities(intent, 65536);
                    Intrinsics.checkExpressionValueIsNotNull(listQueryIntentActivities, "mActivity.packageManager…nager.MATCH_DEFAULT_ONLY)");
                    List<ResolveInfo> list = listQueryIntentActivities;
                    if (!(list == null || list.isEmpty())) {
                        UserFragment.this.startActivity(intent);
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    @Override // com.ido.life.module.user.IUserView
    public void flashLogCollectionSuccess() {
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        String logZipNamePath = logPathImpl.getLogZipNamePath();
        Intrinsics.checkExpressionValueIsNotNull(logZipNamePath, "LogPathImpl.getInstance().logZipNamePath");
        Object[] objArr = {"Flash日志"};
        String str = String.format(logZipNamePath, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        this.ZIP_PATH = str;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass1(null), 2, null);
    }

    @Override // com.ido.life.module.user.IUserView
    public void flashLogCollectionFailed() {
        dismissLoadingDialog();
    }

    @Override // com.ido.life.module.user.IUserView
    public void setUserName(String userName) {
        TextView tv_user_name = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_user_name);
        Intrinsics.checkExpressionValueIsNotNull(tv_user_name, "tv_user_name");
        tv_user_name.setText(userName);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [T, com.ido.common.dialog.CommBottomConfirmDialog] */
    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_my_reward) {
            startActivity(new Intent(this.mActivity, (Class<?>) UserMedalActivity.class));
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_my_data) {
            startActivity(new Intent(this.mActivity, (Class<?>) MyDataActivity.class));
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_my_report) {
            startActivity(new Intent(this.mActivity, (Class<?>) HealthReportActivity.class));
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_message) {
            startActivity(new Intent(this.mActivity, (Class<?>) UserMessageActivity.class));
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_profile) {
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            if (GreenDaoUtil.queryUserInfo(runTimeUtil.getUserId()) != null) {
                startActivity(new Intent(this.mActivity, (Class<?>) UserInfoActivity.class));
                return;
            }
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_help) {
            Intent intent = new Intent(this.mActivity, (Class<?>) CommonWebViewActivity.class);
            intent.putExtra("type", 6);
            startActivity(intent);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.user_manual) {
            if (TextUtils.isEmpty(this.mInstructionUrl)) {
                return;
            }
            Intent intent2 = new Intent(this.mActivity, (Class<?>) NativeWebViewActivity.class);
            intent2.putExtra(CommonWebViewActivity.FORM_URL, this.mInstructionUrl);
            intent2.putExtra("type", 8);
            startActivity(intent2);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_background_help) {
            Intent intent3 = new Intent(this.mActivity, (Class<?>) CommonWebViewActivity.class);
            intent3.putExtra("type", 7);
            startActivity(intent3);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_setting) {
            startActivity(new Intent(this.mActivity, (Class<?>) SettingNewActivity.class));
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_feedback) {
            startActivity(new Intent(this.mActivity, (Class<?>) FeedbackActivity.class));
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_check_version) {
            if (SPHelper.getIsChina()) {
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.huawei_check_version), getLanguageText(R.string.goto_app_market_download), getLanguageText(R.string.sync_ok), null, false);
                ((CommBottomConfirmDialog) objectRef.element).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.UserFragment.onClick.3
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        CommBottomConfirmDialog commBottomConfirmDialog = (CommBottomConfirmDialog) objectRef.element;
                        if (commBottomConfirmDialog != null) {
                            commBottomConfirmDialog.dismissAllowingStateLoss();
                        }
                    }
                });
                ((CommBottomConfirmDialog) objectRef.element).show(getFragmentManager());
                return;
            }
            AppMarketUtils.INSTANCE.gotoMarket(getContext());
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_abort) {
            startActivity(new Intent(this.mActivity, (Class<?>) AboutUsActivity.class));
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.tv_login) {
            this.mActivity.startActivity(new Intent(this.mActivity, (Class<?>) PreLoginAndRegisterActivity.class));
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "我的界面点击立即登录,跳转到登录注册页 ");
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_share) {
            try {
                showLogSelectDialog();
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_switch_server) {
            try {
                showServerSelectDialog();
                return;
            } catch (Exception e3) {
                e3.printStackTrace();
                return;
            }
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_auto_test) {
            try {
                startActivity(new Intent(this.mActivity, (Class<?>) AutoTestActivity.class));
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
    }

    @Override // com.ido.life.base.BaseFragment
    protected void refreshLanguage() {
        super.refreshLanguage();
        TextView tv_login = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_login);
        Intrinsics.checkExpressionValueIsNotNull(tv_login, "tv_login");
        tv_login.setText(getLanguageText(R.string.login_now));
        TextView tv_my_reward = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_my_reward);
        Intrinsics.checkExpressionValueIsNotNull(tv_my_reward, "tv_my_reward");
        tv_my_reward.setText(getLanguageText(R.string.user_center_my_reward));
        TextView tv_reward_count = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_reward_count);
        Intrinsics.checkExpressionValueIsNotNull(tv_reward_count, "tv_reward_count");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String languageText = getLanguageText(R.string.user_reward_count_format);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string.user_reward_count_format)");
        Object[] objArr = {Integer.valueOf(this.mModelList.size())};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        tv_reward_count.setText(str);
        TextView tv_my_data = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_my_data);
        Intrinsics.checkExpressionValueIsNotNull(tv_my_data, "tv_my_data");
        tv_my_data.setText(getLanguageText(R.string.user_center_my_data));
        TextView tv_my_report = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_my_report);
        Intrinsics.checkExpressionValueIsNotNull(tv_my_report, "tv_my_report");
        tv_my_report.setText(getLanguageText(R.string.user_center_life_report));
        OptionView lay_message = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_message);
        Intrinsics.checkExpressionValueIsNotNull(lay_message, "lay_message");
        RegularTextView regularTextView = (RegularTextView) lay_message.findViewById(com.ido.life.R.id.option_start_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView, "lay_message.option_start_text");
        regularTextView.setText(getLanguageText(R.string.message));
        OptionView lay_help = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_help);
        Intrinsics.checkExpressionValueIsNotNull(lay_help, "lay_help");
        RegularTextView regularTextView2 = (RegularTextView) lay_help.findViewById(com.ido.life.R.id.option_start_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView2, "lay_help.option_start_text");
        regularTextView2.setText(getLanguageText(R.string.help));
        OptionView user_manual = (OptionView) _$_findCachedViewById(com.ido.life.R.id.user_manual);
        Intrinsics.checkExpressionValueIsNotNull(user_manual, "user_manual");
        RegularTextView regularTextView3 = (RegularTextView) user_manual.findViewById(com.ido.life.R.id.option_start_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView3, "user_manual.option_start_text");
        regularTextView3.setText(getLanguageText(R.string.user_manual));
        OptionView lay_setting = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_setting);
        Intrinsics.checkExpressionValueIsNotNull(lay_setting, "lay_setting");
        RegularTextView regularTextView4 = (RegularTextView) lay_setting.findViewById(com.ido.life.R.id.option_start_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView4, "lay_setting.option_start_text");
        regularTextView4.setText(getLanguageText(R.string.mine_set));
        OptionView lay_feedback = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_feedback);
        Intrinsics.checkExpressionValueIsNotNull(lay_feedback, "lay_feedback");
        RegularTextView regularTextView5 = (RegularTextView) lay_feedback.findViewById(com.ido.life.R.id.option_start_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView5, "lay_feedback.option_start_text");
        regularTextView5.setText(getLanguageText(R.string.feedback));
        OptionView lay_check_version = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_check_version);
        Intrinsics.checkExpressionValueIsNotNull(lay_check_version, "lay_check_version");
        RegularTextView regularTextView6 = (RegularTextView) lay_check_version.findViewById(com.ido.life.R.id.option_start_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView6, "lay_check_version.option_start_text");
        regularTextView6.setText(getLanguageText(R.string.check_version));
        OptionView lay_abort = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_abort);
        Intrinsics.checkExpressionValueIsNotNull(lay_abort, "lay_abort");
        RegularTextView regularTextView7 = (RegularTextView) lay_abort.findViewById(com.ido.life.R.id.option_start_text);
        Intrinsics.checkExpressionValueIsNotNull(regularTextView7, "lay_abort.option_start_text");
        regularTextView7.setText(getLanguageText(R.string.mine_about));
        TextView tv_edit_name = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_edit_name);
        Intrinsics.checkExpressionValueIsNotNull(tv_edit_name, "tv_edit_name");
        tv_edit_name.setText(getLanguageText(R.string.edit));
        Object obj = SPUtils.get(SHOW_SHARE, false);
        if (obj == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
        }
        if (((Boolean) obj).booleanValue()) {
            OptionView lay_share = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_share);
            Intrinsics.checkExpressionValueIsNotNull(lay_share, "lay_share");
            lay_share.setVisibility(0);
            UserFragment userFragment = this;
            ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_share)).setOnClickListener(userFragment);
            OptionView lay_auto_test = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_auto_test);
            Intrinsics.checkExpressionValueIsNotNull(lay_auto_test, "lay_auto_test");
            lay_auto_test.setVisibility(0);
            ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_auto_test)).setOnClickListener(userFragment);
            OptionView lay_switch_server = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_switch_server);
            Intrinsics.checkExpressionValueIsNotNull(lay_switch_server, "lay_switch_server");
            lay_switch_server.setVisibility(0);
            ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_switch_server)).setOnClickListener(userFragment);
        } else {
            OptionView lay_share2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_share);
            Intrinsics.checkExpressionValueIsNotNull(lay_share2, "lay_share");
            lay_share2.setVisibility(8);
            OptionView lay_switch_server2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_switch_server);
            Intrinsics.checkExpressionValueIsNotNull(lay_switch_server2, "lay_switch_server");
            lay_switch_server2.setVisibility(8);
            OptionView lay_auto_test2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_auto_test);
            Intrinsics.checkExpressionValueIsNotNull(lay_auto_test2, "lay_auto_test");
            lay_auto_test2.setVisibility(8);
        }
        ((UserPresenter) this.mPresenter).getUserInfo();
        this.mInstructionUrl = "";
        ((UserPresenter) this.mPresenter).requestInstruction();
        if (this.mAppVersionInfo == null) {
            ((UserPresenter) this.mPresenter).checkVersion();
        }
        ((UserPresenter) this.mPresenter).getUnReadMessageCount();
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        ArrayList<UserModelnfo> arrayList = this.mModelList;
        if ((arrayList == null || arrayList.isEmpty()) || this.mModelList.size() <= position) {
            return;
        }
        UserModelnfo userModelnfo = this.mModelList.get(position);
        Intrinsics.checkExpressionValueIsNotNull(userModelnfo, "mModelList[position]");
        UserModelnfo userModelnfo2 = userModelnfo;
        if (userModelnfo2 != null) {
            Intent intent = new Intent(this.mActivity, (Class<?>) UserMedalDetailActivity.class);
            intent.putExtra(UserMedalDetailActivity.INSTANCE.getMEDAL_Id(), userModelnfo2.getUserModelEnum().getMedalId());
            startActivity(intent);
        }
    }

    @Override // com.ido.life.module.user.IUserView
    public void onGetDeviceInstruction(String url) {
        this.mInstructionUrl = url;
        if (!TextUtils.isEmpty(this.mInstructionUrl)) {
            OptionView user_manual = (OptionView) _$_findCachedViewById(com.ido.life.R.id.user_manual);
            Intrinsics.checkExpressionValueIsNotNull(user_manual, "user_manual");
            user_manual.setVisibility(0);
        } else {
            OptionView user_manual2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.user_manual);
            Intrinsics.checkExpressionValueIsNotNull(user_manual2, "user_manual");
            user_manual2.setVisibility(8);
        }
    }

    @Override // com.ido.life.module.user.IUserView
    public void onGetNewVersionSuccess(AppInfoEntity.AppInfo appInfo) {
        if (this.mAppVersionInfo != null) {
            return;
        }
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_check_version)).setEndImgVisible(appInfo != null);
        this.mAppVersionInfo = appInfo;
        if (this.mAppVersionInfo != null) {
            NormalToast.showToast(getLanguageText(R.string.find_new_version), 2000);
        }
    }

    @Override // com.ido.life.module.user.IUserView
    public void onGetNewVersionFailed(String errMsg) {
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_check_version)).setEndImgVisible(false);
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage<?> message) {
        super.handleMessage(message);
        Integer numValueOf = message != null ? Integer.valueOf(message.getType()) : null;
        if (numValueOf != null && numValueOf.intValue() == 811) {
            if (this.mPresenter != 0) {
                ((UserPresenter) this.mPresenter).getUserInfo();
            }
        } else if (numValueOf != null && numValueOf.intValue() == 840) {
            if (this.mPresenter != 0) {
                ((UserPresenter) this.mPresenter).getUserMedal();
            }
        } else {
            if (numValueOf == null || numValueOf.intValue() != 839 || this.mPresenter == 0) {
                return;
            }
            ((UserPresenter) this.mPresenter).getUnReadMessageCount();
        }
    }
}