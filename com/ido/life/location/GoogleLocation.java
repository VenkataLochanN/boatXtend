package com.ido.life.location;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.bean.GoogleGeoCodeBean;
import com.ido.life.bean.GoogleGeoCodeItemBean;
import com.ido.life.bean.GoogleGeoCodeResult;
import com.ido.life.bean.LatLngBean;
import com.ido.life.data.api.StravaApi;
import com.ido.life.module.user.country.CountryChooseActivity;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* JADX INFO: loaded from: classes2.dex */
public class GoogleLocation extends BaseLocationPresenter implements LocationListener {
    private static final String TAG = GoogleLocation.class.getSimpleName();
    private android.location.LocationManager mLocationManager;

    String getBasePrioveder() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setAltitudeRequired(true);
        criteria.setPowerRequirement(0);
        criteria.setBearingAccuracy(3);
        criteria.setHorizontalAccuracy(3);
        criteria.setVerticalAccuracy(3);
        return this.mLocationManager.getBestProvider(criteria, true);
    }

    @Override // com.ido.life.location.BaseLocationPresenter, com.ido.life.base.IBaseLocation
    public void startLocation(Context context, boolean z) {
        super.startLocation(context, z);
        if (checkPermission()) {
            if (this.mLocationManager == null) {
                this.mLocationManager = (android.location.LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION);
            }
            Location lastKnownLocation = this.mLocationManager.getLastKnownLocation(getBasePrioveder());
            if (lastKnownLocation != null) {
                queryLocationInfo(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(), Locale.getDefault().getLanguage());
                return;
            }
            return;
        }
        super.onLocationChanged((LocationMessage) null);
    }

    private void queryLocationInfo(final double d2, final double d3, String str) {
        StravaApi.api.queryAddress(d2 + AppInfo.DELIM + d3, str).enqueue(new Callback<GoogleGeoCodeResult>() { // from class: com.ido.life.location.GoogleLocation.1
            @Override // retrofit2.Callback
            public void onResponse(Call<GoogleGeoCodeResult> call, Response<GoogleGeoCodeResult> response) throws Throwable {
                LocationMessage locationMessage;
                List<GoogleGeoCodeBean> list;
                LocationMessage locationMessage2;
                List<String> types;
                if (response == null || !response.isSuccessful()) {
                    return;
                }
                GoogleGeoCodeResult googleGeoCodeResultBody = response.body();
                LocationMessage locationMessage3 = null;
                try {
                    list = googleGeoCodeResultBody.getList();
                } catch (Exception e2) {
                    e = e2;
                    locationMessage = null;
                } catch (Throwable th) {
                    th = th;
                }
                if (list == null || list.size() <= 0) {
                    locationMessage2 = null;
                } else {
                    Iterator<GoogleGeoCodeBean> it = list.iterator();
                    String shortName = "";
                    String shortName2 = "";
                    while (it.hasNext()) {
                        List<GoogleGeoCodeItemBean> list2 = it.next().getList();
                        if (list2 != null && list2.size() > 0) {
                            for (GoogleGeoCodeItemBean googleGeoCodeItemBean : list2) {
                                if (googleGeoCodeItemBean != null && (types = googleGeoCodeItemBean.getTypes()) != null && types.size() > 0) {
                                    if (TextUtils.equals(types.get(0), "locality")) {
                                        shortName2 = googleGeoCodeItemBean.getShortName();
                                    } else if (TextUtils.equals(types.get(0), CountryChooseActivity.COUNTRY)) {
                                        shortName = googleGeoCodeItemBean.getShortName();
                                    }
                                }
                            }
                        }
                        if (!TextUtils.isEmpty(shortName2) && !TextUtils.isEmpty(shortName)) {
                            locationMessage2 = new LocationMessage(109, new LatLngBean(d2, d3));
                            try {
                                locationMessage2.city = shortName2;
                                locationMessage2.country = shortName;
                                break;
                            } catch (Exception e3) {
                                locationMessage = locationMessage2;
                                e = e3;
                            } catch (Throwable th2) {
                                locationMessage3 = locationMessage2;
                                th = th2;
                                GoogleLocation.super.onLocationChanged(locationMessage3);
                                throw th;
                            }
                        }
                        try {
                            e.printStackTrace();
                            GoogleLocation.super.onLocationChanged((LocationMessage) null);
                            return;
                        } catch (Throwable th3) {
                            th = th3;
                            locationMessage3 = locationMessage;
                            GoogleLocation.super.onLocationChanged(locationMessage3);
                            throw th;
                        }
                    }
                    locationMessage2 = null;
                }
                GoogleLocation.super.onLocationChanged(locationMessage2);
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<GoogleGeoCodeResult> call, Throwable th) {
                CommonLogUtil.d(GoogleLocation.TAG, th.getLocalizedMessage());
                GoogleLocation.super.onLocationChanged((LocationMessage) null);
            }
        });
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            location.getExtras();
            LatLngBean latLngBean = new LatLngBean();
            latLngBean.setLatitude(latitude);
            latLngBean.setLongitude(longitude);
            new LocationMessage(109, MapHelper.translate(new LatLngBean(location.getLatitude(), location.getLongitude()))).accurac = location.getAccuracy();
        }
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i, Bundle bundle) {
        CommonLogUtil.d(TAG, "onStatusChanged:provider=" + str + " status=" + i + " extras=" + bundle.toString());
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
        CommonLogUtil.d(TAG, "onProviderEnabled:provider=" + str);
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
        CommonLogUtil.d(TAG, "onProviderDisabled:provider=" + str);
    }
}