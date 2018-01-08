package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditProfileAdmin extends AppCompatActivity {

    ViewPager mViewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    FloatingActionButton fab;
    int lastPosition = 0, currentPosition = 0;
    ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_edit_profile_admin);
//        ShouldAnimateProfile.EditProfileAdmin = EditProfileAdmin.this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Profile");

        mViewPager = (ViewPager) findViewById(R.id.container);

        tabLayout = (TabLayout) findViewById(R.id.profiletabs);
        tabLayout.setupWithViewPager(mViewPager);

        setupViewPager(mViewPager);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentPosition = tab.getPosition();
                animateFab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                lastPosition = tab.getPosition();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                animateFab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentPosition==0){
                    AdminPersonalTabFragment fragment = (AdminPersonalTabFragment) adapter.getItem(0);
                    Boolean personal_success = fragment.validate();
                    if(fragment.edittedFlag==1){
                        if(personal_success){
                            fragment.save();
                            Toast.makeText(EditProfileAdmin.this, "Successfully Updated !", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                if(currentPosition==1){
                    AdminInstituteTabFragment adminInstituteTabFragment= (AdminInstituteTabFragment) adapter.getItem(1);
                    Boolean project_success = adminInstituteTabFragment.validate();
                    if(adminInstituteTabFragment.edittedFlag==1){

                        if(project_success){
                            adminInstituteTabFragment.save();
                            Toast.makeText(EditProfileAdmin.this, "Successfully Updated !", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                if(currentPosition==3){
                    HrExperiencesTabFragment ExperiencesTabFragment= (HrExperiencesTabFragment) adapter.getItem(3);
                    Boolean project_success = ExperiencesTabFragment.validate();
                    if(ExperiencesTabFragment.edittedFlag==1){
                        if(project_success){
                            ExperiencesTabFragment.save();
                            Toast.makeText(EditProfileAdmin.this, "Successfully Updated !", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                if(currentPosition==4){
                    HrContactTabFragment ContactTabFragment= (HrContactTabFragment) adapter.getItem(4);
                    Boolean project_success = ContactTabFragment.validate();
                    if(ContactTabFragment.edittedFlag==1){

                        if(project_success){
                            ContactTabFragment.save();
                            Toast.makeText(EditProfileAdmin.this, "Successfully Updated !", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                setResult(111);
            }
        });

    }//onc


    void animateFab(int position) {

        if (position == 2) {
//            if (lastPosition == 3) {
//                Animation fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
//                fab.startAnimation(fab_open);
//            } else {
                Animation fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
                fab.startAnimation(fab_close);
//            }
        } else if (position == 3) {
            if (lastPosition == 2) {
                Animation fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
                fab.startAnimation(fab_open);
            }
        } else if (position == 1) {
            if (lastPosition == 2) {
                Animation fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
                fab.startAnimation(fab_open);
            }
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new AdminPersonalTabFragment(), "Personal");
        adapter.addFrag(new AdminInstituteTabFragment(), "Institute");
        adapter.addFrag(new AdminAccomplishmentsTabFragment(), "Accomplishments");
        adapter.addFrag(new HrExperiencesTabFragment(), "Experiences");
        adapter.addFrag(new HrContactTabFragment(), "Contact");
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();

                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }



    @Override
    public void onBackPressed() {

        AdminPersonalTabFragment fragment = (AdminPersonalTabFragment) adapter.getItem(0);
        AdminInstituteTabFragment adminInstituteTabFragment= (AdminInstituteTabFragment) adapter.getItem(1);
        HrExperiencesTabFragment ExperiencesTabFragment= (HrExperiencesTabFragment) adapter.getItem(3);
        HrContactTabFragment ContactTabFragment= (HrContactTabFragment) adapter.getItem(4);


        if (fragment.edittedFlag == 1 || adminInstituteTabFragment.edittedFlag == 1 || ExperiencesTabFragment.edittedFlag == 1 || ContactTabFragment.edittedFlag == 1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder
                    .setMessage("Do you want to save all changes ?")
                    .setCancelable(false)
                    .setPositiveButton("save",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    int personalflag = 0, Instituteflag = 0, expflag = 0, conflag = 0;

                                    AdminPersonalTabFragment fragment = (AdminPersonalTabFragment) adapter.getItem(0);
                                    AdminInstituteTabFragment adminInstituteTabFragment= (AdminInstituteTabFragment) adapter.getItem(1);
                                    HrExperiencesTabFragment ExperiencesTabFragment= (HrExperiencesTabFragment) adapter.getItem(3);
                                    HrContactTabFragment ContactTabFragment= (HrContactTabFragment) adapter.getItem(4);


                                    Boolean personal_success = true;
                                    Boolean institute_success = true;
                                    Boolean hr_exp = true;
                                    Boolean hr_contact = true;


                                    if (fragment.edittedFlag == 1) {
                                        personal_success = fragment.validate();
                                        if (!personal_success) {
                                            mViewPager.setCurrentItem(0);
                                            fragment.validate();
                                            personalflag = 1;
                                        } else {
                                            personalflag = 0;
                                            fragment.save();
                                        }

                                    }
//
                                    if (adminInstituteTabFragment.edittedFlag == 1) {
                                        institute_success = adminInstituteTabFragment.validate();
                                        if (!institute_success) {
                                            if (personalflag != 1) {
                                                mViewPager.setCurrentItem(1);
                                                adminInstituteTabFragment.validate();
                                                Instituteflag = 1;
                                            }

                                        } else {
                                            adminInstituteTabFragment.save();
                                            Instituteflag = 0;
                                        }

                                    }
                                    if (ExperiencesTabFragment.edittedFlag == 1) {
                                        hr_exp = ExperiencesTabFragment.validate();
                                        if (!hr_exp) {
                                            if (personalflag != 1 && Instituteflag != 1) {
//                                                hrExperiencesTabFragment.setCount();
                                                mViewPager.setCurrentItem(3);
                                                ExperiencesTabFragment.validate();
                                                expflag = 1;
                                            }
                                        } else {
                                            ExperiencesTabFragment.save();
                                            expflag = 0;
                                        }
                                    }
                                    if (ContactTabFragment.edittedFlag == 1) {
                                        hr_contact = ContactTabFragment.validate();
                                        if (!hr_contact) {
                                            if (personalflag != 1 && Instituteflag != 1 && expflag != 1) {
                                                mViewPager.setCurrentItem(4);
                                                ContactTabFragment.validate();
                                            }
                                        } else {
                                            ContactTabFragment.save();
                                        }
                                    }

                                    if (personal_success && institute_success && hr_exp && hr_contact) {
                                        Toast.makeText(getApplicationContext(), "Successfully Saved..!", Toast.LENGTH_SHORT).show();
                                        setResult(111);
                                        EditProfileAdmin.super.onBackPressed();
                                    }
                                }
                            })

                    .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                            EditProfileAdmin.super.onBackPressed();
                        }
                    });

            final AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(EditProfileAdmin.this));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(EditProfileAdmin.this));
                }
            });

            alertDialog.show();

        } else
            EditProfileAdmin.super.onBackPressed();

    }

}
