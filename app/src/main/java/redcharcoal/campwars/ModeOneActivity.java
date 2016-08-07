package redcharcoal.campwars;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by kyupas on 7/5/2016.
 */
public class ModeOneActivity extends Activity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    public ResourceHandler  resHandler = new ResourceHandler();
    private GestureDetectorCompat mDetector;
    CampWarDrawingBoard drawingboard;
    FirePath firepath;

    private void initResHandler()
    {
        getWindowManager().getDefaultDisplay().getMetrics(resHandler.displayMetrics);
        // TODO: populcate init
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // FULL SCREEN START: Fullscree here, NG behavior observed if declard in manifest
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // FULL SCREEN END

        initResHandler();
        drawingboard = new CampWarDrawingBoard(this, resHandler);
        drawingboard.setBackgroundColor(Color.WHITE);
        setContentView(drawingboard);

        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {

        // TODO: handle fling

        drawingboard.invalidate();
        return true;
    }

    @Override
    public boolean onDown(MotionEvent event) {
        return false;
    }


    @Override
    public void onLongPress(MotionEvent event) {
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent event) {
    }
    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        return false;
    }
}
