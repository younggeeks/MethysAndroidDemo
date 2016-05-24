package younggeeks.com.methystake2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

/**
 * An activity representing a single Car detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link CarListActivity}.
 */
public class CarDetailActivity extends AppCompatActivity {

    //SharedPreferences for retrieving the logged in user
    private SharedPreferences sharedPreferences;

    //loggedIn property will return false if user is not logged in
    private boolean loggedIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        //getting saved sharedPreference file by the name MethysAuth
        sharedPreferences=getSharedPreferences("MethysAuth",MODE_PRIVATE);

        //getting editor object which is responsible for Modification of values in SharedPreferences
        final SharedPreferences.Editor editor=sharedPreferences.edit();

        //retrieving boolean with key logged in
        loggedIn=sharedPreferences.getBoolean("loggedIn",false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if user clicked the logout button , first remove loggedIn from sharedPreference
                editor.remove("loggedIn");
                //saving changes
                editor.commit();
                //then Send User to the login page
                login();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
             Bundle arguments = new Bundle();
            arguments.putString(CarDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(CarDetailFragment.ARG_ITEM_ID));
            CarDetailFragment fragment = new CarDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.car_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, CarListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    /**
     * Samwel Charles
     *
    * Method is responsible for creating an intent(LoginIntent) then starting
    * activity and finally close the current activity using finish() method
     */
    public void login(){
        Intent carListIntent=new Intent(this,LoginActivity.class);
        startActivity(carListIntent);
        finish();
    }
}
