package com.scouljobs.hunter.scouljobs;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class EditJob extends AppCompatActivity {

    private String org_name, title, category, description, qualifications, requirements;
    private int job_id;

    EditText organisation_name, job_title, job_category, job_description, job_qualifications, job_requirements;
    AppCompatButton edit_job;

    AwesomeValidation awesomeValidation;
    CustomToast customToast;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_job);
        customToast = new CustomToast(EditJob.this);
        try{
            org_name = getIntent().getExtras().getString("org_name");
            job_id = getIntent().getExtras().getInt("job_id");
            title = getIntent().getExtras().getString("title");
            category = getIntent().getExtras().getString("category");
            description = getIntent().getExtras().getString("description");
            qualifications = getIntent().getExtras().getString("qualifications");
            requirements = getIntent().getExtras().getString("requirements");
        }catch (NullPointerException e){
            customToast.setToast("Application Error has occurred, close application.");
        }

        organisation_name = findViewById(R.id.organisation_name);
        job_title = findViewById(R.id.job_title);
        job_category = findViewById(R.id.job_category);
        job_description = findViewById(R.id.job_description);
        job_qualifications = findViewById(R.id.job_qualification);
        job_requirements = findViewById(R.id.job_requirements);
        edit_job = findViewById(R.id.edit_job);

        organisation_name.setText(org_name);
        job_title.setText(title);
        job_category.setText(category);
        job_description.setText(description);
        job_qualifications.setText(qualifications);
        job_requirements.setText(requirements);

        edit_job.setOnClickListener(mEditJob);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        progressDialog = new ProgressDialog(EditJob.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(Config.PROGRESS_BAR);
    }

    private View.OnClickListener mEditJob = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            initGUI();
            if(awesomeValidation.validate()){
                progressDialog.show();
                Uploader uploader = new Uploader(EditJob.this, progressDialog);
                uploader.updateJobs(job_id,title,category,description,qualifications,requirements);
            }
        }
    };

    private void initGUI(){
        org_name = organisation_name.getText().toString().trim();
        title = job_title.getText().toString().trim();
        category = job_category.getText().toString().trim();
        description = job_description.getText().toString().trim();
        qualifications = job_qualifications.getText().toString().trim();
        requirements = job_requirements.getText().toString().trim();

        addValidation();
    }

    private void addValidation(){
        awesomeValidation.addValidation(organisation_name, RegexTemplate.NOT_EMPTY, getString(R.string.field_empty_err));
        awesomeValidation.addValidation(job_title, RegexTemplate.NOT_EMPTY, getString(R.string.field_empty_err));
        awesomeValidation.addValidation(job_category, RegexTemplate.NOT_EMPTY, getString(R.string.field_empty_err));
        awesomeValidation.addValidation(job_description, RegexTemplate.NOT_EMPTY, getString(R.string.field_empty_err));
        awesomeValidation.addValidation(job_qualifications, RegexTemplate.NOT_EMPTY, getString(R.string.field_empty_err));
        awesomeValidation.addValidation(job_requirements, RegexTemplate.NOT_EMPTY, getString(R.string.field_empty_err));
    }


}
