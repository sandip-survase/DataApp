package placeme.octopusites.com.placeme;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
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

        Typeface custom_font10 = Typeface.createFromAsset(getAssets(),  "fonts/hint.ttf");
        Typeface custom_font11 = Typeface.createFromAsset(getAssets(),  "fonts/hamm.ttf");

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(SwitchToProActivity.this.getResources().getColor(R.color.transperent)));

        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_actionbar, null);

        TextView trytxt=(TextView)v.findViewById(R.id.trytxt);
        TextView protxt=(TextView)v.findViewById(R.id.protxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/maven.ttf");
        protxt.setTypeface(custom_font10);
        trytxt.setTypeface(custom_font11);

        actionBar.setCustomView(v);

        TextView trytxt2=(TextView)findViewById(R.id.trytxt);
        TextView protxt2=(TextView)findViewById(R.id.protxt);
        TextView hitxt=(TextView)findViewById(R.id.hitxt);
        TextView staytxt=(TextView)findViewById(R.id.staytxt);

        trytxt2.setTypeface(custom_font11);
        protxt2.setTypeface(custom_font10);
        hitxt.setTypeface(custom_font1);
        staytxt.setTypeface(custom_font1);
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
