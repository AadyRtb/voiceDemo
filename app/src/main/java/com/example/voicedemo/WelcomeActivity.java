package com.example.voicedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

public class WelcomeActivity extends AppCompatActivity {


    //所需申请的权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.INTERNET,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_NETWORK_STATE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        applypermission();//申请app所需要用到的权限
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=1d749841");
//////////////
        Button exbtn = (Button) findViewById( R.id.btn_exit );
        exbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder( WelcomeActivity.this )
                        .setIcon( R.drawable.alert )
                        .setTitle("提示")
                        .setMessage("确定退出吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });

/////////
        Button btn1=findViewById(R.id.btn_fun1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( WelcomeActivity.this, VoiceActivity.class );
                startActivity(intent);
            }
        });


        Button btn2=findViewById(R.id.btn_fun2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( WelcomeActivity.this, SpeakActivity.class );
                startActivity(intent);
            }
        });


        Button btn3=findViewById(R.id.btn_fun3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( WelcomeActivity.this, MainActivity.class );
                startActivity(intent);
            }
        });




    }
////////////////////////
    //定义判断权限申请的函数，在onCreat中调用就行
    public void applypermission(){
        if(Build.VERSION.SDK_INT>=23){
            boolean needapply=false;
            for(int i=0;i<PERMISSIONS_STORAGE.length;i++){
                int chechpermission= ContextCompat.checkSelfPermission(getApplicationContext(),
                        PERMISSIONS_STORAGE[i]);
                if(chechpermission!= PackageManager.PERMISSION_GRANTED){
                    needapply=true;
                }
            }
            if(needapply){
                ActivityCompat.requestPermissions(WelcomeActivity.this,PERMISSIONS_STORAGE,1);
            }
        }
    }
    //重载用户是否同意权限的回调函数，进行相应处理
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int i=0;i<grantResults.length;i++){      //检查权限是否获取
            if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
                //同意后的操作
                Toast.makeText(WelcomeActivity.this, permissions[i]+"已授权",Toast.LENGTH_SHORT).show();//提示
            }
            else {
                //不同意后的操作
                Toast.makeText(WelcomeActivity.this,permissions[i]+"拒绝授权",Toast.LENGTH_SHORT).show();//提示

            }
        }
    }

}