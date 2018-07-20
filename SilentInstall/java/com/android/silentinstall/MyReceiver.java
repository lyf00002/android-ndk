package com.android.silentinstall;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.RecoverySystem;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by liuzhen on 2018/6/12.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("ALOG", "silentinstall Receiver receive message ");
        Log.d("ALOG", "intent.getAction " + intent.getAction());
        if ("com.android.silentinstall".equals(intent.getAction())) {

            String downloadPath = intent.getStringExtra("downloadPath");
            String packageName = intent.getStringExtra("packageName");
            String className = intent.getStringExtra("className");
            String recoveryPath = intent.getStringExtra("recoveryPath");

            if (TextUtils.isEmpty(recoveryPath)) {

                boolean installResult = new SilentInstall().install(Environment.getExternalStorageDirectory() + downloadPath);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (installResult) {
                    Log.d("ALOG", "安装失败");
                    restartApplication(context, packageName, className);
                } else {
                    restartApplication(context, packageName, className);
                }
            } else {
                try {
                    RecoverySystem.installPackage(context, new File(recoveryPath));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    Log.v("Recovery", "error");
                    e.printStackTrace();
                }
            }

        }
    }


    /**
     * 开启别的应用
     *
     * @param context
     * @param packageName 包名
     * @param className   启动的类名
     */
    private void restartApplication(Context context, String packageName, String className) {
//        String packageName = "com.melux.airwave";
//        String className = "com.melux.airwave.ui.activity.login.SplashActivity";
        if (!TextUtils.isEmpty(packageName) && !TextUtils.isEmpty(className)) {

            Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.setClassName(packageName,className);

            intent.addCategory(Intent.CATEGORY_LAUNCHER);

//                PackageManager packageManager = MainActivity.this.getPackageManager();
//                intent = packageManager.getLaunchIntentForPackage(packageName);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP) ;
            ComponentName cn = new ComponentName(packageName, className);
            intent.setComponent(cn);
            context.startActivity(intent);
        }
    }
}
