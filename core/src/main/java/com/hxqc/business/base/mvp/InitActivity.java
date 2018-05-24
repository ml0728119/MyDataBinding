package com.hxqc.business.base.mvp;

import android.os.Bundle;

import com.hxqc.business.base.HXBaseActivity;
import com.hxqc.business.base.TUtil;


/**
 * Created by zf
 */
public abstract class InitActivity<T extends BasePresenter, E extends BaseModel> extends HXBaseActivity {
    private T mPresenter;
    private E mModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(this.getLayoutId());
        this.bindView();
        this.init();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
    }


    public abstract int getLayoutId();

    public abstract void bindView();

    public abstract void init();


}
