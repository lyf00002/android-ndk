package com.android.silentinstall;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new SilentInstall().install(Environment.getExternalStorageDirectory() + "/Download/a.apk");
//            }
//        });
        initPermissions();
    }


    @Override
    protected void onResume() {
        finish();
        super.onResume();
    }


    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermissions() {
        String permissions[] = {
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.RECEIVE_BOOT_COMPLETED
        };

        ArrayList<String> toApplyList = new ArrayList<String>();
        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                //将未拥有的权限添加进列表
                toApplyList.add(perm);
            }
        }
        //创建零时数组
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            //当有未申请权时,进行申请权限操作 (将列表数据保存到字符串数组中)
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 1);
        }
    }
}
