package placeme.octopusites.com.placeme;

import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class RecyclerItemUsersAdminAdapter extends RecyclerView.Adapter<RecyclerItemUsersAdminAdapter.MyViewHolder> {

    private List<RecyclerItemUsersAdmin> itemList;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    private String searchText;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,email,role,isactivated;
        public CircleImageView uploadedbyprofile;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);
            role=(TextView) view.findViewById(R.id.role);
            isactivated=(TextView) view.findViewById(R.id.placed);      // changed place to isactivated
            uploadedbyprofile=(CircleImageView) view.findViewById(R.id.uploadedbyprofile);
            name.setTypeface(MyConstants.getBold(name.getContext()));
            email.setTypeface(MyConstants.getLight(email.getContext()));
            role.setTypeface(MyConstants.getLight(role.getContext()));
            isactivated.setTypeface(MyConstants.getLight(isactivated.getContext()));


        }
    }
    public void updateList(List<RecyclerItemUsersAdmin> list,String searchText){
        itemList = list;
        this.searchText = searchText;
        notifyDataSetChanged();
    }

    public RecyclerItemUsersAdminAdapter(List<RecyclerItemUsersAdmin> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list_users_admin, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        RecyclerItemUsersAdmin item = itemList.get(position);

        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority("192.168.100.100")
                .path("AESTest/GetImageThumbnail")
                .appendQueryParameter("u", item.getEncemail())
                .build();

        GlideApp.with(holder.name.getContext())
                .load(uri)
                .signature(new ObjectKey(item.getSignatsure()))
                .into(holder.uploadedbyprofile);


        if(searchText!=null) {
            if (searchText.length() > 0) {
                holder.email.setText(highlightText(searchText,item.getEmail()));
            }
            else
                holder.email.setText(item.getEmail());
        }
        else
            holder.email.setText(item.getEmail());


        holder.name.setText(item.getName());
        holder.role.setText(item.getRole());
        holder.isactivated.setText(item.getIsactivated());


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

