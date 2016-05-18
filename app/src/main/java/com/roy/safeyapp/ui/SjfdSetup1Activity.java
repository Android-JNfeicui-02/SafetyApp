package com.roy.safeyapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.roy.safeyapp.R;
import com.roy.safeyapp.utils.Constants;
import com.roy.safeyapp.utils.SpUtils;

public class SjfdSetup1Activity extends BaseSetupActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
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
