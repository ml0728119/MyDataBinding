package com.hxqc.business.login;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hxqc.business.R;
import com.hxqc.business.base.mvp.InitActivity;
import com.hxqc.business.dialog.AlertCommonDialog;

/**
 * @author zhaofan 2018/5/18.
 */

@Route(path = "/Login/LoginActivity", extras = 1)
public class LoginActivity extends InitActivity {
    private AlertCommonDialog dialog;

    @Override
    public int getLayoutId() {
        return R.layout.core_login_main_activity;
    }

    @Override
    public void bindView() {

    }

    @Override
    public void init() {
       initDialog();
    }


    public void onLogin(View v) {
        dialog.show(mContext);

    }


    private void initDialog() {
//        dialog = DialogHelper.faceDetectDialog(new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                startActivity(new Intent(mContext, FaceDetectActivity.class));
//            }
//        });
    }
}
