package com.scouljobs.hunter.scouljobs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class Register extends AppCompatActivity {

    private AppCompatButton register, login;
    EditText user_firstname, user_lastname, user_dob, user_nationality, user_email, user_phone,user_address, user_password;
    AppCompatSpinner user_education;
    String firstname, lastname, dob, nationality, email, phone, address, password, eduaction;

    AwesomeValidation awesomeValidation;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.auth_register);
        login = findViewById(R.id.auth_login);

        register.setOnClickListener(mRegister);
        login.setOnClickListener(mLogin);

        user_firstname = findViewById(R.id.firstname);
        user_lastname = findViewById(R.id.lastname);
        user_dob = findViewById(R.id.date_of_birth);
        user_nationality = findViewById(R.id.nationality);
        user_email = findViewById(R.id.email);
        user_phone = findViewById(R.id.phone);
        user_address = findViewById(R.id.postal_address);
        user_password = findViewById(R.id.password);
        user_education = findViewById(R.id.education_level);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(Register.this, R.array.education, android.R.layout.simple_spinner_dropdown_item);
        user_education.setAdapter(arrayAdapter);
        user_education.setOnItemSelectedListener(mSelectEducationLevel);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(Config.REGISTER_PROGRESS);

    }

    private View.OnClickListener mRegister = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            initGUI();
            if(awesomeValidation.validate()){
                progressDialog.show();
                Uploader uploader = new Uploader(Register.this, progressDialog);
                uploader.register(firstname,lastname,dob,nationality,email,phone,eduaction,address,password);
            }

        }
    };

    private View.OnClickListener mLogin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);

        }
    };

    private void initGUI(){
       firstname = user_firstname.getText().toString().trim();
       lastname = user_lastname.getText().toString().trim();
       dob = user_dob.getText().toString().trim();
       nationality = user_nationality.getText().toString().trim();
       email = user_email.getText().toString().trim();
       phone = user_phone.getText().toString().trim();
       address = user_address.getText().toString().trim();
       password = user_password.getText().toString().trim();
       AddValidation();

    }

    private void AddValidation(){
        awesomeValidation.addValidation(user_firstname, RegexTemplate.NOT_EMPTY, getString(R.string.field_error));
        awesomeValidation.addValidation(user_lastname, RegexTemplate.NOT_EMPTY, getString(R.string.field_error));
        awesomeValidation.addValidation(user_dob, RegexTemplate.NOT_EMPTY, getString(R.string.date_err));
        awesomeValidation.addValidation(user_nationality, RegexTemplate.NOT_EMPTY, getString(R.string.field_error));
        awesomeValidation.addValidation(user_email, RegexTemplate.NOT_EMPTY, getString(R.string.email_err));
        awesomeValidation.addValidation(user_phone, RegexTemplate.TELEPHONE, getString(R.string.phone_err));
        awesomeValidation.addValidation(user_address, RegexTemplate.NOT_EMPTY, getString(R.string.field_error));
        awesomeValidation.addValidation(user_password, RegexTemplate.NOT_EMPTY, getString(R.string.password_err));
    }

    private AdapterView.OnItemSelectedListener mSelectEducationLevel = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            eduaction = adapterView.getItemAtPosition(i).toString();

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };


}
