package redcharcoal.campwars;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by kyupas on 7/23/2016.
 */
public class CampWarDrawingBoard extends View {
    public static final float NEARBYFIRE_THRESHOLD = 50.0f; // 50px radius

    ResourceHandler resHandler;
    Camps camp1;
    Camps camp2;
    Camps campsturn;
    FirePath fireturn;

    public CampWarDrawingBoard(Context context, ResourceHandler res) {
        super(context);
        resHandler = res;
        initialize();
    }

    private void initialize() // all drawing calculations are done in drawing board. actual drawing is delegated to each objects.
    {
        // TODO: in the future, outlines will be replaced with drawings/bitmaps

        // outline camp1 and camp2
        Path campoutline1 = new Path();
        Path campoutline2 = new Path();
        Point[] startpoint1 = {new Point(), new Point(), new Point()};
        Point[] startpoint2 = {new Point(), new Point(), new Point()};
        float scrwidth = resHandler.displayMetrics.widthPixels;
        float scrheight = resHandler.displayMetrics.heightPixels;
        float side1 = scrwidth / 3;
        float side2 = side1 / 2;
        float campwidth = side1 + (side2 * 2);
        float leftMargin = (scrwidth - campwidth) / 2;

        campoutline1.setLastPoint(leftMargin, 0);
        campoutline1.rLineTo(side1 + (side2 * 2), 0);
        campoutline1.rLineTo(0, side2);
        campoutline1.rLineTo(-side2, 0);
        campoutline1.rLineTo(0, side2);
        campoutline1.rLineTo(-side1, 0);
        campoutline1.rLineTo(0, -side2);
        campoutline1.rLineTo(-side2, 0);
        campoutline1.close();

        // init firepath origins for camp1
        startpoint1[FirePath.STARTPOINT_IDX_MAIN].x = (int) (leftMargin + (campwidth / 2));
        startpoint1[FirePath.STARTPOINT_IDX_MAIN].y = (int) side1;
        startpoint1[FirePath.STARTPOINT_IDX_SUB1].x = (int) (leftMargin + (side2 / 2));
        startpoint1[FirePath.STARTPOINT_IDX_SUB1].y = (int) side2;
        startpoint1[FirePath.STARTPOINT_IDX_SUB2].x = (int) (leftMargin + side2 + side1 + (side2 / 2));
        startpoint1[FirePath.STARTPOINT_IDX_SUB2].y = (int) side2;

        campoutline2.setLastPoint(leftMargin, scrheight);
        campoutline2.rLineTo(0, -side2);
        campoutline2.rLineTo(side2, 0);
        campoutline2.rLineTo(0, -side2);
        campoutline2.rLineTo(side1, 0);
        campoutline2.rLineTo(0, side2);
        campoutline2.rLineTo(side2, 0);
        campoutline2.rLineTo(0, side2);
        campoutline2.close();

        // init firepath origins for camp2
        startpoint2[FirePath.STARTPOINT_IDX_MAIN].x = (int) (leftMargin + (campwidth / 2));
        startpoint2[FirePath.STARTPOINT_IDX_MAIN].y = (int) (scrheight - side1);
        startpoint2[FirePath.STARTPOINT_IDX_SUB1].x = (int) (leftMargin + (side2 / 2));
        startpoint2[FirePath.STARTPOINT_IDX_SUB1].y = (int) (scrheight - side2);
        startpoint2[FirePath.STARTPOINT_IDX_SUB2].x = (int) (leftMargin + side2 + side1 + (side2 / 2));
        startpoint2[FirePath.STARTPOINT_IDX_SUB2].y = (int) (scrheight - side2);

        camp1 = new Camps(resHandler, resHandler.campcolor_camptop, campoutline1, startpoint1);
        camp2 = new Camps(resHandler, resHandler.campcolor_campbottom, campoutline2, startpoint2);


        // TODO: make a coin-toss-like activity to be fair with the torn
        setTurn(camp1);
    }

    public void setTurn(Camps camp)
    {
        campsturn = camp;
    }

    public void toggleTurn()
    {
        if (campsturn == camp1)
            setTurn(camp2);
        else
            setTurn(camp1);
    }

    public void processLongPress(MotionEvent event)
    {
        ArrayList<FirePath> validFire = new ArrayList<FirePath>();
        ArrayList<Float> validFireDist = new ArrayList<Float>();
        FirePath curFirePath;
        Point pLastPt;
        float distance = 0.0f,
                deltaX = 0.0f,
                deltaY = 0.0f;

        for(int i = 0; i < campsturn.firecontainer.size(); i++) {
            for(int j = 0; j < campsturn.firecontainer.get(i).size(); j++) {

                curFirePath = campsturn.firecontainer.get(i).get(j);
                pLastPt = curFirePath.getPointAt(curFirePath.getPathSize()-1);
                deltaX = Math.abs(pLastPt.x - event.getRawX());
                deltaY = Math.abs(pLastPt.y - event.getRawY());
                distance = (float)Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

                if (distance < NEARBYFIRE_THRESHOLD) {
                    validFire.add(curFirePath);
                    validFireDist.add(distance);
                }
            }
        }

        // find the nearest
        float fSmallest = 0.0f;
        for(int k = 0; k < validFireDist.size(); k++)
        {
            if (k == 0)
            {
                fireturn = validFire.get(k);
                fSmallest = validFireDist.get(k);
            }
            else
            {
                if (validFireDist.get(k) < fSmallest) {
                    fireturn = validFire.get(k);
                    fSmallest = validFireDist.get(k);
                }
            }
        }
    }

    public void processFling(float velocityX, float velocityY)
    {
        if (fireturn != null) {
            fireturn.addNewPoint(fireturn.getNewPointFromVelocity(velocityX, velocityY));
            fireturn = null;
            toggleTurn();
        }
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        // draw the camps based from path in camp object
        camp1.drawMyself(canvas);
        camp2.drawMyself(canvas);

        // then draw all fire paths
        for(int i = 0; i < camp1.firecontainer.size(); i++) {
            for(int j = 0; j < camp1.firecontainer.get(i).size(); j++) {
                camp1.firecontainer.get(i).get(j).drawMyself(canvas);
            }
        }
        for(int i = 0; i < camp2.firecontainer.size(); i++) {
            for(int j = 0; j < camp2.firecontainer.get(i).size(); j++) {
                camp2.firecontainer.get(i).get(j).drawMyself(canvas);
            }
        }
    }
}
