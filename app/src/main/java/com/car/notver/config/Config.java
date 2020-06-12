package com.car.notver.config;

public class Config {
    enum Environment {
        DEVELOPMENT, // 开发环境
        TEST, //
        PRODUCTION, // 线上环境
    }

    // 当前环境MI
    public static Environment env = Environment.TEST;
    // 开发环境地
    public static String DEVELOPMENT_PUBLIC_SERVER_URL = "http://kb.jkabe.com";
    // 测试环境地址
    public static String TEST_PUBLIC_SERVER_URL = "http://kb.jkabe.com";
    // 线上环境地址
    public static String PRODUCTION_PUBLIC_SERVER_URL = "http://kb.jkabe.com";

    /********************新版本Api********************/
    public static String getOpenNewApi() {
        if (Config.env == Environment.DEVELOPMENT) {
            return Config.DEVELOPMENT_PUBLIC_SERVER_URL;
        } else if (Config.env == Environment.TEST) {
            return Config.TEST_PUBLIC_SERVER_URL;
        } else if (Config.env == Environment.PRODUCTION) {
            return Config.PRODUCTION_PUBLIC_SERVER_URL;
        }
        return "";
    }
}
