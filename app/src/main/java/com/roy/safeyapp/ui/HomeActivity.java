package com.roy.safeyapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.roy.safeyapp.R;
import com.roy.safeyapp.bean.HomeBean;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private GridView mGridView;


    private ArrayList<HomeBean> mDatas;

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
                convertView = View.inflate(HomeActivity.this,R.layout.item_grid_list,null);
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
