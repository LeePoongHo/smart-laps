package com.example.leeph.smartlaps.Notice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.leeph.smartlaps.R;

public class NoticeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        TextView noticeTitle = (TextView) findViewById(R.id.editNoticeTitle);
        TextView noticeName = (TextView) findViewById(R.id.noticeName);
        TextView noticeDate = (TextView) findViewById(R.id.noticeDate);
        TextView noticeContent = (TextView) findViewById(R.id.editNoticeContent);

        noticeTitle.setText(intent.getStringExtra("title"));
        noticeName.setText(intent.getStringExtra("name"));
        noticeDate.setText(intent.getStringExtra("date"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
