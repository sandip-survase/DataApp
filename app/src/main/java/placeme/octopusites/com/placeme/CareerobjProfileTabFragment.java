package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class CareerobjProfileTabFragment extends Fragment {

    //    RadioGroup radioGroupCareerobj;
//    RadioButton radioButtonObj1,radioButtonObj2,radioButtonObj3,radioButtonObj4,radioButtonObj5,radioButtonObj6;
//    TextInputEditText otherobjedittext,otherstredittext,otherweakedittext,loc1edittext,loc2edittext;
//    CheckBox CheckBoxStr1,CheckBoxStr2,CheckBoxStr3,CheckBoxStr4,CheckBoxStr5,CheckBoxStr6,CheckBoxStr7,CheckBoxOtherStr;
//    CheckBox CheckBoxWeak1,CheckBoxWeak2,CheckBoxWeak3,CheckBoxWeak4,CheckBoxWeak5,CheckBoxWeak6,CheckBoxWeak7,CheckBoxOtherWeak;
//    String username,careerobj,strengths,weaknesses,location1,location2;
//    JSONParser jParser = new JSONParser();
//    JSONObject json;
//    String resultofop="";
//    ProgressBar careerobjprogress;
//    Button save;
    View careerobjbutton,strengthbutton,weakbutton,locbutton;
    String username;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_edit_profile_careerdetails, container, false);

//        radioGroupCareerobj=(RadioGroup)rootView.findViewById(R.id.radioGroupCareerobj);
//
//        radioButtonObj1=(RadioButton)rootView.findViewById(R.id.radioButtonObj1);
//        radioButtonObj2=(RadioButton)rootView.findViewById(R.id.radioButtonObj2);
//        radioButtonObj3=(RadioButton)rootView.findViewById(R.id.radioButtonObj3);
//        radioButtonObj4=(RadioButton)rootView.findViewById(R.id.radioButtonObj4);
//        radioButtonObj5=(RadioButton)rootView.findViewById(R.id.radioButtonObj5);
//        radioButtonObj6=(RadioButton)rootView.findViewById(R.id.radioButtonObj6);
//
//        otherobjedittext=(TextInputEditText)rootView.findViewById(R.id.otherObj);
//        otherstredittext=(TextInputEditText)rootView.findViewById(R.id.otherstr);
//        otherweakedittext=(TextInputEditText)rootView.findViewById(R.id.otherweak);
//        loc1edittext=(TextInputEditText)rootView.findViewById(R.id.loc1);
//        loc2edittext=(TextInputEditText)rootView.findViewById(R.id.loc2);
//
//        CheckBoxStr1=(CheckBox)rootView.findViewById(R.id.CheckBoxStr1);
//        CheckBoxStr2=(CheckBox)rootView.findViewById(R.id.CheckBoxStr2);
//        CheckBoxStr3=(CheckBox)rootView.findViewById(R.id.CheckBoxStr3);
//        CheckBoxStr4=(CheckBox)rootView.findViewById(R.id.CheckBoxStr4);
//        CheckBoxStr5=(CheckBox)rootView.findViewById(R.id.CheckBoxStr5);
//        CheckBoxStr6=(CheckBox)rootView.findViewById(R.id.CheckBoxStr6);
//        CheckBoxStr7=(CheckBox)rootView.findViewById(R.id.CheckBoxStr7);
//        CheckBoxOtherStr=(CheckBox)rootView.findViewById(R.id.CheckBoxOtherStr);
//
//        CheckBoxWeak1=(CheckBox)rootView.findViewById(R.id.CheckBoxWeak1);
//        CheckBoxWeak2=(CheckBox)rootView.findViewById(R.id.CheckBoxWeak2);
//        CheckBoxWeak3=(CheckBox)rootView.findViewById(R.id.CheckBoxWeak3);
//        CheckBoxWeak4=(CheckBox)rootView.findViewById(R.id.CheckBoxWeak4);
//        CheckBoxWeak5=(CheckBox)rootView.findViewById(R.id.CheckBoxWeak5);
//        CheckBoxWeak6=(CheckBox)rootView.findViewById(R.id.CheckBoxWeak6);
//        CheckBoxWeak7=(CheckBox)rootView.findViewById(R.id.CheckBoxWeak7);
//        CheckBoxOtherWeak=(CheckBox)rootView.findViewById(R.id.CheckBoxOtherWeak);

