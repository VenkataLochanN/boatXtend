package com.ido.life.customview.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.smartrefresh.drawablepaint.ProgressDrawable;
import com.ido.smartrefresh.footerclassics.classics.ArrowDrawable;
import com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract;
import com.ido.smartrefresh.layout.api.RefreshFooter;
import com.ido.smartrefresh.layoutkernel.api.RefreshLayout;
import com.ido.smartrefresh.layoutkernel.constant.RefreshState;
import com.ido.smartrefresh.layoutkernel.constant.SpinnerStyle;
import com.ido.smartrefresh.layoutkernel.util.SmartUtil;

/* JADX INFO: loaded from: classes2.dex */
public class CommRefreshFooter extends ClassicsAbstract<CommRefreshFooter> implements RefreshFooter {
    public static String REFRESH_HEADER_FAILED;
    public static String REFRESH_HEADER_FINISH;
    public static String REFRESH_HEADER_LOADING;
    public static String REFRESH_HEADER_PULLING;
    public static String REFRESH_HEADER_REFRESHING;
    public static String REFRESH_HEADER_RELEASE;
    public static String REFRESH_HEADER_SECONDARY;
    protected boolean mEnableLastTime;
    protected String mTextFailed;
    protected String mTextFinish;
    protected String mTextLoading;
    protected String mTextPulling;
    protected String mTextRefreshing;
    protected String mTextRelease;
    protected String mTextSecondary;

    public CommRefreshFooter(Context context) {
        this(context, null);
    }

