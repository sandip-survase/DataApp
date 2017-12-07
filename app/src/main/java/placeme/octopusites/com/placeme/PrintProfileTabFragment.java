package placeme.octopusites.com.placeme;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.DOWNLOAD_SERVICE;

public class PrintProfileTabFragment extends Fragment {

    RadioGroup radioGroupFormat;
    RadioButton radioButtonWord,radioButtonPdf,radioButtonTemplate1,radioButtonTemplate2,radioButtonTemplate3,radioButtonTemplate4,radioButtonTemplate5,radioButtonTemplate6,radioButtonTemplate7,radioButtonTemplate8,radioButtonTemplate9,radioButtonTemplate10,radioButtonTemplate11,radioButtonTemplate12,radioButtonTemplate13,radioButtonTemplate14,radioButtonTemplate15,radioButtonTemplate16,radioButtonTemplate17,radioButtonTemplate18,radioButtonTemplate19,radioButtonTemplate20;
    String format="pdf";
    Button downloadresume;
    int template=1;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    String resultofop="",username;
    ProgressBar resumeprogress;
    ImageView template1,template2,template3,template4,template5,template6,template7,template8,template9,template10,template11,template12,template13,template14,template15,template16,template17,template18,template19,template20;
    int resumeIds[];
    int count=0,setFlag=0;

    private static String load_resume_ids = "http://192.168.100.10/AESTest/GetMyResumeIds";
    private static String load_resume_thumbnail= "http://192.168.100.10/AESTest/GetResumeThumbnail";
    private static String load_student_data = "http://192.168.100.10/ProfileObjects/GetStudentData";

