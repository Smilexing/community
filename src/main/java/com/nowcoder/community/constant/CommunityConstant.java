package com.nowcoder.community.constant;

public interface CommunityConstant {

    /**
     * 成功
     */
    int ACTIVATION_SUCCESS = 0;

    /**
     * 重复
     */

    int ACTIVATION_REPEAT = 1;

    /**
     * 失败
     */

    int ACTIVATION_FAILURE = 2;

    /**
     * 默认状态的登录凭证超时时间
     */

    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    /**
     * 记住状态的登录凭证超时时间-勾选记住我
     */

    int REMEMBER_EXPIRED_SECONDES = 3600 * 24 * 10;

}
