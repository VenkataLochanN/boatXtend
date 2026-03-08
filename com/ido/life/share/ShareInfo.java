package com.ido.life.share;

import android.app.Activity;
import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes3.dex */
public class ShareInfo {
    private Activity mActivity;
    private String mShareAddress;
    private String mShareAuthor;
    private Bitmap mShareBitmap;
    private String mShareComment;
    private String mShareLiveImageUrl;
    private String mShareLocalFilePath;
    private String mShareLocalImagePath;
    private float mShareLocationLatitude;
    private float mShareLocationLongitude;
    private String mShareMusicUrl;
    private String mSharePlatFormName;
    private String mShareSiteUrl;
    private String mShareText;
    private String mShareTitle;
    private int mShareType;
    private String mShareUrl;

    private ShareInfo() {
    }

    private ShareInfo(String str, String str2, String str3, String str4, String str5, String str6, float f2, float f3, String str7, String str8, String str9, String str10, String str11, String str12, Bitmap bitmap, int i, Activity activity) {
        this.mSharePlatFormName = str;
        this.mShareText = str2;
        this.mShareLocalImagePath = str3;
        this.mShareLiveImageUrl = str4;
        this.mShareLocalFilePath = str5;
        this.mShareTitle = str6;
        this.mShareLocationLongitude = f2;
        this.mShareLocationLatitude = f3;
        this.mShareComment = str7;
        this.mShareUrl = str8;
        this.mShareAddress = str9;
        this.mShareMusicUrl = str10;
        this.mShareSiteUrl = str11;
        this.mShareAuthor = str12;
        this.mShareBitmap = bitmap;
        this.mShareType = i;
        this.mActivity = activity;
    }

    public String getSharePlatFormName() {
        return this.mSharePlatFormName;
    }

    public String getShareText() {
        return this.mShareText;
    }

    public String getShareLocalImagePath() {
        return this.mShareLocalImagePath;
    }

    public String getShareLiveImageUrl() {
        return this.mShareLiveImageUrl;
    }

    public String getShareLocalFilePath() {
        return this.mShareLocalFilePath;
    }

    public String getShareTitle() {
        return this.mShareTitle;
    }

    public float getShareLocationLongitude() {
        return this.mShareLocationLongitude;
    }

    public float getShareLocationLatitude() {
        return this.mShareLocationLatitude;
    }

    public String getShareComment() {
        return this.mShareComment;
    }

    public String getShareUrl() {
        return this.mShareUrl;
    }

    public String getShareAddress() {
        return this.mShareAddress;
    }

    public String getShareMusicUrl() {
        return this.mShareMusicUrl;
    }

    public String getShareSiteUrl() {
        return this.mShareSiteUrl;
    }

    public String getShareAuthor() {
        return this.mShareAuthor;
    }

    public Bitmap getShareBitmap() {
        return this.mShareBitmap;
    }

    public int getShareType() {
        return this.mShareType;
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public static class Builder {
        private Activity pActivity;
        private String pShareAddress;
        private String pShareAuthor;
        private Bitmap pShareBitmap;
        private String pShareComment;
        private String pShareLiveImageUrl;
        private String pShareLocalFilePath;
        private String pShareLocalImagePath;
        private float pShareLocationLatitude;
        private float pShareLocationLongitude;
        private String pShareMusicUrl;
        private String pSharePlatFormName;
        private String pShareSiteUrl;
        private String pShareText;
        private String pShareTitle;
        private int pShareType;
        private String pShareUrl;

        public Builder(String str) {
            this.pSharePlatFormName = str;
        }

        public Builder setSharePlatFormName(String str) {
            this.pSharePlatFormName = str;
            return this;
        }

        public Builder setShareText(String str) {
            this.pShareText = str;
            return this;
        }

        public Builder setShareLocalImagePath(String str) {
            this.pShareLocalImagePath = str;
            return this;
        }

        public Builder setShareLiveImageUrl(String str) {
            this.pShareLiveImageUrl = str;
            return this;
        }

        public Builder setShareLocalFilePath(String str) {
            this.pShareLocalFilePath = str;
            return this;
        }

        public Builder setShareTitle(String str) {
            this.pShareTitle = str;
            return this;
        }

        public Builder setShareLocationLongitude(float f2) {
            this.pShareLocationLongitude = f2;
            return this;
        }

        public Builder setShareLocationLatitude(float f2) {
            this.pShareLocationLatitude = f2;
            return this;
        }

        public Builder setShareComment(String str) {
            this.pShareComment = str;
            return this;
        }

        public Builder setShareUrl(String str) {
            this.pShareUrl = str;
            return this;
        }

        public Builder setShareAddress(String str) {
            this.pShareAddress = str;
            return this;
        }

        public Builder setShareMusicUrl(String str) {
            this.pShareMusicUrl = str;
            return this;
        }

        public Builder setShareSiteUrl(String str) {
            this.pShareSiteUrl = str;
            return this;
        }

        public Builder setShareAuthor(String str) {
            this.pShareAuthor = str;
            return this;
        }

        public Builder setShareBitmap(Bitmap bitmap) {
            this.pShareBitmap = bitmap;
            return this;
        }

        public Builder setShareType(int i) {
            this.pShareType = i;
            return this;
        }

        public Builder setActivity(Activity activity) {
            this.pActivity = activity;
            return this;
        }

        public ShareInfo build() {
            return new ShareInfo(this.pSharePlatFormName, this.pShareText, this.pShareLocalImagePath, this.pShareLiveImageUrl, this.pShareLocalFilePath, this.pShareTitle, this.pShareLocationLongitude, this.pShareLocationLatitude, this.pShareComment, this.pShareUrl, this.pShareAddress, this.pShareMusicUrl, this.pShareSiteUrl, this.pShareAuthor, this.pShareBitmap, this.pShareType, this.pActivity);
        }
    }
}