package com.ido.life.module.user.sportrecord;

import com.ido.life.base.IBaseView;
import com.ido.life.module.user.sportrecord.model.SportGroupItem;
import com.ido.life.module.user.sportrecord.model.SportScreenType;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface ISportRecordView extends IBaseView {
    void hideEmpty();

    void hideLoading();

    void setEmptyDrawable(int i);

    void setEmptyText(String str);

    void setSportScreenType(List<SportScreenType> list);

    void showEmpty();

    void showLoading();

    void showMessage(String str);

    void showSportRecord(List<SportGroupItem> list, List<Integer> list2);

    void toIndoorRun(int i, String str);

    void toSportDetail(int i, String str);

    void toSportTravel(int i, String str);

    void toSwim(int i, String str);
}