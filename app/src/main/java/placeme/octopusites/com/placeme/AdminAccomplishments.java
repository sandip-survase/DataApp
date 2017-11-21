package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AdminAccomplishments extends AppCompatActivity {

    View knownlang,skills,honors,patents,publications;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_accomplishments);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Accomplishments");
        ab.setDisplayHomeAsUpEnabled(true);



        username=getIntent().getStringExtra("username");


        TextView knownlangtxt=(TextView)findViewById(R.id.knownlangtxt);
        TextView skillstxt=(TextView)findViewById(R.id.skillstxt);
        TextView honortxt=(TextView)findViewById(R.id.honortxt);
        TextView patenttxt=(TextView)findViewById(R.id.patenttxt);
        TextView publicationtxt=(TextView)findViewById(R.id.publicationtxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/arba.ttf");
        knownlangtxt.setTypeface(custom_font1);
        skillstxt.setTypeface(custom_font1);
        honortxt.setTypeface(custom_font1);
        patenttxt.setTypeface(custom_font1);
        publicationtxt.setTypeface(custom_font1);

        knownlang=(View)findViewById(R.id.studentBlock);
        skills=(View)findViewById(R.id.alumniBlock);
        honors=(View)findViewById(R.id.adminBlock);
        patents=(View)findViewById(R.id.patentbutton);
        publications=(View)findViewById(R.id.publicationbutton);

        knownlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(AdminAccomplishments.this,MyProfileKnownLang.class).putExtra("username",username),0);
            }
        });

        skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(AdminAccomplishments.this,MyProfileSkills.class).putExtra("username",username),0);
            }
        });
        honors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(AdminAccomplishments.this,MyProfileAchievements.class).putExtra("username",username),0);
            }
        });
        patents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(AdminAccomplishments.this,MyProfilePatents.class).putExtra("username",username),0);
            }
        });
        publications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(AdminAccomplishments.this,MyProfilePublications.class).putExtra("username",username),0);

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

        if(resultCode==HRActivity.HR_DATA_CHANGE_RESULT_CODE)
        {
            setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);
        }
        Log.d("TAG", "AdminAccomplishments: editprofile "+resultCode);
    }
}
