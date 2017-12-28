package placeme.octopusites.com.placeme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class WelcomePasswordFragment extends Fragment {



    public WelcomePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= (RelativeLayout)inflater.inflate(R.layout.fragment_welcome_password, container, false);

        ((Welcome)getActivity()).setWelComePasswordView(view);

        return view;
    }


    }
