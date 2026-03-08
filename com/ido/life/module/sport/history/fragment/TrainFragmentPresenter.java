package com.ido.life.module.sport.history.fragment;

import android.graphics.Color;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.customview.TrainingEffectProgressView;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.UserInfo;
import com.ido.life.log.SportLogHelper;
import com.ido.life.module.sport.bean.OxyGenProgressBean;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrainFragmentPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 *2\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001:\u0001*B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\fJ\u000e\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u000e\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cJ\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 J\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050 J\f\u0010#\u001a\b\u0012\u0004\u0012\u00020$0 J\f\u0010%\u001a\b\u0012\u0004\u0012\u00020!0 J\f\u0010&\u001a\b\u0012\u0004\u0012\u00020$0 J\u000e\u0010'\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cJ\u001e\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006+"}, d2 = {"Lcom/ido/life/module/sport/history/fragment/TrainFragmentPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/sport/history/fragment/ITrainView;", "()V", "ageIn", "", "getAgeIn", "()Ljava/lang/String;", "setAgeIn", "(Ljava/lang/String;)V", "mListOxygen", "", "", "getMListOxygen", "()Ljava/util/List;", "setMListOxygen", "(Ljava/util/List;)V", "mPosition", "getMPosition", "()I", "setMPosition", "(I)V", "OxygenProgress", "", "vo2max", "gender", "actionSportHealthData", "sportHealth", "Lcom/ido/life/database/model/SportHealth;", "coverTimeAction", "effectAction", "getBoyOxygenDataList", "", "Lcom/ido/life/module/sport/bean/OxyGenProgressBean;", "getEffectList", "getEffectPorgressList", "Lcom/ido/life/customview/TrainingEffectProgressView$DividerProperty;", "getGirlOxygenDataList", "getOxygenList", "oxygenAction", "oxygenDetail", "position", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class TrainFragmentPresenter extends BasePresenter<ITrainView> {
    private static final String TAG = "TrainFragmentPresenter";
    private String ageIn = "";
    private List<Integer> mListOxygen = CollectionsKt.emptyList();
    private int mPosition;

    public final String getAgeIn() {
        return this.ageIn;
    }

    public final void setAgeIn(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.ageIn = str;
    }

    public final List<Integer> getMListOxygen() {
        return this.mListOxygen;
    }

    public final void setMListOxygen(List<Integer> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.mListOxygen = list;
    }

    public final int getMPosition() {
        return this.mPosition;
    }

    public final void setMPosition(int i) {
        this.mPosition = i;
    }

    public final void actionSportHealthData(SportHealth sportHealth) {
        Intrinsics.checkParameterIsNotNull(sportHealth, "sportHealth");
        if (sportHealth.getType() == 48 || sportHealth.getType() == 49) {
            ITrainView view = getView();
            if (view != null) {
                view.setOxygenVisible(true);
            }
            effectAction(sportHealth);
            oxygenAction(sportHealth);
            coverTimeAction(sportHealth);
            return;
        }
        ITrainView view2 = getView();
        if (view2 != null) {
            view2.setOxygenVisible(false);
        }
        effectAction(sportHealth);
        coverTimeAction(sportHealth);
    }

    public final void effectAction(SportHealth sportHealth) {
        Intrinsics.checkParameterIsNotNull(sportHealth, "sportHealth");
        float trainingEffectScore = sportHealth.getTrainingEffectScore();
        int i = 5;
        if (trainingEffectScore >= 5) {
            i = 6;
            ITrainView view = getView();
            if (view != null) {
                String languageText = LanguageUtil.getLanguageText(R.string.sport_train_effect_tip_5);
                Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…sport_train_effect_tip_5)");
                view.showEffectTip(languageText);
            }
        } else if (trainingEffectScore >= 4) {
            ITrainView view2 = getView();
            if (view2 != null) {
                String languageText2 = LanguageUtil.getLanguageText(R.string.sport_train_effect_tip_4);
                Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…sport_train_effect_tip_4)");
                view2.showEffectTip(languageText2);
            }
        } else if (trainingEffectScore >= 3) {
            ITrainView view3 = getView();
            if (view3 != null) {
                String languageText3 = LanguageUtil.getLanguageText(R.string.sport_train_effect_tip_3);
                Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…sport_train_effect_tip_3)");
                view3.showEffectTip(languageText3);
            }
            i = 4;
        } else if (trainingEffectScore >= 2) {
            ITrainView view4 = getView();
            if (view4 != null) {
                String languageText4 = LanguageUtil.getLanguageText(R.string.sport_train_effect_tip_2);
                Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…sport_train_effect_tip_2)");
                view4.showEffectTip(languageText4);
            }
            i = 3;
        } else if (trainingEffectScore >= 1) {
            ITrainView view5 = getView();
            if (view5 != null) {
                String languageText5 = LanguageUtil.getLanguageText(R.string.sport_train_effect_tip_1);
                Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…sport_train_effect_tip_1)");
                view5.showEffectTip(languageText5);
            }
            i = 2;
        } else if (trainingEffectScore == 0.0f) {
            i = 0;
            ITrainView view6 = getView();
            if (view6 != null) {
                String languageText6 = LanguageUtil.getLanguageText(R.string.sport_detail_train_effect_no_tip);
                Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…tail_train_effect_no_tip)");
                view6.showEffectTip(languageText6);
            }
        } else {
            ITrainView view7 = getView();
            if (view7 != null) {
                String languageText7 = LanguageUtil.getLanguageText(R.string.sport_detail_train_effect_one_tip);
                Intrinsics.checkExpressionValueIsNotNull(languageText7, "LanguageUtil.getLanguage…ail_train_effect_one_tip)");
                view7.showEffectTip(languageText7);
            }
            i = 1;
        }
        ITrainView view8 = getView();
        if (view8 != null) {
            view8.showEffectProgress(i);
        }
        if (trainingEffectScore < 1) {
            ITrainView view9 = getView();
            if (view9 != null) {
                view9.showEffectText("--");
            }
            ITrainView view10 = getView();
            if (view10 != null) {
                view10.showEffectColor(ResourceUtil.getColor(R.color.color_82868F));
                return;
            }
            return;
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        ITrainView view11 = getView();
        if (view11 != null) {
            String str = decimalFormat.format(Float.valueOf(trainingEffectScore));
            Intrinsics.checkExpressionValueIsNotNull(str, "dcf.format(effectScore)");
            view11.showEffectText(str);
        }
    }

    public final List<String> getEffectList() {
        ArrayList arrayList = new ArrayList();
        String languageText = LanguageUtil.getLanguageText(R.string.sport_detail_train_effect_no);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…t_detail_train_effect_no)");
        arrayList.add(languageText);
        String languageText2 = LanguageUtil.getLanguageText(R.string.sport_detail_train_effect_one);
        Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…_detail_train_effect_one)");
        arrayList.add(languageText2);
        String languageText3 = LanguageUtil.getLanguageText(R.string.sport_detail_train_effect_two);
        Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…_detail_train_effect_two)");
        arrayList.add(languageText3);
        String languageText4 = LanguageUtil.getLanguageText(R.string.sport_detail_train_effect_three);
        Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…etail_train_effect_three)");
        arrayList.add(languageText4);
        String languageText5 = LanguageUtil.getLanguageText(R.string.ssport_detail_train_effect_four);
        Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…detail_train_effect_four)");
        arrayList.add(languageText5);
        String languageText6 = LanguageUtil.getLanguageText(R.string.sport_detail_train_effect_five);
        Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…detail_train_effect_five)");
        arrayList.add(languageText6);
        String languageText7 = LanguageUtil.getLanguageText(R.string.sport_detail_train_effect_six);
        Intrinsics.checkExpressionValueIsNotNull(languageText7, "LanguageUtil.getLanguage…_detail_train_effect_six)");
        arrayList.add(languageText7);
        return arrayList;
    }

    public final List<TrainingEffectProgressView.DividerProperty> getEffectPorgressList() {
        ArrayList arrayList = new ArrayList();
        int color = Color.parseColor("#10DFD0");
        float dimens = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens2 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText = LanguageUtil.getLanguageText(R.string.sport_detail_train_effect_two);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…_detail_train_effect_two)");
        arrayList.add(new TrainingEffectProgressView.DividerProperty(color, dimens, dimens2, languageText));
        int color2 = Color.parseColor("#05CE11");
        float dimens3 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens4 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText2 = LanguageUtil.getLanguageText(R.string.sport_detail_train_effect_three);
        Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…etail_train_effect_three)");
        arrayList.add(new TrainingEffectProgressView.DividerProperty(color2, dimens3, dimens4, languageText2));
        int color3 = Color.parseColor("#FFCA18");
        float dimens5 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens6 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText3 = LanguageUtil.getLanguageText(R.string.ssport_detail_train_effect_four);
        Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…detail_train_effect_four)");
        arrayList.add(new TrainingEffectProgressView.DividerProperty(color3, dimens5, dimens6, languageText3));
        int color4 = Color.parseColor("#FF7121");
        float dimens7 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens8 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText4 = LanguageUtil.getLanguageText(R.string.sport_detail_train_effect_five);
        Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…detail_train_effect_five)");
        arrayList.add(new TrainingEffectProgressView.DividerProperty(color4, dimens7, dimens8, languageText4));
        int color5 = Color.parseColor("#F91254");
        float dimens9 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens10 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText5 = LanguageUtil.getLanguageText(R.string.sport_detail_train_effect_six);
        Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…_detail_train_effect_six)");
        arrayList.add(new TrainingEffectProgressView.DividerProperty(color5, dimens9, dimens10, languageText5));
        return arrayList;
    }

    public final void oxygenAction(SportHealth sportHealth) {
        int ageByBirthday;
        Intrinsics.checkParameterIsNotNull(sportHealth, "sportHealth");
        if (sportHealth.getVo2max() == 0) {
            ITrainView view = getView();
            if (view != null) {
                view.showOxygenText("-");
            }
            ITrainView view2 = getView();
            if (view2 != null) {
                view2.showOxygenTextColor(ResourceUtil.getColor(R.color.color_82868F));
            }
        } else {
            ITrainView view3 = getView();
            if (view3 != null) {
                view3.showOxygenText(String.valueOf(sportHealth.getVo2max()));
            }
        }
        Long userId = sportHealth.getUserId();
        Intrinsics.checkExpressionValueIsNotNull(userId, "sportHealth.userId");
        UserInfo userInfo = GreenDaoUtil.queryUserInfo(userId.longValue());
        try {
            Intrinsics.checkExpressionValueIsNotNull(userInfo, "userInfo");
            ageByBirthday = DateUtil.getAgeByBirthday(userInfo.getBirthday(), DateUtil.DATE_FORMAT_YMD);
        } catch (Exception e2) {
            e2.printStackTrace();
            SportLogHelper.saveSportLog(TAG, "年龄异常");
            ageByBirthday = 30;
        }
        if (ageByBirthday >= 0 && 29 >= ageByBirthday) {
            Intrinsics.checkExpressionValueIsNotNull(userInfo, "userInfo");
            oxygenDetail(0, userInfo.getGender(), sportHealth.getVo2max());
            this.ageIn = "20 - 29";
            return;
        }
        if (30 <= ageByBirthday && 39 >= ageByBirthday) {
            Intrinsics.checkExpressionValueIsNotNull(userInfo, "userInfo");
            oxygenDetail(1, userInfo.getGender(), sportHealth.getVo2max());
            this.ageIn = "30 - 39";
            return;
        }
        if (40 <= ageByBirthday && 49 >= ageByBirthday) {
            Intrinsics.checkExpressionValueIsNotNull(userInfo, "userInfo");
            oxygenDetail(2, userInfo.getGender(), sportHealth.getVo2max());
            this.ageIn = "40 - 49";
            return;
        }
        if (50 <= ageByBirthday && 59 >= ageByBirthday) {
            Intrinsics.checkExpressionValueIsNotNull(userInfo, "userInfo");
            oxygenDetail(3, userInfo.getGender(), sportHealth.getVo2max());
            this.ageIn = "50 - 59";
        } else if (60 <= ageByBirthday && 69 >= ageByBirthday) {
            Intrinsics.checkExpressionValueIsNotNull(userInfo, "userInfo");
            oxygenDetail(4, userInfo.getGender(), sportHealth.getVo2max());
            this.ageIn = "60 - 69";
        } else if (70 <= ageByBirthday && 1000 >= ageByBirthday) {
            Intrinsics.checkExpressionValueIsNotNull(userInfo, "userInfo");
            oxygenDetail(5, userInfo.getGender(), sportHealth.getVo2max());
            this.ageIn = "70 - 79";
        }
    }

    public final void oxygenDetail(int position, int gender, int vo2max) {
        if (gender == 1) {
            List<Integer> list_oxygen = getBoyOxygenDataList().get(position).getList_oxygen();
            Intrinsics.checkExpressionValueIsNotNull(list_oxygen, "getBoyOxygenDataList().get(position).list_oxygen");
            this.mListOxygen = list_oxygen;
            OxygenProgress(vo2max, gender);
            return;
        }
        if (gender != 2) {
            return;
        }
        List<Integer> list_oxygen2 = getGirlOxygenDataList().get(position).getList_oxygen();
        Intrinsics.checkExpressionValueIsNotNull(list_oxygen2, "getGirlOxygenDataList().get(position).list_oxygen");
        this.mListOxygen = list_oxygen2;
        OxygenProgress(vo2max, gender);
    }

    public final void OxygenProgress(int vo2max, int gender) {
        ITrainView view;
        ITrainView view2;
        Iterator<T> it = this.mListOxygen.iterator();
        int i = 0;
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            if (vo2max <= iIntValue || (i == CollectionsKt.getLastIndex(this.mListOxygen) && vo2max > iIntValue)) {
                this.mPosition = i - 1;
                break;
            }
            i++;
        }
        if (this.mPosition == -1) {
            this.mPosition = 0;
        }
        if (vo2max == 0) {
            ITrainView view3 = getView();
            if (view3 != null) {
                String languageText = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_man_tip_no);
                Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…_oxygen_level_man_tip_no)");
                view3.showOxygenTip(languageText);
            }
            this.mPosition = -1;
            ITrainView view4 = getView();
            if (view4 != null) {
                view4.showOxygenProgress(1, this.mPosition);
                return;
            }
            return;
        }
        if (gender == 1) {
            int i2 = this.mPosition;
            if (i2 == 0) {
                ITrainView view5 = getView();
                if (view5 != null) {
                    String languageText2 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_man_tip_one);
                    Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…oxygen_level_man_tip_one)");
                    view5.showOxygenTip(languageText2);
                }
            } else if (i2 == 1) {
                ITrainView view6 = getView();
                if (view6 != null) {
                    String languageText3 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_man_tip_two);
                    Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…oxygen_level_man_tip_two)");
                    view6.showOxygenTip(languageText3);
                }
            } else if (i2 == 2) {
                ITrainView view7 = getView();
                if (view7 != null) {
                    String languageText4 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_man_ip_three);
                    Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…xygen_level_man_ip_three)");
                    view7.showOxygenTip(languageText4);
                }
            } else if (i2 == 3) {
                ITrainView view8 = getView();
                if (view8 != null) {
                    String languageText5 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_man_tip_four);
                    Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…xygen_level_man_tip_four)");
                    view8.showOxygenTip(languageText5);
                }
            } else if (i2 == 4) {
                ITrainView view9 = getView();
                if (view9 != null) {
                    String languageText6 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_man_tip_five);
                    Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…xygen_level_man_tip_five)");
                    view9.showOxygenTip(languageText6);
                }
            } else if (i2 == 5 && (view2 = getView()) != null) {
                String languageText7 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_man_tip_six);
                Intrinsics.checkExpressionValueIsNotNull(languageText7, "LanguageUtil.getLanguage…oxygen_level_man_tip_six)");
                view2.showOxygenTip(languageText7);
            }
        } else {
            int i3 = this.mPosition;
            if (i3 == 0) {
                ITrainView view10 = getView();
                if (view10 != null) {
                    String languageText8 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_woman_tip_one);
                    Intrinsics.checkExpressionValueIsNotNull(languageText8, "LanguageUtil.getLanguage…ygen_level_woman_tip_one)");
                    view10.showOxygenTip(languageText8);
                }
            } else if (i3 == 1) {
                ITrainView view11 = getView();
                if (view11 != null) {
                    String languageText9 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_woman_tip_two);
                    Intrinsics.checkExpressionValueIsNotNull(languageText9, "LanguageUtil.getLanguage…ygen_level_woman_tip_two)");
                    view11.showOxygenTip(languageText9);
                }
            } else if (i3 == 2) {
                ITrainView view12 = getView();
                if (view12 != null) {
                    String languageText10 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_woman_tip_three);
                    Intrinsics.checkExpressionValueIsNotNull(languageText10, "LanguageUtil.getLanguage…en_level_woman_tip_three)");
                    view12.showOxygenTip(languageText10);
                }
            } else if (i3 == 3) {
                ITrainView view13 = getView();
                if (view13 != null) {
                    String languageText11 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_woman_tip_four);
                    Intrinsics.checkExpressionValueIsNotNull(languageText11, "LanguageUtil.getLanguage…gen_level_woman_tip_four)");
                    view13.showOxygenTip(languageText11);
                }
            } else if (i3 == 4) {
                ITrainView view14 = getView();
                if (view14 != null) {
                    String languageText12 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_woman_tip_five);
                    Intrinsics.checkExpressionValueIsNotNull(languageText12, "LanguageUtil.getLanguage…gen_level_woman_tip_five)");
                    view14.showOxygenTip(languageText12);
                }
            } else if (i3 == 5 && (view = getView()) != null) {
                String languageText13 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_woman_tip_six);
                Intrinsics.checkExpressionValueIsNotNull(languageText13, "LanguageUtil.getLanguage…ygen_level_woman_tip_six)");
                view.showOxygenTip(languageText13);
            }
        }
        ITrainView view15 = getView();
        if (view15 != null) {
            view15.showOxygenProgress(2, this.mPosition);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void coverTimeAction(com.ido.life.database.model.SportHealth r23) {
        /*
            Method dump skipped, instruction units count: 568
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.sport.history.fragment.TrainFragmentPresenter.coverTimeAction(com.ido.life.database.model.SportHealth):void");
    }

    public final List<OxyGenProgressBean> getBoyOxygenDataList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OxyGenProgressBean(1, 20, 29, CollectionsKt.listOf((Object[]) new Integer[]{26, 37, 41, 44, 50, 54, 70})));
        arrayList.add(new OxyGenProgressBean(1, 30, 39, CollectionsKt.listOf((Object[]) new Integer[]{26, 35, 39, 43, 47, 53, 58})));
        arrayList.add(new OxyGenProgressBean(1, 40, 49, CollectionsKt.listOf((Object[]) new Integer[]{25, 33, 37, 41, 45, 51, 56})));
        arrayList.add(new OxyGenProgressBean(1, 50, 59, CollectionsKt.listOf((Object[]) new Integer[]{22, 31, 34, 38, 42, 48, 54})));
        arrayList.add(new OxyGenProgressBean(1, 60, 69, CollectionsKt.listOf((Object[]) new Integer[]{19, 27, 31, 34, 38, 44, 51})));
        arrayList.add(new OxyGenProgressBean(1, 70, 79, CollectionsKt.listOf((Object[]) new Integer[]{18, 24, 28, 31, 35, 42, 49})));
        return arrayList;
    }

    public final List<OxyGenProgressBean> getGirlOxygenDataList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OxyGenProgressBean(2, 20, 29, CollectionsKt.listOf((Object[]) new Integer[]{23, 31, 35, 38, 42, 48, 54})));
        arrayList.add(new OxyGenProgressBean(2, 30, 39, CollectionsKt.listOf((Object[]) new Integer[]{22, 29, 33, 36, 41, 46, 52})));
        arrayList.add(new OxyGenProgressBean(2, 40, 49, CollectionsKt.listOf((Object[]) new Integer[]{22, 28, 32, 35, 38, 44, 51})));
        arrayList.add(new OxyGenProgressBean(2, 50, 59, CollectionsKt.listOf((Object[]) new Integer[]{20, 25, 29, 32, 35, 40, 46})));
        arrayList.add(new OxyGenProgressBean(2, 60, 69, CollectionsKt.listOf((Object[]) new Integer[]{19, 23, 26, 29, 32, 36, 42})));
        arrayList.add(new OxyGenProgressBean(2, 70, 79, CollectionsKt.listOf((Object[]) new Integer[]{16, 22, 25, 28, 30, 36, 42})));
        return arrayList;
    }

    public final List<TrainingEffectProgressView.DividerProperty> getOxygenList() {
        ArrayList arrayList = new ArrayList();
        int color = Color.parseColor("#E64C8B");
        float dimens = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens2 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_one);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…l_train_oxygen_level_one)");
        arrayList.add(new TrainingEffectProgressView.DividerProperty(color, dimens, dimens2, languageText));
        int color2 = Color.parseColor("#9067F2");
        float dimens3 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens4 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText2 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_two);
        Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…l_train_oxygen_level_two)");
        arrayList.add(new TrainingEffectProgressView.DividerProperty(color2, dimens3, dimens4, languageText2));
        int color3 = Color.parseColor("#598EFF");
        float dimens5 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens6 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText3 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_three);
        Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…train_oxygen_level_three)");
        arrayList.add(new TrainingEffectProgressView.DividerProperty(color3, dimens5, dimens6, languageText3));
        int color4 = Color.parseColor("#01B3FE");
        float dimens7 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens8 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText4 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_four);
        Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…_train_oxygen_level_four)");
        arrayList.add(new TrainingEffectProgressView.DividerProperty(color4, dimens7, dimens8, languageText4));
        int color5 = Color.parseColor("#01CEFE");
        float dimens9 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens10 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText5 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_five);
        Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…_train_oxygen_level_five)");
        arrayList.add(new TrainingEffectProgressView.DividerProperty(color5, dimens9, dimens10, languageText5));
        int color6 = Color.parseColor("#00F2FF");
        float dimens11 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens12 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText6 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_six);
        Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…l_train_oxygen_level_six)");
        arrayList.add(new TrainingEffectProgressView.DividerProperty(color6, dimens11, dimens12, languageText6));
        return arrayList;
    }
}