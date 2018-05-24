package com.hxqc.business.base.mvp;

import com.hxqc.business.base.HXBaseActivity;
import com.hxqc.business.base.HXBaseFragment;


/**
 * Created by zf
 */
public class BasePresenter<E extends BaseModel, T extends BaseView> {
    public HXBaseActivity mContext;
    public E mModel;
    public T mView;

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public void onStart() {
        if (mView instanceof HXBaseActivity) {
            mContext = (HXBaseActivity) mView;
        } else if (mView instanceof HXBaseFragment) {
            mContext = (HXBaseActivity) ((HXBaseFragment) mView).mContext;
        } else {
            throw new IllegalArgumentException(mView.getClass().getName() + "需继承HXBaseActivity或HXBaseFragment");
        }

        if (mContext != null){

        }
    }

    public void onDestroy() {
    }
}
