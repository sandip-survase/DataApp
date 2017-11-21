package placeme.octopusites.com.placeme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeIntroThroughAdminFragment extends Fragment {


    public WelcomeIntroThroughAdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_welcome_intro_through_admin, container, false);

        ((Welcome)getActivity()).setWelComeIntroThroughAdminView(view);
        return view;

    }

}
