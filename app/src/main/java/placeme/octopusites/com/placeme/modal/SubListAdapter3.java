package placeme.octopusites.com.placeme.modal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import placeme.octopusites.com.placeme.R;


public class SubListAdapter3 extends RecyclerView.Adapter<SubListAdapter3.MyViewHolder> {

    private ArrayList<SubListModal> subList1;
    Context mContext;

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
        final SubListModal item = subList1.get(position);
        holder.name.setText(item.getName());
        holder.email.setText(item.getEmail());
        if (item.isSelected())
            holder.checkboxs.setChecked(true);
        else
            holder.checkboxs.setChecked(false);


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


