package com.example.leeph.smartlaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button btnSafety = (Button) findViewById(R.id.menuBtn1);
        Button btnDrug = (Button) findViewById(R.id.menuBtn2);

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


    }
}
