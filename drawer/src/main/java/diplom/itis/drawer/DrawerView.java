package diplom.itis.drawer;

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

public class DrawerView extends SurfaceView {

    ShapeDrawer mShape;
    int mNumberOfPoints = 6; //default
    private List<DrawerModel> mPointList = new ArrayList<DrawerModel>();
    private SurfaceHolder mSurfaceHolder;
    private String mText = "";
    private int mColor = Color.RED;
    private int mFontColor = R.color.app_text_color;
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private DrawerDataSender mSender;

    public DrawerView(Context context) {
        super(context);
        mSurfaceHolder = getHolder();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        initMyView();
    }

    public DrawerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mSurfaceHolder = getHolder();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        initMyView();
    }

    public DrawerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSurfaceHolder = getHolder();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        initMyView();
    }

    public void setCallback(DrawerDataSender callback) {
        mSender = callback;
    }

    public void initMyView() {
        mShape = new ShapeDrawer();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (mSurfaceHolder.getSurface().isValid()) {
                // Add current touch position to the list of points
                Canvas canvas = mSurfaceHolder.lockCanvas();
                // Clear screen
                canvas.drawColor(Color.WHITE);
                DrawerModel drawerModel = new DrawerModel((int) event.getX(), (int) event.getY());
                if (getText().isEmpty()) {
                    drawerModel.numberOfAngle = mNumberOfPoints;
                    drawerModel.radious = 80;
                    drawerModel.flag = true;
                    drawerModel.color = mColor;
                    drawerModel.fontColor = mFontColor;
                } else {
                    drawerModel.text = mText;
                    drawerModel.flag = false;
                    drawerModel.fontSize = 24f;
                    drawerModel.color = mColor;
                    drawerModel.fontColor = mFontColor;
                }
                mSender.getLastModel(drawerModel);
                mPointList.add(drawerModel);
                mSender.getListElements(mPointList);
                for (DrawerModel view : mPointList) {
                    if (view.flag) {
                        // Draw points
                        mShape = new ShapeDrawer();
                        mShape.setPolygon(view.xPos, view.yPos, view.radious, view.numberOfAngle);
                        canvas.drawPath(mShape.getPath(), mShape.getPaint());
                    } else {
                        mPaint.setTextSize(view.fontSize);
                        mPaint.setColor(view.fontColor);
                        canvas.drawText(view.text, view.xPos, view.yPos, mPaint);
                    }
                }
                // Release canvas
                mSurfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
        return false;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
    }

    public int getmNumberOfPoints() {
        return mNumberOfPoints;
    }

    public void setmNumberOfPoints(int mNumberOfPoints) {
        this.mNumberOfPoints = mNumberOfPoints;
        this.mText = "";
    }

    public void setColor(int color) {
        this.mColor = color;
        this.mFontColor = color;
    }

    public int getColor() {return mColor;}
}

