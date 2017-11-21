package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EditUserActivity extends AppCompatActivity {

    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit User");
        ab.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        View trash=(View)findViewById(R.id.trashselectionview);
        View trash2=(View)findViewById(R.id.trash2selectionview);
        View editemailicon=(View)findViewById(R.id.editselectionview);

        trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EditUserActivity.this);

                alertDialogBuilder
                        .setMessage("Are you sure you want to delete this account, by clicking yes this user will be deactivated ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        EditUserActivity.super.onBackPressed();
                                    }
                                })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });

                final AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
                    }
                });

                alertDialog.show();
            }
        });
        trash2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EditUserActivity.this);

                alertDialogBuilder
                        .setMessage("Are you sure you want to delete this account, by clicking yes this user will be deactivated ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        EditUserActivity.super.onBackPressed();
                                    }
                                })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });

                final AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
                    }
                });

                alertDialog.show();
            }
        });
        editemailicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout edituserrl=(RelativeLayout)findViewById(R.id.edituserrl);
                RelativeLayout editemailrl1=(RelativeLayout)findViewById(R.id.editemailrl1);

                edituserrl.setVisibility(View.VISIBLE);
                editemailrl1.setVisibility(View.GONE);

               showmenu();

            }
        });

        TextView createpasstxt=(TextView)findViewById(R.id.createpasstxt);
        TextView passsenstxt=(TextView)findViewById(R.id.passsenstxt);

        TextView asstxt=(TextView)findViewById(R.id.asstxt);
        TextView placementstatustxt=(TextView)findViewById(R.id.placementstatustxt);
        TextView debarstatustxt=(TextView)findViewById(R.id.debarstatustxt);
        TextView rolestatustxt=(TextView)findViewById(R.id.rolestatustxt);

        TextView ass2txt=(TextView)findViewById(R.id.ass2txt);
        TextView passpasstxt=(TextView)findViewById(R.id.passpasstxt);
        TextView placementstatus2txt=(TextView)findViewById(R.id.placementstatus2txt);
        TextView debarstatus2txt=(TextView)findViewById(R.id.debarstatus2txt);
        TextView rolestatus2txt=(TextView)findViewById(R.id.rolestatus2txt);

        Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/cabinsemibold.ttf");
        Typeface custom_font4 = Typeface.createFromAsset(getAssets(),  "fonts/maven.ttf");

        createpasstxt.setTypeface(custom_font3);
        passsenstxt.setTypeface(custom_font4);

        asstxt.setTypeface(custom_font4);
        placementstatustxt.setTypeface(custom_font4);
        debarstatustxt.setTypeface(custom_font4);
        rolestatustxt.setTypeface(custom_font4);
        ass2txt.setTypeface(custom_font4);
        placementstatus2txt.setTypeface(custom_font4);
        debarstatus2txt.setTypeface(custom_font4);
        rolestatus2txt.setTypeface(custom_font4);
        passpasstxt.setTypeface(custom_font4);

    }
    void showmenu()
    {
        EditUserActivity.this.invalidateOptionsMenu();
        Menu m=null;
        flag=1;
        super.onCreateOptionsMenu(m);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:

                Toast.makeText(getBaseContext(),"clciked", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:

                onBackPressed();

                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(flag==1)
            getMenuInflater().inflate(R.menu.admin_edit, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Do you want to discard changes ?")
                .setCancelable(false)
                .setPositiveButton("Discard",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                EditUserActivity.super.onBackPressed();
                            }
                        })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
            }
        });

        alertDialog.show();

    }
}
