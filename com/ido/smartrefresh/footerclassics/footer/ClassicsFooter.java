package com.ido.smartrefresh.footerclassics.footer;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ido.smartrefresh.R;
import com.ido.smartrefresh.drawablepaint.ProgressDrawable;
import com.ido.smartrefresh.footerclassics.classics.ArrowDrawable;
import com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract;
import com.ido.smartrefresh.layoutkernel.api.RefreshFooter;
import com.ido.smartrefresh.layoutkernel.api.RefreshLayout;
import com.ido.smartrefresh.layoutkernel.constant.RefreshState;
import com.ido.smartrefresh.layoutkernel.constant.SpinnerStyle;
import com.ido.smartrefresh.layoutkernel.util.SmartUtil;

/* JADX INFO: loaded from: classes3.dex */
public class ClassicsFooter extends ClassicsAbstract<ClassicsFooter> implements RefreshFooter {
    public static String REFRESH_FOOTER_FAILED;
    public static String REFRESH_FOOTER_FINISH;
    public static String REFRESH_FOOTER_LOADING;
    public static String REFRESH_FOOTER_NOTHING;
    public static String REFRESH_FOOTER_PULLING;
    public static String REFRESH_FOOTER_REFRESHING;
    public static String REFRESH_FOOTER_RELEASE;
    protected boolean mNoMoreData;
    protected String mTextFailed;
    protected String mTextFinish;
    protected String mTextLoading;
    protected String mTextNothing;
    protected String mTextPulling;
    protected String mTextRefreshing;
    protected String mTextRelease;

    public ClassicsFooter(Context context) {
        this(context, null);
    }

