package com.example.voicedemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;

import com.iflytek.cloud.SynthesizerListener;

public class SpeakActivity extends AppCompatActivity {


    public static java.lang.String VOICE_NAME="xiaoyan";
    public static int checkedItem = 0;
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
            mySynthesizer.setParameter(SpeechConstant.VOICE_NAME, VOICE_NAME);
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
        setContentView(R.layout.activity_speak);

        ActionBar actionBar = getSupportActionBar(); //获取ActionBar
        actionBar.setTitle("语音合成"); //设置标题
        actionBar.setDisplayHomeAsUpEnabled(true); //在ActionBar最左边显示返回上一层箭头按钮




        Button chbtn = (Button) findViewById( R.id.btn_choose );
        chbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[]{"xiaoyan", "aisjiuxu", "aisxping", "aisjinger", "aisbabyxu"};
                AlertDialog.Builder builder = new AlertDialog.Builder(SpeakActivity.this);
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setTitle("选择您想使用的发音人：");

                builder.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                          VOICE_NAME = items[which];
                          checkedItem=which;
                        Toast.makeText(SpeakActivity.this, "你选择了" + items[which], Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定", null);
                builder.show();
            }
        });
    }


    public void cleartxt(final Context context){
        final EditText txt = (EditText) findViewById(R.id.edittext);
        txt.setText("");
    }

    public void choosevoice(final Context context){

    }
    public void broadcast(View view){
        final EditText editext = (EditText) findViewById(R.id.edittext);
        AudioUtils.getInstance().init(this); //初始化语音对象
        AudioUtils.getInstance().speakText(editext.getText().toString()); //播放语音
    }

    public void stopvoice(final  Context context ){
        AudioUtils.getInstance().speakText("");}
    public void Clear(View view){cleartxt(this);}

    public void Stop(View view){stopvoice(this);}

    //private AlertDialog alertDialog2;
    public void Choose(View view){


//        final String[] items = new String[]{"Java", "C", "C++", "Python", "C#"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(SpeakActivity.this);
//        builder.setIcon(android.R.drawable.ic_dialog_info);
//        builder.setTitle("选择你最擅长的开发语言：");
//        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(SpeakActivity.this, "你选择了" + items[which], Toast.LENGTH_SHORT).show();
//            }
//        });
//        builder.setPositiveButton("确定", null);
//        builder.show();







//        final String[] items = {"单选1", "单选2", "单选3", "单选4"};
//        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
//        alertBuilder.setTitle("这是单选框");
//        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(SpeakActivity.this, items[i], Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                alertDialog2.dismiss();
//            }
//        });
//
//        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                alertDialog2.dismiss();
//            }
//        });
//
//        alertDialog2 = alertBuilder.create();
//        alertDialog2.show();
    }

}