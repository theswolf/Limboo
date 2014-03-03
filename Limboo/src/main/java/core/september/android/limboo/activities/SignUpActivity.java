package core.september.android.limboo.activities;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quickblox.core.result.Result;

import java.util.regex.Pattern;

import core.september.android.limboo.R;
import core.september.android.limboo.app.Limboo;
import core.september.android.limboo.constants.Const;
import core.september.android.limboo.ifaces.AuthCallback;
import core.september.android.limboo.models.AppUser;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class SignUpActivity extends LoginActivity {
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */

    // UI references.
    //   protected EditText mUserView;
    //   protected EditText mPasswordView;
    protected String mEmail;
    protected String mPasswordConfirm;
    protected EditText mEmailView;
    protected EditText mPasswordConfirmView;
    protected Button mAlredySignedButton;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected int getContentView() {
        return R.layout.activity_signup;
    }

    @Override
    protected void initiateUI() {

        super.initiateUI();
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordConfirmView = (EditText) findViewById(R.id.confirm_password);
        mAlredySignedButton = (Button) findViewById(R.id.already_signed_button);

        mAlredySignedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });


    }

    @Override
    protected boolean checkField() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordConfirmView.setError(null);

        // Store values at the time of the login attempt.
        mEmail = mEmailView.getText().toString();
        mPasswordConfirm = mPasswordConfirmView.getText().toString();

        cancel = false;


        // Check for a valid password.
        if (TextUtils.isEmpty(mPasswordConfirm) && !mPasswordConfirm.equals(mPassword)) {
            mPasswordConfirmView.setError(getString(R.string.error_pass_mismatching));
            focusView = mPasswordConfirmView;
            cancel = true;
        }

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        // Check for a valid password.
        if (TextUtils.isEmpty(mEmail) && !(pattern.matcher(mEmail).matches())) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        return super.checkField() && cancel;
    }

    protected void doLogin(boolean cancel, View focusView) {
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
            showProgress(true);


            //if (Limboo.getInstance().getAppUser() == null) {
            AppUser user = new AppUser();
                user.setUserName(mUsername);
                user.setPassword(mPassword);
                Limboo.getInstance().setAppUser(user);
            // }

            Limboo.getInstance().doSessionInit(new AuthCallback() {
                @Override
                public void onSessionSuccess(Result result) {
                    Limboo.getInstance().doSignUpSignInProcedure(this);
                    //SplashActivity.this.finish();
                }

                @Override
                public void onSessionError(Result result) {
                    StringBuilder sb = new StringBuilder();
                    for (String err : result.getErrors()) {
                        sb.append(err);
                        sb.append(Const.END_OF_LINE);
                    }

                    Toast.makeText(SignUpActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthSuccess(Result result) {
                    startActivity(new Intent(Limboo.getInstance().runningActivity, Const.LANDING_ACTIVITY));
                    Limboo.getInstance().runningActivity.finish();
                }

                @Override
                public void onAuthError(Result result) {
                    StringBuilder sb = new StringBuilder();
                    for (String err : result.getErrors()) {
                        sb.append(err);
                        sb.append(Const.END_OF_LINE);
                    }

                    Toast.makeText(SignUpActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this,SignUpActivity.class));
                    SignUpActivity.this.finish();
                }
            });

        }

            //mAuthTask = new UserLoginTask();
            //mAuthTask.execute((Void) null);
        }
    }

