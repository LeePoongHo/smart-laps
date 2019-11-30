package com.example.leeph.smartlaps.Notice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leeph.smartlaps.FirstActivity;
import com.example.leeph.smartlaps.LoginActivity;
import com.example.leeph.smartlaps.MySharedPreference;
import com.example.leeph.smartlaps.R;
import com.example.leeph.smartlaps.Service.Notice;
import com.example.leeph.smartlaps.Service.ServerService;
import com.example.leeph.smartlaps.Service.ServerServiceClass;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.net.HttpURLConnection.HTTP_OK;

public class NoticeWriteActivity extends AppCompatActivity {

    private Button btnSave;
    private EditText editNoticeTitle, editNoticeContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_write);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSave = findViewById(R.id.btnSave);
        editNoticeTitle = findViewById(R.id.editNoticeTitle);
        editNoticeContent = findViewById(R.id.editNoticeContent);

        final String mem_id = MySharedPreference.getInstance().getPreferences(getApplicationContext(), "mem_id");
        final String mem_name = MySharedPreference.getInstance().getPreferences(getApplicationContext(), "mem_name");
        if (mem_id.isEmpty() || mem_name.isEmpty()) {
            doLogout();
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notice notice = new Notice();
                notice.setTitle(editNoticeTitle.getText().toString());
                notice.setContent(editNoticeContent.getText().toString());
                notice.setMem_id(mem_id);
                notice.setMem_name(mem_name);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
                Date currentTime = new Date();
                notice.setWrite_date(formatter.format(currentTime));
                ServerService serverService = ServerServiceClass.createRetrofit().create(ServerService.class);
                Call<String> postNotice = serverService.postNotice(new Gson().toJson(notice));
                postNotice.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.code() == HTTP_OK) {
                            Log.i("글쓰기", response.message() + " / " + response.body());
                            Toast.makeText(getApplicationContext(), "작성 완료", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "서버 에러", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "네트워크 오류", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void doLogout() {
        MySharedPreference.getInstance().removeAllPreferences(getApplicationContext());
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
