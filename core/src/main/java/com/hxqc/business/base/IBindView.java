package com.hxqc.business.base;

import android.support.annotation.IdRes;
import android.view.View;

/**
 * Created by zhaofan on 2017/1/16.
 */
public interface IBindView {

    <R extends View> R getView(@IdRes int viewID);

    <R extends View> R getView(Object parentView, @IdRes int viewID);

}
