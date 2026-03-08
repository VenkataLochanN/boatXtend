package com.ido.life.module.device.view;

import com.ido.life.base.IBaseView;
import com.ido.life.bean.WorldTimeCity;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: IWorldTimeCityChooseView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0016\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J\b\u0010\b\u001a\u00020\u0003H&J\u0016\u0010\t\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J\b\u0010\n\u001a\u00020\u0003H&J,\u0010\u000b\u001a\u00020\u00032\"\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rj\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f`\u0010H&¨\u0006\u0011"}, d2 = {"Lcom/ido/life/module/device/view/IWorldTimeCityChooseView;", "Lcom/ido/life/base/IBaseView;", "onGetCityFailed", "", "onGetCitySuccess", "cities", "", "Lcom/ido/life/bean/WorldTimeCity;", "onSearchCityFailed", "onSearchCitySuccess", "onTakeCharacterFailed", "onTakeCharacterSuccess", "characters", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IWorldTimeCityChooseView extends IBaseView {
    void onGetCityFailed();

    void onGetCitySuccess(List<WorldTimeCity> cities);

    void onSearchCityFailed();

    void onSearchCitySuccess(List<WorldTimeCity> cities);

    void onTakeCharacterFailed();

    void onTakeCharacterSuccess(HashMap<String, Integer> characters);
}