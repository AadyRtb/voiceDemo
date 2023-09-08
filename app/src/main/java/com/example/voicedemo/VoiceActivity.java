package com.example.voicedemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import java.util.ArrayList;

public class VoiceActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        ActionBar actionBar = getSupportActionBar(); //获取ActionBar
        actionBar.setTitle("语音识别"); //设置标题
        actionBar.setDisplayHomeAsUpEnabled(true); //在ActionBar最左边显示返回上一层箭头按钮
    }

    public void cleartxt(final Context context){
        final TextView txt = (TextView) findViewById(R.id.textview);
        txt.setText("");
    }

    public void copytext(final Context context){
        final TextView textView = findViewById(R.id.textview);//文字
                CopyButtonLibrary copyButtonLibrary = new CopyButtonLibrary(getApplicationContext(),textView);
                copyButtonLibrary.init();
    }
    public class use{
        /**
         *@TODO 科大讯飞语音听写
         * linowl 2020.09.11
         */
        public void initSpeech(final Context context) {
            //1.创建RecognizerDialog对象
            RecognizerDialog mDialog = new RecognizerDialog(context, null);
            //识别器定义
            final TextView txtdisplay = (TextView) findViewById(R.id.textview);
            //定义局部变量txtdisplay TextView标签 id为textView
            //2.设置accent、language等参数
            mDialog.setParameter(SpeechConstant.DOMAIN,"iat");
            //应用领域  iat：日常用语   medical：医疗
            mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            //语言 zh_cn：中文 en_us：英文 ja_jp：日语 ko_kr：韩语 ru-ru：俄语 fr_fr：法语 es_es：西班牙语
            mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
            //方言  默认mandarin
            //3.设置回调接口
            mDialog.setListener(new RecognizerDialogListener() {
                @Override
                public void onResult(RecognizerResult recognizerResult, boolean isLast) {
                    if (!isLast) {
                        //解析语音
                        String result = parseVoice(recognizerResult.getResultString());
                        // Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                        txtdisplay.setText(result);
                    }
                }

                @Override
                public void onError(SpeechError speechError) {

                }
            });
            //4.显示dialog，接收语音输入
            mDialog.show();
        }
    }
    /**
     * 解析语音json
     */
    public String parseVoice(String resultString) {
        Gson gson = new Gson();
        Voice voiceBean = gson.fromJson(resultString, Voice.class);

        StringBuffer sb = new StringBuffer();
        ArrayList<Voice.WSBean>ws = voiceBean.ws;
        for (Voice.WSBean wsBean : ws) {
            String word = wsBean.cw.get(0).w;
            sb.append(word);
        }
        return sb.toString();
    }

    public class Voice {

        public ArrayList<Voice.WSBean> ws;

        public class WSBean {
            public ArrayList<Voice.CWBean> cw;
        }

        public class CWBean {
            public String w;
        }
    }
    public void Recognition(View view){
        new use().initSpeech(this);
    }

    public void Clear(View view){cleartxt(this);}

    public void Copy(View view){copytext(this);}



}