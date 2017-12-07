package placeme.octopusites.com.placeme;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class HrCompanyDetailsTabFragment extends Fragment {
    public static final String USERNAME = "nameKey";
    public static final String MyPREFERENCES = "MyPrefs";
    public static String HRlog = "HRlog";
    public int pos;
    String digest1, digest2;
    String ComName = "", ComMail = "", ComWeb = "", ComPhone = "", ComAlterPhone = "", ComCIIN = "", ComAdd1 = "", ComAdd2 = "", ComAdd3 = "";
    String CompanyType, encobj = "";
    String CompanyNamestr = "", CompanyEmailstr = "", CompanyWebstr = "", Companyphonestr = "", CompanyAltPhonestr = "", CompanyCIINstr = "", CompanyNaturestr = "", CompanyAddressLine1str = "", CompanyAddressLine2str = "", CompanyAddressLine3str = "";
    EditText CompanyName, CompanyEmail, CompanyWebsite, CompanyPhone, CompanyAlternatePhone, CompanyCIN, CompanyAddressLine1, CompanyAddressLine2, CompanyAddressLine3;
    Spinner Company_Nature;
    String encComName, encUsername, encComMail, encComWeb, encComPhone, encComAlterPhone, encComCIIN, encComType, encComAddL1, encComAddL2, encComAddL3;
    String[] Nature = {"-Select Company Nature-", "Partnership", "Propietorship", "LLP (Limited Liability)", "Private Limited", "Public Limited", "Inc"};
    JSONParser jsonParser = new JSONParser();
    //    private static String url_savedata = "http://192.168.100.10/AESTest/SaveHrCompany";
    JSONObject json;

    String userName;
    //    Button savepersonal;
    HrData h = new HrData();
    ArrayAdapter<String> dataAdapter;
    int flag1 = 0, flag2 = 0, flag3 = 0, flag4 = 0, flag5 = 0, flag6 = 0, flag7 = 0, flag8 = 0, flag9 = 0, flag10 = 0;
    int errorflag1 = 0;
    //    ProgressBar personalprogress;


    public HrCompanyDetailsTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_hr_company_details_tab, container, false);

        digest1 = MySharedPreferencesManager.getDigest1(getActivity());
        digest2 = MySharedPreferencesManager.getDigest2(getActivity());
        userName = MySharedPreferencesManager.getUsername(getActivity());
        encUsername = userName;

        CompanyName = (EditText) rootView.findViewById(R.id.instname);
        CompanyEmail = (EditText) rootView.findViewById(R.id.instemail);
        CompanyWebsite = (EditText) rootView.findViewById(R.id.instweb);
        CompanyPhone = (EditText) rootView.findViewById(R.id.instphone);
        CompanyAlternatePhone = (EditText) rootView.findViewById(R.id.instphonea);
        CompanyCIN = (EditText) rootView.findViewById(R.id.instreg);

        CompanyAddressLine1 = (EditText) rootView.findViewById(R.id.compaddressline1);
        CompanyAddressLine2 = (EditText) rootView.findViewById(R.id.compaddressline2);
        CompanyAddressLine3 = (EditText) rootView.findViewById(R.id.compaddressline3);

        CompanyName = (EditText)rootView.findViewById(R.id.instname);
        CompanyEmail = (EditText)rootView.findViewById(R.id.instemail);
        CompanyWebsite = (EditText)rootView.findViewById(R.id.instweb);
        CompanyPhone = (EditText)rootView.findViewById(R.id.instphone);
        CompanyAlternatePhone = (EditText)rootView.findViewById(R.id.instphonea);
        CompanyCIN = (EditText)rootView.findViewById(R.id.instreg);

        CompanyAddressLine1 = (EditText)rootView.findViewById(R.id.compaddressline1);
        CompanyAddressLine2 = (EditText)rootView.findViewById(R.id.compaddressline2);
        CompanyAddressLine3 = (EditText)rootView.findViewById(R.id.compaddressline3);
