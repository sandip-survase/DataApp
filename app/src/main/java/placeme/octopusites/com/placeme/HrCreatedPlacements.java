package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class HrCreatedPlacements extends AppCompatActivity {

    private List<RecyclerItemHrPlacement> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerItemHrPlacementAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_created_placements);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Created Placements");
        ab.setDisplayHomeAsUpEnabled(true);



        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        mAdapter = new RecyclerItemHrPlacementAdapter(itemList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecorationHrPlacements(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        addTempPlacements();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                RecyclerItemHrPlacement movie = itemList.get(position);


                startActivity(new Intent(HrCreatedPlacements.this,ShowRegisteredUsers.class));



            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();

                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }
    void addTempPlacements()
    {
        for(int i=0;i<10;i++)
        {
            RecyclerItemHrPlacement item=new RecyclerItemHrPlacement(i,"Cognizant","17-FEB-2017 5:20 PM","201 Candidates Registered","57 Candidates Placed","20-FEB-2017");
            itemList.add(item);
        }
        mAdapter.notifyDataSetChanged();
    }

}
