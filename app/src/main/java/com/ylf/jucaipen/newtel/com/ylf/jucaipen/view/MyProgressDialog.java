package com.ylf.jucaipen.newtel.com.ylf.jucaipen.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2015/11/3.
 */
public class MyProgressDialog extends View {
    private  int cuttentPoim=0;   //当前进度
    private Paint mBgPaint;   //背景画笔
    private  Paint mProPaint;   //进度画笔
    private  Paint mTexPaint;  // 进度文字画笔
    private  int screenWidth;
    private  int scerrnHeight;
    private  int maxPoint=100;    //最大进度

    public int getCuttentPoim() {
        return cuttentPoim;
    }

    public int getMaxPoint() {
        return maxPoint;
    }

    public void setCuttentPoim(int cuttentPoim) {
        this.cuttentPoim = cuttentPoim;
        invalidate();
    }

    public void setMaxPoint(int maxPoint) {
        this.maxPoint = maxPoint;
    }

    public MyProgressDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyProgressDialog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        WindowManager wm= (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        scerrnHeight=wm.getDefaultDisplay().getHeight();
        screenWidth=wm.getDefaultDisplay().getWidth();
        mBgPaint=new Paint();
        mBgPaint.setColor(Color.GRAY);
        mBgPaint.setStyle(Paint.Style.FILL);
        mBgPaint.setStrokeWidth(1);
        mBgPaint.setAntiAlias(true);

        mProPaint=new Paint();
        mProPaint.setColor(Color.GREEN);
        mProPaint.setStyle(Paint.Style.FILL);
        mProPaint.setStrokeWidth(1);
        mProPaint.setAntiAlias(true);

        mTexPaint=new Paint();
        mTexPaint.setColor(Color.RED);
        mTexPaint.setStyle(Paint.Style.FILL);
        mTexPaint.setStrokeWidth(1);
        mTexPaint.setTextAlign(Paint.Align.CENTER);
        mTexPaint.setTextSize(18);
    }

    public MyProgressDialog(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(screenWidth / 2, scerrnHeight / 2, 300, mBgPaint);
        canvas.drawCircle(screenWidth/2,scerrnHeight/2,cuttentPoim*300/maxPoint,mProPaint);
        canvas.drawText(cuttentPoim*100/maxPoint+"%",screenWidth/2,scerrnHeight/2,mTexPaint);

        
    }
}
