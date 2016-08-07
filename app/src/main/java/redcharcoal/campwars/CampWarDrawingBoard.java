package redcharcoal.campwars;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;

import java.lang.reflect.Array;

/**
 * Created by kyupas on 7/23/2016.
 */
public class CampWarDrawingBoard extends View {
    public static final int STARTPOINT_IDX_MAIN = 0;
    public static final int STARTPOINT_IDX_SUB1 = 1;
    public static final int STARTPOINT_IDX_SUB2 = 2;
    ResourceHandler resHandler;
    Path campoutline1 = new Path();
    Path campoutline2 = new Path();
    Point[] startpoint1 = {new Point(), new Point(), new Point()};
    Point[] startpoint2 = {new Point(), new Point(), new Point()};
    Camps camp1;
    Camps camp2;

    public CampWarDrawingBoard(Context context, ResourceHandler res) {
        super(context);
        resHandler = res;
        initialize();
    }

    private void initialize()
    {
        // TODO: in the future, outlines will be replaced with drawings/bitmaps

        // outline camp1
        float scrwidth = resHandler.displayMetrics.widthPixels;
        float side1 = scrwidth / 3;
        float side2 = side1/2;
        float campwidth = side1 + (side2*2);
        float leftMargin = (scrwidth - campwidth)/2;

        campoutline1.setLastPoint(leftMargin, 0);
        campoutline1.rLineTo(side1+(side2*2), 0);
        campoutline1.rLineTo(0, side2);
        campoutline1.rLineTo(-side2, 0);
        campoutline1.rLineTo(0, side2);
        campoutline1.rLineTo(-side1, 0);
        campoutline1.rLineTo(0, -side2);
        campoutline1.rLineTo(-side2, 0);
        campoutline1.close();

        // init firepath origins for camp1
        startpoint1[STARTPOINT_IDX_MAIN].x = (int)(leftMargin+(campwidth/2));
        startpoint1[STARTPOINT_IDX_MAIN].y = (int)side1;
        startpoint1[STARTPOINT_IDX_SUB1].x = (int)(leftMargin+(side2/2));
        startpoint1[STARTPOINT_IDX_SUB1].y = (int)side2;
        startpoint1[STARTPOINT_IDX_SUB2].x = (int)(leftMargin+side2+side1+(side2/2));
        startpoint1[STARTPOINT_IDX_SUB2].y = (int)side2;

        // TODO: outline camp2
        // TODO: init firepath origins for camp2

        camp1 = new Camps(resHandler, resHandler.campcolor_camptop, campoutline1, startpoint1);
        camp2 = new Camps(resHandler, resHandler.campcolor_campbottom, campoutline2, startpoint2);

    }

    @Override
    public void onDraw(Canvas canvas)
    {
        // draw the camps based from path in camp object
        canvas.drawPath(camp1.basePath, camp1.basePaintFill);
        canvas.drawPath(camp1.basePath, camp1.basePaintOutline);
        // draw the camps END

        FirePath curFire;
        Point pLastPt = new Point(), pCurPt;
        Paint pPoint, pLine;

        // TODO: then draw all fire paths
        for(int i = 0; i < camp1.firecontainer.size(); i++) {
            for(int j = 0; j < camp1.firecontainer.get(i).size(); j++) {
                curFire = camp1.firecontainer.get(i).get(j);
                if (curFire.bActive) {
                    pPoint = curFire.ActivePoint;
                    pLine = curFire.ActiveLine;
                }
                else {
                    pPoint = curFire.InactivePoint;
                    pLine = curFire.InactiveLine;
                }
                for(int k = 0; k < curFire.points.size(); k++) {
                    pCurPt = curFire.points.get(k);
                    if(i == 0){ // main fire
                        canvas.drawCircle(pCurPt.x, pCurPt.y, resHandler.firepath_maindotrad, pPoint);
                        if(k > 0)
                            canvas.drawLine(pCurPt.x, pCurPt.y, pLastPt.x, pLastPt.y, pLine);
                    }
                    else // sub fires
                    {
                        canvas.drawCircle(pCurPt.x, pCurPt.y, resHandler.firepath_subdotrad, pPoint);
                        if(k > 0)
                            canvas.drawLine(pCurPt.x, pCurPt.y, pLastPt.x, pLastPt.y, pLine);
                    }
                    pLastPt = pCurPt;
                }
            }
        }
    }
}
