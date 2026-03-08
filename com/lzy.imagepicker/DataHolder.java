package com.lzy.imagepicker;

import com.lzy.imagepicker.bean.ImageItem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class DataHolder {
    public static final String DH_CURRENT_IMAGE_FOLDER_ITEMS = "dh_current_image_folder_items";
    private static DataHolder mInstance;
    private Map<String, List<ImageItem>> data = new HashMap();

    public static DataHolder getInstance() {
        if (mInstance == null) {
            synchronized (DataHolder.class) {
                if (mInstance == null) {
                    mInstance = new DataHolder();
                }
            }
        }
        return mInstance;
    }

    private DataHolder() {
    }

    public void save(String str, List<ImageItem> list) {
        Map<String, List<ImageItem>> map = this.data;
        if (map != null) {
            map.put(str, list);
        }
    }

    public Object retrieve(String str) {
        Map<String, List<ImageItem>> map = this.data;
        if (map == null || mInstance == null) {
            throw new RuntimeException("你必须先初始化");
        }
        return map.get(str);
    }
}