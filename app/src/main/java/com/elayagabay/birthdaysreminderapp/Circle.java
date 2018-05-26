package com.elayagabay.birthdaysreminderapp;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Random;

public class Circle {
    private float xTop;
    private float yTop;
    private int radius;
    private Paint paint;
    private RectF rectF;
    private int speedX;
    private int speedY;

    public Circle(int color) {
        this.paint = new Paint();
        paint.setColor(color);
        rectF = new RectF();
    }

    public void draw(Canvas canvas){
        canvas.drawOval(rectF,paint);
        move(canvas);
    }

    public void setCoordination(float x, float y, int radius){
        this.xTop = x;
        this.yTop = y;
        this.radius = radius;
        rectF.set(this.xTop,this.yTop,this.xTop+ this.radius,this.yTop+this.radius);
    }

    public void setSpeed(int spdX, int spdY){
        this.speedX = spdX;
        this.speedY = spdY;
    }

    private void move(Canvas canvas) {
        Random random = new Random();
        if(xTop >= canvas.getWidth() - 100){
            speedX = random.nextInt(10) - 15;
        }
        if(xTop <= 0) {
            speedX = random.nextInt(10);
        }
        if(yTop >= canvas.getHeight()- 100){
            speedY = random.nextInt(10) - 15;
        }

        if(yTop <= 0) {
            speedY = random.nextInt(10);
        }

        xTop += speedX;
        yTop += speedY;
        rectF.set(xTop,yTop,xTop+radius,yTop+radius);
    }
}
