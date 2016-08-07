package redcharcoal.campwars;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by kyupas on 7/25/2016.
 */
public class Camps {
    ResourceHandler resHandler;
    Color baseColor;
    Path basePath;
    ArrayList<FirePath> mainfire = new ArrayList<FirePath>();
    ArrayList<FirePath> subfire1 = new ArrayList<FirePath>();
    ArrayList<FirePath> subfire2 = new ArrayList<FirePath>();


    public Camps(ResourceHandler res, int campcolor, Path campoutline, Point[] startpoints)
    {
        resHandler = res;
        // add the fires
        FirePath maintmp = new FirePath(res, true, campcolor, startpoints[CampWarDrawingBoard.STARTPOINT_IDX_MAIN]);
        FirePath sub1tmp = new FirePath(res, false, campcolor, startpoints[CampWarDrawingBoard.STARTPOINT_IDX_SUB1]);
        FirePath sub2tmp = new FirePath(res, false, campcolor, startpoints[CampWarDrawingBoard.STARTPOINT_IDX_SUB2]);

        mainfire.add(maintmp);
        subfire1.add(sub1tmp);
        subfire2.add(sub2tmp);

        basePath = campoutline;

    }
}
