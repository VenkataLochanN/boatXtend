package com.ido.life.customview.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceTypeItemView extends LinearLayout {
    private Drawable mDrawableLeft;
    private Drawable mDrawableRight;
    private ImageView mIvRight;
    private ImageView mIvTypeIcon;
    private TextView mTvTypeName;
    private String mTypeName;

    public DeviceTypeItemView(Context context) {
        this(context, null);
    }

    public DeviceTypeItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DeviceTypeItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initAttrs(context, attributeSet);
        initView();
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        LayoutInflater.from(context).inflate(R.layout.view_device_type_item, (ViewGroup) this, true);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.ido.life.R.styleable.DeviceTypeItemView);
        this.mTypeName = typedArrayObtainStyledAttributes.getString(2);
        this.mDrawableLeft = typedArrayObtainStyledAttributes.getDrawable(1);
        this.mDrawableRight = typedArrayObtainStyledAttributes.getDrawable(0);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void initView() {
        this.mIvTypeIcon = (ImageView) findViewById(R.id.iv_type_icon);
        this.mTvTypeName = (TextView) findViewById(R.id.tv_type_name);
        this.mIvRight = (ImageView) findViewById(R.id.iv_right);
        setTypeIcon(this.mDrawableLeft);
        setTypeName(this.mTypeName);
        setRightImage(this.mDrawableRight);
    }

    public void setTypeIcon(Drawable drawable) {
        if (drawable != null) {
            this.mIvTypeIcon.setImageDrawable(drawable);
        }
    }

    public void setTypeIcon(int i) {
        if (i != 0) {
            this.mIvTypeIcon.setImageResource(i);
        }
    }

    public void setTypeName(String str) {
        this.mTvTypeName.setText(str);
    }

    public void setTypeName(int i) {
        if (i != 0) {
            this.mTvTypeName.setText(i);
        }
    }

    public void setRightImage(Drawable drawable) {
        if (drawable != null) {
            this.mIvRight.setImageDrawable(drawable);
        }
    }

    public void setRightImage(int i) {
        if (i != 0) {
            this.mIvRight.setImageResource(i);
        }
    }
}