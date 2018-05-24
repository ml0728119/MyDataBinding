package com.hxqc.business.base.mvvm;

/**
 * @author zhaofan 2017/11/30.
 */

public interface IViewModel<B> {

    void init(B mBinding);


    void onStart();


    void onDestroy();


    void onRefreshData(int page);
}
