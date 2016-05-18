package com.roy.safeyapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.roy.safeyapp.R;

public class SjfdSetup4Activity extends BaseSetupActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjfd_setup4);
    }

    @Override
    protected void nextActivity() {
        Toast.makeText(SjfdSetup4Activity.this, "设置完毕", Toast.LENGTH_SHORT)
             .show();
    }

    @Override
    protected void prevActivity() {
        start(SjfdSetup3Activity.class);
    }
}
