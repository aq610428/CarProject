package com.car.notver.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.util.AMapUtil;
import com.car.notver.util.LogUtils;
import com.car.notver.util.ToastUtil;
import com.car.notver.util.Utility;
import com.car.notver.weight.ClearEditText;
import com.car.notver.weight.PreferenceUtils;
import com.car.notver.weight.SensorEventHelper;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/6/6
 * @name:定位
 */
public class LocationActivity extends BaseActivity implements LocationSource, AMapLocationListener, AMap.OnMarkerClickListener,
        PoiSearch.OnPoiSearchListener, AMap.OnMapClickListener, GeocodeSearch.OnGeocodeSearchListener {
    private TextView title_text_tv, title_left_btn, text_edition, text_address, text_ok;
    private AMap aMap;
    private MapView mapView;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private boolean mFirstFix = false;
    private SensorEventHelper mSensorHelper;
    private ClearEditText et_map;
    private Marker mLocMarker;
    private LatLng latLng;
    private RelativeLayout rl_tab;
    private static String BACK_LOCATION_PERMISSION = "android.permission.ACCESS_BACKGROUND_LOCATION";
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            BACK_LOCATION_PERMISSION
    };


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_location);
        mapView = getView(R.id.mMapView);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        request();
    }

    private boolean needCheckBackLocation = false;

    private void request() {
        if (Build.VERSION.SDK_INT > 28 && getApplicationContext().getApplicationInfo().targetSdkVersion > 28) {
            needPermissions = new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE,
                    BACK_LOCATION_PERMISSION
            };
            needCheckBackLocation = true;
        }
    }

    /*************************************** 权限检查******************************************************/


    @Override
    protected void initView() {
        rl_tab = getView(R.id.rl_tab);
        text_ok = getView(R.id.text_ok);
        text_address = getView(R.id.text_address);
        text_edition = getView(R.id.text_edition);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("店铺位置");
        et_map = getView(R.id.et_map);
        text_edition.setOnClickListener(this);
        text_ok.setOnClickListener(this);
    }


    public void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
        mSensorHelper = new SensorEventHelper(this);
        if (mSensorHelper != null) {
            mSensorHelper.registerSensorListener();
        }
    }


    private boolean isNeedCheck = true;

    @Override
    protected void onResume() {
        try {
            super.onResume();
            if (Build.VERSION.SDK_INT >= 23) {
                if (isNeedCheck) {
                    checkPermissions1(needPermissions);
                } else {
                    init();
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {

    }


    /**
     * @param
     * @return
     * @since 2.5.0
     */
    public void checkPermissions1(String... permissions) {
        try {
            if (Build.VERSION.SDK_INT >= 23 && getApplicationInfo().targetSdkVersion >= 23) {
                List<String> needRequestPermissonList = findDeniedPermissions(permissions);
                if (null != needRequestPermissonList && needRequestPermissonList.size() > 0) {
                    try {
                        String[] array = needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]);
                        Method method = getClass().getMethod("requestPermissions", new Class[]{String[].class, int.class});
                        method.invoke(this, array, 0);
                    } catch (Throwable e) {

                    }
                }else{
                    init();
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setOnMarkerClickListener(this);
        detailMarker = aMap.addMarker(new MarkerOptions());
        aMap.setOnMapClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_edition:
                poiSearch();
                break;
            case R.id.text_ok:
                String provider = PreferenceUtils.getPrefString(this, "provider", "");
                String city = PreferenceUtils.getPrefString(this, "city", "");
                String district = PreferenceUtils.getPrefString(this, "district", "");
                if (latLng != null&&!Utility.isEmpty(provider)&&!Utility.isEmpty(city)&&!Utility.isEmpty(district)) {
                    PreferenceUtils.setPrefString(this, "name", address);
                    PreferenceUtils.setPrefString(this, "lat", latLng.latitude + "");
                    PreferenceUtils.setPrefString(this, "lon", latLng.longitude + "");
                    finish();
                }

                break;
        }
    }

    String city;

    private void poiSearch() {
        String newText = et_map.getText().toString();
        if (!Utility.isEmpty(newText)) {
            PoiSearch.Query mPoiSearchQuery = new PoiSearch.Query(newText, "", city);
            mPoiSearchQuery.requireSubPois(true);   //true 搜索结果包含POI父子关系; false
            mPoiSearchQuery.setPageSize(10);
            mPoiSearchQuery.setPageNum(0);
            PoiSearch poiSearch = new PoiSearch(this, mPoiSearchQuery);
            poiSearch.setOnPoiSearchListener(this);
            poiSearch.searchPOIAsyn();
        }

    }

    private void addMarker(LatLng latlng) {
        if (mLocMarker != null) {
            return;
        }
        Bitmap bMap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.navi_map_gps_locked);
        BitmapDescriptor des = BitmapDescriptorFactory.fromBitmap(bMap);
        MarkerOptions options = new MarkerOptions();
        options.icon(des);
        options.anchor(0.5f, 0.5f);
        options.position(latlng);
        mLocMarker = aMap.addMarker(options);
    }


    private void showMarker(LatLng latlng) {
        aMap.clear();
        MarkerOptions markerOption = new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                .position(latlng)
                .draggable(true);
        markerOption.draggable(true);//设置Marker可拖动
        aMap.addMarker(markerOption);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onPause();
    }


    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null && amapLocation.getErrorCode() == 0) {
                LatLng latLng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                if (!Utility.isEmpty(amapLocation.getCity())){
                    PreferenceUtils.setPrefString(this, "city", amapLocation.getCity());
                    PreferenceUtils.setPrefString(this, "district", amapLocation.getDistrict());
                    PreferenceUtils.setPrefString(this, "provider", amapLocation.getProvince());
                }
                if (!mFirstFix) {
                    mFirstFix = true;
                    addMarker(latLng);//添加定位图标
                    mSensorHelper.setCurrentMarker(mLocMarker);//定位图标旋转
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
                    getAddress(latLng);
                }
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);

            }
        }
    }

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }


    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }


    @Override
    protected void onPause() {
        super.onPause();
        deactivate();
    }

    private PoiItem mPoi;
    private Marker detailMarker;

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onPoiSearched(PoiResult result, int rcode) {
        if (rcode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null) {
                List<PoiItem> poiItems = result.getPois();
                Intent intent = new Intent(this, PoiSearchActivity.class);
                intent.putExtra("poiItems", (Serializable) poiItems);
                startActivity(intent);
                finish();
            }
        } else {
            ToastUtil.showToast(rcode + "");
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem item, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (item != null) {
                mPoi = item;
                detailMarker.setPosition(AMapUtil.convertToLatLng(mPoi.getLatLonPoint()));
                setPoiItemDisplayContent(mPoi);
            }
        } else {
            ToastUtil.showToast(rCode + "");
        }
    }

    private void setPoiItemDisplayContent(final PoiItem mCurrentPoi) {
        LogUtils.e(mCurrentPoi.getAdName());
    }

    @Override
    public void onMapClick(LatLng latLng) {
        this.latLng = latLng;
        showMarker(latLng);
        getAddress(latLng);

    }

    /*****获取地址*****/
    GeocodeSearch geocoderSearch;

    private void getAddress(LatLng latLng) {
        this.latLng = latLng;
        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
        RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(latLng.latitude, latLng.longitude), 200, GeocodeSearch.AMAP);
        geocoderSearch.getFromLocationAsyn(query);
    }

    String address;

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int rCode) {
        if (rCode == 1000 && regeocodeResult != null) {
            address = regeocodeResult.getRegeocodeAddress().getFormatAddress() + "";
            if (!Utility.isEmpty(regeocodeResult.getRegeocodeAddress().getCity())){
                PreferenceUtils.setPrefString(this, "city",regeocodeResult.getRegeocodeAddress().getCity());
                PreferenceUtils.setPrefString(this, "district", regeocodeResult.getRegeocodeAddress().getDistrict());
                PreferenceUtils.setPrefString(this, "provider", regeocodeResult.getRegeocodeAddress().getProvince());
            }
            LogUtils.e("address=" + address+regeocodeResult.getRegeocodeAddress().getCity()+ regeocodeResult.getRegeocodeAddress().getProvince());
            text_address.setText(address + "");
            rl_tab.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }


    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    @TargetApi(23)
    private List<String> findDeniedPermissions(String[] permissions) {
        try {
            List<String> needRequestPermissonList = new ArrayList<String>();
            if (Build.VERSION.SDK_INT >= 23 && getApplicationInfo().targetSdkVersion >= 23) {
                for (String perm : permissions) {
                    if (checkMySelfPermission(perm) != PackageManager.PERMISSION_GRANTED || shouldShowMyRequestPermissionRationale(perm)) {
                        if (!needCheckBackLocation && BACK_LOCATION_PERMISSION.equals(perm)) {
                            continue;
                        }
                        needRequestPermissonList.add(perm);
                    }
                }
            }
            return needRequestPermissonList;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    private int checkMySelfPermission(String perm) {
        try {
            Method method = getClass().getMethod("checkSelfPermission", new Class[]{String.class});
            Integer permissionInt = (Integer) method.invoke(this, perm);
            return permissionInt;
        } catch (Throwable e) {
        }
        return -1;
    }


    private static final int PERMISSON_REQUESTCODE = 0;

    private boolean shouldShowMyRequestPermissionRationale(String perm) {
        try {
            Method method = getClass().getMethod("shouldShowRequestPermissionRationale", new Class[]{String.class});
            Boolean permissionInt = (Boolean) method.invoke(this, perm);
            return permissionInt;
        } catch (Throwable e) {
        }
        return false;
    }

    /**
     * 检测是否说有的权限都已经授权
     *
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    private boolean verifyPermissions(int[] grantResults) {
        try {
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return true;
    }

    @TargetApi(23)
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] paramArrayOfInt) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                if (requestCode == PERMISSON_REQUESTCODE) {
                    if (!verifyPermissions(paramArrayOfInt)) {
                        msg = "当前应用缺少定位权限。请点击设置权限打开所需权限";
                        showFaiingDialog();
                        isNeedCheck = false;
                    } else {
                        isNeedCheck = true;
                        init();
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


}
