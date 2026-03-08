package com.ido.life.module.user.set;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.VeryFitApp;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.boatservice.DataUploadService;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.viewgroup.OptionView;
import com.ido.life.database.model.PrivateSafeSetting;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.login.PreLoginAndRegisterActivity;
import com.ido.life.module.user.set.cancel.CancelConfirmActivity;
import com.ido.life.module.user.set.data.DataShareActivity;
import com.ido.life.module.user.set.modify.ModifyPwdActivity;
import com.ido.life.module.user.set.modifyemail.ModifyEmailActivity;
import com.ido.life.module.user.set.privacyandsecurity.PrivacyAndSecurityActivity;
import com.ido.life.module.user.set.settingitem.SettingItemActivity;
import com.ido.life.module.user.set.target.SettingTargetActivity;
import com.ido.life.syncdownload.DataDownLoadService;
import com.ido.life.util.DateUtil;
import com.ido.life.util.FileUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPUtils;
import com.ido.life.util.eventbus.EventBusHelper;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: SettingNewActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0015\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0007H\u0014J\u0006\u0010\u0015\u001a\u00020\u0013J\u0016\u0010\u0016\u001a\u00020\u00132\f\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0018H\u0016J\u0006\u0010\u0019\u001a\u00020\u0013J\b\u0010\u001a\u001a\u00020\u0013H\u0014J\b\u0010\u001b\u001a\u00020\u0013H\u0016J\b\u0010\u001c\u001a\u00020\u0013H\u0002J\u0006\u0010\u001d\u001a\u00020\u0013J\u0006\u0010\u001e\u001a\u00020\u0013J\"\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00072\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\u0012\u0010$\u001a\u00020\u00132\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0010\u0010'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020\u00132\u0006\u0010+\u001a\u00020\u0007H\u0016J\u0010\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u0007H\u0016J\u0010\u0010.\u001a\u00020\u00132\u0006\u0010/\u001a\u00020\u0007H\u0016J\u0010\u00100\u001a\u00020\u00132\u0006\u00101\u001a\u00020\u0007H\u0016J\b\u00102\u001a\u00020\u0013H\u0016J\b\u00103\u001a\u00020\u0013H\u0016J\b\u00104\u001a\u00020\u0013H\u0016J\b\u00105\u001a\u00020\u0013H\u0002J\u0006\u00106\u001a\u00020\u0013J\b\u00107\u001a\u00020\u0013H\u0002J\b\u00108\u001a\u00020\u0013H\u0002J\b\u00109\u001a\u00020\u0013H\u0002J\b\u0010:\u001a\u00020\u0013H\u0002J\b\u0010;\u001a\u00020\u0013H\u0002J\b\u0010<\u001a\u00020\u0013H\u0002J\b\u0010=\u001a\u00020\u0013H\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u0007X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lcom/ido/life/module/user/set/SettingNewActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/user/set/SettingNewPresenter;", "Landroid/view/View$OnClickListener;", "Lcom/ido/life/module/user/set/ISettingNewView;", "()V", "REQUEST_CODE", "", "getREQUEST_CODE", "()I", "RESULT_CODE", "getRESULT_CODE", "TAG", "", "mHistoryDataLoadingAnimator", "Landroid/animation/ValueAnimator;", "mRetryDialog", "Lcom/ido/common/dialog/CommBottomConfirmDialog;", "clearCache", "", "getLayoutResId", "goBack", "handleMessage", "message", "Lcom/ido/life/base/BaseMessage;", "hideLoading", "initLabelLanguage", "initViews", "loginOut", "loginOutFailed", "loginOutSuccess", "onActivityResult", "requestCode", "resultCode", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "onGetCacleData", "cacheData", "", "onGetMapEngineSuccess", "mapEngine", "onGetTimeFormatSuccess", "timeFormat", "onGetUnitSuccess", "unit", "onGetWeekStartSuccess", "weekStart", "onHistoryDataDownloadFailed", "onHistoryDataDownloadSuccess", "onHistoryDataDownloading", "showClearCacheDialog", "showLoading", "showRetryDialog", "startHistoryLoadingAnimator", "stopHistoryLoadingAnimator", "syncData", "syncDataDialog", "toCancel", "toGuide", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SettingNewActivity extends BaseActivity<SettingNewPresenter> implements View.OnClickListener, ISettingNewView {
    private HashMap _$_findViewCache;
    private ValueAnimator mHistoryDataLoadingAnimator;
    private CommBottomConfirmDialog mRetryDialog;
    private final String TAG = "SettingNewActivity";
    private final int REQUEST_CODE = 5002;
    private final int RESULT_CODE = 5003;

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
        return R.layout.activity_setting_new_layout;
    }

    @Override // com.ido.life.module.user.set.ISettingNewView
    public void onGetMapEngineSuccess(int mapEngine) {
    }

    public final int getREQUEST_CODE() {
        return this.REQUEST_CODE;
    }

    public final int getRESULT_CODE() {
        return this.RESULT_CODE;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.mine_set));
        OptionView lay_my_target = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_my_target);
        Intrinsics.checkExpressionValueIsNotNull(lay_my_target, "lay_my_target");
        lay_my_target.setStartText(getLanguageText(R.string.my_target));
        OptionView lay_time = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_time);
        Intrinsics.checkExpressionValueIsNotNull(lay_time, "lay_time");
        lay_time.setStartText(getLanguageText(R.string.sport_time));
        OptionView lay_unit = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_unit);
        Intrinsics.checkExpressionValueIsNotNull(lay_unit, "lay_unit");
        lay_unit.setStartText(getLanguageText(R.string.my_unit));
        OptionView lay_week_start = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_week_start);
        Intrinsics.checkExpressionValueIsNotNull(lay_week_start, "lay_week_start");
        lay_week_start.setStartText(getLanguageText(R.string.my_week_start));
        OptionView lay_clear_cache = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_clear_cache);
        Intrinsics.checkExpressionValueIsNotNull(lay_clear_cache, "lay_clear_cache");
        lay_clear_cache.setStartText(getLanguageText(R.string.me_clear_cache_ios));
        if (RunTimeUtil.getInstance().hasLogin()) {
            OptionView lay_private = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_private);
            Intrinsics.checkExpressionValueIsNotNull(lay_private, "lay_private");
            lay_private.setVisibility(0);
            OptionView lay_third_protocal = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_third_protocal);
            Intrinsics.checkExpressionValueIsNotNull(lay_third_protocal, "lay_third_protocal");
            lay_third_protocal.setVisibility(0);
            OptionView lay_update_email = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_update_email);
            Intrinsics.checkExpressionValueIsNotNull(lay_update_email, "lay_update_email");
            lay_update_email.setVisibility(0);
            OptionView lay_update_password = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_update_password);
            Intrinsics.checkExpressionValueIsNotNull(lay_update_password, "lay_update_password");
            lay_update_password.setVisibility(0);
            View space_one = _$_findCachedViewById(com.ido.life.R.id.space_one);
            Intrinsics.checkExpressionValueIsNotNull(space_one, "space_one");
            space_one.setVisibility(8);
            OptionView lay_logout = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_logout);
            Intrinsics.checkExpressionValueIsNotNull(lay_logout, "lay_logout");
            lay_logout.setVisibility(0);
            View space_two = _$_findCachedViewById(com.ido.life.R.id.space_two);
            Intrinsics.checkExpressionValueIsNotNull(space_two, "space_two");
            space_two.setVisibility(0);
            OptionView lay_cancel = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_cancel);
            Intrinsics.checkExpressionValueIsNotNull(lay_cancel, "lay_cancel");
            lay_cancel.setVisibility(0);
            View space_three = _$_findCachedViewById(com.ido.life.R.id.space_three);
            Intrinsics.checkExpressionValueIsNotNull(space_three, "space_three");
            space_three.setVisibility(0);
            View space_four = _$_findCachedViewById(com.ido.life.R.id.space_four);
            Intrinsics.checkExpressionValueIsNotNull(space_four, "space_four");
            space_four.setVisibility(0);
            LinearLayout ll_sync_data = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_sync_data);
            Intrinsics.checkExpressionValueIsNotNull(ll_sync_data, "ll_sync_data");
            ll_sync_data.setVisibility(0);
            ((SettingNewPresenter) this.mPresenter).initConfig();
            TextView tv_sync_data = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_sync_data);
            Intrinsics.checkExpressionValueIsNotNull(tv_sync_data, "tv_sync_data");
            tv_sync_data.setText(getLanguageText(R.string.mine_set_sync_data));
            Object obj = SPUtils.get(Constants.SYNC_DATA_TIME, "");
            if (!TextUtils.isEmpty((CharSequence) obj)) {
                TextView tv_last_sync_time = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_last_sync_time);
                Intrinsics.checkExpressionValueIsNotNull(tv_last_sync_time, "tv_last_sync_time");
                tv_last_sync_time.setVisibility(0);
                TextView tv_last_sync_time2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_last_sync_time);
                Intrinsics.checkExpressionValueIsNotNull(tv_last_sync_time2, "tv_last_sync_time");
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String languageText = getLanguageText(R.string.mine_set_last_sync_time);
                Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string.mine_set_last_sync_time)");
                Object[] objArr = {DateUtil.format(DateUtil.string2Date(obj.toString(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_DMYHm)};
                String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                tv_last_sync_time2.setText(str);
            } else {
                TextView tv_last_sync_time3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_last_sync_time);
                Intrinsics.checkExpressionValueIsNotNull(tv_last_sync_time3, "tv_last_sync_time");
                tv_last_sync_time3.setVisibility(8);
            }
            TextView tv_manual_syncing = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_manual_syncing);
            Intrinsics.checkExpressionValueIsNotNull(tv_manual_syncing, "tv_manual_syncing");
            tv_manual_syncing.setText(getLanguageText(R.string.my_tip_data_syncing));
            RegularTextView manual_sync = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.manual_sync);
            Intrinsics.checkExpressionValueIsNotNull(manual_sync, "manual_sync");
            manual_sync.setText(getLanguageText(R.string.mine_set_manual_sync));
            OptionView lay_private2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_private);
            Intrinsics.checkExpressionValueIsNotNull(lay_private2, "lay_private");
            lay_private2.setStartText(getLanguageText(R.string.private_safe));
            OptionView lay_third_protocal2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_third_protocal);
            Intrinsics.checkExpressionValueIsNotNull(lay_third_protocal2, "lay_third_protocal");
            lay_third_protocal2.setStartText(getLanguageText(R.string.data_share_authorization));
            OptionView lay_update_email2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_update_email);
            Intrinsics.checkExpressionValueIsNotNull(lay_update_email2, "lay_update_email");
            lay_update_email2.setStartText(getLanguageText(R.string.mine_modify_email));
            OptionView lay_update_password2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_update_password);
            Intrinsics.checkExpressionValueIsNotNull(lay_update_password2, "lay_update_password");
            lay_update_password2.setStartText(getLanguageText(R.string.mine_modify_password));
            OptionView lay_logout2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_logout);
            Intrinsics.checkExpressionValueIsNotNull(lay_logout2, "lay_logout");
            lay_logout2.setStartText(getLanguageText(R.string.login_exit_login));
            OptionView lay_cancel2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_cancel);
            Intrinsics.checkExpressionValueIsNotNull(lay_cancel2, "lay_cancel");
            lay_cancel2.setStartText(getLanguageText(R.string.login_cancel_account));
        } else {
            OptionView lay_private3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_private);
            Intrinsics.checkExpressionValueIsNotNull(lay_private3, "lay_private");
            lay_private3.setVisibility(8);
            OptionView lay_third_protocal3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_third_protocal);
            Intrinsics.checkExpressionValueIsNotNull(lay_third_protocal3, "lay_third_protocal");
            lay_third_protocal3.setVisibility(0);
            OptionView lay_update_email3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_update_email);
            Intrinsics.checkExpressionValueIsNotNull(lay_update_email3, "lay_update_email");
            lay_update_email3.setVisibility(8);
            OptionView lay_update_password3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_update_password);
            Intrinsics.checkExpressionValueIsNotNull(lay_update_password3, "lay_update_password");
            lay_update_password3.setVisibility(8);
            View space_one2 = _$_findCachedViewById(com.ido.life.R.id.space_one);
            Intrinsics.checkExpressionValueIsNotNull(space_one2, "space_one");
            space_one2.setVisibility(8);
            OptionView lay_logout3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_logout);
            Intrinsics.checkExpressionValueIsNotNull(lay_logout3, "lay_logout");
            lay_logout3.setVisibility(8);
            View space_two2 = _$_findCachedViewById(com.ido.life.R.id.space_two);
            Intrinsics.checkExpressionValueIsNotNull(space_two2, "space_two");
            space_two2.setVisibility(8);
            OptionView lay_cancel3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_cancel);
            Intrinsics.checkExpressionValueIsNotNull(lay_cancel3, "lay_cancel");
            lay_cancel3.setVisibility(8);
            View space_three2 = _$_findCachedViewById(com.ido.life.R.id.space_three);
            Intrinsics.checkExpressionValueIsNotNull(space_three2, "space_three");
            space_three2.setVisibility(0);
            LinearLayout ll_sync_data2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_sync_data);
            Intrinsics.checkExpressionValueIsNotNull(ll_sync_data2, "ll_sync_data");
            ll_sync_data2.setVisibility(8);
            LinearLayout lay_history_sync = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_history_sync);
            Intrinsics.checkExpressionValueIsNotNull(lay_history_sync, "lay_history_sync");
            lay_history_sync.setVisibility(8);
            View lay_history_sync_divider = _$_findCachedViewById(com.ido.life.R.id.lay_history_sync_divider);
            Intrinsics.checkExpressionValueIsNotNull(lay_history_sync_divider, "lay_history_sync_divider");
            lay_history_sync_divider.setVisibility(8);
            View sync_devider_line = _$_findCachedViewById(com.ido.life.R.id.sync_devider_line);
            Intrinsics.checkExpressionValueIsNotNull(sync_devider_line, "sync_devider_line");
            sync_devider_line.setVisibility(8);
        }
        ((OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_time)).postDelayed(new Runnable() { // from class: com.ido.life.module.user.set.SettingNewActivity.initLabelLanguage.1
            @Override // java.lang.Runnable
            public final void run() {
                RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
                int timeFormat = runTimeUtil.getTimeFormat();
                if (timeFormat == 2) {
                    OptionView lay_time2 = (OptionView) SettingNewActivity.this._$_findCachedViewById(com.ido.life.R.id.lay_time);
                    Intrinsics.checkExpressionValueIsNotNull(lay_time2, "lay_time");
                    lay_time2.setEndText(SettingNewActivity.this.getLanguageText(R.string.hours_12));
                } else if (timeFormat == 1) {
                    OptionView lay_time3 = (OptionView) SettingNewActivity.this._$_findCachedViewById(com.ido.life.R.id.lay_time);
                    Intrinsics.checkExpressionValueIsNotNull(lay_time3, "lay_time");
                    lay_time3.setEndText(SettingNewActivity.this.getLanguageText(R.string.hours_24));
                } else {
                    OptionView lay_time4 = (OptionView) SettingNewActivity.this._$_findCachedViewById(com.ido.life.R.id.lay_time);
                    Intrinsics.checkExpressionValueIsNotNull(lay_time4, "lay_time");
                    lay_time4.setEndText(SettingNewActivity.this.getLanguageText(R.string.device_follow_system_time));
                }
                RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
                if (runTimeUtil2.getUnitSet() == 1) {
                    OptionView lay_unit2 = (OptionView) SettingNewActivity.this._$_findCachedViewById(com.ido.life.R.id.lay_unit);
                    Intrinsics.checkExpressionValueIsNotNull(lay_unit2, "lay_unit");
                    lay_unit2.setEndText(SettingNewActivity.this.getLanguageText(R.string.mine_unit_metric_system));
                } else {
                    OptionView lay_unit3 = (OptionView) SettingNewActivity.this._$_findCachedViewById(com.ido.life.R.id.lay_unit);
                    Intrinsics.checkExpressionValueIsNotNull(lay_unit3, "lay_unit");
                    lay_unit3.setEndText(SettingNewActivity.this.getLanguageText(R.string.mine_unit_british_system));
                }
                RunTimeUtil runTimeUtil3 = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil3, "RunTimeUtil.getInstance()");
                int weekStart = runTimeUtil3.getWeekStart();
                if (weekStart == 1) {
                    OptionView lay_week_start2 = (OptionView) SettingNewActivity.this._$_findCachedViewById(com.ido.life.R.id.lay_week_start);
                    Intrinsics.checkExpressionValueIsNotNull(lay_week_start2, "lay_week_start");
                    lay_week_start2.setEndText(SettingNewActivity.this.getLanguageText(R.string.public_time_sunday));
                } else if (weekStart == 2) {
                    OptionView lay_week_start3 = (OptionView) SettingNewActivity.this._$_findCachedViewById(com.ido.life.R.id.lay_week_start);
                    Intrinsics.checkExpressionValueIsNotNull(lay_week_start3, "lay_week_start");
                    lay_week_start3.setEndText(SettingNewActivity.this.getLanguageText(R.string.public_time_monday));
                } else {
                    if (weekStart != 7) {
                        return;
                    }
                    OptionView lay_week_start4 = (OptionView) SettingNewActivity.this._$_findCachedViewById(com.ido.life.R.id.lay_week_start);
                    Intrinsics.checkExpressionValueIsNotNull(lay_week_start4, "lay_week_start");
                    lay_week_start4.setEndText(SettingNewActivity.this.getLanguageText(R.string.public_time_saturday));
                }
            }
        }, 500L);
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
    }

    private final void showRetryDialog() {
        if (this.mRetryDialog == null) {
            this.mRetryDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.tips), getLanguageText(R.string.mine_manual_synced_failed), getLanguageText(R.string.device_retry), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.SettingNewActivity.showRetryDialog.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SettingNewActivity.this.syncData();
                }
            });
        }
        CommBottomConfirmDialog commBottomConfirmDialog = this.mRetryDialog;
        if (commBottomConfirmDialog != null) {
            commBottomConfirmDialog.show(getSupportFragmentManager());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void syncData() {
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            NormalToast.showToast(getLanguageText(R.string.mine_manual_sync_no_network), 2000);
            return;
        }
        RegularTextView manual_sync = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.manual_sync);
        Intrinsics.checkExpressionValueIsNotNull(manual_sync, "manual_sync");
        manual_sync.setVisibility(8);
        View layout_sync_data = _$_findCachedViewById(com.ido.life.R.id.layout_sync_data);
        Intrinsics.checkExpressionValueIsNotNull(layout_sync_data, "layout_sync_data");
        layout_sync_data.setVisibility(0);
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_sync_data_img)).clearAnimation();
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 5400, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(15000L);
        rotateAnimation.setRepeatCount(-1);
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_sync_data_img)).startAnimation(rotateAnimation);
        DataUploadService.INSTANCE.start(false, true);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_my_target) {
            startActivity(new Intent(this, (Class<?>) SettingTargetActivity.class));
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.manual_sync) {
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            PrivateSafeSetting safeSetting = GreenDaoUtil.queryPrivateSafeSetting(runTimeUtil.getUserId());
            Intrinsics.checkExpressionValueIsNotNull(safeSetting, "safeSetting");
            if (safeSetting.getSavePrivateData() && safeSetting.getSaveSportData() && safeSetting.getSaveHealthData()) {
                syncData();
                return;
            } else {
                syncDataDialog();
                return;
            }
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_time) {
            Intent intent = new Intent(this, (Class<?>) SettingItemActivity.class);
            intent.putExtra(SettingItemActivity.INSTANCE.getSETTING_TYPE(), SettingItemActivity.INSTANCE.getTYPE_SETTING_TIME());
            startActivity(intent);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_unit) {
            Intent intent2 = new Intent(this, (Class<?>) SettingItemActivity.class);
            intent2.putExtra(SettingItemActivity.INSTANCE.getSETTING_TYPE(), SettingItemActivity.INSTANCE.getTYPE_SETTING_UNIT());
            startActivity(intent2);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_week_start) {
            Intent intent3 = new Intent(this, (Class<?>) SettingItemActivity.class);
            intent3.putExtra(SettingItemActivity.INSTANCE.getSETTING_TYPE(), SettingItemActivity.INSTANCE.getTYPE_SETTING_WEEK_START());
            startActivity(intent3);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_clear_cache) {
            clearCache();
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_private) {
            startActivity(new Intent(this, (Class<?>) PrivacyAndSecurityActivity.class));
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_third_protocal) {
            startActivity(new Intent(this, (Class<?>) DataShareActivity.class));
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_update_email) {
            startActivity(new Intent(this, (Class<?>) ModifyEmailActivity.class));
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_update_password) {
            startActivity(new Intent(this, (Class<?>) ModifyPwdActivity.class));
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_logout) {
            final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(ResourceUtil.getString(R.string.me_are_sure_exit_ios), ResourceUtil.getString(R.string.public_tip_confirm), ResourceUtil.getString(R.string.public_tip_cancel), true);
            commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
            commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.SettingNewActivity.onClick.4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                }
            });
            commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.SettingNewActivity.onClick.5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    try {
                        commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                        SettingNewActivity.this.loginOut();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        LogPath logPathImpl = LogPathImpl.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                        CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), SettingNewActivity.this.TAG, "退出登录抛异常，走catch");
                        SettingNewActivity.this.hideLoading();
                        UserInfo userInfoQueryLatestUserInfo = GreenDaoUtil.queryLatestUserInfo();
                        if (userInfoQueryLatestUserInfo != null) {
                            SPUtils.put(Constants.LAST_ACCOUNT_KEY, userInfoQueryLatestUserInfo.getEmail());
                        }
                        LogPath logPathImpl2 = LogPathImpl.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
                        CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), SettingNewActivity.this.TAG, "无网退出，上一次登录的账号邮箱：" + SPUtils.get(Constants.LAST_ACCOUNT_KEY, ""));
                        VeryFitApp.getApp().quitClearCacheThread(new VeryFitApp.ClearCacheCallBack() { // from class: com.ido.life.module.user.set.SettingNewActivity.onClick.5.1
                            @Override // com.ido.life.VeryFitApp.ClearCacheCallBack
                            public final void clearSuccess() {
                                RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
                                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
                                runTimeUtil2.setUserId(-1);
                                LogPath logPathImpl3 = LogPathImpl.getInstance();
                                Intrinsics.checkExpressionValueIsNotNull(logPathImpl3, "LogPathImpl.getInstance()");
                                CommonLogUtil.printAndSave(logPathImpl3.getLoginRegisterLogPath(), SettingNewActivity.this.TAG, "退出登录在没有网络的情况下直接删除缓存信息，跳转到登录注册页。");
                                SettingNewActivity.this.toGuide();
                            }
                        });
                    }
                }
            });
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_cancel) {
            toCancel();
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.lay_history_sync) {
            if (NetworkUtil.isConnected(this)) {
                DataDownLoadService.Companion companion = DataDownLoadService.INSTANCE;
                RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
                companion.loadHistoryData(CollectionsKt.mutableListOf(Long.valueOf(runTimeUtil2.getUserId())));
                return;
            }
            NormalToast.showToast(getLanguageText(R.string.public_net_unuse));
        }
    }

    private final void syncDataDialog() {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(ResourceUtil.getString(R.string.main_setting_manual_sync_dialog), ResourceUtil.getString(R.string.public_tip_confirm), ResourceUtil.getString(R.string.public_tip_cancel), true);
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.SettingNewActivity.syncDataDialog.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.SettingNewActivity.syncDataDialog.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                GreenDaoUtil.saveConfig(true, true, true, null);
                SettingNewActivity.this.syncData();
            }
        });
    }

    private final void clearCache() {
        File cacheFile = getCacheDir();
        if (cacheFile.exists()) {
            Intrinsics.checkExpressionValueIsNotNull(cacheFile, "cacheFile");
            if (cacheFile.isDirectory()) {
                File[] fileArrListFiles = cacheFile.listFiles();
                boolean z = true;
                if (fileArrListFiles != null) {
                    if (!(fileArrListFiles.length == 0)) {
                        z = false;
                    }
                }
                if (!z) {
                    for (File file : fileArrListFiles) {
                        if (file instanceof File) {
                            try {
                                file.delete();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        } else {
                            try {
                                FileUtil.deleteDirectory(file);
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        NormalToast.showToast(getLanguageText(R.string.clear_cache_success), 2000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loginOut() {
        showLoading();
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "showDialog " + WaitingDialog.isShowing());
        DataDownLoadService.INSTANCE.stop();
        loginOutSuccess();
    }

    public final void showLoading() {
        WaitingDialog.showDialog(this);
    }

    public final void hideLoading() {
        WaitingDialog.hideDialog();
    }

    public final void loginOutSuccess() {
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            SPUtils.put(Constants.APP_LOGIN_OUT, true);
            DataUploadService.Companion.start$default(DataUploadService.INSTANCE, true, false, 2, null);
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "退出登录在有网络的情况下，开启数据上传服务。");
            return;
        }
        UserInfo userInfoQueryLatestUserInfo = GreenDaoUtil.queryLatestUserInfo();
        Intrinsics.checkExpressionValueIsNotNull(userInfoQueryLatestUserInfo, "GreenDaoUtil.queryLatestUserInfo()");
        SPUtils.put(Constants.LAST_ACCOUNT_KEY, userInfoQueryLatestUserInfo.getEmail());
        LogPath logPathImpl2 = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), this.TAG, "无网退出，上一次登录的账号邮箱：" + SPUtils.get(Constants.LAST_ACCOUNT_KEY, ""));
        VeryFitApp.getApp().quitClearCacheThread(new VeryFitApp.ClearCacheCallBack() { // from class: com.ido.life.module.user.set.SettingNewActivity.loginOutSuccess.1
            @Override // com.ido.life.VeryFitApp.ClearCacheCallBack
            public final void clearSuccess() {
                RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
                runTimeUtil.setUserId(-1);
                LogPath logPathImpl3 = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl3, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl3.getLoginRegisterLogPath(), SettingNewActivity.this.TAG, "退出登录在没有网络的情况下直接删除缓存信息，跳转到登录注册页。");
                SettingNewActivity.this.hideLoading();
                SettingNewActivity.this.toGuide();
            }
        });
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage<?> message) {
        if (message == null) {
            return;
        }
        super.handleMessage(message);
        int type = message.getType();
        if (type == 812) {
            UserInfo userInfoQueryLatestUserInfo = GreenDaoUtil.queryLatestUserInfo();
            Intrinsics.checkExpressionValueIsNotNull(userInfoQueryLatestUserInfo, "GreenDaoUtil.queryLatestUserInfo()");
            SPUtils.put(Constants.LAST_ACCOUNT_KEY, userInfoQueryLatestUserInfo.getEmail());
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "有网退出登录后，上一次登录的账号邮箱：" + SPUtils.get(Constants.LAST_ACCOUNT_KEY, ""));
            VeryFitApp.getApp().quitClearCacheThread(new VeryFitApp.ClearCacheCallBack() { // from class: com.ido.life.module.user.set.SettingNewActivity.handleMessage.1
                @Override // com.ido.life.VeryFitApp.ClearCacheCallBack
                public final void clearSuccess() {
                    RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
                    runTimeUtil.setUserId(-1);
                    LogPath logPathImpl2 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
                    CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), SettingNewActivity.this.TAG, "上传数据DataUploadService完成，回调清除数据成功，跳转到登录注册页。");
                    SettingNewActivity.this.hideLoading();
                    SettingNewActivity.this.toGuide();
                }
            });
            return;
        }
        if (type != 833) {
            if (type != 834) {
                return;
            }
            RegularTextView manual_sync = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.manual_sync);
            Intrinsics.checkExpressionValueIsNotNull(manual_sync, "manual_sync");
            manual_sync.setVisibility(0);
            View layout_sync_data = _$_findCachedViewById(com.ido.life.R.id.layout_sync_data);
            Intrinsics.checkExpressionValueIsNotNull(layout_sync_data, "layout_sync_data");
            layout_sync_data.setVisibility(8);
            ((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_sync_data_img)).clearAnimation();
            LogPath logPathImpl2 = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), this.TAG, "用户手动上传数据失败 停止同步并弹框提示");
            showRetryDialog();
            return;
        }
        RegularTextView manual_sync2 = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.manual_sync);
        Intrinsics.checkExpressionValueIsNotNull(manual_sync2, "manual_sync");
        manual_sync2.setVisibility(0);
        View layout_sync_data2 = _$_findCachedViewById(com.ido.life.R.id.layout_sync_data);
        Intrinsics.checkExpressionValueIsNotNull(layout_sync_data2, "layout_sync_data");
        layout_sync_data2.setVisibility(8);
        TextView tv_last_sync_time = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_last_sync_time);
        Intrinsics.checkExpressionValueIsNotNull(tv_last_sync_time, "tv_last_sync_time");
        tv_last_sync_time.setVisibility(0);
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.iv_sync_data_img)).clearAnimation();
        TextView tv_last_sync_time2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_last_sync_time);
        Intrinsics.checkExpressionValueIsNotNull(tv_last_sync_time2, "tv_last_sync_time");
        tv_last_sync_time2.setVisibility(0);
        TextView tv_last_sync_time3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_last_sync_time);
        Intrinsics.checkExpressionValueIsNotNull(tv_last_sync_time3, "tv_last_sync_time");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String languageText = getLanguageText(R.string.mine_set_last_sync_time);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string.mine_set_last_sync_time)");
        Object[] objArr = {SPUtils.get(Constants.SYNC_DATA_TIME, "")};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        tv_last_sync_time3.setText(str);
        NormalToast.showToast(LanguageUtil.getLanguageText(R.string.home_main_sync_complete_ios));
        LogPath logPathImpl3 = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl3, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl3.getLoginRegisterLogPath(), this.TAG, "用户手动上传数据成功 停止同步,更新的保存时间为：" + SPUtils.get(Constants.SYNC_DATA_TIME, ""));
    }

    public final void loginOutFailed() {
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "退出登录失败");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toGuide() {
        goBack();
        startActivity(new Intent(this, (Class<?>) PreLoginAndRegisterActivity.class));
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "跳转到PreLoginAndRegisterActivity");
        EventBusHelper.post(Constants.EventConstants.EVENT_QUIT_LOGIN_SUCCESS_FINISH);
        LogPath logPathImpl2 = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl2.getLoginRegisterLogPath(), this.TAG, "退出登录成功---EVENT_QUIT_LOGIN_SUCCESS_FINISH");
    }

    public final void goBack() {
        ActivityCompat.finishAfterTransition(this);
    }

    private final void toCancel() {
        startActivityForResult(new Intent(this, (Class<?>) CancelConfirmActivity.class), this.REQUEST_CODE);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.REQUEST_CODE && resultCode == this.RESULT_CODE) {
            ActivityCompat.finishAfterTransition(this);
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), this.TAG, "注销账号成功后  就把设置界面关闭");
        }
    }

    private final void showClearCacheDialog() {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.me_clear_cache_ios), getLanguageText(R.string.me_clear_cach_detail_ios), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true);
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.SettingNewActivity.showClearCacheDialog.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.SettingNewActivity.showClearCacheDialog.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
    }

    @Override // com.ido.life.module.user.set.ISettingNewView
    public void onGetTimeFormatSuccess(int timeFormat) {
        OptionView optionView = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_time);
        if (optionView != null) {
            optionView.setEndText(getLanguageText(timeFormat == 2 ? R.string.hours_12 : R.string.hours_24));
        }
    }

    @Override // com.ido.life.module.user.set.ISettingNewView
    public void onGetUnitSuccess(int unit) {
        OptionView optionView = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_unit);
        if (optionView != null) {
            optionView.setEndText(getLanguageText(unit == 1 ? R.string.mine_unit_metric_system : R.string.mine_unit_british_system));
        }
    }

    @Override // com.ido.life.module.user.set.ISettingNewView
    public void onGetWeekStartSuccess(int weekStart) {
        OptionView optionView;
        if (weekStart == 1) {
            OptionView optionView2 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_week_start);
            if (optionView2 != null) {
                optionView2.setEndText(getLanguageText(R.string.public_time_sunday));
                return;
            }
            return;
        }
        if (weekStart != 2) {
            if (weekStart == 7 && (optionView = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_week_start)) != null) {
                optionView.setEndText(getLanguageText(R.string.public_time_saturday));
                return;
            }
            return;
        }
        OptionView optionView3 = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_week_start);
        if (optionView3 != null) {
            optionView3.setEndText(getLanguageText(R.string.public_time_monday));
        }
    }

    @Override // com.ido.life.module.user.set.ISettingNewView
    public void onGetCacleData(float cacheData) {
        OptionView optionView = (OptionView) _$_findCachedViewById(com.ido.life.R.id.lay_clear_cache);
        if (optionView != null) {
            Object[] objArr = {Float.valueOf(cacheData)};
            String str = String.format("%.1fM", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
            optionView.setEndText(str);
        }
    }

    @Override // com.ido.life.module.user.set.ISettingNewView
    public void onHistoryDataDownloadSuccess() {
        CommonLogUtil.d(this.TAG, "历史数据下拉成功");
        stopHistoryLoadingAnimator();
        ImageView imageView = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_history_sync_state);
        if (imageView != null) {
            imageView.setImageResource(R.mipmap.history_data_setting_sync_success);
        }
        ImageView imageView2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_history_sync_state);
        if (imageView2 != null) {
            imageView2.setRotation(0.0f);
        }
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_history_sync_state);
        if (textView != null) {
            textView.setText(getLanguageText(R.string.history_data_sync_success));
        }
        ImageView imageView3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_retry);
        if (imageView3 != null) {
            imageView3.setVisibility(8);
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_history_sync);
        if (linearLayout != null) {
            linearLayout.setOnClickListener(null);
        }
    }

    @Override // com.ido.life.module.user.set.ISettingNewView
    public void onHistoryDataDownloadFailed() {
        CommonLogUtil.d(this.TAG, "历史数据下拉失败");
        stopHistoryLoadingAnimator();
        ImageView imageView = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_history_sync_state);
        if (imageView != null) {
            imageView.setImageResource(R.mipmap.history_data_setting_sync_failed);
        }
        ImageView imageView2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_history_sync_state);
        if (imageView2 != null) {
            imageView2.setRotation(0.0f);
        }
        int historyDownloadProgress = ((SettingNewPresenter) this.mPresenter).getHistoryDownloadProgress();
        if (historyDownloadProgress >= 100) {
            historyDownloadProgress = 99;
        }
        ImageView imageView3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_retry);
        if (imageView3 != null) {
            imageView3.setVisibility(0);
        }
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_history_sync_state);
        if (textView != null) {
            textView.setText(getLanguageText(R.string.history_data_sync_failed) + "  " + historyDownloadProgress + '%');
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_history_sync);
        if (linearLayout != null) {
            linearLayout.setOnClickListener(this);
        }
    }

    @Override // com.ido.life.module.user.set.ISettingNewView
    public void onHistoryDataDownloading() {
        CommonLogUtil.d(this.TAG, "历史数据下拉中");
        if (this.mPresenter == 0) {
            return;
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_history_sync);
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        View view_$_findCachedViewById = _$_findCachedViewById(com.ido.life.R.id.lay_history_sync_divider);
        if (view_$_findCachedViewById != null) {
            view_$_findCachedViewById.setVisibility(0);
        }
        View view_$_findCachedViewById2 = _$_findCachedViewById(com.ido.life.R.id.sync_devider_line);
        if (view_$_findCachedViewById2 != null) {
            view_$_findCachedViewById2.setVisibility(0);
        }
        ImageView imageView = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_history_sync_state);
        if (imageView != null) {
            imageView.setImageResource(R.mipmap.history_data_setting_syncing);
        }
        ImageView imageView2 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_history_sync_state);
        if (imageView2 != null) {
            imageView2.setRotation(0.0f);
        }
        int historyDownloadProgress = ((SettingNewPresenter) this.mPresenter).getHistoryDownloadProgress();
        if (historyDownloadProgress >= 100) {
            historyDownloadProgress = 99;
        }
        ImageView imageView3 = (ImageView) _$_findCachedViewById(com.ido.life.R.id.img_retry);
        if (imageView3 != null) {
            imageView3.setVisibility(8);
        }
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_history_sync_state);
        if (textView != null) {
            textView.setText(getLanguageText(R.string.history_data_syncing) + "  " + historyDownloadProgress + '%');
        }
        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_history_sync);
        if (linearLayout2 != null) {
            linearLayout2.setOnClickListener(null);
        }
        startHistoryLoadingAnimator();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void stopHistoryLoadingAnimator() {
        /*
            r1 = this;
            android.animation.ValueAnimator r0 = r1.mHistoryDataLoadingAnimator
            if (r0 == 0) goto L23
            if (r0 != 0) goto L9
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L9:
            boolean r0 = r0.isRunning()
            if (r0 != 0) goto L1c
            android.animation.ValueAnimator r0 = r1.mHistoryDataLoadingAnimator
            if (r0 != 0) goto L16
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L16:
            boolean r0 = r0.isStarted()
            if (r0 == 0) goto L23
        L1c:
            android.animation.ValueAnimator r0 = r1.mHistoryDataLoadingAnimator
            if (r0 == 0) goto L23
            r0.cancel()
        L23:
            r0 = 0
            android.animation.ValueAnimator r0 = (android.animation.ValueAnimator) r0
            r1.mHistoryDataLoadingAnimator = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.user.set.SettingNewActivity.stopHistoryLoadingAnimator():void");
    }

    private final void startHistoryLoadingAnimator() {
        stopHistoryLoadingAnimator();
        this.mHistoryDataLoadingAnimator = ObjectAnimator.ofFloat((ImageView) _$_findCachedViewById(com.ido.life.R.id.img_history_sync_state), "rotation", 0.0f, 360.0f);
        ValueAnimator valueAnimator = this.mHistoryDataLoadingAnimator;
        if (valueAnimator != null) {
            valueAnimator.setDuration(1000L);
        }
        ValueAnimator valueAnimator2 = this.mHistoryDataLoadingAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.setInterpolator(new LinearInterpolator());
        }
        ValueAnimator valueAnimator3 = this.mHistoryDataLoadingAnimator;
        if (valueAnimator3 != null) {
            valueAnimator3.setRepeatCount(-1);
        }
        ValueAnimator valueAnimator4 = this.mHistoryDataLoadingAnimator;
        if (valueAnimator4 != null) {
            valueAnimator4.setRepeatMode(1);
        }
        ValueAnimator valueAnimator5 = this.mHistoryDataLoadingAnimator;
        if (valueAnimator5 != null) {
            valueAnimator5.start();
        }
    }
}