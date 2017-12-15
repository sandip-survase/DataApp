package placeme.octopusites.com.placeme;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class ProSplashScreen extends Activity {

    ProgressBar progressBar;
    int ifback=0;
    String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_splash_screen);


        TextView trytxt2=(TextView)findViewById(R.id.trytxt);
        trytxt2.setTypeface(MyConstants.getBold(this));

//        ProfileRole r=new ProfileRole();
//        role=r.getRole();
        role=MySharedPreferencesManager.getRole(ProSplashScreen.this);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(role.equals("admin"))
                    startActivity(new Intent(getApplicationContext(), PlacemeProAdmin.class));
                    else if(role.equals("student"))
                startActivity(new Intent(getApplicationContext(), PlacemePro.class));
                else if(role.equals("alumni"))
                    startActivity(new Intent(getApplicationContext(), PlacemePro.class));
                finish();
            }
        }, 2500);

        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.splashprogress);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mProgressBar, "progress", 0, 1000);
        progressAnimator.setDuration(2500);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
