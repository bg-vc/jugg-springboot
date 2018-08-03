package com.jugg.vince.springboot.common.core;

/**
 * Author:   Vince
 * Date:     2018/7/24 下午11:05
 * Description:
 */
public class ProjectConstant {
    // 业务名称
    public static final String BIZ_NAME = "demo";
    // 基础包名称
    public static final String BASE_PACKAGE = "com.jugg.vince.springboot";
    // 业务包名称
    public static final String BIZ_PACKAGE = BASE_PACKAGE + "." + BIZ_NAME;
    // model所在包
    public static final String MODEL_PACKAGE = BIZ_PACKAGE + ".model";
    // dao所在包
    public static final String DAO_PACKAGE = BIZ_PACKAGE + ".dao";
    // xml所在包
    public static final String XML_PACKAGE = "mapper." + BIZ_NAME;
    // servicde所在包
    public static final String SERVICE_PACKAGE = BIZ_PACKAGE + ".service";
    // serviceImpl所在包
    public static final String SERVICE_IMPL_PACKAGE = BIZ_PACKAGE + ".service.impl";
    // mapper插件基础接口的限定名
    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".common.core.Mapper";


}
