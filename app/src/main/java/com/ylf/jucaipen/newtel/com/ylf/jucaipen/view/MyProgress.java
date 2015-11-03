package com.ylf.jucaipen.newtel.com.ylf.jucaipen.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import java.sql.Ref;

/**
 * Created by Administrator on 2015/11/3.
 */
public class MyProgress extends View {
    private Paint mBgPaint;  //背景画笔
    private  Paint mProPaint;  //进度画笔
    private  Paint mTextPaint;  //进度文字的画笔
    private  int currtPoint;  //当前进度
    private  int maxPoint;   //最大进度
    private  int mScreesWidth;  //屏幕宽度
    private  int mScreenHeight; //屏幕高度


    public void setCurrtPoint(int currtPoint) {
        this.currtPoint = currtPoint;
        invalidate();
    }

    public void setMaxPoint(int maxPoint) {
        this.maxPoint = maxPoint;
    }

    public MyProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        WindowManager wm= (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mScreesWidth=wm.getDefaultDisplay().getWidth();
        mScreenHeight=wm.getDefaultDisplay().getHeight();
        mBgPaint=new Paint();
        mBgPaint.setColor(Color.GRAY);
        mBgPaint.setStyle(Paint.Style.STROKE);
        mBgPaint.setAntiAlias(true);
        mBgPaint.setStrokeWidth(50);

        mProPaint=new Paint();
        mProPaint.setColor(Color.GREEN);
        mProPaint.setStyle(Paint.Style.STROKE);
        mProPaint.setAntiAlias(true);
        mProPaint.setStrokeWidth(50);

        mTextPaint=new Paint();
        mTextPaint.setColor(Color.RED);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(18);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mScreesWidth / 2, mScreenHeight / 2, 100, mBgPaint);
        int left=mScreesWidth/2-100;
        int right=mScreesWidth/2+100;
        int top=mScreenHeight/2-100;
        int bottom=mScreenHeight/2+100;
        RectF f=new RectF(left,top,right,bottom);
       /* canvas.drawArc(f,-90,currtPoint*360/maxPoint,false,mProPaint);
        canvas.drawText(currtPoint*100/maxPoint+"%",mScreesWidth/2,mScreenHeight/2,mTextPaint);*/
        canvas.drawArc(f,--currtPoint,currtPoint,false,mProPaint);
    }
}
