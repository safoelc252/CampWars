package redcharcoal.campwars;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.view.ViewGroup.LayoutParams;


/**
 * Created by kyupas on 6/25/2016.
 */
public class MainMenuActivity extends Activity {

    @Override
     protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // full screen here, NG behavior observed if declard in manifest
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.mainmenu);

        // TODO: Right now, layout is hardcoded, so dynamically handle
        // button realignment based on device's screen size

        // Handle the button clicks
        final Button button_mode1 = (Button)findViewById(R.id.mainmenu_mode1);
        final Button button_mode2 = (Button)findViewById(R.id.mainmenu_mode2);

        // and listener
        button_mode1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                // launch activity
                Intent intentModeOne = new Intent(MainMenuActivity.this, ModeOneActivity.class);
                startActivity(intentModeOne);
            }
        });

        button_mode2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                // launch activity
            }
        });
    }

}
