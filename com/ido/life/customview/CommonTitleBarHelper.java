package com.ido.life.customview;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.DipPixelUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CommonTitleBarHelper implements View.OnClickListener {
    private static final int DEFAULT_BG_RES = 2131099768;
    public static final int DEFAULT_LEFT_IMAGE = 0;
    private static final int DEFAULT_LEFT_IMG_RES = 2131623963;
    public static final int DEFAULT_MID_TITLECOLOR = 0;
    public static final int DEFAULT_MID_TITLESIZE = 0;
    public static final int DEFAULT_RIGHT_IMAGE = 0;
    private static final int DEFAULT_RIGHT_IMG_RES = 2131624476;
    public static final int LOACTION_ALL = 3;
    public static final int LOACTION_LEFT = 0;
    public static final int LOACTION_MID = 1;
    public static final int LOACTION_RIGHT = 2;
    public static final int NO_LEFT_IMAGE = -1;
    public static final int STYLE_ALL_TEXT = 3;
    public static final int STYLE_DEFAULT = 0;
    public static final int STYLE_LEFT_IMG_TXT_RIGHT_TEXT = 4;
    public static final int STYLE_LEFT_RIGHT_TEXT = 10;
    public static final int STYLE_RIGHT_IMG = 1;
    public static final int STYLE_RIGHT_TEXT = 2;
    public static int defaultTextSize = 18;
    public static final int leftId = 2131362882;
    public static final int leftImgId = 2131362883;
    public static final int leftStrId = 2131362884;
    public static final int rightId = 2131363338;
    public static final int rightImgId = 2131363339;
    public static final int rightStrId = 2131363340;
    public static final int titleId = 2131363699;
    Activity activity;
    private ImageView rightImg;
    private int titleBgRes;
    List<View> mids = new ArrayList();
    List<View> rights = new ArrayList();
    List<View> lefts = new ArrayList();
    private boolean hasBar = true;
    private float rightPadding = 10.0f;
    private float leftPadding = DipPixelUtil.dip2px(16.0f);
    private boolean isOnlyCancelShowing = false;
    private View.OnClickListener mOnLeftClickListener = null;
    private View.OnClickListener mOnLeftTextClickListener = null;
    private int currentStyle = 0;
    public boolean rightAnimation = true;
    private boolean isDelay = false;

    public boolean isHasBar() {
        return this.hasBar;
    }

    public void setHasBar(boolean z) {
        this.hasBar = z;
    }

    public void init(Activity activity) {
        this.activity = activity;
        this.rightPadding = activity.getResources().getDimension(R.dimen.sw_dp_16);
        this.leftPadding = activity.getResources().getDimension(R.dimen.sw_dp_16);
        this.hasBar = activity.findViewById(R.id.layout_left) != null;
        if (this.hasBar) {
            initLayout();
        }
    }

    private CommonTitleBarHelper initLayout() {
        return initLayout(0);
    }

    public CommonTitleBarHelper setTitle(int i) {
        return setTitle(this.activity.getResources().getString(i));
    }

    public CommonTitleBarHelper setTitle(String str) {
        if (this.hasBar) {
            ((TextView) this.activity.findViewById(R.id.titleId)).setText(str);
        }
        return this;
    }

    public void setTitleColor(int i) {
        if (this.hasBar) {
            ((TextView) this.activity.findViewById(R.id.titleId)).setTextColor(i);
        }
    }

    public CommonTitleBarHelper setRightImg(int i) {
        this.activity.findViewById(R.id.rightImgId).setBackgroundResource(i);
        return this;
    }

    public CommonTitleBarHelper setRightImgTint(ColorStateList colorStateList) {
        this.activity.findViewById(R.id.rightImgId).setBackgroundTintList(colorStateList);
        return this;
    }

    public CommonTitleBarHelper setRightVisible(boolean z) {
        LinearLayout titleLayoutRight;
        if (this.hasBar && (titleLayoutRight = getTitleLayoutRight(this.activity)) != null) {
            titleLayoutRight.setVisibility(z ? 0 : 4);
        }
        return this;
    }

    public CommonTitleBarHelper setLeftImg(int i) {
        this.activity.findViewById(R.id.leftImgId).setBackgroundResource(i);
        return this;
    }

    public CommonTitleBarHelper setLeftImgTint(ColorStateList colorStateList) {
        this.activity.findViewById(R.id.leftImgId).setBackgroundTintList(colorStateList);
        return this;
    }

    public CommonTitleBarHelper setLeftImgVisible(boolean z) {
        this.activity.findViewById(R.id.leftImgId).setVisibility(z ? 0 : 8);
        return this;
    }

    public CommonTitleBarHelper switchLeftImgAndTextVisibility(boolean z) {
        setLeftImgVisible(z);
        setLeftTextVisible(!z);
        checkLeftVisible();
        return this;
    }

    private void checkLeftVisible() {
        View viewFindViewById = this.activity.findViewById(R.id.leftStrId);
        View viewFindViewById2 = this.activity.findViewById(R.id.leftImgId);
        this.isOnlyCancelShowing = viewFindViewById != null && viewFindViewById.getVisibility() == 0 && (viewFindViewById2 == null || viewFindViewById2.getVisibility() != 0);
        CommonLogUtil.d("checkLeftVisible,   isOnlyCancelShowing = " + this.isOnlyCancelShowing);
    }

    public CommonTitleBarHelper setLeftTextVisible(boolean z) {
        View viewFindViewById;
        if (this.hasBar && (viewFindViewById = this.activity.findViewById(R.id.leftStrId)) != null) {
            viewFindViewById.setVisibility(z ? 0 : 8);
        }
        return this;
    }

    public CommonTitleBarHelper setRightEnable(boolean z) {
        if (this.hasBar) {
            View viewFindViewById = this.activity.findViewById(R.id.rightStrId);
            if (viewFindViewById != null) {
                viewFindViewById.setEnabled(z);
            }
            getTitleLayoutRight(this.activity).setEnabled(z);
        }
        return this;
    }

    public CommonTitleBarHelper setRightText(int i) {
        if (this.hasBar) {
            ((TextView) this.activity.findViewById(R.id.rightStrId)).setText(i);
        }
        return this;
    }

    public CommonTitleBarHelper setRightText(String str) {
        if (this.hasBar) {
            ((TextView) this.activity.findViewById(R.id.rightStrId)).setText(str);
        }
        return this;
    }

    public CommonTitleBarHelper setRightTextColor(int i) {
        if (this.hasBar) {
            ((TextView) this.activity.findViewById(R.id.rightStrId)).setTextColor(i);
        }
        return this;
    }

    public CommonTitleBarHelper setRightTextColor(ColorStateList colorStateList) {
        if (this.hasBar) {
            ((TextView) this.activity.findViewById(R.id.rightStrId)).setTextColor(colorStateList);
        }
        return this;
    }

    public CommonTitleBarHelper setLeftText(int i) {
        if (this.hasBar) {
            ((TextView) this.activity.findViewById(R.id.leftStrId)).setText(i);
        }
        return this;
    }

    public CommonTitleBarHelper setLeftText(String str) {
        if (this.hasBar) {
            ((TextView) this.activity.findViewById(R.id.leftStrId)).setText(str);
        }
        return this;
    }

    public CommonTitleBarHelper setLeftTextColor(int i) {
        if (this.hasBar) {
            ((TextView) this.activity.findViewById(R.id.leftStrId)).setTextColor(i);
        }
        return this;
    }

    public CommonTitleBarHelper setRightOnClick(View.OnClickListener onClickListener) {
        if (this.hasBar && this.currentStyle != 0) {
            getTitleLayoutRight(this.activity).setOnClickListener(onClickListener);
        }
        return this;
    }

    public CommonTitleBarHelper setLeftOnClick(View.OnClickListener onClickListener) {
        if (this.hasBar) {
            this.mOnLeftClickListener = onClickListener;
        }
        return this;
    }

    public CommonTitleBarHelper setLeftTextOnClick(View.OnClickListener onClickListener) {
        if (this.hasBar) {
            this.mOnLeftTextClickListener = onClickListener;
        }
        return this;
    }

    public CommonTitleBarHelper initLayout(int i) {
        this.rights.clear();
        this.lefts.clear();
        this.mids.clear();
        this.currentStyle = i;
        if (i == 0) {
            addLeftImageView();
            addMidTextView();
        } else if (i == 1) {
            addLeftImageView();
            addMidTextView();
            addRightImageView();
        } else if (i == 2) {
            addLeftImageView();
            addMidTextView();
            addRightTextView();
        } else if (i == 3) {
            addLeftTextView();
            addMidTextView();
            addRightTextView();
        } else if (i == 4) {
            addLeftImageView();
            addLeftTextView();
            addMidTextView();
            addRightTextView();
        }
        initBar();
        return this;
    }

    public CommonTitleBarHelper setRightTextPadding(int i) {
        this.rightPadding = i;
        return this;
    }

    public LinearLayout getTitleLayoutLeft(Activity activity) {
        return findViewById(activity, R.id.layout_left);
    }

    private LinearLayout findViewById(Activity activity, int i) {
        LinearLayout linearLayout = (LinearLayout) activity.findViewById(i);
        linearLayout.setVisibility(0);
        return linearLayout;
    }

    public LinearLayout getTitleLayoutMid(Activity activity) {
        return findViewById(activity, R.id.layout_mid);
    }

    public LinearLayout getTitleLayoutRight(Activity activity) {
        return findViewById(activity, R.id.layout_right);
    }

    public LinearLayout getTitleLayoutAll(Activity activity) {
        return findViewById(activity, R.id.layout_parent);
    }

    public void setLeftImageColorStateList(Activity activity, ColorStateList colorStateList) {
        ImageView imageView;
        if (colorStateList == null || activity == null || (imageView = (ImageView) activity.findViewById(R.id.leftImgId)) == null) {
            return;
        }
        imageView.setImageTintList(colorStateList);
    }

    public void setTitleLayoutBgcolor(Activity activity, int i, int i2) {
        LinearLayout titleLayoutLeft;
        if (i2 == 0) {
            titleLayoutLeft = getTitleLayoutLeft(activity);
        } else if (i2 == 1) {
            titleLayoutLeft = getTitleLayoutMid(activity);
        } else if (i2 == 2) {
            titleLayoutLeft = getTitleLayoutRight(activity);
        } else if (i2 == 3) {
            titleLayoutLeft = getTitleLayoutAll(activity);
        } else {
            titleLayoutLeft = getTitleLayoutAll(activity);
        }
        titleLayoutLeft.setBackgroundColor(i);
    }

    public void setTitleLayoutAllBgcolor(Activity activity, int i) {
        setTitleLayoutBgcolor(activity, i, 3);
    }

    private void addTitle(Activity activity, List<View> list, List<View> list2, List<View> list3) {
        LinearLayout titleLayoutLeft = getTitleLayoutLeft(activity);
        LinearLayout titleLayoutMid = getTitleLayoutMid(activity);
        LinearLayout titleLayoutRight = getTitleLayoutRight(activity);
        titleLayoutLeft.removeAllViews();
        titleLayoutMid.removeAllViews();
        titleLayoutRight.removeAllViews();
        if (list != null) {
            Iterator<View> it = list.iterator();
            while (it.hasNext()) {
                titleLayoutLeft.addView(it.next());
            }
        }
        if (list2 != null) {
            Iterator<View> it2 = list2.iterator();
            while (it2.hasNext()) {
                titleLayoutMid.addView(it2.next());
            }
        }
        if (list3 != null) {
            Iterator<View> it3 = list3.iterator();
            while (it3.hasNext()) {
                titleLayoutRight.addView(it3.next());
            }
        }
        setBarBackground(R.color.color_1E1E1E);
    }

    private void initBar() {
        LinearLayout titleLayoutLeft = getTitleLayoutLeft(this.activity);
        LinearLayout titleLayoutMid = getTitleLayoutMid(this.activity);
        LinearLayout titleLayoutRight = getTitleLayoutRight(this.activity);
        titleLayoutLeft.removeAllViews();
        titleLayoutMid.removeAllViews();
        titleLayoutRight.removeAllViews();
        List<View> list = this.lefts;
        if (list != null) {
            Iterator<View> it = list.iterator();
            while (it.hasNext()) {
                titleLayoutLeft.addView(it.next());
            }
        }
        List<View> list2 = this.mids;
        if (list2 != null) {
            Iterator<View> it2 = list2.iterator();
            while (it2.hasNext()) {
                titleLayoutMid.addView(it2.next());
            }
        }
        List<View> list3 = this.rights;
        if (list3 != null) {
            Iterator<View> it3 = list3.iterator();
            while (it3.hasNext()) {
                titleLayoutRight.addView(it3.next());
            }
        }
        if (this.titleBgRes == 0) {
            this.titleBgRes = R.color.color_1E1E1E;
        }
        setBarBackground(this.titleBgRes);
        checkLeftVisible();
        getTitleLayoutLeft(this.activity).setOnClickListener(this);
    }

    private ImageButton createImageButton(int i, int i2) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(20, 0, 0, 0);
        layoutParams.gravity = 19;
        ImageButton imageButton = new ImageButton(this.activity);
        imageButton.setLayoutParams(layoutParams);
        imageButton.setId(i);
        imageButton.setPadding(0, 0, 0, 0);
        if (i2 == 0) {
            imageButton.setImageResource(R.mipmap.arrow_left);
        } else if (i2 != -1) {
            imageButton.setImageResource(i2);
        }
        imageButton.setBackgroundColor(0);
        return imageButton;
    }

    public CommonTitleBarHelper setRightAnimation(final View.OnClickListener onClickListener, final boolean z, boolean z2) {
        this.rightImg = (ImageView) this.activity.findViewById(R.id.rightImgId);
        this.rightImg.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.customview.CommonTitleBarHelper.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (onClickListener != null && BLEManager.isConnected()) {
                    if (CommonTitleBarHelper.this.isAnimation(z)) {
                        CommonTitleBarHelper.this.setRightImg(R.mipmap.refresh);
                        CommonTitleBarHelper.this.rightImg.startAnimation(AnimationUtils.loadAnimation(CommonTitleBarHelper.this.activity, R.anim.anim_progress));
                    }
                    onClickListener.onClick(view);
                }
            }
        });
        return this;
    }

    public CommonTitleBarHelper setRightAnimationClick(final View.OnClickListener onClickListener, final boolean z, final boolean z2) {
        this.rightImg = (ImageView) this.activity.findViewById(R.id.rightImgId);
        this.rightImg.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.customview.-$$Lambda$CommonTitleBarHelper$N4YIQDTrzQ9kWaAbFl_nAO1BNtE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setRightAnimationClick$0$CommonTitleBarHelper(onClickListener, z2, z, view);
            }
        });
        return this;
    }

    public /* synthetic */ void lambda$setRightAnimationClick$0$CommonTitleBarHelper(View.OnClickListener onClickListener, boolean z, boolean z2, View view) {
        if (onClickListener == null) {
            return;
        }
        if (z && !BLEManager.isConnected()) {
            NormalToast.showToast(this.activity.getString(R.string.sport_device_unbind), 1);
            return;
        }
        if (isAnimation(z2)) {
            setRightImg(R.mipmap.refresh);
            this.rightImg.startAnimation(AnimationUtils.loadAnimation(this.activity, R.anim.anim_progress));
        }
        onClickListener.onClick(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAnimation(boolean z) {
        return z && this.rightAnimation;
    }

    public void closeAnimation() {
        this.rightImg = (ImageView) this.activity.findViewById(R.id.rightImgId);
        ImageView imageView = this.rightImg;
        if (imageView != null) {
            imageView.clearAnimation();
            this.rightImg.setBackgroundResource(R.mipmap.icon_save);
        }
    }

    public CommonTitleBarHelper setBarBackground(int i) {
        this.titleBgRes = i;
        LinearLayout linearLayoutFindViewById = findViewById(this.activity, R.id.layout_parent);
        if (linearLayoutFindViewById != null) {
            linearLayoutFindViewById.setBackgroundResource(i);
        }
        return this;
    }

    private ImageView createImageView(Activity activity, int i, int i2, int i3) {
        return createImageView(activity, i, i2, i3, 0, 0, 0, 0);
    }

    private ImageView createImageView(Activity activity, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        ImageView imageView = new ImageView(activity);
        imageView.setLayoutParams(layoutParams);
        imageView.setId(i);
        imageView.setPadding(i4, i5, i6, i7);
        if (i2 == 0) {
            imageView.setBackgroundResource(i3);
        } else {
            imageView.setBackgroundResource(i2);
        }
        return imageView;
    }

    private TextView createTextView(Activity activity, String str, int i, int i2, int i3) {
        return createTextView(str, i, i2, i3);
    }

    private TextView createTextView(String str, float f2, int i, int i2) {
        return createTextView(str, f2, i, i2, 0.0f, 0.0f, true);
    }

    private TextView createTextView(String str, float f2, int i, int i2, float f3, float f4, boolean z) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        TextView textView = new TextView(this.activity);
        textView.getPaint().setFakeBoldText(z);
        textView.setText(str);
        textView.setId(i2);
        textView.setSingleLine(true);
        textView.setPadding((int) f3, 0, (int) f4, 0);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setLayoutParams(layoutParams);
        if (f2 == 0.0f) {
            textView.setTextSize(defaultTextSize);
        } else {
            textView.setTextSize(0, f2);
        }
        if (i == 0) {
            textView.setTextColor(this.activity.getResources().getColor(R.color.white));
        } else {
            textView.setTextColor(this.activity.getResources().getColor(i));
        }
        return textView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.isOnlyCancelShowing) {
            View.OnClickListener onClickListener = this.mOnLeftTextClickListener;
            if (onClickListener != null) {
                onClickListener.onClick(view);
                return;
            }
            return;
        }
        View.OnClickListener onClickListener2 = this.mOnLeftClickListener;
        if (onClickListener2 != null) {
            onClickListener2.onClick(view);
        }
    }

    static class ClickFinished implements View.OnClickListener {
        private Activity activity;

        public ClickFinished(Activity activity) {
            this.activity = activity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.activity.finish();
        }
    }

    private void addLeftImageView() {
        ImageView imageViewCreateImageView = createImageView(this.activity, R.id.leftImgId, R.mipmap.arrow_left, R.mipmap.arrow_left);
        this.mOnLeftClickListener = new ClickFinished(this.activity);
        this.lefts.add(imageViewCreateImageView);
    }

    private void addRightImageView() {
        this.rights.add(createImageView(this.activity, R.id.rightImgId, R.mipmap.icon_save, R.mipmap.icon_save, 10, 10, 10, 10));
    }

    private void addMidTextView() {
        this.mids.add(createTextView("", this.activity.getResources().getDimension(R.dimen.size18sp), R.color.white, R.id.titleId));
    }

    private void addRightTextView() {
        this.rights.add(createTextView("", this.activity.getResources().getDimension(R.dimen.size16sp), R.color.white, R.id.rightStrId, 0.0f, this.rightPadding, false));
    }

    private void addLeftTextView() {
        this.lefts.add(createTextView("", this.activity.getResources().getDimension(R.dimen.size16sp), R.color.white, R.id.leftStrId, this.leftPadding, 0.0f, false));
    }

    public class CloseAnimator {
        private ImageView view;

        public CloseAnimator() {
        }

        public void close() {
            ImageView imageView = this.view;
            if (imageView != null) {
                imageView.clearAnimation();
                this.view.setImageResource(R.mipmap.icon_save);
            }
        }
    }
}