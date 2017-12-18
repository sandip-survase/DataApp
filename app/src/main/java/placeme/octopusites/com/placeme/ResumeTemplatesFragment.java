package placeme.octopusites.com.placeme;

import android.content.res.Resources;
import android.graphics.Rect;
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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ResumeTemplatesFragment extends Fragment {

    private RecyclerView recyclerView;
    private ResumeTemplateAdapter adapter;
    private List<ResumeTemplateItem> itemList;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    int count=0;
    int resumeIds[];
    String resumeNames[],resumeDownloadStatus[];


    String username;
    public ResumeTemplatesFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_resumetemplates, container, false);

        username=MySharedPreferencesManager.getUsername(getActivity());


        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);

        itemList = new ArrayList<>();
        adapter = new ResumeTemplateAdapter(username,itemList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        new GetResumeIds().execute();
        return rootView;
    }
    class GetResumeIds extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {

            String r=null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));
            json = jParser.makeHttpRequest(MyConstants.load_resume_ids, "GET", params);
            try {
                String s = json.getString("count");
                count=Integer.parseInt(s);
                resumeIds=new int[count];
                resumeNames=new String[count];
                resumeDownloadStatus=new String[count];
                for(int i=0;i<count;i++) {
                    resumeIds[i] = Integer.parseInt(json.getString("id" + i));
                    resumeNames[i] = json.getString("name" + i);
                    resumeDownloadStatus[i] = json.getString("status" + i);
                }


            }catch (Exception e){e.printStackTrace();}
            return r;
        }

        @Override
        protected void onPostExecute(String result) {


            for(int i=0;i<count;i++)
            {
                ResumeTemplateItem item=new ResumeTemplateItem(resumeIds[i],MyConstants.IP+"AESTest/GetResumePage?a="+resumeIds[i]+"&b=1",resumeNames[i],resumeDownloadStatus[i]);
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

    /**
     * Converting dp to pixel
     */
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

                    // ...other AnimationListener methods go here...
                });
            }
        }

        return animation;
    }
}
