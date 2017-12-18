package placeme.octopusites.com.placeme;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.DOWNLOAD_SERVICE;

public class PrintProfileTabFragment extends Fragment {

    private RecyclerView recyclerView;
    private PrintProfileAdapter adapter;
    private List<ResumeTemplateItem> itemList;

    JSONParser jParser = new JSONParser();
    JSONObject json;
    int count=0;
    int template=1;
    int resumeIds[];
    String resumeNames[];
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Username = "nameKey";

    String username,resultofop="";
    RadioGroup radioGroupFormat;
    RadioButton radioButtonWord,radioButtonPdf;
    String format="pdf";
    Button downloadresume;
    View getmoreselectionview;
    TextView getmore,selectformattxt;
    ProgressBar resumeprogress;
    int found_box1=0,found_tenth=0,found_twelth=0,found_diploma=0,found_ug=0,found_pgsem=0,found_pgyear=0,found_projects=0,found_lang=0,found_certificates=0;
    int found_courses=0,found_skills=0,found_honors=0,found_patents=0,found_publications=0,found_careerobj=0,found_strengths=0,found_weaknesses=0,found_locationpreferences=0;
    int found_contact_details=0,found_personal=0;

    int selectedResumeTemplate=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_edit_profile_printprofile, container, false);


        getmore=(TextView)rootView.findViewById(R.id.getmore);
        selectformattxt=(TextView)rootView.findViewById(R.id.selectformattxt);

        getmore.setTypeface(MyConstants.getBold(getActivity()));
        selectformattxt.setTypeface(MyConstants.getLight(getActivity()));
        sharedpreferences=getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username=sharedpreferences.getString(Username,null);
        MySharedPreferencesManager.save(getActivity(),"template",template+"");

        radioGroupFormat=(RadioGroup)rootView.findViewById(R.id.radioGroupFormat);
        radioButtonWord=(RadioButton)rootView.findViewById(R.id.radioButtonWord);
        radioButtonPdf=(RadioButton)rootView.findViewById(R.id.radioButtonPdf);

        radioButtonWord.setTypeface(MyConstants.getBold(getActivity()));
        radioButtonPdf.setTypeface(MyConstants.getBold(getActivity()));

        radioGroupFormat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButtonPdf:
                        format="pdf";
                        break;
                    case R.id.radioButtonWord:
                        format="word";
                        radioButtonPdf.setChecked(true);
//                        Toast.makeText(getActivity(),"Word option is not available right now, but will be available soon.",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);

        itemList = new ArrayList<>();
        adapter = new PrintProfileAdapter(username,itemList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                selectedResumeTemplate=position;
                template= resumeIds[selectedResumeTemplate];
                MySharedPreferencesManager.save(getActivity(),"template",template+"");
                Log.d("TAG", "onClick: radio button  template - "+template);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        downloadresume=(Button)rootView.findViewById(R.id.downloadresume);
        resumeprogress=(ProgressBar)rootView.findViewById(R.id.resumeprogress);
        getmoreselectionview=(View) rootView.findViewById(R.id.getmoreselectionview);

        getmoreselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),GetMoreResumeTemplates.class),0);
            }
        });



        downloadresume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                downloadresume.setVisibility(View.GONE);
                resumeprogress.setVisibility(View.VISIBLE);
                //start downloading resumr
                Log.d("tag","template id -"+resumeIds[selectedResumeTemplate]+"");
                Log.d("tag", "username - : "+username);
                Log.d("tag", "format - : "+format);
//                Log.d("tag","temp id -"+temp);

                new GetStudentData().execute();

            }
        });

        refreshContent();


        return rootView;
    }
    public void refreshContent()
    {
        new GetResumeIds().execute();
    }
    class GetResumeIds extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            itemList.clear();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));
            json = jParser.makeHttpRequest(MyConstants.url_GetMyResumeIds, "GET", params);
            try {
                String s = json.getString("count");
                count=Integer.parseInt(s);
                resumeIds=new int[count];
                resumeNames=new String[count];

                for(int i=0;i<count;i++) {
                    resumeIds[i] = Integer.parseInt(json.getString("id" + i));
                    resumeNames[i] = json.getString("name" + i);
                }


            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {


            ResumeTemplateItem item=null;
            for(int i=0;i<count;i++)
            {
                if(i==0)
                    item=new ResumeTemplateItem(resumeIds[i],MyConstants.IP+"AESTest/GetResumePage?a="+resumeIds[i]+"&b=1",resumeNames[i],"checked");
                else
                    item=new ResumeTemplateItem(resumeIds[i],MyConstants.IP+"AESTest/GetResumePage?a="+resumeIds[i]+"&b=1",resumeNames[i],"unchecked");
                itemList.add(item);
            }
            adapter.notifyDataSetChanged();
        }
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation animation = super.onCreateAnimation(transit, enter, nextAnim);

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

    @Override
    public void onResume() {
        super.onResume();
        IsNewTemplateDownloaded obj=new IsNewTemplateDownloaded();
        if(obj.getIsDownloaded())
        {
            Toast.makeText(getActivity(), "refreshed", Toast.LENGTH_SHORT).show();
            refreshContent();
            obj.setIsDownloaded(false);
        }
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
                        .authority(MyConstants.VPS_IP)
                        .path("GenerateResumeWithJODConverter3/DownloadResume")
                        .appendQueryParameter("username",username)
                        .appendQueryParameter("format",format)
                        .appendQueryParameter("template",resumeIds[selectedResumeTemplate]+"")
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