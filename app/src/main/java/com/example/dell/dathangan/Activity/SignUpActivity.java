package com.example.dell.dathangan.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.dell.dathangan.R;
import com.example.dell.dathangan.Utils.BaseAPI;
import com.example.dell.dathangan.Utils.BaseBundle;
import com.example.dell.dathangan.Utils.BaseHttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SignUpActivity extends AppCompatActivity {

    private AutoCompleteTextView mNameView;
    private AutoCompleteTextView mEmailView;
    private AutoCompleteTextView mPhoneView;
    private EditText mPasswordView;
    private static ProgressDialog progressDialog;
    // Khởi tạo OkHttpClient để lấy dữ liệu.
    private static OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mNameView = (AutoCompleteTextView) findViewById(R.id.sign_up_name);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.sign_up_email);
        mPhoneView = (AutoCompleteTextView) findViewById(R.id.sign_up_phone_number);
        mPasswordView = (EditText) findViewById(R.id.sign_up_password);

        // Button đăng ký
        Button mEmailSignUpButton = (Button) findViewById(R.id.btn_sign_up_account);
        mEmailSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignUp();
            }
        });

        // Button đăng nhập
        Button mEmailSignInButton = (Button) findViewById(R.id.btn_sign_up_login);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void attemptSignUp() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String name = mNameView.getText().toString();
        final String email = mEmailView.getText().toString();
        String phone = mPhoneView.getText().toString();
        final String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            int result = BaseHttp.ActivitySignUp_SignUp(SignUpActivity.this, name, email, phone, password);
            if( result == 0){
                Intent intent = new Intent(SignUpActivity.this , LoginActivity.class);
                intent.putExtra(BaseBundle.SIGN_UP_EMAIL, email);
                intent.putExtra(BaseBundle.SIGN_UP_PASSWORD, password);
                startActivity(intent);
                finish();
            } else {
                mEmailView.setError(getString(R.string.error_invalid_email_vn));
                mEmailView.requestFocus();
            }
        }
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }
}
