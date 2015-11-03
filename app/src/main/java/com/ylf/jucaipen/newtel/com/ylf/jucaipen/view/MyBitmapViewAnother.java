package com.ylf.jucaipen.newtel.com.ylf.jucaipen.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ylf.jucaipen.newtel.R;

/**
 * Created by Administrator on 2015/11/3.
 */
public class MyBitmapViewAnother extends View {
    private Paint mPaint;   //触摸画笔
    private Bitmap mBitmapBackground;  //背景图片
    private Paint mPaintCover;   //浮层画笔
    private Paint mPaintRect;
    private Path mPath;

    private int width, height;
    private Bitmap bitmap;
    private Canvas bitmapCanvas;
    private Matrix matrix;
    private float down_x, down_y, move_x, move_y;

    public MyBitmapViewAnother(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mPaint = new Paint();//Bitmap的画笔

        //设置背景
        mBitmapBackground = BitmapFactory.decodeResource(getResources(), R.mipmap.j);

        mPaintCover = new Paint();
        mPaintCover.setAntiAlias(true);
        mPaintCover.setColor(Color.GRAY);
        mPaintCover.setStrokeWidth(50);
        //设置图形混合方式，这里使用PorterDuff.Mode.XOR模式，与底层重叠部分设为透明
        PorterDuffXfermode model = new PorterDuffXfermode(PorterDuff.Mode.XOR);
        mPaintCover.setXfermode(model);
        mPaintCover.setStyle(Paint.Style.STROKE);
        //设置笔刷的样式，默认为BUTT，如果设置为ROUND(圆形),SQUARE(方形)，需要将填充类型Style设置为STROKE或者FILL_AND_STROKE
        mPaintCover.setStrokeCap(Paint.Cap.ROUND);
        //设置画笔的结合方式
        mPaintCover.setStrokeJoin(Paint.Join.ROUND);

        //绘制蒙版的画笔
        mPaintRect = new Paint();
        mPaintRect.setAntiAlias(true);
        mPaintRect.setColor(Color.LTGRAY);
        //路径记录滑动屏幕的路径。
        mPath = new Path();
    }

    public MyBitmapViewAnother(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);//设置宽和高

        //创建一个Bitmap，用于绘图。
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);//该画布为bitmap。

        //绘制背景BitmapBackground大小的矩阵
        matrix = new Matrix();//如果在构造器中初始化，需要使用reset()方法
        matrix.postScale((float) width / mBitmapBackground.getWidth(), (float) height / mBitmapBackground.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将bitmapBackground设置该View画布的背景
        canvas.drawBitmap(mBitmapBackground, matrix, null);
        //然后画布添加背景的基础上添加bitmap。
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        bitmapCanvas.drawRect(0, 0, width, height, mPaintRect);//bitmap上绘制一个蒙版
        bitmapCanvas.drawPath(mPath, mPaintCover);//bitmap上绘制手 划过的路径
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //获得点击屏幕时的坐标
                down_x = event.getX();
                down_y = event.getY();
                //将Path移动到点击点
                mPath.moveTo(down_x, down_y);
                invalidate();//更新画面
                return true;
            case MotionEvent.ACTION_MOVE:
                //获得在屏幕上移动的坐标
                move_x = event.getX();
                move_y = event.getY();
                //将移动的轨迹画成直线
                mPath.lineTo(move_x, move_y);
                //然后移动到下一个点。
                mPath.moveTo(move_x, move_y);
                invalidate();//更新画面
                return true;
        }
        return super.onTouchEvent(event);
    }
}