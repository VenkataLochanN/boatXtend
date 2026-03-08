package com.ido.life.base;

import android.view.View;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.life.base.BasePresenter;
import com.ido.life.util.ClickUtilKt;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseClickActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b&\u0018\u0000*\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bH\u0014J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\tH\u0016J\u0012\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000bH\u0004J\u0012\u0010\u0011\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000bH\u0004¨\u0006\u0012"}, d2 = {"Lcom/ido/life/base/BaseClickActivity;", "P", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "clickAction", "", "view", "Landroid/view/View;", "getLogPath", "", "kotlin.jvm.PlatformType", "onClick", "v", "printAndSaveLog", "msg", "printLog", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class BaseClickActivity<P extends BasePresenter<?>> extends BaseActivity<P> implements View.OnClickListener {
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

    public abstract void clickAction(View view);

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        if (!ClickUtilKt.canClick(v)) {
            printAndSaveLog("500毫秒之内只能触发一次点击。");
        } else {
            clickAction(v);
        }
    }

    protected final void printAndSaveLog(String msg) {
        String str = msg;
        if (str == null || str.length() == 0) {
            return;
        }
        CommonLogUtil.printAndSave(getLogPath(), getClass().getSimpleName(), msg);
    }

    protected final void printLog(String msg) {
        String str = msg;
        if (str == null || str.length() == 0) {
            return;
        }
        CommonLogUtil.d(getClass().getSimpleName(), msg);
    }

    protected String getLogPath() {
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        return logPathImpl.getLogPath();
    }
}