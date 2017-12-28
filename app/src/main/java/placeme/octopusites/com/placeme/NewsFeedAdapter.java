package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.MyViewHolder> {

    private List<NewsFeedItem> itemList;

    public NewsFeedAdapter(List<NewsFeedItem> itemList) {
        this.itemList = itemList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, timestamp, txtStatusMsg;
        public ImageView profilePic,feedImage1;


        public MyViewHolder(View view) {
            super(view);

//            name = (TextView) view.findViewById(R.id.name);
//            timestamp = (TextView) view.findViewById(R.id.timestamp);
//            txtStatusMsg = (TextView) view.findViewById(R.id.txtStatusMsg);
//            profilePic=(ImageView)view.findViewById(R.id.profilePic);
//            feedImage1=(ImageView)view.findViewById(R.id.feedImage1);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newsfeed_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final NewsFeedItem item = itemList.get(position);

        holder.name.setText(item.getName());

        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(Long.parseLong(item.getTimeStamp()),System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        holder.timestamp.setText(timeAgo);

        if (!TextUtils.isEmpty(item.getStatus())) {
            holder.txtStatusMsg.setText(item.getStatus());
            holder.txtStatusMsg.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            holder.txtStatusMsg.setVisibility(View.GONE);
        }
        if (item.getUrl() != null) {

            holder.feedImage1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_VIEW);
//                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
//                    intent.setData(Uri.parse(item.getUrl()));
//                    holder.feedImage1.getContext().startActivity(intent);

                    Intent intent=new Intent(holder.feedImage1.getContext(),NewsFeedWebView.class);
                    intent.putExtra("url",item.getUrl());
                    intent.putExtra("header",item.getStatus());
                    holder.feedImage1.getContext().startActivity(intent);
                }
            });


        }
        Glide.with(holder.profilePic.getContext())
                .load(item.getProfilePic())
                .into(holder.profilePic);


        if (item.getImge() != null) {

            Glide.with(holder.feedImage1.getContext())
                    .load(item.getImge())
                    .into(holder.feedImage1);

            holder.feedImage1.setVisibility(View.VISIBLE);
        }
        else {
            holder.feedImage1.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
