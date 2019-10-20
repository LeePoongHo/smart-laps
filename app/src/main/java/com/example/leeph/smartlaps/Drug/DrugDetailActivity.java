package com.example.leeph.smartlaps.Drug;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leeph.smartlaps.Drug.Warn.WarnTolueneActivity;
import com.example.leeph.smartlaps.R;
import com.example.leeph.smartlaps.Drug.Warn.WarnPvaActivity;

public class DrugDetailActivity extends AppCompatActivity {
    private ImageView imageTitle;
    private TextView txtDetail;
    private Button btnWarn;
    private String strDrug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageTitle = (ImageView) findViewById(R.id.imageTitle);
        btnWarn = (Button) findViewById(R.id.btnWarn);
        txtDetail = (TextView) findViewById(R.id.txtDetail);

        strDrug = getIntent().getStringExtra("drug");

        if (strDrug.equals("toluene")) {
            imageTitle.setImageResource(R.drawable.img_toluene);
            txtDetail.setText(R.string.strTolueneDetail);
            btnWarn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DrugDetailActivity.this, WarnTolueneActivity.class);
                    startActivity(intent);
                }
            });
        }else {
            imageTitle.setImageResource(R.drawable.logo);
        }
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
