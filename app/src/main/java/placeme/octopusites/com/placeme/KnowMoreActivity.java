package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class KnowMoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_more);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ImageView close=(ImageView)findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });

        final TextView feattxt=(TextView)findViewById(R.id.feattxt);
        TextView trytxt2=(TextView)findViewById(R.id.trytxt);
        TextView protxt2=(TextView)findViewById(R.id.protxt);

        RelativeLayout switchtorl=(RelativeLayout)findViewById(R.id.switchtorl);
        switchtorl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KnowMoreActivity.this,SwitchToProActivity.class));
                finish();
            }
        });

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
        Typeface custom_font10 = Typeface.createFromAsset(getAssets(),  "fonts/hint.ttf");
        Typeface custom_font11 = Typeface.createFromAsset(getAssets(),  "fonts/hamm.ttf");
        feattxt.setTypeface(custom_font2);
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
    }
}
