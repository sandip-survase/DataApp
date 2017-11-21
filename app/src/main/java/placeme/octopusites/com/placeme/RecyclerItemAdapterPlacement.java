package placeme.octopusites.com.placeme;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;

import java.text.Normalizer;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class RecyclerItemAdapterPlacement extends RecyclerView.Adapter<RecyclerItemAdapterPlacement.MyViewHolder> {

    private List<RecyclerItemPlacement> itemList;
    private String searchText;

    public class MyViewHolder extends RecyclerView.ViewHolder {
    public CircleImageView uploadedbyprofile;
    public TextView companyname, lastdateofreg, cpackage,post;

        public MyViewHolder(View view) {
            super(view);
            uploadedbyprofile=(CircleImageView)view.findViewById(R.id.uploadedbyprofile);
            companyname = (TextView) view.findViewById(R.id.companyname);
            lastdateofreg = (TextView) view.findViewById(R.id.lastdateofreg);
            cpackage = (TextView) view.findViewById(R.id.cpackage);
            post=(TextView)view.findViewById(R.id.post);


        }
    }
    public void updateList(List<RecyclerItemPlacement> list,String searchText){
        itemList = list;
        this.searchText = searchText;
        notifyDataSetChanged();
    }

    public RecyclerItemAdapterPlacement(List<RecyclerItemPlacement> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list_row_placements, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        RecyclerItemPlacement item = itemList.get(position);

        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority("192.168.100.100")
                .path("AESTest/GetImageThumbnail")
                .appendQueryParameter("u", item.getUploadedby())
                .build();

        Glide.with(item.getContext())
                .load(uri)
                .crossFade()
                .signature(new StringSignature(item.getSignature()))
                .into(holder.uploadedbyprofile);

        if(searchText!=null) {
            if (searchText.length() > 0) {
                holder.companyname.setText(highlightText(searchText,item.getCompanyname()));
            }
            else
                holder.companyname.setText(item.getCompanyname());
        }
        else
            holder.companyname.setText(item.getCompanyname());

        holder.lastdateofreg.setText(item.getLastdateofregistration());
        holder.cpackage.setText(item.getCpackage());
        holder.post.setText(item.getPost());

        if(item.getisRead())
        {
            holder.companyname.setTextColor(Color.parseColor("#eeeeee"));
            holder.companyname.setTypeface(null, Typeface.NORMAL);

        }
        else if(!item.getisRead())
        {
            holder.companyname.setTextColor(Color.parseColor("#c59a6d"));
            holder.companyname.setTypeface(null, Typeface.BOLD);

        }


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
    @Override
    public int getItemCount() {
        return itemList.size();
    }


}

