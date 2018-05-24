package com.hxqc.business.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hxqc.business.BuildConfig;

/**
 * Created 胡俊杰
 * 2018/5/12.
 * Todo:
 */

public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);

//        SampleApplicationContext.application = this;
//        SampleApplicationContext.context = this;

    }
}
