package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.boat.Xtend.two.R;

/* JADX INFO: compiled from: BottomDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class fs extends ft implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private OfflineMapManager f955a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private View f956b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TextView f957c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private TextView f958d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TextView f959e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private TextView f960f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f961g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f962h;

    public fs(Context context, OfflineMapManager offlineMapManager) {
        super(context);
        this.f955a = offlineMapManager;
    }

    @Override // com.amap.api.mapcore.util.ft
    protected void a() {
        this.f956b = fy.a(getContext(), R.array.calendar, null);
        setContentView(this.f956b);
        this.f956b.setOnClickListener(new View.OnClickListener() { // from class: com.amap.api.mapcore.util.fs.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                fs.this.dismiss();
            }
        });
        this.f957c = (TextView) this.f956b.findViewById(R.dimen.abc_action_bar_overflow_padding_end_material);
        this.f958d = (TextView) this.f956b.findViewById(R.dimen.abc_action_bar_overflow_padding_start_material);
        this.f958d.setText("暂停下载");
        this.f959e = (TextView) this.f956b.findViewById(R.dimen.abc_action_bar_stacked_max_height);
        this.f960f = (TextView) this.f956b.findViewById(R.dimen.abc_action_bar_stacked_tab_max_width);
        this.f958d.setOnClickListener(this);
        this.f959e.setOnClickListener(this);
        this.f960f.setOnClickListener(this);
    }

    public void a(int i, String str) {
        this.f957c.setText(str);
        if (i == 0) {
            this.f958d.setText("暂停下载");
            this.f958d.setVisibility(0);
            this.f959e.setText("取消下载");
        }
        if (i == 2) {
            this.f958d.setVisibility(8);
            this.f959e.setText("取消下载");
        } else if (i == -1 || i == 101 || i == 102 || i == 103) {
            this.f958d.setText("继续下载");
            this.f958d.setVisibility(0);
        } else if (i == 3) {
            this.f958d.setVisibility(0);
            this.f958d.setText("继续下载");
            this.f959e.setText("取消下载");
        } else if (i == 4) {
            this.f959e.setText("删除");
            this.f958d.setVisibility(8);
        }
        this.f961g = i;
        this.f962h = str;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            int id = view.getId();
            if (id != R.dimen.abc_action_bar_overflow_padding_start_material) {
                if (id != R.dimen.abc_action_bar_stacked_max_height) {
                    if (id == R.dimen.abc_action_bar_stacked_tab_max_width) {
                        dismiss();
                        return;
                    }
                    return;
                } else {
                    if (TextUtils.isEmpty(this.f962h)) {
                        return;
                    }
                    this.f955a.remove(this.f962h);
                    dismiss();
                    return;
                }
            }
            if (this.f961g == 0) {
                this.f958d.setText("继续下载");
                this.f955a.pause();
            } else if (this.f961g == 3 || this.f961g == -1 || this.f961g == 101 || this.f961g == 102 || this.f961g == 103) {
                this.f958d.setText("暂停下载");
                this.f955a.downloadByCityName(this.f962h);
            }
            dismiss();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}