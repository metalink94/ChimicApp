package diplom.itis.drawer;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Денис on 06.01.2017.
 */

public class ShapeDrawer {

    private Paint paint;
    private Path path;
    public int mColor = Color.BLACK;

    public ShapeDrawer() {
        paint = new Paint();
        paint.setColor(mColor);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
    }

    public ShapeDrawer(int color) {
        paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
    }

    public void setCircle(float x, float y, float radius, Path.Direction dir) {
        path = new Path();
        path.reset();
        path.addCircle(x, y, radius, dir);
    }

    public void setPolygon(float x, float y, float radius, int numOfPt) {
        path = new Path();
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

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
