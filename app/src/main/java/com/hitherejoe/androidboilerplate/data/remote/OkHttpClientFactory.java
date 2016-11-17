package com.hitherejoe.androidboilerplate.data.remote;

import android.content.Context;

import com.hitherejoe.androidboilerplate.BuildConfig;
import com.hitherejoe.androidboilerplate.data.ProfitConstants;
import com.hitherejoe.androidboilerplate.util.CacheUtils;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Srihari S Reddy on 18/11/16.
 */

public class OkHttpClientFactory {


    public static OkHttpClient getHttpClient(Context context) {

        Cache httpCache = new Cache(CacheUtils.getDiskCacheDir(context.getApplicationContext(),
                ProfitConstants.OKHTTP_CACHE_DIR),
                ProfitConstants.HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(httpCache)
                .addInterceptor(logging)
                .build();

        return okHttpClient;

    }
}
