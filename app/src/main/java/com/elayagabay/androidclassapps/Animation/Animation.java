package com.elayagabay.androidclassapps.Animation;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

public class Animation extends AppCompatActivity {
    CanvasBoard canvasBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        canvasBoard = new CanvasBoard(this);
        canvasBoard.setBackgroundColor(Color.BLACK);
        setContentView(canvasBoard);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
