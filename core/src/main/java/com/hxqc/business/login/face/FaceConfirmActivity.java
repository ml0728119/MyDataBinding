package com.hxqc.business.login.face;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.hxqc.business.R;
import com.hxqc.business.base.mvvm.BaseViewModel;
import com.hxqc.business.base.mvvm.DataBindingActivity;
import com.hxqc.business.databinding.CoreLoginFaceConfirmActivityBinding;

/**
 * @author zhaofan 2018/5/22.
 */

public class FaceConfirmActivity extends DataBindingActivity<CoreLoginFaceConfirmActivityBinding, FaceConfirmActivity.FaceConfirmVm> {
    @Override
    public int bindLayout() {
        return R.layout.core_login_face_confirm_activity;
    }

    @Override
    public void init() {
        Drawable d = getApplication().getResources().getDrawable(R.drawable.core_top_back);
        d.mutate().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        mBinding.toolbar.setNavigationIcon(d);
        mBinding.reTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    public class FaceConfirmVm extends BaseViewModel<CoreLoginFaceConfirmActivityBinding> {

        @Override
        public void init(CoreLoginFaceConfirmActivityBinding mBinding) {

        }
    }
}
