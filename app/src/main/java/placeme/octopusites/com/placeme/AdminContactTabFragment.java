package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

import static placeme.octopusites.com.placeme.AES4all.demo1decrypt;
import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;

public class AdminContactTabFragment extends Fragment {



    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";
    String username;
    String digest1,digest2;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    private static String url_savedata= "http://192.168.100.100/AESTest/SaveContact";
    int edittedFlag=0;
    AdminData a= new AdminData();
    StudentData s = new StudentData();
    EditText fname,lname,email,email2,addressline1,addressline2,addressline3,phone,mobile,mobile2;
    String sfname="",slname="",semail2="",saddressline1="",saddressline2="",saddressline3="",sphone="",smobile="",smobile2="";
    String encfname,enclname,encemail2,encaddressline1,encaddressline2,encaddressline3,encphone,encmobile,encmobile2;
    String plainusername="";
//    Button b1;
//    ProgressBar saveprg;
    int errorflag=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_edit_profile_admin_contact, container, false);

        digest1 = MySharedPreferencesManager.getDigest1(getActivity());
        digest2 = MySharedPreferencesManager.getDigest2(getActivity());
        username=MySharedPreferencesManager.getUsername(getActivity());
        String role=MySharedPreferencesManager.getRole(getActivity());


        TextView addresstxt=(TextView)rootView.findViewById(R.id.addresstxt);
        TextView contactnotxt=(TextView)rootView.findViewById(R.id.contactnotxt);
        Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/arba.ttf");
        addresstxt.setTypeface(custom_font1);
        contactnotxt.setTypeface(custom_font1);

        fname=(EditText) rootView.findViewById(R.id.fname);
        lname=(EditText) rootView.findViewById(R.id.lname);
        email=(EditText) rootView.findViewById(R.id.email);
        email2=(EditText) rootView.findViewById(R.id.email2);
        addressline1=(EditText) rootView.findViewById(R.id.addressline1);
        addressline2=(EditText) rootView.findViewById(R.id.addressline2);
        addressline3=(EditText) rootView.findViewById(R.id.addressline3);
        phone=(EditText) rootView.findViewById(R.id.phone);
        mobile=(EditText) rootView.findViewById(R.id.mobile);
        mobile2=(EditText) rootView.findViewById(R.id.mobile2);
//        b1=(Button)rootView.findViewById(R.id.savepersonal);
//        saveprg=(ProgressBar)rootView.findViewById(R.id.personalprogress);



        email.setFocusable(false);
        email.setFocusableInTouchMode(false);
        email.setClickable(false);

        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
        String sPadding = "ISO10126Padding";


        try {
            byte[] usernameEncryptedBytes = SimpleBase64Encoder.decode(username);
            byte[] usernameDecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, usernameEncryptedBytes);
            plainusername = new String(usernameDecryptedBytes);
            email.setText(plainusername);
        }catch (Exception e){}

      //getters
          sfname=a.getFname();
        slname=a.getLname();
        smobile=a.getPhone();
        semail2=s.getEmail2();
        saddressline1=s.getAddressline1();
        saddressline2=s.getAddressline2();
        saddressline3=s.getAddressline3();
        sphone=s.getTelephone();
        smobile2=s.getMobile2();
        if(sfname!=null) {
            if (sfname.length() > 1) {
                fname.setText(sfname);
            }
        }
        if(slname!=null) {
            if (slname.length() > 1) {
                lname.setText(slname);
            }
        }
        if(smobile!=null) {
            if (smobile.length() > 3) {
                mobile.setText(smobile);
            }
        }
        if(semail2!=null) {
            if (semail2.length() > 3) {

                email2.setText(semail2);
            }
        }
        if(saddressline1!=null) {
            if (saddressline1.length() > 1) {
                addressline1.setText(saddressline1);
            }
        }
        if(saddressline2!=null) {
            if (saddressline2.length() > 1) {
                addressline2.setText(saddressline2);
            }
        }
        if(saddressline3!=null) {
            if (saddressline3.length() > 1) {
                addressline3.setText(saddressline3);
            }
        }
        if(sphone!=null) {
            if (sphone.length() > 3) {
                phone.setText(sphone);
            }
        }
        if(smobile2!=null) {
            if (smobile2.length() > 3) {
                mobile2.setText(smobile2);
            }
        }

        edittedFlag=0;
        fname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag=1;
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
                edittedFlag=1;
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
                edittedFlag=1;
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
                edittedFlag=1;
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
                edittedFlag=1;
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
                edittedFlag=1;
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
                edittedFlag=1;
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
                edittedFlag=1;
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
                edittedFlag=1;
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
                edittedFlag=1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                validateandSave();
