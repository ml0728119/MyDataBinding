package com.hxqc.business.utils;

import android.app.Activity;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.hxqc.business.application.SampleApplicationContext;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Activity堆栈管理
 * Created by zhaofan
 */

public class ActivityStackManager implements Handler.Callback {

    private static volatile ActivityStackManager mActivityManager;
    private Stack<WeakReference<Activity>> activitys = new Stack<>();
    private Handler handler;

    private ActivityStackManager() {
        handler = new Handler(Looper.getMainLooper(), this /* Callback */);
    }

    public static ActivityStackManager getInstance() {
        if (mActivityManager == null)
            synchronized (ActivityStackManager.class) {
                if (mActivityManager == null) {
                    mActivityManager = new ActivityStackManager();
                }
            }
        return mActivityManager;
    }


    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity getTopActivity() {
        Activity activity = null;
        if (!activitys.isEmpty()) {
            activity = activitys.peek().get();
        }
        return activity;
    }


    public void addActivity(Activity ac) {
       /* if (ac instanceof AppCompatActivity) {
            getSupportRequestManagerFragment(((AppCompatActivity) ac).getSupportFragmentManager());
        }*/
        activitys.push(new WeakReference<>(ac));
    }


    /**
     * 从activity堆栈中移除一个activity
     */
    public void removeActivityFromStack(Activity activity) {
        Iterator<WeakReference<Activity>> stackIterator = activitys.iterator();
        while (stackIterator.hasNext()) {
            Activity wActivity = stackIterator.next().get(); //WeakReference中的activity
            //移除无效的弱引用Activity
            if (wActivity == null) {
                stackIterator.remove();
            }
            // 获取当前位置的activity
            if (activity != null && wActivity != null && wActivity.equals(activity)) {
                stackIterator.remove();
            }
        }
    }

    public ArrayList<Activity> getAllOpenedActivities() {
        ArrayList<Activity> activities = new ArrayList<>();
        for (WeakReference<Activity> activity1 : activitys) {
            Activity activity = activity1.get();
            if (activity != null)
                activities.add(activity);
        }
        return activities;
    }


    public void finishActivity(Class<?> cls) {
        for (WeakReference<Activity> task : activitys) {
            if (task.get().getClass().equals(cls))
                if (!task.get().isFinishing()) {
                    task.get().finish();
                }
        }
    }

    public void finishActivity(String clsName) {
        for (WeakReference<Activity> task : activitys) {
            if (task.get().getLocalClassName().equals(clsName))
                if (!task.get().isFinishing()) {
                    task.get().finish();
                }
        }
    }


    public void finishAllActivity() {
        for (WeakReference<Activity> task : activitys) {
            if (!task.get().isFinishing()) {
                task.get().finish();
            }
        }
        activitys.clear();
    }


    public String getTopActivityAction() {
        android.app.ActivityManager manager = (android.app.ActivityManager) SampleApplicationContext.context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

        if (runningTaskInfos != null)
            return runningTaskInfos.get(0).topActivity.getClassName();
        else
            return "";
    }

    private static final String FRAGMENT_TAG = "com.bumptech.glide.manager";
    private static final int ID_REMOVE_SUPPORT_FRAGMENT_MANAGER = 2;
    private Map<FragmentManager, StackManagerFragment> pendingSupportRequestManagerFragments =
            new HashMap<>();

    StackManagerFragment getSupportRequestManagerFragment(final FragmentManager fm) {
        StackManagerFragment current = (StackManagerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            current = pendingSupportRequestManagerFragments.get(fm);
            if (current == null) {
                current = StackManagerFragment.newInstance(activitys);
                pendingSupportRequestManagerFragments.put(fm, current);
                fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
                handler.obtainMessage(ID_REMOVE_SUPPORT_FRAGMENT_MANAGER, fm).sendToTarget();
            }
        }
        return current;
    }

    @Override
    public boolean handleMessage(Message message) {
        boolean handled = true;
        Object removed = null;
        Object key = null;
        switch (message.what) {
            case ID_REMOVE_SUPPORT_FRAGMENT_MANAGER:
                FragmentManager supportFm = (FragmentManager) message.obj;
                key = supportFm;
                removed = pendingSupportRequestManagerFragments.remove(supportFm);
                break;
            default:
                handled = false;
        }
        if (handled && removed == null) {
            Log.e(this.getClass().getSimpleName(), "Failed to remove expected request manager fragment, manager: " + key);
        }
        return handled;
    }

    public static class StackManagerFragment extends Fragment {
        private Stack<WeakReference<Activity>> activitys;

        public static StackManagerFragment newInstance(Stack<WeakReference<Activity>> mWeakReferences) {
            StackManagerFragment f = new StackManagerFragment();
            Bundle args = new Bundle();
            args.putSerializable("stack", mWeakReferences);
            f.setArguments(args);
            return f;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            activitys = (Stack<WeakReference<Activity>>) getArguments().getSerializable("stack");
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
        }

        @Override
        public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
            super.onViewStateRestored(savedInstanceState);
        }

    }

}
