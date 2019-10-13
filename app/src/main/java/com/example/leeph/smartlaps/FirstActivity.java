package com.example.leeph.smartlaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.leeph.smartlaps.Drug.DrugActivity;
import com.example.leeph.smartlaps.Experiment.ExperimentActivity;
import com.example.leeph.smartlaps.Tool.ToolActivity;

public class FirstActivity extends AppCompatActivity {

    private Button btnSafety, btnDrug, btnExperience, btnTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

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


    }
}