//        savepersonal = (Button)rootView.findViewById(R.id.savepersonal);
//        personalprogress = (ProgressBar)rootView.findViewById(R.id.personalprogress);
        Company_Nature = (Spinner) rootView.findViewById(R.id.board10);

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


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, Nature) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {

                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                Typeface custom_font3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/abz.ttf");
                tv.setTypeface(custom_font3);

                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.parseColor("#eeeeee"));
                }
                return view;
            }
        };

        Company_Nature.setAdapter(dataAdapter);


        Company_Nature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                CompanyType = Nature[position];
                if (CompanyNaturestr != null) {
                    if (!CompanyNaturestr.equals(CompanyType)) {
                        flag1 = 1;
                    }
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        if (CompanyNamestr != null) {
            if (!CompanyNamestr.equals(""))
                CompanyName.setText(CompanyNamestr);

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

        if (CompanyNaturestr != null) {
            if (!CompanyNaturestr.equals("")) {
                Company_Nature.setSelection(dataAdapter.getPosition(CompanyNaturestr));
            }
        }

        CompanyName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                flag1 = 1;

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

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


//        savepersonal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                savepersonal.setVisibility(View.GONE);
//                personalprogress.setVisibility(View.VISIBLE);
//                validateandSave();
//            }
//        });

        flag1 = 0;
        return rootView;
    }

    public void validateandSave() {
//         errorflag2 = 0, errorflag3 = 0, errorflag4 = 0, errorflag5 = 0, errorflag6 = 0, errorflag7 = 0, errorflag8 = 0, errorflag9 = 0, errorflag10 = 0;
        if (validate()) {
            save();
        }

    }

    public boolean validate() {
        errorflag1 = 0;


        ComName = CompanyName.getText().toString();
        ComMail = CompanyEmail.getText().toString();
        ComWeb = CompanyWebsite.getText().toString();
        ComPhone = CompanyPhone.getText().toString();
        ComAlterPhone = CompanyAlternatePhone.getText().toString();
        ComCIIN = CompanyCIN.getText().toString();

        ComAdd1 = CompanyAddressLine1.getText().toString();
        ComAdd2 = CompanyAddressLine2.getText().toString();
        ComAdd3 = CompanyAddressLine3.getText().toString();

        if (ComName.length() < 1) {
            CompanyName.setError("Please Enter valid Company Name");
            errorflag1 = 1;
        } else if (ComAdd1.length() < 1) {
            CompanyAddressLine1.setError("Please Enter valid Address ");
            errorflag1 = 1;
        } else if (ComAdd2.length() < 1) {
            CompanyAddressLine2.setError("Please Enter valid Address ");
            errorflag1 = 1;
        } else if (ComAdd3.length() < 1) {
            CompanyAddressLine3.setError("Please Enter valid Address ");
            errorflag1 = 1;
        } else if (ComMail.length() < 1) {
            CompanyEmail.setError("Please Enter valid EMail Id");
            errorflag1 = 1;
        } else if (!ComMail.contains("@")) {
            CompanyEmail.setError("Please Enter valid EMail Id");
            errorflag1 = 1;
        } else if (ComWeb.length() < 1 || !ComWeb.contains(".")) {
            CompanyWebsite.setError("Please Enter valid Company Website");
            errorflag1 = 1;
        } else if (ComPhone.length() < 7) {
            CompanyPhone.setError("Please Enter valid Phone number");
            errorflag1 = 1;
        } else if (ComAlterPhone.length() < 7) {
            CompanyAlternatePhone.setError("Please Enter valid Alternate Phone");
            errorflag1 = 1;
        } else if (ComCIIN.length() < 3) {
            CompanyCIN.setError("Please Enter valid Company CIIN");
            errorflag1 = 1;
        } else if (pos == 0) {
            Toast.makeText(getActivity(), "Select Valid Company Type ", Toast.LENGTH_SHORT).show();
            errorflag1 = 1;
        }
        if (errorflag1 == 0) {
            return true;
        } else
            return false;

    }

    public void save() {
        if (errorflag1 == 0) {

            try {
                ArrayList<CompanyDetailsModal> modalList = new ArrayList<>();
                CompanyDetailsModal obj2 = new CompanyDetailsModal(ComName, ComMail, ComWeb, ComPhone, ComAlterPhone, ComCIIN, ComAdd1, ComAdd2, ComAdd3, CompanyType);
                Log.d("TAG", "validateandSave:-" + obj2.ComName + "   " + obj2.ComMail + "" + obj2.ComWeb + "  " + obj2.ComPhone + "   " + obj2.ComAlterPhone + "   " + obj2.ComCIIN + "   " + obj2.ComAdd1 + "   " + obj2.ComAdd2 + "   " + obj2.ComAdd3 + "   " + obj2.CompanyNature);
                Log.d("TAG", "validateandSave: - modallist size " + modalList.size());
                encobj = OtoString(obj2, MySharedPreferencesManager.getDigest1(getActivity()), MySharedPreferencesManager.getDigest2(getActivity()));
                Log.d("TAG", "validateandSave: encobj - " + encobj);
                new SaveDataHr().execute();
            } catch (Exception company) {
                Toast.makeText(getActivity(), company.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation animation = super.onCreateAnimation(transit, enter, nextAnim);

        // HW layer support only exists on API 11+
        if (Build.VERSION.SDK_INT >= 11) {
            if (animation == null && nextAnim != 0) {
                animation = AnimationUtils.loadAnimation(getActivity(), nextAnim);
            }

            if (animation != null) {
                getView().setLayerType(View.LAYER_TYPE_HARDWARE, null);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    public void onAnimationEnd(Animation animation) {
                        getView().setLayerType(View.LAYER_TYPE_NONE, null);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }

        return animation;
    }

    class SaveDataHr extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {

            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));
            params.add(new BasicNameValuePair("d", encobj));       //1

            json = jsonParser.makeHttpRequest(MyConstants.url_SaveHrCompany, "GET", params);
            try {
                r = json.getString("info");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        protected void onPostExecute(String result) {
            if (result.equals("success")) {
                flag1 = 0;
                h.setCompanyNature(CompanyType);
                getActivity().setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);
            }
        }
    }
}
