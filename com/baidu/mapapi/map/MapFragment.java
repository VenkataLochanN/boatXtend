package com.baidu.mapapi.map;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class MapFragment extends Fragment {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2862a = MapFragment.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private MapView f2863b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BaiduMapOptions f2864c;

    public MapFragment() {
    }

    private MapFragment(BaiduMapOptions baiduMapOptions) {
        this.f2864c = baiduMapOptions;
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    public static MapFragment newInstance(BaiduMapOptions baiduMapOptions) {
        return new MapFragment(baiduMapOptions);
    }

    public BaiduMap getBaiduMap() {
        MapView mapView = this.f2863b;
        if (mapView == null) {
            return null;
        }
        return mapView.getMap();
    }

    public MapView getMapView() {
        return this.f2863b;
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2863b = new MapView(getActivity(), this.f2864c);
        return this.f2863b;
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.f2863b.onDestroy();
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        this.f2863b.onPause();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        this.f2863b.onResume();
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }
}