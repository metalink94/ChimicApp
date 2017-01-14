package diplom.itis.chemistrydrawer.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
    private String text = "";
    boolean flag;

    public MyView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        initMyView();
    }

    public MyView(Context context, float x, float y, int numberOfPoint, boolean flag) {
        super(context);
        this.x = x;
        this.y = y;
        this.numberOfPoint = numberOfPoint;
        this.text = "";
        this.flag = flag;
    }

    public MyView(Context context, float x, float y, String text, boolean flag) {
        super(context);
        this.x = x;
        this.y = y;
        this.text = text;
        this.flag = flag;
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
                Canvas canvas = surfaceHolder.lockCanvas();
                // Clear screen
                canvas.drawColor(Color.WHITE);

                if (getText().equals("")) {
                    pointsList.add(new MyView(getContext(), (int) event.getX(), (int) event.getY(), numberOfPoint, true));
                } else {
                    pointsList.add(new MyView(getContext(), (int) event.getX(), (int) event.getY(), text, false));
                }
                for (MyView view : pointsList) {
                    if (view.flag) {
                        float radius = 80.0f;
                        // Draw points
                        myShape.setPolygon(view.x, view.y, radius, view.numberOfPoint);
                        canvas.drawPath(myShape.getPath(), myShape.getPaint());
                    } else {
                        paint.setTextSize(24f);
                        canvas.drawText(view.text, view.x, view.y, paint);
                    }
                }
                // Release canvas
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
        return false;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumberOfPoint() {
        return numberOfPoint;
    }

    public void setNumberOfPoint(int numberOfPoint) {
        this.numberOfPoint = numberOfPoint;
        this.text = "";
    }
}

