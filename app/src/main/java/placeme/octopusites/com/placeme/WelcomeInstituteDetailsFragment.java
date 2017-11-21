package placeme.octopusites.com.placeme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeInstituteDetailsFragment extends Fragment {


    public WelcomeInstituteDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_welcome_institute_details, container, false);
        ((WelcomeGenrateCodeActivity)getActivity()).setWelComeInstituteView(view);
        return view;

    }

}
