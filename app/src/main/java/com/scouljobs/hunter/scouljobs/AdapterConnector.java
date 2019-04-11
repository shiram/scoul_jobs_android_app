package com.scouljobs.hunter.scouljobs;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdapterConnector {

    private RecyclerView jobs_list;
    private List<JobData> jobData;
    private  RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    private String data;

    public static JobAdapter jobAdapter;
    private Context context;
    CustomToast customToast;

    public AdapterConnector(RecyclerView jobs_list, String data, Context context) {
        this.jobs_list = jobs_list;
        this.data = data;
        this.context = context;

        customToast = new CustomToast(context);
    }

    public void populateList(){
        try{
            jobData = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(data);
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int job_id = jsonObject.getInt("job_id");
                String job_title = jsonObject.getString("job_title");
                String job_description = jsonObject.getString("job_description");
                String job_category = jsonObject.getString("job_category");
                String job_qualifications = jsonObject.getString("job_qualifications");
                String job_requirements = jsonObject.getString("job_requirements");
                String created_at = jsonObject.getString("created_at");
                String updated_at = jsonObject.getString("updated_at");

                jobData.add(new JobData(job_id,job_title,job_category,job_description,job_qualifications,job_requirements,created_at,updated_at));
            }
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            jobs_list.setLayoutManager(layoutManager);

            jobAdapter = new JobAdapter(context, jobData);
            jobs_list.setAdapter(jobAdapter);
        }catch (JSONException e){
            customToast.setToast(Config.NO_DATA_ERROR);
            Log.e("MSG", e.getMessage().toString());
        }
    }
}
