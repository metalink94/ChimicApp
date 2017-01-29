package diplom.itis.chemistrydrawer.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

import diplom.itis.chemistrydrawer.R;
import diplom.itis.chemistrydrawer.models.GraphModel;

/**
 * Created by Денис on 06.01.2017.
 */

public class MyView extends SurfaceView {

    MyShape myShape;
    int numberOfPoint = 6; //default
    private List<GraphModel> pointsList = new ArrayList<GraphModel>();
    private SurfaceHolder surfaceHolder;
    private String text = "";
    private int color = Color.RED;
    private float fontSize = 24f;
    private int fontColor = R.color.app_text_color;
    private int radious = 80;

    public MyView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        initMyView();
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
                GraphModel graphModel = new GraphModel((int) event.getX(), (int) event.getY());
                if (getText().equals("")) {
                    graphModel.numberOfAngle = numberOfPoint;
                    graphModel.radious = radious;
                    graphModel.flag = true;
                    graphModel.color = color;
                } else {
                    graphModel.text = text;
                    graphModel.flag = false;
                    graphModel.fontSize = fontSize;
                    graphModel.fontColor = fontColor;
                }
                pointsList.add(graphModel);
                for (GraphModel view : pointsList) {
                    if (view.flag) {
                        // Draw points
                        Paint paintShape = myShape.getPaint();
                        paintShape.setColor(graphModel.color);
                        myShape.setPolygon(view.xPos, view.yPos, view.radious, view.numberOfAngle);
                        canvas.drawPath(myShape.getPath(), paintShape);
                    } else {
                        paint.setTextSize(view.fontSize);
                        paint.setColor(view.fontColor);
                        canvas.drawText(view.text, view.xPos, view.yPos, paint);
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

    public void setColor(int color) {
        this.color = color;
        this.fontColor = color;
    }

    public int getColor() {return color;}
}

