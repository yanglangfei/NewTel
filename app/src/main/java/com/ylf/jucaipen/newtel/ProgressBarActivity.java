package com.ylf.jucaipen.newtel;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.ylf.jucaipen.newtel.com.ylf.jucaipen.view.MyProgressDialog;

/**
 * Created by Administrator on 2015/11/3.
 */
public class ProgressBarActivity extends Activity {
    private MyProgressDialog myProgress;
    private  int point;
    private Handler mHandle=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            myProgress.setCuttentPoim(point++);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_myprogress);
        initView();
    }

    private void initView() {
        myProgress= (MyProgressDialog) findViewById(R.id.myProgress);
        myProgress.setMaxPoint(100);
        new Thread(){
            @Override
            public void run() {
              while (point<100){
                  try {
                      Thread.sleep(500);
                      mHandle.obtainMessage(100).sendToTarget();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
            }
        }.start();
    }
}
