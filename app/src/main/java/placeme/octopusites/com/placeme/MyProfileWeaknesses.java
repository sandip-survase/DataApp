package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.MyProfileWeaknessesModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;

public class MyProfileWeaknesses extends AppCompatActivity {

    int weakcount = 0;
    View addmoreweak;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    String username;
    String digest1, digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    //    private static String url_saveweaknesses= "http://192.168.100.100/AESTest/SaveWeaknesses";
    View trash1selectionview, trash2selectionview, trash3selectionview, trash4selectionview, trash5selectionview, trash6selectionview, trash7selectionview, trash8selectionview, trash9selectionview, trash10selectionview;
    int edittedFlag = 0;
    ;
    int d = 0;
    StudentData s = new StudentData();
    EditText weak1, weak2, weak3, weak4, weak5, weak6, weak7, weak8, weak9, weak10;
    String sweak1, sweak2, sweak3, sweak4, sweak5, sweak6, sweak7, sweak8, sweak9, sweak10,encobj="";
    String encweak1, encweak2, encweak3, encweak4, encweak5, encweak6, encweak7, encweak8, encweak9, encweak10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_weaknesses);

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

        weak1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                weak1.setError(null);
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
                weak2.setError(null);
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
                weak3.setError(null);
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
                weak4.setError(null);
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
                weak5.setError(null);
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
                weak6.setError(null);
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
                weak7.setError(null);
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
                weak8.setError(null);
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
                weak9.setError(null);
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
                weak10.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        TextView weaktxt = (TextView) findViewById(R.id.weaktxt);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/arba.ttf");
        weaktxt.setTypeface(custom_font1);

        addmoreweak = (View) findViewById(R.id.addmoreweak);
        addmoreweak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (weakcount == 0) {
                    if (weak1.getText().toString() != null) {
                        if (!weak1.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.weakline1);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl2);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            weakcount++;
                        } else
                            Toast.makeText(MyProfileWeaknesses.this, "Please Enter first Weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please Enter first Weaknesses", Toast.LENGTH_SHORT).show();

                } else if (weakcount == 1) {

                    if (weak2.getText().toString() != null) {
                        if (!weak2.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.weakline2);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl3);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            weakcount++;
                        } else
                            Toast.makeText(MyProfileWeaknesses.this, "Please Enter second Weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please Enter secound Weaknesses", Toast.LENGTH_SHORT).show();
                } else if (weakcount == 2) {


                    if (weak3.getText().toString() != null) {
                        if (!weak3.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.weakline3);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl4);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            weakcount++;
                        } else
                            Toast.makeText(MyProfileWeaknesses.this, "Please Enter Third Weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please Enter Third Weaknesses", Toast.LENGTH_SHORT).show();

                } else if (weakcount == 3) {
                    if (weak4.getText().toString() != null) {
                        if (!weak4.getText().toString().equals("")) {
                            View v = (View) findViewById(R.id.weakline4);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl5);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            weakcount++;
                        } else
                            Toast.makeText(MyProfileWeaknesses.this, "Please Enter Fourth Weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please Enter Fourth Weaknesses", Toast.LENGTH_SHORT).show();

                } else if (weakcount == 4) {
                    if (weak5.getText().toString() != null) {
                        if (!weak5.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.weakline5);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl6);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            weakcount++;
                        } else
                            Toast.makeText(MyProfileWeaknesses.this, "Please Enter Fifth Weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please Enter Fifth Weaknesses", Toast.LENGTH_SHORT).show();

                } else if (weakcount == 5) {
                    if (weak6.getText().toString() != null) {
                        if (!weak6.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.weakline6);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl7);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            weakcount++;
                        } else
                            Toast.makeText(MyProfileWeaknesses.this, "Please Enter Sixth Weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please Enter Sixth Weaknesses", Toast.LENGTH_SHORT).show();

                } else if (weakcount == 6) {
                    if (weak7.getText().toString() != null) {
                        if (!weak7.getText().toString().equals("")) {


                            View v = (View) findViewById(R.id.weakline7);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl8);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            weakcount++;
                        } else
                            Toast.makeText(MyProfileWeaknesses.this, "Please Enter Seventh Weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please Enter Seventh Weaknesses", Toast.LENGTH_SHORT).show();

                } else if (weakcount == 7) {

                    if (weak8.getText().toString() != null) {
                        if (!weak8.getText().toString().equals("")) {

                            View v = (View) findViewById(R.id.weakline8);
                            v.setVisibility(View.VISIBLE);

                            RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.weakrl9);
                            relativeLayout1.setVisibility(View.VISIBLE);
                            weakcount++;

                        } else
                            Toast.makeText(MyProfileWeaknesses.this, "Please Enter Eighth Weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please Enter Eighth Weaknesses", Toast.LENGTH_SHORT).show();

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
                            Toast.makeText(MyProfileWeaknesses.this, "Please Enter Nineth Weaknesses", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyProfileWeaknesses.this, "Please Enter Nineth Weaknesses", Toast.LENGTH_SHORT).show();

                    View v = (View) findViewById(R.id.weakline7);
                    v.setVisibility(View.VISIBLE);
                }
            }
        });

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username = sharedpreferences.getString(Username, null);
        String role = sharedpreferences.getString("role", null);

        ProfileRole r = new ProfileRole();
        r.setUsername(username);
        r.setRole(role);

        Digest d = new Digest();
        digest1 = d.getDigest1();
        digest2 = d.getDigest2();

        if (digest1 == null || digest2 == null) {
            digest1 = sharedpreferences.getString("digest1", null);
            digest2 = sharedpreferences.getString("digest2", null);
            d.setDigest1(digest1);
            d.setDigest2(digest2);
        }

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
                .setMessage("Do you want to delete this patent ?")
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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
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

            if (sweak10.length() > 2) {
                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");
            }
        } else if (d == 8) {
            sweak10 = weak10.getText().toString();
            sweak9 = weak9.getText().toString();

            if (sweak9.length() > 2) {
                sweak8 = sweak9;
                weak8.setText(sweak8);
                weak9.setText("");
            }
            if (sweak10.length() > 2) {
                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");
            }
        } else if (d == 7) {
            sweak10 = weak10.getText().toString();
            sweak9 = weak9.getText().toString();
            sweak8 = weak8.getText().toString();

            if (sweak8.length() > 2) {
                sweak7 = sweak8;
                weak7.setText(sweak7);
                weak8.setText("");
            }
            if (sweak9.length() > 2) {
                sweak8 = sweak9;
                weak8.setText(sweak8);
                weak9.setText("");
            }
            if (sweak10.length() > 2) {
                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");
            }
        } else if (d == 6) {
            sweak10 = weak10.getText().toString();
            sweak9 = weak9.getText().toString();
            sweak8 = weak8.getText().toString();
            sweak7 = weak7.getText().toString();

            if (sweak7.length() > 2) {
                sweak6 = sweak7;
                weak6.setText(sweak6);
                weak7.setText("");
            }
            if (sweak8.length() > 2) {
                sweak7 = sweak8;
                weak7.setText(sweak7);
                weak8.setText("");
            }
            if (sweak9.length() > 2) {
                sweak8 = sweak9;
                weak8.setText(sweak8);
                weak9.setText("");
            }
            if (sweak10.length() > 2) {
                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");
            }
        } else if (d == 5) {
            sweak10 = weak10.getText().toString();
            sweak9 = weak9.getText().toString();
            sweak8 = weak8.getText().toString();
            sweak7 = weak7.getText().toString();
            sweak6 = weak6.getText().toString();

            if (sweak6.length() > 2) {
                sweak5 = sweak6;
                weak5.setText(sweak5);
                weak6.setText("");
            }
            if (sweak7.length() > 2) {
                sweak6 = sweak7;
                weak6.setText(sweak6);
                weak7.setText("");
            }
            if (sweak8.length() > 2) {
                sweak7 = sweak8;
                weak7.setText(sweak7);
                weak8.setText("");
            }
            if (sweak9.length() > 2) {
                sweak8 = sweak9;
                weak8.setText(sweak8);
                weak9.setText("");
            }
            if (sweak10.length() > 2) {
                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");
            }
        } else if (d == 4) {
            sweak10 = weak10.getText().toString();
            sweak9 = weak9.getText().toString();
            sweak8 = weak8.getText().toString();
            sweak7 = weak7.getText().toString();
            sweak6 = weak6.getText().toString();
            sweak5 = weak5.getText().toString();

            if (sweak5.length() > 2) {
                sweak4 = sweak5;
                weak4.setText(sweak4);
                weak5.setText("");
            }
            if (sweak6.length() > 2) {
                sweak5 = sweak6;
                weak5.setText(sweak5);
                weak6.setText("");
            }
            if (sweak7.length() > 2) {
                sweak6 = sweak7;
                weak6.setText(sweak6);
                weak7.setText("");
            }
            if (sweak8.length() > 2) {
                sweak7 = sweak8;
                weak7.setText(sweak7);
                weak8.setText("");
            }
            if (sweak9.length() > 2) {
                sweak8 = sweak9;
                weak8.setText(sweak8);
                weak9.setText("");
            }
            if (sweak10.length() > 2) {
                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");
            }
        } else if (d == 3) {
            sweak10 = weak10.getText().toString();
            sweak9 = weak9.getText().toString();
            sweak8 = weak8.getText().toString();
            sweak7 = weak7.getText().toString();
            sweak6 = weak6.getText().toString();
            sweak5 = weak5.getText().toString();
            sweak4 = weak4.getText().toString();

            if (sweak4.length() > 2) {
                sweak3 = sweak4;
                weak3.setText(sweak3);
                weak4.setText("");
            }
            if (sweak5.length() > 2) {
                sweak4 = sweak5;
                weak4.setText(sweak4);
                weak5.setText("");
            }
            if (sweak6.length() > 2) {
                sweak5 = sweak6;
                weak5.setText(sweak5);
                weak6.setText("");
            }
            if (sweak7.length() > 2) {
                sweak6 = sweak7;
                weak6.setText(sweak6);
                weak7.setText("");
            }
            if (sweak8.length() > 2) {
                sweak7 = sweak8;
                weak7.setText(sweak7);
                weak8.setText("");
            }
            if (sweak9.length() > 2) {
                sweak8 = sweak9;
                weak8.setText(sweak8);
                weak9.setText("");
            }
            if (sweak10.length() > 2) {
                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");
            }
        } else if (d == 2) {
            sweak10 = weak10.getText().toString();
            sweak9 = weak9.getText().toString();
            sweak8 = weak8.getText().toString();
            sweak7 = weak7.getText().toString();
            sweak6 = weak6.getText().toString();
            sweak5 = weak5.getText().toString();
            sweak4 = weak4.getText().toString();
            sweak3 = weak3.getText().toString();

            if (sweak3.length() > 2) {
                sweak2 = sweak3;
                weak2.setText(sweak2);
                weak3.setText("");
            }
            if (sweak4.length() > 2) {
                sweak3 = sweak4;
                weak3.setText(sweak3);
                weak4.setText("");
            }
            if (sweak5.length() > 2) {
                sweak4 = sweak5;
                weak4.setText(sweak4);
                weak5.setText("");
            }
            if (sweak6.length() > 2) {
                sweak5 = sweak6;
                weak5.setText(sweak5);
                weak6.setText("");
            }
            if (sweak7.length() > 2) {
                sweak6 = sweak7;
                weak6.setText(sweak6);
                weak7.setText("");
            }
            if (sweak8.length() > 2) {
                sweak7 = sweak8;
                weak7.setText(sweak7);
                weak8.setText("");
            }
            if (sweak9.length() > 2) {
                sweak8 = sweak9;
                weak8.setText(sweak8);
                weak9.setText("");
            }
            if (sweak10.length() > 2) {
                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");
            }
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

            if (sweak2.length() > 1) {
                sweak1 = sweak2;
                weak1.setText(sweak1);
                weak2.setText("");
            }
            if (sweak3.length() > 2) {
                sweak2 = sweak3;
                weak2.setText(sweak2);
                weak3.setText("");
            }
            if (sweak4.length() > 2) {
                sweak3 = sweak4;
                weak3.setText(sweak3);
                weak4.setText("");
            }
            if (sweak5.length() > 2) {
                sweak4 = sweak5;
                weak4.setText(sweak4);
                weak5.setText("");
            }
            if (sweak6.length() > 2) {
                sweak5 = sweak6;
                weak5.setText(sweak5);
                weak6.setText("");
            }
            if (sweak7.length() > 2) {
                sweak6 = sweak7;
                weak6.setText(sweak6);
                weak7.setText("");
            }
            if (sweak8.length() > 2) {
                sweak7 = sweak8;
                weak7.setText(sweak7);
                weak8.setText("");
            }
            if (sweak9.length() > 2) {
                sweak8 = sweak9;
                weak8.setText(sweak8);
                weak9.setText("");
            }
            if (sweak10.length() > 2) {
                sweak9 = sweak10;
                weak9.setText(sweak9);
                weak10.setText("");
            }
        }
    }

    void validateandSave() {
        weak1.setError(null);
        weak2.setError(null);
        weak3.setError(null);
        weak4.setError(null);
        weak5.setError(null);
        weak6.setError(null);
        weak7.setError(null);
        weak8.setError(null);
        weak9.setError(null);
        weak10.setError(null);

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

        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";

        int errorflag = 0;

        if (weakcount == 0) {
            if (sweak1.length() < 2) {
                errorflag = 1;
                weak1.setError("Invalid weak");
            }
        } else if (weakcount == 1) {
            if (sweak1.length() < 2) {
                errorflag = 1;
                weak1.setError("Invalid weak");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weak2.setError("Invalid weak");
                }
            }
        } else if (weakcount == 2) {
            if (sweak1.length() < 2) {
                errorflag = 1;
                weak1.setError("Invalid weak");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weak2.setError("Invalid weak");
                } else {
                    errorflag = 0;
                    if (sweak3.length() < 2) {
                        errorflag = 1;
                        weak3.setError("Invalid weak");
                    }
                }
            }
        } else if (weakcount == 3) {
            if (sweak1.length() < 2) {
                errorflag = 1;
                weak1.setError("Invalid weak");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weak2.setError("Invalid weak");
                } else {
                    errorflag = 0;
                    if (sweak3.length() < 2) {
                        errorflag = 1;
                        weak3.setError("Invalid weak");
                    } else {
                        errorflag = 0;
                        if (sweak4.length() < 2) {
                            errorflag = 1;
                            weak4.setError("Invalid weak");
                        }
                    }
                }
            }
        } else if (weakcount == 4) {
            if (sweak1.length() < 2) {
                errorflag = 1;
                weak1.setError("Invalid weak");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weak2.setError("Invalid weak");
                } else {
                    errorflag = 0;
                    if (sweak3.length() < 2) {
                        errorflag = 1;
                        weak3.setError("Invalid weak");
                    } else {
                        errorflag = 0;
                        if (sweak4.length() < 2) {
                            errorflag = 1;
                            weak4.setError("Invalid weak");
                        } else {
                            errorflag = 0;
                            if (sweak5.length() < 2) {
                                errorflag = 1;
                                weak5.setError("Invalid weak");
                            }
                        }
                    }
                }
            }
        } else if (weakcount == 5) {
            if (sweak1.length() < 2) {
                errorflag = 1;
                weak1.setError("Invalid weak");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weak2.setError("Invalid weak");
                } else {
                    errorflag = 0;
                    if (sweak3.length() < 2) {
                        errorflag = 1;
                        weak3.setError("Invalid weak");
                    } else {
                        errorflag = 0;
                        if (sweak4.length() < 2) {
                            errorflag = 1;
                            weak4.setError("Invalid weak");
                        } else {
                            errorflag = 0;
                            if (sweak5.length() < 2) {
                                errorflag = 1;
                                weak5.setError("Invalid weak");
                            } else {
                                errorflag = 0;
                                if (sweak6.length() < 2) {
                                    errorflag = 1;
                                    weak6.setError("Invalid weak");
                                }
                            }
                        }
                    }
                }
            }
        } else if (weakcount == 6) {
            if (sweak1.length() < 2) {
                errorflag = 1;
                weak1.setError("Invalid weak");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weak2.setError("Invalid weak");
                } else {
                    errorflag = 0;
                    if (sweak3.length() < 2) {
                        errorflag = 1;
                        weak3.setError("Invalid weak");
                    } else {
                        errorflag = 0;
                        if (sweak4.length() < 2) {
                            errorflag = 1;
                            weak4.setError("Invalid weak");
                        } else {
                            errorflag = 0;
                            if (sweak5.length() < 2) {
                                errorflag = 1;
                                weak5.setError("Invalid weak");
                            } else {
                                errorflag = 0;
                                if (sweak6.length() < 2) {
                                    errorflag = 1;
                                    weak6.setError("Invalid weak");
                                } else {
                                    errorflag = 0;
                                    if (sweak7.length() < 2) {
                                        errorflag = 1;
                                        weak7.setError("Invalid weak");
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
                weak1.setError("Invalid weak");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weak2.setError("Invalid weak");
                } else {
                    errorflag = 0;
                    if (sweak3.length() < 2) {
                        errorflag = 1;
                        weak3.setError("Invalid weak");
                    } else {
                        errorflag = 0;
                        if (sweak4.length() < 2) {
                            errorflag = 1;
                            weak4.setError("Invalid weak");
                        } else {
                            errorflag = 0;
                            if (sweak5.length() < 2) {
                                errorflag = 1;
                                weak5.setError("Invalid weak");
                            } else {
                                errorflag = 0;
                                if (sweak6.length() < 2) {
                                    errorflag = 1;
                                    weak6.setError("Invalid weak");
                                } else {
                                    errorflag = 0;
                                    if (sweak7.length() < 2) {
                                        errorflag = 1;
                                        weak7.setError("Invalid weak");
                                    } else {
                                        errorflag = 0;
                                        if (sweak8.length() < 2) {
                                            errorflag = 1;
                                            weak8.setError("Invalid weak");
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
                weak1.setError("Invalid weak");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weak2.setError("Invalid weak");
                } else {
                    errorflag = 0;
                    if (sweak3.length() < 2) {
                        errorflag = 1;
                        weak3.setError("Invalid weak");
                    } else {
                        errorflag = 0;
                        if (sweak4.length() < 2) {
                            errorflag = 1;
                            weak4.setError("Invalid weak");
                        } else {
                            errorflag = 0;
                            if (sweak5.length() < 2) {
                                errorflag = 1;
                                weak5.setError("Invalid weak");
                            } else {
                                errorflag = 0;
                                if (sweak6.length() < 2) {
                                    errorflag = 1;
                                    weak6.setError("Invalid weak");
                                } else {
                                    errorflag = 0;
                                    if (sweak7.length() < 2) {
                                        errorflag = 1;
                                        weak7.setError("Invalid weak");
                                    } else {
                                        errorflag = 0;
                                        if (sweak8.length() < 2) {
                                            errorflag = 1;
                                            weak8.setError("Invalid weak");
                                        } else {
                                            errorflag = 0;
                                            if (sweak9.length() < 2) {
                                                errorflag = 1;
                                                weak9.setError("Invalid weak");
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
                weak1.setError("Invalid weak");
            } else {
                errorflag = 0;
                if (sweak2.length() < 2) {
                    errorflag = 1;
                    weak2.setError("Invalid weak");
                } else {
                    errorflag = 0;
                    if (sweak3.length() < 2) {
                        errorflag = 1;
                        weak3.setError("Invalid weak");
                    } else {
                        errorflag = 0;
                        if (sweak4.length() < 2) {
                            errorflag = 1;
                            weak4.setError("Invalid weak");
                        } else {
                            errorflag = 0;
                            if (sweak5.length() < 2) {
                                errorflag = 1;
                                weak5.setError("Invalid weak");
                            } else {
                                errorflag = 0;
                                if (sweak6.length() < 2) {
                                    errorflag = 1;
                                    weak6.setError("Invalid weak");
                                } else {
                                    errorflag = 0;
                                    if (sweak7.length() < 2) {
                                        errorflag = 1;
                                        weak7.setError("Invalid weak");
                                    } else {
                                        errorflag = 0;
                                        if (sweak8.length() < 2) {
                                            errorflag = 1;
                                            weak8.setError("Invalid weak");
                                        } else {
                                            errorflag = 0;
                                            if (sweak9.length() < 2) {
                                                errorflag = 1;
                                                weak9.setError("Invalid weak");
                                            } else {
                                                errorflag = 0;
                                                if (sweak10.length() < 2) {
                                                    errorflag = 1;
                                                    weak10.setError("Invalid weak");
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
            try {

                MyProfileWeaknessesModal obj2 = new MyProfileWeaknessesModal(sweak1, sweak2, sweak3, sweak4, sweak5, sweak6, sweak7, sweak8, sweak9, sweak10);;

                encobj =OtoString(obj2,MySharedPreferencesManager.getDigest1(MyProfileWeaknesses.this),MySharedPreferencesManager.getDigest2(MyProfileWeaknesses.this));
                Log.d("TAG", "validateandSave: encobj - "+encobj);
                new SaveWeak().execute();

            } catch (Exception e) {
                Toast.makeText(MyProfileWeaknesses.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    class SaveWeak extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("w1", encobj));      //1
            json = jParser.makeHttpRequest(MyConstants.url_saveweaknesses, "GET", params);
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

                ProfileRole r = new ProfileRole();
                String role = r.getRole();
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
                Toast.makeText(MyProfileWeaknesses.this, result, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_save:

                validateandSave();
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
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#282f35"));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#282f35"));
                }
            });

            alertDialog.show();
        } else
            MyProfileWeaknesses.super.onBackPressed();
    }
}
