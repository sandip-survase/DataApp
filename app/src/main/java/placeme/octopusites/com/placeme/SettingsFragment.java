package placeme.octopusites.com.placeme;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class SettingsFragment extends Fragment {

    View changepassselectionview,pushselectionview,emailselectionview,proselectionview,lastloginselectionview,reportselectionview;
    View faqselectionview,helpselectionview,privacyselectionview,termsselectionview,agreementselectionview,signoutselectionview;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    String role;

    public SettingsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        changepassselectionview=(View)rootView.findViewById(R.id.changepassselectionview);
        pushselectionview=(View)rootView.findViewById(R.id.pushselectionview);
        signoutselectionview=(View)rootView.findViewById(R.id.signoutselectionview);
        emailselectionview=(View)rootView.findViewById(R.id.emailselectionview);
        lastloginselectionview=(View)rootView.findViewById(R.id.lastloginselectionview);
        reportselectionview=(View)rootView.findViewById(R.id.reportselectionview);
        proselectionview=(View)rootView.findViewById(R.id.proselectionview);

        faqselectionview=(View)rootView.findViewById(R.id.faqselectionview);
        helpselectionview=(View)rootView.findViewById(R.id.helpselectionview);
        privacyselectionview=(View)rootView.findViewById(R.id.privacyselectionview);
        termsselectionview=(View)rootView.findViewById(R.id.termsselectionview);
        agreementselectionview=(View)rootView.findViewById(R.id.agreementselectionview);



        TextView emailtxt=(TextView)rootView.findViewById(R.id.emailtxt);
        TextView notiftxt=(TextView)rootView.findViewById(R.id.notiftxt);
        TextView passtxt=(TextView)rootView.findViewById(R.id.passtxt);
        TextView logintxt=(TextView)rootView.findViewById(R.id.logintxt);
        TextView reporttxt=(TextView)rootView.findViewById(R.id.reporttxt);

        TextView emailemailtxt=(TextView)rootView.findViewById(R.id.emailemailtxt);
        TextView notifnotiftxt=(TextView)rootView.findViewById(R.id.notifnotiftxt);
        TextView passpasstxt=(TextView)rootView.findViewById(R.id.passpasstxt);
        TextView proprotxt=(TextView)rootView.findViewById(R.id.proprotxt);
        TextView reportreporttxt=(TextView)rootView.findViewById(R.id.reportreporttxt);
        TextView loginlogintxt=(TextView)rootView.findViewById(R.id.loginlogintxt);


        TextView faqtxt=(TextView)rootView.findViewById(R.id.faqtxt);
        TextView helptxt=(TextView)rootView.findViewById(R.id.helptxt);
        TextView privacytxt=(TextView)rootView.findViewById(R.id.privacytxt);
        TextView termstxt=(TextView)rootView.findViewById(R.id.termstxt);
        TextView agreementtxt=(TextView)rootView.findViewById(R.id.agreementtxt);
        TextView signouttxt=(TextView)rootView.findViewById(R.id.signouttxt);


        TextView trytxt=(TextView)rootView.findViewById(R.id.trytxt);
//
        role=MySharedPreferencesManager.getRole(getActivity());

        if(role.equals("hr"))
        {
            proselectionview.setVisibility(View.GONE);
            trytxt.setVisibility(View.GONE);
            proprotxt.setVisibility(View.GONE);
            View settingsline4=(View)rootView.findViewById(R.id.settingsline4);
            settingsline4.setVisibility(View.GONE);
        }


        emailtxt.setTypeface(MyConstants.getBold(getActivity()));
        emailemailtxt.setTypeface(MyConstants.getLight(getActivity()));
        notiftxt.setTypeface(MyConstants.getBold(getActivity()));
        notifnotiftxt.setTypeface(MyConstants.getLight(getActivity()));
        passtxt.setTypeface(MyConstants.getBold(getActivity()));
        passpasstxt.setTypeface(MyConstants.getLight(getActivity()));
        trytxt.setTypeface(MyConstants.getBold(getActivity()));
        proprotxt.setTypeface(MyConstants.getLight(getActivity()));
        logintxt.setTypeface(MyConstants.getBold(getActivity()));
        loginlogintxt.setTypeface(MyConstants.getLight(getActivity()));
        reporttxt.setTypeface(MyConstants.getBold(getActivity()));
        reportreporttxt.setTypeface(MyConstants.getLight(getActivity()));
        faqtxt.setTypeface(MyConstants.getBold(getActivity()));
        helptxt.setTypeface(MyConstants.getBold(getActivity()));
        privacytxt.setTypeface(MyConstants.getBold(getActivity()));
        termstxt.setTypeface(MyConstants.getBold(getActivity()));
        agreementtxt.setTypeface(MyConstants.getBold(getActivity()));
        signouttxt.setTypeface(MyConstants.getBold(getActivity()));

        proselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),ProSplashScreen.class));
            }
        });

        pushselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),PushNotificationPreferences.class));
            }
        });

        changepassselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),ChangePasswordActivity.class));
            }
        });
        signoutselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // removing all vlaues from sharedpreferences
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear().commit();

//                editor.putString("nameKey", null);
//                editor.putString("passKey", null);
//                editor.commit();

                startActivity(new Intent(getActivity(),LoginActivity.class));
                getActivity().finish();

            }
        });
        emailselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),EditEmail.class));
            }
        });

        lastloginselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),LastSession.class));
            }
        });

        reportselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),ReportBug.class));
            }
        });

        faqselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),FAQ.class));
            }
        });
        helpselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),HelpCenter.class));
            }
        });
        privacyselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),PrivacyPolicy.class));
            }
        });
        termsselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),TermsConditions.class));
            }
        });
        agreementselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),UserAgreement.class));
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
    @Override
    public void onAttach(final Activity activity) {

        super.onAttach(activity);

        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(final Menu menu) {

        super.onPrepareOptionsMenu(menu);

        menu.clear();
    }


}

