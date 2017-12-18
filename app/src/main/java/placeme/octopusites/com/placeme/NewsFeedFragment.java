package placeme.octopusites.com.placeme;


import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedFragment extends Fragment {


    private List<NewsFeedItem> itemList= new ArrayList<>();
    private RecyclerView recyclerView;
    private NewsFeedAdapter mAdapter;

    private SwipeRefreshLayout swipe_refresh_layout;
    JSONParser jParser = new JSONParser();
    JSONObject json;

    int count=0;
    int[] id;
    String[] uploadername,uploaderprofile,status,image,url,timestamp;
    public NewsFeedFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newsfeed, container, false);


        swipe_refresh_layout=(SwipeRefreshLayout)rootView.findViewById(R.id.swipe_refresh_layout);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view_newsfeed);
        mAdapter=new NewsFeedAdapter(itemList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new GetNews().execute();
            }
        });
        swipe_refresh_layout.setRefreshing(true);
        new GetNews().execute();

        return rootView;
    }



//    @Override
//    public void onStop() {
//        Log.d("TAG", "onPause: called");
//        ((MainActivity)getActivity()).visibleToolbar();
//        super.onStop();
//
//    }

    class GetNews extends AsyncTask<String, String, String> {

        protected String doInBackground(String... param) {
            String r="";

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            try {

                json = jParser.makeHttpRequest(MyConstants.load_news,"GET", params);
                count = Integer.parseInt(json.getString("count"));
                Log.d("TAG", "news count  "+count);

                id=new int[count];
                uploadername=new String[count];
                uploaderprofile=new String[count];
                status=new String[count];
                image=new String[count];
                url=new String[count];
                timestamp=new String[count];

                itemList.clear();

                for(int i=0;i<count;i++)
                {
                    id[i]=Integer.parseInt(json.getString("id"+i));
                    uploadername[i]=json.getString("uploadername"+i);
                    uploaderprofile[i]=json.getString("uploaderprofile"+i);
                    status[i]=json.getString("status"+i);
                    image[i]=json.getString("image"+i);
                    url[i]=json.getString("url"+i);
                    timestamp[i]=json.getString("timestamp"+i);
                    NewsFeedItem item=new NewsFeedItem(id[i],uploadername[i],image[i],status[i],uploaderprofile[i],timestamp[i],url[i]);
                    itemList.add(item);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            swipe_refresh_layout.setRefreshing(false);
            mAdapter.notifyDataSetChanged();
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


                });
            }
        }

        return animation;
    }

}
