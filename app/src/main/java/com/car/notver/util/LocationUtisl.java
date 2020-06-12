package com.car.notver.util;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.car.notver.base.BaseApplication;
import com.car.notver.weight.PreferenceUtils;

/**
 * @author: zt
 * @date: 2020/5/14
 * @name:LocationUtisl
 */
public class LocationUtisl {

    /*******定位*****/
    public void getLocationAddres() {
        AMapLocationClient mLocationClient = null;
        AMapLocationListener mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                String address = aMapLocation.getAddress();
                PreferenceUtils.setPrefString(BaseApplication.getContext(),Constants.ADRESS,address);
            }
        };
        mLocationClient = new AMapLocationClient(BaseApplication.getContext());
        mLocationClient.setLocationListener(mLocationListener);
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。

        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);
        option.setOnceLocationLatest(true);
        option.setInterval(1000);
        option.setNeedAddress(true);
        option.setMockEnable(true);
        option.setHttpTimeOut(20000);
        mLocationClient.setLocationOption(option);
        mLocationClient.startLocation();
    }
}
