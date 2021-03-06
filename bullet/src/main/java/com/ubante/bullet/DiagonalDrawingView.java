package com.ubante.bullet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * I tried to extend SimpleDrawingView but it's easier to make a new class.
 */
public class DiagonalDrawingView extends View {
    // setup initial color
    private int paintColor = Color.BLACK;
    // defines paint and canvas
    private Paint drawPaint;
    // stores next circle
    private Path path = new Path();
    // stores the point of motion down and motion up
    Point startPoint,endPoint;
    private DiagonalReleaseListener onReleaseListener;

    public interface DiagonalReleaseListener {
        void onRelease();
    }

    public Path getPath() { return path; }

    public Point getStartPoint() { return startPoint; }

    public Point getEndPoint() { return endPoint; }

    public DiagonalDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
    }

    private void setupPaint() {
        // Setup paint with color and stroke styles
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, drawPaint);
    }

    public void clearScreen() {
        path.reset();
        postInvalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();
        // Checks for the event that occurs
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX, pointY);
                startPoint = new Point(pointX,pointY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);
                break;
            case MotionEvent.ACTION_UP:
                endPoint = new Point(pointX,pointY);
                if (onReleaseListener == null) {
                    Toast.makeText(getContext(),"onReleaseListener is null",
                            Toast.LENGTH_SHORT).show();
                } else {
                    onReleaseListener.onRelease();
                }
            default:
                return false;
        }
        // Force a view to draw again
        postInvalidate();
        return true;
    }

    public float getSlope() {
        float slope;

        slope = (endPoint.getY()-startPoint.getY()) / (endPoint.getX()-startPoint.getX());

        return slope * -1; // The y-axis is reversed
    }

    public void setOnReleaseListener(DiagonalReleaseListener onReleaseListener) {
        this.onReleaseListener = onReleaseListener;
    }
}
