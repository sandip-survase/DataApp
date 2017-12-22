package placeme.octopusites.com.placeme;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.CompanyDetailsModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;

public class HrCompanyDetails extends AppCompatActivity {

    public static String HRlog = "HRlog";
    public int pos;
    String CompanyType = "", strobj = "";
    String encobj = "";
    String CompanyNamestr = "", CompanyEmailstr = "", CompanyWebstr = "", Companyphonestr = "", CompanyAltPhonestr = "", CompanyCIINstr = "", CompanyNaturestr = "", CompanyAddressLine1str = "", CompanyAddressLine2str = "", CompanyAddressLine3str = "";
    TextInputEditText CompanyName,otherNature, CompanyEmail, CompanyWebsite, CompanyPhone, CompanyAlternatePhone, CompanyCIN, CompanyAddressLine1, CompanyAddressLine2, CompanyAddressLine3;
    Spinner Company_Nature;
    String encComName = "", encUsername = "", encComMail = "", encComWeb = "", encComPhone = "", encComAlterPhone = "", encComCIIN = "", encComType = "", encComAddL1 = "", encComAddL2 = "", encComAddL3 = "";
    String[] Nature = {"-Select Company Nature-", "Partnership", "Propietorship", "LLP (Limited Liability)", "Private Limited", "Public Limited", "Inc","Other"};
    JSONParser jsonParser = new JSONParser();

