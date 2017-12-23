
package placeme.octopusites.com.placeme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EditProfileAlumni extends AppCompatActivity {

    ViewPagerAdapter adapter;

    ViewPager mViewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    FloatingActionButton fab;
    int lastPosition=0,currentPosition=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_alumni);


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
                currentPosition=position;
                animateFab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 0 2 5
                if(currentPosition==0){
                    PersonalProfileTabFragment fragment = (PersonalProfileTabFragment) adapter.getItem(0);
                    Boolean personal_success = fragment.validate();
                    if(fragment.edittedFlag==1){
                        if(personal_success){
                            fragment.save();
                        }
                    }
                }
                if(currentPosition==2) {
                    ProjectsProfileTabFragment projFrag = (ProjectsProfileTabFragment) adapter.getItem(2);
                    Boolean project_success = projFrag.myvalidate();
                    if (projFrag.edittedFlag == 1) {

                        if (project_success) {
                            projFrag.save();
                        }
                    }
                }
                if(currentPosition==5) {
                    HrExperiencesTabFragment   expFrag= (HrExperiencesTabFragment) adapter.getItem(5);
                    Boolean project_success = expFrag.validate();
                    if (expFrag.edittedFlag == 1) {

                        if (project_success) {
                            expFrag.save();
                        }
                    }
                }

                setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
            }
        });

    }//onc

    void animateFab(int position)
    {

        if(position==0||position==2)
        {
            Animation fab_open= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
            fab.startAnimation(fab_open);
        }
        else if(position==1)
        {
            Animation fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
            fab.startAnimation(fab_close);

        }
        else if(position==3)
        {
            if(lastPosition==2) {
                Animation fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
                fab.startAnimation(fab_close);
            }

        }else if(position==5){

            if(lastPosition==4){
                Animation fab_open= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
                fab.startAnimation(fab_open);
            }
            if(lastPosition==6){
                Animation fab_open= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
                fab.startAnimation(fab_open);
            }
        }else if(position==4){

            if(lastPosition==5){
                Animation fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
                fab.startAnimation(fab_close);
            }
        }
        else if(position==6){
            Animation fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
            fab.startAnimation(fab_close);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new PersonalProfileTabFragment(), "Personal");          //0
        adapter.addFrag(new EducationTabFragment(), "Education");
        adapter.addFrag(new ProjectsProfileTabFragment(), "Projects");
        adapter.addFrag(new AchievementsProfileTabFragment(), "Accomplishments");
        adapter.addFrag(new CareerobjProfileTabFragment(), "Career Objectives");
        adapter.addFrag(new HrExperiencesTabFragment(), "Experiences");
        adapter.addFrag(new PrintProfileTabFragment(), "Print Profile");
        viewPager.setOffscreenPageLimit(6);
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

        if (resultCode == MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE)
            setResult(MainActivity.STUDENT_DATA_CHANGE_RESULT_CODE);

        if (resultCode == AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE)
            setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);

        Log.d("TAG", "onActivityResult: result "+resultCode);

    }

    @Override
    public void onBackPressed() {
        PersonalProfileTabFragment fragment = (PersonalProfileTabFragment) adapter.getItem(0);
        ProjectsProfileTabFragment projFrag = (ProjectsProfileTabFragment) adapter.getItem(2);
        HrExperiencesTabFragment   expFrag= (HrExperiencesTabFragment) adapter.getItem(5);


        if(fragment.edittedFlag==1 || projFrag.edittedFlag==1 || expFrag.edittedFlag==1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder
                    .setMessage("Do you want to save all changes ?")
                    .setCancelable(false)
                    .setPositiveButton("save",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    int personalflag = 0, projectflag = 0, expflag = 0;
                                    PersonalProfileTabFragment fragment = (PersonalProfileTabFragment) adapter.getItem(0);
                                    ProjectsProfileTabFragment projFrag = (ProjectsProfileTabFragment) adapter.getItem(2);
                                    HrExperiencesTabFragment   expFrag= (HrExperiencesTabFragment) adapter.getItem(5);

                                    Boolean personal_success = true;
                                    Boolean project_success = true;
                                    Boolean hr_exp = true;

                                    if(fragment.edittedFlag==1){
                                         personal_success = fragment.validate();
                                        if (!personal_success) {
                                            mViewPager.setCurrentItem(0);
                                            fragment.validate();
                                            personalflag = 1;
                                        } else {
                                            personalflag = 0;
                                            fragment.save();
                                            Toast.makeText(getApplicationContext(), "Successfully Updated !", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    if(projFrag.edittedFlag==1){
                                        project_success = projFrag.myvalidate();
                                        if (!project_success) {
                                            if (personalflag != 1) {
                                                mViewPager.setCurrentItem(2);
                                                projFrag.myvalidate();
                                                projectflag = 1;

                                            }
                                        } else {
                                            projFrag.save();
                                            projectflag = 0;
                                            Toast.makeText(getApplicationContext(), "Successfully Updated !", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    if(expFrag.edittedFlag==1){
                                        hr_exp = expFrag.validate();
                                        if (!hr_exp) {
                                            if (personalflag != 1 && projectflag != 1) {
                                                mViewPager.setCurrentItem(5);
                                                expFrag.validate();
                                                expflag = 1;
                                            }
                                        } else {
                                            expFrag.save();
                                            Toast.makeText(getApplicationContext(), "Successfully Updated !", Toast.LENGTH_SHORT).show();
                                            expflag = 0;
                                        }
                                    }
                                    Log.d("edit", "onClick: personal_success- " + personal_success);
                                    Log.d("edit", "onClick: project_success- " + project_success);
                                    Log.d("edit", "onClick: hr_exp- " + hr_exp);

                                    if (personal_success && project_success && hr_exp) {
                                        Toast.makeText(getApplicationContext(), "Successfully Saved..!", Toast.LENGTH_SHORT).show();
                                        setResult(AlumniActivity.ALUMNI_DATA_CHANGE_RESULT_CODE);
                                        EditProfileAlumni.super.onBackPressed();
                                    }



                                }
                            })

                    .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                            EditProfileAlumni.super.onBackPressed();
                        }
                    });

            final AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00bcd4"));
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Z.getBold(EditProfileAlumni.this));
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Z.getBold(EditProfileAlumni.this));
                }
            });

            alertDialog.show();

        }
        else
            EditProfileAlumni.super.onBackPressed();
    }


}

