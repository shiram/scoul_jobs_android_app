package com.scouljobs.hunter.scouljobs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.EditText;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
public class MainActivity extends AppCompatActivity {

    private AppCompatButton register, login;
    AppCompatTextView auth_password_reset;
    EditText auth_email, auth_password;

    AwesomeValidation awesomeValidation;

    String email, password;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.auth_register);
        login = findViewById(R.id.auth_login);
        auth_email = findViewById(R.id.auth_email);
        auth_password = findViewById(R.id.auth_password);
        auth_password_reset = findViewById(R.id.auth_password_reset);

        register.setOnClickListener(mRegister);
        login.setOnClickListener(mLogin);
        auth_password_reset.setOnClickListener(mPasswordRequest);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(Config.LOGIN_PROGRESS);
    }

    private View.OnClickListener mPasswordRequest = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //To the password request page.
        }
    };


    private View.OnClickListener mRegister = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener mLogin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            initGUI();
            if(awesomeValidation.validate()){
                progressDialog.show();
                Receiver receiver = new Receiver(MainActivity.this, progressDialog);
                receiver.userLogin(email,password);
            }

        }
    };

    private void initGUI(){
        email = auth_email.getText().toString().trim();
        password = auth_password.getText().toString().trim();
        AddValidation();
    }

    private void AddValidation(){
        awesomeValidation.addValidation(auth_email, RegexTemplate.NOT_EMPTY, getString(R.string.email_err));
        awesomeValidation.addValidation(auth_email, RegexTemplate.NOT_EMPTY, getString(R.string.password_err));
    }



}
