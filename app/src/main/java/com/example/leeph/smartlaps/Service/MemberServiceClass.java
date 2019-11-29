package com.example.leeph.smartlaps.Service;

import com.example.leeph.smartlaps.Config;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fopa on 2017-12-06.
 */

public class MemberServiceClass {
    static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new HeaderInterceptor());
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        return builder.build();
    }

    public static class HeaderInterceptor
            implements Interceptor {
        @Override
        public Response intercept(Chain chain)
                throws IOException {
            Request request = chain.request();
            request = request.newBuilder()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
                    /*.addHeader("X-Naver-Client-Id", "iF_wnemb4i37LTsQfDqI")
                    .addHeader("X-Naver-Client-Secret", "5qgHDxIX_J")*/
            Response response = chain.proceed(request);
            return response;
        }
    }

    public static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Config.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static final Retrofit retrofit = createRetrofit();
}
