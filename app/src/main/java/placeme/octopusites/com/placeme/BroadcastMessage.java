package placeme.octopusites.com.placeme;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.support.design.widget.TextInputEditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BroadcastMessage extends AppCompatActivity {

    ImageView facebookicon,twittericon,linkedinicon;
    TextInputLayout notificationinput;
    TextInputEditText notification;
    int toggle1=0,toggle2=0,toggle3=0;
    FloatingActionButton fab;
    final static CharSequence[] items = {"Upload Picture", "Upload Video"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_message);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Broadcast Post");
        ab.setDisplayHomeAsUpEnabled(true);



        Window window = BroadcastMessage.this.getWindow();

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        notificationinput=(TextInputLayout)findViewById(R.id.notificationinput);
        notificationinput.setTypeface(MyConstants.getBold(this));
        notification=(TextInputEditText)findViewById(R.id.notification);
        notification.setTypeface(MyConstants.getBold(this));
        facebookicon=(ImageView)findViewById(R.id.facebookicon);
        twittericon=(ImageView)findViewById(R.id.twittericon);
        linkedinicon=(ImageView)findViewById(R.id.linkedinicon);

        facebookicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle1==0)
                {
                    toggle1=1;
                    facebookicon.setColorFilter(getResources().getColor(R.color.holoblue));
                }
                else
                {
                    toggle1=0;
                    facebookicon.setColorFilter(Color.parseColor("#03353e"));
                }
            }
        });
        twittericon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle2==0)
                {
                    toggle2=1;
                    twittericon.setColorFilter(getResources().getColor(R.color.holoblue));
                }
                else
                {
                    toggle2=0;
                    twittericon.setColorFilter(Color.parseColor("#03353e"));
                }
            }
        });
        linkedinicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle3==0)
                {
                    toggle3=1;
                    linkedinicon.setColorFilter(getResources().getColor(R.color.holoblue));
                }
                else
                {
                    toggle3=0;
                    linkedinicon.setColorFilter(Color.parseColor("#03353e"));
                }
            }
        });



        TextView createpasstxt=(TextView)findViewById(R.id.createpasstxt);
        TextView passsenstxt=(TextView)findViewById(R.id.passsenstxt);
        TextView selecttxt=(TextView)findViewById(R.id.selecttxt);

        createpasstxt.setTypeface(MyConstants.getBold(this));
        passsenstxt.setTypeface(MyConstants.getLight(this));
        selecttxt.setTypeface(MyConstants.getLight(this));

        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new FireMissilesDialogFragment();
                newFragment.show(getSupportFragmentManager(), "missiles");
            }
        });
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
        getMenuInflater().inflate(R.menu.admin_post, menu);
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
                                BroadcastMessage.super.onBackPressed();
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
    public static class FireMissilesDialogFragment extends DialogFragment {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Choose Action").setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    if (which == 0) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);
                    } else if(which==1){
                        Intent intent = new Intent();
                        intent.setType("video/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Video"), 0);
                    }

                }
            });
            return builder.create();
        }
    }
}

