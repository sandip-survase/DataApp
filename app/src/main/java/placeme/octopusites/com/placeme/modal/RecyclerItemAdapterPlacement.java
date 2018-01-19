package placeme.octopusites.com.placeme.modal;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import placeme.octopusites.com.placeme.R;
import placeme.octopusites.com.placeme.Z;


public class RecyclerItemAdapterPlacement extends RecyclerView.Adapter<RecyclerItemAdapterPlacement.MyViewHolder> {

    private List<RecyclerItemPlacement> itemList;
    private String searchText;
    Context mContext;
    HashMap<String, String> encUser = new HashMap<String, String>();
    String extractedusername=null;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView uploadedbyprofile, logo;
        public TextView companyname, lastdateofreg, cpackage, post;

        public MyViewHolder(View view) {
            super(view);
            uploadedbyprofile = (CircleImageView) view.findViewById(R.id.uploadedbyprofile);
            companyname = (TextView) view.findViewById(R.id.companyname);
            lastdateofreg = (TextView) view.findViewById(R.id.lastdateofreg);
            cpackage = (TextView) view.findViewById(R.id.cpackage);
            post = (TextView) view.findViewById(R.id.post);
            logo = (CircleImageView) view.findViewById(R.id.logo);

        }
    }

    public void updateList(List<RecyclerItemPlacement> list, String searchText) {
        itemList = list;
        this.searchText = searchText;
        notifyDataSetChanged();
    }

    public RecyclerItemAdapterPlacement(List<RecyclerItemPlacement> itemList, Context mContext) {
        this.itemList = itemList;
        this.mContext = mContext;
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
        try {

            Log.d("Tag", "contex: " + mContext);
            Log.d("Tag", "uploadedby: " + item.getUploadedby());
            Log.d("Tag", "getCompanyname: " + item.getCompanyname());

            extractedusername = "" + item.getUploadedby();
            if(extractedusername.contains("("))
                extractedusername = extractedusername.substring(extractedusername.indexOf("(") + 1, extractedusername.indexOf(")"));

            extractedusername = extractedusername.trim();
            Log.d("Tag", "extractedusername after: " + extractedusername);



            String value = encUser.get(extractedusername);
            if (value != null) {
            } else {
                encUser.put(extractedusername, Z.Encrypt(extractedusername, mContext));
            }

            Log.d("TAG", "encUser Size: -----------------   " + encUser.size());
            Log.d("kun", "encUser: -----------------   " + encUser.get(extractedusername));


            Uri uri = new Uri.Builder()
                    .scheme("http")
                    .authority(Z.VPS_IP)
                    .path("AESTest/GetImageThumbnail")
                    .appendQueryParameter("u", encUser.get(extractedusername))
                    .build();

            Glide.with(mContext)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .signature(new StringSignature(item.getSignature()))
                    .into(holder.uploadedbyprofile);


            Log.d("Tag", "signature : " + item.getSignature());


            if (searchText != null) {
                if (searchText.length() > 0) {
                    holder.companyname.setText(highlightText(searchText, item.getCompanyname()));
                } else
                    holder.companyname.setText(item.getCompanyname());
            } else
                holder.companyname.setText(item.getCompanyname());

            holder.companyname.setTypeface(Z.getBold(holder.companyname.getContext()));
            holder.lastdateofreg.setText(item.getLastdateofregistration());
            holder.cpackage.setText(item.getCpackage());
            holder.post.setText(item.getPost());
            holder.lastdateofreg.setTypeface(Z.getLight(holder.lastdateofreg.getContext()));
            holder.cpackage.setTypeface(Z.getLight(holder.cpackage.getContext()));
            holder.post.setTypeface(Z.getLight(holder.post.getContext()));


            if (item.isIsread()) {
                holder.companyname.setTextColor(Color.parseColor("#03353e"));


            } else if (!item.isIsread()) {
                holder.companyname.setTextColor(Color.parseColor("#00bcd4"));


            }


            try {
                if(extractedusername!=null){
                    char s1 = extractedusername.charAt(0);


                    if (s1=='C') {
                        holder.logo.setVisibility(View.VISIBLE);
                    } else {
                        holder.logo.setVisibility(View.INVISIBLE);
                    }

                }else{
                    holder.logo.setVisibility(View.INVISIBLE);
                }

            } catch (Exception e) {
                Log.d("uploadedbysfdsdf", e.getMessage());
                e.printStackTrace();
            }

        } catch (Exception e) {

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

