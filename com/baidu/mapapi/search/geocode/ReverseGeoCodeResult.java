package com.baidu.mapapi.search.geocode;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ReverseGeoCodeResult extends SearchResult {
    public static final Parcelable.Creator<ReverseGeoCodeResult> CREATOR = new b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3171a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3172b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private AddressComponent f3173c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private LatLng f3174d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3175e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private List<PoiInfo> f3176f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f3177g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private List<PoiRegionsInfo> f3178h;

    public static class AddressComponent implements Parcelable {
        public static final Parcelable.Creator<AddressComponent> CREATOR = new c();
        public int adcode;
        public String city;
        public int countryCode;
        public String countryName;
        public String direction;
        public String distance;
        public String district;
        public String province;
        public String street;
        public String streetNumber;
        public String town;

        public AddressComponent() {
        }

        protected AddressComponent(Parcel parcel) {
            this.streetNumber = parcel.readString();
            this.street = parcel.readString();
            this.town = parcel.readString();
            this.district = parcel.readString();
            this.city = parcel.readString();
            this.province = parcel.readString();
            this.countryName = parcel.readString();
            this.countryCode = parcel.readInt();
            this.adcode = parcel.readInt();
            this.direction = parcel.readString();
            this.distance = parcel.readString();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String getDirection() {
            return this.direction;
        }

        public String getDistance() {
            return this.distance;
        }

        public String getTown() {
            return this.town;
        }

        public void setDirection(String str) {
            this.direction = str;
        }

        public void setDistance(String str) {
            this.distance = str;
        }

        public void setTown(String str) {
            this.town = str;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.streetNumber);
            parcel.writeString(this.street);
            parcel.writeString(this.town);
            parcel.writeString(this.district);
            parcel.writeString(this.city);
            parcel.writeString(this.province);
            parcel.writeString(this.countryName);
            parcel.writeInt(this.countryCode);
            parcel.writeInt(this.adcode);
            parcel.writeString(this.direction);
            parcel.writeString(this.distance);
        }
    }

    public static class PoiRegionsInfo implements Parcelable {
        public static final Parcelable.Creator<PoiRegionsInfo> CREATOR = new d();
        public String directionDesc;
        public String regionName;
        public String regionTag;

        public PoiRegionsInfo() {
        }

        protected PoiRegionsInfo(Parcel parcel) {
            this.directionDesc = parcel.readString();
            this.regionName = parcel.readString();
            this.regionTag = parcel.readString();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String getDirectionDesc() {
            return this.directionDesc;
        }

        public String getRegionName() {
            return this.regionName;
        }

        public String getRegionTag() {
            return this.regionTag;
        }

        public void setDirectionDesc(String str) {
            this.directionDesc = str;
        }

        public void setRegionName(String str) {
            this.regionName = str;
        }

        public void setRegionTag(String str) {
            this.regionTag = str;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.directionDesc);
            parcel.writeString(this.regionName);
            parcel.writeString(this.regionTag);
        }
    }

    public ReverseGeoCodeResult() {
    }

    protected ReverseGeoCodeResult(Parcel parcel) {
        super(parcel);
        this.f3171a = parcel.readString();
        this.f3172b = parcel.readString();
        this.f3173c = (AddressComponent) parcel.readParcelable(AddressComponent.class.getClassLoader());
        this.f3174d = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.f3176f = parcel.createTypedArrayList(PoiInfo.CREATOR);
        this.f3177g = parcel.readString();
        this.f3178h = parcel.createTypedArrayList(PoiRegionsInfo.CREATOR);
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getAdcode() {
        return this.f3173c.adcode;
    }

    public String getAddress() {
        return this.f3172b;
    }

    public AddressComponent getAddressDetail() {
        return this.f3173c;
    }

    public String getBusinessCircle() {
        return this.f3171a;
    }

    public int getCityCode() {
        return this.f3175e;
    }

    public LatLng getLocation() {
        return this.f3174d;
    }

    public List<PoiInfo> getPoiList() {
        return this.f3176f;
    }

    public List<PoiRegionsInfo> getPoiRegionsInfoList() {
        return this.f3178h;
    }

    public String getSematicDescription() {
        return this.f3177g;
    }

    public void setAdcode(int i) {
        this.f3173c.adcode = i;
    }

    public void setAddress(String str) {
        this.f3172b = str;
    }

    public void setAddressDetail(AddressComponent addressComponent) {
        this.f3173c = addressComponent;
    }

    public void setBusinessCircle(String str) {
        this.f3171a = str;
    }

    public void setCityCode(int i) {
        this.f3175e = i;
    }

    public void setLocation(LatLng latLng) {
        this.f3174d = latLng;
    }

    public void setPoiList(List<PoiInfo> list) {
        this.f3176f = list;
    }

    public void setPoiRegionsInfoList(List<PoiRegionsInfo> list) {
        this.f3178h = list;
    }

    public void setSematicDescription(String str) {
        this.f3177g = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("ReverseGeoCodeResult: \n");
        stringBuffer.append("businessCircle = ");
        stringBuffer.append(this.f3171a);
        stringBuffer.append("; address = ");
        stringBuffer.append(this.f3172b);
        stringBuffer.append("; location = ");
        stringBuffer.append(this.f3174d);
        stringBuffer.append("; sematicDescription = ");
        stringBuffer.append(this.f3177g);
        if (this.f3173c != null) {
            stringBuffer.append("\n#AddressComponent Info BEGIN# \n");
            stringBuffer.append("streetNumber = ");
            stringBuffer.append(this.f3173c.streetNumber);
            stringBuffer.append("; street = ");
            stringBuffer.append(this.f3173c.street);
            stringBuffer.append("; town = ");
            stringBuffer.append(this.f3173c.town);
            stringBuffer.append("; district = ");
            stringBuffer.append(this.f3173c.district);
            stringBuffer.append("; city = ");
            stringBuffer.append(this.f3173c.city);
            stringBuffer.append("; province = ");
            stringBuffer.append(this.f3173c.province);
            stringBuffer.append("; countryName = ");
            stringBuffer.append(this.f3173c.countryName);
            stringBuffer.append("; countryCode = ");
            stringBuffer.append(this.f3173c.countryCode);
            stringBuffer.append("; adcode = ");
            stringBuffer.append(this.f3173c.adcode);
            stringBuffer.append("; direction = ");
            stringBuffer.append(this.f3173c.direction);
            stringBuffer.append("; distance = ");
            stringBuffer.append(this.f3173c.distance);
            stringBuffer.append("\n#AddressComponent Info END# \n");
        }
        List<PoiRegionsInfo> list = this.f3178h;
        if (list != null && !list.isEmpty()) {
            stringBuffer.append("\n#PoiRegions Info  BEGIN#");
            for (int i = 0; i < this.f3178h.size(); i++) {
                PoiRegionsInfo poiRegionsInfo = this.f3178h.get(i);
                if (poiRegionsInfo != null) {
                    stringBuffer.append("\ndirectionDesc = ");
                    stringBuffer.append(poiRegionsInfo.getDirectionDesc());
                    stringBuffer.append("; regionName = ");
                    stringBuffer.append(poiRegionsInfo.getRegionName());
                    stringBuffer.append("; regionTag = ");
                    stringBuffer.append(poiRegionsInfo.getRegionTag());
                }
            }
            stringBuffer.append("\n#PoiRegions Info  END# \n");
        }
        List<PoiInfo> list2 = this.f3176f;
        if (list2 != null && !list2.isEmpty()) {
            stringBuffer.append("\n #PoiList Info  BEGIN#");
            for (int i2 = 0; i2 < this.f3176f.size(); i2++) {
                PoiInfo poiInfo = this.f3176f.get(i2);
                if (poiInfo != null) {
                    stringBuffer.append("\n address = ");
                    stringBuffer.append(poiInfo.getAddress());
                    stringBuffer.append("; phoneNumber = ");
                    stringBuffer.append(poiInfo.getPhoneNum());
                    stringBuffer.append("; uid = ");
                    stringBuffer.append(poiInfo.getUid());
                    stringBuffer.append("; postCode = ");
                    stringBuffer.append(poiInfo.getPostCode());
                    stringBuffer.append("; name = ");
                    stringBuffer.append(poiInfo.getName());
                    stringBuffer.append("; location = ");
                    stringBuffer.append(poiInfo.getLocation());
                    stringBuffer.append("; city = ");
                    stringBuffer.append(poiInfo.getCity());
                    stringBuffer.append("; direction = ");
                    stringBuffer.append(poiInfo.getDirection());
                    stringBuffer.append("; distance = ");
                    stringBuffer.append(poiInfo.getDistance());
                    if (poiInfo.getParentPoi() != null) {
                        stringBuffer.append("\n parentPoiAddress = ");
                        stringBuffer.append(poiInfo.getParentPoi().getParentPoiAddress());
                        stringBuffer.append("; parentPoiDirection = ");
                        stringBuffer.append(poiInfo.getParentPoi().getParentPoiDirection());
                        stringBuffer.append("; parentPoiDistance = ");
                        stringBuffer.append(poiInfo.getParentPoi().getParentPoiDistance());
                        stringBuffer.append("; parentPoiName = ");
                        stringBuffer.append(poiInfo.getParentPoi().getParentPoiName());
                        stringBuffer.append("; parentPoiTag = ");
                        stringBuffer.append(poiInfo.getParentPoi().getParentPoiTag());
                        stringBuffer.append("; parentPoiUid = ");
                        stringBuffer.append(poiInfo.getParentPoi().getParentPoiUid());
                        stringBuffer.append("; parentPoiLocation = ");
                        stringBuffer.append(poiInfo.getParentPoi().getParentPoiLocation());
                    }
                }
            }
            stringBuffer.append("\n #PoiList Info  END# \n");
        }
        return stringBuffer.toString();
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f3171a);
        parcel.writeString(this.f3172b);
        parcel.writeParcelable(this.f3173c, 0);
        parcel.writeValue(this.f3174d);
        parcel.writeTypedList(this.f3176f);
        parcel.writeString(this.f3177g);
        parcel.writeTypedList(this.f3178h);
    }
}