package placeme.octopusites.com.placeme;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
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

    private RecyclerView recyclerView;
    private PrintProfileAdapter adapter;
    private List<ResumeTemplateItem> itemList;

    JSONParser jParser = new JSONParser();
    JSONObject json;
    int count=0;
    int resumeIds[];
    String resumeNames[];

    private static String load_resume_ids = "http://192.168.100.100/AESTest/GetMyResumeIds";
    String username;
    RadioGroup radioGroupFormat;
    RadioButton radioButtonWord,radioButtonPdf;
    String format="PDF";
    Button downloadresume;
    View getmoreselectionview;
    ProgressBar resumeprogress;

    int selectedResumeTemplate=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_edit_profile_printprofile, container, false);

        username=MySharedPreferencesManager.getUsername(getActivity());

        radioGroupFormat=(RadioGroup)rootView.findViewById(R.id.radioGroupFormat);
        radioButtonWord=(RadioButton)rootView.findViewById(R.id.radioButtonWord);
        radioButtonPdf=(RadioButton)rootView.findViewById(R.id.radioButtonPdf);

        radioGroupFormat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButtonPdf:
                        format="PDF";
                        break;
                    case R.id.radioButtonWord:
                        format="WORD";
                        radioButtonPdf.setChecked(true);
                        Toast.makeText(getActivity(),"Word option is not available right now, but will be available soon.",Toast.LENGTH_LONG).show();
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

//                DownloadManager localDownloadManager = (DownloadManager)getContext().getSystemService(DOWNLOAD_SERVICE);
//                Uri uri = new Uri.Builder()
//                        .scheme("http")
//                        .authority("192.168.100.100")
//                        .path("AESTest/DownloadResume")
//                        .appendQueryParameter("u", username)
//                        .appendQueryParameter("f", format)
//                        .appendQueryParameter("t", resumeIds[selectedResumeTemplate]+"")
//                        .build();
//                DownloadManager.Request localRequest = new DownloadManager.Request(uri);
//                localRequest.setNotificationVisibility(1);
//                localDownloadManager.enqueue(localRequest);


                downloadresume.setVisibility(View.VISIBLE);
                resumeprogress.setVisibility(View.GONE);
                Toast.makeText(getContext(),"Downloading Started..",Toast.LENGTH_SHORT).show();

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
            json = jParser.makeHttpRequest(load_resume_ids, "GET", params);
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
                    item=new ResumeTemplateItem(resumeIds[i],"http://192.168.100.100/AESTest/GetResumePage?a="+resumeIds[i]+"&b=1",resumeNames[i],"checked");
                else
                    item=new ResumeTemplateItem(resumeIds[i],"http://192.168.100.100/AESTest/GetResumePage?a="+resumeIds[i]+"&b=1",resumeNames[i],"unchecked");
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
}