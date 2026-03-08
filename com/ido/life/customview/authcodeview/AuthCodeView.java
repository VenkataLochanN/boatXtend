package com.ido.life.customview.authcodeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.core.view.ViewCompat;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class AuthCodeView extends RelativeLayout {
    private LinearLayout containerEt;
    private PwdEditText et;
    private int[] mCodeArr;
    private int mCodeCount;
    private Drawable mEtBackgroundDrawableFocus;
    private Drawable mEtBackgroundDrawableNormal;
    private int mEtTextColor;
    private float mEtTextSize;
    private int mEtWidth;
    private InputListener mInputListener;
    private float mMargin;
    private TextView[] mTextViews;
    private final TextWatcherImpl mTextWatcherImpl;

    public interface InputListener {
        void onInputComplete(int[] iArr);

        void onInputted();
    }

    public AuthCodeView(Context context) {
        this(context, null);
    }

    public AuthCodeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AuthCodeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTextWatcherImpl = new TextWatcherImpl();
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        LayoutInflater.from(context).inflate(R.layout.layout_identifying_code, this);
        this.containerEt = (LinearLayout) findViewById(R.id.container_et);
        this.et = (PwdEditText) findViewById(R.id.et);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.ido.life.R.styleable.AuthCodeView, i, 0);
        this.mCodeCount = typedArrayObtainStyledAttributes.getInteger(3, 1);
        this.mEtWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, 42);
        this.mMargin = typedArrayObtainStyledAttributes.getDimension(2, context.getResources().getDimension(R.dimen.sw_dp_10));
        this.mEtTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, (int) sp2px(16.0f, context));
        this.mEtTextColor = typedArrayObtainStyledAttributes.getColor(4, ViewCompat.MEASURED_STATE_MASK);
        this.mEtBackgroundDrawableFocus = typedArrayObtainStyledAttributes.getDrawable(0);
        this.mEtBackgroundDrawableNormal = typedArrayObtainStyledAttributes.getDrawable(1);
        typedArrayObtainStyledAttributes.recycle();
        if (this.mEtBackgroundDrawableFocus == null) {
            this.mEtBackgroundDrawableFocus = context.getResources().getDrawable(R.drawable.bg_auth_code_focus);
        }
        if (this.mEtBackgroundDrawableNormal == null) {
            this.mEtBackgroundDrawableNormal = context.getResources().getDrawable(R.drawable.bg_auth_code_normal);
        }
        initUI();
    }

    private void initUI() {
        this.mCodeArr = new int[this.mCodeCount];
        initTextViews(getContext(), this.mCodeCount, this.mEtWidth, this.mEtTextSize, this.mEtTextColor);
        initEtContainer(this.mTextViews);
        setListener();
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            i2 = View.MeasureSpec.makeMeasureSpec((int) dp2px(50.0f, getContext()), BasicMeasure.EXACTLY);
        }
        super.onMeasure(i, i2);
    }

    private void initTextViews(Context context, int i, int i2, float f2, int i3) {
        this.et.setCursorVisible(false);
        this.et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i)});
        this.mTextViews = new TextView[i];
        for (int i4 = 0; i4 < this.mTextViews.length; i4++) {
            TextView textView = new TextView(context);
            textView.setTextSize(0, f2);
            textView.setTextColor(i3);
            textView.setWidth(i2);
            textView.setHeight(i2);
            if (i4 == 0) {
                textView.setBackgroundDrawable(this.mEtBackgroundDrawableFocus);
            } else {
                textView.setBackgroundDrawable(this.mEtBackgroundDrawableNormal);
            }
            textView.setGravity(17);
            textView.setFocusable(false);
            this.mTextViews[i4] = textView;
        }
    }

    private void initEtContainer(TextView[] textViewArr) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        for (TextView textView : textViewArr) {
            int i = this.mEtWidth;
            layoutParams.width = i;
            layoutParams.height = i;
            layoutParams.setMarginEnd((int) (this.mMargin / 2.0f));
            layoutParams.setMarginStart((int) (this.mMargin / 2.0f));
            textView.setLayoutParams(layoutParams);
            this.containerEt.addView(textView);
        }
    }

    private void setListener() {
        this.et.addTextChangedListener(this.mTextWatcherImpl);
        this.et.setOnKeyListener(new View.OnKeyListener() { // from class: com.ido.life.customview.authcodeview.AuthCodeView.1
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i != 67 || keyEvent.getAction() != 0) {
                    return false;
                }
                AuthCodeView.this.onKeyDelete();
                return true;
            }
        });
    }

    public void setInputEnable(boolean z) {
        this.et.setVisibility(z ? 0 : 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setText(String str) {
        int i = 0;
        while (true) {
            TextView[] textViewArr = this.mTextViews;
            if (i >= textViewArr.length) {
                return;
            }
            TextView textView = textViewArr[i];
            if (textView.getText().toString().trim().equals("")) {
                textView.setText(str);
                try {
                    this.mCodeArr[i] = Integer.parseInt(str);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                InputListener inputListener = this.mInputListener;
                if (inputListener != null) {
                    if (i == this.mTextViews.length - 1) {
                        inputListener.onInputComplete(this.mCodeArr);
                    }
                    this.mInputListener.onInputted();
                }
                textView.setBackgroundDrawable(this.mEtBackgroundDrawableNormal);
                if (i < this.mCodeCount - 1) {
                    this.mTextViews[i + 1].setBackgroundDrawable(this.mEtBackgroundDrawableFocus);
                    return;
                }
                return;
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onKeyDelete() {
        if (TextUtils.isEmpty(this.mTextViews[0].getText().toString().trim())) {
            return;
        }
        for (int length = this.mTextViews.length - 1; length >= 0; length--) {
            TextView textView = this.mTextViews[length];
            textView.setBackgroundDrawable(this.mEtBackgroundDrawableNormal);
            if (!textView.getText().toString().trim().equals("")) {
                textView.setText("");
                if (length <= this.mCodeCount - 1) {
                    textView.setBackgroundDrawable(this.mEtBackgroundDrawableFocus);
                    return;
                }
                return;
            }
        }
    }

    public String getInputContent() {
        StringBuffer stringBuffer = new StringBuffer();
        for (TextView textView : this.mTextViews) {
            stringBuffer.append(textView.getText().toString().trim());
        }
        return stringBuffer.toString();
    }

    public void clear() {
        int i = 0;
        while (true) {
            TextView[] textViewArr = this.mTextViews;
            if (i >= textViewArr.length) {
                return;
            }
            if (i == 0) {
                textViewArr[i].setBackgroundDrawable(this.mEtBackgroundDrawableFocus);
            } else {
                textViewArr[i].setBackgroundDrawable(this.mEtBackgroundDrawableNormal);
            }
            this.mTextViews[i].setText("");
            i++;
        }
    }

    public void setCodeCount(int i) {
        this.mCodeCount = i;
        this.et.removeTextChangedListener(this.mTextWatcherImpl);
        this.containerEt.removeAllViews();
        initUI();
    }

    public int getCodeCount() {
        return this.mCodeCount;
    }

    public EditText getEditText() {
        return this.et;
    }

    public void setInputListener(InputListener inputListener) {
        this.mInputListener = inputListener;
    }

    public float dp2px(float f2, Context context) {
        return TypedValue.applyDimension(1, f2, context.getResources().getDisplayMetrics());
    }

    public float sp2px(float f2, Context context) {
        return TypedValue.applyDimension(2, f2, context.getResources().getDisplayMetrics());
    }

    private class TextWatcherImpl implements TextWatcher {
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        private TextWatcherImpl() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            String string = editable.toString();
            if (TextUtils.isEmpty(string)) {
                return;
            }
            String[] strArrSplit = string.split("");
            for (int i = 0; i < strArrSplit.length && i <= AuthCodeView.this.mCodeCount; i++) {
                AuthCodeView.this.setText(strArrSplit[i]);
                AuthCodeView.this.et.setText("");
            }
        }
    }
}