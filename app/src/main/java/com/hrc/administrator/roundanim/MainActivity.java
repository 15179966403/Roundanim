package com.hrc.administrator.roundanim;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.InputStream;

public class MainActivity extends Activity {

    class MyView extends View{
        private Bitmap bitmap1;     //十字架
        private Bitmap bitmap2;     //小球
        private int digree1=0;      //十字架的旋转角度
        private int digree2=360;    //小球的旋转角度

        public MyView(Context context){
            super(context);
            setBackgroundColor(Color.WHITE);        //设置画布为白色
            InputStream is=context.getResources().openRawResource(R.raw.cross);     //从raw取出位图流
            bitmap1= BitmapFactory.decodeStream(is);
            is=context.getResources().openRawResource(R.raw.ball);
            bitmap2=BitmapFactory.decodeStream(is);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Matrix matrix=new Matrix();
            if(digree1>360){
                digree1=0;
            }
            if(digree2<0)
                digree2=360;
            matrix.setRotate(digree1++,160,240);        //设置旋转角度以及旋转中心
            canvas.setMatrix(matrix);
            canvas.drawBitmap(bitmap1,88,169,null);     //绘制位图
            matrix.setRotate(digree2--,160,240);
            canvas.setMatrix(matrix);
            canvas.drawBitmap(bitmap2,35,115,null);
            invalidate();               //不断的重新绘制位图，实现旋转
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new MyView(this));

    }
}
