package younggeeks.com.methystake2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import younggeeks.com.methystake2.model.Car;
import younggeeks.com.methystake2.model.CarManager;

import java.util.List;

/**
 * An activity representing a list of Cars. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link CarDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class CarListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    /**
     *Samwel Charles
     *List<Car> which will be passed into our Recycler Adapter
     */
    private  List<Car> cars;

    /**
     *Samwel Charles
     *This is an integer which will track position of the current selected Item
     */
    private int selectedPos=0;

    /**
     *Samwel Charles
     *SharedPreferences instance variable
     */
    private SharedPreferences sharedPreferences;

    //loggedIn property will return false if user is not logged in
    private boolean loggedIn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        //getting saved sharedPreference file by the name MethysAuth
        sharedPreferences=getSharedPreferences("MethysAuth",MODE_PRIVATE);

        //getting editor object which is responsible for Modification of values in SharedPreferences
        final SharedPreferences.Editor editor=sharedPreferences.edit();

        //retrieving boolean with key logged in
        loggedIn=sharedPreferences.getBoolean("loggedIn",false);



        //if user is not logged in , user will be redirected to LoginActivity Immediately
        if (!loggedIn){
            login();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());




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

        View recyclerView = findViewById(R.id.car_list);
        assert recyclerView != null;

        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.car_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }


    /**
     * Samwel Charles
     *
     * Method is responsible for creating an intent(LoginIntent) then starting
     * activity and finally close the current activity using finish() method
     */
    public void login(){
        Intent loginIntent=new Intent(this,LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(CarManager.getInstance().getCars()));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
        //changed the parameters so as to receive list of Car Object
        public SimpleItemRecyclerViewAdapter(List<Car> items) {
            cars = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.car_list_content, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            //retrieving Car object from cars List according to the position
            holder.car = cars.get(position);

            //this will check the current selected  Car object and set Background
            //to accentColor  otherwise the background color will be transparent

            if(selectedPos==position){
                holder.itemView.setBackgroundColor(Color.rgb(255,87,54));
            }else{
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }


            //binding values to the views

                holder.modelLabel.setText(cars.get(position).getModel());

            holder.manufacturerLabel.setText(cars.get(position).getManufacturer());

            holder.imgLabel.setImageResource(holder.car.getDrawable(getApplicationContext(),holder.car.getManufacturer()));

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //notifying recyclerview when selected item changes
                    //for this particular part is for it to change color of selected item
                    notifyItemChanged(selectedPos);
                    selectedPos=position;
                    notifyItemChanged(selectedPos);


                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(CarDetailFragment.ARG_ITEM_ID, Integer.toString(holder.car.getId()));
                        CarDetailFragment fragment = new CarDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.car_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, CarDetailActivity.class);
                        intent.putExtra(CarDetailFragment.ARG_ITEM_ID,Integer.toString(holder.car.getId()));
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return cars.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView manufacturerLabel;
            public final TextView modelLabel;
            public final ImageView imgLabel;
            public Car car;

            public ViewHolder(View view) {
                super(view);
                mView = view;

                manufacturerLabel = (TextView) view.findViewById(R.id.manufacturerLabel);
                modelLabel = (TextView) view.findViewById(R.id.modelLabel);
                imgLabel=(ImageView)view.findViewById(R.id.imgLabel);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + manufacturerLabel.getText() + "'";
            }
        }
    }
}
