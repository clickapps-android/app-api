package com.digital.app.config

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class AppConfig {
    val DEBUG_LEVEL_NONE = 0
    val DEBUG_LEVEL_BASIC = 1
    val DEBUG_LEVEL_HEADERS = 2
    val DEBUG_LEVEL_BODY = 3

    var OK_HTTP_CLIENT:OkHttpClient? = null
    var BASE_URL = ""
    var TIMEOUT_UNIT = TimeUnit.SECONDS
    var CONNECT_TIMEOUT: Long = 20//TimeUnit.SECONDS
    var READ_TIMEOUT: Long = 20//TimeUnit.SECONDS
    var WRITE_TIMEOUT: Long = 30//TimeUnit.SECONDS
    var DEBUG_LEVEL: Int = DEBUG_LEVEL_BODY
    var OBSERVER_ON_MAIN_THREAD: Boolean = true
    var ADAPTERS: List<AppApiAdapterComponent> = listOf()

}

fun Application.appConfig(config: AppConfig.() -> Unit) {
    val appConfig = AppConfig()
    config.invoke(appConfig)
    //updateConstants
    Constants.apply {
        OK_HTTP_CLIENT = appConfig.OK_HTTP_CLIENT
        BASE_URL = appConfig.BASE_URL
        TIMEOUT_UNIT = appConfig.TIMEOUT_UNIT
        CONNECT_TIMEOUT = appConfig.CONNECT_TIMEOUT
        READ_TIMEOUT = appConfig.READ_TIMEOUT
        WRITE_TIMEOUT = appConfig.WRITE_TIMEOUT
        OBSERVER_ON_MAIN_THREAD = appConfig.OBSERVER_ON_MAIN_THREAD
        ADAPTERS = appConfig.ADAPTERS

        DEBUG_LEVEL = when(appConfig.DEBUG_LEVEL){
            appConfig.DEBUG_LEVEL_NONE -> HttpLoggingInterceptor.Level.NONE
            appConfig.DEBUG_LEVEL_BASIC -> HttpLoggingInterceptor.Level.BASIC
            appConfig.DEBUG_LEVEL_HEADERS-> HttpLoggingInterceptor.Level.HEADERS
            appConfig.DEBUG_LEVEL_BODY -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.BODY
        }


    }
}