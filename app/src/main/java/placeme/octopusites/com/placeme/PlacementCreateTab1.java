package placeme.octopusites.com.placeme;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import mabbas007.tagsedittext.TagsEditText;
import placeme.octopusites.com.placeme.modal.RecyclerItemPlacement;


public class PlacementCreateTab1 extends Fragment implements TagsEditText.TagsEditListener {

    public ArrayList<String> TagCreateList = new ArrayList<>();
    AutoCompleteTextView courses, streams;
    TagsEditText selected;
    ArrayList<String> listAll = new ArrayList<String>();
    TextInputLayout companynameinput, packageinput, postinput, vacanciesinput, ldrinput, dorinput, bondinput, CoursecInput, streaminput;
    ArrayList<String> tosettoStreamslist = new ArrayList<>();
    ArrayList<String> tosettoCourseslist = new ArrayList<>();
    String TAG = "PlacementCreateTab1";


    String[] CourseListWithIds, CourseList;
    String[] StramsListWithIds, StramsList, tempStramsList;

    //ui
    EditText companyname, cpackage, post, vacancies, lastdateofrr, dateofarrival, bond;
    String sid, scompanyname = "", scpackage = "", spost = "", svacancies = "", slastdateofrr = "", sdateofarrival = "", sbond = "", sselected = "";

    //flags
    int datepicker1 = 0;
    int Erroflag = 0;
    boolean edittedFlag = false;

    RelativeLayout courseselector, Streamselector, selectedrl;
    View rootView;
    InputMethodManager inputManager;
    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            if (datepicker1 == 1) {
                lastdateofrr.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear + 1)
                        + "/" + String.valueOf(year));
            }
            if (datepicker1 == 2) {
                dateofarrival.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear + 1)
                        + "/" + String.valueOf(year));
            }


        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.create_placement_tab1, container, false);

        companynameinput = (TextInputLayout) rootView.findViewById(R.id.companynameinput);
        packageinput = (TextInputLayout) rootView.findViewById(R.id.packageinput);
        postinput = (TextInputLayout) rootView.findViewById(R.id.postinput);
        vacanciesinput = (TextInputLayout) rootView.findViewById(R.id.vacanciesinput);
        ldrinput = (TextInputLayout) rootView.findViewById(R.id.ldrinput);
        dorinput = (TextInputLayout) rootView.findViewById(R.id.dorinput);
        bondinput = (TextInputLayout) rootView.findViewById(R.id.bondinput);
        CoursecInput = (TextInputLayout) rootView.findViewById(R.id.CoursecInput);
        streaminput = (TextInputLayout) rootView.findViewById(R.id.streaminput);


        companynameinput.setTypeface(Z.getLight(getActivity()));
        packageinput.setTypeface(Z.getLight(getActivity()));
        postinput.setTypeface(Z.getLight(getActivity()));
        vacanciesinput.setTypeface(Z.getLight(getActivity()));
        ldrinput.setTypeface(Z.getLight(getActivity()));
        dorinput.setTypeface(Z.getLight(getActivity()));
        bondinput.setTypeface(Z.getLight(getActivity()));
        CoursecInput.setTypeface(Z.getLight(getActivity()));
        streaminput.setTypeface(Z.getLight(getActivity()));

        TextView choosetxt = (TextView) rootView.findViewById(R.id.choosetxt);
        choosetxt.setTypeface(Z.getBold(getActivity()));
        inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);


        courseselector = (RelativeLayout) rootView.findViewById(R.id.courseselector);
        Streamselector = (RelativeLayout) rootView.findViewById(R.id.Streamselector);

        courses = (AutoCompleteTextView) rootView.findViewById(R.id.courses);
        streams = (AutoCompleteTextView) rootView.findViewById(R.id.streams);
        selected = (TagsEditText) rootView.findViewById(R.id.selected);
        selectedrl = (RelativeLayout) rootView.findViewById(R.id.selectedrl);


        //tags
        companyname = (EditText) rootView.findViewById(R.id.companyname);
        cpackage = (EditText) rootView.findViewById(R.id.cpackage);
        post = (EditText) rootView.findViewById(R.id.post);
        vacancies = (EditText) rootView.findViewById(R.id.vacancies);
        lastdateofrr = (EditText) rootView.findViewById(R.id.ldr);   //date picker
        dateofarrival = (EditText) rootView.findViewById(R.id.dor);      //date picker
        bond = (EditText) rootView.findViewById(R.id.bond);
        companyname.setTypeface(Z.getBold(getActivity()));
        cpackage.setTypeface(Z.getBold(getActivity()));
        post.setTypeface(Z.getBold(getActivity()));
        vacancies.setTypeface(Z.getBold(getActivity()));
        lastdateofrr.setTypeface(Z.getBold(getActivity()));
        dateofarrival.setTypeface(Z.getBold(getActivity()));
        bond.setTypeface(Z.getBold(getActivity()));

        companyname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companynameinput.setError(null);
                edittedFlag = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        cpackage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                packageinput.setError(null);
                edittedFlag = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        post.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                postinput.setError(null);
                edittedFlag = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        vacancies.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                vacanciesinput.setError(null);
                edittedFlag = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        lastdateofrr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ldrinput.setError(null);
                edittedFlag = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dateofarrival.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dorinput.setError(null);
                edittedFlag = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        bond.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bondinput.setError(null);
                edittedFlag = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        CourseListWithIds = getResources().getStringArray(R.array.courses);
        CourseList = new String[CourseListWithIds.length];


        for (int i = 0; i < CourseListWithIds.length; i++) {
            String temp[] = CourseListWithIds[i].split(",");
            CourseList[i] = temp[1];
            Log.d("TAG", "Course without ID: " + CourseList[i]);

        }


        StramsListWithIds = getResources().getStringArray(R.array.streams);
        StramsList = new String[StramsListWithIds.length];
        for (int i = 0; i < StramsListWithIds.length; i++) {
            String temp[] = StramsListWithIds[i].split(",");
            StramsList[i] = temp[1];
            Log.d("TAG", "streams without ID: " + StramsList[i]);

        }

        ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList(CourseList));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, CourseList) {

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
        courses.setAdapter(dataAdapter);
        courses.setThreshold(1);
        streams.setThreshold(1);
        streams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                streams.showDropDown();
            }
        });

        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                courses.showDropDown();
            }
        });

        courses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                CoursecInput.setError(null);
                String toCompare = courses.getText().toString();

                int index = 0;
                for (int i = 0; i < CourseList.length; i++) {
                    if (CourseList[i].equals(toCompare))
                        index = i;

                }
                toCompare = CourseListWithIds[index];
                Log.d("TAG", "toCompare : " + toCompare);
                tosettoStreamslist.clear();

                setStreamAdapter(toCompare);