    ProgressBar progress1,progress2,progress3,progress4,progress5,progress6,progress7,progress8,progress9,progress10,progress11,progress12,progress13,progress14,progress15,progress16,progress17,progress18,progress19,progress20;
    String digest1,digest2;
    View getmoreselectionview;
    int found_box1=0,found_tenth=0,found_twelth=0,found_diploma=0,found_ug=0,found_pgsem=0,found_pgyear=0,found_projects=0,found_lang=0,found_certificates=0;
    int found_courses=0,found_skills=0,found_honors=0,found_patents=0,found_publications=0,found_careerobj=0,found_strengths=0,found_weaknesses=0,found_locationpreferences=0;
    int found_contact_details=0,found_personal=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_edit_profile_printprofile, container, false);



        username=MySharedPreferencesManager.getUsername(getActivity());
        digest1 = MySharedPreferencesManager.getDigest1(getActivity());
        digest2 = MySharedPreferencesManager.getDigest2(getActivity());

        getmoreselectionview=(View)rootView.findViewById(R.id.getmoreselectionview);
        template1=(ImageView)rootView.findViewById(R.id.template1);
        template2=(ImageView)rootView.findViewById(R.id.template2);
        template3=(ImageView)rootView.findViewById(R.id.template3);
        template4=(ImageView)rootView.findViewById(R.id.template4);
        template5=(ImageView)rootView.findViewById(R.id.template5);
        template6=(ImageView)rootView.findViewById(R.id.template6);
        template7=(ImageView)rootView.findViewById(R.id.template7);
        template8=(ImageView)rootView.findViewById(R.id.template8);
        template9=(ImageView)rootView.findViewById(R.id.template9);
        template10=(ImageView)rootView.findViewById(R.id.template10);
        template11=(ImageView)rootView.findViewById(R.id.template11);
        template12=(ImageView)rootView.findViewById(R.id.template12);
        template13=(ImageView)rootView.findViewById(R.id.template13);
        template14=(ImageView)rootView.findViewById(R.id.template14);
        template15=(ImageView)rootView.findViewById(R.id.template15);
        template16=(ImageView)rootView.findViewById(R.id.template16);
        template17=(ImageView)rootView.findViewById(R.id.template17);
        template18=(ImageView)rootView.findViewById(R.id.template18);
        template19=(ImageView)rootView.findViewById(R.id.template19);
        template20=(ImageView)rootView.findViewById(R.id.template20);

        progress1=(ProgressBar)rootView.findViewById(R.id.progress1);
        progress2=(ProgressBar)rootView.findViewById(R.id.progress2);
        progress3=(ProgressBar)rootView.findViewById(R.id.progress3);
        progress4=(ProgressBar)rootView.findViewById(R.id.progress4);
        progress5=(ProgressBar)rootView.findViewById(R.id.progress5);
        progress6=(ProgressBar)rootView.findViewById(R.id.progress6);
        progress7=(ProgressBar)rootView.findViewById(R.id.progress7);
        progress8=(ProgressBar)rootView.findViewById(R.id.progress8);
        progress9=(ProgressBar)rootView.findViewById(R.id.progress9);
        progress10=(ProgressBar)rootView.findViewById(R.id.progress10);
        progress11=(ProgressBar)rootView.findViewById(R.id.progress11);
        progress12=(ProgressBar)rootView.findViewById(R.id.progress12);
        progress13=(ProgressBar)rootView.findViewById(R.id.progress13);
        progress14=(ProgressBar)rootView.findViewById(R.id.progress14);
        progress15=(ProgressBar)rootView.findViewById(R.id.progress15);
        progress16=(ProgressBar)rootView.findViewById(R.id.progress16);
        progress17=(ProgressBar)rootView.findViewById(R.id.progress17);
        progress18=(ProgressBar)rootView.findViewById(R.id.progress18);
        progress19=(ProgressBar)rootView.findViewById(R.id.progress19);
        progress20=(ProgressBar)rootView.findViewById(R.id.progress20);


        template1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[1]));
            }
        });
        template2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[0]));
            }
        });
        template3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[2]));
            }
        });
        template4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[3]));
            }
        });
        template5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[4]));
            }
        });
        template6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[5]));
            }
        });
        template7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[6]));
            }
        });
        template8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[7]));
            }
        });
        template9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[8]));
            }
        });
        template10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[9]));
            }
        });
        template11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[10]));
            }
        });
        template12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[11]));
            }
        });
        template13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[12]));
            }
        });
        template14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[13]));
            }
        });
        template15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[14]));
            }
        });
        template16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[15]));
            }
        });
        template17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[16]));
            }
        });
        template18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[17]));
            }
        });
        template19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[18]));
            }
        });
        template20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ViewTemplateActivity.class).putExtra("key",resumeIds[19]));
            }
        });

        radioGroupFormat=(RadioGroup)rootView.findViewById(R.id.radioGroupFormat);
        radioButtonWord=(RadioButton)rootView.findViewById(R.id.radioButtonWord);
        radioButtonPdf=(RadioButton)rootView.findViewById(R.id.radioButtonPdf);
        radioButtonTemplate1=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate1);
        radioButtonTemplate2=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate2);
        radioButtonTemplate3=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate3);
        radioButtonTemplate4=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate4);
        radioButtonTemplate5=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate5);
        radioButtonTemplate6=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate6);
        radioButtonTemplate7=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate7);
        radioButtonTemplate8=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate8);
        radioButtonTemplate9=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate9);
        radioButtonTemplate10=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate10);
        radioButtonTemplate11=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate11);
        radioButtonTemplate12=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate12);
        radioButtonTemplate13=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate13);
        radioButtonTemplate14=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate14);
        radioButtonTemplate15=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate15);
        radioButtonTemplate16=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate16);
        radioButtonTemplate17=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate17);
        radioButtonTemplate18=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate18);
        radioButtonTemplate19=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate19);
        radioButtonTemplate20=(RadioButton)rootView.findViewById(R.id.radioButtonTemplate20);

        radioGroupFormat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButtonPdf:
                        format="pdf";
                        break;
                    case R.id.radioButtonWord:
                        format="word";
//                        radioButtonPdf.setChecked(false);
//                        Toast.makeText(getActivity(),"Word option is not available right now, but will be available soon.",Toast.LENGTH_LONG).show();
                        break;

                }

            }
        });

        MySharedPreferencesManager.save(getActivity(),"template",template+"");

        radioButtonTemplate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[0];
                MySharedPreferencesManager.save(getActivity(),"template",template+"");
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[1];
                MySharedPreferencesManager.save(getActivity(),"template",template+"");
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[2];
                MySharedPreferencesManager.save(getActivity(),"template",template+"");
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[3];
                MySharedPreferencesManager.save(getActivity(),"template",template+"");
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[4];
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[5];
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[6];
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[7];
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[8];
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[9];
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[10];
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[11];
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[12];
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[13];
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[14];
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[15];
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[16];
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[17];
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate19.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[18];
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate20.setChecked(false);
            }
        });
        radioButtonTemplate20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                template=resumeIds[19];
                radioButtonTemplate1.setChecked(false);
                radioButtonTemplate2.setChecked(false);
                radioButtonTemplate3.setChecked(false);
                radioButtonTemplate4.setChecked(false);
                radioButtonTemplate5.setChecked(false);
                radioButtonTemplate6.setChecked(false);
                radioButtonTemplate7.setChecked(false);
                radioButtonTemplate8.setChecked(false);
                radioButtonTemplate9.setChecked(false);
                radioButtonTemplate10.setChecked(false);
                radioButtonTemplate11.setChecked(false);
                radioButtonTemplate12.setChecked(false);
                radioButtonTemplate13.setChecked(false);
                radioButtonTemplate14.setChecked(false);
                radioButtonTemplate15.setChecked(false);
                radioButtonTemplate16.setChecked(false);
                radioButtonTemplate17.setChecked(false);
                radioButtonTemplate18.setChecked(false);
                radioButtonTemplate19.setChecked(false);

            }
        });


