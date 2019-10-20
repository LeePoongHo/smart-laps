package com.example.leeph.smartlaps.Experiment;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.leeph.smartlaps.Adapter.SlidePagerAdapter;
import com.example.leeph.smartlaps.Experiment.fragments.pageFragment1;
import com.example.leeph.smartlaps.Experiment.fragments.pageFragment2;
import com.example.leeph.smartlaps.Experiment.fragments.pageFragment3;
import com.example.leeph.smartlaps.R;
import com.example.leeph.smartlaps.ViewPagerIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class ExperimentDetailActivity extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
/*
        ViewPagerIndicatorView indicatorView = (ViewPagerIndicatorView) findViewById(R.id.indicator);

        int marginRight = 15;
        int totalItemCount = 3;
        indicatorView.init(totalItemCount, R.drawable.default_dot, R.drawable.selected_dot, marginRight);
        indicatorView.setSelection(3);
*/
        List<Fragment> list = new ArrayList<>();
        list.add(new pageFragment1());
        list.add(new pageFragment2());
        list.add(new pageFragment3());

        pager = (ViewPager)findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);

        pager.setAdapter(pagerAdapter);

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
