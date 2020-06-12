package com.car.notver.util;


import com.car.notver.bean.Bespoke;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.FileInfo;
import com.car.notver.bean.Integral;
import com.car.notver.bean.KeepInfo;
import com.car.notver.bean.Massage;
import com.car.notver.bean.Money;
import com.car.notver.bean.Ordered;
import com.car.notver.bean.StoreInfo;
import com.car.notver.bean.UserInfo;
import com.car.notver.bean.Verison;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Json解析
 *
 * @author Administrator
 */

public class JsonParse {
    private static JsonParse jsonParse = null;
    public CommonalityModel commonality;

    public static synchronized JsonParse getInstance() {
        if (jsonParse == null)
            jsonParse = new JsonParse();
        return jsonParse;
    }


    public static UserInfo getUserInfo(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        UserInfo userInfo = (UserInfo) JsonUtilComm.jsonToBean(jsonObject.toString(), UserInfo.class);
        return userInfo;
    }


    public static List<StoreInfo> getStoreJson(JSONObject object) {
        List<StoreInfo> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            StoreInfo info = (StoreInfo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), StoreInfo.class);
            infos.add(info);
        }
        return infos;
    }



    public static List<FileInfo> getStoreFileJson(JSONObject object) {
        List<FileInfo> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            FileInfo info = (FileInfo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), FileInfo.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<Bespoke> getBespokeJson(JSONObject object) {
        List<Bespoke> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            Bespoke info = (Bespoke) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), Bespoke.class);
            infos.add(info);
        }
        return infos;
    }

    public static Ordered getOrderedJson(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        Ordered userInfo = (Ordered) JsonUtilComm.jsonToBean(jsonObject.toString(), Ordered.class);
        return userInfo;
    }

    public static List<KeepInfo> getKeepInfo(JSONObject object) {
        List<KeepInfo> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            KeepInfo info = (KeepInfo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), KeepInfo.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<Money> getBespokemoniesJson(JSONObject object) {
        List<Money> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            Money info = (Money) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), Money.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<Ordered> getopenJson(JSONObject object) {
        List<Ordered> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            Ordered info = (Ordered) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), Ordered.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<KeepInfo> getopenKeepInfoJson(JSONObject object) {
        List<KeepInfo> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            KeepInfo info = (KeepInfo) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), KeepInfo.class);
            infos.add(info);
        }
        return infos;
    }

    public static List<Integral> getopenKeepaInterJson(JSONObject object) {
        List<Integral> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            Integral info = (Integral) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), Integral.class);
            infos.add(info);
        }
        return infos;
    }

    public static Verison getVerisonUserInfo(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        Verison verison = (Verison) JsonUtilComm.jsonToBean(jsonObject.toString(), Verison.class);
        return verison;
    }

    public static List<Massage> getMassageJson(JSONObject object) {
        List<Massage> infos = new ArrayList<>();
        JSONObject jsonObject = object.optJSONObject("result");
        JSONArray jsonArray = jsonObject.optJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            Massage info = (Massage) JsonUtilComm.jsonToBean(jsonArray.optJSONObject(i).toString(), Massage.class);
            infos.add(info);
        }
        return infos;
    }
}
