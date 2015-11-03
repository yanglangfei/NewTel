package com.ylf.jucaipen.newtel.com.ylf.jucaipen;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.ylf.jucaipen.newtel.R;
import com.ylf.jucaipen.newtel.com.ylf.jucaipen.view.CircleProgress;

/**
 * Created by Administrator on 2015/11/3.
 */
public class CircleProgressActivity extends Activity {
    private Button mButton;
    private CircleProgress myprogressanomation;
    private static final int PROGRESS= 0X0003;
    //定义一个进度
    private int progress;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case PROGRESS:
                    progress++;
                    if (progress <= 100) {
                        myprogressanomation.setCurrentProgress(progress);
                        sendEmptyMessageDelayed(PROGRESS, 100);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_circle_progress);
        mButton = (Button) findViewById(R.id.button_start_myprogressanomation);
        myprogressanomation= (CircleProgress) findViewById(R.id.myprogressanomation);
        myprogressanomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessageDelayed(PROGRESS, 1000);
            }
        });
    }
}
