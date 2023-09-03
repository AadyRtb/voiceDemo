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

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.binary.Base64;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class PhotoActivity extends AppCompatActivity  {

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



//        WebOCR webOCR=new WebOCR();
//        try {
//            webOCR.use();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }

    ActivityResultLauncher launcher2 = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    photo.setImageURI(result);
                }
            });
/**
 * 印刷文字识别WebAPI接口调用示例接口文档(必看)：https://doc.xfyun.cn/rest_api/%E5%8D%B0%E5%88%B7%E6%96%87%E5%AD%97%E8%AF%86%E5%88%AB.html
 * 上传图片base64编码后进行urlencode要求base64编码和urlencode后大小不超过4M最短边至少15px，最长边最大4096px支持jpg/png/bmp格式
 * (Very Important)创建完webapi应用添加合成服务之后一定要设置ip白名单，找到控制台--我的应用--设置ip白名单，如何设置参考：http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=41891
 * 错误码链接：https://www.xfyun.cn/document/error-code (code返回错误码时必看)
 * @author iflytek
 */

//        // OCR webapi 接口地址
//        private static final String WEBOCR_URL = "http://webapi.xfyun.cn/v1/service/v1/ocr/general";
//        // 应用ID (必须为webapi类型应用，并印刷文字识别服务，参考帖子如何创建一个webapi应用：http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=36481)
//        private static final String APPID = "1d749841";
//        // 接口密钥(webapi类型应用开通印刷文字识别服务后，控制台--我的应用---印刷文字识别---服务的apikey)
//        private static final String API_KEY = "b34a0fda7e5cce23f0b14f7a39772fb1";
//        // 是否返回位置信息
//        private static final String LOCATION = "false";
//        // 语种(可选值：en（英文），cn|en（中文或中英混合)
//        private static final String LANGUAGE = "cn|en";
//        // 图片地址,图片最短边至少15px，最长边最大4096px，格式jpg、png、bmp
//        private static final String PIC_PATH = "C://Users//dong//Pictures//Saved Pictures//poem.png";
//
//        /**
//         * OCR WebAPI 调用示例程序
//         *
//         * @param args
//         * @throws IOException
//         */
//        public static void main(String[] args) throws IOException {
//            Map<String, String> header = buildHttpHeader();
//            byte[] imageByteArray = FileUtil.read(PIC_PATH);
//            String imageBase64 = new String(Base64.encodeBase64(imageByteArray), "UTF-8");
//            String result = HttpUtil.doPost1(WEBOCR_URL, header, "image=" + URLEncoder.encode(imageBase64, "UTF-8"));
//            System.out.println("OCR WebAPI 接口调用结果：" + result);
//            //  错误码链接：https://www.xfyun.cn/document/error-code (code返回错误码时必看)
//        }
//
//        /**
//         * 组装http请求头
//         */
//        private static Map<String, String> buildHttpHeader() throws UnsupportedEncodingException {
//            String curTime = System.currentTimeMillis() / 1000L + "";
//            String param = "{\"location\":\"" + LOCATION + "\",\"language\":\"" + LANGUAGE + "\"}";
//            String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
//            String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);
//            Map<String, String> header = new HashMap<String, String>();
//            header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
//            header.put("X-Param", paramBase64);
//            header.put("X-CurTime", curTime);
//            header.put("X-CheckSum", checkSum);
//            header.put("X-Appid", APPID);
//            return header;
//        }
//
}


