package com.amap.api.mapcore.util;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AutoCompleteTextView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.amap.api.maps.offlinemap.DownLoadExpandListView;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.boat.Xtend.two.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: OfflineMapPage.java */
/* JADX INFO: loaded from: classes.dex */
public class fw extends com.amap.api.offlineservice.a implements TextWatcher, View.OnTouchListener, AbsListView.OnScrollListener, OfflineMapManager.OfflineLoadedListener, OfflineMapManager.OfflineMapDownloadListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ImageView f975b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private RelativeLayout f976c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private DownLoadExpandListView f977d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ListView f978e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ExpandableListView f979f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ImageView f980g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ImageView f981h;
    private AutoCompleteTextView i;
    private RelativeLayout j;
    private RelativeLayout k;
    private ImageView l;
    private ImageView m;
    private RelativeLayout n;
    private fq p;
    private fp r;
    private fr s;
    private fs x;
    private List<OfflineMapProvince> o = new ArrayList();
    private OfflineMapManager q = null;
    private boolean t = true;
    private boolean u = true;
    private int v = -1;
    private long w = 0;
    private boolean y = true;

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // com.amap.api.maps.offlinemap.OfflineMapManager.OfflineMapDownloadListener
    public void onCheckUpdate(boolean z, String str) {
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    @Override // com.amap.api.offlineservice.a
    public void a() {
        View viewA = fy.a(this.f1951a, R.array.ambient_volume_label, null);
        this.f977d = (DownLoadExpandListView) viewA.findViewById(R.dimen.abc_action_bar_default_padding_end_material);
        this.f977d.setOnTouchListener(this);
        this.j = (RelativeLayout) viewA.findViewById(R.dimen.abc_action_bar_content_inset_material);
        this.f980g = (ImageView) viewA.findViewById(R.dimen.abc_action_bar_default_height_material);
        this.j.setOnClickListener(this.f1951a);
        this.k = (RelativeLayout) viewA.findViewById(R.dimen.abc_action_bar_elevation_material);
        this.f981h = (ImageView) viewA.findViewById(R.dimen.abc_action_bar_icon_vertical_padding_material);
        this.k.setOnClickListener(this.f1951a);
        this.n = (RelativeLayout) viewA.findViewById(R.dimen.abc_action_bar_default_padding_start_material);
        this.f975b = (ImageView) this.f976c.findViewById(R.dimen.abc_button_padding_vertical_material);
        this.f975b.setOnClickListener(this.f1951a);
        this.m = (ImageView) this.f976c.findViewById(R.dimen.abc_config_prefDialogWidth);
        this.l = (ImageView) this.f976c.findViewById(R.dimen.abc_control_inset_material);
        this.l.setOnClickListener(new View.OnClickListener() { // from class: com.amap.api.mapcore.util.fw.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    fw.this.i.setText("");
                    fw.this.l.setVisibility(8);
                    fw.this.a(false);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fw.this.m.getLayoutParams();
                    layoutParams.leftMargin = fw.this.a(95.0f);
                    fw.this.m.setLayoutParams(layoutParams);
                    fw.this.i.setPadding(fw.this.a(105.0f), 0, 0, 0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        this.f976c.findViewById(R.dimen.abc_control_padding_material).setOnTouchListener(this);
        this.i = (AutoCompleteTextView) this.f976c.findViewById(R.dimen.abc_control_corner_material);
        this.i.addTextChangedListener(this);
        this.i.setOnTouchListener(this);
        this.f978e = (ListView) this.f976c.findViewById(R.dimen.abc_dialog_fixed_height_major);
        this.f979f = (ExpandableListView) this.f976c.findViewById(R.dimen.abc_dialog_corner_radius_material);
        this.f979f.addHeaderView(viewA);
        this.f979f.setOnTouchListener(this);
        this.f979f.setOnScrollListener(this);
        this.q = new OfflineMapManager(this.f1951a, this);
        this.q.setOnOfflineLoadedListener(this);
        l();
        this.p = new fq(this.o, this.q, this.f1951a);
        this.f979f.setAdapter(this.p);
        this.f979f.setOnGroupCollapseListener(this.p);
        this.f979f.setOnGroupExpandListener(this.p);
        this.f979f.setGroupIndicator(null);
        if (this.t) {
            this.f981h.setBackgroundResource(R.animator.design_appbar_state_list_animator);
            this.f979f.setVisibility(0);
        } else {
            this.f981h.setBackgroundResource(R.animator.linear_indeterminate_line1_tail_interpolator);
            this.f979f.setVisibility(8);
        }
        if (this.u) {
            this.f980g.setBackgroundResource(R.animator.design_appbar_state_list_animator);
            this.f977d.setVisibility(0);
        } else {
            this.f980g.setBackgroundResource(R.animator.linear_indeterminate_line1_tail_interpolator);
            this.f977d.setVisibility(8);
        }
    }

    private void j() {
        try {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.m.getLayoutParams();
            layoutParams.leftMargin = a(18.0f);
            this.m.setLayoutParams(layoutParams);
            this.i.setPadding(a(30.0f), 0, 0, 0);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.amap.api.offlineservice.a
    public void a(View view) {
        try {
            int id = view.getId();
            if (id == R.dimen.abc_button_padding_vertical_material) {
                this.f1951a.closeScr();
            } else if (id == R.dimen.abc_action_bar_content_inset_material) {
                if (this.u) {
                    this.f977d.setVisibility(8);
                    this.f980g.setBackgroundResource(R.animator.linear_indeterminate_line1_tail_interpolator);
                    this.u = false;
                } else {
                    this.f977d.setVisibility(0);
                    this.f980g.setBackgroundResource(R.animator.design_appbar_state_list_animator);
                    this.u = true;
                }
            } else if (id == R.dimen.abc_action_bar_elevation_material) {
                if (this.t) {
                    this.p.b();
                    this.f981h.setBackgroundResource(R.animator.linear_indeterminate_line1_tail_interpolator);
                    this.t = false;
                } else {
                    this.p.a();
                    this.f981h.setBackgroundResource(R.animator.design_appbar_state_list_animator);
                    this.t = true;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.amap.api.offlineservice.a
    public RelativeLayout c() {
        if (this.f976c == null) {
            this.f976c = (RelativeLayout) fy.a(this.f1951a, R.array.country_codes_all, null);
        }
        return this.f976c;
    }

    @Override // com.amap.api.offlineservice.a
    public void d() {
        this.q.destroy();
    }

    private void k() {
        l();
        this.s = new fr(this.o, this.q, this.f1951a);
        this.f978e.setAdapter((ListAdapter) this.s);
    }

    public void i() {
        this.r = new fp(this.f1951a, this, this.q, this.o);
        this.f977d.setAdapter(this.r);
        this.r.notifyDataSetChanged();
    }

    public void a(OfflineMapCity offlineMapCity) {
        try {
            if (this.x == null) {
                this.x = new fs(this.f1951a, this.q);
            }
            this.x.a(offlineMapCity.getState(), offlineMapCity.getCity());
            this.x.show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void l() {
        ArrayList<OfflineMapProvince> offlineMapProvinceList = this.q.getOfflineMapProvinceList();
        this.o.clear();
        this.o.add(null);
        ArrayList<OfflineMapCity> arrayList = new ArrayList<>();
        ArrayList<OfflineMapCity> arrayList2 = new ArrayList<>();
        ArrayList<OfflineMapCity> arrayList3 = new ArrayList<>();
        for (int i = 0; i < offlineMapProvinceList.size(); i++) {
            OfflineMapProvince offlineMapProvince = offlineMapProvinceList.get(i);
            if (offlineMapProvince.getCityList().size() != 1) {
                this.o.add(i + 1, offlineMapProvince);
            } else {
                String provinceName = offlineMapProvince.getProvinceName();
                if (provinceName.contains("香港")) {
                    arrayList2.addAll(offlineMapProvince.getCityList());
                } else if (provinceName.contains("澳门")) {
                    arrayList2.addAll(offlineMapProvince.getCityList());
                } else if (provinceName.contains("全国概要图")) {
                    arrayList3.addAll(0, offlineMapProvince.getCityList());
                } else {
                    arrayList3.addAll(offlineMapProvince.getCityList());
                }
            }
        }
        OfflineMapProvince offlineMapProvince2 = new OfflineMapProvince();
        offlineMapProvince2.setProvinceName("基本功能包+直辖市");
        offlineMapProvince2.setCityList(arrayList3);
        this.o.set(0, offlineMapProvince2);
        OfflineMapProvince offlineMapProvince3 = new OfflineMapProvince();
        offlineMapProvince3.setProvinceName("直辖市");
        offlineMapProvince3.setCityList(arrayList);
        OfflineMapProvince offlineMapProvince4 = new OfflineMapProvince();
        offlineMapProvince4.setProvinceName("港澳");
        offlineMapProvince4.setCityList(arrayList2);
        this.o.add(offlineMapProvince4);
    }

    @Override // com.amap.api.maps.offlinemap.OfflineMapManager.OfflineMapDownloadListener
    public void onDownload(int i, int i2, String str) {
        switch (i) {
            case -1:
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                break;
            default:
                switch (i) {
                    case 101:
                        try {
                            Toast.makeText(this.f1951a, "网络异常", 0).show();
                            this.q.pause();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return;
                        }
                        break;
                }
                break;
        }
        if (i == 2) {
            this.r.a();
        }
        if (this.v == i) {
            if (System.currentTimeMillis() - this.w > 1200) {
                if (this.y) {
                    this.r.notifyDataSetChanged();
                }
                this.w = System.currentTimeMillis();
                return;
            }
            return;
        }
        if (this.p != null) {
            this.p.notifyDataSetChanged();
        }
        if (this.r != null) {
            this.r.notifyDataSetChanged();
        }
        if (this.s != null) {
            this.s.notifyDataSetChanged();
        }
        this.v = i;
    }

    @Override // com.amap.api.maps.offlinemap.OfflineMapManager.OfflineMapDownloadListener
    public void onRemove(boolean z, String str, String str2) {
        fp fpVar = this.r;
        if (fpVar != null) {
            fpVar.b();
        }
    }

    @Override // com.amap.api.maps.offlinemap.OfflineMapManager.OfflineLoadedListener
    public void onVerifyComplete() {
        k();
        i();
    }

    @Override // com.amap.api.offlineservice.a
    public boolean b() {
        try {
            if (this.f978e.getVisibility() == 0) {
                this.i.setText("");
                this.l.setVisibility(8);
                a(false);
                return false;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return super.b();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        m();
        if (view.getId() != R.dimen.abc_control_corner_material) {
            return false;
        }
        j();
        return false;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (TextUtils.isEmpty(charSequence)) {
            a(false);
            this.l.setVisibility(8);
            return;
        }
        this.l.setVisibility(0);
        ArrayList arrayList = new ArrayList();
        List<OfflineMapProvince> list = this.o;
        if (list != null && list.size() > 0) {
            ArrayList<OfflineMapCity> arrayList2 = new ArrayList();
            Iterator<OfflineMapProvince> it = this.o.iterator();
            while (it.hasNext()) {
                arrayList2.addAll(it.next().getCityList());
            }
            for (OfflineMapCity offlineMapCity : arrayList2) {
                String city = offlineMapCity.getCity();
                String pinyin = offlineMapCity.getPinyin();
                String jianpin = offlineMapCity.getJianpin();
                if (charSequence.length() == 1) {
                    if (jianpin.startsWith(String.valueOf(charSequence))) {
                        arrayList.add(offlineMapCity);
                    }
                } else if (jianpin.startsWith(String.valueOf(charSequence)) || pinyin.startsWith(String.valueOf(charSequence)) || city.startsWith(String.valueOf(charSequence))) {
                    arrayList.add(offlineMapCity);
                }
            }
        }
        if (arrayList.size() > 0) {
            a(true);
            Collections.sort(arrayList, new Comparator<OfflineMapCity>() { // from class: com.amap.api.mapcore.util.fw.2
                @Override // java.util.Comparator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public int compare(OfflineMapCity offlineMapCity2, OfflineMapCity offlineMapCity3) {
                    char[] charArray = offlineMapCity2.getJianpin().toCharArray();
                    char[] charArray2 = offlineMapCity3.getJianpin().toCharArray();
                    return (charArray[0] >= charArray2[0] && charArray[1] >= charArray2[1]) ? 0 : 1;
                }
            });
            fr frVar = this.s;
            if (frVar != null) {
                frVar.a(arrayList);
                this.s.notifyDataSetChanged();
                return;
            }
            return;
        }
        Toast.makeText(this.f1951a, "未找到相关城市", 0).show();
    }

    public void a(boolean z) {
        if (z) {
            this.j.setVisibility(8);
            this.k.setVisibility(8);
            this.f977d.setVisibility(8);
            this.f979f.setVisibility(8);
            this.n.setVisibility(8);
            this.f978e.setVisibility(0);
            return;
        }
        this.j.setVisibility(0);
        this.k.setVisibility(0);
        this.n.setVisibility(0);
        this.f977d.setVisibility(this.u ? 0 : 8);
        this.f979f.setVisibility(this.t ? 0 : 8);
        this.f978e.setVisibility(8);
    }

    private void m() {
        AutoCompleteTextView autoCompleteTextView = this.i;
        if (autoCompleteTextView == null || !autoCompleteTextView.isFocused()) {
            return;
        }
        this.i.clearFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) this.f1951a.getSystemService("input_method");
        if (inputMethodManager != null ? inputMethodManager.isActive() : false) {
            inputMethodManager.hideSoftInputFromWindow(this.i.getWindowToken(), 2);
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 2) {
            this.y = false;
        } else {
            this.y = true;
        }
    }
}