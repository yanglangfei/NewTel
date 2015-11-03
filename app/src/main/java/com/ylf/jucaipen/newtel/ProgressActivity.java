package com.ylf.jucaipen.newtel;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.ylf.jucaipen.newtel.com.ylf.jucaipen.view.MyProgress;

/**
 * Created by Administrator on 2015/11/3.
 */
public class ProgressActivity extends Activity {

    private MyProgress my_pro;
    private  int current;
    private Handler mHandle=new Handler(){
        @Override
        public void handleMessage(Message msg) {
               my_pro.setCurrtPoint(current++);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_progress);
        initView();
    }

    private void initView() {
        my_pro= (MyProgress) findViewById(R.id.my_pro);
        my_pro.setMaxPoint(100);
        new Thread(){
            @Override
            public void run() {
                try {
                  //  while (current<100) {
                    while (true) {
                        Thread.sleep(50);
                        mHandle.obtainMessage(100).sendToTarget();
                    }
                  //  }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
