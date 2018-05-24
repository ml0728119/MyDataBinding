package com.hxqc.business.base.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxqc.business.base.HXBaseFragment;
import com.hxqc.business.base.TUtil;

/**
 * Created by zf
 */
public abstract class InitFragment<T extends BasePresenter, E extends BaseModel> extends HXBaseFragment {
    public T mPresenter;
    public E mModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.bindView(getView());
        this.init();
    }

    public T getPresenter(BaseView v) {
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null && mModel != null) {
            mModel.init(mContext);
            mPresenter.setVM(v, mModel);
        }
        return mPresenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
    }


    public abstract int getLayoutId();

    public abstract void bindView(View view);

    public abstract void init();

    @Override
    public String fragmentDescription() {
        return this.getClass().getSimpleName();
    }
}