//                selectedrl

//                setStreamAdapter();

            }
        });
        streams.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                CreateTags();

            }
        });


        selected.setTagsWithSpacesEnabled(true);
        selected.setTagsListener(new TagsEditText.TagsEditListener() {
            @Override
            public void onTagsChanged(Collection<String> collection) {

                TagCreateList.clear();
                List<String> newList = new ArrayList<String>(selected.getTags());
                List<String> newList2 = new ArrayList<String>(selected.getTags());
                TagCreateList.addAll(newList);
                String temp = "";
                temp = selected.getText().toString();
                Log.d("tag", "onTagsChanged: " + temp);
                if (temp.equals("")) {
                    selectedrl.setVisibility(View.GONE);
                }


            }

            @Override
            public void onEditingFinished() {

            }
        });


//datepickers
        lastdateofrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker1 = 1;
                showDatePicker();

            }
        });
        dateofarrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker1 = 2;
                showDatePicker();

            }
        });


//        String scompanyname="",scpackage="",spost="",svacancies="",slastdateofrr="",sdateofarrival="",sbond="",sselected="";
//        companyname=(EditText)rootView.findViewById(R.id.companyname);
//        cpackage=(EditText)rootView.findViewById(R.id.cpackage);
//        post=(EditText)rootView.findViewById(R.id.post);
//        vacancies=(EditText)rootView.findViewById(R.id.vacancies);
//        lastdateofrr=(EditText)rootView.findViewById(R.id.ldr);   //date picker
//        dateofarrival=(EditText)rootView.findViewById(R.i

        PlacementEditData getdata = new PlacementEditData();

        String activitytag = getdata.getActivityFromtag();
        Log.d("activitytag", "onCreateView: " + activitytag);
        if (activitytag.contains("EditPlacement")) {
            sid = getdata.getId();
            scompanyname = getdata.getCompanyname();
            scpackage = getdata.getCpackage();
            spost = getdata.getPost();
            svacancies = getdata.getVacancies();
            slastdateofrr = getdata.getLastdateofregistration();
            sdateofarrival = getdata.getDateofarrival();
            sbond = getdata.getBond();
            sselected = getdata.getSelectedcourceforplacement();

            if (scpackage.contains("LPA")) {
                scpackage = scpackage.replace("LPA", "");
            }

            if (scompanyname.length() > 0) {
                companyname.setText(scompanyname);
            }
            if (scpackage.length() > 0) {
                cpackage.setText(scpackage);
            }
            if (spost.length() > 0) {
                post.setText(spost);
            }
            if (svacancies.length() > 0) {
                vacancies.setText(svacancies);
            }
            if (slastdateofrr.length() > 0) {
                lastdateofrr.setText(slastdateofrr);
            }
            if (sdateofarrival.length() > 0) {
                dateofarrival.setText(sdateofarrival);
            }
            if (sbond.length() > 0) {
                bond.setText(sbond);
            }
            if (sselected.length() > 0) {
                selectedrl.setVisibility(View.VISIBLE);
                selected.setText(sselected);
            }
        }
        if (activitytag.contains("HrActivityEdit")) {
            sid = getdata.getId();
            scompanyname = getdata.getCompanyname();
            scpackage = getdata.getCpackage();
            spost = getdata.getPost();
            svacancies = getdata.getVacancies();
            slastdateofrr = getdata.getLastdateofregistration();
            sdateofarrival = getdata.getDateofarrival();
            sbond = getdata.getBond();
            sselected = getdata.getSelectedcourceforplacement();

            if (scpackage.contains("LPA")) {
                scpackage = scpackage.replace("LPA", "");
            }

            if (scompanyname.length() > 0) {
                companyname.setText(scompanyname);
            }
            if (scpackage.length() > 0) {
                cpackage.setText(scpackage);
            }
            if (spost.length() > 0) {
                post.setText(spost);
            }
            if (svacancies.length() > 0) {
                vacancies.setText(svacancies);
            }
            if (slastdateofrr.length() > 0) {
                lastdateofrr.setText(slastdateofrr);
            }
            if (sdateofarrival.length() > 0) {
                dateofarrival.setText(sdateofarrival);
            }
            if (sbond.length() > 0) {
                bond.setText(sbond);
            }
            if (sselected.length() > 0) {
                selectedrl.setVisibility(View.VISIBLE);
                selected.setText(sselected);
            }
        }

        edittedFlag = false;
        return rootView;
    }

    public boolean isTabEditted() {

        return edittedFlag;
    }

    String setStreamAdapter(String a) {
        try {

            String courseArray[] = a.split(",");
            int courseIndex = Integer.parseInt(courseArray[0]);
            Log.d("TAG", "courseIndex: " + courseIndex);

            int index = 0;

            for (int i = 0; i < StramsListWithIds.length; i++) {
                if (StramsListWithIds[i].contains("" + courseIndex)) {
                    String splitedStream[] = StramsListWithIds[i].split(",");
                    if (splitedStream[0].equals("" + courseIndex)) {
                        tosettoStreamslist.add(splitedStream[1]);
                        splitedStream = null;
                    }
                }
            }
            tempStramsList = tosettoStreamslist.toArray(new String[0]);
            if (tempStramsList != null && tempStramsList.length > 0) {
                Streamselector.setVisibility(View.VISIBLE);

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, tempStramsList) {

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
                streams.setAdapter(dataAdapter2);
                Streamselector.setVisibility(View.VISIBLE);
            } else {
                tosettoStreamslist.clear();
                String sCources = courses.getText().toString();
                TagCreateList.add(sCources);
                String[] TagCreateArray = new String[TagCreateList.size()];
                TagCreateArray = TagCreateList.toArray(TagCreateArray);
                selectedrl.setVisibility(View.VISIBLE);
                selected.setTags(TagCreateArray);
                Streamselector.setVisibility(View.GONE);
                courses.setText("");
                streams.setText("");
            }

        } catch (Exception e) {
        }
        return null;
    }

    String CreateTags() {
        String sCources = courses.getText().toString();
        String sStrams = streams.getText().toString();
        String ToDisplay = sCources + " " + sStrams;
        TagCreateList.clear();
        List<String> newList = new ArrayList<String>(selected.getTags());
        TagCreateList.addAll(newList);
        TagCreateList.add(ToDisplay);
//        String[] TagCreateArray;
        String[] TagCreateArray = new String[TagCreateList.size()];
        TagCreateArray = TagCreateList.toArray(TagCreateArray);
        selectedrl.setVisibility(View.VISIBLE);
        selected.setTags(TagCreateArray);
        streams.setText("");
        return null;

    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        args.putString("validate", "min");
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
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

    @Override
    public void onTagsChanged(Collection<String> collection) {

    }

    @Override
    public void onEditingFinished() {

    }

    public Boolean SetFeilds() {
        companyname.setText("Hello");
        return true;
    }

    public Boolean Validate() {


        scompanyname = companyname.getText().toString();
        scpackage = cpackage.getText().toString();
        spost = post.getText().toString();
        svacancies = vacancies.getText().toString();
        slastdateofrr = lastdateofrr.getText().toString();
        sdateofarrival = dateofarrival.getText().toString();
        sbond = bond.getText().toString();
        sselected = selected.getText().toString();
        Log.d("TAG", "scompanyname:" + scompanyname);
        Log.d("TAG", "scpackage:" + scpackage);
        Log.d("TAG", "spost:" + spost);
        Log.d("TAG", "svacancies:" + svacancies);
        Log.d("TAG", "slastdateofrr:" + slastdateofrr);
        Log.d("TAG", "sdateofarrival:" + sdateofarrival);
        Log.d("TAG", "sbond:" + sbond);
        Log.d("TAG", "sselected:" + sselected);


        Erroflag = 0;

        if (scompanyname.length() < 2) {
            companynameinput.setError("Kindly enter valid company name");
            Erroflag = 1;

        } else if (scpackage.length() < 1) {
            packageinput.setError("Kindly enter valid package");
            Log.d("TAG", "This scpackage block is executed");
            Erroflag = 1;

        } else if (spost.length() < 2) {
            postinput.setError("Kindly enter valid post");
            Log.d("TAG", "This postinput block is executed");
            Erroflag = 1;

        } else if (sselected.length() < 1) {
            CoursecInput.setError("Kindly enter course and stream");
            Log.d("TAG", "This course block is executed");
            Erroflag = 1;

        } else if (svacancies.length() < 1) {
            vacanciesinput.setError("Kindly enter number of vacancies");
            Log.d("TAG", "This svacancies block is executed");
            Erroflag = 1;

        } else if (slastdateofrr.length() < 1) {
            ldrinput.setError("Kindly enter valid last date of registration");
            Log.d("TAG", "This slastdateofrr block is executed");
            Erroflag = 1;

        } else if (sdateofarrival.length() < 1) {
            dorinput.setError("Kindly enter valid date of arrival");
            Log.d("TAG", "This sdateofarrival block is executed");
            Erroflag = 1;

        } else if (sbond.length() < 1) {
            bondinput.setError("Kindly enter bond in months");
            Log.d("TAG", "This sdateofarrival block is executed");
            Erroflag = 1;

        }
        if (Erroflag == 0) {
            lastdateofrr.setError(null);
            dateofarrival.setError(null);
            return true;

        } else {
            return false;

        }
    }

    public void datasettest1() {
        companyname = (EditText) rootView.findViewById(R.id.companyname);
        companyname.setText("hello");
    }

    public void datasetters(RecyclerItemPlacement obj) {

        scompanyname = obj.getCompanyname();
        scpackage = obj.getCpackage();

        spost = obj.getPost();
        svacancies = obj.getVacancies();
        slastdateofrr = obj.getLastdateofregistration();
        sdateofarrival = obj.getDateofarrival();
        sbond = obj.getBond();
        sselected = obj.getForwhichcourse();

        Log.d("Tags", "datasetters: " + scompanyname);
        Log.d("Tags", "datasetters: " + scpackage);
        Log.d("Tags", "datasetters: " + spost);
        Log.d("Tags", "datasetters: " + svacancies);
        Log.d("Tags", "datasetters: " + slastdateofrr);
        Log.d("Tags", "datasetters: " + sdateofarrival);
        Log.d("Tags", "datasetters: " + sbond);
        Log.d("Tags", "datasetters: " + sselected);

        if (scompanyname.length() > 0) {
            companyname.setText(scompanyname);
        }
        if (scpackage.length() > 0) {
            cpackage.setText(scpackage);
        }
        if (spost.length() > 0) {
            post.setText(spost);
        }
        if (svacancies.length() > 0) {
            vacancies.setText(svacancies);
        }
        if (slastdateofrr.length() > 0) {
            lastdateofrr.setText(slastdateofrr);
        }
        if (sdateofarrival.length() > 0) {
            dateofarrival.setText(sdateofarrival);
        }
        if (sbond.length() > 0) {
            bond.setText(sbond);
        }
        if (sselected.length() > 0) {
            selected.setText(sselected);
        }
        Log.d("TAG", "datasetters: " + obj.getId());


    }
}
