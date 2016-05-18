package com.roy.safeyapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roy.safeyapp.R;

public class SjfdSetup1Activity extends BaseSetupActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjfd_setup1);
    }

    @Override
    protected void nextActivity() {
        start(SjfdSetup2Activity.class);
    }

    @Override
    protected void prevActivity() {

    }
}
