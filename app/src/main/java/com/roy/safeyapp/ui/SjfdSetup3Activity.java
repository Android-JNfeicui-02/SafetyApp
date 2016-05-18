package com.roy.safeyapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roy.safeyapp.R;

public class SjfdSetup3Activity extends BaseSetupActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjfd_setup3);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void nextActivity() {
        start(SjfdSetup4Activity.class);
    }

    @Override
    protected void prevActivity() {
        start(SjfdSetup2Activity.class);
    }
}
