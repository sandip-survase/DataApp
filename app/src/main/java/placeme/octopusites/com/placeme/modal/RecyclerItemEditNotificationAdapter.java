package placeme.octopusites.com.placeme.modal;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import placeme.octopusites.com.placeme.R;
import placeme.octopusites.com.placeme.Z;

/**
 * Created by sunny on 12/20/2017.
 */

public class RecyclerItemEditNotificationAdapter extends RecyclerView.Adapter<RecyclerItemEditNotificationAdapter.MyViewHolder> {

    private ArrayList<RecyclerItemEdit> itemList;
    private String searchText;
    Context mContext;
    HashMap<String, String> encUser = new HashMap<String, String>();


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView uploadedbyprofile;
        public TextView title, notification, uploadtime;
        public ImageView imageView, logo;
        public ImageView imageView2;


        public MyViewHolder(View view) {
            super(view);
            uploadedbyprofile = (CircleImageView) view.findViewById(R.id.uploadedbyprofile);
            title = (TextView) view.findViewById(R.id.title);
            notification = (TextView) view.findViewById(R.id.notification);
            uploadtime = (TextView) view.findViewById(R.id.uploadtime);
            imageView = (ImageView) view.findViewById(R.id.attachment);
            logo = (CircleImageView) view.findViewById(R.id.logo);

        }
    }

    public RecyclerItemEditNotificationAdapter(ArrayList<RecyclerItemEdit> itemList) {
        this.itemList = itemList;

    }

    public RecyclerItemEditNotificationAdapter(ArrayList<RecyclerItemEdit> itemList, Context mContext) {
        this.itemList = itemList;
        this.mContext = mContext;
    }


    public void updateList(ArrayList<RecyclerItemEdit> list, String searchText) {
        itemList = list;
        this.searchText = searchText;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("tag2", "adapter notification Accessed");

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list_row, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        RecyclerItemEdit item = itemList.get(position);
        try {

            Log.d("Tag", "contex: " + mContext);
            Log.d("Tag", "uploadedby: " + item.getUploadedby());


            String value = encUser.get(item.getUploadedby());
            if (value != null) {
            } else {
                encUser.put(item.getUploadedby(), Z.Encrypt(item.getUploadedby(), mContext));
            }

            Log.d("TAG", "encUser Size: -----------------   " + encUser.size());
            Log.d("kun", "encUser: -----------------   " + encUser.get(item.getUploadedby()));


            Uri uri = new Uri.Builder()
                    .scheme("http")
                    .authority(Z.VPS_IP)
                    .path("AESTest/GetImageThumbnail")
                    .appendQueryParameter("u", encUser.get(item.getUploadedby()))
                    .build();

            Glide.with(mContext)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .signature(new StringSignature(item.getSignature()))
                    .into(holder.uploadedbyprofile);


            Log.d("Tag", "signature : " + item.getSignature());


            if (searchText != null) {
                if (searchText.length() > 0) {
                    holder.title.setText(highlightText(searchText, item.getTitle()));
                } else
                    holder.title.setText(item.getTitle());
            } else
                holder.title.setText(item.getTitle());

            holder.title.setTypeface(Z.getBold(holder.title.getContext()));
            holder.notification.setText(item.getNotification());
            holder.notification.setTypeface(Z.getLight(holder.title.getContext()));


            if (item.getUploadedby().equals("PLACE ME")) {
                holder.uploadtime.setVisibility(View.GONE);
            } else {
                holder.uploadtime.setVisibility(View.VISIBLE);
                holder.uploadtime.setText(item.getUploadtime());
                holder.uploadtime.setTypeface(Z.getLight(holder.title.getContext()));
            }

            if (item.isAttachment()) {
                Drawable myDrawable = mContext.getResources().getDrawable(R.drawable.attachment_icon);
                holder.imageView.setImageDrawable(myDrawable);

            } else if (!item.isAttachment()) {
                holder.imageView.setImageBitmap(null);
            }

            if (item.isIsread()) {
                holder.title.setTextColor(Color.parseColor("#03353e"));


            } else if (!item.isIsread()) {
                holder.title.setTextColor(Color.parseColor("#00bcd4"));


            }


            // temporary work remove when uploader signature sending through object
            try {
                Log.d("uploadedbysfdsdf", ": " + item.getUploadedby());
                String s1 = item.getUploadedby();

                if (s1.equals("sandipsurvase1993@gmail.com")) {
                    holder.logo.setVisibility(View.VISIBLE);
                } else {
                    holder.logo.setVisibility(View.INVISIBLE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public static CharSequence highlightText(String search, String originalText) {
        if (search != null && !search.equalsIgnoreCase("")) {
            String normalizedText = Normalizer.normalize(originalText, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
            int start = normalizedText.indexOf(search);
            if (start < 0) {
                return originalText;
            } else {
                Spannable highlighted = new SpannableString(originalText);
                while (start >= 0) {
                    int spanStart = Math.min(start, originalText.length());
                    int spanEnd = Math.min(start + search.length(), originalText.length());
                    highlighted.setSpan(new ForegroundColorSpan(Color.parseColor("#4169e1")), spanStart, spanEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    start = normalizedText.indexOf(search, spanEnd);
                }
                return highlighted;
            }
        }
        return originalText;
    }


}
