package redcharcoal.campwars;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by kyupas on 7/23/2016.
 */
public class FirePath {
    ResourceHandler resHandler;
    ArrayList<Point> points = new ArrayList<Point>(10);
    boolean bActive = true; // TRUE: still a valid/active path, FALSE: already dead path
    boolean bMainFire;
    Paint ActivePoint = new Paint();
    Paint ActiveLine = new Paint();
    Paint InactivePoint = new Paint();
    Paint InactiveLine = new Paint();
    int nPointRadius;
    int nCampColor;

    public FirePath(ResourceHandler res, boolean bMain, int color, Point startPoint)
    {
        resHandler = res;
        bMainFire = bMain;
        nCampColor = color;
        InactiveLine.setColor(resHandler.campcolor_inactivefire);
        InactivePoint.setColor(resHandler.campcolor_inactivefire);
        InactivePoint.setStrokeWidth(1.0f);
        ActiveLine.setColor(nCampColor);
        ActivePoint.setColor(nCampColor);
        ActivePoint.setStrokeWidth(1.0f);
        addNewPoint(startPoint);

        // main fire
        if (bMainFire) {
            InactiveLine.setStrokeWidth(resHandler.firepath_mainlinethick);
            ActiveLine.setStrokeWidth(resHandler.firepath_mainlinethick);
            nPointRadius = resHandler.firepath_maindotrad;
        }
        else {
            InactiveLine.setStrokeWidth(resHandler.firepath_sublinethick);
            ActiveLine.setStrokeWidth(resHandler.firepath_sublinethick);
            nPointRadius = resHandler.firepath_subdotrad;
        }

    }
    public void addNewPoint(Point newPoint)
    {
        points.add(newPoint);
    }
    public int getPathSize()
    {
        return points.size();
    }

    public Point getPointAt(int nIndex)
    {
        Point empty = new Point();
        empty.x = 0;
        empty.y = 0;

        if(nIndex < points.size())
            return points.get(nIndex);
        else
            return empty;
    }
}
