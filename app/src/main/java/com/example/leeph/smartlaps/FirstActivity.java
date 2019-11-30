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
import com.example.leeph.smartlaps.Tool.ToolActivity;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    private Button btnSafety, btnDrug, btnExperience, btnTool;
    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;
    boolean bLog = false; // false : 로그아웃 상태

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_1);

        noticeListView = (ListView)findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();
        noticeList.add(new Notice("공지사항입니다.", "이풍호", "2019-11-27 08:32:13"));
        noticeList.add(new Notice("공지사항입니다.", "이풍호", "2019-11-27 08:32:13"));
        noticeList.add(new Notice("공지사항입니다.", "이풍호", "2019-11-27 08:32:13"));
        noticeList.add(new Notice("공지사항입니다.", "이풍호", "2019-11-27 08:32:13"));
        noticeList.add(new Notice("공지사항입니다.", "이풍호", "2019-11-27 08:32:13"));
        noticeList.add(new Notice("공지사항입니다.", "이풍호", "2019-11-27 08:32:13"));
        noticeList.add(new Notice("공지사항입니다.", "이풍호", "2019-11-27 08:32:13"));
        noticeList.add(new Notice("공지사항입니다.", "이풍호", "2019-11-27 08:32:13"));

        adapter = new NoticeListAdapter(getApplicationContext(), noticeList);
        noticeListView.setAdapter(adapter);

        noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NoticeDetailActivity.class);
                intent.putExtra("title", noticeList.get(position).getNotice());
                intent.putExtra("name", noticeList.get(position).getName());
                intent.putExtra("date", noticeList.get(position).getDate());
                startActivity(intent);

            }
        });

        btnSafety = (Button) findViewById(R.id.menuBtn1);
        btnDrug = (Button) findViewById(R.id.menuBtn2);
        btnTool = (Button) findViewById(R.id.menuBtn3);
        btnExperience = (Button) findViewById(R.id.menuBtn4);

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
        Log.d("test", "onPrepareOptionsMenu - 옵션메뉴가 " +
                "화면에 보여질때 마다 호출됨");
        if(bLog){ // 로그인 한 상태: 로그인은 안보이게, 로그아웃은 보이게
            menu.getItem(0).setEnabled(true);
            menu.getItem(1).setEnabled(false);
        }else{ // 로그 아웃 한 상태 : 로그인 보이게, 로그아웃은 안보이게
            menu.getItem(0).setEnabled(false);
            menu.getItem(1).setEnabled(true);
        }

        bLog = !bLog;   // 값을 반대로 바꿈

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
            case R.id.menu_login:
                Toast.makeText(getApplicationContext(), "로그인 메뉴 클릭",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_logout:
                Toast.makeText(getApplicationContext(), "로그아웃 메뉴 클릭",
                        Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
