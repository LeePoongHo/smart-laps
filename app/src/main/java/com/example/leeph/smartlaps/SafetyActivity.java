package com.example.leeph.smartlaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SafetyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);

        Button chemistryBtn = (Button)findViewById(R.id.chemistryBtn);
        Button basicruleBtn = (Button) findViewById(R.id.basicruleBtn);

        chemistryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SafetyActivity.this, ChemistryActivity.class);
                startActivity(intent);
            }
        });

        basicruleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SafetyActivity.this, SafetyRuleActivity.class);
                startActivity(intent);
            }
        });


    }
}
