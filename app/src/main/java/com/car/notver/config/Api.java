package com.car.notver.config;

public interface Api {
    /******获取验证码******/
    String GET_MOBILE = Config.getOpenNewApi() + "/member/getregistermembermobilevcode";
    int GET_MOBILE_ID = 100001;

    /******注册******/
    String GET_REGISTER = Config.getOpenNewApi() + "/member/memberregister";
    int GET_REGISTER_ID = 100002;


    /******登录******/
    String GET_LOGIN = Config.getOpenNewApi() + "/member/memberlogin";
    int GET_LOGIN_ID = 100003;

    /******积分******/
    String GET_COINS = Config.getOpenNewApi() + "/integral/getcoins";
    int GET_COINS_ID = 100004;

    /******积分******/
    String GET_UPDATE = Config.getOpenNewApi() + "/member/forgotmemberpassword";
    int GET_UPDATE_ID = 100005;
    /******获取验证码******/
    String GET_MOBILCODE = Config.getOpenNewApi() + "/member/getmembermobilevcode";
    int GET_MOBILCODE_ID = 100006;


    /******文件上传******/
    String GET_UPDATELOAD = Config.getOpenNewApi() + "/upload/uploadstorelogo";
    int GET_GET_UPDATELOAD_ID = 100007;

    /******门店新增******/
    String GET_STOREINFO = Config.getOpenNewApi() + "/store/saveStoreInfo";
    int GET_STOREINFO_ID = 100008;

    /******门店列表******/
    String GET_INFO = Config.getOpenNewApi() + "/store/queryStoreInfoData";
    int GET_INFOO_ID = 100009;

    /******门店列表******/
    String GET_PROPER = Config.getOpenNewApi() + "/store/saveStorePropertiesInfo";
    int GET_PROPER_ID = 100010;


    /******门店列表******/
    String GET_IMAGE = Config.getOpenNewApi() + "/store/queryStoreImageInfoData";
    int GET_IMAGE_ID = 100011;


    /******新增门店图片******/
    String GET_IMAGEINFO = Config.getOpenNewApi() + "/store/saveStoreImageInfo";
    int GET_IMAGEINFO_ID = 100012;

    /******门店图片查询******/
    String GET_BYID = Config.getOpenNewApi() + "/store/getStoreImageInfoById";
    int GET_BYID_ID = 100013;

    /******预约列表******/
    String GET_ADVNCE = Config.getOpenNewApi() + "/store/queryStoreAdvanceInfoData";
    int GET_ADVNCE_ID = 100014;

    /******预约列表******/
    String GET_REMOVE = Config.getOpenNewApi() + "/store/removeStoreImageInfo";
    int GET_REMOVE_ID = 100015;

    /******资质查询******/
    String GET_REMOVE_STORE = Config.getOpenNewApi() + "/store/getStorePropertiesInfoById";
    int GET_REMOVE_STORE_ID = 100016;

    /******预约状态查询******/
    String GET_ADVANCE_STORE = Config.getOpenNewApi() + "/store/receiveStoreAdvance";
    int GET_ADVANCE_STORE_ID = 100017;


    /******预约状态查询******/
    String GET_ADVANCE_BILL = Config.getOpenNewApi() + "/store/getStoreAdvanceBillById";
    int GET_ADVANCE_BILL_ID = 100018;

    /******新增预约开单******/
    String GET_ADVANCE_SAVE = Config.getOpenNewApi() + "/store/saveStoreAdvanceBill";
    int GET_ADVANCE_SAVE_ID = 100019;
    /******首页数据******/
    String GET_SUM_SAVE = Config.getOpenNewApi() + "/store/queryStoreSumData";
    int GET_SUM = 100019;
    /******门店绑定车辆列表******/
    String GET_CAR_SAVE = Config.getOpenNewApi() + "/store/queryStoreBandingCarInfoData";
    int GET_CAR_SAVE_ID = 100020;

    /******门店到期提醒******/
    String GET_CAR_DATA = Config.getOpenNewApi() + "/store/queryStoreCarMaintainInfoData";
    int GET_CAR_DATA_ID = 100021;

    /******虚拟货币列表******/
    String GET_COINS_LIST = Config.getOpenNewApi() + "/integral/getcoins";
    int GET_COINS_LIST_ID = 100022;


    /******积分查询******/
    String GET_COINS_INTER = Config.getOpenNewApi() + "/integral/getmemberintegral";
    int GET_COINS_INTER_ID = 100023;

    /******门店预约******/
    String GET_COINS_INTERBILL = Config.getOpenNewApi() + "/store/queryStoreAdvanceBillData";
    int GET_COINS_INTERBILL_ID = 100024;


    /******排行******/
    String GET_COINS_DAILY = Config.getOpenNewApi() + "/store/queryStoreCarDailyReportInfoData";
    int GET_COINS_DAILY_ID = 100025;

    /******自主开单******/
    String GET_COINS_DAILY_BILL = Config.getOpenNewApi() + "/store/saveStoreAutonomyBill";
    int GET_COINS_DAILY_BILL_ID = 100026;

    /******门店列表******/
    String GET_INTERGRAL = Config.getOpenNewApi() + "/integral/getIntegralDetailList";
    int GET_INTERGRAL_ID = 100027;

    /******版本更新******/
    String GET_INTERGRAL_VERSION = Config.getOpenNewApi() + "/app/getandroidversioninfo";
    int GET_INTERGRAL_VERSION_ID = 100028;

    /******注册成功******/
    String GET_PUSH_VERSION = Config.getOpenNewApi() + "/member/uploadjgandroidregisterid";
    int GET_PUSH_VERSION_ID = 100029;


    /******注册成功******/
    String GET_MSG_VERSION = Config.getOpenNewApi() + "/member/getmembermessagelist";
    int GET_MSG_VERSION_ID = 100030;


    /******添加客户******/
    String GET_USER_VERSION = Config.getOpenNewApi() + "/store/saveStoreUserInfo";
    int GET_USER_VERSION_ID = 100031;

}
