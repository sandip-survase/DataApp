package placeme.octopusites.com.placeme;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditProfileHr extends AppCompatActivity {

    ViewPager mViewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    ViewPagerAdapter adapter;
    FloatingActionButton fab;
    int lastPosition = 0, currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_hr);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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


                if (currentPosition == 0) {
                    HrPersonalTabFragment fragment = (HrPersonalTabFragment) adapter.getItem(0);
                    Boolean personal_success = fragment.validate();
                    if (fragment.edittedFlagp == 1) {
                        if (personal_success) {
                            fragment.save();
                            Toast.makeText(getApplicationContext(), "Successfully Updated !", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                if (currentPosition == 1) {
                    HrCompanyDetailsTabFragment hrCompanyDetailsTabFragment = (HrCompanyDetailsTabFragment) adapter.getItem(1);
                    Boolean project_success = hrCompanyDetailsTabFragment.validate();
                    if (hrCompanyDetailsTabFragment.flag1 == 1) {
                        if (project_success) {
                            hrCompanyDetailsTabFragment.save();
                            Toast.makeText(getApplicationContext(), "Successfully Updated !", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                if (currentPosition == 3) {
                    HrExperiencesTabFragment hrExperiencesTabFragment = (HrExperiencesTabFragment) adapter.getItem(3);
                    if(hrExperiencesTabFragment.editexp == 1){
                        hrExperiencesTabFragment.save();
                        Toast.makeText(EditProfileHr.this, "Successfully Updated !", Toast.LENGTH_SHORT).show();

                    }else {
                        Boolean Exp_success = hrExperiencesTabFragment.validate();
                        if (hrExperiencesTabFragment.edittedFlag == 1) {
                               if (Exp_success) {
                                hrExperiencesTabFragment.save();
                                Toast.makeText(getApplicationContext(), "Successfully Updated !", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                }
                if (currentPosition == 4) {
                    HrContactTabFragment hrContactTabFragment = (HrContactTabFragment) adapter.getItem(4);
                    Boolean contact_success = hrContactTabFragment.validate();
                    if (hrContactTabFragment.edittedFlag == 1) {
                        if (contact_success) {
                            hrContactTabFragment.save();
                            Toast.makeText(getApplicationContext(), "Successfully Updated !", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);


            }
        });
    }

    void animateFab(int position) {
        if (position == 1) {
            if (lastPosition == 2) {
                Animation fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
                fab.startAnimation(fab_open);
            }
        }
        if (position == 2) {
            Animation fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
            fab.startAnimation(fab_close);
        }
        if (position == 3) {
            if (lastPosition == 2) {
                Animation fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
                fab.startAnimation(fab_open);
            }
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new HrPersonalTabFragment(), "Personal");
        adapter.addFrag(new HrCompanyDetailsTabFragment(), "Company Details");
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);
        Log.d("TAg", "onActivityResult: editprofilehr "+resultCode);
    }

    @Override
    public void onBackPressed() {
        HrPersonalTabFragment fragment = (HrPersonalTabFragment) adapter.getItem(0);
//        ProjectsProfileTabFragment projFrag = (ProjectsProfileTabFragment) adapter.getItem(2);
        HrCompanyDetailsTabFragment hrCompanyDetailsTabFragment = (HrCompanyDetailsTabFragment) adapter.getItem(1);
        HrExperiencesTabFragment hrExperiencesTabFragment = (HrExperiencesTabFragment) adapter.getItem(3);
        HrContactTabFragment hrContactTabFragment = (HrContactTabFragment) adapter.getItem(4);


        if (fragment.edittedFlagp == 1 || hrCompanyDetailsTabFragment.flag1 == 1 || hrExperiencesTabFragment.edittedFlag == 1 || hrContactTabFragment.edittedFlag == 1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder
                    .setMessage("Do you want to save all changes ?")
                    .setCancelable(false)
                    .setPositiveButton("save",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    int personalflag = 0, companyflag = 0, expflag = 0, conflag = 0;

                                    HrPersonalTabFragment fragment = (HrPersonalTabFragment) adapter.getItem(0);
                                    HrCompanyDetailsTabFragment hrCompanyDetailsTabFragment = (HrCompanyDetailsTabFragment) adapter.getItem(1);
                                    HrExperiencesTabFragment hrExperiencesTabFragment = (HrExperiencesTabFragment) adapter.getItem(3);
                                    HrContactTabFragment hrContactTabFragment = (HrContactTabFragment) adapter.getItem(4);

                                    Boolean personal_success = true;
                                    Boolean project_success = true;
                                    Boolean hr_exp = true;
                                    Boolean hr_contact = true;


                                    if (fragment.edittedFlagp == 1) {
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
                                    if (hrCompanyDetailsTabFragment.flag1 == 1) {
                                        project_success = hrCompanyDetailsTabFragment.validate();
                                        if (!project_success) {
                                            if (personalflag != 1) {
                                                mViewPager.setCurrentItem(1);
                                                hrCompanyDetailsTabFragment.validate();
                                                companyflag = 1;
                                            }

                                        } else {
                                            hrCompanyDetailsTabFragment.save();
                                            companyflag = 0;
                                        }

                                    }

                                    if(hrExperiencesTabFragment.editexp == 1){
                                        hrExperiencesTabFragment.save();
                                        personal_success=true;
                                    }
                                    else {
                                        if (hrExperiencesTabFragment.edittedFlag == 1) {
                                            hr_exp = hrExperiencesTabFragment.validate();
                                            if (!hr_exp) {
                                                if (personalflag != 1 && companyflag != 1) {
//                                                hrExperiencesTabFragment.setCount();
                                                    mViewPager.setCurrentItem(3);
                                                    hrExperiencesTabFragment.validate();
                                                    expflag = 1;
                                                }
                                            } else {
                                                hrExperiencesTabFragment.save();
                                                expflag = 0;
                                            }
                                        }
                                    }
                                    if (hrContactTabFragment.edittedFlag == 1) {
                                        hr_contact = hrContactTabFragment.validate();
                                        if (!hr_contact) {
                                            if (personalflag != 1 && companyflag != 1 && expflag != 1) {
                                                mViewPager.setCurrentItem(4);
                                                hrContactTabFragment.validate();
                                            }
                                        } else {
                                            hrContactTabFragment.save();
                                        }
                                    }

                                    if (personal_success && project_success && hr_exp && hr_contact) {
                                        Toast.makeText(getApplicationContext(), "Successfully Updated !", Toast.LENGTH_SHORT).show();
                                        setResult(HRActivity.HR_DATA_CHANGE_RESULT_CODE);
                                        EditProfileHr.super.onBackPressed();
                                    }

                                }
                            })

                    .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                            EditProfileHr.super.onBackPressed();
                        }
                    });

            final AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(EditProfileHr.this));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(EditProfileHr.this));
                }
            });

            alertDialog.show();

        } else
            EditProfileHr.super.onBackPressed();

    }



}
