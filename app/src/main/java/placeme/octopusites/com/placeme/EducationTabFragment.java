package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class EducationTabFragment extends Fragment {

    View tenth,twelth,ug,pg;
    String username;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_edit_profile_education, container, false);

        TextView tenthtxt=(TextView)rootView.findViewById(R.id.tenthtxt);
        TextView twelthtxt=(TextView)rootView.findViewById(R.id.twelthtxt);
        TextView ugtxt=(TextView)rootView.findViewById(R.id.ugtxt);
        TextView pgtxt=(TextView)rootView.findViewById(R.id.pgtxt);
        Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/arba.ttf");
        tenthtxt.setTypeface(custom_font1);
        twelthtxt.setTypeface(custom_font1);
        ugtxt.setTypeface(custom_font1);
        pgtxt.setTypeface(custom_font1);


        tenth=(View)rootView.findViewById(R.id.tenthbutton);
        twelth=(View)rootView.findViewById(R.id.twelfthbutton);
        ug=(View)rootView.findViewById(R.id.ugbutton);
        pg=(View)rootView.findViewById(R.id.pgbutton);

        tenth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileTenth.class).putExtra("username",username),0);
            }
        });


        twelth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileTwelthOrDiploma.class).putExtra("username",username),0);
            }
        });

        ug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfileUg.class).putExtra("username",username),0);
            }
        });

        pg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MyProfilePg.class).putExtra("username",username),0);
            }
        });

        return rootView;
    }


    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation animation = super.onCreateAnimation(transit, enter, nextAnim);

        // HW layer support only exists on API 11+
        if (Build.VERSION.SDK_INT >= 11) {
            if (animation == null && nextAnim != 0) {
                animation = AnimationUtils.loadAnimation(getActivity(), nextAnim);
            }

            if (animation != null) {
                getView().setLayerType(View.LAYER_TYPE_HARDWARE, null);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    public void onAnimationEnd(Animation animation) {
                        getView().setLayerType(View.LAYER_TYPE_NONE, null);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }

                    // ...other AnimationListener methods go here...
                });
            }
        }

        return animation;
    }

}