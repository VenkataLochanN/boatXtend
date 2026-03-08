package com.ido.life.database.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ido.life.module.user.country.CountryChooseActivity;
import com.ido.life.util.UnitUtil;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class UserInfo implements Parcelable, Cloneable {
    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() { // from class: com.ido.life.database.model.UserInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfo createFromParcel(Parcel parcel) {
            return new UserInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfo[] newArray(int i) {
            return new UserInfo[i];
        }
    };
    public static final int INT_DEFAULT = -1;

    @SerializedName("areaCode")
    private String AreaCode;

    @SerializedName("avatarUrl")
    protected String AvatarUrl;

    @SerializedName("birthday")
    private String Birthday;

    @SerializedName("city")
    private String City;

    @SerializedName(CountryChooseActivity.COUNTRY)
    private String Country;

    @Expose(deserialize = false, serialize = false)
    private long CreateTime;

    @SerializedName("displayName")
    protected String DisplayName;

    @SerializedName("email")
    private String Email;

    @SerializedName("gender")
    private int Gender;

    @SerializedName("height")
    private float Height;

    @SerializedName("heightUnit")
    private int HeightUnit;
    private Long Id;

    @Expose(deserialize = false, serialize = false)
    private boolean ServerImageUrl;

    @SerializedName("timestamp")
    private long UpdateTime;

    @Expose(deserialize = false, serialize = false)
    private boolean UploadSuccess;

    @SerializedName("id")
    protected long UserId;

    @SerializedName("weight")
    private float Weight;

    @SerializedName("weightUnit")
    private int WeightUnit;
    private transient DaoSession daoSession;
    private transient UserInfoDao myDao;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UserInfo() {
        this.UserId = -1L;
        this.ServerImageUrl = true;
        this.Gender = -1;
        this.HeightUnit = 1;
        this.Height = -1.0f;
        this.WeightUnit = 1;
        this.Weight = -1.0f;
        this.UploadSuccess = false;
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.UpdateTime = jCurrentTimeMillis;
        this.CreateTime = jCurrentTimeMillis;
    }

    protected UserInfo(Parcel parcel) {
        this.UserId = -1L;
        this.ServerImageUrl = true;
        this.Gender = -1;
        this.HeightUnit = 1;
        this.Height = -1.0f;
        this.WeightUnit = 1;
        this.Weight = -1.0f;
        this.UploadSuccess = false;
        if (parcel.readByte() == 0) {
            this.Id = null;
        } else {
            this.Id = Long.valueOf(parcel.readLong());
        }
        this.UserId = parcel.readLong();
        this.DisplayName = parcel.readString();
        this.AvatarUrl = parcel.readString();
        this.ServerImageUrl = parcel.readByte() != 0;
        this.Email = parcel.readString();
        this.Gender = parcel.readInt();
        this.Birthday = parcel.readString();
        this.HeightUnit = parcel.readInt();
        this.Height = parcel.readFloat();
        this.WeightUnit = parcel.readInt();
        this.Weight = parcel.readInt();
        this.Country = parcel.readString();
        this.City = parcel.readString();
        this.AreaCode = parcel.readString();
        this.UploadSuccess = parcel.readByte() != 0;
        this.CreateTime = parcel.readLong();
        this.UpdateTime = parcel.readLong();
    }

    public UserInfo(Long l, long j, String str, String str2, boolean z, String str3, int i, String str4, int i2, float f2, int i3, float f3, String str5, String str6, String str7, boolean z2, long j2, long j3) {
        this.UserId = -1L;
        this.ServerImageUrl = true;
        this.Gender = -1;
        this.HeightUnit = 1;
        this.Height = -1.0f;
        this.WeightUnit = 1;
        this.Weight = -1.0f;
        this.UploadSuccess = false;
        this.Id = l;
        this.UserId = j;
        this.DisplayName = str;
        this.AvatarUrl = str2;
        this.ServerImageUrl = z;
        this.Email = str3;
        this.Gender = i;
        this.Birthday = str4;
        this.HeightUnit = i2;
        this.Height = f2;
        this.WeightUnit = i3;
        this.Weight = f3;
        this.Country = str5;
        this.City = str6;
        this.AreaCode = str7;
        this.UploadSuccess = z2;
        this.CreateTime = j2;
        this.UpdateTime = j3;
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long l) {
        this.Id = l;
    }

    public long getUserId() {
        return this.UserId;
    }

    public void setUserId(long j) {
        this.UserId = j;
    }

    public String getDisplayName() {
        return this.DisplayName;
    }

    public void setDisplayName(String str) {
        this.DisplayName = str;
    }

    public String getAvatarUrl() {
        return this.AvatarUrl;
    }

    public void setAvatarUrl(String str) {
        this.AvatarUrl = str;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String str) {
        this.Email = str;
    }

    public int getGender() {
        return this.Gender;
    }

    public void setGender(int i) {
        this.Gender = i;
    }

    public String getBirthday() {
        return this.Birthday;
    }

    public void setBirthday(String str) {
        if (!TextUtils.isEmpty(str) && str.contains("/")) {
            str = str.replaceAll("/", "-");
        }
        this.Birthday = str;
    }

    public int getHeightUnit() {
        return this.HeightUnit;
    }

    public void setHeightUnit(int i) {
        this.HeightUnit = i;
    }

    public float getHeight() {
        return this.Height;
    }

    public int getHeightInt() {
        return Math.round(this.Height);
    }

    public void setHeight(float f2) {
        this.Height = f2;
    }

    public int getWeightUnit() {
        return this.WeightUnit;
    }

    public void setWeightUnit(int i) {
        this.WeightUnit = i;
    }

    public float getWeight() {
        return this.Weight;
    }

    public void setWeight(float f2) {
        this.Weight = f2;
    }

    public int getWeightInt() {
        return Math.round(this.Weight);
    }

    public String getCountry() {
        return this.Country;
    }

    public void setCountry(String str) {
        this.Country = str;
    }

    public String getCity() {
        return this.City;
    }

    public void setCity(String str) {
        this.City = str;
    }

    public String getAreaCode() {
        return this.AreaCode;
    }

    public void setAreaCode(String str) {
        this.AreaCode = str;
    }

    public boolean isUploadSuccess() {
        return this.UploadSuccess;
    }

    public void setUploadSuccess(boolean z) {
        this.UploadSuccess = z;
    }

    public long getCreateTime() {
        return this.CreateTime;
    }

    public void setCreateTime(long j) {
        this.CreateTime = j;
    }

    public long getUpdateTime() {
        return this.UpdateTime;
    }

    public void setUpdateTime(long j) {
        this.UpdateTime = j;
    }

    public boolean getUploadSuccess() {
        return this.UploadSuccess;
    }

    public boolean isServerImageUrl() {
        return this.ServerImageUrl;
    }

    public void setServerImageUrl(boolean z) {
        this.ServerImageUrl = z;
    }

    public float getWeight(int i) {
        if (i == 2) {
            return getWeightPound();
        }
        if (i == 3) {
            return getWeightSt();
        }
        return getWeightKg();
    }

    public float getHeight(int i) {
        if (i == 2) {
            return getHeightInch();
        }
        return getHeightCm();
    }

    public float getWeightKg() {
        float f2 = this.Weight;
        int i = this.WeightUnit;
        if (i != 2) {
            return i != 3 ? f2 : UnitUtil.getSt2Kg(f2);
        }
        return UnitUtil.getPound2Kg(f2);
    }

    public float getWeightPound() {
        float f2 = this.Weight;
        int i = this.WeightUnit;
        if (i != 1) {
            return i != 3 ? f2 : UnitUtil.getSt2Lb(f2);
        }
        return UnitUtil.getKg2Pound(f2);
    }

    public float getWeightSt() {
        float f2 = this.Weight;
        int i = this.WeightUnit;
        if (i != 1) {
            return i != 2 ? f2 : UnitUtil.getPound2St(f2);
        }
        return UnitUtil.getKg2St(f2);
    }

    public int getHeightCm() {
        if (this.HeightUnit == 1) {
            return Math.round(this.Height);
        }
        return Math.round(UnitUtil.inch2CmF(this.Height));
    }

    public float getHeightInch() {
        if (this.HeightUnit == 2) {
            return this.Height;
        }
        return UnitUtil.cm2Inch(Math.round(this.Height));
    }

    public void delete() {
        UserInfoDao userInfoDao = this.myDao;
        if (userInfoDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        userInfoDao.delete(this);
    }

    public void refresh() {
        UserInfoDao userInfoDao = this.myDao;
        if (userInfoDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        userInfoDao.refresh(this);
    }

    public void update() {
        if (this.myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        this.UpdateTime = System.currentTimeMillis();
        this.myDao.update(this);
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public UserInfo m27clone() {
        UserInfo userInfo = new UserInfo();
        userInfo.setDisplayName(this.DisplayName);
        userInfo.setAvatarUrl(this.AvatarUrl);
        userInfo.setEmail(this.Email);
        userInfo.setGender(this.Gender);
        userInfo.setBirthday(this.Birthday);
        userInfo.setHeight(this.Height);
        userInfo.setHeightUnit(this.HeightUnit);
        userInfo.setWeight(this.Weight);
        userInfo.setWeightUnit(this.WeightUnit);
        userInfo.setUserId(this.UserId);
        userInfo.setAreaCode(this.AreaCode);
        userInfo.setCity(this.City);
        userInfo.setCountry(this.Country);
        userInfo.setServerImageUrl(this.ServerImageUrl);
        userInfo.setUpdateTime(this.UpdateTime);
        return userInfo;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (this.Id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(this.Id.longValue());
        }
        parcel.writeLong(this.UserId);
        parcel.writeString(this.DisplayName);
        parcel.writeString(this.AvatarUrl);
        parcel.writeByte(this.ServerImageUrl ? (byte) 1 : (byte) 0);
        parcel.writeString(this.Email);
        parcel.writeInt(this.Gender);
        parcel.writeString(this.Birthday);
        parcel.writeInt(this.HeightUnit);
        parcel.writeFloat(this.Height);
        parcel.writeInt(this.WeightUnit);
        parcel.writeFloat(this.Weight);
        parcel.writeString(this.Country);
        parcel.writeString(this.City);
        parcel.writeString(this.AreaCode);
        parcel.writeByte(this.UploadSuccess ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.CreateTime);
        parcel.writeLong(this.UpdateTime);
    }

    public boolean getServerImageUrl() {
        return this.ServerImageUrl;
    }

    public String toString() {
        return "UserInfo{Id=" + this.Id + ", UserId=" + this.UserId + ", DisplayName='" + this.DisplayName + "', AvatarUrl='" + this.AvatarUrl + "', ServerImageUrl=" + this.ServerImageUrl + ", Email='" + this.Email + "', Gender=" + this.Gender + ", Birthday='" + this.Birthday + "', HeightUnit=" + this.HeightUnit + ", Height=" + this.Height + ", WeightUnit=" + this.WeightUnit + ", Weight=" + this.Weight + ", Country='" + this.Country + "', City='" + this.City + "', AreaCode='" + this.AreaCode + "', UploadSuccess=" + this.UploadSuccess + ", CreateTime=" + this.CreateTime + ", UpdateTime=" + this.UpdateTime + '}';
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getUserInfoDao() : null;
    }
}