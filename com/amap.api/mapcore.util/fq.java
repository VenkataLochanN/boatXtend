package com.amap.api.mapcore.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.boat.Xtend.two.R;
import java.util.List;

/* JADX INFO: compiled from: OfflineListAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class fq extends BaseExpandableListAdapter implements ExpandableListView.OnGroupCollapseListener, ExpandableListView.OnGroupExpandListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean[] f937a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f938b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<OfflineMapProvince> f939c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private OfflineMapManager f940d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Context f941e;

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i, int i2) {
        return null;
    }

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
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public fq(List<OfflineMapProvince> list, OfflineMapManager offlineMapManager, Context context) {
        this.f939c = null;
        this.f939c = list;
        this.f940d = offlineMapManager;
        this.f941e = context;
        this.f937a = new boolean[list.size()];
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        int i = this.f938b;
        return i == -1 ? this.f939c.size() : i;
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i) {
        return this.f939c.get(i).getProvinceName();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        if (a(i)) {
            return this.f939c.get(i).getCityList().size();
        }
        return this.f939c.get(i).getCityList().size();
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = (RelativeLayout) fy.a(this.f941e, R.array.chart_detail_bottom_label_24, null);
        }
        TextView textView = (TextView) view.findViewById(R.dimen.abc_alert_dialog_button_dimen);
        ImageView imageView = (ImageView) view.findViewById(R.dimen.abc_button_inset_horizontal_material);
        textView.setText(this.f939c.get(i).getProvinceName());
        if (this.f937a[i]) {
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
            fu fuVar = new fu(this.f941e, this.f940d);
            fuVar.a(1);
            View viewA = fuVar.a();
            aVar.f942a = fuVar;
            viewA.setTag(aVar);
            view = viewA;
        }
        aVar.f942a.a(this.f939c.get(i).getCityList().get(i2));
        return view;
    }

    private boolean a(int i) {
        return (i == 0 || i == getGroupCount() - 1) ? false : true;
    }

    public void a() {
        this.f938b = -1;
        notifyDataSetChanged();
    }

    public void b() {
        this.f938b = 0;
        notifyDataSetChanged();
    }

    /* JADX INFO: compiled from: OfflineListAdapter.java */
    public final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public fu f942a;

        public a() {
        }
    }

    @Override // android.widget.ExpandableListView.OnGroupCollapseListener
    public void onGroupCollapse(int i) {
        this.f937a[i] = false;
    }

    @Override // android.widget.ExpandableListView.OnGroupExpandListener
    public void onGroupExpand(int i) {
        this.f937a[i] = true;
    }
}