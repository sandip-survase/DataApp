package placeme.octopusites.com.placeme.modal;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import placeme.octopusites.com.placeme.R;
import placeme.octopusites.com.placeme.UserSelection;
import placeme.octopusites.com.placeme.Z;


public class RecyclerItemUsersAdapter extends RecyclerView.Adapter<RecyclerItemUsersAdapter.MyViewHolder> {
    Context mContext;
    private ArrayList<RecyclerItemUsers> itemList;
    int flag;

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
        }
    }


    public RecyclerItemUsersAdapter(ArrayList<RecyclerItemUsers> itemList, Context mContext, int flag) {
        this.itemList = itemList;
        this.mContext = mContext;
        this.flag = flag;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list_users, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Log.d("TAG2", "flag: " + flag);

        final RecyclerItemUsers item = itemList.get(position);
        try {

            holder.name.setText(item.getName());
            holder.email.setText(Z.Decrypt(item.getEmail(), mContext));
            holder.name.setTypeface(Z.getBold(holder.name.getContext()));
            holder.email.setTypeface(Z.getLight(holder.email.getContext()));

            Uri uri = new Uri.Builder()
                    .scheme("http")
                    .authority(Z.VPS_IP)
                    .path("AESTest/GetImageThumbnail")
                    .appendQueryParameter("u", item.getEmail())
                    .build();

            Glide.with(mContext)
                    .load(uri)
                    .signature(new StringSignature(Z.Decrypt(item.getEmail(), mContext)))
                    .into(holder.profileImage);

        } catch (Exception e) {
        }

        if (item.isSelected())
            holder.checkboxs.setChecked(true);
        else
            holder.checkboxs.setChecked(false);

        if (flag == 3)
            holder.checkboxs.setVisibility(View.GONE);


        holder.resumeSelectioview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((UserSelection) mContext).showResume(item.getEmail());

            }
        });
        holder.checkboxs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    item.setSelected(true);

                } else {
                    item.setSelected(false);

                }
                if (flag == 1)
                    ((UserSelection) mContext).showCount(item);
                else if (flag == 2)
                    ((UserSelection) mContext).showCount2(item);

            }

        });


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

