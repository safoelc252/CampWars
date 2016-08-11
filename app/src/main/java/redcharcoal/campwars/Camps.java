package redcharcoal.campwars;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by kyupas on 7/25/2016.
 */
public class Camps {
    ResourceHandler resHandler;
    Paint basePaintOutline = new Paint();
    Paint basePaintFill = new Paint();
    Path basePath;
    ArrayList<ArrayList<FirePath>> firecontainer = new ArrayList<ArrayList<FirePath>>();


    public Camps(ResourceHandler res, int campcolor, Path campoutline, Point[] startpoints)
    {
        resHandler = res;

        // add the fires
        ArrayList<FirePath> mainfire = new ArrayList<FirePath>();
        ArrayList<FirePath> subfire1 = new ArrayList<FirePath>();
        ArrayList<FirePath> subfire2 = new ArrayList<FirePath>();

        FirePath maintmp = new FirePath(res, true, campcolor, startpoints[FirePath.STARTPOINT_IDX_MAIN]);
        FirePath sub1tmp = new FirePath(res, false, campcolor, startpoints[FirePath.STARTPOINT_IDX_SUB1]);
        FirePath sub2tmp = new FirePath(res, false, campcolor, startpoints[FirePath.STARTPOINT_IDX_SUB2]);

        mainfire.add(maintmp);
        subfire1.add(sub1tmp);
        subfire2.add(sub2tmp);
        firecontainer.add(mainfire);
        firecontainer.add(subfire1);
        firecontainer.add(subfire2);

        basePath = campoutline;
        basePaintOutline.setColor(resHandler.campcolor_campoutline);
        basePaintOutline.setStrokeWidth(1.0f);
        basePaintOutline.setStyle(Paint.Style.STROKE);
        basePaintFill.setColor(campcolor);
        basePaintFill.setStyle(Paint.Style.FILL);
    }

    public void drawMyself(Canvas canvas)
    {
        //canvas.drawPath(basePath, basePaintFill);
        canvas.drawPath(basePath, basePaintOutline);
    }

}