    JSONObject json;
    String userName;
    HrData h = new HrData();
    ArrayAdapter<String> dataAdapter;
    int flag1 = 0, flag2 = 0, flag3 = 0, flag4 = 0, flag5 = 0, flag6 = 0, flag7 = 0, flag8 = 0, flag9 = 0, flag10 = 0;
    TextInputLayout instnameinput,ocompanytherNatureTextInputLayout, addressline1input, addressline2input, addressline3input, instemailinput, instwebinput, instphoneinput, instphoneainput, instreginput;
    TextView loctxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_company_details);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Edit Company Details");
        ab.setDisplayHomeAsUpEnabled(true);

        userName = MySharedPreferencesManager.getUsername(HrCompanyDetails.this);
        encUsername = userName;

        final Drawable upArrow = getResources().getDrawable(R.drawable.close);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        CompanyName = (TextInputEditText) findViewById(R.id.instname);
        CompanyEmail = (TextInputEditText) findViewById(R.id.instemail);
        CompanyWebsite = (TextInputEditText) findViewById(R.id.instweb);
        CompanyPhone = (TextInputEditText) findViewById(R.id.instphone);
        CompanyAlternatePhone = (TextInputEditText) findViewById(R.id.instphonea);
        CompanyCIN = (TextInputEditText) findViewById(R.id.instreg);
        otherNature = (TextInputEditText) findViewById(R.id.otherNature);
        CompanyAddressLine1 = (TextInputEditText) findViewById(R.id.compaddressline1);
        CompanyAddressLine2 = (TextInputEditText) findViewById(R.id.compaddressline2);
        CompanyAddressLine3 = (TextInputEditText) findViewById(R.id.compaddressline3);
        loctxt=(TextView) findViewById(R.id.loctxt);
        Company_Nature = (Spinner) findViewById(R.id.board10);

        instnameinput = (TextInputLayout) findViewById(R.id.instnameinput);
        addressline1input = (TextInputLayout) findViewById(R.id.addressline1input);
        addressline2input = (TextInputLayout) findViewById(R.id.addressline2input);
        addressline3input = (TextInputLayout) findViewById(R.id.addressline3input);
        instemailinput = (TextInputLayout) findViewById(R.id.instemailinput);
        instwebinput = (TextInputLayout) findViewById(R.id.instwebinput);
        instphoneinput = (TextInputLayout) findViewById(R.id.instphoneinput);
        instphoneainput = (TextInputLayout) findViewById(R.id.instphoneainput);
        instreginput = (TextInputLayout) findViewById(R.id.instreginput);

        ocompanytherNatureTextInputLayout= (TextInputLayout) findViewById(R.id.otherNatureTextInputLayout);
        CompanyName.setTypeface(Z.getBold(HrCompanyDetails.this));
        CompanyEmail.setTypeface(Z.getBold(HrCompanyDetails.this));
        CompanyWebsite.setTypeface(Z.getBold(HrCompanyDetails.this));
        CompanyPhone.setTypeface(Z.getBold(HrCompanyDetails.this));
        CompanyAlternatePhone.setTypeface(Z.getBold(HrCompanyDetails.this));
        CompanyCIN.setTypeface(Z.getBold(HrCompanyDetails.this));
        CompanyAddressLine1.setTypeface(Z.getBold(HrCompanyDetails.this));
        CompanyAddressLine2.setTypeface(Z.getBold(HrCompanyDetails.this));
        CompanyAddressLine3.setTypeface(Z.getBold(HrCompanyDetails.this));
        loctxt.setTypeface(Z.getBold(HrCompanyDetails.this));
        otherNature.setTypeface(Z.getBold(HrCompanyDetails.this));

        instnameinput.setTypeface(Z.getLight(HrCompanyDetails.this));
        addressline1input.setTypeface(Z.getLight(HrCompanyDetails.this));
        addressline2input.setTypeface(Z.getLight(HrCompanyDetails.this));
        addressline3input.setTypeface(Z.getLight(HrCompanyDetails.this));
        instemailinput.setTypeface(Z.getLight(HrCompanyDetails.this));
        instwebinput.setTypeface(Z.getLight(HrCompanyDetails.this));
        instphoneinput.setTypeface(Z.getLight(HrCompanyDetails.this));
        instphoneainput.setTypeface(Z.getLight(HrCompanyDetails.this));
        instreginput.setTypeface(Z.getLight(HrCompanyDetails.this));
        ocompanytherNatureTextInputLayout.setTypeface(Z.getLight(this));

        CompanyNamestr = h.getCompanyName();
        CompanyEmailstr = h.getCompanyEmail();
        CompanyWebstr = h.getCompanyWeb();
        Companyphonestr = h.getCompanyphone();
        CompanyAltPhonestr = h.getCompanyAltPhone();
        CompanyCIINstr = h.getCompanyCIIN();
        CompanyNaturestr = h.getCompanyNature();

        CompanyAddressLine1str = h.getCompanyaddl1();
        CompanyAddressLine2str = h.getCompanyaddl2();
        CompanyAddressLine3str = h.getCompanyaddl3();

        Log.d("TAG", "onCreate: ");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, Nature) {
            @Override
            public boolean isEnabled(int position) {

                if (position == 0) {

                    return false;
                } else {
                    return true;
                }
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(getResources().getColor(R.color.dark_color));
                tv.setTypeface(Z.getBold(HrCompanyDetails.this));
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTypeface(Z.getBold(HrCompanyDetails.this));

                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(getResources().getColor(R.color.sky_blue_color));
                } else {
                    tv.setTextColor(getResources().getColor(R.color.dark_color));
                }
                return view;
            }
        };

        Company_Nature.setAdapter(dataAdapter);

        Company_Nature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //selectedCountry = (String) parent.getItemAtPosition(position);

                // new HrCompanyDetails().GetStates().execute();
                pos = position;
                CompanyType = Nature[position];
                Log.d("TAG", "onItemSelected: - " + CompanyType);

                if (CompanyType.equals("Other")) {
                    ocompanytherNatureTextInputLayout.setVisibility(View.VISIBLE);
                    otherNature.setVisibility(View.VISIBLE);
                } else
                    ocompanytherNatureTextInputLayout.setVisibility(View.GONE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        if (CompanyNamestr != null) {
            if (!CompanyNamestr.equals(""))
                CompanyName.setText(CompanyNamestr);

        } else {
            CompanyNamestr = "";
            CompanyType = "";
        }

        if (CompanyEmailstr != null) {
            if (!CompanyEmailstr.equals(""))
                CompanyEmail.setText(CompanyEmailstr);

        }
        if (CompanyWebstr != null) {
            if (!CompanyWebstr.equals(""))
                CompanyWebsite.setText(CompanyWebstr);

        }
        if (Companyphonestr != null) {
            if (!Companyphonestr.equals(""))
                CompanyPhone.setText(Companyphonestr);

        }
        if (CompanyAltPhonestr != null) {
            if (!CompanyAltPhonestr.equals(""))
                CompanyAlternatePhone.setText(CompanyAltPhonestr);

        }
        if (CompanyCIINstr != null) {
            if (!CompanyCIINstr.equals(""))
                CompanyCIN.setText(CompanyCIINstr);

        }
        if (CompanyAddressLine1str != null) {
            if (!CompanyAddressLine1str.equals(""))
                CompanyAddressLine1.setText(CompanyAddressLine1str);

        }
        if (CompanyAddressLine2str != null) {
            if (!CompanyAddressLine2str.equals(""))
                CompanyAddressLine2.setText(CompanyAddressLine2str);

        }
        if (CompanyAddressLine3str != null) {
            if (!CompanyAddressLine3str.equals(""))
                CompanyAddressLine3.setText(CompanyAddressLine3str);

        }

//        if (CompanyNaturestr != null) {
//            if (!CompanyNaturestr.equals("")) {
//                Company_Nature.setSelection(dataAdapter.getPosition(CompanyNaturestr));
//            }
//        }


        if (CompanyNaturestr != null) {
            int foundboard = 0;
            for (int i = 1; i <Nature.length - 1; i++)
                if (CompanyNaturestr.equals(Nature[i])) {
                    foundboard = 1;
                    break;
                }
            if (foundboard == 1)
                Company_Nature.setSelection(dataAdapter.getPosition(CompanyNaturestr));
            else {

                if (CompanyNaturestr.equals("")) {
                    Company_Nature.setSelection(dataAdapter.getPosition("- Select Course -"));
                    otherNature.setVisibility(View.GONE);
                } else {
                    Company_Nature.setSelection(dataAdapter.getPosition("Other"));
                    otherNature.setVisibility(View.VISIBLE);
                    otherNature.setText(CompanyNaturestr);
                }
            }

        } else
            CompanyNaturestr = "- Select Course -";


        otherNature.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ocompanytherNatureTextInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        CompanyName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                flag1 = 1;
                instnameinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        CompanyAddressLine1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                flag1 = 1;
                addressline1input.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        CompanyAddressLine2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                flag1 = 1;
                addressline2input.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        CompanyAddressLine3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                flag1 = 1;
                addressline3input.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        CompanyEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                flag1 = 1;
                instemailinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        CompanyWebsite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                flag1 = 1;
                instwebinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        CompanyPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                flag1 = 1;
                instphoneinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        CompanyAlternatePhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                flag1 = 1;
                instphoneainput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        CompanyCIN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                flag1 = 1;
                instreginput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


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

    public void validateandSave() {
        int errorflag1 = 0, errorflag2 = 0, errorflag3 = 0, errorflag4 = 0, errorflag5 = 0, errorflag6 = 0, errorflag7 = 0, errorflag8 = 0, errorflag9 = 0, errorflag10 = 0;
        String ComName = "", ComMail, ComWeb, ComPhone, ComAlterPhone, ComCIIN, ComAdd1, ComAdd2, ComAdd3,sOtherNature="";
        ComName = CompanyName.getText().toString();
        ComMail = CompanyEmail.getText().toString();
        ComWeb = CompanyWebsite.getText().toString();
        ComPhone = CompanyPhone.getText().toString();
        ComAlterPhone = CompanyAlternatePhone.getText().toString();
        ComCIIN = CompanyCIN.getText().toString();
        sOtherNature = otherNature.getText().toString();
        ComAdd1 = CompanyAddressLine1.getText().toString();
        ComAdd2 = CompanyAddressLine2.getText().toString();
        ComAdd3 = CompanyAddressLine3.getText().toString();

        if (ComName.length() < 1) {
            instnameinput.setError("Kindly enter valid Company Name");
            errorflag1 = 1;
        } else if (ComAdd1.length() < 1) {
            addressline1input.setError("Kindly enter valid Address ");
            errorflag8 = 1;
        } else if (ComAdd2.length() < 1) {
            addressline2input.setError("Kindly enter valid Address ");
            errorflag9 = 1;
        } else if (ComAdd3.length() < 1) {
            addressline3input.setError("Kindly enter valid Address ");
            errorflag10 = 1;
        } else if (ComMail.length() < 1) {
            instemailinput.setError("Kindly enter valid EMail Id");
            errorflag2 = 1;
        } else if (!ComMail.contains("@")) {
            instemailinput.setError("Kindly enter EMail Id");
            errorflag2 = 1;
        } else if (ComWeb.length() < 1 || !ComWeb.contains(".")) {
            instwebinput.setError("Please Enter valid Company Website");
            errorflag3 = 1;
        } else if (ComPhone.length() < 7) {
            instphoneinput.setError("Kindly enter valid Phone number");
            errorflag4 = 1;
        } else if (ComCIIN.length() < 3) {
            instreginput.setError("Kindly enter valid Company CIIN");
            errorflag6 = 1;
        } else if (pos == 0) {
            Toast.makeText(this, "Select Valid Company Type ", Toast.LENGTH_SHORT).show();
            errorflag7 = 1;
        }
        if (CompanyType != null && !CompanyType.equals("")) {
            if (CompanyType.equals("Other")) {
                if (sOtherNature.length() < 2) {
                    errorflag7 = 1;
                    ocompanytherNatureTextInputLayout.setError("Kindly provide valid company nature");
                } else
                    CompanyType = sOtherNature;
            }
        }

        if (errorflag1 == 0 && errorflag2 == 0 && errorflag3 == 0 && errorflag4 == 0 &&  errorflag6 == 0 && errorflag7 == 0 && errorflag8 == 0 && errorflag9 == 0 && errorflag10 == 0) {

            try {


                ArrayList<CompanyDetailsModal> modalList = new ArrayList<>();
//                String ComName, ComMail, ComWeb, ComPhone, ComAlterPhone, ComCIIN, ComAdd1, ComAdd2, ComAdd3;

                CompanyDetailsModal obj2 = new CompanyDetailsModal(ComName, ComMail, ComWeb, ComPhone, ComAlterPhone, ComCIIN, ComAdd1, ComAdd2, ComAdd3, CompanyType);

                Log.d("TAG", "validateandSave:-" + obj2.ComName + "   " + obj2.ComMail + "" + obj2.ComWeb + "  " + obj2.ComPhone + "   " + obj2.ComAlterPhone + "   " + obj2.ComCIIN + "   " + obj2.ComAdd1 + "   " + obj2.ComAdd2 + "   " + obj2.ComAdd3 + "   " + obj2.CompanyNature);

                Log.d("TAG", "validateandSave: - modallist size " + modalList.size());
                encobj = OtoString(obj2, MySharedPreferencesManager.getDigest1(HrCompanyDetails.this), MySharedPreferencesManager.getDigest2(HrCompanyDetails.this));

                Log.d("TAG", "validateandSave: encobj - " + encobj);

                new SaveDataHr().execute();

            } catch (Exception company) {

                Toast.makeText(this, company.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {

        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);

//|| !CompanyNaturestr.equals(CompanyType)
        if (flag1 == 1 ) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder
                    .setMessage("Do you want to discard changes ?")
                    .setCancelable(false)
                    .setPositiveButton("Discard",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    HrCompanyDetails.super.onBackPressed();
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

        } else {
            HrCompanyDetails.super.onBackPressed();
            setResult(Activity.RESULT_CANCELED, returnIntent);
        }

    }

    class SaveDataHr extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {
            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));
            params.add(new BasicNameValuePair("d", encobj));       //1

            json = jsonParser.makeHttpRequest(Z.url_SaveHrCompany, "GET", params);
            try {
                r = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;

        }

        protected void onPostExecute(String result) {
            if (result.equals("success")) {
                Toast.makeText(HrCompanyDetails.this, "Successfully Saved..!", Toast.LENGTH_SHORT).show();
                setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);
                HrCompanyDetails.super.onBackPressed();

            }
        }
    }
}
