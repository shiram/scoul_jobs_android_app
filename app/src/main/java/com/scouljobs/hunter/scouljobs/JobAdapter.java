package com.scouljobs.hunter.scouljobs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {

    private Context context;
    private List<JobData> list;
    private ArrayList<JobData> arrayList;
    private ProgressDialog progressDialog;

    public JobAdapter(Context context, List<JobData> list) {
        this.context = context;
        this.list = list;
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(list);

        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(Config.PROGRESS_BAR);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.job_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final JobData jobData = list.get(position);
        holder.job_title.setText(jobData.getJob_title());
        holder.job_posted.setText("Job Posted: "+jobData.getCreated_at());

        final int job_id = jobData.getJob_id();
        final String title = jobData.getJob_title();
        final String category = jobData.getJob_category();
        final String description = jobData.getJob_description();
        final String qualifications = jobData.getJob_qualifications();
        final String requirements = jobData.getJob_requirements();

        if(new CustomToast(context).getAccessLevel() < 1){
            holder.job_edit.setText("View Details");
            holder.job_delete.setText("Apply");
            holder.job_delete.setVisibility(View.INVISIBLE);

            holder.job_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //View Job Details
//                    final int job_id_i = list.get(position).getJob_id();
//                    final String title_i = list.get(position).getJob_title();
//                    final String category_i = list.get(position).getJob_category();
//                    final String description_i = list.get(position).getJob_description();
//                    final String qualifications_i = list.get(position).getJob_qualifications();
//                    final String requirements_i = list.get(position).getJob_requirements();

                    Intent intent = new Intent(context, ViewJobActivity.class);
                    intent.putExtra("job_id", job_id);
                    intent.putExtra("title", title);
                    intent.putExtra("category", category);
                    intent.putExtra("description", description);
                    intent.putExtra("qualifications", qualifications);
                    intent.putExtra("requirements", requirements);

                    context.startActivity(intent);
                }
            });

            holder.job_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Apply for Job
                    final int job_id = jobData.getJob_id();
                }
            });
        }else {
            holder.job_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Edit Job


                    Intent intent = new Intent(context, EditJob.class);
                    intent.putExtra("job_id", job_id);
                    intent.putExtra("title", title);
                    intent.putExtra("category", category);
                    intent.putExtra("description", description);
                    intent.putExtra("qualifications", qualifications);
                    intent.putExtra("requirements", requirements);

                    context.startActivity(intent);

                }
            });

            holder.job_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Delete Job
                    final int job_id = jobData.getJob_id();
                    progressDialog.show();
                    Receiver receiver = new Receiver(context, progressDialog, ManageJobsFragment.jobs_list);
                    receiver.deleteJobs(job_id);
                }
            });
        }





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        AppCompatTextView  job_title, job_posted;
        AppCompatTextView job_edit, job_delete;

        public ViewHolder(View view){
            super(view);
            job_posted = view.findViewById(R.id.job_posted);
            job_title = view.findViewById(R.id.job_title);
            job_edit = view.findViewById(R.id.job_edit);
           // product_date_created = view.findViewById(R.id.product_date_created);
            job_delete = view.findViewById(R.id.job_delete);
        }

        @Override
        public void onClick(View v) {


        }
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if(charText.length() == 0){
            list.addAll(arrayList);
        }else{
            for(JobData jobData : arrayList){
                if(jobData.getJob_title().toLowerCase(Locale.getDefault()).contains(charText) || jobData.getJob_category().toLowerCase(Locale.getDefault()).contains(charText)  || jobData.getCreated_at().toLowerCase(Locale.getDefault()).contains(charText) ){
                    list.add(jobData);
                }
            }
        }

        notifyDataSetChanged();
    }
}
