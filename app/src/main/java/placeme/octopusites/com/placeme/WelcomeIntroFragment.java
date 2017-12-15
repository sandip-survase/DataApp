package placeme.octopusites.com.placeme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import static placeme.octopusites.com.placeme.AES4all.demo1encrypt;


public class WelcomeIntroFragment extends Fragment {

    View view;



    public WelcomeIntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_welcome_intro, container, false);

        ((Welcome)getActivity()).setWelComeIntroView(view);

        return view;




    }

}
