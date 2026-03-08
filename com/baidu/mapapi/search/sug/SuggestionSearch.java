package com.baidu.mapapi.search.sug;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.l;

/* JADX INFO: loaded from: classes.dex */
public class SuggestionSearch extends l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3308b = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    com.baidu.platform.core.f.a f3307a = new com.baidu.platform.core.f.b();

    private SuggestionSearch() {
    }

    public static SuggestionSearch newInstance() {
        BMapManager.init();
        return new SuggestionSearch();
    }

    public void destroy() {
        if (this.f3308b) {
            return;
        }
        this.f3308b = true;
        this.f3307a.a();
        BMapManager.destroy();
    }

    public boolean requestSuggestion(SuggestionSearchOption suggestionSearchOption) {
        if (this.f3307a == null) {
            throw new IllegalStateException("BDMapSDKException: suggestionsearch is null, please call newInstance() first.");
        }
        if (suggestionSearchOption == null || suggestionSearchOption.mKeyword == null || suggestionSearchOption.mCity == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or keyword or city can not be null");
        }
        return this.f3307a.a(suggestionSearchOption);
    }

    public void setOnGetSuggestionResultListener(OnGetSuggestionResultListener onGetSuggestionResultListener) {
        com.baidu.platform.core.f.a aVar = this.f3307a;
        if (aVar == null) {
            throw new IllegalStateException("BDMapSDKException: suggestionsearch is null, please call newInstance() first.");
        }
        if (onGetSuggestionResultListener == null) {
            throw new IllegalArgumentException("BDMapSDKException: listener can not be null");
        }
        aVar.a(onGetSuggestionResultListener);
    }
}