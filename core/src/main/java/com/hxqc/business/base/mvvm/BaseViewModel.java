package com.hxqc.business.base.mvvm;

import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;

import com.hxqc.business.base.HXBaseActivity;

import org.jetbrains.annotations.NotNull;

/**
 * @author zhaofan 2017/11/17.
 */

public abstract class BaseViewModel<B extends ViewDataBinding> extends BaseObservable implements IViewModel<B> {
    protected HXBaseActivity mContext;
    protected B mBinding;

    public void setBinding(@NotNull B mBinding) {
        this.mBinding = mBinding;
        mContext = (HXBaseActivity) mBinding.getRoot().getContext();
        this.init(mBinding);
        this.onStart();
    }


    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onRefreshData(int page) {

    }
}