//        MySharedPreferencesManager.save(getActivity(),"template",template+"");

        Log.d("tag","template id -"+template);

//    read :    String temp=MySharedPreferencesManager.getData(getActivity(),"template");

        downloadresume=(Button)rootView.findViewById(R.id.downloadresume);
        resumeprogress=(ProgressBar)rootView.findViewById(R.id.resumeprogress);

        downloadresume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String temp=MySharedPreferencesManager.getData(getActivity(),"template");

                Log.d("tag","template id -"+template);
                Log.d("tag", "username - : "+username);
                Log.d("tag", "format - : "+format);
                Log.d("tag","temp id -"+temp);
//  my code
                new GetStudentData().execute();
//   my code end
            }
        });
        getmoreselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),GetTemplatesActivity.class));
            }
        });

        new GetResumeIds().execute();

        return rootView;
    }
    void setThumnails()
    {

        //getting error ---------------------------------------------------------- here

//        setFlag=resumeIds[0];
//        new GetResumeThumbnail().execute();

    }

    private class GetResumeThumbnail extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;

            map = downloadImage(load_resume_thumbnail+"?i="+setFlag);
            return map;
        }

        // Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap result) {
            switch(setFlag)
            {
                case 1:
                    template2.setImageBitmap(result);
                    progress1.setVisibility(View.GONE);
                    break;
                case 2:
                    template1.setImageBitmap(result);
                    progress2.setVisibility(View.GONE);
                    break;
                case 3:
                    template3.setImageBitmap(result);
                    progress3.setVisibility(View.GONE);
                    break;
                case 4:
                    template4.setImageBitmap(result);
                    progress4.setVisibility(View.GONE);
                    break;
            }
            int i=0;
            for(i=0;i<count;i++) {
                if (setFlag == resumeIds[i])
                    break;
            }
            if(i<count-1)
            {
                i=i+1;
                setFlag=resumeIds[i];
                new GetResumeThumbnail().execute();
            }
        }
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }

    class GetResumeIds extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));
            json = jParser.makeHttpRequest(load_resume_ids, "GET", params);
            try {
                String s = json.getString("count");
                count=Integer.parseInt(s);
                Log.d("TAG", "doInBackground: count - "+count);
                resumeIds=new int[count];
                for(int i=0;i<count;i++)
                    resumeIds[i]=Integer.parseInt(json.getString("id"+i));

            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            setThumnails();
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
    private class GetStudentData extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("u", username));
                json = jParser.makeHttpRequest(MyConstants.load_student_data, "GET", params);

                resultofop = json.getString("info");
                if (resultofop.equals("found")) {
                    String s = json.getString("intro");
                    if (s.equals("found")) {
                        found_box1 = 1;
                    }
                    s = json.getString("tenth");
                    if (s.equals("found")) {
                        found_tenth = 1;
                    }
                    s = json.getString("twelth");
                    if (s.equals("found")) {
                        found_twelth = 1;
                    }
                    s = json.getString("diploma");
                    if (s.equals("found")) {
                        found_diploma = 1;
                    }
                    s = json.getString("ug");
                    if (s.equals("found")) {
                        found_ug = 1;
                    }
                    s = json.getString("pgsem");

                    if (s.equals("found")) {
                        found_pgsem = 1;
                    }

                    s = json.getString("pgyear");
                    if (s.equals("found")) {
                        found_pgyear = 1;
                    }

                    s = json.getString("projects");
                    if (s.equals("found")) {
                        found_projects = 1;
                    }
                    s = json.getString("knownlang");
                    if (s.equals("found")) {
                        found_lang = 1;
                    }
                    s = json.getString("certificates");
                    if (s.equals("found")) {
                        found_certificates = 1;
                    }
                    s = json.getString("courses");
                    if (s.equals("found")) {
                        found_courses = 1;
                    }
                    s = json.getString("skills");
                    if (s.equals("found")) {
                        found_skills = 1;
                    }
                    s = json.getString("honors");
                    if (s.equals("found")) {
                        found_honors = 1;
                    }
                    s = json.getString("patents");
                    if (s.equals("found")) {
                        found_patents = 1;
                    }
                    s = json.getString("publications");
                    if (s.equals("found")) {
                        found_publications = 1;
                    }
                    s = json.getString("career");
                    if (s.equals("found")) {
                        found_careerobj = 1;
                    }
                    s = json.getString("strengths");
                    if (s.equals("found")) {
                        found_strengths = 1;
                    }
                    s = json.getString("weaknesses");
                    if (s.equals("found")) {
                        found_weaknesses = 1;
                    }
                    s = json.getString("locationpreferences");
                    if (s.equals("found")) {
                        found_locationpreferences = 1;
                    }
                    s = json.getString("contact_details");
                    if (s.equals("found")) {
                        found_contact_details = 1;
                    }
                    s = json.getString("personal");
                    if (s.equals("found")) {
                        found_personal = 1;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return map;
        }
        @Override
        protected void onPostExecute(Bitmap result) {
//            int found_box1=0,found_tenth=0,found_twelth=0,found_diploma=0,found_ug=0,found_pgsem=0,found_pgyear=0,found_projects=0,found_lang=0,found_certificates=0;
//            int found_courses=0,found_skills=0,found_honors=0,found_patents=0,found_publications=0,found_careerobj=0,found_strengths=0,found_weaknesses=0,found_locationpreferences=0;
//            int found_contact_details=0,found_personal=0;

            if(found_box1==0){
//                    please fill intro information
                Toast.makeText(getActivity(), " please fill introduction information", Toast.LENGTH_SHORT).show();
            }else{
                if(found_tenth==0){
//                        please fill tenth information
                    Toast.makeText(getActivity(), " please fill tenth information", Toast.LENGTH_SHORT).show();

                }
                else{
                    if(found_twelth==0 && found_diploma==0){
//                        please fill twelth or diploma information
                        Toast.makeText(getActivity(), " please fill twelth or diploma information", Toast.LENGTH_SHORT).show();

                    }else{
                        if(found_ug==0){
//                        please fill ug information
                            Toast.makeText(getActivity(), " please fill gradution information", Toast.LENGTH_SHORT).show();

                        }else{
                            if(found_projects==0){
//                        please fill project information
                                Toast.makeText(getActivity(), " please fill project information", Toast.LENGTH_SHORT).show();

                            }else{
                                if(found_lang==0){
//                        please fill language information
                                    Toast.makeText(getActivity(), " please fill language information", Toast.LENGTH_SHORT).show();

                                }else{
                                    if(found_skills==0){
//                        please fill skill information
                                        Toast.makeText(getActivity(), " please fill skill information", Toast.LENGTH_SHORT).show();

                                    }else{
                                        if(found_careerobj==0){
//                        please fill career objective information
                                            Toast.makeText(getActivity(), " please fill career objective information", Toast.LENGTH_SHORT).show();

                                        }else{
                                            if(found_strengths==0){
//                        please fill strength information
                                                Toast.makeText(getActivity(), " please fill strength information", Toast.LENGTH_SHORT).show();

                                            }else{
                                                if(found_weaknesses==0){
//                        please fill weaknesses information
                                                    Toast.makeText(getActivity(), " please fill weaknesses information", Toast.LENGTH_SHORT).show();

                                                }
                                                else{
                                                    if(found_contact_details==0){
//                        please fill contact details information
                                                        Toast.makeText(getActivity(), " please fill contact details information", Toast.LENGTH_SHORT).show();

                                                    }else{
                                                        if(found_personal==0){
//                        please fill personal information
                                                            Toast.makeText(getActivity(), " please fill personal information", Toast.LENGTH_SHORT).show();
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
            if( found_box1 == 1 && found_tenth==1 && (found_diploma==1 || found_twelth==1 )&& found_ug==1 && found_projects==1 && found_lang==1 && found_contact_details==1 && found_skills==1 && found_careerobj==1 && found_strengths==1 && found_weaknesses==1 && found_personal==1){
                downloadresume.setVisibility(View.GONE);
                resumeprogress.setVisibility(View.VISIBLE);
                DownloadManager localDownloadManager = (DownloadManager)getContext().getSystemService(DOWNLOAD_SERVICE);
                Uri uri = new Uri.Builder()
                        .scheme("http")
                        .authority("192.168.100.10")
                        .path("GenerateResumeWithJODConverter3/DownloadResume")
                        .appendQueryParameter("username",username)
                        .appendQueryParameter("format",format)
                        .appendQueryParameter("template",template+"")
                        .build();
                DownloadManager.Request localRequest = new DownloadManager.Request(uri);
                localRequest.setNotificationVisibility(1);
                localDownloadManager.enqueue(localRequest);

                downloadresume.setVisibility(View.VISIBLE);
                resumeprogress.setVisibility(View.GONE);
                Toast.makeText(getContext(),"Downloading Started..",Toast.LENGTH_SHORT).show();
            }
//              else{
//                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//            }

        }
    }
}