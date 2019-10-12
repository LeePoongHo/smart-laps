package com.example.leeph.smartlaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DrugActivity extends AppCompatActivity {
    private Button btnPVA,btnWater,btnEthanol,btnToluene,btnBenzene;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);
        btnPVA = (Button) findViewById(R.id.btnPVA);
        btnWater = (Button) findViewById(R.id.btnWater);
        btnEthanol = (Button) findViewById(R.id.btnEthanol);
        btnToluene = (Button) findViewById(R.id.btnToluene);
        btnBenzene = (Button) findViewById(R.id.btnBenzene);

        btnPVA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDetailActivity("pva");
            }
        });
        btnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDetailActivity("water");
            }
        });
        btnEthanol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDetailActivity("ethanol");
            }
        });
        btnToluene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDetailActivity("toluene");
            }
        });
        btnBenzene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDetailActivity("benzene");
            }
        });
    }

    private void startDetailActivity(String drug) {
        Intent intent = new Intent(DrugActivity.this, DrugDetailActivity.class);
        intent.putExtra("drug", drug);
        startActivity(intent);
    }
}
