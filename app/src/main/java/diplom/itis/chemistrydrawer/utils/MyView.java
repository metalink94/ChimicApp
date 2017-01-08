package diplom.itis.chemistrydrawer.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 06.01.2017.
 */

public class MyView extends SurfaceView {

    MyShape myShape;
    int numberOfPoint = 6; //default
    float x, y;
    private List<MyView> pointsList = new ArrayList<MyView>();
    private SurfaceHolder surfaceHolder;

    public MyView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        initMyView();
    }

    public MyView(Context context, float x, float y, int numberOfPoint) {
        super(context);
        this.x = x;
        this.y = y;
        this.numberOfPoint = numberOfPoint;
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceHolder = getHolder();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        initMyView();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        surfaceHolder = getHolder();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        initMyView();
    }

    public void initMyView() {
        myShape = new MyShape();
    }


    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (surfaceHolder.getSurface().isValid()) {
                // Add current touch position to the list of points
                pointsList.add(new MyView(getContext(), (int) event.getX(), (int) event.getY(), numberOfPoint));
                // Get canvas from surface
                Canvas canvas = surfaceHolder.lockCanvas();
                // Clear screen
                canvas.drawColor(Color.WHITE);
                // Iterate on the list
                for (int i = 0; i < pointsList.size(); i++) {
                    MyView current = pointsList.get(i);
                    float radius = 80.0f;
                    // Draw points
                    myShape.setPolygon(current.x, current.y, radius, current.numberOfPoint);
                    canvas.drawPath(myShape.getPath(), myShape.getPaint());
                    // Draw line with next point (if it exists)
                    if (i + 1 < pointsList.size()) {
                        MyView next = pointsList.get(i + 1);
                        canvas.drawLine(current.x, current.y, next.x - 40f, next.y - 60f, paint);
                    }
                }
                // Release canvas
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
        return false;
    }

    public int getNumberOfPoint() {
        return numberOfPoint;
    }

    public void setNumberOfPoint(int numberOfPoint) {
        this.numberOfPoint = numberOfPoint;
    }
}

