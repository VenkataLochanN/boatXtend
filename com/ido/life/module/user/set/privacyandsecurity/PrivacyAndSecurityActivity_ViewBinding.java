package com.ido.life.module.user.set.privacyandsecurity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes3.dex */
public class PrivacyAndSecurityActivity_ViewBinding implements Unbinder {
    private PrivacyAndSecurityActivity target;

    public PrivacyAndSecurityActivity_ViewBinding(PrivacyAndSecurityActivity privacyAndSecurityActivity) {
        this(privacyAndSecurityActivity, privacyAndSecurityActivity.getWindow().getDecorView());
    }

    public PrivacyAndSecurityActivity_ViewBinding(PrivacyAndSecurityActivity privacyAndSecurityActivity, View view) {
        this.target = privacyAndSecurityActivity;
        privacyAndSecurityActivity.mItemWalkReminderSwitch = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_walk_reminder_switch, "field 'mItemWalkReminderSwitch'", CustomItemLabelView.class);
        privacyAndSecurityActivity.mItemPrivate = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_private, "field 'mItemPrivate'", CustomItemLabelView.class);
        privacyAndSecurityActivity.mItemSport = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_sport, "field 'mItemSport'", CustomItemLabelView.class);
        privacyAndSecurityActivity.mItemHealth = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_health, "field 'mItemHealth'", CustomItemLabelView.class);
        privacyAndSecurityActivity.mItemClear = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_clear, "field 'mItemClear'", CustomItemLabelView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        PrivacyAndSecurityActivity privacyAndSecurityActivity = this.target;
        if (privacyAndSecurityActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        privacyAndSecurityActivity.mItemWalkReminderSwitch = null;
        privacyAndSecurityActivity.mItemPrivate = null;
        privacyAndSecurityActivity.mItemSport = null;
        privacyAndSecurityActivity.mItemHealth = null;
        privacyAndSecurityActivity.mItemClear = null;
    }
}