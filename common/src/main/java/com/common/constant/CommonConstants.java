package com.common.constant;

/***
 **@project: cli
 **@description:
 **@Author: twj
 **@Date: 2019/08/27
 **/
public class CommonConstants {


    /**************************************
     *      *****
     *    **    ***
     *   **      ***
     *   **
     *    **      ***
     *     **    ***
     *       *****
     *                      common constants**/
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final long NUM = 1L;
    public static final String NULL = "null";

    /**************************************
     * *       *       *
     *  *     * *     *
     *   *   *   *   *
     *    ***     ***       workflow constant**/
    public static final Integer MINIUIMSIZE = 2;

    public static final String SUSPEND = "suspend";

    public static final String RUNNING = "running";

    public static final String BPMNSUBFIX = "bpmn";

    public static final String ZIPSUBFIX = "zip";

    public static final String NOBODY = "nobody";

    public static final String FINISHED = "finished";

    public static final String START = "start";

    public static final String SELF = "self";

    public static final String ASSIGN = "assign";

    public static final String USERTASK = "userTask";

    /****
     *  *******
     *  **
     *  *******
     *  **
     *  *******              encrypt constant**/
    public static final String JWT_KEY = "twj_jien2018";

    public static final String AES_KEY = "twl_jien2018_007";

    public static final String IV = "1234567812345678";

    public static final String X_ACCESS_TOKEN = "X-Access-Token";

    public static final String PREFIX_USER_TOKEN = "USER_TOKEN";

    public static final long EXPIRE_TIME = 30 * 60 * 1000;

    /***     *****
     *     **     **
     *    **       **
     *    **       **
     *     **     **
     *       ******
     **            **                         quartz**/
    public static final String FROZEN = "FROZEN";

    public static final String UNFROZEN = "UNFROZEN";

    public static final String INIT = "600";

    public static final String HTTP = "http";

    public static final String KAFKA = "kafka";

    public static final String REDIS = "redis";
}
