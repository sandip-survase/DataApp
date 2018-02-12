package placeme.octopusites.com.placeme.modal;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import placeme.octopusites.com.placeme.R;
import placeme.octopusites.com.placeme.UserSelection2;
import placeme.octopusites.com.placeme.Z;


public class SubListAdapter3 extends RecyclerView.Adapter<SubListAdapter3.MyViewHolder> {

    private ArrayList<SubListModal> subList1;
    public ImageView resumeSelectioview;
    Context mContext;
    HashMap<String, String> encUser = new HashMap<String, String>();
    String extractedusername = null;

    public SubListAdapter3(ArrayList<SubListModal> subList1, Context mContext) {
        this.subList1 = subList1;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list_users, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        try {
        final SubListModal item = subList1.get(position);
        holder.name.setText(item.getName());
        holder.email.setText(item.getEmail());

        //image
        extractedusername = "" + item.getEmail();
        Log.d("Tag", "extractedusername after: " + extractedusername);
        String value = encUser.get(extractedusername);
        if (value != null) {

        } else {

                encUser.put(extractedusername, Z.Encrypt(extractedusername, mContext));

        }

        Log.d("TAG", "encUser Size: -----------------   " + encUser.size());
        Log.d("kun", "encUser: -----------------   " + encUser.get(extractedusername));

        Uri uri = new Uri.Builder()
                .scheme("https")
                .authority(Z.VPS_IP)
                .path("AESTest/GetImageThumbnail")
                .appendQueryParameter("u", encUser.get(extractedusername))
                .build();
        Glide.with(mContext)
                .load(uri)
                .signature(new StringSignature( encUser.get(extractedusername)))
                .into(holder.profileImage);

            holder.resumeSelectioview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((UserSelection2) mContext).showResume(item.getEmail(),"three",item.isSelected());

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return subList1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, email;
        public CheckBox checkboxs;
        public ImageView resumeSelectioview;
        public CircleImageView profileImage;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);
            checkboxs = (CheckBox) view.findViewById(R.id.checkboxs);
            resumeSelectioview = (ImageView) view.findViewById(R.id.resumeSelectioview);
            profileImage = (CircleImageView) view.findViewById(R.id.uploadedbyprofile);
            checkboxs.setVisibility(View.GONE);
        }
    }
}


