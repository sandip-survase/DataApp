package placeme.octopusites.com.placeme;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VideosFragment extends Fragment {

    private List<VideoItem> itemList= new ArrayList<>();
    private RecyclerView recyclerView;
    private VideoAdapter mAdapter;

    private SwipeRefreshLayout swipe_refresh_layout;
    JSONParser jParser = new JSONParser();
    JSONObject json;

    int count=0;
    int[] id;
    String[] thumbnail,title,description,url;

    public VideosFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_videos, container, false);

        swipe_refresh_layout=(SwipeRefreshLayout)rootView.findViewById(R.id.swipe_refresh_layout);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view_videos);
        mAdapter=new VideoAdapter(itemList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

//        for(int i=0;i<5;i++)
//        {
//            VideoItem item=new VideoItem(i,"http://www.wallpapereast.com/static/images/pier_1080.jpg","This is the title","and this is the description","http://www.youtube.com/watch?v=Hxy8BZGQ5Jo");
//            itemList.add(item);
//        }
//        mAdapter.notifyDataSetChanged();

        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new GetVideos().execute();
            }
        });
        swipe_refresh_layout.setRefreshing(true);
        new GetVideos().execute();

        return rootView;
    }
    class GetVideos extends AsyncTask<String, String, String> {


        protected String doInBackground(String... param) {
            String r="";

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            try {

                json = jParser.makeHttpRequest(MyConstants.load_videos,"GET", params);
                count = Integer.parseInt(json.getString("count"));

                id=new int[count];
                thumbnail=new String[count];
                title=new String[count];
                description=new String[count];
                url=new String[count];


                itemList.clear();

                for(int i=0;i<count;i++)
                {
                    id[i]=Integer.parseInt(json.getString("id"+i));
                    thumbnail[i]=json.getString("thumbnail"+i);
                    title[i]=json.getString("title"+i);
                    description[i]=json.getString("description"+i);
                    url[i]=json.getString("url"+i);

                    VideoItem item=new VideoItem(id[i],thumbnail[i],title[i],description[i],url[i]);
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
