package com.bignerdranch.android.rxjavasampelswlh.network;

import com.bignerdranch.android.rxjavasampelswlh.network.api.ZbApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/6/11/011.
 */

public class Network {
    private static final OkHttpClient sOkHttpClient = new OkHttpClient();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private static Converter.Factory gsonConvertFactory = GsonConverterFactory.create();
    private static ZbApi mZbApi;

    public static ZbApi zbApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(sOkHttpClient)
                .baseUrl("http://www.zhuangbi.info/")
                .addConverterFactory(gsonConvertFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();

        mZbApi = retrofit.create(ZbApi.class);

        return mZbApi;
    }
}