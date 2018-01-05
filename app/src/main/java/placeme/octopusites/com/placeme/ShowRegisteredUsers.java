package placeme.octopusites.com.placeme;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import placeme.octopusites.com.placeme.modal.RecyclerItemUsers;
import placeme.octopusites.com.placeme.modal.RecyclerItemUsersAdapter;

public class ShowRegisteredUsers extends AppCompatActivity {

    private MaterialSearchView searchView;
    private List<RecyclerItemUsers> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerItemUsersAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_registered_users);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("News Feed");
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Registered Users</font>"));

        final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
        upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);


        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(ShowRegisteredUsers.this,query,Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
                upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                searchView.setBackIcon(upArrow);
            }

            @Override
            public void onSearchViewClosed() {
                final Drawable upArrow = getResources().getDrawable(R.drawable.backarrow);
                upArrow.setColorFilter(getResources().getColor(R.color.while_color), PorterDuff.Mode.SRC_ATOP);
                getSupportActionBar().setHomeAsUpIndicator(upArrow);
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

//        mAdapter = new RecyclerItemUsersAdapter(itemList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


//        addUsersdatatoAdapter();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
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
//    void addUsersdatatoAdapter()
//    {
//        for(int i=0;i<10;i++)
//        {
////            RecyclerItemUsers item = new RecyclerItemUsers(i,"Abc "+i, "abc"+i+"@gmail.com ","1234");
////            itemList.add(item);
//        }
//
//        mAdapter.notifyDataSetChanged();
//    }
}
