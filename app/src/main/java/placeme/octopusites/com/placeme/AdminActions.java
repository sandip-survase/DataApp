package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AdminActions extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_actions);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Privileges");
        ab.setDisplayHomeAsUpEnabled(true);

        TextView createnotitxt=(TextView)findViewById(R.id.createnotitxt);
        TextView createplacementtxt=(TextView)findViewById(R.id.createplacementtxt);
        TextView createnotitxt3=(TextView)findViewById(R.id.createnotitxt3);
        TextView createnotitxt4=(TextView)findViewById(R.id.createnotitxt4);
        TextView createnotitxt5=(TextView)findViewById(R.id.createnotitxt5);
        TextView createnotitxt6=(TextView)findViewById(R.id.createnotitxt6);
        TextView createnotitxt7=(TextView)findViewById(R.id.createnotitxt7);
        TextView createnotitxt8=(TextView)findViewById(R.id.createnotitxt8);
        TextView createnotitxt9=(TextView)findViewById(R.id.createnotitxt9);
        TextView createnotitxt10=(TextView)findViewById(R.id.createnotitxt10);
        TextView createnotitxt11=(TextView)findViewById(R.id.createnotitxt11);

        TextView createnotinotitxt=(TextView)findViewById(R.id.createnotinotitxt);
        TextView createplacementplacementtxt=(TextView)findViewById(R.id.createplacementplacementtxt);
        TextView createnotinotitxt3=(TextView)findViewById(R.id.createnotinotitxt3);
        TextView createnotinotitxt4=(TextView)findViewById(R.id.createnotinotitxt4);
        TextView createnotinotitxt5=(TextView)findViewById(R.id.createnotinotitxt5);
        TextView createnotinotitxt6=(TextView)findViewById(R.id.createnotinotitxt6);
        TextView createnotinotitxt7=(TextView)findViewById(R.id.createnotinotitxt7);
        TextView createnotinotitxt8=(TextView)findViewById(R.id.createnotinotitxt8);
        TextView createnotinotitxt9=(TextView)findViewById(R.id.createnotinotitxt9);
        TextView createnotinotitxt10=(TextView)findViewById(R.id.createnotinotitxt10);
        TextView createnotinotitxt11=(TextView)findViewById(R.id.createnotinotitxt11);

        TextView createnotificationtxt=(TextView)findViewById(R.id.createnotificationtxt);
        TextView editnotificationtxt=(TextView)findViewById(R.id.editnotificationtxt);
        TextView createplacementttxt=(TextView)findViewById(R.id.createplacementttxt);
        TextView editnotificationtxt2=(TextView)findViewById(R.id.editnotificationtxt2);
        TextView createnotificationtxt3=(TextView)findViewById(R.id.createnotificationtxt3);
        TextView editnotificationtxt3=(TextView)findViewById(R.id.editnotificationtxt3);
        TextView editnotificationtxt11=(TextView)findViewById(R.id.editnotificationtxt11);
        TextView editnotificationtxt4=(TextView)findViewById(R.id.editnotificationtxt4);
        TextView editnotificationtxt5=(TextView)findViewById(R.id.editnotificationtxt5);
        TextView editnotificationtxt6=(TextView)findViewById(R.id.editnotificationtxt6);
        TextView editnotificationtxt7=(TextView)findViewById(R.id.editnotificationtxt7);
        TextView editnotificationtxt8=(TextView)findViewById(R.id.editnotificationtxt8);
        TextView editnotificationtxt9=(TextView)findViewById(R.id.editnotificationtxt9);
        TextView editnotificationtxt10=(TextView)findViewById(R.id.editnotificationtxt10);

        createnotificationtxt.setTypeface(Z.getBold(this));
        editnotificationtxt.setTypeface(Z.getBold(this));
        createplacementttxt.setTypeface(Z.getBold(this));
        editnotificationtxt2.setTypeface(Z.getBold(this));
        createnotificationtxt3.setTypeface(Z.getBold(this));
        editnotificationtxt3.setTypeface(Z.getBold(this));
        editnotificationtxt11.setTypeface(Z.getBold(this));
        editnotificationtxt4.setTypeface(Z.getBold(this));
        editnotificationtxt5.setTypeface(Z.getBold(this));
        editnotificationtxt6.setTypeface(Z.getBold(this));
        editnotificationtxt7.setTypeface(Z.getBold(this));
        editnotificationtxt8.setTypeface(Z.getBold(this));
        editnotificationtxt9.setTypeface(Z.getBold(this));
        editnotificationtxt10.setTypeface(Z.getBold(this));


        createnotitxt.setTypeface(Z.getBold(this));
        createplacementtxt.setTypeface(Z.getBold(this));
        createnotitxt3.setTypeface(Z.getBold(this));
        createnotitxt4.setTypeface(Z.getBold(this));
        createnotitxt5.setTypeface(Z.getBold(this));
        createnotitxt6.setTypeface(Z.getBold(this));
        createnotitxt7.setTypeface(Z.getBold(this));
        createnotitxt8.setTypeface(Z.getBold(this));
        createnotitxt9.setTypeface(Z.getBold(this));
        createnotitxt10.setTypeface(Z.getBold(this));
        createnotitxt11.setTypeface(Z.getBold(this));

        createnotinotitxt.setTypeface(Z.getLight(this));
        createplacementplacementtxt.setTypeface(Z.getLight(this));
        createnotinotitxt3.setTypeface(Z.getLight(this));
        createnotinotitxt4.setTypeface(Z.getLight(this));
        createnotinotitxt5.setTypeface(Z.getLight(this));
        createnotinotitxt6.setTypeface(Z.getLight(this));
        createnotinotitxt7.setTypeface(Z.getLight(this));
        createnotinotitxt8.setTypeface(Z.getLight(this));
        createnotinotitxt9.setTypeface(Z.getLight(this));
        createnotinotitxt10.setTypeface(Z.getLight(this));
        createnotinotitxt11.setTypeface(Z.getLight(this));

        RelativeLayout createnotificationrl=(RelativeLayout)findViewById(R.id.createnotificationrl);
        RelativeLayout editnotificationrl=(RelativeLayout)findViewById(R.id.editnotificationrl);

        createnotificationrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(AdminActions.this, CreateNotification.class);
                i1.putExtra("flag", "fromAdminActivity");
                startActivity(i1);
            }
        });
        editnotificationrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActions.this,EditNotification.class));
            }
        });


        RelativeLayout admincontrolsrl2=(RelativeLayout)findViewById(R.id.admincontrolsrl2);
        RelativeLayout editnotificationrl2=(RelativeLayout)findViewById(R.id.editnotificationrl2);

        admincontrolsrl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActions.this,CreatePlacement.class));
            }
        });
        editnotificationrl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActions.this,EditPlacement.class));
            }
        });


        RelativeLayout admincontrolsrl3=(RelativeLayout)findViewById(R.id.admincontrolsrl3);
        RelativeLayout editnotificationrl3=(RelativeLayout)findViewById(R.id.editnotificationrl3);

        admincontrolsrl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActions.this,AddUsersActivity.class));
            }
        });
        editnotificationrl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActions.this, ShowUsers.class));
            }
        });

        RelativeLayout editnotificationrl4=(RelativeLayout)findViewById(R.id.editnotificationrl4);
        RelativeLayout editnotificationrl5=(RelativeLayout)findViewById(R.id.editnotificationrl5);
        RelativeLayout editnotificationrl6=(RelativeLayout)findViewById(R.id.editnotificationrl6);
        RelativeLayout editnotificationrl7=(RelativeLayout)findViewById(R.id.editnotificationrl7);
        RelativeLayout editnotificationrl8=(RelativeLayout)findViewById(R.id.editnotificationrl8);
        RelativeLayout editnotificationrl9=(RelativeLayout)findViewById(R.id.editnotificationrl9);
        RelativeLayout editnotificationrl10=(RelativeLayout)findViewById(R.id.editnotificationrl10);
        RelativeLayout editnotificationrl11=(RelativeLayout)findViewById(R.id.editnotificationrl11);

        editnotificationrl4.setOnClickListener(this);
        editnotificationrl5.setOnClickListener(this);
        editnotificationrl6.setOnClickListener(this);
        editnotificationrl7.setOnClickListener(this);
        editnotificationrl8.setOnClickListener(this);
        editnotificationrl9.setOnClickListener(this);
        editnotificationrl10.setOnClickListener(this);
        editnotificationrl11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActions.this,BroadcastMessage.class));
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
    public void onClick(View view) {
        startActivity(new Intent(this, StayTunedActivity.class));
    }
}
