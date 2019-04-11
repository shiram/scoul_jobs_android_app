package com.scouljobs.hunter.scouljobs;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddJobsFragment extends Fragment {

    EditText job_title, job_categry, job_description, job_qualifications, job_requirements;
    AppCompatButton add_jobs;

    String org_name, title, category, description, qualifications, requirements;

    AwesomeValidation awesomeValidation;
    ProgressDialog progressDialog;


    public AddJobsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_jobs, container, false);

        job_title = v.findViewById(R.id.job_title);
        job_categry = v.findViewById(R.id.job_category);
        job_description = v.findViewById(R.id.job_description);
        job_qualifications = v.findViewById(R.id.job_qualification);
        job_requirements = v.findViewById(R.id.job_requirements);
        add_jobs = v.findViewById(R.id.add_jobs);

        add_jobs.setOnClickListener(mAddJobs);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(Config.PROGRESS_BAR);

        return v;
    }

    private View.OnClickListener mAddJobs = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            initializeData();
            if(awesomeValidation.validate()){
                //Send data to server
                progressDialog.show();
                Uploader uploader = new Uploader(getContext(), progressDialog);
                uploader.addJobs(title,category,description,qualifications,requirements);
                job_title.setText("");
                job_description.setText("");
                job_qualifications.setText("");
                job_requirements.setText("");
            }
        }
    };

    private void initializeData(){
        title = job_title.getText().toString().trim();
        category = job_categry.getText().toString().trim();
        description = job_description.getText().toString().trim();
        qualifications = job_qualifications.getText().toString().trim();
        requirements = job_requirements.getText().toString().trim();

        addValidation();
    }

    private void addValidation(){
        awesomeValidation.addValidation(job_title, RegexTemplate.NOT_EMPTY, getString(R.string.field_empty_err));
        awesomeValidation.addValidation(job_categry, RegexTemplate.NOT_EMPTY, getString(R.string.field_empty_err));
        awesomeValidation.addValidation(job_description, RegexTemplate.NOT_EMPTY, getString(R.string.field_empty_err));
        awesomeValidation.addValidation(job_qualifications, RegexTemplate.NOT_EMPTY, getString(R.string.field_empty_err));
        awesomeValidation.addValidation(job_requirements, RegexTemplate.NOT_EMPTY, getString(R.string.field_empty_err));
    }

}
