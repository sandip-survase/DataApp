package placeme.octopusites.com.placeme;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.AdminContactDetailsModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;
import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;


/**
 * A simple {@link Fragment} subclass.
 */
public class HrContactTabFragment extends Fragment {

    TextInputLayout fnameinput,lnameinput,emailinput,emai2input,addressline1input,addressline2input,addressline3input,phoneinput,mobileinput,mobile2input;
    EditText fname, lname, email, email2, addressline1, addressline2, addressline3, phone, mobile, mobile2;
//    Button saveContactDetailsButton;
//    ProgressBar contactDetailsProgress;

//    private static String   URL_SAVE_HR_CONTACT_DETAILS = "http://192.168.100.10/AESTest/SaveHrContact1";

    String username,role;
    String digest1, digest2;

    String hrfname = "", hrlname = "", hremail2 = "", hraddressline1 = "", hraddressline2 = "", hraddressline3 = "", hrphone = "", hrmobile = "", hrmobile2 = "";
    String encfname,enclname,encemail2,encaddressline1,encaddressline2,encaddressline3,encphone,encmobile,encmobile2;
    String plainusername = "";
    String strobj="";
    int edittedFlag=0;

    JSONParser jParser = new JSONParser();
    JSONObject json;
    StudentData s = new StudentData();
    HrData hrData = new HrData();

    public HrContactTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView= inflater.inflate(R.layout.fragment_hr_contact_tab, container, false);



        digest1 = MySharedPreferencesManager.getDigest1(getActivity());
        digest2 = MySharedPreferencesManager.getDigest2(getActivity());
        username=MySharedPreferencesManager.getUsername(getActivity());
        role=MySharedPreferencesManager.getRole(getActivity());

        fnameinput=(TextInputLayout)rootView.findViewById(R.id.fnameinput);
        lnameinput=(TextInputLayout)rootView.findViewById(R.id.lnameinput);
        emailinput=(TextInputLayout)rootView.findViewById(R.id.emailinput);
        emai2input=(TextInputLayout)rootView.findViewById(R.id.emai2input);
        addressline1input=(TextInputLayout)rootView.findViewById(R.id.addressline1input);
        addressline2input=(TextInputLayout)rootView.findViewById(R.id.addressline2input);
        addressline3input=(TextInputLayout)rootView.findViewById(R.id.addressline3input);
        phoneinput=(TextInputLayout)rootView.findViewById(R.id.phoneinput);
        mobileinput=(TextInputLayout)rootView.findViewById(R.id.mobileinput);
        mobile2input=(TextInputLayout)rootView.findViewById(R.id.mobile2input);

        fnameinput.setTypeface(MyConstants.getLight(getActivity()));
        lnameinput.setTypeface(MyConstants.getLight(getActivity()));
        emailinput.setTypeface(MyConstants.getLight(getActivity()));
        emai2input.setTypeface(MyConstants.getLight(getActivity()));
        addressline1input.setTypeface(MyConstants.getLight(getActivity()));
        addressline2input.setTypeface(MyConstants.getLight(getActivity()));
        addressline3input.setTypeface(MyConstants.getLight(getActivity()));
        phoneinput.setTypeface(MyConstants.getLight(getActivity()));
        mobileinput.setTypeface(MyConstants.getLight(getActivity()));
        mobile2input.setTypeface(MyConstants.getLight(getActivity()));

        fname = (EditText) rootView.findViewById(R.id.fname);
        lname = (EditText) rootView.findViewById(R.id.lname);
        email = (EditText) rootView.findViewById(R.id.email);
        email2 = (EditText) rootView.findViewById(R.id.email2);
        addressline1 = (EditText) rootView.findViewById(R.id.addressline1);
        addressline2 = (EditText) rootView.findViewById(R.id.addressline2);
        addressline3 = (EditText) rootView.findViewById(R.id.addressline3);
        phone = (EditText) rootView.findViewById(R.id.phone);
        mobile = (EditText) rootView.findViewById(R.id.mobile);
        mobile2 = (EditText) rootView.findViewById(R.id.mobile2);

        fname.setTypeface(MyConstants.getBold(getActivity()));
        lname.setTypeface(MyConstants.getBold(getActivity()));
        email.setTypeface(MyConstants.getBold(getActivity()));
        email2.setTypeface(MyConstants.getBold(getActivity()));
        addressline1.setTypeface(MyConstants.getBold(getActivity()));
        addressline2.setTypeface(MyConstants.getBold(getActivity()));
        addressline3.setTypeface(MyConstants.getBold(getActivity()));
        phone.setTypeface(MyConstants.getBold(getActivity()));
        mobile.setTypeface(MyConstants.getBold(getActivity()));
        mobile2.setTypeface(MyConstants.getBold(getActivity()));

//        saveContactDetailsButton= (Button) rootView.findViewById(R.id.saveContactDetailsButton);
//        contactDetailsProgress= (ProgressBar) rootView.findViewById(R.id.contactDetailsProgress);


        email.setFocusable(false);
        email.setFocusableInTouchMode(false);
        email.setClickable(false);


        TextView addresstxt = (TextView) rootView.findViewById(R.id.addresstxt);
        TextView contactnotxt = (TextView) rootView.findViewById(R.id.contactnotxt);

        addresstxt.setTypeface(MyConstants.getBold(getActivity()));
        contactnotxt.setTypeface(MyConstants.getBold(getActivity()));

        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";


