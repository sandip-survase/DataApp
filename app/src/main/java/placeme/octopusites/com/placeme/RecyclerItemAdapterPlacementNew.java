package placeme.octopusites.com.placeme;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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
import com.bumptech.glide.signature.StringSignature;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static placeme.octopusites.com.placeme.AES4all.Decrypt;

/**
 * Created by admin on 12/2/2017.
 */

public class RecyclerItemAdapterPlacementNew extends RecyclerView.Adapter<RecyclerItemAdapterPlacementNew.MyViewHolder> {

    private List<RecyclerItemPlacementTempModal> itemList;
    private String searchText;
    Context mContext;

    public RecyclerItemAdapterPlacementNew(ArrayList<RecyclerItemPlacementTempModal> itemList, Context mContext) {
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

        RecyclerItemPlacementTempModal item = itemList.get(position);


        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority("192.168.100.100")
                .path("AESTest/GetImageThumbnail")
                .appendQueryParameter("u", item.getUploadedby())
                .build();

        Glide.with(mContext)
                .load(uri)
                .crossFade()
                .signature(new StringSignature(item.getSignature()))
                .into(holder.uploadedbyprofile);



        holder.lastdateofreg.setText(item.getLastdateofregistration());
        holder.cpackage.setText(item.getCpackage());
        holder.post.setText(item.getPost());


        try {
            Log.d("uploadedbysfdsdf", ": "+item.getUploadedby());
            String s1=item.getUploadedby();
            String s1Plain= Decrypt(s1,"I09jdG9wdXMxMkl0ZXMjJQ==","I1BsYWNlMTJNZSMlJSopXg==");




            if(  s1Plain.equals("sandipsurvase1993@gmail.com")   ){
                holder.logo.setVisibility(View.VISIBLE);
            }else {
                holder.logo.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }





    }

    @Override
    public int getItemCount() {
        return 0;
    }

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
    public void updateList(List<RecyclerItemPlacementTempModal> list, String searchText){
        itemList = list;
        this.searchText = searchText;
        notifyDataSetChanged();
    }
}