//            }
//        });



        return rootView;
    }

    void validateandSave()
    {






    }


    public boolean validate(){

        sfname=fname.getText().toString();
        slname=lname.getText().toString();
        semail2=email2.getText().toString();
        saddressline1=addressline1.getText().toString();
        saddressline2=addressline2.getText().toString();
        saddressline3=addressline3.getText().toString();
        sphone=phone.getText().toString();
        smobile=mobile.getText().toString();
        smobile2=mobile2.getText().toString();



        if(sfname.length()<2)
        {
            errorflag=1;
            fname.setError("Invalid Name");
        }
        else {
            if (smobile.length() < 10 || smobile.length() > 10) {
                errorflag = 1;
                mobile.setError("Mobile Number should have 10 digits");
            } else {
                if (plainusername.equals(semail2)) {
                    errorflag = 1;
                    email2.setError("Priamry and Alternate Email cannot be same");
                } else {
                    if (!email2.getText().toString().contains(".edu")) {
                        errorflag = 1;
                        email2.setError("Must Contain .edu");
                    } else {
                        if (saddressline1.length() < 1) {
                            errorflag = 1;
                            addressline1.setError("enter valid address");
                        } else {
                            if (saddressline2.length() < 1) {
                                errorflag = 1;
                                addressline2.setError("enter valid address");
                            } else {
                                if (saddressline3.length() < 1) {
                                    errorflag = 1;
                                    addressline3.setError("enter valid address");
                                } else {
                                    if (sphone.length() < 6) {
                                        phone.setError("Incorrect phone number ");
                                        errorflag = 1;
                                    } else {
                                        if (smobile.length() < 6) {
                                            mobile.setError("Incorrect phone number ");
                                            errorflag = 1;
                                        } else {
                                            if (smobile2.length() < 2) {
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
        }
        if(errorflag==0){
            return true;
        }

        return false;
    }

    public void save(){
        if(errorflag==0)
        {
//            b1.setVisibility(View.GONE);
//            saveprg.setVisibility(View.VISIBLE);
            try {
                byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
                byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
                String sPadding = "ISO10126Padding";


                byte[] fnameBytes = sfname.getBytes("UTF-8");
                byte[] lnameBytes = slname.getBytes("UTF-8");
                byte[] email2Bytes = semail2.getBytes("UTF-8");
                byte[] addressline1Bytes = saddressline1.getBytes("UTF-8");
                byte[] addressline2Bytes = saddressline2.getBytes("UTF-8");
                byte[] addressline3Bytes = saddressline3.getBytes("UTF-8");
                byte[] phoneBytes = sphone.getBytes("UTF-8");
                byte[] mobileBytes = smobile.getBytes("UTF-8");
                byte[] mobile2Bytes = smobile2.getBytes("UTF-8");

                byte[] fnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fnameBytes);
                encfname=new String(SimpleBase64Encoder.encode(fnameEncryptedBytes));
                byte[] lnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, lnameBytes);
                enclname=new String(SimpleBase64Encoder.encode(lnameEncryptedBytes));
                byte[] email2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, email2Bytes);
                encemail2=new String(SimpleBase64Encoder.encode(email2EncryptedBytes));
                byte[] addressline1EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, addressline1Bytes);
                encaddressline1=new String(SimpleBase64Encoder.encode(addressline1EncryptedBytes));
                byte[] addressline2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, addressline2Bytes);
                encaddressline2=new String(SimpleBase64Encoder.encode(addressline2EncryptedBytes));
                byte[] addressline3EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, addressline3Bytes);
                encaddressline3=new String(SimpleBase64Encoder.encode(addressline3EncryptedBytes));
                byte[] phoneEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, phoneBytes);
                encphone=new String(SimpleBase64Encoder.encode(phoneEncryptedBytes));
                byte[] mobileEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, mobileBytes);
                encmobile=new String(SimpleBase64Encoder.encode(mobileEncryptedBytes));
                byte[] mobile2EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, mobile2Bytes);
                encmobile2=new String(SimpleBase64Encoder.encode(mobile2EncryptedBytes));

                new SaveDetails().execute();

            }catch (Exception e){
                Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();}
        }
    }


    class SaveDetails extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));       //0
            params.add(new BasicNameValuePair("f",encfname));               //1
            params.add(new BasicNameValuePair("l",enclname));               //2
            params.add(new BasicNameValuePair("e",encemail2));              //3
            params.add(new BasicNameValuePair("ad1",encaddressline1));      //4
            params.add(new BasicNameValuePair("ad2",encaddressline2));      //5
            params.add(new BasicNameValuePair("ad3",encaddressline3));      //6
            params.add(new BasicNameValuePair("p",encphone));               //7
            params.add(new BasicNameValuePair("m1",encmobile));             //8
            params.add(new BasicNameValuePair("m2",encmobile2));            //9
            json = jParser.makeHttpRequest(url_savedata, "GET", params);
            try {
                r = json.getString("info");


            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("success"))
            {
//                saveprg.setVisibility(View.GONE);
//                b1.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(),"Successfully Saved..!",Toast.LENGTH_SHORT).show();
                if(edittedFlag==1){
                    getActivity().setResult(111);
                }
                edittedFlag=0;
            }
            else
                Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();

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

                    // ...other AnimationListener methods go here...
                });
            }
        }

        return animation;
    }

}