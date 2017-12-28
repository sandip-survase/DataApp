package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ResumeTemplateAdapter extends RecyclerView.Adapter<ResumeTemplateAdapter.MyViewHolder> {

    private List<ResumeTemplateItem> itemList;
    private String username;

    public ResumeTemplateAdapter(String username,List<ResumeTemplateItem> itemList) {
        this.username = username;
        this.itemList = itemList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title,protxt;
        public ImageView thumbnail,status,touchable_area;
        public RelativeLayout procard;
        public ProgressBar resumeprogress;

        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            protxt = (TextView) view.findViewById(R.id.protxt);
            thumbnail=(ImageView)view.findViewById(R.id.thumbnail);
            status=(ImageView)view.findViewById(R.id.status);
            touchable_area=(ImageView)view.findViewById(R.id.touchable_area);
            procard=(RelativeLayout)view.findViewById(R.id.procard);
            resumeprogress=(ProgressBar)view.findViewById(R.id.resumeprogress);


        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resumetemplate_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final ResumeTemplateItem item = itemList.get(position);

        if(item.getTitle().contains("-pro"))
        {
            String title=item.getTitle().replace("-pro","");
            Typeface custom_font10 = Typeface.createFromAsset(holder.thumbnail.getContext().getAssets(),  "fonts/hint.ttf");
            holder.protxt.setTypeface(custom_font10);
            holder.procard.setVisibility(View.VISIBLE);
            holder.title.setText(title);
        }
        else
            holder.title.setText(item.getTitle());

        if (item.getThumbnail() != null) {

            Glide.with(holder.thumbnail.getContext())
                    .load(item.getThumbnail())
                    .crossFade()
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            holder.resumeprogress.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            holder.resumeprogress.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(holder.thumbnail);
        }

        if (item.getStatus() != null) {
            if(item.getStatus().equals("downloaded")) {

                Glide.with(holder.status.getContext())
                        .load(R.drawable.downloaded)
                        .into(holder.status);
            }
            else
            {
                Glide.with(holder.status.getContext())
                        .load(R.drawable.download_white)
                        .into(holder.status);

                holder.touchable_area.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DownloadResumeTemplate(item.getId());
                        Toast.makeText(holder.thumbnail.getContext(), "Successfully Downloaded", Toast.LENGTH_SHORT).show();

                        Glide.with(holder.status.getContext())
                                .load(R.drawable.downloaded)
                                .into(holder.status);


                        IsNewTemplateDownloaded obj=new IsNewTemplateDownloaded();
                        obj.setIsDownloaded(true);

                    }
                });
            }

        }
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.thumbnail.getContext().startActivity(new Intent(holder.thumbnail.getContext(),ViewTemplateActivity.class).putExtra("key",item.getId()));
            }
        });
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private void DownloadResumeTemplate(final int id)
    {
        new AsyncTask<Void, Void, Void>() {

            JSONParser jParser = new JSONParser();
            JSONObject json;
            @Override
            protected Void doInBackground(Void... param) {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                Log.d("TAG", "doInBackground: username -" + username);
                Log.d("TAG", "doInBackground: id -" + id);
                params.add(new BasicNameValuePair("u",username));
                params.add(new BasicNameValuePair("id",id+""));
                json = jParser.makeHttpRequest(Z.download_resume_template, "GET", params);

                try
                {

                    String s=json.getString("info");


                }catch (Exception e){}

                return null;
            }



       }.execute();

    }

}