package core.september.android.limboo.activities;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.quickblox.core.result.Result;

import core.september.android.limboo.R;
import core.september.android.limboo.abs.AuthActivity;
import core.september.android.limboo.app.Limboo;
import core.september.android.limboo.constants.Const;
import core.september.android.limboo.ifaces.AuthCallback;
import core.september.android.limboo.models.AppUser;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends AuthActivity {

    protected String mUsername;
    protected String mPassword;

    // UI references.
    protected EditText mUserView;
    protected EditText mPasswordView;
    protected boolean cancel;
    protected View focusView = null;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initiateUI() {
        // Set up the login form.
        //mEmail = getIntent().getStringExtra(EXTRA_EMAIL);
        mUserView = (EditText) findViewById(R.id.username);
        //mEmailView.setText(mEmail);

        mPasswordView = (EditText) findViewById(R.id.password);
//        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
//                if (id == R.id.login || id == EditorInfo.IME_NULL) {
//                    attemptLogin();
//                    return true;
//                }
//                return false;
//            }
//        });
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */

    protected boolean checkField() {
        // Reset errors.
        mUserView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        mUsername = mUserView.getText().toString();
        mPassword = mPasswordView.getText().toString();

        cancel = false;


        // Check for a valid password.
        if (TextUtils.isEmpty(mPassword)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (mPassword.length() < 4) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(mUsername)) {
            mUserView.setError(getString(R.string.error_field_required));
            focusView = mUserView;
            cancel = true;
        }

        return cancel;
    }

    public void attemptLogin() {

        doLogin(checkField(), focusView);
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
            //}

            Limboo.getInstance().doSessionInit(new AuthCallback() {
                @Override
                public void onSessionSuccess(Result result) {
                    Limboo.getInstance().doSigningProcedure(this);
                    //SplashActivity.this.finish();
                }

                @Override
                public void onSessionError(Result result) {
                    StringBuilder sb = new StringBuilder();
                    for (String err : result.getErrors()) {
                        sb.append(err);
                        sb.append(Const.END_OF_LINE);
                    }

                    Toast.makeText(LoginActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
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

                    Toast.makeText(LoginActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,LoginActivity.class));
                    LoginActivity.this.finish();
                }
            });

        }

        //mAuthTask = new UserLoginTask();
        //mAuthTask.execute((Void) null);
    }


            //mAuthTask = new UserLoginTask();
            //mAuthTask.execute((Void) null);
        }


