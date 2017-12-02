package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class AchievementsProfileTabFragment extends Fragment {

    //    String achievements;
//    EditText achievementsedittext;
//    ProgressBar achievementsprogress;
//    Button save;
    String username;
    //    JSONParser jParser = new JSONParser();
//    private static String saveachievementsinfo = "http://192.168.0.6/HandleMobileRequests/SaveAchievementsData";
//    JSONObject json;
//    String resultofop="";
    View knownlang,certifications,courses,skills,honors,patents,publications;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_edit_profile_accomplishments, container, false);


        TextView knownlangtxt=(TextView)rootView.findViewById(R.id.knownlangtxt);
        TextView certitxt=(TextView)rootView.findViewById(R.id.certitxt);
        TextView coursetxt=(TextView)rootView.findViewById(R.id.coursetxt);
        TextView skillstxt=(TextView)rootView.findViewById(R.id.skillstxt);
        TextView honortxt=(TextView)rootView.findViewById(R.id.honortxt);
        TextView patenttxt=(TextView)rootView.findViewById(R.id.patenttxt);
        TextView publicationtxt=(TextView)rootView.findViewById(R.id.publicationtxt);


        knownlangtxt.setTypeface(MyConstants.getBold(getActivity()));
        certitxt.setTypeface(MyConstants.getBold(getActivity()));
        coursetxt.setTypeface(MyConstants.getBold(getActivity()));
        skillstxt.setTypeface(MyConstants.getBold(getActivity()));
        honortxt.setTypeface(MyConstants.getBold(getActivity()));
        patenttxt.setTypeface(MyConstants.getBold(getActivity()));
        publicationtxt.setTypeface(MyConstants.getBold(getActivity()));

        knownlang=(View)rootView.findViewById(R.id.studentBlock);
        skills=(View)rootView.findViewById(R.id.alumniBlock);
        certifications=(View)rootView.findViewById(R.id.certibutton);
        courses=(View)rootView.findViewById(R.id.coursebutton);
        honors=(View)rootView.findViewById(R.id.adminBlock);
        patents=(View)rootView.findViewById(R.id.patentbutton);
        publications=(View)rootView.findViewById(R.id.publicationbutton);

        knownlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileKnownLang.class).putExtra("username",username),0);
            }
        });

        skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileSkills.class).putExtra("username",username),0);
            }
        });
        certifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileCertifications.class).putExtra("username",username),0);
            }
        });
        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileCourses.class).putExtra("username",username),0);
            }
        });
        honors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileAchievements.class).putExtra("username",username),0);
            }
        });
        patents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfilePatents.class).putExtra("username",username),0);
            }
        });
        publications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfilePublications.class).putExtra("username",username),0);

            }
        });





//        achievementsedittext=(EditText)rootView.findViewById(R.id.achievements);
//        save=(Button)rootView.findViewById(R.id.saveachievements);
//        achievementsprogress=(ProgressBar)rootView.findViewById(R.id.achievementsprogress);
//
//
//        SaveProfileInfo s=new SaveProfileInfo();
//        username=s.getUsername();
//        achievements=s.getAchievements();
//
//        achievementsedittext.setText(achievements);
//
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                save.setVisibility(View.GONE);
//                achievementsprogress.setVisibility(View.VISIBLE);
//
//                achievements=achievementsedittext.getText().toString();
//
//                new MyAsyncTask().execute();
//            }
//        });


        return rootView;
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
//    class MyAsyncTask extends AsyncTask<String, String, String> {
//
//
//        protected String doInBackground(String... param) {
//
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//
//            params.add(new BasicNameValuePair("username",username));
//            params.add(new BasicNameValuePair("achievements",achievements));
//           json = jParser.makeHttpRequest(saveachievementsinfo, "GET", params);
//            try {
//                resultofop = json.getString("info");
//
//
//            }catch (Exception e){e.printStackTrace();}
//            return "";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//            if(resultofop.equals("success"))
//                Toast.makeText(getContext(),"Successfully Updated..!",Toast.LENGTH_SHORT).show();
//            else
//                Toast.makeText(getContext(),"Failed..!",Toast.LENGTH_SHORT).show();
//
//            save.setVisibility(View.VISIBLE);
//            achievementsprogress.setVisibility(View.GONE);
//        }
//    }
}