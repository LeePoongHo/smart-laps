package com.example.leeph.smartlaps;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.leeph.smartlaps.Drug.DrugActivity;
import com.example.leeph.smartlaps.Experiment.ExperimentActivity;
import com.example.leeph.smartlaps.Notice.NoticeDetailActivity;
import com.example.leeph.smartlaps.Notice.NoticeListAdapter;
import com.example.leeph.smartlaps.Notice.NoticeWriteActivity;
import com.example.leeph.smartlaps.Service.Notice;
import com.example.leeph.smartlaps.Service.ServerService;
import com.example.leeph.smartlaps.Service.ServerServiceClass;
import com.example.leeph.smartlaps.Tool.ToolActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static java.net.HttpURLConnection.HTTP_OK;

public class FirstActivity extends AppCompatActivity {

    private Button btnSafety, btnDrug, btnExperience, btnTool;
    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;
    boolean bLog = false; // false : 로그아웃 상태

    @Override
    protected void onStart() {
        String memId = MySharedPreference.getInstance().getPreferences(this,"mem_id");
        if (memId.isEmpty()){
            doLogout();
        }
        super.onStart();
    }

    @Override
    protected void onResume() {
        String memId = MySharedPreference.getInstance().getPreferences(this,"mem_id");
        if (memId.isEmpty()){
            doLogout();
        }
        noticeList.clear();
        callNoticeList();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        FloatingActionButton floatingActionButton = findViewById(R.id.fab_1);


        noticeListView = findViewById(R.id.noticeListView);
        noticeList = new ArrayList<>();
        adapter = new NoticeListAdapter(getApplicationContext(), noticeList);
        noticeListView.setAdapter(adapter);

        noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NoticeDetailActivity.class);
                intent.putExtra("title", noticeList.get(position).getTitle());
                intent.putExtra("name", noticeList.get(position).getMem_name());
                intent.putExtra("date", noticeList.get(position).getWrite_date());
                startActivity(intent);

            }
        });

        btnSafety = findViewById(R.id.menuBtn1);
        btnDrug = findViewById(R.id.menuBtn2);
        btnTool = findViewById(R.id.menuBtn3);
        btnExperience = findViewById(R.id.menuBtn4);

        btnSafety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, SafetyActivity.class);
                startActivity(intent);
            }
        });

        btnDrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, DrugActivity.class);
                startActivity(intent);
            }
        });

        btnTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, ToolActivity.class);
                startActivity(intent);
            }
        });

        btnExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, ExperimentActivity.class);
                startActivity(intent);
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NoticeWriteActivity.class);
                startActivity(intent);
            }
        });

    }

    private void callNoticeList() {
        ServerService serverService = ServerServiceClass.createRetrofit().create(ServerService.class);
        Call<JsonArray> getNoticeListCall = serverService.getNoticeList();
        getNoticeListCall.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.code() == HTTP_OK) {
                    JsonArray jsonArray = response.body();
                    int i = jsonArray.size();
                    while (--i >= 0) {
                        Notice notice = new Gson().fromJson(jsonArray.get(i), Notice.class);
                        noticeList.add(notice);
                        Log.i("공지사항", notice.getTitle());
                    }
                    adapter.notifyDataSetChanged();
                    Log.i("리턴 값", response.message() + " / " + response.body().size());
                } else {
                    Toast.makeText(getApplicationContext(), "아이디나 비밀번호를 확인해 주세요", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "네트워크 오류", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // 메뉴버튼이 처음 눌러졌을 때 실행되는 콜백메서드
        // 메뉴버튼을 눌렀을 때 보여줄 menu 에 대해서 정의
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d("test", "onCreateOptionsMenu - 최초 메뉴키를 눌렀을 때 호출됨");
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        /*if(bLog){ // 로그인 한 상태: 로그인은 안보이게, 로그아웃은 보이게
            menu.getItem(0).setEnabled(true);
            menu.getItem(1).setEnabled(false);
        }else{ // 로그 아웃 한 상태 : 로그인 보이게, 로그아웃은 안보이게
            menu.getItem(0).setEnabled(false);
            menu.getItem(1).setEnabled(true);
        }

        bLog = !bLog;   // 값을 반대로 바꿈*/

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 메뉴의 항목을 선택(클릭)했을 때 호출되는 콜백메서드
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d("test", "onOptionsItemSelected - 메뉴항목을 클릭했을 때 호출됨");

        int id = item.getItemId();

        switch(id) {
            case R.id.menu_logout:
                Toast.makeText(getApplicationContext(), "로그아웃",
                        Toast.LENGTH_SHORT).show();
                doLogout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void doLogout() {
        MySharedPreference.getInstance().removeAllPreferences(FirstActivity.this);
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
