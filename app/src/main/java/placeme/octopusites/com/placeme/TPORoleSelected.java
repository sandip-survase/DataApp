package placeme.octopusites.com.placeme;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

public class TPORoleSelected extends AppCompatActivity {

    private boolean finishEnter;
    TextInputLayout professionalemailTextInputLayout;
    EditText professionalemail;
    View WelcomeRoleView;
    CardView studentrl,alumnirl,tporl,hrrl;
    TextView rolewelcometextviewcontext1;
    TextView rolewelcometextviewcontext2;
    TextView rolewelcometextviewcontext3;
    ImageView cancel;
    ImageView logo;
    TextView welcometextviewcontext1;
    boolean keyboardFlag=false;
    String professionalEmail;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tporole_selected);
        long transitionDuration = 800;
        if (null != savedInstanceState)
            transitionDuration = 0;

        // transition enter
        finishEnter = false;
        EasyTransition.enter(
                this,
                transitionDuration,
                new DecelerateInterpolator(),
                new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        // init other views after transition anim
                        finishEnter = true;

                    }
                });

        professionalemailTextInputLayout = (TextInputLayout)findViewById(R.id.professionalemailTextInputLayout);
        professionalemail = (EditText)findViewById(R.id.professionalemail);
        fadeandmove(professionalemailTextInputLayout);
        professionalemail.setTypeface(Z.getBold(this));
        professionalemailTextInputLayout.setTypeface(Z.getLight(this));
        professionalemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                professionalemailTextInputLayout.setError(null);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        professionalemail.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (keyboardShown(professionalemail.getRootView())) {
                    animateLogoUp();
                    keyboardFlag=true;
                } else {
                    if(keyboardFlag)
                        animateLogoDown();
                }
            }
        });
        Button btnPrev = (Button)findViewById(R.id.btnPrev);
        Button btnNext = (Button)findViewById(R.id.btn_next);

        btnNext.setTypeface(Z.getBold(this));
        btnPrev.setTypeface(Z.getBold(this));

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professionalEmail=professionalemail.getText().toString();
                if(professionalEmail!=null)
                    if(professionalEmail.contains(".edu"))
                    {
                        professionalEmail=professionalEmail.toLowerCase();
                        WelcomeRoleModal obj=new WelcomeRoleModal();
                        obj.setCode(professionalEmail);
                        setResult(3333);
                        finish();
                    }
                    else
                        professionalemailTextInputLayout.setError("Incorrect professional email.(Must contain .edu)");
                else
                    professionalemailTextInputLayout.setError("Kindly provide your professional email.");

            }
        });

        WelcomeRoleModal modal=new WelcomeRoleModal();
        modal.setCode(null);
        setResult(0000);

        AnimationModal obj=new AnimationModal();
        WelcomeRoleView=obj.getRoleView();
        studentrl=(CardView)WelcomeRoleView.findViewById(R.id.studentrl);
        alumnirl=(CardView)WelcomeRoleView.findViewById(R.id.alumnirl);
        tporl=(CardView)WelcomeRoleView.findViewById(R.id.tporl);
        hrrl=(CardView)WelcomeRoleView.findViewById(R.id.hrrl);
        logo = (ImageView)WelcomeRoleView.findViewById(R.id.logo);
        welcometextviewcontext1 = (TextView) WelcomeRoleView.findViewById(R.id.welcometextviewcontext1);
        rolewelcometextviewcontext1=(TextView)WelcomeRoleView.findViewById(R.id.welcometextviewcontext1);
        rolewelcometextviewcontext2=(TextView)WelcomeRoleView.findViewById(R.id.welcometextviewcontext2);
        rolewelcometextviewcontext3=(TextView)WelcomeRoleView.findViewById(R.id.welcometextviewcontext3);

        cancel=(ImageView)findViewById(R.id.cancelView);
        fadeandmove(cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    void animateLogoUp() {
        moveandfadefast(logo);
        moveandfadefast(welcometextviewcontext1);
    }
    void animateLogoDown()
    {
        fadeandmovefast(logo);
        fadeandmovefast(welcometextviewcontext1);
    }
    private boolean keyboardShown(View rootView) {

        final int softKeyboardHeight = 100;
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
        int heightDiff = rootView.getBottom() - r.bottom;
        return heightDiff > softKeyboardHeight * dm.density;
    }
    public void fadeandmovefast(View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadeinmovefast);
        view.startAnimation(animation1);
    }
    public void moveandfadefast(View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadeoutmovefast);
        view.startAnimation(animation1);
    }
    @Override
    public void onBackPressed() {
        if (finishEnter) {
            finishEnter = false;
            animateAllViews();
            EasyTransition.exit(TPORoleSelected.this, 1000, new DecelerateInterpolator());
        }
    }
    void animateAllViews()
    {
        moveandfade(professionalemailTextInputLayout);
        moveandfade(cancel);
        slideinleft1(rolewelcometextviewcontext1);
        slideinleft2(rolewelcometextviewcontext2);
        fade(rolewelcometextviewcontext3);
        scale2(studentrl);
        scale3(alumnirl);
        scale4(hrrl);
    }
    public void fade(View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadein);
        view.startAnimation(animation1);
    }
    public void slideinleft1(View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.slideinleft1);
        view.startAnimation(animation1);
    }
    public void slideinleft2(View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.slideinleft2);
        view.startAnimation(animation1);
    }
    public void fadeandmove(View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadeinmove);
        view.startAnimation(animation1);
    }
    public void moveandfade(View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadeoutmove);
        view.startAnimation(animation1);
    }
    public void scale2(View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.scaleup2);
        view.startAnimation(animation1);
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tporl.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public void scale3(View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.scaleup3);
        view.startAnimation(animation1);
    }
    public void scale4(View view){

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.scaleup4);
        view.startAnimation(animation1);
    }
}
