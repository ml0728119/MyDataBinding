package com.hxqc.business.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

/**
 * Created by zhaofan on 2016/12/23.
 */
public abstract class HXBaseFragment extends Fragment {
    public Activity mContext;
    private BaseUtils utils;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utils = new BaseUtils(this);
    }


    public void onResume() {
        super.onResume();
       // MobclickAgent.onPageStart(description()); //统计页面
    }

    public void onPause() {
        super.onPause();
      //  MobclickAgent.onPageEnd(description());
    }

    @Override
    public void onDestroy() {
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



    private String description() {
        if (TextUtils.isEmpty(fragmentDescription())) {
            return "其他";
        }
        return fragmentDescription();
    }

    public abstract String fragmentDescription();

}
