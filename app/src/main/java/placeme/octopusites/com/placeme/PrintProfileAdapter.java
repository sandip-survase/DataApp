package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ObjectKey;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PrintProfileAdapter extends RecyclerView.Adapter<PrintProfileAdapter.MyViewHolder> {

    private List<ResumeTemplateItem> itemList;
    private String username;
    private static AppCompatRadioButton lastChecked = null;
    private static int lastCheckedPos = 0;

    public PrintProfileAdapter(String username,List<ResumeTemplateItem> itemList) {
        this.username = username;
        this.itemList = itemList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView protxt;
        public AppCompatRadioButton title;
        public ImageView thumbnail;
        public RelativeLayout procard;
        public ProgressBar resumeprogress;

        public MyViewHolder(View view) {
            super(view);

            title = (AppCompatRadioButton) view.findViewById(R.id.title);
            protxt = (TextView) view.findViewById(R.id.protxt);
            thumbnail=(ImageView)view.findViewById(R.id.thumbnail);
            procard=(RelativeLayout)view.findViewById(R.id.procard);
            resumeprogress=(ProgressBar)view.findViewById(R.id.resumeprogress);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.printprofile_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final ResumeTemplateItem item = itemList.get(position);

        if(item.getStatus().equals("checked"))
        {
            holder.title.setChecked(true);
        }
        holder.title.setTag(new Integer(position));

        if(position == 0 && item.getStatus().equals("checked") && holder.title.isChecked())
        {
            lastChecked = holder.title;
            lastCheckedPos = 0;
        }
        holder.title.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AppCompatRadioButton cb = (AppCompatRadioButton)v;
                int clickedPos = ((Integer)cb.getTag()).intValue();

                if(cb.isChecked())
                {
                    if(lastChecked != null)
                    {
                        if(lastChecked!=cb) {
                            lastChecked.setChecked(false);
                            itemList.get(lastCheckedPos).setStatus("checked");
                        }
                    }

                    lastChecked = cb;
                    lastCheckedPos = clickedPos;
                    itemList.get(clickedPos).setStatus("checked");
                }
                else {
                    lastChecked = null;
                    itemList.get(clickedPos).setStatus("unchecked");
                }

            }
        });
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


            GlideApp.with(holder.thumbnail.getContext())
                    .load(item.getThumbnail())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            holder.resumeprogress.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            holder.resumeprogress.setVisibility(View.GONE);
                            return false;
                        }

                    })
                    .into(holder.thumbnail);







        }
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.title.setChecked(true);
                AppCompatRadioButton cb = (AppCompatRadioButton)holder.title;
                int clickedPos = ((Integer)cb.getTag()).intValue();

                if(cb.isChecked())
                {
                    if(lastChecked != null)
                    {
                        if(lastChecked!=cb) {
                            lastChecked.setChecked(false);
                            itemList.get(lastCheckedPos).setStatus("checked");
                        }
                    }

                    lastChecked = cb;
                    lastCheckedPos = clickedPos;
                    itemList.get(clickedPos).setStatus("checked");
                }
                else {
                    lastChecked = null;
                    itemList.get(clickedPos).setStatus("unchecked");
                }

                holder.thumbnail.getContext().startActivity(new Intent(holder.thumbnail.getContext(),ViewTemplateActivity.class).putExtra("key",item.getId()));
            }
        });



    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }



}
