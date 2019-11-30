package com.example.leeph.smartlaps.Service;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerService {

    @POST("LoginProc.jsp")
    Call<JsonElement> postLogin(
            @Query("mem_id") String mem_id,
            @Query("mem_passwd") String mem_passwd
    );

    @POST("IdCheck.jsp")
    Call<ResponseBody> postIdCheck(
            @Query("mem_id") String mem_id
    );

    @POST("MemberInsert.jsp")
    Call<String> postRegister(
            @Query("mem_id") String mem_id,
            @Query("mem_passwd") String mem_passwd,
            @Query("mem_email") String mem_email,
            @Query("mem_sex") String mem_sex,
            @Query("mem_name") String mem_name
    );

    @GET("NoticeList.jsp")
    Call<JsonArray> getNoticeList();

    @FormUrlEncoded
    @POST("NoticeProc.jsp")
    Call<String> postNotice(
            @Field("notice") String notice
    );


}
