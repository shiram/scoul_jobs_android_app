package com.scouljobs.hunter.scouljobs;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewJobActivity extends AppCompatActivity {

    TextView job_title, job_category, job_qualifications, job_requirements, job_description;
    AppCompatButton job_apply;
    String title, category, qualifications, requirements, description;
    int job_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job);

        try{
            job_id = getIntent().getExtras().getInt("job_id");
            title = getIntent().getExtras().getString("title");
            category = getIntent().getExtras().getString("category");
            description = getIntent().getExtras().getString("description");
            qualifications = getIntent().getExtras().getString("qualifications");
            requirements = getIntent().getExtras().getString("requirements");
        }catch (NullPointerException e){
            Toast.makeText(ViewJobActivity.this, "Please select correct job, thank you.", Toast.LENGTH_SHORT).show();
        }

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("View Job Details");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }



        job_apply = findViewById(R.id.job_apply);
        job_title = findViewById(R.id.job_title);
        job_category = findViewById(R.id.job_category);
        job_qualifications = findViewById(R.id.job_qualifications);
        job_requirements = findViewById(R.id.job_requirements);
        job_description = findViewById(R.id.job_description);

        job_title.setText("Title: "+title);
        job_category.setText("Category: "+category);
        job_qualifications.setText(qualifications);
        job_requirements.setText(requirements);
        job_qualifications.setText(qualifications);
        job_description.setText(description);

        job_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] TO = {"scoul_jobs@gmail.com"};
                // String[] CC = {""};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                // emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Apply For Job");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Apply for Job"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ViewJobActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
