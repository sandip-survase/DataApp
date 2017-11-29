package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.AdminInstituteModal;

import static placeme.octopusites.com.placeme.AES4all.OtoString;

public class AdminInstituteTabFragment extends Fragment {
    public static final String MyPREFERENCES = "MyPrefs";
//    Button save;
//    ProgressBar saveprogress;
    public static final String Username = "nameKey";
    EditText iname, iemail, iweb, iphone, ialtphone, uniname, ireg;
    String instname = "", instemail = "", instweb = "", instphone = "", instaltrphone = "", universityname = "", instreg = "", strobj = "";
    String encUsername, enciname, encinstemail, encinstweb, encinstphone, encinstaltrphone, encuniversityname, encCinstreg;
    int errorflag1 = 0, errorflag2 = 0, errorflag3 = 0, errorflag4 = 0, errorflag5 = 0, errorflag6 = 0, errorflag7 = 0;
    String digest1, digest2;
    int edittedFlag = 0;
    SharedPreferences sharedpreferences;
    String username = "", resultofop;
    JSONObject json;
    JSONParser jParser = new JSONParser();


    AdminData a = new AdminData();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_edit_profile_admin_institute, container, false);

        Digest d = new Digest();
        digest1 = d.getDigest1();
        digest2 = d.getDigest2();

        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username = sharedpreferences.getString(Username, null);
//        String role=sharedpreferences.getString("role",null);

        if (digest1 == null || digest2 == null) {
            digest1 = sharedpreferences.getString("digest1", null);
            digest2 = sharedpreferences.getString("digest2", null);
            d.setDigest1(digest1);
            d.setDigest2(digest2);
        }
        iname = (EditText) rootView.findViewById(R.id.instname);
        iemail = (EditText) rootView.findViewById(R.id.instemail);
        iweb = (EditText) rootView.findViewById(R.id.instweb);
        iphone = (EditText) rootView.findViewById(R.id.instphone);
        ialtphone = (EditText) rootView.findViewById(R.id.instphonea);
        uniname = (EditText) rootView.findViewById(R.id.instuniversity);
        ireg = (EditText) rootView.findViewById(R.id.instreg);
//        save=(Button)rootView.findViewById(R.id.savepersonal);
//        saveprogress=(ProgressBar) rootView.findViewById(R.id.personalprogress);


        ProfileRole r = new ProfileRole();
        encUsername = r.getUsername();

        instname = a.getInstitute();
        instemail = a.getInstemail();
        instweb = a.getInstweb();
        instphone = a.getInstphone();
        instaltrphone = a.getInstaltrphone();
        universityname = a.getUnivname();
        instreg = a.getInstregno();

        if (instname != null)
            iname.setText(instname);
        if (instemail != null)
            iemail.setText(instemail);
        if (instweb != null)
            iweb.setText(instweb);
        if (instphone != null)
            iphone.setText(instphone);
        if (instaltrphone != null)
            ialtphone.setText(instaltrphone);
        if (universityname != null)
            uniname.setText(universityname);
        if (instreg != null)
            ireg.setText(instreg);
        iname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        iemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        iweb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        iphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ialtphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        uniname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ireg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edittedFlag = 1;

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return rootView;
    }

    public boolean validate() {

        errorflag1 = 0;
        errorflag2 = 0;
        errorflag3 = 0;
        errorflag4 = 0;
        errorflag5 = 0;
        errorflag6 = 0;
        errorflag7 = 0;

        instname = iname.getText().toString();
        instemail = iemail.getText().toString();
        instweb = iweb.getText().toString();
        instphone = iphone.getText().toString();
        instaltrphone = ialtphone.getText().toString();
        universityname = uniname.getText().toString();
        instreg = ireg.getText().toString();

        if (instname.length() < 2) {
            iname.setError("Incorrect Institute Name");

            errorflag7 = 1;

        } else {
            if (!instemail.contains("@")) {
                iemail.setError("Incorrect Email Address");
                errorflag1 = 1;
            } else {
                if (instweb.length() < 3 && !instweb.contains(".")) {
                    iweb.setError("Incorrect Web Address");
                    errorflag2 = 1;
                } else {
                    if (instphone.length() < 6) {
                        iphone.setError("Incorrect phone number ");
                        errorflag3 = 1;
                    } else {
                        if (instaltrphone.length() < 6) {
                            ialtphone.setError("Incorrect phone number ");
                            errorflag4 = 1;
                        } else {
                            if (universityname.length() < 2) {
                                uniname.setError("Incorrect phone number ");
                                errorflag5 = 1;
                            } else {
                                if (instreg.length() < 2) {
                                    ireg.setError("Incorrect Registration number ");
                                    errorflag6 = 1;
                                }
                            }
                        }

                    }


                }
            }
        }
        if (errorflag1 == 0 && errorflag2 == 0 && errorflag3 == 0 && errorflag4 == 0 && errorflag5 == 0 && errorflag6 == 0 && errorflag7 == 0) {
            return true;
        }

        return false;
    }

    public void save() {
        if (errorflag1 == 0 && errorflag2 == 0 && errorflag3 == 0 && errorflag4 == 0 && errorflag5 == 0 && errorflag6 == 0 && errorflag7 == 0) {
            try {

                AdminInstituteModal obj = new AdminInstituteModal(instname, instemail, instweb, instphone, instaltrphone, universityname, instreg);
//

                digest1 = MySharedPreferencesManager.getDigest1(getActivity());
                digest2 = MySharedPreferencesManager.getDigest2(getActivity());

                strobj = OtoString(obj, digest1, digest2);
                Log.d("TAG", "validateandSave: - " + strobj);

                new SaveData().execute();
//                save.setVisibility(View.GONE);
//                saveprogress.setVisibility(View.VISIBLE);


            } catch (Exception e) {

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

                    // ...other AnimationListener methods go here...
                });
            }
        }

        return animation;
    }

    class SaveData extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            Log.d("TAG", "doInBackground: here");
            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", encUsername));    //0
            params.add(new BasicNameValuePair("obj", strobj));    //1


            json = jParser.makeHttpRequest(MyConstants.url_SaveAdminInstituteData, "GET", params);
            try {
                r = json.getString("info");
                Log.d("TAG", "doInBackground: r - "+r);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("success")) {
//                save.setVisibility(View.VISIBLE);
//                saveprogress.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Successfully Saved..!", Toast.LENGTH_SHORT).show();
                if (edittedFlag == 1) {
                    getActivity().setResult(111);

                }
                edittedFlag = 0;

            } else {
                Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();

            }
        }
    }
}