package com.hitherejoe.androidboilerplate.data.remote;

import android.content.Context;

import com.hitherejoe.androidboilerplate.BuildConfig;
import com.hitherejoe.androidboilerplate.data.ProfitConstants;
import com.hitherejoe.androidboilerplate.data.model.Config;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ConfigService {

    String ENDPOINT = ProfitConstants.CONFIG_ENDPOINT;

    @GET("profit-config.json")
    Observable<Config> getConfig();

    class Factory {

        public static ConfigService makeConfigService(Context context) {
            OkHttpClient okHttpClient = new OkHttpClient();
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                    : HttpLoggingInterceptor.Level.NONE);
//            okHttpClient.interceptors().add(logging);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ConfigService.ENDPOINT)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(ConfigService.class);
        }

    }
}
