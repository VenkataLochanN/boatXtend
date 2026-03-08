package com.amap.api.mapcore.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.boat.Xtend.two.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: OfflineDownloadedAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class fp extends BaseExpandableListAdapter implements ExpandableListView.OnGroupCollapseListener, ExpandableListView.OnGroupExpandListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean[] f927b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f928c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private fu f929d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private fw f931f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private OfflineMapManager f932g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<OfflineMapProvince> f930e = new ArrayList();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    List<OfflineMapProvince> f926a = new ArrayList();

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        return i2;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        return i;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public fp(Context context, fw fwVar, OfflineMapManager offlineMapManager, List<OfflineMapProvince> list) {
        this.f928c = context;
        this.f931f = fwVar;
        this.f932g = offlineMapManager;
        if (list != null && list.size() > 0) {
            this.f930e.clear();
            this.f930e.addAll(list);
            for (OfflineMapProvince offlineMapProvince : this.f930e) {
                if (offlineMapProvince != null && offlineMapProvince.getDownloadedCityList().size() > 0) {
                    this.f926a.add(offlineMapProvince);
                }
            }
        }
        this.f927b = new boolean[this.f926a.size()];
    }

    public void a() {
        for (OfflineMapProvince offlineMapProvince : this.f930e) {
            if (offlineMapProvince.getDownloadedCityList().size() > 0 && !this.f926a.contains(offlineMapProvince)) {
                this.f926a.add(offlineMapProvince);
            }
        }
        this.f927b = new boolean[this.f926a.size()];
        notifyDataSetChanged();
    }

    public void b() {
        try {
            for (int size = this.f926a.size(); size > 0; size--) {
                OfflineMapProvince offlineMapProvince = this.f926a.get(size - 1);
                if (offlineMapProvince.getDownloadedCityList().size() == 0) {
                    this.f926a.remove(offlineMapProvince);
                }
            }
            this.f927b = new boolean[this.f926a.size()];
            notifyDataSetChanged();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.f926a.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        return this.f926a.get(i).getDownloadedCityList().size();
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i) {
        return this.f926a.get(i).getProvinceName();
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i, int i2) {
        return this.f926a.get(i).getDownloadedCityList().get(i2);
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = (RelativeLayout) fy.a(this.f928c, R.array.chart_detail_bottom_label_24, null);
        }
        TextView textView = (TextView) view.findViewById(R.dimen.abc_alert_dialog_button_dimen);
        ImageView imageView = (ImageView) view.findViewById(R.dimen.abc_button_inset_horizontal_material);
        textView.setText(this.f926a.get(i).getProvinceName());
        if (this.f927b[i]) {
            imageView.setImageDrawable(fy.a().getDrawable(R.animator.linear_indeterminate_line2_head_interpolator));
        } else {
            imageView.setImageDrawable(fy.a().getDrawable(R.animator.linear_indeterminate_line2_tail_interpolator));
        }
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        a aVar;
        if (view != null) {
            aVar = (a) view.getTag();
        } else {
            aVar = new a();
            this.f929d = new fu(this.f928c, this.f932g);
            this.f929d.a(2);
            view = this.f929d.a();
            aVar.f935a = this.f929d;
            view.setTag(aVar);
        }
        OfflineMapProvince offlineMapProvince = this.f926a.get(i);
        if (i2 < offlineMapProvince.getDownloadedCityList().size()) {
            final OfflineMapCity offlineMapCity = offlineMapProvince.getDownloadedCityList().get(i2);
            aVar.f935a.a(offlineMapCity);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.amap.api.mapcore.util.fp.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    fp.this.f931f.a(offlineMapCity);
                }
            });
        }
        return view;
    }

    /* JADX INFO: compiled from: OfflineDownloadedAdapter.java */
    public final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public fu f935a;

        public a() {
        }
    }

    @Override // android.widget.ExpandableListView.OnGroupCollapseListener
    public void onGroupCollapse(int i) {
        this.f927b[i] = false;
    }

    @Override // android.widget.ExpandableListView.OnGroupExpandListener
    public void onGroupExpand(int i) {
        this.f927b[i] = true;
    }
}