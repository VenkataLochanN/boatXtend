package com.ido.life.module.bind.scan;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class ScanCodeActivity_ViewBinding implements Unbinder {
    private ScanCodeActivity target;
    private View view7f0a0776;

    public ScanCodeActivity_ViewBinding(ScanCodeActivity scanCodeActivity) {
        this(scanCodeActivity, scanCodeActivity.getWindow().getDecorView());
    }

    public ScanCodeActivity_ViewBinding(final ScanCodeActivity scanCodeActivity, View view) {
        this.target = scanCodeActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mTitleLeftBtn' and method 'back'");
        scanCodeActivity.mTitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mTitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.bind.scan.ScanCodeActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                scanCodeActivity.back(view2);
            }
        });
        scanCodeActivity.mIvTorch = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_torch, "field 'mIvTorch'", ImageView.class);
        scanCodeActivity.mTvLight = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_light, "field 'mTvLight'", TextView.class);
        scanCodeActivity.mTvScanNotFind = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_scan_not_find, "field 'mTvScanNotFind'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        ScanCodeActivity scanCodeActivity = this.target;
        if (scanCodeActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        scanCodeActivity.mTitleLeftBtn = null;
        scanCodeActivity.mIvTorch = null;
        scanCodeActivity.mTvLight = null;
        scanCodeActivity.mTvScanNotFind = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
    }
}