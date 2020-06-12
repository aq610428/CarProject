package com.car.notver.util;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.car.notver.base.BaseApplication;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import static android.content.Context.TELEPHONY_SERVICE;

/**
 * 作者：zt on 2017/7/22.
 * 项目名称：FoodWaiter
 * 类名：zt
 * 创建时间：2017/7/22 15:54
 */

public class OsUtil {

    /******生产设备唯一ID******/
    public static String getDeviceId() {
        String deviceId = "";
        try {
            deviceId = OsUtil.getDeviceUtil().toString();
            if ("0".equals(deviceId + "") || Utility.isEmpty(deviceId)) {
                deviceId = getUniqueDeviceId();
            }
        } catch (IllegalArgumentException ex) {

        }
        return deviceId;
    }


    /**
     * 生成设备唯一标识：IMEI、AndroidId、macAddress 三者拼接再 MD5
     *
     * @return
     */
    protected static final String PREFS_DEVICE_ID = "device_id";
    protected static UUID uuid;

    public static UUID getDeviceUtil() {
        CacheDiskUtils cacheDiskUtils = CacheDiskUtils.getInstance();
        if (uuid == null) {
            synchronized (OsUtil.class) {
                final String id = cacheDiskUtils.getString(PREFS_DEVICE_ID);
                if (id != null) {
                    uuid = UUID.fromString(id);
                    return uuid;
                } else {
                    final String androidId = Settings.Secure.getString(BaseApplication.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                    try {
                        if (!"9774d56d682e549c".equals(androidId)) {
                            uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                        } else {
                            @SuppressLint("MissingPermission") final String deviceId = ((TelephonyManager) BaseApplication.getContext().getSystemService(TELEPHONY_SERVICE)).getDeviceId();
                            uuid = deviceId != null ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")) : UUID.randomUUID();
                        }
                        cacheDiskUtils.put(PREFS_DEVICE_ID, uuid.toString());
                        return uuid;
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return uuid;
    }


    /**
     * 生成设备唯一标识：IMEI、AndroidId、macAddress 三者拼接再 MD5
     *
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getUniqueDeviceId() {
        String imei = "";
        String androidId = "";
        String macAddress = "";
        TelephonyManager telephonyManager = (TelephonyManager) BaseApplication.getContext().getSystemService(TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            imei = telephonyManager.getDeviceId();
        }
        ContentResolver contentResolver = BaseApplication.getContext().getContentResolver();
        if (contentResolver != null) {
            androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID);
        }
        WifiManager wifiManager = (WifiManager) BaseApplication.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            macAddress = wifiManager.getConnectionInfo().getMacAddress();
        }
        StringBuilder longIdBuilder = new StringBuilder();
        if (imei != null) {
            longIdBuilder.append(imei);
        }
        if (androidId != null) {
            longIdBuilder.append(androidId);
        }
        if (macAddress != null) {
            longIdBuilder.append(macAddress);
        }
        return Md5Util.byteArrayToHexString(longIdBuilder.toString().getBytes());
    }
}