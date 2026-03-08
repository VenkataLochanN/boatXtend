package com.ido.life.module.user.sportrecord.model;

import com.ido.life.module.user.sportrecord.adapter.ExpandableRecyclerAdapter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class SportGroupItem extends SportItem {
    public int count;
    public String title;
    public int totalCalorie;
    public int totalSecond;
    public int weekSecond;
    public boolean isExpand = false;
    public ArrayList<SportChildItem> children = new ArrayList<>();

    @Override // com.ido.life.module.user.sportrecord.model.SportItem
    public int type() {
        return ExpandableRecyclerAdapter.GROUP;
    }

    public void addChild(SportChildItem sportChildItem) {
        this.children.add(sportChildItem);
    }

    public SportChildItem getChild(int i) {
        return this.children.get(i);
    }
}