package redcharcoal.campwars;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by kyupas on 7/23/2016.
 */
public class FirePath {
    public static final int STARTPOINT_IDX_MAIN = 0;
    public static final int STARTPOINT_IDX_SUB1 = 1;
    public static final int STARTPOINT_IDX_SUB2 = 2;

    ResourceHandler resHandler;
    ArrayList<Point> points = new ArrayList<Point>(10);
    boolean bMainFire;
    Paint PaintPoint = new Paint();
    Paint PaintLine = new Paint();
    int nPointRadius;
    int nCampColor;

    public FirePath(ResourceHandler res, boolean bMain, int color, Point startPoint)
    {
        resHandler = res;
        bMainFire = bMain;
        nCampColor = color;
        PaintLine.setColor(nCampColor);
        PaintPoint.setColor(nCampColor);
        PaintPoint.setStrokeWidth(1.0f);
        addNewPoint(startPoint);

        // main fire
        if (bMainFire) {
            PaintLine.setStrokeWidth(resHandler.firepath_mainlinethick);
            nPointRadius = resHandler.firepath_maindotrad;
        }
        else {
            PaintLine.setStrokeWidth(resHandler.firepath_sublinethick);
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
        Point empty = new Point(0,0);
        if(nIndex < points.size())
            return points.get(nIndex);
        else
            return empty;
    }

    public Point getNewPointFromVelocity(float velX, float velY)
    {
        float fRedox = 1.0f/60.0f;
        Point   pNew = new Point(),
                pLast = getPointAt(getPathSize()-1);

        pNew.x = pLast.x + (int)(velX * fRedox);
        pNew.y = pLast.y + (int)(velY * fRedox);
        return pNew;
    }

    public void setFireInactive()
    {
        PaintLine.setColor(resHandler.campcolor_inactivefire);
        PaintPoint.setColor(resHandler.campcolor_inactivefire);
    }

    public void drawMyself(Canvas canvas)
    {
        Point pLastPt = new Point(), pCurPt;

        for(int k = 0; k < points.size(); k++) {
            pCurPt = points.get(k);
            if(bMainFire){ // main fire
                canvas.drawCircle(pCurPt.x, pCurPt.y, resHandler.firepath_maindotrad, PaintPoint);
                if(k > 0)
                    canvas.drawLine(pCurPt.x, pCurPt.y, pLastPt.x, pLastPt.y, PaintLine);
            }
            else // sub fires
            {
                canvas.drawCircle(pCurPt.x, pCurPt.y, resHandler.firepath_subdotrad, PaintPoint);
                if(k > 0)
                    canvas.drawLine(pCurPt.x, pCurPt.y, pLastPt.x, pLastPt.y, PaintLine);
            }
            pLastPt.x = pCurPt.x;
            pLastPt.y = pCurPt.y;
        }
    }
}