//        save=(Button)rootView.findViewById(R.id.savecareerobj);
//        careerobjprogress=(ProgressBar)rootView.findViewById(R.id.careerobjprogress);
//
//
//        radioGroupCareerobj.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//
//                switch(i) {
//
//                    case R.id.radioButtonObj1:
//                        careerobj="To secure a promising position in the company of repute that offers both a challenging assignments and ample opportunity for growth in terms of due recognition and added responsibilities.";
//                        otherobjedittext.setFocusable(false);
//                        otherobjedittext.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
//                        otherobjedittext.setClickable(false);
//                        break;
//                    case R.id.radioButtonObj2:
//                        careerobj="To work with the company, belonging to professionally managed group, that is offering enough opportunities for career advancement and professional growth of an individual.";
//                        otherobjedittext.setFocusable(false);
//                        otherobjedittext.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
//                        otherobjedittext.setClickable(false);
//                        break;
//                    case R.id.radioButtonObj3:
//                        careerobj="To work in a stimulating work culture and environment where I am allowed to freely apply the management principles learned to the best interest of the company and gain rich experience thereby enhancing my knowledge.";
//                        otherobjedittext.setFocusable(false);
//                        otherobjedittext.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
//                        otherobjedittext.setClickable(false);
//                        break;
//                    case R.id.radioButtonObj4:
//                        careerobj="To achieve success in competitive environment of the corporate world and making a mark by my hard work, passion for the job and strong will to fulfill management expectations following ethical values.";
//                        otherobjedittext.setFocusable(false);
//                        otherobjedittext.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
//                        otherobjedittext.setClickable(false);
//                        break;
//                    case R.id.radioButtonObj5:
//                        careerobj="To represent myself as one of the important members of the team and driving my team towards fulfillment of company?s goals and objectives and make long lasting impression of my team on senior management.";
//                        otherobjedittext.setFocusable(false);
//                        otherobjedittext.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
//                        otherobjedittext.setClickable(false);
//                        break;
//                    case R.id.radioButtonObj6:
//                        otherobjedittext.setFocusable(true);
//                        otherobjedittext.setFocusableInTouchMode(true); // user touches widget on phone with touch screen
//                        otherobjedittext.setClickable(true);
//                        break;
//
//                }
//            }
//        });
//

        TextView careerobjtxt=(TextView)rootView.findViewById(R.id.careerobjtxt);
        TextView strengthtxt=(TextView)rootView.findViewById(R.id.strengthtxt);
        TextView weaktxt=(TextView)rootView.findViewById(R.id.weaktxt);
        TextView locationtxt=(TextView)rootView.findViewById(R.id.locationtxt);

        careerobjtxt.setTypeface(Z.getBold(getActivity()));
        strengthtxt.setTypeface(Z.getBold(getActivity()));
        weaktxt.setTypeface(Z.getBold(getActivity()));
        locationtxt.setTypeface(Z.getBold(getActivity()));

        careerobjbutton=(View)rootView.findViewById(R.id.careerobjbutton);
        strengthbutton=(View)rootView.findViewById(R.id.strengthbutton);
        weakbutton=(View)rootView.findViewById(R.id.weakbutton);
        locbutton=(View)rootView.findViewById(R.id.locbutton);

        careerobjbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileCareerObj.class).putExtra("username",username),0);
            }
        });
        strengthbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileStrengths.class).putExtra("username",username),0);

            }
        });
        weakbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileWeaknesses.class).putExtra("username",username),0);
            }
        });
        locbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileLocationPreferences.class).putExtra("username",username),0);
            }
        });




