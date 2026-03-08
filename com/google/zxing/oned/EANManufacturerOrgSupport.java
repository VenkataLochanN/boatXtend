package com.google.zxing.oned;

import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.google.zxing.client.result.ExpandedProductParsedResult;
import com.ido.ble.protocol.model.Sport100Type;
import com.ido.life.constants.Constants;
import com.veryfit.multi.nativeprotocol.b;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class EANManufacturerOrgSupport {
    private final List<int[]> ranges = new ArrayList();
    private final List<String> countryIdentifiers = new ArrayList();

    EANManufacturerOrgSupport() {
    }

    String lookupCountryIdentifier(String str) {
        int[] iArr;
        int i;
        initIfNeeded();
        int i2 = Integer.parseInt(str.substring(0, 3));
        int size = this.ranges.size();
        for (int i3 = 0; i3 < size && i2 >= (i = (iArr = this.ranges.get(i3))[0]); i3++) {
            if (iArr.length != 1) {
                i = iArr[1];
            }
            if (i2 <= i) {
                return this.countryIdentifiers.get(i3);
            }
        }
        return null;
    }

    private void add(int[] iArr, String str) {
        this.ranges.add(iArr);
        this.countryIdentifiers.add(str);
    }

    private synchronized void initIfNeeded() {
        if (this.ranges.isEmpty()) {
            add(new int[]{0, 19}, "US/CA");
            add(new int[]{30, 39}, "US");
            add(new int[]{60, Sport100Type.SPORT_TYPE_BEACH_SOCCER}, "US/CA");
            add(new int[]{300, 379}, "FR");
            add(new int[]{380}, "BG");
            add(new int[]{383}, "SI");
            add(new int[]{385}, "HR");
            add(new int[]{387}, "BA");
            add(new int[]{400, 440}, "DE");
            add(new int[]{450, 459}, "JP");
            add(new int[]{460, 469}, "RU");
            add(new int[]{471}, "TW");
            add(new int[]{474}, "EE");
            add(new int[]{475}, "LV");
            add(new int[]{476}, "AZ");
            add(new int[]{477}, "LT");
            add(new int[]{478}, "UZ");
            add(new int[]{479}, "LK");
            add(new int[]{GlMapUtil.DEVICE_DISPLAY_DPI_XHIGH}, "PH");
            add(new int[]{481}, "BY");
            add(new int[]{482}, "UA");
            add(new int[]{484}, "MD");
            add(new int[]{485}, "AM");
            add(new int[]{486}, "GE");
            add(new int[]{487}, "KZ");
            add(new int[]{489}, "HK");
            add(new int[]{490, 499}, "JP");
            add(new int[]{500, 509}, "GB");
            add(new int[]{520}, "GR");
            add(new int[]{528}, ExpandedProductParsedResult.POUND);
            add(new int[]{529}, "CY");
            add(new int[]{531}, "MK");
            add(new int[]{535}, "MT");
            add(new int[]{539}, "IE");
            add(new int[]{540, 549}, "BE/LU");
            add(new int[]{b.b2}, "PT");
            add(new int[]{569}, "IS");
            add(new int[]{b.h2, 579}, "DK");
            add(new int[]{590}, "PL");
            add(new int[]{594}, "RO");
            add(new int[]{599}, "HU");
            add(new int[]{600, 601}, "ZA");
            add(new int[]{603}, "GH");
            add(new int[]{608}, "BH");
            add(new int[]{609}, "MU");
            add(new int[]{611}, "MA");
            add(new int[]{613}, "DZ");
            add(new int[]{Constants.EventConstants.EVENT_FIRST_ONFOOT}, "KE");
            add(new int[]{b.I2}, "CI");
            add(new int[]{619}, "TN");
            add(new int[]{b.K2}, "SY");
            add(new int[]{b.L2}, "EG");
            add(new int[]{b.N2}, "LY");
            add(new int[]{b.O2}, "JO");
            add(new int[]{b.P2}, "IR");
            add(new int[]{b.Q2}, "KW");
            add(new int[]{b.R2}, "SA");
            add(new int[]{b.S2}, "AE");
            add(new int[]{GlMapUtil.DEVICE_DISPLAY_DPI_XXHIGH, 649}, "FI");
            add(new int[]{690, 695}, "CN");
            add(new int[]{700, 709}, "NO");
            add(new int[]{729}, "IL");
            add(new int[]{730, 739}, "SE");
            add(new int[]{740}, "GT");
            add(new int[]{741}, "SV");
            add(new int[]{742}, "HN");
            add(new int[]{743}, "NI");
            add(new int[]{744}, "CR");
            add(new int[]{745}, "PA");
            add(new int[]{746}, "DO");
            add(new int[]{750}, "MX");
            add(new int[]{754, 755}, "CA");
            add(new int[]{759}, "VE");
            add(new int[]{760, 769}, "CH");
            add(new int[]{770}, "CO");
            add(new int[]{773}, "UY");
            add(new int[]{775}, "PE");
            add(new int[]{777}, "BO");
            add(new int[]{779}, "AR");
            add(new int[]{780}, "CL");
            add(new int[]{784}, "PY");
            add(new int[]{785}, "PE");
            add(new int[]{786}, "EC");
            add(new int[]{789, 790}, "BR");
            add(new int[]{GLMapStaticValue.ANIMATION_MOVE_TIME, Constants.EventConstants.EVENT_REFRESH_WEEK_REPORT}, "IT");
            add(new int[]{Constants.EventConstants.EVENT_USER_MEDAL_PULL_SUCCESS, Constants.EventConstants.EVENT_GET_DEVICE_SCREEN_INFO}, "ES");
            add(new int[]{Constants.EventConstants.EVENT_REPORT_SAVE_SCREEN_PHOTO}, "CU");
            add(new int[]{Constants.EventConstants.EVENT_WALK_STEP_TARGET_CHANGED}, "SK");
            add(new int[]{Constants.EventConstants.EVENT_CALORIE_TARGET_CHANGED}, "CZ");
            add(new int[]{Constants.EventConstants.EVENT_ACTIVE_CALORIE_UNIT_CHANGED}, "YU");
            add(new int[]{Constants.EventConstants.EVENT_SET_FAMILY_ACCOUNT_WALK_DURATION_TARGET}, "MN");
            add(new int[]{Constants.EventConstants.EVENT_UNBIND_OTHER_WAY}, "KP");
            add(new int[]{Constants.EventConstants.EVENT_UPDATE_DEVICE_NAME, Constants.EventConstants.EVENT_UNBIND_DEVICE}, "TR");
            add(new int[]{Constants.EventConstants.EVENT_CONNECTING, Constants.EventConstants.EVENT_FINISH_INPUTUSERDATAACTIVITY}, "NL");
            add(new int[]{Constants.EventConstants.EVENT_TO_CONNECT_MAIN_DEVICE}, "KR");
            add(new int[]{Constants.EventConstants.EVENT_MEMBER_ADMIN_TOKEN_INVALID}, "TH");
            add(new int[]{Constants.EventConstants.EVENT_SYNC_CONFIG_TO_DEVICE}, "SG");
            add(new int[]{Constants.EventConstants.EVENT_FIRST_NOISE_DATA}, "IN");
            add(new int[]{Constants.EventConstants.EVENT_DATA_SYNC_START}, "VN");
            add(new int[]{Constants.EventConstants.EVENT_SLEEP_ENTER_DETAIL}, "PK");
            add(new int[]{Constants.EventConstants.EVENT_DEVICE_MUSIC_UPLOAD_FAIL}, "ID");
            add(new int[]{Constants.EventConstants.EVENT_SWITCH_LANGUAGE, 919}, "AT");
            add(new int[]{930, 939}, "AU");
            add(new int[]{940, 949}, "AZ");
            add(new int[]{955}, "MY");
            add(new int[]{958}, "MO");
        }
    }
}