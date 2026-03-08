package com.ido.life.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.core.app.NotificationCompat;
import com.boat.Xtend.two.R;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReminderSelectView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001 B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u0016\u001a\u00020\tJ\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\tJ\u0006\u0010\u001c\u001a\u00020\u001aJ\u0006\u0010\u001d\u001a\u00020\u001aJ\u0006\u0010\u001e\u001a\u00020\u001aJ\u000e\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\fR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/ido/life/customview/ReminderSelectView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/ido/life/customview/ReminderSelectView$OnReminderChangedListener;", "rbDeny", "Landroid/widget/RadioButton;", "rbPermit", "rbSilence", "rbs", "Landroid/util/SparseArray;", "reminderStatus", "rgSelector", "Landroid/widget/RadioGroup;", "getReminderStatus", "isDeny", "", "select", "", NotificationCompat.CATEGORY_STATUS, "selectDeny", "selectPermit", "selectSilence", "setOnReminderChangedListener", "OnReminderChangedListener", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ReminderSelectView extends FrameLayout {
    private HashMap _$_findViewCache;
    private OnReminderChangedListener listener;
    private RadioButton rbDeny;
    private RadioButton rbPermit;
    private RadioButton rbSilence;
    private SparseArray<RadioButton> rbs;
    private int reminderStatus;
    private RadioGroup rgSelector;

    /* JADX INFO: compiled from: ReminderSelectView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/ido/life/customview/ReminderSelectView$OnReminderChangedListener;", "", "onReminderChanged", "", "reminderStatus", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface OnReminderChangedListener {
        void onReminderChanged(int reminderStatus);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReminderSelectView(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.rbs = new SparseArray<>();
        this.reminderStatus = 1;
        View viewInflate = FrameLayout.inflate(getContext(), R.layout.layout_reminder_select, this);
        this.rgSelector = (RadioGroup) viewInflate.findViewById(R.id.rgSelector);
        this.rbPermit = (RadioButton) viewInflate.findViewById(R.id.rbPermit);
        this.rbSilence = (RadioButton) viewInflate.findViewById(R.id.rbSilence);
        this.rbDeny = (RadioButton) viewInflate.findViewById(R.id.rbDeny);
        this.rbs.put(1, this.rbPermit);
        this.rbs.put(2, this.rbSilence);
        this.rbs.put(3, this.rbDeny);
        RadioGroup radioGroup = this.rgSelector;
        if (radioGroup != null) {
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.ido.life.customview.ReminderSelectView.1
                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public final void onCheckedChanged(RadioGroup radioGroup2, int i) {
                    ReminderSelectView.this.reminderStatus = i == R.id.rbPermit ? 1 : i == R.id.rbSilence ? 2 : 3;
                    OnReminderChangedListener onReminderChangedListener = ReminderSelectView.this.listener;
                    if (onReminderChangedListener != null) {
                        onReminderChangedListener.onReminderChanged(ReminderSelectView.this.reminderStatus);
                    }
                }
            });
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReminderSelectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.rbs = new SparseArray<>();
        this.reminderStatus = 1;
        View viewInflate = FrameLayout.inflate(getContext(), R.layout.layout_reminder_select, this);
        this.rgSelector = (RadioGroup) viewInflate.findViewById(R.id.rgSelector);
        this.rbPermit = (RadioButton) viewInflate.findViewById(R.id.rbPermit);
        this.rbSilence = (RadioButton) viewInflate.findViewById(R.id.rbSilence);
        this.rbDeny = (RadioButton) viewInflate.findViewById(R.id.rbDeny);
        this.rbs.put(1, this.rbPermit);
        this.rbs.put(2, this.rbSilence);
        this.rbs.put(3, this.rbDeny);
        RadioGroup radioGroup = this.rgSelector;
        if (radioGroup != null) {
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.ido.life.customview.ReminderSelectView.1
                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public final void onCheckedChanged(RadioGroup radioGroup2, int i) {
                    ReminderSelectView.this.reminderStatus = i == R.id.rbPermit ? 1 : i == R.id.rbSilence ? 2 : 3;
                    OnReminderChangedListener onReminderChangedListener = ReminderSelectView.this.listener;
                    if (onReminderChangedListener != null) {
                        onReminderChangedListener.onReminderChanged(ReminderSelectView.this.reminderStatus);
                    }
                }
            });
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReminderSelectView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.rbs = new SparseArray<>();
        this.reminderStatus = 1;
        View viewInflate = FrameLayout.inflate(getContext(), R.layout.layout_reminder_select, this);
        this.rgSelector = (RadioGroup) viewInflate.findViewById(R.id.rgSelector);
        this.rbPermit = (RadioButton) viewInflate.findViewById(R.id.rbPermit);
        this.rbSilence = (RadioButton) viewInflate.findViewById(R.id.rbSilence);
        this.rbDeny = (RadioButton) viewInflate.findViewById(R.id.rbDeny);
        this.rbs.put(1, this.rbPermit);
        this.rbs.put(2, this.rbSilence);
        this.rbs.put(3, this.rbDeny);
        RadioGroup radioGroup = this.rgSelector;
        if (radioGroup != null) {
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.ido.life.customview.ReminderSelectView.1
                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public final void onCheckedChanged(RadioGroup radioGroup2, int i2) {
                    ReminderSelectView.this.reminderStatus = i2 == R.id.rbPermit ? 1 : i2 == R.id.rbSilence ? 2 : 3;
                    OnReminderChangedListener onReminderChangedListener = ReminderSelectView.this.listener;
                    if (onReminderChangedListener != null) {
                        onReminderChangedListener.onReminderChanged(ReminderSelectView.this.reminderStatus);
                    }
                }
            });
        }
    }

    public final void select(int status) {
        if (status == 1) {
            selectPermit();
        } else if (status == 2) {
            selectSilence();
        } else {
            selectDeny();
        }
    }

    public final boolean isDeny() {
        return this.reminderStatus == 3;
    }

    public final void selectPermit() {
        int size = this.rbs.size();
        int i = 0;
        while (true) {
            boolean z = true;
            if (i < size) {
                int iKeyAt = this.rbs.keyAt(i);
                RadioButton radioButton = this.rbs.get(iKeyAt);
                Intrinsics.checkExpressionValueIsNotNull(radioButton, "rbs[key]");
                RadioButton radioButton2 = radioButton;
                if (iKeyAt != 1) {
                    z = false;
                }
                radioButton2.setChecked(z);
                i++;
            } else {
                this.reminderStatus = 1;
                return;
            }
        }
    }

    public final void selectDeny() {
        int size = this.rbs.size();
        for (int i = 0; i < size; i++) {
            int iKeyAt = this.rbs.keyAt(i);
            RadioButton radioButton = this.rbs.get(iKeyAt);
            Intrinsics.checkExpressionValueIsNotNull(radioButton, "rbs[key]");
            radioButton.setChecked(iKeyAt == 3);
        }
        this.reminderStatus = 3;
    }

    public final void selectSilence() {
        int size = this.rbs.size();
        for (int i = 0; i < size; i++) {
            int iKeyAt = this.rbs.keyAt(i);
            RadioButton radioButton = this.rbs.get(iKeyAt);
            Intrinsics.checkExpressionValueIsNotNull(radioButton, "rbs[key]");
            radioButton.setChecked(iKeyAt == 2);
        }
        this.reminderStatus = 2;
    }

    public final void setOnReminderChangedListener(OnReminderChangedListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.listener = listener;
    }

    public final int getReminderStatus() {
        return this.reminderStatus;
    }
}