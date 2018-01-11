package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.MyProfileWeaknessesModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;

public class MyProfileWeaknesses extends AppCompatActivity {

    int weakcount = 0,editweak=0;
    View addmoreweak;
    String username,role;
    String digest1, digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    View trash1selectionview, trash2selectionview, trash3selectionview, trash4selectionview, trash5selectionview, trash6selectionview, trash7selectionview, trash8selectionview, trash9selectionview, trash10selectionview;
    int edittedFlag = 0;
    ;
    int d = 0;
    StudentData s = new StudentData();
    EditText weak1, weak2, weak3, weak4, weak5, weak6, weak7, weak8, weak9, weak10;
    TextInputLayout weakinput1,weakinput2,weakinput3,weakinput4,weakinput5,weakinput6,weakinput7,weakinput8,weakinput9,weakinput10;
    String sweak1, sweak2, sweak3, sweak4, sweak5, sweak6, sweak7, sweak8, sweak9, sweak10,encobj="";
    String encweak1, encweak2, encweak3, encweak4, encweak5, encweak6, encweak7, encweak8, encweak9, encweak10;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_weaknesses);
        MobileAds.initialize(this, Z.APP_ID);
        mAdView = findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        digest1 = MySharedPreferencesManager.getDigest1(this);
        digest2 = MySharedPreferencesManager.getDigest2(this);
        username=MySharedPreferencesManager.getUsername(this);
        role=MySharedPreferencesManager.getRole(this);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Weaknesses");
        ab.setDisplayHomeAsUpEnabled(true);

        trash1selectionview = (View) findViewById(R.id.trash1selectionview);
        trash2selectionview = (View) findViewById(R.id.trash2selectionview);
        trash3selectionview = (View) findViewById(R.id.trash3selectionview);
        trash4selectionview = (View) findViewById(R.id.trash4selectionview);
        trash5selectionview = (View) findViewById(R.id.trash5selectionview);
        trash6selectionview = (View) findViewById(R.id.trash6selectionview);
        trash7selectionview = (View) findViewById(R.id.trash7selectionview);
        trash8selectionview = (View) findViewById(R.id.trash8selectionview);
        trash9selectionview = (View) findViewById(R.id.trash9selectionview);
        trash10selectionview = (View) findViewById(R.id.trash10selectionview);


        weakinput1= (TextInputLayout) findViewById(R.id.weakinput1);
        weakinput2= (TextInputLayout) findViewById(R.id.weakinput2);
        weakinput3= (TextInputLayout) findViewById(R.id.weakinput3);
        weakinput4= (TextInputLayout) findViewById(R.id.weakinput4);
        weakinput5= (TextInputLayout) findViewById(R.id.weakinput5);
        weakinput6= (TextInputLayout) findViewById(R.id.weakinput6);
        weakinput7= (TextInputLayout) findViewById(R.id.weakinput7);
        weakinput8= (TextInputLayout) findViewById(R.id.weakinput8);
        weakinput9= (TextInputLayout) findViewById(R.id.weakinput9);
        weakinput10= (TextInputLayout) findViewById(R.id.weakinput10);

        weakinput1.setTypeface(Z.getLight(this));
        weakinput2.setTypeface(Z.getLight(this));
        weakinput3.setTypeface(Z.getLight(this));
        weakinput4.setTypeface(Z.getLight(this));
        weakinput5.setTypeface(Z.getLight(this));
        weakinput6.setTypeface(Z.getLight(this));
        weakinput7.setTypeface(Z.getLight(this));
        weakinput8.setTypeface(Z.getLight(this));
        weakinput9.setTypeface(Z.getLight(this));
        weakinput10.setTypeface(Z.getLight(this));


        weak1 = (EditText) findViewById(R.id.weak1);
        weak2 = (EditText) findViewById(R.id.weak2);
        weak3 = (EditText) findViewById(R.id.weak3);
        weak4 = (EditText) findViewById(R.id.weak4);
        weak5 = (EditText) findViewById(R.id.weak5);
        weak6 = (EditText) findViewById(R.id.weak6);
        weak7 = (EditText) findViewById(R.id.weak7);
        weak8 = (EditText) findViewById(R.id.weak8);
        weak9 = (EditText) findViewById(R.id.weak9);
        weak10 = (EditText) findViewById(R.id.weak10);

        weak1.setTypeface(Z.getBold(this));
        weak2.setTypeface(Z.getBold(this));
        weak3.setTypeface(Z.getBold(this));
        weak4.setTypeface(Z.getBold(this));
        weak5.setTypeface(Z.getBold(this));
        weak6.setTypeface(Z.getBold(this));
        weak7.setTypeface(Z.getBold(this));
        weak8.setTypeface(Z.getBold(this));
        weak9.setTypeface(Z.getBold(this));
        weak10.setTypeface(Z.getBold(this));


        TextView addmoreweaktxt = (TextView) findViewById(R.id.addmoreweaktxt);
        addmoreweaktxt.setTypeface(Z.getBold(this));


        trash1selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 1;
                showDeletDialog();
            }
        });
        trash2selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 2;
                showDeletDialog();

            }
        });
        trash3selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 3;
                showDeletDialog();
            }
        });
        trash4selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 4;
                showDeletDialog();
            }
        });
        trash5selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 5;
                showDeletDialog();
            }
        });
        trash6selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 6;
                showDeletDialog();
            }
        });
        trash7selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 7;
                showDeletDialog();
            }
        });
        trash8selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 8;
                showDeletDialog();
            }
        });
        trash9selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 9;
                showDeletDialog();
            }
        });
        trash10selectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = 10;
                showDeletDialog();
            }
        });


        weak1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                weakinput1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        weak2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                weakinput2.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        weak3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                weakinput3.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        weak4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                weakinput4.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        weak5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                weakinput5.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        weak6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                weakinput6.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        weak7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                weakinput7.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        weak8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                weakinput8.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        weak9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                weakinput9.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        weak10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                weakinput10.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        TextView weaktxt = (TextView) findViewById(R.id.weaktxt);
        weaktxt.setTypeface(Z.getBold(this));

        addmoreweak = (View) findViewById(R.id.addmoreweak);
        addmoreweak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editweak=0;
                edittedFlag=1;
                if (weakcount == 0) {
                    if (weak1.getText().toString() != null) {
                        if (!weak1.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.weakline1);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl2);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            weakcount++;
                        } else
                            Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();

                } else if (weakcount == 1) {
                    if (weak2.getText().toString() != null) {
                        if (!weak2.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.weakline2);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl3);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            weakcount++;
                        } else
                            Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();
                } else if (weakcount == 2) {


                    if (weak3.getText().toString() != null) {
                        if (!weak3.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.weakline3);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl4);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            weakcount++;
                        } else
                            Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();

                } else if (weakcount == 3) {
                    if (weak4.getText().toString() != null) {
                        if (!weak4.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.weakline4);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl5);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            weakcount++;
                        } else
                            Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();

                } else if (weakcount == 4) {
                    if (weak5.getText().toString() != null) {
                        if (!weak5.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.weakline5);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl6);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            weakcount++;
                        } else
                            Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();

                } else if (weakcount == 5) {
                    if (weak6.getText().toString() != null) {
                        if (!weak6.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.weakline6);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl7);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            weakcount++;
                        } else
                            Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();

                } else if (weakcount == 6) {
                    if (weak7.getText().toString() != null) {
                        if (!weak7.getText().toString().equals("")) {


                            View v = (View) findViewById(R.id.weakline7);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl8);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            weakcount++;
                        } else
                            Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();

                } else if (weakcount == 7) {

                    if (weak8.getText().toString() != null) {
                        if (!weak8.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.weakline8);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl9);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            weakcount++;

                        } else
                            Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();

                    View v = (View) findViewById(R.id.weakline7);
                    v.setVisibility(View.VISIBLE);

                } else if (weakcount == 8) {

                    if (weak9.getText().toString() != null) {
                        if (!weak9.getText().toString().equals("")) {


                            View v = (View) findViewById(R.id.weakline9);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl10);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            weakcount++;
                            TextView t = (TextView) findViewById(R.id.addmoreweaktxt);
                            ImageView i = (ImageView) findViewById(R.id.addmoreweakimg);
                            addmoreweak.setVisibility(View.GONE);
                            t.setVisibility(View.GONE);
                            i.setVisibility(View.GONE);
                        } else
                            Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please enter empty weaknesses", Toast.LENGTH_SHORT).show();

                    View v = (View) findViewById(R.id.weakline7);
                    v.setVisibility(View.VISIBLE);
                }
            }
        });


        sweak1 = s.getWeak1();
        sweak2 = s.getWeak2();
        sweak3 = s.getWeak3();
        sweak4 = s.getWeak4();
        sweak5 = s.getWeak5();
        sweak6 = s.getWeak6();
        sweak7 = s.getWeak7();
        sweak8 = s.getWeak8();
        sweak9 = s.getWeak9();
        sweak10 = s.getWeak10();

        if (sweak1 != null) {
            if (sweak1.length() > 2) {
                weak1.setText(sweak1);
            }
        }
        if (sweak2 != null) {
            if (sweak2.length() > 2) {
                weak2.setText(sweak2);

                View v = (View) findViewById(R.id.weakline1);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl2);
                relativeLayout1.setVisibility(View.VISIBLE);
                weakcount++;
            }
        }
        if (sweak3 != null) {
            if (sweak3.length() > 2) {
                weak3.setText(sweak3);

                View v = (View) findViewById(R.id.weakline2);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl3);
                relativeLayout1.setVisibility(View.VISIBLE);
                weakcount++;
            }
        }
        if (sweak4 != null) {
            if (sweak4.length() > 2) {
                weak4.setText(sweak4);

                View v = (View) findViewById(R.id.weakline3);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl4);
                relativeLayout1.setVisibility(View.VISIBLE);
                weakcount++;
            }
        }
        if (sweak5 != null) {
            if (sweak5.length() > 2) {
                weak5.setText(sweak5);

                View v = (View) findViewById(R.id.weakline4);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl5);
                relativeLayout1.setVisibility(View.VISIBLE);
                weakcount++;
            }
        }
        if (sweak6 != null) {
            if (sweak6.length() > 2) {
                weak6.setText(sweak6);

                View v = (View) findViewById(R.id.weakline5);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl6);
                relativeLayout1.setVisibility(View.VISIBLE);
                weakcount++;
            }
        }
        if (sweak7 != null) {
            if (sweak7.length() > 2) {
                weak7.setText(sweak7);

                View v = (View) findViewById(R.id.weakline6);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl7);
                relativeLayout1.setVisibility(View.VISIBLE);
                weakcount++;
            }
        }
        if (sweak8 != null) {
            if (sweak8.length() > 2) {
                weak8.setText(sweak8);

                View v = (View) findViewById(R.id.weakline7);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl8);
                relativeLayout1.setVisibility(View.VISIBLE);
                weakcount++;
            }
        }
        if (sweak9 != null) {
            if (sweak9.length() > 2) {
                weak9.setText(sweak9);

                View v = (View) findViewById(R.id.weakline8);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl9);
                relativeLayout1.setVisibility(View.VISIBLE);
                weakcount++;
            }
        }
        if (sweak10 != null) {
            if (sweak10.length() > 2) {
                weak10.setText(sweak10);

                View v = (View) findViewById(R.id.weakline9);
                v.setVisibility(View.VISIBLE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl10);
                relativeLayout1.setVisibility(View.VISIBLE);
                weakcount++;

                TextView t = (TextView) findViewById(R.id.addmoreweaktxt);
                ImageView i = (ImageView) findViewById(R.id.addmoreweakimg);
                addmoreweak.setVisibility(View.GONE);
                t.setVisibility(View.GONE);
                i.setVisibility(View.GONE);
            }
        }

        edittedFlag = 0;

    }

    void showDeletDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Do you want to delete this weakness ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                edittedFlag = 1;
                                deleteWeak();
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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(MyProfileWeaknesses.this));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(MyProfileWeaknesses.this));

            }
        });

        alertDialog.show();

    }

    void deleteWeak() {
        View v = (View) findViewById(R.id.weakline9);
        if (v.getVisibility() == View.VISIBLE) {
            View v1 = (View) findViewById(R.id.weakline9);
            v1.setVisibility(View.GONE);

            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl10);
            relativeLayout1.setVisibility(View.GONE);
            weakcount--;

            TextView t = (TextView) findViewById(R.id.addmoreweaktxt);
            ImageView i = (ImageView) findViewById(R.id.addmoreweakimg);
            addmoreweak.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);


        } else {
            v = (View) findViewById(R.id.weakline8);
            if (v.getVisibility() == View.VISIBLE) {
                View v1 = (View) findViewById(R.id.weakline8);
                v1.setVisibility(View.GONE);

                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl9);
                relativeLayout1.setVisibility(View.GONE);
                weakcount--;

            } else {
                v = (View) findViewById(R.id.weakline7);
                if (v.getVisibility() == View.VISIBLE) {
                    View v1 = (View) findViewById(R.id.weakline7);
                    v1.setVisibility(View.GONE);

                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl8);
                    relativeLayout1.setVisibility(View.GONE);
                    weakcount--;

                } else {
                    v = (View) findViewById(R.id.weakline6);
                    if (v.getVisibility() == View.VISIBLE) {
                        View v1 = (View) findViewById(R.id.weakline6);
                        v1.setVisibility(View.GONE);

                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl7);
                        relativeLayout1.setVisibility(View.GONE);
                        weakcount--;

                    } else {
                        v = (View) findViewById(R.id.weakline5);
                        if (v.getVisibility() == View.VISIBLE) {
                            View v1 = (View) findViewById(R.id.weakline5);
                            v1.setVisibility(View.GONE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl6);
                            relativeLayout1.setVisibility(View.GONE);
                            weakcount--;

                        } else {
                            v = (View) findViewById(R.id.weakline4);
                            if (v.getVisibility() == View.VISIBLE) {
                                View v1 = (View) findViewById(R.id.weakline4);
                                v1.setVisibility(View.GONE);

                                RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl5);
                                relativeLayout1.setVisibility(View.GONE);
                                weakcount--;

                            } else {
                                v = (View) findViewById(R.id.weakline3);
                                if (v.getVisibility() == View.VISIBLE) {
                                    View v1 = (View) findViewById(R.id.weakline3);
                                    v1.setVisibility(View.GONE);

                                    RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl4);
                                    relativeLayout1.setVisibility(View.GONE);
                                    weakcount--;

                                } else {
                                    v = (View) findViewById(R.id.weakline2);
                                    if (v.getVisibility() == View.VISIBLE) {
                                        View v1 = (View) findViewById(R.id.weakline2);
                                        v1.setVisibility(View.GONE);

                                        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl3);
                                        relativeLayout1.setVisibility(View.GONE);
                                        weakcount--;

                                    } else {
                                        v = (View) findViewById(R.id.weakline1);
                                        if (v.getVisibility() == View.VISIBLE) {
                                            View v1 = (View) findViewById(R.id.weakline1);
                                            v1.setVisibility(View.GONE);

                                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl2);
                                            relativeLayout1.setVisibility(View.GONE);
                                            weakcount--;

                                        } else {
                                            weak1.setText("");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (d == 10) {
            weak10.setText("");
        } else if (d == 9) {
            sweak10 = weak10.getText().toString();


                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");

        } else if (d == 8) {
            sweak10 = weak10.getText().toString();
            sweak9 = weak9.getText().toString();


                sweak8 = sweak9;
                weak8.setText(sweak8);
                weak9.setText("");

                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");

        } else if (d == 7) {
            sweak10 = weak10.getText().toString();
            sweak9 = weak9.getText().toString();
            sweak8 = weak8.getText().toString();


                sweak7 = sweak8;
                weak7.setText(sweak7);
                weak8.setText("");

                sweak8 = sweak9;
                weak8.setText(sweak8);
                weak9.setText("");

                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");

        } else if (d == 6) {
            sweak10 = weak10.getText().toString();
            sweak9 = weak9.getText().toString();
            sweak8 = weak8.getText().toString();
            sweak7 = weak7.getText().toString();


                sweak6 = sweak7;
                weak6.setText(sweak6);
                weak7.setText("");

                sweak7 = sweak8;
                weak7.setText(sweak7);
                weak8.setText("");

                sweak8 = sweak9;
                weak8.setText(sweak8);
                weak9.setText("");

                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");

        } else if (d == 5) {
            sweak10 = weak10.getText().toString();
            sweak9 = weak9.getText().toString();
            sweak8 = weak8.getText().toString();
            sweak7 = weak7.getText().toString();
            sweak6 = weak6.getText().toString();


                sweak5 = sweak6;
                weak5.setText(sweak5);
                weak6.setText("");

                sweak6 = sweak7;
                weak6.setText(sweak6);
                weak7.setText("");

                sweak7 = sweak8;
                weak7.setText(sweak7);
                weak8.setText("");

                sweak8 = sweak9;
                weak8.setText(sweak8);
                weak9.setText("");

                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");

        } else if (d == 4) {
            sweak10 = weak10.getText().toString();
            sweak9 = weak9.getText().toString();
            sweak8 = weak8.getText().toString();
            sweak7 = weak7.getText().toString();
            sweak6 = weak6.getText().toString();
            sweak5 = weak5.getText().toString();


                sweak4 = sweak5;
                weak4.setText(sweak4);
                weak5.setText("");

                sweak5 = sweak6;
                weak5.setText(sweak5);
                weak6.setText("");

                sweak6 = sweak7;
                weak6.setText(sweak6);
                weak7.setText("");

                sweak7 = sweak8;
                weak7.setText(sweak7);
                weak8.setText("");

                sweak8 = sweak9;
                weak8.setText(sweak8);
                weak9.setText("");

                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");

        } else if (d == 3) {
            sweak10 = weak10.getText().toString();
            sweak9 = weak9.getText().toString();
            sweak8 = weak8.getText().toString();
            sweak7 = weak7.getText().toString();
            sweak6 = weak6.getText().toString();
            sweak5 = weak5.getText().toString();
            sweak4 = weak4.getText().toString();


                sweak3 = sweak4;
                weak3.setText(sweak3);
                weak4.setText("");

                sweak4 = sweak5;
                weak4.setText(sweak4);
                weak5.setText("");

                sweak5 = sweak6;
                weak5.setText(sweak5);
                weak6.setText("");

                sweak6 = sweak7;
                weak6.setText(sweak6);
                weak7.setText("");

                sweak7 = sweak8;
                weak7.setText(sweak7);
                weak8.setText("");

                sweak8 = sweak9;
                weak8.setText(sweak8);
                weak9.setText("");

                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");

        } else if (d == 2) {
            sweak10 = weak10.getText().toString();
            sweak9 = weak9.getText().toString();
            sweak8 = weak8.getText().toString();
            sweak7 = weak7.getText().toString();
            sweak6 = weak6.getText().toString();
            sweak5 = weak5.getText().toString();
            sweak4 = weak4.getText().toString();
            sweak3 = weak3.getText().toString();


                sweak2 = sweak3;
                weak2.setText(sweak2);
                weak3.setText("");

                sweak3 = sweak4;
                weak3.setText(sweak3);
                weak4.setText("");

                sweak4 = sweak5;
                weak4.setText(sweak4);
                weak5.setText("");

                sweak5 = sweak6;
                weak5.setText(sweak5);
                weak6.setText("");

                sweak6 = sweak7;
                weak6.setText(sweak6);
                weak7.setText("");

                sweak7 = sweak8;
                weak7.setText(sweak7);
                weak8.setText("");

                sweak8 = sweak9;
                weak8.setText(sweak8);
                weak9.setText("");

                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");

        } else if (d == 1) {
            sweak10 = weak10.getText().toString();
            sweak9 = weak9.getText().toString();
            sweak8 = weak8.getText().toString();
            sweak7 = weak7.getText().toString();
            sweak6 = weak6.getText().toString();
            sweak5 = weak5.getText().toString();
            sweak4 = weak4.getText().toString();
            sweak3 = weak3.getText().toString();
            sweak2 = weak2.getText().toString();


                sweak1 = sweak2;
                weak1.setText(sweak1);
                weak2.setText("");

                sweak2 = sweak3;
                weak2.setText(sweak2);
                weak3.setText("");

                sweak3 = sweak4;
                weak3.setText(sweak3);
                weak4.setText("");

                sweak4 = sweak5;
                weak4.setText(sweak4);
                weak5.setText("");

                sweak5 = sweak6;
                weak5.setText(sweak5);
                weak6.setText("");

                sweak6 = sweak7;
                weak6.setText(sweak6);
                weak7.setText("");

                sweak7 = sweak8;
                weak7.setText(sweak7);
                weak8.setText("");

                sweak8 = sweak9;
                weak8.setText(sweak8);
                weak9.setText("");

                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");

            sweak1 = weak1.getText().toString();

            if(sweak1.equals("")){
                editweak=1;
            }

            if(editweak==1){
                encweak();
            }

        }
    }

    void validateandSave() {
//

        sweak1 = weak1.getText().toString();
        sweak2 = weak2.getText().toString();
        sweak3 = weak3.getText().toString();
        sweak4 = weak4.getText().toString();
        sweak5 = weak5.getText().toString();
        sweak6 = weak6.getText().toString();
        sweak7 = weak7.getText().toString();
        sweak8 = weak8.getText().toString();
        sweak9 = weak9.getText().toString();
        sweak10 = weak10.getText().toString();

//

        int errorflag = 0;
        if(editweak==1){
            encweak();
        }
        else {
        if (weakcount == 0) {
            if (sweak1.length() < 2) {
                errorflag = 1;
                weakinput1.setError("Kindly enter valid weakness.");
            }
        } else if (weakcount == 1) {
            if (sweak1.length() < 2) {
                errorflag = 1;
                weakinput1.setError("Kindly enter valid weakness.");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weakinput2.setError("Kindly enter valid weakness.");
                }
            }
        } else if (weakcount == 2) {
            if (sweak1.length() < 2) {
                errorflag = 1;
                weakinput1.setError("Kindly enter valid weakness.");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weakinput2.setError("Kindly enter valid weakness.");
                } else {
                    errorflag = 0;
                    if (sweak3.length() < 2) {
                        errorflag = 1;
                        weakinput3.setError("Kindly enter valid weakness.");
                    }
                }
            }
        } else if (weakcount == 3) {
            if (sweak1.length() < 2) {
                errorflag = 1;
                weakinput1.setError("Kindly enter valid weakness.");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weakinput2.setError("Kindly enter valid weakness.");
                } else {
                    errorflag = 0;
                    if (sweak3.length() < 2) {
                        errorflag = 1;
                        weakinput3.setError("Kindly enter valid weakness.");
                    } else {
                        errorflag = 0;
                        if (sweak4.length() < 2) {
                            errorflag = 1;
                            weakinput4.setError("Kindly enter valid weakness.");
                        }
                    }
                }
            }
        } else if (weakcount == 4) {
            if (sweak1.length() < 2) {
                errorflag = 1;
                weakinput1.setError("Kindly enter valid weakness.");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weakinput2.setError("Kindly enter valid weakness.");
                } else {
                    errorflag = 0;
                    if (sweak3.length() < 2) {
                        errorflag = 1;
                        weakinput3.setError("Kindly enter valid weakness.");
                    } else {
                        errorflag = 0;
                        if (sweak4.length() < 2) {
                            errorflag = 1;
                            weakinput4.setError("Kindly enter valid weakness.");
                        } else {
                            errorflag = 0;
                            if (sweak5.length() < 2) {
                                errorflag = 1;
                                weakinput5.setError("Kindly enter valid weakness.");
                            }
                        }
                    }
                }
            }
        } else if (weakcount == 5) {
            if (sweak1.length() < 2) {
                errorflag = 1;
                weakinput1.setError("Kindly enter valid weakness.");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weakinput2.setError("Kindly enter valid weakness.");
                } else {
                    errorflag = 0;
                    if (sweak3.length() < 2) {
                        errorflag = 1;
                        weakinput3.setError("Kindly enter valid weakness.");
                    } else {
                        errorflag = 0;
                        if (sweak4.length() < 2) {
                            errorflag = 1;
                            weakinput4.setError("Kindly enter valid weakness.");
                        } else {
                            errorflag = 0;
                            if (sweak5.length() < 2) {
                                errorflag = 1;
                                weakinput5.setError("Kindly enter valid weakness.");
                            } else {
                                errorflag = 0;
                                if (sweak6.length() < 2) {
                                    errorflag = 1;
                                    weakinput6.setError("Kindly enter valid weakness.");
                                }
                            }
                        }
                    }
                }
            }
        } else if (weakcount == 6) {
            if (sweak1.length() < 2) {
                errorflag = 1;
                weakinput1.setError("Kindly enter valid weakness.");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weakinput2.setError("Kindly enter valid weakness.");
                } else {
                    errorflag = 0;
                    if (sweak3.length() < 2) {
                        errorflag = 1;
                        weakinput3.setError("Kindly enter valid weakness.");
                    } else {
                        errorflag = 0;
                        if (sweak4.length() < 2) {
                            errorflag = 1;
                            weakinput4.setError("Kindly enter valid weakness.");
                        } else {
                            errorflag = 0;
                            if (sweak5.length() < 2) {
                                errorflag = 1;
                                weakinput5.setError("Kindly enter valid weakness.");
                            } else {
                                errorflag = 0;
                                if (sweak6.length() < 2) {
                                    errorflag = 1;
                                    weakinput6.setError("Kindly enter valid weakness.");
                                } else {
                                    errorflag = 0;
                                    if (sweak7.length() < 2) {
                                        errorflag = 1;
                                        weakinput7.setError("Kindly enter valid weakness.");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (weakcount == 7) {
            if (sweak1.length() < 2) {
                errorflag = 1;
                weakinput1.setError("Kindly enter valid weakness.");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weakinput2.setError("Kindly enter valid weakness.");
                } else {
                    errorflag = 0;
                    if (sweak3.length() < 2) {
                        errorflag = 1;
                        weakinput3.setError("Kindly enter valid weakness.");
                    } else {
                        errorflag = 0;
                        if (sweak4.length() < 2) {
                            errorflag = 1;
                            weakinput4.setError("Kindly enter valid weakness.");
                        } else {
                            errorflag = 0;
                            if (sweak5.length() < 2) {
                                errorflag = 1;
                                weakinput5.setError("Kindly enter valid weakness.");
                            } else {
                                errorflag = 0;
                                if (sweak6.length() < 2) {
                                    errorflag = 1;
                                    weakinput6.setError("Kindly enter valid weakness.");
                                } else {
                                    errorflag = 0;
                                    if (sweak7.length() < 2) {
                                        errorflag = 1;
                                        weakinput7.setError("Kindly enter valid weakness.");
                                    } else {
                                        errorflag = 0;
                                        if (sweak8.length() < 2) {
                                            errorflag = 1;
                                            weakinput8.setError("Kindly enter valid weakness.");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (weakcount == 8) {
            if (sweak1.length() < 2) {
                errorflag = 1;
                weakinput1.setError("Kindly enter valid weakness.");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weakinput2.setError("Kindly enter valid weakness.");
                } else {
                    errorflag = 0;
                    if (sweak3.length() < 2) {
                        errorflag = 1;
                        weakinput3.setError("Kindly enter valid weakness.");
                    } else {
                        errorflag = 0;
                        if (sweak4.length() < 2) {
                            errorflag = 1;
                            weakinput4.setError("Kindly enter valid weakness.");
                        } else {
                            errorflag = 0;
                            if (sweak5.length() < 2) {
                                errorflag = 1;
                                weakinput5.setError("Kindly enter valid weakness.");
                            } else {
                                errorflag = 0;
                                if (sweak6.length() < 2) {
                                    errorflag = 1;
                                    weakinput6.setError("Kindly enter valid weakness.");
                                } else {
                                    errorflag = 0;
                                    if (sweak7.length() < 2) {
                                        errorflag = 1;
                                        weakinput7.setError("Kindly enter valid weakness.");
                                    } else {
                                        errorflag = 0;
                                        if (sweak8.length() < 2) {
                                            errorflag = 1;
                                            weakinput8.setError("Kindly enter valid weakness.");
                                        } else {
                                            errorflag = 0;
                                            if (sweak9.length() < 2) {
                                                errorflag = 1;
                                                weakinput9.setError("Kindly enter valid weakness.");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (weakcount == 9) {
            if (sweak1.length() < 2) {
                errorflag = 1;
                weakinput1.setError("Kindly enter valid weakness.");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weakinput2.setError("Kindly enter valid weakness.");
                } else {
                    errorflag = 0;
                    if (sweak3.length() < 2) {
                        errorflag = 1;
                        weakinput3.setError("Kindly enter valid weakness.");
                    } else {
                        errorflag = 0;
                        if (sweak4.length() < 2) {
                            errorflag = 1;
                            weakinput4.setError("Kindly enter valid weakness.");
                        } else {
                            errorflag = 0;
                            if (sweak5.length() < 2) {
                                errorflag = 1;
                                weakinput5.setError("Kindly enter valid weakness.");
                            } else {
                                errorflag = 0;
                                if (sweak6.length() < 2) {
                                    errorflag = 1;
                                    weakinput6.setError("Kindly enter valid weakness.");
                                } else {
                                    errorflag = 0;
                                    if (sweak7.length() < 2) {
                                        errorflag = 1;
                                        weakinput7.setError("Kindly enter valid weakness.");
                                    } else {
                                        errorflag = 0;
                                        if (sweak8.length() < 2) {
                                            errorflag = 1;
                                            weakinput8.setError("Kindly enter valid weakness.");
                                        } else {
                                            errorflag = 0;
                                            if (sweak9.length() < 2) {
                                                errorflag = 1;
                                                weakinput9.setError("Kindly enter valid weakness.");
                                            } else {
                                                errorflag = 0;
                                                if (sweak10.length() < 2) {
                                                    errorflag = 1;
                                                    weakinput10.setError("Kindly enter valid weakness.");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        }
        if (errorflag == 0) {
            encweak();
        }
    }

    public void encweak(){
        try {

            MyProfileWeaknessesModal obj2 = new MyProfileWeaknessesModal(sweak1, sweak2, sweak3, sweak4, sweak5, sweak6, sweak7, sweak8, sweak9, sweak10);;

            encobj =OtoString(obj2,MySharedPreferencesManager.getDigest1(MyProfileWeaknesses.this),MySharedPreferencesManager.getDigest2(MyProfileWeaknesses.this));
            new SaveWeak().execute();

        } catch (Exception e) {
        }
    }

    class SaveWeak extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("w1", encobj));      //1
            json = jParser.makeHttpRequest(Z.url_saveweaknesses, "GET", params);
            try {
                r = json.getString("info");


            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("success")) {
                Toast.makeText(MyProfileWeaknesses.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();

                if (role.equals("student"))
                    setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);
                else if (role.equals("alumni"))
                    setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);


                s.setWeak1(sweak1);
                s.setWeak2(sweak2);
                s.setWeak3(sweak3);
                s.setWeak4(sweak4);
                s.setWeak5(sweak5);
                s.setWeak6(sweak6);
                s.setWeak7(sweak7);
                s.setWeak8(sweak8);
                s.setWeak9(sweak9);
                s.setWeak10(sweak10);

                MyProfileWeaknesses.super.onBackPressed();
            } else
                Toast.makeText(MyProfileWeaknesses.this, "Try again !", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:
                if (edittedFlag == 1) {
                    validateandSave();
                }
                else {
                    onBackPressed();
                }
                break;

            case android.R.id.home:

                onBackPressed();

                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.savemenu, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public void onBackPressed() {

        if (edittedFlag != 0) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    MyProfileWeaknesses.super.onBackPressed();
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
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(MyProfileWeaknesses.this));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(MyProfileWeaknesses.this));

                }
            });

            alertDialog.show();
        } else
            MyProfileWeaknesses.super.onBackPressed();
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
