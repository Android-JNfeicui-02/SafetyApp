package com.roy.safeyapp.engine;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.roy.safeyapp.bean.AppBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roy Sun on 2016/5/10.
 */
public class RjglProvider {


    /**
     * 自定义一个方法 拿到需要的应用信息
     * @param context
     *  传入当前的上下文
     * @return
     */
    public static List<AppBean> getAppInfo(Context context) {

        // 上下文拿到packagemanager的对象
        PackageManager pm = context.getPackageManager();
        // 通过对象拿到集合
        List<PackageInfo> packages = pm.getInstalledPackages(0);

        // 定义一个数据集合
        List<AppBean> mData = new ArrayList<>();

        // 遍历pm的集合 拿到里面的数据
        for (PackageInfo info : packages) {

            // 拿到图标
            Drawable icon = info.applicationInfo.loadIcon(pm);
            //拿到应用程序的名称
            String name = info.applicationInfo.loadLabel(pm).toString();

            String packageName = info.applicationInfo.packageName;

            AppBean bean = new AppBean();
            bean.name = name;
            bean.icon = icon;
            bean.packageName = packageName;

            mData.add(bean);
        }

        return mData;
    }
}
