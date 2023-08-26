package com.example.voicedemo;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //所需申请的权限
//    private static String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.INTERNET,
//            Manifest.permission.RECORD_AUDIO,
//            Manifest.permission.ACCESS_NETWORK_STATE,
//            Manifest.permission.ACCESS_WIFI_STATE,
//            Manifest.permission.CHANGE_NETWORK_STATE,
//            Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.READ_CONTACTS,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.CAMERA
//    };
    public static class AudioUtils {
        private static AudioUtils audioUtils;
        private SpeechSynthesizer mySynthesizer;
        public AudioUtils(){

        }

        public static AudioUtils getInstance() {
            if (audioUtils == null) {
                synchronized (AudioUtils.class) {
                    if (audioUtils == null) {
                        audioUtils = new AudioUtils();
                    }
                }
            }
            return audioUtils;
        }

        private InitListener myInitListener = new InitListener() { @Override public void onInit(int code) {  } };


        public void init(Context context) {
            mySynthesizer = SpeechSynthesizer.createSynthesizer(context, myInitListener);
            mySynthesizer.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
            //发音人xiaoyan
            mySynthesizer.setParameter(SpeechConstant.SPEED,"50");
            //语速50
            mySynthesizer.setParameter(SpeechConstant.PITCH, "50");
            //语调50
            mySynthesizer.setParameter(SpeechConstant.VOLUME, "100");
            //音量100
        }

        public void speakText(String content){
            int code =mySynthesizer.startSpeaking(content,new SynthesizerListener(){

                @Override
                public void onSpeakBegin() {

                }

                @Override
                public void onBufferProgress(int i, int i1, int i2, String s) {

                }

                @Override
                public void onSpeakPaused() {

                }

                @Override
                public void onSpeakResumed() {

                }

                @Override
                public void onSpeakProgress(int i, int i1, int i2) {

                }

                @Override
                public void onCompleted(SpeechError speechError) {

                }

                @Override
                public void onEvent(int i, int i1, int i2, Bundle bundle) {

                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        applypermission();//申请app所需要用到的权限
        // 将“xxxxxx”替换成您申请的APPID，申请地址：http://www.xfyun.cn
        // 请勿在“=”与appid之间添加任何空字符或者转义符
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=1d749841");

         ActionBar actionBar = getSupportActionBar(); //获取ActionBar
        actionBar.setTitle("语音识别"); //设置标题
         actionBar.setDisplayHomeAsUpEnabled(true); //在ActionBar最左边显示返回上一层箭头按钮
    }

//    //定义判断权限申请的函数，在onCreat中调用就行
//    public void applypermission(){
//        if(Build.VERSION.SDK_INT>=23){
//            boolean needapply=false;
//            for(int i=0;i<PERMISSIONS_STORAGE.length;i++){
//                int chechpermission= ContextCompat.checkSelfPermission(getApplicationContext(),
//                        PERMISSIONS_STORAGE[i]);
//                if(chechpermission!= PackageManager.PERMISSION_GRANTED){
//                    needapply=true;
//                }
//            }
//            if(needapply){
//                ActivityCompat.requestPermissions(MainActivity.this,PERMISSIONS_STORAGE,1);
//            }
//        }
//    }
//    //重载用户是否同意权限的回调函数，进行相应处理
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        for(int i=0;i<grantResults.length;i++){      //检查权限是否获取
//            if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
//                //同意后的操作
//                Toast.makeText(MainActivity.this, permissions[i]+"已授权",Toast.LENGTH_SHORT).show();//提示
//            }
//            else {
//                //不同意后的操作
//                Toast.makeText(MainActivity.this,permissions[i]+"拒绝授权",Toast.LENGTH_SHORT).show();//提示
//
//            }
//        }
//    }

//
//    public class use{
//        /**
//         *@TODO 科大讯飞语音听写
//         * linowl 2020.09.11
//         */
//        public void initSpeech(final Context context) {
//            //1.创建RecognizerDialog对象
//            RecognizerDialog mDialog = new RecognizerDialog(context, null);
//            //识别器定义
//            final TextView txtdisplay = (TextView) findViewById(R.id.textview);
//            //定义局部变量txtdisplay TextView标签 id为textView
//            //2.设置accent、language等参数
//            mDialog.setParameter(SpeechConstant.DOMAIN,"iat");
//            //应用领域  iat：日常用语   medical：医疗
//            mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
//            //语言 zh_cn：中文 en_us：英文 ja_jp：日语 ko_kr：韩语 ru-ru：俄语 fr_fr：法语 es_es：西班牙语
//            mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
//            //方言  默认mandarin
//            //3.设置回调接口
//            mDialog.setListener(new RecognizerDialogListener() {
//                @Override
//                public void onResult(RecognizerResult recognizerResult, boolean isLast) {
//                    if (!isLast) {
//                        //解析语音
//                        //返回的result为识别后的汉字,直接赋值到TextView上即可
//                        String result = parseVoice(recognizerResult.getResultString());
//                        // Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
//                        txtdisplay.setText(result);
//                        //识别成功在textView显示
//                    }
//                }
//
//                @Override
//                public void onError(SpeechError speechError) {
//
//                }
//            });
//            //4.显示dialog，接收语音输入
//            mDialog.show();
//        }
//    }
//
//    /**
//     * 解析语音json
//     */
//    public String parseVoice(String resultString) {
//        Gson gson = new Gson();
//        Voice voiceBean = gson.fromJson(resultString, Voice.class);
//
//        StringBuffer sb = new StringBuffer();
//        ArrayList<Voice.WSBean> ws = voiceBean.ws;
//        for (Voice.WSBean wsBean : ws) {
//            String word = wsBean.cw.get(0).w;
//            sb.append(word);
//        }
//        return sb.toString();
//    }
//    /**
//     * 语音实体类
//     */
//    public class Voice {
//
//        public ArrayList<WSBean> ws;
//
//        public class WSBean {
//            public ArrayList<CWBean> cw;
//        }
//
//        public class CWBean {
//            public String w;
//        }
//    }

    //调用use类的initSpeech函数
//    public void Recognition(View view){
//        new use().initSpeech(this);
//    }

    public void broadcast(View view){
        final EditText editext = (EditText) findViewById(R.id.edittext);
        AudioUtils.getInstance().init(this); //初始化语音对象
        AudioUtils.getInstance().speakText(editext.getText().toString()); //播放语音
    }

}