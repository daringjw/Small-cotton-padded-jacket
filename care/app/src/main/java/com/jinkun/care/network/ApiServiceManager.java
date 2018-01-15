package com.jinkun.care.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jinkun.care.Constant;
import com.jinkun.care.network.api.ApiStationService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Created by coderwjq on 2017/8/15 16:46.
 * @Desc 网络请求框架封装
 */

public class ApiServiceManager {
    public static final String RESPONSE_SUCCESS = "success";
    public static final String RESPONSE_FAILED = "failed";
    private static final int TIMEOUT_CONNECT = 5;
    private static final int TIMEOUT_READ = 5;
    private static final int TIMEOUT_WRITE = 5;
    private static ApiServiceManager mInstance;

    private String mAccessToken;
    private Retrofit mRetrofit;
    private Map<String, Object> mApiServiceMap = new HashMap<>();

    private ApiServiceManager() {
    }

    public static ApiServiceManager getInstance() {
        if (mInstance == null) {
            synchronized (ApiServiceManager.class) {
                if (mInstance == null) {
                    mInstance = new ApiServiceManager();
                }
            }
        }
        return mInstance;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    /**
     * 获取API对应的接口定义文件
     *
     * @param service
     * @param <T>
     * @return
     */
    public <T> T getApiService(Class<T> service) {
        if (mRetrofit == null) {
            setupRetrofit();
        }

        String className = service.getName();

        if (mApiServiceMap.containsKey(className)) {
            return (T) mApiServiceMap.get(className);
        } else {
            // create
            T serviceInstance = mRetrofit.create(service);
            mApiServiceMap.put(className, serviceInstance);
            return serviceInstance;
        }
    }

    /**
     * 初始化retrofit
     */
    private void setupRetrofit() {
        final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        final OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder();
        okHttpClient.connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS);
        okHttpClient.readTimeout(TIMEOUT_READ, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS);

        okHttpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                Request.Builder builder = request.newBuilder().addHeader("User-Agent", "care_android");
                if (mAccessToken != null) {
                    // 默认添加token字段的请求行
                    builder.addHeader("token", mAccessToken);
                }

                request = builder.build();

                Response response = chain.proceed(request);
                return response;
            }
        });

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient.build())
                .build();
    }

    public ApiStationService apiStationService() {
        return ApiServiceManager.getInstance().getApiService(ApiStationService.class);
    }
}
