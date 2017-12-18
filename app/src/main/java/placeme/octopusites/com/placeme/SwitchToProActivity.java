package placeme.octopusites.com.placeme;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
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
        trytxt.setTypeface(Z.getBold(this));
        hitxt.setTypeface(Z.getLight(this));
        staytxt.setTypeface(Z.getBold(this));
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
