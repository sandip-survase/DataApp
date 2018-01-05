package placeme.octopusites.com.placeme.modal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import placeme.octopusites.com.placeme.R;
import placeme.octopusites.com.placeme.Z;


public class RecyclerItemHrPlacementAdapter extends RecyclerView.Adapter<RecyclerItemHrPlacementAdapter.MyViewHolder> {

    private List<RecyclerItemHrPlacement> itemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView companyname, lastmodifiedtime,registerednumber,placednumber,lastdateofreg;


        public MyViewHolder(View view) {
            super(view);
            companyname = (TextView) view.findViewById(R.id.companyname);
            lastmodifiedtime = (TextView) view.findViewById(R.id.lastmodifiedtime);
            registerednumber = (TextView) view.findViewById(R.id.registerednumber);
            placednumber = (TextView) view.findViewById(R.id.placednumber);
            lastdateofreg = (TextView) view.findViewById(R.id.lastdateofreg);
        }
    }


    public RecyclerItemHrPlacementAdapter(List<RecyclerItemHrPlacement> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list_hr_placements, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        RecyclerItemHrPlacement item = itemList.get(position);

        holder.companyname.setText(item.getCompanyname());
        holder.lastmodifiedtime.setText(item.getLastmodifiedtime());
        holder.registerednumber.setText(item.getRegisterednumber()+" Candidates Registered.");
        holder.placednumber.setText(item.getPlacednumber()+" Candidates Placed.");
        holder.lastdateofreg.setText(item.getLastdateofreg());

        holder.companyname.setTypeface(Z.getBold(holder.companyname.getContext()));
        holder.lastmodifiedtime.setTypeface(Z.getBold(holder.lastmodifiedtime.getContext()));
        holder.registerednumber.setTypeface(Z.getBold(holder.registerednumber.getContext()));
        holder.placednumber.setTypeface(Z.getBold(holder.placednumber.getContext()));
        holder.lastdateofreg.setTypeface(Z.getBold(holder.lastdateofreg.getContext()));


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

