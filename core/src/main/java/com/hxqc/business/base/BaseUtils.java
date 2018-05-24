package com.hxqc.business.base;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.View;

import com.hxqc.business.utils.ActivityStackManager;

/**
 * Created by zhaofan on 2016/12/28.
 */
public class BaseUtils {
    public ActivityStackManager mAManager;
  /*  public DiskLruCacheHelper mDiskLruCache;
    public EventBus mEventBus;  //调用前需要regist();
    public RxManage mRxManage;
    public SPUtils mSpUtils;*/
    private SparseArray<View> viewCache = new SparseArray<>();
    private Object obj;
    private Activity mActivity;

    public BaseUtils(Object obj) {
        this.obj = obj;
       mAManager = ActivityStackManager.getInstance();
        if (obj instanceof Activity) {
            mActivity = (Activity) obj;
            mAManager.addActivity(mActivity);
         }
        //
        else if (obj instanceof Fragment) {
            mActivity = ((Fragment) obj).getActivity();
        }

        initResource();
    }


    /**
     * init
     */
    private void initResource() {
   /*     mDiskLruCache = DiskLruCacheHelper.builder();
        mSpUtils = SPUtils.getInstance();
        mRxManage = new RxManage();
        mEventBus = EventBus.getDefault();*/
    }


    public void onDestroy() {
      if (obj instanceof Activity) {
            mAManager.removeActivityFromStack((Activity) obj);
        }
      /*    mRxManage.clear();
        viewCache.clear();
        if (mEventBus.isRegistered(obj)) {
            DebugLog.d("mEventBus", obj.getClass().getSimpleName() + " unregister");
            mEventBus.unregister(obj);
        }*/
    }


    public <R extends View> R getView(@IdRes int viewID) {
        return getView(obj instanceof Fragment ? ((Fragment) obj).getView() : obj, viewID);
    }

    public <R extends View> R getView(Object parentView, @IdRes int viewID) {
        View cachedView = null;
        //父类是View
        if (parentView instanceof View) {
            int key = ((View) parentView).getId() + viewID;
            cachedView = viewCache.get((key));
            if (null == cachedView) {
                cachedView = ((View) parentView).findViewById(viewID);
                viewCache.put(key, cachedView);
            }
        }
        //父类是Activity
        else if (parentView instanceof Activity) {
            cachedView = viewCache.get(viewID);
            if (null == cachedView) {
                cachedView = ((Activity) parentView).findViewById(viewID);
                viewCache.put(viewID, cachedView);
            }
        }
        return (R) cachedView;
    }

}
