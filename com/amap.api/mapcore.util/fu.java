package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.DownloadProgressView;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.boat.Xtend.two.R;

/* JADX INFO: compiled from: OfflineChild.java */
/* JADX INFO: loaded from: classes.dex */
public class fu implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f965b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TextView f966c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private TextView f967d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ImageView f968e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private TextView f969f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private OfflineMapManager f970g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private OfflineMapCity f971h;
    private View k;
    private DownloadProgressView l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f964a = 0;
    private boolean i = false;
    private Handler j = new Handler() { // from class: com.amap.api.mapcore.util.fu.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            try {
                fu.this.a(message.arg1, message.arg2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) throws Exception {
        if (this.f964a == 2 && i2 > 3 && i2 < 100) {
            this.l.setVisibility(0);
            this.l.setProgress(i2);
        } else {
            this.l.setVisibility(8);
        }
        switch (i) {
            case -1:
                e();
                break;
            case 0:
                if (this.f964a == 1) {
                    this.f968e.setVisibility(8);
                    this.f969f.setText("下载中");
                    this.f969f.setTextColor(Color.parseColor("#4287ff"));
                } else {
                    e(i2);
                }
                break;
            case 1:
                d(i2);
                break;
            case 2:
                b(i2);
                break;
            case 3:
                c(i2);
                break;
            case 4:
                f();
                break;
            case 5:
                break;
            case 6:
                c();
                break;
            case 7:
                d();
                break;
            default:
                switch (i) {
                    case 101:
                    case 102:
                    case 103:
                        e();
                        break;
                }
                break;
        }
    }

    public fu(Context context, OfflineMapManager offlineMapManager) {
        this.f965b = context;
        b();
        this.f970g = offlineMapManager;
    }

    public void a(int i) {
        this.f964a = i;
    }

    public View a() {
        return this.k;
    }

    private void b() {
        this.k = fy.a(this.f965b, R.array.chart_detail_bottom_label_12, null);
        this.l = (DownloadProgressView) this.k.findViewById(R.dimen.abc_alert_dialog_button_bar_height);
        this.f966c = (TextView) this.k.findViewById(R.dimen.abc_action_bar_subtitle_bottom_margin_material);
        this.f967d = (TextView) this.k.findViewById(R.dimen.abc_action_button_min_width_overflow_material);
        this.f968e = (ImageView) this.k.findViewById(R.dimen.abc_action_button_min_width_material);
        this.f969f = (TextView) this.k.findViewById(R.dimen.abc_action_button_min_height_material);
        this.f968e.setOnClickListener(this);
    }

    public void a(OfflineMapCity offlineMapCity) {
        if (offlineMapCity != null) {
            this.f971h = offlineMapCity;
            this.f966c.setText(offlineMapCity.getCity());
            double size = ((double) ((int) (((offlineMapCity.getSize() / 1024.0d) / 1024.0d) * 100.0d))) / 100.0d;
            this.f967d.setText(String.valueOf(size) + " M");
            a(this.f971h.getState(), this.f971h.getcompleteCode(), this.i);
        }
    }

    private void a(int i, int i2, boolean z) {
        OfflineMapCity offlineMapCity = this.f971h;
        if (offlineMapCity != null) {
            offlineMapCity.setState(i);
            this.f971h.setCompleteCode(i2);
        }
        Message message = new Message();
        message.arg1 = i;
        message.arg2 = i2;
        this.j.sendMessage(message);
    }

    private void c() {
        this.f969f.setVisibility(8);
        this.f968e.setVisibility(0);
        this.f968e.setImageResource(R.animator.design_fab_show_motion_spec);
    }

    private void d() {
        this.f969f.setVisibility(0);
        this.f968e.setVisibility(0);
        this.f968e.setImageResource(R.animator.design_fab_show_motion_spec);
        this.f969f.setText("已下载-有更新");
    }

    private void b(int i) {
        if (this.f964a == 1) {
            this.f968e.setVisibility(8);
            this.f969f.setVisibility(0);
            this.f969f.setText("等待中");
            this.f969f.setTextColor(Color.parseColor("#4287ff"));
            return;
        }
        this.f969f.setVisibility(0);
        this.f968e.setVisibility(8);
        this.f969f.setTextColor(Color.parseColor("#4287ff"));
        this.f969f.setText("等待中");
    }

    private void e() {
        this.f969f.setVisibility(0);
        this.f968e.setVisibility(8);
        this.f969f.setTextColor(-65536);
        this.f969f.setText("下载出现异常");
    }

    private void c(int i) {
        this.f969f.setVisibility(0);
        this.f968e.setVisibility(8);
        this.f969f.setTextColor(-7829368);
        this.f969f.setText("暂停");
    }

    private void f() {
        this.f969f.setVisibility(0);
        this.f968e.setVisibility(8);
        this.f969f.setText("已下载");
        this.f969f.setTextColor(Color.parseColor("#898989"));
    }

    private void d(int i) {
        if (this.f964a == 1) {
            return;
        }
        this.f969f.setVisibility(0);
        this.f968e.setVisibility(8);
        this.f969f.setText("解压中");
        this.f969f.setTextColor(Color.parseColor("#898989"));
    }

    private void e(int i) {
        if (this.f971h == null) {
            return;
        }
        this.f969f.setVisibility(0);
        this.f969f.setText("下载中");
        this.f968e.setVisibility(8);
        this.f969f.setTextColor(Color.parseColor("#4287ff"));
    }

    private synchronized void g() {
        this.f970g.pause();
        this.f970g.restart();
    }

    private synchronized boolean h() {
        try {
            this.f970g.downloadByCityName(this.f971h.getCity());
        } catch (AMapException e2) {
            e2.printStackTrace();
            Toast.makeText(this.f965b, e2.getErrorMessage(), 0).show();
            return false;
        }
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            if (!er.d(this.f965b)) {
                Toast.makeText(this.f965b, "无网络连接", 0).show();
                return;
            }
            if (this.f971h != null) {
                int state = this.f971h.getState();
                int i = this.f971h.getcompleteCode();
                if (state == 0) {
                    g();
                    c(i);
                } else {
                    if (state == 1 || state == 4) {
                        return;
                    }
                    if (h()) {
                        b(i);
                    } else {
                        e();
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}