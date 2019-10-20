package com.example.leeph.smartlaps.Experiment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.leeph.smartlaps.R;

public class ExperimentSeletedClass extends AppCompatActivity {

    Button ex4Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment_seleted_class);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ex4Btn = (Button) findViewById(R.id.ex4Btn);

        ex4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExperimentSeletedClass.this, ExperimentDetailActivity.class);
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
