package com.example.leeph.smartlaps.Experiment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.leeph.smartlaps.R;

public class ExperimentActivity extends AppCompatActivity {

    Button classBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final int[] selectedItem = {0};

        classBtn = (Button) findViewById(R.id.class1);
        classBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[]{"1학기", "2학기"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(ExperimentActivity.this);
                dialog  .setTitle("학기를 선택하세요.")
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedItem[0] = which;
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ExperimentActivity.this, items[selectedItem[0]], Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ExperimentActivity.this, ExperimentSeletedClass.class);
                                startActivity(intent);
                            }
                        })
                        .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ExperimentActivity.this, "선택 취소", Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog.create();
                dialog.show();
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
