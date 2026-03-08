package com.baidu.platform.core.f;

import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.baidu.platform.base.SearchType;

/* JADX INFO: loaded from: classes.dex */
public class b extends com.baidu.platform.base.a implements a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private OnGetSuggestionResultListener f3949b = null;

    @Override // com.baidu.platform.core.f.a
    public void a() {
        this.f3890a.lock();
        this.f3949b = null;
        this.f3890a.unlock();
    }

    @Override // com.baidu.platform.core.f.a
    public void a(OnGetSuggestionResultListener onGetSuggestionResultListener) {
        this.f3890a.lock();
        this.f3949b = onGetSuggestionResultListener;
        this.f3890a.unlock();
    }

    @Override // com.baidu.platform.core.f.a
    public boolean a(SuggestionSearchOption suggestionSearchOption) {
        c cVar = new c();
        cVar.a(SearchType.SUGGESTION_SEARCH_TYPE);
        return a(new d(suggestionSearchOption), this.f3949b, cVar);
    }
}