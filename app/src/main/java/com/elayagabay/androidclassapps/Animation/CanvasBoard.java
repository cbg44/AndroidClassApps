package com.elayagabay.androidclassapps.Animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class CanvasBoard extends View {
    int xMax;
    int yMax;
    int xLast;
    int yLast;
    ArrayList<Circle> circleList = new ArrayList<>();

    public CanvasBoard(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.BLUE);
        rectPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(200,600,canvas.getWidth() /2,canvas.getHeight() / 2, rectPaint);
        Iterator it = circleList.iterator();
        while(it.hasNext()){
            Circle circle = (Circle) it.next();
            circle.draw(canvas);
        }
        invalidate();
    }

    public boolean onTouchEvent (MotionEvent event){
        Random random = new Random();
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN){
            if (circleList.size() == 10)
                return true;
            int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            Circle circle = new Circle(color);
       //   circle.move();
            circle.setCoordination(100 ,100,90);
            circle.setSpeed(random.nextInt(100), random.nextInt(100));
            circleList.add(circle);
            onSizeChanged(xMax, yMax , xLast, yLast);
        } else {
            return false;
        }
        return true;
    }
}
