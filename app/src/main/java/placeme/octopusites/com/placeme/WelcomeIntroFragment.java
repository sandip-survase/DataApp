package placeme.octopusites.com.placeme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;


public class WelcomeIntroFragment extends Fragment {

    View view;
//    JSONParser jParser = new JSONParser();
//    JSONObject json;
//    EditText fnameEditText,lnameEditText,mobileEditText;
//    Button nextButton;
//    Boolean errorFlag=false;
//    String fname,lname,mobile;
//    String encUsersName,encfname,enclname,encmobile;
//    String digest1, digest2;


    public WelcomeIntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_welcome_intro, container, false);

        ((Welcome)getActivity()).setWelComeIntroView(view);

        return view;

//        fnameEditText= (EditText) view.findViewById(R.id.fname);
//        lnameEditText= (EditText) view.findViewById(R.id.lname);
//        mobileEditText= (EditText) view.findViewById(R.id.mobile);
//        nextButton= (Button) view.findViewById(R.id.nextButton);
//
//        encUsersName=MySharedPreferencesManager.getUsername(getActivity());
//        digest1 = MySharedPreferencesManager.getDigest1(getActivity());
//        digest2 = MySharedPreferencesManager.getDigest2(getActivity());
//
//
//        nextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                fname=fnameEditText.getText().toString().trim();
//                lname=lnameEditText.getText().toString().trim();
//                mobile=mobileEditText.getText().toString().trim();
//
//                if(fname.length()<1){
//                    errorFlag=true;
//                    fnameEditText.setError("Enter minimum 2 characters");
//                }else if(fname.length()<1){
//                    lnameEditText.setError("Enter minimum 2 characters");
//                    errorFlag=true;
//                }else if(mobile.length()!=10){
//                    mobileEditText.setError("Incorrect mobile number");
//                }
//
//
//                if(!errorFlag){
//
//                    try {
//
//                        byte[] demoKeyBytes = SimpleBase64Encoder.decode(digest1);
//                        byte[] demoIVBytes = SimpleBase64Encoder.decode(digest2);
//                        String sPadding = "ISO10126Padding";
//
//                        byte[] fnameBytes = fname.getBytes("UTF-8");
//                        byte[] lnameBytes = lname.getBytes("UTF-8");
//                        byte[] mobileBytes = mobile.getBytes("UTF-8");
//
//
//                        byte[] fnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, fnameBytes);
//                        encfname = new String(SimpleBase64Encoder.encode(fnameEncryptedBytes));
//                        byte[] lnameEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, lnameBytes);
//                        enclname = new String(SimpleBase64Encoder.encode(lnameEncryptedBytes));
//                        byte[] mobileEncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, mobileBytes);
//                        encmobile = new String(SimpleBase64Encoder.encode(mobileEncryptedBytes));
//
//                        MySharedPreferencesManager.save(getActivity(),"fname",encfname);
//                        MySharedPreferencesManager.save(getActivity(),"lname",enclname);
//                        MySharedPreferencesManager.save(getActivity(),"mobile",encmobile);
//
//
//
//                    } catch (Exception e) {
//                        Log.d("TAG", "onClick: EXp "+e.getMessage());
//                    }
//                }
//
//            }
//        });



    }

}
