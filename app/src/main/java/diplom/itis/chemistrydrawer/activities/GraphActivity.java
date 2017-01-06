package diplom.itis.chemistrydrawer.activities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

import diplom.itis.chemistrydrawer.utils.BaseActivity;

/**
 * Created by Денис on 06.01.2017.
 */

public class GraphActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawingView(this));
    }

    class DrawingView extends SurfaceView {

        private final SurfaceHolder surfaceHolder;
        private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        private List<Point> pointsList = new ArrayList<Point>();

        public DrawingView(Context context) {
            super(context);
            surfaceHolder = getHolder();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (surfaceHolder.getSurface().isValid()) {

                    // Add current touch position to the list of points
                    pointsList.add(new Point((int) event.getX(), (int) event.getY()));

                    // Get canvas from surface
                    Canvas canvas = surfaceHolder.lockCanvas();

                    // Clear screen
                    canvas.drawColor(Color.BLACK);

                    // Iterate on the list
                    for (int i = 0; i < pointsList.size(); i++) {
                        Point current = pointsList.get(i);

                        // Draw points
                        canvas.drawCircle(current.x, current.y, 10, paint);

                        // Draw line with next point (if it exists)
                        if (i + 1 < pointsList.size()) {
                            Point next = pointsList.get(i + 1);
                            canvas.drawLine(current.x, current.y, next.x, next.y, paint);
                        }
                    }

                    // Release canvas
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            return false;
        }
    }
}
