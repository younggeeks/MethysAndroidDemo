package younggeeks.com.methystake2;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import younggeeks.com.methystake2.dummy.DummyContent;
import younggeeks.com.methystake2.model.Car;
import younggeeks.com.methystake2.model.CarManager;

/**
 * A fragment representing a single Car detail screen.
 * This fragment is either contained in a {@link CarListActivity}
 * in two-pane mode (on tablets) or a {@link CarDetailActivity}
 * on handsets.
 */
public class CarDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";



    /**
     *Samwel Charles
     *
     */
    private  Activity activity;


    /**
     *Samwel Charles
     *This will help determine if the device is tablet or phone
     */
   private boolean phone;

    /**
     * Samwel Charles
     * The Car Object .
     */
    private Car car;


    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CarDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            //this is getting the current Car Object by passing Car Id as parameter
           car=CarManager.getInstance().getCarMap().get(Integer.parseInt(getArguments().getString(ARG_ITEM_ID)));

            activity= this.getActivity();
            final CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

            if (appBarLayout != null) {
                //added appbar title l
                phone=true;

                //here i added manufacturer and model text when using phone those info will showup as appbar title
                appBarLayout.setTitle(car.getManufacturer()+" "+car.getModel());

                //also when using phone i used Car Photo as Appbar Background,
                // the drawable name is the same as car manufacturer for demo purpose

                ((ImageView)activity.findViewById(R.id.header)).setImageResource(car.getDrawable(getContext(),car.getManufacturer()));

            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.car_detail, container, false);

        if (car != null) {
//
            //this will check if device is not a phone and rootview is loaded(not null)
            //then if it is a tablet view it'll show image to the right
            if (!phone && rootView.findViewById(R.id.car_photo)!=null)
            {
                ((ImageView)rootView.findViewById(R.id.car_photo)).setImageResource(car.getDrawable(getContext(),car.getManufacturer()));
            }


            //setting the rest of Car details

             ((TextView)rootView.findViewById(R.id.manufacturer)).setText(car.getManufacturer());
            ((TextView)rootView.findViewById(R.id.model)).setText(car.getModel());
            ((TextView)rootView.findViewById(R.id.year)).setText(car.getYear());
            ((TextView)rootView.findViewById(R.id.color)).setText(car.getColor());
            ((TextView)rootView.findViewById(R.id.topSpeed)).setText(car.getTopSpeed());
            ((TextView)rootView.findViewById(R.id.type)).setText(car.getType());
            ((TextView)rootView.findViewById(R.id.category)).setText(car.getCategory());
            ((TextView)rootView.findViewById(R.id.fuel)).setText(car.getFuel());
            ((TextView)rootView.findViewById(R.id.wheels)).setText(car.getWheels());
            ((TextView)rootView.findViewById(R.id.transmission)).setText(car.getTransmission());
        }

        return rootView;
    }
}
