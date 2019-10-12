package com.example.leeph.smartlaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChemistryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemistry);

        Button chemistryDetailBtn1 = (Button) findViewById(R.id.chemistryDetailBtn1);

        chemistryDetailBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChemistryActivity.this, ChemistryDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
