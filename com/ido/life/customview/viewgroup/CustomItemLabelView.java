package com.ido.life.customview.viewgroup;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.boat.Xtend.two.R;
import com.ido.common.utils.ScreenUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.CustomToggleButton;

/* JADX INFO: loaded from: classes2.dex */
public class CustomItemLabelView extends RelativeLayout {
    private int mBackgroundRes;
    private View mDivider;
    private View mDividerTwo;
    private Drawable mDrawableLeft;
    private Drawable mDrawableRight;
    private boolean mHasBottomDivider;
    private boolean mHasBottomDividerTwo;
    private boolean mHasLeftIcon;
    private boolean mHasRightIcon;
    private boolean mIsTitleBold;
    private boolean mIsToggle;
    private boolean mIsToggleOn;
    private ImageView mIvLeft;
    private ImageView mIvRight;
    private RelativeLayout mLayoutRoot;
    private float mLeftIconSize;
    private Resources mResources;
    private RegularTextView mRtvSubtitle;
    private RegularTextView mRtvTitle;
    private RegularTextView mRtvValue;
    private String mSubtitle;
    private String mTitle;
    private int mTitleColor;
    private float mTitleSize;
    private CustomToggleButton mToggleButton;
    private String mValue;
    private int mValueColor;
    private float mValueSize;

    public CustomItemLabelView(Context context) {
        this(context, null);
    }

