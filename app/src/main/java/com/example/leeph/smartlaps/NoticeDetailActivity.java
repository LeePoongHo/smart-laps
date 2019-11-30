package com.example.leeph.smartlaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class NoticeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        TextView noticeTitle = (TextView) findViewById(R.id.noticeTitle);
        TextView noticeName = (TextView) findViewById(R.id.noticeName);
        TextView noticeDate = (TextView) findViewById(R.id.noticeDate);
        TextView noticeContent = (TextView) findViewById(R.id.noticeContent);

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
