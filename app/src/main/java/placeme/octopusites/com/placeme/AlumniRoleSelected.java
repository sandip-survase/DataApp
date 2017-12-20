package placeme.octopusites.com.placeme;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AlumniRoleSelected extends AppCompatActivity {

    private boolean finishEnter;
    TextInputLayout instcodeTextInputLayout;
    TextInputEditText instcode;
    View WelcomeRoleView;
    CardView studentrl,alumnirl,tporl,hrrl;
    TextView rolewelcometextviewcontext1;
    TextView rolewelcometextviewcontext2;
    TextView rolewelcometextviewcontext3;
    ImageView cancel;
    ImageView logo;
    TextView welcometextviewcontext1;
    boolean keyboardFlag=false;
    String instituteCode;
    JSONParser jParser = new JSONParser();
    JSONObject json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_role_selected);
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

        instcodeTextInputLayout = (TextInputLayout)findViewById(R.id.instcodeTextInputLayout);
        instcode = (TextInputEditText)findViewById(R.id.instcode);
        fadeandmove(instcodeTextInputLayout);
        instcode.setTypeface(Z.getBold(this));
        instcodeTextInputLayout.setTypeface(Z.getLight(this));
        instcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                instcodeTextInputLayout.setError(null);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        instcode.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (keyboardShown(instcode.getRootView())) {
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
                instituteCode=instcode.getText().toString();
                if(instituteCode!=null)
                    if(instituteCode.length()==8)
                    {
                        instituteCode=instituteCode.toUpperCase();
                        WelcomeRoleModal obj=new WelcomeRoleModal();
                        obj.setCode(instituteCode);
                        new checkUcode().execute(instituteCode);
                    }
                    else
                        instcodeTextInputLayout.setError("Kindly provide correct institute code provided by Place Me.");
                else
                    instcodeTextInputLayout.setError("Kindly provide your institute code provided by Place Me.");
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
    class checkUcode extends AsyncTask<String, String, String> {
        protected String doInBackground(String... param) {

            String inputUcode = param[0];
            Log.d("TAG", "checkUcode: " + inputUcode);
            String r = null;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u", inputUcode));       //0

            json = jParser.makeHttpRequest(Z.url_checkUcode, "GET", params);
            Log.d("TAG", "checkUcode json : " + json);
            try {
                r = json.getString("info");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result != null && result.equals("found")) {
                setResult(2222);
                finish();

            } else {
                setResult(0000);
                Toast.makeText(AlumniRoleSelected.this, "Invalid Institute Code\nplease contact your TPO", Toast.LENGTH_LONG).show();
                instcodeTextInputLayout.setError("Invalid Institute Code\nplease contact your TPO");
            }
        }
    }
    @Override
    public void onBackPressed() {
        if (finishEnter) {
            finishEnter = false;
            animateAllViews();
            EasyTransition.exit(AlumniRoleSelected.this, 1000, new DecelerateInterpolator());
        }
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
    void animateAllViews()
    {
        moveandfade(instcodeTextInputLayout);
        moveandfade(cancel);
        slideinleft1(rolewelcometextviewcontext1);
        slideinleft2(rolewelcometextviewcontext2);
        fade(rolewelcometextviewcontext3);
        scale2(studentrl);
        scale3(tporl);
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
                alumnirl.setVisibility(View.VISIBLE);
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
