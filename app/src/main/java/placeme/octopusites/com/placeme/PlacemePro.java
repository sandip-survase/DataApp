package placeme.octopusites.com.placeme;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PlacemePro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeme_pro);



        Typeface custom_font10 = Typeface.createFromAsset(getAssets(),  "fonts/hint.ttf");
        Typeface custom_font11 = Typeface.createFromAsset(getAssets(),  "fonts/hamm.ttf");



        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(PlacemePro.this.getResources().getColor(R.color.transperent)));

        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_actionbar, null);

        TextView trytxt=(TextView)v.findViewById(R.id.trytxt);
        TextView protxt=(TextView)v.findViewById(R.id.protxt);

        protxt.setTypeface(custom_font10);
        trytxt.setTypeface(custom_font11);

        final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
        upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        actionBar.setHomeAsUpIndicator(upArrow);


        actionBar.setCustomView(v);

        TextView hitxt=(TextView)findViewById(R.id.hitxt);
        TextView feattxt=(TextView)findViewById(R.id.feattxt);
        TextView knowmoretxt=(TextView)findViewById(R.id.knowmoretxt);
        TextView trytxt2=(TextView)findViewById(R.id.trytxt);
        TextView protxt2=(TextView)findViewById(R.id.protxt);

        TextView moretxt=(TextView)findViewById(R.id.moretxt);
        TextView resumetxt=(TextView)findViewById(R.id.resumetxt);
        TextView digitaltxt=(TextView)findViewById(R.id.digitaltxt);
        TextView conntxt=(TextView)findViewById(R.id.conntxt);
        TextView performancetxt=(TextView)findViewById(R.id.performancetxt);

        TextView moremoretxt=(TextView)findViewById(R.id.moremoretxt);
        TextView resumeresumetxt=(TextView)findViewById(R.id.resumeresumetxt);
        TextView digitaldigitaltxt=(TextView)findViewById(R.id.digitaldigitaltxt);
        TextView connconntxt=(TextView)findViewById(R.id.connconntxt);
        TextView performanceperformancetxt=(TextView)findViewById(R.id.performanceperformancetxt);

        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/petro.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/hamm.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/cabinsemibold.ttf");
        Typeface custom_font4 = Typeface.createFromAsset(getAssets(),  "fonts/maven.ttf");
        hitxt.setTypeface(custom_font1);
        feattxt.setTypeface(custom_font2);
        knowmoretxt.setTypeface(custom_font1);
        trytxt2.setTypeface(custom_font2);
        protxt2.setTypeface(custom_font10);

        moretxt.setTypeface(custom_font3);
        resumetxt.setTypeface(custom_font3);
        digitaltxt.setTypeface(custom_font3);
        conntxt.setTypeface(custom_font3);
        performancetxt.setTypeface(custom_font3);

        moremoretxt.setTypeface(custom_font4);
        resumeresumetxt.setTypeface(custom_font4);
        digitaldigitaltxt.setTypeface(custom_font4);
        connconntxt.setTypeface(custom_font4);
        performanceperformancetxt.setTypeface(custom_font4);

        knowmoretxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(PlacemePro.this, KnowMoreActivity.class);
                startActivity(i2);
                overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
            }
        });

        RelativeLayout switchtorl=(RelativeLayout)findViewById(R.id.switchtorl);
        switchtorl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlacemePro.this,SwitchToProActivity.class));
            }
        });
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
