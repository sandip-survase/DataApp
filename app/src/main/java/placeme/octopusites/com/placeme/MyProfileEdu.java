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
import android.widget.Toast;

public class MyProfileEdu extends AppCompatActivity {

    View tenth,twelth,ug,pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_edu);



        TextView tenthtxt=(TextView)findViewById(R.id.tenthtxt);
        TextView twelthtxt=(TextView)findViewById(R.id.twelthtxt);
        TextView ugtxt=(TextView)findViewById(R.id.ugtxt);
        TextView pgtxt=(TextView)findViewById(R.id.pgtxt);

        tenthtxt.setTypeface(MyConstants.getBold(this));
        twelthtxt.setTypeface(MyConstants.getBold(this));
        ugtxt.setTypeface(MyConstants.getBold(this));
        pgtxt.setTypeface(MyConstants.getBold(this));

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Educational Info");
        ab.setDisplayHomeAsUpEnabled(true);

        tenth=(View)findViewById(R.id.tenthbutton);
        twelth=(View)findViewById(R.id.twelfthbutton);
        ug=(View)findViewById(R.id.ugbutton);
        pg=(View)findViewById(R.id.pgbutton);

        tenth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileEdu.this,MyProfileTenth.class),0);
            }
        });

        twelth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileEdu.this,MyProfileTwelthOrDiploma.class),0);
            }
        });

        ug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileEdu.this,MyProfileUg.class),0);
            }
        });

        pg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileEdu.this,MyProfilePg.class),0);
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
