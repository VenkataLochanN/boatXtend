package com.ido.life.customview.authcodeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.appcompat.widget.AppCompatEditText;
import com.ido.life.customview.authcodeview.TInputConnection;

/* JADX INFO: loaded from: classes2.dex */
public class PwdEditText extends AppCompatEditText {
    private TInputConnection inputConnection;

    public PwdEditText(Context context) {
        super(context);
        init();
    }

    public PwdEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PwdEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.inputConnection = new TInputConnection(null, true);
    }

    @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        this.inputConnection.setTarget(super.onCreateInputConnection(editorInfo));
        return this.inputConnection;
    }

    public void setBackSpaceListener(TInputConnection.BackspaceListener backspaceListener) {
        this.inputConnection.setBackspaceListener(backspaceListener);
    }
}