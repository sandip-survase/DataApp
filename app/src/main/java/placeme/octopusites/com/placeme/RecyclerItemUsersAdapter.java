package placeme.octopusites.com.placeme;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class RecyclerItemUsersAdapter extends RecyclerView.Adapter<RecyclerItemUsersAdapter.MyViewHolder> {

    private List<RecyclerItemUsers> itemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,email,mobile;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);
            mobile=(TextView) view.findViewById(R.id.mobile);
        }
    }


    public RecyclerItemUsersAdapter(List<RecyclerItemUsers> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list_users, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        RecyclerItemUsers item = itemList.get(position);

        holder.name.setText(item.getName());
        holder.email.setText(item.getEmail());
        holder.mobile.setText(item.getMobile());



    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

