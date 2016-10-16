package com.team.mobi.photoboxi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.team.mobi.photoboxi.R;

import butterknife.ButterKnife;
import butterknife.Bind;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @Bind(R.id.etUsername)
    EditText _userName;
    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_phone)
    EditText _phoneNumber;
    @Bind(R.id.btn_selfieGo)
    Button _btn_selfieGo;
    @Bind(R.id.eula_text)
    TextView _eula_text;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        _btn_selfieGo.setEnabled(true);

        _btn_selfieGo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
        //setupUserNameErrorCheck();

/*
        _eula_text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                */
/*Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);*//*

            }
        });
*/
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {

            return;
        }

        _btn_selfieGo.setEnabled(false);

        String email = _userName.getText().toString();
        String phoneNumber = _phoneNumber.getText().toString();
        String userName = _userName.getText().toString();
        // TODO: start Camera Activity here
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }


    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String phoneNumber = _phoneNumber.getText().toString();
        String userName = _userName.getText().toString();

        if (userName.length() > 30 && userName.length() <= 4) {
            _userName.setError(getResources().getString(R.string.username_required));
            valid = false;
        } else {
            _userName.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (phoneNumber.isEmpty() || phoneNumber.length() != 10) {
            _phoneNumber.setError("Enter a valid 10 digit phone number");
            valid = false;
        } else {
            _phoneNumber.setError(null);
        }

        return valid;
    }

    private void setupUserNameErrorCheck() {
        final TextInputLayout floatingUsernameLabel = (TextInputLayout) findViewById(R.id.username_text_input_layout);
        floatingUsernameLabel.getEditText().addTextChangedListener(new TextWatcher() {
            // ...
            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() > 30 && text.length() <= 4) {
                    floatingUsernameLabel.setError(getString(R.string.username_required));
                    floatingUsernameLabel.setErrorEnabled(true);
                } else {
                    floatingUsernameLabel.setErrorEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}