package com.example.leeph.smartlaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ChemistryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemistry);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button FlammableLiquidsBtn = (Button) findViewById(R.id.FlammableLiquidsBtn);
        Button OxidantBtn = (Button) findViewById(R.id.oxidantBtn);
        Button AcidBtn = (Button) findViewById(R.id.acidBtn);
        Button ToxicBtn = (Button) findViewById(R.id.toxicBtn);

        FlammableLiquidsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChemistryActivity.this, FlammableLiquidsActivity.class);
                startActivity(intent);
            }
        });

        OxidantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChemistryActivity.this, OxidantActivity.class);
                startActivity(intent);
            }
        });

        AcidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChemistryActivity.this, AcidAlkalineCompoundsActivity.class);
                startActivity(intent);
            }
        });

        ToxicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChemistryActivity.this, ToxicCompoundActivity.class);
                startActivity(intent);
            }
        });
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
