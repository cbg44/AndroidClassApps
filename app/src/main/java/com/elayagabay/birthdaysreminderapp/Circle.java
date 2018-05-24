package com.elayagabay.birthdaysreminderapp;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Circle {
    private float leftTopX;
    private float leftTopY;
    private int radius;
    private Paint paint;
    private RectF bounds;
    private int speedX;
    private int speedY;

    public Circle(int color) {
        this.paint = new Paint();
        paint.setColor(color);
        bounds = new RectF();
    }

    public void draw(Canvas canvas){
        canvas.drawOval(bounds,paint);
        update(canvas);
    }

    public void setCoordination(float x, float y, int radius){
        this.leftTopX = x;
        this.leftTopY = y;
        this.radius = radius;
        bounds.set(this.leftTopX,this.leftTopY,this.leftTopX+ this.radius*2,this.leftTopY+this.radius*2);
    }

    public void setSpeed(int spdX, int spdY){
        this.speedX = spdX;
        this.speedY = spdY;
    }

    private void update(Canvas canvas) {
        if(leftTopX >= canvas.getWidth() - 100){
            speedX = -5;
        }
        if(leftTopX <= 0) {
            speedX = 5;
        }
        if(leftTopY >= canvas.getHeight()- 100){
            speedY = -5;
        }

        if(leftTopY <= 0) {
            speedY = 5;
        }
        if((leftTopX >= 345 && leftTopX <= 905) && ((leftTopY >= 500 &&  leftTopY<=1000)))
        {
            speedX *= -1;
        }

        if((leftTopX >= 350 && leftTopX <= 900) && ((leftTopY >= 495 && leftTopY <=1005)))
        {
            speedY *= -1;
        }
        leftTopX += speedX;
        leftTopY += speedY;

        bounds.set(leftTopX , leftTopY , leftTopX + 2 * radius , leftTopY + 2 * radius);
    }
}
