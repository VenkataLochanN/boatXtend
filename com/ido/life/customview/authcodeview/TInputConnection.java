package com.ido.life.customview.authcodeview;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

/* JADX INFO: loaded from: classes2.dex */
public class TInputConnection extends InputConnectionWrapper {
    private BackspaceListener mBackspaceListener;

    public interface BackspaceListener {
        boolean onBackspace();
    }

    public TInputConnection(InputConnection inputConnection, boolean z) {
        super(inputConnection, z);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean deleteSurroundingText(int i, int i2) {
        BackspaceListener backspaceListener = this.mBackspaceListener;
        if (backspaceListener == null || !backspaceListener.onBackspace()) {
            return super.deleteSurroundingText(i, i2);
        }
        return true;
    }

    public void setBackspaceListener(BackspaceListener backspaceListener) {
        this.mBackspaceListener = backspaceListener;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean sendKeyEvent(KeyEvent keyEvent) {
        BackspaceListener backspaceListener;
        if (keyEvent.getKeyCode() == 67 && keyEvent.getAction() == 0 && (backspaceListener = this.mBackspaceListener) != null && backspaceListener.onBackspace()) {
            return true;
        }
        return super.sendKeyEvent(keyEvent);
    }
}