package com.ido.life.module.sport.ready;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Handler;
import com.boat.Xtend.two.R;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.log.SportLogHelper;
import com.ido.life.module.sport.SportRunManager;
import com.ido.life.module.sport.ready.SportRunReadyContract;

/* JADX INFO: loaded from: classes2.dex */
public class SportRunReadyPresenter implements SportRunReadyContract.Presenter {
    private static final String TAG = "SportRunReadyPresenter";
    private MediaPlayer mMediaPlayer;
    private SportRunReadyContract.View mView;
    private Handler handler = new Handler();
    private Runnable mediaPlayerRunnable = new Runnable() { // from class: com.ido.life.module.sport.ready.SportRunReadyPresenter.2
        @Override // java.lang.Runnable
        public void run() {
            if (SportRunReadyPresenter.this.mMediaPlayer != null) {
                SportRunReadyPresenter.this.mMediaPlayer.stop();
                SportRunReadyPresenter.this.mMediaPlayer.release();
                SportRunReadyPresenter.this.mMediaPlayer = null;
            }
        }
    };
    private SportRunManager mSportRunManager = SportRunManager.getInstance();

    @Override // com.ido.life.module.sport.ready.SportRunReadyContract.Presenter
    public void clearListener() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public SportRunReadyPresenter(SportRunReadyContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.sport.ready.SportRunReadyContract.Presenter
    public void startRun(int i, boolean z) {
        this.mSportRunManager.startRun(i, z);
    }

    @Override // com.ido.life.module.sport.ready.SportRunReadyContract.Presenter
    public void setOnStartListener() {
        this.mSportRunManager.setSportStartCallback(new SportRunManager.ISportStartCallBack() { // from class: com.ido.life.module.sport.ready.SportRunReadyPresenter.1
            @Override // com.ido.life.module.sport.SportRunManager.ISportStartCallBack
            public void sportStartSuccess() {
                if (SportRunReadyPresenter.this.mView == null) {
                    return;
                }
                SportRunReadyPresenter.this.mView.showSportStartSuccess();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), SportRunReadyPresenter.TAG, "sportStartSuccess: ");
            }

            @Override // com.ido.life.module.sport.SportRunManager.ISportStartCallBack
            public void sportStartFailedByLowPower() {
                if (SportRunReadyPresenter.this.mView == null) {
                    return;
                }
                SportRunReadyPresenter.this.mView.showSportStartFailedLowPower();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), SportRunReadyPresenter.TAG, "sportStartFaildByLowPower: ");
            }

            @Override // com.ido.life.module.sport.SportRunManager.ISportStartCallBack
            public void sportStartFailed() {
                if (SportRunReadyPresenter.this.mView == null) {
                    return;
                }
                SportRunReadyPresenter.this.mView.showSportStartFail();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), SportRunReadyPresenter.TAG, "sportStartFaild: ");
            }

            @Override // com.ido.life.module.sport.SportRunManager.ISportStartCallBack
            public void sportChargePower() {
                if (SportRunReadyPresenter.this.mView == null) {
                    return;
                }
                SportRunReadyPresenter.this.mView.showSportStartFailedChargePower();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), SportRunReadyPresenter.TAG, "sportChagerPower: ");
            }

            @Override // com.ido.life.module.sport.SportRunManager.ISportStartCallBack
            public void sportRunInAlexa() {
                if (SportRunReadyPresenter.this.mView == null) {
                    return;
                }
                SportRunReadyPresenter.this.mView.showSportStartError(LanguageUtil.getLanguageText(R.string.sport_in_alexa_mode));
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), SportRunReadyPresenter.TAG, "sportRunInAlexa: ");
            }

            @Override // com.ido.life.module.sport.SportRunManager.ISportStartCallBack
            public void sportStartInCalling() {
                if (SportRunReadyPresenter.this.mView == null) {
                    return;
                }
                SportRunReadyPresenter.this.mView.showSportStartFailedInCalling();
                SportLogHelper.saveSportLog(SportRunReadyPresenter.TAG, "sportStartInCalling: ");
            }
        });
    }

    @Override // com.ido.life.module.sport.ready.SportRunReadyContract.Presenter
    public void forceStartRun() {
        this.mSportRunManager.forceStartRun();
    }

    @Override // com.ido.life.module.sport.ready.SportRunReadyContract.Presenter
    public void playTipsMusic() {
        CommonLogUtil.d(TAG, "playTipsMusic: ");
        if (this.mMediaPlayer == null) {
            try {
                this.mMediaPlayer = MediaPlayer.create(IdoApp.getAppContext(), R.raw.sport_count_music);
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.setLooping(false);
                this.mMediaPlayer.start();
                this.handler.postDelayed(this.mediaPlayerRunnable, 4000L);
            } catch (Resources.NotFoundException e2) {
                e2.printStackTrace();
                CommonLogUtil.d(TAG, "playTipsMusic: " + e2.toString());
            }
        }
    }

    @Override // com.ido.life.module.sport.ready.SportRunReadyContract.Presenter
    public void forceEndRun() {
        if (this.mSportRunManager != null) {
            if (getFunction()) {
                this.mSportRunManager.endV3Cmd();
            } else {
                this.mSportRunManager.endCmd(0);
            }
        }
    }

    private boolean getFunction() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo != null) {
            return supportFunctionInfo.ex_table_main9_v3_activity_exchange_data;
        }
        return true;
    }

    @Override // com.ido.common.base.BasePresenter
    public void release() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.mediaPlayerRunnable);
        }
        this.mView = null;
    }
}