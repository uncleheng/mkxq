package com.mkxq.common.utils

/**
 * @Description:全局常量
 * @author ziheng
 * @date 2022/12/5 17:31
 */
class Constants {
    companion object {
        const val WEB_TITLE: String = "web_title"
        const val WEB_LINK: String = "web_link"
        const val MIN_CLICK_DELAY_TIME: Int = 1000
        const val PATH_WEB: String = "/kb_common/ui/WebActivity"
        const val PATH_LOGIN: String = "/kb_user/ui/loginActivity"

        //kv
        const val USER_NAME: String = "user_name"
        const val USER_COOKIE: String = "user_cookie"
        const val FIRST_OPEN: String = "first_open"

        //http
        const val HTTP_SUCCESS = 0
        const val HTTP_AUTH_INVALID = -1001
    }
}