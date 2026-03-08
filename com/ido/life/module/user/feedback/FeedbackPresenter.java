package com.ido.life.module.user.feedback;

import android.content.Context;
import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.alexa.log.AlexaLogPath;
import com.ido.alexa.log.AlexaLogPathImpl;
import com.ido.ble.BLEManager;
import com.ido.ble.firmware.log.flash.ICollectFlashLogListener;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.http.Result;
import com.ido.common.utils.FileUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.database.model.Feedback;
import com.ido.life.util.DateUtil;
import java.io.File;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeedbackPresenter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005H\u0002J\u0010\u0010\n\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005J\u0006\u0010\f\u001a\u00020\u0007J\b\u0010\r\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/ido/life/module/user/feedback/FeedbackPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/user/feedback/IFeedbackView;", "()V", "mLogZipFilePath", "", "commitSuggest", "", "toCreateFeedback", "linkUrl", "updateImage", "filePath", "verify", "zipLogs", "app_release"}, k = 1, mv = {1, 1, 16})
public final class FeedbackPresenter extends BasePresenter<IFeedbackView> {
    private final String mLogZipFilePath;

    public FeedbackPresenter() {
        StringBuilder sb = new StringBuilder();
        Context appContext = IdoApp.getAppContext();
        Intrinsics.checkExpressionValueIsNotNull(appContext, "IdoApp.getAppContext()");
        File filesDir = appContext.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "IdoApp.getAppContext().filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/log.zip");
        this.mLogZipFilePath = sb.toString();
    }

    public static final /* synthetic */ IFeedbackView access$getView(FeedbackPresenter feedbackPresenter) {
        return feedbackPresenter.getView();
    }

    public final void commitSuggest() {
        if (getView() == null) {
            return;
        }
        IFeedbackView view = getView();
        Integer numValueOf = view != null ? Integer.valueOf(view.getMFeedbackType()) : null;
        if (numValueOf == null) {
            Intrinsics.throwNpe();
        }
        if (numValueOf.intValue() <= 0) {
            IFeedbackView view2 = getView();
            if (view2 != null) {
                view2.showError(ResourceUtil.getString(R.string.me_feedvc_selectdetail_ios));
                return;
            }
            return;
        }
        IFeedbackView view3 = getView();
        if (TextUtils.isEmpty(view3 != null ? view3.getDes() : null)) {
            IFeedbackView view4 = getView();
            if (view4 != null) {
                view4.showError(ResourceUtil.getString(R.string.mine_question_suggestion_detailed));
                return;
            }
            return;
        }
        IFeedbackView view5 = getView();
        if (view5 != null) {
            view5.showLoading();
        }
        IFeedbackView view6 = getView();
        Boolean boolValueOf = view6 != null ? Boolean.valueOf(view6.isCheck()) : null;
        if (boolValueOf == null) {
            Intrinsics.throwNpe();
        }
        if (boolValueOf.booleanValue()) {
            if (BLEManager.isBind() && BLEManager.isConnected()) {
                CommonLogUtil.printAndSave("设备已经连接，开始获取设备的Flash日志。");
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                File file = new File(logPathImpl.getFlashPath());
                if (!file.exists()) {
                    file.mkdirs();
                }
                StringBuilder sb = new StringBuilder();
                LogPath logPathImpl2 = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
                sb.append(logPathImpl2.getFlashPath());
                sb.append(DateUtil.format(new Date(), "yyyy-MM-dd-HHmmss"));
                sb.append("_flash.log");
                File file2 = new File(sb.toString());
                if (file2.createNewFile()) {
                    BLEManager.collectDeviceFlashLog(file2.getAbsolutePath(), 60, new ICollectFlashLogListener() { // from class: com.ido.life.module.user.feedback.FeedbackPresenter.commitSuggest.1
                        @Override // com.ido.ble.firmware.log.flash.ICollectFlashLogListener
                        public void onStart() {
                            CommonLogUtil.printAndSave("开始收集Flash日志");
                        }

                        @Override // com.ido.ble.firmware.log.flash.ICollectFlashLogListener
                        public void onFinish() throws Throwable {
                            CommonLogUtil.printAndSave("Flash日志收集完成");
                            FeedbackPresenter.this.zipLogs();
                        }
                    });
                    return;
                } else {
                    CommonLogUtil.printAndSave("搜集Flash日志文件创建失败,直接压缩本地日志。");
                    zipLogs();
                    return;
                }
            }
            CommonLogUtil.printAndSave("设备未连接，不搜集Flash日志，开始压缩本地日志。");
            zipLogs();
            return;
        }
        toCreateFeedback("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zipLogs() throws Throwable {
        try {
            File file = new File(this.mLogZipFilePath);
            if (file.exists()) {
                file.delete();
            }
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            File file2 = new File(logPathImpl.getAlexaLogPath());
            if (file2.exists()) {
                file2.delete();
            }
            AlexaLogPath alexaLogPathImpl = AlexaLogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(alexaLogPathImpl, "AlexaLogPathImpl.getInstance()");
            String alexaPath = alexaLogPathImpl.getAlexaPath();
            LogPath logPathImpl2 = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
            FileUtil.copyDirectiory(alexaPath, logPathImpl2.getAlexaLogPath());
            LogPath logPathImpl3 = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl3, "LogPathImpl.getInstance()");
            FileUtil.zipFolder(logPathImpl3.getRootPath(), this.mLogZipFilePath);
            AccountRepository.getInstance().updateFileFeedback(this.mLogZipFilePath, new OnResultCallback() { // from class: com.ido.life.module.user.feedback.FeedbackPresenter.zipLogs.1
                @Override // com.ido.life.data.listener.OnResultCallback
                public void onSuccess(Result<?> result) {
                    Intrinsics.checkParameterIsNotNull(result, "result");
                    Object data = result.getData();
                    if (data == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                    }
                    FeedbackPresenter.this.toCreateFeedback((String) data);
                }

                @Override // com.ido.life.data.listener.OnResultCallback
                public void onFailed(String message) {
                    Intrinsics.checkParameterIsNotNull(message, "message");
                    IFeedbackView iFeedbackViewAccess$getView = FeedbackPresenter.access$getView(FeedbackPresenter.this);
                    if (iFeedbackViewAccess$getView != null) {
                        iFeedbackViewAccess$getView.dismissLoading();
                    }
                    FeedbackPresenter.this.toCreateFeedback("");
                }
            });
        } catch (Exception e2) {
            IFeedbackView view = getView();
            if (view != null) {
                view.dismissLoading();
            }
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toCreateFeedback(String linkUrl) {
        if (getView() == null) {
            return;
        }
        Feedback feedback = new Feedback();
        IFeedbackView view = getView();
        Integer numValueOf = view != null ? Integer.valueOf(view.getMFeedbackType()) : null;
        if (numValueOf == null) {
            Intrinsics.throwNpe();
        }
        feedback.setTypeId(numValueOf.intValue());
        IFeedbackView view2 = getView();
        feedback.setBody(view2 != null ? view2.getDes() : null);
        IFeedbackView view3 = getView();
        List<String> picList = view3 != null ? view3.getPicList() : null;
        if (picList == null) {
            Intrinsics.throwNpe();
        }
        int size = picList.size();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                feedback.setImage1Url(picList.get(0));
            } else if (i == 1) {
                feedback.setImage2Url(picList.get(1));
            } else if (i == 2) {
                feedback.setImage3Url(picList.get(2));
            }
        }
        IFeedbackView view4 = getView();
        feedback.setContact(view4 != null ? view4.getContract() : null);
        feedback.setLinkUrl(linkUrl);
        AccountRepository.getInstance().toCreateFeedback(feedback, new OnResultCallback() { // from class: com.ido.life.module.user.feedback.FeedbackPresenter.toCreateFeedback.1
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result<?> result) {
                IFeedbackView iFeedbackViewAccess$getView = FeedbackPresenter.access$getView(FeedbackPresenter.this);
                if (iFeedbackViewAccess$getView != null) {
                    iFeedbackViewAccess$getView.commitFeedBackSuccess();
                }
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String message) {
                Intrinsics.checkParameterIsNotNull(message, "message");
                IFeedbackView iFeedbackViewAccess$getView = FeedbackPresenter.access$getView(FeedbackPresenter.this);
                if (iFeedbackViewAccess$getView != null) {
                    iFeedbackViewAccess$getView.dismissLoading();
                }
                IFeedbackView iFeedbackViewAccess$getView2 = FeedbackPresenter.access$getView(FeedbackPresenter.this);
                if (iFeedbackViewAccess$getView2 != null) {
                    iFeedbackViewAccess$getView2.showError(LanguageUtil.getLanguageText(R.string.feedback_commit_failed));
                }
            }
        });
    }

    public final void verify() {
        if (getView() == null) {
            return;
        }
        IFeedbackView view = getView();
        if (!TextUtils.isEmpty(view != null ? view.getDes() : null)) {
            IFeedbackView view2 = getView();
            Integer numValueOf = view2 != null ? Integer.valueOf(view2.getMFeedbackType()) : null;
            if (numValueOf == null) {
                Intrinsics.throwNpe();
            }
            if (numValueOf.intValue() > 0) {
                IFeedbackView view3 = getView();
                if (view3 != null) {
                    view3.setSubmitBg(R.drawable.shape_rectangle_bg_theme_10_corner);
                    return;
                }
                return;
            }
        }
        IFeedbackView view4 = getView();
        if (view4 != null) {
            view4.setSubmitBg(R.drawable.shape_feedbac_rectangle_bg_gray_10_corner);
        }
    }

    public final void updateImage(final String filePath) {
        IFeedbackView view = getView();
        if (view != null) {
            view.showLoading();
        }
        AccountRepository.getInstance().updateFileFeedback(filePath, new OnResultCallback() { // from class: com.ido.life.module.user.feedback.FeedbackPresenter.updateImage.1
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result<?> result) {
                Intrinsics.checkParameterIsNotNull(result, "result");
                IFeedbackView iFeedbackViewAccess$getView = FeedbackPresenter.access$getView(FeedbackPresenter.this);
                if (iFeedbackViewAccess$getView != null) {
                    iFeedbackViewAccess$getView.dismissLoading();
                }
                Object data = result.getData();
                if (data == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                String str = (String) data;
                if (TextUtils.isEmpty(filePath)) {
                    IFeedbackView iFeedbackViewAccess$getView2 = FeedbackPresenter.access$getView(FeedbackPresenter.this);
                    if (iFeedbackViewAccess$getView2 != null) {
                        iFeedbackViewAccess$getView2.notifyAdapter(str);
                        return;
                    }
                    return;
                }
                IFeedbackView iFeedbackViewAccess$getView3 = FeedbackPresenter.access$getView(FeedbackPresenter.this);
                if (iFeedbackViewAccess$getView3 != null) {
                    iFeedbackViewAccess$getView3.notifyAdapter(filePath);
                }
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String message) {
                Intrinsics.checkParameterIsNotNull(message, "message");
                IFeedbackView iFeedbackViewAccess$getView = FeedbackPresenter.access$getView(FeedbackPresenter.this);
                if (iFeedbackViewAccess$getView != null) {
                    iFeedbackViewAccess$getView.dismissLoading();
                }
                IFeedbackView iFeedbackViewAccess$getView2 = FeedbackPresenter.access$getView(FeedbackPresenter.this);
                if (iFeedbackViewAccess$getView2 != null) {
                    iFeedbackViewAccess$getView2.showError(message);
                }
            }
        });
    }
}