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


        //PackageManager pm = this.getPackageManager();
        //List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_ACTIVITIES);
        //Log.d(TAG, "PackageManager: " + packages.toString());


//        for (int i = 0; i < packages.size(); i++) {
//            PackageInfo pi = packages.get(i);
//            if ((pi.applicationInfo.flags & pi.applicationInfo.FLAG_SYSTEM) == 0) {
//                // 与运算出来的结果 如果不等于0 则是 系统内置的程序
//                // 如果等于0, 则是用户安装的程序
//                Log.d(TAG, "PackageInfo``: " + pi);
//            }
//        }


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

                // 非空判断
                if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(confirmPwd)) {
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
