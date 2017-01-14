package diplom.itis.chemistrydrawer.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Денис on 06.01.2017.
 */

public class MyShape {

    private Paint paint;
    private Path path;

    public MyShape() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

        path = new Path();
    }

    public void setCircle(float x, float y, float radius, Path.Direction dir) {
        path.reset();
        path.addCircle(x, y, radius, dir);
    }

    public void setPolygon(float x, float y, float radius, int numOfPt) {

        double section = 2.0 * Math.PI / numOfPt;

        path.reset();
        path.moveTo(
                (float) (x + radius * Math.cos(0)),
                (float) (y + radius * Math.sin(0)));

        for (int i = 1; i < numOfPt; i++) {
            path.lineTo(
                    (float) (x + radius * Math.cos(section * i)),
                    (float) (y + radius * Math.sin(section * i)));
        }
        path.close();
    }

    public Path getPath() {
        return path;
    }

    public Paint getPaint() {
        return paint;
    }
}
