package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewNotificationAdmin extends AppCompatActivity {

    ImageView editnotification;
    String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notification_admin);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Notification");
        ab.setDisplayHomeAsUpEnabled(true);

        role=MySharedPreferencesManager.getRole(this);

        editnotification=(ImageView)findViewById(R.id.editnotification);
        editnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(role.equals("hr"))
                    startActivity(new Intent(ViewNotificationAdmin.this,EditNotificationHrMain.class));
                else if(role.equals("admin"))
                startActivity(new Intent(ViewNotificationAdmin.this,EditNotificationMain.class));
            }
        });

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/meriweather.ttf");

        TextView notificationheadingview=(TextView)findViewById(R.id.notificationheadingview);
        notificationheadingview.setTypeface(custom_font);

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
}
