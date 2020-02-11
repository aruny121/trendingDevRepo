package com.arunyadav.trendingdev;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.print("branch test");
        setContentView(R.layout.activity_dashboard);
    }
}
