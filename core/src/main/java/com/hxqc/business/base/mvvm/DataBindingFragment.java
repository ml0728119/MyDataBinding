package com.hxqc.business.base.mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxqc.business.base.HXBaseFragment;
import com.hxqc.business.base.TUtil;

/**
 * Created by zf
 */
public abstract class DataBindingFragment<B extends ViewDataBinding, VM extends BaseViewModel<B>> extends HXBaseFragment {
    protected B mBinding;
    protected VM mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = TUtil.getT(this, 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, bindLayout(), container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mViewModel != null) {
            mViewModel.setBinding(mBinding);
        }
        this.init();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mViewModel != null) {
            mViewModel.onDestroy();
        }
    }


    public abstract int bindLayout();


    public abstract void init();

    @Override
    public String fragmentDescription() {
        return this.getClass().getSimpleName();
    }
}
