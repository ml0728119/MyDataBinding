package com.hxqc.business.login.face;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hxqc.business.R;
import com.hxqc.business.customtoolbar.CustomToolBar;

/**
 * @author zhaofan 2018/5/21.
 */
@Route(path = "/Login/FaceDetectActivity", extras = 1)
public class FaceDetectActivity extends AppCompatActivity {
    private static final String TAG = FaceDetectActivity.class.getSimpleName();
    // FaceDetectorCameraView faceView;
    CustomToolBar toolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core_login_face_activity);
        toolBar = findViewById(R.id.toolbar);
        toolBar.setNavigationIcon(R.drawable.core_search_delete);


        //    faceView = findViewById(R.id.face_view);

      /*  faceView.setFrCaptureListener(new FRCaptureFaceListener() {
            @Override
            public void captureFaceOK(String s) {
                Log.e(TAG, s);
            }

            @Override
            public void captureFaceProgress(int i) {
                Log.e(TAG, i + "");
            }
        });*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        //  faceView.onCameraViewStarted(200,200);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //    faceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //  faceView.onCameraViewStopped();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
