package com.roy.safeyapp.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.roy.safeyapp.R;
import com.roy.safeyapp.bean.AppBean;
import com.roy.safeyapp.engine.RjglProvider;

import java.util.ArrayList;
import java.util.List;

public class RjglActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView mLvAppList;

    List<AppBean> mData;

    private static final String TAG = "RjglActivity";
    private RjglAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rjgl);

        mLvAppList = (ListView) findViewById(R.id.rjgl_lv_app);

//        for (int i = 0; i < 50; i++) {
//            AppBean bean = new AppBean();
//            bean.name = "app" + i;
//
//            mData.add(bean);
//        }
        mAdapter = new RjglAdapter();

        mLvAppList.setOnItemClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: start");
        mLvAppList.removeAllViewsInLayout();

        // 调用自己写的方法 拿到需要的数据 赋值给List对象
        mData = RjglProvider.getAppInfo(RjglActivity.this);

        mLvAppList.setAdapter(mAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AppBean appBean = mData.get(position);
        String packageName = appBean.packageName;
        Intent intent = new Intent(Intent.ACTION_DELETE, Uri.parse("package:" + packageName));
        startActivity(intent);

    }

    private class RjglAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (mData != null) {
                return mData.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {

            if (mData != null) {
                return mData.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(getApplicationContext(), R.layout.item_rjgl_list, null);

                holder.ivIcon = (ImageView) convertView.findViewById(R.id.item_rjgl_iv_icon);
                holder.tvName = (TextView) convertView.findViewById(R.id.item_rjgl_tv_name);
                holder.tvPackageName = (TextView) convertView.findViewById(
                        R.id.item_rjgl_tv_packagename);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            AppBean bean = (AppBean) getItem(position);
            holder.ivIcon.setImageDrawable(bean.icon);
            holder.tvName.setText(bean.name);
            holder.tvPackageName.setText(bean.packageName);

            return convertView;
        }
    }

    private class ViewHolder {
        ImageView ivIcon;
        TextView  tvName;
        TextView  tvPackageName;
    }


}


