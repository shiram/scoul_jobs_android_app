package com.scouljobs.hunter.scouljobs;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClientFragment extends Fragment {

    private RecyclerView jobs_list;
    EditText job_search;

    ProgressDialog progressDialog;


    public ClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_client, container, false);

        jobs_list = v.findViewById(R.id.jobs_list);
        job_search = v.findViewById(R.id.job_search);


        job_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = job_search.getText().toString().toLowerCase(Locale.getDefault());
                AdapterConnector.jobAdapter.filter(text);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(Config.PROGRESS_BAR);

        progressDialog.show();
        Receiver receiver = new Receiver(getContext(),progressDialog,jobs_list);
        receiver.getJobs();




        return v;
    }

}
