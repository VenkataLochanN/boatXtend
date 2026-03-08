package com.ido.life.module.sport.editcard;

import com.ido.life.bean.SortBean;
import com.ido.life.database.model.SportCard;
import com.ido.life.module.sport.editcard.EditCardContract;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class EditCardPresenter implements EditCardContract.Presenter {
    private static final String TAG = "EditCardPresenter";
    private EditCardContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public EditCardPresenter(EditCardContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.sport.editcard.EditCardContract.Presenter
    public List<SortBean> getItemList() {
        List<SortBean> cardTypeShowList = getCardTypeShowList();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (SortBean sortBean : cardTypeShowList) {
            sortBean.setNameId(SportCard.initTypeNameId(sortBean.getType()));
            if (sortBean.isSelected()) {
                arrayList2.add(sortBean);
            } else {
                arrayList3.add(sortBean);
            }
        }
        arrayList.addAll(0, arrayList2);
        arrayList.addAll(arrayList3);
        return arrayList;
    }

    @Override // com.ido.life.module.sport.editcard.EditCardContract.Presenter
    public void saveCardStatus(List<SortBean> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(Integer.valueOf(list.get(i).getType()));
            }
        }
        SportCard sportCardQuerySportCard = GreenDaoUtil.querySportCard(RunTimeUtil.getInstance().getUserId());
        if (sportCardQuerySportCard == null) {
            SportCard sportCard = new SportCard();
            sportCard.setUserId(RunTimeUtil.getInstance().getUserId());
            sportCard.setValueList(arrayList);
            sportCard.setUploadSuccess(false);
            long jCurrentTimeMillis = System.currentTimeMillis();
            sportCard.setCreateTime(jCurrentTimeMillis);
            sportCard.setUpdateTime(jCurrentTimeMillis);
            GreenDaoUtil.addSportCard(sportCard);
            return;
        }
        sportCardQuerySportCard.setValueList(arrayList);
        sportCardQuerySportCard.setUploadSuccess(false);
        sportCardQuerySportCard.update();
    }

    public List<SortBean> getCardTypeShowList() {
        boolean z;
        SportCard sportCardQuerySportCard = GreenDaoUtil.querySportCard(RunTimeUtil.getInstance().getUserId());
        ArrayList arrayList = new ArrayList();
        List<Integer> defaultSportCard = SportCard.getDefaultSportCard();
        int size = defaultSportCard.size();
        if (sportCardQuerySportCard == null) {
            for (int i = 0; i < size; i++) {
                arrayList.add(new SortBean(defaultSportCard.get(i).intValue(), true));
            }
        } else {
            int size2 = sportCardQuerySportCard.getValueList() == null ? 0 : sportCardQuerySportCard.getValueList().size();
            for (int i2 = 0; i2 < size2; i2++) {
                arrayList.add(new SortBean(sportCardQuerySportCard.getValueList().get(i2).intValue(), true));
            }
            if (size != size2) {
                for (int i3 = 0; i3 < size; i3++) {
                    int iIntValue = defaultSportCard.get(i3).intValue();
                    int i4 = 0;
                    while (true) {
                        if (i4 >= size2) {
                            z = false;
                            break;
                        }
                        if (iIntValue == sportCardQuerySportCard.getValueList().get(i4).intValue()) {
                            z = true;
                            break;
                        }
                        i4++;
                    }
                    if (!z) {
                        arrayList.add(new SortBean(iIntValue, false));
                    }
                }
            }
        }
        return arrayList;
    }
}