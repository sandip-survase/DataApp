package placeme.octopusites.com.placeme;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class RecyclerItemAdminEditAdapter extends RecyclerView.Adapter<RecyclerItemAdminEditAdapter.MyViewHolder> {

    private List<RecyclerItemAdminEdit> itemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, notification;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            notification = (TextView) view.findViewById(R.id.notification);
            imageView=(ImageView)view.findViewById(R.id.editnotification);
        }
    }


    public RecyclerItemAdminEditAdapter(List<RecyclerItemAdminEdit> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list_admin_edit, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        RecyclerItemAdminEdit item = itemList.get(position);

        holder.title.setText(item.getTitle());
        holder.notification.setText(item.getNotification());




    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

