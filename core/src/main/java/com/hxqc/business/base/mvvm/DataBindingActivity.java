package com.hxqc.business.base.mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.hxqc.business.base.HXBaseActivity;
import com.hxqc.business.base.TUtil;

/**
 * @author zhaofan 2017/11/14.
 */

public abstract class DataBindingActivity<B extends ViewDataBinding, VM extends BaseViewModel<B>> extends HXBaseActivity {
    protected B mBinding;
    protected VM mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, bindLayout());
        mViewModel = TUtil.getT(this, 1);
        if (mViewModel != null) {
            mViewModel.setBinding(mBinding);
        }
        this.init();
    }


    public abstract int bindLayout();

    public abstract void init();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mViewModel != null) {
            mViewModel.onDestroy();
        }

    }
}
