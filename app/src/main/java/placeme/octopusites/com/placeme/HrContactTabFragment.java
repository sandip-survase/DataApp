package placeme.octopusites.com.placeme;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
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

    EditText fname, lname, email, email2, addressline1, addressline2, addressline3, phone, mobile, mobile2;
//    Button saveContactDetailsButton;
//    ProgressBar contactDetailsProgress;

//    private static String   URL_SAVE_HR_CONTACT_DETAILS = "http://192.168.100.10/AESTest/SaveHrContact1";

    String username;
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
        String role=MySharedPreferencesManager.getRole(getActivity());


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

//        saveContactDetailsButton= (Button) rootView.findViewById(R.id.saveContactDetailsButton);
//        contactDetailsProgress= (ProgressBar) rootView.findViewById(R.id.contactDetailsProgress);


        email.setFocusable(false);
        email.setFocusableInTouchMode(false);
        email.setClickable(false);


        TextView addresstxt = (TextView) rootView.findViewById(R.id.addresstxt);
        TextView contactnotxt = (TextView) rootView.findViewById(R.id.contactnotxt);
        Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/arba.ttf");
        addresstxt.setTypeface(custom_font1);
        contactnotxt.setTypeface(custom_font1);

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
                fname.setError(null);
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
                lname.setError(null);
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
                email.setError(null);
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
                email2.setError(null);
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
                addressline1.setError(null);
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
                addressline2.setError(null);
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
                addressline3.setError(null);
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
                phone.setError(null);
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
                mobile.setError(null);
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
                mobile2.setError(null);
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
            fname.setError("Invalid Name");
        }
        else {
            if (plainusername.equals(hremail2)) {
                errorflag = 1;
                email2.setError("Priamry and Alternate Email cannot be same");
            } else {
                if (!email2.getText().toString().contains("@")) {
                    errorflag = 1;
                    email2.setError("Incorrect Email");
                } else {
                    if (hraddressline1.length() < 1) {
                        errorflag = 1;
                        addressline1.setError("Enter valid address");
                    } else {
                        if (hraddressline2.length() < 1) {
                            errorflag = 1;
                            addressline2.setError("Enter valid address");
                        } else {
                            if (hraddressline3.length() < 1) {
                                errorflag = 1;
                                addressline3.setError("Enter valid address");
                            } else {
                                if (hrphone.length() < 6) {
                                    phone.setError("Incorrect phone number ");
                                    errorflag = 1;
                                } else {
                                    if (hrmobile.length() < 10 || hrmobile.length() > 10) {
                                        errorflag = 1;
                                        mobile.setError("Mobile Number should have 10 digits");
                                    } else {
                                        if (hrmobile2.length() < 10 || hrmobile2.length() > 10) {
                                            mobile2.setError("Incorrect phone number ");
                                            errorflag = 1;
                                        }
                                    }
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

        ProfileRole obj = new ProfileRole();
        String role = obj.getRole();
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
