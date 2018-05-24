package com.hxqc.business.base;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by zhaofan on 2016/12/23.
 */
public class HXBaseActivity extends AppCompatActivity {
    public Activity mContext;
    private BaseUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        PushAgent.getInstance(this).onAppStart();
        mContext = this;
        utils = new BaseUtils(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (utils != null) {
            utils.onDestroy();
        }
    }

    public BaseUtils Tool() {
        return utils;
    }


    public <R extends View> R getView(@IdRes int viewID) {
        return utils.getView(viewID);
    }

    public <R extends View> R getView(@IdRes int viewID, @NonNull View.OnClickListener mOnClickListener) {
        R v = utils.getView(viewID);
        v.setOnClickListener(mOnClickListener);
        return v;
    }

    public <R extends View> R getView(Object parentView, @IdRes int viewID) {
        return utils.getView(parentView, viewID);
    }


    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        try {
            res.updateConfiguration(config, res.getDisplayMetrics());
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return res;
    }

}
