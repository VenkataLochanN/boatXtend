package com.baidu.platform.base;

import androidx.core.app.NotificationCompat;
import com.baidu.mapapi.search.core.SearchResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected SearchType f3902a;

    public abstract SearchResult a(String str);

    public SearchType a() {
        return this.f3902a;
    }

    public abstract void a(SearchResult searchResult, Object obj);

    public void a(SearchType searchType) {
        this.f3902a = searchType;
    }

    protected boolean a(String str, SearchResult searchResult, boolean z) {
        SearchResult.ERRORNO errorno;
        if (str != null) {
            try {
                if (str.length() > 0) {
                    int iOptInt = new JSONObject(str).optInt(z ? NotificationCompat.CATEGORY_STATUS : "status_sp");
                    if (iOptInt == 0) {
                        return false;
                    }
                    if (iOptInt != 200 && iOptInt != 230) {
                        switch (iOptInt) {
                            case 104:
                            case 105:
                            case 106:
                            case 107:
                            case 108:
                                errorno = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                                break;
                            default:
                                errorno = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                                break;
                        }
                    } else {
                        errorno = SearchResult.ERRORNO.KEY_ERROR;
                    }
                    searchResult.error = errorno;
                    return true;
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                searchResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                return true;
            }
        }
        searchResult.error = SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
        return true;
    }
}