//        careerobj=s.getCareerobj();
//        strengths=s.getStrengths();
//        weaknesses=s.getWeaknesses();
//        location1=s.getLocation1();
//        location2=s.getLocation2();
//
////        loc1edittext.setText(location1);
////        loc2edittext.setText(location2);
//
//        if(careerobj.contains("To secure a promising position in the company of repute that offers both a challenging assignments and ample opportunity for growth in terms of due recognition and added responsibilities."))
//            radioButtonObj1.setChecked(true);
//        else if(careerobj.contains("To work with the company, belonging to professionally managed group, that is offering enough opportunities for career advancement and professional growth of an individual."))
//            radioButtonObj2.setChecked(true);
//        else if(careerobj.contains("To work in a stimulating work culture and environment where I am allowed to freely apply the management principles learned to the best interest of the company and gain rich experience thereby enhancing my knowledge."))
//            radioButtonObj3.setChecked(true);
//        else if(careerobj.contains("To achieve success in competitive environment of the corporate world and making a mark by my hard work, passion for the job and strong will to fulfill management expectations following ethical values."))
//            radioButtonObj4.setChecked(true);
//        else if(careerobj.contains("To represent myself as one of the important members of the team and driving my team towards fulfillment of company?s goals and objectives and make long lasting impression of my team on senior management."))
//            radioButtonObj5.setChecked(true);
//        else {
//            radioButtonObj6.setChecked(true);
//            otherobjedittext.setText(careerobj);
//        }

//        if(strengths.contains("Team Builder"))
//            CheckBoxStr1.setChecked(true);
//        if(strengths.contains("Leadership Qualities"))
//            CheckBoxStr2.setChecked(true);
//        if(strengths.contains("Quick Learner"))
//            CheckBoxStr3.setChecked(true);
//        if(strengths.contains("Adaptable"))
//            CheckBoxStr4.setChecked(true);
//        if(strengths.contains("Good at Number Crunching"))
//            CheckBoxStr5.setChecked(true);
//        if(strengths.contains("Can Work Under Pressure"))
//            CheckBoxStr6.setChecked(true);
//        if(strengths.contains("Meetings The Deadlines"))
//            CheckBoxStr7.setChecked(true);
//        if(strengths.contains("other"))
//        {
//            CheckBoxOtherStr.setChecked(true);
//            String str=strengths,other="";
//            int index1=str.indexOf("other");
//            for(int j=index1+5;j<str.length();j++)
//                {
//                    other+=str.charAt(j);
//                }
//            otherstredittext.setText(other);
//        }
//
//
//
//        if(weaknesses.contains("Impatient & Highly Sensitive"))
//            CheckBoxWeak1.setChecked(true);
//        if(weaknesses.contains("Going Too Much In Details"))
//            CheckBoxWeak2.setChecked(true);
//        if(weaknesses.contains("Rigid & Stick To My Viewpoint"))
//            CheckBoxWeak3.setChecked(true);
//        if(weaknesses.contains("Not Comfortable Working In Team"))
//            CheckBoxWeak4.setChecked(true);
//        if(weaknesses.contains("Missing Timeliness"))
//            CheckBoxWeak5.setChecked(true);
//        if(weaknesses.contains("Lose Temper Quickly"))
//            CheckBoxWeak6.setChecked(true);
//        if(weaknesses.contains("Arrogant, Argumentative & Stubborn"))
//            CheckBoxWeak7.setChecked(true);
//        if(weaknesses.contains("other"))
//            {
//                CheckBoxOtherWeak.setChecked(true);
//                String str=weaknesses,other="";
//                int index1=str.indexOf("other");
//                for(int j=index1+5;j<str.length();j++)
//                {
//                    other+=str.charAt(j);
//                }
//                otherweakedittext.setText(other);
//            }

//        if(!radioButtonObj6.isChecked()) {
//            otherobjedittext.setFocusable(false);
//            otherobjedittext.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
//            otherobjedittext.setClickable(false);
//        }
//        if(!CheckBoxOtherStr.isChecked()) {
//            otherstredittext.setFocusable(false);
//            otherstredittext.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
//            otherstredittext.setClickable(false);
//        }
//        if(!CheckBoxOtherWeak.isChecked()) {
//            otherweakedittext.setFocusable(false);
//            otherweakedittext.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
//            otherweakedittext.setClickable(false);
//        }
//
//        CheckBoxOtherStr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
//
//            if(isChecked)
//            {
//                otherstredittext.setFocusable(true);
//                otherstredittext.setFocusableInTouchMode(true); // user touches widget on phone with touch screen
//                otherstredittext.setClickable(true);
//            }
//            else
//            {
//                otherstredittext.setFocusable(false);
//                otherstredittext.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
//                otherstredittext.setClickable(false);
//            }
//
//                }
//            }
//        );
//
//        CheckBoxOtherWeak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
//                    if(isChecked)
//                        {
//                            otherweakedittext.setFocusable(true);
//                            otherweakedittext.setFocusableInTouchMode(true); // user touches widget on phone with touch screen
//                            otherweakedittext.setClickable(true);
//                        }
//                    else
//                        {
//                            otherweakedittext.setFocusable(false);
//                            otherweakedittext.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
//                            otherweakedittext.setClickable(false);
//                        }
//
//                }
//                }
//        );

