package com.roy.safeyapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roy.safeyapp.R;

public class SjfdSetup2Activity extends BaseSetupActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjfd_setup2);
    }

    @Override
    protected void nextActivity() {
        start(SjfdSetup3Activity.class);
    }

    @Override
    protected void prevActivity() {
        start(SjfdSetup1Activity.class);
    }
}