    public CustomItemLabelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomItemLabelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initAttrs(context, attributeSet);
        initView();
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        this.mResources = context.getResources();
        LayoutInflater.from(context).inflate(R.layout.view_custom_item_label, (ViewGroup) this, true);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.ido.life.R.styleable.CustomItemLabelView);
        this.mTitle = typedArrayObtainStyledAttributes.getString(13);
        this.mValue = typedArrayObtainStyledAttributes.getString(16);
        this.mSubtitle = typedArrayObtainStyledAttributes.getString(12);
        this.mBackgroundRes = typedArrayObtainStyledAttributes.getResourceId(10, R.color.translate);
        this.mTitleColor = typedArrayObtainStyledAttributes.getColor(14, context.getColor(R.color.white));
        this.mValueColor = typedArrayObtainStyledAttributes.getColor(17, context.getColor(R.color.white));
        this.mHasLeftIcon = typedArrayObtainStyledAttributes.getBoolean(5, true);
        this.mHasRightIcon = typedArrayObtainStyledAttributes.getBoolean(6, true);
        this.mHasBottomDivider = typedArrayObtainStyledAttributes.getBoolean(3, true);
        this.mHasBottomDividerTwo = typedArrayObtainStyledAttributes.getBoolean(4, false);
        this.mIsToggle = typedArrayObtainStyledAttributes.getBoolean(8, false);
        this.mIsToggleOn = typedArrayObtainStyledAttributes.getBoolean(9, false);
        this.mDrawableLeft = typedArrayObtainStyledAttributes.getDrawable(1);
        this.mDrawableRight = typedArrayObtainStyledAttributes.getDrawable(2);
        this.mTitleSize = typedArrayObtainStyledAttributes.getDimension(15, this.mResources.getDimension(R.dimen.size16sp));
        this.mValueSize = typedArrayObtainStyledAttributes.getDimension(18, this.mResources.getDimension(R.dimen.size12sp));
        this.mIsTitleBold = typedArrayObtainStyledAttributes.getBoolean(7, false);
        this.mLeftIconSize = typedArrayObtainStyledAttributes.getDimension(11, this.mResources.getDimension(R.dimen.sw_dp_27));
        typedArrayObtainStyledAttributes.recycle();
    }

    private void initView() {
        this.mLayoutRoot = (RelativeLayout) findViewById(R.id.layout_root);
        this.mIvLeft = (ImageView) findViewById(R.id.iv_left_icon);
        this.mRtvTitle = (RegularTextView) findViewById(R.id.rtv_title);
        this.mRtvValue = (RegularTextView) findViewById(R.id.rtv_label);
        this.mIvRight = (ImageView) findViewById(R.id.iv_right_icon);
        this.mDivider = findViewById(R.id.divider);
        this.mDividerTwo = findViewById(R.id.divider_two);
        this.mToggleButton = (CustomToggleButton) findViewById(R.id.toggle);
        this.mRtvSubtitle = (RegularTextView) findViewById(R.id.rtv_subtitle);
        this.mLayoutRoot.setBackgroundResource(this.mBackgroundRes);
        this.mRtvValue.setMaxWidth(ScreenUtil.getScreenW() / 2);
        ViewGroup.LayoutParams layoutParams = this.mIvLeft.getLayoutParams();
        float f2 = this.mLeftIconSize;
        layoutParams.height = (int) f2;
        layoutParams.width = (int) f2;
        this.mIvLeft.setLayoutParams(layoutParams);
        if (this.mHasLeftIcon) {
            this.mIvLeft.setVisibility(0);
            Drawable drawable = this.mDrawableLeft;
            if (drawable != null) {
                this.mIvLeft.setImageDrawable(drawable);
            }
        } else {
            this.mIvLeft.setVisibility(8);
        }
        if (this.mHasRightIcon) {
            this.mIvRight.setVisibility(0);
            Drawable drawable2 = this.mDrawableRight;
            if (drawable2 != null) {
                this.mIvRight.setImageDrawable(drawable2);
            }
        } else {
            this.mIvRight.setVisibility(8);
        }
        this.mRtvTitle.setText(this.mTitle);
        this.mRtvTitle.setTextColor(this.mTitleColor);
        this.mRtvTitle.setTextSize(0, this.mTitleSize);
        if (this.mIsTitleBold) {
            this.mRtvTitle.setTypeface(Typeface.DEFAULT_BOLD);
        }
        setSubtitle(this.mSubtitle);
        this.mRtvValue.setText(this.mValue);
        this.mRtvValue.setTextColor(this.mValueColor);
        this.mRtvValue.setTextSize(0, this.mValueSize);
        if (this.mHasBottomDividerTwo) {
            this.mDivider.setVisibility(8);
            this.mDividerTwo.setVisibility(0);
        } else {
            this.mDivider.setVisibility(this.mHasBottomDivider ? 0 : 8);
        }
        if (this.mIsToggle) {
            this.mToggleButton.setVisibility(0);
            this.mIvRight.setVisibility(8);
            this.mToggleButton.setSwitchStatus(this.mIsToggleOn);
            return;
        }
        this.mToggleButton.setVisibility(8);
    }

    public void setSubtitle(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mRtvSubtitle.setVisibility(8);
        } else {
            this.mRtvSubtitle.setVisibility(0);
            this.mRtvSubtitle.setText(str);
        }
    }

    public void setTitle(int i) {
        if (i == 0) {
            return;
        }
        this.mTitle = this.mResources.getString(i);
        this.mRtvTitle.setText(i);
    }

    public void setTitle(String str) {
        this.mTitle = str;
        this.mRtvTitle.setText(str);
    }

    public void setTitleSize(float f2) {
        this.mRtvTitle.setTextSize(f2);
    }

    public void setValue(int i) {
        if (i == 0) {
            return;
        }
        this.mValue = this.mResources.getString(i);
        this.mRtvValue.setText(i);
    }

    public void setValue(String str) {
        this.mValue = str;
        this.mRtvValue.setText(str);
    }

    public String getValue() {
        return this.mRtvValue.getText().toString();
    }

    public void setOnSwitchListener(CustomToggleButton.OnSwitchListener onSwitchListener) {
        this.mToggleButton.setOnSwitchListener(onSwitchListener);
    }

    public void setSwitchStatus(boolean z) {
        this.mToggleButton.setSwitchStatus(z);
    }

    public boolean getSwitchStatus() {
        return this.mToggleButton.getSwitchStatus();
    }

    public void setSwitchEnable(boolean z) {
        this.mToggleButton.setTouchEnable(z);
    }

    public void setDrawableRightVisibility(int i) {
        this.mIvRight.setVisibility(i);
    }

    public void setDrawableRight(Drawable drawable) {
        this.mIvRight.setImageDrawable(drawable);
    }

    public ImageView getmIvLeft() {
        return this.mIvLeft;
    }

    public void setDrawableLeftVisibility(int i) {
        this.mIvLeft.setVisibility(i);
    }

    public void setDrawableRight(int i) {
        this.mIvRight.setImageResource(i);
    }

    public void setDrawableLeft(Drawable drawable) {
        this.mIvLeft.setImageDrawable(drawable);
    }

    public void setDrawableLeft(int i) {
        this.mIvLeft.setImageResource(i);
    }

    public void setHasBottomDivider(boolean z) {
        this.mDivider.setVisibility(z ? 0 : 8);
    }

    public void setBackground(int i) {
        if (i == 0) {
            return;
        }
        this.mLayoutRoot.setBackgroundResource(i);
    }

    public void setPromptDotAndTip(String str) {
        this.mRtvValue.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shape_tip_dot_red, 0, 0, 0);
        this.mRtvValue.setText(str);
    }

    public void cancelPromptDot() {
        this.mRtvValue.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        this.mRtvValue.setText((CharSequence) null);
    }

    public void setTitleColor(int i) {
        RegularTextView regularTextView = this.mRtvTitle;
        if (regularTextView != null) {
            regularTextView.setTextColor(i);
        }
    }

    public void setTitleSingleLine() {
        RegularTextView regularTextView = this.mRtvTitle;
        if (regularTextView != null) {
            regularTextView.setSingleLine();
        }
    }

    public void setDrawLeftPadding(int i) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mIvLeft.getLayoutParams();
        layoutParams.setMarginEnd(i);
        this.mIvLeft.setLayoutParams(layoutParams);
    }

    public void setRightToggle(boolean z) {
        if (z) {
            this.mToggleButton.setVisibility(0);
            this.mIvRight.setVisibility(8);
        } else {
            this.mToggleButton.setVisibility(8);
        }
    }
}