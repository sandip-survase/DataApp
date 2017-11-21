package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MyProfileAccomplishments extends AppCompatActivity {

    View knownlang,certifications,courses,skills,honors,patents,publications;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_accomplishments);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Accomplishments");
        ab.setDisplayHomeAsUpEnabled(true);

        username=getIntent().getStringExtra("username");


        TextView knownlangtxt=(TextView)findViewById(R.id.knownlangtxt);
        TextView certitxt=(TextView)findViewById(R.id.certitxt);
        TextView coursetxt=(TextView)findViewById(R.id.coursetxt);
        TextView skillstxt=(TextView)findViewById(R.id.skillstxt);
        TextView honortxt=(TextView)findViewById(R.id.honortxt);
        TextView patenttxt=(TextView)findViewById(R.id.patenttxt);
        TextView publicationtxt=(TextView)findViewById(R.id.publicationtxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/arba.ttf");
        knownlangtxt.setTypeface(custom_font1);
        certitxt.setTypeface(custom_font1);
        coursetxt.setTypeface(custom_font1);
        skillstxt.setTypeface(custom_font1);
        honortxt.setTypeface(custom_font1);
        patenttxt.setTypeface(custom_font1);
        publicationtxt.setTypeface(custom_font1);

        knownlang=(View)findViewById(R.id.studentBlock);
        skills=(View)findViewById(R.id.alumniBlock);
        certifications=(View)findViewById(R.id.certibutton);
        courses=(View)findViewById(R.id.coursebutton);
        honors=(View)findViewById(R.id.adminBlock);
        patents=(View)findViewById(R.id.patentbutton);
        publications=(View)findViewById(R.id.publicationbutton);

        knownlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileAccomplishments.this,MyProfileKnownLang.class).putExtra("username",username),0);
            }
        });

        skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileAccomplishments.this,MyProfileSkills.class).putExtra("username",username),0);
            }
        });
        certifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileAccomplishments.this,MyProfileCertifications.class).putExtra("username",username),0);
            }
        });
        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileAccomplishments.this,MyProfileCourses.class).putExtra("username",username),0);
            }
        });
        honors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileAccomplishments.this,MyProfileAchievements.class).putExtra("username",username),0);
            }
        });
        patents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileAccomplishments.this,MyProfilePatents.class).putExtra("username",username),0);
            }
        });
        publications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MyProfileAccomplishments.this,MyProfilePublications.class).putExtra("username",username),0);

            }
        });
        ScrollView myprofileintroscrollview=(ScrollView)findViewById(R.id.myprofileaccomplishments);
        disableScrollbars(myprofileintroscrollview);
    }
    private void disableScrollbars(ScrollView scrollView) {
        if (scrollView != null) {

            scrollView.setVerticalScrollBarEnabled(false);

        }
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
