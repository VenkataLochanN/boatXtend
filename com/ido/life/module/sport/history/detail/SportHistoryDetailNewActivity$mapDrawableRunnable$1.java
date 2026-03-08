package com.ido.life.module.sport.history.detail;

import android.widget.ImageView;
import com.ido.life.R;
import com.ido.life.customview.TrackPointView;
import com.ido.life.log.SportLogHelper;
import com.ido.life.module.sport.map.BaseMap;
import com.ido.life.module.sport.map.OnMapLoadedListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SportHistoryDetailNewActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
final class SportHistoryDetailNewActivity$mapDrawableRunnable$1 implements Runnable {
    final /* synthetic */ SportHistoryDetailNewActivity this$0;

    SportHistoryDetailNewActivity$mapDrawableRunnable$1(SportHistoryDetailNewActivity sportHistoryDetailNewActivity) {
        this.this$0 = sportHistoryDetailNewActivity;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x003b  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity$mapDrawableRunnable$1.run():void");
    }

    /* JADX INFO: renamed from: com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity$mapDrawableRunnable$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: SportHistoryDetailNewActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "onFinish"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass1 implements TrackPointView.OnTrailChangeListener {
        AnonymousClass1() {
        }

        @Override // com.ido.life.customview.TrackPointView.OnTrailChangeListener
        public final void onFinish() {
            if (SportHistoryDetailNewActivity.access$getMPresenter$p(SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0) == null || SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.getMapModel() == null) {
                return;
            }
            SportLogHelper.saveSportLog(SportHistoryDetailNewActivity.TAG, "run: 动态轨迹加载完成");
            if (SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.mFromType == 0) {
                ImageView iv_out_share = (ImageView) SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0._$_findCachedViewById(R.id.iv_out_share);
                Intrinsics.checkExpressionValueIsNotNull(iv_out_share, "iv_out_share");
                iv_out_share.setVisibility(8);
            } else {
                ImageView iv_out_share2 = (ImageView) SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0._$_findCachedViewById(R.id.iv_out_share);
                Intrinsics.checkExpressionValueIsNotNull(iv_out_share2, "iv_out_share");
                iv_out_share2.setVisibility(0);
            }
            if (SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.getMapModel() == null) {
                return;
            }
            BaseMap<?, ?> mapModel = SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.getMapModel();
            if (mapModel == null) {
                Intrinsics.throwNpe();
            }
            mapModel.setGesturesEnabled(true);
            BaseMap<?, ?> mapModel2 = SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.getMapModel();
            if (mapModel2 == null) {
                Intrinsics.throwNpe();
            }
            mapModel2.drawAllAndShot(1L, SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.mType, SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.mDuration, SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.mDistance, SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.mIsScreenMap, new BaseMap.IDrawFinish() { // from class: com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity.mapDrawableRunnable.1.1.1
                @Override // com.ido.life.module.sport.map.BaseMap.IDrawFinish
                public final void onDrawFinish() {
                    SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.mIsGoogleLoad = true;
                    if (((TrackPointView) SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0._$_findCachedViewById(R.id.track_point_view)) != null) {
                        ((TrackPointView) SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0._$_findCachedViewById(R.id.track_point_view)).setVisibility(8);
                    }
                    if (SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.mIsScreenMap) {
                        SportLogHelper.saveSportLog(SportHistoryDetailNewActivity.TAG, "run: 开始截图");
                        SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.saveAndUploadSportSmallPic(SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.mType, SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.mDuration, SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.mDistance);
                    }
                }
            });
            BaseMap<?, ?> mapModel3 = SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.getMapModel();
            if (mapModel3 == null) {
                Intrinsics.throwNpe();
            }
            mapModel3.getGoogleMap(new OnMapLoadedListener() { // from class: com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity.mapDrawableRunnable.1.1.2
                @Override // com.ido.life.module.sport.map.OnMapLoadedListener
                public final void onMapLoad(boolean z) {
                    if (!z || SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.mIsGoogleLoad) {
                        return;
                    }
                    BaseMap<?, ?> mapModel4 = SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.getMapModel();
                    if (mapModel4 == null) {
                        Intrinsics.throwNpe();
                    }
                    mapModel4.drawAllAndShot(1L, SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.mType, SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.mDuration, SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.mDistance, SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0.mIsScreenMap, new BaseMap.IDrawFinish() { // from class: com.ido.life.module.sport.history.detail.SportHistoryDetailNewActivity.mapDrawableRunnable.1.1.2.1
                        @Override // com.ido.life.module.sport.map.BaseMap.IDrawFinish
                        public final void onDrawFinish() {
                            if (((TrackPointView) SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0._$_findCachedViewById(R.id.track_point_view)) != null) {
                                ((TrackPointView) SportHistoryDetailNewActivity$mapDrawableRunnable$1.this.this$0._$_findCachedViewById(R.id.track_point_view)).setVisibility(8);
                            }
                        }
                    });
                }
            });
        }
    }
}