//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String strength="",weak="";
//
//                save.setVisibility(View.GONE);
//                careerobjprogress.setVisibility(View.VISIBLE);
//
//                String str=otherobjedittext.getText().toString();
//                if(!str.equals(""))
//                    careerobj=str;

//                if(CheckBoxStr1.isChecked())
//                    strength+="Team Builder,";
//                if(CheckBoxStr2.isChecked())
//                    strength+=",Leadership Qualities,";
//                if(CheckBoxStr3.isChecked())
//                    strength+=",Quick Learner,";
//                if(CheckBoxStr4.isChecked())
//                    strength+=",Adaptable,";
//                if(CheckBoxStr5.isChecked())
//                    strength+=",Good at Number Crunching,";
//                if(CheckBoxStr6.isChecked())
//                    strength+=",Can Work Under Pressure,";
//                if(CheckBoxStr7.isChecked())
//                    strength+=",Meetings The Deadlines,";
//                if(CheckBoxOtherStr.isChecked())
//                    strength+=",other"+otherstredittext.getText().toString()+",";
//
//                if(CheckBoxWeak1.isChecked())
//                    weak+="Impatient & Highly Sensitive,";
//                if(CheckBoxWeak2.isChecked())
//                    weak+=",Going Too Much In Details,";
//                if(CheckBoxWeak3.isChecked())
//                    weak+=",Rigid & Stick To My Viewpoint,";
//                if(CheckBoxWeak4.isChecked())
//                    weak+=",Not Comfortable Working In Team,";
//                if(CheckBoxWeak5.isChecked())
//                    weak+=",Missing Timeliness,";
//                if(CheckBoxWeak6.isChecked())
//                    weak+=",Lose Temper Quickly,";
//                if(CheckBoxWeak7.isChecked())
//                    weak+=",Arrogant, Argumentative & Stubborn,";
//                if(CheckBoxOtherWeak.isChecked())
//                    weak+=",other"+otherweakedittext.getText().toString()+",";
//
//                char strarray[]=strength.toCharArray();
//                if(strarray[0]==',')
//                    strarray[0]='\u0000';
//
//                for(int i=0;i<strarray.length-1;i++)
//                {
//                    int index1=i;
//                    int index2=i+1;
//                    if(strarray[index1]==','&&strarray[index2]==',')
//                    {
//                        strarray[index2]=' ';
//                    }
//                }
//                String finalstrenghth="";
//                for(int i=0;i<strarray.length-1;i++)
//                    finalstrenghth+=strarray[i];
//
//                char waekarray[]=weak.toCharArray();
//                if(waekarray[0]==',')
//                    waekarray[0]='\u0000';
//                for(int i=0;i<waekarray.length-1;i++)
//                {
//                    int index1=i;
//                    int index2=i+1;
//                    if(waekarray[index1]==','&&waekarray[index2]==',')
//                    {
//                        waekarray[index2]=' ';
//                    }
//                }
//                String finalweak="";
//                for(int i=0;i<waekarray.length-1;i++)
//                    finalweak+=waekarray[i];
//
//
//
//                strengths=finalstrenghth;
//                weaknesses=finalweak;
//
//
//                location1=loc1edittext.getText().toString();
//                location2=loc2edittext.getText().toString();
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
//            params.add(new BasicNameValuePair("username",username));
//            params.add(new BasicNameValuePair("careerobj",careerobj));
////            params.add(new BasicNameValuePair("strengths",strengths));
////            params.add(new BasicNameValuePair("weaknesses",weaknesses));
////            params.add(new BasicNameValuePair("location1",location1));
////            params.add(new BasicNameValuePair("location2",location2));
//            json = jParser.makeHttpRequest(savecareerobjinfo, "GET", params);
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
//            careerobjprogress.setVisibility(View.GONE);
//        }
//    }
}