    public ClassicsFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.mNoMoreData = false;
        View.inflate(context, R.layout.srl_classics_footer, this);
        ImageView imageView = (ImageView) findViewById(R.id.srl_classics_arrow);
        this.mArrowView = imageView;
        ImageView imageView2 = (ImageView) findViewById(R.id.srl_classics_progress);
        this.mProgressView = imageView2;
        this.mTitleText = (TextView) findViewById(R.id.srl_classics_title);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ClassicsFooter);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) imageView2.getLayoutParams();
        layoutParams2.rightMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ClassicsFooter_srlDrawableMarginRight, SmartUtil.dp2px(20.0f));
        layoutParams.rightMargin = layoutParams2.rightMargin;
        layoutParams.width = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableArrowSize, layoutParams.width);
        layoutParams.height = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableArrowSize, layoutParams.height);
        layoutParams2.width = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableProgressSize, layoutParams2.width);
        layoutParams2.height = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableProgressSize, layoutParams2.height);
        layoutParams.width = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableSize, layoutParams.width);
        layoutParams.height = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableSize, layoutParams.height);
        layoutParams2.width = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableSize, layoutParams2.width);
        layoutParams2.height = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableSize, layoutParams2.height);
        this.mFinishDuration = typedArrayObtainStyledAttributes.getInt(R.styleable.ClassicsFooter_srlFinishDuration, this.mFinishDuration);
        this.mSpinnerStyle = SpinnerStyle.values[typedArrayObtainStyledAttributes.getInt(R.styleable.ClassicsFooter_srlClassicsSpinnerStyle, this.mSpinnerStyle.ordinal)];
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlDrawableArrow)) {
            this.mArrowView.setImageDrawable(typedArrayObtainStyledAttributes.getDrawable(R.styleable.ClassicsFooter_srlDrawableArrow));
        } else if (this.mArrowView.getDrawable() == null) {
            this.mArrowDrawable = new ArrowDrawable();
            this.mArrowDrawable.setColor(-10066330);
            this.mArrowView.setImageDrawable(this.mArrowDrawable);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlDrawableProgress)) {
            this.mProgressView.setImageDrawable(typedArrayObtainStyledAttributes.getDrawable(R.styleable.ClassicsFooter_srlDrawableProgress));
        } else if (this.mProgressView.getDrawable() == null) {
            this.mProgressDrawable = new ProgressDrawable();
            this.mProgressDrawable.setColor(-10066330);
            this.mProgressView.setImageDrawable(this.mProgressDrawable);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlTextSizeTitle)) {
            this.mTitleText.setTextSize(0, typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ClassicsFooter_srlTextSizeTitle, SmartUtil.dp2px(16.0f)));
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlPrimaryColor)) {
            super.setPrimaryColor(typedArrayObtainStyledAttributes.getColor(R.styleable.ClassicsFooter_srlPrimaryColor, 0));
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlAccentColor)) {
            super.setAccentColor(typedArrayObtainStyledAttributes.getColor(R.styleable.ClassicsFooter_srlAccentColor, 0));
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlTextPulling)) {
            this.mTextPulling = typedArrayObtainStyledAttributes.getString(R.styleable.ClassicsFooter_srlTextPulling);
        } else {
            String str = REFRESH_FOOTER_PULLING;
            if (str != null) {
                this.mTextPulling = str;
            } else {
                this.mTextPulling = context.getString(R.string.srl_footer_pulling);
            }
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlTextRelease)) {
            this.mTextRelease = typedArrayObtainStyledAttributes.getString(R.styleable.ClassicsFooter_srlTextRelease);
        } else {
            String str2 = REFRESH_FOOTER_RELEASE;
            if (str2 != null) {
                this.mTextRelease = str2;
            } else {
                this.mTextRelease = context.getString(R.string.srl_footer_release);
            }
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlTextLoading)) {
            this.mTextLoading = typedArrayObtainStyledAttributes.getString(R.styleable.ClassicsFooter_srlTextLoading);
        } else {
            String str3 = REFRESH_FOOTER_LOADING;
            if (str3 != null) {
                this.mTextLoading = str3;
            } else {
                this.mTextLoading = context.getString(R.string.srl_footer_loading);
            }
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlTextRefreshing)) {
            this.mTextRefreshing = typedArrayObtainStyledAttributes.getString(R.styleable.ClassicsFooter_srlTextRefreshing);
        } else {
            String str4 = REFRESH_FOOTER_REFRESHING;
            if (str4 != null) {
                this.mTextRefreshing = str4;
            } else {
                this.mTextRefreshing = context.getString(R.string.srl_footer_refreshing);
            }
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlTextFinish)) {
            this.mTextFinish = typedArrayObtainStyledAttributes.getString(R.styleable.ClassicsFooter_srlTextFinish);
        } else {
            String str5 = REFRESH_FOOTER_FINISH;
            if (str5 != null) {
                this.mTextFinish = str5;
            } else {
                this.mTextFinish = context.getString(R.string.srl_footer_finish);
            }
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlTextFailed)) {
            this.mTextFailed = typedArrayObtainStyledAttributes.getString(R.styleable.ClassicsFooter_srlTextFailed);
        } else {
            String str6 = REFRESH_FOOTER_FAILED;
            if (str6 != null) {
                this.mTextFailed = str6;
            } else {
                this.mTextFailed = context.getString(R.string.srl_footer_failed);
            }
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlTextNothing)) {
            this.mTextNothing = typedArrayObtainStyledAttributes.getString(R.styleable.ClassicsFooter_srlTextNothing);
        } else {
            String str7 = REFRESH_FOOTER_NOTHING;
            if (str7 != null) {
                this.mTextNothing = str7;
            } else {
                this.mTextNothing = context.getString(R.string.srl_footer_nothing);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        imageView2.animate().setInterpolator(null);
        this.mTitleText.setText(isInEditMode() ? this.mTextLoading : this.mTextPulling);
        if (isInEditMode()) {
            imageView.setVisibility(8);
        } else {
            imageView2.setVisibility(8);
        }
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract, com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public int onFinish(RefreshLayout refreshLayout, boolean z) {
        super.onFinish(refreshLayout, z);
        if (this.mNoMoreData) {
            return 0;
        }
        this.mTitleText.setText(z ? this.mTextFinish : this.mTextFailed);
        return this.mFinishDuration;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract, com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    @Deprecated
    public void setPrimaryColors(int... iArr) {
        if (this.mSpinnerStyle == SpinnerStyle.FixedBehind) {
            super.setPrimaryColors(iArr);
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshFooter
    public boolean setNoMoreData(boolean z) {
        if (this.mNoMoreData == z) {
            return true;
        }
        this.mNoMoreData = z;
        ImageView imageView = this.mArrowView;
        if (z) {
            this.mTitleText.setText(this.mTextNothing);
            imageView.setVisibility(8);
            return true;
        }
        this.mTitleText.setText(this.mTextPulling);
        imageView.setVisibility(0);
        return true;
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.listener.OnStateChangedListener
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState refreshState, RefreshState refreshState2) {
        ImageView imageView = this.mArrowView;
        if (this.mNoMoreData) {
            return;
        }
        switch (refreshState2) {
            case None:
                imageView.setVisibility(0);
                break;
            case PullUpToLoad:
                break;
            case Loading:
            case LoadReleased:
                imageView.setVisibility(8);
                this.mTitleText.setText(this.mTextLoading);
                return;
            case ReleaseToLoad:
                this.mTitleText.setText(this.mTextRelease);
                imageView.animate().rotation(0.0f);
                return;
            case Refreshing:
                this.mTitleText.setText(this.mTextRefreshing);
                imageView.setVisibility(8);
                return;
            default:
                return;
        }
        this.mTitleText.setText(this.mTextPulling);
        imageView.animate().rotation(180.0f);
    }
}