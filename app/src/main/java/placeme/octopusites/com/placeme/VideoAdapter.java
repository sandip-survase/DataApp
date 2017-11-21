package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    private List<VideoItem> itemList;

    public VideoAdapter(List<VideoItem> itemList) {
        this.itemList = itemList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, content;
        public ImageView img;


        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            content = (TextView) view.findViewById(R.id.content);
            img=(ImageView)view.findViewById(R.id.img);



        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final VideoItem item = itemList.get(position);

        holder.title.setText(item.getTitle());

        if (item.getUrl() != null) {

            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(item.getUrl()));
                    holder.img.getContext().startActivity(intent);
                }
            });


        }
        if (item.getThumbnail() != null) {
            Glide.with(holder.img.getContext())
                    .load(item.getThumbnail())
                    .crossFade()
                    .into(holder.img);
            holder.img.setVisibility(View.VISIBLE);
        }
        else {
            holder.img.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

