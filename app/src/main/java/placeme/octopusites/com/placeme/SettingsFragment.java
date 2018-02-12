package placeme.octopusites.com.placeme;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

import me.leolin.shortcutbadger.ShortcutBadger;

public class SettingsFragment extends Fragment {

    View changepassselectionview, pushselectionview, emailselectionview, proselectionview, lastloginselectionview, reportselectionview, rateusselectionview;
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
        rateusselectionview = (View) rootView.findViewById(R.id.rateusselectionview);

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
        TextView ratetxt = (TextView) rootView.findViewById(R.id.ratetxt);

        TextView emailemailtxt=(TextView)rootView.findViewById(R.id.emailemailtxt);
        TextView notifnotiftxt=(TextView)rootView.findViewById(R.id.notifnotiftxt);
        TextView passpasstxt=(TextView)rootView.findViewById(R.id.passpasstxt);

        TextView reportreporttxt=(TextView)rootView.findViewById(R.id.reportreporttxt);
        TextView rateratetxt = (TextView) rootView.findViewById(R.id.rateratetxt);
        TextView loginlogintxt=(TextView)rootView.findViewById(R.id.loginlogintxt);


        TextView faqtxt=(TextView)rootView.findViewById(R.id.faqtxt);
        TextView helptxt=(TextView)rootView.findViewById(R.id.helptxt);
        TextView privacytxt=(TextView)rootView.findViewById(R.id.privacytxt);
        TextView termstxt=(TextView)rootView.findViewById(R.id.termstxt);
        TextView agreementtxt=(TextView)rootView.findViewById(R.id.agreementtxt);
        TextView signouttxt=(TextView)rootView.findViewById(R.id.signouttxt);



//
        role=MySharedPreferencesManager.getRole(getActivity());



        emailtxt.setTypeface(Z.getBold(getActivity()));
        emailemailtxt.setTypeface(Z.getLight(getActivity()));
        notiftxt.setTypeface(Z.getBold(getActivity()));
        notifnotiftxt.setTypeface(Z.getLight(getActivity()));
        passtxt.setTypeface(Z.getBold(getActivity()));
        passpasstxt.setTypeface(Z.getLight(getActivity()));


        logintxt.setTypeface(Z.getBold(getActivity()));
        loginlogintxt.setTypeface(Z.getLight(getActivity()));
        reporttxt.setTypeface(Z.getBold(getActivity()));
        ratetxt.setTypeface(Z.getBold(getActivity()));
        reportreporttxt.setTypeface(Z.getLight(getActivity()));
        rateratetxt.setTypeface(Z.getLight(getActivity()));
        faqtxt.setTypeface(Z.getBold(getActivity()));
        helptxt.setTypeface(Z.getBold(getActivity()));
        privacytxt.setTypeface(Z.getBold(getActivity()));
        termstxt.setTypeface(Z.getBold(getActivity()));
        agreementtxt.setTypeface(Z.getBold(getActivity()));
        signouttxt.setTypeface(Z.getBold(getActivity()));


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

                new Thread(new Runnable() {
                    public void run() {
                        try {
                            FirebaseInstanceId.getInstance().deleteInstanceId();
                            Log.d("zzz", "clear InstanceId successfully");
                            FirebaseInstanceId.getInstance().getToken();  // trigger refereshtok

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d("zzz", "InstanceId exp : " + e.getMessage());

                        }
                    }
                }).start();


                // removing all vlaues from sharedpreferences
                SharedPreferences.Editor editor = sharedpreferences.edit();
//                editor.clear().commit();

                editor.putString("nameKey", null);
                editor.putString("passKey", null);
                editor.commit();

                StudentData studentData=new StudentData();
                studentData.setStudentNull();

                AdminData adminData=new AdminData();
                adminData.setAdminDataNull();

                HrData hrData=new HrData();
                hrData.setHrDataNull();

                //clear badge
                ShortcutBadger.removeCount(getActivity());

                ShouldAnimateProfile.isInside = false;
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
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

        rateusselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=placeme.octopusites.com.placeme")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=placeme.octopusites.com.placeme")));
                }
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

                Intent intent = new Intent(getActivity(), NewsFeedWebView.class);
                intent.putExtra("title", "Privacy Policy");
                intent.putExtra("url", "https://placeme.co.in/privacy-policy.jsp");
                startActivity(intent);

//                startActivity(new Intent(getActivity(),PrivacyPolicy.class));
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://placeme.co.in/privacy-policy.jsp"));
//                startActivity(browserIntent);
            }
        });
        termsselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), NewsFeedWebView.class);
                intent.putExtra("title", "Terms and Conditions");
                intent.putExtra("url", "https://placeme.co.in/terms-of-use.jsp");
                startActivity(intent);

//                startActivity(new Intent(getActivity(),TermsConditions.class));
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://placeme.co.in/terms-of-use.jsp"));
//                startActivity(browserIntent);
            }
        });
        agreementselectionview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), NewsFeedWebView.class);
                intent.putExtra("title", "User Agreement");
                intent.putExtra("url", "https://placeme.co.in/terms-of-use.jsp");
                startActivity(intent);

//                startActivity(new Intent(getActivity(),UserAgreement.class));
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://placeme.co.in/terms-of-use.jsp"));
//                startActivity(browserIntent);
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