    public CommRefreshFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.mEnableLastTime = true;
        View.inflate(context, R.layout.layout_refresh_header, this);
        ImageView imageView = (ImageView) findViewById(R.id.srl_classics_arrow);
        this.mArrowView = imageView;
        ImageView imageView2 = (ImageView) findViewById(R.id.srl_classics_progress);
        this.mProgressView = imageView2;
        this.mTitleText = (TextView) findViewById(R.id.srl_classics_title);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.ido.life.R.styleable.ClassicsHeader);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) imageView2.getLayoutParams();
        new LinearLayout.LayoutParams(-2, -2).topMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(20, SmartUtil.dp2px(0.0f));
        layoutParams2.rightMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, SmartUtil.dp2px(20.0f));
        layoutParams.rightMargin = layoutParams2.rightMargin;
        layoutParams.width = typedArrayObtainStyledAttributes.getLayoutDimension(3, layoutParams.width);
        layoutParams.height = typedArrayObtainStyledAttributes.getLayoutDimension(3, layoutParams.height);
        layoutParams2.width = typedArrayObtainStyledAttributes.getLayoutDimension(6, layoutParams2.width);
        layoutParams2.height = typedArrayObtainStyledAttributes.getLayoutDimension(6, layoutParams2.height);
        layoutParams.width = typedArrayObtainStyledAttributes.getLayoutDimension(7, layoutParams.width);
        layoutParams.height = typedArrayObtainStyledAttributes.getLayoutDimension(7, layoutParams.height);
        layoutParams2.width = typedArrayObtainStyledAttributes.getLayoutDimension(7, layoutParams2.width);
        layoutParams2.height = typedArrayObtainStyledAttributes.getLayoutDimension(7, layoutParams2.height);
        this.mFinishDuration = typedArrayObtainStyledAttributes.getInt(9, this.mFinishDuration);
        this.mEnableLastTime = typedArrayObtainStyledAttributes.getBoolean(8, this.mEnableLastTime);
        this.mSpinnerStyle = SpinnerStyle.values[typedArrayObtainStyledAttributes.getInt(1, this.mSpinnerStyle.ordinal)];
        if (typedArrayObtainStyledAttributes.hasValue(2)) {
            this.mArrowView.setImageDrawable(typedArrayObtainStyledAttributes.getDrawable(2));
        } else if (this.mArrowView.getDrawable() == null) {
            this.mArrowDrawable = new ArrowDrawable();
            this.mArrowDrawable.setColor(-10066330);
            this.mArrowView.setImageDrawable(this.mArrowDrawable);
        }
        if (typedArrayObtainStyledAttributes.hasValue(5)) {
            this.mProgressView.setImageDrawable(typedArrayObtainStyledAttributes.getDrawable(5));
        } else if (this.mProgressView.getDrawable() == null) {
            this.mProgressDrawable = new ProgressDrawable();
            this.mProgressDrawable.setColor(-10066330);
            this.mProgressView.setImageDrawable(this.mProgressDrawable);
        }
        if (typedArrayObtainStyledAttributes.hasValue(19)) {
            this.mTitleText.setTextSize(0, typedArrayObtainStyledAttributes.getDimensionPixelSize(19, SmartUtil.dp2px(16.0f)));
        }
        if (typedArrayObtainStyledAttributes.hasValue(10)) {
            super.setPrimaryColor(typedArrayObtainStyledAttributes.getColor(10, 0));
        }
        if (typedArrayObtainStyledAttributes.hasValue(0)) {
            setAccentColor(typedArrayObtainStyledAttributes.getColor(0, 0));
        }
        if (typedArrayObtainStyledAttributes.hasValue(14)) {
            this.mTextPulling = typedArrayObtainStyledAttributes.getString(14);
        } else {
            String str = REFRESH_HEADER_PULLING;
            if (str != null) {
                this.mTextPulling = str;
            } else {
                this.mTextPulling = context.getString(R.string.srl_header_pulling);
            }
        }
        if (typedArrayObtainStyledAttributes.hasValue(13)) {
            this.mTextLoading = typedArrayObtainStyledAttributes.getString(13);
        } else {
            String str2 = REFRESH_HEADER_LOADING;
            if (str2 != null) {
                this.mTextLoading = str2;
            } else {
                this.mTextLoading = context.getString(R.string.srl_header_loading);
            }
        }
        if (typedArrayObtainStyledAttributes.hasValue(16)) {
            this.mTextRelease = typedArrayObtainStyledAttributes.getString(16);
        } else {
            String str3 = REFRESH_HEADER_RELEASE;
            if (str3 != null) {
                this.mTextRelease = str3;
            } else {
                this.mTextRelease = context.getString(R.string.srl_header_release);
            }
        }
        if (typedArrayObtainStyledAttributes.hasValue(12)) {
            this.mTextFinish = typedArrayObtainStyledAttributes.getString(12);
        } else {
            String str4 = REFRESH_HEADER_FINISH;
            if (str4 != null) {
                this.mTextFinish = str4;
            } else {
                this.mTextFinish = context.getString(R.string.srl_header_finish);
            }
        }
        if (typedArrayObtainStyledAttributes.hasValue(11)) {
            this.mTextFailed = typedArrayObtainStyledAttributes.getString(11);
        } else {
            String str5 = REFRESH_HEADER_FAILED;
            if (str5 != null) {
                this.mTextFailed = str5;
            } else {
                this.mTextFailed = context.getString(R.string.srl_header_failed);
            }
        }
        if (typedArrayObtainStyledAttributes.hasValue(17)) {
            this.mTextSecondary = typedArrayObtainStyledAttributes.getString(17);
        } else {
            String str6 = REFRESH_HEADER_SECONDARY;
            if (str6 != null) {
                this.mTextSecondary = str6;
            } else {
                this.mTextSecondary = context.getString(R.string.srl_header_secondary);
            }
        }
        if (typedArrayObtainStyledAttributes.hasValue(15)) {
            this.mTextRefreshing = typedArrayObtainStyledAttributes.getString(15);
        } else {
            String str7 = REFRESH_HEADER_REFRESHING;
            if (str7 != null) {
                this.mTextRefreshing = str7;
            } else {
                this.mTextRefreshing = context.getString(R.string.srl_header_refreshing);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        imageView2.animate().setInterpolator(null);
        this.mTitleText.setText(isInEditMode() ? this.mTextRefreshing : this.mTextPulling);
        if (isInEditMode()) {
            imageView.setVisibility(8);
        } else {
            imageView2.setVisibility(8);
        }
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract, com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public int onFinish(RefreshLayout refreshLayout, boolean z) {
        if (z) {
            this.mTitleText.setText(this.mTextFinish);
        } else {
            this.mTitleText.setText(this.mTextFailed);
        }
        return super.onFinish(refreshLayout, z);
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.listener.OnStateChangedListener
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState refreshState, RefreshState refreshState2) {
        ImageView imageView = this.mArrowView;
        switch (refreshState2) {
            case PullDownToRefresh:
                this.mTitleText.setText(this.mTextPulling);
                imageView.setVisibility(0);
                imageView.animate().rotation(0.0f);
                break;
            case Refreshing:
            case RefreshReleased:
                this.mTitleText.setText(this.mTextRefreshing);
                imageView.setVisibility(8);
                break;
            case ReleaseToRefresh:
                this.mTitleText.setText(this.mTextRelease);
                imageView.animate().rotation(180.0f);
                break;
            case LoadFinish:
                this.mTitleText.setText("加载完成");
                imageView.animate().rotation(0.0f);
                break;
            case Loading:
                imageView.setVisibility(8);
                this.mTitleText.setText(this.mTextLoading);
                break;
        }
    }

    public void setTextPulling(String str) {
        this.mTextPulling = str;
    }

    public void setTextRefreshing(String str) {
        this.mTextRefreshing = str;
        this.mTitleText.setText(str);
    }

    public void setTextFinish(String str) {
        this.mTextFinish = str;
        this.mTextRefreshing = getResources().getString(R.string.device_connecting);
    }

    public void setTextFinishWithOutRefreshing(String str) {
        this.mTextFinish = str;
    }

    public void setTextFailed(String str) {
        this.mTextFailed = str;
        this.mTextRefreshing = getResources().getString(R.string.device_connecting);
    }

    public void setTextFailedWithOutRefreshing(String str) {
        this.mTextFailed = str;
    }

    public void setTextRelease(String str) {
        this.mTextRelease = str;
    }

    public void setProgressViewVisible(int i) {
        if (this.mProgressView != null) {
            this.mProgressView.setVisibility(i);
        }
    }
}