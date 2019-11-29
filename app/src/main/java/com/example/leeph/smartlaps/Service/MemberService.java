package com.example.leeph.smartlaps.Service;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MemberService {

    @POST("LoginProc.jsp")
    Call<String> doLogin(
            @Query("mem_id") String mem_id,
            @Query("mem_passwd") String mem_passwd
    );

    @POST("IdCheck.jsp")
    Call<ResponseBody> doIdCheck(
            @Query("mem_id") String mem_id
    );

    @POST("MemberInsert.jsp")
    Call<String> doRegister(
            @Query("mem_id") String mem_id,
            @Query("mem_passwd") String mem_passwd,
            @Query("mem_email") String mem_email,
            @Query("mem_sex") String mem_sex,
            @Query("mem_name") String mem_name
    );


}
