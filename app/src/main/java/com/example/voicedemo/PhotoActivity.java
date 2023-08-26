package com.example.voicedemo;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.PathUtils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.File;

import javax.security.auth.callback.Callback;

public class PhotoActivity extends AppCompatActivity{

    private ImageView photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        ActionBar actionBar = getSupportActionBar(); //获取ActionBar
        actionBar.setTitle("图片识别"); //设置标题
        actionBar.setDisplayHomeAsUpEnabled(true); //在ActionBar最左边显示返回上一层箭头按钮


        photo = (ImageView) findViewById(R.id.photo);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher2.launch("image/*");
            }
        });




    }

        ActivityResultLauncher launcher2 = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        photo.setImageURI(result);
                    }
                });


}


