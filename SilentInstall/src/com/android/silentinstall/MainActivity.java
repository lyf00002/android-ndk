package com.android.silentinstall;

import android.app.Activity;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;

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
    }


    @Override
    protected void onResume() {
        finish();
        super.onResume();
    }
}
