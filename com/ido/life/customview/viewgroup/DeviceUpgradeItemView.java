package com.ido.life.customview.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceUpgradeItemView extends LinearLayout implements View.OnClickListener {
    private View mBottomDivider;
    private ImageView mIvIcon;
    private TextView mTvCurrentVersion;
    private TextView mTvNewVersion;
    private TextView mTvTitle;
    private TextView mTvUpgrade;
    private UpgradeClickListener mUpgradeClickListener;

    public interface UpgradeClickListener {
        void onUpgradeClicked();
    }

    public DeviceUpgradeItemView(Context context) {
        this(context, null);
    }

    public DeviceUpgradeItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DeviceUpgradeItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        LayoutInflater.from(context).inflate(R.layout.view_device_upgrade_item, (ViewGroup) this, true);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.ido.life.R.styleable.DeviceUpgradeItemView);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        String string = typedArrayObtainStyledAttributes.getString(3);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(2, false);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(4, false);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(1, true);
        typedArrayObtainStyledAttributes.recycle();
        this.mIvIcon = (ImageView) findViewById(R.id.iv_icon);
        this.mTvTitle = (TextView) findViewById(R.id.tv_title);
        this.mTvNewVersion = (TextView) findViewById(R.id.tv_new_version);
        this.mTvCurrentVersion = (TextView) findViewById(R.id.tv_current_version);
        this.mTvUpgrade = (TextView) findViewById(R.id.tv_upgrade);
        this.mBottomDivider = findViewById(R.id.view_divider);
        setIcon(drawable);
        setTitle(string);
        showNewVersion(z);
        setUpgradeEnable(z2);
        showBottomDivider(z3);
        this.mTvUpgrade.setOnClickListener(this);
    }

    public void setCurrentVersion(String str) {
        this.mTvCurrentVersion.setText(str);
    }

    public void showBottomDivider(boolean z) {
        this.mBottomDivider.setVisibility(z ? 0 : 8);
    }

    public void setUpgradeEnable(boolean z) {
        this.mTvUpgrade.setEnabled(z);
    }

    public boolean isUpgradeEnable() {
        return this.mTvUpgrade.isEnabled();
    }

    public void setNewVersion(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            showNewVersion(false);
            return;
        }
        showNewVersion(true);
        this.mTvNewVersion.setText(str);
        setUpgradeEnable(z);
    }

    public void showNewVersion(boolean z) {
        this.mTvNewVersion.setVisibility(z ? 0 : 8);
        this.mTvUpgrade.setVisibility(z ? 0 : 8);
    }

    public void setTitle(String str) {
        this.mTvTitle.setText(str);
    }

    public void setTitle(int i) {
        if (i != 0) {
            this.mTvTitle.setText(i);
        }
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            this.mIvIcon.setImageDrawable(drawable);
        }
    }

    public void setIcon(int i) {
        if (i != 0) {
            this.mIvIcon.setImageResource(i);
        }
    }

    public void setOnUpgradeClickListener(UpgradeClickListener upgradeClickListener) {
        this.mUpgradeClickListener = upgradeClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        UpgradeClickListener upgradeClickListener = this.mUpgradeClickListener;
        if (upgradeClickListener == null) {
            return;
        }
        upgradeClickListener.onUpgradeClicked();
    }
}