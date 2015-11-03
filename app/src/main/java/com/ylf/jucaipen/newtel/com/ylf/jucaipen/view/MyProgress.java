package com.ylf.jucaipen.newtel.com.ylf.jucaipen.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.sql.Ref;

/**
 * Created by Administrator on 2015/11/3.
 */
public class MyProgress extends View {
    private Paint mBgPaint;  //背景画笔
    private  Paint mProPaint;  //进度画笔
    private  int currtPoint;  //当前进度
    private  int maxPoint;   //最大进度
    private  int mScreesWidth;  //屏幕宽度
    private  int mScreenHeight; //屏幕高度
    public MyProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mBgPaint=new Paint();
        mBgPaint.setColor(Color.GRAY);
        mBgPaint.setStyle(Paint.Style.STROKE);
        mBgPaint.setAntiAlias(true);
        mBgPaint.setStrokeWidth(1);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF f=new RectF(100,100,800,400);
        canvas.drawArc(f,45f,10f,true,mBgPaint);
    }
}
