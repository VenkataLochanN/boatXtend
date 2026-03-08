package com.ido.life.module.user;

import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;

/* JADX INFO: loaded from: classes.dex */
public class CountDownTask implements Runnable {
    private TextView mBtCode;
    private final int mCount;

    public CountDownTask(TextView textView, int i) {
        this.mBtCode = textView;
        this.mCount = i;
    }

    public void start() {
        stop();
        CommonLogUtil.d("Test", "CountDownTask---start");
        this.mBtCode.setTag(Integer.valueOf(this.mCount + 1));
        this.mBtCode.setEnabled(false);
        this.mBtCode.setText(String.format(LanguageUtil.getLanguageText(R.string.account_getcode_after_seconds), Integer.valueOf(this.mCount)));
        this.mBtCode.post(this);
    }

    public void stop() {
        CommonLogUtil.d("Test", "CountDownTask---cancel");
        this.mBtCode.setEnabled(true);
        this.mBtCode.setText(LanguageUtil.getLanguageText(R.string.mine_get_verification_code));
        this.mBtCode.removeCallbacks(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        int iIntValue = ((Integer) this.mBtCode.getTag()).intValue() - 1;
        if (iIntValue <= 0) {
            this.mBtCode.setEnabled(true);
            this.mBtCode.setText(LanguageUtil.getLanguageText(R.string.register_verification_code_again));
            return;
        }
        this.mBtCode.setTag(Integer.valueOf(iIntValue));
        this.mBtCode.setEnabled(false);
        String str = String.format(LanguageUtil.getLanguageText(R.string.account_getcode_after_seconds), Integer.valueOf(iIntValue));
        getAgreementSpan(null, str);
        this.mBtCode.setText(getAgreementSpan(null, str));
        this.mBtCode.postDelayed(this, 1000L);
    }

    private SpannableString getAgreementSpan(SpannableString spannableString, String str) {
        if (spannableString == null) {
            spannableString = new SpannableString(str);
        }
        spannableString.setSpan(new ClickableSpan() { // from class: com.ido.life.module.user.CountDownTask.1
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                textPaint.setColor(ResourceUtil.getColor(R.color.color_027AFF));
            }
        }, 0, str.length(), 17);
        return spannableString;
    }
}