        try {
            byte[] usernameEncryptedBytes = SimpleBase64Encoder.decode(username);
            byte[] usernameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, usernameEncryptedBytes);
            plainusername = new String(usernameDecryptedBytes);
            email.setText(plainusername);
        } catch (Exception e) {
        }


        hrfname = s.getFname();
        hrlname = s.getLname();
        hrmobile = s.getPhone();
        hremail2 = s.getEmail2();
        hraddressline1 = s.getAddressline1();
        hraddressline2 = s.getAddressline2();
        hraddressline3 = s.getAddressline3();
        hrphone = s.getTelephone();
        hrmobile2 = s.getMobile2();


//        hrfname = hrData.getFname();
//        hrlname = hrData.getLname();
//        hrmobile = hrData.getMobile();
//        hremail2 = hrData.getEmail2();
//        hraddressline1 = hrData.getAddressline1();
//        hraddressline2 = hrData.getAddressline2();
//        hraddressline3 = hrData.getAddressline3();
//        hrphone = hrData.getPhone();
//        hrmobile2 = hrData.getMobile2();

        if(hrfname!=null) {
            if (hrfname.length() > 1) {
                fname.setText(hrfname);
            }
        }
        if(hrlname!=null) {
            if (hrlname.length() > 1) {
                lname.setText(hrlname);
            }
        }
        if(hrmobile!=null) {   // main number
            if (hrmobile.length() > 3) {
                mobile.setText(hrmobile);
            }
        }
        if(hremail2!=null) {
            if (hremail2.length() > 3) {

                email2.setText(hremail2);
            }
        }
        if(hraddressline1!=null) {
            if (hraddressline1.length() > 1) {
                addressline1.setText(hraddressline1);
            }
        }
        if(hraddressline2!=null) {
            if (hraddressline2.length() > 1) {
                addressline2.setText(hraddressline2);
            }
        }
        if(hraddressline3!=null) {
            if (hraddressline3.length() > 1) {
                addressline3.setText(hraddressline3);
            }
        }
        if(hrphone!=null) {
            if (hrphone.length() > 3) {
                phone.setText(hrphone);
            }
        }
        if(hrmobile2!=null) {
            if (hrmobile2.length() > 3) {
                mobile2.setText(hrmobile2);
            }
        }

//
        fname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                fnameinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        lname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                lnameinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                emailinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        email2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                emai2input.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        addressline1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                addressline1input.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        addressline2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                addressline2input.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        addressline3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                addressline3input.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                phoneinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                mobileinput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mobile2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
                mobile2input.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



//        saveContactDetailsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveContactDetailsButton.setVisibility(View.GONE);
//                contactDetailsProgress.setVisibility(View.VISIBLE);
//                validateandSave();
//            }
//        });


        edittedFlag=0;

        return rootView;

    }// oncreateview



    void validateandSave()
    {
        if(validate())
        {
            save();
        }
    }
    public boolean validate() {

        int errorflag=0;

        hrfname=fname.getText().toString();
        hrlname=lname.getText().toString();
        hremail2=email2.getText().toString();
        hraddressline1=addressline1.getText().toString();
        hraddressline2=addressline2.getText().toString();
        hraddressline3=addressline3.getText().toString();
        hrphone=phone.getText().toString();
        hrmobile=mobile.getText().toString();
        hrmobile2=mobile2.getText().toString();

        if(hrfname.length()<2)
        {
            errorflag=1;
            fnameinput.setError("Kindly enter valid first name");
        }
        else {
            if(hrlname.length()<2)
            {
                errorflag=1;
                lnameinput.setError("Kindly enter valid last name");
            }
            else {
                if (plainusername.equals(hremail2)) {
                    errorflag = 1;
                    emai2input.setError("Personal and professional email cannot be same");
                }  else {
                        if (hraddressline1.length() < 1) {
                            errorflag = 1;
                            addressline1input.setError("Kindly enter valid address");
                        } else {
                            if (hraddressline2.length() < 1) {
                                errorflag = 1;
                                addressline2input.setError("Kindly enter valid address");
                            } else {
                                if (hraddressline3.length() < 1) {
                                    errorflag = 1;
                                    addressline3input.setError("Kindly enter valid address");
                                } else {
                                        if (hrmobile.length() < 10 || hrmobile.length() > 10) {
                                            errorflag = 1;
                                            mobileinput.setError("Kindly enter valid 10-digit mobile number");
                                        }
                                    }
                            }
                        }

                }
            }

        }
        if(errorflag == 0)
        {
            return true;

        }
        else{

            return false;
        }
    }


    public void save()
    {


        try {

            AdminContactDetailsModal obj = new AdminContactDetailsModal(hrfname, hrlname, plainusername, hremail2, hraddressline1, hraddressline2, hraddressline3, hrphone, hrmobile, hrmobile2);
            strobj = OtoString(obj, digest1, digest2);
            Log.d("encstrobj", "strobj: " + strobj);
            new SaveHrContactDetailsTask().execute();

        }catch (Exception e){
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();}

    }

    class SaveHrContactDetailsTask extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", username));       //0
            params.add(new BasicNameValuePair("obj", strobj));               //1
//            json = jParser.makeHttpRequest(MyConstants.url_SaveAdminContact, "GET", params);


            if (role.equals("hr"))
                json = jParser.makeHttpRequest(MyConstants.url_SaveHrContact, "GET", params);
            else
                json = jParser.makeHttpRequest(MyConstants.url_SaveAdminContact, "GET", params);

            try {
                r = json.getString("info");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.equals("success"))
            {
                edittedFlag=0;
                Toast.makeText(getActivity(),"Successfully Saved..!",Toast.LENGTH_SHORT).show();

                if (role.equals("hr"))
                    getActivity().setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);
                else
                    getActivity().setResult(AdminActivity.ADMIN_DATA_CHANGE_RESULT_CODE);



            }
            else
                Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();


        }
    }






}
