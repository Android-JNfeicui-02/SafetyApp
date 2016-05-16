package com.roy.safeyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.roy.safeyapp.R;
import com.roy.safeyapp.bean.HomeBean;
import com.roy.safeyapp.utils.Constants;
import com.roy.safeyapp.utils.SpUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    
    public static final int SJFD = 0;
    public static final int TXWS = 1;
    public static final int RJGL = 2;
    public static final int JCGL = 3;
    public static final int HCQL = 4;
    public static final int SZZX = 5;

    private GridView mGridView;




    Context mContext;

    private ArrayList<HomeBean> mDatas;

    private static final String TAG = "HomeActivity";

    private String[] desc = {"手机防盗", "通讯卫士",
                             "软件管理", "进程管理",
                             "缓存清理", "设置中心"};

    private int[] icons = {R.mipmap.icon_phonemgr, R.mipmap.icon_telmgr,
                           R.mipmap.icon_softmgr, R.mipmap.icon_rocket,
                           R.mipmap.icon_sdclean, R.mipmap.icon_filemgr};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 初始化数据
        initData();



        mGridView = (GridView) findViewById(R.id.gv_home);
        mGridView.setAdapter(new HomeAdapter());

        mGridView.setOnItemClickListener(this);


    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            HomeBean bean = new HomeBean();
            bean.pic = icons[i];
            bean.desc = desc[i];
            mDatas.add(bean);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // int String
        switch (position) {
            case SJFD:
                clickSJFD();

//                Toast.makeText(HomeActivity.this, "点击了" + position, Toast.LENGTH_SHORT)
//                     .show();
                break;

            case TXWS:
                Toast.makeText(HomeActivity.this, "点击了" + position, Toast.LENGTH_SHORT)
                     .show();
                break;

            case RJGL:
                Toast.makeText(HomeActivity.this, "点击了" + position, Toast.LENGTH_SHORT)
                     .show();

                Intent intent = new Intent(this, RjglActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void clickSJFD() {

        // 取出来password
        String password = SpUtils.getString(this, Constants.SJFD_PWD);
        Log.d(TAG, "clickSJFD: " + password);

        // TextUtils.isEmpty
        if (TextUtils.isEmpty(password)) {
            showSetupDialog();
        } else {
            showEnterDialog();
        }


    }

    private void showEnterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(this,R.layout.dialog_login,null);

        final EditText etPwd = (EditText) view.findViewById(R.id.et_enter_pwd);
        Button btnLogin = (Button) view.findViewById(R.id.btn_login);
        Button btnDismiss = (Button) view.findViewById(R.id.btn_dismiss);

        builder.setView(view);
        final AlertDialog dialog = builder.show();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPwd.getText()
                                   .toString()
                                   .trim();

                // 非空校验
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "输入的密码不能为空", Toast.LENGTH_SHORT)
                         .show();
                    return;
                }


                String savedPwd = SpUtils.getString(getApplicationContext(), Constants.SJFD_PWD);
                // 正确校验
                if (!savedPwd.equals(password)) {
                    Toast.makeText(getApplicationContext(), "输入密码有误", Toast.LENGTH_SHORT)
                         .show();
                    return;
                }

                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT)
                     .show();

                Intent intent = new Intent(HomeActivity.this, SjfdSetupActivity.class);
                startActivity(intent);
            }
        });

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void showSetupDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(getApplicationContext(), R.layout.dialog_setup, null);

        final EditText etPwd = (EditText) view.findViewById(R.id.et_pwd1);
        final EditText etConfirmPwd = (EditText) view.findViewById(R.id.et_pwd2);

        Button btnSubmit = (Button) view.findViewById(R.id.btn_submit);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);


        builder.setView(view);
        final AlertDialog dialog = builder.show();


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击以后要校验文本框的内容

                String pwd = etPwd.getText().toString().trim();

                String confirmPwd = etConfirmPwd.getText()
                                                .toString()
                                                .trim();

                // 非空判断TextUtils.isEmpty(pwd) || TextUtils.isEmpty(confirmPwd
                if (pwd.isEmpty() || confirmPwd.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT)
                         .show();

                    return;
                }

                // 相等判断
                if (!pwd.equals(confirmPwd)) {
                    Toast.makeText(getApplicationContext(), "密码不相等", Toast.LENGTH_SHORT)
                         .show();
                    return;
                }

                // 保存edittext里面的内容
                SpUtils.putString(getApplicationContext(), Constants.SJFD_PWD,pwd);
                Toast.makeText(HomeActivity.this, "密码保存成功", Toast.LENGTH_SHORT)
                     .show();

                dialog.dismiss();
            }

        });
    }

    private class HomeAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            if (mDatas != null) {
                return mDatas.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mDatas != null) {
                return mDatas.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = View.inflate(HomeActivity.this, R.layout.item_grid_list, null);
            }
            ImageView ivIcons = (ImageView) convertView.findViewById(R.id.item_iv_pic);
            TextView tvDesc = (TextView) convertView.findViewById(R.id.item_tv_desc);

            HomeBean bean = mDatas.get(position);
            ivIcons.setImageResource(bean.pic);
            tvDesc.setText(bean.desc);

            return convertView;
        }
    }
}
