package com.example.leeph.smartlaps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leeph.smartlaps.Service.RegisterBean;
import com.example.leeph.smartlaps.Service.ServerService;
import com.example.leeph.smartlaps.Service.ServerServiceClass;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.net.HttpURLConnection.HTTP_OK;

public class LoginActivity extends AppCompatActivity {

    private EditText txtId, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtId = findViewById(R.id.txtId);
        txtPassword = findViewById(R.id.txtPassword);

        TextView information = findViewById(R.id.information);
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, Pop.class));
            }
        });

        TextView registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        TextView loginButton = findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerService serverService = ServerServiceClass.createRetrofit().create(ServerService.class);
                Call<JsonElement> loginCall = serverService.postLogin(txtId.getText().toString(), txtPassword.getText().toString());
                loginCall.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        if (response.code() == HTTP_OK) {
                            JsonElement jsonElement = response.body();
                            Log.i("리턴", jsonElement.toString());
                            RegisterBean member = new Gson().fromJson(response.body(), RegisterBean.class);
                            if (jsonElement.toString().equals("{}")) {
                                Toast.makeText(LoginActivity.this, "아이디나 비밀번호를 확인해 주세요", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.i("리턴", member.getMem_id());
                                Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                                MySharedPreference.getInstance().setPreferences(LoginActivity.this, "mem_id", member.getMem_id());
                                MySharedPreference.getInstance().setPreferences(LoginActivity.this, "mem_name", member.getMem_name());
                                MySharedPreference.getInstance().setPreferences(LoginActivity.this, "mem_email", member.getMem_email());
                                MySharedPreference.getInstance().setPreferences(LoginActivity.this, "mem_sex", member.getMem_sex());
                                MySharedPreference.getInstance().setPreferences(LoginActivity.this, "mem_major", member.getMem_major());

                                Intent registerIntent = new Intent(LoginActivity.this, FirstActivity.class);
                                startActivity(registerIntent);
                                finish();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "서버 에러", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "네트워크 오류", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }


}
