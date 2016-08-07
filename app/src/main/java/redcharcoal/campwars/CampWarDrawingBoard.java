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
    Point[] startpoint1 = new Point[3];
    Point[] startpoint2 = new Point[3];
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

        // TODO: outline camp1
        float side1 = resHandler.displayMetrics.widthPixels / 3;
        float side2 = side1/2;


        // TODO: init firepath origins for camp1
        // TODO: outline camp2
        // TODO: init firepath origins for camp2

        camp1 = new Camps(resHandler, resHandler.campcolor_camptop, campoutline1, startpoint1);
        camp2 = new Camps(resHandler, resHandler.campcolor_campbottom, campoutline2, startpoint2);

    }

    @Override
    public void onDraw(Canvas canvas)
    {
        // TODO: draw the camps based from path in camp object
        // TODO: draw the camps END

        // TODO: then draw all fire paths

    }
}
