package com.example.leeph.smartlaps.Tool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leeph.smartlaps.R;

public class ToolDetailActivity extends AppCompatActivity {
    TextView txtToolTitle, txtToolUse, txtToolCaution;
    ImageView imgToolTitle, imgToolDetail;
    String strTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_detail);

        txtToolTitle = (TextView) findViewById(R.id.txtToolTitle);
        txtToolUse = (TextView) findViewById(R.id.txtToolUse);
        txtToolCaution = (TextView) findViewById(R.id.txtToolCaution);

        imgToolTitle = (ImageView) findViewById(R.id.imgToolTitle);
        imgToolDetail = (ImageView) findViewById(R.id.imgToolDetail);

        strTool = getIntent().getStringExtra("tool");

        if (strTool.equals("btnRefluxCondenser")) {
            txtToolTitle.setText(getString(R.string.strRefluxCondenser));
            txtToolUse.setText(getString(R.string.strRefluxCondenserUse));
            txtToolCaution.setText(getString(R.string.strRefluxCondenserCaution));
            imgToolTitle.setImageResource(R.drawable.img_reflux_condenser);
            imgToolDetail.setImageResource(R.drawable.img_reflux_condenser_example);
        }

    }
}
