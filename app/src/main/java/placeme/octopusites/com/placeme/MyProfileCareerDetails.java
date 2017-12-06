package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MyProfileCareerDetails extends AppCompatActivity {

    View careerobjbutton,strengthbutton,weakbutton,locbutton;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_career_details);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Career Details");
        ab.setDisplayHomeAsUpEnabled(true);

        username=getIntent().getStringExtra("username");

        TextView careerobjtxt=(TextView)findViewById(R.id.careerobjtxt);
        TextView strengthtxt=(TextView)findViewById(R.id.strengthtxt);
        TextView weaktxt=(TextView)findViewById(R.id.weaktxt);
        TextView locationtxt=(TextView)findViewById(R.id.locationtxt);

        careerobjtxt.setTypeface(MyConstants.getBold(this));
        strengthtxt.setTypeface(MyConstants.getBold(this));
        weaktxt.setTypeface(MyConstants.getBold(this));
        locationtxt.setTypeface(MyConstants.getBold(this));

        careerobjbutton=(View)findViewById(R.id.careerobjbutton);
        strengthbutton=(View)findViewById(R.id.strengthbutton);
        weakbutton=(View)findViewById(R.id.weakbutton);
        locbutton=(View)findViewById(R.id.locbutton);

        careerobjbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileCareerDetails.this,MyProfileCareerObj.class).putExtra("username",username),0);
            }
        });
        strengthbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileCareerDetails.this,MyProfileStrengths.class).putExtra("username",username),0);

            }
        });
        weakbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileCareerDetails.this,MyProfileWeaknesses.class).putExtra("username",username),0);
            }
        });
        locbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileCareerDetails.this,MyProfileLocationPreferences.class).putExtra("username",username),0);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE)
        {
            setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
        }
        if(resultCode==MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE)
        {
            setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
        }
    }
}
