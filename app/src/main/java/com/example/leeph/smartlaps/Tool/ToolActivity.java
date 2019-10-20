package com.example.leeph.smartlaps.Tool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.leeph.smartlaps.R;

public class ToolActivity extends AppCompatActivity {
    Button btnHeatingMantle,btnElectronicScale,btnAgitator,btnHotPlate,btnBeaker,btnErlenmeyerFlask,btnMeasuringFlask,
    btnMeasuringCylinder,btnSuctionFlask,btnBuchnerFunnel,btnOstwald,btnClamp,btnStand,btnDeanStarkTrap,btnRefluxCondenser,btnPipette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnHeatingMantle = (Button) findViewById(R.id.btnHeatingMantle);
        btnElectronicScale = (Button) findViewById(R.id.btnElectronicScale);
        btnAgitator = (Button) findViewById(R.id.btnAgitator);
        btnHotPlate = (Button) findViewById(R.id.btnHotPlate);
        btnBeaker = (Button) findViewById(R.id.btnBeaker);
        btnErlenmeyerFlask = (Button) findViewById(R.id.btnErlenmeyerFlask);
        btnMeasuringFlask = (Button) findViewById(R.id.btnMeasuringFlask);
        btnMeasuringCylinder = (Button) findViewById(R.id.btnMeasuringCylinder);
        btnSuctionFlask = (Button) findViewById(R.id.btnSuctionFlask);
        btnBuchnerFunnel = (Button) findViewById(R.id.btnBuchnerFunnel);
        btnOstwald = (Button) findViewById(R.id.btnOstwald);
        btnClamp = (Button) findViewById(R.id.btnClamp);
        btnStand = (Button) findViewById(R.id.btnStand);
        btnDeanStarkTrap = (Button) findViewById(R.id.btnDeanStarkTrap);
        btnRefluxCondenser = (Button) findViewById(R.id.btnRefluxCondenser);
        btnPipette = (Button) findViewById(R.id.btnPipette);

        btnRefluxCondenser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ToolActivity.this,ToolDetailActivity.class);
                intent.putExtra("tool","btnRefluxCondenser");
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
