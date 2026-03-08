package com.amazon.identity.auth.device.api.authorization.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class SignInButton extends ImageButton {
    private static final String BUTTON_IMAGE_PREFIX = "btnlwa";
    private static final String BUTTON_PRESSED_SUFFIX = "pressed";
    private static final String LOG_TAG = SignInButton.class.getName();
    private static final Map<String, Integer> resourceIdCache = new HashMap();
    private Color color;
    private Style style;

    public enum Style {
        A_WITH_SMILE("a"),
        LOGIN(FirebaseAnalytics.Event.LOGIN),
        LOGIN_WITH_AMAZON("loginwithamazon");

        private final String name;

        Style(String str) {
            this.name = str;
        }
    }

    public enum Color {
        GOLD("gold"),
        GRAY("gry"),
        DARK_GRAY("dark_gray");

        private final String name;

        Color(String str) {
            this.name = str;
        }
    }

    public SignInButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.style = Style.LOGIN_WITH_AMAZON;
        this.color = Color.GOLD;
        updateImage();
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        super.setPressed(z);
        updateImage();
    }

    private void updateImage() {
        setImageResource(getResourceIdForCurrentState());
    }

    private int getResourceIdForCurrentState() {
        String buttonNameForCurrentState = getButtonNameForCurrentState();
        Integer numValueOf = resourceIdCache.get(buttonNameForCurrentState);
        if (numValueOf == null) {
            numValueOf = Integer.valueOf(getResources().getIdentifier(String.format("%s:drawable/%s", getContext().getPackageName(), buttonNameForCurrentState), null, null));
            if (numValueOf.intValue() != 0) {
                resourceIdCache.put(buttonNameForCurrentState, numValueOf);
            } else {
                MAPLog.e(LOG_TAG, "Could not find the resource ID for the image named \"" + buttonNameForCurrentState + "\". It must be added to the drawables resources  (" + getButtonDescription() + ")");
            }
        }
        return numValueOf.intValue();
    }

    private String getButtonNameForCurrentState() {
        StringBuilder sb = new StringBuilder();
        sb.append(BUTTON_IMAGE_PREFIX);
        sb.append("_");
        sb.append(this.color.name);
        sb.append("_");
        sb.append(this.style.name);
        if (isPressed()) {
            sb.append("_");
            sb.append(BUTTON_PRESSED_SUFFIX);
        }
        return sb.toString();
    }

    private String getButtonDescription() {
        return String.format("Button configuration = { style:%s color:%s pressed:%b }", this.style.toString(), this.color.toString(), Boolean.valueOf(isPressed()));
    }
}