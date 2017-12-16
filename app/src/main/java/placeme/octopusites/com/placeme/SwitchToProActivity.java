package placeme.octopusites.com.placeme;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class SwitchToProActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_to_pro);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Place Me");
        ab.setDisplayHomeAsUpEnabled(true);



        TextView trytxt=(TextView)findViewById(R.id.trytxt);

        TextView hitxt=(TextView)findViewById(R.id.hitxt);
        TextView staytxt=(TextView)findViewById(R.id.staytxt);
        trytxt.setTypeface(MyConstants.getBold(this));
        hitxt.setTypeface(MyConstants.getLight(this));
        staytxt.setTypeface(MyConstants.getBold(this));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();

                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }
}
